/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class TimedSemaphore
/*     */ {
/*     */   public static final int NO_LIMIT = 0;
/*     */   private static final int THREAD_POOL_SIZE = 1;
/*     */   private final ScheduledExecutorService executorService;
/*     */   private final long period;
/*     */   private final TimeUnit unit;
/*     */   private final boolean ownExecutor;
/*     */   private ScheduledFuture<?> task;
/*     */   private long totalAcquireCount;
/*     */   private long periodCount;
/*     */   private int limit;
/*     */   private int acquireCount;
/*     */   private int lastCallsPerPeriod;
/*     */   private boolean shutdown;
/*     */ 
/*     */   public TimedSemaphore(long timePeriod, TimeUnit timeUnit, int limit)
/*     */   {
/* 185 */     this(null, timePeriod, timeUnit, limit);
/*     */   }
/*     */ 
/*     */   public TimedSemaphore(ScheduledExecutorService service, long timePeriod, TimeUnit timeUnit, int limit)
/*     */   {
/* 202 */     if (timePeriod <= 0L) {
/* 203 */       throw new IllegalArgumentException("Time period must be greater 0!");
/*     */     }
/*     */ 
/* 206 */     this.period = timePeriod;
/* 207 */     this.unit = timeUnit;
/*     */ 
/* 209 */     if (service != null) {
/* 210 */       this.executorService = service;
/* 211 */       this.ownExecutor = false;
/*     */     } else {
/* 213 */       ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(1);
/*     */ 
/* 215 */       s.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
/* 216 */       s.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
/* 217 */       this.executorService = s;
/* 218 */       this.ownExecutor = true;
/*     */     }
/*     */ 
/* 221 */     setLimit(limit);
/*     */   }
/*     */ 
/*     */   public final synchronized int getLimit()
/*     */   {
/* 232 */     return this.limit;
/*     */   }
/*     */ 
/*     */   public final synchronized void setLimit(int limit)
/*     */   {
/* 246 */     this.limit = limit;
/*     */   }
/*     */ 
/*     */   public synchronized void shutdown()
/*     */   {
/* 255 */     if (!this.shutdown)
/*     */     {
/* 257 */       if (this.ownExecutor)
/*     */       {
/* 260 */         getExecutorService().shutdownNow();
/*     */       }
/* 262 */       if (this.task != null) {
/* 263 */         this.task.cancel(false);
/*     */       }
/*     */ 
/* 266 */       this.shutdown = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized boolean isShutdown()
/*     */   {
/* 278 */     return this.shutdown;
/*     */   }
/*     */ 
/*     */   public synchronized void acquire()
/*     */     throws InterruptedException
/*     */   {
/* 293 */     if (isShutdown()) {
/* 294 */       throw new IllegalStateException("TimedSemaphore is shut down!");
/*     */     }
/*     */ 
/* 297 */     if (this.task == null) {
/* 298 */       this.task = startTimer();
/*     */     }
/*     */ 
/* 301 */     boolean canPass = false;
/*     */     do {
/* 303 */       canPass = (getLimit() <= 0) || (this.acquireCount < getLimit());
/* 304 */       if (!canPass)
/* 305 */         wait();
/*     */       else
/* 307 */         this.acquireCount += 1;
/*     */     }
/* 309 */     while (!canPass);
/*     */   }
/*     */ 
/*     */   public synchronized int getLastAcquiresPerPeriod()
/*     */   {
/* 323 */     return this.lastCallsPerPeriod;
/*     */   }
/*     */ 
/*     */   public synchronized int getAcquireCount()
/*     */   {
/* 333 */     return this.acquireCount;
/*     */   }
/*     */ 
/*     */   public synchronized int getAvailablePermits()
/*     */   {
/* 348 */     return getLimit() - getAcquireCount();
/*     */   }
/*     */ 
/*     */   public synchronized double getAverageCallsPerPeriod()
/*     */   {
/* 361 */     return this.periodCount == 0L ? 0.0D : this.totalAcquireCount / this.periodCount;
/*     */   }
/*     */ 
/*     */   public long getPeriod()
/*     */   {
/* 373 */     return this.period;
/*     */   }
/*     */ 
/*     */   public TimeUnit getUnit()
/*     */   {
/* 382 */     return this.unit;
/*     */   }
/*     */ 
/*     */   protected ScheduledExecutorService getExecutorService()
/*     */   {
/* 391 */     return this.executorService;
/*     */   }
/*     */ 
/*     */   protected ScheduledFuture<?> startTimer()
/*     */   {
/* 402 */     return getExecutorService().scheduleAtFixedRate(new Runnable() {
/*     */       public void run() {
/* 404 */         TimedSemaphore.this.endOfPeriod();
/*     */       }
/*     */     }
/*     */     , getPeriod(), getPeriod(), getUnit());
/*     */   }
/*     */ 
/*     */   synchronized void endOfPeriod()
/*     */   {
/* 415 */     this.lastCallsPerPeriod = this.acquireCount;
/* 416 */     this.totalAcquireCount += this.acquireCount;
/* 417 */     this.periodCount += 1L;
/* 418 */     this.acquireCount = 0;
/* 419 */     notifyAll();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.TimedSemaphore
 * JD-Core Version:    0.6.2
 */