package de.idyl.winzipaes.impl;

import java.util.zip.ZipException;

public abstract interface AESEncrypter
{
  public abstract void init(String paramString, int paramInt)
    throws ZipException;

  public abstract void encrypt(byte[] paramArrayOfByte, int paramInt);

  public abstract byte[] getSalt();

  public abstract byte[] getPwVerification();

  public abstract byte[] getFinalAuthentication();
}

/* Location:           D:\Backup\Downloads\operateZip\lib\winzipaes.jar
 * Qualified Name:     de.idyl.winzipaes.impl.AESEncrypter
 * JD-Core Version:    0.6.0
 */