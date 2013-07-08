/*   1:    */package org.jasypt.encryption.pbe.config;
/*   2:    */
/*   3:    */import org.jasypt.commons.CommonUtils;
/*   4:    */import org.jasypt.web.pbeconfig.WebPBEConfigRegistry;
/*   5:    */
/*  53:    */public class WebPBEConfig
/*  54:    */  extends SimplePBEConfig
/*  55:    */{
/*  56: 56 */  private String name = null;
/*  57: 57 */  private String validationWord = null;
/*  58:    */  
/*  65:    */  public WebPBEConfig()
/*  66:    */  {
/*  67: 67 */    WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/*  68:    */    
/*  69: 69 */    registry.registerConfig(this);
/*  70:    */  }
/*  71:    */  
/*  79:    */  public String getName()
/*  80:    */  {
/*  81: 81 */    return this.name;
/*  82:    */  }
/*  83:    */  
/*  92:    */  public void setName(String name)
/*  93:    */  {
/*  94: 94 */    CommonUtils.validateNotEmpty(name, "Name cannot be set empty");
/*  95: 95 */    this.name = name;
/*  96:    */  }
/*  97:    */  
/* 109:    */  public String getValidationWord()
/* 110:    */  {
/* 111:111 */    return this.validationWord;
/* 112:    */  }
/* 113:    */  
/* 125:    */  public void setValidationWord(String validation)
/* 126:    */  {
/* 127:127 */    CommonUtils.validateNotEmpty(validation, "Validation word cannot be set empty");
/* 128:128 */    this.validationWord = validation;
/* 129:    */  }
/* 130:    */  
/* 139:    */  public boolean isComplete()
/* 140:    */  {
/* 141:141 */    return (CommonUtils.isNotEmpty(this.name)) && (CommonUtils.isNotEmpty(this.validationWord));
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.WebPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */