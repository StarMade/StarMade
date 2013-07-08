/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */
/*  57:    */public final class ARBTessellationShader
/*  58:    */{
/*  59:    */  public static final int GL_PATCHES = 14;
/*  60:    */  public static final int GL_PATCH_VERTICES = 36466;
/*  61:    */  public static final int GL_PATCH_DEFAULT_INNER_LEVEL = 36467;
/*  62:    */  public static final int GL_PATCH_DEFAULT_OUTER_LEVEL = 36468;
/*  63:    */  public static final int GL_TESS_CONTROL_OUTPUT_VERTICES = 36469;
/*  64:    */  public static final int GL_TESS_GEN_MODE = 36470;
/*  65:    */  public static final int GL_TESS_GEN_SPACING = 36471;
/*  66:    */  public static final int GL_TESS_GEN_VERTEX_ORDER = 36472;
/*  67:    */  public static final int GL_TESS_GEN_POINT_MODE = 36473;
/*  68:    */  public static final int GL_TRIANGLES = 4;
/*  69:    */  public static final int GL_QUADS = 7;
/*  70:    */  public static final int GL_ISOLINES = 36474;
/*  71:    */  public static final int GL_EQUAL = 514;
/*  72:    */  public static final int GL_FRACTIONAL_ODD = 36475;
/*  73:    */  public static final int GL_FRACTIONAL_EVEN = 36476;
/*  74:    */  public static final int GL_CCW = 2305;
/*  75:    */  public static final int GL_CW = 2304;
/*  76:    */  public static final int GL_FALSE = 0;
/*  77:    */  public static final int GL_TRUE = 1;
/*  78:    */  public static final int GL_MAX_PATCH_VERTICES = 36477;
/*  79:    */  public static final int GL_MAX_TESS_GEN_LEVEL = 36478;
/*  80:    */  public static final int GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 36479;
/*  81:    */  public static final int GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 36480;
/*  82:    */  public static final int GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 36481;
/*  83:    */  public static final int GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 36482;
/*  84:    */  public static final int GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 36483;
/*  85:    */  public static final int GL_MAX_TESS_PATCH_COMPONENTS = 36484;
/*  86:    */  public static final int GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS = 36485;
/*  87:    */  public static final int GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 36486;
/*  88:    */  public static final int GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS = 36489;
/*  89:    */  public static final int GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 36490;
/*  90:    */  public static final int GL_MAX_TESS_CONTROL_INPUT_COMPONENTS = 34924;
/*  91:    */  public static final int GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS = 34925;
/*  92:    */  public static final int GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 36382;
/*  93:    */  public static final int GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 36383;
/*  94:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER = 34032;
/*  95:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 34033;
/*  96:    */  public static final int GL_TESS_EVALUATION_SHADER = 36487;
/*  97:    */  public static final int GL_TESS_CONTROL_SHADER = 36488;
/*  98:    */  
/*  99:    */  public static void glPatchParameteri(int pname, int value)
/* 100:    */  {
/* 101:101 */    GL40.glPatchParameteri(pname, value);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static void glPatchParameter(int pname, FloatBuffer values) {
/* 105:105 */    GL40.glPatchParameter(pname, values);
/* 106:    */  }
/* 107:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTessellationShader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */