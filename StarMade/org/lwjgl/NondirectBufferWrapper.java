/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.DoubleBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.nio.LongBuffer;
/*   9:    */import java.nio.ShortBuffer;
/*  10:    */
/*  51:    */public final class NondirectBufferWrapper
/*  52:    */{
/*  53:    */  private static final int INITIAL_BUFFER_SIZE = 1;
/*  54: 54 */  private static final ThreadLocal<CachedBuffers> thread_buffer = new ThreadLocal() {
/*  55:    */    protected NondirectBufferWrapper.CachedBuffers initialValue() {
/*  56: 56 */      return new NondirectBufferWrapper.CachedBuffers(1, null);
/*  57:    */    }
/*  58:    */  };
/*  59:    */  
/*  60:    */  private static CachedBuffers getCachedBuffers(int minimum_byte_size) {
/*  61: 61 */    CachedBuffers buffers = (CachedBuffers)thread_buffer.get();
/*  62: 62 */    int current_byte_size = buffers.byte_buffer.capacity();
/*  63: 63 */    if (minimum_byte_size > current_byte_size) {
/*  64: 64 */      buffers = new CachedBuffers(minimum_byte_size, null);
/*  65: 65 */      thread_buffer.set(buffers);
/*  66:    */    }
/*  67: 67 */    return buffers;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public static ByteBuffer wrapNoCopyBuffer(ByteBuffer buf, int size) {
/*  71: 71 */    BufferChecks.checkBufferSize(buf, size);
/*  72: 72 */    return wrapNoCopyDirect(buf);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static ShortBuffer wrapNoCopyBuffer(ShortBuffer buf, int size) {
/*  76: 76 */    BufferChecks.checkBufferSize(buf, size);
/*  77: 77 */    return wrapNoCopyDirect(buf);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public static IntBuffer wrapNoCopyBuffer(IntBuffer buf, int size) {
/*  81: 81 */    BufferChecks.checkBufferSize(buf, size);
/*  82: 82 */    return wrapNoCopyDirect(buf);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static LongBuffer wrapNoCopyBuffer(LongBuffer buf, int size) {
/*  86: 86 */    BufferChecks.checkBufferSize(buf, size);
/*  87: 87 */    return wrapNoCopyDirect(buf);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public static FloatBuffer wrapNoCopyBuffer(FloatBuffer buf, int size) {
/*  91: 91 */    BufferChecks.checkBufferSize(buf, size);
/*  92: 92 */    return wrapNoCopyDirect(buf);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static DoubleBuffer wrapNoCopyBuffer(DoubleBuffer buf, int size) {
/*  96: 96 */    BufferChecks.checkBufferSize(buf, size);
/*  97: 97 */    return wrapNoCopyDirect(buf);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public static ByteBuffer wrapBuffer(ByteBuffer buf, int size) {
/* 101:101 */    BufferChecks.checkBufferSize(buf, size);
/* 102:102 */    return wrapDirect(buf);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static ShortBuffer wrapBuffer(ShortBuffer buf, int size) {
/* 106:106 */    BufferChecks.checkBufferSize(buf, size);
/* 107:107 */    return wrapDirect(buf);
/* 108:    */  }
/* 109:    */  
/* 110:    */  public static IntBuffer wrapBuffer(IntBuffer buf, int size) {
/* 111:111 */    BufferChecks.checkBufferSize(buf, size);
/* 112:112 */    return wrapDirect(buf);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public static LongBuffer wrapBuffer(LongBuffer buf, int size) {
/* 116:116 */    BufferChecks.checkBufferSize(buf, size);
/* 117:117 */    return wrapDirect(buf);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public static FloatBuffer wrapBuffer(FloatBuffer buf, int size) {
/* 121:121 */    BufferChecks.checkBufferSize(buf, size);
/* 122:122 */    return wrapDirect(buf);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public static DoubleBuffer wrapBuffer(DoubleBuffer buf, int size) {
/* 126:126 */    BufferChecks.checkBufferSize(buf, size);
/* 127:127 */    return wrapDirect(buf);
/* 128:    */  }
/* 129:    */  
/* 130:    */  public static ByteBuffer wrapDirect(ByteBuffer buffer) {
/* 131:131 */    if (!buffer.isDirect())
/* 132:132 */      return doWrap(buffer);
/* 133:133 */    return buffer;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public static ShortBuffer wrapDirect(ShortBuffer buffer) {
/* 137:137 */    if (!buffer.isDirect())
/* 138:138 */      return doWrap(buffer);
/* 139:139 */    return buffer;
/* 140:    */  }
/* 141:    */  
/* 142:    */  public static FloatBuffer wrapDirect(FloatBuffer buffer) {
/* 143:143 */    if (!buffer.isDirect())
/* 144:144 */      return doWrap(buffer);
/* 145:145 */    return buffer;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public static IntBuffer wrapDirect(IntBuffer buffer) {
/* 149:149 */    if (!buffer.isDirect())
/* 150:150 */      return doWrap(buffer);
/* 151:151 */    return buffer;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public static LongBuffer wrapDirect(LongBuffer buffer) {
/* 155:155 */    if (!buffer.isDirect())
/* 156:156 */      return doWrap(buffer);
/* 157:157 */    return buffer;
/* 158:    */  }
/* 159:    */  
/* 160:    */  public static DoubleBuffer wrapDirect(DoubleBuffer buffer) {
/* 161:161 */    if (!buffer.isDirect())
/* 162:162 */      return doWrap(buffer);
/* 163:163 */    return buffer;
/* 164:    */  }
/* 165:    */  
/* 166:    */  public static ByteBuffer wrapNoCopyDirect(ByteBuffer buffer) {
/* 167:167 */    if (!buffer.isDirect())
/* 168:168 */      return doNoCopyWrap(buffer);
/* 169:169 */    return buffer;
/* 170:    */  }
/* 171:    */  
/* 172:    */  public static ShortBuffer wrapNoCopyDirect(ShortBuffer buffer) {
/* 173:173 */    if (!buffer.isDirect())
/* 174:174 */      return doNoCopyWrap(buffer);
/* 175:175 */    return buffer;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public static FloatBuffer wrapNoCopyDirect(FloatBuffer buffer) {
/* 179:179 */    if (!buffer.isDirect())
/* 180:180 */      return doNoCopyWrap(buffer);
/* 181:181 */    return buffer;
/* 182:    */  }
/* 183:    */  
/* 184:    */  public static IntBuffer wrapNoCopyDirect(IntBuffer buffer) {
/* 185:185 */    if (!buffer.isDirect())
/* 186:186 */      return doNoCopyWrap(buffer);
/* 187:187 */    return buffer;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public static LongBuffer wrapNoCopyDirect(LongBuffer buffer) {
/* 191:191 */    if (!buffer.isDirect())
/* 192:192 */      return doNoCopyWrap(buffer);
/* 193:193 */    return buffer;
/* 194:    */  }
/* 195:    */  
/* 196:    */  public static DoubleBuffer wrapNoCopyDirect(DoubleBuffer buffer) {
/* 197:197 */    if (!buffer.isDirect())
/* 198:198 */      return doNoCopyWrap(buffer);
/* 199:199 */    return buffer;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public static void copy(ByteBuffer src, ByteBuffer dst) {
/* 203:203 */    if ((dst != null) && (!dst.isDirect())) {
/* 204:204 */      int saved_position = dst.position();
/* 205:205 */      dst.put(src);
/* 206:206 */      dst.position(saved_position);
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 210:    */  public static void copy(ShortBuffer src, ShortBuffer dst) {
/* 211:211 */    if ((dst != null) && (!dst.isDirect())) {
/* 212:212 */      int saved_position = dst.position();
/* 213:213 */      dst.put(src);
/* 214:214 */      dst.position(saved_position);
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 218:    */  public static void copy(IntBuffer src, IntBuffer dst) {
/* 219:219 */    if ((dst != null) && (!dst.isDirect())) {
/* 220:220 */      int saved_position = dst.position();
/* 221:221 */      dst.put(src);
/* 222:222 */      dst.position(saved_position);
/* 223:    */    }
/* 224:    */  }
/* 225:    */  
/* 226:    */  public static void copy(FloatBuffer src, FloatBuffer dst) {
/* 227:227 */    if ((dst != null) && (!dst.isDirect())) {
/* 228:228 */      int saved_position = dst.position();
/* 229:229 */      dst.put(src);
/* 230:230 */      dst.position(saved_position);
/* 231:    */    }
/* 232:    */  }
/* 233:    */  
/* 234:    */  public static void copy(LongBuffer src, LongBuffer dst) {
/* 235:235 */    if ((dst != null) && (!dst.isDirect())) {
/* 236:236 */      int saved_position = dst.position();
/* 237:237 */      dst.put(src);
/* 238:238 */      dst.position(saved_position);
/* 239:    */    }
/* 240:    */  }
/* 241:    */  
/* 242:    */  public static void copy(DoubleBuffer src, DoubleBuffer dst) {
/* 243:243 */    if ((dst != null) && (!dst.isDirect())) {
/* 244:244 */      int saved_position = dst.position();
/* 245:245 */      dst.put(src);
/* 246:246 */      dst.position(saved_position);
/* 247:    */    }
/* 248:    */  }
/* 249:    */  
/* 250:    */  private static ByteBuffer doNoCopyWrap(ByteBuffer buffer) {
/* 251:251 */    ByteBuffer direct_buffer = lookupBuffer(buffer);
/* 252:252 */    direct_buffer.limit(buffer.limit());
/* 253:253 */    direct_buffer.position(buffer.position());
/* 254:254 */    return direct_buffer;
/* 255:    */  }
/* 256:    */  
/* 257:    */  private static ShortBuffer doNoCopyWrap(ShortBuffer buffer) {
/* 258:258 */    ShortBuffer direct_buffer = lookupBuffer(buffer);
/* 259:259 */    direct_buffer.limit(buffer.limit());
/* 260:260 */    direct_buffer.position(buffer.position());
/* 261:261 */    return direct_buffer;
/* 262:    */  }
/* 263:    */  
/* 264:    */  private static IntBuffer doNoCopyWrap(IntBuffer buffer) {
/* 265:265 */    IntBuffer direct_buffer = lookupBuffer(buffer);
/* 266:266 */    direct_buffer.limit(buffer.limit());
/* 267:267 */    direct_buffer.position(buffer.position());
/* 268:268 */    return direct_buffer;
/* 269:    */  }
/* 270:    */  
/* 271:    */  private static FloatBuffer doNoCopyWrap(FloatBuffer buffer) {
/* 272:272 */    FloatBuffer direct_buffer = lookupBuffer(buffer);
/* 273:273 */    direct_buffer.limit(buffer.limit());
/* 274:274 */    direct_buffer.position(buffer.position());
/* 275:275 */    return direct_buffer;
/* 276:    */  }
/* 277:    */  
/* 278:    */  private static LongBuffer doNoCopyWrap(LongBuffer buffer) {
/* 279:279 */    LongBuffer direct_buffer = lookupBuffer(buffer);
/* 280:280 */    direct_buffer.limit(buffer.limit());
/* 281:281 */    direct_buffer.position(buffer.position());
/* 282:282 */    return direct_buffer;
/* 283:    */  }
/* 284:    */  
/* 285:    */  private static DoubleBuffer doNoCopyWrap(DoubleBuffer buffer) {
/* 286:286 */    DoubleBuffer direct_buffer = lookupBuffer(buffer);
/* 287:287 */    direct_buffer.limit(buffer.limit());
/* 288:288 */    direct_buffer.position(buffer.position());
/* 289:289 */    return direct_buffer;
/* 290:    */  }
/* 291:    */  
/* 292:    */  private static ByteBuffer lookupBuffer(ByteBuffer buffer) {
/* 293:293 */    return getCachedBuffers(buffer.remaining()).byte_buffer;
/* 294:    */  }
/* 295:    */  
/* 296:    */  private static ByteBuffer doWrap(ByteBuffer buffer) {
/* 297:297 */    ByteBuffer direct_buffer = lookupBuffer(buffer);
/* 298:298 */    direct_buffer.clear();
/* 299:299 */    int saved_position = buffer.position();
/* 300:300 */    direct_buffer.put(buffer);
/* 301:301 */    buffer.position(saved_position);
/* 302:302 */    direct_buffer.flip();
/* 303:303 */    return direct_buffer;
/* 304:    */  }
/* 305:    */  
/* 306:    */  private static ShortBuffer lookupBuffer(ShortBuffer buffer) {
/* 307:307 */    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 2);
/* 308:308 */    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.short_buffer_little : buffers.short_buffer_big;
/* 309:    */  }
/* 310:    */  
/* 311:    */  private static ShortBuffer doWrap(ShortBuffer buffer) {
/* 312:312 */    ShortBuffer direct_buffer = lookupBuffer(buffer);
/* 313:313 */    direct_buffer.clear();
/* 314:314 */    int saved_position = buffer.position();
/* 315:315 */    direct_buffer.put(buffer);
/* 316:316 */    buffer.position(saved_position);
/* 317:317 */    direct_buffer.flip();
/* 318:318 */    return direct_buffer;
/* 319:    */  }
/* 320:    */  
/* 321:    */  private static FloatBuffer lookupBuffer(FloatBuffer buffer) {
/* 322:322 */    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 4);
/* 323:323 */    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.float_buffer_little : buffers.float_buffer_big;
/* 324:    */  }
/* 325:    */  
/* 326:    */  private static FloatBuffer doWrap(FloatBuffer buffer) {
/* 327:327 */    FloatBuffer direct_buffer = lookupBuffer(buffer);
/* 328:328 */    direct_buffer.clear();
/* 329:329 */    int saved_position = buffer.position();
/* 330:330 */    direct_buffer.put(buffer);
/* 331:331 */    buffer.position(saved_position);
/* 332:332 */    direct_buffer.flip();
/* 333:333 */    return direct_buffer;
/* 334:    */  }
/* 335:    */  
/* 336:    */  private static IntBuffer lookupBuffer(IntBuffer buffer) {
/* 337:337 */    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 4);
/* 338:338 */    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.int_buffer_little : buffers.int_buffer_big;
/* 339:    */  }
/* 340:    */  
/* 341:    */  private static IntBuffer doWrap(IntBuffer buffer) {
/* 342:342 */    IntBuffer direct_buffer = lookupBuffer(buffer);
/* 343:343 */    direct_buffer.clear();
/* 344:344 */    int saved_position = buffer.position();
/* 345:345 */    direct_buffer.put(buffer);
/* 346:346 */    buffer.position(saved_position);
/* 347:347 */    direct_buffer.flip();
/* 348:348 */    return direct_buffer;
/* 349:    */  }
/* 350:    */  
/* 351:    */  private static LongBuffer lookupBuffer(LongBuffer buffer) {
/* 352:352 */    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 8);
/* 353:353 */    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.long_buffer_little : buffers.long_buffer_big;
/* 354:    */  }
/* 355:    */  
/* 356:    */  private static LongBuffer doWrap(LongBuffer buffer) {
/* 357:357 */    LongBuffer direct_buffer = lookupBuffer(buffer);
/* 358:358 */    direct_buffer.clear();
/* 359:359 */    int saved_position = buffer.position();
/* 360:360 */    direct_buffer.put(buffer);
/* 361:361 */    buffer.position(saved_position);
/* 362:362 */    direct_buffer.flip();
/* 363:363 */    return direct_buffer;
/* 364:    */  }
/* 365:    */  
/* 366:    */  private static DoubleBuffer lookupBuffer(DoubleBuffer buffer) {
/* 367:367 */    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 8);
/* 368:368 */    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.double_buffer_little : buffers.double_buffer_big;
/* 369:    */  }
/* 370:    */  
/* 371:    */  private static DoubleBuffer doWrap(DoubleBuffer buffer) {
/* 372:372 */    DoubleBuffer direct_buffer = lookupBuffer(buffer);
/* 373:373 */    direct_buffer.clear();
/* 374:374 */    int saved_position = buffer.position();
/* 375:375 */    direct_buffer.put(buffer);
/* 376:376 */    buffer.position(saved_position);
/* 377:377 */    direct_buffer.flip();
/* 378:378 */    return direct_buffer;
/* 379:    */  }
/* 380:    */  
/* 381:    */  private static final class CachedBuffers {
/* 382:    */    private final ByteBuffer byte_buffer;
/* 383:    */    private final ShortBuffer short_buffer_big;
/* 384:    */    private final IntBuffer int_buffer_big;
/* 385:    */    private final FloatBuffer float_buffer_big;
/* 386:    */    private final LongBuffer long_buffer_big;
/* 387:    */    private final DoubleBuffer double_buffer_big;
/* 388:    */    private final ShortBuffer short_buffer_little;
/* 389:    */    private final IntBuffer int_buffer_little;
/* 390:    */    private final FloatBuffer float_buffer_little;
/* 391:    */    private final LongBuffer long_buffer_little;
/* 392:    */    private final DoubleBuffer double_buffer_little;
/* 393:    */    
/* 394:    */    private CachedBuffers(int size) {
/* 395:395 */      this.byte_buffer = ByteBuffer.allocateDirect(size);
/* 396:396 */      this.short_buffer_big = this.byte_buffer.asShortBuffer();
/* 397:397 */      this.int_buffer_big = this.byte_buffer.asIntBuffer();
/* 398:398 */      this.float_buffer_big = this.byte_buffer.asFloatBuffer();
/* 399:399 */      this.long_buffer_big = this.byte_buffer.asLongBuffer();
/* 400:400 */      this.double_buffer_big = this.byte_buffer.asDoubleBuffer();
/* 401:401 */      this.byte_buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 402:402 */      this.short_buffer_little = this.byte_buffer.asShortBuffer();
/* 403:403 */      this.int_buffer_little = this.byte_buffer.asIntBuffer();
/* 404:404 */      this.float_buffer_little = this.byte_buffer.asFloatBuffer();
/* 405:405 */      this.long_buffer_little = this.byte_buffer.asLongBuffer();
/* 406:406 */      this.double_buffer_little = this.byte_buffer.asDoubleBuffer();
/* 407:    */    }
/* 408:    */  }
/* 409:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.NondirectBufferWrapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */