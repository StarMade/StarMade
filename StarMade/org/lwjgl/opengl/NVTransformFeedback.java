/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  60:    */public final class NVTransformFeedback
/*  61:    */{
/*  62:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_NV = 35982;
/*  63:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_NV = 35972;
/*  64:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_NV = 35973;
/*  65:    */  public static final int GL_TRANSFORM_FEEDBACK_RECORD_NV = 35974;
/*  66:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_NV = 35983;
/*  67:    */  public static final int GL_INTERLEAVED_ATTRIBS_NV = 35980;
/*  68:    */  public static final int GL_SEPARATE_ATTRIBS_NV = 35981;
/*  69:    */  public static final int GL_PRIMITIVES_GENERATED_NV = 35975;
/*  70:    */  public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_NV = 35976;
/*  71:    */  public static final int GL_RASTERIZER_DISCARD_NV = 35977;
/*  72:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_NV = 35978;
/*  73:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_NV = 35979;
/*  74:    */  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_NV = 35968;
/*  75:    */  public static final int GL_TRANSFORM_FEEDBACK_ATTRIBS_NV = 35966;
/*  76:    */  public static final int GL_ACTIVE_VARYINGS_NV = 35969;
/*  77:    */  public static final int GL_ACTIVE_VARYING_MAX_LENGTH_NV = 35970;
/*  78:    */  public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_NV = 35971;
/*  79:    */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_NV = 35967;
/*  80:    */  public static final int GL_BACK_PRIMARY_COLOR_NV = 35959;
/*  81:    */  public static final int GL_BACK_SECONDARY_COLOR_NV = 35960;
/*  82:    */  public static final int GL_TEXTURE_COORD_NV = 35961;
/*  83:    */  public static final int GL_CLIP_DISTANCE_NV = 35962;
/*  84:    */  public static final int GL_VERTEX_ID_NV = 35963;
/*  85:    */  public static final int GL_PRIMITIVE_ID_NV = 35964;
/*  86:    */  public static final int GL_GENERIC_ATTRIB_NV = 35965;
/*  87:    */  public static final int GL_LAYER_NV = 36266;
/*  88:    */  
/*  89:    */  public static void glBindBufferRangeNV(int target, int index, int buffer, long offset, long size)
/*  90:    */  {
/*  91: 91 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  92: 92 */    long function_pointer = caps.glBindBufferRangeNV;
/*  93: 93 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  94: 94 */    nglBindBufferRangeNV(target, index, buffer, offset, size, function_pointer);
/*  95:    */  }
/*  96:    */  
/*  97:    */  static native void nglBindBufferRangeNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*  98:    */  
/*  99: 99 */  public static void glBindBufferOffsetNV(int target, int index, int buffer, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 100:100 */    long function_pointer = caps.glBindBufferOffsetNV;
/* 101:101 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 102:102 */    nglBindBufferOffsetNV(target, index, buffer, offset, function_pointer);
/* 103:    */  }
/* 104:    */  
/* 105:    */  static native void nglBindBufferOffsetNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 106:    */  
/* 107:107 */  public static void glBindBufferBaseNV(int target, int index, int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 108:108 */    long function_pointer = caps.glBindBufferBaseNV;
/* 109:109 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 110:110 */    nglBindBufferBaseNV(target, index, buffer, function_pointer);
/* 111:    */  }
/* 112:    */  
/* 113:    */  static native void nglBindBufferBaseNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 114:    */  
/* 115:115 */  public static void glTransformFeedbackAttribsNV(IntBuffer attribs, int bufferMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 116:116 */    long function_pointer = caps.glTransformFeedbackAttribsNV;
/* 117:117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 118:118 */    BufferChecks.checkBuffer(attribs, 3);
/* 119:119 */    nglTransformFeedbackAttribsNV(attribs.remaining() / 3, MemoryUtil.getAddress(attribs), bufferMode, function_pointer);
/* 120:    */  }
/* 121:    */  
/* 122:    */  static native void nglTransformFeedbackAttribsNV(int paramInt1, long paramLong1, int paramInt2, long paramLong2);
/* 123:    */  
/* 124:124 */  public static void glTransformFeedbackVaryingsNV(int program, IntBuffer locations, int bufferMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 125:125 */    long function_pointer = caps.glTransformFeedbackVaryingsNV;
/* 126:126 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 127:127 */    BufferChecks.checkDirect(locations);
/* 128:128 */    nglTransformFeedbackVaryingsNV(program, locations.remaining(), MemoryUtil.getAddress(locations), bufferMode, function_pointer);
/* 129:    */  }
/* 130:    */  
/* 131:    */  static native void nglTransformFeedbackVaryingsNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/* 132:    */  
/* 133:133 */  public static void glBeginTransformFeedbackNV(int primitiveMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 134:134 */    long function_pointer = caps.glBeginTransformFeedbackNV;
/* 135:135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 136:136 */    nglBeginTransformFeedbackNV(primitiveMode, function_pointer);
/* 137:    */  }
/* 138:    */  
/* 139:    */  static native void nglBeginTransformFeedbackNV(int paramInt, long paramLong);
/* 140:    */  
/* 141:141 */  public static void glEndTransformFeedbackNV() { ContextCapabilities caps = GLContext.getCapabilities();
/* 142:142 */    long function_pointer = caps.glEndTransformFeedbackNV;
/* 143:143 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 144:144 */    nglEndTransformFeedbackNV(function_pointer);
/* 145:    */  }
/* 146:    */  
/* 147:    */  static native void nglEndTransformFeedbackNV(long paramLong);
/* 148:    */  
/* 149:149 */  public static int glGetVaryingLocationNV(int program, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/* 150:150 */    long function_pointer = caps.glGetVaryingLocationNV;
/* 151:151 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 152:152 */    BufferChecks.checkDirect(name);
/* 153:153 */    BufferChecks.checkNullTerminated(name);
/* 154:154 */    int __result = nglGetVaryingLocationNV(program, MemoryUtil.getAddress(name), function_pointer);
/* 155:155 */    return __result;
/* 156:    */  }
/* 157:    */  
/* 158:    */  static native int nglGetVaryingLocationNV(int paramInt, long paramLong1, long paramLong2);
/* 159:    */  
/* 160:    */  public static int glGetVaryingLocationNV(int program, CharSequence name) {
/* 161:161 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 162:162 */    long function_pointer = caps.glGetVaryingLocationNV;
/* 163:163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 164:164 */    int __result = nglGetVaryingLocationNV(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 165:165 */    return __result;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public static void glGetActiveVaryingNV(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 169:169 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 170:170 */    long function_pointer = caps.glGetActiveVaryingNV;
/* 171:171 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 172:172 */    if (length != null)
/* 173:173 */      BufferChecks.checkBuffer(length, 1);
/* 174:174 */    BufferChecks.checkBuffer(size, 1);
/* 175:175 */    BufferChecks.checkBuffer(type, 1);
/* 176:176 */    BufferChecks.checkDirect(name);
/* 177:177 */    nglGetActiveVaryingNV(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 178:    */  }
/* 179:    */  
/* 182:    */  static native void nglGetActiveVaryingNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 183:    */  
/* 185:    */  public static String glGetActiveVaryingNV(int program, int index, int bufSize, IntBuffer sizeType)
/* 186:    */  {
/* 187:187 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 188:188 */    long function_pointer = caps.glGetActiveVaryingNV;
/* 189:189 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 190:190 */    BufferChecks.checkBuffer(sizeType, 2);
/* 191:191 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 192:192 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 193:193 */    nglGetActiveVaryingNV(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 194:194 */    name.limit(name_length.get(0));
/* 195:195 */    return APIUtil.getString(caps, name);
/* 196:    */  }
/* 197:    */  
/* 202:    */  public static String glGetActiveVaryingNV(int program, int index, int bufSize)
/* 203:    */  {
/* 204:204 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 205:205 */    long function_pointer = caps.glGetActiveVaryingNV;
/* 206:206 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 207:207 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 208:208 */    ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 209:209 */    nglGetActiveVaryingNV(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 210:210 */    name.limit(name_length.get(0));
/* 211:211 */    return APIUtil.getString(caps, name);
/* 212:    */  }
/* 213:    */  
/* 218:    */  public static int glGetActiveVaryingSizeNV(int program, int index)
/* 219:    */  {
/* 220:220 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 221:221 */    long function_pointer = caps.glGetActiveVaryingNV;
/* 222:222 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 223:223 */    IntBuffer size = APIUtil.getBufferInt(caps);
/* 224:224 */    nglGetActiveVaryingNV(program, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 225:225 */    return size.get(0);
/* 226:    */  }
/* 227:    */  
/* 232:    */  public static int glGetActiveVaryingTypeNV(int program, int index)
/* 233:    */  {
/* 234:234 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 235:235 */    long function_pointer = caps.glGetActiveVaryingNV;
/* 236:236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 237:237 */    IntBuffer type = APIUtil.getBufferInt(caps);
/* 238:238 */    nglGetActiveVaryingNV(program, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 239:239 */    return type.get(0);
/* 240:    */  }
/* 241:    */  
/* 242:    */  public static void glActiveVaryingNV(int program, ByteBuffer name) {
/* 243:243 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 244:244 */    long function_pointer = caps.glActiveVaryingNV;
/* 245:245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 246:246 */    BufferChecks.checkDirect(name);
/* 247:247 */    BufferChecks.checkNullTerminated(name);
/* 248:248 */    nglActiveVaryingNV(program, MemoryUtil.getAddress(name), function_pointer);
/* 249:    */  }
/* 250:    */  
/* 251:    */  static native void nglActiveVaryingNV(int paramInt, long paramLong1, long paramLong2);
/* 252:    */  
/* 253:    */  public static void glActiveVaryingNV(int program, CharSequence name) {
/* 254:254 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 255:255 */    long function_pointer = caps.glActiveVaryingNV;
/* 256:256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 257:257 */    nglActiveVaryingNV(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 258:    */  }
/* 259:    */  
/* 260:    */  public static void glGetTransformFeedbackVaryingNV(int program, int index, IntBuffer location) {
/* 261:261 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 262:262 */    long function_pointer = caps.glGetTransformFeedbackVaryingNV;
/* 263:263 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 264:264 */    BufferChecks.checkBuffer(location, 1);
/* 265:265 */    nglGetTransformFeedbackVaryingNV(program, index, MemoryUtil.getAddress(location), function_pointer);
/* 266:    */  }
/* 267:    */  
/* 268:    */  static native void nglGetTransformFeedbackVaryingNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 269:    */  
/* 270:    */  public static int glGetTransformFeedbackVaryingNV(int program, int index) {
/* 271:271 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 272:272 */    long function_pointer = caps.glGetTransformFeedbackVaryingNV;
/* 273:273 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 274:274 */    IntBuffer location = APIUtil.getBufferInt(caps);
/* 275:275 */    nglGetTransformFeedbackVaryingNV(program, index, MemoryUtil.getAddress(location), function_pointer);
/* 276:276 */    return location.get(0);
/* 277:    */  }
/* 278:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTransformFeedback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */