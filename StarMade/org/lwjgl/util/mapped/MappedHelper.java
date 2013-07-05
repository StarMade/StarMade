/*     */ package org.lwjgl.util.mapped;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import sun.misc.Unsafe;
/*     */ 
/*     */ public class MappedHelper
/*     */ {
/*     */   public static void setup(MappedObject mo, ByteBuffer buffer, int align, int sizeof)
/*     */   {
/*  51 */     if ((LWJGLUtil.CHECKS) && (mo.baseAddress != 0L)) {
/*  52 */       throw new IllegalStateException("this method should not be called by user-code");
/*     */     }
/*  54 */     if ((LWJGLUtil.CHECKS) && (!buffer.isDirect()))
/*  55 */       throw new IllegalArgumentException("bytebuffer must be direct");
/*  56 */     mo.preventGC = buffer;
/*     */ 
/*  58 */     if ((LWJGLUtil.CHECKS) && (align <= 0)) {
/*  59 */       throw new IllegalArgumentException("invalid alignment");
/*     */     }
/*  61 */     if ((LWJGLUtil.CHECKS) && ((sizeof <= 0) || (sizeof % align != 0))) {
/*  62 */       throw new IllegalStateException("sizeof not a multiple of alignment");
/*     */     }
/*  64 */     long addr = MemoryUtil.getAddress(buffer);
/*  65 */     if ((LWJGLUtil.CHECKS) && (addr % align != 0L)) {
/*  66 */       throw new IllegalStateException("buffer address not aligned on " + align + " bytes");
/*     */     }
/*  68 */     mo.baseAddress = (mo.viewAddress = addr);
/*     */   }
/*     */ 
/*     */   public static void checkAddress(long viewAddress, MappedObject mapped) {
/*  72 */     mapped.checkAddress(viewAddress);
/*     */   }
/*     */ 
/*     */   public static void put_views(MappedSet2 set, int view) {
/*  76 */     set.view(view);
/*     */   }
/*     */ 
/*     */   public static void put_views(MappedSet3 set, int view) {
/*  80 */     set.view(view);
/*     */   }
/*     */ 
/*     */   public static void put_views(MappedSet4 set, int view) {
/*  84 */     set.view(view);
/*     */   }
/*     */ 
/*     */   public static void put_view(MappedObject mapped, int view, int sizeof) {
/*  88 */     mapped.setViewAddress(mapped.baseAddress + view * sizeof);
/*     */   }
/*     */ 
/*     */   public static int get_view(MappedObject mapped, int sizeof) {
/*  92 */     return (int)(mapped.viewAddress - mapped.baseAddress) / sizeof;
/*     */   }
/*     */ 
/*     */   public static void put_view_shift(MappedObject mapped, int view, int sizeof_shift) {
/*  96 */     mapped.setViewAddress(mapped.baseAddress + (view << sizeof_shift));
/*     */   }
/*     */ 
/*     */   public static int get_view_shift(MappedObject mapped, int sizeof_shift) {
/* 100 */     return (int)(mapped.viewAddress - mapped.baseAddress) >> sizeof_shift;
/*     */   }
/*     */ 
/*     */   public static void put_view_next(MappedObject mapped, int sizeof) {
/* 104 */     mapped.setViewAddress(mapped.viewAddress + sizeof);
/*     */   }
/*     */ 
/*     */   public static MappedObject dup(MappedObject src, MappedObject dst) {
/* 108 */     dst.baseAddress = src.baseAddress;
/* 109 */     dst.viewAddress = src.viewAddress;
/* 110 */     dst.preventGC = src.preventGC;
/* 111 */     return dst;
/*     */   }
/*     */ 
/*     */   public static MappedObject slice(MappedObject src, MappedObject dst) {
/* 115 */     dst.baseAddress = src.viewAddress;
/* 116 */     dst.viewAddress = src.viewAddress;
/* 117 */     dst.preventGC = src.preventGC;
/* 118 */     return dst;
/*     */   }
/*     */ 
/*     */   public static void copy(MappedObject src, MappedObject dst, int bytes) {
/* 122 */     if (MappedObject.CHECKS) {
/* 123 */       src.checkRange(bytes);
/* 124 */       dst.checkRange(bytes);
/*     */     }
/*     */ 
/* 127 */     MappedObjectUnsafe.INSTANCE.copyMemory(src.viewAddress, dst.viewAddress, bytes);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer newBuffer(long address, int capacity) {
/* 131 */     return MappedObjectUnsafe.newBuffer(address, capacity);
/*     */   }
/*     */ 
/*     */   public static void bput(byte value, long addr)
/*     */   {
/* 139 */     MappedObjectUnsafe.INSTANCE.putByte(addr, value);
/*     */   }
/*     */ 
/*     */   public static void bput(MappedObject mapped, byte value, int fieldOffset) {
/* 143 */     MappedObjectUnsafe.INSTANCE.putByte(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static byte bget(long addr) {
/* 147 */     return MappedObjectUnsafe.INSTANCE.getByte(addr);
/*     */   }
/*     */ 
/*     */   public static byte bget(MappedObject mapped, int fieldOffset) {
/* 151 */     return MappedObjectUnsafe.INSTANCE.getByte(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void bvput(byte value, long addr) {
/* 155 */     MappedObjectUnsafe.INSTANCE.putByteVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void bvput(MappedObject mapped, byte value, int fieldOffset) {
/* 159 */     MappedObjectUnsafe.INSTANCE.putByteVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static byte bvget(long addr) {
/* 163 */     return MappedObjectUnsafe.INSTANCE.getByteVolatile(null, addr);
/*     */   }
/*     */ 
/*     */   public static byte bvget(MappedObject mapped, int fieldOffset) {
/* 167 */     return MappedObjectUnsafe.INSTANCE.getByteVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void sput(short value, long addr)
/*     */   {
/* 173 */     MappedObjectUnsafe.INSTANCE.putShort(addr, value);
/*     */   }
/*     */ 
/*     */   public static void sput(MappedObject mapped, short value, int fieldOffset) {
/* 177 */     MappedObjectUnsafe.INSTANCE.putShort(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static short sget(long addr) {
/* 181 */     return MappedObjectUnsafe.INSTANCE.getShort(addr);
/*     */   }
/*     */ 
/*     */   public static short sget(MappedObject mapped, int fieldOffset) {
/* 185 */     return MappedObjectUnsafe.INSTANCE.getShort(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void svput(short value, long addr) {
/* 189 */     MappedObjectUnsafe.INSTANCE.putShortVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void svput(MappedObject mapped, short value, int fieldOffset) {
/* 193 */     MappedObjectUnsafe.INSTANCE.putShortVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static short svget(long addr) {
/* 197 */     return MappedObjectUnsafe.INSTANCE.getShortVolatile(null, addr);
/*     */   }
/*     */ 
/*     */   public static short svget(MappedObject mapped, int fieldOffset) {
/* 201 */     return MappedObjectUnsafe.INSTANCE.getShortVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void cput(char value, long addr)
/*     */   {
/* 207 */     MappedObjectUnsafe.INSTANCE.putChar(addr, value);
/*     */   }
/*     */ 
/*     */   public static void cput(MappedObject mapped, char value, int fieldOffset) {
/* 211 */     MappedObjectUnsafe.INSTANCE.putChar(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static char cget(long addr) {
/* 215 */     return MappedObjectUnsafe.INSTANCE.getChar(addr);
/*     */   }
/*     */ 
/*     */   public static char cget(MappedObject mapped, int fieldOffset) {
/* 219 */     return MappedObjectUnsafe.INSTANCE.getChar(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void cvput(char value, long addr) {
/* 223 */     MappedObjectUnsafe.INSTANCE.putCharVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void cvput(MappedObject mapped, char value, int fieldOffset) {
/* 227 */     MappedObjectUnsafe.INSTANCE.putCharVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static char cvget(long addr) {
/* 231 */     return MappedObjectUnsafe.INSTANCE.getCharVolatile(null, addr);
/*     */   }
/*     */ 
/*     */   public static char cvget(MappedObject mapped, int fieldOffset) {
/* 235 */     return MappedObjectUnsafe.INSTANCE.getCharVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void iput(int value, long addr)
/*     */   {
/* 241 */     MappedObjectUnsafe.INSTANCE.putInt(addr, value);
/*     */   }
/*     */ 
/*     */   public static void iput(MappedObject mapped, int value, int fieldOffset) {
/* 245 */     MappedObjectUnsafe.INSTANCE.putInt(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static int iget(long address) {
/* 249 */     return MappedObjectUnsafe.INSTANCE.getInt(address);
/*     */   }
/*     */ 
/*     */   public static int iget(MappedObject mapped, int fieldOffset) {
/* 253 */     return MappedObjectUnsafe.INSTANCE.getInt(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void ivput(int value, long addr) {
/* 257 */     MappedObjectUnsafe.INSTANCE.putIntVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void ivput(MappedObject mapped, int value, int fieldOffset) {
/* 261 */     MappedObjectUnsafe.INSTANCE.putIntVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static int ivget(long address) {
/* 265 */     return MappedObjectUnsafe.INSTANCE.getIntVolatile(null, address);
/*     */   }
/*     */ 
/*     */   public static int ivget(MappedObject mapped, int fieldOffset) {
/* 269 */     return MappedObjectUnsafe.INSTANCE.getIntVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void fput(float value, long addr)
/*     */   {
/* 275 */     MappedObjectUnsafe.INSTANCE.putFloat(addr, value);
/*     */   }
/*     */ 
/*     */   public static void fput(MappedObject mapped, float value, int fieldOffset) {
/* 279 */     MappedObjectUnsafe.INSTANCE.putFloat(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static float fget(long addr) {
/* 283 */     return MappedObjectUnsafe.INSTANCE.getFloat(addr);
/*     */   }
/*     */ 
/*     */   public static float fget(MappedObject mapped, int fieldOffset) {
/* 287 */     return MappedObjectUnsafe.INSTANCE.getFloat(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void fvput(float value, long addr) {
/* 291 */     MappedObjectUnsafe.INSTANCE.putFloatVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void fvput(MappedObject mapped, float value, int fieldOffset) {
/* 295 */     MappedObjectUnsafe.INSTANCE.putFloatVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static float fvget(long addr) {
/* 299 */     return MappedObjectUnsafe.INSTANCE.getFloatVolatile(null, addr);
/*     */   }
/*     */ 
/*     */   public static float fvget(MappedObject mapped, int fieldOffset) {
/* 303 */     return MappedObjectUnsafe.INSTANCE.getFloatVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void jput(long value, long addr)
/*     */   {
/* 309 */     MappedObjectUnsafe.INSTANCE.putLong(addr, value);
/*     */   }
/*     */ 
/*     */   public static void jput(MappedObject mapped, long value, int fieldOffset) {
/* 313 */     MappedObjectUnsafe.INSTANCE.putLong(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static long jget(long addr) {
/* 317 */     return MappedObjectUnsafe.INSTANCE.getLong(addr);
/*     */   }
/*     */ 
/*     */   public static long jget(MappedObject mapped, int fieldOffset) {
/* 321 */     return MappedObjectUnsafe.INSTANCE.getLong(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void jvput(long value, long addr) {
/* 325 */     MappedObjectUnsafe.INSTANCE.putLongVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void jvput(MappedObject mapped, long value, int fieldOffset) {
/* 329 */     MappedObjectUnsafe.INSTANCE.putLongVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static long jvget(long addr) {
/* 333 */     return MappedObjectUnsafe.INSTANCE.getLongVolatile(null, addr);
/*     */   }
/*     */ 
/*     */   public static long jvget(MappedObject mapped, int fieldOffset) {
/* 337 */     return MappedObjectUnsafe.INSTANCE.getLongVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void aput(long value, long addr)
/*     */   {
/* 343 */     MappedObjectUnsafe.INSTANCE.putAddress(addr, value);
/*     */   }
/*     */ 
/*     */   public static void aput(MappedObject mapped, long value, int fieldOffset) {
/* 347 */     MappedObjectUnsafe.INSTANCE.putAddress(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static long aget(long addr) {
/* 351 */     return MappedObjectUnsafe.INSTANCE.getAddress(addr);
/*     */   }
/*     */ 
/*     */   public static long aget(MappedObject mapped, int fieldOffset) {
/* 355 */     return MappedObjectUnsafe.INSTANCE.getAddress(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void dput(double value, long addr)
/*     */   {
/* 361 */     MappedObjectUnsafe.INSTANCE.putDouble(addr, value);
/*     */   }
/*     */ 
/*     */   public static void dput(MappedObject mapped, double value, int fieldOffset) {
/* 365 */     MappedObjectUnsafe.INSTANCE.putDouble(mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static double dget(long addr) {
/* 369 */     return MappedObjectUnsafe.INSTANCE.getDouble(addr);
/*     */   }
/*     */ 
/*     */   public static double dget(MappedObject mapped, int fieldOffset) {
/* 373 */     return MappedObjectUnsafe.INSTANCE.getDouble(mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ 
/*     */   public static void dvput(double value, long addr) {
/* 377 */     MappedObjectUnsafe.INSTANCE.putDoubleVolatile(null, addr, value);
/*     */   }
/*     */ 
/*     */   public static void dvput(MappedObject mapped, double value, int fieldOffset) {
/* 381 */     MappedObjectUnsafe.INSTANCE.putDoubleVolatile(null, mapped.viewAddress + fieldOffset, value);
/*     */   }
/*     */ 
/*     */   public static double dvget(long addr) {
/* 385 */     return MappedObjectUnsafe.INSTANCE.getDoubleVolatile(null, addr);
/*     */   }
/*     */ 
/*     */   public static double dvget(MappedObject mapped, int fieldOffset) {
/* 389 */     return MappedObjectUnsafe.INSTANCE.getDoubleVolatile(null, mapped.viewAddress + fieldOffset);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedHelper
 * JD-Core Version:    0.6.2
 */