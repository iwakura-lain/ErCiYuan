package cn.antigenmhc.otaku.service.ucenter.service.impl;

import cn.antigenmhc.otaku.service.ucenter.service.Oauth2Service;
import cn.antigenmhc.otaku.service.ucenter.utils.Oauth2Utils;
import cn.antigenmhc.otaku.service.ucenter.properties.GiteeOauth2Properties;
import cn.antigenmhc.otaku.service.ucenter.service.GiteeLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/1 13:23
 * @Version: 1.0
 **/
@Service
@Slf4j
public class GiteeLoginServiceImpl implements GiteeLoginService {

    @Resource
    private GiteeOauth2Properties oauth2Properties;
    @Resource
    private Oauth2Service oauth2Service;

    @Override
    public String getAccessToken(String code) {
        String url = oauth2Properties.getAccessTokenUrl() +
                "?grant_type=authorization_code" +
                "&code=" + code +
                "&client_id=" + oauth2Properties.getClientId() +
                "&redirect_uri=" + oauth2Properties.getRedirectUrl() +
                "&client_secret=" + oauth2Properties.getClientSecret();
        String accessToken = Oauth2Utils.getAccessToken(url);
        return accessToken;
    }

    @Override
    public String getUserInfo(String accessToken) {
        String url = oauth2Properties.getUserInfoUrl();
        String userInfo = Oauth2Utils.getUserInfo(accessToken, url);
        return userInfo;
    }

    @Override
    public String getJwtToken(String userInfo) {
        String token = oauth2Service.getJwtTokenByOauth2UserInfo(userInfo, "gitee");
        return token;
    }

}
