package org.apache.commons.logging.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import org.apache.commons.logging.Log;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class Log4JLogger
  implements Log, Serializable
{
  private static final String FQCN = Log4JLogger.class.getName();
  private transient Logger logger = null;
  private String name = null;
  private static Priority traceLevel;
  
  public Log4JLogger() {}
  
  public Log4JLogger(String name)
  {
    this.name = name;
    this.logger = getLogger();
  }
  
  public Log4JLogger(Logger logger)
  {
    if (logger == null) {
      throw new IllegalArgumentException("Warning - null logger in constructor; possible log4j misconfiguration.");
    }
    this.name = logger.getName();
    this.logger = logger;
  }
  
  public void trace(Object message)
  {
    getLogger().log(FQCN, traceLevel, message, null);
  }
  
  public void trace(Object message, Throwable local_t)
  {
    getLogger().log(FQCN, traceLevel, message, local_t);
  }
  
  public void debug(Object message)
  {
    getLogger().log(FQCN, Priority.DEBUG, message, null);
  }
  
  public void debug(Object message, Throwable local_t)
  {
    getLogger().log(FQCN, Priority.DEBUG, message, local_t);
  }
  
  public void info(Object message)
  {
    getLogger().log(FQCN, Priority.INFO, message, null);
  }
  
  public void info(Object message, Throwable local_t)
  {
    getLogger().log(FQCN, Priority.INFO, message, local_t);
  }
  
  public void warn(Object message)
  {
    getLogger().log(FQCN, Priority.WARN, message, null);
  }
  
  public void warn(Object message, Throwable local_t)
  {
    getLogger().log(FQCN, Priority.WARN, message, local_t);
  }
  
  public void error(Object message)
  {
    getLogger().log(FQCN, Priority.ERROR, message, null);
  }
  
  public void error(Object message, Throwable local_t)
  {
    getLogger().log(FQCN, Priority.ERROR, message, local_t);
  }
  
  public void fatal(Object message)
  {
    getLogger().log(FQCN, Priority.FATAL, message, null);
  }
  
  public void fatal(Object message, Throwable local_t)
  {
    getLogger().log(FQCN, Priority.FATAL, message, local_t);
  }
  
  public Logger getLogger()
  {
    if (this.logger == null) {
      this.logger = Logger.getLogger(this.name);
    }
    return this.logger;
  }
  
  public boolean isDebugEnabled()
  {
    return getLogger().isDebugEnabled();
  }
  
  public boolean isErrorEnabled()
  {
    return getLogger().isEnabledFor(Priority.ERROR);
  }
  
  public boolean isFatalEnabled()
  {
    return getLogger().isEnabledFor(Priority.FATAL);
  }
  
  public boolean isInfoEnabled()
  {
    return getLogger().isInfoEnabled();
  }
  
  public boolean isTraceEnabled()
  {
    return getLogger().isEnabledFor(traceLevel);
  }
  
  public boolean isWarnEnabled()
  {
    return getLogger().isEnabledFor(Priority.WARN);
  }
  
  static
  {
    if (!Priority.class.isAssignableFrom(Level.class)) {
      throw new InstantiationError("Log4J 1.2 not available");
    }
    try
    {
      traceLevel = (Priority)Level.class.getDeclaredField("TRACE").get(null);
    }
    catch (Exception local_ex)
    {
      traceLevel = Priority.DEBUG;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.impl.Log4JLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */