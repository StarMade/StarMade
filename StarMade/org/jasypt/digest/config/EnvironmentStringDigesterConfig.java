/*     */ package org.jasypt.digest.config;
/*     */ 
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ 
/*     */ public class EnvironmentStringDigesterConfig extends EnvironmentDigesterConfig
/*     */   implements StringDigesterConfig
/*     */ {
/*  54 */   private Boolean unicodeNormalizationIgnored = null;
/*  55 */   private String stringOutputType = null;
/*  56 */   private String prefix = null;
/*  57 */   private String suffix = null;
/*     */ 
/*  59 */   private String unicodeNormalizationIgnoredEnvName = null;
/*  60 */   private String stringOutputTypeEnvName = null;
/*  61 */   private String prefixEnvName = null;
/*  62 */   private String suffixEnvName = null;
/*     */ 
/*  64 */   private String unicodeNormalizationIgnoredSysPropertyName = null;
/*  65 */   private String stringOutputTypeSysPropertyName = null;
/*  66 */   private String prefixSysPropertyName = null;
/*  67 */   private String suffixSysPropertyName = null;
/*     */ 
/*     */   public String getUnicodeNormalizationIgnoredEnvName()
/*     */   {
/*  89 */     return this.unicodeNormalizationIgnoredEnvName;
/*     */   }
/*     */ 
/*     */   public void setUnicodeNormalizationIgnoredEnvName(String unicodeNormalizationIgnoredEnvName)
/*     */   {
/* 101 */     this.unicodeNormalizationIgnoredEnvName = unicodeNormalizationIgnoredEnvName;
/*     */ 
/* 103 */     if (unicodeNormalizationIgnoredEnvName == null) {
/* 104 */       this.unicodeNormalizationIgnored = null;
/*     */     } else {
/* 106 */       this.unicodeNormalizationIgnoredSysPropertyName = null;
/* 107 */       String unicodeNormalizationIgnoredValue = System.getenv(unicodeNormalizationIgnoredEnvName);
/*     */ 
/* 109 */       if (unicodeNormalizationIgnoredValue != null) {
/* 110 */         this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnoredValue);
/*     */       }
/*     */       else
/*     */       {
/* 114 */         this.unicodeNormalizationIgnored = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getUnicodeNormalizationIgnoredSysPropertyName()
/*     */   {
/* 127 */     return this.unicodeNormalizationIgnoredSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setUnicodeNormalizationIgnoredSysPropertyName(String unicodeNormalizationIgnoredSysPropertyName)
/*     */   {
/* 138 */     this.unicodeNormalizationIgnoredSysPropertyName = unicodeNormalizationIgnoredSysPropertyName;
/*     */ 
/* 140 */     if (unicodeNormalizationIgnoredSysPropertyName == null) {
/* 141 */       this.unicodeNormalizationIgnored = null;
/*     */     } else {
/* 143 */       this.unicodeNormalizationIgnoredEnvName = null;
/* 144 */       String unicodeNormalizationIgnoredValue = System.getProperty(unicodeNormalizationIgnoredSysPropertyName);
/*     */ 
/* 146 */       if (unicodeNormalizationIgnoredValue != null) {
/* 147 */         this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnoredValue);
/*     */       }
/*     */       else
/*     */       {
/* 151 */         this.unicodeNormalizationIgnored = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getStringOutputTypeEnvName()
/*     */   {
/* 164 */     return this.stringOutputTypeEnvName;
/*     */   }
/*     */ 
/*     */   public void setStringOutputTypeEnvName(String stringOutputTypeEnvName)
/*     */   {
/* 175 */     this.stringOutputTypeEnvName = stringOutputTypeEnvName;
/* 176 */     if (stringOutputTypeEnvName == null) {
/* 177 */       this.stringOutputType = null;
/*     */     } else {
/* 179 */       this.stringOutputTypeSysPropertyName = null;
/* 180 */       this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getenv(stringOutputTypeEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getStringOutputTypeSysPropertyName()
/*     */   {
/* 194 */     return this.stringOutputTypeSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setStringOutputTypeSysPropertyName(String stringOutputTypeSysPropertyName)
/*     */   {
/* 205 */     this.stringOutputTypeSysPropertyName = stringOutputTypeSysPropertyName;
/* 206 */     if (stringOutputTypeSysPropertyName == null) {
/* 207 */       this.stringOutputType = null;
/*     */     } else {
/* 209 */       this.stringOutputTypeEnvName = null;
/* 210 */       this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getProperty(stringOutputTypeSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored)
/*     */   {
/* 246 */     this.unicodeNormalizationIgnoredEnvName = null;
/* 247 */     this.unicodeNormalizationIgnoredSysPropertyName = null;
/* 248 */     this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
/*     */   }
/*     */ 
/*     */   public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored)
/*     */   {
/* 283 */     this.unicodeNormalizationIgnoredEnvName = null;
/* 284 */     this.unicodeNormalizationIgnoredSysPropertyName = null;
/* 285 */     if (unicodeNormalizationIgnored != null) {
/* 286 */       this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
/*     */     }
/*     */     else
/* 289 */       this.unicodeNormalizationIgnored = null;
/*     */   }
/*     */ 
/*     */   public void setStringOutputType(String stringOutputType)
/*     */   {
/* 310 */     this.stringOutputTypeEnvName = null;
/* 311 */     this.stringOutputTypeSysPropertyName = null;
/* 312 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix)
/*     */   {
/* 338 */     this.prefixEnvName = null;
/* 339 */     this.prefixSysPropertyName = null;
/* 340 */     this.prefix = prefix;
/*     */   }
/*     */ 
/*     */   public void setSuffix(String suffix)
/*     */   {
/* 363 */     this.suffixEnvName = null;
/* 364 */     this.suffixSysPropertyName = null;
/* 365 */     this.suffix = suffix;
/*     */   }
/*     */ 
/*     */   public Boolean isUnicodeNormalizationIgnored()
/*     */   {
/* 371 */     return this.unicodeNormalizationIgnored;
/*     */   }
/*     */ 
/*     */   public String getStringOutputType()
/*     */   {
/* 376 */     return this.stringOutputType;
/*     */   }
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/* 382 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public String getSuffix()
/*     */   {
/* 387 */     return this.suffix;
/*     */   }
/*     */ 
/*     */   public String getPrefixEnvName()
/*     */   {
/* 405 */     return this.prefixEnvName;
/*     */   }
/*     */ 
/*     */   public void setPrefixEnvName(String prefixEnvName)
/*     */   {
/* 420 */     this.prefixEnvName = prefixEnvName;
/* 421 */     if (prefixEnvName == null) {
/* 422 */       this.prefix = null;
/*     */     } else {
/* 424 */       this.prefixSysPropertyName = null;
/* 425 */       this.prefix = System.getenv(prefixEnvName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPrefixSysPropertyName()
/*     */   {
/* 440 */     return this.prefixSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setPrefixSysPropertyName(String prefixSysPropertyName)
/*     */   {
/* 454 */     this.prefixSysPropertyName = prefixSysPropertyName;
/* 455 */     if (prefixSysPropertyName == null) {
/* 456 */       this.prefix = null;
/*     */     } else {
/* 458 */       this.prefixEnvName = null;
/* 459 */       this.prefix = System.getProperty(prefixSysPropertyName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSuffixEnvName()
/*     */   {
/* 474 */     return this.suffixEnvName;
/*     */   }
/*     */ 
/*     */   public void setSuffixEnvName(String suffixEnvName)
/*     */   {
/* 489 */     this.suffixEnvName = suffixEnvName;
/* 490 */     if (suffixEnvName == null) {
/* 491 */       this.suffix = null;
/*     */     } else {
/* 493 */       this.suffixSysPropertyName = null;
/* 494 */       this.suffix = System.getenv(suffixEnvName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSuffixSysPropertyName()
/*     */   {
/* 509 */     return this.suffixSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setSuffixSysPropertyName(String suffixSysPropertyName)
/*     */   {
/* 523 */     this.suffixSysPropertyName = suffixSysPropertyName;
/* 524 */     if (suffixSysPropertyName == null) {
/* 525 */       this.suffix = null;
/*     */     } else {
/* 527 */       this.suffixEnvName = null;
/* 528 */       this.suffix = System.getProperty(suffixSysPropertyName);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.EnvironmentStringDigesterConfig
 * JD-Core Version:    0.6.2
 */