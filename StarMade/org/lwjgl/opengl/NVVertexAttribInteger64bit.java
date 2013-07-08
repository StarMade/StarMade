/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.LongBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  13:    */public final class NVVertexAttribInteger64bit
/*  14:    */{
/*  15:    */  public static final int GL_INT64_NV = 5134;
/*  16:    */  public static final int GL_UNSIGNED_INT64_NV = 5135;
/*  17:    */  
/*  18:    */  public static void glVertexAttribL1i64NV(int index, long x)
/*  19:    */  {
/*  20: 20 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  21: 21 */    long function_pointer = caps.glVertexAttribL1i64NV;
/*  22: 22 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  23: 23 */    nglVertexAttribL1i64NV(index, x, function_pointer);
/*  24:    */  }
/*  25:    */  
/*  26:    */  static native void nglVertexAttribL1i64NV(int paramInt, long paramLong1, long paramLong2);
/*  27:    */  
/*  28: 28 */  public static void glVertexAttribL2i64NV(int index, long x, long y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  29: 29 */    long function_pointer = caps.glVertexAttribL2i64NV;
/*  30: 30 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  31: 31 */    nglVertexAttribL2i64NV(index, x, y, function_pointer);
/*  32:    */  }
/*  33:    */  
/*  34:    */  static native void nglVertexAttribL2i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*  35:    */  
/*  36: 36 */  public static void glVertexAttribL3i64NV(int index, long x, long y, long z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  37: 37 */    long function_pointer = caps.glVertexAttribL3i64NV;
/*  38: 38 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  39: 39 */    nglVertexAttribL3i64NV(index, x, y, z, function_pointer);
/*  40:    */  }
/*  41:    */  
/*  42:    */  static native void nglVertexAttribL3i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*  43:    */  
/*  44: 44 */  public static void glVertexAttribL4i64NV(int index, long x, long y, long z, long w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  45: 45 */    long function_pointer = caps.glVertexAttribL4i64NV;
/*  46: 46 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  47: 47 */    nglVertexAttribL4i64NV(index, x, y, z, w, function_pointer);
/*  48:    */  }
/*  49:    */  
/*  50:    */  static native void nglVertexAttribL4i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*  51:    */  
/*  52: 52 */  public static void glVertexAttribL1NV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  53: 53 */    long function_pointer = caps.glVertexAttribL1i64vNV;
/*  54: 54 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  55: 55 */    BufferChecks.checkBuffer(v, 1);
/*  56: 56 */    nglVertexAttribL1i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*  57:    */  }
/*  58:    */  
/*  59:    */  static native void nglVertexAttribL1i64vNV(int paramInt, long paramLong1, long paramLong2);
/*  60:    */  
/*  61: 61 */  public static void glVertexAttribL2NV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  62: 62 */    long function_pointer = caps.glVertexAttribL2i64vNV;
/*  63: 63 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  64: 64 */    BufferChecks.checkBuffer(v, 2);
/*  65: 65 */    nglVertexAttribL2i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*  66:    */  }
/*  67:    */  
/*  68:    */  static native void nglVertexAttribL2i64vNV(int paramInt, long paramLong1, long paramLong2);
/*  69:    */  
/*  70: 70 */  public static void glVertexAttribL3NV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glVertexAttribL3i64vNV;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    BufferChecks.checkBuffer(v, 3);
/*  74: 74 */    nglVertexAttribL3i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*  75:    */  }
/*  76:    */  
/*  77:    */  static native void nglVertexAttribL3i64vNV(int paramInt, long paramLong1, long paramLong2);
/*  78:    */  
/*  79: 79 */  public static void glVertexAttribL4NV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glVertexAttribL4i64vNV;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    BufferChecks.checkBuffer(v, 4);
/*  83: 83 */    nglVertexAttribL4i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86:    */  static native void nglVertexAttribL4i64vNV(int paramInt, long paramLong1, long paramLong2);
/*  87:    */  
/*  88: 88 */  public static void glVertexAttribL1ui64NV(int index, long x) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glVertexAttribL1ui64NV;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    nglVertexAttribL1ui64NV(index, x, function_pointer);
/*  92:    */  }
/*  93:    */  
/*  94:    */  static native void nglVertexAttribL1ui64NV(int paramInt, long paramLong1, long paramLong2);
/*  95:    */  
/*  96: 96 */  public static void glVertexAttribL2ui64NV(int index, long x, long y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  97: 97 */    long function_pointer = caps.glVertexAttribL2ui64NV;
/*  98: 98 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  99: 99 */    nglVertexAttribL2ui64NV(index, x, y, function_pointer);
/* 100:    */  }
/* 101:    */  
/* 102:    */  static native void nglVertexAttribL2ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 103:    */  
/* 104:104 */  public static void glVertexAttribL3ui64NV(int index, long x, long y, long z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 105:105 */    long function_pointer = caps.glVertexAttribL3ui64NV;
/* 106:106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 107:107 */    nglVertexAttribL3ui64NV(index, x, y, z, function_pointer);
/* 108:    */  }
/* 109:    */  
/* 110:    */  static native void nglVertexAttribL3ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 111:    */  
/* 112:112 */  public static void glVertexAttribL4ui64NV(int index, long x, long y, long z, long w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 113:113 */    long function_pointer = caps.glVertexAttribL4ui64NV;
/* 114:114 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 115:115 */    nglVertexAttribL4ui64NV(index, x, y, z, w, function_pointer);
/* 116:    */  }
/* 117:    */  
/* 118:    */  static native void nglVertexAttribL4ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 119:    */  
/* 120:120 */  public static void glVertexAttribL1uNV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 121:121 */    long function_pointer = caps.glVertexAttribL1ui64vNV;
/* 122:122 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 123:123 */    BufferChecks.checkBuffer(v, 1);
/* 124:124 */    nglVertexAttribL1ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/* 125:    */  }
/* 126:    */  
/* 127:    */  static native void nglVertexAttribL1ui64vNV(int paramInt, long paramLong1, long paramLong2);
/* 128:    */  
/* 129:129 */  public static void glVertexAttribL2uNV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glVertexAttribL2ui64vNV;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    BufferChecks.checkBuffer(v, 2);
/* 133:133 */    nglVertexAttribL2ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static native void nglVertexAttribL2ui64vNV(int paramInt, long paramLong1, long paramLong2);
/* 137:    */  
/* 138:138 */  public static void glVertexAttribL3uNV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 139:139 */    long function_pointer = caps.glVertexAttribL3ui64vNV;
/* 140:140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 141:141 */    BufferChecks.checkBuffer(v, 3);
/* 142:142 */    nglVertexAttribL3ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/* 143:    */  }
/* 144:    */  
/* 145:    */  static native void nglVertexAttribL3ui64vNV(int paramInt, long paramLong1, long paramLong2);
/* 146:    */  
/* 147:147 */  public static void glVertexAttribL4uNV(int index, LongBuffer v) { ContextCapabilities caps = GLContext.getCapabilities();
/* 148:148 */    long function_pointer = caps.glVertexAttribL4ui64vNV;
/* 149:149 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 150:150 */    BufferChecks.checkBuffer(v, 4);
/* 151:151 */    nglVertexAttribL4ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/* 152:    */  }
/* 153:    */  
/* 154:    */  static native void nglVertexAttribL4ui64vNV(int paramInt, long paramLong1, long paramLong2);
/* 155:    */  
/* 156:156 */  public static void glGetVertexAttribLNV(int index, int pname, LongBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 157:157 */    long function_pointer = caps.glGetVertexAttribLi64vNV;
/* 158:158 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 159:159 */    BufferChecks.checkBuffer(params, 4);
/* 160:160 */    nglGetVertexAttribLi64vNV(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 161:    */  }
/* 162:    */  
/* 163:    */  static native void nglGetVertexAttribLi64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 164:    */  
/* 165:165 */  public static void glGetVertexAttribLuNV(int index, int pname, LongBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 166:166 */    long function_pointer = caps.glGetVertexAttribLui64vNV;
/* 167:167 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 168:168 */    BufferChecks.checkBuffer(params, 4);
/* 169:169 */    nglGetVertexAttribLui64vNV(index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native void nglGetVertexAttribLui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 173:    */  
/* 174:174 */  public static void glVertexAttribLFormatNV(int index, int size, int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glVertexAttribLFormatNV;
/* 176:176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    nglVertexAttribLFormatNV(index, size, type, stride, function_pointer);
/* 178:    */  }
/* 179:    */  
/* 180:    */  static native void nglVertexAttribLFormatNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 181:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexAttribInteger64bit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */