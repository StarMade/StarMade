package org.jasypt.util.digest;

import java.security.Provider;
import org.jasypt.digest.StandardByteDigester;

public final class Digester
{
  public static final String DEFAULT_ALGORITHM = "MD5";
  private static final int ITERATIONS = 1;
  private static final int SALT_SIZE_BYTES = 0;
  private final StandardByteDigester digester = new StandardByteDigester();
  
  public Digester()
  {
    this.digester.setIterations(1);
    this.digester.setSaltSizeBytes(0);
  }
  
  public Digester(String algorithm)
  {
    this.digester.setIterations(1);
    this.digester.setSaltSizeBytes(0);
    this.digester.setAlgorithm(algorithm);
  }
  
  public Digester(String algorithm, String providerName)
  {
    this.digester.setIterations(1);
    this.digester.setSaltSizeBytes(0);
    this.digester.setAlgorithm(algorithm);
    this.digester.setProviderName(providerName);
  }
  
  public Digester(String algorithm, Provider provider)
  {
    this.digester.setIterations(1);
    this.digester.setSaltSizeBytes(0);
    this.digester.setAlgorithm(algorithm);
    this.digester.setProvider(provider);
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
  
  public byte[] digest(byte[] binary)
  {
    return this.digester.digest(binary);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.digest.Digester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */