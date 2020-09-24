package com.github.cccy0.mall.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhai
 * 2020/9/24 22:28
 */
@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${settings.jwt.secret}")
    private String secret;

    @Value("${settings.jwt.expiration}")
    private Long expiration;

    @Value("${settings.jwt.tokenHead}")
    private String tokenHead;

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     *
     * @param token jwt
     * @return true:失效(token date在当前时间之前)
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> claims = MapUtil.newHashMap(2);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshHeadToken(String oldToken) {
        if (StrUtil.isBlank(oldToken)) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (StrUtil.isBlank(token)) {
            return null;
        }
        // 解析失败
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        // 已过期
        if (isTokenExpired(token)) {
            return null;
        }
        // 30min之内刷新过
        if (tokenRefreshJustBefore(token, 30 * 60)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    private boolean tokenRefreshJustBefore(String token, Integer time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date now = new Date();
        // 当前时间 在 token创建时间 之后 && 当前时间 在 token创建时间+30min 之前
        return now.after(created) && now.before(DateUtil.offsetSecond(created, time));
    }
}
