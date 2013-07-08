/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.nio.Buffer;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.nio.CharBuffer;
/*   7:    */import java.nio.DoubleBuffer;
/*   8:    */import java.nio.FloatBuffer;
/*   9:    */import java.nio.IntBuffer;
/*  10:    */import java.nio.LongBuffer;
/*  11:    */import java.nio.ShortBuffer;
/*  12:    */import java.nio.charset.CharacterCodingException;
/*  13:    */import java.nio.charset.Charset;
/*  14:    */import java.nio.charset.CharsetDecoder;
/*  15:    */import java.nio.charset.CharsetEncoder;
/*  16:    */import java.nio.charset.CoderResult;
/*  17:    */
/*  50:    */public final class MemoryUtil
/*  51:    */{
/*  52: 52 */  private static final Charset ascii = Charset.forName("ISO-8859-1");
/*  53: 53 */  private static final Charset utf8 = Charset.forName("UTF-8");
/*  54: 54 */  private static final Charset utf16 = Charset.forName("UTF-16LE");
/*  55:    */  
/*  57:    */  static
/*  58:    */  {
/*  59:    */    Accessor util;
/*  60:    */    
/*  61:    */    try
/*  62:    */    {
/*  63: 63 */      util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorUnsafe");
/*  64:    */    }
/*  65:    */    catch (Exception e0) {
/*  66:    */      try {
/*  67: 67 */        util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorReflectFast");
/*  68:    */      }
/*  69:    */      catch (Exception e1) {
/*  70:    */        try {
/*  71: 71 */          util = new AccessorReflect();
/*  72:    */        } catch (Exception e2) {
/*  73: 73 */          LWJGLUtil.log("Unsupported JVM detected, this will likely result in low performance. Please inform LWJGL developers.");
/*  74: 74 */          util = new AccessorJNI(null);
/*  75:    */        }
/*  76:    */      }
/*  77:    */    }
/*  78:    */    
/*  79: 79 */    LWJGLUtil.log("MemoryUtil Accessor: " + util.getClass().getSimpleName()); }
/*  80: 80 */  private static final Accessor memUtil = util;
/*  81:    */  
/* 113:113 */  public static long getAddress0(Buffer buffer) { return memUtil.getAddress(buffer); }
/* 114:    */  
/* 115:115 */  public static long getAddress0Safe(Buffer buffer) { return buffer == null ? 0L : memUtil.getAddress(buffer); }
/* 116:    */  
/* 117:117 */  public static long getAddress0(PointerBuffer buffer) { return memUtil.getAddress(buffer.getBuffer()); }
/* 118:    */  
/* 119:119 */  public static long getAddress0Safe(PointerBuffer buffer) { return buffer == null ? 0L : memUtil.getAddress(buffer.getBuffer()); }
/* 120:    */  
/* 123:123 */  public static long getAddress(ByteBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 124:    */  
/* 125:125 */  public static long getAddress(ByteBuffer buffer, int position) { return getAddress0(buffer) + position; }
/* 126:    */  
/* 127:127 */  public static long getAddress(ShortBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 128:    */  
/* 129:129 */  public static long getAddress(ShortBuffer buffer, int position) { return getAddress0(buffer) + (position << 1); }
/* 130:    */  
/* 131:131 */  public static long getAddress(CharBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 132:    */  
/* 133:133 */  public static long getAddress(CharBuffer buffer, int position) { return getAddress0(buffer) + (position << 1); }
/* 134:    */  
/* 135:135 */  public static long getAddress(IntBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 136:    */  
/* 137:137 */  public static long getAddress(IntBuffer buffer, int position) { return getAddress0(buffer) + (position << 2); }
/* 138:    */  
/* 139:139 */  public static long getAddress(FloatBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 140:    */  
/* 141:141 */  public static long getAddress(FloatBuffer buffer, int position) { return getAddress0(buffer) + (position << 2); }
/* 142:    */  
/* 143:143 */  public static long getAddress(LongBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 144:    */  
/* 145:145 */  public static long getAddress(LongBuffer buffer, int position) { return getAddress0(buffer) + (position << 3); }
/* 146:    */  
/* 147:147 */  public static long getAddress(DoubleBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 148:    */  
/* 149:149 */  public static long getAddress(DoubleBuffer buffer, int position) { return getAddress0(buffer) + (position << 3); }
/* 150:    */  
/* 151:151 */  public static long getAddress(PointerBuffer buffer) { return getAddress(buffer, buffer.position()); }
/* 152:    */  
/* 153:153 */  public static long getAddress(PointerBuffer buffer, int position) { return getAddress0(buffer) + position * PointerBuffer.getPointerSize(); }
/* 154:    */  
/* 157:157 */  public static long getAddressSafe(ByteBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 158:    */  
/* 159:159 */  public static long getAddressSafe(ByteBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 160:    */  
/* 161:161 */  public static long getAddressSafe(ShortBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 162:    */  
/* 163:163 */  public static long getAddressSafe(ShortBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 164:    */  
/* 165:165 */  public static long getAddressSafe(CharBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 166:    */  
/* 167:167 */  public static long getAddressSafe(CharBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 168:    */  
/* 169:169 */  public static long getAddressSafe(IntBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 170:    */  
/* 171:171 */  public static long getAddressSafe(IntBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 172:    */  
/* 173:173 */  public static long getAddressSafe(FloatBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 174:    */  
/* 175:175 */  public static long getAddressSafe(FloatBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 176:    */  
/* 177:177 */  public static long getAddressSafe(LongBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 178:    */  
/* 179:179 */  public static long getAddressSafe(LongBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 180:    */  
/* 181:181 */  public static long getAddressSafe(DoubleBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 182:    */  
/* 183:183 */  public static long getAddressSafe(DoubleBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 184:    */  
/* 185:185 */  public static long getAddressSafe(PointerBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }
/* 186:    */  
/* 187:187 */  public static long getAddressSafe(PointerBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/* 188:    */  
/* 200:    */  public static ByteBuffer encodeASCII(CharSequence text)
/* 201:    */  {
/* 202:202 */    return encode(text, ascii);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static ByteBuffer encodeUTF8(CharSequence text)
/* 215:    */  {
/* 216:216 */    return encode(text, utf8);
/* 217:    */  }
/* 218:    */  
/* 226:    */  public static ByteBuffer encodeUTF16(CharSequence text)
/* 227:    */  {
/* 228:228 */    return encode(text, utf16);
/* 229:    */  }
/* 230:    */  
/* 238:    */  private static ByteBuffer encode(CharSequence text, Charset charset)
/* 239:    */  {
/* 240:240 */    if (text == null) {
/* 241:241 */      return null;
/* 242:    */    }
/* 243:243 */    return encode(CharBuffer.wrap(new CharSequenceNT(text)), charset);
/* 244:    */  }
/* 245:    */  
/* 251:    */  private static ByteBuffer encode(CharBuffer in, Charset charset)
/* 252:    */  {
/* 253:253 */    CharsetEncoder encoder = charset.newEncoder();
/* 254:    */    
/* 255:255 */    int n = (int)(in.remaining() * encoder.averageBytesPerChar());
/* 256:256 */    ByteBuffer out = BufferUtils.createByteBuffer(n);
/* 257:    */    
/* 258:258 */    if ((n == 0) && (in.remaining() == 0)) {
/* 259:259 */      return out;
/* 260:    */    }
/* 261:261 */    encoder.reset();
/* 262:    */    for (;;) {
/* 263:263 */      CoderResult cr = in.hasRemaining() ? encoder.encode(in, out, true) : CoderResult.UNDERFLOW;
/* 264:264 */      if (cr.isUnderflow()) {
/* 265:265 */        cr = encoder.flush(out);
/* 266:    */      }
/* 267:267 */      if (cr.isUnderflow()) {
/* 268:    */        break;
/* 269:    */      }
/* 270:270 */      if (cr.isOverflow()) {
/* 271:271 */        n = 2 * n + 1;
/* 272:272 */        ByteBuffer o = BufferUtils.createByteBuffer(n);
/* 273:273 */        out.flip();
/* 274:274 */        o.put(out);
/* 275:275 */        out = o;
/* 276:    */      }
/* 277:    */      else
/* 278:    */      {
/* 279:    */        try {
/* 280:280 */          cr.throwException();
/* 281:    */        } catch (CharacterCodingException e) {
/* 282:282 */          throw new RuntimeException(e);
/* 283:    */        }
/* 284:    */      } }
/* 285:285 */    out.flip();
/* 286:286 */    return out;
/* 287:    */  }
/* 288:    */  
/* 289:    */  public static String decodeASCII(ByteBuffer buffer) {
/* 290:290 */    return decode(buffer, ascii);
/* 291:    */  }
/* 292:    */  
/* 293:    */  public static String decodeUTF8(ByteBuffer buffer) {
/* 294:294 */    return decode(buffer, utf8);
/* 295:    */  }
/* 296:    */  
/* 297:    */  public static String decodeUTF16(ByteBuffer buffer) {
/* 298:298 */    return decode(buffer, utf16);
/* 299:    */  }
/* 300:    */  
/* 301:    */  private static String decode(ByteBuffer buffer, Charset charset) {
/* 302:302 */    if (buffer == null) {
/* 303:303 */      return null;
/* 304:    */    }
/* 305:305 */    return decodeImpl(buffer, charset);
/* 306:    */  }
/* 307:    */  
/* 308:    */  private static String decodeImpl(ByteBuffer in, Charset charset) {
/* 309:309 */    CharsetDecoder decoder = charset.newDecoder();
/* 310:    */    
/* 311:311 */    int n = (int)(in.remaining() * decoder.averageCharsPerByte());
/* 312:312 */    CharBuffer out = BufferUtils.createCharBuffer(n);
/* 313:    */    
/* 314:314 */    if ((n == 0) && (in.remaining() == 0)) {
/* 315:315 */      return "";
/* 316:    */    }
/* 317:317 */    decoder.reset();
/* 318:    */    for (;;) {
/* 319:319 */      CoderResult cr = in.hasRemaining() ? decoder.decode(in, out, true) : CoderResult.UNDERFLOW;
/* 320:320 */      if (cr.isUnderflow()) {
/* 321:321 */        cr = decoder.flush(out);
/* 322:    */      }
/* 323:323 */      if (cr.isUnderflow())
/* 324:    */        break;
/* 325:325 */      if (cr.isOverflow()) {
/* 326:326 */        n = 2 * n + 1;
/* 327:327 */        CharBuffer o = BufferUtils.createCharBuffer(n);
/* 328:328 */        out.flip();
/* 329:329 */        o.put(out);
/* 330:330 */        out = o;
/* 331:    */      }
/* 332:    */      else {
/* 333:    */        try {
/* 334:334 */          cr.throwException();
/* 335:    */        } catch (CharacterCodingException e) {
/* 336:336 */          throw new RuntimeException(e);
/* 337:    */        }
/* 338:    */      } }
/* 339:339 */    out.flip();
/* 340:340 */    return out.toString();
/* 341:    */  }
/* 342:    */  
/* 343:    */  private static class CharSequenceNT implements CharSequence
/* 344:    */  {
/* 345:    */    final CharSequence source;
/* 346:    */    
/* 347:    */    CharSequenceNT(CharSequence source)
/* 348:    */    {
/* 349:349 */      this.source = source;
/* 350:    */    }
/* 351:    */    
/* 352:    */    public int length() {
/* 353:353 */      return this.source.length() + 1;
/* 354:    */    }
/* 355:    */    
/* 356:    */    public char charAt(int index)
/* 357:    */    {
/* 358:358 */      return index == this.source.length() ? '\000' : this.source.charAt(index);
/* 359:    */    }
/* 360:    */    
/* 361:    */    public CharSequence subSequence(int start, int end)
/* 362:    */    {
/* 363:363 */      return new CharSequenceNT(this.source.subSequence(start, Math.min(end, this.source.length())));
/* 364:    */    }
/* 365:    */  }
/* 366:    */  
/* 374:375 */  private static Accessor loadAccessor(String className)
/* 375:375 */    throws Exception { return (Accessor)Class.forName(className).newInstance(); }
/* 376:    */  
/* 377:    */  static abstract interface Accessor {
/* 378:    */    public abstract long getAddress(Buffer paramBuffer);
/* 379:    */  }
/* 380:    */  
/* 381:    */  private static class AccessorJNI implements MemoryUtil.Accessor {
/* 382:382 */    public long getAddress(Buffer buffer) { return BufferUtils.getBufferAddress(buffer); }
/* 383:    */  }
/* 384:    */  
/* 385:    */  private static class AccessorReflect
/* 386:    */    implements MemoryUtil.Accessor
/* 387:    */  {
/* 388:    */    private final Field address;
/* 389:    */    
/* 390:    */    AccessorReflect()
/* 391:    */    {
/* 392:    */      try
/* 393:    */      {
/* 394:394 */        this.address = MemoryUtil.getAddressField();
/* 395:    */      } catch (NoSuchFieldException e) {
/* 396:396 */        throw new UnsupportedOperationException(e);
/* 397:    */      }
/* 398:398 */      this.address.setAccessible(true);
/* 399:    */    }
/* 400:    */    
/* 401:    */    public long getAddress(Buffer buffer) {
/* 402:    */      try {
/* 403:403 */        return this.address.getLong(buffer);
/* 404:    */      }
/* 405:    */      catch (IllegalAccessException e) {}
/* 406:406 */      return 0L;
/* 407:    */    }
/* 408:    */  }
/* 409:    */  
/* 410:    */  static Field getAddressField()
/* 411:    */    throws NoSuchFieldException
/* 412:    */  {
/* 413:413 */    return getDeclaredFieldRecursive(ByteBuffer.class, "address");
/* 414:    */  }
/* 415:    */  
/* 416:    */  private static Field getDeclaredFieldRecursive(Class<?> root, String fieldName) throws NoSuchFieldException {
/* 417:417 */    Class<?> type = root;
/* 418:    */    do
/* 419:    */    {
/* 420:    */      try {
/* 421:421 */        return type.getDeclaredField(fieldName);
/* 422:    */      } catch (NoSuchFieldException e) {
/* 423:423 */        type = type.getSuperclass();
/* 424:    */      }
/* 425:425 */    } while (type != null);
/* 426:    */    
/* 427:427 */    throw new NoSuchFieldException(fieldName + " does not exist in " + root.getSimpleName() + " or any of its superclasses.");
/* 428:    */  }
/* 429:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.MemoryUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */