/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Field;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.log4j.Category;
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.log4j.Priority;
/*     */ 
/*     */ public class Log4JLogger
/*     */   implements Log, Serializable
/*     */ {
/*  55 */   private static final String FQCN = Log4JLogger.class.getName();
/*     */ 
/*  58 */   private transient Logger logger = null;
/*     */ 
/*  61 */   private String name = null;
/*     */   private static Priority traceLevel;
/*     */ 
/*     */   public Log4JLogger()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Log4JLogger(String name)
/*     */   {
/* 108 */     this.name = name;
/* 109 */     this.logger = getLogger();
/*     */   }
/*     */ 
/*     */   public Log4JLogger(Logger logger)
/*     */   {
/* 116 */     if (logger == null) {
/* 117 */       throw new IllegalArgumentException("Warning - null logger in constructor; possible log4j misconfiguration.");
/*     */     }
/*     */ 
/* 120 */     this.name = logger.getName();
/* 121 */     this.logger = logger;
/*     */   }
/*     */ 
/*     */   public void trace(Object message)
/*     */   {
/* 152 */     getLogger().log(FQCN, traceLevel, message, null);
/*     */   }
/*     */ 
/*     */   public void trace(Object message, Throwable t)
/*     */   {
/* 166 */     getLogger().log(FQCN, traceLevel, message, t);
/*     */   }
/*     */ 
/*     */   public void debug(Object message)
/*     */   {
/* 177 */     getLogger().log(FQCN, Priority.DEBUG, message, null);
/*     */   }
/*     */ 
/*     */   public void debug(Object message, Throwable t)
/*     */   {
/* 188 */     getLogger().log(FQCN, Priority.DEBUG, message, t);
/*     */   }
/*     */ 
/*     */   public void info(Object message)
/*     */   {
/* 199 */     getLogger().log(FQCN, Priority.INFO, message, null);
/*     */   }
/*     */ 
/*     */   public void info(Object message, Throwable t)
/*     */   {
/* 211 */     getLogger().log(FQCN, Priority.INFO, message, t);
/*     */   }
/*     */ 
/*     */   public void warn(Object message)
/*     */   {
/* 222 */     getLogger().log(FQCN, Priority.WARN, message, null);
/*     */   }
/*     */ 
/*     */   public void warn(Object message, Throwable t)
/*     */   {
/* 234 */     getLogger().log(FQCN, Priority.WARN, message, t);
/*     */   }
/*     */ 
/*     */   public void error(Object message)
/*     */   {
/* 245 */     getLogger().log(FQCN, Priority.ERROR, message, null);
/*     */   }
/*     */ 
/*     */   public void error(Object message, Throwable t)
/*     */   {
/* 257 */     getLogger().log(FQCN, Priority.ERROR, message, t);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message)
/*     */   {
/* 268 */     getLogger().log(FQCN, Priority.FATAL, message, null);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message, Throwable t)
/*     */   {
/* 280 */     getLogger().log(FQCN, Priority.FATAL, message, t);
/*     */   }
/*     */ 
/*     */   public Logger getLogger()
/*     */   {
/* 288 */     if (this.logger == null) {
/* 289 */       this.logger = Logger.getLogger(this.name);
/*     */     }
/* 291 */     return this.logger;
/*     */   }
/*     */ 
/*     */   public boolean isDebugEnabled()
/*     */   {
/* 299 */     return getLogger().isDebugEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isErrorEnabled()
/*     */   {
/* 307 */     return getLogger().isEnabledFor(Priority.ERROR);
/*     */   }
/*     */ 
/*     */   public boolean isFatalEnabled()
/*     */   {
/* 315 */     return getLogger().isEnabledFor(Priority.FATAL);
/*     */   }
/*     */ 
/*     */   public boolean isInfoEnabled()
/*     */   {
/* 323 */     return getLogger().isInfoEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isTraceEnabled()
/*     */   {
/* 333 */     return getLogger().isEnabledFor(traceLevel);
/*     */   }
/*     */ 
/*     */   public boolean isWarnEnabled()
/*     */   {
/* 340 */     return getLogger().isEnabledFor(Priority.WARN);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  80 */     if (!Priority.class.isAssignableFrom(Level.class))
/*     */     {
/*  82 */       throw new InstantiationError("Log4J 1.2 not available");
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  90 */       traceLevel = (Priority)Level.class.getDeclaredField("TRACE").get(null);
/*     */     }
/*     */     catch (Exception ex) {
/*  93 */       traceLevel = Priority.DEBUG;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.Log4JLogger
 * JD-Core Version:    0.6.2
 */