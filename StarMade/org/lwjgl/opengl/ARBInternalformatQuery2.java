/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.LongBuffer;
/*   4:    */
/*  23:    */public final class ARBInternalformatQuery2
/*  24:    */{
/*  25:    */  public static final int GL_TEXTURE_1D = 3552;
/*  26:    */  public static final int GL_TEXTURE_1D_ARRAY = 35864;
/*  27:    */  public static final int GL_TEXTURE_2D = 3553;
/*  28:    */  public static final int GL_TEXTURE_2D_ARRAY = 35866;
/*  29:    */  public static final int GL_TEXTURE_3D = 32879;
/*  30:    */  public static final int GL_TEXTURE_CUBE_MAP = 34067;
/*  31:    */  public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;
/*  32:    */  public static final int GL_TEXTURE_RECTANGLE = 34037;
/*  33:    */  public static final int GL_TEXTURE_BUFFER = 35882;
/*  34:    */  public static final int GL_RENDERBUFFER = 36161;
/*  35:    */  public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
/*  36:    */  public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
/*  37:    */  public static final int GL_SAMPLES = 32937;
/*  38:    */  public static final int GL_NUM_SAMPLE_COUNTS = 37760;
/*  39:    */  public static final int GL_INTERNALFORMAT_SUPPORTED = 33391;
/*  40:    */  public static final int GL_INTERNALFORMAT_PREFERRED = 33392;
/*  41:    */  public static final int GL_INTERNALFORMAT_RED_SIZE = 33393;
/*  42:    */  public static final int GL_INTERNALFORMAT_GREEN_SIZE = 33394;
/*  43:    */  public static final int GL_INTERNALFORMAT_BLUE_SIZE = 33395;
/*  44:    */  public static final int GL_INTERNALFORMAT_ALPHA_SIZE = 33396;
/*  45:    */  public static final int GL_INTERNALFORMAT_DEPTH_SIZE = 33397;
/*  46:    */  public static final int GL_INTERNALFORMAT_STENCIL_SIZE = 33398;
/*  47:    */  public static final int GL_INTERNALFORMAT_SHARED_SIZE = 33399;
/*  48:    */  public static final int GL_INTERNALFORMAT_RED_TYPE = 33400;
/*  49:    */  public static final int GL_INTERNALFORMAT_GREEN_TYPE = 33401;
/*  50:    */  public static final int GL_INTERNALFORMAT_BLUE_TYPE = 33402;
/*  51:    */  public static final int GL_INTERNALFORMAT_ALPHA_TYPE = 33403;
/*  52:    */  public static final int GL_INTERNALFORMAT_DEPTH_TYPE = 33404;
/*  53:    */  public static final int GL_INTERNALFORMAT_STENCIL_TYPE = 33405;
/*  54:    */  public static final int GL_MAX_WIDTH = 33406;
/*  55:    */  public static final int GL_MAX_HEIGHT = 33407;
/*  56:    */  public static final int GL_MAX_DEPTH = 33408;
/*  57:    */  public static final int GL_MAX_LAYERS = 33409;
/*  58:    */  public static final int GL_MAX_COMBINED_DIMENSIONS = 33410;
/*  59:    */  public static final int GL_COLOR_COMPONENTS = 33411;
/*  60:    */  public static final int GL_DEPTH_COMPONENTS = 33412;
/*  61:    */  public static final int GL_STENCIL_COMPONENTS = 33413;
/*  62:    */  public static final int GL_COLOR_RENDERABLE = 33414;
/*  63:    */  public static final int GL_DEPTH_RENDERABLE = 33415;
/*  64:    */  public static final int GL_STENCIL_RENDERABLE = 33416;
/*  65:    */  public static final int GL_FRAMEBUFFER_RENDERABLE = 33417;
/*  66:    */  public static final int GL_FRAMEBUFFER_RENDERABLE_LAYERED = 33418;
/*  67:    */  public static final int GL_FRAMEBUFFER_BLEND = 33419;
/*  68:    */  public static final int GL_READ_PIXELS = 33420;
/*  69:    */  public static final int GL_READ_PIXELS_FORMAT = 33421;
/*  70:    */  public static final int GL_READ_PIXELS_TYPE = 33422;
/*  71:    */  public static final int GL_TEXTURE_IMAGE_FORMAT = 33423;
/*  72:    */  public static final int GL_TEXTURE_IMAGE_TYPE = 33424;
/*  73:    */  public static final int GL_GET_TEXTURE_IMAGE_FORMAT = 33425;
/*  74:    */  public static final int GL_GET_TEXTURE_IMAGE_TYPE = 33426;
/*  75:    */  public static final int GL_MIPMAP = 33427;
/*  76:    */  public static final int GL_MANUAL_GENERATE_MIPMAP = 33428;
/*  77:    */  public static final int GL_AUTO_GENERATE_MIPMAP = 33429;
/*  78:    */  public static final int GL_COLOR_ENCODING = 33430;
/*  79:    */  public static final int GL_SRGB_READ = 33431;
/*  80:    */  public static final int GL_SRGB_WRITE = 33432;
/*  81:    */  public static final int GL_SRGB_DECODE_ARB = 33433;
/*  82:    */  public static final int GL_FILTER = 33434;
/*  83:    */  public static final int GL_VERTEX_TEXTURE = 33435;
/*  84:    */  public static final int GL_TESS_CONTROL_TEXTURE = 33436;
/*  85:    */  public static final int GL_TESS_EVALUATION_TEXTURE = 33437;
/*  86:    */  public static final int GL_GEOMETRY_TEXTURE = 33438;
/*  87:    */  public static final int GL_FRAGMENT_TEXTURE = 33439;
/*  88:    */  public static final int GL_COMPUTE_TEXTURE = 33440;
/*  89:    */  public static final int GL_TEXTURE_SHADOW = 33441;
/*  90:    */  public static final int GL_TEXTURE_GATHER = 33442;
/*  91:    */  public static final int GL_TEXTURE_GATHER_SHADOW = 33443;
/*  92:    */  public static final int GL_SHADER_IMAGE_LOAD = 33444;
/*  93:    */  public static final int GL_SHADER_IMAGE_STORE = 33445;
/*  94:    */  public static final int GL_SHADER_IMAGE_ATOMIC = 33446;
/*  95:    */  public static final int GL_IMAGE_TEXEL_SIZE = 33447;
/*  96:    */  public static final int GL_IMAGE_COMPATIBILITY_CLASS = 33448;
/*  97:    */  public static final int GL_IMAGE_PIXEL_FORMAT = 33449;
/*  98:    */  public static final int GL_IMAGE_PIXEL_TYPE = 33450;
/*  99:    */  public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;
/* 100:    */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST = 33452;
/* 101:    */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST = 33453;
/* 102:    */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE = 33454;
/* 103:    */  public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 33455;
/* 104:    */  public static final int GL_TEXTURE_COMPRESSED = 34465;
/* 105:    */  public static final int GL_TEXTURE_COMPRESSED_BLOCK_WIDTH = 33457;
/* 106:    */  public static final int GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT = 33458;
/* 107:    */  public static final int GL_TEXTURE_COMPRESSED_BLOCK_SIZE = 33459;
/* 108:    */  public static final int GL_CLEAR_BUFFER = 33460;
/* 109:    */  public static final int GL_TEXTURE_VIEW = 33461;
/* 110:    */  public static final int GL_VIEW_COMPATIBILITY_CLASS = 33462;
/* 111:    */  public static final int GL_FULL_SUPPORT = 33463;
/* 112:    */  public static final int GL_CAVEAT_SUPPORT = 33464;
/* 113:    */  public static final int GL_IMAGE_CLASS_4_X_32 = 33465;
/* 114:    */  public static final int GL_IMAGE_CLASS_2_X_32 = 33466;
/* 115:    */  public static final int GL_IMAGE_CLASS_1_X_32 = 33467;
/* 116:    */  public static final int GL_IMAGE_CLASS_4_X_16 = 33468;
/* 117:    */  public static final int GL_IMAGE_CLASS_2_X_16 = 33469;
/* 118:    */  public static final int GL_IMAGE_CLASS_1_X_16 = 33470;
/* 119:    */  public static final int GL_IMAGE_CLASS_4_X_8 = 33471;
/* 120:    */  public static final int GL_IMAGE_CLASS_2_X_8 = 33472;
/* 121:    */  public static final int GL_IMAGE_CLASS_1_X_8 = 33473;
/* 122:    */  public static final int GL_IMAGE_CLASS_11_11_10 = 33474;
/* 123:    */  public static final int GL_IMAGE_CLASS_10_10_10_2 = 33475;
/* 124:    */  public static final int GL_VIEW_CLASS_128_BITS = 33476;
/* 125:    */  public static final int GL_VIEW_CLASS_96_BITS = 33477;
/* 126:    */  public static final int GL_VIEW_CLASS_64_BITS = 33478;
/* 127:    */  public static final int GL_VIEW_CLASS_48_BITS = 33479;
/* 128:    */  public static final int GL_VIEW_CLASS_32_BITS = 33480;
/* 129:    */  public static final int GL_VIEW_CLASS_24_BITS = 33481;
/* 130:    */  public static final int GL_VIEW_CLASS_16_BITS = 33482;
/* 131:    */  public static final int GL_VIEW_CLASS_8_BITS = 33483;
/* 132:    */  public static final int GL_VIEW_CLASS_S3TC_DXT1_RGB = 33484;
/* 133:    */  public static final int GL_VIEW_CLASS_S3TC_DXT1_RGBA = 33485;
/* 134:    */  public static final int GL_VIEW_CLASS_S3TC_DXT3_RGBA = 33486;
/* 135:    */  public static final int GL_VIEW_CLASS_S3TC_DXT5_RGBA = 33487;
/* 136:    */  public static final int GL_VIEW_CLASS_RGTC1_RED = 33488;
/* 137:    */  public static final int GL_VIEW_CLASS_RGTC2_RG = 33489;
/* 138:    */  public static final int GL_VIEW_CLASS_BPTC_UNORM = 33490;
/* 139:    */  public static final int GL_VIEW_CLASS_BPTC_FLOAT = 33491;
/* 140:    */  
/* 141:    */  public static void glGetInternalformat(int target, int internalformat, int pname, LongBuffer params)
/* 142:    */  {
/* 143:143 */    GL43.glGetInternalformat(target, internalformat, pname, params);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public static long glGetInternalformati64(int target, int internalformat, int pname)
/* 147:    */  {
/* 148:148 */    return GL43.glGetInternalformati64(target, internalformat, pname);
/* 149:    */  }
/* 150:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInternalformatQuery2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */