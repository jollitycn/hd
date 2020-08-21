/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.zip.ZipEntry;
/*     */ 
/*     */ public class ExtZipOutputStream
/*     */   implements ZipConstants
/*     */ {
/*     */   protected String comment;
/*     */   protected OutputStream out;
/*  31 */   protected String encoding = "iso-8859-1";
/*     */ 
/*  33 */   public boolean utf8Flg = false;
/*     */   protected int written;
/*     */   protected static final short ZIP_VERSION = 20;
/* 119 */   private List<ExtZipEntry> entries = new ArrayList();
/*     */ 
/*     */   public ExtZipOutputStream(File file)
/*     */     throws IOException
/*     */   {
/*  20 */     this.out = new FileOutputStream(file);
/*     */   }
/*     */ 
/*     */   public ExtZipOutputStream(OutputStream out) {
/*  24 */     this.out = out;
/*     */   }
/*     */ 
/*     */   public int getWritten()
/*     */   {
/*  39 */     return this.written;
/*     */   }
/*     */ 
/*     */   public void setEncoding(String encoding) {
/*  43 */     this.encoding = encoding;
/*  44 */     this.utf8Flg |= isUTF8(encoding);
/*     */   }
/*     */ 
/*     */   public void writeBytes(byte[] b) throws IOException {
/*  48 */     this.out.write(b);
/*  49 */     this.written += b.length;
/*     */   }
/*     */ 
/*     */   public void writeShort(int v) throws IOException {
/*  53 */     this.out.write(v >>> 0 & 0xFF);
/*  54 */     this.out.write(v >>> 8 & 0xFF);
/*  55 */     this.written += 2;
/*     */   }
/*     */ 
/*     */   public void writeInt(long v) throws IOException {
/*  59 */     this.out.write((int)(v >>> 0 & 0xFF));
/*  60 */     this.out.write((int)(v >>> 8 & 0xFF));
/*  61 */     this.out.write((int)(v >>> 16 & 0xFF));
/*  62 */     this.out.write((int)(v >>> 24 & 0xFF));
/*  63 */     this.written += 4;
/*     */   }
/*     */ 
/*     */   public void writeBytes(byte[] b, int off, int len) throws IOException {
/*  67 */     this.out.write(b, off, len);
/*  68 */     this.written += len;
/*     */   }
/*     */ 
/*     */   protected boolean isUTF8(String encoding)
/*     */   {
/*  74 */     if (encoding == null)
/*     */     {
/*  76 */       encoding = System.getProperty("file.encoding");
/*     */     }
/*     */ 
/*  79 */     return ("UTF8".equalsIgnoreCase(encoding)) || 
/*  79 */       ("UTF-8".equalsIgnoreCase(encoding));
/*     */   }
/*     */ 
/*     */   protected void writeFileInfo(ExtZipEntry entry)
/*     */     throws IOException
/*     */   {
/*  86 */     writeShort(20);
/*     */ 
/*  89 */     writeShort(entry.getFlag());
/*     */ 
/*  91 */     writeShort(entry.getPrimaryCompressionMethod());
/*     */ 
/*  95 */     writeInt(entry.getDosTime());
/*     */ 
/*  98 */     writeInt(entry.getCrc());
/*     */ 
/* 103 */     writeInt((int)entry.getCompressedSize());
/* 104 */     writeInt((int)entry.getSize());
/*     */ 
/* 106 */     writeShort(entry.getName().getBytes(this.encoding).length);
/*     */ 
/* 112 */     if (entry.getExtra() != null)
/* 113 */       writeShort(entry.getExtra().length);
/*     */     else
/* 115 */       writeShort(0);
/*     */   }
/*     */ 
/*     */   protected void writeDirEntry(ExtZipEntry entry)
/*     */     throws IOException
/*     */   {
/* 122 */     writeInt(33639248L);
/*     */ 
/* 124 */     writeShort(20);
/* 125 */     writeFileInfo(entry);
/*     */ 
/* 127 */     writeShort(0);
/* 128 */     writeShort(0);
/* 129 */     writeShort(0);
/* 130 */     writeInt(0L);
/*     */ 
/* 132 */     writeInt(entry.getOffset());
/*     */ 
/* 134 */     writeBytes(entry.getName().getBytes(this.encoding));
/*     */ 
/* 136 */     writeExtraBytes(entry);
/*     */   }
/*     */ 
/*     */   protected void writeExtraBytes(ZipEntry entry) throws IOException {
/* 140 */     byte[] extraBytes = entry.getExtra();
/* 141 */     if (extraBytes != null)
/* 142 */       writeBytes(extraBytes);
/*     */   }
/*     */ 
/*     */   public void putNextEntry(ExtZipEntry entry)
/*     */     throws IOException
/*     */   {
/* 149 */     this.entries.add(entry);
/*     */ 
/* 151 */     entry.setOffset(this.written);
/*     */ 
/* 154 */     writeInt(67324752L);
/*     */ 
/* 156 */     writeFileInfo(entry);
/*     */ 
/* 158 */     writeBytes(entry.getName().getBytes(this.encoding));
/*     */ 
/* 162 */     writeExtraBytes(entry);
/*     */   }
/*     */ 
/*     */   public void finish()
/*     */     throws IOException
/*     */   {
/* 169 */     int dirOffset = this.written;
/*     */ 
/* 172 */     int startOfCentralDirectory = this.written;
/*     */ 
/* 174 */     Iterator it = this.entries.iterator();
/* 175 */     while (it.hasNext()) {
/* 176 */       ExtZipEntry entry = (ExtZipEntry)it.next();
/* 177 */       writeDirEntry(entry);
/*     */     }
/* 179 */     int centralDirectorySize = this.written - startOfCentralDirectory;
/*     */ 
/* 181 */     writeInt(101010256L);
/*     */ 
/* 183 */     writeShort(0);
/* 184 */     writeShort(0);
/*     */ 
/* 186 */     writeShort(this.entries.size());
/* 187 */     writeShort(this.entries.size());
/*     */ 
/* 189 */     writeInt(centralDirectorySize);
/*     */ 
/* 191 */     writeInt(dirOffset);
/*     */ 
/* 193 */     byte[] commentBytes = this.comment != null ? this.comment.getBytes() : new byte[0];
/* 194 */     writeShort(commentBytes.length);
/* 195 */     if (commentBytes.length > 0) {
/* 196 */       writeBytes(commentBytes);
/*     */     }
/*     */ 
/* 199 */     this.out.close();
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 203 */     this.out.close();
/*     */   }
/*     */ 
/*     */   public String getComment() {
/* 207 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment) {
/* 211 */     this.comment = comment;
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.ExtZipOutputStream
 * JD-Core Version:    0.6.0
 */