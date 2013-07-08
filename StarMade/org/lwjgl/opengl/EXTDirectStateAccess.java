/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.ByteOrder;
/*    5:     */import java.nio.DoubleBuffer;
/*    6:     */import java.nio.FloatBuffer;
/*    7:     */import java.nio.IntBuffer;
/*    8:     */import java.nio.ShortBuffer;
/*    9:     */import org.lwjgl.BufferChecks;
/*   10:     */import org.lwjgl.LWJGLUtil;
/*   11:     */import org.lwjgl.MemoryUtil;
/*   12:     */
/*   14:     */public final class EXTDirectStateAccess
/*   15:     */{
/*   16:     */  public static final int GL_PROGRAM_MATRIX_EXT = 36397;
/*   17:     */  public static final int GL_TRANSPOSE_PROGRAM_MATRIX_EXT = 36398;
/*   18:     */  public static final int GL_PROGRAM_MATRIX_STACK_DEPTH_EXT = 36399;
/*   19:     */  
/*   20:     */  public static void glClientAttribDefaultEXT(int mask)
/*   21:     */  {
/*   22:  22 */    ContextCapabilities caps = GLContext.getCapabilities();
/*   23:  23 */    long function_pointer = caps.glClientAttribDefaultEXT;
/*   24:  24 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   25:  25 */    nglClientAttribDefaultEXT(mask, function_pointer);
/*   26:     */  }
/*   27:     */  
/*   28:     */  static native void nglClientAttribDefaultEXT(int paramInt, long paramLong);
/*   29:     */  
/*   30:  30 */  public static void glPushClientAttribDefaultEXT(int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/*   31:  31 */    long function_pointer = caps.glPushClientAttribDefaultEXT;
/*   32:  32 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   33:  33 */    nglPushClientAttribDefaultEXT(mask, function_pointer);
/*   34:     */  }
/*   35:     */  
/*   36:     */  static native void nglPushClientAttribDefaultEXT(int paramInt, long paramLong);
/*   37:     */  
/*   38:  38 */  public static void glMatrixLoadEXT(int matrixMode, FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/*   39:  39 */    long function_pointer = caps.glMatrixLoadfEXT;
/*   40:  40 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   41:  41 */    BufferChecks.checkBuffer(m, 16);
/*   42:  42 */    nglMatrixLoadfEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*   43:     */  }
/*   44:     */  
/*   45:     */  static native void nglMatrixLoadfEXT(int paramInt, long paramLong1, long paramLong2);
/*   46:     */  
/*   47:  47 */  public static void glMatrixLoadEXT(int matrixMode, DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/*   48:  48 */    long function_pointer = caps.glMatrixLoaddEXT;
/*   49:  49 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   50:  50 */    BufferChecks.checkBuffer(m, 16);
/*   51:  51 */    nglMatrixLoaddEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*   52:     */  }
/*   53:     */  
/*   54:     */  static native void nglMatrixLoaddEXT(int paramInt, long paramLong1, long paramLong2);
/*   55:     */  
/*   56:  56 */  public static void glMatrixMultEXT(int matrixMode, FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/*   57:  57 */    long function_pointer = caps.glMatrixMultfEXT;
/*   58:  58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   59:  59 */    BufferChecks.checkBuffer(m, 16);
/*   60:  60 */    nglMatrixMultfEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*   61:     */  }
/*   62:     */  
/*   63:     */  static native void nglMatrixMultfEXT(int paramInt, long paramLong1, long paramLong2);
/*   64:     */  
/*   65:  65 */  public static void glMatrixMultEXT(int matrixMode, DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/*   66:  66 */    long function_pointer = caps.glMatrixMultdEXT;
/*   67:  67 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   68:  68 */    BufferChecks.checkBuffer(m, 16);
/*   69:  69 */    nglMatrixMultdEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*   70:     */  }
/*   71:     */  
/*   72:     */  static native void nglMatrixMultdEXT(int paramInt, long paramLong1, long paramLong2);
/*   73:     */  
/*   74:  74 */  public static void glMatrixLoadIdentityEXT(int matrixMode) { ContextCapabilities caps = GLContext.getCapabilities();
/*   75:  75 */    long function_pointer = caps.glMatrixLoadIdentityEXT;
/*   76:  76 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   77:  77 */    nglMatrixLoadIdentityEXT(matrixMode, function_pointer);
/*   78:     */  }
/*   79:     */  
/*   80:     */  static native void nglMatrixLoadIdentityEXT(int paramInt, long paramLong);
/*   81:     */  
/*   82:  82 */  public static void glMatrixRotatefEXT(int matrixMode, float angle, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/*   83:  83 */    long function_pointer = caps.glMatrixRotatefEXT;
/*   84:  84 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   85:  85 */    nglMatrixRotatefEXT(matrixMode, angle, x, y, z, function_pointer);
/*   86:     */  }
/*   87:     */  
/*   88:     */  static native void nglMatrixRotatefEXT(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*   89:     */  
/*   90:  90 */  public static void glMatrixRotatedEXT(int matrixMode, double angle, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*   91:  91 */    long function_pointer = caps.glMatrixRotatedEXT;
/*   92:  92 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   93:  93 */    nglMatrixRotatedEXT(matrixMode, angle, x, y, z, function_pointer);
/*   94:     */  }
/*   95:     */  
/*   96:     */  static native void nglMatrixRotatedEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*   97:     */  
/*   98:  98 */  public static void glMatrixScalefEXT(int matrixMode, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/*   99:  99 */    long function_pointer = caps.glMatrixScalefEXT;
/*  100: 100 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  101: 101 */    nglMatrixScalefEXT(matrixMode, x, y, z, function_pointer);
/*  102:     */  }
/*  103:     */  
/*  104:     */  static native void nglMatrixScalefEXT(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  105:     */  
/*  106: 106 */  public static void glMatrixScaledEXT(int matrixMode, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  107: 107 */    long function_pointer = caps.glMatrixScaledEXT;
/*  108: 108 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  109: 109 */    nglMatrixScaledEXT(matrixMode, x, y, z, function_pointer);
/*  110:     */  }
/*  111:     */  
/*  112:     */  static native void nglMatrixScaledEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  113:     */  
/*  114: 114 */  public static void glMatrixTranslatefEXT(int matrixMode, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  115: 115 */    long function_pointer = caps.glMatrixTranslatefEXT;
/*  116: 116 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  117: 117 */    nglMatrixTranslatefEXT(matrixMode, x, y, z, function_pointer);
/*  118:     */  }
/*  119:     */  
/*  120:     */  static native void nglMatrixTranslatefEXT(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  121:     */  
/*  122: 122 */  public static void glMatrixTranslatedEXT(int matrixMode, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  123: 123 */    long function_pointer = caps.glMatrixTranslatedEXT;
/*  124: 124 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  125: 125 */    nglMatrixTranslatedEXT(matrixMode, x, y, z, function_pointer);
/*  126:     */  }
/*  127:     */  
/*  128:     */  static native void nglMatrixTranslatedEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  129:     */  
/*  130: 130 */  public static void glMatrixOrthoEXT(int matrixMode, double l, double r, double b, double t, double n, double f) { ContextCapabilities caps = GLContext.getCapabilities();
/*  131: 131 */    long function_pointer = caps.glMatrixOrthoEXT;
/*  132: 132 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  133: 133 */    nglMatrixOrthoEXT(matrixMode, l, r, b, t, n, f, function_pointer);
/*  134:     */  }
/*  135:     */  
/*  136:     */  static native void nglMatrixOrthoEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/*  137:     */  
/*  138: 138 */  public static void glMatrixFrustumEXT(int matrixMode, double l, double r, double b, double t, double n, double f) { ContextCapabilities caps = GLContext.getCapabilities();
/*  139: 139 */    long function_pointer = caps.glMatrixFrustumEXT;
/*  140: 140 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  141: 141 */    nglMatrixFrustumEXT(matrixMode, l, r, b, t, n, f, function_pointer);
/*  142:     */  }
/*  143:     */  
/*  144:     */  static native void nglMatrixFrustumEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/*  145:     */  
/*  146: 146 */  public static void glMatrixPushEXT(int matrixMode) { ContextCapabilities caps = GLContext.getCapabilities();
/*  147: 147 */    long function_pointer = caps.glMatrixPushEXT;
/*  148: 148 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  149: 149 */    nglMatrixPushEXT(matrixMode, function_pointer);
/*  150:     */  }
/*  151:     */  
/*  152:     */  static native void nglMatrixPushEXT(int paramInt, long paramLong);
/*  153:     */  
/*  154: 154 */  public static void glMatrixPopEXT(int matrixMode) { ContextCapabilities caps = GLContext.getCapabilities();
/*  155: 155 */    long function_pointer = caps.glMatrixPopEXT;
/*  156: 156 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  157: 157 */    nglMatrixPopEXT(matrixMode, function_pointer);
/*  158:     */  }
/*  159:     */  
/*  160:     */  static native void nglMatrixPopEXT(int paramInt, long paramLong);
/*  161:     */  
/*  162: 162 */  public static void glTextureParameteriEXT(int texture, int target, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  163: 163 */    long function_pointer = caps.glTextureParameteriEXT;
/*  164: 164 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  165: 165 */    nglTextureParameteriEXT(texture, target, pname, param, function_pointer);
/*  166:     */  }
/*  167:     */  
/*  168:     */  static native void nglTextureParameteriEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  169:     */  
/*  170: 170 */  public static void glTextureParameterEXT(int texture, int target, int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  171: 171 */    long function_pointer = caps.glTextureParameterivEXT;
/*  172: 172 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  173: 173 */    BufferChecks.checkBuffer(param, 4);
/*  174: 174 */    nglTextureParameterivEXT(texture, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*  175:     */  }
/*  176:     */  
/*  177:     */  static native void nglTextureParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  178:     */  
/*  179: 179 */  public static void glTextureParameterfEXT(int texture, int target, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  180: 180 */    long function_pointer = caps.glTextureParameterfEXT;
/*  181: 181 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  182: 182 */    nglTextureParameterfEXT(texture, target, pname, param, function_pointer);
/*  183:     */  }
/*  184:     */  
/*  185:     */  static native void nglTextureParameterfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*  186:     */  
/*  187: 187 */  public static void glTextureParameterEXT(int texture, int target, int pname, FloatBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  188: 188 */    long function_pointer = caps.glTextureParameterfvEXT;
/*  189: 189 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  190: 190 */    BufferChecks.checkBuffer(param, 4);
/*  191: 191 */    nglTextureParameterfvEXT(texture, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*  192:     */  }
/*  193:     */  
/*  194:     */  static native void nglTextureParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  195:     */  
/*  196: 196 */  public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  197: 197 */    long function_pointer = caps.glTextureImage1DEXT;
/*  198: 198 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  199: 199 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  200: 200 */    if (pixels != null)
/*  201: 201 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  202: 202 */    nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  203:     */  }
/*  204:     */  
/*  205: 205 */  public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  206: 206 */    long function_pointer = caps.glTextureImage1DEXT;
/*  207: 207 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  208: 208 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  209: 209 */    if (pixels != null)
/*  210: 210 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  211: 211 */    nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  212:     */  }
/*  213:     */  
/*  214: 214 */  public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  215: 215 */    long function_pointer = caps.glTextureImage1DEXT;
/*  216: 216 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  217: 217 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  218: 218 */    if (pixels != null)
/*  219: 219 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  220: 220 */    nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  221:     */  }
/*  222:     */  
/*  223: 223 */  public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  224: 224 */    long function_pointer = caps.glTextureImage1DEXT;
/*  225: 225 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  226: 226 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  227: 227 */    if (pixels != null)
/*  228: 228 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  229: 229 */    nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  230:     */  }
/*  231:     */  
/*  232: 232 */  public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  233: 233 */    long function_pointer = caps.glTextureImage1DEXT;
/*  234: 234 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  235: 235 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  236: 236 */    if (pixels != null)
/*  237: 237 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  238: 238 */    nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/*  239:     */  
/*  240:     */  static native void nglTextureImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*  241:     */  
/*  242: 242 */  public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  243: 243 */    long function_pointer = caps.glTextureImage1DEXT;
/*  244: 244 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  245: 245 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  246: 246 */    nglTextureImage1DEXTBO(texture, target, level, internalformat, width, border, format, type, pixels_buffer_offset, function_pointer);
/*  247:     */  }
/*  248:     */  
/*  249:     */  static native void nglTextureImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*  250:     */  
/*  251: 251 */  public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  252: 252 */    long function_pointer = caps.glTextureImage2DEXT;
/*  253: 253 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  254: 254 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  255: 255 */    if (pixels != null)
/*  256: 256 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  257: 257 */    nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  258:     */  }
/*  259:     */  
/*  260: 260 */  public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  261: 261 */    long function_pointer = caps.glTextureImage2DEXT;
/*  262: 262 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  263: 263 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  264: 264 */    if (pixels != null)
/*  265: 265 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  266: 266 */    nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  267:     */  }
/*  268:     */  
/*  269: 269 */  public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  270: 270 */    long function_pointer = caps.glTextureImage2DEXT;
/*  271: 271 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  272: 272 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  273: 273 */    if (pixels != null)
/*  274: 274 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  275: 275 */    nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  276:     */  }
/*  277:     */  
/*  278: 278 */  public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  279: 279 */    long function_pointer = caps.glTextureImage2DEXT;
/*  280: 280 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  281: 281 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  282: 282 */    if (pixels != null)
/*  283: 283 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  284: 284 */    nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  285:     */  }
/*  286:     */  
/*  287: 287 */  public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  288: 288 */    long function_pointer = caps.glTextureImage2DEXT;
/*  289: 289 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  290: 290 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  291: 291 */    if (pixels != null)
/*  292: 292 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  293: 293 */    nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/*  294:     */  
/*  295:     */  static native void nglTextureImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*  296:     */  
/*  297: 297 */  public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  298: 298 */    long function_pointer = caps.glTextureImage2DEXT;
/*  299: 299 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  300: 300 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  301: 301 */    nglTextureImage2DEXTBO(texture, target, level, internalformat, width, height, border, format, type, pixels_buffer_offset, function_pointer);
/*  302:     */  }
/*  303:     */  
/*  304:     */  static native void nglTextureImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*  305:     */  
/*  306: 306 */  public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  307: 307 */    long function_pointer = caps.glTextureSubImage1DEXT;
/*  308: 308 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  309: 309 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  310: 310 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  311: 311 */    nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  312:     */  }
/*  313:     */  
/*  314: 314 */  public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  315: 315 */    long function_pointer = caps.glTextureSubImage1DEXT;
/*  316: 316 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  317: 317 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  318: 318 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  319: 319 */    nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  320:     */  }
/*  321:     */  
/*  322: 322 */  public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  323: 323 */    long function_pointer = caps.glTextureSubImage1DEXT;
/*  324: 324 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  325: 325 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  326: 326 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  327: 327 */    nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  328:     */  }
/*  329:     */  
/*  330: 330 */  public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  331: 331 */    long function_pointer = caps.glTextureSubImage1DEXT;
/*  332: 332 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  333: 333 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  334: 334 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  335: 335 */    nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  336:     */  }
/*  337:     */  
/*  338: 338 */  public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  339: 339 */    long function_pointer = caps.glTextureSubImage1DEXT;
/*  340: 340 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  341: 341 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  342: 342 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  343: 343 */    nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/*  344:     */  
/*  345:     */  static native void nglTextureSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*  346:     */  
/*  347: 347 */  public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  348: 348 */    long function_pointer = caps.glTextureSubImage1DEXT;
/*  349: 349 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  350: 350 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  351: 351 */    nglTextureSubImage1DEXTBO(texture, target, level, xoffset, width, format, type, pixels_buffer_offset, function_pointer);
/*  352:     */  }
/*  353:     */  
/*  354:     */  static native void nglTextureSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*  355:     */  
/*  356: 356 */  public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  357: 357 */    long function_pointer = caps.glTextureSubImage2DEXT;
/*  358: 358 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  359: 359 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  360: 360 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  361: 361 */    nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  362:     */  }
/*  363:     */  
/*  364: 364 */  public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  365: 365 */    long function_pointer = caps.glTextureSubImage2DEXT;
/*  366: 366 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  367: 367 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  368: 368 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  369: 369 */    nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  370:     */  }
/*  371:     */  
/*  372: 372 */  public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  373: 373 */    long function_pointer = caps.glTextureSubImage2DEXT;
/*  374: 374 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  375: 375 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  376: 376 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  377: 377 */    nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  378:     */  }
/*  379:     */  
/*  380: 380 */  public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  381: 381 */    long function_pointer = caps.glTextureSubImage2DEXT;
/*  382: 382 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  383: 383 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  384: 384 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  385: 385 */    nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  386:     */  }
/*  387:     */  
/*  388: 388 */  public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  389: 389 */    long function_pointer = caps.glTextureSubImage2DEXT;
/*  390: 390 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  391: 391 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  392: 392 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  393: 393 */    nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/*  394:     */  
/*  395:     */  static native void nglTextureSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*  396:     */  
/*  397: 397 */  public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  398: 398 */    long function_pointer = caps.glTextureSubImage2DEXT;
/*  399: 399 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  400: 400 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  401: 401 */    nglTextureSubImage2DEXTBO(texture, target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset, function_pointer);
/*  402:     */  }
/*  403:     */  
/*  404:     */  static native void nglTextureSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*  405:     */  
/*  406: 406 */  public static void glCopyTextureImage1DEXT(int texture, int target, int level, int internalformat, int x, int y, int width, int border) { ContextCapabilities caps = GLContext.getCapabilities();
/*  407: 407 */    long function_pointer = caps.glCopyTextureImage1DEXT;
/*  408: 408 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  409: 409 */    nglCopyTextureImage1DEXT(texture, target, level, internalformat, x, y, width, border, function_pointer);
/*  410:     */  }
/*  411:     */  
/*  412:     */  static native void nglCopyTextureImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*  413:     */  
/*  414: 414 */  public static void glCopyTextureImage2DEXT(int texture, int target, int level, int internalformat, int x, int y, int width, int height, int border) { ContextCapabilities caps = GLContext.getCapabilities();
/*  415: 415 */    long function_pointer = caps.glCopyTextureImage2DEXT;
/*  416: 416 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  417: 417 */    nglCopyTextureImage2DEXT(texture, target, level, internalformat, x, y, width, height, border, function_pointer);
/*  418:     */  }
/*  419:     */  
/*  420:     */  static native void nglCopyTextureImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*  421:     */  
/*  422: 422 */  public static void glCopyTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int x, int y, int width) { ContextCapabilities caps = GLContext.getCapabilities();
/*  423: 423 */    long function_pointer = caps.glCopyTextureSubImage1DEXT;
/*  424: 424 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  425: 425 */    nglCopyTextureSubImage1DEXT(texture, target, level, xoffset, x, y, width, function_pointer);
/*  426:     */  }
/*  427:     */  
/*  428:     */  static native void nglCopyTextureSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*  429:     */  
/*  430: 430 */  public static void glCopyTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/*  431: 431 */    long function_pointer = caps.glCopyTextureSubImage2DEXT;
/*  432: 432 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  433: 433 */    nglCopyTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, x, y, width, height, function_pointer);
/*  434:     */  }
/*  435:     */  
/*  436:     */  static native void nglCopyTextureSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*  437:     */  
/*  438: 438 */  public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  439: 439 */    long function_pointer = caps.glGetTextureImageEXT;
/*  440: 440 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  441: 441 */    GLChecks.ensurePackPBOdisabled(caps);
/*  442: 442 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  443: 443 */    nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  444:     */  }
/*  445:     */  
/*  446: 446 */  public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  447: 447 */    long function_pointer = caps.glGetTextureImageEXT;
/*  448: 448 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  449: 449 */    GLChecks.ensurePackPBOdisabled(caps);
/*  450: 450 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  451: 451 */    nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  452:     */  }
/*  453:     */  
/*  454: 454 */  public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  455: 455 */    long function_pointer = caps.glGetTextureImageEXT;
/*  456: 456 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  457: 457 */    GLChecks.ensurePackPBOdisabled(caps);
/*  458: 458 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  459: 459 */    nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  460:     */  }
/*  461:     */  
/*  462: 462 */  public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  463: 463 */    long function_pointer = caps.glGetTextureImageEXT;
/*  464: 464 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  465: 465 */    GLChecks.ensurePackPBOdisabled(caps);
/*  466: 466 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  467: 467 */    nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  468:     */  }
/*  469:     */  
/*  470: 470 */  public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  471: 471 */    long function_pointer = caps.glGetTextureImageEXT;
/*  472: 472 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  473: 473 */    GLChecks.ensurePackPBOdisabled(caps);
/*  474: 474 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  475: 475 */    nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/*  476:     */  
/*  477:     */  static native void nglGetTextureImageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  478:     */  
/*  479: 479 */  public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  480: 480 */    long function_pointer = caps.glGetTextureImageEXT;
/*  481: 481 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  482: 482 */    GLChecks.ensurePackPBOenabled(caps);
/*  483: 483 */    nglGetTextureImageEXTBO(texture, target, level, format, type, pixels_buffer_offset, function_pointer);
/*  484:     */  }
/*  485:     */  
/*  486:     */  static native void nglGetTextureImageEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  487:     */  
/*  488: 488 */  public static void glGetTextureParameterEXT(int texture, int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  489: 489 */    long function_pointer = caps.glGetTextureParameterfvEXT;
/*  490: 490 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  491: 491 */    BufferChecks.checkBuffer(params, 4);
/*  492: 492 */    nglGetTextureParameterfvEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  493:     */  }
/*  494:     */  
/*  495:     */  static native void nglGetTextureParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  496:     */  
/*  497:     */  public static float glGetTextureParameterfEXT(int texture, int target, int pname) {
/*  498: 498 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  499: 499 */    long function_pointer = caps.glGetTextureParameterfvEXT;
/*  500: 500 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  501: 501 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/*  502: 502 */    nglGetTextureParameterfvEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  503: 503 */    return params.get(0);
/*  504:     */  }
/*  505:     */  
/*  506:     */  public static void glGetTextureParameterEXT(int texture, int target, int pname, IntBuffer params) {
/*  507: 507 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  508: 508 */    long function_pointer = caps.glGetTextureParameterivEXT;
/*  509: 509 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  510: 510 */    BufferChecks.checkBuffer(params, 4);
/*  511: 511 */    nglGetTextureParameterivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  512:     */  }
/*  513:     */  
/*  514:     */  static native void nglGetTextureParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  515:     */  
/*  516:     */  public static int glGetTextureParameteriEXT(int texture, int target, int pname) {
/*  517: 517 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  518: 518 */    long function_pointer = caps.glGetTextureParameterivEXT;
/*  519: 519 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  520: 520 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  521: 521 */    nglGetTextureParameterivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  522: 522 */    return params.get(0);
/*  523:     */  }
/*  524:     */  
/*  525:     */  public static void glGetTextureLevelParameterEXT(int texture, int target, int level, int pname, FloatBuffer params) {
/*  526: 526 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  527: 527 */    long function_pointer = caps.glGetTextureLevelParameterfvEXT;
/*  528: 528 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  529: 529 */    BufferChecks.checkBuffer(params, 4);
/*  530: 530 */    nglGetTextureLevelParameterfvEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*  531:     */  }
/*  532:     */  
/*  533:     */  static native void nglGetTextureLevelParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  534:     */  
/*  535:     */  public static float glGetTextureLevelParameterfEXT(int texture, int target, int level, int pname) {
/*  536: 536 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  537: 537 */    long function_pointer = caps.glGetTextureLevelParameterfvEXT;
/*  538: 538 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  539: 539 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/*  540: 540 */    nglGetTextureLevelParameterfvEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*  541: 541 */    return params.get(0);
/*  542:     */  }
/*  543:     */  
/*  544:     */  public static void glGetTextureLevelParameterEXT(int texture, int target, int level, int pname, IntBuffer params) {
/*  545: 545 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  546: 546 */    long function_pointer = caps.glGetTextureLevelParameterivEXT;
/*  547: 547 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  548: 548 */    BufferChecks.checkBuffer(params, 4);
/*  549: 549 */    nglGetTextureLevelParameterivEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*  550:     */  }
/*  551:     */  
/*  552:     */  static native void nglGetTextureLevelParameterivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  553:     */  
/*  554:     */  public static int glGetTextureLevelParameteriEXT(int texture, int target, int level, int pname) {
/*  555: 555 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  556: 556 */    long function_pointer = caps.glGetTextureLevelParameterivEXT;
/*  557: 557 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  558: 558 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  559: 559 */    nglGetTextureLevelParameterivEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*  560: 560 */    return params.get(0);
/*  561:     */  }
/*  562:     */  
/*  563:     */  public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
/*  564: 564 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  565: 565 */    long function_pointer = caps.glTextureImage3DEXT;
/*  566: 566 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  567: 567 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  568: 568 */    if (pixels != null)
/*  569: 569 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  570: 570 */    nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  571:     */  }
/*  572:     */  
/*  573: 573 */  public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  574: 574 */    long function_pointer = caps.glTextureImage3DEXT;
/*  575: 575 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  576: 576 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  577: 577 */    if (pixels != null)
/*  578: 578 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  579: 579 */    nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  580:     */  }
/*  581:     */  
/*  582: 582 */  public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  583: 583 */    long function_pointer = caps.glTextureImage3DEXT;
/*  584: 584 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  585: 585 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  586: 586 */    if (pixels != null)
/*  587: 587 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  588: 588 */    nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  589:     */  }
/*  590:     */  
/*  591: 591 */  public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  592: 592 */    long function_pointer = caps.glTextureImage3DEXT;
/*  593: 593 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  594: 594 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  595: 595 */    if (pixels != null)
/*  596: 596 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  597: 597 */    nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  598:     */  }
/*  599:     */  
/*  600: 600 */  public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  601: 601 */    long function_pointer = caps.glTextureImage3DEXT;
/*  602: 602 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  603: 603 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  604: 604 */    if (pixels != null)
/*  605: 605 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  606: 606 */    nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/*  607:     */  
/*  608:     */  static native void nglTextureImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*  609:     */  
/*  610: 610 */  public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  611: 611 */    long function_pointer = caps.glTextureImage3DEXT;
/*  612: 612 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  613: 613 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  614: 614 */    nglTextureImage3DEXTBO(texture, target, level, internalformat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer);
/*  615:     */  }
/*  616:     */  
/*  617:     */  static native void nglTextureImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*  618:     */  
/*  619: 619 */  public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  620: 620 */    long function_pointer = caps.glTextureSubImage3DEXT;
/*  621: 621 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  622: 622 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  623: 623 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  624: 624 */    nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  625:     */  }
/*  626:     */  
/*  627: 627 */  public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  628: 628 */    long function_pointer = caps.glTextureSubImage3DEXT;
/*  629: 629 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  630: 630 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  631: 631 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  632: 632 */    nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  633:     */  }
/*  634:     */  
/*  635: 635 */  public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  636: 636 */    long function_pointer = caps.glTextureSubImage3DEXT;
/*  637: 637 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  638: 638 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  639: 639 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  640: 640 */    nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  641:     */  }
/*  642:     */  
/*  643: 643 */  public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  644: 644 */    long function_pointer = caps.glTextureSubImage3DEXT;
/*  645: 645 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  646: 646 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  647: 647 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  648: 648 */    nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  649:     */  }
/*  650:     */  
/*  651: 651 */  public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  652: 652 */    long function_pointer = caps.glTextureSubImage3DEXT;
/*  653: 653 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  654: 654 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  655: 655 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  656: 656 */    nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/*  657:     */  
/*  658:     */  static native void nglTextureSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*  659:     */  
/*  660: 660 */  public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  661: 661 */    long function_pointer = caps.glTextureSubImage3DEXT;
/*  662: 662 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  663: 663 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  664: 664 */    nglTextureSubImage3DEXTBO(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer);
/*  665:     */  }
/*  666:     */  
/*  667:     */  static native void nglTextureSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*  668:     */  
/*  669: 669 */  public static void glCopyTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/*  670: 670 */    long function_pointer = caps.glCopyTextureSubImage3DEXT;
/*  671: 671 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  672: 672 */    nglCopyTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
/*  673:     */  }
/*  674:     */  
/*  675:     */  static native void nglCopyTextureSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/*  676:     */  
/*  677: 677 */  public static void glBindMultiTextureEXT(int texunit, int target, int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/*  678: 678 */    long function_pointer = caps.glBindMultiTextureEXT;
/*  679: 679 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  680: 680 */    nglBindMultiTextureEXT(texunit, target, texture, function_pointer);
/*  681:     */  }
/*  682:     */  
/*  683:     */  static native void nglBindMultiTextureEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  684:     */  
/*  685: 685 */  public static void glMultiTexCoordPointerEXT(int texunit, int size, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  686: 686 */    long function_pointer = caps.glMultiTexCoordPointerEXT;
/*  687: 687 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  688: 688 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  689: 689 */    BufferChecks.checkDirect(pointer);
/*  690: 690 */    nglMultiTexCoordPointerEXT(texunit, size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*  691:     */  }
/*  692:     */  
/*  693: 693 */  public static void glMultiTexCoordPointerEXT(int texunit, int size, int stride, FloatBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  694: 694 */    long function_pointer = caps.glMultiTexCoordPointerEXT;
/*  695: 695 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  696: 696 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  697: 697 */    BufferChecks.checkDirect(pointer);
/*  698: 698 */    nglMultiTexCoordPointerEXT(texunit, size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/*  699:     */  
/*  700:     */  static native void nglMultiTexCoordPointerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  701:     */  
/*  702: 702 */  public static void glMultiTexCoordPointerEXT(int texunit, int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  703: 703 */    long function_pointer = caps.glMultiTexCoordPointerEXT;
/*  704: 704 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  705: 705 */    GLChecks.ensureArrayVBOenabled(caps);
/*  706: 706 */    nglMultiTexCoordPointerEXTBO(texunit, size, type, stride, pointer_buffer_offset, function_pointer);
/*  707:     */  }
/*  708:     */  
/*  709:     */  static native void nglMultiTexCoordPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  710:     */  
/*  711: 711 */  public static void glMultiTexEnvfEXT(int texunit, int target, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  712: 712 */    long function_pointer = caps.glMultiTexEnvfEXT;
/*  713: 713 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  714: 714 */    nglMultiTexEnvfEXT(texunit, target, pname, param, function_pointer);
/*  715:     */  }
/*  716:     */  
/*  717:     */  static native void nglMultiTexEnvfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*  718:     */  
/*  719: 719 */  public static void glMultiTexEnvEXT(int texunit, int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  720: 720 */    long function_pointer = caps.glMultiTexEnvfvEXT;
/*  721: 721 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  722: 722 */    BufferChecks.checkBuffer(params, 4);
/*  723: 723 */    nglMultiTexEnvfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  724:     */  }
/*  725:     */  
/*  726:     */  static native void nglMultiTexEnvfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  727:     */  
/*  728: 728 */  public static void glMultiTexEnviEXT(int texunit, int target, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  729: 729 */    long function_pointer = caps.glMultiTexEnviEXT;
/*  730: 730 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  731: 731 */    nglMultiTexEnviEXT(texunit, target, pname, param, function_pointer);
/*  732:     */  }
/*  733:     */  
/*  734:     */  static native void nglMultiTexEnviEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  735:     */  
/*  736: 736 */  public static void glMultiTexEnvEXT(int texunit, int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  737: 737 */    long function_pointer = caps.glMultiTexEnvivEXT;
/*  738: 738 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  739: 739 */    BufferChecks.checkBuffer(params, 4);
/*  740: 740 */    nglMultiTexEnvivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  741:     */  }
/*  742:     */  
/*  743:     */  static native void nglMultiTexEnvivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  744:     */  
/*  745: 745 */  public static void glMultiTexGendEXT(int texunit, int coord, int pname, double param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  746: 746 */    long function_pointer = caps.glMultiTexGendEXT;
/*  747: 747 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  748: 748 */    nglMultiTexGendEXT(texunit, coord, pname, param, function_pointer);
/*  749:     */  }
/*  750:     */  
/*  751:     */  static native void nglMultiTexGendEXT(int paramInt1, int paramInt2, int paramInt3, double paramDouble, long paramLong);
/*  752:     */  
/*  753: 753 */  public static void glMultiTexGenEXT(int texunit, int coord, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  754: 754 */    long function_pointer = caps.glMultiTexGendvEXT;
/*  755: 755 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  756: 756 */    BufferChecks.checkBuffer(params, 4);
/*  757: 757 */    nglMultiTexGendvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*  758:     */  }
/*  759:     */  
/*  760:     */  static native void nglMultiTexGendvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  761:     */  
/*  762: 762 */  public static void glMultiTexGenfEXT(int texunit, int coord, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  763: 763 */    long function_pointer = caps.glMultiTexGenfEXT;
/*  764: 764 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  765: 765 */    nglMultiTexGenfEXT(texunit, coord, pname, param, function_pointer);
/*  766:     */  }
/*  767:     */  
/*  768:     */  static native void nglMultiTexGenfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*  769:     */  
/*  770: 770 */  public static void glMultiTexGenEXT(int texunit, int coord, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  771: 771 */    long function_pointer = caps.glMultiTexGenfvEXT;
/*  772: 772 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  773: 773 */    BufferChecks.checkBuffer(params, 4);
/*  774: 774 */    nglMultiTexGenfvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*  775:     */  }
/*  776:     */  
/*  777:     */  static native void nglMultiTexGenfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  778:     */  
/*  779: 779 */  public static void glMultiTexGeniEXT(int texunit, int coord, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  780: 780 */    long function_pointer = caps.glMultiTexGeniEXT;
/*  781: 781 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  782: 782 */    nglMultiTexGeniEXT(texunit, coord, pname, param, function_pointer);
/*  783:     */  }
/*  784:     */  
/*  785:     */  static native void nglMultiTexGeniEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  786:     */  
/*  787: 787 */  public static void glMultiTexGenEXT(int texunit, int coord, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  788: 788 */    long function_pointer = caps.glMultiTexGenivEXT;
/*  789: 789 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  790: 790 */    BufferChecks.checkBuffer(params, 4);
/*  791: 791 */    nglMultiTexGenivEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*  792:     */  }
/*  793:     */  
/*  794:     */  static native void nglMultiTexGenivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  795:     */  
/*  796: 796 */  public static void glGetMultiTexEnvEXT(int texunit, int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  797: 797 */    long function_pointer = caps.glGetMultiTexEnvfvEXT;
/*  798: 798 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  799: 799 */    BufferChecks.checkBuffer(params, 4);
/*  800: 800 */    nglGetMultiTexEnvfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  801:     */  }
/*  802:     */  
/*  803:     */  static native void nglGetMultiTexEnvfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  804:     */  
/*  805: 805 */  public static void glGetMultiTexEnvEXT(int texunit, int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  806: 806 */    long function_pointer = caps.glGetMultiTexEnvivEXT;
/*  807: 807 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  808: 808 */    BufferChecks.checkBuffer(params, 4);
/*  809: 809 */    nglGetMultiTexEnvivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  810:     */  }
/*  811:     */  
/*  812:     */  static native void nglGetMultiTexEnvivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  813:     */  
/*  814: 814 */  public static void glGetMultiTexGenEXT(int texunit, int coord, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  815: 815 */    long function_pointer = caps.glGetMultiTexGendvEXT;
/*  816: 816 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  817: 817 */    BufferChecks.checkBuffer(params, 4);
/*  818: 818 */    nglGetMultiTexGendvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*  819:     */  }
/*  820:     */  
/*  821:     */  static native void nglGetMultiTexGendvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  822:     */  
/*  823: 823 */  public static void glGetMultiTexGenEXT(int texunit, int coord, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  824: 824 */    long function_pointer = caps.glGetMultiTexGenfvEXT;
/*  825: 825 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  826: 826 */    BufferChecks.checkBuffer(params, 4);
/*  827: 827 */    nglGetMultiTexGenfvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*  828:     */  }
/*  829:     */  
/*  830:     */  static native void nglGetMultiTexGenfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  831:     */  
/*  832: 832 */  public static void glGetMultiTexGenEXT(int texunit, int coord, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  833: 833 */    long function_pointer = caps.glGetMultiTexGenivEXT;
/*  834: 834 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  835: 835 */    BufferChecks.checkBuffer(params, 4);
/*  836: 836 */    nglGetMultiTexGenivEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*  837:     */  }
/*  838:     */  
/*  839:     */  static native void nglGetMultiTexGenivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  840:     */  
/*  841: 841 */  public static void glMultiTexParameteriEXT(int texunit, int target, int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  842: 842 */    long function_pointer = caps.glMultiTexParameteriEXT;
/*  843: 843 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  844: 844 */    nglMultiTexParameteriEXT(texunit, target, pname, param, function_pointer);
/*  845:     */  }
/*  846:     */  
/*  847:     */  static native void nglMultiTexParameteriEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  848:     */  
/*  849: 849 */  public static void glMultiTexParameterEXT(int texunit, int target, int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  850: 850 */    long function_pointer = caps.glMultiTexParameterivEXT;
/*  851: 851 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  852: 852 */    BufferChecks.checkBuffer(param, 4);
/*  853: 853 */    nglMultiTexParameterivEXT(texunit, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*  854:     */  }
/*  855:     */  
/*  856:     */  static native void nglMultiTexParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  857:     */  
/*  858: 858 */  public static void glMultiTexParameterfEXT(int texunit, int target, int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  859: 859 */    long function_pointer = caps.glMultiTexParameterfEXT;
/*  860: 860 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  861: 861 */    nglMultiTexParameterfEXT(texunit, target, pname, param, function_pointer);
/*  862:     */  }
/*  863:     */  
/*  864:     */  static native void nglMultiTexParameterfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*  865:     */  
/*  866: 866 */  public static void glMultiTexParameterEXT(int texunit, int target, int pname, FloatBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  867: 867 */    long function_pointer = caps.glMultiTexParameterfvEXT;
/*  868: 868 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  869: 869 */    BufferChecks.checkBuffer(param, 4);
/*  870: 870 */    nglMultiTexParameterfvEXT(texunit, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*  871:     */  }
/*  872:     */  
/*  873:     */  static native void nglMultiTexParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  874:     */  
/*  875: 875 */  public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  876: 876 */    long function_pointer = caps.glMultiTexImage1DEXT;
/*  877: 877 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  878: 878 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  879: 879 */    if (pixels != null)
/*  880: 880 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  881: 881 */    nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  882:     */  }
/*  883:     */  
/*  884: 884 */  public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  885: 885 */    long function_pointer = caps.glMultiTexImage1DEXT;
/*  886: 886 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  887: 887 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  888: 888 */    if (pixels != null)
/*  889: 889 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  890: 890 */    nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  891:     */  }
/*  892:     */  
/*  893: 893 */  public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  894: 894 */    long function_pointer = caps.glMultiTexImage1DEXT;
/*  895: 895 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  896: 896 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  897: 897 */    if (pixels != null)
/*  898: 898 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  899: 899 */    nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  900:     */  }
/*  901:     */  
/*  902: 902 */  public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  903: 903 */    long function_pointer = caps.glMultiTexImage1DEXT;
/*  904: 904 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  905: 905 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  906: 906 */    if (pixels != null)
/*  907: 907 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  908: 908 */    nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  909:     */  }
/*  910:     */  
/*  911: 911 */  public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  912: 912 */    long function_pointer = caps.glMultiTexImage1DEXT;
/*  913: 913 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  914: 914 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  915: 915 */    if (pixels != null)
/*  916: 916 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  917: 917 */    nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/*  918:     */  
/*  919:     */  static native void nglMultiTexImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*  920:     */  
/*  921: 921 */  public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  922: 922 */    long function_pointer = caps.glMultiTexImage1DEXT;
/*  923: 923 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  924: 924 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  925: 925 */    nglMultiTexImage1DEXTBO(texunit, target, level, internalformat, width, border, format, type, pixels_buffer_offset, function_pointer);
/*  926:     */  }
/*  927:     */  
/*  928:     */  static native void nglMultiTexImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*  929:     */  
/*  930: 930 */  public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  931: 931 */    long function_pointer = caps.glMultiTexImage2DEXT;
/*  932: 932 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  933: 933 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  934: 934 */    if (pixels != null)
/*  935: 935 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  936: 936 */    nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  937:     */  }
/*  938:     */  
/*  939: 939 */  public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  940: 940 */    long function_pointer = caps.glMultiTexImage2DEXT;
/*  941: 941 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  942: 942 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  943: 943 */    if (pixels != null)
/*  944: 944 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  945: 945 */    nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  946:     */  }
/*  947:     */  
/*  948: 948 */  public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  949: 949 */    long function_pointer = caps.glMultiTexImage2DEXT;
/*  950: 950 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  951: 951 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  952: 952 */    if (pixels != null)
/*  953: 953 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  954: 954 */    nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  955:     */  }
/*  956:     */  
/*  957: 957 */  public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  958: 958 */    long function_pointer = caps.glMultiTexImage2DEXT;
/*  959: 959 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  960: 960 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  961: 961 */    if (pixels != null)
/*  962: 962 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  963: 963 */    nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*  964:     */  }
/*  965:     */  
/*  966: 966 */  public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  967: 967 */    long function_pointer = caps.glMultiTexImage2DEXT;
/*  968: 968 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  969: 969 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  970: 970 */    if (pixels != null)
/*  971: 971 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  972: 972 */    nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/*  973:     */  
/*  974:     */  static native void nglMultiTexImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*  975:     */  
/*  976: 976 */  public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  977: 977 */    long function_pointer = caps.glMultiTexImage2DEXT;
/*  978: 978 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  979: 979 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  980: 980 */    nglMultiTexImage2DEXTBO(texunit, target, level, internalformat, width, height, border, format, type, pixels_buffer_offset, function_pointer);
/*  981:     */  }
/*  982:     */  
/*  983:     */  static native void nglMultiTexImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*  984:     */  
/*  985: 985 */  public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  986: 986 */    long function_pointer = caps.glMultiTexSubImage1DEXT;
/*  987: 987 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  988: 988 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  989: 989 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  990: 990 */    nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  991:     */  }
/*  992:     */  
/*  993: 993 */  public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  994: 994 */    long function_pointer = caps.glMultiTexSubImage1DEXT;
/*  995: 995 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  996: 996 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  997: 997 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  998: 998 */    nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*  999:     */  }
/* 1000:     */  
/* 1001:1001 */  public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1002:1002 */    long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1003:1003 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1004:1004 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1005:1005 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 1006:1006 */    nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1007:     */  }
/* 1008:     */  
/* 1009:1009 */  public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1010:1010 */    long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1011:1011 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1012:1012 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1013:1013 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 1014:1014 */    nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1015:     */  }
/* 1016:     */  
/* 1017:1017 */  public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1018:1018 */    long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1019:1019 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1020:1020 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1021:1021 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 1022:1022 */    nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 1023:     */  
/* 1024:     */  static native void nglMultiTexSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1025:     */  
/* 1026:1026 */  public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1027:1027 */    long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1028:1028 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1029:1029 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1030:1030 */    nglMultiTexSubImage1DEXTBO(texunit, target, level, xoffset, width, format, type, pixels_buffer_offset, function_pointer);
/* 1031:     */  }
/* 1032:     */  
/* 1033:     */  static native void nglMultiTexSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1034:     */  
/* 1035:1035 */  public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1036:1036 */    long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1037:1037 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1038:1038 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1039:1039 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1040:1040 */    nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1041:     */  }
/* 1042:     */  
/* 1043:1043 */  public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1044:1044 */    long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1045:1045 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1046:1046 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1047:1047 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1048:1048 */    nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1049:     */  }
/* 1050:     */  
/* 1051:1051 */  public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1052:1052 */    long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1053:1053 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1054:1054 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1055:1055 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1056:1056 */    nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1057:     */  }
/* 1058:     */  
/* 1059:1059 */  public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1060:1060 */    long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1061:1061 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1062:1062 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1063:1063 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1064:1064 */    nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1065:     */  }
/* 1066:     */  
/* 1067:1067 */  public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1068:1068 */    long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1069:1069 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1070:1070 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1071:1071 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1072:1072 */    nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 1073:     */  
/* 1074:     */  static native void nglMultiTexSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1075:     */  
/* 1076:1076 */  public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1077:1077 */    long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1078:1078 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1079:1079 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1080:1080 */    nglMultiTexSubImage2DEXTBO(texunit, target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset, function_pointer);
/* 1081:     */  }
/* 1082:     */  
/* 1083:     */  static native void nglMultiTexSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1084:     */  
/* 1085:1085 */  public static void glCopyMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int x, int y, int width, int border) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1086:1086 */    long function_pointer = caps.glCopyMultiTexImage1DEXT;
/* 1087:1087 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1088:1088 */    nglCopyMultiTexImage1DEXT(texunit, target, level, internalformat, x, y, width, border, function_pointer);
/* 1089:     */  }
/* 1090:     */  
/* 1091:     */  static native void nglCopyMultiTexImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/* 1092:     */  
/* 1093:1093 */  public static void glCopyMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int x, int y, int width, int height, int border) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1094:1094 */    long function_pointer = caps.glCopyMultiTexImage2DEXT;
/* 1095:1095 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1096:1096 */    nglCopyMultiTexImage2DEXT(texunit, target, level, internalformat, x, y, width, height, border, function_pointer);
/* 1097:     */  }
/* 1098:     */  
/* 1099:     */  static native void nglCopyMultiTexImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/* 1100:     */  
/* 1101:1101 */  public static void glCopyMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int x, int y, int width) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1102:1102 */    long function_pointer = caps.glCopyMultiTexSubImage1DEXT;
/* 1103:1103 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1104:1104 */    nglCopyMultiTexSubImage1DEXT(texunit, target, level, xoffset, x, y, width, function_pointer);
/* 1105:     */  }
/* 1106:     */  
/* 1107:     */  static native void nglCopyMultiTexSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/* 1108:     */  
/* 1109:1109 */  public static void glCopyMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1110:1110 */    long function_pointer = caps.glCopyMultiTexSubImage2DEXT;
/* 1111:1111 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1112:1112 */    nglCopyMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, x, y, width, height, function_pointer);
/* 1113:     */  }
/* 1114:     */  
/* 1115:     */  static native void nglCopyMultiTexSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/* 1116:     */  
/* 1117:1117 */  public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1118:1118 */    long function_pointer = caps.glGetMultiTexImageEXT;
/* 1119:1119 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1120:1120 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1121:1121 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1122:1122 */    nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1123:     */  }
/* 1124:     */  
/* 1125:1125 */  public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1126:1126 */    long function_pointer = caps.glGetMultiTexImageEXT;
/* 1127:1127 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1128:1128 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1129:1129 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1130:1130 */    nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1131:     */  }
/* 1132:     */  
/* 1133:1133 */  public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1134:1134 */    long function_pointer = caps.glGetMultiTexImageEXT;
/* 1135:1135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1136:1136 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1137:1137 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1138:1138 */    nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1139:     */  }
/* 1140:     */  
/* 1141:1141 */  public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1142:1142 */    long function_pointer = caps.glGetMultiTexImageEXT;
/* 1143:1143 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1144:1144 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1145:1145 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1146:1146 */    nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1147:     */  }
/* 1148:     */  
/* 1149:1149 */  public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1150:1150 */    long function_pointer = caps.glGetMultiTexImageEXT;
/* 1151:1151 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1152:1152 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1153:1153 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1154:1154 */    nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 1155:     */  
/* 1156:     */  static native void nglGetMultiTexImageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 1157:     */  
/* 1158:1158 */  public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1159:1159 */    long function_pointer = caps.glGetMultiTexImageEXT;
/* 1160:1160 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1161:1161 */    GLChecks.ensurePackPBOenabled(caps);
/* 1162:1162 */    nglGetMultiTexImageEXTBO(texunit, target, level, format, type, pixels_buffer_offset, function_pointer);
/* 1163:     */  }
/* 1164:     */  
/* 1165:     */  static native void nglGetMultiTexImageEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 1166:     */  
/* 1167:1167 */  public static void glGetMultiTexParameterEXT(int texunit, int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1168:1168 */    long function_pointer = caps.glGetMultiTexParameterfvEXT;
/* 1169:1169 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1170:1170 */    BufferChecks.checkBuffer(params, 4);
/* 1171:1171 */    nglGetMultiTexParameterfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1172:     */  }
/* 1173:     */  
/* 1174:     */  static native void nglGetMultiTexParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1175:     */  
/* 1176:     */  public static float glGetMultiTexParameterfEXT(int texunit, int target, int pname) {
/* 1177:1177 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1178:1178 */    long function_pointer = caps.glGetMultiTexParameterfvEXT;
/* 1179:1179 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1180:1180 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1181:1181 */    nglGetMultiTexParameterfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1182:1182 */    return params.get(0);
/* 1183:     */  }
/* 1184:     */  
/* 1185:     */  public static void glGetMultiTexParameterEXT(int texunit, int target, int pname, IntBuffer params) {
/* 1186:1186 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1187:1187 */    long function_pointer = caps.glGetMultiTexParameterivEXT;
/* 1188:1188 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1189:1189 */    BufferChecks.checkBuffer(params, 4);
/* 1190:1190 */    nglGetMultiTexParameterivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1191:     */  }
/* 1192:     */  
/* 1193:     */  static native void nglGetMultiTexParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1194:     */  
/* 1195:     */  public static int glGetMultiTexParameteriEXT(int texunit, int target, int pname) {
/* 1196:1196 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1197:1197 */    long function_pointer = caps.glGetMultiTexParameterivEXT;
/* 1198:1198 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1199:1199 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1200:1200 */    nglGetMultiTexParameterivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1201:1201 */    return params.get(0);
/* 1202:     */  }
/* 1203:     */  
/* 1204:     */  public static void glGetMultiTexLevelParameterEXT(int texunit, int target, int level, int pname, FloatBuffer params) {
/* 1205:1205 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1206:1206 */    long function_pointer = caps.glGetMultiTexLevelParameterfvEXT;
/* 1207:1207 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1208:1208 */    BufferChecks.checkBuffer(params, 4);
/* 1209:1209 */    nglGetMultiTexLevelParameterfvEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1210:     */  }
/* 1211:     */  
/* 1212:     */  static native void nglGetMultiTexLevelParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1213:     */  
/* 1214:     */  public static float glGetMultiTexLevelParameterfEXT(int texunit, int target, int level, int pname) {
/* 1215:1215 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1216:1216 */    long function_pointer = caps.glGetMultiTexLevelParameterfvEXT;
/* 1217:1217 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1218:1218 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1219:1219 */    nglGetMultiTexLevelParameterfvEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1220:1220 */    return params.get(0);
/* 1221:     */  }
/* 1222:     */  
/* 1223:     */  public static void glGetMultiTexLevelParameterEXT(int texunit, int target, int level, int pname, IntBuffer params) {
/* 1224:1224 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1225:1225 */    long function_pointer = caps.glGetMultiTexLevelParameterivEXT;
/* 1226:1226 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1227:1227 */    BufferChecks.checkBuffer(params, 4);
/* 1228:1228 */    nglGetMultiTexLevelParameterivEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1229:     */  }
/* 1230:     */  
/* 1231:     */  static native void nglGetMultiTexLevelParameterivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1232:     */  
/* 1233:     */  public static int glGetMultiTexLevelParameteriEXT(int texunit, int target, int level, int pname) {
/* 1234:1234 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1235:1235 */    long function_pointer = caps.glGetMultiTexLevelParameterivEXT;
/* 1236:1236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1237:1237 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1238:1238 */    nglGetMultiTexLevelParameterivEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1239:1239 */    return params.get(0);
/* 1240:     */  }
/* 1241:     */  
/* 1242:     */  public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
/* 1243:1243 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1244:1244 */    long function_pointer = caps.glMultiTexImage3DEXT;
/* 1245:1245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1246:1246 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1247:1247 */    if (pixels != null)
/* 1248:1248 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1249:1249 */    nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 1250:     */  }
/* 1251:     */  
/* 1252:1252 */  public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1253:1253 */    long function_pointer = caps.glMultiTexImage3DEXT;
/* 1254:1254 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1255:1255 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1256:1256 */    if (pixels != null)
/* 1257:1257 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1258:1258 */    nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 1259:     */  }
/* 1260:     */  
/* 1261:1261 */  public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1262:1262 */    long function_pointer = caps.glMultiTexImage3DEXT;
/* 1263:1263 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1264:1264 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1265:1265 */    if (pixels != null)
/* 1266:1266 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1267:1267 */    nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 1268:     */  }
/* 1269:     */  
/* 1270:1270 */  public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1271:1271 */    long function_pointer = caps.glMultiTexImage3DEXT;
/* 1272:1272 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1273:1273 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1274:1274 */    if (pixels != null)
/* 1275:1275 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1276:1276 */    nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 1277:     */  }
/* 1278:     */  
/* 1279:1279 */  public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1280:1280 */    long function_pointer = caps.glMultiTexImage3DEXT;
/* 1281:1281 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1282:1282 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1283:1283 */    if (pixels != null)
/* 1284:1284 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1285:1285 */    nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/* 1286:     */  
/* 1287:     */  static native void nglMultiTexImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 1288:     */  
/* 1289:1289 */  public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1290:1290 */    long function_pointer = caps.glMultiTexImage3DEXT;
/* 1291:1291 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1292:1292 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1293:1293 */    nglMultiTexImage3DEXTBO(texunit, target, level, internalformat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer);
/* 1294:     */  }
/* 1295:     */  
/* 1296:     */  static native void nglMultiTexImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 1297:     */  
/* 1298:1298 */  public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1299:1299 */    long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1300:1300 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1301:1301 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1302:1302 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1303:1303 */    nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1304:     */  }
/* 1305:     */  
/* 1306:1306 */  public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1307:1307 */    long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1308:1308 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1309:1309 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1310:1310 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1311:1311 */    nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1312:     */  }
/* 1313:     */  
/* 1314:1314 */  public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1315:1315 */    long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1316:1316 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1317:1317 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1318:1318 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1319:1319 */    nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1320:     */  }
/* 1321:     */  
/* 1322:1322 */  public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1323:1323 */    long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1324:1324 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1325:1325 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1326:1326 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1327:1327 */    nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 1328:     */  }
/* 1329:     */  
/* 1330:1330 */  public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1331:1331 */    long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1332:1332 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1333:1333 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1334:1334 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1335:1335 */    nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 1336:     */  
/* 1337:     */  static native void nglMultiTexSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/* 1338:     */  
/* 1339:1339 */  public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1340:1340 */    long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1341:1341 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1342:1342 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1343:1343 */    nglMultiTexSubImage3DEXTBO(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer);
/* 1344:     */  }
/* 1345:     */  
/* 1346:     */  static native void nglMultiTexSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/* 1347:     */  
/* 1348:1348 */  public static void glCopyMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1349:1349 */    long function_pointer = caps.glCopyMultiTexSubImage3DEXT;
/* 1350:1350 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1351:1351 */    nglCopyMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
/* 1352:     */  }
/* 1353:     */  
/* 1354:     */  static native void nglCopyMultiTexSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/* 1355:     */  
/* 1356:1356 */  public static void glEnableClientStateIndexedEXT(int array, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1357:1357 */    long function_pointer = caps.glEnableClientStateIndexedEXT;
/* 1358:1358 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1359:1359 */    nglEnableClientStateIndexedEXT(array, index, function_pointer);
/* 1360:     */  }
/* 1361:     */  
/* 1362:     */  static native void nglEnableClientStateIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/* 1363:     */  
/* 1364:1364 */  public static void glDisableClientStateIndexedEXT(int array, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1365:1365 */    long function_pointer = caps.glDisableClientStateIndexedEXT;
/* 1366:1366 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1367:1367 */    nglDisableClientStateIndexedEXT(array, index, function_pointer);
/* 1368:     */  }
/* 1369:     */  
/* 1370:     */  static native void nglDisableClientStateIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/* 1371:     */  
/* 1372:1372 */  public static void glEnableClientStateiEXT(int array, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1373:1373 */    long function_pointer = caps.glEnableClientStateiEXT;
/* 1374:1374 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1375:1375 */    nglEnableClientStateiEXT(array, index, function_pointer);
/* 1376:     */  }
/* 1377:     */  
/* 1378:     */  static native void nglEnableClientStateiEXT(int paramInt1, int paramInt2, long paramLong);
/* 1379:     */  
/* 1380:1380 */  public static void glDisableClientStateiEXT(int array, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1381:1381 */    long function_pointer = caps.glDisableClientStateiEXT;
/* 1382:1382 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1383:1383 */    nglDisableClientStateiEXT(array, index, function_pointer);
/* 1384:     */  }
/* 1385:     */  
/* 1386:     */  static native void nglDisableClientStateiEXT(int paramInt1, int paramInt2, long paramLong);
/* 1387:     */  
/* 1388:1388 */  public static void glGetFloatIndexedEXT(int pname, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1389:1389 */    long function_pointer = caps.glGetFloatIndexedvEXT;
/* 1390:1390 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1391:1391 */    BufferChecks.checkBuffer(params, 16);
/* 1392:1392 */    nglGetFloatIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1393:     */  }
/* 1394:     */  
/* 1395:     */  static native void nglGetFloatIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1396:     */  
/* 1397:     */  public static float glGetFloatIndexedEXT(int pname, int index) {
/* 1398:1398 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1399:1399 */    long function_pointer = caps.glGetFloatIndexedvEXT;
/* 1400:1400 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1401:1401 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1402:1402 */    nglGetFloatIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1403:1403 */    return params.get(0);
/* 1404:     */  }
/* 1405:     */  
/* 1406:     */  public static void glGetDoubleIndexedEXT(int pname, int index, DoubleBuffer params) {
/* 1407:1407 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1408:1408 */    long function_pointer = caps.glGetDoubleIndexedvEXT;
/* 1409:1409 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1410:1410 */    BufferChecks.checkBuffer(params, 16);
/* 1411:1411 */    nglGetDoubleIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1412:     */  }
/* 1413:     */  
/* 1414:     */  static native void nglGetDoubleIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1415:     */  
/* 1416:     */  public static double glGetDoubleIndexedEXT(int pname, int index) {
/* 1417:1417 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1418:1418 */    long function_pointer = caps.glGetDoubleIndexedvEXT;
/* 1419:1419 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1420:1420 */    DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1421:1421 */    nglGetDoubleIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1422:1422 */    return params.get(0);
/* 1423:     */  }
/* 1424:     */  
/* 1425:     */  public static ByteBuffer glGetPointerIndexedEXT(int pname, int index, long result_size) {
/* 1426:1426 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1427:1427 */    long function_pointer = caps.glGetPointerIndexedvEXT;
/* 1428:1428 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1429:1429 */    ByteBuffer __result = nglGetPointerIndexedvEXT(pname, index, result_size, function_pointer);
/* 1430:1430 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 1431:     */  }
/* 1432:     */  
/* 1433:     */  static native ByteBuffer nglGetPointerIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1434:     */  
/* 1435:1435 */  public static void glGetFloatEXT(int pname, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1436:1436 */    long function_pointer = caps.glGetFloati_vEXT;
/* 1437:1437 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1438:1438 */    BufferChecks.checkBuffer(params, 16);
/* 1439:1439 */    nglGetFloati_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1440:     */  }
/* 1441:     */  
/* 1442:     */  static native void nglGetFloati_vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1443:     */  
/* 1444:     */  public static float glGetFloatEXT(int pname, int index) {
/* 1445:1445 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1446:1446 */    long function_pointer = caps.glGetFloati_vEXT;
/* 1447:1447 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1448:1448 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1449:1449 */    nglGetFloati_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1450:1450 */    return params.get(0);
/* 1451:     */  }
/* 1452:     */  
/* 1453:     */  public static void glGetDoubleEXT(int pname, int index, DoubleBuffer params) {
/* 1454:1454 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1455:1455 */    long function_pointer = caps.glGetDoublei_vEXT;
/* 1456:1456 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1457:1457 */    BufferChecks.checkBuffer(params, 16);
/* 1458:1458 */    nglGetDoublei_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1459:     */  }
/* 1460:     */  
/* 1461:     */  static native void nglGetDoublei_vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1462:     */  
/* 1463:     */  public static double glGetDoubleEXT(int pname, int index) {
/* 1464:1464 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1465:1465 */    long function_pointer = caps.glGetDoublei_vEXT;
/* 1466:1466 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1467:1467 */    DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1468:1468 */    nglGetDoublei_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1469:1469 */    return params.get(0);
/* 1470:     */  }
/* 1471:     */  
/* 1472:     */  public static ByteBuffer glGetPointerEXT(int pname, int index, long result_size) {
/* 1473:1473 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1474:1474 */    long function_pointer = caps.glGetPointeri_vEXT;
/* 1475:1475 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1476:1476 */    ByteBuffer __result = nglGetPointeri_vEXT(pname, index, result_size, function_pointer);
/* 1477:1477 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 1478:     */  }
/* 1479:     */  
/* 1480:     */  static native ByteBuffer nglGetPointeri_vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1481:     */  
/* 1482:1482 */  public static void glEnableIndexedEXT(int cap, int index) { EXTDrawBuffers2.glEnableIndexedEXT(cap, index); }
/* 1483:     */  
/* 1484:     */  public static void glDisableIndexedEXT(int cap, int index)
/* 1485:     */  {
/* 1486:1486 */    EXTDrawBuffers2.glDisableIndexedEXT(cap, index);
/* 1487:     */  }
/* 1488:     */  
/* 1489:     */  public static boolean glIsEnabledIndexedEXT(int cap, int index) {
/* 1490:1490 */    return EXTDrawBuffers2.glIsEnabledIndexedEXT(cap, index);
/* 1491:     */  }
/* 1492:     */  
/* 1493:     */  public static void glGetIntegerIndexedEXT(int pname, int index, IntBuffer params) {
/* 1494:1494 */    EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index, params);
/* 1495:     */  }
/* 1496:     */  
/* 1497:     */  public static int glGetIntegerIndexedEXT(int pname, int index)
/* 1498:     */  {
/* 1499:1499 */    return EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index);
/* 1500:     */  }
/* 1501:     */  
/* 1502:     */  public static void glGetBooleanIndexedEXT(int pname, int index, ByteBuffer params) {
/* 1503:1503 */    EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index, params);
/* 1504:     */  }
/* 1505:     */  
/* 1506:     */  public static boolean glGetBooleanIndexedEXT(int pname, int index)
/* 1507:     */  {
/* 1508:1508 */    return EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index);
/* 1509:     */  }
/* 1510:     */  
/* 1511:     */  public static void glNamedProgramStringEXT(int program, int target, int format, ByteBuffer string) {
/* 1512:1512 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1513:1513 */    long function_pointer = caps.glNamedProgramStringEXT;
/* 1514:1514 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1515:1515 */    BufferChecks.checkDirect(string);
/* 1516:1516 */    nglNamedProgramStringEXT(program, target, format, string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/* 1517:     */  }
/* 1518:     */  
/* 1519:     */  static native void nglNamedProgramStringEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1520:     */  
/* 1521:     */  public static void glNamedProgramStringEXT(int program, int target, int format, CharSequence string) {
/* 1522:1522 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1523:1523 */    long function_pointer = caps.glNamedProgramStringEXT;
/* 1524:1524 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1525:1525 */    nglNamedProgramStringEXT(program, target, format, string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/* 1526:     */  }
/* 1527:     */  
/* 1528:     */  public static void glNamedProgramLocalParameter4dEXT(int program, int target, int index, double x, double y, double z, double w) {
/* 1529:1529 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1530:1530 */    long function_pointer = caps.glNamedProgramLocalParameter4dEXT;
/* 1531:1531 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1532:1532 */    nglNamedProgramLocalParameter4dEXT(program, target, index, x, y, z, w, function_pointer);
/* 1533:     */  }
/* 1534:     */  
/* 1535:     */  static native void nglNamedProgramLocalParameter4dEXT(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 1536:     */  
/* 1537:1537 */  public static void glNamedProgramLocalParameter4EXT(int program, int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1538:1538 */    long function_pointer = caps.glNamedProgramLocalParameter4dvEXT;
/* 1539:1539 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1540:1540 */    BufferChecks.checkBuffer(params, 4);
/* 1541:1541 */    nglNamedProgramLocalParameter4dvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 1542:     */  }
/* 1543:     */  
/* 1544:     */  static native void nglNamedProgramLocalParameter4dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1545:     */  
/* 1546:1546 */  public static void glNamedProgramLocalParameter4fEXT(int program, int target, int index, float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1547:1547 */    long function_pointer = caps.glNamedProgramLocalParameter4fEXT;
/* 1548:1548 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1549:1549 */    nglNamedProgramLocalParameter4fEXT(program, target, index, x, y, z, w, function_pointer);
/* 1550:     */  }
/* 1551:     */  
/* 1552:     */  static native void nglNamedProgramLocalParameter4fEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 1553:     */  
/* 1554:1554 */  public static void glNamedProgramLocalParameter4EXT(int program, int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1555:1555 */    long function_pointer = caps.glNamedProgramLocalParameter4fvEXT;
/* 1556:1556 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1557:1557 */    BufferChecks.checkBuffer(params, 4);
/* 1558:1558 */    nglNamedProgramLocalParameter4fvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 1559:     */  }
/* 1560:     */  
/* 1561:     */  static native void nglNamedProgramLocalParameter4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1562:     */  
/* 1563:1563 */  public static void glGetNamedProgramLocalParameterEXT(int program, int target, int index, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1564:1564 */    long function_pointer = caps.glGetNamedProgramLocalParameterdvEXT;
/* 1565:1565 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1566:1566 */    BufferChecks.checkBuffer(params, 4);
/* 1567:1567 */    nglGetNamedProgramLocalParameterdvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 1568:     */  }
/* 1569:     */  
/* 1570:     */  static native void nglGetNamedProgramLocalParameterdvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1571:     */  
/* 1572:1572 */  public static void glGetNamedProgramLocalParameterEXT(int program, int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1573:1573 */    long function_pointer = caps.glGetNamedProgramLocalParameterfvEXT;
/* 1574:1574 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1575:1575 */    BufferChecks.checkBuffer(params, 4);
/* 1576:1576 */    nglGetNamedProgramLocalParameterfvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 1577:     */  }
/* 1578:     */  
/* 1579:     */  static native void nglGetNamedProgramLocalParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1580:     */  
/* 1581:1581 */  public static void glGetNamedProgramEXT(int program, int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1582:1582 */    long function_pointer = caps.glGetNamedProgramivEXT;
/* 1583:1583 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1584:1584 */    BufferChecks.checkBuffer(params, 4);
/* 1585:1585 */    nglGetNamedProgramivEXT(program, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1586:     */  }
/* 1587:     */  
/* 1588:     */  static native void nglGetNamedProgramivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1589:     */  
/* 1590:     */  public static int glGetNamedProgramEXT(int program, int target, int pname) {
/* 1591:1591 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1592:1592 */    long function_pointer = caps.glGetNamedProgramivEXT;
/* 1593:1593 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1594:1594 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 1595:1595 */    nglGetNamedProgramivEXT(program, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1596:1596 */    return params.get(0);
/* 1597:     */  }
/* 1598:     */  
/* 1599:     */  public static void glGetNamedProgramStringEXT(int program, int target, int pname, ByteBuffer string) {
/* 1600:1600 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1601:1601 */    long function_pointer = caps.glGetNamedProgramStringEXT;
/* 1602:1602 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1603:1603 */    BufferChecks.checkDirect(string);
/* 1604:1604 */    nglGetNamedProgramStringEXT(program, target, pname, MemoryUtil.getAddress(string), function_pointer);
/* 1605:     */  }
/* 1606:     */  
/* 1607:     */  static native void nglGetNamedProgramStringEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1608:     */  
/* 1609:     */  public static String glGetNamedProgramStringEXT(int program, int target, int pname) {
/* 1610:1610 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1611:1611 */    long function_pointer = caps.glGetNamedProgramStringEXT;
/* 1612:1612 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1613:1613 */    int programLength = glGetNamedProgramEXT(program, target, 34343);
/* 1614:1614 */    ByteBuffer paramString = APIUtil.getBufferByte(caps, programLength);
/* 1615:1615 */    nglGetNamedProgramStringEXT(program, target, pname, MemoryUtil.getAddress(paramString), function_pointer);
/* 1616:1616 */    paramString.limit(programLength);
/* 1617:1617 */    return APIUtil.getString(caps, paramString);
/* 1618:     */  }
/* 1619:     */  
/* 1620:     */  public static void glCompressedTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
/* 1621:1621 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1622:1622 */    long function_pointer = caps.glCompressedTextureImage3DEXT;
/* 1623:1623 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1624:1624 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1625:1625 */    BufferChecks.checkDirect(data);
/* 1626:1626 */    nglCompressedTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1627:     */  
/* 1628:     */  static native void nglCompressedTextureImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1629:     */  
/* 1630:1630 */  public static void glCompressedTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1631:1631 */    long function_pointer = caps.glCompressedTextureImage3DEXT;
/* 1632:1632 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1633:1633 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1634:1634 */    nglCompressedTextureImage3DEXTBO(texture, target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset, function_pointer);
/* 1635:     */  }
/* 1636:     */  
/* 1637:     */  static native void nglCompressedTextureImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1638:     */  
/* 1639:1639 */  public static void glCompressedTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1640:1640 */    long function_pointer = caps.glCompressedTextureImage2DEXT;
/* 1641:1641 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1642:1642 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1643:1643 */    BufferChecks.checkDirect(data);
/* 1644:1644 */    nglCompressedTextureImage2DEXT(texture, target, level, internalformat, width, height, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1645:     */  
/* 1646:     */  static native void nglCompressedTextureImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 1647:     */  
/* 1648:1648 */  public static void glCompressedTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1649:1649 */    long function_pointer = caps.glCompressedTextureImage2DEXT;
/* 1650:1650 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1651:1651 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1652:1652 */    nglCompressedTextureImage2DEXTBO(texture, target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset, function_pointer);
/* 1653:     */  }
/* 1654:     */  
/* 1655:     */  static native void nglCompressedTextureImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 1656:     */  
/* 1657:1657 */  public static void glCompressedTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1658:1658 */    long function_pointer = caps.glCompressedTextureImage1DEXT;
/* 1659:1659 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1660:1660 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1661:1661 */    BufferChecks.checkDirect(data);
/* 1662:1662 */    nglCompressedTextureImage1DEXT(texture, target, level, internalformat, width, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1663:     */  
/* 1664:     */  static native void nglCompressedTextureImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1665:     */  
/* 1666:1666 */  public static void glCompressedTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1667:1667 */    long function_pointer = caps.glCompressedTextureImage1DEXT;
/* 1668:1668 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1669:1669 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1670:1670 */    nglCompressedTextureImage1DEXTBO(texture, target, level, internalformat, width, border, data_imageSize, data_buffer_offset, function_pointer);
/* 1671:     */  }
/* 1672:     */  
/* 1673:     */  static native void nglCompressedTextureImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1674:     */  
/* 1675:1675 */  public static void glCompressedTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1676:1676 */    long function_pointer = caps.glCompressedTextureSubImage3DEXT;
/* 1677:1677 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1678:1678 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1679:1679 */    BufferChecks.checkDirect(data);
/* 1680:1680 */    nglCompressedTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1681:     */  
/* 1682:     */  static native void nglCompressedTextureSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/* 1683:     */  
/* 1684:1684 */  public static void glCompressedTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1685:1685 */    long function_pointer = caps.glCompressedTextureSubImage3DEXT;
/* 1686:1686 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1687:1687 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1688:1688 */    nglCompressedTextureSubImage3DEXTBO(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset, function_pointer);
/* 1689:     */  }
/* 1690:     */  
/* 1691:     */  static native void nglCompressedTextureSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/* 1692:     */  
/* 1693:1693 */  public static void glCompressedTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1694:1694 */    long function_pointer = caps.glCompressedTextureSubImage2DEXT;
/* 1695:1695 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1696:1696 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1697:1697 */    BufferChecks.checkDirect(data);
/* 1698:1698 */    nglCompressedTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1699:     */  
/* 1700:     */  static native void nglCompressedTextureSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1701:     */  
/* 1702:1702 */  public static void glCompressedTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1703:1703 */    long function_pointer = caps.glCompressedTextureSubImage2DEXT;
/* 1704:1704 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1705:1705 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1706:1706 */    nglCompressedTextureSubImage2DEXTBO(texture, target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset, function_pointer);
/* 1707:     */  }
/* 1708:     */  
/* 1709:     */  static native void nglCompressedTextureSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1710:     */  
/* 1711:1711 */  public static void glCompressedTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1712:1712 */    long function_pointer = caps.glCompressedTextureSubImage1DEXT;
/* 1713:1713 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1714:1714 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1715:1715 */    BufferChecks.checkDirect(data);
/* 1716:1716 */    nglCompressedTextureSubImage1DEXT(texture, target, level, xoffset, width, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1717:     */  
/* 1718:     */  static native void nglCompressedTextureSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1719:     */  
/* 1720:1720 */  public static void glCompressedTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1721:1721 */    long function_pointer = caps.glCompressedTextureSubImage1DEXT;
/* 1722:1722 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1723:1723 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1724:1724 */    nglCompressedTextureSubImage1DEXTBO(texture, target, level, xoffset, width, format, data_imageSize, data_buffer_offset, function_pointer);
/* 1725:     */  }
/* 1726:     */  
/* 1727:     */  static native void nglCompressedTextureSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1728:     */  
/* 1729:1729 */  public static void glGetCompressedTextureImageEXT(int texture, int target, int level, ByteBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1730:1730 */    long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1731:1731 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1732:1732 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1733:1733 */    BufferChecks.checkDirect(img);
/* 1734:1734 */    nglGetCompressedTextureImageEXT(texture, target, level, MemoryUtil.getAddress(img), function_pointer);
/* 1735:     */  }
/* 1736:     */  
/* 1737:1737 */  public static void glGetCompressedTextureImageEXT(int texture, int target, int level, IntBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1738:1738 */    long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1739:1739 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1740:1740 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1741:1741 */    BufferChecks.checkDirect(img);
/* 1742:1742 */    nglGetCompressedTextureImageEXT(texture, target, level, MemoryUtil.getAddress(img), function_pointer);
/* 1743:     */  }
/* 1744:     */  
/* 1745:1745 */  public static void glGetCompressedTextureImageEXT(int texture, int target, int level, ShortBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1746:1746 */    long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1747:1747 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1748:1748 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1749:1749 */    BufferChecks.checkDirect(img);
/* 1750:1750 */    nglGetCompressedTextureImageEXT(texture, target, level, MemoryUtil.getAddress(img), function_pointer); }
/* 1751:     */  
/* 1752:     */  static native void nglGetCompressedTextureImageEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1753:     */  
/* 1754:1754 */  public static void glGetCompressedTextureImageEXT(int texture, int target, int level, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1755:1755 */    long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1756:1756 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1757:1757 */    GLChecks.ensurePackPBOenabled(caps);
/* 1758:1758 */    nglGetCompressedTextureImageEXTBO(texture, target, level, img_buffer_offset, function_pointer);
/* 1759:     */  }
/* 1760:     */  
/* 1761:     */  static native void nglGetCompressedTextureImageEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1762:     */  
/* 1763:1763 */  public static void glCompressedMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1764:1764 */    long function_pointer = caps.glCompressedMultiTexImage3DEXT;
/* 1765:1765 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1766:1766 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1767:1767 */    BufferChecks.checkDirect(data);
/* 1768:1768 */    nglCompressedMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1769:     */  
/* 1770:     */  static native void nglCompressedMultiTexImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1771:     */  
/* 1772:1772 */  public static void glCompressedMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1773:1773 */    long function_pointer = caps.glCompressedMultiTexImage3DEXT;
/* 1774:1774 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1775:1775 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1776:1776 */    nglCompressedMultiTexImage3DEXTBO(texunit, target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset, function_pointer);
/* 1777:     */  }
/* 1778:     */  
/* 1779:     */  static native void nglCompressedMultiTexImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1780:     */  
/* 1781:1781 */  public static void glCompressedMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1782:1782 */    long function_pointer = caps.glCompressedMultiTexImage2DEXT;
/* 1783:1783 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1784:1784 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1785:1785 */    BufferChecks.checkDirect(data);
/* 1786:1786 */    nglCompressedMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1787:     */  
/* 1788:     */  static native void nglCompressedMultiTexImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 1789:     */  
/* 1790:1790 */  public static void glCompressedMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1791:1791 */    long function_pointer = caps.glCompressedMultiTexImage2DEXT;
/* 1792:1792 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1793:1793 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1794:1794 */    nglCompressedMultiTexImage2DEXTBO(texunit, target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset, function_pointer);
/* 1795:     */  }
/* 1796:     */  
/* 1797:     */  static native void nglCompressedMultiTexImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 1798:     */  
/* 1799:1799 */  public static void glCompressedMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1800:1800 */    long function_pointer = caps.glCompressedMultiTexImage1DEXT;
/* 1801:1801 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1802:1802 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1803:1803 */    BufferChecks.checkDirect(data);
/* 1804:1804 */    nglCompressedMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1805:     */  
/* 1806:     */  static native void nglCompressedMultiTexImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1807:     */  
/* 1808:1808 */  public static void glCompressedMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1809:1809 */    long function_pointer = caps.glCompressedMultiTexImage1DEXT;
/* 1810:1810 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1811:1811 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1812:1812 */    nglCompressedMultiTexImage1DEXTBO(texunit, target, level, internalformat, width, border, data_imageSize, data_buffer_offset, function_pointer);
/* 1813:     */  }
/* 1814:     */  
/* 1815:     */  static native void nglCompressedMultiTexImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1816:     */  
/* 1817:1817 */  public static void glCompressedMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1818:1818 */    long function_pointer = caps.glCompressedMultiTexSubImage3DEXT;
/* 1819:1819 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1820:1820 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1821:1821 */    BufferChecks.checkDirect(data);
/* 1822:1822 */    nglCompressedMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1823:     */  
/* 1824:     */  static native void nglCompressedMultiTexSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/* 1825:     */  
/* 1826:1826 */  public static void glCompressedMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1827:1827 */    long function_pointer = caps.glCompressedMultiTexSubImage3DEXT;
/* 1828:1828 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1829:1829 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1830:1830 */    nglCompressedMultiTexSubImage3DEXTBO(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset, function_pointer);
/* 1831:     */  }
/* 1832:     */  
/* 1833:     */  static native void nglCompressedMultiTexSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/* 1834:     */  
/* 1835:1835 */  public static void glCompressedMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1836:1836 */    long function_pointer = caps.glCompressedMultiTexSubImage2DEXT;
/* 1837:1837 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1838:1838 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1839:1839 */    BufferChecks.checkDirect(data);
/* 1840:1840 */    nglCompressedMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1841:     */  
/* 1842:     */  static native void nglCompressedMultiTexSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1843:     */  
/* 1844:1844 */  public static void glCompressedMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1845:1845 */    long function_pointer = caps.glCompressedMultiTexSubImage2DEXT;
/* 1846:1846 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1847:1847 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1848:1848 */    nglCompressedMultiTexSubImage2DEXTBO(texunit, target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset, function_pointer);
/* 1849:     */  }
/* 1850:     */  
/* 1851:     */  static native void nglCompressedMultiTexSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 1852:     */  
/* 1853:1853 */  public static void glCompressedMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1854:1854 */    long function_pointer = caps.glCompressedMultiTexSubImage1DEXT;
/* 1855:1855 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1856:1856 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 1857:1857 */    BufferChecks.checkDirect(data);
/* 1858:1858 */    nglCompressedMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer); }
/* 1859:     */  
/* 1860:     */  static native void nglCompressedMultiTexSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1861:     */  
/* 1862:1862 */  public static void glCompressedMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1863:1863 */    long function_pointer = caps.glCompressedMultiTexSubImage1DEXT;
/* 1864:1864 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1865:1865 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 1866:1866 */    nglCompressedMultiTexSubImage1DEXTBO(texunit, target, level, xoffset, width, format, data_imageSize, data_buffer_offset, function_pointer);
/* 1867:     */  }
/* 1868:     */  
/* 1869:     */  static native void nglCompressedMultiTexSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/* 1870:     */  
/* 1871:1871 */  public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, ByteBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1872:1872 */    long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1873:1873 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1874:1874 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1875:1875 */    BufferChecks.checkDirect(img);
/* 1876:1876 */    nglGetCompressedMultiTexImageEXT(texunit, target, level, MemoryUtil.getAddress(img), function_pointer);
/* 1877:     */  }
/* 1878:     */  
/* 1879:1879 */  public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, IntBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1880:1880 */    long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1881:1881 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1882:1882 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1883:1883 */    BufferChecks.checkDirect(img);
/* 1884:1884 */    nglGetCompressedMultiTexImageEXT(texunit, target, level, MemoryUtil.getAddress(img), function_pointer);
/* 1885:     */  }
/* 1886:     */  
/* 1887:1887 */  public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, ShortBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1888:1888 */    long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1889:1889 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1890:1890 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1891:1891 */    BufferChecks.checkDirect(img);
/* 1892:1892 */    nglGetCompressedMultiTexImageEXT(texunit, target, level, MemoryUtil.getAddress(img), function_pointer); }
/* 1893:     */  
/* 1894:     */  static native void nglGetCompressedMultiTexImageEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1895:     */  
/* 1896:1896 */  public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1897:1897 */    long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1898:1898 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1899:1899 */    GLChecks.ensurePackPBOenabled(caps);
/* 1900:1900 */    nglGetCompressedMultiTexImageEXTBO(texunit, target, level, img_buffer_offset, function_pointer);
/* 1901:     */  }
/* 1902:     */  
/* 1903:     */  static native void nglGetCompressedMultiTexImageEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1904:     */  
/* 1905:1905 */  public static void glMatrixLoadTransposeEXT(int matrixMode, FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1906:1906 */    long function_pointer = caps.glMatrixLoadTransposefEXT;
/* 1907:1907 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1908:1908 */    BufferChecks.checkBuffer(m, 16);
/* 1909:1909 */    nglMatrixLoadTransposefEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/* 1910:     */  }
/* 1911:     */  
/* 1912:     */  static native void nglMatrixLoadTransposefEXT(int paramInt, long paramLong1, long paramLong2);
/* 1913:     */  
/* 1914:1914 */  public static void glMatrixLoadTransposeEXT(int matrixMode, DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1915:1915 */    long function_pointer = caps.glMatrixLoadTransposedEXT;
/* 1916:1916 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1917:1917 */    BufferChecks.checkBuffer(m, 16);
/* 1918:1918 */    nglMatrixLoadTransposedEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/* 1919:     */  }
/* 1920:     */  
/* 1921:     */  static native void nglMatrixLoadTransposedEXT(int paramInt, long paramLong1, long paramLong2);
/* 1922:     */  
/* 1923:1923 */  public static void glMatrixMultTransposeEXT(int matrixMode, FloatBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1924:1924 */    long function_pointer = caps.glMatrixMultTransposefEXT;
/* 1925:1925 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1926:1926 */    BufferChecks.checkBuffer(m, 16);
/* 1927:1927 */    nglMatrixMultTransposefEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/* 1928:     */  }
/* 1929:     */  
/* 1930:     */  static native void nglMatrixMultTransposefEXT(int paramInt, long paramLong1, long paramLong2);
/* 1931:     */  
/* 1932:1932 */  public static void glMatrixMultTransposeEXT(int matrixMode, DoubleBuffer m) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1933:1933 */    long function_pointer = caps.glMatrixMultTransposedEXT;
/* 1934:1934 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1935:1935 */    BufferChecks.checkBuffer(m, 16);
/* 1936:1936 */    nglMatrixMultTransposedEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/* 1937:     */  }
/* 1938:     */  
/* 1939:     */  static native void nglMatrixMultTransposedEXT(int paramInt, long paramLong1, long paramLong2);
/* 1940:     */  
/* 1941:1941 */  public static void glNamedBufferDataEXT(int buffer, long data_size, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1942:1942 */    long function_pointer = caps.glNamedBufferDataEXT;
/* 1943:1943 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1944:1944 */    nglNamedBufferDataEXT(buffer, data_size, 0L, usage, function_pointer);
/* 1945:     */  }
/* 1946:     */  
/* 1947:1947 */  public static void glNamedBufferDataEXT(int buffer, ByteBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1948:1948 */    long function_pointer = caps.glNamedBufferDataEXT;
/* 1949:1949 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1950:1950 */    BufferChecks.checkDirect(data);
/* 1951:1951 */    nglNamedBufferDataEXT(buffer, data.remaining(), MemoryUtil.getAddress(data), usage, function_pointer);
/* 1952:     */  }
/* 1953:     */  
/* 1954:1954 */  public static void glNamedBufferDataEXT(int buffer, DoubleBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1955:1955 */    long function_pointer = caps.glNamedBufferDataEXT;
/* 1956:1956 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1957:1957 */    BufferChecks.checkDirect(data);
/* 1958:1958 */    nglNamedBufferDataEXT(buffer, data.remaining() << 3, MemoryUtil.getAddress(data), usage, function_pointer);
/* 1959:     */  }
/* 1960:     */  
/* 1961:1961 */  public static void glNamedBufferDataEXT(int buffer, FloatBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1962:1962 */    long function_pointer = caps.glNamedBufferDataEXT;
/* 1963:1963 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1964:1964 */    BufferChecks.checkDirect(data);
/* 1965:1965 */    nglNamedBufferDataEXT(buffer, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/* 1966:     */  }
/* 1967:     */  
/* 1968:1968 */  public static void glNamedBufferDataEXT(int buffer, IntBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1969:1969 */    long function_pointer = caps.glNamedBufferDataEXT;
/* 1970:1970 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1971:1971 */    BufferChecks.checkDirect(data);
/* 1972:1972 */    nglNamedBufferDataEXT(buffer, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/* 1973:     */  }
/* 1974:     */  
/* 1975:1975 */  public static void glNamedBufferDataEXT(int buffer, ShortBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1976:1976 */    long function_pointer = caps.glNamedBufferDataEXT;
/* 1977:1977 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1978:1978 */    BufferChecks.checkDirect(data);
/* 1979:1979 */    nglNamedBufferDataEXT(buffer, data.remaining() << 1, MemoryUtil.getAddress(data), usage, function_pointer);
/* 1980:     */  }
/* 1981:     */  
/* 1982:     */  static native void nglNamedBufferDataEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/* 1983:     */  
/* 1984:1984 */  public static void glNamedBufferSubDataEXT(int buffer, long offset, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1985:1985 */    long function_pointer = caps.glNamedBufferSubDataEXT;
/* 1986:1986 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1987:1987 */    BufferChecks.checkDirect(data);
/* 1988:1988 */    nglNamedBufferSubDataEXT(buffer, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 1989:     */  }
/* 1990:     */  
/* 1991:1991 */  public static void glNamedBufferSubDataEXT(int buffer, long offset, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1992:1992 */    long function_pointer = caps.glNamedBufferSubDataEXT;
/* 1993:1993 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1994:1994 */    BufferChecks.checkDirect(data);
/* 1995:1995 */    nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 1996:     */  }
/* 1997:     */  
/* 1998:1998 */  public static void glNamedBufferSubDataEXT(int buffer, long offset, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1999:1999 */    long function_pointer = caps.glNamedBufferSubDataEXT;
/* 2000:2000 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2001:2001 */    BufferChecks.checkDirect(data);
/* 2002:2002 */    nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 2003:     */  }
/* 2004:     */  
/* 2005:2005 */  public static void glNamedBufferSubDataEXT(int buffer, long offset, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2006:2006 */    long function_pointer = caps.glNamedBufferSubDataEXT;
/* 2007:2007 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2008:2008 */    BufferChecks.checkDirect(data);
/* 2009:2009 */    nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 2010:     */  }
/* 2011:     */  
/* 2012:2012 */  public static void glNamedBufferSubDataEXT(int buffer, long offset, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2013:2013 */    long function_pointer = caps.glNamedBufferSubDataEXT;
/* 2014:2014 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2015:2015 */    BufferChecks.checkDirect(data);
/* 2016:2016 */    nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 2017:     */  }
/* 2018:     */  
/* 2030:     */  static native void nglNamedBufferSubDataEXT(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 2031:     */  
/* 2042:     */  public static ByteBuffer glMapNamedBufferEXT(int buffer, int access, ByteBuffer old_buffer)
/* 2043:     */  {
/* 2044:2044 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2045:2045 */    long function_pointer = caps.glMapNamedBufferEXT;
/* 2046:2046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2047:2047 */    if (old_buffer != null)
/* 2048:2048 */      BufferChecks.checkDirect(old_buffer);
/* 2049:2049 */    ByteBuffer __result = nglMapNamedBufferEXT(buffer, access, GLChecks.getNamedBufferObjectSize(caps, buffer), old_buffer, function_pointer);
/* 2050:2050 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 2051:     */  }
/* 2052:     */  
/* 2074:     */  public static ByteBuffer glMapNamedBufferEXT(int buffer, int access, long length, ByteBuffer old_buffer)
/* 2075:     */  {
/* 2076:2076 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2077:2077 */    long function_pointer = caps.glMapNamedBufferEXT;
/* 2078:2078 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2079:2079 */    if (old_buffer != null)
/* 2080:2080 */      BufferChecks.checkDirect(old_buffer);
/* 2081:2081 */    ByteBuffer __result = nglMapNamedBufferEXT(buffer, access, length, old_buffer, function_pointer);
/* 2082:2082 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 2083:     */  }
/* 2084:     */  
/* 2085:     */  static native ByteBuffer nglMapNamedBufferEXT(int paramInt1, int paramInt2, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/* 2086:     */  
/* 2087:2087 */  public static boolean glUnmapNamedBufferEXT(int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2088:2088 */    long function_pointer = caps.glUnmapNamedBufferEXT;
/* 2089:2089 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2090:2090 */    boolean __result = nglUnmapNamedBufferEXT(buffer, function_pointer);
/* 2091:2091 */    return __result;
/* 2092:     */  }
/* 2093:     */  
/* 2094:     */  static native boolean nglUnmapNamedBufferEXT(int paramInt, long paramLong);
/* 2095:     */  
/* 2096:2096 */  public static void glGetNamedBufferParameterEXT(int buffer, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2097:2097 */    long function_pointer = caps.glGetNamedBufferParameterivEXT;
/* 2098:2098 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2099:2099 */    BufferChecks.checkBuffer(params, 4);
/* 2100:2100 */    nglGetNamedBufferParameterivEXT(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2101:     */  }
/* 2102:     */  
/* 2103:     */  static native void nglGetNamedBufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2104:     */  
/* 2105:     */  public static int glGetNamedBufferParameterEXT(int buffer, int pname) {
/* 2106:2106 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2107:2107 */    long function_pointer = caps.glGetNamedBufferParameterivEXT;
/* 2108:2108 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2109:2109 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2110:2110 */    nglGetNamedBufferParameterivEXT(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2111:2111 */    return params.get(0);
/* 2112:     */  }
/* 2113:     */  
/* 2114:     */  public static ByteBuffer glGetNamedBufferPointerEXT(int buffer, int pname) {
/* 2115:2115 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2116:2116 */    long function_pointer = caps.glGetNamedBufferPointervEXT;
/* 2117:2117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2118:2118 */    ByteBuffer __result = nglGetNamedBufferPointervEXT(buffer, pname, GLChecks.getNamedBufferObjectSize(caps, buffer), function_pointer);
/* 2119:2119 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 2120:     */  }
/* 2121:     */  
/* 2122:     */  static native ByteBuffer nglGetNamedBufferPointervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2123:     */  
/* 2124:2124 */  public static void glGetNamedBufferSubDataEXT(int buffer, long offset, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2125:2125 */    long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2126:2126 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2127:2127 */    BufferChecks.checkDirect(data);
/* 2128:2128 */    nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 2129:     */  }
/* 2130:     */  
/* 2131:2131 */  public static void glGetNamedBufferSubDataEXT(int buffer, long offset, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2132:2132 */    long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2133:2133 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2134:2134 */    BufferChecks.checkDirect(data);
/* 2135:2135 */    nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 2136:     */  }
/* 2137:     */  
/* 2138:2138 */  public static void glGetNamedBufferSubDataEXT(int buffer, long offset, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2139:2139 */    long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2140:2140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2141:2141 */    BufferChecks.checkDirect(data);
/* 2142:2142 */    nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 2143:     */  }
/* 2144:     */  
/* 2145:2145 */  public static void glGetNamedBufferSubDataEXT(int buffer, long offset, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2146:2146 */    long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2147:2147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2148:2148 */    BufferChecks.checkDirect(data);
/* 2149:2149 */    nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 2150:     */  }
/* 2151:     */  
/* 2152:2152 */  public static void glGetNamedBufferSubDataEXT(int buffer, long offset, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2153:2153 */    long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2154:2154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2155:2155 */    BufferChecks.checkDirect(data);
/* 2156:2156 */    nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 2157:     */  }
/* 2158:     */  
/* 2159:     */  static native void nglGetNamedBufferSubDataEXT(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 2160:     */  
/* 2161:2161 */  public static void glProgramUniform1fEXT(int program, int location, float v0) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2162:2162 */    long function_pointer = caps.glProgramUniform1fEXT;
/* 2163:2163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2164:2164 */    nglProgramUniform1fEXT(program, location, v0, function_pointer);
/* 2165:     */  }
/* 2166:     */  
/* 2167:     */  static native void nglProgramUniform1fEXT(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 2168:     */  
/* 2169:2169 */  public static void glProgramUniform2fEXT(int program, int location, float v0, float v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2170:2170 */    long function_pointer = caps.glProgramUniform2fEXT;
/* 2171:2171 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2172:2172 */    nglProgramUniform2fEXT(program, location, v0, v1, function_pointer);
/* 2173:     */  }
/* 2174:     */  
/* 2175:     */  static native void nglProgramUniform2fEXT(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, long paramLong);
/* 2176:     */  
/* 2177:2177 */  public static void glProgramUniform3fEXT(int program, int location, float v0, float v1, float v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2178:2178 */    long function_pointer = caps.glProgramUniform3fEXT;
/* 2179:2179 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2180:2180 */    nglProgramUniform3fEXT(program, location, v0, v1, v2, function_pointer);
/* 2181:     */  }
/* 2182:     */  
/* 2183:     */  static native void nglProgramUniform3fEXT(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 2184:     */  
/* 2185:2185 */  public static void glProgramUniform4fEXT(int program, int location, float v0, float v1, float v2, float v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2186:2186 */    long function_pointer = caps.glProgramUniform4fEXT;
/* 2187:2187 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2188:2188 */    nglProgramUniform4fEXT(program, location, v0, v1, v2, v3, function_pointer);
/* 2189:     */  }
/* 2190:     */  
/* 2191:     */  static native void nglProgramUniform4fEXT(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 2192:     */  
/* 2193:2193 */  public static void glProgramUniform1iEXT(int program, int location, int v0) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2194:2194 */    long function_pointer = caps.glProgramUniform1iEXT;
/* 2195:2195 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2196:2196 */    nglProgramUniform1iEXT(program, location, v0, function_pointer);
/* 2197:     */  }
/* 2198:     */  
/* 2199:     */  static native void nglProgramUniform1iEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2200:     */  
/* 2201:2201 */  public static void glProgramUniform2iEXT(int program, int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2202:2202 */    long function_pointer = caps.glProgramUniform2iEXT;
/* 2203:2203 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2204:2204 */    nglProgramUniform2iEXT(program, location, v0, v1, function_pointer);
/* 2205:     */  }
/* 2206:     */  
/* 2207:     */  static native void nglProgramUniform2iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2208:     */  
/* 2209:2209 */  public static void glProgramUniform3iEXT(int program, int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2210:2210 */    long function_pointer = caps.glProgramUniform3iEXT;
/* 2211:2211 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2212:2212 */    nglProgramUniform3iEXT(program, location, v0, v1, v2, function_pointer);
/* 2213:     */  }
/* 2214:     */  
/* 2215:     */  static native void nglProgramUniform3iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2216:     */  
/* 2217:2217 */  public static void glProgramUniform4iEXT(int program, int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2218:2218 */    long function_pointer = caps.glProgramUniform4iEXT;
/* 2219:2219 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2220:2220 */    nglProgramUniform4iEXT(program, location, v0, v1, v2, v3, function_pointer);
/* 2221:     */  }
/* 2222:     */  
/* 2223:     */  static native void nglProgramUniform4iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 2224:     */  
/* 2225:2225 */  public static void glProgramUniform1EXT(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2226:2226 */    long function_pointer = caps.glProgramUniform1fvEXT;
/* 2227:2227 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2228:2228 */    BufferChecks.checkDirect(value);
/* 2229:2229 */    nglProgramUniform1fvEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 2230:     */  }
/* 2231:     */  
/* 2232:     */  static native void nglProgramUniform1fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2233:     */  
/* 2234:2234 */  public static void glProgramUniform2EXT(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2235:2235 */    long function_pointer = caps.glProgramUniform2fvEXT;
/* 2236:2236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2237:2237 */    BufferChecks.checkDirect(value);
/* 2238:2238 */    nglProgramUniform2fvEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 2239:     */  }
/* 2240:     */  
/* 2241:     */  static native void nglProgramUniform2fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2242:     */  
/* 2243:2243 */  public static void glProgramUniform3EXT(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2244:2244 */    long function_pointer = caps.glProgramUniform3fvEXT;
/* 2245:2245 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2246:2246 */    BufferChecks.checkDirect(value);
/* 2247:2247 */    nglProgramUniform3fvEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 2248:     */  }
/* 2249:     */  
/* 2250:     */  static native void nglProgramUniform3fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2251:     */  
/* 2252:2252 */  public static void glProgramUniform4EXT(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2253:2253 */    long function_pointer = caps.glProgramUniform4fvEXT;
/* 2254:2254 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2255:2255 */    BufferChecks.checkDirect(value);
/* 2256:2256 */    nglProgramUniform4fvEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 2257:     */  }
/* 2258:     */  
/* 2259:     */  static native void nglProgramUniform4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2260:     */  
/* 2261:2261 */  public static void glProgramUniform1EXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2262:2262 */    long function_pointer = caps.glProgramUniform1ivEXT;
/* 2263:2263 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2264:2264 */    BufferChecks.checkDirect(value);
/* 2265:2265 */    nglProgramUniform1ivEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 2266:     */  }
/* 2267:     */  
/* 2268:     */  static native void nglProgramUniform1ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2269:     */  
/* 2270:2270 */  public static void glProgramUniform2EXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2271:2271 */    long function_pointer = caps.glProgramUniform2ivEXT;
/* 2272:2272 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2273:2273 */    BufferChecks.checkDirect(value);
/* 2274:2274 */    nglProgramUniform2ivEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 2275:     */  }
/* 2276:     */  
/* 2277:     */  static native void nglProgramUniform2ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2278:     */  
/* 2279:2279 */  public static void glProgramUniform3EXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2280:2280 */    long function_pointer = caps.glProgramUniform3ivEXT;
/* 2281:2281 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2282:2282 */    BufferChecks.checkDirect(value);
/* 2283:2283 */    nglProgramUniform3ivEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 2284:     */  }
/* 2285:     */  
/* 2286:     */  static native void nglProgramUniform3ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2287:     */  
/* 2288:2288 */  public static void glProgramUniform4EXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2289:2289 */    long function_pointer = caps.glProgramUniform4ivEXT;
/* 2290:2290 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2291:2291 */    BufferChecks.checkDirect(value);
/* 2292:2292 */    nglProgramUniform4ivEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 2293:     */  }
/* 2294:     */  
/* 2295:     */  static native void nglProgramUniform4ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2296:     */  
/* 2297:2297 */  public static void glProgramUniformMatrix2EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2298:2298 */    long function_pointer = caps.glProgramUniformMatrix2fvEXT;
/* 2299:2299 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2300:2300 */    BufferChecks.checkDirect(value);
/* 2301:2301 */    nglProgramUniformMatrix2fvEXT(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2302:     */  }
/* 2303:     */  
/* 2304:     */  static native void nglProgramUniformMatrix2fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2305:     */  
/* 2306:2306 */  public static void glProgramUniformMatrix3EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2307:2307 */    long function_pointer = caps.glProgramUniformMatrix3fvEXT;
/* 2308:2308 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2309:2309 */    BufferChecks.checkDirect(value);
/* 2310:2310 */    nglProgramUniformMatrix3fvEXT(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2311:     */  }
/* 2312:     */  
/* 2313:     */  static native void nglProgramUniformMatrix3fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2314:     */  
/* 2315:2315 */  public static void glProgramUniformMatrix4EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2316:2316 */    long function_pointer = caps.glProgramUniformMatrix4fvEXT;
/* 2317:2317 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2318:2318 */    BufferChecks.checkDirect(value);
/* 2319:2319 */    nglProgramUniformMatrix4fvEXT(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2320:     */  }
/* 2321:     */  
/* 2322:     */  static native void nglProgramUniformMatrix4fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2323:     */  
/* 2324:2324 */  public static void glProgramUniformMatrix2x3EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2325:2325 */    long function_pointer = caps.glProgramUniformMatrix2x3fvEXT;
/* 2326:2326 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2327:2327 */    BufferChecks.checkDirect(value);
/* 2328:2328 */    nglProgramUniformMatrix2x3fvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2329:     */  }
/* 2330:     */  
/* 2331:     */  static native void nglProgramUniformMatrix2x3fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2332:     */  
/* 2333:2333 */  public static void glProgramUniformMatrix3x2EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2334:2334 */    long function_pointer = caps.glProgramUniformMatrix3x2fvEXT;
/* 2335:2335 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2336:2336 */    BufferChecks.checkDirect(value);
/* 2337:2337 */    nglProgramUniformMatrix3x2fvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2338:     */  }
/* 2339:     */  
/* 2340:     */  static native void nglProgramUniformMatrix3x2fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2341:     */  
/* 2342:2342 */  public static void glProgramUniformMatrix2x4EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2343:2343 */    long function_pointer = caps.glProgramUniformMatrix2x4fvEXT;
/* 2344:2344 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2345:2345 */    BufferChecks.checkDirect(value);
/* 2346:2346 */    nglProgramUniformMatrix2x4fvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2347:     */  }
/* 2348:     */  
/* 2349:     */  static native void nglProgramUniformMatrix2x4fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2350:     */  
/* 2351:2351 */  public static void glProgramUniformMatrix4x2EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2352:2352 */    long function_pointer = caps.glProgramUniformMatrix4x2fvEXT;
/* 2353:2353 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2354:2354 */    BufferChecks.checkDirect(value);
/* 2355:2355 */    nglProgramUniformMatrix4x2fvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2356:     */  }
/* 2357:     */  
/* 2358:     */  static native void nglProgramUniformMatrix4x2fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2359:     */  
/* 2360:2360 */  public static void glProgramUniformMatrix3x4EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2361:2361 */    long function_pointer = caps.glProgramUniformMatrix3x4fvEXT;
/* 2362:2362 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2363:2363 */    BufferChecks.checkDirect(value);
/* 2364:2364 */    nglProgramUniformMatrix3x4fvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2365:     */  }
/* 2366:     */  
/* 2367:     */  static native void nglProgramUniformMatrix3x4fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2368:     */  
/* 2369:2369 */  public static void glProgramUniformMatrix4x3EXT(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2370:2370 */    long function_pointer = caps.glProgramUniformMatrix4x3fvEXT;
/* 2371:2371 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2372:2372 */    BufferChecks.checkDirect(value);
/* 2373:2373 */    nglProgramUniformMatrix4x3fvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/* 2374:     */  }
/* 2375:     */  
/* 2376:     */  static native void nglProgramUniformMatrix4x3fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/* 2377:     */  
/* 2378:2378 */  public static void glTextureBufferEXT(int texture, int target, int internalformat, int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2379:2379 */    long function_pointer = caps.glTextureBufferEXT;
/* 2380:2380 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2381:2381 */    nglTextureBufferEXT(texture, target, internalformat, buffer, function_pointer);
/* 2382:     */  }
/* 2383:     */  
/* 2384:     */  static native void nglTextureBufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2385:     */  
/* 2386:2386 */  public static void glMultiTexBufferEXT(int texunit, int target, int internalformat, int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2387:2387 */    long function_pointer = caps.glMultiTexBufferEXT;
/* 2388:2388 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2389:2389 */    nglMultiTexBufferEXT(texunit, target, internalformat, buffer, function_pointer);
/* 2390:     */  }
/* 2391:     */  
/* 2392:     */  static native void nglMultiTexBufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2393:     */  
/* 2394:2394 */  public static void glTextureParameterIEXT(int texture, int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2395:2395 */    long function_pointer = caps.glTextureParameterIivEXT;
/* 2396:2396 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2397:2397 */    BufferChecks.checkBuffer(params, 4);
/* 2398:2398 */    nglTextureParameterIivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2399:     */  }
/* 2400:     */  
/* 2401:     */  static native void nglTextureParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2402:     */  
/* 2403:     */  public static void glTextureParameterIEXT(int texture, int target, int pname, int param) {
/* 2404:2404 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2405:2405 */    long function_pointer = caps.glTextureParameterIivEXT;
/* 2406:2406 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2407:2407 */    nglTextureParameterIivEXT(texture, target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 2408:     */  }
/* 2409:     */  
/* 2410:     */  public static void glTextureParameterIuEXT(int texture, int target, int pname, IntBuffer params) {
/* 2411:2411 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2412:2412 */    long function_pointer = caps.glTextureParameterIuivEXT;
/* 2413:2413 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2414:2414 */    BufferChecks.checkBuffer(params, 4);
/* 2415:2415 */    nglTextureParameterIuivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2416:     */  }
/* 2417:     */  
/* 2418:     */  static native void nglTextureParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2419:     */  
/* 2420:     */  public static void glTextureParameterIuEXT(int texture, int target, int pname, int param) {
/* 2421:2421 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2422:2422 */    long function_pointer = caps.glTextureParameterIuivEXT;
/* 2423:2423 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2424:2424 */    nglTextureParameterIuivEXT(texture, target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 2425:     */  }
/* 2426:     */  
/* 2427:     */  public static void glGetTextureParameterIEXT(int texture, int target, int pname, IntBuffer params) {
/* 2428:2428 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2429:2429 */    long function_pointer = caps.glGetTextureParameterIivEXT;
/* 2430:2430 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2431:2431 */    BufferChecks.checkBuffer(params, 4);
/* 2432:2432 */    nglGetTextureParameterIivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2433:     */  }
/* 2434:     */  
/* 2435:     */  static native void nglGetTextureParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2436:     */  
/* 2437:     */  public static int glGetTextureParameterIiEXT(int texture, int target, int pname) {
/* 2438:2438 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2439:2439 */    long function_pointer = caps.glGetTextureParameterIivEXT;
/* 2440:2440 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2441:2441 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2442:2442 */    nglGetTextureParameterIivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2443:2443 */    return params.get(0);
/* 2444:     */  }
/* 2445:     */  
/* 2446:     */  public static void glGetTextureParameterIuEXT(int texture, int target, int pname, IntBuffer params) {
/* 2447:2447 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2448:2448 */    long function_pointer = caps.glGetTextureParameterIuivEXT;
/* 2449:2449 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2450:2450 */    BufferChecks.checkBuffer(params, 4);
/* 2451:2451 */    nglGetTextureParameterIuivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2452:     */  }
/* 2453:     */  
/* 2454:     */  static native void nglGetTextureParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2455:     */  
/* 2456:     */  public static int glGetTextureParameterIuiEXT(int texture, int target, int pname) {
/* 2457:2457 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2458:2458 */    long function_pointer = caps.glGetTextureParameterIuivEXT;
/* 2459:2459 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2460:2460 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2461:2461 */    nglGetTextureParameterIuivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2462:2462 */    return params.get(0);
/* 2463:     */  }
/* 2464:     */  
/* 2465:     */  public static void glMultiTexParameterIEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2466:2466 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2467:2467 */    long function_pointer = caps.glMultiTexParameterIivEXT;
/* 2468:2468 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2469:2469 */    BufferChecks.checkBuffer(params, 4);
/* 2470:2470 */    nglMultiTexParameterIivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2471:     */  }
/* 2472:     */  
/* 2473:     */  static native void nglMultiTexParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2474:     */  
/* 2475:     */  public static void glMultiTexParameterIEXT(int texunit, int target, int pname, int param) {
/* 2476:2476 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2477:2477 */    long function_pointer = caps.glMultiTexParameterIivEXT;
/* 2478:2478 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2479:2479 */    nglMultiTexParameterIivEXT(texunit, target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 2480:     */  }
/* 2481:     */  
/* 2482:     */  public static void glMultiTexParameterIuEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2483:2483 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2484:2484 */    long function_pointer = caps.glMultiTexParameterIuivEXT;
/* 2485:2485 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2486:2486 */    BufferChecks.checkBuffer(params, 4);
/* 2487:2487 */    nglMultiTexParameterIuivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2488:     */  }
/* 2489:     */  
/* 2490:     */  static native void nglMultiTexParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2491:     */  
/* 2492:     */  public static void glMultiTexParameterIuEXT(int texunit, int target, int pname, int param) {
/* 2493:2493 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2494:2494 */    long function_pointer = caps.glMultiTexParameterIuivEXT;
/* 2495:2495 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2496:2496 */    nglMultiTexParameterIuivEXT(texunit, target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 2497:     */  }
/* 2498:     */  
/* 2499:     */  public static void glGetMultiTexParameterIEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2500:2500 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2501:2501 */    long function_pointer = caps.glGetMultiTexParameterIivEXT;
/* 2502:2502 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2503:2503 */    BufferChecks.checkBuffer(params, 4);
/* 2504:2504 */    nglGetMultiTexParameterIivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2505:     */  }
/* 2506:     */  
/* 2507:     */  static native void nglGetMultiTexParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2508:     */  
/* 2509:     */  public static int glGetMultiTexParameterIiEXT(int texunit, int target, int pname) {
/* 2510:2510 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2511:2511 */    long function_pointer = caps.glGetMultiTexParameterIivEXT;
/* 2512:2512 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2513:2513 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2514:2514 */    nglGetMultiTexParameterIivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2515:2515 */    return params.get(0);
/* 2516:     */  }
/* 2517:     */  
/* 2518:     */  public static void glGetMultiTexParameterIuEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2519:2519 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2520:2520 */    long function_pointer = caps.glGetMultiTexParameterIuivEXT;
/* 2521:2521 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2522:2522 */    BufferChecks.checkBuffer(params, 4);
/* 2523:2523 */    nglGetMultiTexParameterIuivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2524:     */  }
/* 2525:     */  
/* 2526:     */  static native void nglGetMultiTexParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2527:     */  
/* 2528:     */  public static int glGetMultiTexParameterIuiEXT(int texunit, int target, int pname) {
/* 2529:2529 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2530:2530 */    long function_pointer = caps.glGetMultiTexParameterIuivEXT;
/* 2531:2531 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2532:2532 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2533:2533 */    nglGetMultiTexParameterIuivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2534:2534 */    return params.get(0);
/* 2535:     */  }
/* 2536:     */  
/* 2537:     */  public static void glProgramUniform1uiEXT(int program, int location, int v0) {
/* 2538:2538 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2539:2539 */    long function_pointer = caps.glProgramUniform1uiEXT;
/* 2540:2540 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2541:2541 */    nglProgramUniform1uiEXT(program, location, v0, function_pointer);
/* 2542:     */  }
/* 2543:     */  
/* 2544:     */  static native void nglProgramUniform1uiEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2545:     */  
/* 2546:2546 */  public static void glProgramUniform2uiEXT(int program, int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2547:2547 */    long function_pointer = caps.glProgramUniform2uiEXT;
/* 2548:2548 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2549:2549 */    nglProgramUniform2uiEXT(program, location, v0, v1, function_pointer);
/* 2550:     */  }
/* 2551:     */  
/* 2552:     */  static native void nglProgramUniform2uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2553:     */  
/* 2554:2554 */  public static void glProgramUniform3uiEXT(int program, int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2555:2555 */    long function_pointer = caps.glProgramUniform3uiEXT;
/* 2556:2556 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2557:2557 */    nglProgramUniform3uiEXT(program, location, v0, v1, v2, function_pointer);
/* 2558:     */  }
/* 2559:     */  
/* 2560:     */  static native void nglProgramUniform3uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2561:     */  
/* 2562:2562 */  public static void glProgramUniform4uiEXT(int program, int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2563:2563 */    long function_pointer = caps.glProgramUniform4uiEXT;
/* 2564:2564 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2565:2565 */    nglProgramUniform4uiEXT(program, location, v0, v1, v2, v3, function_pointer);
/* 2566:     */  }
/* 2567:     */  
/* 2568:     */  static native void nglProgramUniform4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 2569:     */  
/* 2570:2570 */  public static void glProgramUniform1uEXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2571:2571 */    long function_pointer = caps.glProgramUniform1uivEXT;
/* 2572:2572 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2573:2573 */    BufferChecks.checkDirect(value);
/* 2574:2574 */    nglProgramUniform1uivEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/* 2575:     */  }
/* 2576:     */  
/* 2577:     */  static native void nglProgramUniform1uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2578:     */  
/* 2579:2579 */  public static void glProgramUniform2uEXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2580:2580 */    long function_pointer = caps.glProgramUniform2uivEXT;
/* 2581:2581 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2582:2582 */    BufferChecks.checkDirect(value);
/* 2583:2583 */    nglProgramUniform2uivEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/* 2584:     */  }
/* 2585:     */  
/* 2586:     */  static native void nglProgramUniform2uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2587:     */  
/* 2588:2588 */  public static void glProgramUniform3uEXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2589:2589 */    long function_pointer = caps.glProgramUniform3uivEXT;
/* 2590:2590 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2591:2591 */    BufferChecks.checkDirect(value);
/* 2592:2592 */    nglProgramUniform3uivEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/* 2593:     */  }
/* 2594:     */  
/* 2595:     */  static native void nglProgramUniform3uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2596:     */  
/* 2597:2597 */  public static void glProgramUniform4uEXT(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2598:2598 */    long function_pointer = caps.glProgramUniform4uivEXT;
/* 2599:2599 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2600:2600 */    BufferChecks.checkDirect(value);
/* 2601:2601 */    nglProgramUniform4uivEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/* 2602:     */  }
/* 2603:     */  
/* 2604:     */  static native void nglProgramUniform4uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2605:     */  
/* 2606:2606 */  public static void glNamedProgramLocalParameters4EXT(int program, int target, int index, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2607:2607 */    long function_pointer = caps.glNamedProgramLocalParameters4fvEXT;
/* 2608:2608 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2609:2609 */    BufferChecks.checkDirect(params);
/* 2610:2610 */    nglNamedProgramLocalParameters4fvEXT(program, target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 2611:     */  }
/* 2612:     */  
/* 2613:     */  static native void nglNamedProgramLocalParameters4fvEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 2614:     */  
/* 2615:2615 */  public static void glNamedProgramLocalParameterI4iEXT(int program, int target, int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2616:2616 */    long function_pointer = caps.glNamedProgramLocalParameterI4iEXT;
/* 2617:2617 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2618:2618 */    nglNamedProgramLocalParameterI4iEXT(program, target, index, x, y, z, w, function_pointer);
/* 2619:     */  }
/* 2620:     */  
/* 2621:     */  static native void nglNamedProgramLocalParameterI4iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/* 2622:     */  
/* 2623:2623 */  public static void glNamedProgramLocalParameterI4EXT(int program, int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2624:2624 */    long function_pointer = caps.glNamedProgramLocalParameterI4ivEXT;
/* 2625:2625 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2626:2626 */    BufferChecks.checkBuffer(params, 4);
/* 2627:2627 */    nglNamedProgramLocalParameterI4ivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 2628:     */  }
/* 2629:     */  
/* 2630:     */  static native void nglNamedProgramLocalParameterI4ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2631:     */  
/* 2632:2632 */  public static void glNamedProgramLocalParametersI4EXT(int program, int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2633:2633 */    long function_pointer = caps.glNamedProgramLocalParametersI4ivEXT;
/* 2634:2634 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2635:2635 */    BufferChecks.checkDirect(params);
/* 2636:2636 */    nglNamedProgramLocalParametersI4ivEXT(program, target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 2637:     */  }
/* 2638:     */  
/* 2639:     */  static native void nglNamedProgramLocalParametersI4ivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 2640:     */  
/* 2641:2641 */  public static void glNamedProgramLocalParameterI4uiEXT(int program, int target, int index, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2642:2642 */    long function_pointer = caps.glNamedProgramLocalParameterI4uiEXT;
/* 2643:2643 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2644:2644 */    nglNamedProgramLocalParameterI4uiEXT(program, target, index, x, y, z, w, function_pointer);
/* 2645:     */  }
/* 2646:     */  
/* 2647:     */  static native void nglNamedProgramLocalParameterI4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/* 2648:     */  
/* 2649:2649 */  public static void glNamedProgramLocalParameterI4uEXT(int program, int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2650:2650 */    long function_pointer = caps.glNamedProgramLocalParameterI4uivEXT;
/* 2651:2651 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2652:2652 */    BufferChecks.checkBuffer(params, 4);
/* 2653:2653 */    nglNamedProgramLocalParameterI4uivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 2654:     */  }
/* 2655:     */  
/* 2656:     */  static native void nglNamedProgramLocalParameterI4uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2657:     */  
/* 2658:2658 */  public static void glNamedProgramLocalParametersI4uEXT(int program, int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2659:2659 */    long function_pointer = caps.glNamedProgramLocalParametersI4uivEXT;
/* 2660:2660 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2661:2661 */    BufferChecks.checkDirect(params);
/* 2662:2662 */    nglNamedProgramLocalParametersI4uivEXT(program, target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 2663:     */  }
/* 2664:     */  
/* 2665:     */  static native void nglNamedProgramLocalParametersI4uivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 2666:     */  
/* 2667:2667 */  public static void glGetNamedProgramLocalParameterIEXT(int program, int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2668:2668 */    long function_pointer = caps.glGetNamedProgramLocalParameterIivEXT;
/* 2669:2669 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2670:2670 */    BufferChecks.checkBuffer(params, 4);
/* 2671:2671 */    nglGetNamedProgramLocalParameterIivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 2672:     */  }
/* 2673:     */  
/* 2674:     */  static native void nglGetNamedProgramLocalParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2675:     */  
/* 2676:2676 */  public static void glGetNamedProgramLocalParameterIuEXT(int program, int target, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2677:2677 */    long function_pointer = caps.glGetNamedProgramLocalParameterIuivEXT;
/* 2678:2678 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2679:2679 */    BufferChecks.checkBuffer(params, 4);
/* 2680:2680 */    nglGetNamedProgramLocalParameterIuivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/* 2681:     */  }
/* 2682:     */  
/* 2683:     */  static native void nglGetNamedProgramLocalParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2684:     */  
/* 2685:2685 */  public static void glNamedRenderbufferStorageEXT(int renderbuffer, int internalformat, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2686:2686 */    long function_pointer = caps.glNamedRenderbufferStorageEXT;
/* 2687:2687 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2688:2688 */    nglNamedRenderbufferStorageEXT(renderbuffer, internalformat, width, height, function_pointer);
/* 2689:     */  }
/* 2690:     */  
/* 2691:     */  static native void nglNamedRenderbufferStorageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2692:     */  
/* 2693:2693 */  public static void glGetNamedRenderbufferParameterEXT(int renderbuffer, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2694:2694 */    long function_pointer = caps.glGetNamedRenderbufferParameterivEXT;
/* 2695:2695 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2696:2696 */    BufferChecks.checkBuffer(params, 4);
/* 2697:2697 */    nglGetNamedRenderbufferParameterivEXT(renderbuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2698:     */  }
/* 2699:     */  
/* 2700:     */  static native void nglGetNamedRenderbufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2701:     */  
/* 2702:     */  public static int glGetNamedRenderbufferParameterEXT(int renderbuffer, int pname) {
/* 2703:2703 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2704:2704 */    long function_pointer = caps.glGetNamedRenderbufferParameterivEXT;
/* 2705:2705 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2706:2706 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2707:2707 */    nglGetNamedRenderbufferParameterivEXT(renderbuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2708:2708 */    return params.get(0);
/* 2709:     */  }
/* 2710:     */  
/* 2711:     */  public static void glNamedRenderbufferStorageMultisampleEXT(int renderbuffer, int samples, int internalformat, int width, int height) {
/* 2712:2712 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2713:2713 */    long function_pointer = caps.glNamedRenderbufferStorageMultisampleEXT;
/* 2714:2714 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2715:2715 */    nglNamedRenderbufferStorageMultisampleEXT(renderbuffer, samples, internalformat, width, height, function_pointer);
/* 2716:     */  }
/* 2717:     */  
/* 2718:     */  static native void nglNamedRenderbufferStorageMultisampleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2719:     */  
/* 2720:2720 */  public static void glNamedRenderbufferStorageMultisampleCoverageEXT(int renderbuffer, int coverageSamples, int colorSamples, int internalformat, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2721:2721 */    long function_pointer = caps.glNamedRenderbufferStorageMultisampleCoverageEXT;
/* 2722:2722 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2723:2723 */    nglNamedRenderbufferStorageMultisampleCoverageEXT(renderbuffer, coverageSamples, colorSamples, internalformat, width, height, function_pointer);
/* 2724:     */  }
/* 2725:     */  
/* 2726:     */  static native void nglNamedRenderbufferStorageMultisampleCoverageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 2727:     */  
/* 2728:2728 */  public static int glCheckNamedFramebufferStatusEXT(int framebuffer, int target) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2729:2729 */    long function_pointer = caps.glCheckNamedFramebufferStatusEXT;
/* 2730:2730 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2731:2731 */    int __result = nglCheckNamedFramebufferStatusEXT(framebuffer, target, function_pointer);
/* 2732:2732 */    return __result;
/* 2733:     */  }
/* 2734:     */  
/* 2735:     */  static native int nglCheckNamedFramebufferStatusEXT(int paramInt1, int paramInt2, long paramLong);
/* 2736:     */  
/* 2737:2737 */  public static void glNamedFramebufferTexture1DEXT(int framebuffer, int attachment, int textarget, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2738:2738 */    long function_pointer = caps.glNamedFramebufferTexture1DEXT;
/* 2739:2739 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2740:2740 */    nglNamedFramebufferTexture1DEXT(framebuffer, attachment, textarget, texture, level, function_pointer);
/* 2741:     */  }
/* 2742:     */  
/* 2743:     */  static native void nglNamedFramebufferTexture1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2744:     */  
/* 2745:2745 */  public static void glNamedFramebufferTexture2DEXT(int framebuffer, int attachment, int textarget, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2746:2746 */    long function_pointer = caps.glNamedFramebufferTexture2DEXT;
/* 2747:2747 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2748:2748 */    nglNamedFramebufferTexture2DEXT(framebuffer, attachment, textarget, texture, level, function_pointer);
/* 2749:     */  }
/* 2750:     */  
/* 2751:     */  static native void nglNamedFramebufferTexture2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2752:     */  
/* 2753:2753 */  public static void glNamedFramebufferTexture3DEXT(int framebuffer, int attachment, int textarget, int texture, int level, int zoffset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2754:2754 */    long function_pointer = caps.glNamedFramebufferTexture3DEXT;
/* 2755:2755 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2756:2756 */    nglNamedFramebufferTexture3DEXT(framebuffer, attachment, textarget, texture, level, zoffset, function_pointer);
/* 2757:     */  }
/* 2758:     */  
/* 2759:     */  static native void nglNamedFramebufferTexture3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 2760:     */  
/* 2761:2761 */  public static void glNamedFramebufferRenderbufferEXT(int framebuffer, int attachment, int renderbuffertarget, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2762:2762 */    long function_pointer = caps.glNamedFramebufferRenderbufferEXT;
/* 2763:2763 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2764:2764 */    nglNamedFramebufferRenderbufferEXT(framebuffer, attachment, renderbuffertarget, renderbuffer, function_pointer);
/* 2765:     */  }
/* 2766:     */  
/* 2767:     */  static native void nglNamedFramebufferRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2768:     */  
/* 2769:2769 */  public static void glGetNamedFramebufferAttachmentParameterEXT(int framebuffer, int attachment, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2770:2770 */    long function_pointer = caps.glGetNamedFramebufferAttachmentParameterivEXT;
/* 2771:2771 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2772:2772 */    BufferChecks.checkBuffer(params, 4);
/* 2773:2773 */    nglGetNamedFramebufferAttachmentParameterivEXT(framebuffer, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2774:     */  }
/* 2775:     */  
/* 2776:     */  static native void nglGetNamedFramebufferAttachmentParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2777:     */  
/* 2778:     */  public static int glGetNamedFramebufferAttachmentParameterEXT(int framebuffer, int attachment, int pname) {
/* 2779:2779 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2780:2780 */    long function_pointer = caps.glGetNamedFramebufferAttachmentParameterivEXT;
/* 2781:2781 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2782:2782 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 2783:2783 */    nglGetNamedFramebufferAttachmentParameterivEXT(framebuffer, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2784:2784 */    return params.get(0);
/* 2785:     */  }
/* 2786:     */  
/* 2787:     */  public static void glGenerateTextureMipmapEXT(int texture, int target) {
/* 2788:2788 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2789:2789 */    long function_pointer = caps.glGenerateTextureMipmapEXT;
/* 2790:2790 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2791:2791 */    nglGenerateTextureMipmapEXT(texture, target, function_pointer);
/* 2792:     */  }
/* 2793:     */  
/* 2794:     */  static native void nglGenerateTextureMipmapEXT(int paramInt1, int paramInt2, long paramLong);
/* 2795:     */  
/* 2796:2796 */  public static void glGenerateMultiTexMipmapEXT(int texunit, int target) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2797:2797 */    long function_pointer = caps.glGenerateMultiTexMipmapEXT;
/* 2798:2798 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2799:2799 */    nglGenerateMultiTexMipmapEXT(texunit, target, function_pointer);
/* 2800:     */  }
/* 2801:     */  
/* 2802:     */  static native void nglGenerateMultiTexMipmapEXT(int paramInt1, int paramInt2, long paramLong);
/* 2803:     */  
/* 2804:2804 */  public static void glFramebufferDrawBufferEXT(int framebuffer, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2805:2805 */    long function_pointer = caps.glFramebufferDrawBufferEXT;
/* 2806:2806 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2807:2807 */    nglFramebufferDrawBufferEXT(framebuffer, mode, function_pointer);
/* 2808:     */  }
/* 2809:     */  
/* 2810:     */  static native void nglFramebufferDrawBufferEXT(int paramInt1, int paramInt2, long paramLong);
/* 2811:     */  
/* 2812:2812 */  public static void glFramebufferDrawBuffersEXT(int framebuffer, IntBuffer bufs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2813:2813 */    long function_pointer = caps.glFramebufferDrawBuffersEXT;
/* 2814:2814 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2815:2815 */    BufferChecks.checkDirect(bufs);
/* 2816:2816 */    nglFramebufferDrawBuffersEXT(framebuffer, bufs.remaining(), MemoryUtil.getAddress(bufs), function_pointer);
/* 2817:     */  }
/* 2818:     */  
/* 2819:     */  static native void nglFramebufferDrawBuffersEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2820:     */  
/* 2821:2821 */  public static void glFramebufferReadBufferEXT(int framebuffer, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2822:2822 */    long function_pointer = caps.glFramebufferReadBufferEXT;
/* 2823:2823 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2824:2824 */    nglFramebufferReadBufferEXT(framebuffer, mode, function_pointer);
/* 2825:     */  }
/* 2826:     */  
/* 2827:     */  static native void nglFramebufferReadBufferEXT(int paramInt1, int paramInt2, long paramLong);
/* 2828:     */  
/* 2829:2829 */  public static void glGetFramebufferParameterEXT(int framebuffer, int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2830:2830 */    long function_pointer = caps.glGetFramebufferParameterivEXT;
/* 2831:2831 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2832:2832 */    BufferChecks.checkBuffer(param, 4);
/* 2833:2833 */    nglGetFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(param), function_pointer);
/* 2834:     */  }
/* 2835:     */  
/* 2836:     */  static native void nglGetFramebufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 2837:     */  
/* 2838:     */  public static int glGetFramebufferParameterEXT(int framebuffer, int pname) {
/* 2839:2839 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2840:2840 */    long function_pointer = caps.glGetFramebufferParameterivEXT;
/* 2841:2841 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2842:2842 */    IntBuffer param = APIUtil.getBufferInt(caps);
/* 2843:2843 */    nglGetFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(param), function_pointer);
/* 2844:2844 */    return param.get(0);
/* 2845:     */  }
/* 2846:     */  
/* 2847:     */  public static void glNamedCopyBufferSubDataEXT(int readBuffer, int writeBuffer, long readoffset, long writeoffset, long size) {
/* 2848:2848 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 2849:2849 */    long function_pointer = caps.glNamedCopyBufferSubDataEXT;
/* 2850:2850 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2851:2851 */    nglNamedCopyBufferSubDataEXT(readBuffer, writeBuffer, readoffset, writeoffset, size, function_pointer);
/* 2852:     */  }
/* 2853:     */  
/* 2854:     */  static native void nglNamedCopyBufferSubDataEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 2855:     */  
/* 2856:2856 */  public static void glNamedFramebufferTextureEXT(int framebuffer, int attachment, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2857:2857 */    long function_pointer = caps.glNamedFramebufferTextureEXT;
/* 2858:2858 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2859:2859 */    nglNamedFramebufferTextureEXT(framebuffer, attachment, texture, level, function_pointer);
/* 2860:     */  }
/* 2861:     */  
/* 2862:     */  static native void nglNamedFramebufferTextureEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 2863:     */  
/* 2864:2864 */  public static void glNamedFramebufferTextureLayerEXT(int framebuffer, int attachment, int texture, int level, int layer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2865:2865 */    long function_pointer = caps.glNamedFramebufferTextureLayerEXT;
/* 2866:2866 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2867:2867 */    nglNamedFramebufferTextureLayerEXT(framebuffer, attachment, texture, level, layer, function_pointer);
/* 2868:     */  }
/* 2869:     */  
/* 2870:     */  static native void nglNamedFramebufferTextureLayerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2871:     */  
/* 2872:2872 */  public static void glNamedFramebufferTextureFaceEXT(int framebuffer, int attachment, int texture, int level, int face) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2873:2873 */    long function_pointer = caps.glNamedFramebufferTextureFaceEXT;
/* 2874:2874 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2875:2875 */    nglNamedFramebufferTextureFaceEXT(framebuffer, attachment, texture, level, face, function_pointer);
/* 2876:     */  }
/* 2877:     */  
/* 2878:     */  static native void nglNamedFramebufferTextureFaceEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 2879:     */  
/* 2880:2880 */  public static void glTextureRenderbufferEXT(int texture, int target, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2881:2881 */    long function_pointer = caps.glTextureRenderbufferEXT;
/* 2882:2882 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2883:2883 */    nglTextureRenderbufferEXT(texture, target, renderbuffer, function_pointer);
/* 2884:     */  }
/* 2885:     */  
/* 2886:     */  static native void nglTextureRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2887:     */  
/* 2888:2888 */  public static void glMultiTexRenderbufferEXT(int texunit, int target, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2889:2889 */    long function_pointer = caps.glMultiTexRenderbufferEXT;
/* 2890:2890 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2891:2891 */    nglMultiTexRenderbufferEXT(texunit, target, renderbuffer, function_pointer);
/* 2892:     */  }
/* 2893:     */  
/* 2894:     */  static native void nglMultiTexRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 2895:     */  
/* 2896:2896 */  public static void glVertexArrayVertexOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2897:2897 */    long function_pointer = caps.glVertexArrayVertexOffsetEXT;
/* 2898:2898 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2899:2899 */    nglVertexArrayVertexOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/* 2900:     */  }
/* 2901:     */  
/* 2902:     */  static native void nglVertexArrayVertexOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 2903:     */  
/* 2904:2904 */  public static void glVertexArrayColorOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2905:2905 */    long function_pointer = caps.glVertexArrayColorOffsetEXT;
/* 2906:2906 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2907:2907 */    nglVertexArrayColorOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/* 2908:     */  }
/* 2909:     */  
/* 2910:     */  static native void nglVertexArrayColorOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 2911:     */  
/* 2912:2912 */  public static void glVertexArrayEdgeFlagOffsetEXT(int vaobj, int buffer, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2913:2913 */    long function_pointer = caps.glVertexArrayEdgeFlagOffsetEXT;
/* 2914:2914 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2915:2915 */    nglVertexArrayEdgeFlagOffsetEXT(vaobj, buffer, stride, offset, function_pointer);
/* 2916:     */  }
/* 2917:     */  
/* 2918:     */  static native void nglVertexArrayEdgeFlagOffsetEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 2919:     */  
/* 2920:2920 */  public static void glVertexArrayIndexOffsetEXT(int vaobj, int buffer, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2921:2921 */    long function_pointer = caps.glVertexArrayIndexOffsetEXT;
/* 2922:2922 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2923:2923 */    nglVertexArrayIndexOffsetEXT(vaobj, buffer, type, stride, offset, function_pointer);
/* 2924:     */  }
/* 2925:     */  
/* 2926:     */  static native void nglVertexArrayIndexOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 2927:     */  
/* 2928:2928 */  public static void glVertexArrayNormalOffsetEXT(int vaobj, int buffer, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2929:2929 */    long function_pointer = caps.glVertexArrayNormalOffsetEXT;
/* 2930:2930 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2931:2931 */    nglVertexArrayNormalOffsetEXT(vaobj, buffer, type, stride, offset, function_pointer);
/* 2932:     */  }
/* 2933:     */  
/* 2934:     */  static native void nglVertexArrayNormalOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 2935:     */  
/* 2936:2936 */  public static void glVertexArrayTexCoordOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2937:2937 */    long function_pointer = caps.glVertexArrayTexCoordOffsetEXT;
/* 2938:2938 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2939:2939 */    nglVertexArrayTexCoordOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/* 2940:     */  }
/* 2941:     */  
/* 2942:     */  static native void nglVertexArrayTexCoordOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 2943:     */  
/* 2944:2944 */  public static void glVertexArrayMultiTexCoordOffsetEXT(int vaobj, int buffer, int texunit, int size, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2945:2945 */    long function_pointer = caps.glVertexArrayMultiTexCoordOffsetEXT;
/* 2946:2946 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2947:2947 */    nglVertexArrayMultiTexCoordOffsetEXT(vaobj, buffer, texunit, size, type, stride, offset, function_pointer);
/* 2948:     */  }
/* 2949:     */  
/* 2950:     */  static native void nglVertexArrayMultiTexCoordOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 2951:     */  
/* 2952:2952 */  public static void glVertexArrayFogCoordOffsetEXT(int vaobj, int buffer, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2953:2953 */    long function_pointer = caps.glVertexArrayFogCoordOffsetEXT;
/* 2954:2954 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2955:2955 */    nglVertexArrayFogCoordOffsetEXT(vaobj, buffer, type, stride, offset, function_pointer);
/* 2956:     */  }
/* 2957:     */  
/* 2958:     */  static native void nglVertexArrayFogCoordOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 2959:     */  
/* 2960:2960 */  public static void glVertexArraySecondaryColorOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2961:2961 */    long function_pointer = caps.glVertexArraySecondaryColorOffsetEXT;
/* 2962:2962 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2963:2963 */    nglVertexArraySecondaryColorOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/* 2964:     */  }
/* 2965:     */  
/* 2966:     */  static native void nglVertexArraySecondaryColorOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 2967:     */  
/* 2968:2968 */  public static void glVertexArrayVertexAttribOffsetEXT(int vaobj, int buffer, int index, int size, int type, boolean normalized, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2969:2969 */    long function_pointer = caps.glVertexArrayVertexAttribOffsetEXT;
/* 2970:2970 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2971:2971 */    nglVertexArrayVertexAttribOffsetEXT(vaobj, buffer, index, size, type, normalized, stride, offset, function_pointer);
/* 2972:     */  }
/* 2973:     */  
/* 2974:     */  static native void nglVertexArrayVertexAttribOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, int paramInt6, long paramLong1, long paramLong2);
/* 2975:     */  
/* 2976:2976 */  public static void glVertexArrayVertexAttribIOffsetEXT(int vaobj, int buffer, int index, int size, int type, int stride, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2977:2977 */    long function_pointer = caps.glVertexArrayVertexAttribIOffsetEXT;
/* 2978:2978 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2979:2979 */    nglVertexArrayVertexAttribIOffsetEXT(vaobj, buffer, index, size, type, stride, offset, function_pointer);
/* 2980:     */  }
/* 2981:     */  
/* 2982:     */  static native void nglVertexArrayVertexAttribIOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/* 2983:     */  
/* 2984:2984 */  public static void glEnableVertexArrayEXT(int vaobj, int array) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2985:2985 */    long function_pointer = caps.glEnableVertexArrayEXT;
/* 2986:2986 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2987:2987 */    nglEnableVertexArrayEXT(vaobj, array, function_pointer);
/* 2988:     */  }
/* 2989:     */  
/* 2990:     */  static native void nglEnableVertexArrayEXT(int paramInt1, int paramInt2, long paramLong);
/* 2991:     */  
/* 2992:2992 */  public static void glDisableVertexArrayEXT(int vaobj, int array) { ContextCapabilities caps = GLContext.getCapabilities();
/* 2993:2993 */    long function_pointer = caps.glDisableVertexArrayEXT;
/* 2994:2994 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 2995:2995 */    nglDisableVertexArrayEXT(vaobj, array, function_pointer);
/* 2996:     */  }
/* 2997:     */  
/* 2998:     */  static native void nglDisableVertexArrayEXT(int paramInt1, int paramInt2, long paramLong);
/* 2999:     */  
/* 3000:3000 */  public static void glEnableVertexArrayAttribEXT(int vaobj, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3001:3001 */    long function_pointer = caps.glEnableVertexArrayAttribEXT;
/* 3002:3002 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3003:3003 */    nglEnableVertexArrayAttribEXT(vaobj, index, function_pointer);
/* 3004:     */  }
/* 3005:     */  
/* 3006:     */  static native void nglEnableVertexArrayAttribEXT(int paramInt1, int paramInt2, long paramLong);
/* 3007:     */  
/* 3008:3008 */  public static void glDisableVertexArrayAttribEXT(int vaobj, int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3009:3009 */    long function_pointer = caps.glDisableVertexArrayAttribEXT;
/* 3010:3010 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3011:3011 */    nglDisableVertexArrayAttribEXT(vaobj, index, function_pointer);
/* 3012:     */  }
/* 3013:     */  
/* 3014:     */  static native void nglDisableVertexArrayAttribEXT(int paramInt1, int paramInt2, long paramLong);
/* 3015:     */  
/* 3016:3016 */  public static void glGetVertexArrayIntegerEXT(int vaobj, int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3017:3017 */    long function_pointer = caps.glGetVertexArrayIntegervEXT;
/* 3018:3018 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3019:3019 */    BufferChecks.checkBuffer(param, 16);
/* 3020:3020 */    nglGetVertexArrayIntegervEXT(vaobj, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3021:     */  }
/* 3022:     */  
/* 3023:     */  static native void nglGetVertexArrayIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3024:     */  
/* 3025:     */  public static int glGetVertexArrayIntegerEXT(int vaobj, int pname) {
/* 3026:3026 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3027:3027 */    long function_pointer = caps.glGetVertexArrayIntegervEXT;
/* 3028:3028 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3029:3029 */    IntBuffer param = APIUtil.getBufferInt(caps);
/* 3030:3030 */    nglGetVertexArrayIntegervEXT(vaobj, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3031:3031 */    return param.get(0);
/* 3032:     */  }
/* 3033:     */  
/* 3034:     */  public static ByteBuffer glGetVertexArrayPointerEXT(int vaobj, int pname, long result_size) {
/* 3035:3035 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3036:3036 */    long function_pointer = caps.glGetVertexArrayPointervEXT;
/* 3037:3037 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3038:3038 */    ByteBuffer __result = nglGetVertexArrayPointervEXT(vaobj, pname, result_size, function_pointer);
/* 3039:3039 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 3040:     */  }
/* 3041:     */  
/* 3042:     */  static native ByteBuffer nglGetVertexArrayPointervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 3043:     */  
/* 3044:3044 */  public static void glGetVertexArrayIntegerEXT(int vaobj, int index, int pname, IntBuffer param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3045:3045 */    long function_pointer = caps.glGetVertexArrayIntegeri_vEXT;
/* 3046:3046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3047:3047 */    BufferChecks.checkBuffer(param, 16);
/* 3048:3048 */    nglGetVertexArrayIntegeri_vEXT(vaobj, index, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3049:     */  }
/* 3050:     */  
/* 3051:     */  static native void nglGetVertexArrayIntegeri_vEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 3052:     */  
/* 3053:     */  public static int glGetVertexArrayIntegeriEXT(int vaobj, int index, int pname) {
/* 3054:3054 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3055:3055 */    long function_pointer = caps.glGetVertexArrayIntegeri_vEXT;
/* 3056:3056 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3057:3057 */    IntBuffer param = APIUtil.getBufferInt(caps);
/* 3058:3058 */    nglGetVertexArrayIntegeri_vEXT(vaobj, index, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3059:3059 */    return param.get(0);
/* 3060:     */  }
/* 3061:     */  
/* 3062:     */  public static ByteBuffer glGetVertexArrayPointeri_EXT(int vaobj, int index, int pname, long result_size) {
/* 3063:3063 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3064:3064 */    long function_pointer = caps.glGetVertexArrayPointeri_vEXT;
/* 3065:3065 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3066:3066 */    ByteBuffer __result = nglGetVertexArrayPointeri_vEXT(vaobj, index, pname, result_size, function_pointer);
/* 3067:3067 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 3068:     */  }
/* 3069:     */  
/* 3076:     */  static native ByteBuffer nglGetVertexArrayPointeri_vEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 3077:     */  
/* 3084:     */  public static ByteBuffer glMapNamedBufferRangeEXT(int buffer, long offset, long length, int access, ByteBuffer old_buffer)
/* 3085:     */  {
/* 3086:3086 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 3087:3087 */    long function_pointer = caps.glMapNamedBufferRangeEXT;
/* 3088:3088 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3089:3089 */    if (old_buffer != null)
/* 3090:3090 */      BufferChecks.checkDirect(old_buffer);
/* 3091:3091 */    ByteBuffer __result = nglMapNamedBufferRangeEXT(buffer, offset, length, access, old_buffer, function_pointer);
/* 3092:3092 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 3093:     */  }
/* 3094:     */  
/* 3095:     */  static native ByteBuffer nglMapNamedBufferRangeEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, ByteBuffer paramByteBuffer, long paramLong3);
/* 3096:     */  
/* 3097:3097 */  public static void glFlushMappedNamedBufferRangeEXT(int buffer, long offset, long length) { ContextCapabilities caps = GLContext.getCapabilities();
/* 3098:3098 */    long function_pointer = caps.glFlushMappedNamedBufferRangeEXT;
/* 3099:3099 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 3100:3100 */    nglFlushMappedNamedBufferRangeEXT(buffer, offset, length, function_pointer);
/* 3101:     */  }
/* 3102:     */  
/* 3103:     */  static native void nglFlushMappedNamedBufferRangeEXT(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 3104:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDirectStateAccess
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */