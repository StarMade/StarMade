/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.FloatBuffer;
/*  5:   */import java.nio.IntBuffer;
/*  6:   */import org.lwjgl.BufferChecks;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/* 36:   */public final class NVExplicitMultisample
/* 37:   */{
/* 38:   */  public static final int GL_SAMPLE_POSITION_NV = 36432;
/* 39:   */  public static final int GL_SAMPLE_MASK_NV = 36433;
/* 40:   */  public static final int GL_SAMPLE_MASK_VALUE_NV = 36434;
/* 41:   */  public static final int GL_TEXTURE_BINDING_RENDERBUFFER_NV = 36435;
/* 42:   */  public static final int GL_TEXTURE_RENDERBUFFER_DATA_STORE_BINDING_NV = 36436;
/* 43:   */  public static final int GL_MAX_SAMPLE_MASK_WORDS_NV = 36441;
/* 44:   */  public static final int GL_TEXTURE_RENDERBUFFER_NV = 36437;
/* 45:   */  public static final int GL_SAMPLER_RENDERBUFFER_NV = 36438;
/* 46:   */  public static final int GL_INT_SAMPLER_RENDERBUFFER_NV = 36439;
/* 47:   */  public static final int GL_UNSIGNED_INT_SAMPLER_RENDERBUFFER_NV = 36440;
/* 48:   */  
/* 49:   */  public static void glGetBooleanIndexedEXT(int pname, int index, ByteBuffer data)
/* 50:   */  {
/* 51:51 */    EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index, data);
/* 52:   */  }
/* 53:   */  
/* 54:   */  public static boolean glGetBooleanIndexedEXT(int pname, int index)
/* 55:   */  {
/* 56:56 */    return EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index);
/* 57:   */  }
/* 58:   */  
/* 59:   */  public static void glGetIntegerIndexedEXT(int pname, int index, IntBuffer data) {
/* 60:60 */    EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index, data);
/* 61:   */  }
/* 62:   */  
/* 63:   */  public static int glGetIntegerIndexedEXT(int pname, int index)
/* 64:   */  {
/* 65:65 */    return EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index);
/* 66:   */  }
/* 67:   */  
/* 68:   */  public static void glGetMultisampleNV(int pname, int index, FloatBuffer val) {
/* 69:69 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    long function_pointer = caps.glGetMultisamplefvNV;
/* 71:71 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 72:72 */    BufferChecks.checkBuffer(val, 2);
/* 73:73 */    nglGetMultisamplefvNV(pname, index, MemoryUtil.getAddress(val), function_pointer);
/* 74:   */  }
/* 75:   */  
/* 76:   */  static native void nglGetMultisamplefvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 77:   */  
/* 78:78 */  public static void glSampleMaskIndexedNV(int index, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 79:79 */    long function_pointer = caps.glSampleMaskIndexedNV;
/* 80:80 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 81:81 */    nglSampleMaskIndexedNV(index, mask, function_pointer);
/* 82:   */  }
/* 83:   */  
/* 84:   */  static native void nglSampleMaskIndexedNV(int paramInt1, int paramInt2, long paramLong);
/* 85:   */  
/* 86:86 */  public static void glTexRenderbufferNV(int target, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 87:87 */    long function_pointer = caps.glTexRenderbufferNV;
/* 88:88 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 89:89 */    nglTexRenderbufferNV(target, renderbuffer, function_pointer);
/* 90:   */  }
/* 91:   */  
/* 92:   */  static native void nglTexRenderbufferNV(int paramInt1, int paramInt2, long paramLong);
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVExplicitMultisample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */