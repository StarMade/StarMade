/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */
/* 54:   */public final class ARBTextureMultisample
/* 55:   */{
/* 56:   */  public static final int GL_SAMPLE_POSITION = 36432;
/* 57:   */  public static final int GL_SAMPLE_MASK = 36433;
/* 58:   */  public static final int GL_SAMPLE_MASK_VALUE = 36434;
/* 59:   */  public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
/* 60:   */  public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 37121;
/* 61:   */  public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
/* 62:   */  public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 37123;
/* 63:   */  public static final int GL_MAX_SAMPLE_MASK_WORDS = 36441;
/* 64:   */  public static final int GL_MAX_COLOR_TEXTURE_SAMPLES = 37134;
/* 65:   */  public static final int GL_MAX_DEPTH_TEXTURE_SAMPLES = 37135;
/* 66:   */  public static final int GL_MAX_INTEGER_SAMPLES = 37136;
/* 67:   */  public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE = 37124;
/* 68:   */  public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 37125;
/* 69:   */  public static final int GL_TEXTURE_SAMPLES = 37126;
/* 70:   */  public static final int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 37127;
/* 71:   */  public static final int GL_SAMPLER_2D_MULTISAMPLE = 37128;
/* 72:   */  public static final int GL_INT_SAMPLER_2D_MULTISAMPLE = 37129;
/* 73:   */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 37130;
/* 74:   */  public static final int GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 37131;
/* 75:   */  public static final int GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37132;
/* 76:   */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37133;
/* 77:   */  
/* 78:   */  public static void glTexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations)
/* 79:   */  {
/* 80:80 */    GL32.glTexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
/* 81:   */  }
/* 82:   */  
/* 83:   */  public static void glTexImage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
/* 84:84 */    GL32.glTexImage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
/* 85:   */  }
/* 86:   */  
/* 87:   */  public static void glGetMultisample(int pname, int index, FloatBuffer val) {
/* 88:88 */    GL32.glGetMultisample(pname, index, val);
/* 89:   */  }
/* 90:   */  
/* 91:   */  public static void glSampleMaski(int index, int mask) {
/* 92:92 */    GL32.glSampleMaski(index, mask);
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureMultisample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */