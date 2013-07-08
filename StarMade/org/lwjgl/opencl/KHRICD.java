/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */import org.lwjgl.PointerBuffer;
/*  7:   */
/* 16:   */public final class KHRICD
/* 17:   */{
/* 18:   */  public static final int CL_PLATFORM_ICD_SUFFIX_KHR = 2336;
/* 19:   */  public static final int CL_PLATFORM_NOT_FOUND_KHR = -1001;
/* 20:   */  
/* 21:   */  public static int clIcdGetPlatformIDsKHR(PointerBuffer platforms, IntBuffer num_platforms)
/* 22:   */  {
/* 23:23 */    long function_pointer = CLCapabilities.clIcdGetPlatformIDsKHR;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    if (platforms != null)
/* 26:26 */      BufferChecks.checkDirect(platforms);
/* 27:27 */    if (num_platforms != null)
/* 28:28 */      BufferChecks.checkBuffer(num_platforms, 1);
/* 29:29 */    int __result = nclIcdGetPlatformIDsKHR(platforms == null ? 0 : platforms.remaining(), MemoryUtil.getAddressSafe(platforms), MemoryUtil.getAddressSafe(num_platforms), function_pointer);
/* 30:30 */    return __result;
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native int nclIcdGetPlatformIDsKHR(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRICD
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */