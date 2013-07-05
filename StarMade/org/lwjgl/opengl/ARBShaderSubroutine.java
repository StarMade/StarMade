/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ public final class ARBShaderSubroutine
/*     */ {
/*     */   public static final int GL_ACTIVE_SUBROUTINES = 36325;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_UNIFORMS = 36326;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 36423;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 36424;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;
/*     */   public static final int GL_MAX_SUBROUTINES = 36327;
/*     */   public static final int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;
/*     */   public static final int GL_NUM_COMPATIBLE_SUBROUTINES = 36426;
/*     */   public static final int GL_COMPATIBLE_SUBROUTINES = 36427;
/*     */   public static final int GL_UNIFORM_SIZE = 35384;
/*     */   public static final int GL_UNIFORM_NAME_LENGTH = 35385;
/*     */ 
/*     */   public static int glGetSubroutineUniformLocation(int program, int shadertype, ByteBuffer name)
/*     */   {
/*  37 */     return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
/*     */   }
/*     */ 
/*     */   public static int glGetSubroutineUniformLocation(int program, int shadertype, CharSequence name)
/*     */   {
/*  42 */     return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
/*     */   }
/*     */ 
/*     */   public static int glGetSubroutineIndex(int program, int shadertype, ByteBuffer name) {
/*  46 */     return GL40.glGetSubroutineIndex(program, shadertype, name);
/*     */   }
/*     */ 
/*     */   public static int glGetSubroutineIndex(int program, int shadertype, CharSequence name)
/*     */   {
/*  51 */     return GL40.glGetSubroutineIndex(program, shadertype, name);
/*     */   }
/*     */ 
/*     */   public static void glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname, IntBuffer values) {
/*  55 */     GL40.glGetActiveSubroutineUniform(program, shadertype, index, pname, values);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname)
/*     */   {
/*  60 */     return GL40.glGetActiveSubroutineUniformi(program, shadertype, index, pname);
/*     */   }
/*     */ 
/*     */   public static void glGetActiveSubroutineUniformName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/*  64 */     GL40.glGetActiveSubroutineUniformName(program, shadertype, index, length, name);
/*     */   }
/*     */ 
/*     */   public static String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize)
/*     */   {
/*  69 */     return GL40.glGetActiveSubroutineUniformName(program, shadertype, index, bufsize);
/*     */   }
/*     */ 
/*     */   public static void glGetActiveSubroutineName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/*  73 */     GL40.glGetActiveSubroutineName(program, shadertype, index, length, name);
/*     */   }
/*     */ 
/*     */   public static String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize)
/*     */   {
/*  78 */     return GL40.glGetActiveSubroutineName(program, shadertype, index, bufsize);
/*     */   }
/*     */ 
/*     */   public static void glUniformSubroutinesu(int shadertype, IntBuffer indices) {
/*  82 */     GL40.glUniformSubroutinesu(shadertype, indices);
/*     */   }
/*     */ 
/*     */   public static void glGetUniformSubroutineu(int shadertype, int location, IntBuffer params) {
/*  86 */     GL40.glGetUniformSubroutineu(shadertype, location, params);
/*     */   }
/*     */ 
/*     */   public static int glGetUniformSubroutineui(int shadertype, int location)
/*     */   {
/*  91 */     return GL40.glGetUniformSubroutineui(shadertype, location);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramStage(int program, int shadertype, int pname, IntBuffer values) {
/*  95 */     GL40.glGetProgramStage(program, shadertype, pname, values);
/*     */   }
/*     */ 
/*     */   public static int glGetProgramStagei(int program, int shadertype, int pname)
/*     */   {
/* 100 */     return GL40.glGetProgramStagei(program, shadertype, pname);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShaderSubroutine
 * JD-Core Version:    0.6.2
 */