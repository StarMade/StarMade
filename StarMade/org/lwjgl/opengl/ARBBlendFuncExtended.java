/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */
/* 19:   */public final class ARBBlendFuncExtended
/* 20:   */{
/* 21:   */  public static final int GL_SRC1_COLOR = 35065;
/* 22:   */  public static final int GL_SRC1_ALPHA = 34185;
/* 23:   */  public static final int GL_ONE_MINUS_SRC1_COLOR = 35066;
/* 24:   */  public static final int GL_ONE_MINUS_SRC1_ALPHA = 35067;
/* 25:   */  public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;
/* 26:   */  
/* 27:   */  public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, ByteBuffer name)
/* 28:   */  {
/* 29:29 */    GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, CharSequence name)
/* 33:   */  {
/* 34:34 */    GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public static int glGetFragDataIndex(int program, ByteBuffer name) {
/* 38:38 */    return GL33.glGetFragDataIndex(program, name);
/* 39:   */  }
/* 40:   */  
/* 41:   */  public static int glGetFragDataIndex(int program, CharSequence name)
/* 42:   */  {
/* 43:43 */    return GL33.glGetFragDataIndex(program, name);
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBBlendFuncExtended
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */