/*      */ package it.unimi.dsi.fastutil.io;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.BigArrays;
/*      */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*      */ import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*      */ import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*      */ import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
/*      */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*      */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleArrays;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleBigArrays;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*      */ import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*      */ import it.unimi.dsi.fastutil.floats.FloatArrays;
/*      */ import it.unimi.dsi.fastutil.floats.FloatBigArrays;
/*      */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*      */ import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import it.unimi.dsi.fastutil.ints.IntBigArrays;
/*      */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*      */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*      */ import it.unimi.dsi.fastutil.longs.LongArrays;
/*      */ import it.unimi.dsi.fastutil.longs.LongBigArrays;
/*      */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*      */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortArrays;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortBigArrays;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class TextIO
/*      */ {
/*      */   public static final int BUFFER_SIZE = 8192;
/*      */ 
/*      */   public static int loadBooleans(BufferedReader reader, boolean[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  136 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/*  137 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/*  140 */       for (i = 0; (i < length) && 
/*  141 */         ((s = reader.readLine()) != null); i++)
/*  141 */         array[(i + offset)] = Boolean.parseBoolean(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/*  145 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(BufferedReader reader, boolean[] array)
/*      */     throws IOException
/*      */   {
/*  154 */     return loadBooleans(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(File file, boolean[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  165 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/*  166 */     int result = loadBooleans(reader, array, offset, length);
/*  167 */     reader.close();
/*  168 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(CharSequence filename, boolean[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  179 */     return loadBooleans(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(File file, boolean[] array)
/*      */     throws IOException
/*      */   {
/*  188 */     return loadBooleans(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(CharSequence filename, boolean[] array)
/*      */     throws IOException
/*      */   {
/*  197 */     return loadBooleans(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, int offset, int length, PrintStream stream)
/*      */   {
/*  207 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/*  208 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, PrintStream stream)
/*      */   {
/*  216 */     storeBooleans(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/*  226 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  227 */     storeBooleans(array, offset, length, stream);
/*  228 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  238 */     storeBooleans(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, File file)
/*      */     throws IOException
/*      */   {
/*  246 */     storeBooleans(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  254 */     storeBooleans(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(BooleanIterator i, PrintStream stream)
/*      */   {
/*  262 */     while (i.hasNext()) stream.println(i.nextBoolean());
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(BooleanIterator i, File file)
/*      */     throws IOException
/*      */   {
/*  270 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  271 */     storeBooleans(i, stream);
/*  272 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(BooleanIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  280 */     storeBooleans(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(BufferedReader reader, boolean[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  291 */     BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  292 */     long c = 0L;
/*      */     try
/*      */     {
/*  295 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  296 */         boolean[] t = array[i];
/*  297 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  298 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/*  299 */           String s;
/*  299 */           if ((s = reader.readLine()) != null) t[d] = Boolean.parseBoolean(s); else
/*  300 */             return c;
/*  301 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/*  306 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(BufferedReader reader, boolean[][] array)
/*      */     throws IOException
/*      */   {
/*  315 */     return loadBooleans(reader, array, 0L, BooleanBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(File file, boolean[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  326 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/*  327 */     long result = loadBooleans(reader, array, offset, length);
/*  328 */     reader.close();
/*  329 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(CharSequence filename, boolean[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  340 */     return loadBooleans(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(File file, boolean[][] array)
/*      */     throws IOException
/*      */   {
/*  349 */     return loadBooleans(file, array, 0L, BooleanBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(CharSequence filename, boolean[][] array)
/*      */     throws IOException
/*      */   {
/*  358 */     return loadBooleans(filename, array, 0L, BooleanBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, long offset, long length, PrintStream stream)
/*      */   {
/*  368 */     BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  369 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  370 */       boolean[] t = array[i];
/*  371 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  372 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, PrintStream stream)
/*      */   {
/*  381 */     storeBooleans(array, 0L, BooleanBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/*  391 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  392 */     storeBooleans(array, offset, length, stream);
/*  393 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  403 */     storeBooleans(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, File file)
/*      */     throws IOException
/*      */   {
/*  411 */     storeBooleans(array, 0L, BooleanBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  419 */     storeBooleans(array, 0L, BooleanBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static BooleanIterator asBooleanIterator(BufferedReader reader)
/*      */   {
/*  453 */     return new BooleanReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static BooleanIterator asBooleanIterator(File file)
/*      */     throws IOException
/*      */   {
/*  460 */     return new BooleanReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static BooleanIterator asBooleanIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  467 */     return asBooleanIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadBytes(BufferedReader reader, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  515 */     ByteArrays.ensureOffsetLength(array, offset, length);
/*  516 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/*  519 */       for (i = 0; (i < length) && 
/*  520 */         ((s = reader.readLine()) != null); i++)
/*  520 */         array[(i + offset)] = Byte.parseByte(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/*  524 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBytes(BufferedReader reader, byte[] array)
/*      */     throws IOException
/*      */   {
/*  533 */     return loadBytes(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadBytes(File file, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  544 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/*  545 */     int result = loadBytes(reader, array, offset, length);
/*  546 */     reader.close();
/*  547 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadBytes(CharSequence filename, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  558 */     return loadBytes(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadBytes(File file, byte[] array)
/*      */     throws IOException
/*      */   {
/*  567 */     return loadBytes(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadBytes(CharSequence filename, byte[] array)
/*      */     throws IOException
/*      */   {
/*  576 */     return loadBytes(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, PrintStream stream)
/*      */   {
/*  586 */     ByteArrays.ensureOffsetLength(array, offset, length);
/*  587 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, PrintStream stream)
/*      */   {
/*  595 */     storeBytes(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/*  605 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  606 */     storeBytes(array, offset, length, stream);
/*  607 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  617 */     storeBytes(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, File file)
/*      */     throws IOException
/*      */   {
/*  625 */     storeBytes(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  633 */     storeBytes(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(ByteIterator i, PrintStream stream)
/*      */   {
/*  641 */     while (i.hasNext()) stream.println(i.nextByte());
/*      */   }
/*      */ 
/*      */   public static void storeBytes(ByteIterator i, File file)
/*      */     throws IOException
/*      */   {
/*  649 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  650 */     storeBytes(i, stream);
/*  651 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(ByteIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  659 */     storeBytes(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadBytes(BufferedReader reader, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  670 */     ByteBigArrays.ensureOffsetLength(array, offset, length);
/*  671 */     long c = 0L;
/*      */     try
/*      */     {
/*  674 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  675 */         byte[] t = array[i];
/*  676 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  677 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/*  678 */           String s;
/*  678 */           if ((s = reader.readLine()) != null) t[d] = Byte.parseByte(s); else
/*  679 */             return c;
/*  680 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/*  685 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBytes(BufferedReader reader, byte[][] array)
/*      */     throws IOException
/*      */   {
/*  694 */     return loadBytes(reader, array, 0L, ByteBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadBytes(File file, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  705 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/*  706 */     long result = loadBytes(reader, array, offset, length);
/*  707 */     reader.close();
/*  708 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadBytes(CharSequence filename, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  719 */     return loadBytes(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadBytes(File file, byte[][] array)
/*      */     throws IOException
/*      */   {
/*  728 */     return loadBytes(file, array, 0L, ByteBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadBytes(CharSequence filename, byte[][] array)
/*      */     throws IOException
/*      */   {
/*  737 */     return loadBytes(filename, array, 0L, ByteBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, PrintStream stream)
/*      */   {
/*  747 */     ByteBigArrays.ensureOffsetLength(array, offset, length);
/*  748 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  749 */       byte[] t = array[i];
/*  750 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  751 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, PrintStream stream)
/*      */   {
/*  760 */     storeBytes(array, 0L, ByteBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/*  770 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  771 */     storeBytes(array, offset, length, stream);
/*  772 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  782 */     storeBytes(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, File file)
/*      */     throws IOException
/*      */   {
/*  790 */     storeBytes(array, 0L, ByteBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  798 */     storeBytes(array, 0L, ByteBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static ByteIterator asByteIterator(BufferedReader reader)
/*      */   {
/*  832 */     return new ByteReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static ByteIterator asByteIterator(File file)
/*      */     throws IOException
/*      */   {
/*  839 */     return new ByteReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static ByteIterator asByteIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  846 */     return asByteIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadShorts(BufferedReader reader, short[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  894 */     ShortArrays.ensureOffsetLength(array, offset, length);
/*  895 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/*  898 */       for (i = 0; (i < length) && 
/*  899 */         ((s = reader.readLine()) != null); i++)
/*  899 */         array[(i + offset)] = Short.parseShort(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/*  903 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadShorts(BufferedReader reader, short[] array)
/*      */     throws IOException
/*      */   {
/*  912 */     return loadShorts(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadShorts(File file, short[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  923 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/*  924 */     int result = loadShorts(reader, array, offset, length);
/*  925 */     reader.close();
/*  926 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadShorts(CharSequence filename, short[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  937 */     return loadShorts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadShorts(File file, short[] array)
/*      */     throws IOException
/*      */   {
/*  946 */     return loadShorts(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadShorts(CharSequence filename, short[] array)
/*      */     throws IOException
/*      */   {
/*  955 */     return loadShorts(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, int offset, int length, PrintStream stream)
/*      */   {
/*  965 */     ShortArrays.ensureOffsetLength(array, offset, length);
/*  966 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, PrintStream stream)
/*      */   {
/*  974 */     storeShorts(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/*  984 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  985 */     storeShorts(array, offset, length, stream);
/*  986 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  996 */     storeShorts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, File file)
/*      */     throws IOException
/*      */   {
/* 1004 */     storeShorts(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1012 */     storeShorts(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(ShortIterator i, PrintStream stream)
/*      */   {
/* 1020 */     while (i.hasNext()) stream.println(i.nextShort());
/*      */   }
/*      */ 
/*      */   public static void storeShorts(ShortIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 1028 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1029 */     storeShorts(i, stream);
/* 1030 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(ShortIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1038 */     storeShorts(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadShorts(BufferedReader reader, short[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1049 */     ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1050 */     long c = 0L;
/*      */     try
/*      */     {
/* 1053 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1054 */         short[] t = array[i];
/* 1055 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1056 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/* 1057 */           String s;
/* 1057 */           if ((s = reader.readLine()) != null) t[d] = Short.parseShort(s); else
/* 1058 */             return c;
/* 1059 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1064 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadShorts(BufferedReader reader, short[][] array)
/*      */     throws IOException
/*      */   {
/* 1073 */     return loadShorts(reader, array, 0L, ShortBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadShorts(File file, short[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1084 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1085 */     long result = loadShorts(reader, array, offset, length);
/* 1086 */     reader.close();
/* 1087 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadShorts(CharSequence filename, short[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1098 */     return loadShorts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadShorts(File file, short[][] array)
/*      */     throws IOException
/*      */   {
/* 1107 */     return loadShorts(file, array, 0L, ShortBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadShorts(CharSequence filename, short[][] array)
/*      */     throws IOException
/*      */   {
/* 1116 */     return loadShorts(filename, array, 0L, ShortBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, long offset, long length, PrintStream stream)
/*      */   {
/* 1126 */     ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1127 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1128 */       short[] t = array[i];
/* 1129 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1130 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, PrintStream stream)
/*      */   {
/* 1139 */     storeShorts(array, 0L, ShortBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 1149 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1150 */     storeShorts(array, offset, length, stream);
/* 1151 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1161 */     storeShorts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 1169 */     storeShorts(array, 0L, ShortBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1177 */     storeShorts(array, 0L, ShortBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static ShortIterator asShortIterator(BufferedReader reader)
/*      */   {
/* 1211 */     return new ShortReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static ShortIterator asShortIterator(File file)
/*      */     throws IOException
/*      */   {
/* 1218 */     return new ShortReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static ShortIterator asShortIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1225 */     return asShortIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadInts(BufferedReader reader, int[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1273 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 1274 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/* 1277 */       for (i = 0; (i < length) && 
/* 1278 */         ((s = reader.readLine()) != null); i++)
/* 1278 */         array[(i + offset)] = Integer.parseInt(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/* 1282 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadInts(BufferedReader reader, int[] array)
/*      */     throws IOException
/*      */   {
/* 1291 */     return loadInts(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadInts(File file, int[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1302 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1303 */     int result = loadInts(reader, array, offset, length);
/* 1304 */     reader.close();
/* 1305 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadInts(CharSequence filename, int[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1316 */     return loadInts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadInts(File file, int[] array)
/*      */     throws IOException
/*      */   {
/* 1325 */     return loadInts(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadInts(CharSequence filename, int[] array)
/*      */     throws IOException
/*      */   {
/* 1334 */     return loadInts(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, int offset, int length, PrintStream stream)
/*      */   {
/* 1344 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 1345 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, PrintStream stream)
/*      */   {
/* 1353 */     storeInts(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 1363 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1364 */     storeInts(array, offset, length, stream);
/* 1365 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1375 */     storeInts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, File file)
/*      */     throws IOException
/*      */   {
/* 1383 */     storeInts(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1391 */     storeInts(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeInts(IntIterator i, PrintStream stream)
/*      */   {
/* 1399 */     while (i.hasNext()) stream.println(i.nextInt());
/*      */   }
/*      */ 
/*      */   public static void storeInts(IntIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 1407 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1408 */     storeInts(i, stream);
/* 1409 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(IntIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1417 */     storeInts(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadInts(BufferedReader reader, int[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1428 */     IntBigArrays.ensureOffsetLength(array, offset, length);
/* 1429 */     long c = 0L;
/*      */     try
/*      */     {
/* 1432 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1433 */         int[] t = array[i];
/* 1434 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1435 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/* 1436 */           String s;
/* 1436 */           if ((s = reader.readLine()) != null) t[d] = Integer.parseInt(s); else
/* 1437 */             return c;
/* 1438 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1443 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadInts(BufferedReader reader, int[][] array)
/*      */     throws IOException
/*      */   {
/* 1452 */     return loadInts(reader, array, 0L, IntBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadInts(File file, int[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1463 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1464 */     long result = loadInts(reader, array, offset, length);
/* 1465 */     reader.close();
/* 1466 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadInts(CharSequence filename, int[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1477 */     return loadInts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadInts(File file, int[][] array)
/*      */     throws IOException
/*      */   {
/* 1486 */     return loadInts(file, array, 0L, IntBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadInts(CharSequence filename, int[][] array)
/*      */     throws IOException
/*      */   {
/* 1495 */     return loadInts(filename, array, 0L, IntBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, long offset, long length, PrintStream stream)
/*      */   {
/* 1505 */     IntBigArrays.ensureOffsetLength(array, offset, length);
/* 1506 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1507 */       int[] t = array[i];
/* 1508 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1509 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, PrintStream stream)
/*      */   {
/* 1518 */     storeInts(array, 0L, IntBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 1528 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1529 */     storeInts(array, offset, length, stream);
/* 1530 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1540 */     storeInts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 1548 */     storeInts(array, 0L, IntBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1556 */     storeInts(array, 0L, IntBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static IntIterator asIntIterator(BufferedReader reader)
/*      */   {
/* 1590 */     return new IntReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static IntIterator asIntIterator(File file)
/*      */     throws IOException
/*      */   {
/* 1597 */     return new IntReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static IntIterator asIntIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1604 */     return asIntIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadLongs(BufferedReader reader, long[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1652 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 1653 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/* 1656 */       for (i = 0; (i < length) && 
/* 1657 */         ((s = reader.readLine()) != null); i++)
/* 1657 */         array[(i + offset)] = Long.parseLong(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/* 1661 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadLongs(BufferedReader reader, long[] array)
/*      */     throws IOException
/*      */   {
/* 1670 */     return loadLongs(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadLongs(File file, long[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1681 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1682 */     int result = loadLongs(reader, array, offset, length);
/* 1683 */     reader.close();
/* 1684 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadLongs(CharSequence filename, long[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1695 */     return loadLongs(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadLongs(File file, long[] array)
/*      */     throws IOException
/*      */   {
/* 1704 */     return loadLongs(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadLongs(CharSequence filename, long[] array)
/*      */     throws IOException
/*      */   {
/* 1713 */     return loadLongs(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, int offset, int length, PrintStream stream)
/*      */   {
/* 1723 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 1724 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, PrintStream stream)
/*      */   {
/* 1732 */     storeLongs(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 1742 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1743 */     storeLongs(array, offset, length, stream);
/* 1744 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1754 */     storeLongs(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, File file)
/*      */     throws IOException
/*      */   {
/* 1762 */     storeLongs(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1770 */     storeLongs(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(LongIterator i, PrintStream stream)
/*      */   {
/* 1778 */     while (i.hasNext()) stream.println(i.nextLong());
/*      */   }
/*      */ 
/*      */   public static void storeLongs(LongIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 1786 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1787 */     storeLongs(i, stream);
/* 1788 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(LongIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1796 */     storeLongs(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadLongs(BufferedReader reader, long[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1807 */     LongBigArrays.ensureOffsetLength(array, offset, length);
/* 1808 */     long c = 0L;
/*      */     try
/*      */     {
/* 1811 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1812 */         long[] t = array[i];
/* 1813 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1814 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/* 1815 */           String s;
/* 1815 */           if ((s = reader.readLine()) != null) t[d] = Long.parseLong(s); else
/* 1816 */             return c;
/* 1817 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1822 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadLongs(BufferedReader reader, long[][] array)
/*      */     throws IOException
/*      */   {
/* 1831 */     return loadLongs(reader, array, 0L, LongBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadLongs(File file, long[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1842 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1843 */     long result = loadLongs(reader, array, offset, length);
/* 1844 */     reader.close();
/* 1845 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadLongs(CharSequence filename, long[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1856 */     return loadLongs(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadLongs(File file, long[][] array)
/*      */     throws IOException
/*      */   {
/* 1865 */     return loadLongs(file, array, 0L, LongBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadLongs(CharSequence filename, long[][] array)
/*      */     throws IOException
/*      */   {
/* 1874 */     return loadLongs(filename, array, 0L, LongBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, long offset, long length, PrintStream stream)
/*      */   {
/* 1884 */     LongBigArrays.ensureOffsetLength(array, offset, length);
/* 1885 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1886 */       long[] t = array[i];
/* 1887 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1888 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, PrintStream stream)
/*      */   {
/* 1897 */     storeLongs(array, 0L, LongBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 1907 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1908 */     storeLongs(array, offset, length, stream);
/* 1909 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1919 */     storeLongs(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 1927 */     storeLongs(array, 0L, LongBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1935 */     storeLongs(array, 0L, LongBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static LongIterator asLongIterator(BufferedReader reader)
/*      */   {
/* 1969 */     return new LongReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static LongIterator asLongIterator(File file)
/*      */     throws IOException
/*      */   {
/* 1976 */     return new LongReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static LongIterator asLongIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1983 */     return asLongIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadFloats(BufferedReader reader, float[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2031 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 2032 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/* 2035 */       for (i = 0; (i < length) && 
/* 2036 */         ((s = reader.readLine()) != null); i++)
/* 2036 */         array[(i + offset)] = Float.parseFloat(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/* 2040 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadFloats(BufferedReader reader, float[] array)
/*      */     throws IOException
/*      */   {
/* 2049 */     return loadFloats(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadFloats(File file, float[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2060 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2061 */     int result = loadFloats(reader, array, offset, length);
/* 2062 */     reader.close();
/* 2063 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadFloats(CharSequence filename, float[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2074 */     return loadFloats(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadFloats(File file, float[] array)
/*      */     throws IOException
/*      */   {
/* 2083 */     return loadFloats(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadFloats(CharSequence filename, float[] array)
/*      */     throws IOException
/*      */   {
/* 2092 */     return loadFloats(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, int offset, int length, PrintStream stream)
/*      */   {
/* 2102 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 2103 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, PrintStream stream)
/*      */   {
/* 2111 */     storeFloats(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 2121 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2122 */     storeFloats(array, offset, length, stream);
/* 2123 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2133 */     storeFloats(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, File file)
/*      */     throws IOException
/*      */   {
/* 2141 */     storeFloats(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2149 */     storeFloats(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(FloatIterator i, PrintStream stream)
/*      */   {
/* 2157 */     while (i.hasNext()) stream.println(i.nextFloat());
/*      */   }
/*      */ 
/*      */   public static void storeFloats(FloatIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 2165 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2166 */     storeFloats(i, stream);
/* 2167 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(FloatIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2175 */     storeFloats(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadFloats(BufferedReader reader, float[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2186 */     FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 2187 */     long c = 0L;
/*      */     try
/*      */     {
/* 2190 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2191 */         float[] t = array[i];
/* 2192 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2193 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/* 2194 */           String s;
/* 2194 */           if ((s = reader.readLine()) != null) t[d] = Float.parseFloat(s); else
/* 2195 */             return c;
/* 2196 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2201 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadFloats(BufferedReader reader, float[][] array)
/*      */     throws IOException
/*      */   {
/* 2210 */     return loadFloats(reader, array, 0L, FloatBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadFloats(File file, float[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2221 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2222 */     long result = loadFloats(reader, array, offset, length);
/* 2223 */     reader.close();
/* 2224 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadFloats(CharSequence filename, float[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2235 */     return loadFloats(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadFloats(File file, float[][] array)
/*      */     throws IOException
/*      */   {
/* 2244 */     return loadFloats(file, array, 0L, FloatBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadFloats(CharSequence filename, float[][] array)
/*      */     throws IOException
/*      */   {
/* 2253 */     return loadFloats(filename, array, 0L, FloatBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, long offset, long length, PrintStream stream)
/*      */   {
/* 2263 */     FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 2264 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2265 */       float[] t = array[i];
/* 2266 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2267 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, PrintStream stream)
/*      */   {
/* 2276 */     storeFloats(array, 0L, FloatBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 2286 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2287 */     storeFloats(array, offset, length, stream);
/* 2288 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2298 */     storeFloats(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 2306 */     storeFloats(array, 0L, FloatBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2314 */     storeFloats(array, 0L, FloatBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static FloatIterator asFloatIterator(BufferedReader reader)
/*      */   {
/* 2348 */     return new FloatReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static FloatIterator asFloatIterator(File file)
/*      */     throws IOException
/*      */   {
/* 2355 */     return new FloatReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static FloatIterator asFloatIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2362 */     return asFloatIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(BufferedReader reader, double[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2410 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 2411 */     int i = 0;
/*      */     try
/*      */     {
/*      */       String s;
/* 2414 */       for (i = 0; (i < length) && 
/* 2415 */         ((s = reader.readLine()) != null); i++)
/* 2415 */         array[(i + offset)] = Double.parseDouble(s);
/*      */     }
/*      */     catch (EOFException itsOk) {
/*      */     }
/* 2419 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(BufferedReader reader, double[] array)
/*      */     throws IOException
/*      */   {
/* 2428 */     return loadDoubles(reader, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(File file, double[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2439 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2440 */     int result = loadDoubles(reader, array, offset, length);
/* 2441 */     reader.close();
/* 2442 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(CharSequence filename, double[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2453 */     return loadDoubles(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(File file, double[] array)
/*      */     throws IOException
/*      */   {
/* 2462 */     return loadDoubles(file, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(CharSequence filename, double[] array)
/*      */     throws IOException
/*      */   {
/* 2471 */     return loadDoubles(filename, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, int offset, int length, PrintStream stream)
/*      */   {
/* 2481 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 2482 */     for (int i = 0; i < length; i++) stream.println(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, PrintStream stream)
/*      */   {
/* 2490 */     storeDoubles(array, 0, array.length, stream);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 2500 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2501 */     storeDoubles(array, offset, length, stream);
/* 2502 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2512 */     storeDoubles(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, File file)
/*      */     throws IOException
/*      */   {
/* 2520 */     storeDoubles(array, 0, array.length, file);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2528 */     storeDoubles(array, 0, array.length, filename);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(DoubleIterator i, PrintStream stream)
/*      */   {
/* 2536 */     while (i.hasNext()) stream.println(i.nextDouble());
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(DoubleIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 2544 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2545 */     storeDoubles(i, stream);
/* 2546 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(DoubleIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2554 */     storeDoubles(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(BufferedReader reader, double[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2565 */     DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 2566 */     long c = 0L;
/*      */     try
/*      */     {
/* 2569 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2570 */         double[] t = array[i];
/* 2571 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2572 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++)
/*      */         {
/* 2573 */           String s;
/* 2573 */           if ((s = reader.readLine()) != null) t[d] = Double.parseDouble(s); else
/* 2574 */             return c;
/* 2575 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2580 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(BufferedReader reader, double[][] array)
/*      */     throws IOException
/*      */   {
/* 2589 */     return loadDoubles(reader, array, 0L, DoubleBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(File file, double[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2600 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2601 */     long result = loadDoubles(reader, array, offset, length);
/* 2602 */     reader.close();
/* 2603 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(CharSequence filename, double[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2614 */     return loadDoubles(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(File file, double[][] array)
/*      */     throws IOException
/*      */   {
/* 2623 */     return loadDoubles(file, array, 0L, DoubleBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(CharSequence filename, double[][] array)
/*      */     throws IOException
/*      */   {
/* 2632 */     return loadDoubles(filename, array, 0L, DoubleBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, long offset, long length, PrintStream stream)
/*      */   {
/* 2642 */     DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 2643 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2644 */       double[] t = array[i];
/* 2645 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2646 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) stream.println(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, PrintStream stream)
/*      */   {
/* 2655 */     storeDoubles(array, 0L, DoubleBigArrays.length(array), stream);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 2665 */     PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2666 */     storeDoubles(array, offset, length, stream);
/* 2667 */     stream.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2677 */     storeDoubles(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 2685 */     storeDoubles(array, 0L, DoubleBigArrays.length(array), file);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2693 */     storeDoubles(array, 0L, DoubleBigArrays.length(array), filename);
/*      */   }
/*      */ 
/*      */   public static DoubleIterator asDoubleIterator(BufferedReader reader)
/*      */   {
/* 2727 */     return new DoubleReaderWrapper(reader);
/*      */   }
/*      */ 
/*      */   public static DoubleIterator asDoubleIterator(File file)
/*      */     throws IOException
/*      */   {
/* 2734 */     return new DoubleReaderWrapper(new BufferedReader(new FileReader(file)));
/*      */   }
/*      */ 
/*      */   public static DoubleIterator asDoubleIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2741 */     return asDoubleIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   private static final class DoubleReaderWrapper extends AbstractDoubleIterator
/*      */   {
/*      */     private final BufferedReader reader;
/* 2698 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private double next;
/*      */ 
/*      */     public DoubleReaderWrapper(BufferedReader reader)
/*      */     {
/* 2702 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/* 2705 */       if (!this.toAdvance) return this.s != null;
/* 2706 */       this.toAdvance = false;
/*      */       try {
/* 2708 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/* 2711 */         throw new RuntimeException(rethrow);
/* 2712 */       }if (this.s == null) return false;
/* 2713 */       this.next = Double.parseDouble(this.s);
/* 2714 */       return true;
/*      */     }
/*      */     public double nextDouble() {
/* 2717 */       if (!hasNext()) throw new NoSuchElementException();
/* 2718 */       this.toAdvance = true;
/* 2719 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class FloatReaderWrapper extends AbstractFloatIterator
/*      */   {
/*      */     private final BufferedReader reader;
/* 2319 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private float next;
/*      */ 
/*      */     public FloatReaderWrapper(BufferedReader reader)
/*      */     {
/* 2323 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/* 2326 */       if (!this.toAdvance) return this.s != null;
/* 2327 */       this.toAdvance = false;
/*      */       try {
/* 2329 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/* 2332 */         throw new RuntimeException(rethrow);
/* 2333 */       }if (this.s == null) return false;
/* 2334 */       this.next = Float.parseFloat(this.s);
/* 2335 */       return true;
/*      */     }
/*      */     public float nextFloat() {
/* 2338 */       if (!hasNext()) throw new NoSuchElementException();
/* 2339 */       this.toAdvance = true;
/* 2340 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class LongReaderWrapper extends AbstractLongIterator
/*      */   {
/*      */     private final BufferedReader reader;
/* 1940 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private long next;
/*      */ 
/*      */     public LongReaderWrapper(BufferedReader reader)
/*      */     {
/* 1944 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/* 1947 */       if (!this.toAdvance) return this.s != null;
/* 1948 */       this.toAdvance = false;
/*      */       try {
/* 1950 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/* 1953 */         throw new RuntimeException(rethrow);
/* 1954 */       }if (this.s == null) return false;
/* 1955 */       this.next = Long.parseLong(this.s);
/* 1956 */       return true;
/*      */     }
/*      */     public long nextLong() {
/* 1959 */       if (!hasNext()) throw new NoSuchElementException();
/* 1960 */       this.toAdvance = true;
/* 1961 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class IntReaderWrapper extends AbstractIntIterator
/*      */   {
/*      */     private final BufferedReader reader;
/* 1561 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private int next;
/*      */ 
/*      */     public IntReaderWrapper(BufferedReader reader)
/*      */     {
/* 1565 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/* 1568 */       if (!this.toAdvance) return this.s != null;
/* 1569 */       this.toAdvance = false;
/*      */       try {
/* 1571 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/* 1574 */         throw new RuntimeException(rethrow);
/* 1575 */       }if (this.s == null) return false;
/* 1576 */       this.next = Integer.parseInt(this.s);
/* 1577 */       return true;
/*      */     }
/*      */     public int nextInt() {
/* 1580 */       if (!hasNext()) throw new NoSuchElementException();
/* 1581 */       this.toAdvance = true;
/* 1582 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class ShortReaderWrapper extends AbstractShortIterator
/*      */   {
/*      */     private final BufferedReader reader;
/* 1182 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private short next;
/*      */ 
/*      */     public ShortReaderWrapper(BufferedReader reader)
/*      */     {
/* 1186 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/* 1189 */       if (!this.toAdvance) return this.s != null;
/* 1190 */       this.toAdvance = false;
/*      */       try {
/* 1192 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/* 1195 */         throw new RuntimeException(rethrow);
/* 1196 */       }if (this.s == null) return false;
/* 1197 */       this.next = Short.parseShort(this.s);
/* 1198 */       return true;
/*      */     }
/*      */     public short nextShort() {
/* 1201 */       if (!hasNext()) throw new NoSuchElementException();
/* 1202 */       this.toAdvance = true;
/* 1203 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class ByteReaderWrapper extends AbstractByteIterator
/*      */   {
/*      */     private final BufferedReader reader;
/*  803 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private byte next;
/*      */ 
/*      */     public ByteReaderWrapper(BufferedReader reader)
/*      */     {
/*  807 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/*  810 */       if (!this.toAdvance) return this.s != null;
/*  811 */       this.toAdvance = false;
/*      */       try {
/*  813 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/*  816 */         throw new RuntimeException(rethrow);
/*  817 */       }if (this.s == null) return false;
/*  818 */       this.next = Byte.parseByte(this.s);
/*  819 */       return true;
/*      */     }
/*      */     public byte nextByte() {
/*  822 */       if (!hasNext()) throw new NoSuchElementException();
/*  823 */       this.toAdvance = true;
/*  824 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class BooleanReaderWrapper extends AbstractBooleanIterator
/*      */   {
/*      */     private final BufferedReader reader;
/*  424 */     private boolean toAdvance = true;
/*      */     private String s;
/*      */     private boolean next;
/*      */ 
/*      */     public BooleanReaderWrapper(BufferedReader reader)
/*      */     {
/*  428 */       this.reader = reader;
/*      */     }
/*      */     public boolean hasNext() {
/*  431 */       if (!this.toAdvance) return this.s != null;
/*  432 */       this.toAdvance = false;
/*      */       try {
/*  434 */         this.s = this.reader.readLine();
/*      */       } catch (EOFException itsOk) {
/*      */       } catch (IOException rethrow) {
/*  437 */         throw new RuntimeException(rethrow);
/*  438 */       }if (this.s == null) return false;
/*  439 */       this.next = Boolean.parseBoolean(this.s);
/*  440 */       return true;
/*      */     }
/*      */     public boolean nextBoolean() {
/*  443 */       if (!hasNext()) throw new NoSuchElementException();
/*  444 */       this.toAdvance = true;
/*  445 */       return this.next;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.TextIO
 * JD-Core Version:    0.6.2
 */