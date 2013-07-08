package org.apache.commons.logging.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;

public class SimpleLog
  implements Log, Serializable
{
  protected static final String systemPrefix = "org.apache.commons.logging.simplelog.";
  protected static final Properties simpleLogProps = new Properties();
  protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
  protected static boolean showLogName = false;
  protected static boolean showShortName = true;
  protected static boolean showDateTime = false;
  protected static String dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
  protected static DateFormat dateFormatter = null;
  public static final int LOG_LEVEL_TRACE = 1;
  public static final int LOG_LEVEL_DEBUG = 2;
  public static final int LOG_LEVEL_INFO = 3;
  public static final int LOG_LEVEL_WARN = 4;
  public static final int LOG_LEVEL_ERROR = 5;
  public static final int LOG_LEVEL_FATAL = 6;
  public static final int LOG_LEVEL_ALL = 0;
  public static final int LOG_LEVEL_OFF = 7;
  protected String logName = null;
  protected int currentLogLevel;
  private String shortLogName = null;
  
  private static String getStringProperty(String name)
  {
    String prop = null;
    try
    {
      prop = System.getProperty(name);
    }
    catch (SecurityException local_e) {}
    return prop == null ? simpleLogProps.getProperty(name) : prop;
  }
  
  private static String getStringProperty(String name, String dephault)
  {
    String prop = getStringProperty(name);
    return prop == null ? dephault : prop;
  }
  
  private static boolean getBooleanProperty(String name, boolean dephault)
  {
    String prop = getStringProperty(name);
    return prop == null ? dephault : "true".equalsIgnoreCase(prop);
  }
  
  public SimpleLog(String name)
  {
    this.logName = name;
    setLevel(3);
    String lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + this.logName);
    for (int local_i = String.valueOf(name).lastIndexOf("."); (null == lvl) && (local_i > -1); local_i = String.valueOf(name).lastIndexOf("."))
    {
      name = name.substring(0, local_i);
      lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + name);
    }
    if (null == lvl) {
      lvl = getStringProperty("org.apache.commons.logging.simplelog.defaultlog");
    }
    if ("all".equalsIgnoreCase(lvl)) {
      setLevel(0);
    } else if ("trace".equalsIgnoreCase(lvl)) {
      setLevel(1);
    } else if ("debug".equalsIgnoreCase(lvl)) {
      setLevel(2);
    } else if ("info".equalsIgnoreCase(lvl)) {
      setLevel(3);
    } else if ("warn".equalsIgnoreCase(lvl)) {
      setLevel(4);
    } else if ("error".equalsIgnoreCase(lvl)) {
      setLevel(5);
    } else if ("fatal".equalsIgnoreCase(lvl)) {
      setLevel(6);
    } else if ("off".equalsIgnoreCase(lvl)) {
      setLevel(7);
    }
  }
  
  public void setLevel(int currentLogLevel)
  {
    this.currentLogLevel = currentLogLevel;
  }
  
  public int getLevel()
  {
    return this.currentLogLevel;
  }
  
  protected void log(int type, Object message, Throwable local_t)
  {
    StringBuffer buf = new StringBuffer();
    if (showDateTime)
    {
      Date now = new Date();
      String dateText;
      synchronized (dateFormatter)
      {
        dateText = dateFormatter.format(now);
      }
      String dateText;
      buf.append(dateText);
      buf.append(" ");
    }
    switch (type)
    {
    case 1: 
      buf.append("[TRACE] ");
      break;
    case 2: 
      buf.append("[DEBUG] ");
      break;
    case 3: 
      buf.append("[INFO] ");
      break;
    case 4: 
      buf.append("[WARN] ");
      break;
    case 5: 
      buf.append("[ERROR] ");
      break;
    case 6: 
      buf.append("[FATAL] ");
    }
    if (showShortName)
    {
      if (this.shortLogName == null)
      {
        this.shortLogName = this.logName.substring(this.logName.lastIndexOf(".") + 1);
        this.shortLogName = this.shortLogName.substring(this.shortLogName.lastIndexOf("/") + 1);
      }
      buf.append(String.valueOf(this.shortLogName)).append(" - ");
    }
    else if (showLogName)
    {
      buf.append(String.valueOf(this.logName)).append(" - ");
    }
    buf.append(String.valueOf(message));
    if (local_t != null)
    {
      buf.append(" <");
      buf.append(local_t.toString());
      buf.append(">");
      StringWriter now = new StringWriter(1024);
      PrintWriter dateText = new PrintWriter(now);
      local_t.printStackTrace(dateText);
      dateText.close();
      buf.append(now.toString());
    }
    write(buf);
  }
  
  protected void write(StringBuffer buffer)
  {
    System.err.println(buffer.toString());
  }
  
  protected boolean isLevelEnabled(int logLevel)
  {
    return logLevel >= this.currentLogLevel;
  }
  
  public final void debug(Object message)
  {
    if (isLevelEnabled(2)) {
      log(2, message, null);
    }
  }
  
  public final void debug(Object message, Throwable local_t)
  {
    if (isLevelEnabled(2)) {
      log(2, message, local_t);
    }
  }
  
  public final void trace(Object message)
  {
    if (isLevelEnabled(1)) {
      log(1, message, null);
    }
  }
  
  public final void trace(Object message, Throwable local_t)
  {
    if (isLevelEnabled(1)) {
      log(1, message, local_t);
    }
  }
  
  public final void info(Object message)
  {
    if (isLevelEnabled(3)) {
      log(3, message, null);
    }
  }
  
  public final void info(Object message, Throwable local_t)
  {
    if (isLevelEnabled(3)) {
      log(3, message, local_t);
    }
  }
  
  public final void warn(Object message)
  {
    if (isLevelEnabled(4)) {
      log(4, message, null);
    }
  }
  
  public final void warn(Object message, Throwable local_t)
  {
    if (isLevelEnabled(4)) {
      log(4, message, local_t);
    }
  }
  
  public final void error(Object message)
  {
    if (isLevelEnabled(5)) {
      log(5, message, null);
    }
  }
  
  public final void error(Object message, Throwable local_t)
  {
    if (isLevelEnabled(5)) {
      log(5, message, local_t);
    }
  }
  
  public final void fatal(Object message)
  {
    if (isLevelEnabled(6)) {
      log(6, message, null);
    }
  }
  
  public final void fatal(Object message, Throwable local_t)
  {
    if (isLevelEnabled(6)) {
      log(6, message, local_t);
    }
  }
  
  public final boolean isDebugEnabled()
  {
    return isLevelEnabled(2);
  }
  
  public final boolean isErrorEnabled()
  {
    return isLevelEnabled(5);
  }
  
  public final boolean isFatalEnabled()
  {
    return isLevelEnabled(6);
  }
  
  public final boolean isInfoEnabled()
  {
    return isLevelEnabled(3);
  }
  
  public final boolean isTraceEnabled()
  {
    return isLevelEnabled(1);
  }
  
  public final boolean isWarnEnabled()
  {
    return isLevelEnabled(4);
  }
  
  private static ClassLoader getContextClassLoader()
  {
    ClassLoader classLoader = null;
    if (classLoader == null) {
      try
      {
        Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
        try
        {
          classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Class[])null);
        }
        catch (IllegalAccessException local_e) {}catch (InvocationTargetException local_e)
        {
          if (!(local_e.getTargetException() instanceof SecurityException)) {
            throw new LogConfigurationException("Unexpected InvocationTargetException", local_e.getTargetException());
          }
        }
      }
      catch (NoSuchMethodException method) {}
    }
    if (classLoader == null) {
      classLoader = SimpleLog.class.getClassLoader();
    }
    return classLoader;
  }
  
  private static InputStream getResourceAsStream(String name)
  {
    (InputStream)AccessController.doPrivileged(new PrivilegedAction()
    {
      private final String val$name;
      
      public Object run()
      {
        ClassLoader threadCL = SimpleLog.access$000();
        if (threadCL != null) {
          return threadCL.getResourceAsStream(this.val$name);
        }
        return ClassLoader.getSystemResourceAsStream(this.val$name);
      }
    });
  }
  
  static
  {
    InputStream local_in = getResourceAsStream("simplelog.properties");
    if (null != local_in) {
      try
      {
        simpleLogProps.load(local_in);
        local_in.close();
      }
      catch (IOException local_e) {}
    }
    showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
    showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", showShortName);
    showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", showDateTime);
    if (showDateTime)
    {
      dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
      try
      {
        dateFormatter = new SimpleDateFormat(dateTimeFormat);
      }
      catch (IllegalArgumentException local_e)
      {
        dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
        dateFormatter = new SimpleDateFormat(dateTimeFormat);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.impl.SimpleLog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */