/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.DoubleBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBGpuShaderFp64
/*     */ {
/*     */   public static final int GL_DOUBLE = 5130;
/*     */   public static final int GL_DOUBLE_VEC2 = 36860;
/*     */   public static final int GL_DOUBLE_VEC3 = 36861;
/*     */   public static final int GL_DOUBLE_VEC4 = 36862;
/*     */   public static final int GL_DOUBLE_MAT2 = 36678;
/*     */   public static final int GL_DOUBLE_MAT3 = 36679;
/*     */   public static final int GL_DOUBLE_MAT4 = 36680;
/*     */   public static final int GL_DOUBLE_MAT2x3 = 36681;
/*     */   public static final int GL_DOUBLE_MAT2x4 = 36682;
/*     */   public static final int GL_DOUBLE_MAT3x2 = 36683;
/*     */   public static final int GL_DOUBLE_MAT3x4 = 36684;
/*     */   public static final int GL_DOUBLE_MAT4x2 = 36685;
/*     */   public static final int GL_DOUBLE_MAT4x3 = 36686;
/*     */ 
/*     */   public static void glUniform1d(int location, double x)
/*     */   {
/*  31 */     GL40.glUniform1d(location, x);
/*     */   }
/*     */ 
/*     */   public static void glUniform2d(int location, double x, double y) {
/*  35 */     GL40.glUniform2d(location, x, y);
/*     */   }
/*     */ 
/*     */   public static void glUniform3d(int location, double x, double y, double z) {
/*  39 */     GL40.glUniform3d(location, x, y, z);
/*     */   }
/*     */ 
/*     */   public static void glUniform4d(int location, double x, double y, double z, double w) {
/*  43 */     GL40.glUniform4d(location, x, y, z, w);
/*     */   }
/*     */ 
/*     */   public static void glUniform1(int location, DoubleBuffer value) {
/*  47 */     GL40.glUniform1(location, value);
/*     */   }
/*     */ 
/*     */   public static void glUniform2(int location, DoubleBuffer value) {
/*  51 */     GL40.glUniform2(location, value);
/*     */   }
/*     */ 
/*     */   public static void glUniform3(int location, DoubleBuffer value) {
/*  55 */     GL40.glUniform3(location, value);
/*     */   }
/*     */ 
/*     */   public static void glUniform4(int location, DoubleBuffer value) {
/*  59 */     GL40.glUniform4(location, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix2(int location, boolean transpose, DoubleBuffer value) {
/*  63 */     GL40.glUniformMatrix2(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix3(int location, boolean transpose, DoubleBuffer value) {
/*  67 */     GL40.glUniformMatrix3(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix4(int location, boolean transpose, DoubleBuffer value) {
/*  71 */     GL40.glUniformMatrix4(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix2x3(int location, boolean transpose, DoubleBuffer value) {
/*  75 */     GL40.glUniformMatrix2x3(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix2x4(int location, boolean transpose, DoubleBuffer value) {
/*  79 */     GL40.glUniformMatrix2x4(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix3x2(int location, boolean transpose, DoubleBuffer value) {
/*  83 */     GL40.glUniformMatrix3x2(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix3x4(int location, boolean transpose, DoubleBuffer value) {
/*  87 */     GL40.glUniformMatrix3x4(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix4x2(int location, boolean transpose, DoubleBuffer value) {
/*  91 */     GL40.glUniformMatrix4x2(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glUniformMatrix4x3(int location, boolean transpose, DoubleBuffer value) {
/*  95 */     GL40.glUniformMatrix4x3(location, transpose, value);
/*     */   }
/*     */ 
/*     */   public static void glGetUniform(int program, int location, DoubleBuffer params) {
/*  99 */     GL40.glGetUniform(program, location, params);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniform1dEXT(int program, int location, double x) {
/* 103 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 104 */     long function_pointer = caps.glProgramUniform1dEXT;
/* 105 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 106 */     nglProgramUniform1dEXT(program, location, x, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform1dEXT(int paramInt1, int paramInt2, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glProgramUniform2dEXT(int program, int location, double x, double y) {
/* 111 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 112 */     long function_pointer = caps.glProgramUniform2dEXT;
/* 113 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 114 */     nglProgramUniform2dEXT(program, location, x, y, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform2dEXT(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glProgramUniform3dEXT(int program, int location, double x, double y, double z) {
/* 119 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 120 */     long function_pointer = caps.glProgramUniform3dEXT;
/* 121 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 122 */     nglProgramUniform3dEXT(program, location, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform3dEXT(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glProgramUniform4dEXT(int program, int location, double x, double y, double z, double w) {
/* 127 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 128 */     long function_pointer = caps.glProgramUniform4dEXT;
/* 129 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 130 */     nglProgramUniform4dEXT(program, location, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform4dEXT(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glProgramUniform1EXT(int program, int location, DoubleBuffer value) {
/* 135 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 136 */     long function_pointer = caps.glProgramUniform1dvEXT;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     BufferChecks.checkDirect(value);
/* 139 */     nglProgramUniform1dvEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform1dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform2EXT(int program, int location, DoubleBuffer value) {
/* 144 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 145 */     long function_pointer = caps.glProgramUniform2dvEXT;
/* 146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 147 */     BufferChecks.checkDirect(value);
/* 148 */     nglProgramUniform2dvEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform2dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform3EXT(int program, int location, DoubleBuffer value) {
/* 153 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 154 */     long function_pointer = caps.glProgramUniform3dvEXT;
/* 155 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 156 */     BufferChecks.checkDirect(value);
/* 157 */     nglProgramUniform3dvEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform3dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform4EXT(int program, int location, DoubleBuffer value) {
/* 162 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 163 */     long function_pointer = caps.glProgramUniform4dvEXT;
/* 164 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 165 */     BufferChecks.checkDirect(value);
/* 166 */     nglProgramUniform4dvEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform4dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix2EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 171 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 172 */     long function_pointer = caps.glProgramUniformMatrix2dvEXT;
/* 173 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 174 */     BufferChecks.checkDirect(value);
/* 175 */     nglProgramUniformMatrix2dvEXT(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix2dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix3EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 180 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 181 */     long function_pointer = caps.glProgramUniformMatrix3dvEXT;
/* 182 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 183 */     BufferChecks.checkDirect(value);
/* 184 */     nglProgramUniformMatrix3dvEXT(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix3dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix4EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 190 */     long function_pointer = caps.glProgramUniformMatrix4dvEXT;
/* 191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 192 */     BufferChecks.checkDirect(value);
/* 193 */     nglProgramUniformMatrix4dvEXT(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix4dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix2x3EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 198 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 199 */     long function_pointer = caps.glProgramUniformMatrix2x3dvEXT;
/* 200 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 201 */     BufferChecks.checkDirect(value);
/* 202 */     nglProgramUniformMatrix2x3dvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix2x3dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix2x4EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 207 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 208 */     long function_pointer = caps.glProgramUniformMatrix2x4dvEXT;
/* 209 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 210 */     BufferChecks.checkDirect(value);
/* 211 */     nglProgramUniformMatrix2x4dvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix2x4dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix3x2EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 216 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 217 */     long function_pointer = caps.glProgramUniformMatrix3x2dvEXT;
/* 218 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 219 */     BufferChecks.checkDirect(value);
/* 220 */     nglProgramUniformMatrix3x2dvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix3x2dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix3x4EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 225 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 226 */     long function_pointer = caps.glProgramUniformMatrix3x4dvEXT;
/* 227 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 228 */     BufferChecks.checkDirect(value);
/* 229 */     nglProgramUniformMatrix3x4dvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix3x4dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix4x2EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 235 */     long function_pointer = caps.glProgramUniformMatrix4x2dvEXT;
/* 236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 237 */     BufferChecks.checkDirect(value);
/* 238 */     nglProgramUniformMatrix4x2dvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformMatrix4x2dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformMatrix4x3EXT(int program, int location, boolean transpose, DoubleBuffer value) {
/* 243 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 244 */     long function_pointer = caps.glProgramUniformMatrix4x3dvEXT;
/* 245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 246 */     BufferChecks.checkDirect(value);
/* 247 */     nglProgramUniformMatrix4x3dvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglProgramUniformMatrix4x3dvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBGpuShaderFp64
 * JD-Core Version:    0.6.2
 */