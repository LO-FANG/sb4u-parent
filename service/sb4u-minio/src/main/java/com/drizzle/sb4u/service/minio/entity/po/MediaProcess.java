package com.drizzle.sb4u.service.minio.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.drizzle.sb4u.service.base.model.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author drizzle
 * @since 2024-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_media_process")
@ApiModel(value="MediaProcess对象", description="")
public class MediaProcess extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件标识")
    private String fileId;

    @ApiModelProperty(value = "文件名称")
    private String filename;

    @ApiModelProperty(value = "存储桶")
    private String bucket;

    @ApiModelProperty(value = "存储路径")
    private String filePath;

    @ApiModelProperty(value = "状态,1:未处理，2:处理成功，3:处理失败")
    private String status;

    @ApiModelProperty(value = "上传时间")
    private Date createDate;

    @ApiModelProperty(value = "完成时间")
    private Date finishDate;

    @ApiModelProperty(value = "媒资文件访问地址")
    private String url;

    @ApiModelProperty(value = "失败原因")
    private String errormsg;


}
