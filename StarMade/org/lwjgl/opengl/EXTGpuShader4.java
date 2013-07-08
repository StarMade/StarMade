/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.nio.ShortBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.LWJGLUtil;
/*   8:    */import org.lwjgl.MemoryUtil;
/*   9:    */
/*  23:    */public final class EXTGpuShader4
/*  24:    */{
/*  25:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER_EXT = 35069;
/*  26:    */  public static final int GL_SAMPLER_1D_ARRAY_EXT = 36288;
/*  27:    */  public static final int GL_SAMPLER_2D_ARRAY_EXT = 36289;
/*  28:    */  public static final int GL_SAMPLER_BUFFER_EXT = 36290;
/*  29:    */  public static final int GL_SAMPLER_1D_ARRAY_SHADOW_EXT = 36291;
/*  30:    */  public static final int GL_SAMPLER_2D_ARRAY_SHADOW_EXT = 36292;
/*  31:    */  public static final int GL_SAMPLER_CUBE_SHADOW_EXT = 36293;
/*  32:    */  public static final int GL_UNSIGNED_INT_VEC2_EXT = 36294;
/*  33:    */  public static final int GL_UNSIGNED_INT_VEC3_EXT = 36295;
/*  34:    */  public static final int GL_UNSIGNED_INT_VEC4_EXT = 36296;
/*  35:    */  public static final int GL_INT_SAMPLER_1D_EXT = 36297;
/*  36:    */  public static final int GL_INT_SAMPLER_2D_EXT = 36298;
/*  37:    */  public static final int GL_INT_SAMPLER_3D_EXT = 36299;
/*  38:    */  public static final int GL_INT_SAMPLER_CUBE_EXT = 36300;
/*  39:    */  public static final int GL_INT_SAMPLER_2D_RECT_EXT = 36301;
/*  40:    */  public static final int GL_INT_SAMPLER_1D_ARRAY_EXT = 36302;
/*  41:    */  public static final int GL_INT_SAMPLER_2D_ARRAY_EXT = 36303;
/*  42:    */  public static final int GL_INT_SAMPLER_BUFFER_EXT = 36304;
/*  43:    */  public static final int GL_UNSIGNED_INT_SAMPLER_1D_EXT = 36305;
/*  44:    */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_EXT = 36306;
/*  45:    */  public static final int GL_UNSIGNED_INT_SAMPLER_3D_EXT = 36307;
/*  46:    */  public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_EXT = 36308;
/*  47:    */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT_EXT = 36309;
/*  48:    */  public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY_EXT = 36310;
/*  49:    */  public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY_EXT = 36311;
/*  50:    */  public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_EXT = 36312;
/*  51:    */  public static final int GL_MIN_PROGRAM_TEXEL_OFFSET_EXT = 35076;
/*  52:    */  public static final int GL_MAX_PROGRAM_TEXEL_OFFSET_EXT = 35077;
/*  53:    */  
/*  54:    */  public static void glVertexAttribI1iEXT(int index, int x)
/*  55:    */  {
/*  56: 56 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glVertexAttribI1iEXT;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    nglVertexAttribI1iEXT(index, x, function_pointer);
/*  60:    */  }
/*  61:    */  
/*  62:    */  static native void nglVertexAttribI1iEXT(int paramInt1, int paramInt2, long paramLong);
/*  63:    */  
/*  64: 64 */  public static void glVertexAttribI2iEXT(int index, int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  65: 65 */    long function_pointer = caps.glVertexAttribI2iEXT;
/*  66: 66 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  67: 67 */    nglVertexAttribI2iEXT(index, x, y, function_pointer);
/*  68:    */  }
/*  69:    */  
/*  70:    */  static native void nglVertexAttribI2iEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  71:    */  
/*  72: 72 */  public static void glVertexAttribI3iEXT(int index, int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  73: 73 */    long function_pointer = caps.glVertexAttribI3iEXT;
/*  74: 74 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  75: 75 */    nglVertexAttribI3iEXT(index, x, y, z, function_pointer);
/*  76:    */  }
/*  77:    */  
/*  78:    */  static native void nglVertexAttribI3iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  79:    */  
/*  80: 80 */  public static void glVertexAttribI4iEXT(int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  81: 81 */    long function_pointer = caps.glVertexAttribI4iEXT;
/*  82: 82 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  83: 83 */    nglVertexAttribI4iEXT(index, x, y, z, w, function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86:    */  static native void nglVertexAttribI4iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  87:    */  
/*  88: 88 */  public static void glVertexAttribI1uiEXT(int index, int x) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glVertexAttribI1uiEXT;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    nglVertexAttribI1uiEXT(index, x, function_pointer);
/*  92:    */  }
/*  93:    */  
/*  94:    */  static native void nglVertexAttribI1uiEXT(int paramInt1, int paramInt2, long paramLong);
/*  95:    */  
/*  96: 96 */  public static void glVertexAttribI2uiEXT(int index, int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  97: 97 */    long function_pointer = caps.glVertexAttribI2uiEXT;
/*  98: 98 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  99: 99 */    nglVertexAttribI2uiEXT(index, x, y, function_pointer);
/* 100:    */  }
/* 101:    */  
/* 102:    */  static native void nglVertexAttribI2uiEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 103:    */  
/* 104:104 */  public static void glVertexAttribI3uiEXT(int index, int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 105:105 */    long function_pointer = caps.glVertexAttribI3uiEXT;
/* 106:106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 107:107 */    nglVertexAttribI3uiEXT(index, x, y, z, function_pointer);
/* 108:    */  }
/* 109:    */  
/* 110:    */  static native void nglVertexAttribI3uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 111:    */  
/* 112:112 */  public static void glVertexAttribI4uiEXT(int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 113:113 */    long function_pointer = caps.glVertexAttribI4uiEXT;
/* 114:114 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 115:115 */    nglVertexAttribI4uiEXT(index, x, y, z, w, function_pointer);
/* 116:    */  }
/* 117:    */  
/* 118:    */  static native void nglVertexAttribI4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 119:    */  
/* 120:120 */  public static void glVertexAttribI1EXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 121:121 */    long function_pointer = caps.glVertexAttribI1ivEXT;
/* 122:122 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 123:123 */    BufferChecks.checkBuffer(v, 1);
/* 124:124 */    nglVertexAttribI1ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 125:    */  }
/* 126:    */  
/* 127:    */  static native void nglVertexAttribI1ivEXT(int paramInt, long paramLong1, long paramLong2);
/* 128:    */  
/* 129:129 */  public static void glVertexAttribI2EXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glVertexAttribI2ivEXT;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    BufferChecks.checkBuffer(v, 2);
/* 133:133 */    nglVertexAttribI2ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static native void nglVertexAttribI2ivEXT(int paramInt, long paramLong1, long paramLong2);
/* 137:    */  
/* 138:138 */  public static void glVertexAttribI3EXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 139:139 */    long function_pointer = caps.glVertexAttribI3ivEXT;
/* 140:140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 141:141 */    BufferChecks.checkBuffer(v, 3);
/* 142:142 */    nglVertexAttribI3ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 143:    */  }
/* 144:    */  
/* 145:    */  static native void nglVertexAttribI3ivEXT(int paramInt, long paramLong1, long paramLong2);
/* 146:    */  
/* 147:147 */  public static void glVertexAttribI4EXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 148:148 */    long function_pointer = caps.glVertexAttribI4ivEXT;
/* 149:149 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 150:150 */    BufferChecks.checkBuffer(v, 4);
/* 151:151 */    nglVertexAttribI4ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 152:    */  }
/* 153:    */  
/* 154:    */  static native void nglVertexAttribI4ivEXT(int paramInt, long paramLong1, long paramLong2);
/* 155:    */  
/* 156:156 */  public static void glVertexAttribI1uEXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 157:157 */    long function_pointer = caps.glVertexAttribI1uivEXT;
/* 158:158 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 159:159 */    BufferChecks.checkBuffer(v, 1);
/* 160:160 */    nglVertexAttribI1uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 161:    */  }
/* 162:    */  
/* 163:    */  static native void nglVertexAttribI1uivEXT(int paramInt, long paramLong1, long paramLong2);
/* 164:    */  
/* 165:165 */  public static void glVertexAttribI2uEXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 166:166 */    long function_pointer = caps.glVertexAttribI2uivEXT;
/* 167:167 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 168:168 */    BufferChecks.checkBuffer(v, 2);
/* 169:169 */    nglVertexAttribI2uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native void nglVertexAttribI2uivEXT(int paramInt, long paramLong1, long paramLong2);
/* 173:    */  
/* 174:174 */  public static void glVertexAttribI3uEXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glVertexAttribI3uivEXT;
/* 176:176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    BufferChecks.checkBuffer(v, 3);
/* 178:178 */    nglVertexAttribI3uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 179:    */  }
/* 180:    */  
/* 181:    */  static native void nglVertexAttribI3uivEXT(int paramInt, long paramLong1, long paramLong2);
/* 182:    */  
/* 183:183 */  public static void glVertexAttribI4uEXT(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 184:184 */    long function_pointer = caps.glVertexAttribI4uivEXT;
/* 185:185 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 186:186 */    BufferChecks.checkBuffer(v, 4);
/* 187:187 */    nglVertexAttribI4uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 188:    */  }
/* 189:    */  
/* 190:    */  static native void nglVertexAttribI4uivEXT(int paramInt, long paramLong1, long paramLong2);
/* 191:    */  
/* 192:192 */  public static void glVertexAttribI4EXT(int index, ByteBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 193:193 */    long function_pointer = caps.glVertexAttribI4bvEXT;
/* 194:194 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 195:195 */    BufferChecks.checkBuffer(v, 4);
/* 196:196 */    nglVertexAttribI4bvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 197:    */  }
/* 198:    */  
/* 199:    */  static native void nglVertexAttribI4bvEXT(int paramInt, long paramLong1, long paramLong2);
/* 200:    */  
/* 201:201 */  public static void glVertexAttribI4EXT(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 202:202 */    long function_pointer = caps.glVertexAttribI4svEXT;
/* 203:203 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 204:204 */    BufferChecks.checkBuffer(v, 4);
/* 205:205 */    nglVertexAttribI4svEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 206:    */  }
/* 207:    */  
/* 208:    */  static native void nglVertexAttribI4svEXT(int paramInt, long paramLong1, long paramLong2);
/* 209:    */  
/* 210:210 */  public static void glVertexAttribI4uEXT(int index, ByteBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 211:211 */    long function_pointer = caps.glVertexAttribI4ubvEXT;
/* 212:212 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 213:213 */    BufferChecks.checkBuffer(v, 4);
/* 214:214 */    nglVertexAttribI4ubvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 215:    */  }
/* 216:    */  
/* 217:    */  static native void nglVertexAttribI4ubvEXT(int paramInt, long paramLong1, long paramLong2);
/* 218:    */  
/* 219:219 */  public static void glVertexAttribI4uEXT(int index, ShortBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 220:220 */    long function_pointer = caps.glVertexAttribI4usvEXT;
/* 221:221 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 222:222 */    BufferChecks.checkBuffer(v, 4);
/* 223:223 */    nglVertexAttribI4usvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/* 224:    */  }
/* 225:    */  
/* 226:    */  static native void nglVertexAttribI4usvEXT(int paramInt, long paramLong1, long paramLong2);
/* 227:    */  
/* 228:228 */  public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, ByteBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 229:229 */    long function_pointer = caps.glVertexAttribIPointerEXT;
/* 230:230 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 231:231 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 232:232 */    BufferChecks.checkDirect(buffer);
/* 233:233 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 234:234 */    nglVertexAttribIPointerEXT(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 235:    */  }
/* 236:    */  
/* 237:237 */  public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, IntBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 238:238 */    long function_pointer = caps.glVertexAttribIPointerEXT;
/* 239:239 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 240:240 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 241:241 */    BufferChecks.checkDirect(buffer);
/* 242:242 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 243:243 */    nglVertexAttribIPointerEXT(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/* 244:    */  }
/* 245:    */  
/* 246:246 */  public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, ShortBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 247:247 */    long function_pointer = caps.glVertexAttribIPointerEXT;
/* 248:248 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 249:249 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 250:250 */    BufferChecks.checkDirect(buffer);
/* 251:251 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 252:252 */    nglVertexAttribIPointerEXT(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer); }
/* 253:    */  
/* 254:    */  static native void nglVertexAttribIPointerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 255:    */  
/* 256:256 */  public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 257:257 */    long function_pointer = caps.glVertexAttribIPointerEXT;
/* 258:258 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 259:259 */    GLChecks.ensureArrayVBOenabled(caps);
/* 260:260 */    nglVertexAttribIPointerEXTBO(index, size, type, stride, buffer_buffer_offset, function_pointer);
/* 261:    */  }
/* 262:    */  
/* 263:    */  static native void nglVertexAttribIPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 264:    */  
/* 265:265 */  public static void glGetVertexAttribIEXT(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 266:266 */    long function_pointer = caps.glGetVertexAttribIivEXT;
/* 267:267 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 268:268 */    BufferChecks.checkBuffer(params, 4);
/* 269:269 */    nglGetVertexAttribIivEXT(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 270:    */  }
/* 271:    */  
/* 272:    */  static native void nglGetVertexAttribIivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 273:    */  
/* 274:274 */  public static void glGetVertexAttribIuEXT(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 275:275 */    long function_pointer = caps.glGetVertexAttribIuivEXT;
/* 276:276 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 277:277 */    BufferChecks.checkBuffer(params, 4);
/* 278:278 */    nglGetVertexAttribIuivEXT(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 279:    */  }
/* 280:    */  
/* 281:    */  static native void nglGetVertexAttribIuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 282:    */  
/* 283:283 */  public static void glUniform1uiEXT(int location, int v0) { ContextCapabilities caps = GLContext.getCapabilities();
/* 284:284 */    long function_pointer = caps.glUniform1uiEXT;
/* 285:285 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 286:286 */    nglUniform1uiEXT(location, v0, function_pointer);
/* 287:    */  }
/* 288:    */  
/* 289:    */  static native void nglUniform1uiEXT(int paramInt1, int paramInt2, long paramLong);
/* 290:    */  
/* 291:291 */  public static void glUniform2uiEXT(int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 292:292 */    long function_pointer = caps.glUniform2uiEXT;
/* 293:293 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 294:294 */    nglUniform2uiEXT(location, v0, v1, function_pointer);
/* 295:    */  }
/* 296:    */  
/* 297:    */  static native void nglUniform2uiEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 298:    */  
/* 299:299 */  public static void glUniform3uiEXT(int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 300:300 */    long function_pointer = caps.glUniform3uiEXT;
/* 301:301 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 302:302 */    nglUniform3uiEXT(location, v0, v1, v2, function_pointer);
/* 303:    */  }
/* 304:    */  
/* 305:    */  static native void nglUniform3uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 306:    */  
/* 307:307 */  public static void glUniform4uiEXT(int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 308:308 */    long function_pointer = caps.glUniform4uiEXT;
/* 309:309 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 310:310 */    nglUniform4uiEXT(location, v0, v1, v2, v3, function_pointer);
/* 311:    */  }
/* 312:    */  
/* 313:    */  static native void nglUniform4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 314:    */  
/* 315:315 */  public static void glUniform1uEXT(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 316:316 */    long function_pointer = caps.glUniform1uivEXT;
/* 317:317 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 318:318 */    BufferChecks.checkDirect(value);
/* 319:319 */    nglUniform1uivEXT(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 320:    */  }
/* 321:    */  
/* 322:    */  static native void nglUniform1uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 323:    */  
/* 324:324 */  public static void glUniform2uEXT(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 325:325 */    long function_pointer = caps.glUniform2uivEXT;
/* 326:326 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 327:327 */    BufferChecks.checkDirect(value);
/* 328:328 */    nglUniform2uivEXT(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 329:    */  }
/* 330:    */  
/* 331:    */  static native void nglUniform2uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 332:    */  
/* 333:333 */  public static void glUniform3uEXT(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 334:334 */    long function_pointer = caps.glUniform3uivEXT;
/* 335:335 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 336:336 */    BufferChecks.checkDirect(value);
/* 337:337 */    nglUniform3uivEXT(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 338:    */  }
/* 339:    */  
/* 340:    */  static native void nglUniform3uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 341:    */  
/* 342:342 */  public static void glUniform4uEXT(int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 343:343 */    long function_pointer = caps.glUniform4uivEXT;
/* 344:344 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 345:345 */    BufferChecks.checkDirect(value);
/* 346:346 */    nglUniform4uivEXT(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 347:    */  }
/* 348:    */  
/* 349:    */  static native void nglUniform4uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 350:    */  
/* 351:351 */  public static void glGetUniformuEXT(int program, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 352:352 */    long function_pointer = caps.glGetUniformuivEXT;
/* 353:353 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 354:354 */    BufferChecks.checkDirect(params);
/* 355:355 */    nglGetUniformuivEXT(program, location, MemoryUtil.getAddress(params), function_pointer);
/* 356:    */  }
/* 357:    */  
/* 358:    */  static native void nglGetUniformuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 359:    */  
/* 360:360 */  public static void glBindFragDataLocationEXT(int program, int colorNumber, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/* 361:361 */    long function_pointer = caps.glBindFragDataLocationEXT;
/* 362:362 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 363:363 */    BufferChecks.checkDirect(name);
/* 364:364 */    BufferChecks.checkNullTerminated(name);
/* 365:365 */    nglBindFragDataLocationEXT(program, colorNumber, MemoryUtil.getAddress(name), function_pointer);
/* 366:    */  }
/* 367:    */  
/* 368:    */  static native void nglBindFragDataLocationEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 369:    */  
/* 370:    */  public static void glBindFragDataLocationEXT(int program, int colorNumber, CharSequence name) {
/* 371:371 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 372:372 */    long function_pointer = caps.glBindFragDataLocationEXT;
/* 373:373 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 374:374 */    nglBindFragDataLocationEXT(program, colorNumber, APIUtil.getBufferNT(caps, name), function_pointer);
/* 375:    */  }
/* 376:    */  
/* 377:    */  public static int glGetFragDataLocationEXT(int program, ByteBuffer name) {
/* 378:378 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 379:379 */    long function_pointer = caps.glGetFragDataLocationEXT;
/* 380:380 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 381:381 */    BufferChecks.checkDirect(name);
/* 382:382 */    BufferChecks.checkNullTerminated(name);
/* 383:383 */    int __result = nglGetFragDataLocationEXT(program, MemoryUtil.getAddress(name), function_pointer);
/* 384:384 */    return __result;
/* 385:    */  }
/* 386:    */  
/* 387:    */  static native int nglGetFragDataLocationEXT(int paramInt, long paramLong1, long paramLong2);
/* 388:    */  
/* 389:    */  public static int glGetFragDataLocationEXT(int program, CharSequence name) {
/* 390:390 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 391:391 */    long function_pointer = caps.glGetFragDataLocationEXT;
/* 392:392 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 393:393 */    int __result = nglGetFragDataLocationEXT(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 394:394 */    return __result;
/* 395:    */  }
/* 396:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTGpuShader4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */