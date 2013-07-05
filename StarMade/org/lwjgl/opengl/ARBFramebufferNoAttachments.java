/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ARBFramebufferNoAttachments
/*    */ {
/*    */   public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 37648;
/*    */   public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 37649;
/*    */   public static final int GL_FRAMEBUFFER_DEFAULT_LAYERS = 37650;
/*    */   public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 37651;
/*    */   public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;
/*    */   public static final int GL_MAX_FRAMEBUFFER_WIDTH = 37653;
/*    */   public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 37654;
/*    */   public static final int GL_MAX_FRAMEBUFFER_LAYERS = 37655;
/*    */   public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 37656;
/*    */ 
/*    */   public static void glFramebufferParameteri(int target, int pname, int param)
/*    */   {
/* 33 */     GL43.glFramebufferParameteri(target, pname, param);
/*    */   }
/*    */ 
/*    */   public static void glGetFramebufferParameter(int target, int pname, IntBuffer params) {
/* 37 */     GL43.glGetFramebufferParameter(target, pname, params);
/*    */   }
/*    */ 
/*    */   public static int glGetFramebufferParameteri(int target, int pname)
/*    */   {
/* 42 */     return GL43.glGetFramebufferParameteri(target, pname);
/*    */   }
/*    */ 
/*    */   public static void glNamedFramebufferParameteriEXT(int framebuffer, int pname, int param) {
/* 46 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 47 */     long function_pointer = caps.glNamedFramebufferParameteriEXT;
/* 48 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 49 */     nglNamedFramebufferParameteriEXT(framebuffer, pname, param, function_pointer);
/*    */   }
/*    */   static native void nglNamedFramebufferParameteriEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static void glGetNamedFramebufferParameterEXT(int framebuffer, int pname, IntBuffer params) {
/* 54 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 55 */     long function_pointer = caps.glGetNamedFramebufferParameterivEXT;
/* 56 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 57 */     BufferChecks.checkBuffer(params, 1);
/* 58 */     nglGetNamedFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetNamedFramebufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGetNamedFramebufferParameterEXT(int framebuffer, int pname) {
/* 64 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 65 */     long function_pointer = caps.glGetNamedFramebufferParameterivEXT;
/* 66 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 67 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 68 */     nglGetNamedFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 69 */     return params.get(0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBFramebufferNoAttachments
 * JD-Core Version:    0.6.2
 */