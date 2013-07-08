/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.BufferChecks;
/*   8:    */import org.lwjgl.MemoryUtil;
/*   9:    */
/* 143:    */public final class GL40
/* 144:    */{
/* 145:    */  public static final int GL_DRAW_INDIRECT_BUFFER = 36671;
/* 146:    */  public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 36675;
/* 147:    */  public static final int GL_GEOMETRY_SHADER_INVOCATIONS = 34943;
/* 148:    */  public static final int GL_MAX_GEOMETRY_SHADER_INVOCATIONS = 36442;
/* 149:    */  public static final int GL_MIN_FRAGMENT_INTERPOLATION_OFFSET = 36443;
/* 150:    */  public static final int GL_MAX_FRAGMENT_INTERPOLATION_OFFSET = 36444;
/* 151:    */  public static final int GL_FRAGMENT_INTERPOLATION_OFFSET_BITS = 36445;
/* 152:    */  public static final int GL_MAX_VERTEX_STREAMS = 36465;
/* 153:    */  public static final int GL_DOUBLE_VEC2 = 36860;
/* 154:    */  public static final int GL_DOUBLE_VEC3 = 36861;
/* 155:    */  public static final int GL_DOUBLE_VEC4 = 36862;
/* 156:    */  public static final int GL_DOUBLE_MAT2 = 36678;
/* 157:    */  public static final int GL_DOUBLE_MAT3 = 36679;
/* 158:    */  public static final int GL_DOUBLE_MAT4 = 36680;
/* 159:    */  public static final int GL_DOUBLE_MAT2x3 = 36681;
/* 160:    */  public static final int GL_DOUBLE_MAT2x4 = 36682;
/* 161:    */  public static final int GL_DOUBLE_MAT3x2 = 36683;
/* 162:    */  public static final int GL_DOUBLE_MAT3x4 = 36684;
/* 163:    */  public static final int GL_DOUBLE_MAT4x2 = 36685;
/* 164:    */  public static final int GL_DOUBLE_MAT4x3 = 36686;
/* 165:    */  public static final int GL_SAMPLE_SHADING = 35894;
/* 166:    */  public static final int GL_MIN_SAMPLE_SHADING_VALUE = 35895;
/* 167:    */  public static final int GL_ACTIVE_SUBROUTINES = 36325;
/* 168:    */  public static final int GL_ACTIVE_SUBROUTINE_UNIFORMS = 36326;
/* 169:    */  public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 36423;
/* 170:    */  public static final int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 36424;
/* 171:    */  public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;
/* 172:    */  public static final int GL_MAX_SUBROUTINES = 36327;
/* 173:    */  public static final int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;
/* 174:    */  public static final int GL_NUM_COMPATIBLE_SUBROUTINES = 36426;
/* 175:    */  public static final int GL_COMPATIBLE_SUBROUTINES = 36427;
/* 176:    */  public static final int GL_UNIFORM_SIZE = 35384;
/* 177:    */  public static final int GL_UNIFORM_NAME_LENGTH = 35385;
/* 178:    */  public static final int GL_PATCHES = 14;
/* 179:    */  public static final int GL_PATCH_VERTICES = 36466;
/* 180:    */  public static final int GL_PATCH_DEFAULT_INNER_LEVEL = 36467;
/* 181:    */  public static final int GL_PATCH_DEFAULT_OUTER_LEVEL = 36468;
/* 182:    */  public static final int GL_TESS_CONTROL_OUTPUT_VERTICES = 36469;
/* 183:    */  public static final int GL_TESS_GEN_MODE = 36470;
/* 184:    */  public static final int GL_TESS_GEN_SPACING = 36471;
/* 185:    */  public static final int GL_TESS_GEN_VERTEX_ORDER = 36472;
/* 186:    */  public static final int GL_TESS_GEN_POINT_MODE = 36473;
/* 187:    */  public static final int GL_ISOLINES = 36474;
/* 188:    */  public static final int GL_FRACTIONAL_ODD = 36475;
/* 189:    */  public static final int GL_FRACTIONAL_EVEN = 36476;
/* 190:    */  public static final int GL_MAX_PATCH_VERTICES = 36477;
/* 191:    */  public static final int GL_MAX_TESS_GEN_LEVEL = 36478;
/* 192:    */  public static final int GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 36479;
/* 193:    */  public static final int GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 36480;
/* 194:    */  public static final int GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 36481;
/* 195:    */  public static final int GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 36482;
/* 196:    */  public static final int GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 36483;
/* 197:    */  public static final int GL_MAX_TESS_PATCH_COMPONENTS = 36484;
/* 198:    */  public static final int GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS = 36485;
/* 199:    */  public static final int GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 36486;
/* 200:    */  public static final int GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS = 36489;
/* 201:    */  public static final int GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 36490;
/* 202:    */  public static final int GL_MAX_TESS_CONTROL_INPUT_COMPONENTS = 34924;
/* 203:    */  public static final int GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS = 34925;
/* 204:    */  public static final int GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 36382;
/* 205:    */  public static final int GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 36383;
/* 206:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER = 34032;
/* 207:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 34033;
/* 208:    */  public static final int GL_TESS_EVALUATION_SHADER = 36487;
/* 209:    */  public static final int GL_TESS_CONTROL_SHADER = 36488;
/* 210:    */  public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;
/* 211:    */  public static final int GL_TEXTURE_BINDING_CUBE_MAP_ARRAY = 36874;
/* 212:    */  public static final int GL_PROXY_TEXTURE_CUBE_MAP_ARRAY = 36875;
/* 213:    */  public static final int GL_SAMPLER_CUBE_MAP_ARRAY = 36876;
/* 214:    */  public static final int GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW = 36877;
/* 215:    */  public static final int GL_INT_SAMPLER_CUBE_MAP_ARRAY = 36878;
/* 216:    */  public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY = 36879;
/* 217:    */  public static final int GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET_ARB = 36446;
/* 218:    */  public static final int GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET_ARB = 36447;
/* 219:    */  public static final int GL_MAX_PROGRAM_TEXTURE_GATHER_COMPONENTS_ARB = 36767;
/* 220:    */  public static final int GL_TRANSFORM_FEEDBACK = 36386;
/* 221:    */  public static final int GL_TRANSFORM_FEEDBACK_PAUSED = 36387;
/* 222:    */  public static final int GL_TRANSFORM_FEEDBACK_ACTIVE = 36388;
/* 223:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
/* 224:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
/* 225:    */  public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;
/* 226:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 36464;
/* 227:    */  
/* 228:    */  public static void glBlendEquationi(int buf, int mode)
/* 229:    */  {
/* 230:230 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 231:231 */    long function_pointer = caps.glBlendEquationi;
/* 232:232 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 233:233 */    nglBlendEquationi(buf, mode, function_pointer);
/* 234:    */  }
/* 235:    */  
/* 236:    */  static native void nglBlendEquationi(int paramInt1, int paramInt2, long paramLong);
/* 237:    */  
/* 238:238 */  public static void glBlendEquationSeparatei(int buf, int modeRGB, int modeAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 239:239 */    long function_pointer = caps.glBlendEquationSeparatei;
/* 240:240 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 241:241 */    nglBlendEquationSeparatei(buf, modeRGB, modeAlpha, function_pointer);
/* 242:    */  }
/* 243:    */  
/* 244:    */  static native void nglBlendEquationSeparatei(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 245:    */  
/* 246:246 */  public static void glBlendFunci(int buf, int src, int dst) { ContextCapabilities caps = GLContext.getCapabilities();
/* 247:247 */    long function_pointer = caps.glBlendFunci;
/* 248:248 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 249:249 */    nglBlendFunci(buf, src, dst, function_pointer);
/* 250:    */  }
/* 251:    */  
/* 252:    */  static native void nglBlendFunci(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 253:    */  
/* 254:254 */  public static void glBlendFuncSeparatei(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 255:255 */    long function_pointer = caps.glBlendFuncSeparatei;
/* 256:256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 257:257 */    nglBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
/* 258:    */  }
/* 259:    */  
/* 260:    */  static native void nglBlendFuncSeparatei(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 261:    */  
/* 262:262 */  public static void glDrawArraysIndirect(int mode, ByteBuffer indirect) { ContextCapabilities caps = GLContext.getCapabilities();
/* 263:263 */    long function_pointer = caps.glDrawArraysIndirect;
/* 264:264 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 265:265 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 266:266 */    BufferChecks.checkBuffer(indirect, 16);
/* 267:267 */    nglDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), function_pointer); }
/* 268:    */  
/* 269:    */  static native void nglDrawArraysIndirect(int paramInt, long paramLong1, long paramLong2);
/* 270:    */  
/* 271:271 */  public static void glDrawArraysIndirect(int mode, long indirect_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 272:272 */    long function_pointer = caps.glDrawArraysIndirect;
/* 273:273 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 274:274 */    GLChecks.ensureIndirectBOenabled(caps);
/* 275:275 */    nglDrawArraysIndirectBO(mode, indirect_buffer_offset, function_pointer);
/* 276:    */  }
/* 277:    */  
/* 278:    */  static native void nglDrawArraysIndirectBO(int paramInt, long paramLong1, long paramLong2);
/* 279:    */  
/* 280:    */  public static void glDrawArraysIndirect(int mode, IntBuffer indirect) {
/* 281:281 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 282:282 */    long function_pointer = caps.glDrawArraysIndirect;
/* 283:283 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 284:284 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 285:285 */    BufferChecks.checkBuffer(indirect, 4);
/* 286:286 */    nglDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), function_pointer);
/* 287:    */  }
/* 288:    */  
/* 289:    */  public static void glDrawElementsIndirect(int mode, int type, ByteBuffer indirect) {
/* 290:290 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 291:291 */    long function_pointer = caps.glDrawElementsIndirect;
/* 292:292 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 293:293 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 294:294 */    BufferChecks.checkBuffer(indirect, 20);
/* 295:295 */    nglDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), function_pointer); }
/* 296:    */  
/* 297:    */  static native void nglDrawElementsIndirect(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 298:    */  
/* 299:299 */  public static void glDrawElementsIndirect(int mode, int type, long indirect_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 300:300 */    long function_pointer = caps.glDrawElementsIndirect;
/* 301:301 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 302:302 */    GLChecks.ensureIndirectBOenabled(caps);
/* 303:303 */    nglDrawElementsIndirectBO(mode, type, indirect_buffer_offset, function_pointer);
/* 304:    */  }
/* 305:    */  
/* 306:    */  static native void nglDrawElementsIndirectBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 307:    */  
/* 308:    */  public static void glDrawElementsIndirect(int mode, int type, IntBuffer indirect) {
/* 309:309 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 310:310 */    long function_pointer = caps.glDrawElementsIndirect;
/* 311:311 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 312:312 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 313:313 */    BufferChecks.checkBuffer(indirect, 5);
/* 314:314 */    nglDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), function_pointer);
/* 315:    */  }
/* 316:    */  
/* 317:    */  public static void glUniform1d(int location, double x) {
/* 318:318 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 319:319 */    long function_pointer = caps.glUniform1d;
/* 320:320 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 321:321 */    nglUniform1d(location, x, function_pointer);
/* 322:    */  }
/* 323:    */  
/* 324:    */  static native void nglUniform1d(int paramInt, double paramDouble, long paramLong);
/* 325:    */  
/* 326:326 */  public static void glUniform2d(int location, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 327:327 */    long function_pointer = caps.glUniform2d;
/* 328:328 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 329:329 */    nglUniform2d(location, x, y, function_pointer);
/* 330:    */  }
/* 331:    */  
/* 332:    */  static native void nglUniform2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/* 333:    */  
/* 334:334 */  public static void glUniform3d(int location, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 335:335 */    long function_pointer = caps.glUniform3d;
/* 336:336 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 337:337 */    nglUniform3d(location, x, y, z, function_pointer);
/* 338:    */  }
/* 339:    */  
/* 340:    */  static native void nglUniform3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 341:    */  
/* 342:342 */  public static void glUniform4d(int location, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 343:343 */    long function_pointer = caps.glUniform4d;
/* 344:344 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 345:345 */    nglUniform4d(location, x, y, z, w, function_pointer);
/* 346:    */  }
/* 347:    */  
/* 348:    */  static native void nglUniform4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 349:    */  
/* 350:350 */  public static void glUniform1(int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 351:351 */    long function_pointer = caps.glUniform1dv;
/* 352:352 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 353:353 */    BufferChecks.checkDirect(value);
/* 354:354 */    nglUniform1dv(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 355:    */  }
/* 356:    */  
/* 357:    */  static native void nglUniform1dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 358:    */  
/* 359:359 */  public static void glUniform2(int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 360:360 */    long function_pointer = caps.glUniform2dv;
/* 361:361 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 362:362 */    BufferChecks.checkDirect(value);
/* 363:363 */    nglUniform2dv(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 364:    */  }
/* 365:    */  
/* 366:    */  static native void nglUniform2dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 367:    */  
/* 368:368 */  public static void glUniform3(int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 369:369 */    long function_pointer = caps.glUniform3dv;
/* 370:370 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 371:371 */    BufferChecks.checkDirect(value);
/* 372:372 */    nglUniform3dv(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 373:    */  }
/* 374:    */  
/* 375:    */  static native void nglUniform3dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 376:    */  
/* 377:377 */  public static void glUniform4(int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 378:378 */    long function_pointer = caps.glUniform4dv;
/* 379:379 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 380:380 */    BufferChecks.checkDirect(value);
/* 381:381 */    nglUniform4dv(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 382:    */  }
/* 383:    */  
/* 384:    */  static native void nglUniform4dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 385:    */  
/* 386:386 */  public static void glUniformMatrix2(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 387:387 */    long function_pointer = caps.glUniformMatrix2dv;
/* 388:388 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 389:389 */    BufferChecks.checkDirect(value);
/* 390:390 */    nglUniformMatrix2dv(location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 391:    */  }
/* 392:    */  
/* 393:    */  static native void nglUniformMatrix2dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 394:    */  
/* 395:395 */  public static void glUniformMatrix3(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 396:396 */    long function_pointer = caps.glUniformMatrix3dv;
/* 397:397 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 398:398 */    BufferChecks.checkDirect(value);
/* 399:399 */    nglUniformMatrix3dv(location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 400:    */  }
/* 401:    */  
/* 402:    */  static native void nglUniformMatrix3dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 403:    */  
/* 404:404 */  public static void glUniformMatrix4(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 405:405 */    long function_pointer = caps.glUniformMatrix4dv;
/* 406:406 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 407:407 */    BufferChecks.checkDirect(value);
/* 408:408 */    nglUniformMatrix4dv(location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 409:    */  }
/* 410:    */  
/* 411:    */  static native void nglUniformMatrix4dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 412:    */  
/* 413:413 */  public static void glUniformMatrix2x3(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 414:414 */    long function_pointer = caps.glUniformMatrix2x3dv;
/* 415:415 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 416:416 */    BufferChecks.checkDirect(value);
/* 417:417 */    nglUniformMatrix2x3dv(location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 418:    */  }
/* 419:    */  
/* 420:    */  static native void nglUniformMatrix2x3dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 421:    */  
/* 422:422 */  public static void glUniformMatrix2x4(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 423:423 */    long function_pointer = caps.glUniformMatrix2x4dv;
/* 424:424 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 425:425 */    BufferChecks.checkDirect(value);
/* 426:426 */    nglUniformMatrix2x4dv(location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 427:    */  }
/* 428:    */  
/* 429:    */  static native void nglUniformMatrix2x4dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 430:    */  
/* 431:431 */  public static void glUniformMatrix3x2(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 432:432 */    long function_pointer = caps.glUniformMatrix3x2dv;
/* 433:433 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 434:434 */    BufferChecks.checkDirect(value);
/* 435:435 */    nglUniformMatrix3x2dv(location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 436:    */  }
/* 437:    */  
/* 438:    */  static native void nglUniformMatrix3x2dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 439:    */  
/* 440:440 */  public static void glUniformMatrix3x4(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 441:441 */    long function_pointer = caps.glUniformMatrix3x4dv;
/* 442:442 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 443:443 */    BufferChecks.checkDirect(value);
/* 444:444 */    nglUniformMatrix3x4dv(location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 445:    */  }
/* 446:    */  
/* 447:    */  static native void nglUniformMatrix3x4dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 448:    */  
/* 449:449 */  public static void glUniformMatrix4x2(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 450:450 */    long function_pointer = caps.glUniformMatrix4x2dv;
/* 451:451 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 452:452 */    BufferChecks.checkDirect(value);
/* 453:453 */    nglUniformMatrix4x2dv(location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 454:    */  }
/* 455:    */  
/* 456:    */  static native void nglUniformMatrix4x2dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 457:    */  
/* 458:458 */  public static void glUniformMatrix4x3(int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 459:459 */    long function_pointer = caps.glUniformMatrix4x3dv;
/* 460:460 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 461:461 */    BufferChecks.checkDirect(value);
/* 462:462 */    nglUniformMatrix4x3dv(location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 463:    */  }
/* 464:    */  
/* 465:    */  static native void nglUniformMatrix4x3dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/* 466:    */  
/* 467:467 */  public static void glGetUniform(int program, int location, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 468:468 */    long function_pointer = caps.glGetUniformdv;
/* 469:469 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 470:470 */    BufferChecks.checkDirect(params);
/* 471:471 */    nglGetUniformdv(program, location, MemoryUtil.getAddress(params), function_pointer);
/* 472:    */  }
/* 473:    */  
/* 474:    */  static native void nglGetUniformdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 475:    */  
/* 476:476 */  public static void glMinSampleShading(float value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 477:477 */    long function_pointer = caps.glMinSampleShading;
/* 478:478 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 479:479 */    nglMinSampleShading(value, function_pointer);
/* 480:    */  }
/* 481:    */  
/* 482:    */  static native void nglMinSampleShading(float paramFloat, long paramLong);
/* 483:    */  
/* 484:484 */  public static int glGetSubroutineUniformLocation(int program, int shadertype, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/* 485:485 */    long function_pointer = caps.glGetSubroutineUniformLocation;
/* 486:486 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 487:487 */    BufferChecks.checkDirect(name);
/* 488:488 */    BufferChecks.checkNullTerminated(name);
/* 489:489 */    int __result = nglGetSubroutineUniformLocation(program, shadertype, MemoryUtil.getAddress(name), function_pointer);
/* 490:490 */    return __result;
/* 491:    */  }
/* 492:    */  
/* 493:    */  static native int nglGetSubroutineUniformLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 494:    */  
/* 495:    */  public static int glGetSubroutineUniformLocation(int program, int shadertype, CharSequence name) {
/* 496:496 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 497:497 */    long function_pointer = caps.glGetSubroutineUniformLocation;
/* 498:498 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 499:499 */    int __result = nglGetSubroutineUniformLocation(program, shadertype, APIUtil.getBufferNT(caps, name), function_pointer);
/* 500:500 */    return __result;
/* 501:    */  }
/* 502:    */  
/* 503:    */  public static int glGetSubroutineIndex(int program, int shadertype, ByteBuffer name) {
/* 504:504 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 505:505 */    long function_pointer = caps.glGetSubroutineIndex;
/* 506:506 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 507:507 */    BufferChecks.checkDirect(name);
/* 508:508 */    BufferChecks.checkNullTerminated(name);
/* 509:509 */    int __result = nglGetSubroutineIndex(program, shadertype, MemoryUtil.getAddress(name), function_pointer);
/* 510:510 */    return __result;
/* 511:    */  }
/* 512:    */  
/* 513:    */  static native int nglGetSubroutineIndex(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 514:    */  
/* 515:    */  public static int glGetSubroutineIndex(int program, int shadertype, CharSequence name) {
/* 516:516 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 517:517 */    long function_pointer = caps.glGetSubroutineIndex;
/* 518:518 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 519:519 */    int __result = nglGetSubroutineIndex(program, shadertype, APIUtil.getBufferNT(caps, name), function_pointer);
/* 520:520 */    return __result;
/* 521:    */  }
/* 522:    */  
/* 523:    */  public static void glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname, IntBuffer values) {
/* 524:524 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 525:525 */    long function_pointer = caps.glGetActiveSubroutineUniformiv;
/* 526:526 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 527:527 */    BufferChecks.checkBuffer(values, 1);
/* 528:528 */    nglGetActiveSubroutineUniformiv(program, shadertype, index, pname, MemoryUtil.getAddress(values), function_pointer);
/* 529:    */  }
/* 530:    */  
/* 533:    */  static native void nglGetActiveSubroutineUniformiv(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 534:    */  
/* 536:    */  @Deprecated
/* 537:    */  public static int glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname)
/* 538:    */  {
/* 539:539 */    return glGetActiveSubroutineUniformi(program, shadertype, index, pname);
/* 540:    */  }
/* 541:    */  
/* 542:    */  public static int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname)
/* 543:    */  {
/* 544:544 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 545:545 */    long function_pointer = caps.glGetActiveSubroutineUniformiv;
/* 546:546 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 547:547 */    IntBuffer values = APIUtil.getBufferInt(caps);
/* 548:548 */    nglGetActiveSubroutineUniformiv(program, shadertype, index, pname, MemoryUtil.getAddress(values), function_pointer);
/* 549:549 */    return values.get(0);
/* 550:    */  }
/* 551:    */  
/* 552:    */  public static void glGetActiveSubroutineUniformName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/* 553:553 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 554:554 */    long function_pointer = caps.glGetActiveSubroutineUniformName;
/* 555:555 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 556:556 */    if (length != null)
/* 557:557 */      BufferChecks.checkBuffer(length, 1);
/* 558:558 */    BufferChecks.checkDirect(name);
/* 559:559 */    nglGetActiveSubroutineUniformName(program, shadertype, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(name), function_pointer);
/* 560:    */  }
/* 561:    */  
/* 562:    */  static native void nglGetActiveSubroutineUniformName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/* 563:    */  
/* 564:    */  public static String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize) {
/* 565:565 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 566:566 */    long function_pointer = caps.glGetActiveSubroutineUniformName;
/* 567:567 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 568:568 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 569:569 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufsize);
/* 570:570 */    nglGetActiveSubroutineUniformName(program, shadertype, index, bufsize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(name), function_pointer);
/* 571:571 */    name.limit(name_length.get(0));
/* 572:572 */    return APIUtil.getString(caps, name);
/* 573:    */  }
/* 574:    */  
/* 575:    */  public static void glGetActiveSubroutineName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/* 576:576 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 577:577 */    long function_pointer = caps.glGetActiveSubroutineName;
/* 578:578 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 579:579 */    if (length != null)
/* 580:580 */      BufferChecks.checkBuffer(length, 1);
/* 581:581 */    BufferChecks.checkDirect(name);
/* 582:582 */    nglGetActiveSubroutineName(program, shadertype, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(name), function_pointer);
/* 583:    */  }
/* 584:    */  
/* 585:    */  static native void nglGetActiveSubroutineName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/* 586:    */  
/* 587:    */  public static String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize) {
/* 588:588 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 589:589 */    long function_pointer = caps.glGetActiveSubroutineName;
/* 590:590 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 591:591 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 592:592 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufsize);
/* 593:593 */    nglGetActiveSubroutineName(program, shadertype, index, bufsize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(name), function_pointer);
/* 594:594 */    name.limit(name_length.get(0));
/* 595:595 */    return APIUtil.getString(caps, name);
/* 596:    */  }
/* 597:    */  
/* 598:    */  public static void glUniformSubroutinesu(int shadertype, IntBuffer indices) {
/* 599:599 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 600:600 */    long function_pointer = caps.glUniformSubroutinesuiv;
/* 601:601 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 602:602 */    BufferChecks.checkDirect(indices);
/* 603:603 */    nglUniformSubroutinesuiv(shadertype, indices.remaining(), MemoryUtil.getAddress(indices), function_pointer);
/* 604:    */  }
/* 605:    */  
/* 606:    */  static native void nglUniformSubroutinesuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 607:    */  
/* 608:608 */  public static void glGetUniformSubroutineu(int shadertype, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 609:609 */    long function_pointer = caps.glGetUniformSubroutineuiv;
/* 610:610 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 611:611 */    BufferChecks.checkBuffer(params, 1);
/* 612:612 */    nglGetUniformSubroutineuiv(shadertype, location, MemoryUtil.getAddress(params), function_pointer);
/* 613:    */  }
/* 614:    */  
/* 617:    */  static native void nglGetUniformSubroutineuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 618:    */  
/* 620:    */  @Deprecated
/* 621:    */  public static int glGetUniformSubroutineu(int shadertype, int location)
/* 622:    */  {
/* 623:623 */    return glGetUniformSubroutineui(shadertype, location);
/* 624:    */  }
/* 625:    */  
/* 626:    */  public static int glGetUniformSubroutineui(int shadertype, int location)
/* 627:    */  {
/* 628:628 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 629:629 */    long function_pointer = caps.glGetUniformSubroutineuiv;
/* 630:630 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 631:631 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 632:632 */    nglGetUniformSubroutineuiv(shadertype, location, MemoryUtil.getAddress(params), function_pointer);
/* 633:633 */    return params.get(0);
/* 634:    */  }
/* 635:    */  
/* 636:    */  public static void glGetProgramStage(int program, int shadertype, int pname, IntBuffer values) {
/* 637:637 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 638:638 */    long function_pointer = caps.glGetProgramStageiv;
/* 639:639 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 640:640 */    BufferChecks.checkBuffer(values, 1);
/* 641:641 */    nglGetProgramStageiv(program, shadertype, pname, MemoryUtil.getAddress(values), function_pointer);
/* 642:    */  }
/* 643:    */  
/* 646:    */  static native void nglGetProgramStageiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 647:    */  
/* 649:    */  @Deprecated
/* 650:    */  public static int glGetProgramStage(int program, int shadertype, int pname)
/* 651:    */  {
/* 652:652 */    return glGetProgramStagei(program, shadertype, pname);
/* 653:    */  }
/* 654:    */  
/* 655:    */  public static int glGetProgramStagei(int program, int shadertype, int pname)
/* 656:    */  {
/* 657:657 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 658:658 */    long function_pointer = caps.glGetProgramStageiv;
/* 659:659 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 660:660 */    IntBuffer values = APIUtil.getBufferInt(caps);
/* 661:661 */    nglGetProgramStageiv(program, shadertype, pname, MemoryUtil.getAddress(values), function_pointer);
/* 662:662 */    return values.get(0);
/* 663:    */  }
/* 664:    */  
/* 665:    */  public static void glPatchParameteri(int pname, int value) {
/* 666:666 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 667:667 */    long function_pointer = caps.glPatchParameteri;
/* 668:668 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 669:669 */    nglPatchParameteri(pname, value, function_pointer);
/* 670:    */  }
/* 671:    */  
/* 672:    */  static native void nglPatchParameteri(int paramInt1, int paramInt2, long paramLong);
/* 673:    */  
/* 674:674 */  public static void glPatchParameter(int pname, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 675:675 */    long function_pointer = caps.glPatchParameterfv;
/* 676:676 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 677:677 */    BufferChecks.checkBuffer(values, 4);
/* 678:678 */    nglPatchParameterfv(pname, MemoryUtil.getAddress(values), function_pointer);
/* 679:    */  }
/* 680:    */  
/* 681:    */  static native void nglPatchParameterfv(int paramInt, long paramLong1, long paramLong2);
/* 682:    */  
/* 683:683 */  public static void glBindTransformFeedback(int target, int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 684:684 */    long function_pointer = caps.glBindTransformFeedback;
/* 685:685 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 686:686 */    nglBindTransformFeedback(target, id, function_pointer);
/* 687:    */  }
/* 688:    */  
/* 689:    */  static native void nglBindTransformFeedback(int paramInt1, int paramInt2, long paramLong);
/* 690:    */  
/* 691:691 */  public static void glDeleteTransformFeedbacks(IntBuffer ids) { ContextCapabilities caps = GLContext.getCapabilities();
/* 692:692 */    long function_pointer = caps.glDeleteTransformFeedbacks;
/* 693:693 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 694:694 */    BufferChecks.checkDirect(ids);
/* 695:695 */    nglDeleteTransformFeedbacks(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/* 696:    */  }
/* 697:    */  
/* 698:    */  static native void nglDeleteTransformFeedbacks(int paramInt, long paramLong1, long paramLong2);
/* 699:    */  
/* 700:    */  public static void glDeleteTransformFeedbacks(int id) {
/* 701:701 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 702:702 */    long function_pointer = caps.glDeleteTransformFeedbacks;
/* 703:703 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 704:704 */    nglDeleteTransformFeedbacks(1, APIUtil.getInt(caps, id), function_pointer);
/* 705:    */  }
/* 706:    */  
/* 707:    */  public static void glGenTransformFeedbacks(IntBuffer ids) {
/* 708:708 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 709:709 */    long function_pointer = caps.glGenTransformFeedbacks;
/* 710:710 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 711:711 */    BufferChecks.checkDirect(ids);
/* 712:712 */    nglGenTransformFeedbacks(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/* 713:    */  }
/* 714:    */  
/* 715:    */  static native void nglGenTransformFeedbacks(int paramInt, long paramLong1, long paramLong2);
/* 716:    */  
/* 717:    */  public static int glGenTransformFeedbacks() {
/* 718:718 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 719:719 */    long function_pointer = caps.glGenTransformFeedbacks;
/* 720:720 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 721:721 */    IntBuffer ids = APIUtil.getBufferInt(caps);
/* 722:722 */    nglGenTransformFeedbacks(1, MemoryUtil.getAddress(ids), function_pointer);
/* 723:723 */    return ids.get(0);
/* 724:    */  }
/* 725:    */  
/* 726:    */  public static boolean glIsTransformFeedback(int id) {
/* 727:727 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 728:728 */    long function_pointer = caps.glIsTransformFeedback;
/* 729:729 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 730:730 */    boolean __result = nglIsTransformFeedback(id, function_pointer);
/* 731:731 */    return __result;
/* 732:    */  }
/* 733:    */  
/* 734:    */  static native boolean nglIsTransformFeedback(int paramInt, long paramLong);
/* 735:    */  
/* 736:736 */  public static void glPauseTransformFeedback() { ContextCapabilities caps = GLContext.getCapabilities();
/* 737:737 */    long function_pointer = caps.glPauseTransformFeedback;
/* 738:738 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 739:739 */    nglPauseTransformFeedback(function_pointer);
/* 740:    */  }
/* 741:    */  
/* 742:    */  static native void nglPauseTransformFeedback(long paramLong);
/* 743:    */  
/* 744:744 */  public static void glResumeTransformFeedback() { ContextCapabilities caps = GLContext.getCapabilities();
/* 745:745 */    long function_pointer = caps.glResumeTransformFeedback;
/* 746:746 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 747:747 */    nglResumeTransformFeedback(function_pointer);
/* 748:    */  }
/* 749:    */  
/* 750:    */  static native void nglResumeTransformFeedback(long paramLong);
/* 751:    */  
/* 752:752 */  public static void glDrawTransformFeedback(int mode, int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 753:753 */    long function_pointer = caps.glDrawTransformFeedback;
/* 754:754 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 755:755 */    nglDrawTransformFeedback(mode, id, function_pointer);
/* 756:    */  }
/* 757:    */  
/* 758:    */  static native void nglDrawTransformFeedback(int paramInt1, int paramInt2, long paramLong);
/* 759:    */  
/* 760:760 */  public static void glDrawTransformFeedbackStream(int mode, int id, int stream) { ContextCapabilities caps = GLContext.getCapabilities();
/* 761:761 */    long function_pointer = caps.glDrawTransformFeedbackStream;
/* 762:762 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 763:763 */    nglDrawTransformFeedbackStream(mode, id, stream, function_pointer);
/* 764:    */  }
/* 765:    */  
/* 766:    */  static native void nglDrawTransformFeedbackStream(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 767:    */  
/* 768:768 */  public static void glBeginQueryIndexed(int target, int index, int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 769:769 */    long function_pointer = caps.glBeginQueryIndexed;
/* 770:770 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 771:771 */    nglBeginQueryIndexed(target, index, id, function_pointer);
/* 772:    */  }
/* 773:    */  
/* 774:    */  static native void nglBeginQueryIndexed(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 775:    */  
/* 776:776 */  public static void glEndQueryIndexed(int target, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 777:777 */    long function_pointer = caps.glEndQueryIndexed;
/* 778:778 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 779:779 */    nglEndQueryIndexed(target, index, function_pointer);
/* 780:    */  }
/* 781:    */  
/* 782:    */  static native void nglEndQueryIndexed(int paramInt1, int paramInt2, long paramLong);
/* 783:    */  
/* 784:784 */  public static void glGetQueryIndexed(int target, int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 785:785 */    long function_pointer = caps.glGetQueryIndexediv;
/* 786:786 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 787:787 */    BufferChecks.checkBuffer(params, 1);
/* 788:788 */    nglGetQueryIndexediv(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 789:    */  }
/* 790:    */  
/* 793:    */  static native void nglGetQueryIndexediv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 794:    */  
/* 796:    */  @Deprecated
/* 797:    */  public static int glGetQueryIndexed(int target, int index, int pname)
/* 798:    */  {
/* 799:799 */    return glGetQueryIndexedi(target, index, pname);
/* 800:    */  }
/* 801:    */  
/* 802:    */  public static int glGetQueryIndexedi(int target, int index, int pname)
/* 803:    */  {
/* 804:804 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 805:805 */    long function_pointer = caps.glGetQueryIndexediv;
/* 806:806 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 807:807 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 808:808 */    nglGetQueryIndexediv(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 809:809 */    return params.get(0);
/* 810:    */  }
/* 811:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL40
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */