/*     */ package org.lwjgl.util.mapped;
/*     */ 
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public abstract class MappedObject
/*     */ {
/*  55 */   static final boolean CHECKS = LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.Checks");
/*     */   public long baseAddress;
/*     */   public long viewAddress;
/*     */   ByteBuffer preventGC;
/*  79 */   public static int SIZEOF = -1;
/*     */   public int view;
/*     */ 
/*     */   protected final long getViewAddress(int view)
/*     */   {
/*  90 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final void setViewAddress(long address) {
/*  94 */     if (CHECKS)
/*  95 */       checkAddress(address);
/*  96 */     this.viewAddress = address;
/*     */   }
/*     */ 
/*     */   final void checkAddress(long address) {
/* 100 */     long base = MemoryUtil.getAddress0(this.preventGC);
/* 101 */     int offset = (int)(address - base);
/* 102 */     if ((address < base) || (this.preventGC.capacity() < offset + getSizeof()))
/* 103 */       throw new IndexOutOfBoundsException(Integer.toString(offset / getSizeof()));
/*     */   }
/*     */ 
/*     */   final void checkRange(int bytes) {
/* 107 */     if (bytes < 0) {
/* 108 */       throw new IllegalArgumentException();
/*     */     }
/* 110 */     if (this.preventGC.capacity() < this.viewAddress - MemoryUtil.getAddress0(this.preventGC) + bytes)
/* 111 */       throw new BufferOverflowException();
/*     */   }
/*     */ 
/*     */   public final int getAlign()
/*     */   {
/* 122 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final int getSizeof()
/*     */   {
/* 132 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final int capacity()
/*     */   {
/* 142 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public static <T extends MappedObject> T map(ByteBuffer bb)
/*     */   {
/* 157 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public static <T extends MappedObject> T map(long address, int capacity)
/*     */   {
/* 173 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public static <T extends MappedObject> T malloc(int elementCount)
/*     */   {
/* 188 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final <T extends MappedObject> T dup()
/*     */   {
/* 199 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final <T extends MappedObject> T slice()
/*     */   {
/* 208 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final void runViewConstructor()
/*     */   {
/* 217 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final void next()
/*     */   {
/* 224 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final <T extends MappedObject> void copyTo(T target)
/*     */   {
/* 234 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final <T extends MappedObject> void copyRange(T target, int instances)
/*     */   {
/* 245 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public static <T extends MappedObject> Iterable<T> foreach(T mapped)
/*     */   {
/* 257 */     return foreach(mapped, mapped.capacity());
/*     */   }
/*     */ 
/*     */   public static <T extends MappedObject> Iterable<T> foreach(T mapped, int elementCount)
/*     */   {
/* 269 */     return new MappedForeach(mapped, elementCount);
/*     */   }
/*     */ 
/*     */   public final <T extends MappedObject> T[] asArray()
/*     */   {
/* 275 */     throw new InternalError("type not registered");
/*     */   }
/*     */ 
/*     */   public final ByteBuffer backingByteBuffer()
/*     */   {
/* 284 */     return this.preventGC;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObject
 * JD-Core Version:    0.6.2
 */