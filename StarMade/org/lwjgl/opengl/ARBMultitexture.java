/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.BufferChecks;
/*   4:    */
/*   9:    */public final class ARBMultitexture
/*  10:    */{
/*  11:    */  public static final int GL_TEXTURE0_ARB = 33984;
/*  12:    */  public static final int GL_TEXTURE1_ARB = 33985;
/*  13:    */  public static final int GL_TEXTURE2_ARB = 33986;
/*  14:    */  public static final int GL_TEXTURE3_ARB = 33987;
/*  15:    */  public static final int GL_TEXTURE4_ARB = 33988;
/*  16:    */  public static final int GL_TEXTURE5_ARB = 33989;
/*  17:    */  public static final int GL_TEXTURE6_ARB = 33990;
/*  18:    */  public static final int GL_TEXTURE7_ARB = 33991;
/*  19:    */  public static final int GL_TEXTURE8_ARB = 33992;
/*  20:    */  public static final int GL_TEXTURE9_ARB = 33993;
/*  21:    */  public static final int GL_TEXTURE10_ARB = 33994;
/*  22:    */  public static final int GL_TEXTURE11_ARB = 33995;
/*  23:    */  public static final int GL_TEXTURE12_ARB = 33996;
/*  24:    */  public static final int GL_TEXTURE13_ARB = 33997;
/*  25:    */  public static final int GL_TEXTURE14_ARB = 33998;
/*  26:    */  public static final int GL_TEXTURE15_ARB = 33999;
/*  27:    */  public static final int GL_TEXTURE16_ARB = 34000;
/*  28:    */  public static final int GL_TEXTURE17_ARB = 34001;
/*  29:    */  public static final int GL_TEXTURE18_ARB = 34002;
/*  30:    */  public static final int GL_TEXTURE19_ARB = 34003;
/*  31:    */  public static final int GL_TEXTURE20_ARB = 34004;
/*  32:    */  public static final int GL_TEXTURE21_ARB = 34005;
/*  33:    */  public static final int GL_TEXTURE22_ARB = 34006;
/*  34:    */  public static final int GL_TEXTURE23_ARB = 34007;
/*  35:    */  public static final int GL_TEXTURE24_ARB = 34008;
/*  36:    */  public static final int GL_TEXTURE25_ARB = 34009;
/*  37:    */  public static final int GL_TEXTURE26_ARB = 34010;
/*  38:    */  public static final int GL_TEXTURE27_ARB = 34011;
/*  39:    */  public static final int GL_TEXTURE28_ARB = 34012;
/*  40:    */  public static final int GL_TEXTURE29_ARB = 34013;
/*  41:    */  public static final int GL_TEXTURE30_ARB = 34014;
/*  42:    */  public static final int GL_TEXTURE31_ARB = 34015;
/*  43:    */  public static final int GL_ACTIVE_TEXTURE_ARB = 34016;
/*  44:    */  public static final int GL_CLIENT_ACTIVE_TEXTURE_ARB = 34017;
/*  45:    */  public static final int GL_MAX_TEXTURE_UNITS_ARB = 34018;
/*  46:    */  
/*  47:    */  public static void glClientActiveTextureARB(int texture)
/*  48:    */  {
/*  49: 49 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  50: 50 */    long function_pointer = caps.glClientActiveTextureARB;
/*  51: 51 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  52: 52 */    nglClientActiveTextureARB(texture, function_pointer);
/*  53:    */  }
/*  54:    */  
/*  55:    */  static native void nglClientActiveTextureARB(int paramInt, long paramLong);
/*  56:    */  
/*  57: 57 */  public static void glActiveTextureARB(int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/*  58: 58 */    long function_pointer = caps.glActiveTextureARB;
/*  59: 59 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  60: 60 */    nglActiveTextureARB(texture, function_pointer);
/*  61:    */  }
/*  62:    */  
/*  63:    */  static native void nglActiveTextureARB(int paramInt, long paramLong);
/*  64:    */  
/*  65: 65 */  public static void glMultiTexCoord1fARB(int target, float s) { ContextCapabilities caps = GLContext.getCapabilities();
/*  66: 66 */    long function_pointer = caps.glMultiTexCoord1fARB;
/*  67: 67 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  68: 68 */    nglMultiTexCoord1fARB(target, s, function_pointer);
/*  69:    */  }
/*  70:    */  
/*  71:    */  static native void nglMultiTexCoord1fARB(int paramInt, float paramFloat, long paramLong);
/*  72:    */  
/*  73: 73 */  public static void glMultiTexCoord1dARB(int target, double s) { ContextCapabilities caps = GLContext.getCapabilities();
/*  74: 74 */    long function_pointer = caps.glMultiTexCoord1dARB;
/*  75: 75 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  76: 76 */    nglMultiTexCoord1dARB(target, s, function_pointer);
/*  77:    */  }
/*  78:    */  
/*  79:    */  static native void nglMultiTexCoord1dARB(int paramInt, double paramDouble, long paramLong);
/*  80:    */  
/*  81: 81 */  public static void glMultiTexCoord1iARB(int target, int s) { ContextCapabilities caps = GLContext.getCapabilities();
/*  82: 82 */    long function_pointer = caps.glMultiTexCoord1iARB;
/*  83: 83 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  84: 84 */    nglMultiTexCoord1iARB(target, s, function_pointer);
/*  85:    */  }
/*  86:    */  
/*  87:    */  static native void nglMultiTexCoord1iARB(int paramInt1, int paramInt2, long paramLong);
/*  88:    */  
/*  89: 89 */  public static void glMultiTexCoord1sARB(int target, short s) { ContextCapabilities caps = GLContext.getCapabilities();
/*  90: 90 */    long function_pointer = caps.glMultiTexCoord1sARB;
/*  91: 91 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  92: 92 */    nglMultiTexCoord1sARB(target, s, function_pointer);
/*  93:    */  }
/*  94:    */  
/*  95:    */  static native void nglMultiTexCoord1sARB(int paramInt, short paramShort, long paramLong);
/*  96:    */  
/*  97: 97 */  public static void glMultiTexCoord2fARB(int target, float s, float t) { ContextCapabilities caps = GLContext.getCapabilities();
/*  98: 98 */    long function_pointer = caps.glMultiTexCoord2fARB;
/*  99: 99 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 100:100 */    nglMultiTexCoord2fARB(target, s, t, function_pointer);
/* 101:    */  }
/* 102:    */  
/* 103:    */  static native void nglMultiTexCoord2fARB(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/* 104:    */  
/* 105:105 */  public static void glMultiTexCoord2dARB(int target, double s, double t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 106:106 */    long function_pointer = caps.glMultiTexCoord2dARB;
/* 107:107 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 108:108 */    nglMultiTexCoord2dARB(target, s, t, function_pointer);
/* 109:    */  }
/* 110:    */  
/* 111:    */  static native void nglMultiTexCoord2dARB(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/* 112:    */  
/* 113:113 */  public static void glMultiTexCoord2iARB(int target, int s, int t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 114:114 */    long function_pointer = caps.glMultiTexCoord2iARB;
/* 115:115 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 116:116 */    nglMultiTexCoord2iARB(target, s, t, function_pointer);
/* 117:    */  }
/* 118:    */  
/* 119:    */  static native void nglMultiTexCoord2iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 120:    */  
/* 121:121 */  public static void glMultiTexCoord2sARB(int target, short s, short t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 122:122 */    long function_pointer = caps.glMultiTexCoord2sARB;
/* 123:123 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 124:124 */    nglMultiTexCoord2sARB(target, s, t, function_pointer);
/* 125:    */  }
/* 126:    */  
/* 127:    */  static native void nglMultiTexCoord2sARB(int paramInt, short paramShort1, short paramShort2, long paramLong);
/* 128:    */  
/* 129:129 */  public static void glMultiTexCoord3fARB(int target, float s, float t, float r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glMultiTexCoord3fARB;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    nglMultiTexCoord3fARB(target, s, t, r, function_pointer);
/* 133:    */  }
/* 134:    */  
/* 135:    */  static native void nglMultiTexCoord3fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 136:    */  
/* 137:137 */  public static void glMultiTexCoord3dARB(int target, double s, double t, double r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 138:138 */    long function_pointer = caps.glMultiTexCoord3dARB;
/* 139:139 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 140:140 */    nglMultiTexCoord3dARB(target, s, t, r, function_pointer);
/* 141:    */  }
/* 142:    */  
/* 143:    */  static native void nglMultiTexCoord3dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 144:    */  
/* 145:145 */  public static void glMultiTexCoord3iARB(int target, int s, int t, int r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 146:146 */    long function_pointer = caps.glMultiTexCoord3iARB;
/* 147:147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 148:148 */    nglMultiTexCoord3iARB(target, s, t, r, function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  static native void nglMultiTexCoord3iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 152:    */  
/* 153:153 */  public static void glMultiTexCoord3sARB(int target, short s, short t, short r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 154:154 */    long function_pointer = caps.glMultiTexCoord3sARB;
/* 155:155 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 156:156 */    nglMultiTexCoord3sARB(target, s, t, r, function_pointer);
/* 157:    */  }
/* 158:    */  
/* 159:    */  static native void nglMultiTexCoord3sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 160:    */  
/* 161:161 */  public static void glMultiTexCoord4fARB(int target, float s, float t, float r, float q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 162:162 */    long function_pointer = caps.glMultiTexCoord4fARB;
/* 163:163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 164:164 */    nglMultiTexCoord4fARB(target, s, t, r, q, function_pointer);
/* 165:    */  }
/* 166:    */  
/* 167:    */  static native void nglMultiTexCoord4fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 168:    */  
/* 169:169 */  public static void glMultiTexCoord4dARB(int target, double s, double t, double r, double q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 170:170 */    long function_pointer = caps.glMultiTexCoord4dARB;
/* 171:171 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 172:172 */    nglMultiTexCoord4dARB(target, s, t, r, q, function_pointer);
/* 173:    */  }
/* 174:    */  
/* 175:    */  static native void nglMultiTexCoord4dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 176:    */  
/* 177:177 */  public static void glMultiTexCoord4iARB(int target, int s, int t, int r, int q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 178:178 */    long function_pointer = caps.glMultiTexCoord4iARB;
/* 179:179 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 180:180 */    nglMultiTexCoord4iARB(target, s, t, r, q, function_pointer);
/* 181:    */  }
/* 182:    */  
/* 183:    */  static native void nglMultiTexCoord4iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 184:    */  
/* 185:185 */  public static void glMultiTexCoord4sARB(int target, short s, short t, short r, short q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 186:186 */    long function_pointer = caps.glMultiTexCoord4sARB;
/* 187:187 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 188:188 */    nglMultiTexCoord4sARB(target, s, t, r, q, function_pointer);
/* 189:    */  }
/* 190:    */  
/* 191:    */  static native void nglMultiTexCoord4sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 192:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMultitexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */