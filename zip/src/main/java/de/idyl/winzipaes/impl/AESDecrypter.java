package de.idyl.winzipaes.impl;

import java.util.zip.ZipException;

public abstract interface AESDecrypter
{
  public abstract void init(String paramString, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws ZipException;

  public abstract void decrypt(byte[] paramArrayOfByte, int paramInt);

  public abstract byte[] getFinalAuthentication();
}

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESDecrypter
 * JD-Core Version:    0.6.0
 */