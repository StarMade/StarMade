/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTSeparateShaderObjects
/*    */ {
/*    */   public static final int GL_ACTIVE_PROGRAM_EXT = 35725;
/*    */ 
/*    */   public static void glUseShaderProgramEXT(int type, int program)
/*    */   {
/* 18 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 19 */     long function_pointer = caps.glUseShaderProgramEXT;
/* 20 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 21 */     nglUseShaderProgramEXT(type, program, function_pointer);
/*    */   }
/*    */   static native void nglUseShaderProgramEXT(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glActiveProgramEXT(int program) {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glActiveProgramEXT;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     nglActiveProgramEXT(program, function_pointer);
/*    */   }
/*    */   static native void nglActiveProgramEXT(int paramInt, long paramLong);
/*    */ 
/*    */   public static int glCreateShaderProgramEXT(int type, ByteBuffer string) {
/* 34 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 35 */     long function_pointer = caps.glCreateShaderProgramEXT;
/* 36 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 37 */     BufferChecks.checkDirect(string);
/* 38 */     BufferChecks.checkNullTerminated(string);
/* 39 */     int __result = nglCreateShaderProgramEXT(type, MemoryUtil.getAddress(string), function_pointer);
/* 40 */     return __result;
/*    */   }
/*    */ 
/*    */   static native int nglCreateShaderProgramEXT(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glCreateShaderProgramEXT(int type, CharSequence string) {
/* 46 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 47 */     long function_pointer = caps.glCreateShaderProgramEXT;
/* 48 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 49 */     int __result = nglCreateShaderProgramEXT(type, APIUtil.getBufferNT(caps, string), function_pointer);
/* 50 */     return __result;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTSeparateShaderObjects
 * JD-Core Version:    0.6.2
 */