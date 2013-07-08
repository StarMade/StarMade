/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 18:   */public final class NVGeometryProgram4
/* 19:   */{
/* 20:   */  public static final int GL_GEOMETRY_PROGRAM_NV = 35878;
/* 21:   */  public static final int GL_MAX_PROGRAM_OUTPUT_VERTICES_NV = 35879;
/* 22:   */  public static final int GL_MAX_PROGRAM_TOTAL_OUTPUT_COMPONENTS_NV = 35880;
/* 23:   */  
/* 24:   */  public static void glProgramVertexLimitNV(int target, int limit)
/* 25:   */  {
/* 26:26 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glProgramVertexLimitNV;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglProgramVertexLimitNV(target, limit, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglProgramVertexLimitNV(int paramInt1, int paramInt2, long paramLong);
/* 33:   */  
/* 34:34 */  public static void glFramebufferTextureEXT(int target, int attachment, int texture, int level) { EXTGeometryShader4.glFramebufferTextureEXT(target, attachment, texture, level); }
/* 35:   */  
/* 36:   */  public static void glFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer)
/* 37:   */  {
/* 38:38 */    EXTGeometryShader4.glFramebufferTextureLayerEXT(target, attachment, texture, level, layer);
/* 39:   */  }
/* 40:   */  
/* 41:   */  public static void glFramebufferTextureFaceEXT(int target, int attachment, int texture, int level, int face) {
/* 42:42 */    EXTGeometryShader4.glFramebufferTextureFaceEXT(target, attachment, texture, level, face);
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVGeometryProgram4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */