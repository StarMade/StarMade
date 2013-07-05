/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogConfigurationException;
/*     */ 
/*     */ public class SimpleLog
/*     */   implements Log, Serializable
/*     */ {
/*     */   protected static final String systemPrefix = "org.apache.commons.logging.simplelog.";
/*  86 */   protected static final Properties simpleLogProps = new Properties();
/*     */   protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
/*  93 */   protected static boolean showLogName = false;
/*     */ 
/*  98 */   protected static boolean showShortName = true;
/*     */ 
/* 100 */   protected static boolean showDateTime = false;
/*     */ 
/* 102 */   protected static String dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
/*     */ 
/* 112 */   protected static DateFormat dateFormatter = null;
/*     */   public static final int LOG_LEVEL_TRACE = 1;
/*     */   public static final int LOG_LEVEL_DEBUG = 2;
/*     */   public static final int LOG_LEVEL_INFO = 3;
/*     */   public static final int LOG_LEVEL_WARN = 4;
/*     */   public static final int LOG_LEVEL_ERROR = 5;
/*     */   public static final int LOG_LEVEL_FATAL = 6;
/*     */   public static final int LOG_LEVEL_ALL = 0;
/*     */   public static final int LOG_LEVEL_OFF = 7;
/* 193 */   protected String logName = null;
/*     */   protected int currentLogLevel;
/* 197 */   private String shortLogName = null;
/*     */ 
/*     */   private static String getStringProperty(String name)
/*     */   {
/* 139 */     String prop = null;
/*     */     try {
/* 141 */       prop = System.getProperty(name);
/*     */     }
/*     */     catch (SecurityException e) {
/*     */     }
/* 145 */     return prop == null ? simpleLogProps.getProperty(name) : prop;
/*     */   }
/*     */ 
/*     */   private static String getStringProperty(String name, String dephault) {
/* 149 */     String prop = getStringProperty(name);
/* 150 */     return prop == null ? dephault : prop;
/*     */   }
/*     */ 
/*     */   private static boolean getBooleanProperty(String name, boolean dephault) {
/* 154 */     String prop = getStringProperty(name);
/* 155 */     return prop == null ? dephault : "true".equalsIgnoreCase(prop);
/*     */   }
/*     */ 
/*     */   public SimpleLog(String name)
/*     */   {
/* 209 */     this.logName = name;
/*     */ 
/* 214 */     setLevel(3);
/*     */ 
/* 217 */     String lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + this.logName);
/* 218 */     int i = String.valueOf(name).lastIndexOf(".");
/* 219 */     while ((null == lvl) && (i > -1)) {
/* 220 */       name = name.substring(0, i);
/* 221 */       lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + name);
/* 222 */       i = String.valueOf(name).lastIndexOf(".");
/*     */     }
/*     */ 
/* 225 */     if (null == lvl) {
/* 226 */       lvl = getStringProperty("org.apache.commons.logging.simplelog.defaultlog");
/*     */     }
/*     */ 
/* 229 */     if ("all".equalsIgnoreCase(lvl))
/* 230 */       setLevel(0);
/* 231 */     else if ("trace".equalsIgnoreCase(lvl))
/* 232 */       setLevel(1);
/* 233 */     else if ("debug".equalsIgnoreCase(lvl))
/* 234 */       setLevel(2);
/* 235 */     else if ("info".equalsIgnoreCase(lvl))
/* 236 */       setLevel(3);
/* 237 */     else if ("warn".equalsIgnoreCase(lvl))
/* 238 */       setLevel(4);
/* 239 */     else if ("error".equalsIgnoreCase(lvl))
/* 240 */       setLevel(5);
/* 241 */     else if ("fatal".equalsIgnoreCase(lvl))
/* 242 */       setLevel(6);
/* 243 */     else if ("off".equalsIgnoreCase(lvl))
/* 244 */       setLevel(7);
/*     */   }
/*     */ 
/*     */   public void setLevel(int currentLogLevel)
/*     */   {
/* 259 */     this.currentLogLevel = currentLogLevel;
/*     */   }
/*     */ 
/*     */   public int getLevel()
/*     */   {
/* 269 */     return this.currentLogLevel;
/*     */   }
/*     */ 
/*     */   protected void log(int type, Object message, Throwable t)
/*     */   {
/* 287 */     StringBuffer buf = new StringBuffer();
/*     */ 
/* 290 */     if (showDateTime) {
/* 291 */       Date now = new Date();
/*     */       String dateText;
/* 293 */       synchronized (dateFormatter) {
/* 294 */         dateText = dateFormatter.format(now);
/*     */       }
/*     */       String dateText;
/* 296 */       buf.append(dateText);
/* 297 */       buf.append(" ");
/*     */     }
/*     */ 
/* 301 */     switch (type) { case 1:
/* 302 */       buf.append("[TRACE] "); break;
/*     */     case 2:
/* 303 */       buf.append("[DEBUG] "); break;
/*     */     case 3:
/* 304 */       buf.append("[INFO] "); break;
/*     */     case 4:
/* 305 */       buf.append("[WARN] "); break;
/*     */     case 5:
/* 306 */       buf.append("[ERROR] "); break;
/*     */     case 6:
/* 307 */       buf.append("[FATAL] ");
/*     */     }
/*     */ 
/* 311 */     if (showShortName) {
/* 312 */       if (this.shortLogName == null)
/*     */       {
/* 314 */         this.shortLogName = this.logName.substring(this.logName.lastIndexOf(".") + 1);
/* 315 */         this.shortLogName = this.shortLogName.substring(this.shortLogName.lastIndexOf("/") + 1);
/*     */       }
/*     */ 
/* 318 */       buf.append(String.valueOf(this.shortLogName)).append(" - ");
/* 319 */     } else if (showLogName) {
/* 320 */       buf.append(String.valueOf(this.logName)).append(" - ");
/*     */     }
/*     */ 
/* 324 */     buf.append(String.valueOf(message));
/*     */ 
/* 327 */     if (t != null) {
/* 328 */       buf.append(" <");
/* 329 */       buf.append(t.toString());
/* 330 */       buf.append(">");
/*     */ 
/* 332 */       StringWriter sw = new StringWriter(1024);
/* 333 */       PrintWriter pw = new PrintWriter(sw);
/* 334 */       t.printStackTrace(pw);
/* 335 */       pw.close();
/* 336 */       buf.append(sw.toString());
/*     */     }
/*     */ 
/* 340 */     write(buf);
/*     */   }
/*     */ 
/*     */   protected void write(StringBuffer buffer)
/*     */   {
/* 355 */     System.err.println(buffer.toString());
/*     */   }
/*     */ 
/*     */   protected boolean isLevelEnabled(int logLevel)
/*     */   {
/* 368 */     return logLevel >= this.currentLogLevel;
/*     */   }
/*     */ 
/*     */   public final void debug(Object message)
/*     */   {
/* 384 */     if (isLevelEnabled(2))
/* 385 */       log(2, message, null);
/*     */   }
/*     */ 
/*     */   public final void debug(Object message, Throwable t)
/*     */   {
/* 400 */     if (isLevelEnabled(2))
/* 401 */       log(2, message, t);
/*     */   }
/*     */ 
/*     */   public final void trace(Object message)
/*     */   {
/* 415 */     if (isLevelEnabled(1))
/* 416 */       log(1, message, null);
/*     */   }
/*     */ 
/*     */   public final void trace(Object message, Throwable t)
/*     */   {
/* 431 */     if (isLevelEnabled(1))
/* 432 */       log(1, message, t);
/*     */   }
/*     */ 
/*     */   public final void info(Object message)
/*     */   {
/* 446 */     if (isLevelEnabled(3))
/* 447 */       log(3, message, null);
/*     */   }
/*     */ 
/*     */   public final void info(Object message, Throwable t)
/*     */   {
/* 462 */     if (isLevelEnabled(3))
/* 463 */       log(3, message, t);
/*     */   }
/*     */ 
/*     */   public final void warn(Object message)
/*     */   {
/* 477 */     if (isLevelEnabled(4))
/* 478 */       log(4, message, null);
/*     */   }
/*     */ 
/*     */   public final void warn(Object message, Throwable t)
/*     */   {
/* 493 */     if (isLevelEnabled(4))
/* 494 */       log(4, message, t);
/*     */   }
/*     */ 
/*     */   public final void error(Object message)
/*     */   {
/* 508 */     if (isLevelEnabled(5))
/* 509 */       log(5, message, null);
/*     */   }
/*     */ 
/*     */   public final void error(Object message, Throwable t)
/*     */   {
/* 524 */     if (isLevelEnabled(5))
/* 525 */       log(5, message, t);
/*     */   }
/*     */ 
/*     */   public final void fatal(Object message)
/*     */   {
/* 539 */     if (isLevelEnabled(6))
/* 540 */       log(6, message, null);
/*     */   }
/*     */ 
/*     */   public final void fatal(Object message, Throwable t)
/*     */   {
/* 555 */     if (isLevelEnabled(6))
/* 556 */       log(6, message, t);
/*     */   }
/*     */ 
/*     */   public final boolean isDebugEnabled()
/*     */   {
/* 570 */     return isLevelEnabled(2);
/*     */   }
/*     */ 
/*     */   public final boolean isErrorEnabled()
/*     */   {
/* 583 */     return isLevelEnabled(5);
/*     */   }
/*     */ 
/*     */   public final boolean isFatalEnabled()
/*     */   {
/* 596 */     return isLevelEnabled(6);
/*     */   }
/*     */ 
/*     */   public final boolean isInfoEnabled()
/*     */   {
/* 609 */     return isLevelEnabled(3);
/*     */   }
/*     */ 
/*     */   public final boolean isTraceEnabled()
/*     */   {
/* 622 */     return isLevelEnabled(1);
/*     */   }
/*     */ 
/*     */   public final boolean isWarnEnabled()
/*     */   {
/* 635 */     return isLevelEnabled(4);
/*     */   }
/*     */ 
/*     */   private static ClassLoader getContextClassLoader()
/*     */   {
/* 651 */     ClassLoader classLoader = null;
/*     */ 
/* 653 */     if (classLoader == null) {
/*     */       try
/*     */       {
/* 656 */         Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
/*     */         try
/*     */         {
/* 661 */           classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Class[])null);
/*     */         }
/*     */         catch (IllegalAccessException e)
/*     */         {
/*     */         }
/*     */         catch (InvocationTargetException e)
/*     */         {
/* 682 */           if (!(e.getTargetException() instanceof SecurityException))
/*     */           {
/* 687 */             throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException());
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (NoSuchMethodException e)
/*     */       {
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 697 */     if (classLoader == null) {
/* 698 */       classLoader = SimpleLog.class.getClassLoader();
/*     */     }
/*     */ 
/* 702 */     return classLoader;
/*     */   }
/*     */ 
/*     */   private static InputStream getResourceAsStream(String name)
/*     */   {
/* 707 */     return (InputStream)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       private final String val$name;
/*     */ 
/* 710 */       public Object run() { ClassLoader threadCL = SimpleLog.access$000();
/*     */ 
/* 712 */         if (threadCL != null) {
/* 713 */           return threadCL.getResourceAsStream(this.val$name);
/*     */         }
/* 715 */         return ClassLoader.getSystemResourceAsStream(this.val$name);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 163 */     InputStream in = getResourceAsStream("simplelog.properties");
/* 164 */     if (null != in) {
/*     */       try {
/* 166 */         simpleLogProps.load(in);
/* 167 */         in.close();
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/*     */       }
/*     */     }
/* 173 */     showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
/* 174 */     showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", showShortName);
/* 175 */     showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", showDateTime);
/*     */ 
/* 177 */     if (showDateTime) {
/* 178 */       dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
/*     */       try
/*     */       {
/* 181 */         dateFormatter = new SimpleDateFormat(dateTimeFormat);
/*     */       }
/*     */       catch (IllegalArgumentException e) {
/* 184 */         dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
/* 185 */         dateFormatter = new SimpleDateFormat(dateTimeFormat);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.SimpleLog
 * JD-Core Version:    0.6.2
 */