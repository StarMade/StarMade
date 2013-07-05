/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class LWJGLUtil
/*     */ {
/*     */   public static final int PLATFORM_LINUX = 1;
/*     */   public static final int PLATFORM_MACOSX = 2;
/*     */   public static final int PLATFORM_WINDOWS = 3;
/*     */   public static final String PLATFORM_LINUX_NAME = "linux";
/*     */   public static final String PLATFORM_MACOSX_NAME = "macosx";
/*     */   public static final String PLATFORM_WINDOWS_NAME = "windows";
/*     */   private static final String LWJGL_ICON_DATA_16x16 = "";
/*     */   private static final String LWJGL_ICON_DATA_32x32 = "";
/* 259 */   public static final ByteBuffer LWJGLIcon16x16 = loadIcon("");
/*     */ 
/* 262 */   public static final ByteBuffer LWJGLIcon32x32 = loadIcon("");
/*     */ 
/* 265 */   public static final boolean DEBUG = getPrivilegedBoolean("org.lwjgl.util.Debug");
/*     */ 
/* 267 */   public static final boolean CHECKS = !getPrivilegedBoolean("org.lwjgl.util.NoChecks");
/*     */   private static final int PLATFORM;
/*     */ 
/*     */   private static ByteBuffer loadIcon(String data)
/*     */   {
/* 284 */     int len = data.length();
/* 285 */     ByteBuffer bb = BufferUtils.createByteBuffer(len);
/* 286 */     for (int i = 0; i < len; i++) {
/* 287 */       bb.put(i, (byte)data.charAt(i));
/*     */     }
/* 289 */     return bb.asReadOnlyBuffer();
/*     */   }
/*     */ 
/*     */   public static int getPlatform()
/*     */   {
/* 299 */     return PLATFORM;
/*     */   }
/*     */ 
/*     */   public static String getPlatformName()
/*     */   {
/* 310 */     switch (getPlatform()) {
/*     */     case 1:
/* 312 */       return "linux";
/*     */     case 2:
/* 314 */       return "macosx";
/*     */     case 3:
/* 316 */       return "windows";
/*     */     }
/* 318 */     return "unknown";
/*     */   }
/*     */ 
/*     */   public static String[] getLibraryPaths(String libname, String platform_lib_name, ClassLoader classloader)
/*     */   {
/* 331 */     return getLibraryPaths(libname, new String[] { platform_lib_name }, classloader);
/*     */   }
/*     */ 
/*     */   public static String[] getLibraryPaths(String libname, String[] platform_lib_names, ClassLoader classloader)
/*     */   {
/* 344 */     List possible_paths = new ArrayList();
/*     */ 
/* 346 */     String classloader_path = getPathFromClassLoader(libname, classloader);
/* 347 */     if (classloader_path != null) {
/* 348 */       log("getPathFromClassLoader: Path found: " + classloader_path);
/* 349 */       possible_paths.add(classloader_path);
/*     */     }
/*     */ 
/* 352 */     for (String platform_lib_name : platform_lib_names) {
/* 353 */       String lwjgl_classloader_path = getPathFromClassLoader("lwjgl", classloader);
/* 354 */       if (lwjgl_classloader_path != null) {
/* 355 */         log("getPathFromClassLoader: Path found: " + lwjgl_classloader_path);
/* 356 */         possible_paths.add(lwjgl_classloader_path.substring(0, lwjgl_classloader_path.lastIndexOf(File.separator)) + File.separator + platform_lib_name);
/*     */       }
/*     */ 
/* 361 */       String alternative_path = getPrivilegedProperty("org.lwjgl.librarypath");
/* 362 */       if (alternative_path != null) {
/* 363 */         possible_paths.add(alternative_path + File.separator + platform_lib_name);
/*     */       }
/*     */ 
/* 367 */       String java_library_path = getPrivilegedProperty("java.library.path");
/*     */ 
/* 369 */       StringTokenizer st = new StringTokenizer(java_library_path, File.pathSeparator);
/* 370 */       while (st.hasMoreTokens()) {
/* 371 */         String path = st.nextToken();
/* 372 */         possible_paths.add(path + File.separator + platform_lib_name);
/*     */       }
/*     */ 
/* 376 */       String current_dir = getPrivilegedProperty("user.dir");
/* 377 */       possible_paths.add(current_dir + File.separator + platform_lib_name);
/*     */ 
/* 380 */       possible_paths.add(platform_lib_name);
/*     */     }
/*     */ 
/* 384 */     return (String[])possible_paths.toArray(new String[possible_paths.size()]);
/*     */   }
/*     */ 
/*     */   static void execPrivileged(String[] cmd_array) throws Exception {
/*     */     try {
/* 389 */       Process process = (Process)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Process run() throws Exception {
/* 391 */           return Runtime.getRuntime().exec(this.val$cmd_array);
/*     */         }
/*     */       });
/* 395 */       process.getInputStream().close();
/* 396 */       process.getOutputStream().close();
/* 397 */       process.getErrorStream().close();
/*     */     } catch (PrivilegedActionException e) {
/* 399 */       throw ((Exception)e.getCause());
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String getPrivilegedProperty(String property_name) {
/* 404 */     return (String)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public String run() {
/* 406 */         return System.getProperty(this.val$property_name);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private static String getPathFromClassLoader(final String libname, final ClassLoader classloader)
/*     */   {
/*     */     try
/*     */     {
/* 424 */       log("getPathFromClassLoader: searching for: " + libname);
/* 425 */       Class c = classloader.getClass();
/* 426 */       while (c != null) {
/* 427 */         Class clazz = c;
/*     */         try {
/* 429 */           return (String)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */             public String run() throws Exception {
/* 431 */               Method findLibrary = this.val$clazz.getDeclaredMethod("findLibrary", new Class[] { String.class });
/* 432 */               findLibrary.setAccessible(true);
/* 433 */               String path = (String)findLibrary.invoke(classloader, new Object[] { libname });
/* 434 */               return path;
/*     */             } } );
/*     */         }
/*     */         catch (PrivilegedActionException e) {
/* 438 */           log("Failed to locate findLibrary method: " + e.getCause());
/* 439 */           c = c.getSuperclass();
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 443 */       log("Failure locating " + e + " using classloader:" + e);
/*     */     }
/* 445 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean getPrivilegedBoolean(String property_name)
/*     */   {
/* 452 */     return ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public Boolean run() {
/* 454 */         return Boolean.valueOf(Boolean.getBoolean(this.val$property_name));
/*     */       }
/*     */     })).booleanValue();
/*     */   }
/*     */ 
/*     */   public static Integer getPrivilegedInteger(String property_name)
/*     */   {
/* 467 */     return (Integer)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public Integer run() {
/* 469 */         return Integer.getInteger(this.val$property_name);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public static Integer getPrivilegedInteger(String property_name, final int default_val)
/*     */   {
/* 483 */     return (Integer)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public Integer run() {
/* 485 */         return Integer.getInteger(this.val$property_name, default_val);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public static void log(CharSequence msg)
/*     */   {
/* 496 */     if (DEBUG)
/* 497 */       System.err.println("[LWJGL] " + msg);
/*     */   }
/*     */ 
/*     */   public static boolean isMacOSXEqualsOrBetterThan(int major_required, int minor_required)
/*     */   {
/* 507 */     String os_version = getPrivilegedProperty("os.version");
/* 508 */     StringTokenizer version_tokenizer = new StringTokenizer(os_version, ".");
/*     */     int major;
/*     */     int minor;
/*     */     try
/*     */     {
/* 512 */       String major_str = version_tokenizer.nextToken();
/* 513 */       String minor_str = version_tokenizer.nextToken();
/* 514 */       major = Integer.parseInt(major_str);
/* 515 */       minor = Integer.parseInt(minor_str);
/*     */     } catch (Exception e) {
/* 517 */       log("Exception occurred while trying to determine OS version: " + e);
/*     */ 
/* 519 */       return false;
/*     */     }
/* 521 */     return (major > major_required) || ((major == major_required) && (minor >= minor_required));
/*     */   }
/*     */ 
/*     */   public static Map<Integer, String> getClassTokens(TokenFilter filter, Map<Integer, String> target, Class[] tokenClasses)
/*     */   {
/* 539 */     return getClassTokens(filter, target, Arrays.asList(tokenClasses));
/*     */   }
/*     */ 
/*     */   public static Map<Integer, String> getClassTokens(TokenFilter filter, Map<Integer, String> target, Iterable<Class> tokenClasses)
/*     */   {
/* 556 */     if (target == null) {
/* 557 */       target = new HashMap();
/*     */     }
/* 559 */     int TOKEN_MODIFIERS = 25;
/*     */ 
/* 561 */     for (Class tokenClass : tokenClasses) {
/* 562 */       for (Field field : tokenClass.getDeclaredFields())
/*     */       {
/* 564 */         if (((field.getModifiers() & 0x19) == 25) && (field.getType() == Integer.TYPE))
/*     */           try {
/* 566 */             int value = field.getInt(null);
/* 567 */             if ((filter == null) || (filter.accept(field, value)))
/*     */             {
/* 570 */               if (target.containsKey(Integer.valueOf(value)))
/* 571 */                 target.put(Integer.valueOf(value), toHexString(value));
/*     */               else
/* 573 */                 target.put(Integer.valueOf(value), field.getName());
/*     */             }
/*     */           }
/*     */           catch (IllegalAccessException e)
/*     */           {
/*     */           }
/*     */       }
/*     */     }
/* 581 */     return target;
/*     */   }
/*     */ 
/*     */   public static String toHexString(int value)
/*     */   {
/* 594 */     return "0x" + Integer.toHexString(value).toUpperCase();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 272 */     String osName = getPrivilegedProperty("os.name");
/* 273 */     if (osName.startsWith("Windows"))
/* 274 */       PLATFORM = 3;
/* 275 */     else if ((osName.startsWith("Linux")) || (osName.startsWith("FreeBSD")) || (osName.startsWith("SunOS")) || (osName.startsWith("Unix")))
/* 276 */       PLATFORM = 1;
/* 277 */     else if ((osName.startsWith("Mac OS X")) || (osName.startsWith("Darwin")))
/* 278 */       PLATFORM = 2;
/*     */     else
/* 280 */       throw new LinkageError("Unknown platform: " + osName);
/*     */   }
/*     */ 
/*     */   public static abstract interface TokenFilter
/*     */   {
/*     */     public abstract boolean accept(Field paramField, int paramInt);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.LWJGLUtil
 * JD-Core Version:    0.6.2
 */