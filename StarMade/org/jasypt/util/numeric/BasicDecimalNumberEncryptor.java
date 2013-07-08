package org.jasypt.util.numeric;

import java.math.BigDecimal;
import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;

public final class BasicDecimalNumberEncryptor
  implements DecimalNumberEncryptor
{
  private final StandardPBEBigDecimalEncryptor encryptor = new StandardPBEBigDecimalEncryptor();
  
  public BasicDecimalNumberEncryptor()
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
  
  public BigDecimal encrypt(BigDecimal number)
  {
    return this.encryptor.encrypt(number);
  }
  
  public BigDecimal decrypt(BigDecimal encryptedNumber)
  {
    return this.encryptor.decrypt(encryptedNumber);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.numeric.BasicDecimalNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */