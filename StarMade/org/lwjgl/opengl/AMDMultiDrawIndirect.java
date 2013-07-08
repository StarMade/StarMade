/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/*  9:   */public final class AMDMultiDrawIndirect
/* 10:   */{
/* 11:   */  public static void glMultiDrawArraysIndirectAMD(int mode, ByteBuffer indirect, int primcount, int stride)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glMultiDrawArraysIndirectAMD;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 17:17 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 16 : stride) * primcount);
/* 18:18 */    nglMultiDrawArraysIndirectAMD(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer); }
/* 19:   */  
/* 20:   */  static native void nglMultiDrawArraysIndirectAMD(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/* 21:   */  
/* 22:22 */  public static void glMultiDrawArraysIndirectAMD(int mode, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 23:23 */    long function_pointer = caps.glMultiDrawArraysIndirectAMD;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    GLChecks.ensureIndirectBOenabled(caps);
/* 26:26 */    nglMultiDrawArraysIndirectAMDBO(mode, indirect_buffer_offset, primcount, stride, function_pointer);
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native void nglMultiDrawArraysIndirectAMDBO(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/* 30:   */  
/* 31:   */  public static void glMultiDrawArraysIndirectAMD(int mode, IntBuffer indirect, int primcount, int stride) {
/* 32:32 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 33:33 */    long function_pointer = caps.glMultiDrawArraysIndirectAMD;
/* 34:34 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 35:35 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 36:36 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 4 : stride >> 2) * primcount);
/* 37:37 */    nglMultiDrawArraysIndirectAMD(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static void glMultiDrawElementsIndirectAMD(int mode, int type, ByteBuffer indirect, int primcount, int stride) {
/* 41:41 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glMultiDrawElementsIndirectAMD;
/* 43:43 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 45:45 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 20 : stride) * primcount);
/* 46:46 */    nglMultiDrawElementsIndirectAMD(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer); }
/* 47:   */  
/* 48:   */  static native void nglMultiDrawElementsIndirectAMD(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/* 49:   */  
/* 50:50 */  public static void glMultiDrawElementsIndirectAMD(int mode, int type, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 51:51 */    long function_pointer = caps.glMultiDrawElementsIndirectAMD;
/* 52:52 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 53:53 */    GLChecks.ensureIndirectBOenabled(caps);
/* 54:54 */    nglMultiDrawElementsIndirectAMDBO(mode, type, indirect_buffer_offset, primcount, stride, function_pointer);
/* 55:   */  }
/* 56:   */  
/* 57:   */  static native void nglMultiDrawElementsIndirectAMDBO(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/* 58:   */  
/* 59:   */  public static void glMultiDrawElementsIndirectAMD(int mode, int type, IntBuffer indirect, int primcount, int stride) {
/* 60:60 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 61:61 */    long function_pointer = caps.glMultiDrawElementsIndirectAMD;
/* 62:62 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 63:63 */    GLChecks.ensureIndirectBOdisabled(caps);
/* 64:64 */    BufferChecks.checkBuffer(indirect, (stride == 0 ? 5 : stride >> 2) * primcount);
/* 65:65 */    nglMultiDrawElementsIndirectAMD(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDMultiDrawIndirect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */