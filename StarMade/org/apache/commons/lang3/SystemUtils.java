/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ public class SystemUtils
/*      */ {
/*      */   private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
/*      */   private static final String USER_HOME_KEY = "user.home";
/*      */   private static final String USER_DIR_KEY = "user.dir";
/*      */   private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
/*      */   private static final String JAVA_HOME_KEY = "java.home";
/*   89 */   public static final String AWT_TOOLKIT = getSystemProperty("awt.toolkit");
/*      */ 
/*  111 */   public static final String FILE_ENCODING = getSystemProperty("file.encoding");
/*      */ 
/*  129 */   public static final String FILE_SEPARATOR = getSystemProperty("file.separator");
/*      */ 
/*  147 */   public static final String JAVA_AWT_FONTS = getSystemProperty("java.awt.fonts");
/*      */ 
/*  165 */   public static final String JAVA_AWT_GRAPHICSENV = getSystemProperty("java.awt.graphicsenv");
/*      */ 
/*  186 */   public static final String JAVA_AWT_HEADLESS = getSystemProperty("java.awt.headless");
/*      */ 
/*  204 */   public static final String JAVA_AWT_PRINTERJOB = getSystemProperty("java.awt.printerjob");
/*      */ 
/*  222 */   public static final String JAVA_CLASS_PATH = getSystemProperty("java.class.path");
/*      */ 
/*  240 */   public static final String JAVA_CLASS_VERSION = getSystemProperty("java.class.version");
/*      */ 
/*  259 */   public static final String JAVA_COMPILER = getSystemProperty("java.compiler");
/*      */ 
/*  277 */   public static final String JAVA_ENDORSED_DIRS = getSystemProperty("java.endorsed.dirs");
/*      */ 
/*  295 */   public static final String JAVA_EXT_DIRS = getSystemProperty("java.ext.dirs");
/*      */ 
/*  313 */   public static final String JAVA_HOME = getSystemProperty("java.home");
/*      */ 
/*  331 */   public static final String JAVA_IO_TMPDIR = getSystemProperty("java.io.tmpdir");
/*      */ 
/*  349 */   public static final String JAVA_LIBRARY_PATH = getSystemProperty("java.library.path");
/*      */ 
/*  368 */   public static final String JAVA_RUNTIME_NAME = getSystemProperty("java.runtime.name");
/*      */ 
/*  387 */   public static final String JAVA_RUNTIME_VERSION = getSystemProperty("java.runtime.version");
/*      */ 
/*  405 */   public static final String JAVA_SPECIFICATION_NAME = getSystemProperty("java.specification.name");
/*      */ 
/*  423 */   public static final String JAVA_SPECIFICATION_VENDOR = getSystemProperty("java.specification.vendor");
/*      */ 
/*  441 */   public static final String JAVA_SPECIFICATION_VERSION = getSystemProperty("java.specification.version");
/*  442 */   private static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(JAVA_SPECIFICATION_VERSION);
/*      */ 
/*  461 */   public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = getSystemProperty("java.util.prefs.PreferencesFactory");
/*      */ 
/*  480 */   public static final String JAVA_VENDOR = getSystemProperty("java.vendor");
/*      */ 
/*  498 */   public static final String JAVA_VENDOR_URL = getSystemProperty("java.vendor.url");
/*      */ 
/*  516 */   public static final String JAVA_VERSION = getSystemProperty("java.version");
/*      */ 
/*  535 */   public static final String JAVA_VM_INFO = getSystemProperty("java.vm.info");
/*      */ 
/*  553 */   public static final String JAVA_VM_NAME = getSystemProperty("java.vm.name");
/*      */ 
/*  571 */   public static final String JAVA_VM_SPECIFICATION_NAME = getSystemProperty("java.vm.specification.name");
/*      */ 
/*  589 */   public static final String JAVA_VM_SPECIFICATION_VENDOR = getSystemProperty("java.vm.specification.vendor");
/*      */ 
/*  607 */   public static final String JAVA_VM_SPECIFICATION_VERSION = getSystemProperty("java.vm.specification.version");
/*      */ 
/*  625 */   public static final String JAVA_VM_VENDOR = getSystemProperty("java.vm.vendor");
/*      */ 
/*  643 */   public static final String JAVA_VM_VERSION = getSystemProperty("java.vm.version");
/*      */ 
/*  661 */   public static final String LINE_SEPARATOR = getSystemProperty("line.separator");
/*      */ 
/*  679 */   public static final String OS_ARCH = getSystemProperty("os.arch");
/*      */ 
/*  697 */   public static final String OS_NAME = getSystemProperty("os.name");
/*      */ 
/*  715 */   public static final String OS_VERSION = getSystemProperty("os.version");
/*      */ 
/*  733 */   public static final String PATH_SEPARATOR = getSystemProperty("path.separator");
/*      */ 
/*  753 */   public static final String USER_COUNTRY = getSystemProperty("user.country") == null ? getSystemProperty("user.region") : getSystemProperty("user.country");
/*      */ 
/*  772 */   public static final String USER_DIR = getSystemProperty("user.dir");
/*      */ 
/*  790 */   public static final String USER_HOME = getSystemProperty("user.home");
/*      */ 
/*  809 */   public static final String USER_LANGUAGE = getSystemProperty("user.language");
/*      */ 
/*  827 */   public static final String USER_NAME = getSystemProperty("user.name");
/*      */ 
/*  845 */   public static final String USER_TIMEZONE = getSystemProperty("user.timezone");
/*      */ 
/*  860 */   public static final boolean IS_JAVA_1_1 = getJavaVersionMatches("1.1");
/*      */ 
/*  870 */   public static final boolean IS_JAVA_1_2 = getJavaVersionMatches("1.2");
/*      */ 
/*  880 */   public static final boolean IS_JAVA_1_3 = getJavaVersionMatches("1.3");
/*      */ 
/*  890 */   public static final boolean IS_JAVA_1_4 = getJavaVersionMatches("1.4");
/*      */ 
/*  900 */   public static final boolean IS_JAVA_1_5 = getJavaVersionMatches("1.5");
/*      */ 
/*  910 */   public static final boolean IS_JAVA_1_6 = getJavaVersionMatches("1.6");
/*      */ 
/*  922 */   public static final boolean IS_JAVA_1_7 = getJavaVersionMatches("1.7");
/*      */ 
/*  942 */   public static final boolean IS_OS_AIX = getOSMatchesName("AIX");
/*      */ 
/*  954 */   public static final boolean IS_OS_HP_UX = getOSMatchesName("HP-UX");
/*      */ 
/*  966 */   public static final boolean IS_OS_IRIX = getOSMatchesName("Irix");
/*      */ 
/*  978 */   public static final boolean IS_OS_LINUX = (getOSMatchesName("Linux")) || (getOSMatchesName("LINUX"));
/*      */ 
/*  990 */   public static final boolean IS_OS_MAC = getOSMatchesName("Mac");
/*      */ 
/* 1002 */   public static final boolean IS_OS_MAC_OSX = getOSMatchesName("Mac OS X");
/*      */ 
/* 1014 */   public static final boolean IS_OS_FREE_BSD = getOSMatchesName("FreeBSD");
/*      */ 
/* 1026 */   public static final boolean IS_OS_OPEN_BSD = getOSMatchesName("OpenBSD");
/*      */ 
/* 1038 */   public static final boolean IS_OS_NET_BSD = getOSMatchesName("NetBSD");
/*      */ 
/* 1050 */   public static final boolean IS_OS_OS2 = getOSMatchesName("OS/2");
/*      */ 
/* 1062 */   public static final boolean IS_OS_SOLARIS = getOSMatchesName("Solaris");
/*      */ 
/* 1074 */   public static final boolean IS_OS_SUN_OS = getOSMatchesName("SunOS");
/*      */ 
/* 1086 */   public static final boolean IS_OS_UNIX = (IS_OS_AIX) || (IS_OS_HP_UX) || (IS_OS_IRIX) || (IS_OS_LINUX) || (IS_OS_MAC_OSX) || (IS_OS_SOLARIS) || (IS_OS_SUN_OS) || (IS_OS_FREE_BSD) || (IS_OS_OPEN_BSD) || (IS_OS_NET_BSD);
/*      */ 
/* 1099 */   public static final boolean IS_OS_WINDOWS = getOSMatchesName("Windows");
/*      */ 
/* 1111 */   public static final boolean IS_OS_WINDOWS_2000 = getOSMatches("Windows", "5.0");
/*      */ 
/* 1123 */   public static final boolean IS_OS_WINDOWS_2003 = getOSMatches("Windows", "5.2");
/*      */ 
/* 1135 */   public static final boolean IS_OS_WINDOWS_2008 = getOSMatches("Windows Server 2008", "6.1");
/*      */ 
/* 1147 */   public static final boolean IS_OS_WINDOWS_95 = getOSMatches("Windows 9", "4.0");
/*      */ 
/* 1160 */   public static final boolean IS_OS_WINDOWS_98 = getOSMatches("Windows 9", "4.1");
/*      */ 
/* 1173 */   public static final boolean IS_OS_WINDOWS_ME = getOSMatches("Windows", "4.9");
/*      */ 
/* 1186 */   public static final boolean IS_OS_WINDOWS_NT = getOSMatchesName("Windows NT");
/*      */ 
/* 1199 */   public static final boolean IS_OS_WINDOWS_XP = getOSMatches("Windows", "5.1");
/*      */ 
/* 1212 */   public static final boolean IS_OS_WINDOWS_VISTA = getOSMatches("Windows", "6.0");
/*      */ 
/* 1224 */   public static final boolean IS_OS_WINDOWS_7 = getOSMatches("Windows", "6.1");
/*      */ 
/*      */   public static File getJavaHome()
/*      */   {
/* 1238 */     return new File(System.getProperty("java.home"));
/*      */   }
/*      */ 
/*      */   public static File getJavaIoTmpDir()
/*      */   {
/* 1253 */     return new File(System.getProperty("java.io.tmpdir"));
/*      */   }
/*      */ 
/*      */   private static boolean getJavaVersionMatches(String versionPrefix)
/*      */   {
/* 1265 */     return isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, versionPrefix);
/*      */   }
/*      */ 
/*      */   private static boolean getOSMatches(String osNamePrefix, String osVersionPrefix)
/*      */   {
/* 1276 */     return isOSMatch(OS_NAME, OS_VERSION, osNamePrefix, osVersionPrefix);
/*      */   }
/*      */ 
/*      */   private static boolean getOSMatchesName(String osNamePrefix)
/*      */   {
/* 1286 */     return isOSNameMatch(OS_NAME, osNamePrefix);
/*      */   }
/*      */ 
/*      */   private static String getSystemProperty(String property)
/*      */   {
/*      */     try
/*      */     {
/* 1304 */       return System.getProperty(property);
/*      */     }
/*      */     catch (SecurityException ex) {
/* 1307 */       System.err.println("Caught a SecurityException reading the system property '" + property + "'; the SystemUtils property value will default to null.");
/*      */     }
/* 1309 */     return null;
/*      */   }
/*      */ 
/*      */   public static File getUserDir()
/*      */   {
/* 1325 */     return new File(System.getProperty("user.dir"));
/*      */   }
/*      */ 
/*      */   public static File getUserHome()
/*      */   {
/* 1340 */     return new File(System.getProperty("user.home"));
/*      */   }
/*      */ 
/*      */   public static boolean isJavaAwtHeadless()
/*      */   {
/* 1352 */     return JAVA_AWT_HEADLESS != null ? JAVA_AWT_HEADLESS.equals(Boolean.TRUE.toString()) : false;
/*      */   }
/*      */ 
/*      */   public static boolean isJavaVersionAtLeast(JavaVersion requiredVersion)
/*      */   {
/* 1371 */     return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(requiredVersion);
/*      */   }
/*      */ 
/*      */   static boolean isJavaVersionMatch(String version, String versionPrefix)
/*      */   {
/* 1387 */     if (version == null) {
/* 1388 */       return false;
/*      */     }
/* 1390 */     return version.startsWith(versionPrefix);
/*      */   }
/*      */ 
/*      */   static boolean isOSMatch(String osName, String osVersion, String osNamePrefix, String osVersionPrefix)
/*      */   {
/* 1406 */     if ((osName == null) || (osVersion == null)) {
/* 1407 */       return false;
/*      */     }
/* 1409 */     return (osName.startsWith(osNamePrefix)) && (osVersion.startsWith(osVersionPrefix));
/*      */   }
/*      */ 
/*      */   static boolean isOSNameMatch(String osName, String osNamePrefix)
/*      */   {
/* 1423 */     if (osName == null) {
/* 1424 */       return false;
/*      */     }
/* 1426 */     return osName.startsWith(osNamePrefix);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.SystemUtils
 * JD-Core Version:    0.6.2
 */