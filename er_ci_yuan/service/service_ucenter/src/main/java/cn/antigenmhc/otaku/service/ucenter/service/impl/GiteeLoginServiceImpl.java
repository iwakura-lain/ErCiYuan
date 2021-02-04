package cn.antigenmhc.otaku.service.ucenter.service.impl;

import cn.antigenmhc.otaku.service.ucenter.mapper.MemberMapper;
import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import cn.antigenmhc.otaku.service.ucenter.service.Oauth2Service;
import cn.antigenmhc.otaku.service.ucenter.utils.Oauth2SendRequestUtil;
import cn.antigenmhc.otaku.service.ucenter.properties.GiteeOauth2Properties;
import cn.antigenmhc.otaku.service.ucenter.service.GiteeLoginService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Resource
    private MemberMapper memberMapper;

    @Override
    public String getAccessToken(String code) {
        String url = oauth2Properties.getAccessTokenUrl() +
                "?grant_type=authorization_code" +
                "&code=" + code +
                "&client_id=" + oauth2Properties.getClientId() +
                "&redirect_uri=" + oauth2Properties.getRedirectUrl() +
                "&client_secret=" + oauth2Properties.getClientSecret();
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
        String token = oauth2Service.getJwtTokenByOauth2UserInfo(userInfo, "gitee");
        //查询该gitee账号是否已经绑定了手机号
        JSONObject parse = JSONObject.parseObject(userInfo);
        String oauthId = parse.get("id") + "";
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gitee_id", oauthId);
        Member member = memberMapper.selectOne(queryWrapper);
        if(StringUtils.isEmpty(member.getMobile())){
            return member.getGiteeId();
        }
        return token;
    }
}
