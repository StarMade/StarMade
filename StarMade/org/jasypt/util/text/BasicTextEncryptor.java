/*   1:    */package org.jasypt.util.text;
/*   2:    */
/*   3:    */import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
/*   4:    */
/*  61:    */public final class BasicTextEncryptor
/*  62:    */  implements TextEncryptor
/*  63:    */{
/*  64:    */  private final StandardPBEStringEncryptor encryptor;
/*  65:    */  
/*  66:    */  public BasicTextEncryptor()
/*  67:    */  {
/*  68: 68 */    this.encryptor = new StandardPBEStringEncryptor();
/*  69: 69 */    this.encryptor.setAlgorithm("PBEWithMD5AndDES");
/*  70:    */  }
/*  71:    */  
/*  77:    */  public void setPassword(String password)
/*  78:    */  {
/*  79: 79 */    this.encryptor.setPassword(password);
/*  80:    */  }
/*  81:    */  
/*  88:    */  public void setPasswordCharArray(char[] password)
/*  89:    */  {
/*  90: 90 */    this.encryptor.setPasswordCharArray(password);
/*  91:    */  }
/*  92:    */  
/*  99:    */  public String encrypt(String message)
/* 100:    */  {
/* 101:101 */    return this.encryptor.encrypt(message);
/* 102:    */  }
/* 103:    */  
/* 110:    */  public String decrypt(String encryptedMessage)
/* 111:    */  {
/* 112:112 */    return this.encryptor.decrypt(encryptedMessage);
/* 113:    */  }
/* 114:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.text.BasicTextEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */