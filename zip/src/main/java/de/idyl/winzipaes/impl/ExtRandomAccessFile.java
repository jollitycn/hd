/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class ExtRandomAccessFile
/*     */ {
/*     */   protected RandomAccessFile file;
/*     */ 
/*     */   public ExtRandomAccessFile(File zipFile)
/*     */     throws IOException
/*     */   {
/*  22 */     this.file = new RandomAccessFile(zipFile, "r");
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/*  26 */     this.file.close();
/*     */   }
/*     */ 
/*     */   public int readByteArray(byte[] buffer, int len)
/*     */     throws IOException
/*     */   {
/*  32 */     int read = this.file.read(buffer, 0, len);
/*  33 */     return read;
/*     */   }
/*     */ 
/*     */   public byte[] readByteArray(long pos, int length) throws IOException {
/*  37 */     byte[] out = new byte[length];
/*  38 */     this.file.seek(pos);
/*  39 */     this.file.read(out, 0, length);
/*  40 */     return out;
/*     */   }
/*     */ 
/*     */   public long readLong() throws IOException {
/*  44 */     byte[] b = new byte[8];
/*  45 */     this.file.read(b, 0, 8);
/*  46 */     long out = ByteArrayHelper.toLong(b);
/*  47 */     return out;
/*     */   }
/*     */ 
/*     */   public long readLong(long pos) throws IOException {
/*  51 */     this.file.seek(pos);
/*  52 */     return readLong();
/*     */   }
/*     */ 
/*     */   public int readInt() throws IOException {
/*  56 */     byte[] b = new byte[4];
/*  57 */     this.file.read(b, 0, 4);
/*  58 */     int out = ByteArrayHelper.toInt(b);
/*  59 */     return out;
/*     */   }
/*     */ 
/*     */   public int readInt(long pos) throws IOException {
/*  63 */     this.file.seek(pos);
/*  64 */     return readInt();
/*     */   }
/*     */ 
/*     */   public short readShort() throws IOException {
/*  68 */     byte[] b = new byte[2];
/*  69 */     this.file.read(b, 0, 2);
/*  70 */     short out = ByteArrayHelper.toShort(b);
/*  71 */     return out;
/*     */   }
/*     */ 
/*     */   public short readShort(long pos) throws IOException {
/*  75 */     this.file.seek(pos);
/*  76 */     return readShort();
/*     */   }
/*     */ 
/*     */   public byte readByte() throws IOException {
/*  80 */     byte[] b = new byte[1];
/*  81 */     this.file.read(b, 0, 1);
/*  82 */     return b[0];
/*     */   }
/*     */ 
/*     */   public byte readByte(long pos) throws IOException {
/*  86 */     this.file.seek(pos);
/*  87 */     return readByte();
/*     */   }
/*     */ 
/*     */   public void seek(long pos)
/*     */     throws IOException
/*     */   {
/*  93 */     this.file.seek(pos);
/*     */   }
/*     */ 
/*     */   public long getFilePointer() throws IOException {
/*  97 */     return this.file.getFilePointer();
/*     */   }
/*     */ 
/*     */   public long lastPosOf(byte[] bytesToFind)
/*     */     throws IOException
/*     */   {
/* 104 */     long out = -1L;
/* 105 */     for (long seekPos = this.file.length() - 1L - bytesToFind.length; (seekPos > 3L) && (out == -1L); seekPos -= 1L) {
/* 106 */       byte[] buffer = readByteArray(seekPos, bytesToFind.length);
/* 107 */       if (Arrays.equals(bytesToFind, buffer)) {
/* 108 */         out = seekPos;
/*     */       }
/*     */     }
/* 111 */     return out;
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.ExtRandomAccessFile
 * JD-Core Version:    0.6.0
 */