package org.apache.commons.logging.impl;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.log.Hierarchy;
import org.apache.log.Logger;

public class LogKitLogger
  implements Log, Serializable
{
  protected transient Logger logger = null;
  protected String name = null;
  
  public LogKitLogger(String name)
  {
    this.name = name;
    this.logger = getLogger();
  }
  
  public Logger getLogger()
  {
    if (this.logger == null) {
      this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
    }
    return this.logger;
  }
  
  public void trace(Object message)
  {
    debug(message);
  }
  
  public void trace(Object message, Throwable local_t)
  {
    debug(message, local_t);
  }
  
  public void debug(Object message)
  {
    if (message != null) {
      getLogger().debug(String.valueOf(message));
    }
  }
  
  public void debug(Object message, Throwable local_t)
  {
    if (message != null) {
      getLogger().debug(String.valueOf(message), local_t);
    }
  }
  
  public void info(Object message)
  {
    if (message != null) {
      getLogger().info(String.valueOf(message));
    }
  }
  
  public void info(Object message, Throwable local_t)
  {
    if (message != null) {
      getLogger().info(String.valueOf(message), local_t);
    }
  }
  
  public void warn(Object message)
  {
    if (message != null) {
      getLogger().warn(String.valueOf(message));
    }
  }
  
  public void warn(Object message, Throwable local_t)
  {
    if (message != null) {
      getLogger().warn(String.valueOf(message), local_t);
    }
  }
  
  public void error(Object message)
  {
    if (message != null) {
      getLogger().error(String.valueOf(message));
    }
  }
  
  public void error(Object message, Throwable local_t)
  {
    if (message != null) {
      getLogger().error(String.valueOf(message), local_t);
    }
  }
  
  public void fatal(Object message)
  {
    if (message != null) {
      getLogger().fatalError(String.valueOf(message));
    }
  }
  
  public void fatal(Object message, Throwable local_t)
  {
    if (message != null) {
      getLogger().fatalError(String.valueOf(message), local_t);
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.impl.LogKitLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */