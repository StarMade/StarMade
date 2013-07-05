/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public class NVProgram
/*     */ {
/*     */   public static final int GL_PROGRAM_TARGET_NV = 34374;
/*     */   public static final int GL_PROGRAM_LENGTH_NV = 34343;
/*     */   public static final int GL_PROGRAM_RESIDENT_NV = 34375;
/*     */   public static final int GL_PROGRAM_STRING_NV = 34344;
/*     */   public static final int GL_PROGRAM_ERROR_POSITION_NV = 34379;
/*     */   public static final int GL_PROGRAM_ERROR_STRING_NV = 34932;
/*     */ 
/*     */   public static void glLoadProgramNV(int target, int programID, ByteBuffer string)
/*     */   {
/*  35 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  36 */     long function_pointer = caps.glLoadProgramNV;
/*  37 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  38 */     BufferChecks.checkDirect(string);
/*  39 */     nglLoadProgramNV(target, programID, string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglLoadProgramNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glLoadProgramNV(int target, int programID, CharSequence string) {
/*  45 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  46 */     long function_pointer = caps.glLoadProgramNV;
/*  47 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  48 */     nglLoadProgramNV(target, programID, string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glBindProgramNV(int target, int programID) {
/*  52 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  53 */     long function_pointer = caps.glBindProgramNV;
/*  54 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  55 */     nglBindProgramNV(target, programID, function_pointer);
/*     */   }
/*     */   static native void nglBindProgramNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteProgramsNV(IntBuffer programs) {
/*  60 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  61 */     long function_pointer = caps.glDeleteProgramsNV;
/*  62 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  63 */     BufferChecks.checkDirect(programs);
/*  64 */     nglDeleteProgramsNV(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteProgramsNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteProgramsNV(int program) {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glDeleteProgramsNV;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     nglDeleteProgramsNV(1, APIUtil.getInt(caps, program), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenProgramsNV(IntBuffer programs) {
/*  77 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  78 */     long function_pointer = caps.glGenProgramsNV;
/*  79 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  80 */     BufferChecks.checkDirect(programs);
/*  81 */     nglGenProgramsNV(programs.remaining(), MemoryUtil.getAddress(programs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenProgramsNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenProgramsNV() {
/*  87 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  88 */     long function_pointer = caps.glGenProgramsNV;
/*  89 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  90 */     IntBuffer programs = APIUtil.getBufferInt(caps);
/*  91 */     nglGenProgramsNV(1, MemoryUtil.getAddress(programs), function_pointer);
/*  92 */     return programs.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramNV(int programID, int parameterName, IntBuffer params) {
/*  96 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  97 */     long function_pointer = caps.glGetProgramivNV;
/*  98 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  99 */     BufferChecks.checkDirect(params);
/* 100 */     nglGetProgramivNV(programID, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetProgramivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetProgramNV(int programID, int parameterName)
/*     */   {
/* 111 */     return glGetProgramiNV(programID, parameterName);
/*     */   }
/*     */ 
/*     */   public static int glGetProgramiNV(int programID, int parameterName)
/*     */   {
/* 116 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 117 */     long function_pointer = caps.glGetProgramivNV;
/* 118 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 119 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 120 */     nglGetProgramivNV(programID, parameterName, MemoryUtil.getAddress(params), function_pointer);
/* 121 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramStringNV(int programID, int parameterName, ByteBuffer paramString) {
/* 125 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 126 */     long function_pointer = caps.glGetProgramStringNV;
/* 127 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 128 */     BufferChecks.checkDirect(paramString);
/* 129 */     nglGetProgramStringNV(programID, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetProgramStringNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static String glGetProgramStringNV(int programID, int parameterName) {
/* 135 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 136 */     long function_pointer = caps.glGetProgramStringNV;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     int programLength = glGetProgramiNV(programID, 34343);
/* 139 */     ByteBuffer paramString = APIUtil.getBufferByte(caps, programLength);
/* 140 */     nglGetProgramStringNV(programID, parameterName, MemoryUtil.getAddress(paramString), function_pointer);
/* 141 */     paramString.limit(programLength);
/* 142 */     return APIUtil.getString(caps, paramString);
/*     */   }
/*     */ 
/*     */   public static boolean glIsProgramNV(int programID) {
/* 146 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 147 */     long function_pointer = caps.glIsProgramNV;
/* 148 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 149 */     boolean __result = nglIsProgramNV(programID, function_pointer);
/* 150 */     return __result;
/*     */   }
/*     */   static native boolean nglIsProgramNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glAreProgramsResidentNV(IntBuffer programIDs, ByteBuffer programResidences) {
/* 155 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 156 */     long function_pointer = caps.glAreProgramsResidentNV;
/* 157 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 158 */     BufferChecks.checkDirect(programIDs);
/* 159 */     BufferChecks.checkBuffer(programResidences, programIDs.remaining());
/* 160 */     boolean __result = nglAreProgramsResidentNV(programIDs.remaining(), MemoryUtil.getAddress(programIDs), MemoryUtil.getAddress(programResidences), function_pointer);
/* 161 */     return __result;
/*     */   }
/*     */   static native boolean nglAreProgramsResidentNV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glRequestResidentProgramsNV(IntBuffer programIDs) {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glRequestResidentProgramsNV;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     BufferChecks.checkDirect(programIDs);
/* 170 */     nglRequestResidentProgramsNV(programIDs.remaining(), MemoryUtil.getAddress(programIDs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglRequestResidentProgramsNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glRequestResidentProgramsNV(int programID) {
/* 176 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 177 */     long function_pointer = caps.glRequestResidentProgramsNV;
/* 178 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 179 */     nglRequestResidentProgramsNV(1, APIUtil.getInt(caps, programID), function_pointer);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVProgram
 * JD-Core Version:    0.6.2
 */