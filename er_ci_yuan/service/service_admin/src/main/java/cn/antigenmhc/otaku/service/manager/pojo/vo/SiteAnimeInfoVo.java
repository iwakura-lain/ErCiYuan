package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/26 17:10
 * @Version: 1.0
 **/
@Data
public class SiteAnimeInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * anime 表
     */
    private String id;
    private String title;
    private BigDecimal price;
    private Integer animeNum;
    private String cover;
    private Long viewCount;
    /**
     * desc 表
     */
    private String description;
    /**
     * admin 表
     */
    private String adminId;
    private String adminName;
    private String intro;
    private String avatar;
    /**
     * subject 表
     */
    private String subjectLevelOneId;
    private String subjectLevelOne;
    private String subjectLevelTwoId;
    private String subjectLevelTwo;
}
