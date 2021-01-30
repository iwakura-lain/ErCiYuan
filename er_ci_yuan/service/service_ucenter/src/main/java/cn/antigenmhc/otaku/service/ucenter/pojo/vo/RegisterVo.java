package cn.antigenmhc.otaku.service.ucenter.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 22:26
 * @Version: 1.0
 **/
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickname;
    private String mobile;
    private String password;
    private String code;
}
