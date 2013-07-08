package org.jasypt.util.text;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public final class StrongTextEncryptor
  implements TextEncryptor
{
  private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
  
  public StrongTextEncryptor()
  {
    this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
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
 * Qualified Name:     org.jasypt.util.text.StrongTextEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */