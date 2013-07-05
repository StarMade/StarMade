/*    */ package org.jasypt.encryption.pbe.config;
/*    */ 
/*    */ import org.jasypt.commons.CommonUtils;
/*    */ 
/*    */ public class WebStringPBEConfig extends WebPBEConfig
/*    */   implements StringPBEConfig
/*    */ {
/* 59 */   private String stringOutputType = null;
/*    */ 
/*    */   public void setStringOutputType(String stringOutputType)
/*    */   {
/* 88 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*    */   }
/*    */ 
/*    */   public String getStringOutputType()
/*    */   {
/* 95 */     return this.stringOutputType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.WebStringPBEConfig
 * JD-Core Version:    0.6.2
 */