/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.io.PrintWriter;
/*   7:    */import java.io.Serializable;
/*   8:    */import java.io.StringWriter;
/*   9:    */import java.lang.reflect.InvocationTargetException;
/*  10:    */import java.lang.reflect.Method;
/*  11:    */import java.security.AccessController;
/*  12:    */import java.security.PrivilegedAction;
/*  13:    */import java.text.DateFormat;
/*  14:    */import java.text.SimpleDateFormat;
/*  15:    */import java.util.Date;
/*  16:    */import java.util.Properties;
/*  17:    */import org.apache.commons.logging.Log;
/*  18:    */import org.apache.commons.logging.LogConfigurationException;
/*  19:    */
/*  82:    */public class SimpleLog
/*  83:    */  implements Log, Serializable
/*  84:    */{
/*  85:    */  protected static final String systemPrefix = "org.apache.commons.logging.simplelog.";
/*  86: 86 */  protected static final Properties simpleLogProps = new Properties();
/*  87:    */  
/*  90:    */  protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
/*  91:    */  
/*  93: 93 */  protected static boolean showLogName = false;
/*  94:    */  
/*  98: 98 */  protected static boolean showShortName = true;
/*  99:    */  
/* 100:100 */  protected static boolean showDateTime = false;
/* 101:    */  
/* 102:102 */  protected static String dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
/* 103:    */  
/* 112:112 */  protected static DateFormat dateFormatter = null;
/* 113:    */  
/* 115:    */  public static final int LOG_LEVEL_TRACE = 1;
/* 116:    */  
/* 118:    */  public static final int LOG_LEVEL_DEBUG = 2;
/* 119:    */  
/* 121:    */  public static final int LOG_LEVEL_INFO = 3;
/* 122:    */  
/* 124:    */  public static final int LOG_LEVEL_WARN = 4;
/* 125:    */  
/* 127:    */  public static final int LOG_LEVEL_ERROR = 5;
/* 128:    */  
/* 130:    */  public static final int LOG_LEVEL_FATAL = 6;
/* 131:    */  
/* 132:    */  public static final int LOG_LEVEL_ALL = 0;
/* 133:    */  
/* 134:    */  public static final int LOG_LEVEL_OFF = 7;
/* 135:    */  
/* 137:    */  private static String getStringProperty(String name)
/* 138:    */  {
/* 139:139 */    String prop = null;
/* 140:    */    try {
/* 141:141 */      prop = System.getProperty(name);
/* 142:    */    }
/* 143:    */    catch (SecurityException e) {}
/* 144:    */    
/* 145:145 */    return prop == null ? simpleLogProps.getProperty(name) : prop;
/* 146:    */  }
/* 147:    */  
/* 148:    */  private static String getStringProperty(String name, String dephault) {
/* 149:149 */    String prop = getStringProperty(name);
/* 150:150 */    return prop == null ? dephault : prop;
/* 151:    */  }
/* 152:    */  
/* 153:    */  private static boolean getBooleanProperty(String name, boolean dephault) {
/* 154:154 */    String prop = getStringProperty(name);
/* 155:155 */    return prop == null ? dephault : "true".equalsIgnoreCase(prop);
/* 156:    */  }
/* 157:    */  
/* 161:    */  static
/* 162:    */  {
/* 163:163 */    InputStream in = getResourceAsStream("simplelog.properties");
/* 164:164 */    if (null != in) {
/* 165:    */      try {
/* 166:166 */        simpleLogProps.load(in);
/* 167:167 */        in.close();
/* 168:    */      }
/* 169:    */      catch (IOException e) {}
/* 170:    */    }
/* 171:    */    
/* 173:173 */    showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
/* 174:174 */    showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", showShortName);
/* 175:175 */    showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", showDateTime);
/* 176:    */    
/* 177:177 */    if (showDateTime) {
/* 178:178 */      dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
/* 179:    */      try
/* 180:    */      {
/* 181:181 */        dateFormatter = new SimpleDateFormat(dateTimeFormat);
/* 182:    */      }
/* 183:    */      catch (IllegalArgumentException e) {
/* 184:184 */        dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
/* 185:185 */        dateFormatter = new SimpleDateFormat(dateTimeFormat);
/* 186:    */      }
/* 187:    */    }
/* 188:    */  }
/* 189:    */  
/* 193:193 */  protected String logName = null;
/* 194:    */  
/* 195:    */  protected int currentLogLevel;
/* 196:    */  
/* 197:197 */  private String shortLogName = null;
/* 198:    */  
/* 207:    */  public SimpleLog(String name)
/* 208:    */  {
/* 209:209 */    this.logName = name;
/* 210:    */    
/* 214:214 */    setLevel(3);
/* 215:    */    
/* 217:217 */    String lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + this.logName);
/* 218:218 */    int i = String.valueOf(name).lastIndexOf(".");
/* 219:219 */    while ((null == lvl) && (i > -1)) {
/* 220:220 */      name = name.substring(0, i);
/* 221:221 */      lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + name);
/* 222:222 */      i = String.valueOf(name).lastIndexOf(".");
/* 223:    */    }
/* 224:    */    
/* 225:225 */    if (null == lvl) {
/* 226:226 */      lvl = getStringProperty("org.apache.commons.logging.simplelog.defaultlog");
/* 227:    */    }
/* 228:    */    
/* 229:229 */    if ("all".equalsIgnoreCase(lvl)) {
/* 230:230 */      setLevel(0);
/* 231:231 */    } else if ("trace".equalsIgnoreCase(lvl)) {
/* 232:232 */      setLevel(1);
/* 233:233 */    } else if ("debug".equalsIgnoreCase(lvl)) {
/* 234:234 */      setLevel(2);
/* 235:235 */    } else if ("info".equalsIgnoreCase(lvl)) {
/* 236:236 */      setLevel(3);
/* 237:237 */    } else if ("warn".equalsIgnoreCase(lvl)) {
/* 238:238 */      setLevel(4);
/* 239:239 */    } else if ("error".equalsIgnoreCase(lvl)) {
/* 240:240 */      setLevel(5);
/* 241:241 */    } else if ("fatal".equalsIgnoreCase(lvl)) {
/* 242:242 */      setLevel(6);
/* 243:243 */    } else if ("off".equalsIgnoreCase(lvl)) {
/* 244:244 */      setLevel(7);
/* 245:    */    }
/* 246:    */  }
/* 247:    */  
/* 257:    */  public void setLevel(int currentLogLevel)
/* 258:    */  {
/* 259:259 */    this.currentLogLevel = currentLogLevel;
/* 260:    */  }
/* 261:    */  
/* 267:    */  public int getLevel()
/* 268:    */  {
/* 269:269 */    return this.currentLogLevel;
/* 270:    */  }
/* 271:    */  
/* 285:    */  protected void log(int type, Object message, Throwable t)
/* 286:    */  {
/* 287:287 */    StringBuffer buf = new StringBuffer();
/* 288:    */    
/* 290:290 */    if (showDateTime) {
/* 291:291 */      Date now = new Date();
/* 292:    */      String dateText;
/* 293:293 */      synchronized (dateFormatter) {
/* 294:294 */        dateText = dateFormatter.format(now); }
/* 295:    */      String dateText;
/* 296:296 */      buf.append(dateText);
/* 297:297 */      buf.append(" ");
/* 298:    */    }
/* 299:    */    
/* 301:301 */    switch (type) {
/* 302:302 */    case 1:  buf.append("[TRACE] ");break;
/* 303:303 */    case 2:  buf.append("[DEBUG] ");break;
/* 304:304 */    case 3:  buf.append("[INFO] ");break;
/* 305:305 */    case 4:  buf.append("[WARN] ");break;
/* 306:306 */    case 5:  buf.append("[ERROR] ");break;
/* 307:307 */    case 6:  buf.append("[FATAL] ");
/* 308:    */    }
/* 309:    */    
/* 310:    */    
/* 311:311 */    if (showShortName) {
/* 312:312 */      if (this.shortLogName == null)
/* 313:    */      {
/* 314:314 */        this.shortLogName = this.logName.substring(this.logName.lastIndexOf(".") + 1);
/* 315:315 */        this.shortLogName = this.shortLogName.substring(this.shortLogName.lastIndexOf("/") + 1);
/* 316:    */      }
/* 317:    */      
/* 318:318 */      buf.append(String.valueOf(this.shortLogName)).append(" - ");
/* 319:319 */    } else if (showLogName) {
/* 320:320 */      buf.append(String.valueOf(this.logName)).append(" - ");
/* 321:    */    }
/* 322:    */    
/* 324:324 */    buf.append(String.valueOf(message));
/* 325:    */    
/* 327:327 */    if (t != null) {
/* 328:328 */      buf.append(" <");
/* 329:329 */      buf.append(t.toString());
/* 330:330 */      buf.append(">");
/* 331:    */      
/* 332:332 */      StringWriter sw = new StringWriter(1024);
/* 333:333 */      PrintWriter pw = new PrintWriter(sw);
/* 334:334 */      t.printStackTrace(pw);
/* 335:335 */      pw.close();
/* 336:336 */      buf.append(sw.toString());
/* 337:    */    }
/* 338:    */    
/* 340:340 */    write(buf);
/* 341:    */  }
/* 342:    */  
/* 353:    */  protected void write(StringBuffer buffer)
/* 354:    */  {
/* 355:355 */    System.err.println(buffer.toString());
/* 356:    */  }
/* 357:    */  
/* 366:    */  protected boolean isLevelEnabled(int logLevel)
/* 367:    */  {
/* 368:368 */    return logLevel >= this.currentLogLevel;
/* 369:    */  }
/* 370:    */  
/* 382:    */  public final void debug(Object message)
/* 383:    */  {
/* 384:384 */    if (isLevelEnabled(2)) {
/* 385:385 */      log(2, message, null);
/* 386:    */    }
/* 387:    */  }
/* 388:    */  
/* 398:    */  public final void debug(Object message, Throwable t)
/* 399:    */  {
/* 400:400 */    if (isLevelEnabled(2)) {
/* 401:401 */      log(2, message, t);
/* 402:    */    }
/* 403:    */  }
/* 404:    */  
/* 413:    */  public final void trace(Object message)
/* 414:    */  {
/* 415:415 */    if (isLevelEnabled(1)) {
/* 416:416 */      log(1, message, null);
/* 417:    */    }
/* 418:    */  }
/* 419:    */  
/* 429:    */  public final void trace(Object message, Throwable t)
/* 430:    */  {
/* 431:431 */    if (isLevelEnabled(1)) {
/* 432:432 */      log(1, message, t);
/* 433:    */    }
/* 434:    */  }
/* 435:    */  
/* 444:    */  public final void info(Object message)
/* 445:    */  {
/* 446:446 */    if (isLevelEnabled(3)) {
/* 447:447 */      log(3, message, null);
/* 448:    */    }
/* 449:    */  }
/* 450:    */  
/* 460:    */  public final void info(Object message, Throwable t)
/* 461:    */  {
/* 462:462 */    if (isLevelEnabled(3)) {
/* 463:463 */      log(3, message, t);
/* 464:    */    }
/* 465:    */  }
/* 466:    */  
/* 475:    */  public final void warn(Object message)
/* 476:    */  {
/* 477:477 */    if (isLevelEnabled(4)) {
/* 478:478 */      log(4, message, null);
/* 479:    */    }
/* 480:    */  }
/* 481:    */  
/* 491:    */  public final void warn(Object message, Throwable t)
/* 492:    */  {
/* 493:493 */    if (isLevelEnabled(4)) {
/* 494:494 */      log(4, message, t);
/* 495:    */    }
/* 496:    */  }
/* 497:    */  
/* 506:    */  public final void error(Object message)
/* 507:    */  {
/* 508:508 */    if (isLevelEnabled(5)) {
/* 509:509 */      log(5, message, null);
/* 510:    */    }
/* 511:    */  }
/* 512:    */  
/* 522:    */  public final void error(Object message, Throwable t)
/* 523:    */  {
/* 524:524 */    if (isLevelEnabled(5)) {
/* 525:525 */      log(5, message, t);
/* 526:    */    }
/* 527:    */  }
/* 528:    */  
/* 537:    */  public final void fatal(Object message)
/* 538:    */  {
/* 539:539 */    if (isLevelEnabled(6)) {
/* 540:540 */      log(6, message, null);
/* 541:    */    }
/* 542:    */  }
/* 543:    */  
/* 553:    */  public final void fatal(Object message, Throwable t)
/* 554:    */  {
/* 555:555 */    if (isLevelEnabled(6)) {
/* 556:556 */      log(6, message, t);
/* 557:    */    }
/* 558:    */  }
/* 559:    */  
/* 568:    */  public final boolean isDebugEnabled()
/* 569:    */  {
/* 570:570 */    return isLevelEnabled(2);
/* 571:    */  }
/* 572:    */  
/* 581:    */  public final boolean isErrorEnabled()
/* 582:    */  {
/* 583:583 */    return isLevelEnabled(5);
/* 584:    */  }
/* 585:    */  
/* 594:    */  public final boolean isFatalEnabled()
/* 595:    */  {
/* 596:596 */    return isLevelEnabled(6);
/* 597:    */  }
/* 598:    */  
/* 607:    */  public final boolean isInfoEnabled()
/* 608:    */  {
/* 609:609 */    return isLevelEnabled(3);
/* 610:    */  }
/* 611:    */  
/* 620:    */  public final boolean isTraceEnabled()
/* 621:    */  {
/* 622:622 */    return isLevelEnabled(1);
/* 623:    */  }
/* 624:    */  
/* 633:    */  public final boolean isWarnEnabled()
/* 634:    */  {
/* 635:635 */    return isLevelEnabled(4);
/* 636:    */  }
/* 637:    */  
/* 649:    */  private static ClassLoader getContextClassLoader()
/* 650:    */  {
/* 651:651 */    ClassLoader classLoader = null;
/* 652:    */    
/* 653:653 */    if (classLoader == null) {
/* 654:    */      try
/* 655:    */      {
/* 656:656 */        Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
/* 657:    */        
/* 659:    */        try
/* 660:    */        {
/* 661:661 */          classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Class[])null);
/* 671:    */        }
/* 672:    */        catch (IllegalAccessException e) {}catch (InvocationTargetException e)
/* 673:    */        {
/* 682:682 */          if (!(e.getTargetException() instanceof SecurityException))
/* 683:    */          {
/* 687:687 */            throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException());
/* 688:    */          }
/* 689:    */        }
/* 690:    */      }
/* 691:    */      catch (NoSuchMethodException e) {}
/* 692:    */    }
/* 693:    */    
/* 697:697 */    if (classLoader == null) {
/* 698:698 */      classLoader = SimpleLog.class.getClassLoader();
/* 699:    */    }
/* 700:    */    
/* 702:702 */    return classLoader;
/* 703:    */  }
/* 704:    */  
/* 705:    */  private static InputStream getResourceAsStream(String name)
/* 706:    */  {
/* 707:707 */    (InputStream)AccessController.doPrivileged(new PrivilegedAction() {
/* 708:    */      private final String val$name;
/* 709:    */      
/* 710:710 */      public Object run() { ClassLoader threadCL = SimpleLog.access$000();
/* 711:    */        
/* 712:712 */        if (threadCL != null) {
/* 713:713 */          return threadCL.getResourceAsStream(this.val$name);
/* 714:    */        }
/* 715:715 */        return ClassLoader.getSystemResourceAsStream(this.val$name);
/* 716:    */      }
/* 717:    */    });
/* 718:    */  }
/* 719:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.SimpleLog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */