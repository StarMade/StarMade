/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.List;
/*  5:   */import java.util.concurrent.TimeoutException;
/*  6:   */
/* 22:   */public final class TimeoutExceptionWithResults
/* 23:   */  extends TimeoutException
/* 24:   */{
/* 25:25 */  private List<?> results = new ArrayList();
/* 26:   */  
/* 27:   */  public TimeoutExceptionWithResults(String message) {
/* 28:28 */    super(message);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public <T> TimeoutExceptionWithResults(String message, List<T> results) {
/* 32:32 */    this(message);
/* 33:33 */    this.results = new ArrayList(results);
/* 34:   */  }
/* 35:   */  
/* 36:   */  public <T> TimeoutExceptionWithResults(List<T> results)
/* 37:   */  {
/* 38:38 */    this.results = new ArrayList(results);
/* 39:   */  }
/* 40:   */  
/* 41:   */  public <T> List<T> getResults() {
/* 42:42 */    return this.results;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.TimeoutExceptionWithResults
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */