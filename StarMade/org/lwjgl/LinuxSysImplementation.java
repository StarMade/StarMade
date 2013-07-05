/*    */ package org.lwjgl;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.security.AccessController;
/*    */ import java.security.PrivilegedAction;
/*    */ 
/*    */ final class LinuxSysImplementation extends J2SESysImplementation
/*    */ {
/*    */   private static final int JNI_VERSION = 19;
/*    */ 
/*    */   public int getRequiredJNIVersion()
/*    */   {
/* 67 */     return 19;
/*    */   }
/*    */ 
/*    */   public boolean openURL(String url)
/*    */   {
/* 74 */     String[] browsers = { "xdg-open", "firefox", "mozilla", "opera", "konqueror", "nautilus", "galeon", "netscape" };
/*    */ 
/* 76 */     for (String browser : browsers) {
/*    */       try {
/* 78 */         LWJGLUtil.execPrivileged(new String[] { browser, url });
/* 79 */         return true;
/*    */       }
/*    */       catch (Exception e) {
/* 82 */         e.printStackTrace(System.err);
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 87 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean has64Bit() {
/* 91 */     return true;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 50 */     Toolkit.getDefaultToolkit();
/*    */ 
/* 53 */     AccessController.doPrivileged(new PrivilegedAction() {
/*    */       public Object run() {
/*    */         try {
/* 56 */           System.loadLibrary("jawt");
/*    */         }
/*    */         catch (UnsatisfiedLinkError e)
/*    */         {
/*    */         }
/* 61 */         return null;
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.LinuxSysImplementation
 * JD-Core Version:    0.6.2
 */