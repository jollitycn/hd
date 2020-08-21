package de.idyl.winzipaes.impl;

public class AESCryptoBase
{
  public static final int KEY_SIZE_BIT = 256;
  public static final int KEY_SIZE_BYTE = 32;
  public static final int ITERATION_COUNT = 1000;
  protected byte[] saltBytes;
  protected byte[] cryptoKeyBytes;
  protected byte[] authenticationCodeBytes;
  protected byte[] pwVerificationBytes;
  protected int blockSize;
  protected int nonce;
}

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESCryptoBase
 * JD-Core Version:    0.6.0
 */