/*     */ package de.idyl.winzipaes.impl;
/*     */ 
/*     */ import de.idyl.winzipaes.AesZipFileDecrypter;
/*     */ import java.io.IOException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ public class CentralDirectoryEntry
/*     */   implements ZipConstants
/*     */ {
/*  39 */   private static final Logger LOG = Logger.getLogger(CentralDirectoryEntry.class.getName());
/*     */   protected ExtRandomAccessFile raFile;
/*     */   protected long fileOffset;
/*     */   protected boolean isEncrypted;
/*     */   protected boolean isAesEncrypted;
/*     */   protected short fileNameLength;
/*     */   protected long extraFieldOffset;
/*     */   protected String fileName;
/*     */   protected int localHeaderSize;
/*     */   protected short actualCompressionMethod;
/*     */   protected short extraFieldLength;
/*     */   protected long localHeaderOffset;
/*     */   protected int compressedSize;
/*     */   protected int uncompressedSize;
/*     */ 
/*     */   public CentralDirectoryEntry(ExtRandomAccessFile raFile, long fileOffset)
/*     */     throws IOException
/*     */   {
/*  72 */     this.raFile = raFile;
/*  73 */     this.fileOffset = fileOffset;
/*  74 */     initFromRaFile();
/*     */   }
/*     */ 
/*     */   protected void initFromRaFile() throws IOException
/*     */   {
/*  79 */     int censig = this.raFile.readInt(this.fileOffset);
/*  80 */     if (censig != 33639248L)
/*  81 */       throw new ZipException("expected CENSIC not found in central directory (at end of zip file)");
/*  82 */     if (LOG.isLoggable(Level.FINE)) {
/*  83 */       LOG.fine("found censigOffset=" + this.fileOffset);
/*     */     }
/*     */ 
/*  86 */     short flag = this.raFile.readShort(this.fileOffset + 8L);
/*  87 */     this.isEncrypted = ((flag & 0x1) > 0);
/*     */ 
/*  89 */     this.fileNameLength = this.raFile.readShort(this.fileOffset + 28L);
/*  90 */     byte[] fileNameBytes = this.raFile.readByteArray(this.fileOffset + 46L, this.fileNameLength);
/*  91 */     this.fileName = new String(fileNameBytes, AesZipFileDecrypter.charset);
/*  92 */     if (LOG.isLoggable(Level.FINE)) {
/*  93 */       LOG.fine("fileName = " + this.fileName);
/*     */     }
/*     */ 
/*  96 */     this.extraFieldOffset = (this.fileOffset + 46L + this.fileNameLength);
/*  97 */     this.extraFieldLength = this.raFile.readShort(this.fileOffset + 30L);
/*  98 */     this.localHeaderOffset = this.raFile.readInt(this.fileOffset + 28L + 14L);
/*     */ 
/* 100 */     if (LOG.isLoggable(Level.FINE)) {
/* 101 */       LOG.fine("CDS - extraFieldOffset =" + Long.toHexString(this.extraFieldOffset));
/* 102 */       LOG.fine("CDS - extraFieldLength =" + this.extraFieldLength);
/* 103 */       LOG.fine("CDS - localHeaderOffset=" + Long.toHexString(this.localHeaderOffset));
/*     */     }
/*     */ 
/* 108 */     if (this.isEncrypted) {
/* 109 */       byte[] efhid = this.raFile.readByteArray(this.extraFieldOffset, 2);
/* 110 */       if ((efhid[0] != 1) || (efhid[1] != -103)) {
/* 111 */         this.extraFieldOffset = (this.localHeaderOffset + 30L + this.fileNameLength);
/* 112 */         this.extraFieldLength = this.raFile.readShort(this.localHeaderOffset + 28L);
/* 113 */         if (LOG.isLoggable(Level.FINE)) {
/* 114 */           LOG.fine("local header - extraFieldOffset=" + Long.toHexString(this.extraFieldOffset));
/* 115 */           LOG.fine("local header - extraFieldLength=" + Long.toHexString(this.extraFieldLength));
/*     */         }
/* 117 */         if (this.extraFieldLength == 0) {
/* 118 */           throw new ZipException("extra field is of length 0 - this is probably not a WinZip AES encrypted entry");
/*     */         }
/* 120 */         efhid = this.raFile.readByteArray(this.extraFieldOffset, 2);
/* 121 */         if ((efhid[0] == 1) && (efhid[1] == -103))
/* 122 */           this.isAesEncrypted = true;
/*     */       }
/*     */       else {
/* 125 */         this.isAesEncrypted = true;
/*     */       }
/*     */ 
/* 128 */       if (this.isAesEncrypted) {
/* 129 */         this.actualCompressionMethod = this.raFile.readShort(getExtraFieldOffset() + 9L);
/* 130 */         this.localHeaderSize = (30 + getExtraFieldLength() + getFileNameLength());
/*     */       }
/*     */     }
/*     */ 
/* 134 */     this.compressedSize = (int)this.raFile.readLong(this.fileOffset + 20L);
/*     */ 
/* 136 */     this.uncompressedSize = (int)this.raFile.readLong(this.fileOffset + 24L);
/*     */   }
/*     */ 
/*     */   public int getCompressedSize()
/*     */   {
/* 143 */     return this.compressedSize;
/*     */   }
/*     */ 
/*     */   public int getUncompressedSize() {
/* 147 */     return this.uncompressedSize;
/*     */   }
/*     */ 
/*     */   public boolean isEncrypted() {
/* 151 */     return this.isEncrypted;
/*     */   }
/*     */ 
/*     */   public String getFileName() {
/* 155 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public short getFileNameLength() {
/* 159 */     return this.fileNameLength;
/*     */   }
/*     */ 
/*     */   public short getExtraFieldLength() {
/* 163 */     return this.extraFieldLength;
/*     */   }
/*     */ 
/*     */   public long getLocalHeaderOffset() {
/* 167 */     return this.localHeaderOffset;
/*     */   }
/*     */ 
/*     */   protected long getExtraFieldOffset() {
/* 171 */     return this.extraFieldOffset;
/*     */   }
/*     */ 
/*     */   public int getOffset()
/*     */   {
/* 179 */     return (int)(getLocalHeaderOffset() + getLocalHeaderSize() + getCryptoHeaderLength());
/*     */   }
/*     */ 
/*     */   public short getActualCompressionMethod()
/*     */   {
/* 189 */     return this.actualCompressionMethod;
/*     */   }
/*     */ 
/*     */   public byte getEncryptionStrength()
/*     */     throws IOException
/*     */   {
/* 198 */     return this.raFile.readByte(getExtraFieldOffset() + 8L);
/*     */   }
/*     */ 
/*     */   public int getLocalHeaderSize() {
/* 202 */     return this.localHeaderSize;
/*     */   }
/*     */ 
/*     */   public short getCryptoHeaderLength()
/*     */   {
/* 208 */     return 18;
/*     */   }
/*     */ 
/*     */   public boolean isAesEncrypted() {
/* 212 */     return this.isAesEncrypted;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 217 */     StringBuilder sb = new StringBuilder();
/* 218 */     sb.append("fileName\t\t = ").append(this.fileName).append('\n');
/*     */     try {
/* 220 */       sb.append("uncompressedSize\t = ").append(getUncompressedSize()).append('\n');
/* 221 */       sb.append("compressedSize\t\t = ").append(getCompressedSize()).append('\n');
/* 222 */       sb.append("encryptionStrength\t = ").append(getEncryptionStrength()).append('\n');
/* 223 */       sb.append("extraFieldOffset\t = ").append(getExtraFieldOffset()).append('\n');
/* 224 */       sb.append("extraFieldLength\t = ").append(getExtraFieldLength()).append('\n');
/* 225 */       sb.append("localHeaderOffset\t = ").append(getLocalHeaderOffset()).append('\n');
/* 226 */       sb.append("localHeaderSize\t\t = ").append(getLocalHeaderSize()).append('\n');
/* 227 */       sb.append("offset\t\t\t = ").append(getOffset()).append('\n');
/*     */     }
/*     */     catch (IOException ioEx) {
/* 230 */       LOG.log(Level.WARNING, ioEx.getMessage(), ioEx);
/*     */     }
/* 232 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.CentralDirectoryEntry
 * JD-Core Version:    0.6.0
 */