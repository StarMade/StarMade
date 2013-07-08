/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.ByteOrder;
/*    5:     */import java.nio.FloatBuffer;
/*    6:     */import java.nio.IntBuffer;
/*    7:     */import java.nio.ShortBuffer;
/*    8:     */import org.lwjgl.BufferChecks;
/*    9:     */import org.lwjgl.LWJGLUtil;
/*   10:     */import org.lwjgl.MemoryUtil;
/*   11:     */
/*  336:     */public final class GL30
/*  337:     */{
/*  338:     */  public static final int GL_MAJOR_VERSION = 33307;
/*  339:     */  public static final int GL_MINOR_VERSION = 33308;
/*  340:     */  public static final int GL_NUM_EXTENSIONS = 33309;
/*  341:     */  public static final int GL_CONTEXT_FLAGS = 33310;
/*  342:     */  public static final int GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 1;
/*  343:     */  public static final int GL_DEPTH_BUFFER = 33315;
/*  344:     */  public static final int GL_STENCIL_BUFFER = 33316;
/*  345:     */  public static final int GL_COMPRESSED_RED = 33317;
/*  346:     */  public static final int GL_COMPRESSED_RG = 33318;
/*  347:     */  public static final int GL_COMPARE_REF_TO_TEXTURE = 34894;
/*  348:     */  public static final int GL_CLIP_DISTANCE0 = 12288;
/*  349:     */  public static final int GL_CLIP_DISTANCE1 = 12289;
/*  350:     */  public static final int GL_CLIP_DISTANCE2 = 12290;
/*  351:     */  public static final int GL_CLIP_DISTANCE3 = 12291;
/*  352:     */  public static final int GL_CLIP_DISTANCE4 = 12292;
/*  353:     */  public static final int GL_CLIP_DISTANCE5 = 12293;
/*  354:     */  public static final int GL_CLIP_DISTANCE6 = 12294;
/*  355:     */  public static final int GL_CLIP_DISTANCE7 = 12295;
/*  356:     */  public static final int GL_MAX_CLIP_DISTANCES = 3378;
/*  357:     */  public static final int GL_MAX_VARYING_COMPONENTS = 35659;
/*  358:     */  public static final int GL_BUFFER_ACCESS_FLAGS = 37151;
/*  359:     */  public static final int GL_BUFFER_MAP_LENGTH = 37152;
/*  360:     */  public static final int GL_BUFFER_MAP_OFFSET = 37153;
/*  361:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER = 35069;
/*  362:     */  public static final int GL_SAMPLER_BUFFER = 36290;
/*  363:     */  public static final int GL_SAMPLER_CUBE_SHADOW = 36293;
/*  364:     */  public static final int GL_UNSIGNED_INT_VEC2 = 36294;
/*  365:     */  public static final int GL_UNSIGNED_INT_VEC3 = 36295;
/*  366:     */  public static final int GL_UNSIGNED_INT_VEC4 = 36296;
/*  367:     */  public static final int GL_INT_SAMPLER_1D = 36297;
/*  368:     */  public static final int GL_INT_SAMPLER_2D = 36298;
/*  369:     */  public static final int GL_INT_SAMPLER_3D = 36299;
/*  370:     */  public static final int GL_INT_SAMPLER_CUBE = 36300;
/*  371:     */  public static final int GL_INT_SAMPLER_2D_RECT = 36301;
/*  372:     */  public static final int GL_INT_SAMPLER_1D_ARRAY = 36302;
/*  373:     */  public static final int GL_INT_SAMPLER_2D_ARRAY = 36303;
/*  374:     */  public static final int GL_INT_SAMPLER_BUFFER = 36304;
/*  375:     */  public static final int GL_UNSIGNED_INT_SAMPLER_1D = 36305;
/*  376:     */  public static final int GL_UNSIGNED_INT_SAMPLER_2D = 36306;
/*  377:     */  public static final int GL_UNSIGNED_INT_SAMPLER_3D = 36307;
/*  378:     */  public static final int GL_UNSIGNED_INT_SAMPLER_CUBE = 36308;
/*  379:     */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT = 36309;
/*  380:     */  public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 36310;
/*  381:     */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 36311;
/*  382:     */  public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER = 36312;
/*  383:     */  public static final int GL_MIN_PROGRAM_TEXEL_OFFSET = 35076;
/*  384:     */  public static final int GL_MAX_PROGRAM_TEXEL_OFFSET = 35077;
/*  385:     */  public static final int GL_QUERY_WAIT = 36371;
/*  386:     */  public static final int GL_QUERY_NO_WAIT = 36372;
/*  387:     */  public static final int GL_QUERY_BY_REGION_WAIT = 36373;
/*  388:     */  public static final int GL_QUERY_BY_REGION_NO_WAIT = 36374;
/*  389:     */  public static final int GL_MAP_READ_BIT = 1;
/*  390:     */  public static final int GL_MAP_WRITE_BIT = 2;
/*  391:     */  public static final int GL_MAP_INVALIDATE_RANGE_BIT = 4;
/*  392:     */  public static final int GL_MAP_INVALIDATE_BUFFER_BIT = 8;
/*  393:     */  public static final int GL_MAP_FLUSH_EXPLICIT_BIT = 16;
/*  394:     */  public static final int GL_MAP_UNSYNCHRONIZED_BIT = 32;
/*  395:     */  public static final int GL_CLAMP_VERTEX_COLOR = 35098;
/*  396:     */  public static final int GL_CLAMP_FRAGMENT_COLOR = 35099;
/*  397:     */  public static final int GL_CLAMP_READ_COLOR = 35100;
/*  398:     */  public static final int GL_FIXED_ONLY = 35101;
/*  399:     */  public static final int GL_DEPTH_COMPONENT32F = 36012;
/*  400:     */  public static final int GL_DEPTH32F_STENCIL8 = 36013;
/*  401:     */  public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 36269;
/*  402:     */  public static final int GL_TEXTURE_RED_TYPE = 35856;
/*  403:     */  public static final int GL_TEXTURE_GREEN_TYPE = 35857;
/*  404:     */  public static final int GL_TEXTURE_BLUE_TYPE = 35858;
/*  405:     */  public static final int GL_TEXTURE_ALPHA_TYPE = 35859;
/*  406:     */  public static final int GL_TEXTURE_LUMINANCE_TYPE = 35860;
/*  407:     */  public static final int GL_TEXTURE_INTENSITY_TYPE = 35861;
/*  408:     */  public static final int GL_TEXTURE_DEPTH_TYPE = 35862;
/*  409:     */  public static final int GL_UNSIGNED_NORMALIZED = 35863;
/*  410:     */  public static final int GL_RGBA32F = 34836;
/*  411:     */  public static final int GL_RGB32F = 34837;
/*  412:     */  public static final int GL_ALPHA32F = 34838;
/*  413:     */  public static final int GL_RGBA16F = 34842;
/*  414:     */  public static final int GL_RGB16F = 34843;
/*  415:     */  public static final int GL_ALPHA16F = 34844;
/*  416:     */  public static final int GL_R11F_G11F_B10F = 35898;
/*  417:     */  public static final int GL_UNSIGNED_INT_10F_11F_11F_REV = 35899;
/*  418:     */  public static final int GL_RGB9_E5 = 35901;
/*  419:     */  public static final int GL_UNSIGNED_INT_5_9_9_9_REV = 35902;
/*  420:     */  public static final int GL_TEXTURE_SHARED_SIZE = 35903;
/*  421:     */  public static final int GL_FRAMEBUFFER = 36160;
/*  422:     */  public static final int GL_READ_FRAMEBUFFER = 36008;
/*  423:     */  public static final int GL_DRAW_FRAMEBUFFER = 36009;
/*  424:     */  public static final int GL_RENDERBUFFER = 36161;
/*  425:     */  public static final int GL_STENCIL_INDEX1 = 36166;
/*  426:     */  public static final int GL_STENCIL_INDEX4 = 36167;
/*  427:     */  public static final int GL_STENCIL_INDEX8 = 36168;
/*  428:     */  public static final int GL_STENCIL_INDEX16 = 36169;
/*  429:     */  public static final int GL_RENDERBUFFER_WIDTH = 36162;
/*  430:     */  public static final int GL_RENDERBUFFER_HEIGHT = 36163;
/*  431:     */  public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
/*  432:     */  public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
/*  433:     */  public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
/*  434:     */  public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
/*  435:     */  public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
/*  436:     */  public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
/*  437:     */  public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
/*  438:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
/*  439:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
/*  440:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
/*  441:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
/*  442:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 33296;
/*  443:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 33297;
/*  444:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 33298;
/*  445:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 33299;
/*  446:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 33300;
/*  447:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 33301;
/*  448:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 33302;
/*  449:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 33303;
/*  450:     */  public static final int GL_FRAMEBUFFER_DEFAULT = 33304;
/*  451:     */  public static final int GL_INDEX = 33314;
/*  452:     */  public static final int GL_COLOR_ATTACHMENT0 = 36064;
/*  453:     */  public static final int GL_COLOR_ATTACHMENT1 = 36065;
/*  454:     */  public static final int GL_COLOR_ATTACHMENT2 = 36066;
/*  455:     */  public static final int GL_COLOR_ATTACHMENT3 = 36067;
/*  456:     */  public static final int GL_COLOR_ATTACHMENT4 = 36068;
/*  457:     */  public static final int GL_COLOR_ATTACHMENT5 = 36069;
/*  458:     */  public static final int GL_COLOR_ATTACHMENT6 = 36070;
/*  459:     */  public static final int GL_COLOR_ATTACHMENT7 = 36071;
/*  460:     */  public static final int GL_COLOR_ATTACHMENT8 = 36072;
/*  461:     */  public static final int GL_COLOR_ATTACHMENT9 = 36073;
/*  462:     */  public static final int GL_COLOR_ATTACHMENT10 = 36074;
/*  463:     */  public static final int GL_COLOR_ATTACHMENT11 = 36075;
/*  464:     */  public static final int GL_COLOR_ATTACHMENT12 = 36076;
/*  465:     */  public static final int GL_COLOR_ATTACHMENT13 = 36077;
/*  466:     */  public static final int GL_COLOR_ATTACHMENT14 = 36078;
/*  467:     */  public static final int GL_COLOR_ATTACHMENT15 = 36079;
/*  468:     */  public static final int GL_DEPTH_ATTACHMENT = 36096;
/*  469:     */  public static final int GL_STENCIL_ATTACHMENT = 36128;
/*  470:     */  public static final int GL_DEPTH_STENCIL_ATTACHMENT = 33306;
/*  471:     */  public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
/*  472:     */  public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
/*  473:     */  public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
/*  474:     */  public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 36059;
/*  475:     */  public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 36060;
/*  476:     */  public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
/*  477:     */  public static final int GL_FRAMEBUFFER_UNDEFINED = 33305;
/*  478:     */  public static final int GL_FRAMEBUFFER_BINDING = 36006;
/*  479:     */  public static final int GL_RENDERBUFFER_BINDING = 36007;
/*  480:     */  public static final int GL_MAX_COLOR_ATTACHMENTS = 36063;
/*  481:     */  public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
/*  482:     */  public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
/*  483:     */  public static final int GL_HALF_FLOAT = 5131;
/*  484:     */  public static final int GL_RENDERBUFFER_SAMPLES = 36011;
/*  485:     */  public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 36182;
/*  486:     */  public static final int GL_MAX_SAMPLES = 36183;
/*  487:     */  public static final int GL_DRAW_FRAMEBUFFER_BINDING = 36006;
/*  488:     */  public static final int GL_READ_FRAMEBUFFER_BINDING = 36010;
/*  489:     */  public static final int GL_RGBA_INTEGER_MODE = 36254;
/*  490:     */  public static final int GL_RGBA32UI = 36208;
/*  491:     */  public static final int GL_RGB32UI = 36209;
/*  492:     */  public static final int GL_ALPHA32UI = 36210;
/*  493:     */  public static final int GL_RGBA16UI = 36214;
/*  494:     */  public static final int GL_RGB16UI = 36215;
/*  495:     */  public static final int GL_ALPHA16UI = 36216;
/*  496:     */  public static final int GL_RGBA8UI = 36220;
/*  497:     */  public static final int GL_RGB8UI = 36221;
/*  498:     */  public static final int GL_ALPHA8UI = 36222;
/*  499:     */  public static final int GL_RGBA32I = 36226;
/*  500:     */  public static final int GL_RGB32I = 36227;
/*  501:     */  public static final int GL_ALPHA32I = 36228;
/*  502:     */  public static final int GL_RGBA16I = 36232;
/*  503:     */  public static final int GL_RGB16I = 36233;
/*  504:     */  public static final int GL_ALPHA16I = 36234;
/*  505:     */  public static final int GL_RGBA8I = 36238;
/*  506:     */  public static final int GL_RGB8I = 36239;
/*  507:     */  public static final int GL_ALPHA8I = 36240;
/*  508:     */  public static final int GL_RED_INTEGER = 36244;
/*  509:     */  public static final int GL_GREEN_INTEGER = 36245;
/*  510:     */  public static final int GL_BLUE_INTEGER = 36246;
/*  511:     */  public static final int GL_ALPHA_INTEGER = 36247;
/*  512:     */  public static final int GL_RGB_INTEGER = 36248;
/*  513:     */  public static final int GL_RGBA_INTEGER = 36249;
/*  514:     */  public static final int GL_BGR_INTEGER = 36250;
/*  515:     */  public static final int GL_BGRA_INTEGER = 36251;
/*  516:     */  public static final int GL_TEXTURE_1D_ARRAY = 35864;
/*  517:     */  public static final int GL_TEXTURE_2D_ARRAY = 35866;
/*  518:     */  public static final int GL_PROXY_TEXTURE_2D_ARRAY = 35867;
/*  519:     */  public static final int GL_PROXY_TEXTURE_1D_ARRAY = 35865;
/*  520:     */  public static final int GL_TEXTURE_BINDING_1D_ARRAY = 35868;
/*  521:     */  public static final int GL_TEXTURE_BINDING_2D_ARRAY = 35869;
/*  522:     */  public static final int GL_MAX_ARRAY_TEXTURE_LAYERS = 35071;
/*  523:     */  public static final int GL_COMPARE_REF_DEPTH_TO_TEXTURE = 34894;
/*  524:     */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
/*  525:     */  public static final int GL_SAMPLER_1D_ARRAY = 36288;
/*  526:     */  public static final int GL_SAMPLER_2D_ARRAY = 36289;
/*  527:     */  public static final int GL_SAMPLER_1D_ARRAY_SHADOW = 36291;
/*  528:     */  public static final int GL_SAMPLER_2D_ARRAY_SHADOW = 36292;
/*  529:     */  public static final int GL_DEPTH_STENCIL = 34041;
/*  530:     */  public static final int GL_UNSIGNED_INT_24_8 = 34042;
/*  531:     */  public static final int GL_DEPTH24_STENCIL8 = 35056;
/*  532:     */  public static final int GL_TEXTURE_STENCIL_SIZE = 35057;
/*  533:     */  public static final int GL_COMPRESSED_RED_RGTC1 = 36283;
/*  534:     */  public static final int GL_COMPRESSED_SIGNED_RED_RGTC1 = 36284;
/*  535:     */  public static final int GL_COMPRESSED_RG_RGTC2 = 36285;
/*  536:     */  public static final int GL_COMPRESSED_SIGNED_RG_RGTC2 = 36286;
/*  537:     */  public static final int GL_R8 = 33321;
/*  538:     */  public static final int GL_R16 = 33322;
/*  539:     */  public static final int GL_RG8 = 33323;
/*  540:     */  public static final int GL_RG16 = 33324;
/*  541:     */  public static final int GL_R16F = 33325;
/*  542:     */  public static final int GL_R32F = 33326;
/*  543:     */  public static final int GL_RG16F = 33327;
/*  544:     */  public static final int GL_RG32F = 33328;
/*  545:     */  public static final int GL_R8I = 33329;
/*  546:     */  public static final int GL_R8UI = 33330;
/*  547:     */  public static final int GL_R16I = 33331;
/*  548:     */  public static final int GL_R16UI = 33332;
/*  549:     */  public static final int GL_R32I = 33333;
/*  550:     */  public static final int GL_R32UI = 33334;
/*  551:     */  public static final int GL_RG8I = 33335;
/*  552:     */  public static final int GL_RG8UI = 33336;
/*  553:     */  public static final int GL_RG16I = 33337;
/*  554:     */  public static final int GL_RG16UI = 33338;
/*  555:     */  public static final int GL_RG32I = 33339;
/*  556:     */  public static final int GL_RG32UI = 33340;
/*  557:     */  public static final int GL_RG = 33319;
/*  558:     */  public static final int GL_RG_INTEGER = 33320;
/*  559:     */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER = 35982;
/*  560:     */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START = 35972;
/*  561:     */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 35973;
/*  562:     */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 35983;
/*  563:     */  public static final int GL_INTERLEAVED_ATTRIBS = 35980;
/*  564:     */  public static final int GL_SEPARATE_ATTRIBS = 35981;
/*  565:     */  public static final int GL_PRIMITIVES_GENERATED = 35975;
/*  566:     */  public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 35976;
/*  567:     */  public static final int GL_RASTERIZER_DISCARD = 35977;
/*  568:     */  public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 35978;
/*  569:     */  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 35979;
/*  570:     */  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 35968;
/*  571:     */  public static final int GL_TRANSFORM_FEEDBACK_VARYINGS = 35971;
/*  572:     */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 35967;
/*  573:     */  public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 35958;
/*  574:     */  public static final int GL_VERTEX_ARRAY_BINDING = 34229;
/*  575:     */  public static final int GL_FRAMEBUFFER_SRGB = 36281;
/*  576:     */  public static final int GL_FRAMEBUFFER_SRGB_CAPABLE = 36282;
/*  577:     */  
/*  578:     */  public static String glGetStringi(int name, int index)
/*  579:     */  {
/*  580: 580 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  581: 581 */    long function_pointer = caps.glGetStringi;
/*  582: 582 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  583: 583 */    String __result = nglGetStringi(name, index, function_pointer);
/*  584: 584 */    return __result;
/*  585:     */  }
/*  586:     */  
/*  587:     */  static native String nglGetStringi(int paramInt1, int paramInt2, long paramLong);
/*  588:     */  
/*  589: 589 */  public static void glClearBuffer(int buffer, int drawbuffer, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  590: 590 */    long function_pointer = caps.glClearBufferfv;
/*  591: 591 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  592: 592 */    BufferChecks.checkBuffer(value, 4);
/*  593: 593 */    nglClearBufferfv(buffer, drawbuffer, MemoryUtil.getAddress(value), function_pointer);
/*  594:     */  }
/*  595:     */  
/*  596:     */  static native void nglClearBufferfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  597:     */  
/*  598: 598 */  public static void glClearBuffer(int buffer, int drawbuffer, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  599: 599 */    long function_pointer = caps.glClearBufferiv;
/*  600: 600 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  601: 601 */    BufferChecks.checkBuffer(value, 4);
/*  602: 602 */    nglClearBufferiv(buffer, drawbuffer, MemoryUtil.getAddress(value), function_pointer);
/*  603:     */  }
/*  604:     */  
/*  605:     */  static native void nglClearBufferiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  606:     */  
/*  607: 607 */  public static void glClearBufferu(int buffer, int drawbuffer, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  608: 608 */    long function_pointer = caps.glClearBufferuiv;
/*  609: 609 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  610: 610 */    BufferChecks.checkBuffer(value, 4);
/*  611: 611 */    nglClearBufferuiv(buffer, drawbuffer, MemoryUtil.getAddress(value), function_pointer);
/*  612:     */  }
/*  613:     */  
/*  614:     */  static native void nglClearBufferuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  615:     */  
/*  616: 616 */  public static void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil) { ContextCapabilities caps = GLContext.getCapabilities();
/*  617: 617 */    long function_pointer = caps.glClearBufferfi;
/*  618: 618 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  619: 619 */    nglClearBufferfi(buffer, drawbuffer, depth, stencil, function_pointer);
/*  620:     */  }
/*  621:     */  
/*  622:     */  static native void nglClearBufferfi(int paramInt1, int paramInt2, float paramFloat, int paramInt3, long paramLong);
/*  623:     */  
/*  624: 624 */  public static void glVertexAttribI1i(int index, int x) { ContextCapabilities caps = GLContext.getCapabilities();
/*  625: 625 */    long function_pointer = caps.glVertexAttribI1i;
/*  626: 626 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  627: 627 */    nglVertexAttribI1i(index, x, function_pointer);
/*  628:     */  }
/*  629:     */  
/*  630:     */  static native void nglVertexAttribI1i(int paramInt1, int paramInt2, long paramLong);
/*  631:     */  
/*  632: 632 */  public static void glVertexAttribI2i(int index, int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  633: 633 */    long function_pointer = caps.glVertexAttribI2i;
/*  634: 634 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  635: 635 */    nglVertexAttribI2i(index, x, y, function_pointer);
/*  636:     */  }
/*  637:     */  
/*  638:     */  static native void nglVertexAttribI2i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  639:     */  
/*  640: 640 */  public static void glVertexAttribI3i(int index, int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  641: 641 */    long function_pointer = caps.glVertexAttribI3i;
/*  642: 642 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  643: 643 */    nglVertexAttribI3i(index, x, y, z, function_pointer);
/*  644:     */  }
/*  645:     */  
/*  646:     */  static native void nglVertexAttribI3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  647:     */  
/*  648: 648 */  public static void glVertexAttribI4i(int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  649: 649 */    long function_pointer = caps.glVertexAttribI4i;
/*  650: 650 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  651: 651 */    nglVertexAttribI4i(index, x, y, z, w, function_pointer);
/*  652:     */  }
/*  653:     */  
/*  654:     */  static native void nglVertexAttribI4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  655:     */  
/*  656: 656 */  public static void glVertexAttribI1ui(int index, int x) { ContextCapabilities caps = GLContext.getCapabilities();
/*  657: 657 */    long function_pointer = caps.glVertexAttribI1ui;
/*  658: 658 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  659: 659 */    nglVertexAttribI1ui(index, x, function_pointer);
/*  660:     */  }
/*  661:     */  
/*  662:     */  static native void nglVertexAttribI1ui(int paramInt1, int paramInt2, long paramLong);
/*  663:     */  
/*  664: 664 */  public static void glVertexAttribI2ui(int index, int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  665: 665 */    long function_pointer = caps.glVertexAttribI2ui;
/*  666: 666 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  667: 667 */    nglVertexAttribI2ui(index, x, y, function_pointer);
/*  668:     */  }
/*  669:     */  
/*  670:     */  static native void nglVertexAttribI2ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  671:     */  
/*  672: 672 */  public static void glVertexAttribI3ui(int index, int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  673: 673 */    long function_pointer = caps.glVertexAttribI3ui;
/*  674: 674 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  675: 675 */    nglVertexAttribI3ui(index, x, y, z, function_pointer);
/*  676:     */  }
/*  677:     */  
/*  678:     */  static native void nglVertexAttribI3ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  679:     */  
/*  680: 680 */  public static void glVertexAttribI4ui(int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  681: 681 */    long function_pointer = caps.glVertexAttribI4ui;
/*  682: 682 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  683: 683 */    nglVertexAttribI4ui(index, x, y, z, w, function_pointer);
/*  684:     */  }
/*  685:     */  
/*  686:     */  static native void nglVertexAttribI4ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  687:     */  
/*  688: 688 */  public static void glVertexAttribI1(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  689: 689 */    long function_pointer = caps.glVertexAttribI1iv;
/*  690: 690 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  691: 691 */    BufferChecks.checkBuffer(v, 1);
/*  692: 692 */    nglVertexAttribI1iv(index, MemoryUtil.getAddress(v), function_pointer);
/*  693:     */  }
/*  694:     */  
/*  695:     */  static native void nglVertexAttribI1iv(int paramInt, long paramLong1, long paramLong2);
/*  696:     */  
/*  697: 697 */  public static void glVertexAttribI2(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  698: 698 */    long function_pointer = caps.glVertexAttribI2iv;
/*  699: 699 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  700: 700 */    BufferChecks.checkBuffer(v, 2);
/*  701: 701 */    nglVertexAttribI2iv(index, MemoryUtil.getAddress(v), function_pointer);
/*  702:     */  }
/*  703:     */  
/*  704:     */  static native void nglVertexAttribI2iv(int paramInt, long paramLong1, long paramLong2);
/*  705:     */  
/*  706: 706 */  public static void glVertexAttribI3(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  707: 707 */    long function_pointer = caps.glVertexAttribI3iv;
/*  708: 708 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  709: 709 */    BufferChecks.checkBuffer(v, 3);
/*  710: 710 */    nglVertexAttribI3iv(index, MemoryUtil.getAddress(v), function_pointer);
/*  711:     */  }
/*  712:     */  
/*  713:     */  static native void nglVertexAttribI3iv(int paramInt, long paramLong1, long paramLong2);
/*  714:     */  
/*  715: 715 */  public static void glVertexAttribI4(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  716: 716 */    long function_pointer = caps.glVertexAttribI4iv;
/*  717: 717 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  718: 718 */    BufferChecks.checkBuffer(v, 4);
/*  719: 719 */    nglVertexAttribI4iv(index, MemoryUtil.getAddress(v), function_pointer);
/*  720:     */  }
/*  721:     */  
/*  722:     */  static native void nglVertexAttribI4iv(int paramInt, long paramLong1, long paramLong2);
/*  723:     */  
/*  724: 724 */  public static void glVertexAttribI1u(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  725: 725 */    long function_pointer = caps.glVertexAttribI1uiv;
/*  726: 726 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  727: 727 */    BufferChecks.checkBuffer(v, 1);
/*  728: 728 */    nglVertexAttribI1uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*  729:     */  }
/*  730:     */  
/*  731:     */  static native void nglVertexAttribI1uiv(int paramInt, long paramLong1, long paramLong2);
/*  732:     */  
/*  733: 733 */  public static void glVertexAttribI2u(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  734: 734 */    long function_pointer = caps.glVertexAttribI2uiv;
/*  735: 735 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  736: 736 */    BufferChecks.checkBuffer(v, 2);
/*  737: 737 */    nglVertexAttribI2uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*  738:     */  }
/*  739:     */  
/*  740:     */  static native void nglVertexAttribI2uiv(int paramInt, long paramLong1, long paramLong2);
/*  741:     */  
/*  742: 742 */  public static void glVertexAttribI3u(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  743: 743 */    long function_pointer = caps.glVertexAttribI3uiv;
/*  744: 744 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  745: 745 */    BufferChecks.checkBuffer(v, 3);
/*  746: 746 */    nglVertexAttribI3uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*  747:     */  }
/*  748:     */  
/*  749:     */  static native void nglVertexAttribI3uiv(int paramInt, long paramLong1, long paramLong2);
/*  750:     */  
/*  751: 751 */  public static void glVertexAttribI4u(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  752: 752 */    long function_pointer = caps.glVertexAttribI4uiv;
/*  753: 753 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  754: 754 */    BufferChecks.checkBuffer(v, 4);
/*  755: 755 */    nglVertexAttribI4uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*  756:     */  }
/*  757:     */  
/*  758:     */  static native void nglVertexAttribI4uiv(int paramInt, long paramLong1, long paramLong2);
/*  759:     */  
/*  760: 760 */  public static void glVertexAttribI4(int index, ByteBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  761: 761 */    long function_pointer = caps.glVertexAttribI4bv;
/*  762: 762 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  763: 763 */    BufferChecks.checkBuffer(v, 4);
/*  764: 764 */    nglVertexAttribI4bv(index, MemoryUtil.getAddress(v), function_pointer);
/*  765:     */  }
/*  766:     */  
/*  767:     */  static native void nglVertexAttribI4bv(int paramInt, long paramLong1, long paramLong2);
/*  768:     */  
/*  769: 769 */  public static void glVertexAttribI4(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  770: 770 */    long function_pointer = caps.glVertexAttribI4sv;
/*  771: 771 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  772: 772 */    BufferChecks.checkBuffer(v, 4);
/*  773: 773 */    nglVertexAttribI4sv(index, MemoryUtil.getAddress(v), function_pointer);
/*  774:     */  }
/*  775:     */  
/*  776:     */  static native void nglVertexAttribI4sv(int paramInt, long paramLong1, long paramLong2);
/*  777:     */  
/*  778: 778 */  public static void glVertexAttribI4u(int index, ByteBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  779: 779 */    long function_pointer = caps.glVertexAttribI4ubv;
/*  780: 780 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  781: 781 */    BufferChecks.checkBuffer(v, 4);
/*  782: 782 */    nglVertexAttribI4ubv(index, MemoryUtil.getAddress(v), function_pointer);
/*  783:     */  }
/*  784:     */  
/*  785:     */  static native void nglVertexAttribI4ubv(int paramInt, long paramLong1, long paramLong2);
/*  786:     */  
/*  787: 787 */  public static void glVertexAttribI4u(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  788: 788 */    long function_pointer = caps.glVertexAttribI4usv;
/*  789: 789 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  790: 790 */    BufferChecks.checkBuffer(v, 4);
/*  791: 791 */    nglVertexAttribI4usv(index, MemoryUtil.getAddress(v), function_pointer);
/*  792:     */  }
/*  793:     */  
/*  794:     */  static native void nglVertexAttribI4usv(int paramInt, long paramLong1, long paramLong2);
/*  795:     */  
/*  796: 796 */  public static void glVertexAttribIPointer(int index, int size, int type, int stride, ByteBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  797: 797 */    long function_pointer = caps.glVertexAttribIPointer;
/*  798: 798 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  799: 799 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  800: 800 */    BufferChecks.checkDirect(buffer);
/*  801: 801 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  802: 802 */    nglVertexAttribIPointer(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  803:     */  }
/*  804:     */  
/*  805: 805 */  public static void glVertexAttribIPointer(int index, int size, int type, int stride, IntBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  806: 806 */    long function_pointer = caps.glVertexAttribIPointer;
/*  807: 807 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  808: 808 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  809: 809 */    BufferChecks.checkDirect(buffer);
/*  810: 810 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  811: 811 */    nglVertexAttribIPointer(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  812:     */  }
/*  813:     */  
/*  814: 814 */  public static void glVertexAttribIPointer(int index, int size, int type, int stride, ShortBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  815: 815 */    long function_pointer = caps.glVertexAttribIPointer;
/*  816: 816 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  817: 817 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  818: 818 */    BufferChecks.checkDirect(buffer);
/*  819: 819 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  820: 820 */    nglVertexAttribIPointer(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer); }
/*  821:     */  
/*  822:     */  static native void nglVertexAttribIPointer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  823:     */  
/*  824: 824 */  public static void glVertexAttribIPointer(int index, int size, int type, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  825: 825 */    long function_pointer = caps.glVertexAttribIPointer;
/*  826: 826 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  827: 827 */    GLChecks.ensureArrayVBOenabled(caps);
/*  828: 828 */    nglVertexAttribIPointerBO(index, size, type, stride, buffer_buffer_offset, function_pointer);
/*  829:     */  }
/*  830:     */  
/*  831:     */  static native void nglVertexAttribIPointerBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  832:     */  
/*  833: 833 */  public static void glGetVertexAttribI(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  834: 834 */    long function_pointer = caps.glGetVertexAttribIiv;
/*  835: 835 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  836: 836 */    BufferChecks.checkBuffer(params, 4);
/*  837: 837 */    nglGetVertexAttribIiv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  838:     */  }
/*  839:     */  
/*  840:     */  static native void nglGetVertexAttribIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  841:     */  
/*  842: 842 */  public static void glGetVertexAttribIu(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  843: 843 */    long function_pointer = caps.glGetVertexAttribIuiv;
/*  844: 844 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  845: 845 */    BufferChecks.checkBuffer(params, 4);
/*  846: 846 */    nglGetVertexAttribIuiv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  847:     */  }
/*  848:     */  
/*  849:     */  static native void nglGetVertexAttribIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  850:     */  
/*  851: 851 */  public static void glUniform1ui(int location, int v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  852: 852 */    long function_pointer = caps.glUniform1ui;
/*  853: 853 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  854: 854 */    nglUniform1ui(location, v0, function_pointer);
/*  855:     */  }
/*  856:     */  
/*  857:     */  static native void nglUniform1ui(int paramInt1, int paramInt2, long paramLong);
/*  858:     */  
/*  859: 859 */  public static void glUniform2ui(int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  860: 860 */    long function_pointer = caps.glUniform2ui;
/*  861: 861 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  862: 862 */    nglUniform2ui(location, v0, v1, function_pointer);
/*  863:     */  }
/*  864:     */  
/*  865:     */  static native void nglUniform2ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  866:     */  
/*  867: 867 */  public static void glUniform3ui(int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  868: 868 */    long function_pointer = caps.glUniform3ui;
/*  869: 869 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  870: 870 */    nglUniform3ui(location, v0, v1, v2, function_pointer);
/*  871:     */  }
/*  872:     */  
/*  873:     */  static native void nglUniform3ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  874:     */  
/*  875: 875 */  public static void glUniform4ui(int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  876: 876 */    long function_pointer = caps.glUniform4ui;
/*  877: 877 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  878: 878 */    nglUniform4ui(location, v0, v1, v2, v3, function_pointer);
/*  879:     */  }
/*  880:     */  
/*  881:     */  static native void nglUniform4ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  882:     */  
/*  883: 883 */  public static void glUniform1u(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  884: 884 */    long function_pointer = caps.glUniform1uiv;
/*  885: 885 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  886: 886 */    BufferChecks.checkDirect(value);
/*  887: 887 */    nglUniform1uiv(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  888:     */  }
/*  889:     */  
/*  890:     */  static native void nglUniform1uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  891:     */  
/*  892: 892 */  public static void glUniform2u(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  893: 893 */    long function_pointer = caps.glUniform2uiv;
/*  894: 894 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  895: 895 */    BufferChecks.checkDirect(value);
/*  896: 896 */    nglUniform2uiv(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*  897:     */  }
/*  898:     */  
/*  899:     */  static native void nglUniform2uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  900:     */  
/*  901: 901 */  public static void glUniform3u(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  902: 902 */    long function_pointer = caps.glUniform3uiv;
/*  903: 903 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  904: 904 */    BufferChecks.checkDirect(value);
/*  905: 905 */    nglUniform3uiv(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*  906:     */  }
/*  907:     */  
/*  908:     */  static native void nglUniform3uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  909:     */  
/*  910: 910 */  public static void glUniform4u(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  911: 911 */    long function_pointer = caps.glUniform4uiv;
/*  912: 912 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  913: 913 */    BufferChecks.checkDirect(value);
/*  914: 914 */    nglUniform4uiv(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*  915:     */  }
/*  916:     */  
/*  917:     */  static native void nglUniform4uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  918:     */  
/*  919: 919 */  public static void glGetUniformu(int program, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  920: 920 */    long function_pointer = caps.glGetUniformuiv;
/*  921: 921 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  922: 922 */    BufferChecks.checkDirect(params);
/*  923: 923 */    nglGetUniformuiv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*  924:     */  }
/*  925:     */  
/*  926:     */  static native void nglGetUniformuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  927:     */  
/*  928: 928 */  public static void glBindFragDataLocation(int program, int colorNumber, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/*  929: 929 */    long function_pointer = caps.glBindFragDataLocation;
/*  930: 930 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  931: 931 */    BufferChecks.checkDirect(name);
/*  932: 932 */    BufferChecks.checkNullTerminated(name);
/*  933: 933 */    nglBindFragDataLocation(program, colorNumber, MemoryUtil.getAddress(name), function_pointer);
/*  934:     */  }
/*  935:     */  
/*  936:     */  static native void nglBindFragDataLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  937:     */  
/*  938:     */  public static void glBindFragDataLocation(int program, int colorNumber, CharSequence name) {
/*  939: 939 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  940: 940 */    long function_pointer = caps.glBindFragDataLocation;
/*  941: 941 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  942: 942 */    nglBindFragDataLocation(program, colorNumber, APIUtil.getBufferNT(caps, name), function_pointer);
/*  943:     */  }
/*  944:     */  
/*  945:     */  public static int glGetFragDataLocation(int program, ByteBuffer name) {
/*  946: 946 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  947: 947 */    long function_pointer = caps.glGetFragDataLocation;
/*  948: 948 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  949: 949 */    BufferChecks.checkDirect(name);
/*  950: 950 */    BufferChecks.checkNullTerminated(name);
/*  951: 951 */    int __result = nglGetFragDataLocation(program, MemoryUtil.getAddress(name), function_pointer);
/*  952: 952 */    return __result;
/*  953:     */  }
/*  954:     */  
/*  955:     */  static native int nglGetFragDataLocation(int paramInt, long paramLong1, long paramLong2);
/*  956:     */  
/*  957:     */  public static int glGetFragDataLocation(int program, CharSequence name) {
/*  958: 958 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  959: 959 */    long function_pointer = caps.glGetFragDataLocation;
/*  960: 960 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  961: 961 */    int __result = nglGetFragDataLocation(program, APIUtil.getBufferNT(caps, name), function_pointer);
/*  962: 962 */    return __result;
/*  963:     */  }
/*  964:     */  
/*  965:     */  public static void glBeginConditionalRender(int id, int mode) {
/*  966: 966 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  967: 967 */    long function_pointer = caps.glBeginConditionalRender;
/*  968: 968 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  969: 969 */    nglBeginConditionalRender(id, mode, function_pointer);
/*  970:     */  }
/*  971:     */  
/*  972:     */  static native void nglBeginConditionalRender(int paramInt1, int paramInt2, long paramLong);
/*  973:     */  
/*  974: 974 */  public static void glEndConditionalRender() { ContextCapabilities caps = GLContext.getCapabilities();
/*  975: 975 */    long function_pointer = caps.glEndConditionalRender;
/*  976: 976 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  977: 977 */    nglEndConditionalRender(function_pointer);
/*  978:     */  }
/*  979:     */  
/*  986:     */  static native void nglEndConditionalRender(long paramLong);
/*  987:     */  
/*  994:     */  public static ByteBuffer glMapBufferRange(int target, long offset, long length, int access, ByteBuffer old_buffer)
/*  995:     */  {
/*  996: 996 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  997: 997 */    long function_pointer = caps.glMapBufferRange;
/*  998: 998 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  999: 999 */    if (old_buffer != null)
/* 1000:1000 */      BufferChecks.checkDirect(old_buffer);
/* 1001:1001 */    ByteBuffer __result = nglMapBufferRange(target, offset, length, access, old_buffer, function_pointer);
/* 1002:1002 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 1003:     */  }
/* 1004:     */  
/* 1005:     */  static native ByteBuffer nglMapBufferRange(int paramInt1, long paramLong1, long paramLong2, int paramInt2, ByteBuffer paramByteBuffer, long paramLong3);
/* 1006:     */  
/* 1007:1007 */  public static void glFlushMappedBufferRange(int target, long offset, long length) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1008:1008 */    long function_pointer = caps.glFlushMappedBufferRange;
/* 1009:1009 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1010:1010 */    nglFlushMappedBufferRange(target, offset, length, function_pointer);
/* 1011:     */  }
/* 1012:     */  
/* 1013:     */  static native void nglFlushMappedBufferRange(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 1014:     */  
/* 1015:1015 */  public static void glClampColor(int target, int clamp) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1016:1016 */    long function_pointer = caps.glClampColor;
/* 1017:1017 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1018:1018 */    nglClampColor(target, clamp, function_pointer);
/* 1019:     */  }
/* 1020:     */  
/* 1021:     */  static native void nglClampColor(int paramInt1, int paramInt2, long paramLong);
/* 1022:     */  
/* 1023:1023 */  public static boolean glIsRenderbuffer(int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1024:1024 */    long function_pointer = caps.glIsRenderbuffer;
/* 1025:1025 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1026:1026 */    boolean __result = nglIsRenderbuffer(renderbuffer, function_pointer);
/* 1027:1027 */    return __result;
/* 1028:     */  }
/* 1029:     */  
/* 1030:     */  static native boolean nglIsRenderbuffer(int paramInt, long paramLong);
/* 1031:     */  
/* 1032:1032 */  public static void glBindRenderbuffer(int target, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1033:1033 */    long function_pointer = caps.glBindRenderbuffer;
/* 1034:1034 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1035:1035 */    nglBindRenderbuffer(target, renderbuffer, function_pointer);
/* 1036:     */  }
/* 1037:     */  
/* 1038:     */  static native void nglBindRenderbuffer(int paramInt1, int paramInt2, long paramLong);
/* 1039:     */  
/* 1040:1040 */  public static void glDeleteRenderbuffers(IntBuffer renderbuffers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1041:1041 */    long function_pointer = caps.glDeleteRenderbuffers;
/* 1042:1042 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1043:1043 */    BufferChecks.checkDirect(renderbuffers);
/* 1044:1044 */    nglDeleteRenderbuffers(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 1045:     */  }
/* 1046:     */  
/* 1047:     */  static native void nglDeleteRenderbuffers(int paramInt, long paramLong1, long paramLong2);
/* 1048:     */  
/* 1049:     */  public static void glDeleteRenderbuffers(int renderbuffer) {
/* 1050:1050 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1051:1051 */    long function_pointer = caps.glDeleteRenderbuffers;
/* 1052:1052 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1053:1053 */    nglDeleteRenderbuffers(1, APIUtil.getInt(caps, renderbuffer), function_pointer);
/* 1054:     */  }
/* 1055:     */  
/* 1056:     */  public static void glGenRenderbuffers(IntBuffer renderbuffers) {
/* 1057:1057 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1058:1058 */    long function_pointer = caps.glGenRenderbuffers;
/* 1059:1059 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1060:1060 */    BufferChecks.checkDirect(renderbuffers);
/* 1061:1061 */    nglGenRenderbuffers(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 1062:     */  }
/* 1063:     */  
/* 1064:     */  static native void nglGenRenderbuffers(int paramInt, long paramLong1, long paramLong2);
/* 1065:     */  
/* 1066:     */  public static int glGenRenderbuffers() {
/* 1067:1067 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1068:1068 */    long function_pointer = caps.glGenRenderbuffers;
/* 1069:1069 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1070:1070 */    IntBuffer renderbuffers = APIUtil.getBufferInt(caps);
/* 1071:1071 */    nglGenRenderbuffers(1, MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 1072:1072 */    return renderbuffers.get(0);
/* 1073:     */  }
/* 1074:     */  
/* 1075:     */  public static void glRenderbufferStorage(int target, int internalformat, int width, int height) {
/* 1076:1076 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1077:1077 */    long function_pointer = caps.glRenderbufferStorage;
/* 1078:1078 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1079:1079 */    nglRenderbufferStorage(target, internalformat, width, height, function_pointer);
/* 1080:     */  }
/* 1081:     */  
/* 1082:     */  static native void nglRenderbufferStorage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 1083:     */  
/* 1084:1084 */  public static void glGetRenderbufferParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1085:1085 */    long function_pointer = caps.glGetRenderbufferParameteriv;
/* 1086:1086 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1087:1087 */    BufferChecks.checkBuffer(params, 4);
/* 1088:1088 */    nglGetRenderbufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1089:     */  }
/* 1090:     */  
/* 1093:     */  static native void nglGetRenderbufferParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1094:     */  
/* 1096:     */  @Deprecated
/* 1097:     */  public static int glGetRenderbufferParameter(int target, int pname)
/* 1098:     */  {
/* 1099:1099 */    return glGetRenderbufferParameteri(target, pname);
/* 1100:     */  }
/* 1101:     */  
/* 1102:     */  public static int glGetRenderbufferParameteri(int target, int pname)
/* 1103:     */  {
/* 1104:1104 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1105:1105 */    long function_pointer = caps.glGetRenderbufferParameteriv;
/* 1106:1106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1107:1107 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1108:1108 */    nglGetRenderbufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1109:1109 */    return params.get(0);
/* 1110:     */  }
/* 1111:     */  
/* 1112:     */  public static boolean glIsFramebuffer(int framebuffer) {
/* 1113:1113 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1114:1114 */    long function_pointer = caps.glIsFramebuffer;
/* 1115:1115 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1116:1116 */    boolean __result = nglIsFramebuffer(framebuffer, function_pointer);
/* 1117:1117 */    return __result;
/* 1118:     */  }
/* 1119:     */  
/* 1120:     */  static native boolean nglIsFramebuffer(int paramInt, long paramLong);
/* 1121:     */  
/* 1122:1122 */  public static void glBindFramebuffer(int target, int framebuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1123:1123 */    long function_pointer = caps.glBindFramebuffer;
/* 1124:1124 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1125:1125 */    nglBindFramebuffer(target, framebuffer, function_pointer);
/* 1126:     */  }
/* 1127:     */  
/* 1128:     */  static native void nglBindFramebuffer(int paramInt1, int paramInt2, long paramLong);
/* 1129:     */  
/* 1130:1130 */  public static void glDeleteFramebuffers(IntBuffer framebuffers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1131:1131 */    long function_pointer = caps.glDeleteFramebuffers;
/* 1132:1132 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1133:1133 */    BufferChecks.checkDirect(framebuffers);
/* 1134:1134 */    nglDeleteFramebuffers(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/* 1135:     */  }
/* 1136:     */  
/* 1137:     */  static native void nglDeleteFramebuffers(int paramInt, long paramLong1, long paramLong2);
/* 1138:     */  
/* 1139:     */  public static void glDeleteFramebuffers(int framebuffer) {
/* 1140:1140 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1141:1141 */    long function_pointer = caps.glDeleteFramebuffers;
/* 1142:1142 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1143:1143 */    nglDeleteFramebuffers(1, APIUtil.getInt(caps, framebuffer), function_pointer);
/* 1144:     */  }
/* 1145:     */  
/* 1146:     */  public static void glGenFramebuffers(IntBuffer framebuffers) {
/* 1147:1147 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1148:1148 */    long function_pointer = caps.glGenFramebuffers;
/* 1149:1149 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1150:1150 */    BufferChecks.checkDirect(framebuffers);
/* 1151:1151 */    nglGenFramebuffers(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/* 1152:     */  }
/* 1153:     */  
/* 1154:     */  static native void nglGenFramebuffers(int paramInt, long paramLong1, long paramLong2);
/* 1155:     */  
/* 1156:     */  public static int glGenFramebuffers() {
/* 1157:1157 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1158:1158 */    long function_pointer = caps.glGenFramebuffers;
/* 1159:1159 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1160:1160 */    IntBuffer framebuffers = APIUtil.getBufferInt(caps);
/* 1161:1161 */    nglGenFramebuffers(1, MemoryUtil.getAddress(framebuffers), function_pointer);
/* 1162:1162 */    return framebuffers.get(0);
/* 1163:     */  }
/* 1164:     */  
/* 1165:     */  public static int glCheckFramebufferStatus(int target) {
/* 1166:1166 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1167:1167 */    long function_pointer = caps.glCheckFramebufferStatus;
/* 1168:1168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1169:1169 */    int __result = nglCheckFramebufferStatus(target, function_pointer);
/* 1170:1170 */    return __result;
/* 1171:     */  }
/* 1172:     */  
/* 1173:     */  static native int nglCheckFramebufferStatus(int paramInt, long paramLong);
/* 1174:     */  
/* 1175:1175 */  public static void glFramebufferTexture1D(int target, int attachment, int textarget, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1176:1176 */    long function_pointer = caps.glFramebufferTexture1D;
/* 1177:1177 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1178:1178 */    nglFramebufferTexture1D(target, attachment, textarget, texture, level, function_pointer);
/* 1179:     */  }
/* 1180:     */  
/* 1181:     */  static native void nglFramebufferTexture1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 1182:     */  
/* 1183:1183 */  public static void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1184:1184 */    long function_pointer = caps.glFramebufferTexture2D;
/* 1185:1185 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1186:1186 */    nglFramebufferTexture2D(target, attachment, textarget, texture, level, function_pointer);
/* 1187:     */  }
/* 1188:     */  
/* 1189:     */  static native void nglFramebufferTexture2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 1190:     */  
/* 1191:1191 */  public static void glFramebufferTexture3D(int target, int attachment, int textarget, int texture, int level, int zoffset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1192:1192 */    long function_pointer = caps.glFramebufferTexture3D;
/* 1193:1193 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1194:1194 */    nglFramebufferTexture3D(target, attachment, textarget, texture, level, zoffset, function_pointer);
/* 1195:     */  }
/* 1196:     */  
/* 1197:     */  static native void nglFramebufferTexture3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 1198:     */  
/* 1199:1199 */  public static void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1200:1200 */    long function_pointer = caps.glFramebufferRenderbuffer;
/* 1201:1201 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1202:1202 */    nglFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer, function_pointer);
/* 1203:     */  }
/* 1204:     */  
/* 1205:     */  static native void nglFramebufferRenderbuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 1206:     */  
/* 1207:1207 */  public static void glGetFramebufferAttachmentParameter(int target, int attachment, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1208:1208 */    long function_pointer = caps.glGetFramebufferAttachmentParameteriv;
/* 1209:1209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1210:1210 */    BufferChecks.checkBuffer(params, 4);
/* 1211:1211 */    nglGetFramebufferAttachmentParameteriv(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1212:     */  }
/* 1213:     */  
/* 1216:     */  static native void nglGetFramebufferAttachmentParameteriv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1217:     */  
/* 1219:     */  @Deprecated
/* 1220:     */  public static int glGetFramebufferAttachmentParameter(int target, int attachment, int pname)
/* 1221:     */  {
/* 1222:1222 */    return glGetFramebufferAttachmentParameteri(target, attachment, pname);
/* 1223:     */  }
/* 1224:     */  
/* 1225:     */  public static int glGetFramebufferAttachmentParameteri(int target, int attachment, int pname)
/* 1226:     */  {
/* 1227:1227 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1228:1228 */    long function_pointer = caps.glGetFramebufferAttachmentParameteriv;
/* 1229:1229 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1230:1230 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1231:1231 */    nglGetFramebufferAttachmentParameteriv(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1232:1232 */    return params.get(0);
/* 1233:     */  }
/* 1234:     */  
/* 1235:     */  public static void glGenerateMipmap(int target) {
/* 1236:1236 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1237:1237 */    long function_pointer = caps.glGenerateMipmap;
/* 1238:1238 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1239:1239 */    nglGenerateMipmap(target, function_pointer);
/* 1240:     */  }
/* 1241:     */  
/* 1243:     */  static native void nglGenerateMipmap(int paramInt, long paramLong);
/* 1244:     */  
/* 1246:     */  public static void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height)
/* 1247:     */  {
/* 1248:1248 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1249:1249 */    long function_pointer = caps.glRenderbufferStorageMultisample;
/* 1250:1250 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1251:1251 */    nglRenderbufferStorageMultisample(target, samples, internalformat, width, height, function_pointer);
/* 1252:     */  }
/* 1253:     */  
/* 1262:     */  static native void nglRenderbufferStorageMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 1263:     */  
/* 1271:     */  public static void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter)
/* 1272:     */  {
/* 1273:1273 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1274:1274 */    long function_pointer = caps.glBlitFramebuffer;
/* 1275:1275 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1276:1276 */    nglBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter, function_pointer);
/* 1277:     */  }
/* 1278:     */  
/* 1279:     */  static native void nglBlitFramebuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/* 1280:     */  
/* 1281:1281 */  public static void glTexParameterI(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1282:1282 */    long function_pointer = caps.glTexParameterIiv;
/* 1283:1283 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1284:1284 */    BufferChecks.checkBuffer(params, 4);
/* 1285:1285 */    nglTexParameterIiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1286:     */  }
/* 1287:     */  
/* 1288:     */  static native void nglTexParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1289:     */  
/* 1290:     */  public static void glTexParameterIi(int target, int pname, int param) {
/* 1291:1291 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1292:1292 */    long function_pointer = caps.glTexParameterIiv;
/* 1293:1293 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1294:1294 */    nglTexParameterIiv(target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 1295:     */  }
/* 1296:     */  
/* 1297:     */  public static void glTexParameterIu(int target, int pname, IntBuffer params) {
/* 1298:1298 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1299:1299 */    long function_pointer = caps.glTexParameterIuiv;
/* 1300:1300 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1301:1301 */    BufferChecks.checkBuffer(params, 4);
/* 1302:1302 */    nglTexParameterIuiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1303:     */  }
/* 1304:     */  
/* 1305:     */  static native void nglTexParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1306:     */  
/* 1307:     */  public static void glTexParameterIui(int target, int pname, int param) {
/* 1308:1308 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1309:1309 */    long function_pointer = caps.glTexParameterIuiv;
/* 1310:1310 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1311:1311 */    nglTexParameterIuiv(target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 1312:     */  }
/* 1313:     */  
/* 1314:     */  public static void glGetTexParameterI(int target, int pname, IntBuffer params) {
/* 1315:1315 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1316:1316 */    long function_pointer = caps.glGetTexParameterIiv;
/* 1317:1317 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1318:1318 */    BufferChecks.checkBuffer(params, 4);
/* 1319:1319 */    nglGetTexParameterIiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1320:     */  }
/* 1321:     */  
/* 1322:     */  static native void nglGetTexParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1323:     */  
/* 1324:     */  public static int glGetTexParameterIi(int target, int pname) {
/* 1325:1325 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1326:1326 */    long function_pointer = caps.glGetTexParameterIiv;
/* 1327:1327 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1328:1328 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1329:1329 */    nglGetTexParameterIiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1330:1330 */    return params.get(0);
/* 1331:     */  }
/* 1332:     */  
/* 1333:     */  public static void glGetTexParameterIu(int target, int pname, IntBuffer params) {
/* 1334:1334 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1335:1335 */    long function_pointer = caps.glGetTexParameterIuiv;
/* 1336:1336 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1337:1337 */    BufferChecks.checkBuffer(params, 4);
/* 1338:1338 */    nglGetTexParameterIuiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1339:     */  }
/* 1340:     */  
/* 1341:     */  static native void nglGetTexParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1342:     */  
/* 1343:     */  public static int glGetTexParameterIui(int target, int pname) {
/* 1344:1344 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1345:1345 */    long function_pointer = caps.glGetTexParameterIuiv;
/* 1346:1346 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1347:1347 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1348:1348 */    nglGetTexParameterIuiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1349:1349 */    return params.get(0);
/* 1350:     */  }
/* 1351:     */  
/* 1352:     */  public static void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
/* 1353:1353 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1354:1354 */    long function_pointer = caps.glFramebufferTextureLayer;
/* 1355:1355 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1356:1356 */    nglFramebufferTextureLayer(target, attachment, texture, level, layer, function_pointer);
/* 1357:     */  }
/* 1358:     */  
/* 1359:     */  static native void nglFramebufferTextureLayer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 1360:     */  
/* 1361:1361 */  public static void glColorMaski(int buf, boolean r, boolean g, boolean b, boolean a) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1362:1362 */    long function_pointer = caps.glColorMaski;
/* 1363:1363 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1364:1364 */    nglColorMaski(buf, r, g, b, a, function_pointer);
/* 1365:     */  }
/* 1366:     */  
/* 1367:     */  static native void nglColorMaski(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, long paramLong);
/* 1368:     */  
/* 1369:1369 */  public static void glGetBoolean(int value, int index, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1370:1370 */    long function_pointer = caps.glGetBooleani_v;
/* 1371:1371 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1372:1372 */    BufferChecks.checkBuffer(data, 4);
/* 1373:1373 */    nglGetBooleani_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 1374:     */  }
/* 1375:     */  
/* 1376:     */  static native void nglGetBooleani_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1377:     */  
/* 1378:     */  public static boolean glGetBoolean(int value, int index) {
/* 1379:1379 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1380:1380 */    long function_pointer = caps.glGetBooleani_v;
/* 1381:1381 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1382:1382 */    ByteBuffer data = APIUtil.getBufferByte(caps, 1);
/* 1383:1383 */    nglGetBooleani_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 1384:1384 */    return data.get(0) == 1;
/* 1385:     */  }
/* 1386:     */  
/* 1387:     */  public static void glGetInteger(int value, int index, IntBuffer data) {
/* 1388:1388 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1389:1389 */    long function_pointer = caps.glGetIntegeri_v;
/* 1390:1390 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1391:1391 */    BufferChecks.checkBuffer(data, 4);
/* 1392:1392 */    nglGetIntegeri_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 1393:     */  }
/* 1394:     */  
/* 1395:     */  static native void nglGetIntegeri_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1396:     */  
/* 1397:     */  public static int glGetInteger(int value, int index) {
/* 1398:1398 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1399:1399 */    long function_pointer = caps.glGetIntegeri_v;
/* 1400:1400 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1401:1401 */    IntBuffer data = APIUtil.getBufferInt(caps);
/* 1402:1402 */    nglGetIntegeri_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 1403:1403 */    return data.get(0);
/* 1404:     */  }
/* 1405:     */  
/* 1406:     */  public static void glEnablei(int target, int index) {
/* 1407:1407 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1408:1408 */    long function_pointer = caps.glEnablei;
/* 1409:1409 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1410:1410 */    nglEnablei(target, index, function_pointer);
/* 1411:     */  }
/* 1412:     */  
/* 1413:     */  static native void nglEnablei(int paramInt1, int paramInt2, long paramLong);
/* 1414:     */  
/* 1415:1415 */  public static void glDisablei(int target, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1416:1416 */    long function_pointer = caps.glDisablei;
/* 1417:1417 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1418:1418 */    nglDisablei(target, index, function_pointer);
/* 1419:     */  }
/* 1420:     */  
/* 1421:     */  static native void nglDisablei(int paramInt1, int paramInt2, long paramLong);
/* 1422:     */  
/* 1423:1423 */  public static boolean glIsEnabledi(int target, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1424:1424 */    long function_pointer = caps.glIsEnabledi;
/* 1425:1425 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1426:1426 */    boolean __result = nglIsEnabledi(target, index, function_pointer);
/* 1427:1427 */    return __result;
/* 1428:     */  }
/* 1429:     */  
/* 1430:     */  static native boolean nglIsEnabledi(int paramInt1, int paramInt2, long paramLong);
/* 1431:     */  
/* 1432:1432 */  public static void glBindBufferRange(int target, int index, int buffer, long offset, long size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1433:1433 */    long function_pointer = caps.glBindBufferRange;
/* 1434:1434 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1435:1435 */    nglBindBufferRange(target, index, buffer, offset, size, function_pointer);
/* 1436:     */  }
/* 1437:     */  
/* 1438:     */  static native void nglBindBufferRange(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/* 1439:     */  
/* 1440:1440 */  public static void glBindBufferBase(int target, int index, int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1441:1441 */    long function_pointer = caps.glBindBufferBase;
/* 1442:1442 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1443:1443 */    nglBindBufferBase(target, index, buffer, function_pointer);
/* 1444:     */  }
/* 1445:     */  
/* 1446:     */  static native void nglBindBufferBase(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 1447:     */  
/* 1448:1448 */  public static void glBeginTransformFeedback(int primitiveMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1449:1449 */    long function_pointer = caps.glBeginTransformFeedback;
/* 1450:1450 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1451:1451 */    nglBeginTransformFeedback(primitiveMode, function_pointer);
/* 1452:     */  }
/* 1453:     */  
/* 1454:     */  static native void nglBeginTransformFeedback(int paramInt, long paramLong);
/* 1455:     */  
/* 1456:1456 */  public static void glEndTransformFeedback() { ContextCapabilities caps = GLContext.getCapabilities();
/* 1457:1457 */    long function_pointer = caps.glEndTransformFeedback;
/* 1458:1458 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1459:1459 */    nglEndTransformFeedback(function_pointer);
/* 1460:     */  }
/* 1461:     */  
/* 1462:     */  static native void nglEndTransformFeedback(long paramLong);
/* 1463:     */  
/* 1464:1464 */  public static void glTransformFeedbackVaryings(int program, int count, ByteBuffer varyings, int bufferMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1465:1465 */    long function_pointer = caps.glTransformFeedbackVaryings;
/* 1466:1466 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1467:1467 */    BufferChecks.checkDirect(varyings);
/* 1468:1468 */    BufferChecks.checkNullTerminated(varyings, count);
/* 1469:1469 */    nglTransformFeedbackVaryings(program, count, MemoryUtil.getAddress(varyings), bufferMode, function_pointer);
/* 1470:     */  }
/* 1471:     */  
/* 1472:     */  static native void nglTransformFeedbackVaryings(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/* 1473:     */  
/* 1474:     */  public static void glTransformFeedbackVaryings(int program, CharSequence[] varyings, int bufferMode) {
/* 1475:1475 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1476:1476 */    long function_pointer = caps.glTransformFeedbackVaryings;
/* 1477:1477 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1478:1478 */    BufferChecks.checkArray(varyings);
/* 1479:1479 */    nglTransformFeedbackVaryings(program, varyings.length, APIUtil.getBufferNT(caps, varyings), bufferMode, function_pointer);
/* 1480:     */  }
/* 1481:     */  
/* 1482:     */  public static void glGetTransformFeedbackVarying(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 1483:1483 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1484:1484 */    long function_pointer = caps.glGetTransformFeedbackVarying;
/* 1485:1485 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1486:1486 */    if (length != null)
/* 1487:1487 */      BufferChecks.checkBuffer(length, 1);
/* 1488:1488 */    BufferChecks.checkBuffer(size, 1);
/* 1489:1489 */    BufferChecks.checkBuffer(type, 1);
/* 1490:1490 */    BufferChecks.checkDirect(name);
/* 1491:1491 */    nglGetTransformFeedbackVarying(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 1492:     */  }
/* 1493:     */  
/* 1494:     */  static native void nglGetTransformFeedbackVarying(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 1495:     */  
/* 1496:     */  public static String glGetTransformFeedbackVarying(int program, int index, int bufSize, IntBuffer size, IntBuffer type) {
/* 1497:1497 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1498:1498 */    long function_pointer = caps.glGetTransformFeedbackVarying;
/* 1499:1499 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1500:1500 */    BufferChecks.checkBuffer(size, 1);
/* 1501:1501 */    BufferChecks.checkBuffer(type, 1);
/* 1502:1502 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 1503:1503 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 1504:1504 */    nglGetTransformFeedbackVarying(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 1505:1505 */    name.limit(name_length.get(0));
/* 1506:1506 */    return APIUtil.getString(caps, name);
/* 1507:     */  }
/* 1508:     */  
/* 1509:     */  public static void glBindVertexArray(int array) {
/* 1510:1510 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1511:1511 */    long function_pointer = caps.glBindVertexArray;
/* 1512:1512 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1513:1513 */    StateTracker.bindVAO(caps, array);
/* 1514:1514 */    nglBindVertexArray(array, function_pointer);
/* 1515:     */  }
/* 1516:     */  
/* 1517:     */  static native void nglBindVertexArray(int paramInt, long paramLong);
/* 1518:     */  
/* 1519:1519 */  public static void glDeleteVertexArrays(IntBuffer arrays) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1520:1520 */    long function_pointer = caps.glDeleteVertexArrays;
/* 1521:1521 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1522:1522 */    StateTracker.deleteVAO(caps, arrays);
/* 1523:1523 */    BufferChecks.checkDirect(arrays);
/* 1524:1524 */    nglDeleteVertexArrays(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/* 1525:     */  }
/* 1526:     */  
/* 1527:     */  static native void nglDeleteVertexArrays(int paramInt, long paramLong1, long paramLong2);
/* 1528:     */  
/* 1529:     */  public static void glDeleteVertexArrays(int array) {
/* 1530:1530 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1531:1531 */    long function_pointer = caps.glDeleteVertexArrays;
/* 1532:1532 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1533:1533 */    StateTracker.deleteVAO(caps, array);
/* 1534:1534 */    nglDeleteVertexArrays(1, APIUtil.getInt(caps, array), function_pointer);
/* 1535:     */  }
/* 1536:     */  
/* 1537:     */  public static void glGenVertexArrays(IntBuffer arrays) {
/* 1538:1538 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1539:1539 */    long function_pointer = caps.glGenVertexArrays;
/* 1540:1540 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1541:1541 */    BufferChecks.checkDirect(arrays);
/* 1542:1542 */    nglGenVertexArrays(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/* 1543:     */  }
/* 1544:     */  
/* 1545:     */  static native void nglGenVertexArrays(int paramInt, long paramLong1, long paramLong2);
/* 1546:     */  
/* 1547:     */  public static int glGenVertexArrays() {
/* 1548:1548 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1549:1549 */    long function_pointer = caps.glGenVertexArrays;
/* 1550:1550 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1551:1551 */    IntBuffer arrays = APIUtil.getBufferInt(caps);
/* 1552:1552 */    nglGenVertexArrays(1, MemoryUtil.getAddress(arrays), function_pointer);
/* 1553:1553 */    return arrays.get(0);
/* 1554:     */  }
/* 1555:     */  
/* 1556:     */  public static boolean glIsVertexArray(int array) {
/* 1557:1557 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1558:1558 */    long function_pointer = caps.glIsVertexArray;
/* 1559:1559 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1560:1560 */    boolean __result = nglIsVertexArray(array, function_pointer);
/* 1561:1561 */    return __result;
/* 1562:     */  }
/* 1563:     */  
/* 1564:     */  static native boolean nglIsVertexArray(int paramInt, long paramLong);
/* 1565:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL30
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */