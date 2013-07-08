package org.lwjgl.opencl;

import java.nio.IntBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;
import org.lwjgl.PointerBuffer;

public final class KHRICD
{
  public static final int CL_PLATFORM_ICD_SUFFIX_KHR = 2336;
  public static final int CL_PLATFORM_NOT_FOUND_KHR = -1001;
  
  public static int clIcdGetPlatformIDsKHR(PointerBuffer platforms, IntBuffer num_platforms)
  {
    long function_pointer = CLCapabilities.clIcdGetPlatformIDsKHR;
    BufferChecks.checkFunctionAddress(function_pointer);
    if (platforms != null) {
      BufferChecks.checkDirect(platforms);
    }
    if (num_platforms != null) {
      BufferChecks.checkBuffer(num_platforms, 1);
    }
    int __result = nclIcdGetPlatformIDsKHR(platforms == null ? 0 : platforms.remaining(), MemoryUtil.getAddressSafe(platforms), MemoryUtil.getAddressSafe(num_platforms), function_pointer);
    return __result;
  }
  
  static native int nclIcdGetPlatformIDsKHR(int paramInt, long paramLong1, long paramLong2, long paramLong3);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.KHRICD
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */