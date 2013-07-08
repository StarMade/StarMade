/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.concurrency.annotations.Repeating;
/*  4:   */import junit.framework.AssertionFailedError;
/*  5:   */import org.junit.runners.model.FrameworkMethod;
/*  6:   */import org.junit.runners.model.Statement;
/*  7:   */
/* 22:   */class RunRepeatedly
/* 23:   */  extends Statement
/* 24:   */{
/* 25:   */  private final FrameworkMethod method;
/* 26:   */  private final Statement statement;
/* 27:   */  
/* 28:   */  RunRepeatedly(FrameworkMethod method, Statement statement)
/* 29:   */  {
/* 30:30 */    this.method = method;
/* 31:31 */    this.statement = statement;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void evaluate() throws Throwable {
/* 35:35 */    if (repeating(this.method)) {
/* 36:36 */      for (int i = 0; i < repetition(this.method); i++) {
/* 37:   */        try {
/* 38:38 */          this.statement.evaluate();
/* 39:   */        } catch (AssertionFailedError e) {
/* 40:40 */          throw new AssertionFailedError(String.format("%s (failed after %d successful attempts)", new Object[] { e.getMessage(), Integer.valueOf(i) }));
/* 41:   */        }
/* 42:   */      }
/* 43:   */    } else
/* 44:44 */      this.statement.evaluate();
/* 45:   */  }
/* 46:   */  
/* 47:   */  private static boolean repeating(FrameworkMethod method) {
/* 48:48 */    return method.getAnnotation(Repeating.class) != null;
/* 49:   */  }
/* 50:   */  
/* 51:   */  private static int repetition(FrameworkMethod method) {
/* 52:52 */    return ((Repeating)method.getAnnotation(Repeating.class)).repetition();
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RunRepeatedly
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */