/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 13:   */public final class APPLEVertexArrayObject
/* 14:   */{
/* 15:   */  public static final int GL_VERTEX_ARRAY_BINDING_APPLE = 34229;
/* 16:   */  
/* 17:   */  public static void glBindVertexArrayAPPLE(int array)
/* 18:   */  {
/* 19:19 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 20:20 */    long function_pointer = caps.glBindVertexArrayAPPLE;
/* 21:21 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 22:22 */    nglBindVertexArrayAPPLE(array, function_pointer);
/* 23:   */  }
/* 24:   */  
/* 25:   */  static native void nglBindVertexArrayAPPLE(int paramInt, long paramLong);
/* 26:   */  
/* 27:27 */  public static void glDeleteVertexArraysAPPLE(IntBuffer arrays) { ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glDeleteVertexArraysAPPLE;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    BufferChecks.checkDirect(arrays);
/* 31:31 */    nglDeleteVertexArraysAPPLE(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/* 32:   */  }
/* 33:   */  
/* 34:   */  static native void nglDeleteVertexArraysAPPLE(int paramInt, long paramLong1, long paramLong2);
/* 35:   */  
/* 36:   */  public static void glDeleteVertexArraysAPPLE(int array) {
/* 37:37 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 38:38 */    long function_pointer = caps.glDeleteVertexArraysAPPLE;
/* 39:39 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    nglDeleteVertexArraysAPPLE(1, APIUtil.getInt(caps, array), function_pointer);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public static void glGenVertexArraysAPPLE(IntBuffer arrays) {
/* 44:44 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 45:45 */    long function_pointer = caps.glGenVertexArraysAPPLE;
/* 46:46 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 47:47 */    BufferChecks.checkDirect(arrays);
/* 48:48 */    nglGenVertexArraysAPPLE(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/* 49:   */  }
/* 50:   */  
/* 51:   */  static native void nglGenVertexArraysAPPLE(int paramInt, long paramLong1, long paramLong2);
/* 52:   */  
/* 53:   */  public static int glGenVertexArraysAPPLE() {
/* 54:54 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 55:55 */    long function_pointer = caps.glGenVertexArraysAPPLE;
/* 56:56 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 57:57 */    IntBuffer arrays = APIUtil.getBufferInt(caps);
/* 58:58 */    nglGenVertexArraysAPPLE(1, MemoryUtil.getAddress(arrays), function_pointer);
/* 59:59 */    return arrays.get(0);
/* 60:   */  }
/* 61:   */  
/* 62:   */  public static boolean glIsVertexArrayAPPLE(int array) {
/* 63:63 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 64:64 */    long function_pointer = caps.glIsVertexArrayAPPLE;
/* 65:65 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 66:66 */    boolean __result = nglIsVertexArrayAPPLE(array, function_pointer);
/* 67:67 */    return __result;
/* 68:   */  }
/* 69:   */  
/* 70:   */  static native boolean nglIsVertexArrayAPPLE(int paramInt, long paramLong);
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEVertexArrayObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */