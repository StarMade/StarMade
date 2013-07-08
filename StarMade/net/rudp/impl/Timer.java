package net.rudp.impl;

public class Timer
  extends Thread
{
  private Runnable _task;
  private long _delay;
  private long _period;
  private boolean _canceled;
  private boolean _scheduled;
  private boolean _reset;
  private boolean _stopped;
  private Object _lock = new Object();
  
  public Timer(String paramString, Runnable paramRunnable)
  {
    super(paramString);
    setDaemon(true);
    this._task = paramRunnable;
    this._delay = 0L;
    this._period = 0L;
    start();
  }
  
  public void run()
  {
    while (!this._stopped)
    {
      synchronized (this)
      {
        while ((!this._scheduled) && (!this._stopped)) {
          try
          {
            wait();
          }
          catch (InterruptedException localInterruptedException1)
          {
            localInterruptedException1.printStackTrace();
          }
        }
        if (this._stopped) {
          break;
        }
      }
      synchronized (this._lock)
      {
        this._reset = false;
        this._canceled = false;
        if (this._delay > 0L) {
          try
          {
            this._lock.wait(this._delay);
          }
          catch (InterruptedException localInterruptedException2)
          {
            localInterruptedException2.printStackTrace();
          }
        }
        if (this._canceled) {
          continue;
        }
      }
      if (!this._reset) {
        this._task.run();
      }
      if (this._period > 0L) {
        for (;;)
        {
          synchronized (this._lock)
          {
            this._reset = false;
            try
            {
              this._lock.wait(this._period);
            }
            catch (InterruptedException localInterruptedException3)
            {
              localInterruptedException3.printStackTrace();
            }
            if (this._canceled) {
              break;
            }
            if (this._reset) {
              continue;
            }
          }
          this._task.run();
        }
      }
    }
  }
  
  public synchronized void schedule(long paramLong)
  {
    schedule(paramLong, 0L);
  }
  
  public synchronized void schedule(long paramLong1, long paramLong2)
  {
    this._delay = paramLong1;
    this._period = paramLong2;
    if (this._scheduled) {
      throw new IllegalStateException("already scheduled");
    }
    this._scheduled = true;
    notify();
    synchronized (this._lock)
    {
      this._lock.notify();
    }
  }
  
  public synchronized boolean isScheduled()
  {
    return this._scheduled;
  }
  
  public synchronized boolean isIdle()
  {
    return !isScheduled();
  }
  
  public synchronized void reset()
  {
    synchronized (this._lock)
    {
      this._reset = true;
      this._lock.notify();
    }
  }
  
  public synchronized void cancel()
  {
    this._scheduled = false;
    synchronized (this._lock)
    {
      this._canceled = true;
      this._lock.notify();
    }
  }
  
  public synchronized void destroy()
  {
    cancel();
    this._stopped = true;
    notify();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.Timer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */