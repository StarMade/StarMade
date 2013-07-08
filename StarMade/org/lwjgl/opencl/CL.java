/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.LWJGLException;
/*   5:    */import org.lwjgl.LWJGLUtil;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */import org.lwjgl.Sys;
/*   8:    */
/*  62:    */public final class CL
/*  63:    */{
/*  64:    */  private static boolean created;
/*  65:    */  
/*  66:    */  private static native void nCreate(String paramString)
/*  67:    */    throws LWJGLException;
/*  68:    */  
/*  69:    */  private static native void nCreateDefault()
/*  70:    */    throws LWJGLException;
/*  71:    */  
/*  72:    */  private static native void nDestroy();
/*  73:    */  
/*  74:    */  public static boolean isCreated()
/*  75:    */  {
/*  76: 76 */    return created;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public static void create() throws LWJGLException {
/*  80: 80 */    if (created) {
/*  81:    */      return;
/*  82:    */    }
/*  83:    */    
/*  84:    */    String libname;
/*  85:    */    String[] library_names;
/*  86: 86 */    switch (LWJGLUtil.getPlatform()) {
/*  87:    */    case 3: 
/*  88: 88 */      libname = "OpenCL";
/*  89: 89 */      library_names = new String[] { "OpenCL.dll" };
/*  90: 90 */      break;
/*  91:    */    case 1: 
/*  92: 92 */      libname = "OpenCL";
/*  93: 93 */      library_names = new String[] { "libOpenCL64.so", "libOpenCL.so" };
/*  94: 94 */      break;
/*  95:    */    case 2: 
/*  96: 96 */      libname = "OpenCL";
/*  97: 97 */      library_names = new String[] { "OpenCL.dylib" };
/*  98: 98 */      break;
/*  99:    */    default: 
/* 100:100 */      throw new LWJGLException("Unknown platform: " + LWJGLUtil.getPlatform());
/* 101:    */    }
/* 102:    */    
/* 103:103 */    String[] oclPaths = LWJGLUtil.getLibraryPaths(libname, library_names, CL.class.getClassLoader());
/* 104:104 */    LWJGLUtil.log("Found " + oclPaths.length + " OpenCL paths");
/* 105:105 */    for (String oclPath : oclPaths) {
/* 106:    */      try {
/* 107:107 */        nCreate(oclPath);
/* 108:108 */        created = true;
/* 109:    */      }
/* 110:    */      catch (LWJGLException e) {
/* 111:111 */        LWJGLUtil.log("Failed to load " + oclPath + ": " + e.getMessage());
/* 112:    */      }
/* 113:    */    }
/* 114:    */    
/* 115:115 */    if ((!created) && (LWJGLUtil.getPlatform() == 2))
/* 116:    */    {
/* 117:117 */      nCreateDefault();
/* 118:118 */      created = true;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    if (!created) {
/* 122:122 */      throw new LWJGLException("Could not locate OpenCL library.");
/* 123:    */    }
/* 124:124 */    if (!CLCapabilities.OpenCL10) {
/* 125:125 */      throw new RuntimeException("OpenCL 1.0 not supported.");
/* 126:    */    }
/* 127:    */  }
/* 128:    */  
/* 132:    */  public static void destroy() {}
/* 133:    */  
/* 137:    */  static long getFunctionAddress(String[] aliases)
/* 138:    */  {
/* 139:139 */    for (String aliase : aliases) {
/* 140:140 */      long address = getFunctionAddress(aliase);
/* 141:141 */      if (address != 0L)
/* 142:142 */        return address;
/* 143:    */    }
/* 144:144 */    return 0L;
/* 145:    */  }
/* 146:    */  
/* 147:    */  static long getFunctionAddress(String name)
/* 148:    */  {
/* 149:149 */    ByteBuffer buffer = MemoryUtil.encodeASCII(name);
/* 150:150 */    return ngetFunctionAddress(MemoryUtil.getAddress(buffer));
/* 151:    */  }
/* 152:    */  
/* 153:    */  private static native long ngetFunctionAddress(long paramLong);
/* 154:    */  
/* 155:    */  static native ByteBuffer getHostBuffer(long paramLong, int paramInt);
/* 156:    */  
/* 157:    */  private static native void resetNativeStubs(Class paramClass);
/* 158:    */  
/* 159:    */  static {}
/* 160:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */