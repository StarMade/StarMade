package org.jasypt.util.numeric;

import java.math.BigInteger;

public abstract interface IntegerNumberEncryptor
{
  public abstract BigInteger encrypt(BigInteger paramBigInteger);
  
  public abstract BigInteger decrypt(BigInteger paramBigInteger);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.IntegerNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */