/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.io.StringWriter;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class Jdk13LumberjackLogger
/*     */   implements Log, Serializable
/*     */ {
/*  55 */   protected transient Logger logger = null;
/*  56 */   protected String name = null;
/*  57 */   private String sourceClassName = "unknown";
/*  58 */   private String sourceMethodName = "unknown";
/*  59 */   private boolean classAndMethodFound = false;
/*     */ 
/*  68 */   protected static final Level dummyLevel = Level.FINE;
/*     */ 
/*     */   public Jdk13LumberjackLogger(String name)
/*     */   {
/*  80 */     this.name = name;
/*  81 */     this.logger = getLogger();
/*     */   }
/*     */ 
/*     */   private void log(Level level, String msg, Throwable ex)
/*     */   {
/*  90 */     if (getLogger().isLoggable(level)) {
/*  91 */       LogRecord record = new LogRecord(level, msg);
/*  92 */       if (!this.classAndMethodFound) {
/*  93 */         getClassAndMethod();
/*     */       }
/*  95 */       record.setSourceClassName(this.sourceClassName);
/*  96 */       record.setSourceMethodName(this.sourceMethodName);
/*  97 */       if (ex != null) {
/*  98 */         record.setThrown(ex);
/*     */       }
/* 100 */       getLogger().log(record);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void getClassAndMethod()
/*     */   {
/*     */     try
/*     */     {
/* 110 */       Throwable throwable = new Throwable();
/* 111 */       throwable.fillInStackTrace();
/* 112 */       StringWriter stringWriter = new StringWriter();
/* 113 */       PrintWriter printWriter = new PrintWriter(stringWriter);
/* 114 */       throwable.printStackTrace(printWriter);
/* 115 */       String traceString = stringWriter.getBuffer().toString();
/* 116 */       StringTokenizer tokenizer = new StringTokenizer(traceString, "\n");
/*     */ 
/* 118 */       tokenizer.nextToken();
/* 119 */       String line = tokenizer.nextToken();
/* 120 */       while (line.indexOf(getClass().getName()) == -1) {
/* 121 */         line = tokenizer.nextToken();
/*     */       }
/* 123 */       while (line.indexOf(getClass().getName()) >= 0) {
/* 124 */         line = tokenizer.nextToken();
/*     */       }
/* 126 */       int start = line.indexOf("at ") + 3;
/* 127 */       int end = line.indexOf('(');
/* 128 */       String temp = line.substring(start, end);
/* 129 */       int lastPeriod = temp.lastIndexOf('.');
/* 130 */       this.sourceClassName = temp.substring(0, lastPeriod);
/* 131 */       this.sourceMethodName = temp.substring(lastPeriod + 1);
/*     */     }
/*     */     catch (Exception ex) {
/*     */     }
/* 135 */     this.classAndMethodFound = true;
/*     */   }
/*     */ 
/*     */   public void debug(Object message)
/*     */   {
/* 145 */     log(Level.FINE, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void debug(Object message, Throwable exception)
/*     */   {
/* 157 */     log(Level.FINE, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public void error(Object message)
/*     */   {
/* 168 */     log(Level.SEVERE, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void error(Object message, Throwable exception)
/*     */   {
/* 180 */     log(Level.SEVERE, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message)
/*     */   {
/* 191 */     log(Level.SEVERE, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void fatal(Object message, Throwable exception)
/*     */   {
/* 203 */     log(Level.SEVERE, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public Logger getLogger()
/*     */   {
/* 211 */     if (this.logger == null) {
/* 212 */       this.logger = Logger.getLogger(this.name);
/*     */     }
/* 214 */     return this.logger;
/*     */   }
/*     */ 
/*     */   public void info(Object message)
/*     */   {
/* 225 */     log(Level.INFO, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void info(Object message, Throwable exception)
/*     */   {
/* 237 */     log(Level.INFO, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public boolean isDebugEnabled()
/*     */   {
/* 245 */     return getLogger().isLoggable(Level.FINE);
/*     */   }
/*     */ 
/*     */   public boolean isErrorEnabled()
/*     */   {
/* 253 */     return getLogger().isLoggable(Level.SEVERE);
/*     */   }
/*     */ 
/*     */   public boolean isFatalEnabled()
/*     */   {
/* 261 */     return getLogger().isLoggable(Level.SEVERE);
/*     */   }
/*     */ 
/*     */   public boolean isInfoEnabled()
/*     */   {
/* 269 */     return getLogger().isLoggable(Level.INFO);
/*     */   }
/*     */ 
/*     */   public boolean isTraceEnabled()
/*     */   {
/* 277 */     return getLogger().isLoggable(Level.FINEST);
/*     */   }
/*     */ 
/*     */   public boolean isWarnEnabled()
/*     */   {
/* 285 */     return getLogger().isLoggable(Level.WARNING);
/*     */   }
/*     */ 
/*     */   public void trace(Object message)
/*     */   {
/* 296 */     log(Level.FINEST, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void trace(Object message, Throwable exception)
/*     */   {
/* 308 */     log(Level.FINEST, String.valueOf(message), exception);
/*     */   }
/*     */ 
/*     */   public void warn(Object message)
/*     */   {
/* 319 */     log(Level.WARNING, String.valueOf(message), null);
/*     */   }
/*     */ 
/*     */   public void warn(Object message, Throwable exception)
/*     */   {
/* 331 */     log(Level.WARNING, String.valueOf(message), exception);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.Jdk13LumberjackLogger
 * JD-Core Version:    0.6.2
 */