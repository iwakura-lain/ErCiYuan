package cn.antigenmhc.otaku.service.manager.pojo.vo;

import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/24 21:34
 * @Version: 1.0
 **/
@Data
public class SiteAdminInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Admin admin;
    private List<Anime> animeList = new ArrayList<>();
}
