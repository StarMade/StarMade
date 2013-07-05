/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ final class AWTSurfaceLock
/*     */ {
/*     */   private static final int WAIT_DELAY_MILLIS = 100;
/*     */   private final ByteBuffer lock_buffer;
/*     */   private boolean firstLockSucceeded;
/*     */ 
/*     */   AWTSurfaceLock()
/*     */   {
/*  60 */     this.lock_buffer = createHandle();
/*     */   }
/*     */ 
/*     */   private static native ByteBuffer createHandle();
/*     */ 
/*     */   public ByteBuffer lockAndGetHandle(Canvas component) throws LWJGLException {
/*  66 */     while (!privilegedLockAndInitHandle(component)) {
/*  67 */       LWJGLUtil.log("Could not get drawing surface info, retrying...");
/*     */       try {
/*  69 */         Thread.sleep(100L);
/*     */       } catch (InterruptedException e) {
/*  71 */         LWJGLUtil.log("Interrupted while retrying: " + e);
/*     */       }
/*     */     }
/*     */ 
/*  75 */     return this.lock_buffer;
/*     */   }
/*     */ 
/*     */   private boolean privilegedLockAndInitHandle(final Canvas component)
/*     */     throws LWJGLException
/*     */   {
/*  85 */     if (this.firstLockSucceeded)
/*  86 */       return lockAndInitHandle(this.lock_buffer, component);
/*     */     try
/*     */     {
/*  89 */       this.firstLockSucceeded = ((Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Boolean run() throws LWJGLException {
/*  91 */           return Boolean.valueOf(AWTSurfaceLock.lockAndInitHandle(AWTSurfaceLock.this.lock_buffer, component));
/*     */         }
/*     */       })).booleanValue();
/*     */ 
/*  94 */       return this.firstLockSucceeded;
/*     */     } catch (PrivilegedActionException e) {
/*  96 */       throw ((LWJGLException)e.getException());
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native boolean lockAndInitHandle(ByteBuffer paramByteBuffer, Canvas paramCanvas) throws LWJGLException;
/*     */ 
/*     */   void unlock() throws LWJGLException {
/* 103 */     nUnlock(this.lock_buffer);
/*     */   }
/*     */ 
/*     */   private static native void nUnlock(ByteBuffer paramByteBuffer)
/*     */     throws LWJGLException;
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AWTSurfaceLock
 * JD-Core Version:    0.6.2
 */