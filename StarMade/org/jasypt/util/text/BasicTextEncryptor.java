package org.jasypt.util.text;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public final class BasicTextEncryptor
  implements TextEncryptor
{
  private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
  
  public BasicTextEncryptor()
  {
    this.encryptor.setAlgorithm("PBEWithMD5AndDES");
  }
  
  public void setPassword(String password)
  {
    this.encryptor.setPassword(password);
  }
  
  public void setPasswordCharArray(char[] password)
  {
    this.encryptor.setPasswordCharArray(password);
  }
  
  public String encrypt(String message)
  {
    return this.encryptor.encrypt(message);
  }
  
  public String decrypt(String encryptedMessage)
  {
    return this.encryptor.decrypt(encryptedMessage);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.text.BasicTextEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */