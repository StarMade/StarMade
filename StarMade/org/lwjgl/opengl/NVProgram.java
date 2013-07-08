/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  24:    */public class NVProgram
/*  25:    */{
/*  26:    */  public static final int GL_PROGRAM_TARGET_NV = 34374;
/*  27:    */  public static final int GL_PROGRAM_LENGTH_NV = 34343;
/*  28:    */  public static final int GL_PROGRAM_RESIDENT_NV = 34375;
/*  29:    */  public static final int GL_PROGRAM_STRING_NV = 34344;
/*  30:    */  public static final int GL_PROGRAM_ERROR_POSITION_NV = 34379;
/*  31:    */  public static final int GL_PROGRAM_ERROR_STRING_NV = 34932;
/*  32:    */  
/*  33:    */  public static void glLoadProgramNV(int target, int programID, ByteBuffer string)
/*  34:    */  {
/*  35: 35 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  36: 36 */    long function_pointer = caps.glLoadProgramNV;
/*  37: 37 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  38: 38 */    BufferChecks.checkDirect(string);
/*  39: 39 */    nglLoadProgramNV(target, programID, string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*  40:    */  }
/*  41:    */  
/*  42:    */  static native void nglLoadProgramNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  43:    */  
/*  44:    */  public static void glLoadProgramNV(int target, int programID, CharSequence string) {
/*  45: 45 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  46: 46 */    long function_pointer = caps.glLoadProgramNV;
/*  47: 47 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  48: 48 */    nglLoadProgramNV(target, programID, string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public static void glBindProgramNV(int target, int programID) {
/*  52: 52 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  53: 53 */    long function_pointer = caps.glBindProgramNV;
/*  54: 54 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  55: 55 */    nglBindProgramNV(target, programID, function_pointer);
/*  56:    */  }
/*  57:    */  
/*  58:    */  static native void nglBindProgramNV(int paramInt1, int paramInt2, long paramLong);
/*  59:    */  
/*  60: 60 */  public static void glDeleteProgramsNV(IntBuffer programs) { ContextCapabilities caps = GLContext.getCapabilities();
/*  61: 61 */    long function_pointer = caps.glDeleteProgramsNV;
/*  62: 62 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  63: 63 */    BufferChecks.checkDirect(programs);
/*  64: 64 */    nglDeleteProgramsNV(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/*  65:    */  }
/*  66:    */  
/*  67:    */  static native void nglDeleteProgramsNV(int paramInt, long paramLong1, long paramLong2);
/*  68:    */  
/*  69:    */  public static void glDeleteProgramsNV(int program) {
/*  70: 70 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glDeleteProgramsNV;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    nglDeleteProgramsNV(1, APIUtil.getInt(caps, program), function_pointer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public static void glGenProgramsNV(IntBuffer programs) {
/*  77: 77 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  78: 78 */    long function_pointer = caps.glGenProgramsNV;
/*  79: 79 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  80: 80 */    BufferChecks.checkDirect(programs);
/*  81: 81 */    nglGenProgramsNV(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/*  82:    */  }
/*  83:    */  
/*  84:    */  static native void nglGenProgramsNV(int paramInt, long paramLong1, long paramLong2);
/*  85:    */  
/*  86:    */  public static int glGenProgramsNV() {
/*  87: 87 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  88: 88 */    long function_pointer = caps.glGenProgramsNV;
/*  89: 89 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  90: 90 */    IntBuffer programs = APIUtil.getBufferInt(caps);
/*  91: 91 */    nglGenProgramsNV(1, MemoryUtil.getAddress(programs), function_pointer);
/*  92: 92 */    return programs.get(0);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static void glGetProgramNV(int programID, int parameterName, IntBuffer params) {
/*  96: 96 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  97: 97 */    long function_pointer = caps.glGetProgramivNV;
/*  98: 98 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  99: 99 */    BufferChecks.checkDirect(params);
/* 100:100 */    nglGetProgramivNV(programID, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 101:    */  }
/* 102:    */  
/* 105:    */  static native void nglGetProgramivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 106:    */  
/* 108:    */  @Deprecated
/* 109:    */  public static int glGetProgramNV(int programID, int parameterName)
/* 110:    */  {
/* 111:111 */    return glGetProgramiNV(programID, parameterName);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static int glGetProgramiNV(int programID, int parameterName)
/* 115:    */  {
/* 116:116 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 117:117 */    long function_pointer = caps.glGetProgramivNV;
/* 118:118 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 119:119 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 120:120 */    nglGetProgramivNV(programID, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 121:121 */    return params.get(0);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public static void glGetProgramStringNV(int programID, int parameterName, ByteBuffer paramString) {
/* 125:125 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 126:126 */    long function_pointer = caps.glGetProgramStringNV;
/* 127:127 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 128:128 */    BufferChecks.checkDirect(paramString);
/* 129:129 */    nglGetProgramStringNV(programID, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/* 130:    */  }
/* 131:    */  
/* 132:    */  static native void nglGetProgramStringNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 133:    */  
/* 134:    */  public static String glGetProgramStringNV(int programID, int parameterName) {
/* 135:135 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glGetProgramStringNV;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    int programLength = glGetProgramiNV(programID, 34343);
/* 139:139 */    ByteBuffer paramString = APIUtil.getBufferByte(caps, programLength);
/* 140:140 */    nglGetProgramStringNV(programID, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/* 141:141 */    paramString.limit(programLength);
/* 142:142 */    return APIUtil.getString(caps, paramString);
/* 143:    */  }
/* 144:    */  
/* 145:    */  public static boolean glIsProgramNV(int programID) {
/* 146:146 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 147:147 */    long function_pointer = caps.glIsProgramNV;
/* 148:148 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 149:149 */    boolean __result = nglIsProgramNV(programID, function_pointer);
/* 150:150 */    return __result;
/* 151:    */  }
/* 152:    */  
/* 153:    */  static native boolean nglIsProgramNV(int paramInt, long paramLong);
/* 154:    */  
/* 155:155 */  public static boolean glAreProgramsResidentNV(IntBuffer programIDs, ByteBuffer programResidences) { ContextCapabilities caps = GLContext.getCapabilities();
/* 156:156 */    long function_pointer = caps.glAreProgramsResidentNV;
/* 157:157 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 158:158 */    BufferChecks.checkDirect(programIDs);
/* 159:159 */    BufferChecks.checkBuffer(programResidences, programIDs.remaining());
/* 160:160 */    boolean __result = nglAreProgramsResidentNV(programIDs.remaining(), MemoryUtil.getAddress(programIDs), MemoryUtil.getAddress(programResidences), function_pointer);
/* 161:161 */    return __result;
/* 162:    */  }
/* 163:    */  
/* 164:    */  static native boolean nglAreProgramsResidentNV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 165:    */  
/* 166:166 */  public static void glRequestResidentProgramsNV(IntBuffer programIDs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glRequestResidentProgramsNV;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    BufferChecks.checkDirect(programIDs);
/* 170:170 */    nglRequestResidentProgramsNV(programIDs.remaining(), MemoryUtil.getAddress(programIDs), function_pointer);
/* 171:    */  }
/* 172:    */  
/* 173:    */  static native void nglRequestResidentProgramsNV(int paramInt, long paramLong1, long paramLong2);
/* 174:    */  
/* 175:    */  public static void glRequestResidentProgramsNV(int programID) {
/* 176:176 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 177:177 */    long function_pointer = caps.glRequestResidentProgramsNV;
/* 178:178 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 179:179 */    nglRequestResidentProgramsNV(1, APIUtil.getInt(caps, programID), function_pointer);
/* 180:    */  }
/* 181:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */