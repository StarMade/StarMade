/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.BufferChecks;
/*   8:    */import org.lwjgl.LWJGLUtil;
/*   9:    */import org.lwjgl.MemoryUtil;
/*  10:    */
/*  17:    */public final class GL14
/*  18:    */{
/*  19:    */  public static final int GL_GENERATE_MIPMAP = 33169;
/*  20:    */  public static final int GL_GENERATE_MIPMAP_HINT = 33170;
/*  21:    */  public static final int GL_DEPTH_COMPONENT16 = 33189;
/*  22:    */  public static final int GL_DEPTH_COMPONENT24 = 33190;
/*  23:    */  public static final int GL_DEPTH_COMPONENT32 = 33191;
/*  24:    */  public static final int GL_TEXTURE_DEPTH_SIZE = 34890;
/*  25:    */  public static final int GL_DEPTH_TEXTURE_MODE = 34891;
/*  26:    */  public static final int GL_TEXTURE_COMPARE_MODE = 34892;
/*  27:    */  public static final int GL_TEXTURE_COMPARE_FUNC = 34893;
/*  28:    */  public static final int GL_COMPARE_R_TO_TEXTURE = 34894;
/*  29:    */  public static final int GL_FOG_COORDINATE_SOURCE = 33872;
/*  30:    */  public static final int GL_FOG_COORDINATE = 33873;
/*  31:    */  public static final int GL_FRAGMENT_DEPTH = 33874;
/*  32:    */  public static final int GL_CURRENT_FOG_COORDINATE = 33875;
/*  33:    */  public static final int GL_FOG_COORDINATE_ARRAY_TYPE = 33876;
/*  34:    */  public static final int GL_FOG_COORDINATE_ARRAY_STRIDE = 33877;
/*  35:    */  public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 33878;
/*  36:    */  public static final int GL_FOG_COORDINATE_ARRAY = 33879;
/*  37:    */  public static final int GL_POINT_SIZE_MIN = 33062;
/*  38:    */  public static final int GL_POINT_SIZE_MAX = 33063;
/*  39:    */  public static final int GL_POINT_FADE_THRESHOLD_SIZE = 33064;
/*  40:    */  public static final int GL_POINT_DISTANCE_ATTENUATION = 33065;
/*  41:    */  public static final int GL_COLOR_SUM = 33880;
/*  42:    */  public static final int GL_CURRENT_SECONDARY_COLOR = 33881;
/*  43:    */  public static final int GL_SECONDARY_COLOR_ARRAY_SIZE = 33882;
/*  44:    */  public static final int GL_SECONDARY_COLOR_ARRAY_TYPE = 33883;
/*  45:    */  public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE = 33884;
/*  46:    */  public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 33885;
/*  47:    */  public static final int GL_SECONDARY_COLOR_ARRAY = 33886;
/*  48:    */  public static final int GL_BLEND_DST_RGB = 32968;
/*  49:    */  public static final int GL_BLEND_SRC_RGB = 32969;
/*  50:    */  public static final int GL_BLEND_DST_ALPHA = 32970;
/*  51:    */  public static final int GL_BLEND_SRC_ALPHA = 32971;
/*  52:    */  public static final int GL_INCR_WRAP = 34055;
/*  53:    */  public static final int GL_DECR_WRAP = 34056;
/*  54:    */  public static final int GL_TEXTURE_FILTER_CONTROL = 34048;
/*  55:    */  public static final int GL_TEXTURE_LOD_BIAS = 34049;
/*  56:    */  public static final int GL_MAX_TEXTURE_LOD_BIAS = 34045;
/*  57:    */  public static final int GL_MIRRORED_REPEAT = 33648;
/*  58:    */  public static final int GL_BLEND_COLOR = 32773;
/*  59:    */  public static final int GL_BLEND_EQUATION = 32777;
/*  60:    */  public static final int GL_FUNC_ADD = 32774;
/*  61:    */  public static final int GL_FUNC_SUBTRACT = 32778;
/*  62:    */  public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
/*  63:    */  public static final int GL_MIN = 32775;
/*  64:    */  public static final int GL_MAX = 32776;
/*  65:    */  
/*  66:    */  public static void glBlendEquation(int mode)
/*  67:    */  {
/*  68: 68 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  69: 69 */    long function_pointer = caps.glBlendEquation;
/*  70: 70 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  71: 71 */    nglBlendEquation(mode, function_pointer);
/*  72:    */  }
/*  73:    */  
/*  74:    */  static native void nglBlendEquation(int paramInt, long paramLong);
/*  75:    */  
/*  76: 76 */  public static void glBlendColor(float red, float green, float blue, float alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  77: 77 */    long function_pointer = caps.glBlendColor;
/*  78: 78 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  79: 79 */    nglBlendColor(red, green, blue, alpha, function_pointer);
/*  80:    */  }
/*  81:    */  
/*  82:    */  static native void nglBlendColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  83:    */  
/*  84: 84 */  public static void glFogCoordf(float coord) { ContextCapabilities caps = GLContext.getCapabilities();
/*  85: 85 */    long function_pointer = caps.glFogCoordf;
/*  86: 86 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  87: 87 */    nglFogCoordf(coord, function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglFogCoordf(float paramFloat, long paramLong);
/*  91:    */  
/*  92: 92 */  public static void glFogCoordd(double coord) { ContextCapabilities caps = GLContext.getCapabilities();
/*  93: 93 */    long function_pointer = caps.glFogCoordd;
/*  94: 94 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  95: 95 */    nglFogCoordd(coord, function_pointer);
/*  96:    */  }
/*  97:    */  
/*  98:    */  static native void nglFogCoordd(double paramDouble, long paramLong);
/*  99:    */  
/* 100:100 */  public static void glFogCoordPointer(int stride, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 101:101 */    long function_pointer = caps.glFogCoordPointer;
/* 102:102 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 103:103 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 104:104 */    BufferChecks.checkDirect(data);
/* 105:105 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL14_glFogCoordPointer_data = data;
/* 106:106 */    nglFogCoordPointer(5130, stride, MemoryUtil.getAddress(data), function_pointer);
/* 107:    */  }
/* 108:    */  
/* 109:109 */  public static void glFogCoordPointer(int stride, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 110:110 */    long function_pointer = caps.glFogCoordPointer;
/* 111:111 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 112:112 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 113:113 */    BufferChecks.checkDirect(data);
/* 114:114 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL14_glFogCoordPointer_data = data;
/* 115:115 */    nglFogCoordPointer(5126, stride, MemoryUtil.getAddress(data), function_pointer); }
/* 116:    */  
/* 117:    */  static native void nglFogCoordPointer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 118:    */  
/* 119:119 */  public static void glFogCoordPointer(int type, int stride, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 120:120 */    long function_pointer = caps.glFogCoordPointer;
/* 121:121 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 122:122 */    GLChecks.ensureArrayVBOenabled(caps);
/* 123:123 */    nglFogCoordPointerBO(type, stride, data_buffer_offset, function_pointer);
/* 124:    */  }
/* 125:    */  
/* 126:    */  static native void nglFogCoordPointerBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 127:    */  
/* 128:128 */  public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 129:129 */    long function_pointer = caps.glMultiDrawArrays;
/* 130:130 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 131:131 */    BufferChecks.checkDirect(piFirst);
/* 132:132 */    BufferChecks.checkBuffer(piCount, piFirst.remaining());
/* 133:133 */    nglMultiDrawArrays(mode, MemoryUtil.getAddress(piFirst), MemoryUtil.getAddress(piCount), piFirst.remaining(), function_pointer);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static native void nglMultiDrawArrays(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/* 137:    */  
/* 138:138 */  public static void glPointParameteri(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 139:139 */    long function_pointer = caps.glPointParameteri;
/* 140:140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 141:141 */    nglPointParameteri(pname, param, function_pointer);
/* 142:    */  }
/* 143:    */  
/* 144:    */  static native void nglPointParameteri(int paramInt1, int paramInt2, long paramLong);
/* 145:    */  
/* 146:146 */  public static void glPointParameterf(int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 147:147 */    long function_pointer = caps.glPointParameterf;
/* 148:148 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 149:149 */    nglPointParameterf(pname, param, function_pointer);
/* 150:    */  }
/* 151:    */  
/* 152:    */  static native void nglPointParameterf(int paramInt, float paramFloat, long paramLong);
/* 153:    */  
/* 154:154 */  public static void glPointParameter(int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 155:155 */    long function_pointer = caps.glPointParameteriv;
/* 156:156 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 157:157 */    BufferChecks.checkBuffer(params, 4);
/* 158:158 */    nglPointParameteriv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 159:    */  }
/* 160:    */  
/* 161:    */  static native void nglPointParameteriv(int paramInt, long paramLong1, long paramLong2);
/* 162:    */  
/* 163:163 */  public static void glPointParameter(int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 164:164 */    long function_pointer = caps.glPointParameterfv;
/* 165:165 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 166:166 */    BufferChecks.checkBuffer(params, 4);
/* 167:167 */    nglPointParameterfv(pname, MemoryUtil.getAddress(params), function_pointer);
/* 168:    */  }
/* 169:    */  
/* 170:    */  static native void nglPointParameterfv(int paramInt, long paramLong1, long paramLong2);
/* 171:    */  
/* 172:172 */  public static void glSecondaryColor3b(byte red, byte green, byte blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 173:173 */    long function_pointer = caps.glSecondaryColor3b;
/* 174:174 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 175:175 */    nglSecondaryColor3b(red, green, blue, function_pointer);
/* 176:    */  }
/* 177:    */  
/* 178:    */  static native void nglSecondaryColor3b(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/* 179:    */  
/* 180:180 */  public static void glSecondaryColor3f(float red, float green, float blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 181:181 */    long function_pointer = caps.glSecondaryColor3f;
/* 182:182 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 183:183 */    nglSecondaryColor3f(red, green, blue, function_pointer);
/* 184:    */  }
/* 185:    */  
/* 186:    */  static native void nglSecondaryColor3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 187:    */  
/* 188:188 */  public static void glSecondaryColor3d(double red, double green, double blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 189:189 */    long function_pointer = caps.glSecondaryColor3d;
/* 190:190 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 191:191 */    nglSecondaryColor3d(red, green, blue, function_pointer);
/* 192:    */  }
/* 193:    */  
/* 194:    */  static native void nglSecondaryColor3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 195:    */  
/* 196:196 */  public static void glSecondaryColor3ub(byte red, byte green, byte blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 197:197 */    long function_pointer = caps.glSecondaryColor3ub;
/* 198:198 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 199:199 */    nglSecondaryColor3ub(red, green, blue, function_pointer);
/* 200:    */  }
/* 201:    */  
/* 202:    */  static native void nglSecondaryColor3ub(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/* 203:    */  
/* 204:204 */  public static void glSecondaryColorPointer(int size, int stride, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 205:205 */    long function_pointer = caps.glSecondaryColorPointer;
/* 206:206 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 207:207 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 208:208 */    BufferChecks.checkDirect(data);
/* 209:209 */    nglSecondaryColorPointer(size, 5130, stride, MemoryUtil.getAddress(data), function_pointer);
/* 210:    */  }
/* 211:    */  
/* 212:212 */  public static void glSecondaryColorPointer(int size, int stride, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 213:213 */    long function_pointer = caps.glSecondaryColorPointer;
/* 214:214 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 215:215 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 216:216 */    BufferChecks.checkDirect(data);
/* 217:217 */    nglSecondaryColorPointer(size, 5126, stride, MemoryUtil.getAddress(data), function_pointer);
/* 218:    */  }
/* 219:    */  
/* 220:220 */  public static void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 221:221 */    long function_pointer = caps.glSecondaryColorPointer;
/* 222:222 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 223:223 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 224:224 */    BufferChecks.checkDirect(data);
/* 225:225 */    nglSecondaryColorPointer(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(data), function_pointer); }
/* 226:    */  
/* 227:    */  static native void nglSecondaryColorPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 228:    */  
/* 229:229 */  public static void glSecondaryColorPointer(int size, int type, int stride, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 230:230 */    long function_pointer = caps.glSecondaryColorPointer;
/* 231:231 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 232:232 */    GLChecks.ensureArrayVBOenabled(caps);
/* 233:233 */    nglSecondaryColorPointerBO(size, type, stride, data_buffer_offset, function_pointer);
/* 234:    */  }
/* 235:    */  
/* 236:    */  static native void nglSecondaryColorPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 237:    */  
/* 238:238 */  public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 239:239 */    long function_pointer = caps.glBlendFuncSeparate;
/* 240:240 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 241:241 */    nglBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha, function_pointer);
/* 242:    */  }
/* 243:    */  
/* 244:    */  static native void nglBlendFuncSeparate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 245:    */  
/* 246:246 */  public static void glWindowPos2f(float x, float y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 247:247 */    long function_pointer = caps.glWindowPos2f;
/* 248:248 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 249:249 */    nglWindowPos2f(x, y, function_pointer);
/* 250:    */  }
/* 251:    */  
/* 252:    */  static native void nglWindowPos2f(float paramFloat1, float paramFloat2, long paramLong);
/* 253:    */  
/* 254:254 */  public static void glWindowPos2d(double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 255:255 */    long function_pointer = caps.glWindowPos2d;
/* 256:256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 257:257 */    nglWindowPos2d(x, y, function_pointer);
/* 258:    */  }
/* 259:    */  
/* 260:    */  static native void nglWindowPos2d(double paramDouble1, double paramDouble2, long paramLong);
/* 261:    */  
/* 262:262 */  public static void glWindowPos2i(int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 263:263 */    long function_pointer = caps.glWindowPos2i;
/* 264:264 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 265:265 */    nglWindowPos2i(x, y, function_pointer);
/* 266:    */  }
/* 267:    */  
/* 268:    */  static native void nglWindowPos2i(int paramInt1, int paramInt2, long paramLong);
/* 269:    */  
/* 270:270 */  public static void glWindowPos3f(float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 271:271 */    long function_pointer = caps.glWindowPos3f;
/* 272:272 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 273:273 */    nglWindowPos3f(x, y, z, function_pointer);
/* 274:    */  }
/* 275:    */  
/* 276:    */  static native void nglWindowPos3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 277:    */  
/* 278:278 */  public static void glWindowPos3d(double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 279:279 */    long function_pointer = caps.glWindowPos3d;
/* 280:280 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 281:281 */    nglWindowPos3d(x, y, z, function_pointer);
/* 282:    */  }
/* 283:    */  
/* 284:    */  static native void nglWindowPos3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 285:    */  
/* 286:286 */  public static void glWindowPos3i(int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 287:287 */    long function_pointer = caps.glWindowPos3i;
/* 288:288 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 289:289 */    nglWindowPos3i(x, y, z, function_pointer);
/* 290:    */  }
/* 291:    */  
/* 292:    */  static native void nglWindowPos3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 293:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL14
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */