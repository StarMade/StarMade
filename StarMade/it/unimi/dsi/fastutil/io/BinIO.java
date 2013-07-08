package it.unimi.dsi.fastutil.io;

import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteArrays;
import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharArrays;
import it.unimi.dsi.fastutil.chars.CharBigArrays;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleArrays;
import it.unimi.dsi.fastutil.doubles.DoubleBigArrays;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatArrays;
import it.unimi.dsi.fastutil.floats.FloatBigArrays;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.ints.IntBigArrays;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongArrays;
import it.unimi.dsi.fastutil.longs.LongBigArrays;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortArrays;
import it.unimi.dsi.fastutil.shorts.ShortBigArrays;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;

public class BinIO
{
  private static final int MAX_IO_LENGTH = 1048576;
  
  public static void storeObject(Object local_o, File file)
    throws IOException
  {
    ObjectOutputStream oos = new ObjectOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    oos.writeObject(local_o);
    oos.close();
  }
  
  public static void storeObject(Object local_o, CharSequence filename)
    throws IOException
  {
    storeObject(local_o, new File(filename.toString()));
  }
  
  public static Object loadObject(File file)
    throws IOException, ClassNotFoundException
  {
    ObjectInputStream ois = new ObjectInputStream(new FastBufferedInputStream(new FileInputStream(file)));
    Object result = ois.readObject();
    ois.close();
    return result;
  }
  
  public static Object loadObject(CharSequence filename)
    throws IOException, ClassNotFoundException
  {
    return loadObject(new File(filename.toString()));
  }
  
  public static void storeObject(Object local_o, OutputStream local_s)
    throws IOException
  {
    ObjectOutputStream oos = new ObjectOutputStream(new FastBufferedOutputStream(local_s));
    oos.writeObject(local_o);
    oos.flush();
  }
  
  public static Object loadObject(InputStream local_s)
    throws IOException, ClassNotFoundException
  {
    ObjectInputStream ois = new ObjectInputStream(new FastBufferedInputStream(local_s));
    Object result = ois.readObject();
    return result;
  }
  
  public static int loadBooleans(DataInput dataInput, boolean[] array, int offset, int length)
    throws IOException
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readBoolean();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadBooleans(DataInput dataInput, boolean[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readBoolean();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadBooleans(File file, boolean[] array, int offset, int length)
    throws IOException
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readBoolean();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadBooleans(CharSequence filename, boolean[] array, int offset, int length)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadBooleans(File file, boolean[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readBoolean();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadBooleans(CharSequence filename, boolean[] array)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()), array);
  }
  
  public static boolean[] loadBooleans(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size();
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    boolean[] array = new boolean[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readBoolean();
    }
    dis.close();
    return array;
  }
  
  public static boolean[] loadBooleans(CharSequence filename)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()));
  }
  
  public static void storeBooleans(boolean[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeBoolean(array[(offset + local_i)]);
    }
  }
  
  public static void storeBooleans(boolean[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeBoolean(array[local_i]);
    }
  }
  
  public static void storeBooleans(boolean[] array, int offset, int length, File file)
    throws IOException
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeBoolean(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeBooleans(boolean[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBooleans(boolean[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeBoolean(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeBooleans(boolean[] array, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, new File(filename.toString()));
  }
  
  public static long loadBooleans(DataInput dataInput, boolean[][] array, long offset, long length)
    throws IOException
  {
    BooleanBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        boolean[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readBoolean();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadBooleans(DataInput dataInput, boolean[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        boolean[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readBoolean();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadBooleans(File file, boolean[][] array, long offset, long length)
    throws IOException
  {
    BooleanBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        boolean[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readBoolean();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadBooleans(CharSequence filename, boolean[][] array, long offset, long length)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadBooleans(File file, boolean[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        boolean[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readBoolean();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadBooleans(CharSequence filename, boolean[][] array)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()), array);
  }
  
  public static boolean[][] loadBooleansBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size();
    boolean[][] array = BooleanBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      boolean[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readBoolean();
      }
    }
    dis.close();
    return array;
  }
  
  public static boolean[][] loadBooleansBig(CharSequence filename)
    throws IOException
  {
    return loadBooleansBig(new File(filename.toString()));
  }
  
  public static void storeBooleans(boolean[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    BooleanBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      boolean[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeBoolean(local_t[local_d]);
      }
    }
  }
  
  public static void storeBooleans(boolean[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      boolean[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeBoolean(local_t[local_d]);
      }
    }
  }
  
  public static void storeBooleans(boolean[][] array, long offset, long length, File file)
    throws IOException
  {
    BooleanBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      boolean[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeBoolean(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeBooleans(boolean[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBooleans(boolean[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      boolean[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeBoolean(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeBooleans(boolean[][] array, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, new File(filename.toString()));
  }
  
  public static void storeBooleans(BooleanIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeBoolean(local_i.nextBoolean());
    }
  }
  
  public static void storeBooleans(BooleanIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeBoolean(local_i.nextBoolean());
    }
    dos.close();
  }
  
  public static void storeBooleans(BooleanIterator local_i, CharSequence filename)
    throws IOException
  {
    storeBooleans(local_i, new File(filename.toString()));
  }
  
  public static BooleanIterator asBooleanIterator(DataInput dataInput)
  {
    return new BooleanDataInputWrapper(dataInput);
  }
  
  public static BooleanIterator asBooleanIterator(File file)
    throws IOException
  {
    return new BooleanDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static BooleanIterator asBooleanIterator(CharSequence filename)
    throws IOException
  {
    return asBooleanIterator(new File(filename.toString()));
  }
  
  private static int read(InputStream local_is, byte[] local_a, int offset, int length)
    throws IOException
  {
    if (length == 0) {
      return 0;
    }
    int read = 0;
    do
    {
      int result = local_is.read(local_a, offset + read, Math.min(length - read, 1048576));
      if (result < 0) {
        return read;
      }
      read += result;
    } while (read < length);
    return read;
  }
  
  private static void write(OutputStream outputStream, byte[] local_a, int offset, int length)
    throws IOException
  {
    int written = 0;
    while (written < length)
    {
      outputStream.write(local_a, offset + written, Math.min(length - written, 1048576));
      written += Math.min(length - written, 1048576);
    }
  }
  
  private static void write(DataOutput dataOutput, byte[] local_a, int offset, int length)
    throws IOException
  {
    int written = 0;
    while (written < length)
    {
      dataOutput.write(local_a, offset + written, Math.min(length - written, 1048576));
      written += Math.min(length - written, 1048576);
    }
  }
  
  public static int loadBytes(InputStream inputStream, byte[] array, int offset, int length)
    throws IOException
  {
    return read(inputStream, array, offset, length);
  }
  
  public static int loadBytes(InputStream inputStream, byte[] array)
    throws IOException
  {
    return read(inputStream, array, 0, array.length);
  }
  
  public static void storeBytes(byte[] array, int offset, int length, OutputStream outputStream)
    throws IOException
  {
    write(outputStream, array, offset, length);
  }
  
  public static void storeBytes(byte[] array, OutputStream outputStream)
    throws IOException
  {
    write(outputStream, array, 0, array.length);
  }
  
  private static long read(InputStream local_is, byte[][] local_a, long offset, long length)
    throws IOException
  {
    if (length == 0L) {
      return 0L;
    }
    long read = 0L;
    int segment = BigArrays.segment(offset);
    int displacement = BigArrays.displacement(offset);
    do
    {
      int result = local_is.read(local_a[segment], displacement, (int)Math.min(local_a[segment].length - displacement, Math.min(length - read, 1048576L)));
      if (result < 0) {
        return read;
      }
      read += result;
      displacement += result;
      if (displacement == local_a[segment].length)
      {
        segment++;
        displacement = 0;
      }
    } while (read < length);
    return read;
  }
  
  private static void write(OutputStream outputStream, byte[][] local_a, long offset, long length)
    throws IOException
  {
    if (length == 0L) {
      return;
    }
    long written = 0L;
    int segment = BigArrays.segment(offset);
    int displacement = BigArrays.displacement(offset);
    do
    {
      int toWrite = (int)Math.min(local_a[segment].length - displacement, Math.min(length - written, 1048576L));
      outputStream.write(local_a[segment], displacement, toWrite);
      written += toWrite;
      displacement += toWrite;
      if (displacement == local_a[segment].length)
      {
        segment++;
        displacement = 0;
      }
    } while (written < length);
  }
  
  private static void write(DataOutput dataOutput, byte[][] local_a, long offset, long length)
    throws IOException
  {
    if (length == 0L) {
      return;
    }
    long written = 0L;
    int segment = BigArrays.segment(offset);
    int displacement = BigArrays.displacement(offset);
    do
    {
      int toWrite = (int)Math.min(local_a[segment].length - displacement, Math.min(length - written, 1048576L));
      dataOutput.write(local_a[segment], displacement, toWrite);
      written += toWrite;
      displacement += toWrite;
      if (displacement == local_a[segment].length)
      {
        segment++;
        displacement = 0;
      }
    } while (written < length);
  }
  
  public static long loadBytes(InputStream inputStream, byte[][] array, long offset, long length)
    throws IOException
  {
    return read(inputStream, array, offset, length);
  }
  
  public static long loadBytes(InputStream inputStream, byte[][] array)
    throws IOException
  {
    return read(inputStream, array, 0L, ByteBigArrays.length(array));
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, OutputStream outputStream)
    throws IOException
  {
    write(outputStream, array, offset, length);
  }
  
  public static void storeBytes(byte[][] array, OutputStream outputStream)
    throws IOException
  {
    write(outputStream, array, 0L, ByteBigArrays.length(array));
  }
  
  public static int loadBytes(DataInput dataInput, byte[] array, int offset, int length)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readByte();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadBytes(DataInput dataInput, byte[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readByte();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadBytes(File file, byte[] array, int offset, int length)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    int result = read(fis, array, offset, length);
    fis.close();
    return result;
  }
  
  public static int loadBytes(CharSequence filename, byte[] array, int offset, int length)
    throws IOException
  {
    return loadBytes(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadBytes(File file, byte[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    int result = read(fis, array, 0, array.length);
    fis.close();
    return result;
  }
  
  public static int loadBytes(CharSequence filename, byte[] array)
    throws IOException
  {
    return loadBytes(new File(filename.toString()), array);
  }
  
  public static byte[] loadBytes(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 1L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    byte[] array = new byte[(int)length];
    if (read(fis, array, 0, (int)length) < length) {
      throw new EOFException();
    }
    fis.close();
    return array;
  }
  
  public static byte[] loadBytes(CharSequence filename)
    throws IOException
  {
    return loadBytes(new File(filename.toString()));
  }
  
  public static void storeBytes(byte[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    write(dataOutput, array, offset, length);
  }
  
  public static void storeBytes(byte[] array, DataOutput dataOutput)
    throws IOException
  {
    write(dataOutput, array, 0, array.length);
  }
  
  public static void storeBytes(byte[] array, int offset, int length, File file)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    OutputStream local_os = new FastBufferedOutputStream(new FileOutputStream(file));
    write(local_os, array, offset, length);
    local_os.close();
  }
  
  public static void storeBytes(byte[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeBytes(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBytes(byte[] array, File file)
    throws IOException
  {
    OutputStream local_os = new FastBufferedOutputStream(new FileOutputStream(file));
    write(local_os, array, 0, array.length);
    local_os.close();
  }
  
  public static void storeBytes(byte[] array, CharSequence filename)
    throws IOException
  {
    storeBytes(array, new File(filename.toString()));
  }
  
  public static long loadBytes(DataInput dataInput, byte[][] array, long offset, long length)
    throws IOException
  {
    ByteBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        byte[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readByte();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadBytes(DataInput dataInput, byte[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        byte[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readByte();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadBytes(File file, byte[][] array, long offset, long length)
    throws IOException
  {
    ByteBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    long result = read(fis, array, offset, length);
    fis.close();
    return result;
  }
  
  public static long loadBytes(CharSequence filename, byte[][] array, long offset, long length)
    throws IOException
  {
    return loadBytes(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadBytes(File file, byte[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long result = read(fis, array, 0L, ByteBigArrays.length(array));
    fis.close();
    return result;
  }
  
  public static long loadBytes(CharSequence filename, byte[][] array)
    throws IOException
  {
    return loadBytes(new File(filename.toString()), array);
  }
  
  public static byte[][] loadBytesBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 1L;
    byte[][] array = ByteBigArrays.newBigArray(length);
    if (read(fis, array, 0L, length) < length) {
      throw new EOFException();
    }
    fis.close();
    return array;
  }
  
  public static byte[][] loadBytesBig(CharSequence filename)
    throws IOException
  {
    return loadBytesBig(new File(filename.toString()));
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    ByteBigArrays.ensureOffsetLength(array, offset, length);
    write(dataOutput, array, offset, length);
  }
  
  public static void storeBytes(byte[][] array, DataOutput dataOutput)
    throws IOException
  {
    write(dataOutput, array, 0L, ByteBigArrays.length(array));
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, File file)
    throws IOException
  {
    ByteBigArrays.ensureOffsetLength(array, offset, length);
    OutputStream local_os = new FastBufferedOutputStream(new FileOutputStream(file));
    write(local_os, array, offset, length);
    local_os.close();
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeBytes(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBytes(byte[][] array, File file)
    throws IOException
  {
    OutputStream local_os = new FastBufferedOutputStream(new FileOutputStream(file));
    write(local_os, array, 0L, ByteBigArrays.length(array));
    local_os.close();
  }
  
  public static void storeBytes(byte[][] array, CharSequence filename)
    throws IOException
  {
    storeBytes(array, new File(filename.toString()));
  }
  
  public static void storeBytes(ByteIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeByte(local_i.nextByte());
    }
  }
  
  public static void storeBytes(ByteIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeByte(local_i.nextByte());
    }
    dos.close();
  }
  
  public static void storeBytes(ByteIterator local_i, CharSequence filename)
    throws IOException
  {
    storeBytes(local_i, new File(filename.toString()));
  }
  
  public static ByteIterator asByteIterator(DataInput dataInput)
  {
    return new ByteDataInputWrapper(dataInput);
  }
  
  public static ByteIterator asByteIterator(File file)
    throws IOException
  {
    return new ByteDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static ByteIterator asByteIterator(CharSequence filename)
    throws IOException
  {
    return asByteIterator(new File(filename.toString()));
  }
  
  public static int loadShorts(DataInput dataInput, short[] array, int offset, int length)
    throws IOException
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readShort();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadShorts(DataInput dataInput, short[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readShort();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadShorts(File file, short[] array, int offset, int length)
    throws IOException
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readShort();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadShorts(CharSequence filename, short[] array, int offset, int length)
    throws IOException
  {
    return loadShorts(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadShorts(File file, short[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readShort();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadShorts(CharSequence filename, short[] array)
    throws IOException
  {
    return loadShorts(new File(filename.toString()), array);
  }
  
  public static short[] loadShorts(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 2L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    short[] array = new short[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readShort();
    }
    dis.close();
    return array;
  }
  
  public static short[] loadShorts(CharSequence filename)
    throws IOException
  {
    return loadShorts(new File(filename.toString()));
  }
  
  public static void storeShorts(short[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeShort(array[(offset + local_i)]);
    }
  }
  
  public static void storeShorts(short[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeShort(array[local_i]);
    }
  }
  
  public static void storeShorts(short[] array, int offset, int length, File file)
    throws IOException
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeShort(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeShorts(short[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeShorts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeShorts(short[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeShort(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeShorts(short[] array, CharSequence filename)
    throws IOException
  {
    storeShorts(array, new File(filename.toString()));
  }
  
  public static long loadShorts(DataInput dataInput, short[][] array, long offset, long length)
    throws IOException
  {
    ShortBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        short[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readShort();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadShorts(DataInput dataInput, short[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        short[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readShort();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadShorts(File file, short[][] array, long offset, long length)
    throws IOException
  {
    ShortBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        short[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readShort();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadShorts(CharSequence filename, short[][] array, long offset, long length)
    throws IOException
  {
    return loadShorts(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadShorts(File file, short[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        short[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readShort();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadShorts(CharSequence filename, short[][] array)
    throws IOException
  {
    return loadShorts(new File(filename.toString()), array);
  }
  
  public static short[][] loadShortsBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 2L;
    short[][] array = ShortBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      short[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readShort();
      }
    }
    dis.close();
    return array;
  }
  
  public static short[][] loadShortsBig(CharSequence filename)
    throws IOException
  {
    return loadShortsBig(new File(filename.toString()));
  }
  
  public static void storeShorts(short[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    ShortBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      short[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeShort(local_t[local_d]);
      }
    }
  }
  
  public static void storeShorts(short[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      short[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeShort(local_t[local_d]);
      }
    }
  }
  
  public static void storeShorts(short[][] array, long offset, long length, File file)
    throws IOException
  {
    ShortBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      short[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeShort(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeShorts(short[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeShorts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeShorts(short[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      short[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeShort(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeShorts(short[][] array, CharSequence filename)
    throws IOException
  {
    storeShorts(array, new File(filename.toString()));
  }
  
  public static void storeShorts(ShortIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeShort(local_i.nextShort());
    }
  }
  
  public static void storeShorts(ShortIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeShort(local_i.nextShort());
    }
    dos.close();
  }
  
  public static void storeShorts(ShortIterator local_i, CharSequence filename)
    throws IOException
  {
    storeShorts(local_i, new File(filename.toString()));
  }
  
  public static ShortIterator asShortIterator(DataInput dataInput)
  {
    return new ShortDataInputWrapper(dataInput);
  }
  
  public static ShortIterator asShortIterator(File file)
    throws IOException
  {
    return new ShortDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static ShortIterator asShortIterator(CharSequence filename)
    throws IOException
  {
    return asShortIterator(new File(filename.toString()));
  }
  
  public static int loadChars(DataInput dataInput, char[] array, int offset, int length)
    throws IOException
  {
    CharArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readChar();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadChars(DataInput dataInput, char[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readChar();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadChars(File file, char[] array, int offset, int length)
    throws IOException
  {
    CharArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readChar();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadChars(CharSequence filename, char[] array, int offset, int length)
    throws IOException
  {
    return loadChars(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadChars(File file, char[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readChar();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadChars(CharSequence filename, char[] array)
    throws IOException
  {
    return loadChars(new File(filename.toString()), array);
  }
  
  public static char[] loadChars(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 2L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    char[] array = new char[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readChar();
    }
    dis.close();
    return array;
  }
  
  public static char[] loadChars(CharSequence filename)
    throws IOException
  {
    return loadChars(new File(filename.toString()));
  }
  
  public static void storeChars(char[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    CharArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeChar(array[(offset + local_i)]);
    }
  }
  
  public static void storeChars(char[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeChar(array[local_i]);
    }
  }
  
  public static void storeChars(char[] array, int offset, int length, File file)
    throws IOException
  {
    CharArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeChar(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeChars(char[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeChars(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeChars(char[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeChar(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeChars(char[] array, CharSequence filename)
    throws IOException
  {
    storeChars(array, new File(filename.toString()));
  }
  
  public static long loadChars(DataInput dataInput, char[][] array, long offset, long length)
    throws IOException
  {
    CharBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        char[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readChar();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadChars(DataInput dataInput, char[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        char[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readChar();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadChars(File file, char[][] array, long offset, long length)
    throws IOException
  {
    CharBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        char[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readChar();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadChars(CharSequence filename, char[][] array, long offset, long length)
    throws IOException
  {
    return loadChars(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadChars(File file, char[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        char[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readChar();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadChars(CharSequence filename, char[][] array)
    throws IOException
  {
    return loadChars(new File(filename.toString()), array);
  }
  
  public static char[][] loadCharsBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 2L;
    char[][] array = CharBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      char[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readChar();
      }
    }
    dis.close();
    return array;
  }
  
  public static char[][] loadCharsBig(CharSequence filename)
    throws IOException
  {
    return loadCharsBig(new File(filename.toString()));
  }
  
  public static void storeChars(char[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    CharBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      char[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeChar(local_t[local_d]);
      }
    }
  }
  
  public static void storeChars(char[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      char[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeChar(local_t[local_d]);
      }
    }
  }
  
  public static void storeChars(char[][] array, long offset, long length, File file)
    throws IOException
  {
    CharBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      char[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeChar(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeChars(char[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeChars(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeChars(char[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      char[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeChar(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeChars(char[][] array, CharSequence filename)
    throws IOException
  {
    storeChars(array, new File(filename.toString()));
  }
  
  public static void storeChars(CharIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeChar(local_i.nextChar());
    }
  }
  
  public static void storeChars(CharIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeChar(local_i.nextChar());
    }
    dos.close();
  }
  
  public static void storeChars(CharIterator local_i, CharSequence filename)
    throws IOException
  {
    storeChars(local_i, new File(filename.toString()));
  }
  
  public static CharIterator asCharIterator(DataInput dataInput)
  {
    return new CharDataInputWrapper(dataInput);
  }
  
  public static CharIterator asCharIterator(File file)
    throws IOException
  {
    return new CharDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static CharIterator asCharIterator(CharSequence filename)
    throws IOException
  {
    return asCharIterator(new File(filename.toString()));
  }
  
  public static int loadInts(DataInput dataInput, int[] array, int offset, int length)
    throws IOException
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readInt();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadInts(DataInput dataInput, int[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readInt();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadInts(File file, int[] array, int offset, int length)
    throws IOException
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readInt();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadInts(CharSequence filename, int[] array, int offset, int length)
    throws IOException
  {
    return loadInts(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadInts(File file, int[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readInt();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadInts(CharSequence filename, int[] array)
    throws IOException
  {
    return loadInts(new File(filename.toString()), array);
  }
  
  public static int[] loadInts(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 4L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    int[] array = new int[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readInt();
    }
    dis.close();
    return array;
  }
  
  public static int[] loadInts(CharSequence filename)
    throws IOException
  {
    return loadInts(new File(filename.toString()));
  }
  
  public static void storeInts(int[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeInt(array[(offset + local_i)]);
    }
  }
  
  public static void storeInts(int[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeInt(array[local_i]);
    }
  }
  
  public static void storeInts(int[] array, int offset, int length, File file)
    throws IOException
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeInt(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeInts(int[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeInts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeInts(int[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeInt(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeInts(int[] array, CharSequence filename)
    throws IOException
  {
    storeInts(array, new File(filename.toString()));
  }
  
  public static long loadInts(DataInput dataInput, int[][] array, long offset, long length)
    throws IOException
  {
    IntBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        int[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readInt();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadInts(DataInput dataInput, int[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        int[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readInt();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadInts(File file, int[][] array, long offset, long length)
    throws IOException
  {
    IntBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        int[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readInt();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadInts(CharSequence filename, int[][] array, long offset, long length)
    throws IOException
  {
    return loadInts(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadInts(File file, int[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        int[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readInt();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadInts(CharSequence filename, int[][] array)
    throws IOException
  {
    return loadInts(new File(filename.toString()), array);
  }
  
  public static int[][] loadIntsBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 4L;
    int[][] array = IntBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      int[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readInt();
      }
    }
    dis.close();
    return array;
  }
  
  public static int[][] loadIntsBig(CharSequence filename)
    throws IOException
  {
    return loadIntsBig(new File(filename.toString()));
  }
  
  public static void storeInts(int[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    IntBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      int[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeInt(local_t[local_d]);
      }
    }
  }
  
  public static void storeInts(int[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      int[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeInt(local_t[local_d]);
      }
    }
  }
  
  public static void storeInts(int[][] array, long offset, long length, File file)
    throws IOException
  {
    IntBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      int[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeInt(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeInts(int[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeInts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeInts(int[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      int[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeInt(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeInts(int[][] array, CharSequence filename)
    throws IOException
  {
    storeInts(array, new File(filename.toString()));
  }
  
  public static void storeInts(IntIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeInt(local_i.nextInt());
    }
  }
  
  public static void storeInts(IntIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeInt(local_i.nextInt());
    }
    dos.close();
  }
  
  public static void storeInts(IntIterator local_i, CharSequence filename)
    throws IOException
  {
    storeInts(local_i, new File(filename.toString()));
  }
  
  public static IntIterator asIntIterator(DataInput dataInput)
  {
    return new IntDataInputWrapper(dataInput);
  }
  
  public static IntIterator asIntIterator(File file)
    throws IOException
  {
    return new IntDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static IntIterator asIntIterator(CharSequence filename)
    throws IOException
  {
    return asIntIterator(new File(filename.toString()));
  }
  
  public static int loadLongs(DataInput dataInput, long[] array, int offset, int length)
    throws IOException
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readLong();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadLongs(DataInput dataInput, long[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readLong();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadLongs(File file, long[] array, int offset, int length)
    throws IOException
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readLong();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadLongs(CharSequence filename, long[] array, int offset, int length)
    throws IOException
  {
    return loadLongs(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadLongs(File file, long[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readLong();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadLongs(CharSequence filename, long[] array)
    throws IOException
  {
    return loadLongs(new File(filename.toString()), array);
  }
  
  public static long[] loadLongs(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 8L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    long[] array = new long[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readLong();
    }
    dis.close();
    return array;
  }
  
  public static long[] loadLongs(CharSequence filename)
    throws IOException
  {
    return loadLongs(new File(filename.toString()));
  }
  
  public static void storeLongs(long[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeLong(array[(offset + local_i)]);
    }
  }
  
  public static void storeLongs(long[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeLong(array[local_i]);
    }
  }
  
  public static void storeLongs(long[] array, int offset, int length, File file)
    throws IOException
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeLong(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeLongs(long[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeLongs(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeLongs(long[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeLong(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeLongs(long[] array, CharSequence filename)
    throws IOException
  {
    storeLongs(array, new File(filename.toString()));
  }
  
  public static long loadLongs(DataInput dataInput, long[][] array, long offset, long length)
    throws IOException
  {
    LongBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        long[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readLong();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadLongs(DataInput dataInput, long[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        long[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readLong();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadLongs(File file, long[][] array, long offset, long length)
    throws IOException
  {
    LongBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        long[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readLong();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadLongs(CharSequence filename, long[][] array, long offset, long length)
    throws IOException
  {
    return loadLongs(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadLongs(File file, long[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        long[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readLong();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadLongs(CharSequence filename, long[][] array)
    throws IOException
  {
    return loadLongs(new File(filename.toString()), array);
  }
  
  public static long[][] loadLongsBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 8L;
    long[][] array = LongBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      long[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readLong();
      }
    }
    dis.close();
    return array;
  }
  
  public static long[][] loadLongsBig(CharSequence filename)
    throws IOException
  {
    return loadLongsBig(new File(filename.toString()));
  }
  
  public static void storeLongs(long[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    LongBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      long[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeLong(local_t[local_d]);
      }
    }
  }
  
  public static void storeLongs(long[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      long[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeLong(local_t[local_d]);
      }
    }
  }
  
  public static void storeLongs(long[][] array, long offset, long length, File file)
    throws IOException
  {
    LongBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      long[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeLong(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeLongs(long[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeLongs(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeLongs(long[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      long[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeLong(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeLongs(long[][] array, CharSequence filename)
    throws IOException
  {
    storeLongs(array, new File(filename.toString()));
  }
  
  public static void storeLongs(LongIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeLong(local_i.nextLong());
    }
  }
  
  public static void storeLongs(LongIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeLong(local_i.nextLong());
    }
    dos.close();
  }
  
  public static void storeLongs(LongIterator local_i, CharSequence filename)
    throws IOException
  {
    storeLongs(local_i, new File(filename.toString()));
  }
  
  public static LongIterator asLongIterator(DataInput dataInput)
  {
    return new LongDataInputWrapper(dataInput);
  }
  
  public static LongIterator asLongIterator(File file)
    throws IOException
  {
    return new LongDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static LongIterator asLongIterator(CharSequence filename)
    throws IOException
  {
    return asLongIterator(new File(filename.toString()));
  }
  
  public static int loadFloats(DataInput dataInput, float[] array, int offset, int length)
    throws IOException
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readFloat();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadFloats(DataInput dataInput, float[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readFloat();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadFloats(File file, float[] array, int offset, int length)
    throws IOException
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readFloat();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadFloats(CharSequence filename, float[] array, int offset, int length)
    throws IOException
  {
    return loadFloats(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadFloats(File file, float[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readFloat();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadFloats(CharSequence filename, float[] array)
    throws IOException
  {
    return loadFloats(new File(filename.toString()), array);
  }
  
  public static float[] loadFloats(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 4L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    float[] array = new float[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readFloat();
    }
    dis.close();
    return array;
  }
  
  public static float[] loadFloats(CharSequence filename)
    throws IOException
  {
    return loadFloats(new File(filename.toString()));
  }
  
  public static void storeFloats(float[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeFloat(array[(offset + local_i)]);
    }
  }
  
  public static void storeFloats(float[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeFloat(array[local_i]);
    }
  }
  
  public static void storeFloats(float[] array, int offset, int length, File file)
    throws IOException
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeFloat(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeFloats(float[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeFloats(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeFloats(float[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeFloat(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeFloats(float[] array, CharSequence filename)
    throws IOException
  {
    storeFloats(array, new File(filename.toString()));
  }
  
  public static long loadFloats(DataInput dataInput, float[][] array, long offset, long length)
    throws IOException
  {
    FloatBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        float[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readFloat();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadFloats(DataInput dataInput, float[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        float[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readFloat();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadFloats(File file, float[][] array, long offset, long length)
    throws IOException
  {
    FloatBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        float[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readFloat();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadFloats(CharSequence filename, float[][] array, long offset, long length)
    throws IOException
  {
    return loadFloats(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadFloats(File file, float[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        float[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readFloat();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadFloats(CharSequence filename, float[][] array)
    throws IOException
  {
    return loadFloats(new File(filename.toString()), array);
  }
  
  public static float[][] loadFloatsBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 4L;
    float[][] array = FloatBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      float[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readFloat();
      }
    }
    dis.close();
    return array;
  }
  
  public static float[][] loadFloatsBig(CharSequence filename)
    throws IOException
  {
    return loadFloatsBig(new File(filename.toString()));
  }
  
  public static void storeFloats(float[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    FloatBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      float[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeFloat(local_t[local_d]);
      }
    }
  }
  
  public static void storeFloats(float[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      float[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeFloat(local_t[local_d]);
      }
    }
  }
  
  public static void storeFloats(float[][] array, long offset, long length, File file)
    throws IOException
  {
    FloatBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      float[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeFloat(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeFloats(float[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeFloats(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeFloats(float[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      float[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeFloat(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeFloats(float[][] array, CharSequence filename)
    throws IOException
  {
    storeFloats(array, new File(filename.toString()));
  }
  
  public static void storeFloats(FloatIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeFloat(local_i.nextFloat());
    }
  }
  
  public static void storeFloats(FloatIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeFloat(local_i.nextFloat());
    }
    dos.close();
  }
  
  public static void storeFloats(FloatIterator local_i, CharSequence filename)
    throws IOException
  {
    storeFloats(local_i, new File(filename.toString()));
  }
  
  public static FloatIterator asFloatIterator(DataInput dataInput)
  {
    return new FloatDataInputWrapper(dataInput);
  }
  
  public static FloatIterator asFloatIterator(File file)
    throws IOException
  {
    return new FloatDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static FloatIterator asFloatIterator(CharSequence filename)
    throws IOException
  {
    return asFloatIterator(new File(filename.toString()));
  }
  
  public static int loadDoubles(DataInput dataInput, double[] array, int offset, int length)
    throws IOException
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dataInput.readDouble();
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadDoubles(DataInput dataInput, double[] array)
    throws IOException
  {
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dataInput.readDouble();
      }
    }
    catch (EOFException length) {}
    return local_i;
  }
  
  public static int loadDoubles(File file, double[] array, int offset, int length)
    throws IOException
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      for (local_i = 0; local_i < length; local_i++) {
        array[(local_i + offset)] = dis.readDouble();
      }
    }
    catch (EOFException itsOk) {}
    dis.close();
    return local_i;
  }
  
  public static int loadDoubles(CharSequence filename, double[] array, int offset, int length)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadDoubles(File file, double[] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    int local_i = 0;
    try
    {
      int length = array.length;
      for (local_i = 0; local_i < length; local_i++) {
        array[local_i] = dis.readDouble();
      }
    }
    catch (EOFException length) {}
    dis.close();
    return local_i;
  }
  
  public static int loadDoubles(CharSequence filename, double[] array)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()), array);
  }
  
  public static double[] loadDoubles(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 8L;
    if (length > 2147483647L) {
      throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
    }
    double[] array = new double[(int)length];
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < length; local_i++) {
      array[local_i] = dis.readDouble();
    }
    dis.close();
    return array;
  }
  
  public static double[] loadDoubles(CharSequence filename)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()));
  }
  
  public static void storeDoubles(double[] array, int offset, int length, DataOutput dataOutput)
    throws IOException
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeDouble(array[(offset + local_i)]);
    }
  }
  
  public static void storeDoubles(double[] array, DataOutput dataOutput)
    throws IOException
  {
    int length = array.length;
    for (int local_i = 0; local_i < length; local_i++) {
      dataOutput.writeDouble(array[local_i]);
    }
  }
  
  public static void storeDoubles(double[] array, int offset, int length, File file)
    throws IOException
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeDouble(array[(offset + local_i)]);
    }
    dos.close();
  }
  
  public static void storeDoubles(double[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeDoubles(double[] array, File file)
    throws IOException
  {
    int length = array.length;
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < length; local_i++) {
      dos.writeDouble(array[local_i]);
    }
    dos.close();
  }
  
  public static void storeDoubles(double[] array, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, new File(filename.toString()));
  }
  
  public static long loadDoubles(DataInput dataInput, double[][] array, long offset, long length)
    throws IOException
  {
    DoubleBigArrays.ensureOffsetLength(array, offset, length);
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        double[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readDouble();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadDoubles(DataInput dataInput, double[][] array)
    throws IOException
  {
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        double[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dataInput.readDouble();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadDoubles(File file, double[][] array, long offset, long length)
    throws IOException
  {
    DoubleBigArrays.ensureOffsetLength(array, offset, length);
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
      {
        double[] local_t = array[local_i];
        int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
        for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readDouble();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadDoubles(CharSequence filename, double[][] array, long offset, long length)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadDoubles(File file, double[][] array)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    long local_c = 0L;
    try
    {
      for (int local_i = 0; local_i < array.length; local_i++)
      {
        double[] local_t = array[local_i];
        int local_l = local_t.length;
        for (int local_d = 0; local_d < local_l; local_d++)
        {
          local_t[local_d] = dis.readDouble();
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    dis.close();
    return local_c;
  }
  
  public static long loadDoubles(CharSequence filename, double[][] array)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()), array);
  }
  
  public static double[][] loadDoublesBig(File file)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
    long length = fis.getChannel().size() / 8L;
    double[][] array = DoubleBigArrays.newBigArray(length);
    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      double[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        local_t[local_d] = dis.readDouble();
      }
    }
    dis.close();
    return array;
  }
  
  public static double[][] loadDoublesBig(CharSequence filename)
    throws IOException
  {
    return loadDoublesBig(new File(filename.toString()));
  }
  
  public static void storeDoubles(double[][] array, long offset, long length, DataOutput dataOutput)
    throws IOException
  {
    DoubleBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      double[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dataOutput.writeDouble(local_t[local_d]);
      }
    }
  }
  
  public static void storeDoubles(double[][] array, DataOutput dataOutput)
    throws IOException
  {
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      double[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dataOutput.writeDouble(local_t[local_d]);
      }
    }
  }
  
  public static void storeDoubles(double[][] array, long offset, long length, File file)
    throws IOException
  {
    DoubleBigArrays.ensureOffsetLength(array, offset, length);
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      double[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        dos.writeDouble(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeDoubles(double[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeDoubles(double[][] array, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    for (int local_i = 0; local_i < array.length; local_i++)
    {
      double[] local_t = array[local_i];
      int local_l = local_t.length;
      for (int local_d = 0; local_d < local_l; local_d++) {
        dos.writeDouble(local_t[local_d]);
      }
    }
    dos.close();
  }
  
  public static void storeDoubles(double[][] array, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, new File(filename.toString()));
  }
  
  public static void storeDoubles(DoubleIterator local_i, DataOutput dataOutput)
    throws IOException
  {
    while (local_i.hasNext()) {
      dataOutput.writeDouble(local_i.nextDouble());
    }
  }
  
  public static void storeDoubles(DoubleIterator local_i, File file)
    throws IOException
  {
    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    while (local_i.hasNext()) {
      dos.writeDouble(local_i.nextDouble());
    }
    dos.close();
  }
  
  public static void storeDoubles(DoubleIterator local_i, CharSequence filename)
    throws IOException
  {
    storeDoubles(local_i, new File(filename.toString()));
  }
  
  public static DoubleIterator asDoubleIterator(DataInput dataInput)
  {
    return new DoubleDataInputWrapper(dataInput);
  }
  
  public static DoubleIterator asDoubleIterator(File file)
    throws IOException
  {
    return new DoubleDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
  }
  
  public static DoubleIterator asDoubleIterator(CharSequence filename)
    throws IOException
  {
    return asDoubleIterator(new File(filename.toString()));
  }
  
  private static final class DoubleDataInputWrapper
    extends AbstractDoubleIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private double next;
    
    public DoubleDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readDouble();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public double nextDouble()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class FloatDataInputWrapper
    extends AbstractFloatIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private float next;
    
    public FloatDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readFloat();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public float nextFloat()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class LongDataInputWrapper
    extends AbstractLongIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private long next;
    
    public LongDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readLong();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public long nextLong()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class IntDataInputWrapper
    extends AbstractIntIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private int next;
    
    public IntDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readInt();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class CharDataInputWrapper
    extends AbstractCharIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private char next;
    
    public CharDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readChar();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public char nextChar()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class ShortDataInputWrapper
    extends AbstractShortIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private short next;
    
    public ShortDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readShort();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public short nextShort()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class ByteDataInputWrapper
    extends AbstractByteIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private byte next;
    
    public ByteDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readByte();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
  
  private static final class BooleanDataInputWrapper
    extends AbstractBooleanIterator
  {
    private final DataInput dataInput;
    private boolean toAdvance = true;
    private boolean endOfProcess = false;
    private boolean next;
    
    public BooleanDataInputWrapper(DataInput dataInput)
    {
      this.dataInput = dataInput;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return !this.endOfProcess;
      }
      this.toAdvance = false;
      try
      {
        this.next = this.dataInput.readBoolean();
      }
      catch (EOFException eof)
      {
        this.endOfProcess = true;
      }
      catch (IOException eof)
      {
        throw new RuntimeException(eof);
      }
      return !this.endOfProcess;
    }
    
    public boolean nextBoolean()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.toAdvance = true;
      return this.next;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.BinIO
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */