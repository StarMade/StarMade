/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.LongBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ import org.lwjgl.PointerWrapper;
/*      */ 
/*      */ public final class GL43
/*      */ {
/*      */   public static final int GL_NUM_SHADING_LANGUAGE_VERSIONS = 33513;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_LONG = 34638;
/*      */   public static final int GL_COMPRESSED_RGB8_ETC2 = 37492;
/*      */   public static final int GL_COMPRESSED_SRGB8_ETC2 = 37493;
/*      */   public static final int GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37494;
/*      */   public static final int GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37495;
/*      */   public static final int GL_COMPRESSED_RGBA8_ETC2_EAC = 37496;
/*      */   public static final int GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 37497;
/*      */   public static final int GL_COMPRESSED_R11_EAC = 37488;
/*      */   public static final int GL_COMPRESSED_SIGNED_R11_EAC = 37489;
/*      */   public static final int GL_COMPRESSED_RG11_EAC = 37490;
/*      */   public static final int GL_COMPRESSED_SIGNED_RG11_EAC = 37491;
/*      */   public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX = 36201;
/*      */   public static final int GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 36202;
/*      */   public static final int GL_MAX_ELEMENT_INDEX = 36203;
/*      */   public static final int GL_COMPUTE_SHADER = 37305;
/*      */   public static final int GL_MAX_COMPUTE_UNIFORM_BLOCKS = 37307;
/*      */   public static final int GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 37308;
/*      */   public static final int GL_MAX_COMPUTE_IMAGE_UNIFORMS = 37309;
/*      */   public static final int GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 33378;
/*      */   public static final int GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 33379;
/*      */   public static final int GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 33380;
/*      */   public static final int GL_MAX_COMPUTE_ATOMIC_COUNTERS = 33381;
/*      */   public static final int GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 33382;
/*      */   public static final int GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 37099;
/*      */   public static final int GL_MAX_COMPUTE_WORK_GROUP_COUNT = 37310;
/*      */   public static final int GL_MAX_COMPUTE_WORK_GROUP_SIZE = 37311;
/*      */   public static final int GL_COMPUTE_WORK_GROUP_SIZE = 33383;
/*      */   public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 37100;
/*      */   public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 37101;
/*      */   public static final int GL_DISPATCH_INDIRECT_BUFFER = 37102;
/*      */   public static final int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 37103;
/*      */   public static final int GL_COMPUTE_SHADER_BIT = 32;
/*      */   public static final int GL_DEBUG_OUTPUT = 37600;
/*      */   public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS = 33346;
/*      */   public static final int GL_CONTEXT_FLAG_DEBUG_BIT = 2;
/*      */   public static final int GL_MAX_DEBUG_MESSAGE_LENGTH = 37187;
/*      */   public static final int GL_MAX_DEBUG_LOGGED_MESSAGES = 37188;
/*      */   public static final int GL_DEBUG_LOGGED_MESSAGES = 37189;
/*      */   public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 33347;
/*      */   public static final int GL_MAX_DEBUG_GROUP_STACK_DEPTH = 33388;
/*      */   public static final int GL_DEBUG_GROUP_STACK_DEPTH = 33389;
/*      */   public static final int GL_MAX_LABEL_LENGTH = 33512;
/*      */   public static final int GL_DEBUG_CALLBACK_FUNCTION = 33348;
/*      */   public static final int GL_DEBUG_CALLBACK_USER_PARAM = 33349;
/*      */   public static final int GL_DEBUG_SOURCE_API = 33350;
/*      */   public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
/*      */   public static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
/*      */   public static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
/*      */   public static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
/*      */   public static final int GL_DEBUG_SOURCE_OTHER = 33355;
/*      */   public static final int GL_DEBUG_TYPE_ERROR = 33356;
/*      */   public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
/*      */   public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
/*      */   public static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
/*      */   public static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
/*      */   public static final int GL_DEBUG_TYPE_OTHER = 33361;
/*      */   public static final int GL_DEBUG_TYPE_MARKER = 33384;
/*      */   public static final int GL_DEBUG_TYPE_PUSH_GROUP = 33385;
/*      */   public static final int GL_DEBUG_TYPE_POP_GROUP = 33386;
/*      */   public static final int GL_DEBUG_SEVERITY_HIGH = 37190;
/*      */   public static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
/*      */   public static final int GL_DEBUG_SEVERITY_LOW = 37192;
/*      */   public static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
/*      */   public static final int GL_STACK_UNDERFLOW = 1284;
/*      */   public static final int GL_STACK_OVERFLOW = 1283;
/*      */   public static final int GL_BUFFER = 33504;
/*      */   public static final int GL_SHADER = 33505;
/*      */   public static final int GL_PROGRAM = 33506;
/*      */   public static final int GL_QUERY = 33507;
/*      */   public static final int GL_PROGRAM_PIPELINE = 33508;
/*      */   public static final int GL_SAMPLER = 33510;
/*      */   public static final int GL_DISPLAY_LIST = 33511;
/*      */   public static final int GL_MAX_UNIFORM_LOCATIONS = 33390;
/*      */   public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 37648;
/*      */   public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 37649;
/*      */   public static final int GL_FRAMEBUFFER_DEFAULT_LAYERS = 37650;
/*      */   public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 37651;
/*      */   public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;
/*      */   public static final int GL_MAX_FRAMEBUFFER_WIDTH = 37653;
/*      */   public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 37654;
/*      */   public static final int GL_MAX_FRAMEBUFFER_LAYERS = 37655;
/*      */   public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 37656;
/*      */   public static final int GL_TEXTURE_1D = 3552;
/*      */   public static final int GL_TEXTURE_1D_ARRAY = 35864;
/*      */   public static final int GL_TEXTURE_2D = 3553;
/*      */   public static final int GL_TEXTURE_2D_ARRAY = 35866;
/*      */   public static final int GL_TEXTURE_3D = 32879;
/*      */   public static final int GL_TEXTURE_CUBE_MAP = 34067;
/*      */   public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;
/*      */   public static final int GL_TEXTURE_RECTANGLE = 34037;
/*      */   public static final int GL_TEXTURE_BUFFER = 35882;
/*      */   public static final int GL_RENDERBUFFER = 36161;
/*      */   public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
/*      */   public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
/*      */   public static final int GL_SAMPLES = 32937;
/*      */   public static final int GL_NUM_SAMPLE_COUNTS = 37760;
/*      */   public static final int GL_INTERNALFORMAT_SUPPORTED = 33391;
/*      */   public static final int GL_INTERNALFORMAT_PREFERRED = 33392;
/*      */   public static final int GL_INTERNALFORMAT_RED_SIZE = 33393;
/*      */   public static final int GL_INTERNALFORMAT_GREEN_SIZE = 33394;
/*      */   public static final int GL_INTERNALFORMAT_BLUE_SIZE = 33395;
/*      */   public static final int GL_INTERNALFORMAT_ALPHA_SIZE = 33396;
/*      */   public static final int GL_INTERNALFORMAT_DEPTH_SIZE = 33397;
/*      */   public static final int GL_INTERNALFORMAT_STENCIL_SIZE = 33398;
/*      */   public static final int GL_INTERNALFORMAT_SHARED_SIZE = 33399;
/*      */   public static final int GL_INTERNALFORMAT_RED_TYPE = 33400;
/*      */   public static final int GL_INTERNALFORMAT_GREEN_TYPE = 33401;
/*      */   public static final int GL_INTERNALFORMAT_BLUE_TYPE = 33402;
/*      */   public static final int GL_INTERNALFORMAT_ALPHA_TYPE = 33403;
/*      */   public static final int GL_INTERNALFORMAT_DEPTH_TYPE = 33404;
/*      */   public static final int GL_INTERNALFORMAT_STENCIL_TYPE = 33405;
/*      */   public static final int GL_MAX_WIDTH = 33406;
/*      */   public static final int GL_MAX_HEIGHT = 33407;
/*      */   public static final int GL_MAX_DEPTH = 33408;
/*      */   public static final int GL_MAX_LAYERS = 33409;
/*      */   public static final int GL_MAX_COMBINED_DIMENSIONS = 33410;
/*      */   public static final int GL_COLOR_COMPONENTS = 33411;
/*      */   public static final int GL_DEPTH_COMPONENTS = 33412;
/*      */   public static final int GL_STENCIL_COMPONENTS = 33413;
/*      */   public static final int GL_COLOR_RENDERABLE = 33414;
/*      */   public static final int GL_DEPTH_RENDERABLE = 33415;
/*      */   public static final int GL_STENCIL_RENDERABLE = 33416;
/*      */   public static final int GL_FRAMEBUFFER_RENDERABLE = 33417;
/*      */   public static final int GL_FRAMEBUFFER_RENDERABLE_LAYERED = 33418;
/*      */   public static final int GL_FRAMEBUFFER_BLEND = 33419;
/*      */   public static final int GL_READ_PIXELS = 33420;
/*      */   public static final int GL_READ_PIXELS_FORMAT = 33421;
/*      */   public static final int GL_READ_PIXELS_TYPE = 33422;
/*      */   public static final int GL_TEXTURE_IMAGE_FORMAT = 33423;
/*      */   public static final int GL_TEXTURE_IMAGE_TYPE = 33424;
/*      */   public static final int GL_GET_TEXTURE_IMAGE_FORMAT = 33425;
/*      */   public static final int GL_GET_TEXTURE_IMAGE_TYPE = 33426;
/*      */   public static final int GL_MIPMAP = 33427;
/*      */   public static final int GL_MANUAL_GENERATE_MIPMAP = 33428;
/*      */   public static final int GL_AUTO_GENERATE_MIPMAP = 33429;
/*      */   public static final int GL_COLOR_ENCODING = 33430;
/*      */   public static final int GL_SRGB_READ = 33431;
/*      */   public static final int GL_SRGB_WRITE = 33432;
/*      */   public static final int GL_SRGB_DECODE_ARB = 33433;
/*      */   public static final int GL_FILTER = 33434;
/*      */   public static final int GL_VERTEX_TEXTURE = 33435;
/*      */   public static final int GL_TESS_CONTROL_TEXTURE = 33436;
/*      */   public static final int GL_TESS_EVALUATION_TEXTURE = 33437;
/*      */   public static final int GL_GEOMETRY_TEXTURE = 33438;
/*      */   public static final int GL_FRAGMENT_TEXTURE = 33439;
/*      */   public static final int GL_COMPUTE_TEXTURE = 33440;
/*      */   public static final int GL_TEXTURE_SHADOW = 33441;
/*      */   public static final int GL_TEXTURE_GATHER = 33442;
/*      */   public static final int GL_TEXTURE_GATHER_SHADOW = 33443;
/*      */   public static final int GL_SHADER_IMAGE_LOAD = 33444;
/*      */   public static final int GL_SHADER_IMAGE_STORE = 33445;
/*      */   public static final int GL_SHADER_IMAGE_ATOMIC = 33446;
/*      */   public static final int GL_IMAGE_TEXEL_SIZE = 33447;
/*      */   public static final int GL_IMAGE_COMPATIBILITY_CLASS = 33448;
/*      */   public static final int GL_IMAGE_PIXEL_FORMAT = 33449;
/*      */   public static final int GL_IMAGE_PIXEL_TYPE = 33450;
/*      */   public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;
/*      */   public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST = 33452;
/*      */   public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST = 33453;
/*      */   public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE = 33454;
/*      */   public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 33455;
/*      */   public static final int GL_TEXTURE_COMPRESSED = 34465;
/*      */   public static final int GL_TEXTURE_COMPRESSED_BLOCK_WIDTH = 33457;
/*      */   public static final int GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT = 33458;
/*      */   public static final int GL_TEXTURE_COMPRESSED_BLOCK_SIZE = 33459;
/*      */   public static final int GL_CLEAR_BUFFER = 33460;
/*      */   public static final int GL_TEXTURE_VIEW = 33461;
/*      */   public static final int GL_VIEW_COMPATIBILITY_CLASS = 33462;
/*      */   public static final int GL_FULL_SUPPORT = 33463;
/*      */   public static final int GL_CAVEAT_SUPPORT = 33464;
/*      */   public static final int GL_IMAGE_CLASS_4_X_32 = 33465;
/*      */   public static final int GL_IMAGE_CLASS_2_X_32 = 33466;
/*      */   public static final int GL_IMAGE_CLASS_1_X_32 = 33467;
/*      */   public static final int GL_IMAGE_CLASS_4_X_16 = 33468;
/*      */   public static final int GL_IMAGE_CLASS_2_X_16 = 33469;
/*      */   public static final int GL_IMAGE_CLASS_1_X_16 = 33470;
/*      */   public static final int GL_IMAGE_CLASS_4_X_8 = 33471;
/*      */   public static final int GL_IMAGE_CLASS_2_X_8 = 33472;
/*      */   public static final int GL_IMAGE_CLASS_1_X_8 = 33473;
/*      */   public static final int GL_IMAGE_CLASS_11_11_10 = 33474;
/*      */   public static final int GL_IMAGE_CLASS_10_10_10_2 = 33475;
/*      */   public static final int GL_VIEW_CLASS_128_BITS = 33476;
/*      */   public static final int GL_VIEW_CLASS_96_BITS = 33477;
/*      */   public static final int GL_VIEW_CLASS_64_BITS = 33478;
/*      */   public static final int GL_VIEW_CLASS_48_BITS = 33479;
/*      */   public static final int GL_VIEW_CLASS_32_BITS = 33480;
/*      */   public static final int GL_VIEW_CLASS_24_BITS = 33481;
/*      */   public static final int GL_VIEW_CLASS_16_BITS = 33482;
/*      */   public static final int GL_VIEW_CLASS_8_BITS = 33483;
/*      */   public static final int GL_VIEW_CLASS_S3TC_DXT1_RGB = 33484;
/*      */   public static final int GL_VIEW_CLASS_S3TC_DXT1_RGBA = 33485;
/*      */   public static final int GL_VIEW_CLASS_S3TC_DXT3_RGBA = 33486;
/*      */   public static final int GL_VIEW_CLASS_S3TC_DXT5_RGBA = 33487;
/*      */   public static final int GL_VIEW_CLASS_RGTC1_RED = 33488;
/*      */   public static final int GL_VIEW_CLASS_RGTC2_RG = 33489;
/*      */   public static final int GL_VIEW_CLASS_BPTC_UNORM = 33490;
/*      */   public static final int GL_VIEW_CLASS_BPTC_FLOAT = 33491;
/*      */   public static final int GL_UNIFORM = 37601;
/*      */   public static final int GL_UNIFORM_BLOCK = 37602;
/*      */   public static final int GL_PROGRAM_INPUT = 37603;
/*      */   public static final int GL_PROGRAM_OUTPUT = 37604;
/*      */   public static final int GL_BUFFER_VARIABLE = 37605;
/*      */   public static final int GL_SHADER_STORAGE_BLOCK = 37606;
/*      */   public static final int GL_VERTEX_SUBROUTINE = 37608;
/*      */   public static final int GL_TESS_CONTROL_SUBROUTINE = 37609;
/*      */   public static final int GL_TESS_EVALUATION_SUBROUTINE = 37610;
/*      */   public static final int GL_GEOMETRY_SUBROUTINE = 37611;
/*      */   public static final int GL_FRAGMENT_SUBROUTINE = 37612;
/*      */   public static final int GL_COMPUTE_SUBROUTINE = 37613;
/*      */   public static final int GL_VERTEX_SUBROUTINE_UNIFORM = 37614;
/*      */   public static final int GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 37615;
/*      */   public static final int GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 37616;
/*      */   public static final int GL_GEOMETRY_SUBROUTINE_UNIFORM = 37617;
/*      */   public static final int GL_FRAGMENT_SUBROUTINE_UNIFORM = 37618;
/*      */   public static final int GL_COMPUTE_SUBROUTINE_UNIFORM = 37619;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_VARYING = 37620;
/*      */   public static final int GL_ACTIVE_RESOURCES = 37621;
/*      */   public static final int GL_MAX_NAME_LENGTH = 37622;
/*      */   public static final int GL_MAX_NUM_ACTIVE_VARIABLES = 37623;
/*      */   public static final int GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 37624;
/*      */   public static final int GL_NAME_LENGTH = 37625;
/*      */   public static final int GL_TYPE = 37626;
/*      */   public static final int GL_ARRAY_SIZE = 37627;
/*      */   public static final int GL_OFFSET = 37628;
/*      */   public static final int GL_BLOCK_INDEX = 37629;
/*      */   public static final int GL_ARRAY_STRIDE = 37630;
/*      */   public static final int GL_MATRIX_STRIDE = 37631;
/*      */   public static final int GL_IS_ROW_MAJOR = 37632;
/*      */   public static final int GL_ATOMIC_COUNTER_BUFFER_INDEX = 37633;
/*      */   public static final int GL_BUFFER_BINDING = 37634;
/*      */   public static final int GL_BUFFER_DATA_SIZE = 37635;
/*      */   public static final int GL_NUM_ACTIVE_VARIABLES = 37636;
/*      */   public static final int GL_ACTIVE_VARIABLES = 37637;
/*      */   public static final int GL_REFERENCED_BY_VERTEX_SHADER = 37638;
/*      */   public static final int GL_REFERENCED_BY_TESS_CONTROL_SHADER = 37639;
/*      */   public static final int GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 37640;
/*      */   public static final int GL_REFERENCED_BY_GEOMETRY_SHADER = 37641;
/*      */   public static final int GL_REFERENCED_BY_FRAGMENT_SHADER = 37642;
/*      */   public static final int GL_REFERENCED_BY_COMPUTE_SHADER = 37643;
/*      */   public static final int GL_TOP_LEVEL_ARRAY_SIZE = 37644;
/*      */   public static final int GL_TOP_LEVEL_ARRAY_STRIDE = 37645;
/*      */   public static final int GL_LOCATION = 37646;
/*      */   public static final int GL_LOCATION_INDEX = 37647;
/*      */   public static final int GL_IS_PER_PATCH = 37607;
/*      */   public static final int GL_SHADER_STORAGE_BUFFER = 37074;
/*      */   public static final int GL_SHADER_STORAGE_BUFFER_BINDING = 37075;
/*      */   public static final int GL_SHADER_STORAGE_BUFFER_START = 37076;
/*      */   public static final int GL_SHADER_STORAGE_BUFFER_SIZE = 37077;
/*      */   public static final int GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS = 37078;
/*      */   public static final int GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 37079;
/*      */   public static final int GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 37080;
/*      */   public static final int GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 37081;
/*      */   public static final int GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 37082;
/*      */   public static final int GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 37083;
/*      */   public static final int GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS = 37084;
/*      */   public static final int GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS = 37085;
/*      */   public static final int GL_MAX_SHADER_STORAGE_BLOCK_SIZE = 37086;
/*      */   public static final int GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 37087;
/*      */   public static final int GL_SHADER_STORAGE_BARRIER_BIT = 8192;
/*      */   public static final int GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 36665;
/*      */   public static final int GL_DEPTH_STENCIL_TEXTURE_MODE = 37098;
/*      */   public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
/*      */   public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
/*      */   public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;
/*      */   public static final int GL_TEXTURE_VIEW_MIN_LEVEL = 33499;
/*      */   public static final int GL_TEXTURE_VIEW_NUM_LEVELS = 33500;
/*      */   public static final int GL_TEXTURE_VIEW_MIN_LAYER = 33501;
/*      */   public static final int GL_TEXTURE_VIEW_NUM_LAYERS = 33502;
/*      */   public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 33503;
/*      */   public static final int GL_VERTEX_ATTRIB_BINDING = 33492;
/*      */   public static final int GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 33493;
/*      */   public static final int GL_VERTEX_BINDING_DIVISOR = 33494;
/*      */   public static final int GL_VERTEX_BINDING_OFFSET = 33495;
/*      */   public static final int GL_VERTEX_BINDING_STRIDE = 33496;
/*      */   public static final int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 33497;
/*      */   public static final int GL_MAX_VERTEX_ATTRIB_BINDINGS = 33498;
/*      */ 
/*      */   public static void glClearBufferData(int target, int internalformat, int format, int type, ByteBuffer data)
/*      */   {
/*  515 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  516 */     long function_pointer = caps.glClearBufferData;
/*  517 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  518 */     BufferChecks.checkBuffer(data, 1);
/*  519 */     nglClearBufferData(target, internalformat, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglClearBufferData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glClearBufferSubData(int target, int internalformat, long offset, int format, int type, ByteBuffer data) {
/*  524 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  525 */     long function_pointer = caps.glClearBufferSubData;
/*  526 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  527 */     BufferChecks.checkDirect(data);
/*  528 */     nglClearBufferSubData(target, internalformat, offset, data.remaining(), format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglClearBufferSubData(int paramInt1, int paramInt2, long paramLong1, long paramLong2, int paramInt3, int paramInt4, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z) {
/*  533 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  534 */     long function_pointer = caps.glDispatchCompute;
/*  535 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  536 */     nglDispatchCompute(num_groups_x, num_groups_y, num_groups_z, function_pointer);
/*      */   }
/*      */   static native void nglDispatchCompute(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glDispatchComputeIndirect(long indirect) {
/*  541 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  542 */     long function_pointer = caps.glDispatchComputeIndirect;
/*  543 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  544 */     nglDispatchComputeIndirect(indirect, function_pointer);
/*      */   }
/*      */   static native void nglDispatchComputeIndirect(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyImageSubData(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth) {
/*  549 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  550 */     long function_pointer = caps.glCopyImageSubData;
/*  551 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  552 */     nglCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth, srcHeight, srcDepth, function_pointer);
/*      */   }
/*      */   static native void nglCopyImageSubData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, long paramLong);
/*      */ 
/*      */   public static void glDebugMessageControl(int source, int type, int severity, IntBuffer ids, boolean enabled) {
/*  557 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  558 */     long function_pointer = caps.glDebugMessageControl;
/*  559 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  560 */     if (ids != null)
/*  561 */       BufferChecks.checkDirect(ids);
/*  562 */     nglDebugMessageControl(source, type, severity, ids == null ? 0 : ids.remaining(), MemoryUtil.getAddressSafe(ids), enabled, function_pointer);
/*      */   }
/*      */   static native void nglDebugMessageControl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, boolean paramBoolean, long paramLong2);
/*      */ 
/*      */   public static void glDebugMessageInsert(int source, int type, int id, int severity, ByteBuffer buf) {
/*  567 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  568 */     long function_pointer = caps.glDebugMessageInsert;
/*  569 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  570 */     BufferChecks.checkDirect(buf);
/*  571 */     nglDebugMessageInsert(source, type, id, severity, buf.remaining(), MemoryUtil.getAddress(buf), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDebugMessageInsert(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDebugMessageInsert(int source, int type, int id, int severity, CharSequence buf) {
/*  577 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  578 */     long function_pointer = caps.glDebugMessageInsert;
/*  579 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  580 */     nglDebugMessageInsert(source, type, id, severity, buf.length(), APIUtil.getBuffer(caps, buf), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glDebugMessageCallback(KHRDebugCallback callback)
/*      */   {
/*  591 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  592 */     long function_pointer = caps.glDebugMessageCallback;
/*  593 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  594 */     long userParam = callback == null ? 0L : CallbackUtil.createGlobalRef(callback.getHandler());
/*  595 */     CallbackUtil.registerContextCallbackKHR(userParam);
/*  596 */     nglDebugMessageCallback(callback == null ? 0L : callback.getPointer(), userParam, function_pointer);
/*      */   }
/*      */   static native void nglDebugMessageCallback(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) {
/*  601 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  602 */     long function_pointer = caps.glGetDebugMessageLog;
/*  603 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  604 */     if (sources != null)
/*  605 */       BufferChecks.checkBuffer(sources, count);
/*  606 */     if (types != null)
/*  607 */       BufferChecks.checkBuffer(types, count);
/*  608 */     if (ids != null)
/*  609 */       BufferChecks.checkBuffer(ids, count);
/*  610 */     if (severities != null)
/*  611 */       BufferChecks.checkBuffer(severities, count);
/*  612 */     if (lengths != null)
/*  613 */       BufferChecks.checkBuffer(lengths, count);
/*  614 */     if (messageLog != null)
/*  615 */       BufferChecks.checkDirect(messageLog);
/*  616 */     int __result = nglGetDebugMessageLog(count, messageLog == null ? 0 : messageLog.remaining(), MemoryUtil.getAddressSafe(sources), MemoryUtil.getAddressSafe(types), MemoryUtil.getAddressSafe(ids), MemoryUtil.getAddressSafe(severities), MemoryUtil.getAddressSafe(lengths), MemoryUtil.getAddressSafe(messageLog), function_pointer);
/*  617 */     return __result;
/*      */   }
/*      */   static native int nglGetDebugMessageLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/*      */ 
/*      */   public static void glPushDebugGroup(int source, int id, ByteBuffer message) {
/*  622 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  623 */     long function_pointer = caps.glPushDebugGroup;
/*  624 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  625 */     BufferChecks.checkDirect(message);
/*  626 */     nglPushDebugGroup(source, id, message.remaining(), MemoryUtil.getAddress(message), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglPushDebugGroup(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glPushDebugGroup(int source, int id, CharSequence message) {
/*  632 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  633 */     long function_pointer = caps.glPushDebugGroup;
/*  634 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  635 */     nglPushDebugGroup(source, id, message.length(), APIUtil.getBuffer(caps, message), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glPopDebugGroup() {
/*  639 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  640 */     long function_pointer = caps.glPopDebugGroup;
/*  641 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  642 */     nglPopDebugGroup(function_pointer);
/*      */   }
/*      */   static native void nglPopDebugGroup(long paramLong);
/*      */ 
/*      */   public static void glObjectLabel(int identifier, int name, ByteBuffer label) {
/*  647 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  648 */     long function_pointer = caps.glObjectLabel;
/*  649 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  650 */     if (label != null)
/*  651 */       BufferChecks.checkDirect(label);
/*  652 */     nglObjectLabel(identifier, name, label == null ? 0 : label.remaining(), MemoryUtil.getAddressSafe(label), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglObjectLabel(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glObjectLabel(int identifier, int name, CharSequence label) {
/*  658 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  659 */     long function_pointer = caps.glObjectLabel;
/*  660 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  661 */     nglObjectLabel(identifier, name, label.length(), APIUtil.getBuffer(caps, label), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetObjectLabel(int identifier, int name, IntBuffer length, ByteBuffer label) {
/*  665 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  666 */     long function_pointer = caps.glGetObjectLabel;
/*  667 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  668 */     if (length != null)
/*  669 */       BufferChecks.checkBuffer(length, 1);
/*  670 */     BufferChecks.checkDirect(label);
/*  671 */     nglGetObjectLabel(identifier, name, label.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(label), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetObjectLabel(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static String glGetObjectLabel(int identifier, int name, int bufSize) {
/*  677 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  678 */     long function_pointer = caps.glGetObjectLabel;
/*  679 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  680 */     IntBuffer label_length = APIUtil.getLengths(caps);
/*  681 */     ByteBuffer label = APIUtil.getBufferByte(caps, bufSize);
/*  682 */     nglGetObjectLabel(identifier, name, bufSize, MemoryUtil.getAddress0(label_length), MemoryUtil.getAddress(label), function_pointer);
/*  683 */     label.limit(label_length.get(0));
/*  684 */     return APIUtil.getString(caps, label);
/*      */   }
/*      */ 
/*      */   public static void glObjectPtrLabel(PointerWrapper ptr, ByteBuffer label) {
/*  688 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  689 */     long function_pointer = caps.glObjectPtrLabel;
/*  690 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  691 */     if (label != null)
/*  692 */       BufferChecks.checkDirect(label);
/*  693 */     nglObjectPtrLabel(ptr.getPointer(), label == null ? 0 : label.remaining(), MemoryUtil.getAddressSafe(label), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglObjectPtrLabel(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glObjectPtrLabel(PointerWrapper ptr, CharSequence label) {
/*  699 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  700 */     long function_pointer = caps.glObjectPtrLabel;
/*  701 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  702 */     nglObjectPtrLabel(ptr.getPointer(), label.length(), APIUtil.getBuffer(caps, label), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetObjectPtrLabel(PointerWrapper ptr, IntBuffer length, ByteBuffer label) {
/*  706 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  707 */     long function_pointer = caps.glGetObjectPtrLabel;
/*  708 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  709 */     if (length != null)
/*  710 */       BufferChecks.checkBuffer(length, 1);
/*  711 */     BufferChecks.checkDirect(label);
/*  712 */     nglGetObjectPtrLabel(ptr.getPointer(), label.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(label), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetObjectPtrLabel(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static String glGetObjectPtrLabel(PointerWrapper ptr, int bufSize) {
/*  718 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  719 */     long function_pointer = caps.glGetObjectPtrLabel;
/*  720 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  721 */     IntBuffer label_length = APIUtil.getLengths(caps);
/*  722 */     ByteBuffer label = APIUtil.getBufferByte(caps, bufSize);
/*  723 */     nglGetObjectPtrLabel(ptr.getPointer(), bufSize, MemoryUtil.getAddress0(label_length), MemoryUtil.getAddress(label), function_pointer);
/*  724 */     label.limit(label_length.get(0));
/*  725 */     return APIUtil.getString(caps, label);
/*      */   }
/*      */ 
/*      */   public static void glFramebufferParameteri(int target, int pname, int param) {
/*  729 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  730 */     long function_pointer = caps.glFramebufferParameteri;
/*  731 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  732 */     nglFramebufferParameteri(target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glGetFramebufferParameter(int target, int pname, IntBuffer params) {
/*  737 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  738 */     long function_pointer = caps.glGetFramebufferParameteriv;
/*  739 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  740 */     BufferChecks.checkBuffer(params, 1);
/*  741 */     nglGetFramebufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFramebufferParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetFramebufferParameteri(int target, int pname) {
/*  747 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  748 */     long function_pointer = caps.glGetFramebufferParameteriv;
/*  749 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  750 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  751 */     nglGetFramebufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  752 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetInternalformat(int target, int internalformat, int pname, LongBuffer params) {
/*  756 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  757 */     long function_pointer = caps.glGetInternalformati64v;
/*  758 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  759 */     BufferChecks.checkDirect(params);
/*  760 */     nglGetInternalformati64v(target, internalformat, pname, params.remaining(), MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetInternalformati64v(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static long glGetInternalformati64(int target, int internalformat, int pname) {
/*  766 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  767 */     long function_pointer = caps.glGetInternalformati64v;
/*  768 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  769 */     LongBuffer params = APIUtil.getBufferLong(caps);
/*  770 */     nglGetInternalformati64v(target, internalformat, pname, 1, MemoryUtil.getAddress(params), function_pointer);
/*  771 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth) {
/*  775 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  776 */     long function_pointer = caps.glInvalidateTexSubImage;
/*  777 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  778 */     nglInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, function_pointer);
/*      */   }
/*      */   static native void nglInvalidateTexSubImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*      */ 
/*      */   public static void glInvalidateTexImage(int texture, int level) {
/*  783 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  784 */     long function_pointer = caps.glInvalidateTexImage;
/*  785 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  786 */     nglInvalidateTexImage(texture, level, function_pointer);
/*      */   }
/*      */   static native void nglInvalidateTexImage(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glInvalidateBufferSubData(int buffer, long offset, long length) {
/*  791 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  792 */     long function_pointer = caps.glInvalidateBufferSubData;
/*  793 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  794 */     nglInvalidateBufferSubData(buffer, offset, length, function_pointer);
/*      */   }
/*      */   static native void nglInvalidateBufferSubData(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glInvalidateBufferData(int buffer) {
/*  799 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  800 */     long function_pointer = caps.glInvalidateBufferData;
/*  801 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  802 */     nglInvalidateBufferData(buffer, function_pointer);
/*      */   }
/*      */   static native void nglInvalidateBufferData(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glInvalidateFramebuffer(int target, IntBuffer attachments) {
/*  807 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  808 */     long function_pointer = caps.glInvalidateFramebuffer;
/*  809 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  810 */     BufferChecks.checkDirect(attachments);
/*  811 */     nglInvalidateFramebuffer(target, attachments.remaining(), MemoryUtil.getAddress(attachments), function_pointer);
/*      */   }
/*      */   static native void nglInvalidateFramebuffer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glInvalidateSubFramebuffer(int target, IntBuffer attachments, int x, int y, int width, int height) {
/*  816 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  817 */     long function_pointer = caps.glInvalidateSubFramebuffer;
/*  818 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  819 */     BufferChecks.checkDirect(attachments);
/*  820 */     nglInvalidateSubFramebuffer(target, attachments.remaining(), MemoryUtil.getAddress(attachments), x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglInvalidateSubFramebuffer(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong2);
/*      */ 
/*      */   public static void glMultiDrawArraysIndirect(int mode, ByteBuffer indirect, int primcount, int stride) {
/*  825 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  826 */     long function_pointer = caps.glMultiDrawArraysIndirect;
/*  827 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  828 */     GLChecks.ensureIndirectBOdisabled(caps);
/*  829 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 16 : stride) * primcount);
/*  830 */     nglMultiDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*      */   }
/*      */   static native void nglMultiDrawArraysIndirect(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/*      */ 
/*  834 */   public static void glMultiDrawArraysIndirect(int mode, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  835 */     long function_pointer = caps.glMultiDrawArraysIndirect;
/*  836 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  837 */     GLChecks.ensureIndirectBOenabled(caps);
/*  838 */     nglMultiDrawArraysIndirectBO(mode, indirect_buffer_offset, primcount, stride, function_pointer); }
/*      */ 
/*      */   static native void nglMultiDrawArraysIndirectBO(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/*      */ 
/*      */   public static void glMultiDrawArraysIndirect(int mode, IntBuffer indirect, int primcount, int stride)
/*      */   {
/*  844 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  845 */     long function_pointer = caps.glMultiDrawArraysIndirect;
/*  846 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  847 */     GLChecks.ensureIndirectBOdisabled(caps);
/*  848 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 4 : stride >> 2) * primcount);
/*  849 */     nglMultiDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glMultiDrawElementsIndirect(int mode, int type, ByteBuffer indirect, int primcount, int stride) {
/*  853 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  854 */     long function_pointer = caps.glMultiDrawElementsIndirect;
/*  855 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  856 */     GLChecks.ensureIndirectBOdisabled(caps);
/*  857 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 20 : stride) * primcount);
/*  858 */     nglMultiDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*      */   }
/*      */   static native void nglMultiDrawElementsIndirect(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/*      */ 
/*  862 */   public static void glMultiDrawElementsIndirect(int mode, int type, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  863 */     long function_pointer = caps.glMultiDrawElementsIndirect;
/*  864 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  865 */     GLChecks.ensureIndirectBOenabled(caps);
/*  866 */     nglMultiDrawElementsIndirectBO(mode, type, indirect_buffer_offset, primcount, stride, function_pointer); }
/*      */ 
/*      */   static native void nglMultiDrawElementsIndirectBO(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/*      */ 
/*      */   public static void glMultiDrawElementsIndirect(int mode, int type, IntBuffer indirect, int primcount, int stride)
/*      */   {
/*  872 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  873 */     long function_pointer = caps.glMultiDrawElementsIndirect;
/*  874 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  875 */     GLChecks.ensureIndirectBOdisabled(caps);
/*  876 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 5 : stride >> 2) * primcount);
/*  877 */     nglMultiDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetProgramInterface(int program, int programInterface, int pname, IntBuffer params) {
/*  881 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  882 */     long function_pointer = caps.glGetProgramInterfaceiv;
/*  883 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  884 */     BufferChecks.checkBuffer(params, 1);
/*  885 */     nglGetProgramInterfaceiv(program, programInterface, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetProgramInterfaceiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetProgramInterfacei(int program, int programInterface, int pname) {
/*  891 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  892 */     long function_pointer = caps.glGetProgramInterfaceiv;
/*  893 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  894 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  895 */     nglGetProgramInterfaceiv(program, programInterface, pname, MemoryUtil.getAddress(params), function_pointer);
/*  896 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static int glGetProgramResourceIndex(int program, int programInterface, ByteBuffer name) {
/*  900 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  901 */     long function_pointer = caps.glGetProgramResourceIndex;
/*  902 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  903 */     BufferChecks.checkDirect(name);
/*  904 */     BufferChecks.checkNullTerminated(name);
/*  905 */     int __result = nglGetProgramResourceIndex(program, programInterface, MemoryUtil.getAddress(name), function_pointer);
/*  906 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglGetProgramResourceIndex(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetProgramResourceIndex(int program, int programInterface, CharSequence name) {
/*  912 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  913 */     long function_pointer = caps.glGetProgramResourceIndex;
/*  914 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  915 */     int __result = nglGetProgramResourceIndex(program, programInterface, APIUtil.getBufferNT(caps, name), function_pointer);
/*  916 */     return __result;
/*      */   }
/*      */ 
/*      */   public static void glGetProgramResourceName(int program, int programInterface, int index, IntBuffer length, ByteBuffer name) {
/*  920 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  921 */     long function_pointer = caps.glGetProgramResourceName;
/*  922 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  923 */     if (length != null)
/*  924 */       BufferChecks.checkBuffer(length, 1);
/*  925 */     if (name != null)
/*  926 */       BufferChecks.checkDirect(name);
/*  927 */     nglGetProgramResourceName(program, programInterface, index, name == null ? 0 : name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(name), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetProgramResourceName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static String glGetProgramResourceName(int program, int programInterface, int index, int bufSize) {
/*  933 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  934 */     long function_pointer = caps.glGetProgramResourceName;
/*  935 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  936 */     IntBuffer name_length = APIUtil.getLengths(caps);
/*  937 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/*  938 */     nglGetProgramResourceName(program, programInterface, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(name), function_pointer);
/*  939 */     name.limit(name_length.get(0));
/*  940 */     return APIUtil.getString(caps, name);
/*      */   }
/*      */ 
/*      */   public static void glGetProgramResource(int program, int programInterface, int index, IntBuffer props, IntBuffer length, IntBuffer params) {
/*  944 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  945 */     long function_pointer = caps.glGetProgramResourceiv;
/*  946 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  947 */     BufferChecks.checkDirect(props);
/*  948 */     if (length != null)
/*  949 */       BufferChecks.checkBuffer(length, 1);
/*  950 */     BufferChecks.checkDirect(params);
/*  951 */     nglGetProgramResourceiv(program, programInterface, index, props.remaining(), MemoryUtil.getAddress(props), params.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetProgramResourceiv(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static int glGetProgramResourceLocation(int program, int programInterface, ByteBuffer name) {
/*  956 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  957 */     long function_pointer = caps.glGetProgramResourceLocation;
/*  958 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  959 */     BufferChecks.checkDirect(name);
/*  960 */     BufferChecks.checkNullTerminated(name);
/*  961 */     int __result = nglGetProgramResourceLocation(program, programInterface, MemoryUtil.getAddress(name), function_pointer);
/*  962 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglGetProgramResourceLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetProgramResourceLocation(int program, int programInterface, CharSequence name) {
/*  968 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  969 */     long function_pointer = caps.glGetProgramResourceLocation;
/*  970 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  971 */     int __result = nglGetProgramResourceLocation(program, programInterface, APIUtil.getBufferNT(caps, name), function_pointer);
/*  972 */     return __result;
/*      */   }
/*      */ 
/*      */   public static int glGetProgramResourceLocationIndex(int program, int programInterface, ByteBuffer name) {
/*  976 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  977 */     long function_pointer = caps.glGetProgramResourceLocationIndex;
/*  978 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  979 */     BufferChecks.checkDirect(name);
/*  980 */     BufferChecks.checkNullTerminated(name);
/*  981 */     int __result = nglGetProgramResourceLocationIndex(program, programInterface, MemoryUtil.getAddress(name), function_pointer);
/*  982 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglGetProgramResourceLocationIndex(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetProgramResourceLocationIndex(int program, int programInterface, CharSequence name) {
/*  988 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  989 */     long function_pointer = caps.glGetProgramResourceLocationIndex;
/*  990 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  991 */     int __result = nglGetProgramResourceLocationIndex(program, programInterface, APIUtil.getBufferNT(caps, name), function_pointer);
/*  992 */     return __result;
/*      */   }
/*      */ 
/*      */   public static void glShaderStorageBlockBinding(int program, int storageBlockIndex, int storageBlockBinding) {
/*  996 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  997 */     long function_pointer = caps.glShaderStorageBlockBinding;
/*  998 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  999 */     nglShaderStorageBlockBinding(program, storageBlockIndex, storageBlockBinding, function_pointer);
/*      */   }
/*      */   static native void nglShaderStorageBlockBinding(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size) {
/* 1004 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1005 */     long function_pointer = caps.glTexBufferRange;
/* 1006 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1007 */     nglTexBufferRange(target, internalformat, buffer, offset, size, function_pointer);
/*      */   }
/*      */   static native void nglTexBufferRange(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
/* 1012 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1013 */     long function_pointer = caps.glTexStorage2DMultisample;
/* 1014 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1015 */     nglTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations, function_pointer);
/*      */   }
/*      */   static native void nglTexStorage2DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong);
/*      */ 
/*      */   public static void glTexStorage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
/* 1020 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1021 */     long function_pointer = caps.glTexStorage3DMultisample;
/* 1022 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1023 */     nglTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations, function_pointer);
/*      */   }
/*      */   static native void nglTexStorage3DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/*      */ 
/*      */   public static void glTextureView(int texture, int target, int origtexture, int internalformat, int minlevel, int numlevels, int minlayer, int numlayers) {
/* 1028 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1029 */     long function_pointer = caps.glTextureView;
/* 1030 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1031 */     nglTextureView(texture, target, origtexture, internalformat, minlevel, numlevels, minlayer, numlayers, function_pointer);
/*      */   }
/*      */   static native void nglTextureView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*      */ 
/*      */   public static void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride) {
/* 1036 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1037 */     long function_pointer = caps.glBindVertexBuffer;
/* 1038 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1039 */     nglBindVertexBuffer(bindingindex, buffer, offset, stride, function_pointer);
/*      */   }
/*      */   static native void nglBindVertexBuffer(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized, int relativeoffset) {
/* 1044 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1045 */     long function_pointer = caps.glVertexAttribFormat;
/* 1046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1047 */     nglVertexAttribFormat(attribindex, size, type, normalized, relativeoffset, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribFormat(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) {
/* 1052 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1053 */     long function_pointer = caps.glVertexAttribIFormat;
/* 1054 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1055 */     nglVertexAttribIFormat(attribindex, size, type, relativeoffset, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribIFormat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribLFormat(int attribindex, int size, int type, int relativeoffset) {
/* 1060 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1061 */     long function_pointer = caps.glVertexAttribLFormat;
/* 1062 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1063 */     nglVertexAttribLFormat(attribindex, size, type, relativeoffset, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribLFormat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribBinding(int attribindex, int bindingindex) {
/* 1068 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1069 */     long function_pointer = caps.glVertexAttribBinding;
/* 1070 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1071 */     nglVertexAttribBinding(attribindex, bindingindex, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribBinding(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glVertexBindingDivisor(int bindingindex, int divisor) {
/* 1076 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1077 */     long function_pointer = caps.glVertexBindingDivisor;
/* 1078 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1079 */     nglVertexBindingDivisor(bindingindex, divisor, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglVertexBindingDivisor(int paramInt1, int paramInt2, long paramLong);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL43
 * JD-Core Version:    0.6.2
 */