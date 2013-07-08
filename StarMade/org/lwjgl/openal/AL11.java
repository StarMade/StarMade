/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.LWJGLException;
/*   7:    */import org.lwjgl.MemoryUtil;
/*   8:    */
/*  56:    */public final class AL11
/*  57:    */{
/*  58:    */  public static final int AL_SEC_OFFSET = 4132;
/*  59:    */  public static final int AL_SAMPLE_OFFSET = 4133;
/*  60:    */  public static final int AL_BYTE_OFFSET = 4134;
/*  61:    */  public static final int AL_STATIC = 4136;
/*  62:    */  public static final int AL_STREAMING = 4137;
/*  63:    */  public static final int AL_UNDETERMINED = 4144;
/*  64:    */  public static final int AL_ILLEGAL_COMMAND = 40964;
/*  65:    */  public static final int AL_SPEED_OF_SOUND = 49155;
/*  66:    */  public static final int AL_LINEAR_DISTANCE = 53251;
/*  67:    */  public static final int AL_LINEAR_DISTANCE_CLAMPED = 53252;
/*  68:    */  public static final int AL_EXPONENT_DISTANCE = 53253;
/*  69:    */  public static final int AL_EXPONENT_DISTANCE_CLAMPED = 53254;
/*  70:    */  
/*  71:    */  static native void initNativeStubs()
/*  72:    */    throws LWJGLException;
/*  73:    */  
/*  74:    */  public static void alListener3i(int pname, int v1, int v2, int v3)
/*  75:    */  {
/*  76: 76 */    nalListener3i(pname, v1, v2, v3);
/*  77:    */  }
/*  78:    */  
/*  82:    */  static native void nalListener3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*  83:    */  
/*  86:    */  public static void alGetListeneri(int pname, FloatBuffer intdata)
/*  87:    */  {
/*  88: 88 */    BufferChecks.checkBuffer(intdata, 1);
/*  89: 89 */    nalGetListeneriv(pname, MemoryUtil.getAddress(intdata));
/*  90:    */  }
/*  91:    */  
/*  96:    */  static native void nalGetListeneriv(int paramInt, long paramLong);
/*  97:    */  
/* 102:    */  public static void alSource3i(int source, int pname, int v1, int v2, int v3)
/* 103:    */  {
/* 104:104 */    nalSource3i(source, pname, v1, v2, v3);
/* 105:    */  }
/* 106:    */  
/* 110:    */  static native void nalSource3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/* 111:    */  
/* 115:    */  public static void alSource(int source, int pname, IntBuffer value)
/* 116:    */  {
/* 117:117 */    BufferChecks.checkBuffer(value, 1);
/* 118:118 */    nalSourceiv(source, pname, MemoryUtil.getAddress(value));
/* 119:    */  }
/* 120:    */  
/* 125:    */  static native void nalSourceiv(int paramInt1, int paramInt2, long paramLong);
/* 126:    */  
/* 130:    */  public static void alBufferf(int buffer, int pname, float value)
/* 131:    */  {
/* 132:132 */    nalBufferf(buffer, pname, value);
/* 133:    */  }
/* 134:    */  
/* 140:    */  static native void nalBufferf(int paramInt1, int paramInt2, float paramFloat);
/* 141:    */  
/* 146:    */  public static void alBuffer3f(int buffer, int pname, float v1, float v2, float v3)
/* 147:    */  {
/* 148:148 */    nalBuffer3f(buffer, pname, v1, v2, v3);
/* 149:    */  }
/* 150:    */  
/* 155:    */  static native void nalBuffer3f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3);
/* 156:    */  
/* 160:    */  public static void alBuffer(int buffer, int pname, FloatBuffer value)
/* 161:    */  {
/* 162:162 */    BufferChecks.checkBuffer(value, 1);
/* 163:163 */    nalBufferfv(buffer, pname, MemoryUtil.getAddress(value));
/* 164:    */  }
/* 165:    */  
/* 170:    */  static native void nalBufferfv(int paramInt1, int paramInt2, long paramLong);
/* 171:    */  
/* 175:    */  public static void alBufferi(int buffer, int pname, int value)
/* 176:    */  {
/* 177:177 */    nalBufferi(buffer, pname, value);
/* 178:    */  }
/* 179:    */  
/* 185:    */  static native void nalBufferi(int paramInt1, int paramInt2, int paramInt3);
/* 186:    */  
/* 191:    */  public static void alBuffer3i(int buffer, int pname, int v1, int v2, int v3)
/* 192:    */  {
/* 193:193 */    nalBuffer3i(buffer, pname, v1, v2, v3);
/* 194:    */  }
/* 195:    */  
/* 200:    */  static native void nalBuffer3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/* 201:    */  
/* 205:    */  public static void alBuffer(int buffer, int pname, IntBuffer value)
/* 206:    */  {
/* 207:207 */    BufferChecks.checkBuffer(value, 1);
/* 208:208 */    nalBufferiv(buffer, pname, MemoryUtil.getAddress(value));
/* 209:    */  }
/* 210:    */  
/* 215:    */  static native void nalBufferiv(int paramInt1, int paramInt2, long paramLong);
/* 216:    */  
/* 220:    */  public static int alGetBufferi(int buffer, int pname)
/* 221:    */  {
/* 222:222 */    int __result = nalGetBufferi(buffer, pname);
/* 223:223 */    return __result;
/* 224:    */  }
/* 225:    */  
/* 228:    */  static native int nalGetBufferi(int paramInt1, int paramInt2);
/* 229:    */  
/* 232:    */  public static void alGetBuffer(int buffer, int pname, IntBuffer values)
/* 233:    */  {
/* 234:234 */    BufferChecks.checkBuffer(values, 1);
/* 235:235 */    nalGetBufferiv(buffer, pname, MemoryUtil.getAddress(values));
/* 236:    */  }
/* 237:    */  
/* 242:    */  static native void nalGetBufferiv(int paramInt1, int paramInt2, long paramLong);
/* 243:    */  
/* 247:    */  public static float alGetBufferf(int buffer, int pname)
/* 248:    */  {
/* 249:249 */    float __result = nalGetBufferf(buffer, pname);
/* 250:250 */    return __result;
/* 251:    */  }
/* 252:    */  
/* 256:    */  static native float nalGetBufferf(int paramInt1, int paramInt2);
/* 257:    */  
/* 261:    */  public static void alGetBuffer(int buffer, int pname, FloatBuffer values)
/* 262:    */  {
/* 263:263 */    BufferChecks.checkBuffer(values, 1);
/* 264:264 */    nalGetBufferfv(buffer, pname, MemoryUtil.getAddress(values));
/* 265:    */  }
/* 266:    */  
/* 275:    */  static native void nalGetBufferfv(int paramInt1, int paramInt2, long paramLong);
/* 276:    */  
/* 284:    */  public static void alSpeedOfSound(float value)
/* 285:    */  {
/* 286:286 */    nalSpeedOfSound(value);
/* 287:    */  }
/* 288:    */  
/* 289:    */  static native void nalSpeedOfSound(float paramFloat);
/* 290:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.AL11
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */