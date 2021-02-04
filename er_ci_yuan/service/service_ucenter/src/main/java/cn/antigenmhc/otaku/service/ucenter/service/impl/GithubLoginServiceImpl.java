package cn.antigenmhc.otaku.service.ucenter.service.impl;


import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import cn.antigenmhc.otaku.service.ucenter.service.Oauth2Service;
import cn.antigenmhc.otaku.service.ucenter.utils.Oauth2SendRequestUtil;
import cn.antigenmhc.otaku.service.ucenter.mapper.MemberMapper;
import cn.antigenmhc.otaku.service.ucenter.properties.GithubOauth2Properties;
import cn.antigenmhc.otaku.service.ucenter.service.GithubLoginService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Resource
    private MemberMapper memberMapper;

    @Override
    public String getAccessToken(String code) {
        String url = oauth2Properties.getAccessTokenUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&client_secret=" + oauth2Properties.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
        String accessToken = Oauth2SendRequestUtil.getAccessToken(url);
        return accessToken;
    }

    @Override
    public String getUserInfo(String accessToken) {
        String url = oauth2Properties.getUserInfoUrl();
        String userInfo = Oauth2SendRequestUtil.getUserInfo(accessToken, url);
        return userInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getJwtTokenOrOauthId(String userInfo) {
        //生成 token 时会存入不带手机号等信息的会员信息
        String token = oauth2Service.getJwtTokenByOauth2UserInfo(userInfo, "github");
        //因此，查询该github账号是否已经绑定了数据，如果已经绑定，则直接返回 token
        //如果没有绑定，则返回github_id，然后跳转到绑定页面，进行数据绑定
        JSONObject parse = JSONObject.parseObject(userInfo);
        String oauthId = parse.get("id") + "";
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("github_id", oauthId);
        Member member = memberMapper.selectOne(queryWrapper);
        if(StringUtils.isEmpty(member.getMobile())){
            return member.getGithubId();
        }
        return token;
    }
}
