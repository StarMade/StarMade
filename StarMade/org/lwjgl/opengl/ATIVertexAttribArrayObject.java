/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/*  9:   */public final class ATIVertexAttribArrayObject
/* 10:   */{
/* 11:   */  public static void glVertexAttribArrayObjectATI(int index, int size, int type, boolean normalized, int stride, int buffer, int offset)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glVertexAttribArrayObjectATI;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglVertexAttribArrayObjectATI(index, size, type, normalized, stride, buffer, offset, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglVertexAttribArrayObjectATI(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 20:   */  
/* 21:21 */  public static void glGetVertexAttribArrayObjectATI(int index, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glGetVertexAttribArrayObjectfvATI;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    BufferChecks.checkBuffer(params, 4);
/* 25:25 */    nglGetVertexAttribArrayObjectfvATI(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 26:   */  }
/* 27:   */  
/* 28:   */  static native void nglGetVertexAttribArrayObjectfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 29:   */  
/* 30:30 */  public static void glGetVertexAttribArrayObjectATI(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glGetVertexAttribArrayObjectivATI;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    BufferChecks.checkBuffer(params, 4);
/* 34:34 */    nglGetVertexAttribArrayObjectivATI(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 35:   */  }
/* 36:   */  
/* 37:   */  static native void nglGetVertexAttribArrayObjectivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIVertexAttribArrayObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */