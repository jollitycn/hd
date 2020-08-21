/*    */ package de.idyl.winzipaes.impl;
/*    */ 
/*    */ import java.security.SecureRandom;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class AESEncrypterJCA
/*    */   implements AESEncrypter
/*    */ {
/*    */   private byte[] salt;
/*    */   private AESUtilsJCA utils;
/* 57 */   private static final Random RANDOM = new SecureRandom();
/*    */ 
/*    */   public void init(String password, int keySize)
/*    */   {
/* 32 */     this.salt = createSalt(keySize / 16);
/* 33 */     this.utils = new AESUtilsJCA(password, keySize, this.salt);
/*    */   }
/*    */ 
/*    */   public void encrypt(byte[] in, int length)
/*    */   {
/* 38 */     this.utils.cryptUpdate(in, length);
/* 39 */     this.utils.authUpdate(in, length);
/*    */   }
/*    */ 
/*    */   public byte[] getSalt()
/*    */   {
/* 44 */     return this.salt;
/*    */   }
/*    */ 
/*    */   public byte[] getPwVerification()
/*    */   {
/* 49 */     return this.utils.getPasswordVerifier();
/*    */   }
/*    */ 
/*    */   public byte[] getFinalAuthentication()
/*    */   {
/* 54 */     return this.utils.getFinalAuthentifier();
/*    */   }
/*    */ 
/*    */   private static byte[] createSalt(int size)
/*    */   {
/* 60 */     byte[] salt = new byte[size];
/* 61 */     RANDOM.nextBytes(salt);
/* 62 */     return salt;
/*    */   }
/*    */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESEncrypterJCA
 * JD-Core Version:    0.6.0
 */