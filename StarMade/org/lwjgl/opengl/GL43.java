/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.IntBuffer;
/*    5:     */import java.nio.LongBuffer;
/*    6:     */import org.lwjgl.BufferChecks;
/*    7:     */import org.lwjgl.MemoryUtil;
/*    8:     */import org.lwjgl.PointerWrapper;
/*    9:     */
/*  233:     */public final class GL43
/*  234:     */{
/*  235:     */  public static final int GL_NUM_SHADING_LANGUAGE_VERSIONS = 33513;
/*  236:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_LONG = 34638;
/*  237:     */  public static final int GL_COMPRESSED_RGB8_ETC2 = 37492;
/*  238:     */  public static final int GL_COMPRESSED_SRGB8_ETC2 = 37493;
/*  239:     */  public static final int GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37494;
/*  240:     */  public static final int GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37495;
/*  241:     */  public static final int GL_COMPRESSED_RGBA8_ETC2_EAC = 37496;
/*  242:     */  public static final int GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 37497;
/*  243:     */  public static final int GL_COMPRESSED_R11_EAC = 37488;
/*  244:     */  public static final int GL_COMPRESSED_SIGNED_R11_EAC = 37489;
/*  245:     */  public static final int GL_COMPRESSED_RG11_EAC = 37490;
/*  246:     */  public static final int GL_COMPRESSED_SIGNED_RG11_EAC = 37491;
/*  247:     */  public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX = 36201;
/*  248:     */  public static final int GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 36202;
/*  249:     */  public static final int GL_MAX_ELEMENT_INDEX = 36203;
/*  250:     */  public static final int GL_COMPUTE_SHADER = 37305;
/*  251:     */  public static final int GL_MAX_COMPUTE_UNIFORM_BLOCKS = 37307;
/*  252:     */  public static final int GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 37308;
/*  253:     */  public static final int GL_MAX_COMPUTE_IMAGE_UNIFORMS = 37309;
/*  254:     */  public static final int GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 33378;
/*  255:     */  public static final int GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 33379;
/*  256:     */  public static final int GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 33380;
/*  257:     */  public static final int GL_MAX_COMPUTE_ATOMIC_COUNTERS = 33381;
/*  258:     */  public static final int GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 33382;
/*  259:     */  public static final int GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 37099;
/*  260:     */  public static final int GL_MAX_COMPUTE_WORK_GROUP_COUNT = 37310;
/*  261:     */  public static final int GL_MAX_COMPUTE_WORK_GROUP_SIZE = 37311;
/*  262:     */  public static final int GL_COMPUTE_WORK_GROUP_SIZE = 33383;
/*  263:     */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 37100;
/*  264:     */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 37101;
/*  265:     */  public static final int GL_DISPATCH_INDIRECT_BUFFER = 37102;
/*  266:     */  public static final int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 37103;
/*  267:     */  public static final int GL_COMPUTE_SHADER_BIT = 32;
/*  268:     */  public static final int GL_DEBUG_OUTPUT = 37600;
/*  269:     */  public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS = 33346;
/*  270:     */  public static final int GL_CONTEXT_FLAG_DEBUG_BIT = 2;
/*  271:     */  public static final int GL_MAX_DEBUG_MESSAGE_LENGTH = 37187;
/*  272:     */  public static final int GL_MAX_DEBUG_LOGGED_MESSAGES = 37188;
/*  273:     */  public static final int GL_DEBUG_LOGGED_MESSAGES = 37189;
/*  274:     */  public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 33347;
/*  275:     */  public static final int GL_MAX_DEBUG_GROUP_STACK_DEPTH = 33388;
/*  276:     */  public static final int GL_DEBUG_GROUP_STACK_DEPTH = 33389;
/*  277:     */  public static final int GL_MAX_LABEL_LENGTH = 33512;
/*  278:     */  public static final int GL_DEBUG_CALLBACK_FUNCTION = 33348;
/*  279:     */  public static final int GL_DEBUG_CALLBACK_USER_PARAM = 33349;
/*  280:     */  public static final int GL_DEBUG_SOURCE_API = 33350;
/*  281:     */  public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
/*  282:     */  public static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
/*  283:     */  public static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
/*  284:     */  public static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
/*  285:     */  public static final int GL_DEBUG_SOURCE_OTHER = 33355;
/*  286:     */  public static final int GL_DEBUG_TYPE_ERROR = 33356;
/*  287:     */  public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
/*  288:     */  public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
/*  289:     */  public static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
/*  290:     */  public static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
/*  291:     */  public static final int GL_DEBUG_TYPE_OTHER = 33361;
/*  292:     */  public static final int GL_DEBUG_TYPE_MARKER = 33384;
/*  293:     */  public static final int GL_DEBUG_TYPE_PUSH_GROUP = 33385;
/*  294:     */  public static final int GL_DEBUG_TYPE_POP_GROUP = 33386;
/*  295:     */  public static final int GL_DEBUG_SEVERITY_HIGH = 37190;
/*  296:     */  public static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
/*  297:     */  public static final int GL_DEBUG_SEVERITY_LOW = 37192;
/*  298:     */  public static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
/*  299:     */  public static final int GL_STACK_UNDERFLOW = 1284;
/*  300:     */  public static final int GL_STACK_OVERFLOW = 1283;
/*  301:     */  public static final int GL_BUFFER = 33504;
/*  302:     */  public static final int GL_SHADER = 33505;
/*  303:     */  public static final int GL_PROGRAM = 33506;
/*  304:     */  public static final int GL_QUERY = 33507;
/*  305:     */  public static final int GL_PROGRAM_PIPELINE = 33508;
/*  306:     */  public static final int GL_SAMPLER = 33510;
/*  307:     */  public static final int GL_DISPLAY_LIST = 33511;
/*  308:     */  public static final int GL_MAX_UNIFORM_LOCATIONS = 33390;
/*  309:     */  public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 37648;
/*  310:     */  public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 37649;
/*  311:     */  public static final int GL_FRAMEBUFFER_DEFAULT_LAYERS = 37650;
/*  312:     */  public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 37651;
/*  313:     */  public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;
/*  314:     */  public static final int GL_MAX_FRAMEBUFFER_WIDTH = 37653;
/*  315:     */  public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 37654;
/*  316:     */  public static final int GL_MAX_FRAMEBUFFER_LAYERS = 37655;
/*  317:     */  public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 37656;
/*  318:     */  public static final int GL_TEXTURE_1D = 3552;
/*  319:     */  public static final int GL_TEXTURE_1D_ARRAY = 35864;
/*  320:     */  public static final int GL_TEXTURE_2D = 3553;
/*  321:     */  public static final int GL_TEXTURE_2D_ARRAY = 35866;
/*  322:     */  public static final int GL_TEXTURE_3D = 32879;
/*  323:     */  public static final int GL_TEXTURE_CUBE_MAP = 34067;
/*  324:     */  public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;
/*  325:     */  public static final int GL_TEXTURE_RECTANGLE = 34037;
/*  326:     */  public static final int GL_TEXTURE_BUFFER = 35882;
/*  327:     */  public static final int GL_RENDERBUFFER = 36161;
/*  328:     */  public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
/*  329:     */  public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
/*  330:     */  public static final int GL_SAMPLES = 32937;
/*  331:     */  public static final int GL_NUM_SAMPLE_COUNTS = 37760;
/*  332:     */  public static final int GL_INTERNALFORMAT_SUPPORTED = 33391;
/*  333:     */  public static final int GL_INTERNALFORMAT_PREFERRED = 33392;
/*  334:     */  public static final int GL_INTERNALFORMAT_RED_SIZE = 33393;
/*  335:     */  public static final int GL_INTERNALFORMAT_GREEN_SIZE = 33394;
/*  336:     */  public static final int GL_INTERNALFORMAT_BLUE_SIZE = 33395;
/*  337:     */  public static final int GL_INTERNALFORMAT_ALPHA_SIZE = 33396;
/*  338:     */  public static final int GL_INTERNALFORMAT_DEPTH_SIZE = 33397;
/*  339:     */  public static final int GL_INTERNALFORMAT_STENCIL_SIZE = 33398;
/*  340:     */  public static final int GL_INTERNALFORMAT_SHARED_SIZE = 33399;
/*  341:     */  public static final int GL_INTERNALFORMAT_RED_TYPE = 33400;
/*  342:     */  public static final int GL_INTERNALFORMAT_GREEN_TYPE = 33401;
/*  343:     */  public static final int GL_INTERNALFORMAT_BLUE_TYPE = 33402;
/*  344:     */  public static final int GL_INTERNALFORMAT_ALPHA_TYPE = 33403;
/*  345:     */  public static final int GL_INTERNALFORMAT_DEPTH_TYPE = 33404;
/*  346:     */  public static final int GL_INTERNALFORMAT_STENCIL_TYPE = 33405;
/*  347:     */  public static final int GL_MAX_WIDTH = 33406;
/*  348:     */  public static final int GL_MAX_HEIGHT = 33407;
/*  349:     */  public static final int GL_MAX_DEPTH = 33408;
/*  350:     */  public static final int GL_MAX_LAYERS = 33409;
/*  351:     */  public static final int GL_MAX_COMBINED_DIMENSIONS = 33410;
/*  352:     */  public static final int GL_COLOR_COMPONENTS = 33411;
/*  353:     */  public static final int GL_DEPTH_COMPONENTS = 33412;
/*  354:     */  public static final int GL_STENCIL_COMPONENTS = 33413;
/*  355:     */  public static final int GL_COLOR_RENDERABLE = 33414;
/*  356:     */  public static final int GL_DEPTH_RENDERABLE = 33415;
/*  357:     */  public static final int GL_STENCIL_RENDERABLE = 33416;
/*  358:     */  public static final int GL_FRAMEBUFFER_RENDERABLE = 33417;
/*  359:     */  public static final int GL_FRAMEBUFFER_RENDERABLE_LAYERED = 33418;
/*  360:     */  public static final int GL_FRAMEBUFFER_BLEND = 33419;
/*  361:     */  public static final int GL_READ_PIXELS = 33420;
/*  362:     */  public static final int GL_READ_PIXELS_FORMAT = 33421;
/*  363:     */  public static final int GL_READ_PIXELS_TYPE = 33422;
/*  364:     */  public static final int GL_TEXTURE_IMAGE_FORMAT = 33423;
/*  365:     */  public static final int GL_TEXTURE_IMAGE_TYPE = 33424;
/*  366:     */  public static final int GL_GET_TEXTURE_IMAGE_FORMAT = 33425;
/*  367:     */  public static final int GL_GET_TEXTURE_IMAGE_TYPE = 33426;
/*  368:     */  public static final int GL_MIPMAP = 33427;
/*  369:     */  public static final int GL_MANUAL_GENERATE_MIPMAP = 33428;
/*  370:     */  public static final int GL_AUTO_GENERATE_MIPMAP = 33429;
/*  371:     */  public static final int GL_COLOR_ENCODING = 33430;
/*  372:     */  public static final int GL_SRGB_READ = 33431;
/*  373:     */  public static final int GL_SRGB_WRITE = 33432;
/*  374:     */  public static final int GL_SRGB_DECODE_ARB = 33433;
/*  375:     */  public static final int GL_FILTER = 33434;
/*  376:     */  public static final int GL_VERTEX_TEXTURE = 33435;
/*  377:     */  public static final int GL_TESS_CONTROL_TEXTURE = 33436;
/*  378:     */  public static final int GL_TESS_EVALUATION_TEXTURE = 33437;
/*  379:     */  public static final int GL_GEOMETRY_TEXTURE = 33438;
/*  380:     */  public static final int GL_FRAGMENT_TEXTURE = 33439;
/*  381:     */  public static final int GL_COMPUTE_TEXTURE = 33440;
/*  382:     */  public static final int GL_TEXTURE_SHADOW = 33441;
/*  383:     */  public static final int GL_TEXTURE_GATHER = 33442;
/*  384:     */  public static final int GL_TEXTURE_GATHER_SHADOW = 33443;
/*  385:     */  public static final int GL_SHADER_IMAGE_LOAD = 33444;
/*  386:     */  public static final int GL_SHADER_IMAGE_STORE = 33445;
/*  387:     */  public static final int GL_SHADER_IMAGE_ATOMIC = 33446;
/*  388:     */  public static final int GL_IMAGE_TEXEL_SIZE = 33447;
/*  389:     */  public static final int GL_IMAGE_COMPATIBILITY_CLASS = 33448;
/*  390:     */  public static final int GL_IMAGE_PIXEL_FORMAT = 33449;
/*  391:     */  public static final int GL_IMAGE_PIXEL_TYPE = 33450;
/*  392:     */  public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;
/*  393:     */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST = 33452;
/*  394:     */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST = 33453;
/*  395:     */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE = 33454;
/*  396:     */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 33455;
/*  397:     */  public static final int GL_TEXTURE_COMPRESSED = 34465;
/*  398:     */  public static final int GL_TEXTURE_COMPRESSED_BLOCK_WIDTH = 33457;
/*  399:     */  public static final int GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT = 33458;
/*  400:     */  public static final int GL_TEXTURE_COMPRESSED_BLOCK_SIZE = 33459;
/*  401:     */  public static final int GL_CLEAR_BUFFER = 33460;
/*  402:     */  public static final int GL_TEXTURE_VIEW = 33461;
/*  403:     */  public static final int GL_VIEW_COMPATIBILITY_CLASS = 33462;
/*  404:     */  public static final int GL_FULL_SUPPORT = 33463;
/*  405:     */  public static final int GL_CAVEAT_SUPPORT = 33464;
/*  406:     */  public static final int GL_IMAGE_CLASS_4_X_32 = 33465;
/*  407:     */  public static final int GL_IMAGE_CLASS_2_X_32 = 33466;
/*  408:     */  public static final int GL_IMAGE_CLASS_1_X_32 = 33467;
/*  409:     */  public static final int GL_IMAGE_CLASS_4_X_16 = 33468;
/*  410:     */  public static final int GL_IMAGE_CLASS_2_X_16 = 33469;
/*  411:     */  public static final int GL_IMAGE_CLASS_1_X_16 = 33470;
/*  412:     */  public static final int GL_IMAGE_CLASS_4_X_8 = 33471;
/*  413:     */  public static final int GL_IMAGE_CLASS_2_X_8 = 33472;
/*  414:     */  public static final int GL_IMAGE_CLASS_1_X_8 = 33473;
/*  415:     */  public static final int GL_IMAGE_CLASS_11_11_10 = 33474;
/*  416:     */  public static final int GL_IMAGE_CLASS_10_10_10_2 = 33475;
/*  417:     */  public static final int GL_VIEW_CLASS_128_BITS = 33476;
/*  418:     */  public static final int GL_VIEW_CLASS_96_BITS = 33477;
/*  419:     */  public static final int GL_VIEW_CLASS_64_BITS = 33478;
/*  420:     */  public static final int GL_VIEW_CLASS_48_BITS = 33479;
/*  421:     */  public static final int GL_VIEW_CLASS_32_BITS = 33480;
/*  422:     */  public static final int GL_VIEW_CLASS_24_BITS = 33481;
/*  423:     */  public static final int GL_VIEW_CLASS_16_BITS = 33482;
/*  424:     */  public static final int GL_VIEW_CLASS_8_BITS = 33483;
/*  425:     */  public static final int GL_VIEW_CLASS_S3TC_DXT1_RGB = 33484;
/*  426:     */  public static final int GL_VIEW_CLASS_S3TC_DXT1_RGBA = 33485;
/*  427:     */  public static final int GL_VIEW_CLASS_S3TC_DXT3_RGBA = 33486;
/*  428:     */  public static final int GL_VIEW_CLASS_S3TC_DXT5_RGBA = 33487;
/*  429:     */  public static final int GL_VIEW_CLASS_RGTC1_RED = 33488;
/*  430:     */  public static final int GL_VIEW_CLASS_RGTC2_RG = 33489;
/*  431:     */  public static final int GL_VIEW_CLASS_BPTC_UNORM = 33490;
/*  432:     */  public static final int GL_VIEW_CLASS_BPTC_FLOAT = 33491;
/*  433:     */  public static final int GL_UNIFORM = 37601;
/*  434:     */  public static final int GL_UNIFORM_BLOCK = 37602;
/*  435:     */  public static final int GL_PROGRAM_INPUT = 37603;
/*  436:     */  public static final int GL_PROGRAM_OUTPUT = 37604;
/*  437:     */  public static final int GL_BUFFER_VARIABLE = 37605;
/*  438:     */  public static final int GL_SHADER_STORAGE_BLOCK = 37606;
/*  439:     */  public static final int GL_VERTEX_SUBROUTINE = 37608;
/*  440:     */  public static final int GL_TESS_CONTROL_SUBROUTINE = 37609;
/*  441:     */  public static final int GL_TESS_EVALUATION_SUBROUTINE = 37610;
/*  442:     */  public static final int GL_GEOMETRY_SUBROUTINE = 37611;
/*  443:     */  public static final int GL_FRAGMENT_SUBROUTINE = 37612;
/*  444:     */  public static final int GL_COMPUTE_SUBROUTINE = 37613;
/*  445:     */  public static final int GL_VERTEX_SUBROUTINE_UNIFORM = 37614;
/*  446:     */  public static final int GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 37615;
/*  447:     */  public static final int GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 37616;
/*  448:     */  public static final int GL_GEOMETRY_SUBROUTINE_UNIFORM = 37617;
/*  449:     */  public static final int GL_FRAGMENT_SUBROUTINE_UNIFORM = 37618;
/*  450:     */  public static final int GL_COMPUTE_SUBROUTINE_UNIFORM = 37619;
/*  451:     */  public static final int GL_TRANSFORM_FEEDBACK_VARYING = 37620;
/*  452:     */  public static final int GL_ACTIVE_RESOURCES = 37621;
/*  453:     */  public static final int GL_MAX_NAME_LENGTH = 37622;
/*  454:     */  public static final int GL_MAX_NUM_ACTIVE_VARIABLES = 37623;
/*  455:     */  public static final int GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 37624;
/*  456:     */  public static final int GL_NAME_LENGTH = 37625;
/*  457:     */  public static final int GL_TYPE = 37626;
/*  458:     */  public static final int GL_ARRAY_SIZE = 37627;
/*  459:     */  public static final int GL_OFFSET = 37628;
/*  460:     */  public static final int GL_BLOCK_INDEX = 37629;
/*  461:     */  public static final int GL_ARRAY_STRIDE = 37630;
/*  462:     */  public static final int GL_MATRIX_STRIDE = 37631;
/*  463:     */  public static final int GL_IS_ROW_MAJOR = 37632;
/*  464:     */  public static final int GL_ATOMIC_COUNTER_BUFFER_INDEX = 37633;
/*  465:     */  public static final int GL_BUFFER_BINDING = 37634;
/*  466:     */  public static final int GL_BUFFER_DATA_SIZE = 37635;
/*  467:     */  public static final int GL_NUM_ACTIVE_VARIABLES = 37636;
/*  468:     */  public static final int GL_ACTIVE_VARIABLES = 37637;
/*  469:     */  public static final int GL_REFERENCED_BY_VERTEX_SHADER = 37638;
/*  470:     */  public static final int GL_REFERENCED_BY_TESS_CONTROL_SHADER = 37639;
/*  471:     */  public static final int GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 37640;
/*  472:     */  public static final int GL_REFERENCED_BY_GEOMETRY_SHADER = 37641;
/*  473:     */  public static final int GL_REFERENCED_BY_FRAGMENT_SHADER = 37642;
/*  474:     */  public static final int GL_REFERENCED_BY_COMPUTE_SHADER = 37643;
/*  475:     */  public static final int GL_TOP_LEVEL_ARRAY_SIZE = 37644;
/*  476:     */  public static final int GL_TOP_LEVEL_ARRAY_STRIDE = 37645;
/*  477:     */  public static final int GL_LOCATION = 37646;
/*  478:     */  public static final int GL_LOCATION_INDEX = 37647;
/*  479:     */  public static final int GL_IS_PER_PATCH = 37607;
/*  480:     */  public static final int GL_SHADER_STORAGE_BUFFER = 37074;
/*  481:     */  public static final int GL_SHADER_STORAGE_BUFFER_BINDING = 37075;
/*  482:     */  public static final int GL_SHADER_STORAGE_BUFFER_START = 37076;
/*  483:     */  public static final int GL_SHADER_STORAGE_BUFFER_SIZE = 37077;
/*  484:     */  public static final int GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS = 37078;
/*  485:     */  public static final int GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 37079;
/*  486:     */  public static final int GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 37080;
/*  487:     */  public static final int GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 37081;
/*  488:     */  public static final int GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 37082;
/*  489:     */  public static final int GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 37083;
/*  490:     */  public static final int GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS = 37084;
/*  491:     */  public static final int GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS = 37085;
/*  492:     */  public static final int GL_MAX_SHADER_STORAGE_BLOCK_SIZE = 37086;
/*  493:     */  public static final int GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 37087;
/*  494:     */  public static final int GL_SHADER_STORAGE_BARRIER_BIT = 8192;
/*  495:     */  public static final int GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 36665;
/*  496:     */  public static final int GL_DEPTH_STENCIL_TEXTURE_MODE = 37098;
/*  497:     */  public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
/*  498:     */  public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
/*  499:     */  public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;
/*  500:     */  public static final int GL_TEXTURE_VIEW_MIN_LEVEL = 33499;
/*  501:     */  public static final int GL_TEXTURE_VIEW_NUM_LEVELS = 33500;
/*  502:     */  public static final int GL_TEXTURE_VIEW_MIN_LAYER = 33501;
/*  503:     */  public static final int GL_TEXTURE_VIEW_NUM_LAYERS = 33502;
/*  504:     */  public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 33503;
/*  505:     */  public static final int GL_VERTEX_ATTRIB_BINDING = 33492;
/*  506:     */  public static final int GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 33493;
/*  507:     */  public static final int GL_VERTEX_BINDING_DIVISOR = 33494;
/*  508:     */  public static final int GL_VERTEX_BINDING_OFFSET = 33495;
/*  509:     */  public static final int GL_VERTEX_BINDING_STRIDE = 33496;
/*  510:     */  public static final int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 33497;
/*  511:     */  public static final int GL_MAX_VERTEX_ATTRIB_BINDINGS = 33498;
/*  512:     */  
/*  513:     */  public static void glClearBufferData(int target, int internalformat, int format, int type, ByteBuffer data)
/*  514:     */  {
/*  515: 515 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  516: 516 */    long function_pointer = caps.glClearBufferData;
/*  517: 517 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  518: 518 */    BufferChecks.checkBuffer(data, 1);
/*  519: 519 */    nglClearBufferData(target, internalformat, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  520:     */  }
/*  521:     */  
/*  522:     */  static native void nglClearBufferData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  523:     */  
/*  524: 524 */  public static void glClearBufferSubData(int target, int internalformat, long offset, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  525: 525 */    long function_pointer = caps.glClearBufferSubData;
/*  526: 526 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  527: 527 */    BufferChecks.checkDirect(data);
/*  528: 528 */    nglClearBufferSubData(target, internalformat, offset, data.remaining(), format, type, MemoryUtil.getAddress(data), function_pointer);
/*  529:     */  }
/*  530:     */  
/*  531:     */  static native void nglClearBufferSubData(int paramInt1, int paramInt2, long paramLong1, long paramLong2, int paramInt3, int paramInt4, long paramLong3, long paramLong4);
/*  532:     */  
/*  533: 533 */  public static void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  534: 534 */    long function_pointer = caps.glDispatchCompute;
/*  535: 535 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  536: 536 */    nglDispatchCompute(num_groups_x, num_groups_y, num_groups_z, function_pointer);
/*  537:     */  }
/*  538:     */  
/*  539:     */  static native void nglDispatchCompute(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  540:     */  
/*  541: 541 */  public static void glDispatchComputeIndirect(long indirect) { ContextCapabilities caps = GLContext.getCapabilities();
/*  542: 542 */    long function_pointer = caps.glDispatchComputeIndirect;
/*  543: 543 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  544: 544 */    nglDispatchComputeIndirect(indirect, function_pointer);
/*  545:     */  }
/*  546:     */  
/*  547:     */  static native void nglDispatchComputeIndirect(long paramLong1, long paramLong2);
/*  548:     */  
/*  549: 549 */  public static void glCopyImageSubData(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth) { ContextCapabilities caps = GLContext.getCapabilities();
/*  550: 550 */    long function_pointer = caps.glCopyImageSubData;
/*  551: 551 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  552: 552 */    nglCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth, srcHeight, srcDepth, function_pointer);
/*  553:     */  }
/*  554:     */  
/*  555:     */  static native void nglCopyImageSubData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, long paramLong);
/*  556:     */  
/*  557: 557 */  public static void glDebugMessageControl(int source, int type, int severity, IntBuffer ids, boolean enabled) { ContextCapabilities caps = GLContext.getCapabilities();
/*  558: 558 */    long function_pointer = caps.glDebugMessageControl;
/*  559: 559 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  560: 560 */    if (ids != null)
/*  561: 561 */      BufferChecks.checkDirect(ids);
/*  562: 562 */    nglDebugMessageControl(source, type, severity, ids == null ? 0 : ids.remaining(), MemoryUtil.getAddressSafe(ids), enabled, function_pointer);
/*  563:     */  }
/*  564:     */  
/*  565:     */  static native void nglDebugMessageControl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, boolean paramBoolean, long paramLong2);
/*  566:     */  
/*  567: 567 */  public static void glDebugMessageInsert(int source, int type, int id, int severity, ByteBuffer buf) { ContextCapabilities caps = GLContext.getCapabilities();
/*  568: 568 */    long function_pointer = caps.glDebugMessageInsert;
/*  569: 569 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  570: 570 */    BufferChecks.checkDirect(buf);
/*  571: 571 */    nglDebugMessageInsert(source, type, id, severity, buf.remaining(), MemoryUtil.getAddress(buf), function_pointer);
/*  572:     */  }
/*  573:     */  
/*  574:     */  static native void nglDebugMessageInsert(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  575:     */  
/*  576:     */  public static void glDebugMessageInsert(int source, int type, int id, int severity, CharSequence buf) {
/*  577: 577 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  578: 578 */    long function_pointer = caps.glDebugMessageInsert;
/*  579: 579 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  580: 580 */    nglDebugMessageInsert(source, type, id, severity, buf.length(), APIUtil.getBuffer(caps, buf), function_pointer);
/*  581:     */  }
/*  582:     */  
/*  589:     */  public static void glDebugMessageCallback(KHRDebugCallback callback)
/*  590:     */  {
/*  591: 591 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  592: 592 */    long function_pointer = caps.glDebugMessageCallback;
/*  593: 593 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  594: 594 */    long userParam = callback == null ? 0L : CallbackUtil.createGlobalRef(callback.getHandler());
/*  595: 595 */    CallbackUtil.registerContextCallbackKHR(userParam);
/*  596: 596 */    nglDebugMessageCallback(callback == null ? 0L : callback.getPointer(), userParam, function_pointer);
/*  597:     */  }
/*  598:     */  
/*  599:     */  static native void nglDebugMessageCallback(long paramLong1, long paramLong2, long paramLong3);
/*  600:     */  
/*  601: 601 */  public static int glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) { ContextCapabilities caps = GLContext.getCapabilities();
/*  602: 602 */    long function_pointer = caps.glGetDebugMessageLog;
/*  603: 603 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  604: 604 */    if (sources != null)
/*  605: 605 */      BufferChecks.checkBuffer(sources, count);
/*  606: 606 */    if (types != null)
/*  607: 607 */      BufferChecks.checkBuffer(types, count);
/*  608: 608 */    if (ids != null)
/*  609: 609 */      BufferChecks.checkBuffer(ids, count);
/*  610: 610 */    if (severities != null)
/*  611: 611 */      BufferChecks.checkBuffer(severities, count);
/*  612: 612 */    if (lengths != null)
/*  613: 613 */      BufferChecks.checkBuffer(lengths, count);
/*  614: 614 */    if (messageLog != null)
/*  615: 615 */      BufferChecks.checkDirect(messageLog);
/*  616: 616 */    int __result = nglGetDebugMessageLog(count, messageLog == null ? 0 : messageLog.remaining(), MemoryUtil.getAddressSafe(sources), MemoryUtil.getAddressSafe(types), MemoryUtil.getAddressSafe(ids), MemoryUtil.getAddressSafe(severities), MemoryUtil.getAddressSafe(lengths), MemoryUtil.getAddressSafe(messageLog), function_pointer);
/*  617: 617 */    return __result;
/*  618:     */  }
/*  619:     */  
/*  620:     */  static native int nglGetDebugMessageLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/*  621:     */  
/*  622: 622 */  public static void glPushDebugGroup(int source, int id, ByteBuffer message) { ContextCapabilities caps = GLContext.getCapabilities();
/*  623: 623 */    long function_pointer = caps.glPushDebugGroup;
/*  624: 624 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  625: 625 */    BufferChecks.checkDirect(message);
/*  626: 626 */    nglPushDebugGroup(source, id, message.remaining(), MemoryUtil.getAddress(message), function_pointer);
/*  627:     */  }
/*  628:     */  
/*  629:     */  static native void nglPushDebugGroup(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  630:     */  
/*  631:     */  public static void glPushDebugGroup(int source, int id, CharSequence message) {
/*  632: 632 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  633: 633 */    long function_pointer = caps.glPushDebugGroup;
/*  634: 634 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  635: 635 */    nglPushDebugGroup(source, id, message.length(), APIUtil.getBuffer(caps, message), function_pointer);
/*  636:     */  }
/*  637:     */  
/*  638:     */  public static void glPopDebugGroup() {
/*  639: 639 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  640: 640 */    long function_pointer = caps.glPopDebugGroup;
/*  641: 641 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  642: 642 */    nglPopDebugGroup(function_pointer);
/*  643:     */  }
/*  644:     */  
/*  645:     */  static native void nglPopDebugGroup(long paramLong);
/*  646:     */  
/*  647: 647 */  public static void glObjectLabel(int identifier, int name, ByteBuffer label) { ContextCapabilities caps = GLContext.getCapabilities();
/*  648: 648 */    long function_pointer = caps.glObjectLabel;
/*  649: 649 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  650: 650 */    if (label != null)
/*  651: 651 */      BufferChecks.checkDirect(label);
/*  652: 652 */    nglObjectLabel(identifier, name, label == null ? 0 : label.remaining(), MemoryUtil.getAddressSafe(label), function_pointer);
/*  653:     */  }
/*  654:     */  
/*  655:     */  static native void nglObjectLabel(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  656:     */  
/*  657:     */  public static void glObjectLabel(int identifier, int name, CharSequence label) {
/*  658: 658 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  659: 659 */    long function_pointer = caps.glObjectLabel;
/*  660: 660 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  661: 661 */    nglObjectLabel(identifier, name, label.length(), APIUtil.getBuffer(caps, label), function_pointer);
/*  662:     */  }
/*  663:     */  
/*  664:     */  public static void glGetObjectLabel(int identifier, int name, IntBuffer length, ByteBuffer label) {
/*  665: 665 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  666: 666 */    long function_pointer = caps.glGetObjectLabel;
/*  667: 667 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  668: 668 */    if (length != null)
/*  669: 669 */      BufferChecks.checkBuffer(length, 1);
/*  670: 670 */    BufferChecks.checkDirect(label);
/*  671: 671 */    nglGetObjectLabel(identifier, name, label.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(label), function_pointer);
/*  672:     */  }
/*  673:     */  
/*  674:     */  static native void nglGetObjectLabel(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*  675:     */  
/*  676:     */  public static String glGetObjectLabel(int identifier, int name, int bufSize) {
/*  677: 677 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  678: 678 */    long function_pointer = caps.glGetObjectLabel;
/*  679: 679 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  680: 680 */    IntBuffer label_length = APIUtil.getLengths(caps);
/*  681: 681 */    ByteBuffer label = APIUtil.getBufferByte(caps, bufSize);
/*  682: 682 */    nglGetObjectLabel(identifier, name, bufSize, MemoryUtil.getAddress0(label_length), MemoryUtil.getAddress(label), function_pointer);
/*  683: 683 */    label.limit(label_length.get(0));
/*  684: 684 */    return APIUtil.getString(caps, label);
/*  685:     */  }
/*  686:     */  
/*  687:     */  public static void glObjectPtrLabel(PointerWrapper ptr, ByteBuffer label) {
/*  688: 688 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  689: 689 */    long function_pointer = caps.glObjectPtrLabel;
/*  690: 690 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  691: 691 */    if (label != null)
/*  692: 692 */      BufferChecks.checkDirect(label);
/*  693: 693 */    nglObjectPtrLabel(ptr.getPointer(), label == null ? 0 : label.remaining(), MemoryUtil.getAddressSafe(label), function_pointer);
/*  694:     */  }
/*  695:     */  
/*  696:     */  static native void nglObjectPtrLabel(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*  697:     */  
/*  698:     */  public static void glObjectPtrLabel(PointerWrapper ptr, CharSequence label) {
/*  699: 699 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  700: 700 */    long function_pointer = caps.glObjectPtrLabel;
/*  701: 701 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  702: 702 */    nglObjectPtrLabel(ptr.getPointer(), label.length(), APIUtil.getBuffer(caps, label), function_pointer);
/*  703:     */  }
/*  704:     */  
/*  705:     */  public static void glGetObjectPtrLabel(PointerWrapper ptr, IntBuffer length, ByteBuffer label) {
/*  706: 706 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  707: 707 */    long function_pointer = caps.glGetObjectPtrLabel;
/*  708: 708 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  709: 709 */    if (length != null)
/*  710: 710 */      BufferChecks.checkBuffer(length, 1);
/*  711: 711 */    BufferChecks.checkDirect(label);
/*  712: 712 */    nglGetObjectPtrLabel(ptr.getPointer(), label.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(label), function_pointer);
/*  713:     */  }
/*  714:     */  
/*  715:     */  static native void nglGetObjectPtrLabel(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*  716:     */  
/*  717:     */  public static String glGetObjectPtrLabel(PointerWrapper ptr, int bufSize) {
/*  718: 718 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  719: 719 */    long function_pointer = caps.glGetObjectPtrLabel;
/*  720: 720 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  721: 721 */    IntBuffer label_length = APIUtil.getLengths(caps);
/*  722: 722 */    ByteBuffer label = APIUtil.getBufferByte(caps, bufSize);
/*  723: 723 */    nglGetObjectPtrLabel(ptr.getPointer(), bufSize, MemoryUtil.getAddress0(label_length), MemoryUtil.getAddress(label), function_pointer);
/*  724: 724 */    label.limit(label_length.get(0));
/*  725: 725 */    return APIUtil.getString(caps, label);
/*  726:     */  }
/*  727:     */  
/*  728:     */  public static void glFramebufferParameteri(int target, int pname, int param) {
/*  729: 729 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  730: 730 */    long function_pointer = caps.glFramebufferParameteri;
/*  731: 731 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  732: 732 */    nglFramebufferParameteri(target, pname, param, function_pointer);
/*  733:     */  }
/*  734:     */  
/*  735:     */  static native void nglFramebufferParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  736:     */  
/*  737: 737 */  public static void glGetFramebufferParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  738: 738 */    long function_pointer = caps.glGetFramebufferParameteriv;
/*  739: 739 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  740: 740 */    BufferChecks.checkBuffer(params, 1);
/*  741: 741 */    nglGetFramebufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  742:     */  }
/*  743:     */  
/*  744:     */  static native void nglGetFramebufferParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  745:     */  
/*  746:     */  public static int glGetFramebufferParameteri(int target, int pname) {
/*  747: 747 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  748: 748 */    long function_pointer = caps.glGetFramebufferParameteriv;
/*  749: 749 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  750: 750 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  751: 751 */    nglGetFramebufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  752: 752 */    return params.get(0);
/*  753:     */  }
/*  754:     */  
/*  755:     */  public static void glGetInternalformat(int target, int internalformat, int pname, LongBuffer params) {
/*  756: 756 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  757: 757 */    long function_pointer = caps.glGetInternalformati64v;
/*  758: 758 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  759: 759 */    BufferChecks.checkDirect(params);
/*  760: 760 */    nglGetInternalformati64v(target, internalformat, pname, params.remaining(), MemoryUtil.getAddress(params), function_pointer);
/*  761:     */  }
/*  762:     */  
/*  763:     */  static native void nglGetInternalformati64v(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  764:     */  
/*  765:     */  public static long glGetInternalformati64(int target, int internalformat, int pname) {
/*  766: 766 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  767: 767 */    long function_pointer = caps.glGetInternalformati64v;
/*  768: 768 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  769: 769 */    LongBuffer params = APIUtil.getBufferLong(caps);
/*  770: 770 */    nglGetInternalformati64v(target, internalformat, pname, 1, MemoryUtil.getAddress(params), function_pointer);
/*  771: 771 */    return params.get(0);
/*  772:     */  }
/*  773:     */  
/*  774:     */  public static void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth) {
/*  775: 775 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  776: 776 */    long function_pointer = caps.glInvalidateTexSubImage;
/*  777: 777 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  778: 778 */    nglInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, function_pointer);
/*  779:     */  }
/*  780:     */  
/*  781:     */  static native void nglInvalidateTexSubImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*  782:     */  
/*  783: 783 */  public static void glInvalidateTexImage(int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/*  784: 784 */    long function_pointer = caps.glInvalidateTexImage;
/*  785: 785 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  786: 786 */    nglInvalidateTexImage(texture, level, function_pointer);
/*  787:     */  }
/*  788:     */  
/*  789:     */  static native void nglInvalidateTexImage(int paramInt1, int paramInt2, long paramLong);
/*  790:     */  
/*  791: 791 */  public static void glInvalidateBufferSubData(int buffer, long offset, long length) { ContextCapabilities caps = GLContext.getCapabilities();
/*  792: 792 */    long function_pointer = caps.glInvalidateBufferSubData;
/*  793: 793 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  794: 794 */    nglInvalidateBufferSubData(buffer, offset, length, function_pointer);
/*  795:     */  }
/*  796:     */  
/*  797:     */  static native void nglInvalidateBufferSubData(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*  798:     */  
/*  799: 799 */  public static void glInvalidateBufferData(int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  800: 800 */    long function_pointer = caps.glInvalidateBufferData;
/*  801: 801 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  802: 802 */    nglInvalidateBufferData(buffer, function_pointer);
/*  803:     */  }
/*  804:     */  
/*  805:     */  static native void nglInvalidateBufferData(int paramInt, long paramLong);
/*  806:     */  
/*  807: 807 */  public static void glInvalidateFramebuffer(int target, IntBuffer attachments) { ContextCapabilities caps = GLContext.getCapabilities();
/*  808: 808 */    long function_pointer = caps.glInvalidateFramebuffer;
/*  809: 809 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  810: 810 */    BufferChecks.checkDirect(attachments);
/*  811: 811 */    nglInvalidateFramebuffer(target, attachments.remaining(), MemoryUtil.getAddress(attachments), function_pointer);
/*  812:     */  }
/*  813:     */  
/*  814:     */  static native void nglInvalidateFramebuffer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  815:     */  
/*  816: 816 */  public static void glInvalidateSubFramebuffer(int target, IntBuffer attachments, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/*  817: 817 */    long function_pointer = caps.glInvalidateSubFramebuffer;
/*  818: 818 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  819: 819 */    BufferChecks.checkDirect(attachments);
/*  820: 820 */    nglInvalidateSubFramebuffer(target, attachments.remaining(), MemoryUtil.getAddress(attachments), x, y, width, height, function_pointer);
/*  821:     */  }
/*  822:     */  
/*  823:     */  static native void nglInvalidateSubFramebuffer(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong2);
/*  824:     */  
/*  825: 825 */  public static void glMultiDrawArraysIndirect(int mode, ByteBuffer indirect, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  826: 826 */    long function_pointer = caps.glMultiDrawArraysIndirect;
/*  827: 827 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  828: 828 */    GLChecks.ensureIndirectBOdisabled(caps);
/*  829: 829 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 16 : stride) * primcount);
/*  830: 830 */    nglMultiDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer); }
/*  831:     */  
/*  832:     */  static native void nglMultiDrawArraysIndirect(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/*  833:     */  
/*  834: 834 */  public static void glMultiDrawArraysIndirect(int mode, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  835: 835 */    long function_pointer = caps.glMultiDrawArraysIndirect;
/*  836: 836 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  837: 837 */    GLChecks.ensureIndirectBOenabled(caps);
/*  838: 838 */    nglMultiDrawArraysIndirectBO(mode, indirect_buffer_offset, primcount, stride, function_pointer);
/*  839:     */  }
/*  840:     */  
/*  841:     */  static native void nglMultiDrawArraysIndirectBO(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/*  842:     */  
/*  843:     */  public static void glMultiDrawArraysIndirect(int mode, IntBuffer indirect, int primcount, int stride) {
/*  844: 844 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  845: 845 */    long function_pointer = caps.glMultiDrawArraysIndirect;
/*  846: 846 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  847: 847 */    GLChecks.ensureIndirectBOdisabled(caps);
/*  848: 848 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 4 : stride >> 2) * primcount);
/*  849: 849 */    nglMultiDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*  850:     */  }
/*  851:     */  
/*  852:     */  public static void glMultiDrawElementsIndirect(int mode, int type, ByteBuffer indirect, int primcount, int stride) {
/*  853: 853 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  854: 854 */    long function_pointer = caps.glMultiDrawElementsIndirect;
/*  855: 855 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  856: 856 */    GLChecks.ensureIndirectBOdisabled(caps);
/*  857: 857 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 20 : stride) * primcount);
/*  858: 858 */    nglMultiDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer); }
/*  859:     */  
/*  860:     */  static native void nglMultiDrawElementsIndirect(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/*  861:     */  
/*  862: 862 */  public static void glMultiDrawElementsIndirect(int mode, int type, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  863: 863 */    long function_pointer = caps.glMultiDrawElementsIndirect;
/*  864: 864 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  865: 865 */    GLChecks.ensureIndirectBOenabled(caps);
/*  866: 866 */    nglMultiDrawElementsIndirectBO(mode, type, indirect_buffer_offset, primcount, stride, function_pointer);
/*  867:     */  }
/*  868:     */  
/*  869:     */  static native void nglMultiDrawElementsIndirectBO(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/*  870:     */  
/*  871:     */  public static void glMultiDrawElementsIndirect(int mode, int type, IntBuffer indirect, int primcount, int stride) {
/*  872: 872 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  873: 873 */    long function_pointer = caps.glMultiDrawElementsIndirect;
/*  874: 874 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  875: 875 */    GLChecks.ensureIndirectBOdisabled(caps);
/*  876: 876 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 5 : stride >> 2) * primcount);
/*  877: 877 */    nglMultiDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*  878:     */  }
/*  879:     */  
/*  880:     */  public static void glGetProgramInterface(int program, int programInterface, int pname, IntBuffer params) {
/*  881: 881 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  882: 882 */    long function_pointer = caps.glGetProgramInterfaceiv;
/*  883: 883 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  884: 884 */    BufferChecks.checkBuffer(params, 1);
/*  885: 885 */    nglGetProgramInterfaceiv(program, programInterface, pname, MemoryUtil.getAddress(params), function_pointer);
/*  886:     */  }
/*  887:     */  
/*  888:     */  static native void nglGetProgramInterfaceiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  889:     */  
/*  890:     */  public static int glGetProgramInterfacei(int program, int programInterface, int pname) {
/*  891: 891 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  892: 892 */    long function_pointer = caps.glGetProgramInterfaceiv;
/*  893: 893 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  894: 894 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  895: 895 */    nglGetProgramInterfaceiv(program, programInterface, pname, MemoryUtil.getAddress(params), function_pointer);
/*  896: 896 */    return params.get(0);
/*  897:     */  }
/*  898:     */  
/*  899:     */  public static int glGetProgramResourceIndex(int program, int programInterface, ByteBuffer name) {
/*  900: 900 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  901: 901 */    long function_pointer = caps.glGetProgramResourceIndex;
/*  902: 902 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  903: 903 */    BufferChecks.checkDirect(name);
/*  904: 904 */    BufferChecks.checkNullTerminated(name);
/*  905: 905 */    int __result = nglGetProgramResourceIndex(program, programInterface, MemoryUtil.getAddress(name), function_pointer);
/*  906: 906 */    return __result;
/*  907:     */  }
/*  908:     */  
/*  909:     */  static native int nglGetProgramResourceIndex(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  910:     */  
/*  911:     */  public static int glGetProgramResourceIndex(int program, int programInterface, CharSequence name) {
/*  912: 912 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  913: 913 */    long function_pointer = caps.glGetProgramResourceIndex;
/*  914: 914 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  915: 915 */    int __result = nglGetProgramResourceIndex(program, programInterface, APIUtil.getBufferNT(caps, name), function_pointer);
/*  916: 916 */    return __result;
/*  917:     */  }
/*  918:     */  
/*  919:     */  public static void glGetProgramResourceName(int program, int programInterface, int index, IntBuffer length, ByteBuffer name) {
/*  920: 920 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  921: 921 */    long function_pointer = caps.glGetProgramResourceName;
/*  922: 922 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  923: 923 */    if (length != null)
/*  924: 924 */      BufferChecks.checkBuffer(length, 1);
/*  925: 925 */    if (name != null)
/*  926: 926 */      BufferChecks.checkDirect(name);
/*  927: 927 */    nglGetProgramResourceName(program, programInterface, index, name == null ? 0 : name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(name), function_pointer);
/*  928:     */  }
/*  929:     */  
/*  930:     */  static native void nglGetProgramResourceName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/*  931:     */  
/*  932:     */  public static String glGetProgramResourceName(int program, int programInterface, int index, int bufSize) {
/*  933: 933 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  934: 934 */    long function_pointer = caps.glGetProgramResourceName;
/*  935: 935 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  936: 936 */    IntBuffer name_length = APIUtil.getLengths(caps);
/*  937: 937 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/*  938: 938 */    nglGetProgramResourceName(program, programInterface, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(name), function_pointer);
/*  939: 939 */    name.limit(name_length.get(0));
/*  940: 940 */    return APIUtil.getString(caps, name);
/*  941:     */  }
/*  942:     */  
/*  943:     */  public static void glGetProgramResource(int program, int programInterface, int index, IntBuffer props, IntBuffer length, IntBuffer params) {
/*  944: 944 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  945: 945 */    long function_pointer = caps.glGetProgramResourceiv;
/*  946: 946 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  947: 947 */    BufferChecks.checkDirect(props);
/*  948: 948 */    if (length != null)
/*  949: 949 */      BufferChecks.checkBuffer(length, 1);
/*  950: 950 */    BufferChecks.checkDirect(params);
/*  951: 951 */    nglGetProgramResourceiv(program, programInterface, index, props.remaining(), MemoryUtil.getAddress(props), params.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(params), function_pointer);
/*  952:     */  }
/*  953:     */  
/*  954:     */  static native void nglGetProgramResourceiv(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, long paramLong2, long paramLong3, long paramLong4);
/*  955:     */  
/*  956: 956 */  public static int glGetProgramResourceLocation(int program, int programInterface, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/*  957: 957 */    long function_pointer = caps.glGetProgramResourceLocation;
/*  958: 958 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  959: 959 */    BufferChecks.checkDirect(name);
/*  960: 960 */    BufferChecks.checkNullTerminated(name);
/*  961: 961 */    int __result = nglGetProgramResourceLocation(program, programInterface, MemoryUtil.getAddress(name), function_pointer);
/*  962: 962 */    return __result;
/*  963:     */  }
/*  964:     */  
/*  965:     */  static native int nglGetProgramResourceLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  966:     */  
/*  967:     */  public static int glGetProgramResourceLocation(int program, int programInterface, CharSequence name) {
/*  968: 968 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  969: 969 */    long function_pointer = caps.glGetProgramResourceLocation;
/*  970: 970 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  971: 971 */    int __result = nglGetProgramResourceLocation(program, programInterface, APIUtil.getBufferNT(caps, name), function_pointer);
/*  972: 972 */    return __result;
/*  973:     */  }
/*  974:     */  
/*  975:     */  public static int glGetProgramResourceLocationIndex(int program, int programInterface, ByteBuffer name) {
/*  976: 976 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  977: 977 */    long function_pointer = caps.glGetProgramResourceLocationIndex;
/*  978: 978 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  979: 979 */    BufferChecks.checkDirect(name);
/*  980: 980 */    BufferChecks.checkNullTerminated(name);
/*  981: 981 */    int __result = nglGetProgramResourceLocationIndex(program, programInterface, MemoryUtil.getAddress(name), function_pointer);
/*  982: 982 */    return __result;
/*  983:     */  }
/*  984:     */  
/*  985:     */  static native int nglGetProgramResourceLocationIndex(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  986:     */  
/*  987:     */  public static int glGetProgramResourceLocationIndex(int program, int programInterface, CharSequence name) {
/*  988: 988 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  989: 989 */    long function_pointer = caps.glGetProgramResourceLocationIndex;
/*  990: 990 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  991: 991 */    int __result = nglGetProgramResourceLocationIndex(program, programInterface, APIUtil.getBufferNT(caps, name), function_pointer);
/*  992: 992 */    return __result;
/*  993:     */  }
/*  994:     */  
/*  995:     */  public static void glShaderStorageBlockBinding(int program, int storageBlockIndex, int storageBlockBinding) {
/*  996: 996 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  997: 997 */    long function_pointer = caps.glShaderStorageBlockBinding;
/*  998: 998 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  999: 999 */    nglShaderStorageBlockBinding(program, storageBlockIndex, storageBlockBinding, function_pointer);
/* 1000:     */  }
/* 1001:     */  
/* 1002:     */  static native void nglShaderStorageBlockBinding(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 1003:     */  
/* 1004:1004 */  public static void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1005:1005 */    long function_pointer = caps.glTexBufferRange;
/* 1006:1006 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1007:1007 */    nglTexBufferRange(target, internalformat, buffer, offset, size, function_pointer);
/* 1008:     */  }
/* 1009:     */  
/* 1010:     */  static native void nglTexBufferRange(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/* 1011:     */  
/* 1012:1012 */  public static void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1013:1013 */    long function_pointer = caps.glTexStorage2DMultisample;
/* 1014:1014 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1015:1015 */    nglTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations, function_pointer);
/* 1016:     */  }
/* 1017:     */  
/* 1018:     */  static native void nglTexStorage2DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong);
/* 1019:     */  
/* 1020:1020 */  public static void glTexStorage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1021:1021 */    long function_pointer = caps.glTexStorage3DMultisample;
/* 1022:1022 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1023:1023 */    nglTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations, function_pointer);
/* 1024:     */  }
/* 1025:     */  
/* 1026:     */  static native void nglTexStorage3DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/* 1027:     */  
/* 1028:1028 */  public static void glTextureView(int texture, int target, int origtexture, int internalformat, int minlevel, int numlevels, int minlayer, int numlayers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1029:1029 */    long function_pointer = caps.glTextureView;
/* 1030:1030 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1031:1031 */    nglTextureView(texture, target, origtexture, internalformat, minlevel, numlevels, minlayer, numlayers, function_pointer);
/* 1032:     */  }
/* 1033:     */  
/* 1034:     */  static native void nglTextureView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/* 1035:     */  
/* 1036:1036 */  public static void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1037:1037 */    long function_pointer = caps.glBindVertexBuffer;
/* 1038:1038 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1039:1039 */    nglBindVertexBuffer(bindingindex, buffer, offset, stride, function_pointer);
/* 1040:     */  }
/* 1041:     */  
/* 1042:     */  static native void nglBindVertexBuffer(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/* 1043:     */  
/* 1044:1044 */  public static void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized, int relativeoffset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1045:1045 */    long function_pointer = caps.glVertexAttribFormat;
/* 1046:1046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1047:1047 */    nglVertexAttribFormat(attribindex, size, type, normalized, relativeoffset, function_pointer);
/* 1048:     */  }
/* 1049:     */  
/* 1050:     */  static native void nglVertexAttribFormat(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong);
/* 1051:     */  
/* 1052:1052 */  public static void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1053:1053 */    long function_pointer = caps.glVertexAttribIFormat;
/* 1054:1054 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1055:1055 */    nglVertexAttribIFormat(attribindex, size, type, relativeoffset, function_pointer);
/* 1056:     */  }
/* 1057:     */  
/* 1058:     */  static native void nglVertexAttribIFormat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 1059:     */  
/* 1060:1060 */  public static void glVertexAttribLFormat(int attribindex, int size, int type, int relativeoffset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1061:1061 */    long function_pointer = caps.glVertexAttribLFormat;
/* 1062:1062 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1063:1063 */    nglVertexAttribLFormat(attribindex, size, type, relativeoffset, function_pointer);
/* 1064:     */  }
/* 1065:     */  
/* 1066:     */  static native void nglVertexAttribLFormat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 1067:     */  
/* 1068:1068 */  public static void glVertexAttribBinding(int attribindex, int bindingindex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1069:1069 */    long function_pointer = caps.glVertexAttribBinding;
/* 1070:1070 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1071:1071 */    nglVertexAttribBinding(attribindex, bindingindex, function_pointer);
/* 1072:     */  }
/* 1073:     */  
/* 1074:     */  static native void nglVertexAttribBinding(int paramInt1, int paramInt2, long paramLong);
/* 1075:     */  
/* 1076:1076 */  public static void glVertexBindingDivisor(int bindingindex, int divisor) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1077:1077 */    long function_pointer = caps.glVertexBindingDivisor;
/* 1078:1078 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1079:1079 */    nglVertexBindingDivisor(bindingindex, divisor, function_pointer);
/* 1080:     */  }
/* 1081:     */  
/* 1082:     */  static native void nglVertexBindingDivisor(int paramInt1, int paramInt2, long paramLong);
/* 1083:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL43
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */