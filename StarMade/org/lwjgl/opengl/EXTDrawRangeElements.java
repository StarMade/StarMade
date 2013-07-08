/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import java.nio.ShortBuffer;
/*  6:   */import org.lwjgl.BufferChecks;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/*  9:   */public final class EXTDrawRangeElements
/* 10:   */{
/* 11:   */  public static final int GL_MAX_ELEMENTS_VERTICES_EXT = 33000;
/* 12:   */  public static final int GL_MAX_ELEMENTS_INDICES_EXT = 33001;
/* 13:   */  
/* 14:   */  public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices)
/* 15:   */  {
/* 16:16 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 17:17 */    long function_pointer = caps.glDrawRangeElementsEXT;
/* 18:18 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 19:19 */    GLChecks.ensureElementVBOdisabled(caps);
/* 20:20 */    BufferChecks.checkDirect(pIndices);
/* 21:21 */    nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), 5121, MemoryUtil.getAddress(pIndices), function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:24 */  public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 25:25 */    long function_pointer = caps.glDrawRangeElementsEXT;
/* 26:26 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    GLChecks.ensureElementVBOdisabled(caps);
/* 28:28 */    BufferChecks.checkDirect(pIndices);
/* 29:29 */    nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), 5125, MemoryUtil.getAddress(pIndices), function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:32 */  public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 33:33 */    long function_pointer = caps.glDrawRangeElementsEXT;
/* 34:34 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 35:35 */    GLChecks.ensureElementVBOdisabled(caps);
/* 36:36 */    BufferChecks.checkDirect(pIndices);
/* 37:37 */    nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), 5123, MemoryUtil.getAddress(pIndices), function_pointer); }
/* 38:   */  
/* 39:   */  static native void nglDrawRangeElementsEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 40:   */  
/* 41:41 */  public static void glDrawRangeElementsEXT(int mode, int start, int end, int pIndices_count, int type, long pIndices_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glDrawRangeElementsEXT;
/* 43:43 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    GLChecks.ensureElementVBOenabled(caps);
/* 45:45 */    nglDrawRangeElementsEXTBO(mode, start, end, pIndices_count, type, pIndices_buffer_offset, function_pointer);
/* 46:   */  }
/* 47:   */  
/* 48:   */  static native void nglDrawRangeElementsEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDrawRangeElements
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */