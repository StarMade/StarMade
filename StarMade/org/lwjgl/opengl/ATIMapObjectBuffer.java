/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.ByteOrder;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.LWJGLUtil;
/*  7:   */
/* 32:   */public final class ATIMapObjectBuffer
/* 33:   */{
/* 34:   */  public static ByteBuffer glMapObjectBufferATI(int buffer, ByteBuffer old_buffer)
/* 35:   */  {
/* 36:36 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 37:37 */    long function_pointer = caps.glMapObjectBufferATI;
/* 38:38 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 39:39 */    if (old_buffer != null)
/* 40:40 */      BufferChecks.checkDirect(old_buffer);
/* 41:41 */    ByteBuffer __result = nglMapObjectBufferATI(buffer, GLChecks.getBufferObjectSizeATI(caps, buffer), old_buffer, function_pointer);
/* 42:42 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 43:   */  }
/* 44:   */  
/* 66:   */  public static ByteBuffer glMapObjectBufferATI(int buffer, long length, ByteBuffer old_buffer)
/* 67:   */  {
/* 68:68 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 69:69 */    long function_pointer = caps.glMapObjectBufferATI;
/* 70:70 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 71:71 */    if (old_buffer != null)
/* 72:72 */      BufferChecks.checkDirect(old_buffer);
/* 73:73 */    ByteBuffer __result = nglMapObjectBufferATI(buffer, length, old_buffer, function_pointer);
/* 74:74 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 75:   */  }
/* 76:   */  
/* 77:   */  static native ByteBuffer nglMapObjectBufferATI(int paramInt, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/* 78:   */  
/* 79:79 */  public static void glUnmapObjectBufferATI(int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 80:80 */    long function_pointer = caps.glUnmapObjectBufferATI;
/* 81:81 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 82:82 */    nglUnmapObjectBufferATI(buffer, function_pointer);
/* 83:   */  }
/* 84:   */  
/* 85:   */  static native void nglUnmapObjectBufferATI(int paramInt, long paramLong);
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIMapObjectBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */