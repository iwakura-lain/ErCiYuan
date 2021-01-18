package cn.antigenmhc.otaku.service.manager.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/17 15:23
 * @Version: 1.0
 **/
@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> childrenVideo = new ArrayList<>();
}
