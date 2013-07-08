/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.LWJGLUtil;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/*  9:   */public final class EXTVertexWeighting
/* 10:   */{
/* 11:   */  public static final int GL_MODELVIEW0_STACK_DEPTH_EXT = 2979;
/* 12:   */  public static final int GL_MODELVIEW1_STACK_DEPTH_EXT = 34050;
/* 13:   */  public static final int GL_MODELVIEW0_MATRIX_EXT = 2982;
/* 14:   */  public static final int GL_MODELVIEW1_MATRIX_EXT = 34054;
/* 15:   */  public static final int GL_VERTEX_WEIGHTING_EXT = 34057;
/* 16:   */  public static final int GL_MODELVIEW0_EXT = 5888;
/* 17:   */  public static final int GL_MODELVIEW1_EXT = 34058;
/* 18:   */  public static final int GL_CURRENT_VERTEX_WEIGHT_EXT = 34059;
/* 19:   */  public static final int GL_VERTEX_WEIGHT_ARRAY_EXT = 34060;
/* 20:   */  public static final int GL_VERTEX_WEIGHT_ARRAY_SIZE_EXT = 34061;
/* 21:   */  public static final int GL_VERTEX_WEIGHT_ARRAY_TYPE_EXT = 34062;
/* 22:   */  public static final int GL_VERTEX_WEIGHT_ARRAY_STRIDE_EXT = 34063;
/* 23:   */  public static final int GL_VERTEX_WEIGHT_ARRAY_POINTER_EXT = 34064;
/* 24:   */  
/* 25:   */  public static void glVertexWeightfEXT(float weight)
/* 26:   */  {
/* 27:27 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glVertexWeightfEXT;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    nglVertexWeightfEXT(weight, function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native void nglVertexWeightfEXT(float paramFloat, long paramLong);
/* 34:   */  
/* 35:35 */  public static void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 36:36 */    long function_pointer = caps.glVertexWeightPointerEXT;
/* 37:37 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 38:38 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 39:39 */    BufferChecks.checkDirect(pPointer);
/* 40:40 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer = pPointer;
/* 41:41 */    nglVertexWeightPointerEXT(size, 5126, stride, MemoryUtil.getAddress(pPointer), function_pointer); }
/* 42:   */  
/* 43:   */  static native void nglVertexWeightPointerEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 44:   */  
/* 45:45 */  public static void glVertexWeightPointerEXT(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 46:46 */    long function_pointer = caps.glVertexWeightPointerEXT;
/* 47:47 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 48:48 */    GLChecks.ensureArrayVBOenabled(caps);
/* 49:49 */    nglVertexWeightPointerEXTBO(size, type, stride, pPointer_buffer_offset, function_pointer);
/* 50:   */  }
/* 51:   */  
/* 52:   */  static native void nglVertexWeightPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTVertexWeighting
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */