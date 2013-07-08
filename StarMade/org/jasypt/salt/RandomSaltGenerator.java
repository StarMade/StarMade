/*   1:    */package org.jasypt.salt;
/*   2:    */
/*   3:    */import java.security.NoSuchAlgorithmException;
/*   4:    */import java.security.SecureRandom;
/*   5:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   6:    */
/*  54:    */public class RandomSaltGenerator
/*  55:    */  implements SaltGenerator
/*  56:    */{
/*  57:    */  public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
/*  58:    */  private final SecureRandom random;
/*  59:    */  
/*  60:    */  public RandomSaltGenerator()
/*  61:    */  {
/*  62: 62 */    this("SHA1PRNG");
/*  63:    */  }
/*  64:    */  
/*  72:    */  public RandomSaltGenerator(String secureRandomAlgorithm)
/*  73:    */  {
/*  74:    */    try
/*  75:    */    {
/*  76: 76 */      this.random = SecureRandom.getInstance(secureRandomAlgorithm);
/*  77: 77 */      this.random.setSeed(System.currentTimeMillis());
/*  78:    */    } catch (NoSuchAlgorithmException e) {
/*  79: 79 */      throw new EncryptionInitializationException(e);
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  89:    */  public byte[] generateSalt(int lengthBytes)
/*  90:    */  {
/*  91: 91 */    byte[] salt = new byte[lengthBytes];
/*  92: 92 */    synchronized (this.random) {
/*  93: 93 */      this.random.nextBytes(salt);
/*  94:    */    }
/*  95: 95 */    return salt;
/*  96:    */  }
/*  97:    */  
/* 105:    */  public boolean includePlainSaltInEncryptionResults()
/* 106:    */  {
/* 107:107 */    return true;
/* 108:    */  }
/* 109:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.RandomSaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */