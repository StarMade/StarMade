/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVTransformFeedback2
/*    */ {
/*    */   public static final int GL_TRANSFORM_FEEDBACK_NV = 36386;
/*    */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED_NV = 36387;
/*    */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE_NV = 36388;
/*    */   public static final int GL_TRANSFORM_FEEDBACK_BINDING_NV = 36389;
/*    */ 
/*    */   public static void glBindTransformFeedbackNV(int target, int id)
/*    */   {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glBindTransformFeedbackNV;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     nglBindTransformFeedbackNV(target, id, function_pointer);
/*    */   }
/*    */   static native void nglBindTransformFeedbackNV(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glDeleteTransformFeedbacksNV(IntBuffer ids) {
/* 34 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 35 */     long function_pointer = caps.glDeleteTransformFeedbacksNV;
/* 36 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 37 */     BufferChecks.checkDirect(ids);
/* 38 */     nglDeleteTransformFeedbacksNV(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDeleteTransformFeedbacksNV(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDeleteTransformFeedbacksNV(int id) {
/* 44 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 45 */     long function_pointer = caps.glDeleteTransformFeedbacksNV;
/* 46 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 47 */     nglDeleteTransformFeedbacksNV(1, APIUtil.getInt(caps, id), function_pointer);
/*    */   }
/*    */ 
/*    */   public static void glGenTransformFeedbacksNV(IntBuffer ids) {
/* 51 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 52 */     long function_pointer = caps.glGenTransformFeedbacksNV;
/* 53 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 54 */     BufferChecks.checkDirect(ids);
/* 55 */     nglGenTransformFeedbacksNV(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGenTransformFeedbacksNV(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGenTransformFeedbacksNV() {
/* 61 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 62 */     long function_pointer = caps.glGenTransformFeedbacksNV;
/* 63 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 64 */     IntBuffer ids = APIUtil.getBufferInt(caps);
/* 65 */     nglGenTransformFeedbacksNV(1, MemoryUtil.getAddress(ids), function_pointer);
/* 66 */     return ids.get(0);
/*    */   }
/*    */ 
/*    */   public static boolean glIsTransformFeedbackNV(int id) {
/* 70 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 71 */     long function_pointer = caps.glIsTransformFeedbackNV;
/* 72 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 73 */     boolean __result = nglIsTransformFeedbackNV(id, function_pointer);
/* 74 */     return __result;
/*    */   }
/*    */   static native boolean nglIsTransformFeedbackNV(int paramInt, long paramLong);
/*    */ 
/*    */   public static void glPauseTransformFeedbackNV() {
/* 79 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 80 */     long function_pointer = caps.glPauseTransformFeedbackNV;
/* 81 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 82 */     nglPauseTransformFeedbackNV(function_pointer);
/*    */   }
/*    */   static native void nglPauseTransformFeedbackNV(long paramLong);
/*    */ 
/*    */   public static void glResumeTransformFeedbackNV() {
/* 87 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 88 */     long function_pointer = caps.glResumeTransformFeedbackNV;
/* 89 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 90 */     nglResumeTransformFeedbackNV(function_pointer);
/*    */   }
/*    */   static native void nglResumeTransformFeedbackNV(long paramLong);
/*    */ 
/*    */   public static void glDrawTransformFeedbackNV(int mode, int id) {
/* 95 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 96 */     long function_pointer = caps.glDrawTransformFeedbackNV;
/* 97 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 98 */     nglDrawTransformFeedbackNV(mode, id, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawTransformFeedbackNV(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTransformFeedback2
 * JD-Core Version:    0.6.2
 */