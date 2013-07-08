/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.nio.ShortBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.MemoryUtil;
/*   8:    */
/* 105:    */public final class GL31
/* 106:    */{
/* 107:    */  public static final int GL_RED_SNORM = 36752;
/* 108:    */  public static final int GL_RG_SNORM = 36753;
/* 109:    */  public static final int GL_RGB_SNORM = 36754;
/* 110:    */  public static final int GL_RGBA_SNORM = 36755;
/* 111:    */  public static final int GL_R8_SNORM = 36756;
/* 112:    */  public static final int GL_RG8_SNORM = 36757;
/* 113:    */  public static final int GL_RGB8_SNORM = 36758;
/* 114:    */  public static final int GL_RGBA8_SNORM = 36759;
/* 115:    */  public static final int GL_R16_SNORM = 36760;
/* 116:    */  public static final int GL_RG16_SNORM = 36761;
/* 117:    */  public static final int GL_RGB16_SNORM = 36762;
/* 118:    */  public static final int GL_RGBA16_SNORM = 36763;
/* 119:    */  public static final int GL_SIGNED_NORMALIZED = 36764;
/* 120:    */  public static final int GL_COPY_READ_BUFFER_BINDING = 36662;
/* 121:    */  public static final int GL_COPY_WRITE_BUFFER_BINDING = 36663;
/* 122:    */  public static final int GL_COPY_READ_BUFFER = 36662;
/* 123:    */  public static final int GL_COPY_WRITE_BUFFER = 36663;
/* 124:    */  public static final int GL_PRIMITIVE_RESTART = 36765;
/* 125:    */  public static final int GL_PRIMITIVE_RESTART_INDEX = 36766;
/* 126:    */  public static final int GL_TEXTURE_BUFFER = 35882;
/* 127:    */  public static final int GL_MAX_TEXTURE_BUFFER_SIZE = 35883;
/* 128:    */  public static final int GL_TEXTURE_BINDING_BUFFER = 35884;
/* 129:    */  public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 35885;
/* 130:    */  public static final int GL_TEXTURE_BUFFER_FORMAT = 35886;
/* 131:    */  public static final int GL_TEXTURE_RECTANGLE = 34037;
/* 132:    */  public static final int GL_TEXTURE_BINDING_RECTANGLE = 34038;
/* 133:    */  public static final int GL_PROXY_TEXTURE_RECTANGLE = 34039;
/* 134:    */  public static final int GL_MAX_RECTANGLE_TEXTURE_SIZE = 34040;
/* 135:    */  public static final int GL_SAMPLER_2D_RECT = 35683;
/* 136:    */  public static final int GL_SAMPLER_2D_RECT_SHADOW = 35684;
/* 137:    */  public static final int GL_UNIFORM_BUFFER = 35345;
/* 138:    */  public static final int GL_UNIFORM_BUFFER_BINDING = 35368;
/* 139:    */  public static final int GL_UNIFORM_BUFFER_START = 35369;
/* 140:    */  public static final int GL_UNIFORM_BUFFER_SIZE = 35370;
/* 141:    */  public static final int GL_MAX_VERTEX_UNIFORM_BLOCKS = 35371;
/* 142:    */  public static final int GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 35372;
/* 143:    */  public static final int GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 35373;
/* 144:    */  public static final int GL_MAX_COMBINED_UNIFORM_BLOCKS = 35374;
/* 145:    */  public static final int GL_MAX_UNIFORM_BUFFER_BINDINGS = 35375;
/* 146:    */  public static final int GL_MAX_UNIFORM_BLOCK_SIZE = 35376;
/* 147:    */  public static final int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 35377;
/* 148:    */  public static final int GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 35378;
/* 149:    */  public static final int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 35379;
/* 150:    */  public static final int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 35380;
/* 151:    */  public static final int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 35381;
/* 152:    */  public static final int GL_ACTIVE_UNIFORM_BLOCKS = 35382;
/* 153:    */  public static final int GL_UNIFORM_TYPE = 35383;
/* 154:    */  public static final int GL_UNIFORM_SIZE = 35384;
/* 155:    */  public static final int GL_UNIFORM_NAME_LENGTH = 35385;
/* 156:    */  public static final int GL_UNIFORM_BLOCK_INDEX = 35386;
/* 157:    */  public static final int GL_UNIFORM_OFFSET = 35387;
/* 158:    */  public static final int GL_UNIFORM_ARRAY_STRIDE = 35388;
/* 159:    */  public static final int GL_UNIFORM_MATRIX_STRIDE = 35389;
/* 160:    */  public static final int GL_UNIFORM_IS_ROW_MAJOR = 35390;
/* 161:    */  public static final int GL_UNIFORM_BLOCK_BINDING = 35391;
/* 162:    */  public static final int GL_UNIFORM_BLOCK_DATA_SIZE = 35392;
/* 163:    */  public static final int GL_UNIFORM_BLOCK_NAME_LENGTH = 35393;
/* 164:    */  public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 35394;
/* 165:    */  public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 35395;
/* 166:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 35396;
/* 167:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 35397;
/* 168:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 35398;
/* 169:    */  public static final int GL_INVALID_INDEX = -1;
/* 170:    */  
/* 171:    */  public static void glDrawArraysInstanced(int mode, int first, int count, int primcount)
/* 172:    */  {
/* 173:173 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 174:174 */    long function_pointer = caps.glDrawArraysInstanced;
/* 175:175 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 176:176 */    nglDrawArraysInstanced(mode, first, count, primcount, function_pointer);
/* 177:    */  }
/* 178:    */  
/* 179:    */  static native void nglDrawArraysInstanced(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 180:    */  
/* 181:181 */  public static void glDrawElementsInstanced(int mode, ByteBuffer indices, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 182:182 */    long function_pointer = caps.glDrawElementsInstanced;
/* 183:183 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 184:184 */    GLChecks.ensureElementVBOdisabled(caps);
/* 185:185 */    BufferChecks.checkDirect(indices);
/* 186:186 */    nglDrawElementsInstanced(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, function_pointer);
/* 187:    */  }
/* 188:    */  
/* 189:189 */  public static void glDrawElementsInstanced(int mode, IntBuffer indices, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glDrawElementsInstanced;
/* 191:191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    GLChecks.ensureElementVBOdisabled(caps);
/* 193:193 */    BufferChecks.checkDirect(indices);
/* 194:194 */    nglDrawElementsInstanced(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, function_pointer);
/* 195:    */  }
/* 196:    */  
/* 197:197 */  public static void glDrawElementsInstanced(int mode, ShortBuffer indices, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 198:198 */    long function_pointer = caps.glDrawElementsInstanced;
/* 199:199 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 200:200 */    GLChecks.ensureElementVBOdisabled(caps);
/* 201:201 */    BufferChecks.checkDirect(indices);
/* 202:202 */    nglDrawElementsInstanced(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, function_pointer); }
/* 203:    */  
/* 204:    */  static native void nglDrawElementsInstanced(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/* 205:    */  
/* 206:206 */  public static void glDrawElementsInstanced(int mode, int indices_count, int type, long indices_buffer_offset, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 207:207 */    long function_pointer = caps.glDrawElementsInstanced;
/* 208:208 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 209:209 */    GLChecks.ensureElementVBOenabled(caps);
/* 210:210 */    nglDrawElementsInstancedBO(mode, indices_count, type, indices_buffer_offset, primcount, function_pointer);
/* 211:    */  }
/* 212:    */  
/* 213:    */  static native void nglDrawElementsInstancedBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/* 214:    */  
/* 215:215 */  public static void glCopyBufferSubData(int readtarget, int writetarget, long readoffset, long writeoffset, long size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 216:216 */    long function_pointer = caps.glCopyBufferSubData;
/* 217:217 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 218:218 */    nglCopyBufferSubData(readtarget, writetarget, readoffset, writeoffset, size, function_pointer);
/* 219:    */  }
/* 220:    */  
/* 221:    */  static native void nglCopyBufferSubData(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 222:    */  
/* 223:223 */  public static void glPrimitiveRestartIndex(int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 224:224 */    long function_pointer = caps.glPrimitiveRestartIndex;
/* 225:225 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 226:226 */    nglPrimitiveRestartIndex(index, function_pointer);
/* 227:    */  }
/* 228:    */  
/* 229:    */  static native void nglPrimitiveRestartIndex(int paramInt, long paramLong);
/* 230:    */  
/* 231:231 */  public static void glTexBuffer(int target, int internalformat, int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 232:232 */    long function_pointer = caps.glTexBuffer;
/* 233:233 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 234:234 */    nglTexBuffer(target, internalformat, buffer, function_pointer);
/* 235:    */  }
/* 236:    */  
/* 237:    */  static native void nglTexBuffer(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 238:    */  
/* 239:239 */  public static void glGetUniformIndices(int program, ByteBuffer uniformNames, IntBuffer uniformIndices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 240:240 */    long function_pointer = caps.glGetUniformIndices;
/* 241:241 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 242:242 */    BufferChecks.checkDirect(uniformNames);
/* 243:243 */    BufferChecks.checkNullTerminated(uniformNames, uniformIndices.remaining());
/* 244:244 */    BufferChecks.checkDirect(uniformIndices);
/* 245:245 */    nglGetUniformIndices(program, uniformIndices.remaining(), MemoryUtil.getAddress(uniformNames), MemoryUtil.getAddress(uniformIndices), function_pointer);
/* 246:    */  }
/* 247:    */  
/* 248:    */  static native void nglGetUniformIndices(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 249:    */  
/* 250:    */  public static void glGetUniformIndices(int program, CharSequence[] uniformNames, IntBuffer uniformIndices) {
/* 251:251 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 252:252 */    long function_pointer = caps.glGetUniformIndices;
/* 253:253 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 254:254 */    BufferChecks.checkArray(uniformNames);
/* 255:255 */    BufferChecks.checkBuffer(uniformIndices, uniformNames.length);
/* 256:256 */    nglGetUniformIndices(program, uniformNames.length, APIUtil.getBufferNT(caps, uniformNames), MemoryUtil.getAddress(uniformIndices), function_pointer);
/* 257:    */  }
/* 258:    */  
/* 259:    */  public static void glGetActiveUniforms(int program, IntBuffer uniformIndices, int pname, IntBuffer params) {
/* 260:260 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 261:261 */    long function_pointer = caps.glGetActiveUniformsiv;
/* 262:262 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 263:263 */    BufferChecks.checkDirect(uniformIndices);
/* 264:264 */    BufferChecks.checkBuffer(params, uniformIndices.remaining());
/* 265:265 */    nglGetActiveUniformsiv(program, uniformIndices.remaining(), MemoryUtil.getAddress(uniformIndices), pname, MemoryUtil.getAddress(params), function_pointer);
/* 266:    */  }
/* 267:    */  
/* 270:    */  static native void nglGetActiveUniformsiv(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2, long paramLong3);
/* 271:    */  
/* 273:    */  @Deprecated
/* 274:    */  public static int glGetActiveUniforms(int program, int uniformIndex, int pname)
/* 275:    */  {
/* 276:276 */    return glGetActiveUniformsi(program, uniformIndex, pname);
/* 277:    */  }
/* 278:    */  
/* 279:    */  public static int glGetActiveUniformsi(int program, int uniformIndex, int pname)
/* 280:    */  {
/* 281:281 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 282:282 */    long function_pointer = caps.glGetActiveUniformsiv;
/* 283:283 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 284:284 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 285:285 */    nglGetActiveUniformsiv(program, 1, MemoryUtil.getAddress(params.put(1, uniformIndex), 1), pname, MemoryUtil.getAddress(params), function_pointer);
/* 286:286 */    return params.get(0);
/* 287:    */  }
/* 288:    */  
/* 289:    */  public static void glGetActiveUniformName(int program, int uniformIndex, IntBuffer length, ByteBuffer uniformName) {
/* 290:290 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 291:291 */    long function_pointer = caps.glGetActiveUniformName;
/* 292:292 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 293:293 */    if (length != null)
/* 294:294 */      BufferChecks.checkBuffer(length, 1);
/* 295:295 */    BufferChecks.checkDirect(uniformName);
/* 296:296 */    nglGetActiveUniformName(program, uniformIndex, uniformName.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(uniformName), function_pointer);
/* 297:    */  }
/* 298:    */  
/* 299:    */  static native void nglGetActiveUniformName(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/* 300:    */  
/* 301:    */  public static String glGetActiveUniformName(int program, int uniformIndex, int bufSize) {
/* 302:302 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 303:303 */    long function_pointer = caps.glGetActiveUniformName;
/* 304:304 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 305:305 */    IntBuffer uniformName_length = APIUtil.getLengths(caps);
/* 306:306 */    ByteBuffer uniformName = APIUtil.getBufferByte(caps, bufSize);
/* 307:307 */    nglGetActiveUniformName(program, uniformIndex, bufSize, MemoryUtil.getAddress0(uniformName_length), MemoryUtil.getAddress(uniformName), function_pointer);
/* 308:308 */    uniformName.limit(uniformName_length.get(0));
/* 309:309 */    return APIUtil.getString(caps, uniformName);
/* 310:    */  }
/* 311:    */  
/* 312:    */  public static int glGetUniformBlockIndex(int program, ByteBuffer uniformBlockName) {
/* 313:313 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 314:314 */    long function_pointer = caps.glGetUniformBlockIndex;
/* 315:315 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 316:316 */    BufferChecks.checkDirect(uniformBlockName);
/* 317:317 */    BufferChecks.checkNullTerminated(uniformBlockName);
/* 318:318 */    int __result = nglGetUniformBlockIndex(program, MemoryUtil.getAddress(uniformBlockName), function_pointer);
/* 319:319 */    return __result;
/* 320:    */  }
/* 321:    */  
/* 322:    */  static native int nglGetUniformBlockIndex(int paramInt, long paramLong1, long paramLong2);
/* 323:    */  
/* 324:    */  public static int glGetUniformBlockIndex(int program, CharSequence uniformBlockName) {
/* 325:325 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 326:326 */    long function_pointer = caps.glGetUniformBlockIndex;
/* 327:327 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 328:328 */    int __result = nglGetUniformBlockIndex(program, APIUtil.getBufferNT(caps, uniformBlockName), function_pointer);
/* 329:329 */    return __result;
/* 330:    */  }
/* 331:    */  
/* 332:    */  public static void glGetActiveUniformBlock(int program, int uniformBlockIndex, int pname, IntBuffer params) {
/* 333:333 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 334:334 */    long function_pointer = caps.glGetActiveUniformBlockiv;
/* 335:335 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 336:336 */    BufferChecks.checkBuffer(params, 16);
/* 337:337 */    nglGetActiveUniformBlockiv(program, uniformBlockIndex, pname, MemoryUtil.getAddress(params), function_pointer);
/* 338:    */  }
/* 339:    */  
/* 342:    */  static native void nglGetActiveUniformBlockiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 343:    */  
/* 345:    */  @Deprecated
/* 346:    */  public static int glGetActiveUniformBlock(int program, int uniformBlockIndex, int pname)
/* 347:    */  {
/* 348:348 */    return glGetActiveUniformBlocki(program, uniformBlockIndex, pname);
/* 349:    */  }
/* 350:    */  
/* 351:    */  public static int glGetActiveUniformBlocki(int program, int uniformBlockIndex, int pname)
/* 352:    */  {
/* 353:353 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 354:354 */    long function_pointer = caps.glGetActiveUniformBlockiv;
/* 355:355 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 356:356 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 357:357 */    nglGetActiveUniformBlockiv(program, uniformBlockIndex, pname, MemoryUtil.getAddress(params), function_pointer);
/* 358:358 */    return params.get(0);
/* 359:    */  }
/* 360:    */  
/* 361:    */  public static void glGetActiveUniformBlockName(int program, int uniformBlockIndex, IntBuffer length, ByteBuffer uniformBlockName) {
/* 362:362 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 363:363 */    long function_pointer = caps.glGetActiveUniformBlockName;
/* 364:364 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 365:365 */    if (length != null)
/* 366:366 */      BufferChecks.checkBuffer(length, 1);
/* 367:367 */    BufferChecks.checkDirect(uniformBlockName);
/* 368:368 */    nglGetActiveUniformBlockName(program, uniformBlockIndex, uniformBlockName.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(uniformBlockName), function_pointer);
/* 369:    */  }
/* 370:    */  
/* 371:    */  static native void nglGetActiveUniformBlockName(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/* 372:    */  
/* 373:    */  public static String glGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize) {
/* 374:374 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 375:375 */    long function_pointer = caps.glGetActiveUniformBlockName;
/* 376:376 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 377:377 */    IntBuffer uniformBlockName_length = APIUtil.getLengths(caps);
/* 378:378 */    ByteBuffer uniformBlockName = APIUtil.getBufferByte(caps, bufSize);
/* 379:379 */    nglGetActiveUniformBlockName(program, uniformBlockIndex, bufSize, MemoryUtil.getAddress0(uniformBlockName_length), MemoryUtil.getAddress(uniformBlockName), function_pointer);
/* 380:380 */    uniformBlockName.limit(uniformBlockName_length.get(0));
/* 381:381 */    return APIUtil.getString(caps, uniformBlockName);
/* 382:    */  }
/* 383:    */  
/* 384:    */  public static void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding) {
/* 385:385 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 386:386 */    long function_pointer = caps.glUniformBlockBinding;
/* 387:387 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 388:388 */    nglUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding, function_pointer);
/* 389:    */  }
/* 390:    */  
/* 391:    */  static native void nglUniformBlockBinding(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 392:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL31
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */