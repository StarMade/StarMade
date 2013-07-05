/*    */ package org.jasypt.web.pbeconfig;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.jasypt.encryption.pbe.config.WebPBEConfig;
/*    */ import org.jasypt.exceptions.EncryptionInitializationException;
/*    */ 
/*    */ public final class WebPBEConfigRegistry
/*    */ {
/* 45 */   private final Set names = new HashSet();
/* 46 */   private final List configs = new ArrayList();
/* 47 */   private boolean webConfigurationDone = false;
/*    */ 
/* 49 */   private static final WebPBEConfigRegistry instance = new WebPBEConfigRegistry();
/*    */ 
/*    */   public static WebPBEConfigRegistry getInstance()
/*    */   {
/* 54 */     return instance;
/*    */   }
/*    */ 
/*    */   public synchronized void registerConfig(WebPBEConfig config)
/*    */   {
/* 63 */     if (this.webConfigurationDone) {
/* 64 */       throw new EncryptionInitializationException("Cannot register: Web configuration is already done");
/*    */     }
/*    */ 
/* 69 */     if (!this.names.contains(config.getName())) {
/* 70 */       this.configs.add(config);
/* 71 */       this.names.add(config);
/*    */     }
/*    */   }
/*    */ 
/*    */   public synchronized List getConfigs() {
/* 76 */     return Collections.unmodifiableList(this.configs);
/*    */   }
/*    */ 
/*    */   public boolean isWebConfigurationDone() {
/* 80 */     return (this.webConfigurationDone) || (this.configs.size() == 0);
/*    */   }
/*    */ 
/*    */   public void setWebConfigurationDone(boolean configurationDone) {
/* 84 */     this.webConfigurationDone = configurationDone;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigRegistry
 * JD-Core Version:    0.6.2
 */