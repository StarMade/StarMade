/*  1:   */package org.jasypt.encryption.pbe.config;
/*  2:   */
/*  3:   */import org.jasypt.commons.CommonUtils;
/*  4:   */
/* 42:   */public class SimpleStringPBEConfig
/* 43:   */  extends SimplePBEConfig
/* 44:   */  implements StringPBEConfig
/* 45:   */{
/* 46:46 */  private String stringOutputType = null;
/* 47:   */  
/* 76:   */  public void setStringOutputType(String stringOutputType)
/* 77:   */  {
/* 78:78 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/* 79:   */  }
/* 80:   */  
/* 83:   */  public String getStringOutputType()
/* 84:   */  {
/* 85:85 */    return this.stringOutputType;
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */