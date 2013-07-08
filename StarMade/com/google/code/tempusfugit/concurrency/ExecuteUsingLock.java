/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.util.concurrent.locks.Lock;
/*  4:   */
/* 20:   */public class ExecuteUsingLock<T, E extends Exception>
/* 21:   */{
/* 22:   */  private final Callable<T, E> callable;
/* 23:   */  
/* 24:   */  private ExecuteUsingLock(Callable<T, E> callable)
/* 25:   */  {
/* 26:26 */    this.callable = callable;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public static <T, E extends Exception> ExecuteUsingLock<T, E> execute(Callable<T, E> callable) {
/* 30:30 */    return new ExecuteUsingLock(callable);
/* 31:   */  }
/* 32:   */  
/* 33:   */  public T using(Lock lock) throws Exception {
/* 34:   */    try {
/* 35:35 */      lock.lock();
/* 36:36 */      return this.callable.call();
/* 37:   */    } finally {
/* 38:38 */      lock.unlock();
/* 39:   */    }
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ExecuteUsingLock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */