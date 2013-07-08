/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.DoubleBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */
/* 12:   */public final class ARBVertexAttrib64bit
/* 13:   */{
/* 14:   */  public static final int GL_DOUBLE_VEC2 = 36860;
/* 15:   */  public static final int GL_DOUBLE_VEC3 = 36861;
/* 16:   */  public static final int GL_DOUBLE_VEC4 = 36862;
/* 17:   */  public static final int GL_DOUBLE_MAT2 = 36678;
/* 18:   */  public static final int GL_DOUBLE_MAT3 = 36679;
/* 19:   */  public static final int GL_DOUBLE_MAT4 = 36680;
/* 20:   */  public static final int GL_DOUBLE_MAT2x3 = 36681;
/* 21:   */  public static final int GL_DOUBLE_MAT2x4 = 36682;
/* 22:   */  public static final int GL_DOUBLE_MAT3x2 = 36683;
/* 23:   */  public static final int GL_DOUBLE_MAT3x4 = 36684;
/* 24:   */  public static final int GL_DOUBLE_MAT4x2 = 36685;
/* 25:   */  public static final int GL_DOUBLE_MAT4x3 = 36686;
/* 26:   */  
/* 27:   */  public static void glVertexAttribL1d(int index, double x)
/* 28:   */  {
/* 29:29 */    GL41.glVertexAttribL1d(index, x);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public static void glVertexAttribL2d(int index, double x, double y) {
/* 33:33 */    GL41.glVertexAttribL2d(index, x, y);
/* 34:   */  }
/* 35:   */  
/* 36:   */  public static void glVertexAttribL3d(int index, double x, double y, double z) {
/* 37:37 */    GL41.glVertexAttribL3d(index, x, y, z);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static void glVertexAttribL4d(int index, double x, double y, double z, double w) {
/* 41:41 */    GL41.glVertexAttribL4d(index, x, y, z, w);
/* 42:   */  }
/* 43:   */  
/* 44:   */  public static void glVertexAttribL1(int index, DoubleBuffer v) {
/* 45:45 */    GL41.glVertexAttribL1(index, v);
/* 46:   */  }
/* 47:   */  
/* 48:   */  public static void glVertexAttribL2(int index, DoubleBuffer v) {
/* 49:49 */    GL41.glVertexAttribL2(index, v);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public static void glVertexAttribL3(int index, DoubleBuffer v) {
/* 53:53 */    GL41.glVertexAttribL3(index, v);
/* 54:   */  }
/* 55:   */  
/* 56:   */  public static void glVertexAttribL4(int index, DoubleBuffer v) {
/* 57:57 */    GL41.glVertexAttribL4(index, v);
/* 58:   */  }
/* 59:   */  
/* 60:   */  public static void glVertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) {
/* 61:61 */    GL41.glVertexAttribLPointer(index, size, stride, pointer);
/* 62:   */  }
/* 63:   */  
/* 64:64 */  public static void glVertexAttribLPointer(int index, int size, int stride, long pointer_buffer_offset) { GL41.glVertexAttribLPointer(index, size, stride, pointer_buffer_offset); }
/* 65:   */  
/* 66:   */  public static void glGetVertexAttribL(int index, int pname, DoubleBuffer params)
/* 67:   */  {
/* 68:68 */    GL41.glGetVertexAttribL(index, pname, params);
/* 69:   */  }
/* 70:   */  
/* 71:   */  public static void glVertexArrayVertexAttribLOffsetEXT(int vaobj, int buffer, int index, int size, int type, int stride, long offset) {
/* 72:72 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 73:73 */    long function_pointer = caps.glVertexArrayVertexAttribLOffsetEXT;
/* 74:74 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 75:75 */    nglVertexArrayVertexAttribLOffsetEXT(vaobj, buffer, index, size, type, stride, offset, function_pointer);
/* 76:   */  }
/* 77:   */  
/* 78:   */  static native void nglVertexArrayVertexAttribLOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexAttrib64bit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */