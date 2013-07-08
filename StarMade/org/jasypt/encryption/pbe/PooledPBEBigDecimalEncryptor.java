package org.jasypt.encryption.pbe;

import java.math.BigDecimal;
import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.salt.SaltGenerator;

public final class PooledPBEBigDecimalEncryptor
  implements PBEBigDecimalCleanablePasswordEncryptor
{
  private final StandardPBEBigDecimalEncryptor firstEncryptor = new StandardPBEBigDecimalEncryptor();
  private PBEConfig config = null;
  private int poolSize = 0;
  private boolean poolSizeSet = false;
  private StandardPBEBigDecimalEncryptor[] pool;
  private int roundRobin = 0;
  private boolean initialized = false;
  
  public synchronized void setConfig(PBEConfig config)
  {
    this.firstEncryptor.setConfig(config);
    this.config = config;
  }
  
  public void setAlgorithm(String algorithm)
  {
    this.firstEncryptor.setAlgorithm(algorithm);
  }
  
  public void setPassword(String password)
  {
    this.firstEncryptor.setPassword(password);
  }
  
  public synchronized void setPasswordCharArray(char[] password)
  {
    this.firstEncryptor.setPasswordCharArray(password);
  }
  
  public void setKeyObtentionIterations(int keyObtentionIterations)
  {
    this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
  }
  
  public void setSaltGenerator(SaltGenerator saltGenerator)
  {
    this.firstEncryptor.setSaltGenerator(saltGenerator);
  }
  
  public void setProviderName(String providerName)
  {
    this.firstEncryptor.setProviderName(providerName);
  }
  
  public void setProvider(Provider provider)
  {
    this.firstEncryptor.setProvider(provider);
  }
  
  public synchronized void setPoolSize(int poolSize)
  {
    CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.poolSize = poolSize;
    this.poolSizeSet = true;
  }
  
  public boolean isInitialized()
  {
    return this.initialized;
  }
  
  public synchronized void initialize()
  {
    if (!this.initialized)
    {
      if (this.config != null)
      {
        Integer configPoolSize = this.config.getPoolSize();
        this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
      }
      if (this.poolSize <= 0) {
        throw new IllegalArgumentException("Pool size must be set and > 0");
      }
      this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
      this.initialized = true;
    }
  }
  
  public BigDecimal encrypt(BigDecimal message)
  {
    if (!isInitialized()) {
      initialize();
    }
    int poolPosition;
    synchronized (this)
    {
      poolPosition = this.roundRobin;
      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
    }
    return this.pool[poolPosition].encrypt(message);
  }
  
  public BigDecimal decrypt(BigDecimal encryptedMessage)
  {
    if (!isInitialized()) {
      initialize();
    }
    int poolPosition;
    synchronized (this)
    {
      poolPosition = this.roundRobin;
      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
    }
    return this.pool[poolPosition].decrypt(encryptedMessage);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEBigDecimalEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */