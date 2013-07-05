package org.jasypt.encryption;

import java.math.BigInteger;

public abstract interface BigIntegerEncryptor
{
  public abstract BigInteger encrypt(BigInteger paramBigInteger);

  public abstract BigInteger decrypt(BigInteger paramBigInteger);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.BigIntegerEncryptor
 * JD-Core Version:    0.6.2
 */