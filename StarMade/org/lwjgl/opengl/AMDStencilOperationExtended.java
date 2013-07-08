/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 18:   */public final class AMDStencilOperationExtended
/* 19:   */{
/* 20:   */  public static final int GL_SET_AMD = 34634;
/* 21:   */  public static final int GL_AND = 5377;
/* 22:   */  public static final int GL_XOR = 5382;
/* 23:   */  public static final int GL_OR = 5383;
/* 24:   */  public static final int GL_NOR = 5384;
/* 25:   */  public static final int GL_EQUIV = 5385;
/* 26:   */  public static final int GL_NAND = 5390;
/* 27:   */  public static final int GL_REPLACE_VALUE_AMD = 34635;
/* 28:   */  public static final int GL_STENCIL_OP_VALUE_AMD = 34636;
/* 29:   */  public static final int GL_STENCIL_BACK_OP_VALUE_AMD = 34637;
/* 30:   */  
/* 31:   */  public static void glStencilOpValueAMD(int face, int value)
/* 32:   */  {
/* 33:33 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 34:34 */    long function_pointer = caps.glStencilOpValueAMD;
/* 35:35 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 36:36 */    nglStencilOpValueAMD(face, value, function_pointer);
/* 37:   */  }
/* 38:   */  
/* 39:   */  static native void nglStencilOpValueAMD(int paramInt1, int paramInt2, long paramLong);
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDStencilOperationExtended
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */