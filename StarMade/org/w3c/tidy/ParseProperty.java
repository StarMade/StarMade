package org.w3c.tidy;

public abstract interface ParseProperty
{
  public abstract Object parse(String paramString1, String paramString2, Configuration paramConfiguration);
  
  public abstract String getType();
  
  public abstract String getOptionValues();
  
  public abstract String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.ParseProperty
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */