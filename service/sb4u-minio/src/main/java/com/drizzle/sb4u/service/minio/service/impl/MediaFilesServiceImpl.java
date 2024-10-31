package com.drizzle.sb4u.service.minio.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.common.base.result.PageResult;
import com.drizzle.sb4u.common.base.result.ResultCodeEnum;
import com.drizzle.sb4u.service.base.exception.Sb4uException;
import com.drizzle.sb4u.service.minio.entity.dto.QueryMediaParamsDto;
import com.drizzle.sb4u.service.minio.entity.dto.UploadFileParamsDto;
import com.drizzle.sb4u.service.minio.entity.dto.UploadFileResultDto;
import com.drizzle.sb4u.service.minio.entity.po.MediaFiles;
import com.drizzle.sb4u.service.minio.feignclient.ContractServiceClient;
import com.drizzle.sb4u.service.minio.mapper.MediaFilesMapper;
import com.drizzle.sb4u.service.minio.service.MediaFilesService;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 媒资信息 服务实现类
 * </p>
 *
 * @author drizzle
 * @since 2024-10-23
 */
@Service
@Slf4j
public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements MediaFilesService {


    @Autowired
    MinioClient minioClient;

    @Autowired
    MediaFilesService currentProxy;

    @Autowired
    MediaFilesMapper mediaFilesMapper;

    @Autowired
    ContractServiceClient contractServiceClient;

    //合约文件桶
    @Value("${minio.bucket.contractfiles}")
    private String contract_files_bucket;

    //获取文件默认存储目录路径 年/月/日
    private String getDefaultFolderPath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String folder = sdf.format(new Date()).replace("-", "/")+"/";
        return folder;
    }

    //获取文件的md5
    private String getFileMd5(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String fileMd5 = DigestUtils.md5Hex(fileInputStream);
            return fileMd5;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取文件扩展类型
    private String getMimeType(String extension){
        if(extension==null)
            extension = "";
        //根据扩展名取出mimeType
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        //通用mimeType，字节流
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if(extensionMatch!=null){
            mimeType = extensionMatch.getMimeType();
        }
        return mimeType;
    }

    /**
     * @description 将文件写入minIO
     * @param localFilePath  文件地址
     * @param bucket  桶
     * @param objectName 对象名称
     */
    public boolean addMediaFilesToMinIO(String localFilePath,String mimeType,String bucket, String objectName) {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .filename(localFilePath)
                    .contentType(mimeType)
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
            log.debug("上传文件到minio成功,bucket:{},objectName:{}",bucket,objectName);
            System.out.println("上传成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件到minio出错,bucket:{},objectName:{},错误原因:{}",bucket,objectName,e.getMessage(),e);
        }
        return false;
    }


    /**
     * @description 将文件信息添加到文件表
     * @param fileMd5  文件md5值
     * @param uploadFileParamsDto  上传文件的信息
     * @param bucket  桶
     * @param objectName 对象名称
     */
    @Transactional
    public MediaFiles addMediaFilesToDb(String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName) {
        //从数据库查询文件
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
        if (mediaFiles == null) {
            mediaFiles = new MediaFiles();
            //拷贝基本信息
            BeanUtils.copyProperties(uploadFileParamsDto, mediaFiles);
            mediaFiles.setId(fileMd5);
            mediaFiles.setFileId(fileMd5);
            mediaFiles.setUrl("/" + bucket + "/" + objectName);
            mediaFiles.setBucket(bucket);
            mediaFiles.setFilePath(objectName);

            mediaFiles.setStatus("1");
            //保存文件信息到文件表
            int insert = mediaFilesMapper.insert(mediaFiles);
            if (insert <= 0) {
                log.error("保存文件信息到数据库失败,{}",mediaFiles.toString());
                return null;
            }
            log.debug("保存文件信息到数据库成功,{}",mediaFiles.toString());

        }
        return mediaFiles;

    }

    @Override
    public IPage<MediaFiles> selectPage(PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto) {
        LambdaQueryWrapper<MediaFiles> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 条件1： 文件名称
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryMediaParamsDto.getFilename()), MediaFiles::getFilename, queryMediaParamsDto.getFilename());
        // 条件2： 文件类型
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryMediaParamsDto.getFileType()), MediaFiles::getFileType, queryMediaParamsDto.getFileType());
        Page<MediaFiles> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        IPage<MediaFiles> contractBasePage = mediaFilesMapper.selectPage(page, lambdaQueryWrapper);
        long total = contractBasePage.getTotal();
        return contractBasePage;

    }


    @Override
    public Boolean deleteContractFileById(String fileId) {
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileId);
        String bucket = mediaFiles.getBucket();
        String objectName = mediaFiles.getFilePath();
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getDownloadUrl(String contractId) {
        String fileId = contractServiceClient.getFileIdById(contractId);
        if(fileId == null) return "";
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileId);
        if(mediaFiles == null) {
            return "";
        }
        String bucket = mediaFiles.getBucket();
        String objectName = mediaFiles.getFilePath();
        String downloadUrl = null;
        try {
            Map<String, String> params = new HashMap<>(3);
            params.put("response-content-type", "application/octet-stream");
            downloadUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .extraQueryParams(params)
                            .bucket(bucket).object(objectName).build()
            );
            return downloadUrl;
        } catch (Exception e) {
            log.error("获取下载链接失败:{}", e.getMessage());
            return downloadUrl;
        }
    }

    @Override
    public String getDetectFileUrl(String resultId) {

        MediaFiles mediaFiles = mediaFilesMapper.selectById(resultId);
        if(mediaFiles == null) {
            return "";
        }
        String bucket = mediaFiles.getBucket();
        String objectName = mediaFiles.getFilePath();
        String downloadUrl = null;
        try {
            Map<String, String> params = new HashMap<>(3);
            params.put("response-content-type", "application/octet-stream");
            downloadUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .extraQueryParams(params)
                            .bucket(bucket).object(objectName).build()
            );
            return downloadUrl;
        } catch (Exception e) {
            log.error("获取下载链接失败:{}", e.getMessage());
            return downloadUrl;
        }
    }


    @Override
    public UploadFileResultDto uploadFile(UploadFileParamsDto uploadFileParamsDto, String localFilePath, String objectName) {

        // 文件名
        String fileName = uploadFileParamsDto.getFilename();
        // 扩展名
        String extension = fileName.substring(fileName.lastIndexOf("."));
        // 文件类型
        String mineType = getMimeType(extension);
        // 获取子目录
        String folder = getDefaultFolderPath();
        // 获取文件md5值
        String fileMd5 = getFileMd5(new File(localFilePath));
        // 拼接文件名
        // 传入objectname为空， 则使用年/月/日目录结构
        if(StringUtils.isEmpty(objectName)) {
            objectName = folder + fileMd5 + extension;
        }

        //将文件上传到minio
        Boolean result = addMediaFilesToMinIO(localFilePath, mineType, contract_files_bucket, objectName);
        if (!result) throw new Sb4uException(ResultCodeEnum.FILE_UPLOAD_ERROR);

        // 将文件信息保存到数据库
        try {
            // 文件信息入库
            MediaFiles mediaFiles = currentProxy.addMediaFilesToDb(fileMd5, uploadFileParamsDto, contract_files_bucket, objectName);
            if (mediaFiles == null) throw new RuntimeException("文件上传后，保存信息失败");

            //准备返回的对象
            UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
            BeanUtils.copyProperties(mediaFiles, uploadFileResultDto);

            return uploadFileResultDto;
        } catch (Exception e) {
            log.debug("上传文件失败:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
