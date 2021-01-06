package cn.antigenmhc.otaku.service.base.exception;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/6 19:07
 * @Version: 1.0
 **/
@Data
public class IntegrateException extends RuntimeException {

    private Integer code;
    private String message;

    public IntegrateException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public IntegrateException(ResultCodeEnum resultCodeEnum){
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }
}
