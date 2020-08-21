/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.Mac;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import javax.crypto.spec.PBEKeySpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public class AESUtilsJCA
/*     */ {
/*     */   public static final int ITERATION_COUNT = 1000;
/*     */   public static final int BLOCK_SIZE = 16;
/*     */   private final Cipher cipher;
/*     */   private final Mac mac;
/*     */   private final byte[] passwordVerifier;
/*  46 */   private final byte[] iv = new byte[16];
/*  47 */   private final byte[] keystream = new byte[16];
/*  48 */   private int next = 16;
/*     */ 
/*     */   public AESUtilsJCA(String password, int keySize, byte[] salt) {
/*  51 */     if ((keySize != 128) && (keySize != 192) && (keySize != 256))
/*  52 */       throw new IllegalArgumentException("Illegal keysize: " + keySize);
/*     */     try
/*     */     {
/*  55 */       SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
/*  56 */       char[] passwordChars = password.toCharArray();
/*  57 */       PBEKeySpec keySpec = new PBEKeySpec(passwordChars, salt, 1000, keySize * 2 + 16);
/*  58 */       SecretKey sk = skf.generateSecret(keySpec);
/*  59 */       byte[] keyBytes = sk.getEncoded();
/*     */ 
/*  61 */       this.cipher = Cipher.getInstance("AES");
/*  62 */       SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, 0, keySize / 8, "AES");
/*     */ 
/*  67 */       this.cipher.init(1, secretKeySpec);
/*     */ 
/*  69 */       this.mac = Mac.getInstance("HmacSHA1");
/*  70 */       this.mac.init(new SecretKeySpec(keyBytes, keySize / 8, keySize / 8, "HmacSHA1"));
/*     */ 
/*  72 */       this.passwordVerifier = new byte[2];
/*  73 */       System.arraycopy(keyBytes, 2 * (keySize / 8), this.passwordVerifier, 0, 2);
/*     */     }
/*     */     catch (NoSuchAlgorithmException e)
/*     */     {
/*  79 */       throw new Error();
/*     */     } catch (InvalidKeyException e) {
/*  81 */       e.printStackTrace();
/*     */ 
/*  83 */       throw new Error();
/*     */     }
/*     */     catch (InvalidKeySpecException e) {
/*  86 */       throw new Error();
/*     */     }
/*     */     catch (NoSuchPaddingException e) {
/*  89 */       throw new Error();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cryptUpdate(byte[] in, int length)
/*     */   {
/*     */     try
/*     */     {
/*  99 */       for (int i = 0; i < length; i++)
/*     */       {
/* 104 */         if (this.next == 16) {
/* 105 */           for (int j = 0; j < 16; j++)
/*     */           {
/*     */             int tmp26_24 = j;
/*     */             byte[] tmp26_21 = this.iv; if ((tmp26_21[tmp26_24] = (byte)(tmp26_21[tmp26_24] + 1)) != 0) break;
/*     */           }
/* 108 */           this.cipher.update(this.iv, 0, 16, this.keystream);
/* 109 */           this.next = 0;
/*     */         }
/*     */         int tmp75_74 = i;
/*     */         byte[] tmp75_73 = in; tmp75_73[tmp75_74] = (byte)(tmp75_73[tmp75_74] ^ this.keystream[(this.next++)]);
/*     */       }
/*     */     }
/*     */     catch (ShortBufferException e) {
/* 116 */       throw new Error();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void authUpdate(byte[] in, int length) {
/* 121 */     this.mac.update(in, 0, length);
/*     */   }
/*     */ 
/*     */   public byte[] getFinalAuthentifier() {
/* 125 */     byte[] auth = new byte[10];
/* 126 */     System.arraycopy(this.mac.doFinal(), 0, auth, 0, 10);
/* 127 */     return auth;
/*     */   }
/*     */ 
/*     */   public byte[] getPasswordVerifier() {
/* 131 */     return this.passwordVerifier;
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESUtilsJCA
 * JD-Core Version:    0.6.0
 */