/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.MemoryUtil;
/*   8:    */
/* 123:    */public final class NVPathRendering
/* 124:    */{
/* 125:    */  public static final int GL_CLOSE_PATH_NV = 0;
/* 126:    */  public static final int GL_MOVE_TO_NV = 2;
/* 127:    */  public static final int GL_RELATIVE_MOVE_TO_NV = 3;
/* 128:    */  public static final int GL_LINE_TO_NV = 4;
/* 129:    */  public static final int GL_RELATIVE_LINE_TO_NV = 5;
/* 130:    */  public static final int GL_HORIZONTAL_LINE_TO_NV = 6;
/* 131:    */  public static final int GL_RELATIVE_HORIZONTAL_LINE_TO_NV = 7;
/* 132:    */  public static final int GL_VERTICAL_LINE_TO_NV = 8;
/* 133:    */  public static final int GL_RELATIVE_VERTICAL_LINE_TO_NV = 9;
/* 134:    */  public static final int GL_QUADRATIC_CURVE_TO_NV = 10;
/* 135:    */  public static final int GL_RELATIVE_QUADRATIC_CURVE_TO_NV = 11;
/* 136:    */  public static final int GL_CUBIC_CURVE_TO_NV = 12;
/* 137:    */  public static final int GL_RELATIVE_CUBIC_CURVE_TO_NV = 13;
/* 138:    */  public static final int GL_SMOOTH_QUADRATIC_CURVE_TO_NV = 14;
/* 139:    */  public static final int GL_RELATIVE_SMOOTH_QUADRATIC_CURVE_TO_NV = 15;
/* 140:    */  public static final int GL_SMOOTH_CUBIC_CURVE_TO_NV = 16;
/* 141:    */  public static final int GL_RELATIVE_SMOOTH_CUBIC_CURVE_TO_NV = 17;
/* 142:    */  public static final int GL_SMALL_CCW_ARC_TO_NV = 18;
/* 143:    */  public static final int GL_RELATIVE_SMALL_CCW_ARC_TO_NV = 19;
/* 144:    */  public static final int GL_SMALL_CW_ARC_TO_NV = 20;
/* 145:    */  public static final int GL_RELATIVE_SMALL_CW_ARC_TO_NV = 21;
/* 146:    */  public static final int GL_LARGE_CCW_ARC_TO_NV = 22;
/* 147:    */  public static final int GL_RELATIVE_LARGE_CCW_ARC_TO_NV = 23;
/* 148:    */  public static final int GL_LARGE_CW_ARC_TO_NV = 24;
/* 149:    */  public static final int GL_RELATIVE_LARGE_CW_ARC_TO_NV = 25;
/* 150:    */  public static final int GL_CIRCULAR_CCW_ARC_TO_NV = 248;
/* 151:    */  public static final int GL_CIRCULAR_CW_ARC_TO_NV = 250;
/* 152:    */  public static final int GL_CIRCULAR_TANGENT_ARC_TO_NV = 252;
/* 153:    */  public static final int GL_ARC_TO_NV = 254;
/* 154:    */  public static final int GL_RELATIVE_ARC_TO_NV = 255;
/* 155:    */  public static final int GL_PATH_FORMAT_SVG_NV = 36976;
/* 156:    */  public static final int GL_PATH_FORMAT_PS_NV = 36977;
/* 157:    */  public static final int GL_STANDARD_FONT_NAME_NV = 36978;
/* 158:    */  public static final int GL_SYSTEM_FONT_NAME_NV = 36979;
/* 159:    */  public static final int GL_FILE_NAME_NV = 36980;
/* 160:    */  public static final int GL_SKIP_MISSING_GLYPH_NV = 37033;
/* 161:    */  public static final int GL_USE_MISSING_GLYPH_NV = 37034;
/* 162:    */  public static final int GL_PATH_STROKE_WIDTH_NV = 36981;
/* 163:    */  public static final int GL_PATH_INITIAL_END_CAP_NV = 36983;
/* 164:    */  public static final int GL_PATH_TERMINAL_END_CAP_NV = 36984;
/* 165:    */  public static final int GL_PATH_JOIN_STYLE_NV = 36985;
/* 166:    */  public static final int GL_PATH_MITER_LIMIT_NV = 36986;
/* 167:    */  public static final int GL_PATH_INITIAL_DASH_CAP_NV = 36988;
/* 168:    */  public static final int GL_PATH_TERMINAL_DASH_CAP_NV = 36989;
/* 169:    */  public static final int GL_PATH_DASH_OFFSET_NV = 36990;
/* 170:    */  public static final int GL_PATH_CLIENT_LENGTH_NV = 36991;
/* 171:    */  public static final int GL_PATH_DASH_OFFSET_RESET_NV = 37044;
/* 172:    */  public static final int GL_PATH_FILL_MODE_NV = 36992;
/* 173:    */  public static final int GL_PATH_FILL_MASK_NV = 36993;
/* 174:    */  public static final int GL_PATH_FILL_COVER_MODE_NV = 36994;
/* 175:    */  public static final int GL_PATH_STROKE_COVER_MODE_NV = 36995;
/* 176:    */  public static final int GL_PATH_STROKE_MASK_NV = 36996;
/* 177:    */  public static final int GL_PATH_END_CAPS_NV = 36982;
/* 178:    */  public static final int GL_PATH_DASH_CAPS_NV = 36987;
/* 179:    */  public static final int GL_COUNT_UP_NV = 37000;
/* 180:    */  public static final int GL_COUNT_DOWN_NV = 37001;
/* 181:    */  public static final int GL_PRIMARY_COLOR = 34167;
/* 182:    */  public static final int GL_PRIMARY_COLOR_NV = 34092;
/* 183:    */  public static final int GL_SECONDARY_COLOR_NV = 34093;
/* 184:    */  public static final int GL_PATH_OBJECT_BOUNDING_BOX_NV = 37002;
/* 185:    */  public static final int GL_CONVEX_HULL_NV = 37003;
/* 186:    */  public static final int GL_BOUNDING_BOX_NV = 37005;
/* 187:    */  public static final int GL_TRANSLATE_X_NV = 37006;
/* 188:    */  public static final int GL_TRANSLATE_Y_NV = 37007;
/* 189:    */  public static final int GL_TRANSLATE_2D_NV = 37008;
/* 190:    */  public static final int GL_TRANSLATE_3D_NV = 37009;
/* 191:    */  public static final int GL_AFFINE_2D_NV = 37010;
/* 192:    */  public static final int GL_AFFINE_3D_NV = 37012;
/* 193:    */  public static final int GL_TRANSPOSE_AFFINE_2D_NV = 37014;
/* 194:    */  public static final int GL_TRANSPOSE_AFFINE_3D_NV = 37016;
/* 195:    */  public static final int GL_UTF8_NV = 37018;
/* 196:    */  public static final int GL_UTF16_NV = 37019;
/* 197:    */  public static final int GL_BOUNDING_BOX_OF_BOUNDING_BOXES_NV = 37020;
/* 198:    */  public static final int GL_PATH_COMMAND_COUNT_NV = 37021;
/* 199:    */  public static final int GL_PATH_COORD_COUNT_NV = 37022;
/* 200:    */  public static final int GL_PATH_DASH_ARRAY_COUNT_NV = 37023;
/* 201:    */  public static final int GL_PATH_COMPUTED_LENGTH_NV = 37024;
/* 202:    */  public static final int GL_PATH_FILL_BOUNDING_BOX_NV = 37025;
/* 203:    */  public static final int GL_PATH_STROKE_BOUNDING_BOX_NV = 37026;
/* 204:    */  public static final int GL_SQUARE_NV = 37027;
/* 205:    */  public static final int GL_ROUND_NV = 37028;
/* 206:    */  public static final int GL_TRIANGULAR_NV = 37029;
/* 207:    */  public static final int GL_BEVEL_NV = 37030;
/* 208:    */  public static final int GL_MITER_REVERT_NV = 37031;
/* 209:    */  public static final int GL_MITER_TRUNCATE_NV = 37032;
/* 210:    */  public static final int GL_MOVE_TO_RESETS_NV = 37045;
/* 211:    */  public static final int GL_MOVE_TO_CONTINUES_NV = 37046;
/* 212:    */  public static final int GL_BOLD_BIT_NV = 1;
/* 213:    */  public static final int GL_ITALIC_BIT_NV = 2;
/* 214:    */  public static final int GL_PATH_ERROR_POSITION_NV = 37035;
/* 215:    */  public static final int GL_PATH_FOG_GEN_MODE_NV = 37036;
/* 216:    */  public static final int GL_PATH_STENCIL_FUNC_NV = 37047;
/* 217:    */  public static final int GL_PATH_STENCIL_REF_NV = 37048;
/* 218:    */  public static final int GL_PATH_STENCIL_VALUE_MASK_NV = 37049;
/* 219:    */  public static final int GL_PATH_STENCIL_DEPTH_OFFSET_FACTOR_NV = 37053;
/* 220:    */  public static final int GL_PATH_STENCIL_DEPTH_OFFSET_UNITS_NV = 37054;
/* 221:    */  public static final int GL_PATH_COVER_DEPTH_FUNC_NV = 37055;
/* 222:    */  public static final int GL_GLYPH_WIDTH_BIT_NV = 1;
/* 223:    */  public static final int GL_GLYPH_HEIGHT_BIT_NV = 2;
/* 224:    */  public static final int GL_GLYPH_HORIZONTAL_BEARING_X_BIT_NV = 4;
/* 225:    */  public static final int GL_GLYPH_HORIZONTAL_BEARING_Y_BIT_NV = 8;
/* 226:    */  public static final int GL_GLYPH_HORIZONTAL_BEARING_ADVANCE_BIT_NV = 16;
/* 227:    */  public static final int GL_GLYPH_VERTICAL_BEARING_X_BIT_NV = 32;
/* 228:    */  public static final int GL_GLYPH_VERTICAL_BEARING_Y_BIT_NV = 64;
/* 229:    */  public static final int GL_GLYPH_VERTICAL_BEARING_ADVANCE_BIT_NV = 128;
/* 230:    */  public static final int GL_GLYPH_HAS_KERNING_NV = 256;
/* 231:    */  public static final int GL_FONT_X_MIN_BOUNDS_NV = 65536;
/* 232:    */  public static final int GL_FONT_Y_MIN_BOUNDS_NV = 131072;
/* 233:    */  public static final int GL_FONT_X_MAX_BOUNDS_NV = 262144;
/* 234:    */  public static final int GL_FONT_Y_MAX_BOUNDS_NV = 524288;
/* 235:    */  public static final int GL_FONT_UNITS_PER_EM_NV = 1048576;
/* 236:    */  public static final int GL_FONT_ASCENDER_NV = 2097152;
/* 237:    */  public static final int GL_FONT_DESCENDER_NV = 4194304;
/* 238:    */  public static final int GL_FONT_HEIGHT_NV = 8388608;
/* 239:    */  public static final int GL_FONT_MAX_ADVANCE_WIDTH_NV = 16777216;
/* 240:    */  public static final int GL_FONT_MAX_ADVANCE_HEIGHT_NV = 33554432;
/* 241:    */  public static final int GL_FONT_UNDERLINE_POSITION_NV = 67108864;
/* 242:    */  public static final int GL_FONT_UNDERLINE_THICKNESS_NV = 134217728;
/* 243:    */  public static final int GL_FONT_HAS_KERNING_NV = 268435456;
/* 244:    */  public static final int GL_ACCUM_ADJACENT_PAIRS_NV = 37037;
/* 245:    */  public static final int GL_ADJACENT_PAIRS_NV = 37038;
/* 246:    */  public static final int GL_FIRST_TO_REST_NV = 37039;
/* 247:    */  public static final int GL_PATH_GEN_MODE_NV = 37040;
/* 248:    */  public static final int GL_PATH_GEN_COEFF_NV = 37041;
/* 249:    */  public static final int GL_PATH_GEN_COLOR_FORMAT_NV = 37042;
/* 250:    */  public static final int GL_PATH_GEN_COMPONENTS_NV = 37043;
/* 251:    */  
/* 252:    */  public static void glPathCommandsNV(int path, ByteBuffer commands, int coordType, ByteBuffer coords)
/* 253:    */  {
/* 254:254 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 255:255 */    long function_pointer = caps.glPathCommandsNV;
/* 256:256 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 257:257 */    BufferChecks.checkDirect(commands);
/* 258:258 */    BufferChecks.checkDirect(coords);
/* 259:259 */    nglPathCommandsNV(path, commands.remaining(), MemoryUtil.getAddress(commands), coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/* 260:    */  }
/* 261:    */  
/* 262:    */  static native void nglPathCommandsNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2, long paramLong3);
/* 263:    */  
/* 264:264 */  public static void glPathCoordsNV(int path, int coordType, ByteBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 265:265 */    long function_pointer = caps.glPathCoordsNV;
/* 266:266 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 267:267 */    BufferChecks.checkDirect(coords);
/* 268:268 */    nglPathCoordsNV(path, coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/* 269:    */  }
/* 270:    */  
/* 271:    */  static native void nglPathCoordsNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 272:    */  
/* 273:273 */  public static void glPathSubCommandsNV(int path, int commandStart, int commandsToDelete, ByteBuffer commands, int coordType, ByteBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 274:274 */    long function_pointer = caps.glPathSubCommandsNV;
/* 275:275 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 276:276 */    BufferChecks.checkDirect(commands);
/* 277:277 */    BufferChecks.checkDirect(coords);
/* 278:278 */    nglPathSubCommandsNV(path, commandStart, commandsToDelete, commands.remaining(), MemoryUtil.getAddress(commands), coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/* 279:    */  }
/* 280:    */  
/* 281:    */  static native void nglPathSubCommandsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, int paramInt6, long paramLong2, long paramLong3);
/* 282:    */  
/* 283:283 */  public static void glPathSubCoordsNV(int path, int coordStart, int coordType, ByteBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 284:284 */    long function_pointer = caps.glPathSubCoordsNV;
/* 285:285 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 286:286 */    BufferChecks.checkDirect(coords);
/* 287:287 */    nglPathSubCoordsNV(path, coordStart, coords.remaining(), coordType, MemoryUtil.getAddress(coords), function_pointer);
/* 288:    */  }
/* 289:    */  
/* 290:    */  static native void nglPathSubCoordsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 291:    */  
/* 292:292 */  public static void glPathStringNV(int path, int format, ByteBuffer pathString) { ContextCapabilities caps = GLContext.getCapabilities();
/* 293:293 */    long function_pointer = caps.glPathStringNV;
/* 294:294 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 295:295 */    BufferChecks.checkDirect(pathString);
/* 296:296 */    nglPathStringNV(path, format, pathString.remaining(), MemoryUtil.getAddress(pathString), function_pointer);
/* 297:    */  }
/* 298:    */  
/* 299:    */  static native void nglPathStringNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 300:    */  
/* 301:301 */  public static void glPathGlyphsNV(int firstPathName, int fontTarget, ByteBuffer fontName, int fontStyle, int type, ByteBuffer charcodes, int handleMissingGlyphs, int pathParameterTemplate, float emScale) { ContextCapabilities caps = GLContext.getCapabilities();
/* 302:302 */    long function_pointer = caps.glPathGlyphsNV;
/* 303:303 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 304:304 */    BufferChecks.checkDirect(fontName);
/* 305:305 */    BufferChecks.checkNullTerminated(fontName);
/* 306:306 */    BufferChecks.checkDirect(charcodes);
/* 307:307 */    nglPathGlyphsNV(firstPathName, fontTarget, MemoryUtil.getAddress(fontName), fontStyle, charcodes.remaining() / GLChecks.calculateBytesPerCharCode(type), type, MemoryUtil.getAddress(charcodes), handleMissingGlyphs, pathParameterTemplate, emScale, function_pointer);
/* 308:    */  }
/* 309:    */  
/* 310:    */  static native void nglPathGlyphsNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, long paramLong2, int paramInt6, int paramInt7, float paramFloat, long paramLong3);
/* 311:    */  
/* 312:312 */  public static void glPathGlyphRangeNV(int firstPathName, int fontTarget, ByteBuffer fontName, int fontStyle, int firstGlyph, int numGlyphs, int handleMissingGlyphs, int pathParameterTemplate, float emScale) { ContextCapabilities caps = GLContext.getCapabilities();
/* 313:313 */    long function_pointer = caps.glPathGlyphRangeNV;
/* 314:314 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 315:315 */    BufferChecks.checkDirect(fontName);
/* 316:316 */    BufferChecks.checkNullTerminated(fontName);
/* 317:317 */    nglPathGlyphRangeNV(firstPathName, fontTarget, MemoryUtil.getAddress(fontName), fontStyle, firstGlyph, numGlyphs, handleMissingGlyphs, pathParameterTemplate, emScale, function_pointer);
/* 318:    */  }
/* 319:    */  
/* 320:    */  static native void nglPathGlyphRangeNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat, long paramLong2);
/* 321:    */  
/* 322:322 */  public static void glWeightPathsNV(int resultPath, IntBuffer paths, FloatBuffer weights) { ContextCapabilities caps = GLContext.getCapabilities();
/* 323:323 */    long function_pointer = caps.glWeightPathsNV;
/* 324:324 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 325:325 */    BufferChecks.checkDirect(paths);
/* 326:326 */    BufferChecks.checkBuffer(weights, paths.remaining());
/* 327:327 */    nglWeightPathsNV(resultPath, paths.remaining(), MemoryUtil.getAddress(paths), MemoryUtil.getAddress(weights), function_pointer);
/* 328:    */  }
/* 329:    */  
/* 330:    */  static native void nglWeightPathsNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 331:    */  
/* 332:332 */  public static void glCopyPathNV(int resultPath, int srcPath) { ContextCapabilities caps = GLContext.getCapabilities();
/* 333:333 */    long function_pointer = caps.glCopyPathNV;
/* 334:334 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 335:335 */    nglCopyPathNV(resultPath, srcPath, function_pointer);
/* 336:    */  }
/* 337:    */  
/* 338:    */  static native void nglCopyPathNV(int paramInt1, int paramInt2, long paramLong);
/* 339:    */  
/* 340:340 */  public static void glInterpolatePathsNV(int resultPath, int pathA, int pathB, float weight) { ContextCapabilities caps = GLContext.getCapabilities();
/* 341:341 */    long function_pointer = caps.glInterpolatePathsNV;
/* 342:342 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 343:343 */    nglInterpolatePathsNV(resultPath, pathA, pathB, weight, function_pointer);
/* 344:    */  }
/* 345:    */  
/* 346:    */  static native void nglInterpolatePathsNV(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/* 347:    */  
/* 348:348 */  public static void glTransformPathNV(int resultPath, int srcPath, int transformType, FloatBuffer transformValues) { ContextCapabilities caps = GLContext.getCapabilities();
/* 349:349 */    long function_pointer = caps.glTransformPathNV;
/* 350:350 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 351:351 */    if (transformValues != null)
/* 352:352 */      BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 353:353 */    nglTransformPathNV(resultPath, srcPath, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/* 354:    */  }
/* 355:    */  
/* 356:    */  static native void nglTransformPathNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 357:    */  
/* 358:358 */  public static void glPathParameterNV(int path, int pname, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 359:359 */    long function_pointer = caps.glPathParameterivNV;
/* 360:360 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 361:361 */    BufferChecks.checkBuffer(value, 4);
/* 362:362 */    nglPathParameterivNV(path, pname, MemoryUtil.getAddress(value), function_pointer);
/* 363:    */  }
/* 364:    */  
/* 365:    */  static native void nglPathParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 366:    */  
/* 367:367 */  public static void glPathParameteriNV(int path, int pname, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 368:368 */    long function_pointer = caps.glPathParameteriNV;
/* 369:369 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 370:370 */    nglPathParameteriNV(path, pname, value, function_pointer);
/* 371:    */  }
/* 372:    */  
/* 373:    */  static native void nglPathParameteriNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 374:    */  
/* 375:375 */  public static void glPathParameterfNV(int path, int pname, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 376:376 */    long function_pointer = caps.glPathParameterfvNV;
/* 377:377 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 378:378 */    BufferChecks.checkBuffer(value, 4);
/* 379:379 */    nglPathParameterfvNV(path, pname, MemoryUtil.getAddress(value), function_pointer);
/* 380:    */  }
/* 381:    */  
/* 382:    */  static native void nglPathParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 383:    */  
/* 384:384 */  public static void glPathParameterfNV(int path, int pname, float value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 385:385 */    long function_pointer = caps.glPathParameterfNV;
/* 386:386 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 387:387 */    nglPathParameterfNV(path, pname, value, function_pointer);
/* 388:    */  }
/* 389:    */  
/* 390:    */  static native void nglPathParameterfNV(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/* 391:    */  
/* 392:392 */  public static void glPathDashArrayNV(int path, FloatBuffer dashArray) { ContextCapabilities caps = GLContext.getCapabilities();
/* 393:393 */    long function_pointer = caps.glPathDashArrayNV;
/* 394:394 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 395:395 */    BufferChecks.checkDirect(dashArray);
/* 396:396 */    nglPathDashArrayNV(path, dashArray.remaining(), MemoryUtil.getAddress(dashArray), function_pointer);
/* 397:    */  }
/* 398:    */  
/* 399:    */  static native void nglPathDashArrayNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 400:    */  
/* 401:401 */  public static int glGenPathsNV(int range) { ContextCapabilities caps = GLContext.getCapabilities();
/* 402:402 */    long function_pointer = caps.glGenPathsNV;
/* 403:403 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 404:404 */    int __result = nglGenPathsNV(range, function_pointer);
/* 405:405 */    return __result;
/* 406:    */  }
/* 407:    */  
/* 408:    */  static native int nglGenPathsNV(int paramInt, long paramLong);
/* 409:    */  
/* 410:410 */  public static void glDeletePathsNV(int path, int range) { ContextCapabilities caps = GLContext.getCapabilities();
/* 411:411 */    long function_pointer = caps.glDeletePathsNV;
/* 412:412 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 413:413 */    nglDeletePathsNV(path, range, function_pointer);
/* 414:    */  }
/* 415:    */  
/* 416:    */  static native void nglDeletePathsNV(int paramInt1, int paramInt2, long paramLong);
/* 417:    */  
/* 418:418 */  public static boolean glIsPathNV(int path) { ContextCapabilities caps = GLContext.getCapabilities();
/* 419:419 */    long function_pointer = caps.glIsPathNV;
/* 420:420 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 421:421 */    boolean __result = nglIsPathNV(path, function_pointer);
/* 422:422 */    return __result;
/* 423:    */  }
/* 424:    */  
/* 425:    */  static native boolean nglIsPathNV(int paramInt, long paramLong);
/* 426:    */  
/* 427:427 */  public static void glPathStencilFuncNV(int func, int ref, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 428:428 */    long function_pointer = caps.glPathStencilFuncNV;
/* 429:429 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 430:430 */    nglPathStencilFuncNV(func, ref, mask, function_pointer);
/* 431:    */  }
/* 432:    */  
/* 433:    */  static native void nglPathStencilFuncNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 434:    */  
/* 435:435 */  public static void glPathStencilDepthOffsetNV(float factor, int units) { ContextCapabilities caps = GLContext.getCapabilities();
/* 436:436 */    long function_pointer = caps.glPathStencilDepthOffsetNV;
/* 437:437 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 438:438 */    nglPathStencilDepthOffsetNV(factor, units, function_pointer);
/* 439:    */  }
/* 440:    */  
/* 441:    */  static native void nglPathStencilDepthOffsetNV(float paramFloat, int paramInt, long paramLong);
/* 442:    */  
/* 443:443 */  public static void glStencilFillPathNV(int path, int fillMode, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 444:444 */    long function_pointer = caps.glStencilFillPathNV;
/* 445:445 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 446:446 */    nglStencilFillPathNV(path, fillMode, mask, function_pointer);
/* 447:    */  }
/* 448:    */  
/* 449:    */  static native void nglStencilFillPathNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 450:    */  
/* 451:451 */  public static void glStencilStrokePathNV(int path, int reference, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 452:452 */    long function_pointer = caps.glStencilStrokePathNV;
/* 453:453 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 454:454 */    nglStencilStrokePathNV(path, reference, mask, function_pointer);
/* 455:    */  }
/* 456:    */  
/* 457:    */  static native void nglStencilStrokePathNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 458:    */  
/* 459:459 */  public static void glStencilFillPathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int fillMode, int mask, int transformType, FloatBuffer transformValues) { ContextCapabilities caps = GLContext.getCapabilities();
/* 460:460 */    long function_pointer = caps.glStencilFillPathInstancedNV;
/* 461:461 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 462:462 */    BufferChecks.checkDirect(paths);
/* 463:463 */    if (transformValues != null)
/* 464:464 */      BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 465:465 */    nglStencilFillPathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, fillMode, mask, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/* 466:    */  }
/* 467:    */  
/* 468:    */  static native void nglStencilFillPathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong2, long paramLong3);
/* 469:    */  
/* 470:470 */  public static void glStencilStrokePathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int reference, int mask, int transformType, FloatBuffer transformValues) { ContextCapabilities caps = GLContext.getCapabilities();
/* 471:471 */    long function_pointer = caps.glStencilStrokePathInstancedNV;
/* 472:472 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 473:473 */    BufferChecks.checkDirect(paths);
/* 474:474 */    if (transformValues != null)
/* 475:475 */      BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 476:476 */    nglStencilStrokePathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, reference, mask, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/* 477:    */  }
/* 478:    */  
/* 479:    */  static native void nglStencilStrokePathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong2, long paramLong3);
/* 480:    */  
/* 481:481 */  public static void glPathCoverDepthFuncNV(int zfunc) { ContextCapabilities caps = GLContext.getCapabilities();
/* 482:482 */    long function_pointer = caps.glPathCoverDepthFuncNV;
/* 483:483 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 484:484 */    nglPathCoverDepthFuncNV(zfunc, function_pointer);
/* 485:    */  }
/* 486:    */  
/* 487:    */  static native void nglPathCoverDepthFuncNV(int paramInt, long paramLong);
/* 488:    */  
/* 489:489 */  public static void glPathColorGenNV(int color, int genMode, int colorFormat, FloatBuffer coeffs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 490:490 */    long function_pointer = caps.glPathColorGenNV;
/* 491:491 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 492:492 */    if (coeffs != null)
/* 493:493 */      BufferChecks.checkBuffer(coeffs, GLChecks.calculatePathColorGenCoeffsCount(genMode, colorFormat));
/* 494:494 */    nglPathColorGenNV(color, genMode, colorFormat, MemoryUtil.getAddressSafe(coeffs), function_pointer);
/* 495:    */  }
/* 496:    */  
/* 497:    */  static native void nglPathColorGenNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 498:    */  
/* 499:499 */  public static void glPathTexGenNV(int texCoordSet, int genMode, FloatBuffer coeffs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 500:500 */    long function_pointer = caps.glPathTexGenNV;
/* 501:501 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 502:502 */    if (coeffs != null)
/* 503:503 */      BufferChecks.checkDirect(coeffs);
/* 504:504 */    nglPathTexGenNV(texCoordSet, genMode, GLChecks.calculatePathTextGenCoeffsPerComponent(coeffs, genMode), MemoryUtil.getAddressSafe(coeffs), function_pointer);
/* 505:    */  }
/* 506:    */  
/* 507:    */  static native void nglPathTexGenNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 508:    */  
/* 509:509 */  public static void glPathFogGenNV(int genMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 510:510 */    long function_pointer = caps.glPathFogGenNV;
/* 511:511 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 512:512 */    nglPathFogGenNV(genMode, function_pointer);
/* 513:    */  }
/* 514:    */  
/* 515:    */  static native void nglPathFogGenNV(int paramInt, long paramLong);
/* 516:    */  
/* 517:517 */  public static void glCoverFillPathNV(int path, int coverMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 518:518 */    long function_pointer = caps.glCoverFillPathNV;
/* 519:519 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 520:520 */    nglCoverFillPathNV(path, coverMode, function_pointer);
/* 521:    */  }
/* 522:    */  
/* 523:    */  static native void nglCoverFillPathNV(int paramInt1, int paramInt2, long paramLong);
/* 524:    */  
/* 525:525 */  public static void glCoverStrokePathNV(int name, int coverMode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 526:526 */    long function_pointer = caps.glCoverStrokePathNV;
/* 527:527 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 528:528 */    nglCoverStrokePathNV(name, coverMode, function_pointer);
/* 529:    */  }
/* 530:    */  
/* 531:    */  static native void nglCoverStrokePathNV(int paramInt1, int paramInt2, long paramLong);
/* 532:    */  
/* 533:533 */  public static void glCoverFillPathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int coverMode, int transformType, FloatBuffer transformValues) { ContextCapabilities caps = GLContext.getCapabilities();
/* 534:534 */    long function_pointer = caps.glCoverFillPathInstancedNV;
/* 535:535 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 536:536 */    BufferChecks.checkDirect(paths);
/* 537:537 */    if (transformValues != null)
/* 538:538 */      BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 539:539 */    nglCoverFillPathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, coverMode, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/* 540:    */  }
/* 541:    */  
/* 542:    */  static native void nglCoverFillPathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, long paramLong2, long paramLong3);
/* 543:    */  
/* 544:544 */  public static void glCoverStrokePathInstancedNV(int pathNameType, ByteBuffer paths, int pathBase, int coverMode, int transformType, FloatBuffer transformValues) { ContextCapabilities caps = GLContext.getCapabilities();
/* 545:545 */    long function_pointer = caps.glCoverStrokePathInstancedNV;
/* 546:546 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 547:547 */    BufferChecks.checkDirect(paths);
/* 548:548 */    if (transformValues != null)
/* 549:549 */      BufferChecks.checkBuffer(transformValues, GLChecks.calculateTransformPathValues(transformType));
/* 550:550 */    nglCoverStrokePathInstancedNV(paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, coverMode, transformType, MemoryUtil.getAddressSafe(transformValues), function_pointer);
/* 551:    */  }
/* 552:    */  
/* 553:    */  static native void nglCoverStrokePathInstancedNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, long paramLong2, long paramLong3);
/* 554:    */  
/* 555:555 */  public static void glGetPathParameterNV(int name, int param, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 556:556 */    long function_pointer = caps.glGetPathParameterivNV;
/* 557:557 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 558:558 */    BufferChecks.checkBuffer(value, 4);
/* 559:559 */    nglGetPathParameterivNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/* 560:    */  }
/* 561:    */  
/* 562:    */  static native void nglGetPathParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 563:    */  
/* 564:    */  public static int glGetPathParameteriNV(int name, int param) {
/* 565:565 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 566:566 */    long function_pointer = caps.glGetPathParameterivNV;
/* 567:567 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 568:568 */    IntBuffer value = APIUtil.getBufferInt(caps);
/* 569:569 */    nglGetPathParameterivNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/* 570:570 */    return value.get(0);
/* 571:    */  }
/* 572:    */  
/* 573:    */  public static void glGetPathParameterfvNV(int name, int param, FloatBuffer value) {
/* 574:574 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 575:575 */    long function_pointer = caps.glGetPathParameterfvNV;
/* 576:576 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 577:577 */    BufferChecks.checkBuffer(value, 4);
/* 578:578 */    nglGetPathParameterfvNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/* 579:    */  }
/* 580:    */  
/* 581:    */  static native void nglGetPathParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 582:    */  
/* 583:    */  public static float glGetPathParameterfNV(int name, int param) {
/* 584:584 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 585:585 */    long function_pointer = caps.glGetPathParameterfvNV;
/* 586:586 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 587:587 */    FloatBuffer value = APIUtil.getBufferFloat(caps);
/* 588:588 */    nglGetPathParameterfvNV(name, param, MemoryUtil.getAddress(value), function_pointer);
/* 589:589 */    return value.get(0);
/* 590:    */  }
/* 591:    */  
/* 592:    */  public static void glGetPathCommandsNV(int name, ByteBuffer commands) {
/* 593:593 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 594:594 */    long function_pointer = caps.glGetPathCommandsNV;
/* 595:595 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 596:596 */    BufferChecks.checkDirect(commands);
/* 597:597 */    nglGetPathCommandsNV(name, MemoryUtil.getAddress(commands), function_pointer);
/* 598:    */  }
/* 599:    */  
/* 600:    */  static native void nglGetPathCommandsNV(int paramInt, long paramLong1, long paramLong2);
/* 601:    */  
/* 602:602 */  public static void glGetPathCoordsNV(int name, FloatBuffer coords) { ContextCapabilities caps = GLContext.getCapabilities();
/* 603:603 */    long function_pointer = caps.glGetPathCoordsNV;
/* 604:604 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 605:605 */    BufferChecks.checkDirect(coords);
/* 606:606 */    nglGetPathCoordsNV(name, MemoryUtil.getAddress(coords), function_pointer);
/* 607:    */  }
/* 608:    */  
/* 609:    */  static native void nglGetPathCoordsNV(int paramInt, long paramLong1, long paramLong2);
/* 610:    */  
/* 611:611 */  public static void glGetPathDashArrayNV(int name, FloatBuffer dashArray) { ContextCapabilities caps = GLContext.getCapabilities();
/* 612:612 */    long function_pointer = caps.glGetPathDashArrayNV;
/* 613:613 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 614:614 */    BufferChecks.checkDirect(dashArray);
/* 615:615 */    nglGetPathDashArrayNV(name, MemoryUtil.getAddress(dashArray), function_pointer);
/* 616:    */  }
/* 617:    */  
/* 618:    */  static native void nglGetPathDashArrayNV(int paramInt, long paramLong1, long paramLong2);
/* 619:    */  
/* 620:620 */  public static void glGetPathMetricsNV(int metricQueryMask, int pathNameType, ByteBuffer paths, int pathBase, int stride, FloatBuffer metrics) { ContextCapabilities caps = GLContext.getCapabilities();
/* 621:621 */    long function_pointer = caps.glGetPathMetricsNV;
/* 622:622 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 623:623 */    BufferChecks.checkDirect(paths);
/* 624:624 */    BufferChecks.checkBuffer(metrics, GLChecks.calculateMetricsSize(metricQueryMask, stride));
/* 625:625 */    nglGetPathMetricsNV(metricQueryMask, paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType), pathNameType, MemoryUtil.getAddress(paths), pathBase, stride, MemoryUtil.getAddress(metrics), function_pointer);
/* 626:    */  }
/* 627:    */  
/* 628:    */  static native void nglGetPathMetricsNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2, long paramLong3);
/* 629:    */  
/* 630:630 */  public static void glGetPathMetricRangeNV(int metricQueryMask, int fistPathName, int numPaths, int stride, FloatBuffer metrics) { ContextCapabilities caps = GLContext.getCapabilities();
/* 631:631 */    long function_pointer = caps.glGetPathMetricRangeNV;
/* 632:632 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 633:633 */    BufferChecks.checkBuffer(metrics, GLChecks.calculateMetricsSize(metricQueryMask, stride));
/* 634:634 */    nglGetPathMetricRangeNV(metricQueryMask, fistPathName, numPaths, stride, MemoryUtil.getAddress(metrics), function_pointer);
/* 635:    */  }
/* 636:    */  
/* 637:    */  static native void nglGetPathMetricRangeNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 638:    */  
/* 639:639 */  public static void glGetPathSpacingNV(int pathListMode, int pathNameType, ByteBuffer paths, int pathBase, float advanceScale, float kerningScale, int transformType, FloatBuffer returnedSpacing) { ContextCapabilities caps = GLContext.getCapabilities();
/* 640:640 */    long function_pointer = caps.glGetPathSpacingNV;
/* 641:641 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 642:642 */    int numPaths = paths.remaining() / GLChecks.calculateBytesPerPathName(pathNameType);
/* 643:643 */    BufferChecks.checkDirect(paths);
/* 644:644 */    BufferChecks.checkBuffer(returnedSpacing, numPaths - 1);
/* 645:645 */    nglGetPathSpacingNV(pathListMode, numPaths, pathNameType, MemoryUtil.getAddress(paths), pathBase, advanceScale, kerningScale, transformType, MemoryUtil.getAddress(returnedSpacing), function_pointer);
/* 646:    */  }
/* 647:    */  
/* 648:    */  static native void nglGetPathSpacingNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, float paramFloat1, float paramFloat2, int paramInt5, long paramLong2, long paramLong3);
/* 649:    */  
/* 650:650 */  public static void glGetPathColorGenNV(int color, int pname, IntBuffer value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 651:651 */    long function_pointer = caps.glGetPathColorGenivNV;
/* 652:652 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 653:653 */    BufferChecks.checkBuffer(value, 16);
/* 654:654 */    nglGetPathColorGenivNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/* 655:    */  }
/* 656:    */  
/* 657:    */  static native void nglGetPathColorGenivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 658:    */  
/* 659:    */  public static int glGetPathColorGeniNV(int color, int pname) {
/* 660:660 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 661:661 */    long function_pointer = caps.glGetPathColorGenivNV;
/* 662:662 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 663:663 */    IntBuffer value = APIUtil.getBufferInt(caps);
/* 664:664 */    nglGetPathColorGenivNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/* 665:665 */    return value.get(0);
/* 666:    */  }
/* 667:    */  
/* 668:    */  public static void glGetPathColorGenNV(int color, int pname, FloatBuffer value) {
/* 669:669 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 670:670 */    long function_pointer = caps.glGetPathColorGenfvNV;
/* 671:671 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 672:672 */    BufferChecks.checkBuffer(value, 16);
/* 673:673 */    nglGetPathColorGenfvNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/* 674:    */  }
/* 675:    */  
/* 676:    */  static native void nglGetPathColorGenfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 677:    */  
/* 678:    */  public static float glGetPathColorGenfNV(int color, int pname) {
/* 679:679 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 680:680 */    long function_pointer = caps.glGetPathColorGenfvNV;
/* 681:681 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 682:682 */    FloatBuffer value = APIUtil.getBufferFloat(caps);
/* 683:683 */    nglGetPathColorGenfvNV(color, pname, MemoryUtil.getAddress(value), function_pointer);
/* 684:684 */    return value.get(0);
/* 685:    */  }
/* 686:    */  
/* 687:    */  public static void glGetPathTexGenNV(int texCoordSet, int pname, IntBuffer value) {
/* 688:688 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 689:689 */    long function_pointer = caps.glGetPathTexGenivNV;
/* 690:690 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 691:691 */    BufferChecks.checkBuffer(value, 16);
/* 692:692 */    nglGetPathTexGenivNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/* 693:    */  }
/* 694:    */  
/* 695:    */  static native void nglGetPathTexGenivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 696:    */  
/* 697:    */  public static int glGetPathTexGeniNV(int texCoordSet, int pname) {
/* 698:698 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 699:699 */    long function_pointer = caps.glGetPathTexGenivNV;
/* 700:700 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 701:701 */    IntBuffer value = APIUtil.getBufferInt(caps);
/* 702:702 */    nglGetPathTexGenivNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/* 703:703 */    return value.get(0);
/* 704:    */  }
/* 705:    */  
/* 706:    */  public static void glGetPathTexGenNV(int texCoordSet, int pname, FloatBuffer value) {
/* 707:707 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 708:708 */    long function_pointer = caps.glGetPathTexGenfvNV;
/* 709:709 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 710:710 */    BufferChecks.checkBuffer(value, 16);
/* 711:711 */    nglGetPathTexGenfvNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/* 712:    */  }
/* 713:    */  
/* 714:    */  static native void nglGetPathTexGenfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 715:    */  
/* 716:    */  public static float glGetPathTexGenfNV(int texCoordSet, int pname) {
/* 717:717 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 718:718 */    long function_pointer = caps.glGetPathTexGenfvNV;
/* 719:719 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 720:720 */    FloatBuffer value = APIUtil.getBufferFloat(caps);
/* 721:721 */    nglGetPathTexGenfvNV(texCoordSet, pname, MemoryUtil.getAddress(value), function_pointer);
/* 722:722 */    return value.get(0);
/* 723:    */  }
/* 724:    */  
/* 725:    */  public static boolean glIsPointInFillPathNV(int path, int mask, float x, float y) {
/* 726:726 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 727:727 */    long function_pointer = caps.glIsPointInFillPathNV;
/* 728:728 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 729:729 */    boolean __result = nglIsPointInFillPathNV(path, mask, x, y, function_pointer);
/* 730:730 */    return __result;
/* 731:    */  }
/* 732:    */  
/* 733:    */  static native boolean nglIsPointInFillPathNV(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, long paramLong);
/* 734:    */  
/* 735:735 */  public static boolean glIsPointInStrokePathNV(int path, float x, float y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 736:736 */    long function_pointer = caps.glIsPointInStrokePathNV;
/* 737:737 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 738:738 */    boolean __result = nglIsPointInStrokePathNV(path, x, y, function_pointer);
/* 739:739 */    return __result;
/* 740:    */  }
/* 741:    */  
/* 742:    */  static native boolean nglIsPointInStrokePathNV(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 743:    */  
/* 744:744 */  public static float glGetPathLengthNV(int path, int startSegment, int numSegments) { ContextCapabilities caps = GLContext.getCapabilities();
/* 745:745 */    long function_pointer = caps.glGetPathLengthNV;
/* 746:746 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 747:747 */    float __result = nglGetPathLengthNV(path, startSegment, numSegments, function_pointer);
/* 748:748 */    return __result;
/* 749:    */  }
/* 750:    */  
/* 751:    */  static native float nglGetPathLengthNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 752:    */  
/* 753:753 */  public static boolean glPointAlongPathNV(int path, int startSegment, int numSegments, float distance, FloatBuffer x, FloatBuffer y, FloatBuffer tangentX, FloatBuffer tangentY) { ContextCapabilities caps = GLContext.getCapabilities();
/* 754:754 */    long function_pointer = caps.glPointAlongPathNV;
/* 755:755 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 756:756 */    if (x != null)
/* 757:757 */      BufferChecks.checkBuffer(x, 1);
/* 758:758 */    if (y != null)
/* 759:759 */      BufferChecks.checkBuffer(y, 1);
/* 760:760 */    if (tangentX != null)
/* 761:761 */      BufferChecks.checkBuffer(tangentX, 1);
/* 762:762 */    if (tangentY != null)
/* 763:763 */      BufferChecks.checkBuffer(tangentY, 1);
/* 764:764 */    boolean __result = nglPointAlongPathNV(path, startSegment, numSegments, distance, MemoryUtil.getAddressSafe(x), MemoryUtil.getAddressSafe(y), MemoryUtil.getAddressSafe(tangentX), MemoryUtil.getAddressSafe(tangentY), function_pointer);
/* 765:765 */    return __result;
/* 766:    */  }
/* 767:    */  
/* 768:    */  static native boolean nglPointAlongPathNV(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 769:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPathRendering
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */