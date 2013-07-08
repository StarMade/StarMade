/*   1:    */package org.jasypt.digest.config;
/*   2:    */
/*   3:    */import org.jasypt.commons.CommonUtils;
/*   4:    */
/*  43:    */public class SimpleStringDigesterConfig
/*  44:    */  extends SimpleDigesterConfig
/*  45:    */  implements StringDigesterConfig
/*  46:    */{
/*  47: 47 */  private Boolean unicodeNormalizationIgnored = null;
/*  48: 48 */  private String stringOutputType = null;
/*  49: 49 */  private String prefix = null;
/*  50: 50 */  private String suffix = null;
/*  51:    */  
/*  92:    */  public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored)
/*  93:    */  {
/*  94: 94 */    this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
/*  95:    */  }
/*  96:    */  
/* 130:    */  public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored)
/* 131:    */  {
/* 132:132 */    if (unicodeNormalizationIgnored != null) {
/* 133:133 */      this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
/* 134:    */    }
/* 135:    */    else {
/* 136:136 */      this.unicodeNormalizationIgnored = null;
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 158:    */  public void setStringOutputType(String stringOutputType)
/* 159:    */  {
/* 160:160 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/* 161:    */  }
/* 162:    */  
/* 182:    */  public void setPrefix(String prefix)
/* 183:    */  {
/* 184:184 */    this.prefix = prefix;
/* 185:    */  }
/* 186:    */  
/* 204:    */  public void setSuffix(String suffix)
/* 205:    */  {
/* 206:206 */    this.suffix = suffix;
/* 207:    */  }
/* 208:    */  
/* 210:    */  public Boolean isUnicodeNormalizationIgnored()
/* 211:    */  {
/* 212:212 */    return this.unicodeNormalizationIgnored;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public String getStringOutputType()
/* 216:    */  {
/* 217:217 */    return this.stringOutputType;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public String getPrefix() {
/* 221:221 */    return this.prefix;
/* 222:    */  }
/* 223:    */  
/* 224:    */  public String getSuffix() {
/* 225:225 */    return this.suffix;
/* 226:    */  }
/* 227:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.SimpleStringDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */