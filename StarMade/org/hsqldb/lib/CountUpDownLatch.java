package org.hsqldb.lib;

import java.util.concurrent.CountDownLatch;

public class CountUpDownLatch
{
  volatile CountDownLatch latch = new CountDownLatch(1);
  volatile int count;

  public void await()
    throws InterruptedException
  {
    if (this.count == 0)
      return;
    this.latch.await();
  }

  public void countDown()
  {
    this.count -= 1;
    if (this.count == 0)
      this.latch.countDown();
  }

  public long getCount()
  {
    return this.count;
  }

  public void countUp()
  {
    if (this.latch.getCount() == 0L)
      this.latch = new CountDownLatch(1);
    this.count += 1;
  }

  public void setCount(int paramInt)
  {
    if (paramInt == 0)
    {
      if (this.latch.getCount() != 0L)
        this.latch.countDown();
    }
    else if (this.latch.getCount() == 0L)
      this.latch = new CountDownLatch(1);
    this.count = paramInt;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.CountUpDownLatch
 * JD-Core Version:    0.6.2
 */