/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */
/*   9:    */public final class ATIVertexArrayObject
/*  10:    */{
/*  11:    */  public static final int GL_STATIC_ATI = 34656;
/*  12:    */  public static final int GL_DYNAMIC_ATI = 34657;
/*  13:    */  public static final int GL_PRESERVE_ATI = 34658;
/*  14:    */  public static final int GL_DISCARD_ATI = 34659;
/*  15:    */  public static final int GL_OBJECT_BUFFER_SIZE_ATI = 34660;
/*  16:    */  public static final int GL_OBJECT_BUFFER_USAGE_ATI = 34661;
/*  17:    */  public static final int GL_ARRAY_OBJECT_BUFFER_ATI = 34662;
/*  18:    */  public static final int GL_ARRAY_OBJECT_OFFSET_ATI = 34663;
/*  19:    */  
/*  20:    */  public static int glNewObjectBufferATI(int pPointer_size, int usage)
/*  21:    */  {
/*  22: 22 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  23: 23 */    long function_pointer = caps.glNewObjectBufferATI;
/*  24: 24 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  25: 25 */    int __result = nglNewObjectBufferATI(pPointer_size, 0L, usage, function_pointer);
/*  26: 26 */    return __result;
/*  27:    */  }
/*  28:    */  
/*  29: 29 */  public static int glNewObjectBufferATI(ByteBuffer pPointer, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/*  30: 30 */    long function_pointer = caps.glNewObjectBufferATI;
/*  31: 31 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  32: 32 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  33: 33 */    int __result = nglNewObjectBufferATI(pPointer.remaining(), org.lwjgl.MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  34: 34 */    return __result;
/*  35:    */  }
/*  36:    */  
/*  37: 37 */  public static int glNewObjectBufferATI(DoubleBuffer pPointer, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/*  38: 38 */    long function_pointer = caps.glNewObjectBufferATI;
/*  39: 39 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  40: 40 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  41: 41 */    int __result = nglNewObjectBufferATI(pPointer.remaining() << 3, org.lwjgl.MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  42: 42 */    return __result;
/*  43:    */  }
/*  44:    */  
/*  45: 45 */  public static int glNewObjectBufferATI(FloatBuffer pPointer, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/*  46: 46 */    long function_pointer = caps.glNewObjectBufferATI;
/*  47: 47 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  48: 48 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  49: 49 */    int __result = nglNewObjectBufferATI(pPointer.remaining() << 2, org.lwjgl.MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  50: 50 */    return __result;
/*  51:    */  }
/*  52:    */  
/*  53: 53 */  public static int glNewObjectBufferATI(IntBuffer pPointer, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/*  54: 54 */    long function_pointer = caps.glNewObjectBufferATI;
/*  55: 55 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  56: 56 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  57: 57 */    int __result = nglNewObjectBufferATI(pPointer.remaining() << 2, org.lwjgl.MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  58: 58 */    return __result;
/*  59:    */  }
/*  60:    */  
/*  61: 61 */  public static int glNewObjectBufferATI(ShortBuffer pPointer, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/*  62: 62 */    long function_pointer = caps.glNewObjectBufferATI;
/*  63: 63 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  64: 64 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  65: 65 */    int __result = nglNewObjectBufferATI(pPointer.remaining() << 1, org.lwjgl.MemoryUtil.getAddress(pPointer), usage, function_pointer);
/*  66: 66 */    return __result;
/*  67:    */  }
/*  68:    */  
/*  69:    */  static native int nglNewObjectBufferATI(int paramInt1, long paramLong1, int paramInt2, long paramLong2);
/*  70:    */  
/*  71: 71 */  public static boolean glIsObjectBufferATI(int buffer) { ContextCapabilities caps = GLContext.getCapabilities();
/*  72: 72 */    long function_pointer = caps.glIsObjectBufferATI;
/*  73: 73 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  74: 74 */    boolean __result = nglIsObjectBufferATI(buffer, function_pointer);
/*  75: 75 */    return __result;
/*  76:    */  }
/*  77:    */  
/*  78:    */  static native boolean nglIsObjectBufferATI(int paramInt, long paramLong);
/*  79:    */  
/*  80: 80 */  public static void glUpdateObjectBufferATI(int buffer, int offset, ByteBuffer pPointer, int preserve) { ContextCapabilities caps = GLContext.getCapabilities();
/*  81: 81 */    long function_pointer = caps.glUpdateObjectBufferATI;
/*  82: 82 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  83: 83 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  84: 84 */    nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining(), org.lwjgl.MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public static void glUpdateObjectBufferATI(int buffer, int offset, DoubleBuffer pPointer, int preserve) { ContextCapabilities caps = GLContext.getCapabilities();
/*  88: 88 */    long function_pointer = caps.glUpdateObjectBufferATI;
/*  89: 89 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  90: 90 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  91: 91 */    nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 3, org.lwjgl.MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*  92:    */  }
/*  93:    */  
/*  94: 94 */  public static void glUpdateObjectBufferATI(int buffer, int offset, FloatBuffer pPointer, int preserve) { ContextCapabilities caps = GLContext.getCapabilities();
/*  95: 95 */    long function_pointer = caps.glUpdateObjectBufferATI;
/*  96: 96 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/*  97: 97 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/*  98: 98 */    nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 2, org.lwjgl.MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/*  99:    */  }
/* 100:    */  
/* 101:101 */  public static void glUpdateObjectBufferATI(int buffer, int offset, IntBuffer pPointer, int preserve) { ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glUpdateObjectBufferATI;
/* 103:103 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 105:105 */    nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 2, org.lwjgl.MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:108 */  public static void glUpdateObjectBufferATI(int buffer, int offset, ShortBuffer pPointer, int preserve) { ContextCapabilities caps = GLContext.getCapabilities();
/* 109:109 */    long function_pointer = caps.glUpdateObjectBufferATI;
/* 110:110 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 111:111 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 112:112 */    nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining() << 1, org.lwjgl.MemoryUtil.getAddress(pPointer), preserve, function_pointer);
/* 113:    */  }
/* 114:    */  
/* 115:    */  static native void nglUpdateObjectBufferATI(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/* 116:    */  
/* 117:117 */  public static void glGetObjectBufferATI(int buffer, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 118:118 */    long function_pointer = caps.glGetObjectBufferfvATI;
/* 119:119 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 120:120 */    org.lwjgl.BufferChecks.checkDirect(params);
/* 121:121 */    nglGetObjectBufferfvATI(buffer, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 122:    */  }
/* 123:    */  
/* 124:    */  static native void nglGetObjectBufferfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 125:    */  
/* 126:126 */  public static void glGetObjectBufferATI(int buffer, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 127:127 */    long function_pointer = caps.glGetObjectBufferivATI;
/* 128:128 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 129:129 */    org.lwjgl.BufferChecks.checkDirect(params);
/* 130:130 */    nglGetObjectBufferivATI(buffer, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:    */  static native void nglGetObjectBufferivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 134:    */  
/* 135:    */  public static int glGetObjectBufferiATI(int buffer, int pname) {
/* 136:136 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 137:137 */    long function_pointer = caps.glGetObjectBufferivATI;
/* 138:138 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 139:139 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 140:140 */    nglGetObjectBufferivATI(buffer, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 141:141 */    return params.get(0);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public static void glFreeObjectBufferATI(int buffer) {
/* 145:145 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 146:146 */    long function_pointer = caps.glFreeObjectBufferATI;
/* 147:147 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 148:148 */    nglFreeObjectBufferATI(buffer, function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  static native void nglFreeObjectBufferATI(int paramInt, long paramLong);
/* 152:    */  
/* 153:153 */  public static void glArrayObjectATI(int array, int size, int type, int stride, int buffer, int offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 154:154 */    long function_pointer = caps.glArrayObjectATI;
/* 155:155 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 156:156 */    nglArrayObjectATI(array, size, type, stride, buffer, offset, function_pointer);
/* 157:    */  }
/* 158:    */  
/* 159:    */  static native void nglArrayObjectATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 160:    */  
/* 161:161 */  public static void glGetArrayObjectATI(int array, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 162:162 */    long function_pointer = caps.glGetArrayObjectfvATI;
/* 163:163 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 164:164 */    org.lwjgl.BufferChecks.checkBuffer(params, 4);
/* 165:165 */    nglGetArrayObjectfvATI(array, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 166:    */  }
/* 167:    */  
/* 168:    */  static native void nglGetArrayObjectfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 169:    */  
/* 170:170 */  public static void glGetArrayObjectATI(int array, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 171:171 */    long function_pointer = caps.glGetArrayObjectivATI;
/* 172:172 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 173:173 */    org.lwjgl.BufferChecks.checkBuffer(params, 4);
/* 174:174 */    nglGetArrayObjectivATI(array, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 175:    */  }
/* 176:    */  
/* 177:    */  static native void nglGetArrayObjectivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 178:    */  
/* 179:179 */  public static void glVariantArrayObjectATI(int id, int type, int stride, int buffer, int offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 180:180 */    long function_pointer = caps.glVariantArrayObjectATI;
/* 181:181 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 182:182 */    nglVariantArrayObjectATI(id, type, stride, buffer, offset, function_pointer);
/* 183:    */  }
/* 184:    */  
/* 185:    */  static native void nglVariantArrayObjectATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 186:    */  
/* 187:187 */  public static void glGetVariantArrayObjectATI(int id, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 188:188 */    long function_pointer = caps.glGetVariantArrayObjectfvATI;
/* 189:189 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 190:190 */    org.lwjgl.BufferChecks.checkBuffer(params, 4);
/* 191:191 */    nglGetVariantArrayObjectfvATI(id, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 192:    */  }
/* 193:    */  
/* 194:    */  static native void nglGetVariantArrayObjectfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 195:    */  
/* 196:196 */  public static void glGetVariantArrayObjectATI(int id, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 197:197 */    long function_pointer = caps.glGetVariantArrayObjectivATI;
/* 198:198 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 199:199 */    org.lwjgl.BufferChecks.checkBuffer(params, 4);
/* 200:200 */    nglGetVariantArrayObjectivATI(id, pname, org.lwjgl.MemoryUtil.getAddress(params), function_pointer);
/* 201:    */  }
/* 202:    */  
/* 203:    */  static native void nglGetVariantArrayObjectivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 204:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIVertexArrayObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */