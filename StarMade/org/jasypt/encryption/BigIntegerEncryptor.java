package org.jasypt.encryption;

import java.math.BigInteger;

public abstract interface BigIntegerEncryptor
{
  public abstract BigInteger encrypt(BigInteger paramBigInteger);
  
  public abstract BigInteger decrypt(BigInteger paramBigInteger);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.BigIntegerEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */