/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class GL30
/*      */ {
/*      */   public static final int GL_MAJOR_VERSION = 33307;
/*      */   public static final int GL_MINOR_VERSION = 33308;
/*      */   public static final int GL_NUM_EXTENSIONS = 33309;
/*      */   public static final int GL_CONTEXT_FLAGS = 33310;
/*      */   public static final int GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 1;
/*      */   public static final int GL_DEPTH_BUFFER = 33315;
/*      */   public static final int GL_STENCIL_BUFFER = 33316;
/*      */   public static final int GL_COMPRESSED_RED = 33317;
/*      */   public static final int GL_COMPRESSED_RG = 33318;
/*      */   public static final int GL_COMPARE_REF_TO_TEXTURE = 34894;
/*      */   public static final int GL_CLIP_DISTANCE0 = 12288;
/*      */   public static final int GL_CLIP_DISTANCE1 = 12289;
/*      */   public static final int GL_CLIP_DISTANCE2 = 12290;
/*      */   public static final int GL_CLIP_DISTANCE3 = 12291;
/*      */   public static final int GL_CLIP_DISTANCE4 = 12292;
/*      */   public static final int GL_CLIP_DISTANCE5 = 12293;
/*      */   public static final int GL_CLIP_DISTANCE6 = 12294;
/*      */   public static final int GL_CLIP_DISTANCE7 = 12295;
/*      */   public static final int GL_MAX_CLIP_DISTANCES = 3378;
/*      */   public static final int GL_MAX_VARYING_COMPONENTS = 35659;
/*      */   public static final int GL_BUFFER_ACCESS_FLAGS = 37151;
/*      */   public static final int GL_BUFFER_MAP_LENGTH = 37152;
/*      */   public static final int GL_BUFFER_MAP_OFFSET = 37153;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER = 35069;
/*      */   public static final int GL_SAMPLER_BUFFER = 36290;
/*      */   public static final int GL_SAMPLER_CUBE_SHADOW = 36293;
/*      */   public static final int GL_UNSIGNED_INT_VEC2 = 36294;
/*      */   public static final int GL_UNSIGNED_INT_VEC3 = 36295;
/*      */   public static final int GL_UNSIGNED_INT_VEC4 = 36296;
/*      */   public static final int GL_INT_SAMPLER_1D = 36297;
/*      */   public static final int GL_INT_SAMPLER_2D = 36298;
/*      */   public static final int GL_INT_SAMPLER_3D = 36299;
/*      */   public static final int GL_INT_SAMPLER_CUBE = 36300;
/*      */   public static final int GL_INT_SAMPLER_2D_RECT = 36301;
/*      */   public static final int GL_INT_SAMPLER_1D_ARRAY = 36302;
/*      */   public static final int GL_INT_SAMPLER_2D_ARRAY = 36303;
/*      */   public static final int GL_INT_SAMPLER_BUFFER = 36304;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_1D = 36305;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_2D = 36306;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_3D = 36307;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_CUBE = 36308;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT = 36309;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 36310;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 36311;
/*      */   public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER = 36312;
/*      */   public static final int GL_MIN_PROGRAM_TEXEL_OFFSET = 35076;
/*      */   public static final int GL_MAX_PROGRAM_TEXEL_OFFSET = 35077;
/*      */   public static final int GL_QUERY_WAIT = 36371;
/*      */   public static final int GL_QUERY_NO_WAIT = 36372;
/*      */   public static final int GL_QUERY_BY_REGION_WAIT = 36373;
/*      */   public static final int GL_QUERY_BY_REGION_NO_WAIT = 36374;
/*      */   public static final int GL_MAP_READ_BIT = 1;
/*      */   public static final int GL_MAP_WRITE_BIT = 2;
/*      */   public static final int GL_MAP_INVALIDATE_RANGE_BIT = 4;
/*      */   public static final int GL_MAP_INVALIDATE_BUFFER_BIT = 8;
/*      */   public static final int GL_MAP_FLUSH_EXPLICIT_BIT = 16;
/*      */   public static final int GL_MAP_UNSYNCHRONIZED_BIT = 32;
/*      */   public static final int GL_CLAMP_VERTEX_COLOR = 35098;
/*      */   public static final int GL_CLAMP_FRAGMENT_COLOR = 35099;
/*      */   public static final int GL_CLAMP_READ_COLOR = 35100;
/*      */   public static final int GL_FIXED_ONLY = 35101;
/*      */   public static final int GL_DEPTH_COMPONENT32F = 36012;
/*      */   public static final int GL_DEPTH32F_STENCIL8 = 36013;
/*      */   public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 36269;
/*      */   public static final int GL_TEXTURE_RED_TYPE = 35856;
/*      */   public static final int GL_TEXTURE_GREEN_TYPE = 35857;
/*      */   public static final int GL_TEXTURE_BLUE_TYPE = 35858;
/*      */   public static final int GL_TEXTURE_ALPHA_TYPE = 35859;
/*      */   public static final int GL_TEXTURE_LUMINANCE_TYPE = 35860;
/*      */   public static final int GL_TEXTURE_INTENSITY_TYPE = 35861;
/*      */   public static final int GL_TEXTURE_DEPTH_TYPE = 35862;
/*      */   public static final int GL_UNSIGNED_NORMALIZED = 35863;
/*      */   public static final int GL_RGBA32F = 34836;
/*      */   public static final int GL_RGB32F = 34837;
/*      */   public static final int GL_ALPHA32F = 34838;
/*      */   public static final int GL_RGBA16F = 34842;
/*      */   public static final int GL_RGB16F = 34843;
/*      */   public static final int GL_ALPHA16F = 34844;
/*      */   public static final int GL_R11F_G11F_B10F = 35898;
/*      */   public static final int GL_UNSIGNED_INT_10F_11F_11F_REV = 35899;
/*      */   public static final int GL_RGB9_E5 = 35901;
/*      */   public static final int GL_UNSIGNED_INT_5_9_9_9_REV = 35902;
/*      */   public static final int GL_TEXTURE_SHARED_SIZE = 35903;
/*      */   public static final int GL_FRAMEBUFFER = 36160;
/*      */   public static final int GL_READ_FRAMEBUFFER = 36008;
/*      */   public static final int GL_DRAW_FRAMEBUFFER = 36009;
/*      */   public static final int GL_RENDERBUFFER = 36161;
/*      */   public static final int GL_STENCIL_INDEX1 = 36166;
/*      */   public static final int GL_STENCIL_INDEX4 = 36167;
/*      */   public static final int GL_STENCIL_INDEX8 = 36168;
/*      */   public static final int GL_STENCIL_INDEX16 = 36169;
/*      */   public static final int GL_RENDERBUFFER_WIDTH = 36162;
/*      */   public static final int GL_RENDERBUFFER_HEIGHT = 36163;
/*      */   public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
/*      */   public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
/*      */   public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
/*      */   public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
/*      */   public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
/*      */   public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
/*      */   public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 33296;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 33297;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 33298;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 33299;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 33300;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 33301;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 33302;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 33303;
/*      */   public static final int GL_FRAMEBUFFER_DEFAULT = 33304;
/*      */   public static final int GL_INDEX = 33314;
/*      */   public static final int GL_COLOR_ATTACHMENT0 = 36064;
/*      */   public static final int GL_COLOR_ATTACHMENT1 = 36065;
/*      */   public static final int GL_COLOR_ATTACHMENT2 = 36066;
/*      */   public static final int GL_COLOR_ATTACHMENT3 = 36067;
/*      */   public static final int GL_COLOR_ATTACHMENT4 = 36068;
/*      */   public static final int GL_COLOR_ATTACHMENT5 = 36069;
/*      */   public static final int GL_COLOR_ATTACHMENT6 = 36070;
/*      */   public static final int GL_COLOR_ATTACHMENT7 = 36071;
/*      */   public static final int GL_COLOR_ATTACHMENT8 = 36072;
/*      */   public static final int GL_COLOR_ATTACHMENT9 = 36073;
/*      */   public static final int GL_COLOR_ATTACHMENT10 = 36074;
/*      */   public static final int GL_COLOR_ATTACHMENT11 = 36075;
/*      */   public static final int GL_COLOR_ATTACHMENT12 = 36076;
/*      */   public static final int GL_COLOR_ATTACHMENT13 = 36077;
/*      */   public static final int GL_COLOR_ATTACHMENT14 = 36078;
/*      */   public static final int GL_COLOR_ATTACHMENT15 = 36079;
/*      */   public static final int GL_DEPTH_ATTACHMENT = 36096;
/*      */   public static final int GL_STENCIL_ATTACHMENT = 36128;
/*      */   public static final int GL_DEPTH_STENCIL_ATTACHMENT = 33306;
/*      */   public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
/*      */   public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
/*      */   public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
/*      */   public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 36059;
/*      */   public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 36060;
/*      */   public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
/*      */   public static final int GL_FRAMEBUFFER_UNDEFINED = 33305;
/*      */   public static final int GL_FRAMEBUFFER_BINDING = 36006;
/*      */   public static final int GL_RENDERBUFFER_BINDING = 36007;
/*      */   public static final int GL_MAX_COLOR_ATTACHMENTS = 36063;
/*      */   public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
/*      */   public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
/*      */   public static final int GL_HALF_FLOAT = 5131;
/*      */   public static final int GL_RENDERBUFFER_SAMPLES = 36011;
/*      */   public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 36182;
/*      */   public static final int GL_MAX_SAMPLES = 36183;
/*      */   public static final int GL_DRAW_FRAMEBUFFER_BINDING = 36006;
/*      */   public static final int GL_READ_FRAMEBUFFER_BINDING = 36010;
/*      */   public static final int GL_RGBA_INTEGER_MODE = 36254;
/*      */   public static final int GL_RGBA32UI = 36208;
/*      */   public static final int GL_RGB32UI = 36209;
/*      */   public static final int GL_ALPHA32UI = 36210;
/*      */   public static final int GL_RGBA16UI = 36214;
/*      */   public static final int GL_RGB16UI = 36215;
/*      */   public static final int GL_ALPHA16UI = 36216;
/*      */   public static final int GL_RGBA8UI = 36220;
/*      */   public static final int GL_RGB8UI = 36221;
/*      */   public static final int GL_ALPHA8UI = 36222;
/*      */   public static final int GL_RGBA32I = 36226;
/*      */   public static final int GL_RGB32I = 36227;
/*      */   public static final int GL_ALPHA32I = 36228;
/*      */   public static final int GL_RGBA16I = 36232;
/*      */   public static final int GL_RGB16I = 36233;
/*      */   public static final int GL_ALPHA16I = 36234;
/*      */   public static final int GL_RGBA8I = 36238;
/*      */   public static final int GL_RGB8I = 36239;
/*      */   public static final int GL_ALPHA8I = 36240;
/*      */   public static final int GL_RED_INTEGER = 36244;
/*      */   public static final int GL_GREEN_INTEGER = 36245;
/*      */   public static final int GL_BLUE_INTEGER = 36246;
/*      */   public static final int GL_ALPHA_INTEGER = 36247;
/*      */   public static final int GL_RGB_INTEGER = 36248;
/*      */   public static final int GL_RGBA_INTEGER = 36249;
/*      */   public static final int GL_BGR_INTEGER = 36250;
/*      */   public static final int GL_BGRA_INTEGER = 36251;
/*      */   public static final int GL_TEXTURE_1D_ARRAY = 35864;
/*      */   public static final int GL_TEXTURE_2D_ARRAY = 35866;
/*      */   public static final int GL_PROXY_TEXTURE_2D_ARRAY = 35867;
/*      */   public static final int GL_PROXY_TEXTURE_1D_ARRAY = 35865;
/*      */   public static final int GL_TEXTURE_BINDING_1D_ARRAY = 35868;
/*      */   public static final int GL_TEXTURE_BINDING_2D_ARRAY = 35869;
/*      */   public static final int GL_MAX_ARRAY_TEXTURE_LAYERS = 35071;
/*      */   public static final int GL_COMPARE_REF_DEPTH_TO_TEXTURE = 34894;
/*      */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
/*      */   public static final int GL_SAMPLER_1D_ARRAY = 36288;
/*      */   public static final int GL_SAMPLER_2D_ARRAY = 36289;
/*      */   public static final int GL_SAMPLER_1D_ARRAY_SHADOW = 36291;
/*      */   public static final int GL_SAMPLER_2D_ARRAY_SHADOW = 36292;
/*      */   public static final int GL_DEPTH_STENCIL = 34041;
/*      */   public static final int GL_UNSIGNED_INT_24_8 = 34042;
/*      */   public static final int GL_DEPTH24_STENCIL8 = 35056;
/*      */   public static final int GL_TEXTURE_STENCIL_SIZE = 35057;
/*      */   public static final int GL_COMPRESSED_RED_RGTC1 = 36283;
/*      */   public static final int GL_COMPRESSED_SIGNED_RED_RGTC1 = 36284;
/*      */   public static final int GL_COMPRESSED_RG_RGTC2 = 36285;
/*      */   public static final int GL_COMPRESSED_SIGNED_RG_RGTC2 = 36286;
/*      */   public static final int GL_R8 = 33321;
/*      */   public static final int GL_R16 = 33322;
/*      */   public static final int GL_RG8 = 33323;
/*      */   public static final int GL_RG16 = 33324;
/*      */   public static final int GL_R16F = 33325;
/*      */   public static final int GL_R32F = 33326;
/*      */   public static final int GL_RG16F = 33327;
/*      */   public static final int GL_RG32F = 33328;
/*      */   public static final int GL_R8I = 33329;
/*      */   public static final int GL_R8UI = 33330;
/*      */   public static final int GL_R16I = 33331;
/*      */   public static final int GL_R16UI = 33332;
/*      */   public static final int GL_R32I = 33333;
/*      */   public static final int GL_R32UI = 33334;
/*      */   public static final int GL_RG8I = 33335;
/*      */   public static final int GL_RG8UI = 33336;
/*      */   public static final int GL_RG16I = 33337;
/*      */   public static final int GL_RG16UI = 33338;
/*      */   public static final int GL_RG32I = 33339;
/*      */   public static final int GL_RG32UI = 33340;
/*      */   public static final int GL_RG = 33319;
/*      */   public static final int GL_RG_INTEGER = 33320;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER = 35982;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START = 35972;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 35973;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 35983;
/*      */   public static final int GL_INTERLEAVED_ATTRIBS = 35980;
/*      */   public static final int GL_SEPARATE_ATTRIBS = 35981;
/*      */   public static final int GL_PRIMITIVES_GENERATED = 35975;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 35976;
/*      */   public static final int GL_RASTERIZER_DISCARD = 35977;
/*      */   public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 35978;
/*      */   public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 35979;
/*      */   public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 35968;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_VARYINGS = 35971;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 35967;
/*      */   public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 35958;
/*      */   public static final int GL_VERTEX_ARRAY_BINDING = 34229;
/*      */   public static final int GL_FRAMEBUFFER_SRGB = 36281;
/*      */   public static final int GL_FRAMEBUFFER_SRGB_CAPABLE = 36282;
/*      */ 
/*      */   public static String glGetStringi(int name, int index)
/*      */   {
/*  580 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  581 */     long function_pointer = caps.glGetStringi;
/*  582 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  583 */     String __result = nglGetStringi(name, index, function_pointer);
/*  584 */     return __result;
/*      */   }
/*      */   static native String nglGetStringi(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glClearBuffer(int buffer, int drawbuffer, FloatBuffer value) {
/*  589 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  590 */     long function_pointer = caps.glClearBufferfv;
/*  591 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  592 */     BufferChecks.checkBuffer(value, 4);
/*  593 */     nglClearBufferfv(buffer, drawbuffer, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglClearBufferfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glClearBuffer(int buffer, int drawbuffer, IntBuffer value) {
/*  598 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  599 */     long function_pointer = caps.glClearBufferiv;
/*  600 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  601 */     BufferChecks.checkBuffer(value, 4);
/*  602 */     nglClearBufferiv(buffer, drawbuffer, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglClearBufferiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glClearBufferu(int buffer, int drawbuffer, IntBuffer value) {
/*  607 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  608 */     long function_pointer = caps.glClearBufferuiv;
/*  609 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  610 */     BufferChecks.checkBuffer(value, 4);
/*  611 */     nglClearBufferuiv(buffer, drawbuffer, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglClearBufferuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil) {
/*  616 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  617 */     long function_pointer = caps.glClearBufferfi;
/*  618 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  619 */     nglClearBufferfi(buffer, drawbuffer, depth, stencil, function_pointer);
/*      */   }
/*      */   static native void nglClearBufferfi(int paramInt1, int paramInt2, float paramFloat, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI1i(int index, int x) {
/*  624 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  625 */     long function_pointer = caps.glVertexAttribI1i;
/*  626 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  627 */     nglVertexAttribI1i(index, x, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI1i(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI2i(int index, int x, int y) {
/*  632 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  633 */     long function_pointer = caps.glVertexAttribI2i;
/*  634 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  635 */     nglVertexAttribI2i(index, x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI2i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI3i(int index, int x, int y, int z) {
/*  640 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  641 */     long function_pointer = caps.glVertexAttribI3i;
/*  642 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  643 */     nglVertexAttribI3i(index, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI4i(int index, int x, int y, int z, int w) {
/*  648 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  649 */     long function_pointer = caps.glVertexAttribI4i;
/*  650 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  651 */     nglVertexAttribI4i(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI1ui(int index, int x) {
/*  656 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  657 */     long function_pointer = caps.glVertexAttribI1ui;
/*  658 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  659 */     nglVertexAttribI1ui(index, x, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI1ui(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI2ui(int index, int x, int y) {
/*  664 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  665 */     long function_pointer = caps.glVertexAttribI2ui;
/*  666 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  667 */     nglVertexAttribI2ui(index, x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI2ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI3ui(int index, int x, int y, int z) {
/*  672 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  673 */     long function_pointer = caps.glVertexAttribI3ui;
/*  674 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  675 */     nglVertexAttribI3ui(index, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI3ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI4ui(int index, int x, int y, int z, int w) {
/*  680 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  681 */     long function_pointer = caps.glVertexAttribI4ui;
/*  682 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  683 */     nglVertexAttribI4ui(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribI1(int index, IntBuffer v) {
/*  688 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  689 */     long function_pointer = caps.glVertexAttribI1iv;
/*  690 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  691 */     BufferChecks.checkBuffer(v, 1);
/*  692 */     nglVertexAttribI1iv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI1iv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI2(int index, IntBuffer v) {
/*  697 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  698 */     long function_pointer = caps.glVertexAttribI2iv;
/*  699 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  700 */     BufferChecks.checkBuffer(v, 2);
/*  701 */     nglVertexAttribI2iv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI2iv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI3(int index, IntBuffer v) {
/*  706 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  707 */     long function_pointer = caps.glVertexAttribI3iv;
/*  708 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  709 */     BufferChecks.checkBuffer(v, 3);
/*  710 */     nglVertexAttribI3iv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI3iv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI4(int index, IntBuffer v) {
/*  715 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  716 */     long function_pointer = caps.glVertexAttribI4iv;
/*  717 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  718 */     BufferChecks.checkBuffer(v, 4);
/*  719 */     nglVertexAttribI4iv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4iv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI1u(int index, IntBuffer v) {
/*  724 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  725 */     long function_pointer = caps.glVertexAttribI1uiv;
/*  726 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  727 */     BufferChecks.checkBuffer(v, 1);
/*  728 */     nglVertexAttribI1uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI1uiv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI2u(int index, IntBuffer v) {
/*  733 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  734 */     long function_pointer = caps.glVertexAttribI2uiv;
/*  735 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  736 */     BufferChecks.checkBuffer(v, 2);
/*  737 */     nglVertexAttribI2uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI2uiv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI3u(int index, IntBuffer v) {
/*  742 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  743 */     long function_pointer = caps.glVertexAttribI3uiv;
/*  744 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  745 */     BufferChecks.checkBuffer(v, 3);
/*  746 */     nglVertexAttribI3uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI3uiv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI4u(int index, IntBuffer v) {
/*  751 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  752 */     long function_pointer = caps.glVertexAttribI4uiv;
/*  753 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  754 */     BufferChecks.checkBuffer(v, 4);
/*  755 */     nglVertexAttribI4uiv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4uiv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI4(int index, ByteBuffer v) {
/*  760 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  761 */     long function_pointer = caps.glVertexAttribI4bv;
/*  762 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  763 */     BufferChecks.checkBuffer(v, 4);
/*  764 */     nglVertexAttribI4bv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4bv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI4(int index, ShortBuffer v) {
/*  769 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  770 */     long function_pointer = caps.glVertexAttribI4sv;
/*  771 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  772 */     BufferChecks.checkBuffer(v, 4);
/*  773 */     nglVertexAttribI4sv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4sv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI4u(int index, ByteBuffer v) {
/*  778 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  779 */     long function_pointer = caps.glVertexAttribI4ubv;
/*  780 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  781 */     BufferChecks.checkBuffer(v, 4);
/*  782 */     nglVertexAttribI4ubv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4ubv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribI4u(int index, ShortBuffer v) {
/*  787 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  788 */     long function_pointer = caps.glVertexAttribI4usv;
/*  789 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  790 */     BufferChecks.checkBuffer(v, 4);
/*  791 */     nglVertexAttribI4usv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribI4usv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribIPointer(int index, int size, int type, int stride, ByteBuffer buffer) {
/*  796 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  797 */     long function_pointer = caps.glVertexAttribIPointer;
/*  798 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  799 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  800 */     BufferChecks.checkDirect(buffer);
/*  801 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  802 */     nglVertexAttribIPointer(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   public static void glVertexAttribIPointer(int index, int size, int type, int stride, IntBuffer buffer) {
/*  805 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  806 */     long function_pointer = caps.glVertexAttribIPointer;
/*  807 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  808 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  809 */     BufferChecks.checkDirect(buffer);
/*  810 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  811 */     nglVertexAttribIPointer(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   public static void glVertexAttribIPointer(int index, int size, int type, int stride, ShortBuffer buffer) {
/*  814 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  815 */     long function_pointer = caps.glVertexAttribIPointer;
/*  816 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  817 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  818 */     BufferChecks.checkDirect(buffer);
/*  819 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  820 */     nglVertexAttribIPointer(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribIPointer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*  824 */   public static void glVertexAttribIPointer(int index, int size, int type, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  825 */     long function_pointer = caps.glVertexAttribIPointer;
/*  826 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  827 */     GLChecks.ensureArrayVBOenabled(caps);
/*  828 */     nglVertexAttribIPointerBO(index, size, type, stride, buffer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglVertexAttribIPointerBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetVertexAttribI(int index, int pname, IntBuffer params) {
/*  833 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  834 */     long function_pointer = caps.glGetVertexAttribIiv;
/*  835 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  836 */     BufferChecks.checkBuffer(params, 4);
/*  837 */     nglGetVertexAttribIiv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetVertexAttribIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetVertexAttribIu(int index, int pname, IntBuffer params) {
/*  842 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  843 */     long function_pointer = caps.glGetVertexAttribIuiv;
/*  844 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  845 */     BufferChecks.checkBuffer(params, 4);
/*  846 */     nglGetVertexAttribIuiv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetVertexAttribIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform1ui(int location, int v0) {
/*  851 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  852 */     long function_pointer = caps.glUniform1ui;
/*  853 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  854 */     nglUniform1ui(location, v0, function_pointer);
/*      */   }
/*      */   static native void nglUniform1ui(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glUniform2ui(int location, int v0, int v1) {
/*  859 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  860 */     long function_pointer = caps.glUniform2ui;
/*  861 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  862 */     nglUniform2ui(location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglUniform2ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glUniform3ui(int location, int v0, int v1, int v2) {
/*  867 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  868 */     long function_pointer = caps.glUniform3ui;
/*  869 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  870 */     nglUniform3ui(location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglUniform3ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glUniform4ui(int location, int v0, int v1, int v2, int v3) {
/*  875 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  876 */     long function_pointer = caps.glUniform4ui;
/*  877 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  878 */     nglUniform4ui(location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglUniform4ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glUniform1u(int location, IntBuffer value) {
/*  883 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  884 */     long function_pointer = caps.glUniform1uiv;
/*  885 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  886 */     BufferChecks.checkDirect(value);
/*  887 */     nglUniform1uiv(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglUniform1uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform2u(int location, IntBuffer value) {
/*  892 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  893 */     long function_pointer = caps.glUniform2uiv;
/*  894 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  895 */     BufferChecks.checkDirect(value);
/*  896 */     nglUniform2uiv(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglUniform2uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform3u(int location, IntBuffer value) {
/*  901 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  902 */     long function_pointer = caps.glUniform3uiv;
/*  903 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  904 */     BufferChecks.checkDirect(value);
/*  905 */     nglUniform3uiv(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglUniform3uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform4u(int location, IntBuffer value) {
/*  910 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  911 */     long function_pointer = caps.glUniform4uiv;
/*  912 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  913 */     BufferChecks.checkDirect(value);
/*  914 */     nglUniform4uiv(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglUniform4uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetUniformu(int program, int location, IntBuffer params) {
/*  919 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  920 */     long function_pointer = caps.glGetUniformuiv;
/*  921 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  922 */     BufferChecks.checkDirect(params);
/*  923 */     nglGetUniformuiv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetUniformuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glBindFragDataLocation(int program, int colorNumber, ByteBuffer name) {
/*  928 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  929 */     long function_pointer = caps.glBindFragDataLocation;
/*  930 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  931 */     BufferChecks.checkDirect(name);
/*  932 */     BufferChecks.checkNullTerminated(name);
/*  933 */     nglBindFragDataLocation(program, colorNumber, MemoryUtil.getAddress(name), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglBindFragDataLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glBindFragDataLocation(int program, int colorNumber, CharSequence name) {
/*  939 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  940 */     long function_pointer = caps.glBindFragDataLocation;
/*  941 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  942 */     nglBindFragDataLocation(program, colorNumber, APIUtil.getBufferNT(caps, name), function_pointer);
/*      */   }
/*      */ 
/*      */   public static int glGetFragDataLocation(int program, ByteBuffer name) {
/*  946 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  947 */     long function_pointer = caps.glGetFragDataLocation;
/*  948 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  949 */     BufferChecks.checkDirect(name);
/*  950 */     BufferChecks.checkNullTerminated(name);
/*  951 */     int __result = nglGetFragDataLocation(program, MemoryUtil.getAddress(name), function_pointer);
/*  952 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglGetFragDataLocation(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetFragDataLocation(int program, CharSequence name) {
/*  958 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  959 */     long function_pointer = caps.glGetFragDataLocation;
/*  960 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  961 */     int __result = nglGetFragDataLocation(program, APIUtil.getBufferNT(caps, name), function_pointer);
/*  962 */     return __result;
/*      */   }
/*      */ 
/*      */   public static void glBeginConditionalRender(int id, int mode) {
/*  966 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  967 */     long function_pointer = caps.glBeginConditionalRender;
/*  968 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  969 */     nglBeginConditionalRender(id, mode, function_pointer);
/*      */   }
/*      */   static native void nglBeginConditionalRender(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glEndConditionalRender() {
/*  974 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  975 */     long function_pointer = caps.glEndConditionalRender;
/*  976 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  977 */     nglEndConditionalRender(function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglEndConditionalRender(long paramLong);
/*      */ 
/*      */   public static ByteBuffer glMapBufferRange(int target, long offset, long length, int access, ByteBuffer old_buffer)
/*      */   {
/*  996 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  997 */     long function_pointer = caps.glMapBufferRange;
/*  998 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  999 */     if (old_buffer != null)
/* 1000 */       BufferChecks.checkDirect(old_buffer);
/* 1001 */     ByteBuffer __result = nglMapBufferRange(target, offset, length, access, old_buffer, function_pointer);
/* 1002 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglMapBufferRange(int paramInt1, long paramLong1, long paramLong2, int paramInt2, ByteBuffer paramByteBuffer, long paramLong3);
/*      */ 
/*      */   public static void glFlushMappedBufferRange(int target, long offset, long length) {
/* 1007 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1008 */     long function_pointer = caps.glFlushMappedBufferRange;
/* 1009 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1010 */     nglFlushMappedBufferRange(target, offset, length, function_pointer);
/*      */   }
/*      */   static native void nglFlushMappedBufferRange(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glClampColor(int target, int clamp) {
/* 1015 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1016 */     long function_pointer = caps.glClampColor;
/* 1017 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1018 */     nglClampColor(target, clamp, function_pointer);
/*      */   }
/*      */   static native void nglClampColor(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static boolean glIsRenderbuffer(int renderbuffer) {
/* 1023 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1024 */     long function_pointer = caps.glIsRenderbuffer;
/* 1025 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1026 */     boolean __result = nglIsRenderbuffer(renderbuffer, function_pointer);
/* 1027 */     return __result;
/*      */   }
/*      */   static native boolean nglIsRenderbuffer(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glBindRenderbuffer(int target, int renderbuffer) {
/* 1032 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1033 */     long function_pointer = caps.glBindRenderbuffer;
/* 1034 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1035 */     nglBindRenderbuffer(target, renderbuffer, function_pointer);
/*      */   }
/*      */   static native void nglBindRenderbuffer(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDeleteRenderbuffers(IntBuffer renderbuffers) {
/* 1040 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1041 */     long function_pointer = caps.glDeleteRenderbuffers;
/* 1042 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1043 */     BufferChecks.checkDirect(renderbuffers);
/* 1044 */     nglDeleteRenderbuffers(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDeleteRenderbuffers(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDeleteRenderbuffers(int renderbuffer) {
/* 1050 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1051 */     long function_pointer = caps.glDeleteRenderbuffers;
/* 1052 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1053 */     nglDeleteRenderbuffers(1, APIUtil.getInt(caps, renderbuffer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGenRenderbuffers(IntBuffer renderbuffers) {
/* 1057 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1058 */     long function_pointer = caps.glGenRenderbuffers;
/* 1059 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1060 */     BufferChecks.checkDirect(renderbuffers);
/* 1061 */     nglGenRenderbuffers(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGenRenderbuffers(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGenRenderbuffers() {
/* 1067 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1068 */     long function_pointer = caps.glGenRenderbuffers;
/* 1069 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1070 */     IntBuffer renderbuffers = APIUtil.getBufferInt(caps);
/* 1071 */     nglGenRenderbuffers(1, MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 1072 */     return renderbuffers.get(0);
/*      */   }
/*      */ 
/*      */   public static void glRenderbufferStorage(int target, int internalformat, int width, int height) {
/* 1076 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1077 */     long function_pointer = caps.glRenderbufferStorage;
/* 1078 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1079 */     nglRenderbufferStorage(target, internalformat, width, height, function_pointer);
/*      */   }
/*      */   static native void nglRenderbufferStorage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glGetRenderbufferParameter(int target, int pname, IntBuffer params) {
/* 1084 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1085 */     long function_pointer = caps.glGetRenderbufferParameteriv;
/* 1086 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1087 */     BufferChecks.checkBuffer(params, 4);
/* 1088 */     nglGetRenderbufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetRenderbufferParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   @Deprecated
/*      */   public static int glGetRenderbufferParameter(int target, int pname)
/*      */   {
/* 1099 */     return glGetRenderbufferParameteri(target, pname);
/*      */   }
/*      */ 
/*      */   public static int glGetRenderbufferParameteri(int target, int pname)
/*      */   {
/* 1104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1105 */     long function_pointer = caps.glGetRenderbufferParameteriv;
/* 1106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1107 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1108 */     nglGetRenderbufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1109 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static boolean glIsFramebuffer(int framebuffer) {
/* 1113 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1114 */     long function_pointer = caps.glIsFramebuffer;
/* 1115 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1116 */     boolean __result = nglIsFramebuffer(framebuffer, function_pointer);
/* 1117 */     return __result;
/*      */   }
/*      */   static native boolean nglIsFramebuffer(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glBindFramebuffer(int target, int framebuffer) {
/* 1122 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1123 */     long function_pointer = caps.glBindFramebuffer;
/* 1124 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1125 */     nglBindFramebuffer(target, framebuffer, function_pointer);
/*      */   }
/*      */   static native void nglBindFramebuffer(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDeleteFramebuffers(IntBuffer framebuffers) {
/* 1130 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1131 */     long function_pointer = caps.glDeleteFramebuffers;
/* 1132 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1133 */     BufferChecks.checkDirect(framebuffers);
/* 1134 */     nglDeleteFramebuffers(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDeleteFramebuffers(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDeleteFramebuffers(int framebuffer) {
/* 1140 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1141 */     long function_pointer = caps.glDeleteFramebuffers;
/* 1142 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1143 */     nglDeleteFramebuffers(1, APIUtil.getInt(caps, framebuffer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGenFramebuffers(IntBuffer framebuffers) {
/* 1147 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1148 */     long function_pointer = caps.glGenFramebuffers;
/* 1149 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1150 */     BufferChecks.checkDirect(framebuffers);
/* 1151 */     nglGenFramebuffers(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGenFramebuffers(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGenFramebuffers() {
/* 1157 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1158 */     long function_pointer = caps.glGenFramebuffers;
/* 1159 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1160 */     IntBuffer framebuffers = APIUtil.getBufferInt(caps);
/* 1161 */     nglGenFramebuffers(1, MemoryUtil.getAddress(framebuffers), function_pointer);
/* 1162 */     return framebuffers.get(0);
/*      */   }
/*      */ 
/*      */   public static int glCheckFramebufferStatus(int target) {
/* 1166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1167 */     long function_pointer = caps.glCheckFramebufferStatus;
/* 1168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1169 */     int __result = nglCheckFramebufferStatus(target, function_pointer);
/* 1170 */     return __result;
/*      */   }
/*      */   static native int nglCheckFramebufferStatus(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glFramebufferTexture1D(int target, int attachment, int textarget, int texture, int level) {
/* 1175 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1176 */     long function_pointer = caps.glFramebufferTexture1D;
/* 1177 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1178 */     nglFramebufferTexture1D(target, attachment, textarget, texture, level, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferTexture1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
/* 1183 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1184 */     long function_pointer = caps.glFramebufferTexture2D;
/* 1185 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1186 */     nglFramebufferTexture2D(target, attachment, textarget, texture, level, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferTexture2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glFramebufferTexture3D(int target, int attachment, int textarget, int texture, int level, int zoffset) {
/* 1191 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1192 */     long function_pointer = caps.glFramebufferTexture3D;
/* 1193 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1194 */     nglFramebufferTexture3D(target, attachment, textarget, texture, level, zoffset, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferTexture3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
/* 1199 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1200 */     long function_pointer = caps.glFramebufferRenderbuffer;
/* 1201 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1202 */     nglFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferRenderbuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glGetFramebufferAttachmentParameter(int target, int attachment, int pname, IntBuffer params) {
/* 1207 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1208 */     long function_pointer = caps.glGetFramebufferAttachmentParameteriv;
/* 1209 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1210 */     BufferChecks.checkBuffer(params, 4);
/* 1211 */     nglGetFramebufferAttachmentParameteriv(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFramebufferAttachmentParameteriv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   @Deprecated
/*      */   public static int glGetFramebufferAttachmentParameter(int target, int attachment, int pname)
/*      */   {
/* 1222 */     return glGetFramebufferAttachmentParameteri(target, attachment, pname);
/*      */   }
/*      */ 
/*      */   public static int glGetFramebufferAttachmentParameteri(int target, int attachment, int pname)
/*      */   {
/* 1227 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1228 */     long function_pointer = caps.glGetFramebufferAttachmentParameteriv;
/* 1229 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1230 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1231 */     nglGetFramebufferAttachmentParameteriv(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1232 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGenerateMipmap(int target) {
/* 1236 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1237 */     long function_pointer = caps.glGenerateMipmap;
/* 1238 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1239 */     nglGenerateMipmap(target, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGenerateMipmap(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height)
/*      */   {
/* 1248 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1249 */     long function_pointer = caps.glRenderbufferStorageMultisample;
/* 1250 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1251 */     nglRenderbufferStorageMultisample(target, samples, internalformat, width, height, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglRenderbufferStorageMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter)
/*      */   {
/* 1273 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1274 */     long function_pointer = caps.glBlitFramebuffer;
/* 1275 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1276 */     nglBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter, function_pointer);
/*      */   }
/*      */   static native void nglBlitFramebuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/*      */ 
/*      */   public static void glTexParameterI(int target, int pname, IntBuffer params) {
/* 1281 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1282 */     long function_pointer = caps.glTexParameterIiv;
/* 1283 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1284 */     BufferChecks.checkBuffer(params, 4);
/* 1285 */     nglTexParameterIiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglTexParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexParameterIi(int target, int pname, int param) {
/* 1291 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1292 */     long function_pointer = caps.glTexParameterIiv;
/* 1293 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1294 */     nglTexParameterIiv(target, pname, APIUtil.getInt(caps, param), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glTexParameterIu(int target, int pname, IntBuffer params) {
/* 1298 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1299 */     long function_pointer = caps.glTexParameterIuiv;
/* 1300 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1301 */     BufferChecks.checkBuffer(params, 4);
/* 1302 */     nglTexParameterIuiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglTexParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexParameterIui(int target, int pname, int param) {
/* 1308 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1309 */     long function_pointer = caps.glTexParameterIuiv;
/* 1310 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1311 */     nglTexParameterIuiv(target, pname, APIUtil.getInt(caps, param), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetTexParameterI(int target, int pname, IntBuffer params) {
/* 1315 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1316 */     long function_pointer = caps.glGetTexParameterIiv;
/* 1317 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1318 */     BufferChecks.checkBuffer(params, 4);
/* 1319 */     nglGetTexParameterIiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTexParameterIi(int target, int pname) {
/* 1325 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1326 */     long function_pointer = caps.glGetTexParameterIiv;
/* 1327 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1328 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1329 */     nglGetTexParameterIiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1330 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexParameterIu(int target, int pname, IntBuffer params) {
/* 1334 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1335 */     long function_pointer = caps.glGetTexParameterIuiv;
/* 1336 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1337 */     BufferChecks.checkBuffer(params, 4);
/* 1338 */     nglGetTexParameterIuiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTexParameterIui(int target, int pname) {
/* 1344 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1345 */     long function_pointer = caps.glGetTexParameterIuiv;
/* 1346 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1347 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1348 */     nglGetTexParameterIuiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1349 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
/* 1353 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1354 */     long function_pointer = caps.glFramebufferTextureLayer;
/* 1355 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1356 */     nglFramebufferTextureLayer(target, attachment, texture, level, layer, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferTextureLayer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glColorMaski(int buf, boolean r, boolean g, boolean b, boolean a) {
/* 1361 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1362 */     long function_pointer = caps.glColorMaski;
/* 1363 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1364 */     nglColorMaski(buf, r, g, b, a, function_pointer);
/*      */   }
/*      */   static native void nglColorMaski(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, long paramLong);
/*      */ 
/*      */   public static void glGetBoolean(int value, int index, ByteBuffer data) {
/* 1369 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1370 */     long function_pointer = caps.glGetBooleani_v;
/* 1371 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1372 */     BufferChecks.checkBuffer(data, 4);
/* 1373 */     nglGetBooleani_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetBooleani_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static boolean glGetBoolean(int value, int index) {
/* 1379 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1380 */     long function_pointer = caps.glGetBooleani_v;
/* 1381 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1382 */     ByteBuffer data = APIUtil.getBufferByte(caps, 1);
/* 1383 */     nglGetBooleani_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 1384 */     return data.get(0) == 1;
/*      */   }
/*      */ 
/*      */   public static void glGetInteger(int value, int index, IntBuffer data) {
/* 1388 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1389 */     long function_pointer = caps.glGetIntegeri_v;
/* 1390 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1391 */     BufferChecks.checkBuffer(data, 4);
/* 1392 */     nglGetIntegeri_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetIntegeri_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetInteger(int value, int index) {
/* 1398 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1399 */     long function_pointer = caps.glGetIntegeri_v;
/* 1400 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1401 */     IntBuffer data = APIUtil.getBufferInt(caps);
/* 1402 */     nglGetIntegeri_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 1403 */     return data.get(0);
/*      */   }
/*      */ 
/*      */   public static void glEnablei(int target, int index) {
/* 1407 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1408 */     long function_pointer = caps.glEnablei;
/* 1409 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1410 */     nglEnablei(target, index, function_pointer);
/*      */   }
/*      */   static native void nglEnablei(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDisablei(int target, int index) {
/* 1415 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1416 */     long function_pointer = caps.glDisablei;
/* 1417 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1418 */     nglDisablei(target, index, function_pointer);
/*      */   }
/*      */   static native void nglDisablei(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static boolean glIsEnabledi(int target, int index) {
/* 1423 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1424 */     long function_pointer = caps.glIsEnabledi;
/* 1425 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1426 */     boolean __result = nglIsEnabledi(target, index, function_pointer);
/* 1427 */     return __result;
/*      */   }
/*      */   static native boolean nglIsEnabledi(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glBindBufferRange(int target, int index, int buffer, long offset, long size) {
/* 1432 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1433 */     long function_pointer = caps.glBindBufferRange;
/* 1434 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1435 */     nglBindBufferRange(target, index, buffer, offset, size, function_pointer);
/*      */   }
/*      */   static native void nglBindBufferRange(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glBindBufferBase(int target, int index, int buffer) {
/* 1440 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1441 */     long function_pointer = caps.glBindBufferBase;
/* 1442 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1443 */     nglBindBufferBase(target, index, buffer, function_pointer);
/*      */   }
/*      */   static native void nglBindBufferBase(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glBeginTransformFeedback(int primitiveMode) {
/* 1448 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1449 */     long function_pointer = caps.glBeginTransformFeedback;
/* 1450 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1451 */     nglBeginTransformFeedback(primitiveMode, function_pointer);
/*      */   }
/*      */   static native void nglBeginTransformFeedback(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glEndTransformFeedback() {
/* 1456 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1457 */     long function_pointer = caps.glEndTransformFeedback;
/* 1458 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1459 */     nglEndTransformFeedback(function_pointer);
/*      */   }
/*      */   static native void nglEndTransformFeedback(long paramLong);
/*      */ 
/*      */   public static void glTransformFeedbackVaryings(int program, int count, ByteBuffer varyings, int bufferMode) {
/* 1464 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1465 */     long function_pointer = caps.glTransformFeedbackVaryings;
/* 1466 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1467 */     BufferChecks.checkDirect(varyings);
/* 1468 */     BufferChecks.checkNullTerminated(varyings, count);
/* 1469 */     nglTransformFeedbackVaryings(program, count, MemoryUtil.getAddress(varyings), bufferMode, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglTransformFeedbackVaryings(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*      */ 
/*      */   public static void glTransformFeedbackVaryings(int program, CharSequence[] varyings, int bufferMode) {
/* 1475 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1476 */     long function_pointer = caps.glTransformFeedbackVaryings;
/* 1477 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1478 */     BufferChecks.checkArray(varyings);
/* 1479 */     nglTransformFeedbackVaryings(program, varyings.length, APIUtil.getBufferNT(caps, varyings), bufferMode, function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetTransformFeedbackVarying(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 1483 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1484 */     long function_pointer = caps.glGetTransformFeedbackVarying;
/* 1485 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1486 */     if (length != null)
/* 1487 */       BufferChecks.checkBuffer(length, 1);
/* 1488 */     BufferChecks.checkBuffer(size, 1);
/* 1489 */     BufferChecks.checkBuffer(type, 1);
/* 1490 */     BufferChecks.checkDirect(name);
/* 1491 */     nglGetTransformFeedbackVarying(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTransformFeedbackVarying(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static String glGetTransformFeedbackVarying(int program, int index, int bufSize, IntBuffer size, IntBuffer type) {
/* 1497 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1498 */     long function_pointer = caps.glGetTransformFeedbackVarying;
/* 1499 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1500 */     BufferChecks.checkBuffer(size, 1);
/* 1501 */     BufferChecks.checkBuffer(type, 1);
/* 1502 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 1503 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 1504 */     nglGetTransformFeedbackVarying(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 1505 */     name.limit(name_length.get(0));
/* 1506 */     return APIUtil.getString(caps, name);
/*      */   }
/*      */ 
/*      */   public static void glBindVertexArray(int array) {
/* 1510 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1511 */     long function_pointer = caps.glBindVertexArray;
/* 1512 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1513 */     StateTracker.bindVAO(caps, array);
/* 1514 */     nglBindVertexArray(array, function_pointer);
/*      */   }
/*      */   static native void nglBindVertexArray(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDeleteVertexArrays(IntBuffer arrays) {
/* 1519 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1520 */     long function_pointer = caps.glDeleteVertexArrays;
/* 1521 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1522 */     StateTracker.deleteVAO(caps, arrays);
/* 1523 */     BufferChecks.checkDirect(arrays);
/* 1524 */     nglDeleteVertexArrays(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDeleteVertexArrays(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDeleteVertexArrays(int array) {
/* 1530 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1531 */     long function_pointer = caps.glDeleteVertexArrays;
/* 1532 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1533 */     StateTracker.deleteVAO(caps, array);
/* 1534 */     nglDeleteVertexArrays(1, APIUtil.getInt(caps, array), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGenVertexArrays(IntBuffer arrays) {
/* 1538 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1539 */     long function_pointer = caps.glGenVertexArrays;
/* 1540 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1541 */     BufferChecks.checkDirect(arrays);
/* 1542 */     nglGenVertexArrays(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGenVertexArrays(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGenVertexArrays() {
/* 1548 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1549 */     long function_pointer = caps.glGenVertexArrays;
/* 1550 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1551 */     IntBuffer arrays = APIUtil.getBufferInt(caps);
/* 1552 */     nglGenVertexArrays(1, MemoryUtil.getAddress(arrays), function_pointer);
/* 1553 */     return arrays.get(0);
/*      */   }
/*      */ 
/*      */   public static boolean glIsVertexArray(int array) {
/* 1557 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1558 */     long function_pointer = caps.glIsVertexArray;
/* 1559 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1560 */     boolean __result = nglIsVertexArray(array, function_pointer);
/* 1561 */     return __result;
/*      */   }
/*      */ 
/*      */   static native boolean nglIsVertexArray(int paramInt, long paramLong);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL30
 * JD-Core Version:    0.6.2
 */