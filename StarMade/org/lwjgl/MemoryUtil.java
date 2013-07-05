/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ 
/*     */ public final class MemoryUtil
/*     */ {
/*  52 */   private static final Charset ascii = Charset.forName("ISO-8859-1");
/*  53 */   private static final Charset utf8 = Charset.forName("UTF-8");
/*  54 */   private static final Charset utf16 = Charset.forName("UTF-16LE");
/*     */ 
/*  80 */   private static final Accessor memUtil = util;
/*     */ 
/*     */   public static long getAddress0(Buffer buffer)
/*     */   {
/* 113 */     return memUtil.getAddress(buffer);
/*     */   }
/* 115 */   public static long getAddress0Safe(Buffer buffer) { return buffer == null ? 0L : memUtil.getAddress(buffer); } 
/*     */   public static long getAddress0(PointerBuffer buffer) {
/* 117 */     return memUtil.getAddress(buffer.getBuffer());
/*     */   }
/* 119 */   public static long getAddress0Safe(PointerBuffer buffer) { return buffer == null ? 0L : memUtil.getAddress(buffer.getBuffer()); }
/*     */ 
/*     */   public static long getAddress(ByteBuffer buffer)
/*     */   {
/* 123 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 125 */   public static long getAddress(ByteBuffer buffer, int position) { return getAddress0(buffer) + position; } 
/*     */   public static long getAddress(ShortBuffer buffer) {
/* 127 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 129 */   public static long getAddress(ShortBuffer buffer, int position) { return getAddress0(buffer) + (position << 1); } 
/*     */   public static long getAddress(CharBuffer buffer) {
/* 131 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 133 */   public static long getAddress(CharBuffer buffer, int position) { return getAddress0(buffer) + (position << 1); } 
/*     */   public static long getAddress(IntBuffer buffer) {
/* 135 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 137 */   public static long getAddress(IntBuffer buffer, int position) { return getAddress0(buffer) + (position << 2); } 
/*     */   public static long getAddress(FloatBuffer buffer) {
/* 139 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 141 */   public static long getAddress(FloatBuffer buffer, int position) { return getAddress0(buffer) + (position << 2); } 
/*     */   public static long getAddress(LongBuffer buffer) {
/* 143 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 145 */   public static long getAddress(LongBuffer buffer, int position) { return getAddress0(buffer) + (position << 3); } 
/*     */   public static long getAddress(DoubleBuffer buffer) {
/* 147 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 149 */   public static long getAddress(DoubleBuffer buffer, int position) { return getAddress0(buffer) + (position << 3); } 
/*     */   public static long getAddress(PointerBuffer buffer) {
/* 151 */     return getAddress(buffer, buffer.position());
/*     */   }
/* 153 */   public static long getAddress(PointerBuffer buffer, int position) { return getAddress0(buffer) + position * PointerBuffer.getPointerSize(); }
/*     */ 
/*     */   public static long getAddressSafe(ByteBuffer buffer)
/*     */   {
/* 157 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 159 */   public static long getAddressSafe(ByteBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(ShortBuffer buffer) {
/* 161 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 163 */   public static long getAddressSafe(ShortBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(CharBuffer buffer) {
/* 165 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 167 */   public static long getAddressSafe(CharBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(IntBuffer buffer) {
/* 169 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 171 */   public static long getAddressSafe(IntBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(FloatBuffer buffer) {
/* 173 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 175 */   public static long getAddressSafe(FloatBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(LongBuffer buffer) {
/* 177 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 179 */   public static long getAddressSafe(LongBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(DoubleBuffer buffer) {
/* 181 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 183 */   public static long getAddressSafe(DoubleBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); } 
/*     */   public static long getAddressSafe(PointerBuffer buffer) {
/* 185 */     return buffer == null ? 0L : getAddress(buffer);
/*     */   }
/* 187 */   public static long getAddressSafe(PointerBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }
/*     */ 
/*     */ 
/*     */   public static ByteBuffer encodeASCII(CharSequence text)
/*     */   {
/* 202 */     return encode(text, ascii);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer encodeUTF8(CharSequence text)
/*     */   {
/* 216 */     return encode(text, utf8);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer encodeUTF16(CharSequence text)
/*     */   {
/* 228 */     return encode(text, utf16);
/*     */   }
/*     */ 
/*     */   private static ByteBuffer encode(CharSequence text, Charset charset)
/*     */   {
/* 240 */     if (text == null) {
/* 241 */       return null;
/*     */     }
/* 243 */     return encode(CharBuffer.wrap(new CharSequenceNT(text)), charset);
/*     */   }
/*     */ 
/*     */   private static ByteBuffer encode(CharBuffer in, Charset charset)
/*     */   {
/* 253 */     CharsetEncoder encoder = charset.newEncoder();
/*     */ 
/* 255 */     int n = (int)(in.remaining() * encoder.averageBytesPerChar());
/* 256 */     ByteBuffer out = BufferUtils.createByteBuffer(n);
/*     */ 
/* 258 */     if ((n == 0) && (in.remaining() == 0)) {
/* 259 */       return out;
/*     */     }
/* 261 */     encoder.reset();
/*     */     while (true) {
/* 263 */       CoderResult cr = in.hasRemaining() ? encoder.encode(in, out, true) : CoderResult.UNDERFLOW;
/* 264 */       if (cr.isUnderflow()) {
/* 265 */         cr = encoder.flush(out);
/*     */       }
/* 267 */       if (cr.isUnderflow()) {
/*     */         break;
/*     */       }
/* 270 */       if (cr.isOverflow()) {
/* 271 */         n = 2 * n + 1;
/* 272 */         ByteBuffer o = BufferUtils.createByteBuffer(n);
/* 273 */         out.flip();
/* 274 */         o.put(out);
/* 275 */         out = o;
/*     */       }
/*     */       else
/*     */       {
/*     */         try {
/* 280 */           cr.throwException();
/*     */         } catch (CharacterCodingException e) {
/* 282 */           throw new RuntimeException(e);
/*     */         }
/*     */       }
/*     */     }
/* 285 */     out.flip();
/* 286 */     return out;
/*     */   }
/*     */ 
/*     */   public static String decodeASCII(ByteBuffer buffer) {
/* 290 */     return decode(buffer, ascii);
/*     */   }
/*     */ 
/*     */   public static String decodeUTF8(ByteBuffer buffer) {
/* 294 */     return decode(buffer, utf8);
/*     */   }
/*     */ 
/*     */   public static String decodeUTF16(ByteBuffer buffer) {
/* 298 */     return decode(buffer, utf16);
/*     */   }
/*     */ 
/*     */   private static String decode(ByteBuffer buffer, Charset charset) {
/* 302 */     if (buffer == null) {
/* 303 */       return null;
/*     */     }
/* 305 */     return decodeImpl(buffer, charset);
/*     */   }
/*     */ 
/*     */   private static String decodeImpl(ByteBuffer in, Charset charset) {
/* 309 */     CharsetDecoder decoder = charset.newDecoder();
/*     */ 
/* 311 */     int n = (int)(in.remaining() * decoder.averageCharsPerByte());
/* 312 */     CharBuffer out = BufferUtils.createCharBuffer(n);
/*     */ 
/* 314 */     if ((n == 0) && (in.remaining() == 0)) {
/* 315 */       return "";
/*     */     }
/* 317 */     decoder.reset();
/*     */     while (true) {
/* 319 */       CoderResult cr = in.hasRemaining() ? decoder.decode(in, out, true) : CoderResult.UNDERFLOW;
/* 320 */       if (cr.isUnderflow()) {
/* 321 */         cr = decoder.flush(out);
/*     */       }
/* 323 */       if (cr.isUnderflow())
/*     */         break;
/* 325 */       if (cr.isOverflow()) {
/* 326 */         n = 2 * n + 1;
/* 327 */         CharBuffer o = BufferUtils.createCharBuffer(n);
/* 328 */         out.flip();
/* 329 */         o.put(out);
/* 330 */         out = o;
/*     */       }
/*     */       else {
/*     */         try {
/* 334 */           cr.throwException();
/*     */         } catch (CharacterCodingException e) {
/* 336 */           throw new RuntimeException(e);
/*     */         }
/*     */       }
/*     */     }
/* 339 */     out.flip();
/* 340 */     return out.toString();
/*     */   }
/*     */ 
/*     */   private static Accessor loadAccessor(String className)
/*     */     throws Exception
/*     */   {
/* 375 */     return (Accessor)Class.forName(className).newInstance();
/*     */   }
/*     */ 
/*     */   static Field getAddressField()
/*     */     throws NoSuchFieldException
/*     */   {
/* 413 */     return getDeclaredFieldRecursive(ByteBuffer.class, "address");
/*     */   }
/*     */ 
/*     */   private static Field getDeclaredFieldRecursive(Class<?> root, String fieldName) throws NoSuchFieldException {
/* 417 */     Class type = root;
/*     */     do
/*     */       try
/*     */       {
/* 421 */         return type.getDeclaredField(fieldName);
/*     */       } catch (NoSuchFieldException e) {
/* 423 */         type = type.getSuperclass();
/*     */       }
/* 425 */     while (type != null);
/*     */ 
/* 427 */     throw new NoSuchFieldException(fieldName + " does not exist in " + root.getSimpleName() + " or any of its superclasses.");
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     Accessor util;
/*     */     try
/*     */     {
/*  63 */       util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorUnsafe");
/*     */     }
/*     */     catch (Exception e0) {
/*     */       try {
/*  67 */         util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorReflectFast");
/*     */       }
/*     */       catch (Exception e1) {
/*     */         try {
/*  71 */           util = new AccessorReflect();
/*     */         } catch (Exception e2) {
/*  73 */           LWJGLUtil.log("Unsupported JVM detected, this will likely result in low performance. Please inform LWJGL developers.");
/*  74 */           util = new AccessorJNI(null);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  79 */     LWJGLUtil.log("MemoryUtil Accessor: " + util.getClass().getSimpleName());
/*     */   }
/*     */ 
/*     */   private static class AccessorReflect
/*     */     implements MemoryUtil.Accessor
/*     */   {
/*     */     private final Field address;
/*     */ 
/*     */     AccessorReflect()
/*     */     {
/*     */       try
/*     */       {
/* 394 */         this.address = MemoryUtil.getAddressField();
/*     */       } catch (NoSuchFieldException e) {
/* 396 */         throw new UnsupportedOperationException(e);
/*     */       }
/* 398 */       this.address.setAccessible(true);
/*     */     }
/*     */ 
/*     */     public long getAddress(Buffer buffer) {
/*     */       try {
/* 403 */         return this.address.getLong(buffer);
/*     */       } catch (IllegalAccessException e) {
/*     */       }
/* 406 */       return 0L;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class AccessorJNI
/*     */     implements MemoryUtil.Accessor
/*     */   {
/*     */     public long getAddress(Buffer buffer)
/*     */     {
/* 382 */       return BufferUtils.getBufferAddress(buffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface Accessor
/*     */   {
/*     */     public abstract long getAddress(Buffer paramBuffer);
/*     */   }
/*     */ 
/*     */   private static class CharSequenceNT
/*     */     implements CharSequence
/*     */   {
/*     */     final CharSequence source;
/*     */ 
/*     */     CharSequenceNT(CharSequence source)
/*     */     {
/* 349 */       this.source = source;
/*     */     }
/*     */ 
/*     */     public int length() {
/* 353 */       return this.source.length() + 1;
/*     */     }
/*     */ 
/*     */     public char charAt(int index)
/*     */     {
/* 358 */       return index == this.source.length() ? '\000' : this.source.charAt(index);
/*     */     }
/*     */ 
/*     */     public CharSequence subSequence(int start, int end)
/*     */     {
/* 363 */       return new CharSequenceNT(this.source.subSequence(start, Math.min(end, this.source.length())));
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.MemoryUtil
 * JD-Core Version:    0.6.2
 */