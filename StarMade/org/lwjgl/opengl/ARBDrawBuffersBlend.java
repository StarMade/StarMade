/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class ARBDrawBuffersBlend
/* 10:   */{
/* 11:   */  public static void glBlendEquationiARB(int buf, int mode)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glBlendEquationiARB;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglBlendEquationiARB(buf, mode, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglBlendEquationiARB(int paramInt1, int paramInt2, long paramLong);
/* 20:   */  
/* 21:21 */  public static void glBlendEquationSeparateiARB(int buf, int modeRGB, int modeAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glBlendEquationSeparateiARB;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    nglBlendEquationSeparateiARB(buf, modeRGB, modeAlpha, function_pointer);
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native void nglBlendEquationSeparateiARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 28:   */  
/* 29:29 */  public static void glBlendFunciARB(int buf, int src, int dst) { ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glBlendFunciARB;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglBlendFunciARB(buf, src, dst, function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglBlendFunciARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 36:   */  
/* 37:37 */  public static void glBlendFuncSeparateiARB(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 38:38 */    long function_pointer = caps.glBlendFuncSeparateiARB;
/* 39:39 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    nglBlendFuncSeparateiARB(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
/* 41:   */  }
/* 42:   */  
/* 43:   */  static native void nglBlendFuncSeparateiARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawBuffersBlend
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */