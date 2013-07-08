/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.temporal.Duration;
/*  4:   */
/* 20:   */public final class ThreadUtils
/* 21:   */{
/* 22:   */  public static void sleep(Duration duration)
/* 23:   */  {
/* 24:24 */    resetInterruptFlagWhen(sleepingIsInterrupted(duration));
/* 25:   */  }
/* 26:   */  
/* 27:   */  private static Interruptible<Void> sleepingIsInterrupted(Duration duration) {
/* 28:28 */    new Interruptible() {
/* 29:   */      public Void call() throws InterruptedException {
/* 30:30 */        Thread.sleep(this.val$duration.inMillis());
/* 31:31 */        return null;
/* 32:   */      }
/* 33:   */    };
/* 34:   */  }
/* 35:   */  
/* 36:   */  public static <T> T resetInterruptFlagWhen(Interruptible<T> interruptible) {
/* 37:   */    try {
/* 38:38 */      return interruptible.call();
/* 39:   */    } catch (InterruptedException e) {
/* 40:40 */      Thread.currentThread().interrupt();
/* 41:   */    }
/* 42:42 */    return null;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ThreadUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */