/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.DoubleBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.nio.ShortBuffer;
/*   9:    */import org.lwjgl.BufferChecks;
/*  10:    */import org.lwjgl.LWJGLUtil;
/*  11:    */import org.lwjgl.MemoryUtil;
/*  12:    */
/*  40:    */public final class ARBVertexShader
/*  41:    */{
/*  42:    */  public static final int GL_VERTEX_SHADER_ARB = 35633;
/*  43:    */  public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 35658;
/*  44:    */  public static final int GL_MAX_VARYING_FLOATS_ARB = 35659;
/*  45:    */  public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
/*  46:    */  public static final int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 34930;
/*  47:    */  public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 35660;
/*  48:    */  public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 35661;
/*  49:    */  public static final int GL_MAX_TEXTURE_COORDS_ARB = 34929;
/*  50:    */  public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
/*  51:    */  public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
/*  52:    */  public static final int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 35721;
/*  53:    */  public static final int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 35722;
/*  54:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
/*  55:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
/*  56:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
/*  57:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
/*  58:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
/*  59:    */  public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
/*  60:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
/*  61:    */  public static final int GL_FLOAT_VEC2_ARB = 35664;
/*  62:    */  public static final int GL_FLOAT_VEC3_ARB = 35665;
/*  63:    */  public static final int GL_FLOAT_VEC4_ARB = 35666;
/*  64:    */  public static final int GL_FLOAT_MAT2_ARB = 35674;
/*  65:    */  public static final int GL_FLOAT_MAT3_ARB = 35675;
/*  66:    */  public static final int GL_FLOAT_MAT4_ARB = 35676;
/*  67:    */  
/*  68:    */  public static void glVertexAttrib1sARB(int index, short v0)
/*  69:    */  {
/*  70: 70 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glVertexAttrib1sARB;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    nglVertexAttrib1sARB(index, v0, function_pointer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  static native void nglVertexAttrib1sARB(int paramInt, short paramShort, long paramLong);
/*  77:    */  
/*  78: 78 */  public static void glVertexAttrib1fARB(int index, float v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  79: 79 */    long function_pointer = caps.glVertexAttrib1fARB;
/*  80: 80 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  81: 81 */    nglVertexAttrib1fARB(index, v0, function_pointer);
/*  82:    */  }
/*  83:    */  
/*  84:    */  static native void nglVertexAttrib1fARB(int paramInt, float paramFloat, long paramLong);
/*  85:    */  
/*  86: 86 */  public static void glVertexAttrib1dARB(int index, double v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  87: 87 */    long function_pointer = caps.glVertexAttrib1dARB;
/*  88: 88 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  89: 89 */    nglVertexAttrib1dARB(index, v0, function_pointer);
/*  90:    */  }
/*  91:    */  
/*  92:    */  static native void nglVertexAttrib1dARB(int paramInt, double paramDouble, long paramLong);
/*  93:    */  
/*  94: 94 */  public static void glVertexAttrib2sARB(int index, short v0, short v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  95: 95 */    long function_pointer = caps.glVertexAttrib2sARB;
/*  96: 96 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  97: 97 */    nglVertexAttrib2sARB(index, v0, v1, function_pointer);
/*  98:    */  }
/*  99:    */  
/* 100:    */  static native void nglVertexAttrib2sARB(int paramInt, short paramShort1, short paramShort2, long paramLong);
/* 101:    */  
/* 102:102 */  public static void glVertexAttrib2fARB(int index, float v0, float v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glVertexAttrib2fARB;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    nglVertexAttrib2fARB(index, v0, v1, function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglVertexAttrib2fARB(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 109:    */  
/* 110:110 */  public static void glVertexAttrib2dARB(int index, double v0, double v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glVertexAttrib2dARB;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    nglVertexAttrib2dARB(index, v0, v1, function_pointer);
/* 114:    */  }
/* 115:    */  
/* 116:    */  static native void nglVertexAttrib2dARB(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/* 117:    */  
/* 118:118 */  public static void glVertexAttrib3sARB(int index, short v0, short v1, short v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 119:119 */    long function_pointer = caps.glVertexAttrib3sARB;
/* 120:120 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 121:121 */    nglVertexAttrib3sARB(index, v0, v1, v2, function_pointer);
/* 122:    */  }
/* 123:    */  
/* 124:    */  static native void nglVertexAttrib3sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 125:    */  
/* 126:126 */  public static void glVertexAttrib3fARB(int index, float v0, float v1, float v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 127:127 */    long function_pointer = caps.glVertexAttrib3fARB;
/* 128:128 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 129:129 */    nglVertexAttrib3fARB(index, v0, v1, v2, function_pointer);
/* 130:    */  }
/* 131:    */  
/* 132:    */  static native void nglVertexAttrib3fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 133:    */  
/* 134:134 */  public static void glVertexAttrib3dARB(int index, double v0, double v1, double v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 135:135 */    long function_pointer = caps.glVertexAttrib3dARB;
/* 136:136 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 137:137 */    nglVertexAttrib3dARB(index, v0, v1, v2, function_pointer);
/* 138:    */  }
/* 139:    */  
/* 140:    */  static native void nglVertexAttrib3dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 141:    */  
/* 142:142 */  public static void glVertexAttrib4sARB(int index, short v0, short v1, short v2, short v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glVertexAttrib4sARB;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    nglVertexAttrib4sARB(index, v0, v1, v2, v3, function_pointer);
/* 146:    */  }
/* 147:    */  
/* 148:    */  static native void nglVertexAttrib4sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 149:    */  
/* 150:150 */  public static void glVertexAttrib4fARB(int index, float v0, float v1, float v2, float v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 151:151 */    long function_pointer = caps.glVertexAttrib4fARB;
/* 152:152 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 153:153 */    nglVertexAttrib4fARB(index, v0, v1, v2, v3, function_pointer);
/* 154:    */  }
/* 155:    */  
/* 156:    */  static native void nglVertexAttrib4fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 157:    */  
/* 158:158 */  public static void glVertexAttrib4dARB(int index, double v0, double v1, double v2, double v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 159:159 */    long function_pointer = caps.glVertexAttrib4dARB;
/* 160:160 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 161:161 */    nglVertexAttrib4dARB(index, v0, v1, v2, v3, function_pointer);
/* 162:    */  }
/* 163:    */  
/* 164:    */  static native void nglVertexAttrib4dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 165:    */  
/* 166:166 */  public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glVertexAttrib4NubARB;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    nglVertexAttrib4NubARB(index, x, y, z, w, function_pointer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native void nglVertexAttrib4NubARB(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/* 173:    */  
/* 174:174 */  public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 176:176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 178:178 */    BufferChecks.checkDirect(buffer);
/* 179:179 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 180:180 */    nglVertexAttribPointerARB(index, size, 5130, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 181:    */  }
/* 182:    */  
/* 183:183 */  public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 184:184 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 185:185 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 186:186 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 187:187 */    BufferChecks.checkDirect(buffer);
/* 188:188 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 189:189 */    nglVertexAttribPointerARB(index, size, 5126, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 190:    */  }
/* 191:    */  
/* 192:192 */  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 193:193 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 194:194 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 195:195 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 196:196 */    BufferChecks.checkDirect(buffer);
/* 197:197 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 198:198 */    nglVertexAttribPointerARB(index, size, unsigned ? 5121 : 5120, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 199:    */  }
/* 200:    */  
/* 201:201 */  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 202:202 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 203:203 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 204:204 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 205:205 */    BufferChecks.checkDirect(buffer);
/* 206:206 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 207:207 */    nglVertexAttribPointerARB(index, size, unsigned ? 5125 : 5124, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 208:    */  }
/* 209:    */  
/* 210:210 */  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 211:211 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 212:212 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 213:213 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 214:214 */    BufferChecks.checkDirect(buffer);
/* 215:215 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 216:216 */    nglVertexAttribPointerARB(index, size, unsigned ? 5123 : 5122, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer); }
/* 217:    */  
/* 218:    */  static native void nglVertexAttribPointerARB(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/* 219:    */  
/* 220:220 */  public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 221:221 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 222:222 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 223:223 */    GLChecks.ensureArrayVBOenabled(caps);
/* 224:224 */    nglVertexAttribPointerARBBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer);
/* 225:    */  }
/* 226:    */  
/* 227:    */  static native void nglVertexAttribPointerARBBO(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/* 228:    */  
/* 229:    */  public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, ByteBuffer buffer) {
/* 230:230 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 231:231 */    long function_pointer = caps.glVertexAttribPointerARB;
/* 232:232 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 233:233 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 234:234 */    BufferChecks.checkDirect(buffer);
/* 235:235 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 236:236 */    nglVertexAttribPointerARB(index, size, type, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 237:    */  }
/* 238:    */  
/* 239:    */  public static void glEnableVertexAttribArrayARB(int index) {
/* 240:240 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 241:241 */    long function_pointer = caps.glEnableVertexAttribArrayARB;
/* 242:242 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 243:243 */    nglEnableVertexAttribArrayARB(index, function_pointer);
/* 244:    */  }
/* 245:    */  
/* 246:    */  static native void nglEnableVertexAttribArrayARB(int paramInt, long paramLong);
/* 247:    */  
/* 248:248 */  public static void glDisableVertexAttribArrayARB(int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 249:249 */    long function_pointer = caps.glDisableVertexAttribArrayARB;
/* 250:250 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 251:251 */    nglDisableVertexAttribArrayARB(index, function_pointer);
/* 252:    */  }
/* 253:    */  
/* 254:    */  static native void nglDisableVertexAttribArrayARB(int paramInt, long paramLong);
/* 255:    */  
/* 256:256 */  public static void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/* 257:257 */    long function_pointer = caps.glBindAttribLocationARB;
/* 258:258 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 259:259 */    BufferChecks.checkDirect(name);
/* 260:260 */    BufferChecks.checkNullTerminated(name);
/* 261:261 */    nglBindAttribLocationARB(programObj, index, MemoryUtil.getAddress(name), function_pointer);
/* 262:    */  }
/* 263:    */  
/* 264:    */  static native void nglBindAttribLocationARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 265:    */  
/* 266:    */  public static void glBindAttribLocationARB(int programObj, int index, CharSequence name) {
/* 267:267 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 268:268 */    long function_pointer = caps.glBindAttribLocationARB;
/* 269:269 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 270:270 */    nglBindAttribLocationARB(programObj, index, APIUtil.getBufferNT(caps, name), function_pointer);
/* 271:    */  }
/* 272:    */  
/* 273:    */  public static void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 274:274 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 275:275 */    long function_pointer = caps.glGetActiveAttribARB;
/* 276:276 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 277:277 */    if (length != null)
/* 278:278 */      BufferChecks.checkBuffer(length, 1);
/* 279:279 */    BufferChecks.checkBuffer(size, 1);
/* 280:280 */    BufferChecks.checkBuffer(type, 1);
/* 281:281 */    BufferChecks.checkDirect(name);
/* 282:282 */    nglGetActiveAttribARB(programObj, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 283:    */  }
/* 284:    */  
/* 287:    */  static native void nglGetActiveAttribARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 288:    */  
/* 290:    */  public static String glGetActiveAttribARB(int programObj, int index, int maxLength, IntBuffer sizeType)
/* 291:    */  {
/* 292:292 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 293:293 */    long function_pointer = caps.glGetActiveAttribARB;
/* 294:294 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 295:295 */    BufferChecks.checkBuffer(sizeType, 2);
/* 296:296 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 297:297 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 298:298 */    nglGetActiveAttribARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 299:299 */    name.limit(name_length.get(0));
/* 300:300 */    return APIUtil.getString(caps, name);
/* 301:    */  }
/* 302:    */  
/* 307:    */  public static String glGetActiveAttribARB(int programObj, int index, int maxLength)
/* 308:    */  {
/* 309:309 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 310:310 */    long function_pointer = caps.glGetActiveAttribARB;
/* 311:311 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 312:312 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 313:313 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 314:314 */    nglGetActiveAttribARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 315:315 */    name.limit(name_length.get(0));
/* 316:316 */    return APIUtil.getString(caps, name);
/* 317:    */  }
/* 318:    */  
/* 323:    */  public static int glGetActiveAttribSizeARB(int programObj, int index)
/* 324:    */  {
/* 325:325 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 326:326 */    long function_pointer = caps.glGetActiveAttribARB;
/* 327:327 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 328:328 */    IntBuffer size = APIUtil.getBufferInt(caps);
/* 329:329 */    nglGetActiveAttribARB(programObj, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 330:330 */    return size.get(0);
/* 331:    */  }
/* 332:    */  
/* 337:    */  public static int glGetActiveAttribTypeARB(int programObj, int index)
/* 338:    */  {
/* 339:339 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 340:340 */    long function_pointer = caps.glGetActiveAttribARB;
/* 341:341 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 342:342 */    IntBuffer type = APIUtil.getBufferInt(caps);
/* 343:343 */    nglGetActiveAttribARB(programObj, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 344:344 */    return type.get(0);
/* 345:    */  }
/* 346:    */  
/* 347:    */  public static int glGetAttribLocationARB(int programObj, ByteBuffer name) {
/* 348:348 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 349:349 */    long function_pointer = caps.glGetAttribLocationARB;
/* 350:350 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 351:351 */    BufferChecks.checkDirect(name);
/* 352:352 */    BufferChecks.checkNullTerminated(name);
/* 353:353 */    int __result = nglGetAttribLocationARB(programObj, MemoryUtil.getAddress(name), function_pointer);
/* 354:354 */    return __result;
/* 355:    */  }
/* 356:    */  
/* 357:    */  static native int nglGetAttribLocationARB(int paramInt, long paramLong1, long paramLong2);
/* 358:    */  
/* 359:    */  public static int glGetAttribLocationARB(int programObj, CharSequence name) {
/* 360:360 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 361:361 */    long function_pointer = caps.glGetAttribLocationARB;
/* 362:362 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 363:363 */    int __result = nglGetAttribLocationARB(programObj, APIUtil.getBufferNT(caps, name), function_pointer);
/* 364:364 */    return __result;
/* 365:    */  }
/* 366:    */  
/* 367:    */  public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
/* 368:368 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 369:369 */    long function_pointer = caps.glGetVertexAttribfvARB;
/* 370:370 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 371:371 */    BufferChecks.checkBuffer(params, 4);
/* 372:372 */    nglGetVertexAttribfvARB(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 373:    */  }
/* 374:    */  
/* 375:    */  static native void nglGetVertexAttribfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 376:    */  
/* 377:377 */  public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 378:378 */    long function_pointer = caps.glGetVertexAttribdvARB;
/* 379:379 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 380:380 */    BufferChecks.checkBuffer(params, 4);
/* 381:381 */    nglGetVertexAttribdvARB(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 382:    */  }
/* 383:    */  
/* 384:    */  static native void nglGetVertexAttribdvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 385:    */  
/* 386:386 */  public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 387:387 */    long function_pointer = caps.glGetVertexAttribivARB;
/* 388:388 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 389:389 */    BufferChecks.checkBuffer(params, 4);
/* 390:390 */    nglGetVertexAttribivARB(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 391:    */  }
/* 392:    */  
/* 393:    */  static native void nglGetVertexAttribivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 394:    */  
/* 395:395 */  public static ByteBuffer glGetVertexAttribPointerARB(int index, int pname, long result_size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 396:396 */    long function_pointer = caps.glGetVertexAttribPointervARB;
/* 397:397 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 398:398 */    ByteBuffer __result = nglGetVertexAttribPointervARB(index, pname, result_size, function_pointer);
/* 399:399 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 400:    */  }
/* 401:    */  
/* 402:    */  static native ByteBuffer nglGetVertexAttribPointervARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 403:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexShader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */