/*     */ package org.jasypt.encryption.pbe.config;
/*     */ 
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.web.pbeconfig.WebPBEConfigRegistry;
/*     */ 
/*     */ public class WebPBEConfig extends SimplePBEConfig
/*     */ {
/*  56 */   private String name = null;
/*  57 */   private String validationWord = null;
/*     */ 
/*     */   public WebPBEConfig()
/*     */   {
/*  67 */     WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/*     */ 
/*  69 */     registry.registerConfig(this);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  81 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  94 */     CommonUtils.validateNotEmpty(name, "Name cannot be set empty");
/*  95 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getValidationWord()
/*     */   {
/* 111 */     return this.validationWord;
/*     */   }
/*     */ 
/*     */   public void setValidationWord(String validation)
/*     */   {
/* 127 */     CommonUtils.validateNotEmpty(validation, "Validation word cannot be set empty");
/* 128 */     this.validationWord = validation;
/*     */   }
/*     */ 
/*     */   public boolean isComplete()
/*     */   {
/* 141 */     return (CommonUtils.isNotEmpty(this.name)) && (CommonUtils.isNotEmpty(this.validationWord));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.WebPBEConfig
 * JD-Core Version:    0.6.2
 */