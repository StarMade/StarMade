package org.schema.schine.network.udp;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory
  implements ThreadFactory
{
  private String name;
  private boolean daemon;
  private ThreadFactory delegate;
  
  public NamedThreadFactory(String paramString)
  {
    this(paramString, Executors.defaultThreadFactory());
  }
  
  public NamedThreadFactory(String paramString, boolean paramBoolean)
  {
    this(paramString, paramBoolean, Executors.defaultThreadFactory());
  }
  
  public NamedThreadFactory(String paramString, boolean paramBoolean, ThreadFactory paramThreadFactory)
  {
    this.name = paramString;
    this.daemon = paramBoolean;
    this.delegate = paramThreadFactory;
  }
  
  public NamedThreadFactory(String paramString, ThreadFactory paramThreadFactory)
  {
    this(paramString, false, paramThreadFactory);
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    String str = (paramRunnable = this.delegate.newThread(paramRunnable)).getName();
    paramRunnable.setName(this.name + "[" + str + "]");
    paramRunnable.setDaemon(this.daemon);
    return paramRunnable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.NamedThreadFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */