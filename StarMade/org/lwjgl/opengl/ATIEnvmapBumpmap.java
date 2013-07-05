/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ATIEnvmapBumpmap
/*    */ {
/*    */   public static final int GL_BUMP_ROT_MATRIX_ATI = 34677;
/*    */   public static final int GL_BUMP_ROT_MATRIX_SIZE_ATI = 34678;
/*    */   public static final int GL_BUMP_NUM_TEX_UNITS_ATI = 34679;
/*    */   public static final int GL_BUMP_TEX_UNITS_ATI = 34680;
/*    */   public static final int GL_DUDV_ATI = 34681;
/*    */   public static final int GL_DU8DV8_ATI = 34682;
/*    */   public static final int GL_BUMP_ENVMAP_ATI = 34683;
/*    */   public static final int GL_BUMP_TARGET_ATI = 34684;
/*    */ 
/*    */   public static void glTexBumpParameterATI(int pname, FloatBuffer param)
/*    */   {
/* 22 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 23 */     long function_pointer = caps.glTexBumpParameterfvATI;
/* 24 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 25 */     BufferChecks.checkBuffer(param, 4);
/* 26 */     nglTexBumpParameterfvATI(pname, MemoryUtil.getAddress(param), function_pointer);
/*    */   }
/*    */   static native void nglTexBumpParameterfvATI(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glTexBumpParameterATI(int pname, IntBuffer param) {
/* 31 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 32 */     long function_pointer = caps.glTexBumpParameterivATI;
/* 33 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 34 */     BufferChecks.checkBuffer(param, 4);
/* 35 */     nglTexBumpParameterivATI(pname, MemoryUtil.getAddress(param), function_pointer);
/*    */   }
/*    */   static native void nglTexBumpParameterivATI(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glGetTexBumpParameterATI(int pname, FloatBuffer param) {
/* 40 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 41 */     long function_pointer = caps.glGetTexBumpParameterfvATI;
/* 42 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 43 */     BufferChecks.checkBuffer(param, 4);
/* 44 */     nglGetTexBumpParameterfvATI(pname, MemoryUtil.getAddress(param), function_pointer);
/*    */   }
/*    */   static native void nglGetTexBumpParameterfvATI(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glGetTexBumpParameterATI(int pname, IntBuffer param) {
/* 49 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 50 */     long function_pointer = caps.glGetTexBumpParameterivATI;
/* 51 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 52 */     BufferChecks.checkBuffer(param, 4);
/* 53 */     nglGetTexBumpParameterivATI(pname, MemoryUtil.getAddress(param), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetTexBumpParameterivATI(int paramInt, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIEnvmapBumpmap
 * JD-Core Version:    0.6.2
 */