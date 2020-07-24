//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.logistics.sf.oms;

import java.nio.charset.Charset;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class HmacSha512Coder {
    public static final String HMAC_SHA_512 = "HmacSHA512";
    private byte[] keySeeds;
    private final Charset charset = Charset.forName("UTF-8");

    public HmacSha512Coder() {
    }

    public void setKeySeeds(String keeySeedsString) {
        this.keySeeds = (new Base64Codec()).encrypt(keeySeedsString);
    }

    public String generateHMAC(String datas) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(this.keySeeds, "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);
            byte[] macData = mac.doFinal(datas.getBytes(this.charset));
            byte[] hashed = (new Hex()).encode(macData);
            return new String(hashed, this.charset);
        } catch (Exception var6) {
            throw new EncryptionRuntimeException(var6);
        }
    }
}
