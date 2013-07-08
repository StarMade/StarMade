/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  32:    */public final class GL21
/*  33:    */{
/*  34:    */  public static final int GL_FLOAT_MAT2x3 = 35685;
/*  35:    */  public static final int GL_FLOAT_MAT2x4 = 35686;
/*  36:    */  public static final int GL_FLOAT_MAT3x2 = 35687;
/*  37:    */  public static final int GL_FLOAT_MAT3x4 = 35688;
/*  38:    */  public static final int GL_FLOAT_MAT4x2 = 35689;
/*  39:    */  public static final int GL_FLOAT_MAT4x3 = 35690;
/*  40:    */  public static final int GL_PIXEL_PACK_BUFFER = 35051;
/*  41:    */  public static final int GL_PIXEL_UNPACK_BUFFER = 35052;
/*  42:    */  public static final int GL_PIXEL_PACK_BUFFER_BINDING = 35053;
/*  43:    */  public static final int GL_PIXEL_UNPACK_BUFFER_BINDING = 35055;
/*  44:    */  public static final int GL_SRGB = 35904;
/*  45:    */  public static final int GL_SRGB8 = 35905;
/*  46:    */  public static final int GL_SRGB_ALPHA = 35906;
/*  47:    */  public static final int GL_SRGB8_ALPHA8 = 35907;
/*  48:    */  public static final int GL_SLUMINANCE_ALPHA = 35908;
/*  49:    */  public static final int GL_SLUMINANCE8_ALPHA8 = 35909;
/*  50:    */  public static final int GL_SLUMINANCE = 35910;
/*  51:    */  public static final int GL_SLUMINANCE8 = 35911;
/*  52:    */  public static final int GL_COMPRESSED_SRGB = 35912;
/*  53:    */  public static final int GL_COMPRESSED_SRGB_ALPHA = 35913;
/*  54:    */  public static final int GL_COMPRESSED_SLUMINANCE = 35914;
/*  55:    */  public static final int GL_COMPRESSED_SLUMINANCE_ALPHA = 35915;
/*  56:    */  public static final int GL_CURRENT_RASTER_SECONDARY_COLOR = 33887;
/*  57:    */  
/*  58:    */  public static void glUniformMatrix2x3(int location, boolean transpose, FloatBuffer matrices)
/*  59:    */  {
/*  60: 60 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  61: 61 */    long function_pointer = caps.glUniformMatrix2x3fv;
/*  62: 62 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  63: 63 */    BufferChecks.checkDirect(matrices);
/*  64: 64 */    nglUniformMatrix2x3fv(location, matrices.remaining() / 6, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  65:    */  }
/*  66:    */  
/*  67:    */  static native void nglUniformMatrix2x3fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  68:    */  
/*  69: 69 */  public static void glUniformMatrix3x2(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  70: 70 */    long function_pointer = caps.glUniformMatrix3x2fv;
/*  71: 71 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  72: 72 */    BufferChecks.checkDirect(matrices);
/*  73: 73 */    nglUniformMatrix3x2fv(location, matrices.remaining() / 6, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  static native void nglUniformMatrix3x2fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  77:    */  
/*  78: 78 */  public static void glUniformMatrix2x4(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  79: 79 */    long function_pointer = caps.glUniformMatrix2x4fv;
/*  80: 80 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  81: 81 */    BufferChecks.checkDirect(matrices);
/*  82: 82 */    nglUniformMatrix2x4fv(location, matrices.remaining() >> 3, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  83:    */  }
/*  84:    */  
/*  85:    */  static native void nglUniformMatrix2x4fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  86:    */  
/*  87: 87 */  public static void glUniformMatrix4x2(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  88: 88 */    long function_pointer = caps.glUniformMatrix4x2fv;
/*  89: 89 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  90: 90 */    BufferChecks.checkDirect(matrices);
/*  91: 91 */    nglUniformMatrix4x2fv(location, matrices.remaining() >> 3, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  92:    */  }
/*  93:    */  
/*  94:    */  static native void nglUniformMatrix4x2fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  95:    */  
/*  96: 96 */  public static void glUniformMatrix3x4(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  97: 97 */    long function_pointer = caps.glUniformMatrix3x4fv;
/*  98: 98 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  99: 99 */    BufferChecks.checkDirect(matrices);
/* 100:100 */    nglUniformMatrix3x4fv(location, matrices.remaining() / 12, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/* 101:    */  }
/* 102:    */  
/* 103:    */  static native void nglUniformMatrix3x4fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 104:    */  
/* 105:105 */  public static void glUniformMatrix4x3(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 106:106 */    long function_pointer = caps.glUniformMatrix4x3fv;
/* 107:107 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 108:108 */    BufferChecks.checkDirect(matrices);
/* 109:109 */    nglUniformMatrix4x3fv(location, matrices.remaining() / 12, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/* 110:    */  }
/* 111:    */  
/* 112:    */  static native void nglUniformMatrix4x3fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 113:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL21
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */