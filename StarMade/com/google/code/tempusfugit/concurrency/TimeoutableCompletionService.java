package com.google.code.tempusfugit.concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract interface TimeoutableCompletionService
{
  public abstract <T> List<T> submit(List<? extends Callable<T>> paramList)
    throws ExecutionException, TimeoutException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.TimeoutableCompletionService
 * JD-Core Version:    0.6.2
 */