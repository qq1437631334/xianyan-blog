package com.wsy.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: wsy
 */
public class Md5Utils {

    /**
     * 生成盐
     */
    public static String createSalt(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 生成加密字符串
     */
    public static String md5(String source,String salt,Integer hashIterations){
        return new Md5Hash(source,salt,hashIterations).toString();
    }
}
