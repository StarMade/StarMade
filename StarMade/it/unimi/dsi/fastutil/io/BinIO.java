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
/*      */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*      */ import it.unimi.dsi.fastutil.chars.CharArrays;
/*      */ import it.unimi.dsi.fastutil.chars.CharBigArrays;
/*      */ import it.unimi.dsi.fastutil.chars.CharIterator;
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
/*      */ import java.io.DataInput;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutput;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.channels.FileChannel;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class BinIO
/*      */ {
/*      */   private static final int MAX_IO_LENGTH = 1048576;
/*      */ 
/*      */   public static void storeObject(Object o, File file)
/*      */     throws IOException
/*      */   {
/*  104 */     ObjectOutputStream oos = new ObjectOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  105 */     oos.writeObject(o);
/*  106 */     oos.close();
/*      */   }
/*      */ 
/*      */   public static void storeObject(Object o, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  115 */     storeObject(o, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static Object loadObject(File file)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  124 */     ObjectInputStream ois = new ObjectInputStream(new FastBufferedInputStream(new FileInputStream(file)));
/*  125 */     Object result = ois.readObject();
/*  126 */     ois.close();
/*  127 */     return result;
/*      */   }
/*      */ 
/*      */   public static Object loadObject(CharSequence filename)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  136 */     return loadObject(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeObject(Object o, OutputStream s)
/*      */     throws IOException
/*      */   {
/*  148 */     ObjectOutputStream oos = new ObjectOutputStream(new FastBufferedOutputStream(s));
/*  149 */     oos.writeObject(o);
/*  150 */     oos.flush();
/*      */   }
/*      */ 
/*      */   public static Object loadObject(InputStream s)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  165 */     ObjectInputStream ois = new ObjectInputStream(new FastBufferedInputStream(s));
/*  166 */     Object result = ois.readObject();
/*  167 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(DataInput dataInput, boolean[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  218 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/*  219 */     int i = 0;
/*      */     try {
/*  221 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readBoolean(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/*  224 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(DataInput dataInput, boolean[] array)
/*      */     throws IOException
/*      */   {
/*  233 */     int i = 0;
/*      */     try {
/*  235 */       int length = array.length;
/*  236 */       for (i = 0; i < length; i++) array[i] = dataInput.readBoolean(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/*  239 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(File file, boolean[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  250 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/*  251 */     FileInputStream fis = new FileInputStream(file);
/*  252 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  253 */     int i = 0;
/*      */     try {
/*  255 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readBoolean(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/*  258 */     dis.close();
/*  259 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(CharSequence filename, boolean[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  270 */     return loadBooleans(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(File file, boolean[] array)
/*      */     throws IOException
/*      */   {
/*  279 */     FileInputStream fis = new FileInputStream(file);
/*  280 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  281 */     int i = 0;
/*      */     try {
/*  283 */       int length = array.length;
/*  284 */       for (i = 0; i < length; i++) array[i] = dis.readBoolean(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/*  287 */     dis.close();
/*  288 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBooleans(CharSequence filename, boolean[] array)
/*      */     throws IOException
/*      */   {
/*  297 */     return loadBooleans(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static boolean[] loadBooleans(File file)
/*      */     throws IOException
/*      */   {
/*  309 */     FileInputStream fis = new FileInputStream(file);
/*  310 */     long length = fis.getChannel().size();
/*  311 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/*  312 */     boolean[] array = new boolean[(int)length];
/*  313 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  314 */     for (int i = 0; i < length; i++) array[i] = dis.readBoolean();
/*  315 */     dis.close();
/*  316 */     return array;
/*      */   }
/*      */ 
/*      */   public static boolean[] loadBooleans(CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  328 */     return loadBooleans(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/*  338 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/*  339 */     for (int i = 0; i < length; i++) dataOutput.writeBoolean(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/*  347 */     int length = array.length;
/*  348 */     for (int i = 0; i < length; i++) dataOutput.writeBoolean(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/*  358 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/*  359 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  360 */     for (int i = 0; i < length; i++) dos.writeBoolean(array[(offset + i)]);
/*  361 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  371 */     storeBooleans(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, File file)
/*      */     throws IOException
/*      */   {
/*  379 */     int length = array.length;
/*  380 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  381 */     for (int i = 0; i < length; i++) dos.writeBoolean(array[i]);
/*  382 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  390 */     storeBooleans(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(DataInput dataInput, boolean[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  401 */     BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  402 */     long c = 0L;
/*      */     try {
/*  404 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  405 */         boolean[] t = array[i];
/*  406 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  407 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/*  408 */           t[d] = dataInput.readBoolean();
/*  409 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/*  414 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(DataInput dataInput, boolean[][] array)
/*      */     throws IOException
/*      */   {
/*  423 */     long c = 0L;
/*      */     try {
/*  425 */       for (int i = 0; i < array.length; i++) {
/*  426 */         boolean[] t = array[i];
/*  427 */         int l = t.length;
/*  428 */         for (int d = 0; d < l; d++) {
/*  429 */           t[d] = dataInput.readBoolean();
/*  430 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/*  435 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(File file, boolean[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  446 */     BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  447 */     FileInputStream fis = new FileInputStream(file);
/*  448 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  449 */     long c = 0L;
/*      */     try {
/*  451 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  452 */         boolean[] t = array[i];
/*  453 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  454 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/*  455 */           t[d] = dis.readBoolean();
/*  456 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/*  461 */     dis.close();
/*  462 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(CharSequence filename, boolean[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  473 */     return loadBooleans(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(File file, boolean[][] array)
/*      */     throws IOException
/*      */   {
/*  482 */     FileInputStream fis = new FileInputStream(file);
/*  483 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  484 */     long c = 0L;
/*      */     try {
/*  486 */       for (int i = 0; i < array.length; i++) {
/*  487 */         boolean[] t = array[i];
/*  488 */         int l = t.length;
/*  489 */         for (int d = 0; d < l; d++) {
/*  490 */           t[d] = dis.readBoolean();
/*  491 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/*  496 */     dis.close();
/*  497 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBooleans(CharSequence filename, boolean[][] array)
/*      */     throws IOException
/*      */   {
/*  506 */     return loadBooleans(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static boolean[][] loadBooleansBig(File file)
/*      */     throws IOException
/*      */   {
/*  518 */     FileInputStream fis = new FileInputStream(file);
/*  519 */     long length = fis.getChannel().size();
/*  520 */     boolean[][] array = BooleanBigArrays.newBigArray(length);
/*  521 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  522 */     for (int i = 0; i < array.length; i++) {
/*  523 */       boolean[] t = array[i];
/*  524 */       int l = t.length;
/*  525 */       for (int d = 0; d < l; d++) t[d] = dis.readBoolean();
/*      */     }
/*  527 */     dis.close();
/*  528 */     return array;
/*      */   }
/*      */ 
/*      */   public static boolean[][] loadBooleansBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  540 */     return loadBooleansBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/*  550 */     BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  551 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  552 */       boolean[] t = array[i];
/*  553 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  554 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeBoolean(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/*  563 */     for (int i = 0; i < array.length; i++) {
/*  564 */       boolean[] t = array[i];
/*  565 */       int l = t.length;
/*  566 */       for (int d = 0; d < l; d++) dataOutput.writeBoolean(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/*  577 */     BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  578 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  579 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  580 */       boolean[] t = array[i];
/*  581 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  582 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeBoolean(t[d]);
/*      */     }
/*  584 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  594 */     storeBooleans(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, File file)
/*      */     throws IOException
/*      */   {
/*  602 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  603 */     for (int i = 0; i < array.length; i++) {
/*  604 */       boolean[] t = array[i];
/*  605 */       int l = t.length;
/*  606 */       for (int d = 0; d < l; d++) dos.writeBoolean(t[d]);
/*      */     }
/*  608 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(boolean[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  616 */     storeBooleans(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(BooleanIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/*  624 */     while (i.hasNext()) dataOutput.writeBoolean(i.nextBoolean());
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(BooleanIterator i, File file)
/*      */     throws IOException
/*      */   {
/*  632 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  633 */     while (i.hasNext()) dos.writeBoolean(i.nextBoolean());
/*  634 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeBooleans(BooleanIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  642 */     storeBooleans(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static BooleanIterator asBooleanIterator(DataInput dataInput)
/*      */   {
/*  676 */     return new BooleanDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static BooleanIterator asBooleanIterator(File file)
/*      */     throws IOException
/*      */   {
/*  683 */     return new BooleanDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static BooleanIterator asBooleanIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/*  690 */     return asBooleanIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   private static int read(InputStream is, byte[] a, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  732 */     if (length == 0) return 0;
/*  733 */     int read = 0;
/*      */     do {
/*  735 */       int result = is.read(a, offset + read, Math.min(length - read, 1048576));
/*  736 */       if (result < 0) return read;
/*  737 */       read += result;
/*  738 */     }while (read < length);
/*  739 */     return read;
/*      */   }
/*      */   private static void write(OutputStream outputStream, byte[] a, int offset, int length) throws IOException {
/*  742 */     int written = 0;
/*  743 */     while (written < length) {
/*  744 */       outputStream.write(a, offset + written, Math.min(length - written, 1048576));
/*  745 */       written += Math.min(length - written, 1048576);
/*      */     }
/*      */   }
/*      */ 
/*  749 */   private static void write(DataOutput dataOutput, byte[] a, int offset, int length) throws IOException { int written = 0;
/*  750 */     while (written < length) {
/*  751 */       dataOutput.write(a, offset + written, Math.min(length - written, 1048576));
/*  752 */       written += Math.min(length - written, 1048576);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static int loadBytes(InputStream inputStream, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  768 */     return read(inputStream, array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadBytes(InputStream inputStream, byte[] array)
/*      */     throws IOException
/*      */   {
/*  780 */     return read(inputStream, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, OutputStream outputStream)
/*      */     throws IOException
/*      */   {
/*  793 */     write(outputStream, array, offset, length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, OutputStream outputStream)
/*      */     throws IOException
/*      */   {
/*  804 */     write(outputStream, array, 0, array.length);
/*      */   }
/*      */   private static long read(InputStream is, byte[][] a, long offset, long length) throws IOException {
/*  807 */     if (length == 0L) return 0L;
/*  808 */     long read = 0L;
/*  809 */     int segment = BigArrays.segment(offset);
/*  810 */     int displacement = BigArrays.displacement(offset);
/*      */     do
/*      */     {
/*  813 */       int result = is.read(a[segment], displacement, (int)Math.min(a[segment].length - displacement, Math.min(length - read, 1048576L)));
/*  814 */       if (result < 0) return read;
/*  815 */       read += result;
/*  816 */       displacement += result;
/*  817 */       if (displacement == a[segment].length) {
/*  818 */         segment++;
/*  819 */         displacement = 0;
/*      */       }
/*      */     }
/*  821 */     while (read < length);
/*  822 */     return read;
/*      */   }
/*      */   private static void write(OutputStream outputStream, byte[][] a, long offset, long length) throws IOException {
/*  825 */     if (length == 0L) return;
/*  826 */     long written = 0L;
/*      */ 
/*  828 */     int segment = BigArrays.segment(offset);
/*  829 */     int displacement = BigArrays.displacement(offset);
/*      */     do {
/*  831 */       int toWrite = (int)Math.min(a[segment].length - displacement, Math.min(length - written, 1048576L));
/*  832 */       outputStream.write(a[segment], displacement, toWrite);
/*  833 */       written += toWrite;
/*  834 */       displacement += toWrite;
/*  835 */       if (displacement == a[segment].length) {
/*  836 */         segment++;
/*  837 */         displacement = 0;
/*      */       }
/*      */     }
/*  839 */     while (written < length);
/*      */   }
/*      */   private static void write(DataOutput dataOutput, byte[][] a, long offset, long length) throws IOException {
/*  842 */     if (length == 0L) return;
/*  843 */     long written = 0L;
/*      */ 
/*  845 */     int segment = BigArrays.segment(offset);
/*  846 */     int displacement = BigArrays.displacement(offset);
/*      */     do {
/*  848 */       int toWrite = (int)Math.min(a[segment].length - displacement, Math.min(length - written, 1048576L));
/*  849 */       dataOutput.write(a[segment], displacement, toWrite);
/*  850 */       written += toWrite;
/*  851 */       displacement += toWrite;
/*  852 */       if (displacement == a[segment].length) {
/*  853 */         segment++;
/*  854 */         displacement = 0;
/*      */       }
/*      */     }
/*  856 */     while (written < length);
/*      */   }
/*      */ 
/*      */   public static long loadBytes(InputStream inputStream, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/*  871 */     return read(inputStream, array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadBytes(InputStream inputStream, byte[][] array)
/*      */     throws IOException
/*      */   {
/*  883 */     return read(inputStream, array, 0L, ByteBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, OutputStream outputStream)
/*      */     throws IOException
/*      */   {
/*  896 */     write(outputStream, array, offset, length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, OutputStream outputStream)
/*      */     throws IOException
/*      */   {
/*  907 */     write(outputStream, array, 0L, ByteBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static int loadBytes(DataInput dataInput, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  918 */     ByteArrays.ensureOffsetLength(array, offset, length);
/*  919 */     int i = 0;
/*      */     try {
/*  921 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readByte(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/*  924 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBytes(DataInput dataInput, byte[] array)
/*      */     throws IOException
/*      */   {
/*  933 */     int i = 0;
/*      */     try {
/*  935 */       int length = array.length;
/*  936 */       for (i = 0; i < length; i++) array[i] = dataInput.readByte(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/*  939 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadBytes(File file, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  950 */     ByteArrays.ensureOffsetLength(array, offset, length);
/*  951 */     FileInputStream fis = new FileInputStream(file);
/*  952 */     int result = read(fis, array, offset, length);
/*  953 */     fis.close();
/*  954 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadBytes(CharSequence filename, byte[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/*  965 */     return loadBytes(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadBytes(File file, byte[] array)
/*      */     throws IOException
/*      */   {
/*  974 */     FileInputStream fis = new FileInputStream(file);
/*  975 */     int result = read(fis, array, 0, array.length);
/*  976 */     fis.close();
/*  977 */     return result;
/*      */   }
/*      */ 
/*      */   public static int loadBytes(CharSequence filename, byte[] array)
/*      */     throws IOException
/*      */   {
/*  986 */     return loadBytes(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static byte[] loadBytes(File file)
/*      */     throws IOException
/*      */   {
/*  998 */     FileInputStream fis = new FileInputStream(file);
/*  999 */     long length = fis.getChannel().size() / 1L;
/* 1000 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 1001 */     byte[] array = new byte[(int)length];
/* 1002 */     if (read(fis, array, 0, (int)length) < length) throw new EOFException();
/* 1003 */     fis.close();
/* 1004 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[] loadBytes(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1016 */     return loadBytes(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1026 */     ByteArrays.ensureOffsetLength(array, offset, length);
/* 1027 */     write(dataOutput, array, offset, length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1035 */     write(dataOutput, array, 0, array.length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 1045 */     ByteArrays.ensureOffsetLength(array, offset, length);
/* 1046 */     OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1047 */     write(os, array, offset, length);
/* 1048 */     os.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1058 */     storeBytes(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, File file)
/*      */     throws IOException
/*      */   {
/* 1066 */     OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1067 */     write(os, array, 0, array.length);
/* 1068 */     os.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1076 */     storeBytes(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadBytes(DataInput dataInput, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1087 */     ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1088 */     long c = 0L;
/*      */     try {
/* 1090 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1091 */         byte[] t = array[i];
/* 1092 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1093 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 1094 */           t[d] = dataInput.readByte();
/* 1095 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1100 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBytes(DataInput dataInput, byte[][] array)
/*      */     throws IOException
/*      */   {
/* 1109 */     long c = 0L;
/*      */     try {
/* 1111 */       for (int i = 0; i < array.length; i++) {
/* 1112 */         byte[] t = array[i];
/* 1113 */         int l = t.length;
/* 1114 */         for (int d = 0; d < l; d++) {
/* 1115 */           t[d] = dataInput.readByte();
/* 1116 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1121 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadBytes(File file, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1132 */     ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1133 */     FileInputStream fis = new FileInputStream(file);
/* 1134 */     long result = read(fis, array, offset, length);
/* 1135 */     fis.close();
/* 1136 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadBytes(CharSequence filename, byte[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1147 */     return loadBytes(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadBytes(File file, byte[][] array)
/*      */     throws IOException
/*      */   {
/* 1156 */     FileInputStream fis = new FileInputStream(file);
/* 1157 */     long result = read(fis, array, 0L, ByteBigArrays.length(array));
/* 1158 */     fis.close();
/* 1159 */     return result;
/*      */   }
/*      */ 
/*      */   public static long loadBytes(CharSequence filename, byte[][] array)
/*      */     throws IOException
/*      */   {
/* 1168 */     return loadBytes(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static byte[][] loadBytesBig(File file)
/*      */     throws IOException
/*      */   {
/* 1180 */     FileInputStream fis = new FileInputStream(file);
/* 1181 */     long length = fis.getChannel().size() / 1L;
/* 1182 */     byte[][] array = ByteBigArrays.newBigArray(length);
/* 1183 */     if (read(fis, array, 0L, length) < length) throw new EOFException();
/* 1184 */     fis.close();
/* 1185 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[][] loadBytesBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1197 */     return loadBytesBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1207 */     ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1208 */     write(dataOutput, array, offset, length);
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1216 */     write(dataOutput, array, 0L, ByteBigArrays.length(array));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 1226 */     ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1227 */     OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1228 */     write(os, array, offset, length);
/* 1229 */     os.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1239 */     storeBytes(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 1247 */     OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1248 */     write(os, array, 0L, ByteBigArrays.length(array));
/* 1249 */     os.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(byte[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1257 */     storeBytes(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeBytes(ByteIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1265 */     while (i.hasNext()) dataOutput.writeByte(i.nextByte());
/*      */   }
/*      */ 
/*      */   public static void storeBytes(ByteIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 1273 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1274 */     while (i.hasNext()) dos.writeByte(i.nextByte());
/* 1275 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeBytes(ByteIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1283 */     storeBytes(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static ByteIterator asByteIterator(DataInput dataInput)
/*      */   {
/* 1317 */     return new ByteDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static ByteIterator asByteIterator(File file)
/*      */     throws IOException
/*      */   {
/* 1324 */     return new ByteDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static ByteIterator asByteIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1331 */     return asByteIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadShorts(DataInput dataInput, short[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1379 */     ShortArrays.ensureOffsetLength(array, offset, length);
/* 1380 */     int i = 0;
/*      */     try {
/* 1382 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readShort(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1385 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadShorts(DataInput dataInput, short[] array)
/*      */     throws IOException
/*      */   {
/* 1394 */     int i = 0;
/*      */     try {
/* 1396 */       int length = array.length;
/* 1397 */       for (i = 0; i < length; i++) array[i] = dataInput.readShort(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1400 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadShorts(File file, short[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1411 */     ShortArrays.ensureOffsetLength(array, offset, length);
/* 1412 */     FileInputStream fis = new FileInputStream(file);
/* 1413 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1414 */     int i = 0;
/*      */     try {
/* 1416 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readShort(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1419 */     dis.close();
/* 1420 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadShorts(CharSequence filename, short[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1431 */     return loadShorts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadShorts(File file, short[] array)
/*      */     throws IOException
/*      */   {
/* 1440 */     FileInputStream fis = new FileInputStream(file);
/* 1441 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1442 */     int i = 0;
/*      */     try {
/* 1444 */       int length = array.length;
/* 1445 */       for (i = 0; i < length; i++) array[i] = dis.readShort(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1448 */     dis.close();
/* 1449 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadShorts(CharSequence filename, short[] array)
/*      */     throws IOException
/*      */   {
/* 1458 */     return loadShorts(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static short[] loadShorts(File file)
/*      */     throws IOException
/*      */   {
/* 1470 */     FileInputStream fis = new FileInputStream(file);
/* 1471 */     long length = fis.getChannel().size() / 2L;
/* 1472 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 1473 */     short[] array = new short[(int)length];
/* 1474 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1475 */     for (int i = 0; i < length; i++) array[i] = dis.readShort();
/* 1476 */     dis.close();
/* 1477 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[] loadShorts(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1489 */     return loadShorts(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1499 */     ShortArrays.ensureOffsetLength(array, offset, length);
/* 1500 */     for (int i = 0; i < length; i++) dataOutput.writeShort(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1508 */     int length = array.length;
/* 1509 */     for (int i = 0; i < length; i++) dataOutput.writeShort(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 1519 */     ShortArrays.ensureOffsetLength(array, offset, length);
/* 1520 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1521 */     for (int i = 0; i < length; i++) dos.writeShort(array[(offset + i)]);
/* 1522 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1532 */     storeShorts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, File file)
/*      */     throws IOException
/*      */   {
/* 1540 */     int length = array.length;
/* 1541 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1542 */     for (int i = 0; i < length; i++) dos.writeShort(array[i]);
/* 1543 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1551 */     storeShorts(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadShorts(DataInput dataInput, short[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1562 */     ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1563 */     long c = 0L;
/*      */     try {
/* 1565 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1566 */         short[] t = array[i];
/* 1567 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1568 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 1569 */           t[d] = dataInput.readShort();
/* 1570 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1575 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadShorts(DataInput dataInput, short[][] array)
/*      */     throws IOException
/*      */   {
/* 1584 */     long c = 0L;
/*      */     try {
/* 1586 */       for (int i = 0; i < array.length; i++) {
/* 1587 */         short[] t = array[i];
/* 1588 */         int l = t.length;
/* 1589 */         for (int d = 0; d < l; d++) {
/* 1590 */           t[d] = dataInput.readShort();
/* 1591 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1596 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadShorts(File file, short[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1607 */     ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1608 */     FileInputStream fis = new FileInputStream(file);
/* 1609 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1610 */     long c = 0L;
/*      */     try {
/* 1612 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1613 */         short[] t = array[i];
/* 1614 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1615 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 1616 */           t[d] = dis.readShort();
/* 1617 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1622 */     dis.close();
/* 1623 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadShorts(CharSequence filename, short[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 1634 */     return loadShorts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadShorts(File file, short[][] array)
/*      */     throws IOException
/*      */   {
/* 1643 */     FileInputStream fis = new FileInputStream(file);
/* 1644 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1645 */     long c = 0L;
/*      */     try {
/* 1647 */       for (int i = 0; i < array.length; i++) {
/* 1648 */         short[] t = array[i];
/* 1649 */         int l = t.length;
/* 1650 */         for (int d = 0; d < l; d++) {
/* 1651 */           t[d] = dis.readShort();
/* 1652 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 1657 */     dis.close();
/* 1658 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadShorts(CharSequence filename, short[][] array)
/*      */     throws IOException
/*      */   {
/* 1667 */     return loadShorts(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static short[][] loadShortsBig(File file)
/*      */     throws IOException
/*      */   {
/* 1679 */     FileInputStream fis = new FileInputStream(file);
/* 1680 */     long length = fis.getChannel().size() / 2L;
/* 1681 */     short[][] array = ShortBigArrays.newBigArray(length);
/* 1682 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1683 */     for (int i = 0; i < array.length; i++) {
/* 1684 */       short[] t = array[i];
/* 1685 */       int l = t.length;
/* 1686 */       for (int d = 0; d < l; d++) t[d] = dis.readShort();
/*      */     }
/* 1688 */     dis.close();
/* 1689 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[][] loadShortsBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1701 */     return loadShortsBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1711 */     ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1712 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1713 */       short[] t = array[i];
/* 1714 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1715 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeShort(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1724 */     for (int i = 0; i < array.length; i++) {
/* 1725 */       short[] t = array[i];
/* 1726 */       int l = t.length;
/* 1727 */       for (int d = 0; d < l; d++) dataOutput.writeShort(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 1738 */     ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1739 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1740 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1741 */       short[] t = array[i];
/* 1742 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1743 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeShort(t[d]);
/*      */     }
/* 1745 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1755 */     storeShorts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 1763 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1764 */     for (int i = 0; i < array.length; i++) {
/* 1765 */       short[] t = array[i];
/* 1766 */       int l = t.length;
/* 1767 */       for (int d = 0; d < l; d++) dos.writeShort(t[d]);
/*      */     }
/* 1769 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(short[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1777 */     storeShorts(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeShorts(ShortIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 1785 */     while (i.hasNext()) dataOutput.writeShort(i.nextShort());
/*      */   }
/*      */ 
/*      */   public static void storeShorts(ShortIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 1793 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1794 */     while (i.hasNext()) dos.writeShort(i.nextShort());
/* 1795 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeShorts(ShortIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1803 */     storeShorts(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static ShortIterator asShortIterator(DataInput dataInput)
/*      */   {
/* 1837 */     return new ShortDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static ShortIterator asShortIterator(File file)
/*      */     throws IOException
/*      */   {
/* 1844 */     return new ShortDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static ShortIterator asShortIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 1851 */     return asShortIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadChars(DataInput dataInput, char[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1899 */     CharArrays.ensureOffsetLength(array, offset, length);
/* 1900 */     int i = 0;
/*      */     try {
/* 1902 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readChar(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1905 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadChars(DataInput dataInput, char[] array)
/*      */     throws IOException
/*      */   {
/* 1914 */     int i = 0;
/*      */     try {
/* 1916 */       int length = array.length;
/* 1917 */       for (i = 0; i < length; i++) array[i] = dataInput.readChar(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1920 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadChars(File file, char[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1931 */     CharArrays.ensureOffsetLength(array, offset, length);
/* 1932 */     FileInputStream fis = new FileInputStream(file);
/* 1933 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1934 */     int i = 0;
/*      */     try {
/* 1936 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readChar(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1939 */     dis.close();
/* 1940 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadChars(CharSequence filename, char[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 1951 */     return loadChars(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadChars(File file, char[] array)
/*      */     throws IOException
/*      */   {
/* 1960 */     FileInputStream fis = new FileInputStream(file);
/* 1961 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1962 */     int i = 0;
/*      */     try {
/* 1964 */       int length = array.length;
/* 1965 */       for (i = 0; i < length; i++) array[i] = dis.readChar(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 1968 */     dis.close();
/* 1969 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadChars(CharSequence filename, char[] array)
/*      */     throws IOException
/*      */   {
/* 1978 */     return loadChars(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static char[] loadChars(File file)
/*      */     throws IOException
/*      */   {
/* 1990 */     FileInputStream fis = new FileInputStream(file);
/* 1991 */     long length = fis.getChannel().size() / 2L;
/* 1992 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 1993 */     char[] array = new char[(int)length];
/* 1994 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1995 */     for (int i = 0; i < length; i++) array[i] = dis.readChar();
/* 1996 */     dis.close();
/* 1997 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[] loadChars(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2009 */     return loadChars(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2019 */     CharArrays.ensureOffsetLength(array, offset, length);
/* 2020 */     for (int i = 0; i < length; i++) dataOutput.writeChar(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2028 */     int length = array.length;
/* 2029 */     for (int i = 0; i < length; i++) dataOutput.writeChar(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 2039 */     CharArrays.ensureOffsetLength(array, offset, length);
/* 2040 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2041 */     for (int i = 0; i < length; i++) dos.writeChar(array[(offset + i)]);
/* 2042 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2052 */     storeChars(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[] array, File file)
/*      */     throws IOException
/*      */   {
/* 2060 */     int length = array.length;
/* 2061 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2062 */     for (int i = 0; i < length; i++) dos.writeChar(array[i]);
/* 2063 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2071 */     storeChars(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadChars(DataInput dataInput, char[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2082 */     CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2083 */     long c = 0L;
/*      */     try {
/* 2085 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2086 */         char[] t = array[i];
/* 2087 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2088 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2089 */           t[d] = dataInput.readChar();
/* 2090 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2095 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadChars(DataInput dataInput, char[][] array)
/*      */     throws IOException
/*      */   {
/* 2104 */     long c = 0L;
/*      */     try {
/* 2106 */       for (int i = 0; i < array.length; i++) {
/* 2107 */         char[] t = array[i];
/* 2108 */         int l = t.length;
/* 2109 */         for (int d = 0; d < l; d++) {
/* 2110 */           t[d] = dataInput.readChar();
/* 2111 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2116 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadChars(File file, char[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2127 */     CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2128 */     FileInputStream fis = new FileInputStream(file);
/* 2129 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2130 */     long c = 0L;
/*      */     try {
/* 2132 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2133 */         char[] t = array[i];
/* 2134 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2135 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2136 */           t[d] = dis.readChar();
/* 2137 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2142 */     dis.close();
/* 2143 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadChars(CharSequence filename, char[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2154 */     return loadChars(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadChars(File file, char[][] array)
/*      */     throws IOException
/*      */   {
/* 2163 */     FileInputStream fis = new FileInputStream(file);
/* 2164 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2165 */     long c = 0L;
/*      */     try {
/* 2167 */       for (int i = 0; i < array.length; i++) {
/* 2168 */         char[] t = array[i];
/* 2169 */         int l = t.length;
/* 2170 */         for (int d = 0; d < l; d++) {
/* 2171 */           t[d] = dis.readChar();
/* 2172 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2177 */     dis.close();
/* 2178 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadChars(CharSequence filename, char[][] array)
/*      */     throws IOException
/*      */   {
/* 2187 */     return loadChars(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static char[][] loadCharsBig(File file)
/*      */     throws IOException
/*      */   {
/* 2199 */     FileInputStream fis = new FileInputStream(file);
/* 2200 */     long length = fis.getChannel().size() / 2L;
/* 2201 */     char[][] array = CharBigArrays.newBigArray(length);
/* 2202 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2203 */     for (int i = 0; i < array.length; i++) {
/* 2204 */       char[] t = array[i];
/* 2205 */       int l = t.length;
/* 2206 */       for (int d = 0; d < l; d++) t[d] = dis.readChar();
/*      */     }
/* 2208 */     dis.close();
/* 2209 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[][] loadCharsBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2221 */     return loadCharsBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2231 */     CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2232 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2233 */       char[] t = array[i];
/* 2234 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2235 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeChar(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2244 */     for (int i = 0; i < array.length; i++) {
/* 2245 */       char[] t = array[i];
/* 2246 */       int l = t.length;
/* 2247 */       for (int d = 0; d < l; d++) dataOutput.writeChar(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 2258 */     CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2259 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2260 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2261 */       char[] t = array[i];
/* 2262 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2263 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeChar(t[d]);
/*      */     }
/* 2265 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2275 */     storeChars(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 2283 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2284 */     for (int i = 0; i < array.length; i++) {
/* 2285 */       char[] t = array[i];
/* 2286 */       int l = t.length;
/* 2287 */       for (int d = 0; d < l; d++) dos.writeChar(t[d]);
/*      */     }
/* 2289 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeChars(char[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2297 */     storeChars(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeChars(CharIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2305 */     while (i.hasNext()) dataOutput.writeChar(i.nextChar());
/*      */   }
/*      */ 
/*      */   public static void storeChars(CharIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 2313 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2314 */     while (i.hasNext()) dos.writeChar(i.nextChar());
/* 2315 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeChars(CharIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2323 */     storeChars(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static CharIterator asCharIterator(DataInput dataInput)
/*      */   {
/* 2357 */     return new CharDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static CharIterator asCharIterator(File file)
/*      */     throws IOException
/*      */   {
/* 2364 */     return new CharDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static CharIterator asCharIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2371 */     return asCharIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadInts(DataInput dataInput, int[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2419 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 2420 */     int i = 0;
/*      */     try {
/* 2422 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readInt(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2425 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadInts(DataInput dataInput, int[] array)
/*      */     throws IOException
/*      */   {
/* 2434 */     int i = 0;
/*      */     try {
/* 2436 */       int length = array.length;
/* 2437 */       for (i = 0; i < length; i++) array[i] = dataInput.readInt(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2440 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadInts(File file, int[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2451 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 2452 */     FileInputStream fis = new FileInputStream(file);
/* 2453 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2454 */     int i = 0;
/*      */     try {
/* 2456 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readInt(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2459 */     dis.close();
/* 2460 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadInts(CharSequence filename, int[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2471 */     return loadInts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadInts(File file, int[] array)
/*      */     throws IOException
/*      */   {
/* 2480 */     FileInputStream fis = new FileInputStream(file);
/* 2481 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2482 */     int i = 0;
/*      */     try {
/* 2484 */       int length = array.length;
/* 2485 */       for (i = 0; i < length; i++) array[i] = dis.readInt(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2488 */     dis.close();
/* 2489 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadInts(CharSequence filename, int[] array)
/*      */     throws IOException
/*      */   {
/* 2498 */     return loadInts(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static int[] loadInts(File file)
/*      */     throws IOException
/*      */   {
/* 2510 */     FileInputStream fis = new FileInputStream(file);
/* 2511 */     long length = fis.getChannel().size() / 4L;
/* 2512 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 2513 */     int[] array = new int[(int)length];
/* 2514 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2515 */     for (int i = 0; i < length; i++) array[i] = dis.readInt();
/* 2516 */     dis.close();
/* 2517 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[] loadInts(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2529 */     return loadInts(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2539 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 2540 */     for (int i = 0; i < length; i++) dataOutput.writeInt(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2548 */     int length = array.length;
/* 2549 */     for (int i = 0; i < length; i++) dataOutput.writeInt(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 2559 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 2560 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2561 */     for (int i = 0; i < length; i++) dos.writeInt(array[(offset + i)]);
/* 2562 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2572 */     storeInts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, File file)
/*      */     throws IOException
/*      */   {
/* 2580 */     int length = array.length;
/* 2581 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2582 */     for (int i = 0; i < length; i++) dos.writeInt(array[i]);
/* 2583 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2591 */     storeInts(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadInts(DataInput dataInput, int[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2602 */     IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2603 */     long c = 0L;
/*      */     try {
/* 2605 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2606 */         int[] t = array[i];
/* 2607 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2608 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2609 */           t[d] = dataInput.readInt();
/* 2610 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2615 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadInts(DataInput dataInput, int[][] array)
/*      */     throws IOException
/*      */   {
/* 2624 */     long c = 0L;
/*      */     try {
/* 2626 */       for (int i = 0; i < array.length; i++) {
/* 2627 */         int[] t = array[i];
/* 2628 */         int l = t.length;
/* 2629 */         for (int d = 0; d < l; d++) {
/* 2630 */           t[d] = dataInput.readInt();
/* 2631 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2636 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadInts(File file, int[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2647 */     IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2648 */     FileInputStream fis = new FileInputStream(file);
/* 2649 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2650 */     long c = 0L;
/*      */     try {
/* 2652 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2653 */         int[] t = array[i];
/* 2654 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2655 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2656 */           t[d] = dis.readInt();
/* 2657 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2662 */     dis.close();
/* 2663 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadInts(CharSequence filename, int[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 2674 */     return loadInts(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadInts(File file, int[][] array)
/*      */     throws IOException
/*      */   {
/* 2683 */     FileInputStream fis = new FileInputStream(file);
/* 2684 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2685 */     long c = 0L;
/*      */     try {
/* 2687 */       for (int i = 0; i < array.length; i++) {
/* 2688 */         int[] t = array[i];
/* 2689 */         int l = t.length;
/* 2690 */         for (int d = 0; d < l; d++) {
/* 2691 */           t[d] = dis.readInt();
/* 2692 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 2697 */     dis.close();
/* 2698 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadInts(CharSequence filename, int[][] array)
/*      */     throws IOException
/*      */   {
/* 2707 */     return loadInts(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static int[][] loadIntsBig(File file)
/*      */     throws IOException
/*      */   {
/* 2719 */     FileInputStream fis = new FileInputStream(file);
/* 2720 */     long length = fis.getChannel().size() / 4L;
/* 2721 */     int[][] array = IntBigArrays.newBigArray(length);
/* 2722 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2723 */     for (int i = 0; i < array.length; i++) {
/* 2724 */       int[] t = array[i];
/* 2725 */       int l = t.length;
/* 2726 */       for (int d = 0; d < l; d++) t[d] = dis.readInt();
/*      */     }
/* 2728 */     dis.close();
/* 2729 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[][] loadIntsBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2741 */     return loadIntsBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2751 */     IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2752 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2753 */       int[] t = array[i];
/* 2754 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2755 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeInt(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2764 */     for (int i = 0; i < array.length; i++) {
/* 2765 */       int[] t = array[i];
/* 2766 */       int l = t.length;
/* 2767 */       for (int d = 0; d < l; d++) dataOutput.writeInt(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 2778 */     IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2779 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2780 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2781 */       int[] t = array[i];
/* 2782 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2783 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeInt(t[d]);
/*      */     }
/* 2785 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2795 */     storeInts(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 2803 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2804 */     for (int i = 0; i < array.length; i++) {
/* 2805 */       int[] t = array[i];
/* 2806 */       int l = t.length;
/* 2807 */       for (int d = 0; d < l; d++) dos.writeInt(t[d]);
/*      */     }
/* 2809 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(int[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2817 */     storeInts(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeInts(IntIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 2825 */     while (i.hasNext()) dataOutput.writeInt(i.nextInt());
/*      */   }
/*      */ 
/*      */   public static void storeInts(IntIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 2833 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2834 */     while (i.hasNext()) dos.writeInt(i.nextInt());
/* 2835 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeInts(IntIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2843 */     storeInts(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static IntIterator asIntIterator(DataInput dataInput)
/*      */   {
/* 2877 */     return new IntDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static IntIterator asIntIterator(File file)
/*      */     throws IOException
/*      */   {
/* 2884 */     return new IntDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static IntIterator asIntIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 2891 */     return asIntIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadLongs(DataInput dataInput, long[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2939 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 2940 */     int i = 0;
/*      */     try {
/* 2942 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readLong(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2945 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadLongs(DataInput dataInput, long[] array)
/*      */     throws IOException
/*      */   {
/* 2954 */     int i = 0;
/*      */     try {
/* 2956 */       int length = array.length;
/* 2957 */       for (i = 0; i < length; i++) array[i] = dataInput.readLong(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2960 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadLongs(File file, long[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2971 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 2972 */     FileInputStream fis = new FileInputStream(file);
/* 2973 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2974 */     int i = 0;
/*      */     try {
/* 2976 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readLong(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 2979 */     dis.close();
/* 2980 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadLongs(CharSequence filename, long[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 2991 */     return loadLongs(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadLongs(File file, long[] array)
/*      */     throws IOException
/*      */   {
/* 3000 */     FileInputStream fis = new FileInputStream(file);
/* 3001 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3002 */     int i = 0;
/*      */     try {
/* 3004 */       int length = array.length;
/* 3005 */       for (i = 0; i < length; i++) array[i] = dis.readLong(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 3008 */     dis.close();
/* 3009 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadLongs(CharSequence filename, long[] array)
/*      */     throws IOException
/*      */   {
/* 3018 */     return loadLongs(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static long[] loadLongs(File file)
/*      */     throws IOException
/*      */   {
/* 3030 */     FileInputStream fis = new FileInputStream(file);
/* 3031 */     long length = fis.getChannel().size() / 8L;
/* 3032 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 3033 */     long[] array = new long[(int)length];
/* 3034 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3035 */     for (int i = 0; i < length; i++) array[i] = dis.readLong();
/* 3036 */     dis.close();
/* 3037 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[] loadLongs(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3049 */     return loadLongs(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3059 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 3060 */     for (int i = 0; i < length; i++) dataOutput.writeLong(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3068 */     int length = array.length;
/* 3069 */     for (int i = 0; i < length; i++) dataOutput.writeLong(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 3079 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 3080 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3081 */     for (int i = 0; i < length; i++) dos.writeLong(array[(offset + i)]);
/* 3082 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3092 */     storeLongs(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, File file)
/*      */     throws IOException
/*      */   {
/* 3100 */     int length = array.length;
/* 3101 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3102 */     for (int i = 0; i < length; i++) dos.writeLong(array[i]);
/* 3103 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3111 */     storeLongs(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadLongs(DataInput dataInput, long[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 3122 */     LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3123 */     long c = 0L;
/*      */     try {
/* 3125 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3126 */         long[] t = array[i];
/* 3127 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3128 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3129 */           t[d] = dataInput.readLong();
/* 3130 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3135 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadLongs(DataInput dataInput, long[][] array)
/*      */     throws IOException
/*      */   {
/* 3144 */     long c = 0L;
/*      */     try {
/* 3146 */       for (int i = 0; i < array.length; i++) {
/* 3147 */         long[] t = array[i];
/* 3148 */         int l = t.length;
/* 3149 */         for (int d = 0; d < l; d++) {
/* 3150 */           t[d] = dataInput.readLong();
/* 3151 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3156 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadLongs(File file, long[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 3167 */     LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3168 */     FileInputStream fis = new FileInputStream(file);
/* 3169 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3170 */     long c = 0L;
/*      */     try {
/* 3172 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3173 */         long[] t = array[i];
/* 3174 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3175 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3176 */           t[d] = dis.readLong();
/* 3177 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3182 */     dis.close();
/* 3183 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadLongs(CharSequence filename, long[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 3194 */     return loadLongs(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadLongs(File file, long[][] array)
/*      */     throws IOException
/*      */   {
/* 3203 */     FileInputStream fis = new FileInputStream(file);
/* 3204 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3205 */     long c = 0L;
/*      */     try {
/* 3207 */       for (int i = 0; i < array.length; i++) {
/* 3208 */         long[] t = array[i];
/* 3209 */         int l = t.length;
/* 3210 */         for (int d = 0; d < l; d++) {
/* 3211 */           t[d] = dis.readLong();
/* 3212 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3217 */     dis.close();
/* 3218 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadLongs(CharSequence filename, long[][] array)
/*      */     throws IOException
/*      */   {
/* 3227 */     return loadLongs(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static long[][] loadLongsBig(File file)
/*      */     throws IOException
/*      */   {
/* 3239 */     FileInputStream fis = new FileInputStream(file);
/* 3240 */     long length = fis.getChannel().size() / 8L;
/* 3241 */     long[][] array = LongBigArrays.newBigArray(length);
/* 3242 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3243 */     for (int i = 0; i < array.length; i++) {
/* 3244 */       long[] t = array[i];
/* 3245 */       int l = t.length;
/* 3246 */       for (int d = 0; d < l; d++) t[d] = dis.readLong();
/*      */     }
/* 3248 */     dis.close();
/* 3249 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[][] loadLongsBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3261 */     return loadLongsBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3271 */     LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3272 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3273 */       long[] t = array[i];
/* 3274 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3275 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeLong(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3284 */     for (int i = 0; i < array.length; i++) {
/* 3285 */       long[] t = array[i];
/* 3286 */       int l = t.length;
/* 3287 */       for (int d = 0; d < l; d++) dataOutput.writeLong(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 3298 */     LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3299 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3300 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3301 */       long[] t = array[i];
/* 3302 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3303 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeLong(t[d]);
/*      */     }
/* 3305 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3315 */     storeLongs(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 3323 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3324 */     for (int i = 0; i < array.length; i++) {
/* 3325 */       long[] t = array[i];
/* 3326 */       int l = t.length;
/* 3327 */       for (int d = 0; d < l; d++) dos.writeLong(t[d]);
/*      */     }
/* 3329 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(long[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3337 */     storeLongs(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeLongs(LongIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3345 */     while (i.hasNext()) dataOutput.writeLong(i.nextLong());
/*      */   }
/*      */ 
/*      */   public static void storeLongs(LongIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 3353 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3354 */     while (i.hasNext()) dos.writeLong(i.nextLong());
/* 3355 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeLongs(LongIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3363 */     storeLongs(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static LongIterator asLongIterator(DataInput dataInput)
/*      */   {
/* 3397 */     return new LongDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static LongIterator asLongIterator(File file)
/*      */     throws IOException
/*      */   {
/* 3404 */     return new LongDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static LongIterator asLongIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3411 */     return asLongIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadFloats(DataInput dataInput, float[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 3459 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 3460 */     int i = 0;
/*      */     try {
/* 3462 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readFloat(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 3465 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadFloats(DataInput dataInput, float[] array)
/*      */     throws IOException
/*      */   {
/* 3474 */     int i = 0;
/*      */     try {
/* 3476 */       int length = array.length;
/* 3477 */       for (i = 0; i < length; i++) array[i] = dataInput.readFloat(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 3480 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadFloats(File file, float[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 3491 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 3492 */     FileInputStream fis = new FileInputStream(file);
/* 3493 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3494 */     int i = 0;
/*      */     try {
/* 3496 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readFloat(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 3499 */     dis.close();
/* 3500 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadFloats(CharSequence filename, float[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 3511 */     return loadFloats(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadFloats(File file, float[] array)
/*      */     throws IOException
/*      */   {
/* 3520 */     FileInputStream fis = new FileInputStream(file);
/* 3521 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3522 */     int i = 0;
/*      */     try {
/* 3524 */       int length = array.length;
/* 3525 */       for (i = 0; i < length; i++) array[i] = dis.readFloat(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 3528 */     dis.close();
/* 3529 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadFloats(CharSequence filename, float[] array)
/*      */     throws IOException
/*      */   {
/* 3538 */     return loadFloats(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static float[] loadFloats(File file)
/*      */     throws IOException
/*      */   {
/* 3550 */     FileInputStream fis = new FileInputStream(file);
/* 3551 */     long length = fis.getChannel().size() / 4L;
/* 3552 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 3553 */     float[] array = new float[(int)length];
/* 3554 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3555 */     for (int i = 0; i < length; i++) array[i] = dis.readFloat();
/* 3556 */     dis.close();
/* 3557 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[] loadFloats(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3569 */     return loadFloats(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3579 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 3580 */     for (int i = 0; i < length; i++) dataOutput.writeFloat(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3588 */     int length = array.length;
/* 3589 */     for (int i = 0; i < length; i++) dataOutput.writeFloat(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 3599 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 3600 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3601 */     for (int i = 0; i < length; i++) dos.writeFloat(array[(offset + i)]);
/* 3602 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3612 */     storeFloats(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, File file)
/*      */     throws IOException
/*      */   {
/* 3620 */     int length = array.length;
/* 3621 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3622 */     for (int i = 0; i < length; i++) dos.writeFloat(array[i]);
/* 3623 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3631 */     storeFloats(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadFloats(DataInput dataInput, float[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 3642 */     FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3643 */     long c = 0L;
/*      */     try {
/* 3645 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3646 */         float[] t = array[i];
/* 3647 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3648 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3649 */           t[d] = dataInput.readFloat();
/* 3650 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3655 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadFloats(DataInput dataInput, float[][] array)
/*      */     throws IOException
/*      */   {
/* 3664 */     long c = 0L;
/*      */     try {
/* 3666 */       for (int i = 0; i < array.length; i++) {
/* 3667 */         float[] t = array[i];
/* 3668 */         int l = t.length;
/* 3669 */         for (int d = 0; d < l; d++) {
/* 3670 */           t[d] = dataInput.readFloat();
/* 3671 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3676 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadFloats(File file, float[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 3687 */     FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3688 */     FileInputStream fis = new FileInputStream(file);
/* 3689 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3690 */     long c = 0L;
/*      */     try {
/* 3692 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3693 */         float[] t = array[i];
/* 3694 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3695 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3696 */           t[d] = dis.readFloat();
/* 3697 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3702 */     dis.close();
/* 3703 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadFloats(CharSequence filename, float[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 3714 */     return loadFloats(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadFloats(File file, float[][] array)
/*      */     throws IOException
/*      */   {
/* 3723 */     FileInputStream fis = new FileInputStream(file);
/* 3724 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3725 */     long c = 0L;
/*      */     try {
/* 3727 */       for (int i = 0; i < array.length; i++) {
/* 3728 */         float[] t = array[i];
/* 3729 */         int l = t.length;
/* 3730 */         for (int d = 0; d < l; d++) {
/* 3731 */           t[d] = dis.readFloat();
/* 3732 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 3737 */     dis.close();
/* 3738 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadFloats(CharSequence filename, float[][] array)
/*      */     throws IOException
/*      */   {
/* 3747 */     return loadFloats(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static float[][] loadFloatsBig(File file)
/*      */     throws IOException
/*      */   {
/* 3759 */     FileInputStream fis = new FileInputStream(file);
/* 3760 */     long length = fis.getChannel().size() / 4L;
/* 3761 */     float[][] array = FloatBigArrays.newBigArray(length);
/* 3762 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3763 */     for (int i = 0; i < array.length; i++) {
/* 3764 */       float[] t = array[i];
/* 3765 */       int l = t.length;
/* 3766 */       for (int d = 0; d < l; d++) t[d] = dis.readFloat();
/*      */     }
/* 3768 */     dis.close();
/* 3769 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[][] loadFloatsBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3781 */     return loadFloatsBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3791 */     FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3792 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3793 */       float[] t = array[i];
/* 3794 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3795 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeFloat(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3804 */     for (int i = 0; i < array.length; i++) {
/* 3805 */       float[] t = array[i];
/* 3806 */       int l = t.length;
/* 3807 */       for (int d = 0; d < l; d++) dataOutput.writeFloat(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 3818 */     FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3819 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3820 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3821 */       float[] t = array[i];
/* 3822 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3823 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeFloat(t[d]);
/*      */     }
/* 3825 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3835 */     storeFloats(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 3843 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3844 */     for (int i = 0; i < array.length; i++) {
/* 3845 */       float[] t = array[i];
/* 3846 */       int l = t.length;
/* 3847 */       for (int d = 0; d < l; d++) dos.writeFloat(t[d]);
/*      */     }
/* 3849 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(float[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3857 */     storeFloats(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeFloats(FloatIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 3865 */     while (i.hasNext()) dataOutput.writeFloat(i.nextFloat());
/*      */   }
/*      */ 
/*      */   public static void storeFloats(FloatIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 3873 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3874 */     while (i.hasNext()) dos.writeFloat(i.nextFloat());
/* 3875 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeFloats(FloatIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3883 */     storeFloats(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static FloatIterator asFloatIterator(DataInput dataInput)
/*      */   {
/* 3917 */     return new FloatDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static FloatIterator asFloatIterator(File file)
/*      */     throws IOException
/*      */   {
/* 3924 */     return new FloatDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static FloatIterator asFloatIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 3931 */     return asFloatIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(DataInput dataInput, double[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 3979 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 3980 */     int i = 0;
/*      */     try {
/* 3982 */       for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readDouble(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 3985 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(DataInput dataInput, double[] array)
/*      */     throws IOException
/*      */   {
/* 3994 */     int i = 0;
/*      */     try {
/* 3996 */       int length = array.length;
/* 3997 */       for (i = 0; i < length; i++) array[i] = dataInput.readDouble(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 4000 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(File file, double[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 4011 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 4012 */     FileInputStream fis = new FileInputStream(file);
/* 4013 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4014 */     int i = 0;
/*      */     try {
/* 4016 */       for (i = 0; i < length; i++) array[(i + offset)] = dis.readDouble(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 4019 */     dis.close();
/* 4020 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(CharSequence filename, double[] array, int offset, int length)
/*      */     throws IOException
/*      */   {
/* 4031 */     return loadDoubles(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(File file, double[] array)
/*      */     throws IOException
/*      */   {
/* 4040 */     FileInputStream fis = new FileInputStream(file);
/* 4041 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4042 */     int i = 0;
/*      */     try {
/* 4044 */       int length = array.length;
/* 4045 */       for (i = 0; i < length; i++) array[i] = dis.readDouble(); 
/*      */     }
/*      */     catch (EOFException itsOk) {  }
/*      */ 
/* 4048 */     dis.close();
/* 4049 */     return i;
/*      */   }
/*      */ 
/*      */   public static int loadDoubles(CharSequence filename, double[] array)
/*      */     throws IOException
/*      */   {
/* 4058 */     return loadDoubles(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static double[] loadDoubles(File file)
/*      */     throws IOException
/*      */   {
/* 4070 */     FileInputStream fis = new FileInputStream(file);
/* 4071 */     long length = fis.getChannel().size() / 8L;
/* 4072 */     if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 4073 */     double[] array = new double[(int)length];
/* 4074 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4075 */     for (int i = 0; i < length; i++) array[i] = dis.readDouble();
/* 4076 */     dis.close();
/* 4077 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[] loadDoubles(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4089 */     return loadDoubles(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, int offset, int length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 4099 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 4100 */     for (int i = 0; i < length; i++) dataOutput.writeDouble(array[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 4108 */     int length = array.length;
/* 4109 */     for (int i = 0; i < length; i++) dataOutput.writeDouble(array[i]);
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, int offset, int length, File file)
/*      */     throws IOException
/*      */   {
/* 4119 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 4120 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4121 */     for (int i = 0; i < length; i++) dos.writeDouble(array[(offset + i)]);
/* 4122 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, int offset, int length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4132 */     storeDoubles(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, File file)
/*      */     throws IOException
/*      */   {
/* 4140 */     int length = array.length;
/* 4141 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4142 */     for (int i = 0; i < length; i++) dos.writeDouble(array[i]);
/* 4143 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4151 */     storeDoubles(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(DataInput dataInput, double[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 4162 */     DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4163 */     long c = 0L;
/*      */     try {
/* 4165 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4166 */         double[] t = array[i];
/* 4167 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4168 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 4169 */           t[d] = dataInput.readDouble();
/* 4170 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 4175 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(DataInput dataInput, double[][] array)
/*      */     throws IOException
/*      */   {
/* 4184 */     long c = 0L;
/*      */     try {
/* 4186 */       for (int i = 0; i < array.length; i++) {
/* 4187 */         double[] t = array[i];
/* 4188 */         int l = t.length;
/* 4189 */         for (int d = 0; d < l; d++) {
/* 4190 */           t[d] = dataInput.readDouble();
/* 4191 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 4196 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(File file, double[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 4207 */     DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4208 */     FileInputStream fis = new FileInputStream(file);
/* 4209 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4210 */     long c = 0L;
/*      */     try {
/* 4212 */       for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4213 */         double[] t = array[i];
/* 4214 */         int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4215 */         for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 4216 */           t[d] = dis.readDouble();
/* 4217 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 4222 */     dis.close();
/* 4223 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(CharSequence filename, double[][] array, long offset, long length)
/*      */     throws IOException
/*      */   {
/* 4234 */     return loadDoubles(new File(filename.toString()), array, offset, length);
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(File file, double[][] array)
/*      */     throws IOException
/*      */   {
/* 4243 */     FileInputStream fis = new FileInputStream(file);
/* 4244 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4245 */     long c = 0L;
/*      */     try {
/* 4247 */       for (int i = 0; i < array.length; i++) {
/* 4248 */         double[] t = array[i];
/* 4249 */         int l = t.length;
/* 4250 */         for (int d = 0; d < l; d++) {
/* 4251 */           t[d] = dis.readDouble();
/* 4252 */           c += 1L;
/*      */         }
/*      */       }
/*      */     } catch (EOFException itsOk) {
/*      */     }
/* 4257 */     dis.close();
/* 4258 */     return c;
/*      */   }
/*      */ 
/*      */   public static long loadDoubles(CharSequence filename, double[][] array)
/*      */     throws IOException
/*      */   {
/* 4267 */     return loadDoubles(new File(filename.toString()), array);
/*      */   }
/*      */ 
/*      */   public static double[][] loadDoublesBig(File file)
/*      */     throws IOException
/*      */   {
/* 4279 */     FileInputStream fis = new FileInputStream(file);
/* 4280 */     long length = fis.getChannel().size() / 8L;
/* 4281 */     double[][] array = DoubleBigArrays.newBigArray(length);
/* 4282 */     DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4283 */     for (int i = 0; i < array.length; i++) {
/* 4284 */       double[] t = array[i];
/* 4285 */       int l = t.length;
/* 4286 */       for (int d = 0; d < l; d++) t[d] = dis.readDouble();
/*      */     }
/* 4288 */     dis.close();
/* 4289 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[][] loadDoublesBig(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4301 */     return loadDoublesBig(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, long offset, long length, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 4311 */     DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4312 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4313 */       double[] t = array[i];
/* 4314 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4315 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dataOutput.writeDouble(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 4324 */     for (int i = 0; i < array.length; i++) {
/* 4325 */       double[] t = array[i];
/* 4326 */       int l = t.length;
/* 4327 */       for (int d = 0; d < l; d++) dataOutput.writeDouble(t[d]);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, long offset, long length, File file)
/*      */     throws IOException
/*      */   {
/* 4338 */     DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4339 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4340 */     for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4341 */       double[] t = array[i];
/* 4342 */       int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4343 */       for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeDouble(t[d]);
/*      */     }
/* 4345 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, long offset, long length, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4355 */     storeDoubles(array, offset, length, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, File file)
/*      */     throws IOException
/*      */   {
/* 4363 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4364 */     for (int i = 0; i < array.length; i++) {
/* 4365 */       double[] t = array[i];
/* 4366 */       int l = t.length;
/* 4367 */       for (int d = 0; d < l; d++) dos.writeDouble(t[d]);
/*      */     }
/* 4369 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(double[][] array, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4377 */     storeDoubles(array, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(DoubleIterator i, DataOutput dataOutput)
/*      */     throws IOException
/*      */   {
/* 4385 */     while (i.hasNext()) dataOutput.writeDouble(i.nextDouble());
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(DoubleIterator i, File file)
/*      */     throws IOException
/*      */   {
/* 4393 */     DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4394 */     while (i.hasNext()) dos.writeDouble(i.nextDouble());
/* 4395 */     dos.close();
/*      */   }
/*      */ 
/*      */   public static void storeDoubles(DoubleIterator i, CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4403 */     storeDoubles(i, new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   public static DoubleIterator asDoubleIterator(DataInput dataInput)
/*      */   {
/* 4437 */     return new DoubleDataInputWrapper(dataInput);
/*      */   }
/*      */ 
/*      */   public static DoubleIterator asDoubleIterator(File file)
/*      */     throws IOException
/*      */   {
/* 4444 */     return new DoubleDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*      */   }
/*      */ 
/*      */   public static DoubleIterator asDoubleIterator(CharSequence filename)
/*      */     throws IOException
/*      */   {
/* 4451 */     return asDoubleIterator(new File(filename.toString()));
/*      */   }
/*      */ 
/*      */   private static final class DoubleDataInputWrapper extends AbstractDoubleIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 4408 */     private boolean toAdvance = true;
/* 4409 */     private boolean endOfProcess = false;
/*      */     private double next;
/*      */ 
/*      */     public DoubleDataInputWrapper(DataInput dataInput)
/*      */     {
/* 4412 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 4415 */       if (!this.toAdvance) return !this.endOfProcess;
/* 4416 */       this.toAdvance = false;
/*      */       try {
/* 4418 */         this.next = this.dataInput.readDouble();
/*      */       }
/*      */       catch (EOFException eof) {
/* 4421 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 4423 */         throw new RuntimeException(rethrow);
/* 4424 */       }return !this.endOfProcess;
/*      */     }
/*      */     public double nextDouble() {
/* 4427 */       if (!hasNext()) throw new NoSuchElementException();
/* 4428 */       this.toAdvance = true;
/* 4429 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class FloatDataInputWrapper extends AbstractFloatIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 3888 */     private boolean toAdvance = true;
/* 3889 */     private boolean endOfProcess = false;
/*      */     private float next;
/*      */ 
/*      */     public FloatDataInputWrapper(DataInput dataInput)
/*      */     {
/* 3892 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 3895 */       if (!this.toAdvance) return !this.endOfProcess;
/* 3896 */       this.toAdvance = false;
/*      */       try {
/* 3898 */         this.next = this.dataInput.readFloat();
/*      */       }
/*      */       catch (EOFException eof) {
/* 3901 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 3903 */         throw new RuntimeException(rethrow);
/* 3904 */       }return !this.endOfProcess;
/*      */     }
/*      */     public float nextFloat() {
/* 3907 */       if (!hasNext()) throw new NoSuchElementException();
/* 3908 */       this.toAdvance = true;
/* 3909 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class LongDataInputWrapper extends AbstractLongIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 3368 */     private boolean toAdvance = true;
/* 3369 */     private boolean endOfProcess = false;
/*      */     private long next;
/*      */ 
/*      */     public LongDataInputWrapper(DataInput dataInput)
/*      */     {
/* 3372 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 3375 */       if (!this.toAdvance) return !this.endOfProcess;
/* 3376 */       this.toAdvance = false;
/*      */       try {
/* 3378 */         this.next = this.dataInput.readLong();
/*      */       }
/*      */       catch (EOFException eof) {
/* 3381 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 3383 */         throw new RuntimeException(rethrow);
/* 3384 */       }return !this.endOfProcess;
/*      */     }
/*      */     public long nextLong() {
/* 3387 */       if (!hasNext()) throw new NoSuchElementException();
/* 3388 */       this.toAdvance = true;
/* 3389 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class IntDataInputWrapper extends AbstractIntIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 2848 */     private boolean toAdvance = true;
/* 2849 */     private boolean endOfProcess = false;
/*      */     private int next;
/*      */ 
/*      */     public IntDataInputWrapper(DataInput dataInput)
/*      */     {
/* 2852 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 2855 */       if (!this.toAdvance) return !this.endOfProcess;
/* 2856 */       this.toAdvance = false;
/*      */       try {
/* 2858 */         this.next = this.dataInput.readInt();
/*      */       }
/*      */       catch (EOFException eof) {
/* 2861 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 2863 */         throw new RuntimeException(rethrow);
/* 2864 */       }return !this.endOfProcess;
/*      */     }
/*      */     public int nextInt() {
/* 2867 */       if (!hasNext()) throw new NoSuchElementException();
/* 2868 */       this.toAdvance = true;
/* 2869 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class CharDataInputWrapper extends AbstractCharIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 2328 */     private boolean toAdvance = true;
/* 2329 */     private boolean endOfProcess = false;
/*      */     private char next;
/*      */ 
/*      */     public CharDataInputWrapper(DataInput dataInput)
/*      */     {
/* 2332 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 2335 */       if (!this.toAdvance) return !this.endOfProcess;
/* 2336 */       this.toAdvance = false;
/*      */       try {
/* 2338 */         this.next = this.dataInput.readChar();
/*      */       }
/*      */       catch (EOFException eof) {
/* 2341 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 2343 */         throw new RuntimeException(rethrow);
/* 2344 */       }return !this.endOfProcess;
/*      */     }
/*      */     public char nextChar() {
/* 2347 */       if (!hasNext()) throw new NoSuchElementException();
/* 2348 */       this.toAdvance = true;
/* 2349 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class ShortDataInputWrapper extends AbstractShortIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 1808 */     private boolean toAdvance = true;
/* 1809 */     private boolean endOfProcess = false;
/*      */     private short next;
/*      */ 
/*      */     public ShortDataInputWrapper(DataInput dataInput)
/*      */     {
/* 1812 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 1815 */       if (!this.toAdvance) return !this.endOfProcess;
/* 1816 */       this.toAdvance = false;
/*      */       try {
/* 1818 */         this.next = this.dataInput.readShort();
/*      */       }
/*      */       catch (EOFException eof) {
/* 1821 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 1823 */         throw new RuntimeException(rethrow);
/* 1824 */       }return !this.endOfProcess;
/*      */     }
/*      */     public short nextShort() {
/* 1827 */       if (!hasNext()) throw new NoSuchElementException();
/* 1828 */       this.toAdvance = true;
/* 1829 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class ByteDataInputWrapper extends AbstractByteIterator
/*      */   {
/*      */     private final DataInput dataInput;
/* 1288 */     private boolean toAdvance = true;
/* 1289 */     private boolean endOfProcess = false;
/*      */     private byte next;
/*      */ 
/*      */     public ByteDataInputWrapper(DataInput dataInput)
/*      */     {
/* 1292 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/* 1295 */       if (!this.toAdvance) return !this.endOfProcess;
/* 1296 */       this.toAdvance = false;
/*      */       try {
/* 1298 */         this.next = this.dataInput.readByte();
/*      */       }
/*      */       catch (EOFException eof) {
/* 1301 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/* 1303 */         throw new RuntimeException(rethrow);
/* 1304 */       }return !this.endOfProcess;
/*      */     }
/*      */     public byte nextByte() {
/* 1307 */       if (!hasNext()) throw new NoSuchElementException();
/* 1308 */       this.toAdvance = true;
/* 1309 */       return this.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class BooleanDataInputWrapper extends AbstractBooleanIterator
/*      */   {
/*      */     private final DataInput dataInput;
/*  647 */     private boolean toAdvance = true;
/*  648 */     private boolean endOfProcess = false;
/*      */     private boolean next;
/*      */ 
/*      */     public BooleanDataInputWrapper(DataInput dataInput)
/*      */     {
/*  651 */       this.dataInput = dataInput;
/*      */     }
/*      */     public boolean hasNext() {
/*  654 */       if (!this.toAdvance) return !this.endOfProcess;
/*  655 */       this.toAdvance = false;
/*      */       try {
/*  657 */         this.next = this.dataInput.readBoolean();
/*      */       }
/*      */       catch (EOFException eof) {
/*  660 */         this.endOfProcess = true;
/*      */       } catch (IOException rethrow) {
/*  662 */         throw new RuntimeException(rethrow);
/*  663 */       }return !this.endOfProcess;
/*      */     }
/*      */     public boolean nextBoolean() {
/*  666 */       if (!hasNext()) throw new NoSuchElementException();
/*  667 */       this.toAdvance = true;
/*  668 */       return this.next;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.BinIO
 * JD-Core Version:    0.6.2
 */