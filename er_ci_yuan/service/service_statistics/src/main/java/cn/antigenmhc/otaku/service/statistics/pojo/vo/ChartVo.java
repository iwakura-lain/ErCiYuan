package cn.antigenmhc.otaku.service.statistics.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/9 22:05
 * @Version: 1.0
 **/
@Data
public class ChartVo implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer registerCount;
    private Integer giteeCount;
    private Integer githubCount;
}
