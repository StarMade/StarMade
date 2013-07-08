/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import org.apache.avalon.framework.logger.Logger;
/*   4:    */import org.apache.commons.logging.Log;
/*   5:    */
/*  55:    */public class AvalonLogger
/*  56:    */  implements Log
/*  57:    */{
/*  58: 58 */  private static Logger defaultLogger = null;
/*  59:    */  
/*  60: 60 */  private transient Logger logger = null;
/*  61:    */  
/*  66:    */  public AvalonLogger(Logger logger)
/*  67:    */  {
/*  68: 68 */    this.logger = logger;
/*  69:    */  }
/*  70:    */  
/*  75:    */  public AvalonLogger(String name)
/*  76:    */  {
/*  77: 77 */    if (defaultLogger == null)
/*  78: 78 */      throw new NullPointerException("default logger has to be specified if this constructor is used!");
/*  79: 79 */    this.logger = defaultLogger.getChildLogger(name);
/*  80:    */  }
/*  81:    */  
/*  85:    */  public Logger getLogger()
/*  86:    */  {
/*  87: 87 */    return this.logger;
/*  88:    */  }
/*  89:    */  
/*  95:    */  public static void setDefaultLogger(Logger logger)
/*  96:    */  {
/*  97: 97 */    defaultLogger = logger;
/*  98:    */  }
/*  99:    */  
/* 107:    */  public void debug(Object message, Throwable t)
/* 108:    */  {
/* 109:109 */    if (getLogger().isDebugEnabled()) { getLogger().debug(String.valueOf(message), t);
/* 110:    */    }
/* 111:    */  }
/* 112:    */  
/* 118:    */  public void debug(Object message)
/* 119:    */  {
/* 120:120 */    if (getLogger().isDebugEnabled()) { getLogger().debug(String.valueOf(message));
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 130:    */  public void error(Object message, Throwable t)
/* 131:    */  {
/* 132:132 */    if (getLogger().isErrorEnabled()) { getLogger().error(String.valueOf(message), t);
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 141:    */  public void error(Object message)
/* 142:    */  {
/* 143:143 */    if (getLogger().isErrorEnabled()) { getLogger().error(String.valueOf(message));
/* 144:    */    }
/* 145:    */  }
/* 146:    */  
/* 153:    */  public void fatal(Object message, Throwable t)
/* 154:    */  {
/* 155:155 */    if (getLogger().isFatalErrorEnabled()) { getLogger().fatalError(String.valueOf(message), t);
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 164:    */  public void fatal(Object message)
/* 165:    */  {
/* 166:166 */    if (getLogger().isFatalErrorEnabled()) { getLogger().fatalError(String.valueOf(message));
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 176:    */  public void info(Object message, Throwable t)
/* 177:    */  {
/* 178:178 */    if (getLogger().isInfoEnabled()) { getLogger().info(String.valueOf(message), t);
/* 179:    */    }
/* 180:    */  }
/* 181:    */  
/* 187:    */  public void info(Object message)
/* 188:    */  {
/* 189:189 */    if (getLogger().isInfoEnabled()) { getLogger().info(String.valueOf(message));
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 196:    */  public boolean isDebugEnabled()
/* 197:    */  {
/* 198:198 */    return getLogger().isDebugEnabled();
/* 199:    */  }
/* 200:    */  
/* 205:    */  public boolean isErrorEnabled()
/* 206:    */  {
/* 207:207 */    return getLogger().isErrorEnabled();
/* 208:    */  }
/* 209:    */  
/* 214:    */  public boolean isFatalEnabled()
/* 215:    */  {
/* 216:216 */    return getLogger().isFatalErrorEnabled();
/* 217:    */  }
/* 218:    */  
/* 223:    */  public boolean isInfoEnabled()
/* 224:    */  {
/* 225:225 */    return getLogger().isInfoEnabled();
/* 226:    */  }
/* 227:    */  
/* 232:    */  public boolean isTraceEnabled()
/* 233:    */  {
/* 234:234 */    return getLogger().isDebugEnabled();
/* 235:    */  }
/* 236:    */  
/* 241:    */  public boolean isWarnEnabled()
/* 242:    */  {
/* 243:243 */    return getLogger().isWarnEnabled();
/* 244:    */  }
/* 245:    */  
/* 253:    */  public void trace(Object message, Throwable t)
/* 254:    */  {
/* 255:255 */    if (getLogger().isDebugEnabled()) { getLogger().debug(String.valueOf(message), t);
/* 256:    */    }
/* 257:    */  }
/* 258:    */  
/* 264:    */  public void trace(Object message)
/* 265:    */  {
/* 266:266 */    if (getLogger().isDebugEnabled()) { getLogger().debug(String.valueOf(message));
/* 267:    */    }
/* 268:    */  }
/* 269:    */  
/* 276:    */  public void warn(Object message, Throwable t)
/* 277:    */  {
/* 278:278 */    if (getLogger().isWarnEnabled()) { getLogger().warn(String.valueOf(message), t);
/* 279:    */    }
/* 280:    */  }
/* 281:    */  
/* 287:    */  public void warn(Object message)
/* 288:    */  {
/* 289:289 */    if (getLogger().isWarnEnabled()) getLogger().warn(String.valueOf(message));
/* 290:    */  }
/* 291:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.AvalonLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */