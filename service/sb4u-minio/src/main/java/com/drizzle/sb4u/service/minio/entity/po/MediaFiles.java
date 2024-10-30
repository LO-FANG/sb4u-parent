package com.drizzle.sb4u.service.minio.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.drizzle.sb4u.service.base.model.BaseEntity;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 媒资信息
 * </p>
 *
 * @author drizzle
 * @since 2024-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_media_files")
@ApiModel(value="MediaFiles对象", description="媒资信息")
public class MediaFiles extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "文件名称")
    private String filename;

    @ApiModelProperty(value = "文件类型（图片、文档，视频）")
    private String fileType;


    @ApiModelProperty(value = "存储目录")
    private String bucket;

    @ApiModelProperty(value = "存储路径")
    private String filePath;

    @ApiModelProperty(value = "文件id")
    private String fileId;

    @ApiModelProperty(value = "媒资文件访问地址")
    private String url;

    @ApiModelProperty(value = "上传人")
    private String username;


    @ApiModelProperty(value = "状态,1:正常，0:不展示")
    private String status;


    @ApiModelProperty(value = "文件大小")
    private Long fileSize;


}
