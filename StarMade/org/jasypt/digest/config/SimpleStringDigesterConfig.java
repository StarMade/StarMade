/*     */ package org.jasypt.digest.config;
/*     */ 
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ 
/*     */ public class SimpleStringDigesterConfig extends SimpleDigesterConfig
/*     */   implements StringDigesterConfig
/*     */ {
/*  47 */   private Boolean unicodeNormalizationIgnored = null;
/*  48 */   private String stringOutputType = null;
/*  49 */   private String prefix = null;
/*  50 */   private String suffix = null;
/*     */ 
/*     */   public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored)
/*     */   {
/*  94 */     this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
/*     */   }
/*     */ 
/*     */   public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored)
/*     */   {
/* 132 */     if (unicodeNormalizationIgnored != null) {
/* 133 */       this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
/*     */     }
/*     */     else
/* 136 */       this.unicodeNormalizationIgnored = null;
/*     */   }
/*     */ 
/*     */   public void setStringOutputType(String stringOutputType)
/*     */   {
/* 160 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix)
/*     */   {
/* 184 */     this.prefix = prefix;
/*     */   }
/*     */ 
/*     */   public void setSuffix(String suffix)
/*     */   {
/* 206 */     this.suffix = suffix;
/*     */   }
/*     */ 
/*     */   public Boolean isUnicodeNormalizationIgnored()
/*     */   {
/* 212 */     return this.unicodeNormalizationIgnored;
/*     */   }
/*     */ 
/*     */   public String getStringOutputType()
/*     */   {
/* 217 */     return this.stringOutputType;
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/* 221 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public String getSuffix() {
/* 225 */     return this.suffix;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.SimpleStringDigesterConfig
 * JD-Core Version:    0.6.2
 */