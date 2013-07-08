/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  5:   */public final class ARBTextureView
/*  6:   */{
/*  7:   */  public static final int GL_TEXTURE_VIEW_MIN_LEVEL = 33499;
/*  8:   */  
/* 10:   */  public static final int GL_TEXTURE_VIEW_NUM_LEVELS = 33500;
/* 11:   */  
/* 13:   */  public static final int GL_TEXTURE_VIEW_MIN_LAYER = 33501;
/* 14:   */  
/* 16:   */  public static final int GL_TEXTURE_VIEW_NUM_LAYERS = 33502;
/* 17:   */  
/* 18:   */  public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 33503;
/* 19:   */  
/* 21:   */  public static void glTextureView(int texture, int target, int origtexture, int internalformat, int minlevel, int numlevels, int minlayer, int numlayers)
/* 22:   */  {
/* 23:23 */    GL43.glTextureView(texture, target, origtexture, internalformat, minlevel, numlevels, minlayer, numlayers);
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureView
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */