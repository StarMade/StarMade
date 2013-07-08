/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   4:    */public final class ARBShaderImageLoadStore
/*   5:    */{
/*   6:    */  public static final int GL_MAX_IMAGE_UNITS = 36664;
/*   7:    */  
/*   8:    */  public static final int GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 36665;
/*   9:    */  
/*  10:    */  public static final int GL_MAX_IMAGE_SAMPLES = 36973;
/*  11:    */  
/*  12:    */  public static final int GL_MAX_VERTEX_IMAGE_UNIFORMS = 37066;
/*  13:    */  
/*  14:    */  public static final int GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS = 37067;
/*  15:    */  
/*  16:    */  public static final int GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS = 37068;
/*  17:    */  
/*  18:    */  public static final int GL_MAX_GEOMETRY_IMAGE_UNIFORMS = 37069;
/*  19:    */  
/*  20:    */  public static final int GL_MAX_FRAGMENT_IMAGE_UNIFORMS = 37070;
/*  21:    */  
/*  22:    */  public static final int GL_MAX_COMBINED_IMAGE_UNIFORMS = 37071;
/*  23:    */  
/*  24:    */  public static final int GL_IMAGE_BINDING_NAME = 36666;
/*  25:    */  
/*  26:    */  public static final int GL_IMAGE_BINDING_LEVEL = 36667;
/*  27:    */  
/*  28:    */  public static final int GL_IMAGE_BINDING_LAYERED = 36668;
/*  29:    */  
/*  30:    */  public static final int GL_IMAGE_BINDING_LAYER = 36669;
/*  31:    */  
/*  32:    */  public static final int GL_IMAGE_BINDING_ACCESS = 36670;
/*  33:    */  
/*  34:    */  public static final int GL_IMAGE_BINDING_FORMAT = 36974;
/*  35:    */  
/*  36:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 1;
/*  37:    */  
/*  38:    */  public static final int GL_ELEMENT_ARRAY_BARRIER_BIT = 2;
/*  39:    */  
/*  40:    */  public static final int GL_UNIFORM_BARRIER_BIT = 4;
/*  41:    */  
/*  42:    */  public static final int GL_TEXTURE_FETCH_BARRIER_BIT = 8;
/*  43:    */  
/*  44:    */  public static final int GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 32;
/*  45:    */  
/*  46:    */  public static final int GL_COMMAND_BARRIER_BIT = 64;
/*  47:    */  
/*  48:    */  public static final int GL_PIXEL_BUFFER_BARRIER_BIT = 128;
/*  49:    */  
/*  50:    */  public static final int GL_TEXTURE_UPDATE_BARRIER_BIT = 256;
/*  51:    */  
/*  52:    */  public static final int GL_BUFFER_UPDATE_BARRIER_BIT = 512;
/*  53:    */  
/*  54:    */  public static final int GL_FRAMEBUFFER_BARRIER_BIT = 1024;
/*  55:    */  
/*  56:    */  public static final int GL_TRANSFORM_FEEDBACK_BARRIER_BIT = 2048;
/*  57:    */  
/*  58:    */  public static final int GL_ATOMIC_COUNTER_BARRIER_BIT = 4096;
/*  59:    */  
/*  60:    */  public static final int GL_ALL_BARRIER_BITS = -1;
/*  61:    */  
/*  62:    */  public static final int GL_IMAGE_1D = 36940;
/*  63:    */  
/*  64:    */  public static final int GL_IMAGE_2D = 36941;
/*  65:    */  
/*  66:    */  public static final int GL_IMAGE_3D = 36942;
/*  67:    */  
/*  68:    */  public static final int GL_IMAGE_2D_RECT = 36943;
/*  69:    */  
/*  70:    */  public static final int GL_IMAGE_CUBE = 36944;
/*  71:    */  public static final int GL_IMAGE_BUFFER = 36945;
/*  72:    */  public static final int GL_IMAGE_1D_ARRAY = 36946;
/*  73:    */  public static final int GL_IMAGE_2D_ARRAY = 36947;
/*  74:    */  public static final int GL_IMAGE_CUBE_MAP_ARRAY = 36948;
/*  75:    */  public static final int GL_IMAGE_2D_MULTISAMPLE = 36949;
/*  76:    */  public static final int GL_IMAGE_2D_MULTISAMPLE_ARRAY = 36950;
/*  77:    */  public static final int GL_INT_IMAGE_1D = 36951;
/*  78:    */  public static final int GL_INT_IMAGE_2D = 36952;
/*  79:    */  public static final int GL_INT_IMAGE_3D = 36953;
/*  80:    */  public static final int GL_INT_IMAGE_2D_RECT = 36954;
/*  81:    */  public static final int GL_INT_IMAGE_CUBE = 36955;
/*  82:    */  public static final int GL_INT_IMAGE_BUFFER = 36956;
/*  83:    */  public static final int GL_INT_IMAGE_1D_ARRAY = 36957;
/*  84:    */  public static final int GL_INT_IMAGE_2D_ARRAY = 36958;
/*  85:    */  public static final int GL_INT_IMAGE_CUBE_MAP_ARRAY = 36959;
/*  86:    */  public static final int GL_INT_IMAGE_2D_MULTISAMPLE = 36960;
/*  87:    */  public static final int GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36961;
/*  88:    */  public static final int GL_UNSIGNED_INT_IMAGE_1D = 36962;
/*  89:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D = 36963;
/*  90:    */  public static final int GL_UNSIGNED_INT_IMAGE_3D = 36964;
/*  91:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_RECT = 36965;
/*  92:    */  public static final int GL_UNSIGNED_INT_IMAGE_CUBE = 36966;
/*  93:    */  public static final int GL_UNSIGNED_INT_IMAGE_BUFFER = 36967;
/*  94:    */  public static final int GL_UNSIGNED_INT_IMAGE_1D_ARRAY = 36968;
/*  95:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_ARRAY = 36969;
/*  96:    */  public static final int GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY = 36970;
/*  97:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE = 36971;
/*  98:    */  public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36972;
/*  99:    */  public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;
/* 100:    */  public static final int GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 37064;
/* 101:    */  public static final int IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 37065;
/* 102:    */  
/* 103:    */  public static void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer, int access, int format)
/* 104:    */  {
/* 105:105 */    GL42.glBindImageTexture(unit, texture, level, layered, layer, access, format);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public static void glMemoryBarrier(int barriers) {
/* 109:109 */    GL42.glMemoryBarrier(barriers);
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShaderImageLoadStore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */