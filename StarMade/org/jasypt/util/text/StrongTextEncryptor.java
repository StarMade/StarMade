/*   1:    */package org.jasypt.util.text;
/*   2:    */
/*   3:    */import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
/*   4:    */
/*  67:    */public final class StrongTextEncryptor
/*  68:    */  implements TextEncryptor
/*  69:    */{
/*  70:    */  private final StandardPBEStringEncryptor encryptor;
/*  71:    */  
/*  72:    */  public StrongTextEncryptor()
/*  73:    */  {
/*  74: 74 */    this.encryptor = new StandardPBEStringEncryptor();
/*  75: 75 */    this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
/*  76:    */  }
/*  77:    */  
/*  83:    */  public void setPassword(String password)
/*  84:    */  {
/*  85: 85 */    this.encryptor.setPassword(password);
/*  86:    */  }
/*  87:    */  
/*  94:    */  public void setPasswordCharArray(char[] password)
/*  95:    */  {
/*  96: 96 */    this.encryptor.setPasswordCharArray(password);
/*  97:    */  }
/*  98:    */  
/* 105:    */  public String encrypt(String message)
/* 106:    */  {
/* 107:107 */    return this.encryptor.encrypt(message);
/* 108:    */  }
/* 109:    */  
/* 116:    */  public String decrypt(String encryptedMessage)
/* 117:    */  {
/* 118:118 */    return this.encryptor.decrypt(encryptedMessage);
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.text.StrongTextEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */