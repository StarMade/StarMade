/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class GL20
/*      */ {
/*      */   public static final int GL_SHADING_LANGUAGE_VERSION = 35724;
/*      */   public static final int GL_CURRENT_PROGRAM = 35725;
/*      */   public static final int GL_SHADER_TYPE = 35663;
/*      */   public static final int GL_DELETE_STATUS = 35712;
/*      */   public static final int GL_COMPILE_STATUS = 35713;
/*      */   public static final int GL_LINK_STATUS = 35714;
/*      */   public static final int GL_VALIDATE_STATUS = 35715;
/*      */   public static final int GL_INFO_LOG_LENGTH = 35716;
/*      */   public static final int GL_ATTACHED_SHADERS = 35717;
/*      */   public static final int GL_ACTIVE_UNIFORMS = 35718;
/*      */   public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;
/*      */   public static final int GL_ACTIVE_ATTRIBUTES = 35721;
/*      */   public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;
/*      */   public static final int GL_SHADER_SOURCE_LENGTH = 35720;
/*      */   public static final int GL_SHADER_OBJECT = 35656;
/*      */   public static final int GL_FLOAT_VEC2 = 35664;
/*      */   public static final int GL_FLOAT_VEC3 = 35665;
/*      */   public static final int GL_FLOAT_VEC4 = 35666;
/*      */   public static final int GL_INT_VEC2 = 35667;
/*      */   public static final int GL_INT_VEC3 = 35668;
/*      */   public static final int GL_INT_VEC4 = 35669;
/*      */   public static final int GL_BOOL = 35670;
/*      */   public static final int GL_BOOL_VEC2 = 35671;
/*      */   public static final int GL_BOOL_VEC3 = 35672;
/*      */   public static final int GL_BOOL_VEC4 = 35673;
/*      */   public static final int GL_FLOAT_MAT2 = 35674;
/*      */   public static final int GL_FLOAT_MAT3 = 35675;
/*      */   public static final int GL_FLOAT_MAT4 = 35676;
/*      */   public static final int GL_SAMPLER_1D = 35677;
/*      */   public static final int GL_SAMPLER_2D = 35678;
/*      */   public static final int GL_SAMPLER_3D = 35679;
/*      */   public static final int GL_SAMPLER_CUBE = 35680;
/*      */   public static final int GL_SAMPLER_1D_SHADOW = 35681;
/*      */   public static final int GL_SAMPLER_2D_SHADOW = 35682;
/*      */   public static final int GL_VERTEX_SHADER = 35633;
/*      */   public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 35658;
/*      */   public static final int GL_MAX_VARYING_FLOATS = 35659;
/*      */   public static final int GL_MAX_VERTEX_ATTRIBS = 34921;
/*      */   public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
/*      */   public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
/*      */   public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;
/*      */   public static final int GL_MAX_TEXTURE_COORDS = 34929;
/*      */   public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 34370;
/*      */   public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 34371;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;
/*      */   public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;
/*      */   public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;
/*      */   public static final int GL_FRAGMENT_SHADER = 35632;
/*      */   public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 35657;
/*      */   public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 35723;
/*      */   public static final int GL_MAX_DRAW_BUFFERS = 34852;
/*      */   public static final int GL_DRAW_BUFFER0 = 34853;
/*      */   public static final int GL_DRAW_BUFFER1 = 34854;
/*      */   public static final int GL_DRAW_BUFFER2 = 34855;
/*      */   public static final int GL_DRAW_BUFFER3 = 34856;
/*      */   public static final int GL_DRAW_BUFFER4 = 34857;
/*      */   public static final int GL_DRAW_BUFFER5 = 34858;
/*      */   public static final int GL_DRAW_BUFFER6 = 34859;
/*      */   public static final int GL_DRAW_BUFFER7 = 34860;
/*      */   public static final int GL_DRAW_BUFFER8 = 34861;
/*      */   public static final int GL_DRAW_BUFFER9 = 34862;
/*      */   public static final int GL_DRAW_BUFFER10 = 34863;
/*      */   public static final int GL_DRAW_BUFFER11 = 34864;
/*      */   public static final int GL_DRAW_BUFFER12 = 34865;
/*      */   public static final int GL_DRAW_BUFFER13 = 34866;
/*      */   public static final int GL_DRAW_BUFFER14 = 34867;
/*      */   public static final int GL_DRAW_BUFFER15 = 34868;
/*      */   public static final int GL_POINT_SPRITE = 34913;
/*      */   public static final int GL_COORD_REPLACE = 34914;
/*      */   public static final int GL_POINT_SPRITE_COORD_ORIGIN = 36000;
/*      */   public static final int GL_LOWER_LEFT = 36001;
/*      */   public static final int GL_UPPER_LEFT = 36002;
/*      */   public static final int GL_STENCIL_BACK_FUNC = 34816;
/*      */   public static final int GL_STENCIL_BACK_FAIL = 34817;
/*      */   public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;
/*      */   public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;
/*      */   public static final int GL_STENCIL_BACK_REF = 36003;
/*      */   public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
/*      */   public static final int GL_STENCIL_BACK_WRITEMASK = 36005;
/*      */   public static final int GL_BLEND_EQUATION_RGB = 32777;
/*      */   public static final int GL_BLEND_EQUATION_ALPHA = 34877;
/*      */ 
/*      */   public static void glShaderSource(int shader, ByteBuffer string)
/*      */   {
/*  192 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  193 */     long function_pointer = caps.glShaderSource;
/*  194 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  195 */     BufferChecks.checkDirect(string);
/*  196 */     nglShaderSource(shader, 1, MemoryUtil.getAddress(string), string.remaining(), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglShaderSource(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*      */ 
/*      */   public static void glShaderSource(int shader, CharSequence string) {
/*  202 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  203 */     long function_pointer = caps.glShaderSource;
/*  204 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  205 */     nglShaderSource(shader, 1, APIUtil.getBuffer(caps, string), string.length(), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glShaderSource(int shader, CharSequence[] strings)
/*      */   {
/*  210 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  211 */     long function_pointer = caps.glShaderSource;
/*  212 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  213 */     BufferChecks.checkArray(strings);
/*  214 */     nglShaderSource3(shader, strings.length, APIUtil.getBuffer(caps, strings), APIUtil.getLengths(caps, strings), function_pointer);
/*      */   }
/*      */   static native void nglShaderSource3(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int glCreateShader(int type) {
/*  219 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  220 */     long function_pointer = caps.glCreateShader;
/*  221 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  222 */     int __result = nglCreateShader(type, function_pointer);
/*  223 */     return __result;
/*      */   }
/*      */   static native int nglCreateShader(int paramInt, long paramLong);
/*      */ 
/*      */   public static boolean glIsShader(int shader) {
/*  228 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  229 */     long function_pointer = caps.glIsShader;
/*  230 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  231 */     boolean __result = nglIsShader(shader, function_pointer);
/*  232 */     return __result;
/*      */   }
/*      */   static native boolean nglIsShader(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glCompileShader(int shader) {
/*  237 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  238 */     long function_pointer = caps.glCompileShader;
/*  239 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  240 */     nglCompileShader(shader, function_pointer);
/*      */   }
/*      */   static native void nglCompileShader(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDeleteShader(int shader) {
/*  245 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  246 */     long function_pointer = caps.glDeleteShader;
/*  247 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  248 */     nglDeleteShader(shader, function_pointer);
/*      */   }
/*      */   static native void nglDeleteShader(int paramInt, long paramLong);
/*      */ 
/*      */   public static int glCreateProgram() {
/*  253 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  254 */     long function_pointer = caps.glCreateProgram;
/*  255 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  256 */     int __result = nglCreateProgram(function_pointer);
/*  257 */     return __result;
/*      */   }
/*      */   static native int nglCreateProgram(long paramLong);
/*      */ 
/*      */   public static boolean glIsProgram(int program) {
/*  262 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  263 */     long function_pointer = caps.glIsProgram;
/*  264 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  265 */     boolean __result = nglIsProgram(program, function_pointer);
/*  266 */     return __result;
/*      */   }
/*      */   static native boolean nglIsProgram(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glAttachShader(int program, int shader) {
/*  271 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  272 */     long function_pointer = caps.glAttachShader;
/*  273 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  274 */     nglAttachShader(program, shader, function_pointer);
/*      */   }
/*      */   static native void nglAttachShader(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDetachShader(int program, int shader) {
/*  279 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  280 */     long function_pointer = caps.glDetachShader;
/*  281 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  282 */     nglDetachShader(program, shader, function_pointer);
/*      */   }
/*      */   static native void nglDetachShader(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glLinkProgram(int program) {
/*  287 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  288 */     long function_pointer = caps.glLinkProgram;
/*  289 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  290 */     nglLinkProgram(program, function_pointer);
/*      */   }
/*      */   static native void nglLinkProgram(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glUseProgram(int program) {
/*  295 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  296 */     long function_pointer = caps.glUseProgram;
/*  297 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  298 */     nglUseProgram(program, function_pointer);
/*      */   }
/*      */   static native void nglUseProgram(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glValidateProgram(int program) {
/*  303 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  304 */     long function_pointer = caps.glValidateProgram;
/*  305 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  306 */     nglValidateProgram(program, function_pointer);
/*      */   }
/*      */   static native void nglValidateProgram(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDeleteProgram(int program) {
/*  311 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  312 */     long function_pointer = caps.glDeleteProgram;
/*  313 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  314 */     nglDeleteProgram(program, function_pointer);
/*      */   }
/*      */   static native void nglDeleteProgram(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glUniform1f(int location, float v0) {
/*  319 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  320 */     long function_pointer = caps.glUniform1f;
/*  321 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  322 */     nglUniform1f(location, v0, function_pointer);
/*      */   }
/*      */   static native void nglUniform1f(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glUniform2f(int location, float v0, float v1) {
/*  327 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  328 */     long function_pointer = caps.glUniform2f;
/*  329 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  330 */     nglUniform2f(location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglUniform2f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glUniform3f(int location, float v0, float v1, float v2) {
/*  335 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  336 */     long function_pointer = caps.glUniform3f;
/*  337 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  338 */     nglUniform3f(location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglUniform3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glUniform4f(int location, float v0, float v1, float v2, float v3) {
/*  343 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  344 */     long function_pointer = caps.glUniform4f;
/*  345 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  346 */     nglUniform4f(location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglUniform4f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glUniform1i(int location, int v0) {
/*  351 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  352 */     long function_pointer = caps.glUniform1i;
/*  353 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  354 */     nglUniform1i(location, v0, function_pointer);
/*      */   }
/*      */   static native void nglUniform1i(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glUniform2i(int location, int v0, int v1) {
/*  359 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  360 */     long function_pointer = caps.glUniform2i;
/*  361 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  362 */     nglUniform2i(location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglUniform2i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glUniform3i(int location, int v0, int v1, int v2) {
/*  367 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  368 */     long function_pointer = caps.glUniform3i;
/*  369 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  370 */     nglUniform3i(location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglUniform3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glUniform4i(int location, int v0, int v1, int v2, int v3) {
/*  375 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  376 */     long function_pointer = caps.glUniform4i;
/*  377 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  378 */     nglUniform4i(location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglUniform4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glUniform1(int location, FloatBuffer values) {
/*  383 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  384 */     long function_pointer = caps.glUniform1fv;
/*  385 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  386 */     BufferChecks.checkDirect(values);
/*  387 */     nglUniform1fv(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform1fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform2(int location, FloatBuffer values) {
/*  392 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  393 */     long function_pointer = caps.glUniform2fv;
/*  394 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  395 */     BufferChecks.checkDirect(values);
/*  396 */     nglUniform2fv(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform2fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform3(int location, FloatBuffer values) {
/*  401 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  402 */     long function_pointer = caps.glUniform3fv;
/*  403 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  404 */     BufferChecks.checkDirect(values);
/*  405 */     nglUniform3fv(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform3fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform4(int location, FloatBuffer values) {
/*  410 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  411 */     long function_pointer = caps.glUniform4fv;
/*  412 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  413 */     BufferChecks.checkDirect(values);
/*  414 */     nglUniform4fv(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform4fv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform1(int location, IntBuffer values) {
/*  419 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  420 */     long function_pointer = caps.glUniform1iv;
/*  421 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  422 */     BufferChecks.checkDirect(values);
/*  423 */     nglUniform1iv(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform1iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform2(int location, IntBuffer values) {
/*  428 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  429 */     long function_pointer = caps.glUniform2iv;
/*  430 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  431 */     BufferChecks.checkDirect(values);
/*  432 */     nglUniform2iv(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform2iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform3(int location, IntBuffer values) {
/*  437 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  438 */     long function_pointer = caps.glUniform3iv;
/*  439 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  440 */     BufferChecks.checkDirect(values);
/*  441 */     nglUniform3iv(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform3iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniform4(int location, IntBuffer values) {
/*  446 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  447 */     long function_pointer = caps.glUniform4iv;
/*  448 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  449 */     BufferChecks.checkDirect(values);
/*  450 */     nglUniform4iv(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglUniform4iv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) {
/*  455 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  456 */     long function_pointer = caps.glUniformMatrix2fv;
/*  457 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  458 */     BufferChecks.checkDirect(matrices);
/*  459 */     nglUniformMatrix2fv(location, matrices.remaining() >> 2, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*      */   }
/*      */   static native void nglUniformMatrix2fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) {
/*  464 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  465 */     long function_pointer = caps.glUniformMatrix3fv;
/*  466 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  467 */     BufferChecks.checkDirect(matrices);
/*  468 */     nglUniformMatrix3fv(location, matrices.remaining() / 9, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*      */   }
/*      */   static native void nglUniformMatrix3fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
/*  473 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  474 */     long function_pointer = caps.glUniformMatrix4fv;
/*  475 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  476 */     BufferChecks.checkDirect(matrices);
/*  477 */     nglUniformMatrix4fv(location, matrices.remaining() >> 4, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*      */   }
/*      */   static native void nglUniformMatrix4fv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetShader(int shader, int pname, IntBuffer params) {
/*  482 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  483 */     long function_pointer = caps.glGetShaderiv;
/*  484 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  485 */     BufferChecks.checkDirect(params);
/*  486 */     nglGetShaderiv(shader, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetShaderiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   @Deprecated
/*      */   public static int glGetShader(int shader, int pname)
/*      */   {
/*  497 */     return glGetShaderi(shader, pname);
/*      */   }
/*      */ 
/*      */   public static int glGetShaderi(int shader, int pname)
/*      */   {
/*  502 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  503 */     long function_pointer = caps.glGetShaderiv;
/*  504 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  505 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  506 */     nglGetShaderiv(shader, pname, MemoryUtil.getAddress(params), function_pointer);
/*  507 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetProgram(int program, int pname, IntBuffer params) {
/*  511 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  512 */     long function_pointer = caps.glGetProgramiv;
/*  513 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  514 */     BufferChecks.checkDirect(params);
/*  515 */     nglGetProgramiv(program, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetProgramiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   @Deprecated
/*      */   public static int glGetProgram(int program, int pname)
/*      */   {
/*  526 */     return glGetProgrami(program, pname);
/*      */   }
/*      */ 
/*      */   public static int glGetProgrami(int program, int pname)
/*      */   {
/*  531 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  532 */     long function_pointer = caps.glGetProgramiv;
/*  533 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  534 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  535 */     nglGetProgramiv(program, pname, MemoryUtil.getAddress(params), function_pointer);
/*  536 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
/*  540 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  541 */     long function_pointer = caps.glGetShaderInfoLog;
/*  542 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  543 */     if (length != null)
/*  544 */       BufferChecks.checkBuffer(length, 1);
/*  545 */     BufferChecks.checkDirect(infoLog);
/*  546 */     nglGetShaderInfoLog(shader, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetShaderInfoLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static String glGetShaderInfoLog(int shader, int maxLength) {
/*  552 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  553 */     long function_pointer = caps.glGetShaderInfoLog;
/*  554 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  555 */     IntBuffer infoLog_length = APIUtil.getLengths(caps);
/*  556 */     ByteBuffer infoLog = APIUtil.getBufferByte(caps, maxLength);
/*  557 */     nglGetShaderInfoLog(shader, maxLength, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  558 */     infoLog.limit(infoLog_length.get(0));
/*  559 */     return APIUtil.getString(caps, infoLog);
/*      */   }
/*      */ 
/*      */   public static void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
/*  563 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  564 */     long function_pointer = caps.glGetProgramInfoLog;
/*  565 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  566 */     if (length != null)
/*  567 */       BufferChecks.checkBuffer(length, 1);
/*  568 */     BufferChecks.checkDirect(infoLog);
/*  569 */     nglGetProgramInfoLog(program, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetProgramInfoLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static String glGetProgramInfoLog(int program, int maxLength) {
/*  575 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  576 */     long function_pointer = caps.glGetProgramInfoLog;
/*  577 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  578 */     IntBuffer infoLog_length = APIUtil.getLengths(caps);
/*  579 */     ByteBuffer infoLog = APIUtil.getBufferByte(caps, maxLength);
/*  580 */     nglGetProgramInfoLog(program, maxLength, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  581 */     infoLog.limit(infoLog_length.get(0));
/*  582 */     return APIUtil.getString(caps, infoLog);
/*      */   }
/*      */ 
/*      */   public static void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
/*  586 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  587 */     long function_pointer = caps.glGetAttachedShaders;
/*  588 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  589 */     if (count != null)
/*  590 */       BufferChecks.checkBuffer(count, 1);
/*  591 */     BufferChecks.checkDirect(shaders);
/*  592 */     nglGetAttachedShaders(program, shaders.remaining(), MemoryUtil.getAddressSafe(count), MemoryUtil.getAddress(shaders), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetAttachedShaders(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static int glGetUniformLocation(int program, ByteBuffer name)
/*      */   {
/*  604 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  605 */     long function_pointer = caps.glGetUniformLocation;
/*  606 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  607 */     BufferChecks.checkBuffer(name, 1);
/*  608 */     BufferChecks.checkNullTerminated(name);
/*  609 */     int __result = nglGetUniformLocation(program, MemoryUtil.getAddress(name), function_pointer);
/*  610 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglGetUniformLocation(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetUniformLocation(int program, CharSequence name) {
/*  616 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  617 */     long function_pointer = caps.glGetUniformLocation;
/*  618 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  619 */     int __result = nglGetUniformLocation(program, APIUtil.getBufferNT(caps, name), function_pointer);
/*  620 */     return __result;
/*      */   }
/*      */ 
/*      */   public static void glGetActiveUniform(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/*  624 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  625 */     long function_pointer = caps.glGetActiveUniform;
/*  626 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  627 */     if (length != null)
/*  628 */       BufferChecks.checkBuffer(length, 1);
/*  629 */     BufferChecks.checkBuffer(size, 1);
/*  630 */     BufferChecks.checkBuffer(type, 1);
/*  631 */     BufferChecks.checkDirect(name);
/*  632 */     nglGetActiveUniform(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetActiveUniform(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static String glGetActiveUniform(int program, int index, int maxLength, IntBuffer sizeType)
/*      */   {
/*  642 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  643 */     long function_pointer = caps.glGetActiveUniform;
/*  644 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  645 */     BufferChecks.checkBuffer(sizeType, 2);
/*  646 */     IntBuffer name_length = APIUtil.getLengths(caps);
/*  647 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/*  648 */     nglGetActiveUniform(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/*  649 */     name.limit(name_length.get(0));
/*  650 */     return APIUtil.getString(caps, name);
/*      */   }
/*      */ 
/*      */   public static String glGetActiveUniform(int program, int index, int maxLength)
/*      */   {
/*  659 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  660 */     long function_pointer = caps.glGetActiveUniform;
/*  661 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  662 */     IntBuffer name_length = APIUtil.getLengths(caps);
/*  663 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/*  664 */     nglGetActiveUniform(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/*  665 */     name.limit(name_length.get(0));
/*  666 */     return APIUtil.getString(caps, name);
/*      */   }
/*      */ 
/*      */   public static int glGetActiveUniformSize(int program, int index)
/*      */   {
/*  675 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  676 */     long function_pointer = caps.glGetActiveUniform;
/*  677 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  678 */     IntBuffer size = APIUtil.getBufferInt(caps);
/*  679 */     nglGetActiveUniform(program, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/*  680 */     return size.get(0);
/*      */   }
/*      */ 
/*      */   public static int glGetActiveUniformType(int program, int index)
/*      */   {
/*  689 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  690 */     long function_pointer = caps.glGetActiveUniform;
/*  691 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  692 */     IntBuffer type = APIUtil.getBufferInt(caps);
/*  693 */     nglGetActiveUniform(program, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/*  694 */     return type.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetUniform(int program, int location, FloatBuffer params) {
/*  698 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  699 */     long function_pointer = caps.glGetUniformfv;
/*  700 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  701 */     BufferChecks.checkDirect(params);
/*  702 */     nglGetUniformfv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetUniformfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetUniform(int program, int location, IntBuffer params) {
/*  707 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  708 */     long function_pointer = caps.glGetUniformiv;
/*  709 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  710 */     BufferChecks.checkDirect(params);
/*  711 */     nglGetUniformiv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetUniformiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
/*  716 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  717 */     long function_pointer = caps.glGetShaderSource;
/*  718 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  719 */     if (length != null)
/*  720 */       BufferChecks.checkBuffer(length, 1);
/*  721 */     BufferChecks.checkDirect(source);
/*  722 */     nglGetShaderSource(shader, source.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(source), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetShaderSource(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static String glGetShaderSource(int shader, int maxLength) {
/*  728 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  729 */     long function_pointer = caps.glGetShaderSource;
/*  730 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  731 */     IntBuffer source_length = APIUtil.getLengths(caps);
/*  732 */     ByteBuffer source = APIUtil.getBufferByte(caps, maxLength);
/*  733 */     nglGetShaderSource(shader, maxLength, MemoryUtil.getAddress0(source_length), MemoryUtil.getAddress(source), function_pointer);
/*  734 */     source.limit(source_length.get(0));
/*  735 */     return APIUtil.getString(caps, source);
/*      */   }
/*      */ 
/*      */   public static void glVertexAttrib1s(int index, short x) {
/*  739 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  740 */     long function_pointer = caps.glVertexAttrib1s;
/*  741 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  742 */     nglVertexAttrib1s(index, x, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib1s(int paramInt, short paramShort, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib1f(int index, float x) {
/*  747 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  748 */     long function_pointer = caps.glVertexAttrib1f;
/*  749 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  750 */     nglVertexAttrib1f(index, x, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib1f(int paramInt, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib1d(int index, double x) {
/*  755 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  756 */     long function_pointer = caps.glVertexAttrib1d;
/*  757 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  758 */     nglVertexAttrib1d(index, x, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib1d(int paramInt, double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib2s(int index, short x, short y) {
/*  763 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  764 */     long function_pointer = caps.glVertexAttrib2s;
/*  765 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  766 */     nglVertexAttrib2s(index, x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib2s(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib2f(int index, float x, float y) {
/*  771 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  772 */     long function_pointer = caps.glVertexAttrib2f;
/*  773 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  774 */     nglVertexAttrib2f(index, x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib2f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib2d(int index, double x, double y) {
/*  779 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  780 */     long function_pointer = caps.glVertexAttrib2d;
/*  781 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  782 */     nglVertexAttrib2d(index, x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib3s(int index, short x, short y, short z) {
/*  787 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  788 */     long function_pointer = caps.glVertexAttrib3s;
/*  789 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  790 */     nglVertexAttrib3s(index, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib3s(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib3f(int index, float x, float y, float z) {
/*  795 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  796 */     long function_pointer = caps.glVertexAttrib3f;
/*  797 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  798 */     nglVertexAttrib3f(index, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib3d(int index, double x, double y, double z) {
/*  803 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  804 */     long function_pointer = caps.glVertexAttrib3d;
/*  805 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  806 */     nglVertexAttrib3d(index, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib4s(int index, short x, short y, short z, short w) {
/*  811 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  812 */     long function_pointer = caps.glVertexAttrib4s;
/*  813 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  814 */     nglVertexAttrib4s(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib4s(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib4f(int index, float x, float y, float z, float w) {
/*  819 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  820 */     long function_pointer = caps.glVertexAttrib4f;
/*  821 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  822 */     nglVertexAttrib4f(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib4f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib4d(int index, double x, double y, double z, double w) {
/*  827 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  828 */     long function_pointer = caps.glVertexAttrib4d;
/*  829 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  830 */     nglVertexAttrib4d(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) {
/*  835 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  836 */     long function_pointer = caps.glVertexAttrib4Nub;
/*  837 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  838 */     nglVertexAttrib4Nub(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttrib4Nub(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
/*  843 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  844 */     long function_pointer = caps.glVertexAttribPointer;
/*  845 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  846 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  847 */     BufferChecks.checkDirect(buffer);
/*  848 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  849 */     nglVertexAttribPointer(index, size, 5130, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
/*  852 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  853 */     long function_pointer = caps.glVertexAttribPointer;
/*  854 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  855 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  856 */     BufferChecks.checkDirect(buffer);
/*  857 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  858 */     nglVertexAttribPointer(index, size, 5126, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
/*  861 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  862 */     long function_pointer = caps.glVertexAttribPointer;
/*  863 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  864 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  865 */     BufferChecks.checkDirect(buffer);
/*  866 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  867 */     nglVertexAttribPointer(index, size, unsigned ? 5121 : 5120, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
/*  870 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  871 */     long function_pointer = caps.glVertexAttribPointer;
/*  872 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  873 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  874 */     BufferChecks.checkDirect(buffer);
/*  875 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  876 */     nglVertexAttribPointer(index, size, unsigned ? 5125 : 5124, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
/*  879 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  880 */     long function_pointer = caps.glVertexAttribPointer;
/*  881 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  882 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  883 */     BufferChecks.checkDirect(buffer);
/*  884 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  885 */     nglVertexAttribPointer(index, size, unsigned ? 5123 : 5122, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribPointer(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*  889 */   public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  890 */     long function_pointer = caps.glVertexAttribPointer;
/*  891 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  892 */     GLChecks.ensureArrayVBOenabled(caps);
/*  893 */     nglVertexAttribPointerBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglVertexAttribPointerBO(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, ByteBuffer buffer)
/*      */   {
/*  899 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  900 */     long function_pointer = caps.glVertexAttribPointer;
/*  901 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  902 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  903 */     BufferChecks.checkDirect(buffer);
/*  904 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/*  905 */     nglVertexAttribPointer(index, size, type, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glEnableVertexAttribArray(int index) {
/*  909 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  910 */     long function_pointer = caps.glEnableVertexAttribArray;
/*  911 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  912 */     nglEnableVertexAttribArray(index, function_pointer);
/*      */   }
/*      */   static native void nglEnableVertexAttribArray(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDisableVertexAttribArray(int index) {
/*  917 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  918 */     long function_pointer = caps.glDisableVertexAttribArray;
/*  919 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  920 */     nglDisableVertexAttribArray(index, function_pointer);
/*      */   }
/*      */   static native void nglDisableVertexAttribArray(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetVertexAttrib(int index, int pname, FloatBuffer params) {
/*  925 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  926 */     long function_pointer = caps.glGetVertexAttribfv;
/*  927 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  928 */     BufferChecks.checkBuffer(params, 4);
/*  929 */     nglGetVertexAttribfv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetVertexAttribfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetVertexAttrib(int index, int pname, DoubleBuffer params) {
/*  934 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  935 */     long function_pointer = caps.glGetVertexAttribdv;
/*  936 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  937 */     BufferChecks.checkBuffer(params, 4);
/*  938 */     nglGetVertexAttribdv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetVertexAttribdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetVertexAttrib(int index, int pname, IntBuffer params) {
/*  943 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  944 */     long function_pointer = caps.glGetVertexAttribiv;
/*  945 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  946 */     BufferChecks.checkBuffer(params, 4);
/*  947 */     nglGetVertexAttribiv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetVertexAttribiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static ByteBuffer glGetVertexAttribPointer(int index, int pname, long result_size) {
/*  952 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  953 */     long function_pointer = caps.glGetVertexAttribPointerv;
/*  954 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  955 */     ByteBuffer __result = nglGetVertexAttribPointerv(index, pname, result_size, function_pointer);
/*  956 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglGetVertexAttribPointerv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glBindAttribLocation(int program, int index, ByteBuffer name) {
/*  961 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  962 */     long function_pointer = caps.glBindAttribLocation;
/*  963 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  964 */     BufferChecks.checkDirect(name);
/*  965 */     BufferChecks.checkNullTerminated(name);
/*  966 */     nglBindAttribLocation(program, index, MemoryUtil.getAddress(name), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglBindAttribLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glBindAttribLocation(int program, int index, CharSequence name) {
/*  972 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  973 */     long function_pointer = caps.glBindAttribLocation;
/*  974 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  975 */     nglBindAttribLocation(program, index, APIUtil.getBufferNT(caps, name), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetActiveAttrib(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/*  979 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  980 */     long function_pointer = caps.glGetActiveAttrib;
/*  981 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  982 */     if (length != null)
/*  983 */       BufferChecks.checkBuffer(length, 1);
/*  984 */     BufferChecks.checkBuffer(size, 1);
/*  985 */     BufferChecks.checkBuffer(type, 1);
/*  986 */     BufferChecks.checkDirect(name);
/*  987 */     nglGetActiveAttrib(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetActiveAttrib(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*      */ 
/*      */   public static String glGetActiveAttrib(int program, int index, int maxLength, IntBuffer sizeType)
/*      */   {
/*  997 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  998 */     long function_pointer = caps.glGetActiveAttrib;
/*  999 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1000 */     BufferChecks.checkBuffer(sizeType, 2);
/* 1001 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 1002 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 1003 */     nglGetActiveAttrib(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 1004 */     name.limit(name_length.get(0));
/* 1005 */     return APIUtil.getString(caps, name);
/*      */   }
/*      */ 
/*      */   public static String glGetActiveAttrib(int program, int index, int maxLength)
/*      */   {
/* 1014 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1015 */     long function_pointer = caps.glGetActiveAttrib;
/* 1016 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1017 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 1018 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 1019 */     nglGetActiveAttrib(program, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 1020 */     name.limit(name_length.get(0));
/* 1021 */     return APIUtil.getString(caps, name);
/*      */   }
/*      */ 
/*      */   public static int glGetActiveAttribSize(int program, int index)
/*      */   {
/* 1030 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1031 */     long function_pointer = caps.glGetActiveAttrib;
/* 1032 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1033 */     IntBuffer size = APIUtil.getBufferInt(caps);
/* 1034 */     nglGetActiveAttrib(program, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 1035 */     return size.get(0);
/*      */   }
/*      */ 
/*      */   public static int glGetActiveAttribType(int program, int index)
/*      */   {
/* 1044 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1045 */     long function_pointer = caps.glGetActiveAttrib;
/* 1046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1047 */     IntBuffer type = APIUtil.getBufferInt(caps);
/* 1048 */     nglGetActiveAttrib(program, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 1049 */     return type.get(0);
/*      */   }
/*      */ 
/*      */   public static int glGetAttribLocation(int program, ByteBuffer name) {
/* 1053 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1054 */     long function_pointer = caps.glGetAttribLocation;
/* 1055 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1056 */     BufferChecks.checkDirect(name);
/* 1057 */     BufferChecks.checkNullTerminated(name);
/* 1058 */     int __result = nglGetAttribLocation(program, MemoryUtil.getAddress(name), function_pointer);
/* 1059 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglGetAttribLocation(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetAttribLocation(int program, CharSequence name) {
/* 1065 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1066 */     long function_pointer = caps.glGetAttribLocation;
/* 1067 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1068 */     int __result = nglGetAttribLocation(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 1069 */     return __result;
/*      */   }
/*      */ 
/*      */   public static void glDrawBuffers(IntBuffer buffers) {
/* 1073 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1074 */     long function_pointer = caps.glDrawBuffers;
/* 1075 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1076 */     BufferChecks.checkDirect(buffers);
/* 1077 */     nglDrawBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDrawBuffers(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDrawBuffers(int buffer) {
/* 1083 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1084 */     long function_pointer = caps.glDrawBuffers;
/* 1085 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1086 */     nglDrawBuffers(1, APIUtil.getInt(caps, buffer), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
/* 1090 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1091 */     long function_pointer = caps.glStencilOpSeparate;
/* 1092 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1093 */     nglStencilOpSeparate(face, sfail, dpfail, dppass, function_pointer);
/*      */   }
/*      */   static native void nglStencilOpSeparate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glStencilFuncSeparate(int face, int func, int ref, int mask) {
/* 1098 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1099 */     long function_pointer = caps.glStencilFuncSeparate;
/* 1100 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1101 */     nglStencilFuncSeparate(face, func, ref, mask, function_pointer);
/*      */   }
/*      */   static native void nglStencilFuncSeparate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glStencilMaskSeparate(int face, int mask) {
/* 1106 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1107 */     long function_pointer = caps.glStencilMaskSeparate;
/* 1108 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1109 */     nglStencilMaskSeparate(face, mask, function_pointer);
/*      */   }
/*      */   static native void nglStencilMaskSeparate(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
/* 1114 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1115 */     long function_pointer = caps.glBlendEquationSeparate;
/* 1116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1117 */     nglBlendEquationSeparate(modeRGB, modeAlpha, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglBlendEquationSeparate(int paramInt1, int paramInt2, long paramLong);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL20
 * JD-Core Version:    0.6.2
 */