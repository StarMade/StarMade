package org.apache.commons.logging.impl;

import org.apache.avalon.framework.logger.Logger;
import org.apache.commons.logging.Log;

public class AvalonLogger
  implements Log
{
  private static Logger defaultLogger = null;
  private transient Logger logger = null;
  
  public AvalonLogger(Logger logger)
  {
    this.logger = logger;
  }
  
  public AvalonLogger(String name)
  {
    if (defaultLogger == null) {
      throw new NullPointerException("default logger has to be specified if this constructor is used!");
    }
    this.logger = defaultLogger.getChildLogger(name);
  }
  
  public Logger getLogger()
  {
    return this.logger;
  }
  
  public static void setDefaultLogger(Logger logger)
  {
    defaultLogger = logger;
  }
  
  public void debug(Object message, Throwable local_t)
  {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(String.valueOf(message), local_t);
    }
  }
  
  public void debug(Object message)
  {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(String.valueOf(message));
    }
  }
  
  public void error(Object message, Throwable local_t)
  {
    if (getLogger().isErrorEnabled()) {
      getLogger().error(String.valueOf(message), local_t);
    }
  }
  
  public void error(Object message)
  {
    if (getLogger().isErrorEnabled()) {
      getLogger().error(String.valueOf(message));
    }
  }
  
  public void fatal(Object message, Throwable local_t)
  {
    if (getLogger().isFatalErrorEnabled()) {
      getLogger().fatalError(String.valueOf(message), local_t);
    }
  }
  
  public void fatal(Object message)
  {
    if (getLogger().isFatalErrorEnabled()) {
      getLogger().fatalError(String.valueOf(message));
    }
  }
  
  public void info(Object message, Throwable local_t)
  {
    if (getLogger().isInfoEnabled()) {
      getLogger().info(String.valueOf(message), local_t);
    }
  }
  
  public void info(Object message)
  {
    if (getLogger().isInfoEnabled()) {
      getLogger().info(String.valueOf(message));
    }
  }
  
  public boolean isDebugEnabled()
  {
    return getLogger().isDebugEnabled();
  }
  
  public boolean isErrorEnabled()
  {
    return getLogger().isErrorEnabled();
  }
  
  public boolean isFatalEnabled()
  {
    return getLogger().isFatalErrorEnabled();
  }
  
  public boolean isInfoEnabled()
  {
    return getLogger().isInfoEnabled();
  }
  
  public boolean isTraceEnabled()
  {
    return getLogger().isDebugEnabled();
  }
  
  public boolean isWarnEnabled()
  {
    return getLogger().isWarnEnabled();
  }
  
  public void trace(Object message, Throwable local_t)
  {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(String.valueOf(message), local_t);
    }
  }
  
  public void trace(Object message)
  {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(String.valueOf(message));
    }
  }
  
  public void warn(Object message, Throwable local_t)
  {
    if (getLogger().isWarnEnabled()) {
      getLogger().warn(String.valueOf(message), local_t);
    }
  }
  
  public void warn(Object message)
  {
    if (getLogger().isWarnEnabled()) {
      getLogger().warn(String.valueOf(message));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.impl.AvalonLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */