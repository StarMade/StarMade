/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class AMDNameGenDelete
/*    */ {
/*    */   public static final int GL_DATA_BUFFER_AMD = 37201;
/*    */   public static final int GL_PERFORMANCE_MONITOR_AMD = 37202;
/*    */   public static final int GL_QUERY_OBJECT_AMD = 37203;
/*    */   public static final int GL_VERTEX_ARRAY_OBJECT_AMD = 37204;
/*    */   public static final int GL_SAMPLER_OBJECT_AMD = 37205;
/*    */ 
/*    */   public static void glGenNamesAMD(int identifier, IntBuffer names)
/*    */   {
/* 22 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 23 */     long function_pointer = caps.glGenNamesAMD;
/* 24 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 25 */     BufferChecks.checkDirect(names);
/* 26 */     nglGenNamesAMD(identifier, names.remaining(), MemoryUtil.getAddress(names), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGenNamesAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGenNamesAMD(int identifier) {
/* 32 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 33 */     long function_pointer = caps.glGenNamesAMD;
/* 34 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 35 */     IntBuffer names = APIUtil.getBufferInt(caps);
/* 36 */     nglGenNamesAMD(identifier, 1, MemoryUtil.getAddress(names), function_pointer);
/* 37 */     return names.get(0);
/*    */   }
/*    */ 
/*    */   public static void glDeleteNamesAMD(int identifier, IntBuffer names) {
/* 41 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 42 */     long function_pointer = caps.glDeleteNamesAMD;
/* 43 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 44 */     BufferChecks.checkDirect(names);
/* 45 */     nglDeleteNamesAMD(identifier, names.remaining(), MemoryUtil.getAddress(names), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDeleteNamesAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDeleteNamesAMD(int identifier, int name) {
/* 51 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 52 */     long function_pointer = caps.glDeleteNamesAMD;
/* 53 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 54 */     nglDeleteNamesAMD(identifier, 1, APIUtil.getInt(caps, name), function_pointer);
/*    */   }
/*    */ 
/*    */   public static boolean glIsNameAMD(int identifier, int name) {
/* 58 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 59 */     long function_pointer = caps.glIsNameAMD;
/* 60 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 61 */     boolean __result = nglIsNameAMD(identifier, name, function_pointer);
/* 62 */     return __result;
/*    */   }
/*    */ 
/*    */   static native boolean nglIsNameAMD(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDNameGenDelete
 * JD-Core Version:    0.6.2
 */