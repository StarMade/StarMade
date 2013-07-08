/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 12:   */public final class CL12GL
/* 13:   */{
/* 14:   */  public static final int CL_GL_OBJECT_TEXTURE2D_ARRAY = 8206;
/* 15:   */  public static final int CL_GL_OBJECT_TEXTURE1D = 8207;
/* 16:   */  public static final int CL_GL_OBJECT_TEXTURE1D_ARRAY = 8208;
/* 17:   */  public static final int CL_GL_OBJECT_TEXTURE_BUFFER = 8209;
/* 18:   */  
/* 19:   */  public static CLMem clCreateFromGLTexture(CLContext context, long flags, int target, int miplevel, int texture, IntBuffer errcode_ret)
/* 20:   */  {
/* 21:21 */    long function_pointer = CLCapabilities.clCreateFromGLTexture;
/* 22:22 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 23:23 */    if (errcode_ret != null)
/* 24:24 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 25:25 */    CLMem __result = new CLMem(nclCreateFromGLTexture(context.getPointer(), flags, target, miplevel, texture, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 26:26 */    return __result;
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native long nclCreateFromGLTexture(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong3, long paramLong4);
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL12GL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */