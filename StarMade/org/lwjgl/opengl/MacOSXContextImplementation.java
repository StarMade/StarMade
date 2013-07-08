/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */
/*  41:    */final class MacOSXContextImplementation
/*  42:    */  implements ContextImplementation
/*  43:    */{
/*  44:    */  public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle)
/*  45:    */    throws LWJGLException
/*  46:    */  {
/*  47: 47 */    ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*  48:    */    try {
/*  49: 49 */      return nCreate(peer_handle, shared_context_handle);
/*  50:    */    } finally {
/*  51: 51 */      peer_info.unlock();
/*  52:    */    }
/*  53:    */  }
/*  54:    */  
/*  55:    */  private static native ByteBuffer nCreate(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*  56:    */  
/*  57:    */  public void swapBuffers() throws LWJGLException {
/*  58: 58 */    ContextGL current_context = ContextGL.getCurrentContext();
/*  59: 59 */    if (current_context == null)
/*  60: 60 */      throw new IllegalStateException("No context is current");
/*  61: 61 */    synchronized (current_context) {
/*  62: 62 */      nSwapBuffers(current_context.getHandle());
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  66:    */  native long getCGLShareGroup(ByteBuffer paramByteBuffer);
/*  67:    */  
/*  68:    */  private static native void nSwapBuffers(ByteBuffer paramByteBuffer) throws LWJGLException;
/*  69:    */  
/*  70:    */  public void update(ByteBuffer context_handle) {
/*  71: 71 */    nUpdate(context_handle);
/*  72:    */  }
/*  73:    */  
/*  74:    */  private static native void nUpdate(ByteBuffer paramByteBuffer);
/*  75:    */  
/*  76:    */  public void releaseCurrentContext() throws LWJGLException
/*  77:    */  {}
/*  78:    */  
/*  79:    */  private static native void nReleaseCurrentContext() throws LWJGLException;
/*  80:    */  
/*  81:    */  public void releaseDrawable(ByteBuffer context_handle) throws LWJGLException
/*  82:    */  {
/*  83: 83 */    clearDrawable(context_handle);
/*  84:    */  }
/*  85:    */  
/*  86:    */  private static native void clearDrawable(ByteBuffer paramByteBuffer) throws LWJGLException;
/*  87:    */  
/*  88:    */  static void resetView(PeerInfo peer_info, ContextGL context) throws LWJGLException {
/*  89: 89 */    ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*  90:    */    try {
/*  91: 91 */      synchronized (context) {
/*  92: 92 */        clearDrawable(context.getHandle());
/*  93: 93 */        setView(peer_handle, context.getHandle());
/*  94:    */      }
/*  95:    */    } finally {
/*  96: 96 */      peer_info.unlock();
/*  97:    */    }
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 101:101 */    ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/* 102:    */    try {
/* 103:103 */      setView(peer_handle, handle);
/* 104:104 */      nMakeCurrent(handle);
/* 105:    */    } finally {
/* 106:106 */      peer_info.unlock();
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 110:    */  private static native void setView(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/* 111:    */  
/* 112:    */  private static native void nMakeCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/* 113:    */  
/* 114:    */  public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
/* 115:115 */    boolean result = nIsCurrent(handle);
/* 116:116 */    return result;
/* 117:    */  }
/* 118:    */  
/* 119:    */  private static native boolean nIsCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/* 120:    */  
/* 121:    */  public void setSwapInterval(int value) {
/* 122:122 */    ContextGL current_context = ContextGL.getCurrentContext();
/* 123:123 */    synchronized (current_context) {
/* 124:124 */      nSetSwapInterval(current_context.getHandle(), value);
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 128:    */  private static native void nSetSwapInterval(ByteBuffer paramByteBuffer, int paramInt);
/* 129:    */  
/* 130:    */  public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 131:131 */    nDestroy(handle);
/* 132:    */  }
/* 133:    */  
/* 134:    */  private static native void nDestroy(ByteBuffer paramByteBuffer)
/* 135:    */    throws LWJGLException;
/* 136:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXContextImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */