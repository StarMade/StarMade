/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class LinuxDisplayPeerInfo extends LinuxPeerInfo
/*    */ {
/*    */   final boolean egl;
/*    */ 
/*    */   LinuxDisplayPeerInfo()
/*    */     throws LWJGLException
/*    */   {
/* 49 */     this.egl = true;
/* 50 */     org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
/*    */   }
/*    */ 
/*    */   LinuxDisplayPeerInfo(PixelFormat pixel_format) throws LWJGLException {
/* 54 */     this.egl = false;
/* 55 */     LinuxDisplay.lockAWT();
/*    */     try {
/* 57 */       GLContext.loadOpenGLLibrary();
/*    */       try {
/* 59 */         LinuxDisplay.incDisplay();
/*    */         try {
/* 61 */           initDefaultPeerInfo(LinuxDisplay.getDisplay(), LinuxDisplay.getDefaultScreen(), getHandle(), pixel_format);
/*    */         }
/*    */         catch (LWJGLException e) {
/* 64 */           throw e;
/*    */         }
/*    */       }
/*    */       catch (LWJGLException e) {
/* 68 */         throw e;
/*    */       }
/*    */     } finally {
/* 71 */       LinuxDisplay.unlockAWT();
/*    */     }
/*    */   }
/*    */ 
/*    */   private static native void initDefaultPeerInfo(long paramLong, int paramInt, ByteBuffer paramByteBuffer, PixelFormat paramPixelFormat) throws LWJGLException;
/*    */ 
/* 77 */   protected void doLockAndInitHandle() throws LWJGLException { LinuxDisplay.lockAWT();
/*    */     try {
/* 79 */       initDrawable(LinuxDisplay.getWindow(), getHandle());
/*    */     } finally {
/* 81 */       LinuxDisplay.unlockAWT();
/*    */     } }
/*    */ 
/*    */   private static native void initDrawable(long paramLong, ByteBuffer paramByteBuffer);
/*    */ 
/*    */   protected void doUnlock() throws LWJGLException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void destroy() {
/* 91 */     super.destroy();
/*    */ 
/* 93 */     if (this.egl) {
/* 94 */       org.lwjgl.opengles.GLContext.unloadOpenGLLibrary();
/*    */     } else {
/* 96 */       LinuxDisplay.lockAWT();
/* 97 */       LinuxDisplay.decDisplay();
/* 98 */       GLContext.unloadOpenGLLibrary();
/* 99 */       LinuxDisplay.unlockAWT();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxDisplayPeerInfo
 * JD-Core Version:    0.6.2
 */