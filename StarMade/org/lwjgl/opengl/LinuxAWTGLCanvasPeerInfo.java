/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.LWJGLException;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ 
/*    */ final class LinuxAWTGLCanvasPeerInfo extends LinuxPeerInfo
/*    */ {
/*    */   private final Canvas component;
/* 48 */   private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
/* 49 */   private int screen = -1;
/*    */ 
/*    */   LinuxAWTGLCanvasPeerInfo(Canvas component) {
/* 52 */     this.component = component;
/*    */   }
/*    */ 
/*    */   protected void doLockAndInitHandle() throws LWJGLException {
/* 56 */     ByteBuffer surface_handle = this.awt_surface.lockAndGetHandle(this.component);
/* 57 */     if (this.screen == -1) {
/*    */       try {
/* 59 */         this.screen = getScreenFromSurfaceInfo(surface_handle);
/*    */       } catch (LWJGLException e) {
/* 61 */         LWJGLUtil.log("Got exception while trying to determine screen: " + e);
/* 62 */         this.screen = 0;
/*    */       }
/*    */     }
/* 65 */     nInitHandle(this.screen, surface_handle, getHandle());
/*    */   }
/*    */   private static native int getScreenFromSurfaceInfo(ByteBuffer paramByteBuffer) throws LWJGLException;
/*    */ 
/*    */   private static native void nInitHandle(int paramInt, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*    */ 
/* 71 */   protected void doUnlock() throws LWJGLException { this.awt_surface.unlock(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxAWTGLCanvasPeerInfo
 * JD-Core Version:    0.6.2
 */