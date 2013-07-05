/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ public final class ARBSamplerObjects
/*     */ {
/*     */   public static final int GL_SAMPLER_BINDING = 35097;
/*     */ 
/*     */   public static void glGenSamplers(IntBuffer samplers)
/*     */   {
/*  19 */     GL33.glGenSamplers(samplers);
/*     */   }
/*     */ 
/*     */   public static int glGenSamplers()
/*     */   {
/*  24 */     return GL33.glGenSamplers();
/*     */   }
/*     */ 
/*     */   public static void glDeleteSamplers(IntBuffer samplers) {
/*  28 */     GL33.glDeleteSamplers(samplers);
/*     */   }
/*     */ 
/*     */   public static void glDeleteSamplers(int sampler)
/*     */   {
/*  33 */     GL33.glDeleteSamplers(sampler);
/*     */   }
/*     */ 
/*     */   public static boolean glIsSampler(int sampler) {
/*  37 */     return GL33.glIsSampler(sampler);
/*     */   }
/*     */ 
/*     */   public static void glBindSampler(int unit, int sampler) {
/*  41 */     GL33.glBindSampler(unit, sampler);
/*     */   }
/*     */ 
/*     */   public static void glSamplerParameteri(int sampler, int pname, int param) {
/*  45 */     GL33.glSamplerParameteri(sampler, pname, param);
/*     */   }
/*     */ 
/*     */   public static void glSamplerParameterf(int sampler, int pname, float param) {
/*  49 */     GL33.glSamplerParameterf(sampler, pname, param);
/*     */   }
/*     */ 
/*     */   public static void glSamplerParameter(int sampler, int pname, IntBuffer params) {
/*  53 */     GL33.glSamplerParameter(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static void glSamplerParameter(int sampler, int pname, FloatBuffer params) {
/*  57 */     GL33.glSamplerParameter(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static void glSamplerParameterI(int sampler, int pname, IntBuffer params) {
/*  61 */     GL33.glSamplerParameterI(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static void glSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/*  65 */     GL33.glSamplerParameterIu(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameter(int sampler, int pname, IntBuffer params) {
/*  69 */     GL33.glGetSamplerParameter(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static int glGetSamplerParameteri(int sampler, int pname)
/*     */   {
/*  74 */     return GL33.glGetSamplerParameteri(sampler, pname);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameter(int sampler, int pname, FloatBuffer params) {
/*  78 */     GL33.glGetSamplerParameter(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static float glGetSamplerParameterf(int sampler, int pname)
/*     */   {
/*  83 */     return GL33.glGetSamplerParameterf(sampler, pname);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameterI(int sampler, int pname, IntBuffer params) {
/*  87 */     GL33.glGetSamplerParameterI(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static int glGetSamplerParameterIi(int sampler, int pname)
/*     */   {
/*  92 */     return GL33.glGetSamplerParameterIi(sampler, pname);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/*  96 */     GL33.glGetSamplerParameterIu(sampler, pname, params);
/*     */   }
/*     */ 
/*     */   public static int glGetSamplerParameterIui(int sampler, int pname)
/*     */   {
/* 101 */     return GL33.glGetSamplerParameterIui(sampler, pname);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSamplerObjects
 * JD-Core Version:    0.6.2
 */