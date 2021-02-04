package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/3 22:32
 * @Version: 1.0
 **/
@Data
public class AnimeCollectVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String animeId;
    private String cover;
    private String title;
    private String adminName;
    private String gmtCreate;
    private BigDecimal price;
}
