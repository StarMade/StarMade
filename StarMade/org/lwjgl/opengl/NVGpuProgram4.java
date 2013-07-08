/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  12:    */public final class NVGpuProgram4
/*  13:    */{
/*  14:    */  public static final int GL_PROGRAM_ATTRIB_COMPONENTS_NV = 35078;
/*  15:    */  public static final int GL_PROGRAM_RESULT_COMPONENTS_NV = 35079;
/*  16:    */  public static final int GL_MAX_PROGRAM_ATTRIB_COMPONENTS_NV = 35080;
/*  17:    */  public static final int GL_MAX_PROGRAM_RESULT_COMPONENTS_NV = 35081;
/*  18:    */  public static final int GL_MAX_PROGRAM_GENERIC_ATTRIBS_NV = 36261;
/*  19:    */  public static final int GL_MAX_PROGRAM_GENERIC_RESULTS_NV = 36262;
/*  20:    */  
/*  21:    */  public static void glProgramLocalParameterI4iNV(int target, int index, int x, int y, int z, int w)
/*  22:    */  {
/*  23: 23 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  24: 24 */    long function_pointer = caps.glProgramLocalParameterI4iNV;
/*  25: 25 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  26: 26 */    nglProgramLocalParameterI4iNV(target, index, x, y, z, w, function_pointer);
/*  27:    */  }
/*  28:    */  
/*  29:    */  static native void nglProgramLocalParameterI4iNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  30:    */  
/*  31: 31 */  public static void glProgramLocalParameterI4NV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  32: 32 */    long function_pointer = caps.glProgramLocalParameterI4ivNV;
/*  33: 33 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  34: 34 */    BufferChecks.checkBuffer(params, 4);
/*  35: 35 */    nglProgramLocalParameterI4ivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*  36:    */  }
/*  37:    */  
/*  38:    */  static native void nglProgramLocalParameterI4ivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  39:    */  
/*  40: 40 */  public static void glProgramLocalParametersI4NV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  41: 41 */    long function_pointer = caps.glProgramLocalParametersI4ivNV;
/*  42: 42 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  43: 43 */    BufferChecks.checkDirect(params);
/*  44: 44 */    nglProgramLocalParametersI4ivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*  45:    */  }
/*  46:    */  
/*  47:    */  static native void nglProgramLocalParametersI4ivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  48:    */  
/*  49: 49 */  public static void glProgramLocalParameterI4uiNV(int target, int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  50: 50 */    long function_pointer = caps.glProgramLocalParameterI4uiNV;
/*  51: 51 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  52: 52 */    nglProgramLocalParameterI4uiNV(target, index, x, y, z, w, function_pointer);
/*  53:    */  }
/*  54:    */  
/*  55:    */  static native void nglProgramLocalParameterI4uiNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  56:    */  
/*  57: 57 */  public static void glProgramLocalParameterI4uNV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  58: 58 */    long function_pointer = caps.glProgramLocalParameterI4uivNV;
/*  59: 59 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  60: 60 */    BufferChecks.checkBuffer(params, 4);
/*  61: 61 */    nglProgramLocalParameterI4uivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*  62:    */  }
/*  63:    */  
/*  64:    */  static native void nglProgramLocalParameterI4uivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  65:    */  
/*  66: 66 */  public static void glProgramLocalParametersI4uNV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  67: 67 */    long function_pointer = caps.glProgramLocalParametersI4uivNV;
/*  68: 68 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  69: 69 */    BufferChecks.checkDirect(params);
/*  70: 70 */    nglProgramLocalParametersI4uivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*  71:    */  }
/*  72:    */  
/*  73:    */  static native void nglProgramLocalParametersI4uivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  74:    */  
/*  75: 75 */  public static void glProgramEnvParameterI4iNV(int target, int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  76: 76 */    long function_pointer = caps.glProgramEnvParameterI4iNV;
/*  77: 77 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  78: 78 */    nglProgramEnvParameterI4iNV(target, index, x, y, z, w, function_pointer);
/*  79:    */  }
/*  80:    */  
/*  81:    */  static native void nglProgramEnvParameterI4iNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  82:    */  
/*  83: 83 */  public static void glProgramEnvParameterI4NV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  84: 84 */    long function_pointer = caps.glProgramEnvParameterI4ivNV;
/*  85: 85 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  86: 86 */    BufferChecks.checkBuffer(params, 4);
/*  87: 87 */    nglProgramEnvParameterI4ivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglProgramEnvParameterI4ivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  91:    */  
/*  92: 92 */  public static void glProgramEnvParametersI4NV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  93: 93 */    long function_pointer = caps.glProgramEnvParametersI4ivNV;
/*  94: 94 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  95: 95 */    BufferChecks.checkDirect(params);
/*  96: 96 */    nglProgramEnvParametersI4ivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*  97:    */  }
/*  98:    */  
/*  99:    */  static native void nglProgramEnvParametersI4ivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 100:    */  
/* 101:101 */  public static void glProgramEnvParameterI4uiNV(int target, int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glProgramEnvParameterI4uiNV;
/* 103:103 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    nglProgramEnvParameterI4uiNV(target, index, x, y, z, w, function_pointer);
/* 105:    */  }
/* 106:    */  
/* 107:    */  static native void nglProgramEnvParameterI4uiNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 108:    */  
/* 109:109 */  public static void glProgramEnvParameterI4uNV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 110:110 */    long function_pointer = caps.glProgramEnvParameterI4uivNV;
/* 111:111 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 112:112 */    BufferChecks.checkBuffer(params, 4);
/* 113:113 */    nglProgramEnvParameterI4uivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 114:    */  }
/* 115:    */  
/* 116:    */  static native void nglProgramEnvParameterI4uivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 117:    */  
/* 118:118 */  public static void glProgramEnvParametersI4uNV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 119:119 */    long function_pointer = caps.glProgramEnvParametersI4uivNV;
/* 120:120 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 121:121 */    BufferChecks.checkDirect(params);
/* 122:122 */    nglProgramEnvParametersI4uivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 123:    */  }
/* 124:    */  
/* 125:    */  static native void nglProgramEnvParametersI4uivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 126:    */  
/* 127:127 */  public static void glGetProgramLocalParameterINV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 128:128 */    long function_pointer = caps.glGetProgramLocalParameterIivNV;
/* 129:129 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 130:130 */    BufferChecks.checkBuffer(params, 4);
/* 131:131 */    nglGetProgramLocalParameterIivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 132:    */  }
/* 133:    */  
/* 134:    */  static native void nglGetProgramLocalParameterIivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 135:    */  
/* 136:136 */  public static void glGetProgramLocalParameterIuNV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 137:137 */    long function_pointer = caps.glGetProgramLocalParameterIuivNV;
/* 138:138 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 139:139 */    BufferChecks.checkBuffer(params, 4);
/* 140:140 */    nglGetProgramLocalParameterIuivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 141:    */  }
/* 142:    */  
/* 143:    */  static native void nglGetProgramLocalParameterIuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 144:    */  
/* 145:145 */  public static void glGetProgramEnvParameterINV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 146:146 */    long function_pointer = caps.glGetProgramEnvParameterIivNV;
/* 147:147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 148:148 */    BufferChecks.checkBuffer(params, 4);
/* 149:149 */    nglGetProgramEnvParameterIivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 150:    */  }
/* 151:    */  
/* 152:    */  static native void nglGetProgramEnvParameterIivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 153:    */  
/* 154:154 */  public static void glGetProgramEnvParameterIuNV(int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 155:155 */    long function_pointer = caps.glGetProgramEnvParameterIuivNV;
/* 156:156 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 157:157 */    BufferChecks.checkBuffer(params, 4);
/* 158:158 */    nglGetProgramEnvParameterIuivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 159:    */  }
/* 160:    */  
/* 161:    */  static native void nglGetProgramEnvParameterIuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVGpuProgram4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */