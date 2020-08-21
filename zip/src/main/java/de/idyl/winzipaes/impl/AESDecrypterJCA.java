/*    */ package de.idyl.winzipaes.impl;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.zip.ZipException;
/*    */ 
/*    */ public class AESDecrypterJCA
/*    */   implements AESDecrypter
/*    */ {
/*    */   private AESUtilsJCA utils;
/*    */ 
/*    */   public void init(String password, int keySize, byte[] salt, byte[] passwordVerifier)
/*    */     throws ZipException
/*    */   {
/* 31 */     this.utils = new AESUtilsJCA(password, keySize, salt);
/* 32 */     if (!Arrays.equals(passwordVerifier, this.utils.getPasswordVerifier()))
/* 33 */       throw new ZipException("Password verification failed");
/*    */   }
/*    */ 
/*    */   public void decrypt(byte[] in, int length)
/*    */   {
/* 38 */     this.utils.authUpdate(in, length);
/* 39 */     this.utils.cryptUpdate(in, length);
/*    */   }
/*    */ 
/*    */   public byte[] getFinalAuthentication()
/*    */   {
/* 44 */     return this.utils.getFinalAuthentifier();
/*    */   }
/*    */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESDecrypterJCA
 * JD-Core Version:    0.6.0
 */