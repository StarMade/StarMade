/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.GraphicsDevice;
/*    */ import java.awt.Toolkit;
/*    */ import java.security.AccessController;
/*    */ import java.security.PrivilegedAction;
/*    */ import org.lwjgl.LWJGLException;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ 
/*    */ final class WindowsCanvasImplementation
/*    */   implements AWTCanvasImplementation
/*    */ {
/*    */   public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs)
/*    */     throws LWJGLException
/*    */   {
/* 70 */     return new WindowsAWTGLCanvasPeerInfo(component, pixel_format);
/*    */   }
/*    */ 
/*    */   public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
/*    */     throws LWJGLException
/*    */   {
/* 83 */     return null;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 53 */     Toolkit.getDefaultToolkit();
/* 54 */     AccessController.doPrivileged(new PrivilegedAction() {
/*    */       public Object run() {
/*    */         try {
/* 57 */           System.loadLibrary("jawt");
/*    */         }
/*    */         catch (UnsatisfiedLinkError e)
/*    */         {
/* 62 */           LWJGLUtil.log("Failed to load jawt: " + e.getMessage());
/*    */         }
/* 64 */         return null;
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsCanvasImplementation
 * JD-Core Version:    0.6.2
 */