/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.util.concurrent.ThreadFactory;
/*  4:   */
/* 19:   */public class DefaultThreadFactory
/* 20:   */  implements ThreadFactory
/* 21:   */{
/* 22:   */  public Thread newThread(Runnable runnable)
/* 23:   */  {
/* 24:24 */    return new Thread(runnable);
/* 25:   */  }
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DefaultThreadFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */