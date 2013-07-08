package com.google.code.tempusfugit.concurrency;

import java.util.concurrent.Callable;

public abstract interface Interruptible<T>
  extends Callable<T>
{
  public abstract T call()
    throws InterruptedException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.Interruptible
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */