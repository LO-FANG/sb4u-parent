package com.drizzle.sb4u.service.minio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.common.base.result.PageResult;
import com.drizzle.sb4u.common.base.result.R;
import com.drizzle.sb4u.service.minio.entity.dto.QueryMediaParamsDto;
import com.drizzle.sb4u.service.minio.entity.dto.UploadFileParamsDto;
import com.drizzle.sb4u.service.minio.entity.dto.UploadFileResultDto;
import com.drizzle.sb4u.service.minio.entity.po.MediaFiles;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 媒资信息 服务类
 * </p>
 *
 * @author drizzle
 * @since 2024-10-23
 */
public interface MediaFilesService extends IService<MediaFiles> {

    /**
     * 上传文件
     * @param uploadFileParamsDto 上传文件信息
     * @param localFilePath 文件磁盘路径
     * @return 文件信息
     */
    public UploadFileResultDto uploadFile(UploadFileParamsDto uploadFileParamsDto, String localFilePath, String objectName);

    @Transactional
    public MediaFiles addMediaFilesToDb(String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName);

    IPage<MediaFiles> selectPage(PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);

    public Boolean deleteContractFileById(String fileId);

    public String getDownloadUrl(String contractId);

    public String getDetectFileUrl(String resultId);

}
