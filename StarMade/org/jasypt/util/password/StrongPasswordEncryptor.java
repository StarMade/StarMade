/*  1:   */package org.jasypt.util.password;
/*  2:   */
/*  3:   */import org.jasypt.digest.StandardStringDigester;
/*  4:   */
/* 61:   */public final class StrongPasswordEncryptor
/* 62:   */  implements PasswordEncryptor
/* 63:   */{
/* 64:   */  private final StandardStringDigester digester;
/* 65:   */  
/* 66:   */  public StrongPasswordEncryptor()
/* 67:   */  {
/* 68:68 */    this.digester = new StandardStringDigester();
/* 69:69 */    this.digester.setAlgorithm("SHA-256");
/* 70:70 */    this.digester.setIterations(100000);
/* 71:71 */    this.digester.setSaltSizeBytes(16);
/* 72:72 */    this.digester.initialize();
/* 73:   */  }
/* 74:   */  
/* 82:   */  public String encryptPassword(String password)
/* 83:   */  {
/* 84:84 */    return this.digester.digest(password);
/* 85:   */  }
/* 86:   */  
/* 97:   */  public boolean checkPassword(String plainPassword, String encryptedPassword)
/* 98:   */  {
/* 99:99 */    return this.digester.matches(plainPassword, encryptedPassword);
/* 100:   */  }
/* 101:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.StrongPasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */