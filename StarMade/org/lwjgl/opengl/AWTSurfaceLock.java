package org.lwjgl.opengl;

import java.awt.Canvas;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

final class AWTSurfaceLock
{
  private static final int WAIT_DELAY_MILLIS = 100;
  private final ByteBuffer lock_buffer = createHandle();
  private boolean firstLockSucceeded;
  
  private static native ByteBuffer createHandle();
  
  public ByteBuffer lockAndGetHandle(Canvas component)
    throws LWJGLException
  {
    while (!privilegedLockAndInitHandle(component))
    {
      LWJGLUtil.log("Could not get drawing surface info, retrying...");
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException local_e)
      {
        LWJGLUtil.log("Interrupted while retrying: " + local_e);
      }
    }
    return this.lock_buffer;
  }
  
  private boolean privilegedLockAndInitHandle(final Canvas component)
    throws LWJGLException
  {
    if (this.firstLockSucceeded) {
      return lockAndInitHandle(this.lock_buffer, component);
    }
    try
    {
      this.firstLockSucceeded = ((Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction())
      {
        public Boolean run()
          throws LWJGLException
        {
          return Boolean.valueOf(AWTSurfaceLock.lockAndInitHandle(AWTSurfaceLock.this.lock_buffer, component));
        }
      }()).booleanValue();
      return this.firstLockSucceeded;
    }
    catch (PrivilegedActionException local_e)
    {
      throw ((LWJGLException)local_e.getException());
    }
  }
  
  private static native boolean lockAndInitHandle(ByteBuffer paramByteBuffer, Canvas paramCanvas)
    throws LWJGLException;
  
  void unlock()
    throws LWJGLException
  {
    nUnlock(this.lock_buffer);
  }
  
  private static native void nUnlock(ByteBuffer paramByteBuffer)
    throws LWJGLException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.AWTSurfaceLock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */