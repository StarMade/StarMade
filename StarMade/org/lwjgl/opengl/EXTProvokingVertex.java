/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 17:   */public final class EXTProvokingVertex
/* 18:   */{
/* 19:   */  public static final int GL_FIRST_VERTEX_CONVENTION_EXT = 36429;
/* 20:   */  public static final int GL_LAST_VERTEX_CONVENTION_EXT = 36430;
/* 21:   */  public static final int GL_PROVOKING_VERTEX_EXT = 36431;
/* 22:   */  public static final int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION_EXT = 36428;
/* 23:   */  
/* 24:   */  public static void glProvokingVertexEXT(int mode)
/* 25:   */  {
/* 26:26 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glProvokingVertexEXT;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglProvokingVertexEXT(mode, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglProvokingVertexEXT(int paramInt, long paramLong);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTProvokingVertex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */