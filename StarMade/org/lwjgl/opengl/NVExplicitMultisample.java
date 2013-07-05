/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVExplicitMultisample
/*    */ {
/*    */   public static final int GL_SAMPLE_POSITION_NV = 36432;
/*    */   public static final int GL_SAMPLE_MASK_NV = 36433;
/*    */   public static final int GL_SAMPLE_MASK_VALUE_NV = 36434;
/*    */   public static final int GL_TEXTURE_BINDING_RENDERBUFFER_NV = 36435;
/*    */   public static final int GL_TEXTURE_RENDERBUFFER_DATA_STORE_BINDING_NV = 36436;
/*    */   public static final int GL_MAX_SAMPLE_MASK_WORDS_NV = 36441;
/*    */   public static final int GL_TEXTURE_RENDERBUFFER_NV = 36437;
/*    */   public static final int GL_SAMPLER_RENDERBUFFER_NV = 36438;
/*    */   public static final int GL_INT_SAMPLER_RENDERBUFFER_NV = 36439;
/*    */   public static final int GL_UNSIGNED_INT_SAMPLER_RENDERBUFFER_NV = 36440;
/*    */ 
/*    */   public static void glGetBooleanIndexedEXT(int pname, int index, ByteBuffer data)
/*    */   {
/* 51 */     EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index, data);
/*    */   }
/*    */ 
/*    */   public static boolean glGetBooleanIndexedEXT(int pname, int index)
/*    */   {
/* 56 */     return EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index);
/*    */   }
/*    */ 
/*    */   public static void glGetIntegerIndexedEXT(int pname, int index, IntBuffer data) {
/* 60 */     EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index, data);
/*    */   }
/*    */ 
/*    */   public static int glGetIntegerIndexedEXT(int pname, int index)
/*    */   {
/* 65 */     return EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index);
/*    */   }
/*    */ 
/*    */   public static void glGetMultisampleNV(int pname, int index, FloatBuffer val) {
/* 69 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 70 */     long function_pointer = caps.glGetMultisamplefvNV;
/* 71 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 72 */     BufferChecks.checkBuffer(val, 2);
/* 73 */     nglGetMultisamplefvNV(pname, index, MemoryUtil.getAddress(val), function_pointer);
/*    */   }
/*    */   static native void nglGetMultisamplefvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glSampleMaskIndexedNV(int index, int mask) {
/* 78 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 79 */     long function_pointer = caps.glSampleMaskIndexedNV;
/* 80 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 81 */     nglSampleMaskIndexedNV(index, mask, function_pointer);
/*    */   }
/*    */   static native void nglSampleMaskIndexedNV(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glTexRenderbufferNV(int target, int renderbuffer) {
/* 86 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 87 */     long function_pointer = caps.glTexRenderbufferNV;
/* 88 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 89 */     nglTexRenderbufferNV(target, renderbuffer, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglTexRenderbufferNV(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVExplicitMultisample
 * JD-Core Version:    0.6.2
 */