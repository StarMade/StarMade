/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.nio.ShortBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.MemoryUtil;
/*   8:    */
/*  96:    */public final class GL42
/*  97:    */{
/*  98:    */  public static final int GL_COMPRESSED_RGBA_BPTC_UNORM = 36492;
/*  99:    */  public static final int GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM = 36493;
/* 100:    */  public static final int GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT = 36494;
/* 101:    */  public static final int GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = 36495;
/* 102:    */  public static final int GL_UNPACK_COMPRESSED_BLOCK_WIDTH = 37159;
/* 103:    */  public static final int GL_UNPACK_COMPRESSED_BLOCK_HEIGHT = 37160;
/* 104:    */  public static final int GL_UNPACK_COMPRESSED_BLOCK_DEPTH = 37161;
/* 105:    */  public static final int GL_UNPACK_COMPRESSED_BLOCK_SIZE = 37162;
/* 106:    */  public static final int GL_PACK_COMPRESSED_BLOCK_WIDTH = 37163;
/* 107:    */  public static final int GL_PACK_COMPRESSED_BLOCK_HEIGHT = 37164;
/* 108:    */  public static final int GL_PACK_COMPRESSED_BLOCK_DEPTH = 37165;
/* 109:    */  public static final int GL_PACK_COMPRESSED_BLOCK_SIZE = 37166;
/* 110:    */  public static final int GL_ATOMIC_COUNTER_BUFFER = 37568;
/* 111:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_BINDING = 37569;
/* 112:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_START = 37570;
/* 113:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_SIZE = 37571;
/* 114:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE = 37572;
/* 115:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS = 37573;
/* 116:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES = 37574;
/* 117:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER = 37575;
/* 118:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER = 37576;
/* 119:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 37577;
/* 120:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER = 37578;
/* 121:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER = 37579;
/* 122:    */  public static final int GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS = 37580;
/* 123:    */  public static final int GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS = 37581;
/* 124:    */  public static final int GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 37582;
/* 125:    */  public static final int GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS = 37583;
/* 126:    */  public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS = 37584;
/* 127:    */  public static final int GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS = 37585;
/* 128:    */  public static final int GL_MAX_VERTEX_ATOMIC_COUNTERS = 37586;
/* 129:    */  public static final int GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS = 37587;
/* 130:    */  public static final int GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 37588;
/* 131:    */  public static final int GL_MAX_GEOMETRY_ATOMIC_COUNTERS = 37589;
/* 132:    */  public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTERS = 37590;
/* 133:    */  public static final int GL_MAX_COMBINED_ATOMIC_COUNTERS = 37591;
/* 134:    */  public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE = 37592;
/* 135:    */  public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS = 37596;
/* 136:    */  public static final int GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 37593;
/* 137:    */  public static final int GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 37594;
/* 138:    */  public static final int GL_UNSIGNED_INT_ATOMIC_COUNTER = 37595;
/* 139:    */  public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;
/* 140:    */  public static final int GL_MAX_IMAGE_UNITS = 36664;
/* 141:    */  public static final int GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 36665;
/* 142:    */  public static final int GL_MAX_IMAGE_SAMPLES = 36973;
/* 143:    */  public static final int GL_MAX_VERTEX_IMAGE_UNIFORMS = 37066;
/* 144:    */  public static final int GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS = 37067;
/* 145:    */  public static final int GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS = 37068;
/* 146:    */  public static final int GL_MAX_GEOMETRY_IMAGE_UNIFORMS = 37069;
/* 147:    */  public static final int GL_MAX_FRAGMENT_IMAGE_UNIFORMS = 37070;
/* 148:    */  public static final int GL_MAX_COMBINED_IMAGE_UNIFORMS = 37071;
/* 149:    */  public static final int GL_IMAGE_BINDING_NAME = 36666;
/* 150:    */  public static final int GL_IMAGE_BINDING_LEVEL = 36667;
/* 151:    */  public static final int GL_IMAGE_BINDING_LAYERED = 36668;
/* 152:    */  public static final int GL_IMAGE_BINDING_LAYER = 36669;
/* 153:    */  public static final int GL_IMAGE_BINDING_ACCESS = 36670;
/* 154:    */  public static final int GL_IMAGE_BINDING_FORMAT = 36974;
/* 155:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 1;
/* 156:    */  public static final int GL_ELEMENT_ARRAY_BARRIER_BIT = 2;
/* 157:    */  public static final int GL_UNIFORM_BARRIER_BIT = 4;
/* 158:    */  public static final int GL_TEXTURE_FETCH_BARRIER_BIT = 8;
/* 159:    */  public static final int GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 32;
/* 160:    */  public static final int GL_COMMAND_BARRIER_BIT = 64;
/* 161:    */  public static final int GL_PIXEL_BUFFER_BARRIER_BIT = 128;
/* 162:    */  public static final int GL_TEXTURE_UPDATE_BARRIER_BIT = 256;
/* 163:    */  public static final int GL_BUFFER_UPDATE_BARRIER_BIT = 512;
/* 164:    */  public static final int GL_FRAMEBUFFER_BARRIER_BIT = 1024;
/* 165:    */  public static final int GL_TRANSFORM_FEEDBACK_BARRIER_BIT = 2048;
/* 166:    */  public static final int GL_ATOMIC_COUNTER_BARRIER_BIT = 4096;
/* 167:    */  public static final int GL_ALL_BARRIER_BITS = -1;
/* 168:    */  public static final int GL_IMAGE_1D = 36940;
/* 169:    */  public static final int GL_IMAGE_2D = 36941;
/* 170:    */  public static final int GL_IMAGE_3D = 36942;
/* 171:    */  public static final int GL_IMAGE_2D_RECT = 36943;
/* 172:    */  public static final int GL_IMAGE_CUBE = 36944;
/* 173:    */  public static final int GL_IMAGE_BUFFER = 36945;
/* 174:    */  public static final int GL_IMAGE_1D_ARRAY = 36946;
/* 175:    */  public static final int GL_IMAGE_2D_ARRAY = 36947;
/* 176:    */  public static final int GL_IMAGE_CUBE_MAP_ARRAY = 36948;
/* 177:    */  public static final int GL_IMAGE_2D_MULTISAMPLE = 36949;
/* 178:    */  public static final int GL_IMAGE_2D_MULTISAMPLE_ARRAY = 36950;
/* 179:    */  public static final int GL_INT_IMAGE_1D = 36951;
/* 180:    */  public static final int GL_INT_IMAGE_2D = 36952;
/* 181:    */  public static final int GL_INT_IMAGE_3D = 36953;
/* 182:    */  public static final int GL_INT_IMAGE_2D_RECT = 36954;
/* 183:    */  public static final int GL_INT_IMAGE_CUBE = 36955;
/* 184:    */  public static final int GL_INT_IMAGE_BUFFER = 36956;
/* 185:    */  public static final int GL_INT_IMAGE_1D_ARRAY = 36957;
/* 186:    */  public static final int GL_INT_IMAGE_2D_ARRAY = 36958;
/* 187:    */  public static final int GL_INT_IMAGE_CUBE_MAP_ARRAY = 36959;
/* 188:    */  public static final int GL_INT_IMAGE_2D_MULTISAMPLE = 36960;
/* 189:    */  public static final int GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36961;
/* 190:    */  public static final int GL_UNSIGNED_INT_IMAGE_1D = 36962;
/* 191:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D = 36963;
/* 192:    */  public static final int GL_UNSIGNED_INT_IMAGE_3D = 36964;
/* 193:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_RECT = 36965;
/* 194:    */  public static final int GL_UNSIGNED_INT_IMAGE_CUBE = 36966;
/* 195:    */  public static final int GL_UNSIGNED_INT_IMAGE_BUFFER = 36967;
/* 196:    */  public static final int GL_UNSIGNED_INT_IMAGE_1D_ARRAY = 36968;
/* 197:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_ARRAY = 36969;
/* 198:    */  public static final int GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY = 36970;
/* 199:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE = 36971;
/* 200:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36972;
/* 201:    */  public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;
/* 202:    */  public static final int GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 37064;
/* 203:    */  public static final int IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 37065;
/* 204:    */  public static final int GL_NUM_SAMPLE_COUNTS = 37760;
/* 205:    */  public static final int GL_MIN_MAP_BUFFER_ALIGNMENT = 37052;
/* 206:    */  
/* 207:    */  public static void glGetActiveAtomicCounterBuffer(int program, int bufferIndex, int pname, IntBuffer params)
/* 208:    */  {
/* 209:209 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 210:210 */    long function_pointer = caps.glGetActiveAtomicCounterBufferiv;
/* 211:211 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 212:212 */    BufferChecks.checkBuffer(params, 1);
/* 213:213 */    nglGetActiveAtomicCounterBufferiv(program, bufferIndex, pname, MemoryUtil.getAddress(params), function_pointer);
/* 214:    */  }
/* 215:    */  
/* 216:    */  static native void nglGetActiveAtomicCounterBufferiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 217:    */  
/* 218:    */  public static int glGetActiveAtomicCounterBuffer(int program, int bufferIndex, int pname) {
/* 219:219 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 220:220 */    long function_pointer = caps.glGetActiveAtomicCounterBufferiv;
/* 221:221 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 222:222 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 223:223 */    nglGetActiveAtomicCounterBufferiv(program, bufferIndex, pname, MemoryUtil.getAddress(params), function_pointer);
/* 224:224 */    return params.get(0);
/* 225:    */  }
/* 226:    */  
/* 227:    */  public static void glTexStorage1D(int target, int levels, int internalformat, int width) {
/* 228:228 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 229:229 */    long function_pointer = caps.glTexStorage1D;
/* 230:230 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 231:231 */    nglTexStorage1D(target, levels, internalformat, width, function_pointer);
/* 232:    */  }
/* 233:    */  
/* 234:    */  static native void nglTexStorage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 235:    */  
/* 236:236 */  public static void glTexStorage2D(int target, int levels, int internalformat, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 237:237 */    long function_pointer = caps.glTexStorage2D;
/* 238:238 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 239:239 */    nglTexStorage2D(target, levels, internalformat, width, height, function_pointer);
/* 240:    */  }
/* 241:    */  
/* 242:    */  static native void nglTexStorage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 243:    */  
/* 244:244 */  public static void glTexStorage3D(int target, int levels, int internalformat, int width, int height, int depth) { ContextCapabilities caps = GLContext.getCapabilities();
/* 245:245 */    long function_pointer = caps.glTexStorage3D;
/* 246:246 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 247:247 */    nglTexStorage3D(target, levels, internalformat, width, height, depth, function_pointer);
/* 248:    */  }
/* 249:    */  
/* 250:    */  static native void nglTexStorage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 251:    */  
/* 252:252 */  public static void glDrawTransformFeedbackInstanced(int mode, int id, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 253:253 */    long function_pointer = caps.glDrawTransformFeedbackInstanced;
/* 254:254 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 255:255 */    nglDrawTransformFeedbackInstanced(mode, id, primcount, function_pointer);
/* 256:    */  }
/* 257:    */  
/* 258:    */  static native void nglDrawTransformFeedbackInstanced(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 259:    */  
/* 260:260 */  public static void glDrawTransformFeedbackStreamInstanced(int mode, int id, int stream, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 261:261 */    long function_pointer = caps.glDrawTransformFeedbackStreamInstanced;
/* 262:262 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 263:263 */    nglDrawTransformFeedbackStreamInstanced(mode, id, stream, primcount, function_pointer);
/* 264:    */  }
/* 265:    */  
/* 266:    */  static native void nglDrawTransformFeedbackStreamInstanced(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 267:    */  
/* 268:268 */  public static void glDrawArraysInstancedBaseInstance(int mode, int first, int count, int primcount, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 269:269 */    long function_pointer = caps.glDrawArraysInstancedBaseInstance;
/* 270:270 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 271:271 */    nglDrawArraysInstancedBaseInstance(mode, first, count, primcount, baseinstance, function_pointer);
/* 272:    */  }
/* 273:    */  
/* 274:    */  static native void nglDrawArraysInstancedBaseInstance(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 275:    */  
/* 276:276 */  public static void glDrawElementsInstancedBaseInstance(int mode, ByteBuffer indices, int primcount, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 277:277 */    long function_pointer = caps.glDrawElementsInstancedBaseInstance;
/* 278:278 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 279:279 */    GLChecks.ensureElementVBOdisabled(caps);
/* 280:280 */    BufferChecks.checkDirect(indices);
/* 281:281 */    nglDrawElementsInstancedBaseInstance(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, baseinstance, function_pointer);
/* 282:    */  }
/* 283:    */  
/* 284:284 */  public static void glDrawElementsInstancedBaseInstance(int mode, IntBuffer indices, int primcount, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 285:285 */    long function_pointer = caps.glDrawElementsInstancedBaseInstance;
/* 286:286 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 287:287 */    GLChecks.ensureElementVBOdisabled(caps);
/* 288:288 */    BufferChecks.checkDirect(indices);
/* 289:289 */    nglDrawElementsInstancedBaseInstance(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, baseinstance, function_pointer);
/* 290:    */  }
/* 291:    */  
/* 292:292 */  public static void glDrawElementsInstancedBaseInstance(int mode, ShortBuffer indices, int primcount, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 293:293 */    long function_pointer = caps.glDrawElementsInstancedBaseInstance;
/* 294:294 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 295:295 */    GLChecks.ensureElementVBOdisabled(caps);
/* 296:296 */    BufferChecks.checkDirect(indices);
/* 297:297 */    nglDrawElementsInstancedBaseInstance(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, baseinstance, function_pointer); }
/* 298:    */  
/* 299:    */  static native void nglDrawElementsInstancedBaseInstance(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2);
/* 300:    */  
/* 301:301 */  public static void glDrawElementsInstancedBaseInstance(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 302:302 */    long function_pointer = caps.glDrawElementsInstancedBaseInstance;
/* 303:303 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 304:304 */    GLChecks.ensureElementVBOenabled(caps);
/* 305:305 */    nglDrawElementsInstancedBaseInstanceBO(mode, indices_count, type, indices_buffer_offset, primcount, baseinstance, function_pointer);
/* 306:    */  }
/* 307:    */  
/* 308:    */  static native void nglDrawElementsInstancedBaseInstanceBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2);
/* 309:    */  
/* 310:310 */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ByteBuffer indices, int primcount, int basevertex, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 311:311 */    long function_pointer = caps.glDrawElementsInstancedBaseVertexBaseInstance;
/* 312:312 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 313:313 */    GLChecks.ensureElementVBOdisabled(caps);
/* 314:314 */    BufferChecks.checkDirect(indices);
/* 315:315 */    nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, basevertex, baseinstance, function_pointer);
/* 316:    */  }
/* 317:    */  
/* 318:318 */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, IntBuffer indices, int primcount, int basevertex, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 319:319 */    long function_pointer = caps.glDrawElementsInstancedBaseVertexBaseInstance;
/* 320:320 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 321:321 */    GLChecks.ensureElementVBOdisabled(caps);
/* 322:322 */    BufferChecks.checkDirect(indices);
/* 323:323 */    nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, basevertex, baseinstance, function_pointer);
/* 324:    */  }
/* 325:    */  
/* 326:326 */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ShortBuffer indices, int primcount, int basevertex, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 327:327 */    long function_pointer = caps.glDrawElementsInstancedBaseVertexBaseInstance;
/* 328:328 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 329:329 */    GLChecks.ensureElementVBOdisabled(caps);
/* 330:330 */    BufferChecks.checkDirect(indices);
/* 331:331 */    nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, basevertex, baseinstance, function_pointer); }
/* 332:    */  
/* 333:    */  static native void nglDrawElementsInstancedBaseVertexBaseInstance(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, int paramInt6, long paramLong2);
/* 334:    */  
/* 335:335 */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex, int baseinstance) { ContextCapabilities caps = GLContext.getCapabilities();
/* 336:336 */    long function_pointer = caps.glDrawElementsInstancedBaseVertexBaseInstance;
/* 337:337 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 338:338 */    GLChecks.ensureElementVBOenabled(caps);
/* 339:339 */    nglDrawElementsInstancedBaseVertexBaseInstanceBO(mode, indices_count, type, indices_buffer_offset, primcount, basevertex, baseinstance, function_pointer);
/* 340:    */  }
/* 341:    */  
/* 342:    */  static native void nglDrawElementsInstancedBaseVertexBaseInstanceBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, int paramInt6, long paramLong2);
/* 343:    */  
/* 344:344 */  public static void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer, int access, int format) { ContextCapabilities caps = GLContext.getCapabilities();
/* 345:345 */    long function_pointer = caps.glBindImageTexture;
/* 346:346 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 347:347 */    nglBindImageTexture(unit, texture, level, layered, layer, access, format, function_pointer);
/* 348:    */  }
/* 349:    */  
/* 350:    */  static native void nglBindImageTexture(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 351:    */  
/* 352:352 */  public static void glMemoryBarrier(int barriers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 353:353 */    long function_pointer = caps.glMemoryBarrier;
/* 354:354 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 355:355 */    nglMemoryBarrier(barriers, function_pointer);
/* 356:    */  }
/* 357:    */  
/* 358:    */  static native void nglMemoryBarrier(int paramInt, long paramLong);
/* 359:    */  
/* 360:360 */  public static void glGetInternalformat(int target, int internalformat, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 361:361 */    long function_pointer = caps.glGetInternalformativ;
/* 362:362 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 363:363 */    BufferChecks.checkDirect(params);
/* 364:364 */    nglGetInternalformativ(target, internalformat, pname, params.remaining(), MemoryUtil.getAddress(params), function_pointer);
/* 365:    */  }
/* 366:    */  
/* 367:    */  static native void nglGetInternalformativ(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 368:    */  
/* 369:    */  public static int glGetInternalformat(int target, int internalformat, int pname) {
/* 370:370 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 371:371 */    long function_pointer = caps.glGetInternalformativ;
/* 372:372 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 373:373 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 374:374 */    nglGetInternalformativ(target, internalformat, pname, 1, MemoryUtil.getAddress(params), function_pointer);
/* 375:375 */    return params.get(0);
/* 376:    */  }
/* 377:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL42
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */