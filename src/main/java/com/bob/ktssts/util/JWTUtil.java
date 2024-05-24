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
	private static final long jwtExpirationMs = 300000;

	/* 密钥 */
	@Value("${ktsssys.api.jwtSecret}")
	private String jwtSecret;

	/**
	 * 验证token账号密码与数据源是否匹配
	 * @param token token
	 * @param user 账号
	 * @param secret 密码
	 * @return 验证是否成功
	 */
	public static boolean verifyToken(String token, String user, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SM4Util.encryptData_CBC(secret));
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("user", user)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 通过账号密码生成token
	 * -该方法创建的JWT不带角色或权限
	 *
	 * @param user   账号
	 * @param secret 密码
	 * @return 生成的token
	 */
	public static String createJWT(String user, String secret) {
		try {
			secret = SM4Util.encryptData_CBC(secret);
			Algorithm algorithm = null;
			if (secret != null) {
				algorithm = Algorithm.HMAC256(secret);
			} else {
				logger.error("SM4Util encryptData_CBC fail , secret is null !");
			}
			return JWT.create().withClaim("user", user).withIssuer("Ktssts").withIssuedAt(new Date()).withExpiresAt(new Date(new Date().getTime() + jwtExpirationMs)).sign(algorithm);
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 * 附加角色属性
	 * -该方法创建的JWT不带权限
	 *
	 * @param user   账号
	 * @param secret 密码
	 * @param roles  角色
	 * @return 生成的token
	 */
	public static String createJWT(String user, String secret, String roles) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withClaim("user", user).withClaim("role", roles).withIssuer("Ktssts").withIssuedAt(new Date()).withExpiresAt(new Date(new Date().getTime() + jwtExpirationMs)).sign(algorithm);
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 * 附加角色与权限属性
	 *
	 * @param user       账号
	 * @param secret     密码
	 * @param roles      角色
	 * @param permission 权限
	 * @return 生成的token
	 */
	public static String createJWT(String user, String secret, String roles, String permission) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withClaim("user", user).withIssuer("Ktssts").withIssuedAt(new Date()).withExpiresAt(new Date(new Date().getTime() + jwtExpirationMs)).withClaim("role", roles).withClaim("permission", permission).sign(algorithm);
		} catch (Exception exception) {
			return null;
		}
	}

	public static String getUserName(String token) {
		DecodedJWT decodedJWT = JWT.decode(token);
		try {
			return decodedJWT.getClaim("user").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoidGVzdCIsImlzcyI6Ikt0c3N0cyIsImlhdCI6MTcxNjU0MTg2NywiZXhwIjoxNzE2NTQyMTY3fQ.XzFyukAyhAGvJxhXxC3j2VGtWlN3fVWuszI1phK0G7U";

		// 使用auth0.jwt.JWT解码JWT token
		DecodedJWT decodedJWT = JWT.decode(jwtToken);

		// 获取头部信息
		String algorithm = decodedJWT.getAlgorithm();
		System.out.println("Algorithm: " + algorithm);

		// 获取载荷信息
		String issuer = decodedJWT.getIssuer();
		System.out.println("Issuer: " + issuer);
		String subject = decodedJWT.getSubject();
		System.out.println("Subject: " + subject);
		// ... 其他你需要的载荷信息

		// 获取签名
		String signature = decodedJWT.getSignature();
		System.out.println("Signature: " + signature);
	}

}
