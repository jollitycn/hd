package com.insigma.ordercenter.service.sf.oms;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class sfmd5
{
  public static String md5(String data, String key)
  {
    String encryptdata;
    try
    {
      String str = data + key;
      encryptdata = new String(Base64.encodeBase64(DigestUtils.md5(str
        .getBytes("utf-8"))), "utf-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
      return "error";
    }
    return encryptdata;
  }
  public static String decodeUrl(String data) {
    String str = URLDecoder.decode(data);
    return str;
  }
}