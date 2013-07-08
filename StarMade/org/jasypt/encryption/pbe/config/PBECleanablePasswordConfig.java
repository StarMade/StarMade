package org.jasypt.encryption.pbe.config;

public abstract interface PBECleanablePasswordConfig
{
  public abstract char[] getPasswordCharArray();
  
  public abstract void cleanPassword();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.PBECleanablePasswordConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */