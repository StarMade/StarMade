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
/*   17:     */public final class ARBImaging
/*   18:     */{
/*   19:     */  public static final int GL_CONSTANT_COLOR = 32769;
/*   20:     */  public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
/*   21:     */  public static final int GL_CONSTANT_ALPHA = 32771;
/*   22:     */  public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
/*   23:     */  public static final int GL_BLEND_COLOR = 32773;
/*   24:     */  public static final int GL_FUNC_ADD = 32774;
/*   25:     */  public static final int GL_MIN = 32775;
/*   26:     */  public static final int GL_MAX = 32776;
/*   27:     */  public static final int GL_BLEND_EQUATION = 32777;
/*   28:     */  public static final int GL_FUNC_SUBTRACT = 32778;
/*   29:     */  public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
/*   30:     */  public static final int GL_COLOR_MATRIX = 32945;
/*   31:     */  public static final int GL_COLOR_MATRIX_STACK_DEPTH = 32946;
/*   32:     */  public static final int GL_MAX_COLOR_MATRIX_STACK_DEPTH = 32947;
/*   33:     */  public static final int GL_POST_COLOR_MATRIX_RED_SCALE = 32948;
/*   34:     */  public static final int GL_POST_COLOR_MATRIX_GREEN_SCALE = 32949;
/*   35:     */  public static final int GL_POST_COLOR_MATRIX_BLUE_SCALE = 32950;
/*   36:     */  public static final int GL_POST_COLOR_MATRIX_ALPHA_SCALE = 32951;
/*   37:     */  public static final int GL_POST_COLOR_MATRIX_RED_BIAS = 32952;
/*   38:     */  public static final int GL_POST_COLOR_MATRIX_GREEN_BIAS = 32953;
/*   39:     */  public static final int GL_POST_COLOR_MATRIX_BLUE_BIAS = 32954;
/*   40:     */  public static final int GL_POST_COLOR_MATRIX_ALPHA_BIAS = 32955;
/*   41:     */  public static final int GL_COLOR_TABLE = 32976;
/*   42:     */  public static final int GL_POST_CONVOLUTION_COLOR_TABLE = 32977;
/*   43:     */  public static final int GL_POST_COLOR_MATRIX_COLOR_TABLE = 32978;
/*   44:     */  public static final int GL_PROXY_COLOR_TABLE = 32979;
/*   45:     */  public static final int GL_PROXY_POST_CONVOLUTION_COLOR_TABLE = 32980;
/*   46:     */  public static final int GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE = 32981;
/*   47:     */  public static final int GL_COLOR_TABLE_SCALE = 32982;
/*   48:     */  public static final int GL_COLOR_TABLE_BIAS = 32983;
/*   49:     */  public static final int GL_COLOR_TABLE_FORMAT = 32984;
/*   50:     */  public static final int GL_COLOR_TABLE_WIDTH = 32985;
/*   51:     */  public static final int GL_COLOR_TABLE_RED_SIZE = 32986;
/*   52:     */  public static final int GL_COLOR_TABLE_GREEN_SIZE = 32987;
/*   53:     */  public static final int GL_COLOR_TABLE_BLUE_SIZE = 32988;
/*   54:     */  public static final int GL_COLOR_TABLE_ALPHA_SIZE = 32989;
/*   55:     */  public static final int GL_COLOR_TABLE_LUMINANCE_SIZE = 32990;
/*   56:     */  public static final int GL_COLOR_TABLE_INTENSITY_SIZE = 32991;
/*   57:     */  public static final int GL_CONVOLUTION_1D = 32784;
/*   58:     */  public static final int GL_CONVOLUTION_2D = 32785;
/*   59:     */  public static final int GL_SEPARABLE_2D = 32786;
/*   60:     */  public static final int GL_CONVOLUTION_BORDER_MODE = 32787;
/*   61:     */  public static final int GL_CONVOLUTION_FILTER_SCALE = 32788;
/*   62:     */  public static final int GL_CONVOLUTION_FILTER_BIAS = 32789;
/*   63:     */  public static final int GL_REDUCE = 32790;
/*   64:     */  public static final int GL_CONVOLUTION_FORMAT = 32791;
/*   65:     */  public static final int GL_CONVOLUTION_WIDTH = 32792;
/*   66:     */  public static final int GL_CONVOLUTION_HEIGHT = 32793;
/*   67:     */  public static final int GL_MAX_CONVOLUTION_WIDTH = 32794;
/*   68:     */  public static final int GL_MAX_CONVOLUTION_HEIGHT = 32795;
/*   69:     */  public static final int GL_POST_CONVOLUTION_RED_SCALE = 32796;
/*   70:     */  public static final int GL_POST_CONVOLUTION_GREEN_SCALE = 32797;
/*   71:     */  public static final int GL_POST_CONVOLUTION_BLUE_SCALE = 32798;
/*   72:     */  public static final int GL_POST_CONVOLUTION_ALPHA_SCALE = 32799;
/*   73:     */  public static final int GL_POST_CONVOLUTION_RED_BIAS = 32800;
/*   74:     */  public static final int GL_POST_CONVOLUTION_GREEN_BIAS = 32801;
/*   75:     */  public static final int GL_POST_CONVOLUTION_BLUE_BIAS = 32802;
/*   76:     */  public static final int GL_POST_CONVOLUTION_ALPHA_BIAS = 32803;
/*   77:     */  public static final int GL_IGNORE_BORDER = 33104;
/*   78:     */  public static final int GL_CONSTANT_BORDER = 33105;
/*   79:     */  public static final int GL_REPLICATE_BORDER = 33107;
/*   80:     */  public static final int GL_CONVOLUTION_BORDER_COLOR = 33108;
/*   81:     */  public static final int GL_HISTOGRAM = 32804;
/*   82:     */  public static final int GL_PROXY_HISTOGRAM = 32805;
/*   83:     */  public static final int GL_HISTOGRAM_WIDTH = 32806;
/*   84:     */  public static final int GL_HISTOGRAM_FORMAT = 32807;
/*   85:     */  public static final int GL_HISTOGRAM_RED_SIZE = 32808;
/*   86:     */  public static final int GL_HISTOGRAM_GREEN_SIZE = 32809;
/*   87:     */  public static final int GL_HISTOGRAM_BLUE_SIZE = 32810;
/*   88:     */  public static final int GL_HISTOGRAM_ALPHA_SIZE = 32811;
/*   89:     */  public static final int GL_HISTOGRAM_LUMINANCE_SIZE = 32812;
/*   90:     */  public static final int GL_HISTOGRAM_SINK = 32813;
/*   91:     */  public static final int GL_MINMAX = 32814;
/*   92:     */  public static final int GL_MINMAX_FORMAT = 32815;
/*   93:     */  public static final int GL_MINMAX_SINK = 32816;
/*   94:     */  public static final int GL_TABLE_TOO_LARGE = 32817;
/*   95:     */  
/*   96:     */  public static void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data)
/*   97:     */  {
/*   98:  98 */    ContextCapabilities caps = GLContext.getCapabilities();
/*   99:  99 */    long function_pointer = caps.glColorTable;
/*  100: 100 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  101: 101 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  102: 102 */    BufferChecks.checkBuffer(data, 256);
/*  103: 103 */    nglColorTable(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  104:     */  }
/*  105:     */  
/*  106: 106 */  public static void glColorTable(int target, int internalFormat, int width, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  107: 107 */    long function_pointer = caps.glColorTable;
/*  108: 108 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  109: 109 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  110: 110 */    BufferChecks.checkBuffer(data, 256);
/*  111: 111 */    nglColorTable(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  112:     */  }
/*  113:     */  
/*  114: 114 */  public static void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  115: 115 */    long function_pointer = caps.glColorTable;
/*  116: 116 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  117: 117 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  118: 118 */    BufferChecks.checkBuffer(data, 256);
/*  119: 119 */    nglColorTable(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer); }
/*  120:     */  
/*  121:     */  static native void nglColorTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  122:     */  
/*  123: 123 */  public static void glColorTable(int target, int internalFormat, int width, int format, int type, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  124: 124 */    long function_pointer = caps.glColorTable;
/*  125: 125 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  126: 126 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  127: 127 */    nglColorTableBO(target, internalFormat, width, format, type, data_buffer_offset, function_pointer);
/*  128:     */  }
/*  129:     */  
/*  130:     */  static native void nglColorTableBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  131:     */  
/*  132: 132 */  public static void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  133: 133 */    long function_pointer = caps.glColorSubTable;
/*  134: 134 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  135: 135 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  136: 136 */    BufferChecks.checkBuffer(data, 256);
/*  137: 137 */    nglColorSubTable(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  138:     */  }
/*  139:     */  
/*  140: 140 */  public static void glColorSubTable(int target, int start, int count, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  141: 141 */    long function_pointer = caps.glColorSubTable;
/*  142: 142 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  143: 143 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  144: 144 */    BufferChecks.checkBuffer(data, 256);
/*  145: 145 */    nglColorSubTable(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  146:     */  }
/*  147:     */  
/*  148: 148 */  public static void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  149: 149 */    long function_pointer = caps.glColorSubTable;
/*  150: 150 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  151: 151 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  152: 152 */    BufferChecks.checkBuffer(data, 256);
/*  153: 153 */    nglColorSubTable(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer); }
/*  154:     */  
/*  155:     */  static native void nglColorSubTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  156:     */  
/*  157: 157 */  public static void glColorSubTable(int target, int start, int count, int format, int type, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  158: 158 */    long function_pointer = caps.glColorSubTable;
/*  159: 159 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  160: 160 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  161: 161 */    nglColorSubTableBO(target, start, count, format, type, data_buffer_offset, function_pointer);
/*  162:     */  }
/*  163:     */  
/*  164:     */  static native void nglColorSubTableBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  165:     */  
/*  166: 166 */  public static void glColorTableParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  167: 167 */    long function_pointer = caps.glColorTableParameteriv;
/*  168: 168 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  169: 169 */    BufferChecks.checkBuffer(params, 4);
/*  170: 170 */    nglColorTableParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  171:     */  }
/*  172:     */  
/*  173:     */  static native void nglColorTableParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  174:     */  
/*  175: 175 */  public static void glColorTableParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  176: 176 */    long function_pointer = caps.glColorTableParameterfv;
/*  177: 177 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  178: 178 */    BufferChecks.checkBuffer(params, 4);
/*  179: 179 */    nglColorTableParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  180:     */  }
/*  181:     */  
/*  182:     */  static native void nglColorTableParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  183:     */  
/*  184: 184 */  public static void glCopyColorSubTable(int target, int start, int x, int y, int width) { ContextCapabilities caps = GLContext.getCapabilities();
/*  185: 185 */    long function_pointer = caps.glCopyColorSubTable;
/*  186: 186 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  187: 187 */    nglCopyColorSubTable(target, start, x, y, width, function_pointer);
/*  188:     */  }
/*  189:     */  
/*  190:     */  static native void nglCopyColorSubTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  191:     */  
/*  192: 192 */  public static void glCopyColorTable(int target, int internalformat, int x, int y, int width) { ContextCapabilities caps = GLContext.getCapabilities();
/*  193: 193 */    long function_pointer = caps.glCopyColorTable;
/*  194: 194 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  195: 195 */    nglCopyColorTable(target, internalformat, x, y, width, function_pointer);
/*  196:     */  }
/*  197:     */  
/*  198:     */  static native void nglCopyColorTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  199:     */  
/*  200: 200 */  public static void glGetColorTable(int target, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  201: 201 */    long function_pointer = caps.glGetColorTable;
/*  202: 202 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  203: 203 */    BufferChecks.checkBuffer(data, 256);
/*  204: 204 */    nglGetColorTable(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  205:     */  }
/*  206:     */  
/*  207: 207 */  public static void glGetColorTable(int target, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  208: 208 */    long function_pointer = caps.glGetColorTable;
/*  209: 209 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  210: 210 */    BufferChecks.checkBuffer(data, 256);
/*  211: 211 */    nglGetColorTable(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  212:     */  }
/*  213:     */  
/*  214: 214 */  public static void glGetColorTable(int target, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  215: 215 */    long function_pointer = caps.glGetColorTable;
/*  216: 216 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  217: 217 */    BufferChecks.checkBuffer(data, 256);
/*  218: 218 */    nglGetColorTable(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  219:     */  }
/*  220:     */  
/*  221:     */  static native void nglGetColorTable(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  222:     */  
/*  223: 223 */  public static void glGetColorTableParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  224: 224 */    long function_pointer = caps.glGetColorTableParameteriv;
/*  225: 225 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  226: 226 */    BufferChecks.checkBuffer(params, 4);
/*  227: 227 */    nglGetColorTableParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  228:     */  }
/*  229:     */  
/*  230:     */  static native void nglGetColorTableParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  231:     */  
/*  232: 232 */  public static void glGetColorTableParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  233: 233 */    long function_pointer = caps.glGetColorTableParameterfv;
/*  234: 234 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  235: 235 */    BufferChecks.checkBuffer(params, 4);
/*  236: 236 */    nglGetColorTableParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  237:     */  }
/*  238:     */  
/*  239:     */  static native void nglGetColorTableParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  240:     */  
/*  241: 241 */  public static void glBlendEquation(int mode) { GL14.glBlendEquation(mode); }
/*  242:     */  
/*  243:     */  public static void glBlendColor(float red, float green, float blue, float alpha)
/*  244:     */  {
/*  245: 245 */    GL14.glBlendColor(red, green, blue, alpha);
/*  246:     */  }
/*  247:     */  
/*  248:     */  public static void glHistogram(int target, int width, int internalformat, boolean sink) {
/*  249: 249 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  250: 250 */    long function_pointer = caps.glHistogram;
/*  251: 251 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  252: 252 */    nglHistogram(target, width, internalformat, sink, function_pointer);
/*  253:     */  }
/*  254:     */  
/*  255:     */  static native void nglHistogram(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong);
/*  256:     */  
/*  257: 257 */  public static void glResetHistogram(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/*  258: 258 */    long function_pointer = caps.glResetHistogram;
/*  259: 259 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  260: 260 */    nglResetHistogram(target, function_pointer);
/*  261:     */  }
/*  262:     */  
/*  263:     */  static native void nglResetHistogram(int paramInt, long paramLong);
/*  264:     */  
/*  265: 265 */  public static void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  266: 266 */    long function_pointer = caps.glGetHistogram;
/*  267: 267 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  268: 268 */    GLChecks.ensurePackPBOdisabled(caps);
/*  269: 269 */    BufferChecks.checkBuffer(values, 256);
/*  270: 270 */    nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*  271:     */  }
/*  272:     */  
/*  273: 273 */  public static void glGetHistogram(int target, boolean reset, int format, int type, DoubleBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  274: 274 */    long function_pointer = caps.glGetHistogram;
/*  275: 275 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  276: 276 */    GLChecks.ensurePackPBOdisabled(caps);
/*  277: 277 */    BufferChecks.checkBuffer(values, 256);
/*  278: 278 */    nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*  279:     */  }
/*  280:     */  
/*  281: 281 */  public static void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  282: 282 */    long function_pointer = caps.glGetHistogram;
/*  283: 283 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  284: 284 */    GLChecks.ensurePackPBOdisabled(caps);
/*  285: 285 */    BufferChecks.checkBuffer(values, 256);
/*  286: 286 */    nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*  287:     */  }
/*  288:     */  
/*  289: 289 */  public static void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  290: 290 */    long function_pointer = caps.glGetHistogram;
/*  291: 291 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  292: 292 */    GLChecks.ensurePackPBOdisabled(caps);
/*  293: 293 */    BufferChecks.checkBuffer(values, 256);
/*  294: 294 */    nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*  295:     */  }
/*  296:     */  
/*  297: 297 */  public static void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  298: 298 */    long function_pointer = caps.glGetHistogram;
/*  299: 299 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  300: 300 */    GLChecks.ensurePackPBOdisabled(caps);
/*  301: 301 */    BufferChecks.checkBuffer(values, 256);
/*  302: 302 */    nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer); }
/*  303:     */  
/*  304:     */  static native void nglGetHistogram(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  305:     */  
/*  306: 306 */  public static void glGetHistogram(int target, boolean reset, int format, int type, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  307: 307 */    long function_pointer = caps.glGetHistogram;
/*  308: 308 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  309: 309 */    GLChecks.ensurePackPBOenabled(caps);
/*  310: 310 */    nglGetHistogramBO(target, reset, format, type, values_buffer_offset, function_pointer);
/*  311:     */  }
/*  312:     */  
/*  313:     */  static native void nglGetHistogramBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  314:     */  
/*  315: 315 */  public static void glGetHistogramParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  316: 316 */    long function_pointer = caps.glGetHistogramParameterfv;
/*  317: 317 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  318: 318 */    BufferChecks.checkBuffer(params, 256);
/*  319: 319 */    nglGetHistogramParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  320:     */  }
/*  321:     */  
/*  322:     */  static native void nglGetHistogramParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  323:     */  
/*  324: 324 */  public static void glGetHistogramParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  325: 325 */    long function_pointer = caps.glGetHistogramParameteriv;
/*  326: 326 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  327: 327 */    BufferChecks.checkBuffer(params, 256);
/*  328: 328 */    nglGetHistogramParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  329:     */  }
/*  330:     */  
/*  331:     */  static native void nglGetHistogramParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  332:     */  
/*  333: 333 */  public static void glMinmax(int target, int internalformat, boolean sink) { ContextCapabilities caps = GLContext.getCapabilities();
/*  334: 334 */    long function_pointer = caps.glMinmax;
/*  335: 335 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  336: 336 */    nglMinmax(target, internalformat, sink, function_pointer);
/*  337:     */  }
/*  338:     */  
/*  339:     */  static native void nglMinmax(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong);
/*  340:     */  
/*  341: 341 */  public static void glResetMinmax(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/*  342: 342 */    long function_pointer = caps.glResetMinmax;
/*  343: 343 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  344: 344 */    nglResetMinmax(target, function_pointer);
/*  345:     */  }
/*  346:     */  
/*  347:     */  static native void nglResetMinmax(int paramInt, long paramLong);
/*  348:     */  
/*  349: 349 */  public static void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  350: 350 */    long function_pointer = caps.glGetMinmax;
/*  351: 351 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  352: 352 */    GLChecks.ensurePackPBOdisabled(caps);
/*  353: 353 */    BufferChecks.checkBuffer(values, 4);
/*  354: 354 */    nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*  355:     */  }
/*  356:     */  
/*  357: 357 */  public static void glGetMinmax(int target, boolean reset, int format, int types, DoubleBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  358: 358 */    long function_pointer = caps.glGetMinmax;
/*  359: 359 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  360: 360 */    GLChecks.ensurePackPBOdisabled(caps);
/*  361: 361 */    BufferChecks.checkBuffer(values, 4);
/*  362: 362 */    nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*  363:     */  }
/*  364:     */  
/*  365: 365 */  public static void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  366: 366 */    long function_pointer = caps.glGetMinmax;
/*  367: 367 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  368: 368 */    GLChecks.ensurePackPBOdisabled(caps);
/*  369: 369 */    BufferChecks.checkBuffer(values, 4);
/*  370: 370 */    nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*  371:     */  }
/*  372:     */  
/*  373: 373 */  public static void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  374: 374 */    long function_pointer = caps.glGetMinmax;
/*  375: 375 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  376: 376 */    GLChecks.ensurePackPBOdisabled(caps);
/*  377: 377 */    BufferChecks.checkBuffer(values, 4);
/*  378: 378 */    nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*  379:     */  }
/*  380:     */  
/*  381: 381 */  public static void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  382: 382 */    long function_pointer = caps.glGetMinmax;
/*  383: 383 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  384: 384 */    GLChecks.ensurePackPBOdisabled(caps);
/*  385: 385 */    BufferChecks.checkBuffer(values, 4);
/*  386: 386 */    nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer); }
/*  387:     */  
/*  388:     */  static native void nglGetMinmax(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  389:     */  
/*  390: 390 */  public static void glGetMinmax(int target, boolean reset, int format, int types, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  391: 391 */    long function_pointer = caps.glGetMinmax;
/*  392: 392 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  393: 393 */    GLChecks.ensurePackPBOenabled(caps);
/*  394: 394 */    nglGetMinmaxBO(target, reset, format, types, values_buffer_offset, function_pointer);
/*  395:     */  }
/*  396:     */  
/*  397:     */  static native void nglGetMinmaxBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  398:     */  
/*  399: 399 */  public static void glGetMinmaxParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  400: 400 */    long function_pointer = caps.glGetMinmaxParameterfv;
/*  401: 401 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  402: 402 */    BufferChecks.checkBuffer(params, 4);
/*  403: 403 */    nglGetMinmaxParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  404:     */  }
/*  405:     */  
/*  406:     */  static native void nglGetMinmaxParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  407:     */  
/*  408: 408 */  public static void glGetMinmaxParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  409: 409 */    long function_pointer = caps.glGetMinmaxParameteriv;
/*  410: 410 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  411: 411 */    BufferChecks.checkBuffer(params, 4);
/*  412: 412 */    nglGetMinmaxParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  413:     */  }
/*  414:     */  
/*  415:     */  static native void nglGetMinmaxParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  416:     */  
/*  417: 417 */  public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  418: 418 */    long function_pointer = caps.glConvolutionFilter1D;
/*  419: 419 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  420: 420 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  421: 421 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  422: 422 */    nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  423:     */  }
/*  424:     */  
/*  425: 425 */  public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, DoubleBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  426: 426 */    long function_pointer = caps.glConvolutionFilter1D;
/*  427: 427 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  428: 428 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  429: 429 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  430: 430 */    nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  431:     */  }
/*  432:     */  
/*  433: 433 */  public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  434: 434 */    long function_pointer = caps.glConvolutionFilter1D;
/*  435: 435 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  436: 436 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  437: 437 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  438: 438 */    nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  439:     */  }
/*  440:     */  
/*  441: 441 */  public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  442: 442 */    long function_pointer = caps.glConvolutionFilter1D;
/*  443: 443 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  444: 444 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  445: 445 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  446: 446 */    nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  447:     */  }
/*  448:     */  
/*  449: 449 */  public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  450: 450 */    long function_pointer = caps.glConvolutionFilter1D;
/*  451: 451 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  452: 452 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  453: 453 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  454: 454 */    nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer); }
/*  455:     */  
/*  456:     */  static native void nglConvolutionFilter1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  457:     */  
/*  458: 458 */  public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  459: 459 */    long function_pointer = caps.glConvolutionFilter1D;
/*  460: 460 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  461: 461 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  462: 462 */    nglConvolutionFilter1DBO(target, internalformat, width, format, type, image_buffer_offset, function_pointer);
/*  463:     */  }
/*  464:     */  
/*  465:     */  static native void nglConvolutionFilter1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  466:     */  
/*  467: 467 */  public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  468: 468 */    long function_pointer = caps.glConvolutionFilter2D;
/*  469: 469 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  470: 470 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  471: 471 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
/*  472: 472 */    nglConvolutionFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  473:     */  }
/*  474:     */  
/*  475: 475 */  public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  476: 476 */    long function_pointer = caps.glConvolutionFilter2D;
/*  477: 477 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  478: 478 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  479: 479 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
/*  480: 480 */    nglConvolutionFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  481:     */  }
/*  482:     */  
/*  483: 483 */  public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  484: 484 */    long function_pointer = caps.glConvolutionFilter2D;
/*  485: 485 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  486: 486 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  487: 487 */    BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
/*  488: 488 */    nglConvolutionFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(image), function_pointer); }
/*  489:     */  
/*  490:     */  static native void nglConvolutionFilter2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*  491:     */  
/*  492: 492 */  public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  493: 493 */    long function_pointer = caps.glConvolutionFilter2D;
/*  494: 494 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  495: 495 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  496: 496 */    nglConvolutionFilter2DBO(target, internalformat, width, height, format, type, image_buffer_offset, function_pointer);
/*  497:     */  }
/*  498:     */  
/*  499:     */  static native void nglConvolutionFilter2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*  500:     */  
/*  501: 501 */  public static void glConvolutionParameterf(int target, int pname, float params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  502: 502 */    long function_pointer = caps.glConvolutionParameterf;
/*  503: 503 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  504: 504 */    nglConvolutionParameterf(target, pname, params, function_pointer);
/*  505:     */  }
/*  506:     */  
/*  507:     */  static native void nglConvolutionParameterf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*  508:     */  
/*  509: 509 */  public static void glConvolutionParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  510: 510 */    long function_pointer = caps.glConvolutionParameterfv;
/*  511: 511 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  512: 512 */    BufferChecks.checkBuffer(params, 4);
/*  513: 513 */    nglConvolutionParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  514:     */  }
/*  515:     */  
/*  516:     */  static native void nglConvolutionParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  517:     */  
/*  518: 518 */  public static void glConvolutionParameteri(int target, int pname, int params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  519: 519 */    long function_pointer = caps.glConvolutionParameteri;
/*  520: 520 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  521: 521 */    nglConvolutionParameteri(target, pname, params, function_pointer);
/*  522:     */  }
/*  523:     */  
/*  524:     */  static native void nglConvolutionParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  525:     */  
/*  526: 526 */  public static void glConvolutionParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  527: 527 */    long function_pointer = caps.glConvolutionParameteriv;
/*  528: 528 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  529: 529 */    BufferChecks.checkBuffer(params, 4);
/*  530: 530 */    nglConvolutionParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  531:     */  }
/*  532:     */  
/*  533:     */  static native void nglConvolutionParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  534:     */  
/*  535: 535 */  public static void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width) { ContextCapabilities caps = GLContext.getCapabilities();
/*  536: 536 */    long function_pointer = caps.glCopyConvolutionFilter1D;
/*  537: 537 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  538: 538 */    nglCopyConvolutionFilter1D(target, internalformat, x, y, width, function_pointer);
/*  539:     */  }
/*  540:     */  
/*  541:     */  static native void nglCopyConvolutionFilter1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  542:     */  
/*  543: 543 */  public static void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/*  544: 544 */    long function_pointer = caps.glCopyConvolutionFilter2D;
/*  545: 545 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  546: 546 */    nglCopyConvolutionFilter2D(target, internalformat, x, y, width, height, function_pointer);
/*  547:     */  }
/*  548:     */  
/*  549:     */  static native void nglCopyConvolutionFilter2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  550:     */  
/*  551: 551 */  public static void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  552: 552 */    long function_pointer = caps.glGetConvolutionFilter;
/*  553: 553 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  554: 554 */    GLChecks.ensurePackPBOdisabled(caps);
/*  555: 555 */    BufferChecks.checkDirect(image);
/*  556: 556 */    nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  557:     */  }
/*  558:     */  
/*  559: 559 */  public static void glGetConvolutionFilter(int target, int format, int type, DoubleBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  560: 560 */    long function_pointer = caps.glGetConvolutionFilter;
/*  561: 561 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  562: 562 */    GLChecks.ensurePackPBOdisabled(caps);
/*  563: 563 */    BufferChecks.checkDirect(image);
/*  564: 564 */    nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  565:     */  }
/*  566:     */  
/*  567: 567 */  public static void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  568: 568 */    long function_pointer = caps.glGetConvolutionFilter;
/*  569: 569 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  570: 570 */    GLChecks.ensurePackPBOdisabled(caps);
/*  571: 571 */    BufferChecks.checkDirect(image);
/*  572: 572 */    nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  573:     */  }
/*  574:     */  
/*  575: 575 */  public static void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  576: 576 */    long function_pointer = caps.glGetConvolutionFilter;
/*  577: 577 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  578: 578 */    GLChecks.ensurePackPBOdisabled(caps);
/*  579: 579 */    BufferChecks.checkDirect(image);
/*  580: 580 */    nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*  581:     */  }
/*  582:     */  
/*  583: 583 */  public static void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) { ContextCapabilities caps = GLContext.getCapabilities();
/*  584: 584 */    long function_pointer = caps.glGetConvolutionFilter;
/*  585: 585 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  586: 586 */    GLChecks.ensurePackPBOdisabled(caps);
/*  587: 587 */    BufferChecks.checkDirect(image);
/*  588: 588 */    nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer); }
/*  589:     */  
/*  590:     */  static native void nglGetConvolutionFilter(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  591:     */  
/*  592: 592 */  public static void glGetConvolutionFilter(int target, int format, int type, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  593: 593 */    long function_pointer = caps.glGetConvolutionFilter;
/*  594: 594 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  595: 595 */    GLChecks.ensurePackPBOenabled(caps);
/*  596: 596 */    nglGetConvolutionFilterBO(target, format, type, image_buffer_offset, function_pointer);
/*  597:     */  }
/*  598:     */  
/*  599:     */  static native void nglGetConvolutionFilterBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  600:     */  
/*  601: 601 */  public static void glGetConvolutionParameter(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  602: 602 */    long function_pointer = caps.glGetConvolutionParameterfv;
/*  603: 603 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  604: 604 */    BufferChecks.checkBuffer(params, 4);
/*  605: 605 */    nglGetConvolutionParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  606:     */  }
/*  607:     */  
/*  608:     */  static native void nglGetConvolutionParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  609:     */  
/*  610: 610 */  public static void glGetConvolutionParameter(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  611: 611 */    long function_pointer = caps.glGetConvolutionParameteriv;
/*  612: 612 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  613: 613 */    BufferChecks.checkBuffer(params, 4);
/*  614: 614 */    nglGetConvolutionParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  615:     */  }
/*  616:     */  
/*  617:     */  static native void nglGetConvolutionParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  618:     */  
/*  619: 619 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, ByteBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  620: 620 */    long function_pointer = caps.glSeparableFilter2D;
/*  621: 621 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  622: 622 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  623: 623 */    BufferChecks.checkDirect(row);
/*  624: 624 */    BufferChecks.checkDirect(column);
/*  625: 625 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  626:     */  }
/*  627:     */  
/*  628: 628 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, DoubleBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  629: 629 */    long function_pointer = caps.glSeparableFilter2D;
/*  630: 630 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  631: 631 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  632: 632 */    BufferChecks.checkDirect(row);
/*  633: 633 */    BufferChecks.checkDirect(column);
/*  634: 634 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  635:     */  }
/*  636:     */  
/*  637: 637 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, FloatBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  638: 638 */    long function_pointer = caps.glSeparableFilter2D;
/*  639: 639 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  640: 640 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  641: 641 */    BufferChecks.checkDirect(row);
/*  642: 642 */    BufferChecks.checkDirect(column);
/*  643: 643 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  644:     */  }
/*  645:     */  
/*  646: 646 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, IntBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  647: 647 */    long function_pointer = caps.glSeparableFilter2D;
/*  648: 648 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  649: 649 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  650: 650 */    BufferChecks.checkDirect(row);
/*  651: 651 */    BufferChecks.checkDirect(column);
/*  652: 652 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  653:     */  }
/*  654:     */  
/*  655: 655 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, ShortBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  656: 656 */    long function_pointer = caps.glSeparableFilter2D;
/*  657: 657 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  658: 658 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  659: 659 */    BufferChecks.checkDirect(row);
/*  660: 660 */    BufferChecks.checkDirect(column);
/*  661: 661 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  662:     */  }
/*  663:     */  
/*  664: 664 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, ByteBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  665: 665 */    long function_pointer = caps.glSeparableFilter2D;
/*  666: 666 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  667: 667 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  668: 668 */    BufferChecks.checkDirect(row);
/*  669: 669 */    BufferChecks.checkDirect(column);
/*  670: 670 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  671:     */  }
/*  672:     */  
/*  673: 673 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, DoubleBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  674: 674 */    long function_pointer = caps.glSeparableFilter2D;
/*  675: 675 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  676: 676 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  677: 677 */    BufferChecks.checkDirect(row);
/*  678: 678 */    BufferChecks.checkDirect(column);
/*  679: 679 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  680:     */  }
/*  681:     */  
/*  682: 682 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, FloatBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  683: 683 */    long function_pointer = caps.glSeparableFilter2D;
/*  684: 684 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  685: 685 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  686: 686 */    BufferChecks.checkDirect(row);
/*  687: 687 */    BufferChecks.checkDirect(column);
/*  688: 688 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  689:     */  }
/*  690:     */  
/*  691: 691 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, IntBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  692: 692 */    long function_pointer = caps.glSeparableFilter2D;
/*  693: 693 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  694: 694 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  695: 695 */    BufferChecks.checkDirect(row);
/*  696: 696 */    BufferChecks.checkDirect(column);
/*  697: 697 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  698:     */  }
/*  699:     */  
/*  700: 700 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, ShortBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  701: 701 */    long function_pointer = caps.glSeparableFilter2D;
/*  702: 702 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  703: 703 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  704: 704 */    BufferChecks.checkDirect(row);
/*  705: 705 */    BufferChecks.checkDirect(column);
/*  706: 706 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  707:     */  }
/*  708:     */  
/*  709: 709 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, ByteBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  710: 710 */    long function_pointer = caps.glSeparableFilter2D;
/*  711: 711 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  712: 712 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  713: 713 */    BufferChecks.checkDirect(row);
/*  714: 714 */    BufferChecks.checkDirect(column);
/*  715: 715 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  716:     */  }
/*  717:     */  
/*  718: 718 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, DoubleBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  719: 719 */    long function_pointer = caps.glSeparableFilter2D;
/*  720: 720 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  721: 721 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  722: 722 */    BufferChecks.checkDirect(row);
/*  723: 723 */    BufferChecks.checkDirect(column);
/*  724: 724 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  725:     */  }
/*  726:     */  
/*  727: 727 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, FloatBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  728: 728 */    long function_pointer = caps.glSeparableFilter2D;
/*  729: 729 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  730: 730 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  731: 731 */    BufferChecks.checkDirect(row);
/*  732: 732 */    BufferChecks.checkDirect(column);
/*  733: 733 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  734:     */  }
/*  735:     */  
/*  736: 736 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, IntBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  737: 737 */    long function_pointer = caps.glSeparableFilter2D;
/*  738: 738 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  739: 739 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  740: 740 */    BufferChecks.checkDirect(row);
/*  741: 741 */    BufferChecks.checkDirect(column);
/*  742: 742 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  743:     */  }
/*  744:     */  
/*  745: 745 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, ShortBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  746: 746 */    long function_pointer = caps.glSeparableFilter2D;
/*  747: 747 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  748: 748 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  749: 749 */    BufferChecks.checkDirect(row);
/*  750: 750 */    BufferChecks.checkDirect(column);
/*  751: 751 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  752:     */  }
/*  753:     */  
/*  754: 754 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, ByteBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  755: 755 */    long function_pointer = caps.glSeparableFilter2D;
/*  756: 756 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  757: 757 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  758: 758 */    BufferChecks.checkDirect(row);
/*  759: 759 */    BufferChecks.checkDirect(column);
/*  760: 760 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  761:     */  }
/*  762:     */  
/*  763: 763 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, DoubleBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  764: 764 */    long function_pointer = caps.glSeparableFilter2D;
/*  765: 765 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  766: 766 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  767: 767 */    BufferChecks.checkDirect(row);
/*  768: 768 */    BufferChecks.checkDirect(column);
/*  769: 769 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  770:     */  }
/*  771:     */  
/*  772: 772 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, FloatBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  773: 773 */    long function_pointer = caps.glSeparableFilter2D;
/*  774: 774 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  775: 775 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  776: 776 */    BufferChecks.checkDirect(row);
/*  777: 777 */    BufferChecks.checkDirect(column);
/*  778: 778 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  779:     */  }
/*  780:     */  
/*  781: 781 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, IntBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  782: 782 */    long function_pointer = caps.glSeparableFilter2D;
/*  783: 783 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  784: 784 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  785: 785 */    BufferChecks.checkDirect(row);
/*  786: 786 */    BufferChecks.checkDirect(column);
/*  787: 787 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  788:     */  }
/*  789:     */  
/*  790: 790 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, ShortBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  791: 791 */    long function_pointer = caps.glSeparableFilter2D;
/*  792: 792 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  793: 793 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  794: 794 */    BufferChecks.checkDirect(row);
/*  795: 795 */    BufferChecks.checkDirect(column);
/*  796: 796 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  797:     */  }
/*  798:     */  
/*  799: 799 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, ByteBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  800: 800 */    long function_pointer = caps.glSeparableFilter2D;
/*  801: 801 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  802: 802 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  803: 803 */    BufferChecks.checkDirect(row);
/*  804: 804 */    BufferChecks.checkDirect(column);
/*  805: 805 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  806:     */  }
/*  807:     */  
/*  808: 808 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, DoubleBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  809: 809 */    long function_pointer = caps.glSeparableFilter2D;
/*  810: 810 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  811: 811 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  812: 812 */    BufferChecks.checkDirect(row);
/*  813: 813 */    BufferChecks.checkDirect(column);
/*  814: 814 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  815:     */  }
/*  816:     */  
/*  817: 817 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, FloatBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  818: 818 */    long function_pointer = caps.glSeparableFilter2D;
/*  819: 819 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  820: 820 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  821: 821 */    BufferChecks.checkDirect(row);
/*  822: 822 */    BufferChecks.checkDirect(column);
/*  823: 823 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  824:     */  }
/*  825:     */  
/*  826: 826 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, IntBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  827: 827 */    long function_pointer = caps.glSeparableFilter2D;
/*  828: 828 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  829: 829 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  830: 830 */    BufferChecks.checkDirect(row);
/*  831: 831 */    BufferChecks.checkDirect(column);
/*  832: 832 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*  833:     */  }
/*  834:     */  
/*  835: 835 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, ShortBuffer column) { ContextCapabilities caps = GLContext.getCapabilities();
/*  836: 836 */    long function_pointer = caps.glSeparableFilter2D;
/*  837: 837 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  838: 838 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  839: 839 */    BufferChecks.checkDirect(row);
/*  840: 840 */    BufferChecks.checkDirect(column);
/*  841: 841 */    nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer); }
/*  842:     */  
/*  843:     */  static native void nglSeparableFilter2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2, long paramLong3);
/*  844:     */  
/*  845: 845 */  public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, long row_buffer_offset, long column_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  846: 846 */    long function_pointer = caps.glSeparableFilter2D;
/*  847: 847 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  848: 848 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  849: 849 */    nglSeparableFilter2DBO(target, internalformat, width, height, format, type, row_buffer_offset, column_buffer_offset, function_pointer);
/*  850:     */  }
/*  851:     */  
/*  852:     */  static native void nglSeparableFilter2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2, long paramLong3);
/*  853:     */  
/*  854: 854 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  855: 855 */    long function_pointer = caps.glGetSeparableFilter;
/*  856: 856 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  857: 857 */    GLChecks.ensurePackPBOdisabled(caps);
/*  858: 858 */    BufferChecks.checkDirect(row);
/*  859: 859 */    BufferChecks.checkDirect(column);
/*  860: 860 */    BufferChecks.checkDirect(span);
/*  861: 861 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  862:     */  }
/*  863:     */  
/*  864: 864 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  865: 865 */    long function_pointer = caps.glGetSeparableFilter;
/*  866: 866 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  867: 867 */    GLChecks.ensurePackPBOdisabled(caps);
/*  868: 868 */    BufferChecks.checkDirect(row);
/*  869: 869 */    BufferChecks.checkDirect(column);
/*  870: 870 */    BufferChecks.checkDirect(span);
/*  871: 871 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  872:     */  }
/*  873:     */  
/*  874: 874 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  875: 875 */    long function_pointer = caps.glGetSeparableFilter;
/*  876: 876 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  877: 877 */    GLChecks.ensurePackPBOdisabled(caps);
/*  878: 878 */    BufferChecks.checkDirect(row);
/*  879: 879 */    BufferChecks.checkDirect(column);
/*  880: 880 */    BufferChecks.checkDirect(span);
/*  881: 881 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  882:     */  }
/*  883:     */  
/*  884: 884 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  885: 885 */    long function_pointer = caps.glGetSeparableFilter;
/*  886: 886 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  887: 887 */    GLChecks.ensurePackPBOdisabled(caps);
/*  888: 888 */    BufferChecks.checkDirect(row);
/*  889: 889 */    BufferChecks.checkDirect(column);
/*  890: 890 */    BufferChecks.checkDirect(span);
/*  891: 891 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  892:     */  }
/*  893:     */  
/*  894: 894 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  895: 895 */    long function_pointer = caps.glGetSeparableFilter;
/*  896: 896 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  897: 897 */    GLChecks.ensurePackPBOdisabled(caps);
/*  898: 898 */    BufferChecks.checkDirect(row);
/*  899: 899 */    BufferChecks.checkDirect(column);
/*  900: 900 */    BufferChecks.checkDirect(span);
/*  901: 901 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  902:     */  }
/*  903:     */  
/*  904: 904 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  905: 905 */    long function_pointer = caps.glGetSeparableFilter;
/*  906: 906 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  907: 907 */    GLChecks.ensurePackPBOdisabled(caps);
/*  908: 908 */    BufferChecks.checkDirect(row);
/*  909: 909 */    BufferChecks.checkDirect(column);
/*  910: 910 */    BufferChecks.checkDirect(span);
/*  911: 911 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  912:     */  }
/*  913:     */  
/*  914: 914 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  915: 915 */    long function_pointer = caps.glGetSeparableFilter;
/*  916: 916 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  917: 917 */    GLChecks.ensurePackPBOdisabled(caps);
/*  918: 918 */    BufferChecks.checkDirect(row);
/*  919: 919 */    BufferChecks.checkDirect(column);
/*  920: 920 */    BufferChecks.checkDirect(span);
/*  921: 921 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  922:     */  }
/*  923:     */  
/*  924: 924 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  925: 925 */    long function_pointer = caps.glGetSeparableFilter;
/*  926: 926 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  927: 927 */    GLChecks.ensurePackPBOdisabled(caps);
/*  928: 928 */    BufferChecks.checkDirect(row);
/*  929: 929 */    BufferChecks.checkDirect(column);
/*  930: 930 */    BufferChecks.checkDirect(span);
/*  931: 931 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  932:     */  }
/*  933:     */  
/*  934: 934 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  935: 935 */    long function_pointer = caps.glGetSeparableFilter;
/*  936: 936 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  937: 937 */    GLChecks.ensurePackPBOdisabled(caps);
/*  938: 938 */    BufferChecks.checkDirect(row);
/*  939: 939 */    BufferChecks.checkDirect(column);
/*  940: 940 */    BufferChecks.checkDirect(span);
/*  941: 941 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  942:     */  }
/*  943:     */  
/*  944: 944 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  945: 945 */    long function_pointer = caps.glGetSeparableFilter;
/*  946: 946 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  947: 947 */    GLChecks.ensurePackPBOdisabled(caps);
/*  948: 948 */    BufferChecks.checkDirect(row);
/*  949: 949 */    BufferChecks.checkDirect(column);
/*  950: 950 */    BufferChecks.checkDirect(span);
/*  951: 951 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  952:     */  }
/*  953:     */  
/*  954: 954 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  955: 955 */    long function_pointer = caps.glGetSeparableFilter;
/*  956: 956 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  957: 957 */    GLChecks.ensurePackPBOdisabled(caps);
/*  958: 958 */    BufferChecks.checkDirect(row);
/*  959: 959 */    BufferChecks.checkDirect(column);
/*  960: 960 */    BufferChecks.checkDirect(span);
/*  961: 961 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  962:     */  }
/*  963:     */  
/*  964: 964 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  965: 965 */    long function_pointer = caps.glGetSeparableFilter;
/*  966: 966 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  967: 967 */    GLChecks.ensurePackPBOdisabled(caps);
/*  968: 968 */    BufferChecks.checkDirect(row);
/*  969: 969 */    BufferChecks.checkDirect(column);
/*  970: 970 */    BufferChecks.checkDirect(span);
/*  971: 971 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  972:     */  }
/*  973:     */  
/*  974: 974 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  975: 975 */    long function_pointer = caps.glGetSeparableFilter;
/*  976: 976 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  977: 977 */    GLChecks.ensurePackPBOdisabled(caps);
/*  978: 978 */    BufferChecks.checkDirect(row);
/*  979: 979 */    BufferChecks.checkDirect(column);
/*  980: 980 */    BufferChecks.checkDirect(span);
/*  981: 981 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  982:     */  }
/*  983:     */  
/*  984: 984 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  985: 985 */    long function_pointer = caps.glGetSeparableFilter;
/*  986: 986 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  987: 987 */    GLChecks.ensurePackPBOdisabled(caps);
/*  988: 988 */    BufferChecks.checkDirect(row);
/*  989: 989 */    BufferChecks.checkDirect(column);
/*  990: 990 */    BufferChecks.checkDirect(span);
/*  991: 991 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*  992:     */  }
/*  993:     */  
/*  994: 994 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/*  995: 995 */    long function_pointer = caps.glGetSeparableFilter;
/*  996: 996 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  997: 997 */    GLChecks.ensurePackPBOdisabled(caps);
/*  998: 998 */    BufferChecks.checkDirect(row);
/*  999: 999 */    BufferChecks.checkDirect(column);
/* 1000:1000 */    BufferChecks.checkDirect(span);
/* 1001:1001 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1002:     */  }
/* 1003:     */  
/* 1004:1004 */  public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1005:1005 */    long function_pointer = caps.glGetSeparableFilter;
/* 1006:1006 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1007:1007 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1008:1008 */    BufferChecks.checkDirect(row);
/* 1009:1009 */    BufferChecks.checkDirect(column);
/* 1010:1010 */    BufferChecks.checkDirect(span);
/* 1011:1011 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1012:     */  }
/* 1013:     */  
/* 1014:1014 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1015:1015 */    long function_pointer = caps.glGetSeparableFilter;
/* 1016:1016 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1017:1017 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1018:1018 */    BufferChecks.checkDirect(row);
/* 1019:1019 */    BufferChecks.checkDirect(column);
/* 1020:1020 */    BufferChecks.checkDirect(span);
/* 1021:1021 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1022:     */  }
/* 1023:     */  
/* 1024:1024 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1025:1025 */    long function_pointer = caps.glGetSeparableFilter;
/* 1026:1026 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1027:1027 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1028:1028 */    BufferChecks.checkDirect(row);
/* 1029:1029 */    BufferChecks.checkDirect(column);
/* 1030:1030 */    BufferChecks.checkDirect(span);
/* 1031:1031 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1032:     */  }
/* 1033:     */  
/* 1034:1034 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1035:1035 */    long function_pointer = caps.glGetSeparableFilter;
/* 1036:1036 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1037:1037 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1038:1038 */    BufferChecks.checkDirect(row);
/* 1039:1039 */    BufferChecks.checkDirect(column);
/* 1040:1040 */    BufferChecks.checkDirect(span);
/* 1041:1041 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1042:     */  }
/* 1043:     */  
/* 1044:1044 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1045:1045 */    long function_pointer = caps.glGetSeparableFilter;
/* 1046:1046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1047:1047 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1048:1048 */    BufferChecks.checkDirect(row);
/* 1049:1049 */    BufferChecks.checkDirect(column);
/* 1050:1050 */    BufferChecks.checkDirect(span);
/* 1051:1051 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1052:     */  }
/* 1053:     */  
/* 1054:1054 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1055:1055 */    long function_pointer = caps.glGetSeparableFilter;
/* 1056:1056 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1057:1057 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1058:1058 */    BufferChecks.checkDirect(row);
/* 1059:1059 */    BufferChecks.checkDirect(column);
/* 1060:1060 */    BufferChecks.checkDirect(span);
/* 1061:1061 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1062:     */  }
/* 1063:     */  
/* 1064:1064 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1065:1065 */    long function_pointer = caps.glGetSeparableFilter;
/* 1066:1066 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1067:1067 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1068:1068 */    BufferChecks.checkDirect(row);
/* 1069:1069 */    BufferChecks.checkDirect(column);
/* 1070:1070 */    BufferChecks.checkDirect(span);
/* 1071:1071 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1072:     */  }
/* 1073:     */  
/* 1074:1074 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1075:1075 */    long function_pointer = caps.glGetSeparableFilter;
/* 1076:1076 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1077:1077 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1078:1078 */    BufferChecks.checkDirect(row);
/* 1079:1079 */    BufferChecks.checkDirect(column);
/* 1080:1080 */    BufferChecks.checkDirect(span);
/* 1081:1081 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1082:     */  }
/* 1083:     */  
/* 1084:1084 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1085:1085 */    long function_pointer = caps.glGetSeparableFilter;
/* 1086:1086 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1087:1087 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1088:1088 */    BufferChecks.checkDirect(row);
/* 1089:1089 */    BufferChecks.checkDirect(column);
/* 1090:1090 */    BufferChecks.checkDirect(span);
/* 1091:1091 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1092:     */  }
/* 1093:     */  
/* 1094:1094 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1095:1095 */    long function_pointer = caps.glGetSeparableFilter;
/* 1096:1096 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1097:1097 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1098:1098 */    BufferChecks.checkDirect(row);
/* 1099:1099 */    BufferChecks.checkDirect(column);
/* 1100:1100 */    BufferChecks.checkDirect(span);
/* 1101:1101 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1102:     */  }
/* 1103:     */  
/* 1104:1104 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1105:1105 */    long function_pointer = caps.glGetSeparableFilter;
/* 1106:1106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1107:1107 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1108:1108 */    BufferChecks.checkDirect(row);
/* 1109:1109 */    BufferChecks.checkDirect(column);
/* 1110:1110 */    BufferChecks.checkDirect(span);
/* 1111:1111 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1112:     */  }
/* 1113:     */  
/* 1114:1114 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1115:1115 */    long function_pointer = caps.glGetSeparableFilter;
/* 1116:1116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1117:1117 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1118:1118 */    BufferChecks.checkDirect(row);
/* 1119:1119 */    BufferChecks.checkDirect(column);
/* 1120:1120 */    BufferChecks.checkDirect(span);
/* 1121:1121 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1122:     */  }
/* 1123:     */  
/* 1124:1124 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1125:1125 */    long function_pointer = caps.glGetSeparableFilter;
/* 1126:1126 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1127:1127 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1128:1128 */    BufferChecks.checkDirect(row);
/* 1129:1129 */    BufferChecks.checkDirect(column);
/* 1130:1130 */    BufferChecks.checkDirect(span);
/* 1131:1131 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1132:     */  }
/* 1133:     */  
/* 1134:1134 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1135:1135 */    long function_pointer = caps.glGetSeparableFilter;
/* 1136:1136 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1137:1137 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1138:1138 */    BufferChecks.checkDirect(row);
/* 1139:1139 */    BufferChecks.checkDirect(column);
/* 1140:1140 */    BufferChecks.checkDirect(span);
/* 1141:1141 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1142:     */  }
/* 1143:     */  
/* 1144:1144 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1145:1145 */    long function_pointer = caps.glGetSeparableFilter;
/* 1146:1146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1147:1147 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1148:1148 */    BufferChecks.checkDirect(row);
/* 1149:1149 */    BufferChecks.checkDirect(column);
/* 1150:1150 */    BufferChecks.checkDirect(span);
/* 1151:1151 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1152:     */  }
/* 1153:     */  
/* 1154:1154 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1155:1155 */    long function_pointer = caps.glGetSeparableFilter;
/* 1156:1156 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1157:1157 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1158:1158 */    BufferChecks.checkDirect(row);
/* 1159:1159 */    BufferChecks.checkDirect(column);
/* 1160:1160 */    BufferChecks.checkDirect(span);
/* 1161:1161 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1162:     */  }
/* 1163:     */  
/* 1164:1164 */  public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1165:1165 */    long function_pointer = caps.glGetSeparableFilter;
/* 1166:1166 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1167:1167 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1168:1168 */    BufferChecks.checkDirect(row);
/* 1169:1169 */    BufferChecks.checkDirect(column);
/* 1170:1170 */    BufferChecks.checkDirect(span);
/* 1171:1171 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1172:     */  }
/* 1173:     */  
/* 1174:1174 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1175:1175 */    long function_pointer = caps.glGetSeparableFilter;
/* 1176:1176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1177:1177 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1178:1178 */    BufferChecks.checkDirect(row);
/* 1179:1179 */    BufferChecks.checkDirect(column);
/* 1180:1180 */    BufferChecks.checkDirect(span);
/* 1181:1181 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1182:     */  }
/* 1183:     */  
/* 1184:1184 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1185:1185 */    long function_pointer = caps.glGetSeparableFilter;
/* 1186:1186 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1187:1187 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1188:1188 */    BufferChecks.checkDirect(row);
/* 1189:1189 */    BufferChecks.checkDirect(column);
/* 1190:1190 */    BufferChecks.checkDirect(span);
/* 1191:1191 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1192:     */  }
/* 1193:     */  
/* 1194:1194 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1195:1195 */    long function_pointer = caps.glGetSeparableFilter;
/* 1196:1196 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1197:1197 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1198:1198 */    BufferChecks.checkDirect(row);
/* 1199:1199 */    BufferChecks.checkDirect(column);
/* 1200:1200 */    BufferChecks.checkDirect(span);
/* 1201:1201 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1202:     */  }
/* 1203:     */  
/* 1204:1204 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1205:1205 */    long function_pointer = caps.glGetSeparableFilter;
/* 1206:1206 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1207:1207 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1208:1208 */    BufferChecks.checkDirect(row);
/* 1209:1209 */    BufferChecks.checkDirect(column);
/* 1210:1210 */    BufferChecks.checkDirect(span);
/* 1211:1211 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1212:     */  }
/* 1213:     */  
/* 1214:1214 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1215:1215 */    long function_pointer = caps.glGetSeparableFilter;
/* 1216:1216 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1217:1217 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1218:1218 */    BufferChecks.checkDirect(row);
/* 1219:1219 */    BufferChecks.checkDirect(column);
/* 1220:1220 */    BufferChecks.checkDirect(span);
/* 1221:1221 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1222:     */  }
/* 1223:     */  
/* 1224:1224 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1225:1225 */    long function_pointer = caps.glGetSeparableFilter;
/* 1226:1226 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1227:1227 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1228:1228 */    BufferChecks.checkDirect(row);
/* 1229:1229 */    BufferChecks.checkDirect(column);
/* 1230:1230 */    BufferChecks.checkDirect(span);
/* 1231:1231 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1232:     */  }
/* 1233:     */  
/* 1234:1234 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1235:1235 */    long function_pointer = caps.glGetSeparableFilter;
/* 1236:1236 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1237:1237 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1238:1238 */    BufferChecks.checkDirect(row);
/* 1239:1239 */    BufferChecks.checkDirect(column);
/* 1240:1240 */    BufferChecks.checkDirect(span);
/* 1241:1241 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1242:     */  }
/* 1243:     */  
/* 1244:1244 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1245:1245 */    long function_pointer = caps.glGetSeparableFilter;
/* 1246:1246 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1247:1247 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1248:1248 */    BufferChecks.checkDirect(row);
/* 1249:1249 */    BufferChecks.checkDirect(column);
/* 1250:1250 */    BufferChecks.checkDirect(span);
/* 1251:1251 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1252:     */  }
/* 1253:     */  
/* 1254:1254 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1255:1255 */    long function_pointer = caps.glGetSeparableFilter;
/* 1256:1256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1257:1257 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1258:1258 */    BufferChecks.checkDirect(row);
/* 1259:1259 */    BufferChecks.checkDirect(column);
/* 1260:1260 */    BufferChecks.checkDirect(span);
/* 1261:1261 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1262:     */  }
/* 1263:     */  
/* 1264:1264 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1265:1265 */    long function_pointer = caps.glGetSeparableFilter;
/* 1266:1266 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1267:1267 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1268:1268 */    BufferChecks.checkDirect(row);
/* 1269:1269 */    BufferChecks.checkDirect(column);
/* 1270:1270 */    BufferChecks.checkDirect(span);
/* 1271:1271 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1272:     */  }
/* 1273:     */  
/* 1274:1274 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1275:1275 */    long function_pointer = caps.glGetSeparableFilter;
/* 1276:1276 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1277:1277 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1278:1278 */    BufferChecks.checkDirect(row);
/* 1279:1279 */    BufferChecks.checkDirect(column);
/* 1280:1280 */    BufferChecks.checkDirect(span);
/* 1281:1281 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1282:     */  }
/* 1283:     */  
/* 1284:1284 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1285:1285 */    long function_pointer = caps.glGetSeparableFilter;
/* 1286:1286 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1287:1287 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1288:1288 */    BufferChecks.checkDirect(row);
/* 1289:1289 */    BufferChecks.checkDirect(column);
/* 1290:1290 */    BufferChecks.checkDirect(span);
/* 1291:1291 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1292:     */  }
/* 1293:     */  
/* 1294:1294 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1295:1295 */    long function_pointer = caps.glGetSeparableFilter;
/* 1296:1296 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1297:1297 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1298:1298 */    BufferChecks.checkDirect(row);
/* 1299:1299 */    BufferChecks.checkDirect(column);
/* 1300:1300 */    BufferChecks.checkDirect(span);
/* 1301:1301 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1302:     */  }
/* 1303:     */  
/* 1304:1304 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1305:1305 */    long function_pointer = caps.glGetSeparableFilter;
/* 1306:1306 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1307:1307 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1308:1308 */    BufferChecks.checkDirect(row);
/* 1309:1309 */    BufferChecks.checkDirect(column);
/* 1310:1310 */    BufferChecks.checkDirect(span);
/* 1311:1311 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1312:     */  }
/* 1313:     */  
/* 1314:1314 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1315:1315 */    long function_pointer = caps.glGetSeparableFilter;
/* 1316:1316 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1317:1317 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1318:1318 */    BufferChecks.checkDirect(row);
/* 1319:1319 */    BufferChecks.checkDirect(column);
/* 1320:1320 */    BufferChecks.checkDirect(span);
/* 1321:1321 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1322:     */  }
/* 1323:     */  
/* 1324:1324 */  public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1325:1325 */    long function_pointer = caps.glGetSeparableFilter;
/* 1326:1326 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1327:1327 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1328:1328 */    BufferChecks.checkDirect(row);
/* 1329:1329 */    BufferChecks.checkDirect(column);
/* 1330:1330 */    BufferChecks.checkDirect(span);
/* 1331:1331 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1332:     */  }
/* 1333:     */  
/* 1334:1334 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1335:1335 */    long function_pointer = caps.glGetSeparableFilter;
/* 1336:1336 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1337:1337 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1338:1338 */    BufferChecks.checkDirect(row);
/* 1339:1339 */    BufferChecks.checkDirect(column);
/* 1340:1340 */    BufferChecks.checkDirect(span);
/* 1341:1341 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1342:     */  }
/* 1343:     */  
/* 1344:1344 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1345:1345 */    long function_pointer = caps.glGetSeparableFilter;
/* 1346:1346 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1347:1347 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1348:1348 */    BufferChecks.checkDirect(row);
/* 1349:1349 */    BufferChecks.checkDirect(column);
/* 1350:1350 */    BufferChecks.checkDirect(span);
/* 1351:1351 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1352:     */  }
/* 1353:     */  
/* 1354:1354 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1355:1355 */    long function_pointer = caps.glGetSeparableFilter;
/* 1356:1356 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1357:1357 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1358:1358 */    BufferChecks.checkDirect(row);
/* 1359:1359 */    BufferChecks.checkDirect(column);
/* 1360:1360 */    BufferChecks.checkDirect(span);
/* 1361:1361 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1362:     */  }
/* 1363:     */  
/* 1364:1364 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1365:1365 */    long function_pointer = caps.glGetSeparableFilter;
/* 1366:1366 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1367:1367 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1368:1368 */    BufferChecks.checkDirect(row);
/* 1369:1369 */    BufferChecks.checkDirect(column);
/* 1370:1370 */    BufferChecks.checkDirect(span);
/* 1371:1371 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1372:     */  }
/* 1373:     */  
/* 1374:1374 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1375:1375 */    long function_pointer = caps.glGetSeparableFilter;
/* 1376:1376 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1377:1377 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1378:1378 */    BufferChecks.checkDirect(row);
/* 1379:1379 */    BufferChecks.checkDirect(column);
/* 1380:1380 */    BufferChecks.checkDirect(span);
/* 1381:1381 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1382:     */  }
/* 1383:     */  
/* 1384:1384 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1385:1385 */    long function_pointer = caps.glGetSeparableFilter;
/* 1386:1386 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1387:1387 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1388:1388 */    BufferChecks.checkDirect(row);
/* 1389:1389 */    BufferChecks.checkDirect(column);
/* 1390:1390 */    BufferChecks.checkDirect(span);
/* 1391:1391 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1392:     */  }
/* 1393:     */  
/* 1394:1394 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1395:1395 */    long function_pointer = caps.glGetSeparableFilter;
/* 1396:1396 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1397:1397 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1398:1398 */    BufferChecks.checkDirect(row);
/* 1399:1399 */    BufferChecks.checkDirect(column);
/* 1400:1400 */    BufferChecks.checkDirect(span);
/* 1401:1401 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1402:     */  }
/* 1403:     */  
/* 1404:1404 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1405:1405 */    long function_pointer = caps.glGetSeparableFilter;
/* 1406:1406 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1407:1407 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1408:1408 */    BufferChecks.checkDirect(row);
/* 1409:1409 */    BufferChecks.checkDirect(column);
/* 1410:1410 */    BufferChecks.checkDirect(span);
/* 1411:1411 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1412:     */  }
/* 1413:     */  
/* 1414:1414 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1415:1415 */    long function_pointer = caps.glGetSeparableFilter;
/* 1416:1416 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1417:1417 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1418:1418 */    BufferChecks.checkDirect(row);
/* 1419:1419 */    BufferChecks.checkDirect(column);
/* 1420:1420 */    BufferChecks.checkDirect(span);
/* 1421:1421 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1422:     */  }
/* 1423:     */  
/* 1424:1424 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1425:1425 */    long function_pointer = caps.glGetSeparableFilter;
/* 1426:1426 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1427:1427 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1428:1428 */    BufferChecks.checkDirect(row);
/* 1429:1429 */    BufferChecks.checkDirect(column);
/* 1430:1430 */    BufferChecks.checkDirect(span);
/* 1431:1431 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1432:     */  }
/* 1433:     */  
/* 1434:1434 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1435:1435 */    long function_pointer = caps.glGetSeparableFilter;
/* 1436:1436 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1437:1437 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1438:1438 */    BufferChecks.checkDirect(row);
/* 1439:1439 */    BufferChecks.checkDirect(column);
/* 1440:1440 */    BufferChecks.checkDirect(span);
/* 1441:1441 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1442:     */  }
/* 1443:     */  
/* 1444:1444 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1445:1445 */    long function_pointer = caps.glGetSeparableFilter;
/* 1446:1446 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1447:1447 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1448:1448 */    BufferChecks.checkDirect(row);
/* 1449:1449 */    BufferChecks.checkDirect(column);
/* 1450:1450 */    BufferChecks.checkDirect(span);
/* 1451:1451 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1452:     */  }
/* 1453:     */  
/* 1454:1454 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1455:1455 */    long function_pointer = caps.glGetSeparableFilter;
/* 1456:1456 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1457:1457 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1458:1458 */    BufferChecks.checkDirect(row);
/* 1459:1459 */    BufferChecks.checkDirect(column);
/* 1460:1460 */    BufferChecks.checkDirect(span);
/* 1461:1461 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1462:     */  }
/* 1463:     */  
/* 1464:1464 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1465:1465 */    long function_pointer = caps.glGetSeparableFilter;
/* 1466:1466 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1467:1467 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1468:1468 */    BufferChecks.checkDirect(row);
/* 1469:1469 */    BufferChecks.checkDirect(column);
/* 1470:1470 */    BufferChecks.checkDirect(span);
/* 1471:1471 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1472:     */  }
/* 1473:     */  
/* 1474:1474 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1475:1475 */    long function_pointer = caps.glGetSeparableFilter;
/* 1476:1476 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1477:1477 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1478:1478 */    BufferChecks.checkDirect(row);
/* 1479:1479 */    BufferChecks.checkDirect(column);
/* 1480:1480 */    BufferChecks.checkDirect(span);
/* 1481:1481 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1482:     */  }
/* 1483:     */  
/* 1484:1484 */  public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1485:1485 */    long function_pointer = caps.glGetSeparableFilter;
/* 1486:1486 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1487:1487 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1488:1488 */    BufferChecks.checkDirect(row);
/* 1489:1489 */    BufferChecks.checkDirect(column);
/* 1490:1490 */    BufferChecks.checkDirect(span);
/* 1491:1491 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1492:     */  }
/* 1493:     */  
/* 1494:1494 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1495:1495 */    long function_pointer = caps.glGetSeparableFilter;
/* 1496:1496 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1497:1497 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1498:1498 */    BufferChecks.checkDirect(row);
/* 1499:1499 */    BufferChecks.checkDirect(column);
/* 1500:1500 */    BufferChecks.checkDirect(span);
/* 1501:1501 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1502:     */  }
/* 1503:     */  
/* 1504:1504 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1505:1505 */    long function_pointer = caps.glGetSeparableFilter;
/* 1506:1506 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1507:1507 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1508:1508 */    BufferChecks.checkDirect(row);
/* 1509:1509 */    BufferChecks.checkDirect(column);
/* 1510:1510 */    BufferChecks.checkDirect(span);
/* 1511:1511 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1512:     */  }
/* 1513:     */  
/* 1514:1514 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1515:1515 */    long function_pointer = caps.glGetSeparableFilter;
/* 1516:1516 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1517:1517 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1518:1518 */    BufferChecks.checkDirect(row);
/* 1519:1519 */    BufferChecks.checkDirect(column);
/* 1520:1520 */    BufferChecks.checkDirect(span);
/* 1521:1521 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1522:     */  }
/* 1523:     */  
/* 1524:1524 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1525:1525 */    long function_pointer = caps.glGetSeparableFilter;
/* 1526:1526 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1527:1527 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1528:1528 */    BufferChecks.checkDirect(row);
/* 1529:1529 */    BufferChecks.checkDirect(column);
/* 1530:1530 */    BufferChecks.checkDirect(span);
/* 1531:1531 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1532:     */  }
/* 1533:     */  
/* 1534:1534 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1535:1535 */    long function_pointer = caps.glGetSeparableFilter;
/* 1536:1536 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1537:1537 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1538:1538 */    BufferChecks.checkDirect(row);
/* 1539:1539 */    BufferChecks.checkDirect(column);
/* 1540:1540 */    BufferChecks.checkDirect(span);
/* 1541:1541 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1542:     */  }
/* 1543:     */  
/* 1544:1544 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1545:1545 */    long function_pointer = caps.glGetSeparableFilter;
/* 1546:1546 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1547:1547 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1548:1548 */    BufferChecks.checkDirect(row);
/* 1549:1549 */    BufferChecks.checkDirect(column);
/* 1550:1550 */    BufferChecks.checkDirect(span);
/* 1551:1551 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1552:     */  }
/* 1553:     */  
/* 1554:1554 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1555:1555 */    long function_pointer = caps.glGetSeparableFilter;
/* 1556:1556 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1557:1557 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1558:1558 */    BufferChecks.checkDirect(row);
/* 1559:1559 */    BufferChecks.checkDirect(column);
/* 1560:1560 */    BufferChecks.checkDirect(span);
/* 1561:1561 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1562:     */  }
/* 1563:     */  
/* 1564:1564 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1565:1565 */    long function_pointer = caps.glGetSeparableFilter;
/* 1566:1566 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1567:1567 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1568:1568 */    BufferChecks.checkDirect(row);
/* 1569:1569 */    BufferChecks.checkDirect(column);
/* 1570:1570 */    BufferChecks.checkDirect(span);
/* 1571:1571 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1572:     */  }
/* 1573:     */  
/* 1574:1574 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1575:1575 */    long function_pointer = caps.glGetSeparableFilter;
/* 1576:1576 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1577:1577 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1578:1578 */    BufferChecks.checkDirect(row);
/* 1579:1579 */    BufferChecks.checkDirect(column);
/* 1580:1580 */    BufferChecks.checkDirect(span);
/* 1581:1581 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1582:     */  }
/* 1583:     */  
/* 1584:1584 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1585:1585 */    long function_pointer = caps.glGetSeparableFilter;
/* 1586:1586 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1587:1587 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1588:1588 */    BufferChecks.checkDirect(row);
/* 1589:1589 */    BufferChecks.checkDirect(column);
/* 1590:1590 */    BufferChecks.checkDirect(span);
/* 1591:1591 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1592:     */  }
/* 1593:     */  
/* 1594:1594 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1595:1595 */    long function_pointer = caps.glGetSeparableFilter;
/* 1596:1596 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1597:1597 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1598:1598 */    BufferChecks.checkDirect(row);
/* 1599:1599 */    BufferChecks.checkDirect(column);
/* 1600:1600 */    BufferChecks.checkDirect(span);
/* 1601:1601 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1602:     */  }
/* 1603:     */  
/* 1604:1604 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1605:1605 */    long function_pointer = caps.glGetSeparableFilter;
/* 1606:1606 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1607:1607 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1608:1608 */    BufferChecks.checkDirect(row);
/* 1609:1609 */    BufferChecks.checkDirect(column);
/* 1610:1610 */    BufferChecks.checkDirect(span);
/* 1611:1611 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1612:     */  }
/* 1613:     */  
/* 1614:1614 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, ByteBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1615:1615 */    long function_pointer = caps.glGetSeparableFilter;
/* 1616:1616 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1617:1617 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1618:1618 */    BufferChecks.checkDirect(row);
/* 1619:1619 */    BufferChecks.checkDirect(column);
/* 1620:1620 */    BufferChecks.checkDirect(span);
/* 1621:1621 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1622:     */  }
/* 1623:     */  
/* 1624:1624 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, DoubleBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1625:1625 */    long function_pointer = caps.glGetSeparableFilter;
/* 1626:1626 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1627:1627 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1628:1628 */    BufferChecks.checkDirect(row);
/* 1629:1629 */    BufferChecks.checkDirect(column);
/* 1630:1630 */    BufferChecks.checkDirect(span);
/* 1631:1631 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1632:     */  }
/* 1633:     */  
/* 1634:1634 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, IntBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1635:1635 */    long function_pointer = caps.glGetSeparableFilter;
/* 1636:1636 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1637:1637 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1638:1638 */    BufferChecks.checkDirect(row);
/* 1639:1639 */    BufferChecks.checkDirect(column);
/* 1640:1640 */    BufferChecks.checkDirect(span);
/* 1641:1641 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/* 1642:     */  }
/* 1643:     */  
/* 1644:1644 */  public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, ShortBuffer span) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1645:1645 */    long function_pointer = caps.glGetSeparableFilter;
/* 1646:1646 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1647:1647 */    GLChecks.ensurePackPBOdisabled(caps);
/* 1648:1648 */    BufferChecks.checkDirect(row);
/* 1649:1649 */    BufferChecks.checkDirect(column);
/* 1650:1650 */    BufferChecks.checkDirect(span);
/* 1651:1651 */    nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer); }
/* 1652:     */  
/* 1653:     */  static native void nglGetSeparableFilter(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 1654:     */  
/* 1655:1655 */  public static void glGetSeparableFilter(int target, int format, int type, long row_buffer_offset, long column_buffer_offset, long span_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1656:1656 */    long function_pointer = caps.glGetSeparableFilter;
/* 1657:1657 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1658:1658 */    GLChecks.ensurePackPBOenabled(caps);
/* 1659:1659 */    nglGetSeparableFilterBO(target, format, type, row_buffer_offset, column_buffer_offset, span_buffer_offset, function_pointer);
/* 1660:     */  }
/* 1661:     */  
/* 1662:     */  static native void nglGetSeparableFilterBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 1663:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBImaging
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */