/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.util.concurrent.Callable;
/*  4:   */
/* 18:   */public class CallableAdapter
/* 19:   */{
/* 20:   */  public static Runnable runnable(Callable callable)
/* 21:   */  {
/* 22:22 */    new Runnable() {
/* 23:   */      public void run() {
/* 24:   */        try {
/* 25:25 */          this.val$callable.call();
/* 26:   */        } catch (Exception e) {
/* 27:27 */          throw new RuntimeException(e);
/* 28:   */        }
/* 29:   */      }
/* 30:   */    };
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.CallableAdapter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */