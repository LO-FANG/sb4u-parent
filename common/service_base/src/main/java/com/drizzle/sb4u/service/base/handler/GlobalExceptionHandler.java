package com.drizzle.sb4u.service.base.handler;

import com.drizzle.sb4u.common.base.result.R;
import com.drizzle.sb4u.common.base.util.ExceptionUtils;
import com.drizzle.sb4u.service.base.exception.Sb4uException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/10/9:03
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        log.error(ExceptionUtils.getMessage(e));
        return R.error();
    }


    @ExceptionHandler(Sb4uException.class)
    public R error(Sb4uException e){
        log.error(ExceptionUtils.getMessage(e));
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
