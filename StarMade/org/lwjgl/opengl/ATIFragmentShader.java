/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*   9:    */public final class ATIFragmentShader
/*  10:    */{
/*  11:    */  public static final int GL_FRAGMENT_SHADER_ATI = 35104;
/*  12:    */  public static final int GL_REG_0_ATI = 35105;
/*  13:    */  public static final int GL_REG_1_ATI = 35106;
/*  14:    */  public static final int GL_REG_2_ATI = 35107;
/*  15:    */  public static final int GL_REG_3_ATI = 35108;
/*  16:    */  public static final int GL_REG_4_ATI = 35109;
/*  17:    */  public static final int GL_REG_5_ATI = 35110;
/*  18:    */  public static final int GL_REG_6_ATI = 35111;
/*  19:    */  public static final int GL_REG_7_ATI = 35112;
/*  20:    */  public static final int GL_REG_8_ATI = 35113;
/*  21:    */  public static final int GL_REG_9_ATI = 35114;
/*  22:    */  public static final int GL_REG_10_ATI = 35115;
/*  23:    */  public static final int GL_REG_11_ATI = 35116;
/*  24:    */  public static final int GL_REG_12_ATI = 35117;
/*  25:    */  public static final int GL_REG_13_ATI = 35118;
/*  26:    */  public static final int GL_REG_14_ATI = 35119;
/*  27:    */  public static final int GL_REG_15_ATI = 35120;
/*  28:    */  public static final int GL_REG_16_ATI = 35121;
/*  29:    */  public static final int GL_REG_17_ATI = 35122;
/*  30:    */  public static final int GL_REG_18_ATI = 35123;
/*  31:    */  public static final int GL_REG_19_ATI = 35124;
/*  32:    */  public static final int GL_REG_20_ATI = 35125;
/*  33:    */  public static final int GL_REG_21_ATI = 35126;
/*  34:    */  public static final int GL_REG_22_ATI = 35127;
/*  35:    */  public static final int GL_REG_23_ATI = 35128;
/*  36:    */  public static final int GL_REG_24_ATI = 35129;
/*  37:    */  public static final int GL_REG_25_ATI = 35130;
/*  38:    */  public static final int GL_REG_26_ATI = 35131;
/*  39:    */  public static final int GL_REG_27_ATI = 35132;
/*  40:    */  public static final int GL_REG_28_ATI = 35133;
/*  41:    */  public static final int GL_REG_29_ATI = 35134;
/*  42:    */  public static final int GL_REG_30_ATI = 35135;
/*  43:    */  public static final int GL_REG_31_ATI = 35136;
/*  44:    */  public static final int GL_CON_0_ATI = 35137;
/*  45:    */  public static final int GL_CON_1_ATI = 35138;
/*  46:    */  public static final int GL_CON_2_ATI = 35139;
/*  47:    */  public static final int GL_CON_3_ATI = 35140;
/*  48:    */  public static final int GL_CON_4_ATI = 35141;
/*  49:    */  public static final int GL_CON_5_ATI = 35142;
/*  50:    */  public static final int GL_CON_6_ATI = 35143;
/*  51:    */  public static final int GL_CON_7_ATI = 35144;
/*  52:    */  public static final int GL_CON_8_ATI = 35145;
/*  53:    */  public static final int GL_CON_9_ATI = 35146;
/*  54:    */  public static final int GL_CON_10_ATI = 35147;
/*  55:    */  public static final int GL_CON_11_ATI = 35148;
/*  56:    */  public static final int GL_CON_12_ATI = 35149;
/*  57:    */  public static final int GL_CON_13_ATI = 35150;
/*  58:    */  public static final int GL_CON_14_ATI = 35151;
/*  59:    */  public static final int GL_CON_15_ATI = 35152;
/*  60:    */  public static final int GL_CON_16_ATI = 35153;
/*  61:    */  public static final int GL_CON_17_ATI = 35154;
/*  62:    */  public static final int GL_CON_18_ATI = 35155;
/*  63:    */  public static final int GL_CON_19_ATI = 35156;
/*  64:    */  public static final int GL_CON_20_ATI = 35157;
/*  65:    */  public static final int GL_CON_21_ATI = 35158;
/*  66:    */  public static final int GL_CON_22_ATI = 35159;
/*  67:    */  public static final int GL_CON_23_ATI = 35160;
/*  68:    */  public static final int GL_CON_24_ATI = 35161;
/*  69:    */  public static final int GL_CON_25_ATI = 35162;
/*  70:    */  public static final int GL_CON_26_ATI = 35163;
/*  71:    */  public static final int GL_CON_27_ATI = 35164;
/*  72:    */  public static final int GL_CON_28_ATI = 35165;
/*  73:    */  public static final int GL_CON_29_ATI = 35166;
/*  74:    */  public static final int GL_CON_30_ATI = 35167;
/*  75:    */  public static final int GL_CON_31_ATI = 35168;
/*  76:    */  public static final int GL_MOV_ATI = 35169;
/*  77:    */  public static final int GL_ADD_ATI = 35171;
/*  78:    */  public static final int GL_MUL_ATI = 35172;
/*  79:    */  public static final int GL_SUB_ATI = 35173;
/*  80:    */  public static final int GL_DOT3_ATI = 35174;
/*  81:    */  public static final int GL_DOT4_ATI = 35175;
/*  82:    */  public static final int GL_MAD_ATI = 35176;
/*  83:    */  public static final int GL_LERP_ATI = 35177;
/*  84:    */  public static final int GL_CND_ATI = 35178;
/*  85:    */  public static final int GL_CND0_ATI = 35179;
/*  86:    */  public static final int GL_DOT2_ADD_ATI = 35180;
/*  87:    */  public static final int GL_SECONDARY_INTERPOLATOR_ATI = 35181;
/*  88:    */  public static final int GL_NUM_FRAGMENT_REGISTERS_ATI = 35182;
/*  89:    */  public static final int GL_NUM_FRAGMENT_CONSTANTS_ATI = 35183;
/*  90:    */  public static final int GL_NUM_PASSES_ATI = 35184;
/*  91:    */  public static final int GL_NUM_INSTRUCTIONS_PER_PASS_ATI = 35185;
/*  92:    */  public static final int GL_NUM_INSTRUCTIONS_TOTAL_ATI = 35186;
/*  93:    */  public static final int GL_NUM_INPUT_INTERPOLATOR_COMPONENTS_ATI = 35187;
/*  94:    */  public static final int GL_NUM_LOOPBACK_COMPONENTS_ATI = 35188;
/*  95:    */  public static final int GL_COLOR_ALPHA_PAIRING_ATI = 35189;
/*  96:    */  public static final int GL_SWIZZLE_STR_ATI = 35190;
/*  97:    */  public static final int GL_SWIZZLE_STQ_ATI = 35191;
/*  98:    */  public static final int GL_SWIZZLE_STR_DR_ATI = 35192;
/*  99:    */  public static final int GL_SWIZZLE_STQ_DQ_ATI = 35193;
/* 100:    */  public static final int GL_SWIZZLE_STRQ_ATI = 35194;
/* 101:    */  public static final int GL_SWIZZLE_STRQ_DQ_ATI = 35195;
/* 102:    */  public static final int GL_RED_BIT_ATI = 1;
/* 103:    */  public static final int GL_GREEN_BIT_ATI = 2;
/* 104:    */  public static final int GL_BLUE_BIT_ATI = 4;
/* 105:    */  public static final int GL_2X_BIT_ATI = 1;
/* 106:    */  public static final int GL_4X_BIT_ATI = 2;
/* 107:    */  public static final int GL_8X_BIT_ATI = 4;
/* 108:    */  public static final int GL_HALF_BIT_ATI = 8;
/* 109:    */  public static final int GL_QUARTER_BIT_ATI = 16;
/* 110:    */  public static final int GL_EIGHTH_BIT_ATI = 32;
/* 111:    */  public static final int GL_SATURATE_BIT_ATI = 64;
/* 112:    */  public static final int GL_COMP_BIT_ATI = 2;
/* 113:    */  public static final int GL_NEGATE_BIT_ATI = 4;
/* 114:    */  public static final int GL_BIAS_BIT_ATI = 8;
/* 115:    */  
/* 116:    */  public static int glGenFragmentShadersATI(int range)
/* 117:    */  {
/* 118:118 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 119:119 */    long function_pointer = caps.glGenFragmentShadersATI;
/* 120:120 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 121:121 */    int __result = nglGenFragmentShadersATI(range, function_pointer);
/* 122:122 */    return __result;
/* 123:    */  }
/* 124:    */  
/* 125:    */  static native int nglGenFragmentShadersATI(int paramInt, long paramLong);
/* 126:    */  
/* 127:127 */  public static void glBindFragmentShaderATI(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 128:128 */    long function_pointer = caps.glBindFragmentShaderATI;
/* 129:129 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 130:130 */    nglBindFragmentShaderATI(id, function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:    */  static native void nglBindFragmentShaderATI(int paramInt, long paramLong);
/* 134:    */  
/* 135:135 */  public static void glDeleteFragmentShaderATI(int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glDeleteFragmentShaderATI;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    nglDeleteFragmentShaderATI(id, function_pointer);
/* 139:    */  }
/* 140:    */  
/* 141:    */  static native void nglDeleteFragmentShaderATI(int paramInt, long paramLong);
/* 142:    */  
/* 143:143 */  public static void glBeginFragmentShaderATI() { ContextCapabilities caps = GLContext.getCapabilities();
/* 144:144 */    long function_pointer = caps.glBeginFragmentShaderATI;
/* 145:145 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 146:146 */    nglBeginFragmentShaderATI(function_pointer);
/* 147:    */  }
/* 148:    */  
/* 149:    */  static native void nglBeginFragmentShaderATI(long paramLong);
/* 150:    */  
/* 151:151 */  public static void glEndFragmentShaderATI() { ContextCapabilities caps = GLContext.getCapabilities();
/* 152:152 */    long function_pointer = caps.glEndFragmentShaderATI;
/* 153:153 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 154:154 */    nglEndFragmentShaderATI(function_pointer);
/* 155:    */  }
/* 156:    */  
/* 157:    */  static native void nglEndFragmentShaderATI(long paramLong);
/* 158:    */  
/* 159:159 */  public static void glPassTexCoordATI(int dst, int coord, int swizzle) { ContextCapabilities caps = GLContext.getCapabilities();
/* 160:160 */    long function_pointer = caps.glPassTexCoordATI;
/* 161:161 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 162:162 */    nglPassTexCoordATI(dst, coord, swizzle, function_pointer);
/* 163:    */  }
/* 164:    */  
/* 165:    */  static native void nglPassTexCoordATI(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 166:    */  
/* 167:167 */  public static void glSampleMapATI(int dst, int interp, int swizzle) { ContextCapabilities caps = GLContext.getCapabilities();
/* 168:168 */    long function_pointer = caps.glSampleMapATI;
/* 169:169 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 170:170 */    nglSampleMapATI(dst, interp, swizzle, function_pointer);
/* 171:    */  }
/* 172:    */  
/* 173:    */  static native void nglSampleMapATI(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 174:    */  
/* 175:175 */  public static void glColorFragmentOp1ATI(int op, int dst, int dstMask, int dstMod, int arg1, int arg1Rep, int arg1Mod) { ContextCapabilities caps = GLContext.getCapabilities();
/* 176:176 */    long function_pointer = caps.glColorFragmentOp1ATI;
/* 177:177 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 178:178 */    nglColorFragmentOp1ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, function_pointer);
/* 179:    */  }
/* 180:    */  
/* 181:    */  static native void nglColorFragmentOp1ATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/* 182:    */  
/* 183:183 */  public static void glColorFragmentOp2ATI(int op, int dst, int dstMask, int dstMod, int arg1, int arg1Rep, int arg1Mod, int arg2, int arg2Rep, int arg2Mod) { ContextCapabilities caps = GLContext.getCapabilities();
/* 184:184 */    long function_pointer = caps.glColorFragmentOp2ATI;
/* 185:185 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 186:186 */    nglColorFragmentOp2ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, function_pointer);
/* 187:    */  }
/* 188:    */  
/* 189:    */  static native void nglColorFragmentOp2ATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/* 190:    */  
/* 191:191 */  public static void glColorFragmentOp3ATI(int op, int dst, int dstMask, int dstMod, int arg1, int arg1Rep, int arg1Mod, int arg2, int arg2Rep, int arg2Mod, int arg3, int arg3Rep, int arg3Mod) { ContextCapabilities caps = GLContext.getCapabilities();
/* 192:192 */    long function_pointer = caps.glColorFragmentOp3ATI;
/* 193:193 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 194:194 */    nglColorFragmentOp3ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod, function_pointer);
/* 195:    */  }
/* 196:    */  
/* 197:    */  static native void nglColorFragmentOp3ATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, long paramLong);
/* 198:    */  
/* 199:199 */  public static void glAlphaFragmentOp1ATI(int op, int dst, int dstMod, int arg1, int arg1Rep, int arg1Mod) { ContextCapabilities caps = GLContext.getCapabilities();
/* 200:200 */    long function_pointer = caps.glAlphaFragmentOp1ATI;
/* 201:201 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 202:202 */    nglAlphaFragmentOp1ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, function_pointer);
/* 203:    */  }
/* 204:    */  
/* 205:    */  static native void nglAlphaFragmentOp1ATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 206:    */  
/* 207:207 */  public static void glAlphaFragmentOp2ATI(int op, int dst, int dstMod, int arg1, int arg1Rep, int arg1Mod, int arg2, int arg2Rep, int arg2Mod) { ContextCapabilities caps = GLContext.getCapabilities();
/* 208:208 */    long function_pointer = caps.glAlphaFragmentOp2ATI;
/* 209:209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 210:210 */    nglAlphaFragmentOp2ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, function_pointer);
/* 211:    */  }
/* 212:    */  
/* 213:    */  static native void nglAlphaFragmentOp2ATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/* 214:    */  
/* 215:215 */  public static void glAlphaFragmentOp3ATI(int op, int dst, int dstMod, int arg1, int arg1Rep, int arg1Mod, int arg2, int arg2Rep, int arg2Mod, int arg3, int arg3Rep, int arg3Mod) { ContextCapabilities caps = GLContext.getCapabilities();
/* 216:216 */    long function_pointer = caps.glAlphaFragmentOp3ATI;
/* 217:217 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 218:218 */    nglAlphaFragmentOp3ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod, function_pointer);
/* 219:    */  }
/* 220:    */  
/* 221:    */  static native void nglAlphaFragmentOp3ATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, long paramLong);
/* 222:    */  
/* 223:223 */  public static void glSetFragmentShaderConstantATI(int dst, FloatBuffer pfValue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 224:224 */    long function_pointer = caps.glSetFragmentShaderConstantATI;
/* 225:225 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 226:226 */    BufferChecks.checkBuffer(pfValue, 4);
/* 227:227 */    nglSetFragmentShaderConstantATI(dst, MemoryUtil.getAddress(pfValue), function_pointer);
/* 228:    */  }
/* 229:    */  
/* 230:    */  static native void nglSetFragmentShaderConstantATI(int paramInt, long paramLong1, long paramLong2);
/* 231:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIFragmentShader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */