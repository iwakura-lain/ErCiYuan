package cn.antigenmhc.otaku.service.ucenter.service;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/1 13:23
 * @Version: 1.0
 **/
public interface GiteeLoginService {
    String getAccessToken(String code);

    String getUserInfo(String accessToken);

    String getJwtTokenOrOauthId(String userInfo);
}
