/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.DoubleBuffer;
/*  4:   */import java.nio.FloatBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.LWJGLUtil;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/*  9:   */public final class EXTFogCoord
/* 10:   */{
/* 11:   */  public static final int GL_FOG_COORDINATE_SOURCE_EXT = 33872;
/* 12:   */  public static final int GL_FOG_COORDINATE_EXT = 33873;
/* 13:   */  public static final int GL_FRAGMENT_DEPTH_EXT = 33874;
/* 14:   */  public static final int GL_CURRENT_FOG_COORDINATE_EXT = 33875;
/* 15:   */  public static final int GL_FOG_COORDINATE_ARRAY_TYPE_EXT = 33876;
/* 16:   */  public static final int GL_FOG_COORDINATE_ARRAY_STRIDE_EXT = 33877;
/* 17:   */  public static final int GL_FOG_COORDINATE_ARRAY_POINTER_EXT = 33878;
/* 18:   */  public static final int GL_FOG_COORDINATE_ARRAY_EXT = 33879;
/* 19:   */  
/* 20:   */  public static void glFogCoordfEXT(float coord)
/* 21:   */  {
/* 22:22 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 23:23 */    long function_pointer = caps.glFogCoordfEXT;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    nglFogCoordfEXT(coord, function_pointer);
/* 26:   */  }
/* 27:   */  
/* 28:   */  static native void nglFogCoordfEXT(float paramFloat, long paramLong);
/* 29:   */  
/* 30:30 */  public static void glFogCoorddEXT(double coord) { ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glFogCoorddEXT;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    nglFogCoorddEXT(coord, function_pointer);
/* 34:   */  }
/* 35:   */  
/* 36:   */  static native void nglFogCoorddEXT(double paramDouble, long paramLong);
/* 37:   */  
/* 38:38 */  public static void glFogCoordPointerEXT(int stride, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 39:39 */    long function_pointer = caps.glFogCoordPointerEXT;
/* 40:40 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 41:41 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 42:42 */    BufferChecks.checkDirect(data);
/* 43:43 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_fog_coord_glFogCoordPointerEXT_data = data;
/* 44:44 */    nglFogCoordPointerEXT(5130, stride, MemoryUtil.getAddress(data), function_pointer);
/* 45:   */  }
/* 46:   */  
/* 47:47 */  public static void glFogCoordPointerEXT(int stride, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 48:48 */    long function_pointer = caps.glFogCoordPointerEXT;
/* 49:49 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 50:50 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 51:51 */    BufferChecks.checkDirect(data);
/* 52:52 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_fog_coord_glFogCoordPointerEXT_data = data;
/* 53:53 */    nglFogCoordPointerEXT(5126, stride, MemoryUtil.getAddress(data), function_pointer); }
/* 54:   */  
/* 55:   */  static native void nglFogCoordPointerEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 56:   */  
/* 57:57 */  public static void glFogCoordPointerEXT(int type, int stride, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 58:58 */    long function_pointer = caps.glFogCoordPointerEXT;
/* 59:59 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 60:60 */    GLChecks.ensureArrayVBOenabled(caps);
/* 61:61 */    nglFogCoordPointerEXTBO(type, stride, data_buffer_offset, function_pointer);
/* 62:   */  }
/* 63:   */  
/* 64:   */  static native void nglFogCoordPointerEXTBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTFogCoord
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */