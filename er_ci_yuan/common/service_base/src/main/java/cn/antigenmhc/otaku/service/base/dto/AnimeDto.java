package cn.antigenmhc.otaku.service.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 21:51
 * @Version: 1.0
 **/
@Data
public class AnimeDto implements Serializable {
    private static final long serialVersionUID=1L;

    private String id;
    private String title;
    private BigDecimal price;
    private String cover;
    private String adminName;
}
