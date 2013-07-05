/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class MacOSXAWTGLCanvasPeerInfo extends MacOSXCanvasPeerInfo
/*    */ {
/*    */   private final Canvas component;
/*    */ 
/*    */   MacOSXAWTGLCanvasPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
/*    */     throws LWJGLException
/*    */   {
/* 48 */     super(pixel_format, attribs, support_pbuffer);
/* 49 */     this.component = component;
/*    */   }
/*    */ 
/*    */   protected void doLockAndInitHandle() throws LWJGLException {
/* 53 */     initHandle(this.component);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXAWTGLCanvasPeerInfo
 * JD-Core Version:    0.6.2
 */