/*   1:    */package org.jasypt.util.numeric;
/*   2:    */
/*   3:    */import java.math.BigInteger;
/*   4:    */import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
/*   5:    */
/*  63:    */public final class BasicIntegerNumberEncryptor
/*  64:    */  implements IntegerNumberEncryptor
/*  65:    */{
/*  66:    */  private final StandardPBEBigIntegerEncryptor encryptor;
/*  67:    */  
/*  68:    */  public BasicIntegerNumberEncryptor()
/*  69:    */  {
/*  70: 70 */    this.encryptor = new StandardPBEBigIntegerEncryptor();
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
/* 101:    */  public BigInteger encrypt(BigInteger number)
/* 102:    */  {
/* 103:103 */    return this.encryptor.encrypt(number);
/* 104:    */  }
/* 105:    */  
/* 112:    */  public BigInteger decrypt(BigInteger encryptedNumber)
/* 113:    */  {
/* 114:114 */    return this.encryptor.decrypt(encryptedNumber);
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.BasicIntegerNumberEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */