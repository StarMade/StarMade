/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVParameterBufferObject
/*    */ {
/*    */   public static final int GL_MAX_PROGRAM_PARAMETER_BUFFER_BINDINGS_NV = 36256;
/*    */   public static final int GL_MAX_PROGRAM_PARAMETER_BUFFER_SIZE_NV = 36257;
/*    */   public static final int GL_VERTEX_PROGRAM_PARAMETER_BUFFER_NV = 36258;
/*    */   public static final int GL_GEOMETRY_PROGRAM_PARAMETER_BUFFER_NV = 36259;
/*    */   public static final int GL_FRAGMENT_PROGRAM_PARAMETER_BUFFER_NV = 36260;
/*    */ 
/*    */   public static void glProgramBufferParametersNV(int target, int buffer, int index, FloatBuffer params)
/*    */   {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glProgramBufferParametersfvNV;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     BufferChecks.checkDirect(params);
/* 33 */     nglProgramBufferParametersfvNV(target, buffer, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */   static native void nglProgramBufferParametersfvNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glProgramBufferParametersINV(int target, int buffer, int index, IntBuffer params) {
/* 38 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 39 */     long function_pointer = caps.glProgramBufferParametersIivNV;
/* 40 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 41 */     BufferChecks.checkDirect(params);
/* 42 */     nglProgramBufferParametersIivNV(target, buffer, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */   static native void nglProgramBufferParametersIivNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glProgramBufferParametersIuNV(int target, int buffer, int index, IntBuffer params) {
/* 47 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 48 */     long function_pointer = caps.glProgramBufferParametersIuivNV;
/* 49 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 50 */     BufferChecks.checkDirect(params);
/* 51 */     nglProgramBufferParametersIuivNV(target, buffer, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglProgramBufferParametersIuivNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVParameterBufferObject
 * JD-Core Version:    0.6.2
 */