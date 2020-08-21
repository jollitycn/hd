/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.ZipEntry;
/*     */ 
/*     */ public class ZipFileEntryInputStream
/*     */   implements ZipConstants
/*     */ {
/*  18 */   private static final Logger LOG = Logger.getLogger(ZipFileEntryInputStream.class.getName());
/*     */   protected FileInputStream fis;
/*     */   protected long startPos;
/*     */   protected long endPos;
/*     */   protected long currentPos;
/*     */   protected long compressedSize;
/*     */ 
/*     */   public long getCompressedSize()
/*     */   {
/*  31 */     return this.compressedSize;
/*     */   }
/*     */ 
/*     */   public ZipFileEntryInputStream(String fileName) throws IOException {
/*  35 */     this.fis = new FileInputStream(fileName);
/*     */   }
/*     */ 
/*     */   public void nextEntry(ZipEntry ze)
/*     */     throws IOException
/*     */   {
/*  44 */     LOG.fine("nextEntry().currentPos=" + this.currentPos);
/*     */ 
/*  46 */     byte[] intBuffer = new byte[4];
/*  47 */     int bytesRead = this.fis.read(intBuffer);
/*  48 */     LOG.fine("bytes read=" + bytesRead);
/*  49 */     if (bytesRead == -1)
/*     */     {
/*  51 */       throw new IOException("no data available - available=" + this.fis.available());
/*     */     }
/*     */ 
/*  54 */     int dataDescriptorLength = 0;
/*  55 */     if (Arrays.equals(intBuffer, new byte[] { 80, 75, 7, 8 }))
/*     */     {
/*  58 */       dataDescriptorLength = 12;
/*  59 */       this.fis.skip(dataDescriptorLength);
/*     */ 
/*  61 */       this.fis.read(intBuffer);
/*     */     }
/*     */ 
/*  64 */     if (!Arrays.equals(intBuffer, new byte[] { 80, 75, 3, 4 })) {
/*  65 */       throw new IOException("wrong local file header signature - value=" + ByteArrayHelper.toString(intBuffer));
/*     */     }
/*     */ 
/*  69 */     boolean hasDataDescriptor = (ze.getMethod() & 0x8) > 0;
/*  70 */     LOG.fine("nextEntry().hasDataDescriptor=" + hasDataDescriptor);
/*     */ 
/*  72 */     this.compressedSize = ze.getCompressedSize();
/*     */ 
/*  74 */     this.fis.skip(22L);
/*     */ 
/*  76 */     byte[] shortBuffer = new byte[2];
/*  77 */     this.fis.read(shortBuffer);
/*  78 */     int fileNameLength = ByteArrayHelper.toInt(shortBuffer);
/*     */ 
/*  80 */     this.fis.read(shortBuffer);
/*  81 */     int extraFieldLength = ByteArrayHelper.toInt(shortBuffer);
/*     */ 
/*  83 */     this.startPos = (30 + fileNameLength + extraFieldLength + dataDescriptorLength);
/*  84 */     this.currentPos = this.startPos;
/*  85 */     this.endPos = (this.startPos + this.compressedSize);
/*     */ 
/*  87 */     this.fis.skip(fileNameLength + extraFieldLength);
/*     */   }
/*     */ 
/*     */   public int read(byte[] b) throws IOException
/*     */   {
/*  92 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  96 */     int bytesRead = -1;
/*  97 */     int remainingBytes = (int)(this.endPos - this.currentPos);
/*  98 */     if (remainingBytes > 0) {
/*  99 */       if (this.currentPos + len < this.endPos) {
/* 100 */         bytesRead = this.fis.read(b, off, len);
/* 101 */         this.currentPos += bytesRead;
/*     */       } else {
/* 103 */         bytesRead = this.fis.read(b, off, remainingBytes);
/* 104 */         this.currentPos += bytesRead;
/*     */       }
/*     */     }
/* 107 */     return bytesRead;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 111 */     this.fis.close();
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.ZipFileEntryInputStream
 * JD-Core Version:    0.6.0
 */