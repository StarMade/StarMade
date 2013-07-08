/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.lang.reflect.Field;
/*   5:    */import org.apache.commons.logging.Log;
/*   6:    */import org.apache.log4j.Category;
/*   7:    */import org.apache.log4j.Level;
/*   8:    */import org.apache.log4j.Logger;
/*   9:    */import org.apache.log4j.Priority;
/*  10:    */
/*  52:    */public class Log4JLogger
/*  53:    */  implements Log, Serializable
/*  54:    */{
/*  55: 55 */  private static final String FQCN = Log4JLogger.class.getName();
/*  56:    */  
/*  58: 58 */  private transient Logger logger = null;
/*  59:    */  
/*  61: 61 */  private String name = null;
/*  62:    */  
/*  69:    */  private static Priority traceLevel;
/*  70:    */  
/*  78:    */  static
/*  79:    */  {
/*  80: 80 */    if (!Priority.class.isAssignableFrom(Level.class))
/*  81:    */    {
/*  82: 82 */      throw new InstantiationError("Log4J 1.2 not available");
/*  83:    */    }
/*  84:    */    
/*  88:    */    try
/*  89:    */    {
/*  90: 90 */      traceLevel = (Priority)Level.class.getDeclaredField("TRACE").get(null);
/*  91:    */    }
/*  92:    */    catch (Exception ex) {
/*  93: 93 */      traceLevel = Priority.DEBUG;
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/* 106:    */  public Log4JLogger(String name)
/* 107:    */  {
/* 108:108 */    this.name = name;
/* 109:109 */    this.logger = getLogger();
/* 110:    */  }
/* 111:    */  
/* 114:    */  public Log4JLogger(Logger logger)
/* 115:    */  {
/* 116:116 */    if (logger == null) {
/* 117:117 */      throw new IllegalArgumentException("Warning - null logger in constructor; possible log4j misconfiguration.");
/* 118:    */    }
/* 119:    */    
/* 120:120 */    this.name = logger.getName();
/* 121:121 */    this.logger = logger;
/* 122:    */  }
/* 123:    */  
/* 150:    */  public void trace(Object message)
/* 151:    */  {
/* 152:152 */    getLogger().log(FQCN, traceLevel, message, null);
/* 153:    */  }
/* 154:    */  
/* 164:    */  public void trace(Object message, Throwable t)
/* 165:    */  {
/* 166:166 */    getLogger().log(FQCN, traceLevel, message, t);
/* 167:    */  }
/* 168:    */  
/* 175:    */  public void debug(Object message)
/* 176:    */  {
/* 177:177 */    getLogger().log(FQCN, Priority.DEBUG, message, null);
/* 178:    */  }
/* 179:    */  
/* 186:    */  public void debug(Object message, Throwable t)
/* 187:    */  {
/* 188:188 */    getLogger().log(FQCN, Priority.DEBUG, message, t);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public void info(Object message)
/* 198:    */  {
/* 199:199 */    getLogger().log(FQCN, Priority.INFO, message, null);
/* 200:    */  }
/* 201:    */  
/* 209:    */  public void info(Object message, Throwable t)
/* 210:    */  {
/* 211:211 */    getLogger().log(FQCN, Priority.INFO, message, t);
/* 212:    */  }
/* 213:    */  
/* 220:    */  public void warn(Object message)
/* 221:    */  {
/* 222:222 */    getLogger().log(FQCN, Priority.WARN, message, null);
/* 223:    */  }
/* 224:    */  
/* 232:    */  public void warn(Object message, Throwable t)
/* 233:    */  {
/* 234:234 */    getLogger().log(FQCN, Priority.WARN, message, t);
/* 235:    */  }
/* 236:    */  
/* 243:    */  public void error(Object message)
/* 244:    */  {
/* 245:245 */    getLogger().log(FQCN, Priority.ERROR, message, null);
/* 246:    */  }
/* 247:    */  
/* 255:    */  public void error(Object message, Throwable t)
/* 256:    */  {
/* 257:257 */    getLogger().log(FQCN, Priority.ERROR, message, t);
/* 258:    */  }
/* 259:    */  
/* 266:    */  public void fatal(Object message)
/* 267:    */  {
/* 268:268 */    getLogger().log(FQCN, Priority.FATAL, message, null);
/* 269:    */  }
/* 270:    */  
/* 278:    */  public void fatal(Object message, Throwable t)
/* 279:    */  {
/* 280:280 */    getLogger().log(FQCN, Priority.FATAL, message, t);
/* 281:    */  }
/* 282:    */  
/* 286:    */  public Logger getLogger()
/* 287:    */  {
/* 288:288 */    if (this.logger == null) {
/* 289:289 */      this.logger = Logger.getLogger(this.name);
/* 290:    */    }
/* 291:291 */    return this.logger;
/* 292:    */  }
/* 293:    */  
/* 297:    */  public boolean isDebugEnabled()
/* 298:    */  {
/* 299:299 */    return getLogger().isDebugEnabled();
/* 300:    */  }
/* 301:    */  
/* 305:    */  public boolean isErrorEnabled()
/* 306:    */  {
/* 307:307 */    return getLogger().isEnabledFor(Priority.ERROR);
/* 308:    */  }
/* 309:    */  
/* 313:    */  public boolean isFatalEnabled()
/* 314:    */  {
/* 315:315 */    return getLogger().isEnabledFor(Priority.FATAL);
/* 316:    */  }
/* 317:    */  
/* 321:    */  public boolean isInfoEnabled()
/* 322:    */  {
/* 323:323 */    return getLogger().isInfoEnabled();
/* 324:    */  }
/* 325:    */  
/* 331:    */  public boolean isTraceEnabled()
/* 332:    */  {
/* 333:333 */    return getLogger().isEnabledFor(traceLevel);
/* 334:    */  }
/* 335:    */  
/* 338:    */  public boolean isWarnEnabled()
/* 339:    */  {
/* 340:340 */    return getLogger().isEnabledFor(Priority.WARN);
/* 341:    */  }
/* 342:    */  
/* 343:    */  public Log4JLogger() {}
/* 344:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.Log4JLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */