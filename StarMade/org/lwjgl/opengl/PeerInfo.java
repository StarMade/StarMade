/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.LWJGLException;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ 
/*    */ abstract class PeerInfo
/*    */ {
/*    */   private final ByteBuffer handle;
/*    */   private Thread locking_thread;
/*    */   private int lock_count;
/*    */ 
/*    */   protected PeerInfo(ByteBuffer handle)
/*    */   {
/* 51 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */   private void lockAndInitHandle() throws LWJGLException {
/* 55 */     doLockAndInitHandle();
/*    */   }
/*    */ 
/*    */   public final synchronized void unlock() throws LWJGLException {
/* 59 */     if (this.lock_count <= 0)
/* 60 */       throw new IllegalStateException("PeerInfo not locked!");
/* 61 */     if (Thread.currentThread() != this.locking_thread)
/* 62 */       throw new IllegalStateException("PeerInfo already locked by " + this.locking_thread);
/* 63 */     this.lock_count -= 1;
/* 64 */     if (this.lock_count == 0) {
/* 65 */       doUnlock();
/* 66 */       this.locking_thread = null;
/* 67 */       notify();
/*    */     }
/*    */   }
/*    */ 
/*    */   protected abstract void doLockAndInitHandle() throws LWJGLException;
/*    */ 
/*    */   protected abstract void doUnlock() throws LWJGLException;
/*    */ 
/* 75 */   public final synchronized ByteBuffer lockAndGetHandle() throws LWJGLException { Thread this_thread = Thread.currentThread();
/* 76 */     while ((this.locking_thread != null) && (this.locking_thread != this_thread)) {
/*    */       try {
/* 78 */         wait();
/*    */       } catch (InterruptedException e) {
/* 80 */         LWJGLUtil.log("Interrupted while waiting for PeerInfo lock: " + e);
/*    */       }
/*    */     }
/* 83 */     if (this.lock_count == 0) {
/* 84 */       this.locking_thread = this_thread;
/* 85 */       doLockAndInitHandle();
/*    */     }
/* 87 */     this.lock_count += 1;
/* 88 */     return getHandle(); }
/*    */ 
/*    */   protected final ByteBuffer getHandle()
/*    */   {
/* 92 */     return this.handle;
/*    */   }
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.PeerInfo
 * JD-Core Version:    0.6.2
 */