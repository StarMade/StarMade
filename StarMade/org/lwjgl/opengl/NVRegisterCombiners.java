/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*   9:    */public final class NVRegisterCombiners
/*  10:    */{
/*  11:    */  public static final int GL_REGISTER_COMBINERS_NV = 34082;
/*  12:    */  public static final int GL_COMBINER0_NV = 34128;
/*  13:    */  public static final int GL_COMBINER1_NV = 34129;
/*  14:    */  public static final int GL_COMBINER2_NV = 34130;
/*  15:    */  public static final int GL_COMBINER3_NV = 34131;
/*  16:    */  public static final int GL_COMBINER4_NV = 34132;
/*  17:    */  public static final int GL_COMBINER5_NV = 34133;
/*  18:    */  public static final int GL_COMBINER6_NV = 34134;
/*  19:    */  public static final int GL_COMBINER7_NV = 34135;
/*  20:    */  public static final int GL_VARIABLE_A_NV = 34083;
/*  21:    */  public static final int GL_VARIABLE_B_NV = 34084;
/*  22:    */  public static final int GL_VARIABLE_C_NV = 34085;
/*  23:    */  public static final int GL_VARIABLE_D_NV = 34086;
/*  24:    */  public static final int GL_VARIABLE_E_NV = 34087;
/*  25:    */  public static final int GL_VARIABLE_F_NV = 34088;
/*  26:    */  public static final int GL_VARIABLE_G_NV = 34089;
/*  27:    */  public static final int GL_CONSTANT_COLOR0_NV = 34090;
/*  28:    */  public static final int GL_CONSTANT_COLOR1_NV = 34091;
/*  29:    */  public static final int GL_PRIMARY_COLOR_NV = 34092;
/*  30:    */  public static final int GL_SECONDARY_COLOR_NV = 34093;
/*  31:    */  public static final int GL_SPARE0_NV = 34094;
/*  32:    */  public static final int GL_SPARE1_NV = 34095;
/*  33:    */  public static final int GL_UNSIGNED_IDENTITY_NV = 34102;
/*  34:    */  public static final int GL_UNSIGNED_INVERT_NV = 34103;
/*  35:    */  public static final int GL_EXPAND_NORMAL_NV = 34104;
/*  36:    */  public static final int GL_EXPAND_NEGATE_NV = 34105;
/*  37:    */  public static final int GL_HALF_BIAS_NORMAL_NV = 34106;
/*  38:    */  public static final int GL_HALF_BIAS_NEGATE_NV = 34107;
/*  39:    */  public static final int GL_SIGNED_IDENTITY_NV = 34108;
/*  40:    */  public static final int GL_SIGNED_NEGATE_NV = 34109;
/*  41:    */  public static final int GL_E_TIMES_F_NV = 34097;
/*  42:    */  public static final int GL_SPARE0_PLUS_SECONDARY_COLOR_NV = 34098;
/*  43:    */  public static final int GL_SCALE_BY_TWO_NV = 34110;
/*  44:    */  public static final int GL_SCALE_BY_FOUR_NV = 34111;
/*  45:    */  public static final int GL_SCALE_BY_ONE_HALF_NV = 34112;
/*  46:    */  public static final int GL_BIAS_BY_NEGATIVE_ONE_HALF_NV = 34113;
/*  47:    */  public static final int GL_DISCARD_NV = 34096;
/*  48:    */  public static final int GL_COMBINER_INPUT_NV = 34114;
/*  49:    */  public static final int GL_COMBINER_MAPPING_NV = 34115;
/*  50:    */  public static final int GL_COMBINER_COMPONENT_USAGE_NV = 34116;
/*  51:    */  public static final int GL_COMBINER_AB_DOT_PRODUCT_NV = 34117;
/*  52:    */  public static final int GL_COMBINER_CD_DOT_PRODUCT_NV = 34118;
/*  53:    */  public static final int GL_COMBINER_MUX_SUM_NV = 34119;
/*  54:    */  public static final int GL_COMBINER_SCALE_NV = 34120;
/*  55:    */  public static final int GL_COMBINER_BIAS_NV = 34121;
/*  56:    */  public static final int GL_COMBINER_AB_OUTPUT_NV = 34122;
/*  57:    */  public static final int GL_COMBINER_CD_OUTPUT_NV = 34123;
/*  58:    */  public static final int GL_COMBINER_SUM_OUTPUT_NV = 34124;
/*  59:    */  public static final int GL_NUM_GENERAL_COMBINERS_NV = 34126;
/*  60:    */  public static final int GL_COLOR_SUM_CLAMP_NV = 34127;
/*  61:    */  public static final int GL_MAX_GENERAL_COMBINERS_NV = 34125;
/*  62:    */  
/*  63:    */  public static void glCombinerParameterfNV(int pname, float param)
/*  64:    */  {
/*  65: 65 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  66: 66 */    long function_pointer = caps.glCombinerParameterfNV;
/*  67: 67 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  68: 68 */    nglCombinerParameterfNV(pname, param, function_pointer);
/*  69:    */  }
/*  70:    */  
/*  71:    */  static native void nglCombinerParameterfNV(int paramInt, float paramFloat, long paramLong);
/*  72:    */  
/*  73: 73 */  public static void glCombinerParameterNV(int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  74: 74 */    long function_pointer = caps.glCombinerParameterfvNV;
/*  75: 75 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  76: 76 */    BufferChecks.checkBuffer(params, 4);
/*  77: 77 */    nglCombinerParameterfvNV(pname, MemoryUtil.getAddress(params), function_pointer);
/*  78:    */  }
/*  79:    */  
/*  80:    */  static native void nglCombinerParameterfvNV(int paramInt, long paramLong1, long paramLong2);
/*  81:    */  
/*  82: 82 */  public static void glCombinerParameteriNV(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/*  83: 83 */    long function_pointer = caps.glCombinerParameteriNV;
/*  84: 84 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  85: 85 */    nglCombinerParameteriNV(pname, param, function_pointer);
/*  86:    */  }
/*  87:    */  
/*  88:    */  static native void nglCombinerParameteriNV(int paramInt1, int paramInt2, long paramLong);
/*  89:    */  
/*  90: 90 */  public static void glCombinerParameterNV(int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  91: 91 */    long function_pointer = caps.glCombinerParameterivNV;
/*  92: 92 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  93: 93 */    BufferChecks.checkBuffer(params, 4);
/*  94: 94 */    nglCombinerParameterivNV(pname, MemoryUtil.getAddress(params), function_pointer);
/*  95:    */  }
/*  96:    */  
/*  97:    */  static native void nglCombinerParameterivNV(int paramInt, long paramLong1, long paramLong2);
/*  98:    */  
/*  99: 99 */  public static void glCombinerInputNV(int stage, int portion, int variable, int input, int mapping, int componentUsage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 100:100 */    long function_pointer = caps.glCombinerInputNV;
/* 101:101 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 102:102 */    nglCombinerInputNV(stage, portion, variable, input, mapping, componentUsage, function_pointer);
/* 103:    */  }
/* 104:    */  
/* 105:    */  static native void nglCombinerInputNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 106:    */  
/* 107:107 */  public static void glCombinerOutputNV(int stage, int portion, int abOutput, int cdOutput, int sumOutput, int scale, int bias, boolean abDotProduct, boolean cdDotProduct, boolean muxSum) { ContextCapabilities caps = GLContext.getCapabilities();
/* 108:108 */    long function_pointer = caps.glCombinerOutputNV;
/* 109:109 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 110:110 */    nglCombinerOutputNV(stage, portion, abOutput, cdOutput, sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum, function_pointer);
/* 111:    */  }
/* 112:    */  
/* 113:    */  static native void nglCombinerOutputNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, long paramLong);
/* 114:    */  
/* 115:115 */  public static void glFinalCombinerInputNV(int variable, int input, int mapping, int componentUsage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 116:116 */    long function_pointer = caps.glFinalCombinerInputNV;
/* 117:117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 118:118 */    nglFinalCombinerInputNV(variable, input, mapping, componentUsage, function_pointer);
/* 119:    */  }
/* 120:    */  
/* 121:    */  static native void nglFinalCombinerInputNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 122:    */  
/* 123:123 */  public static void glGetCombinerInputParameterNV(int stage, int portion, int variable, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 124:124 */    long function_pointer = caps.glGetCombinerInputParameterfvNV;
/* 125:125 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 126:126 */    BufferChecks.checkBuffer(params, 4);
/* 127:127 */    nglGetCombinerInputParameterfvNV(stage, portion, variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 128:    */  }
/* 129:    */  
/* 130:    */  static native void nglGetCombinerInputParameterfvNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 131:    */  
/* 132:    */  public static float glGetCombinerInputParameterfNV(int stage, int portion, int variable, int pname) {
/* 133:133 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 134:134 */    long function_pointer = caps.glGetCombinerInputParameterfvNV;
/* 135:135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 136:136 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 137:137 */    nglGetCombinerInputParameterfvNV(stage, portion, variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 138:138 */    return params.get(0);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public static void glGetCombinerInputParameterNV(int stage, int portion, int variable, int pname, IntBuffer params) {
/* 142:142 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glGetCombinerInputParameterivNV;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    BufferChecks.checkBuffer(params, 4);
/* 146:146 */    nglGetCombinerInputParameterivNV(stage, portion, variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 147:    */  }
/* 148:    */  
/* 149:    */  static native void nglGetCombinerInputParameterivNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 150:    */  
/* 151:    */  public static int glGetCombinerInputParameteriNV(int stage, int portion, int variable, int pname) {
/* 152:152 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glGetCombinerInputParameterivNV;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 156:156 */    nglGetCombinerInputParameterivNV(stage, portion, variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 157:157 */    return params.get(0);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public static void glGetCombinerOutputParameterNV(int stage, int portion, int pname, FloatBuffer params) {
/* 161:161 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 162:162 */    long function_pointer = caps.glGetCombinerOutputParameterfvNV;
/* 163:163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 164:164 */    BufferChecks.checkBuffer(params, 4);
/* 165:165 */    nglGetCombinerOutputParameterfvNV(stage, portion, pname, MemoryUtil.getAddress(params), function_pointer);
/* 166:    */  }
/* 167:    */  
/* 168:    */  static native void nglGetCombinerOutputParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 169:    */  
/* 170:    */  public static float glGetCombinerOutputParameterfNV(int stage, int portion, int pname) {
/* 171:171 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 172:172 */    long function_pointer = caps.glGetCombinerOutputParameterfvNV;
/* 173:173 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 174:174 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 175:175 */    nglGetCombinerOutputParameterfvNV(stage, portion, pname, MemoryUtil.getAddress(params), function_pointer);
/* 176:176 */    return params.get(0);
/* 177:    */  }
/* 178:    */  
/* 179:    */  public static void glGetCombinerOutputParameterNV(int stage, int portion, int pname, IntBuffer params) {
/* 180:180 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 181:181 */    long function_pointer = caps.glGetCombinerOutputParameterivNV;
/* 182:182 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 183:183 */    BufferChecks.checkBuffer(params, 4);
/* 184:184 */    nglGetCombinerOutputParameterivNV(stage, portion, pname, MemoryUtil.getAddress(params), function_pointer);
/* 185:    */  }
/* 186:    */  
/* 187:    */  static native void nglGetCombinerOutputParameterivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 188:    */  
/* 189:    */  public static int glGetCombinerOutputParameteriNV(int stage, int portion, int pname) {
/* 190:190 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 191:191 */    long function_pointer = caps.glGetCombinerOutputParameterivNV;
/* 192:192 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 193:193 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 194:194 */    nglGetCombinerOutputParameterivNV(stage, portion, pname, MemoryUtil.getAddress(params), function_pointer);
/* 195:195 */    return params.get(0);
/* 196:    */  }
/* 197:    */  
/* 198:    */  public static void glGetFinalCombinerInputParameterNV(int variable, int pname, FloatBuffer params) {
/* 199:199 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 200:200 */    long function_pointer = caps.glGetFinalCombinerInputParameterfvNV;
/* 201:201 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 202:202 */    BufferChecks.checkBuffer(params, 4);
/* 203:203 */    nglGetFinalCombinerInputParameterfvNV(variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 204:    */  }
/* 205:    */  
/* 206:    */  static native void nglGetFinalCombinerInputParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 207:    */  
/* 208:    */  public static float glGetFinalCombinerInputParameterfNV(int variable, int pname) {
/* 209:209 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 210:210 */    long function_pointer = caps.glGetFinalCombinerInputParameterfvNV;
/* 211:211 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 212:212 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 213:213 */    nglGetFinalCombinerInputParameterfvNV(variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 214:214 */    return params.get(0);
/* 215:    */  }
/* 216:    */  
/* 217:    */  public static void glGetFinalCombinerInputParameterNV(int variable, int pname, IntBuffer params) {
/* 218:218 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 219:219 */    long function_pointer = caps.glGetFinalCombinerInputParameterivNV;
/* 220:220 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 221:221 */    BufferChecks.checkBuffer(params, 4);
/* 222:222 */    nglGetFinalCombinerInputParameterivNV(variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 223:    */  }
/* 224:    */  
/* 225:    */  static native void nglGetFinalCombinerInputParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 226:    */  
/* 227:    */  public static int glGetFinalCombinerInputParameteriNV(int variable, int pname) {
/* 228:228 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 229:229 */    long function_pointer = caps.glGetFinalCombinerInputParameterivNV;
/* 230:230 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 231:231 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 232:232 */    nglGetFinalCombinerInputParameterivNV(variable, pname, MemoryUtil.getAddress(params), function_pointer);
/* 233:233 */    return params.get(0);
/* 234:    */  }
/* 235:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVRegisterCombiners
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */