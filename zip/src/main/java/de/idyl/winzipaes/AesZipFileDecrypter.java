/*     */ package de.idyl.winzipaes;
/*     */ 
/*     */ import de.idyl.winzipaes.impl.AESDecrypter;
/*     */ import de.idyl.winzipaes.impl.ByteArrayHelper;
/*     */ import de.idyl.winzipaes.impl.CentralDirectoryEntry;
/*     */ import de.idyl.winzipaes.impl.ExtRandomAccessFile;
/*     */ import de.idyl.winzipaes.impl.ExtZipEntry;
/*     */ import de.idyl.winzipaes.impl.ExtZipOutputStream;
/*     */ import de.idyl.winzipaes.impl.ZipConstants;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipException;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipInputStream;
/*     */ 
/*     */ public class AesZipFileDecrypter
/*     */   implements ZipConstants
/*     */ {
/*  43 */   private static final Logger LOG = Logger.getLogger(AesZipFileDecrypter.class.getName());
/*     */ 
/*  48 */   public static String charset = "iso-8859-1";
/*     */ 
/*  51 */   protected static int bufferSize = 10240;
/*     */   protected AESDecrypter decrypter;
/*     */   protected ExtRandomAccessFile raFile;
/*     */   protected long dirOffsetPos;
/*     */   protected File zipFile;
/*     */   protected String comment;
/*     */ 
/*     */   public AesZipFileDecrypter(File zipFile, AESDecrypter decrypter)
/*     */     throws IOException
/*     */   {
/*  70 */     this.zipFile = zipFile;
/*  71 */     this.decrypter = decrypter;
/*  72 */     this.raFile = new ExtRandomAccessFile(zipFile);
/*  73 */     initDirOffsetPosAndComment();
/*     */   }
/*     */ 
/*     */   protected void initDirOffsetPosAndComment() throws IOException
/*     */   {
/*  78 */     this.dirOffsetPos = (this.zipFile.length() - 6L);
/*  79 */     int dirOffset = this.raFile.readInt(this.dirOffsetPos - 16L);
/*  80 */     if (dirOffset != 101010256L)
/*     */     {
/*  82 */       byte[] endsig = ByteArrayHelper.toByteArray(101010256);
/*  83 */       long endsigPos = this.raFile.lastPosOf(endsig);
/*  84 */       if (endsigPos == -1L) {
/*  85 */         throw new ZipException("expected ENDSIC not found (marks the beginning of the central directory at end of the zip file)");
/*     */       }
/*  87 */       this.dirOffsetPos = (endsigPos + 16L);
/*  88 */       short commentLength = this.raFile.readShort(this.dirOffsetPos + 4L);
/*  89 */       this.comment = new String(this.raFile.readByteArray(this.dirOffsetPos + 6L, commentLength));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void close() throws IOException
/*     */   {
/*  95 */     this.raFile.close();
/*     */   }
/*     */ 
/*     */   public List<ExtZipEntry> getEntryList()
/*     */     throws IOException, ZipException
/*     */   {
/* 105 */     List out = new ArrayList();
/*     */ 
/* 107 */     short totalNumberOfEntries = getNumberOfEntries();
/* 108 */     int dirOffset = this.raFile.readInt(this.dirOffsetPos);
/*     */ 
/* 110 */     long fileOffset = dirOffset;
/* 111 */     for (int i = 0; i < totalNumberOfEntries; i++) {
/* 112 */       int censig = this.raFile.readInt(fileOffset);
/* 113 */       if (censig != 33639248L) {
/* 114 */         throw new ZipException("expected CENSIC not found at entry no " + (i + 1) + " in central directory at end of zip file at " + fileOffset);
/*     */       }
/*     */ 
/* 117 */       short fileNameLength = this.raFile.readShort(fileOffset + 28L);
/* 118 */       short extraFieldLength = this.raFile.readShort(fileOffset + 30L);
/* 119 */       long fileOffsetPos = fileOffset + 28L + 14L;
/* 120 */       long fileDataOffset = this.raFile.readInt(fileOffsetPos);
/* 121 */       int locsig = this.raFile.readInt(fileDataOffset);
/* 122 */       if (locsig != 67324752L) {
/* 123 */         throw new ZipException("expected LOCSIC not found at alleged position of data for file no " + (i + 1));
/*     */       }
/*     */ 
/* 126 */       byte[] fileNameBytes = this.raFile.readByteArray(fileOffsetPos + 4L, fileNameLength);
/* 127 */       long nextFileOffset = this.raFile.getFilePointer();
/* 128 */       String fileName = new String(fileNameBytes, charset);
/*     */ 
/* 130 */       CentralDirectoryEntry cde = new CentralDirectoryEntry(this.raFile, fileOffset);
/* 131 */       ExtZipEntry zipEntry = new ExtZipEntry(fileName, cde);
/*     */ 
/* 133 */       zipEntry.setCompressedSize(cde.getCompressedSize());
/* 134 */       zipEntry.setSize(cde.getUncompressedSize());
/*     */ 
/* 136 */       long dosTime = this.raFile.readInt(fileOffset + 12L);
/* 137 */       zipEntry.setTime(ExtZipEntry.dosToJavaTime(dosTime));
/*     */ 
/* 139 */       if (cde.isEncrypted()) {
/* 140 */         zipEntry.setMethod(cde.getActualCompressionMethod());
/* 141 */         zipEntry.setOffset((int)(cde.getLocalHeaderOffset() + cde.getLocalHeaderSize()) + cde.getCryptoHeaderLength());
/* 142 */         zipEntry.initEncryptedEntry();
/*     */       } else {
/* 144 */         zipEntry.setMethod(8);
/* 145 */         zipEntry.setPrimaryCompressionMethod(8);
/*     */       }
/*     */ 
/* 148 */       nextFileOffset += extraFieldLength;
/*     */ 
/* 150 */       out.add(zipEntry);
/*     */ 
/* 152 */       fileOffset = nextFileOffset;
/*     */     }
/*     */ 
/* 155 */     return out;
/*     */   }
/*     */ 
/*     */   public ExtZipEntry getEntry(String name) throws IOException, ZipException, DataFormatException {
/* 159 */     for (ExtZipEntry zipEntry : getEntryList()) {
/* 160 */       if (name.equals(zipEntry.getName())) {
/* 161 */         return zipEntry;
/*     */       }
/*     */     }
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */   protected void checkZipEntry(ExtZipEntry zipEntry) throws ZipException {
/* 168 */     if (zipEntry == null) {
/* 169 */       throw new ZipException("zipEntry must NOT be NULL");
/*     */     }
/* 171 */     if (zipEntry.isDirectory()) {
/* 172 */       throw new ZipException("directory entries cannot be decrypted");
/*     */     }
/* 174 */     if (!zipEntry.isEncrypted()) {
        /* 175 */
        throw new ZipException("currently only extracts encrypted files - use java.util.zip to unzip");
    }
/*     */   }
/*     */ 
/*     */   public void extractEntryWithTmpFile(ExtZipEntry zipEntry, File outFile, String password) throws IOException, ZipException, DataFormatException
/*     */   {
/* 180 */     checkZipEntry(zipEntry);
/*     */ 
/* 182 */     CentralDirectoryEntry cde = zipEntry.getCentralDirectoryEntry();
/* 183 */     if (!cde.isAesEncrypted()) {
/* 184 */       throw new ZipException("only AES encrypted files are supported");
/*     */     }
/*     */ 
/* 187 */     int cryptoHeaderOffset = zipEntry.getOffset() - cde.getCryptoHeaderLength();
/*     */ 
/* 189 */     byte[] salt = this.raFile.readByteArray(cryptoHeaderOffset, 16);
/* 190 */     byte[] pwVerification = this.raFile.readByteArray(cryptoHeaderOffset + 16, 2);
/*     */ 
/* 192 */     if (LOG.isLoggable(Level.FINEST)) {
/* 193 */       LOG.finest("\n" + cde.toString());
/* 194 */       LOG.finest("offset    = " + zipEntry.getOffset());
/* 195 */       LOG.finest("cryptoOff = " + cryptoHeaderOffset);
/* 196 */       LOG.finest("password  = " + password + " - " + password.length());
/* 197 */       LOG.finest("salt      = " + ByteArrayHelper.toString(salt) + " - " + salt.length);
/* 198 */       LOG.finest("pwVerif   = " + ByteArrayHelper.toString(pwVerification) + " - " + pwVerification.length);
/*     */     }
/*     */ 
/* 202 */     this.decrypter.init(password, 256, salt, pwVerification);
/*     */ 
/* 205 */     File tmpFile = new File(outFile.getPath() + "_TMP.zip");
/* 206 */     makeDir(new File(tmpFile.getParent()));
/*     */ 
/* 208 */     ExtZipOutputStream zos = null;
/* 209 */     ZipFile zf = null;
/* 210 */     FileOutputStream fos = null;
/* 211 */     InputStream is = null;
/*     */     try {
/* 213 */       zos = new ExtZipOutputStream(tmpFile);
/* 214 */       ExtZipEntry tmpEntry = new ExtZipEntry(zipEntry);
/* 215 */       tmpEntry.setPrimaryCompressionMethod(zipEntry.getMethod());
/* 216 */       zos.putNextEntry(tmpEntry);
/*     */ 
/* 218 */       this.raFile.seek(cde.getOffset());
/* 219 */       byte[] buffer = new byte[bufferSize];
/* 220 */       int remaining = (int)zipEntry.getEncryptedDataSize();
/* 221 */       while (remaining > 0) {
/* 222 */         int len = remaining > buffer.length ? buffer.length : remaining;
/* 223 */         int read = this.raFile.readByteArray(buffer, len);
/* 224 */         this.decrypter.decrypt(buffer, read);
/* 225 */         zos.writeBytes(buffer, 0, read);
/* 226 */         remaining -= len;
/*     */       }
/* 228 */       zos.finish();
/* 229 */       zos = null;
/*     */ 
/* 231 */       byte[] storedMac = new byte[10];
/* 232 */       this.raFile.readByteArray(storedMac, 10);
/* 233 */       byte[] calcMac = this.decrypter.getFinalAuthentication();
/* 234 */       if (LOG.isLoggable(Level.FINE)) {
/* 235 */         LOG.fine("storedMac=" + Arrays.toString(storedMac));
/* 236 */         LOG.fine("calcMac=" + Arrays.toString(calcMac));
/*     */       }
/* 238 */       if (!Arrays.equals(storedMac, calcMac)) {
/* 239 */         throw new ZipException("stored authentication (mac) value does not match calculated one");
/*     */       }
/*     */ 
/* 242 */       zf = new ZipFile(tmpFile);
/* 243 */       ZipEntry ze = (ZipEntry)zf.entries().nextElement();
/* 244 */       is = zf.getInputStream(ze);
/* 245 */       fos = new FileOutputStream(outFile.getPath());
/* 246 */       int read = is.read(buffer);
/* 247 */       while (read > 0) {
/* 248 */         fos.write(buffer, 0, read);
/* 249 */         read = is.read(buffer);
/*     */       }
/*     */     } finally {
/* 252 */       if (zos != null) {
/* 253 */         zos.close();
/*     */       }
/* 255 */       if (zf != null) {
/* 256 */         zf.close();
/*     */       }
/* 258 */       if (fos != null) {
/* 259 */         fos.close();
/*     */       }
/* 261 */       if (is != null) {
/* 262 */         is.close();
/*     */       }
/*     */     }
/*     */ 
/* 266 */     tmpFile.delete();
/*     */   }
/*     */ 
/*     */   public short getNumberOfEntries() throws IOException
/*     */   {
/* 271 */     return this.raFile.readShort(this.dirOffsetPos - 6L);
/*     */   }
/*     */ 
/*     */   protected static void makeDir(File dir) {
/* 275 */     if ((dir != null) && 
/* 276 */       (!dir.exists())) {
/* 277 */       if (dir.getParent() != null) {
/* 278 */         File parentDir = new File(dir.getParent());
/* 279 */         if (!parentDir.exists()) {
/* 280 */           makeDir(parentDir);
/*     */         }
/*     */       }
/* 283 */       dir.mkdir();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/* 290 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void extractEntry(ExtZipEntry zipEntry, OutputStream outStream, String password)
/*     */     throws IOException, ZipException, DataFormatException
/*     */   {
/* 300 */     checkZipEntry(zipEntry);
/*     */ 
/* 302 */     ZipInputStream zipInputStream = null;
/* 303 */     ByteArrayOutputStream bos = null;
/*     */     try {
/* 305 */       CentralDirectoryEntry cde = zipEntry.getCentralDirectoryEntry();
/* 306 */       if (!cde.isAesEncrypted()) {
/* 307 */         throw new ZipException("only AES encrypted files are supported");
/*     */       }
/* 309 */       int cryptoHeaderOffset = zipEntry.getOffset() - cde.getCryptoHeaderLength();
/* 310 */       byte[] salt = this.raFile.readByteArray(cryptoHeaderOffset, 16);
/* 311 */       byte[] pwVerification = this.raFile.readByteArray(cryptoHeaderOffset + 16, 2);
/* 312 */       if (LOG.isLoggable(Level.FINEST)) {
/* 313 */         LOG.finest("\n" + cde.toString());
/* 314 */         LOG.finest("offset    = " + zipEntry.getOffset());
/* 315 */         LOG.finest("cryptoOff = " + cryptoHeaderOffset);
/* 316 */         LOG.finest("password  = " + password + " - " + password.length());
/* 317 */         LOG.finest("salt      = " + ByteArrayHelper.toString(salt) + " - " + salt.length);
/* 318 */         LOG.finest("pwVerif   = " + ByteArrayHelper.toString(pwVerification) + " - " + 
/* 319 */           pwVerification.length);
/*     */       }
/*     */ 
/* 322 */       this.decrypter.init(password, 256, salt, pwVerification);
/*     */ 
/* 324 */       bos = new ByteArrayOutputStream(bufferSize);
/* 325 */       ExtZipOutputStream zos = new ExtZipOutputStream(bos);
/* 326 */       ExtZipEntry tmpEntry = new ExtZipEntry(zipEntry);
/* 327 */       tmpEntry.setPrimaryCompressionMethod(zipEntry.getMethod());
/* 328 */       tmpEntry.setCompressedSize(zipEntry.getEncryptedDataSize());
/* 329 */       zos.putNextEntry(tmpEntry);
/* 330 */       this.raFile.seek(cde.getOffset());
/* 331 */       byte[] buffer = new byte[bufferSize];
/* 332 */       int remaining = (int)zipEntry.getEncryptedDataSize();
/* 333 */       while (remaining > 0) {
/* 334 */         int len = remaining > buffer.length ? buffer.length : remaining;
/* 335 */         int read = this.raFile.readByteArray(buffer, len);
/* 336 */         this.decrypter.decrypt(buffer, read);
/* 337 */         zos.writeBytes(buffer, 0, read);
/* 338 */         remaining -= len;
/*     */       }
/* 340 */       zos.finish();
/* 341 */       byte[] storedMac = new byte[10];
/* 342 */       this.raFile.readByteArray(storedMac, 10);
/* 343 */       byte[] calcMac = this.decrypter.getFinalAuthentication();
/* 344 */       if (LOG.isLoggable(Level.FINE)) {
/* 345 */         LOG.fine("storedMac=" + Arrays.toString(storedMac));
/* 346 */         LOG.fine("calcMac=" + Arrays.toString(calcMac));
/*     */       }
/* 348 */       if (!Arrays.equals(storedMac, calcMac)) {
/* 349 */         throw new ZipException("stored authentication (mac) value does not match calculated one");
/*     */       }
/* 351 */       zipInputStream = new ZipInputStream(new ByteArrayInputStream(bos.toByteArray()));
/* 352 */       ZipEntry entry = zipInputStream.getNextEntry();
/* 353 */       if (entry.getSize() != 0L) {
/* 354 */         int read = zipInputStream.read(buffer);
/*     */ 
/* 358 */         CRC32 crc32 = new CRC32();
/* 359 */         while (read > 0) {
/* 360 */           outStream.write(buffer, 0, read);
/* 361 */           crc32.update(buffer, 0, read);
/* 362 */           entry.setCrc(crc32.getValue());
/* 363 */           read = zipInputStream.read(buffer);
/*     */         }
/*     */       }
/*     */     } finally {
/* 367 */       if (bos != null) {
/* 368 */         bos.close();
/*     */       }
/* 370 */       if (zipInputStream != null)
/* 371 */         zipInputStream.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void extractEntry(ExtZipEntry zipEntry, File outFile, String password)
/*     */     throws IOException, ZipException, DataFormatException
/*     */   {
/* 385 */     ByteArrayOutputStream bos = null;
/* 386 */     FileOutputStream fos = null;
/*     */     try {
/* 388 */       bos = new ByteArrayOutputStream(bufferSize);
/* 389 */       fos = new FileOutputStream(outFile);
/* 390 */       extractEntry(zipEntry, bos, password);
/* 391 */       byte[] buffer = bos.toByteArray();
/* 392 */       fos.write(buffer);
/*     */     } finally {
/* 394 */       if (bos != null) {
/* 395 */         bos.close();
/*     */       }
/* 397 */       if (fos != null)
/* 398 */         fos.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.AesZipFileDecrypter
 * JD-Core Version:    0.6.0
 */