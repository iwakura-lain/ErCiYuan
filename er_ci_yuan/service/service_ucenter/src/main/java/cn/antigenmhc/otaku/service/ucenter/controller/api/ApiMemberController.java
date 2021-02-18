package cn.antigenmhc.otaku.service.ucenter.controller.api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.common.base.utils.JwtUtil;
import cn.antigenmhc.otaku.service.base.dto.MemberDto;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.LoginVo;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.UpdateVo;
import cn.antigenmhc.otaku.service.ucenter.service.MemberService;
import cn.antigenmhc.otaku.service.ucenter.utils.JwtUtilEx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 22:28
 * @Version: 1.0
 **/
@Api(description = "前台会员展示")
@RestController
@RequestMapping("/api/site/member")
@Slf4j
public class ApiMemberController {

    @Resource
    private MemberService memberService;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("会员注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok().setMessage("注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return Result.ok().setData("token", token);
    }

    @ApiOperation("解析token获取用户信息")
    @GetMapping("get-login-info")
    public Result getLoginInfo(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        try {
            //检查是否是濒死 jwt (剩余存活时间小于5min)，如果是的话，则生成新的 jwt
            //同时，如果 jwt 是正常过期 (jwt 过期，redis 中还有，则也生成一个新的 jwt)
            String token = JwtUtil.checkTokenExpireTimeAndGetNew(jwtToken, redisUtil);
            //如果 jwt 不为 null，解析 jwt 中的用户信息
            if(!StringUtils.isEmpty(token)){
                JwtInfo userInfo = JwtUtilEx.getMemberByJwtToken(token);
                return Result.ok().setData("token", token).setData("userInfo", userInfo);
            }
            // 如果 jwt 为 null，返回 null 即可
            JwtInfo userInfo = JwtUtilEx.getMemberByJwtToken(token);
            return Result.ok().setData("userInfo", userInfo);
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @ApiOperation("更新用户信息")
    @PostMapping("auth/update-member")
    public Result updateMember(@RequestBody UpdateVo updateVo){
        String jwt = memberService.updateMemberInfo(updateVo);
        return Result.ok().setData("token", jwt);
    }

    @ApiOperation("会员注册 by-Oauth2")
    @PostMapping("register/{id}/{type}")
    public Result registerByOauth2(@RequestBody RegisterVo registerVo,
                                   @PathVariable("id") String oauthId,
                                   @PathVariable("type") String type){
        memberService.registerByOauth2(registerVo, oauthId, type);
        return Result.ok().setMessage("注册成功");
    }

    @ApiOperation("根据 id 获取会员信息，组装 MemberDto")
    @GetMapping("inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(@PathVariable("memberId") String memberId){
        MemberDto memberDto = memberService.getMemberDtoByMemberId(memberId);
        return memberDto;
    }
}
