/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.DoubleBuffer;
/*    5:     */import java.nio.FloatBuffer;
/*    6:     */import java.nio.IntBuffer;
/*    7:     */import org.lwjgl.BufferChecks;
/*    8:     */import org.lwjgl.LWJGLUtil;
/*    9:     */import org.lwjgl.MemoryUtil;
/*   10:     */
/*   88:     */public final class GL41
/*   89:     */{
/*   90:     */  public static final int GL_SHADER_COMPILER = 36346;
/*   91:     */  public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
/*   92:     */  public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
/*   93:     */  public static final int GL_MAX_VARYING_VECTORS = 36348;
/*   94:     */  public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
/*   95:     */  public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
/*   96:     */  public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
/*   97:     */  public static final int GL_FIXED = 5132;
/*   98:     */  public static final int GL_LOW_FLOAT = 36336;
/*   99:     */  public static final int GL_MEDIUM_FLOAT = 36337;
/*  100:     */  public static final int GL_HIGH_FLOAT = 36338;
/*  101:     */  public static final int GL_LOW_INT = 36339;
/*  102:     */  public static final int GL_MEDIUM_INT = 36340;
/*  103:     */  public static final int GL_HIGH_INT = 36341;
/*  104:     */  public static final int GL_RGB565 = 36194;
/*  105:     */  public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;
/*  106:     */  public static final int GL_PROGRAM_BINARY_LENGTH = 34625;
/*  107:     */  public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 34814;
/*  108:     */  public static final int GL_PROGRAM_BINARY_FORMATS = 34815;
/*  109:     */  public static final int GL_VERTEX_SHADER_BIT = 1;
/*  110:     */  public static final int GL_FRAGMENT_SHADER_BIT = 2;
/*  111:     */  public static final int GL_GEOMETRY_SHADER_BIT = 4;
/*  112:     */  public static final int GL_TESS_CONTROL_SHADER_BIT = 8;
/*  113:     */  public static final int GL_TESS_EVALUATION_SHADER_BIT = 16;
/*  114:     */  public static final int GL_ALL_SHADER_BITS = -1;
/*  115:     */  public static final int GL_PROGRAM_SEPARABLE = 33368;
/*  116:     */  public static final int GL_ACTIVE_PROGRAM = 33369;
/*  117:     */  public static final int GL_PROGRAM_PIPELINE_BINDING = 33370;
/*  118:     */  public static final int GL_DOUBLE_VEC2 = 36860;
/*  119:     */  public static final int GL_DOUBLE_VEC3 = 36861;
/*  120:     */  public static final int GL_DOUBLE_VEC4 = 36862;
/*  121:     */  public static final int GL_DOUBLE_MAT2 = 36678;
/*  122:     */  public static final int GL_DOUBLE_MAT3 = 36679;
/*  123:     */  public static final int GL_DOUBLE_MAT4 = 36680;
/*  124:     */  public static final int GL_DOUBLE_MAT2x3 = 36681;
/*  125:     */  public static final int GL_DOUBLE_MAT2x4 = 36682;
/*  126:     */  public static final int GL_DOUBLE_MAT3x2 = 36683;
/*  127:     */  public static final int GL_DOUBLE_MAT3x4 = 36684;
/*  128:     */  public static final int GL_DOUBLE_MAT4x2 = 36685;
/*  129:     */  public static final int GL_DOUBLE_MAT4x3 = 36686;
/*  130:     */  public static final int GL_MAX_VIEWPORTS = 33371;
/*  131:     */  public static final int GL_VIEWPORT_SUBPIXEL_BITS = 33372;
/*  132:     */  public static final int GL_VIEWPORT_BOUNDS_RANGE = 33373;
/*  133:     */  public static final int GL_LAYER_PROVOKING_VERTEX = 33374;
/*  134:     */  public static final int GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;
/*  135:     */  public static final int GL_SCISSOR_BOX = 3088;
/*  136:     */  public static final int GL_VIEWPORT = 2978;
/*  137:     */  public static final int GL_DEPTH_RANGE = 2928;
/*  138:     */  public static final int GL_SCISSOR_TEST = 3089;
/*  139:     */  public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
/*  140:     */  public static final int GL_LAST_VERTEX_CONVENTION = 36430;
/*  141:     */  public static final int GL_PROVOKING_VERTEX = 36431;
/*  142:     */  public static final int GL_UNDEFINED_VERTEX = 33376;
/*  143:     */  
/*  144:     */  public static void glReleaseShaderCompiler()
/*  145:     */  {
/*  146: 146 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  147: 147 */    long function_pointer = caps.glReleaseShaderCompiler;
/*  148: 148 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  149: 149 */    nglReleaseShaderCompiler(function_pointer);
/*  150:     */  }
/*  151:     */  
/*  152:     */  static native void nglReleaseShaderCompiler(long paramLong);
/*  153:     */  
/*  154: 154 */  public static void glShaderBinary(IntBuffer shaders, int binaryformat, ByteBuffer binary) { ContextCapabilities caps = GLContext.getCapabilities();
/*  155: 155 */    long function_pointer = caps.glShaderBinary;
/*  156: 156 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  157: 157 */    BufferChecks.checkDirect(shaders);
/*  158: 158 */    BufferChecks.checkDirect(binary);
/*  159: 159 */    nglShaderBinary(shaders.remaining(), MemoryUtil.getAddress(shaders), binaryformat, MemoryUtil.getAddress(binary), binary.remaining(), function_pointer);
/*  160:     */  }
/*  161:     */  
/*  162:     */  static native void nglShaderBinary(int paramInt1, long paramLong1, int paramInt2, long paramLong2, int paramInt3, long paramLong3);
/*  163:     */  
/*  164: 164 */  public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) { ContextCapabilities caps = GLContext.getCapabilities();
/*  165: 165 */    long function_pointer = caps.glGetShaderPrecisionFormat;
/*  166: 166 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  167: 167 */    BufferChecks.checkBuffer(range, 2);
/*  168: 168 */    BufferChecks.checkBuffer(precision, 1);
/*  169: 169 */    nglGetShaderPrecisionFormat(shadertype, precisiontype, MemoryUtil.getAddress(range), MemoryUtil.getAddress(precision), function_pointer);
/*  170:     */  }
/*  171:     */  
/*  172:     */  static native void nglGetShaderPrecisionFormat(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  173:     */  
/*  174: 174 */  public static void glDepthRangef(float n, float f) { ContextCapabilities caps = GLContext.getCapabilities();
/*  175: 175 */    long function_pointer = caps.glDepthRangef;
/*  176: 176 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  177: 177 */    nglDepthRangef(n, f, function_pointer);
/*  178:     */  }
/*  179:     */  
/*  180:     */  static native void nglDepthRangef(float paramFloat1, float paramFloat2, long paramLong);
/*  181:     */  
/*  182: 182 */  public static void glClearDepthf(float d) { ContextCapabilities caps = GLContext.getCapabilities();
/*  183: 183 */    long function_pointer = caps.glClearDepthf;
/*  184: 184 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  185: 185 */    nglClearDepthf(d, function_pointer);
/*  186:     */  }
/*  187:     */  
/*  188:     */  static native void nglClearDepthf(float paramFloat, long paramLong);
/*  189:     */  
/*  190: 190 */  public static void glGetProgramBinary(int program, IntBuffer length, IntBuffer binaryFormat, ByteBuffer binary) { ContextCapabilities caps = GLContext.getCapabilities();
/*  191: 191 */    long function_pointer = caps.glGetProgramBinary;
/*  192: 192 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  193: 193 */    if (length != null)
/*  194: 194 */      BufferChecks.checkBuffer(length, 1);
/*  195: 195 */    BufferChecks.checkBuffer(binaryFormat, 1);
/*  196: 196 */    BufferChecks.checkDirect(binary);
/*  197: 197 */    nglGetProgramBinary(program, binary.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(binaryFormat), MemoryUtil.getAddress(binary), function_pointer);
/*  198:     */  }
/*  199:     */  
/*  200:     */  static native void nglGetProgramBinary(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*  201:     */  
/*  202: 202 */  public static void glProgramBinary(int program, int binaryFormat, ByteBuffer binary) { ContextCapabilities caps = GLContext.getCapabilities();
/*  203: 203 */    long function_pointer = caps.glProgramBinary;
/*  204: 204 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  205: 205 */    BufferChecks.checkDirect(binary);
/*  206: 206 */    nglProgramBinary(program, binaryFormat, MemoryUtil.getAddress(binary), binary.remaining(), function_pointer);
/*  207:     */  }
/*  208:     */  
/*  209:     */  static native void nglProgramBinary(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*  210:     */  
/*  211: 211 */  public static void glProgramParameteri(int program, int pname, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  212: 212 */    long function_pointer = caps.glProgramParameteri;
/*  213: 213 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  214: 214 */    nglProgramParameteri(program, pname, value, function_pointer);
/*  215:     */  }
/*  216:     */  
/*  217:     */  static native void nglProgramParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  218:     */  
/*  219: 219 */  public static void glUseProgramStages(int pipeline, int stages, int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  220: 220 */    long function_pointer = caps.glUseProgramStages;
/*  221: 221 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  222: 222 */    nglUseProgramStages(pipeline, stages, program, function_pointer);
/*  223:     */  }
/*  224:     */  
/*  225:     */  static native void nglUseProgramStages(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  226:     */  
/*  227: 227 */  public static void glActiveShaderProgram(int pipeline, int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  228: 228 */    long function_pointer = caps.glActiveShaderProgram;
/*  229: 229 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  230: 230 */    nglActiveShaderProgram(pipeline, program, function_pointer);
/*  231:     */  }
/*  232:     */  
/*  234:     */  static native void nglActiveShaderProgram(int paramInt1, int paramInt2, long paramLong);
/*  235:     */  
/*  236:     */  public static int glCreateShaderProgram(int type, ByteBuffer string)
/*  237:     */  {
/*  238: 238 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  239: 239 */    long function_pointer = caps.glCreateShaderProgramv;
/*  240: 240 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  241: 241 */    BufferChecks.checkDirect(string);
/*  242: 242 */    BufferChecks.checkNullTerminated(string);
/*  243: 243 */    int __result = nglCreateShaderProgramv(type, 1, MemoryUtil.getAddress(string), function_pointer);
/*  244: 244 */    return __result;
/*  245:     */  }
/*  246:     */  
/*  249:     */  static native int nglCreateShaderProgramv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  250:     */  
/*  252:     */  public static int glCreateShaderProgram(int type, int count, ByteBuffer strings)
/*  253:     */  {
/*  254: 254 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  255: 255 */    long function_pointer = caps.glCreateShaderProgramv;
/*  256: 256 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  257: 257 */    BufferChecks.checkDirect(strings);
/*  258: 258 */    BufferChecks.checkNullTerminated(strings, count);
/*  259: 259 */    int __result = nglCreateShaderProgramv2(type, count, MemoryUtil.getAddress(strings), function_pointer);
/*  260: 260 */    return __result;
/*  261:     */  }
/*  262:     */  
/*  263:     */  static native int nglCreateShaderProgramv2(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  264:     */  
/*  265:     */  public static int glCreateShaderProgram(int type, ByteBuffer[] strings) {
/*  266: 266 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  267: 267 */    long function_pointer = caps.glCreateShaderProgramv;
/*  268: 268 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  269: 269 */    BufferChecks.checkArray(strings, 1);
/*  270: 270 */    int __result = nglCreateShaderProgramv3(type, strings.length, strings, function_pointer);
/*  271: 271 */    return __result;
/*  272:     */  }
/*  273:     */  
/*  274:     */  static native int nglCreateShaderProgramv3(int paramInt1, int paramInt2, ByteBuffer[] paramArrayOfByteBuffer, long paramLong);
/*  275:     */  
/*  276:     */  public static int glCreateShaderProgram(int type, CharSequence string) {
/*  277: 277 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  278: 278 */    long function_pointer = caps.glCreateShaderProgramv;
/*  279: 279 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  280: 280 */    int __result = nglCreateShaderProgramv(type, 1, APIUtil.getBufferNT(caps, string), function_pointer);
/*  281: 281 */    return __result;
/*  282:     */  }
/*  283:     */  
/*  284:     */  public static int glCreateShaderProgram(int type, CharSequence[] strings)
/*  285:     */  {
/*  286: 286 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  287: 287 */    long function_pointer = caps.glCreateShaderProgramv;
/*  288: 288 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  289: 289 */    BufferChecks.checkArray(strings);
/*  290: 290 */    int __result = nglCreateShaderProgramv2(type, strings.length, APIUtil.getBufferNT(caps, strings), function_pointer);
/*  291: 291 */    return __result;
/*  292:     */  }
/*  293:     */  
/*  294:     */  public static void glBindProgramPipeline(int pipeline) {
/*  295: 295 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  296: 296 */    long function_pointer = caps.glBindProgramPipeline;
/*  297: 297 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  298: 298 */    nglBindProgramPipeline(pipeline, function_pointer);
/*  299:     */  }
/*  300:     */  
/*  301:     */  static native void nglBindProgramPipeline(int paramInt, long paramLong);
/*  302:     */  
/*  303: 303 */  public static void glDeleteProgramPipelines(IntBuffer pipelines) { ContextCapabilities caps = GLContext.getCapabilities();
/*  304: 304 */    long function_pointer = caps.glDeleteProgramPipelines;
/*  305: 305 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  306: 306 */    BufferChecks.checkDirect(pipelines);
/*  307: 307 */    nglDeleteProgramPipelines(pipelines.remaining(), MemoryUtil.getAddress(pipelines), function_pointer);
/*  308:     */  }
/*  309:     */  
/*  310:     */  static native void nglDeleteProgramPipelines(int paramInt, long paramLong1, long paramLong2);
/*  311:     */  
/*  312:     */  public static void glDeleteProgramPipelines(int pipeline) {
/*  313: 313 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  314: 314 */    long function_pointer = caps.glDeleteProgramPipelines;
/*  315: 315 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  316: 316 */    nglDeleteProgramPipelines(1, APIUtil.getInt(caps, pipeline), function_pointer);
/*  317:     */  }
/*  318:     */  
/*  319:     */  public static void glGenProgramPipelines(IntBuffer pipelines) {
/*  320: 320 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  321: 321 */    long function_pointer = caps.glGenProgramPipelines;
/*  322: 322 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  323: 323 */    BufferChecks.checkDirect(pipelines);
/*  324: 324 */    nglGenProgramPipelines(pipelines.remaining(), MemoryUtil.getAddress(pipelines), function_pointer);
/*  325:     */  }
/*  326:     */  
/*  327:     */  static native void nglGenProgramPipelines(int paramInt, long paramLong1, long paramLong2);
/*  328:     */  
/*  329:     */  public static int glGenProgramPipelines() {
/*  330: 330 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  331: 331 */    long function_pointer = caps.glGenProgramPipelines;
/*  332: 332 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  333: 333 */    IntBuffer pipelines = APIUtil.getBufferInt(caps);
/*  334: 334 */    nglGenProgramPipelines(1, MemoryUtil.getAddress(pipelines), function_pointer);
/*  335: 335 */    return pipelines.get(0);
/*  336:     */  }
/*  337:     */  
/*  338:     */  public static boolean glIsProgramPipeline(int pipeline) {
/*  339: 339 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  340: 340 */    long function_pointer = caps.glIsProgramPipeline;
/*  341: 341 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  342: 342 */    boolean __result = nglIsProgramPipeline(pipeline, function_pointer);
/*  343: 343 */    return __result;
/*  344:     */  }
/*  345:     */  
/*  346:     */  static native boolean nglIsProgramPipeline(int paramInt, long paramLong);
/*  347:     */  
/*  348: 348 */  public static void glGetProgramPipeline(int pipeline, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  349: 349 */    long function_pointer = caps.glGetProgramPipelineiv;
/*  350: 350 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  351: 351 */    BufferChecks.checkBuffer(params, 1);
/*  352: 352 */    nglGetProgramPipelineiv(pipeline, pname, MemoryUtil.getAddress(params), function_pointer);
/*  353:     */  }
/*  354:     */  
/*  355:     */  static native void nglGetProgramPipelineiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  356:     */  
/*  357:     */  public static int glGetProgramPipelinei(int pipeline, int pname) {
/*  358: 358 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  359: 359 */    long function_pointer = caps.glGetProgramPipelineiv;
/*  360: 360 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  361: 361 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  362: 362 */    nglGetProgramPipelineiv(pipeline, pname, MemoryUtil.getAddress(params), function_pointer);
/*  363: 363 */    return params.get(0);
/*  364:     */  }
/*  365:     */  
/*  366:     */  public static void glProgramUniform1i(int program, int location, int v0) {
/*  367: 367 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  368: 368 */    long function_pointer = caps.glProgramUniform1i;
/*  369: 369 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  370: 370 */    nglProgramUniform1i(program, location, v0, function_pointer);
/*  371:     */  }
/*  372:     */  
/*  373:     */  static native void nglProgramUniform1i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  374:     */  
/*  375: 375 */  public static void glProgramUniform2i(int program, int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  376: 376 */    long function_pointer = caps.glProgramUniform2i;
/*  377: 377 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  378: 378 */    nglProgramUniform2i(program, location, v0, v1, function_pointer);
/*  379:     */  }
/*  380:     */  
/*  381:     */  static native void nglProgramUniform2i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  382:     */  
/*  383: 383 */  public static void glProgramUniform3i(int program, int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  384: 384 */    long function_pointer = caps.glProgramUniform3i;
/*  385: 385 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  386: 386 */    nglProgramUniform3i(program, location, v0, v1, v2, function_pointer);
/*  387:     */  }
/*  388:     */  
/*  389:     */  static native void nglProgramUniform3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  390:     */  
/*  391: 391 */  public static void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  392: 392 */    long function_pointer = caps.glProgramUniform4i;
/*  393: 393 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  394: 394 */    nglProgramUniform4i(program, location, v0, v1, v2, v3, function_pointer);
/*  395:     */  }
/*  396:     */  
/*  397:     */  static native void nglProgramUniform4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  398:     */  
/*  399: 399 */  public static void glProgramUniform1f(int program, int location, float v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  400: 400 */    long function_pointer = caps.glProgramUniform1f;
/*  401: 401 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  402: 402 */    nglProgramUniform1f(program, location, v0, function_pointer);
/*  403:     */  }
/*  404:     */  
/*  405:     */  static native void nglProgramUniform1f(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*  406:     */  
/*  407: 407 */  public static void glProgramUniform2f(int program, int location, float v0, float v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  408: 408 */    long function_pointer = caps.glProgramUniform2f;
/*  409: 409 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  410: 410 */    nglProgramUniform2f(program, location, v0, v1, function_pointer);
/*  411:     */  }
/*  412:     */  
/*  413:     */  static native void nglProgramUniform2f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, long paramLong);
/*  414:     */  
/*  415: 415 */  public static void glProgramUniform3f(int program, int location, float v0, float v1, float v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  416: 416 */    long function_pointer = caps.glProgramUniform3f;
/*  417: 417 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  418: 418 */    nglProgramUniform3f(program, location, v0, v1, v2, function_pointer);
/*  419:     */  }
/*  420:     */  
/*  421:     */  static native void nglProgramUniform3f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  422:     */  
/*  423: 423 */  public static void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  424: 424 */    long function_pointer = caps.glProgramUniform4f;
/*  425: 425 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  426: 426 */    nglProgramUniform4f(program, location, v0, v1, v2, v3, function_pointer);
/*  427:     */  }
/*  428:     */  
/*  429:     */  static native void nglProgramUniform4f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  430:     */  
/*  431: 431 */  public static void glProgramUniform1d(int program, int location, double v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  432: 432 */    long function_pointer = caps.glProgramUniform1d;
/*  433: 433 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  434: 434 */    nglProgramUniform1d(program, location, v0, function_pointer);
/*  435:     */  }
/*  436:     */  
/*  437:     */  static native void nglProgramUniform1d(int paramInt1, int paramInt2, double paramDouble, long paramLong);
/*  438:     */  
/*  439: 439 */  public static void glProgramUniform2d(int program, int location, double v0, double v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  440: 440 */    long function_pointer = caps.glProgramUniform2d;
/*  441: 441 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  442: 442 */    nglProgramUniform2d(program, location, v0, v1, function_pointer);
/*  443:     */  }
/*  444:     */  
/*  445:     */  static native void nglProgramUniform2d(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, long paramLong);
/*  446:     */  
/*  447: 447 */  public static void glProgramUniform3d(int program, int location, double v0, double v1, double v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  448: 448 */    long function_pointer = caps.glProgramUniform3d;
/*  449: 449 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  450: 450 */    nglProgramUniform3d(program, location, v0, v1, v2, function_pointer);
/*  451:     */  }
/*  452:     */  
/*  453:     */  static native void nglProgramUniform3d(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  454:     */  
/*  455: 455 */  public static void glProgramUniform4d(int program, int location, double v0, double v1, double v2, double v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  456: 456 */    long function_pointer = caps.glProgramUniform4d;
/*  457: 457 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  458: 458 */    nglProgramUniform4d(program, location, v0, v1, v2, v3, function_pointer);
/*  459:     */  }
/*  460:     */  
/*  461:     */  static native void nglProgramUniform4d(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*  462:     */  
/*  463: 463 */  public static void glProgramUniform1(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  464: 464 */    long function_pointer = caps.glProgramUniform1iv;
/*  465: 465 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  466: 466 */    BufferChecks.checkDirect(value);
/*  467: 467 */    nglProgramUniform1iv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  468:     */  }
/*  469:     */  
/*  470:     */  static native void nglProgramUniform1iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  471:     */  
/*  472: 472 */  public static void glProgramUniform2(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  473: 473 */    long function_pointer = caps.glProgramUniform2iv;
/*  474: 474 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  475: 475 */    BufferChecks.checkDirect(value);
/*  476: 476 */    nglProgramUniform2iv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*  477:     */  }
/*  478:     */  
/*  479:     */  static native void nglProgramUniform2iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  480:     */  
/*  481: 481 */  public static void glProgramUniform3(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  482: 482 */    long function_pointer = caps.glProgramUniform3iv;
/*  483: 483 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  484: 484 */    BufferChecks.checkDirect(value);
/*  485: 485 */    nglProgramUniform3iv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*  486:     */  }
/*  487:     */  
/*  488:     */  static native void nglProgramUniform3iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  489:     */  
/*  490: 490 */  public static void glProgramUniform4(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  491: 491 */    long function_pointer = caps.glProgramUniform4iv;
/*  492: 492 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  493: 493 */    BufferChecks.checkDirect(value);
/*  494: 494 */    nglProgramUniform4iv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*  495:     */  }
/*  496:     */  
/*  497:     */  static native void nglProgramUniform4iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  498:     */  
/*  499: 499 */  public static void glProgramUniform1(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  500: 500 */    long function_pointer = caps.glProgramUniform1fv;
/*  501: 501 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  502: 502 */    BufferChecks.checkDirect(value);
/*  503: 503 */    nglProgramUniform1fv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  504:     */  }
/*  505:     */  
/*  506:     */  static native void nglProgramUniform1fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  507:     */  
/*  508: 508 */  public static void glProgramUniform2(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  509: 509 */    long function_pointer = caps.glProgramUniform2fv;
/*  510: 510 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  511: 511 */    BufferChecks.checkDirect(value);
/*  512: 512 */    nglProgramUniform2fv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*  513:     */  }
/*  514:     */  
/*  515:     */  static native void nglProgramUniform2fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  516:     */  
/*  517: 517 */  public static void glProgramUniform3(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  518: 518 */    long function_pointer = caps.glProgramUniform3fv;
/*  519: 519 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  520: 520 */    BufferChecks.checkDirect(value);
/*  521: 521 */    nglProgramUniform3fv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*  522:     */  }
/*  523:     */  
/*  524:     */  static native void nglProgramUniform3fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  525:     */  
/*  526: 526 */  public static void glProgramUniform4(int program, int location, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  527: 527 */    long function_pointer = caps.glProgramUniform4fv;
/*  528: 528 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  529: 529 */    BufferChecks.checkDirect(value);
/*  530: 530 */    nglProgramUniform4fv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*  531:     */  }
/*  532:     */  
/*  533:     */  static native void nglProgramUniform4fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  534:     */  
/*  535: 535 */  public static void glProgramUniform1(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  536: 536 */    long function_pointer = caps.glProgramUniform1dv;
/*  537: 537 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  538: 538 */    BufferChecks.checkDirect(value);
/*  539: 539 */    nglProgramUniform1dv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  540:     */  }
/*  541:     */  
/*  542:     */  static native void nglProgramUniform1dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  543:     */  
/*  544: 544 */  public static void glProgramUniform2(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  545: 545 */    long function_pointer = caps.glProgramUniform2dv;
/*  546: 546 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  547: 547 */    BufferChecks.checkDirect(value);
/*  548: 548 */    nglProgramUniform2dv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*  549:     */  }
/*  550:     */  
/*  551:     */  static native void nglProgramUniform2dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  552:     */  
/*  553: 553 */  public static void glProgramUniform3(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  554: 554 */    long function_pointer = caps.glProgramUniform3dv;
/*  555: 555 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  556: 556 */    BufferChecks.checkDirect(value);
/*  557: 557 */    nglProgramUniform3dv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*  558:     */  }
/*  559:     */  
/*  560:     */  static native void nglProgramUniform3dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  561:     */  
/*  562: 562 */  public static void glProgramUniform4(int program, int location, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  563: 563 */    long function_pointer = caps.glProgramUniform4dv;
/*  564: 564 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  565: 565 */    BufferChecks.checkDirect(value);
/*  566: 566 */    nglProgramUniform4dv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*  567:     */  }
/*  568:     */  
/*  569:     */  static native void nglProgramUniform4dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  570:     */  
/*  571: 571 */  public static void glProgramUniform1ui(int program, int location, int v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  572: 572 */    long function_pointer = caps.glProgramUniform1ui;
/*  573: 573 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  574: 574 */    nglProgramUniform1ui(program, location, v0, function_pointer);
/*  575:     */  }
/*  576:     */  
/*  577:     */  static native void nglProgramUniform1ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  578:     */  
/*  579: 579 */  public static void glProgramUniform2ui(int program, int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  580: 580 */    long function_pointer = caps.glProgramUniform2ui;
/*  581: 581 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  582: 582 */    nglProgramUniform2ui(program, location, v0, v1, function_pointer);
/*  583:     */  }
/*  584:     */  
/*  585:     */  static native void nglProgramUniform2ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  586:     */  
/*  587: 587 */  public static void glProgramUniform3ui(int program, int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  588: 588 */    long function_pointer = caps.glProgramUniform3ui;
/*  589: 589 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  590: 590 */    nglProgramUniform3ui(program, location, v0, v1, v2, function_pointer);
/*  591:     */  }
/*  592:     */  
/*  593:     */  static native void nglProgramUniform3ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  594:     */  
/*  595: 595 */  public static void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  596: 596 */    long function_pointer = caps.glProgramUniform4ui;
/*  597: 597 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  598: 598 */    nglProgramUniform4ui(program, location, v0, v1, v2, v3, function_pointer);
/*  599:     */  }
/*  600:     */  
/*  601:     */  static native void nglProgramUniform4ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*  602:     */  
/*  603: 603 */  public static void glProgramUniform1u(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  604: 604 */    long function_pointer = caps.glProgramUniform1uiv;
/*  605: 605 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  606: 606 */    BufferChecks.checkDirect(value);
/*  607: 607 */    nglProgramUniform1uiv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*  608:     */  }
/*  609:     */  
/*  610:     */  static native void nglProgramUniform1uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  611:     */  
/*  612: 612 */  public static void glProgramUniform2u(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  613: 613 */    long function_pointer = caps.glProgramUniform2uiv;
/*  614: 614 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  615: 615 */    BufferChecks.checkDirect(value);
/*  616: 616 */    nglProgramUniform2uiv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*  617:     */  }
/*  618:     */  
/*  619:     */  static native void nglProgramUniform2uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  620:     */  
/*  621: 621 */  public static void glProgramUniform3u(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  622: 622 */    long function_pointer = caps.glProgramUniform3uiv;
/*  623: 623 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  624: 624 */    BufferChecks.checkDirect(value);
/*  625: 625 */    nglProgramUniform3uiv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*  626:     */  }
/*  627:     */  
/*  628:     */  static native void nglProgramUniform3uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  629:     */  
/*  630: 630 */  public static void glProgramUniform4u(int program, int location, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  631: 631 */    long function_pointer = caps.glProgramUniform4uiv;
/*  632: 632 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  633: 633 */    BufferChecks.checkDirect(value);
/*  634: 634 */    nglProgramUniform4uiv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*  635:     */  }
/*  636:     */  
/*  637:     */  static native void nglProgramUniform4uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*  638:     */  
/*  639: 639 */  public static void glProgramUniformMatrix2(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  640: 640 */    long function_pointer = caps.glProgramUniformMatrix2fv;
/*  641: 641 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  642: 642 */    BufferChecks.checkDirect(value);
/*  643: 643 */    nglProgramUniformMatrix2fv(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  644:     */  }
/*  645:     */  
/*  646:     */  static native void nglProgramUniformMatrix2fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  647:     */  
/*  648: 648 */  public static void glProgramUniformMatrix3(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  649: 649 */    long function_pointer = caps.glProgramUniformMatrix3fv;
/*  650: 650 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  651: 651 */    BufferChecks.checkDirect(value);
/*  652: 652 */    nglProgramUniformMatrix3fv(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  653:     */  }
/*  654:     */  
/*  655:     */  static native void nglProgramUniformMatrix3fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  656:     */  
/*  657: 657 */  public static void glProgramUniformMatrix4(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  658: 658 */    long function_pointer = caps.glProgramUniformMatrix4fv;
/*  659: 659 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  660: 660 */    BufferChecks.checkDirect(value);
/*  661: 661 */    nglProgramUniformMatrix4fv(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  662:     */  }
/*  663:     */  
/*  664:     */  static native void nglProgramUniformMatrix4fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  665:     */  
/*  666: 666 */  public static void glProgramUniformMatrix2(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  667: 667 */    long function_pointer = caps.glProgramUniformMatrix2dv;
/*  668: 668 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  669: 669 */    BufferChecks.checkDirect(value);
/*  670: 670 */    nglProgramUniformMatrix2dv(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  671:     */  }
/*  672:     */  
/*  673:     */  static native void nglProgramUniformMatrix2dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  674:     */  
/*  675: 675 */  public static void glProgramUniformMatrix3(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  676: 676 */    long function_pointer = caps.glProgramUniformMatrix3dv;
/*  677: 677 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  678: 678 */    BufferChecks.checkDirect(value);
/*  679: 679 */    nglProgramUniformMatrix3dv(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  680:     */  }
/*  681:     */  
/*  682:     */  static native void nglProgramUniformMatrix3dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  683:     */  
/*  684: 684 */  public static void glProgramUniformMatrix4(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  685: 685 */    long function_pointer = caps.glProgramUniformMatrix4dv;
/*  686: 686 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  687: 687 */    BufferChecks.checkDirect(value);
/*  688: 688 */    nglProgramUniformMatrix4dv(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  689:     */  }
/*  690:     */  
/*  691:     */  static native void nglProgramUniformMatrix4dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  692:     */  
/*  693: 693 */  public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  694: 694 */    long function_pointer = caps.glProgramUniformMatrix2x3fv;
/*  695: 695 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  696: 696 */    BufferChecks.checkDirect(value);
/*  697: 697 */    nglProgramUniformMatrix2x3fv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  698:     */  }
/*  699:     */  
/*  700:     */  static native void nglProgramUniformMatrix2x3fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  701:     */  
/*  702: 702 */  public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  703: 703 */    long function_pointer = caps.glProgramUniformMatrix3x2fv;
/*  704: 704 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  705: 705 */    BufferChecks.checkDirect(value);
/*  706: 706 */    nglProgramUniformMatrix3x2fv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  707:     */  }
/*  708:     */  
/*  709:     */  static native void nglProgramUniformMatrix3x2fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  710:     */  
/*  711: 711 */  public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  712: 712 */    long function_pointer = caps.glProgramUniformMatrix2x4fv;
/*  713: 713 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  714: 714 */    BufferChecks.checkDirect(value);
/*  715: 715 */    nglProgramUniformMatrix2x4fv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  716:     */  }
/*  717:     */  
/*  718:     */  static native void nglProgramUniformMatrix2x4fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  719:     */  
/*  720: 720 */  public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  721: 721 */    long function_pointer = caps.glProgramUniformMatrix4x2fv;
/*  722: 722 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  723: 723 */    BufferChecks.checkDirect(value);
/*  724: 724 */    nglProgramUniformMatrix4x2fv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  725:     */  }
/*  726:     */  
/*  727:     */  static native void nglProgramUniformMatrix4x2fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  728:     */  
/*  729: 729 */  public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  730: 730 */    long function_pointer = caps.glProgramUniformMatrix3x4fv;
/*  731: 731 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  732: 732 */    BufferChecks.checkDirect(value);
/*  733: 733 */    nglProgramUniformMatrix3x4fv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  734:     */  }
/*  735:     */  
/*  736:     */  static native void nglProgramUniformMatrix3x4fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  737:     */  
/*  738: 738 */  public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, FloatBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  739: 739 */    long function_pointer = caps.glProgramUniformMatrix4x3fv;
/*  740: 740 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  741: 741 */    BufferChecks.checkDirect(value);
/*  742: 742 */    nglProgramUniformMatrix4x3fv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  743:     */  }
/*  744:     */  
/*  745:     */  static native void nglProgramUniformMatrix4x3fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  746:     */  
/*  747: 747 */  public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  748: 748 */    long function_pointer = caps.glProgramUniformMatrix2x3dv;
/*  749: 749 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  750: 750 */    BufferChecks.checkDirect(value);
/*  751: 751 */    nglProgramUniformMatrix2x3dv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  752:     */  }
/*  753:     */  
/*  754:     */  static native void nglProgramUniformMatrix2x3dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  755:     */  
/*  756: 756 */  public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  757: 757 */    long function_pointer = caps.glProgramUniformMatrix3x2dv;
/*  758: 758 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  759: 759 */    BufferChecks.checkDirect(value);
/*  760: 760 */    nglProgramUniformMatrix3x2dv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  761:     */  }
/*  762:     */  
/*  763:     */  static native void nglProgramUniformMatrix3x2dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  764:     */  
/*  765: 765 */  public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  766: 766 */    long function_pointer = caps.glProgramUniformMatrix2x4dv;
/*  767: 767 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  768: 768 */    BufferChecks.checkDirect(value);
/*  769: 769 */    nglProgramUniformMatrix2x4dv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  770:     */  }
/*  771:     */  
/*  772:     */  static native void nglProgramUniformMatrix2x4dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  773:     */  
/*  774: 774 */  public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  775: 775 */    long function_pointer = caps.glProgramUniformMatrix4x2dv;
/*  776: 776 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  777: 777 */    BufferChecks.checkDirect(value);
/*  778: 778 */    nglProgramUniformMatrix4x2dv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  779:     */  }
/*  780:     */  
/*  781:     */  static native void nglProgramUniformMatrix4x2dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  782:     */  
/*  783: 783 */  public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  784: 784 */    long function_pointer = caps.glProgramUniformMatrix3x4dv;
/*  785: 785 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  786: 786 */    BufferChecks.checkDirect(value);
/*  787: 787 */    nglProgramUniformMatrix3x4dv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  788:     */  }
/*  789:     */  
/*  790:     */  static native void nglProgramUniformMatrix3x4dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  791:     */  
/*  792: 792 */  public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, DoubleBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/*  793: 793 */    long function_pointer = caps.glProgramUniformMatrix4x3dv;
/*  794: 794 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  795: 795 */    BufferChecks.checkDirect(value);
/*  796: 796 */    nglProgramUniformMatrix4x3dv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*  797:     */  }
/*  798:     */  
/*  799:     */  static native void nglProgramUniformMatrix4x3dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*  800:     */  
/*  801: 801 */  public static void glValidateProgramPipeline(int pipeline) { ContextCapabilities caps = GLContext.getCapabilities();
/*  802: 802 */    long function_pointer = caps.glValidateProgramPipeline;
/*  803: 803 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  804: 804 */    nglValidateProgramPipeline(pipeline, function_pointer);
/*  805:     */  }
/*  806:     */  
/*  807:     */  static native void nglValidateProgramPipeline(int paramInt, long paramLong);
/*  808:     */  
/*  809: 809 */  public static void glGetProgramPipelineInfoLog(int pipeline, IntBuffer length, ByteBuffer infoLog) { ContextCapabilities caps = GLContext.getCapabilities();
/*  810: 810 */    long function_pointer = caps.glGetProgramPipelineInfoLog;
/*  811: 811 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  812: 812 */    if (length != null)
/*  813: 813 */      BufferChecks.checkBuffer(length, 1);
/*  814: 814 */    BufferChecks.checkDirect(infoLog);
/*  815: 815 */    nglGetProgramPipelineInfoLog(pipeline, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  816:     */  }
/*  817:     */  
/*  818:     */  static native void nglGetProgramPipelineInfoLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  819:     */  
/*  820:     */  public static String glGetProgramPipelineInfoLog(int pipeline, int bufSize) {
/*  821: 821 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  822: 822 */    long function_pointer = caps.glGetProgramPipelineInfoLog;
/*  823: 823 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  824: 824 */    IntBuffer infoLog_length = APIUtil.getLengths(caps);
/*  825: 825 */    ByteBuffer infoLog = APIUtil.getBufferByte(caps, bufSize);
/*  826: 826 */    nglGetProgramPipelineInfoLog(pipeline, bufSize, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  827: 827 */    infoLog.limit(infoLog_length.get(0));
/*  828: 828 */    return APIUtil.getString(caps, infoLog);
/*  829:     */  }
/*  830:     */  
/*  831:     */  public static void glVertexAttribL1d(int index, double x) {
/*  832: 832 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  833: 833 */    long function_pointer = caps.glVertexAttribL1d;
/*  834: 834 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  835: 835 */    nglVertexAttribL1d(index, x, function_pointer);
/*  836:     */  }
/*  837:     */  
/*  838:     */  static native void nglVertexAttribL1d(int paramInt, double paramDouble, long paramLong);
/*  839:     */  
/*  840: 840 */  public static void glVertexAttribL2d(int index, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  841: 841 */    long function_pointer = caps.glVertexAttribL2d;
/*  842: 842 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  843: 843 */    nglVertexAttribL2d(index, x, y, function_pointer);
/*  844:     */  }
/*  845:     */  
/*  846:     */  static native void nglVertexAttribL2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*  847:     */  
/*  848: 848 */  public static void glVertexAttribL3d(int index, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  849: 849 */    long function_pointer = caps.glVertexAttribL3d;
/*  850: 850 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  851: 851 */    nglVertexAttribL3d(index, x, y, z, function_pointer);
/*  852:     */  }
/*  853:     */  
/*  854:     */  static native void nglVertexAttribL3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  855:     */  
/*  856: 856 */  public static void glVertexAttribL4d(int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  857: 857 */    long function_pointer = caps.glVertexAttribL4d;
/*  858: 858 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  859: 859 */    nglVertexAttribL4d(index, x, y, z, w, function_pointer);
/*  860:     */  }
/*  861:     */  
/*  862:     */  static native void nglVertexAttribL4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*  863:     */  
/*  864: 864 */  public static void glVertexAttribL1(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  865: 865 */    long function_pointer = caps.glVertexAttribL1dv;
/*  866: 866 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  867: 867 */    BufferChecks.checkBuffer(v, 1);
/*  868: 868 */    nglVertexAttribL1dv(index, MemoryUtil.getAddress(v), function_pointer);
/*  869:     */  }
/*  870:     */  
/*  871:     */  static native void nglVertexAttribL1dv(int paramInt, long paramLong1, long paramLong2);
/*  872:     */  
/*  873: 873 */  public static void glVertexAttribL2(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  874: 874 */    long function_pointer = caps.glVertexAttribL2dv;
/*  875: 875 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  876: 876 */    BufferChecks.checkBuffer(v, 2);
/*  877: 877 */    nglVertexAttribL2dv(index, MemoryUtil.getAddress(v), function_pointer);
/*  878:     */  }
/*  879:     */  
/*  880:     */  static native void nglVertexAttribL2dv(int paramInt, long paramLong1, long paramLong2);
/*  881:     */  
/*  882: 882 */  public static void glVertexAttribL3(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  883: 883 */    long function_pointer = caps.glVertexAttribL3dv;
/*  884: 884 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  885: 885 */    BufferChecks.checkBuffer(v, 3);
/*  886: 886 */    nglVertexAttribL3dv(index, MemoryUtil.getAddress(v), function_pointer);
/*  887:     */  }
/*  888:     */  
/*  889:     */  static native void nglVertexAttribL3dv(int paramInt, long paramLong1, long paramLong2);
/*  890:     */  
/*  891: 891 */  public static void glVertexAttribL4(int index, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  892: 892 */    long function_pointer = caps.glVertexAttribL4dv;
/*  893: 893 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  894: 894 */    BufferChecks.checkBuffer(v, 4);
/*  895: 895 */    nglVertexAttribL4dv(index, MemoryUtil.getAddress(v), function_pointer);
/*  896:     */  }
/*  897:     */  
/*  898:     */  static native void nglVertexAttribL4dv(int paramInt, long paramLong1, long paramLong2);
/*  899:     */  
/*  900: 900 */  public static void glVertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  901: 901 */    long function_pointer = caps.glVertexAttribLPointer;
/*  902: 902 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  903: 903 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  904: 904 */    BufferChecks.checkDirect(pointer);
/*  905: 905 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = pointer;
/*  906: 906 */    nglVertexAttribLPointer(index, size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer); }
/*  907:     */  
/*  908:     */  static native void nglVertexAttribLPointer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  909:     */  
/*  910: 910 */  public static void glVertexAttribLPointer(int index, int size, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  911: 911 */    long function_pointer = caps.glVertexAttribLPointer;
/*  912: 912 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  913: 913 */    GLChecks.ensureArrayVBOenabled(caps);
/*  914: 914 */    nglVertexAttribLPointerBO(index, size, 5130, stride, pointer_buffer_offset, function_pointer);
/*  915:     */  }
/*  916:     */  
/*  917:     */  static native void nglVertexAttribLPointerBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*  918:     */  
/*  919: 919 */  public static void glGetVertexAttribL(int index, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  920: 920 */    long function_pointer = caps.glGetVertexAttribLdv;
/*  921: 921 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  922: 922 */    BufferChecks.checkBuffer(params, 4);
/*  923: 923 */    nglGetVertexAttribLdv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  924:     */  }
/*  925:     */  
/*  926:     */  static native void nglGetVertexAttribLdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  927:     */  
/*  928: 928 */  public static void glViewportArray(int first, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  929: 929 */    long function_pointer = caps.glViewportArrayv;
/*  930: 930 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  931: 931 */    BufferChecks.checkDirect(v);
/*  932: 932 */    nglViewportArrayv(first, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*  933:     */  }
/*  934:     */  
/*  935:     */  static native void nglViewportArrayv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  936:     */  
/*  937: 937 */  public static void glViewportIndexedf(int index, float x, float y, float w, float h) { ContextCapabilities caps = GLContext.getCapabilities();
/*  938: 938 */    long function_pointer = caps.glViewportIndexedf;
/*  939: 939 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  940: 940 */    nglViewportIndexedf(index, x, y, w, h, function_pointer);
/*  941:     */  }
/*  942:     */  
/*  943:     */  static native void nglViewportIndexedf(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  944:     */  
/*  945: 945 */  public static void glViewportIndexed(int index, FloatBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  946: 946 */    long function_pointer = caps.glViewportIndexedfv;
/*  947: 947 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  948: 948 */    BufferChecks.checkBuffer(v, 4);
/*  949: 949 */    nglViewportIndexedfv(index, MemoryUtil.getAddress(v), function_pointer);
/*  950:     */  }
/*  951:     */  
/*  952:     */  static native void nglViewportIndexedfv(int paramInt, long paramLong1, long paramLong2);
/*  953:     */  
/*  954: 954 */  public static void glScissorArray(int first, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  955: 955 */    long function_pointer = caps.glScissorArrayv;
/*  956: 956 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  957: 957 */    BufferChecks.checkDirect(v);
/*  958: 958 */    nglScissorArrayv(first, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*  959:     */  }
/*  960:     */  
/*  961:     */  static native void nglScissorArrayv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  962:     */  
/*  963: 963 */  public static void glScissorIndexed(int index, int left, int bottom, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/*  964: 964 */    long function_pointer = caps.glScissorIndexed;
/*  965: 965 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  966: 966 */    nglScissorIndexed(index, left, bottom, width, height, function_pointer);
/*  967:     */  }
/*  968:     */  
/*  969:     */  static native void nglScissorIndexed(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  970:     */  
/*  971: 971 */  public static void glScissorIndexed(int index, IntBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  972: 972 */    long function_pointer = caps.glScissorIndexedv;
/*  973: 973 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  974: 974 */    BufferChecks.checkBuffer(v, 4);
/*  975: 975 */    nglScissorIndexedv(index, MemoryUtil.getAddress(v), function_pointer);
/*  976:     */  }
/*  977:     */  
/*  978:     */  static native void nglScissorIndexedv(int paramInt, long paramLong1, long paramLong2);
/*  979:     */  
/*  980: 980 */  public static void glDepthRangeArray(int first, DoubleBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  981: 981 */    long function_pointer = caps.glDepthRangeArrayv;
/*  982: 982 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  983: 983 */    BufferChecks.checkDirect(v);
/*  984: 984 */    nglDepthRangeArrayv(first, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/*  985:     */  }
/*  986:     */  
/*  987:     */  static native void nglDepthRangeArrayv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  988:     */  
/*  989: 989 */  public static void glDepthRangeIndexed(int index, double n, double f) { ContextCapabilities caps = GLContext.getCapabilities();
/*  990: 990 */    long function_pointer = caps.glDepthRangeIndexed;
/*  991: 991 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  992: 992 */    nglDepthRangeIndexed(index, n, f, function_pointer);
/*  993:     */  }
/*  994:     */  
/*  995:     */  static native void nglDepthRangeIndexed(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*  996:     */  
/*  997: 997 */  public static void glGetFloat(int target, int index, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  998: 998 */    long function_pointer = caps.glGetFloati_v;
/*  999: 999 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1000:1000 */    BufferChecks.checkDirect(data);
/* 1001:1001 */    nglGetFloati_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/* 1002:     */  }
/* 1003:     */  
/* 1004:     */  static native void nglGetFloati_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1005:     */  
/* 1006:     */  public static float glGetFloat(int target, int index) {
/* 1007:1007 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1008:1008 */    long function_pointer = caps.glGetFloati_v;
/* 1009:1009 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1010:1010 */    FloatBuffer data = APIUtil.getBufferFloat(caps);
/* 1011:1011 */    nglGetFloati_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/* 1012:1012 */    return data.get(0);
/* 1013:     */  }
/* 1014:     */  
/* 1015:     */  public static void glGetDouble(int target, int index, DoubleBuffer data) {
/* 1016:1016 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1017:1017 */    long function_pointer = caps.glGetDoublei_v;
/* 1018:1018 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1019:1019 */    BufferChecks.checkDirect(data);
/* 1020:1020 */    nglGetDoublei_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/* 1021:     */  }
/* 1022:     */  
/* 1023:     */  static native void nglGetDoublei_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 1024:     */  
/* 1025:     */  public static double glGetDouble(int target, int index) {
/* 1026:1026 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1027:1027 */    long function_pointer = caps.glGetDoublei_v;
/* 1028:1028 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1029:1029 */    DoubleBuffer data = APIUtil.getBufferDouble(caps);
/* 1030:1030 */    nglGetDoublei_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/* 1031:1031 */    return data.get(0);
/* 1032:     */  }
/* 1033:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL41
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */