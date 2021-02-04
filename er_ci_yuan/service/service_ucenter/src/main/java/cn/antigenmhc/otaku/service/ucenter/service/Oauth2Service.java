package cn.antigenmhc.otaku.service.ucenter.service;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 0:11
 * @Version: 1.0
 **/
public interface Oauth2Service {
    String getJwtTokenByOauth2UserInfo(String userInfo, String oauthFrom);
    boolean fetchCallBackState(String code, String state);
}
