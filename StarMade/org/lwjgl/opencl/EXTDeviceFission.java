/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ public final class EXTDeviceFission
/*     */ {
/*     */   public static final int CL_DEVICE_PARTITION_EQUALLY_EXT = 16464;
/*     */   public static final int CL_DEVICE_PARTITION_BY_COUNTS_EXT = 16465;
/*     */   public static final int CL_DEVICE_PARTITION_BY_NAMES_EXT = 16466;
/*     */   public static final int CL_DEVICE_PARTITION_BY_AFFINITY_DOMAIN_EXT = 16467;
/*     */   public static final int CL_AFFINITY_DOMAIN_L1_CACHE_EXT = 1;
/*     */   public static final int CL_AFFINITY_DOMAIN_L2_CACHE_EXT = 2;
/*     */   public static final int CL_AFFINITY_DOMAIN_L3_CACHE_EXT = 3;
/*     */   public static final int CL_AFFINITY_DOMAIN_L4_CACHE_EXT = 4;
/*     */   public static final int CL_AFFINITY_DOMAIN_NUMA_EXT = 16;
/*     */   public static final int CL_AFFINITY_DOMAIN_NEXT_FISSIONABLE_EXT = 256;
/*     */   public static final int CL_DEVICE_PARENT_DEVICE_EXT = 16468;
/*     */   public static final int CL_DEVICE_PARITION_TYPES_EXT = 16469;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAINS_EXT = 16470;
/*     */   public static final int CL_DEVICE_REFERENCE_COUNT_EXT = 16471;
/*     */   public static final int CL_DEVICE_PARTITION_STYLE_EXT = 16472;
/*     */   public static final int CL_PROPERTIES_LIST_END_EXT = 0;
/*     */   public static final int CL_PARTITION_BY_COUNTS_LIST_END_EXT = 0;
/*     */   public static final int CL_PARTITION_BY_NAMES_LIST_END_EXT = -1;
/*     */   public static final int CL_DEVICE_PARTITION_FAILED_EXT = -1057;
/*     */   public static final int CL_INVALID_PARTITION_COUNT_EXT = -1058;
/*     */   public static final int CL_INVALID_PARTITION_NAME_EXT = -1059;
/*     */ 
/*     */   public static int clRetainDeviceEXT(CLDevice device)
/*     */   {
/*  82 */     long function_pointer = CLCapabilities.clRetainDeviceEXT;
/*  83 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  84 */     int __result = nclRetainDeviceEXT(device.getPointer(), function_pointer);
/*  85 */     if (__result == 0) device.retain();
/*  86 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nclRetainDeviceEXT(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int clReleaseDeviceEXT(CLDevice device)
/*     */   {
/*  99 */     long function_pointer = CLCapabilities.clReleaseDeviceEXT;
/* 100 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 101 */     APIUtil.releaseObjects(device);
/* 102 */     int __result = nclReleaseDeviceEXT(device.getPointer(), function_pointer);
/* 103 */     if (__result == 0) device.release();
/* 104 */     return __result;
/*     */   }
/*     */   static native int nclReleaseDeviceEXT(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int clCreateSubDevicesEXT(CLDevice in_device, LongBuffer properties, PointerBuffer out_devices, IntBuffer num_devices) {
/* 109 */     long function_pointer = CLCapabilities.clCreateSubDevicesEXT;
/* 110 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 111 */     BufferChecks.checkDirect(properties);
/* 112 */     BufferChecks.checkNullTerminated(properties);
/* 113 */     if (out_devices != null)
/* 114 */       BufferChecks.checkDirect(out_devices);
/* 115 */     if (num_devices != null)
/* 116 */       BufferChecks.checkBuffer(num_devices, 1);
/* 117 */     int __result = nclCreateSubDevicesEXT(in_device.getPointer(), MemoryUtil.getAddress(properties), out_devices == null ? 0 : out_devices.remaining(), MemoryUtil.getAddressSafe(out_devices), MemoryUtil.getAddressSafe(num_devices), function_pointer);
/* 118 */     if ((__result == 0) && (out_devices != null)) in_device.registerSubCLDevices(out_devices);
/* 119 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nclCreateSubDevicesEXT(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.EXTDeviceFission
 * JD-Core Version:    0.6.2
 */