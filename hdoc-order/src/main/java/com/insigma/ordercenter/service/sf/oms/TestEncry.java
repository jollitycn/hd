package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.logistics.sf.oms.AESCipher;
import com.insigma.ordercenter.logistics.sf.oms.HmacSha512CoderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEncry
{
  private static final Logger logger = LoggerFactory.getLogger(TestEncry.class);

  public static void main(String[] args) throws UnsupportedEncodingException {
    String aes256Key = getUUID();
    String hmacsha512 = getUUID();

    String str = null;
    try {
      str = new String(getFileContent("E:\\temp\\入库3000明细报文.txt"), "utf-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }

    String msgData = AES256CipherExternalFactory.AES256Encode(str, aes256Key);
    logger.info("msgData:{}", msgData);
    System.out.println(msgData);

    AESCipher cipher = new AESCipher(aes256Key.getBytes("UTF-8"));
    String msgData1 = cipher.getEncryptedMessage(str);
    System.out.println(msgData1);

    System.out.println(cipher.getDecryptedMessage(msgData));
    System.err.println(AES256CipherExternalFactory.AES256Decode(msgData1, aes256Key));

    String dataDigest = HmacSha512CoderFactory.getHmacSha512Coder(hmacsha512, msgData);
    logger.info("dataDigest:{}", dataDigest);

    String msgDataToUrlEncode = URLEncoder.encode(msgData, "UTF-8");
    logger.info("msgDataToUrlEncode:{}", msgDataToUrlEncode);
    String dataDigestToUrlEncode = URLEncoder.encode(dataDigest, "UTF-8");
    logger.info("dataDigestToUrlEncode:{}", dataDigestToUrlEncode);

    String msgDataToUrlDecode = sfmd5.decodeUrl(msgDataToUrlEncode);
    logger.info("msgDataToUrlDecode:{}", msgDataToUrlDecode);
    String dataDigestToUrlDecode = sfmd5.decodeUrl(dataDigestToUrlEncode);
    logger.info("dataDigestToUrlDecode:{}", dataDigestToUrlDecode);

    if (HmacSha512CoderFactory.getHmacSha512Coder(hmacsha512, msgDataToUrlDecode).equals(dataDigestToUrlDecode)) {
      logger.info("SUCCESS");

      String decodeMsgData = AES256CipherExternalFactory.AES256Decode(msgDataToUrlDecode, aes256Key);
      logger.info("decodeMsgData:{}", decodeMsgData);
    } else {
      logger.info("Error");
    }
  }

  private static String getUUID()
  {
    return UUID.randomUUID().toString().trim().replaceAll("-", "");
  }

  private static byte[] getFileContent(String filePath)
  {
    File file = new File(filePath);
    byte[] c = null;
    try { FileInputStream fis = new FileInputStream(file); Throwable localThrowable3 = null;
      try { long length = file.length();
        c = new byte[(int)length];
        fis.read(c, 0, c.length);
      }
      catch (Throwable localThrowable1)
      {
        localThrowable3 = localThrowable1; throw localThrowable1;
      }
      finally
      {
        if (fis != null) if (localThrowable3 != null) try { fis.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else fis.close();  
      }
    } catch (Exception localException)
    {
    }
    return c;
  }
}