/*  1:   */package org.jasypt.encryption.pbe.config;
/*  2:   */
/*  3:   */import org.jasypt.commons.CommonUtils;
/*  4:   */
/* 55:   */public class WebStringPBEConfig
/* 56:   */  extends WebPBEConfig
/* 57:   */  implements StringPBEConfig
/* 58:   */{
/* 59:59 */  private String stringOutputType = null;
/* 60:   */  
/* 86:   */  public void setStringOutputType(String stringOutputType)
/* 87:   */  {
/* 88:88 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/* 89:   */  }
/* 90:   */  
/* 93:   */  public String getStringOutputType()
/* 94:   */  {
/* 95:95 */    return this.stringOutputType;
/* 96:   */  }
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.WebStringPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */