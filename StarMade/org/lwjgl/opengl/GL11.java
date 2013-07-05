/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class GL11
/*      */ {
/*      */   public static final int GL_ACCUM = 256;
/*      */   public static final int GL_LOAD = 257;
/*      */   public static final int GL_RETURN = 258;
/*      */   public static final int GL_MULT = 259;
/*      */   public static final int GL_ADD = 260;
/*      */   public static final int GL_NEVER = 512;
/*      */   public static final int GL_LESS = 513;
/*      */   public static final int GL_EQUAL = 514;
/*      */   public static final int GL_LEQUAL = 515;
/*      */   public static final int GL_GREATER = 516;
/*      */   public static final int GL_NOTEQUAL = 517;
/*      */   public static final int GL_GEQUAL = 518;
/*      */   public static final int GL_ALWAYS = 519;
/*      */   public static final int GL_CURRENT_BIT = 1;
/*      */   public static final int GL_POINT_BIT = 2;
/*      */   public static final int GL_LINE_BIT = 4;
/*      */   public static final int GL_POLYGON_BIT = 8;
/*      */   public static final int GL_POLYGON_STIPPLE_BIT = 16;
/*      */   public static final int GL_PIXEL_MODE_BIT = 32;
/*      */   public static final int GL_LIGHTING_BIT = 64;
/*      */   public static final int GL_FOG_BIT = 128;
/*      */   public static final int GL_DEPTH_BUFFER_BIT = 256;
/*      */   public static final int GL_ACCUM_BUFFER_BIT = 512;
/*      */   public static final int GL_STENCIL_BUFFER_BIT = 1024;
/*      */   public static final int GL_VIEWPORT_BIT = 2048;
/*      */   public static final int GL_TRANSFORM_BIT = 4096;
/*      */   public static final int GL_ENABLE_BIT = 8192;
/*      */   public static final int GL_COLOR_BUFFER_BIT = 16384;
/*      */   public static final int GL_HINT_BIT = 32768;
/*      */   public static final int GL_EVAL_BIT = 65536;
/*      */   public static final int GL_LIST_BIT = 131072;
/*      */   public static final int GL_TEXTURE_BIT = 262144;
/*      */   public static final int GL_SCISSOR_BIT = 524288;
/*      */   public static final int GL_ALL_ATTRIB_BITS = 1048575;
/*      */   public static final int GL_POINTS = 0;
/*      */   public static final int GL_LINES = 1;
/*      */   public static final int GL_LINE_LOOP = 2;
/*      */   public static final int GL_LINE_STRIP = 3;
/*      */   public static final int GL_TRIANGLES = 4;
/*      */   public static final int GL_TRIANGLE_STRIP = 5;
/*      */   public static final int GL_TRIANGLE_FAN = 6;
/*      */   public static final int GL_QUADS = 7;
/*      */   public static final int GL_QUAD_STRIP = 8;
/*      */   public static final int GL_POLYGON = 9;
/*      */   public static final int GL_ZERO = 0;
/*      */   public static final int GL_ONE = 1;
/*      */   public static final int GL_SRC_COLOR = 768;
/*      */   public static final int GL_ONE_MINUS_SRC_COLOR = 769;
/*      */   public static final int GL_SRC_ALPHA = 770;
/*      */   public static final int GL_ONE_MINUS_SRC_ALPHA = 771;
/*      */   public static final int GL_DST_ALPHA = 772;
/*      */   public static final int GL_ONE_MINUS_DST_ALPHA = 773;
/*      */   public static final int GL_DST_COLOR = 774;
/*      */   public static final int GL_ONE_MINUS_DST_COLOR = 775;
/*      */   public static final int GL_SRC_ALPHA_SATURATE = 776;
/*      */   public static final int GL_CONSTANT_COLOR = 32769;
/*      */   public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
/*      */   public static final int GL_CONSTANT_ALPHA = 32771;
/*      */   public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
/*      */   public static final int GL_TRUE = 1;
/*      */   public static final int GL_FALSE = 0;
/*      */   public static final int GL_CLIP_PLANE0 = 12288;
/*      */   public static final int GL_CLIP_PLANE1 = 12289;
/*      */   public static final int GL_CLIP_PLANE2 = 12290;
/*      */   public static final int GL_CLIP_PLANE3 = 12291;
/*      */   public static final int GL_CLIP_PLANE4 = 12292;
/*      */   public static final int GL_CLIP_PLANE5 = 12293;
/*      */   public static final int GL_BYTE = 5120;
/*      */   public static final int GL_UNSIGNED_BYTE = 5121;
/*      */   public static final int GL_SHORT = 5122;
/*      */   public static final int GL_UNSIGNED_SHORT = 5123;
/*      */   public static final int GL_INT = 5124;
/*      */   public static final int GL_UNSIGNED_INT = 5125;
/*      */   public static final int GL_FLOAT = 5126;
/*      */   public static final int GL_2_BYTES = 5127;
/*      */   public static final int GL_3_BYTES = 5128;
/*      */   public static final int GL_4_BYTES = 5129;
/*      */   public static final int GL_DOUBLE = 5130;
/*      */   public static final int GL_NONE = 0;
/*      */   public static final int GL_FRONT_LEFT = 1024;
/*      */   public static final int GL_FRONT_RIGHT = 1025;
/*      */   public static final int GL_BACK_LEFT = 1026;
/*      */   public static final int GL_BACK_RIGHT = 1027;
/*      */   public static final int GL_FRONT = 1028;
/*      */   public static final int GL_BACK = 1029;
/*      */   public static final int GL_LEFT = 1030;
/*      */   public static final int GL_RIGHT = 1031;
/*      */   public static final int GL_FRONT_AND_BACK = 1032;
/*      */   public static final int GL_AUX0 = 1033;
/*      */   public static final int GL_AUX1 = 1034;
/*      */   public static final int GL_AUX2 = 1035;
/*      */   public static final int GL_AUX3 = 1036;
/*      */   public static final int GL_NO_ERROR = 0;
/*      */   public static final int GL_INVALID_ENUM = 1280;
/*      */   public static final int GL_INVALID_VALUE = 1281;
/*      */   public static final int GL_INVALID_OPERATION = 1282;
/*      */   public static final int GL_STACK_OVERFLOW = 1283;
/*      */   public static final int GL_STACK_UNDERFLOW = 1284;
/*      */   public static final int GL_OUT_OF_MEMORY = 1285;
/*      */   public static final int GL_2D = 1536;
/*      */   public static final int GL_3D = 1537;
/*      */   public static final int GL_3D_COLOR = 1538;
/*      */   public static final int GL_3D_COLOR_TEXTURE = 1539;
/*      */   public static final int GL_4D_COLOR_TEXTURE = 1540;
/*      */   public static final int GL_PASS_THROUGH_TOKEN = 1792;
/*      */   public static final int GL_POINT_TOKEN = 1793;
/*      */   public static final int GL_LINE_TOKEN = 1794;
/*      */   public static final int GL_POLYGON_TOKEN = 1795;
/*      */   public static final int GL_BITMAP_TOKEN = 1796;
/*      */   public static final int GL_DRAW_PIXEL_TOKEN = 1797;
/*      */   public static final int GL_COPY_PIXEL_TOKEN = 1798;
/*      */   public static final int GL_LINE_RESET_TOKEN = 1799;
/*      */   public static final int GL_EXP = 2048;
/*      */   public static final int GL_EXP2 = 2049;
/*      */   public static final int GL_CW = 2304;
/*      */   public static final int GL_CCW = 2305;
/*      */   public static final int GL_COEFF = 2560;
/*      */   public static final int GL_ORDER = 2561;
/*      */   public static final int GL_DOMAIN = 2562;
/*      */   public static final int GL_CURRENT_COLOR = 2816;
/*      */   public static final int GL_CURRENT_INDEX = 2817;
/*      */   public static final int GL_CURRENT_NORMAL = 2818;
/*      */   public static final int GL_CURRENT_TEXTURE_COORDS = 2819;
/*      */   public static final int GL_CURRENT_RASTER_COLOR = 2820;
/*      */   public static final int GL_CURRENT_RASTER_INDEX = 2821;
/*      */   public static final int GL_CURRENT_RASTER_TEXTURE_COORDS = 2822;
/*      */   public static final int GL_CURRENT_RASTER_POSITION = 2823;
/*      */   public static final int GL_CURRENT_RASTER_POSITION_VALID = 2824;
/*      */   public static final int GL_CURRENT_RASTER_DISTANCE = 2825;
/*      */   public static final int GL_POINT_SMOOTH = 2832;
/*      */   public static final int GL_POINT_SIZE = 2833;
/*      */   public static final int GL_POINT_SIZE_RANGE = 2834;
/*      */   public static final int GL_POINT_SIZE_GRANULARITY = 2835;
/*      */   public static final int GL_LINE_SMOOTH = 2848;
/*      */   public static final int GL_LINE_WIDTH = 2849;
/*      */   public static final int GL_LINE_WIDTH_RANGE = 2850;
/*      */   public static final int GL_LINE_WIDTH_GRANULARITY = 2851;
/*      */   public static final int GL_LINE_STIPPLE = 2852;
/*      */   public static final int GL_LINE_STIPPLE_PATTERN = 2853;
/*      */   public static final int GL_LINE_STIPPLE_REPEAT = 2854;
/*      */   public static final int GL_LIST_MODE = 2864;
/*      */   public static final int GL_MAX_LIST_NESTING = 2865;
/*      */   public static final int GL_LIST_BASE = 2866;
/*      */   public static final int GL_LIST_INDEX = 2867;
/*      */   public static final int GL_POLYGON_MODE = 2880;
/*      */   public static final int GL_POLYGON_SMOOTH = 2881;
/*      */   public static final int GL_POLYGON_STIPPLE = 2882;
/*      */   public static final int GL_EDGE_FLAG = 2883;
/*      */   public static final int GL_CULL_FACE = 2884;
/*      */   public static final int GL_CULL_FACE_MODE = 2885;
/*      */   public static final int GL_FRONT_FACE = 2886;
/*      */   public static final int GL_LIGHTING = 2896;
/*      */   public static final int GL_LIGHT_MODEL_LOCAL_VIEWER = 2897;
/*      */   public static final int GL_LIGHT_MODEL_TWO_SIDE = 2898;
/*      */   public static final int GL_LIGHT_MODEL_AMBIENT = 2899;
/*      */   public static final int GL_SHADE_MODEL = 2900;
/*      */   public static final int GL_COLOR_MATERIAL_FACE = 2901;
/*      */   public static final int GL_COLOR_MATERIAL_PARAMETER = 2902;
/*      */   public static final int GL_COLOR_MATERIAL = 2903;
/*      */   public static final int GL_FOG = 2912;
/*      */   public static final int GL_FOG_INDEX = 2913;
/*      */   public static final int GL_FOG_DENSITY = 2914;
/*      */   public static final int GL_FOG_START = 2915;
/*      */   public static final int GL_FOG_END = 2916;
/*      */   public static final int GL_FOG_MODE = 2917;
/*      */   public static final int GL_FOG_COLOR = 2918;
/*      */   public static final int GL_DEPTH_RANGE = 2928;
/*      */   public static final int GL_DEPTH_TEST = 2929;
/*      */   public static final int GL_DEPTH_WRITEMASK = 2930;
/*      */   public static final int GL_DEPTH_CLEAR_VALUE = 2931;
/*      */   public static final int GL_DEPTH_FUNC = 2932;
/*      */   public static final int GL_ACCUM_CLEAR_VALUE = 2944;
/*      */   public static final int GL_STENCIL_TEST = 2960;
/*      */   public static final int GL_STENCIL_CLEAR_VALUE = 2961;
/*      */   public static final int GL_STENCIL_FUNC = 2962;
/*      */   public static final int GL_STENCIL_VALUE_MASK = 2963;
/*      */   public static final int GL_STENCIL_FAIL = 2964;
/*      */   public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
/*      */   public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
/*      */   public static final int GL_STENCIL_REF = 2967;
/*      */   public static final int GL_STENCIL_WRITEMASK = 2968;
/*      */   public static final int GL_MATRIX_MODE = 2976;
/*      */   public static final int GL_NORMALIZE = 2977;
/*      */   public static final int GL_VIEWPORT = 2978;
/*      */   public static final int GL_MODELVIEW_STACK_DEPTH = 2979;
/*      */   public static final int GL_PROJECTION_STACK_DEPTH = 2980;
/*      */   public static final int GL_TEXTURE_STACK_DEPTH = 2981;
/*      */   public static final int GL_MODELVIEW_MATRIX = 2982;
/*      */   public static final int GL_PROJECTION_MATRIX = 2983;
/*      */   public static final int GL_TEXTURE_MATRIX = 2984;
/*      */   public static final int GL_ATTRIB_STACK_DEPTH = 2992;
/*      */   public static final int GL_CLIENT_ATTRIB_STACK_DEPTH = 2993;
/*      */   public static final int GL_ALPHA_TEST = 3008;
/*      */   public static final int GL_ALPHA_TEST_FUNC = 3009;
/*      */   public static final int GL_ALPHA_TEST_REF = 3010;
/*      */   public static final int GL_DITHER = 3024;
/*      */   public static final int GL_BLEND_DST = 3040;
/*      */   public static final int GL_BLEND_SRC = 3041;
/*      */   public static final int GL_BLEND = 3042;
/*      */   public static final int GL_LOGIC_OP_MODE = 3056;
/*      */   public static final int GL_INDEX_LOGIC_OP = 3057;
/*      */   public static final int GL_COLOR_LOGIC_OP = 3058;
/*      */   public static final int GL_AUX_BUFFERS = 3072;
/*      */   public static final int GL_DRAW_BUFFER = 3073;
/*      */   public static final int GL_READ_BUFFER = 3074;
/*      */   public static final int GL_SCISSOR_BOX = 3088;
/*      */   public static final int GL_SCISSOR_TEST = 3089;
/*      */   public static final int GL_INDEX_CLEAR_VALUE = 3104;
/*      */   public static final int GL_INDEX_WRITEMASK = 3105;
/*      */   public static final int GL_COLOR_CLEAR_VALUE = 3106;
/*      */   public static final int GL_COLOR_WRITEMASK = 3107;
/*      */   public static final int GL_INDEX_MODE = 3120;
/*      */   public static final int GL_RGBA_MODE = 3121;
/*      */   public static final int GL_DOUBLEBUFFER = 3122;
/*      */   public static final int GL_STEREO = 3123;
/*      */   public static final int GL_RENDER_MODE = 3136;
/*      */   public static final int GL_PERSPECTIVE_CORRECTION_HINT = 3152;
/*      */   public static final int GL_POINT_SMOOTH_HINT = 3153;
/*      */   public static final int GL_LINE_SMOOTH_HINT = 3154;
/*      */   public static final int GL_POLYGON_SMOOTH_HINT = 3155;
/*      */   public static final int GL_FOG_HINT = 3156;
/*      */   public static final int GL_TEXTURE_GEN_S = 3168;
/*      */   public static final int GL_TEXTURE_GEN_T = 3169;
/*      */   public static final int GL_TEXTURE_GEN_R = 3170;
/*      */   public static final int GL_TEXTURE_GEN_Q = 3171;
/*      */   public static final int GL_PIXEL_MAP_I_TO_I = 3184;
/*      */   public static final int GL_PIXEL_MAP_S_TO_S = 3185;
/*      */   public static final int GL_PIXEL_MAP_I_TO_R = 3186;
/*      */   public static final int GL_PIXEL_MAP_I_TO_G = 3187;
/*      */   public static final int GL_PIXEL_MAP_I_TO_B = 3188;
/*      */   public static final int GL_PIXEL_MAP_I_TO_A = 3189;
/*      */   public static final int GL_PIXEL_MAP_R_TO_R = 3190;
/*      */   public static final int GL_PIXEL_MAP_G_TO_G = 3191;
/*      */   public static final int GL_PIXEL_MAP_B_TO_B = 3192;
/*      */   public static final int GL_PIXEL_MAP_A_TO_A = 3193;
/*      */   public static final int GL_PIXEL_MAP_I_TO_I_SIZE = 3248;
/*      */   public static final int GL_PIXEL_MAP_S_TO_S_SIZE = 3249;
/*      */   public static final int GL_PIXEL_MAP_I_TO_R_SIZE = 3250;
/*      */   public static final int GL_PIXEL_MAP_I_TO_G_SIZE = 3251;
/*      */   public static final int GL_PIXEL_MAP_I_TO_B_SIZE = 3252;
/*      */   public static final int GL_PIXEL_MAP_I_TO_A_SIZE = 3253;
/*      */   public static final int GL_PIXEL_MAP_R_TO_R_SIZE = 3254;
/*      */   public static final int GL_PIXEL_MAP_G_TO_G_SIZE = 3255;
/*      */   public static final int GL_PIXEL_MAP_B_TO_B_SIZE = 3256;
/*      */   public static final int GL_PIXEL_MAP_A_TO_A_SIZE = 3257;
/*      */   public static final int GL_UNPACK_SWAP_BYTES = 3312;
/*      */   public static final int GL_UNPACK_LSB_FIRST = 3313;
/*      */   public static final int GL_UNPACK_ROW_LENGTH = 3314;
/*      */   public static final int GL_UNPACK_SKIP_ROWS = 3315;
/*      */   public static final int GL_UNPACK_SKIP_PIXELS = 3316;
/*      */   public static final int GL_UNPACK_ALIGNMENT = 3317;
/*      */   public static final int GL_PACK_SWAP_BYTES = 3328;
/*      */   public static final int GL_PACK_LSB_FIRST = 3329;
/*      */   public static final int GL_PACK_ROW_LENGTH = 3330;
/*      */   public static final int GL_PACK_SKIP_ROWS = 3331;
/*      */   public static final int GL_PACK_SKIP_PIXELS = 3332;
/*      */   public static final int GL_PACK_ALIGNMENT = 3333;
/*      */   public static final int GL_MAP_COLOR = 3344;
/*      */   public static final int GL_MAP_STENCIL = 3345;
/*      */   public static final int GL_INDEX_SHIFT = 3346;
/*      */   public static final int GL_INDEX_OFFSET = 3347;
/*      */   public static final int GL_RED_SCALE = 3348;
/*      */   public static final int GL_RED_BIAS = 3349;
/*      */   public static final int GL_ZOOM_X = 3350;
/*      */   public static final int GL_ZOOM_Y = 3351;
/*      */   public static final int GL_GREEN_SCALE = 3352;
/*      */   public static final int GL_GREEN_BIAS = 3353;
/*      */   public static final int GL_BLUE_SCALE = 3354;
/*      */   public static final int GL_BLUE_BIAS = 3355;
/*      */   public static final int GL_ALPHA_SCALE = 3356;
/*      */   public static final int GL_ALPHA_BIAS = 3357;
/*      */   public static final int GL_DEPTH_SCALE = 3358;
/*      */   public static final int GL_DEPTH_BIAS = 3359;
/*      */   public static final int GL_MAX_EVAL_ORDER = 3376;
/*      */   public static final int GL_MAX_LIGHTS = 3377;
/*      */   public static final int GL_MAX_CLIP_PLANES = 3378;
/*      */   public static final int GL_MAX_TEXTURE_SIZE = 3379;
/*      */   public static final int GL_MAX_PIXEL_MAP_TABLE = 3380;
/*      */   public static final int GL_MAX_ATTRIB_STACK_DEPTH = 3381;
/*      */   public static final int GL_MAX_MODELVIEW_STACK_DEPTH = 3382;
/*      */   public static final int GL_MAX_NAME_STACK_DEPTH = 3383;
/*      */   public static final int GL_MAX_PROJECTION_STACK_DEPTH = 3384;
/*      */   public static final int GL_MAX_TEXTURE_STACK_DEPTH = 3385;
/*      */   public static final int GL_MAX_VIEWPORT_DIMS = 3386;
/*      */   public static final int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 3387;
/*      */   public static final int GL_SUBPIXEL_BITS = 3408;
/*      */   public static final int GL_INDEX_BITS = 3409;
/*      */   public static final int GL_RED_BITS = 3410;
/*      */   public static final int GL_GREEN_BITS = 3411;
/*      */   public static final int GL_BLUE_BITS = 3412;
/*      */   public static final int GL_ALPHA_BITS = 3413;
/*      */   public static final int GL_DEPTH_BITS = 3414;
/*      */   public static final int GL_STENCIL_BITS = 3415;
/*      */   public static final int GL_ACCUM_RED_BITS = 3416;
/*      */   public static final int GL_ACCUM_GREEN_BITS = 3417;
/*      */   public static final int GL_ACCUM_BLUE_BITS = 3418;
/*      */   public static final int GL_ACCUM_ALPHA_BITS = 3419;
/*      */   public static final int GL_NAME_STACK_DEPTH = 3440;
/*      */   public static final int GL_AUTO_NORMAL = 3456;
/*      */   public static final int GL_MAP1_COLOR_4 = 3472;
/*      */   public static final int GL_MAP1_INDEX = 3473;
/*      */   public static final int GL_MAP1_NORMAL = 3474;
/*      */   public static final int GL_MAP1_TEXTURE_COORD_1 = 3475;
/*      */   public static final int GL_MAP1_TEXTURE_COORD_2 = 3476;
/*      */   public static final int GL_MAP1_TEXTURE_COORD_3 = 3477;
/*      */   public static final int GL_MAP1_TEXTURE_COORD_4 = 3478;
/*      */   public static final int GL_MAP1_VERTEX_3 = 3479;
/*      */   public static final int GL_MAP1_VERTEX_4 = 3480;
/*      */   public static final int GL_MAP2_COLOR_4 = 3504;
/*      */   public static final int GL_MAP2_INDEX = 3505;
/*      */   public static final int GL_MAP2_NORMAL = 3506;
/*      */   public static final int GL_MAP2_TEXTURE_COORD_1 = 3507;
/*      */   public static final int GL_MAP2_TEXTURE_COORD_2 = 3508;
/*      */   public static final int GL_MAP2_TEXTURE_COORD_3 = 3509;
/*      */   public static final int GL_MAP2_TEXTURE_COORD_4 = 3510;
/*      */   public static final int GL_MAP2_VERTEX_3 = 3511;
/*      */   public static final int GL_MAP2_VERTEX_4 = 3512;
/*      */   public static final int GL_MAP1_GRID_DOMAIN = 3536;
/*      */   public static final int GL_MAP1_GRID_SEGMENTS = 3537;
/*      */   public static final int GL_MAP2_GRID_DOMAIN = 3538;
/*      */   public static final int GL_MAP2_GRID_SEGMENTS = 3539;
/*      */   public static final int GL_TEXTURE_1D = 3552;
/*      */   public static final int GL_TEXTURE_2D = 3553;
/*      */   public static final int GL_FEEDBACK_BUFFER_POINTER = 3568;
/*      */   public static final int GL_FEEDBACK_BUFFER_SIZE = 3569;
/*      */   public static final int GL_FEEDBACK_BUFFER_TYPE = 3570;
/*      */   public static final int GL_SELECTION_BUFFER_POINTER = 3571;
/*      */   public static final int GL_SELECTION_BUFFER_SIZE = 3572;
/*      */   public static final int GL_TEXTURE_WIDTH = 4096;
/*      */   public static final int GL_TEXTURE_HEIGHT = 4097;
/*      */   public static final int GL_TEXTURE_INTERNAL_FORMAT = 4099;
/*      */   public static final int GL_TEXTURE_BORDER_COLOR = 4100;
/*      */   public static final int GL_TEXTURE_BORDER = 4101;
/*      */   public static final int GL_DONT_CARE = 4352;
/*      */   public static final int GL_FASTEST = 4353;
/*      */   public static final int GL_NICEST = 4354;
/*      */   public static final int GL_LIGHT0 = 16384;
/*      */   public static final int GL_LIGHT1 = 16385;
/*      */   public static final int GL_LIGHT2 = 16386;
/*      */   public static final int GL_LIGHT3 = 16387;
/*      */   public static final int GL_LIGHT4 = 16388;
/*      */   public static final int GL_LIGHT5 = 16389;
/*      */   public static final int GL_LIGHT6 = 16390;
/*      */   public static final int GL_LIGHT7 = 16391;
/*      */   public static final int GL_AMBIENT = 4608;
/*      */   public static final int GL_DIFFUSE = 4609;
/*      */   public static final int GL_SPECULAR = 4610;
/*      */   public static final int GL_POSITION = 4611;
/*      */   public static final int GL_SPOT_DIRECTION = 4612;
/*      */   public static final int GL_SPOT_EXPONENT = 4613;
/*      */   public static final int GL_SPOT_CUTOFF = 4614;
/*      */   public static final int GL_CONSTANT_ATTENUATION = 4615;
/*      */   public static final int GL_LINEAR_ATTENUATION = 4616;
/*      */   public static final int GL_QUADRATIC_ATTENUATION = 4617;
/*      */   public static final int GL_COMPILE = 4864;
/*      */   public static final int GL_COMPILE_AND_EXECUTE = 4865;
/*      */   public static final int GL_CLEAR = 5376;
/*      */   public static final int GL_AND = 5377;
/*      */   public static final int GL_AND_REVERSE = 5378;
/*      */   public static final int GL_COPY = 5379;
/*      */   public static final int GL_AND_INVERTED = 5380;
/*      */   public static final int GL_NOOP = 5381;
/*      */   public static final int GL_XOR = 5382;
/*      */   public static final int GL_OR = 5383;
/*      */   public static final int GL_NOR = 5384;
/*      */   public static final int GL_EQUIV = 5385;
/*      */   public static final int GL_INVERT = 5386;
/*      */   public static final int GL_OR_REVERSE = 5387;
/*      */   public static final int GL_COPY_INVERTED = 5388;
/*      */   public static final int GL_OR_INVERTED = 5389;
/*      */   public static final int GL_NAND = 5390;
/*      */   public static final int GL_SET = 5391;
/*      */   public static final int GL_EMISSION = 5632;
/*      */   public static final int GL_SHININESS = 5633;
/*      */   public static final int GL_AMBIENT_AND_DIFFUSE = 5634;
/*      */   public static final int GL_COLOR_INDEXES = 5635;
/*      */   public static final int GL_MODELVIEW = 5888;
/*      */   public static final int GL_PROJECTION = 5889;
/*      */   public static final int GL_TEXTURE = 5890;
/*      */   public static final int GL_COLOR = 6144;
/*      */   public static final int GL_DEPTH = 6145;
/*      */   public static final int GL_STENCIL = 6146;
/*      */   public static final int GL_COLOR_INDEX = 6400;
/*      */   public static final int GL_STENCIL_INDEX = 6401;
/*      */   public static final int GL_DEPTH_COMPONENT = 6402;
/*      */   public static final int GL_RED = 6403;
/*      */   public static final int GL_GREEN = 6404;
/*      */   public static final int GL_BLUE = 6405;
/*      */   public static final int GL_ALPHA = 6406;
/*      */   public static final int GL_RGB = 6407;
/*      */   public static final int GL_RGBA = 6408;
/*      */   public static final int GL_LUMINANCE = 6409;
/*      */   public static final int GL_LUMINANCE_ALPHA = 6410;
/*      */   public static final int GL_BITMAP = 6656;
/*      */   public static final int GL_POINT = 6912;
/*      */   public static final int GL_LINE = 6913;
/*      */   public static final int GL_FILL = 6914;
/*      */   public static final int GL_RENDER = 7168;
/*      */   public static final int GL_FEEDBACK = 7169;
/*      */   public static final int GL_SELECT = 7170;
/*      */   public static final int GL_FLAT = 7424;
/*      */   public static final int GL_SMOOTH = 7425;
/*      */   public static final int GL_KEEP = 7680;
/*      */   public static final int GL_REPLACE = 7681;
/*      */   public static final int GL_INCR = 7682;
/*      */   public static final int GL_DECR = 7683;
/*      */   public static final int GL_VENDOR = 7936;
/*      */   public static final int GL_RENDERER = 7937;
/*      */   public static final int GL_VERSION = 7938;
/*      */   public static final int GL_EXTENSIONS = 7939;
/*      */   public static final int GL_S = 8192;
/*      */   public static final int GL_T = 8193;
/*      */   public static final int GL_R = 8194;
/*      */   public static final int GL_Q = 8195;
/*      */   public static final int GL_MODULATE = 8448;
/*      */   public static final int GL_DECAL = 8449;
/*      */   public static final int GL_TEXTURE_ENV_MODE = 8704;
/*      */   public static final int GL_TEXTURE_ENV_COLOR = 8705;
/*      */   public static final int GL_TEXTURE_ENV = 8960;
/*      */   public static final int GL_EYE_LINEAR = 9216;
/*      */   public static final int GL_OBJECT_LINEAR = 9217;
/*      */   public static final int GL_SPHERE_MAP = 9218;
/*      */   public static final int GL_TEXTURE_GEN_MODE = 9472;
/*      */   public static final int GL_OBJECT_PLANE = 9473;
/*      */   public static final int GL_EYE_PLANE = 9474;
/*      */   public static final int GL_NEAREST = 9728;
/*      */   public static final int GL_LINEAR = 9729;
/*      */   public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;
/*      */   public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;
/*      */   public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;
/*      */   public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;
/*      */   public static final int GL_TEXTURE_MAG_FILTER = 10240;
/*      */   public static final int GL_TEXTURE_MIN_FILTER = 10241;
/*      */   public static final int GL_TEXTURE_WRAP_S = 10242;
/*      */   public static final int GL_TEXTURE_WRAP_T = 10243;
/*      */   public static final int GL_CLAMP = 10496;
/*      */   public static final int GL_REPEAT = 10497;
/*      */   public static final int GL_CLIENT_PIXEL_STORE_BIT = 1;
/*      */   public static final int GL_CLIENT_VERTEX_ARRAY_BIT = 2;
/*      */   public static final int GL_ALL_CLIENT_ATTRIB_BITS = -1;
/*      */   public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
/*      */   public static final int GL_POLYGON_OFFSET_UNITS = 10752;
/*      */   public static final int GL_POLYGON_OFFSET_POINT = 10753;
/*      */   public static final int GL_POLYGON_OFFSET_LINE = 10754;
/*      */   public static final int GL_POLYGON_OFFSET_FILL = 32823;
/*      */   public static final int GL_ALPHA4 = 32827;
/*      */   public static final int GL_ALPHA8 = 32828;
/*      */   public static final int GL_ALPHA12 = 32829;
/*      */   public static final int GL_ALPHA16 = 32830;
/*      */   public static final int GL_LUMINANCE4 = 32831;
/*      */   public static final int GL_LUMINANCE8 = 32832;
/*      */   public static final int GL_LUMINANCE12 = 32833;
/*      */   public static final int GL_LUMINANCE16 = 32834;
/*      */   public static final int GL_LUMINANCE4_ALPHA4 = 32835;
/*      */   public static final int GL_LUMINANCE6_ALPHA2 = 32836;
/*      */   public static final int GL_LUMINANCE8_ALPHA8 = 32837;
/*      */   public static final int GL_LUMINANCE12_ALPHA4 = 32838;
/*      */   public static final int GL_LUMINANCE12_ALPHA12 = 32839;
/*      */   public static final int GL_LUMINANCE16_ALPHA16 = 32840;
/*      */   public static final int GL_INTENSITY = 32841;
/*      */   public static final int GL_INTENSITY4 = 32842;
/*      */   public static final int GL_INTENSITY8 = 32843;
/*      */   public static final int GL_INTENSITY12 = 32844;
/*      */   public static final int GL_INTENSITY16 = 32845;
/*      */   public static final int GL_R3_G3_B2 = 10768;
/*      */   public static final int GL_RGB4 = 32847;
/*      */   public static final int GL_RGB5 = 32848;
/*      */   public static final int GL_RGB8 = 32849;
/*      */   public static final int GL_RGB10 = 32850;
/*      */   public static final int GL_RGB12 = 32851;
/*      */   public static final int GL_RGB16 = 32852;
/*      */   public static final int GL_RGBA2 = 32853;
/*      */   public static final int GL_RGBA4 = 32854;
/*      */   public static final int GL_RGB5_A1 = 32855;
/*      */   public static final int GL_RGBA8 = 32856;
/*      */   public static final int GL_RGB10_A2 = 32857;
/*      */   public static final int GL_RGBA12 = 32858;
/*      */   public static final int GL_RGBA16 = 32859;
/*      */   public static final int GL_TEXTURE_RED_SIZE = 32860;
/*      */   public static final int GL_TEXTURE_GREEN_SIZE = 32861;
/*      */   public static final int GL_TEXTURE_BLUE_SIZE = 32862;
/*      */   public static final int GL_TEXTURE_ALPHA_SIZE = 32863;
/*      */   public static final int GL_TEXTURE_LUMINANCE_SIZE = 32864;
/*      */   public static final int GL_TEXTURE_INTENSITY_SIZE = 32865;
/*      */   public static final int GL_PROXY_TEXTURE_1D = 32867;
/*      */   public static final int GL_PROXY_TEXTURE_2D = 32868;
/*      */   public static final int GL_TEXTURE_PRIORITY = 32870;
/*      */   public static final int GL_TEXTURE_RESIDENT = 32871;
/*      */   public static final int GL_TEXTURE_BINDING_1D = 32872;
/*      */   public static final int GL_TEXTURE_BINDING_2D = 32873;
/*      */   public static final int GL_VERTEX_ARRAY = 32884;
/*      */   public static final int GL_NORMAL_ARRAY = 32885;
/*      */   public static final int GL_COLOR_ARRAY = 32886;
/*      */   public static final int GL_INDEX_ARRAY = 32887;
/*      */   public static final int GL_TEXTURE_COORD_ARRAY = 32888;
/*      */   public static final int GL_EDGE_FLAG_ARRAY = 32889;
/*      */   public static final int GL_VERTEX_ARRAY_SIZE = 32890;
/*      */   public static final int GL_VERTEX_ARRAY_TYPE = 32891;
/*      */   public static final int GL_VERTEX_ARRAY_STRIDE = 32892;
/*      */   public static final int GL_NORMAL_ARRAY_TYPE = 32894;
/*      */   public static final int GL_NORMAL_ARRAY_STRIDE = 32895;
/*      */   public static final int GL_COLOR_ARRAY_SIZE = 32897;
/*      */   public static final int GL_COLOR_ARRAY_TYPE = 32898;
/*      */   public static final int GL_COLOR_ARRAY_STRIDE = 32899;
/*      */   public static final int GL_INDEX_ARRAY_TYPE = 32901;
/*      */   public static final int GL_INDEX_ARRAY_STRIDE = 32902;
/*      */   public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 32904;
/*      */   public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 32905;
/*      */   public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 32906;
/*      */   public static final int GL_EDGE_FLAG_ARRAY_STRIDE = 32908;
/*      */   public static final int GL_VERTEX_ARRAY_POINTER = 32910;
/*      */   public static final int GL_NORMAL_ARRAY_POINTER = 32911;
/*      */   public static final int GL_COLOR_ARRAY_POINTER = 32912;
/*      */   public static final int GL_INDEX_ARRAY_POINTER = 32913;
/*      */   public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 32914;
/*      */   public static final int GL_EDGE_FLAG_ARRAY_POINTER = 32915;
/*      */   public static final int GL_V2F = 10784;
/*      */   public static final int GL_V3F = 10785;
/*      */   public static final int GL_C4UB_V2F = 10786;
/*      */   public static final int GL_C4UB_V3F = 10787;
/*      */   public static final int GL_C3F_V3F = 10788;
/*      */   public static final int GL_N3F_V3F = 10789;
/*      */   public static final int GL_C4F_N3F_V3F = 10790;
/*      */   public static final int GL_T2F_V3F = 10791;
/*      */   public static final int GL_T4F_V4F = 10792;
/*      */   public static final int GL_T2F_C4UB_V3F = 10793;
/*      */   public static final int GL_T2F_C3F_V3F = 10794;
/*      */   public static final int GL_T2F_N3F_V3F = 10795;
/*      */   public static final int GL_T2F_C4F_N3F_V3F = 10796;
/*      */   public static final int GL_T4F_C4F_N3F_V4F = 10797;
/*      */   public static final int GL_LOGIC_OP = 3057;
/*      */   public static final int GL_TEXTURE_COMPONENTS = 4099;
/*      */ 
/*      */   public static void glAccum(int op, float value)
/*      */   {
/*  553 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  554 */     long function_pointer = caps.glAccum;
/*  555 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  556 */     nglAccum(op, value, function_pointer);
/*      */   }
/*      */   static native void nglAccum(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glAlphaFunc(int func, float ref) {
/*  561 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  562 */     long function_pointer = caps.glAlphaFunc;
/*  563 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  564 */     nglAlphaFunc(func, ref, function_pointer);
/*      */   }
/*      */   static native void nglAlphaFunc(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glClearColor(float red, float green, float blue, float alpha) {
/*  569 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  570 */     long function_pointer = caps.glClearColor;
/*  571 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  572 */     nglClearColor(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglClearColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glClearAccum(float red, float green, float blue, float alpha) {
/*  577 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  578 */     long function_pointer = caps.glClearAccum;
/*  579 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  580 */     nglClearAccum(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglClearAccum(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glClear(int mask) {
/*  585 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  586 */     long function_pointer = caps.glClear;
/*  587 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  588 */     nglClear(mask, function_pointer);
/*      */   }
/*      */   static native void nglClear(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glCallLists(ByteBuffer lists) {
/*  593 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  594 */     long function_pointer = caps.glCallLists;
/*  595 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  596 */     BufferChecks.checkDirect(lists);
/*  597 */     nglCallLists(lists.remaining(), 5121, MemoryUtil.getAddress(lists), function_pointer);
/*      */   }
/*      */   public static void glCallLists(IntBuffer lists) {
/*  600 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  601 */     long function_pointer = caps.glCallLists;
/*  602 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  603 */     BufferChecks.checkDirect(lists);
/*  604 */     nglCallLists(lists.remaining(), 5125, MemoryUtil.getAddress(lists), function_pointer);
/*      */   }
/*      */   public static void glCallLists(ShortBuffer lists) {
/*  607 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  608 */     long function_pointer = caps.glCallLists;
/*  609 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  610 */     BufferChecks.checkDirect(lists);
/*  611 */     nglCallLists(lists.remaining(), 5123, MemoryUtil.getAddress(lists), function_pointer);
/*      */   }
/*      */   static native void nglCallLists(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCallList(int list) {
/*  616 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  617 */     long function_pointer = caps.glCallList;
/*  618 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  619 */     nglCallList(list, function_pointer);
/*      */   }
/*      */   static native void nglCallList(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glBlendFunc(int sfactor, int dfactor) {
/*  624 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  625 */     long function_pointer = caps.glBlendFunc;
/*  626 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  627 */     nglBlendFunc(sfactor, dfactor, function_pointer);
/*      */   }
/*      */   static native void nglBlendFunc(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) {
/*  632 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  633 */     long function_pointer = caps.glBitmap;
/*  634 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  635 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  636 */     if (bitmap != null)
/*  637 */       BufferChecks.checkBuffer(bitmap, (width + 7) / 8 * height);
/*  638 */     nglBitmap(width, height, xorig, yorig, xmove, ymove, MemoryUtil.getAddressSafe(bitmap), function_pointer);
/*      */   }
/*      */   static native void nglBitmap(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2);
/*      */ 
/*  642 */   public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, long bitmap_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  643 */     long function_pointer = caps.glBitmap;
/*  644 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  645 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  646 */     nglBitmapBO(width, height, xorig, yorig, xmove, ymove, bitmap_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglBitmapBO(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glBindTexture(int target, int texture) {
/*  651 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  652 */     long function_pointer = caps.glBindTexture;
/*  653 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  654 */     nglBindTexture(target, texture, function_pointer);
/*      */   }
/*      */   static native void nglBindTexture(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glPrioritizeTextures(IntBuffer textures, FloatBuffer priorities) {
/*  659 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  660 */     long function_pointer = caps.glPrioritizeTextures;
/*  661 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  662 */     BufferChecks.checkDirect(textures);
/*  663 */     BufferChecks.checkBuffer(priorities, textures.remaining());
/*  664 */     nglPrioritizeTextures(textures.remaining(), MemoryUtil.getAddress(textures), MemoryUtil.getAddress(priorities), function_pointer);
/*      */   }
/*      */   static native void nglPrioritizeTextures(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static boolean glAreTexturesResident(IntBuffer textures, ByteBuffer residences) {
/*  669 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  670 */     long function_pointer = caps.glAreTexturesResident;
/*  671 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  672 */     BufferChecks.checkDirect(textures);
/*  673 */     BufferChecks.checkBuffer(residences, textures.remaining());
/*  674 */     boolean __result = nglAreTexturesResident(textures.remaining(), MemoryUtil.getAddress(textures), MemoryUtil.getAddress(residences), function_pointer);
/*  675 */     return __result;
/*      */   }
/*      */   static native boolean nglAreTexturesResident(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glBegin(int mode) {
/*  680 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  681 */     long function_pointer = caps.glBegin;
/*  682 */     BufferChecks.checkFunctionAddress(function_pointer);
/*      */ 
/*  684 */     nglBegin(mode, function_pointer);
/*      */   }
/*      */   static native void nglBegin(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glEnd() {
/*  689 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  690 */     long function_pointer = caps.glEnd;
/*  691 */     BufferChecks.checkFunctionAddress(function_pointer);
/*      */ 
/*  693 */     nglEnd(function_pointer);
/*      */   }
/*      */   static native void nglEnd(long paramLong);
/*      */ 
/*      */   public static void glArrayElement(int i) {
/*  698 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  699 */     long function_pointer = caps.glArrayElement;
/*  700 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  701 */     nglArrayElement(i, function_pointer);
/*      */   }
/*      */   static native void nglArrayElement(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glClearDepth(double depth) {
/*  706 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  707 */     long function_pointer = caps.glClearDepth;
/*  708 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  709 */     nglClearDepth(depth, function_pointer);
/*      */   }
/*      */   static native void nglClearDepth(double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glDeleteLists(int list, int range) {
/*  714 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  715 */     long function_pointer = caps.glDeleteLists;
/*  716 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  717 */     nglDeleteLists(list, range, function_pointer);
/*      */   }
/*      */   static native void nglDeleteLists(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDeleteTextures(IntBuffer textures) {
/*  722 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  723 */     long function_pointer = caps.glDeleteTextures;
/*  724 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  725 */     BufferChecks.checkDirect(textures);
/*  726 */     nglDeleteTextures(textures.remaining(), MemoryUtil.getAddress(textures), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDeleteTextures(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDeleteTextures(int texture) {
/*  732 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  733 */     long function_pointer = caps.glDeleteTextures;
/*  734 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  735 */     nglDeleteTextures(1, APIUtil.getInt(caps, texture), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glCullFace(int mode) {
/*  739 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  740 */     long function_pointer = caps.glCullFace;
/*  741 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  742 */     nglCullFace(mode, function_pointer);
/*      */   }
/*      */   static native void nglCullFace(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
/*  747 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  748 */     long function_pointer = caps.glCopyTexSubImage2D;
/*  749 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  750 */     nglCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglCopyTexSubImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*      */ 
/*      */   public static void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) {
/*  755 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  756 */     long function_pointer = caps.glCopyTexSubImage1D;
/*  757 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  758 */     nglCopyTexSubImage1D(target, level, xoffset, x, y, width, function_pointer);
/*      */   }
/*      */   static native void nglCopyTexSubImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) {
/*  763 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  764 */     long function_pointer = caps.glCopyTexImage2D;
/*  765 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  766 */     nglCopyTexImage2D(target, level, internalFormat, x, y, width, height, border, function_pointer);
/*      */   }
/*      */   static native void nglCopyTexImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*      */ 
/*      */   public static void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) {
/*  771 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  772 */     long function_pointer = caps.glCopyTexImage1D;
/*  773 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  774 */     nglCopyTexImage1D(target, level, internalFormat, x, y, width, border, function_pointer);
/*      */   }
/*      */   static native void nglCopyTexImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*      */ 
/*      */   public static void glCopyPixels(int x, int y, int width, int height, int type) {
/*  779 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  780 */     long function_pointer = caps.glCopyPixels;
/*  781 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  782 */     nglCopyPixels(x, y, width, height, type, function_pointer);
/*      */   }
/*      */   static native void nglCopyPixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glColorPointer(int size, int stride, DoubleBuffer pointer) {
/*  787 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  788 */     long function_pointer = caps.glColorPointer;
/*  789 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  790 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  791 */     BufferChecks.checkDirect(pointer);
/*  792 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  793 */     nglColorPointer(size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glColorPointer(int size, int stride, FloatBuffer pointer) {
/*  796 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  797 */     long function_pointer = caps.glColorPointer;
/*  798 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  799 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  800 */     BufferChecks.checkDirect(pointer);
/*  801 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  802 */     nglColorPointer(size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) {
/*  805 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  806 */     long function_pointer = caps.glColorPointer;
/*  807 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  808 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  809 */     BufferChecks.checkDirect(pointer);
/*  810 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  811 */     nglColorPointer(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglColorPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*  815 */   public static void glColorPointer(int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  816 */     long function_pointer = caps.glColorPointer;
/*  817 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  818 */     GLChecks.ensureArrayVBOenabled(caps);
/*  819 */     nglColorPointerBO(size, type, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglColorPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glColorPointer(int size, int type, int stride, ByteBuffer pointer)
/*      */   {
/*  825 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  826 */     long function_pointer = caps.glColorPointer;
/*  827 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  828 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  829 */     BufferChecks.checkDirect(pointer);
/*  830 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  831 */     nglColorPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glColorMaterial(int face, int mode) {
/*  835 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  836 */     long function_pointer = caps.glColorMaterial;
/*  837 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  838 */     nglColorMaterial(face, mode, function_pointer);
/*      */   }
/*      */   static native void nglColorMaterial(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
/*  843 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  844 */     long function_pointer = caps.glColorMask;
/*  845 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  846 */     nglColorMask(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglColorMask(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, long paramLong);
/*      */ 
/*      */   public static void glColor3b(byte red, byte green, byte blue) {
/*  851 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  852 */     long function_pointer = caps.glColor3b;
/*  853 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  854 */     nglColor3b(red, green, blue, function_pointer);
/*      */   }
/*      */   static native void nglColor3b(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*      */ 
/*      */   public static void glColor3f(float red, float green, float blue) {
/*  859 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  860 */     long function_pointer = caps.glColor3f;
/*  861 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  862 */     nglColor3f(red, green, blue, function_pointer);
/*      */   }
/*      */   static native void nglColor3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glColor3d(double red, double green, double blue) {
/*  867 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  868 */     long function_pointer = caps.glColor3d;
/*  869 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  870 */     nglColor3d(red, green, blue, function_pointer);
/*      */   }
/*      */   static native void nglColor3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glColor3ub(byte red, byte green, byte blue) {
/*  875 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  876 */     long function_pointer = caps.glColor3ub;
/*  877 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  878 */     nglColor3ub(red, green, blue, function_pointer);
/*      */   }
/*      */   static native void nglColor3ub(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*      */ 
/*      */   public static void glColor4b(byte red, byte green, byte blue, byte alpha) {
/*  883 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  884 */     long function_pointer = caps.glColor4b;
/*  885 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  886 */     nglColor4b(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglColor4b(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*      */ 
/*      */   public static void glColor4f(float red, float green, float blue, float alpha) {
/*  891 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  892 */     long function_pointer = caps.glColor4f;
/*  893 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  894 */     nglColor4f(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglColor4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glColor4d(double red, double green, double blue, double alpha) {
/*  899 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  900 */     long function_pointer = caps.glColor4d;
/*  901 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  902 */     nglColor4d(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglColor4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glColor4ub(byte red, byte green, byte blue, byte alpha) {
/*  907 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  908 */     long function_pointer = caps.glColor4ub;
/*  909 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  910 */     nglColor4ub(red, green, blue, alpha, function_pointer);
/*      */   }
/*      */   static native void nglColor4ub(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*      */ 
/*      */   public static void glClipPlane(int plane, DoubleBuffer equation) {
/*  915 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  916 */     long function_pointer = caps.glClipPlane;
/*  917 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  918 */     BufferChecks.checkBuffer(equation, 4);
/*  919 */     nglClipPlane(plane, MemoryUtil.getAddress(equation), function_pointer);
/*      */   }
/*      */   static native void nglClipPlane(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glClearStencil(int s) {
/*  924 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  925 */     long function_pointer = caps.glClearStencil;
/*  926 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  927 */     nglClearStencil(s, function_pointer);
/*      */   }
/*      */   static native void nglClearStencil(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glEvalPoint1(int i) {
/*  932 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  933 */     long function_pointer = caps.glEvalPoint1;
/*  934 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  935 */     nglEvalPoint1(i, function_pointer);
/*      */   }
/*      */   static native void nglEvalPoint1(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glEvalPoint2(int i, int j) {
/*  940 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  941 */     long function_pointer = caps.glEvalPoint2;
/*  942 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  943 */     nglEvalPoint2(i, j, function_pointer);
/*      */   }
/*      */   static native void nglEvalPoint2(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glEvalMesh1(int mode, int i1, int i2) {
/*  948 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  949 */     long function_pointer = caps.glEvalMesh1;
/*  950 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  951 */     nglEvalMesh1(mode, i1, i2, function_pointer);
/*      */   }
/*      */   static native void nglEvalMesh1(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glEvalMesh2(int mode, int i1, int i2, int j1, int j2) {
/*  956 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  957 */     long function_pointer = caps.glEvalMesh2;
/*  958 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  959 */     nglEvalMesh2(mode, i1, i2, j1, j2, function_pointer);
/*      */   }
/*      */   static native void nglEvalMesh2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glEvalCoord1f(float u) {
/*  964 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  965 */     long function_pointer = caps.glEvalCoord1f;
/*  966 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  967 */     nglEvalCoord1f(u, function_pointer);
/*      */   }
/*      */   static native void nglEvalCoord1f(float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glEvalCoord1d(double u) {
/*  972 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  973 */     long function_pointer = caps.glEvalCoord1d;
/*  974 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  975 */     nglEvalCoord1d(u, function_pointer);
/*      */   }
/*      */   static native void nglEvalCoord1d(double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glEvalCoord2f(float u, float v) {
/*  980 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  981 */     long function_pointer = caps.glEvalCoord2f;
/*  982 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  983 */     nglEvalCoord2f(u, v, function_pointer);
/*      */   }
/*      */   static native void nglEvalCoord2f(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glEvalCoord2d(double u, double v) {
/*  988 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  989 */     long function_pointer = caps.glEvalCoord2d;
/*  990 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  991 */     nglEvalCoord2d(u, v, function_pointer);
/*      */   }
/*      */   static native void nglEvalCoord2d(double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glEnableClientState(int cap) {
/*  996 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  997 */     long function_pointer = caps.glEnableClientState;
/*  998 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  999 */     nglEnableClientState(cap, function_pointer);
/*      */   }
/*      */   static native void nglEnableClientState(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDisableClientState(int cap) {
/* 1004 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1005 */     long function_pointer = caps.glDisableClientState;
/* 1006 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1007 */     nglDisableClientState(cap, function_pointer);
/*      */   }
/*      */   static native void nglDisableClientState(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glEnable(int cap) {
/* 1012 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1013 */     long function_pointer = caps.glEnable;
/* 1014 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1015 */     nglEnable(cap, function_pointer);
/*      */   }
/*      */   static native void nglEnable(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDisable(int cap) {
/* 1020 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1021 */     long function_pointer = caps.glDisable;
/* 1022 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1023 */     nglDisable(cap, function_pointer);
/*      */   }
/*      */   static native void nglDisable(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
/* 1028 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1029 */     long function_pointer = caps.glEdgeFlagPointer;
/* 1030 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1031 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 1032 */     BufferChecks.checkDirect(pointer);
/* 1033 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glEdgeFlagPointer_pointer = pointer;
/* 1034 */     nglEdgeFlagPointer(stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglEdgeFlagPointer(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/* 1038 */   public static void glEdgeFlagPointer(int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1039 */     long function_pointer = caps.glEdgeFlagPointer;
/* 1040 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1041 */     GLChecks.ensureArrayVBOenabled(caps);
/* 1042 */     nglEdgeFlagPointerBO(stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglEdgeFlagPointerBO(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glEdgeFlag(boolean flag) {
/* 1047 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1048 */     long function_pointer = caps.glEdgeFlag;
/* 1049 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1050 */     nglEdgeFlag(flag, function_pointer);
/*      */   }
/*      */   static native void nglEdgeFlag(boolean paramBoolean, long paramLong);
/*      */ 
/*      */   public static void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
/* 1055 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1056 */     long function_pointer = caps.glDrawPixels;
/* 1057 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1058 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1059 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1060 */     nglDrawPixels(width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
/* 1063 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1064 */     long function_pointer = caps.glDrawPixels;
/* 1065 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1066 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1067 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1068 */     nglDrawPixels(width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
/* 1071 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1072 */     long function_pointer = caps.glDrawPixels;
/* 1073 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1074 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1075 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1076 */     nglDrawPixels(width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglDrawPixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/* 1080 */   public static void glDrawPixels(int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1081 */     long function_pointer = caps.glDrawPixels;
/* 1082 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1083 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1084 */     nglDrawPixelsBO(width, height, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglDrawPixelsBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDrawElements(int mode, ByteBuffer indices) {
/* 1089 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1090 */     long function_pointer = caps.glDrawElements;
/* 1091 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1092 */     GLChecks.ensureElementVBOdisabled(caps);
/* 1093 */     BufferChecks.checkDirect(indices);
/* 1094 */     nglDrawElements(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), function_pointer);
/*      */   }
/*      */   public static void glDrawElements(int mode, IntBuffer indices) {
/* 1097 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1098 */     long function_pointer = caps.glDrawElements;
/* 1099 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1100 */     GLChecks.ensureElementVBOdisabled(caps);
/* 1101 */     BufferChecks.checkDirect(indices);
/* 1102 */     nglDrawElements(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), function_pointer);
/*      */   }
/*      */   public static void glDrawElements(int mode, ShortBuffer indices) {
/* 1105 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1106 */     long function_pointer = caps.glDrawElements;
/* 1107 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1108 */     GLChecks.ensureElementVBOdisabled(caps);
/* 1109 */     BufferChecks.checkDirect(indices);
/* 1110 */     nglDrawElements(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), function_pointer);
/*      */   }
/*      */   static native void nglDrawElements(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/* 1114 */   public static void glDrawElements(int mode, int indices_count, int type, long indices_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1115 */     long function_pointer = caps.glDrawElements;
/* 1116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1117 */     GLChecks.ensureElementVBOenabled(caps);
/* 1118 */     nglDrawElementsBO(mode, indices_count, type, indices_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglDrawElementsBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDrawBuffer(int mode) {
/* 1123 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1124 */     long function_pointer = caps.glDrawBuffer;
/* 1125 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1126 */     nglDrawBuffer(mode, function_pointer);
/*      */   }
/*      */   static native void nglDrawBuffer(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDrawArrays(int mode, int first, int count) {
/* 1131 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1132 */     long function_pointer = caps.glDrawArrays;
/* 1133 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1134 */     nglDrawArrays(mode, first, count, function_pointer);
/*      */   }
/*      */   static native void nglDrawArrays(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glDepthRange(double zNear, double zFar) {
/* 1139 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1140 */     long function_pointer = caps.glDepthRange;
/* 1141 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1142 */     nglDepthRange(zNear, zFar, function_pointer);
/*      */   }
/*      */   static native void nglDepthRange(double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glDepthMask(boolean flag) {
/* 1147 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1148 */     long function_pointer = caps.glDepthMask;
/* 1149 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1150 */     nglDepthMask(flag, function_pointer);
/*      */   }
/*      */   static native void nglDepthMask(boolean paramBoolean, long paramLong);
/*      */ 
/*      */   public static void glDepthFunc(int func) {
/* 1155 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1156 */     long function_pointer = caps.glDepthFunc;
/* 1157 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1158 */     nglDepthFunc(func, function_pointer);
/*      */   }
/*      */   static native void nglDepthFunc(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glFeedbackBuffer(int type, FloatBuffer buffer) {
/* 1163 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1164 */     long function_pointer = caps.glFeedbackBuffer;
/* 1165 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1166 */     BufferChecks.checkDirect(buffer);
/* 1167 */     nglFeedbackBuffer(buffer.remaining(), type, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   static native void nglFeedbackBuffer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetPixelMap(int map, FloatBuffer values) {
/* 1172 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1173 */     long function_pointer = caps.glGetPixelMapfv;
/* 1174 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1175 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1176 */     BufferChecks.checkBuffer(values, 256);
/* 1177 */     nglGetPixelMapfv(map, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetPixelMapfv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/* 1181 */   public static void glGetPixelMapfv(int map, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1182 */     long function_pointer = caps.glGetPixelMapfv;
/* 1183 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1184 */     GLChecks.ensurePackPBOenabled(caps);
/* 1185 */     nglGetPixelMapfvBO(map, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetPixelMapfvBO(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetPixelMapu(int map, IntBuffer values) {
/* 1190 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1191 */     long function_pointer = caps.glGetPixelMapuiv;
/* 1192 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1193 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1194 */     BufferChecks.checkBuffer(values, 256);
/* 1195 */     nglGetPixelMapuiv(map, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetPixelMapuiv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/* 1199 */   public static void glGetPixelMapuiv(int map, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1200 */     long function_pointer = caps.glGetPixelMapuiv;
/* 1201 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1202 */     GLChecks.ensurePackPBOenabled(caps);
/* 1203 */     nglGetPixelMapuivBO(map, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetPixelMapuivBO(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetPixelMapu(int map, ShortBuffer values) {
/* 1208 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1209 */     long function_pointer = caps.glGetPixelMapusv;
/* 1210 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1211 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1212 */     BufferChecks.checkBuffer(values, 256);
/* 1213 */     nglGetPixelMapusv(map, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetPixelMapusv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/* 1217 */   public static void glGetPixelMapusv(int map, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1218 */     long function_pointer = caps.glGetPixelMapusv;
/* 1219 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1220 */     GLChecks.ensurePackPBOenabled(caps);
/* 1221 */     nglGetPixelMapusvBO(map, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetPixelMapusvBO(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMaterial(int face, int pname, FloatBuffer params) {
/* 1226 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1227 */     long function_pointer = caps.glGetMaterialfv;
/* 1228 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1229 */     BufferChecks.checkBuffer(params, 4);
/* 1230 */     nglGetMaterialfv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMaterialfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMaterial(int face, int pname, IntBuffer params) {
/* 1235 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1236 */     long function_pointer = caps.glGetMaterialiv;
/* 1237 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1238 */     BufferChecks.checkBuffer(params, 4);
/* 1239 */     nglGetMaterialiv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMaterialiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMap(int target, int query, FloatBuffer v) {
/* 1244 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1245 */     long function_pointer = caps.glGetMapfv;
/* 1246 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1247 */     BufferChecks.checkBuffer(v, 256);
/* 1248 */     nglGetMapfv(target, query, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglGetMapfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMap(int target, int query, DoubleBuffer v) {
/* 1253 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1254 */     long function_pointer = caps.glGetMapdv;
/* 1255 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1256 */     BufferChecks.checkBuffer(v, 256);
/* 1257 */     nglGetMapdv(target, query, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglGetMapdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMap(int target, int query, IntBuffer v) {
/* 1262 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1263 */     long function_pointer = caps.glGetMapiv;
/* 1264 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1265 */     BufferChecks.checkBuffer(v, 256);
/* 1266 */     nglGetMapiv(target, query, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglGetMapiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetLight(int light, int pname, FloatBuffer params) {
/* 1271 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1272 */     long function_pointer = caps.glGetLightfv;
/* 1273 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1274 */     BufferChecks.checkBuffer(params, 4);
/* 1275 */     nglGetLightfv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetLightfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetLight(int light, int pname, IntBuffer params) {
/* 1280 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1281 */     long function_pointer = caps.glGetLightiv;
/* 1282 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1283 */     BufferChecks.checkBuffer(params, 4);
/* 1284 */     nglGetLightiv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetLightiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetError() {
/* 1289 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1290 */     long function_pointer = caps.glGetError;
/* 1291 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1292 */     int __result = nglGetError(function_pointer);
/* 1293 */     return __result;
/*      */   }
/*      */   static native int nglGetError(long paramLong);
/*      */ 
/*      */   public static void glGetClipPlane(int plane, DoubleBuffer equation) {
/* 1298 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1299 */     long function_pointer = caps.glGetClipPlane;
/* 1300 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1301 */     BufferChecks.checkBuffer(equation, 4);
/* 1302 */     nglGetClipPlane(plane, MemoryUtil.getAddress(equation), function_pointer);
/*      */   }
/*      */   static native void nglGetClipPlane(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetBoolean(int pname, ByteBuffer params) {
/* 1307 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1308 */     long function_pointer = caps.glGetBooleanv;
/* 1309 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1310 */     BufferChecks.checkBuffer(params, 16);
/* 1311 */     nglGetBooleanv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetBooleanv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static boolean glGetBoolean(int pname) {
/* 1317 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1318 */     long function_pointer = caps.glGetBooleanv;
/* 1319 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1320 */     ByteBuffer params = APIUtil.getBufferByte(caps, 1);
/* 1321 */     nglGetBooleanv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1322 */     return params.get(0) == 1;
/*      */   }
/*      */ 
/*      */   public static void glGetDouble(int pname, DoubleBuffer params) {
/* 1326 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1327 */     long function_pointer = caps.glGetDoublev;
/* 1328 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1329 */     BufferChecks.checkBuffer(params, 16);
/* 1330 */     nglGetDoublev(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetDoublev(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static double glGetDouble(int pname) {
/* 1336 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1337 */     long function_pointer = caps.glGetDoublev;
/* 1338 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1339 */     DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1340 */     nglGetDoublev(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1341 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetFloat(int pname, FloatBuffer params) {
/* 1345 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1346 */     long function_pointer = caps.glGetFloatv;
/* 1347 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1348 */     BufferChecks.checkBuffer(params, 16);
/* 1349 */     nglGetFloatv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFloatv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetFloat(int pname) {
/* 1355 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1356 */     long function_pointer = caps.glGetFloatv;
/* 1357 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1358 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1359 */     nglGetFloatv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1360 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetInteger(int pname, IntBuffer params) {
/* 1364 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1365 */     long function_pointer = caps.glGetIntegerv;
/* 1366 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1367 */     BufferChecks.checkBuffer(params, 16);
/* 1368 */     nglGetIntegerv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetIntegerv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetInteger(int pname) {
/* 1374 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1375 */     long function_pointer = caps.glGetIntegerv;
/* 1376 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1377 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1378 */     nglGetIntegerv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1379 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGenTextures(IntBuffer textures) {
/* 1383 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1384 */     long function_pointer = caps.glGenTextures;
/* 1385 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1386 */     BufferChecks.checkDirect(textures);
/* 1387 */     nglGenTextures(textures.remaining(), MemoryUtil.getAddress(textures), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGenTextures(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGenTextures() {
/* 1393 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1394 */     long function_pointer = caps.glGenTextures;
/* 1395 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1396 */     IntBuffer textures = APIUtil.getBufferInt(caps);
/* 1397 */     nglGenTextures(1, MemoryUtil.getAddress(textures), function_pointer);
/* 1398 */     return textures.get(0);
/*      */   }
/*      */ 
/*      */   public static int glGenLists(int range) {
/* 1402 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1403 */     long function_pointer = caps.glGenLists;
/* 1404 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1405 */     int __result = nglGenLists(range, function_pointer);
/* 1406 */     return __result;
/*      */   }
/*      */   static native int nglGenLists(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar) {
/* 1411 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1412 */     long function_pointer = caps.glFrustum;
/* 1413 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1414 */     nglFrustum(left, right, bottom, top, zNear, zFar, function_pointer);
/*      */   }
/*      */   static native void nglFrustum(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/*      */ 
/*      */   public static void glFrontFace(int mode) {
/* 1419 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1420 */     long function_pointer = caps.glFrontFace;
/* 1421 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1422 */     nglFrontFace(mode, function_pointer);
/*      */   }
/*      */   static native void nglFrontFace(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glFogf(int pname, float param) {
/* 1427 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1428 */     long function_pointer = caps.glFogf;
/* 1429 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1430 */     nglFogf(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglFogf(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glFogi(int pname, int param) {
/* 1435 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1436 */     long function_pointer = caps.glFogi;
/* 1437 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1438 */     nglFogi(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglFogi(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glFog(int pname, FloatBuffer params) {
/* 1443 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1444 */     long function_pointer = caps.glFogfv;
/* 1445 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1446 */     BufferChecks.checkBuffer(params, 4);
/* 1447 */     nglFogfv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglFogfv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glFog(int pname, IntBuffer params) {
/* 1452 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1453 */     long function_pointer = caps.glFogiv;
/* 1454 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1455 */     BufferChecks.checkBuffer(params, 4);
/* 1456 */     nglFogiv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglFogiv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glFlush() {
/* 1461 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1462 */     long function_pointer = caps.glFlush;
/* 1463 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1464 */     nglFlush(function_pointer);
/*      */   }
/*      */   static native void nglFlush(long paramLong);
/*      */ 
/*      */   public static void glFinish() {
/* 1469 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1470 */     long function_pointer = caps.glFinish;
/* 1471 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1472 */     nglFinish(function_pointer);
/*      */   }
/*      */   static native void nglFinish(long paramLong);
/*      */ 
/*      */   public static ByteBuffer glGetPointer(int pname, long result_size) {
/* 1477 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1478 */     long function_pointer = caps.glGetPointerv;
/* 1479 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1480 */     ByteBuffer __result = nglGetPointerv(pname, result_size, function_pointer);
/* 1481 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglGetPointerv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static boolean glIsEnabled(int cap) {
/* 1486 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1487 */     long function_pointer = caps.glIsEnabled;
/* 1488 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1489 */     boolean __result = nglIsEnabled(cap, function_pointer);
/* 1490 */     return __result;
/*      */   }
/*      */   static native boolean nglIsEnabled(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
/* 1495 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1496 */     long function_pointer = caps.glInterleavedArrays;
/* 1497 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1498 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 1499 */     BufferChecks.checkDirect(pointer);
/* 1500 */     nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glInterleavedArrays(int format, int stride, DoubleBuffer pointer) {
/* 1503 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1504 */     long function_pointer = caps.glInterleavedArrays;
/* 1505 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1506 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 1507 */     BufferChecks.checkDirect(pointer);
/* 1508 */     nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
/* 1511 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1512 */     long function_pointer = caps.glInterleavedArrays;
/* 1513 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1514 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 1515 */     BufferChecks.checkDirect(pointer);
/* 1516 */     nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
/* 1519 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1520 */     long function_pointer = caps.glInterleavedArrays;
/* 1521 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1522 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 1523 */     BufferChecks.checkDirect(pointer);
/* 1524 */     nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
/* 1527 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1528 */     long function_pointer = caps.glInterleavedArrays;
/* 1529 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1530 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 1531 */     BufferChecks.checkDirect(pointer);
/* 1532 */     nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglInterleavedArrays(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/* 1536 */   public static void glInterleavedArrays(int format, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1537 */     long function_pointer = caps.glInterleavedArrays;
/* 1538 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1539 */     GLChecks.ensureArrayVBOenabled(caps);
/* 1540 */     nglInterleavedArraysBO(format, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglInterleavedArraysBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glInitNames() {
/* 1545 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1546 */     long function_pointer = caps.glInitNames;
/* 1547 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1548 */     nglInitNames(function_pointer);
/*      */   }
/*      */   static native void nglInitNames(long paramLong);
/*      */ 
/*      */   public static void glHint(int target, int mode) {
/* 1553 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1554 */     long function_pointer = caps.glHint;
/* 1555 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1556 */     nglHint(target, mode, function_pointer);
/*      */   }
/*      */   static native void nglHint(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glGetTexParameter(int target, int pname, FloatBuffer params) {
/* 1561 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1562 */     long function_pointer = caps.glGetTexParameterfv;
/* 1563 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1564 */     BufferChecks.checkBuffer(params, 4);
/* 1565 */     nglGetTexParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetTexParameterf(int target, int pname) {
/* 1571 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1572 */     long function_pointer = caps.glGetTexParameterfv;
/* 1573 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1574 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1575 */     nglGetTexParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1576 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexParameter(int target, int pname, IntBuffer params) {
/* 1580 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1581 */     long function_pointer = caps.glGetTexParameteriv;
/* 1582 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1583 */     BufferChecks.checkBuffer(params, 4);
/* 1584 */     nglGetTexParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTexParameteri(int target, int pname) {
/* 1590 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1591 */     long function_pointer = caps.glGetTexParameteriv;
/* 1592 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1593 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1594 */     nglGetTexParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1595 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
/* 1599 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1600 */     long function_pointer = caps.glGetTexLevelParameterfv;
/* 1601 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1602 */     BufferChecks.checkBuffer(params, 4);
/* 1603 */     nglGetTexLevelParameterfv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexLevelParameterfv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetTexLevelParameterf(int target, int level, int pname) {
/* 1609 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1610 */     long function_pointer = caps.glGetTexLevelParameterfv;
/* 1611 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1612 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1613 */     nglGetTexLevelParameterfv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1614 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
/* 1618 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1619 */     long function_pointer = caps.glGetTexLevelParameteriv;
/* 1620 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1621 */     BufferChecks.checkBuffer(params, 4);
/* 1622 */     nglGetTexLevelParameteriv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexLevelParameteriv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTexLevelParameteri(int target, int level, int pname) {
/* 1628 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1629 */     long function_pointer = caps.glGetTexLevelParameteriv;
/* 1630 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1631 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1632 */     nglGetTexLevelParameteriv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1633 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
/* 1637 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1638 */     long function_pointer = caps.glGetTexImage;
/* 1639 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1640 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1641 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1642 */     nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTexImage(int target, int level, int format, int type, DoubleBuffer pixels) {
/* 1645 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1646 */     long function_pointer = caps.glGetTexImage;
/* 1647 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1648 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1649 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1650 */     nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTexImage(int target, int level, int format, int type, FloatBuffer pixels) {
/* 1653 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1654 */     long function_pointer = caps.glGetTexImage;
/* 1655 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1656 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1657 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1658 */     nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
/* 1661 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1662 */     long function_pointer = caps.glGetTexImage;
/* 1663 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1664 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1665 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1666 */     nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) {
/* 1669 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1670 */     long function_pointer = caps.glGetTexImage;
/* 1671 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1672 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1673 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1674 */     nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglGetTexImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/* 1678 */   public static void glGetTexImage(int target, int level, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1679 */     long function_pointer = caps.glGetTexImage;
/* 1680 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1681 */     GLChecks.ensurePackPBOenabled(caps);
/* 1682 */     nglGetTexImageBO(target, level, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetTexImageBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetTexGen(int coord, int pname, IntBuffer params) {
/* 1687 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1688 */     long function_pointer = caps.glGetTexGeniv;
/* 1689 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1690 */     BufferChecks.checkBuffer(params, 4);
/* 1691 */     nglGetTexGeniv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexGeniv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTexGeni(int coord, int pname) {
/* 1697 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1698 */     long function_pointer = caps.glGetTexGeniv;
/* 1699 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1700 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1701 */     nglGetTexGeniv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1702 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexGen(int coord, int pname, FloatBuffer params) {
/* 1706 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1707 */     long function_pointer = caps.glGetTexGenfv;
/* 1708 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1709 */     BufferChecks.checkBuffer(params, 4);
/* 1710 */     nglGetTexGenfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexGenfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetTexGenf(int coord, int pname) {
/* 1716 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1717 */     long function_pointer = caps.glGetTexGenfv;
/* 1718 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1719 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1720 */     nglGetTexGenfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1721 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexGen(int coord, int pname, DoubleBuffer params) {
/* 1725 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1726 */     long function_pointer = caps.glGetTexGendv;
/* 1727 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1728 */     BufferChecks.checkBuffer(params, 4);
/* 1729 */     nglGetTexGendv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexGendv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static double glGetTexGend(int coord, int pname) {
/* 1735 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1736 */     long function_pointer = caps.glGetTexGendv;
/* 1737 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1738 */     DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1739 */     nglGetTexGendv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1740 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexEnv(int coord, int pname, IntBuffer params) {
/* 1744 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1745 */     long function_pointer = caps.glGetTexEnviv;
/* 1746 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1747 */     BufferChecks.checkBuffer(params, 4);
/* 1748 */     nglGetTexEnviv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexEnviv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTexEnvi(int coord, int pname) {
/* 1754 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1755 */     long function_pointer = caps.glGetTexEnviv;
/* 1756 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1757 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1758 */     nglGetTexEnviv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1759 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTexEnv(int coord, int pname, FloatBuffer params) {
/* 1763 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1764 */     long function_pointer = caps.glGetTexEnvfv;
/* 1765 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1766 */     BufferChecks.checkBuffer(params, 4);
/* 1767 */     nglGetTexEnvfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTexEnvfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetTexEnvf(int coord, int pname) {
/* 1773 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1774 */     long function_pointer = caps.glGetTexEnvfv;
/* 1775 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1776 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1777 */     nglGetTexEnvfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1778 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static String glGetString(int name) {
/* 1782 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1783 */     long function_pointer = caps.glGetString;
/* 1784 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1785 */     String __result = nglGetString(name, function_pointer);
/* 1786 */     return __result;
/*      */   }
/*      */   static native String nglGetString(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetPolygonStipple(ByteBuffer mask) {
/* 1791 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1792 */     long function_pointer = caps.glGetPolygonStipple;
/* 1793 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1794 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1795 */     BufferChecks.checkBuffer(mask, 128);
/* 1796 */     nglGetPolygonStipple(MemoryUtil.getAddress(mask), function_pointer);
/*      */   }
/*      */   static native void nglGetPolygonStipple(long paramLong1, long paramLong2);
/*      */ 
/* 1800 */   public static void glGetPolygonStipple(long mask_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1801 */     long function_pointer = caps.glGetPolygonStipple;
/* 1802 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1803 */     GLChecks.ensurePackPBOenabled(caps);
/* 1804 */     nglGetPolygonStippleBO(mask_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetPolygonStippleBO(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static boolean glIsList(int list) {
/* 1809 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1810 */     long function_pointer = caps.glIsList;
/* 1811 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1812 */     boolean __result = nglIsList(list, function_pointer);
/* 1813 */     return __result;
/*      */   }
/*      */   static native boolean nglIsList(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glMaterialf(int face, int pname, float param) {
/* 1818 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1819 */     long function_pointer = caps.glMaterialf;
/* 1820 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1821 */     nglMaterialf(face, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMaterialf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glMateriali(int face, int pname, int param) {
/* 1826 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1827 */     long function_pointer = caps.glMateriali;
/* 1828 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1829 */     nglMateriali(face, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMateriali(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glMaterial(int face, int pname, FloatBuffer params) {
/* 1834 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1835 */     long function_pointer = caps.glMaterialfv;
/* 1836 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1837 */     BufferChecks.checkBuffer(params, 4);
/* 1838 */     nglMaterialfv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMaterialfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMaterial(int face, int pname, IntBuffer params) {
/* 1843 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1844 */     long function_pointer = caps.glMaterialiv;
/* 1845 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1846 */     BufferChecks.checkBuffer(params, 4);
/* 1847 */     nglMaterialiv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMaterialiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMapGrid1f(int un, float u1, float u2) {
/* 1852 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1853 */     long function_pointer = caps.glMapGrid1f;
/* 1854 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1855 */     nglMapGrid1f(un, u1, u2, function_pointer);
/*      */   }
/*      */   static native void nglMapGrid1f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glMapGrid1d(int un, double u1, double u2) {
/* 1860 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1861 */     long function_pointer = caps.glMapGrid1d;
/* 1862 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1863 */     nglMapGrid1d(un, u1, u2, function_pointer);
/*      */   }
/*      */   static native void nglMapGrid1d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) {
/* 1868 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1869 */     long function_pointer = caps.glMapGrid2f;
/* 1870 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1871 */     nglMapGrid2f(un, u1, u2, vn, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglMapGrid2f(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2) {
/* 1876 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1877 */     long function_pointer = caps.glMapGrid2d;
/* 1878 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1879 */     nglMapGrid2d(un, u1, u2, vn, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglMapGrid2d(int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
/* 1884 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1885 */     long function_pointer = caps.glMap2f;
/* 1886 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1887 */     BufferChecks.checkDirect(points);
/* 1888 */     nglMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
/*      */   }
/*      */   static native void nglMap2f(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, float paramFloat3, float paramFloat4, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMap2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) {
/* 1893 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1894 */     long function_pointer = caps.glMap2d;
/* 1895 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1896 */     BufferChecks.checkDirect(points);
/* 1897 */     nglMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
/*      */   }
/*      */   static native void nglMap2d(int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3, double paramDouble3, double paramDouble4, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
/* 1902 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1903 */     long function_pointer = caps.glMap1f;
/* 1904 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1905 */     BufferChecks.checkDirect(points);
/* 1906 */     nglMap1f(target, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
/*      */   }
/*      */   static native void nglMap1f(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMap1d(int target, double u1, double u2, int stride, int order, DoubleBuffer points) {
/* 1911 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1912 */     long function_pointer = caps.glMap1d;
/* 1913 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1914 */     BufferChecks.checkDirect(points);
/* 1915 */     nglMap1d(target, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
/*      */   }
/*      */   static native void nglMap1d(int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glLogicOp(int opcode) {
/* 1920 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1921 */     long function_pointer = caps.glLogicOp;
/* 1922 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1923 */     nglLogicOp(opcode, function_pointer);
/*      */   }
/*      */   static native void nglLogicOp(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glLoadName(int name) {
/* 1928 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1929 */     long function_pointer = caps.glLoadName;
/* 1930 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1931 */     nglLoadName(name, function_pointer);
/*      */   }
/*      */   static native void nglLoadName(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glLoadMatrix(FloatBuffer m) {
/* 1936 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1937 */     long function_pointer = caps.glLoadMatrixf;
/* 1938 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1939 */     BufferChecks.checkBuffer(m, 16);
/* 1940 */     nglLoadMatrixf(MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglLoadMatrixf(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glLoadMatrix(DoubleBuffer m) {
/* 1945 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1946 */     long function_pointer = caps.glLoadMatrixd;
/* 1947 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1948 */     BufferChecks.checkBuffer(m, 16);
/* 1949 */     nglLoadMatrixd(MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglLoadMatrixd(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glLoadIdentity() {
/* 1954 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1955 */     long function_pointer = caps.glLoadIdentity;
/* 1956 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1957 */     nglLoadIdentity(function_pointer);
/*      */   }
/*      */   static native void nglLoadIdentity(long paramLong);
/*      */ 
/*      */   public static void glListBase(int base) {
/* 1962 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1963 */     long function_pointer = caps.glListBase;
/* 1964 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1965 */     nglListBase(base, function_pointer);
/*      */   }
/*      */   static native void nglListBase(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glLineWidth(float width) {
/* 1970 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1971 */     long function_pointer = caps.glLineWidth;
/* 1972 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1973 */     nglLineWidth(width, function_pointer);
/*      */   }
/*      */   static native void nglLineWidth(float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glLineStipple(int factor, short pattern) {
/* 1978 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1979 */     long function_pointer = caps.glLineStipple;
/* 1980 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1981 */     nglLineStipple(factor, pattern, function_pointer);
/*      */   }
/*      */   static native void nglLineStipple(int paramInt, short paramShort, long paramLong);
/*      */ 
/*      */   public static void glLightModelf(int pname, float param) {
/* 1986 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1987 */     long function_pointer = caps.glLightModelf;
/* 1988 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1989 */     nglLightModelf(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglLightModelf(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glLightModeli(int pname, int param) {
/* 1994 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1995 */     long function_pointer = caps.glLightModeli;
/* 1996 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1997 */     nglLightModeli(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglLightModeli(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glLightModel(int pname, FloatBuffer params) {
/* 2002 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2003 */     long function_pointer = caps.glLightModelfv;
/* 2004 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2005 */     BufferChecks.checkBuffer(params, 4);
/* 2006 */     nglLightModelfv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglLightModelfv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glLightModel(int pname, IntBuffer params) {
/* 2011 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2012 */     long function_pointer = caps.glLightModeliv;
/* 2013 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2014 */     BufferChecks.checkBuffer(params, 4);
/* 2015 */     nglLightModeliv(pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglLightModeliv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glLightf(int light, int pname, float param) {
/* 2020 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2021 */     long function_pointer = caps.glLightf;
/* 2022 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2023 */     nglLightf(light, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglLightf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glLighti(int light, int pname, int param) {
/* 2028 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2029 */     long function_pointer = caps.glLighti;
/* 2030 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2031 */     nglLighti(light, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglLighti(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glLight(int light, int pname, FloatBuffer params) {
/* 2036 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2037 */     long function_pointer = caps.glLightfv;
/* 2038 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2039 */     BufferChecks.checkBuffer(params, 4);
/* 2040 */     nglLightfv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglLightfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glLight(int light, int pname, IntBuffer params) {
/* 2045 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2046 */     long function_pointer = caps.glLightiv;
/* 2047 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2048 */     BufferChecks.checkBuffer(params, 4);
/* 2049 */     nglLightiv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglLightiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static boolean glIsTexture(int texture) {
/* 2054 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2055 */     long function_pointer = caps.glIsTexture;
/* 2056 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2057 */     boolean __result = nglIsTexture(texture, function_pointer);
/* 2058 */     return __result;
/*      */   }
/*      */   static native boolean nglIsTexture(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glMatrixMode(int mode) {
/* 2063 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2064 */     long function_pointer = caps.glMatrixMode;
/* 2065 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2066 */     nglMatrixMode(mode, function_pointer);
/*      */   }
/*      */   static native void nglMatrixMode(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glPolygonStipple(ByteBuffer mask) {
/* 2071 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2072 */     long function_pointer = caps.glPolygonStipple;
/* 2073 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2074 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2075 */     BufferChecks.checkBuffer(mask, 128);
/* 2076 */     nglPolygonStipple(MemoryUtil.getAddress(mask), function_pointer);
/*      */   }
/*      */   static native void nglPolygonStipple(long paramLong1, long paramLong2);
/*      */ 
/* 2080 */   public static void glPolygonStipple(long mask_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2081 */     long function_pointer = caps.glPolygonStipple;
/* 2082 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2083 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2084 */     nglPolygonStippleBO(mask_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglPolygonStippleBO(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glPolygonOffset(float factor, float units) {
/* 2089 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2090 */     long function_pointer = caps.glPolygonOffset;
/* 2091 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2092 */     nglPolygonOffset(factor, units, function_pointer);
/*      */   }
/*      */   static native void nglPolygonOffset(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glPolygonMode(int face, int mode) {
/* 2097 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2098 */     long function_pointer = caps.glPolygonMode;
/* 2099 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2100 */     nglPolygonMode(face, mode, function_pointer);
/*      */   }
/*      */   static native void nglPolygonMode(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glPointSize(float size) {
/* 2105 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2106 */     long function_pointer = caps.glPointSize;
/* 2107 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2108 */     nglPointSize(size, function_pointer);
/*      */   }
/*      */   static native void nglPointSize(float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glPixelZoom(float xfactor, float yfactor) {
/* 2113 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2114 */     long function_pointer = caps.glPixelZoom;
/* 2115 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2116 */     nglPixelZoom(xfactor, yfactor, function_pointer);
/*      */   }
/*      */   static native void nglPixelZoom(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glPixelTransferf(int pname, float param) {
/* 2121 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2122 */     long function_pointer = caps.glPixelTransferf;
/* 2123 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2124 */     nglPixelTransferf(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglPixelTransferf(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glPixelTransferi(int pname, int param) {
/* 2129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2130 */     long function_pointer = caps.glPixelTransferi;
/* 2131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2132 */     nglPixelTransferi(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglPixelTransferi(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glPixelStoref(int pname, float param) {
/* 2137 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2138 */     long function_pointer = caps.glPixelStoref;
/* 2139 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2140 */     nglPixelStoref(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglPixelStoref(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glPixelStorei(int pname, int param) {
/* 2145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2146 */     long function_pointer = caps.glPixelStorei;
/* 2147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2148 */     nglPixelStorei(pname, param, function_pointer);
/*      */   }
/*      */   static native void nglPixelStorei(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glPixelMap(int map, FloatBuffer values) {
/* 2153 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2154 */     long function_pointer = caps.glPixelMapfv;
/* 2155 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2156 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2157 */     BufferChecks.checkDirect(values);
/* 2158 */     nglPixelMapfv(map, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglPixelMapfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/* 2162 */   public static void glPixelMapfv(int map, int values_mapsize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2163 */     long function_pointer = caps.glPixelMapfv;
/* 2164 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2165 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2166 */     nglPixelMapfvBO(map, values_mapsize, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglPixelMapfvBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glPixelMapu(int map, IntBuffer values) {
/* 2171 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2172 */     long function_pointer = caps.glPixelMapuiv;
/* 2173 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2174 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2175 */     BufferChecks.checkDirect(values);
/* 2176 */     nglPixelMapuiv(map, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglPixelMapuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/* 2180 */   public static void glPixelMapuiv(int map, int values_mapsize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2181 */     long function_pointer = caps.glPixelMapuiv;
/* 2182 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2183 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2184 */     nglPixelMapuivBO(map, values_mapsize, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglPixelMapuivBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glPixelMapu(int map, ShortBuffer values) {
/* 2189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2190 */     long function_pointer = caps.glPixelMapusv;
/* 2191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2192 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2193 */     BufferChecks.checkDirect(values);
/* 2194 */     nglPixelMapusv(map, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglPixelMapusv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/* 2198 */   public static void glPixelMapusv(int map, int values_mapsize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2199 */     long function_pointer = caps.glPixelMapusv;
/* 2200 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2201 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2202 */     nglPixelMapusvBO(map, values_mapsize, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglPixelMapusvBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glPassThrough(float token) {
/* 2207 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2208 */     long function_pointer = caps.glPassThrough;
/* 2209 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2210 */     nglPassThrough(token, function_pointer);
/*      */   }
/*      */   static native void nglPassThrough(float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) {
/* 2215 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2216 */     long function_pointer = caps.glOrtho;
/* 2217 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2218 */     nglOrtho(left, right, bottom, top, zNear, zFar, function_pointer);
/*      */   }
/*      */   static native void nglOrtho(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/*      */ 
/*      */   public static void glNormalPointer(int stride, ByteBuffer pointer) {
/* 2223 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2224 */     long function_pointer = caps.glNormalPointer;
/* 2225 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2226 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2227 */     BufferChecks.checkDirect(pointer);
/* 2228 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2229 */     nglNormalPointer(5120, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glNormalPointer(int stride, DoubleBuffer pointer) {
/* 2232 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2233 */     long function_pointer = caps.glNormalPointer;
/* 2234 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2235 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2236 */     BufferChecks.checkDirect(pointer);
/* 2237 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2238 */     nglNormalPointer(5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glNormalPointer(int stride, FloatBuffer pointer) {
/* 2241 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2242 */     long function_pointer = caps.glNormalPointer;
/* 2243 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2244 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2245 */     BufferChecks.checkDirect(pointer);
/* 2246 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2247 */     nglNormalPointer(5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glNormalPointer(int stride, IntBuffer pointer) {
/* 2250 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2251 */     long function_pointer = caps.glNormalPointer;
/* 2252 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2253 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2254 */     BufferChecks.checkDirect(pointer);
/* 2255 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2256 */     nglNormalPointer(5124, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglNormalPointer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/* 2260 */   public static void glNormalPointer(int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2261 */     long function_pointer = caps.glNormalPointer;
/* 2262 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2263 */     GLChecks.ensureArrayVBOenabled(caps);
/* 2264 */     nglNormalPointerBO(type, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglNormalPointerBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNormalPointer(int type, int stride, ByteBuffer pointer)
/*      */   {
/* 2270 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2271 */     long function_pointer = caps.glNormalPointer;
/* 2272 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2273 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2274 */     BufferChecks.checkDirect(pointer);
/* 2275 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2276 */     nglNormalPointer(type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glNormal3b(byte nx, byte ny, byte nz) {
/* 2280 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2281 */     long function_pointer = caps.glNormal3b;
/* 2282 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2283 */     nglNormal3b(nx, ny, nz, function_pointer);
/*      */   }
/*      */   static native void nglNormal3b(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*      */ 
/*      */   public static void glNormal3f(float nx, float ny, float nz) {
/* 2288 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2289 */     long function_pointer = caps.glNormal3f;
/* 2290 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2291 */     nglNormal3f(nx, ny, nz, function_pointer);
/*      */   }
/*      */   static native void nglNormal3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glNormal3d(double nx, double ny, double nz) {
/* 2296 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2297 */     long function_pointer = caps.glNormal3d;
/* 2298 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2299 */     nglNormal3d(nx, ny, nz, function_pointer);
/*      */   }
/*      */   static native void nglNormal3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glNormal3i(int nx, int ny, int nz) {
/* 2304 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2305 */     long function_pointer = caps.glNormal3i;
/* 2306 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2307 */     nglNormal3i(nx, ny, nz, function_pointer);
/*      */   }
/*      */   static native void nglNormal3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glNewList(int list, int mode) {
/* 2312 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2313 */     long function_pointer = caps.glNewList;
/* 2314 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2315 */     nglNewList(list, mode, function_pointer);
/*      */   }
/*      */   static native void nglNewList(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glEndList() {
/* 2320 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2321 */     long function_pointer = caps.glEndList;
/* 2322 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2323 */     nglEndList(function_pointer);
/*      */   }
/*      */   static native void nglEndList(long paramLong);
/*      */ 
/*      */   public static void glMultMatrix(FloatBuffer m) {
/* 2328 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2329 */     long function_pointer = caps.glMultMatrixf;
/* 2330 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2331 */     BufferChecks.checkBuffer(m, 16);
/* 2332 */     nglMultMatrixf(MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMultMatrixf(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultMatrix(DoubleBuffer m) {
/* 2337 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2338 */     long function_pointer = caps.glMultMatrixd;
/* 2339 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2340 */     BufferChecks.checkBuffer(m, 16);
/* 2341 */     nglMultMatrixd(MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMultMatrixd(long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glShadeModel(int mode) {
/* 2346 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2347 */     long function_pointer = caps.glShadeModel;
/* 2348 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2349 */     nglShadeModel(mode, function_pointer);
/*      */   }
/*      */   static native void nglShadeModel(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glSelectBuffer(IntBuffer buffer) {
/* 2354 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2355 */     long function_pointer = caps.glSelectBuffer;
/* 2356 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2357 */     BufferChecks.checkDirect(buffer);
/* 2358 */     nglSelectBuffer(buffer.remaining(), MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   static native void nglSelectBuffer(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glScissor(int x, int y, int width, int height) {
/* 2363 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2364 */     long function_pointer = caps.glScissor;
/* 2365 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2366 */     nglScissor(x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglScissor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glScalef(float x, float y, float z) {
/* 2371 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2372 */     long function_pointer = caps.glScalef;
/* 2373 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2374 */     nglScalef(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglScalef(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glScaled(double x, double y, double z) {
/* 2379 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2380 */     long function_pointer = caps.glScaled;
/* 2381 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2382 */     nglScaled(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglScaled(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glRotatef(float angle, float x, float y, float z) {
/* 2387 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2388 */     long function_pointer = caps.glRotatef;
/* 2389 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2390 */     nglRotatef(angle, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglRotatef(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glRotated(double angle, double x, double y, double z) {
/* 2395 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2396 */     long function_pointer = caps.glRotated;
/* 2397 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2398 */     nglRotated(angle, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglRotated(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static int glRenderMode(int mode) {
/* 2403 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2404 */     long function_pointer = caps.glRenderMode;
/* 2405 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2406 */     int __result = nglRenderMode(mode, function_pointer);
/* 2407 */     return __result;
/*      */   }
/*      */   static native int nglRenderMode(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glRectf(float x1, float y1, float x2, float y2) {
/* 2412 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2413 */     long function_pointer = caps.glRectf;
/* 2414 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2415 */     nglRectf(x1, y1, x2, y2, function_pointer);
/*      */   }
/*      */   static native void nglRectf(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glRectd(double x1, double y1, double x2, double y2) {
/* 2420 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2421 */     long function_pointer = caps.glRectd;
/* 2422 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2423 */     nglRectd(x1, y1, x2, y2, function_pointer);
/*      */   }
/*      */   static native void nglRectd(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glRecti(int x1, int y1, int x2, int y2) {
/* 2428 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2429 */     long function_pointer = caps.glRecti;
/* 2430 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2431 */     nglRecti(x1, y1, x2, y2, function_pointer);
/*      */   }
/*      */   static native void nglRecti(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
/* 2436 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2437 */     long function_pointer = caps.glReadPixels;
/* 2438 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2439 */     GLChecks.ensurePackPBOdisabled(caps);
/* 2440 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2441 */     nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glReadPixels(int x, int y, int width, int height, int format, int type, DoubleBuffer pixels) {
/* 2444 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2445 */     long function_pointer = caps.glReadPixels;
/* 2446 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2447 */     GLChecks.ensurePackPBOdisabled(caps);
/* 2448 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2449 */     nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glReadPixels(int x, int y, int width, int height, int format, int type, FloatBuffer pixels) {
/* 2452 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2453 */     long function_pointer = caps.glReadPixels;
/* 2454 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2455 */     GLChecks.ensurePackPBOdisabled(caps);
/* 2456 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2457 */     nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
/* 2460 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2461 */     long function_pointer = caps.glReadPixels;
/* 2462 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2463 */     GLChecks.ensurePackPBOdisabled(caps);
/* 2464 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2465 */     nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
/* 2468 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2469 */     long function_pointer = caps.glReadPixels;
/* 2470 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2471 */     GLChecks.ensurePackPBOdisabled(caps);
/* 2472 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2473 */     nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglReadPixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/* 2477 */   public static void glReadPixels(int x, int y, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2478 */     long function_pointer = caps.glReadPixels;
/* 2479 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2480 */     GLChecks.ensurePackPBOenabled(caps);
/* 2481 */     nglReadPixelsBO(x, y, width, height, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglReadPixelsBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glReadBuffer(int mode) {
/* 2486 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2487 */     long function_pointer = caps.glReadBuffer;
/* 2488 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2489 */     nglReadBuffer(mode, function_pointer);
/*      */   }
/*      */   static native void nglReadBuffer(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glRasterPos2f(float x, float y) {
/* 2494 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2495 */     long function_pointer = caps.glRasterPos2f;
/* 2496 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2497 */     nglRasterPos2f(x, y, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos2f(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glRasterPos2d(double x, double y) {
/* 2502 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2503 */     long function_pointer = caps.glRasterPos2d;
/* 2504 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2505 */     nglRasterPos2d(x, y, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos2d(double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glRasterPos2i(int x, int y) {
/* 2510 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2511 */     long function_pointer = caps.glRasterPos2i;
/* 2512 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2513 */     nglRasterPos2i(x, y, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos2i(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glRasterPos3f(float x, float y, float z) {
/* 2518 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2519 */     long function_pointer = caps.glRasterPos3f;
/* 2520 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2521 */     nglRasterPos3f(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glRasterPos3d(double x, double y, double z) {
/* 2526 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2527 */     long function_pointer = caps.glRasterPos3d;
/* 2528 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2529 */     nglRasterPos3d(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glRasterPos3i(int x, int y, int z) {
/* 2534 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2535 */     long function_pointer = caps.glRasterPos3i;
/* 2536 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2537 */     nglRasterPos3i(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glRasterPos4f(float x, float y, float z, float w) {
/* 2542 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2543 */     long function_pointer = caps.glRasterPos4f;
/* 2544 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2545 */     nglRasterPos4f(x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glRasterPos4d(double x, double y, double z, double w) {
/* 2550 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2551 */     long function_pointer = caps.glRasterPos4d;
/* 2552 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2553 */     nglRasterPos4d(x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glRasterPos4i(int x, int y, int z, int w) {
/* 2558 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2559 */     long function_pointer = caps.glRasterPos4i;
/* 2560 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2561 */     nglRasterPos4i(x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglRasterPos4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glPushName(int name) {
/* 2566 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2567 */     long function_pointer = caps.glPushName;
/* 2568 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2569 */     nglPushName(name, function_pointer);
/*      */   }
/*      */   static native void nglPushName(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glPopName() {
/* 2574 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2575 */     long function_pointer = caps.glPopName;
/* 2576 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2577 */     nglPopName(function_pointer);
/*      */   }
/*      */   static native void nglPopName(long paramLong);
/*      */ 
/*      */   public static void glPushMatrix() {
/* 2582 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2583 */     long function_pointer = caps.glPushMatrix;
/* 2584 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2585 */     nglPushMatrix(function_pointer);
/*      */   }
/*      */   static native void nglPushMatrix(long paramLong);
/*      */ 
/*      */   public static void glPopMatrix() {
/* 2590 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2591 */     long function_pointer = caps.glPopMatrix;
/* 2592 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2593 */     nglPopMatrix(function_pointer);
/*      */   }
/*      */   static native void nglPopMatrix(long paramLong);
/*      */ 
/*      */   public static void glPushClientAttrib(int mask) {
/* 2598 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2599 */     long function_pointer = caps.glPushClientAttrib;
/* 2600 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2601 */     StateTracker.pushAttrib(caps, mask);
/* 2602 */     nglPushClientAttrib(mask, function_pointer);
/*      */   }
/*      */   static native void nglPushClientAttrib(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glPopClientAttrib() {
/* 2607 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2608 */     long function_pointer = caps.glPopClientAttrib;
/* 2609 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2610 */     StateTracker.popAttrib(caps);
/* 2611 */     nglPopClientAttrib(function_pointer);
/*      */   }
/*      */   static native void nglPopClientAttrib(long paramLong);
/*      */ 
/*      */   public static void glPushAttrib(int mask) {
/* 2616 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2617 */     long function_pointer = caps.glPushAttrib;
/* 2618 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2619 */     nglPushAttrib(mask, function_pointer);
/*      */   }
/*      */   static native void nglPushAttrib(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glPopAttrib() {
/* 2624 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2625 */     long function_pointer = caps.glPopAttrib;
/* 2626 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2627 */     nglPopAttrib(function_pointer);
/*      */   }
/*      */   static native void nglPopAttrib(long paramLong);
/*      */ 
/*      */   public static void glStencilFunc(int func, int ref, int mask) {
/* 2632 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2633 */     long function_pointer = caps.glStencilFunc;
/* 2634 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2635 */     nglStencilFunc(func, ref, mask, function_pointer);
/*      */   }
/*      */   static native void nglStencilFunc(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glVertexPointer(int size, int stride, DoubleBuffer pointer) {
/* 2640 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2641 */     long function_pointer = caps.glVertexPointer;
/* 2642 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2643 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2644 */     BufferChecks.checkDirect(pointer);
/* 2645 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2646 */     nglVertexPointer(size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glVertexPointer(int size, int stride, FloatBuffer pointer) {
/* 2649 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2650 */     long function_pointer = caps.glVertexPointer;
/* 2651 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2652 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2653 */     BufferChecks.checkDirect(pointer);
/* 2654 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2655 */     nglVertexPointer(size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glVertexPointer(int size, int stride, IntBuffer pointer) {
/* 2658 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2659 */     long function_pointer = caps.glVertexPointer;
/* 2660 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2661 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2662 */     BufferChecks.checkDirect(pointer);
/* 2663 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2664 */     nglVertexPointer(size, 5124, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glVertexPointer(int size, int stride, ShortBuffer pointer) {
/* 2667 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2668 */     long function_pointer = caps.glVertexPointer;
/* 2669 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2670 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2671 */     BufferChecks.checkDirect(pointer);
/* 2672 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2673 */     nglVertexPointer(size, 5122, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglVertexPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/* 2677 */   public static void glVertexPointer(int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2678 */     long function_pointer = caps.glVertexPointer;
/* 2679 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2680 */     GLChecks.ensureArrayVBOenabled(caps);
/* 2681 */     nglVertexPointerBO(size, type, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglVertexPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexPointer(int size, int type, int stride, ByteBuffer pointer)
/*      */   {
/* 2687 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2688 */     long function_pointer = caps.glVertexPointer;
/* 2689 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2690 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 2691 */     BufferChecks.checkDirect(pointer);
/* 2692 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2693 */     nglVertexPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glVertex2f(float x, float y) {
/* 2697 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2698 */     long function_pointer = caps.glVertex2f;
/* 2699 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2700 */     nglVertex2f(x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertex2f(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glVertex2d(double x, double y) {
/* 2705 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2706 */     long function_pointer = caps.glVertex2d;
/* 2707 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2708 */     nglVertex2d(x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertex2d(double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glVertex2i(int x, int y) {
/* 2713 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2714 */     long function_pointer = caps.glVertex2i;
/* 2715 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2716 */     nglVertex2i(x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertex2i(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glVertex3f(float x, float y, float z) {
/* 2721 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2722 */     long function_pointer = caps.glVertex3f;
/* 2723 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2724 */     nglVertex3f(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertex3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glVertex3d(double x, double y, double z) {
/* 2729 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2730 */     long function_pointer = caps.glVertex3d;
/* 2731 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2732 */     nglVertex3d(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertex3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glVertex3i(int x, int y, int z) {
/* 2737 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2738 */     long function_pointer = caps.glVertex3i;
/* 2739 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2740 */     nglVertex3i(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertex3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glVertex4f(float x, float y, float z, float w) {
/* 2745 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2746 */     long function_pointer = caps.glVertex4f;
/* 2747 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2748 */     nglVertex4f(x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertex4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glVertex4d(double x, double y, double z, double w) {
/* 2753 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2754 */     long function_pointer = caps.glVertex4d;
/* 2755 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2756 */     nglVertex4d(x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertex4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glVertex4i(int x, int y, int z, int w) {
/* 2761 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2762 */     long function_pointer = caps.glVertex4i;
/* 2763 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2764 */     nglVertex4i(x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertex4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glTranslatef(float x, float y, float z) {
/* 2769 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2770 */     long function_pointer = caps.glTranslatef;
/* 2771 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2772 */     nglTranslatef(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglTranslatef(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glTranslated(double x, double y, double z) {
/* 2777 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2778 */     long function_pointer = caps.glTranslated;
/* 2779 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2780 */     nglTranslated(x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglTranslated(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
/* 2785 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2786 */     long function_pointer = caps.glTexImage1D;
/* 2787 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2788 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2789 */     if (pixels != null)
/* 2790 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2791 */     nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) {
/* 2794 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2795 */     long function_pointer = caps.glTexImage1D;
/* 2796 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2797 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2798 */     if (pixels != null)
/* 2799 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2800 */     nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
/* 2803 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2804 */     long function_pointer = caps.glTexImage1D;
/* 2805 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2806 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2807 */     if (pixels != null)
/* 2808 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2809 */     nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
/* 2812 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2813 */     long function_pointer = caps.glTexImage1D;
/* 2814 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2815 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2816 */     if (pixels != null)
/* 2817 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2818 */     nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
/* 2821 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2822 */     long function_pointer = caps.glTexImage1D;
/* 2823 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2824 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2825 */     if (pixels != null)
/* 2826 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2827 */     nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTexImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/* 2831 */   public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2832 */     long function_pointer = caps.glTexImage1D;
/* 2833 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2834 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2835 */     nglTexImage1DBO(target, level, internalformat, width, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTexImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
/* 2840 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2841 */     long function_pointer = caps.glTexImage2D;
/* 2842 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2843 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2844 */     if (pixels != null)
/* 2845 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2846 */     nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) {
/* 2849 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2850 */     long function_pointer = caps.glTexImage2D;
/* 2851 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2852 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2853 */     if (pixels != null)
/* 2854 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2855 */     nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
/* 2858 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2859 */     long function_pointer = caps.glTexImage2D;
/* 2860 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2861 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2862 */     if (pixels != null)
/* 2863 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2864 */     nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
/* 2867 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2868 */     long function_pointer = caps.glTexImage2D;
/* 2869 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2870 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2871 */     if (pixels != null)
/* 2872 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2873 */     nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
/* 2876 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2877 */     long function_pointer = caps.glTexImage2D;
/* 2878 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2879 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2880 */     if (pixels != null)
/* 2881 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2882 */     nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTexImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/* 2886 */   public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2887 */     long function_pointer = caps.glTexImage2D;
/* 2888 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2889 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2890 */     nglTexImage2DBO(target, level, internalformat, width, height, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTexImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
/* 2895 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2896 */     long function_pointer = caps.glTexSubImage1D;
/* 2897 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2898 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2899 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2900 */     nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
/* 2903 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2904 */     long function_pointer = caps.glTexSubImage1D;
/* 2905 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2906 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2907 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2908 */     nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
/* 2911 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2912 */     long function_pointer = caps.glTexSubImage1D;
/* 2913 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2914 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2915 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2916 */     nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
/* 2919 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2920 */     long function_pointer = caps.glTexSubImage1D;
/* 2921 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2922 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2923 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2924 */     nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
/* 2927 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2928 */     long function_pointer = caps.glTexSubImage1D;
/* 2929 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2930 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2931 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2932 */     nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTexSubImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/* 2936 */   public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2937 */     long function_pointer = caps.glTexSubImage1D;
/* 2938 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2939 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2940 */     nglTexSubImage1DBO(target, level, xoffset, width, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTexSubImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
/* 2945 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2946 */     long function_pointer = caps.glTexSubImage2D;
/* 2947 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2948 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2949 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2950 */     nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) {
/* 2953 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2954 */     long function_pointer = caps.glTexSubImage2D;
/* 2955 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2956 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2957 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2958 */     nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
/* 2961 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2962 */     long function_pointer = caps.glTexSubImage2D;
/* 2963 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2964 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2965 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2966 */     nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
/* 2969 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2970 */     long function_pointer = caps.glTexSubImage2D;
/* 2971 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2972 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2973 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2974 */     nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
/* 2977 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2978 */     long function_pointer = caps.glTexSubImage2D;
/* 2979 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2980 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 2981 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2982 */     nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTexSubImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/* 2986 */   public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2987 */     long function_pointer = caps.glTexSubImage2D;
/* 2988 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2989 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 2990 */     nglTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTexSubImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexParameterf(int target, int pname, float param) {
/* 2995 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2996 */     long function_pointer = caps.glTexParameterf;
/* 2997 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2998 */     nglTexParameterf(target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexParameterf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glTexParameteri(int target, int pname, int param) {
/* 3003 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3004 */     long function_pointer = caps.glTexParameteri;
/* 3005 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3006 */     nglTexParameteri(target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glTexParameter(int target, int pname, FloatBuffer param) {
/* 3011 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3012 */     long function_pointer = caps.glTexParameterfv;
/* 3013 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3014 */     BufferChecks.checkBuffer(param, 4);
/* 3015 */     nglTexParameterfv(target, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */   static native void nglTexParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexParameter(int target, int pname, IntBuffer param) {
/* 3020 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3021 */     long function_pointer = caps.glTexParameteriv;
/* 3022 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3023 */     BufferChecks.checkBuffer(param, 4);
/* 3024 */     nglTexParameteriv(target, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */   static native void nglTexParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexGenf(int coord, int pname, float param) {
/* 3029 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3030 */     long function_pointer = caps.glTexGenf;
/* 3031 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3032 */     nglTexGenf(coord, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexGenf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glTexGend(int coord, int pname, double param) {
/* 3037 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3038 */     long function_pointer = caps.glTexGend;
/* 3039 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3040 */     nglTexGend(coord, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexGend(int paramInt1, int paramInt2, double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glTexGen(int coord, int pname, FloatBuffer params) {
/* 3045 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3046 */     long function_pointer = caps.glTexGenfv;
/* 3047 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3048 */     BufferChecks.checkBuffer(params, 4);
/* 3049 */     nglTexGenfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglTexGenfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexGen(int coord, int pname, DoubleBuffer params) {
/* 3054 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3055 */     long function_pointer = caps.glTexGendv;
/* 3056 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3057 */     BufferChecks.checkBuffer(params, 4);
/* 3058 */     nglTexGendv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglTexGendv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexGeni(int coord, int pname, int param) {
/* 3063 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3064 */     long function_pointer = caps.glTexGeni;
/* 3065 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3066 */     nglTexGeni(coord, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexGeni(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glTexGen(int coord, int pname, IntBuffer params) {
/* 3071 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3072 */     long function_pointer = caps.glTexGeniv;
/* 3073 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3074 */     BufferChecks.checkBuffer(params, 4);
/* 3075 */     nglTexGeniv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglTexGeniv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexEnvf(int target, int pname, float param) {
/* 3080 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3081 */     long function_pointer = caps.glTexEnvf;
/* 3082 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3083 */     nglTexEnvf(target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexEnvf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glTexEnvi(int target, int pname, int param) {
/* 3088 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3089 */     long function_pointer = caps.glTexEnvi;
/* 3090 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3091 */     nglTexEnvi(target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTexEnvi(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glTexEnv(int target, int pname, FloatBuffer params) {
/* 3096 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3097 */     long function_pointer = caps.glTexEnvfv;
/* 3098 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3099 */     BufferChecks.checkBuffer(params, 4);
/* 3100 */     nglTexEnvfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglTexEnvfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexEnv(int target, int pname, IntBuffer params) {
/* 3105 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3106 */     long function_pointer = caps.glTexEnviv;
/* 3107 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3108 */     BufferChecks.checkBuffer(params, 4);
/* 3109 */     nglTexEnviv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglTexEnviv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexCoordPointer(int size, int stride, DoubleBuffer pointer) {
/* 3114 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3115 */     long function_pointer = caps.glTexCoordPointer;
/* 3116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3117 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 3118 */     BufferChecks.checkDirect(pointer);
/* 3119 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3120 */     nglTexCoordPointer(size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glTexCoordPointer(int size, int stride, FloatBuffer pointer) {
/* 3123 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3124 */     long function_pointer = caps.glTexCoordPointer;
/* 3125 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3126 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 3127 */     BufferChecks.checkDirect(pointer);
/* 3128 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3129 */     nglTexCoordPointer(size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glTexCoordPointer(int size, int stride, IntBuffer pointer) {
/* 3132 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3133 */     long function_pointer = caps.glTexCoordPointer;
/* 3134 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3135 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 3136 */     BufferChecks.checkDirect(pointer);
/* 3137 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3138 */     nglTexCoordPointer(size, 5124, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glTexCoordPointer(int size, int stride, ShortBuffer pointer) {
/* 3141 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3142 */     long function_pointer = caps.glTexCoordPointer;
/* 3143 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3144 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 3145 */     BufferChecks.checkDirect(pointer);
/* 3146 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3147 */     nglTexCoordPointer(size, 5122, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglTexCoordPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/* 3151 */   public static void glTexCoordPointer(int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3152 */     long function_pointer = caps.glTexCoordPointer;
/* 3153 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3154 */     GLChecks.ensureArrayVBOenabled(caps);
/* 3155 */     nglTexCoordPointerBO(size, type, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTexCoordPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTexCoordPointer(int size, int type, int stride, ByteBuffer pointer)
/*      */   {
/* 3161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3162 */     long function_pointer = caps.glTexCoordPointer;
/* 3163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3164 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 3165 */     BufferChecks.checkDirect(pointer);
/* 3166 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3167 */     nglTexCoordPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glTexCoord1f(float s) {
/* 3171 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3172 */     long function_pointer = caps.glTexCoord1f;
/* 3173 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3174 */     nglTexCoord1f(s, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord1f(float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glTexCoord1d(double s) {
/* 3179 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3180 */     long function_pointer = caps.glTexCoord1d;
/* 3181 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3182 */     nglTexCoord1d(s, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord1d(double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glTexCoord2f(float s, float t) {
/* 3187 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3188 */     long function_pointer = caps.glTexCoord2f;
/* 3189 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3190 */     nglTexCoord2f(s, t, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord2f(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glTexCoord2d(double s, double t) {
/* 3195 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3196 */     long function_pointer = caps.glTexCoord2d;
/* 3197 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3198 */     nglTexCoord2d(s, t, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord2d(double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glTexCoord3f(float s, float t, float r) {
/* 3203 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3204 */     long function_pointer = caps.glTexCoord3f;
/* 3205 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3206 */     nglTexCoord3f(s, t, r, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glTexCoord3d(double s, double t, double r) {
/* 3211 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3212 */     long function_pointer = caps.glTexCoord3d;
/* 3213 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3214 */     nglTexCoord3d(s, t, r, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glTexCoord4f(float s, float t, float r, float q) {
/* 3219 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3220 */     long function_pointer = caps.glTexCoord4f;
/* 3221 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3222 */     nglTexCoord4f(s, t, r, q, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glTexCoord4d(double s, double t, double r, double q) {
/* 3227 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3228 */     long function_pointer = caps.glTexCoord4d;
/* 3229 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3230 */     nglTexCoord4d(s, t, r, q, function_pointer);
/*      */   }
/*      */   static native void nglTexCoord4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glStencilOp(int fail, int zfail, int zpass) {
/* 3235 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3236 */     long function_pointer = caps.glStencilOp;
/* 3237 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3238 */     nglStencilOp(fail, zfail, zpass, function_pointer);
/*      */   }
/*      */   static native void nglStencilOp(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glStencilMask(int mask) {
/* 3243 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3244 */     long function_pointer = caps.glStencilMask;
/* 3245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3246 */     nglStencilMask(mask, function_pointer);
/*      */   }
/*      */   static native void nglStencilMask(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glViewport(int x, int y, int width, int height) {
/* 3251 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3252 */     long function_pointer = caps.glViewport;
/* 3253 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3254 */     nglViewport(x, y, width, height, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglViewport(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL11
 * JD-Core Version:    0.6.2
 */