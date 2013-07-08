/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.LongBuffer;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.lwjgl.LWJGLUtil;
/*  10:    */import org.lwjgl.MemoryUtil;
/*  11:    */
/*  48:    */final class APIUtil
/*  49:    */{
/*  50:    */  private static final int INITIAL_BUFFER_SIZE = 256;
/*  51:    */  private static final int INITIAL_LENGTHS_SIZE = 4;
/*  52:    */  private static final int BUFFERS_SIZE = 32;
/*  53:    */  private char[] array;
/*  54:    */  private ByteBuffer buffer;
/*  55:    */  private IntBuffer lengths;
/*  56:    */  private final IntBuffer ints;
/*  57:    */  private final LongBuffer longs;
/*  58:    */  private final FloatBuffer floats;
/*  59:    */  private final DoubleBuffer doubles;
/*  60:    */  
/*  61:    */  APIUtil()
/*  62:    */  {
/*  63: 63 */    this.array = new char[256];
/*  64: 64 */    this.buffer = BufferUtils.createByteBuffer(256);
/*  65: 65 */    this.lengths = BufferUtils.createIntBuffer(4);
/*  66:    */    
/*  67: 67 */    this.ints = BufferUtils.createIntBuffer(32);
/*  68: 68 */    this.longs = BufferUtils.createLongBuffer(32);
/*  69:    */    
/*  70: 70 */    this.floats = BufferUtils.createFloatBuffer(32);
/*  71: 71 */    this.doubles = BufferUtils.createDoubleBuffer(32);
/*  72:    */  }
/*  73:    */  
/*  74:    */  private static char[] getArray(ContextCapabilities caps, int size) {
/*  75: 75 */    char[] array = caps.util.array;
/*  76:    */    
/*  77: 77 */    if (array.length < size) {
/*  78: 78 */      int sizeNew = array.length << 1;
/*  79: 79 */      while (sizeNew < size) {
/*  80: 80 */        sizeNew <<= 1;
/*  81:    */      }
/*  82: 82 */      array = new char[size];
/*  83: 83 */      caps.util.array = array;
/*  84:    */    }
/*  85:    */    
/*  86: 86 */    return array;
/*  87:    */  }
/*  88:    */  
/*  89:    */  static ByteBuffer getBufferByte(ContextCapabilities caps, int size) {
/*  90: 90 */    ByteBuffer buffer = caps.util.buffer;
/*  91:    */    
/*  92: 92 */    if (buffer.capacity() < size) {
/*  93: 93 */      int sizeNew = buffer.capacity() << 1;
/*  94: 94 */      while (sizeNew < size) {
/*  95: 95 */        sizeNew <<= 1;
/*  96:    */      }
/*  97: 97 */      buffer = BufferUtils.createByteBuffer(size);
/*  98: 98 */      caps.util.buffer = buffer;
/*  99:    */    } else {
/* 100:100 */      buffer.clear();
/* 101:    */    }
/* 102:102 */    return buffer;
/* 103:    */  }
/* 104:    */  
/* 105:    */  private static ByteBuffer getBufferByteOffset(ContextCapabilities caps, int size) {
/* 106:106 */    ByteBuffer buffer = caps.util.buffer;
/* 107:    */    
/* 108:108 */    if (buffer.capacity() < size) {
/* 109:109 */      int sizeNew = buffer.capacity() << 1;
/* 110:110 */      while (sizeNew < size) {
/* 111:111 */        sizeNew <<= 1;
/* 112:    */      }
/* 113:113 */      ByteBuffer bufferNew = BufferUtils.createByteBuffer(size);
/* 114:114 */      bufferNew.put(buffer);
/* 115:115 */      caps.util.buffer = (buffer = bufferNew);
/* 116:    */    } else {
/* 117:117 */      buffer.position(buffer.limit());
/* 118:118 */      buffer.limit(buffer.capacity());
/* 119:    */    }
/* 120:    */    
/* 121:121 */    return buffer;
/* 122:    */  }
/* 123:    */  
/* 124:124 */  static IntBuffer getBufferInt(ContextCapabilities caps) { return caps.util.ints; }
/* 125:    */  
/* 126:126 */  static LongBuffer getBufferLong(ContextCapabilities caps) { return caps.util.longs; }
/* 127:    */  
/* 128:128 */  static FloatBuffer getBufferFloat(ContextCapabilities caps) { return caps.util.floats; }
/* 129:    */  
/* 130:130 */  static DoubleBuffer getBufferDouble(ContextCapabilities caps) { return caps.util.doubles; }
/* 131:    */  
/* 132:    */  static IntBuffer getLengths(ContextCapabilities caps) {
/* 133:133 */    return getLengths(caps, 1);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static IntBuffer getLengths(ContextCapabilities caps, int size) {
/* 137:137 */    IntBuffer lengths = caps.util.lengths;
/* 138:    */    
/* 139:139 */    if (lengths.capacity() < size) {
/* 140:140 */      int sizeNew = lengths.capacity();
/* 141:141 */      while (sizeNew < size) {
/* 142:142 */        sizeNew <<= 1;
/* 143:    */      }
/* 144:144 */      lengths = BufferUtils.createIntBuffer(size);
/* 145:145 */      caps.util.lengths = lengths;
/* 146:    */    } else {
/* 147:147 */      lengths.clear();
/* 148:    */    }
/* 149:149 */    return lengths;
/* 150:    */  }
/* 151:    */  
/* 157:    */  private static ByteBuffer encode(ByteBuffer buffer, CharSequence string)
/* 158:    */  {
/* 159:159 */    for (int i = 0; i < string.length(); i++) {
/* 160:160 */      char c = string.charAt(i);
/* 161:161 */      if ((LWJGLUtil.DEBUG) && ('' <= c)) {
/* 162:162 */        buffer.put((byte)26);
/* 163:    */      } else {
/* 164:164 */        buffer.put((byte)c);
/* 165:    */      }
/* 166:    */    }
/* 167:167 */    return buffer;
/* 168:    */  }
/* 169:    */  
/* 176:    */  static String getString(ContextCapabilities caps, ByteBuffer buffer)
/* 177:    */  {
/* 178:178 */    int length = buffer.remaining();
/* 179:179 */    char[] charArray = getArray(caps, length);
/* 180:    */    
/* 181:181 */    for (int i = buffer.position(); i < buffer.limit(); i++) {
/* 182:182 */      charArray[(i - buffer.position())] = ((char)buffer.get(i));
/* 183:    */    }
/* 184:184 */    return new String(charArray, 0, length);
/* 185:    */  }
/* 186:    */  
/* 193:    */  static long getBuffer(ContextCapabilities caps, CharSequence string)
/* 194:    */  {
/* 195:195 */    ByteBuffer buffer = encode(getBufferByte(caps, string.length()), string);
/* 196:196 */    buffer.flip();
/* 197:197 */    return MemoryUtil.getAddress0(buffer);
/* 198:    */  }
/* 199:    */  
/* 206:    */  static long getBuffer(ContextCapabilities caps, CharSequence string, int offset)
/* 207:    */  {
/* 208:208 */    ByteBuffer buffer = encode(getBufferByteOffset(caps, offset + string.length()), string);
/* 209:209 */    buffer.flip();
/* 210:210 */    return MemoryUtil.getAddress(buffer);
/* 211:    */  }
/* 212:    */  
/* 219:    */  static long getBufferNT(ContextCapabilities caps, CharSequence string)
/* 220:    */  {
/* 221:221 */    ByteBuffer buffer = encode(getBufferByte(caps, string.length() + 1), string);
/* 222:222 */    buffer.put((byte)0);
/* 223:223 */    buffer.flip();
/* 224:224 */    return MemoryUtil.getAddress0(buffer);
/* 225:    */  }
/* 226:    */  
/* 227:    */  static int getTotalLength(CharSequence[] strings) {
/* 228:228 */    int length = 0;
/* 229:229 */    for (CharSequence string : strings) {
/* 230:230 */      length += string.length();
/* 231:    */    }
/* 232:232 */    return length;
/* 233:    */  }
/* 234:    */  
/* 241:    */  static long getBuffer(ContextCapabilities caps, CharSequence[] strings)
/* 242:    */  {
/* 243:243 */    ByteBuffer buffer = getBufferByte(caps, getTotalLength(strings));
/* 244:    */    
/* 245:245 */    for (CharSequence string : strings) {
/* 246:246 */      encode(buffer, string);
/* 247:    */    }
/* 248:248 */    buffer.flip();
/* 249:249 */    return MemoryUtil.getAddress0(buffer);
/* 250:    */  }
/* 251:    */  
/* 258:    */  static long getBufferNT(ContextCapabilities caps, CharSequence[] strings)
/* 259:    */  {
/* 260:260 */    ByteBuffer buffer = getBufferByte(caps, getTotalLength(strings) + strings.length);
/* 261:    */    
/* 262:262 */    for (CharSequence string : strings) {
/* 263:263 */      encode(buffer, string);
/* 264:264 */      buffer.put((byte)0);
/* 265:    */    }
/* 266:    */    
/* 267:267 */    buffer.flip();
/* 268:268 */    return MemoryUtil.getAddress0(buffer);
/* 269:    */  }
/* 270:    */  
/* 277:    */  static long getLengths(ContextCapabilities caps, CharSequence[] strings)
/* 278:    */  {
/* 279:279 */    IntBuffer buffer = getLengths(caps, strings.length);
/* 280:    */    
/* 281:281 */    for (CharSequence string : strings) {
/* 282:282 */      buffer.put(string.length());
/* 283:    */    }
/* 284:284 */    buffer.flip();
/* 285:285 */    return MemoryUtil.getAddress0(buffer);
/* 286:    */  }
/* 287:    */  
/* 288:    */  static long getInt(ContextCapabilities caps, int value) {
/* 289:289 */    return MemoryUtil.getAddress0(getBufferInt(caps).put(0, value));
/* 290:    */  }
/* 291:    */  
/* 292:    */  static long getBufferByte0(ContextCapabilities caps) {
/* 293:293 */    return MemoryUtil.getAddress0(getBufferByte(caps, 0));
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APIUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */