/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.log.Hierarchy;
/*     */ import org.apache.log.Logger;
/*     */ 
/*     */ public class LogKitLogger
/*     */   implements Log, Serializable
/*     */ {
/*  48 */   protected transient Logger logger = null;
/*     */ 
/*  51 */   protected String name = null;
/*     */ 
/*     */   public LogKitLogger(String name)
/*     */   {
/*  64 */     this.name = name;
/*  65 */     this.logger = getLogger();
/*     */   }
/*     */ 
/*     */   public Logger getLogger()
/*     */   {
/*  77 */     if (this.logger == null) {
/*  78 */       this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
/*     */     }
/*  80 */     return this.logger;
/*     */   }
/*     */ 
/*     */   public void trace(Object message)
/*     */   {
/*  95 */     debug(message);
/*     */   }
/*     */ 
/*     */   public void trace(Object message, Throwable t)
/*     */   {
/* 107 */     debug(message, t);
/*     */   }
/*     */ 
/*     */   public void debug(Object message)
/*     */   {
/* 118 */     if (message != null)
/* 119 */       getLogger().debug(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void debug(Object message, Throwable t)
/*     */   {
/* 132 */     if (message != null)
/* 133 */       getLogger().debug(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void info(Object message)
/*     */   {
/* 145 */     if (message != null)
/* 146 */       getLogger().info(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void info(Object message, Throwable t)
/*     */   {
/* 159 */     if (message != null)
/* 160 */       getLogger().info(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void warn(Object message)
/*     */   {
/* 172 */     if (message != null)
/* 173 */       getLogger().warn(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void warn(Object message, Throwable t)
/*     */   {
/* 186 */     if (message != null)
/* 187 */       getLogger().warn(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void error(Object message)
/*     */   {
/* 199 */     if (message != null)
/* 200 */       getLogger().error(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void error(Object message, Throwable t)
/*     */   {
/* 213 */     if (message != null)
/* 214 */       getLogger().error(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message)
/*     */   {
/* 226 */     if (message != null)
/* 227 */       getLogger().fatalError(String.valueOf(message));
/*     */   }
/*     */ 
/*     */   public void fatal(Object message, Throwable t)
/*     */   {
/* 240 */     if (message != null)
/* 241 */       getLogger().fatalError(String.valueOf(message), t);
/*     */   }
/*     */ 
/*     */   public boolean isDebugEnabled()
/*     */   {
/* 250 */     return getLogger().isDebugEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isErrorEnabled()
/*     */   {
/* 258 */     return getLogger().isErrorEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isFatalEnabled()
/*     */   {
/* 266 */     return getLogger().isFatalErrorEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isInfoEnabled()
/*     */   {
/* 274 */     return getLogger().isInfoEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isTraceEnabled()
/*     */   {
/* 282 */     return getLogger().isDebugEnabled();
/*     */   }
/*     */ 
/*     */   public boolean isWarnEnabled()
/*     */   {
/* 290 */     return getLogger().isWarnEnabled();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogKitLogger
 * JD-Core Version:    0.6.2
 */