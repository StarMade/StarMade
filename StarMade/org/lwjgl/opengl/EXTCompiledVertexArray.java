/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class EXTCompiledVertexArray
/* 10:   */{
/* 11:   */  public static final int GL_ARRAY_ELEMENT_LOCK_FIRST_EXT = 33192;
/* 12:   */  public static final int GL_ARRAY_ELEMENT_LOCK_COUNT_EXT = 33193;
/* 13:   */  
/* 14:   */  public static void glLockArraysEXT(int first, int count)
/* 15:   */  {
/* 16:16 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 17:17 */    long function_pointer = caps.glLockArraysEXT;
/* 18:18 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 19:19 */    nglLockArraysEXT(first, count, function_pointer);
/* 20:   */  }
/* 21:   */  
/* 22:   */  static native void nglLockArraysEXT(int paramInt1, int paramInt2, long paramLong);
/* 23:   */  
/* 24:24 */  public static void glUnlockArraysEXT() { ContextCapabilities caps = GLContext.getCapabilities();
/* 25:25 */    long function_pointer = caps.glUnlockArraysEXT;
/* 26:26 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    nglUnlockArraysEXT(function_pointer);
/* 28:   */  }
/* 29:   */  
/* 30:   */  static native void nglUnlockArraysEXT(long paramLong);
/* 31:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTCompiledVertexArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */