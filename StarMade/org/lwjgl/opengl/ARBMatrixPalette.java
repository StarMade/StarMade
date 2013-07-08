/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import java.nio.ShortBuffer;
/*  6:   */import org.lwjgl.LWJGLUtil;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/*  9:   */public final class ARBMatrixPalette
/* 10:   */{
/* 11:   */  public static final int GL_MATRIX_PALETTE_ARB = 34880;
/* 12:   */  public static final int GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB = 34881;
/* 13:   */  public static final int GL_MAX_PALETTE_MATRICES_ARB = 34882;
/* 14:   */  public static final int GL_CURRENT_PALETTE_MATRIX_ARB = 34883;
/* 15:   */  public static final int GL_MATRIX_INDEX_ARRAY_ARB = 34884;
/* 16:   */  public static final int GL_CURRENT_MATRIX_INDEX_ARB = 34885;
/* 17:   */  public static final int GL_MATRIX_INDEX_ARRAY_SIZE_ARB = 34886;
/* 18:   */  public static final int GL_MATRIX_INDEX_ARRAY_TYPE_ARB = 34887;
/* 19:   */  public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_ARB = 34888;
/* 20:   */  public static final int GL_MATRIX_INDEX_ARRAY_POINTER_ARB = 34889;
/* 21:   */  
/* 22:   */  public static void glCurrentPaletteMatrixARB(int index)
/* 23:   */  {
/* 24:24 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 25:25 */    long function_pointer = caps.glCurrentPaletteMatrixARB;
/* 26:26 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    nglCurrentPaletteMatrixARB(index, function_pointer);
/* 28:   */  }
/* 29:   */  
/* 30:   */  static native void nglCurrentPaletteMatrixARB(int paramInt, long paramLong);
/* 31:   */  
/* 32:32 */  public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 33:33 */    long function_pointer = caps.glMatrixIndexPointerARB;
/* 34:34 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 35:35 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 36:36 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 37:37 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
/* 38:38 */    nglMatrixIndexPointerARB(size, 5121, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:41 */  public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glMatrixIndexPointerARB;
/* 43:43 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 45:45 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 46:46 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
/* 47:47 */    nglMatrixIndexPointerARB(size, 5125, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/* 48:   */  }
/* 49:   */  
/* 50:50 */  public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 51:51 */    long function_pointer = caps.glMatrixIndexPointerARB;
/* 52:52 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 53:53 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 54:54 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 55:55 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
/* 56:56 */    nglMatrixIndexPointerARB(size, 5123, stride, MemoryUtil.getAddress(pPointer), function_pointer); }
/* 57:   */  
/* 58:   */  static native void nglMatrixIndexPointerARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 59:   */  
/* 60:60 */  public static void glMatrixIndexPointerARB(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 61:61 */    long function_pointer = caps.glMatrixIndexPointerARB;
/* 62:62 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 63:63 */    GLChecks.ensureArrayVBOenabled(caps);
/* 64:64 */    nglMatrixIndexPointerARBBO(size, type, stride, pPointer_buffer_offset, function_pointer);
/* 65:   */  }
/* 66:   */  
/* 67:   */  static native void nglMatrixIndexPointerARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 68:   */  
/* 69:69 */  public static void glMatrixIndexuARB(ByteBuffer pIndices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    long function_pointer = caps.glMatrixIndexubvARB;
/* 71:71 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 72:72 */    org.lwjgl.BufferChecks.checkDirect(pIndices);
/* 73:73 */    nglMatrixIndexubvARB(pIndices.remaining(), MemoryUtil.getAddress(pIndices), function_pointer);
/* 74:   */  }
/* 75:   */  
/* 76:   */  static native void nglMatrixIndexubvARB(int paramInt, long paramLong1, long paramLong2);
/* 77:   */  
/* 78:78 */  public static void glMatrixIndexuARB(ShortBuffer pIndices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 79:79 */    long function_pointer = caps.glMatrixIndexusvARB;
/* 80:80 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 81:81 */    org.lwjgl.BufferChecks.checkDirect(pIndices);
/* 82:82 */    nglMatrixIndexusvARB(pIndices.remaining(), MemoryUtil.getAddress(pIndices), function_pointer);
/* 83:   */  }
/* 84:   */  
/* 85:   */  static native void nglMatrixIndexusvARB(int paramInt, long paramLong1, long paramLong2);
/* 86:   */  
/* 87:87 */  public static void glMatrixIndexuARB(IntBuffer pIndices) { ContextCapabilities caps = GLContext.getCapabilities();
/* 88:88 */    long function_pointer = caps.glMatrixIndexuivARB;
/* 89:89 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 90:90 */    org.lwjgl.BufferChecks.checkDirect(pIndices);
/* 91:91 */    nglMatrixIndexuivARB(pIndices.remaining(), MemoryUtil.getAddress(pIndices), function_pointer);
/* 92:   */  }
/* 93:   */  
/* 94:   */  static native void nglMatrixIndexuivARB(int paramInt, long paramLong1, long paramLong2);
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMatrixPalette
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */