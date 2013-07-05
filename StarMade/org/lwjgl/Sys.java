/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public final class Sys
/*     */ {
/*     */   private static final String JNI_LIBRARY_NAME = "lwjgl";
/*     */   private static final String VERSION = "2.9.0";
/*     */   private static final String POSTFIX64BIT = "64";
/* 111 */   private static final SysImplementation implementation = createImplementation();
/*     */   private static final boolean is64Bit;
/*     */ 
/*     */   private static void doLoadLibrary(String lib_name)
/*     */   {
/*  66 */     AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public Object run() {
/*  68 */         String library_path = System.getProperty("org.lwjgl.librarypath");
/*  69 */         if (library_path != null) {
/*  70 */           System.load(library_path + File.separator + System.mapLibraryName(this.val$lib_name));
/*     */         }
/*     */         else {
/*  73 */           System.loadLibrary(this.val$lib_name);
/*     */         }
/*  75 */         return null;
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private static void loadLibrary(String lib_name)
/*     */   {
/*  82 */     String osArch = System.getProperty("os.arch");
/*  83 */     boolean is64bit = ("amd64".equals(osArch)) || ("x86_64".equals(osArch));
/*  84 */     if (is64bit) {
/*     */       try {
/*  86 */         doLoadLibrary(lib_name + "64");
/*  87 */         return;
/*     */       } catch (UnsatisfiedLinkError e) {
/*  89 */         LWJGLUtil.log("Failed to load 64 bit library: " + e.getMessage());
/*     */       }
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  95 */       doLoadLibrary(lib_name);
/*     */     } catch (UnsatisfiedLinkError e) {
/*  97 */       if (implementation.has64Bit()) {
/*     */         try {
/*  99 */           doLoadLibrary(lib_name + "64");
/* 100 */           return;
/*     */         } catch (UnsatisfiedLinkError e2) {
/* 102 */           LWJGLUtil.log("Failed to load 64 bit library: " + e2.getMessage());
/*     */         }
/*     */       }
/*     */ 
/* 106 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static SysImplementation createImplementation()
/*     */   {
/* 124 */     switch (LWJGLUtil.getPlatform()) {
/*     */     case 1:
/* 126 */       return new LinuxSysImplementation();
/*     */     case 3:
/* 128 */       return new WindowsSysImplementation();
/*     */     case 2:
/* 130 */       return new MacOSXSysImplementation();
/*     */     }
/* 132 */     throw new IllegalStateException("Unsupported platform");
/*     */   }
/*     */ 
/*     */   public static String getVersion()
/*     */   {
/* 146 */     return "2.9.0";
/*     */   }
/*     */ 
/*     */   public static void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public static boolean is64Bit()
/*     */   {
/* 157 */     return is64Bit;
/*     */   }
/*     */ 
/*     */   public static long getTimerResolution()
/*     */   {
/* 167 */     return implementation.getTimerResolution();
/*     */   }
/*     */ 
/*     */   public static long getTime()
/*     */   {
/* 178 */     return implementation.getTime() & 0xFFFFFFFF;
/*     */   }
/*     */ 
/*     */   public static void alert(String title, String message)
/*     */   {
/* 200 */     boolean grabbed = Mouse.isGrabbed();
/* 201 */     if (grabbed) {
/* 202 */       Mouse.setGrabbed(false);
/*     */     }
/* 204 */     if (title == null)
/* 205 */       title = "";
/* 206 */     if (message == null)
/* 207 */       message = "";
/* 208 */     implementation.alert(title, message);
/* 209 */     if (grabbed)
/* 210 */       Mouse.setGrabbed(true);
/*     */   }
/*     */ 
/*     */   public static boolean openURL(String url)
/*     */   {
/*     */     try
/*     */     {
/* 231 */       Class serviceManagerClass = Class.forName("javax.jnlp.ServiceManager");
/* 232 */       Method lookupMethod = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Method run() throws Exception {
/* 234 */           return this.val$serviceManagerClass.getMethod("lookup", new Class[] { String.class });
/*     */         }
/*     */       });
/* 237 */       Object basicService = lookupMethod.invoke(serviceManagerClass, new Object[] { "javax.jnlp.BasicService" });
/* 238 */       Class basicServiceClass = Class.forName("javax.jnlp.BasicService");
/* 239 */       Method showDocumentMethod = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Method run() throws Exception {
/* 241 */           return this.val$basicServiceClass.getMethod("showDocument", new Class[] { URL.class });
/*     */         }
/*     */       });
/*     */       try {
/* 245 */         Boolean ret = (Boolean)showDocumentMethod.invoke(basicService, new Object[] { new URL(url) });
/* 246 */         return ret.booleanValue();
/*     */       } catch (MalformedURLException e) {
/* 248 */         e.printStackTrace(System.err);
/* 249 */         return false;
/*     */       }
/*     */     } catch (Exception ue) {  }
/*     */ 
/* 252 */     return implementation.openURL(url);
/*     */   }
/*     */ 
/*     */   public static String getClipboard()
/*     */   {
/* 265 */     return implementation.getClipboard();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 112 */     loadLibrary("lwjgl");
/* 113 */     is64Bit = implementation.getPointerSize() == 8;
/*     */ 
/* 115 */     int native_jni_version = implementation.getJNIVersion();
/* 116 */     int required_version = implementation.getRequiredJNIVersion();
/* 117 */     if (native_jni_version != required_version) {
/* 118 */       throw new LinkageError("Version mismatch: jar version is '" + required_version + "', native library version is '" + native_jni_version + "'");
/*     */     }
/* 120 */     implementation.setDebug(LWJGLUtil.DEBUG);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.Sys
 * JD-Core Version:    0.6.2
 */