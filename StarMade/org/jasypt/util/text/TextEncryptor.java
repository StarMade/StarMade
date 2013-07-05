package org.jasypt.util.text;

public abstract interface TextEncryptor
{
  public abstract String encrypt(String paramString);

  public abstract String decrypt(String paramString);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.text.TextEncryptor
 * JD-Core Version:    0.6.2
 */