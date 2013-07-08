package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

abstract class PeerInfo
{
  private final ByteBuffer handle;
  private Thread locking_thread;
  private int lock_count;
  
  protected PeerInfo(ByteBuffer handle)
  {
    this.handle = handle;
  }
  
  private void lockAndInitHandle()
    throws LWJGLException
  {
    doLockAndInitHandle();
  }
  
  public final synchronized void unlock()
    throws LWJGLException
  {
    if (this.lock_count <= 0) {
      throw new IllegalStateException("PeerInfo not locked!");
    }
    if (Thread.currentThread() != this.locking_thread) {
      throw new IllegalStateException("PeerInfo already locked by " + this.locking_thread);
    }
    this.lock_count -= 1;
    if (this.lock_count == 0)
    {
      doUnlock();
      this.locking_thread = null;
      notify();
    }
  }
  
  protected abstract void doLockAndInitHandle()
    throws LWJGLException;
  
  protected abstract void doUnlock()
    throws LWJGLException;
  
  public final synchronized ByteBuffer lockAndGetHandle()
    throws LWJGLException
  {
    Thread this_thread = Thread.currentThread();
    while ((this.locking_thread != null) && (this.locking_thread != this_thread)) {
      try
      {
        wait();
      }
      catch (InterruptedException local_e)
      {
        LWJGLUtil.log("Interrupted while waiting for PeerInfo lock: " + local_e);
      }
    }
    if (this.lock_count == 0)
    {
      this.locking_thread = this_thread;
      doLockAndInitHandle();
    }
    this.lock_count += 1;
    return getHandle();
  }
  
  protected final ByteBuffer getHandle()
  {
    return this.handle;
  }
  
  public void destroy() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.PeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */