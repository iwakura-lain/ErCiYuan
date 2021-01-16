package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/15 22:52
 * @Version: 1.0
 **/
@Data
public class AnimePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer animeNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String adminName;
    private String price;
}
