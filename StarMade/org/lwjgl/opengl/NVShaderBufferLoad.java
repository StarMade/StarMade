/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.LongBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  21:    */public final class NVShaderBufferLoad
/*  22:    */{
/*  23:    */  public static final int GL_BUFFER_GPU_ADDRESS_NV = 36637;
/*  24:    */  public static final int GL_GPU_ADDRESS_NV = 36660;
/*  25:    */  public static final int GL_MAX_SHADER_BUFFER_ADDRESS_NV = 36661;
/*  26:    */  
/*  27:    */  public static void glMakeBufferResidentNV(int target, int access)
/*  28:    */  {
/*  29: 29 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  30: 30 */    long function_pointer = caps.glMakeBufferResidentNV;
/*  31: 31 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  32: 32 */    nglMakeBufferResidentNV(target, access, function_pointer);
/*  33:    */  }
/*  34:    */  
/*  35:    */  static native void nglMakeBufferResidentNV(int paramInt1, int paramInt2, long paramLong);
/*  36:    */  
/*  37: 37 */  public static void glMakeBufferNonResidentNV(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/*  38: 38 */    long function_pointer = caps.glMakeBufferNonResidentNV;
/*  39: 39 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  40: 40 */    nglMakeBufferNonResidentNV(target, function_pointer);
/*  41:    */  }
/*  42:    */  
/*  43:    */  static native void nglMakeBufferNonResidentNV(int paramInt, long paramLong);
/*  44:    */  
/*  45: 45 */  public static boolean glIsBufferResidentNV(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/*  46: 46 */    long function_pointer = caps.glIsBufferResidentNV;
/*  47: 47 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  48: 48 */    boolean __result = nglIsBufferResidentNV(target, function_pointer);
/*  49: 49 */    return __result;
/*  50:    */  }
/*  51:    */  
/*  52:    */  static native boolean nglIsBufferResidentNV(int paramInt, long paramLong);
/*  53:    */  
/*  54: 54 */  public static void glMakeNamedBufferResidentNV(int buffer, int access) { ContextCapabilities caps = GLContext.getCapabilities();
/*  55: 55 */    long function_pointer = caps.glMakeNamedBufferResidentNV;
/*  56: 56 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  57: 57 */    nglMakeNamedBufferResidentNV(buffer, access, function_pointer);
/*  58:    */  }
/*  59:    */  
/*  60:    */  static native void nglMakeNamedBufferResidentNV(int paramInt1, int paramInt2, long paramLong);
/*  61:    */  
/*  62: 62 */  public static void glMakeNamedBufferNonResidentNV(int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  63: 63 */    long function_pointer = caps.glMakeNamedBufferNonResidentNV;
/*  64: 64 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  65: 65 */    nglMakeNamedBufferNonResidentNV(buffer, function_pointer);
/*  66:    */  }
/*  67:    */  
/*  68:    */  static native void nglMakeNamedBufferNonResidentNV(int paramInt, long paramLong);
/*  69:    */  
/*  70: 70 */  public static boolean glIsNamedBufferResidentNV(int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glIsNamedBufferResidentNV;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    boolean __result = nglIsNamedBufferResidentNV(buffer, function_pointer);
/*  74: 74 */    return __result;
/*  75:    */  }
/*  76:    */  
/*  77:    */  static native boolean nglIsNamedBufferResidentNV(int paramInt, long paramLong);
/*  78:    */  
/*  79: 79 */  public static void glGetBufferParameteruNV(int target, int pname, LongBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glGetBufferParameterui64vNV;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    BufferChecks.checkBuffer(params, 1);
/*  83: 83 */    nglGetBufferParameterui64vNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86:    */  static native void nglGetBufferParameterui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  87:    */  
/*  88:    */  public static long glGetBufferParameterui64NV(int target, int pname) {
/*  89: 89 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  90: 90 */    long function_pointer = caps.glGetBufferParameterui64vNV;
/*  91: 91 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  92: 92 */    LongBuffer params = APIUtil.getBufferLong(caps);
/*  93: 93 */    nglGetBufferParameterui64vNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  94: 94 */    return params.get(0);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public static void glGetNamedBufferParameteruNV(int buffer, int pname, LongBuffer params) {
/*  98: 98 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  99: 99 */    long function_pointer = caps.glGetNamedBufferParameterui64vNV;
/* 100:100 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 101:101 */    BufferChecks.checkBuffer(params, 1);
/* 102:102 */    nglGetNamedBufferParameterui64vNV(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 103:    */  }
/* 104:    */  
/* 105:    */  static native void nglGetNamedBufferParameterui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 106:    */  
/* 107:    */  public static long glGetNamedBufferParameterui64NV(int buffer, int pname) {
/* 108:108 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 109:109 */    long function_pointer = caps.glGetNamedBufferParameterui64vNV;
/* 110:110 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 111:111 */    LongBuffer params = APIUtil.getBufferLong(caps);
/* 112:112 */    nglGetNamedBufferParameterui64vNV(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 113:113 */    return params.get(0);
/* 114:    */  }
/* 115:    */  
/* 116:    */  public static void glGetIntegeruNV(int value, LongBuffer result) {
/* 117:117 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 118:118 */    long function_pointer = caps.glGetIntegerui64vNV;
/* 119:119 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 120:120 */    BufferChecks.checkBuffer(result, 1);
/* 121:121 */    nglGetIntegerui64vNV(value, MemoryUtil.getAddress(result), function_pointer);
/* 122:    */  }
/* 123:    */  
/* 124:    */  static native void nglGetIntegerui64vNV(int paramInt, long paramLong1, long paramLong2);
/* 125:    */  
/* 126:    */  public static long glGetIntegerui64NV(int value) {
/* 127:127 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 128:128 */    long function_pointer = caps.glGetIntegerui64vNV;
/* 129:129 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 130:130 */    LongBuffer result = APIUtil.getBufferLong(caps);
/* 131:131 */    nglGetIntegerui64vNV(value, MemoryUtil.getAddress(result), function_pointer);
/* 132:132 */    return result.get(0);
/* 133:    */  }
/* 134:    */  
/* 135:    */  public static void glUniformui64NV(int location, long value) {
/* 136:136 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 137:137 */    long function_pointer = caps.glUniformui64NV;
/* 138:138 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 139:139 */    nglUniformui64NV(location, value, function_pointer);
/* 140:    */  }
/* 141:    */  
/* 142:    */  static native void nglUniformui64NV(int paramInt, long paramLong1, long paramLong2);
/* 143:    */  
/* 144:144 */  public static void glUniformuNV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 145:145 */    long function_pointer = caps.glUniformui64vNV;
/* 146:146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 147:147 */    BufferChecks.checkDirect(value);
/* 148:148 */    nglUniformui64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  static native void nglUniformui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 152:    */  
/* 153:153 */  public static void glGetUniformuNV(int program, int location, LongBuffer params) { NVGpuShader5.glGetUniformuNV(program, location, params); }
/* 154:    */  
/* 155:    */  public static void glProgramUniformui64NV(int program, int location, long value)
/* 156:    */  {
/* 157:157 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 158:158 */    long function_pointer = caps.glProgramUniformui64NV;
/* 159:159 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 160:160 */    nglProgramUniformui64NV(program, location, value, function_pointer);
/* 161:    */  }
/* 162:    */  
/* 163:    */  static native void nglProgramUniformui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 164:    */  
/* 165:165 */  public static void glProgramUniformuNV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 166:166 */    long function_pointer = caps.glProgramUniformui64vNV;
/* 167:167 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 168:168 */    BufferChecks.checkDirect(value);
/* 169:169 */    nglProgramUniformui64vNV(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native void nglProgramUniformui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 173:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVShaderBufferLoad
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */