package org.jasypt.digest;

public abstract interface StringDigester
{
  public abstract String digest(String paramString);

  public abstract boolean matches(String paramString1, String paramString2);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.StringDigester
 * JD-Core Version:    0.6.2
 */