package cn.antigenmhc.otaku.service.ucenter.service.impl;


import cn.antigenmhc.otaku.service.ucenter.service.Oauth2Service;
import cn.antigenmhc.otaku.service.ucenter.utils.Oauth2Utils;
import cn.antigenmhc.otaku.service.ucenter.mapper.MemberMapper;
import cn.antigenmhc.otaku.service.ucenter.properties.GithubOauth2Properties;
import cn.antigenmhc.otaku.service.ucenter.service.GithubLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/31 19:52
 * @Version: 1.0
 **/
@Service
@Slf4j
public class GithubLoginServiceImpl implements GithubLoginService {

    @Resource
    private GithubOauth2Properties oauth2Properties;
    @Resource
    private Oauth2Service oauth2Service;

    @Override
    public String getAccessToken(String code) {
        String url = oauth2Properties.getAccessTokenUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&client_secret=" + oauth2Properties.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
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
    public String saveGithubIdAndUserInfo(String userInfo) {
        String token = oauth2Service.getJwtTokenByOauth2UserInfo(userInfo, "github");
        return token;
    }
}
