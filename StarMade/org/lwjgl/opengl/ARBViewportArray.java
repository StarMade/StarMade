/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.DoubleBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */
/*  34:    */public final class ARBViewportArray
/*  35:    */{
/*  36:    */  public static final int GL_MAX_VIEWPORTS = 33371;
/*  37:    */  public static final int GL_VIEWPORT_SUBPIXEL_BITS = 33372;
/*  38:    */  public static final int GL_VIEWPORT_BOUNDS_RANGE = 33373;
/*  39:    */  public static final int GL_LAYER_PROVOKING_VERTEX = 33374;
/*  40:    */  public static final int GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;
/*  41:    */  public static final int GL_SCISSOR_BOX = 3088;
/*  42:    */  public static final int GL_VIEWPORT = 2978;
/*  43:    */  public static final int GL_DEPTH_RANGE = 2928;
/*  44:    */  public static final int GL_SCISSOR_TEST = 3089;
/*  45:    */  public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
/*  46:    */  public static final int GL_LAST_VERTEX_CONVENTION = 36430;
/*  47:    */  public static final int GL_PROVOKING_VERTEX = 36431;
/*  48:    */  public static final int GL_UNDEFINED_VERTEX = 33376;
/*  49:    */  
/*  50:    */  public static void glViewportArray(int first, FloatBuffer v)
/*  51:    */  {
/*  52: 52 */    GL41.glViewportArray(first, v);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public static void glViewportIndexedf(int index, float x, float y, float w, float h) {
/*  56: 56 */    GL41.glViewportIndexedf(index, x, y, w, h);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public static void glViewportIndexed(int index, FloatBuffer v) {
/*  60: 60 */    GL41.glViewportIndexed(index, v);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public static void glScissorArray(int first, IntBuffer v) {
/*  64: 64 */    GL41.glScissorArray(first, v);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static void glScissorIndexed(int index, int left, int bottom, int width, int height) {
/*  68: 68 */    GL41.glScissorIndexed(index, left, bottom, width, height);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public static void glScissorIndexed(int index, IntBuffer v) {
/*  72: 72 */    GL41.glScissorIndexed(index, v);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static void glDepthRangeArray(int first, DoubleBuffer v) {
/*  76: 76 */    GL41.glDepthRangeArray(first, v);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public static void glDepthRangeIndexed(int index, double n, double f) {
/*  80: 80 */    GL41.glDepthRangeIndexed(index, n, f);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public static void glGetFloat(int target, int index, FloatBuffer data) {
/*  84: 84 */    GL41.glGetFloat(target, index, data);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public static float glGetFloat(int target, int index)
/*  88:    */  {
/*  89: 89 */    return GL41.glGetFloat(target, index);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static void glGetDouble(int target, int index, DoubleBuffer data) {
/*  93: 93 */    GL41.glGetDouble(target, index, data);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public static double glGetDouble(int target, int index)
/*  97:    */  {
/*  98: 98 */    return GL41.glGetDouble(target, index);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public static void glGetIntegerIndexedEXT(int target, int index, IntBuffer v) {
/* 102:102 */    EXTDrawBuffers2.glGetIntegerIndexedEXT(target, index, v);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static int glGetIntegerIndexedEXT(int target, int index)
/* 106:    */  {
/* 107:107 */    return EXTDrawBuffers2.glGetIntegerIndexedEXT(target, index);
/* 108:    */  }
/* 109:    */  
/* 110:    */  public static void glEnableIndexedEXT(int target, int index) {
/* 111:111 */    EXTDrawBuffers2.glEnableIndexedEXT(target, index);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static void glDisableIndexedEXT(int target, int index) {
/* 115:115 */    EXTDrawBuffers2.glDisableIndexedEXT(target, index);
/* 116:    */  }
/* 117:    */  
/* 118:    */  public static boolean glIsEnabledIndexedEXT(int target, int index) {
/* 119:119 */    return EXTDrawBuffers2.glIsEnabledIndexedEXT(target, index);
/* 120:    */  }
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBViewportArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */