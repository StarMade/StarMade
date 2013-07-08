/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.DoubleBuffer;
/*  5:   */import java.nio.FloatBuffer;
/*  6:   */import org.lwjgl.LWJGLUtil;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/*  9:   */public final class EXTSecondaryColor
/* 10:   */{
/* 11:   */  public static final int GL_COLOR_SUM_EXT = 33880;
/* 12:   */  public static final int GL_CURRENT_SECONDARY_COLOR_EXT = 33881;
/* 13:   */  public static final int GL_SECONDARY_COLOR_ARRAY_SIZE_EXT = 33882;
/* 14:   */  public static final int GL_SECONDARY_COLOR_ARRAY_TYPE_EXT = 33883;
/* 15:   */  public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT = 33884;
/* 16:   */  public static final int GL_SECONDARY_COLOR_ARRAY_POINTER_EXT = 33885;
/* 17:   */  public static final int GL_SECONDARY_COLOR_ARRAY_EXT = 33886;
/* 18:   */  
/* 19:   */  public static void glSecondaryColor3bEXT(byte red, byte green, byte blue)
/* 20:   */  {
/* 21:21 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glSecondaryColor3bEXT;
/* 23:23 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    nglSecondaryColor3bEXT(red, green, blue, function_pointer);
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native void nglSecondaryColor3bEXT(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/* 28:   */  
/* 29:29 */  public static void glSecondaryColor3fEXT(float red, float green, float blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glSecondaryColor3fEXT;
/* 31:31 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglSecondaryColor3fEXT(red, green, blue, function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglSecondaryColor3fEXT(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 36:   */  
/* 37:37 */  public static void glSecondaryColor3dEXT(double red, double green, double blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 38:38 */    long function_pointer = caps.glSecondaryColor3dEXT;
/* 39:39 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    nglSecondaryColor3dEXT(red, green, blue, function_pointer);
/* 41:   */  }
/* 42:   */  
/* 43:   */  static native void nglSecondaryColor3dEXT(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 44:   */  
/* 45:45 */  public static void glSecondaryColor3ubEXT(byte red, byte green, byte blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 46:46 */    long function_pointer = caps.glSecondaryColor3ubEXT;
/* 47:47 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 48:48 */    nglSecondaryColor3ubEXT(red, green, blue, function_pointer);
/* 49:   */  }
/* 50:   */  
/* 51:   */  static native void nglSecondaryColor3ubEXT(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/* 52:   */  
/* 53:53 */  public static void glSecondaryColorPointerEXT(int size, int stride, DoubleBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 54:54 */    long function_pointer = caps.glSecondaryColorPointerEXT;
/* 55:55 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 56:56 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 57:57 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 58:58 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = pPointer;
/* 59:59 */    nglSecondaryColorPointerEXT(size, 5130, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/* 60:   */  }
/* 61:   */  
/* 62:62 */  public static void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 63:63 */    long function_pointer = caps.glSecondaryColorPointerEXT;
/* 64:64 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 65:65 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 66:66 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 67:67 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = pPointer;
/* 68:68 */    nglSecondaryColorPointerEXT(size, 5126, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/* 69:   */  }
/* 70:   */  
/* 71:71 */  public static void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 72:72 */    long function_pointer = caps.glSecondaryColorPointerEXT;
/* 73:73 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 74:74 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 75:75 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 76:76 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = pPointer;
/* 77:77 */    nglSecondaryColorPointerEXT(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(pPointer), function_pointer); }
/* 78:   */  
/* 79:   */  static native void nglSecondaryColorPointerEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 80:   */  
/* 81:81 */  public static void glSecondaryColorPointerEXT(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 82:82 */    long function_pointer = caps.glSecondaryColorPointerEXT;
/* 83:83 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 84:84 */    GLChecks.ensureArrayVBOenabled(caps);
/* 85:85 */    nglSecondaryColorPointerEXTBO(size, type, stride, pPointer_buffer_offset, function_pointer);
/* 86:   */  }
/* 87:   */  
/* 88:   */  static native void nglSecondaryColorPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTSecondaryColor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */