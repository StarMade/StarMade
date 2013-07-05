/*      */ package org.lwjgl.opencl;
/*      */ 
/*      */ import java.nio.Buffer;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.LongBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ import org.lwjgl.PointerBuffer;
/*      */ 
/*      */ public final class CL10
/*      */ {
/*      */   public static final int CL_SUCCESS = 0;
/*      */   public static final int CL_DEVICE_NOT_FOUND = -1;
/*      */   public static final int CL_DEVICE_NOT_AVAILABLE = -2;
/*      */   public static final int CL_COMPILER_NOT_AVAILABLE = -3;
/*      */   public static final int CL_MEM_OBJECT_ALLOCATION_FAILURE = -4;
/*      */   public static final int CL_OUT_OF_RESOURCES = -5;
/*      */   public static final int CL_OUT_OF_HOST_MEMORY = -6;
/*      */   public static final int CL_PROFILING_INFO_NOT_AVAILABLE = -7;
/*      */   public static final int CL_MEM_COPY_OVERLAP = -8;
/*      */   public static final int CL_IMAGE_FORMAT_MISMATCH = -9;
/*      */   public static final int CL_IMAGE_FORMAT_NOT_SUPPORTED = -10;
/*      */   public static final int CL_BUILD_PROGRAM_FAILURE = -11;
/*      */   public static final int CL_MAP_FAILURE = -12;
/*      */   public static final int CL_INVALID_VALUE = -30;
/*      */   public static final int CL_INVALID_DEVICE_TYPE = -31;
/*      */   public static final int CL_INVALID_PLATFORM = -32;
/*      */   public static final int CL_INVALID_DEVICE = -33;
/*      */   public static final int CL_INVALID_CONTEXT = -34;
/*      */   public static final int CL_INVALID_QUEUE_PROPERTIES = -35;
/*      */   public static final int CL_INVALID_COMMAND_QUEUE = -36;
/*      */   public static final int CL_INVALID_HOST_PTR = -37;
/*      */   public static final int CL_INVALID_MEM_OBJECT = -38;
/*      */   public static final int CL_INVALID_IMAGE_FORMAT_DESCRIPTOR = -39;
/*      */   public static final int CL_INVALID_IMAGE_SIZE = -40;
/*      */   public static final int CL_INVALID_SAMPLER = -41;
/*      */   public static final int CL_INVALID_BINARY = -42;
/*      */   public static final int CL_INVALID_BUILD_OPTIONS = -43;
/*      */   public static final int CL_INVALID_PROGRAM = -44;
/*      */   public static final int CL_INVALID_PROGRAM_EXECUTABLE = -45;
/*      */   public static final int CL_INVALID_KERNEL_NAME = -46;
/*      */   public static final int CL_INVALID_KERNEL_DEFINITION = -47;
/*      */   public static final int CL_INVALID_KERNEL = -48;
/*      */   public static final int CL_INVALID_ARG_INDEX = -49;
/*      */   public static final int CL_INVALID_ARG_VALUE = -50;
/*      */   public static final int CL_INVALID_ARG_SIZE = -51;
/*      */   public static final int CL_INVALID_KERNEL_ARGS = -52;
/*      */   public static final int CL_INVALID_WORK_DIMENSION = -53;
/*      */   public static final int CL_INVALID_WORK_GROUP_SIZE = -54;
/*      */   public static final int CL_INVALID_WORK_ITEM_SIZE = -55;
/*      */   public static final int CL_INVALID_GLOBAL_OFFSET = -56;
/*      */   public static final int CL_INVALID_EVENT_WAIT_LIST = -57;
/*      */   public static final int CL_INVALID_EVENT = -58;
/*      */   public static final int CL_INVALID_OPERATION = -59;
/*      */   public static final int CL_INVALID_GL_OBJECT = -60;
/*      */   public static final int CL_INVALID_BUFFER_SIZE = -61;
/*      */   public static final int CL_INVALID_MIP_LEVEL = -62;
/*      */   public static final int CL_INVALID_GLOBAL_WORK_SIZE = -63;
/*      */   public static final int CL_VERSION_1_0 = 1;
/*      */   public static final int CL_FALSE = 0;
/*      */   public static final int CL_TRUE = 1;
/*      */   public static final int CL_PLATFORM_PROFILE = 2304;
/*      */   public static final int CL_PLATFORM_VERSION = 2305;
/*      */   public static final int CL_PLATFORM_NAME = 2306;
/*      */   public static final int CL_PLATFORM_VENDOR = 2307;
/*      */   public static final int CL_PLATFORM_EXTENSIONS = 2308;
/*      */   public static final int CL_DEVICE_TYPE_DEFAULT = 1;
/*      */   public static final int CL_DEVICE_TYPE_CPU = 2;
/*      */   public static final int CL_DEVICE_TYPE_GPU = 4;
/*      */   public static final int CL_DEVICE_TYPE_ACCELERATOR = 8;
/*      */   public static final int CL_DEVICE_TYPE_ALL = -1;
/*      */   public static final int CL_DEVICE_TYPE = 4096;
/*      */   public static final int CL_DEVICE_VENDOR_ID = 4097;
/*      */   public static final int CL_DEVICE_MAX_COMPUTE_UNITS = 4098;
/*      */   public static final int CL_DEVICE_MAX_WORK_ITEM_DIMENSIONS = 4099;
/*      */   public static final int CL_DEVICE_MAX_WORK_GROUP_SIZE = 4100;
/*      */   public static final int CL_DEVICE_MAX_WORK_ITEM_SIZES = 4101;
/*      */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_CHAR = 4102;
/*      */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_SHORT = 4103;
/*      */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_ = 4104;
/*      */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_LONG = 4105;
/*      */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_FLOAT = 4106;
/*      */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_DOUBLE = 4107;
/*      */   public static final int CL_DEVICE_MAX_CLOCK_FREQUENCY = 4108;
/*      */   public static final int CL_DEVICE_ADDRESS_BITS = 4109;
/*      */   public static final int CL_DEVICE_MAX_READ_IMAGE_ARGS = 4110;
/*      */   public static final int CL_DEVICE_MAX_WRITE_IMAGE_ARGS = 4111;
/*      */   public static final int CL_DEVICE_MAX_MEM_ALLOC_SIZE = 4112;
/*      */   public static final int CL_DEVICE_IMAGE2D_MAX_WIDTH = 4113;
/*      */   public static final int CL_DEVICE_IMAGE2D_MAX_HEIGHT = 4114;
/*      */   public static final int CL_DEVICE_IMAGE3D_MAX_WIDTH = 4115;
/*      */   public static final int CL_DEVICE_IMAGE3D_MAX_HEIGHT = 4116;
/*      */   public static final int CL_DEVICE_IMAGE3D_MAX_DEPTH = 4117;
/*      */   public static final int CL_DEVICE_IMAGE_SUPPORT = 4118;
/*      */   public static final int CL_DEVICE_MAX_PARAMETER_SIZE = 4119;
/*      */   public static final int CL_DEVICE_MAX_SAMPLERS = 4120;
/*      */   public static final int CL_DEVICE_MEM_BASE_ADDR_ALIGN = 4121;
/*      */   public static final int CL_DEVICE_MIN_DATA_TYPE_ALIGN_SIZE = 4122;
/*      */   public static final int CL_DEVICE_SINGLE_FP_CONFIG = 4123;
/*      */   public static final int CL_DEVICE_GLOBAL_MEM_CACHE_TYPE = 4124;
/*      */   public static final int CL_DEVICE_GLOBAL_MEM_CACHELINE_SIZE = 4125;
/*      */   public static final int CL_DEVICE_GLOBAL_MEM_CACHE_SIZE = 4126;
/*      */   public static final int CL_DEVICE_GLOBAL_MEM_SIZE = 4127;
/*      */   public static final int CL_DEVICE_MAX_CONSTANT_BUFFER_SIZE = 4128;
/*      */   public static final int CL_DEVICE_MAX_CONSTANT_ARGS = 4129;
/*      */   public static final int CL_DEVICE_LOCAL_MEM_TYPE = 4130;
/*      */   public static final int CL_DEVICE_LOCAL_MEM_SIZE = 4131;
/*      */   public static final int CL_DEVICE_ERROR_CORRECTION_SUPPORT = 4132;
/*      */   public static final int CL_DEVICE_PROFILING_TIMER_RESOLUTION = 4133;
/*      */   public static final int CL_DEVICE_ENDIAN_LITTLE = 4134;
/*      */   public static final int CL_DEVICE_AVAILABLE = 4135;
/*      */   public static final int CL_DEVICE_COMPILER_AVAILABLE = 4136;
/*      */   public static final int CL_DEVICE_EXECUTION_CAPABILITIES = 4137;
/*      */   public static final int CL_DEVICE_QUEUE_PROPERTIES = 4138;
/*      */   public static final int CL_DEVICE_NAME = 4139;
/*      */   public static final int CL_DEVICE_VENDOR = 4140;
/*      */   public static final int CL_DRIVER_VERSION = 4141;
/*      */   public static final int CL_DEVICE_PROFILE = 4142;
/*      */   public static final int CL_DEVICE_VERSION = 4143;
/*      */   public static final int CL_DEVICE_EXTENSIONS = 4144;
/*      */   public static final int CL_DEVICE_PLATFORM = 4145;
/*      */   public static final int CL_FP_DENORM = 1;
/*      */   public static final int CL_FP_INF_NAN = 2;
/*      */   public static final int CL_FP_ROUND_TO_NEAREST = 4;
/*      */   public static final int CL_FP_ROUND_TO_ZERO = 8;
/*      */   public static final int CL_FP_ROUND_TO_INF = 16;
/*      */   public static final int CL_FP_FMA = 32;
/*      */   public static final int CL_NONE = 0;
/*      */   public static final int CL_READ_ONLY_CACHE = 1;
/*      */   public static final int CL_READ_WRITE_CACHE = 2;
/*      */   public static final int CL_LOCAL = 1;
/*      */   public static final int CL_GLOBAL = 2;
/*      */   public static final int CL_EXEC_KERNEL = 1;
/*      */   public static final int CL_EXEC_NATIVE_KERNEL = 2;
/*      */   public static final int CL_QUEUE_OUT_OF_ORDER_EXEC_MODE_ENABLE = 1;
/*      */   public static final int CL_QUEUE_PROFILING_ENABLE = 2;
/*      */   public static final int CL_CONTEXT_REFERENCE_COUNT = 4224;
/*      */   public static final int CL_CONTEXT_DEVICES = 4225;
/*      */   public static final int CL_CONTEXT_PROPERTIES = 4226;
/*      */   public static final int CL_CONTEXT_PLATFORM = 4228;
/*      */   public static final int CL_QUEUE_CONTEXT = 4240;
/*      */   public static final int CL_QUEUE_DEVICE = 4241;
/*      */   public static final int CL_QUEUE_REFERENCE_COUNT = 4242;
/*      */   public static final int CL_QUEUE_PROPERTIES = 4243;
/*      */   public static final int CL_MEM_READ_WRITE = 1;
/*      */   public static final int CL_MEM_WRITE_ONLY = 2;
/*      */   public static final int CL_MEM_READ_ONLY = 4;
/*      */   public static final int CL_MEM_USE_HOST_PTR = 8;
/*      */   public static final int CL_MEM_ALLOC_HOST_PTR = 16;
/*      */   public static final int CL_MEM_COPY_HOST_PTR = 32;
/*      */   public static final int CL_R = 4272;
/*      */   public static final int CL_A = 4273;
/*      */   public static final int CL_RG = 4274;
/*      */   public static final int CL_RA = 4275;
/*      */   public static final int CL_RGB = 4276;
/*      */   public static final int CL_RGBA = 4277;
/*      */   public static final int CL_BGRA = 4278;
/*      */   public static final int CL_ARGB = 4279;
/*      */   public static final int CL_INTENSITY = 4280;
/*      */   public static final int CL_LUMINANCE = 4281;
/*      */   public static final int CL_SNORM_INT8 = 4304;
/*      */   public static final int CL_SNORM_INT16 = 4305;
/*      */   public static final int CL_UNORM_INT8 = 4306;
/*      */   public static final int CL_UNORM_INT16 = 4307;
/*      */   public static final int CL_UNORM_SHORT_565 = 4308;
/*      */   public static final int CL_UNORM_SHORT_555 = 4309;
/*      */   public static final int CL_UNORM_INT_101010 = 4310;
/*      */   public static final int CL_SIGNED_INT8 = 4311;
/*      */   public static final int CL_SIGNED_INT16 = 4312;
/*      */   public static final int CL_SIGNED_INT32 = 4313;
/*      */   public static final int CL_UNSIGNED_INT8 = 4314;
/*      */   public static final int CL_UNSIGNED_INT16 = 4315;
/*      */   public static final int CL_UNSIGNED_INT32 = 4316;
/*      */   public static final int CL_HALF_FLOAT = 4317;
/*      */   public static final int CL_FLOAT = 4318;
/*      */   public static final int CL_MEM_OBJECT_BUFFER = 4336;
/*      */   public static final int CL_MEM_OBJECT_IMAGE2D = 4337;
/*      */   public static final int CL_MEM_OBJECT_IMAGE3D = 4338;
/*      */   public static final int CL_MEM_TYPE = 4352;
/*      */   public static final int CL_MEM_FLAGS = 4353;
/*      */   public static final int CL_MEM_SIZE = 4354;
/*      */   public static final int CL_MEM_HOST_PTR = 4355;
/*      */   public static final int CL_MEM_MAP_COUNT = 4356;
/*      */   public static final int CL_MEM_REFERENCE_COUNT = 4357;
/*      */   public static final int CL_MEM_CONTEXT = 4358;
/*      */   public static final int CL_IMAGE_FORMAT = 4368;
/*      */   public static final int CL_IMAGE_ELEMENT_SIZE = 4369;
/*      */   public static final int CL_IMAGE_ROW_PITCH = 4370;
/*      */   public static final int CL_IMAGE_SLICE_PITCH = 4371;
/*      */   public static final int CL_IMAGE_WIDTH = 4372;
/*      */   public static final int CL_IMAGE_HEIGHT = 4373;
/*      */   public static final int CL_IMAGE_DEPTH = 4374;
/*      */   public static final int CL_ADDRESS_NONE = 4400;
/*      */   public static final int CL_ADDRESS_CLAMP_TO_EDGE = 4401;
/*      */   public static final int CL_ADDRESS_CLAMP = 4402;
/*      */   public static final int CL_ADDRESS_REPEAT = 4403;
/*      */   public static final int CL_FILTER_NEAREST = 4416;
/*      */   public static final int CL_FILTER_LINEAR = 4417;
/*      */   public static final int CL_SAMPLER_REFERENCE_COUNT = 4432;
/*      */   public static final int CL_SAMPLER_CONTEXT = 4433;
/*      */   public static final int CL_SAMPLER_NORMALIZED_COORDS = 4434;
/*      */   public static final int CL_SAMPLER_ADDRESSING_MODE = 4435;
/*      */   public static final int CL_SAMPLER_FILTER_MODE = 4436;
/*      */   public static final int CL_MAP_READ = 1;
/*      */   public static final int CL_MAP_WRITE = 2;
/*      */   public static final int CL_PROGRAM_REFERENCE_COUNT = 4448;
/*      */   public static final int CL_PROGRAM_CONTEXT = 4449;
/*      */   public static final int CL_PROGRAM_NUM_DEVICES = 4450;
/*      */   public static final int CL_PROGRAM_DEVICES = 4451;
/*      */   public static final int CL_PROGRAM_SOURCE = 4452;
/*      */   public static final int CL_PROGRAM_BINARY_SIZES = 4453;
/*      */   public static final int CL_PROGRAM_BINARIES = 4454;
/*      */   public static final int CL_PROGRAM_BUILD_STATUS = 4481;
/*      */   public static final int CL_PROGRAM_BUILD_OPTIONS = 4482;
/*      */   public static final int CL_PROGRAM_BUILD_LOG = 4483;
/*      */   public static final int CL_BUILD_SUCCESS = 0;
/*      */   public static final int CL_BUILD_NONE = -1;
/*      */   public static final int CL_BUILD_ERROR = -2;
/*      */   public static final int CL_BUILD_IN_PROGRESS = -3;
/*      */   public static final int CL_KERNEL_FUNCTION_NAME = 4496;
/*      */   public static final int CL_KERNEL_NUM_ARGS = 4497;
/*      */   public static final int CL_KERNEL_REFERENCE_COUNT = 4498;
/*      */   public static final int CL_KERNEL_CONTEXT = 4499;
/*      */   public static final int CL_KERNEL_PROGRAM = 4500;
/*      */   public static final int CL_KERNEL_WORK_GROUP_SIZE = 4528;
/*      */   public static final int CL_KERNEL_COMPILE_WORK_GROUP_SIZE = 4529;
/*      */   public static final int CL_KERNEL_LOCAL_MEM_SIZE = 4530;
/*      */   public static final int CL_EVENT_COMMAND_QUEUE = 4560;
/*      */   public static final int CL_EVENT_COMMAND_TYPE = 4561;
/*      */   public static final int CL_EVENT_REFERENCE_COUNT = 4562;
/*      */   public static final int CL_EVENT_COMMAND_EXECUTION_STATUS = 4563;
/*      */   public static final int CL_COMMAND_NDRANGE_KERNEL = 4592;
/*      */   public static final int CL_COMMAND_TASK = 4593;
/*      */   public static final int CL_COMMAND_NATIVE_KERNEL = 4594;
/*      */   public static final int CL_COMMAND_READ_BUFFER = 4595;
/*      */   public static final int CL_COMMAND_WRITE_BUFFER = 4596;
/*      */   public static final int CL_COMMAND_COPY_BUFFER = 4597;
/*      */   public static final int CL_COMMAND_READ_IMAGE = 4598;
/*      */   public static final int CL_COMMAND_WRITE_IMAGE = 4599;
/*      */   public static final int CL_COMMAND_COPY_IMAGE = 4600;
/*      */   public static final int CL_COMMAND_COPY_IMAGE_TO_BUFFER = 4601;
/*      */   public static final int CL_COMMAND_COPY_BUFFER_TO_IMAGE = 4602;
/*      */   public static final int CL_COMMAND_MAP_BUFFER = 4603;
/*      */   public static final int CL_COMMAND_MAP_IMAGE = 4604;
/*      */   public static final int CL_COMMAND_UNMAP_MEM_OBJECT = 4605;
/*      */   public static final int CL_COMMAND_MARKER = 4606;
/*      */   public static final int CL_COMMAND_ACQUIRE_GL_OBJECTS = 4607;
/*      */   public static final int CL_COMMAND_RELEASE_GL_OBJECTS = 4608;
/*      */   public static final int CL_COMPLETE = 0;
/*      */   public static final int CL_RUNNING = 1;
/*      */   public static final int CL_SUBMITTED = 2;
/*      */   public static final int CL_QUEUED = 3;
/*      */   public static final int CL_PROFILING_COMMAND_QUEUED = 4736;
/*      */   public static final int CL_PROFILING_COMMAND_SUBMIT = 4737;
/*      */   public static final int CL_PROFILING_COMMAND_START = 4738;
/*      */   public static final int CL_PROFILING_COMMAND_END = 4739;
/*      */ 
/*      */   public static int clGetPlatformIDs(PointerBuffer platforms, IntBuffer num_platforms)
/*      */   {
/*  393 */     long function_pointer = CLCapabilities.clGetPlatformIDs;
/*  394 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  395 */     if (platforms != null)
/*  396 */       BufferChecks.checkDirect(platforms);
/*  397 */     if (num_platforms != null)
/*  398 */       BufferChecks.checkBuffer(num_platforms, 1);
/*  399 */     if (num_platforms == null) num_platforms = APIUtil.getBufferInt();
/*  400 */     int __result = nclGetPlatformIDs(platforms == null ? 0 : platforms.remaining(), MemoryUtil.getAddressSafe(platforms), MemoryUtil.getAddressSafe(num_platforms), function_pointer);
/*  401 */     if ((__result == 0) && (platforms != null)) CLPlatform.registerCLPlatforms(platforms, num_platforms);
/*  402 */     return __result;
/*      */   }
/*      */   static native int nclGetPlatformIDs(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int clGetPlatformInfo(CLPlatform platform, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  407 */     long function_pointer = CLCapabilities.clGetPlatformInfo;
/*  408 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  409 */     if (param_value != null)
/*  410 */       BufferChecks.checkDirect(param_value);
/*  411 */     if (param_value_size_ret != null)
/*  412 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  413 */     int __result = nclGetPlatformInfo(platform == null ? 0L : platform.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  414 */     return __result;
/*      */   }
/*      */   static native int nclGetPlatformInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clGetDeviceIDs(CLPlatform platform, long device_type, PointerBuffer devices, IntBuffer num_devices) {
/*  419 */     long function_pointer = CLCapabilities.clGetDeviceIDs;
/*  420 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  421 */     if (devices != null)
/*  422 */       BufferChecks.checkDirect(devices);
/*  423 */     if (num_devices != null)
/*  424 */       BufferChecks.checkBuffer(num_devices, 1);
/*      */     else
/*  426 */       num_devices = APIUtil.getBufferInt();
/*  427 */     int __result = nclGetDeviceIDs(platform.getPointer(), device_type, devices == null ? 0 : devices.remaining(), MemoryUtil.getAddressSafe(devices), MemoryUtil.getAddressSafe(num_devices), function_pointer);
/*  428 */     if ((__result == 0) && (devices != null)) platform.registerCLDevices(devices, num_devices);
/*  429 */     return __result;
/*      */   }
/*      */   static native int nclGetDeviceIDs(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clGetDeviceInfo(CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  434 */     long function_pointer = CLCapabilities.clGetDeviceInfo;
/*  435 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  436 */     if (param_value != null)
/*  437 */       BufferChecks.checkDirect(param_value);
/*  438 */     if (param_value_size_ret != null)
/*  439 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  440 */     int __result = nclGetDeviceInfo(device.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  441 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nclGetDeviceInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLContext clCreateContext(PointerBuffer properties, PointerBuffer devices, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*      */   {
/*  449 */     long function_pointer = CLCapabilities.clCreateContext;
/*  450 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  451 */     BufferChecks.checkBuffer(properties, 3);
/*  452 */     BufferChecks.checkNullTerminated(properties);
/*  453 */     BufferChecks.checkBuffer(devices, 1);
/*  454 */     if (errcode_ret != null)
/*  455 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  456 */     long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/*  457 */     CLContext __result = null;
/*      */     try {
/*  459 */       __result = new CLContext(nclCreateContext(MemoryUtil.getAddress(properties), devices.remaining(), MemoryUtil.getAddress(devices), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), APIUtil.getCLPlatform(properties));
/*  460 */       return __result;
/*      */     } finally {
/*  462 */       if (__result != null) __result.setContextCallback(user_data);
/*      */     }
/*      */   }
/*      */ 
/*      */   static native long nclCreateContext(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static CLContext clCreateContext(PointerBuffer properties, CLDevice device, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*      */   {
/*  473 */     long function_pointer = CLCapabilities.clCreateContext;
/*  474 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  475 */     BufferChecks.checkBuffer(properties, 3);
/*  476 */     BufferChecks.checkNullTerminated(properties);
/*  477 */     if (errcode_ret != null)
/*  478 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  479 */     long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/*  480 */     CLContext __result = null;
/*      */     try {
/*  482 */       __result = new CLContext(nclCreateContext(MemoryUtil.getAddress(properties), 1, APIUtil.getPointer(device), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), APIUtil.getCLPlatform(properties));
/*  483 */       return __result;
/*      */     } finally {
/*  485 */       if (__result != null) __result.setContextCallback(user_data);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static CLContext clCreateContextFromType(PointerBuffer properties, long device_type, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*      */   {
/*  493 */     long function_pointer = CLCapabilities.clCreateContextFromType;
/*  494 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  495 */     BufferChecks.checkBuffer(properties, 3);
/*  496 */     BufferChecks.checkNullTerminated(properties);
/*  497 */     if (errcode_ret != null)
/*  498 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  499 */     long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/*  500 */     CLContext __result = null;
/*      */     try {
/*  502 */       __result = new CLContext(nclCreateContextFromType(MemoryUtil.getAddress(properties), device_type, pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), APIUtil.getCLPlatform(properties));
/*  503 */       return __result;
/*      */     } finally {
/*  505 */       if (__result != null) __result.setContextCallback(user_data); 
/*      */     }
/*      */   }
/*      */ 
/*      */   static native long nclCreateContextFromType(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*  511 */   public static int clRetainContext(CLContext context) { long function_pointer = CLCapabilities.clRetainContext;
/*  512 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  513 */     int __result = nclRetainContext(context.getPointer(), function_pointer);
/*  514 */     if (__result == 0) context.retain();
/*  515 */     return __result; }
/*      */ 
/*      */   static native int nclRetainContext(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseContext(CLContext context) {
/*  520 */     long function_pointer = CLCapabilities.clReleaseContext;
/*  521 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  522 */     APIUtil.releaseObjects(context);
/*  523 */     int __result = nclReleaseContext(context.getPointer(), function_pointer);
/*  524 */     if (__result == 0) context.releaseImpl();
/*  525 */     return __result;
/*      */   }
/*      */   static native int nclReleaseContext(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clGetContextInfo(CLContext context, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  530 */     long function_pointer = CLCapabilities.clGetContextInfo;
/*  531 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  532 */     if (param_value != null)
/*  533 */       BufferChecks.checkDirect(param_value);
/*  534 */     if (param_value_size_ret != null)
/*  535 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  536 */     if ((param_value_size_ret == null) && (APIUtil.isDevicesParam(param_name))) param_value_size_ret = APIUtil.getBufferPointer();
/*  537 */     int __result = nclGetContextInfo(context.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  538 */     if ((__result == 0) && (param_value != null) && (APIUtil.isDevicesParam(param_name))) ((CLPlatform)context.getParent()).registerCLDevices(param_value, param_value_size_ret);
/*  539 */     return __result;
/*      */   }
/*      */   static native int nclGetContextInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLCommandQueue clCreateCommandQueue(CLContext context, CLDevice device, long properties, IntBuffer errcode_ret) {
/*  544 */     long function_pointer = CLCapabilities.clCreateCommandQueue;
/*  545 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  546 */     if (errcode_ret != null)
/*  547 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  548 */     CLCommandQueue __result = new CLCommandQueue(nclCreateCommandQueue(context.getPointer(), device.getPointer(), properties, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context, device);
/*  549 */     return __result;
/*      */   }
/*      */   static native long nclCreateCommandQueue(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clRetainCommandQueue(CLCommandQueue command_queue) {
/*  554 */     long function_pointer = CLCapabilities.clRetainCommandQueue;
/*  555 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  556 */     int __result = nclRetainCommandQueue(command_queue.getPointer(), function_pointer);
/*  557 */     if (__result == 0) command_queue.retain();
/*  558 */     return __result;
/*      */   }
/*      */   static native int nclRetainCommandQueue(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseCommandQueue(CLCommandQueue command_queue) {
/*  563 */     long function_pointer = CLCapabilities.clReleaseCommandQueue;
/*  564 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  565 */     APIUtil.releaseObjects(command_queue);
/*  566 */     int __result = nclReleaseCommandQueue(command_queue.getPointer(), function_pointer);
/*  567 */     if (__result == 0) command_queue.release();
/*  568 */     return __result;
/*      */   }
/*      */   static native int nclReleaseCommandQueue(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clGetCommandQueueInfo(CLCommandQueue command_queue, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  573 */     long function_pointer = CLCapabilities.clGetCommandQueueInfo;
/*  574 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  575 */     if (param_value != null)
/*  576 */       BufferChecks.checkDirect(param_value);
/*  577 */     if (param_value_size_ret != null)
/*  578 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  579 */     int __result = nclGetCommandQueueInfo(command_queue.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  580 */     return __result;
/*      */   }
/*      */   static native int nclGetCommandQueueInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, long host_ptr_size, IntBuffer errcode_ret) {
/*  585 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  586 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  587 */     if (errcode_ret != null)
/*  588 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  589 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr_size, 0L, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  590 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, ByteBuffer host_ptr, IntBuffer errcode_ret) {
/*  593 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  594 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  595 */     BufferChecks.checkDirect(host_ptr);
/*  596 */     if (errcode_ret != null)
/*  597 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  598 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining(), MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  599 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, DoubleBuffer host_ptr, IntBuffer errcode_ret) {
/*  602 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  603 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  604 */     BufferChecks.checkDirect(host_ptr);
/*  605 */     if (errcode_ret != null)
/*  606 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  607 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 3, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  608 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, FloatBuffer host_ptr, IntBuffer errcode_ret) {
/*  611 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  612 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  613 */     BufferChecks.checkDirect(host_ptr);
/*  614 */     if (errcode_ret != null)
/*  615 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  616 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 2, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  617 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, IntBuffer host_ptr, IntBuffer errcode_ret) {
/*  620 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  621 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  622 */     BufferChecks.checkDirect(host_ptr);
/*  623 */     if (errcode_ret != null)
/*  624 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  625 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 2, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  626 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, LongBuffer host_ptr, IntBuffer errcode_ret) {
/*  629 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  630 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  631 */     BufferChecks.checkDirect(host_ptr);
/*  632 */     if (errcode_ret != null)
/*  633 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  634 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 3, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  635 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateBuffer(CLContext context, long flags, ShortBuffer host_ptr, IntBuffer errcode_ret) {
/*  638 */     long function_pointer = CLCapabilities.clCreateBuffer;
/*  639 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  640 */     BufferChecks.checkDirect(host_ptr);
/*  641 */     if (errcode_ret != null)
/*  642 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  643 */     CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 1, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  644 */     return __result;
/*      */   }
/*      */   static native long nclCreateBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  649 */     long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  650 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  651 */     BufferChecks.checkDirect(ptr);
/*  652 */     if (event_wait_list != null)
/*  653 */       BufferChecks.checkDirect(event_wait_list);
/*  654 */     if (event != null)
/*  655 */       BufferChecks.checkBuffer(event, 1);
/*  656 */     int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining(), MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  657 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  658 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, DoubleBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  661 */     long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  662 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  663 */     BufferChecks.checkDirect(ptr);
/*  664 */     if (event_wait_list != null)
/*  665 */       BufferChecks.checkDirect(event_wait_list);
/*  666 */     if (event != null)
/*  667 */       BufferChecks.checkBuffer(event, 1);
/*  668 */     int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  669 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  670 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  673 */     long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  674 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  675 */     BufferChecks.checkDirect(ptr);
/*  676 */     if (event_wait_list != null)
/*  677 */       BufferChecks.checkDirect(event_wait_list);
/*  678 */     if (event != null)
/*  679 */       BufferChecks.checkBuffer(event, 1);
/*  680 */     int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  681 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  682 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  685 */     long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  686 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  687 */     BufferChecks.checkDirect(ptr);
/*  688 */     if (event_wait_list != null)
/*  689 */       BufferChecks.checkDirect(event_wait_list);
/*  690 */     if (event != null)
/*  691 */       BufferChecks.checkBuffer(event, 1);
/*  692 */     int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  693 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  694 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, LongBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  697 */     long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  698 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  699 */     BufferChecks.checkDirect(ptr);
/*  700 */     if (event_wait_list != null)
/*  701 */       BufferChecks.checkDirect(event_wait_list);
/*  702 */     if (event != null)
/*  703 */       BufferChecks.checkBuffer(event, 1);
/*  704 */     int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  705 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  706 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  709 */     long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  710 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  711 */     BufferChecks.checkDirect(ptr);
/*  712 */     if (event_wait_list != null)
/*  713 */       BufferChecks.checkDirect(event_wait_list);
/*  714 */     if (event != null)
/*  715 */       BufferChecks.checkBuffer(event, 1);
/*  716 */     int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 1, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  717 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  718 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueReadBuffer(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8);
/*      */ 
/*      */   public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  723 */     long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  724 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  725 */     BufferChecks.checkDirect(ptr);
/*  726 */     if (event_wait_list != null)
/*  727 */       BufferChecks.checkDirect(event_wait_list);
/*  728 */     if (event != null)
/*  729 */       BufferChecks.checkBuffer(event, 1);
/*  730 */     int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining(), MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  731 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  732 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, DoubleBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  735 */     long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  736 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  737 */     BufferChecks.checkDirect(ptr);
/*  738 */     if (event_wait_list != null)
/*  739 */       BufferChecks.checkDirect(event_wait_list);
/*  740 */     if (event != null)
/*  741 */       BufferChecks.checkBuffer(event, 1);
/*  742 */     int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  743 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  744 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  747 */     long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  748 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  749 */     BufferChecks.checkDirect(ptr);
/*  750 */     if (event_wait_list != null)
/*  751 */       BufferChecks.checkDirect(event_wait_list);
/*  752 */     if (event != null)
/*  753 */       BufferChecks.checkBuffer(event, 1);
/*  754 */     int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  755 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  756 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  759 */     long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  760 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  761 */     BufferChecks.checkDirect(ptr);
/*  762 */     if (event_wait_list != null)
/*  763 */       BufferChecks.checkDirect(event_wait_list);
/*  764 */     if (event != null)
/*  765 */       BufferChecks.checkBuffer(event, 1);
/*  766 */     int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  767 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  768 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, LongBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  771 */     long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  772 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  773 */     BufferChecks.checkDirect(ptr);
/*  774 */     if (event_wait_list != null)
/*  775 */       BufferChecks.checkDirect(event_wait_list);
/*  776 */     if (event != null)
/*  777 */       BufferChecks.checkBuffer(event, 1);
/*  778 */     int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  779 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  780 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  783 */     long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  784 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  785 */     BufferChecks.checkDirect(ptr);
/*  786 */     if (event_wait_list != null)
/*  787 */       BufferChecks.checkDirect(event_wait_list);
/*  788 */     if (event != null)
/*  789 */       BufferChecks.checkBuffer(event, 1);
/*  790 */     int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 1, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  791 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  792 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueWriteBuffer(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8);
/*      */ 
/*      */   public static int clEnqueueCopyBuffer(CLCommandQueue command_queue, CLMem src_buffer, CLMem dst_buffer, long src_offset, long dst_offset, long size, PointerBuffer event_wait_list, PointerBuffer event) {
/*  797 */     long function_pointer = CLCapabilities.clEnqueueCopyBuffer;
/*  798 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  799 */     if (event_wait_list != null)
/*  800 */       BufferChecks.checkDirect(event_wait_list);
/*  801 */     if (event != null)
/*  802 */       BufferChecks.checkBuffer(event, 1);
/*  803 */     int __result = nclEnqueueCopyBuffer(command_queue.getPointer(), src_buffer.getPointer(), dst_buffer.getPointer(), src_offset, dst_offset, size, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  804 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  805 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueCopyBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/*      */ 
/*      */   public static ByteBuffer clEnqueueMapBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_map, long map_flags, long offset, long size, PointerBuffer event_wait_list, PointerBuffer event, IntBuffer errcode_ret) {
/*  810 */     long function_pointer = CLCapabilities.clEnqueueMapBuffer;
/*  811 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  812 */     if (event_wait_list != null)
/*  813 */       BufferChecks.checkDirect(event_wait_list);
/*  814 */     if (event != null)
/*  815 */       BufferChecks.checkBuffer(event, 1);
/*  816 */     if (errcode_ret != null)
/*  817 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  818 */     ByteBuffer __result = nclEnqueueMapBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_map, map_flags, offset, size, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), MemoryUtil.getAddressSafe(errcode_ret), size, function_pointer);
/*  819 */     if (__result != null) command_queue.registerCLEvent(event);
/*  820 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nclEnqueueMapBuffer(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10);
/*      */ 
/*      */   public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, ByteBuffer host_ptr, IntBuffer errcode_ret) {
/*  825 */     long function_pointer = CLCapabilities.clCreateImage2D;
/*  826 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  827 */     BufferChecks.checkBuffer(image_format, 8);
/*  828 */     if (host_ptr != null)
/*  829 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  830 */     if (errcode_ret != null)
/*  831 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  832 */     CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  833 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, FloatBuffer host_ptr, IntBuffer errcode_ret) {
/*  836 */     long function_pointer = CLCapabilities.clCreateImage2D;
/*  837 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  838 */     BufferChecks.checkBuffer(image_format, 8);
/*  839 */     if (host_ptr != null)
/*  840 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  841 */     if (errcode_ret != null)
/*  842 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  843 */     CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  844 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, IntBuffer host_ptr, IntBuffer errcode_ret) {
/*  847 */     long function_pointer = CLCapabilities.clCreateImage2D;
/*  848 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  849 */     BufferChecks.checkBuffer(image_format, 8);
/*  850 */     if (host_ptr != null)
/*  851 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  852 */     if (errcode_ret != null)
/*  853 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  854 */     CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  855 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, ShortBuffer host_ptr, IntBuffer errcode_ret) {
/*  858 */     long function_pointer = CLCapabilities.clCreateImage2D;
/*  859 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  860 */     BufferChecks.checkBuffer(image_format, 8);
/*  861 */     if (host_ptr != null)
/*  862 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  863 */     if (errcode_ret != null)
/*  864 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  865 */     CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  866 */     return __result;
/*      */   }
/*      */   static native long nclCreateImage2D(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9);
/*      */ 
/*      */   public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, ByteBuffer host_ptr, IntBuffer errcode_ret) {
/*  871 */     long function_pointer = CLCapabilities.clCreateImage3D;
/*  872 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  873 */     BufferChecks.checkBuffer(image_format, 8);
/*  874 */     if (host_ptr != null)
/*  875 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  876 */     if (errcode_ret != null)
/*  877 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  878 */     CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  879 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, FloatBuffer host_ptr, IntBuffer errcode_ret) {
/*  882 */     long function_pointer = CLCapabilities.clCreateImage3D;
/*  883 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  884 */     BufferChecks.checkBuffer(image_format, 8);
/*  885 */     if (host_ptr != null)
/*  886 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  887 */     if (errcode_ret != null)
/*  888 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  889 */     CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  890 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, IntBuffer host_ptr, IntBuffer errcode_ret) {
/*  893 */     long function_pointer = CLCapabilities.clCreateImage3D;
/*  894 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  895 */     BufferChecks.checkBuffer(image_format, 8);
/*  896 */     if (host_ptr != null)
/*  897 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  898 */     if (errcode_ret != null)
/*  899 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  900 */     CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  901 */     return __result;
/*      */   }
/*      */   public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, ShortBuffer host_ptr, IntBuffer errcode_ret) {
/*  904 */     long function_pointer = CLCapabilities.clCreateImage3D;
/*  905 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  906 */     BufferChecks.checkBuffer(image_format, 8);
/*  907 */     if (host_ptr != null)
/*  908 */       BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  909 */     if (errcode_ret != null)
/*  910 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  911 */     CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  912 */     return __result;
/*      */   }
/*      */   static native long nclCreateImage3D(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10, long paramLong11);
/*      */ 
/*      */   public static int clGetSupportedImageFormats(CLContext context, long flags, int image_type, ByteBuffer image_formats, IntBuffer num_image_formats) {
/*  917 */     long function_pointer = CLCapabilities.clGetSupportedImageFormats;
/*  918 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  919 */     if (image_formats != null)
/*  920 */       BufferChecks.checkDirect(image_formats);
/*  921 */     if (num_image_formats != null)
/*  922 */       BufferChecks.checkBuffer(num_image_formats, 1);
/*  923 */     int __result = nclGetSupportedImageFormats(context.getPointer(), flags, image_type, (image_formats == null ? 0 : image_formats.remaining()) / 8, MemoryUtil.getAddressSafe(image_formats), MemoryUtil.getAddressSafe(num_image_formats), function_pointer);
/*  924 */     return __result;
/*      */   }
/*      */   static native int nclGetSupportedImageFormats(long paramLong1, long paramLong2, int paramInt1, int paramInt2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  929 */     long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  930 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  931 */     BufferChecks.checkBuffer(origin, 3);
/*  932 */     BufferChecks.checkBuffer(region, 3);
/*  933 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  934 */     if (event_wait_list != null)
/*  935 */       BufferChecks.checkDirect(event_wait_list);
/*  936 */     if (event != null)
/*  937 */       BufferChecks.checkBuffer(event, 1);
/*  938 */     int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  939 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  940 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  943 */     long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  944 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  945 */     BufferChecks.checkBuffer(origin, 3);
/*  946 */     BufferChecks.checkBuffer(region, 3);
/*  947 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  948 */     if (event_wait_list != null)
/*  949 */       BufferChecks.checkDirect(event_wait_list);
/*  950 */     if (event != null)
/*  951 */       BufferChecks.checkBuffer(event, 1);
/*  952 */     int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  953 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  954 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  957 */     long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  958 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  959 */     BufferChecks.checkBuffer(origin, 3);
/*  960 */     BufferChecks.checkBuffer(region, 3);
/*  961 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  962 */     if (event_wait_list != null)
/*  963 */       BufferChecks.checkDirect(event_wait_list);
/*  964 */     if (event != null)
/*  965 */       BufferChecks.checkBuffer(event, 1);
/*  966 */     int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  967 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  968 */     return __result;
/*      */   }
/*      */   public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  971 */     long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  972 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  973 */     BufferChecks.checkBuffer(origin, 3);
/*  974 */     BufferChecks.checkBuffer(region, 3);
/*  975 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  976 */     if (event_wait_list != null)
/*  977 */       BufferChecks.checkDirect(event_wait_list);
/*  978 */     if (event != null)
/*  979 */       BufferChecks.checkBuffer(event, 1);
/*  980 */     int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  981 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  982 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueReadImage(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt2, long paramLong8, long paramLong9, long paramLong10);
/*      */ 
/*      */   public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/*  987 */     long function_pointer = CLCapabilities.clEnqueueWriteImage;
/*  988 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  989 */     BufferChecks.checkBuffer(origin, 3);
/*  990 */     BufferChecks.checkBuffer(region, 3);
/*  991 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/*  992 */     if (event_wait_list != null)
/*  993 */       BufferChecks.checkDirect(event_wait_list);
/*  994 */     if (event != null)
/*  995 */       BufferChecks.checkBuffer(event, 1);
/*  996 */     int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  997 */     if (__result == 0) command_queue.registerCLEvent(event);
/*  998 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1001 */     long function_pointer = CLCapabilities.clEnqueueWriteImage;
/* 1002 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1003 */     BufferChecks.checkBuffer(origin, 3);
/* 1004 */     BufferChecks.checkBuffer(region, 3);
/* 1005 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/* 1006 */     if (event_wait_list != null)
/* 1007 */       BufferChecks.checkDirect(event_wait_list);
/* 1008 */     if (event != null)
/* 1009 */       BufferChecks.checkBuffer(event, 1);
/* 1010 */     int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1011 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1012 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1015 */     long function_pointer = CLCapabilities.clEnqueueWriteImage;
/* 1016 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1017 */     BufferChecks.checkBuffer(origin, 3);
/* 1018 */     BufferChecks.checkBuffer(region, 3);
/* 1019 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/* 1020 */     if (event_wait_list != null)
/* 1021 */       BufferChecks.checkDirect(event_wait_list);
/* 1022 */     if (event != null)
/* 1023 */       BufferChecks.checkBuffer(event, 1);
/* 1024 */     int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1025 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1026 */     return __result;
/*      */   }
/*      */   public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1029 */     long function_pointer = CLCapabilities.clEnqueueWriteImage;
/* 1030 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1031 */     BufferChecks.checkBuffer(origin, 3);
/* 1032 */     BufferChecks.checkBuffer(region, 3);
/* 1033 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/* 1034 */     if (event_wait_list != null)
/* 1035 */       BufferChecks.checkDirect(event_wait_list);
/* 1036 */     if (event != null)
/* 1037 */       BufferChecks.checkBuffer(event, 1);
/* 1038 */     int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1039 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1040 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueWriteImage(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt2, long paramLong8, long paramLong9, long paramLong10);
/*      */ 
/*      */   public static int clEnqueueCopyImage(CLCommandQueue command_queue, CLMem src_image, CLMem dst_image, PointerBuffer src_origin, PointerBuffer dst_origin, PointerBuffer region, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1045 */     long function_pointer = CLCapabilities.clEnqueueCopyImage;
/* 1046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1047 */     BufferChecks.checkBuffer(src_origin, 3);
/* 1048 */     BufferChecks.checkBuffer(dst_origin, 3);
/* 1049 */     BufferChecks.checkBuffer(region, 3);
/* 1050 */     if (event_wait_list != null)
/* 1051 */       BufferChecks.checkDirect(event_wait_list);
/* 1052 */     if (event != null)
/* 1053 */       BufferChecks.checkBuffer(event, 1);
/* 1054 */     int __result = nclEnqueueCopyImage(command_queue.getPointer(), src_image.getPointer(), dst_image.getPointer(), MemoryUtil.getAddress(src_origin), MemoryUtil.getAddress(dst_origin), MemoryUtil.getAddress(region), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1055 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1056 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueCopyImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/*      */ 
/*      */   public static int clEnqueueCopyImageToBuffer(CLCommandQueue command_queue, CLMem src_image, CLMem dst_buffer, PointerBuffer src_origin, PointerBuffer region, long dst_offset, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1061 */     long function_pointer = CLCapabilities.clEnqueueCopyImageToBuffer;
/* 1062 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1063 */     BufferChecks.checkBuffer(src_origin, 3);
/* 1064 */     BufferChecks.checkBuffer(region, 3);
/* 1065 */     if (event_wait_list != null)
/* 1066 */       BufferChecks.checkDirect(event_wait_list);
/* 1067 */     if (event != null)
/* 1068 */       BufferChecks.checkBuffer(event, 1);
/* 1069 */     int __result = nclEnqueueCopyImageToBuffer(command_queue.getPointer(), src_image.getPointer(), dst_buffer.getPointer(), MemoryUtil.getAddress(src_origin), MemoryUtil.getAddress(region), dst_offset, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1070 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1071 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueCopyImageToBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/*      */ 
/*      */   public static int clEnqueueCopyBufferToImage(CLCommandQueue command_queue, CLMem src_buffer, CLMem dst_image, long src_offset, PointerBuffer dst_origin, PointerBuffer region, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1076 */     long function_pointer = CLCapabilities.clEnqueueCopyBufferToImage;
/* 1077 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1078 */     BufferChecks.checkBuffer(dst_origin, 3);
/* 1079 */     BufferChecks.checkBuffer(region, 3);
/* 1080 */     if (event_wait_list != null)
/* 1081 */       BufferChecks.checkDirect(event_wait_list);
/* 1082 */     if (event != null)
/* 1083 */       BufferChecks.checkBuffer(event, 1);
/* 1084 */     int __result = nclEnqueueCopyBufferToImage(command_queue.getPointer(), src_buffer.getPointer(), dst_image.getPointer(), src_offset, MemoryUtil.getAddress(dst_origin), MemoryUtil.getAddress(region), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1085 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1086 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueCopyBufferToImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/*      */ 
/*      */   public static ByteBuffer clEnqueueMapImage(CLCommandQueue command_queue, CLMem image, int blocking_map, long map_flags, PointerBuffer origin, PointerBuffer region, PointerBuffer image_row_pitch, PointerBuffer image_slice_pitch, PointerBuffer event_wait_list, PointerBuffer event, IntBuffer errcode_ret) {
/* 1091 */     long function_pointer = CLCapabilities.clEnqueueMapImage;
/* 1092 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1093 */     BufferChecks.checkBuffer(origin, 3);
/* 1094 */     BufferChecks.checkBuffer(region, 3);
/* 1095 */     BufferChecks.checkBuffer(image_row_pitch, 1);
/* 1096 */     if (image_slice_pitch != null)
/* 1097 */       BufferChecks.checkBuffer(image_slice_pitch, 1);
/* 1098 */     if (event_wait_list != null)
/* 1099 */       BufferChecks.checkDirect(event_wait_list);
/* 1100 */     if (event != null)
/* 1101 */       BufferChecks.checkBuffer(event, 1);
/* 1102 */     if (errcode_ret != null)
/* 1103 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1104 */     ByteBuffer __result = nclEnqueueMapImage(command_queue.getPointer(), image.getPointer(), blocking_map, map_flags, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), MemoryUtil.getAddress(image_row_pitch), MemoryUtil.getAddressSafe(image_slice_pitch), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), MemoryUtil.getAddressSafe(errcode_ret), function_pointer);
/* 1105 */     if (__result != null) command_queue.registerCLEvent(event);
/* 1106 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nclEnqueueMapImage(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt2, long paramLong8, long paramLong9, long paramLong10, long paramLong11);
/*      */ 
/*      */   public static int clGetImageInfo(CLMem image, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1111 */     long function_pointer = CLCapabilities.clGetImageInfo;
/* 1112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1113 */     if (param_value != null)
/* 1114 */       BufferChecks.checkDirect(param_value);
/* 1115 */     if (param_value_size_ret != null)
/* 1116 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1117 */     int __result = nclGetImageInfo(image.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1118 */     return __result;
/*      */   }
/*      */   static native int nclGetImageInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clRetainMemObject(CLMem memobj) {
/* 1123 */     long function_pointer = CLCapabilities.clRetainMemObject;
/* 1124 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1125 */     int __result = nclRetainMemObject(memobj.getPointer(), function_pointer);
/* 1126 */     if (__result == 0) memobj.retain();
/* 1127 */     return __result;
/*      */   }
/*      */   static native int nclRetainMemObject(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseMemObject(CLMem memobj) {
/* 1132 */     long function_pointer = CLCapabilities.clReleaseMemObject;
/* 1133 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1134 */     int __result = nclReleaseMemObject(memobj.getPointer(), function_pointer);
/* 1135 */     if (__result == 0) memobj.release();
/* 1136 */     return __result;
/*      */   }
/*      */   static native int nclReleaseMemObject(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clEnqueueUnmapMemObject(CLCommandQueue command_queue, CLMem memobj, ByteBuffer mapped_ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1141 */     long function_pointer = CLCapabilities.clEnqueueUnmapMemObject;
/* 1142 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1143 */     BufferChecks.checkDirect(mapped_ptr);
/* 1144 */     if (event_wait_list != null)
/* 1145 */       BufferChecks.checkDirect(event_wait_list);
/* 1146 */     if (event != null)
/* 1147 */       BufferChecks.checkBuffer(event, 1);
/* 1148 */     int __result = nclEnqueueUnmapMemObject(command_queue.getPointer(), memobj.getPointer(), MemoryUtil.getAddress(mapped_ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1149 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1150 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueUnmapMemObject(long paramLong1, long paramLong2, long paramLong3, int paramInt, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static int clGetMemObjectInfo(CLMem memobj, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1155 */     long function_pointer = CLCapabilities.clGetMemObjectInfo;
/* 1156 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1157 */     if (param_value != null)
/* 1158 */       BufferChecks.checkDirect(param_value);
/* 1159 */     if (param_value_size_ret != null)
/* 1160 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1161 */     int __result = nclGetMemObjectInfo(memobj.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1162 */     return __result;
/*      */   }
/*      */   static native int nclGetMemObjectInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLSampler clCreateSampler(CLContext context, int normalized_coords, int addressing_mode, int filter_mode, IntBuffer errcode_ret) {
/* 1167 */     long function_pointer = CLCapabilities.clCreateSampler;
/* 1168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1169 */     if (errcode_ret != null)
/* 1170 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1171 */     CLSampler __result = new CLSampler(nclCreateSampler(context.getPointer(), normalized_coords, addressing_mode, filter_mode, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1172 */     return __result;
/*      */   }
/*      */   static native long nclCreateSampler(long paramLong1, int paramInt1, int paramInt2, int paramInt3, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int clRetainSampler(CLSampler sampler) {
/* 1177 */     long function_pointer = CLCapabilities.clRetainSampler;
/* 1178 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1179 */     int __result = nclRetainSampler(sampler.getPointer(), function_pointer);
/* 1180 */     if (__result == 0) sampler.retain();
/* 1181 */     return __result;
/*      */   }
/*      */   static native int nclRetainSampler(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseSampler(CLSampler sampler) {
/* 1186 */     long function_pointer = CLCapabilities.clReleaseSampler;
/* 1187 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1188 */     int __result = nclReleaseSampler(sampler.getPointer(), function_pointer);
/* 1189 */     if (__result == 0) sampler.release();
/* 1190 */     return __result;
/*      */   }
/*      */   static native int nclReleaseSampler(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clGetSamplerInfo(CLSampler sampler, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1195 */     long function_pointer = CLCapabilities.clGetSamplerInfo;
/* 1196 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1197 */     if (param_value != null)
/* 1198 */       BufferChecks.checkDirect(param_value);
/* 1199 */     if (param_value_size_ret != null)
/* 1200 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1201 */     int __result = nclGetSamplerInfo(sampler.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1202 */     return __result;
/*      */   }
/*      */   static native int nclGetSamplerInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithSource(CLContext context, ByteBuffer string, IntBuffer errcode_ret) {
/* 1207 */     long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1208 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1209 */     BufferChecks.checkDirect(string);
/* 1210 */     if (errcode_ret != null)
/* 1211 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1212 */     CLProgram __result = new CLProgram(nclCreateProgramWithSource(context.getPointer(), 1, MemoryUtil.getAddress(string), string.remaining(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1213 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclCreateProgramWithSource(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithSource(CLContext context, ByteBuffer strings, PointerBuffer lengths, IntBuffer errcode_ret) {
/* 1219 */     long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1220 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1221 */     BufferChecks.checkBuffer(strings, APIUtil.getSize(lengths));
/* 1222 */     BufferChecks.checkBuffer(lengths, 1);
/* 1223 */     if (errcode_ret != null)
/* 1224 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1225 */     CLProgram __result = new CLProgram(nclCreateProgramWithSource2(context.getPointer(), lengths.remaining(), MemoryUtil.getAddress(strings), MemoryUtil.getAddress(lengths), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1226 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclCreateProgramWithSource2(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithSource(CLContext context, ByteBuffer[] strings, IntBuffer errcode_ret) {
/* 1232 */     long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1233 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1234 */     BufferChecks.checkArray(strings, 1);
/* 1235 */     if (errcode_ret != null)
/* 1236 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1237 */     CLProgram __result = new CLProgram(nclCreateProgramWithSource3(context.getPointer(), strings.length, strings, APIUtil.getLengths(strings), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1238 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclCreateProgramWithSource3(long paramLong1, int paramInt, ByteBuffer[] paramArrayOfByteBuffer, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithSource(CLContext context, CharSequence string, IntBuffer errcode_ret) {
/* 1244 */     long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1246 */     if (errcode_ret != null)
/* 1247 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1248 */     CLProgram __result = new CLProgram(nclCreateProgramWithSource(context.getPointer(), 1, APIUtil.getBuffer(string), string.length(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1249 */     return __result;
/*      */   }
/*      */ 
/*      */   public static CLProgram clCreateProgramWithSource(CLContext context, CharSequence[] strings, IntBuffer errcode_ret)
/*      */   {
/* 1254 */     long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1255 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1256 */     BufferChecks.checkArray(strings);
/* 1257 */     if (errcode_ret != null)
/* 1258 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1259 */     CLProgram __result = new CLProgram(nclCreateProgramWithSource4(context.getPointer(), strings.length, APIUtil.getBuffer(strings), APIUtil.getLengths(strings), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1260 */     return __result;
/*      */   }
/*      */   static native long nclCreateProgramWithSource4(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithBinary(CLContext context, CLDevice device, ByteBuffer binary, IntBuffer binary_status, IntBuffer errcode_ret) {
/* 1265 */     long function_pointer = CLCapabilities.clCreateProgramWithBinary;
/* 1266 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1267 */     BufferChecks.checkDirect(binary);
/* 1268 */     BufferChecks.checkBuffer(binary_status, 1);
/* 1269 */     if (errcode_ret != null)
/* 1270 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1271 */     CLProgram __result = new CLProgram(nclCreateProgramWithBinary(context.getPointer(), 1, device.getPointer(), binary.remaining(), MemoryUtil.getAddress(binary), MemoryUtil.getAddress(binary_status), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1272 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclCreateProgramWithBinary(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithBinary(CLContext context, PointerBuffer device_list, PointerBuffer lengths, ByteBuffer binaries, IntBuffer binary_status, IntBuffer errcode_ret) {
/* 1278 */     long function_pointer = CLCapabilities.clCreateProgramWithBinary;
/* 1279 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1280 */     BufferChecks.checkBuffer(device_list, 1);
/* 1281 */     BufferChecks.checkBuffer(lengths, device_list.remaining());
/* 1282 */     BufferChecks.checkBuffer(binaries, APIUtil.getSize(lengths));
/* 1283 */     BufferChecks.checkBuffer(binary_status, device_list.remaining());
/* 1284 */     if (errcode_ret != null)
/* 1285 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1286 */     CLProgram __result = new CLProgram(nclCreateProgramWithBinary2(context.getPointer(), device_list.remaining(), MemoryUtil.getAddress(device_list), MemoryUtil.getAddress(lengths), MemoryUtil.getAddress(binaries), MemoryUtil.getAddress(binary_status), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1287 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclCreateProgramWithBinary2(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/*      */ 
/*      */   public static CLProgram clCreateProgramWithBinary(CLContext context, PointerBuffer device_list, ByteBuffer[] binaries, IntBuffer binary_status, IntBuffer errcode_ret) {
/* 1293 */     long function_pointer = CLCapabilities.clCreateProgramWithBinary;
/* 1294 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1295 */     BufferChecks.checkBuffer(device_list, binaries.length);
/* 1296 */     BufferChecks.checkArray(binaries, 1);
/* 1297 */     BufferChecks.checkBuffer(binary_status, binaries.length);
/* 1298 */     if (errcode_ret != null)
/* 1299 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1300 */     CLProgram __result = new CLProgram(nclCreateProgramWithBinary3(context.getPointer(), binaries.length, MemoryUtil.getAddress(device_list), APIUtil.getLengths(binaries), binaries, MemoryUtil.getAddress(binary_status), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1301 */     return __result;
/*      */   }
/*      */   static native long nclCreateProgramWithBinary3(long paramLong1, int paramInt, long paramLong2, long paramLong3, ByteBuffer[] paramArrayOfByteBuffer, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static int clRetainProgram(CLProgram program) {
/* 1306 */     long function_pointer = CLCapabilities.clRetainProgram;
/* 1307 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1308 */     int __result = nclRetainProgram(program.getPointer(), function_pointer);
/* 1309 */     if (__result == 0) program.retain();
/* 1310 */     return __result;
/*      */   }
/*      */   static native int nclRetainProgram(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseProgram(CLProgram program) {
/* 1315 */     long function_pointer = CLCapabilities.clReleaseProgram;
/* 1316 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1317 */     APIUtil.releaseObjects(program);
/* 1318 */     int __result = nclReleaseProgram(program.getPointer(), function_pointer);
/* 1319 */     if (__result == 0) program.release();
/* 1320 */     return __result;
/*      */   }
/*      */   static native int nclReleaseProgram(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clBuildProgram(CLProgram program, PointerBuffer device_list, ByteBuffer options, CLBuildProgramCallback pfn_notify) {
/* 1325 */     long function_pointer = CLCapabilities.clBuildProgram;
/* 1326 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1327 */     if (device_list != null)
/* 1328 */       BufferChecks.checkDirect(device_list);
/* 1329 */     BufferChecks.checkDirect(options);
/* 1330 */     BufferChecks.checkNullTerminated(options);
/* 1331 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 1332 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 1333 */     int __result = 0;
/*      */     try {
/* 1335 */       __result = nclBuildProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 1336 */       return __result;
/*      */     } finally {
/* 1338 */       CallbackUtil.checkCallback(__result, user_data);
/*      */     }
/*      */   }
/*      */ 
/*      */   static native int nclBuildProgram(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static int clBuildProgram(CLProgram program, PointerBuffer device_list, CharSequence options, CLBuildProgramCallback pfn_notify) {
/* 1345 */     long function_pointer = CLCapabilities.clBuildProgram;
/* 1346 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1347 */     if (device_list != null)
/* 1348 */       BufferChecks.checkDirect(device_list);
/* 1349 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 1350 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 1351 */     int __result = 0;
/*      */     try {
/* 1353 */       __result = nclBuildProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 1354 */       return __result;
/*      */     } finally {
/* 1356 */       CallbackUtil.checkCallback(__result, user_data);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static int clBuildProgram(CLProgram program, CLDevice device, CharSequence options, CLBuildProgramCallback pfn_notify)
/*      */   {
/* 1362 */     long function_pointer = CLCapabilities.clBuildProgram;
/* 1363 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1364 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 1365 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 1366 */     int __result = 0;
/*      */     try {
/* 1368 */       __result = nclBuildProgram(program.getPointer(), 1, APIUtil.getPointer(device), APIUtil.getBufferNT(options), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 1369 */       return __result;
/*      */     } finally {
/* 1371 */       CallbackUtil.checkCallback(__result, user_data);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static int clUnloadCompiler() {
/* 1376 */     long function_pointer = CLCapabilities.clUnloadCompiler;
/* 1377 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1378 */     int __result = nclUnloadCompiler(function_pointer);
/* 1379 */     return __result;
/*      */   }
/*      */   static native int nclUnloadCompiler(long paramLong);
/*      */ 
/*      */   public static int clGetProgramInfo(CLProgram program, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1384 */     long function_pointer = CLCapabilities.clGetProgramInfo;
/* 1385 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1386 */     if (param_value != null)
/* 1387 */       BufferChecks.checkDirect(param_value);
/* 1388 */     if (param_value_size_ret != null)
/* 1389 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1390 */     int __result = nclGetProgramInfo(program.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1391 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nclGetProgramInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clGetProgramInfo(CLProgram program, PointerBuffer sizes, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*      */   {
/* 1410 */     long function_pointer = CLCapabilities.clGetProgramInfo;
/* 1411 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1412 */     BufferChecks.checkBuffer(sizes, 1);
/* 1413 */     BufferChecks.checkBuffer(param_value, APIUtil.getSize(sizes));
/* 1414 */     if (param_value_size_ret != null)
/* 1415 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1416 */     int __result = nclGetProgramInfo2(program.getPointer(), 4454, sizes.remainingByte(), MemoryUtil.getAddress(sizes), MemoryUtil.getAddress(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1417 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nclGetProgramInfo2(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static int clGetProgramInfo(CLProgram program, ByteBuffer[] param_value, PointerBuffer param_value_size_ret)
/*      */   {
/* 1436 */     long function_pointer = CLCapabilities.clGetProgramInfo;
/* 1437 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1438 */     BufferChecks.checkArray(param_value);
/* 1439 */     if (param_value_size_ret != null)
/* 1440 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1441 */     int __result = nclGetProgramInfo3(program.getPointer(), 4454, param_value.length * PointerBuffer.getPointerSize(), param_value, MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1442 */     return __result;
/*      */   }
/*      */   static native int nclGetProgramInfo3(long paramLong1, int paramInt, long paramLong2, ByteBuffer[] paramArrayOfByteBuffer, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static int clGetProgramBuildInfo(CLProgram program, CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1447 */     long function_pointer = CLCapabilities.clGetProgramBuildInfo;
/* 1448 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1449 */     if (param_value != null)
/* 1450 */       BufferChecks.checkDirect(param_value);
/* 1451 */     if (param_value_size_ret != null)
/* 1452 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1453 */     int __result = nclGetProgramBuildInfo(program.getPointer(), device.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1454 */     return __result;
/*      */   }
/*      */   static native int nclGetProgramBuildInfo(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static CLKernel clCreateKernel(CLProgram program, ByteBuffer kernel_name, IntBuffer errcode_ret) {
/* 1459 */     long function_pointer = CLCapabilities.clCreateKernel;
/* 1460 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1461 */     BufferChecks.checkDirect(kernel_name);
/* 1462 */     BufferChecks.checkNullTerminated(kernel_name);
/* 1463 */     if (errcode_ret != null)
/* 1464 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1465 */     CLKernel __result = new CLKernel(nclCreateKernel(program.getPointer(), MemoryUtil.getAddress(kernel_name), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), program);
/* 1466 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclCreateKernel(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static CLKernel clCreateKernel(CLProgram program, CharSequence kernel_name, IntBuffer errcode_ret) {
/* 1472 */     long function_pointer = CLCapabilities.clCreateKernel;
/* 1473 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1474 */     if (errcode_ret != null)
/* 1475 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 1476 */     CLKernel __result = new CLKernel(nclCreateKernel(program.getPointer(), APIUtil.getBufferNT(kernel_name), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), program);
/* 1477 */     return __result;
/*      */   }
/*      */ 
/*      */   public static int clCreateKernelsInProgram(CLProgram program, PointerBuffer kernels, IntBuffer num_kernels_ret) {
/* 1481 */     long function_pointer = CLCapabilities.clCreateKernelsInProgram;
/* 1482 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1483 */     if (kernels != null)
/* 1484 */       BufferChecks.checkDirect(kernels);
/* 1485 */     if (num_kernels_ret != null)
/* 1486 */       BufferChecks.checkBuffer(num_kernels_ret, 1);
/* 1487 */     int __result = nclCreateKernelsInProgram(program.getPointer(), kernels == null ? 0 : kernels.remaining(), MemoryUtil.getAddressSafe(kernels), MemoryUtil.getAddressSafe(num_kernels_ret), function_pointer);
/* 1488 */     if ((__result == 0) && (kernels != null)) program.registerCLKernels(kernels);
/* 1489 */     return __result;
/*      */   }
/*      */   static native int nclCreateKernelsInProgram(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static int clRetainKernel(CLKernel kernel) {
/* 1494 */     long function_pointer = CLCapabilities.clRetainKernel;
/* 1495 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1496 */     int __result = nclRetainKernel(kernel.getPointer(), function_pointer);
/* 1497 */     if (__result == 0) kernel.retain();
/* 1498 */     return __result;
/*      */   }
/*      */   static native int nclRetainKernel(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseKernel(CLKernel kernel) {
/* 1503 */     long function_pointer = CLCapabilities.clReleaseKernel;
/* 1504 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1505 */     int __result = nclReleaseKernel(kernel.getPointer(), function_pointer);
/* 1506 */     if (__result == 0) kernel.release();
/* 1507 */     return __result;
/*      */   }
/*      */   static native int nclReleaseKernel(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, long arg_value_arg_size) {
/* 1512 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1513 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1514 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value_arg_size, 0L, function_pointer);
/* 1515 */     return __result;
/*      */   }
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, ByteBuffer arg_value) {
/* 1518 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1519 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1520 */     BufferChecks.checkDirect(arg_value);
/* 1521 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining(), MemoryUtil.getAddress(arg_value), function_pointer);
/* 1522 */     return __result;
/*      */   }
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, DoubleBuffer arg_value) {
/* 1525 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1526 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1527 */     BufferChecks.checkDirect(arg_value);
/* 1528 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 3, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1529 */     return __result;
/*      */   }
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, FloatBuffer arg_value) {
/* 1532 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1533 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1534 */     BufferChecks.checkDirect(arg_value);
/* 1535 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 2, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1536 */     return __result;
/*      */   }
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, IntBuffer arg_value) {
/* 1539 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1540 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1541 */     BufferChecks.checkDirect(arg_value);
/* 1542 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 2, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1543 */     return __result;
/*      */   }
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, LongBuffer arg_value) {
/* 1546 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1547 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1548 */     BufferChecks.checkDirect(arg_value);
/* 1549 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 3, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1550 */     return __result;
/*      */   }
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, ShortBuffer arg_value) {
/* 1553 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1554 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1555 */     BufferChecks.checkDirect(arg_value);
/* 1556 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 1, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1557 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nclSetKernelArg(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static int clSetKernelArg(CLKernel kernel, int arg_index, CLObject arg_value) {
/* 1563 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1564 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1565 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, PointerBuffer.getPointerSize(), APIUtil.getPointerSafe(arg_value), function_pointer);
/* 1566 */     return __result;
/*      */   }
/*      */ 
/*      */   static int clSetKernelArg(CLKernel kernel, int arg_index, long arg_size, Buffer arg_value)
/*      */   {
/* 1571 */     long function_pointer = CLCapabilities.clSetKernelArg;
/* 1572 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1573 */     int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_size, MemoryUtil.getAddress0(arg_value), function_pointer);
/* 1574 */     return __result;
/*      */   }
/*      */ 
/*      */   public static int clGetKernelInfo(CLKernel kernel, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1578 */     long function_pointer = CLCapabilities.clGetKernelInfo;
/* 1579 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1580 */     if (param_value != null)
/* 1581 */       BufferChecks.checkDirect(param_value);
/* 1582 */     if (param_value_size_ret != null)
/* 1583 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1584 */     int __result = nclGetKernelInfo(kernel.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1585 */     return __result;
/*      */   }
/*      */   static native int nclGetKernelInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clGetKernelWorkGroupInfo(CLKernel kernel, CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1590 */     long function_pointer = CLCapabilities.clGetKernelWorkGroupInfo;
/* 1591 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1592 */     if (param_value != null)
/* 1593 */       BufferChecks.checkDirect(param_value);
/* 1594 */     if (param_value_size_ret != null)
/* 1595 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1596 */     int __result = nclGetKernelWorkGroupInfo(kernel.getPointer(), device == null ? 0L : device.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1597 */     return __result;
/*      */   }
/*      */   static native int nclGetKernelWorkGroupInfo(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*      */ 
/*      */   public static int clEnqueueNDRangeKernel(CLCommandQueue command_queue, CLKernel kernel, int work_dim, PointerBuffer global_work_offset, PointerBuffer global_work_size, PointerBuffer local_work_size, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1602 */     long function_pointer = CLCapabilities.clEnqueueNDRangeKernel;
/* 1603 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1604 */     if (global_work_offset != null)
/* 1605 */       BufferChecks.checkBuffer(global_work_offset, work_dim);
/* 1606 */     if (global_work_size != null)
/* 1607 */       BufferChecks.checkBuffer(global_work_size, work_dim);
/* 1608 */     if (local_work_size != null)
/* 1609 */       BufferChecks.checkBuffer(local_work_size, work_dim);
/* 1610 */     if (event_wait_list != null)
/* 1611 */       BufferChecks.checkDirect(event_wait_list);
/* 1612 */     if (event != null)
/* 1613 */       BufferChecks.checkBuffer(event, 1);
/* 1614 */     int __result = nclEnqueueNDRangeKernel(command_queue.getPointer(), kernel.getPointer(), work_dim, MemoryUtil.getAddressSafe(global_work_offset), MemoryUtil.getAddressSafe(global_work_size), MemoryUtil.getAddressSafe(local_work_size), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1615 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1616 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueNDRangeKernel(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8);
/*      */ 
/*      */   public static int clEnqueueTask(CLCommandQueue command_queue, CLKernel kernel, PointerBuffer event_wait_list, PointerBuffer event) {
/* 1621 */     long function_pointer = CLCapabilities.clEnqueueTask;
/* 1622 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1623 */     if (event_wait_list != null)
/* 1624 */       BufferChecks.checkDirect(event_wait_list);
/* 1625 */     if (event != null)
/* 1626 */       BufferChecks.checkBuffer(event, 1);
/* 1627 */     int __result = nclEnqueueTask(command_queue.getPointer(), kernel.getPointer(), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1628 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1629 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nclEnqueueTask(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clEnqueueNativeKernel(CLCommandQueue command_queue, CLNativeKernel user_func, CLMem[] mem_list, long[] sizes, PointerBuffer event_wait_list, PointerBuffer event)
/*      */   {
/* 1650 */     long function_pointer = CLCapabilities.clEnqueueNativeKernel;
/* 1651 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1652 */     if (mem_list != null)
/* 1653 */       BufferChecks.checkArray(mem_list, 1);
/* 1654 */     if (sizes != null)
/* 1655 */       BufferChecks.checkArray(sizes, mem_list.length);
/* 1656 */     if (event_wait_list != null)
/* 1657 */       BufferChecks.checkDirect(event_wait_list);
/* 1658 */     if (event != null)
/* 1659 */       BufferChecks.checkBuffer(event, 1);
/* 1660 */     long user_func_ref = CallbackUtil.createGlobalRef(user_func);
/* 1661 */     ByteBuffer args = APIUtil.getNativeKernelArgs(user_func_ref, mem_list, sizes);
/* 1662 */     int __result = 0;
/*      */     try {
/* 1664 */       __result = nclEnqueueNativeKernel(command_queue.getPointer(), user_func.getPointer(), MemoryUtil.getAddress0(args), args.remaining(), mem_list == null ? 0 : mem_list.length, mem_list, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1665 */       if (__result == 0) command_queue.registerCLEvent(event);
/* 1666 */       return __result;
/*      */     } finally {
/* 1668 */       CallbackUtil.checkCallback(__result, user_func_ref);
/*      */     }
/*      */   }
/*      */ 
/*      */   static native int nclEnqueueNativeKernel(long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, CLMem[] paramArrayOfCLMem, int paramInt2, long paramLong5, long paramLong6, long paramLong7);
/*      */ 
/* 1674 */   public static int clWaitForEvents(PointerBuffer event_list) { long function_pointer = CLCapabilities.clWaitForEvents;
/* 1675 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1676 */     BufferChecks.checkBuffer(event_list, 1);
/* 1677 */     int __result = nclWaitForEvents(event_list.remaining(), MemoryUtil.getAddress(event_list), function_pointer);
/* 1678 */     return __result; }
/*      */ 
/*      */   static native int nclWaitForEvents(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clWaitForEvents(CLEvent event)
/*      */   {
/* 1684 */     long function_pointer = CLCapabilities.clWaitForEvents;
/* 1685 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1686 */     int __result = nclWaitForEvents(1, APIUtil.getPointer(event), function_pointer);
/* 1687 */     return __result;
/*      */   }
/*      */ 
/*      */   public static int clGetEventInfo(CLEvent event, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1691 */     long function_pointer = CLCapabilities.clGetEventInfo;
/* 1692 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1693 */     if (param_value != null)
/* 1694 */       BufferChecks.checkDirect(param_value);
/* 1695 */     if (param_value_size_ret != null)
/* 1696 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1697 */     int __result = nclGetEventInfo(event.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1698 */     return __result;
/*      */   }
/*      */   static native int nclGetEventInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clRetainEvent(CLEvent event) {
/* 1703 */     long function_pointer = CLCapabilities.clRetainEvent;
/* 1704 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1705 */     int __result = nclRetainEvent(event.getPointer(), function_pointer);
/* 1706 */     if (__result == 0) event.retain();
/* 1707 */     return __result;
/*      */   }
/*      */   static native int nclRetainEvent(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clReleaseEvent(CLEvent event) {
/* 1712 */     long function_pointer = CLCapabilities.clReleaseEvent;
/* 1713 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1714 */     int __result = nclReleaseEvent(event.getPointer(), function_pointer);
/* 1715 */     if (__result == 0) event.release();
/* 1716 */     return __result;
/*      */   }
/*      */   static native int nclReleaseEvent(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clEnqueueMarker(CLCommandQueue command_queue, PointerBuffer event) {
/* 1721 */     long function_pointer = CLCapabilities.clEnqueueMarker;
/* 1722 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1723 */     BufferChecks.checkBuffer(event, 1);
/* 1724 */     int __result = nclEnqueueMarker(command_queue.getPointer(), MemoryUtil.getAddress(event), function_pointer);
/* 1725 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 1726 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueMarker(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int clEnqueueBarrier(CLCommandQueue command_queue) {
/* 1731 */     long function_pointer = CLCapabilities.clEnqueueBarrier;
/* 1732 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1733 */     int __result = nclEnqueueBarrier(command_queue.getPointer(), function_pointer);
/* 1734 */     return __result;
/*      */   }
/*      */   static native int nclEnqueueBarrier(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clEnqueueWaitForEvents(CLCommandQueue command_queue, PointerBuffer event_list) {
/* 1739 */     long function_pointer = CLCapabilities.clEnqueueWaitForEvents;
/* 1740 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1741 */     BufferChecks.checkBuffer(event_list, 1);
/* 1742 */     int __result = nclEnqueueWaitForEvents(command_queue.getPointer(), event_list.remaining(), MemoryUtil.getAddress(event_list), function_pointer);
/* 1743 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nclEnqueueWaitForEvents(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int clEnqueueWaitForEvents(CLCommandQueue command_queue, CLEvent event) {
/* 1749 */     long function_pointer = CLCapabilities.clEnqueueWaitForEvents;
/* 1750 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1751 */     int __result = nclEnqueueWaitForEvents(command_queue.getPointer(), 1, APIUtil.getPointer(event), function_pointer);
/* 1752 */     return __result;
/*      */   }
/*      */ 
/*      */   public static int clGetEventProfilingInfo(CLEvent event, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1756 */     long function_pointer = CLCapabilities.clGetEventProfilingInfo;
/* 1757 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1758 */     if (param_value != null)
/* 1759 */       BufferChecks.checkDirect(param_value);
/* 1760 */     if (param_value_size_ret != null)
/* 1761 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1762 */     int __result = nclGetEventProfilingInfo(event.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1763 */     return __result;
/*      */   }
/*      */   static native int nclGetEventProfilingInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static int clFlush(CLCommandQueue command_queue) {
/* 1768 */     long function_pointer = CLCapabilities.clFlush;
/* 1769 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1770 */     int __result = nclFlush(command_queue.getPointer(), function_pointer);
/* 1771 */     return __result;
/*      */   }
/*      */   static native int nclFlush(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int clFinish(CLCommandQueue command_queue) {
/* 1776 */     long function_pointer = CLCapabilities.clFinish;
/* 1777 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1778 */     int __result = nclFinish(command_queue.getPointer(), function_pointer);
/* 1779 */     return __result;
/*      */   }
/*      */   static native int nclFinish(long paramLong1, long paramLong2);
/*      */ 
/*      */   static CLFunctionAddress clGetExtensionFunctionAddress(ByteBuffer func_name) {
/* 1784 */     long function_pointer = CLCapabilities.clGetExtensionFunctionAddress;
/* 1785 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1786 */     BufferChecks.checkDirect(func_name);
/* 1787 */     BufferChecks.checkNullTerminated(func_name);
/* 1788 */     CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddress(MemoryUtil.getAddress(func_name), function_pointer));
/* 1789 */     return __result;
/*      */   }
/*      */ 
/*      */   static native long nclGetExtensionFunctionAddress(long paramLong1, long paramLong2);
/*      */ 
/*      */   static CLFunctionAddress clGetExtensionFunctionAddress(CharSequence func_name) {
/* 1795 */     long function_pointer = CLCapabilities.clGetExtensionFunctionAddress;
/* 1796 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1797 */     CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddress(APIUtil.getBufferNT(func_name), function_pointer));
/* 1798 */     return __result;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL10
 * JD-Core Version:    0.6.2
 */