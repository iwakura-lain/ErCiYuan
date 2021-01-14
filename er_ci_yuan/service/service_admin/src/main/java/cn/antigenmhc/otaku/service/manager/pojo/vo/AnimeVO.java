package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/14 18:18
 * @Version: 1.0
 **/
@Data
public class AnimeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String adminName;
    private Integer animeNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;
}
