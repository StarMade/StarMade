package org.lwjgl;

import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ReadOnlyBufferException;

public class PointerBuffer
  implements Comparable
{
  private static final boolean is64Bit;
  protected final ByteBuffer pointers;
  protected final Buffer view;
  protected final IntBuffer view32;
  protected final LongBuffer view64;
  
  public PointerBuffer(int capacity)
  {
    this(BufferUtils.createByteBuffer(capacity * getPointerSize()));
  }
  
  public PointerBuffer(ByteBuffer source)
  {
    if (LWJGLUtil.CHECKS) {
      checkSource(source);
    }
    this.pointers = source.slice().order(source.order());
    if (is64Bit)
    {
      this.view32 = null;
      this.view = (this.view64 = this.pointers.asLongBuffer());
    }
    else
    {
      this.view = (this.view32 = this.pointers.asIntBuffer());
      this.view64 = null;
    }
  }
  
  private static void checkSource(ByteBuffer source)
  {
    if (!source.isDirect()) {
      throw new IllegalArgumentException("The source buffer is not direct.");
    }
    int alignment = is64Bit ? 8 : 4;
    if (((MemoryUtil.getAddress0(source) + source.position()) % alignment != 0L) || (source.remaining() % alignment != 0)) {
      throw new IllegalArgumentException("The source buffer is not aligned to " + alignment + " bytes.");
    }
  }
  
  public ByteBuffer getBuffer()
  {
    return this.pointers;
  }
  
  public static boolean is64Bit()
  {
    return is64Bit;
  }
  
  public static int getPointerSize()
  {
    return is64Bit ? 8 : 4;
  }
  
  public final int capacity()
  {
    return this.view.capacity();
  }
  
  public final int position()
  {
    return this.view.position();
  }
  
  public final int positionByte()
  {
    return position() * getPointerSize();
  }
  
  public final PointerBuffer position(int newPosition)
  {
    this.view.position(newPosition);
    return this;
  }
  
  public final int limit()
  {
    return this.view.limit();
  }
  
  public final PointerBuffer limit(int newLimit)
  {
    this.view.limit(newLimit);
    return this;
  }
  
  public final PointerBuffer mark()
  {
    this.view.mark();
    return this;
  }
  
  public final PointerBuffer reset()
  {
    this.view.reset();
    return this;
  }
  
  public final PointerBuffer clear()
  {
    this.view.clear();
    return this;
  }
  
  public final PointerBuffer flip()
  {
    this.view.flip();
    return this;
  }
  
  public final PointerBuffer rewind()
  {
    this.view.rewind();
    return this;
  }
  
  public final int remaining()
  {
    return this.view.remaining();
  }
  
  public final int remainingByte()
  {
    return remaining() * getPointerSize();
  }
  
  public final boolean hasRemaining()
  {
    return this.view.hasRemaining();
  }
  
  public static PointerBuffer allocateDirect(int capacity)
  {
    return new PointerBuffer(capacity);
  }
  
  protected PointerBuffer newInstance(ByteBuffer source)
  {
    return new PointerBuffer(source);
  }
  
  public PointerBuffer slice()
  {
    int pointerSize = getPointerSize();
    this.pointers.position(this.view.position() * pointerSize);
    this.pointers.limit(this.view.limit() * pointerSize);
    try
    {
      PointerBuffer localPointerBuffer = newInstance(this.pointers);
      return localPointerBuffer;
    }
    finally
    {
      this.pointers.clear();
    }
  }
  
  public PointerBuffer duplicate()
  {
    PointerBuffer buffer = newInstance(this.pointers);
    buffer.position(this.view.position());
    buffer.limit(this.view.limit());
    return buffer;
  }
  
  public PointerBuffer asReadOnlyBuffer()
  {
    PointerBuffer buffer = new PointerBufferR(this.pointers);
    buffer.position(this.view.position());
    buffer.limit(this.view.limit());
    return buffer;
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public long get()
  {
    if (is64Bit) {
      return this.view64.get();
    }
    return this.view32.get() & 0xFFFFFFFF;
  }
  
  public PointerBuffer put(long local_l)
  {
    if (is64Bit) {
      this.view64.put(local_l);
    } else {
      this.view32.put((int)local_l);
    }
    return this;
  }
  
  public PointerBuffer put(PointerWrapper pointer)
  {
    return put(pointer.getPointer());
  }
  
  public static void put(ByteBuffer target, long local_l)
  {
    if (is64Bit) {
      target.putLong(local_l);
    } else {
      target.putInt((int)local_l);
    }
  }
  
  public long get(int index)
  {
    if (is64Bit) {
      return this.view64.get(index);
    }
    return this.view32.get(index) & 0xFFFFFFFF;
  }
  
  public PointerBuffer put(int index, long local_l)
  {
    if (is64Bit) {
      this.view64.put(index, local_l);
    } else {
      this.view32.put(index, (int)local_l);
    }
    return this;
  }
  
  public PointerBuffer put(int index, PointerWrapper pointer)
  {
    return put(index, pointer.getPointer());
  }
  
  public static void put(ByteBuffer target, int index, long local_l)
  {
    if (is64Bit) {
      target.putLong(index, local_l);
    } else {
      target.putInt(index, (int)local_l);
    }
  }
  
  public PointerBuffer get(long[] dst, int offset, int length)
  {
    if (is64Bit)
    {
      this.view64.get(dst, offset, length);
    }
    else
    {
      checkBounds(offset, length, dst.length);
      if (length > this.view32.remaining()) {
        throw new BufferUnderflowException();
      }
      int end = offset + length;
      for (int local_i = offset; local_i < end; local_i++) {
        dst[local_i] = (this.view32.get() & 0xFFFFFFFF);
      }
    }
    return this;
  }
  
  public PointerBuffer get(long[] dst)
  {
    return get(dst, 0, dst.length);
  }
  
  public PointerBuffer put(PointerBuffer src)
  {
    if (is64Bit) {
      this.view64.put(src.view64);
    } else {
      this.view32.put(src.view32);
    }
    return this;
  }
  
  public PointerBuffer put(long[] src, int offset, int length)
  {
    if (is64Bit)
    {
      this.view64.put(src, offset, length);
    }
    else
    {
      checkBounds(offset, length, src.length);
      if (length > this.view32.remaining()) {
        throw new BufferOverflowException();
      }
      int end = offset + length;
      for (int local_i = offset; local_i < end; local_i++) {
        this.view32.put((int)src[local_i]);
      }
    }
    return this;
  }
  
  public final PointerBuffer put(long[] src)
  {
    return put(src, 0, src.length);
  }
  
  public PointerBuffer compact()
  {
    if (is64Bit) {
      this.view64.compact();
    } else {
      this.view32.compact();
    }
    return this;
  }
  
  public ByteOrder order()
  {
    if (is64Bit) {
      return this.view64.order();
    }
    return this.view32.order();
  }
  
  public String toString()
  {
    StringBuilder local_sb = new StringBuilder(48);
    local_sb.append(getClass().getName());
    local_sb.append("[pos=");
    local_sb.append(position());
    local_sb.append(" lim=");
    local_sb.append(limit());
    local_sb.append(" cap=");
    local_sb.append(capacity());
    local_sb.append("]");
    return local_sb.toString();
  }
  
  public int hashCode()
  {
    int local_h = 1;
    int local_p = position();
    for (int local_i = limit() - 1; local_i >= local_p; local_i--) {
      local_h = 31 * local_h + (int)get(local_i);
    }
    return local_h;
  }
  
  public boolean equals(Object local_ob)
  {
    if (!(local_ob instanceof PointerBuffer)) {
      return false;
    }
    PointerBuffer that = (PointerBuffer)local_ob;
    if (remaining() != that.remaining()) {
      return false;
    }
    int local_p = position();
    int local_i = limit() - 1;
    for (int local_j = that.limit() - 1; local_i >= local_p; local_j--)
    {
      long local_v1 = get(local_i);
      long local_v2 = that.get(local_j);
      if (local_v1 != local_v2) {
        return false;
      }
      local_i--;
    }
    return true;
  }
  
  public int compareTo(Object local_o)
  {
    PointerBuffer that = (PointerBuffer)local_o;
    int local_n = position() + Math.min(remaining(), that.remaining());
    int local_i = position();
    for (int local_j = that.position(); local_i < local_n; local_j++)
    {
      long local_v1 = get(local_i);
      long local_v2 = that.get(local_j);
      if (local_v1 != local_v2)
      {
        if (local_v1 < local_v2) {
          return -1;
        }
        return 1;
      }
      local_i++;
    }
    return remaining() - that.remaining();
  }
  
  private static void checkBounds(int off, int len, int size)
  {
    if ((off | len | off + len | size - (off + len)) < 0) {
      throw new IndexOutOfBoundsException();
    }
  }
  
  static
  {
    boolean is64 = false;
    try
    {
      Method local_m = Class.forName("org.lwjgl.Sys").getDeclaredMethod("is64Bit", (Class[])null);
      is64 = ((Boolean)local_m.invoke(null, (Object[])null)).booleanValue();
    }
    catch (Throwable local_m) {}finally
    {
      is64Bit = is64;
    }
  }
  
  private static final class PointerBufferR
    extends PointerBuffer
  {
    PointerBufferR(ByteBuffer source)
    {
      super();
    }
    
    public boolean isReadOnly()
    {
      return true;
    }
    
    protected PointerBuffer newInstance(ByteBuffer source)
    {
      return new PointerBufferR(source);
    }
    
    public PointerBuffer asReadOnlyBuffer()
    {
      return duplicate();
    }
    
    public PointerBuffer put(long local_l)
    {
      throw new ReadOnlyBufferException();
    }
    
    public PointerBuffer put(int index, long local_l)
    {
      throw new ReadOnlyBufferException();
    }
    
    public PointerBuffer put(PointerBuffer src)
    {
      throw new ReadOnlyBufferException();
    }
    
    public PointerBuffer put(long[] src, int offset, int length)
    {
      throw new ReadOnlyBufferException();
    }
    
    public PointerBuffer compact()
    {
      throw new ReadOnlyBufferException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.PointerBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */