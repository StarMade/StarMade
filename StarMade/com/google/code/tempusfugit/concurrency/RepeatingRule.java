/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import org.junit.rules.MethodRule;
/*  4:   */import org.junit.runners.model.FrameworkMethod;
/*  5:   */import org.junit.runners.model.Statement;
/*  6:   */
/* 21:   */public class RepeatingRule
/* 22:   */  implements MethodRule
/* 23:   */{
/* 24:   */  public Statement apply(Statement base, FrameworkMethod method, Object target)
/* 25:   */  {
/* 26:26 */    return new RunRepeatedly(method, base);
/* 27:   */  }
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RepeatingRule
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */