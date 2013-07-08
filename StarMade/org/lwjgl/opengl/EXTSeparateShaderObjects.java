/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 12:   */public final class EXTSeparateShaderObjects
/* 13:   */{
/* 14:   */  public static final int GL_ACTIVE_PROGRAM_EXT = 35725;
/* 15:   */  
/* 16:   */  public static void glUseShaderProgramEXT(int type, int program)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glUseShaderProgramEXT;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    nglUseShaderProgramEXT(type, program, function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:   */  static native void nglUseShaderProgramEXT(int paramInt1, int paramInt2, long paramLong);
/* 25:   */  
/* 26:26 */  public static void glActiveProgramEXT(int program) { ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glActiveProgramEXT;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglActiveProgramEXT(program, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglActiveProgramEXT(int paramInt, long paramLong);
/* 33:   */  
/* 34:34 */  public static int glCreateShaderProgramEXT(int type, ByteBuffer string) { ContextCapabilities caps = GLContext.getCapabilities();
/* 35:35 */    long function_pointer = caps.glCreateShaderProgramEXT;
/* 36:36 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 37:37 */    BufferChecks.checkDirect(string);
/* 38:38 */    BufferChecks.checkNullTerminated(string);
/* 39:39 */    int __result = nglCreateShaderProgramEXT(type, MemoryUtil.getAddress(string), function_pointer);
/* 40:40 */    return __result;
/* 41:   */  }
/* 42:   */  
/* 43:   */  static native int nglCreateShaderProgramEXT(int paramInt, long paramLong1, long paramLong2);
/* 44:   */  
/* 45:   */  public static int glCreateShaderProgramEXT(int type, CharSequence string) {
/* 46:46 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 47:47 */    long function_pointer = caps.glCreateShaderProgramEXT;
/* 48:48 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 49:49 */    int __result = nglCreateShaderProgramEXT(type, APIUtil.getBufferNT(caps, string), function_pointer);
/* 50:50 */    return __result;
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTSeparateShaderObjects
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */