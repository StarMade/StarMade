/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 44:   */public final class EXTGeometryShader4
/* 45:   */{
/* 46:   */  public static final int GL_GEOMETRY_SHADER_EXT = 36313;
/* 47:   */  public static final int GL_GEOMETRY_VERTICES_OUT_EXT = 36314;
/* 48:   */  public static final int GL_GEOMETRY_INPUT_TYPE_EXT = 36315;
/* 49:   */  public static final int GL_GEOMETRY_OUTPUT_TYPE_EXT = 36316;
/* 50:   */  public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS_EXT = 35881;
/* 51:   */  public static final int GL_MAX_GEOMETRY_VARYING_COMPONENTS_EXT = 36317;
/* 52:   */  public static final int GL_MAX_VERTEX_VARYING_COMPONENTS_EXT = 36318;
/* 53:   */  public static final int GL_MAX_VARYING_COMPONENTS_EXT = 35659;
/* 54:   */  public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS_EXT = 36319;
/* 55:   */  public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES_EXT = 36320;
/* 56:   */  public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS_EXT = 36321;
/* 57:   */  public static final int GL_LINES_ADJACENCY_EXT = 10;
/* 58:   */  public static final int GL_LINE_STRIP_ADJACENCY_EXT = 11;
/* 59:   */  public static final int GL_TRIANGLES_ADJACENCY_EXT = 12;
/* 60:   */  public static final int GL_TRIANGLE_STRIP_ADJACENCY_EXT = 13;
/* 61:   */  public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS_EXT = 36264;
/* 62:   */  public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_COUNT_EXT = 36265;
/* 63:   */  public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED_EXT = 36263;
/* 64:   */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER_EXT = 36052;
/* 65:   */  public static final int GL_PROGRAM_POINT_SIZE_EXT = 34370;
/* 66:   */  
/* 67:   */  public static void glProgramParameteriEXT(int program, int pname, int value)
/* 68:   */  {
/* 69:69 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    long function_pointer = caps.glProgramParameteriEXT;
/* 71:71 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 72:72 */    nglProgramParameteriEXT(program, pname, value, function_pointer);
/* 73:   */  }
/* 74:   */  
/* 75:   */  static native void nglProgramParameteriEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 76:   */  
/* 77:77 */  public static void glFramebufferTextureEXT(int target, int attachment, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 78:78 */    long function_pointer = caps.glFramebufferTextureEXT;
/* 79:79 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 80:80 */    nglFramebufferTextureEXT(target, attachment, texture, level, function_pointer);
/* 81:   */  }
/* 82:   */  
/* 83:   */  static native void nglFramebufferTextureEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 84:   */  
/* 85:85 */  public static void glFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 86:86 */    long function_pointer = caps.glFramebufferTextureLayerEXT;
/* 87:87 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 88:88 */    nglFramebufferTextureLayerEXT(target, attachment, texture, level, layer, function_pointer);
/* 89:   */  }
/* 90:   */  
/* 91:   */  static native void nglFramebufferTextureLayerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 92:   */  
/* 93:93 */  public static void glFramebufferTextureFaceEXT(int target, int attachment, int texture, int level, int face) { ContextCapabilities caps = GLContext.getCapabilities();
/* 94:94 */    long function_pointer = caps.glFramebufferTextureFaceEXT;
/* 95:95 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 96:96 */    nglFramebufferTextureFaceEXT(target, attachment, texture, level, face, function_pointer);
/* 97:   */  }
/* 98:   */  
/* 99:   */  static native void nglFramebufferTextureFaceEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 100:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTGeometryShader4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */