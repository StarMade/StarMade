package org.hsqldb.lib;

public class HsqlTaskQueue
{
  protected Thread taskRunnerThread;
  protected static final Runnable SHUTDOWNTASK = new Runnable()
  {
    public void run() {}
  };
  protected volatile boolean isShutdown;
  protected final HsqlDeque queue = new HsqlDeque();
  protected final TaskRunner taskRunner = new TaskRunner();
  
  public synchronized Thread getTaskRunnerThread()
  {
    return this.taskRunnerThread;
  }
  
  protected synchronized void clearThread()
  {
    try
    {
      this.taskRunnerThread.setContextClassLoader(null);
    }
    catch (Throwable localThrowable) {}
    this.taskRunnerThread = null;
  }
  
  public boolean isShutdown()
  {
    return this.isShutdown;
  }
  
  public synchronized void restart()
  {
    if ((this.taskRunnerThread == null) && (!this.isShutdown))
    {
      this.taskRunnerThread = new Thread(this.taskRunner);
      this.taskRunnerThread.start();
    }
  }
  
  public void execute(Runnable paramRunnable)
    throws RuntimeException
  {
    if (!this.isShutdown)
    {
      synchronized (this.queue)
      {
        this.queue.addLast(paramRunnable);
      }
      restart();
    }
  }
  
  public synchronized void shutdownAfterQueued()
  {
    if (!this.isShutdown) {
      synchronized (this.queue)
      {
        this.queue.addLast(SHUTDOWNTASK);
      }
    }
  }
  
  public synchronized void shutdownAfterCurrent()
  {
    this.isShutdown = true;
    synchronized (this.queue)
    {
      this.queue.clear();
      this.queue.addLast(SHUTDOWNTASK);
    }
  }
  
  public synchronized void shutdownImmediately()
  {
    this.isShutdown = true;
    if (this.taskRunnerThread != null) {
      this.taskRunnerThread.interrupt();
    }
    synchronized (this.queue)
    {
      this.queue.clear();
      this.queue.addLast(SHUTDOWNTASK);
    }
  }
  
  protected class TaskRunner
    implements Runnable
  {
    protected TaskRunner() {}
    
    public void run()
    {
      try
      {
        while (!HsqlTaskQueue.this.isShutdown)
        {
          synchronized (HsqlTaskQueue.this.queue)
          {
            localRunnable = (Runnable)HsqlTaskQueue.this.queue.getFirst();
          }
          if (localRunnable == HsqlTaskQueue.SHUTDOWNTASK)
          {
            HsqlTaskQueue.this.isShutdown = true;
            synchronized (HsqlTaskQueue.this.queue)
            {
              HsqlTaskQueue.this.queue.clear();
            }
            break;
          }
          if (localRunnable == null) {
            break;
          }
          localRunnable.run();
          Runnable localRunnable = null;
        }
      }
      finally
      {
        HsqlTaskQueue.this.clearThread();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.HsqlTaskQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */