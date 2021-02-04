package cn.antigenmhc.otaku.service.ucenter.controller.api;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.ucenter.properties.GithubOauth2Properties;
import cn.antigenmhc.otaku.service.ucenter.service.GithubLoginService;
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
 * @Date: 2021/1/31 19:15
 * @Version: 1.0
 **/
@Slf4j
@Controller
@RequestMapping("api/github/login")
public class ApiGitHubController {
    @Resource
    private GithubOauth2Properties oauth2Properties;
    @Resource
    private GithubLoginService githubLoginService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private Oauth2Service oauth2Service;

    /**
     * 让用户跳转到 GitHub
     * 这里不能加@ResponseBody，因为这里是要跳转而不是返回响应
     * 另外LoginController也不能用@RestController修饰
     * @return 跳转url
     */
    @GetMapping("authorize")
    public String authorize(HttpSession httpSession) {
        String state = UUID.randomUUID().toString();
        //将 state 存在 redis 中
        String sessionId = httpSession.getId();
        redisUtil.set(sessionId, state, 600);
        redisUtil.set(state, sessionId, 600);
        String url = oauth2Properties.getAuthorizeUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&redirect_uri=" + oauth2Properties.getRedirectUrl() +
                "&state=" + state;
        return "redirect:" + url;
    }

    /**
     * 回调接口，用户同意授权后，GitHub会重定向到此路径
     * @param code GitHub重定向时附加的授权码，只能用一次
     * @return: 重定向到首页
     */
    @GetMapping("callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) {

        if(!oauth2Service.fetchCallBackState(code, state)){
            log.error("非法回调");
            throw new IntegrateException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }

        // code换token
        String accessToken = githubLoginService.getAccessToken(code);
        // token换userInfo
        String userInfo = githubLoginService.getUserInfo(accessToken);
        // 关联保存，并返回数据;
        // 如果该数据已经关联了手机号等信息，则携带token跳转到首页
        // 否则携带 oauth2 的 id 跳转到信息补全页
        String token = githubLoginService.getJwtTokenOrOauthId(userInfo);
        //如果数据已经绑定了，则跳转到首页
        if(token.contains(".")){
            return "redirect:http://localhost:3000?token=" + token;
        }
        return "redirect:http://localhost:3000/bind?type=github&id=" + token;
    }
}


