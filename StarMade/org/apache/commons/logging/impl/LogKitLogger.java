/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import org.apache.commons.logging.Log;
/*   5:    */import org.apache.log.Hierarchy;
/*   6:    */import org.apache.log.Logger;
/*   7:    */
/*  45:    */public class LogKitLogger
/*  46:    */  implements Log, Serializable
/*  47:    */{
/*  48: 48 */  protected transient Logger logger = null;
/*  49:    */  
/*  51: 51 */  protected String name = null;
/*  52:    */  
/*  62:    */  public LogKitLogger(String name)
/*  63:    */  {
/*  64: 64 */    this.name = name;
/*  65: 65 */    this.logger = getLogger();
/*  66:    */  }
/*  67:    */  
/*  75:    */  public Logger getLogger()
/*  76:    */  {
/*  77: 77 */    if (this.logger == null) {
/*  78: 78 */      this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
/*  79:    */    }
/*  80: 80 */    return this.logger;
/*  81:    */  }
/*  82:    */  
/*  93:    */  public void trace(Object message)
/*  94:    */  {
/*  95: 95 */    debug(message);
/*  96:    */  }
/*  97:    */  
/* 105:    */  public void trace(Object message, Throwable t)
/* 106:    */  {
/* 107:107 */    debug(message, t);
/* 108:    */  }
/* 109:    */  
/* 116:    */  public void debug(Object message)
/* 117:    */  {
/* 118:118 */    if (message != null) {
/* 119:119 */      getLogger().debug(String.valueOf(message));
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 130:    */  public void debug(Object message, Throwable t)
/* 131:    */  {
/* 132:132 */    if (message != null) {
/* 133:133 */      getLogger().debug(String.valueOf(message), t);
/* 134:    */    }
/* 135:    */  }
/* 136:    */  
/* 143:    */  public void info(Object message)
/* 144:    */  {
/* 145:145 */    if (message != null) {
/* 146:146 */      getLogger().info(String.valueOf(message));
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 157:    */  public void info(Object message, Throwable t)
/* 158:    */  {
/* 159:159 */    if (message != null) {
/* 160:160 */      getLogger().info(String.valueOf(message), t);
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 170:    */  public void warn(Object message)
/* 171:    */  {
/* 172:172 */    if (message != null) {
/* 173:173 */      getLogger().warn(String.valueOf(message));
/* 174:    */    }
/* 175:    */  }
/* 176:    */  
/* 184:    */  public void warn(Object message, Throwable t)
/* 185:    */  {
/* 186:186 */    if (message != null) {
/* 187:187 */      getLogger().warn(String.valueOf(message), t);
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 197:    */  public void error(Object message)
/* 198:    */  {
/* 199:199 */    if (message != null) {
/* 200:200 */      getLogger().error(String.valueOf(message));
/* 201:    */    }
/* 202:    */  }
/* 203:    */  
/* 211:    */  public void error(Object message, Throwable t)
/* 212:    */  {
/* 213:213 */    if (message != null) {
/* 214:214 */      getLogger().error(String.valueOf(message), t);
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 224:    */  public void fatal(Object message)
/* 225:    */  {
/* 226:226 */    if (message != null) {
/* 227:227 */      getLogger().fatalError(String.valueOf(message));
/* 228:    */    }
/* 229:    */  }
/* 230:    */  
/* 238:    */  public void fatal(Object message, Throwable t)
/* 239:    */  {
/* 240:240 */    if (message != null) {
/* 241:241 */      getLogger().fatalError(String.valueOf(message), t);
/* 242:    */    }
/* 243:    */  }
/* 244:    */  
/* 248:    */  public boolean isDebugEnabled()
/* 249:    */  {
/* 250:250 */    return getLogger().isDebugEnabled();
/* 251:    */  }
/* 252:    */  
/* 256:    */  public boolean isErrorEnabled()
/* 257:    */  {
/* 258:258 */    return getLogger().isErrorEnabled();
/* 259:    */  }
/* 260:    */  
/* 264:    */  public boolean isFatalEnabled()
/* 265:    */  {
/* 266:266 */    return getLogger().isFatalErrorEnabled();
/* 267:    */  }
/* 268:    */  
/* 272:    */  public boolean isInfoEnabled()
/* 273:    */  {
/* 274:274 */    return getLogger().isInfoEnabled();
/* 275:    */  }
/* 276:    */  
/* 280:    */  public boolean isTraceEnabled()
/* 281:    */  {
/* 282:282 */    return getLogger().isDebugEnabled();
/* 283:    */  }
/* 284:    */  
/* 288:    */  public boolean isWarnEnabled()
/* 289:    */  {
/* 290:290 */    return getLogger().isWarnEnabled();
/* 291:    */  }
/* 292:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogKitLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */