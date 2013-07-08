package org.jasypt.salt;

public abstract interface SaltGenerator
{
  public abstract byte[] generateSalt(int paramInt);
  
  public abstract boolean includePlainSaltInEncryptionResults();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.salt.SaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */