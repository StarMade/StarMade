/*    */ package org.jasypt.encryption.pbe.config;
/*    */ 
/*    */ import org.jasypt.commons.CommonUtils;
/*    */ 
/*    */ public class SimpleStringPBEConfig extends SimplePBEConfig
/*    */   implements StringPBEConfig
/*    */ {
/* 46 */   private String stringOutputType = null;
/*    */ 
/*    */   public void setStringOutputType(String stringOutputType)
/*    */   {
/* 78 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*    */   }
/*    */ 
/*    */   public String getStringOutputType()
/*    */   {
/* 85 */     return this.stringOutputType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
 * JD-Core Version:    0.6.2
 */