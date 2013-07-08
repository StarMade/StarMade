/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.BufferChecks;
/*   4:    */
/*   9:    */public final class ATIVertexStreams
/*  10:    */{
/*  11:    */  public static final int GL_MAX_VERTEX_STREAMS_ATI = 34667;
/*  12:    */  public static final int GL_VERTEX_SOURCE_ATI = 34668;
/*  13:    */  public static final int GL_VERTEX_STREAM0_ATI = 34669;
/*  14:    */  public static final int GL_VERTEX_STREAM1_ATI = 34670;
/*  15:    */  public static final int GL_VERTEX_STREAM2_ATI = 34671;
/*  16:    */  public static final int GL_VERTEX_STREAM3_ATI = 34672;
/*  17:    */  public static final int GL_VERTEX_STREAM4_ATI = 34673;
/*  18:    */  public static final int GL_VERTEX_STREAM5_ATI = 34674;
/*  19:    */  public static final int GL_VERTEX_STREAM6_ATI = 34675;
/*  20:    */  public static final int GL_VERTEX_STREAM7_ATI = 34676;
/*  21:    */  
/*  22:    */  public static void glVertexStream2fATI(int stream, float x, float y)
/*  23:    */  {
/*  24: 24 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  25: 25 */    long function_pointer = caps.glVertexStream2fATI;
/*  26: 26 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  27: 27 */    nglVertexStream2fATI(stream, x, y, function_pointer);
/*  28:    */  }
/*  29:    */  
/*  30:    */  static native void nglVertexStream2fATI(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*  31:    */  
/*  32: 32 */  public static void glVertexStream2dATI(int stream, double x, double y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  33: 33 */    long function_pointer = caps.glVertexStream2dATI;
/*  34: 34 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  35: 35 */    nglVertexStream2dATI(stream, x, y, function_pointer);
/*  36:    */  }
/*  37:    */  
/*  38:    */  static native void nglVertexStream2dATI(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*  39:    */  
/*  40: 40 */  public static void glVertexStream2iATI(int stream, int x, int y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  41: 41 */    long function_pointer = caps.glVertexStream2iATI;
/*  42: 42 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  43: 43 */    nglVertexStream2iATI(stream, x, y, function_pointer);
/*  44:    */  }
/*  45:    */  
/*  46:    */  static native void nglVertexStream2iATI(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  47:    */  
/*  48: 48 */  public static void glVertexStream2sATI(int stream, short x, short y) { ContextCapabilities caps = GLContext.getCapabilities();
/*  49: 49 */    long function_pointer = caps.glVertexStream2sATI;
/*  50: 50 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  51: 51 */    nglVertexStream2sATI(stream, x, y, function_pointer);
/*  52:    */  }
/*  53:    */  
/*  54:    */  static native void nglVertexStream2sATI(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*  55:    */  
/*  56: 56 */  public static void glVertexStream3fATI(int stream, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glVertexStream3fATI;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    nglVertexStream3fATI(stream, x, y, z, function_pointer);
/*  60:    */  }
/*  61:    */  
/*  62:    */  static native void nglVertexStream3fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*  63:    */  
/*  64: 64 */  public static void glVertexStream3dATI(int stream, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  65: 65 */    long function_pointer = caps.glVertexStream3dATI;
/*  66: 66 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  67: 67 */    nglVertexStream3dATI(stream, x, y, z, function_pointer);
/*  68:    */  }
/*  69:    */  
/*  70:    */  static native void nglVertexStream3dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*  71:    */  
/*  72: 72 */  public static void glVertexStream3iATI(int stream, int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  73: 73 */    long function_pointer = caps.glVertexStream3iATI;
/*  74: 74 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  75: 75 */    nglVertexStream3iATI(stream, x, y, z, function_pointer);
/*  76:    */  }
/*  77:    */  
/*  78:    */  static native void nglVertexStream3iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  79:    */  
/*  80: 80 */  public static void glVertexStream3sATI(int stream, short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/*  81: 81 */    long function_pointer = caps.glVertexStream3sATI;
/*  82: 82 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  83: 83 */    nglVertexStream3sATI(stream, x, y, z, function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86:    */  static native void nglVertexStream3sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*  87:    */  
/*  88: 88 */  public static void glVertexStream4fATI(int stream, float x, float y, float z, float w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glVertexStream4fATI;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    nglVertexStream4fATI(stream, x, y, z, w, function_pointer);
/*  92:    */  }
/*  93:    */  
/*  94:    */  static native void nglVertexStream4fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*  95:    */  
/*  96: 96 */  public static void glVertexStream4dATI(int stream, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/*  97: 97 */    long function_pointer = caps.glVertexStream4dATI;
/*  98: 98 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  99: 99 */    nglVertexStream4dATI(stream, x, y, z, w, function_pointer);
/* 100:    */  }
/* 101:    */  
/* 102:    */  static native void nglVertexStream4dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/* 103:    */  
/* 104:104 */  public static void glVertexStream4iATI(int stream, int x, int y, int z, int w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 105:105 */    long function_pointer = caps.glVertexStream4iATI;
/* 106:106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 107:107 */    nglVertexStream4iATI(stream, x, y, z, w, function_pointer);
/* 108:    */  }
/* 109:    */  
/* 110:    */  static native void nglVertexStream4iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 111:    */  
/* 112:112 */  public static void glVertexStream4sATI(int stream, short x, short y, short z, short w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 113:113 */    long function_pointer = caps.glVertexStream4sATI;
/* 114:114 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 115:115 */    nglVertexStream4sATI(stream, x, y, z, w, function_pointer);
/* 116:    */  }
/* 117:    */  
/* 118:    */  static native void nglVertexStream4sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/* 119:    */  
/* 120:120 */  public static void glNormalStream3bATI(int stream, byte x, byte y, byte z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 121:121 */    long function_pointer = caps.glNormalStream3bATI;
/* 122:122 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 123:123 */    nglNormalStream3bATI(stream, x, y, z, function_pointer);
/* 124:    */  }
/* 125:    */  
/* 126:    */  static native void nglNormalStream3bATI(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/* 127:    */  
/* 128:128 */  public static void glNormalStream3fATI(int stream, float x, float y, float z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 129:129 */    long function_pointer = caps.glNormalStream3fATI;
/* 130:130 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 131:131 */    nglNormalStream3fATI(stream, x, y, z, function_pointer);
/* 132:    */  }
/* 133:    */  
/* 134:    */  static native void nglNormalStream3fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/* 135:    */  
/* 136:136 */  public static void glNormalStream3dATI(int stream, double x, double y, double z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 137:137 */    long function_pointer = caps.glNormalStream3dATI;
/* 138:138 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 139:139 */    nglNormalStream3dATI(stream, x, y, z, function_pointer);
/* 140:    */  }
/* 141:    */  
/* 142:    */  static native void nglNormalStream3dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/* 143:    */  
/* 144:144 */  public static void glNormalStream3iATI(int stream, int x, int y, int z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 145:145 */    long function_pointer = caps.glNormalStream3iATI;
/* 146:146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 147:147 */    nglNormalStream3iATI(stream, x, y, z, function_pointer);
/* 148:    */  }
/* 149:    */  
/* 150:    */  static native void nglNormalStream3iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 151:    */  
/* 152:152 */  public static void glNormalStream3sATI(int stream, short x, short y, short z) { ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glNormalStream3sATI;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    nglNormalStream3sATI(stream, x, y, z, function_pointer);
/* 156:    */  }
/* 157:    */  
/* 158:    */  static native void nglNormalStream3sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/* 159:    */  
/* 160:160 */  public static void glClientActiveVertexStreamATI(int stream) { ContextCapabilities caps = GLContext.getCapabilities();
/* 161:161 */    long function_pointer = caps.glClientActiveVertexStreamATI;
/* 162:162 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 163:163 */    nglClientActiveVertexStreamATI(stream, function_pointer);
/* 164:    */  }
/* 165:    */  
/* 166:    */  static native void nglClientActiveVertexStreamATI(int paramInt, long paramLong);
/* 167:    */  
/* 168:168 */  public static void glVertexBlendEnvfATI(int pname, float param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 169:169 */    long function_pointer = caps.glVertexBlendEnvfATI;
/* 170:170 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 171:171 */    nglVertexBlendEnvfATI(pname, param, function_pointer);
/* 172:    */  }
/* 173:    */  
/* 174:    */  static native void nglVertexBlendEnvfATI(int paramInt, float paramFloat, long paramLong);
/* 175:    */  
/* 176:176 */  public static void glVertexBlendEnviATI(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 177:177 */    long function_pointer = caps.glVertexBlendEnviATI;
/* 178:178 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 179:179 */    nglVertexBlendEnviATI(pname, param, function_pointer);
/* 180:    */  }
/* 181:    */  
/* 182:    */  static native void nglVertexBlendEnviATI(int paramInt1, int paramInt2, long paramLong);
/* 183:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIVertexStreams
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */