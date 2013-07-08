/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */
/* 28:   */public final class ARBES2Compatibility
/* 29:   */{
/* 30:   */  public static final int GL_SHADER_COMPILER = 36346;
/* 31:   */  public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
/* 32:   */  public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
/* 33:   */  public static final int GL_MAX_VARYING_VECTORS = 36348;
/* 34:   */  public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
/* 35:   */  public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
/* 36:   */  public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
/* 37:   */  public static final int GL_FIXED = 5132;
/* 38:   */  public static final int GL_LOW_FLOAT = 36336;
/* 39:   */  public static final int GL_MEDIUM_FLOAT = 36337;
/* 40:   */  public static final int GL_HIGH_FLOAT = 36338;
/* 41:   */  public static final int GL_LOW_INT = 36339;
/* 42:   */  public static final int GL_MEDIUM_INT = 36340;
/* 43:   */  public static final int GL_HIGH_INT = 36341;
/* 44:   */  public static final int GL_RGB565 = 36194;
/* 45:   */  
/* 46:   */  public static void glReleaseShaderCompiler() {}
/* 47:   */  
/* 48:   */  public static void glShaderBinary(IntBuffer shaders, int binaryformat, ByteBuffer binary)
/* 49:   */  {
/* 50:50 */    GL41.glShaderBinary(shaders, binaryformat, binary);
/* 51:   */  }
/* 52:   */  
/* 53:   */  public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
/* 54:54 */    GL41.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
/* 55:   */  }
/* 56:   */  
/* 57:   */  public static void glDepthRangef(float n, float f) {
/* 58:58 */    GL41.glDepthRangef(n, f);
/* 59:   */  }
/* 60:   */  
/* 61:   */  public static void glClearDepthf(float d) {
/* 62:62 */    GL41.glClearDepthf(d);
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBES2Compatibility
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */