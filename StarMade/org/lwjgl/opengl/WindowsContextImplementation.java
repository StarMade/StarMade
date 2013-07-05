/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ final class WindowsContextImplementation
/*     */   implements ContextImplementation
/*     */ {
/*     */   public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle)
/*     */     throws LWJGLException
/*     */   {
/*  48 */     ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */     try {
/*  50 */       return nCreate(peer_handle, attribs, shared_context_handle);
/*     */     } finally {
/*  52 */       peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native ByteBuffer nCreate(ByteBuffer paramByteBuffer1, IntBuffer paramIntBuffer, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*     */ 
/*     */   native long getHGLRC(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   native long getHDC(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public void swapBuffers() throws LWJGLException {
/*  63 */     ContextGL current_context = ContextGL.getCurrentContext();
/*  64 */     if (current_context == null)
/*  65 */       throw new IllegalStateException("No context is current");
/*  66 */     synchronized (current_context) {
/*  67 */       PeerInfo current_peer_info = current_context.getPeerInfo();
/*  68 */       ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
/*     */       try {
/*  70 */         nSwapBuffers(peer_handle);
/*     */       } finally {
/*  72 */         current_peer_info.unlock();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nSwapBuffers(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void releaseDrawable(ByteBuffer context_handle) throws LWJGLException {
/*     */   }
/*     */ 
/*     */   public void update(ByteBuffer context_handle) {
/*     */   }
/*     */ 
/*     */   public void releaseCurrentContext() throws LWJGLException {
/*  86 */     nReleaseCurrentContext();
/*     */   }
/*     */ 
/*     */   private static native void nReleaseCurrentContext() throws LWJGLException;
/*     */ 
/*     */   public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/*  92 */     ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */     try {
/*  94 */       nMakeCurrent(peer_handle, handle);
/*     */     } finally {
/*  96 */       peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nMakeCurrent(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*     */ 
/*     */   public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
/* 103 */     boolean result = nIsCurrent(handle);
/* 104 */     return result;
/*     */   }
/*     */ 
/*     */   private static native boolean nIsCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void setSwapInterval(int value) {
/* 110 */     boolean success = nSetSwapInterval(value);
/* 111 */     if (!success)
/* 112 */       LWJGLUtil.log("Failed to set swap interval");
/* 113 */     Util.checkGLError();
/*     */   }
/*     */ 
/*     */   private static native boolean nSetSwapInterval(int paramInt);
/*     */ 
/*     */   public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 119 */     nDestroy(handle);
/*     */   }
/*     */ 
/*     */   private static native void nDestroy(ByteBuffer paramByteBuffer)
/*     */     throws LWJGLException;
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsContextImplementation
 * JD-Core Version:    0.6.2
 */