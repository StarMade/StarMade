/*    */ package org.lwjgl;
/*    */ 
/*    */ import com.apple.eio.FileManager;
/*    */ import java.awt.Toolkit;
/*    */ 
/*    */ final class MacOSXSysImplementation extends J2SESysImplementation
/*    */ {
/*    */   private static final int JNI_VERSION = 25;
/*    */ 
/*    */   public int getRequiredJNIVersion()
/*    */   {
/* 55 */     return 25;
/*    */   }
/*    */ 
/*    */   public boolean openURL(String url) {
/*    */     try {
/* 60 */       FileManager.openURL(url);
/* 61 */       return true;
/*    */     } catch (Exception e) {
/* 63 */       LWJGLUtil.log("Exception occurred while trying to invoke browser: " + e);
/* 64 */     }return false;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 51 */     Toolkit.getDefaultToolkit();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.MacOSXSysImplementation
 * JD-Core Version:    0.6.2
 */