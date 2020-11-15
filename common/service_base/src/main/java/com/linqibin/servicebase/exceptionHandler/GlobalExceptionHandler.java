package com.linqibin.servicebase.exceptionHandler;

import com.linqibin.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一异常处理类
 * @param
 * @return
 * @author linqibin&youmei
 * @creed: ProjectForSDDM
 * @date 2020/10/14 20:20
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //指定出现那种异常执行这个方法
    @ResponseBody   //将Result返回到前端
    public Result error(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error();
    }


}
