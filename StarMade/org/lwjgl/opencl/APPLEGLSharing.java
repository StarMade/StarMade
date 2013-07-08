/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */import org.lwjgl.PointerBuffer;
/*  7:   */
/* 29:   */public final class APPLEGLSharing
/* 30:   */{
/* 31:   */  public static final int CL_CONTEXT_PROPERTY_USE_CGL_SHAREGROUP_APPLE = 268435456;
/* 32:   */  public static final int CL_CGL_DEVICE_FOR_CURRENT_VIRTUAL_SCREEN_APPLE = 268435458;
/* 33:   */  public static final int CL_CGL_DEVICES_FOR_SUPPORTED_VIRTUAL_SCREENS_APPLE = 268435459;
/* 34:   */  public static final int CL_INVALID_GL_CONTEXT_APPLE = -1000;
/* 35:   */  
/* 36:   */  public static int clGetGLContextInfoAPPLE(CLContext context, PointerBuffer platform_gl_ctx, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/* 37:   */  {
/* 38:38 */    long function_pointer = CLCapabilities.clGetGLContextInfoAPPLE;
/* 39:39 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    BufferChecks.checkBuffer(platform_gl_ctx, 1);
/* 41:41 */    if (param_value != null)
/* 42:42 */      BufferChecks.checkDirect(param_value);
/* 43:43 */    if (param_value_size_ret != null)
/* 44:44 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 45:45 */    if ((param_value_size_ret == null) && (APIUtil.isDevicesParam(param_name))) param_value_size_ret = APIUtil.getBufferPointer();
/* 46:46 */    int __result = nclGetGLContextInfoAPPLE(context.getPointer(), MemoryUtil.getAddress(platform_gl_ctx), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 47:47 */    if ((__result == 0) && (param_value != null) && (APIUtil.isDevicesParam(param_name))) ((CLPlatform)context.getParent()).registerCLDevices(param_value, param_value_size_ret);
/* 48:48 */    return __result;
/* 49:   */  }
/* 50:   */  
/* 51:   */  static native int nclGetGLContextInfoAPPLE(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLEGLSharing
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */