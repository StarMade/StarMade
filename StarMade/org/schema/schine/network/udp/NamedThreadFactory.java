/*  1:   */package org.schema.schine.network.udp;
/*  2:   */
/*  3:   */import java.util.concurrent.Executors;
/*  4:   */import java.util.concurrent.ThreadFactory;
/*  5:   */
/*  6:   */public class NamedThreadFactory implements ThreadFactory
/*  7:   */{
/*  8:   */  private String name;
/*  9:   */  private boolean daemon;
/* 10:   */  private ThreadFactory delegate;
/* 11:   */  
/* 12:   */  public NamedThreadFactory(String paramString)
/* 13:   */  {
/* 14:14 */    this(paramString, Executors.defaultThreadFactory());
/* 15:   */  }
/* 16:   */  
/* 17:   */  public NamedThreadFactory(String paramString, boolean paramBoolean)
/* 18:   */  {
/* 19:19 */    this(paramString, paramBoolean, Executors.defaultThreadFactory());
/* 20:   */  }
/* 21:   */  
/* 22:   */  public NamedThreadFactory(String paramString, boolean paramBoolean, ThreadFactory paramThreadFactory)
/* 23:   */  {
/* 24:24 */    this.name = paramString;
/* 25:25 */    this.daemon = paramBoolean;
/* 26:26 */    this.delegate = paramThreadFactory;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public NamedThreadFactory(String paramString, ThreadFactory paramThreadFactory)
/* 30:   */  {
/* 31:31 */    this(paramString, false, paramThreadFactory);
/* 32:   */  }
/* 33:   */  
/* 36:   */  public Thread newThread(Runnable paramRunnable)
/* 37:   */  {
/* 38:38 */    String str = (paramRunnable = this.delegate.newThread(paramRunnable)).getName();
/* 39:39 */    paramRunnable.setName(this.name + "[" + str + "]");
/* 40:40 */    paramRunnable.setDaemon(this.daemon);
/* 41:41 */    return paramRunnable;
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.NamedThreadFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */