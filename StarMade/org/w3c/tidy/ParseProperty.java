package org.w3c.tidy;

public abstract interface ParseProperty
{
  public abstract Object parse(String paramString1, String paramString2, Configuration paramConfiguration);

  public abstract String getType();

  public abstract String getOptionValues();

  public abstract String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.ParseProperty
 * JD-Core Version:    0.6.2
 */