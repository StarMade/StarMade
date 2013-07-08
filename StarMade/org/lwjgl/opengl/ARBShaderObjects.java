/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.MemoryUtil;
/*   8:    */
/*  24:    */public final class ARBShaderObjects
/*  25:    */{
/*  26:    */  public static final int GL_PROGRAM_OBJECT_ARB = 35648;
/*  27:    */  public static final int GL_OBJECT_TYPE_ARB = 35662;
/*  28:    */  public static final int GL_OBJECT_SUBTYPE_ARB = 35663;
/*  29:    */  public static final int GL_OBJECT_DELETE_STATUS_ARB = 35712;
/*  30:    */  public static final int GL_OBJECT_COMPILE_STATUS_ARB = 35713;
/*  31:    */  public static final int GL_OBJECT_LINK_STATUS_ARB = 35714;
/*  32:    */  public static final int GL_OBJECT_VALIDATE_STATUS_ARB = 35715;
/*  33:    */  public static final int GL_OBJECT_INFO_LOG_LENGTH_ARB = 35716;
/*  34:    */  public static final int GL_OBJECT_ATTACHED_OBJECTS_ARB = 35717;
/*  35:    */  public static final int GL_OBJECT_ACTIVE_UNIFORMS_ARB = 35718;
/*  36:    */  public static final int GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB = 35719;
/*  37:    */  public static final int GL_OBJECT_SHADER_SOURCE_LENGTH_ARB = 35720;
/*  38:    */  public static final int GL_SHADER_OBJECT_ARB = 35656;
/*  39:    */  public static final int GL_FLOAT_VEC2_ARB = 35664;
/*  40:    */  public static final int GL_FLOAT_VEC3_ARB = 35665;
/*  41:    */  public static final int GL_FLOAT_VEC4_ARB = 35666;
/*  42:    */  public static final int GL_INT_VEC2_ARB = 35667;
/*  43:    */  public static final int GL_INT_VEC3_ARB = 35668;
/*  44:    */  public static final int GL_INT_VEC4_ARB = 35669;
/*  45:    */  public static final int GL_BOOL_ARB = 35670;
/*  46:    */  public static final int GL_BOOL_VEC2_ARB = 35671;
/*  47:    */  public static final int GL_BOOL_VEC3_ARB = 35672;
/*  48:    */  public static final int GL_BOOL_VEC4_ARB = 35673;
/*  49:    */  public static final int GL_FLOAT_MAT2_ARB = 35674;
/*  50:    */  public static final int GL_FLOAT_MAT3_ARB = 35675;
/*  51:    */  public static final int GL_FLOAT_MAT4_ARB = 35676;
/*  52:    */  public static final int GL_SAMPLER_1D_ARB = 35677;
/*  53:    */  public static final int GL_SAMPLER_2D_ARB = 35678;
/*  54:    */  public static final int GL_SAMPLER_3D_ARB = 35679;
/*  55:    */  public static final int GL_SAMPLER_CUBE_ARB = 35680;
/*  56:    */  public static final int GL_SAMPLER_1D_SHADOW_ARB = 35681;
/*  57:    */  public static final int GL_SAMPLER_2D_SHADOW_ARB = 35682;
/*  58:    */  public static final int GL_SAMPLER_2D_RECT_ARB = 35683;
/*  59:    */  public static final int GL_SAMPLER_2D_RECT_SHADOW_ARB = 35684;
/*  60:    */  
/*  61:    */  public static void glDeleteObjectARB(int obj)
/*  62:    */  {
/*  63: 63 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  64: 64 */    long function_pointer = caps.glDeleteObjectARB;
/*  65: 65 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  66: 66 */    nglDeleteObjectARB(obj, function_pointer);
/*  67:    */  }
/*  68:    */  
/*  69:    */  static native void nglDeleteObjectARB(int paramInt, long paramLong);
/*  70:    */  
/*  71: 71 */  public static int glGetHandleARB(int pname) { ContextCapabilities caps = GLContext.getCapabilities();
/*  72: 72 */    long function_pointer = caps.glGetHandleARB;
/*  73: 73 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  74: 74 */    int __result = nglGetHandleARB(pname, function_pointer);
/*  75: 75 */    return __result;
/*  76:    */  }
/*  77:    */  
/*  78:    */  static native int nglGetHandleARB(int paramInt, long paramLong);
/*  79:    */  
/*  80: 80 */  public static void glDetachObjectARB(int containerObj, int attachedObj) { ContextCapabilities caps = GLContext.getCapabilities();
/*  81: 81 */    long function_pointer = caps.glDetachObjectARB;
/*  82: 82 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  83: 83 */    nglDetachObjectARB(containerObj, attachedObj, function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86:    */  static native void nglDetachObjectARB(int paramInt1, int paramInt2, long paramLong);
/*  87:    */  
/*  88: 88 */  public static int glCreateShaderObjectARB(int shaderType) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glCreateShaderObjectARB;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    int __result = nglCreateShaderObjectARB(shaderType, function_pointer);
/*  92: 92 */    return __result;
/*  93:    */  }
/*  94:    */  
/*  97:    */  static native int nglCreateShaderObjectARB(int paramInt, long paramLong);
/*  98:    */  
/* 100:    */  public static void glShaderSourceARB(int shader, ByteBuffer string)
/* 101:    */  {
/* 102:102 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glShaderSourceARB;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    BufferChecks.checkDirect(string);
/* 106:106 */    nglShaderSourceARB(shader, 1, MemoryUtil.getAddress(string), string.remaining(), function_pointer);
/* 107:    */  }
/* 108:    */  
/* 109:    */  static native void nglShaderSourceARB(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/* 110:    */  
/* 111:    */  public static void glShaderSourceARB(int shader, CharSequence string) {
/* 112:112 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 113:113 */    long function_pointer = caps.glShaderSourceARB;
/* 114:114 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 115:115 */    nglShaderSourceARB(shader, 1, APIUtil.getBuffer(caps, string), string.length(), function_pointer);
/* 116:    */  }
/* 117:    */  
/* 118:    */  public static void glShaderSourceARB(int shader, CharSequence[] strings)
/* 119:    */  {
/* 120:120 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 121:121 */    long function_pointer = caps.glShaderSourceARB;
/* 122:122 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 123:123 */    BufferChecks.checkArray(strings);
/* 124:124 */    nglShaderSourceARB3(shader, strings.length, APIUtil.getBuffer(caps, strings), APIUtil.getLengths(caps, strings), function_pointer);
/* 125:    */  }
/* 126:    */  
/* 127:    */  static native void nglShaderSourceARB3(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 128:    */  
/* 129:129 */  public static void glCompileShaderARB(int shaderObj) { ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glCompileShaderARB;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    nglCompileShaderARB(shaderObj, function_pointer);
/* 133:    */  }
/* 134:    */  
/* 135:    */  static native void nglCompileShaderARB(int paramInt, long paramLong);
/* 136:    */  
/* 137:137 */  public static int glCreateProgramObjectARB() { ContextCapabilities caps = GLContext.getCapabilities();
/* 138:138 */    long function_pointer = caps.glCreateProgramObjectARB;
/* 139:139 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 140:140 */    int __result = nglCreateProgramObjectARB(function_pointer);
/* 141:141 */    return __result;
/* 142:    */  }
/* 143:    */  
/* 144:    */  static native int nglCreateProgramObjectARB(long paramLong);
/* 145:    */  
/* 146:146 */  public static void glAttachObjectARB(int containerObj, int obj) { ContextCapabilities caps = GLContext.getCapabilities();
/* 147:147 */    long function_pointer = caps.glAttachObjectARB;
/* 148:148 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 149:149 */    nglAttachObjectARB(containerObj, obj, function_pointer);
/* 150:    */  }
/* 151:    */  
/* 152:    */  static native void nglAttachObjectARB(int paramInt1, int paramInt2, long paramLong);
/* 153:    */  
/* 154:154 */  public static void glLinkProgramARB(int programObj) { ContextCapabilities caps = GLContext.getCapabilities();
/* 155:155 */    long function_pointer = caps.glLinkProgramARB;
/* 156:156 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 157:157 */    nglLinkProgramARB(programObj, function_pointer);
/* 158:    */  }
/* 159:    */  
/* 160:    */  static native void nglLinkProgramARB(int paramInt, long paramLong);
/* 161:    */  
/* 162:162 */  public static void glUseProgramObjectARB(int programObj) { ContextCapabilities caps = GLContext.getCapabilities();
/* 163:163 */    long function_pointer = caps.glUseProgramObjectARB;
/* 164:164 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 165:165 */    nglUseProgramObjectARB(programObj, function_pointer);
/* 166:    */  }
/* 167:    */  
/* 168:    */  static native void nglUseProgramObjectARB(int paramInt, long paramLong);
/* 169:    */  
/* 170:170 */  public static void glValidateProgramARB(int programObj) { ContextCapabilities caps = GLContext.getCapabilities();
/* 171:171 */    long function_pointer = caps.glValidateProgramARB;
/* 172:172 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 173:173 */    nglValidateProgramARB(programObj, function_pointer);
/* 174:    */  }
/* 175:    */  
/* 176:    */  static native void nglValidateProgramARB(int paramInt, long paramLong);
/* 177:    */  
/* 178:178 */  public static void glUniform1fARB(int location, float v0) { ContextCapabilities caps = GLContext.getCapabilities();
/* 179:179 */    long function_pointer = caps.glUniform1fARB;
/* 180:180 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 181:181 */    nglUniform1fARB(location, v0, function_pointer);
/* 182:    */  }
/* 183:    */  
/* 184:    */  static native void nglUniform1fARB(int paramInt, float paramFloat, long paramLong);
/* 185:    */  
/* 186:186 */  public static void glUniform2fARB(int location, float v0, float v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 187:187 */    long function_pointer = caps.glUniform2fARB;
/* 188:188 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 189:189 */    nglUniform2fARB(location, v0, v1, function_pointer);
/* 190:    */  }
/* 191:    */  
/* 192:    */  static native void nglUniform2fARB(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 193:    */  
/* 194:194 */  public static void glUniform3fARB(int location, float v0, float v1, float v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 195:195 */    long function_pointer = caps.glUniform3fARB;
/* 196:196 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 197:197 */    nglUniform3fARB(location, v0, v1, v2, function_pointer);
/* 198:    */  }
/* 199:    */  
/* 200:    */  static native void nglUniform3fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 201:    */  
/* 202:202 */  public static void glUniform4fARB(int location, float v0, float v1, float v2, float v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 203:203 */    long function_pointer = caps.glUniform4fARB;
/* 204:204 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 205:205 */    nglUniform4fARB(location, v0, v1, v2, v3, function_pointer);
/* 206:    */  }
/* 207:    */  
/* 208:    */  static native void nglUniform4fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 209:    */  
/* 210:210 */  public static void glUniform1iARB(int location, int v0) { ContextCapabilities caps = GLContext.getCapabilities();
/* 211:211 */    long function_pointer = caps.glUniform1iARB;
/* 212:212 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 213:213 */    nglUniform1iARB(location, v0, function_pointer);
/* 214:    */  }
/* 215:    */  
/* 216:    */  static native void nglUniform1iARB(int paramInt1, int paramInt2, long paramLong);
/* 217:    */  
/* 218:218 */  public static void glUniform2iARB(int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 219:219 */    long function_pointer = caps.glUniform2iARB;
/* 220:220 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 221:221 */    nglUniform2iARB(location, v0, v1, function_pointer);
/* 222:    */  }
/* 223:    */  
/* 224:    */  static native void nglUniform2iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 225:    */  
/* 226:226 */  public static void glUniform3iARB(int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 227:227 */    long function_pointer = caps.glUniform3iARB;
/* 228:228 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 229:229 */    nglUniform3iARB(location, v0, v1, v2, function_pointer);
/* 230:    */  }
/* 231:    */  
/* 232:    */  static native void nglUniform3iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 233:    */  
/* 234:234 */  public static void glUniform4iARB(int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 235:235 */    long function_pointer = caps.glUniform4iARB;
/* 236:236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 237:237 */    nglUniform4iARB(location, v0, v1, v2, v3, function_pointer);
/* 238:    */  }
/* 239:    */  
/* 240:    */  static native void nglUniform4iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 241:    */  
/* 242:242 */  public static void glUniform1ARB(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 243:243 */    long function_pointer = caps.glUniform1fvARB;
/* 244:244 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 245:245 */    BufferChecks.checkDirect(values);
/* 246:246 */    nglUniform1fvARB(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/* 247:    */  }
/* 248:    */  
/* 249:    */  static native void nglUniform1fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 250:    */  
/* 251:251 */  public static void glUniform2ARB(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 252:252 */    long function_pointer = caps.glUniform2fvARB;
/* 253:253 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 254:254 */    BufferChecks.checkDirect(values);
/* 255:255 */    nglUniform2fvARB(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/* 256:    */  }
/* 257:    */  
/* 258:    */  static native void nglUniform2fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 259:    */  
/* 260:260 */  public static void glUniform3ARB(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 261:261 */    long function_pointer = caps.glUniform3fvARB;
/* 262:262 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 263:263 */    BufferChecks.checkDirect(values);
/* 264:264 */    nglUniform3fvARB(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/* 265:    */  }
/* 266:    */  
/* 267:    */  static native void nglUniform3fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 268:    */  
/* 269:269 */  public static void glUniform4ARB(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 270:270 */    long function_pointer = caps.glUniform4fvARB;
/* 271:271 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 272:272 */    BufferChecks.checkDirect(values);
/* 273:273 */    nglUniform4fvARB(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/* 274:    */  }
/* 275:    */  
/* 276:    */  static native void nglUniform4fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 277:    */  
/* 278:278 */  public static void glUniform1ARB(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 279:279 */    long function_pointer = caps.glUniform1ivARB;
/* 280:280 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 281:281 */    BufferChecks.checkDirect(values);
/* 282:282 */    nglUniform1ivARB(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/* 283:    */  }
/* 284:    */  
/* 285:    */  static native void nglUniform1ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 286:    */  
/* 287:287 */  public static void glUniform2ARB(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 288:288 */    long function_pointer = caps.glUniform2ivARB;
/* 289:289 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 290:290 */    BufferChecks.checkDirect(values);
/* 291:291 */    nglUniform2ivARB(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/* 292:    */  }
/* 293:    */  
/* 294:    */  static native void nglUniform2ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 295:    */  
/* 296:296 */  public static void glUniform3ARB(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 297:297 */    long function_pointer = caps.glUniform3ivARB;
/* 298:298 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 299:299 */    BufferChecks.checkDirect(values);
/* 300:300 */    nglUniform3ivARB(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/* 301:    */  }
/* 302:    */  
/* 303:    */  static native void nglUniform3ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 304:    */  
/* 305:305 */  public static void glUniform4ARB(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 306:306 */    long function_pointer = caps.glUniform4ivARB;
/* 307:307 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 308:308 */    BufferChecks.checkDirect(values);
/* 309:309 */    nglUniform4ivARB(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/* 310:    */  }
/* 311:    */  
/* 312:    */  static native void nglUniform4ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 313:    */  
/* 314:314 */  public static void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 315:315 */    long function_pointer = caps.glUniformMatrix2fvARB;
/* 316:316 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 317:317 */    BufferChecks.checkDirect(matrices);
/* 318:318 */    nglUniformMatrix2fvARB(location, matrices.remaining() >> 2, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/* 319:    */  }
/* 320:    */  
/* 321:    */  static native void nglUniformMatrix2fvARB(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 322:    */  
/* 323:323 */  public static void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 324:324 */    long function_pointer = caps.glUniformMatrix3fvARB;
/* 325:325 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 326:326 */    BufferChecks.checkDirect(matrices);
/* 327:327 */    nglUniformMatrix3fvARB(location, matrices.remaining() / 9, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/* 328:    */  }
/* 329:    */  
/* 330:    */  static native void nglUniformMatrix3fvARB(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 331:    */  
/* 332:332 */  public static void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 333:333 */    long function_pointer = caps.glUniformMatrix4fvARB;
/* 334:334 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 335:335 */    BufferChecks.checkDirect(matrices);
/* 336:336 */    nglUniformMatrix4fvARB(location, matrices.remaining() >> 4, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/* 337:    */  }
/* 338:    */  
/* 339:    */  static native void nglUniformMatrix4fvARB(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 340:    */  
/* 341:341 */  public static void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 342:342 */    long function_pointer = caps.glGetObjectParameterfvARB;
/* 343:343 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 344:344 */    BufferChecks.checkDirect(params);
/* 345:345 */    nglGetObjectParameterfvARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/* 346:    */  }
/* 347:    */  
/* 348:    */  static native void nglGetObjectParameterfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 349:    */  
/* 350:    */  public static float glGetObjectParameterfARB(int obj, int pname) {
/* 351:351 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 352:352 */    long function_pointer = caps.glGetObjectParameterfvARB;
/* 353:353 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 354:354 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 355:355 */    nglGetObjectParameterfvARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/* 356:356 */    return params.get(0);
/* 357:    */  }
/* 358:    */  
/* 359:    */  public static void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
/* 360:360 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 361:361 */    long function_pointer = caps.glGetObjectParameterivARB;
/* 362:362 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 363:363 */    BufferChecks.checkDirect(params);
/* 364:364 */    nglGetObjectParameterivARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/* 365:    */  }
/* 366:    */  
/* 367:    */  static native void nglGetObjectParameterivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 368:    */  
/* 369:    */  public static int glGetObjectParameteriARB(int obj, int pname) {
/* 370:370 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 371:371 */    long function_pointer = caps.glGetObjectParameterivARB;
/* 372:372 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 373:373 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 374:374 */    nglGetObjectParameterivARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/* 375:375 */    return params.get(0);
/* 376:    */  }
/* 377:    */  
/* 378:    */  public static void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
/* 379:379 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 380:380 */    long function_pointer = caps.glGetInfoLogARB;
/* 381:381 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 382:382 */    if (length != null)
/* 383:383 */      BufferChecks.checkBuffer(length, 1);
/* 384:384 */    BufferChecks.checkDirect(infoLog);
/* 385:385 */    nglGetInfoLogARB(obj, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/* 386:    */  }
/* 387:    */  
/* 388:    */  static native void nglGetInfoLogARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 389:    */  
/* 390:    */  public static String glGetInfoLogARB(int obj, int maxLength) {
/* 391:391 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 392:392 */    long function_pointer = caps.glGetInfoLogARB;
/* 393:393 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 394:394 */    IntBuffer infoLog_length = APIUtil.getLengths(caps);
/* 395:395 */    ByteBuffer infoLog = APIUtil.getBufferByte(caps, maxLength);
/* 396:396 */    nglGetInfoLogARB(obj, maxLength, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/* 397:397 */    infoLog.limit(infoLog_length.get(0));
/* 398:398 */    return APIUtil.getString(caps, infoLog);
/* 399:    */  }
/* 400:    */  
/* 401:    */  public static void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
/* 402:402 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 403:403 */    long function_pointer = caps.glGetAttachedObjectsARB;
/* 404:404 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 405:405 */    if (count != null)
/* 406:406 */      BufferChecks.checkBuffer(count, 1);
/* 407:407 */    BufferChecks.checkDirect(obj);
/* 408:408 */    nglGetAttachedObjectsARB(containerObj, obj.remaining(), MemoryUtil.getAddressSafe(count), MemoryUtil.getAddress(obj), function_pointer);
/* 409:    */  }
/* 410:    */  
/* 413:    */  static native void nglGetAttachedObjectsARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 414:    */  
/* 417:    */  public static int glGetUniformLocationARB(int programObj, ByteBuffer name)
/* 418:    */  {
/* 419:419 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 420:420 */    long function_pointer = caps.glGetUniformLocationARB;
/* 421:421 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 422:422 */    BufferChecks.checkDirect(name);
/* 423:423 */    BufferChecks.checkNullTerminated(name);
/* 424:424 */    int __result = nglGetUniformLocationARB(programObj, MemoryUtil.getAddress(name), function_pointer);
/* 425:425 */    return __result;
/* 426:    */  }
/* 427:    */  
/* 428:    */  static native int nglGetUniformLocationARB(int paramInt, long paramLong1, long paramLong2);
/* 429:    */  
/* 430:    */  public static int glGetUniformLocationARB(int programObj, CharSequence name) {
/* 431:431 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 432:432 */    long function_pointer = caps.glGetUniformLocationARB;
/* 433:433 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 434:434 */    int __result = nglGetUniformLocationARB(programObj, APIUtil.getBufferNT(caps, name), function_pointer);
/* 435:435 */    return __result;
/* 436:    */  }
/* 437:    */  
/* 438:    */  public static void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 439:439 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 440:440 */    long function_pointer = caps.glGetActiveUniformARB;
/* 441:441 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 442:442 */    if (length != null)
/* 443:443 */      BufferChecks.checkBuffer(length, 1);
/* 444:444 */    BufferChecks.checkBuffer(size, 1);
/* 445:445 */    BufferChecks.checkBuffer(type, 1);
/* 446:446 */    BufferChecks.checkDirect(name);
/* 447:447 */    nglGetActiveUniformARB(programObj, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 448:    */  }
/* 449:    */  
/* 452:    */  static native void nglGetActiveUniformARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 453:    */  
/* 455:    */  public static String glGetActiveUniformARB(int programObj, int index, int maxLength, IntBuffer sizeType)
/* 456:    */  {
/* 457:457 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 458:458 */    long function_pointer = caps.glGetActiveUniformARB;
/* 459:459 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 460:460 */    BufferChecks.checkBuffer(sizeType, 2);
/* 461:461 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 462:462 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 463:463 */    nglGetActiveUniformARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 464:464 */    name.limit(name_length.get(0));
/* 465:465 */    return APIUtil.getString(caps, name);
/* 466:    */  }
/* 467:    */  
/* 472:    */  public static String glGetActiveUniformARB(int programObj, int index, int maxLength)
/* 473:    */  {
/* 474:474 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 475:475 */    long function_pointer = caps.glGetActiveUniformARB;
/* 476:476 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 477:477 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 478:478 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 479:479 */    nglGetActiveUniformARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 480:480 */    name.limit(name_length.get(0));
/* 481:481 */    return APIUtil.getString(caps, name);
/* 482:    */  }
/* 483:    */  
/* 488:    */  public static int glGetActiveUniformSizeARB(int programObj, int index)
/* 489:    */  {
/* 490:490 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 491:491 */    long function_pointer = caps.glGetActiveUniformARB;
/* 492:492 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 493:493 */    IntBuffer size = APIUtil.getBufferInt(caps);
/* 494:494 */    nglGetActiveUniformARB(programObj, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 495:495 */    return size.get(0);
/* 496:    */  }
/* 497:    */  
/* 502:    */  public static int glGetActiveUniformTypeARB(int programObj, int index)
/* 503:    */  {
/* 504:504 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 505:505 */    long function_pointer = caps.glGetActiveUniformARB;
/* 506:506 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 507:507 */    IntBuffer type = APIUtil.getBufferInt(caps);
/* 508:508 */    nglGetActiveUniformARB(programObj, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 509:509 */    return type.get(0);
/* 510:    */  }
/* 511:    */  
/* 512:    */  public static void glGetUniformARB(int programObj, int location, FloatBuffer params) {
/* 513:513 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 514:514 */    long function_pointer = caps.glGetUniformfvARB;
/* 515:515 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 516:516 */    BufferChecks.checkDirect(params);
/* 517:517 */    nglGetUniformfvARB(programObj, location, MemoryUtil.getAddress(params), function_pointer);
/* 518:    */  }
/* 519:    */  
/* 520:    */  static native void nglGetUniformfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 521:    */  
/* 522:522 */  public static void glGetUniformARB(int programObj, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 523:523 */    long function_pointer = caps.glGetUniformivARB;
/* 524:524 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 525:525 */    BufferChecks.checkDirect(params);
/* 526:526 */    nglGetUniformivARB(programObj, location, MemoryUtil.getAddress(params), function_pointer);
/* 527:    */  }
/* 528:    */  
/* 529:    */  static native void nglGetUniformivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 530:    */  
/* 531:531 */  public static void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) { ContextCapabilities caps = GLContext.getCapabilities();
/* 532:532 */    long function_pointer = caps.glGetShaderSourceARB;
/* 533:533 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 534:534 */    if (length != null)
/* 535:535 */      BufferChecks.checkBuffer(length, 1);
/* 536:536 */    BufferChecks.checkDirect(source);
/* 537:537 */    nglGetShaderSourceARB(obj, source.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(source), function_pointer);
/* 538:    */  }
/* 539:    */  
/* 540:    */  static native void nglGetShaderSourceARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 541:    */  
/* 542:    */  public static String glGetShaderSourceARB(int obj, int maxLength) {
/* 543:543 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 544:544 */    long function_pointer = caps.glGetShaderSourceARB;
/* 545:545 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 546:546 */    IntBuffer source_length = APIUtil.getLengths(caps);
/* 547:547 */    ByteBuffer source = APIUtil.getBufferByte(caps, maxLength);
/* 548:548 */    nglGetShaderSourceARB(obj, maxLength, MemoryUtil.getAddress0(source_length), MemoryUtil.getAddress(source), function_pointer);
/* 549:549 */    source.limit(source_length.get(0));
/* 550:550 */    return APIUtil.getString(caps, source);
/* 551:    */  }
/* 552:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShaderObjects
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */