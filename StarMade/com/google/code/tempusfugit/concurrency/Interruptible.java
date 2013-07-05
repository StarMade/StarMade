package com.google.code.tempusfugit.concurrency;

import java.util.concurrent.Callable;

public abstract interface Interruptible<T> extends Callable<T>
{
  public abstract T call()
    throws InterruptedException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.Interruptible
 * JD-Core Version:    0.6.2
 */