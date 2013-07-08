/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */
/*  44:    */public final class ARBUniformBufferObject
/*  45:    */{
/*  46:    */  public static final int GL_UNIFORM_BUFFER = 35345;
/*  47:    */  public static final int GL_UNIFORM_BUFFER_BINDING = 35368;
/*  48:    */  public static final int GL_UNIFORM_BUFFER_START = 35369;
/*  49:    */  public static final int GL_UNIFORM_BUFFER_SIZE = 35370;
/*  50:    */  public static final int GL_MAX_VERTEX_UNIFORM_BLOCKS = 35371;
/*  51:    */  public static final int GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 35372;
/*  52:    */  public static final int GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 35373;
/*  53:    */  public static final int GL_MAX_COMBINED_UNIFORM_BLOCKS = 35374;
/*  54:    */  public static final int GL_MAX_UNIFORM_BUFFER_BINDINGS = 35375;
/*  55:    */  public static final int GL_MAX_UNIFORM_BLOCK_SIZE = 35376;
/*  56:    */  public static final int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 35377;
/*  57:    */  public static final int GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 35378;
/*  58:    */  public static final int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 35379;
/*  59:    */  public static final int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 35380;
/*  60:    */  public static final int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 35381;
/*  61:    */  public static final int GL_ACTIVE_UNIFORM_BLOCKS = 35382;
/*  62:    */  public static final int GL_UNIFORM_TYPE = 35383;
/*  63:    */  public static final int GL_UNIFORM_SIZE = 35384;
/*  64:    */  public static final int GL_UNIFORM_NAME_LENGTH = 35385;
/*  65:    */  public static final int GL_UNIFORM_BLOCK_INDEX = 35386;
/*  66:    */  public static final int GL_UNIFORM_OFFSET = 35387;
/*  67:    */  public static final int GL_UNIFORM_ARRAY_STRIDE = 35388;
/*  68:    */  public static final int GL_UNIFORM_MATRIX_STRIDE = 35389;
/*  69:    */  public static final int GL_UNIFORM_IS_ROW_MAJOR = 35390;
/*  70:    */  public static final int GL_UNIFORM_BLOCK_BINDING = 35391;
/*  71:    */  public static final int GL_UNIFORM_BLOCK_DATA_SIZE = 35392;
/*  72:    */  public static final int GL_UNIFORM_BLOCK_NAME_LENGTH = 35393;
/*  73:    */  public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 35394;
/*  74:    */  public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 35395;
/*  75:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 35396;
/*  76:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 35397;
/*  77:    */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 35398;
/*  78:    */  public static final int GL_INVALID_INDEX = -1;
/*  79:    */  
/*  80:    */  public static void glGetUniformIndices(int program, ByteBuffer uniformNames, IntBuffer uniformIndices)
/*  81:    */  {
/*  82: 82 */    GL31.glGetUniformIndices(program, uniformNames, uniformIndices);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static void glGetUniformIndices(int program, CharSequence[] uniformNames, IntBuffer uniformIndices)
/*  86:    */  {
/*  87: 87 */    GL31.glGetUniformIndices(program, uniformNames, uniformIndices);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public static void glGetActiveUniforms(int program, IntBuffer uniformIndices, int pname, IntBuffer params) {
/*  91: 91 */    GL31.glGetActiveUniforms(program, uniformIndices, pname, params);
/*  92:    */  }
/*  93:    */  
/*  98:    */  @Deprecated
/*  99:    */  public static int glGetActiveUniforms(int program, int uniformIndex, int pname)
/* 100:    */  {
/* 101:101 */    return GL31.glGetActiveUniformsi(program, uniformIndex, pname);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static int glGetActiveUniformsi(int program, int uniformIndex, int pname)
/* 105:    */  {
/* 106:106 */    return GL31.glGetActiveUniformsi(program, uniformIndex, pname);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public static void glGetActiveUniformName(int program, int uniformIndex, IntBuffer length, ByteBuffer uniformName) {
/* 110:110 */    GL31.glGetActiveUniformName(program, uniformIndex, length, uniformName);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public static String glGetActiveUniformName(int program, int uniformIndex, int bufSize)
/* 114:    */  {
/* 115:115 */    return GL31.glGetActiveUniformName(program, uniformIndex, bufSize);
/* 116:    */  }
/* 117:    */  
/* 118:    */  public static int glGetUniformBlockIndex(int program, ByteBuffer uniformBlockName) {
/* 119:119 */    return GL31.glGetUniformBlockIndex(program, uniformBlockName);
/* 120:    */  }
/* 121:    */  
/* 122:    */  public static int glGetUniformBlockIndex(int program, CharSequence uniformBlockName)
/* 123:    */  {
/* 124:124 */    return GL31.glGetUniformBlockIndex(program, uniformBlockName);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public static void glGetActiveUniformBlock(int program, int uniformBlockIndex, int pname, IntBuffer params) {
/* 128:128 */    GL31.glGetActiveUniformBlock(program, uniformBlockIndex, pname, params);
/* 129:    */  }
/* 130:    */  
/* 135:    */  @Deprecated
/* 136:    */  public static int glGetActiveUniformBlock(int program, int uniformBlockIndex, int pname)
/* 137:    */  {
/* 138:138 */    return GL31.glGetActiveUniformBlocki(program, uniformBlockIndex, pname);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public static int glGetActiveUniformBlocki(int program, int uniformBlockIndex, int pname)
/* 142:    */  {
/* 143:143 */    return GL31.glGetActiveUniformBlocki(program, uniformBlockIndex, pname);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public static void glGetActiveUniformBlockName(int program, int uniformBlockIndex, IntBuffer length, ByteBuffer uniformBlockName) {
/* 147:147 */    GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, length, uniformBlockName);
/* 148:    */  }
/* 149:    */  
/* 150:    */  public static String glGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize)
/* 151:    */  {
/* 152:152 */    return GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, bufSize);
/* 153:    */  }
/* 154:    */  
/* 155:    */  public static void glBindBufferRange(int target, int index, int buffer, long offset, long size) {
/* 156:156 */    GL30.glBindBufferRange(target, index, buffer, offset, size);
/* 157:    */  }
/* 158:    */  
/* 159:    */  public static void glBindBufferBase(int target, int index, int buffer) {
/* 160:160 */    GL30.glBindBufferBase(target, index, buffer);
/* 161:    */  }
/* 162:    */  
/* 163:    */  public static void glGetInteger(int value, int index, IntBuffer data) {
/* 164:164 */    GL30.glGetInteger(value, index, data);
/* 165:    */  }
/* 166:    */  
/* 167:    */  public static int glGetInteger(int value, int index)
/* 168:    */  {
/* 169:169 */    return GL30.glGetInteger(value, index);
/* 170:    */  }
/* 171:    */  
/* 172:    */  public static void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding) {
/* 173:173 */    GL31.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding);
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBUniformBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */