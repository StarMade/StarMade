/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import java.nio.ShortBuffer;
/*  6:   */import org.lwjgl.BufferChecks;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/* 22:   */public final class APPLEElementArray
/* 23:   */{
/* 24:   */  public static final int GL_ELEMENT_ARRAY_APPLE = 34664;
/* 25:   */  public static final int GL_ELEMENT_ARRAY_TYPE_APPLE = 34665;
/* 26:   */  public static final int GL_ELEMENT_ARRAY_POINTER_APPLE = 34666;
/* 27:   */  
/* 28:   */  public static void glElementPointerAPPLE(ByteBuffer pointer)
/* 29:   */  {
/* 30:30 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glElementPointerAPPLE;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    BufferChecks.checkDirect(pointer);
/* 34:34 */    nglElementPointerAPPLE(5121, MemoryUtil.getAddress(pointer), function_pointer);
/* 35:   */  }
/* 36:   */  
/* 37:37 */  public static void glElementPointerAPPLE(IntBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 38:38 */    long function_pointer = caps.glElementPointerAPPLE;
/* 39:39 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    BufferChecks.checkDirect(pointer);
/* 41:41 */    nglElementPointerAPPLE(5125, MemoryUtil.getAddress(pointer), function_pointer);
/* 42:   */  }
/* 43:   */  
/* 44:44 */  public static void glElementPointerAPPLE(ShortBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 45:45 */    long function_pointer = caps.glElementPointerAPPLE;
/* 46:46 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 47:47 */    BufferChecks.checkDirect(pointer);
/* 48:48 */    nglElementPointerAPPLE(5123, MemoryUtil.getAddress(pointer), function_pointer);
/* 49:   */  }
/* 50:   */  
/* 51:   */  static native void nglElementPointerAPPLE(int paramInt, long paramLong1, long paramLong2);
/* 52:   */  
/* 53:53 */  public static void glDrawElementArrayAPPLE(int mode, int first, int count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 54:54 */    long function_pointer = caps.glDrawElementArrayAPPLE;
/* 55:55 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 56:56 */    nglDrawElementArrayAPPLE(mode, first, count, function_pointer);
/* 57:   */  }
/* 58:   */  
/* 59:   */  static native void nglDrawElementArrayAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 60:   */  
/* 61:61 */  public static void glDrawRangeElementArrayAPPLE(int mode, int start, int end, int first, int count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 62:62 */    long function_pointer = caps.glDrawRangeElementArrayAPPLE;
/* 63:63 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 64:64 */    nglDrawRangeElementArrayAPPLE(mode, start, end, first, count, function_pointer);
/* 65:   */  }
/* 66:   */  
/* 67:   */  static native void nglDrawRangeElementArrayAPPLE(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 68:   */  
/* 69:69 */  public static void glMultiDrawElementArrayAPPLE(int mode, IntBuffer first, IntBuffer count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    long function_pointer = caps.glMultiDrawElementArrayAPPLE;
/* 71:71 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 72:72 */    BufferChecks.checkDirect(first);
/* 73:73 */    BufferChecks.checkBuffer(count, first.remaining());
/* 74:74 */    nglMultiDrawElementArrayAPPLE(mode, MemoryUtil.getAddress(first), MemoryUtil.getAddress(count), first.remaining(), function_pointer);
/* 75:   */  }
/* 76:   */  
/* 77:   */  static native void nglMultiDrawElementArrayAPPLE(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/* 78:   */  
/* 79:79 */  public static void glMultiDrawRangeElementArrayAPPLE(int mode, int start, int end, IntBuffer first, IntBuffer count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 80:80 */    long function_pointer = caps.glMultiDrawRangeElementArrayAPPLE;
/* 81:81 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 82:82 */    BufferChecks.checkDirect(first);
/* 83:83 */    BufferChecks.checkBuffer(count, first.remaining());
/* 84:84 */    nglMultiDrawRangeElementArrayAPPLE(mode, start, end, MemoryUtil.getAddress(first), MemoryUtil.getAddress(count), first.remaining(), function_pointer);
/* 85:   */  }
/* 86:   */  
/* 87:   */  static native void nglMultiDrawRangeElementArrayAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, int paramInt4, long paramLong3);
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEElementArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */