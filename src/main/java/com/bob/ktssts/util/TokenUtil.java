package com.bob.ktssts.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.el.parser.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TokenUtil {

	// Issuer 所有者
	public static final String ISSUER = "kingsware.cn";
	// Audience
	public static final String AUDIENCE = "Client";
	// Expire unit second
	public static final Integer EXPIRES = 2 * 60 * 60;
	// 密钥
	public static final String KEY = "bob19kdks2014+29";
	// 算法
	public static final Algorithm ALGORITHM = Algorithm.HMAC256(TokenUtil.KEY);
	// Header
	public static final Map<String, Object> HEADER_MAP = new HashMap<>() {
		{
			put("alg", "HS256");
			put("typ", "JWT");
		}
	};
	private static final Logger LOGGER = LogManager.getLogger(TokenUtil.class);

	/**
	 *
	 * @param userId 数据库中的UserID
	 * @return 验证成功则返回ApiUser对象，否则返回null
	 */
	public static String GenerateToken(String userId) {
		Map<String, String> claims = new HashMap<String, String>();
		if (userId != null && !userId.isEmpty()) {
			claims.put("userId", userId);
			TokenUtil.GenerateToken(claims);
			return TokenUtil.GenerateToken(claims);
		}
		return null;
	}

	public static String getUserId(String token) {
		token = token.replaceFirst("Bearer ", "");
//		LOGGER.info("正在解吗token : {}", token);
		JWTVerifier verifier = JWT.require(TokenUtil.ALGORITHM).withIssuer(TokenUtil.ISSUER).withAudience(TokenUtil.AUDIENCE).build();
		DecodedJWT decodedJWT;
		try {
			decodedJWT = verifier.verify(token);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

		String subject = decodedJWT.getSubject();
		Date issuedAt = decodedJWT.getIssuedAt();
		Date expiresAt = decodedJWT.getExpiresAt();

		return decodedJWT.getClaim("userId").asString();
	}

	/**
	 * 生成 Token 字符串
	 *
	 * @param claimMap claim 数据
	 * @return Token 字符串
	 */
	public static String GenerateToken(Map<String, String> claimMap) {
		Date nowDate = new Date();
		// 设置过期时间
		Date expireDate = TokenUtil.AddDate(nowDate);

		// Token 建造器
		JWTCreator.Builder tokenBuilder = JWT.create();

		for (Map.Entry<String, String> entry : claimMap.entrySet()) {
			// Payload 部分，根据需求添加
			tokenBuilder.withClaim(entry.getKey(), entry.getValue());
		}

		//token 字符串
		String token = tokenBuilder.withHeader(TokenUtil.HEADER_MAP)//Header 部分
				.withIssuer(TokenUtil.ISSUER)//issuer
				.withAudience(TokenUtil.AUDIENCE)//audience
				.withIssuedAt(nowDate)//生效时间
				.withExpiresAt(expireDate)//过期时间
				.sign(TokenUtil.ALGORITHM);//签名，算法加密

		return token;
	}

	/**
	 * 时间加法
	 *
	 * @param date 当前时间
	 * @return 时间加法结果
	 */
	private static Date AddDate(Date date) {
		if (null == date) {
			date = new Date();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, TokenUtil.EXPIRES);

		return calendar.getTime();
	}

	/**
	 * 验证 Token
	 *
	 * @param webToken 前端传递的 Token 字符串
	 * @return Token 字符串是否正确
	 * @throws Exception 异常信息
	 */
	public static boolean VerifyJWTToken(String webToken) {
		if(webToken == null)
			return false;
		if(webToken.startsWith("Bearer "))
			webToken = webToken.replaceFirst("Bearer ", "");

		JWTVerifier verifier = JWT.require(TokenUtil.ALGORITHM).withIssuer(TokenUtil.ISSUER).build();
		try {
			DecodedJWT jwt = verifier.verify(webToken);
			return true;
		} catch (AlgorithmMismatchException exception) {
			LOGGER.error("Invalid JWT algorithm : {}", exception.getMessage());
		} catch (SignatureVerificationException exception) {
			LOGGER.error("Invalid JWT signature : {}", exception.getMessage());
		} catch (TokenExpiredException exception) {
			LOGGER.error("Token has expired : {}", exception.getMessage());
		}
		return false;
	}

}
