package org.jasypt.digest.config;

import org.jasypt.commons.CommonUtils;

public class EnvironmentStringDigesterConfig
  extends EnvironmentDigesterConfig
  implements StringDigesterConfig
{
  private Boolean unicodeNormalizationIgnored = null;
  private String stringOutputType = null;
  private String prefix = null;
  private String suffix = null;
  private String unicodeNormalizationIgnoredEnvName = null;
  private String stringOutputTypeEnvName = null;
  private String prefixEnvName = null;
  private String suffixEnvName = null;
  private String unicodeNormalizationIgnoredSysPropertyName = null;
  private String stringOutputTypeSysPropertyName = null;
  private String prefixSysPropertyName = null;
  private String suffixSysPropertyName = null;
  
  public String getUnicodeNormalizationIgnoredEnvName()
  {
    return this.unicodeNormalizationIgnoredEnvName;
  }
  
  public void setUnicodeNormalizationIgnoredEnvName(String unicodeNormalizationIgnoredEnvName)
  {
    this.unicodeNormalizationIgnoredEnvName = unicodeNormalizationIgnoredEnvName;
    if (unicodeNormalizationIgnoredEnvName == null)
    {
      this.unicodeNormalizationIgnored = null;
    }
    else
    {
      this.unicodeNormalizationIgnoredSysPropertyName = null;
      String unicodeNormalizationIgnoredValue = System.getenv(unicodeNormalizationIgnoredEnvName);
      if (unicodeNormalizationIgnoredValue != null) {
        this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnoredValue);
      } else {
        this.unicodeNormalizationIgnored = null;
      }
    }
  }
  
  public String getUnicodeNormalizationIgnoredSysPropertyName()
  {
    return this.unicodeNormalizationIgnoredSysPropertyName;
  }
  
  public void setUnicodeNormalizationIgnoredSysPropertyName(String unicodeNormalizationIgnoredSysPropertyName)
  {
    this.unicodeNormalizationIgnoredSysPropertyName = unicodeNormalizationIgnoredSysPropertyName;
    if (unicodeNormalizationIgnoredSysPropertyName == null)
    {
      this.unicodeNormalizationIgnored = null;
    }
    else
    {
      this.unicodeNormalizationIgnoredEnvName = null;
      String unicodeNormalizationIgnoredValue = System.getProperty(unicodeNormalizationIgnoredSysPropertyName);
      if (unicodeNormalizationIgnoredValue != null) {
        this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnoredValue);
      } else {
        this.unicodeNormalizationIgnored = null;
      }
    }
  }
  
  public String getStringOutputTypeEnvName()
  {
    return this.stringOutputTypeEnvName;
  }
  
  public void setStringOutputTypeEnvName(String stringOutputTypeEnvName)
  {
    this.stringOutputTypeEnvName = stringOutputTypeEnvName;
    if (stringOutputTypeEnvName == null)
    {
      this.stringOutputType = null;
    }
    else
    {
      this.stringOutputTypeSysPropertyName = null;
      this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getenv(stringOutputTypeEnvName));
    }
  }
  
  public String getStringOutputTypeSysPropertyName()
  {
    return this.stringOutputTypeSysPropertyName;
  }
  
  public void setStringOutputTypeSysPropertyName(String stringOutputTypeSysPropertyName)
  {
    this.stringOutputTypeSysPropertyName = stringOutputTypeSysPropertyName;
    if (stringOutputTypeSysPropertyName == null)
    {
      this.stringOutputType = null;
    }
    else
    {
      this.stringOutputTypeEnvName = null;
      this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getProperty(stringOutputTypeSysPropertyName));
    }
  }
  
  public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored)
  {
    this.unicodeNormalizationIgnoredEnvName = null;
    this.unicodeNormalizationIgnoredSysPropertyName = null;
    this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
  }
  
  public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored)
  {
    this.unicodeNormalizationIgnoredEnvName = null;
    this.unicodeNormalizationIgnoredSysPropertyName = null;
    if (unicodeNormalizationIgnored != null) {
      this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
    } else {
      this.unicodeNormalizationIgnored = null;
    }
  }
  
  public void setStringOutputType(String stringOutputType)
  {
    this.stringOutputTypeEnvName = null;
    this.stringOutputTypeSysPropertyName = null;
    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
  }
  
  public void setPrefix(String prefix)
  {
    this.prefixEnvName = null;
    this.prefixSysPropertyName = null;
    this.prefix = prefix;
  }
  
  public void setSuffix(String suffix)
  {
    this.suffixEnvName = null;
    this.suffixSysPropertyName = null;
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
  
  public String getPrefixEnvName()
  {
    return this.prefixEnvName;
  }
  
  public void setPrefixEnvName(String prefixEnvName)
  {
    this.prefixEnvName = prefixEnvName;
    if (prefixEnvName == null)
    {
      this.prefix = null;
    }
    else
    {
      this.prefixSysPropertyName = null;
      this.prefix = System.getenv(prefixEnvName);
    }
  }
  
  public String getPrefixSysPropertyName()
  {
    return this.prefixSysPropertyName;
  }
  
  public void setPrefixSysPropertyName(String prefixSysPropertyName)
  {
    this.prefixSysPropertyName = prefixSysPropertyName;
    if (prefixSysPropertyName == null)
    {
      this.prefix = null;
    }
    else
    {
      this.prefixEnvName = null;
      this.prefix = System.getProperty(prefixSysPropertyName);
    }
  }
  
  public String getSuffixEnvName()
  {
    return this.suffixEnvName;
  }
  
  public void setSuffixEnvName(String suffixEnvName)
  {
    this.suffixEnvName = suffixEnvName;
    if (suffixEnvName == null)
    {
      this.suffix = null;
    }
    else
    {
      this.suffixSysPropertyName = null;
      this.suffix = System.getenv(suffixEnvName);
    }
  }
  
  public String getSuffixSysPropertyName()
  {
    return this.suffixSysPropertyName;
  }
  
  public void setSuffixSysPropertyName(String suffixSysPropertyName)
  {
    this.suffixSysPropertyName = suffixSysPropertyName;
    if (suffixSysPropertyName == null)
    {
      this.suffix = null;
    }
    else
    {
      this.suffixEnvName = null;
      this.suffix = System.getProperty(suffixSysPropertyName);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.config.EnvironmentStringDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */