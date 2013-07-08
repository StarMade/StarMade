package org.jasypt.encryption.pbe.config;

import java.security.Provider;
import org.jasypt.salt.SaltGenerator;

public abstract interface PBEConfig
{
  public abstract String getAlgorithm();
  
  public abstract String getPassword();
  
  public abstract Integer getKeyObtentionIterations();
  
  public abstract SaltGenerator getSaltGenerator();
  
  public abstract String getProviderName();
  
  public abstract Provider getProvider();
  
  public abstract Integer getPoolSize();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.config.PBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */