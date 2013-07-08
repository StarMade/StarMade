package org.jasypt.digest.config;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.salt.SaltGenerator;

public class EnvironmentDigesterConfig
  extends SimpleDigesterConfig
{
  private String algorithmEnvName = null;
  private String iterationsEnvName = null;
  private String saltSizeBytesEnvName = null;
  private String saltGeneratorClassNameEnvName = null;
  private String providerNameEnvName = null;
  private String providerClassNameEnvName = null;
  private String invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
  private String invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
  private String useLenientSaltSizeCheckEnvName = null;
  private String poolSizeEnvName = null;
  private String algorithmSysPropertyName = null;
  private String iterationsSysPropertyName = null;
  private String saltSizeBytesSysPropertyName = null;
  private String saltGeneratorClassNameSysPropertyName = null;
  private String providerNameSysPropertyName = null;
  private String providerClassNameSysPropertyName = null;
  private String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
  private String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
  private String useLenientSaltSizeCheckSysPropertyName = null;
  private String poolSizeSysPropertyName = null;
  
  public String getAlgorithmEnvName()
  {
    return this.algorithmEnvName;
  }
  
  public void setAlgorithmEnvName(String algorithmEnvName)
  {
    this.algorithmEnvName = algorithmEnvName;
    if (algorithmEnvName == null)
    {
      super.setAlgorithm(null);
    }
    else
    {
      this.algorithmSysPropertyName = null;
      super.setAlgorithm(System.getenv(algorithmEnvName));
    }
  }
  
  public String getAlgorithmSysPropertyName()
  {
    return this.algorithmSysPropertyName;
  }
  
  public void setAlgorithmSysPropertyName(String algorithmSysPropertyName)
  {
    this.algorithmSysPropertyName = algorithmSysPropertyName;
    if (algorithmSysPropertyName == null)
    {
      super.setAlgorithm(null);
    }
    else
    {
      this.algorithmEnvName = null;
      super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
    }
  }
  
  public String getIterationsEnvName()
  {
    return this.iterationsEnvName;
  }
  
  public void setIterationsEnvName(String iterationsEnvName)
  {
    this.iterationsEnvName = iterationsEnvName;
    if (iterationsEnvName == null)
    {
      super.setIterations((String)null);
    }
    else
    {
      this.iterationsSysPropertyName = null;
      super.setIterations(System.getenv(iterationsEnvName));
    }
  }
  
  public String getIterationsSysPropertyName()
  {
    return this.iterationsSysPropertyName;
  }
  
  public void setIterationsSysPropertyName(String iterationsSysPropertyName)
  {
    this.iterationsSysPropertyName = iterationsSysPropertyName;
    if (iterationsSysPropertyName == null)
    {
      super.setIterations((String)null);
    }
    else
    {
      this.iterationsEnvName = null;
      super.setIterations(System.getProperty(iterationsSysPropertyName));
    }
  }
  
  public String getSaltSizeBytesEnvName()
  {
    return this.saltSizeBytesEnvName;
  }
  
  public void setSaltSizeBytesEnvName(String saltSizeBytesEnvName)
  {
    this.saltSizeBytesEnvName = saltSizeBytesEnvName;
    if (saltSizeBytesEnvName == null)
    {
      super.setSaltSizeBytes((String)null);
    }
    else
    {
      this.saltSizeBytesSysPropertyName = null;
      super.setSaltSizeBytes(System.getenv(saltSizeBytesEnvName));
    }
  }
  
  public String getSaltSizeBytesSysPropertyName()
  {
    return this.saltSizeBytesSysPropertyName;
  }
  
  public void setSaltSizeBytesSysPropertyName(String saltSizeBytesSysPropertyName)
  {
    this.saltSizeBytesSysPropertyName = saltSizeBytesSysPropertyName;
    if (saltSizeBytesSysPropertyName == null)
    {
      super.setSaltSizeBytes((Integer)null);
    }
    else
    {
      this.saltSizeBytesEnvName = null;
      super.setSaltSizeBytes(System.getProperty(saltSizeBytesSysPropertyName));
    }
  }
  
  public String getSaltGeneratorClassNameEnvName()
  {
    return this.saltGeneratorClassNameEnvName;
  }
  
  public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName)
  {
    this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
    if (saltGeneratorClassNameEnvName == null)
    {
      super.setSaltGenerator(null);
    }
    else
    {
      this.saltGeneratorClassNameSysPropertyName = null;
      String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
      super.setSaltGeneratorClassName(saltGeneratorClassName);
    }
  }
  
  public String getSaltGeneratorClassNameSysPropertyName()
  {
    return this.saltGeneratorClassNameSysPropertyName;
  }
  
  public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName)
  {
    this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
    if (saltGeneratorClassNameSysPropertyName == null)
    {
      super.setSaltGenerator(null);
    }
    else
    {
      this.saltGeneratorClassNameEnvName = null;
      String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
      super.setSaltGeneratorClassName(saltGeneratorClassName);
    }
  }
  
  public String getProviderNameEnvName()
  {
    return this.providerNameEnvName;
  }
  
  public void setProviderNameEnvName(String providerNameEnvName)
  {
    this.providerNameEnvName = providerNameEnvName;
    if (providerNameEnvName == null)
    {
      super.setProviderName(null);
    }
    else
    {
      this.providerNameSysPropertyName = null;
      super.setProviderName(System.getenv(providerNameEnvName));
    }
  }
  
  public String getProviderNameSysPropertyName()
  {
    return this.providerNameSysPropertyName;
  }
  
  public void setProviderNameSysPropertyName(String providerNameSysPropertyName)
  {
    this.providerNameSysPropertyName = providerNameSysPropertyName;
    if (providerNameSysPropertyName == null)
    {
      super.setProviderName(null);
    }
    else
    {
      this.providerNameEnvName = null;
      super.setProviderName(System.getProperty(providerNameSysPropertyName));
    }
  }
  
  public String getProviderClassNameEnvName()
  {
    return this.providerClassNameEnvName;
  }
  
  public void setProviderClassNameEnvName(String providerClassNameEnvName)
  {
    this.providerClassNameEnvName = providerClassNameEnvName;
    if (providerClassNameEnvName == null)
    {
      super.setProvider(null);
    }
    else
    {
      this.providerClassNameSysPropertyName = null;
      String providerClassName = System.getenv(providerClassNameEnvName);
      super.setProviderClassName(providerClassName);
    }
  }
  
  public String getProviderClassNameSysPropertyName()
  {
    return this.providerClassNameSysPropertyName;
  }
  
  public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName)
  {
    this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
    if (providerClassNameSysPropertyName == null)
    {
      super.setProvider(null);
    }
    else
    {
      this.providerClassNameEnvName = null;
      String providerClassName = System.getProperty(providerClassNameSysPropertyName);
      super.setProviderClassName(providerClassName);
    }
  }
  
  public String getInvertPositionOfSaltInMessageBeforeDigestingEnvName()
  {
    return this.invertPositionOfSaltInMessageBeforeDigestingEnvName;
  }
  
  public void setInvertPositionOfSaltInMessageBeforeDigestingEnvName(String invertPositionOfSaltInMessageBeforeDigestingEnvName)
  {
    this.invertPositionOfSaltInMessageBeforeDigestingEnvName = invertPositionOfSaltInMessageBeforeDigestingEnvName;
    if (invertPositionOfSaltInMessageBeforeDigestingEnvName == null)
    {
      super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
    }
    else
    {
      this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
      super.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(System.getenv(invertPositionOfSaltInMessageBeforeDigestingEnvName)));
    }
  }
  
  public String getInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName()
  {
    return this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
  }
  
  public void setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)
  {
    this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
    if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName == null)
    {
      super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
    }
    else
    {
      this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
      super.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(System.getProperty(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)));
    }
  }
  
  public String getInvertPositionOfPlainSaltInEncryptionResultsEnvName()
  {
    return this.invertPositionOfPlainSaltInEncryptionResultsEnvName;
  }
  
  public void setInvertPositionOfPlainSaltInEncryptionResultsEnvName(String invertPositionOfPlainSaltInEncryptionResultsEnvName)
  {
    this.invertPositionOfPlainSaltInEncryptionResultsEnvName = invertPositionOfPlainSaltInEncryptionResultsEnvName;
    if (invertPositionOfPlainSaltInEncryptionResultsEnvName == null)
    {
      super.setInvertPositionOfPlainSaltInEncryptionResults(null);
    }
    else
    {
      this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
      super.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(System.getenv(invertPositionOfPlainSaltInEncryptionResultsEnvName)));
    }
  }
  
  public String getInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName()
  {
    return this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
  }
  
  public void setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)
  {
    this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
    if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName == null)
    {
      super.setInvertPositionOfPlainSaltInEncryptionResults(null);
    }
    else
    {
      this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
      super.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(System.getProperty(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)));
    }
  }
  
  public String getUseLenientSaltSizeCheckEnvName()
  {
    return this.useLenientSaltSizeCheckEnvName;
  }
  
  public void setUseLenientSaltSizeCheckEnvName(String useLenientSaltSizeCheckEnvName)
  {
    this.useLenientSaltSizeCheckEnvName = useLenientSaltSizeCheckEnvName;
    if (useLenientSaltSizeCheckEnvName == null)
    {
      super.setUseLenientSaltSizeCheck(null);
    }
    else
    {
      this.useLenientSaltSizeCheckSysPropertyName = null;
      super.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(System.getenv(useLenientSaltSizeCheckEnvName)));
    }
  }
  
  public String getUseLenientSaltSizeCheckSysPropertyName()
  {
    return this.useLenientSaltSizeCheckSysPropertyName;
  }
  
  public void setUseLenientSaltSizeCheckSysPropertyName(String useLenientSaltSizeCheckSysPropertyName)
  {
    this.useLenientSaltSizeCheckSysPropertyName = useLenientSaltSizeCheckSysPropertyName;
    if (useLenientSaltSizeCheckSysPropertyName == null)
    {
      super.setUseLenientSaltSizeCheck(null);
    }
    else
    {
      this.useLenientSaltSizeCheckEnvName = null;
      super.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(System.getProperty(useLenientSaltSizeCheckSysPropertyName)));
    }
  }
  
  public String getPoolSizeEnvName()
  {
    return this.poolSizeEnvName;
  }
  
  public void setPoolSizeEnvName(String poolSizeEnvName)
  {
    this.poolSizeEnvName = poolSizeEnvName;
    if (poolSizeEnvName == null)
    {
      super.setPoolSize((String)null);
    }
    else
    {
      this.poolSizeSysPropertyName = null;
      super.setPoolSize(System.getenv(poolSizeEnvName));
    }
  }
  
  public String getPoolSizeSysPropertyName()
  {
    return this.poolSizeSysPropertyName;
  }
  
  public void setPoolSizeSysPropertyName(String poolSizeSysPropertyName)
  {
    this.poolSizeSysPropertyName = poolSizeSysPropertyName;
    if (poolSizeSysPropertyName == null)
    {
      super.setPoolSize((String)null);
    }
    else
    {
      this.poolSizeEnvName = null;
      super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
    }
  }
  
  public void setAlgorithm(String algorithm)
  {
    this.algorithmEnvName = null;
    this.algorithmSysPropertyName = null;
    super.setAlgorithm(algorithm);
  }
  
  public void setIterations(Integer iterations)
  {
    this.iterationsEnvName = null;
    this.iterationsSysPropertyName = null;
    super.setIterations(iterations);
  }
  
  public void setIterations(String iterations)
  {
    this.iterationsEnvName = null;
    this.iterationsSysPropertyName = null;
    super.setIterations(iterations);
  }
  
  public void setSaltSizeBytes(Integer saltSizeBytes)
  {
    this.saltSizeBytesEnvName = null;
    this.saltSizeBytesSysPropertyName = null;
    super.setSaltSizeBytes(saltSizeBytes);
  }
  
  public void setSaltSizeBytes(String saltSizeBytes)
  {
    this.saltSizeBytesEnvName = null;
    this.saltSizeBytesSysPropertyName = null;
    super.setSaltSizeBytes(saltSizeBytes);
  }
  
  public void setSaltGenerator(SaltGenerator saltGenerator)
  {
    this.saltGeneratorClassNameEnvName = null;
    this.saltGeneratorClassNameSysPropertyName = null;
    super.setSaltGenerator(saltGenerator);
  }
  
  public void setSaltGeneratorClassName(String saltGeneratorClassName)
  {
    this.saltGeneratorClassNameEnvName = null;
    this.saltGeneratorClassNameSysPropertyName = null;
    super.setSaltGeneratorClassName(saltGeneratorClassName);
  }
  
  public void setProviderName(String providerName)
  {
    this.providerNameEnvName = null;
    this.providerNameSysPropertyName = null;
    super.setProviderName(providerName);
  }
  
  public void setProvider(Provider provider)
  {
    this.providerClassNameEnvName = null;
    this.providerClassNameSysPropertyName = null;
    super.setProvider(provider);
  }
  
  public void setProviderClassName(String providerClassName)
  {
    this.providerClassNameEnvName = null;
    this.providerClassNameSysPropertyName = null;
    super.setProviderClassName(providerClassName);
  }
  
  public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults)
  {
    this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
    this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
    super.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
  }
  
  public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting)
  {
    this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
    this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
    super.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
  }
  
  public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck)
  {
    this.useLenientSaltSizeCheckEnvName = null;
    this.useLenientSaltSizeCheckSysPropertyName = null;
    super.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
  }
  
  public void setPoolSize(Integer poolSize)
  {
    this.poolSizeEnvName = null;
    this.poolSizeSysPropertyName = null;
    super.setPoolSize(poolSize);
  }
  
  public void setPoolSize(String poolSize)
  {
    this.poolSizeEnvName = null;
    this.poolSizeSysPropertyName = null;
    super.setPoolSize(poolSize);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.config.EnvironmentDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */