/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public class ARBProgram
/*     */ {
/*     */   public static final int GL_PROGRAM_FORMAT_ASCII_ARB = 34933;
/*     */   public static final int GL_PROGRAM_LENGTH_ARB = 34343;
/*     */   public static final int GL_PROGRAM_FORMAT_ARB = 34934;
/*     */   public static final int GL_PROGRAM_BINDING_ARB = 34423;
/*     */   public static final int GL_PROGRAM_INSTRUCTIONS_ARB = 34976;
/*     */   public static final int GL_MAX_PROGRAM_INSTRUCTIONS_ARB = 34977;
/*     */   public static final int GL_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 34978;
/*     */   public static final int GL_MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 34979;
/*     */   public static final int GL_PROGRAM_TEMPORARIES_ARB = 34980;
/*     */   public static final int GL_MAX_PROGRAM_TEMPORARIES_ARB = 34981;
/*     */   public static final int GL_PROGRAM_NATIVE_TEMPORARIES_ARB = 34982;
/*     */   public static final int GL_MAX_PROGRAM_NATIVE_TEMPORARIES_ARB = 34983;
/*     */   public static final int GL_PROGRAM_PARAMETERS_ARB = 34984;
/*     */   public static final int GL_MAX_PROGRAM_PARAMETERS_ARB = 34985;
/*     */   public static final int GL_PROGRAM_NATIVE_PARAMETERS_ARB = 34986;
/*     */   public static final int GL_MAX_PROGRAM_NATIVE_PARAMETERS_ARB = 34987;
/*     */   public static final int GL_PROGRAM_ATTRIBS_ARB = 34988;
/*     */   public static final int GL_MAX_PROGRAM_ATTRIBS_ARB = 34989;
/*     */   public static final int GL_PROGRAM_NATIVE_ATTRIBS_ARB = 34990;
/*     */   public static final int GL_MAX_PROGRAM_NATIVE_ATTRIBS_ARB = 34991;
/*     */   public static final int GL_MAX_PROGRAM_LOCAL_PARAMETERS_ARB = 34996;
/*     */   public static final int GL_MAX_PROGRAM_ENV_PARAMETERS_ARB = 34997;
/*     */   public static final int GL_PROGRAM_UNDER_NATIVE_LIMITS_ARB = 34998;
/*     */   public static final int GL_PROGRAM_STRING_ARB = 34344;
/*     */   public static final int GL_PROGRAM_ERROR_POSITION_ARB = 34379;
/*     */   public static final int GL_CURRENT_MATRIX_ARB = 34369;
/*     */   public static final int GL_TRANSPOSE_CURRENT_MATRIX_ARB = 34999;
/*     */   public static final int GL_CURRENT_MATRIX_STACK_DEPTH_ARB = 34368;
/*     */   public static final int GL_MAX_PROGRAM_MATRICES_ARB = 34351;
/*     */   public static final int GL_MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB = 34350;
/*     */   public static final int GL_PROGRAM_ERROR_STRING_ARB = 34932;
/*     */   public static final int GL_MATRIX0_ARB = 35008;
/*     */   public static final int GL_MATRIX1_ARB = 35009;
/*     */   public static final int GL_MATRIX2_ARB = 35010;
/*     */   public static final int GL_MATRIX3_ARB = 35011;
/*     */   public static final int GL_MATRIX4_ARB = 35012;
/*     */   public static final int GL_MATRIX5_ARB = 35013;
/*     */   public static final int GL_MATRIX6_ARB = 35014;
/*     */   public static final int GL_MATRIX7_ARB = 35015;
/*     */   public static final int GL_MATRIX8_ARB = 35016;
/*     */   public static final int GL_MATRIX9_ARB = 35017;
/*     */   public static final int GL_MATRIX10_ARB = 35018;
/*     */   public static final int GL_MATRIX11_ARB = 35019;
/*     */   public static final int GL_MATRIX12_ARB = 35020;
/*     */   public static final int GL_MATRIX13_ARB = 35021;
/*     */   public static final int GL_MATRIX14_ARB = 35022;
/*     */   public static final int GL_MATRIX15_ARB = 35023;
/*     */   public static final int GL_MATRIX16_ARB = 35024;
/*     */   public static final int GL_MATRIX17_ARB = 35025;
/*     */   public static final int GL_MATRIX18_ARB = 35026;
/*     */   public static final int GL_MATRIX19_ARB = 35027;
/*     */   public static final int GL_MATRIX20_ARB = 35028;
/*     */   public static final int GL_MATRIX21_ARB = 35029;
/*     */   public static final int GL_MATRIX22_ARB = 35030;
/*     */   public static final int GL_MATRIX23_ARB = 35031;
/*     */   public static final int GL_MATRIX24_ARB = 35032;
/*     */   public static final int GL_MATRIX25_ARB = 35033;
/*     */   public static final int GL_MATRIX26_ARB = 35034;
/*     */   public static final int GL_MATRIX27_ARB = 35035;
/*     */   public static final int GL_MATRIX28_ARB = 35036;
/*     */   public static final int GL_MATRIX29_ARB = 35037;
/*     */   public static final int GL_MATRIX30_ARB = 35038;
/*     */   public static final int GL_MATRIX31_ARB = 35039;
/*     */ 
/*     */   public static void glProgramStringARB(int target, int format, ByteBuffer string)
/*     */   {
/* 100 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 101 */     long function_pointer = caps.glProgramStringARB;
/* 102 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 103 */     BufferChecks.checkDirect(string);
/* 104 */     nglProgramStringARB(target, format, string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglProgramStringARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramStringARB(int target, int format, CharSequence string) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glProgramStringARB;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     nglProgramStringARB(target, format, string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glBindProgramARB(int target, int program) {
/* 117 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 118 */     long function_pointer = caps.glBindProgramARB;
/* 119 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 120 */     nglBindProgramARB(target, program, function_pointer);
/*     */   }
/*     */   static native void nglBindProgramARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteProgramsARB(IntBuffer programs) {
/* 125 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 126 */     long function_pointer = caps.glDeleteProgramsARB;
/* 127 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 128 */     BufferChecks.checkDirect(programs);
/* 129 */     nglDeleteProgramsARB(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteProgramsARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteProgramsARB(int program) {
/* 135 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 136 */     long function_pointer = caps.glDeleteProgramsARB;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     nglDeleteProgramsARB(1, APIUtil.getInt(caps, program), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenProgramsARB(IntBuffer programs) {
/* 142 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 143 */     long function_pointer = caps.glGenProgramsARB;
/* 144 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 145 */     BufferChecks.checkDirect(programs);
/* 146 */     nglGenProgramsARB(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenProgramsARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenProgramsARB() {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glGenProgramsARB;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     IntBuffer programs = APIUtil.getBufferInt(caps);
/* 156 */     nglGenProgramsARB(1, MemoryUtil.getAddress(programs), function_pointer);
/* 157 */     return programs.get(0);
/*     */   }
/*     */ 
/*     */   public static void glProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w) {
/* 161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 162 */     long function_pointer = caps.glProgramEnvParameter4fARB;
/* 163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 164 */     nglProgramEnvParameter4fARB(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameter4fARB(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glProgramEnvParameter4dARB(int target, int index, double x, double y, double z, double w) {
/* 169 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 170 */     long function_pointer = caps.glProgramEnvParameter4dARB;
/* 171 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 172 */     nglProgramEnvParameter4dARB(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameter4dARB(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glProgramEnvParameter4ARB(int target, int index, FloatBuffer params) {
/* 177 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 178 */     long function_pointer = caps.glProgramEnvParameter4fvARB;
/* 179 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 180 */     BufferChecks.checkBuffer(params, 4);
/* 181 */     nglProgramEnvParameter4fvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameter4fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramEnvParameter4ARB(int target, int index, DoubleBuffer params) {
/* 186 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 187 */     long function_pointer = caps.glProgramEnvParameter4dvARB;
/* 188 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 189 */     BufferChecks.checkBuffer(params, 4);
/* 190 */     nglProgramEnvParameter4dvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameter4dvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w) {
/* 195 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 196 */     long function_pointer = caps.glProgramLocalParameter4fARB;
/* 197 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 198 */     nglProgramLocalParameter4fARB(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameter4fARB(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glProgramLocalParameter4dARB(int target, int index, double x, double y, double z, double w) {
/* 203 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 204 */     long function_pointer = caps.glProgramLocalParameter4dARB;
/* 205 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 206 */     nglProgramLocalParameter4dARB(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameter4dARB(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glProgramLocalParameter4ARB(int target, int index, FloatBuffer params) {
/* 211 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 212 */     long function_pointer = caps.glProgramLocalParameter4fvARB;
/* 213 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 214 */     BufferChecks.checkBuffer(params, 4);
/* 215 */     nglProgramLocalParameter4fvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameter4fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramLocalParameter4ARB(int target, int index, DoubleBuffer params) {
/* 220 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 221 */     long function_pointer = caps.glProgramLocalParameter4dvARB;
/* 222 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 223 */     BufferChecks.checkBuffer(params, 4);
/* 224 */     nglProgramLocalParameter4dvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameter4dvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramEnvParameterARB(int target, int index, FloatBuffer params) {
/* 229 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 230 */     long function_pointer = caps.glGetProgramEnvParameterfvARB;
/* 231 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 232 */     BufferChecks.checkBuffer(params, 4);
/* 233 */     nglGetProgramEnvParameterfvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramEnvParameterfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramEnvParameterARB(int target, int index, DoubleBuffer params) {
/* 238 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 239 */     long function_pointer = caps.glGetProgramEnvParameterdvARB;
/* 240 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 241 */     BufferChecks.checkBuffer(params, 4);
/* 242 */     nglGetProgramEnvParameterdvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramEnvParameterdvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramLocalParameterARB(int target, int index, FloatBuffer params) {
/* 247 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 248 */     long function_pointer = caps.glGetProgramLocalParameterfvARB;
/* 249 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 250 */     BufferChecks.checkBuffer(params, 4);
/* 251 */     nglGetProgramLocalParameterfvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramLocalParameterfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramLocalParameterARB(int target, int index, DoubleBuffer params) {
/* 256 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 257 */     long function_pointer = caps.glGetProgramLocalParameterdvARB;
/* 258 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 259 */     BufferChecks.checkBuffer(params, 4);
/* 260 */     nglGetProgramLocalParameterdvARB(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramLocalParameterdvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramARB(int target, int parameterName, IntBuffer params) {
/* 265 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 266 */     long function_pointer = caps.glGetProgramivARB;
/* 267 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 268 */     BufferChecks.checkBuffer(params, 4);
/* 269 */     nglGetProgramivARB(target, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetProgramivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetProgramARB(int target, int parameterName)
/*     */   {
/* 280 */     return glGetProgramiARB(target, parameterName);
/*     */   }
/*     */ 
/*     */   public static int glGetProgramiARB(int target, int parameterName)
/*     */   {
/* 285 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 286 */     long function_pointer = caps.glGetProgramivARB;
/* 287 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 288 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 289 */     nglGetProgramivARB(target, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 290 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramStringARB(int target, int parameterName, ByteBuffer paramString) {
/* 294 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 295 */     long function_pointer = caps.glGetProgramStringARB;
/* 296 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 297 */     BufferChecks.checkDirect(paramString);
/* 298 */     nglGetProgramStringARB(target, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetProgramStringARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static String glGetProgramStringARB(int target, int parameterName) {
/* 304 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 305 */     long function_pointer = caps.glGetProgramStringARB;
/* 306 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 307 */     int programLength = glGetProgramiARB(target, 34343);
/* 308 */     ByteBuffer paramString = APIUtil.getBufferByte(caps, programLength);
/* 309 */     nglGetProgramStringARB(target, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/* 310 */     paramString.limit(programLength);
/* 311 */     return APIUtil.getString(caps, paramString);
/*     */   }
/*     */ 
/*     */   public static boolean glIsProgramARB(int program) {
/* 315 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 316 */     long function_pointer = caps.glIsProgramARB;
/* 317 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 318 */     boolean __result = nglIsProgramARB(program, function_pointer);
/* 319 */     return __result;
/*     */   }
/*     */ 
/*     */   static native boolean nglIsProgramARB(int paramInt, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBProgram
 * JD-Core Version:    0.6.2
 */