/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.temporal.Clock;
/*  4:   */import com.google.code.tempusfugit.temporal.Duration;
/*  5:   */import com.google.code.tempusfugit.temporal.RealClock;
/*  6:   */import com.google.code.tempusfugit.temporal.StopWatch;
/*  7:   */import com.google.code.tempusfugit.temporal.Timeout;
/*  8:   */import com.google.code.tempusfugit.temporal.WaitFor;
/*  9:   */import java.util.concurrent.atomic.AtomicLong;
/* 10:   */
/* 31:   */public final class Interrupter
/* 32:   */{
/* 33:33 */  private static final AtomicLong counter = new AtomicLong(0L);
/* 34:   */  
/* 35:   */  private final Thread threadToInterrupt;
/* 36:   */  private Thread interrupterThread;
/* 37:37 */  private Clock time = RealClock.now();
/* 38:   */  
/* 39:   */  private Interrupter(Thread threadToInterrupt) {
/* 40:40 */    this.threadToInterrupt = threadToInterrupt;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public static Interrupter interrupt(Thread thread) {
/* 44:44 */    return new Interrupter(thread);
/* 45:   */  }
/* 46:   */  
/* 47:   */  Interrupter using(Clock time) {
/* 48:48 */    if (this.interrupterThread != null)
/* 49:49 */      throw new IllegalStateException("Controlling time after events have been put in motion will have no affect");
/* 50:50 */    this.time = time;
/* 51:51 */    return this;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public Interrupter after(Duration duration) {
/* 55:55 */    final Timeout timeout = Timeout.timeout(duration, startStopWatch());
/* 56:56 */    this.interrupterThread = (new Runnable() {
/* 57:   */      public void run() {
/* 58:   */        try {
/* 59:59 */          WaitFor.waitUntil(timeout);
/* 60:60 */          if (!Interrupter.this.interrupterThread.isInterrupted()) {
/* 61:61 */            Interrupter.this.threadToInterrupt.interrupt();
/* 62:   */          }
/* 63:   */        } catch (InterruptedException e) {
/* 64:64 */          Thread.currentThread().interrupt(); } } } , "Interrupter-Thread-" + counter.incrementAndGet());
/* 65:   */    
/* 68:68 */    this.interrupterThread.start();
/* 69:69 */    return this;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public void cancel() {
/* 73:73 */    if (this.interrupterThread.isAlive())
/* 74:74 */      this.interrupterThread.interrupt();
/* 75:   */  }
/* 76:   */  
/* 77:   */  private StopWatch startStopWatch() {
/* 78:78 */    return StopWatch.start(this.time);
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.Interrupter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */