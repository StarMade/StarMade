/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */
/*  13:    */public final class ARBSamplerObjects
/*  14:    */{
/*  15:    */  public static final int GL_SAMPLER_BINDING = 35097;
/*  16:    */  
/*  17:    */  public static void glGenSamplers(IntBuffer samplers)
/*  18:    */  {
/*  19: 19 */    GL33.glGenSamplers(samplers);
/*  20:    */  }
/*  21:    */  
/*  22:    */  public static int glGenSamplers()
/*  23:    */  {
/*  24: 24 */    return GL33.glGenSamplers();
/*  25:    */  }
/*  26:    */  
/*  27:    */  public static void glDeleteSamplers(IntBuffer samplers) {
/*  28: 28 */    GL33.glDeleteSamplers(samplers);
/*  29:    */  }
/*  30:    */  
/*  31:    */  public static void glDeleteSamplers(int sampler)
/*  32:    */  {
/*  33: 33 */    GL33.glDeleteSamplers(sampler);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public static boolean glIsSampler(int sampler) {
/*  37: 37 */    return GL33.glIsSampler(sampler);
/*  38:    */  }
/*  39:    */  
/*  40:    */  public static void glBindSampler(int unit, int sampler) {
/*  41: 41 */    GL33.glBindSampler(unit, sampler);
/*  42:    */  }
/*  43:    */  
/*  44:    */  public static void glSamplerParameteri(int sampler, int pname, int param) {
/*  45: 45 */    GL33.glSamplerParameteri(sampler, pname, param);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public static void glSamplerParameterf(int sampler, int pname, float param) {
/*  49: 49 */    GL33.glSamplerParameterf(sampler, pname, param);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public static void glSamplerParameter(int sampler, int pname, IntBuffer params) {
/*  53: 53 */    GL33.glSamplerParameter(sampler, pname, params);
/*  54:    */  }
/*  55:    */  
/*  56:    */  public static void glSamplerParameter(int sampler, int pname, FloatBuffer params) {
/*  57: 57 */    GL33.glSamplerParameter(sampler, pname, params);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public static void glSamplerParameterI(int sampler, int pname, IntBuffer params) {
/*  61: 61 */    GL33.glSamplerParameterI(sampler, pname, params);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public static void glSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/*  65: 65 */    GL33.glSamplerParameterIu(sampler, pname, params);
/*  66:    */  }
/*  67:    */  
/*  68:    */  public static void glGetSamplerParameter(int sampler, int pname, IntBuffer params) {
/*  69: 69 */    GL33.glGetSamplerParameter(sampler, pname, params);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static int glGetSamplerParameteri(int sampler, int pname)
/*  73:    */  {
/*  74: 74 */    return GL33.glGetSamplerParameteri(sampler, pname);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public static void glGetSamplerParameter(int sampler, int pname, FloatBuffer params) {
/*  78: 78 */    GL33.glGetSamplerParameter(sampler, pname, params);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static float glGetSamplerParameterf(int sampler, int pname)
/*  82:    */  {
/*  83: 83 */    return GL33.glGetSamplerParameterf(sampler, pname);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static void glGetSamplerParameterI(int sampler, int pname, IntBuffer params) {
/*  87: 87 */    GL33.glGetSamplerParameterI(sampler, pname, params);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public static int glGetSamplerParameterIi(int sampler, int pname)
/*  91:    */  {
/*  92: 92 */    return GL33.glGetSamplerParameterIi(sampler, pname);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static void glGetSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/*  96: 96 */    GL33.glGetSamplerParameterIu(sampler, pname, params);
/*  97:    */  }
/*  98:    */  
/*  99:    */  public static int glGetSamplerParameterIui(int sampler, int pname)
/* 100:    */  {
/* 101:101 */    return GL33.glGetSamplerParameterIui(sampler, pname);
/* 102:    */  }
/* 103:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSamplerObjects
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */