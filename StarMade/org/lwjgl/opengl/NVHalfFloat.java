/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ShortBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  16:    */public final class NVHalfFloat
/*  17:    */{
/*  18:    */  public static final int GL_HALF_FLOAT_NV = 5131;
/*  19:    */  
/*  20:    */  public static void glVertex2hNV(short x, short y)
/*  21:    */  {
/*  22: 22 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  23: 23 */    long function_pointer = caps.glVertex2hNV;
/*  24: 24 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  25: 25 */    nglVertex2hNV(x, y, function_pointer);
/*  26:    */  }
/*  27:    */  
/*  28:    */  static native void nglVertex2hNV(short paramShort1, short paramShort2, long paramLong);
/*  29:    */  
/*  30: 30 */  public static void glVertex3hNV(short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  31: 31 */    long function_pointer = caps.glVertex3hNV;
/*  32: 32 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  33: 33 */    nglVertex3hNV(x, y, z, function_pointer);
/*  34:    */  }
/*  35:    */  
/*  36:    */  static native void nglVertex3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*  37:    */  
/*  38: 38 */  public static void glVertex4hNV(short x, short y, short z, short w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  39: 39 */    long function_pointer = caps.glVertex4hNV;
/*  40: 40 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  41: 41 */    nglVertex4hNV(x, y, z, w, function_pointer);
/*  42:    */  }
/*  43:    */  
/*  44:    */  static native void nglVertex4hNV(short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*  45:    */  
/*  46: 46 */  public static void glNormal3hNV(short nx, short ny, short nz) { ContextCapabilities caps = GLContext.getCapabilities();
/*  47: 47 */    long function_pointer = caps.glNormal3hNV;
/*  48: 48 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  49: 49 */    nglNormal3hNV(nx, ny, nz, function_pointer);
/*  50:    */  }
/*  51:    */  
/*  52:    */  static native void nglNormal3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*  53:    */  
/*  54: 54 */  public static void glColor3hNV(short red, short green, short blue) { ContextCapabilities caps = GLContext.getCapabilities();
/*  55: 55 */    long function_pointer = caps.glColor3hNV;
/*  56: 56 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  57: 57 */    nglColor3hNV(red, green, blue, function_pointer);
/*  58:    */  }
/*  59:    */  
/*  60:    */  static native void nglColor3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*  61:    */  
/*  62: 62 */  public static void glColor4hNV(short red, short green, short blue, short alpha) { ContextCapabilities caps = GLContext.getCapabilities();
/*  63: 63 */    long function_pointer = caps.glColor4hNV;
/*  64: 64 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  65: 65 */    nglColor4hNV(red, green, blue, alpha, function_pointer);
/*  66:    */  }
/*  67:    */  
/*  68:    */  static native void nglColor4hNV(short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*  69:    */  
/*  70: 70 */  public static void glTexCoord1hNV(short s) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glTexCoord1hNV;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    nglTexCoord1hNV(s, function_pointer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  static native void nglTexCoord1hNV(short paramShort, long paramLong);
/*  77:    */  
/*  78: 78 */  public static void glTexCoord2hNV(short s, short t) { ContextCapabilities caps = GLContext.getCapabilities();
/*  79: 79 */    long function_pointer = caps.glTexCoord2hNV;
/*  80: 80 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  81: 81 */    nglTexCoord2hNV(s, t, function_pointer);
/*  82:    */  }
/*  83:    */  
/*  84:    */  static native void nglTexCoord2hNV(short paramShort1, short paramShort2, long paramLong);
/*  85:    */  
/*  86: 86 */  public static void glTexCoord3hNV(short s, short t, short r) { ContextCapabilities caps = GLContext.getCapabilities();
/*  87: 87 */    long function_pointer = caps.glTexCoord3hNV;
/*  88: 88 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  89: 89 */    nglTexCoord3hNV(s, t, r, function_pointer);
/*  90:    */  }
/*  91:    */  
/*  92:    */  static native void nglTexCoord3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*  93:    */  
/*  94: 94 */  public static void glTexCoord4hNV(short s, short t, short r, short q) { ContextCapabilities caps = GLContext.getCapabilities();
/*  95: 95 */    long function_pointer = caps.glTexCoord4hNV;
/*  96: 96 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  97: 97 */    nglTexCoord4hNV(s, t, r, q, function_pointer);
/*  98:    */  }
/*  99:    */  
/* 100:    */  static native void nglTexCoord4hNV(short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 101:    */  
/* 102:102 */  public static void glMultiTexCoord1hNV(int target, short s) { ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glMultiTexCoord1hNV;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    nglMultiTexCoord1hNV(target, s, function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglMultiTexCoord1hNV(int paramInt, short paramShort, long paramLong);
/* 109:    */  
/* 110:110 */  public static void glMultiTexCoord2hNV(int target, short s, short t) { ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glMultiTexCoord2hNV;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    nglMultiTexCoord2hNV(target, s, t, function_pointer);
/* 114:    */  }
/* 115:    */  
/* 116:    */  static native void nglMultiTexCoord2hNV(int paramInt, short paramShort1, short paramShort2, long paramLong);
/* 117:    */  
/* 118:118 */  public static void glMultiTexCoord3hNV(int target, short s, short t, short r) { ContextCapabilities caps = GLContext.getCapabilities();
/* 119:119 */    long function_pointer = caps.glMultiTexCoord3hNV;
/* 120:120 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 121:121 */    nglMultiTexCoord3hNV(target, s, t, r, function_pointer);
/* 122:    */  }
/* 123:    */  
/* 124:    */  static native void nglMultiTexCoord3hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 125:    */  
/* 126:126 */  public static void glMultiTexCoord4hNV(int target, short s, short t, short r, short q) { ContextCapabilities caps = GLContext.getCapabilities();
/* 127:127 */    long function_pointer = caps.glMultiTexCoord4hNV;
/* 128:128 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 129:129 */    nglMultiTexCoord4hNV(target, s, t, r, q, function_pointer);
/* 130:    */  }
/* 131:    */  
/* 132:    */  static native void nglMultiTexCoord4hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 133:    */  
/* 134:134 */  public static void glFogCoordhNV(short fog) { ContextCapabilities caps = GLContext.getCapabilities();
/* 135:135 */    long function_pointer = caps.glFogCoordhNV;
/* 136:136 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 137:137 */    nglFogCoordhNV(fog, function_pointer);
/* 138:    */  }
/* 139:    */  
/* 140:    */  static native void nglFogCoordhNV(short paramShort, long paramLong);
/* 141:    */  
/* 142:142 */  public static void glSecondaryColor3hNV(short red, short green, short blue) { ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glSecondaryColor3hNV;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    nglSecondaryColor3hNV(red, green, blue, function_pointer);
/* 146:    */  }
/* 147:    */  
/* 148:    */  static native void nglSecondaryColor3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 149:    */  
/* 150:150 */  public static void glVertexWeighthNV(short weight) { ContextCapabilities caps = GLContext.getCapabilities();
/* 151:151 */    long function_pointer = caps.glVertexWeighthNV;
/* 152:152 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 153:153 */    nglVertexWeighthNV(weight, function_pointer);
/* 154:    */  }
/* 155:    */  
/* 156:    */  static native void nglVertexWeighthNV(short paramShort, long paramLong);
/* 157:    */  
/* 158:158 */  public static void glVertexAttrib1hNV(int index, short x) { ContextCapabilities caps = GLContext.getCapabilities();
/* 159:159 */    long function_pointer = caps.glVertexAttrib1hNV;
/* 160:160 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 161:161 */    nglVertexAttrib1hNV(index, x, function_pointer);
/* 162:    */  }
/* 163:    */  
/* 164:    */  static native void nglVertexAttrib1hNV(int paramInt, short paramShort, long paramLong);
/* 165:    */  
/* 166:166 */  public static void glVertexAttrib2hNV(int index, short x, short y) { ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glVertexAttrib2hNV;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    nglVertexAttrib2hNV(index, x, y, function_pointer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native void nglVertexAttrib2hNV(int paramInt, short paramShort1, short paramShort2, long paramLong);
/* 173:    */  
/* 174:174 */  public static void glVertexAttrib3hNV(int index, short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glVertexAttrib3hNV;
/* 176:176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    nglVertexAttrib3hNV(index, x, y, z, function_pointer);
/* 178:    */  }
/* 179:    */  
/* 180:    */  static native void nglVertexAttrib3hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 181:    */  
/* 182:182 */  public static void glVertexAttrib4hNV(int index, short x, short y, short z, short w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 183:183 */    long function_pointer = caps.glVertexAttrib4hNV;
/* 184:184 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 185:185 */    nglVertexAttrib4hNV(index, x, y, z, w, function_pointer);
/* 186:    */  }
/* 187:    */  
/* 188:    */  static native void nglVertexAttrib4hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 189:    */  
/* 190:190 */  public static void glVertexAttribs1NV(int index, ShortBuffer attribs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 191:191 */    long function_pointer = caps.glVertexAttribs1hvNV;
/* 192:192 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 193:193 */    BufferChecks.checkDirect(attribs);
/* 194:194 */    nglVertexAttribs1hvNV(index, attribs.remaining(), MemoryUtil.getAddress(attribs), function_pointer);
/* 195:    */  }
/* 196:    */  
/* 197:    */  static native void nglVertexAttribs1hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 198:    */  
/* 199:199 */  public static void glVertexAttribs2NV(int index, ShortBuffer attribs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 200:200 */    long function_pointer = caps.glVertexAttribs2hvNV;
/* 201:201 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 202:202 */    BufferChecks.checkDirect(attribs);
/* 203:203 */    nglVertexAttribs2hvNV(index, attribs.remaining() >> 1, MemoryUtil.getAddress(attribs), function_pointer);
/* 204:    */  }
/* 205:    */  
/* 206:    */  static native void nglVertexAttribs2hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 207:    */  
/* 208:208 */  public static void glVertexAttribs3NV(int index, ShortBuffer attribs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 209:209 */    long function_pointer = caps.glVertexAttribs3hvNV;
/* 210:210 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 211:211 */    BufferChecks.checkDirect(attribs);
/* 212:212 */    nglVertexAttribs3hvNV(index, attribs.remaining() / 3, MemoryUtil.getAddress(attribs), function_pointer);
/* 213:    */  }
/* 214:    */  
/* 215:    */  static native void nglVertexAttribs3hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 216:    */  
/* 217:217 */  public static void glVertexAttribs4NV(int index, ShortBuffer attribs) { ContextCapabilities caps = GLContext.getCapabilities();
/* 218:218 */    long function_pointer = caps.glVertexAttribs4hvNV;
/* 219:219 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 220:220 */    BufferChecks.checkDirect(attribs);
/* 221:221 */    nglVertexAttribs4hvNV(index, attribs.remaining() >> 2, MemoryUtil.getAddress(attribs), function_pointer);
/* 222:    */  }
/* 223:    */  
/* 224:    */  static native void nglVertexAttribs4hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 225:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVHalfFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */