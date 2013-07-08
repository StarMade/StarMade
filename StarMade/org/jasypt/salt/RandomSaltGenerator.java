package org.jasypt.salt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.jasypt.exceptions.EncryptionInitializationException;

public class RandomSaltGenerator
  implements SaltGenerator
{
  public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
  private final SecureRandom random;
  
  public RandomSaltGenerator()
  {
    this("SHA1PRNG");
  }
  
  public RandomSaltGenerator(String secureRandomAlgorithm)
  {
    try
    {
      this.random = SecureRandom.getInstance(secureRandomAlgorithm);
      this.random.setSeed(System.currentTimeMillis());
    }
    catch (NoSuchAlgorithmException local_e)
    {
      throw new EncryptionInitializationException(local_e);
    }
  }
  
  public byte[] generateSalt(int lengthBytes)
  {
    byte[] salt = new byte[lengthBytes];
    synchronized (this.random)
    {
      this.random.nextBytes(salt);
    }
    return salt;
  }
  
  public boolean includePlainSaltInEncryptionResults()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.salt.RandomSaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */