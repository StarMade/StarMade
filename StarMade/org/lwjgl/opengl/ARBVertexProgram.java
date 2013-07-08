package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public final class ARBVertexProgram
  extends ARBProgram
{
  public static final int GL_VERTEX_PROGRAM_ARB = 34336;
  public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
  public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
  public static final int GL_COLOR_SUM_ARB = 33880;
  public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
  public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
  public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
  public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
  public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
  public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
  public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
  public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 34992;
  public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 34993;
  public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34994;
  public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34995;
  public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
  
  public static void glVertexAttrib1sARB(int index, short local_x)
  {
    ARBVertexShader.glVertexAttrib1sARB(index, local_x);
  }
  
  public static void glVertexAttrib1fARB(int index, float local_x)
  {
    ARBVertexShader.glVertexAttrib1fARB(index, local_x);
  }
  
  public static void glVertexAttrib1dARB(int index, double local_x)
  {
    ARBVertexShader.glVertexAttrib1dARB(index, local_x);
  }
  
  public static void glVertexAttrib2sARB(int index, short local_x, short local_y)
  {
    ARBVertexShader.glVertexAttrib2sARB(index, local_x, local_y);
  }
  
  public static void glVertexAttrib2fARB(int index, float local_x, float local_y)
  {
    ARBVertexShader.glVertexAttrib2fARB(index, local_x, local_y);
  }
  
  public static void glVertexAttrib2dARB(int index, double local_x, double local_y)
  {
    ARBVertexShader.glVertexAttrib2dARB(index, local_x, local_y);
  }
  
  public static void glVertexAttrib3sARB(int index, short local_x, short local_y, short local_z)
  {
    ARBVertexShader.glVertexAttrib3sARB(index, local_x, local_y, local_z);
  }
  
  public static void glVertexAttrib3fARB(int index, float local_x, float local_y, float local_z)
  {
    ARBVertexShader.glVertexAttrib3fARB(index, local_x, local_y, local_z);
  }
  
  public static void glVertexAttrib3dARB(int index, double local_x, double local_y, double local_z)
  {
    ARBVertexShader.glVertexAttrib3dARB(index, local_x, local_y, local_z);
  }
  
  public static void glVertexAttrib4sARB(int index, short local_x, short local_y, short local_z, short local_w)
  {
    ARBVertexShader.glVertexAttrib4sARB(index, local_x, local_y, local_z, local_w);
  }
  
  public static void glVertexAttrib4fARB(int index, float local_x, float local_y, float local_z, float local_w)
  {
    ARBVertexShader.glVertexAttrib4fARB(index, local_x, local_y, local_z, local_w);
  }
  
  public static void glVertexAttrib4dARB(int index, double local_x, double local_y, double local_z, double local_w)
  {
    ARBVertexShader.glVertexAttrib4dARB(index, local_x, local_y, local_z, local_w);
  }
  
  public static void glVertexAttrib4NubARB(int index, byte local_x, byte local_y, byte local_z, byte local_w)
  {
    ARBVertexShader.glVertexAttrib4NubARB(index, local_x, local_y, local_z, local_w);
  }
  
  public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer)
  {
    ARBVertexShader.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
  }
  
  public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer)
  {
    ARBVertexShader.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
  }
  
  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer)
  {
    ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
  }
  
  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer)
  {
    ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
  }
  
  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer)
  {
    ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
  }
  
  public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset)
  {
    ARBVertexShader.glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_buffer_offset);
  }
  
  public static void glEnableVertexAttribArrayARB(int index)
  {
    ARBVertexShader.glEnableVertexAttribArrayARB(index);
  }
  
  public static void glDisableVertexAttribArrayARB(int index)
  {
    ARBVertexShader.glDisableVertexAttribArrayARB(index);
  }
  
  public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params)
  {
    ARBVertexShader.glGetVertexAttribARB(index, pname, params);
  }
  
  public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params)
  {
    ARBVertexShader.glGetVertexAttribARB(index, pname, params);
  }
  
  public static void glGetVertexAttribARB(int index, int pname, IntBuffer params)
  {
    ARBVertexShader.glGetVertexAttribARB(index, pname, params);
  }
  
  public static ByteBuffer glGetVertexAttribPointerARB(int index, int pname, long result_size)
  {
    return ARBVertexShader.glGetVertexAttribPointerARB(index, pname, result_size);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBVertexProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */