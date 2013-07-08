/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.DoubleBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  13:    */public final class ARBGpuShaderFp64
/*  14:    */{
/*  15:    */  public static final int GL_DOUBLE = 5130;
/*  16:    */  public static final int GL_DOUBLE_VEC2 = 36860;
/*  17:    */  public static final int GL_DOUBLE_VEC3 = 36861;
/*  18:    */  public static final int GL_DOUBLE_VEC4 = 36862;
/*  19:    */  public static final int GL_DOUBLE_MAT2 = 36678;
/*  20:    */  public static final int GL_DOUBLE_MAT3 = 36679;
/*  21:    */  public static final int GL_DOUBLE_MAT4 = 36680;
/*  22:    */  public static final int GL_DOUBLE_MAT2x3 = 36681;
/*  23:    */  public static final int GL_DOUBLE_MAT2x4 = 36682;
/*  24:    */  public static final int GL_DOUBLE_MAT3x2 = 36683;
/*  25:    */  public static final int GL_DOUBLE_MAT3x4 = 36684;
/*  26:    */  public static final int GL_DOUBLE_MAT4x2 = 36685;
/*  27:    */  public static final int GL_DOUBLE_MAT4x3 = 36686;
/*  28:    */  
/*  29:    */  public static void glUniform1d(int location, double x)
/*  30:    */  {
/*  31: 31 */    GL40.glUniform1d(location, x);
/*  32:    */  }
/*  33:    */  
/*  34:    */  public static void glUniform2d(int location, double x, double y) {
/*  35: 35 */    GL40.glUniform2d(location, x, y);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public static void glUniform3d(int location, double x, double y, double z) {
/*  39: 39 */    GL40.glUniform3d(location, x, y, z);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public static void glUniform4d(int location, double x, double y, double z, double w) {
/*  43: 43 */    GL40.glUniform4d(location, x, y, z, w);
/*  44:    */  }
/*  45:    */  
/*  46:    */  public static void glUniform1(int location, DoubleBuffer value) {
/*  47: 47 */    GL40.glUniform1(location, value);
/*  48:    */  }
/*  49:    */  
/*  50:    */  public static void glUniform2(int location, DoubleBuffer value) {
/*  51: 51 */    GL40.glUniform2(location, value);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public static void glUniform3(int location, DoubleBuffer value) {
/*  55: 55 */    GL40.glUniform3(location, value);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public static void glUniform4(int location, DoubleBuffer value) {
/*  59: 59 */    GL40.glUniform4(location, value);
/*  60:    */  }
/*  61:    */  
/*  62:    */  public static void glUniformMatrix2(int location, boolean transpose, DoubleBuffer value) {
/*  63: 63 */    GL40.glUniformMatrix2(location, transpose, value);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public static void glUniformMatrix3(int location, boolean transpose, DoubleBuffer value) {
/*  67: 67 */    GL40.glUniformMatrix3(location, transpose, value);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public static void glUniformMatrix4(int location, boolean transpose, DoubleBuffer value) {
/*  71: 71 */    GL40.glUniformMatrix4(location, transpose, value);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public static void glUniformMatrix2x3(int location, boolean transpose, DoubleBuffer value) {
/*  75: 75 */    GL40.glUniformMatrix2x3(location, transpose, value);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static void glUniformMatrix2x4(int location, boolean transpose, DoubleBuffer value) {
/*  79: 79 */    GL40.glUniformMatrix2x4(location, transpose, value);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public static void glUniformMatrix3x2(int location, boolean transpose, DoubleBuffer value) {
/*  83: 83 */    GL40.glUniformMatrix3x2(location, transpose, value);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static void glUniformMatrix3x4(int location, boolean transpose, DoubleBuffer value) {
/*  87: 87 */    GL40.glUniformMatrix3x4(location, transpose, value);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public static void glUniformMatrix4x2(int location, boolean transpose, DoubleBuffer value) {
/*  91: 91 */    GL40.glUniformMatrix4x2(location, transpose, value);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public static void glUniformMatrix4x3(int location, boolean transpose, DoubleBuffer value) {
/*  95: 95 */    GL40.glUniformMatrix4x3(location, transpose, value);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public static void glGetUniform(int program, int location, DoubleBuffer params) {
/*  99: 99 */    GL40.glGetUniform(program, location, params);
/* 100:    */  }
/* 101:    */  
/* 102:    */  public static void glProgramUniform1dEXT(int program, int location, double x) {
/* 103:103 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 104:104 */    long function_pointer = caps.glProgramUniform1dEXT;
/* 105:105 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 106:106 */    nglProgramUniform1dEXT(program, location, x, function_pointer);
/* 107:    */  }
/* 108:    */  
/* 109:    */  static native void nglProgramUniform1dEXT(int paramInt1, int paramInt2, double paramDouble, long paramLong);
/* 110:    */  
/* 111:111 */  public static void glProgramUniform2dEXT(int program, int location, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 112:112 */    long function_pointer = caps.glProgramUniform2dEXT;
/* 113:113 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 114:114 */    nglProgramUniform2dEXT(program, location, x, y, function_pointer);
/* 115:    */  }
/* 116:    */  
/* 117:    */  static native void nglProgramUniform2dEXT(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, long paramLong);
/* 118:    */  
/* 119:119 */  public static void glProgramUniform3dEXT(int program, int location, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 120:120 */    long function_pointer = caps.glProgramUniform3dEXT;
/* 121:121 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 122:122 */    nglProgramUniform3dEXT(program, location, x, y, z, function_pointer);
/* 123:    */  }
/* 124:    */  
/* 125:    */  static native void nglProgramUniform3dEXT(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 126:    */  
/* 127:127 */  public static void glProgramUniform4dEXT(int program, int location, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 128:128 */    long function_pointer = caps.glProgramUniform4dEXT;
/* 129:129 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 130:130 */    nglProgramUniform4dEXT(program, location, x, y, z, w, function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:    */  static native void nglProgramUniform4dEXT(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 134:    */  
/* 135:135 */  public static void glProgramUniform1EXT(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glProgramUniform1dvEXT;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    BufferChecks.checkDirect(value);
/* 139:139 */    nglProgramUniform1dvEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 140:    */  }
/* 141:    */  
/* 142:    */  static native void nglProgramUniform1dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 143:    */  
/* 144:144 */  public static void glProgramUniform2EXT(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 145:145 */    long function_pointer = caps.glProgramUniform2dvEXT;
/* 146:146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 147:147 */    BufferChecks.checkDirect(value);
/* 148:148 */    nglProgramUniform2dvEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  static native void nglProgramUniform2dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 152:    */  
/* 153:153 */  public static void glProgramUniform3EXT(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 154:154 */    long function_pointer = caps.glProgramUniform3dvEXT;
/* 155:155 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 156:156 */    BufferChecks.checkDirect(value);
/* 157:157 */    nglProgramUniform3dvEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 158:    */  }
/* 159:    */  
/* 160:    */  static native void nglProgramUniform3dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 161:    */  
/* 162:162 */  public static void glProgramUniform4EXT(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 163:163 */    long function_pointer = caps.glProgramUniform4dvEXT;
/* 164:164 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 165:165 */    BufferChecks.checkDirect(value);
/* 166:166 */    nglProgramUniform4dvEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 167:    */  }
/* 168:    */  
/* 169:    */  static native void nglProgramUniform4dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 170:    */  
/* 171:171 */  public static void glProgramUniformMatrix2EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 172:172 */    long function_pointer = caps.glProgramUniformMatrix2dvEXT;
/* 173:173 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 174:174 */    BufferChecks.checkDirect(value);
/* 175:175 */    nglProgramUniformMatrix2dvEXT(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 176:    */  }
/* 177:    */  
/* 178:    */  static native void nglProgramUniformMatrix2dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 179:    */  
/* 180:180 */  public static void glProgramUniformMatrix3EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 181:181 */    long function_pointer = caps.glProgramUniformMatrix3dvEXT;
/* 182:182 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 183:183 */    BufferChecks.checkDirect(value);
/* 184:184 */    nglProgramUniformMatrix3dvEXT(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 185:    */  }
/* 186:    */  
/* 187:    */  static native void nglProgramUniformMatrix3dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 188:    */  
/* 189:189 */  public static void glProgramUniformMatrix4EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glProgramUniformMatrix4dvEXT;
/* 191:191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    BufferChecks.checkDirect(value);
/* 193:193 */    nglProgramUniformMatrix4dvEXT(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 194:    */  }
/* 195:    */  
/* 196:    */  static native void nglProgramUniformMatrix4dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 197:    */  
/* 198:198 */  public static void glProgramUniformMatrix2x3EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 199:199 */    long function_pointer = caps.glProgramUniformMatrix2x3dvEXT;
/* 200:200 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 201:201 */    BufferChecks.checkDirect(value);
/* 202:202 */    nglProgramUniformMatrix2x3dvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 203:    */  }
/* 204:    */  
/* 205:    */  static native void nglProgramUniformMatrix2x3dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 206:    */  
/* 207:207 */  public static void glProgramUniformMatrix2x4EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 208:208 */    long function_pointer = caps.glProgramUniformMatrix2x4dvEXT;
/* 209:209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 210:210 */    BufferChecks.checkDirect(value);
/* 211:211 */    nglProgramUniformMatrix2x4dvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 212:    */  }
/* 213:    */  
/* 214:    */  static native void nglProgramUniformMatrix2x4dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 215:    */  
/* 216:216 */  public static void glProgramUniformMatrix3x2EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 217:217 */    long function_pointer = caps.glProgramUniformMatrix3x2dvEXT;
/* 218:218 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 219:219 */    BufferChecks.checkDirect(value);
/* 220:220 */    nglProgramUniformMatrix3x2dvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 221:    */  }
/* 222:    */  
/* 223:    */  static native void nglProgramUniformMatrix3x2dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 224:    */  
/* 225:225 */  public static void glProgramUniformMatrix3x4EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 226:226 */    long function_pointer = caps.glProgramUniformMatrix3x4dvEXT;
/* 227:227 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 228:228 */    BufferChecks.checkDirect(value);
/* 229:229 */    nglProgramUniformMatrix3x4dvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 230:    */  }
/* 231:    */  
/* 232:    */  static native void nglProgramUniformMatrix3x4dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 233:    */  
/* 234:234 */  public static void glProgramUniformMatrix4x2EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 235:235 */    long function_pointer = caps.glProgramUniformMatrix4x2dvEXT;
/* 236:236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 237:237 */    BufferChecks.checkDirect(value);
/* 238:238 */    nglProgramUniformMatrix4x2dvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 239:    */  }
/* 240:    */  
/* 241:    */  static native void nglProgramUniformMatrix4x2dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 242:    */  
/* 243:243 */  public static void glProgramUniformMatrix4x3EXT(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 244:244 */    long function_pointer = caps.glProgramUniformMatrix4x3dvEXT;
/* 245:245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 246:246 */    BufferChecks.checkDirect(value);
/* 247:247 */    nglProgramUniformMatrix4x3dvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 248:    */  }
/* 249:    */  
/* 250:    */  static native void nglProgramUniformMatrix4x3dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 251:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBGpuShaderFp64
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */