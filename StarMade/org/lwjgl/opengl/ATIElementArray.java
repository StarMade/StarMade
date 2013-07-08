/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import java.nio.ShortBuffer;
/*  6:   */import org.lwjgl.BufferChecks;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/*  9:   */public final class ATIElementArray
/* 10:   */{
/* 11:   */  public static final int GL_ELEMENT_ARRAY_ATI = 34664;
/* 12:   */  public static final int GL_ELEMENT_ARRAY_TYPE_ATI = 34665;
/* 13:   */  public static final int GL_ELEMENT_ARRAY_POINTER_ATI = 34666;
/* 14:   */  
/* 15:   */  public static void glElementPointerATI(ByteBuffer pPointer)
/* 16:   */  {
/* 17:17 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 18:18 */    long function_pointer = caps.glElementPointerATI;
/* 19:19 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 20:20 */    BufferChecks.checkDirect(pPointer);
/* 21:21 */    nglElementPointerATI(5121, MemoryUtil.getAddress(pPointer), function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:24 */  public static void glElementPointerATI(IntBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 25:25 */    long function_pointer = caps.glElementPointerATI;
/* 26:26 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    BufferChecks.checkDirect(pPointer);
/* 28:28 */    nglElementPointerATI(5125, MemoryUtil.getAddress(pPointer), function_pointer);
/* 29:   */  }
/* 30:   */  
/* 31:31 */  public static void glElementPointerATI(ShortBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 32:32 */    long function_pointer = caps.glElementPointerATI;
/* 33:33 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 34:34 */    BufferChecks.checkDirect(pPointer);
/* 35:35 */    nglElementPointerATI(5123, MemoryUtil.getAddress(pPointer), function_pointer);
/* 36:   */  }
/* 37:   */  
/* 38:   */  static native void nglElementPointerATI(int paramInt, long paramLong1, long paramLong2);
/* 39:   */  
/* 40:40 */  public static void glDrawElementArrayATI(int mode, int count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 41:41 */    long function_pointer = caps.glDrawElementArrayATI;
/* 42:42 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 43:43 */    nglDrawElementArrayATI(mode, count, function_pointer);
/* 44:   */  }
/* 45:   */  
/* 46:   */  static native void nglDrawElementArrayATI(int paramInt1, int paramInt2, long paramLong);
/* 47:   */  
/* 48:48 */  public static void glDrawRangeElementArrayATI(int mode, int start, int end, int count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 49:49 */    long function_pointer = caps.glDrawRangeElementArrayATI;
/* 50:50 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 51:51 */    nglDrawRangeElementArrayATI(mode, start, end, count, function_pointer);
/* 52:   */  }
/* 53:   */  
/* 54:   */  static native void nglDrawRangeElementArrayATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIElementArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */