package cn.antigenmhc.otaku.service.ucenter.utils;

import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.common.base.utils.JwtUtil;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/30 12:28
 * @Version: 1.0
 **/
public class JwtUtilEx extends JwtUtil {

    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
    private static final long ADVANCE_EXPIRE_TIME = 300000;
    /**
     * 获得 key
     * @return ：加密后的 key
     */
    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(APP_SECRET);
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    /**
     * 根据 member 对象生成 JwtInfo，并将其放入荷载中，设置过期时间
     * @param member：member 对象
     * @return jwt
     */
    public static String createJwtToken(Member member){
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setAvatar(member.getAvatar());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setId(member.getId());
        return JwtUtil.getJwtToken(jwtInfo, 18000);
    }
}
