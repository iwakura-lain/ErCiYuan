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
     * 判断 token 是否过期以及其剩余存活时间
     * 如果剩余存活时间小于 5min，则颁发新的 jwt
     * 如果 jwt 正常过期(jwt过期，但是 redis 中的 jwt 没有过期)，则颁发新的 token
     * 否则视为异常过期，需要进行重新登录
     * @param request：请求上下文
     * @param redisUtil：redis工具类
     * @return ：jwt
     */
    public static String checkTokenExpireTimeAndGetNew(
            HttpServletRequest request,
            RedisUtil redisUtil){
        String token = request.getHeader("token");
        Jws<Claims> claimsJws = null;
        Claims claims;
        Member member;
        try {
            //得到 DefaultJwtParser
            long expireTime = Jwts.parser()
                    //设置签名的秘钥
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token)
                    .getBody().getExpiration().getTime();
            long diff = expireTime - System.currentTimeMillis();
            claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
            claims = claimsJws.getBody();
            member = new Member();
            member.setId((String) claims.get("id"));
            member.setAvatar((String) claims.get("avatar"));
            member.setNickname((String) claims.get("nickname"));
            //如果有效期小于5分钟，则不建议继续使用该 jwt，颁发新的 jwt
            if (diff < ADVANCE_EXPIRE_TIME && diff > 0) {
                String jwtInfo = (String)redisUtil.get((String) claims.get("id"));
                if(StringUtils.isEmpty(jwtInfo)){
                    return null;
                }
                return createJwtToken(member);
            }
        // jwt 过期异常
        } catch (ExpiredJwtException e) {
            // 如果是正常过期 ( jwt 过期了，但是 redis 中还有数据, 同样也生成一个新的 token 返回 )
            claims = e.getClaims();
            String jwtTokenInRedis = (String) redisUtil.get((String) claims.get("id"));
            if(!StringUtils.isEmpty(jwtTokenInRedis)){
                member = new Member();
                member.setId((String) claims.get("id"));
                member.setAvatar((String) claims.get("avatar"));
                member.setNickname((String) claims.get("nickname"));
                return createJwtToken(member);
            }
            return null;
        } catch (Exception e){
            return null;
        }
        return null;
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
