/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.security.AccessController;
/*   6:    */import java.security.PrivilegedActionException;
/*   7:    */import java.security.PrivilegedExceptionAction;
/*   8:    */import org.lwjgl.LWJGLException;
/*   9:    */import org.lwjgl.LWJGLUtil;
/*  10:    */
/*  52:    */final class AWTSurfaceLock
/*  53:    */{
/*  54:    */  private static final int WAIT_DELAY_MILLIS = 100;
/*  55:    */  private final ByteBuffer lock_buffer;
/*  56:    */  private boolean firstLockSucceeded;
/*  57:    */  
/*  58:    */  AWTSurfaceLock()
/*  59:    */  {
/*  60: 60 */    this.lock_buffer = createHandle();
/*  61:    */  }
/*  62:    */  
/*  63:    */  private static native ByteBuffer createHandle();
/*  64:    */  
/*  65:    */  public ByteBuffer lockAndGetHandle(Canvas component) throws LWJGLException {
/*  66: 66 */    while (!privilegedLockAndInitHandle(component)) {
/*  67: 67 */      LWJGLUtil.log("Could not get drawing surface info, retrying...");
/*  68:    */      try {
/*  69: 69 */        Thread.sleep(100L);
/*  70:    */      } catch (InterruptedException e) {
/*  71: 71 */        LWJGLUtil.log("Interrupted while retrying: " + e);
/*  72:    */      }
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    return this.lock_buffer;
/*  76:    */  }
/*  77:    */  
/*  82:    */  private boolean privilegedLockAndInitHandle(final Canvas component)
/*  83:    */    throws LWJGLException
/*  84:    */  {
/*  85: 85 */    if (this.firstLockSucceeded) {
/*  86: 86 */      return lockAndInitHandle(this.lock_buffer, component);
/*  87:    */    }
/*  88:    */    try {
/*  89: 89 */      this.firstLockSucceeded = ((Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction()) {
/*  90:    */        public Boolean run() throws LWJGLException {
/*  91: 91 */          return Boolean.valueOf(AWTSurfaceLock.lockAndInitHandle(AWTSurfaceLock.this.lock_buffer, component));
/*  92:    */        }
/*  93: 93 */      }()).booleanValue();
/*  94: 94 */      return this.firstLockSucceeded;
/*  95:    */    } catch (PrivilegedActionException e) {
/*  96: 96 */      throw ((LWJGLException)e.getException());
/*  97:    */    }
/*  98:    */  }
/*  99:    */  
/* 100:    */  private static native boolean lockAndInitHandle(ByteBuffer paramByteBuffer, Canvas paramCanvas) throws LWJGLException;
/* 101:    */  
/* 102:    */  void unlock() throws LWJGLException {
/* 103:103 */    nUnlock(this.lock_buffer);
/* 104:    */  }
/* 105:    */  
/* 106:    */  private static native void nUnlock(ByteBuffer paramByteBuffer)
/* 107:    */    throws LWJGLException;
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AWTSurfaceLock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */