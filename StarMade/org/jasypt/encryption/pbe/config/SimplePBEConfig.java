package org.jasypt.encryption.pbe.config;

import java.security.Provider;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.PasswordAlreadyCleanedException;
import org.jasypt.salt.SaltGenerator;

public class SimplePBEConfig
  implements PBEConfig, PBECleanablePasswordConfig
{
  private String algorithm = null;
  private char[] password = null;
  private Integer keyObtentionIterations = null;
  private SaltGenerator saltGenerator = null;
  private String providerName = null;
  private Provider provider = null;
  private Integer poolSize = null;
  private boolean passwordCleaned = false;
  
  public void setAlgorithm(String algorithm)
  {
    this.algorithm = algorithm;
  }
  
  public void setPassword(String password)
  {
    if (this.password != null) {
      cleanPassword();
    }
    if (password == null) {
      this.password = null;
    } else {
      this.password = password.toCharArray();
    }
  }
  
  public void setPasswordCharArray(char[] password)
  {
    if (this.password != null) {
      cleanPassword();
    }
    if (password == null)
    {
      this.password = null;
    }
    else
    {
      this.password = new char[password.length];
      System.arraycopy(password, 0, this.password, 0, password.length);
    }
  }
  
  public void setKeyObtentionIterations(Integer keyObtentionIterations)
  {
    this.keyObtentionIterations = keyObtentionIterations;
  }
  
  public void setKeyObtentionIterations(String keyObtentionIterations)
  {
    if (keyObtentionIterations != null) {
      try
      {
        this.keyObtentionIterations = new Integer(keyObtentionIterations);
      }
      catch (NumberFormatException local_e)
      {
        throw new EncryptionInitializationException(local_e);
      }
    } else {
      this.keyObtentionIterations = null;
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
  
  public String getPassword()
  {
    if (this.passwordCleaned) {
      throw new PasswordAlreadyCleanedException();
    }
    return new String(this.password);
  }
  
  public char[] getPasswordCharArray()
  {
    if (this.passwordCleaned) {
      throw new PasswordAlreadyCleanedException();
    }
    char[] result = new char[this.password.length];
    System.arraycopy(this.password, 0, result, 0, this.password.length);
    return result;
  }
  
  public Integer getKeyObtentionIterations()
  {
    return this.keyObtentionIterations;
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
  
  public Integer getPoolSize()
  {
    return this.poolSize;
  }
  
  public void cleanPassword()
  {
    if (this.password != null)
    {
      int pwdLength = this.password.length;
      for (int local_i = 0; local_i < pwdLength; local_i++) {
        this.password[local_i] = '\000';
      }
      this.password = new char[0];
    }
    this.passwordCleaned = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.config.SimplePBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */