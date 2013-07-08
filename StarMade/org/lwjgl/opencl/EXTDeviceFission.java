/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import java.nio.LongBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */import org.lwjgl.PointerBuffer;
/*   8:    */
/*  56:    */public final class EXTDeviceFission
/*  57:    */{
/*  58:    */  public static final int CL_DEVICE_PARTITION_EQUALLY_EXT = 16464;
/*  59:    */  public static final int CL_DEVICE_PARTITION_BY_COUNTS_EXT = 16465;
/*  60:    */  public static final int CL_DEVICE_PARTITION_BY_NAMES_EXT = 16466;
/*  61:    */  public static final int CL_DEVICE_PARTITION_BY_AFFINITY_DOMAIN_EXT = 16467;
/*  62:    */  public static final int CL_AFFINITY_DOMAIN_L1_CACHE_EXT = 1;
/*  63:    */  public static final int CL_AFFINITY_DOMAIN_L2_CACHE_EXT = 2;
/*  64:    */  public static final int CL_AFFINITY_DOMAIN_L3_CACHE_EXT = 3;
/*  65:    */  public static final int CL_AFFINITY_DOMAIN_L4_CACHE_EXT = 4;
/*  66:    */  public static final int CL_AFFINITY_DOMAIN_NUMA_EXT = 16;
/*  67:    */  public static final int CL_AFFINITY_DOMAIN_NEXT_FISSIONABLE_EXT = 256;
/*  68:    */  public static final int CL_DEVICE_PARENT_DEVICE_EXT = 16468;
/*  69:    */  public static final int CL_DEVICE_PARITION_TYPES_EXT = 16469;
/*  70:    */  public static final int CL_DEVICE_AFFINITY_DOMAINS_EXT = 16470;
/*  71:    */  public static final int CL_DEVICE_REFERENCE_COUNT_EXT = 16471;
/*  72:    */  public static final int CL_DEVICE_PARTITION_STYLE_EXT = 16472;
/*  73:    */  public static final int CL_PROPERTIES_LIST_END_EXT = 0;
/*  74:    */  public static final int CL_PARTITION_BY_COUNTS_LIST_END_EXT = 0;
/*  75:    */  public static final int CL_PARTITION_BY_NAMES_LIST_END_EXT = -1;
/*  76:    */  public static final int CL_DEVICE_PARTITION_FAILED_EXT = -1057;
/*  77:    */  public static final int CL_INVALID_PARTITION_COUNT_EXT = -1058;
/*  78:    */  public static final int CL_INVALID_PARTITION_NAME_EXT = -1059;
/*  79:    */  
/*  80:    */  public static int clRetainDeviceEXT(CLDevice device)
/*  81:    */  {
/*  82: 82 */    long function_pointer = CLCapabilities.clRetainDeviceEXT;
/*  83: 83 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  84: 84 */    int __result = nclRetainDeviceEXT(device.getPointer(), function_pointer);
/*  85: 85 */    if (__result == 0) device.retain();
/*  86: 86 */    return __result;
/*  87:    */  }
/*  88:    */  
/*  92:    */  static native int nclRetainDeviceEXT(long paramLong1, long paramLong2);
/*  93:    */  
/*  97:    */  public static int clReleaseDeviceEXT(CLDevice device)
/*  98:    */  {
/*  99: 99 */    long function_pointer = CLCapabilities.clReleaseDeviceEXT;
/* 100:100 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 101:101 */    APIUtil.releaseObjects(device);
/* 102:102 */    int __result = nclReleaseDeviceEXT(device.getPointer(), function_pointer);
/* 103:103 */    if (__result == 0) device.release();
/* 104:104 */    return __result;
/* 105:    */  }
/* 106:    */  
/* 107:    */  static native int nclReleaseDeviceEXT(long paramLong1, long paramLong2);
/* 108:    */  
/* 109:109 */  public static int clCreateSubDevicesEXT(CLDevice in_device, LongBuffer properties, PointerBuffer out_devices, IntBuffer num_devices) { long function_pointer = CLCapabilities.clCreateSubDevicesEXT;
/* 110:110 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 111:111 */    BufferChecks.checkDirect(properties);
/* 112:112 */    BufferChecks.checkNullTerminated(properties);
/* 113:113 */    if (out_devices != null)
/* 114:114 */      BufferChecks.checkDirect(out_devices);
/* 115:115 */    if (num_devices != null)
/* 116:116 */      BufferChecks.checkBuffer(num_devices, 1);
/* 117:117 */    int __result = nclCreateSubDevicesEXT(in_device.getPointer(), MemoryUtil.getAddress(properties), out_devices == null ? 0 : out_devices.remaining(), MemoryUtil.getAddressSafe(out_devices), MemoryUtil.getAddressSafe(num_devices), function_pointer);
/* 118:118 */    if ((__result == 0) && (out_devices != null)) in_device.registerSubCLDevices(out_devices);
/* 119:119 */    return __result;
/* 120:    */  }
/* 121:    */  
/* 122:    */  static native int nclCreateSubDevicesEXT(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/* 123:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.EXTDeviceFission
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */