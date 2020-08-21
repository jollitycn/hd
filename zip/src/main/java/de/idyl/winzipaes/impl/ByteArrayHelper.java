/*    */ package de.idyl.winzipaes.impl;
/*    */ 
/*    */ public class ByteArrayHelper
/*    */ {
/*    */   public static long toLong(byte[] in)
/*    */   {
/* 11 */     long out = 0L;
/* 12 */     for (int i = in.length - 1; i > 0; i--) {
/* 13 */       out |= in[i] & 0xFF;
/* 14 */       out <<= 8;
/*    */     }
/* 16 */     out |= in[0] & 0xFF;
/* 17 */     return out;
/*    */   }
/*    */ 
/*    */   public static int toInt(byte[] in) {
/* 21 */     int out = 0;
/* 22 */     for (int i = in.length - 1; i > 0; i--) {
/* 23 */       out |= in[i] & 0xFF;
/* 24 */       out <<= 8;
/*    */     }
/* 26 */     out |= in[0] & 0xFF;
/* 27 */     return out;
/*    */   }
/*    */ 
/*    */   public static short toShort(byte[] in) {
/* 31 */     short out = 0;
/* 32 */     for (int i = in.length - 1; i > 0; i--) {
/* 33 */       out = (short)(out | in[i] & 0xFF);
/* 34 */       out = (short)(out << 8);
/*    */     }
/* 36 */     out = (short)(out | in[0] & 0xFF);
/* 37 */     return out;
/*    */   }
/*    */ 
/*    */   public static byte[] toByteArray(int in) {
/* 41 */     byte[] out = new byte[4];
/*    */ 
/* 43 */     out[0] = (byte)in;
/* 44 */     out[1] = (byte)(in >> 8);
/* 45 */     out[2] = (byte)(in >> 16);
/* 46 */     out[3] = (byte)(in >> 24);
/*    */ 
/* 48 */     return out;
/*    */   }
/*    */ 
/*    */   public static byte[] toByteArray(int in, int outSize) {
/* 52 */     byte[] out = new byte[outSize];
/* 53 */     byte[] intArray = toByteArray(in);
/* 54 */     for (int i = 0; (i < intArray.length) && (i < outSize); i++) {
/* 55 */       out[i] = intArray[i];
/*    */     }
/* 57 */     return out;
/*    */   }
/*    */ 
/*    */   public static String toString(byte[] theByteArray) {
/* 61 */     StringBuffer out = new StringBuffer();
/* 62 */     for (int i = 0; i < theByteArray.length; i++) {
/* 63 */       String s = Integer.toHexString(theByteArray[i] & 0xFF);
/* 64 */       if (s.length() < 2) {
/* 65 */         out.append('0');
/*    */       }
/* 67 */       out.append(s).append(' ');
/*    */     }
/* 69 */     return out.toString();
/*    */   }
/*    */ 
/*    */   public static boolean isEqual(byte[] first, byte[] second) {
/* 73 */     boolean out = (first != null) && (second != null) && (first.length == second.length);
/* 74 */     for (int i = 0; (out) && (i < first.length); i++) {
/* 75 */       if (first[i] != second[i]) {
/* 76 */         out = false;
/*    */       }
/*    */     }
/* 79 */     return out;
/*    */   }
/*    */ }

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.ByteArrayHelper
 * JD-Core Version:    0.6.0
 */