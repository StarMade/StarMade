package com.google.code.tempusfugit.concurrency;

import java.util.concurrent.locks.Lock;

public class ExecuteUsingLock<T, E extends Exception>
{
  private final Callable<T, E> callable;
  
  private ExecuteUsingLock(Callable<T, E> callable)
  {
    this.callable = callable;
  }
  
  public static <T, E extends Exception> ExecuteUsingLock<T, E> execute(Callable<T, E> callable)
  {
    return new ExecuteUsingLock(callable);
  }
  
  public T using(Lock lock)
    throws Exception
  {
    try
    {
      lock.lock();
      Object localObject1 = this.callable.call();
      return localObject1;
    }
    finally
    {
      lock.unlock();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ExecuteUsingLock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */