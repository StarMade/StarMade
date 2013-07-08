/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */import org.lwjgl.LWJGLUtil;
/*  6:   */
/* 43:   */abstract class PeerInfo
/* 44:   */{
/* 45:   */  private final ByteBuffer handle;
/* 46:   */  private Thread locking_thread;
/* 47:   */  private int lock_count;
/* 48:   */  
/* 49:   */  protected PeerInfo(ByteBuffer handle)
/* 50:   */  {
/* 51:51 */    this.handle = handle;
/* 52:   */  }
/* 53:   */  
/* 54:   */  private void lockAndInitHandle() throws LWJGLException {
/* 55:55 */    doLockAndInitHandle();
/* 56:   */  }
/* 57:   */  
/* 58:   */  public final synchronized void unlock() throws LWJGLException {
/* 59:59 */    if (this.lock_count <= 0)
/* 60:60 */      throw new IllegalStateException("PeerInfo not locked!");
/* 61:61 */    if (Thread.currentThread() != this.locking_thread)
/* 62:62 */      throw new IllegalStateException("PeerInfo already locked by " + this.locking_thread);
/* 63:63 */    this.lock_count -= 1;
/* 64:64 */    if (this.lock_count == 0) {
/* 65:65 */      doUnlock();
/* 66:66 */      this.locking_thread = null;
/* 67:67 */      notify();
/* 68:   */    }
/* 69:   */  }
/* 70:   */  
/* 71:   */  protected abstract void doLockAndInitHandle() throws LWJGLException;
/* 72:   */  
/* 73:   */  protected abstract void doUnlock() throws LWJGLException;
/* 74:   */  
/* 75:75 */  public final synchronized ByteBuffer lockAndGetHandle() throws LWJGLException { Thread this_thread = Thread.currentThread();
/* 76:76 */    while ((this.locking_thread != null) && (this.locking_thread != this_thread)) {
/* 77:   */      try {
/* 78:78 */        wait();
/* 79:   */      } catch (InterruptedException e) {
/* 80:80 */        LWJGLUtil.log("Interrupted while waiting for PeerInfo lock: " + e);
/* 81:   */      }
/* 82:   */    }
/* 83:83 */    if (this.lock_count == 0) {
/* 84:84 */      this.locking_thread = this_thread;
/* 85:85 */      doLockAndInitHandle();
/* 86:   */    }
/* 87:87 */    this.lock_count += 1;
/* 88:88 */    return getHandle();
/* 89:   */  }
/* 90:   */  
/* 91:   */  protected final ByteBuffer getHandle() {
/* 92:92 */    return this.handle;
/* 93:   */  }
/* 94:   */  
/* 95:   */  public void destroy() {}
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.PeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */