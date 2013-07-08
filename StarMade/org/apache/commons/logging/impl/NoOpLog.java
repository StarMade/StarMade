/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import org.apache.commons.logging.Log;
/*   5:    */
/*  37:    */public class NoOpLog
/*  38:    */  implements Log, Serializable
/*  39:    */{
/*  40:    */  public NoOpLog() {}
/*  41:    */  
/*  42:    */  public NoOpLog(String name) {}
/*  43:    */  
/*  44:    */  public void trace(Object message) {}
/*  45:    */  
/*  46:    */  public void trace(Object message, Throwable t) {}
/*  47:    */  
/*  48:    */  public void debug(Object message) {}
/*  49:    */  
/*  50:    */  public void debug(Object message, Throwable t) {}
/*  51:    */  
/*  52:    */  public void info(Object message) {}
/*  53:    */  
/*  54:    */  public void info(Object message, Throwable t) {}
/*  55:    */  
/*  56:    */  public void warn(Object message) {}
/*  57:    */  
/*  58:    */  public void warn(Object message, Throwable t) {}
/*  59:    */  
/*  60:    */  public void error(Object message) {}
/*  61:    */  
/*  62:    */  public void error(Object message, Throwable t) {}
/*  63:    */  
/*  64:    */  public void fatal(Object message) {}
/*  65:    */  
/*  66:    */  public void fatal(Object message, Throwable t) {}
/*  67:    */  
/*  68:    */  public final boolean isDebugEnabled()
/*  69:    */  {
/*  70: 70 */    return false;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public final boolean isErrorEnabled()
/*  76:    */  {
/*  77: 77 */    return false;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public final boolean isFatalEnabled()
/*  83:    */  {
/*  84: 84 */    return false;
/*  85:    */  }
/*  86:    */  
/*  89:    */  public final boolean isInfoEnabled()
/*  90:    */  {
/*  91: 91 */    return false;
/*  92:    */  }
/*  93:    */  
/*  96:    */  public final boolean isTraceEnabled()
/*  97:    */  {
/*  98: 98 */    return false;
/*  99:    */  }
/* 100:    */  
/* 103:    */  public final boolean isWarnEnabled()
/* 104:    */  {
/* 105:105 */    return false;
/* 106:    */  }
/* 107:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.NoOpLog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */