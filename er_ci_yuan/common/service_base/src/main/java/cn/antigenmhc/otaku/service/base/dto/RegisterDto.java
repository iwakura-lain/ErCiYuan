package cn.antigenmhc.otaku.service.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/9 21:14
 * @Version: 1.0
 **/
@Data
public class RegisterDto implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer registerCount;
    private Integer giteeCount;
    private Integer githubCount;
}
