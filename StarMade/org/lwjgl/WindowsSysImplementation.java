/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import org.lwjgl.opengl.Display;
/*     */ 
/*     */ final class WindowsSysImplementation extends DefaultSysImplementation
/*     */ {
/*     */   private static final int JNI_VERSION = 24;
/*     */ 
/*     */   public int getRequiredJNIVersion()
/*     */   {
/*  56 */     return 24;
/*     */   }
/*     */ 
/*     */   public long getTimerResolution() {
/*  60 */     return 1000L;
/*     */   }
/*     */ 
/*     */   public long getTime() {
/*  64 */     return nGetTime();
/*     */   }
/*     */   private static native long nGetTime();
/*     */ 
/*     */   public boolean has64Bit() {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   private static long getHwnd() {
/*  73 */     if (!Display.isCreated()) {
/*  74 */       return 0L;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  79 */       return ((Long)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Long run() throws Exception {
/*  81 */           Method getImplementation_method = Display.class.getDeclaredMethod("getImplementation", new Class[0]);
/*  82 */           getImplementation_method.setAccessible(true);
/*  83 */           Object display_impl = getImplementation_method.invoke(null, new Object[0]);
/*  84 */           Class WindowsDisplay_class = Class.forName("org.lwjgl.opengl.WindowsDisplay");
/*  85 */           Method getHwnd_method = WindowsDisplay_class.getDeclaredMethod("getHwnd", new Class[0]);
/*  86 */           getHwnd_method.setAccessible(true);
/*  87 */           return (Long)getHwnd_method.invoke(display_impl, new Object[0]);
/*     */         }
/*     */       })).longValue();
/*     */     }
/*     */     catch (PrivilegedActionException e)
/*     */     {
/*  91 */       throw new Error(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void alert(String title, String message) {
/*  96 */     if (!Display.isCreated()) {
/*  97 */       initCommonControls();
/*     */     }
/*     */ 
/* 100 */     LWJGLUtil.log(String.format("*** Alert *** %s\n%s\n", new Object[] { title, message }));
/*     */ 
/* 102 */     ByteBuffer titleText = MemoryUtil.encodeUTF16(title);
/* 103 */     ByteBuffer messageText = MemoryUtil.encodeUTF16(message);
/* 104 */     nAlert(getHwnd(), MemoryUtil.getAddress(titleText), MemoryUtil.getAddress(messageText));
/*     */   }
/*     */   private static native void nAlert(long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   private static native void initCommonControls();
/*     */ 
/*     */   public boolean openURL(String url) {
/*     */     try { LWJGLUtil.execPrivileged(new String[] { "rundll32", "url.dll,FileProtocolHandler", url });
/* 112 */       return true;
/*     */     } catch (Exception e) {
/* 114 */       LWJGLUtil.log("Failed to open url (" + url + "): " + e.getMessage());
/* 115 */     }return false;
/*     */   }
/*     */ 
/*     */   public String getClipboard()
/*     */   {
/* 120 */     return nGetClipboard();
/*     */   }
/*     */ 
/*     */   private static native String nGetClipboard();
/*     */ 
/*     */   static
/*     */   {
/*  52 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.WindowsSysImplementation
 * JD-Core Version:    0.6.2
 */