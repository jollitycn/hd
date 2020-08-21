/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import org.apache.tools.zip.ZipEntry;
/*     */ 
/*     */ public class ExtZipEntry extends ZipEntry
/*     */ {
/*     */   private CentralDirectoryEntry centralDirectoryEntry;
/*     */   protected int flag;
/*     */   protected int offset;
/*     */   protected int primaryCompressionMethod;
/*     */ 
/*     */   public ExtZipEntry(String name)
/*     */   {
/*  20 */     super(name);
/*     */   }
/*     */ 
/*     */   public ExtZipEntry(ExtZipEntry entry)
/*     */   {
/*  25 */     super(entry.getName());
/*  26 */     setCompressedSize(entry.getCompressedSize());
/*  27 */     setSize(entry.getSize());
/*  28 */     setComment(entry.getComment());
/*  29 */     setTime(entry.getTime());
/*  30 */     setMethod(entry.getMethod());
/*     */   }
/*     */ 
/*     */   public ExtZipEntry(String name, CentralDirectoryEntry centralDirectoryEntry) {
/*  34 */     super(name);
/*  35 */     this.centralDirectoryEntry = centralDirectoryEntry;
/*     */   }
/*     */ 
/*     */   public void initEncryptedEntry(boolean utf8Flg) {
/*  39 */     setCrc(0L);
/*     */ 
/*  41 */     this.flag |= 1;
/*     */ 
/*  43 */     if (utf8Flg) {
/*  44 */       this.flag |= 2048;
/*     */     }
/*     */ 
/*  48 */     this.primaryCompressionMethod = 99;
/*     */ 
/*  50 */     byte[] extraBytes = new byte[11];
/*  51 */     extraBytes = new byte[11];
/*     */ 
/*  54 */     extraBytes[0] = 1;
/*  55 */     extraBytes[1] = -103;
/*     */ 
/*  59 */     extraBytes[2] = 7;
/*  60 */     extraBytes[3] = 0;
/*     */ 
/*  63 */     extraBytes[4] = 2;
/*  64 */     extraBytes[5] = 0;
/*     */ 
/*  67 */     extraBytes[6] = 65;
/*  68 */     extraBytes[7] = 69;
/*     */ 
/*  71 */     extraBytes[8] = 3;
/*     */ 
/*  74 */     extraBytes[9] = (byte)(getMethod() & 0xFF);
/*  75 */     extraBytes[10] = (byte)((getMethod() & 0xFF00) >> 8);
/*     */ 
/*  77 */     setExtra(extraBytes);
/*     */   }
/*     */ 
/*     */   public void initEncryptedEntry() {
/*  81 */     setCrc(0L);
/*     */ 
/*  83 */     this.flag |= 1;
/*     */ 
/*  85 */     this.primaryCompressionMethod = 99;
/*     */ 
/*  87 */     byte[] extraBytes = new byte[11];
/*  88 */     extraBytes = new byte[11];
/*     */ 
/*  91 */     extraBytes[0] = 1;
/*  92 */     extraBytes[1] = -103;
/*     */ 
/*  96 */     extraBytes[2] = 7;
/*  97 */     extraBytes[3] = 0;
/*     */ 
/* 100 */     extraBytes[4] = 2;
/* 101 */     extraBytes[5] = 0;
/*     */ 
/* 104 */     extraBytes[6] = 65;
/* 105 */     extraBytes[7] = 69;
/*     */ 
/* 108 */     extraBytes[8] = 3;
/*     */ 
/* 111 */     extraBytes[9] = (byte)(getMethod() & 0xFF);
/* 112 */     extraBytes[10] = (byte)((getMethod() & 0xFF00) >> 8);
/*     */ 
/* 114 */     setExtra(extraBytes);
/*     */   }
/*     */ 
/*     */   public int getFlag()
/*     */   {
/* 120 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public boolean isAesEncrypted() {
/* 124 */     return (isEncrypted()) && (this.centralDirectoryEntry != null) && (this.centralDirectoryEntry.isAesEncrypted());
/*     */   }
/*     */ 
/*     */   public boolean isEncrypted() {
/* 128 */     return (this.flag & 0x1) > 0;
/*     */   }
/*     */ 
/*     */   public int getOffset()
/*     */   {
/* 134 */     return this.offset;
/*     */   }
/*     */ 
/*     */   public void setOffset(int offset) {
/* 138 */     this.offset = offset;
/*     */   }
/*     */ 
/*     */   public int getPrimaryCompressionMethod()
/*     */   {
/* 145 */     return this.primaryCompressionMethod;
/*     */   }
/*     */ 
/*     */   public void setPrimaryCompressionMethod(int primaryCompressionMethod) {
/* 149 */     this.primaryCompressionMethod = primaryCompressionMethod;
/*     */   }
/*     */ 
/*     */   public long getEncryptedDataSize()
/*     */   {
/* 163 */     return getCompressedSize() - 10L - 16L - 2L;
/*     */   }
/*     */ 
/*     */   public CentralDirectoryEntry getCentralDirectoryEntry() {
/* 167 */     return this.centralDirectoryEntry;
/*     */   }
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/* 172 */     if (size < 0L) {
/* 173 */       size &= 4294967295L;
/*     */     }
/* 175 */     super.setSize(size);
/*     */   }
/*     */ 
/*     */   public long getDosTime()
/*     */   {
/* 186 */     return javaToDosTime(getTime());
/*     */   }
/*     */ 
/*     */   public static long javaToDosTime(long javaTime) {
/* 190 */     Date d = new Date(javaTime);
/* 191 */     Calendar ca = Calendar.getInstance();
/* 192 */     ca.setTime(d);
/* 193 */     int year = ca.get(1);
/* 194 */     if (year < 1980) {
/* 195 */       return 2162688L;
/*     */     }
/* 197 */     return year - 1980 << 25 | ca.get(2) + 1 << 21 | 
/* 198 */       ca.get(5) << 16 | 
/* 199 */       ca.get(11) << 11 | 
/* 200 */       ca.get(12) << 5 | ca.get(13) >> 1;
/*     */   }
/*     */ 
/*     */   public static long dosToJavaTime(long dosTime) {
/* 204 */     Calendar ca = Calendar.getInstance();
/* 205 */     ca.set(1, (int)(dosTime >> 25 & 0x7F) + 1980);
/* 206 */     ca.set(2, (int)(dosTime >> 21 & 0xF) - 1);
/* 207 */     ca.set(5, (int)(dosTime >> 16) & 0x1F);
/* 208 */     ca.set(11, (int)(dosTime >> 11) & 0x1F);
/* 209 */     ca.set(12, (int)(dosTime >> 5) & 0x3F);
/* 210 */     ca.set(13, (int)(dosTime << 1) & 0x3E);
/* 211 */     return ca.getTime().getTime();
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.ExtZipEntry
 * JD-Core Version:    0.6.0
 */