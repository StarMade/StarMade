/*    1:     */package org.lwjgl.opencl;
/*    2:     */
/*    3:     */import java.nio.Buffer;
/*    4:     */import java.nio.ByteBuffer;
/*    5:     */import java.nio.ByteOrder;
/*    6:     */import java.nio.DoubleBuffer;
/*    7:     */import java.nio.FloatBuffer;
/*    8:     */import java.nio.IntBuffer;
/*    9:     */import java.nio.LongBuffer;
/*   10:     */import java.nio.ShortBuffer;
/*   11:     */import org.lwjgl.BufferChecks;
/*   12:     */import org.lwjgl.LWJGLUtil;
/*   13:     */import org.lwjgl.MemoryUtil;
/*   14:     */import org.lwjgl.PointerBuffer;
/*   15:     */
/*  143:     */public final class CL10
/*  144:     */{
/*  145:     */  public static final int CL_SUCCESS = 0;
/*  146:     */  public static final int CL_DEVICE_NOT_FOUND = -1;
/*  147:     */  public static final int CL_DEVICE_NOT_AVAILABLE = -2;
/*  148:     */  public static final int CL_COMPILER_NOT_AVAILABLE = -3;
/*  149:     */  public static final int CL_MEM_OBJECT_ALLOCATION_FAILURE = -4;
/*  150:     */  public static final int CL_OUT_OF_RESOURCES = -5;
/*  151:     */  public static final int CL_OUT_OF_HOST_MEMORY = -6;
/*  152:     */  public static final int CL_PROFILING_INFO_NOT_AVAILABLE = -7;
/*  153:     */  public static final int CL_MEM_COPY_OVERLAP = -8;
/*  154:     */  public static final int CL_IMAGE_FORMAT_MISMATCH = -9;
/*  155:     */  public static final int CL_IMAGE_FORMAT_NOT_SUPPORTED = -10;
/*  156:     */  public static final int CL_BUILD_PROGRAM_FAILURE = -11;
/*  157:     */  public static final int CL_MAP_FAILURE = -12;
/*  158:     */  public static final int CL_INVALID_VALUE = -30;
/*  159:     */  public static final int CL_INVALID_DEVICE_TYPE = -31;
/*  160:     */  public static final int CL_INVALID_PLATFORM = -32;
/*  161:     */  public static final int CL_INVALID_DEVICE = -33;
/*  162:     */  public static final int CL_INVALID_CONTEXT = -34;
/*  163:     */  public static final int CL_INVALID_QUEUE_PROPERTIES = -35;
/*  164:     */  public static final int CL_INVALID_COMMAND_QUEUE = -36;
/*  165:     */  public static final int CL_INVALID_HOST_PTR = -37;
/*  166:     */  public static final int CL_INVALID_MEM_OBJECT = -38;
/*  167:     */  public static final int CL_INVALID_IMAGE_FORMAT_DESCRIPTOR = -39;
/*  168:     */  public static final int CL_INVALID_IMAGE_SIZE = -40;
/*  169:     */  public static final int CL_INVALID_SAMPLER = -41;
/*  170:     */  public static final int CL_INVALID_BINARY = -42;
/*  171:     */  public static final int CL_INVALID_BUILD_OPTIONS = -43;
/*  172:     */  public static final int CL_INVALID_PROGRAM = -44;
/*  173:     */  public static final int CL_INVALID_PROGRAM_EXECUTABLE = -45;
/*  174:     */  public static final int CL_INVALID_KERNEL_NAME = -46;
/*  175:     */  public static final int CL_INVALID_KERNEL_DEFINITION = -47;
/*  176:     */  public static final int CL_INVALID_KERNEL = -48;
/*  177:     */  public static final int CL_INVALID_ARG_INDEX = -49;
/*  178:     */  public static final int CL_INVALID_ARG_VALUE = -50;
/*  179:     */  public static final int CL_INVALID_ARG_SIZE = -51;
/*  180:     */  public static final int CL_INVALID_KERNEL_ARGS = -52;
/*  181:     */  public static final int CL_INVALID_WORK_DIMENSION = -53;
/*  182:     */  public static final int CL_INVALID_WORK_GROUP_SIZE = -54;
/*  183:     */  public static final int CL_INVALID_WORK_ITEM_SIZE = -55;
/*  184:     */  public static final int CL_INVALID_GLOBAL_OFFSET = -56;
/*  185:     */  public static final int CL_INVALID_EVENT_WAIT_LIST = -57;
/*  186:     */  public static final int CL_INVALID_EVENT = -58;
/*  187:     */  public static final int CL_INVALID_OPERATION = -59;
/*  188:     */  public static final int CL_INVALID_GL_OBJECT = -60;
/*  189:     */  public static final int CL_INVALID_BUFFER_SIZE = -61;
/*  190:     */  public static final int CL_INVALID_MIP_LEVEL = -62;
/*  191:     */  public static final int CL_INVALID_GLOBAL_WORK_SIZE = -63;
/*  192:     */  public static final int CL_VERSION_1_0 = 1;
/*  193:     */  public static final int CL_FALSE = 0;
/*  194:     */  public static final int CL_TRUE = 1;
/*  195:     */  public static final int CL_PLATFORM_PROFILE = 2304;
/*  196:     */  public static final int CL_PLATFORM_VERSION = 2305;
/*  197:     */  public static final int CL_PLATFORM_NAME = 2306;
/*  198:     */  public static final int CL_PLATFORM_VENDOR = 2307;
/*  199:     */  public static final int CL_PLATFORM_EXTENSIONS = 2308;
/*  200:     */  public static final int CL_DEVICE_TYPE_DEFAULT = 1;
/*  201:     */  public static final int CL_DEVICE_TYPE_CPU = 2;
/*  202:     */  public static final int CL_DEVICE_TYPE_GPU = 4;
/*  203:     */  public static final int CL_DEVICE_TYPE_ACCELERATOR = 8;
/*  204:     */  public static final int CL_DEVICE_TYPE_ALL = -1;
/*  205:     */  public static final int CL_DEVICE_TYPE = 4096;
/*  206:     */  public static final int CL_DEVICE_VENDOR_ID = 4097;
/*  207:     */  public static final int CL_DEVICE_MAX_COMPUTE_UNITS = 4098;
/*  208:     */  public static final int CL_DEVICE_MAX_WORK_ITEM_DIMENSIONS = 4099;
/*  209:     */  public static final int CL_DEVICE_MAX_WORK_GROUP_SIZE = 4100;
/*  210:     */  public static final int CL_DEVICE_MAX_WORK_ITEM_SIZES = 4101;
/*  211:     */  public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_CHAR = 4102;
/*  212:     */  public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_SHORT = 4103;
/*  213:     */  public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_ = 4104;
/*  214:     */  public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_LONG = 4105;
/*  215:     */  public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_FLOAT = 4106;
/*  216:     */  public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_DOUBLE = 4107;
/*  217:     */  public static final int CL_DEVICE_MAX_CLOCK_FREQUENCY = 4108;
/*  218:     */  public static final int CL_DEVICE_ADDRESS_BITS = 4109;
/*  219:     */  public static final int CL_DEVICE_MAX_READ_IMAGE_ARGS = 4110;
/*  220:     */  public static final int CL_DEVICE_MAX_WRITE_IMAGE_ARGS = 4111;
/*  221:     */  public static final int CL_DEVICE_MAX_MEM_ALLOC_SIZE = 4112;
/*  222:     */  public static final int CL_DEVICE_IMAGE2D_MAX_WIDTH = 4113;
/*  223:     */  public static final int CL_DEVICE_IMAGE2D_MAX_HEIGHT = 4114;
/*  224:     */  public static final int CL_DEVICE_IMAGE3D_MAX_WIDTH = 4115;
/*  225:     */  public static final int CL_DEVICE_IMAGE3D_MAX_HEIGHT = 4116;
/*  226:     */  public static final int CL_DEVICE_IMAGE3D_MAX_DEPTH = 4117;
/*  227:     */  public static final int CL_DEVICE_IMAGE_SUPPORT = 4118;
/*  228:     */  public static final int CL_DEVICE_MAX_PARAMETER_SIZE = 4119;
/*  229:     */  public static final int CL_DEVICE_MAX_SAMPLERS = 4120;
/*  230:     */  public static final int CL_DEVICE_MEM_BASE_ADDR_ALIGN = 4121;
/*  231:     */  public static final int CL_DEVICE_MIN_DATA_TYPE_ALIGN_SIZE = 4122;
/*  232:     */  public static final int CL_DEVICE_SINGLE_FP_CONFIG = 4123;
/*  233:     */  public static final int CL_DEVICE_GLOBAL_MEM_CACHE_TYPE = 4124;
/*  234:     */  public static final int CL_DEVICE_GLOBAL_MEM_CACHELINE_SIZE = 4125;
/*  235:     */  public static final int CL_DEVICE_GLOBAL_MEM_CACHE_SIZE = 4126;
/*  236:     */  public static final int CL_DEVICE_GLOBAL_MEM_SIZE = 4127;
/*  237:     */  public static final int CL_DEVICE_MAX_CONSTANT_BUFFER_SIZE = 4128;
/*  238:     */  public static final int CL_DEVICE_MAX_CONSTANT_ARGS = 4129;
/*  239:     */  public static final int CL_DEVICE_LOCAL_MEM_TYPE = 4130;
/*  240:     */  public static final int CL_DEVICE_LOCAL_MEM_SIZE = 4131;
/*  241:     */  public static final int CL_DEVICE_ERROR_CORRECTION_SUPPORT = 4132;
/*  242:     */  public static final int CL_DEVICE_PROFILING_TIMER_RESOLUTION = 4133;
/*  243:     */  public static final int CL_DEVICE_ENDIAN_LITTLE = 4134;
/*  244:     */  public static final int CL_DEVICE_AVAILABLE = 4135;
/*  245:     */  public static final int CL_DEVICE_COMPILER_AVAILABLE = 4136;
/*  246:     */  public static final int CL_DEVICE_EXECUTION_CAPABILITIES = 4137;
/*  247:     */  public static final int CL_DEVICE_QUEUE_PROPERTIES = 4138;
/*  248:     */  public static final int CL_DEVICE_NAME = 4139;
/*  249:     */  public static final int CL_DEVICE_VENDOR = 4140;
/*  250:     */  public static final int CL_DRIVER_VERSION = 4141;
/*  251:     */  public static final int CL_DEVICE_PROFILE = 4142;
/*  252:     */  public static final int CL_DEVICE_VERSION = 4143;
/*  253:     */  public static final int CL_DEVICE_EXTENSIONS = 4144;
/*  254:     */  public static final int CL_DEVICE_PLATFORM = 4145;
/*  255:     */  public static final int CL_FP_DENORM = 1;
/*  256:     */  public static final int CL_FP_INF_NAN = 2;
/*  257:     */  public static final int CL_FP_ROUND_TO_NEAREST = 4;
/*  258:     */  public static final int CL_FP_ROUND_TO_ZERO = 8;
/*  259:     */  public static final int CL_FP_ROUND_TO_INF = 16;
/*  260:     */  public static final int CL_FP_FMA = 32;
/*  261:     */  public static final int CL_NONE = 0;
/*  262:     */  public static final int CL_READ_ONLY_CACHE = 1;
/*  263:     */  public static final int CL_READ_WRITE_CACHE = 2;
/*  264:     */  public static final int CL_LOCAL = 1;
/*  265:     */  public static final int CL_GLOBAL = 2;
/*  266:     */  public static final int CL_EXEC_KERNEL = 1;
/*  267:     */  public static final int CL_EXEC_NATIVE_KERNEL = 2;
/*  268:     */  public static final int CL_QUEUE_OUT_OF_ORDER_EXEC_MODE_ENABLE = 1;
/*  269:     */  public static final int CL_QUEUE_PROFILING_ENABLE = 2;
/*  270:     */  public static final int CL_CONTEXT_REFERENCE_COUNT = 4224;
/*  271:     */  public static final int CL_CONTEXT_DEVICES = 4225;
/*  272:     */  public static final int CL_CONTEXT_PROPERTIES = 4226;
/*  273:     */  public static final int CL_CONTEXT_PLATFORM = 4228;
/*  274:     */  public static final int CL_QUEUE_CONTEXT = 4240;
/*  275:     */  public static final int CL_QUEUE_DEVICE = 4241;
/*  276:     */  public static final int CL_QUEUE_REFERENCE_COUNT = 4242;
/*  277:     */  public static final int CL_QUEUE_PROPERTIES = 4243;
/*  278:     */  public static final int CL_MEM_READ_WRITE = 1;
/*  279:     */  public static final int CL_MEM_WRITE_ONLY = 2;
/*  280:     */  public static final int CL_MEM_READ_ONLY = 4;
/*  281:     */  public static final int CL_MEM_USE_HOST_PTR = 8;
/*  282:     */  public static final int CL_MEM_ALLOC_HOST_PTR = 16;
/*  283:     */  public static final int CL_MEM_COPY_HOST_PTR = 32;
/*  284:     */  public static final int CL_R = 4272;
/*  285:     */  public static final int CL_A = 4273;
/*  286:     */  public static final int CL_RG = 4274;
/*  287:     */  public static final int CL_RA = 4275;
/*  288:     */  public static final int CL_RGB = 4276;
/*  289:     */  public static final int CL_RGBA = 4277;
/*  290:     */  public static final int CL_BGRA = 4278;
/*  291:     */  public static final int CL_ARGB = 4279;
/*  292:     */  public static final int CL_INTENSITY = 4280;
/*  293:     */  public static final int CL_LUMINANCE = 4281;
/*  294:     */  public static final int CL_SNORM_INT8 = 4304;
/*  295:     */  public static final int CL_SNORM_INT16 = 4305;
/*  296:     */  public static final int CL_UNORM_INT8 = 4306;
/*  297:     */  public static final int CL_UNORM_INT16 = 4307;
/*  298:     */  public static final int CL_UNORM_SHORT_565 = 4308;
/*  299:     */  public static final int CL_UNORM_SHORT_555 = 4309;
/*  300:     */  public static final int CL_UNORM_INT_101010 = 4310;
/*  301:     */  public static final int CL_SIGNED_INT8 = 4311;
/*  302:     */  public static final int CL_SIGNED_INT16 = 4312;
/*  303:     */  public static final int CL_SIGNED_INT32 = 4313;
/*  304:     */  public static final int CL_UNSIGNED_INT8 = 4314;
/*  305:     */  public static final int CL_UNSIGNED_INT16 = 4315;
/*  306:     */  public static final int CL_UNSIGNED_INT32 = 4316;
/*  307:     */  public static final int CL_HALF_FLOAT = 4317;
/*  308:     */  public static final int CL_FLOAT = 4318;
/*  309:     */  public static final int CL_MEM_OBJECT_BUFFER = 4336;
/*  310:     */  public static final int CL_MEM_OBJECT_IMAGE2D = 4337;
/*  311:     */  public static final int CL_MEM_OBJECT_IMAGE3D = 4338;
/*  312:     */  public static final int CL_MEM_TYPE = 4352;
/*  313:     */  public static final int CL_MEM_FLAGS = 4353;
/*  314:     */  public static final int CL_MEM_SIZE = 4354;
/*  315:     */  public static final int CL_MEM_HOST_PTR = 4355;
/*  316:     */  public static final int CL_MEM_MAP_COUNT = 4356;
/*  317:     */  public static final int CL_MEM_REFERENCE_COUNT = 4357;
/*  318:     */  public static final int CL_MEM_CONTEXT = 4358;
/*  319:     */  public static final int CL_IMAGE_FORMAT = 4368;
/*  320:     */  public static final int CL_IMAGE_ELEMENT_SIZE = 4369;
/*  321:     */  public static final int CL_IMAGE_ROW_PITCH = 4370;
/*  322:     */  public static final int CL_IMAGE_SLICE_PITCH = 4371;
/*  323:     */  public static final int CL_IMAGE_WIDTH = 4372;
/*  324:     */  public static final int CL_IMAGE_HEIGHT = 4373;
/*  325:     */  public static final int CL_IMAGE_DEPTH = 4374;
/*  326:     */  public static final int CL_ADDRESS_NONE = 4400;
/*  327:     */  public static final int CL_ADDRESS_CLAMP_TO_EDGE = 4401;
/*  328:     */  public static final int CL_ADDRESS_CLAMP = 4402;
/*  329:     */  public static final int CL_ADDRESS_REPEAT = 4403;
/*  330:     */  public static final int CL_FILTER_NEAREST = 4416;
/*  331:     */  public static final int CL_FILTER_LINEAR = 4417;
/*  332:     */  public static final int CL_SAMPLER_REFERENCE_COUNT = 4432;
/*  333:     */  public static final int CL_SAMPLER_CONTEXT = 4433;
/*  334:     */  public static final int CL_SAMPLER_NORMALIZED_COORDS = 4434;
/*  335:     */  public static final int CL_SAMPLER_ADDRESSING_MODE = 4435;
/*  336:     */  public static final int CL_SAMPLER_FILTER_MODE = 4436;
/*  337:     */  public static final int CL_MAP_READ = 1;
/*  338:     */  public static final int CL_MAP_WRITE = 2;
/*  339:     */  public static final int CL_PROGRAM_REFERENCE_COUNT = 4448;
/*  340:     */  public static final int CL_PROGRAM_CONTEXT = 4449;
/*  341:     */  public static final int CL_PROGRAM_NUM_DEVICES = 4450;
/*  342:     */  public static final int CL_PROGRAM_DEVICES = 4451;
/*  343:     */  public static final int CL_PROGRAM_SOURCE = 4452;
/*  344:     */  public static final int CL_PROGRAM_BINARY_SIZES = 4453;
/*  345:     */  public static final int CL_PROGRAM_BINARIES = 4454;
/*  346:     */  public static final int CL_PROGRAM_BUILD_STATUS = 4481;
/*  347:     */  public static final int CL_PROGRAM_BUILD_OPTIONS = 4482;
/*  348:     */  public static final int CL_PROGRAM_BUILD_LOG = 4483;
/*  349:     */  public static final int CL_BUILD_SUCCESS = 0;
/*  350:     */  public static final int CL_BUILD_NONE = -1;
/*  351:     */  public static final int CL_BUILD_ERROR = -2;
/*  352:     */  public static final int CL_BUILD_IN_PROGRESS = -3;
/*  353:     */  public static final int CL_KERNEL_FUNCTION_NAME = 4496;
/*  354:     */  public static final int CL_KERNEL_NUM_ARGS = 4497;
/*  355:     */  public static final int CL_KERNEL_REFERENCE_COUNT = 4498;
/*  356:     */  public static final int CL_KERNEL_CONTEXT = 4499;
/*  357:     */  public static final int CL_KERNEL_PROGRAM = 4500;
/*  358:     */  public static final int CL_KERNEL_WORK_GROUP_SIZE = 4528;
/*  359:     */  public static final int CL_KERNEL_COMPILE_WORK_GROUP_SIZE = 4529;
/*  360:     */  public static final int CL_KERNEL_LOCAL_MEM_SIZE = 4530;
/*  361:     */  public static final int CL_EVENT_COMMAND_QUEUE = 4560;
/*  362:     */  public static final int CL_EVENT_COMMAND_TYPE = 4561;
/*  363:     */  public static final int CL_EVENT_REFERENCE_COUNT = 4562;
/*  364:     */  public static final int CL_EVENT_COMMAND_EXECUTION_STATUS = 4563;
/*  365:     */  public static final int CL_COMMAND_NDRANGE_KERNEL = 4592;
/*  366:     */  public static final int CL_COMMAND_TASK = 4593;
/*  367:     */  public static final int CL_COMMAND_NATIVE_KERNEL = 4594;
/*  368:     */  public static final int CL_COMMAND_READ_BUFFER = 4595;
/*  369:     */  public static final int CL_COMMAND_WRITE_BUFFER = 4596;
/*  370:     */  public static final int CL_COMMAND_COPY_BUFFER = 4597;
/*  371:     */  public static final int CL_COMMAND_READ_IMAGE = 4598;
/*  372:     */  public static final int CL_COMMAND_WRITE_IMAGE = 4599;
/*  373:     */  public static final int CL_COMMAND_COPY_IMAGE = 4600;
/*  374:     */  public static final int CL_COMMAND_COPY_IMAGE_TO_BUFFER = 4601;
/*  375:     */  public static final int CL_COMMAND_COPY_BUFFER_TO_IMAGE = 4602;
/*  376:     */  public static final int CL_COMMAND_MAP_BUFFER = 4603;
/*  377:     */  public static final int CL_COMMAND_MAP_IMAGE = 4604;
/*  378:     */  public static final int CL_COMMAND_UNMAP_MEM_OBJECT = 4605;
/*  379:     */  public static final int CL_COMMAND_MARKER = 4606;
/*  380:     */  public static final int CL_COMMAND_ACQUIRE_GL_OBJECTS = 4607;
/*  381:     */  public static final int CL_COMMAND_RELEASE_GL_OBJECTS = 4608;
/*  382:     */  public static final int CL_COMPLETE = 0;
/*  383:     */  public static final int CL_RUNNING = 1;
/*  384:     */  public static final int CL_SUBMITTED = 2;
/*  385:     */  public static final int CL_QUEUED = 3;
/*  386:     */  public static final int CL_PROFILING_COMMAND_QUEUED = 4736;
/*  387:     */  public static final int CL_PROFILING_COMMAND_SUBMIT = 4737;
/*  388:     */  public static final int CL_PROFILING_COMMAND_START = 4738;
/*  389:     */  public static final int CL_PROFILING_COMMAND_END = 4739;
/*  390:     */  
/*  391:     */  public static int clGetPlatformIDs(PointerBuffer platforms, IntBuffer num_platforms)
/*  392:     */  {
/*  393: 393 */    long function_pointer = CLCapabilities.clGetPlatformIDs;
/*  394: 394 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  395: 395 */    if (platforms != null)
/*  396: 396 */      BufferChecks.checkDirect(platforms);
/*  397: 397 */    if (num_platforms != null)
/*  398: 398 */      BufferChecks.checkBuffer(num_platforms, 1);
/*  399: 399 */    if (num_platforms == null) num_platforms = APIUtil.getBufferInt();
/*  400: 400 */    int __result = nclGetPlatformIDs(platforms == null ? 0 : platforms.remaining(), MemoryUtil.getAddressSafe(platforms), MemoryUtil.getAddressSafe(num_platforms), function_pointer);
/*  401: 401 */    if ((__result == 0) && (platforms != null)) CLPlatform.registerCLPlatforms(platforms, num_platforms);
/*  402: 402 */    return __result;
/*  403:     */  }
/*  404:     */  
/*  405:     */  static native int nclGetPlatformIDs(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*  406:     */  
/*  407: 407 */  public static int clGetPlatformInfo(CLPlatform platform, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetPlatformInfo;
/*  408: 408 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  409: 409 */    if (param_value != null)
/*  410: 410 */      BufferChecks.checkDirect(param_value);
/*  411: 411 */    if (param_value_size_ret != null)
/*  412: 412 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  413: 413 */    int __result = nclGetPlatformInfo(platform == null ? 0L : platform.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  414: 414 */    return __result;
/*  415:     */  }
/*  416:     */  
/*  417:     */  static native int nclGetPlatformInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  418:     */  
/*  419: 419 */  public static int clGetDeviceIDs(CLPlatform platform, long device_type, PointerBuffer devices, IntBuffer num_devices) { long function_pointer = CLCapabilities.clGetDeviceIDs;
/*  420: 420 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  421: 421 */    if (devices != null)
/*  422: 422 */      BufferChecks.checkDirect(devices);
/*  423: 423 */    if (num_devices != null) {
/*  424: 424 */      BufferChecks.checkBuffer(num_devices, 1);
/*  425:     */    } else
/*  426: 426 */      num_devices = APIUtil.getBufferInt();
/*  427: 427 */    int __result = nclGetDeviceIDs(platform.getPointer(), device_type, devices == null ? 0 : devices.remaining(), MemoryUtil.getAddressSafe(devices), MemoryUtil.getAddressSafe(num_devices), function_pointer);
/*  428: 428 */    if ((__result == 0) && (devices != null)) platform.registerCLDevices(devices, num_devices);
/*  429: 429 */    return __result;
/*  430:     */  }
/*  431:     */  
/*  432:     */  static native int nclGetDeviceIDs(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/*  433:     */  
/*  434: 434 */  public static int clGetDeviceInfo(CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetDeviceInfo;
/*  435: 435 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  436: 436 */    if (param_value != null)
/*  437: 437 */      BufferChecks.checkDirect(param_value);
/*  438: 438 */    if (param_value_size_ret != null)
/*  439: 439 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  440: 440 */    int __result = nclGetDeviceInfo(device.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  441: 441 */    return __result;
/*  442:     */  }
/*  443:     */  
/*  445:     */  static native int nclGetDeviceInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  446:     */  
/*  447:     */  public static CLContext clCreateContext(PointerBuffer properties, PointerBuffer devices, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*  448:     */  {
/*  449: 449 */    long function_pointer = CLCapabilities.clCreateContext;
/*  450: 450 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  451: 451 */    BufferChecks.checkBuffer(properties, 3);
/*  452: 452 */    BufferChecks.checkNullTerminated(properties);
/*  453: 453 */    BufferChecks.checkBuffer(devices, 1);
/*  454: 454 */    if (errcode_ret != null)
/*  455: 455 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  456: 456 */    long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/*  457: 457 */    CLContext __result = null;
/*  458:     */    try {
/*  459: 459 */      __result = new CLContext(nclCreateContext(MemoryUtil.getAddress(properties), devices.remaining(), MemoryUtil.getAddress(devices), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), APIUtil.getCLPlatform(properties));
/*  460: 460 */      return __result;
/*  461:     */    } finally {
/*  462: 462 */      if (__result != null) { __result.setContextCallback(user_data);
/*  463:     */      }
/*  464:     */    }
/*  465:     */  }
/*  466:     */  
/*  468:     */  static native long nclCreateContext(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*  469:     */  
/*  471:     */  public static CLContext clCreateContext(PointerBuffer properties, CLDevice device, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*  472:     */  {
/*  473: 473 */    long function_pointer = CLCapabilities.clCreateContext;
/*  474: 474 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  475: 475 */    BufferChecks.checkBuffer(properties, 3);
/*  476: 476 */    BufferChecks.checkNullTerminated(properties);
/*  477: 477 */    if (errcode_ret != null)
/*  478: 478 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  479: 479 */    long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/*  480: 480 */    CLContext __result = null;
/*  481:     */    try {
/*  482: 482 */      __result = new CLContext(nclCreateContext(MemoryUtil.getAddress(properties), 1, APIUtil.getPointer(device), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), APIUtil.getCLPlatform(properties));
/*  483: 483 */      return __result;
/*  484:     */    } finally {
/*  485: 485 */      if (__result != null) { __result.setContextCallback(user_data);
/*  486:     */      }
/*  487:     */    }
/*  488:     */  }
/*  489:     */  
/*  491:     */  public static CLContext clCreateContextFromType(PointerBuffer properties, long device_type, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*  492:     */  {
/*  493: 493 */    long function_pointer = CLCapabilities.clCreateContextFromType;
/*  494: 494 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  495: 495 */    BufferChecks.checkBuffer(properties, 3);
/*  496: 496 */    BufferChecks.checkNullTerminated(properties);
/*  497: 497 */    if (errcode_ret != null)
/*  498: 498 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  499: 499 */    long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/*  500: 500 */    CLContext __result = null;
/*  501:     */    try {
/*  502: 502 */      __result = new CLContext(nclCreateContextFromType(MemoryUtil.getAddress(properties), device_type, pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), APIUtil.getCLPlatform(properties));
/*  503: 503 */      return __result;
/*  504:     */    } finally {
/*  505: 505 */      if (__result != null) __result.setContextCallback(user_data);
/*  506:     */    }
/*  507:     */  }
/*  508:     */  
/*  509:     */  static native long nclCreateContextFromType(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*  510:     */  
/*  511: 511 */  public static int clRetainContext(CLContext context) { long function_pointer = CLCapabilities.clRetainContext;
/*  512: 512 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  513: 513 */    int __result = nclRetainContext(context.getPointer(), function_pointer);
/*  514: 514 */    if (__result == 0) context.retain();
/*  515: 515 */    return __result;
/*  516:     */  }
/*  517:     */  
/*  518:     */  static native int nclRetainContext(long paramLong1, long paramLong2);
/*  519:     */  
/*  520: 520 */  public static int clReleaseContext(CLContext context) { long function_pointer = CLCapabilities.clReleaseContext;
/*  521: 521 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  522: 522 */    APIUtil.releaseObjects(context);
/*  523: 523 */    int __result = nclReleaseContext(context.getPointer(), function_pointer);
/*  524: 524 */    if (__result == 0) context.releaseImpl();
/*  525: 525 */    return __result;
/*  526:     */  }
/*  527:     */  
/*  528:     */  static native int nclReleaseContext(long paramLong1, long paramLong2);
/*  529:     */  
/*  530: 530 */  public static int clGetContextInfo(CLContext context, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetContextInfo;
/*  531: 531 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  532: 532 */    if (param_value != null)
/*  533: 533 */      BufferChecks.checkDirect(param_value);
/*  534: 534 */    if (param_value_size_ret != null)
/*  535: 535 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  536: 536 */    if ((param_value_size_ret == null) && (APIUtil.isDevicesParam(param_name))) param_value_size_ret = APIUtil.getBufferPointer();
/*  537: 537 */    int __result = nclGetContextInfo(context.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  538: 538 */    if ((__result == 0) && (param_value != null) && (APIUtil.isDevicesParam(param_name))) ((CLPlatform)context.getParent()).registerCLDevices(param_value, param_value_size_ret);
/*  539: 539 */    return __result;
/*  540:     */  }
/*  541:     */  
/*  542:     */  static native int nclGetContextInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  543:     */  
/*  544: 544 */  public static CLCommandQueue clCreateCommandQueue(CLContext context, CLDevice device, long properties, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateCommandQueue;
/*  545: 545 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  546: 546 */    if (errcode_ret != null)
/*  547: 547 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  548: 548 */    CLCommandQueue __result = new CLCommandQueue(nclCreateCommandQueue(context.getPointer(), device.getPointer(), properties, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context, device);
/*  549: 549 */    return __result;
/*  550:     */  }
/*  551:     */  
/*  552:     */  static native long nclCreateCommandQueue(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  553:     */  
/*  554: 554 */  public static int clRetainCommandQueue(CLCommandQueue command_queue) { long function_pointer = CLCapabilities.clRetainCommandQueue;
/*  555: 555 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  556: 556 */    int __result = nclRetainCommandQueue(command_queue.getPointer(), function_pointer);
/*  557: 557 */    if (__result == 0) command_queue.retain();
/*  558: 558 */    return __result;
/*  559:     */  }
/*  560:     */  
/*  561:     */  static native int nclRetainCommandQueue(long paramLong1, long paramLong2);
/*  562:     */  
/*  563: 563 */  public static int clReleaseCommandQueue(CLCommandQueue command_queue) { long function_pointer = CLCapabilities.clReleaseCommandQueue;
/*  564: 564 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  565: 565 */    APIUtil.releaseObjects(command_queue);
/*  566: 566 */    int __result = nclReleaseCommandQueue(command_queue.getPointer(), function_pointer);
/*  567: 567 */    if (__result == 0) command_queue.release();
/*  568: 568 */    return __result;
/*  569:     */  }
/*  570:     */  
/*  571:     */  static native int nclReleaseCommandQueue(long paramLong1, long paramLong2);
/*  572:     */  
/*  573: 573 */  public static int clGetCommandQueueInfo(CLCommandQueue command_queue, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetCommandQueueInfo;
/*  574: 574 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  575: 575 */    if (param_value != null)
/*  576: 576 */      BufferChecks.checkDirect(param_value);
/*  577: 577 */    if (param_value_size_ret != null)
/*  578: 578 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  579: 579 */    int __result = nclGetCommandQueueInfo(command_queue.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  580: 580 */    return __result;
/*  581:     */  }
/*  582:     */  
/*  583:     */  static native int nclGetCommandQueueInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  584:     */  
/*  585: 585 */  public static CLMem clCreateBuffer(CLContext context, long flags, long host_ptr_size, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  586: 586 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  587: 587 */    if (errcode_ret != null)
/*  588: 588 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  589: 589 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr_size, 0L, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  590: 590 */    return __result;
/*  591:     */  }
/*  592:     */  
/*  593: 593 */  public static CLMem clCreateBuffer(CLContext context, long flags, ByteBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  594: 594 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  595: 595 */    BufferChecks.checkDirect(host_ptr);
/*  596: 596 */    if (errcode_ret != null)
/*  597: 597 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  598: 598 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining(), MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  599: 599 */    return __result;
/*  600:     */  }
/*  601:     */  
/*  602: 602 */  public static CLMem clCreateBuffer(CLContext context, long flags, DoubleBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  603: 603 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  604: 604 */    BufferChecks.checkDirect(host_ptr);
/*  605: 605 */    if (errcode_ret != null)
/*  606: 606 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  607: 607 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 3, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  608: 608 */    return __result;
/*  609:     */  }
/*  610:     */  
/*  611: 611 */  public static CLMem clCreateBuffer(CLContext context, long flags, FloatBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  612: 612 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  613: 613 */    BufferChecks.checkDirect(host_ptr);
/*  614: 614 */    if (errcode_ret != null)
/*  615: 615 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  616: 616 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 2, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  617: 617 */    return __result;
/*  618:     */  }
/*  619:     */  
/*  620: 620 */  public static CLMem clCreateBuffer(CLContext context, long flags, IntBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  621: 621 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  622: 622 */    BufferChecks.checkDirect(host_ptr);
/*  623: 623 */    if (errcode_ret != null)
/*  624: 624 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  625: 625 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 2, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  626: 626 */    return __result;
/*  627:     */  }
/*  628:     */  
/*  629: 629 */  public static CLMem clCreateBuffer(CLContext context, long flags, LongBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  630: 630 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  631: 631 */    BufferChecks.checkDirect(host_ptr);
/*  632: 632 */    if (errcode_ret != null)
/*  633: 633 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  634: 634 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 3, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  635: 635 */    return __result;
/*  636:     */  }
/*  637:     */  
/*  638: 638 */  public static CLMem clCreateBuffer(CLContext context, long flags, ShortBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateBuffer;
/*  639: 639 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  640: 640 */    BufferChecks.checkDirect(host_ptr);
/*  641: 641 */    if (errcode_ret != null)
/*  642: 642 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  643: 643 */    CLMem __result = new CLMem(nclCreateBuffer(context.getPointer(), flags, host_ptr.remaining() << 1, MemoryUtil.getAddress(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  644: 644 */    return __result;
/*  645:     */  }
/*  646:     */  
/*  647:     */  static native long nclCreateBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/*  648:     */  
/*  649: 649 */  public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  650: 650 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  651: 651 */    BufferChecks.checkDirect(ptr);
/*  652: 652 */    if (event_wait_list != null)
/*  653: 653 */      BufferChecks.checkDirect(event_wait_list);
/*  654: 654 */    if (event != null)
/*  655: 655 */      BufferChecks.checkBuffer(event, 1);
/*  656: 656 */    int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining(), MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  657: 657 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  658: 658 */    return __result;
/*  659:     */  }
/*  660:     */  
/*  661: 661 */  public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, DoubleBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  662: 662 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  663: 663 */    BufferChecks.checkDirect(ptr);
/*  664: 664 */    if (event_wait_list != null)
/*  665: 665 */      BufferChecks.checkDirect(event_wait_list);
/*  666: 666 */    if (event != null)
/*  667: 667 */      BufferChecks.checkBuffer(event, 1);
/*  668: 668 */    int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  669: 669 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  670: 670 */    return __result;
/*  671:     */  }
/*  672:     */  
/*  673: 673 */  public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  674: 674 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  675: 675 */    BufferChecks.checkDirect(ptr);
/*  676: 676 */    if (event_wait_list != null)
/*  677: 677 */      BufferChecks.checkDirect(event_wait_list);
/*  678: 678 */    if (event != null)
/*  679: 679 */      BufferChecks.checkBuffer(event, 1);
/*  680: 680 */    int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  681: 681 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  682: 682 */    return __result;
/*  683:     */  }
/*  684:     */  
/*  685: 685 */  public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  686: 686 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  687: 687 */    BufferChecks.checkDirect(ptr);
/*  688: 688 */    if (event_wait_list != null)
/*  689: 689 */      BufferChecks.checkDirect(event_wait_list);
/*  690: 690 */    if (event != null)
/*  691: 691 */      BufferChecks.checkBuffer(event, 1);
/*  692: 692 */    int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  693: 693 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  694: 694 */    return __result;
/*  695:     */  }
/*  696:     */  
/*  697: 697 */  public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, LongBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  698: 698 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  699: 699 */    BufferChecks.checkDirect(ptr);
/*  700: 700 */    if (event_wait_list != null)
/*  701: 701 */      BufferChecks.checkDirect(event_wait_list);
/*  702: 702 */    if (event != null)
/*  703: 703 */      BufferChecks.checkBuffer(event, 1);
/*  704: 704 */    int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  705: 705 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  706: 706 */    return __result;
/*  707:     */  }
/*  708:     */  
/*  709: 709 */  public static int clEnqueueReadBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_read, long offset, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBuffer;
/*  710: 710 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  711: 711 */    BufferChecks.checkDirect(ptr);
/*  712: 712 */    if (event_wait_list != null)
/*  713: 713 */      BufferChecks.checkDirect(event_wait_list);
/*  714: 714 */    if (event != null)
/*  715: 715 */      BufferChecks.checkBuffer(event, 1);
/*  716: 716 */    int __result = nclEnqueueReadBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_read, offset, ptr.remaining() << 1, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  717: 717 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  718: 718 */    return __result;
/*  719:     */  }
/*  720:     */  
/*  721:     */  static native int nclEnqueueReadBuffer(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8);
/*  722:     */  
/*  723: 723 */  public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  724: 724 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  725: 725 */    BufferChecks.checkDirect(ptr);
/*  726: 726 */    if (event_wait_list != null)
/*  727: 727 */      BufferChecks.checkDirect(event_wait_list);
/*  728: 728 */    if (event != null)
/*  729: 729 */      BufferChecks.checkBuffer(event, 1);
/*  730: 730 */    int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining(), MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  731: 731 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  732: 732 */    return __result;
/*  733:     */  }
/*  734:     */  
/*  735: 735 */  public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, DoubleBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  736: 736 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  737: 737 */    BufferChecks.checkDirect(ptr);
/*  738: 738 */    if (event_wait_list != null)
/*  739: 739 */      BufferChecks.checkDirect(event_wait_list);
/*  740: 740 */    if (event != null)
/*  741: 741 */      BufferChecks.checkBuffer(event, 1);
/*  742: 742 */    int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  743: 743 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  744: 744 */    return __result;
/*  745:     */  }
/*  746:     */  
/*  747: 747 */  public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  748: 748 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  749: 749 */    BufferChecks.checkDirect(ptr);
/*  750: 750 */    if (event_wait_list != null)
/*  751: 751 */      BufferChecks.checkDirect(event_wait_list);
/*  752: 752 */    if (event != null)
/*  753: 753 */      BufferChecks.checkBuffer(event, 1);
/*  754: 754 */    int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  755: 755 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  756: 756 */    return __result;
/*  757:     */  }
/*  758:     */  
/*  759: 759 */  public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  760: 760 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  761: 761 */    BufferChecks.checkDirect(ptr);
/*  762: 762 */    if (event_wait_list != null)
/*  763: 763 */      BufferChecks.checkDirect(event_wait_list);
/*  764: 764 */    if (event != null)
/*  765: 765 */      BufferChecks.checkBuffer(event, 1);
/*  766: 766 */    int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 2, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  767: 767 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  768: 768 */    return __result;
/*  769:     */  }
/*  770:     */  
/*  771: 771 */  public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, LongBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  772: 772 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  773: 773 */    BufferChecks.checkDirect(ptr);
/*  774: 774 */    if (event_wait_list != null)
/*  775: 775 */      BufferChecks.checkDirect(event_wait_list);
/*  776: 776 */    if (event != null)
/*  777: 777 */      BufferChecks.checkBuffer(event, 1);
/*  778: 778 */    int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 3, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  779: 779 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  780: 780 */    return __result;
/*  781:     */  }
/*  782:     */  
/*  783: 783 */  public static int clEnqueueWriteBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_write, long offset, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteBuffer;
/*  784: 784 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  785: 785 */    BufferChecks.checkDirect(ptr);
/*  786: 786 */    if (event_wait_list != null)
/*  787: 787 */      BufferChecks.checkDirect(event_wait_list);
/*  788: 788 */    if (event != null)
/*  789: 789 */      BufferChecks.checkBuffer(event, 1);
/*  790: 790 */    int __result = nclEnqueueWriteBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_write, offset, ptr.remaining() << 1, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  791: 791 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  792: 792 */    return __result;
/*  793:     */  }
/*  794:     */  
/*  795:     */  static native int nclEnqueueWriteBuffer(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8);
/*  796:     */  
/*  797: 797 */  public static int clEnqueueCopyBuffer(CLCommandQueue command_queue, CLMem src_buffer, CLMem dst_buffer, long src_offset, long dst_offset, long size, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueCopyBuffer;
/*  798: 798 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  799: 799 */    if (event_wait_list != null)
/*  800: 800 */      BufferChecks.checkDirect(event_wait_list);
/*  801: 801 */    if (event != null)
/*  802: 802 */      BufferChecks.checkBuffer(event, 1);
/*  803: 803 */    int __result = nclEnqueueCopyBuffer(command_queue.getPointer(), src_buffer.getPointer(), dst_buffer.getPointer(), src_offset, dst_offset, size, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  804: 804 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  805: 805 */    return __result;
/*  806:     */  }
/*  807:     */  
/*  808:     */  static native int nclEnqueueCopyBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/*  809:     */  
/*  810: 810 */  public static ByteBuffer clEnqueueMapBuffer(CLCommandQueue command_queue, CLMem buffer, int blocking_map, long map_flags, long offset, long size, PointerBuffer event_wait_list, PointerBuffer event, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clEnqueueMapBuffer;
/*  811: 811 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  812: 812 */    if (event_wait_list != null)
/*  813: 813 */      BufferChecks.checkDirect(event_wait_list);
/*  814: 814 */    if (event != null)
/*  815: 815 */      BufferChecks.checkBuffer(event, 1);
/*  816: 816 */    if (errcode_ret != null)
/*  817: 817 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  818: 818 */    ByteBuffer __result = nclEnqueueMapBuffer(command_queue.getPointer(), buffer.getPointer(), blocking_map, map_flags, offset, size, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), MemoryUtil.getAddressSafe(errcode_ret), size, function_pointer);
/*  819: 819 */    if (__result != null) command_queue.registerCLEvent(event);
/*  820: 820 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*  821:     */  }
/*  822:     */  
/*  823:     */  static native ByteBuffer nclEnqueueMapBuffer(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10);
/*  824:     */  
/*  825: 825 */  public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, ByteBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage2D;
/*  826: 826 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  827: 827 */    BufferChecks.checkBuffer(image_format, 8);
/*  828: 828 */    if (host_ptr != null)
/*  829: 829 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  830: 830 */    if (errcode_ret != null)
/*  831: 831 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  832: 832 */    CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  833: 833 */    return __result;
/*  834:     */  }
/*  835:     */  
/*  836: 836 */  public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, FloatBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage2D;
/*  837: 837 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  838: 838 */    BufferChecks.checkBuffer(image_format, 8);
/*  839: 839 */    if (host_ptr != null)
/*  840: 840 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  841: 841 */    if (errcode_ret != null)
/*  842: 842 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  843: 843 */    CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  844: 844 */    return __result;
/*  845:     */  }
/*  846:     */  
/*  847: 847 */  public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, IntBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage2D;
/*  848: 848 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  849: 849 */    BufferChecks.checkBuffer(image_format, 8);
/*  850: 850 */    if (host_ptr != null)
/*  851: 851 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  852: 852 */    if (errcode_ret != null)
/*  853: 853 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  854: 854 */    CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  855: 855 */    return __result;
/*  856:     */  }
/*  857:     */  
/*  858: 858 */  public static CLMem clCreateImage2D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_row_pitch, ShortBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage2D;
/*  859: 859 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  860: 860 */    BufferChecks.checkBuffer(image_format, 8);
/*  861: 861 */    if (host_ptr != null)
/*  862: 862 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(image_format, image_width, image_height, image_row_pitch));
/*  863: 863 */    if (errcode_ret != null)
/*  864: 864 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  865: 865 */    CLMem __result = new CLMem(nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_row_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  866: 866 */    return __result;
/*  867:     */  }
/*  868:     */  
/*  869:     */  static native long nclCreateImage2D(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9);
/*  870:     */  
/*  871: 871 */  public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, ByteBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage3D;
/*  872: 872 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  873: 873 */    BufferChecks.checkBuffer(image_format, 8);
/*  874: 874 */    if (host_ptr != null)
/*  875: 875 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  876: 876 */    if (errcode_ret != null)
/*  877: 877 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  878: 878 */    CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  879: 879 */    return __result;
/*  880:     */  }
/*  881:     */  
/*  882: 882 */  public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, FloatBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage3D;
/*  883: 883 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  884: 884 */    BufferChecks.checkBuffer(image_format, 8);
/*  885: 885 */    if (host_ptr != null)
/*  886: 886 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  887: 887 */    if (errcode_ret != null)
/*  888: 888 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  889: 889 */    CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  890: 890 */    return __result;
/*  891:     */  }
/*  892:     */  
/*  893: 893 */  public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, IntBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage3D;
/*  894: 894 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  895: 895 */    BufferChecks.checkBuffer(image_format, 8);
/*  896: 896 */    if (host_ptr != null)
/*  897: 897 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  898: 898 */    if (errcode_ret != null)
/*  899: 899 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  900: 900 */    CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  901: 901 */    return __result;
/*  902:     */  }
/*  903:     */  
/*  904: 904 */  public static CLMem clCreateImage3D(CLContext context, long flags, ByteBuffer image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, ShortBuffer host_ptr, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateImage3D;
/*  905: 905 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  906: 906 */    BufferChecks.checkBuffer(image_format, 8);
/*  907: 907 */    if (host_ptr != null)
/*  908: 908 */      BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch));
/*  909: 909 */    if (errcode_ret != null)
/*  910: 910 */      BufferChecks.checkBuffer(errcode_ret, 1);
/*  911: 911 */    CLMem __result = new CLMem(nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(image_format), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddressSafe(host_ptr), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  912: 912 */    return __result;
/*  913:     */  }
/*  914:     */  
/*  915:     */  static native long nclCreateImage3D(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10, long paramLong11);
/*  916:     */  
/*  917: 917 */  public static int clGetSupportedImageFormats(CLContext context, long flags, int image_type, ByteBuffer image_formats, IntBuffer num_image_formats) { long function_pointer = CLCapabilities.clGetSupportedImageFormats;
/*  918: 918 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  919: 919 */    if (image_formats != null)
/*  920: 920 */      BufferChecks.checkDirect(image_formats);
/*  921: 921 */    if (num_image_formats != null)
/*  922: 922 */      BufferChecks.checkBuffer(num_image_formats, 1);
/*  923: 923 */    int __result = nclGetSupportedImageFormats(context.getPointer(), flags, image_type, (image_formats == null ? 0 : image_formats.remaining()) / 8, MemoryUtil.getAddressSafe(image_formats), MemoryUtil.getAddressSafe(num_image_formats), function_pointer);
/*  924: 924 */    return __result;
/*  925:     */  }
/*  926:     */  
/*  927:     */  static native int nclGetSupportedImageFormats(long paramLong1, long paramLong2, int paramInt1, int paramInt2, long paramLong3, long paramLong4, long paramLong5);
/*  928:     */  
/*  929: 929 */  public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  930: 930 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  931: 931 */    BufferChecks.checkBuffer(origin, 3);
/*  932: 932 */    BufferChecks.checkBuffer(region, 3);
/*  933: 933 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  934: 934 */    if (event_wait_list != null)
/*  935: 935 */      BufferChecks.checkDirect(event_wait_list);
/*  936: 936 */    if (event != null)
/*  937: 937 */      BufferChecks.checkBuffer(event, 1);
/*  938: 938 */    int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  939: 939 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  940: 940 */    return __result;
/*  941:     */  }
/*  942:     */  
/*  943: 943 */  public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  944: 944 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  945: 945 */    BufferChecks.checkBuffer(origin, 3);
/*  946: 946 */    BufferChecks.checkBuffer(region, 3);
/*  947: 947 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  948: 948 */    if (event_wait_list != null)
/*  949: 949 */      BufferChecks.checkDirect(event_wait_list);
/*  950: 950 */    if (event != null)
/*  951: 951 */      BufferChecks.checkBuffer(event, 1);
/*  952: 952 */    int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  953: 953 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  954: 954 */    return __result;
/*  955:     */  }
/*  956:     */  
/*  957: 957 */  public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  958: 958 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  959: 959 */    BufferChecks.checkBuffer(origin, 3);
/*  960: 960 */    BufferChecks.checkBuffer(region, 3);
/*  961: 961 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  962: 962 */    if (event_wait_list != null)
/*  963: 963 */      BufferChecks.checkDirect(event_wait_list);
/*  964: 964 */    if (event != null)
/*  965: 965 */      BufferChecks.checkBuffer(event, 1);
/*  966: 966 */    int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  967: 967 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  968: 968 */    return __result;
/*  969:     */  }
/*  970:     */  
/*  971: 971 */  public static int clEnqueueReadImage(CLCommandQueue command_queue, CLMem image, int blocking_read, PointerBuffer origin, PointerBuffer region, long row_pitch, long slice_pitch, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadImage;
/*  972: 972 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  973: 973 */    BufferChecks.checkBuffer(origin, 3);
/*  974: 974 */    BufferChecks.checkBuffer(region, 3);
/*  975: 975 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, row_pitch, slice_pitch));
/*  976: 976 */    if (event_wait_list != null)
/*  977: 977 */      BufferChecks.checkDirect(event_wait_list);
/*  978: 978 */    if (event != null)
/*  979: 979 */      BufferChecks.checkBuffer(event, 1);
/*  980: 980 */    int __result = nclEnqueueReadImage(command_queue.getPointer(), image.getPointer(), blocking_read, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), row_pitch, slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  981: 981 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  982: 982 */    return __result;
/*  983:     */  }
/*  984:     */  
/*  985:     */  static native int nclEnqueueReadImage(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt2, long paramLong8, long paramLong9, long paramLong10);
/*  986:     */  
/*  987: 987 */  public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteImage;
/*  988: 988 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  989: 989 */    BufferChecks.checkBuffer(origin, 3);
/*  990: 990 */    BufferChecks.checkBuffer(region, 3);
/*  991: 991 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/*  992: 992 */    if (event_wait_list != null)
/*  993: 993 */      BufferChecks.checkDirect(event_wait_list);
/*  994: 994 */    if (event != null)
/*  995: 995 */      BufferChecks.checkBuffer(event, 1);
/*  996: 996 */    int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/*  997: 997 */    if (__result == 0) command_queue.registerCLEvent(event);
/*  998: 998 */    return __result;
/*  999:     */  }
/* 1000:     */  
/* 1001:1001 */  public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteImage;
/* 1002:1002 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1003:1003 */    BufferChecks.checkBuffer(origin, 3);
/* 1004:1004 */    BufferChecks.checkBuffer(region, 3);
/* 1005:1005 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/* 1006:1006 */    if (event_wait_list != null)
/* 1007:1007 */      BufferChecks.checkDirect(event_wait_list);
/* 1008:1008 */    if (event != null)
/* 1009:1009 */      BufferChecks.checkBuffer(event, 1);
/* 1010:1010 */    int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1011:1011 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1012:1012 */    return __result;
/* 1013:     */  }
/* 1014:     */  
/* 1015:1015 */  public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteImage;
/* 1016:1016 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1017:1017 */    BufferChecks.checkBuffer(origin, 3);
/* 1018:1018 */    BufferChecks.checkBuffer(region, 3);
/* 1019:1019 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/* 1020:1020 */    if (event_wait_list != null)
/* 1021:1021 */      BufferChecks.checkDirect(event_wait_list);
/* 1022:1022 */    if (event != null)
/* 1023:1023 */      BufferChecks.checkBuffer(event, 1);
/* 1024:1024 */    int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1025:1025 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1026:1026 */    return __result;
/* 1027:     */  }
/* 1028:     */  
/* 1029:1029 */  public static int clEnqueueWriteImage(CLCommandQueue command_queue, CLMem image, int blocking_write, PointerBuffer origin, PointerBuffer region, long input_row_pitch, long input_slice_pitch, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueWriteImage;
/* 1030:1030 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1031:1031 */    BufferChecks.checkBuffer(origin, 3);
/* 1032:1032 */    BufferChecks.checkBuffer(region, 3);
/* 1033:1033 */    BufferChecks.checkBuffer(ptr, CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch));
/* 1034:1034 */    if (event_wait_list != null)
/* 1035:1035 */      BufferChecks.checkDirect(event_wait_list);
/* 1036:1036 */    if (event != null)
/* 1037:1037 */      BufferChecks.checkBuffer(event, 1);
/* 1038:1038 */    int __result = nclEnqueueWriteImage(command_queue.getPointer(), image.getPointer(), blocking_write, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), input_row_pitch, input_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1039:1039 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1040:1040 */    return __result;
/* 1041:     */  }
/* 1042:     */  
/* 1043:     */  static native int nclEnqueueWriteImage(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt2, long paramLong8, long paramLong9, long paramLong10);
/* 1044:     */  
/* 1045:1045 */  public static int clEnqueueCopyImage(CLCommandQueue command_queue, CLMem src_image, CLMem dst_image, PointerBuffer src_origin, PointerBuffer dst_origin, PointerBuffer region, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueCopyImage;
/* 1046:1046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1047:1047 */    BufferChecks.checkBuffer(src_origin, 3);
/* 1048:1048 */    BufferChecks.checkBuffer(dst_origin, 3);
/* 1049:1049 */    BufferChecks.checkBuffer(region, 3);
/* 1050:1050 */    if (event_wait_list != null)
/* 1051:1051 */      BufferChecks.checkDirect(event_wait_list);
/* 1052:1052 */    if (event != null)
/* 1053:1053 */      BufferChecks.checkBuffer(event, 1);
/* 1054:1054 */    int __result = nclEnqueueCopyImage(command_queue.getPointer(), src_image.getPointer(), dst_image.getPointer(), MemoryUtil.getAddress(src_origin), MemoryUtil.getAddress(dst_origin), MemoryUtil.getAddress(region), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1055:1055 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1056:1056 */    return __result;
/* 1057:     */  }
/* 1058:     */  
/* 1059:     */  static native int nclEnqueueCopyImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/* 1060:     */  
/* 1061:1061 */  public static int clEnqueueCopyImageToBuffer(CLCommandQueue command_queue, CLMem src_image, CLMem dst_buffer, PointerBuffer src_origin, PointerBuffer region, long dst_offset, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueCopyImageToBuffer;
/* 1062:1062 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1063:1063 */    BufferChecks.checkBuffer(src_origin, 3);
/* 1064:1064 */    BufferChecks.checkBuffer(region, 3);
/* 1065:1065 */    if (event_wait_list != null)
/* 1066:1066 */      BufferChecks.checkDirect(event_wait_list);
/* 1067:1067 */    if (event != null)
/* 1068:1068 */      BufferChecks.checkBuffer(event, 1);
/* 1069:1069 */    int __result = nclEnqueueCopyImageToBuffer(command_queue.getPointer(), src_image.getPointer(), dst_buffer.getPointer(), MemoryUtil.getAddress(src_origin), MemoryUtil.getAddress(region), dst_offset, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1070:1070 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1071:1071 */    return __result;
/* 1072:     */  }
/* 1073:     */  
/* 1074:     */  static native int nclEnqueueCopyImageToBuffer(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/* 1075:     */  
/* 1076:1076 */  public static int clEnqueueCopyBufferToImage(CLCommandQueue command_queue, CLMem src_buffer, CLMem dst_image, long src_offset, PointerBuffer dst_origin, PointerBuffer region, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueCopyBufferToImage;
/* 1077:1077 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1078:1078 */    BufferChecks.checkBuffer(dst_origin, 3);
/* 1079:1079 */    BufferChecks.checkBuffer(region, 3);
/* 1080:1080 */    if (event_wait_list != null)
/* 1081:1081 */      BufferChecks.checkDirect(event_wait_list);
/* 1082:1082 */    if (event != null)
/* 1083:1083 */      BufferChecks.checkBuffer(event, 1);
/* 1084:1084 */    int __result = nclEnqueueCopyBufferToImage(command_queue.getPointer(), src_buffer.getPointer(), dst_image.getPointer(), src_offset, MemoryUtil.getAddress(dst_origin), MemoryUtil.getAddress(region), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1085:1085 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1086:1086 */    return __result;
/* 1087:     */  }
/* 1088:     */  
/* 1089:     */  static native int nclEnqueueCopyBufferToImage(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt, long paramLong7, long paramLong8, long paramLong9);
/* 1090:     */  
/* 1091:1091 */  public static ByteBuffer clEnqueueMapImage(CLCommandQueue command_queue, CLMem image, int blocking_map, long map_flags, PointerBuffer origin, PointerBuffer region, PointerBuffer image_row_pitch, PointerBuffer image_slice_pitch, PointerBuffer event_wait_list, PointerBuffer event, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clEnqueueMapImage;
/* 1092:1092 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1093:1093 */    BufferChecks.checkBuffer(origin, 3);
/* 1094:1094 */    BufferChecks.checkBuffer(region, 3);
/* 1095:1095 */    BufferChecks.checkBuffer(image_row_pitch, 1);
/* 1096:1096 */    if (image_slice_pitch != null)
/* 1097:1097 */      BufferChecks.checkBuffer(image_slice_pitch, 1);
/* 1098:1098 */    if (event_wait_list != null)
/* 1099:1099 */      BufferChecks.checkDirect(event_wait_list);
/* 1100:1100 */    if (event != null)
/* 1101:1101 */      BufferChecks.checkBuffer(event, 1);
/* 1102:1102 */    if (errcode_ret != null)
/* 1103:1103 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1104:1104 */    ByteBuffer __result = nclEnqueueMapImage(command_queue.getPointer(), image.getPointer(), blocking_map, map_flags, MemoryUtil.getAddress(origin), MemoryUtil.getAddress(region), MemoryUtil.getAddress(image_row_pitch), MemoryUtil.getAddressSafe(image_slice_pitch), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), MemoryUtil.getAddressSafe(errcode_ret), function_pointer);
/* 1105:1105 */    if (__result != null) command_queue.registerCLEvent(event);
/* 1106:1106 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 1107:     */  }
/* 1108:     */  
/* 1109:     */  static native ByteBuffer nclEnqueueMapImage(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt2, long paramLong8, long paramLong9, long paramLong10, long paramLong11);
/* 1110:     */  
/* 1111:1111 */  public static int clGetImageInfo(CLMem image, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetImageInfo;
/* 1112:1112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1113:1113 */    if (param_value != null)
/* 1114:1114 */      BufferChecks.checkDirect(param_value);
/* 1115:1115 */    if (param_value_size_ret != null)
/* 1116:1116 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1117:1117 */    int __result = nclGetImageInfo(image.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1118:1118 */    return __result;
/* 1119:     */  }
/* 1120:     */  
/* 1121:     */  static native int nclGetImageInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1122:     */  
/* 1123:1123 */  public static int clRetainMemObject(CLMem memobj) { long function_pointer = CLCapabilities.clRetainMemObject;
/* 1124:1124 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1125:1125 */    int __result = nclRetainMemObject(memobj.getPointer(), function_pointer);
/* 1126:1126 */    if (__result == 0) memobj.retain();
/* 1127:1127 */    return __result;
/* 1128:     */  }
/* 1129:     */  
/* 1130:     */  static native int nclRetainMemObject(long paramLong1, long paramLong2);
/* 1131:     */  
/* 1132:1132 */  public static int clReleaseMemObject(CLMem memobj) { long function_pointer = CLCapabilities.clReleaseMemObject;
/* 1133:1133 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1134:1134 */    int __result = nclReleaseMemObject(memobj.getPointer(), function_pointer);
/* 1135:1135 */    if (__result == 0) memobj.release();
/* 1136:1136 */    return __result;
/* 1137:     */  }
/* 1138:     */  
/* 1139:     */  static native int nclReleaseMemObject(long paramLong1, long paramLong2);
/* 1140:     */  
/* 1141:1141 */  public static int clEnqueueUnmapMemObject(CLCommandQueue command_queue, CLMem memobj, ByteBuffer mapped_ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueUnmapMemObject;
/* 1142:1142 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1143:1143 */    BufferChecks.checkDirect(mapped_ptr);
/* 1144:1144 */    if (event_wait_list != null)
/* 1145:1145 */      BufferChecks.checkDirect(event_wait_list);
/* 1146:1146 */    if (event != null)
/* 1147:1147 */      BufferChecks.checkBuffer(event, 1);
/* 1148:1148 */    int __result = nclEnqueueUnmapMemObject(command_queue.getPointer(), memobj.getPointer(), MemoryUtil.getAddress(mapped_ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1149:1149 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1150:1150 */    return __result;
/* 1151:     */  }
/* 1152:     */  
/* 1153:     */  static native int nclEnqueueUnmapMemObject(long paramLong1, long paramLong2, long paramLong3, int paramInt, long paramLong4, long paramLong5, long paramLong6);
/* 1154:     */  
/* 1155:1155 */  public static int clGetMemObjectInfo(CLMem memobj, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetMemObjectInfo;
/* 1156:1156 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1157:1157 */    if (param_value != null)
/* 1158:1158 */      BufferChecks.checkDirect(param_value);
/* 1159:1159 */    if (param_value_size_ret != null)
/* 1160:1160 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1161:1161 */    int __result = nclGetMemObjectInfo(memobj.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1162:1162 */    return __result;
/* 1163:     */  }
/* 1164:     */  
/* 1165:     */  static native int nclGetMemObjectInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1166:     */  
/* 1167:1167 */  public static CLSampler clCreateSampler(CLContext context, int normalized_coords, int addressing_mode, int filter_mode, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateSampler;
/* 1168:1168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1169:1169 */    if (errcode_ret != null)
/* 1170:1170 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1171:1171 */    CLSampler __result = new CLSampler(nclCreateSampler(context.getPointer(), normalized_coords, addressing_mode, filter_mode, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1172:1172 */    return __result;
/* 1173:     */  }
/* 1174:     */  
/* 1175:     */  static native long nclCreateSampler(long paramLong1, int paramInt1, int paramInt2, int paramInt3, long paramLong2, long paramLong3);
/* 1176:     */  
/* 1177:1177 */  public static int clRetainSampler(CLSampler sampler) { long function_pointer = CLCapabilities.clRetainSampler;
/* 1178:1178 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1179:1179 */    int __result = nclRetainSampler(sampler.getPointer(), function_pointer);
/* 1180:1180 */    if (__result == 0) sampler.retain();
/* 1181:1181 */    return __result;
/* 1182:     */  }
/* 1183:     */  
/* 1184:     */  static native int nclRetainSampler(long paramLong1, long paramLong2);
/* 1185:     */  
/* 1186:1186 */  public static int clReleaseSampler(CLSampler sampler) { long function_pointer = CLCapabilities.clReleaseSampler;
/* 1187:1187 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1188:1188 */    int __result = nclReleaseSampler(sampler.getPointer(), function_pointer);
/* 1189:1189 */    if (__result == 0) sampler.release();
/* 1190:1190 */    return __result;
/* 1191:     */  }
/* 1192:     */  
/* 1193:     */  static native int nclReleaseSampler(long paramLong1, long paramLong2);
/* 1194:     */  
/* 1195:1195 */  public static int clGetSamplerInfo(CLSampler sampler, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetSamplerInfo;
/* 1196:1196 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1197:1197 */    if (param_value != null)
/* 1198:1198 */      BufferChecks.checkDirect(param_value);
/* 1199:1199 */    if (param_value_size_ret != null)
/* 1200:1200 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1201:1201 */    int __result = nclGetSamplerInfo(sampler.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1202:1202 */    return __result;
/* 1203:     */  }
/* 1204:     */  
/* 1205:     */  static native int nclGetSamplerInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1206:     */  
/* 1207:1207 */  public static CLProgram clCreateProgramWithSource(CLContext context, ByteBuffer string, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1208:1208 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1209:1209 */    BufferChecks.checkDirect(string);
/* 1210:1210 */    if (errcode_ret != null)
/* 1211:1211 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1212:1212 */    CLProgram __result = new CLProgram(nclCreateProgramWithSource(context.getPointer(), 1, MemoryUtil.getAddress(string), string.remaining(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1213:1213 */    return __result;
/* 1214:     */  }
/* 1215:     */  
/* 1216:     */  static native long nclCreateProgramWithSource(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1217:     */  
/* 1218:     */  public static CLProgram clCreateProgramWithSource(CLContext context, ByteBuffer strings, PointerBuffer lengths, IntBuffer errcode_ret) {
/* 1219:1219 */    long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1220:1220 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1221:1221 */    BufferChecks.checkBuffer(strings, APIUtil.getSize(lengths));
/* 1222:1222 */    BufferChecks.checkBuffer(lengths, 1);
/* 1223:1223 */    if (errcode_ret != null)
/* 1224:1224 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1225:1225 */    CLProgram __result = new CLProgram(nclCreateProgramWithSource2(context.getPointer(), lengths.remaining(), MemoryUtil.getAddress(strings), MemoryUtil.getAddress(lengths), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1226:1226 */    return __result;
/* 1227:     */  }
/* 1228:     */  
/* 1229:     */  static native long nclCreateProgramWithSource2(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1230:     */  
/* 1231:     */  public static CLProgram clCreateProgramWithSource(CLContext context, ByteBuffer[] strings, IntBuffer errcode_ret) {
/* 1232:1232 */    long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1233:1233 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1234:1234 */    BufferChecks.checkArray(strings, 1);
/* 1235:1235 */    if (errcode_ret != null)
/* 1236:1236 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1237:1237 */    CLProgram __result = new CLProgram(nclCreateProgramWithSource3(context.getPointer(), strings.length, strings, APIUtil.getLengths(strings), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1238:1238 */    return __result;
/* 1239:     */  }
/* 1240:     */  
/* 1241:     */  static native long nclCreateProgramWithSource3(long paramLong1, int paramInt, ByteBuffer[] paramArrayOfByteBuffer, long paramLong2, long paramLong3, long paramLong4);
/* 1242:     */  
/* 1243:     */  public static CLProgram clCreateProgramWithSource(CLContext context, CharSequence string, IntBuffer errcode_ret) {
/* 1244:1244 */    long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1245:1245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1246:1246 */    if (errcode_ret != null)
/* 1247:1247 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1248:1248 */    CLProgram __result = new CLProgram(nclCreateProgramWithSource(context.getPointer(), 1, APIUtil.getBuffer(string), string.length(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1249:1249 */    return __result;
/* 1250:     */  }
/* 1251:     */  
/* 1252:     */  public static CLProgram clCreateProgramWithSource(CLContext context, CharSequence[] strings, IntBuffer errcode_ret)
/* 1253:     */  {
/* 1254:1254 */    long function_pointer = CLCapabilities.clCreateProgramWithSource;
/* 1255:1255 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1256:1256 */    BufferChecks.checkArray(strings);
/* 1257:1257 */    if (errcode_ret != null)
/* 1258:1258 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1259:1259 */    CLProgram __result = new CLProgram(nclCreateProgramWithSource4(context.getPointer(), strings.length, APIUtil.getBuffer(strings), APIUtil.getLengths(strings), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1260:1260 */    return __result;
/* 1261:     */  }
/* 1262:     */  
/* 1263:     */  static native long nclCreateProgramWithSource4(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1264:     */  
/* 1265:1265 */  public static CLProgram clCreateProgramWithBinary(CLContext context, CLDevice device, ByteBuffer binary, IntBuffer binary_status, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateProgramWithBinary;
/* 1266:1266 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1267:1267 */    BufferChecks.checkDirect(binary);
/* 1268:1268 */    BufferChecks.checkBuffer(binary_status, 1);
/* 1269:1269 */    if (errcode_ret != null)
/* 1270:1270 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1271:1271 */    CLProgram __result = new CLProgram(nclCreateProgramWithBinary(context.getPointer(), 1, device.getPointer(), binary.remaining(), MemoryUtil.getAddress(binary), MemoryUtil.getAddress(binary_status), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1272:1272 */    return __result;
/* 1273:     */  }
/* 1274:     */  
/* 1275:     */  static native long nclCreateProgramWithBinary(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/* 1276:     */  
/* 1277:     */  public static CLProgram clCreateProgramWithBinary(CLContext context, PointerBuffer device_list, PointerBuffer lengths, ByteBuffer binaries, IntBuffer binary_status, IntBuffer errcode_ret) {
/* 1278:1278 */    long function_pointer = CLCapabilities.clCreateProgramWithBinary;
/* 1279:1279 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1280:1280 */    BufferChecks.checkBuffer(device_list, 1);
/* 1281:1281 */    BufferChecks.checkBuffer(lengths, device_list.remaining());
/* 1282:1282 */    BufferChecks.checkBuffer(binaries, APIUtil.getSize(lengths));
/* 1283:1283 */    BufferChecks.checkBuffer(binary_status, device_list.remaining());
/* 1284:1284 */    if (errcode_ret != null)
/* 1285:1285 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1286:1286 */    CLProgram __result = new CLProgram(nclCreateProgramWithBinary2(context.getPointer(), device_list.remaining(), MemoryUtil.getAddress(device_list), MemoryUtil.getAddress(lengths), MemoryUtil.getAddress(binaries), MemoryUtil.getAddress(binary_status), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1287:1287 */    return __result;
/* 1288:     */  }
/* 1289:     */  
/* 1290:     */  static native long nclCreateProgramWithBinary2(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/* 1291:     */  
/* 1292:     */  public static CLProgram clCreateProgramWithBinary(CLContext context, PointerBuffer device_list, ByteBuffer[] binaries, IntBuffer binary_status, IntBuffer errcode_ret) {
/* 1293:1293 */    long function_pointer = CLCapabilities.clCreateProgramWithBinary;
/* 1294:1294 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1295:1295 */    BufferChecks.checkBuffer(device_list, binaries.length);
/* 1296:1296 */    BufferChecks.checkArray(binaries, 1);
/* 1297:1297 */    BufferChecks.checkBuffer(binary_status, binaries.length);
/* 1298:1298 */    if (errcode_ret != null)
/* 1299:1299 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1300:1300 */    CLProgram __result = new CLProgram(nclCreateProgramWithBinary3(context.getPointer(), binaries.length, MemoryUtil.getAddress(device_list), APIUtil.getLengths(binaries), binaries, MemoryUtil.getAddress(binary_status), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 1301:1301 */    return __result;
/* 1302:     */  }
/* 1303:     */  
/* 1304:     */  static native long nclCreateProgramWithBinary3(long paramLong1, int paramInt, long paramLong2, long paramLong3, ByteBuffer[] paramArrayOfByteBuffer, long paramLong4, long paramLong5, long paramLong6);
/* 1305:     */  
/* 1306:1306 */  public static int clRetainProgram(CLProgram program) { long function_pointer = CLCapabilities.clRetainProgram;
/* 1307:1307 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1308:1308 */    int __result = nclRetainProgram(program.getPointer(), function_pointer);
/* 1309:1309 */    if (__result == 0) program.retain();
/* 1310:1310 */    return __result;
/* 1311:     */  }
/* 1312:     */  
/* 1313:     */  static native int nclRetainProgram(long paramLong1, long paramLong2);
/* 1314:     */  
/* 1315:1315 */  public static int clReleaseProgram(CLProgram program) { long function_pointer = CLCapabilities.clReleaseProgram;
/* 1316:1316 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1317:1317 */    APIUtil.releaseObjects(program);
/* 1318:1318 */    int __result = nclReleaseProgram(program.getPointer(), function_pointer);
/* 1319:1319 */    if (__result == 0) program.release();
/* 1320:1320 */    return __result;
/* 1321:     */  }
/* 1322:     */  
/* 1323:     */  static native int nclReleaseProgram(long paramLong1, long paramLong2);
/* 1324:     */  
/* 1325:1325 */  public static int clBuildProgram(CLProgram program, PointerBuffer device_list, ByteBuffer options, CLBuildProgramCallback pfn_notify) { long function_pointer = CLCapabilities.clBuildProgram;
/* 1326:1326 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1327:1327 */    if (device_list != null)
/* 1328:1328 */      BufferChecks.checkDirect(device_list);
/* 1329:1329 */    BufferChecks.checkDirect(options);
/* 1330:1330 */    BufferChecks.checkNullTerminated(options);
/* 1331:1331 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 1332:1332 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 1333:1333 */    int __result = 0;
/* 1334:     */    try {
/* 1335:1335 */      __result = nclBuildProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), MemoryUtil.getAddress(options), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 1336:1336 */      return __result;
/* 1337:     */    } finally {
/* 1338:1338 */      CallbackUtil.checkCallback(__result, user_data);
/* 1339:     */    }
/* 1340:     */  }
/* 1341:     */  
/* 1342:     */  static native int nclBuildProgram(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/* 1343:     */  
/* 1344:     */  public static int clBuildProgram(CLProgram program, PointerBuffer device_list, CharSequence options, CLBuildProgramCallback pfn_notify) {
/* 1345:1345 */    long function_pointer = CLCapabilities.clBuildProgram;
/* 1346:1346 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1347:1347 */    if (device_list != null)
/* 1348:1348 */      BufferChecks.checkDirect(device_list);
/* 1349:1349 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 1350:1350 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 1351:1351 */    int __result = 0;
/* 1352:     */    try {
/* 1353:1353 */      __result = nclBuildProgram(program.getPointer(), device_list == null ? 0 : device_list.remaining(), MemoryUtil.getAddressSafe(device_list), APIUtil.getBufferNT(options), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 1354:1354 */      return __result;
/* 1355:     */    } finally {
/* 1356:1356 */      CallbackUtil.checkCallback(__result, user_data);
/* 1357:     */    }
/* 1358:     */  }
/* 1359:     */  
/* 1360:     */  public static int clBuildProgram(CLProgram program, CLDevice device, CharSequence options, CLBuildProgramCallback pfn_notify)
/* 1361:     */  {
/* 1362:1362 */    long function_pointer = CLCapabilities.clBuildProgram;
/* 1363:1363 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1364:1364 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 1365:1365 */    if (pfn_notify != null) pfn_notify.setContext((CLContext)program.getParent());
/* 1366:1366 */    int __result = 0;
/* 1367:     */    try {
/* 1368:1368 */      __result = nclBuildProgram(program.getPointer(), 1, APIUtil.getPointer(device), APIUtil.getBufferNT(options), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, function_pointer);
/* 1369:1369 */      return __result;
/* 1370:     */    } finally {
/* 1371:1371 */      CallbackUtil.checkCallback(__result, user_data);
/* 1372:     */    }
/* 1373:     */  }
/* 1374:     */  
/* 1375:     */  public static int clUnloadCompiler() {
/* 1376:1376 */    long function_pointer = CLCapabilities.clUnloadCompiler;
/* 1377:1377 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1378:1378 */    int __result = nclUnloadCompiler(function_pointer);
/* 1379:1379 */    return __result;
/* 1380:     */  }
/* 1381:     */  
/* 1382:     */  static native int nclUnloadCompiler(long paramLong);
/* 1383:     */  
/* 1384:1384 */  public static int clGetProgramInfo(CLProgram program, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetProgramInfo;
/* 1385:1385 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1386:1386 */    if (param_value != null)
/* 1387:1387 */      BufferChecks.checkDirect(param_value);
/* 1388:1388 */    if (param_value_size_ret != null)
/* 1389:1389 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1390:1390 */    int __result = nclGetProgramInfo(program.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1391:1391 */    return __result;
/* 1392:     */  }
/* 1393:     */  
/* 1400:     */  static native int nclGetProgramInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1401:     */  
/* 1408:     */  public static int clGetProgramInfo(CLProgram program, PointerBuffer sizes, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/* 1409:     */  {
/* 1410:1410 */    long function_pointer = CLCapabilities.clGetProgramInfo;
/* 1411:1411 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1412:1412 */    BufferChecks.checkBuffer(sizes, 1);
/* 1413:1413 */    BufferChecks.checkBuffer(param_value, APIUtil.getSize(sizes));
/* 1414:1414 */    if (param_value_size_ret != null)
/* 1415:1415 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1416:1416 */    int __result = nclGetProgramInfo2(program.getPointer(), 4454, sizes.remainingByte(), MemoryUtil.getAddress(sizes), MemoryUtil.getAddress(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1417:1417 */    return __result;
/* 1418:     */  }
/* 1419:     */  
/* 1426:     */  static native int nclGetProgramInfo2(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/* 1427:     */  
/* 1434:     */  public static int clGetProgramInfo(CLProgram program, ByteBuffer[] param_value, PointerBuffer param_value_size_ret)
/* 1435:     */  {
/* 1436:1436 */    long function_pointer = CLCapabilities.clGetProgramInfo;
/* 1437:1437 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1438:1438 */    BufferChecks.checkArray(param_value);
/* 1439:1439 */    if (param_value_size_ret != null)
/* 1440:1440 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1441:1441 */    int __result = nclGetProgramInfo3(program.getPointer(), 4454, param_value.length * PointerBuffer.getPointerSize(), param_value, MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1442:1442 */    return __result;
/* 1443:     */  }
/* 1444:     */  
/* 1445:     */  static native int nclGetProgramInfo3(long paramLong1, int paramInt, long paramLong2, ByteBuffer[] paramArrayOfByteBuffer, long paramLong3, long paramLong4);
/* 1446:     */  
/* 1447:1447 */  public static int clGetProgramBuildInfo(CLProgram program, CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetProgramBuildInfo;
/* 1448:1448 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1449:1449 */    if (param_value != null)
/* 1450:1450 */      BufferChecks.checkDirect(param_value);
/* 1451:1451 */    if (param_value_size_ret != null)
/* 1452:1452 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1453:1453 */    int __result = nclGetProgramBuildInfo(program.getPointer(), device.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1454:1454 */    return __result;
/* 1455:     */  }
/* 1456:     */  
/* 1457:     */  static native int nclGetProgramBuildInfo(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/* 1458:     */  
/* 1459:1459 */  public static CLKernel clCreateKernel(CLProgram program, ByteBuffer kernel_name, IntBuffer errcode_ret) { long function_pointer = CLCapabilities.clCreateKernel;
/* 1460:1460 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1461:1461 */    BufferChecks.checkDirect(kernel_name);
/* 1462:1462 */    BufferChecks.checkNullTerminated(kernel_name);
/* 1463:1463 */    if (errcode_ret != null)
/* 1464:1464 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1465:1465 */    CLKernel __result = new CLKernel(nclCreateKernel(program.getPointer(), MemoryUtil.getAddress(kernel_name), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), program);
/* 1466:1466 */    return __result;
/* 1467:     */  }
/* 1468:     */  
/* 1469:     */  static native long nclCreateKernel(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 1470:     */  
/* 1471:     */  public static CLKernel clCreateKernel(CLProgram program, CharSequence kernel_name, IntBuffer errcode_ret) {
/* 1472:1472 */    long function_pointer = CLCapabilities.clCreateKernel;
/* 1473:1473 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1474:1474 */    if (errcode_ret != null)
/* 1475:1475 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 1476:1476 */    CLKernel __result = new CLKernel(nclCreateKernel(program.getPointer(), APIUtil.getBufferNT(kernel_name), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), program);
/* 1477:1477 */    return __result;
/* 1478:     */  }
/* 1479:     */  
/* 1480:     */  public static int clCreateKernelsInProgram(CLProgram program, PointerBuffer kernels, IntBuffer num_kernels_ret) {
/* 1481:1481 */    long function_pointer = CLCapabilities.clCreateKernelsInProgram;
/* 1482:1482 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1483:1483 */    if (kernels != null)
/* 1484:1484 */      BufferChecks.checkDirect(kernels);
/* 1485:1485 */    if (num_kernels_ret != null)
/* 1486:1486 */      BufferChecks.checkBuffer(num_kernels_ret, 1);
/* 1487:1487 */    int __result = nclCreateKernelsInProgram(program.getPointer(), kernels == null ? 0 : kernels.remaining(), MemoryUtil.getAddressSafe(kernels), MemoryUtil.getAddressSafe(num_kernels_ret), function_pointer);
/* 1488:1488 */    if ((__result == 0) && (kernels != null)) program.registerCLKernels(kernels);
/* 1489:1489 */    return __result;
/* 1490:     */  }
/* 1491:     */  
/* 1492:     */  static native int nclCreateKernelsInProgram(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/* 1493:     */  
/* 1494:1494 */  public static int clRetainKernel(CLKernel kernel) { long function_pointer = CLCapabilities.clRetainKernel;
/* 1495:1495 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1496:1496 */    int __result = nclRetainKernel(kernel.getPointer(), function_pointer);
/* 1497:1497 */    if (__result == 0) kernel.retain();
/* 1498:1498 */    return __result;
/* 1499:     */  }
/* 1500:     */  
/* 1501:     */  static native int nclRetainKernel(long paramLong1, long paramLong2);
/* 1502:     */  
/* 1503:1503 */  public static int clReleaseKernel(CLKernel kernel) { long function_pointer = CLCapabilities.clReleaseKernel;
/* 1504:1504 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1505:1505 */    int __result = nclReleaseKernel(kernel.getPointer(), function_pointer);
/* 1506:1506 */    if (__result == 0) kernel.release();
/* 1507:1507 */    return __result;
/* 1508:     */  }
/* 1509:     */  
/* 1510:     */  static native int nclReleaseKernel(long paramLong1, long paramLong2);
/* 1511:     */  
/* 1512:1512 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, long arg_value_arg_size) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1513:1513 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1514:1514 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value_arg_size, 0L, function_pointer);
/* 1515:1515 */    return __result;
/* 1516:     */  }
/* 1517:     */  
/* 1518:1518 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, ByteBuffer arg_value) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1519:1519 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1520:1520 */    BufferChecks.checkDirect(arg_value);
/* 1521:1521 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining(), MemoryUtil.getAddress(arg_value), function_pointer);
/* 1522:1522 */    return __result;
/* 1523:     */  }
/* 1524:     */  
/* 1525:1525 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, DoubleBuffer arg_value) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1526:1526 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1527:1527 */    BufferChecks.checkDirect(arg_value);
/* 1528:1528 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 3, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1529:1529 */    return __result;
/* 1530:     */  }
/* 1531:     */  
/* 1532:1532 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, FloatBuffer arg_value) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1533:1533 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1534:1534 */    BufferChecks.checkDirect(arg_value);
/* 1535:1535 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 2, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1536:1536 */    return __result;
/* 1537:     */  }
/* 1538:     */  
/* 1539:1539 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, IntBuffer arg_value) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1540:1540 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1541:1541 */    BufferChecks.checkDirect(arg_value);
/* 1542:1542 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 2, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1543:1543 */    return __result;
/* 1544:     */  }
/* 1545:     */  
/* 1546:1546 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, LongBuffer arg_value) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1547:1547 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1548:1548 */    BufferChecks.checkDirect(arg_value);
/* 1549:1549 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 3, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1550:1550 */    return __result;
/* 1551:     */  }
/* 1552:     */  
/* 1553:1553 */  public static int clSetKernelArg(CLKernel kernel, int arg_index, ShortBuffer arg_value) { long function_pointer = CLCapabilities.clSetKernelArg;
/* 1554:1554 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1555:1555 */    BufferChecks.checkDirect(arg_value);
/* 1556:1556 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_value.remaining() << 1, MemoryUtil.getAddress(arg_value), function_pointer);
/* 1557:1557 */    return __result;
/* 1558:     */  }
/* 1559:     */  
/* 1560:     */  static native int nclSetKernelArg(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/* 1561:     */  
/* 1562:     */  public static int clSetKernelArg(CLKernel kernel, int arg_index, CLObject arg_value) {
/* 1563:1563 */    long function_pointer = CLCapabilities.clSetKernelArg;
/* 1564:1564 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1565:1565 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, PointerBuffer.getPointerSize(), APIUtil.getPointerSafe(arg_value), function_pointer);
/* 1566:1566 */    return __result;
/* 1567:     */  }
/* 1568:     */  
/* 1569:     */  static int clSetKernelArg(CLKernel kernel, int arg_index, long arg_size, Buffer arg_value)
/* 1570:     */  {
/* 1571:1571 */    long function_pointer = CLCapabilities.clSetKernelArg;
/* 1572:1572 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1573:1573 */    int __result = nclSetKernelArg(kernel.getPointer(), arg_index, arg_size, MemoryUtil.getAddress0(arg_value), function_pointer);
/* 1574:1574 */    return __result;
/* 1575:     */  }
/* 1576:     */  
/* 1577:     */  public static int clGetKernelInfo(CLKernel kernel, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1578:1578 */    long function_pointer = CLCapabilities.clGetKernelInfo;
/* 1579:1579 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1580:1580 */    if (param_value != null)
/* 1581:1581 */      BufferChecks.checkDirect(param_value);
/* 1582:1582 */    if (param_value_size_ret != null)
/* 1583:1583 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1584:1584 */    int __result = nclGetKernelInfo(kernel.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1585:1585 */    return __result;
/* 1586:     */  }
/* 1587:     */  
/* 1588:     */  static native int nclGetKernelInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1589:     */  
/* 1590:1590 */  public static int clGetKernelWorkGroupInfo(CLKernel kernel, CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) { long function_pointer = CLCapabilities.clGetKernelWorkGroupInfo;
/* 1591:1591 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1592:1592 */    if (param_value != null)
/* 1593:1593 */      BufferChecks.checkDirect(param_value);
/* 1594:1594 */    if (param_value_size_ret != null)
/* 1595:1595 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1596:1596 */    int __result = nclGetKernelWorkGroupInfo(kernel.getPointer(), device == null ? 0L : device.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1597:1597 */    return __result;
/* 1598:     */  }
/* 1599:     */  
/* 1600:     */  static native int nclGetKernelWorkGroupInfo(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5, long paramLong6);
/* 1601:     */  
/* 1602:1602 */  public static int clEnqueueNDRangeKernel(CLCommandQueue command_queue, CLKernel kernel, int work_dim, PointerBuffer global_work_offset, PointerBuffer global_work_size, PointerBuffer local_work_size, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueNDRangeKernel;
/* 1603:1603 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1604:1604 */    if (global_work_offset != null)
/* 1605:1605 */      BufferChecks.checkBuffer(global_work_offset, work_dim);
/* 1606:1606 */    if (global_work_size != null)
/* 1607:1607 */      BufferChecks.checkBuffer(global_work_size, work_dim);
/* 1608:1608 */    if (local_work_size != null)
/* 1609:1609 */      BufferChecks.checkBuffer(local_work_size, work_dim);
/* 1610:1610 */    if (event_wait_list != null)
/* 1611:1611 */      BufferChecks.checkDirect(event_wait_list);
/* 1612:1612 */    if (event != null)
/* 1613:1613 */      BufferChecks.checkBuffer(event, 1);
/* 1614:1614 */    int __result = nclEnqueueNDRangeKernel(command_queue.getPointer(), kernel.getPointer(), work_dim, MemoryUtil.getAddressSafe(global_work_offset), MemoryUtil.getAddressSafe(global_work_size), MemoryUtil.getAddressSafe(local_work_size), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1615:1615 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1616:1616 */    return __result;
/* 1617:     */  }
/* 1618:     */  
/* 1619:     */  static native int nclEnqueueNDRangeKernel(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8);
/* 1620:     */  
/* 1621:1621 */  public static int clEnqueueTask(CLCommandQueue command_queue, CLKernel kernel, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueTask;
/* 1622:1622 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1623:1623 */    if (event_wait_list != null)
/* 1624:1624 */      BufferChecks.checkDirect(event_wait_list);
/* 1625:1625 */    if (event != null)
/* 1626:1626 */      BufferChecks.checkBuffer(event, 1);
/* 1627:1627 */    int __result = nclEnqueueTask(command_queue.getPointer(), kernel.getPointer(), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1628:1628 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1629:1629 */    return __result;
/* 1630:     */  }
/* 1631:     */  
/* 1639:     */  static native int nclEnqueueTask(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/* 1640:     */  
/* 1648:     */  public static int clEnqueueNativeKernel(CLCommandQueue command_queue, CLNativeKernel user_func, CLMem[] mem_list, long[] sizes, PointerBuffer event_wait_list, PointerBuffer event)
/* 1649:     */  {
/* 1650:1650 */    long function_pointer = CLCapabilities.clEnqueueNativeKernel;
/* 1651:1651 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1652:1652 */    if (mem_list != null)
/* 1653:1653 */      BufferChecks.checkArray(mem_list, 1);
/* 1654:1654 */    if (sizes != null)
/* 1655:1655 */      BufferChecks.checkArray(sizes, mem_list.length);
/* 1656:1656 */    if (event_wait_list != null)
/* 1657:1657 */      BufferChecks.checkDirect(event_wait_list);
/* 1658:1658 */    if (event != null)
/* 1659:1659 */      BufferChecks.checkBuffer(event, 1);
/* 1660:1660 */    long user_func_ref = CallbackUtil.createGlobalRef(user_func);
/* 1661:1661 */    ByteBuffer args = APIUtil.getNativeKernelArgs(user_func_ref, mem_list, sizes);
/* 1662:1662 */    int __result = 0;
/* 1663:     */    try {
/* 1664:1664 */      __result = nclEnqueueNativeKernel(command_queue.getPointer(), user_func.getPointer(), MemoryUtil.getAddress0(args), args.remaining(), mem_list == null ? 0 : mem_list.length, mem_list, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 1665:1665 */      if (__result == 0) command_queue.registerCLEvent(event);
/* 1666:1666 */      return __result;
/* 1667:     */    } finally {
/* 1668:1668 */      CallbackUtil.checkCallback(__result, user_func_ref);
/* 1669:     */    }
/* 1670:     */  }
/* 1671:     */  
/* 1672:     */  static native int nclEnqueueNativeKernel(long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, CLMem[] paramArrayOfCLMem, int paramInt2, long paramLong5, long paramLong6, long paramLong7);
/* 1673:     */  
/* 1674:1674 */  public static int clWaitForEvents(PointerBuffer event_list) { long function_pointer = CLCapabilities.clWaitForEvents;
/* 1675:1675 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1676:1676 */    BufferChecks.checkBuffer(event_list, 1);
/* 1677:1677 */    int __result = nclWaitForEvents(event_list.remaining(), MemoryUtil.getAddress(event_list), function_pointer);
/* 1678:1678 */    return __result;
/* 1679:     */  }
/* 1680:     */  
/* 1681:     */  static native int nclWaitForEvents(int paramInt, long paramLong1, long paramLong2);
/* 1682:     */  
/* 1683:     */  public static int clWaitForEvents(CLEvent event) {
/* 1684:1684 */    long function_pointer = CLCapabilities.clWaitForEvents;
/* 1685:1685 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1686:1686 */    int __result = nclWaitForEvents(1, APIUtil.getPointer(event), function_pointer);
/* 1687:1687 */    return __result;
/* 1688:     */  }
/* 1689:     */  
/* 1690:     */  public static int clGetEventInfo(CLEvent event, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1691:1691 */    long function_pointer = CLCapabilities.clGetEventInfo;
/* 1692:1692 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1693:1693 */    if (param_value != null)
/* 1694:1694 */      BufferChecks.checkDirect(param_value);
/* 1695:1695 */    if (param_value_size_ret != null)
/* 1696:1696 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1697:1697 */    int __result = nclGetEventInfo(event.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1698:1698 */    return __result;
/* 1699:     */  }
/* 1700:     */  
/* 1701:     */  static native int nclGetEventInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1702:     */  
/* 1703:1703 */  public static int clRetainEvent(CLEvent event) { long function_pointer = CLCapabilities.clRetainEvent;
/* 1704:1704 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1705:1705 */    int __result = nclRetainEvent(event.getPointer(), function_pointer);
/* 1706:1706 */    if (__result == 0) event.retain();
/* 1707:1707 */    return __result;
/* 1708:     */  }
/* 1709:     */  
/* 1710:     */  static native int nclRetainEvent(long paramLong1, long paramLong2);
/* 1711:     */  
/* 1712:1712 */  public static int clReleaseEvent(CLEvent event) { long function_pointer = CLCapabilities.clReleaseEvent;
/* 1713:1713 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1714:1714 */    int __result = nclReleaseEvent(event.getPointer(), function_pointer);
/* 1715:1715 */    if (__result == 0) event.release();
/* 1716:1716 */    return __result;
/* 1717:     */  }
/* 1718:     */  
/* 1719:     */  static native int nclReleaseEvent(long paramLong1, long paramLong2);
/* 1720:     */  
/* 1721:1721 */  public static int clEnqueueMarker(CLCommandQueue command_queue, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueMarker;
/* 1722:1722 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1723:1723 */    BufferChecks.checkBuffer(event, 1);
/* 1724:1724 */    int __result = nclEnqueueMarker(command_queue.getPointer(), MemoryUtil.getAddress(event), function_pointer);
/* 1725:1725 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 1726:1726 */    return __result;
/* 1727:     */  }
/* 1728:     */  
/* 1729:     */  static native int nclEnqueueMarker(long paramLong1, long paramLong2, long paramLong3);
/* 1730:     */  
/* 1731:1731 */  public static int clEnqueueBarrier(CLCommandQueue command_queue) { long function_pointer = CLCapabilities.clEnqueueBarrier;
/* 1732:1732 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1733:1733 */    int __result = nclEnqueueBarrier(command_queue.getPointer(), function_pointer);
/* 1734:1734 */    return __result;
/* 1735:     */  }
/* 1736:     */  
/* 1737:     */  static native int nclEnqueueBarrier(long paramLong1, long paramLong2);
/* 1738:     */  
/* 1739:1739 */  public static int clEnqueueWaitForEvents(CLCommandQueue command_queue, PointerBuffer event_list) { long function_pointer = CLCapabilities.clEnqueueWaitForEvents;
/* 1740:1740 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1741:1741 */    BufferChecks.checkBuffer(event_list, 1);
/* 1742:1742 */    int __result = nclEnqueueWaitForEvents(command_queue.getPointer(), event_list.remaining(), MemoryUtil.getAddress(event_list), function_pointer);
/* 1743:1743 */    return __result;
/* 1744:     */  }
/* 1745:     */  
/* 1746:     */  static native int nclEnqueueWaitForEvents(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/* 1747:     */  
/* 1748:     */  public static int clEnqueueWaitForEvents(CLCommandQueue command_queue, CLEvent event) {
/* 1749:1749 */    long function_pointer = CLCapabilities.clEnqueueWaitForEvents;
/* 1750:1750 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1751:1751 */    int __result = nclEnqueueWaitForEvents(command_queue.getPointer(), 1, APIUtil.getPointer(event), function_pointer);
/* 1752:1752 */    return __result;
/* 1753:     */  }
/* 1754:     */  
/* 1755:     */  public static int clGetEventProfilingInfo(CLEvent event, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 1756:1756 */    long function_pointer = CLCapabilities.clGetEventProfilingInfo;
/* 1757:1757 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1758:1758 */    if (param_value != null)
/* 1759:1759 */      BufferChecks.checkDirect(param_value);
/* 1760:1760 */    if (param_value_size_ret != null)
/* 1761:1761 */      BufferChecks.checkBuffer(param_value_size_ret, 1);
/* 1762:1762 */    int __result = nclGetEventProfilingInfo(event.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/* 1763:1763 */    return __result;
/* 1764:     */  }
/* 1765:     */  
/* 1766:     */  static native int nclGetEventProfilingInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1767:     */  
/* 1768:1768 */  public static int clFlush(CLCommandQueue command_queue) { long function_pointer = CLCapabilities.clFlush;
/* 1769:1769 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1770:1770 */    int __result = nclFlush(command_queue.getPointer(), function_pointer);
/* 1771:1771 */    return __result;
/* 1772:     */  }
/* 1773:     */  
/* 1774:     */  static native int nclFlush(long paramLong1, long paramLong2);
/* 1775:     */  
/* 1776:1776 */  public static int clFinish(CLCommandQueue command_queue) { long function_pointer = CLCapabilities.clFinish;
/* 1777:1777 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1778:1778 */    int __result = nclFinish(command_queue.getPointer(), function_pointer);
/* 1779:1779 */    return __result;
/* 1780:     */  }
/* 1781:     */  
/* 1782:     */  static native int nclFinish(long paramLong1, long paramLong2);
/* 1783:     */  
/* 1784:1784 */  static CLFunctionAddress clGetExtensionFunctionAddress(ByteBuffer func_name) { long function_pointer = CLCapabilities.clGetExtensionFunctionAddress;
/* 1785:1785 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1786:1786 */    BufferChecks.checkDirect(func_name);
/* 1787:1787 */    BufferChecks.checkNullTerminated(func_name);
/* 1788:1788 */    CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddress(MemoryUtil.getAddress(func_name), function_pointer));
/* 1789:1789 */    return __result;
/* 1790:     */  }
/* 1791:     */  
/* 1792:     */  static native long nclGetExtensionFunctionAddress(long paramLong1, long paramLong2);
/* 1793:     */  
/* 1794:     */  static CLFunctionAddress clGetExtensionFunctionAddress(CharSequence func_name) {
/* 1795:1795 */    long function_pointer = CLCapabilities.clGetExtensionFunctionAddress;
/* 1796:1796 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1797:1797 */    CLFunctionAddress __result = new CLFunctionAddress(nclGetExtensionFunctionAddress(APIUtil.getBufferNT(func_name), function_pointer));
/* 1798:1798 */    return __result;
/* 1799:     */  }
/* 1800:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL10
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */