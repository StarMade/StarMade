/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/*  9:   */public final class EXTDrawBuffers2
/* 10:   */{
/* 11:   */  public static void glColorMaskIndexedEXT(int buf, boolean r, boolean g, boolean b, boolean a)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glColorMaskIndexedEXT;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglColorMaskIndexedEXT(buf, r, g, b, a, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglColorMaskIndexedEXT(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, long paramLong);
/* 20:   */  
/* 21:21 */  public static void glGetBooleanIndexedEXT(int value, int index, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glGetBooleanIndexedvEXT;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    BufferChecks.checkBuffer(data, 4);
/* 25:25 */    nglGetBooleanIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 26:   */  }
/* 27:   */  
/* 28:   */  static native void nglGetBooleanIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 29:   */  
/* 30:   */  public static boolean glGetBooleanIndexedEXT(int value, int index) {
/* 31:31 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 32:32 */    long function_pointer = caps.glGetBooleanIndexedvEXT;
/* 33:33 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 34:34 */    ByteBuffer data = APIUtil.getBufferByte(caps, 1);
/* 35:35 */    nglGetBooleanIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 36:36 */    return data.get(0) == 1;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public static void glGetIntegerIndexedEXT(int value, int index, IntBuffer data) {
/* 40:40 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 41:41 */    long function_pointer = caps.glGetIntegerIndexedvEXT;
/* 42:42 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 43:43 */    BufferChecks.checkBuffer(data, 4);
/* 44:44 */    nglGetIntegerIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 45:   */  }
/* 46:   */  
/* 47:   */  static native void nglGetIntegerIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 48:   */  
/* 49:   */  public static int glGetIntegerIndexedEXT(int value, int index) {
/* 50:50 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 51:51 */    long function_pointer = caps.glGetIntegerIndexedvEXT;
/* 52:52 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 53:53 */    IntBuffer data = APIUtil.getBufferInt(caps);
/* 54:54 */    nglGetIntegerIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 55:55 */    return data.get(0);
/* 56:   */  }
/* 57:   */  
/* 58:   */  public static void glEnableIndexedEXT(int target, int index) {
/* 59:59 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 60:60 */    long function_pointer = caps.glEnableIndexedEXT;
/* 61:61 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 62:62 */    nglEnableIndexedEXT(target, index, function_pointer);
/* 63:   */  }
/* 64:   */  
/* 65:   */  static native void nglEnableIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/* 66:   */  
/* 67:67 */  public static void glDisableIndexedEXT(int target, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 68:68 */    long function_pointer = caps.glDisableIndexedEXT;
/* 69:69 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 70:70 */    nglDisableIndexedEXT(target, index, function_pointer);
/* 71:   */  }
/* 72:   */  
/* 73:   */  static native void nglDisableIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/* 74:   */  
/* 75:75 */  public static boolean glIsEnabledIndexedEXT(int target, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 76:76 */    long function_pointer = caps.glIsEnabledIndexedEXT;
/* 77:77 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 78:78 */    boolean __result = nglIsEnabledIndexedEXT(target, index, function_pointer);
/* 79:79 */    return __result;
/* 80:   */  }
/* 81:   */  
/* 82:   */  static native boolean nglIsEnabledIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDrawBuffers2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */