package cn.antigenmhc.otaku.service.ucenter.controller;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import cn.antigenmhc.otaku.service.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Resource
    private MemberService memberService;

}

