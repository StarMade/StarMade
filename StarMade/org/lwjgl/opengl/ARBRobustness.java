/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.DoubleBuffer;
/*    5:     */import java.nio.FloatBuffer;
/*    6:     */import java.nio.IntBuffer;
/*    7:     */import java.nio.ShortBuffer;
/*    8:     */import org.lwjgl.BufferChecks;
/*    9:     */import org.lwjgl.MemoryUtil;
/*   10:     */
/*   26:     */public final class ARBRobustness
/*   27:     */{
/*   28:     */  public static final int GL_NO_ERROR = 0;
/*   29:     */  public static final int GL_GUILTY_CONTEXT_RESET_ARB = 33363;
/*   30:     */  public static final int GL_INNOCENT_CONTEXT_RESET_ARB = 33364;
/*   31:     */  public static final int GL_UNKNOWN_CONTEXT_RESET_ARB = 33365;
/*   32:     */  public static final int GL_RESET_NOTIFICATION_STRATEGY_ARB = 33366;
/*   33:     */  public static final int GL_LOSE_CONTEXT_ON_RESET_ARB = 33362;
/*   34:     */  public static final int GL_NO_RESET_NOTIFICATION_ARB = 33377;
/*   35:     */  public static final int GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT_ARB = 4;
/*   36:     */  
/*   37:     */  public static int glGetGraphicsResetStatusARB()
/*   38:     */  {
/*   39:  39 */    ContextCapabilities caps = GLContext.getCapabilities();
/*   40:  40 */    long function_pointer = caps.glGetGraphicsResetStatusARB;
/*   41:  41 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   42:  42 */    int __result = nglGetGraphicsResetStatusARB(function_pointer);
/*   43:  43 */    return __result;
/*   44:     */  }
/*   45:     */  
/*   46:     */  static native int nglGetGraphicsResetStatusARB(long paramLong);
/*   47:     */  
/*   48:  48 */  public static void glGetnMapdvARB(int target, int query, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*   49:  49 */    long function_pointer = caps.glGetnMapdvARB;
/*   50:  50 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   51:  51 */    BufferChecks.checkDirect(v);
/*   52:  52 */    nglGetnMapdvARB(target, query, v.remaining() << 3, MemoryUtil.getAddress(v), function_pointer);
/*   53:     */  }
/*   54:     */  
/*   55:     */  static native void nglGetnMapdvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*   56:     */  
/*   57:  57 */  public static void glGetnMapfvARB(int target, int query, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*   58:  58 */    long function_pointer = caps.glGetnMapfvARB;
/*   59:  59 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   60:  60 */    BufferChecks.checkDirect(v);
/*   61:  61 */    nglGetnMapfvARB(target, query, v.remaining() << 2, MemoryUtil.getAddress(v), function_pointer);
/*   62:     */  }
/*   63:     */  
/*   64:     */  static native void nglGetnMapfvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*   65:     */  
/*   66:  66 */  public static void glGetnMapivARB(int target, int query, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*   67:  67 */    long function_pointer = caps.glGetnMapivARB;
/*   68:  68 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   69:  69 */    BufferChecks.checkDirect(v);
/*   70:  70 */    nglGetnMapivARB(target, query, v.remaining() << 2, MemoryUtil.getAddress(v), function_pointer);
/*   71:     */  }
/*   72:     */  
/*   73:     */  static native void nglGetnMapivARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*   74:     */  
/*   75:  75 */  public static void glGetnPixelMapfvARB(int map, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*   76:  76 */    long function_pointer = caps.glGetnPixelMapfvARB;
/*   77:  77 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   78:  78 */    BufferChecks.checkDirect(values);
/*   79:  79 */    nglGetnPixelMapfvARB(map, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*   80:     */  }
/*   81:     */  
/*   82:     */  static native void nglGetnPixelMapfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*   83:     */  
/*   84:  84 */  public static void glGetnPixelMapuivARB(int map, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*   85:  85 */    long function_pointer = caps.glGetnPixelMapuivARB;
/*   86:  86 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   87:  87 */    BufferChecks.checkDirect(values);
/*   88:  88 */    nglGetnPixelMapuivARB(map, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*   89:     */  }
/*   90:     */  
/*   91:     */  static native void nglGetnPixelMapuivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*   92:     */  
/*   93:  93 */  public static void glGetnPixelMapusvARB(int map, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*   94:  94 */    long function_pointer = caps.glGetnPixelMapusvARB;
/*   95:  95 */    BufferChecks.checkFunctionAddress(function_pointer);
/*   96:  96 */    BufferChecks.checkDirect(values);
/*   97:  97 */    nglGetnPixelMapusvARB(map, values.remaining() << 1, MemoryUtil.getAddress(values), function_pointer);
/*   98:     */  }
/*   99:     */  
/*  100:     */  static native void nglGetnPixelMapusvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  101:     */  
/*  102: 102 */  public static void glGetnPolygonStippleARB(ByteBuffer pattern) { ContextCapabilities caps = GLContext.getCapabilities();
/*  103: 103 */    long function_pointer = caps.glGetnPolygonStippleARB;
/*  104: 104 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  105: 105 */    BufferChecks.checkDirect(pattern);
/*  106: 106 */    nglGetnPolygonStippleARB(pattern.remaining(), MemoryUtil.getAddress(pattern), function_pointer);
/*  107:     */  }
/*  108:     */  
/*  109:     */  static native void nglGetnPolygonStippleARB(int paramInt, long paramLong1, long paramLong2);
/*  110:     */  
/*  111: 111 */  public static void glGetnTexImageARB(int target, int level, int format, int type, ByteBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/*  112: 112 */    long function_pointer = caps.glGetnTexImageARB;
/*  113: 113 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  114: 114 */    GLChecks.ensurePackPBOdisabled(caps);
/*  115: 115 */    BufferChecks.checkDirect(img);
/*  116: 116 */    nglGetnTexImageARB(target, level, format, type, img.remaining(), MemoryUtil.getAddress(img), function_pointer);
/*  117:     */  }
/*  118:     */  
/*  119: 119 */  public static void glGetnTexImageARB(int target, int level, int format, int type, DoubleBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/*  120: 120 */    long function_pointer = caps.glGetnTexImageARB;
/*  121: 121 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  122: 122 */    GLChecks.ensurePackPBOdisabled(caps);
/*  123: 123 */    BufferChecks.checkDirect(img);
/*  124: 124 */    nglGetnTexImageARB(target, level, format, type, img.remaining() << 3, MemoryUtil.getAddress(img), function_pointer);
/*  125:     */  }
/*  126:     */  
/*  127: 127 */  public static void glGetnTexImageARB(int target, int level, int format, int type, FloatBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/*  128: 128 */    long function_pointer = caps.glGetnTexImageARB;
/*  129: 129 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  130: 130 */    GLChecks.ensurePackPBOdisabled(caps);
/*  131: 131 */    BufferChecks.checkDirect(img);
/*  132: 132 */    nglGetnTexImageARB(target, level, format, type, img.remaining() << 2, MemoryUtil.getAddress(img), function_pointer);
/*  133:     */  }
/*  134:     */  
/*  135: 135 */  public static void glGetnTexImageARB(int target, int level, int format, int type, IntBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/*  136: 136 */    long function_pointer = caps.glGetnTexImageARB;
/*  137: 137 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  138: 138 */    GLChecks.ensurePackPBOdisabled(caps);
/*  139: 139 */    BufferChecks.checkDirect(img);
/*  140: 140 */    nglGetnTexImageARB(target, level, format, type, img.remaining() << 2, MemoryUtil.getAddress(img), function_pointer);
/*  141:     */  }
/*  142:     */  
/*  143: 143 */  public static void glGetnTexImageARB(int target, int level, int format, int type, ShortBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/*  144: 144 */    long function_pointer = caps.glGetnTexImageARB;
/*  145: 145 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  146: 146 */    GLChecks.ensurePackPBOdisabled(caps);
/*  147: 147 */    BufferChecks.checkDirect(img);
/*  148: 148 */    nglGetnTexImageARB(target, level, format, type, img.remaining() << 1, MemoryUtil.getAddress(img), function_pointer); }
/*  149:     */  
/*  150:     */  static native void nglGetnTexImageARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  151:     */  
/*  152: 152 */  public static void glGetnTexImageARB(int target, int level, int format, int type, int img_bufSize, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  153: 153 */    long function_pointer = caps.glGetnTexImageARB;
/*  154: 154 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  155: 155 */    GLChecks.ensurePackPBOenabled(caps);
/*  156: 156 */    nglGetnTexImageARBBO(target, level, format, type, img_bufSize, img_buffer_offset, function_pointer);
/*  157:     */  }
/*  158:     */  
/*  159:     */  static native void nglGetnTexImageARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  160:     */  
/*  161: 161 */  public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  162: 162 */    long function_pointer = caps.glReadnPixelsARB;
/*  163: 163 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  164: 164 */    GLChecks.ensurePackPBOdisabled(caps);
/*  165: 165 */    BufferChecks.checkDirect(data);
/*  166: 166 */    nglReadnPixelsARB(x, y, width, height, format, type, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*  167:     */  }
/*  168:     */  
/*  169: 169 */  public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  170: 170 */    long function_pointer = caps.glReadnPixelsARB;
/*  171: 171 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  172: 172 */    GLChecks.ensurePackPBOdisabled(caps);
/*  173: 173 */    BufferChecks.checkDirect(data);
/*  174: 174 */    nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*  175:     */  }
/*  176:     */  
/*  177: 177 */  public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  178: 178 */    long function_pointer = caps.glReadnPixelsARB;
/*  179: 179 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  180: 180 */    GLChecks.ensurePackPBOdisabled(caps);
/*  181: 181 */    BufferChecks.checkDirect(data);
/*  182: 182 */    nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*  183:     */  }
/*  184:     */  
/*  185: 185 */  public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  186: 186 */    long function_pointer = caps.glReadnPixelsARB;
/*  187: 187 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  188: 188 */    GLChecks.ensurePackPBOdisabled(caps);
/*  189: 189 */    BufferChecks.checkDirect(data);
/*  190: 190 */    nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*  191:     */  }
/*  192:     */  
/*  193: 193 */  public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  194: 194 */    long function_pointer = caps.glReadnPixelsARB;
/*  195: 195 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  196: 196 */    GLChecks.ensurePackPBOdisabled(caps);
/*  197: 197 */    BufferChecks.checkDirect(data);
/*  198: 198 */    nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer); }
/*  199:     */  
/*  200:     */  static native void nglReadnPixelsARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*  201:     */  
/*  202: 202 */  public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, int data_bufSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  203: 203 */    long function_pointer = caps.glReadnPixelsARB;
/*  204: 204 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  205: 205 */    GLChecks.ensurePackPBOenabled(caps);
/*  206: 206 */    nglReadnPixelsARBBO(x, y, width, height, format, type, data_bufSize, data_buffer_offset, function_pointer);
/*  207:     */  }
/*  208:     */  
/*  209:     */  static native void nglReadnPixelsARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*  210:     */  
/*  211: 211 */  public static void glGetnColorTableARB(int target, int format, int type, ByteBuffer table) { ContextCapabilities caps = GLContext.getCapabilities();
/*  212: 212 */    long function_pointer = caps.glGetnColorTableARB;
/*  213: 213 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  214: 214 */    BufferChecks.checkDirect(table);
/*  215: 215 */    nglGetnColorTableARB(target, format, type, table.remaining(), MemoryUtil.getAddress(table), function_pointer);
/*  216:     */  }
/*  217:     */  
/*  218: 218 */  public static void glGetnColorTableARB(int target, int format, int type, DoubleBuffer table) { ContextCapabilities caps = GLContext.getCapabilities();
/*  219: 219 */    long function_pointer = caps.glGetnColorTableARB;
/*  220: 220 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  221: 221 */    BufferChecks.checkDirect(table);
/*  222: 222 */    nglGetnColorTableARB(target, format, type, table.remaining() << 3, MemoryUtil.getAddress(table), function_pointer);
/*  223:     */  }
/*  224:     */  
/*  225: 225 */  public static void glGetnColorTableARB(int target, int format, int type, FloatBuffer table) { ContextCapabilities caps = GLContext.getCapabilities();
/*  226: 226 */    long function_pointer = caps.glGetnColorTableARB;
/*  227: 227 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  228: 228 */    BufferChecks.checkDirect(table);
/*  229: 229 */    nglGetnColorTableARB(target, format, type, table.remaining() << 2, MemoryUtil.getAddress(table), function_pointer);
/*  230:     */  }
/*  231:     */  
/*  232:     */  static native void nglGetnColorTableARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  233:     */  
/*  234: 234 */  public static void glGetnConvolutionFilterARB(int target, int format, int type, ByteBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  235: 235 */    long function_pointer = caps.glGetnConvolutionFilterARB;
/*  236: 236 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  237: 237 */    GLChecks.ensurePackPBOdisabled(caps);
/*  238: 238 */    BufferChecks.checkDirect(image);
/*  239: 239 */    nglGetnConvolutionFilterARB(target, format, type, image.remaining(), MemoryUtil.getAddress(image), function_pointer);
/*  240:     */  }
/*  241:     */  
/*  242: 242 */  public static void glGetnConvolutionFilterARB(int target, int format, int type, DoubleBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  243: 243 */    long function_pointer = caps.glGetnConvolutionFilterARB;
/*  244: 244 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  245: 245 */    GLChecks.ensurePackPBOdisabled(caps);
/*  246: 246 */    BufferChecks.checkDirect(image);
/*  247: 247 */    nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 3, MemoryUtil.getAddress(image), function_pointer);
/*  248:     */  }
/*  249:     */  
/*  250: 250 */  public static void glGetnConvolutionFilterARB(int target, int format, int type, FloatBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  251: 251 */    long function_pointer = caps.glGetnConvolutionFilterARB;
/*  252: 252 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  253: 253 */    GLChecks.ensurePackPBOdisabled(caps);
/*  254: 254 */    BufferChecks.checkDirect(image);
/*  255: 255 */    nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 2, MemoryUtil.getAddress(image), function_pointer);
/*  256:     */  }
/*  257:     */  
/*  258: 258 */  public static void glGetnConvolutionFilterARB(int target, int format, int type, IntBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  259: 259 */    long function_pointer = caps.glGetnConvolutionFilterARB;
/*  260: 260 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  261: 261 */    GLChecks.ensurePackPBOdisabled(caps);
/*  262: 262 */    BufferChecks.checkDirect(image);
/*  263: 263 */    nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 2, MemoryUtil.getAddress(image), function_pointer);
/*  264:     */  }
/*  265:     */  
/*  266: 266 */  public static void glGetnConvolutionFilterARB(int target, int format, int type, ShortBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  267: 267 */    long function_pointer = caps.glGetnConvolutionFilterARB;
/*  268: 268 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  269: 269 */    GLChecks.ensurePackPBOdisabled(caps);
/*  270: 270 */    BufferChecks.checkDirect(image);
/*  271: 271 */    nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 1, MemoryUtil.getAddress(image), function_pointer); }
/*  272:     */  
/*  273:     */  static native void nglGetnConvolutionFilterARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  274:     */  
/*  275: 275 */  public static void glGetnConvolutionFilterARB(int target, int format, int type, int image_bufSize, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  276: 276 */    long function_pointer = caps.glGetnConvolutionFilterARB;
/*  277: 277 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  278: 278 */    GLChecks.ensurePackPBOenabled(caps);
/*  279: 279 */    nglGetnConvolutionFilterARBBO(target, format, type, image_bufSize, image_buffer_offset, function_pointer);
/*  280:     */  }
/*  281:     */  
/*  282:     */  static native void nglGetnConvolutionFilterARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  283:     */  
/*  284: 284 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  285: 285 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  286: 286 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  287: 287 */    GLChecks.ensurePackPBOdisabled(caps);
/*  288: 288 */    BufferChecks.checkDirect(row);
/*  289: 289 */    BufferChecks.checkDirect(column);
/*  290: 290 */    BufferChecks.checkDirect(span);
/*  291: 291 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  292:     */  }
/*  293:     */  
/*  294: 294 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  295: 295 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  296: 296 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  297: 297 */    GLChecks.ensurePackPBOdisabled(caps);
/*  298: 298 */    BufferChecks.checkDirect(row);
/*  299: 299 */    BufferChecks.checkDirect(column);
/*  300: 300 */    BufferChecks.checkDirect(span);
/*  301: 301 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  302:     */  }
/*  303:     */  
/*  304: 304 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  305: 305 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  306: 306 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  307: 307 */    GLChecks.ensurePackPBOdisabled(caps);
/*  308: 308 */    BufferChecks.checkDirect(row);
/*  309: 309 */    BufferChecks.checkDirect(column);
/*  310: 310 */    BufferChecks.checkDirect(span);
/*  311: 311 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  312:     */  }
/*  313:     */  
/*  314: 314 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  315: 315 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  316: 316 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  317: 317 */    GLChecks.ensurePackPBOdisabled(caps);
/*  318: 318 */    BufferChecks.checkDirect(row);
/*  319: 319 */    BufferChecks.checkDirect(column);
/*  320: 320 */    BufferChecks.checkDirect(span);
/*  321: 321 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  322:     */  }
/*  323:     */  
/*  324: 324 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  325: 325 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  326: 326 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  327: 327 */    GLChecks.ensurePackPBOdisabled(caps);
/*  328: 328 */    BufferChecks.checkDirect(row);
/*  329: 329 */    BufferChecks.checkDirect(column);
/*  330: 330 */    BufferChecks.checkDirect(span);
/*  331: 331 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  332:     */  }
/*  333:     */  
/*  334: 334 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  335: 335 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  336: 336 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  337: 337 */    GLChecks.ensurePackPBOdisabled(caps);
/*  338: 338 */    BufferChecks.checkDirect(row);
/*  339: 339 */    BufferChecks.checkDirect(column);
/*  340: 340 */    BufferChecks.checkDirect(span);
/*  341: 341 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  342:     */  }
/*  343:     */  
/*  344: 344 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  345: 345 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  346: 346 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  347: 347 */    GLChecks.ensurePackPBOdisabled(caps);
/*  348: 348 */    BufferChecks.checkDirect(row);
/*  349: 349 */    BufferChecks.checkDirect(column);
/*  350: 350 */    BufferChecks.checkDirect(span);
/*  351: 351 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  352:     */  }
/*  353:     */  
/*  354: 354 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  355: 355 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  356: 356 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  357: 357 */    GLChecks.ensurePackPBOdisabled(caps);
/*  358: 358 */    BufferChecks.checkDirect(row);
/*  359: 359 */    BufferChecks.checkDirect(column);
/*  360: 360 */    BufferChecks.checkDirect(span);
/*  361: 361 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  362:     */  }
/*  363:     */  
/*  364: 364 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  365: 365 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  366: 366 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  367: 367 */    GLChecks.ensurePackPBOdisabled(caps);
/*  368: 368 */    BufferChecks.checkDirect(row);
/*  369: 369 */    BufferChecks.checkDirect(column);
/*  370: 370 */    BufferChecks.checkDirect(span);
/*  371: 371 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  372:     */  }
/*  373:     */  
/*  374: 374 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  375: 375 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  376: 376 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  377: 377 */    GLChecks.ensurePackPBOdisabled(caps);
/*  378: 378 */    BufferChecks.checkDirect(row);
/*  379: 379 */    BufferChecks.checkDirect(column);
/*  380: 380 */    BufferChecks.checkDirect(span);
/*  381: 381 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  382:     */  }
/*  383:     */  
/*  384: 384 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  385: 385 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  386: 386 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  387: 387 */    GLChecks.ensurePackPBOdisabled(caps);
/*  388: 388 */    BufferChecks.checkDirect(row);
/*  389: 389 */    BufferChecks.checkDirect(column);
/*  390: 390 */    BufferChecks.checkDirect(span);
/*  391: 391 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  392:     */  }
/*  393:     */  
/*  394: 394 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  395: 395 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  396: 396 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  397: 397 */    GLChecks.ensurePackPBOdisabled(caps);
/*  398: 398 */    BufferChecks.checkDirect(row);
/*  399: 399 */    BufferChecks.checkDirect(column);
/*  400: 400 */    BufferChecks.checkDirect(span);
/*  401: 401 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  402:     */  }
/*  403:     */  
/*  404: 404 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  405: 405 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  406: 406 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  407: 407 */    GLChecks.ensurePackPBOdisabled(caps);
/*  408: 408 */    BufferChecks.checkDirect(row);
/*  409: 409 */    BufferChecks.checkDirect(column);
/*  410: 410 */    BufferChecks.checkDirect(span);
/*  411: 411 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  412:     */  }
/*  413:     */  
/*  414: 414 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  415: 415 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  416: 416 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  417: 417 */    GLChecks.ensurePackPBOdisabled(caps);
/*  418: 418 */    BufferChecks.checkDirect(row);
/*  419: 419 */    BufferChecks.checkDirect(column);
/*  420: 420 */    BufferChecks.checkDirect(span);
/*  421: 421 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  422:     */  }
/*  423:     */  
/*  424: 424 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  425: 425 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  426: 426 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  427: 427 */    GLChecks.ensurePackPBOdisabled(caps);
/*  428: 428 */    BufferChecks.checkDirect(row);
/*  429: 429 */    BufferChecks.checkDirect(column);
/*  430: 430 */    BufferChecks.checkDirect(span);
/*  431: 431 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  432:     */  }
/*  433:     */  
/*  434: 434 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  435: 435 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  436: 436 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  437: 437 */    GLChecks.ensurePackPBOdisabled(caps);
/*  438: 438 */    BufferChecks.checkDirect(row);
/*  439: 439 */    BufferChecks.checkDirect(column);
/*  440: 440 */    BufferChecks.checkDirect(span);
/*  441: 441 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  442:     */  }
/*  443:     */  
/*  444: 444 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  445: 445 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  446: 446 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  447: 447 */    GLChecks.ensurePackPBOdisabled(caps);
/*  448: 448 */    BufferChecks.checkDirect(row);
/*  449: 449 */    BufferChecks.checkDirect(column);
/*  450: 450 */    BufferChecks.checkDirect(span);
/*  451: 451 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  452:     */  }
/*  453:     */  
/*  454: 454 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  455: 455 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  456: 456 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  457: 457 */    GLChecks.ensurePackPBOdisabled(caps);
/*  458: 458 */    BufferChecks.checkDirect(row);
/*  459: 459 */    BufferChecks.checkDirect(column);
/*  460: 460 */    BufferChecks.checkDirect(span);
/*  461: 461 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  462:     */  }
/*  463:     */  
/*  464: 464 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  465: 465 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  466: 466 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  467: 467 */    GLChecks.ensurePackPBOdisabled(caps);
/*  468: 468 */    BufferChecks.checkDirect(row);
/*  469: 469 */    BufferChecks.checkDirect(column);
/*  470: 470 */    BufferChecks.checkDirect(span);
/*  471: 471 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  472:     */  }
/*  473:     */  
/*  474: 474 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  475: 475 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  476: 476 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  477: 477 */    GLChecks.ensurePackPBOdisabled(caps);
/*  478: 478 */    BufferChecks.checkDirect(row);
/*  479: 479 */    BufferChecks.checkDirect(column);
/*  480: 480 */    BufferChecks.checkDirect(span);
/*  481: 481 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  482:     */  }
/*  483:     */  
/*  484: 484 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  485: 485 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  486: 486 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  487: 487 */    GLChecks.ensurePackPBOdisabled(caps);
/*  488: 488 */    BufferChecks.checkDirect(row);
/*  489: 489 */    BufferChecks.checkDirect(column);
/*  490: 490 */    BufferChecks.checkDirect(span);
/*  491: 491 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  492:     */  }
/*  493:     */  
/*  494: 494 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  495: 495 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  496: 496 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  497: 497 */    GLChecks.ensurePackPBOdisabled(caps);
/*  498: 498 */    BufferChecks.checkDirect(row);
/*  499: 499 */    BufferChecks.checkDirect(column);
/*  500: 500 */    BufferChecks.checkDirect(span);
/*  501: 501 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  502:     */  }
/*  503:     */  
/*  504: 504 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  505: 505 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  506: 506 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  507: 507 */    GLChecks.ensurePackPBOdisabled(caps);
/*  508: 508 */    BufferChecks.checkDirect(row);
/*  509: 509 */    BufferChecks.checkDirect(column);
/*  510: 510 */    BufferChecks.checkDirect(span);
/*  511: 511 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  512:     */  }
/*  513:     */  
/*  514: 514 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  515: 515 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  516: 516 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  517: 517 */    GLChecks.ensurePackPBOdisabled(caps);
/*  518: 518 */    BufferChecks.checkDirect(row);
/*  519: 519 */    BufferChecks.checkDirect(column);
/*  520: 520 */    BufferChecks.checkDirect(span);
/*  521: 521 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  522:     */  }
/*  523:     */  
/*  524: 524 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  525: 525 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  526: 526 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  527: 527 */    GLChecks.ensurePackPBOdisabled(caps);
/*  528: 528 */    BufferChecks.checkDirect(row);
/*  529: 529 */    BufferChecks.checkDirect(column);
/*  530: 530 */    BufferChecks.checkDirect(span);
/*  531: 531 */    nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  532:     */  }
/*  533:     */  
/*  534: 534 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  535: 535 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  536: 536 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  537: 537 */    GLChecks.ensurePackPBOdisabled(caps);
/*  538: 538 */    BufferChecks.checkDirect(row);
/*  539: 539 */    BufferChecks.checkDirect(column);
/*  540: 540 */    BufferChecks.checkDirect(span);
/*  541: 541 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  542:     */  }
/*  543:     */  
/*  544: 544 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  545: 545 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  546: 546 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  547: 547 */    GLChecks.ensurePackPBOdisabled(caps);
/*  548: 548 */    BufferChecks.checkDirect(row);
/*  549: 549 */    BufferChecks.checkDirect(column);
/*  550: 550 */    BufferChecks.checkDirect(span);
/*  551: 551 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  552:     */  }
/*  553:     */  
/*  554: 554 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  555: 555 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  556: 556 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  557: 557 */    GLChecks.ensurePackPBOdisabled(caps);
/*  558: 558 */    BufferChecks.checkDirect(row);
/*  559: 559 */    BufferChecks.checkDirect(column);
/*  560: 560 */    BufferChecks.checkDirect(span);
/*  561: 561 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  562:     */  }
/*  563:     */  
/*  564: 564 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  565: 565 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  566: 566 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  567: 567 */    GLChecks.ensurePackPBOdisabled(caps);
/*  568: 568 */    BufferChecks.checkDirect(row);
/*  569: 569 */    BufferChecks.checkDirect(column);
/*  570: 570 */    BufferChecks.checkDirect(span);
/*  571: 571 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  572:     */  }
/*  573:     */  
/*  574: 574 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  575: 575 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  576: 576 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  577: 577 */    GLChecks.ensurePackPBOdisabled(caps);
/*  578: 578 */    BufferChecks.checkDirect(row);
/*  579: 579 */    BufferChecks.checkDirect(column);
/*  580: 580 */    BufferChecks.checkDirect(span);
/*  581: 581 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  582:     */  }
/*  583:     */  
/*  584: 584 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  585: 585 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  586: 586 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  587: 587 */    GLChecks.ensurePackPBOdisabled(caps);
/*  588: 588 */    BufferChecks.checkDirect(row);
/*  589: 589 */    BufferChecks.checkDirect(column);
/*  590: 590 */    BufferChecks.checkDirect(span);
/*  591: 591 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  592:     */  }
/*  593:     */  
/*  594: 594 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  595: 595 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  596: 596 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  597: 597 */    GLChecks.ensurePackPBOdisabled(caps);
/*  598: 598 */    BufferChecks.checkDirect(row);
/*  599: 599 */    BufferChecks.checkDirect(column);
/*  600: 600 */    BufferChecks.checkDirect(span);
/*  601: 601 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  602:     */  }
/*  603:     */  
/*  604: 604 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  605: 605 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  606: 606 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  607: 607 */    GLChecks.ensurePackPBOdisabled(caps);
/*  608: 608 */    BufferChecks.checkDirect(row);
/*  609: 609 */    BufferChecks.checkDirect(column);
/*  610: 610 */    BufferChecks.checkDirect(span);
/*  611: 611 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  612:     */  }
/*  613:     */  
/*  614: 614 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  615: 615 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  616: 616 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  617: 617 */    GLChecks.ensurePackPBOdisabled(caps);
/*  618: 618 */    BufferChecks.checkDirect(row);
/*  619: 619 */    BufferChecks.checkDirect(column);
/*  620: 620 */    BufferChecks.checkDirect(span);
/*  621: 621 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  622:     */  }
/*  623:     */  
/*  624: 624 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  625: 625 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  626: 626 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  627: 627 */    GLChecks.ensurePackPBOdisabled(caps);
/*  628: 628 */    BufferChecks.checkDirect(row);
/*  629: 629 */    BufferChecks.checkDirect(column);
/*  630: 630 */    BufferChecks.checkDirect(span);
/*  631: 631 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  632:     */  }
/*  633:     */  
/*  634: 634 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  635: 635 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  636: 636 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  637: 637 */    GLChecks.ensurePackPBOdisabled(caps);
/*  638: 638 */    BufferChecks.checkDirect(row);
/*  639: 639 */    BufferChecks.checkDirect(column);
/*  640: 640 */    BufferChecks.checkDirect(span);
/*  641: 641 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  642:     */  }
/*  643:     */  
/*  644: 644 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  645: 645 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  646: 646 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  647: 647 */    GLChecks.ensurePackPBOdisabled(caps);
/*  648: 648 */    BufferChecks.checkDirect(row);
/*  649: 649 */    BufferChecks.checkDirect(column);
/*  650: 650 */    BufferChecks.checkDirect(span);
/*  651: 651 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  652:     */  }
/*  653:     */  
/*  654: 654 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  655: 655 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  656: 656 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  657: 657 */    GLChecks.ensurePackPBOdisabled(caps);
/*  658: 658 */    BufferChecks.checkDirect(row);
/*  659: 659 */    BufferChecks.checkDirect(column);
/*  660: 660 */    BufferChecks.checkDirect(span);
/*  661: 661 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  662:     */  }
/*  663:     */  
/*  664: 664 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  665: 665 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  666: 666 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  667: 667 */    GLChecks.ensurePackPBOdisabled(caps);
/*  668: 668 */    BufferChecks.checkDirect(row);
/*  669: 669 */    BufferChecks.checkDirect(column);
/*  670: 670 */    BufferChecks.checkDirect(span);
/*  671: 671 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  672:     */  }
/*  673:     */  
/*  674: 674 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  675: 675 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  676: 676 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  677: 677 */    GLChecks.ensurePackPBOdisabled(caps);
/*  678: 678 */    BufferChecks.checkDirect(row);
/*  679: 679 */    BufferChecks.checkDirect(column);
/*  680: 680 */    BufferChecks.checkDirect(span);
/*  681: 681 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  682:     */  }
/*  683:     */  
/*  684: 684 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  685: 685 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  686: 686 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  687: 687 */    GLChecks.ensurePackPBOdisabled(caps);
/*  688: 688 */    BufferChecks.checkDirect(row);
/*  689: 689 */    BufferChecks.checkDirect(column);
/*  690: 690 */    BufferChecks.checkDirect(span);
/*  691: 691 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  692:     */  }
/*  693:     */  
/*  694: 694 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  695: 695 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  696: 696 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  697: 697 */    GLChecks.ensurePackPBOdisabled(caps);
/*  698: 698 */    BufferChecks.checkDirect(row);
/*  699: 699 */    BufferChecks.checkDirect(column);
/*  700: 700 */    BufferChecks.checkDirect(span);
/*  701: 701 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  702:     */  }
/*  703:     */  
/*  704: 704 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  705: 705 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  706: 706 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  707: 707 */    GLChecks.ensurePackPBOdisabled(caps);
/*  708: 708 */    BufferChecks.checkDirect(row);
/*  709: 709 */    BufferChecks.checkDirect(column);
/*  710: 710 */    BufferChecks.checkDirect(span);
/*  711: 711 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  712:     */  }
/*  713:     */  
/*  714: 714 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  715: 715 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  716: 716 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  717: 717 */    GLChecks.ensurePackPBOdisabled(caps);
/*  718: 718 */    BufferChecks.checkDirect(row);
/*  719: 719 */    BufferChecks.checkDirect(column);
/*  720: 720 */    BufferChecks.checkDirect(span);
/*  721: 721 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  722:     */  }
/*  723:     */  
/*  724: 724 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  725: 725 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  726: 726 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  727: 727 */    GLChecks.ensurePackPBOdisabled(caps);
/*  728: 728 */    BufferChecks.checkDirect(row);
/*  729: 729 */    BufferChecks.checkDirect(column);
/*  730: 730 */    BufferChecks.checkDirect(span);
/*  731: 731 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  732:     */  }
/*  733:     */  
/*  734: 734 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  735: 735 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  736: 736 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  737: 737 */    GLChecks.ensurePackPBOdisabled(caps);
/*  738: 738 */    BufferChecks.checkDirect(row);
/*  739: 739 */    BufferChecks.checkDirect(column);
/*  740: 740 */    BufferChecks.checkDirect(span);
/*  741: 741 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  742:     */  }
/*  743:     */  
/*  744: 744 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  745: 745 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  746: 746 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  747: 747 */    GLChecks.ensurePackPBOdisabled(caps);
/*  748: 748 */    BufferChecks.checkDirect(row);
/*  749: 749 */    BufferChecks.checkDirect(column);
/*  750: 750 */    BufferChecks.checkDirect(span);
/*  751: 751 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  752:     */  }
/*  753:     */  
/*  754: 754 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  755: 755 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  756: 756 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  757: 757 */    GLChecks.ensurePackPBOdisabled(caps);
/*  758: 758 */    BufferChecks.checkDirect(row);
/*  759: 759 */    BufferChecks.checkDirect(column);
/*  760: 760 */    BufferChecks.checkDirect(span);
/*  761: 761 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  762:     */  }
/*  763:     */  
/*  764: 764 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  765: 765 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  766: 766 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  767: 767 */    GLChecks.ensurePackPBOdisabled(caps);
/*  768: 768 */    BufferChecks.checkDirect(row);
/*  769: 769 */    BufferChecks.checkDirect(column);
/*  770: 770 */    BufferChecks.checkDirect(span);
/*  771: 771 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  772:     */  }
/*  773:     */  
/*  774: 774 */  public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  775: 775 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  776: 776 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  777: 777 */    GLChecks.ensurePackPBOdisabled(caps);
/*  778: 778 */    BufferChecks.checkDirect(row);
/*  779: 779 */    BufferChecks.checkDirect(column);
/*  780: 780 */    BufferChecks.checkDirect(span);
/*  781: 781 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  782:     */  }
/*  783:     */  
/*  784: 784 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  785: 785 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  786: 786 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  787: 787 */    GLChecks.ensurePackPBOdisabled(caps);
/*  788: 788 */    BufferChecks.checkDirect(row);
/*  789: 789 */    BufferChecks.checkDirect(column);
/*  790: 790 */    BufferChecks.checkDirect(span);
/*  791: 791 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  792:     */  }
/*  793:     */  
/*  794: 794 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  795: 795 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  796: 796 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  797: 797 */    GLChecks.ensurePackPBOdisabled(caps);
/*  798: 798 */    BufferChecks.checkDirect(row);
/*  799: 799 */    BufferChecks.checkDirect(column);
/*  800: 800 */    BufferChecks.checkDirect(span);
/*  801: 801 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  802:     */  }
/*  803:     */  
/*  804: 804 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  805: 805 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  806: 806 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  807: 807 */    GLChecks.ensurePackPBOdisabled(caps);
/*  808: 808 */    BufferChecks.checkDirect(row);
/*  809: 809 */    BufferChecks.checkDirect(column);
/*  810: 810 */    BufferChecks.checkDirect(span);
/*  811: 811 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  812:     */  }
/*  813:     */  
/*  814: 814 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  815: 815 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  816: 816 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  817: 817 */    GLChecks.ensurePackPBOdisabled(caps);
/*  818: 818 */    BufferChecks.checkDirect(row);
/*  819: 819 */    BufferChecks.checkDirect(column);
/*  820: 820 */    BufferChecks.checkDirect(span);
/*  821: 821 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  822:     */  }
/*  823:     */  
/*  824: 824 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  825: 825 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  826: 826 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  827: 827 */    GLChecks.ensurePackPBOdisabled(caps);
/*  828: 828 */    BufferChecks.checkDirect(row);
/*  829: 829 */    BufferChecks.checkDirect(column);
/*  830: 830 */    BufferChecks.checkDirect(span);
/*  831: 831 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  832:     */  }
/*  833:     */  
/*  834: 834 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  835: 835 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  836: 836 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  837: 837 */    GLChecks.ensurePackPBOdisabled(caps);
/*  838: 838 */    BufferChecks.checkDirect(row);
/*  839: 839 */    BufferChecks.checkDirect(column);
/*  840: 840 */    BufferChecks.checkDirect(span);
/*  841: 841 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  842:     */  }
/*  843:     */  
/*  844: 844 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  845: 845 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  846: 846 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  847: 847 */    GLChecks.ensurePackPBOdisabled(caps);
/*  848: 848 */    BufferChecks.checkDirect(row);
/*  849: 849 */    BufferChecks.checkDirect(column);
/*  850: 850 */    BufferChecks.checkDirect(span);
/*  851: 851 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  852:     */  }
/*  853:     */  
/*  854: 854 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  855: 855 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  856: 856 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  857: 857 */    GLChecks.ensurePackPBOdisabled(caps);
/*  858: 858 */    BufferChecks.checkDirect(row);
/*  859: 859 */    BufferChecks.checkDirect(column);
/*  860: 860 */    BufferChecks.checkDirect(span);
/*  861: 861 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  862:     */  }
/*  863:     */  
/*  864: 864 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  865: 865 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  866: 866 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  867: 867 */    GLChecks.ensurePackPBOdisabled(caps);
/*  868: 868 */    BufferChecks.checkDirect(row);
/*  869: 869 */    BufferChecks.checkDirect(column);
/*  870: 870 */    BufferChecks.checkDirect(span);
/*  871: 871 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  872:     */  }
/*  873:     */  
/*  874: 874 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  875: 875 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  876: 876 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  877: 877 */    GLChecks.ensurePackPBOdisabled(caps);
/*  878: 878 */    BufferChecks.checkDirect(row);
/*  879: 879 */    BufferChecks.checkDirect(column);
/*  880: 880 */    BufferChecks.checkDirect(span);
/*  881: 881 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  882:     */  }
/*  883:     */  
/*  884: 884 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  885: 885 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  886: 886 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  887: 887 */    GLChecks.ensurePackPBOdisabled(caps);
/*  888: 888 */    BufferChecks.checkDirect(row);
/*  889: 889 */    BufferChecks.checkDirect(column);
/*  890: 890 */    BufferChecks.checkDirect(span);
/*  891: 891 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  892:     */  }
/*  893:     */  
/*  894: 894 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  895: 895 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  896: 896 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  897: 897 */    GLChecks.ensurePackPBOdisabled(caps);
/*  898: 898 */    BufferChecks.checkDirect(row);
/*  899: 899 */    BufferChecks.checkDirect(column);
/*  900: 900 */    BufferChecks.checkDirect(span);
/*  901: 901 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  902:     */  }
/*  903:     */  
/*  904: 904 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  905: 905 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  906: 906 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  907: 907 */    GLChecks.ensurePackPBOdisabled(caps);
/*  908: 908 */    BufferChecks.checkDirect(row);
/*  909: 909 */    BufferChecks.checkDirect(column);
/*  910: 910 */    BufferChecks.checkDirect(span);
/*  911: 911 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  912:     */  }
/*  913:     */  
/*  914: 914 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  915: 915 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  916: 916 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  917: 917 */    GLChecks.ensurePackPBOdisabled(caps);
/*  918: 918 */    BufferChecks.checkDirect(row);
/*  919: 919 */    BufferChecks.checkDirect(column);
/*  920: 920 */    BufferChecks.checkDirect(span);
/*  921: 921 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  922:     */  }
/*  923:     */  
/*  924: 924 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  925: 925 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  926: 926 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  927: 927 */    GLChecks.ensurePackPBOdisabled(caps);
/*  928: 928 */    BufferChecks.checkDirect(row);
/*  929: 929 */    BufferChecks.checkDirect(column);
/*  930: 930 */    BufferChecks.checkDirect(span);
/*  931: 931 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  932:     */  }
/*  933:     */  
/*  934: 934 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  935: 935 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  936: 936 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  937: 937 */    GLChecks.ensurePackPBOdisabled(caps);
/*  938: 938 */    BufferChecks.checkDirect(row);
/*  939: 939 */    BufferChecks.checkDirect(column);
/*  940: 940 */    BufferChecks.checkDirect(span);
/*  941: 941 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  942:     */  }
/*  943:     */  
/*  944: 944 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  945: 945 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  946: 946 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  947: 947 */    GLChecks.ensurePackPBOdisabled(caps);
/*  948: 948 */    BufferChecks.checkDirect(row);
/*  949: 949 */    BufferChecks.checkDirect(column);
/*  950: 950 */    BufferChecks.checkDirect(span);
/*  951: 951 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  952:     */  }
/*  953:     */  
/*  954: 954 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  955: 955 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  956: 956 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  957: 957 */    GLChecks.ensurePackPBOdisabled(caps);
/*  958: 958 */    BufferChecks.checkDirect(row);
/*  959: 959 */    BufferChecks.checkDirect(column);
/*  960: 960 */    BufferChecks.checkDirect(span);
/*  961: 961 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  962:     */  }
/*  963:     */  
/*  964: 964 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  965: 965 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  966: 966 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  967: 967 */    GLChecks.ensurePackPBOdisabled(caps);
/*  968: 968 */    BufferChecks.checkDirect(row);
/*  969: 969 */    BufferChecks.checkDirect(column);
/*  970: 970 */    BufferChecks.checkDirect(span);
/*  971: 971 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  972:     */  }
/*  973:     */  
/*  974: 974 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  975: 975 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  976: 976 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  977: 977 */    GLChecks.ensurePackPBOdisabled(caps);
/*  978: 978 */    BufferChecks.checkDirect(row);
/*  979: 979 */    BufferChecks.checkDirect(column);
/*  980: 980 */    BufferChecks.checkDirect(span);
/*  981: 981 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  982:     */  }
/*  983:     */  
/*  984: 984 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  985: 985 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  986: 986 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  987: 987 */    GLChecks.ensurePackPBOdisabled(caps);
/*  988: 988 */    BufferChecks.checkDirect(row);
/*  989: 989 */    BufferChecks.checkDirect(column);
/*  990: 990 */    BufferChecks.checkDirect(span);
/*  991: 991 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  992:     */  }
/*  993:     */  
/*  994: 994 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  995: 995 */    long function_pointer = caps.glGetnSeparableFilterARB;
/*  996: 996 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  997: 997 */    GLChecks.ensurePackPBOdisabled(caps);
/*  998: 998 */    BufferChecks.checkDirect(row);
/*  999: 999 */    BufferChecks.checkDirect(column);
/* 1000:1000 */    BufferChecks.checkDirect(span);
/* 1001:1001 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1002:     */  }
/* 1003:     */  
/* 1004:1004 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1005:1005 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1006:1006 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1007:1007 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1008:1008 */    BufferChecks.checkDirect(row);
/* 1009:1009 */    BufferChecks.checkDirect(column);
/* 1010:1010 */    BufferChecks.checkDirect(span);
/* 1011:1011 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1012:     */  }
/* 1013:     */  
/* 1014:1014 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1015:1015 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1016:1016 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1017:1017 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1018:1018 */    BufferChecks.checkDirect(row);
/* 1019:1019 */    BufferChecks.checkDirect(column);
/* 1020:1020 */    BufferChecks.checkDirect(span);
/* 1021:1021 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1022:     */  }
/* 1023:     */  
/* 1024:1024 */  public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1025:1025 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1026:1026 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1027:1027 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1028:1028 */    BufferChecks.checkDirect(row);
/* 1029:1029 */    BufferChecks.checkDirect(column);
/* 1030:1030 */    BufferChecks.checkDirect(span);
/* 1031:1031 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1032:     */  }
/* 1033:     */  
/* 1034:1034 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1035:1035 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1036:1036 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1037:1037 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1038:1038 */    BufferChecks.checkDirect(row);
/* 1039:1039 */    BufferChecks.checkDirect(column);
/* 1040:1040 */    BufferChecks.checkDirect(span);
/* 1041:1041 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1042:     */  }
/* 1043:     */  
/* 1044:1044 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1045:1045 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1046:1046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1047:1047 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1048:1048 */    BufferChecks.checkDirect(row);
/* 1049:1049 */    BufferChecks.checkDirect(column);
/* 1050:1050 */    BufferChecks.checkDirect(span);
/* 1051:1051 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1052:     */  }
/* 1053:     */  
/* 1054:1054 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1055:1055 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1056:1056 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1057:1057 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1058:1058 */    BufferChecks.checkDirect(row);
/* 1059:1059 */    BufferChecks.checkDirect(column);
/* 1060:1060 */    BufferChecks.checkDirect(span);
/* 1061:1061 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1062:     */  }
/* 1063:     */  
/* 1064:1064 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1065:1065 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1066:1066 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1067:1067 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1068:1068 */    BufferChecks.checkDirect(row);
/* 1069:1069 */    BufferChecks.checkDirect(column);
/* 1070:1070 */    BufferChecks.checkDirect(span);
/* 1071:1071 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1072:     */  }
/* 1073:     */  
/* 1074:1074 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1075:1075 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1076:1076 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1077:1077 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1078:1078 */    BufferChecks.checkDirect(row);
/* 1079:1079 */    BufferChecks.checkDirect(column);
/* 1080:1080 */    BufferChecks.checkDirect(span);
/* 1081:1081 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1082:     */  }
/* 1083:     */  
/* 1084:1084 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1085:1085 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1086:1086 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1087:1087 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1088:1088 */    BufferChecks.checkDirect(row);
/* 1089:1089 */    BufferChecks.checkDirect(column);
/* 1090:1090 */    BufferChecks.checkDirect(span);
/* 1091:1091 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1092:     */  }
/* 1093:     */  
/* 1094:1094 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1095:1095 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1096:1096 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1097:1097 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1098:1098 */    BufferChecks.checkDirect(row);
/* 1099:1099 */    BufferChecks.checkDirect(column);
/* 1100:1100 */    BufferChecks.checkDirect(span);
/* 1101:1101 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1102:     */  }
/* 1103:     */  
/* 1104:1104 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1105:1105 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1106:1106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1107:1107 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1108:1108 */    BufferChecks.checkDirect(row);
/* 1109:1109 */    BufferChecks.checkDirect(column);
/* 1110:1110 */    BufferChecks.checkDirect(span);
/* 1111:1111 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1112:     */  }
/* 1113:     */  
/* 1114:1114 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1115:1115 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1116:1116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1117:1117 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1118:1118 */    BufferChecks.checkDirect(row);
/* 1119:1119 */    BufferChecks.checkDirect(column);
/* 1120:1120 */    BufferChecks.checkDirect(span);
/* 1121:1121 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1122:     */  }
/* 1123:     */  
/* 1124:1124 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1125:1125 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1126:1126 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1127:1127 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1128:1128 */    BufferChecks.checkDirect(row);
/* 1129:1129 */    BufferChecks.checkDirect(column);
/* 1130:1130 */    BufferChecks.checkDirect(span);
/* 1131:1131 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1132:     */  }
/* 1133:     */  
/* 1134:1134 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1135:1135 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1136:1136 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1137:1137 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1138:1138 */    BufferChecks.checkDirect(row);
/* 1139:1139 */    BufferChecks.checkDirect(column);
/* 1140:1140 */    BufferChecks.checkDirect(span);
/* 1141:1141 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1142:     */  }
/* 1143:     */  
/* 1144:1144 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1145:1145 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1146:1146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1147:1147 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1148:1148 */    BufferChecks.checkDirect(row);
/* 1149:1149 */    BufferChecks.checkDirect(column);
/* 1150:1150 */    BufferChecks.checkDirect(span);
/* 1151:1151 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1152:     */  }
/* 1153:     */  
/* 1154:1154 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1155:1155 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1156:1156 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1157:1157 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1158:1158 */    BufferChecks.checkDirect(row);
/* 1159:1159 */    BufferChecks.checkDirect(column);
/* 1160:1160 */    BufferChecks.checkDirect(span);
/* 1161:1161 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1162:     */  }
/* 1163:     */  
/* 1164:1164 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1165:1165 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1166:1166 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1167:1167 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1168:1168 */    BufferChecks.checkDirect(row);
/* 1169:1169 */    BufferChecks.checkDirect(column);
/* 1170:1170 */    BufferChecks.checkDirect(span);
/* 1171:1171 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1172:     */  }
/* 1173:     */  
/* 1174:1174 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1175:1175 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1176:1176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1177:1177 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1178:1178 */    BufferChecks.checkDirect(row);
/* 1179:1179 */    BufferChecks.checkDirect(column);
/* 1180:1180 */    BufferChecks.checkDirect(span);
/* 1181:1181 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1182:     */  }
/* 1183:     */  
/* 1184:1184 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1185:1185 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1186:1186 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1187:1187 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1188:1188 */    BufferChecks.checkDirect(row);
/* 1189:1189 */    BufferChecks.checkDirect(column);
/* 1190:1190 */    BufferChecks.checkDirect(span);
/* 1191:1191 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1192:     */  }
/* 1193:     */  
/* 1194:1194 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1195:1195 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1196:1196 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1197:1197 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1198:1198 */    BufferChecks.checkDirect(row);
/* 1199:1199 */    BufferChecks.checkDirect(column);
/* 1200:1200 */    BufferChecks.checkDirect(span);
/* 1201:1201 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1202:     */  }
/* 1203:     */  
/* 1204:1204 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1205:1205 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1206:1206 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1207:1207 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1208:1208 */    BufferChecks.checkDirect(row);
/* 1209:1209 */    BufferChecks.checkDirect(column);
/* 1210:1210 */    BufferChecks.checkDirect(span);
/* 1211:1211 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1212:     */  }
/* 1213:     */  
/* 1214:1214 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1215:1215 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1216:1216 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1217:1217 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1218:1218 */    BufferChecks.checkDirect(row);
/* 1219:1219 */    BufferChecks.checkDirect(column);
/* 1220:1220 */    BufferChecks.checkDirect(span);
/* 1221:1221 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1222:     */  }
/* 1223:     */  
/* 1224:1224 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1225:1225 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1226:1226 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1227:1227 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1228:1228 */    BufferChecks.checkDirect(row);
/* 1229:1229 */    BufferChecks.checkDirect(column);
/* 1230:1230 */    BufferChecks.checkDirect(span);
/* 1231:1231 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1232:     */  }
/* 1233:     */  
/* 1234:1234 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1235:1235 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1236:1236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1237:1237 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1238:1238 */    BufferChecks.checkDirect(row);
/* 1239:1239 */    BufferChecks.checkDirect(column);
/* 1240:1240 */    BufferChecks.checkDirect(span);
/* 1241:1241 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1242:     */  }
/* 1243:     */  
/* 1244:1244 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1245:1245 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1246:1246 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1247:1247 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1248:1248 */    BufferChecks.checkDirect(row);
/* 1249:1249 */    BufferChecks.checkDirect(column);
/* 1250:1250 */    BufferChecks.checkDirect(span);
/* 1251:1251 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1252:     */  }
/* 1253:     */  
/* 1254:1254 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1255:1255 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1256:1256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1257:1257 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1258:1258 */    BufferChecks.checkDirect(row);
/* 1259:1259 */    BufferChecks.checkDirect(column);
/* 1260:1260 */    BufferChecks.checkDirect(span);
/* 1261:1261 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1262:     */  }
/* 1263:     */  
/* 1264:1264 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1265:1265 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1266:1266 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1267:1267 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1268:1268 */    BufferChecks.checkDirect(row);
/* 1269:1269 */    BufferChecks.checkDirect(column);
/* 1270:1270 */    BufferChecks.checkDirect(span);
/* 1271:1271 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1272:     */  }
/* 1273:     */  
/* 1274:1274 */  public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1275:1275 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1276:1276 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1277:1277 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1278:1278 */    BufferChecks.checkDirect(row);
/* 1279:1279 */    BufferChecks.checkDirect(column);
/* 1280:1280 */    BufferChecks.checkDirect(span);
/* 1281:1281 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1282:     */  }
/* 1283:     */  
/* 1284:1284 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1285:1285 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1286:1286 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1287:1287 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1288:1288 */    BufferChecks.checkDirect(row);
/* 1289:1289 */    BufferChecks.checkDirect(column);
/* 1290:1290 */    BufferChecks.checkDirect(span);
/* 1291:1291 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1292:     */  }
/* 1293:     */  
/* 1294:1294 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1295:1295 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1296:1296 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1297:1297 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1298:1298 */    BufferChecks.checkDirect(row);
/* 1299:1299 */    BufferChecks.checkDirect(column);
/* 1300:1300 */    BufferChecks.checkDirect(span);
/* 1301:1301 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1302:     */  }
/* 1303:     */  
/* 1304:1304 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1305:1305 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1306:1306 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1307:1307 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1308:1308 */    BufferChecks.checkDirect(row);
/* 1309:1309 */    BufferChecks.checkDirect(column);
/* 1310:1310 */    BufferChecks.checkDirect(span);
/* 1311:1311 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1312:     */  }
/* 1313:     */  
/* 1314:1314 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1315:1315 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1316:1316 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1317:1317 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1318:1318 */    BufferChecks.checkDirect(row);
/* 1319:1319 */    BufferChecks.checkDirect(column);
/* 1320:1320 */    BufferChecks.checkDirect(span);
/* 1321:1321 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1322:     */  }
/* 1323:     */  
/* 1324:1324 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1325:1325 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1326:1326 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1327:1327 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1328:1328 */    BufferChecks.checkDirect(row);
/* 1329:1329 */    BufferChecks.checkDirect(column);
/* 1330:1330 */    BufferChecks.checkDirect(span);
/* 1331:1331 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1332:     */  }
/* 1333:     */  
/* 1334:1334 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1335:1335 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1336:1336 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1337:1337 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1338:1338 */    BufferChecks.checkDirect(row);
/* 1339:1339 */    BufferChecks.checkDirect(column);
/* 1340:1340 */    BufferChecks.checkDirect(span);
/* 1341:1341 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1342:     */  }
/* 1343:     */  
/* 1344:1344 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1345:1345 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1346:1346 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1347:1347 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1348:1348 */    BufferChecks.checkDirect(row);
/* 1349:1349 */    BufferChecks.checkDirect(column);
/* 1350:1350 */    BufferChecks.checkDirect(span);
/* 1351:1351 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1352:     */  }
/* 1353:     */  
/* 1354:1354 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1355:1355 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1356:1356 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1357:1357 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1358:1358 */    BufferChecks.checkDirect(row);
/* 1359:1359 */    BufferChecks.checkDirect(column);
/* 1360:1360 */    BufferChecks.checkDirect(span);
/* 1361:1361 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1362:     */  }
/* 1363:     */  
/* 1364:1364 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1365:1365 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1366:1366 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1367:1367 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1368:1368 */    BufferChecks.checkDirect(row);
/* 1369:1369 */    BufferChecks.checkDirect(column);
/* 1370:1370 */    BufferChecks.checkDirect(span);
/* 1371:1371 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1372:     */  }
/* 1373:     */  
/* 1374:1374 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1375:1375 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1376:1376 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1377:1377 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1378:1378 */    BufferChecks.checkDirect(row);
/* 1379:1379 */    BufferChecks.checkDirect(column);
/* 1380:1380 */    BufferChecks.checkDirect(span);
/* 1381:1381 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1382:     */  }
/* 1383:     */  
/* 1384:1384 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1385:1385 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1386:1386 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1387:1387 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1388:1388 */    BufferChecks.checkDirect(row);
/* 1389:1389 */    BufferChecks.checkDirect(column);
/* 1390:1390 */    BufferChecks.checkDirect(span);
/* 1391:1391 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1392:     */  }
/* 1393:     */  
/* 1394:1394 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1395:1395 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1396:1396 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1397:1397 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1398:1398 */    BufferChecks.checkDirect(row);
/* 1399:1399 */    BufferChecks.checkDirect(column);
/* 1400:1400 */    BufferChecks.checkDirect(span);
/* 1401:1401 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1402:     */  }
/* 1403:     */  
/* 1404:1404 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1405:1405 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1406:1406 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1407:1407 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1408:1408 */    BufferChecks.checkDirect(row);
/* 1409:1409 */    BufferChecks.checkDirect(column);
/* 1410:1410 */    BufferChecks.checkDirect(span);
/* 1411:1411 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1412:     */  }
/* 1413:     */  
/* 1414:1414 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1415:1415 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1416:1416 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1417:1417 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1418:1418 */    BufferChecks.checkDirect(row);
/* 1419:1419 */    BufferChecks.checkDirect(column);
/* 1420:1420 */    BufferChecks.checkDirect(span);
/* 1421:1421 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1422:     */  }
/* 1423:     */  
/* 1424:1424 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1425:1425 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1426:1426 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1427:1427 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1428:1428 */    BufferChecks.checkDirect(row);
/* 1429:1429 */    BufferChecks.checkDirect(column);
/* 1430:1430 */    BufferChecks.checkDirect(span);
/* 1431:1431 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1432:     */  }
/* 1433:     */  
/* 1434:1434 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1435:1435 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1436:1436 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1437:1437 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1438:1438 */    BufferChecks.checkDirect(row);
/* 1439:1439 */    BufferChecks.checkDirect(column);
/* 1440:1440 */    BufferChecks.checkDirect(span);
/* 1441:1441 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1442:     */  }
/* 1443:     */  
/* 1444:1444 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1445:1445 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1446:1446 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1447:1447 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1448:1448 */    BufferChecks.checkDirect(row);
/* 1449:1449 */    BufferChecks.checkDirect(column);
/* 1450:1450 */    BufferChecks.checkDirect(span);
/* 1451:1451 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1452:     */  }
/* 1453:     */  
/* 1454:1454 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1455:1455 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1456:1456 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1457:1457 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1458:1458 */    BufferChecks.checkDirect(row);
/* 1459:1459 */    BufferChecks.checkDirect(column);
/* 1460:1460 */    BufferChecks.checkDirect(span);
/* 1461:1461 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1462:     */  }
/* 1463:     */  
/* 1464:1464 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1465:1465 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1466:1466 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1467:1467 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1468:1468 */    BufferChecks.checkDirect(row);
/* 1469:1469 */    BufferChecks.checkDirect(column);
/* 1470:1470 */    BufferChecks.checkDirect(span);
/* 1471:1471 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1472:     */  }
/* 1473:     */  
/* 1474:1474 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1475:1475 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1476:1476 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1477:1477 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1478:1478 */    BufferChecks.checkDirect(row);
/* 1479:1479 */    BufferChecks.checkDirect(column);
/* 1480:1480 */    BufferChecks.checkDirect(span);
/* 1481:1481 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1482:     */  }
/* 1483:     */  
/* 1484:1484 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1485:1485 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1486:1486 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1487:1487 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1488:1488 */    BufferChecks.checkDirect(row);
/* 1489:1489 */    BufferChecks.checkDirect(column);
/* 1490:1490 */    BufferChecks.checkDirect(span);
/* 1491:1491 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1492:     */  }
/* 1493:     */  
/* 1494:1494 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1495:1495 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1496:1496 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1497:1497 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1498:1498 */    BufferChecks.checkDirect(row);
/* 1499:1499 */    BufferChecks.checkDirect(column);
/* 1500:1500 */    BufferChecks.checkDirect(span);
/* 1501:1501 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1502:     */  }
/* 1503:     */  
/* 1504:1504 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, FloatBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1505:1505 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1506:1506 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1507:1507 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1508:1508 */    BufferChecks.checkDirect(row);
/* 1509:1509 */    BufferChecks.checkDirect(column);
/* 1510:1510 */    BufferChecks.checkDirect(span);
/* 1511:1511 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1512:     */  }
/* 1513:     */  
/* 1514:1514 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1515:1515 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1516:1516 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1517:1517 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1518:1518 */    BufferChecks.checkDirect(row);
/* 1519:1519 */    BufferChecks.checkDirect(column);
/* 1520:1520 */    BufferChecks.checkDirect(span);
/* 1521:1521 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1522:     */  }
/* 1523:     */  
/* 1524:1524 */  public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1525:1525 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1526:1526 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1527:1527 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1528:1528 */    BufferChecks.checkDirect(row);
/* 1529:1529 */    BufferChecks.checkDirect(column);
/* 1530:1530 */    BufferChecks.checkDirect(span);
/* 1531:1531 */    nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer); }
/* 1532:     */  
/* 1533:     */  static native void nglGetnSeparableFilterARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, long paramLong2, long paramLong3, long paramLong4);
/* 1534:     */  
/* 1535:1535 */  public static void glGetnSeparableFilterARB(int target, int format, int type, int row_rowBufSize, long row_buffer_offset, int column_columnBufSize, long column_buffer_offset, long span_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1536:1536 */    long function_pointer = caps.glGetnSeparableFilterARB;
/* 1537:1537 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1538:1538 */    GLChecks.ensurePackPBOenabled(caps);
/* 1539:1539 */    nglGetnSeparableFilterARBBO(target, format, type, row_rowBufSize, row_buffer_offset, column_columnBufSize, column_buffer_offset, span_buffer_offset, function_pointer);
/* 1540:     */  }
/* 1541:     */  
/* 1542:     */  static native void nglGetnSeparableFilterARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, long paramLong2, long paramLong3, long paramLong4);
/* 1543:     */  
/* 1544:1544 */  public static void glGetnHistogramARB(int target, boolean reset, int format, int type, ByteBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1545:1545 */    long function_pointer = caps.glGetnHistogramARB;
/* 1546:1546 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1547:1547 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1548:1548 */    BufferChecks.checkDirect(values);
/* 1549:1549 */    nglGetnHistogramARB(target, reset, format, type, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/* 1550:     */  }
/* 1551:     */  
/* 1552:1552 */  public static void glGetnHistogramARB(int target, boolean reset, int format, int type, DoubleBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1553:1553 */    long function_pointer = caps.glGetnHistogramARB;
/* 1554:1554 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1555:1555 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1556:1556 */    BufferChecks.checkDirect(values);
/* 1557:1557 */    nglGetnHistogramARB(target, reset, format, type, values.remaining() << 3, MemoryUtil.getAddress(values), function_pointer);
/* 1558:     */  }
/* 1559:     */  
/* 1560:1560 */  public static void glGetnHistogramARB(int target, boolean reset, int format, int type, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1561:1561 */    long function_pointer = caps.glGetnHistogramARB;
/* 1562:1562 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1563:1563 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1564:1564 */    BufferChecks.checkDirect(values);
/* 1565:1565 */    nglGetnHistogramARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/* 1566:     */  }
/* 1567:     */  
/* 1568:1568 */  public static void glGetnHistogramARB(int target, boolean reset, int format, int type, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1569:1569 */    long function_pointer = caps.glGetnHistogramARB;
/* 1570:1570 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1571:1571 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1572:1572 */    BufferChecks.checkDirect(values);
/* 1573:1573 */    nglGetnHistogramARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/* 1574:     */  }
/* 1575:     */  
/* 1576:1576 */  public static void glGetnHistogramARB(int target, boolean reset, int format, int type, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1577:1577 */    long function_pointer = caps.glGetnHistogramARB;
/* 1578:1578 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1579:1579 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1580:1580 */    BufferChecks.checkDirect(values);
/* 1581:1581 */    nglGetnHistogramARB(target, reset, format, type, values.remaining() << 1, MemoryUtil.getAddress(values), function_pointer); }
/* 1582:     */  
/* 1583:     */  static native void nglGetnHistogramARB(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1584:     */  
/* 1585:1585 */  public static void glGetnHistogramARB(int target, boolean reset, int format, int type, int values_bufSize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1586:1586 */    long function_pointer = caps.glGetnHistogramARB;
/* 1587:1587 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1588:1588 */    GLChecks.ensurePackPBOenabled(caps);
/* 1589:1589 */    nglGetnHistogramARBBO(target, reset, format, type, values_bufSize, values_buffer_offset, function_pointer);
/* 1590:     */  }
/* 1591:     */  
/* 1592:     */  static native void nglGetnHistogramARBBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1593:     */  
/* 1594:1594 */  public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, ByteBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1595:1595 */    long function_pointer = caps.glGetnMinmaxARB;
/* 1596:1596 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1597:1597 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1598:1598 */    BufferChecks.checkDirect(values);
/* 1599:1599 */    nglGetnMinmaxARB(target, reset, format, type, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/* 1600:     */  }
/* 1601:     */  
/* 1602:1602 */  public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, DoubleBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1603:1603 */    long function_pointer = caps.glGetnMinmaxARB;
/* 1604:1604 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1605:1605 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1606:1606 */    BufferChecks.checkDirect(values);
/* 1607:1607 */    nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 3, MemoryUtil.getAddress(values), function_pointer);
/* 1608:     */  }
/* 1609:     */  
/* 1610:1610 */  public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1611:1611 */    long function_pointer = caps.glGetnMinmaxARB;
/* 1612:1612 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1613:1613 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1614:1614 */    BufferChecks.checkDirect(values);
/* 1615:1615 */    nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/* 1616:     */  }
/* 1617:     */  
/* 1618:1618 */  public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1619:1619 */    long function_pointer = caps.glGetnMinmaxARB;
/* 1620:1620 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1621:1621 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1622:1622 */    BufferChecks.checkDirect(values);
/* 1623:1623 */    nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/* 1624:     */  }
/* 1625:     */  
/* 1626:1626 */  public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1627:1627 */    long function_pointer = caps.glGetnMinmaxARB;
/* 1628:1628 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1629:1629 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1630:1630 */    BufferChecks.checkDirect(values);
/* 1631:1631 */    nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 1, MemoryUtil.getAddress(values), function_pointer); }
/* 1632:     */  
/* 1633:     */  static native void nglGetnMinmaxARB(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1634:     */  
/* 1635:1635 */  public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, int values_bufSize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1636:1636 */    long function_pointer = caps.glGetnMinmaxARB;
/* 1637:1637 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1638:1638 */    GLChecks.ensurePackPBOenabled(caps);
/* 1639:1639 */    nglGetnMinmaxARBBO(target, reset, format, type, values_bufSize, values_buffer_offset, function_pointer);
/* 1640:     */  }
/* 1641:     */  
/* 1642:     */  static native void nglGetnMinmaxARBBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 1643:     */  
/* 1644:1644 */  public static void glGetnCompressedTexImageARB(int target, int lod, ByteBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1645:1645 */    long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1646:1646 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1647:1647 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1648:1648 */    BufferChecks.checkDirect(img);
/* 1649:1649 */    nglGetnCompressedTexImageARB(target, lod, img.remaining(), MemoryUtil.getAddress(img), function_pointer);
/* 1650:     */  }
/* 1651:     */  
/* 1652:1652 */  public static void glGetnCompressedTexImageARB(int target, int lod, IntBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1653:1653 */    long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1654:1654 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1655:1655 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1656:1656 */    BufferChecks.checkDirect(img);
/* 1657:1657 */    nglGetnCompressedTexImageARB(target, lod, img.remaining() << 2, MemoryUtil.getAddress(img), function_pointer);
/* 1658:     */  }
/* 1659:     */  
/* 1660:1660 */  public static void glGetnCompressedTexImageARB(int target, int lod, ShortBuffer img) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1661:1661 */    long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1662:1662 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1663:1663 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1664:1664 */    BufferChecks.checkDirect(img);
/* 1665:1665 */    nglGetnCompressedTexImageARB(target, lod, img.remaining() << 1, MemoryUtil.getAddress(img), function_pointer); }
/* 1666:     */  
/* 1667:     */  static native void nglGetnCompressedTexImageARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1668:     */  
/* 1669:1669 */  public static void glGetnCompressedTexImageARB(int target, int lod, int img_bufSize, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1670:1670 */    long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1671:1671 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1672:1672 */    GLChecks.ensurePackPBOenabled(caps);
/* 1673:1673 */    nglGetnCompressedTexImageARBBO(target, lod, img_bufSize, img_buffer_offset, function_pointer);
/* 1674:     */  }
/* 1675:     */  
/* 1676:     */  static native void nglGetnCompressedTexImageARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1677:     */  
/* 1678:1678 */  public static void glGetnUniformfvARB(int program, int location, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1679:1679 */    long function_pointer = caps.glGetnUniformfvARB;
/* 1680:1680 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1681:1681 */    BufferChecks.checkDirect(params);
/* 1682:1682 */    nglGetnUniformfvARB(program, location, params.remaining() << 2, MemoryUtil.getAddress(params), function_pointer);
/* 1683:     */  }
/* 1684:     */  
/* 1685:     */  static native void nglGetnUniformfvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1686:     */  
/* 1687:1687 */  public static void glGetnUniformivARB(int program, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1688:1688 */    long function_pointer = caps.glGetnUniformivARB;
/* 1689:1689 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1690:1690 */    BufferChecks.checkDirect(params);
/* 1691:1691 */    nglGetnUniformivARB(program, location, params.remaining() << 2, MemoryUtil.getAddress(params), function_pointer);
/* 1692:     */  }
/* 1693:     */  
/* 1694:     */  static native void nglGetnUniformivARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1695:     */  
/* 1696:1696 */  public static void glGetnUniformuivARB(int program, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1697:1697 */    long function_pointer = caps.glGetnUniformuivARB;
/* 1698:1698 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1699:1699 */    BufferChecks.checkDirect(params);
/* 1700:1700 */    nglGetnUniformuivARB(program, location, params.remaining() << 2, MemoryUtil.getAddress(params), function_pointer);
/* 1701:     */  }
/* 1702:     */  
/* 1703:     */  static native void nglGetnUniformuivARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1704:     */  
/* 1705:1705 */  public static void glGetnUniformdvARB(int program, int location, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1706:1706 */    long function_pointer = caps.glGetnUniformdvARB;
/* 1707:1707 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1708:1708 */    BufferChecks.checkDirect(params);
/* 1709:1709 */    nglGetnUniformdvARB(program, location, params.remaining() << 3, MemoryUtil.getAddress(params), function_pointer);
/* 1710:     */  }
/* 1711:     */  
/* 1712:     */  static native void nglGetnUniformdvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 1713:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBRobustness
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */