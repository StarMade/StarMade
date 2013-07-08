package org.jasypt.encryption;

import java.math.BigDecimal;

public abstract interface BigDecimalEncryptor
{
  public abstract BigDecimal encrypt(BigDecimal paramBigDecimal);
  
  public abstract BigDecimal decrypt(BigDecimal paramBigDecimal);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.BigDecimalEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */