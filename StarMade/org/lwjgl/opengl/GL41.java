/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class GL41
/*      */ {
/*      */   public static final int GL_SHADER_COMPILER = 36346;
/*      */   public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
/*      */   public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
/*      */   public static final int GL_MAX_VARYING_VECTORS = 36348;
/*      */   public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
/*      */   public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
/*      */   public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
/*      */   public static final int GL_FIXED = 5132;
/*      */   public static final int GL_LOW_FLOAT = 36336;
/*      */   public static final int GL_MEDIUM_FLOAT = 36337;
/*      */   public static final int GL_HIGH_FLOAT = 36338;
/*      */   public static final int GL_LOW_INT = 36339;
/*      */   public static final int GL_MEDIUM_INT = 36340;
/*      */   public static final int GL_HIGH_INT = 36341;
/*      */   public static final int GL_RGB565 = 36194;
/*      */   public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;
/*      */   public static final int GL_PROGRAM_BINARY_LENGTH = 34625;
/*      */   public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 34814;
/*      */   public static final int GL_PROGRAM_BINARY_FORMATS = 34815;
/*      */   public static final int GL_VERTEX_SHADER_BIT = 1;
/*      */   public static final int GL_FRAGMENT_SHADER_BIT = 2;
/*      */   public static final int GL_GEOMETRY_SHADER_BIT = 4;
/*      */   public static final int GL_TESS_CONTROL_SHADER_BIT = 8;
/*      */   public static final int GL_TESS_EVALUATION_SHADER_BIT = 16;
/*      */   public static final int GL_ALL_SHADER_BITS = -1;
/*      */   public static final int GL_PROGRAM_SEPARABLE = 33368;
/*      */   public static final int GL_ACTIVE_PROGRAM = 33369;
/*      */   public static final int GL_PROGRAM_PIPELINE_BINDING = 33370;
/*      */   public static final int GL_DOUBLE_VEC2 = 36860;
/*      */   public static final int GL_DOUBLE_VEC3 = 36861;
/*      */   public static final int GL_DOUBLE_VEC4 = 36862;
/*      */   public static final int GL_DOUBLE_MAT2 = 36678;
/*      */   public static final int GL_DOUBLE_MAT3 = 36679;
/*      */   public static final int GL_DOUBLE_MAT4 = 36680;
/*      */   public static final int GL_DOUBLE_MAT2x3 = 36681;
/*      */   public static final int GL_DOUBLE_MAT2x4 = 36682;
/*      */   public static final int GL_DOUBLE_MAT3x2 = 36683;
/*      */   public static final int GL_DOUBLE_MAT3x4 = 36684;
/*      */   public static final int GL_DOUBLE_MAT4x2 = 36685;
/*      */   public static final int GL_DOUBLE_MAT4x3 = 36686;
/*      */   public static final int GL_MAX_VIEWPORTS = 33371;
/*      */   public static final int GL_VIEWPORT_SUBPIXEL_BITS = 33372;
/*      */   public static final int GL_VIEWPORT_BOUNDS_RANGE = 33373;
/*      */   public static final int GL_LAYER_PROVOKING_VERTEX = 33374;
/*      */   public static final int GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;
/*      */   public static final int GL_SCISSOR_BOX = 3088;
/*      */   public static final int GL_VIEWPORT = 2978;
/*      */   public static final int GL_DEPTH_RANGE = 2928;
/*      */   public static final int GL_SCISSOR_TEST = 3089;
/*      */   public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
/*      */   public static final int GL_LAST_VERTEX_CONVENTION = 36430;
/*      */   public static final int GL_PROVOKING_VERTEX = 36431;
/*      */   public static final int GL_UNDEFINED_VERTEX = 33376;
/*      */ 
/*      */   public static void glReleaseShaderCompiler()
/*      */   {
/*  146 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  147 */     long function_pointer = caps.glReleaseShaderCompiler;
/*  148 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  149 */     nglReleaseShaderCompiler(function_pointer);
/*      */   }
/*      */   static native void nglReleaseShaderCompiler(long paramLong);
/*      */ 
/*      */   public static void glShaderBinary(IntBuffer shaders, int binaryformat, ByteBuffer binary) {
/*  154 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  155 */     long function_pointer = caps.glShaderBinary;
/*  156 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  157 */     BufferChecks.checkDirect(shaders);
/*  158 */     BufferChecks.checkDirect(binary);
/*  159 */     nglShaderBinary(shaders.remaining(), MemoryUtil.getAddress(shaders), binaryformat, MemoryUtil.getAddress(binary), binary.remaining(), function_pointer);
/*      */   }
/*      */   static native void nglShaderBinary(int paramInt1, long paramLong1, int paramInt2, long paramLong2, int paramInt3, long paramLong3);
/*      */ 
/*      */   public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
/*  164 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  165 */     long function_pointer = caps.glGetShaderPrecisionFormat;
/*  166 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  167 */     BufferChecks.checkBuffer(range, 2);
/*  168 */     BufferChecks.checkBuffer(precision, 1);
/*  169 */     nglGetShaderPrecisionFormat(shadertype, precisiontype, MemoryUtil.getAddress(range), MemoryUtil.getAddress(precision), function_pointer);
/*      */   }
/*      */   static native void nglGetShaderPrecisionFormat(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glDepthRangef(float n, float f) {
/*  174 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  175 */     long function_pointer = caps.glDepthRangef;
/*  176 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  177 */     nglDepthRangef(n, f, function_pointer);
/*      */   }
/*      */   static native void nglDepthRangef(float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glClearDepthf(float d) {
/*  182 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  183 */     long function_pointer = caps.glClearDepthf;
/*  184 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  185 */     nglClearDepthf(d, function_pointer);
/*      */   }
/*      */   static native void nglClearDepthf(float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glGetProgramBinary(int program, IntBuffer length, IntBuffer binaryFormat, ByteBuffer binary) {
/*  190 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  191 */     long function_pointer = caps.glGetProgramBinary;
/*  192 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  193 */     if (length != null)
/*  194 */       BufferChecks.checkBuffer(length, 1);
/*  195 */     BufferChecks.checkBuffer(binaryFormat, 1);
/*  196 */     BufferChecks.checkDirect(binary);
/*  197 */     nglGetProgramBinary(program, binary.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(binaryFormat), MemoryUtil.getAddress(binary), function_pointer);
/*      */   }
/*      */   static native void nglGetProgramBinary(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static void glProgramBinary(int program, int binaryFormat, ByteBuffer binary) {
/*  202 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  203 */     long function_pointer = caps.glProgramBinary;
/*  204 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  205 */     BufferChecks.checkDirect(binary);
/*  206 */     nglProgramBinary(program, binaryFormat, MemoryUtil.getAddress(binary), binary.remaining(), function_pointer);
/*      */   }
/*      */   static native void nglProgramBinary(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*      */ 
/*      */   public static void glProgramParameteri(int program, int pname, int value) {
/*  211 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  212 */     long function_pointer = caps.glProgramParameteri;
/*  213 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  214 */     nglProgramParameteri(program, pname, value, function_pointer);
/*      */   }
/*      */   static native void nglProgramParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glUseProgramStages(int pipeline, int stages, int program) {
/*  219 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  220 */     long function_pointer = caps.glUseProgramStages;
/*  221 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  222 */     nglUseProgramStages(pipeline, stages, program, function_pointer);
/*      */   }
/*      */   static native void nglUseProgramStages(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glActiveShaderProgram(int pipeline, int program) {
/*  227 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  228 */     long function_pointer = caps.glActiveShaderProgram;
/*  229 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  230 */     nglActiveShaderProgram(pipeline, program, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglActiveShaderProgram(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static int glCreateShaderProgram(int type, ByteBuffer string)
/*      */   {
/*  238 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  239 */     long function_pointer = caps.glCreateShaderProgramv;
/*  240 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  241 */     BufferChecks.checkDirect(string);
/*  242 */     BufferChecks.checkNullTerminated(string);
/*  243 */     int __result = nglCreateShaderProgramv(type, 1, MemoryUtil.getAddress(string), function_pointer);
/*  244 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglCreateShaderProgramv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glCreateShaderProgram(int type, int count, ByteBuffer strings)
/*      */   {
/*  254 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  255 */     long function_pointer = caps.glCreateShaderProgramv;
/*  256 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  257 */     BufferChecks.checkDirect(strings);
/*  258 */     BufferChecks.checkNullTerminated(strings, count);
/*  259 */     int __result = nglCreateShaderProgramv2(type, count, MemoryUtil.getAddress(strings), function_pointer);
/*  260 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglCreateShaderProgramv2(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glCreateShaderProgram(int type, ByteBuffer[] strings) {
/*  266 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  267 */     long function_pointer = caps.glCreateShaderProgramv;
/*  268 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  269 */     BufferChecks.checkArray(strings, 1);
/*  270 */     int __result = nglCreateShaderProgramv3(type, strings.length, strings, function_pointer);
/*  271 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nglCreateShaderProgramv3(int paramInt1, int paramInt2, ByteBuffer[] paramArrayOfByteBuffer, long paramLong);
/*      */ 
/*      */   public static int glCreateShaderProgram(int type, CharSequence string) {
/*  277 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  278 */     long function_pointer = caps.glCreateShaderProgramv;
/*  279 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  280 */     int __result = nglCreateShaderProgramv(type, 1, APIUtil.getBufferNT(caps, string), function_pointer);
/*  281 */     return __result;
/*      */   }
/*      */ 
/*      */   public static int glCreateShaderProgram(int type, CharSequence[] strings)
/*      */   {
/*  286 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  287 */     long function_pointer = caps.glCreateShaderProgramv;
/*  288 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  289 */     BufferChecks.checkArray(strings);
/*  290 */     int __result = nglCreateShaderProgramv2(type, strings.length, APIUtil.getBufferNT(caps, strings), function_pointer);
/*  291 */     return __result;
/*      */   }
/*      */ 
/*      */   public static void glBindProgramPipeline(int pipeline) {
/*  295 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  296 */     long function_pointer = caps.glBindProgramPipeline;
/*  297 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  298 */     nglBindProgramPipeline(pipeline, function_pointer);
/*      */   }
/*      */   static native void nglBindProgramPipeline(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glDeleteProgramPipelines(IntBuffer pipelines) {
/*  303 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  304 */     long function_pointer = caps.glDeleteProgramPipelines;
/*  305 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  306 */     BufferChecks.checkDirect(pipelines);
/*  307 */     nglDeleteProgramPipelines(pipelines.remaining(), MemoryUtil.getAddress(pipelines), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglDeleteProgramPipelines(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDeleteProgramPipelines(int pipeline) {
/*  313 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  314 */     long function_pointer = caps.glDeleteProgramPipelines;
/*  315 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  316 */     nglDeleteProgramPipelines(1, APIUtil.getInt(caps, pipeline), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGenProgramPipelines(IntBuffer pipelines) {
/*  320 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  321 */     long function_pointer = caps.glGenProgramPipelines;
/*  322 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  323 */     BufferChecks.checkDirect(pipelines);
/*  324 */     nglGenProgramPipelines(pipelines.remaining(), MemoryUtil.getAddress(pipelines), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGenProgramPipelines(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGenProgramPipelines() {
/*  330 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  331 */     long function_pointer = caps.glGenProgramPipelines;
/*  332 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  333 */     IntBuffer pipelines = APIUtil.getBufferInt(caps);
/*  334 */     nglGenProgramPipelines(1, MemoryUtil.getAddress(pipelines), function_pointer);
/*  335 */     return pipelines.get(0);
/*      */   }
/*      */ 
/*      */   public static boolean glIsProgramPipeline(int pipeline) {
/*  339 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  340 */     long function_pointer = caps.glIsProgramPipeline;
/*  341 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  342 */     boolean __result = nglIsProgramPipeline(pipeline, function_pointer);
/*  343 */     return __result;
/*      */   }
/*      */   static native boolean nglIsProgramPipeline(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetProgramPipeline(int pipeline, int pname, IntBuffer params) {
/*  348 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  349 */     long function_pointer = caps.glGetProgramPipelineiv;
/*  350 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  351 */     BufferChecks.checkBuffer(params, 1);
/*  352 */     nglGetProgramPipelineiv(pipeline, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetProgramPipelineiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetProgramPipelinei(int pipeline, int pname) {
/*  358 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  359 */     long function_pointer = caps.glGetProgramPipelineiv;
/*  360 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  361 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  362 */     nglGetProgramPipelineiv(pipeline, pname, MemoryUtil.getAddress(params), function_pointer);
/*  363 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glProgramUniform1i(int program, int location, int v0) {
/*  367 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  368 */     long function_pointer = caps.glProgramUniform1i;
/*  369 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  370 */     nglProgramUniform1i(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2i(int program, int location, int v0, int v1) {
/*  375 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  376 */     long function_pointer = caps.glProgramUniform2i;
/*  377 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  378 */     nglProgramUniform2i(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3i(int program, int location, int v0, int v1, int v2) {
/*  383 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  384 */     long function_pointer = caps.glProgramUniform3i;
/*  385 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  386 */     nglProgramUniform3i(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3) {
/*  391 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  392 */     long function_pointer = caps.glProgramUniform4i;
/*  393 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  394 */     nglProgramUniform4i(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1f(int program, int location, float v0) {
/*  399 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  400 */     long function_pointer = caps.glProgramUniform1f;
/*  401 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  402 */     nglProgramUniform1f(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1f(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2f(int program, int location, float v0, float v1) {
/*  407 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  408 */     long function_pointer = caps.glProgramUniform2f;
/*  409 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  410 */     nglProgramUniform2f(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3f(int program, int location, float v0, float v1, float v2) {
/*  415 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  416 */     long function_pointer = caps.glProgramUniform3f;
/*  417 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  418 */     nglProgramUniform3f(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3) {
/*  423 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  424 */     long function_pointer = caps.glProgramUniform4f;
/*  425 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  426 */     nglProgramUniform4f(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1d(int program, int location, double v0) {
/*  431 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  432 */     long function_pointer = caps.glProgramUniform1d;
/*  433 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  434 */     nglProgramUniform1d(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1d(int paramInt1, int paramInt2, double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2d(int program, int location, double v0, double v1) {
/*  439 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  440 */     long function_pointer = caps.glProgramUniform2d;
/*  441 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  442 */     nglProgramUniform2d(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2d(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3d(int program, int location, double v0, double v1, double v2) {
/*  447 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  448 */     long function_pointer = caps.glProgramUniform3d;
/*  449 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  450 */     nglProgramUniform3d(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3d(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4d(int program, int location, double v0, double v1, double v2, double v3) {
/*  455 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  456 */     long function_pointer = caps.glProgramUniform4d;
/*  457 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  458 */     nglProgramUniform4d(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4d(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1(int program, int location, IntBuffer value) {
/*  463 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  464 */     long function_pointer = caps.glProgramUniform1iv;
/*  465 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  466 */     BufferChecks.checkDirect(value);
/*  467 */     nglProgramUniform1iv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2(int program, int location, IntBuffer value) {
/*  472 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  473 */     long function_pointer = caps.glProgramUniform2iv;
/*  474 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  475 */     BufferChecks.checkDirect(value);
/*  476 */     nglProgramUniform2iv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3(int program, int location, IntBuffer value) {
/*  481 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  482 */     long function_pointer = caps.glProgramUniform3iv;
/*  483 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  484 */     BufferChecks.checkDirect(value);
/*  485 */     nglProgramUniform3iv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4(int program, int location, IntBuffer value) {
/*  490 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  491 */     long function_pointer = caps.glProgramUniform4iv;
/*  492 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  493 */     BufferChecks.checkDirect(value);
/*  494 */     nglProgramUniform4iv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4iv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform1(int program, int location, FloatBuffer value) {
/*  499 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  500 */     long function_pointer = caps.glProgramUniform1fv;
/*  501 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  502 */     BufferChecks.checkDirect(value);
/*  503 */     nglProgramUniform1fv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2(int program, int location, FloatBuffer value) {
/*  508 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  509 */     long function_pointer = caps.glProgramUniform2fv;
/*  510 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  511 */     BufferChecks.checkDirect(value);
/*  512 */     nglProgramUniform2fv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3(int program, int location, FloatBuffer value) {
/*  517 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  518 */     long function_pointer = caps.glProgramUniform3fv;
/*  519 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  520 */     BufferChecks.checkDirect(value);
/*  521 */     nglProgramUniform3fv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4(int program, int location, FloatBuffer value) {
/*  526 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  527 */     long function_pointer = caps.glProgramUniform4fv;
/*  528 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  529 */     BufferChecks.checkDirect(value);
/*  530 */     nglProgramUniform4fv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4fv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform1(int program, int location, DoubleBuffer value) {
/*  535 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  536 */     long function_pointer = caps.glProgramUniform1dv;
/*  537 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  538 */     BufferChecks.checkDirect(value);
/*  539 */     nglProgramUniform1dv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2(int program, int location, DoubleBuffer value) {
/*  544 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  545 */     long function_pointer = caps.glProgramUniform2dv;
/*  546 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  547 */     BufferChecks.checkDirect(value);
/*  548 */     nglProgramUniform2dv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3(int program, int location, DoubleBuffer value) {
/*  553 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  554 */     long function_pointer = caps.glProgramUniform3dv;
/*  555 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  556 */     BufferChecks.checkDirect(value);
/*  557 */     nglProgramUniform3dv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4(int program, int location, DoubleBuffer value) {
/*  562 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  563 */     long function_pointer = caps.glProgramUniform4dv;
/*  564 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  565 */     BufferChecks.checkDirect(value);
/*  566 */     nglProgramUniform4dv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4dv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform1ui(int program, int location, int v0) {
/*  571 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  572 */     long function_pointer = caps.glProgramUniform1ui;
/*  573 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  574 */     nglProgramUniform1ui(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2ui(int program, int location, int v0, int v1) {
/*  579 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  580 */     long function_pointer = caps.glProgramUniform2ui;
/*  581 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  582 */     nglProgramUniform2ui(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3ui(int program, int location, int v0, int v1, int v2) {
/*  587 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  588 */     long function_pointer = caps.glProgramUniform3ui;
/*  589 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  590 */     nglProgramUniform3ui(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3) {
/*  595 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  596 */     long function_pointer = caps.glProgramUniform4ui;
/*  597 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  598 */     nglProgramUniform4ui(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4ui(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1u(int program, int location, IntBuffer value) {
/*  603 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  604 */     long function_pointer = caps.glProgramUniform1uiv;
/*  605 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  606 */     BufferChecks.checkDirect(value);
/*  607 */     nglProgramUniform1uiv(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2u(int program, int location, IntBuffer value) {
/*  612 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  613 */     long function_pointer = caps.glProgramUniform2uiv;
/*  614 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  615 */     BufferChecks.checkDirect(value);
/*  616 */     nglProgramUniform2uiv(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3u(int program, int location, IntBuffer value) {
/*  621 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  622 */     long function_pointer = caps.glProgramUniform3uiv;
/*  623 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  624 */     BufferChecks.checkDirect(value);
/*  625 */     nglProgramUniform3uiv(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4u(int program, int location, IntBuffer value) {
/*  630 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  631 */     long function_pointer = caps.glProgramUniform4uiv;
/*  632 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  633 */     BufferChecks.checkDirect(value);
/*  634 */     nglProgramUniform4uiv(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4uiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2(int program, int location, boolean transpose, FloatBuffer value) {
/*  639 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  640 */     long function_pointer = caps.glProgramUniformMatrix2fv;
/*  641 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  642 */     BufferChecks.checkDirect(value);
/*  643 */     nglProgramUniformMatrix2fv(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3(int program, int location, boolean transpose, FloatBuffer value) {
/*  648 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  649 */     long function_pointer = caps.glProgramUniformMatrix3fv;
/*  650 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  651 */     BufferChecks.checkDirect(value);
/*  652 */     nglProgramUniformMatrix3fv(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4(int program, int location, boolean transpose, FloatBuffer value) {
/*  657 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  658 */     long function_pointer = caps.glProgramUniformMatrix4fv;
/*  659 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  660 */     BufferChecks.checkDirect(value);
/*  661 */     nglProgramUniformMatrix4fv(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2(int program, int location, boolean transpose, DoubleBuffer value) {
/*  666 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  667 */     long function_pointer = caps.glProgramUniformMatrix2dv;
/*  668 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  669 */     BufferChecks.checkDirect(value);
/*  670 */     nglProgramUniformMatrix2dv(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3(int program, int location, boolean transpose, DoubleBuffer value) {
/*  675 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  676 */     long function_pointer = caps.glProgramUniformMatrix3dv;
/*  677 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  678 */     BufferChecks.checkDirect(value);
/*  679 */     nglProgramUniformMatrix3dv(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4(int program, int location, boolean transpose, DoubleBuffer value) {
/*  684 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  685 */     long function_pointer = caps.glProgramUniformMatrix4dv;
/*  686 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  687 */     BufferChecks.checkDirect(value);
/*  688 */     nglProgramUniformMatrix4dv(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, FloatBuffer value) {
/*  693 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  694 */     long function_pointer = caps.glProgramUniformMatrix2x3fv;
/*  695 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  696 */     BufferChecks.checkDirect(value);
/*  697 */     nglProgramUniformMatrix2x3fv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2x3fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, FloatBuffer value) {
/*  702 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  703 */     long function_pointer = caps.glProgramUniformMatrix3x2fv;
/*  704 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  705 */     BufferChecks.checkDirect(value);
/*  706 */     nglProgramUniformMatrix3x2fv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3x2fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, FloatBuffer value) {
/*  711 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  712 */     long function_pointer = caps.glProgramUniformMatrix2x4fv;
/*  713 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  714 */     BufferChecks.checkDirect(value);
/*  715 */     nglProgramUniformMatrix2x4fv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2x4fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, FloatBuffer value) {
/*  720 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  721 */     long function_pointer = caps.glProgramUniformMatrix4x2fv;
/*  722 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  723 */     BufferChecks.checkDirect(value);
/*  724 */     nglProgramUniformMatrix4x2fv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4x2fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, FloatBuffer value) {
/*  729 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  730 */     long function_pointer = caps.glProgramUniformMatrix3x4fv;
/*  731 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  732 */     BufferChecks.checkDirect(value);
/*  733 */     nglProgramUniformMatrix3x4fv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3x4fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, FloatBuffer value) {
/*  738 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  739 */     long function_pointer = caps.glProgramUniformMatrix4x3fv;
/*  740 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  741 */     BufferChecks.checkDirect(value);
/*  742 */     nglProgramUniformMatrix4x3fv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4x3fv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, DoubleBuffer value) {
/*  747 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  748 */     long function_pointer = caps.glProgramUniformMatrix2x3dv;
/*  749 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  750 */     BufferChecks.checkDirect(value);
/*  751 */     nglProgramUniformMatrix2x3dv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2x3dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, DoubleBuffer value) {
/*  756 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  757 */     long function_pointer = caps.glProgramUniformMatrix3x2dv;
/*  758 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  759 */     BufferChecks.checkDirect(value);
/*  760 */     nglProgramUniformMatrix3x2dv(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3x2dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, DoubleBuffer value) {
/*  765 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  766 */     long function_pointer = caps.glProgramUniformMatrix2x4dv;
/*  767 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  768 */     BufferChecks.checkDirect(value);
/*  769 */     nglProgramUniformMatrix2x4dv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2x4dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, DoubleBuffer value) {
/*  774 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  775 */     long function_pointer = caps.glProgramUniformMatrix4x2dv;
/*  776 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  777 */     BufferChecks.checkDirect(value);
/*  778 */     nglProgramUniformMatrix4x2dv(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4x2dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, DoubleBuffer value) {
/*  783 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  784 */     long function_pointer = caps.glProgramUniformMatrix3x4dv;
/*  785 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  786 */     BufferChecks.checkDirect(value);
/*  787 */     nglProgramUniformMatrix3x4dv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3x4dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, DoubleBuffer value) {
/*  792 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  793 */     long function_pointer = caps.glProgramUniformMatrix4x3dv;
/*  794 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  795 */     BufferChecks.checkDirect(value);
/*  796 */     nglProgramUniformMatrix4x3dv(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4x3dv(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glValidateProgramPipeline(int pipeline) {
/*  801 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  802 */     long function_pointer = caps.glValidateProgramPipeline;
/*  803 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  804 */     nglValidateProgramPipeline(pipeline, function_pointer);
/*      */   }
/*      */   static native void nglValidateProgramPipeline(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetProgramPipelineInfoLog(int pipeline, IntBuffer length, ByteBuffer infoLog) {
/*  809 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  810 */     long function_pointer = caps.glGetProgramPipelineInfoLog;
/*  811 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  812 */     if (length != null)
/*  813 */       BufferChecks.checkBuffer(length, 1);
/*  814 */     BufferChecks.checkDirect(infoLog);
/*  815 */     nglGetProgramPipelineInfoLog(pipeline, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetProgramPipelineInfoLog(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static String glGetProgramPipelineInfoLog(int pipeline, int bufSize) {
/*  821 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  822 */     long function_pointer = caps.glGetProgramPipelineInfoLog;
/*  823 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  824 */     IntBuffer infoLog_length = APIUtil.getLengths(caps);
/*  825 */     ByteBuffer infoLog = APIUtil.getBufferByte(caps, bufSize);
/*  826 */     nglGetProgramPipelineInfoLog(pipeline, bufSize, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/*  827 */     infoLog.limit(infoLog_length.get(0));
/*  828 */     return APIUtil.getString(caps, infoLog);
/*      */   }
/*      */ 
/*      */   public static void glVertexAttribL1d(int index, double x) {
/*  832 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  833 */     long function_pointer = caps.glVertexAttribL1d;
/*  834 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  835 */     nglVertexAttribL1d(index, x, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL1d(int paramInt, double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribL2d(int index, double x, double y) {
/*  840 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  841 */     long function_pointer = caps.glVertexAttribL2d;
/*  842 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  843 */     nglVertexAttribL2d(index, x, y, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribL3d(int index, double x, double y, double z) {
/*  848 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  849 */     long function_pointer = caps.glVertexAttribL3d;
/*  850 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  851 */     nglVertexAttribL3d(index, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribL4d(int index, double x, double y, double z, double w) {
/*  856 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  857 */     long function_pointer = caps.glVertexAttribL4d;
/*  858 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  859 */     nglVertexAttribL4d(index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glVertexAttribL1(int index, DoubleBuffer v) {
/*  864 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  865 */     long function_pointer = caps.glVertexAttribL1dv;
/*  866 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  867 */     BufferChecks.checkBuffer(v, 1);
/*  868 */     nglVertexAttribL1dv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL1dv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribL2(int index, DoubleBuffer v) {
/*  873 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  874 */     long function_pointer = caps.glVertexAttribL2dv;
/*  875 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  876 */     BufferChecks.checkBuffer(v, 2);
/*  877 */     nglVertexAttribL2dv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL2dv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribL3(int index, DoubleBuffer v) {
/*  882 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  883 */     long function_pointer = caps.glVertexAttribL3dv;
/*  884 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  885 */     BufferChecks.checkBuffer(v, 3);
/*  886 */     nglVertexAttribL3dv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL3dv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribL4(int index, DoubleBuffer v) {
/*  891 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  892 */     long function_pointer = caps.glVertexAttribL4dv;
/*  893 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  894 */     BufferChecks.checkBuffer(v, 4);
/*  895 */     nglVertexAttribL4dv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribL4dv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) {
/*  900 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  901 */     long function_pointer = caps.glVertexAttribLPointer;
/*  902 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  903 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  904 */     BufferChecks.checkDirect(pointer);
/*  905 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = pointer;
/*  906 */     nglVertexAttribLPointer(index, size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglVertexAttribLPointer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*  910 */   public static void glVertexAttribLPointer(int index, int size, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  911 */     long function_pointer = caps.glVertexAttribLPointer;
/*  912 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  913 */     GLChecks.ensureArrayVBOenabled(caps);
/*  914 */     nglVertexAttribLPointerBO(index, size, 5130, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglVertexAttribLPointerBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetVertexAttribL(int index, int pname, DoubleBuffer params) {
/*  919 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  920 */     long function_pointer = caps.glGetVertexAttribLdv;
/*  921 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  922 */     BufferChecks.checkBuffer(params, 4);
/*  923 */     nglGetVertexAttribLdv(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetVertexAttribLdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glViewportArray(int first, FloatBuffer v) {
/*  928 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  929 */     long function_pointer = caps.glViewportArrayv;
/*  930 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  931 */     BufferChecks.checkDirect(v);
/*  932 */     nglViewportArrayv(first, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglViewportArrayv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glViewportIndexedf(int index, float x, float y, float w, float h) {
/*  937 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  938 */     long function_pointer = caps.glViewportIndexedf;
/*  939 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  940 */     nglViewportIndexedf(index, x, y, w, h, function_pointer);
/*      */   }
/*      */   static native void nglViewportIndexedf(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glViewportIndexed(int index, FloatBuffer v) {
/*  945 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  946 */     long function_pointer = caps.glViewportIndexedfv;
/*  947 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  948 */     BufferChecks.checkBuffer(v, 4);
/*  949 */     nglViewportIndexedfv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglViewportIndexedfv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glScissorArray(int first, IntBuffer v) {
/*  954 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  955 */     long function_pointer = caps.glScissorArrayv;
/*  956 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  957 */     BufferChecks.checkDirect(v);
/*  958 */     nglScissorArrayv(first, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglScissorArrayv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glScissorIndexed(int index, int left, int bottom, int width, int height) {
/*  963 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  964 */     long function_pointer = caps.glScissorIndexed;
/*  965 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  966 */     nglScissorIndexed(index, left, bottom, width, height, function_pointer);
/*      */   }
/*      */   static native void nglScissorIndexed(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glScissorIndexed(int index, IntBuffer v) {
/*  971 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  972 */     long function_pointer = caps.glScissorIndexedv;
/*  973 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  974 */     BufferChecks.checkBuffer(v, 4);
/*  975 */     nglScissorIndexedv(index, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglScissorIndexedv(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDepthRangeArray(int first, DoubleBuffer v) {
/*  980 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  981 */     long function_pointer = caps.glDepthRangeArrayv;
/*  982 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  983 */     BufferChecks.checkDirect(v);
/*  984 */     nglDepthRangeArrayv(first, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglDepthRangeArrayv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glDepthRangeIndexed(int index, double n, double f) {
/*  989 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  990 */     long function_pointer = caps.glDepthRangeIndexed;
/*  991 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  992 */     nglDepthRangeIndexed(index, n, f, function_pointer);
/*      */   }
/*      */   static native void nglDepthRangeIndexed(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*      */ 
/*      */   public static void glGetFloat(int target, int index, FloatBuffer data) {
/*  997 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  998 */     long function_pointer = caps.glGetFloati_v;
/*  999 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1000 */     BufferChecks.checkDirect(data);
/* 1001 */     nglGetFloati_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFloati_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetFloat(int target, int index) {
/* 1007 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1008 */     long function_pointer = caps.glGetFloati_v;
/* 1009 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1010 */     FloatBuffer data = APIUtil.getBufferFloat(caps);
/* 1011 */     nglGetFloati_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/* 1012 */     return data.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetDouble(int target, int index, DoubleBuffer data) {
/* 1016 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1017 */     long function_pointer = caps.glGetDoublei_v;
/* 1018 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1019 */     BufferChecks.checkDirect(data);
/* 1020 */     nglGetDoublei_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetDoublei_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static double glGetDouble(int target, int index) {
/* 1026 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1027 */     long function_pointer = caps.glGetDoublei_v;
/* 1028 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1029 */     DoubleBuffer data = APIUtil.getBufferDouble(caps);
/* 1030 */     nglGetDoublei_v(target, index, MemoryUtil.getAddress(data), function_pointer);
/* 1031 */     return data.get(0);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL41
 * JD-Core Version:    0.6.2
 */