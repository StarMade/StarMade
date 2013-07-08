package org.jasypt.digest.config;

public abstract interface StringDigesterConfig
  extends DigesterConfig
{
  public abstract Boolean isUnicodeNormalizationIgnored();
  
  public abstract String getStringOutputType();
  
  public abstract String getPrefix();
  
  public abstract String getSuffix();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.config.StringDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */