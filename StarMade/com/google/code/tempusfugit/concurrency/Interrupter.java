package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.temporal.Clock;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.RealClock;
import com.google.code.tempusfugit.temporal.StopWatch;
import com.google.code.tempusfugit.temporal.Timeout;
import com.google.code.tempusfugit.temporal.WaitFor;
import java.util.concurrent.atomic.AtomicLong;

public final class Interrupter
{
  private static final AtomicLong counter = new AtomicLong(0L);
  private final Thread threadToInterrupt;
  private Thread interrupterThread;
  private Clock time = RealClock.now();
  
  private Interrupter(Thread threadToInterrupt)
  {
    this.threadToInterrupt = threadToInterrupt;
  }
  
  public static Interrupter interrupt(Thread thread)
  {
    return new Interrupter(thread);
  }
  
  Interrupter using(Clock time)
  {
    if (this.interrupterThread != null) {
      throw new IllegalStateException("Controlling time after events have been put in motion will have no affect");
    }
    this.time = time;
    return this;
  }
  
  public Interrupter after(Duration duration)
  {
    final Timeout timeout = Timeout.timeout(duration, startStopWatch());
    this.interrupterThread = (new Runnable()
    {
      public void run()
      {
        try
        {
          WaitFor.waitUntil(timeout);
          if (!Interrupter.this.interrupterThread.isInterrupted()) {
            Interrupter.this.threadToInterrupt.interrupt();
          }
        }
        catch (InterruptedException local_e)
        {
          Thread.currentThread().interrupt();
        }
      }
    }, "Interrupter-Thread-" + counter.incrementAndGet());
    this.interrupterThread.start();
    return this;
  }
  
  public void cancel()
  {
    if (this.interrupterThread.isAlive()) {
      this.interrupterThread.interrupt();
    }
  }
  
  private StopWatch startStopWatch()
  {
    return StopWatch.start(this.time);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.Interrupter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */