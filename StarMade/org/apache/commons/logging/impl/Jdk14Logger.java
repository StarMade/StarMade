/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.logging.Level;
/*   5:    */import java.util.logging.Logger;
/*   6:    */import org.apache.commons.logging.Log;
/*   7:    */
/*  45:    */public class Jdk14Logger
/*  46:    */  implements Log, Serializable
/*  47:    */{
/*  48: 48 */  protected static final Level dummyLevel = Level.FINE;
/*  49:    */  
/*  58:    */  public Jdk14Logger(String name)
/*  59:    */  {
/*  60: 60 */    this.name = name;
/*  61: 61 */    this.logger = getLogger();
/*  62:    */  }
/*  63:    */  
/*  72: 72 */  protected transient Logger logger = null;
/*  73:    */  
/*  78: 78 */  protected String name = null;
/*  79:    */  
/*  83:    */  private void log(Level level, String msg, Throwable ex)
/*  84:    */  {
/*  85: 85 */    Logger logger = getLogger();
/*  86: 86 */    if (logger.isLoggable(level))
/*  87:    */    {
/*  88: 88 */      Throwable dummyException = new Throwable();
/*  89: 89 */      StackTraceElement[] locations = dummyException.getStackTrace();
/*  90:    */      
/*  91: 91 */      String cname = "unknown";
/*  92: 92 */      String method = "unknown";
/*  93: 93 */      if ((locations != null) && (locations.length > 2)) {
/*  94: 94 */        StackTraceElement caller = locations[2];
/*  95: 95 */        cname = caller.getClassName();
/*  96: 96 */        method = caller.getMethodName();
/*  97:    */      }
/*  98: 98 */      if (ex == null) {
/*  99: 99 */        logger.logp(level, cname, method, msg);
/* 100:    */      } else {
/* 101:101 */        logger.logp(level, cname, method, msg, ex);
/* 102:    */      }
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 112:    */  public void debug(Object message)
/* 113:    */  {
/* 114:114 */    log(Level.FINE, String.valueOf(message), null);
/* 115:    */  }
/* 116:    */  
/* 124:    */  public void debug(Object message, Throwable exception)
/* 125:    */  {
/* 126:126 */    log(Level.FINE, String.valueOf(message), exception);
/* 127:    */  }
/* 128:    */  
/* 135:    */  public void error(Object message)
/* 136:    */  {
/* 137:137 */    log(Level.SEVERE, String.valueOf(message), null);
/* 138:    */  }
/* 139:    */  
/* 147:    */  public void error(Object message, Throwable exception)
/* 148:    */  {
/* 149:149 */    log(Level.SEVERE, String.valueOf(message), exception);
/* 150:    */  }
/* 151:    */  
/* 158:    */  public void fatal(Object message)
/* 159:    */  {
/* 160:160 */    log(Level.SEVERE, String.valueOf(message), null);
/* 161:    */  }
/* 162:    */  
/* 170:    */  public void fatal(Object message, Throwable exception)
/* 171:    */  {
/* 172:172 */    log(Level.SEVERE, String.valueOf(message), exception);
/* 173:    */  }
/* 174:    */  
/* 178:    */  public Logger getLogger()
/* 179:    */  {
/* 180:180 */    if (this.logger == null) {
/* 181:181 */      this.logger = Logger.getLogger(this.name);
/* 182:    */    }
/* 183:183 */    return this.logger;
/* 184:    */  }
/* 185:    */  
/* 192:    */  public void info(Object message)
/* 193:    */  {
/* 194:194 */    log(Level.INFO, String.valueOf(message), null);
/* 195:    */  }
/* 196:    */  
/* 204:    */  public void info(Object message, Throwable exception)
/* 205:    */  {
/* 206:206 */    log(Level.INFO, String.valueOf(message), exception);
/* 207:    */  }
/* 208:    */  
/* 212:    */  public boolean isDebugEnabled()
/* 213:    */  {
/* 214:214 */    return getLogger().isLoggable(Level.FINE);
/* 215:    */  }
/* 216:    */  
/* 220:    */  public boolean isErrorEnabled()
/* 221:    */  {
/* 222:222 */    return getLogger().isLoggable(Level.SEVERE);
/* 223:    */  }
/* 224:    */  
/* 228:    */  public boolean isFatalEnabled()
/* 229:    */  {
/* 230:230 */    return getLogger().isLoggable(Level.SEVERE);
/* 231:    */  }
/* 232:    */  
/* 236:    */  public boolean isInfoEnabled()
/* 237:    */  {
/* 238:238 */    return getLogger().isLoggable(Level.INFO);
/* 239:    */  }
/* 240:    */  
/* 244:    */  public boolean isTraceEnabled()
/* 245:    */  {
/* 246:246 */    return getLogger().isLoggable(Level.FINEST);
/* 247:    */  }
/* 248:    */  
/* 252:    */  public boolean isWarnEnabled()
/* 253:    */  {
/* 254:254 */    return getLogger().isLoggable(Level.WARNING);
/* 255:    */  }
/* 256:    */  
/* 263:    */  public void trace(Object message)
/* 264:    */  {
/* 265:265 */    log(Level.FINEST, String.valueOf(message), null);
/* 266:    */  }
/* 267:    */  
/* 275:    */  public void trace(Object message, Throwable exception)
/* 276:    */  {
/* 277:277 */    log(Level.FINEST, String.valueOf(message), exception);
/* 278:    */  }
/* 279:    */  
/* 286:    */  public void warn(Object message)
/* 287:    */  {
/* 288:288 */    log(Level.WARNING, String.valueOf(message), null);
/* 289:    */  }
/* 290:    */  
/* 298:    */  public void warn(Object message, Throwable exception)
/* 299:    */  {
/* 300:300 */    log(Level.WARNING, String.valueOf(message), exception);
/* 301:    */  }
/* 302:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.Jdk14Logger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */