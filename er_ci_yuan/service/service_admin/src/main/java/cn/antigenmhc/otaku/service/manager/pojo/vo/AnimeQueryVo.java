package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/14 16:29
 * @Version: 1.0
 **/
@Data
public class AnimeQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String adminId;
    private String subjectId;
    private String subjectParentId;
}
