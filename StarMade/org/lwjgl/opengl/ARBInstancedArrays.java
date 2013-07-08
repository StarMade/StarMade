/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 13:   */public final class ARBInstancedArrays
/* 14:   */{
/* 15:   */  public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR_ARB = 35070;
/* 16:   */  
/* 17:   */  public static void glVertexAttribDivisorARB(int index, int divisor)
/* 18:   */  {
/* 19:19 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 20:20 */    long function_pointer = caps.glVertexAttribDivisorARB;
/* 21:21 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 22:22 */    nglVertexAttribDivisorARB(index, divisor, function_pointer);
/* 23:   */  }
/* 24:   */  
/* 25:   */  static native void nglVertexAttribDivisorARB(int paramInt1, int paramInt2, long paramLong);
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInstancedArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */