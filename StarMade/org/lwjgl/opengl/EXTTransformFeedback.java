/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  50:    */public final class EXTTransformFeedback
/*  51:    */{
/*  52:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_EXT = 35982;
/*  53:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_EXT = 35972;
/*  54:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_EXT = 35973;
/*  55:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_EXT = 35983;
/*  56:    */  public static final int GL_INTERLEAVED_ATTRIBS_EXT = 35980;
/*  57:    */  public static final int GL_SEPARATE_ATTRIBS_EXT = 35981;
/*  58:    */  public static final int GL_PRIMITIVES_GENERATED_EXT = 35975;
/*  59:    */  public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_EXT = 35976;
/*  60:    */  public static final int GL_RASTERIZER_DISCARD_EXT = 35977;
/*  61:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_EXT = 35978;
/*  62:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_EXT = 35979;
/*  63:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_EXT = 35968;
/*  64:    */  public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_EXT = 35971;
/*  65:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_EXT = 35967;
/*  66:    */  public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH_EXT = 35958;
/*  67:    */  
/*  68:    */  public static void glBindBufferRangeEXT(int target, int index, int buffer, long offset, long size)
/*  69:    */  {
/*  70: 70 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glBindBufferRangeEXT;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    nglBindBufferRangeEXT(target, index, buffer, offset, size, function_pointer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  static native void nglBindBufferRangeEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*  77:    */  
/*  78: 78 */  public static void glBindBufferOffsetEXT(int target, int index, int buffer, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  79: 79 */    long function_pointer = caps.glBindBufferOffsetEXT;
/*  80: 80 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  81: 81 */    nglBindBufferOffsetEXT(target, index, buffer, offset, function_pointer);
/*  82:    */  }
/*  83:    */  
/*  84:    */  static native void nglBindBufferOffsetEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  85:    */  
/*  86: 86 */  public static void glBindBufferBaseEXT(int target, int index, int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  87: 87 */    long function_pointer = caps.glBindBufferBaseEXT;
/*  88: 88 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  89: 89 */    nglBindBufferBaseEXT(target, index, buffer, function_pointer);
/*  90:    */  }
/*  91:    */  
/*  92:    */  static native void nglBindBufferBaseEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  93:    */  
/*  94: 94 */  public static void glBeginTransformFeedbackEXT(int primitiveMode) { ContextCapabilities caps = GLContext.getCapabilities();
/*  95: 95 */    long function_pointer = caps.glBeginTransformFeedbackEXT;
/*  96: 96 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  97: 97 */    nglBeginTransformFeedbackEXT(primitiveMode, function_pointer);
/*  98:    */  }
/*  99:    */  
/* 100:    */  static native void nglBeginTransformFeedbackEXT(int paramInt, long paramLong);
/* 101:    */  
/* 102:102 */  public static void glEndTransformFeedbackEXT() { ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glEndTransformFeedbackEXT;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    nglEndTransformFeedbackEXT(function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglEndTransformFeedbackEXT(long paramLong);
/* 109:    */  
/* 110:110 */  public static void glTransformFeedbackVaryingsEXT(int program, int count, ByteBuffer varyings, int bufferMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glTransformFeedbackVaryingsEXT;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    BufferChecks.checkDirect(varyings);
/* 114:114 */    BufferChecks.checkNullTerminated(varyings, count);
/* 115:115 */    nglTransformFeedbackVaryingsEXT(program, count, MemoryUtil.getAddress(varyings), bufferMode, function_pointer);
/* 116:    */  }
/* 117:    */  
/* 118:    */  static native void nglTransformFeedbackVaryingsEXT(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/* 119:    */  
/* 120:    */  public static void glTransformFeedbackVaryingsEXT(int program, CharSequence[] varyings, int bufferMode) {
/* 121:121 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 122:122 */    long function_pointer = caps.glTransformFeedbackVaryingsEXT;
/* 123:123 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 124:124 */    BufferChecks.checkArray(varyings);
/* 125:125 */    nglTransformFeedbackVaryingsEXT(program, varyings.length, APIUtil.getBufferNT(caps, varyings), bufferMode, function_pointer);
/* 126:    */  }
/* 127:    */  
/* 128:    */  public static void glGetTransformFeedbackVaryingEXT(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 129:129 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glGetTransformFeedbackVaryingEXT;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    if (length != null)
/* 133:133 */      BufferChecks.checkBuffer(length, 1);
/* 134:134 */    BufferChecks.checkBuffer(size, 1);
/* 135:135 */    BufferChecks.checkBuffer(type, 1);
/* 136:136 */    BufferChecks.checkDirect(name);
/* 137:137 */    nglGetTransformFeedbackVaryingEXT(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 138:    */  }
/* 139:    */  
/* 140:    */  static native void nglGetTransformFeedbackVaryingEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 141:    */  
/* 142:    */  public static String glGetTransformFeedbackVaryingEXT(int program, int index, int bufSize, IntBuffer size, IntBuffer type) {
/* 143:143 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 144:144 */    long function_pointer = caps.glGetTransformFeedbackVaryingEXT;
/* 145:145 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 146:146 */    BufferChecks.checkBuffer(size, 1);
/* 147:147 */    BufferChecks.checkBuffer(type, 1);
/* 148:148 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 149:149 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 150:150 */    nglGetTransformFeedbackVaryingEXT(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 151:151 */    name.limit(name_length.get(0));
/* 152:152 */    return APIUtil.getString(caps, name);
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTransformFeedback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */