/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteOrder;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.ShortBuffer;
/*   7:    */import org.lwjgl.LWJGLUtil;
/*   8:    */
/*   9:    */public final class EXTVertexShader
/*  10:    */{
/*  11:    */  public static final int GL_VERTEX_SHADER_EXT = 34688;
/*  12:    */  public static final int GL_VERTEX_SHADER_BINDING_EXT = 34689;
/*  13:    */  public static final int GL_OP_INDEX_EXT = 34690;
/*  14:    */  public static final int GL_OP_NEGATE_EXT = 34691;
/*  15:    */  public static final int GL_OP_DOT3_EXT = 34692;
/*  16:    */  public static final int GL_OP_DOT4_EXT = 34693;
/*  17:    */  public static final int GL_OP_MUL_EXT = 34694;
/*  18:    */  public static final int GL_OP_ADD_EXT = 34695;
/*  19:    */  public static final int GL_OP_MADD_EXT = 34696;
/*  20:    */  public static final int GL_OP_FRAC_EXT = 34697;
/*  21:    */  public static final int GL_OP_MAX_EXT = 34698;
/*  22:    */  public static final int GL_OP_MIN_EXT = 34699;
/*  23:    */  public static final int GL_OP_SET_GE_EXT = 34700;
/*  24:    */  public static final int GL_OP_SET_LT_EXT = 34701;
/*  25:    */  public static final int GL_OP_CLAMP_EXT = 34702;
/*  26:    */  public static final int GL_OP_FLOOR_EXT = 34703;
/*  27:    */  public static final int GL_OP_ROUND_EXT = 34704;
/*  28:    */  public static final int GL_OP_EXP_BASE_2_EXT = 34705;
/*  29:    */  public static final int GL_OP_LOG_BASE_2_EXT = 34706;
/*  30:    */  public static final int GL_OP_POWER_EXT = 34707;
/*  31:    */  public static final int GL_OP_RECIP_EXT = 34708;
/*  32:    */  public static final int GL_OP_RECIP_SQRT_EXT = 34709;
/*  33:    */  public static final int GL_OP_SUB_EXT = 34710;
/*  34:    */  public static final int GL_OP_CROSS_PRODUCT_EXT = 34711;
/*  35:    */  public static final int GL_OP_MULTIPLY_MATRIX_EXT = 34712;
/*  36:    */  public static final int GL_OP_MOV_EXT = 34713;
/*  37:    */  public static final int GL_OUTPUT_VERTEX_EXT = 34714;
/*  38:    */  public static final int GL_OUTPUT_COLOR0_EXT = 34715;
/*  39:    */  public static final int GL_OUTPUT_COLOR1_EXT = 34716;
/*  40:    */  public static final int GL_OUTPUT_TEXTURE_COORD0_EXT = 34717;
/*  41:    */  public static final int GL_OUTPUT_TEXTURE_COORD1_EXT = 34718;
/*  42:    */  public static final int GL_OUTPUT_TEXTURE_COORD2_EXT = 34719;
/*  43:    */  public static final int GL_OUTPUT_TEXTURE_COORD3_EXT = 34720;
/*  44:    */  public static final int GL_OUTPUT_TEXTURE_COORD4_EXT = 34721;
/*  45:    */  public static final int GL_OUTPUT_TEXTURE_COORD5_EXT = 34722;
/*  46:    */  public static final int GL_OUTPUT_TEXTURE_COORD6_EXT = 34723;
/*  47:    */  public static final int GL_OUTPUT_TEXTURE_COORD7_EXT = 34724;
/*  48:    */  public static final int GL_OUTPUT_TEXTURE_COORD8_EXT = 34725;
/*  49:    */  public static final int GL_OUTPUT_TEXTURE_COORD9_EXT = 34726;
/*  50:    */  public static final int GL_OUTPUT_TEXTURE_COORD10_EXT = 34727;
/*  51:    */  public static final int GL_OUTPUT_TEXTURE_COORD11_EXT = 34728;
/*  52:    */  public static final int GL_OUTPUT_TEXTURE_COORD12_EXT = 34729;
/*  53:    */  public static final int GL_OUTPUT_TEXTURE_COORD13_EXT = 34730;
/*  54:    */  public static final int GL_OUTPUT_TEXTURE_COORD14_EXT = 34731;
/*  55:    */  public static final int GL_OUTPUT_TEXTURE_COORD15_EXT = 34732;
/*  56:    */  public static final int GL_OUTPUT_TEXTURE_COORD16_EXT = 34733;
/*  57:    */  public static final int GL_OUTPUT_TEXTURE_COORD17_EXT = 34734;
/*  58:    */  public static final int GL_OUTPUT_TEXTURE_COORD18_EXT = 34735;
/*  59:    */  public static final int GL_OUTPUT_TEXTURE_COORD19_EXT = 34736;
/*  60:    */  public static final int GL_OUTPUT_TEXTURE_COORD20_EXT = 34737;
/*  61:    */  public static final int GL_OUTPUT_TEXTURE_COORD21_EXT = 34738;
/*  62:    */  public static final int GL_OUTPUT_TEXTURE_COORD22_EXT = 34739;
/*  63:    */  public static final int GL_OUTPUT_TEXTURE_COORD23_EXT = 34740;
/*  64:    */  public static final int GL_OUTPUT_TEXTURE_COORD24_EXT = 34741;
/*  65:    */  public static final int GL_OUTPUT_TEXTURE_COORD25_EXT = 34742;
/*  66:    */  public static final int GL_OUTPUT_TEXTURE_COORD26_EXT = 34743;
/*  67:    */  public static final int GL_OUTPUT_TEXTURE_COORD27_EXT = 34744;
/*  68:    */  public static final int GL_OUTPUT_TEXTURE_COORD28_EXT = 34745;
/*  69:    */  public static final int GL_OUTPUT_TEXTURE_COORD29_EXT = 34746;
/*  70:    */  public static final int GL_OUTPUT_TEXTURE_COORD30_EXT = 34747;
/*  71:    */  public static final int GL_OUTPUT_TEXTURE_COORD31_EXT = 34748;
/*  72:    */  public static final int GL_OUTPUT_FOG_EXT = 34749;
/*  73:    */  public static final int GL_SCALAR_EXT = 34750;
/*  74:    */  public static final int GL_VECTOR_EXT = 34751;
/*  75:    */  public static final int GL_MATRIX_EXT = 34752;
/*  76:    */  public static final int GL_VARIANT_EXT = 34753;
/*  77:    */  public static final int GL_INVARIANT_EXT = 34754;
/*  78:    */  public static final int GL_LOCAL_CONSTANT_EXT = 34755;
/*  79:    */  public static final int GL_LOCAL_EXT = 34756;
/*  80:    */  public static final int GL_MAX_VERTEX_SHADER_INSTRUCTIONS_EXT = 34757;
/*  81:    */  public static final int GL_MAX_VERTEX_SHADER_VARIANTS_EXT = 34758;
/*  82:    */  public static final int GL_MAX_VERTEX_SHADER_INVARIANTS_EXT = 34759;
/*  83:    */  public static final int GL_MAX_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 34760;
/*  84:    */  public static final int GL_MAX_VERTEX_SHADER_LOCALS_EXT = 34761;
/*  85:    */  public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INSTRUCTIONS_EXT = 34762;
/*  86:    */  public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_VARIANTS_EXT = 34763;
/*  87:    */  public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INVARIANTS_EXT = 34764;
/*  88:    */  public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 34765;
/*  89:    */  public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCALS_EXT = 34766;
/*  90:    */  public static final int GL_VERTEX_SHADER_INSTRUCTIONS_EXT = 34767;
/*  91:    */  public static final int GL_VERTEX_SHADER_VARIANTS_EXT = 34768;
/*  92:    */  public static final int GL_VERTEX_SHADER_INVARIANTS_EXT = 34769;
/*  93:    */  public static final int GL_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 34770;
/*  94:    */  public static final int GL_VERTEX_SHADER_LOCALS_EXT = 34771;
/*  95:    */  public static final int GL_VERTEX_SHADER_OPTIMIZED_EXT = 34772;
/*  96:    */  public static final int GL_X_EXT = 34773;
/*  97:    */  public static final int GL_Y_EXT = 34774;
/*  98:    */  public static final int GL_Z_EXT = 34775;
/*  99:    */  public static final int GL_W_EXT = 34776;
/* 100:    */  public static final int GL_NEGATIVE_X_EXT = 34777;
/* 101:    */  public static final int GL_NEGATIVE_Y_EXT = 34778;
/* 102:    */  public static final int GL_NEGATIVE_Z_EXT = 34779;
/* 103:    */  public static final int GL_NEGATIVE_W_EXT = 34780;
/* 104:    */  public static final int GL_ZERO_EXT = 34781;
/* 105:    */  public static final int GL_ONE_EXT = 34782;
/* 106:    */  public static final int GL_NEGATIVE_ONE_EXT = 34783;
/* 107:    */  public static final int GL_NORMALIZED_RANGE_EXT = 34784;
/* 108:    */  public static final int GL_FULL_RANGE_EXT = 34785;
/* 109:    */  public static final int GL_CURRENT_VERTEX_EXT = 34786;
/* 110:    */  public static final int GL_MVP_MATRIX_EXT = 34787;
/* 111:    */  public static final int GL_VARIANT_VALUE_EXT = 34788;
/* 112:    */  public static final int GL_VARIANT_DATATYPE_EXT = 34789;
/* 113:    */  public static final int GL_VARIANT_ARRAY_STRIDE_EXT = 34790;
/* 114:    */  public static final int GL_VARIANT_ARRAY_TYPE_EXT = 34791;
/* 115:    */  public static final int GL_VARIANT_ARRAY_EXT = 34792;
/* 116:    */  public static final int GL_VARIANT_ARRAY_POINTER_EXT = 34793;
/* 117:    */  public static final int GL_INVARIANT_VALUE_EXT = 34794;
/* 118:    */  public static final int GL_INVARIANT_DATATYPE_EXT = 34795;
/* 119:    */  public static final int GL_LOCAL_CONSTANT_VALUE_EXT = 34796;
/* 120:    */  public static final int GL_LOCAL_CONSTANT_DATATYPE_EXT = 34797;
/* 121:    */  
/* 122:    */  public static void glBeginVertexShaderEXT()
/* 123:    */  {
/* 124:124 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 125:125 */    long function_pointer = caps.glBeginVertexShaderEXT;
/* 126:126 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 127:127 */    nglBeginVertexShaderEXT(function_pointer);
/* 128:    */  }
/* 129:    */  
/* 130:    */  static native void nglBeginVertexShaderEXT(long paramLong);
/* 131:    */  
/* 132:132 */  public static void glEndVertexShaderEXT() { ContextCapabilities caps = GLContext.getCapabilities();
/* 133:133 */    long function_pointer = caps.glEndVertexShaderEXT;
/* 134:134 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 135:135 */    nglEndVertexShaderEXT(function_pointer);
/* 136:    */  }
/* 137:    */  
/* 138:    */  static native void nglEndVertexShaderEXT(long paramLong);
/* 139:    */  
/* 140:140 */  public static void glBindVertexShaderEXT(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 141:141 */    long function_pointer = caps.glBindVertexShaderEXT;
/* 142:142 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 143:143 */    nglBindVertexShaderEXT(id, function_pointer);
/* 144:    */  }
/* 145:    */  
/* 146:    */  static native void nglBindVertexShaderEXT(int paramInt, long paramLong);
/* 147:    */  
/* 148:148 */  public static int glGenVertexShadersEXT(int range) { ContextCapabilities caps = GLContext.getCapabilities();
/* 149:149 */    long function_pointer = caps.glGenVertexShadersEXT;
/* 150:150 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 151:151 */    int __result = nglGenVertexShadersEXT(range, function_pointer);
/* 152:152 */    return __result;
/* 153:    */  }
/* 154:    */  
/* 155:    */  static native int nglGenVertexShadersEXT(int paramInt, long paramLong);
/* 156:    */  
/* 157:157 */  public static void glDeleteVertexShaderEXT(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 158:158 */    long function_pointer = caps.glDeleteVertexShaderEXT;
/* 159:159 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 160:160 */    nglDeleteVertexShaderEXT(id, function_pointer);
/* 161:    */  }
/* 162:    */  
/* 163:    */  static native void nglDeleteVertexShaderEXT(int paramInt, long paramLong);
/* 164:    */  
/* 165:165 */  public static void glShaderOp1EXT(int op, int res, int arg1) { ContextCapabilities caps = GLContext.getCapabilities();
/* 166:166 */    long function_pointer = caps.glShaderOp1EXT;
/* 167:167 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 168:168 */    nglShaderOp1EXT(op, res, arg1, function_pointer);
/* 169:    */  }
/* 170:    */  
/* 171:    */  static native void nglShaderOp1EXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 172:    */  
/* 173:173 */  public static void glShaderOp2EXT(int op, int res, int arg1, int arg2) { ContextCapabilities caps = GLContext.getCapabilities();
/* 174:174 */    long function_pointer = caps.glShaderOp2EXT;
/* 175:175 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 176:176 */    nglShaderOp2EXT(op, res, arg1, arg2, function_pointer);
/* 177:    */  }
/* 178:    */  
/* 179:    */  static native void nglShaderOp2EXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 180:    */  
/* 181:181 */  public static void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3) { ContextCapabilities caps = GLContext.getCapabilities();
/* 182:182 */    long function_pointer = caps.glShaderOp3EXT;
/* 183:183 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 184:184 */    nglShaderOp3EXT(op, res, arg1, arg2, arg3, function_pointer);
/* 185:    */  }
/* 186:    */  
/* 187:    */  static native void nglShaderOp3EXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 188:    */  
/* 189:189 */  public static void glSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW) { ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glSwizzleEXT;
/* 191:191 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    nglSwizzleEXT(res, in, outX, outY, outZ, outW, function_pointer);
/* 193:    */  }
/* 194:    */  
/* 195:    */  static native void nglSwizzleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 196:    */  
/* 197:197 */  public static void glWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW) { ContextCapabilities caps = GLContext.getCapabilities();
/* 198:198 */    long function_pointer = caps.glWriteMaskEXT;
/* 199:199 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 200:200 */    nglWriteMaskEXT(res, in, outX, outY, outZ, outW, function_pointer);
/* 201:    */  }
/* 202:    */  
/* 203:    */  static native void nglWriteMaskEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 204:    */  
/* 205:205 */  public static void glInsertComponentEXT(int res, int src, int num) { ContextCapabilities caps = GLContext.getCapabilities();
/* 206:206 */    long function_pointer = caps.glInsertComponentEXT;
/* 207:207 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 208:208 */    nglInsertComponentEXT(res, src, num, function_pointer);
/* 209:    */  }
/* 210:    */  
/* 211:    */  static native void nglInsertComponentEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 212:    */  
/* 213:213 */  public static void glExtractComponentEXT(int res, int src, int num) { ContextCapabilities caps = GLContext.getCapabilities();
/* 214:214 */    long function_pointer = caps.glExtractComponentEXT;
/* 215:215 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 216:216 */    nglExtractComponentEXT(res, src, num, function_pointer);
/* 217:    */  }
/* 218:    */  
/* 219:    */  static native void nglExtractComponentEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 220:    */  
/* 221:221 */  public static int glGenSymbolsEXT(int dataType, int storageType, int range, int components) { ContextCapabilities caps = GLContext.getCapabilities();
/* 222:222 */    long function_pointer = caps.glGenSymbolsEXT;
/* 223:223 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 224:224 */    int __result = nglGenSymbolsEXT(dataType, storageType, range, components, function_pointer);
/* 225:225 */    return __result;
/* 226:    */  }
/* 227:    */  
/* 228:    */  static native int nglGenSymbolsEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 229:    */  
/* 230:230 */  public static void glSetInvariantEXT(int id, DoubleBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 231:231 */    long function_pointer = caps.glSetInvariantEXT;
/* 232:232 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 233:233 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 234:234 */    nglSetInvariantEXT(id, 5130, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 235:    */  }
/* 236:    */  
/* 237:237 */  public static void glSetInvariantEXT(int id, FloatBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 238:238 */    long function_pointer = caps.glSetInvariantEXT;
/* 239:239 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 240:240 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 241:241 */    nglSetInvariantEXT(id, 5126, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 242:    */  }
/* 243:    */  
/* 244:244 */  public static void glSetInvariantEXT(int id, boolean unsigned, java.nio.ByteBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 245:245 */    long function_pointer = caps.glSetInvariantEXT;
/* 246:246 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 247:247 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 248:248 */    nglSetInvariantEXT(id, unsigned ? 5121 : 5120, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 249:    */  }
/* 250:    */  
/* 251:251 */  public static void glSetInvariantEXT(int id, boolean unsigned, java.nio.IntBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 252:252 */    long function_pointer = caps.glSetInvariantEXT;
/* 253:253 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 254:254 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 255:255 */    nglSetInvariantEXT(id, unsigned ? 5125 : 5124, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 256:    */  }
/* 257:    */  
/* 258:258 */  public static void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 259:259 */    long function_pointer = caps.glSetInvariantEXT;
/* 260:260 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 261:261 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 262:262 */    nglSetInvariantEXT(id, unsigned ? 5123 : 5122, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 263:    */  }
/* 264:    */  
/* 265:    */  static native void nglSetInvariantEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 266:    */  
/* 267:267 */  public static void glSetLocalConstantEXT(int id, DoubleBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 268:268 */    long function_pointer = caps.glSetLocalConstantEXT;
/* 269:269 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 270:270 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 271:271 */    nglSetLocalConstantEXT(id, 5130, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 272:    */  }
/* 273:    */  
/* 274:274 */  public static void glSetLocalConstantEXT(int id, FloatBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 275:275 */    long function_pointer = caps.glSetLocalConstantEXT;
/* 276:276 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 277:277 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 278:278 */    nglSetLocalConstantEXT(id, 5126, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 279:    */  }
/* 280:    */  
/* 281:281 */  public static void glSetLocalConstantEXT(int id, boolean unsigned, java.nio.ByteBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 282:282 */    long function_pointer = caps.glSetLocalConstantEXT;
/* 283:283 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 284:284 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 285:285 */    nglSetLocalConstantEXT(id, unsigned ? 5121 : 5120, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 286:    */  }
/* 287:    */  
/* 288:288 */  public static void glSetLocalConstantEXT(int id, boolean unsigned, java.nio.IntBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 289:289 */    long function_pointer = caps.glSetLocalConstantEXT;
/* 290:290 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 291:291 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 292:292 */    nglSetLocalConstantEXT(id, unsigned ? 5125 : 5124, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 293:    */  }
/* 294:    */  
/* 295:295 */  public static void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 296:296 */    long function_pointer = caps.glSetLocalConstantEXT;
/* 297:297 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 298:298 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 299:299 */    nglSetLocalConstantEXT(id, unsigned ? 5123 : 5122, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 300:    */  }
/* 301:    */  
/* 302:    */  static native void nglSetLocalConstantEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 303:    */  
/* 304:304 */  public static void glVariantEXT(int id, java.nio.ByteBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 305:305 */    long function_pointer = caps.glVariantbvEXT;
/* 306:306 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 307:307 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 308:308 */    nglVariantbvEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 309:    */  }
/* 310:    */  
/* 311:    */  static native void nglVariantbvEXT(int paramInt, long paramLong1, long paramLong2);
/* 312:    */  
/* 313:313 */  public static void glVariantEXT(int id, ShortBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 314:314 */    long function_pointer = caps.glVariantsvEXT;
/* 315:315 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 316:316 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 317:317 */    nglVariantsvEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 318:    */  }
/* 319:    */  
/* 320:    */  static native void nglVariantsvEXT(int paramInt, long paramLong1, long paramLong2);
/* 321:    */  
/* 322:322 */  public static void glVariantEXT(int id, java.nio.IntBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 323:323 */    long function_pointer = caps.glVariantivEXT;
/* 324:324 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 325:325 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 326:326 */    nglVariantivEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 327:    */  }
/* 328:    */  
/* 329:    */  static native void nglVariantivEXT(int paramInt, long paramLong1, long paramLong2);
/* 330:    */  
/* 331:331 */  public static void glVariantEXT(int id, FloatBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 332:332 */    long function_pointer = caps.glVariantfvEXT;
/* 333:333 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 334:334 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 335:335 */    nglVariantfvEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 336:    */  }
/* 337:    */  
/* 338:    */  static native void nglVariantfvEXT(int paramInt, long paramLong1, long paramLong2);
/* 339:    */  
/* 340:340 */  public static void glVariantEXT(int id, DoubleBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 341:341 */    long function_pointer = caps.glVariantdvEXT;
/* 342:342 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 343:343 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 344:344 */    nglVariantdvEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 345:    */  }
/* 346:    */  
/* 347:    */  static native void nglVariantdvEXT(int paramInt, long paramLong1, long paramLong2);
/* 348:    */  
/* 349:349 */  public static void glVariantuEXT(int id, java.nio.ByteBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 350:350 */    long function_pointer = caps.glVariantubvEXT;
/* 351:351 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 352:352 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 353:353 */    nglVariantubvEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 354:    */  }
/* 355:    */  
/* 356:    */  static native void nglVariantubvEXT(int paramInt, long paramLong1, long paramLong2);
/* 357:    */  
/* 358:358 */  public static void glVariantuEXT(int id, ShortBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 359:359 */    long function_pointer = caps.glVariantusvEXT;
/* 360:360 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 361:361 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 362:362 */    nglVariantusvEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 363:    */  }
/* 364:    */  
/* 365:    */  static native void nglVariantusvEXT(int paramInt, long paramLong1, long paramLong2);
/* 366:    */  
/* 367:367 */  public static void glVariantuEXT(int id, java.nio.IntBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 368:368 */    long function_pointer = caps.glVariantuivEXT;
/* 369:369 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 370:370 */    org.lwjgl.BufferChecks.checkBuffer(pAddr, 4);
/* 371:371 */    nglVariantuivEXT(id, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 372:    */  }
/* 373:    */  
/* 374:    */  static native void nglVariantuivEXT(int paramInt, long paramLong1, long paramLong2);
/* 375:    */  
/* 376:376 */  public static void glVariantPointerEXT(int id, int stride, DoubleBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 377:377 */    long function_pointer = caps.glVariantPointerEXT;
/* 378:378 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 379:379 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 380:380 */    org.lwjgl.BufferChecks.checkDirect(pAddr);
/* 381:381 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 382:382 */    nglVariantPointerEXT(id, 5130, stride, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 383:    */  }
/* 384:    */  
/* 385:385 */  public static void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 386:386 */    long function_pointer = caps.glVariantPointerEXT;
/* 387:387 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 388:388 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 389:389 */    org.lwjgl.BufferChecks.checkDirect(pAddr);
/* 390:390 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 391:391 */    nglVariantPointerEXT(id, 5126, stride, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 392:    */  }
/* 393:    */  
/* 394:394 */  public static void glVariantPointerEXT(int id, boolean unsigned, int stride, java.nio.ByteBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 395:395 */    long function_pointer = caps.glVariantPointerEXT;
/* 396:396 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 397:397 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 398:398 */    org.lwjgl.BufferChecks.checkDirect(pAddr);
/* 399:399 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 400:400 */    nglVariantPointerEXT(id, unsigned ? 5121 : 5120, stride, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 401:    */  }
/* 402:    */  
/* 403:403 */  public static void glVariantPointerEXT(int id, boolean unsigned, int stride, java.nio.IntBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 404:404 */    long function_pointer = caps.glVariantPointerEXT;
/* 405:405 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 406:406 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 407:407 */    org.lwjgl.BufferChecks.checkDirect(pAddr);
/* 408:408 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 409:409 */    nglVariantPointerEXT(id, unsigned ? 5125 : 5124, stride, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer);
/* 410:    */  }
/* 411:    */  
/* 412:412 */  public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) { ContextCapabilities caps = GLContext.getCapabilities();
/* 413:413 */    long function_pointer = caps.glVariantPointerEXT;
/* 414:414 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 415:415 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 416:416 */    org.lwjgl.BufferChecks.checkDirect(pAddr);
/* 417:417 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
/* 418:418 */    nglVariantPointerEXT(id, unsigned ? 5123 : 5122, stride, org.lwjgl.MemoryUtil.getAddress(pAddr), function_pointer); }
/* 419:    */  
/* 420:    */  static native void nglVariantPointerEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 421:    */  
/* 422:422 */  public static void glVariantPointerEXT(int id, int type, int stride, long pAddr_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 423:423 */    long function_pointer = caps.glVariantPointerEXT;
/* 424:424 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 425:425 */    GLChecks.ensureArrayVBOenabled(caps);
/* 426:426 */    nglVariantPointerEXTBO(id, type, stride, pAddr_buffer_offset, function_pointer);
/* 427:    */  }
/* 428:    */  
/* 429:    */  static native void nglVariantPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 430:    */  
/* 431:431 */  public static void glEnableVariantClientStateEXT(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 432:432 */    long function_pointer = caps.glEnableVariantClientStateEXT;
/* 433:433 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 434:434 */    nglEnableVariantClientStateEXT(id, function_pointer);
/* 435:    */  }
/* 436:    */  
/* 437:    */  static native void nglEnableVariantClientStateEXT(int paramInt, long paramLong);
/* 438:    */  
/* 439:439 */  public static void glDisableVariantClientStateEXT(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 440:440 */    long function_pointer = caps.glDisableVariantClientStateEXT;
/* 441:441 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 442:442 */    nglDisableVariantClientStateEXT(id, function_pointer);
/* 443:    */  }
/* 444:    */  
/* 445:    */  static native void nglDisableVariantClientStateEXT(int paramInt, long paramLong);
/* 446:    */  
/* 447:447 */  public static int glBindLightParameterEXT(int light, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 448:448 */    long function_pointer = caps.glBindLightParameterEXT;
/* 449:449 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 450:450 */    int __result = nglBindLightParameterEXT(light, value, function_pointer);
/* 451:451 */    return __result;
/* 452:    */  }
/* 453:    */  
/* 454:    */  static native int nglBindLightParameterEXT(int paramInt1, int paramInt2, long paramLong);
/* 455:    */  
/* 456:456 */  public static int glBindMaterialParameterEXT(int face, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 457:457 */    long function_pointer = caps.glBindMaterialParameterEXT;
/* 458:458 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 459:459 */    int __result = nglBindMaterialParameterEXT(face, value, function_pointer);
/* 460:460 */    return __result;
/* 461:    */  }
/* 462:    */  
/* 463:    */  static native int nglBindMaterialParameterEXT(int paramInt1, int paramInt2, long paramLong);
/* 464:    */  
/* 465:465 */  public static int glBindTexGenParameterEXT(int unit, int coord, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 466:466 */    long function_pointer = caps.glBindTexGenParameterEXT;
/* 467:467 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 468:468 */    int __result = nglBindTexGenParameterEXT(unit, coord, value, function_pointer);
/* 469:469 */    return __result;
/* 470:    */  }
/* 471:    */  
/* 472:    */  static native int nglBindTexGenParameterEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 473:    */  
/* 474:474 */  public static int glBindTextureUnitParameterEXT(int unit, int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 475:475 */    long function_pointer = caps.glBindTextureUnitParameterEXT;
/* 476:476 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 477:477 */    int __result = nglBindTextureUnitParameterEXT(unit, value, function_pointer);
/* 478:478 */    return __result;
/* 479:    */  }
/* 480:    */  
/* 481:    */  static native int nglBindTextureUnitParameterEXT(int paramInt1, int paramInt2, long paramLong);
/* 482:    */  
/* 483:483 */  public static int glBindParameterEXT(int value) { ContextCapabilities caps = GLContext.getCapabilities();
/* 484:484 */    long function_pointer = caps.glBindParameterEXT;
/* 485:485 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 486:486 */    int __result = nglBindParameterEXT(value, function_pointer);
/* 487:487 */    return __result;
/* 488:    */  }
/* 489:    */  
/* 490:    */  static native int nglBindParameterEXT(int paramInt, long paramLong);
/* 491:    */  
/* 492:492 */  public static boolean glIsVariantEnabledEXT(int id, int cap) { ContextCapabilities caps = GLContext.getCapabilities();
/* 493:493 */    long function_pointer = caps.glIsVariantEnabledEXT;
/* 494:494 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 495:495 */    boolean __result = nglIsVariantEnabledEXT(id, cap, function_pointer);
/* 496:496 */    return __result;
/* 497:    */  }
/* 498:    */  
/* 499:    */  static native boolean nglIsVariantEnabledEXT(int paramInt1, int paramInt2, long paramLong);
/* 500:    */  
/* 501:501 */  public static void glGetVariantBooleanEXT(int id, int value, java.nio.ByteBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 502:502 */    long function_pointer = caps.glGetVariantBooleanvEXT;
/* 503:503 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 504:504 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 505:505 */    nglGetVariantBooleanvEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 506:    */  }
/* 507:    */  
/* 508:    */  static native void nglGetVariantBooleanvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 509:    */  
/* 510:510 */  public static void glGetVariantIntegerEXT(int id, int value, java.nio.IntBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 511:511 */    long function_pointer = caps.glGetVariantIntegervEXT;
/* 512:512 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 513:513 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 514:514 */    nglGetVariantIntegervEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 515:    */  }
/* 516:    */  
/* 517:    */  static native void nglGetVariantIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 518:    */  
/* 519:519 */  public static void glGetVariantFloatEXT(int id, int value, FloatBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 520:520 */    long function_pointer = caps.glGetVariantFloatvEXT;
/* 521:521 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 522:522 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 523:523 */    nglGetVariantFloatvEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 524:    */  }
/* 525:    */  
/* 526:    */  static native void nglGetVariantFloatvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 527:    */  
/* 528:528 */  public static java.nio.ByteBuffer glGetVariantPointerEXT(int id, int value, long result_size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 529:529 */    long function_pointer = caps.glGetVariantPointervEXT;
/* 530:530 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 531:531 */    java.nio.ByteBuffer __result = nglGetVariantPointervEXT(id, value, result_size, function_pointer);
/* 532:532 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 533:    */  }
/* 534:    */  
/* 535:    */  static native java.nio.ByteBuffer nglGetVariantPointervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 536:    */  
/* 537:537 */  public static void glGetInvariantBooleanEXT(int id, int value, java.nio.ByteBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 538:538 */    long function_pointer = caps.glGetInvariantBooleanvEXT;
/* 539:539 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 540:540 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 541:541 */    nglGetInvariantBooleanvEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 542:    */  }
/* 543:    */  
/* 544:    */  static native void nglGetInvariantBooleanvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 545:    */  
/* 546:546 */  public static void glGetInvariantIntegerEXT(int id, int value, java.nio.IntBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 547:547 */    long function_pointer = caps.glGetInvariantIntegervEXT;
/* 548:548 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 549:549 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 550:550 */    nglGetInvariantIntegervEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 551:    */  }
/* 552:    */  
/* 553:    */  static native void nglGetInvariantIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 554:    */  
/* 555:555 */  public static void glGetInvariantFloatEXT(int id, int value, FloatBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 556:556 */    long function_pointer = caps.glGetInvariantFloatvEXT;
/* 557:557 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 558:558 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 559:559 */    nglGetInvariantFloatvEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 560:    */  }
/* 561:    */  
/* 562:    */  static native void nglGetInvariantFloatvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 563:    */  
/* 564:564 */  public static void glGetLocalConstantBooleanEXT(int id, int value, java.nio.ByteBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 565:565 */    long function_pointer = caps.glGetLocalConstantBooleanvEXT;
/* 566:566 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 567:567 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 568:568 */    nglGetLocalConstantBooleanvEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 569:    */  }
/* 570:    */  
/* 571:    */  static native void nglGetLocalConstantBooleanvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 572:    */  
/* 573:573 */  public static void glGetLocalConstantIntegerEXT(int id, int value, java.nio.IntBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 574:574 */    long function_pointer = caps.glGetLocalConstantIntegervEXT;
/* 575:575 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 576:576 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 577:577 */    nglGetLocalConstantIntegervEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 578:    */  }
/* 579:    */  
/* 580:    */  static native void nglGetLocalConstantIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 581:    */  
/* 582:582 */  public static void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pbData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 583:583 */    long function_pointer = caps.glGetLocalConstantFloatvEXT;
/* 584:584 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 585:585 */    org.lwjgl.BufferChecks.checkBuffer(pbData, 4);
/* 586:586 */    nglGetLocalConstantFloatvEXT(id, value, org.lwjgl.MemoryUtil.getAddress(pbData), function_pointer);
/* 587:    */  }
/* 588:    */  
/* 589:    */  static native void nglGetLocalConstantFloatvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 590:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTVertexShader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */