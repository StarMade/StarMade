/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class NVDrawTexture
/* 10:   */{
/* 11:   */  public static void glDrawTextureNV(int texture, int sampler, float x0, float y0, float x1, float y1, float z, float s0, float t0, float s1, float t1)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glDrawTextureNV;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglDrawTextureNV(texture, sampler, x0, y0, x1, y1, z, s0, t0, s1, t1, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglDrawTextureNV(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, long paramLong);
/* 20:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVDrawTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */