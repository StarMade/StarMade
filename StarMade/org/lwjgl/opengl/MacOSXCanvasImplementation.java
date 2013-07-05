/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.GraphicsDevice;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class MacOSXCanvasImplementation
/*    */   implements AWTCanvasImplementation
/*    */ {
/*    */   public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs)
/*    */     throws LWJGLException
/*    */   {
/*    */     try
/*    */     {
/* 49 */       return new MacOSXAWTGLCanvasPeerInfo(component, pixel_format, attribs, true); } catch (LWJGLException e) {
/*    */     }
/* 51 */     return new MacOSXAWTGLCanvasPeerInfo(component, pixel_format, attribs, false);
/*    */   }
/*    */ 
/*    */   public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
/*    */     throws LWJGLException
/*    */   {
/* 64 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasImplementation
 * JD-Core Version:    0.6.2
 */