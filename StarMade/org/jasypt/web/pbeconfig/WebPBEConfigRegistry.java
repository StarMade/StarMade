/*  1:   */package org.jasypt.web.pbeconfig;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.Collections;
/*  5:   */import java.util.HashSet;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Set;
/*  8:   */import org.jasypt.encryption.pbe.config.WebPBEConfig;
/*  9:   */import org.jasypt.exceptions.EncryptionInitializationException;
/* 10:   */
/* 43:   */public final class WebPBEConfigRegistry
/* 44:   */{
/* 45:45 */  private final Set names = new HashSet();
/* 46:46 */  private final List configs = new ArrayList();
/* 47:47 */  private boolean webConfigurationDone = false;
/* 48:   */  
/* 49:49 */  private static final WebPBEConfigRegistry instance = new WebPBEConfigRegistry();
/* 50:   */  
/* 52:   */  public static WebPBEConfigRegistry getInstance()
/* 53:   */  {
/* 54:54 */    return instance;
/* 55:   */  }
/* 56:   */  
/* 61:   */  public synchronized void registerConfig(WebPBEConfig config)
/* 62:   */  {
/* 63:63 */    if (this.webConfigurationDone) {
/* 64:64 */      throw new EncryptionInitializationException("Cannot register: Web configuration is already done");
/* 65:   */    }
/* 66:   */    
/* 69:69 */    if (!this.names.contains(config.getName())) {
/* 70:70 */      this.configs.add(config);
/* 71:71 */      this.names.add(config);
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 75:   */  public synchronized List getConfigs() {
/* 76:76 */    return Collections.unmodifiableList(this.configs);
/* 77:   */  }
/* 78:   */  
/* 79:   */  public boolean isWebConfigurationDone() {
/* 80:80 */    return (this.webConfigurationDone) || (this.configs.size() == 0);
/* 81:   */  }
/* 82:   */  
/* 83:   */  public void setWebConfigurationDone(boolean configurationDone) {
/* 84:84 */    this.webConfigurationDone = configurationDone;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */