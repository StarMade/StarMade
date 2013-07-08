package com.google.code.tempusfugit.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public final class TimeoutExceptionWithResults
  extends TimeoutException
{
  private List<?> results = new ArrayList();
  
  public TimeoutExceptionWithResults(String message)
  {
    super(message);
  }
  
  public <T> TimeoutExceptionWithResults(String message, List<T> results)
  {
    this(message);
    this.results = new ArrayList(results);
  }
  
  public <T> TimeoutExceptionWithResults(List<T> results)
  {
    this.results = new ArrayList(results);
  }
  
  public <T> List<T> getResults()
  {
    return this.results;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.TimeoutExceptionWithResults
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */