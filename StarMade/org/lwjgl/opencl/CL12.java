/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ public final class CL12
/*     */ {
/*     */   public static final int CL_COMPILE_PROGRAM_FAILURE = -15;
/*     */   public static final int CL_LINKER_NOT_AVAILABLE = -16;
/*     */   public static final int CL_LINK_PROGRAM_FAILURE = -17;
/*     */   public static final int CL_DEVICE_PARTITION_FAILED = -18;
/*     */   public static final int CL_KERNEL_ARG_INFO_NOT_AVAILABLE = -19;
/*     */   public static final int CL_INVALID_IMAGE_DESCRIPTOR = -65;
/*     */   public static final int CL_INVALID_COMPILER_OPTIONS = -66;
/*     */   public static final int CL_INVALID_LINKER_OPTIONS = -67;
/*     */   public static final int CL_INVALID_DEVICE_PARTITION_COUNT = -68;
/*     */   public static final int CL_VERSION_1_2 = 1;
/*     */   public static final int CL_BLOCKING = 1;
/*     */   public static final int CL_NON_BLOCKING = 0;
/*     */   public static final int CL_DEVICE_TYPE_CUSTOM = 16;
/*     */   public static final int CL_DEVICE_DOUBLE_FP_CONFIG = 4146;
/*     */   public static final int CL_DEVICE_LINKER_AVAILABLE = 4158;
/*     */   public static final int CL_DEVICE_BUILT_IN_KERNELS = 4159;
/*     */   public static final int CL_DEVICE_IMAGE_MAX_BUFFER_SIZE = 4160;
/*     */   public static final int CL_DEVICE_IMAGE_MAX_ARRAY_SIZE = 4161;
/*     */   public static final int CL_DEVICE_PARENT_DEVICE = 4162;
/*     */   public static final int CL_DEVICE_PARTITION_MAX_SUB_DEVICES = 4163;
/*     */   public static final int CL_DEVICE_PARTITION_PROPERTIES = 4164;
/*     */   public static final int CL_DEVICE_PARTITION_AFFINITY_DOMAIN = 4165;
/*     */   public static final int CL_DEVICE_PARTITION_TYPE = 4166;
/*     */   public static final int CL_DEVICE_REFERENCE_COUNT = 4167;
/*     */   public static final int CL_DEVICE_PREFERRED_INTEROP_USER_SYNC = 4168;
/*     */   public static final int CL_DEVICE_PRINTF_BUFFER_SIZE = 4169;
/*     */   public static final int CL_FP_CORRECTLY_ROUNDED_DIVIDE_SQRT = 128;
/*     */   public static final int CL_CONTEXT_INTEROP_USER_SYNC = 4229;
/*     */   public static final int CL_DEVICE_PARTITION_EQUALLY = 4230;
/*     */   public static final int CL_DEVICE_PARTITION_BY_COUNTS = 4231;
/*     */   public static final int CL_DEVICE_PARTITION_BY_COUNTS_LIST_END = 0;
/*     */   public static final int CL_DEVICE_PARTITION_BY_AFFINITY_DOMAIN = 4232;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAIN_NUMA = 1;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAIN_L4_CACHE = 2;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAIN_L3_CACHE = 4;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAIN_L2_CACHE = 8;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAIN_L1_CACHE = 16;
/*     */   public static final int CL_DEVICE_AFFINITY_DOMAIN_NEXT_PARTITIONABLE = 32;
/*     */   public static final int CL_MEM_HOST_WRITE_ONLY = 128;
/*     */   public static final int CL_MEM_HOST_READ_ONLY = 256;
/*     */   public static final int CL_MEM_HOST_NO_ACCESS = 512;
/*     */   public static final int CL_MIGRATE_MEM_OBJECT_HOST = 1;
/*     */   public static final int CL_MIGRATE_MEM_OBJECT_CONTENT_UNDEFINED = 2;
/*     */   public static final int CL_MEM_OBJECT_IMAGE2D_ARRAY = 4339;
/*     */   public static final int CL_MEM_OBJECT_IMAGE1D = 4340;
/*     */   public static final int CL_MEM_OBJECT_IMAGE1D_ARRAY = 4341;
/*     */   public static final int CL_MEM_OBJECT_IMAGE1D_BUFFER = 4342;
/*     */   public static final int CL_IMAGE_ARRAY_SIZE = 4375;
/*     */   public static final int CL_IMAGE_BUFFER = 4376;
/*     */   public static final int CL_IMAGE_NUM_MIP_LEVELS = 4377;
/*     */   public static final int CL_IMAGE_NUM_SAMPLES = 4378;
/*     */   public static final int CL_MAP_WRITE_INVALIDATE_REGION = 4;
/*     */   public static final int CL_PROGRAM_NUM_KERNELS = 4455;
/*     */   public static final int CL_PROGRAM_KERNEL_NAMES = 4456;
/*     */   public static final int CL_PROGRAM_BINARY_TYPE = 4484;
/*     */   public static final int CL_PROGRAM_BINARY_TYPE_NONE = 0;
/*     */   public static final int CL_PROGRAM_BINARY_TYPE_COMPILED_OBJECT = 1;
/*     */   public static final int CL_PROGRAM_BINARY_TYPE_LIBRARY = 2;
/*     */   public static final int CL_PROGRAM_BINARY_TYPE_EXECUTABLE = 4;
/*     */   public static final int CL_KERNEL_ATTRIBUTES = 4501;
/*     */   public static final int CL_KERNEL_ARG_ADDRESS_QUALIFIER = 4502;
/*     */   public static final int CL_KERNEL_ARG_ACCESS_QUALIFIER = 4503;
/*     */   public static final int CL_KERNEL_ARG_TYPE_NAME = 4504;
/*     */   public static final int CL_KERNEL_ARG_TYPE_QUALIFIER = 4505;
/*     */   public static final int CL_KERNEL_ARG_NAME = 4506;
/*     */   public static final int CL_KERNEL_ARG_ADDRESS_GLOBAL = 4506;
/*     */   public static final int CL_KERNEL_ARG_ADDRESS_LOCAL = 4507;
/*     */   public static final int CL_KERNEL_ARG_ADDRESS_CONSTANT = 4508;
/*     */   public static final int CL_KERNEL_ARG_ADDRESS_PRIVATE = 4509;
/*     */   public static final int CL_KERNEL_ARG_ACCESS_READ_ONLY = 4512;
/*     */   public static final int CL_KERNEL_ARG_ACCESS_WRITE_ONLY = 4513;
/*     */   public static final int CL_KERNEL_ARG_ACCESS_READ_WRITE = 4514;
/*     */   public static final int CL_KERNEL_ARG_ACCESS_NONE = 4515;
/*     */   public static final int CL_KERNEL_ARG_TYPE_NONE = 0;
/*     */   public static final int CL_KERNEL_ARG_TYPE_CONST = 1;
/*     */   public static final int CL_KERNEL_ARG_TYPE_RESTRICT = 2;
/*     */   public static final int CL_KERNEL_ARG_TYPE_VOLATILE = 4;
/*     */   public static final int CL_KERNEL_GLOBAL_WORK_SIZE = 4533;
/*     */   public static final int CL_COMMAND_BARRIER = 4613;
/*     */   public static final int CL_COMMAND_MIGRATE_MEM_OBJECTS = 4614;
/*     */   public static final int CL_COMMAND_FILL_BUFFER = 4615;
/*     */   public static final int CL_COMMAND_FILL_IMAGE = 4616;
/*     */ 
/*     */   public static int clRetainDevice(CLDevice device)
/*     */   {
/* 114 */     long function_pointer = CLCapabilities.clRetainDevice;
/* 115 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 116 */     int __result = nclRetainDevice(device.getPointer(), function_pointer);
/* 117 */     if (__result == 0) device.retain();
/* 118 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nclRetainDevice(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int clReleaseDevice(CLDevice device)
/*     */   {
/* 131 */     long function_pointer = CLCapabilities.clReleaseDevice;
/* 132 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 133 */     APIUtil.releaseObjects(device);
/* 134 */     int __result = nclReleaseDevice(device.getPointer(), function_pointer);
/* 135 */     if (__result == 0) device.release();
/* 136 */     return __result;
/*     */   }
/*     */   static native int nclReleaseDevice(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int clCreateSubDevices(CLDevice in_device, LongBuffer properties, PointerBuffer out_devices, IntBuffer num_devices_ret) {
/* 141 */     long function_pointer = CLCapabilities.clCreateSubDevices;
/* 142 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 143 */     BufferChecks.checkDirect(properties);
/* 144 */     BufferChecks.checkNullTerminated(properties);
/* 145 */     if (out_devices != null)
/* 146 */       BufferChecks.checkDirect(out_devices);
/* 147 */     if (num_devices_ret != null)
/* 148 */       BufferChecks.checkBuffer(num_devices_ret, 1);
/* 149 */     int __result = nclCreateSubDevices(in_device.getPointer(), MemoryUtil.getAddress(properties), out_devices == null ? 0 : out_devices.remaining(), MemoryUtil.getAddressSafe(out_devices), MemoryUtil.getAddressSafe(num_devices_ret), function_pointer);
/* 150 */     if ((__result == 0) && (out_devices != null)) in_device.registerSubCLDevices(out_devices);
/* 151 */     return __result;
/*     */   }
/*     */   static native int nclCreateSubDevices(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, ByteBuffer host_ptr, IntBuffer errcode_ret) {
/* 156 */     long function_pointer = CLCapabilities.clCreateImage;
/* 157 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 158 */     BufferChecks.checkBuffer(image_format, 8);
/* 159 */     BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 160 */     if (host_ptr != null)
/* 161 */       BufferChecks.checkDirect(host_ptr);
/* 162 */     if (errcode_ret != null)
/* 163 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 164 */     CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 165 */     return __result;
/*     */   }
/*     */   public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, FloatBuffer host_ptr, IntBuffer errcode_ret) {
/* 168 */     long function_pointer = CLCapabilities.clCreateImage;
/* 169 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 170 */     BufferChecks.checkBuffer(image_format, 8);
/* 171 */     BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 172 */     if (host_ptr != null)
/* 173 */       BufferChecks.checkDirect(host_ptr);
/* 174 */     if (errcode_ret != null)
/* 175 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 176 */     CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 177 */     return __result;
/*     */   }
/*     */   public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, IntBuffer host_ptr, IntBuffer errcode_ret) {
/* 180 */     long function_pointer = CLCapabilities.clCreateImage;
/* 181 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 182 */     BufferChecks.checkBuffer(image_format, 8);
/* 183 */     BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 184 */     if (host_ptr != null)
/* 185 */       BufferChecks.checkDirect(host_ptr);
/* 186 */     if (errcode_ret != null)
/* 187 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 188 */     CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 189 */     return __result;
/*     */   }
/*     */   public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, ShortBuffer host_ptr, IntBuffer errcode_ret) {
/* 192 */     long function_pointer = CLCapabilities.clCreateImage;
/* 193 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 194 */     BufferChecks.checkBuffer(image_format, 8);
/* 195 */     BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 196 */     if (host_ptr != null)
/* 197 */       BufferChecks.checkDirect(host_ptr);
/* 198 */     if (errcode_ret != null)
/* 199 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 200 */     CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 201 */     return __result;
/*     */   }
/*     */   static native long nclCreateImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/*     */ 
/*     */   public static CLProgram clCreateProgramWithBuiltInKernels(CLContext context, PointerBuffer device_list, ByteBuffer kernel_names, IntBuffer errcode_ret) {
/* 206 */     long function_pointer = CLCapabilities.clCreateProgramWithBuiltInKernels;
/* 207 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 208 */     BufferChecks.checkBuffer(device_list, 1);
/* 209 */     BufferChecks.checkDirect(kernel_names);
/* 210 */     if (errcode_ret != null)
/* 211 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 212 */     CLProgram __result = new CLProgram(nclCreateProgramWithBuiltInKernels(context.getPointer(), device_list.remaining(), MemoryUtil.getAddress(device_list), MemoryUtil.getAddress(kernel_names), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 213 */     return __result;
/*     */   }
/*     */ 
/*     */   static native long nclCreateProgramWithBuiltInKernels(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static CLProgram clCreateProgramWithBuiltInKernels(CLContext context, PointerBuffer device_list, CharSequence kernel_names, IntBuffer errcode_ret) {
/* 219 */     long function_pointer = CLCapabilities.clCreateProgramWithBuiltInKernels;
/* 220 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 221 */     BufferChecks.checkBuffer(device_list, 1);
/* 222 */     if (errcode_ret != null)
/* 223 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 224 */     CLProgram __result = new CLProgram(nclCreateProgramWithBuiltInKernels(context.getPointer(), device_list.remaining(), MemoryUtil.getAddress(device_list), APIUtil.getBuffer(kernel_names), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 225 */     return __result;
/*     */   }
/*     */ 
/*     */   public static int clCompileProgram(CLProgram program, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_header, ByteBuffer header_include_name, CLCompileProgramCallback pfn_notify)
/*     */   {
/* 232 */     long function_pointer = CLCapabilities.clCompileProgram;
/* 233 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 234 */     if (device_list != null)
/* 235 */       BufferChecks.checkDirect(device_list);
/* 236 */     BufferChecks.checkDirect(options);
/* 237 */     BufferChecks.checkNullTerminated(options);
/* 238 */     BufferChecks.checkBuffer(input_header, 1);
/* 239 */     BufferChecks.checkDirect(header_include_name);
/* 240 */     BufferChecks.checkNullTerminated(header_include_name);
/* 241 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 242 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 243 */     int __result = 0;
/*     */     try {
/* 245 */       __result = nclCompileProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), 1, MemoryUtil.getAddress(input_header), MemoryUtil.getAddress(header_include_name), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 246 */       return __result;
/*     */     } finally {
/* 248 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native int nclCompileProgram(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
/*     */ 
/*     */   public static int clCompileProgramMulti(CLProgram program, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_headers, ByteBuffer header_include_names, CLCompileProgramCallback pfn_notify)
/*     */   {
/* 259 */     long function_pointer = CLCapabilities.clCompileProgram;
/* 260 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 261 */     if (device_list != null)
/* 262 */       BufferChecks.checkDirect(device_list);
/* 263 */     BufferChecks.checkDirect(options);
/* 264 */     BufferChecks.checkNullTerminated(options);
/* 265 */     BufferChecks.checkBuffer(input_headers, 1);
/* 266 */     BufferChecks.checkDirect(header_include_names);
/* 267 */     BufferChecks.checkNullTerminated(header_include_names, input_headers.remaining());
/* 268 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 269 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 270 */     int __result = 0;
/*     */     try {
/* 272 */       __result = nclCompileProgramMulti(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), input_headers.remaining(), MemoryUtil.getAddress(input_headers), MemoryUtil.getAddress(header_include_names), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 273 */       return __result;
/*     */     } finally {
/* 275 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native int nclCompileProgramMulti(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
/*     */ 
/*     */   public static int clCompileProgram(CLProgram program, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_headers, ByteBuffer[] header_include_names, CLCompileProgramCallback pfn_notify) {
/* 282 */     long function_pointer = CLCapabilities.clCompileProgram;
/* 283 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 284 */     if (device_list != null)
/* 285 */       BufferChecks.checkDirect(device_list);
/* 286 */     BufferChecks.checkDirect(options);
/* 287 */     BufferChecks.checkNullTerminated(options);
/* 288 */     BufferChecks.checkBuffer(input_headers, header_include_names.length);
/* 289 */     BufferChecks.checkArray(header_include_names, 1);
/* 290 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 291 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 292 */     int __result = 0;
/*     */     try {
/* 294 */       __result = nclCompileProgram3(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), header_include_names.length, MemoryUtil.getAddress(input_headers), header_include_names, pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 295 */       return __result;
/*     */     } finally {
/* 297 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native int nclCompileProgram3(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, ByteBuffer[] paramArrayOfByteBuffer, long paramLong5, long paramLong6, long paramLong7);
/*     */ 
/*     */   public static int clCompileProgram(CLProgram program, PointerBuffer device_list, CharSequence options, PointerBuffer input_header, CharSequence header_include_name, CLCompileProgramCallback pfn_notify) {
/* 304 */     long function_pointer = CLCapabilities.clCompileProgram;
/* 305 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 306 */     if (device_list != null)
/* 307 */       BufferChecks.checkDirect(device_list);
/* 308 */     BufferChecks.checkBuffer(input_header, 1);
/* 309 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 310 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 311 */     int __result = 0;
/*     */     try {
/* 313 */       __result = nclCompileProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), 1, MemoryUtil.getAddress(input_header), APIUtil.getBufferNT(header_include_name), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 314 */       return __result;
/*     */     } finally {
/* 316 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int clCompileProgram(CLProgram program, PointerBuffer device_list, CharSequence options, PointerBuffer input_header, CharSequence[] header_include_name, CLCompileProgramCallback pfn_notify)
/*     */   {
/* 322 */     long function_pointer = CLCapabilities.clCompileProgram;
/* 323 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 324 */     if (device_list != null)
/* 325 */       BufferChecks.checkDirect(device_list);
/* 326 */     BufferChecks.checkBuffer(input_header, 1);
/* 327 */     BufferChecks.checkArray(header_include_name);
/* 328 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 329 */     if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 330 */     int __result = 0;
/*     */     try {
/* 332 */       __result = nclCompileProgramMulti(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), input_header.remaining(), MemoryUtil.getAddress(input_header), APIUtil.getBufferNT(header_include_name), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 333 */       return __result;
/*     */     } finally {
/* 335 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static CLProgram clLinkProgram(CLContext context, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_programs, CLLinkProgramCallback pfn_notify, IntBuffer errcode_ret) {
/* 340 */     long function_pointer = CLCapabilities.clLinkProgram;
/* 341 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 342 */     if (device_list != null)
/* 343 */       BufferChecks.checkDirect(device_list);
/* 344 */     BufferChecks.checkDirect(options);
/* 345 */     BufferChecks.checkNullTerminated(options);
/* 346 */     BufferChecks.checkDirect(input_programs);
/* 347 */     BufferChecks.checkBuffer(errcode_ret, 1);
/* 348 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 349 */     if (pfn_notify != null) pfn_notify.setContext(context);
/* 350 */     CLProgram __result = null;
/*     */     try {
/* 352 */       __result = new CLProgram(nclLinkProgram(context.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), input_programs.remaining(), MemoryUtil.getAddress(input_programs), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddress(errcode_ret), function_pointer), context);
/* 353 */       return __result;
/*     */     } finally {
/* 355 */       CallbackUtil.checkCallback(errcode_ret.get(errcode_ret.position()), user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native long nclLinkProgram(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
/*     */ 
/*     */   public static CLProgram clLinkProgram(CLContext context, PointerBuffer device_list, CharSequence options, PointerBuffer input_programs, CLLinkProgramCallback pfn_notify, IntBuffer errcode_ret) {
/* 362 */     long function_pointer = CLCapabilities.clLinkProgram;
/* 363 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 364 */     if (device_list != null)
/* 365 */       BufferChecks.checkDirect(device_list);
/* 366 */     BufferChecks.checkDirect(input_programs);
/* 367 */     BufferChecks.checkBuffer(errcode_ret, 1);
/* 368 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 369 */     if (pfn_notify != null) pfn_notify.setContext(context);
/* 370 */     CLProgram __result = null;
/*     */     try {
/* 372 */       __result = new CLProgram(nclLinkProgram(context.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), input_programs.remaining(), MemoryUtil.getAddress(input_programs), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddress(errcode_ret), function_pointer), context);
/* 373 */       return __result;
/*     */     } finally {
/* 375 */       CallbackUtil.checkCallback(errcode_ret.get(errcode_ret.position()), user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int clUnloadPlatformCompiler(CLPlatform platform) {
/* 380 */     long function_pointer = CLCapabilities.clUnloadPlatformCompiler;
/* 381 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 382 */     int __result = nclUnloadPlatformCompiler(platform.getPointer(), function_pointer);
/* 383 */     return __result;
/*     */   }
/*     */   static native int nclUnloadPlatformCompiler(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int clGetKernelArgInfo(CLKernel kernel, int arg_indx, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 388 */     long function_pointer = CLCapabilities.clGetKernelArgInfo;
/* 389 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 390 */     if (param_value != null)
/* 391 */       BufferChecks.checkDirect(param_value);
/* 392 */     if (param_value_size_ret != null)
/* 393 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 394 */     int __result = nclGetKernelArgInfo(kernel.getPointer(), arg_indx, param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 395 */     return __result;
/*     */   }
/*     */   static native int nclGetKernelArgInfo(long paramLong1, int paramInt1, int paramInt2, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static int clEnqueueFillBuffer(CLCommandQueue command_queue, CLMem buffer, ByteBuffer pattern, long offset, long size, PointerBuffer event_wait_list, PointerBuffer event) {
/* 400 */     long function_pointer = CLCapabilities.clEnqueueFillBuffer;
/* 401 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 402 */     BufferChecks.checkDirect(pattern);
/* 403 */     if (event_wait_list != null)
/* 404 */       BufferChecks.checkDirect(event_wait_list);
/* 405 */     if (event != null)
/* 406 */       BufferChecks.checkBuffer(event, 1);
/* 407 */     int __result = nclEnqueueFillBuffer(command_queue.getPointer(), buffer.getPointer(), MemoryUtil.getAddress(pattern), pattern.remaining(), offset, size, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 408 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueFillBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/*     */ 
/*     */   public static int clEnqueueFillImage(CLCommandQueue command_queue, CLMem image, ByteBuffer fill_color, PointerBuffer origin, PointerBuffer region, PointerBuffer event_wait_list, PointerBuffer event) {
/* 413 */     long function_pointer = CLCapabilities.clEnqueueFillImage;
/* 414 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 415 */     BufferChecks.checkBuffer(fill_color, 16);
/* 416 */     BufferChecks.checkBuffer(origin, 3);
/* 417 */     BufferChecks.checkBuffer(region, 3);
/* 418 */     if (event_wait_list != null)
/* 419 */       BufferChecks.checkDirect(event_wait_list);
/* 420 */     if (event != null)
/* 421 */       BufferChecks.checkBuffer(event, 1);
/* 422 */     int __result = nclEnqueueFillImage(command_queue.getPointer(), image.getPointer(), MemoryUtil.getAddress(fill_color), MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 423 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueFillImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, int paramInt, long paramLong6, long paramLong7, long paramLong8);
/*     */ 
/*     */   public static int clEnqueueMigrateMemObjects(CLCommandQueue command_queue, PointerBuffer mem_objects, long flags, PointerBuffer event_wait_list, PointerBuffer event) {
/* 428 */     long function_pointer = CLCapabilities.clEnqueueMigrateMemObjects;
/* 429 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 430 */     BufferChecks.checkDirect(mem_objects);
/* 431 */     if (event_wait_list != null)
/* 432 */       BufferChecks.checkDirect(event_wait_list);
/* 433 */     if (event != null)
/* 434 */       BufferChecks.checkBuffer(event, 1);
/* 435 */     int __result = nclEnqueueMigrateMemObjects(command_queue.getPointer(), mem_objects.remaining(), MemoryUtil.getAddress(mem_objects), flags, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 436 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueMigrateMemObjects(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6);
/*     */ 
/*     */   public static int clEnqueueMarkerWithWaitList(CLCommandQueue command_queue, PointerBuffer event_wait_list, PointerBuffer event) {
/* 441 */     long function_pointer = CLCapabilities.clEnqueueMarkerWithWaitList;
/* 442 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 443 */     if (event_wait_list != null)
/* 444 */       BufferChecks.checkDirect(event_wait_list);
/* 445 */     if (event != null)
/* 446 */       BufferChecks.checkBuffer(event, 1);
/* 447 */     int __result = nclEnqueueMarkerWithWaitList(command_queue.getPointer(), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 448 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueMarkerWithWaitList(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static int clEnqueueBarrierWithWaitList(CLCommandQueue command_queue, PointerBuffer event_wait_list, PointerBuffer event) {
/* 453 */     long function_pointer = CLCapabilities.clEnqueueBarrierWithWaitList;
/* 454 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 455 */     if (event_wait_list != null)
/* 456 */       BufferChecks.checkDirect(event_wait_list);
/* 457 */     if (event != null)
/* 458 */       BufferChecks.checkBuffer(event, 1);
/* 459 */     int __result = nclEnqueueBarrierWithWaitList(command_queue.getPointer(), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 460 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueBarrierWithWaitList(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static int clSetPrintfCallback(CLContext context, CLPrintfCallback pfn_notify) {
/* 465 */     long function_pointer = CLCapabilities.clSetPrintfCallback;
/* 466 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 467 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 468 */     int __result = 0;
/*     */     try {
/* 470 */       __result = nclSetPrintfCallback(context.getPointer(), pfn_notify.getPointer(), user_data, function_pointer);
/* 471 */       return __result;
/*     */     } finally {
/* 473 */       context.setPrintfCallback(user_data, __result);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native int nclSetPrintfCallback(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/* 479 */   static CLFunctionAddress clGetExtensionFunctionAddressForPlatform(CLPlatform platform, ByteBuffer func_name) { long function_pointer = CLCapabilities.clGetExtensionFunctionAddressForPlatform;
/* 480 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 481 */     BufferChecks.checkDirect(func_name);
/* 482 */     BufferChecks.checkNullTerminated(func_name);
/* 483 */     CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddressForPlatform(platform.getPointer(), MemoryUtil.getAddress(func_name), function_pointer));
/* 484 */     return __result; }
/*     */ 
/*     */   static native long nclGetExtensionFunctionAddressForPlatform(long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   static CLFunctionAddress clGetExtensionFunctionAddressForPlatform(CLPlatform platform, CharSequence func_name)
/*     */   {
/* 490 */     long function_pointer = CLCapabilities.clGetExtensionFunctionAddressForPlatform;
/* 491 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 492 */     CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddressForPlatform(platform.getPointer(), APIUtil.getBufferNT(func_name), function_pointer));
/* 493 */     return __result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL12
 * JD-Core Version:    0.6.2
 */