/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ public class AESEncrypterBC extends AESCryptoBase
/*     */   implements AESEncrypter
/*     */ {
/*  31 */   private static final Logger LOG = Logger.getLogger(AESEncrypterBC.class.getName());
/*     */   protected CipherParameters cipherParameters;
/*     */   protected SICBlockCipher aesCipher;
/*     */   protected HMac mac;
/* 144 */   private static final Random RANDOM = new SecureRandom();
/*     */ 
/*     */   public void init(String pwStr, int keySize)
/*     */     throws ZipException
/*     */   {
/*  46 */     byte[] pwBytes = pwStr.getBytes();
/*  47 */     PBEParametersGenerator generator = new PKCS5S2ParametersGenerator();
/*  48 */     this.saltBytes = createSalt();
/*  49 */     generator.init(pwBytes, this.saltBytes, 1000);
/*     */ 
/*  53 */     this.cipherParameters = generator.generateDerivedParameters(528);
/*  54 */     byte[] keyBytes = ((KeyParameter)this.cipherParameters).getKey();
/*     */ 
/*  56 */     this.cryptoKeyBytes = new byte[32];
/*  57 */     System.arraycopy(keyBytes, 0, this.cryptoKeyBytes, 0, 32);
/*     */ 
/*  59 */     this.authenticationCodeBytes = new byte[32];
/*  60 */     System.arraycopy(keyBytes, 32, this.authenticationCodeBytes, 0, 32);
/*     */ 
/*  63 */     this.pwVerificationBytes = new byte[2];
/*  64 */     System.arraycopy(keyBytes, 64, this.pwVerificationBytes, 0, 2);
/*     */ 
/*  67 */     generator.init(pwBytes, this.saltBytes, 1000);
/*  68 */     this.cipherParameters = generator.generateDerivedParameters(256);
/*     */ 
/*  71 */     this.mac = new HMac(new SHA1Digest());
/*  72 */     this.mac.init(new KeyParameter(this.authenticationCodeBytes));
/*     */ 
/*  74 */     this.aesCipher = new SICBlockCipher(new AESEngine());
/*  75 */     this.blockSize = this.aesCipher.getBlockSize();
/*     */ 
/*  78 */     this.nonce = 1;
/*     */ 
/*  80 */     if (LOG.isLoggable(Level.FINEST)) {
/*  81 */       LOG.finest("pwBytes   = " + ByteArrayHelper.toString(pwBytes) + " - " + pwBytes.length);
/*  82 */       LOG.finest("salt      = " + ByteArrayHelper.toString(this.saltBytes) + " - " + this.saltBytes.length);
/*  83 */       LOG.finest("pwVerif   = " + ByteArrayHelper.toString(this.pwVerificationBytes) + " - " + this.pwVerificationBytes.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void encrypt(byte[] in, int length)
/*     */   {
/*  91 */     int pos = 0;
/*  92 */     while ((pos < in.length) && (pos < length)) {
/*  93 */       encryptBlock(in, pos, length);
/*  94 */       pos += this.blockSize;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void encryptBlock(byte[] in, int pos, int length)
/*     */   {
/* 103 */     byte[] encryptedIn = new byte[this.blockSize];
/* 104 */     byte[] ivBytes = ByteArrayHelper.toByteArray(this.nonce++, 16);
/* 105 */     ParametersWithIV ivParams = new ParametersWithIV(this.cipherParameters, ivBytes);
/* 106 */     this.aesCipher.init(true, ivParams);
/*     */ 
/* 108 */     int remainingCount = length - pos;
/* 109 */     if (remainingCount >= this.blockSize) {
/* 110 */       this.aesCipher.processBlock(in, pos, encryptedIn, 0);
/* 111 */       System.arraycopy(encryptedIn, 0, in, pos, this.blockSize);
/* 112 */       this.mac.update(encryptedIn, 0, this.blockSize);
/*     */     } else {
/* 114 */       byte[] extendedIn = new byte[this.blockSize];
/* 115 */       System.arraycopy(in, pos, extendedIn, 0, remainingCount);
/* 116 */       this.aesCipher.processBlock(extendedIn, 0, encryptedIn, 0);
/* 117 */       System.arraycopy(encryptedIn, 0, in, pos, remainingCount);
/* 118 */       this.mac.update(encryptedIn, 0, remainingCount);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getSalt()
/*     */   {
/* 124 */     return this.saltBytes;
/*     */   }
/*     */ 
/*     */   public byte[] getPwVerification()
/*     */   {
/* 129 */     return this.pwVerificationBytes;
/*     */   }
/*     */ 
/*     */   public byte[] getFinalAuthentication()
/*     */   {
/* 135 */     byte[] macBytes = new byte[this.mac.getMacSize()];
/* 136 */     this.mac.doFinal(macBytes, 0);
/* 137 */     byte[] macBytes10 = new byte[10];
/* 138 */     System.arraycopy(macBytes, 0, macBytes10, 0, 10);
/* 139 */     return macBytes10;
/*     */   }
/*     */ 
/*     */   protected static byte[] createSalt()
/*     */   {
/* 150 */     byte[] salt = new byte[16];
/* 151 */     RANDOM.nextBytes(salt);
/* 152 */     return salt;
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESEncrypterBC
 * JD-Core Version:    0.6.0
 */