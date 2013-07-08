/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.DoubleBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.LWJGLUtil;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  12:    */public final class EXTVertexAttrib64bit
/*  13:    */{
/*  14:    */  public static final int GL_DOUBLE_VEC2_EXT = 36860;
/*  15:    */  public static final int GL_DOUBLE_VEC3_EXT = 36861;
/*  16:    */  public static final int GL_DOUBLE_VEC4_EXT = 36862;
/*  17:    */  public static final int GL_DOUBLE_MAT2_EXT = 36678;
/*  18:    */  public static final int GL_DOUBLE_MAT3_EXT = 36679;
/*  19:    */  public static final int GL_DOUBLE_MAT4_EXT = 36680;
/*  20:    */  public static final int GL_DOUBLE_MAT2x3_EXT = 36681;
/*  21:    */  public static final int GL_DOUBLE_MAT2x4_EXT = 36682;
/*  22:    */  public static final int GL_DOUBLE_MAT3x2_EXT = 36683;
/*  23:    */  public static final int GL_DOUBLE_MAT3x4_EXT = 36684;
/*  24:    */  public static final int GL_DOUBLE_MAT4x2_EXT = 36685;
/*  25:    */  public static final int GL_DOUBLE_MAT4x3_EXT = 36686;
/*  26:    */  
/*  27:    */  public static void glVertexAttribL1dEXT(int index, double x)
/*  28:    */  {
/*  29: 29 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  30: 30 */    long function_pointer = caps.glVertexAttribL1dEXT;
/*  31: 31 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  32: 32 */    nglVertexAttribL1dEXT(index, x, function_pointer);
/*  33:    */  }
/*  34:    */  
/*  35:    */  static native void nglVertexAttribL1dEXT(int paramInt, double paramDouble, long paramLong);
/*  36:    */  
/*  37: 37 */  public static void glVertexAttribL2dEXT(int index, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  38: 38 */    long function_pointer = caps.glVertexAttribL2dEXT;
/*  39: 39 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  40: 40 */    nglVertexAttribL2dEXT(index, x, y, function_pointer);
/*  41:    */  }
/*  42:    */  
/*  43:    */  static native void nglVertexAttribL2dEXT(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*  44:    */  
/*  45: 45 */  public static void glVertexAttribL3dEXT(int index, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  46: 46 */    long function_pointer = caps.glVertexAttribL3dEXT;
/*  47: 47 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  48: 48 */    nglVertexAttribL3dEXT(index, x, y, z, function_pointer);
/*  49:    */  }
/*  50:    */  
/*  51:    */  static native void nglVertexAttribL3dEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  52:    */  
/*  53: 53 */  public static void glVertexAttribL4dEXT(int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  54: 54 */    long function_pointer = caps.glVertexAttribL4dEXT;
/*  55: 55 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  56: 56 */    nglVertexAttribL4dEXT(index, x, y, z, w, function_pointer);
/*  57:    */  }
/*  58:    */  
/*  59:    */  static native void nglVertexAttribL4dEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*  60:    */  
/*  61: 61 */  public static void glVertexAttribL1EXT(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  62: 62 */    long function_pointer = caps.glVertexAttribL1dvEXT;
/*  63: 63 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  64: 64 */    BufferChecks.checkBuffer(v, 1);
/*  65: 65 */    nglVertexAttribL1dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*  66:    */  }
/*  67:    */  
/*  68:    */  static native void nglVertexAttribL1dvEXT(int paramInt, long paramLong1, long paramLong2);
/*  69:    */  
/*  70: 70 */  public static void glVertexAttribL2EXT(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glVertexAttribL2dvEXT;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    BufferChecks.checkBuffer(v, 2);
/*  74: 74 */    nglVertexAttribL2dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*  75:    */  }
/*  76:    */  
/*  77:    */  static native void nglVertexAttribL2dvEXT(int paramInt, long paramLong1, long paramLong2);
/*  78:    */  
/*  79: 79 */  public static void glVertexAttribL3EXT(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glVertexAttribL3dvEXT;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    BufferChecks.checkBuffer(v, 3);
/*  83: 83 */    nglVertexAttribL3dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86:    */  static native void nglVertexAttribL3dvEXT(int paramInt, long paramLong1, long paramLong2);
/*  87:    */  
/*  88: 88 */  public static void glVertexAttribL4EXT(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glVertexAttribL4dvEXT;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    BufferChecks.checkBuffer(v, 4);
/*  92: 92 */    nglVertexAttribL4dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*  93:    */  }
/*  94:    */  
/*  95:    */  static native void nglVertexAttribL4dvEXT(int paramInt, long paramLong1, long paramLong2);
/*  96:    */  
/*  97: 97 */  public static void glVertexAttribLPointerEXT(int index, int size, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  98: 98 */    long function_pointer = caps.glVertexAttribLPointerEXT;
/*  99: 99 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 100:100 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 101:101 */    BufferChecks.checkDirect(pointer);
/* 102:102 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = pointer;
/* 103:103 */    nglVertexAttribLPointerEXT(index, size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/* 104:    */  
/* 105:    */  static native void nglVertexAttribLPointerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 106:    */  
/* 107:107 */  public static void glVertexAttribLPointerEXT(int index, int size, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 108:108 */    long function_pointer = caps.glVertexAttribLPointerEXT;
/* 109:109 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 110:110 */    GLChecks.ensureArrayVBOenabled(caps);
/* 111:111 */    nglVertexAttribLPointerEXTBO(index, size, 5130, stride, pointer_buffer_offset, function_pointer);
/* 112:    */  }
/* 113:    */  
/* 114:    */  static native void nglVertexAttribLPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 115:    */  
/* 116:116 */  public static void glGetVertexAttribLEXT(int index, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 117:117 */    long function_pointer = caps.glGetVertexAttribLdvEXT;
/* 118:118 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 119:119 */    BufferChecks.checkBuffer(params, 4);
/* 120:120 */    nglGetVertexAttribLdvEXT(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 121:    */  }
/* 122:    */  
/* 123:    */  static native void nglGetVertexAttribLdvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 124:    */  
/* 125:125 */  public static void glVertexArrayVertexAttribLOffsetEXT(int vaobj, int buffer, int index, int size, int type, int stride, long offset) { ARBVertexAttrib64bit.glVertexArrayVertexAttribLOffsetEXT(vaobj, buffer, index, size, type, stride, offset); }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTVertexAttrib64bit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */