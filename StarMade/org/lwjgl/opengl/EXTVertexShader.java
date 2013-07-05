/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class EXTVertexShader
/*     */ {
/*     */   public static final int GL_VERTEX_SHADER_EXT = 34688;
/*     */   public static final int GL_VERTEX_SHADER_BINDING_EXT = 34689;
/*     */   public static final int GL_OP_INDEX_EXT = 34690;
/*     */   public static final int GL_OP_NEGATE_EXT = 34691;
/*     */   public static final int GL_OP_DOT3_EXT = 34692;
/*     */   public static final int GL_OP_DOT4_EXT = 34693;
/*     */   public static final int GL_OP_MUL_EXT = 34694;
/*     */   public static final int GL_OP_ADD_EXT = 34695;
/*     */   public static final int GL_OP_MADD_EXT = 34696;
/*     */   public static final int GL_OP_FRAC_EXT = 34697;
/*     */   public static final int GL_OP_MAX_EXT = 34698;
/*     */   public static final int GL_OP_MIN_EXT = 34699;
/*     */   public static final int GL_OP_SET_GE_EXT = 34700;
/*     */   public static final int GL_OP_SET_LT_EXT = 34701;
/*     */   public static final int GL_OP_CLAMP_EXT = 34702;
/*     */   public static final int GL_OP_FLOOR_EXT = 34703;
/*     */   public static final int GL_OP_ROUND_EXT = 34704;
/*     */   public static final int GL_OP_EXP_BASE_2_EXT = 34705;
/*     */   public static final int GL_OP_LOG_BASE_2_EXT = 34706;
/*     */   public static final int GL_OP_POWER_EXT = 34707;
/*     */   public static final int GL_OP_RECIP_EXT = 34708;
/*     */   public static final int GL_OP_RECIP_SQRT_EXT = 34709;
/*     */   public static final int GL_OP_SUB_EXT = 34710;
/*     */   public static final int GL_OP_CROSS_PRODUCT_EXT = 34711;
/*     */   public static final int GL_OP_MULTIPLY_MATRIX_EXT = 34712;
/*     */   public static final int GL_OP_MOV_EXT = 34713;
/*     */   public static final int GL_OUTPUT_VERTEX_EXT = 34714;
/*     */   public static final int GL_OUTPUT_COLOR0_EXT = 34715;
/*     */   public static final int GL_OUTPUT_COLOR1_EXT = 34716;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD0_EXT = 34717;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD1_EXT = 34718;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD2_EXT = 34719;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD3_EXT = 34720;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD4_EXT = 34721;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD5_EXT = 34722;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD6_EXT = 34723;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD7_EXT = 34724;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD8_EXT = 34725;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD9_EXT = 34726;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD10_EXT = 34727;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD11_EXT = 34728;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD12_EXT = 34729;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD13_EXT = 34730;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD14_EXT = 34731;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD15_EXT = 34732;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD16_EXT = 34733;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD17_EXT = 34734;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD18_EXT = 34735;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD19_EXT = 34736;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD20_EXT = 34737;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD21_EXT = 34738;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD22_EXT = 34739;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD23_EXT = 34740;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD24_EXT = 34741;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD25_EXT = 34742;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD26_EXT = 34743;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD27_EXT = 34744;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD28_EXT = 34745;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD29_EXT = 34746;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD30_EXT = 34747;
/*     */   public static final int GL_OUTPUT_TEXTURE_COORD31_EXT = 34748;
/*     */   public static final int GL_OUTPUT_FOG_EXT = 34749;
/*     */   public static final int GL_SCALAR_EXT = 34750;
/*     */   public static final int GL_VECTOR_EXT = 34751;
/*     */   public static final int GL_MATRIX_EXT = 34752;
/*     */   public static final int GL_VARIANT_EXT = 34753;
/*     */   public static final int GL_INVARIANT_EXT = 34754;
/*     */   public static final int GL_LOCAL_CONSTANT_EXT = 34755;
/*     */   public static final int GL_LOCAL_EXT = 34756;
/*     */   public static final int GL_MAX_VERTEX_SHADER_INSTRUCTIONS_EXT = 34757;
/*     */   public static final int GL_MAX_VERTEX_SHADER_VARIANTS_EXT = 34758;
/*     */   public static final int GL_MAX_VERTEX_SHADER_INVARIANTS_EXT = 34759;
/*     */   public static final int GL_MAX_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 34760;
/*     */   public static final int GL_MAX_VERTEX_SHADER_LOCALS_EXT = 34761;
/*     */   public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INSTRUCTIONS_EXT = 34762;
/*     */   public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_VARIANTS_EXT = 34763;
/*     */   public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INVARIANTS_EXT = 34764;
/*     */   public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 34765;
/*     */   public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCALS_EXT = 34766;
/*     */   public static final int GL_VERTEX_SHADER_INSTRUCTIONS_EXT = 34767;
/*     */   public static final int GL_VERTEX_SHADER_VARIANTS_EXT = 34768;
/*     */   public static final int GL_VERTEX_SHADER_INVARIANTS_EXT = 34769;
/*     */   public static final int GL_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 34770;
/*     */   public static final int GL_VERTEX_SHADER_LOCALS_EXT = 34771;
/*     */   public static final int GL_VERTEX_SHADER_OPTIMIZED_EXT = 34772;
/*     */   public static final int GL_X_EXT = 34773;
/*     */   public static final int GL_Y_EXT = 34774;
/*     */   public static final int GL_Z_EXT = 34775;
/*     */   public static final int GL_W_EXT = 34776;
/*     */   public static final int GL_NEGATIVE_X_EXT = 34777;
/*     */   public static final int GL_NEGATIVE_Y_EXT = 34778;
/*     */   public static final int GL_NEGATIVE_Z_EXT = 34779;
/*     */   public static final int GL_NEGATIVE_W_EXT = 34780;
/*     */   public static final int GL_ZERO_EXT = 34781;
/*     */   public static final int GL_ONE_EXT = 34782;
/*     */   public static final int GL_NEGATIVE_ONE_EXT = 34783;
/*     */   public static final int GL_NORMALIZED_RANGE_EXT = 34784;
/*     */   public static final int GL_FULL_RANGE_EXT = 34785;
/*     */   public static final int GL_CURRENT_VERTEX_EXT = 34786;
/*     */   public static final int GL_MVP_MATRIX_EXT = 34787;
/*     */   public static final int GL_VARIANT_VALUE_EXT = 34788;
/*     */   public static final int GL_VARIANT_DATATYPE_EXT = 34789;
/*     */   public static final int GL_VARIANT_ARRAY_STRIDE_EXT = 34790;
/*     */   public static final int GL_VARIANT_ARRAY_TYPE_EXT = 34791;
/*     */   public static final int GL_VARIANT_ARRAY_EXT = 34792;
/*     */   public static final int GL_VARIANT_ARRAY_POINTER_EXT = 34793;
/*     */   public static final int GL_INVARIANT_VALUE_EXT = 34794;
/*     */   public static final int GL_INVARIANT_DATATYPE_EXT = 34795;
/*     */   public static final int GL_LOCAL_CONSTANT_VALUE_EXT = 34796;
/*     */   public static final int GL_LOCAL_CONSTANT_DATATYPE_EXT = 34797;
/*     */ 
/*     */   public static void glBeginVertexShaderEXT()
/*     */   {
/* 124 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 125 */     long function_pointer = caps.glBeginVertexShaderEXT;
/* 126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 127 */     nglBeginVertexShaderEXT(function_pointer);
/*     */   }
/*     */   static native void nglBeginVertexShaderEXT(long paramLong);
/*     */ 
/*     */   public static void glEndVertexShaderEXT() {
/* 132 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 133 */     long function_pointer = caps.glEndVertexShaderEXT;
/* 134 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 135 */     nglEndVertexShaderEXT(function_pointer);
/*     */   }
/*     */   static native void nglEndVertexShaderEXT(long paramLong);
/*     */ 
/*     */   public static void glBindVertexShaderEXT(int id) {
/* 140 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 141 */     long function_pointer = caps.glBindVertexShaderEXT;
/* 142 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 143 */     nglBindVertexShaderEXT(id, function_pointer);
/*     */   }
/*     */   static native void nglBindVertexShaderEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static int glGenVertexShadersEXT(int range) {
/* 148 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 149 */     long function_pointer = caps.glGenVertexShadersEXT;
/* 150 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 151 */     int __result = nglGenVertexShadersEXT(range, function_pointer);
/* 152 */     return __result;
/*     */   }
/*     */   static native int nglGenVertexShadersEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glDeleteVertexShaderEXT(int id) {
/* 157 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 158 */     long function_pointer = caps.glDeleteVertexShaderEXT;
/* 159 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 160 */     nglDeleteVertexShaderEXT(id, function_pointer);
/*     */   }
/*     */   static native void nglDeleteVertexShaderEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glShaderOp1EXT(int op, int res, int arg1) {
/* 165 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 166 */     long function_pointer = caps.glShaderOp1EXT;
/* 167 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 168 */     nglShaderOp1EXT(op, res, arg1, function_pointer);
/*     */   }
/*     */   static native void nglShaderOp1EXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glShaderOp2EXT(int op, int res, int arg1, int arg2) {
/* 173 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 174 */     long function_pointer = caps.glShaderOp2EXT;
/* 175 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 176 */     nglShaderOp2EXT(op, res, arg1, arg2, function_pointer);
/*     */   }
/*     */   static native void nglShaderOp2EXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3) {
/* 181 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 182 */     long function_pointer = caps.glShaderOp3EXT;
/* 183 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 184 */     nglShaderOp3EXT(op, res, arg1, arg2, arg3, function_pointer);
/*     */   }
/*     */   static native void nglShaderOp3EXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW) {
/* 189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 190 */     long function_pointer = caps.glSwizzleEXT;
/* 191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 192 */     nglSwizzleEXT(res, in, outX, outY, outZ, outW, function_pointer);
/*     */   }
/*     */   static native void nglSwizzleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW) {
/* 197 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 198 */     long function_pointer = caps.glWriteMaskEXT;
/* 199 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 200 */     nglWriteMaskEXT(res, in, outX, outY, outZ, outW, function_pointer);
/*     */   }
/*     */   static native void nglWriteMaskEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glInsertComponentEXT(int res, int src, int num) {
/* 205 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 206 */     long function_pointer = caps.glInsertComponentEXT;
/* 207 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 208 */     nglInsertComponentEXT(res, src, num, function_pointer);
/*     */   }
/*     */   static native void nglInsertComponentEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glExtractComponentEXT(int res, int src, int num) {
/* 213 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 214 */     long function_pointer = caps.glExtractComponentEXT;
/* 215 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 216 */     nglExtractComponentEXT(res, src, num, function_pointer);
/*     */   }
/*     */   static native void nglExtractComponentEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static int glGenSymbolsEXT(int dataType, int storageType, int range, int components) {
/* 221 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 222 */     long function_pointer = caps.glGenSymbolsEXT;
/* 223 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 224 */     int __result = nglGenSymbolsEXT(dataType, storageType, range, components, function_pointer);
/* 225 */     return __result;
/*     */   }
/*     */   static native int nglGenSymbolsEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glSetInvariantEXT(int id, DoubleBuffer pAddr) {
/* 230 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 231 */     long function_pointer = caps.glSetInvariantEXT;
/* 232 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 233 */     BufferChecks.checkBuffer(pAddr, 4);
/* 234 */     nglSetInvariantEXT(id, 5130, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetInvariantEXT(int id, FloatBuffer pAddr) {
/* 237 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 238 */     long function_pointer = caps.glSetInvariantEXT;
/* 239 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 240 */     BufferChecks.checkBuffer(pAddr, 4);
/* 241 */     nglSetInvariantEXT(id, 5126, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
/* 244 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 245 */     long function_pointer = caps.glSetInvariantEXT;
/* 246 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 247 */     BufferChecks.checkBuffer(pAddr, 4);
/* 248 */     nglSetInvariantEXT(id, unsigned ? 5121 : 5120, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr) {
/* 251 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 252 */     long function_pointer = caps.glSetInvariantEXT;
/* 253 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 254 */     BufferChecks.checkBuffer(pAddr, 4);
/* 255 */     nglSetInvariantEXT(id, unsigned ? 5125 : 5124, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
/* 258 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 259 */     long function_pointer = caps.glSetInvariantEXT;
/* 260 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 261 */     BufferChecks.checkBuffer(pAddr, 4);
/* 262 */     nglSetInvariantEXT(id, unsigned ? 5123 : 5122, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglSetInvariantEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSetLocalConstantEXT(int id, DoubleBuffer pAddr) {
/* 267 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 268 */     long function_pointer = caps.glSetLocalConstantEXT;
/* 269 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 270 */     BufferChecks.checkBuffer(pAddr, 4);
/* 271 */     nglSetLocalConstantEXT(id, 5130, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetLocalConstantEXT(int id, FloatBuffer pAddr) {
/* 274 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 275 */     long function_pointer = caps.glSetLocalConstantEXT;
/* 276 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 277 */     BufferChecks.checkBuffer(pAddr, 4);
/* 278 */     nglSetLocalConstantEXT(id, 5126, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
/* 281 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 282 */     long function_pointer = caps.glSetLocalConstantEXT;
/* 283 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 284 */     BufferChecks.checkBuffer(pAddr, 4);
/* 285 */     nglSetLocalConstantEXT(id, unsigned ? 5121 : 5120, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr) {
/* 288 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 289 */     long function_pointer = caps.glSetLocalConstantEXT;
/* 290 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 291 */     BufferChecks.checkBuffer(pAddr, 4);
/* 292 */     nglSetLocalConstantEXT(id, unsigned ? 5125 : 5124, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
/* 295 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 296 */     long function_pointer = caps.glSetLocalConstantEXT;
/* 297 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 298 */     BufferChecks.checkBuffer(pAddr, 4);
/* 299 */     nglSetLocalConstantEXT(id, unsigned ? 5123 : 5122, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglSetLocalConstantEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantEXT(int id, ByteBuffer pAddr) {
/* 304 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 305 */     long function_pointer = caps.glVariantbvEXT;
/* 306 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 307 */     BufferChecks.checkBuffer(pAddr, 4);
/* 308 */     nglVariantbvEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantbvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantEXT(int id, ShortBuffer pAddr) {
/* 313 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 314 */     long function_pointer = caps.glVariantsvEXT;
/* 315 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 316 */     BufferChecks.checkBuffer(pAddr, 4);
/* 317 */     nglVariantsvEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantsvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantEXT(int id, IntBuffer pAddr) {
/* 322 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 323 */     long function_pointer = caps.glVariantivEXT;
/* 324 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 325 */     BufferChecks.checkBuffer(pAddr, 4);
/* 326 */     nglVariantivEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantEXT(int id, FloatBuffer pAddr) {
/* 331 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 332 */     long function_pointer = caps.glVariantfvEXT;
/* 333 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 334 */     BufferChecks.checkBuffer(pAddr, 4);
/* 335 */     nglVariantfvEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantfvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantEXT(int id, DoubleBuffer pAddr) {
/* 340 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 341 */     long function_pointer = caps.glVariantdvEXT;
/* 342 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 343 */     BufferChecks.checkBuffer(pAddr, 4);
/* 344 */     nglVariantdvEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantdvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantuEXT(int id, ByteBuffer pAddr) {
/* 349 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 350 */     long function_pointer = caps.glVariantubvEXT;
/* 351 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 352 */     BufferChecks.checkBuffer(pAddr, 4);
/* 353 */     nglVariantubvEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantubvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantuEXT(int id, ShortBuffer pAddr) {
/* 358 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 359 */     long function_pointer = caps.glVariantusvEXT;
/* 360 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 361 */     BufferChecks.checkBuffer(pAddr, 4);
/* 362 */     nglVariantusvEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantusvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantuEXT(int id, IntBuffer pAddr) {
/* 367 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 368 */     long function_pointer = caps.glVariantuivEXT;
/* 369 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 370 */     BufferChecks.checkBuffer(pAddr, 4);
/* 371 */     nglVariantuivEXT(id, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantuivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVariantPointerEXT(int id, int stride, DoubleBuffer pAddr) {
/* 376 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 377 */     long function_pointer = caps.glVariantPointerEXT;
/* 378 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 379 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 380 */     BufferChecks.checkDirect(pAddr);
/* 381 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 382 */     nglVariantPointerEXT(id, 5130, stride, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) {
/* 385 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 386 */     long function_pointer = caps.glVariantPointerEXT;
/* 387 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 388 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 389 */     BufferChecks.checkDirect(pAddr);
/* 390 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 391 */     nglVariantPointerEXT(id, 5126, stride, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr) {
/* 394 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 395 */     long function_pointer = caps.glVariantPointerEXT;
/* 396 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 397 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 398 */     BufferChecks.checkDirect(pAddr);
/* 399 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 400 */     nglVariantPointerEXT(id, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr) {
/* 403 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 404 */     long function_pointer = caps.glVariantPointerEXT;
/* 405 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 406 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 407 */     BufferChecks.checkDirect(pAddr);
/* 408 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 409 */     nglVariantPointerEXT(id, unsigned ? 5125 : 5124, stride, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) {
/* 412 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 413 */     long function_pointer = caps.glVariantPointerEXT;
/* 414 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 415 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 416 */     BufferChecks.checkDirect(pAddr);
/* 417 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 418 */     nglVariantPointerEXT(id, unsigned ? 5123 : 5122, stride, MemoryUtil.getAddress(pAddr), function_pointer);
/*     */   }
/*     */   static native void nglVariantPointerEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/* 422 */   public static void glVariantPointerEXT(int id, int type, int stride, long pAddr_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 423 */     long function_pointer = caps.glVariantPointerEXT;
/* 424 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 425 */     GLChecks.ensureArrayVBOenabled(caps);
/* 426 */     nglVariantPointerEXTBO(id, type, stride, pAddr_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglVariantPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glEnableVariantClientStateEXT(int id) {
/* 431 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 432 */     long function_pointer = caps.glEnableVariantClientStateEXT;
/* 433 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 434 */     nglEnableVariantClientStateEXT(id, function_pointer);
/*     */   }
/*     */   static native void nglEnableVariantClientStateEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glDisableVariantClientStateEXT(int id) {
/* 439 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 440 */     long function_pointer = caps.glDisableVariantClientStateEXT;
/* 441 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 442 */     nglDisableVariantClientStateEXT(id, function_pointer);
/*     */   }
/*     */   static native void nglDisableVariantClientStateEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static int glBindLightParameterEXT(int light, int value) {
/* 447 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 448 */     long function_pointer = caps.glBindLightParameterEXT;
/* 449 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 450 */     int __result = nglBindLightParameterEXT(light, value, function_pointer);
/* 451 */     return __result;
/*     */   }
/*     */   static native int nglBindLightParameterEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static int glBindMaterialParameterEXT(int face, int value) {
/* 456 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 457 */     long function_pointer = caps.glBindMaterialParameterEXT;
/* 458 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 459 */     int __result = nglBindMaterialParameterEXT(face, value, function_pointer);
/* 460 */     return __result;
/*     */   }
/*     */   static native int nglBindMaterialParameterEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static int glBindTexGenParameterEXT(int unit, int coord, int value) {
/* 465 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 466 */     long function_pointer = caps.glBindTexGenParameterEXT;
/* 467 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 468 */     int __result = nglBindTexGenParameterEXT(unit, coord, value, function_pointer);
/* 469 */     return __result;
/*     */   }
/*     */   static native int nglBindTexGenParameterEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static int glBindTextureUnitParameterEXT(int unit, int value) {
/* 474 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 475 */     long function_pointer = caps.glBindTextureUnitParameterEXT;
/* 476 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 477 */     int __result = nglBindTextureUnitParameterEXT(unit, value, function_pointer);
/* 478 */     return __result;
/*     */   }
/*     */   static native int nglBindTextureUnitParameterEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static int glBindParameterEXT(int value) {
/* 483 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 484 */     long function_pointer = caps.glBindParameterEXT;
/* 485 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 486 */     int __result = nglBindParameterEXT(value, function_pointer);
/* 487 */     return __result;
/*     */   }
/*     */   static native int nglBindParameterEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glIsVariantEnabledEXT(int id, int cap) {
/* 492 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 493 */     long function_pointer = caps.glIsVariantEnabledEXT;
/* 494 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 495 */     boolean __result = nglIsVariantEnabledEXT(id, cap, function_pointer);
/* 496 */     return __result;
/*     */   }
/*     */   static native boolean nglIsVariantEnabledEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData) {
/* 501 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 502 */     long function_pointer = caps.glGetVariantBooleanvEXT;
/* 503 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 504 */     BufferChecks.checkBuffer(pbData, 4);
/* 505 */     nglGetVariantBooleanvEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetVariantBooleanvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVariantIntegerEXT(int id, int value, IntBuffer pbData) {
/* 510 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 511 */     long function_pointer = caps.glGetVariantIntegervEXT;
/* 512 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 513 */     BufferChecks.checkBuffer(pbData, 4);
/* 514 */     nglGetVariantIntegervEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetVariantIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVariantFloatEXT(int id, int value, FloatBuffer pbData) {
/* 519 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 520 */     long function_pointer = caps.glGetVariantFloatvEXT;
/* 521 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 522 */     BufferChecks.checkBuffer(pbData, 4);
/* 523 */     nglGetVariantFloatvEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetVariantFloatvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static ByteBuffer glGetVariantPointerEXT(int id, int value, long result_size) {
/* 528 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 529 */     long function_pointer = caps.glGetVariantPointervEXT;
/* 530 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 531 */     ByteBuffer __result = nglGetVariantPointervEXT(id, value, result_size, function_pointer);
/* 532 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */   static native ByteBuffer nglGetVariantPointervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData) {
/* 537 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 538 */     long function_pointer = caps.glGetInvariantBooleanvEXT;
/* 539 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 540 */     BufferChecks.checkBuffer(pbData, 4);
/* 541 */     nglGetInvariantBooleanvEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetInvariantBooleanvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetInvariantIntegerEXT(int id, int value, IntBuffer pbData) {
/* 546 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 547 */     long function_pointer = caps.glGetInvariantIntegervEXT;
/* 548 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 549 */     BufferChecks.checkBuffer(pbData, 4);
/* 550 */     nglGetInvariantIntegervEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetInvariantIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetInvariantFloatEXT(int id, int value, FloatBuffer pbData) {
/* 555 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 556 */     long function_pointer = caps.glGetInvariantFloatvEXT;
/* 557 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 558 */     BufferChecks.checkBuffer(pbData, 4);
/* 559 */     nglGetInvariantFloatvEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetInvariantFloatvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData) {
/* 564 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 565 */     long function_pointer = caps.glGetLocalConstantBooleanvEXT;
/* 566 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 567 */     BufferChecks.checkBuffer(pbData, 4);
/* 568 */     nglGetLocalConstantBooleanvEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetLocalConstantBooleanvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer pbData) {
/* 573 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 574 */     long function_pointer = caps.glGetLocalConstantIntegervEXT;
/* 575 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 576 */     BufferChecks.checkBuffer(pbData, 4);
/* 577 */     nglGetLocalConstantIntegervEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */   static native void nglGetLocalConstantIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pbData) {
/* 582 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 583 */     long function_pointer = caps.glGetLocalConstantFloatvEXT;
/* 584 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 585 */     BufferChecks.checkBuffer(pbData, 4);
/* 586 */     nglGetLocalConstantFloatvEXT(id, value, MemoryUtil.getAddress(pbData), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetLocalConstantFloatvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTVertexShader
 * JD-Core Version:    0.6.2
 */