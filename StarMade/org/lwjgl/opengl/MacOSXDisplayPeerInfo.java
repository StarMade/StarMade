/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class MacOSXDisplayPeerInfo extends MacOSXCanvasPeerInfo
/*    */ {
/*    */   private boolean locked;
/*    */ 
/*    */   MacOSXDisplayPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
/*    */     throws LWJGLException
/*    */   {
/* 48 */     super(pixel_format, attribs, support_pbuffer);
/*    */   }
/*    */ 
/*    */   protected void doLockAndInitHandle() throws LWJGLException {
/* 52 */     if (this.locked)
/* 53 */       throw new RuntimeException("Already locked");
/* 54 */     Canvas canvas = ((MacOSXDisplay)Display.getImplementation()).getCanvas();
/* 55 */     if (canvas != null) {
/* 56 */       initHandle(canvas);
/* 57 */       this.locked = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   protected void doUnlock() throws LWJGLException {
/* 62 */     if (this.locked) {
/* 63 */       super.doUnlock();
/* 64 */       this.locked = false;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXDisplayPeerInfo
 * JD-Core Version:    0.6.2
 */