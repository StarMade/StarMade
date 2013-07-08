package org.jasypt.util.password;

import org.jasypt.digest.StandardStringDigester;

public final class StrongPasswordEncryptor
  implements PasswordEncryptor
{
  private final StandardStringDigester digester = new StandardStringDigester();
  
  public StrongPasswordEncryptor()
  {
    this.digester.setAlgorithm("SHA-256");
    this.digester.setIterations(100000);
    this.digester.setSaltSizeBytes(16);
    this.digester.initialize();
  }
  
  public String encryptPassword(String password)
  {
    return this.digester.digest(password);
  }
  
  public boolean checkPassword(String plainPassword, String encryptedPassword)
  {
    return this.digester.matches(plainPassword, encryptedPassword);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.password.StrongPasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */