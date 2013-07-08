/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 44:   */public final class ARBGeometryShader4
/* 45:   */{
/* 46:   */  public static final int GL_GEOMETRY_SHADER_ARB = 36313;
/* 47:   */  public static final int GL_GEOMETRY_VERTICES_OUT_ARB = 36314;
/* 48:   */  public static final int GL_GEOMETRY_INPUT_TYPE_ARB = 36315;
/* 49:   */  public static final int GL_GEOMETRY_OUTPUT_TYPE_ARB = 36316;
/* 50:   */  public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS_ARB = 35881;
/* 51:   */  public static final int GL_MAX_GEOMETRY_VARYING_COMPONENTS_ARB = 36317;
/* 52:   */  public static final int GL_MAX_VERTEX_VARYING_COMPONENTS_ARB = 36318;
/* 53:   */  public static final int GL_MAX_VARYING_COMPONENTS_ARB = 35659;
/* 54:   */  public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS_ARB = 36319;
/* 55:   */  public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES_ARB = 36320;
/* 56:   */  public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS_ARB = 36321;
/* 57:   */  public static final int GL_LINES_ADJACENCY_ARB = 10;
/* 58:   */  public static final int GL_LINE_STRIP_ADJACENCY_ARB = 11;
/* 59:   */  public static final int GL_TRIANGLES_ADJACENCY_ARB = 12;
/* 60:   */  public static final int GL_TRIANGLE_STRIP_ADJACENCY_ARB = 13;
/* 61:   */  public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS_ARB = 36264;
/* 62:   */  public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_COUNT_ARB = 36265;
/* 63:   */  public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED_ARB = 36263;
/* 64:   */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER_ARB = 36052;
/* 65:   */  public static final int GL_PROGRAM_POINT_SIZE_ARB = 34370;
/* 66:   */  
/* 67:   */  public static void glProgramParameteriARB(int program, int pname, int value)
/* 68:   */  {
/* 69:69 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    long function_pointer = caps.glProgramParameteriARB;
/* 71:71 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 72:72 */    nglProgramParameteriARB(program, pname, value, function_pointer);
/* 73:   */  }
/* 74:   */  
/* 75:   */  static native void nglProgramParameteriARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 76:   */  
/* 77:77 */  public static void glFramebufferTextureARB(int target, int attachment, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 78:78 */    long function_pointer = caps.glFramebufferTextureARB;
/* 79:79 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 80:80 */    nglFramebufferTextureARB(target, attachment, texture, level, function_pointer);
/* 81:   */  }
/* 82:   */  
/* 83:   */  static native void nglFramebufferTextureARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 84:   */  
/* 85:85 */  public static void glFramebufferTextureLayerARB(int target, int attachment, int texture, int level, int layer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 86:86 */    long function_pointer = caps.glFramebufferTextureLayerARB;
/* 87:87 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 88:88 */    nglFramebufferTextureLayerARB(target, attachment, texture, level, layer, function_pointer);
/* 89:   */  }
/* 90:   */  
/* 91:   */  static native void nglFramebufferTextureLayerARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 92:   */  
/* 93:93 */  public static void glFramebufferTextureFaceARB(int target, int attachment, int texture, int level, int face) { ContextCapabilities caps = GLContext.getCapabilities();
/* 94:94 */    long function_pointer = caps.glFramebufferTextureFaceARB;
/* 95:95 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 96:96 */    nglFramebufferTextureFaceARB(target, attachment, texture, level, face, function_pointer);
/* 97:   */  }
/* 98:   */  
/* 99:   */  static native void nglFramebufferTextureFaceARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 100:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBGeometryShader4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */