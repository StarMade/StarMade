/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class GREMEDYFrameTerminator
/* 10:   */{
/* 11:   */  public static void glFrameTerminatorGREMEDY()
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glFrameTerminatorGREMEDY;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglFrameTerminatorGREMEDY(function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglFrameTerminatorGREMEDY(long paramLong);
/* 20:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GREMEDYFrameTerminator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */