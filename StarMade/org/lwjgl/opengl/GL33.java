/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.nio.LongBuffer;
/*   7:    */import org.lwjgl.BufferChecks;
/*   8:    */import org.lwjgl.MemoryUtil;
/*   9:    */
/*  69:    */public final class GL33
/*  70:    */{
/*  71:    */  public static final int GL_SRC1_COLOR = 35065;
/*  72:    */  public static final int GL_SRC1_ALPHA = 34185;
/*  73:    */  public static final int GL_ONE_MINUS_SRC1_COLOR = 35066;
/*  74:    */  public static final int GL_ONE_MINUS_SRC1_ALPHA = 35067;
/*  75:    */  public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;
/*  76:    */  public static final int GL_ANY_SAMPLES_PASSED = 35887;
/*  77:    */  public static final int GL_SAMPLER_BINDING = 35097;
/*  78:    */  public static final int GL_RGB10_A2UI = 36975;
/*  79:    */  public static final int GL_TEXTURE_SWIZZLE_R = 36418;
/*  80:    */  public static final int GL_TEXTURE_SWIZZLE_G = 36419;
/*  81:    */  public static final int GL_TEXTURE_SWIZZLE_B = 36420;
/*  82:    */  public static final int GL_TEXTURE_SWIZZLE_A = 36421;
/*  83:    */  public static final int GL_TEXTURE_SWIZZLE_RGBA = 36422;
/*  84:    */  public static final int GL_TIME_ELAPSED = 35007;
/*  85:    */  public static final int GL_TIMESTAMP = 36392;
/*  86:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 35070;
/*  87:    */  public static final int GL_INT_2_10_10_10_REV = 36255;
/*  88:    */  
/*  89:    */  public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, ByteBuffer name)
/*  90:    */  {
/*  91: 91 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  92: 92 */    long function_pointer = caps.glBindFragDataLocationIndexed;
/*  93: 93 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  94: 94 */    BufferChecks.checkDirect(name);
/*  95: 95 */    BufferChecks.checkNullTerminated(name);
/*  96: 96 */    nglBindFragDataLocationIndexed(program, colorNumber, index, MemoryUtil.getAddress(name), function_pointer);
/*  97:    */  }
/*  98:    */  
/*  99:    */  static native void nglBindFragDataLocationIndexed(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 100:    */  
/* 101:    */  public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, CharSequence name) {
/* 102:102 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glBindFragDataLocationIndexed;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    nglBindFragDataLocationIndexed(program, colorNumber, index, APIUtil.getBufferNT(caps, name), function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public static int glGetFragDataIndex(int program, ByteBuffer name) {
/* 109:109 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 110:110 */    long function_pointer = caps.glGetFragDataIndex;
/* 111:111 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 112:112 */    BufferChecks.checkDirect(name);
/* 113:113 */    BufferChecks.checkNullTerminated(name);
/* 114:114 */    int __result = nglGetFragDataIndex(program, MemoryUtil.getAddress(name), function_pointer);
/* 115:115 */    return __result;
/* 116:    */  }
/* 117:    */  
/* 118:    */  static native int nglGetFragDataIndex(int paramInt, long paramLong1, long paramLong2);
/* 119:    */  
/* 120:    */  public static int glGetFragDataIndex(int program, CharSequence name) {
/* 121:121 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 122:122 */    long function_pointer = caps.glGetFragDataIndex;
/* 123:123 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 124:124 */    int __result = nglGetFragDataIndex(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 125:125 */    return __result;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public static void glGenSamplers(IntBuffer samplers) {
/* 129:129 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glGenSamplers;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    BufferChecks.checkDirect(samplers);
/* 133:133 */    nglGenSamplers(samplers.remaining(), MemoryUtil.getAddress(samplers), function_pointer);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static native void nglGenSamplers(int paramInt, long paramLong1, long paramLong2);
/* 137:    */  
/* 138:    */  public static int glGenSamplers() {
/* 139:139 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 140:140 */    long function_pointer = caps.glGenSamplers;
/* 141:141 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 142:142 */    IntBuffer samplers = APIUtil.getBufferInt(caps);
/* 143:143 */    nglGenSamplers(1, MemoryUtil.getAddress(samplers), function_pointer);
/* 144:144 */    return samplers.get(0);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public static void glDeleteSamplers(IntBuffer samplers) {
/* 148:148 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 149:149 */    long function_pointer = caps.glDeleteSamplers;
/* 150:150 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 151:151 */    BufferChecks.checkDirect(samplers);
/* 152:152 */    nglDeleteSamplers(samplers.remaining(), MemoryUtil.getAddress(samplers), function_pointer);
/* 153:    */  }
/* 154:    */  
/* 155:    */  static native void nglDeleteSamplers(int paramInt, long paramLong1, long paramLong2);
/* 156:    */  
/* 157:    */  public static void glDeleteSamplers(int sampler) {
/* 158:158 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 159:159 */    long function_pointer = caps.glDeleteSamplers;
/* 160:160 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 161:161 */    nglDeleteSamplers(1, APIUtil.getInt(caps, sampler), function_pointer);
/* 162:    */  }
/* 163:    */  
/* 164:    */  public static boolean glIsSampler(int sampler) {
/* 165:165 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 166:166 */    long function_pointer = caps.glIsSampler;
/* 167:167 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 168:168 */    boolean __result = nglIsSampler(sampler, function_pointer);
/* 169:169 */    return __result;
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native boolean nglIsSampler(int paramInt, long paramLong);
/* 173:    */  
/* 174:174 */  public static void glBindSampler(int unit, int sampler) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glBindSampler;
/* 176:176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    nglBindSampler(unit, sampler, function_pointer);
/* 178:    */  }
/* 179:    */  
/* 180:    */  static native void nglBindSampler(int paramInt1, int paramInt2, long paramLong);
/* 181:    */  
/* 182:182 */  public static void glSamplerParameteri(int sampler, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 183:183 */    long function_pointer = caps.glSamplerParameteri;
/* 184:184 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 185:185 */    nglSamplerParameteri(sampler, pname, param, function_pointer);
/* 186:    */  }
/* 187:    */  
/* 188:    */  static native void nglSamplerParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 189:    */  
/* 190:190 */  public static void glSamplerParameterf(int sampler, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 191:191 */    long function_pointer = caps.glSamplerParameterf;
/* 192:192 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 193:193 */    nglSamplerParameterf(sampler, pname, param, function_pointer);
/* 194:    */  }
/* 195:    */  
/* 196:    */  static native void nglSamplerParameterf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 197:    */  
/* 198:198 */  public static void glSamplerParameter(int sampler, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 199:199 */    long function_pointer = caps.glSamplerParameteriv;
/* 200:200 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 201:201 */    BufferChecks.checkBuffer(params, 4);
/* 202:202 */    nglSamplerParameteriv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 203:    */  }
/* 204:    */  
/* 205:    */  static native void nglSamplerParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 206:    */  
/* 207:207 */  public static void glSamplerParameter(int sampler, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 208:208 */    long function_pointer = caps.glSamplerParameterfv;
/* 209:209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 210:210 */    BufferChecks.checkBuffer(params, 4);
/* 211:211 */    nglSamplerParameterfv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 212:    */  }
/* 213:    */  
/* 214:    */  static native void nglSamplerParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 215:    */  
/* 216:216 */  public static void glSamplerParameterI(int sampler, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 217:217 */    long function_pointer = caps.glSamplerParameterIiv;
/* 218:218 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 219:219 */    BufferChecks.checkBuffer(params, 4);
/* 220:220 */    nglSamplerParameterIiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 221:    */  }
/* 222:    */  
/* 223:    */  static native void nglSamplerParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 224:    */  
/* 225:225 */  public static void glSamplerParameterIu(int sampler, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 226:226 */    long function_pointer = caps.glSamplerParameterIuiv;
/* 227:227 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 228:228 */    BufferChecks.checkBuffer(params, 4);
/* 229:229 */    nglSamplerParameterIuiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 230:    */  }
/* 231:    */  
/* 232:    */  static native void nglSamplerParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 233:    */  
/* 234:234 */  public static void glGetSamplerParameter(int sampler, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 235:235 */    long function_pointer = caps.glGetSamplerParameteriv;
/* 236:236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 237:237 */    BufferChecks.checkBuffer(params, 4);
/* 238:238 */    nglGetSamplerParameteriv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 239:    */  }
/* 240:    */  
/* 241:    */  static native void nglGetSamplerParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 242:    */  
/* 243:    */  public static int glGetSamplerParameteri(int sampler, int pname) {
/* 244:244 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 245:245 */    long function_pointer = caps.glGetSamplerParameteriv;
/* 246:246 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 247:247 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 248:248 */    nglGetSamplerParameteriv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 249:249 */    return params.get(0);
/* 250:    */  }
/* 251:    */  
/* 252:    */  public static void glGetSamplerParameter(int sampler, int pname, FloatBuffer params) {
/* 253:253 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 254:254 */    long function_pointer = caps.glGetSamplerParameterfv;
/* 255:255 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 256:256 */    BufferChecks.checkBuffer(params, 4);
/* 257:257 */    nglGetSamplerParameterfv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 258:    */  }
/* 259:    */  
/* 260:    */  static native void nglGetSamplerParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 261:    */  
/* 262:    */  public static float glGetSamplerParameterf(int sampler, int pname) {
/* 263:263 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 264:264 */    long function_pointer = caps.glGetSamplerParameterfv;
/* 265:265 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 266:266 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 267:267 */    nglGetSamplerParameterfv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 268:268 */    return params.get(0);
/* 269:    */  }
/* 270:    */  
/* 271:    */  public static void glGetSamplerParameterI(int sampler, int pname, IntBuffer params) {
/* 272:272 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 273:273 */    long function_pointer = caps.glGetSamplerParameterIiv;
/* 274:274 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 275:275 */    BufferChecks.checkBuffer(params, 4);
/* 276:276 */    nglGetSamplerParameterIiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 277:    */  }
/* 278:    */  
/* 279:    */  static native void nglGetSamplerParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 280:    */  
/* 281:    */  public static int glGetSamplerParameterIi(int sampler, int pname) {
/* 282:282 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 283:283 */    long function_pointer = caps.glGetSamplerParameterIiv;
/* 284:284 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 285:285 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 286:286 */    nglGetSamplerParameterIiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 287:287 */    return params.get(0);
/* 288:    */  }
/* 289:    */  
/* 290:    */  public static void glGetSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/* 291:291 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 292:292 */    long function_pointer = caps.glGetSamplerParameterIuiv;
/* 293:293 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 294:294 */    BufferChecks.checkBuffer(params, 4);
/* 295:295 */    nglGetSamplerParameterIuiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 296:    */  }
/* 297:    */  
/* 298:    */  static native void nglGetSamplerParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 299:    */  
/* 300:    */  public static int glGetSamplerParameterIui(int sampler, int pname) {
/* 301:301 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 302:302 */    long function_pointer = caps.glGetSamplerParameterIuiv;
/* 303:303 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 304:304 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 305:305 */    nglGetSamplerParameterIuiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 306:306 */    return params.get(0);
/* 307:    */  }
/* 308:    */  
/* 309:    */  public static void glQueryCounter(int id, int target) {
/* 310:310 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 311:311 */    long function_pointer = caps.glQueryCounter;
/* 312:312 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 313:313 */    nglQueryCounter(id, target, function_pointer);
/* 314:    */  }
/* 315:    */  
/* 316:    */  static native void nglQueryCounter(int paramInt1, int paramInt2, long paramLong);
/* 317:    */  
/* 318:318 */  public static void glGetQueryObject(int id, int pname, LongBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 319:319 */    long function_pointer = caps.glGetQueryObjecti64v;
/* 320:320 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 321:321 */    BufferChecks.checkBuffer(params, 1);
/* 322:322 */    nglGetQueryObjecti64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 323:    */  }
/* 324:    */  
/* 327:    */  static native void nglGetQueryObjecti64v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 328:    */  
/* 330:    */  @Deprecated
/* 331:    */  public static long glGetQueryObject(int id, int pname)
/* 332:    */  {
/* 333:333 */    return glGetQueryObjecti64(id, pname);
/* 334:    */  }
/* 335:    */  
/* 336:    */  public static long glGetQueryObjecti64(int id, int pname)
/* 337:    */  {
/* 338:338 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 339:339 */    long function_pointer = caps.glGetQueryObjecti64v;
/* 340:340 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 341:341 */    LongBuffer params = APIUtil.getBufferLong(caps);
/* 342:342 */    nglGetQueryObjecti64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 343:343 */    return params.get(0);
/* 344:    */  }
/* 345:    */  
/* 346:    */  public static void glGetQueryObjectu(int id, int pname, LongBuffer params) {
/* 347:347 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 348:348 */    long function_pointer = caps.glGetQueryObjectui64v;
/* 349:349 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 350:350 */    BufferChecks.checkBuffer(params, 1);
/* 351:351 */    nglGetQueryObjectui64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 352:    */  }
/* 353:    */  
/* 356:    */  static native void nglGetQueryObjectui64v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 357:    */  
/* 359:    */  @Deprecated
/* 360:    */  public static long glGetQueryObjectu(int id, int pname)
/* 361:    */  {
/* 362:362 */    return glGetQueryObjectui64(id, pname);
/* 363:    */  }
/* 364:    */  
/* 365:    */  public static long glGetQueryObjectui64(int id, int pname)
/* 366:    */  {
/* 367:367 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 368:368 */    long function_pointer = caps.glGetQueryObjectui64v;
/* 369:369 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 370:370 */    LongBuffer params = APIUtil.getBufferLong(caps);
/* 371:371 */    nglGetQueryObjectui64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 372:372 */    return params.get(0);
/* 373:    */  }
/* 374:    */  
/* 375:    */  public static void glVertexAttribDivisor(int index, int divisor) {
/* 376:376 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 377:377 */    long function_pointer = caps.glVertexAttribDivisor;
/* 378:378 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 379:379 */    nglVertexAttribDivisor(index, divisor, function_pointer);
/* 380:    */  }
/* 381:    */  
/* 382:    */  static native void nglVertexAttribDivisor(int paramInt1, int paramInt2, long paramLong);
/* 383:    */  
/* 384:384 */  public static void glVertexP2ui(int type, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 385:385 */    long function_pointer = caps.glVertexP2ui;
/* 386:386 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 387:387 */    nglVertexP2ui(type, value, function_pointer);
/* 388:    */  }
/* 389:    */  
/* 390:    */  static native void nglVertexP2ui(int paramInt1, int paramInt2, long paramLong);
/* 391:    */  
/* 392:392 */  public static void glVertexP3ui(int type, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 393:393 */    long function_pointer = caps.glVertexP3ui;
/* 394:394 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 395:395 */    nglVertexP3ui(type, value, function_pointer);
/* 396:    */  }
/* 397:    */  
/* 398:    */  static native void nglVertexP3ui(int paramInt1, int paramInt2, long paramLong);
/* 399:    */  
/* 400:400 */  public static void glVertexP4ui(int type, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 401:401 */    long function_pointer = caps.glVertexP4ui;
/* 402:402 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 403:403 */    nglVertexP4ui(type, value, function_pointer);
/* 404:    */  }
/* 405:    */  
/* 406:    */  static native void nglVertexP4ui(int paramInt1, int paramInt2, long paramLong);
/* 407:    */  
/* 408:408 */  public static void glVertexP2u(int type, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 409:409 */    long function_pointer = caps.glVertexP2uiv;
/* 410:410 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 411:411 */    BufferChecks.checkBuffer(value, 2);
/* 412:412 */    nglVertexP2uiv(type, MemoryUtil.getAddress(value), function_pointer);
/* 413:    */  }
/* 414:    */  
/* 415:    */  static native void nglVertexP2uiv(int paramInt, long paramLong1, long paramLong2);
/* 416:    */  
/* 417:417 */  public static void glVertexP3u(int type, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 418:418 */    long function_pointer = caps.glVertexP3uiv;
/* 419:419 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 420:420 */    BufferChecks.checkBuffer(value, 3);
/* 421:421 */    nglVertexP3uiv(type, MemoryUtil.getAddress(value), function_pointer);
/* 422:    */  }
/* 423:    */  
/* 424:    */  static native void nglVertexP3uiv(int paramInt, long paramLong1, long paramLong2);
/* 425:    */  
/* 426:426 */  public static void glVertexP4u(int type, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 427:427 */    long function_pointer = caps.glVertexP4uiv;
/* 428:428 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 429:429 */    BufferChecks.checkBuffer(value, 4);
/* 430:430 */    nglVertexP4uiv(type, MemoryUtil.getAddress(value), function_pointer);
/* 431:    */  }
/* 432:    */  
/* 433:    */  static native void nglVertexP4uiv(int paramInt, long paramLong1, long paramLong2);
/* 434:    */  
/* 435:435 */  public static void glTexCoordP1ui(int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 436:436 */    long function_pointer = caps.glTexCoordP1ui;
/* 437:437 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 438:438 */    nglTexCoordP1ui(type, coords, function_pointer);
/* 439:    */  }
/* 440:    */  
/* 441:    */  static native void nglTexCoordP1ui(int paramInt1, int paramInt2, long paramLong);
/* 442:    */  
/* 443:443 */  public static void glTexCoordP2ui(int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 444:444 */    long function_pointer = caps.glTexCoordP2ui;
/* 445:445 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 446:446 */    nglTexCoordP2ui(type, coords, function_pointer);
/* 447:    */  }
/* 448:    */  
/* 449:    */  static native void nglTexCoordP2ui(int paramInt1, int paramInt2, long paramLong);
/* 450:    */  
/* 451:451 */  public static void glTexCoordP3ui(int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 452:452 */    long function_pointer = caps.glTexCoordP3ui;
/* 453:453 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 454:454 */    nglTexCoordP3ui(type, coords, function_pointer);
/* 455:    */  }
/* 456:    */  
/* 457:    */  static native void nglTexCoordP3ui(int paramInt1, int paramInt2, long paramLong);
/* 458:    */  
/* 459:459 */  public static void glTexCoordP4ui(int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 460:460 */    long function_pointer = caps.glTexCoordP4ui;
/* 461:461 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 462:462 */    nglTexCoordP4ui(type, coords, function_pointer);
/* 463:    */  }
/* 464:    */  
/* 465:    */  static native void nglTexCoordP4ui(int paramInt1, int paramInt2, long paramLong);
/* 466:    */  
/* 467:467 */  public static void glTexCoordP1u(int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 468:468 */    long function_pointer = caps.glTexCoordP1uiv;
/* 469:469 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 470:470 */    BufferChecks.checkBuffer(coords, 1);
/* 471:471 */    nglTexCoordP1uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/* 472:    */  }
/* 473:    */  
/* 474:    */  static native void nglTexCoordP1uiv(int paramInt, long paramLong1, long paramLong2);
/* 475:    */  
/* 476:476 */  public static void glTexCoordP2u(int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 477:477 */    long function_pointer = caps.glTexCoordP2uiv;
/* 478:478 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 479:479 */    BufferChecks.checkBuffer(coords, 2);
/* 480:480 */    nglTexCoordP2uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/* 481:    */  }
/* 482:    */  
/* 483:    */  static native void nglTexCoordP2uiv(int paramInt, long paramLong1, long paramLong2);
/* 484:    */  
/* 485:485 */  public static void glTexCoordP3u(int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 486:486 */    long function_pointer = caps.glTexCoordP3uiv;
/* 487:487 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 488:488 */    BufferChecks.checkBuffer(coords, 3);
/* 489:489 */    nglTexCoordP3uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/* 490:    */  }
/* 491:    */  
/* 492:    */  static native void nglTexCoordP3uiv(int paramInt, long paramLong1, long paramLong2);
/* 493:    */  
/* 494:494 */  public static void glTexCoordP4u(int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 495:495 */    long function_pointer = caps.glTexCoordP4uiv;
/* 496:496 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 497:497 */    BufferChecks.checkBuffer(coords, 4);
/* 498:498 */    nglTexCoordP4uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/* 499:    */  }
/* 500:    */  
/* 501:    */  static native void nglTexCoordP4uiv(int paramInt, long paramLong1, long paramLong2);
/* 502:    */  
/* 503:503 */  public static void glMultiTexCoordP1ui(int texture, int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 504:504 */    long function_pointer = caps.glMultiTexCoordP1ui;
/* 505:505 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 506:506 */    nglMultiTexCoordP1ui(texture, type, coords, function_pointer);
/* 507:    */  }
/* 508:    */  
/* 509:    */  static native void nglMultiTexCoordP1ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 510:    */  
/* 511:511 */  public static void glMultiTexCoordP2ui(int texture, int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 512:512 */    long function_pointer = caps.glMultiTexCoordP2ui;
/* 513:513 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 514:514 */    nglMultiTexCoordP2ui(texture, type, coords, function_pointer);
/* 515:    */  }
/* 516:    */  
/* 517:    */  static native void nglMultiTexCoordP2ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 518:    */  
/* 519:519 */  public static void glMultiTexCoordP3ui(int texture, int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 520:520 */    long function_pointer = caps.glMultiTexCoordP3ui;
/* 521:521 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 522:522 */    nglMultiTexCoordP3ui(texture, type, coords, function_pointer);
/* 523:    */  }
/* 524:    */  
/* 525:    */  static native void nglMultiTexCoordP3ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 526:    */  
/* 527:527 */  public static void glMultiTexCoordP4ui(int texture, int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 528:528 */    long function_pointer = caps.glMultiTexCoordP4ui;
/* 529:529 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 530:530 */    nglMultiTexCoordP4ui(texture, type, coords, function_pointer);
/* 531:    */  }
/* 532:    */  
/* 533:    */  static native void nglMultiTexCoordP4ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 534:    */  
/* 535:535 */  public static void glMultiTexCoordP1u(int texture, int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 536:536 */    long function_pointer = caps.glMultiTexCoordP1uiv;
/* 537:537 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 538:538 */    BufferChecks.checkBuffer(coords, 1);
/* 539:539 */    nglMultiTexCoordP1uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/* 540:    */  }
/* 541:    */  
/* 542:    */  static native void nglMultiTexCoordP1uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 543:    */  
/* 544:544 */  public static void glMultiTexCoordP2u(int texture, int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 545:545 */    long function_pointer = caps.glMultiTexCoordP2uiv;
/* 546:546 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 547:547 */    BufferChecks.checkBuffer(coords, 2);
/* 548:548 */    nglMultiTexCoordP2uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/* 549:    */  }
/* 550:    */  
/* 551:    */  static native void nglMultiTexCoordP2uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 552:    */  
/* 553:553 */  public static void glMultiTexCoordP3u(int texture, int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 554:554 */    long function_pointer = caps.glMultiTexCoordP3uiv;
/* 555:555 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 556:556 */    BufferChecks.checkBuffer(coords, 3);
/* 557:557 */    nglMultiTexCoordP3uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/* 558:    */  }
/* 559:    */  
/* 560:    */  static native void nglMultiTexCoordP3uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 561:    */  
/* 562:562 */  public static void glMultiTexCoordP4u(int texture, int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 563:563 */    long function_pointer = caps.glMultiTexCoordP4uiv;
/* 564:564 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 565:565 */    BufferChecks.checkBuffer(coords, 4);
/* 566:566 */    nglMultiTexCoordP4uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/* 567:    */  }
/* 568:    */  
/* 569:    */  static native void nglMultiTexCoordP4uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 570:    */  
/* 571:571 */  public static void glNormalP3ui(int type, int coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 572:572 */    long function_pointer = caps.glNormalP3ui;
/* 573:573 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 574:574 */    nglNormalP3ui(type, coords, function_pointer);
/* 575:    */  }
/* 576:    */  
/* 577:    */  static native void nglNormalP3ui(int paramInt1, int paramInt2, long paramLong);
/* 578:    */  
/* 579:579 */  public static void glNormalP3u(int type, IntBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 580:580 */    long function_pointer = caps.glNormalP3uiv;
/* 581:581 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 582:582 */    BufferChecks.checkBuffer(coords, 3);
/* 583:583 */    nglNormalP3uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/* 584:    */  }
/* 585:    */  
/* 586:    */  static native void nglNormalP3uiv(int paramInt, long paramLong1, long paramLong2);
/* 587:    */  
/* 588:588 */  public static void glColorP3ui(int type, int color) { ContextCapabilities caps = GLContext.getCapabilities();
/* 589:589 */    long function_pointer = caps.glColorP3ui;
/* 590:590 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 591:591 */    nglColorP3ui(type, color, function_pointer);
/* 592:    */  }
/* 593:    */  
/* 594:    */  static native void nglColorP3ui(int paramInt1, int paramInt2, long paramLong);
/* 595:    */  
/* 596:596 */  public static void glColorP4ui(int type, int color) { ContextCapabilities caps = GLContext.getCapabilities();
/* 597:597 */    long function_pointer = caps.glColorP4ui;
/* 598:598 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 599:599 */    nglColorP4ui(type, color, function_pointer);
/* 600:    */  }
/* 601:    */  
/* 602:    */  static native void nglColorP4ui(int paramInt1, int paramInt2, long paramLong);
/* 603:    */  
/* 604:604 */  public static void glColorP3u(int type, IntBuffer color) { ContextCapabilities caps = GLContext.getCapabilities();
/* 605:605 */    long function_pointer = caps.glColorP3uiv;
/* 606:606 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 607:607 */    BufferChecks.checkBuffer(color, 3);
/* 608:608 */    nglColorP3uiv(type, MemoryUtil.getAddress(color), function_pointer);
/* 609:    */  }
/* 610:    */  
/* 611:    */  static native void nglColorP3uiv(int paramInt, long paramLong1, long paramLong2);
/* 612:    */  
/* 613:613 */  public static void glColorP4u(int type, IntBuffer color) { ContextCapabilities caps = GLContext.getCapabilities();
/* 614:614 */    long function_pointer = caps.glColorP4uiv;
/* 615:615 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 616:616 */    BufferChecks.checkBuffer(color, 4);
/* 617:617 */    nglColorP4uiv(type, MemoryUtil.getAddress(color), function_pointer);
/* 618:    */  }
/* 619:    */  
/* 620:    */  static native void nglColorP4uiv(int paramInt, long paramLong1, long paramLong2);
/* 621:    */  
/* 622:622 */  public static void glSecondaryColorP3ui(int type, int color) { ContextCapabilities caps = GLContext.getCapabilities();
/* 623:623 */    long function_pointer = caps.glSecondaryColorP3ui;
/* 624:624 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 625:625 */    nglSecondaryColorP3ui(type, color, function_pointer);
/* 626:    */  }
/* 627:    */  
/* 628:    */  static native void nglSecondaryColorP3ui(int paramInt1, int paramInt2, long paramLong);
/* 629:    */  
/* 630:630 */  public static void glSecondaryColorP3u(int type, IntBuffer color) { ContextCapabilities caps = GLContext.getCapabilities();
/* 631:631 */    long function_pointer = caps.glSecondaryColorP3uiv;
/* 632:632 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 633:633 */    BufferChecks.checkBuffer(color, 3);
/* 634:634 */    nglSecondaryColorP3uiv(type, MemoryUtil.getAddress(color), function_pointer);
/* 635:    */  }
/* 636:    */  
/* 637:    */  static native void nglSecondaryColorP3uiv(int paramInt, long paramLong1, long paramLong2);
/* 638:    */  
/* 639:639 */  public static void glVertexAttribP1ui(int index, int type, boolean normalized, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 640:640 */    long function_pointer = caps.glVertexAttribP1ui;
/* 641:641 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 642:642 */    nglVertexAttribP1ui(index, type, normalized, value, function_pointer);
/* 643:    */  }
/* 644:    */  
/* 645:    */  static native void nglVertexAttribP1ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/* 646:    */  
/* 647:647 */  public static void glVertexAttribP2ui(int index, int type, boolean normalized, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 648:648 */    long function_pointer = caps.glVertexAttribP2ui;
/* 649:649 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 650:650 */    nglVertexAttribP2ui(index, type, normalized, value, function_pointer);
/* 651:    */  }
/* 652:    */  
/* 653:    */  static native void nglVertexAttribP2ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/* 654:    */  
/* 655:655 */  public static void glVertexAttribP3ui(int index, int type, boolean normalized, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 656:656 */    long function_pointer = caps.glVertexAttribP3ui;
/* 657:657 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 658:658 */    nglVertexAttribP3ui(index, type, normalized, value, function_pointer);
/* 659:    */  }
/* 660:    */  
/* 661:    */  static native void nglVertexAttribP3ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/* 662:    */  
/* 663:663 */  public static void glVertexAttribP4ui(int index, int type, boolean normalized, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 664:664 */    long function_pointer = caps.glVertexAttribP4ui;
/* 665:665 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 666:666 */    nglVertexAttribP4ui(index, type, normalized, value, function_pointer);
/* 667:    */  }
/* 668:    */  
/* 669:    */  static native void nglVertexAttribP4ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/* 670:    */  
/* 671:671 */  public static void glVertexAttribP1u(int index, int type, boolean normalized, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 672:672 */    long function_pointer = caps.glVertexAttribP1uiv;
/* 673:673 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 674:674 */    BufferChecks.checkBuffer(value, 1);
/* 675:675 */    nglVertexAttribP1uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/* 676:    */  }
/* 677:    */  
/* 678:    */  static native void nglVertexAttribP1uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 679:    */  
/* 680:680 */  public static void glVertexAttribP2u(int index, int type, boolean normalized, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 681:681 */    long function_pointer = caps.glVertexAttribP2uiv;
/* 682:682 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 683:683 */    BufferChecks.checkBuffer(value, 2);
/* 684:684 */    nglVertexAttribP2uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/* 685:    */  }
/* 686:    */  
/* 687:    */  static native void nglVertexAttribP2uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 688:    */  
/* 689:689 */  public static void glVertexAttribP3u(int index, int type, boolean normalized, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 690:690 */    long function_pointer = caps.glVertexAttribP3uiv;
/* 691:691 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 692:692 */    BufferChecks.checkBuffer(value, 3);
/* 693:693 */    nglVertexAttribP3uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/* 694:    */  }
/* 695:    */  
/* 696:    */  static native void nglVertexAttribP3uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 697:    */  
/* 698:698 */  public static void glVertexAttribP4u(int index, int type, boolean normalized, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 699:699 */    long function_pointer = caps.glVertexAttribP4uiv;
/* 700:700 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 701:701 */    BufferChecks.checkBuffer(value, 4);
/* 702:702 */    nglVertexAttribP4uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/* 703:    */  }
/* 704:    */  
/* 705:    */  static native void nglVertexAttribP4uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 706:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL33
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */