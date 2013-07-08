package org.jasypt.util.password.rfc2307;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.util.password.PasswordEncryptor;

public final class RFC2307SMD5PasswordEncryptor
  implements PasswordEncryptor
{
  private final StandardStringDigester digester = new StandardStringDigester();
  
  public RFC2307SMD5PasswordEncryptor()
  {
    this.digester.setAlgorithm("MD5");
    this.digester.setIterations(1);
    this.digester.setSaltSizeBytes(8);
    this.digester.setPrefix("{SMD5}");
    this.digester.setInvertPositionOfSaltInMessageBeforeDigesting(true);
    this.digester.setInvertPositionOfPlainSaltInEncryptionResults(true);
    this.digester.setUseLenientSaltSizeCheck(true);
  }
  
  public void setSaltSizeBytes(int saltSizeBytes)
  {
    this.digester.setSaltSizeBytes(saltSizeBytes);
  }
  
  public void setStringOutputType(String stringOutputType)
  {
    this.digester.setStringOutputType(stringOutputType);
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
 * Qualified Name:     org.jasypt.util.password.rfc2307.RFC2307SMD5PasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */