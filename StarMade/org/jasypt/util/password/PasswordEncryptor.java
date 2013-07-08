package org.jasypt.util.password;

public abstract interface PasswordEncryptor
{
  public abstract String encryptPassword(String paramString);
  
  public abstract boolean checkPassword(String paramString1, String paramString2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.password.PasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */