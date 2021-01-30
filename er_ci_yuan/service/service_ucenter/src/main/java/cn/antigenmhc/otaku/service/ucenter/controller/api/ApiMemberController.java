package cn.antigenmhc.otaku.service.ucenter.controller.api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import cn.antigenmhc.otaku.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 22:28
 * @Version: 1.0
 **/
@Api(description = "前台会员展示")
@RestController
@RequestMapping("/api/site/member")
public class ApiMemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation("会员注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok().setMessage("注册成功");
    }

}
