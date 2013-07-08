/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.nio.LongBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */import org.lwjgl.BufferChecks;
/*   9:    */import org.lwjgl.MemoryUtil;
/*  10:    */
/* 144:    */public final class GL32
/* 145:    */{
/* 146:    */  public static final int GL_CONTEXT_PROFILE_MASK = 37158;
/* 147:    */  public static final int GL_CONTEXT_CORE_PROFILE_BIT = 1;
/* 148:    */  public static final int GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 2;
/* 149:    */  public static final int GL_MAX_VERTEX_OUTPUT_COMPONENTS = 37154;
/* 150:    */  public static final int GL_MAX_GEOMETRY_INPUT_COMPONENTS = 37155;
/* 151:    */  public static final int GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 37156;
/* 152:    */  public static final int GL_MAX_FRAGMENT_INPUT_COMPONENTS = 37157;
/* 153:    */  public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
/* 154:    */  public static final int GL_LAST_VERTEX_CONVENTION = 36430;
/* 155:    */  public static final int GL_PROVOKING_VERTEX = 36431;
/* 156:    */  public static final int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 36428;
/* 157:    */  public static final int GL_TEXTURE_CUBE_MAP_SEAMLESS = 34895;
/* 158:    */  public static final int GL_SAMPLE_POSITION = 36432;
/* 159:    */  public static final int GL_SAMPLE_MASK = 36433;
/* 160:    */  public static final int GL_SAMPLE_MASK_VALUE = 36434;
/* 161:    */  public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
/* 162:    */  public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 37121;
/* 163:    */  public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
/* 164:    */  public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 37123;
/* 165:    */  public static final int GL_MAX_SAMPLE_MASK_WORDS = 36441;
/* 166:    */  public static final int GL_MAX_COLOR_TEXTURE_SAMPLES = 37134;
/* 167:    */  public static final int GL_MAX_DEPTH_TEXTURE_SAMPLES = 37135;
/* 168:    */  public static final int GL_MAX_INTEGER_SAMPLES = 37136;
/* 169:    */  public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE = 37124;
/* 170:    */  public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 37125;
/* 171:    */  public static final int GL_TEXTURE_SAMPLES = 37126;
/* 172:    */  public static final int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 37127;
/* 173:    */  public static final int GL_SAMPLER_2D_MULTISAMPLE = 37128;
/* 174:    */  public static final int GL_INT_SAMPLER_2D_MULTISAMPLE = 37129;
/* 175:    */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 37130;
/* 176:    */  public static final int GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 37131;
/* 177:    */  public static final int GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37132;
/* 178:    */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37133;
/* 179:    */  public static final int GL_DEPTH_CLAMP = 34383;
/* 180:    */  public static final int GL_GEOMETRY_SHADER = 36313;
/* 181:    */  public static final int GL_GEOMETRY_VERTICES_OUT = 36314;
/* 182:    */  public static final int GL_GEOMETRY_INPUT_TYPE = 36315;
/* 183:    */  public static final int GL_GEOMETRY_OUTPUT_TYPE = 36316;
/* 184:    */  public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 35881;
/* 185:    */  public static final int GL_MAX_VARYING_COMPONENTS = 35659;
/* 186:    */  public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS = 36319;
/* 187:    */  public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES = 36320;
/* 188:    */  public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 36321;
/* 189:    */  public static final int GL_LINES_ADJACENCY = 10;
/* 190:    */  public static final int GL_LINE_STRIP_ADJACENCY = 11;
/* 191:    */  public static final int GL_TRIANGLES_ADJACENCY = 12;
/* 192:    */  public static final int GL_TRIANGLE_STRIP_ADJACENCY = 13;
/* 193:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 36264;
/* 194:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED = 36263;
/* 195:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
/* 196:    */  public static final int GL_PROGRAM_POINT_SIZE = 34370;
/* 197:    */  public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 37137;
/* 198:    */  public static final int GL_OBJECT_TYPE = 37138;
/* 199:    */  public static final int GL_SYNC_CONDITION = 37139;
/* 200:    */  public static final int GL_SYNC_STATUS = 37140;
/* 201:    */  public static final int GL_SYNC_FLAGS = 37141;
/* 202:    */  public static final int GL_SYNC_FENCE = 37142;
/* 203:    */  public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 37143;
/* 204:    */  public static final int GL_UNSIGNALED = 37144;
/* 205:    */  public static final int GL_SIGNALED = 37145;
/* 206:    */  public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;
/* 207:    */  public static final long GL_TIMEOUT_IGNORED = -1L;
/* 208:    */  public static final int GL_ALREADY_SIGNALED = 37146;
/* 209:    */  public static final int GL_TIMEOUT_EXPIRED = 37147;
/* 210:    */  public static final int GL_CONDITION_SATISFIED = 37148;
/* 211:    */  public static final int GL_WAIT_FAILED = 37149;
/* 212:    */  
/* 213:    */  public static void glGetBufferParameter(int target, int pname, LongBuffer params)
/* 214:    */  {
/* 215:215 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 216:216 */    long function_pointer = caps.glGetBufferParameteri64v;
/* 217:217 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 218:218 */    BufferChecks.checkBuffer(params, 4);
/* 219:219 */    nglGetBufferParameteri64v(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 220:    */  }
/* 221:    */  
/* 224:    */  static native void nglGetBufferParameteri64v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 225:    */  
/* 227:    */  @Deprecated
/* 228:    */  public static long glGetBufferParameter(int target, int pname)
/* 229:    */  {
/* 230:230 */    return glGetBufferParameteri64(target, pname);
/* 231:    */  }
/* 232:    */  
/* 233:    */  public static long glGetBufferParameteri64(int target, int pname)
/* 234:    */  {
/* 235:235 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 236:236 */    long function_pointer = caps.glGetBufferParameteri64v;
/* 237:237 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 238:238 */    LongBuffer params = APIUtil.getBufferLong(caps);
/* 239:239 */    nglGetBufferParameteri64v(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 240:240 */    return params.get(0);
/* 241:    */  }
/* 242:    */  
/* 243:    */  public static void glDrawElementsBaseVertex(int mode, ByteBuffer indices, int basevertex) {
/* 244:244 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 245:245 */    long function_pointer = caps.glDrawElementsBaseVertex;
/* 246:246 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 247:247 */    GLChecks.ensureElementVBOdisabled(caps);
/* 248:248 */    BufferChecks.checkDirect(indices);
/* 249:249 */    nglDrawElementsBaseVertex(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/* 250:    */  }
/* 251:    */  
/* 252:252 */  public static void glDrawElementsBaseVertex(int mode, IntBuffer indices, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 253:253 */    long function_pointer = caps.glDrawElementsBaseVertex;
/* 254:254 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 255:255 */    GLChecks.ensureElementVBOdisabled(caps);
/* 256:256 */    BufferChecks.checkDirect(indices);
/* 257:257 */    nglDrawElementsBaseVertex(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/* 258:    */  }
/* 259:    */  
/* 260:260 */  public static void glDrawElementsBaseVertex(int mode, ShortBuffer indices, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 261:261 */    long function_pointer = caps.glDrawElementsBaseVertex;
/* 262:262 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 263:263 */    GLChecks.ensureElementVBOdisabled(caps);
/* 264:264 */    BufferChecks.checkDirect(indices);
/* 265:265 */    nglDrawElementsBaseVertex(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), basevertex, function_pointer); }
/* 266:    */  
/* 267:    */  static native void nglDrawElementsBaseVertex(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/* 268:    */  
/* 269:269 */  public static void glDrawElementsBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 270:270 */    long function_pointer = caps.glDrawElementsBaseVertex;
/* 271:271 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 272:272 */    GLChecks.ensureElementVBOenabled(caps);
/* 273:273 */    nglDrawElementsBaseVertexBO(mode, indices_count, type, indices_buffer_offset, basevertex, function_pointer);
/* 274:    */  }
/* 275:    */  
/* 276:    */  static native void nglDrawElementsBaseVertexBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/* 277:    */  
/* 278:278 */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ByteBuffer indices, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 279:279 */    long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 280:280 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 281:281 */    GLChecks.ensureElementVBOdisabled(caps);
/* 282:282 */    BufferChecks.checkDirect(indices);
/* 283:283 */    nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), 5121, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/* 284:    */  }
/* 285:    */  
/* 286:286 */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, IntBuffer indices, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 287:287 */    long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 288:288 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 289:289 */    GLChecks.ensureElementVBOdisabled(caps);
/* 290:290 */    BufferChecks.checkDirect(indices);
/* 291:291 */    nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), 5125, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/* 292:    */  }
/* 293:    */  
/* 294:294 */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ShortBuffer indices, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 295:295 */    long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 296:296 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 297:297 */    GLChecks.ensureElementVBOdisabled(caps);
/* 298:298 */    BufferChecks.checkDirect(indices);
/* 299:299 */    nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), 5123, MemoryUtil.getAddress(indices), basevertex, function_pointer); }
/* 300:    */  
/* 301:    */  static native void nglDrawRangeElementsBaseVertex(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, int paramInt6, long paramLong2);
/* 302:    */  
/* 303:303 */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 304:304 */    long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 305:305 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 306:306 */    GLChecks.ensureElementVBOenabled(caps);
/* 307:307 */    nglDrawRangeElementsBaseVertexBO(mode, start, end, indices_count, type, indices_buffer_offset, basevertex, function_pointer);
/* 308:    */  }
/* 309:    */  
/* 310:    */  static native void nglDrawRangeElementsBaseVertexBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, int paramInt6, long paramLong2);
/* 311:    */  
/* 312:312 */  public static void glDrawElementsInstancedBaseVertex(int mode, ByteBuffer indices, int primcount, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 313:313 */    long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 314:314 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 315:315 */    GLChecks.ensureElementVBOdisabled(caps);
/* 316:316 */    BufferChecks.checkDirect(indices);
/* 317:317 */    nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, basevertex, function_pointer);
/* 318:    */  }
/* 319:    */  
/* 320:320 */  public static void glDrawElementsInstancedBaseVertex(int mode, IntBuffer indices, int primcount, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 321:321 */    long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 322:322 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 323:323 */    GLChecks.ensureElementVBOdisabled(caps);
/* 324:324 */    BufferChecks.checkDirect(indices);
/* 325:325 */    nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, basevertex, function_pointer);
/* 326:    */  }
/* 327:    */  
/* 328:328 */  public static void glDrawElementsInstancedBaseVertex(int mode, ShortBuffer indices, int primcount, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 329:329 */    long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 330:330 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 331:331 */    GLChecks.ensureElementVBOdisabled(caps);
/* 332:332 */    BufferChecks.checkDirect(indices);
/* 333:333 */    nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, basevertex, function_pointer); }
/* 334:    */  
/* 335:    */  static native void nglDrawElementsInstancedBaseVertex(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2);
/* 336:    */  
/* 337:337 */  public static void glDrawElementsInstancedBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 338:338 */    long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 339:339 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 340:340 */    GLChecks.ensureElementVBOenabled(caps);
/* 341:341 */    nglDrawElementsInstancedBaseVertexBO(mode, indices_count, type, indices_buffer_offset, primcount, basevertex, function_pointer);
/* 342:    */  }
/* 343:    */  
/* 344:    */  static native void nglDrawElementsInstancedBaseVertexBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2);
/* 345:    */  
/* 346:346 */  public static void glProvokingVertex(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 347:347 */    long function_pointer = caps.glProvokingVertex;
/* 348:348 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 349:349 */    nglProvokingVertex(mode, function_pointer);
/* 350:    */  }
/* 351:    */  
/* 352:    */  static native void nglProvokingVertex(int paramInt, long paramLong);
/* 353:    */  
/* 354:354 */  public static void glTexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 355:355 */    long function_pointer = caps.glTexImage2DMultisample;
/* 356:356 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 357:357 */    nglTexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations, function_pointer);
/* 358:    */  }
/* 359:    */  
/* 360:    */  static native void nglTexImage2DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong);
/* 361:    */  
/* 362:362 */  public static void glTexImage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 363:363 */    long function_pointer = caps.glTexImage3DMultisample;
/* 364:364 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 365:365 */    nglTexImage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations, function_pointer);
/* 366:    */  }
/* 367:    */  
/* 368:    */  static native void nglTexImage3DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/* 369:    */  
/* 370:370 */  public static void glGetMultisample(int pname, int index, FloatBuffer val) { ContextCapabilities caps = GLContext.getCapabilities();
/* 371:371 */    long function_pointer = caps.glGetMultisamplefv;
/* 372:372 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 373:373 */    BufferChecks.checkBuffer(val, 2);
/* 374:374 */    nglGetMultisamplefv(pname, index, MemoryUtil.getAddress(val), function_pointer);
/* 375:    */  }
/* 376:    */  
/* 377:    */  static native void nglGetMultisamplefv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 378:    */  
/* 379:379 */  public static void glSampleMaski(int index, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 380:380 */    long function_pointer = caps.glSampleMaski;
/* 381:381 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 382:382 */    nglSampleMaski(index, mask, function_pointer);
/* 383:    */  }
/* 384:    */  
/* 385:    */  static native void nglSampleMaski(int paramInt1, int paramInt2, long paramLong);
/* 386:    */  
/* 387:387 */  public static void glFramebufferTexture(int target, int attachment, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 388:388 */    long function_pointer = caps.glFramebufferTexture;
/* 389:389 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 390:390 */    nglFramebufferTexture(target, attachment, texture, level, function_pointer);
/* 391:    */  }
/* 392:    */  
/* 393:    */  static native void nglFramebufferTexture(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 394:    */  
/* 395:395 */  public static GLSync glFenceSync(int condition, int flags) { ContextCapabilities caps = GLContext.getCapabilities();
/* 396:396 */    long function_pointer = caps.glFenceSync;
/* 397:397 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 398:398 */    GLSync __result = new GLSync(nglFenceSync(condition, flags, function_pointer));
/* 399:399 */    return __result;
/* 400:    */  }
/* 401:    */  
/* 402:    */  static native long nglFenceSync(int paramInt1, int paramInt2, long paramLong);
/* 403:    */  
/* 404:404 */  public static boolean glIsSync(GLSync sync) { ContextCapabilities caps = GLContext.getCapabilities();
/* 405:405 */    long function_pointer = caps.glIsSync;
/* 406:406 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 407:407 */    boolean __result = nglIsSync(sync.getPointer(), function_pointer);
/* 408:408 */    return __result;
/* 409:    */  }
/* 410:    */  
/* 411:    */  static native boolean nglIsSync(long paramLong1, long paramLong2);
/* 412:    */  
/* 413:413 */  public static void glDeleteSync(GLSync sync) { ContextCapabilities caps = GLContext.getCapabilities();
/* 414:414 */    long function_pointer = caps.glDeleteSync;
/* 415:415 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 416:416 */    nglDeleteSync(sync.getPointer(), function_pointer);
/* 417:    */  }
/* 418:    */  
/* 419:    */  static native void nglDeleteSync(long paramLong1, long paramLong2);
/* 420:    */  
/* 421:421 */  public static int glClientWaitSync(GLSync sync, int flags, long timeout) { ContextCapabilities caps = GLContext.getCapabilities();
/* 422:422 */    long function_pointer = caps.glClientWaitSync;
/* 423:423 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 424:424 */    int __result = nglClientWaitSync(sync.getPointer(), flags, timeout, function_pointer);
/* 425:425 */    return __result;
/* 426:    */  }
/* 427:    */  
/* 428:    */  static native int nglClientWaitSync(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/* 429:    */  
/* 430:430 */  public static void glWaitSync(GLSync sync, int flags, long timeout) { ContextCapabilities caps = GLContext.getCapabilities();
/* 431:431 */    long function_pointer = caps.glWaitSync;
/* 432:432 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 433:433 */    nglWaitSync(sync.getPointer(), flags, timeout, function_pointer);
/* 434:    */  }
/* 435:    */  
/* 436:    */  static native void nglWaitSync(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/* 437:    */  
/* 438:438 */  public static void glGetInteger64(int pname, LongBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 439:439 */    long function_pointer = caps.glGetInteger64v;
/* 440:440 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 441:441 */    BufferChecks.checkBuffer(data, 1);
/* 442:442 */    nglGetInteger64v(pname, MemoryUtil.getAddress(data), function_pointer);
/* 443:    */  }
/* 444:    */  
/* 445:    */  static native void nglGetInteger64v(int paramInt, long paramLong1, long paramLong2);
/* 446:    */  
/* 447:    */  public static long glGetInteger64(int pname) {
/* 448:448 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 449:449 */    long function_pointer = caps.glGetInteger64v;
/* 450:450 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 451:451 */    LongBuffer data = APIUtil.getBufferLong(caps);
/* 452:452 */    nglGetInteger64v(pname, MemoryUtil.getAddress(data), function_pointer);
/* 453:453 */    return data.get(0);
/* 454:    */  }
/* 455:    */  
/* 456:    */  public static void glGetInteger64(int value, int index, LongBuffer data) {
/* 457:457 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 458:458 */    long function_pointer = caps.glGetInteger64i_v;
/* 459:459 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 460:460 */    BufferChecks.checkBuffer(data, 4);
/* 461:461 */    nglGetInteger64i_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 462:    */  }
/* 463:    */  
/* 464:    */  static native void nglGetInteger64i_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 465:    */  
/* 466:    */  public static long glGetInteger64(int value, int index) {
/* 467:467 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 468:468 */    long function_pointer = caps.glGetInteger64i_v;
/* 469:469 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 470:470 */    LongBuffer data = APIUtil.getBufferLong(caps);
/* 471:471 */    nglGetInteger64i_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 472:472 */    return data.get(0);
/* 473:    */  }
/* 474:    */  
/* 475:    */  public static void glGetSync(GLSync sync, int pname, IntBuffer length, IntBuffer values) {
/* 476:476 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 477:477 */    long function_pointer = caps.glGetSynciv;
/* 478:478 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 479:479 */    if (length != null)
/* 480:480 */      BufferChecks.checkBuffer(length, 1);
/* 481:481 */    BufferChecks.checkDirect(values);
/* 482:482 */    nglGetSynciv(sync.getPointer(), pname, values.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(values), function_pointer);
/* 483:    */  }
/* 484:    */  
/* 487:    */  static native void nglGetSynciv(long paramLong1, int paramInt1, int paramInt2, long paramLong2, long paramLong3, long paramLong4);
/* 488:    */  
/* 490:    */  @Deprecated
/* 491:    */  public static int glGetSync(GLSync sync, int pname)
/* 492:    */  {
/* 493:493 */    return glGetSynci(sync, pname);
/* 494:    */  }
/* 495:    */  
/* 496:    */  public static int glGetSynci(GLSync sync, int pname)
/* 497:    */  {
/* 498:498 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 499:499 */    long function_pointer = caps.glGetSynciv;
/* 500:500 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 501:501 */    IntBuffer values = APIUtil.getBufferInt(caps);
/* 502:502 */    nglGetSynciv(sync.getPointer(), pname, 1, 0L, MemoryUtil.getAddress(values), function_pointer);
/* 503:503 */    return values.get(0);
/* 504:    */  }
/* 505:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL32
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */