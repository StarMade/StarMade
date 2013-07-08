/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class NVCopyImage
/* 10:   */{
/* 11:   */  public static void glCopyImageSubDataNV(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int width, int height, int depth)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glCopyImageSubDataNV;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglCopyImageSubDataNV(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, width, height, depth, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglCopyImageSubDataNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, long paramLong);
/* 20:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVCopyImage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */