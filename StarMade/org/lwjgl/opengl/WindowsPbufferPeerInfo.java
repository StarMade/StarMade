/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ final class WindowsPbufferPeerInfo extends WindowsPeerInfo
/*    */ {
/*    */   WindowsPbufferPeerInfo(int width, int height, PixelFormat pixel_format, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/*    */     throws LWJGLException
/*    */   {
/* 47 */     nCreate(getHandle(), width, height, pixel_format, pixelFormatCaps, pBufferAttribs);
/*    */   }
/*    */   private static native void nCreate(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, PixelFormat paramPixelFormat, IntBuffer paramIntBuffer1, IntBuffer paramIntBuffer2) throws LWJGLException;
/*    */ 
/*    */   public boolean isBufferLost() {
/* 52 */     return nIsBufferLost(getHandle());
/*    */   }
/*    */   private static native boolean nIsBufferLost(ByteBuffer paramByteBuffer);
/*    */ 
/*    */   public void setPbufferAttrib(int attrib, int value) {
/* 57 */     nSetPbufferAttrib(getHandle(), attrib, value);
/*    */   }
/*    */   private static native void nSetPbufferAttrib(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/*    */ 
/*    */   public void bindTexImageToPbuffer(int buffer) {
/* 62 */     nBindTexImageToPbuffer(getHandle(), buffer);
/*    */   }
/*    */   private static native void nBindTexImageToPbuffer(ByteBuffer paramByteBuffer, int paramInt);
/*    */ 
/*    */   public void releaseTexImageFromPbuffer(int buffer) {
/* 67 */     nReleaseTexImageFromPbuffer(getHandle(), buffer);
/*    */   }
/*    */   private static native void nReleaseTexImageFromPbuffer(ByteBuffer paramByteBuffer, int paramInt);
/*    */ 
/*    */   public void destroy() {
/* 72 */     nDestroy(getHandle());
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
 * Qualified Name:     org.lwjgl.opengl.WindowsPbufferPeerInfo
 * JD-Core Version:    0.6.2
 */