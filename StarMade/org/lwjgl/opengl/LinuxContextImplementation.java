/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ 
/*     */ final class LinuxContextImplementation
/*     */   implements ContextImplementation
/*     */ {
/*     */   public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle)
/*     */     throws LWJGLException
/*     */   {
/*  47 */     LinuxDisplay.lockAWT();
/*     */     try {
/*  49 */       ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */       try {
/*  51 */         ByteBuffer localByteBuffer1 = nCreate(peer_handle, attribs, shared_context_handle);
/*     */ 
/*  53 */         peer_info.unlock();
/*     */ 
/*  56 */         return localByteBuffer1;
/*     */       }
/*     */       finally
/*     */       {
/*  53 */         peer_info.unlock();
/*     */       }
/*     */     } finally {
/*  56 */       LinuxDisplay.unlockAWT();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native ByteBuffer nCreate(ByteBuffer paramByteBuffer1, IntBuffer paramIntBuffer, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*     */ 
/*     */   native long getGLXContext(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   native long getDisplay(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public void releaseDrawable(ByteBuffer context_handle) throws LWJGLException {
/*     */   }
/*     */ 
/*     */   public void swapBuffers() throws LWJGLException {
/*  70 */     ContextGL current_context = ContextGL.getCurrentContext();
/*  71 */     if (current_context == null)
/*  72 */       throw new IllegalStateException("No context is current");
/*  73 */     synchronized (current_context) {
/*  74 */       PeerInfo current_peer_info = current_context.getPeerInfo();
/*  75 */       LinuxDisplay.lockAWT();
/*     */       try {
/*  77 */         ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
/*     */         try {
/*  79 */           nSwapBuffers(peer_handle);
/*     */         } finally {
/*  81 */           current_peer_info.unlock();
/*     */         }
/*     */       } finally {
/*  84 */         LinuxDisplay.unlockAWT();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nSwapBuffers(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void releaseCurrentContext() throws LWJGLException {
/*  92 */     ContextGL current_context = ContextGL.getCurrentContext();
/*  93 */     if (current_context == null)
/*  94 */       throw new IllegalStateException("No context is current");
/*  95 */     synchronized (current_context) {
/*  96 */       PeerInfo current_peer_info = current_context.getPeerInfo();
/*  97 */       LinuxDisplay.lockAWT();
/*     */       try {
/*  99 */         ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
/*     */         try {
/* 101 */           nReleaseCurrentContext(peer_handle);
/*     */         } finally {
/* 103 */           current_peer_info.unlock();
/*     */         }
/*     */       } finally {
/* 106 */         LinuxDisplay.unlockAWT();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nReleaseCurrentContext(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void update(ByteBuffer context_handle) {
/*     */   }
/*     */ 
/*     */   public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 117 */     LinuxDisplay.lockAWT();
/*     */     try {
/* 119 */       ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */       try {
/* 121 */         nMakeCurrent(peer_handle, handle);
/*     */       } finally {
/* 123 */         peer_info.unlock();
/*     */       }
/*     */     } finally {
/* 126 */       LinuxDisplay.unlockAWT();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nMakeCurrent(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*     */ 
/*     */   public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
/* 133 */     LinuxDisplay.lockAWT();
/*     */     try {
/* 135 */       boolean result = nIsCurrent(handle);
/* 136 */       return result;
/*     */     } finally {
/* 138 */       LinuxDisplay.unlockAWT();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native boolean nIsCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/*     */ 
/*     */   public void setSwapInterval(int value) {
/* 145 */     ContextGL current_context = ContextGL.getCurrentContext();
/* 146 */     PeerInfo peer_info = current_context.getPeerInfo();
/*     */ 
/* 148 */     if (current_context == null)
/* 149 */       throw new IllegalStateException("No context is current");
/* 150 */     synchronized (current_context) {
/* 151 */       LinuxDisplay.lockAWT();
/*     */       try {
/* 153 */         ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */         try {
/* 155 */           nSetSwapInterval(peer_handle, current_context.getHandle(), value);
/*     */         } finally {
/* 157 */           peer_info.unlock();
/*     */         }
/*     */       }
/*     */       catch (LWJGLException e) {
/* 161 */         e.printStackTrace();
/*     */       } finally {
/* 163 */         LinuxDisplay.unlockAWT();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nSetSwapInterval(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt);
/*     */ 
/*     */   public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 171 */     LinuxDisplay.lockAWT();
/*     */     try {
/* 173 */       ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*     */       try {
/* 175 */         nDestroy(peer_handle, handle);
/*     */       } finally {
/* 177 */         peer_info.unlock();
/*     */       }
/*     */     } finally {
/* 180 */       LinuxDisplay.unlockAWT();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native void nDestroy(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2)
/*     */     throws LWJGLException;
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxContextImplementation
 * JD-Core Version:    0.6.2
 */