package org.lwjgl.opengl;

import org.lwjgl.Sys;

class Sync
{
  private static final long NANOS_IN_SECOND = 1000000000L;
  private static long nextFrame = 0L;
  private static boolean initialised = false;
  private static RunningAvg sleepDurations = new RunningAvg(10);
  private static RunningAvg yieldDurations = new RunningAvg(10);
  
  public static void sync(int fps)
  {
    if (fps <= 0) {
      return;
    }
    if (!initialised) {
      initialise();
    }
    try
    {
      long local_t1;
      for (long local_t0 = getTime(); nextFrame - local_t0 > sleepDurations.avg(); local_t0 = local_t1)
      {
        Thread.sleep(1L);
        sleepDurations.add((local_t1 = getTime()) - local_t0);
      }
      sleepDurations.dampenForLowResTicker();
      long local_t1;
      for (long local_t0 = getTime(); nextFrame - local_t0 > yieldDurations.avg(); local_t0 = local_t1)
      {
        Thread.yield();
        yieldDurations.add((local_t1 = getTime()) - local_t0);
      }
    }
    catch (InterruptedException local_t0) {}
    nextFrame = Math.max(nextFrame + 1000000000L / fps, getTime());
  }
  
  private static void initialise()
  {
    initialised = true;
    sleepDurations.init(1000000L);
    yieldDurations.init((int)(-(getTime() - getTime()) * 1.333D));
    nextFrame = getTime();
    String osName = System.getProperty("os.name");
    if (osName.startsWith("Win"))
    {
      Thread timerAccuracyThread = (new Runnable()
      {
        public void run()
        {
          try
          {
            Thread.sleep(9223372036854775807L);
          }
          catch (Exception local_e) {}
        }
      });
      timerAccuracyThread.setName("LWJGL Timer");
      timerAccuracyThread.setDaemon(true);
      timerAccuracyThread.start();
    }
  }
  
  private static long getTime()
  {
    return Sys.getTime() * 1000000000L / Sys.getTimerResolution();
  }
  
  private static class RunningAvg
  {
    private final long[] slots;
    private int offset;
    private static final long DAMPEN_THRESHOLD = 10000000L;
    private static final float DAMPEN_FACTOR = 0.9F;
    
    public RunningAvg(int slotCount)
    {
      this.slots = new long[slotCount];
      this.offset = 0;
    }
    
    public void init(long value)
    {
      while (this.offset < this.slots.length) {
        this.slots[(this.offset++)] = value;
      }
    }
    
    public void add(long value)
    {
      this.slots[(this.offset++ % this.slots.length)] = value;
      this.offset %= this.slots.length;
    }
    
    public long avg()
    {
      long sum = 0L;
      for (int local_i = 0; local_i < this.slots.length; local_i++) {
        sum += this.slots[local_i];
      }
      return sum / this.slots.length;
    }
    
    public void dampenForLowResTicker()
    {
      if (avg() > 10000000L) {
        for (int local_i = 0; local_i < this.slots.length; local_i++)
        {
          int tmp27_26 = local_i;
          long[] tmp27_23 = this.slots;
          tmp27_23[tmp27_26] = (((float)tmp27_23[tmp27_26] * 0.9F));
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.Sync
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */