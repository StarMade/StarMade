/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 12:   */public final class AMDNameGenDelete
/* 13:   */{
/* 14:   */  public static final int GL_DATA_BUFFER_AMD = 37201;
/* 15:   */  public static final int GL_PERFORMANCE_MONITOR_AMD = 37202;
/* 16:   */  public static final int GL_QUERY_OBJECT_AMD = 37203;
/* 17:   */  public static final int GL_VERTEX_ARRAY_OBJECT_AMD = 37204;
/* 18:   */  public static final int GL_SAMPLER_OBJECT_AMD = 37205;
/* 19:   */  
/* 20:   */  public static void glGenNamesAMD(int identifier, IntBuffer names)
/* 21:   */  {
/* 22:22 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 23:23 */    long function_pointer = caps.glGenNamesAMD;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    BufferChecks.checkDirect(names);
/* 26:26 */    nglGenNamesAMD(identifier, names.remaining(), MemoryUtil.getAddress(names), function_pointer);
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native void nglGenNamesAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 30:   */  
/* 31:   */  public static int glGenNamesAMD(int identifier) {
/* 32:32 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 33:33 */    long function_pointer = caps.glGenNamesAMD;
/* 34:34 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 35:35 */    IntBuffer names = APIUtil.getBufferInt(caps);
/* 36:36 */    nglGenNamesAMD(identifier, 1, MemoryUtil.getAddress(names), function_pointer);
/* 37:37 */    return names.get(0);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static void glDeleteNamesAMD(int identifier, IntBuffer names) {
/* 41:41 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glDeleteNamesAMD;
/* 43:43 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    BufferChecks.checkDirect(names);
/* 45:45 */    nglDeleteNamesAMD(identifier, names.remaining(), MemoryUtil.getAddress(names), function_pointer);
/* 46:   */  }
/* 47:   */  
/* 48:   */  static native void nglDeleteNamesAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 49:   */  
/* 50:   */  public static void glDeleteNamesAMD(int identifier, int name) {
/* 51:51 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 52:52 */    long function_pointer = caps.glDeleteNamesAMD;
/* 53:53 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 54:54 */    nglDeleteNamesAMD(identifier, 1, APIUtil.getInt(caps, name), function_pointer);
/* 55:   */  }
/* 56:   */  
/* 57:   */  public static boolean glIsNameAMD(int identifier, int name) {
/* 58:58 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 59:59 */    long function_pointer = caps.glIsNameAMD;
/* 60:60 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 61:61 */    boolean __result = nglIsNameAMD(identifier, name, function_pointer);
/* 62:62 */    return __result;
/* 63:   */  }
/* 64:   */  
/* 65:   */  static native boolean nglIsNameAMD(int paramInt1, int paramInt2, long paramLong);
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDNameGenDelete
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */