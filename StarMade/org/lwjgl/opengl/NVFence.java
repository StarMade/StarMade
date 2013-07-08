/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class NVFence
/* 10:   */{
/* 11:   */  public static final int GL_ALL_COMPLETED_NV = 34034;
/* 12:   */  public static final int GL_FENCE_STATUS_NV = 34035;
/* 13:   */  public static final int GL_FENCE_CONDITION_NV = 34036;
/* 14:   */  
/* 15:   */  public static void glGenFencesNV(IntBuffer piFences)
/* 16:   */  {
/* 17:17 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 18:18 */    long function_pointer = caps.glGenFencesNV;
/* 19:19 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 20:20 */    BufferChecks.checkDirect(piFences);
/* 21:21 */    nglGenFencesNV(piFences.remaining(), MemoryUtil.getAddress(piFences), function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:   */  static native void nglGenFencesNV(int paramInt, long paramLong1, long paramLong2);
/* 25:   */  
/* 26:   */  public static int glGenFencesNV() {
/* 27:27 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glGenFencesNV;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    IntBuffer piFences = APIUtil.getBufferInt(caps);
/* 31:31 */    nglGenFencesNV(1, MemoryUtil.getAddress(piFences), function_pointer);
/* 32:32 */    return piFences.get(0);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public static void glDeleteFencesNV(IntBuffer piFences) {
/* 36:36 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 37:37 */    long function_pointer = caps.glDeleteFencesNV;
/* 38:38 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 39:39 */    BufferChecks.checkDirect(piFences);
/* 40:40 */    nglDeleteFencesNV(piFences.remaining(), MemoryUtil.getAddress(piFences), function_pointer);
/* 41:   */  }
/* 42:   */  
/* 43:   */  static native void nglDeleteFencesNV(int paramInt, long paramLong1, long paramLong2);
/* 44:   */  
/* 45:   */  public static void glDeleteFencesNV(int fence) {
/* 46:46 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 47:47 */    long function_pointer = caps.glDeleteFencesNV;
/* 48:48 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 49:49 */    nglDeleteFencesNV(1, APIUtil.getInt(caps, fence), function_pointer);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public static void glSetFenceNV(int fence, int condition) {
/* 53:53 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 54:54 */    long function_pointer = caps.glSetFenceNV;
/* 55:55 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 56:56 */    nglSetFenceNV(fence, condition, function_pointer);
/* 57:   */  }
/* 58:   */  
/* 59:   */  static native void nglSetFenceNV(int paramInt1, int paramInt2, long paramLong);
/* 60:   */  
/* 61:61 */  public static boolean glTestFenceNV(int fence) { ContextCapabilities caps = GLContext.getCapabilities();
/* 62:62 */    long function_pointer = caps.glTestFenceNV;
/* 63:63 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 64:64 */    boolean __result = nglTestFenceNV(fence, function_pointer);
/* 65:65 */    return __result;
/* 66:   */  }
/* 67:   */  
/* 68:   */  static native boolean nglTestFenceNV(int paramInt, long paramLong);
/* 69:   */  
/* 70:70 */  public static void glFinishFenceNV(int fence) { ContextCapabilities caps = GLContext.getCapabilities();
/* 71:71 */    long function_pointer = caps.glFinishFenceNV;
/* 72:72 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 73:73 */    nglFinishFenceNV(fence, function_pointer);
/* 74:   */  }
/* 75:   */  
/* 76:   */  static native void nglFinishFenceNV(int paramInt, long paramLong);
/* 77:   */  
/* 78:78 */  public static boolean glIsFenceNV(int fence) { ContextCapabilities caps = GLContext.getCapabilities();
/* 79:79 */    long function_pointer = caps.glIsFenceNV;
/* 80:80 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 81:81 */    boolean __result = nglIsFenceNV(fence, function_pointer);
/* 82:82 */    return __result;
/* 83:   */  }
/* 84:   */  
/* 85:   */  static native boolean nglIsFenceNV(int paramInt, long paramLong);
/* 86:   */  
/* 87:87 */  public static void glGetFenceivNV(int fence, int pname, IntBuffer piParams) { ContextCapabilities caps = GLContext.getCapabilities();
/* 88:88 */    long function_pointer = caps.glGetFenceivNV;
/* 89:89 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 90:90 */    BufferChecks.checkBuffer(piParams, 4);
/* 91:91 */    nglGetFenceivNV(fence, pname, MemoryUtil.getAddress(piParams), function_pointer);
/* 92:   */  }
/* 93:   */  
/* 94:   */  static native void nglGetFenceivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVFence
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */