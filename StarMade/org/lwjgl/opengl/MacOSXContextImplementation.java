/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ 
/*     */ final class MacOSXContextImplementation
/*     */   implements ContextImplementation
/*     */ {
/*     */   public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle)
/*     */     throws LWJGLException
/*     */   {
/*  47 */     ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */     try {
/*  49 */       return nCreate(peer_handle, shared_context_handle);
/*     */     } finally {
/*  51 */       peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native ByteBuffer nCreate(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*     */ 
/*     */   public void swapBuffers() throws LWJGLException {
/*  58 */     ContextGL current_context = ContextGL.getCurrentContext();
/*  59 */     if (current_context == null)
/*  60 */       throw new IllegalStateException("No context is current");
/*  61 */     synchronized (current_context) {
/*  62 */       nSwapBuffers(current_context.getHandle());
/*     */     }
/*     */   }
/*     */ 
/*     */   native long getCGLShareGroup(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private static native void nSwapBuffers(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void update(ByteBuffer context_handle) {
/*  71 */     nUpdate(context_handle);
/*     */   }
/*     */ 
/*     */   private static native void nUpdate(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public void releaseCurrentContext() throws LWJGLException {
/*  77 */     nReleaseCurrentContext();
/*     */   }
/*     */ 
/*     */   private static native void nReleaseCurrentContext() throws LWJGLException;
/*     */ 
/*     */   public void releaseDrawable(ByteBuffer context_handle) throws LWJGLException {
/*  83 */     clearDrawable(context_handle);
/*     */   }
/*     */ 
/*     */   private static native void clearDrawable(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   static void resetView(PeerInfo peer_info, ContextGL context) throws LWJGLException {
/*  89 */     ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */     try {
/*  91 */       synchronized (context) {
/*  92 */         clearDrawable(context.getHandle());
/*  93 */         setView(peer_handle, context.getHandle());
/*     */       }
/*     */     } finally {
/*  96 */       peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 101 */     ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */     try {
/* 103 */       setView(peer_handle, handle);
/* 104 */       nMakeCurrent(handle);
/*     */     } finally {
/* 106 */       peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void setView(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*     */ 
/*     */   private static native void nMakeCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
/* 115 */     boolean result = nIsCurrent(handle);
/* 116 */     return result;
/*     */   }
/*     */ 
/*     */   private static native boolean nIsCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void setSwapInterval(int value) {
/* 122 */     ContextGL current_context = ContextGL.getCurrentContext();
/* 123 */     synchronized (current_context) {
/* 124 */       nSetSwapInterval(current_context.getHandle(), value);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nSetSwapInterval(ByteBuffer paramByteBuffer, int paramInt);
/*     */ 
/*     */   public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 131 */     nDestroy(handle);
/*     */   }
/*     */ 
/*     */   private static native void nDestroy(ByteBuffer paramByteBuffer)
/*     */     throws LWJGLException;
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXContextImplementation
 * JD-Core Version:    0.6.2
 */