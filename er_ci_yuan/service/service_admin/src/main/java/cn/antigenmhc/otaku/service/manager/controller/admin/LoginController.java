package cn.antigenmhc.otaku.service.manager.controller.admin;

import cn.antigenmhc.otaku.common.base.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: antigenMHC
 * @Date: 2020/12/30 22:11
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public Result logIn(){
        return Result.ok().setData("token", "admin");
    }

    /**
     * 通过 token 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("info")
    public Result info(String token){
        return Result.ok().
                setData("name", "zzz").
                setData("roles", "[admin]").
                setData("avater", "https://q1.qlogo.cn/g?b=qq&nk=908204532&s=640")
                ;
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}
