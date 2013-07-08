package org.lwjgl.opengl;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class ARBViewportArray
{
  public static final int GL_MAX_VIEWPORTS = 33371;
  public static final int GL_VIEWPORT_SUBPIXEL_BITS = 33372;
  public static final int GL_VIEWPORT_BOUNDS_RANGE = 33373;
  public static final int GL_LAYER_PROVOKING_VERTEX = 33374;
  public static final int GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;
  public static final int GL_SCISSOR_BOX = 3088;
  public static final int GL_VIEWPORT = 2978;
  public static final int GL_DEPTH_RANGE = 2928;
  public static final int GL_SCISSOR_TEST = 3089;
  public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
  public static final int GL_LAST_VERTEX_CONVENTION = 36430;
  public static final int GL_PROVOKING_VERTEX = 36431;
  public static final int GL_UNDEFINED_VERTEX = 33376;
  
  public static void glViewportArray(int first, FloatBuffer local_v)
  {
    GL41.glViewportArray(first, local_v);
  }
  
  public static void glViewportIndexedf(int index, float local_x, float local_y, float local_w, float local_h)
  {
    GL41.glViewportIndexedf(index, local_x, local_y, local_w, local_h);
  }
  
  public static void glViewportIndexed(int index, FloatBuffer local_v)
  {
    GL41.glViewportIndexed(index, local_v);
  }
  
  public static void glScissorArray(int first, IntBuffer local_v)
  {
    GL41.glScissorArray(first, local_v);
  }
  
  public static void glScissorIndexed(int index, int left, int bottom, int width, int height)
  {
    GL41.glScissorIndexed(index, left, bottom, width, height);
  }
  
  public static void glScissorIndexed(int index, IntBuffer local_v)
  {
    GL41.glScissorIndexed(index, local_v);
  }
  
  public static void glDepthRangeArray(int first, DoubleBuffer local_v)
  {
    GL41.glDepthRangeArray(first, local_v);
  }
  
  public static void glDepthRangeIndexed(int index, double local_n, double local_f)
  {
    GL41.glDepthRangeIndexed(index, local_n, local_f);
  }
  
  public static void glGetFloat(int target, int index, FloatBuffer data)
  {
    GL41.glGetFloat(target, index, data);
  }
  
  public static float glGetFloat(int target, int index)
  {
    return GL41.glGetFloat(target, index);
  }
  
  public static void glGetDouble(int target, int index, DoubleBuffer data)
  {
    GL41.glGetDouble(target, index, data);
  }
  
  public static double glGetDouble(int target, int index)
  {
    return GL41.glGetDouble(target, index);
  }
  
  public static void glGetIntegerIndexedEXT(int target, int index, IntBuffer local_v)
  {
    EXTDrawBuffers2.glGetIntegerIndexedEXT(target, index, local_v);
  }
  
  public static int glGetIntegerIndexedEXT(int target, int index)
  {
    return EXTDrawBuffers2.glGetIntegerIndexedEXT(target, index);
  }
  
  public static void glEnableIndexedEXT(int target, int index)
  {
    EXTDrawBuffers2.glEnableIndexedEXT(target, index);
  }
  
  public static void glDisableIndexedEXT(int target, int index)
  {
    EXTDrawBuffers2.glDisableIndexedEXT(target, index);
  }
  
  public static boolean glIsEnabledIndexedEXT(int target, int index)
  {
    return EXTDrawBuffers2.glIsEnabledIndexedEXT(target, index);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBViewportArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */