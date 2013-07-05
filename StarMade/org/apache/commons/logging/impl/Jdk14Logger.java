/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class Jdk14Logger
/*     */   implements Log, Serializable
/*     */ {
/*  48 */   protected static final Level dummyLevel = Level.FINE;
/*     */ 
/*  72 */   protected transient Logger logger = null;
/*     */ 
/*  78 */   protected String name = null;
/*     */ 
/*     */   public Jdk14Logger(String name)
/*     */   {
/*  60 */     this.name = name;
/*  61 */     this.logger = getLogger();
/*     */   }
/*     */ 
/*     */   private void log(Level level, String msg, Throwable ex)
/*     */   {
/*  85 */     Logger logger = getLogger();
/*  86 */     if (logger.isLoggable(level))
/*     */     {
/*  88 */       Throwable dummyException = new Throwable();
/*  89 */       StackTraceElement[] locations = dummyException.getStackTrace();
/*     */ 
/*  91 */       String cname = "unknown";
/*  92 */       String method = "unknown";
/*  93 */       if ((locations != null) && (locations.length > 2)) {
/*  94 */         StackTraceElement caller = locations[2];
/*  95 */         cname = caller.getClassName();
/*  96 */         method = caller.getMethodName();
/*     */       }
/*  98 */       if (ex == null)
/*  99 */         logger.logp(level, cname, method, msg);
/*     */       else
/* 101 */         logger.logp(level, cname, method, msg, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void debug(Object message)
/*     */   {
/* 114 */     log(Level.FINE, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void debug(Object message, Throwable exception)
/*     */   {
/* 126 */     log(Level.FINE, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public void error(Object message)
/*     */   {
/* 137 */     log(Level.SEVERE, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void error(Object message, Throwable exception)
/*     */   {
/* 149 */     log(Level.SEVERE, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message)
/*     */   {
/* 160 */     log(Level.SEVERE, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message, Throwable exception)
/*     */   {
/* 172 */     log(Level.SEVERE, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public Logger getLogger()
/*     */   {
/* 180 */     if (this.logger == null) {
/* 181 */       this.logger = Logger.getLogger(this.name);
/*     */     }
/* 183 */     return this.logger;
/*     */   }
/*     */ 
/*     */   public void info(Object message)
/*     */   {
/* 194 */     log(Level.INFO, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void info(Object message, Throwable exception)
/*     */   {
/* 206 */     log(Level.INFO, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public boolean isDebugEnabled()
/*     */   {
/* 214 */     return getLogger().isLoggable(Level.FINE);
/*     */   }
/*     */ 
/*     */   public boolean isErrorEnabled()
/*     */   {
/* 222 */     return getLogger().isLoggable(Level.SEVERE);
/*     */   }
/*     */ 
/*     */   public boolean isFatalEnabled()
/*     */   {
/* 230 */     return getLogger().isLoggable(Level.SEVERE);
/*     */   }
/*     */ 
/*     */   public boolean isInfoEnabled()
/*     */   {
/* 238 */     return getLogger().isLoggable(Level.INFO);
/*     */   }
/*     */ 
/*     */   public boolean isTraceEnabled()
/*     */   {
/* 246 */     return getLogger().isLoggable(Level.FINEST);
/*     */   }
/*     */ 
/*     */   public boolean isWarnEnabled()
/*     */   {
/* 254 */     return getLogger().isLoggable(Level.WARNING);
/*     */   }
/*     */ 
/*     */   public void trace(Object message)
/*     */   {
/* 265 */     log(Level.FINEST, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void trace(Object message, Throwable exception)
/*     */   {
/* 277 */     log(Level.FINEST, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public void warn(Object message)
/*     */   {
/* 288 */     log(Level.WARNING, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void warn(Object message, Throwable exception)
/*     */   {
/* 300 */     log(Level.WARNING, String.valueOf(message), exception);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.Jdk14Logger
 * JD-Core Version:    0.6.2
 */