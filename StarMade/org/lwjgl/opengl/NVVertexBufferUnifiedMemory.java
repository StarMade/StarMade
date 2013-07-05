/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVVertexBufferUnifiedMemory
/*     */ {
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_UNIFIED_NV = 36638;
/*     */   public static final int GL_ELEMENT_ARRAY_UNIFIED_NV = 36639;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_ADDRESS_NV = 36640;
/*     */   public static final int GL_TEXTURE_COORD_ARRAY_ADDRESS_NV = 36645;
/*     */   public static final int GL_VERTEX_ARRAY_ADDRESS_NV = 36641;
/*     */   public static final int GL_NORMAL_ARRAY_ADDRESS_NV = 36642;
/*     */   public static final int GL_COLOR_ARRAY_ADDRESS_NV = 36643;
/*     */   public static final int GL_INDEX_ARRAY_ADDRESS_NV = 36644;
/*     */   public static final int GL_EDGE_FLAG_ARRAY_ADDRESS_NV = 36646;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_ADDRESS_NV = 36647;
/*     */   public static final int GL_FOG_COORD_ARRAY_ADDRESS_NV = 36648;
/*     */   public static final int GL_ELEMENT_ARRAY_ADDRESS_NV = 36649;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_LENGTH_NV = 36650;
/*     */   public static final int GL_TEXTURE_COORD_ARRAY_LENGTH_NV = 36655;
/*     */   public static final int GL_VERTEX_ARRAY_LENGTH_NV = 36651;
/*     */   public static final int GL_NORMAL_ARRAY_LENGTH_NV = 36652;
/*     */   public static final int GL_COLOR_ARRAY_LENGTH_NV = 36653;
/*     */   public static final int GL_INDEX_ARRAY_LENGTH_NV = 36654;
/*     */   public static final int GL_EDGE_FLAG_ARRAY_LENGTH_NV = 36656;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_LENGTH_NV = 36657;
/*     */   public static final int GL_FOG_COORD_ARRAY_LENGTH_NV = 36658;
/*     */   public static final int GL_ELEMENT_ARRAY_LENGTH_NV = 36659;
/*     */ 
/*     */   public static void glBufferAddressRangeNV(int pname, int index, long address, long length)
/*     */   {
/*  58 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  59 */     long function_pointer = caps.glBufferAddressRangeNV;
/*  60 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  61 */     nglBufferAddressRangeNV(pname, index, address, length, function_pointer);
/*     */   }
/*     */   static native void nglBufferAddressRangeNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glVertexFormatNV(int size, int type, int stride) {
/*  66 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  67 */     long function_pointer = caps.glVertexFormatNV;
/*  68 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  69 */     nglVertexFormatNV(size, type, stride, function_pointer);
/*     */   }
/*     */   static native void nglVertexFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glNormalFormatNV(int type, int stride) {
/*  74 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  75 */     long function_pointer = caps.glNormalFormatNV;
/*  76 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  77 */     nglNormalFormatNV(type, stride, function_pointer);
/*     */   }
/*     */   static native void nglNormalFormatNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glColorFormatNV(int size, int type, int stride) {
/*  82 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  83 */     long function_pointer = caps.glColorFormatNV;
/*  84 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  85 */     nglColorFormatNV(size, type, stride, function_pointer);
/*     */   }
/*     */   static native void nglColorFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glIndexFormatNV(int type, int stride) {
/*  90 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  91 */     long function_pointer = caps.glIndexFormatNV;
/*  92 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  93 */     nglIndexFormatNV(type, stride, function_pointer);
/*     */   }
/*     */   static native void nglIndexFormatNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glTexCoordFormatNV(int size, int type, int stride) {
/*  98 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  99 */     long function_pointer = caps.glTexCoordFormatNV;
/* 100 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 101 */     nglTexCoordFormatNV(size, type, stride, function_pointer);
/*     */   }
/*     */   static native void nglTexCoordFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glEdgeFlagFormatNV(int stride) {
/* 106 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 107 */     long function_pointer = caps.glEdgeFlagFormatNV;
/* 108 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 109 */     nglEdgeFlagFormatNV(stride, function_pointer);
/*     */   }
/*     */   static native void nglEdgeFlagFormatNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColorFormatNV(int size, int type, int stride) {
/* 114 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 115 */     long function_pointer = caps.glSecondaryColorFormatNV;
/* 116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 117 */     nglSecondaryColorFormatNV(size, type, stride, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColorFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glFogCoordFormatNV(int type, int stride) {
/* 122 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 123 */     long function_pointer = caps.glFogCoordFormatNV;
/* 124 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 125 */     nglFogCoordFormatNV(type, stride, function_pointer);
/*     */   }
/*     */   static native void nglFogCoordFormatNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribFormatNV(int index, int size, int type, boolean normalized, int stride) {
/* 130 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 131 */     long function_pointer = caps.glVertexAttribFormatNV;
/* 132 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 133 */     nglVertexAttribFormatNV(index, size, type, normalized, stride, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribFormatNV(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribIFormatNV(int index, int size, int type, int stride) {
/* 138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 139 */     long function_pointer = caps.glVertexAttribIFormatNV;
/* 140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 141 */     nglVertexAttribIFormatNV(index, size, type, stride, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribIFormatNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glGetIntegeruNV(int value, int index, LongBuffer result) {
/* 146 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 147 */     long function_pointer = caps.glGetIntegerui64i_vNV;
/* 148 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 149 */     BufferChecks.checkBuffer(result, 1);
/* 150 */     nglGetIntegerui64i_vNV(value, index, MemoryUtil.getAddress(result), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetIntegerui64i_vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetIntegerui64NV(int value, int index) {
/* 156 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 157 */     long function_pointer = caps.glGetIntegerui64i_vNV;
/* 158 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 159 */     LongBuffer result = APIUtil.getBufferLong(caps);
/* 160 */     nglGetIntegerui64i_vNV(value, index, MemoryUtil.getAddress(result), function_pointer);
/* 161 */     return result.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexBufferUnifiedMemory
 * JD-Core Version:    0.6.2
 */