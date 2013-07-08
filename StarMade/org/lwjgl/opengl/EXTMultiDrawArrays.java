/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class EXTMultiDrawArrays
/* 10:   */{
/* 11:   */  public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glMultiDrawArraysEXT;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    BufferChecks.checkDirect(piFirst);
/* 17:17 */    BufferChecks.checkBuffer(piCount, piFirst.remaining());
/* 18:18 */    nglMultiDrawArraysEXT(mode, MemoryUtil.getAddress(piFirst), MemoryUtil.getAddress(piCount), piFirst.remaining(), function_pointer);
/* 19:   */  }
/* 20:   */  
/* 21:   */  static native void nglMultiDrawArraysEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/* 22:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTMultiDrawArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */