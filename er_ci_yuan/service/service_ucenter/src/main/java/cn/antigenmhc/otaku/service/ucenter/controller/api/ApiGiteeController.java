package cn.antigenmhc.otaku.service.ucenter.controller.api;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.ucenter.properties.GiteeOauth2Properties;
import cn.antigenmhc.otaku.service.ucenter.service.GiteeLoginService;
import cn.antigenmhc.otaku.service.ucenter.service.Oauth2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/1 13:12
 * @Version: 1.0
 **/
@Slf4j
@Controller
@RequestMapping("api/gitee/login")
public class ApiGiteeController {

    @Resource
    private GiteeLoginService giteeLoginService;
    @Resource
    private GiteeOauth2Properties oauth2Properties;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private Oauth2Service oauth2Service;


    @GetMapping("authorize")
    public String authorize(HttpSession httpSession) {
        String state = UUID.randomUUID().toString();
        //将 state 存在 redis 中
        String sessionId = httpSession.getId();
        //双向关联设置，用于在回调后比较 state
        redisUtil.set(sessionId, state, 600);
        redisUtil.set(state, sessionId, 600);
        String url = oauth2Properties.getAuthorizeUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&redirect_uri=" + oauth2Properties.getRedirectUrl() +
                "&response_type=code" +
                "&state=" + state;
        return "redirect:" + url;
    }

    @GetMapping("callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) {
        if(!oauth2Service.fetchCallBackState(code, state)){
            log.error("非法回调");
            throw new IntegrateException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }

        String accessToken = giteeLoginService.getAccessToken(code);
        String userInfo = giteeLoginService.getUserInfo(accessToken);
        String token = giteeLoginService.getJwtTokenOrOauthId(userInfo);
        if(token.contains(".")){
            return "redirect:http://localhost:3000?token=" + token;
        }
        return "redirect:http://localhost:3000/bind?type=gitee&id=" + token;
    }
}
