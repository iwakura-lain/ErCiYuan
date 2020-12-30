package cn.antigenmhc.otaku.service.manager.controller;


import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 动漫 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@RestController
@RequestMapping("/manager/anime")
public class AnimeController {

    @Resource
    private AnimeService animeService;

    @GetMapping("allanime")
    public List<Anime> getAll(){
        return animeService.list();
    }
}

