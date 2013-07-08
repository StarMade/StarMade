package org.lwjgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public final class BufferUtils
{
  public static ByteBuffer createByteBuffer(int size)
  {
    return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
  }
  
  public static ShortBuffer createShortBuffer(int size)
  {
    return createByteBuffer(size << 1).asShortBuffer();
  }
  
  public static CharBuffer createCharBuffer(int size)
  {
    return createByteBuffer(size << 1).asCharBuffer();
  }
  
  public static IntBuffer createIntBuffer(int size)
  {
    return createByteBuffer(size << 2).asIntBuffer();
  }
  
  public static LongBuffer createLongBuffer(int size)
  {
    return createByteBuffer(size << 3).asLongBuffer();
  }
  
  public static FloatBuffer createFloatBuffer(int size)
  {
    return createByteBuffer(size << 2).asFloatBuffer();
  }
  
  public static DoubleBuffer createDoubleBuffer(int size)
  {
    return createByteBuffer(size << 3).asDoubleBuffer();
  }
  
  public static PointerBuffer createPointerBuffer(int size)
  {
    return PointerBuffer.allocateDirect(size);
  }
  
  public static int getElementSizeExponent(Buffer buf)
  {
    if ((buf instanceof ByteBuffer)) {
      return 0;
    }
    if (((buf instanceof ShortBuffer)) || ((buf instanceof CharBuffer))) {
      return 1;
    }
    if (((buf instanceof FloatBuffer)) || ((buf instanceof IntBuffer))) {
      return 2;
    }
    if (((buf instanceof LongBuffer)) || ((buf instanceof DoubleBuffer))) {
      return 3;
    }
    throw new IllegalStateException("Unsupported buffer type: " + buf);
  }
  
  public static int getOffset(Buffer buffer)
  {
    return buffer.position() << getElementSizeExponent(buffer);
  }
  
  public static void zeroBuffer(ByteBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position(), local_b.remaining());
  }
  
  public static void zeroBuffer(ShortBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position() * 2L, local_b.remaining() * 2L);
  }
  
  public static void zeroBuffer(CharBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position() * 2L, local_b.remaining() * 2L);
  }
  
  public static void zeroBuffer(IntBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position() * 4L, local_b.remaining() * 4L);
  }
  
  public static void zeroBuffer(FloatBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position() * 4L, local_b.remaining() * 4L);
  }
  
  public static void zeroBuffer(LongBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position() * 8L, local_b.remaining() * 8L);
  }
  
  public static void zeroBuffer(DoubleBuffer local_b)
  {
    zeroBuffer0(local_b, local_b.position() * 8L, local_b.remaining() * 8L);
  }
  
  private static native void zeroBuffer0(Buffer paramBuffer, long paramLong1, long paramLong2);
  
  static native long getBufferAddress(Buffer paramBuffer);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.BufferUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */