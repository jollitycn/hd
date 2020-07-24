//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.logistics.sf.oms;

import com.google.common.base.Throwables;
import com.google.common.io.BaseEncoding;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCipher {
    private static final String ALGORITHM_AES256 = "AES/CBC/PKCS5Padding";
    private static final byte[] INITIAL_IV = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final SecretKeySpec secretKeySpec;
    private final Cipher cipher;
    private IvParameterSpec iv;

    public AESCipher(Key key) {
        this(key.getEncoded());
    }

    public AESCipher(Key key, byte[] iv) {
        this(key.getEncoded(), iv);
    }

    public AESCipher(byte[] key) {
        this(key, INITIAL_IV);
    }

    private AESCipher(byte[] key, byte[] iv) {
        try {
            this.secretKeySpec = new SecretKeySpec(key, "AES");
            this.iv = new IvParameterSpec(iv);
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException var4) {
            throw Throwables.propagate(var4);
        }
    }

    public String getEncryptedMessage(String message) {
        try {
            Cipher cipher = this.getCipher(1);
            byte[] encryptedTextBytes = cipher.doFinal(message.getBytes("UTF-8"));
            return BaseEncoding.base64().encode(encryptedTextBytes);
        } catch (BadPaddingException | UnsupportedEncodingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException var4) {
            throw Throwables.propagate(var4);
        }
    }

    public String getDecryptedMessage(String message) {
        try {
            Cipher cipher = this.getCipher(2);
            byte[] encryptedTextBytes = BaseEncoding.base64().decode(message);
            byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
            return new String(decryptedTextBytes);
        } catch (BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException var5) {
            throw Throwables.propagate(var5);
        }
    }

    public String getIV() {
        return BaseEncoding.base64().encode(this.iv.getIV());
    }

    public String getKey() {
        return this.getKey(KeyEncoding.BASE64);
    }

    public String getKey(KeyEncoding encoding) {
        String result = null;
        switch(encoding) {
        case BASE64:
            result = BaseEncoding.base64().encode(this.secretKeySpec.getEncoded());
            break;
        case HEX:
            result = BaseEncoding.base16().encode(this.secretKeySpec.getEncoded());
            break;
        case BASE32:
            result = BaseEncoding.base32().encode(this.secretKeySpec.getEncoded());
        }

        return result;
    }

    private Cipher getCipher(int encryptMode) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.cipher.init(encryptMode, this.getSecretKeySpec(), this.iv);
        return this.cipher;
    }

    private SecretKeySpec getSecretKeySpec() {
        return this.secretKeySpec;
    }
}
