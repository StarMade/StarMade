/*     */ package org.apache.commons.lang3.time;
/*     */ 
/*     */ public class StopWatch
/*     */ {
/*     */   private static final long NANO_2_MILLIS = 1000000L;
/*     */   private static final int STATE_UNSTARTED = 0;
/*     */   private static final int STATE_RUNNING = 1;
/*     */   private static final int STATE_STOPPED = 2;
/*     */   private static final int STATE_SUSPENDED = 3;
/*     */   private static final int STATE_UNSPLIT = 10;
/*     */   private static final int STATE_SPLIT = 11;
/*  79 */   private int runningState = 0;
/*     */ 
/*  84 */   private int splitState = 10;
/*     */   private long startTime;
/*     */   private long startTimeMillis;
/*     */   private long stopTime;
/*     */ 
/*     */   public void start()
/*     */   {
/* 125 */     if (this.runningState == 2) {
/* 126 */       throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
/*     */     }
/* 128 */     if (this.runningState != 0) {
/* 129 */       throw new IllegalStateException("Stopwatch already started. ");
/*     */     }
/* 131 */     this.startTime = System.nanoTime();
/* 132 */     this.startTimeMillis = System.currentTimeMillis();
/* 133 */     this.runningState = 1;
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 149 */     if ((this.runningState != 1) && (this.runningState != 3)) {
/* 150 */       throw new IllegalStateException("Stopwatch is not running. ");
/*     */     }
/* 152 */     if (this.runningState == 1) {
/* 153 */       this.stopTime = System.nanoTime();
/*     */     }
/* 155 */     this.runningState = 2;
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 168 */     this.runningState = 0;
/* 169 */     this.splitState = 10;
/*     */   }
/*     */ 
/*     */   public void split()
/*     */   {
/* 186 */     if (this.runningState != 1) {
/* 187 */       throw new IllegalStateException("Stopwatch is not running. ");
/*     */     }
/* 189 */     this.stopTime = System.nanoTime();
/* 190 */     this.splitState = 11;
/*     */   }
/*     */ 
/*     */   public void unsplit()
/*     */   {
/* 207 */     if (this.splitState != 11) {
/* 208 */       throw new IllegalStateException("Stopwatch has not been split. ");
/*     */     }
/* 210 */     this.splitState = 10;
/*     */   }
/*     */ 
/*     */   public void suspend()
/*     */   {
/* 227 */     if (this.runningState != 1) {
/* 228 */       throw new IllegalStateException("Stopwatch must be running to suspend. ");
/*     */     }
/* 230 */     this.stopTime = System.nanoTime();
/* 231 */     this.runningState = 3;
/*     */   }
/*     */ 
/*     */   public void resume()
/*     */   {
/* 248 */     if (this.runningState != 3) {
/* 249 */       throw new IllegalStateException("Stopwatch must be suspended to resume. ");
/*     */     }
/* 251 */     this.startTime += System.nanoTime() - this.stopTime;
/* 252 */     this.runningState = 1;
/*     */   }
/*     */ 
/*     */   public long getTime()
/*     */   {
/* 268 */     return getNanoTime() / 1000000L;
/*     */   }
/*     */ 
/*     */   public long getNanoTime()
/*     */   {
/* 284 */     if ((this.runningState == 2) || (this.runningState == 3))
/* 285 */       return this.stopTime - this.startTime;
/* 286 */     if (this.runningState == 0)
/* 287 */       return 0L;
/* 288 */     if (this.runningState == 1) {
/* 289 */       return System.nanoTime() - this.startTime;
/*     */     }
/* 291 */     throw new RuntimeException("Illegal running state has occured. ");
/*     */   }
/*     */ 
/*     */   public long getSplitTime()
/*     */   {
/* 310 */     return getSplitNanoTime() / 1000000L;
/*     */   }
/*     */ 
/*     */   public long getSplitNanoTime()
/*     */   {
/* 328 */     if (this.splitState != 11) {
/* 329 */       throw new IllegalStateException("Stopwatch must be split to get the split time. ");
/*     */     }
/* 331 */     return this.stopTime - this.startTime;
/*     */   }
/*     */ 
/*     */   public long getStartTime()
/*     */   {
/* 343 */     if (this.runningState == 0) {
/* 344 */       throw new IllegalStateException("Stopwatch has not been started");
/*     */     }
/*     */ 
/* 347 */     return this.startTimeMillis;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 363 */     return DurationFormatUtils.formatDurationHMS(getTime());
/*     */   }
/*     */ 
/*     */   public String toSplitString()
/*     */   {
/* 379 */     return DurationFormatUtils.formatDurationHMS(getSplitTime());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.StopWatch
 * JD-Core Version:    0.6.2
 */