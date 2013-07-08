/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.nio.LongBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */import org.lwjgl.BufferChecks;
/*   9:    */import org.lwjgl.MemoryUtil;
/*  10:    */import org.lwjgl.PointerBuffer;
/*  11:    */
/*  27:    */public final class CL12
/*  28:    */{
/*  29:    */  public static final int CL_COMPILE_PROGRAM_FAILURE = -15;
/*  30:    */  public static final int CL_LINKER_NOT_AVAILABLE = -16;
/*  31:    */  public static final int CL_LINK_PROGRAM_FAILURE = -17;
/*  32:    */  public static final int CL_DEVICE_PARTITION_FAILED = -18;
/*  33:    */  public static final int CL_KERNEL_ARG_INFO_NOT_AVAILABLE = -19;
/*  34:    */  public static final int CL_INVALID_IMAGE_DESCRIPTOR = -65;
/*  35:    */  public static final int CL_INVALID_COMPILER_OPTIONS = -66;
/*  36:    */  public static final int CL_INVALID_LINKER_OPTIONS = -67;
/*  37:    */  public static final int CL_INVALID_DEVICE_PARTITION_COUNT = -68;
/*  38:    */  public static final int CL_VERSION_1_2 = 1;
/*  39:    */  public static final int CL_BLOCKING = 1;
/*  40:    */  public static final int CL_NON_BLOCKING = 0;
/*  41:    */  public static final int CL_DEVICE_TYPE_CUSTOM = 16;
/*  42:    */  public static final int CL_DEVICE_DOUBLE_FP_CONFIG = 4146;
/*  43:    */  public static final int CL_DEVICE_LINKER_AVAILABLE = 4158;
/*  44:    */  public static final int CL_DEVICE_BUILT_IN_KERNELS = 4159;
/*  45:    */  public static final int CL_DEVICE_IMAGE_MAX_BUFFER_SIZE = 4160;
/*  46:    */  public static final int CL_DEVICE_IMAGE_MAX_ARRAY_SIZE = 4161;
/*  47:    */  public static final int CL_DEVICE_PARENT_DEVICE = 4162;
/*  48:    */  public static final int CL_DEVICE_PARTITION_MAX_SUB_DEVICES = 4163;
/*  49:    */  public static final int CL_DEVICE_PARTITION_PROPERTIES = 4164;
/*  50:    */  public static final int CL_DEVICE_PARTITION_AFFINITY_DOMAIN = 4165;
/*  51:    */  public static final int CL_DEVICE_PARTITION_TYPE = 4166;
/*  52:    */  public static final int CL_DEVICE_REFERENCE_COUNT = 4167;
/*  53:    */  public static final int CL_DEVICE_PREFERRED_INTEROP_USER_SYNC = 4168;
/*  54:    */  public static final int CL_DEVICE_PRINTF_BUFFER_SIZE = 4169;
/*  55:    */  public static final int CL_FP_CORRECTLY_ROUNDED_DIVIDE_SQRT = 128;
/*  56:    */  public static final int CL_CONTEXT_INTEROP_USER_SYNC = 4229;
/*  57:    */  public static final int CL_DEVICE_PARTITION_EQUALLY = 4230;
/*  58:    */  public static final int CL_DEVICE_PARTITION_BY_COUNTS = 4231;
/*  59:    */  public static final int CL_DEVICE_PARTITION_BY_COUNTS_LIST_END = 0;
/*  60:    */  public static final int CL_DEVICE_PARTITION_BY_AFFINITY_DOMAIN = 4232;
/*  61:    */  public static final int CL_DEVICE_AFFINITY_DOMAIN_NUMA = 1;
/*  62:    */  public static final int CL_DEVICE_AFFINITY_DOMAIN_L4_CACHE = 2;
/*  63:    */  public static final int CL_DEVICE_AFFINITY_DOMAIN_L3_CACHE = 4;
/*  64:    */  public static final int CL_DEVICE_AFFINITY_DOMAIN_L2_CACHE = 8;
/*  65:    */  public static final int CL_DEVICE_AFFINITY_DOMAIN_L1_CACHE = 16;
/*  66:    */  public static final int CL_DEVICE_AFFINITY_DOMAIN_NEXT_PARTITIONABLE = 32;
/*  67:    */  public static final int CL_MEM_HOST_WRITE_ONLY = 128;
/*  68:    */  public static final int CL_MEM_HOST_READ_ONLY = 256;
/*  69:    */  public static final int CL_MEM_HOST_NO_ACCESS = 512;
/*  70:    */  public static final int CL_MIGRATE_MEM_OBJECT_HOST = 1;
/*  71:    */  public static final int CL_MIGRATE_MEM_OBJECT_CONTENT_UNDEFINED = 2;
/*  72:    */  public static final int CL_MEM_OBJECT_IMAGE2D_ARRAY = 4339;
/*  73:    */  public static final int CL_MEM_OBJECT_IMAGE1D = 4340;
/*  74:    */  public static final int CL_MEM_OBJECT_IMAGE1D_ARRAY = 4341;
/*  75:    */  public static final int CL_MEM_OBJECT_IMAGE1D_BUFFER = 4342;
/*  76:    */  public static final int CL_IMAGE_ARRAY_SIZE = 4375;
/*  77:    */  public static final int CL_IMAGE_BUFFER = 4376;
/*  78:    */  public static final int CL_IMAGE_NUM_MIP_LEVELS = 4377;
/*  79:    */  public static final int CL_IMAGE_NUM_SAMPLES = 4378;
/*  80:    */  public static final int CL_MAP_WRITE_INVALIDATE_REGION = 4;
/*  81:    */  public static final int CL_PROGRAM_NUM_KERNELS = 4455;
/*  82:    */  public static final int CL_PROGRAM_KERNEL_NAMES = 4456;
/*  83:    */  public static final int CL_PROGRAM_BINARY_TYPE = 4484;
/*  84:    */  public static final int CL_PROGRAM_BINARY_TYPE_NONE = 0;
/*  85:    */  public static final int CL_PROGRAM_BINARY_TYPE_COMPILED_OBJECT = 1;
/*  86:    */  public static final int CL_PROGRAM_BINARY_TYPE_LIBRARY = 2;
/*  87:    */  public static final int CL_PROGRAM_BINARY_TYPE_EXECUTABLE = 4;
/*  88:    */  public static final int CL_KERNEL_ATTRIBUTES = 4501;
/*  89:    */  public static final int CL_KERNEL_ARG_ADDRESS_QUALIFIER = 4502;
/*  90:    */  public static final int CL_KERNEL_ARG_ACCESS_QUALIFIER = 4503;
/*  91:    */  public static final int CL_KERNEL_ARG_TYPE_NAME = 4504;
/*  92:    */  public static final int CL_KERNEL_ARG_TYPE_QUALIFIER = 4505;
/*  93:    */  public static final int CL_KERNEL_ARG_NAME = 4506;
/*  94:    */  public static final int CL_KERNEL_ARG_ADDRESS_GLOBAL = 4506;
/*  95:    */  public static final int CL_KERNEL_ARG_ADDRESS_LOCAL = 4507;
/*  96:    */  public static final int CL_KERNEL_ARG_ADDRESS_CONSTANT = 4508;
/*  97:    */  public static final int CL_KERNEL_ARG_ADDRESS_PRIVATE = 4509;
/*  98:    */  public static final int CL_KERNEL_ARG_ACCESS_READ_ONLY = 4512;
/*  99:    */  public static final int CL_KERNEL_ARG_ACCESS_WRITE_ONLY = 4513;
/* 100:    */  public static final int CL_KERNEL_ARG_ACCESS_READ_WRITE = 4514;
/* 101:    */  public static final int CL_KERNEL_ARG_ACCESS_NONE = 4515;
/* 102:    */  public static final int CL_KERNEL_ARG_TYPE_NONE = 0;
/* 103:    */  public static final int CL_KERNEL_ARG_TYPE_CONST = 1;
/* 104:    */  public static final int CL_KERNEL_ARG_TYPE_RESTRICT = 2;
/* 105:    */  public static final int CL_KERNEL_ARG_TYPE_VOLATILE = 4;
/* 106:    */  public static final int CL_KERNEL_GLOBAL_WORK_SIZE = 4533;
/* 107:    */  public static final int CL_COMMAND_BARRIER = 4613;
/* 108:    */  public static final int CL_COMMAND_MIGRATE_MEM_OBJECTS = 4614;
/* 109:    */  public static final int CL_COMMAND_FILL_BUFFER = 4615;
/* 110:    */  public static final int CL_COMMAND_FILL_IMAGE = 4616;
/* 111:    */  
/* 112:    */  public static int clRetainDevice(CLDevice device)
/* 113:    */  {
/* 114:114 */    long function_pointer = CLCapabilities.clRetainDevice;
/* 115:115 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 116:116 */    int __result = nclRetainDevice(device.getPointer(), function_pointer);
/* 117:117 */    if (__result == 0) device.retain();
/* 118:118 */    return __result;
/* 119:    */  }
/* 120:    */  
/* 124:    */  static native int nclRetainDevice(long paramLong1, long paramLong2);
/* 125:    */  
/* 129:    */  public static int clReleaseDevice(CLDevice device)
/* 130:    */  {
/* 131:131 */    long function_pointer = CLCapabilities.clReleaseDevice;
/* 132:132 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 133:133 */    APIUtil.releaseObjects(device);
/* 134:134 */    int __result = nclReleaseDevice(device.getPointer(), function_pointer);
/* 135:135 */    if (__result == 0) device.release();
/* 136:136 */    return __result;
/* 137:    */  }
/* 138:    */  
/* 139:    */  static native int nclReleaseDevice(long paramLong1, long paramLong2);
/* 140:    */  
/* 141:141 */  public static int clCreateSubDevices(CLDevice in_device, LongBuffer properties, PointerBuffer out_devices, IntBuffer num_devices_ret) { long function_pointer = CLCapabilities.clCreateSubDevices;
/* 142:142 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 143:143 */    BufferChecks.checkDirect(properties);
/* 144:144 */    BufferChecks.checkNullTerminated(properties);
/* 145:145 */    if (out_devices != null)
/* 146:146 */      BufferChecks.checkDirect(out_devices);
/* 147:147 */    if (num_devices_ret != null)
/* 148:148 */      BufferChecks.checkBuffer(num_devices_ret, 1);
/* 149:149 */    int __result = nclCreateSubDevices(in_device.getPointer(), MemoryUtil.getAddress(properties), out_devices == null ? 0 : out_devices.remaining(), MemoryUtil.getAddressSafe(out_devices), MemoryUtil.getAddressSafe(num_devices_ret), function_pointer);
/* 150:150 */    if ((__result == 0) && (out_devices != null)) in_device.registerSubCLDevices(out_devices);
/* 151:151 */    return __result;
/* 152:    */  }
/* 153:    */  
/* 154:    */  static native int nclCreateSubDevices(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/* 155:    */  
/* 156:156 */  public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, ByteBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage;
/* 157:157 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 158:158 */    BufferChecks.checkBuffer(image_format, 8);
/* 159:159 */    BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 160:160 */    if (host_ptr != null)
/* 161:161 */      BufferChecks.checkDirect(host_ptr);
/* 162:162 */    if (errcode_ret != null)
/* 163:163 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 164:164 */    CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 165:165 */    return __result;
/* 166:    */  }
/* 167:    */  
/* 168:168 */  public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, FloatBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage;
/* 169:169 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 170:170 */    BufferChecks.checkBuffer(image_format, 8);
/* 171:171 */    BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 172:172 */    if (host_ptr != null)
/* 173:173 */      BufferChecks.checkDirect(host_ptr);
/* 174:174 */    if (errcode_ret != null)
/* 175:175 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 176:176 */    CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 177:177 */    return __result;
/* 178:    */  }
/* 179:    */  
/* 180:180 */  public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, IntBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage;
/* 181:181 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 182:182 */    BufferChecks.checkBuffer(image_format, 8);
/* 183:183 */    BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 184:184 */    if (host_ptr != null)
/* 185:185 */      BufferChecks.checkDirect(host_ptr);
/* 186:186 */    if (errcode_ret != null)
/* 187:187 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 188:188 */    CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 189:189 */    return __result;
/* 190:    */  }
/* 191:    */  
/* 192:192 */  public static CLMem clCreateImage(CLContext context, long flags, ByteBuffer image_format, ByteBuffer image_desc, ShortBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage;
/* 193:193 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 194:194 */    BufferChecks.checkBuffer(image_format, 8);
/* 195:195 */    BufferChecks.checkBuffer(image_desc, 7 * PointerBuffer.getPointerSize() + 8 + PointerBuffer.getPointerSize());
/* 196:196 */    if (host_ptr != null)
/* 197:197 */      BufferChecks.checkDirect(host_ptr);
/* 198:198 */    if (errcode_ret != null)
/* 199:199 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 200:200 */    CLMem __result = new CLMem(nclCreateImage(context.getPointer(), flags, MemoryUtil.getAddress(image_format), MemoryUtil.getAddress(image_desc), MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 201:201 */    return __result;
/* 202:    */  }
/* 203:    */  
/* 204:    */  static native long nclCreateImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/* 205:    */  
/* 206:206 */  public static CLProgram clCreateProgramWithBuiltInKernels(CLContext context, PointerBuffer device_list, ByteBuffer kernel_names, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateProgramWithBuiltInKernels;
/* 207:207 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 208:208 */    BufferChecks.checkBuffer(device_list, 1);
/* 209:209 */    BufferChecks.checkDirect(kernel_names);
/* 210:210 */    if (errcode_ret != null)
/* 211:211 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 212:212 */    CLProgram __result = new CLProgram(nclCreateProgramWithBuiltInKernels(context.getPointer(), device_list.remaining(), MemoryUtil.getAddress(device_list), MemoryUtil.getAddress(kernel_names), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 213:213 */    return __result;
/* 214:    */  }
/* 215:    */  
/* 216:    */  static native long nclCreateProgramWithBuiltInKernels(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 217:    */  
/* 218:    */  public static CLProgram clCreateProgramWithBuiltInKernels(CLContext context, PointerBuffer device_list, CharSequence kernel_names, IntBuffer errcode_ret) {
/* 219:219 */    long function_pointer = CLCapabilities.clCreateProgramWithBuiltInKernels;
/* 220:220 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 221:221 */    BufferChecks.checkBuffer(device_list, 1);
/* 222:222 */    if (errcode_ret != null)
/* 223:223 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 224:224 */    CLProgram __result = new CLProgram(nclCreateProgramWithBuiltInKernels(context.getPointer(), device_list.remaining(), MemoryUtil.getAddress(device_list), APIUtil.getBuffer(kernel_names), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 225:225 */    return __result;
/* 226:    */  }
/* 227:    */  
/* 230:    */  public static int clCompileProgram(CLProgram program, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_header, ByteBuffer header_include_name, CLCompileProgramCallback pfn_notify)
/* 231:    */  {
/* 232:232 */    long function_pointer = CLCapabilities.clCompileProgram;
/* 233:233 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 234:234 */    if (device_list != null)
/* 235:235 */      BufferChecks.checkDirect(device_list);
/* 236:236 */    BufferChecks.checkDirect(options);
/* 237:237 */    BufferChecks.checkNullTerminated(options);
/* 238:238 */    BufferChecks.checkBuffer(input_header, 1);
/* 239:239 */    BufferChecks.checkDirect(header_include_name);
/* 240:240 */    BufferChecks.checkNullTerminated(header_include_name);
/* 241:241 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 242:242 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 243:243 */    int __result = 0;
/* 244:    */    try {
/* 245:245 */      __result = nclCompileProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), 1, MemoryUtil.getAddress(input_header), MemoryUtil.getAddress(header_include_name), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 246:246 */      return __result;
/* 247:    */    } finally {
/* 248:248 */      CallbackUtil.checkCallback(__result, user_data);
/* 249:    */    }
/* 250:    */  }
/* 251:    */  
/* 254:    */  static native int nclCompileProgram(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
/* 255:    */  
/* 257:    */  public static int clCompileProgramMulti(CLProgram program, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_headers, ByteBuffer header_include_names, CLCompileProgramCallback pfn_notify)
/* 258:    */  {
/* 259:259 */    long function_pointer = CLCapabilities.clCompileProgram;
/* 260:260 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 261:261 */    if (device_list != null)
/* 262:262 */      BufferChecks.checkDirect(device_list);
/* 263:263 */    BufferChecks.checkDirect(options);
/* 264:264 */    BufferChecks.checkNullTerminated(options);
/* 265:265 */    BufferChecks.checkBuffer(input_headers, 1);
/* 266:266 */    BufferChecks.checkDirect(header_include_names);
/* 267:267 */    BufferChecks.checkNullTerminated(header_include_names, input_headers.remaining());
/* 268:268 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 269:269 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 270:270 */    int __result = 0;
/* 271:    */    try {
/* 272:272 */      __result = nclCompileProgramMulti(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), input_headers.remaining(), MemoryUtil.getAddress(input_headers), MemoryUtil.getAddress(header_include_names), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 273:273 */      return __result;
/* 274:    */    } finally {
/* 275:275 */      CallbackUtil.checkCallback(__result, user_data);
/* 276:    */    }
/* 277:    */  }
/* 278:    */  
/* 279:    */  static native int nclCompileProgramMulti(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
/* 280:    */  
/* 281:    */  public static int clCompileProgram(CLProgram program, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_headers, ByteBuffer[] header_include_names, CLCompileProgramCallback pfn_notify) {
/* 282:282 */    long function_pointer = CLCapabilities.clCompileProgram;
/* 283:283 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 284:284 */    if (device_list != null)
/* 285:285 */      BufferChecks.checkDirect(device_list);
/* 286:286 */    BufferChecks.checkDirect(options);
/* 287:287 */    BufferChecks.checkNullTerminated(options);
/* 288:288 */    BufferChecks.checkBuffer(input_headers, header_include_names.length);
/* 289:289 */    BufferChecks.checkArray(header_include_names, 1);
/* 290:290 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 291:291 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 292:292 */    int __result = 0;
/* 293:    */    try {
/* 294:294 */      __result = nclCompileProgram3(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), header_include_names.length, MemoryUtil.getAddress(input_headers), header_include_names, pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 295:295 */      return __result;
/* 296:    */    } finally {
/* 297:297 */      CallbackUtil.checkCallback(__result, user_data);
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 301:    */  static native int nclCompileProgram3(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, ByteBuffer[] paramArrayOfByteBuffer, long paramLong5, long paramLong6, long paramLong7);
/* 302:    */  
/* 303:    */  public static int clCompileProgram(CLProgram program, PointerBuffer device_list, CharSequence options, PointerBuffer input_header, CharSequence header_include_name, CLCompileProgramCallback pfn_notify) {
/* 304:304 */    long function_pointer = CLCapabilities.clCompileProgram;
/* 305:305 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 306:306 */    if (device_list != null)
/* 307:307 */      BufferChecks.checkDirect(device_list);
/* 308:308 */    BufferChecks.checkBuffer(input_header, 1);
/* 309:309 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 310:310 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 311:311 */    int __result = 0;
/* 312:    */    try {
/* 313:313 */      __result = nclCompileProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), 1, MemoryUtil.getAddress(input_header), APIUtil.getBufferNT(header_include_name), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 314:314 */      return __result;
/* 315:    */    } finally {
/* 316:316 */      CallbackUtil.checkCallback(__result, user_data);
/* 317:    */    }
/* 318:    */  }
/* 319:    */  
/* 320:    */  public static int clCompileProgram(CLProgram program, PointerBuffer device_list, CharSequence options, PointerBuffer input_header, CharSequence[] header_include_name, CLCompileProgramCallback pfn_notify)
/* 321:    */  {
/* 322:322 */    long function_pointer = CLCapabilities.clCompileProgram;
/* 323:323 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 324:324 */    if (device_list != null)
/* 325:325 */      BufferChecks.checkDirect(device_list);
/* 326:326 */    BufferChecks.checkBuffer(input_header, 1);
/* 327:327 */    BufferChecks.checkArray(header_include_name);
/* 328:328 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 329:329 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 330:330 */    int __result = 0;
/* 331:    */    try {
/* 332:332 */      __result = nclCompileProgramMulti(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), input_header.remaining(), MemoryUtil.getAddress(input_header), APIUtil.getBufferNT(header_include_name), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 333:333 */      return __result;
/* 334:    */    } finally {
/* 335:335 */      CallbackUtil.checkCallback(__result, user_data);
/* 336:    */    }
/* 337:    */  }
/* 338:    */  
/* 339:    */  public static CLProgram clLinkProgram(CLContext context, PointerBuffer device_list, ByteBuffer options, PointerBuffer input_programs, CLLinkProgramCallback pfn_notify, IntBuffer errcode_ret) {
/* 340:340 */    long function_pointer = CLCapabilities.clLinkProgram;
/* 341:341 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 342:342 */    if (device_list != null)
/* 343:343 */      BufferChecks.checkDirect(device_list);
/* 344:344 */    BufferChecks.checkDirect(options);
/* 345:345 */    BufferChecks.checkNullTerminated(options);
/* 346:346 */    BufferChecks.checkDirect(input_programs);
/* 347:347 */    BufferChecks.checkBuffer(errcode_ret, 1);
/* 348:348 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 349:349 */    if (pfn_notify != null) pfn_notify.setContext(context);
/* 350:350 */    CLProgram __result = null;
/* 351:    */    try {
/* 352:352 */      __result = new CLProgram(nclLinkProgram(context.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), input_programs.remaining(), MemoryUtil.getAddress(input_programs), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddress(errcode_ret), function_pointer), context);
/* 353:353 */      return __result;
/* 354:    */    } finally {
/* 355:355 */      CallbackUtil.checkCallback(errcode_ret.get(errcode_ret.position()), user_data);
/* 356:    */    }
/* 357:    */  }
/* 358:    */  
/* 359:    */  static native long nclLinkProgram(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
/* 360:    */  
/* 361:    */  public static CLProgram clLinkProgram(CLContext context, PointerBuffer device_list, CharSequence options, PointerBuffer input_programs, CLLinkProgramCallback pfn_notify, IntBuffer errcode_ret) {
/* 362:362 */    long function_pointer = CLCapabilities.clLinkProgram;
/* 363:363 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 364:364 */    if (device_list != null)
/* 365:365 */      BufferChecks.checkDirect(device_list);
/* 366:366 */    BufferChecks.checkDirect(input_programs);
/* 367:367 */    BufferChecks.checkBuffer(errcode_ret, 1);
/* 368:368 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 369:369 */    if (pfn_notify != null) pfn_notify.setContext(context);
/* 370:370 */    CLProgram __result = null;
/* 371:    */    try {
/* 372:372 */      __result = new CLProgram(nclLinkProgram(context.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), input_programs.remaining(), MemoryUtil.getAddress(input_programs), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddress(errcode_ret), function_pointer), context);
/* 373:373 */      return __result;
/* 374:    */    } finally {
/* 375:375 */      CallbackUtil.checkCallback(errcode_ret.get(errcode_ret.position()), user_data);
/* 376:    */    }
/* 377:    */  }
/* 378:    */  
/* 379:    */  public static int clUnloadPlatformCompiler(CLPlatform platform) {
/* 380:380 */    long function_pointer = CLCapabilities.clUnloadPlatformCompiler;
/* 381:381 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 382:382 */    int __result = nclUnloadPlatformCompiler(platform.getPointer(), function_pointer);
/* 383:383 */    return __result;
/* 384:    */  }
/* 385:    */  
/* 386:    */  static native int nclUnloadPlatformCompiler(long paramLong1, long paramLong2);
/* 387:    */  
/* 388:388 */  public static int clGetKernelArgInfo(CLKernel kernel, int arg_indx, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetKernelArgInfo;
/* 389:389 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 390:390 */    if (param_value != null)
/* 391:391 */      BufferChecks.checkDirect(param_value);
/* 392:392 */    if (param_value_size_ret != null)
/* 393:393 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 394:394 */    int __result = nclGetKernelArgInfo(kernel.getPointer(), arg_indx, param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 395:395 */    return __result;
/* 396:    */  }
/* 397:    */  
/* 398:    */  static native int nclGetKernelArgInfo(long paramLong1, int paramInt1, int paramInt2, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 399:    */  
/* 400:400 */  public static int clEnqueueFillBuffer(CLCommandQueue command_queue, CLMem buffer, ByteBuffer pattern, long offset, long size, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueFillBuffer;
/* 401:401 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 402:402 */    BufferChecks.checkDirect(pattern);
/* 403:403 */    if (event_wait_list != null)
/* 404:404 */      BufferChecks.checkDirect(event_wait_list);
/* 405:405 */    if (event != null)
/* 406:406 */      BufferChecks.checkBuffer(event, 1);
/* 407:407 */    int __result = nclEnqueueFillBuffer(command_queue.getPointer(), buffer.getPointer(), MemoryUtil.getAddress(pattern), pattern.remaining(), offset, size, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 408:408 */    return __result;
/* 409:    */  }
/* 410:    */  
/* 411:    */  static native int nclEnqueueFillBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/* 412:    */  
/* 413:413 */  public static int clEnqueueFillImage(CLCommandQueue command_queue, CLMem image, ByteBuffer fill_color, PointerBuffer origin, PointerBuffer region, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueFillImage;
/* 414:414 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 415:415 */    BufferChecks.checkBuffer(fill_color, 16);
/* 416:416 */    BufferChecks.checkBuffer(origin, 3);
/* 417:417 */    BufferChecks.checkBuffer(region, 3);
/* 418:418 */    if (event_wait_list != null)
/* 419:419 */      BufferChecks.checkDirect(event_wait_list);
/* 420:420 */    if (event != null)
/* 421:421 */      BufferChecks.checkBuffer(event, 1);
/* 422:422 */    int __result = nclEnqueueFillImage(command_queue.getPointer(), image.getPointer(), MemoryUtil.getAddress(fill_color), MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 423:423 */    return __result;
/* 424:    */  }
/* 425:    */  
/* 426:    */  static native int nclEnqueueFillImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, int paramInt, long paramLong6, long paramLong7, long paramLong8);
/* 427:    */  
/* 428:428 */  public static int clEnqueueMigrateMemObjects(CLCommandQueue command_queue, PointerBuffer mem_objects, long flags, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueMigrateMemObjects;
/* 429:429 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 430:430 */    BufferChecks.checkDirect(mem_objects);
/* 431:431 */    if (event_wait_list != null)
/* 432:432 */      BufferChecks.checkDirect(event_wait_list);
/* 433:433 */    if (event != null)
/* 434:434 */      BufferChecks.checkBuffer(event, 1);
/* 435:435 */    int __result = nclEnqueueMigrateMemObjects(command_queue.getPointer(), mem_objects.remaining(), MemoryUtil.getAddress(mem_objects), flags, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 436:436 */    return __result;
/* 437:    */  }
/* 438:    */  
/* 439:    */  static native int nclEnqueueMigrateMemObjects(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6);
/* 440:    */  
/* 441:441 */  public static int clEnqueueMarkerWithWaitList(CLCommandQueue command_queue, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueMarkerWithWaitList;
/* 442:442 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 443:443 */    if (event_wait_list != null)
/* 444:444 */      BufferChecks.checkDirect(event_wait_list);
/* 445:445 */    if (event != null)
/* 446:446 */      BufferChecks.checkBuffer(event, 1);
/* 447:447 */    int __result = nclEnqueueMarkerWithWaitList(command_queue.getPointer(), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 448:448 */    return __result;
/* 449:    */  }
/* 450:    */  
/* 451:    */  static native int nclEnqueueMarkerWithWaitList(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/* 452:    */  
/* 453:453 */  public static int clEnqueueBarrierWithWaitList(CLCommandQueue command_queue, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueBarrierWithWaitList;
/* 454:454 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 455:455 */    if (event_wait_list != null)
/* 456:456 */      BufferChecks.checkDirect(event_wait_list);
/* 457:457 */    if (event != null)
/* 458:458 */      BufferChecks.checkBuffer(event, 1);
/* 459:459 */    int __result = nclEnqueueBarrierWithWaitList(command_queue.getPointer(), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 460:460 */    return __result;
/* 461:    */  }
/* 462:    */  
/* 463:    */  static native int nclEnqueueBarrierWithWaitList(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/* 464:    */  
/* 465:465 */  public static int clSetPrintfCallback(CLContext context, CLPrintfCallback pfn_notify) { long function_pointer = CLCapabilities.clSetPrintfCallback;
/* 466:466 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 467:467 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 468:468 */    int __result = 0;
/* 469:    */    try {
/* 470:470 */      __result = nclSetPrintfCallback(context.getPointer(), pfn_notify.getPointer(), user_data, function_pointer);
/* 471:471 */      return __result;
/* 472:    */    } finally {
/* 473:473 */      context.setPrintfCallback(user_data, __result);
/* 474:    */    }
/* 475:    */  }
/* 476:    */  
/* 477:    */  static native int nclSetPrintfCallback(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 478:    */  
/* 479:479 */  static CLFunctionAddress clGetExtensionFunctionAddressForPlatform(CLPlatform platform, ByteBuffer func_name) { long function_pointer = CLCapabilities.clGetExtensionFunctionAddressForPlatform;
/* 480:480 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 481:481 */    BufferChecks.checkDirect(func_name);
/* 482:482 */    BufferChecks.checkNullTerminated(func_name);
/* 483:483 */    CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddressForPlatform(platform.getPointer(), MemoryUtil.getAddress(func_name), function_pointer));
/* 484:484 */    return __result;
/* 485:    */  }
/* 486:    */  
/* 487:    */  static native long nclGetExtensionFunctionAddressForPlatform(long paramLong1, long paramLong2, long paramLong3);
/* 488:    */  
/* 489:    */  static CLFunctionAddress clGetExtensionFunctionAddressForPlatform(CLPlatform platform, CharSequence func_name) {
/* 490:490 */    long function_pointer = CLCapabilities.clGetExtensionFunctionAddressForPlatform;
/* 491:491 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 492:492 */    CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddressForPlatform(platform.getPointer(), APIUtil.getBufferNT(func_name), function_pointer));
/* 493:493 */    return __result;
/* 494:    */  }
/* 495:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL12
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */