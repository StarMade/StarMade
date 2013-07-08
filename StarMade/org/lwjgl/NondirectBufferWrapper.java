package org.lwjgl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public final class NondirectBufferWrapper
{
  private static final int INITIAL_BUFFER_SIZE = 1;
  private static final ThreadLocal<CachedBuffers> thread_buffer = new ThreadLocal()
  {
    protected NondirectBufferWrapper.CachedBuffers initialValue()
    {
      return new NondirectBufferWrapper.CachedBuffers(1, null);
    }
  };
  
  private static CachedBuffers getCachedBuffers(int minimum_byte_size)
  {
    CachedBuffers buffers = (CachedBuffers)thread_buffer.get();
    int current_byte_size = buffers.byte_buffer.capacity();
    if (minimum_byte_size > current_byte_size)
    {
      buffers = new CachedBuffers(minimum_byte_size, null);
      thread_buffer.set(buffers);
    }
    return buffers;
  }
  
  public static ByteBuffer wrapNoCopyBuffer(ByteBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapNoCopyDirect(buf);
  }
  
  public static ShortBuffer wrapNoCopyBuffer(ShortBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapNoCopyDirect(buf);
  }
  
  public static IntBuffer wrapNoCopyBuffer(IntBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapNoCopyDirect(buf);
  }
  
  public static LongBuffer wrapNoCopyBuffer(LongBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapNoCopyDirect(buf);
  }
  
  public static FloatBuffer wrapNoCopyBuffer(FloatBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapNoCopyDirect(buf);
  }
  
  public static DoubleBuffer wrapNoCopyBuffer(DoubleBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapNoCopyDirect(buf);
  }
  
  public static ByteBuffer wrapBuffer(ByteBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapDirect(buf);
  }
  
  public static ShortBuffer wrapBuffer(ShortBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapDirect(buf);
  }
  
  public static IntBuffer wrapBuffer(IntBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapDirect(buf);
  }
  
  public static LongBuffer wrapBuffer(LongBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapDirect(buf);
  }
  
  public static FloatBuffer wrapBuffer(FloatBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapDirect(buf);
  }
  
  public static DoubleBuffer wrapBuffer(DoubleBuffer buf, int size)
  {
    BufferChecks.checkBufferSize(buf, size);
    return wrapDirect(buf);
  }
  
  public static ByteBuffer wrapDirect(ByteBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doWrap(buffer);
    }
    return buffer;
  }
  
  public static ShortBuffer wrapDirect(ShortBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doWrap(buffer);
    }
    return buffer;
  }
  
  public static FloatBuffer wrapDirect(FloatBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doWrap(buffer);
    }
    return buffer;
  }
  
  public static IntBuffer wrapDirect(IntBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doWrap(buffer);
    }
    return buffer;
  }
  
  public static LongBuffer wrapDirect(LongBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doWrap(buffer);
    }
    return buffer;
  }
  
  public static DoubleBuffer wrapDirect(DoubleBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doWrap(buffer);
    }
    return buffer;
  }
  
  public static ByteBuffer wrapNoCopyDirect(ByteBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doNoCopyWrap(buffer);
    }
    return buffer;
  }
  
  public static ShortBuffer wrapNoCopyDirect(ShortBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doNoCopyWrap(buffer);
    }
    return buffer;
  }
  
  public static FloatBuffer wrapNoCopyDirect(FloatBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doNoCopyWrap(buffer);
    }
    return buffer;
  }
  
  public static IntBuffer wrapNoCopyDirect(IntBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doNoCopyWrap(buffer);
    }
    return buffer;
  }
  
  public static LongBuffer wrapNoCopyDirect(LongBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doNoCopyWrap(buffer);
    }
    return buffer;
  }
  
  public static DoubleBuffer wrapNoCopyDirect(DoubleBuffer buffer)
  {
    if (!buffer.isDirect()) {
      return doNoCopyWrap(buffer);
    }
    return buffer;
  }
  
  public static void copy(ByteBuffer src, ByteBuffer dst)
  {
    if ((dst != null) && (!dst.isDirect()))
    {
      int saved_position = dst.position();
      dst.put(src);
      dst.position(saved_position);
    }
  }
  
  public static void copy(ShortBuffer src, ShortBuffer dst)
  {
    if ((dst != null) && (!dst.isDirect()))
    {
      int saved_position = dst.position();
      dst.put(src);
      dst.position(saved_position);
    }
  }
  
  public static void copy(IntBuffer src, IntBuffer dst)
  {
    if ((dst != null) && (!dst.isDirect()))
    {
      int saved_position = dst.position();
      dst.put(src);
      dst.position(saved_position);
    }
  }
  
  public static void copy(FloatBuffer src, FloatBuffer dst)
  {
    if ((dst != null) && (!dst.isDirect()))
    {
      int saved_position = dst.position();
      dst.put(src);
      dst.position(saved_position);
    }
  }
  
  public static void copy(LongBuffer src, LongBuffer dst)
  {
    if ((dst != null) && (!dst.isDirect()))
    {
      int saved_position = dst.position();
      dst.put(src);
      dst.position(saved_position);
    }
  }
  
  public static void copy(DoubleBuffer src, DoubleBuffer dst)
  {
    if ((dst != null) && (!dst.isDirect()))
    {
      int saved_position = dst.position();
      dst.put(src);
      dst.position(saved_position);
    }
  }
  
  private static ByteBuffer doNoCopyWrap(ByteBuffer buffer)
  {
    ByteBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.limit(buffer.limit());
    direct_buffer.position(buffer.position());
    return direct_buffer;
  }
  
  private static ShortBuffer doNoCopyWrap(ShortBuffer buffer)
  {
    ShortBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.limit(buffer.limit());
    direct_buffer.position(buffer.position());
    return direct_buffer;
  }
  
  private static IntBuffer doNoCopyWrap(IntBuffer buffer)
  {
    IntBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.limit(buffer.limit());
    direct_buffer.position(buffer.position());
    return direct_buffer;
  }
  
  private static FloatBuffer doNoCopyWrap(FloatBuffer buffer)
  {
    FloatBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.limit(buffer.limit());
    direct_buffer.position(buffer.position());
    return direct_buffer;
  }
  
  private static LongBuffer doNoCopyWrap(LongBuffer buffer)
  {
    LongBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.limit(buffer.limit());
    direct_buffer.position(buffer.position());
    return direct_buffer;
  }
  
  private static DoubleBuffer doNoCopyWrap(DoubleBuffer buffer)
  {
    DoubleBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.limit(buffer.limit());
    direct_buffer.position(buffer.position());
    return direct_buffer;
  }
  
  private static ByteBuffer lookupBuffer(ByteBuffer buffer)
  {
    return getCachedBuffers(buffer.remaining()).byte_buffer;
  }
  
  private static ByteBuffer doWrap(ByteBuffer buffer)
  {
    ByteBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.clear();
    int saved_position = buffer.position();
    direct_buffer.put(buffer);
    buffer.position(saved_position);
    direct_buffer.flip();
    return direct_buffer;
  }
  
  private static ShortBuffer lookupBuffer(ShortBuffer buffer)
  {
    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 2);
    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.short_buffer_little : buffers.short_buffer_big;
  }
  
  private static ShortBuffer doWrap(ShortBuffer buffer)
  {
    ShortBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.clear();
    int saved_position = buffer.position();
    direct_buffer.put(buffer);
    buffer.position(saved_position);
    direct_buffer.flip();
    return direct_buffer;
  }
  
  private static FloatBuffer lookupBuffer(FloatBuffer buffer)
  {
    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 4);
    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.float_buffer_little : buffers.float_buffer_big;
  }
  
  private static FloatBuffer doWrap(FloatBuffer buffer)
  {
    FloatBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.clear();
    int saved_position = buffer.position();
    direct_buffer.put(buffer);
    buffer.position(saved_position);
    direct_buffer.flip();
    return direct_buffer;
  }
  
  private static IntBuffer lookupBuffer(IntBuffer buffer)
  {
    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 4);
    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.int_buffer_little : buffers.int_buffer_big;
  }
  
  private static IntBuffer doWrap(IntBuffer buffer)
  {
    IntBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.clear();
    int saved_position = buffer.position();
    direct_buffer.put(buffer);
    buffer.position(saved_position);
    direct_buffer.flip();
    return direct_buffer;
  }
  
  private static LongBuffer lookupBuffer(LongBuffer buffer)
  {
    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 8);
    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.long_buffer_little : buffers.long_buffer_big;
  }
  
  private static LongBuffer doWrap(LongBuffer buffer)
  {
    LongBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.clear();
    int saved_position = buffer.position();
    direct_buffer.put(buffer);
    buffer.position(saved_position);
    direct_buffer.flip();
    return direct_buffer;
  }
  
  private static DoubleBuffer lookupBuffer(DoubleBuffer buffer)
  {
    CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 8);
    return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.double_buffer_little : buffers.double_buffer_big;
  }
  
  private static DoubleBuffer doWrap(DoubleBuffer buffer)
  {
    DoubleBuffer direct_buffer = lookupBuffer(buffer);
    direct_buffer.clear();
    int saved_position = buffer.position();
    direct_buffer.put(buffer);
    buffer.position(saved_position);
    direct_buffer.flip();
    return direct_buffer;
  }
  
  private static final class CachedBuffers
  {
    private final ByteBuffer byte_buffer;
    private final ShortBuffer short_buffer_big;
    private final IntBuffer int_buffer_big;
    private final FloatBuffer float_buffer_big;
    private final LongBuffer long_buffer_big;
    private final DoubleBuffer double_buffer_big;
    private final ShortBuffer short_buffer_little;
    private final IntBuffer int_buffer_little;
    private final FloatBuffer float_buffer_little;
    private final LongBuffer long_buffer_little;
    private final DoubleBuffer double_buffer_little;
    
    private CachedBuffers(int size)
    {
      this.byte_buffer = ByteBuffer.allocateDirect(size);
      this.short_buffer_big = this.byte_buffer.asShortBuffer();
      this.int_buffer_big = this.byte_buffer.asIntBuffer();
      this.float_buffer_big = this.byte_buffer.asFloatBuffer();
      this.long_buffer_big = this.byte_buffer.asLongBuffer();
      this.double_buffer_big = this.byte_buffer.asDoubleBuffer();
      this.byte_buffer.order(ByteOrder.LITTLE_ENDIAN);
      this.short_buffer_little = this.byte_buffer.asShortBuffer();
      this.int_buffer_little = this.byte_buffer.asIntBuffer();
      this.float_buffer_little = this.byte_buffer.asFloatBuffer();
      this.long_buffer_little = this.byte_buffer.asLongBuffer();
      this.double_buffer_little = this.byte_buffer.asDoubleBuffer();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.NondirectBufferWrapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */