/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */import org.lwjgl.BufferChecks;
/*   9:    */import org.lwjgl.MemoryUtil;
/*  10:    */
/*  17:    */public final class GL13
/*  18:    */{
/*  19:    */  public static final int GL_TEXTURE0 = 33984;
/*  20:    */  public static final int GL_TEXTURE1 = 33985;
/*  21:    */  public static final int GL_TEXTURE2 = 33986;
/*  22:    */  public static final int GL_TEXTURE3 = 33987;
/*  23:    */  public static final int GL_TEXTURE4 = 33988;
/*  24:    */  public static final int GL_TEXTURE5 = 33989;
/*  25:    */  public static final int GL_TEXTURE6 = 33990;
/*  26:    */  public static final int GL_TEXTURE7 = 33991;
/*  27:    */  public static final int GL_TEXTURE8 = 33992;
/*  28:    */  public static final int GL_TEXTURE9 = 33993;
/*  29:    */  public static final int GL_TEXTURE10 = 33994;
/*  30:    */  public static final int GL_TEXTURE11 = 33995;
/*  31:    */  public static final int GL_TEXTURE12 = 33996;
/*  32:    */  public static final int GL_TEXTURE13 = 33997;
/*  33:    */  public static final int GL_TEXTURE14 = 33998;
/*  34:    */  public static final int GL_TEXTURE15 = 33999;
/*  35:    */  public static final int GL_TEXTURE16 = 34000;
/*  36:    */  public static final int GL_TEXTURE17 = 34001;
/*  37:    */  public static final int GL_TEXTURE18 = 34002;
/*  38:    */  public static final int GL_TEXTURE19 = 34003;
/*  39:    */  public static final int GL_TEXTURE20 = 34004;
/*  40:    */  public static final int GL_TEXTURE21 = 34005;
/*  41:    */  public static final int GL_TEXTURE22 = 34006;
/*  42:    */  public static final int GL_TEXTURE23 = 34007;
/*  43:    */  public static final int GL_TEXTURE24 = 34008;
/*  44:    */  public static final int GL_TEXTURE25 = 34009;
/*  45:    */  public static final int GL_TEXTURE26 = 34010;
/*  46:    */  public static final int GL_TEXTURE27 = 34011;
/*  47:    */  public static final int GL_TEXTURE28 = 34012;
/*  48:    */  public static final int GL_TEXTURE29 = 34013;
/*  49:    */  public static final int GL_TEXTURE30 = 34014;
/*  50:    */  public static final int GL_TEXTURE31 = 34015;
/*  51:    */  public static final int GL_ACTIVE_TEXTURE = 34016;
/*  52:    */  public static final int GL_CLIENT_ACTIVE_TEXTURE = 34017;
/*  53:    */  public static final int GL_MAX_TEXTURE_UNITS = 34018;
/*  54:    */  public static final int GL_NORMAL_MAP = 34065;
/*  55:    */  public static final int GL_REFLECTION_MAP = 34066;
/*  56:    */  public static final int GL_TEXTURE_CUBE_MAP = 34067;
/*  57:    */  public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
/*  58:    */  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
/*  59:    */  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
/*  60:    */  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
/*  61:    */  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
/*  62:    */  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
/*  63:    */  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
/*  64:    */  public static final int GL_PROXY_TEXTURE_CUBE_MAP = 34075;
/*  65:    */  public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
/*  66:    */  public static final int GL_COMPRESSED_ALPHA = 34025;
/*  67:    */  public static final int GL_COMPRESSED_LUMINANCE = 34026;
/*  68:    */  public static final int GL_COMPRESSED_LUMINANCE_ALPHA = 34027;
/*  69:    */  public static final int GL_COMPRESSED_INTENSITY = 34028;
/*  70:    */  public static final int GL_COMPRESSED_RGB = 34029;
/*  71:    */  public static final int GL_COMPRESSED_RGBA = 34030;
/*  72:    */  public static final int GL_TEXTURE_COMPRESSION_HINT = 34031;
/*  73:    */  public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 34464;
/*  74:    */  public static final int GL_TEXTURE_COMPRESSED = 34465;
/*  75:    */  public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
/*  76:    */  public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
/*  77:    */  public static final int GL_MULTISAMPLE = 32925;
/*  78:    */  public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
/*  79:    */  public static final int GL_SAMPLE_ALPHA_TO_ONE = 32927;
/*  80:    */  public static final int GL_SAMPLE_COVERAGE = 32928;
/*  81:    */  public static final int GL_SAMPLE_BUFFERS = 32936;
/*  82:    */  public static final int GL_SAMPLES = 32937;
/*  83:    */  public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
/*  84:    */  public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
/*  85:    */  public static final int GL_MULTISAMPLE_BIT = 536870912;
/*  86:    */  public static final int GL_TRANSPOSE_MODELVIEW_MATRIX = 34019;
/*  87:    */  public static final int GL_TRANSPOSE_PROJECTION_MATRIX = 34020;
/*  88:    */  public static final int GL_TRANSPOSE_TEXTURE_MATRIX = 34021;
/*  89:    */  public static final int GL_TRANSPOSE_COLOR_MATRIX = 34022;
/*  90:    */  public static final int GL_COMBINE = 34160;
/*  91:    */  public static final int GL_COMBINE_RGB = 34161;
/*  92:    */  public static final int GL_COMBINE_ALPHA = 34162;
/*  93:    */  public static final int GL_SOURCE0_RGB = 34176;
/*  94:    */  public static final int GL_SOURCE1_RGB = 34177;
/*  95:    */  public static final int GL_SOURCE2_RGB = 34178;
/*  96:    */  public static final int GL_SOURCE0_ALPHA = 34184;
/*  97:    */  public static final int GL_SOURCE1_ALPHA = 34185;
/*  98:    */  public static final int GL_SOURCE2_ALPHA = 34186;
/*  99:    */  public static final int GL_OPERAND0_RGB = 34192;
/* 100:    */  public static final int GL_OPERAND1_RGB = 34193;
/* 101:    */  public static final int GL_OPERAND2_RGB = 34194;
/* 102:    */  public static final int GL_OPERAND0_ALPHA = 34200;
/* 103:    */  public static final int GL_OPERAND1_ALPHA = 34201;
/* 104:    */  public static final int GL_OPERAND2_ALPHA = 34202;
/* 105:    */  public static final int GL_RGB_SCALE = 34163;
/* 106:    */  public static final int GL_ADD_SIGNED = 34164;
/* 107:    */  public static final int GL_INTERPOLATE = 34165;
/* 108:    */  public static final int GL_SUBTRACT = 34023;
/* 109:    */  public static final int GL_CONSTANT = 34166;
/* 110:    */  public static final int GL_PRIMARY_COLOR = 34167;
/* 111:    */  public static final int GL_PREVIOUS = 34168;
/* 112:    */  public static final int GL_DOT3_RGB = 34478;
/* 113:    */  public static final int GL_DOT3_RGBA = 34479;
/* 114:    */  public static final int GL_CLAMP_TO_BORDER = 33069;
/* 115:    */  
/* 116:    */  public static void glActiveTexture(int texture)
/* 117:    */  {
/* 118:118 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 119:119 */    long function_pointer = caps.glActiveTexture;
/* 120:120 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 121:121 */    nglActiveTexture(texture, function_pointer);
/* 122:    */  }
/* 123:    */  
/* 124:    */  static native void nglActiveTexture(int paramInt, long paramLong);
/* 125:    */  
/* 126:126 */  public static void glClientActiveTexture(int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/* 127:127 */    long function_pointer = caps.glClientActiveTexture;
/* 128:128 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 129:129 */    StateTracker.getReferences(caps).glClientActiveTexture = (texture - 33984);
/* 130:130 */    nglClientActiveTexture(texture, function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:    */  static native void nglClientActiveTexture(int paramInt, long paramLong);
/* 134:    */  
/* 135:135 */  public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glCompressedTexImage1D;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 139:139 */    BufferChecks.checkDirect(data);
/* 140:140 */    nglCompressedTexImage1D(target, level, internalformat, width, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 141:    */  
/* 142:    */  static native void nglCompressedTexImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 143:    */  
/* 144:144 */  public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 145:145 */    long function_pointer = caps.glCompressedTexImage1D;
/* 146:146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 147:147 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 148:148 */    nglCompressedTexImage1DBO(target, level, internalformat, width, border, data_imageSize, data_buffer_offset, function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  static native void nglCompressedTexImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 152:    */  
/* 153:153 */  public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 154:154 */    long function_pointer = caps.glCompressedTexImage2D;
/* 155:155 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 156:156 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 157:157 */    BufferChecks.checkDirect(data);
/* 158:158 */    nglCompressedTexImage2D(target, level, internalformat, width, height, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 159:    */  
/* 160:    */  static native void nglCompressedTexImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 161:    */  
/* 162:162 */  public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 163:163 */    long function_pointer = caps.glCompressedTexImage2D;
/* 164:164 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 165:165 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 166:166 */    nglCompressedTexImage2DBO(target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset, function_pointer);
/* 167:    */  }
/* 168:    */  
/* 169:    */  static native void nglCompressedTexImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 170:    */  
/* 171:171 */  public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 172:172 */    long function_pointer = caps.glCompressedTexImage3D;
/* 173:173 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 174:174 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 175:175 */    BufferChecks.checkDirect(data);
/* 176:176 */    nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 177:    */  
/* 178:    */  static native void nglCompressedTexImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 179:    */  
/* 180:180 */  public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 181:181 */    long function_pointer = caps.glCompressedTexImage3D;
/* 182:182 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 183:183 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 184:184 */    nglCompressedTexImage3DBO(target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset, function_pointer);
/* 185:    */  }
/* 186:    */  
/* 187:    */  static native void nglCompressedTexImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 188:    */  
/* 189:189 */  public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glCompressedTexSubImage1D;
/* 191:191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 193:193 */    BufferChecks.checkDirect(data);
/* 194:194 */    nglCompressedTexSubImage1D(target, level, xoffset, width, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 195:    */  
/* 196:    */  static native void nglCompressedTexSubImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 197:    */  
/* 198:198 */  public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 199:199 */    long function_pointer = caps.glCompressedTexSubImage1D;
/* 200:200 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 201:201 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 202:202 */    nglCompressedTexSubImage1DBO(target, level, xoffset, width, format, data_imageSize, data_buffer_offset, function_pointer);
/* 203:    */  }
/* 204:    */  
/* 205:    */  static native void nglCompressedTexSubImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 206:    */  
/* 207:207 */  public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 208:208 */    long function_pointer = caps.glCompressedTexSubImage2D;
/* 209:209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 210:210 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 211:211 */    BufferChecks.checkDirect(data);
/* 212:212 */    nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 213:    */  
/* 214:    */  static native void nglCompressedTexSubImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 215:    */  
/* 216:216 */  public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 217:217 */    long function_pointer = caps.glCompressedTexSubImage2D;
/* 218:218 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 219:219 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 220:220 */    nglCompressedTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset, function_pointer);
/* 221:    */  }
/* 222:    */  
/* 223:    */  static native void nglCompressedTexSubImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 224:    */  
/* 225:225 */  public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 226:226 */    long function_pointer = caps.glCompressedTexSubImage3D;
/* 227:227 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 228:228 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 229:229 */    BufferChecks.checkDirect(data);
/* 230:230 */    nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 231:    */  
/* 232:    */  static native void nglCompressedTexSubImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 233:    */  
/* 234:234 */  public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 235:235 */    long function_pointer = caps.glCompressedTexSubImage3D;
/* 236:236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 237:237 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 238:238 */    nglCompressedTexSubImage3DBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset, function_pointer);
/* 239:    */  }
/* 240:    */  
/* 241:    */  static native void nglCompressedTexSubImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 242:    */  
/* 243:243 */  public static void glGetCompressedTexImage(int target, int lod, ByteBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 244:244 */    long function_pointer = caps.glGetCompressedTexImage;
/* 245:245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 246:246 */    GLChecks.ensurePackPBOdisabled(caps);
/* 247:247 */    BufferChecks.checkDirect(img);
/* 248:248 */    nglGetCompressedTexImage(target, lod, MemoryUtil.getAddress(img), function_pointer);
/* 249:    */  }
/* 250:    */  
/* 251:251 */  public static void glGetCompressedTexImage(int target, int lod, IntBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 252:252 */    long function_pointer = caps.glGetCompressedTexImage;
/* 253:253 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 254:254 */    GLChecks.ensurePackPBOdisabled(caps);
/* 255:255 */    BufferChecks.checkDirect(img);
/* 256:256 */    nglGetCompressedTexImage(target, lod, MemoryUtil.getAddress(img), function_pointer);
/* 257:    */  }
/* 258:    */  
/* 259:259 */  public static void glGetCompressedTexImage(int target, int lod, ShortBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 260:260 */    long function_pointer = caps.glGetCompressedTexImage;
/* 261:261 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 262:262 */    GLChecks.ensurePackPBOdisabled(caps);
/* 263:263 */    BufferChecks.checkDirect(img);
/* 264:264 */    nglGetCompressedTexImage(target, lod, MemoryUtil.getAddress(img), function_pointer); }
/* 265:    */  
/* 266:    */  static native void nglGetCompressedTexImage(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 267:    */  
/* 268:268 */  public static void glGetCompressedTexImage(int target, int lod, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 269:269 */    long function_pointer = caps.glGetCompressedTexImage;
/* 270:270 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 271:271 */    GLChecks.ensurePackPBOenabled(caps);
/* 272:272 */    nglGetCompressedTexImageBO(target, lod, img_buffer_offset, function_pointer);
/* 273:    */  }
/* 274:    */  
/* 275:    */  static native void nglGetCompressedTexImageBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 276:    */  
/* 277:277 */  public static void glMultiTexCoord1f(int target, float s) { ContextCapabilities caps = GLContext.getCapabilities();
/* 278:278 */    long function_pointer = caps.glMultiTexCoord1f;
/* 279:279 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 280:280 */    nglMultiTexCoord1f(target, s, function_pointer);
/* 281:    */  }
/* 282:    */  
/* 283:    */  static native void nglMultiTexCoord1f(int paramInt, float paramFloat, long paramLong);
/* 284:    */  
/* 285:285 */  public static void glMultiTexCoord1d(int target, double s) { ContextCapabilities caps = GLContext.getCapabilities();
/* 286:286 */    long function_pointer = caps.glMultiTexCoord1d;
/* 287:287 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 288:288 */    nglMultiTexCoord1d(target, s, function_pointer);
/* 289:    */  }
/* 290:    */  
/* 291:    */  static native void nglMultiTexCoord1d(int paramInt, double paramDouble, long paramLong);
/* 292:    */  
/* 293:293 */  public static void glMultiTexCoord2f(int target, float s, float t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 294:294 */    long function_pointer = caps.glMultiTexCoord2f;
/* 295:295 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 296:296 */    nglMultiTexCoord2f(target, s, t, function_pointer);
/* 297:    */  }
/* 298:    */  
/* 299:    */  static native void nglMultiTexCoord2f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 300:    */  
/* 301:301 */  public static void glMultiTexCoord2d(int target, double s, double t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 302:302 */    long function_pointer = caps.glMultiTexCoord2d;
/* 303:303 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 304:304 */    nglMultiTexCoord2d(target, s, t, function_pointer);
/* 305:    */  }
/* 306:    */  
/* 307:    */  static native void nglMultiTexCoord2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/* 308:    */  
/* 309:309 */  public static void glMultiTexCoord3f(int target, float s, float t, float r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 310:310 */    long function_pointer = caps.glMultiTexCoord3f;
/* 311:311 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 312:312 */    nglMultiTexCoord3f(target, s, t, r, function_pointer);
/* 313:    */  }
/* 314:    */  
/* 315:    */  static native void nglMultiTexCoord3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 316:    */  
/* 317:317 */  public static void glMultiTexCoord3d(int target, double s, double t, double r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 318:318 */    long function_pointer = caps.glMultiTexCoord3d;
/* 319:319 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 320:320 */    nglMultiTexCoord3d(target, s, t, r, function_pointer);
/* 321:    */  }
/* 322:    */  
/* 323:    */  static native void nglMultiTexCoord3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 324:    */  
/* 325:325 */  public static void glMultiTexCoord4f(int target, float s, float t, float r, float q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 326:326 */    long function_pointer = caps.glMultiTexCoord4f;
/* 327:327 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 328:328 */    nglMultiTexCoord4f(target, s, t, r, q, function_pointer);
/* 329:    */  }
/* 330:    */  
/* 331:    */  static native void nglMultiTexCoord4f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 332:    */  
/* 333:333 */  public static void glMultiTexCoord4d(int target, double s, double t, double r, double q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 334:334 */    long function_pointer = caps.glMultiTexCoord4d;
/* 335:335 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 336:336 */    nglMultiTexCoord4d(target, s, t, r, q, function_pointer);
/* 337:    */  }
/* 338:    */  
/* 339:    */  static native void nglMultiTexCoord4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 340:    */  
/* 341:341 */  public static void glLoadTransposeMatrix(FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 342:342 */    long function_pointer = caps.glLoadTransposeMatrixf;
/* 343:343 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 344:344 */    BufferChecks.checkBuffer(m, 16);
/* 345:345 */    nglLoadTransposeMatrixf(MemoryUtil.getAddress(m), function_pointer);
/* 346:    */  }
/* 347:    */  
/* 348:    */  static native void nglLoadTransposeMatrixf(long paramLong1, long paramLong2);
/* 349:    */  
/* 350:350 */  public static void glLoadTransposeMatrix(DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 351:351 */    long function_pointer = caps.glLoadTransposeMatrixd;
/* 352:352 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 353:353 */    BufferChecks.checkBuffer(m, 16);
/* 354:354 */    nglLoadTransposeMatrixd(MemoryUtil.getAddress(m), function_pointer);
/* 355:    */  }
/* 356:    */  
/* 357:    */  static native void nglLoadTransposeMatrixd(long paramLong1, long paramLong2);
/* 358:    */  
/* 359:359 */  public static void glMultTransposeMatrix(FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 360:360 */    long function_pointer = caps.glMultTransposeMatrixf;
/* 361:361 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 362:362 */    BufferChecks.checkBuffer(m, 16);
/* 363:363 */    nglMultTransposeMatrixf(MemoryUtil.getAddress(m), function_pointer);
/* 364:    */  }
/* 365:    */  
/* 366:    */  static native void nglMultTransposeMatrixf(long paramLong1, long paramLong2);
/* 367:    */  
/* 368:368 */  public static void glMultTransposeMatrix(DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 369:369 */    long function_pointer = caps.glMultTransposeMatrixd;
/* 370:370 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 371:371 */    BufferChecks.checkBuffer(m, 16);
/* 372:372 */    nglMultTransposeMatrixd(MemoryUtil.getAddress(m), function_pointer);
/* 373:    */  }
/* 374:    */  
/* 375:    */  static native void nglMultTransposeMatrixd(long paramLong1, long paramLong2);
/* 376:    */  
/* 377:377 */  public static void glSampleCoverage(float value, boolean invert) { ContextCapabilities caps = GLContext.getCapabilities();
/* 378:378 */    long function_pointer = caps.glSampleCoverage;
/* 379:379 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 380:380 */    nglSampleCoverage(value, invert, function_pointer);
/* 381:    */  }
/* 382:    */  
/* 383:    */  static native void nglSampleCoverage(float paramFloat, boolean paramBoolean, long paramLong);
/* 384:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL13
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */