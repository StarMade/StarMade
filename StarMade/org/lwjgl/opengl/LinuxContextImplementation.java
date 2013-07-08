/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */
/*   7:    */final class LinuxContextImplementation
/*   8:    */  implements ContextImplementation
/*   9:    */{
/*  10:    */  /* Error */
/*  11:    */  public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle)
/*  12:    */    throws LWJGLException
/*  13:    */  {
/*  14:    */    // Byte code:
/*  15:    */    //   0: invokestatic 2	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/*  16:    */    //   3: aload_1
/*  17:    */    //   4: invokevirtual 3	org/lwjgl/opengl/PeerInfo:lockAndGetHandle	()Ljava/nio/ByteBuffer;
/*  18:    */    //   7: astore 4
/*  19:    */    //   9: aload 4
/*  20:    */    //   11: aload_2
/*  21:    */    //   12: aload_3
/*  22:    */    //   13: invokestatic 4	org/lwjgl/opengl/LinuxContextImplementation:nCreate	(Ljava/nio/ByteBuffer;Ljava/nio/IntBuffer;Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
/*  23:    */    //   16: astore 5
/*  24:    */    //   18: aload_1
/*  25:    */    //   19: invokevirtual 5	org/lwjgl/opengl/PeerInfo:unlock	()V
/*  26:    */    //   22: invokestatic 6	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  27:    */    //   25: aload 5
/*  28:    */    //   27: areturn
/*  29:    */    //   28: astore 6
/*  30:    */    //   30: aload_1
/*  31:    */    //   31: invokevirtual 5	org/lwjgl/opengl/PeerInfo:unlock	()V
/*  32:    */    //   34: aload 6
/*  33:    */    //   36: athrow
/*  34:    */    //   37: astore 7
/*  35:    */    //   39: invokestatic 6	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  36:    */    //   42: aload 7
/*  37:    */    //   44: athrow
/*  38:    */    // Line number table:
/*  39:    */    //   Java source line #47	-> byte code offset #0
/*  40:    */    //   Java source line #49	-> byte code offset #3
/*  41:    */    //   Java source line #51	-> byte code offset #9
/*  42:    */    //   Java source line #53	-> byte code offset #18
/*  43:    */    //   Java source line #56	-> byte code offset #22
/*  44:    */    //   Java source line #53	-> byte code offset #28
/*  45:    */    //   Java source line #56	-> byte code offset #37
/*  46:    */    // Local variable table:
/*  47:    */    //   start	length	slot	name	signature
/*  48:    */    //   0	45	0	this	LinuxContextImplementation
/*  49:    */    //   0	45	1	peer_info	PeerInfo
/*  50:    */    //   0	45	2	attribs	IntBuffer
/*  51:    */    //   0	45	3	shared_context_handle	ByteBuffer
/*  52:    */    //   7	3	4	peer_handle	ByteBuffer
/*  53:    */    //   16	10	5	localByteBuffer1	ByteBuffer
/*  54:    */    //   28	7	6	localObject1	Object
/*  55:    */    //   37	6	7	localObject2	Object
/*  56:    */    // Exception table:
/*  57:    */    //   from	to	target	type
/*  58:    */    //   9	18	28	finally
/*  59:    */    //   28	30	28	finally
/*  60:    */    //   3	22	37	finally
/*  61:    */    //   28	39	37	finally
/*  62:    */  }
/*  63:    */  
/*  64:    */  private static native ByteBuffer nCreate(ByteBuffer paramByteBuffer1, IntBuffer paramIntBuffer, ByteBuffer paramByteBuffer2)
/*  65:    */    throws LWJGLException;
/*  66:    */  
/*  67:    */  native long getGLXContext(ByteBuffer paramByteBuffer);
/*  68:    */  
/*  69:    */  native long getDisplay(ByteBuffer paramByteBuffer);
/*  70:    */  
/*  71:    */  public void releaseDrawable(ByteBuffer context_handle)
/*  72:    */    throws LWJGLException
/*  73:    */  {}
/*  74:    */  
/*  75:    */  public void swapBuffers()
/*  76:    */    throws LWJGLException
/*  77:    */  {
/*  78: 70 */    ContextGL current_context = ContextGL.getCurrentContext();
/*  79: 71 */    if (current_context == null)
/*  80: 72 */      throw new IllegalStateException("No context is current");
/*  81: 73 */    synchronized (current_context) {
/*  82: 74 */      PeerInfo current_peer_info = current_context.getPeerInfo();
/*  83: 75 */      LinuxDisplay.lockAWT();
/*  84:    */      try {
/*  85: 77 */        ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
/*  86:    */        try {
/*  87: 79 */          nSwapBuffers(peer_handle);
/*  88:    */        } finally {
/*  89: 81 */          current_peer_info.unlock();
/*  90:    */        }
/*  91:    */      } finally {
/*  92: 84 */        LinuxDisplay.unlockAWT();
/*  93:    */      }
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/*  97:    */  private static native void nSwapBuffers(ByteBuffer paramByteBuffer) throws LWJGLException;
/*  98:    */  
/*  99:    */  public void releaseCurrentContext() throws LWJGLException {
/* 100: 92 */    ContextGL current_context = ContextGL.getCurrentContext();
/* 101: 93 */    if (current_context == null)
/* 102: 94 */      throw new IllegalStateException("No context is current");
/* 103: 95 */    synchronized (current_context) {
/* 104: 96 */      PeerInfo current_peer_info = current_context.getPeerInfo();
/* 105: 97 */      LinuxDisplay.lockAWT();
/* 106:    */      try {
/* 107: 99 */        ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
/* 108:    */        try {
/* 109:101 */          nReleaseCurrentContext(peer_handle);
/* 110:    */        } finally {
/* 111:103 */          current_peer_info.unlock();
/* 112:    */        }
/* 113:    */      } finally {
/* 114:106 */        LinuxDisplay.unlockAWT();
/* 115:    */      }
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 119:    */  private static native void nReleaseCurrentContext(ByteBuffer paramByteBuffer) throws LWJGLException;
/* 120:    */  
/* 121:    */  public void update(ByteBuffer context_handle) {}
/* 122:    */  
/* 123:    */  public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException
/* 124:    */  {
/* 125:    */    
/* 126:    */    try {
/* 127:119 */      ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/* 128:    */      try {
/* 129:121 */        nMakeCurrent(peer_handle, handle);
/* 130:    */      } finally {
/* 131:123 */        peer_info.unlock();
/* 132:    */      }
/* 133:    */    } finally {
/* 134:126 */      LinuxDisplay.unlockAWT();
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:    */  private static native void nMakeCurrent(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/* 139:    */  
/* 140:    */  public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
/* 141:    */    
/* 142:    */    try {
/* 143:135 */      boolean result = nIsCurrent(handle);
/* 144:136 */      return result;
/* 145:    */    } finally {
/* 146:138 */      LinuxDisplay.unlockAWT();
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 150:    */  private static native boolean nIsCurrent(ByteBuffer paramByteBuffer) throws LWJGLException;
/* 151:    */  
/* 152:    */  public void setSwapInterval(int value) {
/* 153:145 */    ContextGL current_context = ContextGL.getCurrentContext();
/* 154:146 */    PeerInfo peer_info = current_context.getPeerInfo();
/* 155:    */    
/* 156:148 */    if (current_context == null)
/* 157:149 */      throw new IllegalStateException("No context is current");
/* 158:150 */    synchronized (current_context) {
/* 159:151 */      LinuxDisplay.lockAWT();
/* 160:    */      try {
/* 161:153 */        ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/* 162:    */        try {
/* 163:155 */          nSetSwapInterval(peer_handle, current_context.getHandle(), value);
/* 164:    */        } finally {
/* 165:157 */          peer_info.unlock();
/* 166:    */        }
/* 167:    */      }
/* 168:    */      catch (LWJGLException e) {
/* 169:161 */        e.printStackTrace();
/* 170:    */      } finally {
/* 171:163 */        LinuxDisplay.unlockAWT();
/* 172:    */      }
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 176:    */  private static native void nSetSwapInterval(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt);
/* 177:    */  
/* 178:    */  public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
/* 179:    */    
/* 180:    */    try {
/* 181:173 */      ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/* 182:    */      try {
/* 183:175 */        nDestroy(peer_handle, handle);
/* 184:    */      } finally {
/* 185:177 */        peer_info.unlock();
/* 186:    */      }
/* 187:    */    } finally {
/* 188:180 */      LinuxDisplay.unlockAWT();
/* 189:    */    }
/* 190:    */  }
/* 191:    */  
/* 192:    */  private static native void nDestroy(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2)
/* 193:    */    throws LWJGLException;
/* 194:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxContextImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */