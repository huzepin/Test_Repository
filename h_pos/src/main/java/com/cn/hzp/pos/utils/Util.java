package com.cn.hzp.pos.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Util {

    public static String createToken(String phone){
        if (phone == null) return null;
        String token = (System.currentTimeMillis()+"-" + new Random().nextInt(999999999)) + phone;
        try {
          //  MessageDigest md = MessageDigest.getInstance("md5");
           // byte md5[] =  md.digest(token.getBytes());
         //   BASE64Encoder encoder = new BASE64Encoder();
            //encoder.encode(token.getBytes());
            final Base64.Encoder encoder = Base64.getEncoder();
            final byte[] textByte = token.getBytes("UTF-8");
            final String encodedText = encoder.encodeToString(textByte);
            return encodedText;



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String decoderToken(String data) throws IOException {
        if (data == null) return null;
        // BASE64解密
     //   BASE64Decoder decoder = new BASE64Decoder();
       // byte[] bytes = decoder.decodeBuffer(data);
      // return new String(bytes)
        final Base64.Decoder decoder = Base64.getDecoder();
       return  new String(decoder.decode(data), "UTF-8");
    }





}
