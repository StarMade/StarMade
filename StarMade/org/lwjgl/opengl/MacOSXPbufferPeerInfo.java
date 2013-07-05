/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class MacOSXPbufferPeerInfo extends MacOSXPeerInfo
/*    */ {
/*    */   MacOSXPbufferPeerInfo(int width, int height, PixelFormat pixel_format, ContextAttribs attribs)
/*    */     throws LWJGLException
/*    */   {
/* 46 */     super(pixel_format, attribs, false, false, true, false);
/* 47 */     nCreate(getHandle(), width, height);
/*    */   }
/*    */   private static native void nCreate(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) throws LWJGLException;
/*    */ 
/*    */   public void destroy() {
/* 52 */     nDestroy(getHandle());
/*    */   }
/*    */ 
/*    */   private static native void nDestroy(ByteBuffer paramByteBuffer);
/*    */ 
/*    */   protected void doLockAndInitHandle()
/*    */     throws LWJGLException
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void doUnlock()
/*    */     throws LWJGLException
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXPbufferPeerInfo
 * JD-Core Version:    0.6.2
 */