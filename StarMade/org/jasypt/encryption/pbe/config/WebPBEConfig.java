package org.jasypt.encryption.pbe.config;

import org.jasypt.commons.CommonUtils;
import org.jasypt.web.pbeconfig.WebPBEConfigRegistry;

public class WebPBEConfig
  extends SimplePBEConfig
{
  private String name = null;
  private String validationWord = null;
  
  public WebPBEConfig()
  {
    WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
    registry.registerConfig(this);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    CommonUtils.validateNotEmpty(name, "Name cannot be set empty");
    this.name = name;
  }
  
  public String getValidationWord()
  {
    return this.validationWord;
  }
  
  public void setValidationWord(String validation)
  {
    CommonUtils.validateNotEmpty(validation, "Validation word cannot be set empty");
    this.validationWord = validation;
  }
  
  public boolean isComplete()
  {
    return (CommonUtils.isNotEmpty(this.name)) && (CommonUtils.isNotEmpty(this.validationWord));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.config.WebPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */