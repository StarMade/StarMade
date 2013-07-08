package org.jasypt.digest.config;

import java.security.Provider;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.salt.SaltGenerator;

public class SimpleDigesterConfig
  implements DigesterConfig
{
  private String algorithm = null;
  private Integer iterations = null;
  private Integer saltSizeBytes = null;
  private SaltGenerator saltGenerator = null;
  private String providerName = null;
  private Provider provider = null;
  private Boolean invertPositionOfSaltInMessageBeforeDigesting = null;
  private Boolean invertPositionOfPlainSaltInEncryptionResults = null;
  private Boolean useLenientSaltSizeCheck = null;
  private Integer poolSize = null;
  
  public void setAlgorithm(String algorithm)
  {
    this.algorithm = algorithm;
  }
  
  public void setIterations(Integer iterations)
  {
    this.iterations = iterations;
  }
  
  public void setIterations(String iterations)
  {
    if (iterations != null) {
      try
      {
        this.iterations = new Integer(iterations);
      }
      catch (NumberFormatException local_e)
      {
        throw new EncryptionInitializationException(local_e);
      }
    } else {
      this.iterations = null;
    }
  }
  
  public void setSaltSizeBytes(Integer saltSizeBytes)
  {
    this.saltSizeBytes = saltSizeBytes;
  }
  
  public void setSaltSizeBytes(String saltSizeBytes)
  {
    if (saltSizeBytes != null) {
      try
      {
        this.saltSizeBytes = new Integer(saltSizeBytes);
      }
      catch (NumberFormatException local_e)
      {
        throw new EncryptionInitializationException(local_e);
      }
    } else {
      this.saltSizeBytes = null;
    }
  }
  
  public void setSaltGenerator(SaltGenerator saltGenerator)
  {
    this.saltGenerator = saltGenerator;
  }
  
  public void setSaltGeneratorClassName(String saltGeneratorClassName)
  {
    if (saltGeneratorClassName != null) {
      try
      {
        Class saltGeneratorClass = Thread.currentThread().getContextClassLoader().loadClass(saltGeneratorClassName);
        this.saltGenerator = ((SaltGenerator)saltGeneratorClass.newInstance());
      }
      catch (Exception saltGeneratorClass)
      {
        throw new EncryptionInitializationException(saltGeneratorClass);
      }
    } else {
      this.saltGenerator = null;
    }
  }
  
  public void setProviderName(String providerName)
  {
    this.providerName = providerName;
  }
  
  public void setProvider(Provider provider)
  {
    this.provider = provider;
  }
  
  public void setProviderClassName(String providerClassName)
  {
    if (providerClassName != null) {
      try
      {
        Class providerClass = Thread.currentThread().getContextClassLoader().loadClass(providerClassName);
        this.provider = ((Provider)providerClass.newInstance());
      }
      catch (Exception providerClass)
      {
        throw new EncryptionInitializationException(providerClass);
      }
    } else {
      this.provider = null;
    }
  }
  
  public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting)
  {
    this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
  }
  
  public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults)
  {
    this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
  }
  
  public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck)
  {
    this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
  }
  
  public void setPoolSize(Integer poolSize)
  {
    this.poolSize = poolSize;
  }
  
  public void setPoolSize(String poolSize)
  {
    if (poolSize != null) {
      try
      {
        this.poolSize = new Integer(poolSize);
      }
      catch (NumberFormatException local_e)
      {
        throw new EncryptionInitializationException(local_e);
      }
    } else {
      this.poolSize = null;
    }
  }
  
  public String getAlgorithm()
  {
    return this.algorithm;
  }
  
  public Integer getIterations()
  {
    return this.iterations;
  }
  
  public Integer getSaltSizeBytes()
  {
    return this.saltSizeBytes;
  }
  
  public SaltGenerator getSaltGenerator()
  {
    return this.saltGenerator;
  }
  
  public String getProviderName()
  {
    return this.providerName;
  }
  
  public Provider getProvider()
  {
    return this.provider;
  }
  
  public Boolean getInvertPositionOfSaltInMessageBeforeDigesting()
  {
    return this.invertPositionOfSaltInMessageBeforeDigesting;
  }
  
  public Boolean getInvertPositionOfPlainSaltInEncryptionResults()
  {
    return this.invertPositionOfPlainSaltInEncryptionResults;
  }
  
  public Boolean getUseLenientSaltSizeCheck()
  {
    return this.useLenientSaltSizeCheck;
  }
  
  public Integer getPoolSize()
  {
    return this.poolSize;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.config.SimpleDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */