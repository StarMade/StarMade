/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.apache.commons.lang3.builder.Builder;
/*     */ 
/*     */ public class BasicThreadFactory
/*     */   implements ThreadFactory
/*     */ {
/*     */   private final AtomicLong threadCounter;
/*     */   private final ThreadFactory wrappedFactory;
/*     */   private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
/*     */   private final String namingPattern;
/*     */   private final Integer priority;
/*     */   private final Boolean daemonFlag;
/*     */ 
/*     */   private BasicThreadFactory(Builder builder)
/*     */   {
/* 116 */     if (builder.wrappedFactory == null)
/* 117 */       this.wrappedFactory = Executors.defaultThreadFactory();
/*     */     else {
/* 119 */       this.wrappedFactory = builder.wrappedFactory;
/*     */     }
/*     */ 
/* 122 */     this.namingPattern = builder.namingPattern;
/* 123 */     this.priority = builder.priority;
/* 124 */     this.daemonFlag = builder.daemonFlag;
/* 125 */     this.uncaughtExceptionHandler = builder.exceptionHandler;
/*     */ 
/* 127 */     this.threadCounter = new AtomicLong();
/*     */   }
/*     */ 
/*     */   public final ThreadFactory getWrappedFactory()
/*     */   {
/* 139 */     return this.wrappedFactory;
/*     */   }
/*     */ 
/*     */   public final String getNamingPattern()
/*     */   {
/* 149 */     return this.namingPattern;
/*     */   }
/*     */ 
/*     */   public final Boolean getDaemonFlag()
/*     */   {
/* 161 */     return this.daemonFlag;
/*     */   }
/*     */ 
/*     */   public final Integer getPriority()
/*     */   {
/* 171 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public final Thread.UncaughtExceptionHandler getUncaughtExceptionHandler()
/*     */   {
/* 181 */     return this.uncaughtExceptionHandler;
/*     */   }
/*     */ 
/*     */   public long getThreadCount()
/*     */   {
/* 192 */     return this.threadCounter.get();
/*     */   }
/*     */ 
/*     */   public Thread newThread(Runnable r)
/*     */   {
/* 204 */     Thread t = getWrappedFactory().newThread(r);
/* 205 */     initializeThread(t);
/*     */ 
/* 207 */     return t;
/*     */   }
/*     */ 
/*     */   private void initializeThread(Thread t)
/*     */   {
/* 220 */     if (getNamingPattern() != null) {
/* 221 */       Long count = Long.valueOf(this.threadCounter.incrementAndGet());
/* 222 */       t.setName(String.format(getNamingPattern(), new Object[] { count }));
/*     */     }
/*     */ 
/* 225 */     if (getUncaughtExceptionHandler() != null) {
/* 226 */       t.setUncaughtExceptionHandler(getUncaughtExceptionHandler());
/*     */     }
/*     */ 
/* 229 */     if (getPriority() != null) {
/* 230 */       t.setPriority(getPriority().intValue());
/*     */     }
/*     */ 
/* 233 */     if (getDaemonFlag() != null)
/* 234 */       t.setDaemon(getDaemonFlag().booleanValue());
/*     */   }
/*     */ 
/*     */   public static class Builder
/*     */     implements Builder<BasicThreadFactory>
/*     */   {
/*     */     private ThreadFactory wrappedFactory;
/*     */     private Thread.UncaughtExceptionHandler exceptionHandler;
/*     */     private String namingPattern;
/*     */     private Integer priority;
/*     */     private Boolean daemonFlag;
/*     */ 
/*     */     public Builder wrappedFactory(ThreadFactory factory)
/*     */     {
/* 282 */       if (factory == null) {
/* 283 */         throw new NullPointerException("Wrapped ThreadFactory must not be null!");
/*     */       }
/*     */ 
/* 287 */       this.wrappedFactory = factory;
/* 288 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder namingPattern(String pattern)
/*     */     {
/* 300 */       if (pattern == null) {
/* 301 */         throw new NullPointerException("Naming pattern must not be null!");
/*     */       }
/*     */ 
/* 305 */       this.namingPattern = pattern;
/* 306 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder daemon(boolean f)
/*     */     {
/* 318 */       this.daemonFlag = Boolean.valueOf(f);
/* 319 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder priority(int prio)
/*     */     {
/* 330 */       this.priority = Integer.valueOf(prio);
/* 331 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler handler)
/*     */     {
/* 345 */       if (handler == null) {
/* 346 */         throw new NullPointerException("Uncaught exception handler must not be null!");
/*     */       }
/*     */ 
/* 350 */       this.exceptionHandler = handler;
/* 351 */       return this;
/*     */     }
/*     */ 
/*     */     public void reset()
/*     */     {
/* 361 */       this.wrappedFactory = null;
/* 362 */       this.exceptionHandler = null;
/* 363 */       this.namingPattern = null;
/* 364 */       this.priority = null;
/* 365 */       this.daemonFlag = null;
/*     */     }
/*     */ 
/*     */     public BasicThreadFactory build()
/*     */     {
/* 376 */       BasicThreadFactory factory = new BasicThreadFactory(this, null);
/* 377 */       reset();
/* 378 */       return factory;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.BasicThreadFactory
 * JD-Core Version:    0.6.2
 */