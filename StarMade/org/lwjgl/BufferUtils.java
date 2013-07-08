/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.nio.Buffer;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.ByteOrder;
/*   6:    */import java.nio.CharBuffer;
/*   7:    */import java.nio.DoubleBuffer;
/*   8:    */import java.nio.FloatBuffer;
/*   9:    */import java.nio.IntBuffer;
/*  10:    */import java.nio.LongBuffer;
/*  11:    */import java.nio.ShortBuffer;
/*  12:    */
/*  56:    */public final class BufferUtils
/*  57:    */{
/*  58:    */  public static ByteBuffer createByteBuffer(int size)
/*  59:    */  {
/*  60: 60 */    return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
/*  61:    */  }
/*  62:    */  
/*  68:    */  public static ShortBuffer createShortBuffer(int size)
/*  69:    */  {
/*  70: 70 */    return createByteBuffer(size << 1).asShortBuffer();
/*  71:    */  }
/*  72:    */  
/*  78:    */  public static CharBuffer createCharBuffer(int size)
/*  79:    */  {
/*  80: 80 */    return createByteBuffer(size << 1).asCharBuffer();
/*  81:    */  }
/*  82:    */  
/*  88:    */  public static IntBuffer createIntBuffer(int size)
/*  89:    */  {
/*  90: 90 */    return createByteBuffer(size << 2).asIntBuffer();
/*  91:    */  }
/*  92:    */  
/*  98:    */  public static LongBuffer createLongBuffer(int size)
/*  99:    */  {
/* 100:100 */    return createByteBuffer(size << 3).asLongBuffer();
/* 101:    */  }
/* 102:    */  
/* 108:    */  public static FloatBuffer createFloatBuffer(int size)
/* 109:    */  {
/* 110:110 */    return createByteBuffer(size << 2).asFloatBuffer();
/* 111:    */  }
/* 112:    */  
/* 118:    */  public static DoubleBuffer createDoubleBuffer(int size)
/* 119:    */  {
/* 120:120 */    return createByteBuffer(size << 3).asDoubleBuffer();
/* 121:    */  }
/* 122:    */  
/* 128:    */  public static PointerBuffer createPointerBuffer(int size)
/* 129:    */  {
/* 130:130 */    return PointerBuffer.allocateDirect(size);
/* 131:    */  }
/* 132:    */  
/* 135:    */  public static int getElementSizeExponent(Buffer buf)
/* 136:    */  {
/* 137:137 */    if ((buf instanceof ByteBuffer))
/* 138:138 */      return 0;
/* 139:139 */    if (((buf instanceof ShortBuffer)) || ((buf instanceof CharBuffer)))
/* 140:140 */      return 1;
/* 141:141 */    if (((buf instanceof FloatBuffer)) || ((buf instanceof IntBuffer)))
/* 142:142 */      return 2;
/* 143:143 */    if (((buf instanceof LongBuffer)) || ((buf instanceof DoubleBuffer))) {
/* 144:144 */      return 3;
/* 145:    */    }
/* 146:146 */    throw new IllegalStateException("Unsupported buffer type: " + buf);
/* 147:    */  }
/* 148:    */  
/* 153:    */  public static int getOffset(Buffer buffer)
/* 154:    */  {
/* 155:155 */    return buffer.position() << getElementSizeExponent(buffer);
/* 156:    */  }
/* 157:    */  
/* 158:    */  public static void zeroBuffer(ByteBuffer b)
/* 159:    */  {
/* 160:160 */    zeroBuffer0(b, b.position(), b.remaining());
/* 161:    */  }
/* 162:    */  
/* 163:    */  public static void zeroBuffer(ShortBuffer b)
/* 164:    */  {
/* 165:165 */    zeroBuffer0(b, b.position() * 2L, b.remaining() * 2L);
/* 166:    */  }
/* 167:    */  
/* 168:    */  public static void zeroBuffer(CharBuffer b)
/* 169:    */  {
/* 170:170 */    zeroBuffer0(b, b.position() * 2L, b.remaining() * 2L);
/* 171:    */  }
/* 172:    */  
/* 173:    */  public static void zeroBuffer(IntBuffer b)
/* 174:    */  {
/* 175:175 */    zeroBuffer0(b, b.position() * 4L, b.remaining() * 4L);
/* 176:    */  }
/* 177:    */  
/* 178:    */  public static void zeroBuffer(FloatBuffer b)
/* 179:    */  {
/* 180:180 */    zeroBuffer0(b, b.position() * 4L, b.remaining() * 4L);
/* 181:    */  }
/* 182:    */  
/* 183:    */  public static void zeroBuffer(LongBuffer b)
/* 184:    */  {
/* 185:185 */    zeroBuffer0(b, b.position() * 8L, b.remaining() * 8L);
/* 186:    */  }
/* 187:    */  
/* 188:    */  public static void zeroBuffer(DoubleBuffer b)
/* 189:    */  {
/* 190:190 */    zeroBuffer0(b, b.position() * 8L, b.remaining() * 8L);
/* 191:    */  }
/* 192:    */  
/* 193:    */  private static native void zeroBuffer0(Buffer paramBuffer, long paramLong1, long paramLong2);
/* 194:    */  
/* 195:    */  static native long getBufferAddress(Buffer paramBuffer);
/* 196:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.BufferUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */