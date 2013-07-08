/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  12:    */public final class APPLEFence
/*  13:    */{
/*  14:    */  public static final int GL_DRAW_PIXELS_APPLE = 35338;
/*  15:    */  public static final int GL_FENCE_APPLE = 35339;
/*  16:    */  
/*  17:    */  public static void glGenFencesAPPLE(IntBuffer fences)
/*  18:    */  {
/*  19: 19 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  20: 20 */    long function_pointer = caps.glGenFencesAPPLE;
/*  21: 21 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  22: 22 */    BufferChecks.checkDirect(fences);
/*  23: 23 */    nglGenFencesAPPLE(fences.remaining(), MemoryUtil.getAddress(fences), function_pointer);
/*  24:    */  }
/*  25:    */  
/*  26:    */  static native void nglGenFencesAPPLE(int paramInt, long paramLong1, long paramLong2);
/*  27:    */  
/*  28:    */  public static int glGenFencesAPPLE() {
/*  29: 29 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  30: 30 */    long function_pointer = caps.glGenFencesAPPLE;
/*  31: 31 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  32: 32 */    IntBuffer fences = APIUtil.getBufferInt(caps);
/*  33: 33 */    nglGenFencesAPPLE(1, MemoryUtil.getAddress(fences), function_pointer);
/*  34: 34 */    return fences.get(0);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public static void glDeleteFencesAPPLE(IntBuffer fences) {
/*  38: 38 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  39: 39 */    long function_pointer = caps.glDeleteFencesAPPLE;
/*  40: 40 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  41: 41 */    BufferChecks.checkDirect(fences);
/*  42: 42 */    nglDeleteFencesAPPLE(fences.remaining(), MemoryUtil.getAddress(fences), function_pointer);
/*  43:    */  }
/*  44:    */  
/*  45:    */  static native void nglDeleteFencesAPPLE(int paramInt, long paramLong1, long paramLong2);
/*  46:    */  
/*  47:    */  public static void glDeleteFencesAPPLE(int fence) {
/*  48: 48 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  49: 49 */    long function_pointer = caps.glDeleteFencesAPPLE;
/*  50: 50 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  51: 51 */    nglDeleteFencesAPPLE(1, APIUtil.getInt(caps, fence), function_pointer);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public static void glSetFenceAPPLE(int fence) {
/*  55: 55 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  56: 56 */    long function_pointer = caps.glSetFenceAPPLE;
/*  57: 57 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  58: 58 */    nglSetFenceAPPLE(fence, function_pointer);
/*  59:    */  }
/*  60:    */  
/*  61:    */  static native void nglSetFenceAPPLE(int paramInt, long paramLong);
/*  62:    */  
/*  63: 63 */  public static boolean glIsFenceAPPLE(int fence) { ContextCapabilities caps = GLContext.getCapabilities();
/*  64: 64 */    long function_pointer = caps.glIsFenceAPPLE;
/*  65: 65 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  66: 66 */    boolean __result = nglIsFenceAPPLE(fence, function_pointer);
/*  67: 67 */    return __result;
/*  68:    */  }
/*  69:    */  
/*  70:    */  static native boolean nglIsFenceAPPLE(int paramInt, long paramLong);
/*  71:    */  
/*  72: 72 */  public static boolean glTestFenceAPPLE(int fence) { ContextCapabilities caps = GLContext.getCapabilities();
/*  73: 73 */    long function_pointer = caps.glTestFenceAPPLE;
/*  74: 74 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  75: 75 */    boolean __result = nglTestFenceAPPLE(fence, function_pointer);
/*  76: 76 */    return __result;
/*  77:    */  }
/*  78:    */  
/*  79:    */  static native boolean nglTestFenceAPPLE(int paramInt, long paramLong);
/*  80:    */  
/*  81: 81 */  public static void glFinishFenceAPPLE(int fence) { ContextCapabilities caps = GLContext.getCapabilities();
/*  82: 82 */    long function_pointer = caps.glFinishFenceAPPLE;
/*  83: 83 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  84: 84 */    nglFinishFenceAPPLE(fence, function_pointer);
/*  85:    */  }
/*  86:    */  
/*  87:    */  static native void nglFinishFenceAPPLE(int paramInt, long paramLong);
/*  88:    */  
/*  89: 89 */  public static boolean glTestObjectAPPLE(int object, int name) { ContextCapabilities caps = GLContext.getCapabilities();
/*  90: 90 */    long function_pointer = caps.glTestObjectAPPLE;
/*  91: 91 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  92: 92 */    boolean __result = nglTestObjectAPPLE(object, name, function_pointer);
/*  93: 93 */    return __result;
/*  94:    */  }
/*  95:    */  
/*  96:    */  static native boolean nglTestObjectAPPLE(int paramInt1, int paramInt2, long paramLong);
/*  97:    */  
/*  98: 98 */  public static void glFinishObjectAPPLE(int object, int name) { ContextCapabilities caps = GLContext.getCapabilities();
/*  99: 99 */    long function_pointer = caps.glFinishObjectAPPLE;
/* 100:100 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 101:101 */    nglFinishObjectAPPLE(object, name, function_pointer);
/* 102:    */  }
/* 103:    */  
/* 104:    */  static native void nglFinishObjectAPPLE(int paramInt1, int paramInt2, long paramLong);
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEFence
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */