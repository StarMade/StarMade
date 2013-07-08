package com.google.code.tempusfugit.concurrency;

import java.util.concurrent.Callable;

public class CallableAdapter
{
  public static Runnable runnable(Callable callable)
  {
    new Runnable()
    {
      public void run()
      {
        try
        {
          this.val$callable.call();
        }
        catch (Exception local_e)
        {
          throw new RuntimeException(local_e);
        }
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.CallableAdapter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */