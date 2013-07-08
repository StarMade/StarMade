package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class IntermittentTestRunner
  extends BlockJUnit4ClassRunner
{
  private final Class<?> type;
  
  public IntermittentTestRunner(Class<?> type)
    throws InitializationError
  {
    super(type);
    this.type = type;
  }
  
  protected void runChild(FrameworkMethod method, RunNotifier notifier)
  {
    for (int local_i = 0; local_i < repeatCount(method); local_i++) {
      super.runChild(method, notifier);
    }
  }
  
  private int repeatCount(FrameworkMethod method)
  {
    if (intermittent(this.type)) {
      return repetition(this.type);
    }
    if (intermittent(method)) {
      return repetition(method);
    }
    return 1;
  }
  
  private static boolean intermittent(FrameworkMethod method)
  {
    return method.getAnnotation(Intermittent.class) != null;
  }
  
  private static boolean intermittent(Class<?> type)
  {
    return type.getAnnotation(Intermittent.class) != null;
  }
  
  private static int repetition(FrameworkMethod method)
  {
    return ((Intermittent)method.getAnnotation(Intermittent.class)).repetition();
  }
  
  private static int repetition(Class<?> type)
  {
    return ((Intermittent)type.getAnnotation(Intermittent.class)).repetition();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.IntermittentTestRunner
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */