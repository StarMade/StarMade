/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 19:   */public final class EXTBindableUniform
/* 20:   */{
/* 21:   */  public static final int GL_MAX_VERTEX_BINDABLE_UNIFORMS_EXT = 36322;
/* 22:   */  public static final int GL_MAX_FRAGMENT_BINDABLE_UNIFORMS_EXT = 36323;
/* 23:   */  public static final int GL_MAX_GEOMETRY_BINDABLE_UNIFORMS_EXT = 36324;
/* 24:   */  public static final int GL_MAX_BINDABLE_UNIFORM_SIZE_EXT = 36333;
/* 25:   */  public static final int GL_UNIFORM_BUFFER_BINDING_EXT = 36335;
/* 26:   */  public static final int GL_UNIFORM_BUFFER_EXT = 36334;
/* 27:   */  
/* 28:   */  public static void glUniformBufferEXT(int program, int location, int buffer)
/* 29:   */  {
/* 30:30 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glUniformBufferEXT;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    nglUniformBufferEXT(program, location, buffer, function_pointer);
/* 34:   */  }
/* 35:   */  
/* 36:   */  static native void nglUniformBufferEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 37:   */  
/* 38:38 */  public static int glGetUniformBufferSizeEXT(int program, int location) { ContextCapabilities caps = GLContext.getCapabilities();
/* 39:39 */    long function_pointer = caps.glGetUniformBufferSizeEXT;
/* 40:40 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 41:41 */    int __result = nglGetUniformBufferSizeEXT(program, location, function_pointer);
/* 42:42 */    return __result;
/* 43:   */  }
/* 44:   */  
/* 45:   */  static native int nglGetUniformBufferSizeEXT(int paramInt1, int paramInt2, long paramLong);
/* 46:   */  
/* 47:47 */  public static long glGetUniformOffsetEXT(int program, int location) { ContextCapabilities caps = GLContext.getCapabilities();
/* 48:48 */    long function_pointer = caps.glGetUniformOffsetEXT;
/* 49:49 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 50:50 */    long __result = nglGetUniformOffsetEXT(program, location, function_pointer);
/* 51:51 */    return __result;
/* 52:   */  }
/* 53:   */  
/* 54:   */  static native long nglGetUniformOffsetEXT(int paramInt1, int paramInt2, long paramLong);
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBindableUniform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */