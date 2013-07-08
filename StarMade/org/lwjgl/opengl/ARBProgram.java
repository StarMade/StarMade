/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.BufferChecks;
/*   8:    */import org.lwjgl.MemoryUtil;
/*   9:    */
/*  32:    */public class ARBProgram
/*  33:    */{
/*  34:    */  public static final int GL_PROGRAM_FORMAT_ASCII_ARB = 34933;
/*  35:    */  public static final int GL_PROGRAM_LENGTH_ARB = 34343;
/*  36:    */  public static final int GL_PROGRAM_FORMAT_ARB = 34934;
/*  37:    */  public static final int GL_PROGRAM_BINDING_ARB = 34423;
/*  38:    */  public static final int GL_PROGRAM_INSTRUCTIONS_ARB = 34976;
/*  39:    */  public static final int GL_MAX_PROGRAM_INSTRUCTIONS_ARB = 34977;
/*  40:    */  public static final int GL_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 34978;
/*  41:    */  public static final int GL_MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 34979;
/*  42:    */  public static final int GL_PROGRAM_TEMPORARIES_ARB = 34980;
/*  43:    */  public static final int GL_MAX_PROGRAM_TEMPORARIES_ARB = 34981;
/*  44:    */  public static final int GL_PROGRAM_NATIVE_TEMPORARIES_ARB = 34982;
/*  45:    */  public static final int GL_MAX_PROGRAM_NATIVE_TEMPORARIES_ARB = 34983;
/*  46:    */  public static final int GL_PROGRAM_PARAMETERS_ARB = 34984;
/*  47:    */  public static final int GL_MAX_PROGRAM_PARAMETERS_ARB = 34985;
/*  48:    */  public static final int GL_PROGRAM_NATIVE_PARAMETERS_ARB = 34986;
/*  49:    */  public static final int GL_MAX_PROGRAM_NATIVE_PARAMETERS_ARB = 34987;
/*  50:    */  public static final int GL_PROGRAM_ATTRIBS_ARB = 34988;
/*  51:    */  public static final int GL_MAX_PROGRAM_ATTRIBS_ARB = 34989;
/*  52:    */  public static final int GL_PROGRAM_NATIVE_ATTRIBS_ARB = 34990;
/*  53:    */  public static final int GL_MAX_PROGRAM_NATIVE_ATTRIBS_ARB = 34991;
/*  54:    */  public static final int GL_MAX_PROGRAM_LOCAL_PARAMETERS_ARB = 34996;
/*  55:    */  public static final int GL_MAX_PROGRAM_ENV_PARAMETERS_ARB = 34997;
/*  56:    */  public static final int GL_PROGRAM_UNDER_NATIVE_LIMITS_ARB = 34998;
/*  57:    */  public static final int GL_PROGRAM_STRING_ARB = 34344;
/*  58:    */  public static final int GL_PROGRAM_ERROR_POSITION_ARB = 34379;
/*  59:    */  public static final int GL_CURRENT_MATRIX_ARB = 34369;
/*  60:    */  public static final int GL_TRANSPOSE_CURRENT_MATRIX_ARB = 34999;
/*  61:    */  public static final int GL_CURRENT_MATRIX_STACK_DEPTH_ARB = 34368;
/*  62:    */  public static final int GL_MAX_PROGRAM_MATRICES_ARB = 34351;
/*  63:    */  public static final int GL_MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB = 34350;
/*  64:    */  public static final int GL_PROGRAM_ERROR_STRING_ARB = 34932;
/*  65:    */  public static final int GL_MATRIX0_ARB = 35008;
/*  66:    */  public static final int GL_MATRIX1_ARB = 35009;
/*  67:    */  public static final int GL_MATRIX2_ARB = 35010;
/*  68:    */  public static final int GL_MATRIX3_ARB = 35011;
/*  69:    */  public static final int GL_MATRIX4_ARB = 35012;
/*  70:    */  public static final int GL_MATRIX5_ARB = 35013;
/*  71:    */  public static final int GL_MATRIX6_ARB = 35014;
/*  72:    */  public static final int GL_MATRIX7_ARB = 35015;
/*  73:    */  public static final int GL_MATRIX8_ARB = 35016;
/*  74:    */  public static final int GL_MATRIX9_ARB = 35017;
/*  75:    */  public static final int GL_MATRIX10_ARB = 35018;
/*  76:    */  public static final int GL_MATRIX11_ARB = 35019;
/*  77:    */  public static final int GL_MATRIX12_ARB = 35020;
/*  78:    */  public static final int GL_MATRIX13_ARB = 35021;
/*  79:    */  public static final int GL_MATRIX14_ARB = 35022;
/*  80:    */  public static final int GL_MATRIX15_ARB = 35023;
/*  81:    */  public static final int GL_MATRIX16_ARB = 35024;
/*  82:    */  public static final int GL_MATRIX17_ARB = 35025;
/*  83:    */  public static final int GL_MATRIX18_ARB = 35026;
/*  84:    */  public static final int GL_MATRIX19_ARB = 35027;
/*  85:    */  public static final int GL_MATRIX20_ARB = 35028;
/*  86:    */  public static final int GL_MATRIX21_ARB = 35029;
/*  87:    */  public static final int GL_MATRIX22_ARB = 35030;
/*  88:    */  public static final int GL_MATRIX23_ARB = 35031;
/*  89:    */  public static final int GL_MATRIX24_ARB = 35032;
/*  90:    */  public static final int GL_MATRIX25_ARB = 35033;
/*  91:    */  public static final int GL_MATRIX26_ARB = 35034;
/*  92:    */  public static final int GL_MATRIX27_ARB = 35035;
/*  93:    */  public static final int GL_MATRIX28_ARB = 35036;
/*  94:    */  public static final int GL_MATRIX29_ARB = 35037;
/*  95:    */  public static final int GL_MATRIX30_ARB = 35038;
/*  96:    */  public static final int GL_MATRIX31_ARB = 35039;
/*  97:    */  
/*  98:    */  public static void glProgramStringARB(int target, int format, ByteBuffer string)
/*  99:    */  {
/* 100:100 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 101:101 */    long function_pointer = caps.glProgramStringARB;
/* 102:102 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 103:103 */    BufferChecks.checkDirect(string);
/* 104:104 */    nglProgramStringARB(target, format, string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/* 105:    */  }
/* 106:    */  
/* 107:    */  static native void nglProgramStringARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 108:    */  
/* 109:    */  public static void glProgramStringARB(int target, int format, CharSequence string) {
/* 110:110 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glProgramStringARB;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    nglProgramStringARB(target, format, string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/* 114:    */  }
/* 115:    */  
/* 116:    */  public static void glBindProgramARB(int target, int program) {
/* 117:117 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 118:118 */    long function_pointer = caps.glBindProgramARB;
/* 119:119 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 120:120 */    nglBindProgramARB(target, program, function_pointer);
/* 121:    */  }
/* 122:    */  
/* 123:    */  static native void nglBindProgramARB(int paramInt1, int paramInt2, long paramLong);
/* 124:    */  
/* 125:125 */  public static void glDeleteProgramsARB(IntBuffer programs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 126:126 */    long function_pointer = caps.glDeleteProgramsARB;
/* 127:127 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 128:128 */    BufferChecks.checkDirect(programs);
/* 129:129 */    nglDeleteProgramsARB(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/* 130:    */  }
/* 131:    */  
/* 132:    */  static native void nglDeleteProgramsARB(int paramInt, long paramLong1, long paramLong2);
/* 133:    */  
/* 134:    */  public static void glDeleteProgramsARB(int program) {
/* 135:135 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glDeleteProgramsARB;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    nglDeleteProgramsARB(1, APIUtil.getInt(caps, program), function_pointer);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public static void glGenProgramsARB(IntBuffer programs) {
/* 142:142 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glGenProgramsARB;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    BufferChecks.checkDirect(programs);
/* 146:146 */    nglGenProgramsARB(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/* 147:    */  }
/* 148:    */  
/* 149:    */  static native void nglGenProgramsARB(int paramInt, long paramLong1, long paramLong2);
/* 150:    */  
/* 151:    */  public static int glGenProgramsARB() {
/* 152:152 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glGenProgramsARB;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    IntBuffer programs = APIUtil.getBufferInt(caps);
/* 156:156 */    nglGenProgramsARB(1, MemoryUtil.getAddress(programs), function_pointer);
/* 157:157 */    return programs.get(0);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public static void glProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w) {
/* 161:161 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 162:162 */    long function_pointer = caps.glProgramEnvParameter4fARB;
/* 163:163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 164:164 */    nglProgramEnvParameter4fARB(target, index, x, y, z, w, function_pointer);
/* 165:    */  }
/* 166:    */  
/* 167:    */  static native void nglProgramEnvParameter4fARB(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 168:    */  
/* 169:169 */  public static void glProgramEnvParameter4dARB(int target, int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 170:170 */    long function_pointer = caps.glProgramEnvParameter4dARB;
/* 171:171 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 172:172 */    nglProgramEnvParameter4dARB(target, index, x, y, z, w, function_pointer);
/* 173:    */  }
/* 174:    */  
/* 175:    */  static native void nglProgramEnvParameter4dARB(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 176:    */  
/* 177:177 */  public static void glProgramEnvParameter4ARB(int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 178:178 */    long function_pointer = caps.glProgramEnvParameter4fvARB;
/* 179:179 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 180:180 */    BufferChecks.checkBuffer(params, 4);
/* 181:181 */    nglProgramEnvParameter4fvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 182:    */  }
/* 183:    */  
/* 184:    */  static native void nglProgramEnvParameter4fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 185:    */  
/* 186:186 */  public static void glProgramEnvParameter4ARB(int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 187:187 */    long function_pointer = caps.glProgramEnvParameter4dvARB;
/* 188:188 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 189:189 */    BufferChecks.checkBuffer(params, 4);
/* 190:190 */    nglProgramEnvParameter4dvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 191:    */  }
/* 192:    */  
/* 193:    */  static native void nglProgramEnvParameter4dvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 194:    */  
/* 195:195 */  public static void glProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 196:196 */    long function_pointer = caps.glProgramLocalParameter4fARB;
/* 197:197 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 198:198 */    nglProgramLocalParameter4fARB(target, index, x, y, z, w, function_pointer);
/* 199:    */  }
/* 200:    */  
/* 201:    */  static native void nglProgramLocalParameter4fARB(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 202:    */  
/* 203:203 */  public static void glProgramLocalParameter4dARB(int target, int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 204:204 */    long function_pointer = caps.glProgramLocalParameter4dARB;
/* 205:205 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 206:206 */    nglProgramLocalParameter4dARB(target, index, x, y, z, w, function_pointer);
/* 207:    */  }
/* 208:    */  
/* 209:    */  static native void nglProgramLocalParameter4dARB(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 210:    */  
/* 211:211 */  public static void glProgramLocalParameter4ARB(int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 212:212 */    long function_pointer = caps.glProgramLocalParameter4fvARB;
/* 213:213 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 214:214 */    BufferChecks.checkBuffer(params, 4);
/* 215:215 */    nglProgramLocalParameter4fvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 216:    */  }
/* 217:    */  
/* 218:    */  static native void nglProgramLocalParameter4fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 219:    */  
/* 220:220 */  public static void glProgramLocalParameter4ARB(int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 221:221 */    long function_pointer = caps.glProgramLocalParameter4dvARB;
/* 222:222 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 223:223 */    BufferChecks.checkBuffer(params, 4);
/* 224:224 */    nglProgramLocalParameter4dvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 225:    */  }
/* 226:    */  
/* 227:    */  static native void nglProgramLocalParameter4dvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 228:    */  
/* 229:229 */  public static void glGetProgramEnvParameterARB(int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 230:230 */    long function_pointer = caps.glGetProgramEnvParameterfvARB;
/* 231:231 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 232:232 */    BufferChecks.checkBuffer(params, 4);
/* 233:233 */    nglGetProgramEnvParameterfvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 234:    */  }
/* 235:    */  
/* 236:    */  static native void nglGetProgramEnvParameterfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 237:    */  
/* 238:238 */  public static void glGetProgramEnvParameterARB(int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 239:239 */    long function_pointer = caps.glGetProgramEnvParameterdvARB;
/* 240:240 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 241:241 */    BufferChecks.checkBuffer(params, 4);
/* 242:242 */    nglGetProgramEnvParameterdvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 243:    */  }
/* 244:    */  
/* 245:    */  static native void nglGetProgramEnvParameterdvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 246:    */  
/* 247:247 */  public static void glGetProgramLocalParameterARB(int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 248:248 */    long function_pointer = caps.glGetProgramLocalParameterfvARB;
/* 249:249 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 250:250 */    BufferChecks.checkBuffer(params, 4);
/* 251:251 */    nglGetProgramLocalParameterfvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 252:    */  }
/* 253:    */  
/* 254:    */  static native void nglGetProgramLocalParameterfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 255:    */  
/* 256:256 */  public static void glGetProgramLocalParameterARB(int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 257:257 */    long function_pointer = caps.glGetProgramLocalParameterdvARB;
/* 258:258 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 259:259 */    BufferChecks.checkBuffer(params, 4);
/* 260:260 */    nglGetProgramLocalParameterdvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/* 261:    */  }
/* 262:    */  
/* 263:    */  static native void nglGetProgramLocalParameterdvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 264:    */  
/* 265:265 */  public static void glGetProgramARB(int target, int parameterName, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 266:266 */    long function_pointer = caps.glGetProgramivARB;
/* 267:267 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 268:268 */    BufferChecks.checkBuffer(params, 4);
/* 269:269 */    nglGetProgramivARB(target, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 270:    */  }
/* 271:    */  
/* 274:    */  static native void nglGetProgramivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 275:    */  
/* 277:    */  @Deprecated
/* 278:    */  public static int glGetProgramARB(int target, int parameterName)
/* 279:    */  {
/* 280:280 */    return glGetProgramiARB(target, parameterName);
/* 281:    */  }
/* 282:    */  
/* 283:    */  public static int glGetProgramiARB(int target, int parameterName)
/* 284:    */  {
/* 285:285 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 286:286 */    long function_pointer = caps.glGetProgramivARB;
/* 287:287 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 288:288 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 289:289 */    nglGetProgramivARB(target, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 290:290 */    return params.get(0);
/* 291:    */  }
/* 292:    */  
/* 293:    */  public static void glGetProgramStringARB(int target, int parameterName, ByteBuffer paramString) {
/* 294:294 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 295:295 */    long function_pointer = caps.glGetProgramStringARB;
/* 296:296 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 297:297 */    BufferChecks.checkDirect(paramString);
/* 298:298 */    nglGetProgramStringARB(target, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/* 299:    */  }
/* 300:    */  
/* 301:    */  static native void nglGetProgramStringARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 302:    */  
/* 303:    */  public static String glGetProgramStringARB(int target, int parameterName) {
/* 304:304 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 305:305 */    long function_pointer = caps.glGetProgramStringARB;
/* 306:306 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 307:307 */    int programLength = glGetProgramiARB(target, 34343);
/* 308:308 */    ByteBuffer paramString = APIUtil.getBufferByte(caps, programLength);
/* 309:309 */    nglGetProgramStringARB(target, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/* 310:310 */    paramString.limit(programLength);
/* 311:311 */    return APIUtil.getString(caps, paramString);
/* 312:    */  }
/* 313:    */  
/* 314:    */  public static boolean glIsProgramARB(int program) {
/* 315:315 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 316:316 */    long function_pointer = caps.glIsProgramARB;
/* 317:317 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 318:318 */    boolean __result = nglIsProgramARB(program, function_pointer);
/* 319:319 */    return __result;
/* 320:    */  }
/* 321:    */  
/* 322:    */  static native boolean nglIsProgramARB(int paramInt, long paramLong);
/* 323:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */