package com.cheny.springboot.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5(String pass){
        try {
            MessageDigest digest=MessageDigest.getInstance("md5");
            byte[] result=digest.digest(pass.getBytes());
            StringBuffer buffer=new StringBuffer();
            for(byte b:result){
                int number=b & 0xff;
                String str=Integer.toHexString(number);
                if(str.length()==1){
                    buffer.append("0");
                }
                buffer.append(str);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
    
}
