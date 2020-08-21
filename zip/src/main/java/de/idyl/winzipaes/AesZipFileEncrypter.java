/*     */ package de.idyl.winzipaes;
/*     */ 
/*     */ import de.idyl.winzipaes.impl.AESEncrypter;
/*     */ import de.idyl.winzipaes.impl.ExtZipEntry;
/*     */ import de.idyl.winzipaes.impl.ExtZipOutputStream;
/*     */ import de.idyl.winzipaes.impl.ZipFileEntryInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ 
/*     */ public class AesZipFileEncrypter
/*     */ {
/*  39 */   private static final Logger LOG = Logger.getLogger(AesZipFileEncrypter.class.getName());
/*     */   protected AESEncrypter encrypter;
/*     */   protected ExtZipOutputStream zipOS;
/*     */ 
/*     */   public void setEncoding(String encoding)
/*     */   {
/*  50 */     this.zipOS.setEncoding(encoding);
/*     */   }
/*     */ 
/*     */   public AesZipFileEncrypter(String pathName, AESEncrypter encrypter)
/*     */     throws IOException
/*     */   {
/*  59 */     this.zipOS = new ExtZipOutputStream(new File(pathName));
/*  60 */     this.encrypter = encrypter;
/*     */   }
/*     */ 
/*     */   public AesZipFileEncrypter(File outFile, AESEncrypter encrypter)
/*     */     throws IOException
/*     */   {
/*  69 */     this.zipOS = new ExtZipOutputStream(outFile);
/*  70 */     this.encrypter = encrypter;
/*     */   }
/*     */ 
/*     */   public AesZipFileEncrypter(OutputStream outFile, AESEncrypter encrypter) throws IOException {
/*  74 */     this.zipOS = new ExtZipOutputStream(outFile);
/*  75 */     this.encrypter = encrypter;
/*     */   }
/*     */ 
/*     */   protected void add(ExtZipEntry zipEntry, InputStream zipData)
/*     */     throws IOException, UnsupportedEncodingException
/*     */   {
/*  82 */     this.zipOS.putNextEntry(zipEntry);
/*     */ 
/*  84 */     byte[] data = new byte[1024];
/*  85 */     int read = zipData.read(data);
/*  86 */     while (read != -1) {
/*  87 */       this.zipOS.writeBytes(data, 0, read);
/*  88 */       read = zipData.read(data);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void add(ZipFile inFile, String password) throws IOException, UnsupportedEncodingException
/*     */   {
/*  94 */     ZipFileEntryInputStream zfe = new ZipFileEntryInputStream(inFile.getName());
/*     */     try {
/*  96 */       Enumeration en = inFile.entries();
/*  97 */       while (en.hasMoreElements()) {
/*  98 */         ZipEntry ze = (ZipEntry)en.nextElement();
/*  99 */         zfe.nextEntry(ze);
/* 100 */         add(ze, zfe, password);
/*     */       }
/*     */     } finally {
/* 103 */       zfe.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void add(ZipEntry zipEntry, ZipFileEntryInputStream zipData, String password)
/*     */     throws IOException, UnsupportedEncodingException
/*     */   {
/* 110 */     this.encrypter.init(password, 256);
/*     */ 
/* 112 */     ExtZipEntry entry = new ExtZipEntry(zipEntry.getName());
/* 113 */     entry.setMethod(zipEntry.getMethod());
/* 114 */     entry.setSize(zipEntry.getSize());
/* 115 */     entry.setCompressedSize(zipEntry.getCompressedSize() + 28L);
/* 116 */     entry.setTime(zipEntry.getTime());
/* 117 */     entry.initEncryptedEntry(this.zipOS.utf8Flg);
/*     */ 
/* 119 */     this.zipOS.putNextEntry(entry);
/*     */ 
/* 121 */     this.zipOS.writeBytes(this.encrypter.getSalt());
/* 122 */     this.zipOS.writeBytes(this.encrypter.getPwVerification());
/*     */ 
/* 124 */     byte[] data = new byte[1024];
/* 125 */     int read = zipData.read(data);
/* 126 */     while (read != -1) {
/* 127 */       this.encrypter.encrypt(data, read);
/* 128 */       this.zipOS.writeBytes(data, 0, read);
/* 129 */       read = zipData.read(data);
/*     */     }
/*     */ 
/* 132 */     byte[] finalAuthentication = this.encrypter.getFinalAuthentication();
/* 133 */     if (LOG.isLoggable(Level.FINE)) {
/* 134 */       LOG.fine("finalAuthentication=" + Arrays.toString(finalAuthentication) + " at pos=" + 
/* 135 */         this.zipOS.getWritten());
/*     */     }
/*     */ 
/* 138 */     this.zipOS.writeBytes(finalAuthentication);
/*     */   }
/*     */ 
/*     */   public void add(File file, String pathForEntry, String password)
/*     */     throws IOException, UnsupportedEncodingException
/*     */   {
/* 149 */     FileInputStream fis = new FileInputStream(file);
/*     */     try {
/* 151 */       add(pathForEntry, fis, password);
/*     */     } finally {
/* 153 */       fis.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void add(File file, String password)
/*     */     throws IOException, UnsupportedEncodingException
/*     */   {
/* 164 */     FileInputStream fis = new FileInputStream(file);
/*     */     try {
/* 166 */       add(file.getPath(), fis, password);
/*     */     } finally {
/* 168 */       fis.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void add(String name, InputStream is, String password)
/*     */     throws IOException, UnsupportedEncodingException
/*     */   {
/* 181 */     this.encrypter.init(password, 256);
/*     */ 
/* 185 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 186 */     DeflaterOutputStream dos = new DeflaterOutputStream(bos, new Deflater(9, true), 8192);
/* 187 */     int read = 0;
/* 188 */     long inputLen = 0L;
/* 189 */     byte[] buf = new byte[8192];
/* 190 */     while ((read = is.read(buf)) > 0) {
/* 191 */       inputLen += read;
/* 192 */       dos.write(buf, 0, read);
/*     */     }
/* 194 */     dos.close();
/* 195 */     byte[] data = bos.toByteArray();
/*     */ 
/* 197 */     ExtZipEntry entry = new ExtZipEntry(name);
/* 198 */     entry.setMethod(8);
/* 199 */     entry.setSize(inputLen);
/* 200 */     entry.setCompressedSize(data.length + 28);
/* 201 */     entry.setTime(System.currentTimeMillis());
/* 202 */     entry.initEncryptedEntry(this.zipOS.utf8Flg);
/*     */ 
/* 204 */     this.zipOS.putNextEntry(entry);
/*     */ 
/* 206 */     this.zipOS.writeBytes(this.encrypter.getSalt());
/* 207 */     this.zipOS.writeBytes(this.encrypter.getPwVerification());
/*     */ 
/* 209 */     this.encrypter.encrypt(data, data.length);
/* 210 */     this.zipOS.writeBytes(data, 0, data.length);
/*     */ 
/* 212 */     byte[] finalAuthentication = this.encrypter.getFinalAuthentication();
/* 213 */     if (LOG.isLoggable(Level.FINE)) {
/* 214 */       LOG.fine("finalAuthentication=" + Arrays.toString(finalAuthentication) + " at pos=" + 
/* 215 */         this.zipOS.getWritten());
/*     */     }
/*     */ 
/* 218 */     this.zipOS.writeBytes(finalAuthentication);
/*     */   }
/*     */ 
/*     */   public static void zip(File inFile, File outFile)
/*     */     throws IOException
/*     */   {
/* 227 */     FileInputStream fin = new FileInputStream(inFile);
/* 228 */     FileOutputStream fout = new FileOutputStream(outFile);
/* 229 */     ZipOutputStream zout = new ZipOutputStream(fout);
/*     */     try
/*     */     {
/* 232 */       zout.putNextEntry(new ZipEntry(inFile.getName()));
/* 233 */       byte[] buffer = new byte[1024];
/*     */       int len;
/* 235 */       while ((len = fin.read(buffer)) > 0)
/*     */       {
/* 236 */         zout.write(buffer, 0, len);
/*     */       }
/* 238 */       zout.closeEntry();
/*     */     } finally {
/* 240 */       zout.close();
/* 241 */       fin.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addAll(File pathToZipFile, String password)
/*     */     throws IOException
/*     */   {
/* 262 */     ZipFile zipFile = new ZipFile(pathToZipFile);
/*     */     try {
/* 264 */       add(zipFile, password);
/*     */     } finally {
/* 266 */       zipFile.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 273 */     this.zipOS.setComment(comment);
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 283 */     this.zipOS.finish();
/*     */   }
/*     */ 
/*     */   public static void zipAndEncrypt(File inFile, File outFile, String password, AESEncrypter encrypter)
/*     */     throws IOException
/*     */   {
/* 292 */     AesZipFileEncrypter enc = new AesZipFileEncrypter(outFile, encrypter);
/*     */     try {
/* 294 */       enc.add(inFile, password);
/*     */     } finally {
/* 296 */       enc.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void zipAndEncryptAll(File inZipFile, File outFile, String password, AESEncrypter encrypter)
/*     */     throws IOException
/*     */   {
/* 306 */     AesZipFileEncrypter enc = new AesZipFileEncrypter(outFile, encrypter);
/*     */     try {
/* 308 */       enc.addAll(inZipFile, password);
/*     */     } finally {
/* 310 */       enc.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.AesZipFileEncrypter
 * JD-Core Version:    0.6.0
 */