/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ public final class ARBSeparateShaderObjects
/*     */ {
/*     */   public static final int GL_VERTEX_SHADER_BIT = 1;
/*     */   public static final int GL_FRAGMENT_SHADER_BIT = 2;
/*     */   public static final int GL_GEOMETRY_SHADER_BIT = 4;
/*     */   public static final int GL_TESS_CONTROL_SHADER_BIT = 8;
/*     */   public static final int GL_TESS_EVALUATION_SHADER_BIT = 16;
/*     */   public static final int GL_ALL_SHADER_BITS = -1;
/*     */   public static final int GL_PROGRAM_SEPARABLE = 33368;
/*     */   public static final int GL_ACTIVE_PROGRAM = 33369;
/*     */   public static final int GL_PROGRAM_PIPELINE_BINDING = 33370;
/*     */ 
/*     */   public static void glUseProgramStages(int pipeline, int stages, int program)
/*     */   {
/*  40 */     GL41.glUseProgramStages(pipeline, stages, program);
/*     */   }
/*     */ 
/*     */   public static void glActiveShaderProgram(int pipeline, int program) {
/*  44 */     GL41.glActiveShaderProgram(pipeline, program);
/*     */   }
/*     */ 
/*     */   public static int glCreateShaderProgram(int type, ByteBuffer string)
/*     */   {
/*  51 */     return GL41.glCreateShaderProgram(type, string);
/*     */   }
/*     */ 
/*     */   public static int glCreateShaderProgram(int type, int count, ByteBuffer strings)
/*     */   {
/*  60 */     return GL41.glCreateShaderProgram(type, count, strings);
/*     */   }
/*     */ 
/*     */   public static int glCreateShaderProgram(int type, ByteBuffer[] strings)
/*     */   {
/*  65 */     return GL41.glCreateShaderProgram(type, strings);
/*     */   }
/*     */ 
/*     */   public static int glCreateShaderProgram(int type, CharSequence string)
/*     */   {
/*  70 */     return GL41.glCreateShaderProgram(type, string);
/*     */   }
/*     */ 
/*     */   public static int glCreateShaderProgram(int type, CharSequence[] strings)
/*     */   {
/*  75 */     return GL41.glCreateShaderProgram(type, strings);
/*     */   }
/*     */ 
/*     */   public static void glBindProgramPipeline(int pipeline) {
/*  79 */     GL41.glBindProgramPipeline(pipeline);
/*     */   }
/*     */ 
/*     */   public static void glDeleteProgramPipelines(IntBuffer pipelines) {
/*  83 */     GL41.glDeleteProgramPipelines(pipelines);
/*     */   }
/*     */ 
/*     */   public static void glDeleteProgramPipelines(int pipeline)
/*     */   {
/*  88 */     GL41.glDeleteProgramPipelines(pipeline);
/*     */   }
/*     */ 
/*     */   public static void glGenProgramPipelines(IntBuffer pipelines) {
/*  92 */     GL41.glGenProgramPipelines(pipelines);
/*     */   }
/*     */ 
/*     */   public static int glGenProgramPipelines()
/*     */   {
/*  97 */     return GL41.glGenProgramPipelines();
/*     */   }
/*     */ 
/*     */   public static boolean glIsProgramPipeline(int pipeline) {
/* 101 */     return GL41.glIsProgramPipeline(pipeline);
/*     */   }
/*     */ 
/*     */   public static void glProgramParameteri(int program, int pname, int value) {
/* 105 */     GL41.glProgramParameteri(program, pname, value);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramPipeline(int pipeline, int pname, IntBuffer params) {
/* 109 */     GL41.glGetProgramPipeline(pipeline, pname, params);
/*     */   }
/*     */ 
/*     */   public static int glGetProgramPipelinei(int pipeline, int pname)
/*     */   {
/* 114 */     return GL41.glGetProgramPipelinei(pipeline, pname);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1i(int program, int location, int v0) {
/* 118 */     GL41.glProgramUniform1i(program, location, v0);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2i(int program, int location, int v0, int v1) {
/* 122 */     GL41.glProgramUniform2i(program, location, v0, v1);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3i(int program, int location, int v0, int v1, int v2) {
/* 126 */     GL41.glProgramUniform3i(program, location, v0, v1, v2);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3) {
/* 130 */     GL41.glProgramUniform4i(program, location, v0, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1f(int program, int location, float v0) {
/* 134 */     GL41.glProgramUniform1f(program, location, v0);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2f(int program, int location, float v0, float v1) {
/* 138 */     GL41.glProgramUniform2f(program, location, v0, v1);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3f(int program, int location, float v0, float v1, float v2) {
/* 142 */     GL41.glProgramUniform3f(program, location, v0, v1, v2);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3) {
/* 146 */     GL41.glProgramUniform4f(program, location, v0, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1d(int program, int location, double v0) {
/* 150 */     GL41.glProgramUniform1d(program, location, v0);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2d(int program, int location, double v0, double v1) {
/* 154 */     GL41.glProgramUniform2d(program, location, v0, v1);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3d(int program, int location, double v0, double v1, double v2) {
/* 158 */     GL41.glProgramUniform3d(program, location, v0, v1, v2);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4d(int program, int location, double v0, double v1, double v2, double v3) {
/* 162 */     GL41.glProgramUniform4d(program, location, v0, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1(int program, int location, IntBuffer value) {
/* 166 */     GL41.glProgramUniform1(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2(int program, int location, IntBuffer value) {
/* 170 */     GL41.glProgramUniform2(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3(int program, int location, IntBuffer value) {
/* 174 */     GL41.glProgramUniform3(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4(int program, int location, IntBuffer value) {
/* 178 */     GL41.glProgramUniform4(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1(int program, int location, FloatBuffer value) {
/* 182 */     GL41.glProgramUniform1(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2(int program, int location, FloatBuffer value) {
/* 186 */     GL41.glProgramUniform2(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3(int program, int location, FloatBuffer value) {
/* 190 */     GL41.glProgramUniform3(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4(int program, int location, FloatBuffer value) {
/* 194 */     GL41.glProgramUniform4(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1(int program, int location, DoubleBuffer value) {
/* 198 */     GL41.glProgramUniform1(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2(int program, int location, DoubleBuffer value) {
/* 202 */     GL41.glProgramUniform2(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3(int program, int location, DoubleBuffer value) {
/* 206 */     GL41.glProgramUniform3(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4(int program, int location, DoubleBuffer value) {
/* 210 */     GL41.glProgramUniform4(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1ui(int program, int location, int v0) {
/* 214 */     GL41.glProgramUniform1ui(program, location, v0);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2ui(int program, int location, int v0, int v1) {
/* 218 */     GL41.glProgramUniform2ui(program, location, v0, v1);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3ui(int program, int location, int v0, int v1, int v2) {
/* 222 */     GL41.glProgramUniform3ui(program, location, v0, v1, v2);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3) {
/* 226 */     GL41.glProgramUniform4ui(program, location, v0, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1u(int program, int location, IntBuffer value) {
/* 230 */     GL41.glProgramUniform1u(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform2u(int program, int location, IntBuffer value) {
/* 234 */     GL41.glProgramUniform2u(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform3u(int program, int location, IntBuffer value) {
/* 238 */     GL41.glProgramUniform3u(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform4u(int program, int location, IntBuffer value) {
/* 242 */     GL41.glProgramUniform4u(program, location, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix2(int program, int location, boolean transpose, FloatBuffer value) {
/* 246 */     GL41.glProgramUniformMatrix2(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix3(int program, int location, boolean transpose, FloatBuffer value) {
/* 250 */     GL41.glProgramUniformMatrix3(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix4(int program, int location, boolean transpose, FloatBuffer value) {
/* 254 */     GL41.glProgramUniformMatrix4(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix2(int program, int location, boolean transpose, DoubleBuffer value) {
/* 258 */     GL41.glProgramUniformMatrix2(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix3(int program, int location, boolean transpose, DoubleBuffer value) {
/* 262 */     GL41.glProgramUniformMatrix3(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix4(int program, int location, boolean transpose, DoubleBuffer value) {
/* 266 */     GL41.glProgramUniformMatrix4(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, FloatBuffer value) {
/* 270 */     GL41.glProgramUniformMatrix2x3(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, FloatBuffer value) {
/* 274 */     GL41.glProgramUniformMatrix3x2(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, FloatBuffer value) {
/* 278 */     GL41.glProgramUniformMatrix2x4(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, FloatBuffer value) {
/* 282 */     GL41.glProgramUniformMatrix4x2(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, FloatBuffer value) {
/* 286 */     GL41.glProgramUniformMatrix3x4(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, FloatBuffer value) {
/* 290 */     GL41.glProgramUniformMatrix4x3(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, DoubleBuffer value) {
/* 294 */     GL41.glProgramUniformMatrix2x3(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, DoubleBuffer value) {
/* 298 */     GL41.glProgramUniformMatrix3x2(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, DoubleBuffer value) {
/* 302 */     GL41.glProgramUniformMatrix2x4(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, DoubleBuffer value) {
/* 306 */     GL41.glProgramUniformMatrix4x2(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, DoubleBuffer value) {
/* 310 */     GL41.glProgramUniformMatrix3x4(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, DoubleBuffer value) {
/* 314 */     GL41.glProgramUniformMatrix4x3(program, location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glValidateProgramPipeline(int pipeline) {
/* 318 */     GL41.glValidateProgramPipeline(pipeline);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramPipelineInfoLog(int pipeline, IntBuffer length, ByteBuffer infoLog) {
/* 322 */     GL41.glGetProgramPipelineInfoLog(pipeline, length, infoLog);
/*     */   }
/*     */ 
/*     */   public static String glGetProgramPipelineInfoLog(int pipeline, int bufSize)
/*     */   {
/* 327 */     return GL41.glGetProgramPipelineInfoLog(pipeline, bufSize);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSeparateShaderObjects
 * JD-Core Version:    0.6.2
 */