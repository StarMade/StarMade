/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.DoubleBuffer;
/*    */ import java.nio.FloatBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVFragmentProgram extends NVProgram
/*    */ {
/*    */   public static final int GL_FRAGMENT_PROGRAM_NV = 34928;
/*    */   public static final int GL_MAX_TEXTURE_COORDS_NV = 34929;
/*    */   public static final int GL_MAX_TEXTURE_IMAGE_UNITS_NV = 34930;
/*    */   public static final int GL_FRAGMENT_PROGRAM_BINDING_NV = 34931;
/*    */   public static final int GL_MAX_FRAGMENT_PROGRAM_LOCAL_PARAMETERS_NV = 34920;
/*    */ 
/*    */   public static void glProgramNamedParameter4fNV(int id, ByteBuffer name, float x, float y, float z, float w)
/*    */   {
/* 32 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 33 */     long function_pointer = caps.glProgramNamedParameter4fNV;
/* 34 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 35 */     BufferChecks.checkDirect(name);
/* 36 */     nglProgramNamedParameter4fNV(id, name.remaining(), MemoryUtil.getAddress(name), x, y, z, w, function_pointer);
/*    */   }
/*    */   static native void nglProgramNamedParameter4fNV(int paramInt1, int paramInt2, long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2);
/*    */ 
/*    */   public static void glProgramNamedParameter4dNV(int id, ByteBuffer name, double x, double y, double z, double w) {
/* 41 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 42 */     long function_pointer = caps.glProgramNamedParameter4dNV;
/* 43 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 44 */     BufferChecks.checkDirect(name);
/* 45 */     nglProgramNamedParameter4dNV(id, name.remaining(), MemoryUtil.getAddress(name), x, y, z, w, function_pointer);
/*    */   }
/*    */   static native void nglProgramNamedParameter4dNV(int paramInt1, int paramInt2, long paramLong1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong2);
/*    */ 
/*    */   public static void glGetProgramNamedParameterNV(int id, ByteBuffer name, FloatBuffer params) {
/* 50 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 51 */     long function_pointer = caps.glGetProgramNamedParameterfvNV;
/* 52 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 53 */     BufferChecks.checkDirect(name);
/* 54 */     BufferChecks.checkBuffer(params, 4);
/* 55 */     nglGetProgramNamedParameterfvNV(id, name.remaining(), MemoryUtil.getAddress(name), MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */   static native void nglGetProgramNamedParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*    */ 
/*    */   public static void glGetProgramNamedParameterNV(int id, ByteBuffer name, DoubleBuffer params) {
/* 60 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 61 */     long function_pointer = caps.glGetProgramNamedParameterdvNV;
/* 62 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 63 */     BufferChecks.checkDirect(name);
/* 64 */     BufferChecks.checkBuffer(params, 4);
/* 65 */     nglGetProgramNamedParameterdvNV(id, name.remaining(), MemoryUtil.getAddress(name), MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetProgramNamedParameterdvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVFragmentProgram
 * JD-Core Version:    0.6.2
 */