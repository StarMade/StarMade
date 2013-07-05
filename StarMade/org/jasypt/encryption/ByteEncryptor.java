package org.jasypt.encryption;

public abstract interface ByteEncryptor
{
  public abstract byte[] encrypt(byte[] paramArrayOfByte);

  public abstract byte[] decrypt(byte[] paramArrayOfByte);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.ByteEncryptor
 * JD-Core Version:    0.6.2
 */