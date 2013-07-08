/*   1:    */package org.jasypt.util.numeric;
/*   2:    */
/*   3:    */import java.math.BigDecimal;
/*   4:    */import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
/*   5:    */
/*  64:    */public final class StrongDecimalNumberEncryptor
/*  65:    */  implements DecimalNumberEncryptor
/*  66:    */{
/*  67:    */  private final StandardPBEBigDecimalEncryptor encryptor;
/*  68:    */  
/*  69:    */  public StrongDecimalNumberEncryptor()
/*  70:    */  {
/*  71: 71 */    this.encryptor = new StandardPBEBigDecimalEncryptor();
/*  72: 72 */    this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
/*  73:    */  }
/*  74:    */  
/*  80:    */  public void setPassword(String password)
/*  81:    */  {
/*  82: 82 */    this.encryptor.setPassword(password);
/*  83:    */  }
/*  84:    */  
/*  91:    */  public void setPasswordCharArray(char[] password)
/*  92:    */  {
/*  93: 93 */    this.encryptor.setPasswordCharArray(password);
/*  94:    */  }
/*  95:    */  
/* 102:    */  public BigDecimal encrypt(BigDecimal number)
/* 103:    */  {
/* 104:104 */    return this.encryptor.encrypt(number);
/* 105:    */  }
/* 106:    */  
/* 113:    */  public BigDecimal decrypt(BigDecimal encryptedNumber)
/* 114:    */  {
/* 115:115 */    return this.encryptor.decrypt(encryptedNumber);
/* 116:    */  }
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.StrongDecimalNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */