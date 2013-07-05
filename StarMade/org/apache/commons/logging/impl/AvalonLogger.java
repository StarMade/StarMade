/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import org.apache.avalon.framework.logger.Logger;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class AvalonLogger
/*     */   implements Log
/*     */ {
/*  58 */   private static Logger defaultLogger = null;
/*     */ 
/*  60 */   private transient Logger logger = null;
/*     */ 
/*     */   public AvalonLogger(Logger logger)
/*     */   {
/*  68 */     this.logger = logger;
/*     */   }
/*     */ 
/*     */   public AvalonLogger(String name)
/*     */   {
/*  77 */     if (defaultLogger == null)
/*  78 */       throw new NullPointerException("default logger has to be specified if this constructor is used!");
/*  79 */     this.logger = defaultLogger.getChildLogger(name);
/*     */   }
/*     */ 
/*     */   public Logger getLogger()
/*     */   {
/*  87 */     return this.logger;
/*     */   }
/*     */ 
/*     */   public static void setDefaultLogger(Logger logger)
/*     */   {
/*  97 */     defaultLogger = logger;
/*     */   }
/*     */ 
/*     */   public void debug(Object message, Throwable t)
/*     */   {
/* 109 */     if (getLogger().isDebugEnabled()) getLogger().debug(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void debug(Object message)
/*     */   {
/* 120 */     if (getLogger().isDebugEnabled()) getLogger().debug(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void error(Object message, Throwable t)
/*     */   {
/* 132 */     if (getLogger().isErrorEnabled()) getLogger().error(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void error(Object message)
/*     */   {
/* 143 */     if (getLogger().isErrorEnabled()) getLogger().error(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void fatal(Object message, Throwable t)
/*     */   {
/* 155 */     if (getLogger().isFatalErrorEnabled()) getLogger().fatalError(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message)
/*     */   {
/* 166 */     if (getLogger().isFatalErrorEnabled()) getLogger().fatalError(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void info(Object message, Throwable t)
/*     */   {
/* 178 */     if (getLogger().isInfoEnabled()) getLogger().info(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void info(Object message)
/*     */   {
/* 189 */     if (getLogger().isInfoEnabled()) getLogger().info(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public boolean isDebugEnabled()
/*     */   {
/* 198 */     return getLogger().isDebugEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isErrorEnabled()
/*     */   {
/* 207 */     return getLogger().isErrorEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isFatalEnabled()
/*     */   {
/* 216 */     return getLogger().isFatalErrorEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isInfoEnabled()
/*     */   {
/* 225 */     return getLogger().isInfoEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isTraceEnabled()
/*     */   {
/* 234 */     return getLogger().isDebugEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isWarnEnabled()
/*     */   {
/* 243 */     return getLogger().isWarnEnabled();
/*     */   }
/*     */ 
/*     */   public void trace(Object message, Throwable t)
/*     */   {
/* 255 */     if (getLogger().isDebugEnabled()) getLogger().debug(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void trace(Object message)
/*     */   {
/* 266 */     if (getLogger().isDebugEnabled()) getLogger().debug(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void warn(Object message, Throwable t)
/*     */   {
/* 278 */     if (getLogger().isWarnEnabled()) getLogger().warn(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void warn(Object message)
/*     */   {
/* 289 */     if (getLogger().isWarnEnabled()) getLogger().warn(String.valueOf(message));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.AvalonLogger
 * JD-Core Version:    0.6.2
 */