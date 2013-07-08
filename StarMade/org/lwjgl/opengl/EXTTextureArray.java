/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  6:   */public final class EXTTextureArray
/*  7:   */{
/*  8:   */  public static final int GL_TEXTURE_1D_ARRAY_EXT = 35864;
/*  9:   */  
/* 12:   */  public static final int GL_TEXTURE_2D_ARRAY_EXT = 35866;
/* 13:   */  
/* 16:   */  public static final int GL_PROXY_TEXTURE_2D_ARRAY_EXT = 35867;
/* 17:   */  
/* 20:   */  public static final int GL_PROXY_TEXTURE_1D_ARRAY_EXT = 35865;
/* 21:   */  
/* 24:   */  public static final int GL_TEXTURE_BINDING_1D_ARRAY_EXT = 35868;
/* 25:   */  
/* 28:   */  public static final int GL_TEXTURE_BINDING_2D_ARRAY_EXT = 35869;
/* 29:   */  
/* 32:   */  public static final int GL_MAX_ARRAY_TEXTURE_LAYERS_EXT = 35071;
/* 33:   */  
/* 36:   */  public static final int GL_COMPARE_REF_DEPTH_TO_TEXTURE_EXT = 34894;
/* 37:   */  
/* 40:   */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER_EXT = 36052;
/* 41:   */  
/* 44:   */  public static final int GL_SAMPLER_1D_ARRAY_EXT = 36288;
/* 45:   */  
/* 48:   */  public static final int GL_SAMPLER_2D_ARRAY_EXT = 36289;
/* 49:   */  
/* 52:   */  public static final int GL_SAMPLER_1D_ARRAY_SHADOW_EXT = 36291;
/* 53:   */  
/* 56:   */  public static final int GL_SAMPLER_2D_ARRAY_SHADOW_EXT = 36292;
/* 57:   */  
/* 60:   */  public static void glFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer)
/* 61:   */  {
/* 62:62 */    EXTGeometryShader4.glFramebufferTextureLayerEXT(target, attachment, texture, level, layer);
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTextureArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */