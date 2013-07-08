/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class NVTextureBarrier
/* 10:   */{
/* 11:   */  public static void glTextureBarrierNV()
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glTextureBarrierNV;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglTextureBarrierNV(function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglTextureBarrierNV(long paramLong);
/* 20:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTextureBarrier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */