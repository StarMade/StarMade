/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ final class APIUtil
/*     */ {
/*     */   private static final int INITIAL_BUFFER_SIZE = 256;
/*     */   private static final int INITIAL_LENGTHS_SIZE = 4;
/*     */   private static final int BUFFERS_SIZE = 32;
/*     */   private char[] array;
/*     */   private ByteBuffer buffer;
/*     */   private IntBuffer lengths;
/*     */   private final IntBuffer ints;
/*     */   private final LongBuffer longs;
/*     */   private final FloatBuffer floats;
/*     */   private final DoubleBuffer doubles;
/*     */ 
/*     */   APIUtil()
/*     */   {
/*  63 */     this.array = new char[256];
/*  64 */     this.buffer = BufferUtils.createByteBuffer(256);
/*  65 */     this.lengths = BufferUtils.createIntBuffer(4);
/*     */ 
/*  67 */     this.ints = BufferUtils.createIntBuffer(32);
/*  68 */     this.longs = BufferUtils.createLongBuffer(32);
/*     */ 
/*  70 */     this.floats = BufferUtils.createFloatBuffer(32);
/*  71 */     this.doubles = BufferUtils.createDoubleBuffer(32);
/*     */   }
/*     */ 
/*     */   private static char[] getArray(ContextCapabilities caps, int size) {
/*  75 */     char[] array = caps.util.array;
/*     */ 
/*  77 */     if (array.length < size) {
/*  78 */       int sizeNew = array.length << 1;
/*  79 */       while (sizeNew < size) {
/*  80 */         sizeNew <<= 1;
/*     */       }
/*  82 */       array = new char[size];
/*  83 */       caps.util.array = array;
/*     */     }
/*     */ 
/*  86 */     return array;
/*     */   }
/*     */ 
/*     */   static ByteBuffer getBufferByte(ContextCapabilities caps, int size) {
/*  90 */     ByteBuffer buffer = caps.util.buffer;
/*     */ 
/*  92 */     if (buffer.capacity() < size) {
/*  93 */       int sizeNew = buffer.capacity() << 1;
/*  94 */       while (sizeNew < size) {
/*  95 */         sizeNew <<= 1;
/*     */       }
/*  97 */       buffer = BufferUtils.createByteBuffer(size);
/*  98 */       caps.util.buffer = buffer;
/*     */     } else {
/* 100 */       buffer.clear();
/*     */     }
/* 102 */     return buffer;
/*     */   }
/*     */ 
/*     */   private static ByteBuffer getBufferByteOffset(ContextCapabilities caps, int size) {
/* 106 */     ByteBuffer buffer = caps.util.buffer;
/*     */ 
/* 108 */     if (buffer.capacity() < size) {
/* 109 */       int sizeNew = buffer.capacity() << 1;
/* 110 */       while (sizeNew < size) {
/* 111 */         sizeNew <<= 1;
/*     */       }
/* 113 */       ByteBuffer bufferNew = BufferUtils.createByteBuffer(size);
/* 114 */       bufferNew.put(buffer);
/* 115 */       caps.util.buffer = (buffer = bufferNew);
/*     */     } else {
/* 117 */       buffer.position(buffer.limit());
/* 118 */       buffer.limit(buffer.capacity());
/*     */     }
/*     */ 
/* 121 */     return buffer;
/*     */   }
/*     */   static IntBuffer getBufferInt(ContextCapabilities caps) {
/* 124 */     return caps.util.ints;
/*     */   }
/* 126 */   static LongBuffer getBufferLong(ContextCapabilities caps) { return caps.util.longs; } 
/*     */   static FloatBuffer getBufferFloat(ContextCapabilities caps) {
/* 128 */     return caps.util.floats;
/*     */   }
/* 130 */   static DoubleBuffer getBufferDouble(ContextCapabilities caps) { return caps.util.doubles; }
/*     */ 
/*     */   static IntBuffer getLengths(ContextCapabilities caps) {
/* 133 */     return getLengths(caps, 1);
/*     */   }
/*     */ 
/*     */   static IntBuffer getLengths(ContextCapabilities caps, int size) {
/* 137 */     IntBuffer lengths = caps.util.lengths;
/*     */ 
/* 139 */     if (lengths.capacity() < size) {
/* 140 */       int sizeNew = lengths.capacity();
/* 141 */       while (sizeNew < size) {
/* 142 */         sizeNew <<= 1;
/*     */       }
/* 144 */       lengths = BufferUtils.createIntBuffer(size);
/* 145 */       caps.util.lengths = lengths;
/*     */     } else {
/* 147 */       lengths.clear();
/*     */     }
/* 149 */     return lengths;
/*     */   }
/*     */ 
/*     */   private static ByteBuffer encode(ByteBuffer buffer, CharSequence string)
/*     */   {
/* 159 */     for (int i = 0; i < string.length(); i++) {
/* 160 */       char c = string.charAt(i);
/* 161 */       if ((LWJGLUtil.DEBUG) && ('' <= c))
/* 162 */         buffer.put((byte)26);
/*     */       else {
/* 164 */         buffer.put((byte)c);
/*     */       }
/*     */     }
/* 167 */     return buffer;
/*     */   }
/*     */ 
/*     */   static String getString(ContextCapabilities caps, ByteBuffer buffer)
/*     */   {
/* 178 */     int length = buffer.remaining();
/* 179 */     char[] charArray = getArray(caps, length);
/*     */ 
/* 181 */     for (int i = buffer.position(); i < buffer.limit(); i++) {
/* 182 */       charArray[(i - buffer.position())] = ((char)buffer.get(i));
/*     */     }
/* 184 */     return new String(charArray, 0, length);
/*     */   }
/*     */ 
/*     */   static long getBuffer(ContextCapabilities caps, CharSequence string)
/*     */   {
/* 195 */     ByteBuffer buffer = encode(getBufferByte(caps, string.length()), string);
/* 196 */     buffer.flip();
/* 197 */     return MemoryUtil.getAddress0(buffer);
/*     */   }
/*     */ 
/*     */   static long getBuffer(ContextCapabilities caps, CharSequence string, int offset)
/*     */   {
/* 208 */     ByteBuffer buffer = encode(getBufferByteOffset(caps, offset + string.length()), string);
/* 209 */     buffer.flip();
/* 210 */     return MemoryUtil.getAddress(buffer);
/*     */   }
/*     */ 
/*     */   static long getBufferNT(ContextCapabilities caps, CharSequence string)
/*     */   {
/* 221 */     ByteBuffer buffer = encode(getBufferByte(caps, string.length() + 1), string);
/* 222 */     buffer.put((byte)0);
/* 223 */     buffer.flip();
/* 224 */     return MemoryUtil.getAddress0(buffer);
/*     */   }
/*     */ 
/*     */   static int getTotalLength(CharSequence[] strings) {
/* 228 */     int length = 0;
/* 229 */     for (CharSequence string : strings) {
/* 230 */       length += string.length();
/*     */     }
/* 232 */     return length;
/*     */   }
/*     */ 
/*     */   static long getBuffer(ContextCapabilities caps, CharSequence[] strings)
/*     */   {
/* 243 */     ByteBuffer buffer = getBufferByte(caps, getTotalLength(strings));
/*     */ 
/* 245 */     for (CharSequence string : strings) {
/* 246 */       encode(buffer, string);
/*     */     }
/* 248 */     buffer.flip();
/* 249 */     return MemoryUtil.getAddress0(buffer);
/*     */   }
/*     */ 
/*     */   static long getBufferNT(ContextCapabilities caps, CharSequence[] strings)
/*     */   {
/* 260 */     ByteBuffer buffer = getBufferByte(caps, getTotalLength(strings) + strings.length);
/*     */ 
/* 262 */     for (CharSequence string : strings) {
/* 263 */       encode(buffer, string);
/* 264 */       buffer.put((byte)0);
/*     */     }
/*     */ 
/* 267 */     buffer.flip();
/* 268 */     return MemoryUtil.getAddress0(buffer);
/*     */   }
/*     */ 
/*     */   static long getLengths(ContextCapabilities caps, CharSequence[] strings)
/*     */   {
/* 279 */     IntBuffer buffer = getLengths(caps, strings.length);
/*     */ 
/* 281 */     for (CharSequence string : strings) {
/* 282 */       buffer.put(string.length());
/*     */     }
/* 284 */     buffer.flip();
/* 285 */     return MemoryUtil.getAddress0(buffer);
/*     */   }
/*     */ 
/*     */   static long getInt(ContextCapabilities caps, int value) {
/* 289 */     return MemoryUtil.getAddress0(getBufferInt(caps).put(0, value));
/*     */   }
/*     */ 
/*     */   static long getBufferByte0(ContextCapabilities caps) {
/* 293 */     return MemoryUtil.getAddress0(getBufferByte(caps, 0));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APIUtil
 * JD-Core Version:    0.6.2
 */