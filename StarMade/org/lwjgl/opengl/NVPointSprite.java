/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class NVPointSprite
/* 10:   */{
/* 11:   */  public static final int GL_POINT_SPRITE_NV = 34913;
/* 12:   */  public static final int GL_COORD_REPLACE_NV = 34914;
/* 13:   */  public static final int GL_POINT_SPRITE_R_MODE_NV = 34915;
/* 14:   */  
/* 15:   */  public static void glPointParameteriNV(int pname, int param)
/* 16:   */  {
/* 17:17 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 18:18 */    long function_pointer = caps.glPointParameteriNV;
/* 19:19 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 20:20 */    nglPointParameteriNV(pname, param, function_pointer);
/* 21:   */  }
/* 22:   */  
/* 23:   */  static native void nglPointParameteriNV(int paramInt1, int paramInt2, long paramLong);
/* 24:   */  
/* 25:25 */  public static void glPointParameterNV(int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 26:26 */    long function_pointer = caps.glPointParameterivNV;
/* 27:27 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 28:28 */    BufferChecks.checkBuffer(params, 4);
/* 29:29 */    nglPointParameterivNV(pname, MemoryUtil.getAddress(params), function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglPointParameterivNV(int paramInt, long paramLong1, long paramLong2);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPointSprite
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */