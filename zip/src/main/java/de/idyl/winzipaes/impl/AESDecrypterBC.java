/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.util.zip.ZipException;
/*     */ import org.bouncycastle.crypto.CipherParameters;
/*     */ import org.bouncycastle.crypto.PBEParametersGenerator;
/*     */ import org.bouncycastle.crypto.digests.SHA1Digest;
/*     */ import org.bouncycastle.crypto.engines.AESEngine;
/*     */ import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
/*     */ import org.bouncycastle.crypto.macs.HMac;
/*     */ import org.bouncycastle.crypto.modes.SICBlockCipher;
/*     */ import org.bouncycastle.crypto.params.KeyParameter;
/*     */ import org.bouncycastle.crypto.params.ParametersWithIV;
/*     */ 
/*     */ public class AESDecrypterBC extends AESCryptoBase
/*     */   implements AESDecrypter
/*     */ {
/*     */   protected CipherParameters cipherParameters;
/*     */   protected SICBlockCipher aesCipher;
/*     */   protected HMac mac;
/*     */ 
/*     */   public void init(String pwStr, int keySize, byte[] salt, byte[] pwVerification)
/*     */     throws ZipException
/*     */   {
/*  26 */     byte[] pwBytes = pwStr.getBytes();
/*     */ 
/*  28 */     this.saltBytes = salt;
/*     */ 
/*  30 */     PBEParametersGenerator generator = new PKCS5S2ParametersGenerator();
/*  31 */     generator.init(pwBytes, salt, 1000);
/*     */ 
/*  33 */     this.cipherParameters = generator.generateDerivedParameters(528);
/*  34 */     byte[] keyBytes = ((KeyParameter)this.cipherParameters).getKey();
/*     */ 
/*  36 */     this.cryptoKeyBytes = new byte[32];
/*  37 */     System.arraycopy(keyBytes, 0, this.cryptoKeyBytes, 0, 32);
/*     */ 
/*  39 */     this.authenticationCodeBytes = new byte[32];
/*  40 */     System.arraycopy(keyBytes, 32, this.authenticationCodeBytes, 0, 32);
/*     */ 
/*  43 */     this.pwVerificationBytes = new byte[2];
/*  44 */     System.arraycopy(keyBytes, 64, this.pwVerificationBytes, 0, 2);
/*     */ 
/*  46 */     if (!ByteArrayHelper.isEqual(this.pwVerificationBytes, pwVerification)) {
/*  47 */       throw new ZipException("wrong password - " + ByteArrayHelper.toString(this.pwVerificationBytes) + "/ " + ByteArrayHelper.toString(pwVerification));
/*     */     }
/*     */ 
/*  51 */     generator.init(pwBytes, salt, 1000);
/*  52 */     this.cipherParameters = generator.generateDerivedParameters(256);
/*     */ 
/*  55 */     this.mac = new HMac(new SHA1Digest());
/*  56 */     this.mac.init(new KeyParameter(this.authenticationCodeBytes));
/*     */ 
/*  58 */     this.aesCipher = new SICBlockCipher(new AESEngine());
/*  59 */     this.blockSize = this.aesCipher.getBlockSize();
/*     */ 
/*  62 */     this.nonce = 1;
/*     */   }
/*     */ 
/*     */   public void decrypt(byte[] in, int length)
/*     */   {
/*  77 */     int pos = 0;
/*  78 */     while ((pos < in.length) && (pos < length)) {
/*  79 */       decryptBlock(in, pos, length);
/*  80 */       pos += this.blockSize;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void decryptBlock(byte[] in, int pos, int length)
/*     */   {
/*  89 */     byte[] decryptedIn = new byte[this.blockSize];
/*  90 */     byte[] ivBytes = ByteArrayHelper.toByteArray(this.nonce++, 16);
/*  91 */     ParametersWithIV ivParams = new ParametersWithIV(this.cipherParameters, ivBytes);
/*  92 */     this.aesCipher.init(false, ivParams);
/*     */ 
/*  94 */     int remainingCount = length - pos;
/*  95 */     if (remainingCount >= this.blockSize) {
/*  96 */       this.mac.update(in, pos, this.blockSize);
/*  97 */       this.aesCipher.processBlock(in, pos, decryptedIn, 0);
/*  98 */       System.arraycopy(decryptedIn, 0, in, pos, this.blockSize);
/*     */     } else {
/* 100 */       this.mac.update(in, pos, remainingCount);
/* 101 */       byte[] extendedIn = new byte[this.blockSize];
/* 102 */       System.arraycopy(in, pos, extendedIn, 0, remainingCount);
/* 103 */       this.aesCipher.processBlock(extendedIn, 0, decryptedIn, 0);
/* 104 */       System.arraycopy(decryptedIn, 0, in, pos, remainingCount);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getFinalAuthentication() {
/* 109 */     byte[] macBytes = new byte[this.mac.getMacSize()];
/* 110 */     this.mac.doFinal(macBytes, 0);
/* 111 */     byte[] macBytes10 = new byte[10];
/* 112 */     System.arraycopy(macBytes, 0, macBytes10, 0, 10);
/* 113 */     return macBytes10;
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESDecrypterBC
 * JD-Core Version:    0.6.0
 */