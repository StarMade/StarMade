package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.temporal.Duration;

public final class ThreadUtils
{
  public static void sleep(Duration duration)
  {
    resetInterruptFlagWhen(sleepingIsInterrupted(duration));
  }
  
  private static Interruptible<Void> sleepingIsInterrupted(Duration duration)
  {
    new Interruptible()
    {
      public Void call()
        throws InterruptedException
      {
        Thread.sleep(this.val$duration.inMillis());
        return null;
      }
    };
  }
  
  public static <T> T resetInterruptFlagWhen(Interruptible<T> interruptible)
  {
    try
    {
      return interruptible.call();
    }
    catch (InterruptedException local_e)
    {
      Thread.currentThread().interrupt();
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ThreadUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */