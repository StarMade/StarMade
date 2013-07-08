/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class GREMEDYStringMarker
/* 10:   */{
/* 11:   */  public static void glStringMarkerGREMEDY(ByteBuffer string)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glStringMarkerGREMEDY;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    BufferChecks.checkDirect(string);
/* 17:17 */    nglStringMarkerGREMEDY(string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/* 18:   */  }
/* 19:   */  
/* 20:   */  static native void nglStringMarkerGREMEDY(int paramInt, long paramLong1, long paramLong2);
/* 21:   */  
/* 22:   */  public static void glStringMarkerGREMEDY(CharSequence string) {
/* 23:23 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 24:24 */    long function_pointer = caps.glStringMarkerGREMEDY;
/* 25:25 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 26:26 */    nglStringMarkerGREMEDY(string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/* 27:   */  }
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GREMEDYStringMarker
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */