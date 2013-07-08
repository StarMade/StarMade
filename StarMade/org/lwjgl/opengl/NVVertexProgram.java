/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.DoubleBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.nio.ShortBuffer;
/*   9:    */import org.lwjgl.BufferChecks;
/*  10:    */import org.lwjgl.LWJGLUtil;
/*  11:    */import org.lwjgl.MemoryUtil;
/*  12:    */
/*  82:    */public final class NVVertexProgram
/*  83:    */  extends NVProgram
/*  84:    */{
/*  85:    */  public static final int GL_VERTEX_PROGRAM_NV = 34336;
/*  86:    */  public static final int GL_VERTEX_PROGRAM_POINT_SIZE_NV = 34370;
/*  87:    */  public static final int GL_VERTEX_PROGRAM_TWO_SIDE_NV = 34371;
/*  88:    */  public static final int GL_VERTEX_STATE_PROGRAM_NV = 34337;
/*  89:    */  public static final int GL_ATTRIB_ARRAY_SIZE_NV = 34339;
/*  90:    */  public static final int GL_ATTRIB_ARRAY_STRIDE_NV = 34340;
/*  91:    */  public static final int GL_ATTRIB_ARRAY_TYPE_NV = 34341;
/*  92:    */  public static final int GL_CURRENT_ATTRIB_NV = 34342;
/*  93:    */  public static final int GL_PROGRAM_PARAMETER_NV = 34372;
/*  94:    */  public static final int GL_ATTRIB_ARRAY_POINTER_NV = 34373;
/*  95:    */  public static final int GL_TRACK_MATRIX_NV = 34376;
/*  96:    */  public static final int GL_TRACK_MATRIX_TRANSFORM_NV = 34377;
/*  97:    */  public static final int GL_MAX_TRACK_MATRIX_STACK_DEPTH_NV = 34350;
/*  98:    */  public static final int GL_MAX_TRACK_MATRICES_NV = 34351;
/*  99:    */  public static final int GL_CURRENT_MATRIX_STACK_DEPTH_NV = 34368;
/* 100:    */  public static final int GL_CURRENT_MATRIX_NV = 34369;
/* 101:    */  public static final int GL_VERTEX_PROGRAM_BINDING_NV = 34378;
/* 102:    */  public static final int GL_MODELVIEW_PROJECTION_NV = 34345;
/* 103:    */  public static final int GL_MATRIX0_NV = 34352;
/* 104:    */  public static final int GL_MATRIX1_NV = 34353;
/* 105:    */  public static final int GL_MATRIX2_NV = 34354;
/* 106:    */  public static final int GL_MATRIX3_NV = 34355;
/* 107:    */  public static final int GL_MATRIX4_NV = 34356;
/* 108:    */  public static final int GL_MATRIX5_NV = 34357;
/* 109:    */  public static final int GL_MATRIX6_NV = 34358;
/* 110:    */  public static final int GL_MATRIX7_NV = 34359;
/* 111:    */  public static final int GL_IDENTITY_NV = 34346;
/* 112:    */  public static final int GL_INVERSE_NV = 34347;
/* 113:    */  public static final int GL_TRANSPOSE_NV = 34348;
/* 114:    */  public static final int GL_INVERSE_TRANSPOSE_NV = 34349;
/* 115:    */  public static final int GL_VERTEX_ATTRIB_ARRAY0_NV = 34384;
/* 116:    */  public static final int GL_VERTEX_ATTRIB_ARRAY1_NV = 34385;
/* 117:    */  public static final int GL_VERTEX_ATTRIB_ARRAY2_NV = 34386;
/* 118:    */  public static final int GL_VERTEX_ATTRIB_ARRAY3_NV = 34387;
/* 119:    */  public static final int GL_VERTEX_ATTRIB_ARRAY4_NV = 34388;
/* 120:    */  public static final int GL_VERTEX_ATTRIB_ARRAY5_NV = 34389;
/* 121:    */  public static final int GL_VERTEX_ATTRIB_ARRAY6_NV = 34390;
/* 122:    */  public static final int GL_VERTEX_ATTRIB_ARRAY7_NV = 34391;
/* 123:    */  public static final int GL_VERTEX_ATTRIB_ARRAY8_NV = 34392;
/* 124:    */  public static final int GL_VERTEX_ATTRIB_ARRAY9_NV = 34393;
/* 125:    */  public static final int GL_VERTEX_ATTRIB_ARRAY10_NV = 34394;
/* 126:    */  public static final int GL_VERTEX_ATTRIB_ARRAY11_NV = 34395;
/* 127:    */  public static final int GL_VERTEX_ATTRIB_ARRAY12_NV = 34396;
/* 128:    */  public static final int GL_VERTEX_ATTRIB_ARRAY13_NV = 34397;
/* 129:    */  public static final int GL_VERTEX_ATTRIB_ARRAY14_NV = 34398;
/* 130:    */  public static final int GL_VERTEX_ATTRIB_ARRAY15_NV = 34399;
/* 131:    */  public static final int GL_MAP1_VERTEX_ATTRIB0_4_NV = 34400;
/* 132:    */  public static final int GL_MAP1_VERTEX_ATTRIB1_4_NV = 34401;
/* 133:    */  public static final int GL_MAP1_VERTEX_ATTRIB2_4_NV = 34402;
/* 134:    */  public static final int GL_MAP1_VERTEX_ATTRIB3_4_NV = 34403;
/* 135:    */  public static final int GL_MAP1_VERTEX_ATTRIB4_4_NV = 34404;
/* 136:    */  public static final int GL_MAP1_VERTEX_ATTRIB5_4_NV = 34405;
/* 137:    */  public static final int GL_MAP1_VERTEX_ATTRIB6_4_NV = 34406;
/* 138:    */  public static final int GL_MAP1_VERTEX_ATTRIB7_4_NV = 34407;
/* 139:    */  public static final int GL_MAP1_VERTEX_ATTRIB8_4_NV = 34408;
/* 140:    */  public static final int GL_MAP1_VERTEX_ATTRIB9_4_NV = 34409;
/* 141:    */  public static final int GL_MAP1_VERTEX_ATTRIB10_4_NV = 34410;
/* 142:    */  public static final int GL_MAP1_VERTEX_ATTRIB11_4_NV = 34411;
/* 143:    */  public static final int GL_MAP1_VERTEX_ATTRIB12_4_NV = 34412;
/* 144:    */  public static final int GL_MAP1_VERTEX_ATTRIB13_4_NV = 34413;
/* 145:    */  public static final int GL_MAP1_VERTEX_ATTRIB14_4_NV = 34414;
/* 146:    */  public static final int GL_MAP1_VERTEX_ATTRIB15_4_NV = 34415;
/* 147:    */  public static final int GL_MAP2_VERTEX_ATTRIB0_4_NV = 34416;
/* 148:    */  public static final int GL_MAP2_VERTEX_ATTRIB1_4_NV = 34417;
/* 149:    */  public static final int GL_MAP2_VERTEX_ATTRIB2_4_NV = 34418;
/* 150:    */  public static final int GL_MAP2_VERTEX_ATTRIB3_4_NV = 34419;
/* 151:    */  public static final int GL_MAP2_VERTEX_ATTRIB4_4_NV = 34420;
/* 152:    */  public static final int GL_MAP2_VERTEX_ATTRIB5_4_NV = 34421;
/* 153:    */  public static final int GL_MAP2_VERTEX_ATTRIB6_4_NV = 34422;
/* 154:    */  public static final int GL_MAP2_VERTEX_ATTRIB7_4_NV = 34423;
/* 155:    */  public static final int GL_MAP2_VERTEX_ATTRIB8_4_NV = 34424;
/* 156:    */  public static final int GL_MAP2_VERTEX_ATTRIB9_4_NV = 34425;
/* 157:    */  public static final int GL_MAP2_VERTEX_ATTRIB10_4_NV = 34426;
/* 158:    */  public static final int GL_MAP2_VERTEX_ATTRIB11_4_NV = 34427;
/* 159:    */  public static final int GL_MAP2_VERTEX_ATTRIB12_4_NV = 34428;
/* 160:    */  public static final int GL_MAP2_VERTEX_ATTRIB13_4_NV = 34429;
/* 161:    */  public static final int GL_MAP2_VERTEX_ATTRIB14_4_NV = 34430;
/* 162:    */  public static final int GL_MAP2_VERTEX_ATTRIB15_4_NV = 34431;
/* 163:    */  
/* 164:    */  public static void glExecuteProgramNV(int target, int id, FloatBuffer params)
/* 165:    */  {
/* 166:166 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glExecuteProgramNV;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    BufferChecks.checkBuffer(params, 4);
/* 170:170 */    nglExecuteProgramNV(target, id, MemoryUtil.getAddress(params), function_pointer);
/* 171:    */  }
/* 172:    */  
/* 173:    */  static native void nglExecuteProgramNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 174:    */  
/* 175:175 */  public static void glGetProgramParameterNV(int target, int index, int parameterName, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 176:176 */    long function_pointer = caps.glGetProgramParameterfvNV;
/* 177:177 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 178:178 */    BufferChecks.checkBuffer(params, 4);
/* 179:179 */    nglGetProgramParameterfvNV(target, index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 180:    */  }
/* 181:    */  
/* 182:    */  static native void nglGetProgramParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 183:    */  
/* 184:184 */  public static void glGetProgramParameterNV(int target, int index, int parameterName, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 185:185 */    long function_pointer = caps.glGetProgramParameterdvNV;
/* 186:186 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 187:187 */    BufferChecks.checkBuffer(params, 4);
/* 188:188 */    nglGetProgramParameterdvNV(target, index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 189:    */  }
/* 190:    */  
/* 191:    */  static native void nglGetProgramParameterdvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 192:    */  
/* 193:193 */  public static void glGetTrackMatrixNV(int target, int address, int parameterName, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 194:194 */    long function_pointer = caps.glGetTrackMatrixivNV;
/* 195:195 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 196:196 */    BufferChecks.checkBuffer(params, 4);
/* 197:197 */    nglGetTrackMatrixivNV(target, address, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 198:    */  }
/* 199:    */  
/* 200:    */  static native void nglGetTrackMatrixivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 201:    */  
/* 202:202 */  public static void glGetVertexAttribNV(int index, int parameterName, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 203:203 */    long function_pointer = caps.glGetVertexAttribfvNV;
/* 204:204 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 205:205 */    BufferChecks.checkBuffer(params, 4);
/* 206:206 */    nglGetVertexAttribfvNV(index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 207:    */  }
/* 208:    */  
/* 209:    */  static native void nglGetVertexAttribfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 210:    */  
/* 211:211 */  public static void glGetVertexAttribNV(int index, int parameterName, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 212:212 */    long function_pointer = caps.glGetVertexAttribdvNV;
/* 213:213 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 214:214 */    BufferChecks.checkBuffer(params, 4);
/* 215:215 */    nglGetVertexAttribdvNV(index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 216:    */  }
/* 217:    */  
/* 218:    */  static native void nglGetVertexAttribdvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 219:    */  
/* 220:220 */  public static void glGetVertexAttribNV(int index, int parameterName, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 221:221 */    long function_pointer = caps.glGetVertexAttribivNV;
/* 222:222 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 223:223 */    BufferChecks.checkBuffer(params, 4);
/* 224:224 */    nglGetVertexAttribivNV(index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 225:    */  }
/* 226:    */  
/* 227:    */  static native void nglGetVertexAttribivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 228:    */  
/* 229:229 */  public static ByteBuffer glGetVertexAttribPointerNV(int index, int parameterName, long result_size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 230:230 */    long function_pointer = caps.glGetVertexAttribPointervNV;
/* 231:231 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 232:232 */    ByteBuffer __result = nglGetVertexAttribPointervNV(index, parameterName, result_size, function_pointer);
/* 233:233 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 234:    */  }
/* 235:    */  
/* 236:    */  static native ByteBuffer nglGetVertexAttribPointervNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 237:    */  
/* 238:238 */  public static void glProgramParameter4fNV(int target, int index, float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 239:239 */    long function_pointer = caps.glProgramParameter4fNV;
/* 240:240 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 241:241 */    nglProgramParameter4fNV(target, index, x, y, z, w, function_pointer);
/* 242:    */  }
/* 243:    */  
/* 244:    */  static native void nglProgramParameter4fNV(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 245:    */  
/* 246:246 */  public static void glProgramParameter4dNV(int target, int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 247:247 */    long function_pointer = caps.glProgramParameter4dNV;
/* 248:248 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 249:249 */    nglProgramParameter4dNV(target, index, x, y, z, w, function_pointer);
/* 250:    */  }
/* 251:    */  
/* 252:    */  static native void nglProgramParameter4dNV(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 253:    */  
/* 254:254 */  public static void glProgramParameters4NV(int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 255:255 */    long function_pointer = caps.glProgramParameters4fvNV;
/* 256:256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 257:257 */    BufferChecks.checkDirect(params);
/* 258:258 */    nglProgramParameters4fvNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 259:    */  }
/* 260:    */  
/* 261:    */  static native void nglProgramParameters4fvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 262:    */  
/* 263:263 */  public static void glProgramParameters4NV(int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 264:264 */    long function_pointer = caps.glProgramParameters4dvNV;
/* 265:265 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 266:266 */    BufferChecks.checkDirect(params);
/* 267:267 */    nglProgramParameters4dvNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 268:    */  }
/* 269:    */  
/* 270:    */  static native void nglProgramParameters4dvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 271:    */  
/* 272:272 */  public static void glTrackMatrixNV(int target, int address, int matrix, int transform) { ContextCapabilities caps = GLContext.getCapabilities();
/* 273:273 */    long function_pointer = caps.glTrackMatrixNV;
/* 274:274 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 275:275 */    nglTrackMatrixNV(target, address, matrix, transform, function_pointer);
/* 276:    */  }
/* 277:    */  
/* 278:    */  static native void nglTrackMatrixNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 279:    */  
/* 280:280 */  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, DoubleBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 281:281 */    long function_pointer = caps.glVertexAttribPointerNV;
/* 282:282 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 283:283 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 284:284 */    BufferChecks.checkDirect(buffer);
/* 285:285 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 286:286 */    nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 287:    */  }
/* 288:    */  
/* 289:289 */  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, FloatBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 290:290 */    long function_pointer = caps.glVertexAttribPointerNV;
/* 291:291 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 292:292 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 293:293 */    BufferChecks.checkDirect(buffer);
/* 294:294 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 295:295 */    nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 296:    */  }
/* 297:    */  
/* 298:298 */  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, ByteBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 299:299 */    long function_pointer = caps.glVertexAttribPointerNV;
/* 300:300 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 301:301 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 302:302 */    BufferChecks.checkDirect(buffer);
/* 303:303 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 304:304 */    nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 305:    */  }
/* 306:    */  
/* 307:307 */  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, IntBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 308:308 */    long function_pointer = caps.glVertexAttribPointerNV;
/* 309:309 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 310:310 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 311:311 */    BufferChecks.checkDirect(buffer);
/* 312:312 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 313:313 */    nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 314:    */  }
/* 315:    */  
/* 316:316 */  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, ShortBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 317:317 */    long function_pointer = caps.glVertexAttribPointerNV;
/* 318:318 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 319:319 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 320:320 */    BufferChecks.checkDirect(buffer);
/* 321:321 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 322:322 */    nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer); }
/* 323:    */  
/* 324:    */  static native void nglVertexAttribPointerNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 325:    */  
/* 326:326 */  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 327:327 */    long function_pointer = caps.glVertexAttribPointerNV;
/* 328:328 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 329:329 */    GLChecks.ensureArrayVBOenabled(caps);
/* 330:330 */    nglVertexAttribPointerNVBO(index, size, type, stride, buffer_buffer_offset, function_pointer);
/* 331:    */  }
/* 332:    */  
/* 333:    */  static native void nglVertexAttribPointerNVBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 334:    */  
/* 335:335 */  public static void glVertexAttrib1sNV(int index, short x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 336:336 */    long function_pointer = caps.glVertexAttrib1sNV;
/* 337:337 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 338:338 */    nglVertexAttrib1sNV(index, x, function_pointer);
/* 339:    */  }
/* 340:    */  
/* 341:    */  static native void nglVertexAttrib1sNV(int paramInt, short paramShort, long paramLong);
/* 342:    */  
/* 343:343 */  public static void glVertexAttrib1fNV(int index, float x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 344:344 */    long function_pointer = caps.glVertexAttrib1fNV;
/* 345:345 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 346:346 */    nglVertexAttrib1fNV(index, x, function_pointer);
/* 347:    */  }
/* 348:    */  
/* 349:    */  static native void nglVertexAttrib1fNV(int paramInt, float paramFloat, long paramLong);
/* 350:    */  
/* 351:351 */  public static void glVertexAttrib1dNV(int index, double x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 352:352 */    long function_pointer = caps.glVertexAttrib1dNV;
/* 353:353 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 354:354 */    nglVertexAttrib1dNV(index, x, function_pointer);
/* 355:    */  }
/* 356:    */  
/* 357:    */  static native void nglVertexAttrib1dNV(int paramInt, double paramDouble, long paramLong);
/* 358:    */  
/* 359:359 */  public static void glVertexAttrib2sNV(int index, short x, short y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 360:360 */    long function_pointer = caps.glVertexAttrib2sNV;
/* 361:361 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 362:362 */    nglVertexAttrib2sNV(index, x, y, function_pointer);
/* 363:    */  }
/* 364:    */  
/* 365:    */  static native void nglVertexAttrib2sNV(int paramInt, short paramShort1, short paramShort2, long paramLong);
/* 366:    */  
/* 367:367 */  public static void glVertexAttrib2fNV(int index, float x, float y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 368:368 */    long function_pointer = caps.glVertexAttrib2fNV;
/* 369:369 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 370:370 */    nglVertexAttrib2fNV(index, x, y, function_pointer);
/* 371:    */  }
/* 372:    */  
/* 373:    */  static native void nglVertexAttrib2fNV(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 374:    */  
/* 375:375 */  public static void glVertexAttrib2dNV(int index, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 376:376 */    long function_pointer = caps.glVertexAttrib2dNV;
/* 377:377 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 378:378 */    nglVertexAttrib2dNV(index, x, y, function_pointer);
/* 379:    */  }
/* 380:    */  
/* 381:    */  static native void nglVertexAttrib2dNV(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/* 382:    */  
/* 383:383 */  public static void glVertexAttrib3sNV(int index, short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 384:384 */    long function_pointer = caps.glVertexAttrib3sNV;
/* 385:385 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 386:386 */    nglVertexAttrib3sNV(index, x, y, z, function_pointer);
/* 387:    */  }
/* 388:    */  
/* 389:    */  static native void nglVertexAttrib3sNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 390:    */  
/* 391:391 */  public static void glVertexAttrib3fNV(int index, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 392:392 */    long function_pointer = caps.glVertexAttrib3fNV;
/* 393:393 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 394:394 */    nglVertexAttrib3fNV(index, x, y, z, function_pointer);
/* 395:    */  }
/* 396:    */  
/* 397:    */  static native void nglVertexAttrib3fNV(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 398:    */  
/* 399:399 */  public static void glVertexAttrib3dNV(int index, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 400:400 */    long function_pointer = caps.glVertexAttrib3dNV;
/* 401:401 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 402:402 */    nglVertexAttrib3dNV(index, x, y, z, function_pointer);
/* 403:    */  }
/* 404:    */  
/* 405:    */  static native void nglVertexAttrib3dNV(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 406:    */  
/* 407:407 */  public static void glVertexAttrib4sNV(int index, short x, short y, short z, short w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 408:408 */    long function_pointer = caps.glVertexAttrib4sNV;
/* 409:409 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 410:410 */    nglVertexAttrib4sNV(index, x, y, z, w, function_pointer);
/* 411:    */  }
/* 412:    */  
/* 413:    */  static native void nglVertexAttrib4sNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 414:    */  
/* 415:415 */  public static void glVertexAttrib4fNV(int index, float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 416:416 */    long function_pointer = caps.glVertexAttrib4fNV;
/* 417:417 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 418:418 */    nglVertexAttrib4fNV(index, x, y, z, w, function_pointer);
/* 419:    */  }
/* 420:    */  
/* 421:    */  static native void nglVertexAttrib4fNV(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 422:    */  
/* 423:423 */  public static void glVertexAttrib4dNV(int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 424:424 */    long function_pointer = caps.glVertexAttrib4dNV;
/* 425:425 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 426:426 */    nglVertexAttrib4dNV(index, x, y, z, w, function_pointer);
/* 427:    */  }
/* 428:    */  
/* 429:    */  static native void nglVertexAttrib4dNV(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 430:    */  
/* 431:431 */  public static void glVertexAttrib4ubNV(int index, byte x, byte y, byte z, byte w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 432:432 */    long function_pointer = caps.glVertexAttrib4ubNV;
/* 433:433 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 434:434 */    nglVertexAttrib4ubNV(index, x, y, z, w, function_pointer);
/* 435:    */  }
/* 436:    */  
/* 437:    */  static native void nglVertexAttrib4ubNV(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/* 438:    */  
/* 439:439 */  public static void glVertexAttribs1NV(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 440:440 */    long function_pointer = caps.glVertexAttribs1svNV;
/* 441:441 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 442:442 */    BufferChecks.checkDirect(v);
/* 443:443 */    nglVertexAttribs1svNV(index, v.remaining(), MemoryUtil.getAddress(v), function_pointer);
/* 444:    */  }
/* 445:    */  
/* 446:    */  static native void nglVertexAttribs1svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 447:    */  
/* 448:448 */  public static void glVertexAttribs1NV(int index, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 449:449 */    long function_pointer = caps.glVertexAttribs1fvNV;
/* 450:450 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 451:451 */    BufferChecks.checkDirect(v);
/* 452:452 */    nglVertexAttribs1fvNV(index, v.remaining(), MemoryUtil.getAddress(v), function_pointer);
/* 453:    */  }
/* 454:    */  
/* 455:    */  static native void nglVertexAttribs1fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 456:    */  
/* 457:457 */  public static void glVertexAttribs1NV(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 458:458 */    long function_pointer = caps.glVertexAttribs1dvNV;
/* 459:459 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 460:460 */    BufferChecks.checkDirect(v);
/* 461:461 */    nglVertexAttribs1dvNV(index, v.remaining(), MemoryUtil.getAddress(v), function_pointer);
/* 462:    */  }
/* 463:    */  
/* 464:    */  static native void nglVertexAttribs1dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 465:    */  
/* 466:466 */  public static void glVertexAttribs2NV(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 467:467 */    long function_pointer = caps.glVertexAttribs2svNV;
/* 468:468 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 469:469 */    BufferChecks.checkDirect(v);
/* 470:470 */    nglVertexAttribs2svNV(index, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/* 471:    */  }
/* 472:    */  
/* 473:    */  static native void nglVertexAttribs2svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 474:    */  
/* 475:475 */  public static void glVertexAttribs2NV(int index, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 476:476 */    long function_pointer = caps.glVertexAttribs2fvNV;
/* 477:477 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 478:478 */    BufferChecks.checkDirect(v);
/* 479:479 */    nglVertexAttribs2fvNV(index, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/* 480:    */  }
/* 481:    */  
/* 482:    */  static native void nglVertexAttribs2fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 483:    */  
/* 484:484 */  public static void glVertexAttribs2NV(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 485:485 */    long function_pointer = caps.glVertexAttribs2dvNV;
/* 486:486 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 487:487 */    BufferChecks.checkDirect(v);
/* 488:488 */    nglVertexAttribs2dvNV(index, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/* 489:    */  }
/* 490:    */  
/* 491:    */  static native void nglVertexAttribs2dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 492:    */  
/* 493:493 */  public static void glVertexAttribs3NV(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 494:494 */    long function_pointer = caps.glVertexAttribs3svNV;
/* 495:495 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 496:496 */    BufferChecks.checkDirect(v);
/* 497:497 */    nglVertexAttribs3svNV(index, v.remaining() / 3, MemoryUtil.getAddress(v), function_pointer);
/* 498:    */  }
/* 499:    */  
/* 500:    */  static native void nglVertexAttribs3svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 501:    */  
/* 502:502 */  public static void glVertexAttribs3NV(int index, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 503:503 */    long function_pointer = caps.glVertexAttribs3fvNV;
/* 504:504 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 505:505 */    BufferChecks.checkDirect(v);
/* 506:506 */    nglVertexAttribs3fvNV(index, v.remaining() / 3, MemoryUtil.getAddress(v), function_pointer);
/* 507:    */  }
/* 508:    */  
/* 509:    */  static native void nglVertexAttribs3fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 510:    */  
/* 511:511 */  public static void glVertexAttribs3NV(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 512:512 */    long function_pointer = caps.glVertexAttribs3dvNV;
/* 513:513 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 514:514 */    BufferChecks.checkDirect(v);
/* 515:515 */    nglVertexAttribs3dvNV(index, v.remaining() / 3, MemoryUtil.getAddress(v), function_pointer);
/* 516:    */  }
/* 517:    */  
/* 518:    */  static native void nglVertexAttribs3dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 519:    */  
/* 520:520 */  public static void glVertexAttribs4NV(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 521:521 */    long function_pointer = caps.glVertexAttribs4svNV;
/* 522:522 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 523:523 */    BufferChecks.checkDirect(v);
/* 524:524 */    nglVertexAttribs4svNV(index, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/* 525:    */  }
/* 526:    */  
/* 527:    */  static native void nglVertexAttribs4svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 528:    */  
/* 529:529 */  public static void glVertexAttribs4NV(int index, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 530:530 */    long function_pointer = caps.glVertexAttribs4fvNV;
/* 531:531 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 532:532 */    BufferChecks.checkDirect(v);
/* 533:533 */    nglVertexAttribs4fvNV(index, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/* 534:    */  }
/* 535:    */  
/* 536:    */  static native void nglVertexAttribs4fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 537:    */  
/* 538:538 */  public static void glVertexAttribs4NV(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 539:539 */    long function_pointer = caps.glVertexAttribs4dvNV;
/* 540:540 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 541:541 */    BufferChecks.checkDirect(v);
/* 542:542 */    nglVertexAttribs4dvNV(index, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/* 543:    */  }
/* 544:    */  
/* 545:    */  static native void nglVertexAttribs4dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 546:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */