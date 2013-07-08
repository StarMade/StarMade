package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class NVOcclusionQuery
{
  public static final int GL_OCCLUSION_TEST_HP = 33125;
  public static final int GL_OCCLUSION_TEST_RESULT_HP = 33126;
  public static final int GL_PIXEL_COUNTER_BITS_NV = 34916;
  public static final int GL_CURRENT_OCCLUSION_QUERY_ID_NV = 34917;
  public static final int GL_PIXEL_COUNT_NV = 34918;
  public static final int GL_PIXEL_COUNT_AVAILABLE_NV = 34919;
  
  public static void glGenOcclusionQueriesNV(IntBuffer piIDs)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGenOcclusionQueriesNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(piIDs);
    nglGenOcclusionQueriesNV(piIDs.remaining(), MemoryUtil.getAddress(piIDs), function_pointer);
  }
  
  static native void nglGenOcclusionQueriesNV(int paramInt, long paramLong1, long paramLong2);
  
  public static int glGenOcclusionQueriesNV()
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGenOcclusionQueriesNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    IntBuffer piIDs = APIUtil.getBufferInt(caps);
    nglGenOcclusionQueriesNV(1, MemoryUtil.getAddress(piIDs), function_pointer);
    return piIDs.get(0);
  }
  
  public static void glDeleteOcclusionQueriesNV(IntBuffer piIDs)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glDeleteOcclusionQueriesNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(piIDs);
    nglDeleteOcclusionQueriesNV(piIDs.remaining(), MemoryUtil.getAddress(piIDs), function_pointer);
  }
  
  static native void nglDeleteOcclusionQueriesNV(int paramInt, long paramLong1, long paramLong2);
  
  public static void glDeleteOcclusionQueriesNV(int piID)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glDeleteOcclusionQueriesNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglDeleteOcclusionQueriesNV(1, APIUtil.getInt(caps, piID), function_pointer);
  }
  
  public static boolean glIsOcclusionQueryNV(int local_id)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glIsOcclusionQueryNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    boolean __result = nglIsOcclusionQueryNV(local_id, function_pointer);
    return __result;
  }
  
  static native boolean nglIsOcclusionQueryNV(int paramInt, long paramLong);
  
  public static void glBeginOcclusionQueryNV(int local_id)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glBeginOcclusionQueryNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglBeginOcclusionQueryNV(local_id, function_pointer);
  }
  
  static native void nglBeginOcclusionQueryNV(int paramInt, long paramLong);
  
  public static void glEndOcclusionQueryNV()
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glEndOcclusionQueryNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglEndOcclusionQueryNV(function_pointer);
  }
  
  static native void nglEndOcclusionQueryNV(long paramLong);
  
  public static void glGetOcclusionQueryuNV(int local_id, int pname, IntBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetOcclusionQueryuivNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(params, 1);
    nglGetOcclusionQueryuivNV(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglGetOcclusionQueryuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
  
  public static int glGetOcclusionQueryuiNV(int local_id, int pname)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetOcclusionQueryuivNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    IntBuffer params = APIUtil.getBufferInt(caps);
    nglGetOcclusionQueryuivNV(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
    return params.get(0);
  }
  
  public static void glGetOcclusionQueryNV(int local_id, int pname, IntBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetOcclusionQueryivNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(params, 1);
    nglGetOcclusionQueryivNV(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglGetOcclusionQueryivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
  
  public static int glGetOcclusionQueryiNV(int local_id, int pname)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetOcclusionQueryivNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    IntBuffer params = APIUtil.getBufferInt(caps);
    nglGetOcclusionQueryivNV(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
    return params.get(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.NVOcclusionQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */