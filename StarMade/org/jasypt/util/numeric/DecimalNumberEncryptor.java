package org.jasypt.util.numeric;

import java.math.BigDecimal;

public abstract interface DecimalNumberEncryptor
{
  public abstract BigDecimal encrypt(BigDecimal paramBigDecimal);

  public abstract BigDecimal decrypt(BigDecimal paramBigDecimal);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.DecimalNumberEncryptor
 * JD-Core Version:    0.6.2
 */