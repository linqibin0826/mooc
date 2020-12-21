package com.linqibin.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 令牌
 * @author linqibin&youmei
 * @creed: ProjectForSDDM
 * @date 2020/10/29 16:16
 */
public class JwtUtils {
    /**
     * Session 过期时间
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    /**
     * 防伪标识, 哈希标签密钥.
     */
    public static final String APP_SECRET = "gsaidofjewbntFSD53FSVCFDSCfdse3";

    public static String getJwtToken(String id, String nickname){

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("PTU-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在
     * @param jwtToken
     * @return boolean
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/29 16:18
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在
     * @param request
     * @return boolean
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/29 16:19
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取Token中的用户ID
     * @param request
     * @return java.lang.String
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/29 16:19
     */
    public static String getIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}