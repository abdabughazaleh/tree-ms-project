package com.treeproject.account.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class ASEEncrypt {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String secret = "klasjfhnsanfklsafin219812ylmn";


    private static void setKey() {
        MessageDigest sha = null;
        try {
            key = secret.getBytes("UTF-8");
            System.out.println(secret);
            System.out.println(key);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            log.error("cannot set the key {}", e.getMessage());
        }
    }

    public static String encrypt(final String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            log.error("Error while encrypting:  {}", e.getMessage());
        }
        return null;
    }

    public static String decrypt(final String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            log.error("Error while encrypting:  {}", e.toString());
        }
        return null;
    }
}
