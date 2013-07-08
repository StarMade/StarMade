package org.jasypt.digest.config;

import java.security.Provider;
import org.jasypt.salt.SaltGenerator;

public abstract interface DigesterConfig
{
  public abstract String getAlgorithm();
  
  public abstract Integer getSaltSizeBytes();
  
  public abstract Integer getIterations();
  
  public abstract SaltGenerator getSaltGenerator();
  
  public abstract String getProviderName();
  
  public abstract Provider getProvider();
  
  public abstract Boolean getInvertPositionOfSaltInMessageBeforeDigesting();
  
  public abstract Boolean getInvertPositionOfPlainSaltInEncryptionResults();
  
  public abstract Boolean getUseLenientSaltSizeCheck();
  
  public abstract Integer getPoolSize();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.DigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */