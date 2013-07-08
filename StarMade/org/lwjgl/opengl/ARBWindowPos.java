/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class ARBWindowPos
/* 10:   */{
/* 11:   */  public static void glWindowPos2fARB(float x, float y)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glWindowPos2fARB;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglWindowPos2fARB(x, y, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglWindowPos2fARB(float paramFloat1, float paramFloat2, long paramLong);
/* 20:   */  
/* 21:21 */  public static void glWindowPos2dARB(double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glWindowPos2dARB;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    nglWindowPos2dARB(x, y, function_pointer);
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native void nglWindowPos2dARB(double paramDouble1, double paramDouble2, long paramLong);
/* 28:   */  
/* 29:29 */  public static void glWindowPos2iARB(int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glWindowPos2iARB;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglWindowPos2iARB(x, y, function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglWindowPos2iARB(int paramInt1, int paramInt2, long paramLong);
/* 36:   */  
/* 37:37 */  public static void glWindowPos2sARB(short x, short y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 38:38 */    long function_pointer = caps.glWindowPos2sARB;
/* 39:39 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    nglWindowPos2sARB(x, y, function_pointer);
/* 41:   */  }
/* 42:   */  
/* 43:   */  static native void nglWindowPos2sARB(short paramShort1, short paramShort2, long paramLong);
/* 44:   */  
/* 45:45 */  public static void glWindowPos3fARB(float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 46:46 */    long function_pointer = caps.glWindowPos3fARB;
/* 47:47 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 48:48 */    nglWindowPos3fARB(x, y, z, function_pointer);
/* 49:   */  }
/* 50:   */  
/* 51:   */  static native void nglWindowPos3fARB(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 52:   */  
/* 53:53 */  public static void glWindowPos3dARB(double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 54:54 */    long function_pointer = caps.glWindowPos3dARB;
/* 55:55 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 56:56 */    nglWindowPos3dARB(x, y, z, function_pointer);
/* 57:   */  }
/* 58:   */  
/* 59:   */  static native void nglWindowPos3dARB(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 60:   */  
/* 61:61 */  public static void glWindowPos3iARB(int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 62:62 */    long function_pointer = caps.glWindowPos3iARB;
/* 63:63 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 64:64 */    nglWindowPos3iARB(x, y, z, function_pointer);
/* 65:   */  }
/* 66:   */  
/* 67:   */  static native void nglWindowPos3iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 68:   */  
/* 69:69 */  public static void glWindowPos3sARB(short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    long function_pointer = caps.glWindowPos3sARB;
/* 71:71 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 72:72 */    nglWindowPos3sARB(x, y, z, function_pointer);
/* 73:   */  }
/* 74:   */  
/* 75:   */  static native void nglWindowPos3sARB(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBWindowPos
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */