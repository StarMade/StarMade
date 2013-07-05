/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.Buffer;
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ 
/*     */ public class PointerBuffer
/*     */   implements Comparable
/*     */ {
/*     */   private static final boolean is64Bit;
/*     */   protected final ByteBuffer pointers;
/*     */   protected final Buffer view;
/*     */   protected final IntBuffer view32;
/*     */   protected final LongBuffer view64;
/*     */ 
/*     */   public PointerBuffer(int capacity)
/*     */   {
/*  72 */     this(BufferUtils.createByteBuffer(capacity * getPointerSize()));
/*     */   }
/*     */ 
/*     */   public PointerBuffer(ByteBuffer source)
/*     */   {
/*  83 */     if (LWJGLUtil.CHECKS) {
/*  84 */       checkSource(source);
/*     */     }
/*  86 */     this.pointers = source.slice().order(source.order());
/*     */ 
/*  88 */     if (is64Bit) {
/*  89 */       this.view32 = null;
/*  90 */       this.view = (this.view64 = this.pointers.asLongBuffer());
/*     */     } else {
/*  92 */       this.view = (this.view32 = this.pointers.asIntBuffer());
/*  93 */       this.view64 = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void checkSource(ByteBuffer source) {
/*  98 */     if (!source.isDirect()) {
/*  99 */       throw new IllegalArgumentException("The source buffer is not direct.");
/*     */     }
/* 101 */     int alignment = is64Bit ? 8 : 4;
/* 102 */     if (((MemoryUtil.getAddress0(source) + source.position()) % alignment != 0L) || (source.remaining() % alignment != 0))
/* 103 */       throw new IllegalArgumentException("The source buffer is not aligned to " + alignment + " bytes.");
/*     */   }
/*     */ 
/*     */   public ByteBuffer getBuffer()
/*     */   {
/* 112 */     return this.pointers;
/*     */   }
/*     */ 
/*     */   public static boolean is64Bit()
/*     */   {
/* 117 */     return is64Bit;
/*     */   }
/*     */ 
/*     */   public static int getPointerSize()
/*     */   {
/* 126 */     return is64Bit ? 8 : 4;
/*     */   }
/*     */ 
/*     */   public final int capacity()
/*     */   {
/* 135 */     return this.view.capacity();
/*     */   }
/*     */ 
/*     */   public final int position()
/*     */   {
/* 144 */     return this.view.position();
/*     */   }
/*     */ 
/*     */   public final int positionByte()
/*     */   {
/* 153 */     return position() * getPointerSize();
/*     */   }
/*     */ 
/*     */   public final PointerBuffer position(int newPosition)
/*     */   {
/* 168 */     this.view.position(newPosition);
/* 169 */     return this;
/*     */   }
/*     */ 
/*     */   public final int limit()
/*     */   {
/* 178 */     return this.view.limit();
/*     */   }
/*     */ 
/*     */   public final PointerBuffer limit(int newLimit)
/*     */   {
/* 194 */     this.view.limit(newLimit);
/* 195 */     return this;
/*     */   }
/*     */ 
/*     */   public final PointerBuffer mark()
/*     */   {
/* 204 */     this.view.mark();
/* 205 */     return this;
/*     */   }
/*     */ 
/*     */   public final PointerBuffer reset()
/*     */   {
/* 219 */     this.view.reset();
/* 220 */     return this;
/*     */   }
/*     */ 
/*     */   public final PointerBuffer clear()
/*     */   {
/* 241 */     this.view.clear();
/* 242 */     return this;
/*     */   }
/*     */ 
/*     */   public final PointerBuffer flip()
/*     */   {
/* 267 */     this.view.flip();
/* 268 */     return this;
/*     */   }
/*     */ 
/*     */   public final PointerBuffer rewind()
/*     */   {
/* 287 */     this.view.rewind();
/* 288 */     return this;
/*     */   }
/*     */ 
/*     */   public final int remaining()
/*     */   {
/* 298 */     return this.view.remaining();
/*     */   }
/*     */ 
/*     */   public final int remainingByte()
/*     */   {
/* 308 */     return remaining() * getPointerSize();
/*     */   }
/*     */ 
/*     */   public final boolean hasRemaining()
/*     */   {
/* 319 */     return this.view.hasRemaining();
/*     */   }
/*     */ 
/*     */   public static PointerBuffer allocateDirect(int capacity)
/*     */   {
/* 335 */     return new PointerBuffer(capacity);
/*     */   }
/*     */ 
/*     */   protected PointerBuffer newInstance(ByteBuffer source)
/*     */   {
/* 347 */     return new PointerBuffer(source);
/*     */   }
/*     */ 
/*     */   public PointerBuffer slice()
/*     */   {
/* 368 */     int pointerSize = getPointerSize();
/*     */ 
/* 370 */     this.pointers.position(this.view.position() * pointerSize);
/* 371 */     this.pointers.limit(this.view.limit() * pointerSize);
/*     */     try
/*     */     {
/* 375 */       return newInstance(this.pointers);
/*     */     } finally {
/* 377 */       this.pointers.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   public PointerBuffer duplicate()
/*     */   {
/* 397 */     PointerBuffer buffer = newInstance(this.pointers);
/*     */ 
/* 399 */     buffer.position(this.view.position());
/* 400 */     buffer.limit(this.view.limit());
/*     */ 
/* 402 */     return buffer;
/*     */   }
/*     */ 
/*     */   public PointerBuffer asReadOnlyBuffer()
/*     */   {
/* 424 */     PointerBuffer buffer = new PointerBufferR(this.pointers);
/*     */ 
/* 426 */     buffer.position(this.view.position());
/* 427 */     buffer.limit(this.view.limit());
/*     */ 
/* 429 */     return buffer;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly() {
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */   public long get()
/*     */   {
/* 445 */     if (is64Bit) {
/* 446 */       return this.view64.get();
/*     */     }
/* 448 */     return this.view32.get() & 0xFFFFFFFF;
/*     */   }
/*     */ 
/*     */   public PointerBuffer put(long l)
/*     */   {
/* 465 */     if (is64Bit)
/* 466 */       this.view64.put(l);
/*     */     else
/* 468 */       this.view32.put((int)l);
/* 469 */     return this;
/*     */   }
/*     */ 
/*     */   public PointerBuffer put(PointerWrapper pointer)
/*     */   {
/* 478 */     return put(pointer.getPointer());
/*     */   }
/*     */ 
/*     */   public static void put(ByteBuffer target, long l)
/*     */   {
/* 488 */     if (is64Bit)
/* 489 */       target.putLong(l);
/*     */     else
/* 491 */       target.putInt((int)l);
/*     */   }
/*     */ 
/*     */   public long get(int index)
/*     */   {
/* 506 */     if (is64Bit) {
/* 507 */       return this.view64.get(index);
/*     */     }
/* 509 */     return this.view32.get(index) & 0xFFFFFFFF;
/*     */   }
/*     */ 
/*     */   public PointerBuffer put(int index, long l)
/*     */   {
/* 528 */     if (is64Bit)
/* 529 */       this.view64.put(index, l);
/*     */     else
/* 531 */       this.view32.put(index, (int)l);
/* 532 */     return this;
/*     */   }
/*     */ 
/*     */   public PointerBuffer put(int index, PointerWrapper pointer)
/*     */   {
/* 541 */     return put(index, pointer.getPointer());
/*     */   }
/*     */ 
/*     */   public static void put(ByteBuffer target, int index, long l)
/*     */   {
/* 552 */     if (is64Bit)
/* 553 */       target.putLong(index, l);
/*     */     else
/* 555 */       target.putInt(index, (int)l);
/*     */   }
/*     */ 
/*     */   public PointerBuffer get(long[] dst, int offset, int length)
/*     */   {
/* 602 */     if (is64Bit) {
/* 603 */       this.view64.get(dst, offset, length);
/*     */     } else {
/* 605 */       checkBounds(offset, length, dst.length);
/* 606 */       if (length > this.view32.remaining())
/* 607 */         throw new BufferUnderflowException();
/* 608 */       int end = offset + length;
/* 609 */       for (int i = offset; i < end; i++) {
/* 610 */         dst[i] = (this.view32.get() & 0xFFFFFFFF);
/*     */       }
/*     */     }
/* 613 */     return this;
/*     */   }
/*     */ 
/*     */   public PointerBuffer get(long[] dst)
/*     */   {
/* 632 */     return get(dst, 0, dst.length);
/*     */   }
/*     */ 
/*     */   public PointerBuffer put(PointerBuffer src)
/*     */   {
/* 671 */     if (is64Bit)
/* 672 */       this.view64.put(src.view64);
/*     */     else
/* 674 */       this.view32.put(src.view32);
/* 675 */     return this;
/*     */   }
/*     */ 
/*     */   public PointerBuffer put(long[] src, int offset, int length)
/*     */   {
/* 719 */     if (is64Bit) {
/* 720 */       this.view64.put(src, offset, length);
/*     */     } else {
/* 722 */       checkBounds(offset, length, src.length);
/* 723 */       if (length > this.view32.remaining())
/* 724 */         throw new BufferOverflowException();
/* 725 */       int end = offset + length;
/* 726 */       for (int i = offset; i < end; i++) {
/* 727 */         this.view32.put((int)src[i]);
/*     */       }
/*     */     }
/* 730 */     return this;
/*     */   }
/*     */ 
/*     */   public final PointerBuffer put(long[] src)
/*     */   {
/* 750 */     return put(src, 0, src.length);
/*     */   }
/*     */ 
/*     */   public PointerBuffer compact()
/*     */   {
/* 776 */     if (is64Bit)
/* 777 */       this.view64.compact();
/*     */     else
/* 779 */       this.view32.compact();
/* 780 */     return this;
/*     */   }
/*     */ 
/*     */   public ByteOrder order()
/*     */   {
/* 796 */     if (is64Bit) {
/* 797 */       return this.view64.order();
/*     */     }
/* 799 */     return this.view32.order();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 808 */     StringBuilder sb = new StringBuilder(48);
/* 809 */     sb.append(getClass().getName());
/* 810 */     sb.append("[pos=");
/* 811 */     sb.append(position());
/* 812 */     sb.append(" lim=");
/* 813 */     sb.append(limit());
/* 814 */     sb.append(" cap=");
/* 815 */     sb.append(capacity());
/* 816 */     sb.append("]");
/* 817 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 834 */     int h = 1;
/* 835 */     int p = position();
/* 836 */     for (int i = limit() - 1; i >= p; i--)
/* 837 */       h = 31 * h + (int)get(i);
/* 838 */     return h;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object ob)
/*     */   {
/* 867 */     if (!(ob instanceof PointerBuffer))
/* 868 */       return false;
/* 869 */     PointerBuffer that = (PointerBuffer)ob;
/* 870 */     if (remaining() != that.remaining())
/* 871 */       return false;
/* 872 */     int p = position();
/* 873 */     int i = limit() - 1; for (int j = that.limit() - 1; i >= p; j--) {
/* 874 */       long v1 = get(i);
/* 875 */       long v2 = that.get(j);
/* 876 */       if (v1 != v2)
/* 877 */         return false;
/* 873 */       i--;
/*     */     }
/*     */ 
/* 880 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(Object o)
/*     */   {
/* 896 */     PointerBuffer that = (PointerBuffer)o;
/* 897 */     int n = position() + Math.min(remaining(), that.remaining());
/* 898 */     int i = position(); for (int j = that.position(); i < n; j++) {
/* 899 */       long v1 = get(i);
/* 900 */       long v2 = that.get(j);
/* 901 */       if (v1 != v2)
/*     */       {
/* 903 */         if (v1 < v2)
/* 904 */           return -1;
/* 905 */         return 1;
/*     */       }
/* 898 */       i++;
/*     */     }
/*     */ 
/* 907 */     return remaining() - that.remaining();
/*     */   }
/*     */ 
/*     */   private static void checkBounds(int off, int len, int size) {
/* 911 */     if ((off | len | off + len | size - (off + len)) < 0)
/* 912 */       throw new IndexOutOfBoundsException();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  49 */     boolean is64 = false;
/*     */     try {
/*  51 */       Method m = Class.forName("org.lwjgl.Sys").getDeclaredMethod("is64Bit", (Class[])null);
/*  52 */       is64 = ((Boolean)m.invoke(null, (Object[])null)).booleanValue();
/*     */     } catch (Throwable t) {
/*     */     }
/*     */     finally {
/*  56 */       is64Bit = is64;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class PointerBufferR extends PointerBuffer
/*     */   {
/*     */     PointerBufferR(ByteBuffer source)
/*     */     {
/* 923 */       super();
/*     */     }
/*     */ 
/*     */     public boolean isReadOnly() {
/* 927 */       return true;
/*     */     }
/*     */ 
/*     */     protected PointerBuffer newInstance(ByteBuffer source) {
/* 931 */       return new PointerBufferR(source);
/*     */     }
/*     */ 
/*     */     public PointerBuffer asReadOnlyBuffer() {
/* 935 */       return duplicate();
/*     */     }
/*     */ 
/*     */     public PointerBuffer put(long l) {
/* 939 */       throw new ReadOnlyBufferException();
/*     */     }
/*     */ 
/*     */     public PointerBuffer put(int index, long l) {
/* 943 */       throw new ReadOnlyBufferException();
/*     */     }
/*     */ 
/*     */     public PointerBuffer put(PointerBuffer src) {
/* 947 */       throw new ReadOnlyBufferException();
/*     */     }
/*     */ 
/*     */     public PointerBuffer put(long[] src, int offset, int length) {
/* 951 */       throw new ReadOnlyBufferException();
/*     */     }
/*     */ 
/*     */     public PointerBuffer compact() {
/* 955 */       throw new ReadOnlyBufferException();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.PointerBuffer
 * JD-Core Version:    0.6.2
 */