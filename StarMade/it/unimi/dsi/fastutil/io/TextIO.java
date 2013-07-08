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
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class TextIO
{
  public static final int BUFFER_SIZE = 8192;
  
  public static int loadBooleans(BufferedReader reader, boolean[] array, int offset, int length)
    throws IOException
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Boolean.parseBoolean(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadBooleans(BufferedReader reader, boolean[] array)
    throws IOException
  {
    return loadBooleans(reader, array, 0, array.length);
  }
  
  public static int loadBooleans(File file, boolean[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadBooleans(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static int loadBooleans(CharSequence filename, boolean[] array, int offset, int length)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadBooleans(File file, boolean[] array)
    throws IOException
  {
    return loadBooleans(file, array, 0, array.length);
  }
  
  public static int loadBooleans(CharSequence filename, boolean[] array)
    throws IOException
  {
    return loadBooleans(filename, array, 0, array.length);
  }
  
  public static void storeBooleans(boolean[] array, int offset, int length, PrintStream stream)
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeBooleans(boolean[] array, PrintStream stream)
  {
    storeBooleans(array, 0, array.length, stream);
  }
  
  public static void storeBooleans(boolean[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeBooleans(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeBooleans(boolean[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBooleans(boolean[] array, File file)
    throws IOException
  {
    storeBooleans(array, 0, array.length, file);
  }
  
  public static void storeBooleans(boolean[] array, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, 0, array.length, filename);
  }
  
  public static void storeBooleans(BooleanIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextBoolean());
    }
  }
  
  public static void storeBooleans(BooleanIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeBooleans(local_i, stream);
    stream.close();
  }
  
  public static void storeBooleans(BooleanIterator local_i, CharSequence filename)
    throws IOException
  {
    storeBooleans(local_i, new File(filename.toString()));
  }
  
  public static long loadBooleans(BufferedReader reader, boolean[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Boolean.parseBoolean(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadBooleans(BufferedReader reader, boolean[][] array)
    throws IOException
  {
    return loadBooleans(reader, array, 0L, BooleanBigArrays.length(array));
  }
  
  public static long loadBooleans(File file, boolean[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadBooleans(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static long loadBooleans(CharSequence filename, boolean[][] array, long offset, long length)
    throws IOException
  {
    return loadBooleans(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadBooleans(File file, boolean[][] array)
    throws IOException
  {
    return loadBooleans(file, array, 0L, BooleanBigArrays.length(array));
  }
  
  public static long loadBooleans(CharSequence filename, boolean[][] array)
    throws IOException
  {
    return loadBooleans(filename, array, 0L, BooleanBigArrays.length(array));
  }
  
  public static void storeBooleans(boolean[][] array, long offset, long length, PrintStream stream)
  {
    BooleanBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      boolean[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeBooleans(boolean[][] array, PrintStream stream)
  {
    storeBooleans(array, 0L, BooleanBigArrays.length(array), stream);
  }
  
  public static void storeBooleans(boolean[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeBooleans(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeBooleans(boolean[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBooleans(boolean[][] array, File file)
    throws IOException
  {
    storeBooleans(array, 0L, BooleanBigArrays.length(array), file);
  }
  
  public static void storeBooleans(boolean[][] array, CharSequence filename)
    throws IOException
  {
    storeBooleans(array, 0L, BooleanBigArrays.length(array), filename);
  }
  
  public static BooleanIterator asBooleanIterator(BufferedReader reader)
  {
    return new BooleanReaderWrapper(reader);
  }
  
  public static BooleanIterator asBooleanIterator(File file)
    throws IOException
  {
    return new BooleanReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static BooleanIterator asBooleanIterator(CharSequence filename)
    throws IOException
  {
    return asBooleanIterator(new File(filename.toString()));
  }
  
  public static int loadBytes(BufferedReader reader, byte[] array, int offset, int length)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Byte.parseByte(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadBytes(BufferedReader reader, byte[] array)
    throws IOException
  {
    return loadBytes(reader, array, 0, array.length);
  }
  
  public static int loadBytes(File file, byte[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadBytes(reader, array, offset, length);
    reader.close();
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
    return loadBytes(file, array, 0, array.length);
  }
  
  public static int loadBytes(CharSequence filename, byte[] array)
    throws IOException
  {
    return loadBytes(filename, array, 0, array.length);
  }
  
  public static void storeBytes(byte[] array, int offset, int length, PrintStream stream)
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeBytes(byte[] array, PrintStream stream)
  {
    storeBytes(array, 0, array.length, stream);
  }
  
  public static void storeBytes(byte[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeBytes(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeBytes(byte[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeBytes(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBytes(byte[] array, File file)
    throws IOException
  {
    storeBytes(array, 0, array.length, file);
  }
  
  public static void storeBytes(byte[] array, CharSequence filename)
    throws IOException
  {
    storeBytes(array, 0, array.length, filename);
  }
  
  public static void storeBytes(ByteIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextByte());
    }
  }
  
  public static void storeBytes(ByteIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeBytes(local_i, stream);
    stream.close();
  }
  
  public static void storeBytes(ByteIterator local_i, CharSequence filename)
    throws IOException
  {
    storeBytes(local_i, new File(filename.toString()));
  }
  
  public static long loadBytes(BufferedReader reader, byte[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Byte.parseByte(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadBytes(BufferedReader reader, byte[][] array)
    throws IOException
  {
    return loadBytes(reader, array, 0L, ByteBigArrays.length(array));
  }
  
  public static long loadBytes(File file, byte[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadBytes(reader, array, offset, length);
    reader.close();
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
    return loadBytes(file, array, 0L, ByteBigArrays.length(array));
  }
  
  public static long loadBytes(CharSequence filename, byte[][] array)
    throws IOException
  {
    return loadBytes(filename, array, 0L, ByteBigArrays.length(array));
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, PrintStream stream)
  {
    ByteBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      byte[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeBytes(byte[][] array, PrintStream stream)
  {
    storeBytes(array, 0L, ByteBigArrays.length(array), stream);
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeBytes(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeBytes(byte[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeBytes(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeBytes(byte[][] array, File file)
    throws IOException
  {
    storeBytes(array, 0L, ByteBigArrays.length(array), file);
  }
  
  public static void storeBytes(byte[][] array, CharSequence filename)
    throws IOException
  {
    storeBytes(array, 0L, ByteBigArrays.length(array), filename);
  }
  
  public static ByteIterator asByteIterator(BufferedReader reader)
  {
    return new ByteReaderWrapper(reader);
  }
  
  public static ByteIterator asByteIterator(File file)
    throws IOException
  {
    return new ByteReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static ByteIterator asByteIterator(CharSequence filename)
    throws IOException
  {
    return asByteIterator(new File(filename.toString()));
  }
  
  public static int loadShorts(BufferedReader reader, short[] array, int offset, int length)
    throws IOException
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Short.parseShort(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadShorts(BufferedReader reader, short[] array)
    throws IOException
  {
    return loadShorts(reader, array, 0, array.length);
  }
  
  public static int loadShorts(File file, short[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadShorts(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static int loadShorts(CharSequence filename, short[] array, int offset, int length)
    throws IOException
  {
    return loadShorts(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadShorts(File file, short[] array)
    throws IOException
  {
    return loadShorts(file, array, 0, array.length);
  }
  
  public static int loadShorts(CharSequence filename, short[] array)
    throws IOException
  {
    return loadShorts(filename, array, 0, array.length);
  }
  
  public static void storeShorts(short[] array, int offset, int length, PrintStream stream)
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeShorts(short[] array, PrintStream stream)
  {
    storeShorts(array, 0, array.length, stream);
  }
  
  public static void storeShorts(short[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeShorts(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeShorts(short[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeShorts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeShorts(short[] array, File file)
    throws IOException
  {
    storeShorts(array, 0, array.length, file);
  }
  
  public static void storeShorts(short[] array, CharSequence filename)
    throws IOException
  {
    storeShorts(array, 0, array.length, filename);
  }
  
  public static void storeShorts(ShortIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextShort());
    }
  }
  
  public static void storeShorts(ShortIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeShorts(local_i, stream);
    stream.close();
  }
  
  public static void storeShorts(ShortIterator local_i, CharSequence filename)
    throws IOException
  {
    storeShorts(local_i, new File(filename.toString()));
  }
  
  public static long loadShorts(BufferedReader reader, short[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Short.parseShort(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadShorts(BufferedReader reader, short[][] array)
    throws IOException
  {
    return loadShorts(reader, array, 0L, ShortBigArrays.length(array));
  }
  
  public static long loadShorts(File file, short[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadShorts(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static long loadShorts(CharSequence filename, short[][] array, long offset, long length)
    throws IOException
  {
    return loadShorts(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadShorts(File file, short[][] array)
    throws IOException
  {
    return loadShorts(file, array, 0L, ShortBigArrays.length(array));
  }
  
  public static long loadShorts(CharSequence filename, short[][] array)
    throws IOException
  {
    return loadShorts(filename, array, 0L, ShortBigArrays.length(array));
  }
  
  public static void storeShorts(short[][] array, long offset, long length, PrintStream stream)
  {
    ShortBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      short[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeShorts(short[][] array, PrintStream stream)
  {
    storeShorts(array, 0L, ShortBigArrays.length(array), stream);
  }
  
  public static void storeShorts(short[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeShorts(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeShorts(short[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeShorts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeShorts(short[][] array, File file)
    throws IOException
  {
    storeShorts(array, 0L, ShortBigArrays.length(array), file);
  }
  
  public static void storeShorts(short[][] array, CharSequence filename)
    throws IOException
  {
    storeShorts(array, 0L, ShortBigArrays.length(array), filename);
  }
  
  public static ShortIterator asShortIterator(BufferedReader reader)
  {
    return new ShortReaderWrapper(reader);
  }
  
  public static ShortIterator asShortIterator(File file)
    throws IOException
  {
    return new ShortReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static ShortIterator asShortIterator(CharSequence filename)
    throws IOException
  {
    return asShortIterator(new File(filename.toString()));
  }
  
  public static int loadInts(BufferedReader reader, int[] array, int offset, int length)
    throws IOException
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Integer.parseInt(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadInts(BufferedReader reader, int[] array)
    throws IOException
  {
    return loadInts(reader, array, 0, array.length);
  }
  
  public static int loadInts(File file, int[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadInts(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static int loadInts(CharSequence filename, int[] array, int offset, int length)
    throws IOException
  {
    return loadInts(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadInts(File file, int[] array)
    throws IOException
  {
    return loadInts(file, array, 0, array.length);
  }
  
  public static int loadInts(CharSequence filename, int[] array)
    throws IOException
  {
    return loadInts(filename, array, 0, array.length);
  }
  
  public static void storeInts(int[] array, int offset, int length, PrintStream stream)
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeInts(int[] array, PrintStream stream)
  {
    storeInts(array, 0, array.length, stream);
  }
  
  public static void storeInts(int[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeInts(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeInts(int[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeInts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeInts(int[] array, File file)
    throws IOException
  {
    storeInts(array, 0, array.length, file);
  }
  
  public static void storeInts(int[] array, CharSequence filename)
    throws IOException
  {
    storeInts(array, 0, array.length, filename);
  }
  
  public static void storeInts(IntIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextInt());
    }
  }
  
  public static void storeInts(IntIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeInts(local_i, stream);
    stream.close();
  }
  
  public static void storeInts(IntIterator local_i, CharSequence filename)
    throws IOException
  {
    storeInts(local_i, new File(filename.toString()));
  }
  
  public static long loadInts(BufferedReader reader, int[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Integer.parseInt(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadInts(BufferedReader reader, int[][] array)
    throws IOException
  {
    return loadInts(reader, array, 0L, IntBigArrays.length(array));
  }
  
  public static long loadInts(File file, int[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadInts(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static long loadInts(CharSequence filename, int[][] array, long offset, long length)
    throws IOException
  {
    return loadInts(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadInts(File file, int[][] array)
    throws IOException
  {
    return loadInts(file, array, 0L, IntBigArrays.length(array));
  }
  
  public static long loadInts(CharSequence filename, int[][] array)
    throws IOException
  {
    return loadInts(filename, array, 0L, IntBigArrays.length(array));
  }
  
  public static void storeInts(int[][] array, long offset, long length, PrintStream stream)
  {
    IntBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      int[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeInts(int[][] array, PrintStream stream)
  {
    storeInts(array, 0L, IntBigArrays.length(array), stream);
  }
  
  public static void storeInts(int[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeInts(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeInts(int[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeInts(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeInts(int[][] array, File file)
    throws IOException
  {
    storeInts(array, 0L, IntBigArrays.length(array), file);
  }
  
  public static void storeInts(int[][] array, CharSequence filename)
    throws IOException
  {
    storeInts(array, 0L, IntBigArrays.length(array), filename);
  }
  
  public static IntIterator asIntIterator(BufferedReader reader)
  {
    return new IntReaderWrapper(reader);
  }
  
  public static IntIterator asIntIterator(File file)
    throws IOException
  {
    return new IntReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static IntIterator asIntIterator(CharSequence filename)
    throws IOException
  {
    return asIntIterator(new File(filename.toString()));
  }
  
  public static int loadLongs(BufferedReader reader, long[] array, int offset, int length)
    throws IOException
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Long.parseLong(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadLongs(BufferedReader reader, long[] array)
    throws IOException
  {
    return loadLongs(reader, array, 0, array.length);
  }
  
  public static int loadLongs(File file, long[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadLongs(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static int loadLongs(CharSequence filename, long[] array, int offset, int length)
    throws IOException
  {
    return loadLongs(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadLongs(File file, long[] array)
    throws IOException
  {
    return loadLongs(file, array, 0, array.length);
  }
  
  public static int loadLongs(CharSequence filename, long[] array)
    throws IOException
  {
    return loadLongs(filename, array, 0, array.length);
  }
  
  public static void storeLongs(long[] array, int offset, int length, PrintStream stream)
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeLongs(long[] array, PrintStream stream)
  {
    storeLongs(array, 0, array.length, stream);
  }
  
  public static void storeLongs(long[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeLongs(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeLongs(long[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeLongs(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeLongs(long[] array, File file)
    throws IOException
  {
    storeLongs(array, 0, array.length, file);
  }
  
  public static void storeLongs(long[] array, CharSequence filename)
    throws IOException
  {
    storeLongs(array, 0, array.length, filename);
  }
  
  public static void storeLongs(LongIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextLong());
    }
  }
  
  public static void storeLongs(LongIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeLongs(local_i, stream);
    stream.close();
  }
  
  public static void storeLongs(LongIterator local_i, CharSequence filename)
    throws IOException
  {
    storeLongs(local_i, new File(filename.toString()));
  }
  
  public static long loadLongs(BufferedReader reader, long[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Long.parseLong(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadLongs(BufferedReader reader, long[][] array)
    throws IOException
  {
    return loadLongs(reader, array, 0L, LongBigArrays.length(array));
  }
  
  public static long loadLongs(File file, long[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadLongs(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static long loadLongs(CharSequence filename, long[][] array, long offset, long length)
    throws IOException
  {
    return loadLongs(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadLongs(File file, long[][] array)
    throws IOException
  {
    return loadLongs(file, array, 0L, LongBigArrays.length(array));
  }
  
  public static long loadLongs(CharSequence filename, long[][] array)
    throws IOException
  {
    return loadLongs(filename, array, 0L, LongBigArrays.length(array));
  }
  
  public static void storeLongs(long[][] array, long offset, long length, PrintStream stream)
  {
    LongBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      long[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeLongs(long[][] array, PrintStream stream)
  {
    storeLongs(array, 0L, LongBigArrays.length(array), stream);
  }
  
  public static void storeLongs(long[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeLongs(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeLongs(long[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeLongs(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeLongs(long[][] array, File file)
    throws IOException
  {
    storeLongs(array, 0L, LongBigArrays.length(array), file);
  }
  
  public static void storeLongs(long[][] array, CharSequence filename)
    throws IOException
  {
    storeLongs(array, 0L, LongBigArrays.length(array), filename);
  }
  
  public static LongIterator asLongIterator(BufferedReader reader)
  {
    return new LongReaderWrapper(reader);
  }
  
  public static LongIterator asLongIterator(File file)
    throws IOException
  {
    return new LongReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static LongIterator asLongIterator(CharSequence filename)
    throws IOException
  {
    return asLongIterator(new File(filename.toString()));
  }
  
  public static int loadFloats(BufferedReader reader, float[] array, int offset, int length)
    throws IOException
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Float.parseFloat(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadFloats(BufferedReader reader, float[] array)
    throws IOException
  {
    return loadFloats(reader, array, 0, array.length);
  }
  
  public static int loadFloats(File file, float[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadFloats(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static int loadFloats(CharSequence filename, float[] array, int offset, int length)
    throws IOException
  {
    return loadFloats(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadFloats(File file, float[] array)
    throws IOException
  {
    return loadFloats(file, array, 0, array.length);
  }
  
  public static int loadFloats(CharSequence filename, float[] array)
    throws IOException
  {
    return loadFloats(filename, array, 0, array.length);
  }
  
  public static void storeFloats(float[] array, int offset, int length, PrintStream stream)
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeFloats(float[] array, PrintStream stream)
  {
    storeFloats(array, 0, array.length, stream);
  }
  
  public static void storeFloats(float[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeFloats(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeFloats(float[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeFloats(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeFloats(float[] array, File file)
    throws IOException
  {
    storeFloats(array, 0, array.length, file);
  }
  
  public static void storeFloats(float[] array, CharSequence filename)
    throws IOException
  {
    storeFloats(array, 0, array.length, filename);
  }
  
  public static void storeFloats(FloatIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextFloat());
    }
  }
  
  public static void storeFloats(FloatIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeFloats(local_i, stream);
    stream.close();
  }
  
  public static void storeFloats(FloatIterator local_i, CharSequence filename)
    throws IOException
  {
    storeFloats(local_i, new File(filename.toString()));
  }
  
  public static long loadFloats(BufferedReader reader, float[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Float.parseFloat(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadFloats(BufferedReader reader, float[][] array)
    throws IOException
  {
    return loadFloats(reader, array, 0L, FloatBigArrays.length(array));
  }
  
  public static long loadFloats(File file, float[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadFloats(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static long loadFloats(CharSequence filename, float[][] array, long offset, long length)
    throws IOException
  {
    return loadFloats(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadFloats(File file, float[][] array)
    throws IOException
  {
    return loadFloats(file, array, 0L, FloatBigArrays.length(array));
  }
  
  public static long loadFloats(CharSequence filename, float[][] array)
    throws IOException
  {
    return loadFloats(filename, array, 0L, FloatBigArrays.length(array));
  }
  
  public static void storeFloats(float[][] array, long offset, long length, PrintStream stream)
  {
    FloatBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      float[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeFloats(float[][] array, PrintStream stream)
  {
    storeFloats(array, 0L, FloatBigArrays.length(array), stream);
  }
  
  public static void storeFloats(float[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeFloats(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeFloats(float[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeFloats(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeFloats(float[][] array, File file)
    throws IOException
  {
    storeFloats(array, 0L, FloatBigArrays.length(array), file);
  }
  
  public static void storeFloats(float[][] array, CharSequence filename)
    throws IOException
  {
    storeFloats(array, 0L, FloatBigArrays.length(array), filename);
  }
  
  public static FloatIterator asFloatIterator(BufferedReader reader)
  {
    return new FloatReaderWrapper(reader);
  }
  
  public static FloatIterator asFloatIterator(File file)
    throws IOException
  {
    return new FloatReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static FloatIterator asFloatIterator(CharSequence filename)
    throws IOException
  {
    return asFloatIterator(new File(filename.toString()));
  }
  
  public static int loadDoubles(BufferedReader reader, double[] array, int offset, int length)
    throws IOException
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    int local_i = 0;
    try
    {
      String local_s;
      for (local_i = 0; (local_i < length) && ((local_s = reader.readLine()) != null); local_i++) {
        array[(local_i + offset)] = Double.parseDouble(local_s);
      }
    }
    catch (EOFException itsOk) {}
    return local_i;
  }
  
  public static int loadDoubles(BufferedReader reader, double[] array)
    throws IOException
  {
    return loadDoubles(reader, array, 0, array.length);
  }
  
  public static int loadDoubles(File file, double[] array, int offset, int length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int result = loadDoubles(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static int loadDoubles(CharSequence filename, double[] array, int offset, int length)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()), array, offset, length);
  }
  
  public static int loadDoubles(File file, double[] array)
    throws IOException
  {
    return loadDoubles(file, array, 0, array.length);
  }
  
  public static int loadDoubles(CharSequence filename, double[] array)
    throws IOException
  {
    return loadDoubles(filename, array, 0, array.length);
  }
  
  public static void storeDoubles(double[] array, int offset, int length, PrintStream stream)
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      stream.println(array[(offset + local_i)]);
    }
  }
  
  public static void storeDoubles(double[] array, PrintStream stream)
  {
    storeDoubles(array, 0, array.length, stream);
  }
  
  public static void storeDoubles(double[] array, int offset, int length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeDoubles(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeDoubles(double[] array, int offset, int length, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeDoubles(double[] array, File file)
    throws IOException
  {
    storeDoubles(array, 0, array.length, file);
  }
  
  public static void storeDoubles(double[] array, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, 0, array.length, filename);
  }
  
  public static void storeDoubles(DoubleIterator local_i, PrintStream stream)
  {
    while (local_i.hasNext()) {
      stream.println(local_i.nextDouble());
    }
  }
  
  public static void storeDoubles(DoubleIterator local_i, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeDoubles(local_i, stream);
    stream.close();
  }
  
  public static void storeDoubles(DoubleIterator local_i, CharSequence filename)
    throws IOException
  {
    storeDoubles(local_i, new File(filename.toString()));
  }
  
  public static long loadDoubles(BufferedReader reader, double[][] array, long offset, long length)
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
          String local_s;
          if ((local_s = reader.readLine()) != null) {
            local_t[local_d] = Double.parseDouble(local_s);
          } else {
            return local_c;
          }
          local_c += 1L;
        }
      }
    }
    catch (EOFException local_i) {}
    return local_c;
  }
  
  public static long loadDoubles(BufferedReader reader, double[][] array)
    throws IOException
  {
    return loadDoubles(reader, array, 0L, DoubleBigArrays.length(array));
  }
  
  public static long loadDoubles(File file, double[][] array, long offset, long length)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    long result = loadDoubles(reader, array, offset, length);
    reader.close();
    return result;
  }
  
  public static long loadDoubles(CharSequence filename, double[][] array, long offset, long length)
    throws IOException
  {
    return loadDoubles(new File(filename.toString()), array, offset, length);
  }
  
  public static long loadDoubles(File file, double[][] array)
    throws IOException
  {
    return loadDoubles(file, array, 0L, DoubleBigArrays.length(array));
  }
  
  public static long loadDoubles(CharSequence filename, double[][] array)
    throws IOException
  {
    return loadDoubles(filename, array, 0L, DoubleBigArrays.length(array));
  }
  
  public static void storeDoubles(double[][] array, long offset, long length, PrintStream stream)
  {
    DoubleBigArrays.ensureOffsetLength(array, offset, length);
    for (int local_i = BigArrays.segment(offset); local_i < BigArrays.segment(offset + length + 134217727L); local_i++)
    {
      double[] local_t = array[local_i];
      int local_l = (int)Math.min(local_t.length, offset + length - BigArrays.start(local_i));
      for (int local_d = (int)Math.max(0L, offset - BigArrays.start(local_i)); local_d < local_l; local_d++) {
        stream.println(local_t[local_d]);
      }
    }
  }
  
  public static void storeDoubles(double[][] array, PrintStream stream)
  {
    storeDoubles(array, 0L, DoubleBigArrays.length(array), stream);
  }
  
  public static void storeDoubles(double[][] array, long offset, long length, File file)
    throws IOException
  {
    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
    storeDoubles(array, offset, length, stream);
    stream.close();
  }
  
  public static void storeDoubles(double[][] array, long offset, long length, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, offset, length, new File(filename.toString()));
  }
  
  public static void storeDoubles(double[][] array, File file)
    throws IOException
  {
    storeDoubles(array, 0L, DoubleBigArrays.length(array), file);
  }
  
  public static void storeDoubles(double[][] array, CharSequence filename)
    throws IOException
  {
    storeDoubles(array, 0L, DoubleBigArrays.length(array), filename);
  }
  
  public static DoubleIterator asDoubleIterator(BufferedReader reader)
  {
    return new DoubleReaderWrapper(reader);
  }
  
  public static DoubleIterator asDoubleIterator(File file)
    throws IOException
  {
    return new DoubleReaderWrapper(new BufferedReader(new FileReader(file)));
  }
  
  public static DoubleIterator asDoubleIterator(CharSequence filename)
    throws IOException
  {
    return asDoubleIterator(new File(filename.toString()));
  }
  
  private static final class DoubleReaderWrapper
    extends AbstractDoubleIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_319;
    private double next;
    
    public DoubleReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_319 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_319 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_319 == null) {
        return false;
      }
      this.next = Double.parseDouble(this.field_319);
      return true;
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
  
  private static final class FloatReaderWrapper
    extends AbstractFloatIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_313;
    private float next;
    
    public FloatReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_313 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_313 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_313 == null) {
        return false;
      }
      this.next = Float.parseFloat(this.field_313);
      return true;
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
  
  private static final class LongReaderWrapper
    extends AbstractLongIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_66;
    private long next;
    
    public LongReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_66 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_66 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_66 == null) {
        return false;
      }
      this.next = Long.parseLong(this.field_66);
      return true;
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
  
  private static final class IntReaderWrapper
    extends AbstractIntIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_417;
    private int next;
    
    public IntReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_417 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_417 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_417 == null) {
        return false;
      }
      this.next = Integer.parseInt(this.field_417);
      return true;
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
  
  private static final class ShortReaderWrapper
    extends AbstractShortIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_334;
    private short next;
    
    public ShortReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_334 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_334 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_334 == null) {
        return false;
      }
      this.next = Short.parseShort(this.field_334);
      return true;
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
  
  private static final class ByteReaderWrapper
    extends AbstractByteIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_348;
    private byte next;
    
    public ByteReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_348 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_348 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_348 == null) {
        return false;
      }
      this.next = Byte.parseByte(this.field_348);
      return true;
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
  
  private static final class BooleanReaderWrapper
    extends AbstractBooleanIterator
  {
    private final BufferedReader reader;
    private boolean toAdvance = true;
    private String field_371;
    private boolean next;
    
    public BooleanReaderWrapper(BufferedReader reader)
    {
      this.reader = reader;
    }
    
    public boolean hasNext()
    {
      if (!this.toAdvance) {
        return this.field_371 != null;
      }
      this.toAdvance = false;
      try
      {
        this.field_371 = this.reader.readLine();
      }
      catch (EOFException itsOk) {}catch (IOException itsOk)
      {
        throw new RuntimeException(itsOk);
      }
      if (this.field_371 == null) {
        return false;
      }
      this.next = Boolean.parseBoolean(this.field_371);
      return true;
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
 * Qualified Name:     it.unimi.dsi.fastutil.io.TextIO
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */