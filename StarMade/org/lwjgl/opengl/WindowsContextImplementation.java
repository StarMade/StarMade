/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.LWJGLUtil;
/*   7:    */
/*  42:    */final class WindowsContextImplementation
/*  43:    */  implements ContextImplementation
/*  44:    */{
/*  45:    */  public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle)
/*  46:    */    throws LWJGLException
/*  47:    */  {
/*  48: 48 */    ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*  49:    */    try {
/*  50: 50 */      return nCreate(peer_handle, attribs, shared_context_handle);
/*  51:    */    } finally {
/*  52: 52 */      peer_info.unlock();
/*  53:    */    }
/*  54:    */  }
/*  55:    */  
/*  56:    */  private static native ByteBuffer nCreate(ByteBuffer paramByteBuffer1, IntBuffer paramIntBuffer, ByteBuffer paramByteBuffer2) throws LWJGLException;
/*  57:    */  
/*  58:    */  native long getHGLRC(ByteBuffer paramByteBuffer);
/*  59:    */  
/*  60:    */  native long getHDC(ByteBuffer paramByteBuffer);
/*  61:    */  
/*  62:    */  public void swapBuffers() throws LWJGLException {
/*  63: 63 */    ContextGL current_context = ContextGL.getCurrentContext();
/*  64: 64 */    if (current_context == null)
/*  65: 65 */      throw new IllegalStateException("No context is current");
/*  66: 66 */    synchronized (current_context) {
/*  67: 67 */      PeerInfo current_peer_info = current_context.getPeerInfo();
/*  68: 68 */      ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
/*  69:    */      try {
/*  70: 70 */        nSwapBuffers(peer_handle);
/*  71:    */      } finally {
/*  72: 72 */        current_peer_info.unlock();
/*  73:    */      }
/*  74:    */    }
/*  75:    */  }
/*  76:    */  
/*  77:    */  private static native void nSwapBuffers(ByteBuffer paramByteBuffer)
/*  78:    */    throws LWJGLException;
/*  79:    */  
/*  80:    */  public void releaseDrawable(ByteBuffer context_handle) throws LWJGLException
/*  81:    */  {}
/*  82:    */  
/*  83:    */  public void update(ByteBuffer context_handle) {}
/*  84:    */  
/*  85:    */  public void releaseCurrentContext() throws LWJGLException
/*  86:    */  {}
/*  87:    */  
/*  88:    */  private static native void nReleaseCurrentContext() throws LWJGLException;
/*  89:    */  
/*  90:    */  public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException
/*  91:    */  {
/*  92: 92 */    ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/*  93:    */    try {
/*  94: 94 */      nMakeCurrent(peer_handle, handle);
/*  95:    */    } finally {
/*  96: 96 */      peer_info.unlock();
/*  97:    */    }
/*  98:    */  }
/*  99:    */  
/* 100:    */  private static native void nMakeCurrent(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/* 101:    */  
/* 102:    */  public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
/* 103:103 */    boolean result = nIsCurrent(handle);
/* 104:104 */    return result;
/* 105:    */  }
/* 106:    */  
/* 107:    */  private static native boolean nIsCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/* 108:    */  
/* 109:    */  public void setSwapInterval(int value) {
/* 110:110 */    boolean success = nSetSwapInterval(value);
/* 111:111 */    if (!success)
/* 112:112 */      LWJGLUtil.log("Failed to set swap interval");
/* 113:113 */    Util.checkGLError();
/* 114:    */  }
/* 115:    */  
/* 116:    */  private static native boolean nSetSwapInterval(int paramInt);
/* 117:    */  
/* 118:    */  public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 119:119 */    nDestroy(handle);
/* 120:    */  }
/* 121:    */  
/* 122:    */  private static native void nDestroy(ByteBuffer paramByteBuffer)
/* 123:    */    throws LWJGLException;
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsContextImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */