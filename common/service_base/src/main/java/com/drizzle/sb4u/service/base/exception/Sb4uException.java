package com.drizzle.sb4u.service.base.exception;

import com.drizzle.sb4u.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/10/15:26
 * @Description:
 */
@Data
public class Sb4uException extends RuntimeException{

    private Integer code;

    public Sb4uException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Sb4uException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
