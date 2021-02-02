package cn.antigenmhc.otaku.service.ucenter.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/31 9:27
 * @Version: 1.0
 **/
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mobile;
    private String password;
}
