package org.lwjgl;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public final class MemoryUtil
{
  private static final Charset ascii = Charset.forName("ISO-8859-1");
  private static final Charset utf8 = Charset.forName("UTF-8");
  private static final Charset utf16 = Charset.forName("UTF-16LE");
  private static final Accessor memUtil = util;
  
  public static long getAddress0(Buffer buffer)
  {
    return memUtil.getAddress(buffer);
  }
  
  public static long getAddress0Safe(Buffer buffer)
  {
    return buffer == null ? 0L : memUtil.getAddress(buffer);
  }
  
  public static long getAddress0(PointerBuffer buffer)
  {
    return memUtil.getAddress(buffer.getBuffer());
  }
  
  public static long getAddress0Safe(PointerBuffer buffer)
  {
    return buffer == null ? 0L : memUtil.getAddress(buffer.getBuffer());
  }
  
  public static long getAddress(ByteBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(ByteBuffer buffer, int position)
  {
    return getAddress0(buffer) + position;
  }
  
  public static long getAddress(ShortBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(ShortBuffer buffer, int position)
  {
    return getAddress0(buffer) + (position << 1);
  }
  
  public static long getAddress(CharBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(CharBuffer buffer, int position)
  {
    return getAddress0(buffer) + (position << 1);
  }
  
  public static long getAddress(IntBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(IntBuffer buffer, int position)
  {
    return getAddress0(buffer) + (position << 2);
  }
  
  public static long getAddress(FloatBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(FloatBuffer buffer, int position)
  {
    return getAddress0(buffer) + (position << 2);
  }
  
  public static long getAddress(LongBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(LongBuffer buffer, int position)
  {
    return getAddress0(buffer) + (position << 3);
  }
  
  public static long getAddress(DoubleBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(DoubleBuffer buffer, int position)
  {
    return getAddress0(buffer) + (position << 3);
  }
  
  public static long getAddress(PointerBuffer buffer)
  {
    return getAddress(buffer, buffer.position());
  }
  
  public static long getAddress(PointerBuffer buffer, int position)
  {
    return getAddress0(buffer) + position * PointerBuffer.getPointerSize();
  }
  
  public static long getAddressSafe(ByteBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(ByteBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(ShortBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(ShortBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(CharBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(CharBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(IntBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(IntBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(FloatBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(FloatBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(LongBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(LongBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(DoubleBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(DoubleBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static long getAddressSafe(PointerBuffer buffer)
  {
    return buffer == null ? 0L : getAddress(buffer);
  }
  
  public static long getAddressSafe(PointerBuffer buffer, int position)
  {
    return buffer == null ? 0L : getAddress(buffer, position);
  }
  
  public static ByteBuffer encodeASCII(CharSequence text)
  {
    return encode(text, ascii);
  }
  
  public static ByteBuffer encodeUTF8(CharSequence text)
  {
    return encode(text, utf8);
  }
  
  public static ByteBuffer encodeUTF16(CharSequence text)
  {
    return encode(text, utf16);
  }
  
  private static ByteBuffer encode(CharSequence text, Charset charset)
  {
    if (text == null) {
      return null;
    }
    return encode(CharBuffer.wrap(new CharSequenceNT(text)), charset);
  }
  
  private static ByteBuffer encode(CharBuffer local_in, Charset charset)
  {
    CharsetEncoder encoder = charset.newEncoder();
    int local_n = (int)(local_in.remaining() * encoder.averageBytesPerChar());
    ByteBuffer out = BufferUtils.createByteBuffer(local_n);
    if ((local_n == 0) && (local_in.remaining() == 0)) {
      return out;
    }
    encoder.reset();
    for (;;)
    {
      CoderResult local_cr = local_in.hasRemaining() ? encoder.encode(local_in, out, true) : CoderResult.UNDERFLOW;
      if (local_cr.isUnderflow()) {
        local_cr = encoder.flush(out);
      }
      if (local_cr.isUnderflow()) {
        break;
      }
      if (local_cr.isOverflow())
      {
        local_n = 2 * local_n + 1;
        ByteBuffer local_o = BufferUtils.createByteBuffer(local_n);
        out.flip();
        local_o.put(out);
        out = local_o;
      }
      else
      {
        try
        {
          local_cr.throwException();
        }
        catch (CharacterCodingException local_o)
        {
          throw new RuntimeException(local_o);
        }
      }
    }
    out.flip();
    return out;
  }
  
  public static String decodeASCII(ByteBuffer buffer)
  {
    return decode(buffer, ascii);
  }
  
  public static String decodeUTF8(ByteBuffer buffer)
  {
    return decode(buffer, utf8);
  }
  
  public static String decodeUTF16(ByteBuffer buffer)
  {
    return decode(buffer, utf16);
  }
  
  private static String decode(ByteBuffer buffer, Charset charset)
  {
    if (buffer == null) {
      return null;
    }
    return decodeImpl(buffer, charset);
  }
  
  private static String decodeImpl(ByteBuffer local_in, Charset charset)
  {
    CharsetDecoder decoder = charset.newDecoder();
    int local_n = (int)(local_in.remaining() * decoder.averageCharsPerByte());
    CharBuffer out = BufferUtils.createCharBuffer(local_n);
    if ((local_n == 0) && (local_in.remaining() == 0)) {
      return "";
    }
    decoder.reset();
    for (;;)
    {
      CoderResult local_cr = local_in.hasRemaining() ? decoder.decode(local_in, out, true) : CoderResult.UNDERFLOW;
      if (local_cr.isUnderflow()) {
        local_cr = decoder.flush(out);
      }
      if (local_cr.isUnderflow()) {
        break;
      }
      if (local_cr.isOverflow())
      {
        local_n = 2 * local_n + 1;
        CharBuffer local_o = BufferUtils.createCharBuffer(local_n);
        out.flip();
        local_o.put(out);
        out = local_o;
      }
      else
      {
        try
        {
          local_cr.throwException();
        }
        catch (CharacterCodingException local_o)
        {
          throw new RuntimeException(local_o);
        }
      }
    }
    out.flip();
    return out.toString();
  }
  
  private static Accessor loadAccessor(String className)
    throws Exception
  {
    return (Accessor)Class.forName(className).newInstance();
  }
  
  static Field getAddressField()
    throws NoSuchFieldException
  {
    return getDeclaredFieldRecursive(ByteBuffer.class, "address");
  }
  
  private static Field getDeclaredFieldRecursive(Class<?> root, String fieldName)
    throws NoSuchFieldException
  {
    Class<?> type = root;
    do
    {
      try
      {
        return type.getDeclaredField(fieldName);
      }
      catch (NoSuchFieldException local_e)
      {
        type = type.getSuperclass();
      }
    } while (type != null);
    throw new NoSuchFieldException(fieldName + " does not exist in " + root.getSimpleName() + " or any of its superclasses.");
  }
  
  static
  {
    Accessor util;
    try
    {
      util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorUnsafe");
    }
    catch (Exception local_e0)
    {
      try
      {
        util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorReflectFast");
      }
      catch (Exception local_e1)
      {
        try
        {
          util = new AccessorReflect();
        }
        catch (Exception local_e2)
        {
          LWJGLUtil.log("Unsupported JVM detected, this will likely result in low performance. Please inform LWJGL developers.");
          util = new AccessorJNI(null);
        }
      }
    }
    LWJGLUtil.log("MemoryUtil Accessor: " + util.getClass().getSimpleName());
  }
  
  private static class AccessorReflect
    implements MemoryUtil.Accessor
  {
    private final Field address;
    
    AccessorReflect()
    {
      try
      {
        this.address = MemoryUtil.getAddressField();
      }
      catch (NoSuchFieldException local_e)
      {
        throw new UnsupportedOperationException(local_e);
      }
      this.address.setAccessible(true);
    }
    
    public long getAddress(Buffer buffer)
    {
      try
      {
        return this.address.getLong(buffer);
      }
      catch (IllegalAccessException local_e) {}
      return 0L;
    }
  }
  
  private static class AccessorJNI
    implements MemoryUtil.Accessor
  {
    public long getAddress(Buffer buffer)
    {
      return BufferUtils.getBufferAddress(buffer);
    }
  }
  
  static abstract interface Accessor
  {
    public abstract long getAddress(Buffer paramBuffer);
  }
  
  private static class CharSequenceNT
    implements CharSequence
  {
    final CharSequence source;
    
    CharSequenceNT(CharSequence source)
    {
      this.source = source;
    }
    
    public int length()
    {
      return this.source.length() + 1;
    }
    
    public char charAt(int index)
    {
      return index == this.source.length() ? '\000' : this.source.charAt(index);
    }
    
    public CharSequence subSequence(int start, int end)
    {
      return new CharSequenceNT(this.source.subSequence(start, Math.min(end, this.source.length())));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.MemoryUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */