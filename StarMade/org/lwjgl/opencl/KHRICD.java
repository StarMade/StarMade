/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ import org.lwjgl.PointerBuffer;
/*    */ 
/*    */ public final class KHRICD
/*    */ {
/*    */   public static final int CL_PLATFORM_ICD_SUFFIX_KHR = 2336;
/*    */   public static final int CL_PLATFORM_NOT_FOUND_KHR = -1001;
/*    */ 
/*    */   public static int clIcdGetPlatformIDsKHR(PointerBuffer platforms, IntBuffer num_platforms)
/*    */   {
/* 23 */     long function_pointer = CLCapabilities.clIcdGetPlatformIDsKHR;
/* 24 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 25 */     if (platforms != null)
/* 26 */       BufferChecks.checkDirect(platforms);
/* 27 */     if (num_platforms != null)
/* 28 */       BufferChecks.checkBuffer(num_platforms, 1);
/* 29 */     int __result = nclIcdGetPlatformIDsKHR(platforms == null ? 0 : platforms.remaining(), MemoryUtil.getAddressSafe(platforms), MemoryUtil.getAddressSafe(num_platforms), function_pointer);
/* 30 */     return __result;
/*    */   }
/*    */ 
/*    */   static native int nclIcdGetPlatformIDsKHR(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRICD
 * JD-Core Version:    0.6.2
 */