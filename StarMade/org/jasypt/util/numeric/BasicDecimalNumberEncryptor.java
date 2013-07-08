/*   1:    */package org.jasypt.util.numeric;
/*   2:    */
/*   3:    */import java.math.BigDecimal;
/*   4:    */import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
/*   5:    */
/*  63:    */public final class BasicDecimalNumberEncryptor
/*  64:    */  implements DecimalNumberEncryptor
/*  65:    */{
/*  66:    */  private final StandardPBEBigDecimalEncryptor encryptor;
/*  67:    */  
/*  68:    */  public BasicDecimalNumberEncryptor()
/*  69:    */  {
/*  70: 70 */    this.encryptor = new StandardPBEBigDecimalEncryptor();
/*  71: 71 */    this.encryptor.setAlgorithm("PBEWithMD5AndDES");
/*  72:    */  }
/*  73:    */  
/*  79:    */  public void setPassword(String password)
/*  80:    */  {
/*  81: 81 */    this.encryptor.setPassword(password);
/*  82:    */  }
/*  83:    */  
/*  90:    */  public void setPasswordCharArray(char[] password)
/*  91:    */  {
/*  92: 92 */    this.encryptor.setPasswordCharArray(password);
/*  93:    */  }
/*  94:    */  
/* 101:    */  public BigDecimal encrypt(BigDecimal number)
/* 102:    */  {
/* 103:103 */    return this.encryptor.encrypt(number);
/* 104:    */  }
/* 105:    */  
/* 112:    */  public BigDecimal decrypt(BigDecimal encryptedNumber)
/* 113:    */  {
/* 114:114 */    return this.encryptor.decrypt(encryptedNumber);
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.BasicDecimalNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */