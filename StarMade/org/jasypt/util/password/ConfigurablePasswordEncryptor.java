package org.jasypt.util.password;

import java.security.Provider;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.digest.config.DigesterConfig;

public final class ConfigurablePasswordEncryptor
  implements PasswordEncryptor
{
  private final StandardStringDigester digester = new StandardStringDigester();
  
  public void setConfig(DigesterConfig config)
  {
    this.digester.setConfig(config);
  }
  
  public void setAlgorithm(String algorithm)
  {
    this.digester.setAlgorithm(algorithm);
  }
  
  public void setProviderName(String providerName)
  {
    this.digester.setProviderName(providerName);
  }
  
  public void setProvider(Provider provider)
  {
    this.digester.setProvider(provider);
  }
  
  public void setPlainDigest(boolean plainDigest)
  {
    if (plainDigest)
    {
      this.digester.setIterations(1);
      this.digester.setSaltSizeBytes(0);
    }
    else
    {
      this.digester.setIterations(1000);
      this.digester.setSaltSizeBytes(8);
    }
  }
  
  public void setStringOutputType(String stringOutputType)
  {
    this.digester.setStringOutputType(stringOutputType);
  }
  
  public String encryptPassword(String password)
  {
    return this.digester.digest(password);
  }
  
  public boolean checkPassword(String plainPassword, String encryptedPassword)
  {
    return this.digester.matches(plainPassword, encryptedPassword);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.password.ConfigurablePasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */