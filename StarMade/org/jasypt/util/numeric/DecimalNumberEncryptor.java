package org.jasypt.util.numeric;

import java.math.BigDecimal;

public abstract interface DecimalNumberEncryptor
{
  public abstract BigDecimal encrypt(BigDecimal paramBigDecimal);
  
  public abstract BigDecimal decrypt(BigDecimal paramBigDecimal);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.numeric.DecimalNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */