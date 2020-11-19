package com.wsy.blog.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @version 1.0
 * @author 杨德石
 * @date 2019/3/31 0031 下午 1:49
 */
public class Md5Utils {
    public static String toMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密失败！");
        }
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.append(0);
        }
        return md5code.toString();
    }

    public static void main(String[] args) {
        System.out.println(Md5Utils.toMD5("wsy112233"));
    }
}
