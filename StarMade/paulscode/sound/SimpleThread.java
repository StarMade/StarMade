package paulscode.sound;

public class SimpleThread
  extends Thread
{
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  private boolean alive = true;
  private boolean kill = false;
  
  protected void cleanup()
  {
    kill(true, true);
    alive(true, false);
  }
  
  public void run()
  {
    cleanup();
  }
  
  public void restart()
  {
    new Thread()
    {
      public void run()
      {
        SimpleThread.this.rerun();
      }
    }.start();
  }
  
  private void rerun()
  {
    kill(true, true);
    while (alive(false, false)) {
      snooze(100L);
    }
    alive(true, true);
    kill(true, false);
    run();
  }
  
  public boolean alive()
  {
    return alive(false, false);
  }
  
  public void kill()
  {
    kill(true, true);
  }
  
  protected boolean dying()
  {
    return kill(false, false);
  }
  
  private synchronized boolean alive(boolean action, boolean value)
  {
    if (action == true) {
      this.alive = value;
    }
    return this.alive;
  }
  
  private synchronized boolean kill(boolean action, boolean value)
  {
    if (action == true) {
      this.kill = value;
    }
    return this.kill;
  }
  
  protected void snooze(long milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch (InterruptedException local_e) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.SimpleThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */