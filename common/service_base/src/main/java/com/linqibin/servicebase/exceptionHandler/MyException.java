package com.linqibin.servicebase.exceptionHandler;

import com.linqibin.commonutils.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常
 *
 * @author hugh &you
 * @since 2020 /12/18 13:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {

    private Integer code;
    private String message;

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e) {
        e.printStackTrace();
        return Result.error().code(e.getCode()).message((e.getMessage()));
    }
}

