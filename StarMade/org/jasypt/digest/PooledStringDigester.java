package org.jasypt.digest;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.config.DigesterConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.salt.SaltGenerator;

public class PooledStringDigester
  implements StringDigester
{
  private final StandardStringDigester firstDigester = new StandardStringDigester();
  private DigesterConfig config = null;
  private int poolSize = 0;
  private boolean poolSizeSet = false;
  private StandardStringDigester[] pool;
  private int roundRobin = 0;
  private boolean initialized = false;
  
  public synchronized void setConfig(DigesterConfig config)
  {
    this.firstDigester.setConfig(config);
    this.config = config;
  }
  
  public synchronized void setAlgorithm(String algorithm)
  {
    this.firstDigester.setAlgorithm(algorithm);
  }
  
  public synchronized void setSaltSizeBytes(int saltSizeBytes)
  {
    this.firstDigester.setSaltSizeBytes(saltSizeBytes);
  }
  
  public synchronized void setIterations(int iterations)
  {
    this.firstDigester.setIterations(iterations);
  }
  
  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
  {
    this.firstDigester.setSaltGenerator(saltGenerator);
  }
  
  public synchronized void setProviderName(String providerName)
  {
    this.firstDigester.setProviderName(providerName);
  }
  
  public synchronized void setProvider(Provider provider)
  {
    this.firstDigester.setProvider(provider);
  }
  
  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
  {
    this.firstDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
  }
  
  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
  {
    this.firstDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
  }
  
  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
  {
    this.firstDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
  }
  
  public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored)
  {
    this.firstDigester.setUnicodeNormalizationIgnored(unicodeNormalizationIgnored);
  }
  
  public synchronized void setStringOutputType(String stringOutputType)
  {
    this.firstDigester.setStringOutputType(stringOutputType);
  }
  
  public synchronized void setPrefix(String prefix)
  {
    this.firstDigester.setPrefix(prefix);
  }
  
  public synchronized void setSuffix(String suffix)
  {
    this.firstDigester.setSuffix(suffix);
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
      this.pool = new StandardStringDigester[this.poolSize];
      this.pool[0] = this.firstDigester;
      for (int configPoolSize = 1; configPoolSize < this.poolSize; configPoolSize++) {
        this.pool[configPoolSize] = this.pool[(configPoolSize - 1)].cloneDigester();
      }
      this.initialized = true;
    }
  }
  
  public String digest(String message)
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
    return this.pool[poolPosition].digest(message);
  }
  
  public boolean matches(String message, String digest)
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
    return this.pool[poolPosition].matches(message, digest);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.PooledStringDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */