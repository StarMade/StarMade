package org.jasypt.util.binary;

public abstract interface BinaryEncryptor
{
  public abstract byte[] encrypt(byte[] paramArrayOfByte);
  
  public abstract byte[] decrypt(byte[] paramArrayOfByte);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.binary.BinaryEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */