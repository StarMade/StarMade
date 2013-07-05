/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.temporal.Clock;
/*    */ import com.google.code.tempusfugit.temporal.Duration;
/*    */ import com.google.code.tempusfugit.temporal.RealClock;
/*    */ import com.google.code.tempusfugit.temporal.StopWatch;
/*    */ import com.google.code.tempusfugit.temporal.Timeout;
/*    */ import com.google.code.tempusfugit.temporal.WaitFor;
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ 
/*    */ public final class Interrupter
/*    */ {
/* 33 */   private static final AtomicLong counter = new AtomicLong(0L);
/*    */   private final Thread threadToInterrupt;
/*    */   private Thread interrupterThread;
/* 37 */   private Clock time = RealClock.now();
/*    */ 
/*    */   private Interrupter(Thread threadToInterrupt) {
/* 40 */     this.threadToInterrupt = threadToInterrupt;
/*    */   }
/*    */ 
/*    */   public static Interrupter interrupt(Thread thread) {
/* 44 */     return new Interrupter(thread);
/*    */   }
/*    */ 
/*    */   Interrupter using(Clock time) {
/* 48 */     if (this.interrupterThread != null)
/* 49 */       throw new IllegalStateException("Controlling time after events have been put in motion will have no affect");
/* 50 */     this.time = time;
/* 51 */     return this;
/*    */   }
/*    */ 
/*    */   public Interrupter after(Duration duration) {
/* 55 */     final Timeout timeout = Timeout.timeout(duration, startStopWatch());
/* 56 */     this.interrupterThread = new Thread(new Runnable() {
/*    */       public void run() {
/*    */         try {
/* 59 */           WaitFor.waitUntil(timeout);
/* 60 */           if (!Interrupter.this.interrupterThread.isInterrupted())
/* 61 */             Interrupter.this.threadToInterrupt.interrupt();
/*    */         }
/*    */         catch (InterruptedException e) {
/* 64 */           Thread.currentThread().interrupt();
/*    */         }
/*    */       }
/*    */     }
/*    */     , "Interrupter-Thread-" + counter.incrementAndGet());
/*    */ 
/* 68 */     this.interrupterThread.start();
/* 69 */     return this;
/*    */   }
/*    */ 
/*    */   public void cancel() {
/* 73 */     if (this.interrupterThread.isAlive())
/* 74 */       this.interrupterThread.interrupt();
/*    */   }
/*    */ 
/*    */   private StopWatch startStopWatch() {
/* 78 */     return StopWatch.start(this.time);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.Interrupter
 * JD-Core Version:    0.6.2
 */