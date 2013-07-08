/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.ByteOrder;
/*    5:     */import java.nio.DoubleBuffer;
/*    6:     */import java.nio.FloatBuffer;
/*    7:     */import java.nio.IntBuffer;
/*    8:     */import java.nio.ShortBuffer;
/*    9:     */import org.lwjgl.BufferChecks;
/*   10:     */import org.lwjgl.LWJGLUtil;
/*   11:     */import org.lwjgl.MemoryUtil;
/*   12:     */
/*   16:     */public final class GL11
/*   17:     */{
/*   18:     */  public static final int GL_ACCUM = 256;
/*   19:     */  public static final int GL_LOAD = 257;
/*   20:     */  public static final int GL_RETURN = 258;
/*   21:     */  public static final int GL_MULT = 259;
/*   22:     */  public static final int GL_ADD = 260;
/*   23:     */  public static final int GL_NEVER = 512;
/*   24:     */  public static final int GL_LESS = 513;
/*   25:     */  public static final int GL_EQUAL = 514;
/*   26:     */  public static final int GL_LEQUAL = 515;
/*   27:     */  public static final int GL_GREATER = 516;
/*   28:     */  public static final int GL_NOTEQUAL = 517;
/*   29:     */  public static final int GL_GEQUAL = 518;
/*   30:     */  public static final int GL_ALWAYS = 519;
/*   31:     */  public static final int GL_CURRENT_BIT = 1;
/*   32:     */  public static final int GL_POINT_BIT = 2;
/*   33:     */  public static final int GL_LINE_BIT = 4;
/*   34:     */  public static final int GL_POLYGON_BIT = 8;
/*   35:     */  public static final int GL_POLYGON_STIPPLE_BIT = 16;
/*   36:     */  public static final int GL_PIXEL_MODE_BIT = 32;
/*   37:     */  public static final int GL_LIGHTING_BIT = 64;
/*   38:     */  public static final int GL_FOG_BIT = 128;
/*   39:     */  public static final int GL_DEPTH_BUFFER_BIT = 256;
/*   40:     */  public static final int GL_ACCUM_BUFFER_BIT = 512;
/*   41:     */  public static final int GL_STENCIL_BUFFER_BIT = 1024;
/*   42:     */  public static final int GL_VIEWPORT_BIT = 2048;
/*   43:     */  public static final int GL_TRANSFORM_BIT = 4096;
/*   44:     */  public static final int GL_ENABLE_BIT = 8192;
/*   45:     */  public static final int GL_COLOR_BUFFER_BIT = 16384;
/*   46:     */  public static final int GL_HINT_BIT = 32768;
/*   47:     */  public static final int GL_EVAL_BIT = 65536;
/*   48:     */  public static final int GL_LIST_BIT = 131072;
/*   49:     */  public static final int GL_TEXTURE_BIT = 262144;
/*   50:     */  public static final int GL_SCISSOR_BIT = 524288;
/*   51:     */  public static final int GL_ALL_ATTRIB_BITS = 1048575;
/*   52:     */  public static final int GL_POINTS = 0;
/*   53:     */  public static final int GL_LINES = 1;
/*   54:     */  public static final int GL_LINE_LOOP = 2;
/*   55:     */  public static final int GL_LINE_STRIP = 3;
/*   56:     */  public static final int GL_TRIANGLES = 4;
/*   57:     */  public static final int GL_TRIANGLE_STRIP = 5;
/*   58:     */  public static final int GL_TRIANGLE_FAN = 6;
/*   59:     */  public static final int GL_QUADS = 7;
/*   60:     */  public static final int GL_QUAD_STRIP = 8;
/*   61:     */  public static final int GL_POLYGON = 9;
/*   62:     */  public static final int GL_ZERO = 0;
/*   63:     */  public static final int GL_ONE = 1;
/*   64:     */  public static final int GL_SRC_COLOR = 768;
/*   65:     */  public static final int GL_ONE_MINUS_SRC_COLOR = 769;
/*   66:     */  public static final int GL_SRC_ALPHA = 770;
/*   67:     */  public static final int GL_ONE_MINUS_SRC_ALPHA = 771;
/*   68:     */  public static final int GL_DST_ALPHA = 772;
/*   69:     */  public static final int GL_ONE_MINUS_DST_ALPHA = 773;
/*   70:     */  public static final int GL_DST_COLOR = 774;
/*   71:     */  public static final int GL_ONE_MINUS_DST_COLOR = 775;
/*   72:     */  public static final int GL_SRC_ALPHA_SATURATE = 776;
/*   73:     */  public static final int GL_CONSTANT_COLOR = 32769;
/*   74:     */  public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
/*   75:     */  public static final int GL_CONSTANT_ALPHA = 32771;
/*   76:     */  public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
/*   77:     */  public static final int GL_TRUE = 1;
/*   78:     */  public static final int GL_FALSE = 0;
/*   79:     */  public static final int GL_CLIP_PLANE0 = 12288;
/*   80:     */  public static final int GL_CLIP_PLANE1 = 12289;
/*   81:     */  public static final int GL_CLIP_PLANE2 = 12290;
/*   82:     */  public static final int GL_CLIP_PLANE3 = 12291;
/*   83:     */  public static final int GL_CLIP_PLANE4 = 12292;
/*   84:     */  public static final int GL_CLIP_PLANE5 = 12293;
/*   85:     */  public static final int GL_BYTE = 5120;
/*   86:     */  public static final int GL_UNSIGNED_BYTE = 5121;
/*   87:     */  public static final int GL_SHORT = 5122;
/*   88:     */  public static final int GL_UNSIGNED_SHORT = 5123;
/*   89:     */  public static final int GL_INT = 5124;
/*   90:     */  public static final int GL_UNSIGNED_INT = 5125;
/*   91:     */  public static final int GL_FLOAT = 5126;
/*   92:     */  public static final int GL_2_BYTES = 5127;
/*   93:     */  public static final int GL_3_BYTES = 5128;
/*   94:     */  public static final int GL_4_BYTES = 5129;
/*   95:     */  public static final int GL_DOUBLE = 5130;
/*   96:     */  public static final int GL_NONE = 0;
/*   97:     */  public static final int GL_FRONT_LEFT = 1024;
/*   98:     */  public static final int GL_FRONT_RIGHT = 1025;
/*   99:     */  public static final int GL_BACK_LEFT = 1026;
/*  100:     */  public static final int GL_BACK_RIGHT = 1027;
/*  101:     */  public static final int GL_FRONT = 1028;
/*  102:     */  public static final int GL_BACK = 1029;
/*  103:     */  public static final int GL_LEFT = 1030;
/*  104:     */  public static final int GL_RIGHT = 1031;
/*  105:     */  public static final int GL_FRONT_AND_BACK = 1032;
/*  106:     */  public static final int GL_AUX0 = 1033;
/*  107:     */  public static final int GL_AUX1 = 1034;
/*  108:     */  public static final int GL_AUX2 = 1035;
/*  109:     */  public static final int GL_AUX3 = 1036;
/*  110:     */  public static final int GL_NO_ERROR = 0;
/*  111:     */  public static final int GL_INVALID_ENUM = 1280;
/*  112:     */  public static final int GL_INVALID_VALUE = 1281;
/*  113:     */  public static final int GL_INVALID_OPERATION = 1282;
/*  114:     */  public static final int GL_STACK_OVERFLOW = 1283;
/*  115:     */  public static final int GL_STACK_UNDERFLOW = 1284;
/*  116:     */  public static final int GL_OUT_OF_MEMORY = 1285;
/*  117:     */  public static final int GL_2D = 1536;
/*  118:     */  public static final int GL_3D = 1537;
/*  119:     */  public static final int GL_3D_COLOR = 1538;
/*  120:     */  public static final int GL_3D_COLOR_TEXTURE = 1539;
/*  121:     */  public static final int GL_4D_COLOR_TEXTURE = 1540;
/*  122:     */  public static final int GL_PASS_THROUGH_TOKEN = 1792;
/*  123:     */  public static final int GL_POINT_TOKEN = 1793;
/*  124:     */  public static final int GL_LINE_TOKEN = 1794;
/*  125:     */  public static final int GL_POLYGON_TOKEN = 1795;
/*  126:     */  public static final int GL_BITMAP_TOKEN = 1796;
/*  127:     */  public static final int GL_DRAW_PIXEL_TOKEN = 1797;
/*  128:     */  public static final int GL_COPY_PIXEL_TOKEN = 1798;
/*  129:     */  public static final int GL_LINE_RESET_TOKEN = 1799;
/*  130:     */  public static final int GL_EXP = 2048;
/*  131:     */  public static final int GL_EXP2 = 2049;
/*  132:     */  public static final int GL_CW = 2304;
/*  133:     */  public static final int GL_CCW = 2305;
/*  134:     */  public static final int GL_COEFF = 2560;
/*  135:     */  public static final int GL_ORDER = 2561;
/*  136:     */  public static final int GL_DOMAIN = 2562;
/*  137:     */  public static final int GL_CURRENT_COLOR = 2816;
/*  138:     */  public static final int GL_CURRENT_INDEX = 2817;
/*  139:     */  public static final int GL_CURRENT_NORMAL = 2818;
/*  140:     */  public static final int GL_CURRENT_TEXTURE_COORDS = 2819;
/*  141:     */  public static final int GL_CURRENT_RASTER_COLOR = 2820;
/*  142:     */  public static final int GL_CURRENT_RASTER_INDEX = 2821;
/*  143:     */  public static final int GL_CURRENT_RASTER_TEXTURE_COORDS = 2822;
/*  144:     */  public static final int GL_CURRENT_RASTER_POSITION = 2823;
/*  145:     */  public static final int GL_CURRENT_RASTER_POSITION_VALID = 2824;
/*  146:     */  public static final int GL_CURRENT_RASTER_DISTANCE = 2825;
/*  147:     */  public static final int GL_POINT_SMOOTH = 2832;
/*  148:     */  public static final int GL_POINT_SIZE = 2833;
/*  149:     */  public static final int GL_POINT_SIZE_RANGE = 2834;
/*  150:     */  public static final int GL_POINT_SIZE_GRANULARITY = 2835;
/*  151:     */  public static final int GL_LINE_SMOOTH = 2848;
/*  152:     */  public static final int GL_LINE_WIDTH = 2849;
/*  153:     */  public static final int GL_LINE_WIDTH_RANGE = 2850;
/*  154:     */  public static final int GL_LINE_WIDTH_GRANULARITY = 2851;
/*  155:     */  public static final int GL_LINE_STIPPLE = 2852;
/*  156:     */  public static final int GL_LINE_STIPPLE_PATTERN = 2853;
/*  157:     */  public static final int GL_LINE_STIPPLE_REPEAT = 2854;
/*  158:     */  public static final int GL_LIST_MODE = 2864;
/*  159:     */  public static final int GL_MAX_LIST_NESTING = 2865;
/*  160:     */  public static final int GL_LIST_BASE = 2866;
/*  161:     */  public static final int GL_LIST_INDEX = 2867;
/*  162:     */  public static final int GL_POLYGON_MODE = 2880;
/*  163:     */  public static final int GL_POLYGON_SMOOTH = 2881;
/*  164:     */  public static final int GL_POLYGON_STIPPLE = 2882;
/*  165:     */  public static final int GL_EDGE_FLAG = 2883;
/*  166:     */  public static final int GL_CULL_FACE = 2884;
/*  167:     */  public static final int GL_CULL_FACE_MODE = 2885;
/*  168:     */  public static final int GL_FRONT_FACE = 2886;
/*  169:     */  public static final int GL_LIGHTING = 2896;
/*  170:     */  public static final int GL_LIGHT_MODEL_LOCAL_VIEWER = 2897;
/*  171:     */  public static final int GL_LIGHT_MODEL_TWO_SIDE = 2898;
/*  172:     */  public static final int GL_LIGHT_MODEL_AMBIENT = 2899;
/*  173:     */  public static final int GL_SHADE_MODEL = 2900;
/*  174:     */  public static final int GL_COLOR_MATERIAL_FACE = 2901;
/*  175:     */  public static final int GL_COLOR_MATERIAL_PARAMETER = 2902;
/*  176:     */  public static final int GL_COLOR_MATERIAL = 2903;
/*  177:     */  public static final int GL_FOG = 2912;
/*  178:     */  public static final int GL_FOG_INDEX = 2913;
/*  179:     */  public static final int GL_FOG_DENSITY = 2914;
/*  180:     */  public static final int GL_FOG_START = 2915;
/*  181:     */  public static final int GL_FOG_END = 2916;
/*  182:     */  public static final int GL_FOG_MODE = 2917;
/*  183:     */  public static final int GL_FOG_COLOR = 2918;
/*  184:     */  public static final int GL_DEPTH_RANGE = 2928;
/*  185:     */  public static final int GL_DEPTH_TEST = 2929;
/*  186:     */  public static final int GL_DEPTH_WRITEMASK = 2930;
/*  187:     */  public static final int GL_DEPTH_CLEAR_VALUE = 2931;
/*  188:     */  public static final int GL_DEPTH_FUNC = 2932;
/*  189:     */  public static final int GL_ACCUM_CLEAR_VALUE = 2944;
/*  190:     */  public static final int GL_STENCIL_TEST = 2960;
/*  191:     */  public static final int GL_STENCIL_CLEAR_VALUE = 2961;
/*  192:     */  public static final int GL_STENCIL_FUNC = 2962;
/*  193:     */  public static final int GL_STENCIL_VALUE_MASK = 2963;
/*  194:     */  public static final int GL_STENCIL_FAIL = 2964;
/*  195:     */  public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
/*  196:     */  public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
/*  197:     */  public static final int GL_STENCIL_REF = 2967;
/*  198:     */  public static final int GL_STENCIL_WRITEMASK = 2968;
/*  199:     */  public static final int GL_MATRIX_MODE = 2976;
/*  200:     */  public static final int GL_NORMALIZE = 2977;
/*  201:     */  public static final int GL_VIEWPORT = 2978;
/*  202:     */  public static final int GL_MODELVIEW_STACK_DEPTH = 2979;
/*  203:     */  public static final int GL_PROJECTION_STACK_DEPTH = 2980;
/*  204:     */  public static final int GL_TEXTURE_STACK_DEPTH = 2981;
/*  205:     */  public static final int GL_MODELVIEW_MATRIX = 2982;
/*  206:     */  public static final int GL_PROJECTION_MATRIX = 2983;
/*  207:     */  public static final int GL_TEXTURE_MATRIX = 2984;
/*  208:     */  public static final int GL_ATTRIB_STACK_DEPTH = 2992;
/*  209:     */  public static final int GL_CLIENT_ATTRIB_STACK_DEPTH = 2993;
/*  210:     */  public static final int GL_ALPHA_TEST = 3008;
/*  211:     */  public static final int GL_ALPHA_TEST_FUNC = 3009;
/*  212:     */  public static final int GL_ALPHA_TEST_REF = 3010;
/*  213:     */  public static final int GL_DITHER = 3024;
/*  214:     */  public static final int GL_BLEND_DST = 3040;
/*  215:     */  public static final int GL_BLEND_SRC = 3041;
/*  216:     */  public static final int GL_BLEND = 3042;
/*  217:     */  public static final int GL_LOGIC_OP_MODE = 3056;
/*  218:     */  public static final int GL_INDEX_LOGIC_OP = 3057;
/*  219:     */  public static final int GL_COLOR_LOGIC_OP = 3058;
/*  220:     */  public static final int GL_AUX_BUFFERS = 3072;
/*  221:     */  public static final int GL_DRAW_BUFFER = 3073;
/*  222:     */  public static final int GL_READ_BUFFER = 3074;
/*  223:     */  public static final int GL_SCISSOR_BOX = 3088;
/*  224:     */  public static final int GL_SCISSOR_TEST = 3089;
/*  225:     */  public static final int GL_INDEX_CLEAR_VALUE = 3104;
/*  226:     */  public static final int GL_INDEX_WRITEMASK = 3105;
/*  227:     */  public static final int GL_COLOR_CLEAR_VALUE = 3106;
/*  228:     */  public static final int GL_COLOR_WRITEMASK = 3107;
/*  229:     */  public static final int GL_INDEX_MODE = 3120;
/*  230:     */  public static final int GL_RGBA_MODE = 3121;
/*  231:     */  public static final int GL_DOUBLEBUFFER = 3122;
/*  232:     */  public static final int GL_STEREO = 3123;
/*  233:     */  public static final int GL_RENDER_MODE = 3136;
/*  234:     */  public static final int GL_PERSPECTIVE_CORRECTION_HINT = 3152;
/*  235:     */  public static final int GL_POINT_SMOOTH_HINT = 3153;
/*  236:     */  public static final int GL_LINE_SMOOTH_HINT = 3154;
/*  237:     */  public static final int GL_POLYGON_SMOOTH_HINT = 3155;
/*  238:     */  public static final int GL_FOG_HINT = 3156;
/*  239:     */  public static final int GL_TEXTURE_GEN_S = 3168;
/*  240:     */  public static final int GL_TEXTURE_GEN_T = 3169;
/*  241:     */  public static final int GL_TEXTURE_GEN_R = 3170;
/*  242:     */  public static final int GL_TEXTURE_GEN_Q = 3171;
/*  243:     */  public static final int GL_PIXEL_MAP_I_TO_I = 3184;
/*  244:     */  public static final int GL_PIXEL_MAP_S_TO_S = 3185;
/*  245:     */  public static final int GL_PIXEL_MAP_I_TO_R = 3186;
/*  246:     */  public static final int GL_PIXEL_MAP_I_TO_G = 3187;
/*  247:     */  public static final int GL_PIXEL_MAP_I_TO_B = 3188;
/*  248:     */  public static final int GL_PIXEL_MAP_I_TO_A = 3189;
/*  249:     */  public static final int GL_PIXEL_MAP_R_TO_R = 3190;
/*  250:     */  public static final int GL_PIXEL_MAP_G_TO_G = 3191;
/*  251:     */  public static final int GL_PIXEL_MAP_B_TO_B = 3192;
/*  252:     */  public static final int GL_PIXEL_MAP_A_TO_A = 3193;
/*  253:     */  public static final int GL_PIXEL_MAP_I_TO_I_SIZE = 3248;
/*  254:     */  public static final int GL_PIXEL_MAP_S_TO_S_SIZE = 3249;
/*  255:     */  public static final int GL_PIXEL_MAP_I_TO_R_SIZE = 3250;
/*  256:     */  public static final int GL_PIXEL_MAP_I_TO_G_SIZE = 3251;
/*  257:     */  public static final int GL_PIXEL_MAP_I_TO_B_SIZE = 3252;
/*  258:     */  public static final int GL_PIXEL_MAP_I_TO_A_SIZE = 3253;
/*  259:     */  public static final int GL_PIXEL_MAP_R_TO_R_SIZE = 3254;
/*  260:     */  public static final int GL_PIXEL_MAP_G_TO_G_SIZE = 3255;
/*  261:     */  public static final int GL_PIXEL_MAP_B_TO_B_SIZE = 3256;
/*  262:     */  public static final int GL_PIXEL_MAP_A_TO_A_SIZE = 3257;
/*  263:     */  public static final int GL_UNPACK_SWAP_BYTES = 3312;
/*  264:     */  public static final int GL_UNPACK_LSB_FIRST = 3313;
/*  265:     */  public static final int GL_UNPACK_ROW_LENGTH = 3314;
/*  266:     */  public static final int GL_UNPACK_SKIP_ROWS = 3315;
/*  267:     */  public static final int GL_UNPACK_SKIP_PIXELS = 3316;
/*  268:     */  public static final int GL_UNPACK_ALIGNMENT = 3317;
/*  269:     */  public static final int GL_PACK_SWAP_BYTES = 3328;
/*  270:     */  public static final int GL_PACK_LSB_FIRST = 3329;
/*  271:     */  public static final int GL_PACK_ROW_LENGTH = 3330;
/*  272:     */  public static final int GL_PACK_SKIP_ROWS = 3331;
/*  273:     */  public static final int GL_PACK_SKIP_PIXELS = 3332;
/*  274:     */  public static final int GL_PACK_ALIGNMENT = 3333;
/*  275:     */  public static final int GL_MAP_COLOR = 3344;
/*  276:     */  public static final int GL_MAP_STENCIL = 3345;
/*  277:     */  public static final int GL_INDEX_SHIFT = 3346;
/*  278:     */  public static final int GL_INDEX_OFFSET = 3347;
/*  279:     */  public static final int GL_RED_SCALE = 3348;
/*  280:     */  public static final int GL_RED_BIAS = 3349;
/*  281:     */  public static final int GL_ZOOM_X = 3350;
/*  282:     */  public static final int GL_ZOOM_Y = 3351;
/*  283:     */  public static final int GL_GREEN_SCALE = 3352;
/*  284:     */  public static final int GL_GREEN_BIAS = 3353;
/*  285:     */  public static final int GL_BLUE_SCALE = 3354;
/*  286:     */  public static final int GL_BLUE_BIAS = 3355;
/*  287:     */  public static final int GL_ALPHA_SCALE = 3356;
/*  288:     */  public static final int GL_ALPHA_BIAS = 3357;
/*  289:     */  public static final int GL_DEPTH_SCALE = 3358;
/*  290:     */  public static final int GL_DEPTH_BIAS = 3359;
/*  291:     */  public static final int GL_MAX_EVAL_ORDER = 3376;
/*  292:     */  public static final int GL_MAX_LIGHTS = 3377;
/*  293:     */  public static final int GL_MAX_CLIP_PLANES = 3378;
/*  294:     */  public static final int GL_MAX_TEXTURE_SIZE = 3379;
/*  295:     */  public static final int GL_MAX_PIXEL_MAP_TABLE = 3380;
/*  296:     */  public static final int GL_MAX_ATTRIB_STACK_DEPTH = 3381;
/*  297:     */  public static final int GL_MAX_MODELVIEW_STACK_DEPTH = 3382;
/*  298:     */  public static final int GL_MAX_NAME_STACK_DEPTH = 3383;
/*  299:     */  public static final int GL_MAX_PROJECTION_STACK_DEPTH = 3384;
/*  300:     */  public static final int GL_MAX_TEXTURE_STACK_DEPTH = 3385;
/*  301:     */  public static final int GL_MAX_VIEWPORT_DIMS = 3386;
/*  302:     */  public static final int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 3387;
/*  303:     */  public static final int GL_SUBPIXEL_BITS = 3408;
/*  304:     */  public static final int GL_INDEX_BITS = 3409;
/*  305:     */  public static final int GL_RED_BITS = 3410;
/*  306:     */  public static final int GL_GREEN_BITS = 3411;
/*  307:     */  public static final int GL_BLUE_BITS = 3412;
/*  308:     */  public static final int GL_ALPHA_BITS = 3413;
/*  309:     */  public static final int GL_DEPTH_BITS = 3414;
/*  310:     */  public static final int GL_STENCIL_BITS = 3415;
/*  311:     */  public static final int GL_ACCUM_RED_BITS = 3416;
/*  312:     */  public static final int GL_ACCUM_GREEN_BITS = 3417;
/*  313:     */  public static final int GL_ACCUM_BLUE_BITS = 3418;
/*  314:     */  public static final int GL_ACCUM_ALPHA_BITS = 3419;
/*  315:     */  public static final int GL_NAME_STACK_DEPTH = 3440;
/*  316:     */  public static final int GL_AUTO_NORMAL = 3456;
/*  317:     */  public static final int GL_MAP1_COLOR_4 = 3472;
/*  318:     */  public static final int GL_MAP1_INDEX = 3473;
/*  319:     */  public static final int GL_MAP1_NORMAL = 3474;
/*  320:     */  public static final int GL_MAP1_TEXTURE_COORD_1 = 3475;
/*  321:     */  public static final int GL_MAP1_TEXTURE_COORD_2 = 3476;
/*  322:     */  public static final int GL_MAP1_TEXTURE_COORD_3 = 3477;
/*  323:     */  public static final int GL_MAP1_TEXTURE_COORD_4 = 3478;
/*  324:     */  public static final int GL_MAP1_VERTEX_3 = 3479;
/*  325:     */  public static final int GL_MAP1_VERTEX_4 = 3480;
/*  326:     */  public static final int GL_MAP2_COLOR_4 = 3504;
/*  327:     */  public static final int GL_MAP2_INDEX = 3505;
/*  328:     */  public static final int GL_MAP2_NORMAL = 3506;
/*  329:     */  public static final int GL_MAP2_TEXTURE_COORD_1 = 3507;
/*  330:     */  public static final int GL_MAP2_TEXTURE_COORD_2 = 3508;
/*  331:     */  public static final int GL_MAP2_TEXTURE_COORD_3 = 3509;
/*  332:     */  public static final int GL_MAP2_TEXTURE_COORD_4 = 3510;
/*  333:     */  public static final int GL_MAP2_VERTEX_3 = 3511;
/*  334:     */  public static final int GL_MAP2_VERTEX_4 = 3512;
/*  335:     */  public static final int GL_MAP1_GRID_DOMAIN = 3536;
/*  336:     */  public static final int GL_MAP1_GRID_SEGMENTS = 3537;
/*  337:     */  public static final int GL_MAP2_GRID_DOMAIN = 3538;
/*  338:     */  public static final int GL_MAP2_GRID_SEGMENTS = 3539;
/*  339:     */  public static final int GL_TEXTURE_1D = 3552;
/*  340:     */  public static final int GL_TEXTURE_2D = 3553;
/*  341:     */  public static final int GL_FEEDBACK_BUFFER_POINTER = 3568;
/*  342:     */  public static final int GL_FEEDBACK_BUFFER_SIZE = 3569;
/*  343:     */  public static final int GL_FEEDBACK_BUFFER_TYPE = 3570;
/*  344:     */  public static final int GL_SELECTION_BUFFER_POINTER = 3571;
/*  345:     */  public static final int GL_SELECTION_BUFFER_SIZE = 3572;
/*  346:     */  public static final int GL_TEXTURE_WIDTH = 4096;
/*  347:     */  public static final int GL_TEXTURE_HEIGHT = 4097;
/*  348:     */  public static final int GL_TEXTURE_INTERNAL_FORMAT = 4099;
/*  349:     */  public static final int GL_TEXTURE_BORDER_COLOR = 4100;
/*  350:     */  public static final int GL_TEXTURE_BORDER = 4101;
/*  351:     */  public static final int GL_DONT_CARE = 4352;
/*  352:     */  public static final int GL_FASTEST = 4353;
/*  353:     */  public static final int GL_NICEST = 4354;
/*  354:     */  public static final int GL_LIGHT0 = 16384;
/*  355:     */  public static final int GL_LIGHT1 = 16385;
/*  356:     */  public static final int GL_LIGHT2 = 16386;
/*  357:     */  public static final int GL_LIGHT3 = 16387;
/*  358:     */  public static final int GL_LIGHT4 = 16388;
/*  359:     */  public static final int GL_LIGHT5 = 16389;
/*  360:     */  public static final int GL_LIGHT6 = 16390;
/*  361:     */  public static final int GL_LIGHT7 = 16391;
/*  362:     */  public static final int GL_AMBIENT = 4608;
/*  363:     */  public static final int GL_DIFFUSE = 4609;
/*  364:     */  public static final int GL_SPECULAR = 4610;
/*  365:     */  public static final int GL_POSITION = 4611;
/*  366:     */  public static final int GL_SPOT_DIRECTION = 4612;
/*  367:     */  public static final int GL_SPOT_EXPONENT = 4613;
/*  368:     */  public static final int GL_SPOT_CUTOFF = 4614;
/*  369:     */  public static final int GL_CONSTANT_ATTENUATION = 4615;
/*  370:     */  public static final int GL_LINEAR_ATTENUATION = 4616;
/*  371:     */  public static final int GL_QUADRATIC_ATTENUATION = 4617;
/*  372:     */  public static final int GL_COMPILE = 4864;
/*  373:     */  public static final int GL_COMPILE_AND_EXECUTE = 4865;
/*  374:     */  public static final int GL_CLEAR = 5376;
/*  375:     */  public static final int GL_AND = 5377;
/*  376:     */  public static final int GL_AND_REVERSE = 5378;
/*  377:     */  public static final int GL_COPY = 5379;
/*  378:     */  public static final int GL_AND_INVERTED = 5380;
/*  379:     */  public static final int GL_NOOP = 5381;
/*  380:     */  public static final int GL_XOR = 5382;
/*  381:     */  public static final int GL_OR = 5383;
/*  382:     */  public static final int GL_NOR = 5384;
/*  383:     */  public static final int GL_EQUIV = 5385;
/*  384:     */  public static final int GL_INVERT = 5386;
/*  385:     */  public static final int GL_OR_REVERSE = 5387;
/*  386:     */  public static final int GL_COPY_INVERTED = 5388;
/*  387:     */  public static final int GL_OR_INVERTED = 5389;
/*  388:     */  public static final int GL_NAND = 5390;
/*  389:     */  public static final int GL_SET = 5391;
/*  390:     */  public static final int GL_EMISSION = 5632;
/*  391:     */  public static final int GL_SHININESS = 5633;
/*  392:     */  public static final int GL_AMBIENT_AND_DIFFUSE = 5634;
/*  393:     */  public static final int GL_COLOR_INDEXES = 5635;
/*  394:     */  public static final int GL_MODELVIEW = 5888;
/*  395:     */  public static final int GL_PROJECTION = 5889;
/*  396:     */  public static final int GL_TEXTURE = 5890;
/*  397:     */  public static final int GL_COLOR = 6144;
/*  398:     */  public static final int GL_DEPTH = 6145;
/*  399:     */  public static final int GL_STENCIL = 6146;
/*  400:     */  public static final int GL_COLOR_INDEX = 6400;
/*  401:     */  public static final int GL_STENCIL_INDEX = 6401;
/*  402:     */  public static final int GL_DEPTH_COMPONENT = 6402;
/*  403:     */  public static final int GL_RED = 6403;
/*  404:     */  public static final int GL_GREEN = 6404;
/*  405:     */  public static final int GL_BLUE = 6405;
/*  406:     */  public static final int GL_ALPHA = 6406;
/*  407:     */  public static final int GL_RGB = 6407;
/*  408:     */  public static final int GL_RGBA = 6408;
/*  409:     */  public static final int GL_LUMINANCE = 6409;
/*  410:     */  public static final int GL_LUMINANCE_ALPHA = 6410;
/*  411:     */  public static final int GL_BITMAP = 6656;
/*  412:     */  public static final int GL_POINT = 6912;
/*  413:     */  public static final int GL_LINE = 6913;
/*  414:     */  public static final int GL_FILL = 6914;
/*  415:     */  public static final int GL_RENDER = 7168;
/*  416:     */  public static final int GL_FEEDBACK = 7169;
/*  417:     */  public static final int GL_SELECT = 7170;
/*  418:     */  public static final int GL_FLAT = 7424;
/*  419:     */  public static final int GL_SMOOTH = 7425;
/*  420:     */  public static final int GL_KEEP = 7680;
/*  421:     */  public static final int GL_REPLACE = 7681;
/*  422:     */  public static final int GL_INCR = 7682;
/*  423:     */  public static final int GL_DECR = 7683;
/*  424:     */  public static final int GL_VENDOR = 7936;
/*  425:     */  public static final int GL_RENDERER = 7937;
/*  426:     */  public static final int GL_VERSION = 7938;
/*  427:     */  public static final int GL_EXTENSIONS = 7939;
/*  428:     */  public static final int GL_S = 8192;
/*  429:     */  public static final int GL_T = 8193;
/*  430:     */  public static final int GL_R = 8194;
/*  431:     */  public static final int GL_Q = 8195;
/*  432:     */  public static final int GL_MODULATE = 8448;
/*  433:     */  public static final int GL_DECAL = 8449;
/*  434:     */  public static final int GL_TEXTURE_ENV_MODE = 8704;
/*  435:     */  public static final int GL_TEXTURE_ENV_COLOR = 8705;
/*  436:     */  public static final int GL_TEXTURE_ENV = 8960;
/*  437:     */  public static final int GL_EYE_LINEAR = 9216;
/*  438:     */  public static final int GL_OBJECT_LINEAR = 9217;
/*  439:     */  public static final int GL_SPHERE_MAP = 9218;
/*  440:     */  public static final int GL_TEXTURE_GEN_MODE = 9472;
/*  441:     */  public static final int GL_OBJECT_PLANE = 9473;
/*  442:     */  public static final int GL_EYE_PLANE = 9474;
/*  443:     */  public static final int GL_NEAREST = 9728;
/*  444:     */  public static final int GL_LINEAR = 9729;
/*  445:     */  public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;
/*  446:     */  public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;
/*  447:     */  public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;
/*  448:     */  public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;
/*  449:     */  public static final int GL_TEXTURE_MAG_FILTER = 10240;
/*  450:     */  public static final int GL_TEXTURE_MIN_FILTER = 10241;
/*  451:     */  public static final int GL_TEXTURE_WRAP_S = 10242;
/*  452:     */  public static final int GL_TEXTURE_WRAP_T = 10243;
/*  453:     */  public static final int GL_CLAMP = 10496;
/*  454:     */  public static final int GL_REPEAT = 10497;
/*  455:     */  public static final int GL_CLIENT_PIXEL_STORE_BIT = 1;
/*  456:     */  public static final int GL_CLIENT_VERTEX_ARRAY_BIT = 2;
/*  457:     */  public static final int GL_ALL_CLIENT_ATTRIB_BITS = -1;
/*  458:     */  public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
/*  459:     */  public static final int GL_POLYGON_OFFSET_UNITS = 10752;
/*  460:     */  public static final int GL_POLYGON_OFFSET_POINT = 10753;
/*  461:     */  public static final int GL_POLYGON_OFFSET_LINE = 10754;
/*  462:     */  public static final int GL_POLYGON_OFFSET_FILL = 32823;
/*  463:     */  public static final int GL_ALPHA4 = 32827;
/*  464:     */  public static final int GL_ALPHA8 = 32828;
/*  465:     */  public static final int GL_ALPHA12 = 32829;
/*  466:     */  public static final int GL_ALPHA16 = 32830;
/*  467:     */  public static final int GL_LUMINANCE4 = 32831;
/*  468:     */  public static final int GL_LUMINANCE8 = 32832;
/*  469:     */  public static final int GL_LUMINANCE12 = 32833;
/*  470:     */  public static final int GL_LUMINANCE16 = 32834;
/*  471:     */  public static final int GL_LUMINANCE4_ALPHA4 = 32835;
/*  472:     */  public static final int GL_LUMINANCE6_ALPHA2 = 32836;
/*  473:     */  public static final int GL_LUMINANCE8_ALPHA8 = 32837;
/*  474:     */  public static final int GL_LUMINANCE12_ALPHA4 = 32838;
/*  475:     */  public static final int GL_LUMINANCE12_ALPHA12 = 32839;
/*  476:     */  public static final int GL_LUMINANCE16_ALPHA16 = 32840;
/*  477:     */  public static final int GL_INTENSITY = 32841;
/*  478:     */  public static final int GL_INTENSITY4 = 32842;
/*  479:     */  public static final int GL_INTENSITY8 = 32843;
/*  480:     */  public static final int GL_INTENSITY12 = 32844;
/*  481:     */  public static final int GL_INTENSITY16 = 32845;
/*  482:     */  public static final int GL_R3_G3_B2 = 10768;
/*  483:     */  public static final int GL_RGB4 = 32847;
/*  484:     */  public static final int GL_RGB5 = 32848;
/*  485:     */  public static final int GL_RGB8 = 32849;
/*  486:     */  public static final int GL_RGB10 = 32850;
/*  487:     */  public static final int GL_RGB12 = 32851;
/*  488:     */  public static final int GL_RGB16 = 32852;
/*  489:     */  public static final int GL_RGBA2 = 32853;
/*  490:     */  public static final int GL_RGBA4 = 32854;
/*  491:     */  public static final int GL_RGB5_A1 = 32855;
/*  492:     */  public static final int GL_RGBA8 = 32856;
/*  493:     */  public static final int GL_RGB10_A2 = 32857;
/*  494:     */  public static final int GL_RGBA12 = 32858;
/*  495:     */  public static final int GL_RGBA16 = 32859;
/*  496:     */  public static final int GL_TEXTURE_RED_SIZE = 32860;
/*  497:     */  public static final int GL_TEXTURE_GREEN_SIZE = 32861;
/*  498:     */  public static final int GL_TEXTURE_BLUE_SIZE = 32862;
/*  499:     */  public static final int GL_TEXTURE_ALPHA_SIZE = 32863;
/*  500:     */  public static final int GL_TEXTURE_LUMINANCE_SIZE = 32864;
/*  501:     */  public static final int GL_TEXTURE_INTENSITY_SIZE = 32865;
/*  502:     */  public static final int GL_PROXY_TEXTURE_1D = 32867;
/*  503:     */  public static final int GL_PROXY_TEXTURE_2D = 32868;
/*  504:     */  public static final int GL_TEXTURE_PRIORITY = 32870;
/*  505:     */  public static final int GL_TEXTURE_RESIDENT = 32871;
/*  506:     */  public static final int GL_TEXTURE_BINDING_1D = 32872;
/*  507:     */  public static final int GL_TEXTURE_BINDING_2D = 32873;
/*  508:     */  public static final int GL_VERTEX_ARRAY = 32884;
/*  509:     */  public static final int GL_NORMAL_ARRAY = 32885;
/*  510:     */  public static final int GL_COLOR_ARRAY = 32886;
/*  511:     */  public static final int GL_INDEX_ARRAY = 32887;
/*  512:     */  public static final int GL_TEXTURE_COORD_ARRAY = 32888;
/*  513:     */  public static final int GL_EDGE_FLAG_ARRAY = 32889;
/*  514:     */  public static final int GL_VERTEX_ARRAY_SIZE = 32890;
/*  515:     */  public static final int GL_VERTEX_ARRAY_TYPE = 32891;
/*  516:     */  public static final int GL_VERTEX_ARRAY_STRIDE = 32892;
/*  517:     */  public static final int GL_NORMAL_ARRAY_TYPE = 32894;
/*  518:     */  public static final int GL_NORMAL_ARRAY_STRIDE = 32895;
/*  519:     */  public static final int GL_COLOR_ARRAY_SIZE = 32897;
/*  520:     */  public static final int GL_COLOR_ARRAY_TYPE = 32898;
/*  521:     */  public static final int GL_COLOR_ARRAY_STRIDE = 32899;
/*  522:     */  public static final int GL_INDEX_ARRAY_TYPE = 32901;
/*  523:     */  public static final int GL_INDEX_ARRAY_STRIDE = 32902;
/*  524:     */  public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 32904;
/*  525:     */  public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 32905;
/*  526:     */  public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 32906;
/*  527:     */  public static final int GL_EDGE_FLAG_ARRAY_STRIDE = 32908;
/*  528:     */  public static final int GL_VERTEX_ARRAY_POINTER = 32910;
/*  529:     */  public static final int GL_NORMAL_ARRAY_POINTER = 32911;
/*  530:     */  public static final int GL_COLOR_ARRAY_POINTER = 32912;
/*  531:     */  public static final int GL_INDEX_ARRAY_POINTER = 32913;
/*  532:     */  public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 32914;
/*  533:     */  public static final int GL_EDGE_FLAG_ARRAY_POINTER = 32915;
/*  534:     */  public static final int GL_V2F = 10784;
/*  535:     */  public static final int GL_V3F = 10785;
/*  536:     */  public static final int GL_C4UB_V2F = 10786;
/*  537:     */  public static final int GL_C4UB_V3F = 10787;
/*  538:     */  public static final int GL_C3F_V3F = 10788;
/*  539:     */  public static final int GL_N3F_V3F = 10789;
/*  540:     */  public static final int GL_C4F_N3F_V3F = 10790;
/*  541:     */  public static final int GL_T2F_V3F = 10791;
/*  542:     */  public static final int GL_T4F_V4F = 10792;
/*  543:     */  public static final int GL_T2F_C4UB_V3F = 10793;
/*  544:     */  public static final int GL_T2F_C3F_V3F = 10794;
/*  545:     */  public static final int GL_T2F_N3F_V3F = 10795;
/*  546:     */  public static final int GL_T2F_C4F_N3F_V3F = 10796;
/*  547:     */  public static final int GL_T4F_C4F_N3F_V4F = 10797;
/*  548:     */  public static final int GL_LOGIC_OP = 3057;
/*  549:     */  public static final int GL_TEXTURE_COMPONENTS = 4099;
/*  550:     */  
/*  551:     */  public static void glAccum(int op, float value)
/*  552:     */  {
/*  553: 553 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  554: 554 */    long function_pointer = caps.glAccum;
/*  555: 555 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  556: 556 */    nglAccum(op, value, function_pointer);
/*  557:     */  }
/*  558:     */  
/*  559:     */  static native void nglAccum(int paramInt, float paramFloat, long paramLong);
/*  560:     */  
/*  561: 561 */  public static void glAlphaFunc(int func, float ref) { ContextCapabilities caps = GLContext.getCapabilities();
/*  562: 562 */    long function_pointer = caps.glAlphaFunc;
/*  563: 563 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  564: 564 */    nglAlphaFunc(func, ref, function_pointer);
/*  565:     */  }
/*  566:     */  
/*  567:     */  static native void nglAlphaFunc(int paramInt, float paramFloat, long paramLong);
/*  568:     */  
/*  569: 569 */  public static void glClearColor(float red, float green, float blue, float alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  570: 570 */    long function_pointer = caps.glClearColor;
/*  571: 571 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  572: 572 */    nglClearColor(red, green, blue, alpha, function_pointer);
/*  573:     */  }
/*  574:     */  
/*  575:     */  static native void nglClearColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  576:     */  
/*  577: 577 */  public static void glClearAccum(float red, float green, float blue, float alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  578: 578 */    long function_pointer = caps.glClearAccum;
/*  579: 579 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  580: 580 */    nglClearAccum(red, green, blue, alpha, function_pointer);
/*  581:     */  }
/*  582:     */  
/*  583:     */  static native void nglClearAccum(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  584:     */  
/*  585: 585 */  public static void glClear(int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/*  586: 586 */    long function_pointer = caps.glClear;
/*  587: 587 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  588: 588 */    nglClear(mask, function_pointer);
/*  589:     */  }
/*  590:     */  
/*  591:     */  static native void nglClear(int paramInt, long paramLong);
/*  592:     */  
/*  593: 593 */  public static void glCallLists(ByteBuffer lists) { ContextCapabilities caps = GLContext.getCapabilities();
/*  594: 594 */    long function_pointer = caps.glCallLists;
/*  595: 595 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  596: 596 */    BufferChecks.checkDirect(lists);
/*  597: 597 */    nglCallLists(lists.remaining(), 5121, MemoryUtil.getAddress(lists), function_pointer);
/*  598:     */  }
/*  599:     */  
/*  600: 600 */  public static void glCallLists(IntBuffer lists) { ContextCapabilities caps = GLContext.getCapabilities();
/*  601: 601 */    long function_pointer = caps.glCallLists;
/*  602: 602 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  603: 603 */    BufferChecks.checkDirect(lists);
/*  604: 604 */    nglCallLists(lists.remaining(), 5125, MemoryUtil.getAddress(lists), function_pointer);
/*  605:     */  }
/*  606:     */  
/*  607: 607 */  public static void glCallLists(ShortBuffer lists) { ContextCapabilities caps = GLContext.getCapabilities();
/*  608: 608 */    long function_pointer = caps.glCallLists;
/*  609: 609 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  610: 610 */    BufferChecks.checkDirect(lists);
/*  611: 611 */    nglCallLists(lists.remaining(), 5123, MemoryUtil.getAddress(lists), function_pointer);
/*  612:     */  }
/*  613:     */  
/*  614:     */  static native void nglCallLists(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  615:     */  
/*  616: 616 */  public static void glCallList(int list) { ContextCapabilities caps = GLContext.getCapabilities();
/*  617: 617 */    long function_pointer = caps.glCallList;
/*  618: 618 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  619: 619 */    nglCallList(list, function_pointer);
/*  620:     */  }
/*  621:     */  
/*  622:     */  static native void nglCallList(int paramInt, long paramLong);
/*  623:     */  
/*  624: 624 */  public static void glBlendFunc(int sfactor, int dfactor) { ContextCapabilities caps = GLContext.getCapabilities();
/*  625: 625 */    long function_pointer = caps.glBlendFunc;
/*  626: 626 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  627: 627 */    nglBlendFunc(sfactor, dfactor, function_pointer);
/*  628:     */  }
/*  629:     */  
/*  630:     */  static native void nglBlendFunc(int paramInt1, int paramInt2, long paramLong);
/*  631:     */  
/*  632: 632 */  public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) { ContextCapabilities caps = GLContext.getCapabilities();
/*  633: 633 */    long function_pointer = caps.glBitmap;
/*  634: 634 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  635: 635 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  636: 636 */    if (bitmap != null)
/*  637: 637 */      BufferChecks.checkBuffer(bitmap, (width + 7) / 8 * height);
/*  638: 638 */    nglBitmap(width, height, xorig, yorig, xmove, ymove, MemoryUtil.getAddressSafe(bitmap), function_pointer); }
/*  639:     */  
/*  640:     */  static native void nglBitmap(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2);
/*  641:     */  
/*  642: 642 */  public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, long bitmap_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  643: 643 */    long function_pointer = caps.glBitmap;
/*  644: 644 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  645: 645 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  646: 646 */    nglBitmapBO(width, height, xorig, yorig, xmove, ymove, bitmap_buffer_offset, function_pointer);
/*  647:     */  }
/*  648:     */  
/*  649:     */  static native void nglBitmapBO(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2);
/*  650:     */  
/*  651: 651 */  public static void glBindTexture(int target, int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/*  652: 652 */    long function_pointer = caps.glBindTexture;
/*  653: 653 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  654: 654 */    nglBindTexture(target, texture, function_pointer);
/*  655:     */  }
/*  656:     */  
/*  657:     */  static native void nglBindTexture(int paramInt1, int paramInt2, long paramLong);
/*  658:     */  
/*  659: 659 */  public static void glPrioritizeTextures(IntBuffer textures, FloatBuffer priorities) { ContextCapabilities caps = GLContext.getCapabilities();
/*  660: 660 */    long function_pointer = caps.glPrioritizeTextures;
/*  661: 661 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  662: 662 */    BufferChecks.checkDirect(textures);
/*  663: 663 */    BufferChecks.checkBuffer(priorities, textures.remaining());
/*  664: 664 */    nglPrioritizeTextures(textures.remaining(), MemoryUtil.getAddress(textures), MemoryUtil.getAddress(priorities), function_pointer);
/*  665:     */  }
/*  666:     */  
/*  667:     */  static native void nglPrioritizeTextures(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*  668:     */  
/*  669: 669 */  public static boolean glAreTexturesResident(IntBuffer textures, ByteBuffer residences) { ContextCapabilities caps = GLContext.getCapabilities();
/*  670: 670 */    long function_pointer = caps.glAreTexturesResident;
/*  671: 671 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  672: 672 */    BufferChecks.checkDirect(textures);
/*  673: 673 */    BufferChecks.checkBuffer(residences, textures.remaining());
/*  674: 674 */    boolean __result = nglAreTexturesResident(textures.remaining(), MemoryUtil.getAddress(textures), MemoryUtil.getAddress(residences), function_pointer);
/*  675: 675 */    return __result;
/*  676:     */  }
/*  677:     */  
/*  678:     */  static native boolean nglAreTexturesResident(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*  679:     */  
/*  680: 680 */  public static void glBegin(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/*  681: 681 */    long function_pointer = caps.glBegin;
/*  682: 682 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  683:     */    
/*  684: 684 */    nglBegin(mode, function_pointer);
/*  685:     */  }
/*  686:     */  
/*  687:     */  static native void nglBegin(int paramInt, long paramLong);
/*  688:     */  
/*  689: 689 */  public static void glEnd() { ContextCapabilities caps = GLContext.getCapabilities();
/*  690: 690 */    long function_pointer = caps.glEnd;
/*  691: 691 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  692:     */    
/*  693: 693 */    nglEnd(function_pointer);
/*  694:     */  }
/*  695:     */  
/*  696:     */  static native void nglEnd(long paramLong);
/*  697:     */  
/*  698: 698 */  public static void glArrayElement(int i) { ContextCapabilities caps = GLContext.getCapabilities();
/*  699: 699 */    long function_pointer = caps.glArrayElement;
/*  700: 700 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  701: 701 */    nglArrayElement(i, function_pointer);
/*  702:     */  }
/*  703:     */  
/*  704:     */  static native void nglArrayElement(int paramInt, long paramLong);
/*  705:     */  
/*  706: 706 */  public static void glClearDepth(double depth) { ContextCapabilities caps = GLContext.getCapabilities();
/*  707: 707 */    long function_pointer = caps.glClearDepth;
/*  708: 708 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  709: 709 */    nglClearDepth(depth, function_pointer);
/*  710:     */  }
/*  711:     */  
/*  712:     */  static native void nglClearDepth(double paramDouble, long paramLong);
/*  713:     */  
/*  714: 714 */  public static void glDeleteLists(int list, int range) { ContextCapabilities caps = GLContext.getCapabilities();
/*  715: 715 */    long function_pointer = caps.glDeleteLists;
/*  716: 716 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  717: 717 */    nglDeleteLists(list, range, function_pointer);
/*  718:     */  }
/*  719:     */  
/*  720:     */  static native void nglDeleteLists(int paramInt1, int paramInt2, long paramLong);
/*  721:     */  
/*  722: 722 */  public static void glDeleteTextures(IntBuffer textures) { ContextCapabilities caps = GLContext.getCapabilities();
/*  723: 723 */    long function_pointer = caps.glDeleteTextures;
/*  724: 724 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  725: 725 */    BufferChecks.checkDirect(textures);
/*  726: 726 */    nglDeleteTextures(textures.remaining(), MemoryUtil.getAddress(textures), function_pointer);
/*  727:     */  }
/*  728:     */  
/*  729:     */  static native void nglDeleteTextures(int paramInt, long paramLong1, long paramLong2);
/*  730:     */  
/*  731:     */  public static void glDeleteTextures(int texture) {
/*  732: 732 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  733: 733 */    long function_pointer = caps.glDeleteTextures;
/*  734: 734 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  735: 735 */    nglDeleteTextures(1, APIUtil.getInt(caps, texture), function_pointer);
/*  736:     */  }
/*  737:     */  
/*  738:     */  public static void glCullFace(int mode) {
/*  739: 739 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  740: 740 */    long function_pointer = caps.glCullFace;
/*  741: 741 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  742: 742 */    nglCullFace(mode, function_pointer);
/*  743:     */  }
/*  744:     */  
/*  745:     */  static native void nglCullFace(int paramInt, long paramLong);
/*  746:     */  
/*  747: 747 */  public static void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/*  748: 748 */    long function_pointer = caps.glCopyTexSubImage2D;
/*  749: 749 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  750: 750 */    nglCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height, function_pointer);
/*  751:     */  }
/*  752:     */  
/*  753:     */  static native void nglCopyTexSubImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*  754:     */  
/*  755: 755 */  public static void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) { ContextCapabilities caps = GLContext.getCapabilities();
/*  756: 756 */    long function_pointer = caps.glCopyTexSubImage1D;
/*  757: 757 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  758: 758 */    nglCopyTexSubImage1D(target, level, xoffset, x, y, width, function_pointer);
/*  759:     */  }
/*  760:     */  
/*  761:     */  static native void nglCopyTexSubImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  762:     */  
/*  763: 763 */  public static void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) { ContextCapabilities caps = GLContext.getCapabilities();
/*  764: 764 */    long function_pointer = caps.glCopyTexImage2D;
/*  765: 765 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  766: 766 */    nglCopyTexImage2D(target, level, internalFormat, x, y, width, height, border, function_pointer);
/*  767:     */  }
/*  768:     */  
/*  769:     */  static native void nglCopyTexImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*  770:     */  
/*  771: 771 */  public static void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) { ContextCapabilities caps = GLContext.getCapabilities();
/*  772: 772 */    long function_pointer = caps.glCopyTexImage1D;
/*  773: 773 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  774: 774 */    nglCopyTexImage1D(target, level, internalFormat, x, y, width, border, function_pointer);
/*  775:     */  }
/*  776:     */  
/*  777:     */  static native void nglCopyTexImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*  778:     */  
/*  779: 779 */  public static void glCopyPixels(int x, int y, int width, int height, int type) { ContextCapabilities caps = GLContext.getCapabilities();
/*  780: 780 */    long function_pointer = caps.glCopyPixels;
/*  781: 781 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  782: 782 */    nglCopyPixels(x, y, width, height, type, function_pointer);
/*  783:     */  }
/*  784:     */  
/*  785:     */  static native void nglCopyPixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  786:     */  
/*  787: 787 */  public static void glColorPointer(int size, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  788: 788 */    long function_pointer = caps.glColorPointer;
/*  789: 789 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  790: 790 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  791: 791 */    BufferChecks.checkDirect(pointer);
/*  792: 792 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  793: 793 */    nglColorPointer(size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*  794:     */  }
/*  795:     */  
/*  796: 796 */  public static void glColorPointer(int size, int stride, FloatBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  797: 797 */    long function_pointer = caps.glColorPointer;
/*  798: 798 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  799: 799 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  800: 800 */    BufferChecks.checkDirect(pointer);
/*  801: 801 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  802: 802 */    nglColorPointer(size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*  803:     */  }
/*  804:     */  
/*  805: 805 */  public static void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  806: 806 */    long function_pointer = caps.glColorPointer;
/*  807: 807 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  808: 808 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  809: 809 */    BufferChecks.checkDirect(pointer);
/*  810: 810 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  811: 811 */    nglColorPointer(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/*  812:     */  
/*  813:     */  static native void nglColorPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  814:     */  
/*  815: 815 */  public static void glColorPointer(int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  816: 816 */    long function_pointer = caps.glColorPointer;
/*  817: 817 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  818: 818 */    GLChecks.ensureArrayVBOenabled(caps);
/*  819: 819 */    nglColorPointerBO(size, type, stride, pointer_buffer_offset, function_pointer);
/*  820:     */  }
/*  821:     */  
/*  822:     */  static native void nglColorPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  823:     */  
/*  824:     */  public static void glColorPointer(int size, int type, int stride, ByteBuffer pointer) {
/*  825: 825 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  826: 826 */    long function_pointer = caps.glColorPointer;
/*  827: 827 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  828: 828 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  829: 829 */    BufferChecks.checkDirect(pointer);
/*  830: 830 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glColorPointer_pointer = pointer;
/*  831: 831 */    nglColorPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*  832:     */  }
/*  833:     */  
/*  834:     */  public static void glColorMaterial(int face, int mode) {
/*  835: 835 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  836: 836 */    long function_pointer = caps.glColorMaterial;
/*  837: 837 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  838: 838 */    nglColorMaterial(face, mode, function_pointer);
/*  839:     */  }
/*  840:     */  
/*  841:     */  static native void nglColorMaterial(int paramInt1, int paramInt2, long paramLong);
/*  842:     */  
/*  843: 843 */  public static void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  844: 844 */    long function_pointer = caps.glColorMask;
/*  845: 845 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  846: 846 */    nglColorMask(red, green, blue, alpha, function_pointer);
/*  847:     */  }
/*  848:     */  
/*  849:     */  static native void nglColorMask(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, long paramLong);
/*  850:     */  
/*  851: 851 */  public static void glColor3b(byte red, byte green, byte blue) { ContextCapabilities caps = GLContext.getCapabilities();
/*  852: 852 */    long function_pointer = caps.glColor3b;
/*  853: 853 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  854: 854 */    nglColor3b(red, green, blue, function_pointer);
/*  855:     */  }
/*  856:     */  
/*  857:     */  static native void nglColor3b(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*  858:     */  
/*  859: 859 */  public static void glColor3f(float red, float green, float blue) { ContextCapabilities caps = GLContext.getCapabilities();
/*  860: 860 */    long function_pointer = caps.glColor3f;
/*  861: 861 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  862: 862 */    nglColor3f(red, green, blue, function_pointer);
/*  863:     */  }
/*  864:     */  
/*  865:     */  static native void nglColor3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  866:     */  
/*  867: 867 */  public static void glColor3d(double red, double green, double blue) { ContextCapabilities caps = GLContext.getCapabilities();
/*  868: 868 */    long function_pointer = caps.glColor3d;
/*  869: 869 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  870: 870 */    nglColor3d(red, green, blue, function_pointer);
/*  871:     */  }
/*  872:     */  
/*  873:     */  static native void nglColor3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  874:     */  
/*  875: 875 */  public static void glColor3ub(byte red, byte green, byte blue) { ContextCapabilities caps = GLContext.getCapabilities();
/*  876: 876 */    long function_pointer = caps.glColor3ub;
/*  877: 877 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  878: 878 */    nglColor3ub(red, green, blue, function_pointer);
/*  879:     */  }
/*  880:     */  
/*  881:     */  static native void nglColor3ub(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*  882:     */  
/*  883: 883 */  public static void glColor4b(byte red, byte green, byte blue, byte alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  884: 884 */    long function_pointer = caps.glColor4b;
/*  885: 885 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  886: 886 */    nglColor4b(red, green, blue, alpha, function_pointer);
/*  887:     */  }
/*  888:     */  
/*  889:     */  static native void nglColor4b(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*  890:     */  
/*  891: 891 */  public static void glColor4f(float red, float green, float blue, float alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  892: 892 */    long function_pointer = caps.glColor4f;
/*  893: 893 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  894: 894 */    nglColor4f(red, green, blue, alpha, function_pointer);
/*  895:     */  }
/*  896:     */  
/*  897:     */  static native void nglColor4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  898:     */  
/*  899: 899 */  public static void glColor4d(double red, double green, double blue, double alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  900: 900 */    long function_pointer = caps.glColor4d;
/*  901: 901 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  902: 902 */    nglColor4d(red, green, blue, alpha, function_pointer);
/*  903:     */  }
/*  904:     */  
/*  905:     */  static native void nglColor4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*  906:     */  
/*  907: 907 */  public static void glColor4ub(byte red, byte green, byte blue, byte alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  908: 908 */    long function_pointer = caps.glColor4ub;
/*  909: 909 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  910: 910 */    nglColor4ub(red, green, blue, alpha, function_pointer);
/*  911:     */  }
/*  912:     */  
/*  913:     */  static native void nglColor4ub(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*  914:     */  
/*  915: 915 */  public static void glClipPlane(int plane, DoubleBuffer equation) { ContextCapabilities caps = GLContext.getCapabilities();
/*  916: 916 */    long function_pointer = caps.glClipPlane;
/*  917: 917 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  918: 918 */    BufferChecks.checkBuffer(equation, 4);
/*  919: 919 */    nglClipPlane(plane, MemoryUtil.getAddress(equation), function_pointer);
/*  920:     */  }
/*  921:     */  
/*  922:     */  static native void nglClipPlane(int paramInt, long paramLong1, long paramLong2);
/*  923:     */  
/*  924: 924 */  public static void glClearStencil(int s) { ContextCapabilities caps = GLContext.getCapabilities();
/*  925: 925 */    long function_pointer = caps.glClearStencil;
/*  926: 926 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  927: 927 */    nglClearStencil(s, function_pointer);
/*  928:     */  }
/*  929:     */  
/*  930:     */  static native void nglClearStencil(int paramInt, long paramLong);
/*  931:     */  
/*  932: 932 */  public static void glEvalPoint1(int i) { ContextCapabilities caps = GLContext.getCapabilities();
/*  933: 933 */    long function_pointer = caps.glEvalPoint1;
/*  934: 934 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  935: 935 */    nglEvalPoint1(i, function_pointer);
/*  936:     */  }
/*  937:     */  
/*  938:     */  static native void nglEvalPoint1(int paramInt, long paramLong);
/*  939:     */  
/*  940: 940 */  public static void glEvalPoint2(int i, int j) { ContextCapabilities caps = GLContext.getCapabilities();
/*  941: 941 */    long function_pointer = caps.glEvalPoint2;
/*  942: 942 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  943: 943 */    nglEvalPoint2(i, j, function_pointer);
/*  944:     */  }
/*  945:     */  
/*  946:     */  static native void nglEvalPoint2(int paramInt1, int paramInt2, long paramLong);
/*  947:     */  
/*  948: 948 */  public static void glEvalMesh1(int mode, int i1, int i2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  949: 949 */    long function_pointer = caps.glEvalMesh1;
/*  950: 950 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  951: 951 */    nglEvalMesh1(mode, i1, i2, function_pointer);
/*  952:     */  }
/*  953:     */  
/*  954:     */  static native void nglEvalMesh1(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  955:     */  
/*  956: 956 */  public static void glEvalMesh2(int mode, int i1, int i2, int j1, int j2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  957: 957 */    long function_pointer = caps.glEvalMesh2;
/*  958: 958 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  959: 959 */    nglEvalMesh2(mode, i1, i2, j1, j2, function_pointer);
/*  960:     */  }
/*  961:     */  
/*  962:     */  static native void nglEvalMesh2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  963:     */  
/*  964: 964 */  public static void glEvalCoord1f(float u) { ContextCapabilities caps = GLContext.getCapabilities();
/*  965: 965 */    long function_pointer = caps.glEvalCoord1f;
/*  966: 966 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  967: 967 */    nglEvalCoord1f(u, function_pointer);
/*  968:     */  }
/*  969:     */  
/*  970:     */  static native void nglEvalCoord1f(float paramFloat, long paramLong);
/*  971:     */  
/*  972: 972 */  public static void glEvalCoord1d(double u) { ContextCapabilities caps = GLContext.getCapabilities();
/*  973: 973 */    long function_pointer = caps.glEvalCoord1d;
/*  974: 974 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  975: 975 */    nglEvalCoord1d(u, function_pointer);
/*  976:     */  }
/*  977:     */  
/*  978:     */  static native void nglEvalCoord1d(double paramDouble, long paramLong);
/*  979:     */  
/*  980: 980 */  public static void glEvalCoord2f(float u, float v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  981: 981 */    long function_pointer = caps.glEvalCoord2f;
/*  982: 982 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  983: 983 */    nglEvalCoord2f(u, v, function_pointer);
/*  984:     */  }
/*  985:     */  
/*  986:     */  static native void nglEvalCoord2f(float paramFloat1, float paramFloat2, long paramLong);
/*  987:     */  
/*  988: 988 */  public static void glEvalCoord2d(double u, double v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  989: 989 */    long function_pointer = caps.glEvalCoord2d;
/*  990: 990 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  991: 991 */    nglEvalCoord2d(u, v, function_pointer);
/*  992:     */  }
/*  993:     */  
/*  994:     */  static native void nglEvalCoord2d(double paramDouble1, double paramDouble2, long paramLong);
/*  995:     */  
/*  996: 996 */  public static void glEnableClientState(int cap) { ContextCapabilities caps = GLContext.getCapabilities();
/*  997: 997 */    long function_pointer = caps.glEnableClientState;
/*  998: 998 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  999: 999 */    nglEnableClientState(cap, function_pointer);
/* 1000:     */  }
/* 1001:     */  
/* 1002:     */  static native void nglEnableClientState(int paramInt, long paramLong);
/* 1003:     */  
/* 1004:1004 */  public static void glDisableClientState(int cap) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1005:1005 */    long function_pointer = caps.glDisableClientState;
/* 1006:1006 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1007:1007 */    nglDisableClientState(cap, function_pointer);
/* 1008:     */  }
/* 1009:     */  
/* 1010:     */  static native void nglDisableClientState(int paramInt, long paramLong);
/* 1011:     */  
/* 1012:1012 */  public static void glEnable(int cap) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1013:1013 */    long function_pointer = caps.glEnable;
/* 1014:1014 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1015:1015 */    nglEnable(cap, function_pointer);
/* 1016:     */  }
/* 1017:     */  
/* 1018:     */  static native void nglEnable(int paramInt, long paramLong);
/* 1019:     */  
/* 1020:1020 */  public static void glDisable(int cap) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1021:1021 */    long function_pointer = caps.glDisable;
/* 1022:1022 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1023:1023 */    nglDisable(cap, function_pointer);
/* 1024:     */  }
/* 1025:     */  
/* 1026:     */  static native void nglDisable(int paramInt, long paramLong);
/* 1027:     */  
/* 1028:1028 */  public static void glEdgeFlagPointer(int stride, ByteBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1029:1029 */    long function_pointer = caps.glEdgeFlagPointer;
/* 1030:1030 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1031:1031 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 1032:1032 */    BufferChecks.checkDirect(pointer);
/* 1033:1033 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glEdgeFlagPointer_pointer = pointer;
/* 1034:1034 */    nglEdgeFlagPointer(stride, MemoryUtil.getAddress(pointer), function_pointer); }
/* 1035:     */  
/* 1036:     */  static native void nglEdgeFlagPointer(int paramInt, long paramLong1, long paramLong2);
/* 1037:     */  
/* 1038:1038 */  public static void glEdgeFlagPointer(int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1039:1039 */    long function_pointer = caps.glEdgeFlagPointer;
/* 1040:1040 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1041:1041 */    GLChecks.ensureArrayVBOenabled(caps);
/* 1042:1042 */    nglEdgeFlagPointerBO(stride, pointer_buffer_offset, function_pointer);
/* 1043:     */  }
/* 1044:     */  
/* 1045:     */  static native void nglEdgeFlagPointerBO(int paramInt, long paramLong1, long paramLong2);
/* 1046:     */  
/* 1047:1047 */  public static void glEdgeFlag(boolean flag) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1048:1048 */    long function_pointer = caps.glEdgeFlag;
/* 1049:1049 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1050:1050 */    nglEdgeFlag(flag, function_pointer);
/* 1051:     */  }
/* 1052:     */  
/* 1053:     */  static native void nglEdgeFlag(boolean paramBoolean, long paramLong);
/* 1054:     */  
/* 1055:1055 */  public static void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1056:1056 */    long function_pointer = caps.glDrawPixels;
/* 1057:1057 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1058:1058 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1059:1059 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1060:1060 */    nglDrawPixels(width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1061:     */  }
/* 1062:     */  
/* 1063:1063 */  public static void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1064:1064 */    long function_pointer = caps.glDrawPixels;
/* 1065:1065 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1066:1066 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1067:1067 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1068:1068 */    nglDrawPixels(width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1069:     */  }
/* 1070:     */  
/* 1071:1071 */  public static void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1072:1072 */    long function_pointer = caps.glDrawPixels;
/* 1073:1073 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1074:1074 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1075:1075 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1076:1076 */    nglDrawPixels(width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 1077:     */  
/* 1078:     */  static native void nglDrawPixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1079:     */  
/* 1080:1080 */  public static void glDrawPixels(int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1081:1081 */    long function_pointer = caps.glDrawPixels;
/* 1082:1082 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1083:1083 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1084:1084 */    nglDrawPixelsBO(width, height, format, type, pixels_buffer_offset, function_pointer);
/* 1085:     */  }
/* 1086:     */  
/* 1087:     */  static native void nglDrawPixelsBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1088:     */  
/* 1089:1089 */  public static void glDrawElements(int mode, ByteBuffer indices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1090:1090 */    long function_pointer = caps.glDrawElements;
/* 1091:1091 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1092:1092 */    GLChecks.ensureElementVBOdisabled(caps);
/* 1093:1093 */    BufferChecks.checkDirect(indices);
/* 1094:1094 */    nglDrawElements(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), function_pointer);
/* 1095:     */  }
/* 1096:     */  
/* 1097:1097 */  public static void glDrawElements(int mode, IntBuffer indices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1098:1098 */    long function_pointer = caps.glDrawElements;
/* 1099:1099 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1100:1100 */    GLChecks.ensureElementVBOdisabled(caps);
/* 1101:1101 */    BufferChecks.checkDirect(indices);
/* 1102:1102 */    nglDrawElements(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), function_pointer);
/* 1103:     */  }
/* 1104:     */  
/* 1105:1105 */  public static void glDrawElements(int mode, ShortBuffer indices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1106:1106 */    long function_pointer = caps.glDrawElements;
/* 1107:1107 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1108:1108 */    GLChecks.ensureElementVBOdisabled(caps);
/* 1109:1109 */    BufferChecks.checkDirect(indices);
/* 1110:1110 */    nglDrawElements(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), function_pointer); }
/* 1111:     */  
/* 1112:     */  static native void nglDrawElements(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1113:     */  
/* 1114:1114 */  public static void glDrawElements(int mode, int indices_count, int type, long indices_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1115:1115 */    long function_pointer = caps.glDrawElements;
/* 1116:1116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1117:1117 */    GLChecks.ensureElementVBOenabled(caps);
/* 1118:1118 */    nglDrawElementsBO(mode, indices_count, type, indices_buffer_offset, function_pointer);
/* 1119:     */  }
/* 1120:     */  
/* 1121:     */  static native void nglDrawElementsBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1122:     */  
/* 1123:1123 */  public static void glDrawBuffer(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1124:1124 */    long function_pointer = caps.glDrawBuffer;
/* 1125:1125 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1126:1126 */    nglDrawBuffer(mode, function_pointer);
/* 1127:     */  }
/* 1128:     */  
/* 1129:     */  static native void nglDrawBuffer(int paramInt, long paramLong);
/* 1130:     */  
/* 1131:1131 */  public static void glDrawArrays(int mode, int first, int count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1132:1132 */    long function_pointer = caps.glDrawArrays;
/* 1133:1133 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1134:1134 */    nglDrawArrays(mode, first, count, function_pointer);
/* 1135:     */  }
/* 1136:     */  
/* 1137:     */  static native void nglDrawArrays(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 1138:     */  
/* 1139:1139 */  public static void glDepthRange(double zNear, double zFar) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1140:1140 */    long function_pointer = caps.glDepthRange;
/* 1141:1141 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1142:1142 */    nglDepthRange(zNear, zFar, function_pointer);
/* 1143:     */  }
/* 1144:     */  
/* 1145:     */  static native void nglDepthRange(double paramDouble1, double paramDouble2, long paramLong);
/* 1146:     */  
/* 1147:1147 */  public static void glDepthMask(boolean flag) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1148:1148 */    long function_pointer = caps.glDepthMask;
/* 1149:1149 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1150:1150 */    nglDepthMask(flag, function_pointer);
/* 1151:     */  }
/* 1152:     */  
/* 1153:     */  static native void nglDepthMask(boolean paramBoolean, long paramLong);
/* 1154:     */  
/* 1155:1155 */  public static void glDepthFunc(int func) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1156:1156 */    long function_pointer = caps.glDepthFunc;
/* 1157:1157 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1158:1158 */    nglDepthFunc(func, function_pointer);
/* 1159:     */  }
/* 1160:     */  
/* 1161:     */  static native void nglDepthFunc(int paramInt, long paramLong);
/* 1162:     */  
/* 1163:1163 */  public static void glFeedbackBuffer(int type, FloatBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1164:1164 */    long function_pointer = caps.glFeedbackBuffer;
/* 1165:1165 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1166:1166 */    BufferChecks.checkDirect(buffer);
/* 1167:1167 */    nglFeedbackBuffer(buffer.remaining(), type, MemoryUtil.getAddress(buffer), function_pointer);
/* 1168:     */  }
/* 1169:     */  
/* 1170:     */  static native void nglFeedbackBuffer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1171:     */  
/* 1172:1172 */  public static void glGetPixelMap(int map, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1173:1173 */    long function_pointer = caps.glGetPixelMapfv;
/* 1174:1174 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1175:1175 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1176:1176 */    BufferChecks.checkBuffer(values, 256);
/* 1177:1177 */    nglGetPixelMapfv(map, MemoryUtil.getAddress(values), function_pointer); }
/* 1178:     */  
/* 1179:     */  static native void nglGetPixelMapfv(int paramInt, long paramLong1, long paramLong2);
/* 1180:     */  
/* 1181:1181 */  public static void glGetPixelMapfv(int map, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1182:1182 */    long function_pointer = caps.glGetPixelMapfv;
/* 1183:1183 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1184:1184 */    GLChecks.ensurePackPBOenabled(caps);
/* 1185:1185 */    nglGetPixelMapfvBO(map, values_buffer_offset, function_pointer);
/* 1186:     */  }
/* 1187:     */  
/* 1188:     */  static native void nglGetPixelMapfvBO(int paramInt, long paramLong1, long paramLong2);
/* 1189:     */  
/* 1190:1190 */  public static void glGetPixelMapu(int map, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1191:1191 */    long function_pointer = caps.glGetPixelMapuiv;
/* 1192:1192 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1193:1193 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1194:1194 */    BufferChecks.checkBuffer(values, 256);
/* 1195:1195 */    nglGetPixelMapuiv(map, MemoryUtil.getAddress(values), function_pointer); }
/* 1196:     */  
/* 1197:     */  static native void nglGetPixelMapuiv(int paramInt, long paramLong1, long paramLong2);
/* 1198:     */  
/* 1199:1199 */  public static void glGetPixelMapuiv(int map, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1200:1200 */    long function_pointer = caps.glGetPixelMapuiv;
/* 1201:1201 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1202:1202 */    GLChecks.ensurePackPBOenabled(caps);
/* 1203:1203 */    nglGetPixelMapuivBO(map, values_buffer_offset, function_pointer);
/* 1204:     */  }
/* 1205:     */  
/* 1206:     */  static native void nglGetPixelMapuivBO(int paramInt, long paramLong1, long paramLong2);
/* 1207:     */  
/* 1208:1208 */  public static void glGetPixelMapu(int map, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1209:1209 */    long function_pointer = caps.glGetPixelMapusv;
/* 1210:1210 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1211:1211 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1212:1212 */    BufferChecks.checkBuffer(values, 256);
/* 1213:1213 */    nglGetPixelMapusv(map, MemoryUtil.getAddress(values), function_pointer); }
/* 1214:     */  
/* 1215:     */  static native void nglGetPixelMapusv(int paramInt, long paramLong1, long paramLong2);
/* 1216:     */  
/* 1217:1217 */  public static void glGetPixelMapusv(int map, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1218:1218 */    long function_pointer = caps.glGetPixelMapusv;
/* 1219:1219 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1220:1220 */    GLChecks.ensurePackPBOenabled(caps);
/* 1221:1221 */    nglGetPixelMapusvBO(map, values_buffer_offset, function_pointer);
/* 1222:     */  }
/* 1223:     */  
/* 1224:     */  static native void nglGetPixelMapusvBO(int paramInt, long paramLong1, long paramLong2);
/* 1225:     */  
/* 1226:1226 */  public static void glGetMaterial(int face, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1227:1227 */    long function_pointer = caps.glGetMaterialfv;
/* 1228:1228 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1229:1229 */    BufferChecks.checkBuffer(params, 4);
/* 1230:1230 */    nglGetMaterialfv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1231:     */  }
/* 1232:     */  
/* 1233:     */  static native void nglGetMaterialfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1234:     */  
/* 1235:1235 */  public static void glGetMaterial(int face, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1236:1236 */    long function_pointer = caps.glGetMaterialiv;
/* 1237:1237 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1238:1238 */    BufferChecks.checkBuffer(params, 4);
/* 1239:1239 */    nglGetMaterialiv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1240:     */  }
/* 1241:     */  
/* 1242:     */  static native void nglGetMaterialiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1243:     */  
/* 1244:1244 */  public static void glGetMap(int target, int query, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1245:1245 */    long function_pointer = caps.glGetMapfv;
/* 1246:1246 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1247:1247 */    BufferChecks.checkBuffer(v, 256);
/* 1248:1248 */    nglGetMapfv(target, query, MemoryUtil.getAddress(v), function_pointer);
/* 1249:     */  }
/* 1250:     */  
/* 1251:     */  static native void nglGetMapfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1252:     */  
/* 1253:1253 */  public static void glGetMap(int target, int query, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1254:1254 */    long function_pointer = caps.glGetMapdv;
/* 1255:1255 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1256:1256 */    BufferChecks.checkBuffer(v, 256);
/* 1257:1257 */    nglGetMapdv(target, query, MemoryUtil.getAddress(v), function_pointer);
/* 1258:     */  }
/* 1259:     */  
/* 1260:     */  static native void nglGetMapdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1261:     */  
/* 1262:1262 */  public static void glGetMap(int target, int query, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1263:1263 */    long function_pointer = caps.glGetMapiv;
/* 1264:1264 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1265:1265 */    BufferChecks.checkBuffer(v, 256);
/* 1266:1266 */    nglGetMapiv(target, query, MemoryUtil.getAddress(v), function_pointer);
/* 1267:     */  }
/* 1268:     */  
/* 1269:     */  static native void nglGetMapiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1270:     */  
/* 1271:1271 */  public static void glGetLight(int light, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1272:1272 */    long function_pointer = caps.glGetLightfv;
/* 1273:1273 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1274:1274 */    BufferChecks.checkBuffer(params, 4);
/* 1275:1275 */    nglGetLightfv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1276:     */  }
/* 1277:     */  
/* 1278:     */  static native void nglGetLightfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1279:     */  
/* 1280:1280 */  public static void glGetLight(int light, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1281:1281 */    long function_pointer = caps.glGetLightiv;
/* 1282:1282 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1283:1283 */    BufferChecks.checkBuffer(params, 4);
/* 1284:1284 */    nglGetLightiv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1285:     */  }
/* 1286:     */  
/* 1287:     */  static native void nglGetLightiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1288:     */  
/* 1289:1289 */  public static int glGetError() { ContextCapabilities caps = GLContext.getCapabilities();
/* 1290:1290 */    long function_pointer = caps.glGetError;
/* 1291:1291 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1292:1292 */    int __result = nglGetError(function_pointer);
/* 1293:1293 */    return __result;
/* 1294:     */  }
/* 1295:     */  
/* 1296:     */  static native int nglGetError(long paramLong);
/* 1297:     */  
/* 1298:1298 */  public static void glGetClipPlane(int plane, DoubleBuffer equation) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1299:1299 */    long function_pointer = caps.glGetClipPlane;
/* 1300:1300 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1301:1301 */    BufferChecks.checkBuffer(equation, 4);
/* 1302:1302 */    nglGetClipPlane(plane, MemoryUtil.getAddress(equation), function_pointer);
/* 1303:     */  }
/* 1304:     */  
/* 1305:     */  static native void nglGetClipPlane(int paramInt, long paramLong1, long paramLong2);
/* 1306:     */  
/* 1307:1307 */  public static void glGetBoolean(int pname, ByteBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1308:1308 */    long function_pointer = caps.glGetBooleanv;
/* 1309:1309 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1310:1310 */    BufferChecks.checkBuffer(params, 16);
/* 1311:1311 */    nglGetBooleanv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1312:     */  }
/* 1313:     */  
/* 1314:     */  static native void nglGetBooleanv(int paramInt, long paramLong1, long paramLong2);
/* 1315:     */  
/* 1316:     */  public static boolean glGetBoolean(int pname) {
/* 1317:1317 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1318:1318 */    long function_pointer = caps.glGetBooleanv;
/* 1319:1319 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1320:1320 */    ByteBuffer params = APIUtil.getBufferByte(caps, 1);
/* 1321:1321 */    nglGetBooleanv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1322:1322 */    return params.get(0) == 1;
/* 1323:     */  }
/* 1324:     */  
/* 1325:     */  public static void glGetDouble(int pname, DoubleBuffer params) {
/* 1326:1326 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1327:1327 */    long function_pointer = caps.glGetDoublev;
/* 1328:1328 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1329:1329 */    BufferChecks.checkBuffer(params, 16);
/* 1330:1330 */    nglGetDoublev(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1331:     */  }
/* 1332:     */  
/* 1333:     */  static native void nglGetDoublev(int paramInt, long paramLong1, long paramLong2);
/* 1334:     */  
/* 1335:     */  public static double glGetDouble(int pname) {
/* 1336:1336 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1337:1337 */    long function_pointer = caps.glGetDoublev;
/* 1338:1338 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1339:1339 */    DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1340:1340 */    nglGetDoublev(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1341:1341 */    return params.get(0);
/* 1342:     */  }
/* 1343:     */  
/* 1344:     */  public static void glGetFloat(int pname, FloatBuffer params) {
/* 1345:1345 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1346:1346 */    long function_pointer = caps.glGetFloatv;
/* 1347:1347 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1348:1348 */    BufferChecks.checkBuffer(params, 16);
/* 1349:1349 */    nglGetFloatv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1350:     */  }
/* 1351:     */  
/* 1352:     */  static native void nglGetFloatv(int paramInt, long paramLong1, long paramLong2);
/* 1353:     */  
/* 1354:     */  public static float glGetFloat(int pname) {
/* 1355:1355 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1356:1356 */    long function_pointer = caps.glGetFloatv;
/* 1357:1357 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1358:1358 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1359:1359 */    nglGetFloatv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1360:1360 */    return params.get(0);
/* 1361:     */  }
/* 1362:     */  
/* 1363:     */  public static void glGetInteger(int pname, IntBuffer params) {
/* 1364:1364 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1365:1365 */    long function_pointer = caps.glGetIntegerv;
/* 1366:1366 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1367:1367 */    BufferChecks.checkBuffer(params, 16);
/* 1368:1368 */    nglGetIntegerv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1369:     */  }
/* 1370:     */  
/* 1371:     */  static native void nglGetIntegerv(int paramInt, long paramLong1, long paramLong2);
/* 1372:     */  
/* 1373:     */  public static int glGetInteger(int pname) {
/* 1374:1374 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1375:1375 */    long function_pointer = caps.glGetIntegerv;
/* 1376:1376 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1377:1377 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1378:1378 */    nglGetIntegerv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1379:1379 */    return params.get(0);
/* 1380:     */  }
/* 1381:     */  
/* 1382:     */  public static void glGenTextures(IntBuffer textures) {
/* 1383:1383 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1384:1384 */    long function_pointer = caps.glGenTextures;
/* 1385:1385 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1386:1386 */    BufferChecks.checkDirect(textures);
/* 1387:1387 */    nglGenTextures(textures.remaining(), MemoryUtil.getAddress(textures), function_pointer);
/* 1388:     */  }
/* 1389:     */  
/* 1390:     */  static native void nglGenTextures(int paramInt, long paramLong1, long paramLong2);
/* 1391:     */  
/* 1392:     */  public static int glGenTextures() {
/* 1393:1393 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1394:1394 */    long function_pointer = caps.glGenTextures;
/* 1395:1395 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1396:1396 */    IntBuffer textures = APIUtil.getBufferInt(caps);
/* 1397:1397 */    nglGenTextures(1, MemoryUtil.getAddress(textures), function_pointer);
/* 1398:1398 */    return textures.get(0);
/* 1399:     */  }
/* 1400:     */  
/* 1401:     */  public static int glGenLists(int range) {
/* 1402:1402 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1403:1403 */    long function_pointer = caps.glGenLists;
/* 1404:1404 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1405:1405 */    int __result = nglGenLists(range, function_pointer);
/* 1406:1406 */    return __result;
/* 1407:     */  }
/* 1408:     */  
/* 1409:     */  static native int nglGenLists(int paramInt, long paramLong);
/* 1410:     */  
/* 1411:1411 */  public static void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1412:1412 */    long function_pointer = caps.glFrustum;
/* 1413:1413 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1414:1414 */    nglFrustum(left, right, bottom, top, zNear, zFar, function_pointer);
/* 1415:     */  }
/* 1416:     */  
/* 1417:     */  static native void nglFrustum(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/* 1418:     */  
/* 1419:1419 */  public static void glFrontFace(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1420:1420 */    long function_pointer = caps.glFrontFace;
/* 1421:1421 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1422:1422 */    nglFrontFace(mode, function_pointer);
/* 1423:     */  }
/* 1424:     */  
/* 1425:     */  static native void nglFrontFace(int paramInt, long paramLong);
/* 1426:     */  
/* 1427:1427 */  public static void glFogf(int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1428:1428 */    long function_pointer = caps.glFogf;
/* 1429:1429 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1430:1430 */    nglFogf(pname, param, function_pointer);
/* 1431:     */  }
/* 1432:     */  
/* 1433:     */  static native void nglFogf(int paramInt, float paramFloat, long paramLong);
/* 1434:     */  
/* 1435:1435 */  public static void glFogi(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1436:1436 */    long function_pointer = caps.glFogi;
/* 1437:1437 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1438:1438 */    nglFogi(pname, param, function_pointer);
/* 1439:     */  }
/* 1440:     */  
/* 1441:     */  static native void nglFogi(int paramInt1, int paramInt2, long paramLong);
/* 1442:     */  
/* 1443:1443 */  public static void glFog(int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1444:1444 */    long function_pointer = caps.glFogfv;
/* 1445:1445 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1446:1446 */    BufferChecks.checkBuffer(params, 4);
/* 1447:1447 */    nglFogfv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1448:     */  }
/* 1449:     */  
/* 1450:     */  static native void nglFogfv(int paramInt, long paramLong1, long paramLong2);
/* 1451:     */  
/* 1452:1452 */  public static void glFog(int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1453:1453 */    long function_pointer = caps.glFogiv;
/* 1454:1454 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1455:1455 */    BufferChecks.checkBuffer(params, 4);
/* 1456:1456 */    nglFogiv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 1457:     */  }
/* 1458:     */  
/* 1459:     */  static native void nglFogiv(int paramInt, long paramLong1, long paramLong2);
/* 1460:     */  
/* 1461:1461 */  public static void glFlush() { ContextCapabilities caps = GLContext.getCapabilities();
/* 1462:1462 */    long function_pointer = caps.glFlush;
/* 1463:1463 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1464:1464 */    nglFlush(function_pointer);
/* 1465:     */  }
/* 1466:     */  
/* 1467:     */  static native void nglFlush(long paramLong);
/* 1468:     */  
/* 1469:1469 */  public static void glFinish() { ContextCapabilities caps = GLContext.getCapabilities();
/* 1470:1470 */    long function_pointer = caps.glFinish;
/* 1471:1471 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1472:1472 */    nglFinish(function_pointer);
/* 1473:     */  }
/* 1474:     */  
/* 1475:     */  static native void nglFinish(long paramLong);
/* 1476:     */  
/* 1477:1477 */  public static ByteBuffer glGetPointer(int pname, long result_size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1478:1478 */    long function_pointer = caps.glGetPointerv;
/* 1479:1479 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1480:1480 */    ByteBuffer __result = nglGetPointerv(pname, result_size, function_pointer);
/* 1481:1481 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 1482:     */  }
/* 1483:     */  
/* 1484:     */  static native ByteBuffer nglGetPointerv(int paramInt, long paramLong1, long paramLong2);
/* 1485:     */  
/* 1486:1486 */  public static boolean glIsEnabled(int cap) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1487:1487 */    long function_pointer = caps.glIsEnabled;
/* 1488:1488 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1489:1489 */    boolean __result = nglIsEnabled(cap, function_pointer);
/* 1490:1490 */    return __result;
/* 1491:     */  }
/* 1492:     */  
/* 1493:     */  static native boolean nglIsEnabled(int paramInt, long paramLong);
/* 1494:     */  
/* 1495:1495 */  public static void glInterleavedArrays(int format, int stride, ByteBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1496:1496 */    long function_pointer = caps.glInterleavedArrays;
/* 1497:1497 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1498:1498 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 1499:1499 */    BufferChecks.checkDirect(pointer);
/* 1500:1500 */    nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 1501:     */  }
/* 1502:     */  
/* 1503:1503 */  public static void glInterleavedArrays(int format, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1504:1504 */    long function_pointer = caps.glInterleavedArrays;
/* 1505:1505 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1506:1506 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 1507:1507 */    BufferChecks.checkDirect(pointer);
/* 1508:1508 */    nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 1509:     */  }
/* 1510:     */  
/* 1511:1511 */  public static void glInterleavedArrays(int format, int stride, FloatBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1512:1512 */    long function_pointer = caps.glInterleavedArrays;
/* 1513:1513 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1514:1514 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 1515:1515 */    BufferChecks.checkDirect(pointer);
/* 1516:1516 */    nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 1517:     */  }
/* 1518:     */  
/* 1519:1519 */  public static void glInterleavedArrays(int format, int stride, IntBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1520:1520 */    long function_pointer = caps.glInterleavedArrays;
/* 1521:1521 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1522:1522 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 1523:1523 */    BufferChecks.checkDirect(pointer);
/* 1524:1524 */    nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 1525:     */  }
/* 1526:     */  
/* 1527:1527 */  public static void glInterleavedArrays(int format, int stride, ShortBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1528:1528 */    long function_pointer = caps.glInterleavedArrays;
/* 1529:1529 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1530:1530 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 1531:1531 */    BufferChecks.checkDirect(pointer);
/* 1532:1532 */    nglInterleavedArrays(format, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/* 1533:     */  
/* 1534:     */  static native void nglInterleavedArrays(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1535:     */  
/* 1536:1536 */  public static void glInterleavedArrays(int format, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1537:1537 */    long function_pointer = caps.glInterleavedArrays;
/* 1538:1538 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1539:1539 */    GLChecks.ensureArrayVBOenabled(caps);
/* 1540:1540 */    nglInterleavedArraysBO(format, stride, pointer_buffer_offset, function_pointer);
/* 1541:     */  }
/* 1542:     */  
/* 1543:     */  static native void nglInterleavedArraysBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1544:     */  
/* 1545:1545 */  public static void glInitNames() { ContextCapabilities caps = GLContext.getCapabilities();
/* 1546:1546 */    long function_pointer = caps.glInitNames;
/* 1547:1547 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1548:1548 */    nglInitNames(function_pointer);
/* 1549:     */  }
/* 1550:     */  
/* 1551:     */  static native void nglInitNames(long paramLong);
/* 1552:     */  
/* 1553:1553 */  public static void glHint(int target, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1554:1554 */    long function_pointer = caps.glHint;
/* 1555:1555 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1556:1556 */    nglHint(target, mode, function_pointer);
/* 1557:     */  }
/* 1558:     */  
/* 1559:     */  static native void nglHint(int paramInt1, int paramInt2, long paramLong);
/* 1560:     */  
/* 1561:1561 */  public static void glGetTexParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1562:1562 */    long function_pointer = caps.glGetTexParameterfv;
/* 1563:1563 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1564:1564 */    BufferChecks.checkBuffer(params, 4);
/* 1565:1565 */    nglGetTexParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1566:     */  }
/* 1567:     */  
/* 1568:     */  static native void nglGetTexParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1569:     */  
/* 1570:     */  public static float glGetTexParameterf(int target, int pname) {
/* 1571:1571 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1572:1572 */    long function_pointer = caps.glGetTexParameterfv;
/* 1573:1573 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1574:1574 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1575:1575 */    nglGetTexParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1576:1576 */    return params.get(0);
/* 1577:     */  }
/* 1578:     */  
/* 1579:     */  public static void glGetTexParameter(int target, int pname, IntBuffer params) {
/* 1580:1580 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1581:1581 */    long function_pointer = caps.glGetTexParameteriv;
/* 1582:1582 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1583:1583 */    BufferChecks.checkBuffer(params, 4);
/* 1584:1584 */    nglGetTexParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1585:     */  }
/* 1586:     */  
/* 1587:     */  static native void nglGetTexParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1588:     */  
/* 1589:     */  public static int glGetTexParameteri(int target, int pname) {
/* 1590:1590 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1591:1591 */    long function_pointer = caps.glGetTexParameteriv;
/* 1592:1592 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1593:1593 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1594:1594 */    nglGetTexParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1595:1595 */    return params.get(0);
/* 1596:     */  }
/* 1597:     */  
/* 1598:     */  public static void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
/* 1599:1599 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1600:1600 */    long function_pointer = caps.glGetTexLevelParameterfv;
/* 1601:1601 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1602:1602 */    BufferChecks.checkBuffer(params, 4);
/* 1603:1603 */    nglGetTexLevelParameterfv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1604:     */  }
/* 1605:     */  
/* 1606:     */  static native void nglGetTexLevelParameterfv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1607:     */  
/* 1608:     */  public static float glGetTexLevelParameterf(int target, int level, int pname) {
/* 1609:1609 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1610:1610 */    long function_pointer = caps.glGetTexLevelParameterfv;
/* 1611:1611 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1612:1612 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1613:1613 */    nglGetTexLevelParameterfv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1614:1614 */    return params.get(0);
/* 1615:     */  }
/* 1616:     */  
/* 1617:     */  public static void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
/* 1618:1618 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1619:1619 */    long function_pointer = caps.glGetTexLevelParameteriv;
/* 1620:1620 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1621:1621 */    BufferChecks.checkBuffer(params, 4);
/* 1622:1622 */    nglGetTexLevelParameteriv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1623:     */  }
/* 1624:     */  
/* 1625:     */  static native void nglGetTexLevelParameteriv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1626:     */  
/* 1627:     */  public static int glGetTexLevelParameteri(int target, int level, int pname) {
/* 1628:1628 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1629:1629 */    long function_pointer = caps.glGetTexLevelParameteriv;
/* 1630:1630 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1631:1631 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1632:1632 */    nglGetTexLevelParameteriv(target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1633:1633 */    return params.get(0);
/* 1634:     */  }
/* 1635:     */  
/* 1636:     */  public static void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
/* 1637:1637 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1638:1638 */    long function_pointer = caps.glGetTexImage;
/* 1639:1639 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1640:1640 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1641:1641 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1642:1642 */    nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1643:     */  }
/* 1644:     */  
/* 1645:1645 */  public static void glGetTexImage(int target, int level, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1646:1646 */    long function_pointer = caps.glGetTexImage;
/* 1647:1647 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1648:1648 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1649:1649 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1650:1650 */    nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1651:     */  }
/* 1652:     */  
/* 1653:1653 */  public static void glGetTexImage(int target, int level, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1654:1654 */    long function_pointer = caps.glGetTexImage;
/* 1655:1655 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1656:1656 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1657:1657 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1658:1658 */    nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1659:     */  }
/* 1660:     */  
/* 1661:1661 */  public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1662:1662 */    long function_pointer = caps.glGetTexImage;
/* 1663:1663 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1664:1664 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1665:1665 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1666:1666 */    nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1667:     */  }
/* 1668:     */  
/* 1669:1669 */  public static void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1670:1670 */    long function_pointer = caps.glGetTexImage;
/* 1671:1671 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1672:1672 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1673:1673 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1674:1674 */    nglGetTexImage(target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 1675:     */  
/* 1676:     */  static native void nglGetTexImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1677:     */  
/* 1678:1678 */  public static void glGetTexImage(int target, int level, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1679:1679 */    long function_pointer = caps.glGetTexImage;
/* 1680:1680 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1681:1681 */    GLChecks.ensurePackPBOenabled(caps);
/* 1682:1682 */    nglGetTexImageBO(target, level, format, type, pixels_buffer_offset, function_pointer);
/* 1683:     */  }
/* 1684:     */  
/* 1685:     */  static native void nglGetTexImageBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1686:     */  
/* 1687:1687 */  public static void glGetTexGen(int coord, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1688:1688 */    long function_pointer = caps.glGetTexGeniv;
/* 1689:1689 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1690:1690 */    BufferChecks.checkBuffer(params, 4);
/* 1691:1691 */    nglGetTexGeniv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1692:     */  }
/* 1693:     */  
/* 1694:     */  static native void nglGetTexGeniv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1695:     */  
/* 1696:     */  public static int glGetTexGeni(int coord, int pname) {
/* 1697:1697 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1698:1698 */    long function_pointer = caps.glGetTexGeniv;
/* 1699:1699 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1700:1700 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1701:1701 */    nglGetTexGeniv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1702:1702 */    return params.get(0);
/* 1703:     */  }
/* 1704:     */  
/* 1705:     */  public static void glGetTexGen(int coord, int pname, FloatBuffer params) {
/* 1706:1706 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1707:1707 */    long function_pointer = caps.glGetTexGenfv;
/* 1708:1708 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1709:1709 */    BufferChecks.checkBuffer(params, 4);
/* 1710:1710 */    nglGetTexGenfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1711:     */  }
/* 1712:     */  
/* 1713:     */  static native void nglGetTexGenfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1714:     */  
/* 1715:     */  public static float glGetTexGenf(int coord, int pname) {
/* 1716:1716 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1717:1717 */    long function_pointer = caps.glGetTexGenfv;
/* 1718:1718 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1719:1719 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1720:1720 */    nglGetTexGenfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1721:1721 */    return params.get(0);
/* 1722:     */  }
/* 1723:     */  
/* 1724:     */  public static void glGetTexGen(int coord, int pname, DoubleBuffer params) {
/* 1725:1725 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1726:1726 */    long function_pointer = caps.glGetTexGendv;
/* 1727:1727 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1728:1728 */    BufferChecks.checkBuffer(params, 4);
/* 1729:1729 */    nglGetTexGendv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1730:     */  }
/* 1731:     */  
/* 1732:     */  static native void nglGetTexGendv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1733:     */  
/* 1734:     */  public static double glGetTexGend(int coord, int pname) {
/* 1735:1735 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1736:1736 */    long function_pointer = caps.glGetTexGendv;
/* 1737:1737 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1738:1738 */    DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1739:1739 */    nglGetTexGendv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1740:1740 */    return params.get(0);
/* 1741:     */  }
/* 1742:     */  
/* 1743:     */  public static void glGetTexEnv(int coord, int pname, IntBuffer params) {
/* 1744:1744 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1745:1745 */    long function_pointer = caps.glGetTexEnviv;
/* 1746:1746 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1747:1747 */    BufferChecks.checkBuffer(params, 4);
/* 1748:1748 */    nglGetTexEnviv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1749:     */  }
/* 1750:     */  
/* 1751:     */  static native void nglGetTexEnviv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1752:     */  
/* 1753:     */  public static int glGetTexEnvi(int coord, int pname) {
/* 1754:1754 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1755:1755 */    long function_pointer = caps.glGetTexEnviv;
/* 1756:1756 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1757:1757 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1758:1758 */    nglGetTexEnviv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1759:1759 */    return params.get(0);
/* 1760:     */  }
/* 1761:     */  
/* 1762:     */  public static void glGetTexEnv(int coord, int pname, FloatBuffer params) {
/* 1763:1763 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1764:1764 */    long function_pointer = caps.glGetTexEnvfv;
/* 1765:1765 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1766:1766 */    BufferChecks.checkBuffer(params, 4);
/* 1767:1767 */    nglGetTexEnvfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1768:     */  }
/* 1769:     */  
/* 1770:     */  static native void nglGetTexEnvfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1771:     */  
/* 1772:     */  public static float glGetTexEnvf(int coord, int pname) {
/* 1773:1773 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1774:1774 */    long function_pointer = caps.glGetTexEnvfv;
/* 1775:1775 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1776:1776 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1777:1777 */    nglGetTexEnvfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1778:1778 */    return params.get(0);
/* 1779:     */  }
/* 1780:     */  
/* 1781:     */  public static String glGetString(int name) {
/* 1782:1782 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1783:1783 */    long function_pointer = caps.glGetString;
/* 1784:1784 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1785:1785 */    String __result = nglGetString(name, function_pointer);
/* 1786:1786 */    return __result;
/* 1787:     */  }
/* 1788:     */  
/* 1789:     */  static native String nglGetString(int paramInt, long paramLong);
/* 1790:     */  
/* 1791:1791 */  public static void glGetPolygonStipple(ByteBuffer mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1792:1792 */    long function_pointer = caps.glGetPolygonStipple;
/* 1793:1793 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1794:1794 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1795:1795 */    BufferChecks.checkBuffer(mask, 128);
/* 1796:1796 */    nglGetPolygonStipple(MemoryUtil.getAddress(mask), function_pointer); }
/* 1797:     */  
/* 1798:     */  static native void nglGetPolygonStipple(long paramLong1, long paramLong2);
/* 1799:     */  
/* 1800:1800 */  public static void glGetPolygonStipple(long mask_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1801:1801 */    long function_pointer = caps.glGetPolygonStipple;
/* 1802:1802 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1803:1803 */    GLChecks.ensurePackPBOenabled(caps);
/* 1804:1804 */    nglGetPolygonStippleBO(mask_buffer_offset, function_pointer);
/* 1805:     */  }
/* 1806:     */  
/* 1807:     */  static native void nglGetPolygonStippleBO(long paramLong1, long paramLong2);
/* 1808:     */  
/* 1809:1809 */  public static boolean glIsList(int list) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1810:1810 */    long function_pointer = caps.glIsList;
/* 1811:1811 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1812:1812 */    boolean __result = nglIsList(list, function_pointer);
/* 1813:1813 */    return __result;
/* 1814:     */  }
/* 1815:     */  
/* 1816:     */  static native boolean nglIsList(int paramInt, long paramLong);
/* 1817:     */  
/* 1818:1818 */  public static void glMaterialf(int face, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1819:1819 */    long function_pointer = caps.glMaterialf;
/* 1820:1820 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1821:1821 */    nglMaterialf(face, pname, param, function_pointer);
/* 1822:     */  }
/* 1823:     */  
/* 1824:     */  static native void nglMaterialf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 1825:     */  
/* 1826:1826 */  public static void glMateriali(int face, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1827:1827 */    long function_pointer = caps.glMateriali;
/* 1828:1828 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1829:1829 */    nglMateriali(face, pname, param, function_pointer);
/* 1830:     */  }
/* 1831:     */  
/* 1832:     */  static native void nglMateriali(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 1833:     */  
/* 1834:1834 */  public static void glMaterial(int face, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1835:1835 */    long function_pointer = caps.glMaterialfv;
/* 1836:1836 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1837:1837 */    BufferChecks.checkBuffer(params, 4);
/* 1838:1838 */    nglMaterialfv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1839:     */  }
/* 1840:     */  
/* 1841:     */  static native void nglMaterialfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1842:     */  
/* 1843:1843 */  public static void glMaterial(int face, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1844:1844 */    long function_pointer = caps.glMaterialiv;
/* 1845:1845 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1846:1846 */    BufferChecks.checkBuffer(params, 4);
/* 1847:1847 */    nglMaterialiv(face, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1848:     */  }
/* 1849:     */  
/* 1850:     */  static native void nglMaterialiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1851:     */  
/* 1852:1852 */  public static void glMapGrid1f(int un, float u1, float u2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1853:1853 */    long function_pointer = caps.glMapGrid1f;
/* 1854:1854 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1855:1855 */    nglMapGrid1f(un, u1, u2, function_pointer);
/* 1856:     */  }
/* 1857:     */  
/* 1858:     */  static native void nglMapGrid1f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 1859:     */  
/* 1860:1860 */  public static void glMapGrid1d(int un, double u1, double u2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1861:1861 */    long function_pointer = caps.glMapGrid1d;
/* 1862:1862 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1863:1863 */    nglMapGrid1d(un, u1, u2, function_pointer);
/* 1864:     */  }
/* 1865:     */  
/* 1866:     */  static native void nglMapGrid1d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/* 1867:     */  
/* 1868:1868 */  public static void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1869:1869 */    long function_pointer = caps.glMapGrid2f;
/* 1870:1870 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1871:1871 */    nglMapGrid2f(un, u1, u2, vn, v1, v2, function_pointer);
/* 1872:     */  }
/* 1873:     */  
/* 1874:     */  static native void nglMapGrid2f(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, float paramFloat3, float paramFloat4, long paramLong);
/* 1875:     */  
/* 1876:1876 */  public static void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1877:1877 */    long function_pointer = caps.glMapGrid2d;
/* 1878:1878 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1879:1879 */    nglMapGrid2d(un, u1, u2, vn, v1, v2, function_pointer);
/* 1880:     */  }
/* 1881:     */  
/* 1882:     */  static native void nglMapGrid2d(int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, double paramDouble3, double paramDouble4, long paramLong);
/* 1883:     */  
/* 1884:1884 */  public static void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1885:1885 */    long function_pointer = caps.glMap2f;
/* 1886:1886 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1887:1887 */    BufferChecks.checkDirect(points);
/* 1888:1888 */    nglMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
/* 1889:     */  }
/* 1890:     */  
/* 1891:     */  static native void nglMap2f(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, float paramFloat3, float paramFloat4, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 1892:     */  
/* 1893:1893 */  public static void glMap2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1894:1894 */    long function_pointer = caps.glMap2d;
/* 1895:1895 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1896:1896 */    BufferChecks.checkDirect(points);
/* 1897:1897 */    nglMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
/* 1898:     */  }
/* 1899:     */  
/* 1900:     */  static native void nglMap2d(int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3, double paramDouble3, double paramDouble4, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 1901:     */  
/* 1902:1902 */  public static void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1903:1903 */    long function_pointer = caps.glMap1f;
/* 1904:1904 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1905:1905 */    BufferChecks.checkDirect(points);
/* 1906:1906 */    nglMap1f(target, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
/* 1907:     */  }
/* 1908:     */  
/* 1909:     */  static native void nglMap1f(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1910:     */  
/* 1911:1911 */  public static void glMap1d(int target, double u1, double u2, int stride, int order, DoubleBuffer points) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1912:1912 */    long function_pointer = caps.glMap1d;
/* 1913:1913 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1914:1914 */    BufferChecks.checkDirect(points);
/* 1915:1915 */    nglMap1d(target, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
/* 1916:     */  }
/* 1917:     */  
/* 1918:     */  static native void nglMap1d(int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1919:     */  
/* 1920:1920 */  public static void glLogicOp(int opcode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1921:1921 */    long function_pointer = caps.glLogicOp;
/* 1922:1922 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1923:1923 */    nglLogicOp(opcode, function_pointer);
/* 1924:     */  }
/* 1925:     */  
/* 1926:     */  static native void nglLogicOp(int paramInt, long paramLong);
/* 1927:     */  
/* 1928:1928 */  public static void glLoadName(int name) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1929:1929 */    long function_pointer = caps.glLoadName;
/* 1930:1930 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1931:1931 */    nglLoadName(name, function_pointer);
/* 1932:     */  }
/* 1933:     */  
/* 1934:     */  static native void nglLoadName(int paramInt, long paramLong);
/* 1935:     */  
/* 1936:1936 */  public static void glLoadMatrix(FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1937:1937 */    long function_pointer = caps.glLoadMatrixf;
/* 1938:1938 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1939:1939 */    BufferChecks.checkBuffer(m, 16);
/* 1940:1940 */    nglLoadMatrixf(MemoryUtil.getAddress(m), function_pointer);
/* 1941:     */  }
/* 1942:     */  
/* 1943:     */  static native void nglLoadMatrixf(long paramLong1, long paramLong2);
/* 1944:     */  
/* 1945:1945 */  public static void glLoadMatrix(DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1946:1946 */    long function_pointer = caps.glLoadMatrixd;
/* 1947:1947 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1948:1948 */    BufferChecks.checkBuffer(m, 16);
/* 1949:1949 */    nglLoadMatrixd(MemoryUtil.getAddress(m), function_pointer);
/* 1950:     */  }
/* 1951:     */  
/* 1952:     */  static native void nglLoadMatrixd(long paramLong1, long paramLong2);
/* 1953:     */  
/* 1954:1954 */  public static void glLoadIdentity() { ContextCapabilities caps = GLContext.getCapabilities();
/* 1955:1955 */    long function_pointer = caps.glLoadIdentity;
/* 1956:1956 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1957:1957 */    nglLoadIdentity(function_pointer);
/* 1958:     */  }
/* 1959:     */  
/* 1960:     */  static native void nglLoadIdentity(long paramLong);
/* 1961:     */  
/* 1962:1962 */  public static void glListBase(int base) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1963:1963 */    long function_pointer = caps.glListBase;
/* 1964:1964 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1965:1965 */    nglListBase(base, function_pointer);
/* 1966:     */  }
/* 1967:     */  
/* 1968:     */  static native void nglListBase(int paramInt, long paramLong);
/* 1969:     */  
/* 1970:1970 */  public static void glLineWidth(float width) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1971:1971 */    long function_pointer = caps.glLineWidth;
/* 1972:1972 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1973:1973 */    nglLineWidth(width, function_pointer);
/* 1974:     */  }
/* 1975:     */  
/* 1976:     */  static native void nglLineWidth(float paramFloat, long paramLong);
/* 1977:     */  
/* 1978:1978 */  public static void glLineStipple(int factor, short pattern) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1979:1979 */    long function_pointer = caps.glLineStipple;
/* 1980:1980 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1981:1981 */    nglLineStipple(factor, pattern, function_pointer);
/* 1982:     */  }
/* 1983:     */  
/* 1984:     */  static native void nglLineStipple(int paramInt, short paramShort, long paramLong);
/* 1985:     */  
/* 1986:1986 */  public static void glLightModelf(int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1987:1987 */    long function_pointer = caps.glLightModelf;
/* 1988:1988 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1989:1989 */    nglLightModelf(pname, param, function_pointer);
/* 1990:     */  }
/* 1991:     */  
/* 1992:     */  static native void nglLightModelf(int paramInt, float paramFloat, long paramLong);
/* 1993:     */  
/* 1994:1994 */  public static void glLightModeli(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1995:1995 */    long function_pointer = caps.glLightModeli;
/* 1996:1996 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1997:1997 */    nglLightModeli(pname, param, function_pointer);
/* 1998:     */  }
/* 1999:     */  
/* 2000:     */  static native void nglLightModeli(int paramInt1, int paramInt2, long paramLong);
/* 2001:     */  
/* 2002:2002 */  public static void glLightModel(int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2003:2003 */    long function_pointer = caps.glLightModelfv;
/* 2004:2004 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2005:2005 */    BufferChecks.checkBuffer(params, 4);
/* 2006:2006 */    nglLightModelfv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 2007:     */  }
/* 2008:     */  
/* 2009:     */  static native void nglLightModelfv(int paramInt, long paramLong1, long paramLong2);
/* 2010:     */  
/* 2011:2011 */  public static void glLightModel(int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2012:2012 */    long function_pointer = caps.glLightModeliv;
/* 2013:2013 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2014:2014 */    BufferChecks.checkBuffer(params, 4);
/* 2015:2015 */    nglLightModeliv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 2016:     */  }
/* 2017:     */  
/* 2018:     */  static native void nglLightModeliv(int paramInt, long paramLong1, long paramLong2);
/* 2019:     */  
/* 2020:2020 */  public static void glLightf(int light, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2021:2021 */    long function_pointer = caps.glLightf;
/* 2022:2022 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2023:2023 */    nglLightf(light, pname, param, function_pointer);
/* 2024:     */  }
/* 2025:     */  
/* 2026:     */  static native void nglLightf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 2027:     */  
/* 2028:2028 */  public static void glLighti(int light, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2029:2029 */    long function_pointer = caps.glLighti;
/* 2030:2030 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2031:2031 */    nglLighti(light, pname, param, function_pointer);
/* 2032:     */  }
/* 2033:     */  
/* 2034:     */  static native void nglLighti(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2035:     */  
/* 2036:2036 */  public static void glLight(int light, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2037:2037 */    long function_pointer = caps.glLightfv;
/* 2038:2038 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2039:2039 */    BufferChecks.checkBuffer(params, 4);
/* 2040:2040 */    nglLightfv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2041:     */  }
/* 2042:     */  
/* 2043:     */  static native void nglLightfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2044:     */  
/* 2045:2045 */  public static void glLight(int light, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2046:2046 */    long function_pointer = caps.glLightiv;
/* 2047:2047 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2048:2048 */    BufferChecks.checkBuffer(params, 4);
/* 2049:2049 */    nglLightiv(light, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2050:     */  }
/* 2051:     */  
/* 2052:     */  static native void nglLightiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2053:     */  
/* 2054:2054 */  public static boolean glIsTexture(int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2055:2055 */    long function_pointer = caps.glIsTexture;
/* 2056:2056 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2057:2057 */    boolean __result = nglIsTexture(texture, function_pointer);
/* 2058:2058 */    return __result;
/* 2059:     */  }
/* 2060:     */  
/* 2061:     */  static native boolean nglIsTexture(int paramInt, long paramLong);
/* 2062:     */  
/* 2063:2063 */  public static void glMatrixMode(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2064:2064 */    long function_pointer = caps.glMatrixMode;
/* 2065:2065 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2066:2066 */    nglMatrixMode(mode, function_pointer);
/* 2067:     */  }
/* 2068:     */  
/* 2069:     */  static native void nglMatrixMode(int paramInt, long paramLong);
/* 2070:     */  
/* 2071:2071 */  public static void glPolygonStipple(ByteBuffer mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2072:2072 */    long function_pointer = caps.glPolygonStipple;
/* 2073:2073 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2074:2074 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2075:2075 */    BufferChecks.checkBuffer(mask, 128);
/* 2076:2076 */    nglPolygonStipple(MemoryUtil.getAddress(mask), function_pointer); }
/* 2077:     */  
/* 2078:     */  static native void nglPolygonStipple(long paramLong1, long paramLong2);
/* 2079:     */  
/* 2080:2080 */  public static void glPolygonStipple(long mask_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2081:2081 */    long function_pointer = caps.glPolygonStipple;
/* 2082:2082 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2083:2083 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2084:2084 */    nglPolygonStippleBO(mask_buffer_offset, function_pointer);
/* 2085:     */  }
/* 2086:     */  
/* 2087:     */  static native void nglPolygonStippleBO(long paramLong1, long paramLong2);
/* 2088:     */  
/* 2089:2089 */  public static void glPolygonOffset(float factor, float units) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2090:2090 */    long function_pointer = caps.glPolygonOffset;
/* 2091:2091 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2092:2092 */    nglPolygonOffset(factor, units, function_pointer);
/* 2093:     */  }
/* 2094:     */  
/* 2095:     */  static native void nglPolygonOffset(float paramFloat1, float paramFloat2, long paramLong);
/* 2096:     */  
/* 2097:2097 */  public static void glPolygonMode(int face, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2098:2098 */    long function_pointer = caps.glPolygonMode;
/* 2099:2099 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2100:2100 */    nglPolygonMode(face, mode, function_pointer);
/* 2101:     */  }
/* 2102:     */  
/* 2103:     */  static native void nglPolygonMode(int paramInt1, int paramInt2, long paramLong);
/* 2104:     */  
/* 2105:2105 */  public static void glPointSize(float size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2106:2106 */    long function_pointer = caps.glPointSize;
/* 2107:2107 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2108:2108 */    nglPointSize(size, function_pointer);
/* 2109:     */  }
/* 2110:     */  
/* 2111:     */  static native void nglPointSize(float paramFloat, long paramLong);
/* 2112:     */  
/* 2113:2113 */  public static void glPixelZoom(float xfactor, float yfactor) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2114:2114 */    long function_pointer = caps.glPixelZoom;
/* 2115:2115 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2116:2116 */    nglPixelZoom(xfactor, yfactor, function_pointer);
/* 2117:     */  }
/* 2118:     */  
/* 2119:     */  static native void nglPixelZoom(float paramFloat1, float paramFloat2, long paramLong);
/* 2120:     */  
/* 2121:2121 */  public static void glPixelTransferf(int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2122:2122 */    long function_pointer = caps.glPixelTransferf;
/* 2123:2123 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2124:2124 */    nglPixelTransferf(pname, param, function_pointer);
/* 2125:     */  }
/* 2126:     */  
/* 2127:     */  static native void nglPixelTransferf(int paramInt, float paramFloat, long paramLong);
/* 2128:     */  
/* 2129:2129 */  public static void glPixelTransferi(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2130:2130 */    long function_pointer = caps.glPixelTransferi;
/* 2131:2131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2132:2132 */    nglPixelTransferi(pname, param, function_pointer);
/* 2133:     */  }
/* 2134:     */  
/* 2135:     */  static native void nglPixelTransferi(int paramInt1, int paramInt2, long paramLong);
/* 2136:     */  
/* 2137:2137 */  public static void glPixelStoref(int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2138:2138 */    long function_pointer = caps.glPixelStoref;
/* 2139:2139 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2140:2140 */    nglPixelStoref(pname, param, function_pointer);
/* 2141:     */  }
/* 2142:     */  
/* 2143:     */  static native void nglPixelStoref(int paramInt, float paramFloat, long paramLong);
/* 2144:     */  
/* 2145:2145 */  public static void glPixelStorei(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2146:2146 */    long function_pointer = caps.glPixelStorei;
/* 2147:2147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2148:2148 */    nglPixelStorei(pname, param, function_pointer);
/* 2149:     */  }
/* 2150:     */  
/* 2151:     */  static native void nglPixelStorei(int paramInt1, int paramInt2, long paramLong);
/* 2152:     */  
/* 2153:2153 */  public static void glPixelMap(int map, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2154:2154 */    long function_pointer = caps.glPixelMapfv;
/* 2155:2155 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2156:2156 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2157:2157 */    BufferChecks.checkDirect(values);
/* 2158:2158 */    nglPixelMapfv(map, values.remaining(), MemoryUtil.getAddress(values), function_pointer); }
/* 2159:     */  
/* 2160:     */  static native void nglPixelMapfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2161:     */  
/* 2162:2162 */  public static void glPixelMapfv(int map, int values_mapsize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2163:2163 */    long function_pointer = caps.glPixelMapfv;
/* 2164:2164 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2165:2165 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2166:2166 */    nglPixelMapfvBO(map, values_mapsize, values_buffer_offset, function_pointer);
/* 2167:     */  }
/* 2168:     */  
/* 2169:     */  static native void nglPixelMapfvBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2170:     */  
/* 2171:2171 */  public static void glPixelMapu(int map, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2172:2172 */    long function_pointer = caps.glPixelMapuiv;
/* 2173:2173 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2174:2174 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2175:2175 */    BufferChecks.checkDirect(values);
/* 2176:2176 */    nglPixelMapuiv(map, values.remaining(), MemoryUtil.getAddress(values), function_pointer); }
/* 2177:     */  
/* 2178:     */  static native void nglPixelMapuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2179:     */  
/* 2180:2180 */  public static void glPixelMapuiv(int map, int values_mapsize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2181:2181 */    long function_pointer = caps.glPixelMapuiv;
/* 2182:2182 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2183:2183 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2184:2184 */    nglPixelMapuivBO(map, values_mapsize, values_buffer_offset, function_pointer);
/* 2185:     */  }
/* 2186:     */  
/* 2187:     */  static native void nglPixelMapuivBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2188:     */  
/* 2189:2189 */  public static void glPixelMapu(int map, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2190:2190 */    long function_pointer = caps.glPixelMapusv;
/* 2191:2191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2192:2192 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2193:2193 */    BufferChecks.checkDirect(values);
/* 2194:2194 */    nglPixelMapusv(map, values.remaining(), MemoryUtil.getAddress(values), function_pointer); }
/* 2195:     */  
/* 2196:     */  static native void nglPixelMapusv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2197:     */  
/* 2198:2198 */  public static void glPixelMapusv(int map, int values_mapsize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2199:2199 */    long function_pointer = caps.glPixelMapusv;
/* 2200:2200 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2201:2201 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2202:2202 */    nglPixelMapusvBO(map, values_mapsize, values_buffer_offset, function_pointer);
/* 2203:     */  }
/* 2204:     */  
/* 2205:     */  static native void nglPixelMapusvBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2206:     */  
/* 2207:2207 */  public static void glPassThrough(float token) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2208:2208 */    long function_pointer = caps.glPassThrough;
/* 2209:2209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2210:2210 */    nglPassThrough(token, function_pointer);
/* 2211:     */  }
/* 2212:     */  
/* 2213:     */  static native void nglPassThrough(float paramFloat, long paramLong);
/* 2214:     */  
/* 2215:2215 */  public static void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2216:2216 */    long function_pointer = caps.glOrtho;
/* 2217:2217 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2218:2218 */    nglOrtho(left, right, bottom, top, zNear, zFar, function_pointer);
/* 2219:     */  }
/* 2220:     */  
/* 2221:     */  static native void nglOrtho(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/* 2222:     */  
/* 2223:2223 */  public static void glNormalPointer(int stride, ByteBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2224:2224 */    long function_pointer = caps.glNormalPointer;
/* 2225:2225 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2226:2226 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2227:2227 */    BufferChecks.checkDirect(pointer);
/* 2228:2228 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2229:2229 */    nglNormalPointer(5120, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2230:     */  }
/* 2231:     */  
/* 2232:2232 */  public static void glNormalPointer(int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2233:2233 */    long function_pointer = caps.glNormalPointer;
/* 2234:2234 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2235:2235 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2236:2236 */    BufferChecks.checkDirect(pointer);
/* 2237:2237 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2238:2238 */    nglNormalPointer(5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2239:     */  }
/* 2240:     */  
/* 2241:2241 */  public static void glNormalPointer(int stride, FloatBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2242:2242 */    long function_pointer = caps.glNormalPointer;
/* 2243:2243 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2244:2244 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2245:2245 */    BufferChecks.checkDirect(pointer);
/* 2246:2246 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2247:2247 */    nglNormalPointer(5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2248:     */  }
/* 2249:     */  
/* 2250:2250 */  public static void glNormalPointer(int stride, IntBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2251:2251 */    long function_pointer = caps.glNormalPointer;
/* 2252:2252 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2253:2253 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2254:2254 */    BufferChecks.checkDirect(pointer);
/* 2255:2255 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2256:2256 */    nglNormalPointer(5124, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/* 2257:     */  
/* 2258:     */  static native void nglNormalPointer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2259:     */  
/* 2260:2260 */  public static void glNormalPointer(int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2261:2261 */    long function_pointer = caps.glNormalPointer;
/* 2262:2262 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2263:2263 */    GLChecks.ensureArrayVBOenabled(caps);
/* 2264:2264 */    nglNormalPointerBO(type, stride, pointer_buffer_offset, function_pointer);
/* 2265:     */  }
/* 2266:     */  
/* 2267:     */  static native void nglNormalPointerBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2268:     */  
/* 2269:     */  public static void glNormalPointer(int type, int stride, ByteBuffer pointer) {
/* 2270:2270 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2271:2271 */    long function_pointer = caps.glNormalPointer;
/* 2272:2272 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2273:2273 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2274:2274 */    BufferChecks.checkDirect(pointer);
/* 2275:2275 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glNormalPointer_pointer = pointer;
/* 2276:2276 */    nglNormalPointer(type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2277:     */  }
/* 2278:     */  
/* 2279:     */  public static void glNormal3b(byte nx, byte ny, byte nz) {
/* 2280:2280 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2281:2281 */    long function_pointer = caps.glNormal3b;
/* 2282:2282 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2283:2283 */    nglNormal3b(nx, ny, nz, function_pointer);
/* 2284:     */  }
/* 2285:     */  
/* 2286:     */  static native void nglNormal3b(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/* 2287:     */  
/* 2288:2288 */  public static void glNormal3f(float nx, float ny, float nz) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2289:2289 */    long function_pointer = caps.glNormal3f;
/* 2290:2290 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2291:2291 */    nglNormal3f(nx, ny, nz, function_pointer);
/* 2292:     */  }
/* 2293:     */  
/* 2294:     */  static native void nglNormal3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 2295:     */  
/* 2296:2296 */  public static void glNormal3d(double nx, double ny, double nz) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2297:2297 */    long function_pointer = caps.glNormal3d;
/* 2298:2298 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2299:2299 */    nglNormal3d(nx, ny, nz, function_pointer);
/* 2300:     */  }
/* 2301:     */  
/* 2302:     */  static native void nglNormal3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 2303:     */  
/* 2304:2304 */  public static void glNormal3i(int nx, int ny, int nz) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2305:2305 */    long function_pointer = caps.glNormal3i;
/* 2306:2306 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2307:2307 */    nglNormal3i(nx, ny, nz, function_pointer);
/* 2308:     */  }
/* 2309:     */  
/* 2310:     */  static native void nglNormal3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2311:     */  
/* 2312:2312 */  public static void glNewList(int list, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2313:2313 */    long function_pointer = caps.glNewList;
/* 2314:2314 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2315:2315 */    nglNewList(list, mode, function_pointer);
/* 2316:     */  }
/* 2317:     */  
/* 2318:     */  static native void nglNewList(int paramInt1, int paramInt2, long paramLong);
/* 2319:     */  
/* 2320:2320 */  public static void glEndList() { ContextCapabilities caps = GLContext.getCapabilities();
/* 2321:2321 */    long function_pointer = caps.glEndList;
/* 2322:2322 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2323:2323 */    nglEndList(function_pointer);
/* 2324:     */  }
/* 2325:     */  
/* 2326:     */  static native void nglEndList(long paramLong);
/* 2327:     */  
/* 2328:2328 */  public static void glMultMatrix(FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2329:2329 */    long function_pointer = caps.glMultMatrixf;
/* 2330:2330 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2331:2331 */    BufferChecks.checkBuffer(m, 16);
/* 2332:2332 */    nglMultMatrixf(MemoryUtil.getAddress(m), function_pointer);
/* 2333:     */  }
/* 2334:     */  
/* 2335:     */  static native void nglMultMatrixf(long paramLong1, long paramLong2);
/* 2336:     */  
/* 2337:2337 */  public static void glMultMatrix(DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2338:2338 */    long function_pointer = caps.glMultMatrixd;
/* 2339:2339 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2340:2340 */    BufferChecks.checkBuffer(m, 16);
/* 2341:2341 */    nglMultMatrixd(MemoryUtil.getAddress(m), function_pointer);
/* 2342:     */  }
/* 2343:     */  
/* 2344:     */  static native void nglMultMatrixd(long paramLong1, long paramLong2);
/* 2345:     */  
/* 2346:2346 */  public static void glShadeModel(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2347:2347 */    long function_pointer = caps.glShadeModel;
/* 2348:2348 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2349:2349 */    nglShadeModel(mode, function_pointer);
/* 2350:     */  }
/* 2351:     */  
/* 2352:     */  static native void nglShadeModel(int paramInt, long paramLong);
/* 2353:     */  
/* 2354:2354 */  public static void glSelectBuffer(IntBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2355:2355 */    long function_pointer = caps.glSelectBuffer;
/* 2356:2356 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2357:2357 */    BufferChecks.checkDirect(buffer);
/* 2358:2358 */    nglSelectBuffer(buffer.remaining(), MemoryUtil.getAddress(buffer), function_pointer);
/* 2359:     */  }
/* 2360:     */  
/* 2361:     */  static native void nglSelectBuffer(int paramInt, long paramLong1, long paramLong2);
/* 2362:     */  
/* 2363:2363 */  public static void glScissor(int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2364:2364 */    long function_pointer = caps.glScissor;
/* 2365:2365 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2366:2366 */    nglScissor(x, y, width, height, function_pointer);
/* 2367:     */  }
/* 2368:     */  
/* 2369:     */  static native void nglScissor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2370:     */  
/* 2371:2371 */  public static void glScalef(float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2372:2372 */    long function_pointer = caps.glScalef;
/* 2373:2373 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2374:2374 */    nglScalef(x, y, z, function_pointer);
/* 2375:     */  }
/* 2376:     */  
/* 2377:     */  static native void nglScalef(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 2378:     */  
/* 2379:2379 */  public static void glScaled(double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2380:2380 */    long function_pointer = caps.glScaled;
/* 2381:2381 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2382:2382 */    nglScaled(x, y, z, function_pointer);
/* 2383:     */  }
/* 2384:     */  
/* 2385:     */  static native void nglScaled(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 2386:     */  
/* 2387:2387 */  public static void glRotatef(float angle, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2388:2388 */    long function_pointer = caps.glRotatef;
/* 2389:2389 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2390:2390 */    nglRotatef(angle, x, y, z, function_pointer);
/* 2391:     */  }
/* 2392:     */  
/* 2393:     */  static native void nglRotatef(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 2394:     */  
/* 2395:2395 */  public static void glRotated(double angle, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2396:2396 */    long function_pointer = caps.glRotated;
/* 2397:2397 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2398:2398 */    nglRotated(angle, x, y, z, function_pointer);
/* 2399:     */  }
/* 2400:     */  
/* 2401:     */  static native void nglRotated(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 2402:     */  
/* 2403:2403 */  public static int glRenderMode(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2404:2404 */    long function_pointer = caps.glRenderMode;
/* 2405:2405 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2406:2406 */    int __result = nglRenderMode(mode, function_pointer);
/* 2407:2407 */    return __result;
/* 2408:     */  }
/* 2409:     */  
/* 2410:     */  static native int nglRenderMode(int paramInt, long paramLong);
/* 2411:     */  
/* 2412:2412 */  public static void glRectf(float x1, float y1, float x2, float y2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2413:2413 */    long function_pointer = caps.glRectf;
/* 2414:2414 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2415:2415 */    nglRectf(x1, y1, x2, y2, function_pointer);
/* 2416:     */  }
/* 2417:     */  
/* 2418:     */  static native void nglRectf(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 2419:     */  
/* 2420:2420 */  public static void glRectd(double x1, double y1, double x2, double y2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2421:2421 */    long function_pointer = caps.glRectd;
/* 2422:2422 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2423:2423 */    nglRectd(x1, y1, x2, y2, function_pointer);
/* 2424:     */  }
/* 2425:     */  
/* 2426:     */  static native void nglRectd(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 2427:     */  
/* 2428:2428 */  public static void glRecti(int x1, int y1, int x2, int y2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2429:2429 */    long function_pointer = caps.glRecti;
/* 2430:2430 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2431:2431 */    nglRecti(x1, y1, x2, y2, function_pointer);
/* 2432:     */  }
/* 2433:     */  
/* 2434:     */  static native void nglRecti(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2435:     */  
/* 2436:2436 */  public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2437:2437 */    long function_pointer = caps.glReadPixels;
/* 2438:2438 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2439:2439 */    GLChecks.ensurePackPBOdisabled(caps);
/* 2440:2440 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2441:2441 */    nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2442:     */  }
/* 2443:     */  
/* 2444:2444 */  public static void glReadPixels(int x, int y, int width, int height, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2445:2445 */    long function_pointer = caps.glReadPixels;
/* 2446:2446 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2447:2447 */    GLChecks.ensurePackPBOdisabled(caps);
/* 2448:2448 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2449:2449 */    nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2450:     */  }
/* 2451:     */  
/* 2452:2452 */  public static void glReadPixels(int x, int y, int width, int height, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2453:2453 */    long function_pointer = caps.glReadPixels;
/* 2454:2454 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2455:2455 */    GLChecks.ensurePackPBOdisabled(caps);
/* 2456:2456 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2457:2457 */    nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2458:     */  }
/* 2459:     */  
/* 2460:2460 */  public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2461:2461 */    long function_pointer = caps.glReadPixels;
/* 2462:2462 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2463:2463 */    GLChecks.ensurePackPBOdisabled(caps);
/* 2464:2464 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2465:2465 */    nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2466:     */  }
/* 2467:     */  
/* 2468:2468 */  public static void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2469:2469 */    long function_pointer = caps.glReadPixels;
/* 2470:2470 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2471:2471 */    GLChecks.ensurePackPBOdisabled(caps);
/* 2472:2472 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2473:2473 */    nglReadPixels(x, y, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 2474:     */  
/* 2475:     */  static native void nglReadPixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 2476:     */  
/* 2477:2477 */  public static void glReadPixels(int x, int y, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2478:2478 */    long function_pointer = caps.glReadPixels;
/* 2479:2479 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2480:2480 */    GLChecks.ensurePackPBOenabled(caps);
/* 2481:2481 */    nglReadPixelsBO(x, y, width, height, format, type, pixels_buffer_offset, function_pointer);
/* 2482:     */  }
/* 2483:     */  
/* 2484:     */  static native void nglReadPixelsBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 2485:     */  
/* 2486:2486 */  public static void glReadBuffer(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2487:2487 */    long function_pointer = caps.glReadBuffer;
/* 2488:2488 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2489:2489 */    nglReadBuffer(mode, function_pointer);
/* 2490:     */  }
/* 2491:     */  
/* 2492:     */  static native void nglReadBuffer(int paramInt, long paramLong);
/* 2493:     */  
/* 2494:2494 */  public static void glRasterPos2f(float x, float y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2495:2495 */    long function_pointer = caps.glRasterPos2f;
/* 2496:2496 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2497:2497 */    nglRasterPos2f(x, y, function_pointer);
/* 2498:     */  }
/* 2499:     */  
/* 2500:     */  static native void nglRasterPos2f(float paramFloat1, float paramFloat2, long paramLong);
/* 2501:     */  
/* 2502:2502 */  public static void glRasterPos2d(double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2503:2503 */    long function_pointer = caps.glRasterPos2d;
/* 2504:2504 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2505:2505 */    nglRasterPos2d(x, y, function_pointer);
/* 2506:     */  }
/* 2507:     */  
/* 2508:     */  static native void nglRasterPos2d(double paramDouble1, double paramDouble2, long paramLong);
/* 2509:     */  
/* 2510:2510 */  public static void glRasterPos2i(int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2511:2511 */    long function_pointer = caps.glRasterPos2i;
/* 2512:2512 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2513:2513 */    nglRasterPos2i(x, y, function_pointer);
/* 2514:     */  }
/* 2515:     */  
/* 2516:     */  static native void nglRasterPos2i(int paramInt1, int paramInt2, long paramLong);
/* 2517:     */  
/* 2518:2518 */  public static void glRasterPos3f(float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2519:2519 */    long function_pointer = caps.glRasterPos3f;
/* 2520:2520 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2521:2521 */    nglRasterPos3f(x, y, z, function_pointer);
/* 2522:     */  }
/* 2523:     */  
/* 2524:     */  static native void nglRasterPos3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 2525:     */  
/* 2526:2526 */  public static void glRasterPos3d(double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2527:2527 */    long function_pointer = caps.glRasterPos3d;
/* 2528:2528 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2529:2529 */    nglRasterPos3d(x, y, z, function_pointer);
/* 2530:     */  }
/* 2531:     */  
/* 2532:     */  static native void nglRasterPos3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 2533:     */  
/* 2534:2534 */  public static void glRasterPos3i(int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2535:2535 */    long function_pointer = caps.glRasterPos3i;
/* 2536:2536 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2537:2537 */    nglRasterPos3i(x, y, z, function_pointer);
/* 2538:     */  }
/* 2539:     */  
/* 2540:     */  static native void nglRasterPos3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2541:     */  
/* 2542:2542 */  public static void glRasterPos4f(float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2543:2543 */    long function_pointer = caps.glRasterPos4f;
/* 2544:2544 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2545:2545 */    nglRasterPos4f(x, y, z, w, function_pointer);
/* 2546:     */  }
/* 2547:     */  
/* 2548:     */  static native void nglRasterPos4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 2549:     */  
/* 2550:2550 */  public static void glRasterPos4d(double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2551:2551 */    long function_pointer = caps.glRasterPos4d;
/* 2552:2552 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2553:2553 */    nglRasterPos4d(x, y, z, w, function_pointer);
/* 2554:     */  }
/* 2555:     */  
/* 2556:     */  static native void nglRasterPos4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 2557:     */  
/* 2558:2558 */  public static void glRasterPos4i(int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2559:2559 */    long function_pointer = caps.glRasterPos4i;
/* 2560:2560 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2561:2561 */    nglRasterPos4i(x, y, z, w, function_pointer);
/* 2562:     */  }
/* 2563:     */  
/* 2564:     */  static native void nglRasterPos4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2565:     */  
/* 2566:2566 */  public static void glPushName(int name) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2567:2567 */    long function_pointer = caps.glPushName;
/* 2568:2568 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2569:2569 */    nglPushName(name, function_pointer);
/* 2570:     */  }
/* 2571:     */  
/* 2572:     */  static native void nglPushName(int paramInt, long paramLong);
/* 2573:     */  
/* 2574:2574 */  public static void glPopName() { ContextCapabilities caps = GLContext.getCapabilities();
/* 2575:2575 */    long function_pointer = caps.glPopName;
/* 2576:2576 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2577:2577 */    nglPopName(function_pointer);
/* 2578:     */  }
/* 2579:     */  
/* 2580:     */  static native void nglPopName(long paramLong);
/* 2581:     */  
/* 2582:2582 */  public static void glPushMatrix() { ContextCapabilities caps = GLContext.getCapabilities();
/* 2583:2583 */    long function_pointer = caps.glPushMatrix;
/* 2584:2584 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2585:2585 */    nglPushMatrix(function_pointer);
/* 2586:     */  }
/* 2587:     */  
/* 2588:     */  static native void nglPushMatrix(long paramLong);
/* 2589:     */  
/* 2590:2590 */  public static void glPopMatrix() { ContextCapabilities caps = GLContext.getCapabilities();
/* 2591:2591 */    long function_pointer = caps.glPopMatrix;
/* 2592:2592 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2593:2593 */    nglPopMatrix(function_pointer);
/* 2594:     */  }
/* 2595:     */  
/* 2596:     */  static native void nglPopMatrix(long paramLong);
/* 2597:     */  
/* 2598:2598 */  public static void glPushClientAttrib(int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2599:2599 */    long function_pointer = caps.glPushClientAttrib;
/* 2600:2600 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2601:2601 */    StateTracker.pushAttrib(caps, mask);
/* 2602:2602 */    nglPushClientAttrib(mask, function_pointer);
/* 2603:     */  }
/* 2604:     */  
/* 2605:     */  static native void nglPushClientAttrib(int paramInt, long paramLong);
/* 2606:     */  
/* 2607:2607 */  public static void glPopClientAttrib() { ContextCapabilities caps = GLContext.getCapabilities();
/* 2608:2608 */    long function_pointer = caps.glPopClientAttrib;
/* 2609:2609 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2610:2610 */    StateTracker.popAttrib(caps);
/* 2611:2611 */    nglPopClientAttrib(function_pointer);
/* 2612:     */  }
/* 2613:     */  
/* 2614:     */  static native void nglPopClientAttrib(long paramLong);
/* 2615:     */  
/* 2616:2616 */  public static void glPushAttrib(int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2617:2617 */    long function_pointer = caps.glPushAttrib;
/* 2618:2618 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2619:2619 */    nglPushAttrib(mask, function_pointer);
/* 2620:     */  }
/* 2621:     */  
/* 2622:     */  static native void nglPushAttrib(int paramInt, long paramLong);
/* 2623:     */  
/* 2624:2624 */  public static void glPopAttrib() { ContextCapabilities caps = GLContext.getCapabilities();
/* 2625:2625 */    long function_pointer = caps.glPopAttrib;
/* 2626:2626 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2627:2627 */    nglPopAttrib(function_pointer);
/* 2628:     */  }
/* 2629:     */  
/* 2630:     */  static native void nglPopAttrib(long paramLong);
/* 2631:     */  
/* 2632:2632 */  public static void glStencilFunc(int func, int ref, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2633:2633 */    long function_pointer = caps.glStencilFunc;
/* 2634:2634 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2635:2635 */    nglStencilFunc(func, ref, mask, function_pointer);
/* 2636:     */  }
/* 2637:     */  
/* 2638:     */  static native void nglStencilFunc(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2639:     */  
/* 2640:2640 */  public static void glVertexPointer(int size, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2641:2641 */    long function_pointer = caps.glVertexPointer;
/* 2642:2642 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2643:2643 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2644:2644 */    BufferChecks.checkDirect(pointer);
/* 2645:2645 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2646:2646 */    nglVertexPointer(size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2647:     */  }
/* 2648:     */  
/* 2649:2649 */  public static void glVertexPointer(int size, int stride, FloatBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2650:2650 */    long function_pointer = caps.glVertexPointer;
/* 2651:2651 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2652:2652 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2653:2653 */    BufferChecks.checkDirect(pointer);
/* 2654:2654 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2655:2655 */    nglVertexPointer(size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2656:     */  }
/* 2657:     */  
/* 2658:2658 */  public static void glVertexPointer(int size, int stride, IntBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2659:2659 */    long function_pointer = caps.glVertexPointer;
/* 2660:2660 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2661:2661 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2662:2662 */    BufferChecks.checkDirect(pointer);
/* 2663:2663 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2664:2664 */    nglVertexPointer(size, 5124, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2665:     */  }
/* 2666:     */  
/* 2667:2667 */  public static void glVertexPointer(int size, int stride, ShortBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2668:2668 */    long function_pointer = caps.glVertexPointer;
/* 2669:2669 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2670:2670 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2671:2671 */    BufferChecks.checkDirect(pointer);
/* 2672:2672 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2673:2673 */    nglVertexPointer(size, 5122, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/* 2674:     */  
/* 2675:     */  static native void nglVertexPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2676:     */  
/* 2677:2677 */  public static void glVertexPointer(int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2678:2678 */    long function_pointer = caps.glVertexPointer;
/* 2679:2679 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2680:2680 */    GLChecks.ensureArrayVBOenabled(caps);
/* 2681:2681 */    nglVertexPointerBO(size, type, stride, pointer_buffer_offset, function_pointer);
/* 2682:     */  }
/* 2683:     */  
/* 2684:     */  static native void nglVertexPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2685:     */  
/* 2686:     */  public static void glVertexPointer(int size, int type, int stride, ByteBuffer pointer) {
/* 2687:2687 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2688:2688 */    long function_pointer = caps.glVertexPointer;
/* 2689:2689 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2690:2690 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 2691:2691 */    BufferChecks.checkDirect(pointer);
/* 2692:2692 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
/* 2693:2693 */    nglVertexPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 2694:     */  }
/* 2695:     */  
/* 2696:     */  public static void glVertex2f(float x, float y) {
/* 2697:2697 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2698:2698 */    long function_pointer = caps.glVertex2f;
/* 2699:2699 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2700:2700 */    nglVertex2f(x, y, function_pointer);
/* 2701:     */  }
/* 2702:     */  
/* 2703:     */  static native void nglVertex2f(float paramFloat1, float paramFloat2, long paramLong);
/* 2704:     */  
/* 2705:2705 */  public static void glVertex2d(double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2706:2706 */    long function_pointer = caps.glVertex2d;
/* 2707:2707 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2708:2708 */    nglVertex2d(x, y, function_pointer);
/* 2709:     */  }
/* 2710:     */  
/* 2711:     */  static native void nglVertex2d(double paramDouble1, double paramDouble2, long paramLong);
/* 2712:     */  
/* 2713:2713 */  public static void glVertex2i(int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2714:2714 */    long function_pointer = caps.glVertex2i;
/* 2715:2715 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2716:2716 */    nglVertex2i(x, y, function_pointer);
/* 2717:     */  }
/* 2718:     */  
/* 2719:     */  static native void nglVertex2i(int paramInt1, int paramInt2, long paramLong);
/* 2720:     */  
/* 2721:2721 */  public static void glVertex3f(float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2722:2722 */    long function_pointer = caps.glVertex3f;
/* 2723:2723 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2724:2724 */    nglVertex3f(x, y, z, function_pointer);
/* 2725:     */  }
/* 2726:     */  
/* 2727:     */  static native void nglVertex3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 2728:     */  
/* 2729:2729 */  public static void glVertex3d(double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2730:2730 */    long function_pointer = caps.glVertex3d;
/* 2731:2731 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2732:2732 */    nglVertex3d(x, y, z, function_pointer);
/* 2733:     */  }
/* 2734:     */  
/* 2735:     */  static native void nglVertex3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 2736:     */  
/* 2737:2737 */  public static void glVertex3i(int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2738:2738 */    long function_pointer = caps.glVertex3i;
/* 2739:2739 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2740:2740 */    nglVertex3i(x, y, z, function_pointer);
/* 2741:     */  }
/* 2742:     */  
/* 2743:     */  static native void nglVertex3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2744:     */  
/* 2745:2745 */  public static void glVertex4f(float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2746:2746 */    long function_pointer = caps.glVertex4f;
/* 2747:2747 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2748:2748 */    nglVertex4f(x, y, z, w, function_pointer);
/* 2749:     */  }
/* 2750:     */  
/* 2751:     */  static native void nglVertex4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 2752:     */  
/* 2753:2753 */  public static void glVertex4d(double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2754:2754 */    long function_pointer = caps.glVertex4d;
/* 2755:2755 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2756:2756 */    nglVertex4d(x, y, z, w, function_pointer);
/* 2757:     */  }
/* 2758:     */  
/* 2759:     */  static native void nglVertex4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 2760:     */  
/* 2761:2761 */  public static void glVertex4i(int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2762:2762 */    long function_pointer = caps.glVertex4i;
/* 2763:2763 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2764:2764 */    nglVertex4i(x, y, z, w, function_pointer);
/* 2765:     */  }
/* 2766:     */  
/* 2767:     */  static native void nglVertex4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2768:     */  
/* 2769:2769 */  public static void glTranslatef(float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2770:2770 */    long function_pointer = caps.glTranslatef;
/* 2771:2771 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2772:2772 */    nglTranslatef(x, y, z, function_pointer);
/* 2773:     */  }
/* 2774:     */  
/* 2775:     */  static native void nglTranslatef(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 2776:     */  
/* 2777:2777 */  public static void glTranslated(double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2778:2778 */    long function_pointer = caps.glTranslated;
/* 2779:2779 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2780:2780 */    nglTranslated(x, y, z, function_pointer);
/* 2781:     */  }
/* 2782:     */  
/* 2783:     */  static native void nglTranslated(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 2784:     */  
/* 2785:2785 */  public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2786:2786 */    long function_pointer = caps.glTexImage1D;
/* 2787:2787 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2788:2788 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2789:2789 */    if (pixels != null)
/* 2790:2790 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2791:2791 */    nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2792:     */  }
/* 2793:     */  
/* 2794:2794 */  public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2795:2795 */    long function_pointer = caps.glTexImage1D;
/* 2796:2796 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2797:2797 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2798:2798 */    if (pixels != null)
/* 2799:2799 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2800:2800 */    nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2801:     */  }
/* 2802:     */  
/* 2803:2803 */  public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2804:2804 */    long function_pointer = caps.glTexImage1D;
/* 2805:2805 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2806:2806 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2807:2807 */    if (pixels != null)
/* 2808:2808 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2809:2809 */    nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2810:     */  }
/* 2811:     */  
/* 2812:2812 */  public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2813:2813 */    long function_pointer = caps.glTexImage1D;
/* 2814:2814 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2815:2815 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2816:2816 */    if (pixels != null)
/* 2817:2817 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2818:2818 */    nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2819:     */  }
/* 2820:     */  
/* 2821:2821 */  public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2822:2822 */    long function_pointer = caps.glTexImage1D;
/* 2823:2823 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2824:2824 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2825:2825 */    if (pixels != null)
/* 2826:2826 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/* 2827:2827 */    nglTexImage1D(target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/* 2828:     */  
/* 2829:     */  static native void nglTexImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 2830:     */  
/* 2831:2831 */  public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2832:2832 */    long function_pointer = caps.glTexImage1D;
/* 2833:2833 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2834:2834 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2835:2835 */    nglTexImage1DBO(target, level, internalformat, width, border, format, type, pixels_buffer_offset, function_pointer);
/* 2836:     */  }
/* 2837:     */  
/* 2838:     */  static native void nglTexImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 2839:     */  
/* 2840:2840 */  public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2841:2841 */    long function_pointer = caps.glTexImage2D;
/* 2842:2842 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2843:2843 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2844:2844 */    if (pixels != null)
/* 2845:2845 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2846:2846 */    nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2847:     */  }
/* 2848:     */  
/* 2849:2849 */  public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2850:2850 */    long function_pointer = caps.glTexImage2D;
/* 2851:2851 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2852:2852 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2853:2853 */    if (pixels != null)
/* 2854:2854 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2855:2855 */    nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2856:     */  }
/* 2857:     */  
/* 2858:2858 */  public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2859:2859 */    long function_pointer = caps.glTexImage2D;
/* 2860:2860 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2861:2861 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2862:2862 */    if (pixels != null)
/* 2863:2863 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2864:2864 */    nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2865:     */  }
/* 2866:     */  
/* 2867:2867 */  public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2868:2868 */    long function_pointer = caps.glTexImage2D;
/* 2869:2869 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2870:2870 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2871:2871 */    if (pixels != null)
/* 2872:2872 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2873:2873 */    nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 2874:     */  }
/* 2875:     */  
/* 2876:2876 */  public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2877:2877 */    long function_pointer = caps.glTexImage2D;
/* 2878:2878 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2879:2879 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2880:2880 */    if (pixels != null)
/* 2881:2881 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/* 2882:2882 */    nglTexImage2D(target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/* 2883:     */  
/* 2884:     */  static native void nglTexImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 2885:     */  
/* 2886:2886 */  public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2887:2887 */    long function_pointer = caps.glTexImage2D;
/* 2888:2888 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2889:2889 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2890:2890 */    nglTexImage2DBO(target, level, internalformat, width, height, border, format, type, pixels_buffer_offset, function_pointer);
/* 2891:     */  }
/* 2892:     */  
/* 2893:     */  static native void nglTexImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 2894:     */  
/* 2895:2895 */  public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2896:2896 */    long function_pointer = caps.glTexSubImage1D;
/* 2897:2897 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2898:2898 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2899:2899 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2900:2900 */    nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2901:     */  }
/* 2902:     */  
/* 2903:2903 */  public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2904:2904 */    long function_pointer = caps.glTexSubImage1D;
/* 2905:2905 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2906:2906 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2907:2907 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2908:2908 */    nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2909:     */  }
/* 2910:     */  
/* 2911:2911 */  public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2912:2912 */    long function_pointer = caps.glTexSubImage1D;
/* 2913:2913 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2914:2914 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2915:2915 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2916:2916 */    nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2917:     */  }
/* 2918:     */  
/* 2919:2919 */  public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2920:2920 */    long function_pointer = caps.glTexSubImage1D;
/* 2921:2921 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2922:2922 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2923:2923 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2924:2924 */    nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2925:     */  }
/* 2926:     */  
/* 2927:2927 */  public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2928:2928 */    long function_pointer = caps.glTexSubImage1D;
/* 2929:2929 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2930:2930 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2931:2931 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 2932:2932 */    nglTexSubImage1D(target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 2933:     */  
/* 2934:     */  static native void nglTexSubImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 2935:     */  
/* 2936:2936 */  public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2937:2937 */    long function_pointer = caps.glTexSubImage1D;
/* 2938:2938 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2939:2939 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2940:2940 */    nglTexSubImage1DBO(target, level, xoffset, width, format, type, pixels_buffer_offset, function_pointer);
/* 2941:     */  }
/* 2942:     */  
/* 2943:     */  static native void nglTexSubImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 2944:     */  
/* 2945:2945 */  public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2946:2946 */    long function_pointer = caps.glTexSubImage2D;
/* 2947:2947 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2948:2948 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2949:2949 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2950:2950 */    nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2951:     */  }
/* 2952:     */  
/* 2953:2953 */  public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2954:2954 */    long function_pointer = caps.glTexSubImage2D;
/* 2955:2955 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2956:2956 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2957:2957 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2958:2958 */    nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2959:     */  }
/* 2960:     */  
/* 2961:2961 */  public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2962:2962 */    long function_pointer = caps.glTexSubImage2D;
/* 2963:2963 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2964:2964 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2965:2965 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2966:2966 */    nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2967:     */  }
/* 2968:     */  
/* 2969:2969 */  public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2970:2970 */    long function_pointer = caps.glTexSubImage2D;
/* 2971:2971 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2972:2972 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2973:2973 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2974:2974 */    nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 2975:     */  }
/* 2976:     */  
/* 2977:2977 */  public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2978:2978 */    long function_pointer = caps.glTexSubImage2D;
/* 2979:2979 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2980:2980 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 2981:2981 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 2982:2982 */    nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 2983:     */  
/* 2984:     */  static native void nglTexSubImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 2985:     */  
/* 2986:2986 */  public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2987:2987 */    long function_pointer = caps.glTexSubImage2D;
/* 2988:2988 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2989:2989 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 2990:2990 */    nglTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset, function_pointer);
/* 2991:     */  }
/* 2992:     */  
/* 2993:     */  static native void nglTexSubImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 2994:     */  
/* 2995:2995 */  public static void glTexParameterf(int target, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2996:2996 */    long function_pointer = caps.glTexParameterf;
/* 2997:2997 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2998:2998 */    nglTexParameterf(target, pname, param, function_pointer);
/* 2999:     */  }
/* 3000:     */  
/* 3001:     */  static native void nglTexParameterf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 3002:     */  
/* 3003:3003 */  public static void glTexParameteri(int target, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3004:3004 */    long function_pointer = caps.glTexParameteri;
/* 3005:3005 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3006:3006 */    nglTexParameteri(target, pname, param, function_pointer);
/* 3007:     */  }
/* 3008:     */  
/* 3009:     */  static native void nglTexParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 3010:     */  
/* 3011:3011 */  public static void glTexParameter(int target, int pname, FloatBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3012:3012 */    long function_pointer = caps.glTexParameterfv;
/* 3013:3013 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3014:3014 */    BufferChecks.checkBuffer(param, 4);
/* 3015:3015 */    nglTexParameterfv(target, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3016:     */  }
/* 3017:     */  
/* 3018:     */  static native void nglTexParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3019:     */  
/* 3020:3020 */  public static void glTexParameter(int target, int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3021:3021 */    long function_pointer = caps.glTexParameteriv;
/* 3022:3022 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3023:3023 */    BufferChecks.checkBuffer(param, 4);
/* 3024:3024 */    nglTexParameteriv(target, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3025:     */  }
/* 3026:     */  
/* 3027:     */  static native void nglTexParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3028:     */  
/* 3029:3029 */  public static void glTexGenf(int coord, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3030:3030 */    long function_pointer = caps.glTexGenf;
/* 3031:3031 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3032:3032 */    nglTexGenf(coord, pname, param, function_pointer);
/* 3033:     */  }
/* 3034:     */  
/* 3035:     */  static native void nglTexGenf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 3036:     */  
/* 3037:3037 */  public static void glTexGend(int coord, int pname, double param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3038:3038 */    long function_pointer = caps.glTexGend;
/* 3039:3039 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3040:3040 */    nglTexGend(coord, pname, param, function_pointer);
/* 3041:     */  }
/* 3042:     */  
/* 3043:     */  static native void nglTexGend(int paramInt1, int paramInt2, double paramDouble, long paramLong);
/* 3044:     */  
/* 3045:3045 */  public static void glTexGen(int coord, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3046:3046 */    long function_pointer = caps.glTexGenfv;
/* 3047:3047 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3048:3048 */    BufferChecks.checkBuffer(params, 4);
/* 3049:3049 */    nglTexGenfv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 3050:     */  }
/* 3051:     */  
/* 3052:     */  static native void nglTexGenfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3053:     */  
/* 3054:3054 */  public static void glTexGen(int coord, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3055:3055 */    long function_pointer = caps.glTexGendv;
/* 3056:3056 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3057:3057 */    BufferChecks.checkBuffer(params, 4);
/* 3058:3058 */    nglTexGendv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 3059:     */  }
/* 3060:     */  
/* 3061:     */  static native void nglTexGendv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3062:     */  
/* 3063:3063 */  public static void glTexGeni(int coord, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3064:3064 */    long function_pointer = caps.glTexGeni;
/* 3065:3065 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3066:3066 */    nglTexGeni(coord, pname, param, function_pointer);
/* 3067:     */  }
/* 3068:     */  
/* 3069:     */  static native void nglTexGeni(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 3070:     */  
/* 3071:3071 */  public static void glTexGen(int coord, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3072:3072 */    long function_pointer = caps.glTexGeniv;
/* 3073:3073 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3074:3074 */    BufferChecks.checkBuffer(params, 4);
/* 3075:3075 */    nglTexGeniv(coord, pname, MemoryUtil.getAddress(params), function_pointer);
/* 3076:     */  }
/* 3077:     */  
/* 3078:     */  static native void nglTexGeniv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3079:     */  
/* 3080:3080 */  public static void glTexEnvf(int target, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3081:3081 */    long function_pointer = caps.glTexEnvf;
/* 3082:3082 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3083:3083 */    nglTexEnvf(target, pname, param, function_pointer);
/* 3084:     */  }
/* 3085:     */  
/* 3086:     */  static native void nglTexEnvf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 3087:     */  
/* 3088:3088 */  public static void glTexEnvi(int target, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3089:3089 */    long function_pointer = caps.glTexEnvi;
/* 3090:3090 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3091:3091 */    nglTexEnvi(target, pname, param, function_pointer);
/* 3092:     */  }
/* 3093:     */  
/* 3094:     */  static native void nglTexEnvi(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 3095:     */  
/* 3096:3096 */  public static void glTexEnv(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3097:3097 */    long function_pointer = caps.glTexEnvfv;
/* 3098:3098 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3099:3099 */    BufferChecks.checkBuffer(params, 4);
/* 3100:3100 */    nglTexEnvfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 3101:     */  }
/* 3102:     */  
/* 3103:     */  static native void nglTexEnvfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3104:     */  
/* 3105:3105 */  public static void glTexEnv(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3106:3106 */    long function_pointer = caps.glTexEnviv;
/* 3107:3107 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3108:3108 */    BufferChecks.checkBuffer(params, 4);
/* 3109:3109 */    nglTexEnviv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 3110:     */  }
/* 3111:     */  
/* 3112:     */  static native void nglTexEnviv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3113:     */  
/* 3114:3114 */  public static void glTexCoordPointer(int size, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3115:3115 */    long function_pointer = caps.glTexCoordPointer;
/* 3116:3116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3117:3117 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 3118:3118 */    BufferChecks.checkDirect(pointer);
/* 3119:3119 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3120:3120 */    nglTexCoordPointer(size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 3121:     */  }
/* 3122:     */  
/* 3123:3123 */  public static void glTexCoordPointer(int size, int stride, FloatBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3124:3124 */    long function_pointer = caps.glTexCoordPointer;
/* 3125:3125 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3126:3126 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 3127:3127 */    BufferChecks.checkDirect(pointer);
/* 3128:3128 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3129:3129 */    nglTexCoordPointer(size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 3130:     */  }
/* 3131:     */  
/* 3132:3132 */  public static void glTexCoordPointer(int size, int stride, IntBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3133:3133 */    long function_pointer = caps.glTexCoordPointer;
/* 3134:3134 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3135:3135 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 3136:3136 */    BufferChecks.checkDirect(pointer);
/* 3137:3137 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3138:3138 */    nglTexCoordPointer(size, 5124, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 3139:     */  }
/* 3140:     */  
/* 3141:3141 */  public static void glTexCoordPointer(int size, int stride, ShortBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3142:3142 */    long function_pointer = caps.glTexCoordPointer;
/* 3143:3143 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3144:3144 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 3145:3145 */    BufferChecks.checkDirect(pointer);
/* 3146:3146 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3147:3147 */    nglTexCoordPointer(size, 5122, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/* 3148:     */  
/* 3149:     */  static native void nglTexCoordPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 3150:     */  
/* 3151:3151 */  public static void glTexCoordPointer(int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3152:3152 */    long function_pointer = caps.glTexCoordPointer;
/* 3153:3153 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3154:3154 */    GLChecks.ensureArrayVBOenabled(caps);
/* 3155:3155 */    nglTexCoordPointerBO(size, type, stride, pointer_buffer_offset, function_pointer);
/* 3156:     */  }
/* 3157:     */  
/* 3158:     */  static native void nglTexCoordPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 3159:     */  
/* 3160:     */  public static void glTexCoordPointer(int size, int type, int stride, ByteBuffer pointer) {
/* 3161:3161 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3162:3162 */    long function_pointer = caps.glTexCoordPointer;
/* 3163:3163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3164:3164 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 3165:3165 */    BufferChecks.checkDirect(pointer);
/* 3166:3166 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glTexCoordPointer_buffer[StateTracker.getReferences(caps).glClientActiveTexture] = pointer;
/* 3167:3167 */    nglTexCoordPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
/* 3168:     */  }
/* 3169:     */  
/* 3170:     */  public static void glTexCoord1f(float s) {
/* 3171:3171 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3172:3172 */    long function_pointer = caps.glTexCoord1f;
/* 3173:3173 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3174:3174 */    nglTexCoord1f(s, function_pointer);
/* 3175:     */  }
/* 3176:     */  
/* 3177:     */  static native void nglTexCoord1f(float paramFloat, long paramLong);
/* 3178:     */  
/* 3179:3179 */  public static void glTexCoord1d(double s) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3180:3180 */    long function_pointer = caps.glTexCoord1d;
/* 3181:3181 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3182:3182 */    nglTexCoord1d(s, function_pointer);
/* 3183:     */  }
/* 3184:     */  
/* 3185:     */  static native void nglTexCoord1d(double paramDouble, long paramLong);
/* 3186:     */  
/* 3187:3187 */  public static void glTexCoord2f(float s, float t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3188:3188 */    long function_pointer = caps.glTexCoord2f;
/* 3189:3189 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3190:3190 */    nglTexCoord2f(s, t, function_pointer);
/* 3191:     */  }
/* 3192:     */  
/* 3193:     */  static native void nglTexCoord2f(float paramFloat1, float paramFloat2, long paramLong);
/* 3194:     */  
/* 3195:3195 */  public static void glTexCoord2d(double s, double t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3196:3196 */    long function_pointer = caps.glTexCoord2d;
/* 3197:3197 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3198:3198 */    nglTexCoord2d(s, t, function_pointer);
/* 3199:     */  }
/* 3200:     */  
/* 3201:     */  static native void nglTexCoord2d(double paramDouble1, double paramDouble2, long paramLong);
/* 3202:     */  
/* 3203:3203 */  public static void glTexCoord3f(float s, float t, float r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3204:3204 */    long function_pointer = caps.glTexCoord3f;
/* 3205:3205 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3206:3206 */    nglTexCoord3f(s, t, r, function_pointer);
/* 3207:     */  }
/* 3208:     */  
/* 3209:     */  static native void nglTexCoord3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 3210:     */  
/* 3211:3211 */  public static void glTexCoord3d(double s, double t, double r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3212:3212 */    long function_pointer = caps.glTexCoord3d;
/* 3213:3213 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3214:3214 */    nglTexCoord3d(s, t, r, function_pointer);
/* 3215:     */  }
/* 3216:     */  
/* 3217:     */  static native void nglTexCoord3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 3218:     */  
/* 3219:3219 */  public static void glTexCoord4f(float s, float t, float r, float q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3220:3220 */    long function_pointer = caps.glTexCoord4f;
/* 3221:3221 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3222:3222 */    nglTexCoord4f(s, t, r, q, function_pointer);
/* 3223:     */  }
/* 3224:     */  
/* 3225:     */  static native void nglTexCoord4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 3226:     */  
/* 3227:3227 */  public static void glTexCoord4d(double s, double t, double r, double q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3228:3228 */    long function_pointer = caps.glTexCoord4d;
/* 3229:3229 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3230:3230 */    nglTexCoord4d(s, t, r, q, function_pointer);
/* 3231:     */  }
/* 3232:     */  
/* 3233:     */  static native void nglTexCoord4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 3234:     */  
/* 3235:3235 */  public static void glStencilOp(int fail, int zfail, int zpass) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3236:3236 */    long function_pointer = caps.glStencilOp;
/* 3237:3237 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3238:3238 */    nglStencilOp(fail, zfail, zpass, function_pointer);
/* 3239:     */  }
/* 3240:     */  
/* 3241:     */  static native void nglStencilOp(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 3242:     */  
/* 3243:3243 */  public static void glStencilMask(int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3244:3244 */    long function_pointer = caps.glStencilMask;
/* 3245:3245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3246:3246 */    nglStencilMask(mask, function_pointer);
/* 3247:     */  }
/* 3248:     */  
/* 3249:     */  static native void nglStencilMask(int paramInt, long paramLong);
/* 3250:     */  
/* 3251:3251 */  public static void glViewport(int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3252:3252 */    long function_pointer = caps.glViewport;
/* 3253:3253 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3254:3254 */    nglViewport(x, y, width, height, function_pointer);
/* 3255:     */  }
/* 3256:     */  
/* 3257:     */  static native void nglViewport(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 3258:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL11
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */