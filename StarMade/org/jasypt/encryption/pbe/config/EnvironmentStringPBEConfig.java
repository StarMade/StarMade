/*   1:    */package org.jasypt.encryption.pbe.config;
/*   2:    */
/*   3:    */import org.jasypt.commons.CommonUtils;
/*   4:    */
/*  49:    */public class EnvironmentStringPBEConfig
/*  50:    */  extends EnvironmentPBEConfig
/*  51:    */  implements StringPBEConfig
/*  52:    */{
/*  53: 53 */  private String stringOutputType = null;
/*  54:    */  
/*  55: 55 */  private String stringOutputTypeEnvName = null;
/*  56:    */  
/*  57: 57 */  private String stringOutputTypeSysPropertyName = null;
/*  58:    */  
/*  76:    */  public String getStringOutputTypeEnvName()
/*  77:    */  {
/*  78: 78 */    return this.stringOutputTypeEnvName;
/*  79:    */  }
/*  80:    */  
/*  87:    */  public void setStringOutputTypeEnvName(String stringOutputTypeEnvName)
/*  88:    */  {
/*  89: 89 */    this.stringOutputTypeEnvName = stringOutputTypeEnvName;
/*  90: 90 */    if (stringOutputTypeEnvName == null) {
/*  91: 91 */      this.stringOutputType = null;
/*  92:    */    } else {
/*  93: 93 */      this.stringOutputTypeSysPropertyName = null;
/*  94: 94 */      this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getenv(stringOutputTypeEnvName));
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/* 106:    */  public String getStringOutputTypeSysPropertyName()
/* 107:    */  {
/* 108:108 */    return this.stringOutputTypeSysPropertyName;
/* 109:    */  }
/* 110:    */  
/* 117:    */  public void setStringOutputTypeSysPropertyName(String stringOutputTypeSysPropertyName)
/* 118:    */  {
/* 119:119 */    this.stringOutputTypeSysPropertyName = stringOutputTypeSysPropertyName;
/* 120:120 */    if (stringOutputTypeSysPropertyName == null) {
/* 121:121 */      this.stringOutputType = null;
/* 122:    */    } else {
/* 123:123 */      this.stringOutputTypeEnvName = null;
/* 124:124 */      this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getProperty(stringOutputTypeSysPropertyName));
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 145:    */  public void setStringOutputType(String stringOutputType)
/* 146:    */  {
/* 147:147 */    this.stringOutputTypeEnvName = null;
/* 148:148 */    this.stringOutputTypeSysPropertyName = null;
/* 149:149 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/* 150:    */  }
/* 151:    */  
/* 154:    */  public String getStringOutputType()
/* 155:    */  {
/* 156:156 */    return this.stringOutputType;
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */