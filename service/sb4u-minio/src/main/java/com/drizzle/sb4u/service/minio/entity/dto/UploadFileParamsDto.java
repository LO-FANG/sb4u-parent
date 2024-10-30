package com.drizzle.sb4u.service.minio.entity.dto;

import com.drizzle.sb4u.service.minio.entity.po.MediaFiles;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/25/10:27
 * @Description:
 */
@Data
public class UploadFileParamsDto {
    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件id
     */
    private String id;

    private String bucket;

    private String filePath;


    /**
     * 文件类型（sol、js、ts、ruby...）
     */
    private String fileType;
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 上传人
     */
    private String username;

}
