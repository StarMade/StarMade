package org.jasypt.digest.config;

import org.jasypt.commons.CommonUtils;

public class SimpleStringDigesterConfig
  extends SimpleDigesterConfig
  implements StringDigesterConfig
{
  private Boolean unicodeNormalizationIgnored = null;
  private String stringOutputType = null;
  private String prefix = null;
  private String suffix = null;
  
  public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored)
  {
    this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
  }
  
  public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored)
  {
    if (unicodeNormalizationIgnored != null) {
      this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
    } else {
      this.unicodeNormalizationIgnored = null;
    }
  }
  
  public void setStringOutputType(String stringOutputType)
  {
    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
  }
  
  public void setPrefix(String prefix)
  {
    this.prefix = prefix;
  }
  
  public void setSuffix(String suffix)
  {
    this.suffix = suffix;
  }
  
  public Boolean isUnicodeNormalizationIgnored()
  {
    return this.unicodeNormalizationIgnored;
  }
  
  public String getStringOutputType()
  {
    return this.stringOutputType;
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public String getSuffix()
  {
    return this.suffix;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.config.SimpleStringDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */