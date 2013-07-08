/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import java.util.concurrent.Callable;
/*   4:    */import java.util.concurrent.ExecutorService;
/*   5:    */
/*  73:    */public class CallableBackgroundInitializer<T>
/*  74:    */  extends BackgroundInitializer<T>
/*  75:    */{
/*  76:    */  private final Callable<T> callable;
/*  77:    */  
/*  78:    */  public CallableBackgroundInitializer(Callable<T> call)
/*  79:    */  {
/*  80: 80 */    checkCallable(call);
/*  81: 81 */    this.callable = call;
/*  82:    */  }
/*  83:    */  
/*  94:    */  public CallableBackgroundInitializer(Callable<T> call, ExecutorService exec)
/*  95:    */  {
/*  96: 96 */    super(exec);
/*  97: 97 */    checkCallable(call);
/*  98: 98 */    this.callable = call;
/*  99:    */  }
/* 100:    */  
/* 108:    */  protected T initialize()
/* 109:    */    throws Exception
/* 110:    */  {
/* 111:111 */    return this.callable.call();
/* 112:    */  }
/* 113:    */  
/* 120:    */  private void checkCallable(Callable<T> call)
/* 121:    */  {
/* 122:122 */    if (call == null) {
/* 123:123 */      throw new IllegalArgumentException("Callable must not be null!");
/* 124:    */    }
/* 125:    */  }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.CallableBackgroundInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */