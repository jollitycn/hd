package com.insigma.ordercenter.logistics.best.sdk;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sign {
    public static String makeSign(Param param){
        String signString = makeSignString(param);
        String sign = "";
        try {
            sign = digestEncrypte(signString.getBytes("utf-8"), "MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    public static String makeBase64Sign(Param param){
        String signString = makeSignString(param);
        String sign = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signString.getBytes("utf-8"));
            byte[] b = md.digest();
            sign = (new sun.misc.BASE64Encoder()).encode(b);
        }catch (Throwable e) {
            e.printStackTrace();
        }
        return sign;
    }

    private static String makeSignString(Param param) {
		String signString = param.getBizData() + param.getPartnerKey();        return signString;
    }

    public static String digestEncrypte(byte[] plainText, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(plainText);
        byte[] b = md.digest();
        StringBuilder output = new StringBuilder(32);
        for (int i = 0; i < b.length; i++) {
            String temp = Integer.toHexString(b[i] & 0xff);
            if (temp.length() < 2) {
                output.append("0");
            }
            output.append(temp);
        }
        return output.toString();
    }
}
