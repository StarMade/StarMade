package org.jasypt.web.pbeconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jasypt.encryption.pbe.config.WebPBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;

public final class WebPBEConfigRegistry
{
  private final Set names = new HashSet();
  private final List configs = new ArrayList();
  private boolean webConfigurationDone = false;
  private static final WebPBEConfigRegistry instance = new WebPBEConfigRegistry();
  
  public static WebPBEConfigRegistry getInstance()
  {
    return instance;
  }
  
  public synchronized void registerConfig(WebPBEConfig config)
  {
    if (this.webConfigurationDone) {
      throw new EncryptionInitializationException("Cannot register: Web configuration is already done");
    }
    if (!this.names.contains(config.getName()))
    {
      this.configs.add(config);
      this.names.add(config);
    }
  }
  
  public synchronized List getConfigs()
  {
    return Collections.unmodifiableList(this.configs);
  }
  
  public boolean isWebConfigurationDone()
  {
    return (this.webConfigurationDone) || (this.configs.size() == 0);
  }
  
  public void setWebConfigurationDone(boolean configurationDone)
  {
    this.webConfigurationDone = configurationDone;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */