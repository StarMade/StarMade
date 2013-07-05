/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVPathRendering
/*     */ {
/*     */   public static final int GL_CLOSE_PATH_NV = 0;
/*     */   public static final int GL_MOVE_TO_NV = 2;
/*     */   public static final int GL_RELATIVE_MOVE_TO_NV = 3;
/*     */   public static final int GL_LINE_TO_NV = 4;
/*     */   public static final int GL_RELATIVE_LINE_TO_NV = 5;
/*     */   public static final int GL_HORIZONTAL_LINE_TO_NV = 6;
/*     */   public static final int GL_RELATIVE_HORIZONTAL_LINE_TO_NV = 7;
/*     */   public static final int GL_VERTICAL_LINE_TO_NV = 8;
/*     */   public static final int GL_RELATIVE_VERTICAL_LINE_TO_NV = 9;
/*     */   public static final int GL_QUADRATIC_CURVE_TO_NV = 10;
/*     */   public static final int GL_RELATIVE_QUADRATIC_CURVE_TO_NV = 11;
/*     */   public static final int GL_CUBIC_CURVE_TO_NV = 12;
/*     */   public static final int GL_RELATIVE_CUBIC_CURVE_TO_NV = 13;
/*     */   public static final int GL_SMOOTH_QUADRATIC_CURVE_TO_NV = 14;
/*     */   public static final int GL_RELATIVE_SMOOTH_QUADRATIC_CURVE_TO_NV = 15;
/*     */   public static final int GL_SMOOTH_CUBIC_CURVE_TO_NV = 16;
/*     */   public static final int GL_RELATIVE_SMOOTH_CUBIC_CURVE_TO_NV = 17;
/*     */   public static final int GL_SMALL_CCW_ARC_TO_NV = 18;
/*     */   public static final int GL_RELATIVE_SMALL_CCW_ARC_TO_NV = 19;
/*     */   public static final int GL_SMALL_CW_ARC_TO_NV = 20;
/*     */   public static final int GL_RELATIVE_SMALL_CW_ARC_TO_NV = 21;
/*     */   public static final int GL_LARGE_CCW_ARC_TO_NV = 22;
/*     */   public static final int GL_RELATIVE_LARGE_CCW_ARC_TO_NV = 23;
/*     */   public static final int GL_LARGE_CW_ARC_TO_NV = 24;
/*     */   public static final int GL_RELATIVE_LARGE_CW_ARC_TO_NV = 25;
/*     */   public static final int GL_CIRCULAR_CCW_ARC_TO_NV = 248;
/*     */   public static final int GL_CIRCULAR_CW_ARC_TO_NV = 250;
/*     */   public static final int GL_CIRCULAR_TANGENT_ARC_TO_NV = 252;
/*     */   public static final int GL_ARC_TO_NV = 254;
/*     */   public static final int GL_RELATIVE_ARC_TO_NV = 255;
/*     */   public static final int GL_PATH_FORMAT_SVG_NV = 36976;
/*     */   public static final int GL_PATH_FORMAT_PS_NV = 36977;
/*     */   public static final int GL_STANDARD_FONT_NAME_NV = 36978;
/*     */   public static final int GL_SYSTEM_FONT_NAME_NV = 36979;
/*     */   public static final int GL_FILE_NAME_NV = 36980;
/*     */   public static final int GL_SKIP_MISSING_GLYPH_NV = 37033;
/*     */   public static final int GL_USE_MISSING_GLYPH_NV = 37034;
/*     */   public static final int GL_PATH_STROKE_WIDTH_NV = 36981;
/*     */   public static final int GL_PATH_INITIAL_END_CAP_NV = 36983;
/*     */   public static final int GL_PATH_TERMINAL_END_CAP_NV = 36984;
/*     */   public static final int GL_PATH_JOIN_STYLE_NV = 36985;
/*     */   public static final int GL_PATH_MITER_LIMIT_NV = 36986;
/*     */   public static final int GL_PATH_INITIAL_DASH_CAP_NV = 36988;
/*     */   public static final int GL_PATH_TERMINAL_DASH_CAP_NV = 36989;
/*     */   public static final int GL_PATH_DASH_OFFSET_NV = 36990;
/*     */   public static final int GL_PATH_CLIENT_LENGTH_NV = 36991;
/*     */   public static final int GL_PATH_DASH_OFFSET_RESET_NV = 37044;
/*     */   public static final int GL_PATH_FILL_MODE_NV = 36992;
/*     */   public static final int GL_PATH_FILL_MASK_NV = 36993;
/*     */   public static final int GL_PATH_FILL_COVER_MODE_NV = 36994;
/*     */   public static final int GL_PATH_STROKE_COVER_MODE_NV = 36995;
/*     */   public static final int GL_PATH_STROKE_MASK_NV = 36996;
/*     */   public static final int GL_PATH_END_CAPS_NV = 36982;
/*     */   public static final int GL_PATH_DASH_CAPS_NV = 36987;
/*     */   public static final int GL_COUNT_UP_NV = 37000;
/*     */   public static final int GL_COUNT_DOWN_NV = 37001;
/*     */   public static final int GL_PRIMARY_COLOR = 34167;
/*     */   public static final int GL_PRIMARY_COLOR_NV = 34092;
/*     */   public static final int GL_SECONDARY_COLOR_NV = 34093;
/*     */   public static final int GL_PATH_OBJECT_BOUNDING_BOX_NV = 37002;
/*     */   public static final int GL_CONVEX_HULL_NV = 37003;
/*     */   public static final int GL_BOUNDING_BOX_NV = 37005;
/*     */   public static final int GL_TRANSLATE_X_NV = 37006;
/*     */   public static final int GL_TRANSLATE_Y_NV = 37007;
/*     */   public static final int GL_TRANSLATE_2D_NV = 37008;
/*     */   public static final int GL_TRANSLATE_3D_NV = 37009;
/*     */   public static final int GL_AFFINE_2D_NV = 37010;
/*     */   public static final int GL_AFFINE_3D_NV = 37012;
/*     */   public static final int GL_TRANSPOSE_AFFINE_2D_NV = 37014;
/*     */   public static final int GL_TRANSPOSE_AFFINE_3D_NV = 37016;
/*     */   public static final int GL_UTF8_NV = 37018;
/*     */   public static final int GL_UTF16_NV = 37019;
/*     */   public static final int GL_BOUNDING_BOX_OF_BOUNDING_BOXES_NV = 37020;
/*     */   public static final int GL_PATH_COMMAND_COUNT_NV = 37021;
/*     */   public static final int GL_PATH_COORD_COUNT_NV = 37022;
/*     */   public static final int GL_PATH_DASH_ARRAY_COUNT_NV = 37023;
/*     */   public static final int GL_PATH_COMPUTED_LENGTH_NV = 37024;
/*     */   public static final int GL_PATH_FILL_BOUNDING_BOX_NV = 37025;
/*     */   public static final int GL_PATH_STROKE_BOUNDING_BOX_NV = 37026;
/*     */   public static final int GL_SQUARE_NV = 37027;
/*     */   public static final int GL_ROUND_NV = 37028;
/*     */   public static final int GL_TRIANGULAR_NV = 37029;
/*     */   public static final int GL_BEVEL_NV = 37030;
/*     */   public static final int GL_MITER_REVERT_NV = 37031;
/*     */   public static final int GL_MITER_TRUNCATE_NV = 37032;
/*     */   public static final int GL_MOVE_TO_RESETS_NV = 37045;
/*     */   public static final int GL_MOVE_TO_CONTINUES_NV = 37046;
/*     */   public static final int GL_BOLD_BIT_NV = 1;
/*     */   public static final int GL_ITALIC_BIT_NV = 2;
/*     */   public static final int GL_PATH_ERROR_POSITION_NV = 37035;
/*     */   public static final int GL_PATH_FOG_GEN_MODE_NV = 37036;
/*     */   public static final int GL_PATH_STENCIL_FUNC_NV = 37047;
/*     */   public static final int GL_PATH_STENCIL_REF_NV = 37048;
/*     */   public static final int GL_PATH_STENCIL_VALUE_MASK_NV = 37049;
/*     */   public static final int GL_PATH_STENCIL_DEPTH_OFFSET_FACTOR_NV = 37053;
/*     */   public static final int GL_PATH_STENCIL_DEPTH_OFFSET_UNITS_NV = 37054;
/*     */   public static final int GL_PATH_COVER_DEPTH_FUNC_NV = 37055;
/*     */   public static final int GL_GLYPH_WIDTH_BIT_NV = 1;
/*     */   public static final int GL_GLYPH_HEIGHT_BIT_NV = 2;
/*     */   public static final int GL_GLYPH_HORIZONTAL_BEARING_X_BIT_NV = 4;
/*     */   public static final int GL_GLYPH_HORIZONTAL_BEARING_Y_BIT_NV = 8;
/*     */   public static final int GL_GLYPH_HORIZONTAL_BEARING_ADVANCE_BIT_NV = 16;
/*     */   public static final int GL_GLYPH_VERTICAL_BEARING_X_BIT_NV = 32;
/*     */   public static final int GL_GLYPH_VERTICAL_BEARING_Y_BIT_NV = 64;
/*     */   public static final int GL_GLYPH_VERTICAL_BEARING_ADVANCE_BIT_NV = 128;
/*     */   public static final int GL_GLYPH_HAS_KERNING_NV = 256;
/*     */   public static final int GL_FONT_X_MIN_BOUNDS_NV = 65536;
/*     */   public static final int GL_FONT_Y_MIN_BOUNDS_NV = 131072;
/*     */   public static final int GL_FONT_X_MAX_BOUNDS_NV = 262144;
/*     */   public static final int GL_FONT_Y_MAX_BOUNDS_NV = 524288;
/*     */   public static final int GL_FONT_UNITS_PER_EM_NV = 1048576;
/*     */   public static final int GL_FONT_ASCENDER_NV = 2097152;
/*     */   public static final int GL_FONT_DESCENDER_NV = 4194304;
/*     */   public static final int GL_FONT_HEIGHT_NV = 8388608;
/*     */   public static final int GL_FONT_MAX_ADVANCE_WIDTH_NV = 16777216;
/*     */   public static final int GL_FONT_MAX_ADVANCE_HEIGHT_NV = 33554432;
/*     */   public static final int GL_FONT_UNDERLINE_POSITION_NV = 67108864;
/*     */   public static final int GL_FONT_UNDERLINE_THICKNESS_NV = 134217728;
/*     */   public static final int GL_FONT_HAS_KERNING_NV = 268435456;
/*     */   public static final int GL_ACCUM_ADJACENT_PAIRS_NV = 37037;
/*     */   public static final int GL_ADJACENT_PAIRS_NV = 37038;
/*     */   public static final int GL_FIRST_TO_REST_NV = 37039;
/*     */   public static final int GL_PATH_GEN_MODE_NV = 37040;
/*     */   public static final int GL_PATH_GEN_COEFF_NV = 37041;
/*     */   public static final int GL_PATH_GEN_COLOR_FORMAT_NV = 37042;
/*     */   public static final int GL_PATH_GEN_COMPONENTS_NV = 37043;
/*     */ 
/*     */   public static void glPathCommandsNV(int path, ByteBuffer commands, int coordType, ByteBuffer coords)
/*     */   {
/* 254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 255 */     long function_pointer = caps.glPathCommandsNV;
/* 256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 257 */     BufferChecks.checkDirect(commands);
/* 258 */     BufferChecks.checkDirect(coords);
/* 259 */     nglPathCommandsNV(path, commands.remaining(), MemoryUtil.getAddress(commands), coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglPathCommandsNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glPathCoordsNV(int path, int coordType, ByteBuffer coords) {
/* 264 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 265 */     long function_pointer = caps.glPathCoordsNV;
/* 266 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 267 */     BufferChecks.checkDirect(coords);
/* 268 */     nglPathCoordsNV(path, coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglPathCoordsNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathSubCommandsNV(int path, int commandStart, int commandsToDelete, ByteBuffer commands, int coordType, ByteBuffer coords) {
/* 273 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 274 */     long function_pointer = caps.glPathSubCommandsNV;
/* 275 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 276 */     BufferChecks.checkDirect(commands);
/* 277 */     BufferChecks.checkDirect(coords);
/* 278 */     nglPathSubCommandsNV(path, commandStart, commandsToDelete, commands.remaining(), MemoryUtil.getAddress(commands), coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglPathSubCommandsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, int paramInt6, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glPathSubCoordsNV(int path, int coordStart, int coordType, ByteBuffer coords) {
/* 283 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 284 */     long function_pointer = caps.glPathSubCoordsNV;
/* 285 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 286 */     BufferChecks.checkDirect(coords);
/* 287 */     nglPathSubCoordsNV(path, coordStart, coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglPathSubCoordsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathStringNV(int path, int format, ByteBuffer pathString) {
/* 292 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 293 */     long function_pointer = caps.glPathStringNV;
/* 294 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 295 */     BufferChecks.checkDirect(pathString);
/* 296 */     nglPathStringNV(path, format, pathString.remaining(), MemoryUtil.getAddress(pathString), function_pointer);
/*     */   }
/*     */   static native void nglPathStringNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathGlyphsNV(int firstPathName, int fontTarget, ByteBuffer fontName, int fontStyle, int type, ByteBuffer charcodes, int handleMissingGlyphs, int pathParameterTemplate, float emScale) {
/* 301 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 302 */     long function_pointer = caps.glPathGlyphsNV;
/* 303 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 304 */     BufferChecks.checkDirect(fontName);
/* 305 */     BufferChecks.checkNullTerminated(fontName);
/* 306 */     BufferChecks.checkDirect(charcodes);
/* 307 */     nglPathGlyphsNV(firstPathName, fontTarget, MemoryUtil.getAddress(fontName), fontStyle, charcodes.remaining() / GLChecks.calculateBytesPerCharCode(type), type, MemoryUtil.getAddress(charcodes), handleMissingGlyphs, pathParameterTemplate, emScale, function_pointer);
/*     */   }
/*     */   static native void nglPathGlyphsNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, long paramLong2, int paramInt6, int paramInt7, float paramFloat, long paramLong3);
/*     */ 
/*     */   public static void glPathGlyphRangeNV(int firstPathName, int fontTarget, ByteBuffer fontName, int fontStyle, int firstGlyph, int numGlyphs, int handleMissingGlyphs, int pathParameterTemplate, float emScale) {
/* 312 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 313 */     long function_pointer = caps.glPathGlyphRangeNV;
/* 314 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 315 */     BufferChecks.checkDirect(fontName);
/* 316 */     BufferChecks.checkNullTerminated(fontName);
/* 317 */     nglPathGlyphRangeNV(firstPathName, fontTarget, MemoryUtil.getAddress(fontName), fontStyle, firstGlyph, numGlyphs, handleMissingGlyphs, pathParameterTemplate, emScale, function_pointer);
/*     */   }
/*     */   static native void nglPathGlyphRangeNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat, long paramLong2);
/*     */ 
/*     */   public static void glWeightPathsNV(int resultPath, IntBuffer paths, FloatBuffer weights) {
/* 322 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 323 */     long function_pointer = caps.glWeightPathsNV;
/* 324 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 325 */     BufferChecks.checkDirect(paths);
/* 326 */     BufferChecks.checkBuffer(weights, paths.remaining());
/* 327 */     nglWeightPathsNV(resultPath, paths.remaining(), MemoryUtil.getAddress(paths), MemoryUtil.getAddress(weights), function_pointer);
/*     */   }
/*     */   static native void nglWeightPathsNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glCopyPathNV(int resultPath, int srcPath) {
/* 332 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 333 */     long function_pointer = caps.glCopyPathNV;
/* 334 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 335 */     nglCopyPathNV(resultPath, srcPath, function_pointer);
/*     */   }
/*     */   static native void nglCopyPathNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glInterpolatePathsNV(int resultPath, int pathA, int pathB, float weight) {
/* 340 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 341 */     long function_pointer = caps.glInterpolatePathsNV;
/* 342 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 343 */     nglInterpolatePathsNV(resultPath, pathA, pathB, weight, function_pointer);
/*     */   }
/*     */   static native void nglInterpolatePathsNV(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glTransformPathNV(int resultPath, int srcPath, int transformType, FloatBuffer transformValues) {
/* 348 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 349 */     long function_pointer = caps.glTransformPathNV;
/* 350 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 351 */     if (transformValues != null)
/* 352 */       BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 353 */     nglTransformPathNV(resultPath, srcPath, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/*     */   }
/*     */   static native void nglTransformPathNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathParameterNV(int path, int pname, IntBuffer value) {
/* 358 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 359 */     long function_pointer = caps.glPathParameterivNV;
/* 360 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 361 */     BufferChecks.checkBuffer(value, 4);
/* 362 */     nglPathParameterivNV(path, pname, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglPathParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathParameteriNV(int path, int pname, int value) {
/* 367 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 368 */     long function_pointer = caps.glPathParameteriNV;
/* 369 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 370 */     nglPathParameteriNV(path, pname, value, function_pointer);
/*     */   }
/*     */   static native void nglPathParameteriNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glPathParameterfNV(int path, int pname, IntBuffer value) {
/* 375 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 376 */     long function_pointer = caps.glPathParameterfvNV;
/* 377 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 378 */     BufferChecks.checkBuffer(value, 4);
/* 379 */     nglPathParameterfvNV(path, pname, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglPathParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathParameterfNV(int path, int pname, float value) {
/* 384 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 385 */     long function_pointer = caps.glPathParameterfNV;
/* 386 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 387 */     nglPathParameterfNV(path, pname, value, function_pointer);
/*     */   }
/*     */   static native void nglPathParameterfNV(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glPathDashArrayNV(int path, FloatBuffer dashArray) {
/* 392 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 393 */     long function_pointer = caps.glPathDashArrayNV;
/* 394 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 395 */     BufferChecks.checkDirect(dashArray);
/* 396 */     nglPathDashArrayNV(path, dashArray.remaining(), MemoryUtil.getAddress(dashArray), function_pointer);
/*     */   }
/*     */   static native void nglPathDashArrayNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenPathsNV(int range) {
/* 401 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 402 */     long function_pointer = caps.glGenPathsNV;
/* 403 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 404 */     int __result = nglGenPathsNV(range, function_pointer);
/* 405 */     return __result;
/*     */   }
/*     */   static native int nglGenPathsNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glDeletePathsNV(int path, int range) {
/* 410 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 411 */     long function_pointer = caps.glDeletePathsNV;
/* 412 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 413 */     nglDeletePathsNV(path, range, function_pointer);
/*     */   }
/*     */   static native void nglDeletePathsNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static boolean glIsPathNV(int path) {
/* 418 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 419 */     long function_pointer = caps.glIsPathNV;
/* 420 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 421 */     boolean __result = nglIsPathNV(path, function_pointer);
/* 422 */     return __result;
/*     */   }
/*     */   static native boolean nglIsPathNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glPathStencilFuncNV(int func, int ref, int mask) {
/* 427 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 428 */     long function_pointer = caps.glPathStencilFuncNV;
/* 429 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 430 */     nglPathStencilFuncNV(func, ref, mask, function_pointer);
/*     */   }
/*     */   static native void nglPathStencilFuncNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glPathStencilDepthOffsetNV(float factor, int units) {
/* 435 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 436 */     long function_pointer = caps.glPathStencilDepthOffsetNV;
/* 437 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 438 */     nglPathStencilDepthOffsetNV(factor, units, function_pointer);
/*     */   }
/*     */   static native void nglPathStencilDepthOffsetNV(float paramFloat, int paramInt, long paramLong);
/*     */ 
/*     */   public static void glStencilFillPathNV(int path, int fillMode, int mask) {
/* 443 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 444 */     long function_pointer = caps.glStencilFillPathNV;
/* 445 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 446 */     nglStencilFillPathNV(path, fillMode, mask, function_pointer);
/*     */   }
/*     */   static native void nglStencilFillPathNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glStencilStrokePathNV(int path, int reference, int mask) {
/* 451 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 452 */     long function_pointer = caps.glStencilStrokePathNV;
/* 453 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 454 */     nglStencilStrokePathNV(path, reference, mask, function_pointer);
/*     */   }
/*     */   static native void nglStencilStrokePathNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glStencilFillPathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int fillMode, int mask, int transformType, FloatBuffer transformValues) {
/* 459 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 460 */     long function_pointer = caps.glStencilFillPathInstancedNV;
/* 461 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 462 */     BufferChecks.checkDirect(paths);
/* 463 */     if (transformValues != null)
/* 464 */       BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 465 */     nglStencilFillPathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, fillMode, mask, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/*     */   }
/*     */   static native void nglStencilFillPathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glStencilStrokePathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int reference, int mask, int transformType, FloatBuffer transformValues) {
/* 470 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 471 */     long function_pointer = caps.glStencilStrokePathInstancedNV;
/* 472 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 473 */     BufferChecks.checkDirect(paths);
/* 474 */     if (transformValues != null)
/* 475 */       BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 476 */     nglStencilStrokePathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, reference, mask, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/*     */   }
/*     */   static native void nglStencilStrokePathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glPathCoverDepthFuncNV(int zfunc) {
/* 481 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 482 */     long function_pointer = caps.glPathCoverDepthFuncNV;
/* 483 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 484 */     nglPathCoverDepthFuncNV(zfunc, function_pointer);
/*     */   }
/*     */   static native void nglPathCoverDepthFuncNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glPathColorGenNV(int color, int genMode, int colorFormat, FloatBuffer coeffs) {
/* 489 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 490 */     long function_pointer = caps.glPathColorGenNV;
/* 491 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 492 */     if (coeffs != null)
/* 493 */       BufferChecks.checkBuffer(coeffs, GLChecks.calculatePathColorGenCoeffsCount(genMode, colorFormat));
/* 494 */     nglPathColorGenNV(color, genMode, colorFormat, MemoryUtil.getAddressSafe(coeffs), function_pointer);
/*     */   }
/*     */   static native void nglPathColorGenNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathTexGenNV(int texCoordSet, int genMode, FloatBuffer coeffs) {
/* 499 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 500 */     long function_pointer = caps.glPathTexGenNV;
/* 501 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 502 */     if (coeffs != null)
/* 503 */       BufferChecks.checkDirect(coeffs);
/* 504 */     nglPathTexGenNV(texCoordSet, genMode, GLChecks.calculatePathTextGenCoeffsPerComponent(coeffs, genMode), MemoryUtil.getAddressSafe(coeffs), function_pointer);
/*     */   }
/*     */   static native void nglPathTexGenNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPathFogGenNV(int genMode) {
/* 509 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 510 */     long function_pointer = caps.glPathFogGenNV;
/* 511 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 512 */     nglPathFogGenNV(genMode, function_pointer);
/*     */   }
/*     */   static native void nglPathFogGenNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glCoverFillPathNV(int path, int coverMode) {
/* 517 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 518 */     long function_pointer = caps.glCoverFillPathNV;
/* 519 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 520 */     nglCoverFillPathNV(path, coverMode, function_pointer);
/*     */   }
/*     */   static native void nglCoverFillPathNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glCoverStrokePathNV(int name, int coverMode) {
/* 525 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 526 */     long function_pointer = caps.glCoverStrokePathNV;
/* 527 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 528 */     nglCoverStrokePathNV(name, coverMode, function_pointer);
/*     */   }
/*     */   static native void nglCoverStrokePathNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glCoverFillPathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int coverMode, int transformType, FloatBuffer transformValues) {
/* 533 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 534 */     long function_pointer = caps.glCoverFillPathInstancedNV;
/* 535 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 536 */     BufferChecks.checkDirect(paths);
/* 537 */     if (transformValues != null)
/* 538 */       BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 539 */     nglCoverFillPathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, coverMode, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/*     */   }
/*     */   static native void nglCoverFillPathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glCoverStrokePathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int coverMode, int transformType, FloatBuffer transformValues) {
/* 544 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 545 */     long function_pointer = caps.glCoverStrokePathInstancedNV;
/* 546 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 547 */     BufferChecks.checkDirect(paths);
/* 548 */     if (transformValues != null)
/* 549 */       BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 550 */     nglCoverStrokePathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, coverMode, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/*     */   }
/*     */   static native void nglCoverStrokePathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glGetPathParameterNV(int name, int param, IntBuffer value) {
/* 555 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 556 */     long function_pointer = caps.glGetPathParameterivNV;
/* 557 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 558 */     BufferChecks.checkBuffer(value, 4);
/* 559 */     nglGetPathParameterivNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPathParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetPathParameteriNV(int name, int param) {
/* 565 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 566 */     long function_pointer = caps.glGetPathParameterivNV;
/* 567 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 568 */     IntBuffer value = APIUtil.getBufferInt(caps);
/* 569 */     nglGetPathParameterivNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/* 570 */     return value.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetPathParameterfvNV(int name, int param, FloatBuffer value) {
/* 574 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 575 */     long function_pointer = caps.glGetPathParameterfvNV;
/* 576 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 577 */     BufferChecks.checkBuffer(value, 4);
/* 578 */     nglGetPathParameterfvNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPathParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static float glGetPathParameterfNV(int name, int param) {
/* 584 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 585 */     long function_pointer = caps.glGetPathParameterfvNV;
/* 586 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 587 */     FloatBuffer value = APIUtil.getBufferFloat(caps);
/* 588 */     nglGetPathParameterfvNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/* 589 */     return value.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetPathCommandsNV(int name, ByteBuffer commands) {
/* 593 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 594 */     long function_pointer = caps.glGetPathCommandsNV;
/* 595 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 596 */     BufferChecks.checkDirect(commands);
/* 597 */     nglGetPathCommandsNV(name, MemoryUtil.getAddress(commands), function_pointer);
/*     */   }
/*     */   static native void nglGetPathCommandsNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetPathCoordsNV(int name, FloatBuffer coords) {
/* 602 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 603 */     long function_pointer = caps.glGetPathCoordsNV;
/* 604 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 605 */     BufferChecks.checkDirect(coords);
/* 606 */     nglGetPathCoordsNV(name, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglGetPathCoordsNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetPathDashArrayNV(int name, FloatBuffer dashArray) {
/* 611 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 612 */     long function_pointer = caps.glGetPathDashArrayNV;
/* 613 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 614 */     BufferChecks.checkDirect(dashArray);
/* 615 */     nglGetPathDashArrayNV(name, MemoryUtil.getAddress(dashArray), function_pointer);
/*     */   }
/*     */   static native void nglGetPathDashArrayNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetPathMetricsNV(int metricQueryMask, int pathNameType, ByteBuffer paths, int pathBase, int stride, FloatBuffer metrics) {
/* 620 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 621 */     long function_pointer = caps.glGetPathMetricsNV;
/* 622 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 623 */     BufferChecks.checkDirect(paths);
/* 624 */     BufferChecks.checkBuffer(metrics, GLChecks.calculateMetricsSize(metricQueryMask, stride));
/* 625 */     nglGetPathMetricsNV(metricQueryMask, paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, stride, MemoryUtil.getAddress(metrics), function_pointer);
/*     */   }
/*     */   static native void nglGetPathMetricsNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glGetPathMetricRangeNV(int metricQueryMask, int fistPathName, int numPaths, int stride, FloatBuffer metrics) {
/* 630 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 631 */     long function_pointer = caps.glGetPathMetricRangeNV;
/* 632 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 633 */     BufferChecks.checkBuffer(metrics, GLChecks.calculateMetricsSize(metricQueryMask, stride));
/* 634 */     nglGetPathMetricRangeNV(metricQueryMask, fistPathName, numPaths, stride, MemoryUtil.getAddress(metrics), function_pointer);
/*     */   }
/*     */   static native void nglGetPathMetricRangeNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetPathSpacingNV(int pathListMode, int pathNameType, ByteBuffer paths, int pathBase, float advanceScale, float kerningScale, int transformType, FloatBuffer returnedSpacing) {
/* 639 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 640 */     long function_pointer = caps.glGetPathSpacingNV;
/* 641 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 642 */     int numPaths = paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType);
/* 643 */     BufferChecks.checkDirect(paths);
/* 644 */     BufferChecks.checkBuffer(returnedSpacing, numPaths - 1);
/* 645 */     nglGetPathSpacingNV(pathListMode, numPaths, pathNameType, MemoryUtil.getAddress(paths), pathBase, advanceScale, kerningScale, transformType, MemoryUtil.getAddress(returnedSpacing), function_pointer);
/*     */   }
/*     */   static native void nglGetPathSpacingNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, float paramFloat1, float paramFloat2, int paramInt5, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glGetPathColorGenNV(int color, int pname, IntBuffer value) {
/* 650 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 651 */     long function_pointer = caps.glGetPathColorGenivNV;
/* 652 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 653 */     BufferChecks.checkBuffer(value, 16);
/* 654 */     nglGetPathColorGenivNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPathColorGenivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetPathColorGeniNV(int color, int pname) {
/* 660 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 661 */     long function_pointer = caps.glGetPathColorGenivNV;
/* 662 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 663 */     IntBuffer value = APIUtil.getBufferInt(caps);
/* 664 */     nglGetPathColorGenivNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/* 665 */     return value.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetPathColorGenNV(int color, int pname, FloatBuffer value) {
/* 669 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 670 */     long function_pointer = caps.glGetPathColorGenfvNV;
/* 671 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 672 */     BufferChecks.checkBuffer(value, 16);
/* 673 */     nglGetPathColorGenfvNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPathColorGenfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static float glGetPathColorGenfNV(int color, int pname) {
/* 679 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 680 */     long function_pointer = caps.glGetPathColorGenfvNV;
/* 681 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 682 */     FloatBuffer value = APIUtil.getBufferFloat(caps);
/* 683 */     nglGetPathColorGenfvNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/* 684 */     return value.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetPathTexGenNV(int texCoordSet, int pname, IntBuffer value) {
/* 688 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 689 */     long function_pointer = caps.glGetPathTexGenivNV;
/* 690 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 691 */     BufferChecks.checkBuffer(value, 16);
/* 692 */     nglGetPathTexGenivNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPathTexGenivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetPathTexGeniNV(int texCoordSet, int pname) {
/* 698 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 699 */     long function_pointer = caps.glGetPathTexGenivNV;
/* 700 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 701 */     IntBuffer value = APIUtil.getBufferInt(caps);
/* 702 */     nglGetPathTexGenivNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/* 703 */     return value.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetPathTexGenNV(int texCoordSet, int pname, FloatBuffer value) {
/* 707 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 708 */     long function_pointer = caps.glGetPathTexGenfvNV;
/* 709 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 710 */     BufferChecks.checkBuffer(value, 16);
/* 711 */     nglGetPathTexGenfvNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPathTexGenfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static float glGetPathTexGenfNV(int texCoordSet, int pname) {
/* 717 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 718 */     long function_pointer = caps.glGetPathTexGenfvNV;
/* 719 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 720 */     FloatBuffer value = APIUtil.getBufferFloat(caps);
/* 721 */     nglGetPathTexGenfvNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/* 722 */     return value.get(0);
/*     */   }
/*     */ 
/*     */   public static boolean glIsPointInFillPathNV(int path, int mask, float x, float y) {
/* 726 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 727 */     long function_pointer = caps.glIsPointInFillPathNV;
/* 728 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 729 */     boolean __result = nglIsPointInFillPathNV(path, mask, x, y, function_pointer);
/* 730 */     return __result;
/*     */   }
/*     */   static native boolean nglIsPointInFillPathNV(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static boolean glIsPointInStrokePathNV(int path, float x, float y) {
/* 735 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 736 */     long function_pointer = caps.glIsPointInStrokePathNV;
/* 737 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 738 */     boolean __result = nglIsPointInStrokePathNV(path, x, y, function_pointer);
/* 739 */     return __result;
/*     */   }
/*     */   static native boolean nglIsPointInStrokePathNV(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static float glGetPathLengthNV(int path, int startSegment, int numSegments) {
/* 744 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 745 */     long function_pointer = caps.glGetPathLengthNV;
/* 746 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 747 */     float __result = nglGetPathLengthNV(path, startSegment, numSegments, function_pointer);
/* 748 */     return __result;
/*     */   }
/*     */   static native float nglGetPathLengthNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static boolean glPointAlongPathNV(int path, int startSegment, int numSegments, float distance, FloatBuffer x, FloatBuffer y, FloatBuffer tangentX, FloatBuffer tangentY) {
/* 753 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 754 */     long function_pointer = caps.glPointAlongPathNV;
/* 755 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 756 */     if (x != null)
/* 757 */       BufferChecks.checkBuffer(x, 1);
/* 758 */     if (y != null)
/* 759 */       BufferChecks.checkBuffer(y, 1);
/* 760 */     if (tangentX != null)
/* 761 */       BufferChecks.checkBuffer(tangentX, 1);
/* 762 */     if (tangentY != null)
/* 763 */       BufferChecks.checkBuffer(tangentY, 1);
/* 764 */     boolean __result = nglPointAlongPathNV(path, startSegment, numSegments, distance, MemoryUtil.getAddressSafe(x), MemoryUtil.getAddressSafe(y), MemoryUtil.getAddressSafe(tangentX), MemoryUtil.getAddressSafe(tangentY), function_pointer);
/* 765 */     return __result;
/*     */   }
/*     */ 
/*     */   static native boolean nglPointAlongPathNV(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPathRendering
 * JD-Core Version:    0.6.2
 */