/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 27:   */public final class APPLEObjectPurgeable
/* 28:   */{
/* 29:   */  public static final int GL_RELEASED_APPLE = 35353;
/* 30:   */  public static final int GL_VOLATILE_APPLE = 35354;
/* 31:   */  public static final int GL_RETAINED_APPLE = 35355;
/* 32:   */  public static final int GL_UNDEFINED_APPLE = 35356;
/* 33:   */  public static final int GL_PURGEABLE_APPLE = 35357;
/* 34:   */  public static final int GL_BUFFER_OBJECT_APPLE = 34227;
/* 35:   */  
/* 36:   */  public static int glObjectPurgeableAPPLE(int objectType, int name, int option)
/* 37:   */  {
/* 38:38 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 39:39 */    long function_pointer = caps.glObjectPurgeableAPPLE;
/* 40:40 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 41:41 */    int __result = nglObjectPurgeableAPPLE(objectType, name, option, function_pointer);
/* 42:42 */    return __result;
/* 43:   */  }
/* 44:   */  
/* 45:   */  static native int nglObjectPurgeableAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 46:   */  
/* 47:47 */  public static int glObjectUnpurgeableAPPLE(int objectType, int name, int option) { ContextCapabilities caps = GLContext.getCapabilities();
/* 48:48 */    long function_pointer = caps.glObjectUnpurgeableAPPLE;
/* 49:49 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 50:50 */    int __result = nglObjectUnpurgeableAPPLE(objectType, name, option, function_pointer);
/* 51:51 */    return __result;
/* 52:   */  }
/* 53:   */  
/* 54:   */  static native int nglObjectUnpurgeableAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 55:   */  
/* 56:56 */  public static void glGetObjectParameterAPPLE(int objectType, int name, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 57:57 */    long function_pointer = caps.glGetObjectParameterivAPPLE;
/* 58:58 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 59:59 */    BufferChecks.checkBuffer(params, 1);
/* 60:60 */    nglGetObjectParameterivAPPLE(objectType, name, pname, MemoryUtil.getAddress(params), function_pointer);
/* 61:   */  }
/* 62:   */  
/* 63:   */  static native void nglGetObjectParameterivAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 64:   */  
/* 65:   */  public static int glGetObjectParameteriAPPLE(int objectType, int name, int pname) {
/* 66:66 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 67:67 */    long function_pointer = caps.glGetObjectParameterivAPPLE;
/* 68:68 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 69:69 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 70:70 */    nglGetObjectParameterivAPPLE(objectType, name, pname, MemoryUtil.getAddress(params), function_pointer);
/* 71:71 */    return params.get(0);
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEObjectPurgeable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */