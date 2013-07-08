/*   1:    */package org.jasypt.util.password.rfc2307;
/*   2:    */
/*   3:    */import org.jasypt.digest.StandardStringDigester;
/*   4:    */import org.jasypt.util.password.PasswordEncryptor;
/*   5:    */
/*  55:    */public final class RFC2307MD5PasswordEncryptor
/*  56:    */  implements PasswordEncryptor
/*  57:    */{
/*  58:    */  private final StandardStringDigester digester;
/*  59:    */  
/*  60:    */  public RFC2307MD5PasswordEncryptor()
/*  61:    */  {
/*  62: 62 */    this.digester = new StandardStringDigester();
/*  63: 63 */    this.digester.setAlgorithm("MD5");
/*  64: 64 */    this.digester.setIterations(1);
/*  65: 65 */    this.digester.setSaltSizeBytes(0);
/*  66: 66 */    this.digester.setPrefix("{MD5}");
/*  67:    */  }
/*  68:    */  
/*  81:    */  public void setStringOutputType(String stringOutputType)
/*  82:    */  {
/*  83: 83 */    this.digester.setStringOutputType(stringOutputType);
/*  84:    */  }
/*  85:    */  
/*  93:    */  public String encryptPassword(String password)
/*  94:    */  {
/*  95: 95 */    return this.digester.digest(password);
/*  96:    */  }
/*  97:    */  
/* 108:    */  public boolean checkPassword(String plainPassword, String encryptedPassword)
/* 109:    */  {
/* 110:110 */    return this.digester.matches(plainPassword, encryptedPassword);
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.rfc2307.RFC2307MD5PasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */