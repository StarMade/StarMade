package org.jasypt.util.numeric;

import java.math.BigInteger;
import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;

public final class BasicIntegerNumberEncryptor
  implements IntegerNumberEncryptor
{
  private final StandardPBEBigIntegerEncryptor encryptor = new StandardPBEBigIntegerEncryptor();
  
  public BasicIntegerNumberEncryptor()
  {
    this.encryptor.setAlgorithm("PBEWithMD5AndDES");
  }
  
  public void setPassword(String password)
  {
    this.encryptor.setPassword(password);
  }
  
  public void setPasswordCharArray(char[] password)
  {
    this.encryptor.setPasswordCharArray(password);
  }
  
  public BigInteger encrypt(BigInteger number)
  {
    return this.encryptor.encrypt(number);
  }
  
  public BigInteger decrypt(BigInteger encryptedNumber)
  {
    return this.encryptor.decrypt(encryptedNumber);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.numeric.BasicIntegerNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */