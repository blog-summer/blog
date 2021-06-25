package com.best.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    //md5加密
    public static String code(String str){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] byteDigest=md.digest();
            int i;
            StringBuffer buf =new StringBuffer("");
            for (int offset=0;offset<byteDigest.length;offset++){
                i=byteDigest[offset];
                if(i<0)
                    i+=256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public  static void main(String[] args){
        System.out.println(code("111111"));
    }
}
