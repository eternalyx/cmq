package com.tcm.questionnaire.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "aff7828839004351b8e9cb17a2ce204c";

    /**
     * 生成token
     * 过期时间：不过期
     */
    public static String sign(Integer userId){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //header information
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .sign(algorithm);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 解析token
     */
    public static Integer parse(String token, String key){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(key)){
            return null;
        }

        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = verifier.verify(token);
            Claim claim = decodedJWT.getClaim(key);

            if(null == claim){
                return null;
            }
            return claim.asInt();
        }catch (Exception e){
            return null;
        }

    }


}
