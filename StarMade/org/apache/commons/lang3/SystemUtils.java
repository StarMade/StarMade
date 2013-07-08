/*    1:     */package org.apache.commons.lang3;
/*    2:     */
/*    3:     */import java.io.File;
/*    4:     */import java.io.PrintStream;
/*    5:     */
/*   82:     */public class SystemUtils
/*   83:     */{
/*   84:     */  private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
/*   85:     */  private static final String USER_HOME_KEY = "user.home";
/*   86:     */  private static final String USER_DIR_KEY = "user.dir";
/*   87:     */  private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
/*   88:     */  private static final String JAVA_HOME_KEY = "java.home";
/*   89:  89 */  public static final String AWT_TOOLKIT = getSystemProperty("awt.toolkit");
/*   90:     */  
/*  111: 111 */  public static final String FILE_ENCODING = getSystemProperty("file.encoding");
/*  112:     */  
/*  129: 129 */  public static final String FILE_SEPARATOR = getSystemProperty("file.separator");
/*  130:     */  
/*  147: 147 */  public static final String JAVA_AWT_FONTS = getSystemProperty("java.awt.fonts");
/*  148:     */  
/*  165: 165 */  public static final String JAVA_AWT_GRAPHICSENV = getSystemProperty("java.awt.graphicsenv");
/*  166:     */  
/*  186: 186 */  public static final String JAVA_AWT_HEADLESS = getSystemProperty("java.awt.headless");
/*  187:     */  
/*  204: 204 */  public static final String JAVA_AWT_PRINTERJOB = getSystemProperty("java.awt.printerjob");
/*  205:     */  
/*  222: 222 */  public static final String JAVA_CLASS_PATH = getSystemProperty("java.class.path");
/*  223:     */  
/*  240: 240 */  public static final String JAVA_CLASS_VERSION = getSystemProperty("java.class.version");
/*  241:     */  
/*  259: 259 */  public static final String JAVA_COMPILER = getSystemProperty("java.compiler");
/*  260:     */  
/*  277: 277 */  public static final String JAVA_ENDORSED_DIRS = getSystemProperty("java.endorsed.dirs");
/*  278:     */  
/*  295: 295 */  public static final String JAVA_EXT_DIRS = getSystemProperty("java.ext.dirs");
/*  296:     */  
/*  313: 313 */  public static final String JAVA_HOME = getSystemProperty("java.home");
/*  314:     */  
/*  331: 331 */  public static final String JAVA_IO_TMPDIR = getSystemProperty("java.io.tmpdir");
/*  332:     */  
/*  349: 349 */  public static final String JAVA_LIBRARY_PATH = getSystemProperty("java.library.path");
/*  350:     */  
/*  368: 368 */  public static final String JAVA_RUNTIME_NAME = getSystemProperty("java.runtime.name");
/*  369:     */  
/*  387: 387 */  public static final String JAVA_RUNTIME_VERSION = getSystemProperty("java.runtime.version");
/*  388:     */  
/*  405: 405 */  public static final String JAVA_SPECIFICATION_NAME = getSystemProperty("java.specification.name");
/*  406:     */  
/*  423: 423 */  public static final String JAVA_SPECIFICATION_VENDOR = getSystemProperty("java.specification.vendor");
/*  424:     */  
/*  441: 441 */  public static final String JAVA_SPECIFICATION_VERSION = getSystemProperty("java.specification.version");
/*  442: 442 */  private static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(JAVA_SPECIFICATION_VERSION);
/*  443:     */  
/*  461: 461 */  public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = getSystemProperty("java.util.prefs.PreferencesFactory");
/*  462:     */  
/*  480: 480 */  public static final String JAVA_VENDOR = getSystemProperty("java.vendor");
/*  481:     */  
/*  498: 498 */  public static final String JAVA_VENDOR_URL = getSystemProperty("java.vendor.url");
/*  499:     */  
/*  516: 516 */  public static final String JAVA_VERSION = getSystemProperty("java.version");
/*  517:     */  
/*  535: 535 */  public static final String JAVA_VM_INFO = getSystemProperty("java.vm.info");
/*  536:     */  
/*  553: 553 */  public static final String JAVA_VM_NAME = getSystemProperty("java.vm.name");
/*  554:     */  
/*  571: 571 */  public static final String JAVA_VM_SPECIFICATION_NAME = getSystemProperty("java.vm.specification.name");
/*  572:     */  
/*  589: 589 */  public static final String JAVA_VM_SPECIFICATION_VENDOR = getSystemProperty("java.vm.specification.vendor");
/*  590:     */  
/*  607: 607 */  public static final String JAVA_VM_SPECIFICATION_VERSION = getSystemProperty("java.vm.specification.version");
/*  608:     */  
/*  625: 625 */  public static final String JAVA_VM_VENDOR = getSystemProperty("java.vm.vendor");
/*  626:     */  
/*  643: 643 */  public static final String JAVA_VM_VERSION = getSystemProperty("java.vm.version");
/*  644:     */  
/*  661: 661 */  public static final String LINE_SEPARATOR = getSystemProperty("line.separator");
/*  662:     */  
/*  679: 679 */  public static final String OS_ARCH = getSystemProperty("os.arch");
/*  680:     */  
/*  697: 697 */  public static final String OS_NAME = getSystemProperty("os.name");
/*  698:     */  
/*  715: 715 */  public static final String OS_VERSION = getSystemProperty("os.version");
/*  716:     */  
/*  733: 733 */  public static final String PATH_SEPARATOR = getSystemProperty("path.separator");
/*  734:     */  
/*  753: 753 */  public static final String USER_COUNTRY = getSystemProperty("user.country") == null ? getSystemProperty("user.region") : getSystemProperty("user.country");
/*  754:     */  
/*  772: 772 */  public static final String USER_DIR = getSystemProperty("user.dir");
/*  773:     */  
/*  790: 790 */  public static final String USER_HOME = getSystemProperty("user.home");
/*  791:     */  
/*  809: 809 */  public static final String USER_LANGUAGE = getSystemProperty("user.language");
/*  810:     */  
/*  827: 827 */  public static final String USER_NAME = getSystemProperty("user.name");
/*  828:     */  
/*  845: 845 */  public static final String USER_TIMEZONE = getSystemProperty("user.timezone");
/*  846:     */  
/*  860: 860 */  public static final boolean IS_JAVA_1_1 = getJavaVersionMatches("1.1");
/*  861:     */  
/*  870: 870 */  public static final boolean IS_JAVA_1_2 = getJavaVersionMatches("1.2");
/*  871:     */  
/*  880: 880 */  public static final boolean IS_JAVA_1_3 = getJavaVersionMatches("1.3");
/*  881:     */  
/*  890: 890 */  public static final boolean IS_JAVA_1_4 = getJavaVersionMatches("1.4");
/*  891:     */  
/*  900: 900 */  public static final boolean IS_JAVA_1_5 = getJavaVersionMatches("1.5");
/*  901:     */  
/*  910: 910 */  public static final boolean IS_JAVA_1_6 = getJavaVersionMatches("1.6");
/*  911:     */  
/*  922: 922 */  public static final boolean IS_JAVA_1_7 = getJavaVersionMatches("1.7");
/*  923:     */  
/*  942: 942 */  public static final boolean IS_OS_AIX = getOSMatchesName("AIX");
/*  943:     */  
/*  954: 954 */  public static final boolean IS_OS_HP_UX = getOSMatchesName("HP-UX");
/*  955:     */  
/*  966: 966 */  public static final boolean IS_OS_IRIX = getOSMatchesName("Irix");
/*  967:     */  
/*  978: 978 */  public static final boolean IS_OS_LINUX = (getOSMatchesName("Linux")) || (getOSMatchesName("LINUX"));
/*  979:     */  
/*  990: 990 */  public static final boolean IS_OS_MAC = getOSMatchesName("Mac");
/*  991:     */  
/* 1002:1002 */  public static final boolean IS_OS_MAC_OSX = getOSMatchesName("Mac OS X");
/* 1003:     */  
/* 1014:1014 */  public static final boolean IS_OS_FREE_BSD = getOSMatchesName("FreeBSD");
/* 1015:     */  
/* 1026:1026 */  public static final boolean IS_OS_OPEN_BSD = getOSMatchesName("OpenBSD");
/* 1027:     */  
/* 1038:1038 */  public static final boolean IS_OS_NET_BSD = getOSMatchesName("NetBSD");
/* 1039:     */  
/* 1050:1050 */  public static final boolean IS_OS_OS2 = getOSMatchesName("OS/2");
/* 1051:     */  
/* 1062:1062 */  public static final boolean IS_OS_SOLARIS = getOSMatchesName("Solaris");
/* 1063:     */  
/* 1074:1074 */  public static final boolean IS_OS_SUN_OS = getOSMatchesName("SunOS");
/* 1075:     */  
/* 1086:1086 */  public static final boolean IS_OS_UNIX = (IS_OS_AIX) || (IS_OS_HP_UX) || (IS_OS_IRIX) || (IS_OS_LINUX) || (IS_OS_MAC_OSX) || (IS_OS_SOLARIS) || (IS_OS_SUN_OS) || (IS_OS_FREE_BSD) || (IS_OS_OPEN_BSD) || (IS_OS_NET_BSD);
/* 1087:     */  
/* 1099:1099 */  public static final boolean IS_OS_WINDOWS = getOSMatchesName("Windows");
/* 1100:     */  
/* 1111:1111 */  public static final boolean IS_OS_WINDOWS_2000 = getOSMatches("Windows", "5.0");
/* 1112:     */  
/* 1123:1123 */  public static final boolean IS_OS_WINDOWS_2003 = getOSMatches("Windows", "5.2");
/* 1124:     */  
/* 1135:1135 */  public static final boolean IS_OS_WINDOWS_2008 = getOSMatches("Windows Server 2008", "6.1");
/* 1136:     */  
/* 1147:1147 */  public static final boolean IS_OS_WINDOWS_95 = getOSMatches("Windows 9", "4.0");
/* 1148:     */  
/* 1160:1160 */  public static final boolean IS_OS_WINDOWS_98 = getOSMatches("Windows 9", "4.1");
/* 1161:     */  
/* 1173:1173 */  public static final boolean IS_OS_WINDOWS_ME = getOSMatches("Windows", "4.9");
/* 1174:     */  
/* 1186:1186 */  public static final boolean IS_OS_WINDOWS_NT = getOSMatchesName("Windows NT");
/* 1187:     */  
/* 1199:1199 */  public static final boolean IS_OS_WINDOWS_XP = getOSMatches("Windows", "5.1");
/* 1200:     */  
/* 1212:1212 */  public static final boolean IS_OS_WINDOWS_VISTA = getOSMatches("Windows", "6.0");
/* 1213:     */  
/* 1224:1224 */  public static final boolean IS_OS_WINDOWS_7 = getOSMatches("Windows", "6.1");
/* 1225:     */  
/* 1236:     */  public static File getJavaHome()
/* 1237:     */  {
/* 1238:1238 */    return new File(System.getProperty("java.home"));
/* 1239:     */  }
/* 1240:     */  
/* 1251:     */  public static File getJavaIoTmpDir()
/* 1252:     */  {
/* 1253:1253 */    return new File(System.getProperty("java.io.tmpdir"));
/* 1254:     */  }
/* 1255:     */  
/* 1263:     */  private static boolean getJavaVersionMatches(String versionPrefix)
/* 1264:     */  {
/* 1265:1265 */    return isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, versionPrefix);
/* 1266:     */  }
/* 1267:     */  
/* 1274:     */  private static boolean getOSMatches(String osNamePrefix, String osVersionPrefix)
/* 1275:     */  {
/* 1276:1276 */    return isOSMatch(OS_NAME, OS_VERSION, osNamePrefix, osVersionPrefix);
/* 1277:     */  }
/* 1278:     */  
/* 1284:     */  private static boolean getOSMatchesName(String osNamePrefix)
/* 1285:     */  {
/* 1286:1286 */    return isOSNameMatch(OS_NAME, osNamePrefix);
/* 1287:     */  }
/* 1288:     */  
/* 1300:     */  private static String getSystemProperty(String property)
/* 1301:     */  {
/* 1302:     */    try
/* 1303:     */    {
/* 1304:1304 */      return System.getProperty(property);
/* 1305:     */    }
/* 1306:     */    catch (SecurityException ex) {
/* 1307:1307 */      System.err.println("Caught a SecurityException reading the system property '" + property + "'; the SystemUtils property value will default to null.");
/* 1308:     */    }
/* 1309:1309 */    return null;
/* 1310:     */  }
/* 1311:     */  
/* 1323:     */  public static File getUserDir()
/* 1324:     */  {
/* 1325:1325 */    return new File(System.getProperty("user.dir"));
/* 1326:     */  }
/* 1327:     */  
/* 1338:     */  public static File getUserHome()
/* 1339:     */  {
/* 1340:1340 */    return new File(System.getProperty("user.home"));
/* 1341:     */  }
/* 1342:     */  
/* 1350:     */  public static boolean isJavaAwtHeadless()
/* 1351:     */  {
/* 1352:1352 */    return JAVA_AWT_HEADLESS != null ? JAVA_AWT_HEADLESS.equals(Boolean.TRUE.toString()) : false;
/* 1353:     */  }
/* 1354:     */  
/* 1369:     */  public static boolean isJavaVersionAtLeast(JavaVersion requiredVersion)
/* 1370:     */  {
/* 1371:1371 */    return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(requiredVersion);
/* 1372:     */  }
/* 1373:     */  
/* 1385:     */  static boolean isJavaVersionMatch(String version, String versionPrefix)
/* 1386:     */  {
/* 1387:1387 */    if (version == null) {
/* 1388:1388 */      return false;
/* 1389:     */    }
/* 1390:1390 */    return version.startsWith(versionPrefix);
/* 1391:     */  }
/* 1392:     */  
/* 1404:     */  static boolean isOSMatch(String osName, String osVersion, String osNamePrefix, String osVersionPrefix)
/* 1405:     */  {
/* 1406:1406 */    if ((osName == null) || (osVersion == null)) {
/* 1407:1407 */      return false;
/* 1408:     */    }
/* 1409:1409 */    return (osName.startsWith(osNamePrefix)) && (osVersion.startsWith(osVersionPrefix));
/* 1410:     */  }
/* 1411:     */  
/* 1421:     */  static boolean isOSNameMatch(String osName, String osNamePrefix)
/* 1422:     */  {
/* 1423:1423 */    if (osName == null) {
/* 1424:1424 */      return false;
/* 1425:     */    }
/* 1426:1426 */    return osName.startsWith(osNamePrefix);
/* 1427:     */  }
/* 1428:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.SystemUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */