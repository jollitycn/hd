package sf.oms;

import com.insigma.ordercenter.service.sf.oms.OMSServiceCode;

public class Main {
	public static final String UTF_8 = "UTF-8";
	
	private static  final String AES256KEY="alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";
    private static  final String HMACSHA512="W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";
	public static void main(String[] args) throws Exception{
	    Demo.demo("test", OMSServiceCode.TRANSPORT);
//
//
//        String str = "!@#$%^&*(测试内容";
//
//        //STEP1. 客户系统将业务报文进行 AES256CBC 加密，生成 MsgData (AESCipher线程安全问题，每次创建一个)
//        AESCipher cipher = new AESCipher(AES256KEY.getBytes(UTF_8));
//        String msgData = cipher.getEncryptedMessage(str);
//
//        //STEP2. 将第一步的加密后的数据 再次进行非对称加密
//        String dataDigest = HmacSha512CoderFactory.getHmacSha512Coder(HMACSHA512, msgData);
//        System.out.println(msgData);
//        System.out.println(dataDigest);
//
//        //STEP3. 将 MsgData 和 DataDigest 进行 Urlencode 转码
//        String msgDataToUrlEncode = URLEncoder.encode(msgData, UTF_8);
//        System.out.println(msgDataToUrlEncode);
//        String dataDigestToUrlEncode = URLEncoder.encode(dataDigest, UTF_8);
//        System.out.println(dataDigestToUrlEncode);
//
//
//        //STEP4. 将 MsgData 和 DataDigest 进行 Urlencode 解码
//        String msgDataToUrlDecode = URLDecoder.decode(msgDataToUrlEncode,UTF_8);
//        System.out.println(msgDataToUrlDecode);
//        String dataDigestToUrlDecode = URLDecoder.decode(dataDigestToUrlEncode,UTF_8);
//        System.out.println(dataDigestToUrlDecode);
//
//        //STEP5. 将解码的 MsgData 进行 HMAC-SHA512加密， 与 解码后的 DataDigest 比较内容是否一致
//        if (HmacSha512CoderFactory.getHmacSha512Coder(HMACSHA512, msgDataToUrlDecode).equals(dataDigestToUrlDecode)) {
//        	System.out.println("SUCCESS");
//
//            //STEP6. 完整性一致后，进行 AES256CBC 解密
//            String decodeMsgData = cipher.getDecryptedMessage(msgDataToUrlDecode);
//            System.out.println(decodeMsgData);
//        } else {
//        	System.out.println("Error");
//        }
        
	} 
	
	
	/**
     * 生成32位编码
     *
     * @return string
     */
//    private static String getUUID() {
//        return UUID.randomUUID().toString().trim().replaceAll("-", "");
//    }

}
