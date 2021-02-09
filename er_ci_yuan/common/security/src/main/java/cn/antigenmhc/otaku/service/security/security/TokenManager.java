package cn.antigenmhc.otaku.service.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * token管理
 * </p>
 *
 * @author antigenMHC
 * @date 2021/2/08 10:20
 * @version 1.0
 **/
@Component
public class TokenManager {

    private static final long TOKEN_EXPIRE = 24*60*60*1000;
    private static final String SIGN_KEY = "123456";

    public String createToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE))
                .signWith(SignatureAlgorithm.HS512, SIGN_KEY).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    public String getUserFromToken(String token) {
        String user = Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

    public void removeToken(String token) {
        //jwt token无需删除，客户端扔掉即可。
    }

}
