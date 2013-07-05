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

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.PBEConfig
 * JD-Core Version:    0.6.2
 */