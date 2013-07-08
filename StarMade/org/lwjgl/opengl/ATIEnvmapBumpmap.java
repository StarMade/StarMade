/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/*  9:   */public final class ATIEnvmapBumpmap
/* 10:   */{
/* 11:   */  public static final int GL_BUMP_ROT_MATRIX_ATI = 34677;
/* 12:   */  public static final int GL_BUMP_ROT_MATRIX_SIZE_ATI = 34678;
/* 13:   */  public static final int GL_BUMP_NUM_TEX_UNITS_ATI = 34679;
/* 14:   */  public static final int GL_BUMP_TEX_UNITS_ATI = 34680;
/* 15:   */  public static final int GL_DUDV_ATI = 34681;
/* 16:   */  public static final int GL_DU8DV8_ATI = 34682;
/* 17:   */  public static final int GL_BUMP_ENVMAP_ATI = 34683;
/* 18:   */  public static final int GL_BUMP_TARGET_ATI = 34684;
/* 19:   */  
/* 20:   */  public static void glTexBumpParameterATI(int pname, FloatBuffer param)
/* 21:   */  {
/* 22:22 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 23:23 */    long function_pointer = caps.glTexBumpParameterfvATI;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    BufferChecks.checkBuffer(param, 4);
/* 26:26 */    nglTexBumpParameterfvATI(pname, MemoryUtil.getAddress(param), function_pointer);
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native void nglTexBumpParameterfvATI(int paramInt, long paramLong1, long paramLong2);
/* 30:   */  
/* 31:31 */  public static void glTexBumpParameterATI(int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 32:32 */    long function_pointer = caps.glTexBumpParameterivATI;
/* 33:33 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 34:34 */    BufferChecks.checkBuffer(param, 4);
/* 35:35 */    nglTexBumpParameterivATI(pname, MemoryUtil.getAddress(param), function_pointer);
/* 36:   */  }
/* 37:   */  
/* 38:   */  static native void nglTexBumpParameterivATI(int paramInt, long paramLong1, long paramLong2);
/* 39:   */  
/* 40:40 */  public static void glGetTexBumpParameterATI(int pname, FloatBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 41:41 */    long function_pointer = caps.glGetTexBumpParameterfvATI;
/* 42:42 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 43:43 */    BufferChecks.checkBuffer(param, 4);
/* 44:44 */    nglGetTexBumpParameterfvATI(pname, MemoryUtil.getAddress(param), function_pointer);
/* 45:   */  }
/* 46:   */  
/* 47:   */  static native void nglGetTexBumpParameterfvATI(int paramInt, long paramLong1, long paramLong2);
/* 48:   */  
/* 49:49 */  public static void glGetTexBumpParameterATI(int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 50:50 */    long function_pointer = caps.glGetTexBumpParameterivATI;
/* 51:51 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 52:52 */    BufferChecks.checkBuffer(param, 4);
/* 53:53 */    nglGetTexBumpParameterivATI(pname, MemoryUtil.getAddress(param), function_pointer);
/* 54:   */  }
/* 55:   */  
/* 56:   */  static native void nglGetTexBumpParameterivATI(int paramInt, long paramLong1, long paramLong2);
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIEnvmapBumpmap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */