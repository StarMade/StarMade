package org.jasypt.util.password;

public abstract interface PasswordEncryptor
{
  public abstract String encryptPassword(String paramString);

  public abstract boolean checkPassword(String paramString1, String paramString2);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.PasswordEncryptor
 * JD-Core Version:    0.6.2
 */