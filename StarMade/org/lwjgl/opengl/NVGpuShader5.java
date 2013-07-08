/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.LongBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  17:    */public final class NVGpuShader5
/*  18:    */{
/*  19:    */  public static final int GL_INT64_NV = 5134;
/*  20:    */  public static final int GL_UNSIGNED_INT64_NV = 5135;
/*  21:    */  public static final int GL_INT8_NV = 36832;
/*  22:    */  public static final int GL_INT8_VEC2_NV = 36833;
/*  23:    */  public static final int GL_INT8_VEC3_NV = 36834;
/*  24:    */  public static final int GL_INT8_VEC4_NV = 36835;
/*  25:    */  public static final int GL_INT16_NV = 36836;
/*  26:    */  public static final int GL_INT16_VEC2_NV = 36837;
/*  27:    */  public static final int GL_INT16_VEC3_NV = 36838;
/*  28:    */  public static final int GL_INT16_VEC4_NV = 36839;
/*  29:    */  public static final int GL_INT64_VEC2_NV = 36841;
/*  30:    */  public static final int GL_INT64_VEC3_NV = 36842;
/*  31:    */  public static final int GL_INT64_VEC4_NV = 36843;
/*  32:    */  public static final int GL_UNSIGNED_INT8_NV = 36844;
/*  33:    */  public static final int GL_UNSIGNED_INT8_VEC2_NV = 36845;
/*  34:    */  public static final int GL_UNSIGNED_INT8_VEC3_NV = 36846;
/*  35:    */  public static final int GL_UNSIGNED_INT8_VEC4_NV = 36847;
/*  36:    */  public static final int GL_UNSIGNED_INT16_NV = 36848;
/*  37:    */  public static final int GL_UNSIGNED_INT16_VEC2_NV = 36849;
/*  38:    */  public static final int GL_UNSIGNED_INT16_VEC3_NV = 36850;
/*  39:    */  public static final int GL_UNSIGNED_INT16_VEC4_NV = 36851;
/*  40:    */  public static final int GL_UNSIGNED_INT64_VEC2_NV = 36853;
/*  41:    */  public static final int GL_UNSIGNED_INT64_VEC3_NV = 36854;
/*  42:    */  public static final int GL_UNSIGNED_INT64_VEC4_NV = 36855;
/*  43:    */  public static final int GL_FLOAT16_NV = 36856;
/*  44:    */  public static final int GL_FLOAT16_VEC2_NV = 36857;
/*  45:    */  public static final int GL_FLOAT16_VEC3_NV = 36858;
/*  46:    */  public static final int GL_FLOAT16_VEC4_NV = 36859;
/*  47:    */  public static final int GL_PATCHES = 14;
/*  48:    */  
/*  49:    */  public static void glUniform1i64NV(int location, long x)
/*  50:    */  {
/*  51: 51 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  52: 52 */    long function_pointer = caps.glUniform1i64NV;
/*  53: 53 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  54: 54 */    nglUniform1i64NV(location, x, function_pointer);
/*  55:    */  }
/*  56:    */  
/*  57:    */  static native void nglUniform1i64NV(int paramInt, long paramLong1, long paramLong2);
/*  58:    */  
/*  59: 59 */  public static void glUniform2i64NV(int location, long x, long y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  60: 60 */    long function_pointer = caps.glUniform2i64NV;
/*  61: 61 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  62: 62 */    nglUniform2i64NV(location, x, y, function_pointer);
/*  63:    */  }
/*  64:    */  
/*  65:    */  static native void nglUniform2i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*  66:    */  
/*  67: 67 */  public static void glUniform3i64NV(int location, long x, long y, long z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  68: 68 */    long function_pointer = caps.glUniform3i64NV;
/*  69: 69 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  70: 70 */    nglUniform3i64NV(location, x, y, z, function_pointer);
/*  71:    */  }
/*  72:    */  
/*  73:    */  static native void nglUniform3i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*  74:    */  
/*  75: 75 */  public static void glUniform4i64NV(int location, long x, long y, long z, long w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  76: 76 */    long function_pointer = caps.glUniform4i64NV;
/*  77: 77 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  78: 78 */    nglUniform4i64NV(location, x, y, z, w, function_pointer);
/*  79:    */  }
/*  80:    */  
/*  81:    */  static native void nglUniform4i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  82:    */  
/*  83: 83 */  public static void glUniform1NV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  84: 84 */    long function_pointer = caps.glUniform1i64vNV;
/*  85: 85 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  86: 86 */    BufferChecks.checkDirect(value);
/*  87: 87 */    nglUniform1i64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglUniform1i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  91:    */  
/*  92: 92 */  public static void glUniform2NV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  93: 93 */    long function_pointer = caps.glUniform2i64vNV;
/*  94: 94 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  95: 95 */    BufferChecks.checkDirect(value);
/*  96: 96 */    nglUniform2i64vNV(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*  97:    */  }
/*  98:    */  
/*  99:    */  static native void nglUniform2i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 100:    */  
/* 101:101 */  public static void glUniform3NV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glUniform3i64vNV;
/* 103:103 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    BufferChecks.checkDirect(value);
/* 105:105 */    nglUniform3i64vNV(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglUniform3i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 109:    */  
/* 110:110 */  public static void glUniform4NV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glUniform4i64vNV;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    BufferChecks.checkDirect(value);
/* 114:114 */    nglUniform4i64vNV(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 115:    */  }
/* 116:    */  
/* 117:    */  static native void nglUniform4i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 118:    */  
/* 119:119 */  public static void glUniform1ui64NV(int location, long x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 120:120 */    long function_pointer = caps.glUniform1ui64NV;
/* 121:121 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 122:122 */    nglUniform1ui64NV(location, x, function_pointer);
/* 123:    */  }
/* 124:    */  
/* 125:    */  static native void nglUniform1ui64NV(int paramInt, long paramLong1, long paramLong2);
/* 126:    */  
/* 127:127 */  public static void glUniform2ui64NV(int location, long x, long y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 128:128 */    long function_pointer = caps.glUniform2ui64NV;
/* 129:129 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 130:130 */    nglUniform2ui64NV(location, x, y, function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:    */  static native void nglUniform2ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 134:    */  
/* 135:135 */  public static void glUniform3ui64NV(int location, long x, long y, long z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glUniform3ui64NV;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    nglUniform3ui64NV(location, x, y, z, function_pointer);
/* 139:    */  }
/* 140:    */  
/* 141:    */  static native void nglUniform3ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 142:    */  
/* 143:143 */  public static void glUniform4ui64NV(int location, long x, long y, long z, long w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 144:144 */    long function_pointer = caps.glUniform4ui64NV;
/* 145:145 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 146:146 */    nglUniform4ui64NV(location, x, y, z, w, function_pointer);
/* 147:    */  }
/* 148:    */  
/* 149:    */  static native void nglUniform4ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 150:    */  
/* 151:151 */  public static void glUniform1uNV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 152:152 */    long function_pointer = caps.glUniform1ui64vNV;
/* 153:153 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 154:154 */    BufferChecks.checkDirect(value);
/* 155:155 */    nglUniform1ui64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 156:    */  }
/* 157:    */  
/* 158:    */  static native void nglUniform1ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 159:    */  
/* 160:160 */  public static void glUniform2uNV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 161:161 */    long function_pointer = caps.glUniform2ui64vNV;
/* 162:162 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 163:163 */    BufferChecks.checkDirect(value);
/* 164:164 */    nglUniform2ui64vNV(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 165:    */  }
/* 166:    */  
/* 167:    */  static native void nglUniform2ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 168:    */  
/* 169:169 */  public static void glUniform3uNV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 170:170 */    long function_pointer = caps.glUniform3ui64vNV;
/* 171:171 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 172:172 */    BufferChecks.checkDirect(value);
/* 173:173 */    nglUniform3ui64vNV(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 174:    */  }
/* 175:    */  
/* 176:    */  static native void nglUniform3ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 177:    */  
/* 178:178 */  public static void glUniform4uNV(int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 179:179 */    long function_pointer = caps.glUniform4ui64vNV;
/* 180:180 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 181:181 */    BufferChecks.checkDirect(value);
/* 182:182 */    nglUniform4ui64vNV(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 183:    */  }
/* 184:    */  
/* 185:    */  static native void nglUniform4ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 186:    */  
/* 187:187 */  public static void glGetUniformNV(int program, int location, LongBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 188:188 */    long function_pointer = caps.glGetUniformi64vNV;
/* 189:189 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 190:190 */    BufferChecks.checkBuffer(params, 1);
/* 191:191 */    nglGetUniformi64vNV(program, location, MemoryUtil.getAddress(params), function_pointer);
/* 192:    */  }
/* 193:    */  
/* 194:    */  static native void nglGetUniformi64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 195:    */  
/* 196:196 */  public static void glGetUniformuNV(int program, int location, LongBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 197:197 */    long function_pointer = caps.glGetUniformui64vNV;
/* 198:198 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 199:199 */    BufferChecks.checkBuffer(params, 1);
/* 200:200 */    nglGetUniformui64vNV(program, location, MemoryUtil.getAddress(params), function_pointer);
/* 201:    */  }
/* 202:    */  
/* 203:    */  static native void nglGetUniformui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 204:    */  
/* 205:205 */  public static void glProgramUniform1i64NV(int program, int location, long x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 206:206 */    long function_pointer = caps.glProgramUniform1i64NV;
/* 207:207 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 208:208 */    nglProgramUniform1i64NV(program, location, x, function_pointer);
/* 209:    */  }
/* 210:    */  
/* 211:    */  static native void nglProgramUniform1i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 212:    */  
/* 213:213 */  public static void glProgramUniform2i64NV(int program, int location, long x, long y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 214:214 */    long function_pointer = caps.glProgramUniform2i64NV;
/* 215:215 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 216:216 */    nglProgramUniform2i64NV(program, location, x, y, function_pointer);
/* 217:    */  }
/* 218:    */  
/* 219:    */  static native void nglProgramUniform2i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 220:    */  
/* 221:221 */  public static void glProgramUniform3i64NV(int program, int location, long x, long y, long z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 222:222 */    long function_pointer = caps.glProgramUniform3i64NV;
/* 223:223 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 224:224 */    nglProgramUniform3i64NV(program, location, x, y, z, function_pointer);
/* 225:    */  }
/* 226:    */  
/* 227:    */  static native void nglProgramUniform3i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 228:    */  
/* 229:229 */  public static void glProgramUniform4i64NV(int program, int location, long x, long y, long z, long w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 230:230 */    long function_pointer = caps.glProgramUniform4i64NV;
/* 231:231 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 232:232 */    nglProgramUniform4i64NV(program, location, x, y, z, w, function_pointer);
/* 233:    */  }
/* 234:    */  
/* 235:    */  static native void nglProgramUniform4i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 236:    */  
/* 237:237 */  public static void glProgramUniform1NV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 238:238 */    long function_pointer = caps.glProgramUniform1i64vNV;
/* 239:239 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 240:240 */    BufferChecks.checkDirect(value);
/* 241:241 */    nglProgramUniform1i64vNV(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 242:    */  }
/* 243:    */  
/* 244:    */  static native void nglProgramUniform1i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 245:    */  
/* 246:246 */  public static void glProgramUniform2NV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 247:247 */    long function_pointer = caps.glProgramUniform2i64vNV;
/* 248:248 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 249:249 */    BufferChecks.checkDirect(value);
/* 250:250 */    nglProgramUniform2i64vNV(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 251:    */  }
/* 252:    */  
/* 253:    */  static native void nglProgramUniform2i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 254:    */  
/* 255:255 */  public static void glProgramUniform3NV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 256:256 */    long function_pointer = caps.glProgramUniform3i64vNV;
/* 257:257 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 258:258 */    BufferChecks.checkDirect(value);
/* 259:259 */    nglProgramUniform3i64vNV(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 260:    */  }
/* 261:    */  
/* 262:    */  static native void nglProgramUniform3i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 263:    */  
/* 264:264 */  public static void glProgramUniform4NV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 265:265 */    long function_pointer = caps.glProgramUniform4i64vNV;
/* 266:266 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 267:267 */    BufferChecks.checkDirect(value);
/* 268:268 */    nglProgramUniform4i64vNV(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 269:    */  }
/* 270:    */  
/* 271:    */  static native void nglProgramUniform4i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 272:    */  
/* 273:273 */  public static void glProgramUniform1ui64NV(int program, int location, long x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 274:274 */    long function_pointer = caps.glProgramUniform1ui64NV;
/* 275:275 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 276:276 */    nglProgramUniform1ui64NV(program, location, x, function_pointer);
/* 277:    */  }
/* 278:    */  
/* 279:    */  static native void nglProgramUniform1ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 280:    */  
/* 281:281 */  public static void glProgramUniform2ui64NV(int program, int location, long x, long y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 282:282 */    long function_pointer = caps.glProgramUniform2ui64NV;
/* 283:283 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 284:284 */    nglProgramUniform2ui64NV(program, location, x, y, function_pointer);
/* 285:    */  }
/* 286:    */  
/* 287:    */  static native void nglProgramUniform2ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 288:    */  
/* 289:289 */  public static void glProgramUniform3ui64NV(int program, int location, long x, long y, long z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 290:290 */    long function_pointer = caps.glProgramUniform3ui64NV;
/* 291:291 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 292:292 */    nglProgramUniform3ui64NV(program, location, x, y, z, function_pointer);
/* 293:    */  }
/* 294:    */  
/* 295:    */  static native void nglProgramUniform3ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 296:    */  
/* 297:297 */  public static void glProgramUniform4ui64NV(int program, int location, long x, long y, long z, long w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 298:298 */    long function_pointer = caps.glProgramUniform4ui64NV;
/* 299:299 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 300:300 */    nglProgramUniform4ui64NV(program, location, x, y, z, w, function_pointer);
/* 301:    */  }
/* 302:    */  
/* 303:    */  static native void nglProgramUniform4ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 304:    */  
/* 305:305 */  public static void glProgramUniform1uNV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 306:306 */    long function_pointer = caps.glProgramUniform1ui64vNV;
/* 307:307 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 308:308 */    BufferChecks.checkDirect(value);
/* 309:309 */    nglProgramUniform1ui64vNV(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 310:    */  }
/* 311:    */  
/* 312:    */  static native void nglProgramUniform1ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 313:    */  
/* 314:314 */  public static void glProgramUniform2uNV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 315:315 */    long function_pointer = caps.glProgramUniform2ui64vNV;
/* 316:316 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 317:317 */    BufferChecks.checkDirect(value);
/* 318:318 */    nglProgramUniform2ui64vNV(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 319:    */  }
/* 320:    */  
/* 321:    */  static native void nglProgramUniform2ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 322:    */  
/* 323:323 */  public static void glProgramUniform3uNV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 324:324 */    long function_pointer = caps.glProgramUniform3ui64vNV;
/* 325:325 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 326:326 */    BufferChecks.checkDirect(value);
/* 327:327 */    nglProgramUniform3ui64vNV(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 328:    */  }
/* 329:    */  
/* 330:    */  static native void nglProgramUniform3ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 331:    */  
/* 332:332 */  public static void glProgramUniform4uNV(int program, int location, LongBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 333:333 */    long function_pointer = caps.glProgramUniform4ui64vNV;
/* 334:334 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 335:335 */    BufferChecks.checkDirect(value);
/* 336:336 */    nglProgramUniform4ui64vNV(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 337:    */  }
/* 338:    */  
/* 339:    */  static native void nglProgramUniform4ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 340:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVGpuShader5
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */