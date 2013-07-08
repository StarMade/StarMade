package org.hsqldb.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FrameworkLogger
{
  private static Map loggerInstances = new HashMap();
  private static Map jdkToLog4jLevels = new HashMap();
  private static Method log4jGetLogger;
  private static Method log4jLogMethod;
  private Object log4jLogger;
  private Logger jdkLogger;
  private static boolean noopMode;
  
  public static String report()
  {
    return loggerInstances.size() + " logger instances:  " + loggerInstances.keySet();
  }
  
  public static synchronized void clearLoggers(String paramString)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = loggerInstances.keySet().iterator();
    String str2 = paramString + '.';
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      if ((str1.equals(paramString)) || (str1.startsWith(str2))) {
        localHashSet.add(str1);
      }
    }
    loggerInstances.keySet().removeAll(localHashSet);
  }
  
  static void reconfigure()
  {
    noopMode = false;
    Class localClass = null;
    loggerInstances.clear();
    localClass = null;
    log4jGetLogger = null;
    log4jLogMethod = null;
    try
    {
      localClass = Class.forName("org.apache.log4j.Logger");
    }
    catch (Exception localException1) {}
    if (localClass != null) {
      try
      {
        if (jdkToLog4jLevels.size() < 1)
        {
          Method localMethod = Class.forName("org.apache.log4j.Level").getMethod("toLevel", new Class[] { String.class });
          jdkToLog4jLevels.put(Level.ALL, localMethod.invoke(null, new Object[] { "ALL" }));
          jdkToLog4jLevels.put(Level.FINER, localMethod.invoke(null, new Object[] { "DEBUG" }));
          jdkToLog4jLevels.put(Level.WARNING, localMethod.invoke(null, new Object[] { "ERROR" }));
          jdkToLog4jLevels.put(Level.SEVERE, localMethod.invoke(null, new Object[] { "FATAL" }));
          jdkToLog4jLevels.put(Level.INFO, localMethod.invoke(null, new Object[] { "INFO" }));
          jdkToLog4jLevels.put(Level.OFF, localMethod.invoke(null, new Object[] { "OFF" }));
          jdkToLog4jLevels.put(Level.FINEST, localMethod.invoke(null, new Object[] { "TRACE" }));
          jdkToLog4jLevels.put(Level.WARNING, localMethod.invoke(null, new Object[] { "WARN" }));
        }
        log4jLogMethod = localClass.getMethod("log", new Class[] { String.class, Class.forName("org.apache.log4j.Priority"), Object.class, Throwable.class });
        log4jGetLogger = localClass.getMethod("getLogger", new Class[] { String.class });
        return;
      }
      catch (Exception localException2)
      {
        try
        {
          System.err.println("<clinit> failure instantiating configured Log4j system: " + localException2);
        }
        catch (Throwable localThrowable) {}
      }
    }
    localClass = null;
    log4jLogMethod = null;
    log4jGetLogger = null;
    String str = System.getProperty("hsqldb.reconfig_logging");
    if ((str != null) && (str.equalsIgnoreCase("false"))) {
      return;
    }
    try
    {
      LogManager localLogManager = LogManager.getLogManager();
      if (isDefaultJdkConfig())
      {
        localLogManager.reset();
        ConsoleHandler localConsoleHandler = new ConsoleHandler();
        localConsoleHandler.setFormatter(new BasicTextJdkLogFormatter(false));
        localConsoleHandler.setLevel(Level.INFO);
        localLogManager.readConfiguration(FrameworkLogger.class.getResourceAsStream("/org/hsqldb/resources/jdklogging-default.properties"));
        Logger localLogger = Logger.getLogger("org.hsqldb.cmdline");
        localLogger.addHandler(localConsoleHandler);
        localLogger.setUseParentHandlers(false);
      }
      else
      {
        localLogManager.readConfiguration();
      }
    }
    catch (Exception localException3)
    {
      noopMode = true;
      System.err.println("<clinit> failure initializing JDK logging system.  Continuing without Application logging.");
      localException3.printStackTrace();
    }
  }
  
  private FrameworkLogger(String paramString)
  {
    if (!noopMode) {
      if (log4jGetLogger == null) {
        this.jdkLogger = Logger.getLogger(paramString);
      } else {
        try
        {
          this.log4jLogger = log4jGetLogger.invoke(null, new Object[] { paramString });
        }
        catch (Exception localException)
        {
          throw new RuntimeException("Failed to instantiate Log4j Logger", localException);
        }
      }
    }
    loggerInstances.put(paramString, this);
  }
  
  public static FrameworkLogger getLog(Class paramClass)
  {
    return getLog(paramClass.getName());
  }
  
  public static FrameworkLogger getLog(Class paramClass, String paramString)
  {
    return paramString == null ? getLog(paramClass) : getLog(paramString + '.' + paramClass.getName());
  }
  
  public static FrameworkLogger getLog(String paramString1, String paramString2)
  {
    return paramString2 == null ? getLog(paramString1) : getLog(paramString2 + '.' + paramString1);
  }
  
  public static FrameworkLogger getLog(String paramString)
  {
    if (loggerInstances.containsKey(paramString)) {
      return (FrameworkLogger)loggerInstances.get(paramString);
    }
    return new FrameworkLogger(paramString);
  }
  
  public void log(Level paramLevel, String paramString, Throwable paramThrowable)
  {
    privlog(paramLevel, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void privlog(Level paramLevel, String paramString, Throwable paramThrowable, int paramInt, Class paramClass)
  {
    if (noopMode) {
      return;
    }
    if (this.log4jLogger == null)
    {
      StackTraceElement[] arrayOfStackTraceElement = new Throwable().getStackTrace();
      String str1 = arrayOfStackTraceElement[paramInt].getClassName();
      String str2 = arrayOfStackTraceElement[paramInt].getMethodName();
      if (paramThrowable == null) {
        this.jdkLogger.logp(paramLevel, str1, str2, paramString);
      } else {
        this.jdkLogger.logp(paramLevel, str1, str2, paramString, paramThrowable);
      }
    }
    else
    {
      try
      {
        log4jLogMethod.invoke(this.log4jLogger, new Object[] { paramClass.getName(), jdkToLog4jLevels.get(paramLevel), paramString, paramThrowable });
      }
      catch (Exception localException)
      {
        throw new RuntimeException("Logging failed when attempting to log: " + paramString, localException);
      }
    }
  }
  
  public void enduserlog(Level paramLevel, String paramString)
  {
    if (noopMode) {
      return;
    }
    if (this.log4jLogger == null)
    {
      String str1 = FrameworkLogger.class.getName();
      String str2 = "\\l";
      this.jdkLogger.logp(paramLevel, str1, str2, paramString);
    }
    else
    {
      try
      {
        log4jLogMethod.invoke(this.log4jLogger, new Object[] { FrameworkLogger.class.getName(), jdkToLog4jLevels.get(paramLevel), paramString, null });
      }
      catch (Exception localException)
      {
        throw new RuntimeException("Logging failed when attempting to log: " + paramString, localException);
      }
    }
  }
  
  public void log(Level paramLevel, String paramString)
  {
    privlog(paramLevel, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void finer(String paramString)
  {
    privlog(Level.FINER, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void warning(String paramString)
  {
    privlog(Level.WARNING, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void severe(String paramString)
  {
    privlog(Level.SEVERE, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void info(String paramString)
  {
    privlog(Level.INFO, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void finest(String paramString)
  {
    privlog(Level.FINEST, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void error(String paramString)
  {
    privlog(Level.WARNING, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void finer(String paramString, Throwable paramThrowable)
  {
    privlog(Level.FINER, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void warning(String paramString, Throwable paramThrowable)
  {
    privlog(Level.WARNING, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void severe(String paramString, Throwable paramThrowable)
  {
    privlog(Level.SEVERE, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void info(String paramString, Throwable paramThrowable)
  {
    privlog(Level.INFO, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void finest(String paramString, Throwable paramThrowable)
  {
    privlog(Level.FINEST, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void error(String paramString, Throwable paramThrowable)
  {
    privlog(Level.WARNING, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public static boolean isDefaultJdkConfig()
  {
    File localFile = new File(System.getProperty("java.home"), "lib/logging.properties");
    if (!localFile.isFile()) {
      return false;
    }
    FileInputStream localFileInputStream = null;
    LogManager localLogManager = LogManager.getLogManager();
    try
    {
      localFileInputStream = new FileInputStream(localFile);
      Properties localProperties = new Properties();
      localProperties.load(localFileInputStream);
      Enumeration localEnumeration = localProperties.propertyNames();
      int i = 0;
      while (localEnumeration.hasMoreElements())
      {
        i++;
        String str1 = (String)localEnumeration.nextElement();
        String str2 = localLogManager.getProperty(str1);
        if (str2 == null)
        {
          bool2 = false;
          return bool2;
        }
        if (!localLogManager.getProperty(str1).equals(str2))
        {
          bool2 = false;
          return bool2;
        }
      }
      boolean bool2 = true;
      return bool2;
    }
    catch (IOException localIOException1)
    {
      boolean bool1 = false;
      return bool1;
    }
    finally
    {
      if (localFileInputStream != null) {
        try
        {
          localFileInputStream.close();
        }
        catch (IOException localIOException6) {}
      }
    }
  }
  
  static
  {
    try
    {
      reconfigure();
    }
    catch (SecurityException localSecurityException) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.FrameworkLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */