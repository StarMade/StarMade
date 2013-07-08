/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.DoubleBuffer;
/*  4:   */import java.nio.FloatBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/* 18:   */public final class APPLEVertexProgramEvaluators
/* 19:   */{
/* 20:   */  public static final int GL_VERTEX_ATTRIB_MAP1_APPLE = 35328;
/* 21:   */  public static final int GL_VERTEX_ATTRIB_MAP2_APPLE = 35329;
/* 22:   */  public static final int GL_VERTEX_ATTRIB_MAP1_SIZE_APPLE = 35330;
/* 23:   */  public static final int GL_VERTEX_ATTRIB_MAP1_COEFF_APPLE = 35331;
/* 24:   */  public static final int GL_VERTEX_ATTRIB_MAP1_ORDER_APPLE = 35332;
/* 25:   */  public static final int GL_VERTEX_ATTRIB_MAP1_DOMAIN_APPLE = 35333;
/* 26:   */  public static final int GL_VERTEX_ATTRIB_MAP2_SIZE_APPLE = 35334;
/* 27:   */  public static final int GL_VERTEX_ATTRIB_MAP2_COEFF_APPLE = 35335;
/* 28:   */  public static final int GL_VERTEX_ATTRIB_MAP2_ORDER_APPLE = 35336;
/* 29:   */  public static final int GL_VERTEX_ATTRIB_MAP2_DOMAIN_APPLE = 35337;
/* 30:   */  
/* 31:   */  public static void glEnableVertexAttribAPPLE(int index, int pname)
/* 32:   */  {
/* 33:33 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 34:34 */    long function_pointer = caps.glEnableVertexAttribAPPLE;
/* 35:35 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 36:36 */    nglEnableVertexAttribAPPLE(index, pname, function_pointer);
/* 37:   */  }
/* 38:   */  
/* 39:   */  static native void nglEnableVertexAttribAPPLE(int paramInt1, int paramInt2, long paramLong);
/* 40:   */  
/* 41:41 */  public static void glDisableVertexAttribAPPLE(int index, int pname) { ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glDisableVertexAttribAPPLE;
/* 43:43 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    nglDisableVertexAttribAPPLE(index, pname, function_pointer);
/* 45:   */  }
/* 46:   */  
/* 47:   */  static native void nglDisableVertexAttribAPPLE(int paramInt1, int paramInt2, long paramLong);
/* 48:   */  
/* 49:49 */  public static boolean glIsVertexAttribEnabledAPPLE(int index, int pname) { ContextCapabilities caps = GLContext.getCapabilities();
/* 50:50 */    long function_pointer = caps.glIsVertexAttribEnabledAPPLE;
/* 51:51 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 52:52 */    boolean __result = nglIsVertexAttribEnabledAPPLE(index, pname, function_pointer);
/* 53:53 */    return __result;
/* 54:   */  }
/* 55:   */  
/* 56:   */  static native boolean nglIsVertexAttribEnabledAPPLE(int paramInt1, int paramInt2, long paramLong);
/* 57:   */  
/* 58:58 */  public static void glMapVertexAttrib1dAPPLE(int index, int size, double u1, double u2, int stride, int order, DoubleBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 59:59 */    long function_pointer = caps.glMapVertexAttrib1dAPPLE;
/* 60:60 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 61:61 */    BufferChecks.checkDirect(points);
/* 62:62 */    nglMapVertexAttrib1dAPPLE(index, size, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
/* 63:   */  }
/* 64:   */  
/* 65:   */  static native void nglMapVertexAttrib1dAPPLE(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 66:   */  
/* 67:67 */  public static void glMapVertexAttrib1fAPPLE(int index, int size, float u1, float u2, int stride, int order, FloatBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 68:68 */    long function_pointer = caps.glMapVertexAttrib1fAPPLE;
/* 69:69 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 70:70 */    BufferChecks.checkDirect(points);
/* 71:71 */    nglMapVertexAttrib1fAPPLE(index, size, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
/* 72:   */  }
/* 73:   */  
/* 74:   */  static native void nglMapVertexAttrib1fAPPLE(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 75:   */  
/* 76:76 */  public static void glMapVertexAttrib2dAPPLE(int index, int size, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 77:77 */    long function_pointer = caps.glMapVertexAttrib2dAPPLE;
/* 78:78 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 79:79 */    BufferChecks.checkDirect(points);
/* 80:80 */    nglMapVertexAttrib2dAPPLE(index, size, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
/* 81:   */  }
/* 82:   */  
/* 83:   */  static native void nglMapVertexAttrib2dAPPLE(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, int paramInt3, int paramInt4, double paramDouble3, double paramDouble4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 84:   */  
/* 85:85 */  public static void glMapVertexAttrib2fAPPLE(int index, int size, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 86:86 */    long function_pointer = caps.glMapVertexAttrib2fAPPLE;
/* 87:87 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 88:88 */    BufferChecks.checkDirect(points);
/* 89:89 */    nglMapVertexAttrib2fAPPLE(index, size, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
/* 90:   */  }
/* 91:   */  
/* 92:   */  static native void nglMapVertexAttrib2fAPPLE(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, int paramInt4, float paramFloat3, float paramFloat4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEVertexProgramEvaluators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */