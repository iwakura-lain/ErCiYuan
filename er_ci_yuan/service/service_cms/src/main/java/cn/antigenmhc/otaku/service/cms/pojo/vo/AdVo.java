package cn.antigenmhc.otaku.service.cms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/07 18:32
 * @Version: 1.0
 **/
@Data
public class AdVo implements Serializable {

    private static final long serialVersionUID=1L;
    private String id;
    private String title;
    private Integer sort;
    private String type;
}
