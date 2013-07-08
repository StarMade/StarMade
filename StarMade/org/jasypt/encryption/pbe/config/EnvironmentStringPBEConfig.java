package org.jasypt.encryption.pbe.config;

import org.jasypt.commons.CommonUtils;

public class EnvironmentStringPBEConfig
  extends EnvironmentPBEConfig
  implements StringPBEConfig
{
  private String stringOutputType = null;
  private String stringOutputTypeEnvName = null;
  private String stringOutputTypeSysPropertyName = null;
  
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
  
  public void setStringOutputType(String stringOutputType)
  {
    this.stringOutputTypeEnvName = null;
    this.stringOutputTypeSysPropertyName = null;
    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
  }
  
  public String getStringOutputType()
  {
    return this.stringOutputType;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */