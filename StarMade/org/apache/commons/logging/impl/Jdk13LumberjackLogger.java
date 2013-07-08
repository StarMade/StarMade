/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.PrintWriter;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.io.StringWriter;
/*   6:    */import java.util.StringTokenizer;
/*   7:    */import java.util.logging.Level;
/*   8:    */import java.util.logging.LogRecord;
/*   9:    */import java.util.logging.Logger;
/*  10:    */import org.apache.commons.logging.Log;
/*  11:    */
/*  52:    */public class Jdk13LumberjackLogger
/*  53:    */  implements Log, Serializable
/*  54:    */{
/*  55: 55 */  protected transient Logger logger = null;
/*  56: 56 */  protected String name = null;
/*  57: 57 */  private String sourceClassName = "unknown";
/*  58: 58 */  private String sourceMethodName = "unknown";
/*  59: 59 */  private boolean classAndMethodFound = false;
/*  60:    */  
/*  68: 68 */  protected static final Level dummyLevel = Level.FINE;
/*  69:    */  
/*  78:    */  public Jdk13LumberjackLogger(String name)
/*  79:    */  {
/*  80: 80 */    this.name = name;
/*  81: 81 */    this.logger = getLogger();
/*  82:    */  }
/*  83:    */  
/*  88:    */  private void log(Level level, String msg, Throwable ex)
/*  89:    */  {
/*  90: 90 */    if (getLogger().isLoggable(level)) {
/*  91: 91 */      LogRecord record = new LogRecord(level, msg);
/*  92: 92 */      if (!this.classAndMethodFound) {
/*  93: 93 */        getClassAndMethod();
/*  94:    */      }
/*  95: 95 */      record.setSourceClassName(this.sourceClassName);
/*  96: 96 */      record.setSourceMethodName(this.sourceMethodName);
/*  97: 97 */      if (ex != null) {
/*  98: 98 */        record.setThrown(ex);
/*  99:    */      }
/* 100:100 */      getLogger().log(record);
/* 101:    */    }
/* 102:    */  }
/* 103:    */  
/* 106:    */  private void getClassAndMethod()
/* 107:    */  {
/* 108:    */    try
/* 109:    */    {
/* 110:110 */      Throwable throwable = new Throwable();
/* 111:111 */      throwable.fillInStackTrace();
/* 112:112 */      StringWriter stringWriter = new StringWriter();
/* 113:113 */      PrintWriter printWriter = new PrintWriter(stringWriter);
/* 114:114 */      throwable.printStackTrace(printWriter);
/* 115:115 */      String traceString = stringWriter.getBuffer().toString();
/* 116:116 */      StringTokenizer tokenizer = new StringTokenizer(traceString, "\n");
/* 117:    */      
/* 118:118 */      tokenizer.nextToken();
/* 119:119 */      String line = tokenizer.nextToken();
/* 120:120 */      while (line.indexOf(getClass().getName()) == -1) {
/* 121:121 */        line = tokenizer.nextToken();
/* 122:    */      }
/* 123:123 */      while (line.indexOf(getClass().getName()) >= 0) {
/* 124:124 */        line = tokenizer.nextToken();
/* 125:    */      }
/* 126:126 */      int start = line.indexOf("at ") + 3;
/* 127:127 */      int end = line.indexOf('(');
/* 128:128 */      String temp = line.substring(start, end);
/* 129:129 */      int lastPeriod = temp.lastIndexOf('.');
/* 130:130 */      this.sourceClassName = temp.substring(0, lastPeriod);
/* 131:131 */      this.sourceMethodName = temp.substring(lastPeriod + 1);
/* 132:    */    }
/* 133:    */    catch (Exception ex) {}
/* 134:    */    
/* 135:135 */    this.classAndMethodFound = true;
/* 136:    */  }
/* 137:    */  
/* 143:    */  public void debug(Object message)
/* 144:    */  {
/* 145:145 */    log(Level.FINE, String.valueOf(message), null);
/* 146:    */  }
/* 147:    */  
/* 155:    */  public void debug(Object message, Throwable exception)
/* 156:    */  {
/* 157:157 */    log(Level.FINE, String.valueOf(message), exception);
/* 158:    */  }
/* 159:    */  
/* 166:    */  public void error(Object message)
/* 167:    */  {
/* 168:168 */    log(Level.SEVERE, String.valueOf(message), null);
/* 169:    */  }
/* 170:    */  
/* 178:    */  public void error(Object message, Throwable exception)
/* 179:    */  {
/* 180:180 */    log(Level.SEVERE, String.valueOf(message), exception);
/* 181:    */  }
/* 182:    */  
/* 189:    */  public void fatal(Object message)
/* 190:    */  {
/* 191:191 */    log(Level.SEVERE, String.valueOf(message), null);
/* 192:    */  }
/* 193:    */  
/* 201:    */  public void fatal(Object message, Throwable exception)
/* 202:    */  {
/* 203:203 */    log(Level.SEVERE, String.valueOf(message), exception);
/* 204:    */  }
/* 205:    */  
/* 209:    */  public Logger getLogger()
/* 210:    */  {
/* 211:211 */    if (this.logger == null) {
/* 212:212 */      this.logger = Logger.getLogger(this.name);
/* 213:    */    }
/* 214:214 */    return this.logger;
/* 215:    */  }
/* 216:    */  
/* 223:    */  public void info(Object message)
/* 224:    */  {
/* 225:225 */    log(Level.INFO, String.valueOf(message), null);
/* 226:    */  }
/* 227:    */  
/* 235:    */  public void info(Object message, Throwable exception)
/* 236:    */  {
/* 237:237 */    log(Level.INFO, String.valueOf(message), exception);
/* 238:    */  }
/* 239:    */  
/* 243:    */  public boolean isDebugEnabled()
/* 244:    */  {
/* 245:245 */    return getLogger().isLoggable(Level.FINE);
/* 246:    */  }
/* 247:    */  
/* 251:    */  public boolean isErrorEnabled()
/* 252:    */  {
/* 253:253 */    return getLogger().isLoggable(Level.SEVERE);
/* 254:    */  }
/* 255:    */  
/* 259:    */  public boolean isFatalEnabled()
/* 260:    */  {
/* 261:261 */    return getLogger().isLoggable(Level.SEVERE);
/* 262:    */  }
/* 263:    */  
/* 267:    */  public boolean isInfoEnabled()
/* 268:    */  {
/* 269:269 */    return getLogger().isLoggable(Level.INFO);
/* 270:    */  }
/* 271:    */  
/* 275:    */  public boolean isTraceEnabled()
/* 276:    */  {
/* 277:277 */    return getLogger().isLoggable(Level.FINEST);
/* 278:    */  }
/* 279:    */  
/* 283:    */  public boolean isWarnEnabled()
/* 284:    */  {
/* 285:285 */    return getLogger().isLoggable(Level.WARNING);
/* 286:    */  }
/* 287:    */  
/* 294:    */  public void trace(Object message)
/* 295:    */  {
/* 296:296 */    log(Level.FINEST, String.valueOf(message), null);
/* 297:    */  }
/* 298:    */  
/* 306:    */  public void trace(Object message, Throwable exception)
/* 307:    */  {
/* 308:308 */    log(Level.FINEST, String.valueOf(message), exception);
/* 309:    */  }
/* 310:    */  
/* 317:    */  public void warn(Object message)
/* 318:    */  {
/* 319:319 */    log(Level.WARNING, String.valueOf(message), null);
/* 320:    */  }
/* 321:    */  
/* 329:    */  public void warn(Object message, Throwable exception)
/* 330:    */  {
/* 331:331 */    log(Level.WARNING, String.valueOf(message), exception);
/* 332:    */  }
/* 333:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.Jdk13LumberjackLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */