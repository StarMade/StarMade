/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 19:   */public final class ARBFramebufferNoAttachments
/* 20:   */{
/* 21:   */  public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 37648;
/* 22:   */  public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 37649;
/* 23:   */  public static final int GL_FRAMEBUFFER_DEFAULT_LAYERS = 37650;
/* 24:   */  public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 37651;
/* 25:   */  public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;
/* 26:   */  public static final int GL_MAX_FRAMEBUFFER_WIDTH = 37653;
/* 27:   */  public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 37654;
/* 28:   */  public static final int GL_MAX_FRAMEBUFFER_LAYERS = 37655;
/* 29:   */  public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 37656;
/* 30:   */  
/* 31:   */  public static void glFramebufferParameteri(int target, int pname, int param)
/* 32:   */  {
/* 33:33 */    GL43.glFramebufferParameteri(target, pname, param);
/* 34:   */  }
/* 35:   */  
/* 36:   */  public static void glGetFramebufferParameter(int target, int pname, IntBuffer params) {
/* 37:37 */    GL43.glGetFramebufferParameter(target, pname, params);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static int glGetFramebufferParameteri(int target, int pname)
/* 41:   */  {
/* 42:42 */    return GL43.glGetFramebufferParameteri(target, pname);
/* 43:   */  }
/* 44:   */  
/* 45:   */  public static void glNamedFramebufferParameteriEXT(int framebuffer, int pname, int param) {
/* 46:46 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 47:47 */    long function_pointer = caps.glNamedFramebufferParameteriEXT;
/* 48:48 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 49:49 */    nglNamedFramebufferParameteriEXT(framebuffer, pname, param, function_pointer);
/* 50:   */  }
/* 51:   */  
/* 52:   */  static native void nglNamedFramebufferParameteriEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 53:   */  
/* 54:54 */  public static void glGetNamedFramebufferParameterEXT(int framebuffer, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 55:55 */    long function_pointer = caps.glGetNamedFramebufferParameterivEXT;
/* 56:56 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 57:57 */    BufferChecks.checkBuffer(params, 1);
/* 58:58 */    nglGetNamedFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 59:   */  }
/* 60:   */  
/* 61:   */  static native void nglGetNamedFramebufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 62:   */  
/* 63:   */  public static int glGetNamedFramebufferParameterEXT(int framebuffer, int pname) {
/* 64:64 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 65:65 */    long function_pointer = caps.glGetNamedFramebufferParameterivEXT;
/* 66:66 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 67:67 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 68:68 */    nglGetNamedFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 69:69 */    return params.get(0);
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBFramebufferNoAttachments
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */