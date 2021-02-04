package cn.antigenmhc.otaku.service.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 22:51
 * @Version: 1.0
 **/
@Data
public class MemberDto implements Serializable {
    private static final long serialVersionUID=1L;

    private String id;
    private String nickname;
    private String mobile;
}
