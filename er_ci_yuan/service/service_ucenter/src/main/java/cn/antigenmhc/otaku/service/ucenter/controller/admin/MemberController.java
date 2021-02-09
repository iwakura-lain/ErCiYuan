package cn.antigenmhc.otaku.service.ucenter.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import cn.antigenmhc.otaku.service.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/ucenter/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation("注册用户数据统计/d")
    @GetMapping("register-count/{date}")
    public Result getRegisterCount(@PathVariable("date") String date){
        int count = memberService.registerCount(date);
        return Result.ok().setData("registerCount", count);
    }
}

