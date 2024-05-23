package com.bob.ktssts.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    /* 过期时间 */
    @Value("${ktsssys.api.jwtExpirationMs}")
    private long jwtExpirationMs;

    /* 密钥 */
    @Value("${ktsssys.api.jwtSecret}")
    private String jwtSecret;

    public static String createJWT(String user, String secret){

        return "";
    }


}
