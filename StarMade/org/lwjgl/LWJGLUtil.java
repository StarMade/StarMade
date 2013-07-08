/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.OutputStream;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.lang.reflect.Field;
/*   8:    */import java.lang.reflect.Method;
/*   9:    */import java.nio.ByteBuffer;
/*  10:    */import java.security.AccessController;
/*  11:    */import java.security.PrivilegedAction;
/*  12:    */import java.security.PrivilegedActionException;
/*  13:    */import java.security.PrivilegedExceptionAction;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.Arrays;
/*  16:    */import java.util.HashMap;
/*  17:    */import java.util.List;
/*  18:    */import java.util.Map;
/*  19:    */import java.util.StringTokenizer;
/*  20:    */
/* 249:    */public class LWJGLUtil
/* 250:    */{
/* 251:    */  public static final int PLATFORM_LINUX = 1;
/* 252:    */  public static final int PLATFORM_MACOSX = 2;
/* 253:    */  public static final int PLATFORM_WINDOWS = 3;
/* 254:    */  public static final String PLATFORM_LINUX_NAME = "linux";
/* 255:    */  public static final String PLATFORM_MACOSX_NAME = "macosx";
/* 256:    */  public static final String PLATFORM_WINDOWS_NAME = "windows";
/* 257:    */  private static final String LWJGL_ICON_DATA_16x16 = "";
/* 258:    */  private static final String LWJGL_ICON_DATA_32x32 = "";
/* 259:259 */  public static final ByteBuffer LWJGLIcon16x16 = loadIcon("");
/* 260:    */  
/* 262:262 */  public static final ByteBuffer LWJGLIcon32x32 = loadIcon("");
/* 263:    */  
/* 265:265 */  public static final boolean DEBUG = getPrivilegedBoolean("org.lwjgl.util.Debug");
/* 266:    */  
/* 267:267 */  public static final boolean CHECKS = !getPrivilegedBoolean("org.lwjgl.util.NoChecks");
/* 268:    */  private static final int PLATFORM;
/* 269:    */  
/* 270:    */  static
/* 271:    */  {
/* 272:272 */    String osName = getPrivilegedProperty("os.name");
/* 273:273 */    if (osName.startsWith("Windows")) {
/* 274:274 */      PLATFORM = 3;
/* 275:275 */    } else if ((osName.startsWith("Linux")) || (osName.startsWith("FreeBSD")) || (osName.startsWith("SunOS")) || (osName.startsWith("Unix"))) {
/* 276:276 */      PLATFORM = 1;
/* 277:277 */    } else if ((osName.startsWith("Mac OS X")) || (osName.startsWith("Darwin"))) {
/* 278:278 */      PLATFORM = 2;
/* 279:    */    } else
/* 280:280 */      throw new LinkageError("Unknown platform: " + osName);
/* 281:    */  }
/* 282:    */  
/* 283:    */  private static ByteBuffer loadIcon(String data) {
/* 284:284 */    int len = data.length();
/* 285:285 */    ByteBuffer bb = BufferUtils.createByteBuffer(len);
/* 286:286 */    for (int i = 0; i < len; i++) {
/* 287:287 */      bb.put(i, (byte)data.charAt(i));
/* 288:    */    }
/* 289:289 */    return bb.asReadOnlyBuffer();
/* 290:    */  }
/* 291:    */  
/* 297:    */  public static int getPlatform()
/* 298:    */  {
/* 299:299 */    return PLATFORM;
/* 300:    */  }
/* 301:    */  
/* 308:    */  public static String getPlatformName()
/* 309:    */  {
/* 310:310 */    switch () {
/* 311:    */    case 1: 
/* 312:312 */      return "linux";
/* 313:    */    case 2: 
/* 314:314 */      return "macosx";
/* 315:    */    case 3: 
/* 316:316 */      return "windows";
/* 317:    */    }
/* 318:318 */    return "unknown";
/* 319:    */  }
/* 320:    */  
/* 329:    */  public static String[] getLibraryPaths(String libname, String platform_lib_name, ClassLoader classloader)
/* 330:    */  {
/* 331:331 */    return getLibraryPaths(libname, new String[] { platform_lib_name }, classloader);
/* 332:    */  }
/* 333:    */  
/* 342:    */  public static String[] getLibraryPaths(String libname, String[] platform_lib_names, ClassLoader classloader)
/* 343:    */  {
/* 344:344 */    List<String> possible_paths = new ArrayList();
/* 345:    */    
/* 346:346 */    String classloader_path = getPathFromClassLoader(libname, classloader);
/* 347:347 */    if (classloader_path != null) {
/* 348:348 */      log("getPathFromClassLoader: Path found: " + classloader_path);
/* 349:349 */      possible_paths.add(classloader_path);
/* 350:    */    }
/* 351:    */    
/* 352:352 */    for (String platform_lib_name : platform_lib_names) {
/* 353:353 */      String lwjgl_classloader_path = getPathFromClassLoader("lwjgl", classloader);
/* 354:354 */      if (lwjgl_classloader_path != null) {
/* 355:355 */        log("getPathFromClassLoader: Path found: " + lwjgl_classloader_path);
/* 356:356 */        possible_paths.add(lwjgl_classloader_path.substring(0, lwjgl_classloader_path.lastIndexOf(File.separator)) + File.separator + platform_lib_name);
/* 357:    */      }
/* 358:    */      
/* 361:361 */      String alternative_path = getPrivilegedProperty("org.lwjgl.librarypath");
/* 362:362 */      if (alternative_path != null) {
/* 363:363 */        possible_paths.add(alternative_path + File.separator + platform_lib_name);
/* 364:    */      }
/* 365:    */      
/* 367:367 */      String java_library_path = getPrivilegedProperty("java.library.path");
/* 368:    */      
/* 369:369 */      StringTokenizer st = new StringTokenizer(java_library_path, File.pathSeparator);
/* 370:370 */      while (st.hasMoreTokens()) {
/* 371:371 */        String path = st.nextToken();
/* 372:372 */        possible_paths.add(path + File.separator + platform_lib_name);
/* 373:    */      }
/* 374:    */      
/* 376:376 */      String current_dir = getPrivilegedProperty("user.dir");
/* 377:377 */      possible_paths.add(current_dir + File.separator + platform_lib_name);
/* 378:    */      
/* 380:380 */      possible_paths.add(platform_lib_name);
/* 381:    */    }
/* 382:    */    
/* 384:384 */    return (String[])possible_paths.toArray(new String[possible_paths.size()]);
/* 385:    */  }
/* 386:    */  
/* 387:    */  static void execPrivileged(String[] cmd_array) throws Exception {
/* 388:    */    try {
/* 389:389 */      Process process = (Process)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 390:    */        public Process run() throws Exception {
/* 391:391 */          return Runtime.getRuntime().exec(this.val$cmd_array);
/* 392:    */        }
/* 393:    */        
/* 394:394 */      });
/* 395:395 */      process.getInputStream().close();
/* 396:396 */      process.getOutputStream().close();
/* 397:397 */      process.getErrorStream().close();
/* 398:    */    } catch (PrivilegedActionException e) {
/* 399:399 */      throw ((Exception)e.getCause());
/* 400:    */    }
/* 401:    */  }
/* 402:    */  
/* 403:    */  private static String getPrivilegedProperty(String property_name) {
/* 404:404 */    (String)AccessController.doPrivileged(new PrivilegedAction() {
/* 405:    */      public String run() {
/* 406:406 */        return System.getProperty(this.val$property_name);
/* 407:    */      }
/* 408:    */    });
/* 409:    */  }
/* 410:    */  
/* 420:    */  private static String getPathFromClassLoader(final String libname, final ClassLoader classloader)
/* 421:    */  {
/* 422:    */    try
/* 423:    */    {
/* 424:424 */      log("getPathFromClassLoader: searching for: " + libname);
/* 425:425 */      Class<?> c = classloader.getClass();
/* 426:426 */      while (c != null) {
/* 427:427 */        Class<?> clazz = c;
/* 428:    */        try {
/* 429:429 */          (String)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 430:    */            public String run() throws Exception {
/* 431:431 */              Method findLibrary = this.val$clazz.getDeclaredMethod("findLibrary", new Class[] { String.class });
/* 432:432 */              findLibrary.setAccessible(true);
/* 433:433 */              String path = (String)findLibrary.invoke(classloader, new Object[] { libname });
/* 434:434 */              return path;
/* 435:    */            }
/* 436:    */          });
/* 437:    */        } catch (PrivilegedActionException e) {
/* 438:438 */          log("Failed to locate findLibrary method: " + e.getCause());
/* 439:439 */          c = c.getSuperclass();
/* 440:    */        }
/* 441:    */      }
/* 442:    */    } catch (Exception e) {
/* 443:443 */      log("Failure locating " + e + " using classloader:" + e);
/* 444:    */    }
/* 445:445 */    return null;
/* 446:    */  }
/* 447:    */  
/* 450:    */  public static boolean getPrivilegedBoolean(String property_name)
/* 451:    */  {
/* 452:452 */    ((Boolean)AccessController.doPrivileged(new PrivilegedAction()) {
/* 453:    */      public Boolean run() {
/* 454:454 */        return Boolean.valueOf(Boolean.getBoolean(this.val$property_name));
/* 455:    */      }
/* 456:    */    }()).booleanValue();
/* 457:    */  }
/* 458:    */  
/* 465:    */  public static Integer getPrivilegedInteger(String property_name)
/* 466:    */  {
/* 467:467 */    (Integer)AccessController.doPrivileged(new PrivilegedAction() {
/* 468:    */      public Integer run() {
/* 469:469 */        return Integer.getInteger(this.val$property_name);
/* 470:    */      }
/* 471:    */    });
/* 472:    */  }
/* 473:    */  
/* 481:    */  public static Integer getPrivilegedInteger(String property_name, final int default_val)
/* 482:    */  {
/* 483:483 */    (Integer)AccessController.doPrivileged(new PrivilegedAction() {
/* 484:    */      public Integer run() {
/* 485:485 */        return Integer.getInteger(this.val$property_name, default_val);
/* 486:    */      }
/* 487:    */    });
/* 488:    */  }
/* 489:    */  
/* 494:    */  public static void log(CharSequence msg)
/* 495:    */  {
/* 496:496 */    if (DEBUG) {
/* 497:497 */      System.err.println("[LWJGL] " + msg);
/* 498:    */    }
/* 499:    */  }
/* 500:    */  
/* 505:    */  public static boolean isMacOSXEqualsOrBetterThan(int major_required, int minor_required)
/* 506:    */  {
/* 507:507 */    String os_version = getPrivilegedProperty("os.version");
/* 508:508 */    StringTokenizer version_tokenizer = new StringTokenizer(os_version, ".");
/* 509:    */    int major;
/* 510:    */    int minor;
/* 511:    */    try {
/* 512:512 */      String major_str = version_tokenizer.nextToken();
/* 513:513 */      String minor_str = version_tokenizer.nextToken();
/* 514:514 */      major = Integer.parseInt(major_str);
/* 515:515 */      minor = Integer.parseInt(minor_str);
/* 516:    */    } catch (Exception e) {
/* 517:517 */      log("Exception occurred while trying to determine OS version: " + e);
/* 518:    */      
/* 519:519 */      return false;
/* 520:    */    }
/* 521:521 */    return (major > major_required) || ((major == major_required) && (minor >= minor_required));
/* 522:    */  }
/* 523:    */  
/* 537:    */  public static Map<Integer, String> getClassTokens(TokenFilter filter, Map<Integer, String> target, Class... tokenClasses)
/* 538:    */  {
/* 539:539 */    return getClassTokens(filter, target, Arrays.asList(tokenClasses));
/* 540:    */  }
/* 541:    */  
/* 554:    */  public static Map<Integer, String> getClassTokens(TokenFilter filter, Map<Integer, String> target, Iterable<Class> tokenClasses)
/* 555:    */  {
/* 556:556 */    if (target == null) {
/* 557:557 */      target = new HashMap();
/* 558:    */    }
/* 559:559 */    int TOKEN_MODIFIERS = 25;
/* 560:    */    
/* 561:561 */    for (Class tokenClass : tokenClasses) {
/* 562:562 */      for (Field field : tokenClass.getDeclaredFields())
/* 563:    */      {
/* 564:564 */        if (((field.getModifiers() & 0x19) == 25) && (field.getType() == Integer.TYPE)) {
/* 565:    */          try {
/* 566:566 */            int value = field.getInt(null);
/* 567:567 */            if ((filter == null) || (filter.accept(field, value)))
/* 568:    */            {
/* 570:570 */              if (target.containsKey(Integer.valueOf(value))) {
/* 571:571 */                target.put(Integer.valueOf(value), toHexString(value));
/* 572:    */              } else {
/* 573:573 */                target.put(Integer.valueOf(value), field.getName());
/* 574:    */              }
/* 575:    */            }
/* 576:    */          }
/* 577:    */          catch (IllegalAccessException e) {}
/* 578:    */        }
/* 579:    */      }
/* 580:    */    }
/* 581:581 */    return target;
/* 582:    */  }
/* 583:    */  
/* 592:    */  public static String toHexString(int value)
/* 593:    */  {
/* 594:594 */    return "0x" + Integer.toHexString(value).toUpperCase();
/* 595:    */  }
/* 596:    */  
/* 597:    */  public static abstract interface TokenFilter
/* 598:    */  {
/* 599:    */    public abstract boolean accept(Field paramField, int paramInt);
/* 600:    */  }
/* 601:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.LWJGLUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */