package cn.antigenmhc.otaku.service.ucenter.service;

import cn.antigenmhc.otaku.common.base.utils.JwtInfo;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/31 19:48
 * @Version: 1.0
 **/
public interface GithubLoginService {
    /**
     * 通过 github 那边给予的 code 获取 accessToken
     * @param code：code，一次请求只能用一次
     * @return ：accessToken
     */
    String getAccessToken(String code);

    /**
     * 通过获取到的 accessToken 换取用户信息
     * @param accessToken：通过 code 换取到的 accessToken
     * @return ：用户信息
     */
    String getUserInfo(String accessToken);

    /**
     * 尝试获取与 github 回调 id 关联的用户，如不存在则存入数据库后返回token到首页
     * 否则通过查询出的用户拼接 jwtInfo 后生成 token 返回
     * @param userInfo：github 那边返回的用户信息，根据 accessToken获取到的
     * @return ：jwt_token
     */
    String saveGithubIdAndUserInfo(String userInfo);
}
