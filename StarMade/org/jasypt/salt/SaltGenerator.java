package org.jasypt.salt;

public abstract interface SaltGenerator
{
  public abstract byte[] generateSalt(int paramInt);

  public abstract boolean includePlainSaltInEncryptionResults();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.SaltGenerator
 * JD-Core Version:    0.6.2
 */