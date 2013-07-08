package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import junit.framework.AssertionFailedError;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

class RunRepeatedly
  extends Statement
{
  private final FrameworkMethod method;
  private final Statement statement;
  
  RunRepeatedly(FrameworkMethod method, Statement statement)
  {
    this.method = method;
    this.statement = statement;
  }
  
  public void evaluate()
    throws Throwable
  {
    if (repeating(this.method)) {
      for (int local_i = 0; local_i < repetition(this.method); local_i++) {
        try
        {
          this.statement.evaluate();
        }
        catch (AssertionFailedError local_e)
        {
          throw new AssertionFailedError(String.format("%s (failed after %d successful attempts)", new Object[] { local_e.getMessage(), Integer.valueOf(local_i) }));
        }
      }
    } else {
      this.statement.evaluate();
    }
  }
  
  private static boolean repeating(FrameworkMethod method)
  {
    return method.getAnnotation(Repeating.class) != null;
  }
  
  private static int repetition(FrameworkMethod method)
  {
    return ((Repeating)method.getAnnotation(Repeating.class)).repetition();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RunRepeatedly
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */