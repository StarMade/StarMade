package org.jasypt.digest;

public abstract interface ByteDigester
{
  public abstract byte[] digest(byte[] paramArrayOfByte);
  
  public abstract boolean matches(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.ByteDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */