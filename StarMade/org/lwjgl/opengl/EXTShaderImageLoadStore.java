/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 25:   */public final class EXTShaderImageLoadStore
/* 26:   */{
/* 27:   */  public static final int GL_MAX_IMAGE_UNITS_EXT = 36664;
/* 28:   */  public static final int GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS_EXT = 36665;
/* 29:   */  public static final int GL_MAX_IMAGE_SAMPLES_EXT = 36973;
/* 30:   */  public static final int GL_IMAGE_BINDING_NAME_EXT = 36666;
/* 31:   */  public static final int GL_IMAGE_BINDING_LEVEL_EXT = 36667;
/* 32:   */  public static final int GL_IMAGE_BINDING_LAYERED_EXT = 36668;
/* 33:   */  public static final int GL_IMAGE_BINDING_LAYER_EXT = 36669;
/* 34:   */  public static final int GL_IMAGE_BINDING_ACCESS_EXT = 36670;
/* 35:   */  public static final int GL_IMAGE_BINDING_FORMAT_EXT = 36974;
/* 36:   */  public static final int GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT_EXT = 1;
/* 37:   */  public static final int GL_ELEMENT_ARRAY_BARRIER_BIT_EXT = 2;
/* 38:   */  public static final int GL_UNIFORM_BARRIER_BIT_EXT = 4;
/* 39:   */  public static final int GL_TEXTURE_FETCH_BARRIER_BIT_EXT = 8;
/* 40:   */  public static final int GL_SHADER_IMAGE_ACCESS_BARRIER_BIT_EXT = 32;
/* 41:   */  public static final int GL_COMMAND_BARRIER_BIT_EXT = 64;
/* 42:   */  public static final int GL_PIXEL_BUFFER_BARRIER_BIT_EXT = 128;
/* 43:   */  public static final int GL_TEXTURE_UPDATE_BARRIER_BIT_EXT = 256;
/* 44:   */  public static final int GL_BUFFER_UPDATE_BARRIER_BIT_EXT = 512;
/* 45:   */  public static final int GL_FRAMEBUFFER_BARRIER_BIT_EXT = 1024;
/* 46:   */  public static final int GL_TRANSFORM_FEEDBACK_BARRIER_BIT_EXT = 2048;
/* 47:   */  public static final int GL_ATOMIC_COUNTER_BARRIER_BIT_EXT = 4096;
/* 48:   */  public static final int GL_ALL_BARRIER_BITS_EXT = -1;
/* 49:   */  public static final int GL_IMAGE_1D_EXT = 36940;
/* 50:   */  public static final int GL_IMAGE_2D_EXT = 36941;
/* 51:   */  public static final int GL_IMAGE_3D_EXT = 36942;
/* 52:   */  public static final int GL_IMAGE_2D_RECT_EXT = 36943;
/* 53:   */  public static final int GL_IMAGE_CUBE_EXT = 36944;
/* 54:   */  public static final int GL_IMAGE_BUFFER_EXT = 36945;
/* 55:   */  public static final int GL_IMAGE_1D_ARRAY_EXT = 36946;
/* 56:   */  public static final int GL_IMAGE_2D_ARRAY_EXT = 36947;
/* 57:   */  public static final int GL_IMAGE_CUBE_MAP_ARRAY_EXT = 36948;
/* 58:   */  public static final int GL_IMAGE_2D_MULTISAMPLE_EXT = 36949;
/* 59:   */  public static final int GL_IMAGE_2D_MULTISAMPLE_ARRAY_EXT = 36950;
/* 60:   */  public static final int GL_INT_IMAGE_1D_EXT = 36951;
/* 61:   */  public static final int GL_INT_IMAGE_2D_EXT = 36952;
/* 62:   */  public static final int GL_INT_IMAGE_3D_EXT = 36953;
/* 63:   */  public static final int GL_INT_IMAGE_2D_RECT_EXT = 36954;
/* 64:   */  public static final int GL_INT_IMAGE_CUBE_EXT = 36955;
/* 65:   */  public static final int GL_INT_IMAGE_BUFFER_EXT = 36956;
/* 66:   */  public static final int GL_INT_IMAGE_1D_ARRAY_EXT = 36957;
/* 67:   */  public static final int GL_INT_IMAGE_2D_ARRAY_EXT = 36958;
/* 68:   */  public static final int GL_INT_IMAGE_CUBE_MAP_ARRAY_EXT = 36959;
/* 69:   */  public static final int GL_INT_IMAGE_2D_MULTISAMPLE_EXT = 36960;
/* 70:   */  public static final int GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY_EXT = 36961;
/* 71:   */  public static final int GL_UNSIGNED_INT_IMAGE_1D_EXT = 36962;
/* 72:   */  public static final int GL_UNSIGNED_INT_IMAGE_2D_EXT = 36963;
/* 73:   */  public static final int GL_UNSIGNED_INT_IMAGE_3D_EXT = 36964;
/* 74:   */  public static final int GL_UNSIGNED_INT_IMAGE_2D_RECT_EXT = 36965;
/* 75:   */  public static final int GL_UNSIGNED_INT_IMAGE_CUBE_EXT = 36966;
/* 76:   */  public static final int GL_UNSIGNED_INT_IMAGE_BUFFER_EXT = 36967;
/* 77:   */  public static final int GL_UNSIGNED_INT_IMAGE_1D_ARRAY_EXT = 36968;
/* 78:   */  public static final int GL_UNSIGNED_INT_IMAGE_2D_ARRAY_EXT = 36969;
/* 79:   */  public static final int GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY_EXT = 36970;
/* 80:   */  public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_EXT = 36971;
/* 81:   */  public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY_EXT = 36972;
/* 82:   */  
/* 83:   */  public static void glBindImageTextureEXT(int index, int texture, int level, boolean layered, int layer, int access, int format)
/* 84:   */  {
/* 85:85 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 86:86 */    long function_pointer = caps.glBindImageTextureEXT;
/* 87:87 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 88:88 */    nglBindImageTextureEXT(index, texture, level, layered, layer, access, format, function_pointer);
/* 89:   */  }
/* 90:   */  
/* 91:   */  static native void nglBindImageTextureEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 92:   */  
/* 93:93 */  public static void glMemoryBarrierEXT(int barriers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 94:94 */    long function_pointer = caps.glMemoryBarrierEXT;
/* 95:95 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 96:96 */    nglMemoryBarrierEXT(barriers, function_pointer);
/* 97:   */  }
/* 98:   */  
/* 99:   */  static native void nglMemoryBarrierEXT(int paramInt, long paramLong);
/* 100:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTShaderImageLoadStore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */