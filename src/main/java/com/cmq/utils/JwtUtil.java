package com.cmq.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cmq.po.DoctorPO;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * token私钥：随机uuid
     */
    private static final String TOKEN_SECRET = "aff7828839004351b8e9cb17a2ce204c";

    /**
     * 生成token
     * 过期时间：不过期
     */
    public static String sign(DoctorPO doctor){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //header information
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("id", doctor.getId())
                    .withClaim("mobile", doctor.getMobile())
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

            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 根据key获得token中对应的claim对象
     */
    public static Claim parseClaim(String token, String key){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(key)){
            return null;
        }

        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim(key);
        }catch (Exception e){
            return null;
        }

    }

    public static Integer parseInteger(String token, String key){
        Claim claim = parseClaim(token, key);
        if(null == claim){
            return null;
        }
        return claim.asInt();
    }

    public static String parseString(String token, String key){
        Claim claim = parseClaim(token, key);
        if(null == claim){
            return null;
        }
        return claim.asString();
    }
}
