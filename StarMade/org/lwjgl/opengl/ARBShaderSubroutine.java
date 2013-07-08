/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */
/*  21:    */public final class ARBShaderSubroutine
/*  22:    */{
/*  23:    */  public static final int GL_ACTIVE_SUBROUTINES = 36325;
/*  24:    */  public static final int GL_ACTIVE_SUBROUTINE_UNIFORMS = 36326;
/*  25:    */  public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 36423;
/*  26:    */  public static final int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 36424;
/*  27:    */  public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;
/*  28:    */  public static final int GL_MAX_SUBROUTINES = 36327;
/*  29:    */  public static final int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;
/*  30:    */  public static final int GL_NUM_COMPATIBLE_SUBROUTINES = 36426;
/*  31:    */  public static final int GL_COMPATIBLE_SUBROUTINES = 36427;
/*  32:    */  public static final int GL_UNIFORM_SIZE = 35384;
/*  33:    */  public static final int GL_UNIFORM_NAME_LENGTH = 35385;
/*  34:    */  
/*  35:    */  public static int glGetSubroutineUniformLocation(int program, int shadertype, ByteBuffer name)
/*  36:    */  {
/*  37: 37 */    return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
/*  38:    */  }
/*  39:    */  
/*  40:    */  public static int glGetSubroutineUniformLocation(int program, int shadertype, CharSequence name)
/*  41:    */  {
/*  42: 42 */    return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
/*  43:    */  }
/*  44:    */  
/*  45:    */  public static int glGetSubroutineIndex(int program, int shadertype, ByteBuffer name) {
/*  46: 46 */    return GL40.glGetSubroutineIndex(program, shadertype, name);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public static int glGetSubroutineIndex(int program, int shadertype, CharSequence name)
/*  50:    */  {
/*  51: 51 */    return GL40.glGetSubroutineIndex(program, shadertype, name);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public static void glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname, IntBuffer values) {
/*  55: 55 */    GL40.glGetActiveSubroutineUniform(program, shadertype, index, pname, values);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public static int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname)
/*  59:    */  {
/*  60: 60 */    return GL40.glGetActiveSubroutineUniformi(program, shadertype, index, pname);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public static void glGetActiveSubroutineUniformName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/*  64: 64 */    GL40.glGetActiveSubroutineUniformName(program, shadertype, index, length, name);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize)
/*  68:    */  {
/*  69: 69 */    return GL40.glGetActiveSubroutineUniformName(program, shadertype, index, bufsize);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static void glGetActiveSubroutineName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/*  73: 73 */    GL40.glGetActiveSubroutineName(program, shadertype, index, length, name);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public static String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize)
/*  77:    */  {
/*  78: 78 */    return GL40.glGetActiveSubroutineName(program, shadertype, index, bufsize);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static void glUniformSubroutinesu(int shadertype, IntBuffer indices) {
/*  82: 82 */    GL40.glUniformSubroutinesu(shadertype, indices);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static void glGetUniformSubroutineu(int shadertype, int location, IntBuffer params) {
/*  86: 86 */    GL40.glGetUniformSubroutineu(shadertype, location, params);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public static int glGetUniformSubroutineui(int shadertype, int location)
/*  90:    */  {
/*  91: 91 */    return GL40.glGetUniformSubroutineui(shadertype, location);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public static void glGetProgramStage(int program, int shadertype, int pname, IntBuffer values) {
/*  95: 95 */    GL40.glGetProgramStage(program, shadertype, pname, values);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public static int glGetProgramStagei(int program, int shadertype, int pname)
/*  99:    */  {
/* 100:100 */    return GL40.glGetProgramStagei(program, shadertype, pname);
/* 101:    */  }
/* 102:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShaderSubroutine
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */