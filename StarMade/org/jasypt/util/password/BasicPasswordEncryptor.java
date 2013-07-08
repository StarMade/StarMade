/*  1:   */package org.jasypt.util.password;
/*  2:   */
/*  3:   */import org.jasypt.digest.StandardStringDigester;
/*  4:   */
/* 60:   */public final class BasicPasswordEncryptor
/* 61:   */  implements PasswordEncryptor
/* 62:   */{
/* 63:   */  private final StandardStringDigester digester;
/* 64:   */  
/* 65:   */  public BasicPasswordEncryptor()
/* 66:   */  {
/* 67:67 */    this.digester = new StandardStringDigester();
/* 68:68 */    this.digester.initialize();
/* 69:   */  }
/* 70:   */  
/* 78:   */  public String encryptPassword(String password)
/* 79:   */  {
/* 80:80 */    return this.digester.digest(password);
/* 81:   */  }
/* 82:   */  
/* 93:   */  public boolean checkPassword(String plainPassword, String encryptedPassword)
/* 94:   */  {
/* 95:95 */    return this.digester.matches(plainPassword, encryptedPassword);
/* 96:   */  }
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.BasicPasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */