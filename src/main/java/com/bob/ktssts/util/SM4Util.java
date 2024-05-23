package com.bob.ktssts.util;

/*
  Created by $(USER) on $(DATE)
 */

import com.bob.ktssts.encryption.EncryptionUtil;
import com.bob.ktssts.encryption.sm4.SM4;
import com.bob.ktssts.encryption.sm4.SM4_Context;
import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SM4Util {
//	private String secretKey = "";
//    private String iv = "";
//    private boolean hexString = false;

    private static final String secretKey = "KTABOBLZS20192NBCH2016T20240517E";
    private static final String iv = "21029471021091031234871012942814";
    public static boolean hexString = true;

    public SM4Util() {
    }


    public static String encryptData_ECB(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
			keyBytes = EncryptionUtil.hexStringToBytes(secretKey);

			SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(StandardCharsets.UTF_8));
            return EncryptionUtil.byteToHex(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptData_ECB(String cipherText) {
        try {
            byte[] encrypted = EncryptionUtil.hexToByte(cipherText);
            cipherText=Base64.encodeBase64String(encrypted);
            //cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && !cipherText.trim().isEmpty()) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }

            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = EncryptionUtil.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.decodeBase64(cipherText));
            //byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptData_CBC(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = EncryptionUtil.hexStringToBytes(secretKey);
                ivBytes = EncryptionUtil.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes(StandardCharsets.UTF_8));
            return EncryptionUtil.byteToHex(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptData_CBC(String cipherText) {
        try {
            byte[] encrypted = EncryptionUtil.hexToByte(cipherText);
            cipherText=Base64.encodeBase64String(encrypted);
            //cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && !cipherText.trim().isEmpty()) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = EncryptionUtil.hexStringToBytes(secretKey);
                ivBytes = EncryptionUtil.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            //byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.decodeBase64(cipherText));
            /*String text = new String(decrypted, "UTF-8");
            return text.substring(0,text.length()-1);*/
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        String plainText = "test";
        String string = SM4Util.encryptData_CBC(plainText);
        System.out.println("CBC加密后字符：" + string);
        string = SM4Util.decryptData_CBC(string);
        System.out.println("CBC解密后字符：" + string);




    }
}
