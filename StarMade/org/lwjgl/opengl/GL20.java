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
/*  102:     */public final class GL20
/*  103:     */{
/*  104:     */  public static final int GL_SHADING_LANGUAGE_VERSION = 35724;
/*  105:     */  public static final int GL_CURRENT_PROGRAM = 35725;
/*  106:     */  public static final int GL_SHADER_TYPE = 35663;
/*  107:     */  public static final int GL_DELETE_STATUS = 35712;
/*  108:     */  public static final int GL_COMPILE_STATUS = 35713;
/*  109:     */  public static final int GL_LINK_STATUS = 35714;
/*  110:     */  public static final int GL_VALIDATE_STATUS = 35715;
/*  111:     */  public static final int GL_INFO_LOG_LENGTH = 35716;
/*  112:     */  public static final int GL_ATTACHED_SHADERS = 35717;
/*  113:     */  public static final int GL_ACTIVE_UNIFORMS = 35718;
/*  114:     */  public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;
/*  115:     */  public static final int GL_ACTIVE_ATTRIBUTES = 35721;
/*  116:     */  public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;
/*  117:     */  public static final int GL_SHADER_SOURCE_LENGTH = 35720;
/*  118:     */  public static final int GL_SHADER_OBJECT = 35656;
/*  119:     */  public static final int GL_FLOAT_VEC2 = 35664;
/*  120:     */  public static final int GL_FLOAT_VEC3 = 35665;
/*  121:     */  public static final int GL_FLOAT_VEC4 = 35666;
/*  122:     */  public static final int GL_INT_VEC2 = 35667;
/*  123:     */  public static final int GL_INT_VEC3 = 35668;
/*  124:     */  public static final int GL_INT_VEC4 = 35669;
/*  125:     */  public static final int GL_BOOL = 35670;
/*  126:     */  public static final int GL_BOOL_VEC2 = 35671;
/*  127:     */  public static final int GL_BOOL_VEC3 = 35672;
/*  128:     */  public static final int GL_BOOL_VEC4 = 35673;
/*  129:     */  public static final int GL_FLOAT_MAT2 = 35674;
/*  130:     */  public static final int GL_FLOAT_MAT3 = 35675;
/*  131:     */  public static final int GL_FLOAT_MAT4 = 35676;
/*  132:     */  public static final int GL_SAMPLER_1D = 35677;
/*  133:     */  public static final int GL_SAMPLER_2D = 35678;
/*  134:     */  public static final int GL_SAMPLER_3D = 35679;
/*  135:     */  public static final int GL_SAMPLER_CUBE = 35680;
/*  136:     */  public static final int GL_SAMPLER_1D_SHADOW = 35681;
/*  137:     */  public static final int GL_SAMPLER_2D_SHADOW = 35682;
/*  138:     */  public static final int GL_VERTEX_SHADER = 35633;
/*  139:     */  public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 35658;
/*  140:     */  public static final int GL_MAX_VARYING_FLOATS = 35659;
/*  141:     */  public static final int GL_MAX_VERTEX_ATTRIBS = 34921;
/*  142:     */  public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
/*  143:     */  public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
/*  144:     */  public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;
/*  145:     */  public static final int GL_MAX_TEXTURE_COORDS = 34929;
/*  146:     */  public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 34370;
/*  147:     */  public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 34371;
/*  148:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;
/*  149:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;
/*  150:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
/*  151:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;
/*  152:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;
/*  153:     */  public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;
/*  154:     */  public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;
/*  155:     */  public static final int GL_FRAGMENT_SHADER = 35632;
/*  156:     */  public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 35657;
/*  157:     */  public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 35723;
/*  158:     */  public static final int GL_MAX_DRAW_BUFFERS = 34852;
/*  159:     */  public static final int GL_DRAW_BUFFER0 = 34853;
/*  160:     */  public static final int GL_DRAW_BUFFER1 = 34854;
/*  161:     */  public static final int GL_DRAW_BUFFER2 = 34855;
/*  162:     */  public static final int GL_DRAW_BUFFER3 = 34856;
/*  163:     */  public static final int GL_DRAW_BUFFER4 = 34857;
/*  164:     */  public static final int GL_DRAW_BUFFER5 = 34858;
/*  165:     */  public static final int GL_DRAW_BUFFER6 = 34859;
/*  166:     */  public static final int GL_DRAW_BUFFER7 = 34860;
/*  167:     */  public static final int GL_DRAW_BUFFER8 = 34861;
/*  168:     */  public static final int GL_DRAW_BUFFER9 = 34862;
/*  169:     */  public static final int GL_DRAW_BUFFER10 = 34863;
/*  170:     */  public static final int GL_DRAW_BUFFER11 = 34864;
/*  171:     */  public static final int GL_DRAW_BUFFER12 = 34865;
/*  172:     */  public static final int GL_DRAW_BUFFER13 = 34866;
/*  173:     */  public static final int GL_DRAW_BUFFER14 = 34867;
/*  174:     */  public static final int GL_DRAW_BUFFER15 = 34868;
/*  175:     */  public static final int GL_POINT_SPRITE = 34913;
/*  176:     */  public static final int GL_COORD_REPLACE = 34914;
/*  177:     */  public static final int GL_POINT_SPRITE_COORD_ORIGIN = 36000;
/*  178:     */  public static final int GL_LOWER_LEFT = 36001;
/*  179:     */  public static final int GL_UPPER_LEFT = 36002;
/*  180:     */  public static final int GL_STENCIL_BACK_FUNC = 34816;
/*  181:     */  public static final int GL_STENCIL_BACK_FAIL = 34817;
/*  182:     */  public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;
/*  183:     */  public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;
/*  184:     */  public static final int GL_STENCIL_BACK_REF = 36003;
/*  185:     */  public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
/*  186:     */  public static final int GL_STENCIL_BACK_WRITEMASK = 36005;
/*  187:     */  public static final int GL_BLEND_EQUATION_RGB = 32777;
/*  188:     */  public static final int GL_BLEND_EQUATION_ALPHA = 34877;
/*  189:     */  
/*  190:     */  public static void glShaderSource(int shader, ByteBuffer string)
/*  191:     */  {
/*  192: 192 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  193: 193 */    long function_pointer = caps.glShaderSource;
/*  194: 194 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  195: 195 */    BufferChecks.checkDirect(string);
/*  196: 196 */    nglShaderSource(shader, 1, MemoryUtil.getAddress(string), string.remaining(), function_pointer);
/*  197:     */  }
/*  198:     */  
/*  199:     */  static native void nglShaderSource(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*  200:     */  
/*  201:     */  public static void glShaderSource(int shader, CharSequence string) {
/*  202: 202 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  203: 203 */    long function_pointer = caps.glShaderSource;
/*  204: 204 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  205: 205 */    nglShaderSource(shader, 1, APIUtil.getBuffer(caps, string), string.length(), function_pointer);
/*  206:     */  }
/*  207:     */  
/*  208:     */  public static void glShaderSource(int shader, CharSequence[] strings)
/*  209:     */  {
/*  210: 210 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  211: 211 */    long function_pointer = caps.glShaderSource;
/*  212: 212 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  213: 213 */    BufferChecks.checkArray(strings);
/*  214: 214 */    nglShaderSource3(shader, strings.length, APIUtil.getBuffer(caps, strings), APIUtil.getLengths(caps, strings), function_pointer);
/*  215:     */  }
/*  216:     */  
/*  217:     */  static native void nglShaderSource3(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  218:     */  
/*  219: 219 */  public static int glCreateShader(int type) { ContextCapabilities caps = GLContext.getCapabilities();
/*  220: 220 */    long function_pointer = caps.glCreateShader;
/*  221: 221 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  222: 222 */    int __result = nglCreateShader(type, function_pointer);
/*  223: 223 */    return __result;
/*  224:     */  }
/*  225:     */  
/*  226:     */  static native int nglCreateShader(int paramInt, long paramLong);
/*  227:     */  
/*  228: 228 */  public static boolean glIsShader(int shader) { ContextCapabilities caps = GLContext.getCapabilities();
/*  229: 229 */    long function_pointer = caps.glIsShader;
/*  230: 230 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  231: 231 */    boolean __result = nglIsShader(shader, function_pointer);
/*  232: 232 */    return __result;
/*  233:     */  }
/*  234:     */  
/*  235:     */  static native boolean nglIsShader(int paramInt, long paramLong);
/*  236:     */  
/*  237: 237 */  public static void glCompileShader(int shader) { ContextCapabilities caps = GLContext.getCapabilities();
/*  238: 238 */    long function_pointer = caps.glCompileShader;
/*  239: 239 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  240: 240 */    nglCompileShader(shader, function_pointer);
/*  241:     */  }
/*  242:     */  
/*  243:     */  static native void nglCompileShader(int paramInt, long paramLong);
/*  244:     */  
/*  245: 245 */  public static void glDeleteShader(int shader) { ContextCapabilities caps = GLContext.getCapabilities();
/*  246: 246 */    long function_pointer = caps.glDeleteShader;
/*  247: 247 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  248: 248 */    nglDeleteShader(shader, function_pointer);
/*  249:     */  }
/*  250:     */  
/*  251:     */  static native void nglDeleteShader(int paramInt, long paramLong);
/*  252:     */  
/*  253: 253 */  public static int glCreateProgram() { ContextCapabilities caps = GLContext.getCapabilities();
/*  254: 254 */    long function_pointer = caps.glCreateProgram;
/*  255: 255 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  256: 256 */    int __result = nglCreateProgram(function_pointer);
/*  257: 257 */    return __result;
/*  258:     */  }
/*  259:     */  
/*  260:     */  static native int nglCreateProgram(long paramLong);
/*  261:     */  
/*  262: 262 */  public static boolean glIsProgram(int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  263: 263 */    long function_pointer = caps.glIsProgram;
/*  264: 264 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  265: 265 */    boolean __result = nglIsProgram(program, function_pointer);
/*  266: 266 */    return __result;
/*  267:     */  }
/*  268:     */  
/*  269:     */  static native boolean nglIsProgram(int paramInt, long paramLong);
/*  270:     */  
/*  271: 271 */  public static void glAttachShader(int program, int shader) { ContextCapabilities caps = GLContext.getCapabilities();
/*  272: 272 */    long function_pointer = caps.glAttachShader;
/*  273: 273 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  274: 274 */    nglAttachShader(program, shader, function_pointer);
/*  275:     */  }
/*  276:     */  
/*  277:     */  static native void nglAttachShader(int paramInt1, int paramInt2, long paramLong);
/*  278:     */  
/*  279: 279 */  public static void glDetachShader(int program, int shader) { ContextCapabilities caps = GLContext.getCapabilities();
/*  280: 280 */    long function_pointer = caps.glDetachShader;
/*  281: 281 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  282: 282 */    nglDetachShader(program, shader, function_pointer);
/*  283:     */  }
/*  284:     */  
/*  285:     */  static native void nglDetachShader(int paramInt1, int paramInt2, long paramLong);
/*  286:     */  
/*  287: 287 */  public static void glLinkProgram(int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  288: 288 */    long function_pointer = caps.glLinkProgram;
/*  289: 289 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  290: 290 */    nglLinkProgram(program, function_pointer);
/*  291:     */  }
/*  292:     */  
/*  293:     */  static native void nglLinkProgram(int paramInt, long paramLong);
/*  294:     */  
/*  295: 295 */  public static void glUseProgram(int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  296: 296 */    long function_pointer = caps.glUseProgram;
/*  297: 297 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  298: 298 */    nglUseProgram(program, function_pointer);
/*  299:     */  }
/*  300:     */  
/*  301:     */  static native void nglUseProgram(int paramInt, long paramLong);
/*  302:     */  
/*  303: 303 */  public static void glValidateProgram(int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  304: 304 */    long function_pointer = caps.glValidateProgram;
/*  305: 305 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  306: 306 */    nglValidateProgram(program, function_pointer);
/*  307:     */  }
/*  308:     */  
/*  309:     */  static native void nglValidateProgram(int paramInt, long paramLong);
/*  310:     */  
/*  311: 311 */  public static void glDeleteProgram(int program) { ContextCapabilities caps = GLContext.getCapabilities();
/*  312: 312 */    long function_pointer = caps.glDeleteProgram;
/*  313: 313 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  314: 314 */    nglDeleteProgram(program, function_pointer);
/*  315:     */  }
/*  316:     */  
/*  317:     */  static native void nglDeleteProgram(int paramInt, long paramLong);
/*  318:     */  
/*  319: 319 */  public static void glUniform1f(int location, float v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  320: 320 */    long function_pointer = caps.glUniform1f;
/*  321: 321 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  322: 322 */    nglUniform1f(location, v0, function_pointer);
/*  323:     */  }
/*  324:     */  
/*  325:     */  static native void nglUniform1f(int paramInt, float paramFloat, long paramLong);
/*  326:     */  
/*  327: 327 */  public static void glUniform2f(int location, float v0, float v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  328: 328 */    long function_pointer = caps.glUniform2f;
/*  329: 329 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  330: 330 */    nglUniform2f(location, v0, v1, function_pointer);
/*  331:     */  }
/*  332:     */  
/*  333:     */  static native void nglUniform2f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*  334:     */  
/*  335: 335 */  public static void glUniform3f(int location, float v0, float v1, float v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  336: 336 */    long function_pointer = caps.glUniform3f;
/*  337: 337 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  338: 338 */    nglUniform3f(location, v0, v1, v2, function_pointer);
/*  339:     */  }
/*  340:     */  
/*  341:     */  static native void nglUniform3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  342:     */  
/*  343: 343 */  public static void glUniform4f(int location, float v0, float v1, float v2, float v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  344: 344 */    long function_pointer = caps.glUniform4f;
/*  345: 345 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  346: 346 */    nglUniform4f(location, v0, v1, v2, v3, function_pointer);
/*  347:     */  }
/*  348:     */  
/*  349:     */  static native void nglUniform4f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  350:     */  
/*  351: 351 */  public static void glUniform1i(int location, int v0) { ContextCapabilities caps = GLContext.getCapabilities();
/*  352: 352 */    long function_pointer = caps.glUniform1i;
/*  353: 353 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  354: 354 */    nglUniform1i(location, v0, function_pointer);
/*  355:     */  }
/*  356:     */  
/*  357:     */  static native void nglUniform1i(int paramInt1, int paramInt2, long paramLong);
/*  358:     */  
/*  359: 359 */  public static void glUniform2i(int location, int v0, int v1) { ContextCapabilities caps = GLContext.getCapabilities();
/*  360: 360 */    long function_pointer = caps.glUniform2i;
/*  361: 361 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  362: 362 */    nglUniform2i(location, v0, v1, function_pointer);
/*  363:     */  }
/*  364:     */  
/*  365:     */  static native void nglUniform2i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  366:     */  
/*  367: 367 */  public static void glUniform3i(int location, int v0, int v1, int v2) { ContextCapabilities caps = GLContext.getCapabilities();
/*  368: 368 */    long function_pointer = caps.glUniform3i;
/*  369: 369 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  370: 370 */    nglUniform3i(location, v0, v1, v2, function_pointer);
/*  371:     */  }
/*  372:     */  
/*  373:     */  static native void nglUniform3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  374:     */  
/*  375: 375 */  public static void glUniform4i(int location, int v0, int v1, int v2, int v3) { ContextCapabilities caps = GLContext.getCapabilities();
/*  376: 376 */    long function_pointer = caps.glUniform4i;
/*  377: 377 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  378: 378 */    nglUniform4i(location, v0, v1, v2, v3, function_pointer);
/*  379:     */  }
/*  380:     */  
/*  381:     */  static native void nglUniform4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*  382:     */  
/*  383: 383 */  public static void glUniform1(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  384: 384 */    long function_pointer = caps.glUniform1fv;
/*  385: 385 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  386: 386 */    BufferChecks.checkDirect(values);
/*  387: 387 */    nglUniform1fv(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*  388:     */  }
/*  389:     */  
/*  390:     */  static native void nglUniform1fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  391:     */  
/*  392: 392 */  public static void glUniform2(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  393: 393 */    long function_pointer = caps.glUniform2fv;
/*  394: 394 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  395: 395 */    BufferChecks.checkDirect(values);
/*  396: 396 */    nglUniform2fv(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/*  397:     */  }
/*  398:     */  
/*  399:     */  static native void nglUniform2fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  400:     */  
/*  401: 401 */  public static void glUniform3(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  402: 402 */    long function_pointer = caps.glUniform3fv;
/*  403: 403 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  404: 404 */    BufferChecks.checkDirect(values);
/*  405: 405 */    nglUniform3fv(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/*  406:     */  }
/*  407:     */  
/*  408:     */  static native void nglUniform3fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  409:     */  
/*  410: 410 */  public static void glUniform4(int location, FloatBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  411: 411 */    long function_pointer = caps.glUniform4fv;
/*  412: 412 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  413: 413 */    BufferChecks.checkDirect(values);
/*  414: 414 */    nglUniform4fv(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/*  415:     */  }
/*  416:     */  
/*  417:     */  static native void nglUniform4fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  418:     */  
/*  419: 419 */  public static void glUniform1(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  420: 420 */    long function_pointer = caps.glUniform1iv;
/*  421: 421 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  422: 422 */    BufferChecks.checkDirect(values);
/*  423: 423 */    nglUniform1iv(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*  424:     */  }
/*  425:     */  
/*  426:     */  static native void nglUniform1iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  427:     */  
/*  428: 428 */  public static void glUniform2(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  429: 429 */    long function_pointer = caps.glUniform2iv;
/*  430: 430 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  431: 431 */    BufferChecks.checkDirect(values);
/*  432: 432 */    nglUniform2iv(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/*  433:     */  }
/*  434:     */  
/*  435:     */  static native void nglUniform2iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  436:     */  
/*  437: 437 */  public static void glUniform3(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  438: 438 */    long function_pointer = caps.glUniform3iv;
/*  439: 439 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  440: 440 */    BufferChecks.checkDirect(values);
/*  441: 441 */    nglUniform3iv(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/*  442:     */  }
/*  443:     */  
/*  444:     */  static native void nglUniform3iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  445:     */  
/*  446: 446 */  public static void glUniform4(int location, IntBuffer values) { ContextCapabilities caps = GLContext.getCapabilities();
/*  447: 447 */    long function_pointer = caps.glUniform4iv;
/*  448: 448 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  449: 449 */    BufferChecks.checkDirect(values);
/*  450: 450 */    nglUniform4iv(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/*  451:     */  }
/*  452:     */  
/*  453:     */  static native void nglUniform4iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  454:     */  
/*  455: 455 */  public static void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  456: 456 */    long function_pointer = caps.glUniformMatrix2fv;
/*  457: 457 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  458: 458 */    BufferChecks.checkDirect(matrices);
/*  459: 459 */    nglUniformMatrix2fv(location, matrices.remaining() >> 2, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  460:     */  }
/*  461:     */  
/*  462:     */  static native void nglUniformMatrix2fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  463:     */  
/*  464: 464 */  public static void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  465: 465 */    long function_pointer = caps.glUniformMatrix3fv;
/*  466: 466 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  467: 467 */    BufferChecks.checkDirect(matrices);
/*  468: 468 */    nglUniformMatrix3fv(location, matrices.remaining() / 9, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  469:     */  }
/*  470:     */  
/*  471:     */  static native void nglUniformMatrix3fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  472:     */  
/*  473: 473 */  public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  474: 474 */    long function_pointer = caps.glUniformMatrix4fv;
/*  475: 475 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  476: 476 */    BufferChecks.checkDirect(matrices);
/*  477: 477 */    nglUniformMatrix4fv(location, matrices.remaining() >> 4, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*  478:     */  }
/*  479:     */  
/*  480:     */  static native void nglUniformMatrix4fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*  481:     */  
/*  482: 482 */  public static void glGetShader(int shader, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  483: 483 */    long function_pointer = caps.glGetShaderiv;
/*  484: 484 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  485: 485 */    BufferChecks.checkDirect(params);
/*  486: 486 */    nglGetShaderiv(shader, pname, MemoryUtil.getAddress(params), function_pointer);
/*  487:     */  }
/*  488:     */  
/*  491:     */  static native void nglGetShaderiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  492:     */  
/*  494:     */  @Deprecated
/*  495:     */  public static int glGetShader(int shader, int pname)
/*  496:     */  {
/*  497: 497 */    return glGetShaderi(shader, pname);
/*  498:     */  }
/*  499:     */  
/*  500:     */  public static int glGetShaderi(int shader, int pname)
/*  501:     */  {
/*  502: 502 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  503: 503 */    long function_pointer = caps.glGetShaderiv;
/*  504: 504 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  505: 505 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  506: 506 */    nglGetShaderiv(shader, pname, MemoryUtil.getAddress(params), function_pointer);
/*  507: 507 */    return params.get(0);
/*  508:     */  }
/*  509:     */  
/*  510:     */  public static void glGetProgram(int program, int pname, IntBuffer params) {
/*  511: 511 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  512: 512 */    long function_pointer = caps.glGetProgramiv;
/*  513: 513 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  514: 514 */    BufferChecks.checkDirect(params);
/*  515: 515 */    nglGetProgramiv(program, pname, MemoryUtil.getAddress(params), function_pointer);
/*  516:     */  }
/*  517:     */  
/*  520:     */  static native void nglGetProgramiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  521:     */  
/*  523:     */  @Deprecated
/*  524:     */  public static int glGetProgram(int program, int pname)
/*  525:     */  {
/*  526: 526 */    return glGetProgrami(program, pname);
/*  527:     */  }
/*  528:     */  
/*  529:     */  public static int glGetProgrami(int program, int pname)
/*  530:     */  {
/*  531: 531 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  532: 532 */    long function_pointer = caps.glGetProgramiv;
/*  533: 533 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  534: 534 */    IntBuffer params = APIUtil.getBufferInt(caps);
/*  535: 535 */    nglGetProgramiv(program, pname, MemoryUtil.getAddress(params), function_pointer);
/*  536: 536 */    return params.get(0);
/*  537:     */  }
/*  538:     */  
/*  539:     */  public static void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
/*  540: 540 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  541: 541 */    long function_pointer = caps.glGetShaderInfoLog;
/*  542: 542 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  543: 543 */    if (length != null)
/*  544: 544 */      BufferChecks.checkBuffer(length, 1);
/*  545: 545 */    BufferChecks.checkDirect(infoLog);
/*  546: 546 */    nglGetShaderInfoLog(shader, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  547:     */  }
/*  548:     */  
/*  549:     */  static native void nglGetShaderInfoLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  550:     */  
/*  551:     */  public static String glGetShaderInfoLog(int shader, int maxLength) {
/*  552: 552 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  553: 553 */    long function_pointer = caps.glGetShaderInfoLog;
/*  554: 554 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  555: 555 */    IntBuffer infoLog_length = APIUtil.getLengths(caps);
/*  556: 556 */    ByteBuffer infoLog = APIUtil.getBufferByte(caps, maxLength);
/*  557: 557 */    nglGetShaderInfoLog(shader, maxLength, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  558: 558 */    infoLog.limit(infoLog_length.get(0));
/*  559: 559 */    return APIUtil.getString(caps, infoLog);
/*  560:     */  }
/*  561:     */  
/*  562:     */  public static void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
/*  563: 563 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  564: 564 */    long function_pointer = caps.glGetProgramInfoLog;
/*  565: 565 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  566: 566 */    if (length != null)
/*  567: 567 */      BufferChecks.checkBuffer(length, 1);
/*  568: 568 */    BufferChecks.checkDirect(infoLog);
/*  569: 569 */    nglGetProgramInfoLog(program, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  570:     */  }
/*  571:     */  
/*  572:     */  static native void nglGetProgramInfoLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  573:     */  
/*  574:     */  public static String glGetProgramInfoLog(int program, int maxLength) {
/*  575: 575 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  576: 576 */    long function_pointer = caps.glGetProgramInfoLog;
/*  577: 577 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  578: 578 */    IntBuffer infoLog_length = APIUtil.getLengths(caps);
/*  579: 579 */    ByteBuffer infoLog = APIUtil.getBufferByte(caps, maxLength);
/*  580: 580 */    nglGetProgramInfoLog(program, maxLength, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  581: 581 */    infoLog.limit(infoLog_length.get(0));
/*  582: 582 */    return APIUtil.getString(caps, infoLog);
/*  583:     */  }
/*  584:     */  
/*  585:     */  public static void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
/*  586: 586 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  587: 587 */    long function_pointer = caps.glGetAttachedShaders;
/*  588: 588 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  589: 589 */    if (count != null)
/*  590: 590 */      BufferChecks.checkBuffer(count, 1);
/*  591: 591 */    BufferChecks.checkDirect(shaders);
/*  592: 592 */    nglGetAttachedShaders(program, shaders.remaining(), MemoryUtil.getAddressSafe(count), MemoryUtil.getAddress(shaders), function_pointer);
/*  593:     */  }
/*  594:     */  
/*  598:     */  static native void nglGetAttachedShaders(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  599:     */  
/*  602:     */  public static int glGetUniformLocation(int program, ByteBuffer name)
/*  603:     */  {
/*  604: 604 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  605: 605 */    long function_pointer = caps.glGetUniformLocation;
/*  606: 606 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  607: 607 */    BufferChecks.checkBuffer(name, 1);
/*  608: 608 */    BufferChecks.checkNullTerminated(name);
/*  609: 609 */    int __result = nglGetUniformLocation(program, MemoryUtil.getAddress(name), function_pointer);
/*  610: 610 */    return __result;
/*  611:     */  }
/*  612:     */  
/*  613:     */  static native int nglGetUniformLocation(int paramInt, long paramLong1, long paramLong2);
/*  614:     */  
/*  615:     */  public static int glGetUniformLocation(int program, CharSequence name) {
/*  616: 616 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  617: 617 */    long function_pointer = caps.glGetUniformLocation;
/*  618: 618 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  619: 619 */    int __result = nglGetUniformLocation(program, APIUtil.getBufferNT(caps, name), function_pointer);
/*  620: 620 */    return __result;
/*  621:     */  }
/*  622:     */  
/*  623:     */  public static void glGetActiveUniform(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/*  624: 624 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  625: 625 */    long function_pointer = caps.glGetActiveUniform;
/*  626: 626 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  627: 627 */    if (length != null)
/*  628: 628 */      BufferChecks.checkBuffer(length, 1);
/*  629: 629 */    BufferChecks.checkBuffer(size, 1);
/*  630: 630 */    BufferChecks.checkBuffer(type, 1);
/*  631: 631 */    BufferChecks.checkDirect(name);
/*  632: 632 */    nglGetActiveUniform(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*  633:     */  }
/*  634:     */  
/*  637:     */  static native void nglGetActiveUniform(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  638:     */  
/*  640:     */  public static String glGetActiveUniform(int program, int index, int maxLength, IntBuffer sizeType)
/*  641:     */  {
/*  642: 642 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  643: 643 */    long function_pointer = caps.glGetActiveUniform;
/*  644: 644 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  645: 645 */    BufferChecks.checkBuffer(sizeType, 2);
/*  646: 646 */    IntBuffer name_length = APIUtil.getLengths(caps);
/*  647: 647 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/*  648: 648 */    nglGetActiveUniform(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/*  649: 649 */    name.limit(name_length.get(0));
/*  650: 650 */    return APIUtil.getString(caps, name);
/*  651:     */  }
/*  652:     */  
/*  657:     */  public static String glGetActiveUniform(int program, int index, int maxLength)
/*  658:     */  {
/*  659: 659 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  660: 660 */    long function_pointer = caps.glGetActiveUniform;
/*  661: 661 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  662: 662 */    IntBuffer name_length = APIUtil.getLengths(caps);
/*  663: 663 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/*  664: 664 */    nglGetActiveUniform(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/*  665: 665 */    name.limit(name_length.get(0));
/*  666: 666 */    return APIUtil.getString(caps, name);
/*  667:     */  }
/*  668:     */  
/*  673:     */  public static int glGetActiveUniformSize(int program, int index)
/*  674:     */  {
/*  675: 675 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  676: 676 */    long function_pointer = caps.glGetActiveUniform;
/*  677: 677 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  678: 678 */    IntBuffer size = APIUtil.getBufferInt(caps);
/*  679: 679 */    nglGetActiveUniform(program, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/*  680: 680 */    return size.get(0);
/*  681:     */  }
/*  682:     */  
/*  687:     */  public static int glGetActiveUniformType(int program, int index)
/*  688:     */  {
/*  689: 689 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  690: 690 */    long function_pointer = caps.glGetActiveUniform;
/*  691: 691 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  692: 692 */    IntBuffer type = APIUtil.getBufferInt(caps);
/*  693: 693 */    nglGetActiveUniform(program, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/*  694: 694 */    return type.get(0);
/*  695:     */  }
/*  696:     */  
/*  697:     */  public static void glGetUniform(int program, int location, FloatBuffer params) {
/*  698: 698 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  699: 699 */    long function_pointer = caps.glGetUniformfv;
/*  700: 700 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  701: 701 */    BufferChecks.checkDirect(params);
/*  702: 702 */    nglGetUniformfv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*  703:     */  }
/*  704:     */  
/*  705:     */  static native void nglGetUniformfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  706:     */  
/*  707: 707 */  public static void glGetUniform(int program, int location, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  708: 708 */    long function_pointer = caps.glGetUniformiv;
/*  709: 709 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  710: 710 */    BufferChecks.checkDirect(params);
/*  711: 711 */    nglGetUniformiv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*  712:     */  }
/*  713:     */  
/*  714:     */  static native void nglGetUniformiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  715:     */  
/*  716: 716 */  public static void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) { ContextCapabilities caps = GLContext.getCapabilities();
/*  717: 717 */    long function_pointer = caps.glGetShaderSource;
/*  718: 718 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  719: 719 */    if (length != null)
/*  720: 720 */      BufferChecks.checkBuffer(length, 1);
/*  721: 721 */    BufferChecks.checkDirect(source);
/*  722: 722 */    nglGetShaderSource(shader, source.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(source), function_pointer);
/*  723:     */  }
/*  724:     */  
/*  725:     */  static native void nglGetShaderSource(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  726:     */  
/*  727:     */  public static String glGetShaderSource(int shader, int maxLength) {
/*  728: 728 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  729: 729 */    long function_pointer = caps.glGetShaderSource;
/*  730: 730 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  731: 731 */    IntBuffer source_length = APIUtil.getLengths(caps);
/*  732: 732 */    ByteBuffer source = APIUtil.getBufferByte(caps, maxLength);
/*  733: 733 */    nglGetShaderSource(shader, maxLength, MemoryUtil.getAddress0(source_length), MemoryUtil.getAddress(source), function_pointer);
/*  734: 734 */    source.limit(source_length.get(0));
/*  735: 735 */    return APIUtil.getString(caps, source);
/*  736:     */  }
/*  737:     */  
/*  738:     */  public static void glVertexAttrib1s(int index, short x) {
/*  739: 739 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  740: 740 */    long function_pointer = caps.glVertexAttrib1s;
/*  741: 741 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  742: 742 */    nglVertexAttrib1s(index, x, function_pointer);
/*  743:     */  }
/*  744:     */  
/*  745:     */  static native void nglVertexAttrib1s(int paramInt, short paramShort, long paramLong);
/*  746:     */  
/*  747: 747 */  public static void glVertexAttrib1f(int index, float x) { ContextCapabilities caps = GLContext.getCapabilities();
/*  748: 748 */    long function_pointer = caps.glVertexAttrib1f;
/*  749: 749 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  750: 750 */    nglVertexAttrib1f(index, x, function_pointer);
/*  751:     */  }
/*  752:     */  
/*  753:     */  static native void nglVertexAttrib1f(int paramInt, float paramFloat, long paramLong);
/*  754:     */  
/*  755: 755 */  public static void glVertexAttrib1d(int index, double x) { ContextCapabilities caps = GLContext.getCapabilities();
/*  756: 756 */    long function_pointer = caps.glVertexAttrib1d;
/*  757: 757 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  758: 758 */    nglVertexAttrib1d(index, x, function_pointer);
/*  759:     */  }
/*  760:     */  
/*  761:     */  static native void nglVertexAttrib1d(int paramInt, double paramDouble, long paramLong);
/*  762:     */  
/*  763: 763 */  public static void glVertexAttrib2s(int index, short x, short y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  764: 764 */    long function_pointer = caps.glVertexAttrib2s;
/*  765: 765 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  766: 766 */    nglVertexAttrib2s(index, x, y, function_pointer);
/*  767:     */  }
/*  768:     */  
/*  769:     */  static native void nglVertexAttrib2s(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*  770:     */  
/*  771: 771 */  public static void glVertexAttrib2f(int index, float x, float y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  772: 772 */    long function_pointer = caps.glVertexAttrib2f;
/*  773: 773 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  774: 774 */    nglVertexAttrib2f(index, x, y, function_pointer);
/*  775:     */  }
/*  776:     */  
/*  777:     */  static native void nglVertexAttrib2f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*  778:     */  
/*  779: 779 */  public static void glVertexAttrib2d(int index, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  780: 780 */    long function_pointer = caps.glVertexAttrib2d;
/*  781: 781 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  782: 782 */    nglVertexAttrib2d(index, x, y, function_pointer);
/*  783:     */  }
/*  784:     */  
/*  785:     */  static native void nglVertexAttrib2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*  786:     */  
/*  787: 787 */  public static void glVertexAttrib3s(int index, short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  788: 788 */    long function_pointer = caps.glVertexAttrib3s;
/*  789: 789 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  790: 790 */    nglVertexAttrib3s(index, x, y, z, function_pointer);
/*  791:     */  }
/*  792:     */  
/*  793:     */  static native void nglVertexAttrib3s(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*  794:     */  
/*  795: 795 */  public static void glVertexAttrib3f(int index, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  796: 796 */    long function_pointer = caps.glVertexAttrib3f;
/*  797: 797 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  798: 798 */    nglVertexAttrib3f(index, x, y, z, function_pointer);
/*  799:     */  }
/*  800:     */  
/*  801:     */  static native void nglVertexAttrib3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  802:     */  
/*  803: 803 */  public static void glVertexAttrib3d(int index, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  804: 804 */    long function_pointer = caps.glVertexAttrib3d;
/*  805: 805 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  806: 806 */    nglVertexAttrib3d(index, x, y, z, function_pointer);
/*  807:     */  }
/*  808:     */  
/*  809:     */  static native void nglVertexAttrib3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  810:     */  
/*  811: 811 */  public static void glVertexAttrib4s(int index, short x, short y, short z, short w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  812: 812 */    long function_pointer = caps.glVertexAttrib4s;
/*  813: 813 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  814: 814 */    nglVertexAttrib4s(index, x, y, z, w, function_pointer);
/*  815:     */  }
/*  816:     */  
/*  817:     */  static native void nglVertexAttrib4s(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*  818:     */  
/*  819: 819 */  public static void glVertexAttrib4f(int index, float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  820: 820 */    long function_pointer = caps.glVertexAttrib4f;
/*  821: 821 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  822: 822 */    nglVertexAttrib4f(index, x, y, z, w, function_pointer);
/*  823:     */  }
/*  824:     */  
/*  825:     */  static native void nglVertexAttrib4f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  826:     */  
/*  827: 827 */  public static void glVertexAttrib4d(int index, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  828: 828 */    long function_pointer = caps.glVertexAttrib4d;
/*  829: 829 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  830: 830 */    nglVertexAttrib4d(index, x, y, z, w, function_pointer);
/*  831:     */  }
/*  832:     */  
/*  833:     */  static native void nglVertexAttrib4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*  834:     */  
/*  835: 835 */  public static void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  836: 836 */    long function_pointer = caps.glVertexAttrib4Nub;
/*  837: 837 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  838: 838 */    nglVertexAttrib4Nub(index, x, y, z, w, function_pointer);
/*  839:     */  }
/*  840:     */  
/*  841:     */  static native void nglVertexAttrib4Nub(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*  842:     */  
/*  843: 843 */  public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  844: 844 */    long function_pointer = caps.glVertexAttribPointer;
/*  845: 845 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  846: 846 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  847: 847 */    BufferChecks.checkDirect(buffer);
/*  848: 848 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  849: 849 */    nglVertexAttribPointer(index, size, 5130, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  850:     */  }
/*  851:     */  
/*  852: 852 */  public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, FloatBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  853: 853 */    long function_pointer = caps.glVertexAttribPointer;
/*  854: 854 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  855: 855 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  856: 856 */    BufferChecks.checkDirect(buffer);
/*  857: 857 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  858: 858 */    nglVertexAttribPointer(index, size, 5126, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  859:     */  }
/*  860:     */  
/*  861: 861 */  public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  862: 862 */    long function_pointer = caps.glVertexAttribPointer;
/*  863: 863 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  864: 864 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  865: 865 */    BufferChecks.checkDirect(buffer);
/*  866: 866 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  867: 867 */    nglVertexAttribPointer(index, size, unsigned ? 5121 : 5120, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  868:     */  }
/*  869:     */  
/*  870: 870 */  public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  871: 871 */    long function_pointer = caps.glVertexAttribPointer;
/*  872: 872 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  873: 873 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  874: 874 */    BufferChecks.checkDirect(buffer);
/*  875: 875 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  876: 876 */    nglVertexAttribPointer(index, size, unsigned ? 5125 : 5124, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  877:     */  }
/*  878:     */  
/*  879: 879 */  public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  880: 880 */    long function_pointer = caps.glVertexAttribPointer;
/*  881: 881 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  882: 882 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  883: 883 */    BufferChecks.checkDirect(buffer);
/*  884: 884 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  885: 885 */    nglVertexAttribPointer(index, size, unsigned ? 5123 : 5122, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer); }
/*  886:     */  
/*  887:     */  static native void nglVertexAttribPointer(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/*  888:     */  
/*  889: 889 */  public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  890: 890 */    long function_pointer = caps.glVertexAttribPointer;
/*  891: 891 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  892: 892 */    GLChecks.ensureArrayVBOenabled(caps);
/*  893: 893 */    nglVertexAttribPointerBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer);
/*  894:     */  }
/*  895:     */  
/*  896:     */  static native void nglVertexAttribPointerBO(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/*  897:     */  
/*  898:     */  public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, ByteBuffer buffer) {
/*  899: 899 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  900: 900 */    long function_pointer = caps.glVertexAttribPointer;
/*  901: 901 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  902: 902 */    GLChecks.ensureArrayVBOdisabled(caps);
/*  903: 903 */    BufferChecks.checkDirect(buffer);
/*  904: 904 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  905: 905 */    nglVertexAttribPointer(index, size, type, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*  906:     */  }
/*  907:     */  
/*  908:     */  public static void glEnableVertexAttribArray(int index) {
/*  909: 909 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  910: 910 */    long function_pointer = caps.glEnableVertexAttribArray;
/*  911: 911 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  912: 912 */    nglEnableVertexAttribArray(index, function_pointer);
/*  913:     */  }
/*  914:     */  
/*  915:     */  static native void nglEnableVertexAttribArray(int paramInt, long paramLong);
/*  916:     */  
/*  917: 917 */  public static void glDisableVertexAttribArray(int index) { ContextCapabilities caps = GLContext.getCapabilities();
/*  918: 918 */    long function_pointer = caps.glDisableVertexAttribArray;
/*  919: 919 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  920: 920 */    nglDisableVertexAttribArray(index, function_pointer);
/*  921:     */  }
/*  922:     */  
/*  923:     */  static native void nglDisableVertexAttribArray(int paramInt, long paramLong);
/*  924:     */  
/*  925: 925 */  public static void glGetVertexAttrib(int index, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  926: 926 */    long function_pointer = caps.glGetVertexAttribfv;
/*  927: 927 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  928: 928 */    BufferChecks.checkBuffer(params, 4);
/*  929: 929 */    nglGetVertexAttribfv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  930:     */  }
/*  931:     */  
/*  932:     */  static native void nglGetVertexAttribfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  933:     */  
/*  934: 934 */  public static void glGetVertexAttrib(int index, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  935: 935 */    long function_pointer = caps.glGetVertexAttribdv;
/*  936: 936 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  937: 937 */    BufferChecks.checkBuffer(params, 4);
/*  938: 938 */    nglGetVertexAttribdv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  939:     */  }
/*  940:     */  
/*  941:     */  static native void nglGetVertexAttribdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  942:     */  
/*  943: 943 */  public static void glGetVertexAttrib(int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  944: 944 */    long function_pointer = caps.glGetVertexAttribiv;
/*  945: 945 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  946: 946 */    BufferChecks.checkBuffer(params, 4);
/*  947: 947 */    nglGetVertexAttribiv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  948:     */  }
/*  949:     */  
/*  950:     */  static native void nglGetVertexAttribiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  951:     */  
/*  952: 952 */  public static ByteBuffer glGetVertexAttribPointer(int index, int pname, long result_size) { ContextCapabilities caps = GLContext.getCapabilities();
/*  953: 953 */    long function_pointer = caps.glGetVertexAttribPointerv;
/*  954: 954 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  955: 955 */    ByteBuffer __result = nglGetVertexAttribPointerv(index, pname, result_size, function_pointer);
/*  956: 956 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*  957:     */  }
/*  958:     */  
/*  959:     */  static native ByteBuffer nglGetVertexAttribPointerv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  960:     */  
/*  961: 961 */  public static void glBindAttribLocation(int program, int index, ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/*  962: 962 */    long function_pointer = caps.glBindAttribLocation;
/*  963: 963 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  964: 964 */    BufferChecks.checkDirect(name);
/*  965: 965 */    BufferChecks.checkNullTerminated(name);
/*  966: 966 */    nglBindAttribLocation(program, index, MemoryUtil.getAddress(name), function_pointer);
/*  967:     */  }
/*  968:     */  
/*  969:     */  static native void nglBindAttribLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  970:     */  
/*  971:     */  public static void glBindAttribLocation(int program, int index, CharSequence name) {
/*  972: 972 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  973: 973 */    long function_pointer = caps.glBindAttribLocation;
/*  974: 974 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  975: 975 */    nglBindAttribLocation(program, index, APIUtil.getBufferNT(caps, name), function_pointer);
/*  976:     */  }
/*  977:     */  
/*  978:     */  public static void glGetActiveAttrib(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/*  979: 979 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  980: 980 */    long function_pointer = caps.glGetActiveAttrib;
/*  981: 981 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  982: 982 */    if (length != null)
/*  983: 983 */      BufferChecks.checkBuffer(length, 1);
/*  984: 984 */    BufferChecks.checkBuffer(size, 1);
/*  985: 985 */    BufferChecks.checkBuffer(type, 1);
/*  986: 986 */    BufferChecks.checkDirect(name);
/*  987: 987 */    nglGetActiveAttrib(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*  988:     */  }
/*  989:     */  
/*  992:     */  static native void nglGetActiveAttrib(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  993:     */  
/*  995:     */  public static String glGetActiveAttrib(int program, int index, int maxLength, IntBuffer sizeType)
/*  996:     */  {
/*  997: 997 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  998: 998 */    long function_pointer = caps.glGetActiveAttrib;
/*  999: 999 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1000:1000 */    BufferChecks.checkBuffer(sizeType, 2);
/* 1001:1001 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 1002:1002 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 1003:1003 */    nglGetActiveAttrib(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 1004:1004 */    name.limit(name_length.get(0));
/* 1005:1005 */    return APIUtil.getString(caps, name);
/* 1006:     */  }
/* 1007:     */  
/* 1012:     */  public static String glGetActiveAttrib(int program, int index, int maxLength)
/* 1013:     */  {
/* 1014:1014 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1015:1015 */    long function_pointer = caps.glGetActiveAttrib;
/* 1016:1016 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1017:1017 */    IntBuffer name_length = APIUtil.getLengths(caps);
/* 1018:1018 */    ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 1019:1019 */    nglGetActiveAttrib(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 1020:1020 */    name.limit(name_length.get(0));
/* 1021:1021 */    return APIUtil.getString(caps, name);
/* 1022:     */  }
/* 1023:     */  
/* 1028:     */  public static int glGetActiveAttribSize(int program, int index)
/* 1029:     */  {
/* 1030:1030 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1031:1031 */    long function_pointer = caps.glGetActiveAttrib;
/* 1032:1032 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1033:1033 */    IntBuffer size = APIUtil.getBufferInt(caps);
/* 1034:1034 */    nglGetActiveAttrib(program, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 1035:1035 */    return size.get(0);
/* 1036:     */  }
/* 1037:     */  
/* 1042:     */  public static int glGetActiveAttribType(int program, int index)
/* 1043:     */  {
/* 1044:1044 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1045:1045 */    long function_pointer = caps.glGetActiveAttrib;
/* 1046:1046 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1047:1047 */    IntBuffer type = APIUtil.getBufferInt(caps);
/* 1048:1048 */    nglGetActiveAttrib(program, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 1049:1049 */    return type.get(0);
/* 1050:     */  }
/* 1051:     */  
/* 1052:     */  public static int glGetAttribLocation(int program, ByteBuffer name) {
/* 1053:1053 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1054:1054 */    long function_pointer = caps.glGetAttribLocation;
/* 1055:1055 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1056:1056 */    BufferChecks.checkDirect(name);
/* 1057:1057 */    BufferChecks.checkNullTerminated(name);
/* 1058:1058 */    int __result = nglGetAttribLocation(program, MemoryUtil.getAddress(name), function_pointer);
/* 1059:1059 */    return __result;
/* 1060:     */  }
/* 1061:     */  
/* 1062:     */  static native int nglGetAttribLocation(int paramInt, long paramLong1, long paramLong2);
/* 1063:     */  
/* 1064:     */  public static int glGetAttribLocation(int program, CharSequence name) {
/* 1065:1065 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1066:1066 */    long function_pointer = caps.glGetAttribLocation;
/* 1067:1067 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1068:1068 */    int __result = nglGetAttribLocation(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 1069:1069 */    return __result;
/* 1070:     */  }
/* 1071:     */  
/* 1072:     */  public static void glDrawBuffers(IntBuffer buffers) {
/* 1073:1073 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1074:1074 */    long function_pointer = caps.glDrawBuffers;
/* 1075:1075 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1076:1076 */    BufferChecks.checkDirect(buffers);
/* 1077:1077 */    nglDrawBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/* 1078:     */  }
/* 1079:     */  
/* 1080:     */  static native void nglDrawBuffers(int paramInt, long paramLong1, long paramLong2);
/* 1081:     */  
/* 1082:     */  public static void glDrawBuffers(int buffer) {
/* 1083:1083 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1084:1084 */    long function_pointer = caps.glDrawBuffers;
/* 1085:1085 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1086:1086 */    nglDrawBuffers(1, APIUtil.getInt(caps, buffer), function_pointer);
/* 1087:     */  }
/* 1088:     */  
/* 1089:     */  public static void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
/* 1090:1090 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 1091:1091 */    long function_pointer = caps.glStencilOpSeparate;
/* 1092:1092 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1093:1093 */    nglStencilOpSeparate(face, sfail, dpfail, dppass, function_pointer);
/* 1094:     */  }
/* 1095:     */  
/* 1096:     */  static native void nglStencilOpSeparate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 1097:     */  
/* 1098:1098 */  public static void glStencilFuncSeparate(int face, int func, int ref, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1099:1099 */    long function_pointer = caps.glStencilFuncSeparate;
/* 1100:1100 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1101:1101 */    nglStencilFuncSeparate(face, func, ref, mask, function_pointer);
/* 1102:     */  }
/* 1103:     */  
/* 1104:     */  static native void nglStencilFuncSeparate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 1105:     */  
/* 1106:1106 */  public static void glStencilMaskSeparate(int face, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1107:1107 */    long function_pointer = caps.glStencilMaskSeparate;
/* 1108:1108 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1109:1109 */    nglStencilMaskSeparate(face, mask, function_pointer);
/* 1110:     */  }
/* 1111:     */  
/* 1112:     */  static native void nglStencilMaskSeparate(int paramInt1, int paramInt2, long paramLong);
/* 1113:     */  
/* 1114:1114 */  public static void glBlendEquationSeparate(int modeRGB, int modeAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1115:1115 */    long function_pointer = caps.glBlendEquationSeparate;
/* 1116:1116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 1117:1117 */    nglBlendEquationSeparate(modeRGB, modeAlpha, function_pointer);
/* 1118:     */  }
/* 1119:     */  
/* 1120:     */  static native void nglBlendEquationSeparate(int paramInt1, int paramInt2, long paramLong);
/* 1121:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL20
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */