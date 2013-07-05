/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.DoubleBuffer;
/*    */ import java.nio.FloatBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTSecondaryColor
/*    */ {
/*    */   public static final int GL_COLOR_SUM_EXT = 33880;
/*    */   public static final int GL_CURRENT_SECONDARY_COLOR_EXT = 33881;
/*    */   public static final int GL_SECONDARY_COLOR_ARRAY_SIZE_EXT = 33882;
/*    */   public static final int GL_SECONDARY_COLOR_ARRAY_TYPE_EXT = 33883;
/*    */   public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT = 33884;
/*    */   public static final int GL_SECONDARY_COLOR_ARRAY_POINTER_EXT = 33885;
/*    */   public static final int GL_SECONDARY_COLOR_ARRAY_EXT = 33886;
/*    */ 
/*    */   public static void glSecondaryColor3bEXT(byte red, byte green, byte blue)
/*    */   {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glSecondaryColor3bEXT;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     nglSecondaryColor3bEXT(red, green, blue, function_pointer);
/*    */   }
/*    */   static native void nglSecondaryColor3bEXT(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*    */ 
/*    */   public static void glSecondaryColor3fEXT(float red, float green, float blue) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glSecondaryColor3fEXT;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     nglSecondaryColor3fEXT(red, green, blue, function_pointer);
/*    */   }
/*    */   static native void nglSecondaryColor3fEXT(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*    */ 
/*    */   public static void glSecondaryColor3dEXT(double red, double green, double blue) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glSecondaryColor3dEXT;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     nglSecondaryColor3dEXT(red, green, blue, function_pointer);
/*    */   }
/*    */   static native void nglSecondaryColor3dEXT(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*    */ 
/*    */   public static void glSecondaryColor3ubEXT(byte red, byte green, byte blue) {
/* 45 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 46 */     long function_pointer = caps.glSecondaryColor3ubEXT;
/* 47 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 48 */     nglSecondaryColor3ubEXT(red, green, blue, function_pointer);
/*    */   }
/*    */   static native void nglSecondaryColor3ubEXT(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*    */ 
/*    */   public static void glSecondaryColorPointerEXT(int size, int stride, DoubleBuffer pPointer) {
/* 53 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 54 */     long function_pointer = caps.glSecondaryColorPointerEXT;
/* 55 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 56 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 57 */     BufferChecks.checkDirect(pPointer);
/* 58 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = pPointer;
/* 59 */     nglSecondaryColorPointerEXT(size, 5130, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer) {
/* 62 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 63 */     long function_pointer = caps.glSecondaryColorPointerEXT;
/* 64 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 65 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 66 */     BufferChecks.checkDirect(pPointer);
/* 67 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = pPointer;
/* 68 */     nglSecondaryColorPointerEXT(size, 5126, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
/* 71 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 72 */     long function_pointer = caps.glSecondaryColorPointerEXT;
/* 73 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 74 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 75 */     BufferChecks.checkDirect(pPointer);
/* 76 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = pPointer;
/* 77 */     nglSecondaryColorPointerEXT(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   static native void nglSecondaryColorPointerEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*    */ 
/* 81 */   public static void glSecondaryColorPointerEXT(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 82 */     long function_pointer = caps.glSecondaryColorPointerEXT;
/* 83 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 84 */     GLChecks.ensureArrayVBOenabled(caps);
/* 85 */     nglSecondaryColorPointerEXTBO(size, type, stride, pPointer_buffer_offset, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglSecondaryColorPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTSecondaryColor
 * JD-Core Version:    0.6.2
 */