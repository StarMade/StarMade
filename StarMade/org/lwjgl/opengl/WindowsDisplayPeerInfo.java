/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class WindowsDisplayPeerInfo extends WindowsPeerInfo
/*    */ {
/*    */   final boolean egl;
/*    */ 
/*    */   WindowsDisplayPeerInfo(boolean egl)
/*    */     throws LWJGLException
/*    */   {
/* 49 */     this.egl = egl;
/*    */ 
/* 51 */     if (egl)
/* 52 */       org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
/*    */     else
/* 54 */       GLContext.loadOpenGLLibrary();
/*    */   }
/*    */ 
/*    */   void initDC(long hwnd, long hdc) throws LWJGLException {
/* 58 */     nInitDC(getHandle(), hwnd, hdc);
/*    */   }
/*    */ 
/*    */   private static native void nInitDC(ByteBuffer paramByteBuffer, long paramLong1, long paramLong2);
/*    */ 
/*    */   protected void doLockAndInitHandle() throws LWJGLException
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void doUnlock() throws LWJGLException {
/*    */   }
/*    */ 
/*    */   public void destroy() {
/* 71 */     super.destroy();
/*    */ 
/* 73 */     if (this.egl)
/* 74 */       org.lwjgl.opengles.GLContext.unloadOpenGLLibrary();
/*    */     else
/* 76 */       GLContext.unloadOpenGLLibrary();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsDisplayPeerInfo
 * JD-Core Version:    0.6.2
 */