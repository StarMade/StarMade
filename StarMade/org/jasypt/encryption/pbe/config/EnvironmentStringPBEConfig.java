/*     */ package org.jasypt.encryption.pbe.config;
/*     */ 
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ 
/*     */ public class EnvironmentStringPBEConfig extends EnvironmentPBEConfig
/*     */   implements StringPBEConfig
/*     */ {
/*  53 */   private String stringOutputType = null;
/*     */ 
/*  55 */   private String stringOutputTypeEnvName = null;
/*     */ 
/*  57 */   private String stringOutputTypeSysPropertyName = null;
/*     */ 
/*     */   public String getStringOutputTypeEnvName()
/*     */   {
/*  78 */     return this.stringOutputTypeEnvName;
/*     */   }
/*     */ 
/*     */   public void setStringOutputTypeEnvName(String stringOutputTypeEnvName)
/*     */   {
/*  89 */     this.stringOutputTypeEnvName = stringOutputTypeEnvName;
/*  90 */     if (stringOutputTypeEnvName == null) {
/*  91 */       this.stringOutputType = null;
/*     */     } else {
/*  93 */       this.stringOutputTypeSysPropertyName = null;
/*  94 */       this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getenv(stringOutputTypeEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getStringOutputTypeSysPropertyName()
/*     */   {
/* 108 */     return this.stringOutputTypeSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setStringOutputTypeSysPropertyName(String stringOutputTypeSysPropertyName)
/*     */   {
/* 119 */     this.stringOutputTypeSysPropertyName = stringOutputTypeSysPropertyName;
/* 120 */     if (stringOutputTypeSysPropertyName == null) {
/* 121 */       this.stringOutputType = null;
/*     */     } else {
/* 123 */       this.stringOutputTypeEnvName = null;
/* 124 */       this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getProperty(stringOutputTypeSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setStringOutputType(String stringOutputType)
/*     */   {
/* 147 */     this.stringOutputTypeEnvName = null;
/* 148 */     this.stringOutputTypeSysPropertyName = null;
/* 149 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public String getStringOutputType()
/*     */   {
/* 156 */     return this.stringOutputType;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig
 * JD-Core Version:    0.6.2
 */