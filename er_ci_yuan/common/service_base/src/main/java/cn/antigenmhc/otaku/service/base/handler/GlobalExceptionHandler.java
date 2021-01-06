package cn.antigenmhc.otaku.service.base.handler;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: antigenMHC
 * @Date: 2020/12/9 23:11
 * @Version: 1.0
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理
     * @return：统一结果对象
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result generalError(Exception e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.error();
    }

    /**
     * SQL 语法错误异常处理 / SQL 错误处理
     * @return：错误枚举
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result badSqlException(BadSqlGrammarException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result jsonParseException(HttpMessageNotReadableException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    /**
     * 统一异常处理
     * @param e: 统一异常处理
     * @return
     */
    @ExceptionHandler(IntegrateException.class)
    @ResponseBody
    public Result integrateException(IntegrateException e){
        log.error(e.getMessage());
        return Result.error().setMessage(e.getMessage()).setCode(e.getCode());
    }
}
