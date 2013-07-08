/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.LongBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*   9:    */public final class NVBindlessTexture
/*  10:    */{
/*  11:    */  public static long glGetTextureHandleNV(int texture)
/*  12:    */  {
/*  13: 13 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  14: 14 */    long function_pointer = caps.glGetTextureHandleNV;
/*  15: 15 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  16: 16 */    long __result = nglGetTextureHandleNV(texture, function_pointer);
/*  17: 17 */    return __result;
/*  18:    */  }
/*  19:    */  
/*  20:    */  static native long nglGetTextureHandleNV(int paramInt, long paramLong);
/*  21:    */  
/*  22: 22 */  public static long glGetTextureSamplerHandleNV(int texture, int sampler) { ContextCapabilities caps = GLContext.getCapabilities();
/*  23: 23 */    long function_pointer = caps.glGetTextureSamplerHandleNV;
/*  24: 24 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  25: 25 */    long __result = nglGetTextureSamplerHandleNV(texture, sampler, function_pointer);
/*  26: 26 */    return __result;
/*  27:    */  }
/*  28:    */  
/*  29:    */  static native long nglGetTextureSamplerHandleNV(int paramInt1, int paramInt2, long paramLong);
/*  30:    */  
/*  31: 31 */  public static void glMakeTextureHandleResidentNV(long handle) { ContextCapabilities caps = GLContext.getCapabilities();
/*  32: 32 */    long function_pointer = caps.glMakeTextureHandleResidentNV;
/*  33: 33 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  34: 34 */    nglMakeTextureHandleResidentNV(handle, function_pointer);
/*  35:    */  }
/*  36:    */  
/*  37:    */  static native void nglMakeTextureHandleResidentNV(long paramLong1, long paramLong2);
/*  38:    */  
/*  39: 39 */  public static void glMakeTextureHandleNonResidentNV(long handle) { ContextCapabilities caps = GLContext.getCapabilities();
/*  40: 40 */    long function_pointer = caps.glMakeTextureHandleNonResidentNV;
/*  41: 41 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  42: 42 */    nglMakeTextureHandleNonResidentNV(handle, function_pointer);
/*  43:    */  }
/*  44:    */  
/*  45:    */  static native void nglMakeTextureHandleNonResidentNV(long paramLong1, long paramLong2);
/*  46:    */  
/*  47: 47 */  public static long glGetImageHandleNV(int texture, int level, boolean layered, int layer, int format) { ContextCapabilities caps = GLContext.getCapabilities();
/*  48: 48 */    long function_pointer = caps.glGetImageHandleNV;
/*  49: 49 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  50: 50 */    long __result = nglGetImageHandleNV(texture, level, layered, layer, format, function_pointer);
/*  51: 51 */    return __result;
/*  52:    */  }
/*  53:    */  
/*  54:    */  static native long nglGetImageHandleNV(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, long paramLong);
/*  55:    */  
/*  56: 56 */  public static void glMakeImageHandleResidentNV(long handle, int access) { ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glMakeImageHandleResidentNV;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    nglMakeImageHandleResidentNV(handle, access, function_pointer);
/*  60:    */  }
/*  61:    */  
/*  62:    */  static native void nglMakeImageHandleResidentNV(long paramLong1, int paramInt, long paramLong2);
/*  63:    */  
/*  64: 64 */  public static void glMakeImageHandleNonResidentNV(long handle) { ContextCapabilities caps = GLContext.getCapabilities();
/*  65: 65 */    long function_pointer = caps.glMakeImageHandleNonResidentNV;
/*  66: 66 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  67: 67 */    nglMakeImageHandleNonResidentNV(handle, function_pointer);
/*  68:    */  }
/*  69:    */  
/*  70:    */  static native void nglMakeImageHandleNonResidentNV(long paramLong1, long paramLong2);
/*  71:    */  
/*  72: 72 */  public static void glUniformHandleui64NV(int location, long value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  73: 73 */    long function_pointer = caps.glUniformHandleui64NV;
/*  74: 74 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  75: 75 */    nglUniformHandleui64NV(location, value, function_pointer);
/*  76:    */  }
/*  77:    */  
/*  78:    */  static native void nglUniformHandleui64NV(int paramInt, long paramLong1, long paramLong2);
/*  79:    */  
/*  80: 80 */  public static void glUniformHandleuNV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  81: 81 */    long function_pointer = caps.glUniformHandleui64vNV;
/*  82: 82 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  83: 83 */    BufferChecks.checkDirect(value);
/*  84: 84 */    nglUniformHandleui64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  85:    */  }
/*  86:    */  
/*  87:    */  static native void nglUniformHandleui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  88:    */  
/*  89: 89 */  public static void glProgramUniformHandleui64NV(int program, int location, long value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  90: 90 */    long function_pointer = caps.glProgramUniformHandleui64NV;
/*  91: 91 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  92: 92 */    nglProgramUniformHandleui64NV(program, location, value, function_pointer);
/*  93:    */  }
/*  94:    */  
/*  95:    */  static native void nglProgramUniformHandleui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  96:    */  
/*  97: 97 */  public static void glProgramUniformHandleuNV(int program, int location, LongBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  98: 98 */    long function_pointer = caps.glProgramUniformHandleui64vNV;
/*  99: 99 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 100:100 */    BufferChecks.checkDirect(values);
/* 101:101 */    nglProgramUniformHandleui64vNV(program, location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/* 102:    */  }
/* 103:    */  
/* 104:    */  static native void nglProgramUniformHandleui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 105:    */  
/* 106:106 */  public static boolean glIsTextureHandleResidentNV(long handle) { ContextCapabilities caps = GLContext.getCapabilities();
/* 107:107 */    long function_pointer = caps.glIsTextureHandleResidentNV;
/* 108:108 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 109:109 */    boolean __result = nglIsTextureHandleResidentNV(handle, function_pointer);
/* 110:110 */    return __result;
/* 111:    */  }
/* 112:    */  
/* 113:    */  static native boolean nglIsTextureHandleResidentNV(long paramLong1, long paramLong2);
/* 114:    */  
/* 115:115 */  public static boolean glIsImageHandleResidentNV(long handle) { ContextCapabilities caps = GLContext.getCapabilities();
/* 116:116 */    long function_pointer = caps.glIsImageHandleResidentNV;
/* 117:117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 118:118 */    boolean __result = nglIsImageHandleResidentNV(handle, function_pointer);
/* 119:119 */    return __result;
/* 120:    */  }
/* 121:    */  
/* 122:    */  static native boolean nglIsImageHandleResidentNV(long paramLong1, long paramLong2);
/* 123:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVBindlessTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */