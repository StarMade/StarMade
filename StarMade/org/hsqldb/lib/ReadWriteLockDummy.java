package org.hsqldb.lib;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockDummy
  implements ReadWriteLock
{
  public Lock readLock()
  {
    return new LockDummy();
  }
  
  public Lock writeLock()
  {
    return new LockDummy();
  }
  
  public static class LockDummy
    implements Lock
  {
    public void lock() {}
    
    public void lockInterruptibly()
      throws InterruptedException
    {}
    
    public boolean tryLock()
    {
      return false;
    }
    
    public boolean tryLock(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      return false;
    }
    
    public void unlock() {}
    
    public Condition newCondition()
    {
      return null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.ReadWriteLockDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */