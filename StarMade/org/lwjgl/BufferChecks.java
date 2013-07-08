/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.nio.Buffer;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.DoubleBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.nio.LongBuffer;
/*   9:    */import java.nio.ShortBuffer;
/*  10:    */
/*  53:    */public class BufferChecks
/*  54:    */{
/*  55:    */  public static void checkFunctionAddress(long pointer)
/*  56:    */  {
/*  57: 57 */    if ((LWJGLUtil.CHECKS) && (pointer == 0L)) {
/*  58: 58 */      throw new IllegalStateException("Function is not supported");
/*  59:    */    }
/*  60:    */  }
/*  61:    */  
/*  64:    */  public static void checkNullTerminated(ByteBuffer buf)
/*  65:    */  {
/*  66: 66 */    if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0)) {
/*  67: 67 */      throw new IllegalArgumentException("Missing null termination");
/*  68:    */    }
/*  69:    */  }
/*  70:    */  
/*  71:    */  public static void checkNullTerminated(ByteBuffer buf, int count) {
/*  72: 72 */    if (LWJGLUtil.CHECKS) {
/*  73: 73 */      int nullFound = 0;
/*  74: 74 */      for (int i = buf.position(); i < buf.limit(); i++) {
/*  75: 75 */        if (buf.get(i) == 0) {
/*  76: 76 */          nullFound++;
/*  77:    */        }
/*  78:    */      }
/*  79: 79 */      if (nullFound < count) {
/*  80: 80 */        throw new IllegalArgumentException("Missing null termination");
/*  81:    */      }
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static void checkNullTerminated(IntBuffer buf) {
/*  86: 86 */    if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0)) {
/*  87: 87 */      throw new IllegalArgumentException("Missing null termination");
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  91:    */  public static void checkNullTerminated(LongBuffer buf)
/*  92:    */  {
/*  93: 93 */    if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0L)) {
/*  94: 94 */      throw new IllegalArgumentException("Missing null termination");
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98:    */  public static void checkNullTerminated(PointerBuffer buf)
/*  99:    */  {
/* 100:100 */    if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0L)) {
/* 101:101 */      throw new IllegalArgumentException("Missing null termination");
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static void checkNotNull(Object o) {
/* 106:106 */    if ((LWJGLUtil.CHECKS) && (o == null)) {
/* 107:107 */      throw new IllegalArgumentException("Null argument");
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 112:    */  public static void checkDirect(ByteBuffer buf)
/* 113:    */  {
/* 114:114 */    if ((LWJGLUtil.CHECKS) && (!buf.isDirect())) {
/* 115:115 */      throw new IllegalArgumentException("ByteBuffer is not direct");
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 119:    */  public static void checkDirect(ShortBuffer buf) {
/* 120:120 */    if ((LWJGLUtil.CHECKS) && (!buf.isDirect())) {
/* 121:121 */      throw new IllegalArgumentException("ShortBuffer is not direct");
/* 122:    */    }
/* 123:    */  }
/* 124:    */  
/* 125:    */  public static void checkDirect(IntBuffer buf) {
/* 126:126 */    if ((LWJGLUtil.CHECKS) && (!buf.isDirect())) {
/* 127:127 */      throw new IllegalArgumentException("IntBuffer is not direct");
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 131:    */  public static void checkDirect(LongBuffer buf) {
/* 132:132 */    if ((LWJGLUtil.CHECKS) && (!buf.isDirect())) {
/* 133:133 */      throw new IllegalArgumentException("LongBuffer is not direct");
/* 134:    */    }
/* 135:    */  }
/* 136:    */  
/* 137:    */  public static void checkDirect(FloatBuffer buf) {
/* 138:138 */    if ((LWJGLUtil.CHECKS) && (!buf.isDirect())) {
/* 139:139 */      throw new IllegalArgumentException("FloatBuffer is not direct");
/* 140:    */    }
/* 141:    */  }
/* 142:    */  
/* 143:    */  public static void checkDirect(DoubleBuffer buf) {
/* 144:144 */    if ((LWJGLUtil.CHECKS) && (!buf.isDirect())) {
/* 145:145 */      throw new IllegalArgumentException("DoubleBuffer is not direct");
/* 146:    */    }
/* 147:    */  }
/* 148:    */  
/* 150:    */  public static void checkDirect(PointerBuffer buf) {}
/* 151:    */  
/* 152:    */  public static void checkArray(Object[] array)
/* 153:    */  {
/* 154:154 */    if ((LWJGLUtil.CHECKS) && ((array == null) || (array.length == 0))) {
/* 155:155 */      throw new IllegalArgumentException("Invalid array");
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 160:    */  private static void throwBufferSizeException(Buffer buf, int size)
/* 161:    */  {
/* 162:162 */    throw new IllegalArgumentException("Number of remaining buffer elements is " + buf.remaining() + ", must be at least " + size + ". Because at most " + size + " elements can be returned, a buffer with at least " + size + " elements is required, regardless of actual returned element count");
/* 163:    */  }
/* 164:    */  
/* 165:    */  private static void throwBufferSizeException(PointerBuffer buf, int size) {
/* 166:166 */    throw new IllegalArgumentException("Number of remaining pointer buffer elements is " + buf.remaining() + ", must be at least " + size);
/* 167:    */  }
/* 168:    */  
/* 169:    */  private static void throwArraySizeException(Object[] array, int size) {
/* 170:170 */    throw new IllegalArgumentException("Number of array elements is " + array.length + ", must be at least " + size);
/* 171:    */  }
/* 172:    */  
/* 173:    */  private static void throwArraySizeException(long[] array, int size) {
/* 174:174 */    throw new IllegalArgumentException("Number of array elements is " + array.length + ", must be at least " + size);
/* 175:    */  }
/* 176:    */  
/* 186:    */  public static void checkBufferSize(Buffer buf, int size)
/* 187:    */  {
/* 188:188 */    if ((LWJGLUtil.CHECKS) && (buf.remaining() < size)) {
/* 189:189 */      throwBufferSizeException(buf, size);
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 197:    */  public static int checkBuffer(Buffer buffer, int size)
/* 198:    */  {
/* 199:    */    int posShift;
/* 200:    */    
/* 204:204 */    if ((buffer instanceof ByteBuffer)) {
/* 205:205 */      checkBuffer((ByteBuffer)buffer, size);
/* 206:206 */      posShift = 0; } else { int posShift;
/* 207:207 */      if ((buffer instanceof ShortBuffer)) {
/* 208:208 */        checkBuffer((ShortBuffer)buffer, size);
/* 209:209 */        posShift = 1; } else { int posShift;
/* 210:210 */        if ((buffer instanceof IntBuffer)) {
/* 211:211 */          checkBuffer((IntBuffer)buffer, size);
/* 212:212 */          posShift = 2; } else { int posShift;
/* 213:213 */          if ((buffer instanceof LongBuffer)) {
/* 214:214 */            checkBuffer((LongBuffer)buffer, size);
/* 215:215 */            posShift = 4; } else { int posShift;
/* 216:216 */            if ((buffer instanceof FloatBuffer)) {
/* 217:217 */              checkBuffer((FloatBuffer)buffer, size);
/* 218:218 */              posShift = 2; } else { int posShift;
/* 219:219 */              if ((buffer instanceof DoubleBuffer)) {
/* 220:220 */                checkBuffer((DoubleBuffer)buffer, size);
/* 221:221 */                posShift = 4;
/* 222:    */              } else {
/* 223:223 */                throw new IllegalArgumentException("Unsupported Buffer type specified: " + buffer.getClass()); } } } } } }
/* 224:    */    int posShift;
/* 225:225 */    return buffer.position() << posShift;
/* 226:    */  }
/* 227:    */  
/* 228:    */  public static void checkBuffer(ByteBuffer buf, int size) {
/* 229:229 */    if (LWJGLUtil.CHECKS) {
/* 230:230 */      checkBufferSize(buf, size);
/* 231:231 */      checkDirect(buf);
/* 232:    */    }
/* 233:    */  }
/* 234:    */  
/* 235:    */  public static void checkBuffer(ShortBuffer buf, int size) {
/* 236:236 */    if (LWJGLUtil.CHECKS) {
/* 237:237 */      checkBufferSize(buf, size);
/* 238:238 */      checkDirect(buf);
/* 239:    */    }
/* 240:    */  }
/* 241:    */  
/* 242:    */  public static void checkBuffer(IntBuffer buf, int size) {
/* 243:243 */    if (LWJGLUtil.CHECKS) {
/* 244:244 */      checkBufferSize(buf, size);
/* 245:245 */      checkDirect(buf);
/* 246:    */    }
/* 247:    */  }
/* 248:    */  
/* 249:    */  public static void checkBuffer(LongBuffer buf, int size) {
/* 250:250 */    if (LWJGLUtil.CHECKS) {
/* 251:251 */      checkBufferSize(buf, size);
/* 252:252 */      checkDirect(buf);
/* 253:    */    }
/* 254:    */  }
/* 255:    */  
/* 256:    */  public static void checkBuffer(FloatBuffer buf, int size) {
/* 257:257 */    if (LWJGLUtil.CHECKS) {
/* 258:258 */      checkBufferSize(buf, size);
/* 259:259 */      checkDirect(buf);
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 263:    */  public static void checkBuffer(DoubleBuffer buf, int size) {
/* 264:264 */    if (LWJGLUtil.CHECKS) {
/* 265:265 */      checkBufferSize(buf, size);
/* 266:266 */      checkDirect(buf);
/* 267:    */    }
/* 268:    */  }
/* 269:    */  
/* 270:    */  public static void checkBuffer(PointerBuffer buf, int size) {
/* 271:271 */    if ((LWJGLUtil.CHECKS) && (buf.remaining() < size)) {
/* 272:272 */      throwBufferSizeException(buf, size);
/* 273:    */    }
/* 274:    */  }
/* 275:    */  
/* 276:    */  public static void checkArray(Object[] array, int size) {
/* 277:277 */    if ((LWJGLUtil.CHECKS) && (array.length < size))
/* 278:278 */      throwArraySizeException(array, size);
/* 279:    */  }
/* 280:    */  
/* 281:    */  public static void checkArray(long[] array, int size) {
/* 282:282 */    if ((LWJGLUtil.CHECKS) && (array.length < size)) {
/* 283:283 */      throwArraySizeException(array, size);
/* 284:    */    }
/* 285:    */  }
/* 286:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.BufferChecks
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */