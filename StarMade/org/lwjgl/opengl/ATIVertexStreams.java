package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class ATIVertexStreams
{
  public static final int GL_MAX_VERTEX_STREAMS_ATI = 34667;
  public static final int GL_VERTEX_SOURCE_ATI = 34668;
  public static final int GL_VERTEX_STREAM0_ATI = 34669;
  public static final int GL_VERTEX_STREAM1_ATI = 34670;
  public static final int GL_VERTEX_STREAM2_ATI = 34671;
  public static final int GL_VERTEX_STREAM3_ATI = 34672;
  public static final int GL_VERTEX_STREAM4_ATI = 34673;
  public static final int GL_VERTEX_STREAM5_ATI = 34674;
  public static final int GL_VERTEX_STREAM6_ATI = 34675;
  public static final int GL_VERTEX_STREAM7_ATI = 34676;
  
  public static void glVertexStream2fATI(int stream, float local_x, float local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream2fATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream2fATI(stream, local_x, local_y, function_pointer);
  }
  
  static native void nglVertexStream2fATI(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
  
  public static void glVertexStream2dATI(int stream, double local_x, double local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream2dATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream2dATI(stream, local_x, local_y, function_pointer);
  }
  
  static native void nglVertexStream2dATI(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
  
  public static void glVertexStream2iATI(int stream, int local_x, int local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream2iATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream2iATI(stream, local_x, local_y, function_pointer);
  }
  
  static native void nglVertexStream2iATI(int paramInt1, int paramInt2, int paramInt3, long paramLong);
  
  public static void glVertexStream2sATI(int stream, short local_x, short local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream2sATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream2sATI(stream, local_x, local_y, function_pointer);
  }
  
  static native void nglVertexStream2sATI(int paramInt, short paramShort1, short paramShort2, long paramLong);
  
  public static void glVertexStream3fATI(int stream, float local_x, float local_y, float local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream3fATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream3fATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglVertexStream3fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
  
  public static void glVertexStream3dATI(int stream, double local_x, double local_y, double local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream3dATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream3dATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglVertexStream3dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
  
  public static void glVertexStream3iATI(int stream, int local_x, int local_y, int local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream3iATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream3iATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglVertexStream3iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
  
  public static void glVertexStream3sATI(int stream, short local_x, short local_y, short local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream3sATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream3sATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglVertexStream3sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
  
  public static void glVertexStream4fATI(int stream, float local_x, float local_y, float local_z, float local_w)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream4fATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream4fATI(stream, local_x, local_y, local_z, local_w, function_pointer);
  }
  
  static native void nglVertexStream4fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
  
  public static void glVertexStream4dATI(int stream, double local_x, double local_y, double local_z, double local_w)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream4dATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream4dATI(stream, local_x, local_y, local_z, local_w, function_pointer);
  }
  
  static native void nglVertexStream4dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
  
  public static void glVertexStream4iATI(int stream, int local_x, int local_y, int local_z, int local_w)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream4iATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream4iATI(stream, local_x, local_y, local_z, local_w, function_pointer);
  }
  
  static native void nglVertexStream4iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
  
  public static void glVertexStream4sATI(int stream, short local_x, short local_y, short local_z, short local_w)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexStream4sATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexStream4sATI(stream, local_x, local_y, local_z, local_w, function_pointer);
  }
  
  static native void nglVertexStream4sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
  
  public static void glNormalStream3bATI(int stream, byte local_x, byte local_y, byte local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glNormalStream3bATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglNormalStream3bATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglNormalStream3bATI(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
  
  public static void glNormalStream3fATI(int stream, float local_x, float local_y, float local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glNormalStream3fATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglNormalStream3fATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglNormalStream3fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
  
  public static void glNormalStream3dATI(int stream, double local_x, double local_y, double local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glNormalStream3dATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglNormalStream3dATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglNormalStream3dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
  
  public static void glNormalStream3iATI(int stream, int local_x, int local_y, int local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glNormalStream3iATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglNormalStream3iATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglNormalStream3iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
  
  public static void glNormalStream3sATI(int stream, short local_x, short local_y, short local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glNormalStream3sATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglNormalStream3sATI(stream, local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglNormalStream3sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
  
  public static void glClientActiveVertexStreamATI(int stream)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glClientActiveVertexStreamATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglClientActiveVertexStreamATI(stream, function_pointer);
  }
  
  static native void nglClientActiveVertexStreamATI(int paramInt, long paramLong);
  
  public static void glVertexBlendEnvfATI(int pname, float param)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexBlendEnvfATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexBlendEnvfATI(pname, param, function_pointer);
  }
  
  static native void nglVertexBlendEnvfATI(int paramInt, float paramFloat, long paramLong);
  
  public static void glVertexBlendEnviATI(int pname, int param)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexBlendEnviATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexBlendEnviATI(pname, param, function_pointer);
  }
  
  static native void nglVertexBlendEnviATI(int paramInt1, int paramInt2, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ATIVertexStreams
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */