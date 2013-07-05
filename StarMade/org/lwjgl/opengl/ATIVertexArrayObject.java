/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ATIVertexArrayObject
/*     */ {
/*     */   public static final int GL_STATIC_ATI = 34656;
/*     */   public static final int GL_DYNAMIC_ATI = 34657;
/*     */   public static final int GL_PRESERVE_ATI = 34658;
/*     */   public static final int GL_DISCARD_ATI = 34659;
/*     */   public static final int GL_OBJECT_BUFFER_SIZE_ATI = 34660;
/*     */   public static final int GL_OBJECT_BUFFER_USAGE_ATI = 34661;
/*     */   public static final int GL_ARRAY_OBJECT_BUFFER_ATI = 34662;
/*     */   public static final int GL_ARRAY_OBJECT_OFFSET_ATI = 34663;
/*     */ 
/*     */   public static int glNewObjectBufferATI(int pPointer_size, int usage)
/*     */   {
/*  22 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  23 */     long function_pointer = caps.glNewObjectBufferATI;
/*  24 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  25 */     int __result = nglNewObjectBufferATI(pPointer_size, 0L, usage, function_pointer);
/*  26 */     return __result;
/*     */   }
/*     */   public static int glNewObjectBufferATI(ByteBuffer pPointer, int usage) {
/*  29 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  30 */     long function_pointer = caps.glNewObjectBufferATI;
/*  31 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  32 */     BufferChecks.checkDirect(pPointer);
/*  33 */     int __result = nglNewObjectBufferATI(pPointer.remaining(), MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  34 */     return __result;
/*     */   }
/*     */   public static int glNewObjectBufferATI(DoubleBuffer pPointer, int usage) {
/*  37 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  38 */     long function_pointer = caps.glNewObjectBufferATI;
/*  39 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  40 */     BufferChecks.checkDirect(pPointer);
/*  41 */     int __result = nglNewObjectBufferATI(pPointer.remaining() << 3, MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  42 */     return __result;
/*     */   }
/*     */   public static int glNewObjectBufferATI(FloatBuffer pPointer, int usage) {
/*  45 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  46 */     long function_pointer = caps.glNewObjectBufferATI;
/*  47 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  48 */     BufferChecks.checkDirect(pPointer);
/*  49 */     int __result = nglNewObjectBufferATI(pPointer.remaining() << 2, MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  50 */     return __result;
/*     */   }
/*     */   public static int glNewObjectBufferATI(IntBuffer pPointer, int usage) {
/*  53 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  54 */     long function_pointer = caps.glNewObjectBufferATI;
/*  55 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  56 */     BufferChecks.checkDirect(pPointer);
/*  57 */     int __result = nglNewObjectBufferATI(pPointer.remaining() << 2, MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  58 */     return __result;
/*     */   }
/*     */   public static int glNewObjectBufferATI(ShortBuffer pPointer, int usage) {
/*  61 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  62 */     long function_pointer = caps.glNewObjectBufferATI;
/*  63 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  64 */     BufferChecks.checkDirect(pPointer);
/*  65 */     int __result = nglNewObjectBufferATI(pPointer.remaining() << 1, MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  66 */     return __result;
/*     */   }
/*     */   static native int nglNewObjectBufferATI(int paramInt1, long paramLong1, int paramInt2, long paramLong2);
/*     */ 
/*     */   public static boolean glIsObjectBufferATI(int buffer) {
/*  71 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  72 */     long function_pointer = caps.glIsObjectBufferATI;
/*  73 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  74 */     boolean __result = nglIsObjectBufferATI(buffer, function_pointer);
/*  75 */     return __result;
/*     */   }
/*     */   static native boolean nglIsObjectBufferATI(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glUpdateObjectBufferATI(int buffer, int offset, ByteBuffer pPointer, int preserve) {
/*  80 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  81 */     long function_pointer = caps.glUpdateObjectBufferATI;
/*  82 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  83 */     BufferChecks.checkDirect(pPointer);
/*  84 */     nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining(), MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*     */   }
/*     */   public static void glUpdateObjectBufferATI(int buffer, int offset, DoubleBuffer pPointer, int preserve) {
/*  87 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  88 */     long function_pointer = caps.glUpdateObjectBufferATI;
/*  89 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  90 */     BufferChecks.checkDirect(pPointer);
/*  91 */     nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 3, MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*     */   }
/*     */   public static void glUpdateObjectBufferATI(int buffer, int offset, FloatBuffer pPointer, int preserve) {
/*  94 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  95 */     long function_pointer = caps.glUpdateObjectBufferATI;
/*  96 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  97 */     BufferChecks.checkDirect(pPointer);
/*  98 */     nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 2, MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*     */   }
/*     */   public static void glUpdateObjectBufferATI(int buffer, int offset, IntBuffer pPointer, int preserve) {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glUpdateObjectBufferATI;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     BufferChecks.checkDirect(pPointer);
/* 105 */     nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 2, MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*     */   }
/*     */   public static void glUpdateObjectBufferATI(int buffer, int offset, ShortBuffer pPointer, int preserve) {
/* 108 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 109 */     long function_pointer = caps.glUpdateObjectBufferATI;
/* 110 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 111 */     BufferChecks.checkDirect(pPointer);
/* 112 */     nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 1, MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*     */   }
/*     */   static native void nglUpdateObjectBufferATI(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*     */ 
/*     */   public static void glGetObjectBufferATI(int buffer, int pname, FloatBuffer params) {
/* 117 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 118 */     long function_pointer = caps.glGetObjectBufferfvATI;
/* 119 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 120 */     BufferChecks.checkDirect(params);
/* 121 */     nglGetObjectBufferfvATI(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetObjectBufferfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetObjectBufferATI(int buffer, int pname, IntBuffer params) {
/* 126 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 127 */     long function_pointer = caps.glGetObjectBufferivATI;
/* 128 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 129 */     BufferChecks.checkDirect(params);
/* 130 */     nglGetObjectBufferivATI(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetObjectBufferivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetObjectBufferiATI(int buffer, int pname) {
/* 136 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 137 */     long function_pointer = caps.glGetObjectBufferivATI;
/* 138 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 139 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 140 */     nglGetObjectBufferivATI(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 141 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glFreeObjectBufferATI(int buffer) {
/* 145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 146 */     long function_pointer = caps.glFreeObjectBufferATI;
/* 147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 148 */     nglFreeObjectBufferATI(buffer, function_pointer);
/*     */   }
/*     */   static native void nglFreeObjectBufferATI(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glArrayObjectATI(int array, int size, int type, int stride, int buffer, int offset) {
/* 153 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 154 */     long function_pointer = caps.glArrayObjectATI;
/* 155 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 156 */     nglArrayObjectATI(array, size, type, stride, buffer, offset, function_pointer);
/*     */   }
/*     */   static native void nglArrayObjectATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glGetArrayObjectATI(int array, int pname, FloatBuffer params) {
/* 161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 162 */     long function_pointer = caps.glGetArrayObjectfvATI;
/* 163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 164 */     BufferChecks.checkBuffer(params, 4);
/* 165 */     nglGetArrayObjectfvATI(array, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetArrayObjectfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetArrayObjectATI(int array, int pname, IntBuffer params) {
/* 170 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 171 */     long function_pointer = caps.glGetArrayObjectivATI;
/* 172 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 173 */     BufferChecks.checkBuffer(params, 4);
/* 174 */     nglGetArrayObjectivATI(array, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetArrayObjectivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantArrayObjectATI(int id, int type, int stride, int buffer, int offset) {
/* 179 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 180 */     long function_pointer = caps.glVariantArrayObjectATI;
/* 181 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 182 */     nglVariantArrayObjectATI(id, type, stride, buffer, offset, function_pointer);
/*     */   }
/*     */   static native void nglVariantArrayObjectATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glGetVariantArrayObjectATI(int id, int pname, FloatBuffer params) {
/* 187 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 188 */     long function_pointer = caps.glGetVariantArrayObjectfvATI;
/* 189 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 190 */     BufferChecks.checkBuffer(params, 4);
/* 191 */     nglGetVariantArrayObjectfvATI(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVariantArrayObjectfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVariantArrayObjectATI(int id, int pname, IntBuffer params) {
/* 196 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 197 */     long function_pointer = caps.glGetVariantArrayObjectivATI;
/* 198 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 199 */     BufferChecks.checkBuffer(params, 4);
/* 200 */     nglGetVariantArrayObjectivATI(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVariantArrayObjectivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIVertexArrayObject
 * JD-Core Version:    0.6.2
 */