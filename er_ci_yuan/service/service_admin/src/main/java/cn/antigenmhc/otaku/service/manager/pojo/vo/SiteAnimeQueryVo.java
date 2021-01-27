package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/25 17:00
 * @Version: 1.0
 **/
@Data
public class SiteAnimeQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subjectParentId;
    private String subjectId;
    private int hasAnimeNumSort;
    private int hasViewSort;
    private int hasGmtModifiedSort;
    private int page;
    private int size;
}
