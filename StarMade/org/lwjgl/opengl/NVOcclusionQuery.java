/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*   9:    */public final class NVOcclusionQuery
/*  10:    */{
/*  11:    */  public static final int GL_OCCLUSION_TEST_HP = 33125;
/*  12:    */  public static final int GL_OCCLUSION_TEST_RESULT_HP = 33126;
/*  13:    */  public static final int GL_PIXEL_COUNTER_BITS_NV = 34916;
/*  14:    */  public static final int GL_CURRENT_OCCLUSION_QUERY_ID_NV = 34917;
/*  15:    */  public static final int GL_PIXEL_COUNT_NV = 34918;
/*  16:    */  public static final int GL_PIXEL_COUNT_AVAILABLE_NV = 34919;
/*  17:    */  
/*  18:    */  public static void glGenOcclusionQueriesNV(IntBuffer piIDs)
/*  19:    */  {
/*  20: 20 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  21: 21 */    long function_pointer = caps.glGenOcclusionQueriesNV;
/*  22: 22 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  23: 23 */    BufferChecks.checkDirect(piIDs);
/*  24: 24 */    nglGenOcclusionQueriesNV(piIDs.remaining(), MemoryUtil.getAddress(piIDs), function_pointer);
/*  25:    */  }
/*  26:    */  
/*  27:    */  static native void nglGenOcclusionQueriesNV(int paramInt, long paramLong1, long paramLong2);
/*  28:    */  
/*  29:    */  public static int glGenOcclusionQueriesNV() {
/*  30: 30 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  31: 31 */    long function_pointer = caps.glGenOcclusionQueriesNV;
/*  32: 32 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  33: 33 */    IntBuffer piIDs = APIUtil.getBufferInt(caps);
/*  34: 34 */    nglGenOcclusionQueriesNV(1, MemoryUtil.getAddress(piIDs), function_pointer);
/*  35: 35 */    return piIDs.get(0);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public static void glDeleteOcclusionQueriesNV(IntBuffer piIDs) {
/*  39: 39 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  40: 40 */    long function_pointer = caps.glDeleteOcclusionQueriesNV;
/*  41: 41 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  42: 42 */    BufferChecks.checkDirect(piIDs);
/*  43: 43 */    nglDeleteOcclusionQueriesNV(piIDs.remaining(), MemoryUtil.getAddress(piIDs), function_pointer);
/*  44:    */  }
/*  45:    */  
/*  46:    */  static native void nglDeleteOcclusionQueriesNV(int paramInt, long paramLong1, long paramLong2);
/*  47:    */  
/*  48:    */  public static void glDeleteOcclusionQueriesNV(int piID) {
/*  49: 49 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  50: 50 */    long function_pointer = caps.glDeleteOcclusionQueriesNV;
/*  51: 51 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  52: 52 */    nglDeleteOcclusionQueriesNV(1, APIUtil.getInt(caps, piID), function_pointer);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public static boolean glIsOcclusionQueryNV(int id) {
/*  56: 56 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glIsOcclusionQueryNV;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    boolean __result = nglIsOcclusionQueryNV(id, function_pointer);
/*  60: 60 */    return __result;
/*  61:    */  }
/*  62:    */  
/*  63:    */  static native boolean nglIsOcclusionQueryNV(int paramInt, long paramLong);
/*  64:    */  
/*  65: 65 */  public static void glBeginOcclusionQueryNV(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/*  66: 66 */    long function_pointer = caps.glBeginOcclusionQueryNV;
/*  67: 67 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  68: 68 */    nglBeginOcclusionQueryNV(id, function_pointer);
/*  69:    */  }
/*  70:    */  
/*  71:    */  static native void nglBeginOcclusionQueryNV(int paramInt, long paramLong);
/*  72:    */  
/*  73: 73 */  public static void glEndOcclusionQueryNV() { ContextCapabilities caps = GLContext.getCapabilities();
/*  74: 74 */    long function_pointer = caps.glEndOcclusionQueryNV;
/*  75: 75 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  76: 76 */    nglEndOcclusionQueryNV(function_pointer);
/*  77:    */  }
/*  78:    */  
/*  79:    */  static native void nglEndOcclusionQueryNV(long paramLong);
/*  80:    */  
/*  81: 81 */  public static void glGetOcclusionQueryuNV(int id, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  82: 82 */    long function_pointer = caps.glGetOcclusionQueryuivNV;
/*  83: 83 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  84: 84 */    BufferChecks.checkBuffer(params, 1);
/*  85: 85 */    nglGetOcclusionQueryuivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*  86:    */  }
/*  87:    */  
/*  88:    */  static native void nglGetOcclusionQueryuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  89:    */  
/*  90:    */  public static int glGetOcclusionQueryuiNV(int id, int pname) {
/*  91: 91 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  92: 92 */    long function_pointer = caps.glGetOcclusionQueryuivNV;
/*  93: 93 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  94: 94 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  95: 95 */    nglGetOcclusionQueryuivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*  96: 96 */    return params.get(0);
/*  97:    */  }
/*  98:    */  
/*  99:    */  public static void glGetOcclusionQueryNV(int id, int pname, IntBuffer params) {
/* 100:100 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 101:101 */    long function_pointer = caps.glGetOcclusionQueryivNV;
/* 102:102 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 103:103 */    BufferChecks.checkBuffer(params, 1);
/* 104:104 */    nglGetOcclusionQueryivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 105:    */  }
/* 106:    */  
/* 107:    */  static native void nglGetOcclusionQueryivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 108:    */  
/* 109:    */  public static int glGetOcclusionQueryiNV(int id, int pname) {
/* 110:110 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glGetOcclusionQueryivNV;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 114:114 */    nglGetOcclusionQueryivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 115:115 */    return params.get(0);
/* 116:    */  }
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVOcclusionQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */