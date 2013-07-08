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
/*  23:    */public final class GL15
/*  24:    */{
/*  25:    */  public static final int GL_ARRAY_BUFFER = 34962;
/*  26:    */  public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
/*  27:    */  public static final int GL_ARRAY_BUFFER_BINDING = 34964;
/*  28:    */  public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
/*  29:    */  public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 34966;
/*  30:    */  public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 34967;
/*  31:    */  public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 34968;
/*  32:    */  public static final int GL_INDEX_ARRAY_BUFFER_BINDING = 34969;
/*  33:    */  public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 34970;
/*  34:    */  public static final int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING = 34971;
/*  35:    */  public static final int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 34972;
/*  36:    */  public static final int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING = 34973;
/*  37:    */  public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING = 34974;
/*  38:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
/*  39:    */  public static final int GL_STREAM_DRAW = 35040;
/*  40:    */  public static final int GL_STREAM_READ = 35041;
/*  41:    */  public static final int GL_STREAM_COPY = 35042;
/*  42:    */  public static final int GL_STATIC_DRAW = 35044;
/*  43:    */  public static final int GL_STATIC_READ = 35045;
/*  44:    */  public static final int GL_STATIC_COPY = 35046;
/*  45:    */  public static final int GL_DYNAMIC_DRAW = 35048;
/*  46:    */  public static final int GL_DYNAMIC_READ = 35049;
/*  47:    */  public static final int GL_DYNAMIC_COPY = 35050;
/*  48:    */  public static final int GL_READ_ONLY = 35000;
/*  49:    */  public static final int GL_WRITE_ONLY = 35001;
/*  50:    */  public static final int GL_READ_WRITE = 35002;
/*  51:    */  public static final int GL_BUFFER_SIZE = 34660;
/*  52:    */  public static final int GL_BUFFER_USAGE = 34661;
/*  53:    */  public static final int GL_BUFFER_ACCESS = 35003;
/*  54:    */  public static final int GL_BUFFER_MAPPED = 35004;
/*  55:    */  public static final int GL_BUFFER_MAP_POINTER = 35005;
/*  56:    */  public static final int GL_FOG_COORD_SRC = 33872;
/*  57:    */  public static final int GL_FOG_COORD = 33873;
/*  58:    */  public static final int GL_CURRENT_FOG_COORD = 33875;
/*  59:    */  public static final int GL_FOG_COORD_ARRAY_TYPE = 33876;
/*  60:    */  public static final int GL_FOG_COORD_ARRAY_STRIDE = 33877;
/*  61:    */  public static final int GL_FOG_COORD_ARRAY_POINTER = 33878;
/*  62:    */  public static final int GL_FOG_COORD_ARRAY = 33879;
/*  63:    */  public static final int GL_FOG_COORD_ARRAY_BUFFER_BINDING = 34973;
/*  64:    */  public static final int GL_SRC0_RGB = 34176;
/*  65:    */  public static final int GL_SRC1_RGB = 34177;
/*  66:    */  public static final int GL_SRC2_RGB = 34178;
/*  67:    */  public static final int GL_SRC0_ALPHA = 34184;
/*  68:    */  public static final int GL_SRC1_ALPHA = 34185;
/*  69:    */  public static final int GL_SRC2_ALPHA = 34186;
/*  70:    */  public static final int GL_SAMPLES_PASSED = 35092;
/*  71:    */  public static final int GL_QUERY_COUNTER_BITS = 34916;
/*  72:    */  public static final int GL_CURRENT_QUERY = 34917;
/*  73:    */  public static final int GL_QUERY_RESULT = 34918;
/*  74:    */  public static final int GL_QUERY_RESULT_AVAILABLE = 34919;
/*  75:    */  
/*  76:    */  public static void glBindBuffer(int target, int buffer)
/*  77:    */  {
/*  78: 78 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  79: 79 */    long function_pointer = caps.glBindBuffer;
/*  80: 80 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  81: 81 */    StateTracker.bindBuffer(caps, target, buffer);
/*  82: 82 */    nglBindBuffer(target, buffer, function_pointer);
/*  83:    */  }
/*  84:    */  
/*  85:    */  static native void nglBindBuffer(int paramInt1, int paramInt2, long paramLong);
/*  86:    */  
/*  87: 87 */  public static void glDeleteBuffers(IntBuffer buffers) { ContextCapabilities caps = GLContext.getCapabilities();
/*  88: 88 */    long function_pointer = caps.glDeleteBuffers;
/*  89: 89 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  90: 90 */    BufferChecks.checkDirect(buffers);
/*  91: 91 */    nglDeleteBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*  92:    */  }
/*  93:    */  
/*  94:    */  static native void nglDeleteBuffers(int paramInt, long paramLong1, long paramLong2);
/*  95:    */  
/*  96:    */  public static void glDeleteBuffers(int buffer) {
/*  97: 97 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  98: 98 */    long function_pointer = caps.glDeleteBuffers;
/*  99: 99 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 100:100 */    nglDeleteBuffers(1, APIUtil.getInt(caps, buffer), function_pointer);
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static void glGenBuffers(IntBuffer buffers) {
/* 104:104 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 105:105 */    long function_pointer = caps.glGenBuffers;
/* 106:106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 107:107 */    BufferChecks.checkDirect(buffers);
/* 108:108 */    nglGenBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/* 109:    */  }
/* 110:    */  
/* 111:    */  static native void nglGenBuffers(int paramInt, long paramLong1, long paramLong2);
/* 112:    */  
/* 113:    */  public static int glGenBuffers() {
/* 114:114 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 115:115 */    long function_pointer = caps.glGenBuffers;
/* 116:116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 117:117 */    IntBuffer buffers = APIUtil.getBufferInt(caps);
/* 118:118 */    nglGenBuffers(1, MemoryUtil.getAddress(buffers), function_pointer);
/* 119:119 */    return buffers.get(0);
/* 120:    */  }
/* 121:    */  
/* 122:    */  public static boolean glIsBuffer(int buffer) {
/* 123:123 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 124:124 */    long function_pointer = caps.glIsBuffer;
/* 125:125 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 126:126 */    boolean __result = nglIsBuffer(buffer, function_pointer);
/* 127:127 */    return __result;
/* 128:    */  }
/* 129:    */  
/* 130:    */  static native boolean nglIsBuffer(int paramInt, long paramLong);
/* 131:    */  
/* 132:132 */  public static void glBufferData(int target, long data_size, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 133:133 */    long function_pointer = caps.glBufferData;
/* 134:134 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 135:135 */    nglBufferData(target, data_size, 0L, usage, function_pointer);
/* 136:    */  }
/* 137:    */  
/* 138:138 */  public static void glBufferData(int target, ByteBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 139:139 */    long function_pointer = caps.glBufferData;
/* 140:140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 141:141 */    BufferChecks.checkDirect(data);
/* 142:142 */    nglBufferData(target, data.remaining(), MemoryUtil.getAddress(data), usage, function_pointer);
/* 143:    */  }
/* 144:    */  
/* 145:145 */  public static void glBufferData(int target, DoubleBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 146:146 */    long function_pointer = caps.glBufferData;
/* 147:147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 148:148 */    BufferChecks.checkDirect(data);
/* 149:149 */    nglBufferData(target, data.remaining() << 3, MemoryUtil.getAddress(data), usage, function_pointer);
/* 150:    */  }
/* 151:    */  
/* 152:152 */  public static void glBufferData(int target, FloatBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glBufferData;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    BufferChecks.checkDirect(data);
/* 156:156 */    nglBufferData(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/* 157:    */  }
/* 158:    */  
/* 159:159 */  public static void glBufferData(int target, IntBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 160:160 */    long function_pointer = caps.glBufferData;
/* 161:161 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 162:162 */    BufferChecks.checkDirect(data);
/* 163:163 */    nglBufferData(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/* 164:    */  }
/* 165:    */  
/* 166:166 */  public static void glBufferData(int target, ShortBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glBufferData;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    BufferChecks.checkDirect(data);
/* 170:170 */    nglBufferData(target, data.remaining() << 1, MemoryUtil.getAddress(data), usage, function_pointer);
/* 171:    */  }
/* 172:    */  
/* 173:    */  static native void nglBufferData(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/* 174:    */  
/* 175:175 */  public static void glBufferSubData(int target, long offset, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 176:176 */    long function_pointer = caps.glBufferSubData;
/* 177:177 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 178:178 */    BufferChecks.checkDirect(data);
/* 179:179 */    nglBufferSubData(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 180:    */  }
/* 181:    */  
/* 182:182 */  public static void glBufferSubData(int target, long offset, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 183:183 */    long function_pointer = caps.glBufferSubData;
/* 184:184 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 185:185 */    BufferChecks.checkDirect(data);
/* 186:186 */    nglBufferSubData(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 187:    */  }
/* 188:    */  
/* 189:189 */  public static void glBufferSubData(int target, long offset, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glBufferSubData;
/* 191:191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    BufferChecks.checkDirect(data);
/* 193:193 */    nglBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 194:    */  }
/* 195:    */  
/* 196:196 */  public static void glBufferSubData(int target, long offset, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 197:197 */    long function_pointer = caps.glBufferSubData;
/* 198:198 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 199:199 */    BufferChecks.checkDirect(data);
/* 200:200 */    nglBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 201:    */  }
/* 202:    */  
/* 203:203 */  public static void glBufferSubData(int target, long offset, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 204:204 */    long function_pointer = caps.glBufferSubData;
/* 205:205 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 206:206 */    BufferChecks.checkDirect(data);
/* 207:207 */    nglBufferSubData(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 208:    */  }
/* 209:    */  
/* 210:    */  static native void nglBufferSubData(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 211:    */  
/* 212:212 */  public static void glGetBufferSubData(int target, long offset, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 213:213 */    long function_pointer = caps.glGetBufferSubData;
/* 214:214 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 215:215 */    BufferChecks.checkDirect(data);
/* 216:216 */    nglGetBufferSubData(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 217:    */  }
/* 218:    */  
/* 219:219 */  public static void glGetBufferSubData(int target, long offset, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 220:220 */    long function_pointer = caps.glGetBufferSubData;
/* 221:221 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 222:222 */    BufferChecks.checkDirect(data);
/* 223:223 */    nglGetBufferSubData(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 224:    */  }
/* 225:    */  
/* 226:226 */  public static void glGetBufferSubData(int target, long offset, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 227:227 */    long function_pointer = caps.glGetBufferSubData;
/* 228:228 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 229:229 */    BufferChecks.checkDirect(data);
/* 230:230 */    nglGetBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 231:    */  }
/* 232:    */  
/* 233:233 */  public static void glGetBufferSubData(int target, long offset, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 234:234 */    long function_pointer = caps.glGetBufferSubData;
/* 235:235 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 236:236 */    BufferChecks.checkDirect(data);
/* 237:237 */    nglGetBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 238:    */  }
/* 239:    */  
/* 240:240 */  public static void glGetBufferSubData(int target, long offset, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 241:241 */    long function_pointer = caps.glGetBufferSubData;
/* 242:242 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 243:243 */    BufferChecks.checkDirect(data);
/* 244:244 */    nglGetBufferSubData(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 245:    */  }
/* 246:    */  
/* 257:    */  static native void nglGetBufferSubData(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 258:    */  
/* 269:    */  public static ByteBuffer glMapBuffer(int target, int access, ByteBuffer old_buffer)
/* 270:    */  {
/* 271:271 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 272:272 */    long function_pointer = caps.glMapBuffer;
/* 273:273 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 274:274 */    if (old_buffer != null)
/* 275:275 */      BufferChecks.checkDirect(old_buffer);
/* 276:276 */    ByteBuffer __result = nglMapBuffer(target, access, GLChecks.getBufferObjectSize(caps, target), old_buffer, function_pointer);
/* 277:277 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 278:    */  }
/* 279:    */  
/* 300:    */  public static ByteBuffer glMapBuffer(int target, int access, long length, ByteBuffer old_buffer)
/* 301:    */  {
/* 302:302 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 303:303 */    long function_pointer = caps.glMapBuffer;
/* 304:304 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 305:305 */    if (old_buffer != null)
/* 306:306 */      BufferChecks.checkDirect(old_buffer);
/* 307:307 */    ByteBuffer __result = nglMapBuffer(target, access, length, old_buffer, function_pointer);
/* 308:308 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 309:    */  }
/* 310:    */  
/* 311:    */  static native ByteBuffer nglMapBuffer(int paramInt1, int paramInt2, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/* 312:    */  
/* 313:313 */  public static boolean glUnmapBuffer(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/* 314:314 */    long function_pointer = caps.glUnmapBuffer;
/* 315:315 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 316:316 */    boolean __result = nglUnmapBuffer(target, function_pointer);
/* 317:317 */    return __result;
/* 318:    */  }
/* 319:    */  
/* 320:    */  static native boolean nglUnmapBuffer(int paramInt, long paramLong);
/* 321:    */  
/* 322:322 */  public static void glGetBufferParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 323:323 */    long function_pointer = caps.glGetBufferParameteriv;
/* 324:324 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 325:325 */    BufferChecks.checkBuffer(params, 4);
/* 326:326 */    nglGetBufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 327:    */  }
/* 328:    */  
/* 331:    */  static native void nglGetBufferParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 332:    */  
/* 334:    */  @Deprecated
/* 335:    */  public static int glGetBufferParameter(int target, int pname)
/* 336:    */  {
/* 337:337 */    return glGetBufferParameteri(target, pname);
/* 338:    */  }
/* 339:    */  
/* 340:    */  public static int glGetBufferParameteri(int target, int pname)
/* 341:    */  {
/* 342:342 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 343:343 */    long function_pointer = caps.glGetBufferParameteriv;
/* 344:344 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 345:345 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 346:346 */    nglGetBufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 347:347 */    return params.get(0);
/* 348:    */  }
/* 349:    */  
/* 350:    */  public static ByteBuffer glGetBufferPointer(int target, int pname) {
/* 351:351 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 352:352 */    long function_pointer = caps.glGetBufferPointerv;
/* 353:353 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 354:354 */    ByteBuffer __result = nglGetBufferPointerv(target, pname, GLChecks.getBufferObjectSize(caps, target), function_pointer);
/* 355:355 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 356:    */  }
/* 357:    */  
/* 358:    */  static native ByteBuffer nglGetBufferPointerv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 359:    */  
/* 360:360 */  public static void glGenQueries(IntBuffer ids) { ContextCapabilities caps = GLContext.getCapabilities();
/* 361:361 */    long function_pointer = caps.glGenQueries;
/* 362:362 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 363:363 */    BufferChecks.checkDirect(ids);
/* 364:364 */    nglGenQueries(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/* 365:    */  }
/* 366:    */  
/* 367:    */  static native void nglGenQueries(int paramInt, long paramLong1, long paramLong2);
/* 368:    */  
/* 369:    */  public static int glGenQueries() {
/* 370:370 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 371:371 */    long function_pointer = caps.glGenQueries;
/* 372:372 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 373:373 */    IntBuffer ids = APIUtil.getBufferInt(caps);
/* 374:374 */    nglGenQueries(1, MemoryUtil.getAddress(ids), function_pointer);
/* 375:375 */    return ids.get(0);
/* 376:    */  }
/* 377:    */  
/* 378:    */  public static void glDeleteQueries(IntBuffer ids) {
/* 379:379 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 380:380 */    long function_pointer = caps.glDeleteQueries;
/* 381:381 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 382:382 */    BufferChecks.checkDirect(ids);
/* 383:383 */    nglDeleteQueries(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/* 384:    */  }
/* 385:    */  
/* 386:    */  static native void nglDeleteQueries(int paramInt, long paramLong1, long paramLong2);
/* 387:    */  
/* 388:    */  public static void glDeleteQueries(int id) {
/* 389:389 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 390:390 */    long function_pointer = caps.glDeleteQueries;
/* 391:391 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 392:392 */    nglDeleteQueries(1, APIUtil.getInt(caps, id), function_pointer);
/* 393:    */  }
/* 394:    */  
/* 395:    */  public static boolean glIsQuery(int id) {
/* 396:396 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 397:397 */    long function_pointer = caps.glIsQuery;
/* 398:398 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 399:399 */    boolean __result = nglIsQuery(id, function_pointer);
/* 400:400 */    return __result;
/* 401:    */  }
/* 402:    */  
/* 403:    */  static native boolean nglIsQuery(int paramInt, long paramLong);
/* 404:    */  
/* 405:405 */  public static void glBeginQuery(int target, int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 406:406 */    long function_pointer = caps.glBeginQuery;
/* 407:407 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 408:408 */    nglBeginQuery(target, id, function_pointer);
/* 409:    */  }
/* 410:    */  
/* 411:    */  static native void nglBeginQuery(int paramInt1, int paramInt2, long paramLong);
/* 412:    */  
/* 413:413 */  public static void glEndQuery(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/* 414:414 */    long function_pointer = caps.glEndQuery;
/* 415:415 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 416:416 */    nglEndQuery(target, function_pointer);
/* 417:    */  }
/* 418:    */  
/* 419:    */  static native void nglEndQuery(int paramInt, long paramLong);
/* 420:    */  
/* 421:421 */  public static void glGetQuery(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 422:422 */    long function_pointer = caps.glGetQueryiv;
/* 423:423 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 424:424 */    BufferChecks.checkBuffer(params, 1);
/* 425:425 */    nglGetQueryiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 426:    */  }
/* 427:    */  
/* 430:    */  static native void nglGetQueryiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 431:    */  
/* 433:    */  @Deprecated
/* 434:    */  public static int glGetQuery(int target, int pname)
/* 435:    */  {
/* 436:436 */    return glGetQueryi(target, pname);
/* 437:    */  }
/* 438:    */  
/* 439:    */  public static int glGetQueryi(int target, int pname)
/* 440:    */  {
/* 441:441 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 442:442 */    long function_pointer = caps.glGetQueryiv;
/* 443:443 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 444:444 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 445:445 */    nglGetQueryiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 446:446 */    return params.get(0);
/* 447:    */  }
/* 448:    */  
/* 449:    */  public static void glGetQueryObject(int id, int pname, IntBuffer params) {
/* 450:450 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 451:451 */    long function_pointer = caps.glGetQueryObjectiv;
/* 452:452 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 453:453 */    BufferChecks.checkBuffer(params, 1);
/* 454:454 */    nglGetQueryObjectiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 455:    */  }
/* 456:    */  
/* 457:    */  static native void nglGetQueryObjectiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 458:    */  
/* 459:    */  public static int glGetQueryObjecti(int id, int pname) {
/* 460:460 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 461:461 */    long function_pointer = caps.glGetQueryObjectiv;
/* 462:462 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 463:463 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 464:464 */    nglGetQueryObjectiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 465:465 */    return params.get(0);
/* 466:    */  }
/* 467:    */  
/* 468:    */  public static void glGetQueryObjectu(int id, int pname, IntBuffer params) {
/* 469:469 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 470:470 */    long function_pointer = caps.glGetQueryObjectuiv;
/* 471:471 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 472:472 */    BufferChecks.checkBuffer(params, 1);
/* 473:473 */    nglGetQueryObjectuiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 474:    */  }
/* 475:    */  
/* 476:    */  static native void nglGetQueryObjectuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 477:    */  
/* 478:    */  public static int glGetQueryObjectui(int id, int pname) {
/* 479:479 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 480:480 */    long function_pointer = caps.glGetQueryObjectuiv;
/* 481:481 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 482:482 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 483:483 */    nglGetQueryObjectuiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 484:484 */    return params.get(0);
/* 485:    */  }
/* 486:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL15
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */