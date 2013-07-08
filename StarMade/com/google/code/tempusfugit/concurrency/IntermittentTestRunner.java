/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
/*  4:   */import org.junit.runner.notification.RunNotifier;
/*  5:   */import org.junit.runners.BlockJUnit4ClassRunner;
/*  6:   */import org.junit.runners.model.FrameworkMethod;
/*  7:   */import org.junit.runners.model.InitializationError;
/*  8:   */
/* 21:   */public class IntermittentTestRunner
/* 22:   */  extends BlockJUnit4ClassRunner
/* 23:   */{
/* 24:   */  private final Class<?> type;
/* 25:   */  
/* 26:   */  public IntermittentTestRunner(Class<?> type)
/* 27:   */    throws InitializationError
/* 28:   */  {
/* 29:29 */    super(type);
/* 30:30 */    this.type = type;
/* 31:   */  }
/* 32:   */  
/* 33:   */  protected void runChild(FrameworkMethod method, RunNotifier notifier)
/* 34:   */  {
/* 35:35 */    for (int i = 0; i < repeatCount(method); i++)
/* 36:36 */      super.runChild(method, notifier);
/* 37:   */  }
/* 38:   */  
/* 39:   */  private int repeatCount(FrameworkMethod method) {
/* 40:40 */    if (intermittent(this.type))
/* 41:41 */      return repetition(this.type);
/* 42:42 */    if (intermittent(method))
/* 43:43 */      return repetition(method);
/* 44:44 */    return 1;
/* 45:   */  }
/* 46:   */  
/* 47:   */  private static boolean intermittent(FrameworkMethod method) {
/* 48:48 */    return method.getAnnotation(Intermittent.class) != null;
/* 49:   */  }
/* 50:   */  
/* 51:   */  private static boolean intermittent(Class<?> type) {
/* 52:52 */    return type.getAnnotation(Intermittent.class) != null;
/* 53:   */  }
/* 54:   */  
/* 55:   */  private static int repetition(FrameworkMethod method) {
/* 56:56 */    return ((Intermittent)method.getAnnotation(Intermittent.class)).repetition();
/* 57:   */  }
/* 58:   */  
/* 59:   */  private static int repetition(Class<?> type) {
/* 60:60 */    return ((Intermittent)type.getAnnotation(Intermittent.class)).repetition();
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.IntermittentTestRunner
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */