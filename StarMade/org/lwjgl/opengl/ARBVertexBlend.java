/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.LWJGLUtil;
/*   8:    */
/*   9:    */public final class ARBVertexBlend
/*  10:    */{
/*  11:    */  public static final int GL_MAX_VERTEX_UNITS_ARB = 34468;
/*  12:    */  public static final int GL_ACTIVE_VERTEX_UNITS_ARB = 34469;
/*  13:    */  public static final int GL_WEIGHT_SUM_UNITY_ARB = 34470;
/*  14:    */  public static final int GL_VERTEX_BLEND_ARB = 34471;
/*  15:    */  public static final int GL_CURRENT_WEIGHT_ARB = 34472;
/*  16:    */  public static final int GL_WEIGHT_ARRAY_TYPE_ARB = 34473;
/*  17:    */  public static final int GL_WEIGHT_ARRAY_STRIDE_ARB = 34474;
/*  18:    */  public static final int GL_WEIGHT_ARRAY_SIZE_ARB = 34475;
/*  19:    */  public static final int GL_WEIGHT_ARRAY_POINTER_ARB = 34476;
/*  20:    */  public static final int GL_WEIGHT_ARRAY_ARB = 34477;
/*  21:    */  public static final int GL_MODELVIEW0_ARB = 5888;
/*  22:    */  public static final int GL_MODELVIEW1_ARB = 34058;
/*  23:    */  public static final int GL_MODELVIEW2_ARB = 34594;
/*  24:    */  public static final int GL_MODELVIEW3_ARB = 34595;
/*  25:    */  public static final int GL_MODELVIEW4_ARB = 34596;
/*  26:    */  public static final int GL_MODELVIEW5_ARB = 34597;
/*  27:    */  public static final int GL_MODELVIEW6_ARB = 34598;
/*  28:    */  public static final int GL_MODELVIEW7_ARB = 34599;
/*  29:    */  public static final int GL_MODELVIEW8_ARB = 34600;
/*  30:    */  public static final int GL_MODELVIEW9_ARB = 34601;
/*  31:    */  public static final int GL_MODELVIEW10_ARB = 34602;
/*  32:    */  public static final int GL_MODELVIEW11_ARB = 34603;
/*  33:    */  public static final int GL_MODELVIEW12_ARB = 34604;
/*  34:    */  public static final int GL_MODELVIEW13_ARB = 34605;
/*  35:    */  public static final int GL_MODELVIEW14_ARB = 34606;
/*  36:    */  public static final int GL_MODELVIEW15_ARB = 34607;
/*  37:    */  public static final int GL_MODELVIEW16_ARB = 34608;
/*  38:    */  public static final int GL_MODELVIEW17_ARB = 34609;
/*  39:    */  public static final int GL_MODELVIEW18_ARB = 34610;
/*  40:    */  public static final int GL_MODELVIEW19_ARB = 34611;
/*  41:    */  public static final int GL_MODELVIEW20_ARB = 34612;
/*  42:    */  public static final int GL_MODELVIEW21_ARB = 34613;
/*  43:    */  public static final int GL_MODELVIEW22_ARB = 34614;
/*  44:    */  public static final int GL_MODELVIEW23_ARB = 34615;
/*  45:    */  public static final int GL_MODELVIEW24_ARB = 34616;
/*  46:    */  public static final int GL_MODELVIEW25_ARB = 34617;
/*  47:    */  public static final int GL_MODELVIEW26_ARB = 34618;
/*  48:    */  public static final int GL_MODELVIEW27_ARB = 34619;
/*  49:    */  public static final int GL_MODELVIEW28_ARB = 34620;
/*  50:    */  public static final int GL_MODELVIEW29_ARB = 34621;
/*  51:    */  public static final int GL_MODELVIEW30_ARB = 34622;
/*  52:    */  public static final int GL_MODELVIEW31_ARB = 34623;
/*  53:    */  
/*  54:    */  public static void glWeightARB(ByteBuffer pWeights)
/*  55:    */  {
/*  56: 56 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glWeightbvARB;
/*  58: 58 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/*  60: 60 */    nglWeightbvARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/*  61:    */  }
/*  62:    */  
/*  63:    */  static native void nglWeightbvARB(int paramInt, long paramLong1, long paramLong2);
/*  64:    */  
/*  65: 65 */  public static void glWeightARB(java.nio.ShortBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/*  66: 66 */    long function_pointer = caps.glWeightsvARB;
/*  67: 67 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  68: 68 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/*  69: 69 */    nglWeightsvARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/*  70:    */  }
/*  71:    */  
/*  72:    */  static native void nglWeightsvARB(int paramInt, long paramLong1, long paramLong2);
/*  73:    */  
/*  74: 74 */  public static void glWeightARB(IntBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/*  75: 75 */    long function_pointer = caps.glWeightivARB;
/*  76: 76 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  77: 77 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/*  78: 78 */    nglWeightivARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/*  79:    */  }
/*  80:    */  
/*  81:    */  static native void nglWeightivARB(int paramInt, long paramLong1, long paramLong2);
/*  82:    */  
/*  83: 83 */  public static void glWeightARB(FloatBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/*  84: 84 */    long function_pointer = caps.glWeightfvARB;
/*  85: 85 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  86: 86 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/*  87: 87 */    nglWeightfvARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglWeightfvARB(int paramInt, long paramLong1, long paramLong2);
/*  91:    */  
/*  92: 92 */  public static void glWeightARB(DoubleBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/*  93: 93 */    long function_pointer = caps.glWeightdvARB;
/*  94: 94 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  95: 95 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/*  96: 96 */    nglWeightdvARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/*  97:    */  }
/*  98:    */  
/*  99:    */  static native void nglWeightdvARB(int paramInt, long paramLong1, long paramLong2);
/* 100:    */  
/* 101:101 */  public static void glWeightuARB(ByteBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glWeightubvARB;
/* 103:103 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/* 105:105 */    nglWeightubvARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglWeightubvARB(int paramInt, long paramLong1, long paramLong2);
/* 109:    */  
/* 110:110 */  public static void glWeightuARB(java.nio.ShortBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glWeightusvARB;
/* 112:112 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/* 114:114 */    nglWeightusvARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/* 115:    */  }
/* 116:    */  
/* 117:    */  static native void nglWeightusvARB(int paramInt, long paramLong1, long paramLong2);
/* 118:    */  
/* 119:119 */  public static void glWeightuARB(IntBuffer pWeights) { ContextCapabilities caps = GLContext.getCapabilities();
/* 120:120 */    long function_pointer = caps.glWeightuivARB;
/* 121:121 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 122:122 */    org.lwjgl.BufferChecks.checkDirect(pWeights);
/* 123:123 */    nglWeightuivARB(pWeights.remaining(), org.lwjgl.MemoryUtil.getAddress(pWeights), function_pointer);
/* 124:    */  }
/* 125:    */  
/* 126:    */  static native void nglWeightuivARB(int paramInt, long paramLong1, long paramLong2);
/* 127:    */  
/* 128:128 */  public static void glWeightPointerARB(int size, int stride, DoubleBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 129:129 */    long function_pointer = caps.glWeightPointerARB;
/* 130:130 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 131:131 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 132:132 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 133:133 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 134:134 */    nglWeightPointerARB(size, 5130, stride, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 135:    */  }
/* 136:    */  
/* 137:137 */  public static void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 138:138 */    long function_pointer = caps.glWeightPointerARB;
/* 139:139 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 140:140 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 141:141 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 142:142 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 143:143 */    nglWeightPointerARB(size, 5126, stride, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 144:    */  }
/* 145:    */  
/* 146:146 */  public static void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 147:147 */    long function_pointer = caps.glWeightPointerARB;
/* 148:148 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 149:149 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 150:150 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 151:151 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 152:152 */    nglWeightPointerARB(size, unsigned ? 5121 : 5120, stride, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 153:    */  }
/* 154:    */  
/* 155:155 */  public static void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 156:156 */    long function_pointer = caps.glWeightPointerARB;
/* 157:157 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 158:158 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 159:159 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 160:160 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 161:161 */    nglWeightPointerARB(size, unsigned ? 5125 : 5124, stride, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 162:    */  }
/* 163:    */  
/* 164:164 */  public static void glWeightPointerARB(int size, boolean unsigned, int stride, java.nio.ShortBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 165:165 */    long function_pointer = caps.glWeightPointerARB;
/* 166:166 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 167:167 */    GLChecks.ensureArrayVBOdisabled(caps);
/* 168:168 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 169:169 */    if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 170:170 */    nglWeightPointerARB(size, unsigned ? 5123 : 5122, stride, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer); }
/* 171:    */  
/* 172:    */  static native void nglWeightPointerARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 173:    */  
/* 174:174 */  public static void glWeightPointerARB(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glWeightPointerARB;
/* 176:176 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    GLChecks.ensureArrayVBOenabled(caps);
/* 178:178 */    nglWeightPointerARBBO(size, type, stride, pPointer_buffer_offset, function_pointer);
/* 179:    */  }
/* 180:    */  
/* 181:    */  static native void nglWeightPointerARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 182:    */  
/* 183:183 */  public static void glVertexBlendARB(int count) { ContextCapabilities caps = GLContext.getCapabilities();
/* 184:184 */    long function_pointer = caps.glVertexBlendARB;
/* 185:185 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 186:186 */    nglVertexBlendARB(count, function_pointer);
/* 187:    */  }
/* 188:    */  
/* 189:    */  static native void nglVertexBlendARB(int paramInt, long paramLong);
/* 190:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexBlend
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */