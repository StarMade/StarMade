/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */import org.lwjgl.PointerBuffer;
/*  7:   */
/* 23:   */public final class KHRGLSharing
/* 24:   */{
/* 25:   */  public static final int CL_INVALID_GL_SHAREGROUP_REFERENCE_KHR = -1000;
/* 26:   */  public static final int CL_CURRENT_DEVICE_FOR_GL_CONTEXT_KHR = 8198;
/* 27:   */  public static final int CL_DEVICES_FOR_GL_CONTEXT_KHR = 8199;
/* 28:   */  public static final int CL_GL_CONTEXT_KHR = 8200;
/* 29:   */  public static final int CL_EGL_DISPLAY_KHR = 8201;
/* 30:   */  public static final int CL_GLX_DISPLAY_KHR = 8202;
/* 31:   */  public static final int CL_WGL_HDC_KHR = 8203;
/* 32:   */  public static final int CL_CGL_SHAREGROUP_KHR = 8204;
/* 33:   */  
/* 34:   */  public static int clGetGLContextInfoKHR(PointerBuffer properties, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/* 35:   */  {
/* 36:36 */    long function_pointer = CLCapabilities.clGetGLContextInfoKHR;
/* 37:37 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 38:38 */    BufferChecks.checkDirect(properties);
/* 39:39 */    BufferChecks.checkNullTerminated(properties);
/* 40:40 */    if (param_value != null)
/* 41:41 */      BufferChecks.checkDirect(param_value);
/* 42:42 */    if (param_value_size_ret != null)
/* 43:43 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 44:44 */    if ((param_value_size_ret == null) && (APIUtil.isDevicesParam(param_name))) param_value_size_ret = APIUtil.getBufferPointer();
/* 45:45 */    int __result = nclGetGLContextInfoKHR(MemoryUtil.getAddress(properties), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 46:46 */    if ((__result == 0) && (param_value != null) && (APIUtil.isDevicesParam(param_name))) APIUtil.getCLPlatform(properties).registerCLDevices(param_value, param_value_size_ret);
/* 47:47 */    return __result;
/* 48:   */  }
/* 49:   */  
/* 50:   */  static native int nclGetGLContextInfoKHR(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRGLSharing
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */