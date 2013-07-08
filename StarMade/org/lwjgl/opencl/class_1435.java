package org.lwjgl.opencl;

import java.nio.ByteBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.MemoryUtil;
import org.lwjgl.Sys;

public final class class_1435
{
  private static boolean created;
  
  private static native void nCreate(String paramString)
    throws LWJGLException;
  
  private static native void nCreateDefault()
    throws LWJGLException;
  
  private static native void nDestroy();
  
  public static boolean isCreated()
  {
    return created;
  }
  
  public static void create()
    throws LWJGLException
  {
    if (created) {
      return;
    }
    String libname;
    String[] library_names;
    switch (LWJGLUtil.getPlatform())
    {
    case 3: 
      libname = "OpenCL";
      library_names = new String[] { "OpenCL.dll" };
      break;
    case 1: 
      libname = "OpenCL";
      library_names = new String[] { "libOpenCL64.so", "libOpenCL.so" };
      break;
    case 2: 
      libname = "OpenCL";
      library_names = new String[] { "OpenCL.dylib" };
      break;
    default: 
      throw new LWJGLException("Unknown platform: " + LWJGLUtil.getPlatform());
    }
    String[] oclPaths = LWJGLUtil.getLibraryPaths(libname, library_names, CL.class.getClassLoader());
    LWJGLUtil.log("Found " + oclPaths.length + " OpenCL paths");
    String[] arr$ = oclPaths;
    int len$ = arr$.length;
    int local_i$ = 0;
    while (local_i$ < len$)
    {
      String oclPath = arr$[local_i$];
      try
      {
        nCreate(oclPath);
        created = true;
      }
      catch (LWJGLException local_e)
      {
        LWJGLUtil.log("Failed to load " + oclPath + ": " + local_e.getMessage());
        local_i$++;
      }
    }
    if ((!created) && (LWJGLUtil.getPlatform() == 2))
    {
      nCreateDefault();
      created = true;
    }
    if (!created) {
      throw new LWJGLException("Could not locate OpenCL library.");
    }
    if (!CLCapabilities.OpenCL10) {
      throw new RuntimeException("OpenCL 1.0 not supported.");
    }
  }
  
  public static void destroy() {}
  
  static long getFunctionAddress(String[] aliases)
  {
    for (String aliase : aliases)
    {
      long address = getFunctionAddress(aliase);
      if (address != 0L) {
        return address;
      }
    }
    return 0L;
  }
  
  static long getFunctionAddress(String name)
  {
    ByteBuffer buffer = MemoryUtil.encodeASCII(name);
    return ngetFunctionAddress(MemoryUtil.getAddress(buffer));
  }
  
  private static native long ngetFunctionAddress(long paramLong);
  
  static native ByteBuffer getHostBuffer(long paramLong, int paramInt);
  
  private static native void resetNativeStubs(Class paramClass);
  
  static {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.class_1435
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */