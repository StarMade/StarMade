/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.DoubleBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.nio.ShortBuffer;
/*   9:    */import org.lwjgl.BufferChecks;
/*  10:    */import org.lwjgl.LWJGLUtil;
/*  11:    */import org.lwjgl.MemoryUtil;
/*  12:    */
/*  19:    */public class ARBBufferObject
/*  20:    */{
/*  21:    */  public static final int GL_STREAM_DRAW_ARB = 35040;
/*  22:    */  public static final int GL_STREAM_READ_ARB = 35041;
/*  23:    */  public static final int GL_STREAM_COPY_ARB = 35042;
/*  24:    */  public static final int GL_STATIC_DRAW_ARB = 35044;
/*  25:    */  public static final int GL_STATIC_READ_ARB = 35045;
/*  26:    */  public static final int GL_STATIC_COPY_ARB = 35046;
/*  27:    */  public static final int GL_DYNAMIC_DRAW_ARB = 35048;
/*  28:    */  public static final int GL_DYNAMIC_READ_ARB = 35049;
/*  29:    */  public static final int GL_DYNAMIC_COPY_ARB = 35050;
/*  30:    */  public static final int GL_READ_ONLY_ARB = 35000;
/*  31:    */  public static final int GL_WRITE_ONLY_ARB = 35001;
/*  32:    */  public static final int GL_READ_WRITE_ARB = 35002;
/*  33:    */  public static final int GL_BUFFER_SIZE_ARB = 34660;
/*  34:    */  public static final int GL_BUFFER_USAGE_ARB = 34661;
/*  35:    */  public static final int GL_BUFFER_ACCESS_ARB = 35003;
/*  36:    */  public static final int GL_BUFFER_MAPPED_ARB = 35004;
/*  37:    */  public static final int GL_BUFFER_MAP_POINTER_ARB = 35005;
/*  38:    */  
/*  39:    */  public static void glBindBufferARB(int target, int buffer)
/*  40:    */  {
/*  41: 41 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  42: 42 */    long function_pointer = caps.glBindBufferARB;
/*  43: 43 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  44: 44 */    StateTracker.bindBuffer(caps, target, buffer);
/*  45: 45 */    nglBindBufferARB(target, buffer, function_pointer);
/*  46:    */  }
/*  47:    */  
/*  48:    */  static native void nglBindBufferARB(int paramInt1, int paramInt2, long paramLong);
/*  49:    */  
/*  50: 50 */  public static void glDeleteBuffersARB(IntBuffer buffers) { ContextCapabilities caps = GLContext.getCapabilities();
/*  51: 51 */    long function_pointer = caps.glDeleteBuffersARB;
/*  52: 52 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  53: 53 */    BufferChecks.checkDirect(buffers);
/*  54: 54 */    nglDeleteBuffersARB(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*  55:    */  }
/*  56:    */  
/*  57:    */  static native void nglDeleteBuffersARB(int paramInt, long paramLong1, long paramLong2);
/*  58:    */  
/*  59:    */  public static void glDeleteBuffersARB(int buffer) {
/*  60: 60 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  61: 61 */    long function_pointer = caps.glDeleteBuffersARB;
/*  62: 62 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  63: 63 */    nglDeleteBuffersARB(1, APIUtil.getInt(caps, buffer), function_pointer);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public static void glGenBuffersARB(IntBuffer buffers) {
/*  67: 67 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  68: 68 */    long function_pointer = caps.glGenBuffersARB;
/*  69: 69 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  70: 70 */    BufferChecks.checkDirect(buffers);
/*  71: 71 */    nglGenBuffersARB(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*  72:    */  }
/*  73:    */  
/*  74:    */  static native void nglGenBuffersARB(int paramInt, long paramLong1, long paramLong2);
/*  75:    */  
/*  76:    */  public static int glGenBuffersARB() {
/*  77: 77 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  78: 78 */    long function_pointer = caps.glGenBuffersARB;
/*  79: 79 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  80: 80 */    IntBuffer buffers = APIUtil.getBufferInt(caps);
/*  81: 81 */    nglGenBuffersARB(1, MemoryUtil.getAddress(buffers), function_pointer);
/*  82: 82 */    return buffers.get(0);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static boolean glIsBufferARB(int buffer) {
/*  86: 86 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  87: 87 */    long function_pointer = caps.glIsBufferARB;
/*  88: 88 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  89: 89 */    boolean __result = nglIsBufferARB(buffer, function_pointer);
/*  90: 90 */    return __result;
/*  91:    */  }
/*  92:    */  
/*  93:    */  static native boolean nglIsBufferARB(int paramInt, long paramLong);
/*  94:    */  
/*  95: 95 */  public static void glBufferDataARB(int target, long data_size, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/*  96: 96 */    long function_pointer = caps.glBufferDataARB;
/*  97: 97 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  98: 98 */    nglBufferDataARB(target, data_size, 0L, usage, function_pointer);
/*  99:    */  }
/* 100:    */  
/* 101:101 */  public static void glBufferDataARB(int target, ByteBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glBufferDataARB;
/* 103:103 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    BufferChecks.checkDirect(data);
/* 105:105 */    nglBufferDataARB(target, data.remaining(), MemoryUtil.getAddress(data), usage, function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:108 */  public static void glBufferDataARB(int target, DoubleBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 109:109 */    long function_pointer = caps.glBufferDataARB;
/* 110:110 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 111:111 */    BufferChecks.checkDirect(data);
/* 112:112 */    nglBufferDataARB(target, data.remaining() << 3, MemoryUtil.getAddress(data), usage, function_pointer);
/* 113:    */  }
/* 114:    */  
/* 115:115 */  public static void glBufferDataARB(int target, FloatBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 116:116 */    long function_pointer = caps.glBufferDataARB;
/* 117:117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 118:118 */    BufferChecks.checkDirect(data);
/* 119:119 */    nglBufferDataARB(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/* 120:    */  }
/* 121:    */  
/* 122:122 */  public static void glBufferDataARB(int target, IntBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 123:123 */    long function_pointer = caps.glBufferDataARB;
/* 124:124 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 125:125 */    BufferChecks.checkDirect(data);
/* 126:126 */    nglBufferDataARB(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/* 127:    */  }
/* 128:    */  
/* 129:129 */  public static void glBufferDataARB(int target, ShortBuffer data, int usage) { ContextCapabilities caps = GLContext.getCapabilities();
/* 130:130 */    long function_pointer = caps.glBufferDataARB;
/* 131:131 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 132:132 */    BufferChecks.checkDirect(data);
/* 133:133 */    nglBufferDataARB(target, data.remaining() << 1, MemoryUtil.getAddress(data), usage, function_pointer);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static native void nglBufferDataARB(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/* 137:    */  
/* 138:138 */  public static void glBufferSubDataARB(int target, long offset, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 139:139 */    long function_pointer = caps.glBufferSubDataARB;
/* 140:140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 141:141 */    BufferChecks.checkDirect(data);
/* 142:142 */    nglBufferSubDataARB(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 143:    */  }
/* 144:    */  
/* 145:145 */  public static void glBufferSubDataARB(int target, long offset, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 146:146 */    long function_pointer = caps.glBufferSubDataARB;
/* 147:147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 148:148 */    BufferChecks.checkDirect(data);
/* 149:149 */    nglBufferSubDataARB(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 150:    */  }
/* 151:    */  
/* 152:152 */  public static void glBufferSubDataARB(int target, long offset, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glBufferSubDataARB;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    BufferChecks.checkDirect(data);
/* 156:156 */    nglBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 157:    */  }
/* 158:    */  
/* 159:159 */  public static void glBufferSubDataARB(int target, long offset, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 160:160 */    long function_pointer = caps.glBufferSubDataARB;
/* 161:161 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 162:162 */    BufferChecks.checkDirect(data);
/* 163:163 */    nglBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 164:    */  }
/* 165:    */  
/* 166:166 */  public static void glBufferSubDataARB(int target, long offset, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glBufferSubDataARB;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    BufferChecks.checkDirect(data);
/* 170:170 */    nglBufferSubDataARB(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 171:    */  }
/* 172:    */  
/* 173:    */  static native void nglBufferSubDataARB(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 174:    */  
/* 175:175 */  public static void glGetBufferSubDataARB(int target, long offset, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 176:176 */    long function_pointer = caps.glGetBufferSubDataARB;
/* 177:177 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 178:178 */    BufferChecks.checkDirect(data);
/* 179:179 */    nglGetBufferSubDataARB(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 180:    */  }
/* 181:    */  
/* 182:182 */  public static void glGetBufferSubDataARB(int target, long offset, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 183:183 */    long function_pointer = caps.glGetBufferSubDataARB;
/* 184:184 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 185:185 */    BufferChecks.checkDirect(data);
/* 186:186 */    nglGetBufferSubDataARB(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 187:    */  }
/* 188:    */  
/* 189:189 */  public static void glGetBufferSubDataARB(int target, long offset, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glGetBufferSubDataARB;
/* 191:191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    BufferChecks.checkDirect(data);
/* 193:193 */    nglGetBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 194:    */  }
/* 195:    */  
/* 196:196 */  public static void glGetBufferSubDataARB(int target, long offset, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 197:197 */    long function_pointer = caps.glGetBufferSubDataARB;
/* 198:198 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 199:199 */    BufferChecks.checkDirect(data);
/* 200:200 */    nglGetBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 201:    */  }
/* 202:    */  
/* 203:203 */  public static void glGetBufferSubDataARB(int target, long offset, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 204:204 */    long function_pointer = caps.glGetBufferSubDataARB;
/* 205:205 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 206:206 */    BufferChecks.checkDirect(data);
/* 207:207 */    nglGetBufferSubDataARB(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 208:    */  }
/* 209:    */  
/* 221:    */  static native void nglGetBufferSubDataARB(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 222:    */  
/* 233:    */  public static ByteBuffer glMapBufferARB(int target, int access, ByteBuffer old_buffer)
/* 234:    */  {
/* 235:235 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 236:236 */    long function_pointer = caps.glMapBufferARB;
/* 237:237 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 238:238 */    if (old_buffer != null)
/* 239:239 */      BufferChecks.checkDirect(old_buffer);
/* 240:240 */    ByteBuffer __result = nglMapBufferARB(target, access, GLChecks.getBufferObjectSizeARB(caps, target), old_buffer, function_pointer);
/* 241:241 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 242:    */  }
/* 243:    */  
/* 265:    */  public static ByteBuffer glMapBufferARB(int target, int access, long length, ByteBuffer old_buffer)
/* 266:    */  {
/* 267:267 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 268:268 */    long function_pointer = caps.glMapBufferARB;
/* 269:269 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 270:270 */    if (old_buffer != null)
/* 271:271 */      BufferChecks.checkDirect(old_buffer);
/* 272:272 */    ByteBuffer __result = nglMapBufferARB(target, access, length, old_buffer, function_pointer);
/* 273:273 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 274:    */  }
/* 275:    */  
/* 276:    */  static native ByteBuffer nglMapBufferARB(int paramInt1, int paramInt2, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/* 277:    */  
/* 278:278 */  public static boolean glUnmapBufferARB(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/* 279:279 */    long function_pointer = caps.glUnmapBufferARB;
/* 280:280 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 281:281 */    boolean __result = nglUnmapBufferARB(target, function_pointer);
/* 282:282 */    return __result;
/* 283:    */  }
/* 284:    */  
/* 285:    */  static native boolean nglUnmapBufferARB(int paramInt, long paramLong);
/* 286:    */  
/* 287:287 */  public static void glGetBufferParameterARB(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 288:288 */    long function_pointer = caps.glGetBufferParameterivARB;
/* 289:289 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 290:290 */    BufferChecks.checkBuffer(params, 4);
/* 291:291 */    nglGetBufferParameterivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 292:    */  }
/* 293:    */  
/* 296:    */  static native void nglGetBufferParameterivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 297:    */  
/* 299:    */  @Deprecated
/* 300:    */  public static int glGetBufferParameterARB(int target, int pname)
/* 301:    */  {
/* 302:302 */    return glGetBufferParameteriARB(target, pname);
/* 303:    */  }
/* 304:    */  
/* 305:    */  public static int glGetBufferParameteriARB(int target, int pname)
/* 306:    */  {
/* 307:307 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 308:308 */    long function_pointer = caps.glGetBufferParameterivARB;
/* 309:309 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 310:310 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 311:311 */    nglGetBufferParameterivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 312:312 */    return params.get(0);
/* 313:    */  }
/* 314:    */  
/* 315:    */  public static ByteBuffer glGetBufferPointerARB(int target, int pname) {
/* 316:316 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 317:317 */    long function_pointer = caps.glGetBufferPointervARB;
/* 318:318 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 319:319 */    ByteBuffer __result = nglGetBufferPointervARB(target, pname, GLChecks.getBufferObjectSizeARB(caps, target), function_pointer);
/* 320:320 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 321:    */  }
/* 322:    */  
/* 323:    */  static native ByteBuffer nglGetBufferPointervARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 324:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */