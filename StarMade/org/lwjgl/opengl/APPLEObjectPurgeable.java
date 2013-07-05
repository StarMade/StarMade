/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class APPLEObjectPurgeable
/*    */ {
/*    */   public static final int GL_RELEASED_APPLE = 35353;
/*    */   public static final int GL_VOLATILE_APPLE = 35354;
/*    */   public static final int GL_RETAINED_APPLE = 35355;
/*    */   public static final int GL_UNDEFINED_APPLE = 35356;
/*    */   public static final int GL_PURGEABLE_APPLE = 35357;
/*    */   public static final int GL_BUFFER_OBJECT_APPLE = 34227;
/*    */ 
/*    */   public static int glObjectPurgeableAPPLE(int objectType, int name, int option)
/*    */   {
/* 38 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 39 */     long function_pointer = caps.glObjectPurgeableAPPLE;
/* 40 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 41 */     int __result = nglObjectPurgeableAPPLE(objectType, name, option, function_pointer);
/* 42 */     return __result;
/*    */   }
/*    */   static native int nglObjectPurgeableAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static int glObjectUnpurgeableAPPLE(int objectType, int name, int option) {
/* 47 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 48 */     long function_pointer = caps.glObjectUnpurgeableAPPLE;
/* 49 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 50 */     int __result = nglObjectUnpurgeableAPPLE(objectType, name, option, function_pointer);
/* 51 */     return __result;
/*    */   }
/*    */   static native int nglObjectUnpurgeableAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static void glGetObjectParameterAPPLE(int objectType, int name, int pname, IntBuffer params) {
/* 56 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 57 */     long function_pointer = caps.glGetObjectParameterivAPPLE;
/* 58 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 59 */     BufferChecks.checkBuffer(params, 1);
/* 60 */     nglGetObjectParameterivAPPLE(objectType, name, pname, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetObjectParameterivAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGetObjectParameteriAPPLE(int objectType, int name, int pname) {
/* 66 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 67 */     long function_pointer = caps.glGetObjectParameterivAPPLE;
/* 68 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 69 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 70 */     nglGetObjectParameterivAPPLE(objectType, name, pname, MemoryUtil.getAddress(params), function_pointer);
/* 71 */     return params.get(0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEObjectPurgeable
 * JD-Core Version:    0.6.2
 */