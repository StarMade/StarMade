package org.hsqldb.lib;

class HsqlThreadFactory
  implements ThreadFactory
{
  protected ThreadFactory factory;
  
  public HsqlThreadFactory()
  {
    this(null);
  }
  
  public HsqlThreadFactory(ThreadFactory paramThreadFactory)
  {
    setImpl(paramThreadFactory);
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    return this.factory == this ? new Thread(paramRunnable) : this.factory.newThread(paramRunnable);
  }
  
  public synchronized ThreadFactory setImpl(ThreadFactory paramThreadFactory)
  {
    ThreadFactory localThreadFactory = this.factory;
    this.factory = (paramThreadFactory == null ? this : paramThreadFactory);
    return localThreadFactory;
  }
  
  public synchronized ThreadFactory getImpl()
  {
    return this.factory;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.HsqlThreadFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */