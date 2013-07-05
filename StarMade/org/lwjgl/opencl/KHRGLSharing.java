/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ import org.lwjgl.PointerBuffer;
/*    */ 
/*    */ public final class KHRGLSharing
/*    */ {
/*    */   public static final int CL_INVALID_GL_SHAREGROUP_REFERENCE_KHR = -1000;
/*    */   public static final int CL_CURRENT_DEVICE_FOR_GL_CONTEXT_KHR = 8198;
/*    */   public static final int CL_DEVICES_FOR_GL_CONTEXT_KHR = 8199;
/*    */   public static final int CL_GL_CONTEXT_KHR = 8200;
/*    */   public static final int CL_EGL_DISPLAY_KHR = 8201;
/*    */   public static final int CL_GLX_DISPLAY_KHR = 8202;
/*    */   public static final int CL_WGL_HDC_KHR = 8203;
/*    */   public static final int CL_CGL_SHAREGROUP_KHR = 8204;
/*    */ 
/*    */   public static int clGetGLContextInfoKHR(PointerBuffer properties, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*    */   {
/* 36 */     long function_pointer = CLCapabilities.clGetGLContextInfoKHR;
/* 37 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 38 */     BufferChecks.checkDirect(properties);
/* 39 */     BufferChecks.checkNullTerminated(properties);
/* 40 */     if (param_value != null)
/* 41 */       BufferChecks.checkDirect(param_value);
/* 42 */     if (param_value_size_ret != null)
/* 43 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 44 */     if ((param_value_size_ret == null) && (APIUtil.isDevicesParam(param_name))) param_value_size_ret = APIUtil.getBufferPointer();
/* 45 */     int __result = nclGetGLContextInfoKHR(MemoryUtil.getAddress(properties), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 46 */     if ((__result == 0) && (param_value != null) && (APIUtil.isDevicesParam(param_name))) APIUtil.getCLPlatform(properties).registerCLDevices(param_value, param_value_size_ret);
/* 47 */     return __result;
/*    */   }
/*    */ 
/*    */   static native int nclGetGLContextInfoKHR(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRGLSharing
 * JD-Core Version:    0.6.2
 */