/*    1:     */package it.unimi.dsi.fastutil.io;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.BigArrays;
/*    4:     */import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*    5:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
/*    7:     */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*    8:     */import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*    9:     */import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*   10:     */import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
/*   11:     */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*   12:     */import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*   13:     */import it.unimi.dsi.fastutil.chars.CharArrays;
/*   14:     */import it.unimi.dsi.fastutil.chars.CharBigArrays;
/*   15:     */import it.unimi.dsi.fastutil.chars.CharIterator;
/*   16:     */import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*   17:     */import it.unimi.dsi.fastutil.doubles.DoubleArrays;
/*   18:     */import it.unimi.dsi.fastutil.doubles.DoubleBigArrays;
/*   19:     */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   20:     */import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*   21:     */import it.unimi.dsi.fastutil.floats.FloatArrays;
/*   22:     */import it.unimi.dsi.fastutil.floats.FloatBigArrays;
/*   23:     */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   24:     */import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*   25:     */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   26:     */import it.unimi.dsi.fastutil.ints.IntBigArrays;
/*   27:     */import it.unimi.dsi.fastutil.ints.IntIterator;
/*   28:     */import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*   29:     */import it.unimi.dsi.fastutil.longs.LongArrays;
/*   30:     */import it.unimi.dsi.fastutil.longs.LongBigArrays;
/*   31:     */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   32:     */import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*   33:     */import it.unimi.dsi.fastutil.shorts.ShortArrays;
/*   34:     */import it.unimi.dsi.fastutil.shorts.ShortBigArrays;
/*   35:     */import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*   36:     */import java.io.DataInput;
/*   37:     */import java.io.DataInputStream;
/*   38:     */import java.io.DataOutput;
/*   39:     */import java.io.DataOutputStream;
/*   40:     */import java.io.EOFException;
/*   41:     */import java.io.File;
/*   42:     */import java.io.FileInputStream;
/*   43:     */import java.io.FileOutputStream;
/*   44:     */import java.io.IOException;
/*   45:     */import java.io.InputStream;
/*   46:     */import java.io.ObjectInputStream;
/*   47:     */import java.io.ObjectOutputStream;
/*   48:     */import java.io.OutputStream;
/*   49:     */import java.nio.channels.FileChannel;
/*   50:     */import java.util.NoSuchElementException;
/*   51:     */
/*   97:     */public class BinIO
/*   98:     */{
/*   99:     */  private static final int MAX_IO_LENGTH = 1048576;
/*  100:     */  
/*  101:     */  public static void storeObject(Object o, File file)
/*  102:     */    throws IOException
/*  103:     */  {
/*  104: 104 */    ObjectOutputStream oos = new ObjectOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  105: 105 */    oos.writeObject(o);
/*  106: 106 */    oos.close();
/*  107:     */  }
/*  108:     */  
/*  112:     */  public static void storeObject(Object o, CharSequence filename)
/*  113:     */    throws IOException
/*  114:     */  {
/*  115: 115 */    storeObject(o, new File(filename.toString()));
/*  116:     */  }
/*  117:     */  
/*  121:     */  public static Object loadObject(File file)
/*  122:     */    throws IOException, ClassNotFoundException
/*  123:     */  {
/*  124: 124 */    ObjectInputStream ois = new ObjectInputStream(new FastBufferedInputStream(new FileInputStream(file)));
/*  125: 125 */    Object result = ois.readObject();
/*  126: 126 */    ois.close();
/*  127: 127 */    return result;
/*  128:     */  }
/*  129:     */  
/*  133:     */  public static Object loadObject(CharSequence filename)
/*  134:     */    throws IOException, ClassNotFoundException
/*  135:     */  {
/*  136: 136 */    return loadObject(new File(filename.toString()));
/*  137:     */  }
/*  138:     */  
/*  145:     */  public static void storeObject(Object o, OutputStream s)
/*  146:     */    throws IOException
/*  147:     */  {
/*  148: 148 */    ObjectOutputStream oos = new ObjectOutputStream(new FastBufferedOutputStream(s));
/*  149: 149 */    oos.writeObject(o);
/*  150: 150 */    oos.flush();
/*  151:     */  }
/*  152:     */  
/*  162:     */  public static Object loadObject(InputStream s)
/*  163:     */    throws IOException, ClassNotFoundException
/*  164:     */  {
/*  165: 165 */    ObjectInputStream ois = new ObjectInputStream(new FastBufferedInputStream(s));
/*  166: 166 */    Object result = ois.readObject();
/*  167: 167 */    return result;
/*  168:     */  }
/*  169:     */  
/*  215:     */  public static int loadBooleans(DataInput dataInput, boolean[] array, int offset, int length)
/*  216:     */    throws IOException
/*  217:     */  {
/*  218: 218 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/*  219: 219 */    int i = 0;
/*  220:     */    try {
/*  221: 221 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readBoolean();
/*  222:     */    }
/*  223:     */    catch (EOFException itsOk) {}
/*  224: 224 */    return i;
/*  225:     */  }
/*  226:     */  
/*  230:     */  public static int loadBooleans(DataInput dataInput, boolean[] array)
/*  231:     */    throws IOException
/*  232:     */  {
/*  233: 233 */    int i = 0;
/*  234:     */    try {
/*  235: 235 */      int length = array.length;
/*  236: 236 */      for (i = 0; i < length; i++) array[i] = dataInput.readBoolean();
/*  237:     */    }
/*  238:     */    catch (EOFException itsOk) {}
/*  239: 239 */    return i;
/*  240:     */  }
/*  241:     */  
/*  247:     */  public static int loadBooleans(File file, boolean[] array, int offset, int length)
/*  248:     */    throws IOException
/*  249:     */  {
/*  250: 250 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/*  251: 251 */    FileInputStream fis = new FileInputStream(file);
/*  252: 252 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  253: 253 */    int i = 0;
/*  254:     */    try {
/*  255: 255 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readBoolean();
/*  256:     */    }
/*  257:     */    catch (EOFException itsOk) {}
/*  258: 258 */    dis.close();
/*  259: 259 */    return i;
/*  260:     */  }
/*  261:     */  
/*  267:     */  public static int loadBooleans(CharSequence filename, boolean[] array, int offset, int length)
/*  268:     */    throws IOException
/*  269:     */  {
/*  270: 270 */    return loadBooleans(new File(filename.toString()), array, offset, length);
/*  271:     */  }
/*  272:     */  
/*  276:     */  public static int loadBooleans(File file, boolean[] array)
/*  277:     */    throws IOException
/*  278:     */  {
/*  279: 279 */    FileInputStream fis = new FileInputStream(file);
/*  280: 280 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  281: 281 */    int i = 0;
/*  282:     */    try {
/*  283: 283 */      int length = array.length;
/*  284: 284 */      for (i = 0; i < length; i++) array[i] = dis.readBoolean();
/*  285:     */    }
/*  286:     */    catch (EOFException itsOk) {}
/*  287: 287 */    dis.close();
/*  288: 288 */    return i;
/*  289:     */  }
/*  290:     */  
/*  294:     */  public static int loadBooleans(CharSequence filename, boolean[] array)
/*  295:     */    throws IOException
/*  296:     */  {
/*  297: 297 */    return loadBooleans(new File(filename.toString()), array);
/*  298:     */  }
/*  299:     */  
/*  306:     */  public static boolean[] loadBooleans(File file)
/*  307:     */    throws IOException
/*  308:     */  {
/*  309: 309 */    FileInputStream fis = new FileInputStream(file);
/*  310: 310 */    long length = fis.getChannel().size();
/*  311: 311 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/*  312: 312 */    boolean[] array = new boolean[(int)length];
/*  313: 313 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  314: 314 */    for (int i = 0; i < length; i++) array[i] = dis.readBoolean();
/*  315: 315 */    dis.close();
/*  316: 316 */    return array;
/*  317:     */  }
/*  318:     */  
/*  325:     */  public static boolean[] loadBooleans(CharSequence filename)
/*  326:     */    throws IOException
/*  327:     */  {
/*  328: 328 */    return loadBooleans(new File(filename.toString()));
/*  329:     */  }
/*  330:     */  
/*  335:     */  public static void storeBooleans(boolean[] array, int offset, int length, DataOutput dataOutput)
/*  336:     */    throws IOException
/*  337:     */  {
/*  338: 338 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/*  339: 339 */    for (int i = 0; i < length; i++) { dataOutput.writeBoolean(array[(offset + i)]);
/*  340:     */    }
/*  341:     */  }
/*  342:     */  
/*  344:     */  public static void storeBooleans(boolean[] array, DataOutput dataOutput)
/*  345:     */    throws IOException
/*  346:     */  {
/*  347: 347 */    int length = array.length;
/*  348: 348 */    for (int i = 0; i < length; i++) { dataOutput.writeBoolean(array[i]);
/*  349:     */    }
/*  350:     */  }
/*  351:     */  
/*  355:     */  public static void storeBooleans(boolean[] array, int offset, int length, File file)
/*  356:     */    throws IOException
/*  357:     */  {
/*  358: 358 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/*  359: 359 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  360: 360 */    for (int i = 0; i < length; i++) dos.writeBoolean(array[(offset + i)]);
/*  361: 361 */    dos.close();
/*  362:     */  }
/*  363:     */  
/*  368:     */  public static void storeBooleans(boolean[] array, int offset, int length, CharSequence filename)
/*  369:     */    throws IOException
/*  370:     */  {
/*  371: 371 */    storeBooleans(array, offset, length, new File(filename.toString()));
/*  372:     */  }
/*  373:     */  
/*  376:     */  public static void storeBooleans(boolean[] array, File file)
/*  377:     */    throws IOException
/*  378:     */  {
/*  379: 379 */    int length = array.length;
/*  380: 380 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  381: 381 */    for (int i = 0; i < length; i++) dos.writeBoolean(array[i]);
/*  382: 382 */    dos.close();
/*  383:     */  }
/*  384:     */  
/*  387:     */  public static void storeBooleans(boolean[] array, CharSequence filename)
/*  388:     */    throws IOException
/*  389:     */  {
/*  390: 390 */    storeBooleans(array, new File(filename.toString()));
/*  391:     */  }
/*  392:     */  
/*  398:     */  public static long loadBooleans(DataInput dataInput, boolean[][] array, long offset, long length)
/*  399:     */    throws IOException
/*  400:     */  {
/*  401: 401 */    BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  402: 402 */    long c = 0L;
/*  403:     */    try {
/*  404: 404 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  405: 405 */        boolean[] t = array[i];
/*  406: 406 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  407: 407 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/*  408: 408 */          t[d] = dataInput.readBoolean();
/*  409: 409 */          c += 1L;
/*  410:     */        }
/*  411:     */      }
/*  412:     */    }
/*  413:     */    catch (EOFException itsOk) {}
/*  414: 414 */    return c;
/*  415:     */  }
/*  416:     */  
/*  420:     */  public static long loadBooleans(DataInput dataInput, boolean[][] array)
/*  421:     */    throws IOException
/*  422:     */  {
/*  423: 423 */    long c = 0L;
/*  424:     */    try {
/*  425: 425 */      for (int i = 0; i < array.length; i++) {
/*  426: 426 */        boolean[] t = array[i];
/*  427: 427 */        int l = t.length;
/*  428: 428 */        for (int d = 0; d < l; d++) {
/*  429: 429 */          t[d] = dataInput.readBoolean();
/*  430: 430 */          c += 1L;
/*  431:     */        }
/*  432:     */      }
/*  433:     */    }
/*  434:     */    catch (EOFException itsOk) {}
/*  435: 435 */    return c;
/*  436:     */  }
/*  437:     */  
/*  443:     */  public static long loadBooleans(File file, boolean[][] array, long offset, long length)
/*  444:     */    throws IOException
/*  445:     */  {
/*  446: 446 */    BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  447: 447 */    FileInputStream fis = new FileInputStream(file);
/*  448: 448 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  449: 449 */    long c = 0L;
/*  450:     */    try {
/*  451: 451 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  452: 452 */        boolean[] t = array[i];
/*  453: 453 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  454: 454 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/*  455: 455 */          t[d] = dis.readBoolean();
/*  456: 456 */          c += 1L;
/*  457:     */        }
/*  458:     */      }
/*  459:     */    }
/*  460:     */    catch (EOFException itsOk) {}
/*  461: 461 */    dis.close();
/*  462: 462 */    return c;
/*  463:     */  }
/*  464:     */  
/*  470:     */  public static long loadBooleans(CharSequence filename, boolean[][] array, long offset, long length)
/*  471:     */    throws IOException
/*  472:     */  {
/*  473: 473 */    return loadBooleans(new File(filename.toString()), array, offset, length);
/*  474:     */  }
/*  475:     */  
/*  479:     */  public static long loadBooleans(File file, boolean[][] array)
/*  480:     */    throws IOException
/*  481:     */  {
/*  482: 482 */    FileInputStream fis = new FileInputStream(file);
/*  483: 483 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  484: 484 */    long c = 0L;
/*  485:     */    try {
/*  486: 486 */      for (int i = 0; i < array.length; i++) {
/*  487: 487 */        boolean[] t = array[i];
/*  488: 488 */        int l = t.length;
/*  489: 489 */        for (int d = 0; d < l; d++) {
/*  490: 490 */          t[d] = dis.readBoolean();
/*  491: 491 */          c += 1L;
/*  492:     */        }
/*  493:     */      }
/*  494:     */    }
/*  495:     */    catch (EOFException itsOk) {}
/*  496: 496 */    dis.close();
/*  497: 497 */    return c;
/*  498:     */  }
/*  499:     */  
/*  503:     */  public static long loadBooleans(CharSequence filename, boolean[][] array)
/*  504:     */    throws IOException
/*  505:     */  {
/*  506: 506 */    return loadBooleans(new File(filename.toString()), array);
/*  507:     */  }
/*  508:     */  
/*  515:     */  public static boolean[][] loadBooleansBig(File file)
/*  516:     */    throws IOException
/*  517:     */  {
/*  518: 518 */    FileInputStream fis = new FileInputStream(file);
/*  519: 519 */    long length = fis.getChannel().size();
/*  520: 520 */    boolean[][] array = BooleanBigArrays.newBigArray(length);
/*  521: 521 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/*  522: 522 */    for (int i = 0; i < array.length; i++) {
/*  523: 523 */      boolean[] t = array[i];
/*  524: 524 */      int l = t.length;
/*  525: 525 */      for (int d = 0; d < l; d++) t[d] = dis.readBoolean();
/*  526:     */    }
/*  527: 527 */    dis.close();
/*  528: 528 */    return array;
/*  529:     */  }
/*  530:     */  
/*  537:     */  public static boolean[][] loadBooleansBig(CharSequence filename)
/*  538:     */    throws IOException
/*  539:     */  {
/*  540: 540 */    return loadBooleansBig(new File(filename.toString()));
/*  541:     */  }
/*  542:     */  
/*  547:     */  public static void storeBooleans(boolean[][] array, long offset, long length, DataOutput dataOutput)
/*  548:     */    throws IOException
/*  549:     */  {
/*  550: 550 */    BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  551: 551 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  552: 552 */      boolean[] t = array[i];
/*  553: 553 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  554: 554 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeBoolean(t[d]);
/*  555:     */      }
/*  556:     */    }
/*  557:     */  }
/*  558:     */  
/*  560:     */  public static void storeBooleans(boolean[][] array, DataOutput dataOutput)
/*  561:     */    throws IOException
/*  562:     */  {
/*  563: 563 */    for (int i = 0; i < array.length; i++) {
/*  564: 564 */      boolean[] t = array[i];
/*  565: 565 */      int l = t.length;
/*  566: 566 */      for (int d = 0; d < l; d++) { dataOutput.writeBoolean(t[d]);
/*  567:     */      }
/*  568:     */    }
/*  569:     */  }
/*  570:     */  
/*  574:     */  public static void storeBooleans(boolean[][] array, long offset, long length, File file)
/*  575:     */    throws IOException
/*  576:     */  {
/*  577: 577 */    BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  578: 578 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  579: 579 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  580: 580 */      boolean[] t = array[i];
/*  581: 581 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  582: 582 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeBoolean(t[d]);
/*  583:     */    }
/*  584: 584 */    dos.close();
/*  585:     */  }
/*  586:     */  
/*  591:     */  public static void storeBooleans(boolean[][] array, long offset, long length, CharSequence filename)
/*  592:     */    throws IOException
/*  593:     */  {
/*  594: 594 */    storeBooleans(array, offset, length, new File(filename.toString()));
/*  595:     */  }
/*  596:     */  
/*  599:     */  public static void storeBooleans(boolean[][] array, File file)
/*  600:     */    throws IOException
/*  601:     */  {
/*  602: 602 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  603: 603 */    for (int i = 0; i < array.length; i++) {
/*  604: 604 */      boolean[] t = array[i];
/*  605: 605 */      int l = t.length;
/*  606: 606 */      for (int d = 0; d < l; d++) dos.writeBoolean(t[d]);
/*  607:     */    }
/*  608: 608 */    dos.close();
/*  609:     */  }
/*  610:     */  
/*  613:     */  public static void storeBooleans(boolean[][] array, CharSequence filename)
/*  614:     */    throws IOException
/*  615:     */  {
/*  616: 616 */    storeBooleans(array, new File(filename.toString()));
/*  617:     */  }
/*  618:     */  
/*  621:     */  public static void storeBooleans(BooleanIterator i, DataOutput dataOutput)
/*  622:     */    throws IOException
/*  623:     */  {
/*  624: 624 */    while (i.hasNext()) { dataOutput.writeBoolean(i.nextBoolean());
/*  625:     */    }
/*  626:     */  }
/*  627:     */  
/*  629:     */  public static void storeBooleans(BooleanIterator i, File file)
/*  630:     */    throws IOException
/*  631:     */  {
/*  632: 632 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  633: 633 */    while (i.hasNext()) dos.writeBoolean(i.nextBoolean());
/*  634: 634 */    dos.close();
/*  635:     */  }
/*  636:     */  
/*  639:     */  public static void storeBooleans(BooleanIterator i, CharSequence filename)
/*  640:     */    throws IOException
/*  641:     */  {
/*  642: 642 */    storeBooleans(i, new File(filename.toString()));
/*  643:     */  }
/*  644:     */  
/*  645:     */  private static final class BooleanDataInputWrapper extends AbstractBooleanIterator {
/*  646:     */    private final DataInput dataInput;
/*  647: 647 */    private boolean toAdvance = true;
/*  648: 648 */    private boolean endOfProcess = false;
/*  649:     */    private boolean next;
/*  650:     */    
/*  651: 651 */    public BooleanDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/*  652:     */    
/*  653:     */    public boolean hasNext() {
/*  654: 654 */      if (!this.toAdvance) return !this.endOfProcess;
/*  655: 655 */      this.toAdvance = false;
/*  656:     */      try {
/*  657: 657 */        this.next = this.dataInput.readBoolean();
/*  658:     */      }
/*  659:     */      catch (EOFException eof) {
/*  660: 660 */        this.endOfProcess = true;
/*  661:     */      } catch (IOException rethrow) {
/*  662: 662 */        throw new RuntimeException(rethrow); }
/*  663: 663 */      return !this.endOfProcess;
/*  664:     */    }
/*  665:     */    
/*  666: 666 */    public boolean nextBoolean() { if (!hasNext()) throw new NoSuchElementException();
/*  667: 667 */      this.toAdvance = true;
/*  668: 668 */      return this.next;
/*  669:     */    }
/*  670:     */  }
/*  671:     */  
/*  674:     */  public static BooleanIterator asBooleanIterator(DataInput dataInput)
/*  675:     */  {
/*  676: 676 */    return new BooleanDataInputWrapper(dataInput);
/*  677:     */  }
/*  678:     */  
/*  680:     */  public static BooleanIterator asBooleanIterator(File file)
/*  681:     */    throws IOException
/*  682:     */  {
/*  683: 683 */    return new BooleanDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/*  684:     */  }
/*  685:     */  
/*  687:     */  public static BooleanIterator asBooleanIterator(CharSequence filename)
/*  688:     */    throws IOException
/*  689:     */  {
/*  690: 690 */    return asBooleanIterator(new File(filename.toString()));
/*  691:     */  }
/*  692:     */  
/*  729:     */  private static int read(InputStream is, byte[] a, int offset, int length)
/*  730:     */    throws IOException
/*  731:     */  {
/*  732: 732 */    if (length == 0) return 0;
/*  733: 733 */    int read = 0;
/*  734:     */    do {
/*  735: 735 */      int result = is.read(a, offset + read, Math.min(length - read, 1048576));
/*  736: 736 */      if (result < 0) return read;
/*  737: 737 */      read += result;
/*  738: 738 */    } while (read < length);
/*  739: 739 */    return read;
/*  740:     */  }
/*  741:     */  
/*  742: 742 */  private static void write(OutputStream outputStream, byte[] a, int offset, int length) throws IOException { int written = 0;
/*  743: 743 */    while (written < length) {
/*  744: 744 */      outputStream.write(a, offset + written, Math.min(length - written, 1048576));
/*  745: 745 */      written += Math.min(length - written, 1048576);
/*  746:     */    }
/*  747:     */  }
/*  748:     */  
/*  749: 749 */  private static void write(DataOutput dataOutput, byte[] a, int offset, int length) throws IOException { int written = 0;
/*  750: 750 */    while (written < length) {
/*  751: 751 */      dataOutput.write(a, offset + written, Math.min(length - written, 1048576));
/*  752: 752 */      written += Math.min(length - written, 1048576);
/*  753:     */    }
/*  754:     */  }
/*  755:     */  
/*  765:     */  public static int loadBytes(InputStream inputStream, byte[] array, int offset, int length)
/*  766:     */    throws IOException
/*  767:     */  {
/*  768: 768 */    return read(inputStream, array, offset, length);
/*  769:     */  }
/*  770:     */  
/*  777:     */  public static int loadBytes(InputStream inputStream, byte[] array)
/*  778:     */    throws IOException
/*  779:     */  {
/*  780: 780 */    return read(inputStream, array, 0, array.length);
/*  781:     */  }
/*  782:     */  
/*  790:     */  public static void storeBytes(byte[] array, int offset, int length, OutputStream outputStream)
/*  791:     */    throws IOException
/*  792:     */  {
/*  793: 793 */    write(outputStream, array, offset, length);
/*  794:     */  }
/*  795:     */  
/*  803: 804 */  public static void storeBytes(byte[] array, OutputStream outputStream)
/*  804: 804 */    throws IOException { write(outputStream, array, 0, array.length); }
/*  805:     */  
/*  806:     */  private static long read(InputStream is, byte[][] a, long offset, long length) throws IOException {
/*  807: 807 */    if (length == 0L) return 0L;
/*  808: 808 */    long read = 0L;
/*  809: 809 */    int segment = BigArrays.segment(offset);
/*  810: 810 */    int displacement = BigArrays.displacement(offset);
/*  811:     */    do
/*  812:     */    {
/*  813: 813 */      int result = is.read(a[segment], displacement, (int)Math.min(a[segment].length - displacement, Math.min(length - read, 1048576L)));
/*  814: 814 */      if (result < 0) return read;
/*  815: 815 */      read += result;
/*  816: 816 */      displacement += result;
/*  817: 817 */      if (displacement == a[segment].length) {
/*  818: 818 */        segment++;
/*  819: 819 */        displacement = 0;
/*  820:     */      }
/*  821: 821 */    } while (read < length);
/*  822: 822 */    return read;
/*  823:     */  }
/*  824:     */  
/*  825: 825 */  private static void write(OutputStream outputStream, byte[][] a, long offset, long length) throws IOException { if (length == 0L) return;
/*  826: 826 */    long written = 0L;
/*  827:     */    
/*  828: 828 */    int segment = BigArrays.segment(offset);
/*  829: 829 */    int displacement = BigArrays.displacement(offset);
/*  830:     */    do {
/*  831: 831 */      int toWrite = (int)Math.min(a[segment].length - displacement, Math.min(length - written, 1048576L));
/*  832: 832 */      outputStream.write(a[segment], displacement, toWrite);
/*  833: 833 */      written += toWrite;
/*  834: 834 */      displacement += toWrite;
/*  835: 835 */      if (displacement == a[segment].length) {
/*  836: 836 */        segment++;
/*  837: 837 */        displacement = 0;
/*  838:     */      }
/*  839: 839 */    } while (written < length);
/*  840:     */  }
/*  841:     */  
/*  842: 842 */  private static void write(DataOutput dataOutput, byte[][] a, long offset, long length) throws IOException { if (length == 0L) return;
/*  843: 843 */    long written = 0L;
/*  844:     */    
/*  845: 845 */    int segment = BigArrays.segment(offset);
/*  846: 846 */    int displacement = BigArrays.displacement(offset);
/*  847:     */    do {
/*  848: 848 */      int toWrite = (int)Math.min(a[segment].length - displacement, Math.min(length - written, 1048576L));
/*  849: 849 */      dataOutput.write(a[segment], displacement, toWrite);
/*  850: 850 */      written += toWrite;
/*  851: 851 */      displacement += toWrite;
/*  852: 852 */      if (displacement == a[segment].length) {
/*  853: 853 */        segment++;
/*  854: 854 */        displacement = 0;
/*  855:     */      }
/*  856: 856 */    } while (written < length);
/*  857:     */  }
/*  858:     */  
/*  868:     */  public static long loadBytes(InputStream inputStream, byte[][] array, long offset, long length)
/*  869:     */    throws IOException
/*  870:     */  {
/*  871: 871 */    return read(inputStream, array, offset, length);
/*  872:     */  }
/*  873:     */  
/*  880:     */  public static long loadBytes(InputStream inputStream, byte[][] array)
/*  881:     */    throws IOException
/*  882:     */  {
/*  883: 883 */    return read(inputStream, array, 0L, ByteBigArrays.length(array));
/*  884:     */  }
/*  885:     */  
/*  893:     */  public static void storeBytes(byte[][] array, long offset, long length, OutputStream outputStream)
/*  894:     */    throws IOException
/*  895:     */  {
/*  896: 896 */    write(outputStream, array, offset, length);
/*  897:     */  }
/*  898:     */  
/*  904:     */  public static void storeBytes(byte[][] array, OutputStream outputStream)
/*  905:     */    throws IOException
/*  906:     */  {
/*  907: 907 */    write(outputStream, array, 0L, ByteBigArrays.length(array));
/*  908:     */  }
/*  909:     */  
/*  915:     */  public static int loadBytes(DataInput dataInput, byte[] array, int offset, int length)
/*  916:     */    throws IOException
/*  917:     */  {
/*  918: 918 */    ByteArrays.ensureOffsetLength(array, offset, length);
/*  919: 919 */    int i = 0;
/*  920:     */    try {
/*  921: 921 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readByte();
/*  922:     */    }
/*  923:     */    catch (EOFException itsOk) {}
/*  924: 924 */    return i;
/*  925:     */  }
/*  926:     */  
/*  930:     */  public static int loadBytes(DataInput dataInput, byte[] array)
/*  931:     */    throws IOException
/*  932:     */  {
/*  933: 933 */    int i = 0;
/*  934:     */    try {
/*  935: 935 */      int length = array.length;
/*  936: 936 */      for (i = 0; i < length; i++) array[i] = dataInput.readByte();
/*  937:     */    }
/*  938:     */    catch (EOFException itsOk) {}
/*  939: 939 */    return i;
/*  940:     */  }
/*  941:     */  
/*  947:     */  public static int loadBytes(File file, byte[] array, int offset, int length)
/*  948:     */    throws IOException
/*  949:     */  {
/*  950: 950 */    ByteArrays.ensureOffsetLength(array, offset, length);
/*  951: 951 */    FileInputStream fis = new FileInputStream(file);
/*  952: 952 */    int result = read(fis, array, offset, length);
/*  953: 953 */    fis.close();
/*  954: 954 */    return result;
/*  955:     */  }
/*  956:     */  
/*  962:     */  public static int loadBytes(CharSequence filename, byte[] array, int offset, int length)
/*  963:     */    throws IOException
/*  964:     */  {
/*  965: 965 */    return loadBytes(new File(filename.toString()), array, offset, length);
/*  966:     */  }
/*  967:     */  
/*  971:     */  public static int loadBytes(File file, byte[] array)
/*  972:     */    throws IOException
/*  973:     */  {
/*  974: 974 */    FileInputStream fis = new FileInputStream(file);
/*  975: 975 */    int result = read(fis, array, 0, array.length);
/*  976: 976 */    fis.close();
/*  977: 977 */    return result;
/*  978:     */  }
/*  979:     */  
/*  983:     */  public static int loadBytes(CharSequence filename, byte[] array)
/*  984:     */    throws IOException
/*  985:     */  {
/*  986: 986 */    return loadBytes(new File(filename.toString()), array);
/*  987:     */  }
/*  988:     */  
/*  995:     */  public static byte[] loadBytes(File file)
/*  996:     */    throws IOException
/*  997:     */  {
/*  998: 998 */    FileInputStream fis = new FileInputStream(file);
/*  999: 999 */    long length = fis.getChannel().size() / 1L;
/* 1000:1000 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 1001:1001 */    byte[] array = new byte[(int)length];
/* 1002:1002 */    if (read(fis, array, 0, (int)length) < length) throw new EOFException();
/* 1003:1003 */    fis.close();
/* 1004:1004 */    return array;
/* 1005:     */  }
/* 1006:     */  
/* 1013:     */  public static byte[] loadBytes(CharSequence filename)
/* 1014:     */    throws IOException
/* 1015:     */  {
/* 1016:1016 */    return loadBytes(new File(filename.toString()));
/* 1017:     */  }
/* 1018:     */  
/* 1023:     */  public static void storeBytes(byte[] array, int offset, int length, DataOutput dataOutput)
/* 1024:     */    throws IOException
/* 1025:     */  {
/* 1026:1026 */    ByteArrays.ensureOffsetLength(array, offset, length);
/* 1027:1027 */    write(dataOutput, array, offset, length);
/* 1028:     */  }
/* 1029:     */  
/* 1032:     */  public static void storeBytes(byte[] array, DataOutput dataOutput)
/* 1033:     */    throws IOException
/* 1034:     */  {
/* 1035:1035 */    write(dataOutput, array, 0, array.length);
/* 1036:     */  }
/* 1037:     */  
/* 1042:     */  public static void storeBytes(byte[] array, int offset, int length, File file)
/* 1043:     */    throws IOException
/* 1044:     */  {
/* 1045:1045 */    ByteArrays.ensureOffsetLength(array, offset, length);
/* 1046:1046 */    OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1047:1047 */    write(os, array, offset, length);
/* 1048:1048 */    os.close();
/* 1049:     */  }
/* 1050:     */  
/* 1055:     */  public static void storeBytes(byte[] array, int offset, int length, CharSequence filename)
/* 1056:     */    throws IOException
/* 1057:     */  {
/* 1058:1058 */    storeBytes(array, offset, length, new File(filename.toString()));
/* 1059:     */  }
/* 1060:     */  
/* 1063:     */  public static void storeBytes(byte[] array, File file)
/* 1064:     */    throws IOException
/* 1065:     */  {
/* 1066:1066 */    OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1067:1067 */    write(os, array, 0, array.length);
/* 1068:1068 */    os.close();
/* 1069:     */  }
/* 1070:     */  
/* 1073:     */  public static void storeBytes(byte[] array, CharSequence filename)
/* 1074:     */    throws IOException
/* 1075:     */  {
/* 1076:1076 */    storeBytes(array, new File(filename.toString()));
/* 1077:     */  }
/* 1078:     */  
/* 1084:     */  public static long loadBytes(DataInput dataInput, byte[][] array, long offset, long length)
/* 1085:     */    throws IOException
/* 1086:     */  {
/* 1087:1087 */    ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1088:1088 */    long c = 0L;
/* 1089:     */    try {
/* 1090:1090 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1091:1091 */        byte[] t = array[i];
/* 1092:1092 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1093:1093 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 1094:1094 */          t[d] = dataInput.readByte();
/* 1095:1095 */          c += 1L;
/* 1096:     */        }
/* 1097:     */      }
/* 1098:     */    }
/* 1099:     */    catch (EOFException itsOk) {}
/* 1100:1100 */    return c;
/* 1101:     */  }
/* 1102:     */  
/* 1106:     */  public static long loadBytes(DataInput dataInput, byte[][] array)
/* 1107:     */    throws IOException
/* 1108:     */  {
/* 1109:1109 */    long c = 0L;
/* 1110:     */    try {
/* 1111:1111 */      for (int i = 0; i < array.length; i++) {
/* 1112:1112 */        byte[] t = array[i];
/* 1113:1113 */        int l = t.length;
/* 1114:1114 */        for (int d = 0; d < l; d++) {
/* 1115:1115 */          t[d] = dataInput.readByte();
/* 1116:1116 */          c += 1L;
/* 1117:     */        }
/* 1118:     */      }
/* 1119:     */    }
/* 1120:     */    catch (EOFException itsOk) {}
/* 1121:1121 */    return c;
/* 1122:     */  }
/* 1123:     */  
/* 1129:     */  public static long loadBytes(File file, byte[][] array, long offset, long length)
/* 1130:     */    throws IOException
/* 1131:     */  {
/* 1132:1132 */    ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1133:1133 */    FileInputStream fis = new FileInputStream(file);
/* 1134:1134 */    long result = read(fis, array, offset, length);
/* 1135:1135 */    fis.close();
/* 1136:1136 */    return result;
/* 1137:     */  }
/* 1138:     */  
/* 1144:     */  public static long loadBytes(CharSequence filename, byte[][] array, long offset, long length)
/* 1145:     */    throws IOException
/* 1146:     */  {
/* 1147:1147 */    return loadBytes(new File(filename.toString()), array, offset, length);
/* 1148:     */  }
/* 1149:     */  
/* 1153:     */  public static long loadBytes(File file, byte[][] array)
/* 1154:     */    throws IOException
/* 1155:     */  {
/* 1156:1156 */    FileInputStream fis = new FileInputStream(file);
/* 1157:1157 */    long result = read(fis, array, 0L, ByteBigArrays.length(array));
/* 1158:1158 */    fis.close();
/* 1159:1159 */    return result;
/* 1160:     */  }
/* 1161:     */  
/* 1165:     */  public static long loadBytes(CharSequence filename, byte[][] array)
/* 1166:     */    throws IOException
/* 1167:     */  {
/* 1168:1168 */    return loadBytes(new File(filename.toString()), array);
/* 1169:     */  }
/* 1170:     */  
/* 1177:     */  public static byte[][] loadBytesBig(File file)
/* 1178:     */    throws IOException
/* 1179:     */  {
/* 1180:1180 */    FileInputStream fis = new FileInputStream(file);
/* 1181:1181 */    long length = fis.getChannel().size() / 1L;
/* 1182:1182 */    byte[][] array = ByteBigArrays.newBigArray(length);
/* 1183:1183 */    if (read(fis, array, 0L, length) < length) throw new EOFException();
/* 1184:1184 */    fis.close();
/* 1185:1185 */    return array;
/* 1186:     */  }
/* 1187:     */  
/* 1194:     */  public static byte[][] loadBytesBig(CharSequence filename)
/* 1195:     */    throws IOException
/* 1196:     */  {
/* 1197:1197 */    return loadBytesBig(new File(filename.toString()));
/* 1198:     */  }
/* 1199:     */  
/* 1204:     */  public static void storeBytes(byte[][] array, long offset, long length, DataOutput dataOutput)
/* 1205:     */    throws IOException
/* 1206:     */  {
/* 1207:1207 */    ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1208:1208 */    write(dataOutput, array, offset, length);
/* 1209:     */  }
/* 1210:     */  
/* 1213:     */  public static void storeBytes(byte[][] array, DataOutput dataOutput)
/* 1214:     */    throws IOException
/* 1215:     */  {
/* 1216:1216 */    write(dataOutput, array, 0L, ByteBigArrays.length(array));
/* 1217:     */  }
/* 1218:     */  
/* 1223:     */  public static void storeBytes(byte[][] array, long offset, long length, File file)
/* 1224:     */    throws IOException
/* 1225:     */  {
/* 1226:1226 */    ByteBigArrays.ensureOffsetLength(array, offset, length);
/* 1227:1227 */    OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1228:1228 */    write(os, array, offset, length);
/* 1229:1229 */    os.close();
/* 1230:     */  }
/* 1231:     */  
/* 1236:     */  public static void storeBytes(byte[][] array, long offset, long length, CharSequence filename)
/* 1237:     */    throws IOException
/* 1238:     */  {
/* 1239:1239 */    storeBytes(array, offset, length, new File(filename.toString()));
/* 1240:     */  }
/* 1241:     */  
/* 1244:     */  public static void storeBytes(byte[][] array, File file)
/* 1245:     */    throws IOException
/* 1246:     */  {
/* 1247:1247 */    OutputStream os = new FastBufferedOutputStream(new FileOutputStream(file));
/* 1248:1248 */    write(os, array, 0L, ByteBigArrays.length(array));
/* 1249:1249 */    os.close();
/* 1250:     */  }
/* 1251:     */  
/* 1254:     */  public static void storeBytes(byte[][] array, CharSequence filename)
/* 1255:     */    throws IOException
/* 1256:     */  {
/* 1257:1257 */    storeBytes(array, new File(filename.toString()));
/* 1258:     */  }
/* 1259:     */  
/* 1262:     */  public static void storeBytes(ByteIterator i, DataOutput dataOutput)
/* 1263:     */    throws IOException
/* 1264:     */  {
/* 1265:1265 */    while (i.hasNext()) { dataOutput.writeByte(i.nextByte());
/* 1266:     */    }
/* 1267:     */  }
/* 1268:     */  
/* 1270:     */  public static void storeBytes(ByteIterator i, File file)
/* 1271:     */    throws IOException
/* 1272:     */  {
/* 1273:1273 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1274:1274 */    while (i.hasNext()) dos.writeByte(i.nextByte());
/* 1275:1275 */    dos.close();
/* 1276:     */  }
/* 1277:     */  
/* 1280:     */  public static void storeBytes(ByteIterator i, CharSequence filename)
/* 1281:     */    throws IOException
/* 1282:     */  {
/* 1283:1283 */    storeBytes(i, new File(filename.toString()));
/* 1284:     */  }
/* 1285:     */  
/* 1286:     */  private static final class ByteDataInputWrapper extends AbstractByteIterator {
/* 1287:     */    private final DataInput dataInput;
/* 1288:1288 */    private boolean toAdvance = true;
/* 1289:1289 */    private boolean endOfProcess = false;
/* 1290:     */    private byte next;
/* 1291:     */    
/* 1292:1292 */    public ByteDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 1293:     */    
/* 1294:     */    public boolean hasNext() {
/* 1295:1295 */      if (!this.toAdvance) return !this.endOfProcess;
/* 1296:1296 */      this.toAdvance = false;
/* 1297:     */      try {
/* 1298:1298 */        this.next = this.dataInput.readByte();
/* 1299:     */      }
/* 1300:     */      catch (EOFException eof) {
/* 1301:1301 */        this.endOfProcess = true;
/* 1302:     */      } catch (IOException rethrow) {
/* 1303:1303 */        throw new RuntimeException(rethrow); }
/* 1304:1304 */      return !this.endOfProcess;
/* 1305:     */    }
/* 1306:     */    
/* 1307:1307 */    public byte nextByte() { if (!hasNext()) throw new NoSuchElementException();
/* 1308:1308 */      this.toAdvance = true;
/* 1309:1309 */      return this.next;
/* 1310:     */    }
/* 1311:     */  }
/* 1312:     */  
/* 1315:     */  public static ByteIterator asByteIterator(DataInput dataInput)
/* 1316:     */  {
/* 1317:1317 */    return new ByteDataInputWrapper(dataInput);
/* 1318:     */  }
/* 1319:     */  
/* 1321:     */  public static ByteIterator asByteIterator(File file)
/* 1322:     */    throws IOException
/* 1323:     */  {
/* 1324:1324 */    return new ByteDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 1325:     */  }
/* 1326:     */  
/* 1328:     */  public static ByteIterator asByteIterator(CharSequence filename)
/* 1329:     */    throws IOException
/* 1330:     */  {
/* 1331:1331 */    return asByteIterator(new File(filename.toString()));
/* 1332:     */  }
/* 1333:     */  
/* 1376:     */  public static int loadShorts(DataInput dataInput, short[] array, int offset, int length)
/* 1377:     */    throws IOException
/* 1378:     */  {
/* 1379:1379 */    ShortArrays.ensureOffsetLength(array, offset, length);
/* 1380:1380 */    int i = 0;
/* 1381:     */    try {
/* 1382:1382 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readShort();
/* 1383:     */    }
/* 1384:     */    catch (EOFException itsOk) {}
/* 1385:1385 */    return i;
/* 1386:     */  }
/* 1387:     */  
/* 1391:     */  public static int loadShorts(DataInput dataInput, short[] array)
/* 1392:     */    throws IOException
/* 1393:     */  {
/* 1394:1394 */    int i = 0;
/* 1395:     */    try {
/* 1396:1396 */      int length = array.length;
/* 1397:1397 */      for (i = 0; i < length; i++) array[i] = dataInput.readShort();
/* 1398:     */    }
/* 1399:     */    catch (EOFException itsOk) {}
/* 1400:1400 */    return i;
/* 1401:     */  }
/* 1402:     */  
/* 1408:     */  public static int loadShorts(File file, short[] array, int offset, int length)
/* 1409:     */    throws IOException
/* 1410:     */  {
/* 1411:1411 */    ShortArrays.ensureOffsetLength(array, offset, length);
/* 1412:1412 */    FileInputStream fis = new FileInputStream(file);
/* 1413:1413 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1414:1414 */    int i = 0;
/* 1415:     */    try {
/* 1416:1416 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readShort();
/* 1417:     */    }
/* 1418:     */    catch (EOFException itsOk) {}
/* 1419:1419 */    dis.close();
/* 1420:1420 */    return i;
/* 1421:     */  }
/* 1422:     */  
/* 1428:     */  public static int loadShorts(CharSequence filename, short[] array, int offset, int length)
/* 1429:     */    throws IOException
/* 1430:     */  {
/* 1431:1431 */    return loadShorts(new File(filename.toString()), array, offset, length);
/* 1432:     */  }
/* 1433:     */  
/* 1437:     */  public static int loadShorts(File file, short[] array)
/* 1438:     */    throws IOException
/* 1439:     */  {
/* 1440:1440 */    FileInputStream fis = new FileInputStream(file);
/* 1441:1441 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1442:1442 */    int i = 0;
/* 1443:     */    try {
/* 1444:1444 */      int length = array.length;
/* 1445:1445 */      for (i = 0; i < length; i++) array[i] = dis.readShort();
/* 1446:     */    }
/* 1447:     */    catch (EOFException itsOk) {}
/* 1448:1448 */    dis.close();
/* 1449:1449 */    return i;
/* 1450:     */  }
/* 1451:     */  
/* 1455:     */  public static int loadShorts(CharSequence filename, short[] array)
/* 1456:     */    throws IOException
/* 1457:     */  {
/* 1458:1458 */    return loadShorts(new File(filename.toString()), array);
/* 1459:     */  }
/* 1460:     */  
/* 1467:     */  public static short[] loadShorts(File file)
/* 1468:     */    throws IOException
/* 1469:     */  {
/* 1470:1470 */    FileInputStream fis = new FileInputStream(file);
/* 1471:1471 */    long length = fis.getChannel().size() / 2L;
/* 1472:1472 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 1473:1473 */    short[] array = new short[(int)length];
/* 1474:1474 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1475:1475 */    for (int i = 0; i < length; i++) array[i] = dis.readShort();
/* 1476:1476 */    dis.close();
/* 1477:1477 */    return array;
/* 1478:     */  }
/* 1479:     */  
/* 1486:     */  public static short[] loadShorts(CharSequence filename)
/* 1487:     */    throws IOException
/* 1488:     */  {
/* 1489:1489 */    return loadShorts(new File(filename.toString()));
/* 1490:     */  }
/* 1491:     */  
/* 1496:     */  public static void storeShorts(short[] array, int offset, int length, DataOutput dataOutput)
/* 1497:     */    throws IOException
/* 1498:     */  {
/* 1499:1499 */    ShortArrays.ensureOffsetLength(array, offset, length);
/* 1500:1500 */    for (int i = 0; i < length; i++) { dataOutput.writeShort(array[(offset + i)]);
/* 1501:     */    }
/* 1502:     */  }
/* 1503:     */  
/* 1505:     */  public static void storeShorts(short[] array, DataOutput dataOutput)
/* 1506:     */    throws IOException
/* 1507:     */  {
/* 1508:1508 */    int length = array.length;
/* 1509:1509 */    for (int i = 0; i < length; i++) { dataOutput.writeShort(array[i]);
/* 1510:     */    }
/* 1511:     */  }
/* 1512:     */  
/* 1516:     */  public static void storeShorts(short[] array, int offset, int length, File file)
/* 1517:     */    throws IOException
/* 1518:     */  {
/* 1519:1519 */    ShortArrays.ensureOffsetLength(array, offset, length);
/* 1520:1520 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1521:1521 */    for (int i = 0; i < length; i++) dos.writeShort(array[(offset + i)]);
/* 1522:1522 */    dos.close();
/* 1523:     */  }
/* 1524:     */  
/* 1529:     */  public static void storeShorts(short[] array, int offset, int length, CharSequence filename)
/* 1530:     */    throws IOException
/* 1531:     */  {
/* 1532:1532 */    storeShorts(array, offset, length, new File(filename.toString()));
/* 1533:     */  }
/* 1534:     */  
/* 1537:     */  public static void storeShorts(short[] array, File file)
/* 1538:     */    throws IOException
/* 1539:     */  {
/* 1540:1540 */    int length = array.length;
/* 1541:1541 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1542:1542 */    for (int i = 0; i < length; i++) dos.writeShort(array[i]);
/* 1543:1543 */    dos.close();
/* 1544:     */  }
/* 1545:     */  
/* 1548:     */  public static void storeShorts(short[] array, CharSequence filename)
/* 1549:     */    throws IOException
/* 1550:     */  {
/* 1551:1551 */    storeShorts(array, new File(filename.toString()));
/* 1552:     */  }
/* 1553:     */  
/* 1559:     */  public static long loadShorts(DataInput dataInput, short[][] array, long offset, long length)
/* 1560:     */    throws IOException
/* 1561:     */  {
/* 1562:1562 */    ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1563:1563 */    long c = 0L;
/* 1564:     */    try {
/* 1565:1565 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1566:1566 */        short[] t = array[i];
/* 1567:1567 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1568:1568 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 1569:1569 */          t[d] = dataInput.readShort();
/* 1570:1570 */          c += 1L;
/* 1571:     */        }
/* 1572:     */      }
/* 1573:     */    }
/* 1574:     */    catch (EOFException itsOk) {}
/* 1575:1575 */    return c;
/* 1576:     */  }
/* 1577:     */  
/* 1581:     */  public static long loadShorts(DataInput dataInput, short[][] array)
/* 1582:     */    throws IOException
/* 1583:     */  {
/* 1584:1584 */    long c = 0L;
/* 1585:     */    try {
/* 1586:1586 */      for (int i = 0; i < array.length; i++) {
/* 1587:1587 */        short[] t = array[i];
/* 1588:1588 */        int l = t.length;
/* 1589:1589 */        for (int d = 0; d < l; d++) {
/* 1590:1590 */          t[d] = dataInput.readShort();
/* 1591:1591 */          c += 1L;
/* 1592:     */        }
/* 1593:     */      }
/* 1594:     */    }
/* 1595:     */    catch (EOFException itsOk) {}
/* 1596:1596 */    return c;
/* 1597:     */  }
/* 1598:     */  
/* 1604:     */  public static long loadShorts(File file, short[][] array, long offset, long length)
/* 1605:     */    throws IOException
/* 1606:     */  {
/* 1607:1607 */    ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1608:1608 */    FileInputStream fis = new FileInputStream(file);
/* 1609:1609 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1610:1610 */    long c = 0L;
/* 1611:     */    try {
/* 1612:1612 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1613:1613 */        short[] t = array[i];
/* 1614:1614 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1615:1615 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 1616:1616 */          t[d] = dis.readShort();
/* 1617:1617 */          c += 1L;
/* 1618:     */        }
/* 1619:     */      }
/* 1620:     */    }
/* 1621:     */    catch (EOFException itsOk) {}
/* 1622:1622 */    dis.close();
/* 1623:1623 */    return c;
/* 1624:     */  }
/* 1625:     */  
/* 1631:     */  public static long loadShorts(CharSequence filename, short[][] array, long offset, long length)
/* 1632:     */    throws IOException
/* 1633:     */  {
/* 1634:1634 */    return loadShorts(new File(filename.toString()), array, offset, length);
/* 1635:     */  }
/* 1636:     */  
/* 1640:     */  public static long loadShorts(File file, short[][] array)
/* 1641:     */    throws IOException
/* 1642:     */  {
/* 1643:1643 */    FileInputStream fis = new FileInputStream(file);
/* 1644:1644 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1645:1645 */    long c = 0L;
/* 1646:     */    try {
/* 1647:1647 */      for (int i = 0; i < array.length; i++) {
/* 1648:1648 */        short[] t = array[i];
/* 1649:1649 */        int l = t.length;
/* 1650:1650 */        for (int d = 0; d < l; d++) {
/* 1651:1651 */          t[d] = dis.readShort();
/* 1652:1652 */          c += 1L;
/* 1653:     */        }
/* 1654:     */      }
/* 1655:     */    }
/* 1656:     */    catch (EOFException itsOk) {}
/* 1657:1657 */    dis.close();
/* 1658:1658 */    return c;
/* 1659:     */  }
/* 1660:     */  
/* 1664:     */  public static long loadShorts(CharSequence filename, short[][] array)
/* 1665:     */    throws IOException
/* 1666:     */  {
/* 1667:1667 */    return loadShorts(new File(filename.toString()), array);
/* 1668:     */  }
/* 1669:     */  
/* 1676:     */  public static short[][] loadShortsBig(File file)
/* 1677:     */    throws IOException
/* 1678:     */  {
/* 1679:1679 */    FileInputStream fis = new FileInputStream(file);
/* 1680:1680 */    long length = fis.getChannel().size() / 2L;
/* 1681:1681 */    short[][] array = ShortBigArrays.newBigArray(length);
/* 1682:1682 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1683:1683 */    for (int i = 0; i < array.length; i++) {
/* 1684:1684 */      short[] t = array[i];
/* 1685:1685 */      int l = t.length;
/* 1686:1686 */      for (int d = 0; d < l; d++) t[d] = dis.readShort();
/* 1687:     */    }
/* 1688:1688 */    dis.close();
/* 1689:1689 */    return array;
/* 1690:     */  }
/* 1691:     */  
/* 1698:     */  public static short[][] loadShortsBig(CharSequence filename)
/* 1699:     */    throws IOException
/* 1700:     */  {
/* 1701:1701 */    return loadShortsBig(new File(filename.toString()));
/* 1702:     */  }
/* 1703:     */  
/* 1708:     */  public static void storeShorts(short[][] array, long offset, long length, DataOutput dataOutput)
/* 1709:     */    throws IOException
/* 1710:     */  {
/* 1711:1711 */    ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1712:1712 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1713:1713 */      short[] t = array[i];
/* 1714:1714 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1715:1715 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeShort(t[d]);
/* 1716:     */      }
/* 1717:     */    }
/* 1718:     */  }
/* 1719:     */  
/* 1721:     */  public static void storeShorts(short[][] array, DataOutput dataOutput)
/* 1722:     */    throws IOException
/* 1723:     */  {
/* 1724:1724 */    for (int i = 0; i < array.length; i++) {
/* 1725:1725 */      short[] t = array[i];
/* 1726:1726 */      int l = t.length;
/* 1727:1727 */      for (int d = 0; d < l; d++) { dataOutput.writeShort(t[d]);
/* 1728:     */      }
/* 1729:     */    }
/* 1730:     */  }
/* 1731:     */  
/* 1735:     */  public static void storeShorts(short[][] array, long offset, long length, File file)
/* 1736:     */    throws IOException
/* 1737:     */  {
/* 1738:1738 */    ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1739:1739 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1740:1740 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1741:1741 */      short[] t = array[i];
/* 1742:1742 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1743:1743 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeShort(t[d]);
/* 1744:     */    }
/* 1745:1745 */    dos.close();
/* 1746:     */  }
/* 1747:     */  
/* 1752:     */  public static void storeShorts(short[][] array, long offset, long length, CharSequence filename)
/* 1753:     */    throws IOException
/* 1754:     */  {
/* 1755:1755 */    storeShorts(array, offset, length, new File(filename.toString()));
/* 1756:     */  }
/* 1757:     */  
/* 1760:     */  public static void storeShorts(short[][] array, File file)
/* 1761:     */    throws IOException
/* 1762:     */  {
/* 1763:1763 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1764:1764 */    for (int i = 0; i < array.length; i++) {
/* 1765:1765 */      short[] t = array[i];
/* 1766:1766 */      int l = t.length;
/* 1767:1767 */      for (int d = 0; d < l; d++) dos.writeShort(t[d]);
/* 1768:     */    }
/* 1769:1769 */    dos.close();
/* 1770:     */  }
/* 1771:     */  
/* 1774:     */  public static void storeShorts(short[][] array, CharSequence filename)
/* 1775:     */    throws IOException
/* 1776:     */  {
/* 1777:1777 */    storeShorts(array, new File(filename.toString()));
/* 1778:     */  }
/* 1779:     */  
/* 1782:     */  public static void storeShorts(ShortIterator i, DataOutput dataOutput)
/* 1783:     */    throws IOException
/* 1784:     */  {
/* 1785:1785 */    while (i.hasNext()) { dataOutput.writeShort(i.nextShort());
/* 1786:     */    }
/* 1787:     */  }
/* 1788:     */  
/* 1790:     */  public static void storeShorts(ShortIterator i, File file)
/* 1791:     */    throws IOException
/* 1792:     */  {
/* 1793:1793 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1794:1794 */    while (i.hasNext()) dos.writeShort(i.nextShort());
/* 1795:1795 */    dos.close();
/* 1796:     */  }
/* 1797:     */  
/* 1800:     */  public static void storeShorts(ShortIterator i, CharSequence filename)
/* 1801:     */    throws IOException
/* 1802:     */  {
/* 1803:1803 */    storeShorts(i, new File(filename.toString()));
/* 1804:     */  }
/* 1805:     */  
/* 1806:     */  private static final class ShortDataInputWrapper extends AbstractShortIterator {
/* 1807:     */    private final DataInput dataInput;
/* 1808:1808 */    private boolean toAdvance = true;
/* 1809:1809 */    private boolean endOfProcess = false;
/* 1810:     */    private short next;
/* 1811:     */    
/* 1812:1812 */    public ShortDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 1813:     */    
/* 1814:     */    public boolean hasNext() {
/* 1815:1815 */      if (!this.toAdvance) return !this.endOfProcess;
/* 1816:1816 */      this.toAdvance = false;
/* 1817:     */      try {
/* 1818:1818 */        this.next = this.dataInput.readShort();
/* 1819:     */      }
/* 1820:     */      catch (EOFException eof) {
/* 1821:1821 */        this.endOfProcess = true;
/* 1822:     */      } catch (IOException rethrow) {
/* 1823:1823 */        throw new RuntimeException(rethrow); }
/* 1824:1824 */      return !this.endOfProcess;
/* 1825:     */    }
/* 1826:     */    
/* 1827:1827 */    public short nextShort() { if (!hasNext()) throw new NoSuchElementException();
/* 1828:1828 */      this.toAdvance = true;
/* 1829:1829 */      return this.next;
/* 1830:     */    }
/* 1831:     */  }
/* 1832:     */  
/* 1835:     */  public static ShortIterator asShortIterator(DataInput dataInput)
/* 1836:     */  {
/* 1837:1837 */    return new ShortDataInputWrapper(dataInput);
/* 1838:     */  }
/* 1839:     */  
/* 1841:     */  public static ShortIterator asShortIterator(File file)
/* 1842:     */    throws IOException
/* 1843:     */  {
/* 1844:1844 */    return new ShortDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 1845:     */  }
/* 1846:     */  
/* 1848:     */  public static ShortIterator asShortIterator(CharSequence filename)
/* 1849:     */    throws IOException
/* 1850:     */  {
/* 1851:1851 */    return asShortIterator(new File(filename.toString()));
/* 1852:     */  }
/* 1853:     */  
/* 1896:     */  public static int loadChars(DataInput dataInput, char[] array, int offset, int length)
/* 1897:     */    throws IOException
/* 1898:     */  {
/* 1899:1899 */    CharArrays.ensureOffsetLength(array, offset, length);
/* 1900:1900 */    int i = 0;
/* 1901:     */    try {
/* 1902:1902 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readChar();
/* 1903:     */    }
/* 1904:     */    catch (EOFException itsOk) {}
/* 1905:1905 */    return i;
/* 1906:     */  }
/* 1907:     */  
/* 1911:     */  public static int loadChars(DataInput dataInput, char[] array)
/* 1912:     */    throws IOException
/* 1913:     */  {
/* 1914:1914 */    int i = 0;
/* 1915:     */    try {
/* 1916:1916 */      int length = array.length;
/* 1917:1917 */      for (i = 0; i < length; i++) array[i] = dataInput.readChar();
/* 1918:     */    }
/* 1919:     */    catch (EOFException itsOk) {}
/* 1920:1920 */    return i;
/* 1921:     */  }
/* 1922:     */  
/* 1928:     */  public static int loadChars(File file, char[] array, int offset, int length)
/* 1929:     */    throws IOException
/* 1930:     */  {
/* 1931:1931 */    CharArrays.ensureOffsetLength(array, offset, length);
/* 1932:1932 */    FileInputStream fis = new FileInputStream(file);
/* 1933:1933 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1934:1934 */    int i = 0;
/* 1935:     */    try {
/* 1936:1936 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readChar();
/* 1937:     */    }
/* 1938:     */    catch (EOFException itsOk) {}
/* 1939:1939 */    dis.close();
/* 1940:1940 */    return i;
/* 1941:     */  }
/* 1942:     */  
/* 1948:     */  public static int loadChars(CharSequence filename, char[] array, int offset, int length)
/* 1949:     */    throws IOException
/* 1950:     */  {
/* 1951:1951 */    return loadChars(new File(filename.toString()), array, offset, length);
/* 1952:     */  }
/* 1953:     */  
/* 1957:     */  public static int loadChars(File file, char[] array)
/* 1958:     */    throws IOException
/* 1959:     */  {
/* 1960:1960 */    FileInputStream fis = new FileInputStream(file);
/* 1961:1961 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1962:1962 */    int i = 0;
/* 1963:     */    try {
/* 1964:1964 */      int length = array.length;
/* 1965:1965 */      for (i = 0; i < length; i++) array[i] = dis.readChar();
/* 1966:     */    }
/* 1967:     */    catch (EOFException itsOk) {}
/* 1968:1968 */    dis.close();
/* 1969:1969 */    return i;
/* 1970:     */  }
/* 1971:     */  
/* 1975:     */  public static int loadChars(CharSequence filename, char[] array)
/* 1976:     */    throws IOException
/* 1977:     */  {
/* 1978:1978 */    return loadChars(new File(filename.toString()), array);
/* 1979:     */  }
/* 1980:     */  
/* 1987:     */  public static char[] loadChars(File file)
/* 1988:     */    throws IOException
/* 1989:     */  {
/* 1990:1990 */    FileInputStream fis = new FileInputStream(file);
/* 1991:1991 */    long length = fis.getChannel().size() / 2L;
/* 1992:1992 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 1993:1993 */    char[] array = new char[(int)length];
/* 1994:1994 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 1995:1995 */    for (int i = 0; i < length; i++) array[i] = dis.readChar();
/* 1996:1996 */    dis.close();
/* 1997:1997 */    return array;
/* 1998:     */  }
/* 1999:     */  
/* 2006:     */  public static char[] loadChars(CharSequence filename)
/* 2007:     */    throws IOException
/* 2008:     */  {
/* 2009:2009 */    return loadChars(new File(filename.toString()));
/* 2010:     */  }
/* 2011:     */  
/* 2016:     */  public static void storeChars(char[] array, int offset, int length, DataOutput dataOutput)
/* 2017:     */    throws IOException
/* 2018:     */  {
/* 2019:2019 */    CharArrays.ensureOffsetLength(array, offset, length);
/* 2020:2020 */    for (int i = 0; i < length; i++) { dataOutput.writeChar(array[(offset + i)]);
/* 2021:     */    }
/* 2022:     */  }
/* 2023:     */  
/* 2025:     */  public static void storeChars(char[] array, DataOutput dataOutput)
/* 2026:     */    throws IOException
/* 2027:     */  {
/* 2028:2028 */    int length = array.length;
/* 2029:2029 */    for (int i = 0; i < length; i++) { dataOutput.writeChar(array[i]);
/* 2030:     */    }
/* 2031:     */  }
/* 2032:     */  
/* 2036:     */  public static void storeChars(char[] array, int offset, int length, File file)
/* 2037:     */    throws IOException
/* 2038:     */  {
/* 2039:2039 */    CharArrays.ensureOffsetLength(array, offset, length);
/* 2040:2040 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2041:2041 */    for (int i = 0; i < length; i++) dos.writeChar(array[(offset + i)]);
/* 2042:2042 */    dos.close();
/* 2043:     */  }
/* 2044:     */  
/* 2049:     */  public static void storeChars(char[] array, int offset, int length, CharSequence filename)
/* 2050:     */    throws IOException
/* 2051:     */  {
/* 2052:2052 */    storeChars(array, offset, length, new File(filename.toString()));
/* 2053:     */  }
/* 2054:     */  
/* 2057:     */  public static void storeChars(char[] array, File file)
/* 2058:     */    throws IOException
/* 2059:     */  {
/* 2060:2060 */    int length = array.length;
/* 2061:2061 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2062:2062 */    for (int i = 0; i < length; i++) dos.writeChar(array[i]);
/* 2063:2063 */    dos.close();
/* 2064:     */  }
/* 2065:     */  
/* 2068:     */  public static void storeChars(char[] array, CharSequence filename)
/* 2069:     */    throws IOException
/* 2070:     */  {
/* 2071:2071 */    storeChars(array, new File(filename.toString()));
/* 2072:     */  }
/* 2073:     */  
/* 2079:     */  public static long loadChars(DataInput dataInput, char[][] array, long offset, long length)
/* 2080:     */    throws IOException
/* 2081:     */  {
/* 2082:2082 */    CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2083:2083 */    long c = 0L;
/* 2084:     */    try {
/* 2085:2085 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2086:2086 */        char[] t = array[i];
/* 2087:2087 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2088:2088 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2089:2089 */          t[d] = dataInput.readChar();
/* 2090:2090 */          c += 1L;
/* 2091:     */        }
/* 2092:     */      }
/* 2093:     */    }
/* 2094:     */    catch (EOFException itsOk) {}
/* 2095:2095 */    return c;
/* 2096:     */  }
/* 2097:     */  
/* 2101:     */  public static long loadChars(DataInput dataInput, char[][] array)
/* 2102:     */    throws IOException
/* 2103:     */  {
/* 2104:2104 */    long c = 0L;
/* 2105:     */    try {
/* 2106:2106 */      for (int i = 0; i < array.length; i++) {
/* 2107:2107 */        char[] t = array[i];
/* 2108:2108 */        int l = t.length;
/* 2109:2109 */        for (int d = 0; d < l; d++) {
/* 2110:2110 */          t[d] = dataInput.readChar();
/* 2111:2111 */          c += 1L;
/* 2112:     */        }
/* 2113:     */      }
/* 2114:     */    }
/* 2115:     */    catch (EOFException itsOk) {}
/* 2116:2116 */    return c;
/* 2117:     */  }
/* 2118:     */  
/* 2124:     */  public static long loadChars(File file, char[][] array, long offset, long length)
/* 2125:     */    throws IOException
/* 2126:     */  {
/* 2127:2127 */    CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2128:2128 */    FileInputStream fis = new FileInputStream(file);
/* 2129:2129 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2130:2130 */    long c = 0L;
/* 2131:     */    try {
/* 2132:2132 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2133:2133 */        char[] t = array[i];
/* 2134:2134 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2135:2135 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2136:2136 */          t[d] = dis.readChar();
/* 2137:2137 */          c += 1L;
/* 2138:     */        }
/* 2139:     */      }
/* 2140:     */    }
/* 2141:     */    catch (EOFException itsOk) {}
/* 2142:2142 */    dis.close();
/* 2143:2143 */    return c;
/* 2144:     */  }
/* 2145:     */  
/* 2151:     */  public static long loadChars(CharSequence filename, char[][] array, long offset, long length)
/* 2152:     */    throws IOException
/* 2153:     */  {
/* 2154:2154 */    return loadChars(new File(filename.toString()), array, offset, length);
/* 2155:     */  }
/* 2156:     */  
/* 2160:     */  public static long loadChars(File file, char[][] array)
/* 2161:     */    throws IOException
/* 2162:     */  {
/* 2163:2163 */    FileInputStream fis = new FileInputStream(file);
/* 2164:2164 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2165:2165 */    long c = 0L;
/* 2166:     */    try {
/* 2167:2167 */      for (int i = 0; i < array.length; i++) {
/* 2168:2168 */        char[] t = array[i];
/* 2169:2169 */        int l = t.length;
/* 2170:2170 */        for (int d = 0; d < l; d++) {
/* 2171:2171 */          t[d] = dis.readChar();
/* 2172:2172 */          c += 1L;
/* 2173:     */        }
/* 2174:     */      }
/* 2175:     */    }
/* 2176:     */    catch (EOFException itsOk) {}
/* 2177:2177 */    dis.close();
/* 2178:2178 */    return c;
/* 2179:     */  }
/* 2180:     */  
/* 2184:     */  public static long loadChars(CharSequence filename, char[][] array)
/* 2185:     */    throws IOException
/* 2186:     */  {
/* 2187:2187 */    return loadChars(new File(filename.toString()), array);
/* 2188:     */  }
/* 2189:     */  
/* 2196:     */  public static char[][] loadCharsBig(File file)
/* 2197:     */    throws IOException
/* 2198:     */  {
/* 2199:2199 */    FileInputStream fis = new FileInputStream(file);
/* 2200:2200 */    long length = fis.getChannel().size() / 2L;
/* 2201:2201 */    char[][] array = CharBigArrays.newBigArray(length);
/* 2202:2202 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2203:2203 */    for (int i = 0; i < array.length; i++) {
/* 2204:2204 */      char[] t = array[i];
/* 2205:2205 */      int l = t.length;
/* 2206:2206 */      for (int d = 0; d < l; d++) t[d] = dis.readChar();
/* 2207:     */    }
/* 2208:2208 */    dis.close();
/* 2209:2209 */    return array;
/* 2210:     */  }
/* 2211:     */  
/* 2218:     */  public static char[][] loadCharsBig(CharSequence filename)
/* 2219:     */    throws IOException
/* 2220:     */  {
/* 2221:2221 */    return loadCharsBig(new File(filename.toString()));
/* 2222:     */  }
/* 2223:     */  
/* 2228:     */  public static void storeChars(char[][] array, long offset, long length, DataOutput dataOutput)
/* 2229:     */    throws IOException
/* 2230:     */  {
/* 2231:2231 */    CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2232:2232 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2233:2233 */      char[] t = array[i];
/* 2234:2234 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2235:2235 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeChar(t[d]);
/* 2236:     */      }
/* 2237:     */    }
/* 2238:     */  }
/* 2239:     */  
/* 2241:     */  public static void storeChars(char[][] array, DataOutput dataOutput)
/* 2242:     */    throws IOException
/* 2243:     */  {
/* 2244:2244 */    for (int i = 0; i < array.length; i++) {
/* 2245:2245 */      char[] t = array[i];
/* 2246:2246 */      int l = t.length;
/* 2247:2247 */      for (int d = 0; d < l; d++) { dataOutput.writeChar(t[d]);
/* 2248:     */      }
/* 2249:     */    }
/* 2250:     */  }
/* 2251:     */  
/* 2255:     */  public static void storeChars(char[][] array, long offset, long length, File file)
/* 2256:     */    throws IOException
/* 2257:     */  {
/* 2258:2258 */    CharBigArrays.ensureOffsetLength(array, offset, length);
/* 2259:2259 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2260:2260 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2261:2261 */      char[] t = array[i];
/* 2262:2262 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2263:2263 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeChar(t[d]);
/* 2264:     */    }
/* 2265:2265 */    dos.close();
/* 2266:     */  }
/* 2267:     */  
/* 2272:     */  public static void storeChars(char[][] array, long offset, long length, CharSequence filename)
/* 2273:     */    throws IOException
/* 2274:     */  {
/* 2275:2275 */    storeChars(array, offset, length, new File(filename.toString()));
/* 2276:     */  }
/* 2277:     */  
/* 2280:     */  public static void storeChars(char[][] array, File file)
/* 2281:     */    throws IOException
/* 2282:     */  {
/* 2283:2283 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2284:2284 */    for (int i = 0; i < array.length; i++) {
/* 2285:2285 */      char[] t = array[i];
/* 2286:2286 */      int l = t.length;
/* 2287:2287 */      for (int d = 0; d < l; d++) dos.writeChar(t[d]);
/* 2288:     */    }
/* 2289:2289 */    dos.close();
/* 2290:     */  }
/* 2291:     */  
/* 2294:     */  public static void storeChars(char[][] array, CharSequence filename)
/* 2295:     */    throws IOException
/* 2296:     */  {
/* 2297:2297 */    storeChars(array, new File(filename.toString()));
/* 2298:     */  }
/* 2299:     */  
/* 2302:     */  public static void storeChars(CharIterator i, DataOutput dataOutput)
/* 2303:     */    throws IOException
/* 2304:     */  {
/* 2305:2305 */    while (i.hasNext()) { dataOutput.writeChar(i.nextChar());
/* 2306:     */    }
/* 2307:     */  }
/* 2308:     */  
/* 2310:     */  public static void storeChars(CharIterator i, File file)
/* 2311:     */    throws IOException
/* 2312:     */  {
/* 2313:2313 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2314:2314 */    while (i.hasNext()) dos.writeChar(i.nextChar());
/* 2315:2315 */    dos.close();
/* 2316:     */  }
/* 2317:     */  
/* 2320:     */  public static void storeChars(CharIterator i, CharSequence filename)
/* 2321:     */    throws IOException
/* 2322:     */  {
/* 2323:2323 */    storeChars(i, new File(filename.toString()));
/* 2324:     */  }
/* 2325:     */  
/* 2326:     */  private static final class CharDataInputWrapper extends AbstractCharIterator {
/* 2327:     */    private final DataInput dataInput;
/* 2328:2328 */    private boolean toAdvance = true;
/* 2329:2329 */    private boolean endOfProcess = false;
/* 2330:     */    private char next;
/* 2331:     */    
/* 2332:2332 */    public CharDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 2333:     */    
/* 2334:     */    public boolean hasNext() {
/* 2335:2335 */      if (!this.toAdvance) return !this.endOfProcess;
/* 2336:2336 */      this.toAdvance = false;
/* 2337:     */      try {
/* 2338:2338 */        this.next = this.dataInput.readChar();
/* 2339:     */      }
/* 2340:     */      catch (EOFException eof) {
/* 2341:2341 */        this.endOfProcess = true;
/* 2342:     */      } catch (IOException rethrow) {
/* 2343:2343 */        throw new RuntimeException(rethrow); }
/* 2344:2344 */      return !this.endOfProcess;
/* 2345:     */    }
/* 2346:     */    
/* 2347:2347 */    public char nextChar() { if (!hasNext()) throw new NoSuchElementException();
/* 2348:2348 */      this.toAdvance = true;
/* 2349:2349 */      return this.next;
/* 2350:     */    }
/* 2351:     */  }
/* 2352:     */  
/* 2355:     */  public static CharIterator asCharIterator(DataInput dataInput)
/* 2356:     */  {
/* 2357:2357 */    return new CharDataInputWrapper(dataInput);
/* 2358:     */  }
/* 2359:     */  
/* 2361:     */  public static CharIterator asCharIterator(File file)
/* 2362:     */    throws IOException
/* 2363:     */  {
/* 2364:2364 */    return new CharDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 2365:     */  }
/* 2366:     */  
/* 2368:     */  public static CharIterator asCharIterator(CharSequence filename)
/* 2369:     */    throws IOException
/* 2370:     */  {
/* 2371:2371 */    return asCharIterator(new File(filename.toString()));
/* 2372:     */  }
/* 2373:     */  
/* 2416:     */  public static int loadInts(DataInput dataInput, int[] array, int offset, int length)
/* 2417:     */    throws IOException
/* 2418:     */  {
/* 2419:2419 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 2420:2420 */    int i = 0;
/* 2421:     */    try {
/* 2422:2422 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readInt();
/* 2423:     */    }
/* 2424:     */    catch (EOFException itsOk) {}
/* 2425:2425 */    return i;
/* 2426:     */  }
/* 2427:     */  
/* 2431:     */  public static int loadInts(DataInput dataInput, int[] array)
/* 2432:     */    throws IOException
/* 2433:     */  {
/* 2434:2434 */    int i = 0;
/* 2435:     */    try {
/* 2436:2436 */      int length = array.length;
/* 2437:2437 */      for (i = 0; i < length; i++) array[i] = dataInput.readInt();
/* 2438:     */    }
/* 2439:     */    catch (EOFException itsOk) {}
/* 2440:2440 */    return i;
/* 2441:     */  }
/* 2442:     */  
/* 2448:     */  public static int loadInts(File file, int[] array, int offset, int length)
/* 2449:     */    throws IOException
/* 2450:     */  {
/* 2451:2451 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 2452:2452 */    FileInputStream fis = new FileInputStream(file);
/* 2453:2453 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2454:2454 */    int i = 0;
/* 2455:     */    try {
/* 2456:2456 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readInt();
/* 2457:     */    }
/* 2458:     */    catch (EOFException itsOk) {}
/* 2459:2459 */    dis.close();
/* 2460:2460 */    return i;
/* 2461:     */  }
/* 2462:     */  
/* 2468:     */  public static int loadInts(CharSequence filename, int[] array, int offset, int length)
/* 2469:     */    throws IOException
/* 2470:     */  {
/* 2471:2471 */    return loadInts(new File(filename.toString()), array, offset, length);
/* 2472:     */  }
/* 2473:     */  
/* 2477:     */  public static int loadInts(File file, int[] array)
/* 2478:     */    throws IOException
/* 2479:     */  {
/* 2480:2480 */    FileInputStream fis = new FileInputStream(file);
/* 2481:2481 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2482:2482 */    int i = 0;
/* 2483:     */    try {
/* 2484:2484 */      int length = array.length;
/* 2485:2485 */      for (i = 0; i < length; i++) array[i] = dis.readInt();
/* 2486:     */    }
/* 2487:     */    catch (EOFException itsOk) {}
/* 2488:2488 */    dis.close();
/* 2489:2489 */    return i;
/* 2490:     */  }
/* 2491:     */  
/* 2495:     */  public static int loadInts(CharSequence filename, int[] array)
/* 2496:     */    throws IOException
/* 2497:     */  {
/* 2498:2498 */    return loadInts(new File(filename.toString()), array);
/* 2499:     */  }
/* 2500:     */  
/* 2507:     */  public static int[] loadInts(File file)
/* 2508:     */    throws IOException
/* 2509:     */  {
/* 2510:2510 */    FileInputStream fis = new FileInputStream(file);
/* 2511:2511 */    long length = fis.getChannel().size() / 4L;
/* 2512:2512 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 2513:2513 */    int[] array = new int[(int)length];
/* 2514:2514 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2515:2515 */    for (int i = 0; i < length; i++) array[i] = dis.readInt();
/* 2516:2516 */    dis.close();
/* 2517:2517 */    return array;
/* 2518:     */  }
/* 2519:     */  
/* 2526:     */  public static int[] loadInts(CharSequence filename)
/* 2527:     */    throws IOException
/* 2528:     */  {
/* 2529:2529 */    return loadInts(new File(filename.toString()));
/* 2530:     */  }
/* 2531:     */  
/* 2536:     */  public static void storeInts(int[] array, int offset, int length, DataOutput dataOutput)
/* 2537:     */    throws IOException
/* 2538:     */  {
/* 2539:2539 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 2540:2540 */    for (int i = 0; i < length; i++) { dataOutput.writeInt(array[(offset + i)]);
/* 2541:     */    }
/* 2542:     */  }
/* 2543:     */  
/* 2545:     */  public static void storeInts(int[] array, DataOutput dataOutput)
/* 2546:     */    throws IOException
/* 2547:     */  {
/* 2548:2548 */    int length = array.length;
/* 2549:2549 */    for (int i = 0; i < length; i++) { dataOutput.writeInt(array[i]);
/* 2550:     */    }
/* 2551:     */  }
/* 2552:     */  
/* 2556:     */  public static void storeInts(int[] array, int offset, int length, File file)
/* 2557:     */    throws IOException
/* 2558:     */  {
/* 2559:2559 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 2560:2560 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2561:2561 */    for (int i = 0; i < length; i++) dos.writeInt(array[(offset + i)]);
/* 2562:2562 */    dos.close();
/* 2563:     */  }
/* 2564:     */  
/* 2569:     */  public static void storeInts(int[] array, int offset, int length, CharSequence filename)
/* 2570:     */    throws IOException
/* 2571:     */  {
/* 2572:2572 */    storeInts(array, offset, length, new File(filename.toString()));
/* 2573:     */  }
/* 2574:     */  
/* 2577:     */  public static void storeInts(int[] array, File file)
/* 2578:     */    throws IOException
/* 2579:     */  {
/* 2580:2580 */    int length = array.length;
/* 2581:2581 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2582:2582 */    for (int i = 0; i < length; i++) dos.writeInt(array[i]);
/* 2583:2583 */    dos.close();
/* 2584:     */  }
/* 2585:     */  
/* 2588:     */  public static void storeInts(int[] array, CharSequence filename)
/* 2589:     */    throws IOException
/* 2590:     */  {
/* 2591:2591 */    storeInts(array, new File(filename.toString()));
/* 2592:     */  }
/* 2593:     */  
/* 2599:     */  public static long loadInts(DataInput dataInput, int[][] array, long offset, long length)
/* 2600:     */    throws IOException
/* 2601:     */  {
/* 2602:2602 */    IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2603:2603 */    long c = 0L;
/* 2604:     */    try {
/* 2605:2605 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2606:2606 */        int[] t = array[i];
/* 2607:2607 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2608:2608 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2609:2609 */          t[d] = dataInput.readInt();
/* 2610:2610 */          c += 1L;
/* 2611:     */        }
/* 2612:     */      }
/* 2613:     */    }
/* 2614:     */    catch (EOFException itsOk) {}
/* 2615:2615 */    return c;
/* 2616:     */  }
/* 2617:     */  
/* 2621:     */  public static long loadInts(DataInput dataInput, int[][] array)
/* 2622:     */    throws IOException
/* 2623:     */  {
/* 2624:2624 */    long c = 0L;
/* 2625:     */    try {
/* 2626:2626 */      for (int i = 0; i < array.length; i++) {
/* 2627:2627 */        int[] t = array[i];
/* 2628:2628 */        int l = t.length;
/* 2629:2629 */        for (int d = 0; d < l; d++) {
/* 2630:2630 */          t[d] = dataInput.readInt();
/* 2631:2631 */          c += 1L;
/* 2632:     */        }
/* 2633:     */      }
/* 2634:     */    }
/* 2635:     */    catch (EOFException itsOk) {}
/* 2636:2636 */    return c;
/* 2637:     */  }
/* 2638:     */  
/* 2644:     */  public static long loadInts(File file, int[][] array, long offset, long length)
/* 2645:     */    throws IOException
/* 2646:     */  {
/* 2647:2647 */    IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2648:2648 */    FileInputStream fis = new FileInputStream(file);
/* 2649:2649 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2650:2650 */    long c = 0L;
/* 2651:     */    try {
/* 2652:2652 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2653:2653 */        int[] t = array[i];
/* 2654:2654 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2655:2655 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 2656:2656 */          t[d] = dis.readInt();
/* 2657:2657 */          c += 1L;
/* 2658:     */        }
/* 2659:     */      }
/* 2660:     */    }
/* 2661:     */    catch (EOFException itsOk) {}
/* 2662:2662 */    dis.close();
/* 2663:2663 */    return c;
/* 2664:     */  }
/* 2665:     */  
/* 2671:     */  public static long loadInts(CharSequence filename, int[][] array, long offset, long length)
/* 2672:     */    throws IOException
/* 2673:     */  {
/* 2674:2674 */    return loadInts(new File(filename.toString()), array, offset, length);
/* 2675:     */  }
/* 2676:     */  
/* 2680:     */  public static long loadInts(File file, int[][] array)
/* 2681:     */    throws IOException
/* 2682:     */  {
/* 2683:2683 */    FileInputStream fis = new FileInputStream(file);
/* 2684:2684 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2685:2685 */    long c = 0L;
/* 2686:     */    try {
/* 2687:2687 */      for (int i = 0; i < array.length; i++) {
/* 2688:2688 */        int[] t = array[i];
/* 2689:2689 */        int l = t.length;
/* 2690:2690 */        for (int d = 0; d < l; d++) {
/* 2691:2691 */          t[d] = dis.readInt();
/* 2692:2692 */          c += 1L;
/* 2693:     */        }
/* 2694:     */      }
/* 2695:     */    }
/* 2696:     */    catch (EOFException itsOk) {}
/* 2697:2697 */    dis.close();
/* 2698:2698 */    return c;
/* 2699:     */  }
/* 2700:     */  
/* 2704:     */  public static long loadInts(CharSequence filename, int[][] array)
/* 2705:     */    throws IOException
/* 2706:     */  {
/* 2707:2707 */    return loadInts(new File(filename.toString()), array);
/* 2708:     */  }
/* 2709:     */  
/* 2716:     */  public static int[][] loadIntsBig(File file)
/* 2717:     */    throws IOException
/* 2718:     */  {
/* 2719:2719 */    FileInputStream fis = new FileInputStream(file);
/* 2720:2720 */    long length = fis.getChannel().size() / 4L;
/* 2721:2721 */    int[][] array = IntBigArrays.newBigArray(length);
/* 2722:2722 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2723:2723 */    for (int i = 0; i < array.length; i++) {
/* 2724:2724 */      int[] t = array[i];
/* 2725:2725 */      int l = t.length;
/* 2726:2726 */      for (int d = 0; d < l; d++) t[d] = dis.readInt();
/* 2727:     */    }
/* 2728:2728 */    dis.close();
/* 2729:2729 */    return array;
/* 2730:     */  }
/* 2731:     */  
/* 2738:     */  public static int[][] loadIntsBig(CharSequence filename)
/* 2739:     */    throws IOException
/* 2740:     */  {
/* 2741:2741 */    return loadIntsBig(new File(filename.toString()));
/* 2742:     */  }
/* 2743:     */  
/* 2748:     */  public static void storeInts(int[][] array, long offset, long length, DataOutput dataOutput)
/* 2749:     */    throws IOException
/* 2750:     */  {
/* 2751:2751 */    IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2752:2752 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2753:2753 */      int[] t = array[i];
/* 2754:2754 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2755:2755 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeInt(t[d]);
/* 2756:     */      }
/* 2757:     */    }
/* 2758:     */  }
/* 2759:     */  
/* 2761:     */  public static void storeInts(int[][] array, DataOutput dataOutput)
/* 2762:     */    throws IOException
/* 2763:     */  {
/* 2764:2764 */    for (int i = 0; i < array.length; i++) {
/* 2765:2765 */      int[] t = array[i];
/* 2766:2766 */      int l = t.length;
/* 2767:2767 */      for (int d = 0; d < l; d++) { dataOutput.writeInt(t[d]);
/* 2768:     */      }
/* 2769:     */    }
/* 2770:     */  }
/* 2771:     */  
/* 2775:     */  public static void storeInts(int[][] array, long offset, long length, File file)
/* 2776:     */    throws IOException
/* 2777:     */  {
/* 2778:2778 */    IntBigArrays.ensureOffsetLength(array, offset, length);
/* 2779:2779 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2780:2780 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2781:2781 */      int[] t = array[i];
/* 2782:2782 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2783:2783 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeInt(t[d]);
/* 2784:     */    }
/* 2785:2785 */    dos.close();
/* 2786:     */  }
/* 2787:     */  
/* 2792:     */  public static void storeInts(int[][] array, long offset, long length, CharSequence filename)
/* 2793:     */    throws IOException
/* 2794:     */  {
/* 2795:2795 */    storeInts(array, offset, length, new File(filename.toString()));
/* 2796:     */  }
/* 2797:     */  
/* 2800:     */  public static void storeInts(int[][] array, File file)
/* 2801:     */    throws IOException
/* 2802:     */  {
/* 2803:2803 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2804:2804 */    for (int i = 0; i < array.length; i++) {
/* 2805:2805 */      int[] t = array[i];
/* 2806:2806 */      int l = t.length;
/* 2807:2807 */      for (int d = 0; d < l; d++) dos.writeInt(t[d]);
/* 2808:     */    }
/* 2809:2809 */    dos.close();
/* 2810:     */  }
/* 2811:     */  
/* 2814:     */  public static void storeInts(int[][] array, CharSequence filename)
/* 2815:     */    throws IOException
/* 2816:     */  {
/* 2817:2817 */    storeInts(array, new File(filename.toString()));
/* 2818:     */  }
/* 2819:     */  
/* 2822:     */  public static void storeInts(IntIterator i, DataOutput dataOutput)
/* 2823:     */    throws IOException
/* 2824:     */  {
/* 2825:2825 */    while (i.hasNext()) { dataOutput.writeInt(i.nextInt());
/* 2826:     */    }
/* 2827:     */  }
/* 2828:     */  
/* 2830:     */  public static void storeInts(IntIterator i, File file)
/* 2831:     */    throws IOException
/* 2832:     */  {
/* 2833:2833 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2834:2834 */    while (i.hasNext()) dos.writeInt(i.nextInt());
/* 2835:2835 */    dos.close();
/* 2836:     */  }
/* 2837:     */  
/* 2840:     */  public static void storeInts(IntIterator i, CharSequence filename)
/* 2841:     */    throws IOException
/* 2842:     */  {
/* 2843:2843 */    storeInts(i, new File(filename.toString()));
/* 2844:     */  }
/* 2845:     */  
/* 2846:     */  private static final class IntDataInputWrapper extends AbstractIntIterator {
/* 2847:     */    private final DataInput dataInput;
/* 2848:2848 */    private boolean toAdvance = true;
/* 2849:2849 */    private boolean endOfProcess = false;
/* 2850:     */    private int next;
/* 2851:     */    
/* 2852:2852 */    public IntDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 2853:     */    
/* 2854:     */    public boolean hasNext() {
/* 2855:2855 */      if (!this.toAdvance) return !this.endOfProcess;
/* 2856:2856 */      this.toAdvance = false;
/* 2857:     */      try {
/* 2858:2858 */        this.next = this.dataInput.readInt();
/* 2859:     */      }
/* 2860:     */      catch (EOFException eof) {
/* 2861:2861 */        this.endOfProcess = true;
/* 2862:     */      } catch (IOException rethrow) {
/* 2863:2863 */        throw new RuntimeException(rethrow); }
/* 2864:2864 */      return !this.endOfProcess;
/* 2865:     */    }
/* 2866:     */    
/* 2867:2867 */    public int nextInt() { if (!hasNext()) throw new NoSuchElementException();
/* 2868:2868 */      this.toAdvance = true;
/* 2869:2869 */      return this.next;
/* 2870:     */    }
/* 2871:     */  }
/* 2872:     */  
/* 2875:     */  public static IntIterator asIntIterator(DataInput dataInput)
/* 2876:     */  {
/* 2877:2877 */    return new IntDataInputWrapper(dataInput);
/* 2878:     */  }
/* 2879:     */  
/* 2881:     */  public static IntIterator asIntIterator(File file)
/* 2882:     */    throws IOException
/* 2883:     */  {
/* 2884:2884 */    return new IntDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 2885:     */  }
/* 2886:     */  
/* 2888:     */  public static IntIterator asIntIterator(CharSequence filename)
/* 2889:     */    throws IOException
/* 2890:     */  {
/* 2891:2891 */    return asIntIterator(new File(filename.toString()));
/* 2892:     */  }
/* 2893:     */  
/* 2936:     */  public static int loadLongs(DataInput dataInput, long[] array, int offset, int length)
/* 2937:     */    throws IOException
/* 2938:     */  {
/* 2939:2939 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 2940:2940 */    int i = 0;
/* 2941:     */    try {
/* 2942:2942 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readLong();
/* 2943:     */    }
/* 2944:     */    catch (EOFException itsOk) {}
/* 2945:2945 */    return i;
/* 2946:     */  }
/* 2947:     */  
/* 2951:     */  public static int loadLongs(DataInput dataInput, long[] array)
/* 2952:     */    throws IOException
/* 2953:     */  {
/* 2954:2954 */    int i = 0;
/* 2955:     */    try {
/* 2956:2956 */      int length = array.length;
/* 2957:2957 */      for (i = 0; i < length; i++) array[i] = dataInput.readLong();
/* 2958:     */    }
/* 2959:     */    catch (EOFException itsOk) {}
/* 2960:2960 */    return i;
/* 2961:     */  }
/* 2962:     */  
/* 2968:     */  public static int loadLongs(File file, long[] array, int offset, int length)
/* 2969:     */    throws IOException
/* 2970:     */  {
/* 2971:2971 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 2972:2972 */    FileInputStream fis = new FileInputStream(file);
/* 2973:2973 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 2974:2974 */    int i = 0;
/* 2975:     */    try {
/* 2976:2976 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readLong();
/* 2977:     */    }
/* 2978:     */    catch (EOFException itsOk) {}
/* 2979:2979 */    dis.close();
/* 2980:2980 */    return i;
/* 2981:     */  }
/* 2982:     */  
/* 2988:     */  public static int loadLongs(CharSequence filename, long[] array, int offset, int length)
/* 2989:     */    throws IOException
/* 2990:     */  {
/* 2991:2991 */    return loadLongs(new File(filename.toString()), array, offset, length);
/* 2992:     */  }
/* 2993:     */  
/* 2997:     */  public static int loadLongs(File file, long[] array)
/* 2998:     */    throws IOException
/* 2999:     */  {
/* 3000:3000 */    FileInputStream fis = new FileInputStream(file);
/* 3001:3001 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3002:3002 */    int i = 0;
/* 3003:     */    try {
/* 3004:3004 */      int length = array.length;
/* 3005:3005 */      for (i = 0; i < length; i++) array[i] = dis.readLong();
/* 3006:     */    }
/* 3007:     */    catch (EOFException itsOk) {}
/* 3008:3008 */    dis.close();
/* 3009:3009 */    return i;
/* 3010:     */  }
/* 3011:     */  
/* 3015:     */  public static int loadLongs(CharSequence filename, long[] array)
/* 3016:     */    throws IOException
/* 3017:     */  {
/* 3018:3018 */    return loadLongs(new File(filename.toString()), array);
/* 3019:     */  }
/* 3020:     */  
/* 3027:     */  public static long[] loadLongs(File file)
/* 3028:     */    throws IOException
/* 3029:     */  {
/* 3030:3030 */    FileInputStream fis = new FileInputStream(file);
/* 3031:3031 */    long length = fis.getChannel().size() / 8L;
/* 3032:3032 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 3033:3033 */    long[] array = new long[(int)length];
/* 3034:3034 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3035:3035 */    for (int i = 0; i < length; i++) array[i] = dis.readLong();
/* 3036:3036 */    dis.close();
/* 3037:3037 */    return array;
/* 3038:     */  }
/* 3039:     */  
/* 3046:     */  public static long[] loadLongs(CharSequence filename)
/* 3047:     */    throws IOException
/* 3048:     */  {
/* 3049:3049 */    return loadLongs(new File(filename.toString()));
/* 3050:     */  }
/* 3051:     */  
/* 3056:     */  public static void storeLongs(long[] array, int offset, int length, DataOutput dataOutput)
/* 3057:     */    throws IOException
/* 3058:     */  {
/* 3059:3059 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 3060:3060 */    for (int i = 0; i < length; i++) { dataOutput.writeLong(array[(offset + i)]);
/* 3061:     */    }
/* 3062:     */  }
/* 3063:     */  
/* 3065:     */  public static void storeLongs(long[] array, DataOutput dataOutput)
/* 3066:     */    throws IOException
/* 3067:     */  {
/* 3068:3068 */    int length = array.length;
/* 3069:3069 */    for (int i = 0; i < length; i++) { dataOutput.writeLong(array[i]);
/* 3070:     */    }
/* 3071:     */  }
/* 3072:     */  
/* 3076:     */  public static void storeLongs(long[] array, int offset, int length, File file)
/* 3077:     */    throws IOException
/* 3078:     */  {
/* 3079:3079 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 3080:3080 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3081:3081 */    for (int i = 0; i < length; i++) dos.writeLong(array[(offset + i)]);
/* 3082:3082 */    dos.close();
/* 3083:     */  }
/* 3084:     */  
/* 3089:     */  public static void storeLongs(long[] array, int offset, int length, CharSequence filename)
/* 3090:     */    throws IOException
/* 3091:     */  {
/* 3092:3092 */    storeLongs(array, offset, length, new File(filename.toString()));
/* 3093:     */  }
/* 3094:     */  
/* 3097:     */  public static void storeLongs(long[] array, File file)
/* 3098:     */    throws IOException
/* 3099:     */  {
/* 3100:3100 */    int length = array.length;
/* 3101:3101 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3102:3102 */    for (int i = 0; i < length; i++) dos.writeLong(array[i]);
/* 3103:3103 */    dos.close();
/* 3104:     */  }
/* 3105:     */  
/* 3108:     */  public static void storeLongs(long[] array, CharSequence filename)
/* 3109:     */    throws IOException
/* 3110:     */  {
/* 3111:3111 */    storeLongs(array, new File(filename.toString()));
/* 3112:     */  }
/* 3113:     */  
/* 3119:     */  public static long loadLongs(DataInput dataInput, long[][] array, long offset, long length)
/* 3120:     */    throws IOException
/* 3121:     */  {
/* 3122:3122 */    LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3123:3123 */    long c = 0L;
/* 3124:     */    try {
/* 3125:3125 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3126:3126 */        long[] t = array[i];
/* 3127:3127 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3128:3128 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3129:3129 */          t[d] = dataInput.readLong();
/* 3130:3130 */          c += 1L;
/* 3131:     */        }
/* 3132:     */      }
/* 3133:     */    }
/* 3134:     */    catch (EOFException itsOk) {}
/* 3135:3135 */    return c;
/* 3136:     */  }
/* 3137:     */  
/* 3141:     */  public static long loadLongs(DataInput dataInput, long[][] array)
/* 3142:     */    throws IOException
/* 3143:     */  {
/* 3144:3144 */    long c = 0L;
/* 3145:     */    try {
/* 3146:3146 */      for (int i = 0; i < array.length; i++) {
/* 3147:3147 */        long[] t = array[i];
/* 3148:3148 */        int l = t.length;
/* 3149:3149 */        for (int d = 0; d < l; d++) {
/* 3150:3150 */          t[d] = dataInput.readLong();
/* 3151:3151 */          c += 1L;
/* 3152:     */        }
/* 3153:     */      }
/* 3154:     */    }
/* 3155:     */    catch (EOFException itsOk) {}
/* 3156:3156 */    return c;
/* 3157:     */  }
/* 3158:     */  
/* 3164:     */  public static long loadLongs(File file, long[][] array, long offset, long length)
/* 3165:     */    throws IOException
/* 3166:     */  {
/* 3167:3167 */    LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3168:3168 */    FileInputStream fis = new FileInputStream(file);
/* 3169:3169 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3170:3170 */    long c = 0L;
/* 3171:     */    try {
/* 3172:3172 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3173:3173 */        long[] t = array[i];
/* 3174:3174 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3175:3175 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3176:3176 */          t[d] = dis.readLong();
/* 3177:3177 */          c += 1L;
/* 3178:     */        }
/* 3179:     */      }
/* 3180:     */    }
/* 3181:     */    catch (EOFException itsOk) {}
/* 3182:3182 */    dis.close();
/* 3183:3183 */    return c;
/* 3184:     */  }
/* 3185:     */  
/* 3191:     */  public static long loadLongs(CharSequence filename, long[][] array, long offset, long length)
/* 3192:     */    throws IOException
/* 3193:     */  {
/* 3194:3194 */    return loadLongs(new File(filename.toString()), array, offset, length);
/* 3195:     */  }
/* 3196:     */  
/* 3200:     */  public static long loadLongs(File file, long[][] array)
/* 3201:     */    throws IOException
/* 3202:     */  {
/* 3203:3203 */    FileInputStream fis = new FileInputStream(file);
/* 3204:3204 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3205:3205 */    long c = 0L;
/* 3206:     */    try {
/* 3207:3207 */      for (int i = 0; i < array.length; i++) {
/* 3208:3208 */        long[] t = array[i];
/* 3209:3209 */        int l = t.length;
/* 3210:3210 */        for (int d = 0; d < l; d++) {
/* 3211:3211 */          t[d] = dis.readLong();
/* 3212:3212 */          c += 1L;
/* 3213:     */        }
/* 3214:     */      }
/* 3215:     */    }
/* 3216:     */    catch (EOFException itsOk) {}
/* 3217:3217 */    dis.close();
/* 3218:3218 */    return c;
/* 3219:     */  }
/* 3220:     */  
/* 3224:     */  public static long loadLongs(CharSequence filename, long[][] array)
/* 3225:     */    throws IOException
/* 3226:     */  {
/* 3227:3227 */    return loadLongs(new File(filename.toString()), array);
/* 3228:     */  }
/* 3229:     */  
/* 3236:     */  public static long[][] loadLongsBig(File file)
/* 3237:     */    throws IOException
/* 3238:     */  {
/* 3239:3239 */    FileInputStream fis = new FileInputStream(file);
/* 3240:3240 */    long length = fis.getChannel().size() / 8L;
/* 3241:3241 */    long[][] array = LongBigArrays.newBigArray(length);
/* 3242:3242 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3243:3243 */    for (int i = 0; i < array.length; i++) {
/* 3244:3244 */      long[] t = array[i];
/* 3245:3245 */      int l = t.length;
/* 3246:3246 */      for (int d = 0; d < l; d++) t[d] = dis.readLong();
/* 3247:     */    }
/* 3248:3248 */    dis.close();
/* 3249:3249 */    return array;
/* 3250:     */  }
/* 3251:     */  
/* 3258:     */  public static long[][] loadLongsBig(CharSequence filename)
/* 3259:     */    throws IOException
/* 3260:     */  {
/* 3261:3261 */    return loadLongsBig(new File(filename.toString()));
/* 3262:     */  }
/* 3263:     */  
/* 3268:     */  public static void storeLongs(long[][] array, long offset, long length, DataOutput dataOutput)
/* 3269:     */    throws IOException
/* 3270:     */  {
/* 3271:3271 */    LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3272:3272 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3273:3273 */      long[] t = array[i];
/* 3274:3274 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3275:3275 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeLong(t[d]);
/* 3276:     */      }
/* 3277:     */    }
/* 3278:     */  }
/* 3279:     */  
/* 3281:     */  public static void storeLongs(long[][] array, DataOutput dataOutput)
/* 3282:     */    throws IOException
/* 3283:     */  {
/* 3284:3284 */    for (int i = 0; i < array.length; i++) {
/* 3285:3285 */      long[] t = array[i];
/* 3286:3286 */      int l = t.length;
/* 3287:3287 */      for (int d = 0; d < l; d++) { dataOutput.writeLong(t[d]);
/* 3288:     */      }
/* 3289:     */    }
/* 3290:     */  }
/* 3291:     */  
/* 3295:     */  public static void storeLongs(long[][] array, long offset, long length, File file)
/* 3296:     */    throws IOException
/* 3297:     */  {
/* 3298:3298 */    LongBigArrays.ensureOffsetLength(array, offset, length);
/* 3299:3299 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3300:3300 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3301:3301 */      long[] t = array[i];
/* 3302:3302 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3303:3303 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeLong(t[d]);
/* 3304:     */    }
/* 3305:3305 */    dos.close();
/* 3306:     */  }
/* 3307:     */  
/* 3312:     */  public static void storeLongs(long[][] array, long offset, long length, CharSequence filename)
/* 3313:     */    throws IOException
/* 3314:     */  {
/* 3315:3315 */    storeLongs(array, offset, length, new File(filename.toString()));
/* 3316:     */  }
/* 3317:     */  
/* 3320:     */  public static void storeLongs(long[][] array, File file)
/* 3321:     */    throws IOException
/* 3322:     */  {
/* 3323:3323 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3324:3324 */    for (int i = 0; i < array.length; i++) {
/* 3325:3325 */      long[] t = array[i];
/* 3326:3326 */      int l = t.length;
/* 3327:3327 */      for (int d = 0; d < l; d++) dos.writeLong(t[d]);
/* 3328:     */    }
/* 3329:3329 */    dos.close();
/* 3330:     */  }
/* 3331:     */  
/* 3334:     */  public static void storeLongs(long[][] array, CharSequence filename)
/* 3335:     */    throws IOException
/* 3336:     */  {
/* 3337:3337 */    storeLongs(array, new File(filename.toString()));
/* 3338:     */  }
/* 3339:     */  
/* 3342:     */  public static void storeLongs(LongIterator i, DataOutput dataOutput)
/* 3343:     */    throws IOException
/* 3344:     */  {
/* 3345:3345 */    while (i.hasNext()) { dataOutput.writeLong(i.nextLong());
/* 3346:     */    }
/* 3347:     */  }
/* 3348:     */  
/* 3350:     */  public static void storeLongs(LongIterator i, File file)
/* 3351:     */    throws IOException
/* 3352:     */  {
/* 3353:3353 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3354:3354 */    while (i.hasNext()) dos.writeLong(i.nextLong());
/* 3355:3355 */    dos.close();
/* 3356:     */  }
/* 3357:     */  
/* 3360:     */  public static void storeLongs(LongIterator i, CharSequence filename)
/* 3361:     */    throws IOException
/* 3362:     */  {
/* 3363:3363 */    storeLongs(i, new File(filename.toString()));
/* 3364:     */  }
/* 3365:     */  
/* 3366:     */  private static final class LongDataInputWrapper extends AbstractLongIterator {
/* 3367:     */    private final DataInput dataInput;
/* 3368:3368 */    private boolean toAdvance = true;
/* 3369:3369 */    private boolean endOfProcess = false;
/* 3370:     */    private long next;
/* 3371:     */    
/* 3372:3372 */    public LongDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 3373:     */    
/* 3374:     */    public boolean hasNext() {
/* 3375:3375 */      if (!this.toAdvance) return !this.endOfProcess;
/* 3376:3376 */      this.toAdvance = false;
/* 3377:     */      try {
/* 3378:3378 */        this.next = this.dataInput.readLong();
/* 3379:     */      }
/* 3380:     */      catch (EOFException eof) {
/* 3381:3381 */        this.endOfProcess = true;
/* 3382:     */      } catch (IOException rethrow) {
/* 3383:3383 */        throw new RuntimeException(rethrow); }
/* 3384:3384 */      return !this.endOfProcess;
/* 3385:     */    }
/* 3386:     */    
/* 3387:3387 */    public long nextLong() { if (!hasNext()) throw new NoSuchElementException();
/* 3388:3388 */      this.toAdvance = true;
/* 3389:3389 */      return this.next;
/* 3390:     */    }
/* 3391:     */  }
/* 3392:     */  
/* 3395:     */  public static LongIterator asLongIterator(DataInput dataInput)
/* 3396:     */  {
/* 3397:3397 */    return new LongDataInputWrapper(dataInput);
/* 3398:     */  }
/* 3399:     */  
/* 3401:     */  public static LongIterator asLongIterator(File file)
/* 3402:     */    throws IOException
/* 3403:     */  {
/* 3404:3404 */    return new LongDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 3405:     */  }
/* 3406:     */  
/* 3408:     */  public static LongIterator asLongIterator(CharSequence filename)
/* 3409:     */    throws IOException
/* 3410:     */  {
/* 3411:3411 */    return asLongIterator(new File(filename.toString()));
/* 3412:     */  }
/* 3413:     */  
/* 3456:     */  public static int loadFloats(DataInput dataInput, float[] array, int offset, int length)
/* 3457:     */    throws IOException
/* 3458:     */  {
/* 3459:3459 */    FloatArrays.ensureOffsetLength(array, offset, length);
/* 3460:3460 */    int i = 0;
/* 3461:     */    try {
/* 3462:3462 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readFloat();
/* 3463:     */    }
/* 3464:     */    catch (EOFException itsOk) {}
/* 3465:3465 */    return i;
/* 3466:     */  }
/* 3467:     */  
/* 3471:     */  public static int loadFloats(DataInput dataInput, float[] array)
/* 3472:     */    throws IOException
/* 3473:     */  {
/* 3474:3474 */    int i = 0;
/* 3475:     */    try {
/* 3476:3476 */      int length = array.length;
/* 3477:3477 */      for (i = 0; i < length; i++) array[i] = dataInput.readFloat();
/* 3478:     */    }
/* 3479:     */    catch (EOFException itsOk) {}
/* 3480:3480 */    return i;
/* 3481:     */  }
/* 3482:     */  
/* 3488:     */  public static int loadFloats(File file, float[] array, int offset, int length)
/* 3489:     */    throws IOException
/* 3490:     */  {
/* 3491:3491 */    FloatArrays.ensureOffsetLength(array, offset, length);
/* 3492:3492 */    FileInputStream fis = new FileInputStream(file);
/* 3493:3493 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3494:3494 */    int i = 0;
/* 3495:     */    try {
/* 3496:3496 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readFloat();
/* 3497:     */    }
/* 3498:     */    catch (EOFException itsOk) {}
/* 3499:3499 */    dis.close();
/* 3500:3500 */    return i;
/* 3501:     */  }
/* 3502:     */  
/* 3508:     */  public static int loadFloats(CharSequence filename, float[] array, int offset, int length)
/* 3509:     */    throws IOException
/* 3510:     */  {
/* 3511:3511 */    return loadFloats(new File(filename.toString()), array, offset, length);
/* 3512:     */  }
/* 3513:     */  
/* 3517:     */  public static int loadFloats(File file, float[] array)
/* 3518:     */    throws IOException
/* 3519:     */  {
/* 3520:3520 */    FileInputStream fis = new FileInputStream(file);
/* 3521:3521 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3522:3522 */    int i = 0;
/* 3523:     */    try {
/* 3524:3524 */      int length = array.length;
/* 3525:3525 */      for (i = 0; i < length; i++) array[i] = dis.readFloat();
/* 3526:     */    }
/* 3527:     */    catch (EOFException itsOk) {}
/* 3528:3528 */    dis.close();
/* 3529:3529 */    return i;
/* 3530:     */  }
/* 3531:     */  
/* 3535:     */  public static int loadFloats(CharSequence filename, float[] array)
/* 3536:     */    throws IOException
/* 3537:     */  {
/* 3538:3538 */    return loadFloats(new File(filename.toString()), array);
/* 3539:     */  }
/* 3540:     */  
/* 3547:     */  public static float[] loadFloats(File file)
/* 3548:     */    throws IOException
/* 3549:     */  {
/* 3550:3550 */    FileInputStream fis = new FileInputStream(file);
/* 3551:3551 */    long length = fis.getChannel().size() / 4L;
/* 3552:3552 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 3553:3553 */    float[] array = new float[(int)length];
/* 3554:3554 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3555:3555 */    for (int i = 0; i < length; i++) array[i] = dis.readFloat();
/* 3556:3556 */    dis.close();
/* 3557:3557 */    return array;
/* 3558:     */  }
/* 3559:     */  
/* 3566:     */  public static float[] loadFloats(CharSequence filename)
/* 3567:     */    throws IOException
/* 3568:     */  {
/* 3569:3569 */    return loadFloats(new File(filename.toString()));
/* 3570:     */  }
/* 3571:     */  
/* 3576:     */  public static void storeFloats(float[] array, int offset, int length, DataOutput dataOutput)
/* 3577:     */    throws IOException
/* 3578:     */  {
/* 3579:3579 */    FloatArrays.ensureOffsetLength(array, offset, length);
/* 3580:3580 */    for (int i = 0; i < length; i++) { dataOutput.writeFloat(array[(offset + i)]);
/* 3581:     */    }
/* 3582:     */  }
/* 3583:     */  
/* 3585:     */  public static void storeFloats(float[] array, DataOutput dataOutput)
/* 3586:     */    throws IOException
/* 3587:     */  {
/* 3588:3588 */    int length = array.length;
/* 3589:3589 */    for (int i = 0; i < length; i++) { dataOutput.writeFloat(array[i]);
/* 3590:     */    }
/* 3591:     */  }
/* 3592:     */  
/* 3596:     */  public static void storeFloats(float[] array, int offset, int length, File file)
/* 3597:     */    throws IOException
/* 3598:     */  {
/* 3599:3599 */    FloatArrays.ensureOffsetLength(array, offset, length);
/* 3600:3600 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3601:3601 */    for (int i = 0; i < length; i++) dos.writeFloat(array[(offset + i)]);
/* 3602:3602 */    dos.close();
/* 3603:     */  }
/* 3604:     */  
/* 3609:     */  public static void storeFloats(float[] array, int offset, int length, CharSequence filename)
/* 3610:     */    throws IOException
/* 3611:     */  {
/* 3612:3612 */    storeFloats(array, offset, length, new File(filename.toString()));
/* 3613:     */  }
/* 3614:     */  
/* 3617:     */  public static void storeFloats(float[] array, File file)
/* 3618:     */    throws IOException
/* 3619:     */  {
/* 3620:3620 */    int length = array.length;
/* 3621:3621 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3622:3622 */    for (int i = 0; i < length; i++) dos.writeFloat(array[i]);
/* 3623:3623 */    dos.close();
/* 3624:     */  }
/* 3625:     */  
/* 3628:     */  public static void storeFloats(float[] array, CharSequence filename)
/* 3629:     */    throws IOException
/* 3630:     */  {
/* 3631:3631 */    storeFloats(array, new File(filename.toString()));
/* 3632:     */  }
/* 3633:     */  
/* 3639:     */  public static long loadFloats(DataInput dataInput, float[][] array, long offset, long length)
/* 3640:     */    throws IOException
/* 3641:     */  {
/* 3642:3642 */    FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3643:3643 */    long c = 0L;
/* 3644:     */    try {
/* 3645:3645 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3646:3646 */        float[] t = array[i];
/* 3647:3647 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3648:3648 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3649:3649 */          t[d] = dataInput.readFloat();
/* 3650:3650 */          c += 1L;
/* 3651:     */        }
/* 3652:     */      }
/* 3653:     */    }
/* 3654:     */    catch (EOFException itsOk) {}
/* 3655:3655 */    return c;
/* 3656:     */  }
/* 3657:     */  
/* 3661:     */  public static long loadFloats(DataInput dataInput, float[][] array)
/* 3662:     */    throws IOException
/* 3663:     */  {
/* 3664:3664 */    long c = 0L;
/* 3665:     */    try {
/* 3666:3666 */      for (int i = 0; i < array.length; i++) {
/* 3667:3667 */        float[] t = array[i];
/* 3668:3668 */        int l = t.length;
/* 3669:3669 */        for (int d = 0; d < l; d++) {
/* 3670:3670 */          t[d] = dataInput.readFloat();
/* 3671:3671 */          c += 1L;
/* 3672:     */        }
/* 3673:     */      }
/* 3674:     */    }
/* 3675:     */    catch (EOFException itsOk) {}
/* 3676:3676 */    return c;
/* 3677:     */  }
/* 3678:     */  
/* 3684:     */  public static long loadFloats(File file, float[][] array, long offset, long length)
/* 3685:     */    throws IOException
/* 3686:     */  {
/* 3687:3687 */    FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3688:3688 */    FileInputStream fis = new FileInputStream(file);
/* 3689:3689 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3690:3690 */    long c = 0L;
/* 3691:     */    try {
/* 3692:3692 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3693:3693 */        float[] t = array[i];
/* 3694:3694 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3695:3695 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 3696:3696 */          t[d] = dis.readFloat();
/* 3697:3697 */          c += 1L;
/* 3698:     */        }
/* 3699:     */      }
/* 3700:     */    }
/* 3701:     */    catch (EOFException itsOk) {}
/* 3702:3702 */    dis.close();
/* 3703:3703 */    return c;
/* 3704:     */  }
/* 3705:     */  
/* 3711:     */  public static long loadFloats(CharSequence filename, float[][] array, long offset, long length)
/* 3712:     */    throws IOException
/* 3713:     */  {
/* 3714:3714 */    return loadFloats(new File(filename.toString()), array, offset, length);
/* 3715:     */  }
/* 3716:     */  
/* 3720:     */  public static long loadFloats(File file, float[][] array)
/* 3721:     */    throws IOException
/* 3722:     */  {
/* 3723:3723 */    FileInputStream fis = new FileInputStream(file);
/* 3724:3724 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3725:3725 */    long c = 0L;
/* 3726:     */    try {
/* 3727:3727 */      for (int i = 0; i < array.length; i++) {
/* 3728:3728 */        float[] t = array[i];
/* 3729:3729 */        int l = t.length;
/* 3730:3730 */        for (int d = 0; d < l; d++) {
/* 3731:3731 */          t[d] = dis.readFloat();
/* 3732:3732 */          c += 1L;
/* 3733:     */        }
/* 3734:     */      }
/* 3735:     */    }
/* 3736:     */    catch (EOFException itsOk) {}
/* 3737:3737 */    dis.close();
/* 3738:3738 */    return c;
/* 3739:     */  }
/* 3740:     */  
/* 3744:     */  public static long loadFloats(CharSequence filename, float[][] array)
/* 3745:     */    throws IOException
/* 3746:     */  {
/* 3747:3747 */    return loadFloats(new File(filename.toString()), array);
/* 3748:     */  }
/* 3749:     */  
/* 3756:     */  public static float[][] loadFloatsBig(File file)
/* 3757:     */    throws IOException
/* 3758:     */  {
/* 3759:3759 */    FileInputStream fis = new FileInputStream(file);
/* 3760:3760 */    long length = fis.getChannel().size() / 4L;
/* 3761:3761 */    float[][] array = FloatBigArrays.newBigArray(length);
/* 3762:3762 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 3763:3763 */    for (int i = 0; i < array.length; i++) {
/* 3764:3764 */      float[] t = array[i];
/* 3765:3765 */      int l = t.length;
/* 3766:3766 */      for (int d = 0; d < l; d++) t[d] = dis.readFloat();
/* 3767:     */    }
/* 3768:3768 */    dis.close();
/* 3769:3769 */    return array;
/* 3770:     */  }
/* 3771:     */  
/* 3778:     */  public static float[][] loadFloatsBig(CharSequence filename)
/* 3779:     */    throws IOException
/* 3780:     */  {
/* 3781:3781 */    return loadFloatsBig(new File(filename.toString()));
/* 3782:     */  }
/* 3783:     */  
/* 3788:     */  public static void storeFloats(float[][] array, long offset, long length, DataOutput dataOutput)
/* 3789:     */    throws IOException
/* 3790:     */  {
/* 3791:3791 */    FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3792:3792 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3793:3793 */      float[] t = array[i];
/* 3794:3794 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3795:3795 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeFloat(t[d]);
/* 3796:     */      }
/* 3797:     */    }
/* 3798:     */  }
/* 3799:     */  
/* 3801:     */  public static void storeFloats(float[][] array, DataOutput dataOutput)
/* 3802:     */    throws IOException
/* 3803:     */  {
/* 3804:3804 */    for (int i = 0; i < array.length; i++) {
/* 3805:3805 */      float[] t = array[i];
/* 3806:3806 */      int l = t.length;
/* 3807:3807 */      for (int d = 0; d < l; d++) { dataOutput.writeFloat(t[d]);
/* 3808:     */      }
/* 3809:     */    }
/* 3810:     */  }
/* 3811:     */  
/* 3815:     */  public static void storeFloats(float[][] array, long offset, long length, File file)
/* 3816:     */    throws IOException
/* 3817:     */  {
/* 3818:3818 */    FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 3819:3819 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3820:3820 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 3821:3821 */      float[] t = array[i];
/* 3822:3822 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 3823:3823 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeFloat(t[d]);
/* 3824:     */    }
/* 3825:3825 */    dos.close();
/* 3826:     */  }
/* 3827:     */  
/* 3832:     */  public static void storeFloats(float[][] array, long offset, long length, CharSequence filename)
/* 3833:     */    throws IOException
/* 3834:     */  {
/* 3835:3835 */    storeFloats(array, offset, length, new File(filename.toString()));
/* 3836:     */  }
/* 3837:     */  
/* 3840:     */  public static void storeFloats(float[][] array, File file)
/* 3841:     */    throws IOException
/* 3842:     */  {
/* 3843:3843 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3844:3844 */    for (int i = 0; i < array.length; i++) {
/* 3845:3845 */      float[] t = array[i];
/* 3846:3846 */      int l = t.length;
/* 3847:3847 */      for (int d = 0; d < l; d++) dos.writeFloat(t[d]);
/* 3848:     */    }
/* 3849:3849 */    dos.close();
/* 3850:     */  }
/* 3851:     */  
/* 3854:     */  public static void storeFloats(float[][] array, CharSequence filename)
/* 3855:     */    throws IOException
/* 3856:     */  {
/* 3857:3857 */    storeFloats(array, new File(filename.toString()));
/* 3858:     */  }
/* 3859:     */  
/* 3862:     */  public static void storeFloats(FloatIterator i, DataOutput dataOutput)
/* 3863:     */    throws IOException
/* 3864:     */  {
/* 3865:3865 */    while (i.hasNext()) { dataOutput.writeFloat(i.nextFloat());
/* 3866:     */    }
/* 3867:     */  }
/* 3868:     */  
/* 3870:     */  public static void storeFloats(FloatIterator i, File file)
/* 3871:     */    throws IOException
/* 3872:     */  {
/* 3873:3873 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 3874:3874 */    while (i.hasNext()) dos.writeFloat(i.nextFloat());
/* 3875:3875 */    dos.close();
/* 3876:     */  }
/* 3877:     */  
/* 3880:     */  public static void storeFloats(FloatIterator i, CharSequence filename)
/* 3881:     */    throws IOException
/* 3882:     */  {
/* 3883:3883 */    storeFloats(i, new File(filename.toString()));
/* 3884:     */  }
/* 3885:     */  
/* 3886:     */  private static final class FloatDataInputWrapper extends AbstractFloatIterator {
/* 3887:     */    private final DataInput dataInput;
/* 3888:3888 */    private boolean toAdvance = true;
/* 3889:3889 */    private boolean endOfProcess = false;
/* 3890:     */    private float next;
/* 3891:     */    
/* 3892:3892 */    public FloatDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 3893:     */    
/* 3894:     */    public boolean hasNext() {
/* 3895:3895 */      if (!this.toAdvance) return !this.endOfProcess;
/* 3896:3896 */      this.toAdvance = false;
/* 3897:     */      try {
/* 3898:3898 */        this.next = this.dataInput.readFloat();
/* 3899:     */      }
/* 3900:     */      catch (EOFException eof) {
/* 3901:3901 */        this.endOfProcess = true;
/* 3902:     */      } catch (IOException rethrow) {
/* 3903:3903 */        throw new RuntimeException(rethrow); }
/* 3904:3904 */      return !this.endOfProcess;
/* 3905:     */    }
/* 3906:     */    
/* 3907:3907 */    public float nextFloat() { if (!hasNext()) throw new NoSuchElementException();
/* 3908:3908 */      this.toAdvance = true;
/* 3909:3909 */      return this.next;
/* 3910:     */    }
/* 3911:     */  }
/* 3912:     */  
/* 3915:     */  public static FloatIterator asFloatIterator(DataInput dataInput)
/* 3916:     */  {
/* 3917:3917 */    return new FloatDataInputWrapper(dataInput);
/* 3918:     */  }
/* 3919:     */  
/* 3921:     */  public static FloatIterator asFloatIterator(File file)
/* 3922:     */    throws IOException
/* 3923:     */  {
/* 3924:3924 */    return new FloatDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 3925:     */  }
/* 3926:     */  
/* 3928:     */  public static FloatIterator asFloatIterator(CharSequence filename)
/* 3929:     */    throws IOException
/* 3930:     */  {
/* 3931:3931 */    return asFloatIterator(new File(filename.toString()));
/* 3932:     */  }
/* 3933:     */  
/* 3976:     */  public static int loadDoubles(DataInput dataInput, double[] array, int offset, int length)
/* 3977:     */    throws IOException
/* 3978:     */  {
/* 3979:3979 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 3980:3980 */    int i = 0;
/* 3981:     */    try {
/* 3982:3982 */      for (i = 0; i < length; i++) array[(i + offset)] = dataInput.readDouble();
/* 3983:     */    }
/* 3984:     */    catch (EOFException itsOk) {}
/* 3985:3985 */    return i;
/* 3986:     */  }
/* 3987:     */  
/* 3991:     */  public static int loadDoubles(DataInput dataInput, double[] array)
/* 3992:     */    throws IOException
/* 3993:     */  {
/* 3994:3994 */    int i = 0;
/* 3995:     */    try {
/* 3996:3996 */      int length = array.length;
/* 3997:3997 */      for (i = 0; i < length; i++) array[i] = dataInput.readDouble();
/* 3998:     */    }
/* 3999:     */    catch (EOFException itsOk) {}
/* 4000:4000 */    return i;
/* 4001:     */  }
/* 4002:     */  
/* 4008:     */  public static int loadDoubles(File file, double[] array, int offset, int length)
/* 4009:     */    throws IOException
/* 4010:     */  {
/* 4011:4011 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 4012:4012 */    FileInputStream fis = new FileInputStream(file);
/* 4013:4013 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4014:4014 */    int i = 0;
/* 4015:     */    try {
/* 4016:4016 */      for (i = 0; i < length; i++) array[(i + offset)] = dis.readDouble();
/* 4017:     */    }
/* 4018:     */    catch (EOFException itsOk) {}
/* 4019:4019 */    dis.close();
/* 4020:4020 */    return i;
/* 4021:     */  }
/* 4022:     */  
/* 4028:     */  public static int loadDoubles(CharSequence filename, double[] array, int offset, int length)
/* 4029:     */    throws IOException
/* 4030:     */  {
/* 4031:4031 */    return loadDoubles(new File(filename.toString()), array, offset, length);
/* 4032:     */  }
/* 4033:     */  
/* 4037:     */  public static int loadDoubles(File file, double[] array)
/* 4038:     */    throws IOException
/* 4039:     */  {
/* 4040:4040 */    FileInputStream fis = new FileInputStream(file);
/* 4041:4041 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4042:4042 */    int i = 0;
/* 4043:     */    try {
/* 4044:4044 */      int length = array.length;
/* 4045:4045 */      for (i = 0; i < length; i++) array[i] = dis.readDouble();
/* 4046:     */    }
/* 4047:     */    catch (EOFException itsOk) {}
/* 4048:4048 */    dis.close();
/* 4049:4049 */    return i;
/* 4050:     */  }
/* 4051:     */  
/* 4055:     */  public static int loadDoubles(CharSequence filename, double[] array)
/* 4056:     */    throws IOException
/* 4057:     */  {
/* 4058:4058 */    return loadDoubles(new File(filename.toString()), array);
/* 4059:     */  }
/* 4060:     */  
/* 4067:     */  public static double[] loadDoubles(File file)
/* 4068:     */    throws IOException
/* 4069:     */  {
/* 4070:4070 */    FileInputStream fis = new FileInputStream(file);
/* 4071:4071 */    long length = fis.getChannel().size() / 8L;
/* 4072:4072 */    if (length > 2147483647L) throw new IllegalArgumentException("File too long: " + fis.getChannel().size() + " bytes (" + length + " elements)");
/* 4073:4073 */    double[] array = new double[(int)length];
/* 4074:4074 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4075:4075 */    for (int i = 0; i < length; i++) array[i] = dis.readDouble();
/* 4076:4076 */    dis.close();
/* 4077:4077 */    return array;
/* 4078:     */  }
/* 4079:     */  
/* 4086:     */  public static double[] loadDoubles(CharSequence filename)
/* 4087:     */    throws IOException
/* 4088:     */  {
/* 4089:4089 */    return loadDoubles(new File(filename.toString()));
/* 4090:     */  }
/* 4091:     */  
/* 4096:     */  public static void storeDoubles(double[] array, int offset, int length, DataOutput dataOutput)
/* 4097:     */    throws IOException
/* 4098:     */  {
/* 4099:4099 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 4100:4100 */    for (int i = 0; i < length; i++) { dataOutput.writeDouble(array[(offset + i)]);
/* 4101:     */    }
/* 4102:     */  }
/* 4103:     */  
/* 4105:     */  public static void storeDoubles(double[] array, DataOutput dataOutput)
/* 4106:     */    throws IOException
/* 4107:     */  {
/* 4108:4108 */    int length = array.length;
/* 4109:4109 */    for (int i = 0; i < length; i++) { dataOutput.writeDouble(array[i]);
/* 4110:     */    }
/* 4111:     */  }
/* 4112:     */  
/* 4116:     */  public static void storeDoubles(double[] array, int offset, int length, File file)
/* 4117:     */    throws IOException
/* 4118:     */  {
/* 4119:4119 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 4120:4120 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4121:4121 */    for (int i = 0; i < length; i++) dos.writeDouble(array[(offset + i)]);
/* 4122:4122 */    dos.close();
/* 4123:     */  }
/* 4124:     */  
/* 4129:     */  public static void storeDoubles(double[] array, int offset, int length, CharSequence filename)
/* 4130:     */    throws IOException
/* 4131:     */  {
/* 4132:4132 */    storeDoubles(array, offset, length, new File(filename.toString()));
/* 4133:     */  }
/* 4134:     */  
/* 4137:     */  public static void storeDoubles(double[] array, File file)
/* 4138:     */    throws IOException
/* 4139:     */  {
/* 4140:4140 */    int length = array.length;
/* 4141:4141 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4142:4142 */    for (int i = 0; i < length; i++) dos.writeDouble(array[i]);
/* 4143:4143 */    dos.close();
/* 4144:     */  }
/* 4145:     */  
/* 4148:     */  public static void storeDoubles(double[] array, CharSequence filename)
/* 4149:     */    throws IOException
/* 4150:     */  {
/* 4151:4151 */    storeDoubles(array, new File(filename.toString()));
/* 4152:     */  }
/* 4153:     */  
/* 4159:     */  public static long loadDoubles(DataInput dataInput, double[][] array, long offset, long length)
/* 4160:     */    throws IOException
/* 4161:     */  {
/* 4162:4162 */    DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4163:4163 */    long c = 0L;
/* 4164:     */    try {
/* 4165:4165 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4166:4166 */        double[] t = array[i];
/* 4167:4167 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4168:4168 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 4169:4169 */          t[d] = dataInput.readDouble();
/* 4170:4170 */          c += 1L;
/* 4171:     */        }
/* 4172:     */      }
/* 4173:     */    }
/* 4174:     */    catch (EOFException itsOk) {}
/* 4175:4175 */    return c;
/* 4176:     */  }
/* 4177:     */  
/* 4181:     */  public static long loadDoubles(DataInput dataInput, double[][] array)
/* 4182:     */    throws IOException
/* 4183:     */  {
/* 4184:4184 */    long c = 0L;
/* 4185:     */    try {
/* 4186:4186 */      for (int i = 0; i < array.length; i++) {
/* 4187:4187 */        double[] t = array[i];
/* 4188:4188 */        int l = t.length;
/* 4189:4189 */        for (int d = 0; d < l; d++) {
/* 4190:4190 */          t[d] = dataInput.readDouble();
/* 4191:4191 */          c += 1L;
/* 4192:     */        }
/* 4193:     */      }
/* 4194:     */    }
/* 4195:     */    catch (EOFException itsOk) {}
/* 4196:4196 */    return c;
/* 4197:     */  }
/* 4198:     */  
/* 4204:     */  public static long loadDoubles(File file, double[][] array, long offset, long length)
/* 4205:     */    throws IOException
/* 4206:     */  {
/* 4207:4207 */    DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4208:4208 */    FileInputStream fis = new FileInputStream(file);
/* 4209:4209 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4210:4210 */    long c = 0L;
/* 4211:     */    try {
/* 4212:4212 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4213:4213 */        double[] t = array[i];
/* 4214:4214 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4215:4215 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) {
/* 4216:4216 */          t[d] = dis.readDouble();
/* 4217:4217 */          c += 1L;
/* 4218:     */        }
/* 4219:     */      }
/* 4220:     */    }
/* 4221:     */    catch (EOFException itsOk) {}
/* 4222:4222 */    dis.close();
/* 4223:4223 */    return c;
/* 4224:     */  }
/* 4225:     */  
/* 4231:     */  public static long loadDoubles(CharSequence filename, double[][] array, long offset, long length)
/* 4232:     */    throws IOException
/* 4233:     */  {
/* 4234:4234 */    return loadDoubles(new File(filename.toString()), array, offset, length);
/* 4235:     */  }
/* 4236:     */  
/* 4240:     */  public static long loadDoubles(File file, double[][] array)
/* 4241:     */    throws IOException
/* 4242:     */  {
/* 4243:4243 */    FileInputStream fis = new FileInputStream(file);
/* 4244:4244 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4245:4245 */    long c = 0L;
/* 4246:     */    try {
/* 4247:4247 */      for (int i = 0; i < array.length; i++) {
/* 4248:4248 */        double[] t = array[i];
/* 4249:4249 */        int l = t.length;
/* 4250:4250 */        for (int d = 0; d < l; d++) {
/* 4251:4251 */          t[d] = dis.readDouble();
/* 4252:4252 */          c += 1L;
/* 4253:     */        }
/* 4254:     */      }
/* 4255:     */    }
/* 4256:     */    catch (EOFException itsOk) {}
/* 4257:4257 */    dis.close();
/* 4258:4258 */    return c;
/* 4259:     */  }
/* 4260:     */  
/* 4264:     */  public static long loadDoubles(CharSequence filename, double[][] array)
/* 4265:     */    throws IOException
/* 4266:     */  {
/* 4267:4267 */    return loadDoubles(new File(filename.toString()), array);
/* 4268:     */  }
/* 4269:     */  
/* 4276:     */  public static double[][] loadDoublesBig(File file)
/* 4277:     */    throws IOException
/* 4278:     */  {
/* 4279:4279 */    FileInputStream fis = new FileInputStream(file);
/* 4280:4280 */    long length = fis.getChannel().size() / 8L;
/* 4281:4281 */    double[][] array = DoubleBigArrays.newBigArray(length);
/* 4282:4282 */    DataInputStream dis = new DataInputStream(new FastBufferedInputStream(fis));
/* 4283:4283 */    for (int i = 0; i < array.length; i++) {
/* 4284:4284 */      double[] t = array[i];
/* 4285:4285 */      int l = t.length;
/* 4286:4286 */      for (int d = 0; d < l; d++) t[d] = dis.readDouble();
/* 4287:     */    }
/* 4288:4288 */    dis.close();
/* 4289:4289 */    return array;
/* 4290:     */  }
/* 4291:     */  
/* 4298:     */  public static double[][] loadDoublesBig(CharSequence filename)
/* 4299:     */    throws IOException
/* 4300:     */  {
/* 4301:4301 */    return loadDoublesBig(new File(filename.toString()));
/* 4302:     */  }
/* 4303:     */  
/* 4308:     */  public static void storeDoubles(double[][] array, long offset, long length, DataOutput dataOutput)
/* 4309:     */    throws IOException
/* 4310:     */  {
/* 4311:4311 */    DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4312:4312 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4313:4313 */      double[] t = array[i];
/* 4314:4314 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4315:4315 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { dataOutput.writeDouble(t[d]);
/* 4316:     */      }
/* 4317:     */    }
/* 4318:     */  }
/* 4319:     */  
/* 4321:     */  public static void storeDoubles(double[][] array, DataOutput dataOutput)
/* 4322:     */    throws IOException
/* 4323:     */  {
/* 4324:4324 */    for (int i = 0; i < array.length; i++) {
/* 4325:4325 */      double[] t = array[i];
/* 4326:4326 */      int l = t.length;
/* 4327:4327 */      for (int d = 0; d < l; d++) { dataOutput.writeDouble(t[d]);
/* 4328:     */      }
/* 4329:     */    }
/* 4330:     */  }
/* 4331:     */  
/* 4335:     */  public static void storeDoubles(double[][] array, long offset, long length, File file)
/* 4336:     */    throws IOException
/* 4337:     */  {
/* 4338:4338 */    DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 4339:4339 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4340:4340 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 4341:4341 */      double[] t = array[i];
/* 4342:4342 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 4343:4343 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) dos.writeDouble(t[d]);
/* 4344:     */    }
/* 4345:4345 */    dos.close();
/* 4346:     */  }
/* 4347:     */  
/* 4352:     */  public static void storeDoubles(double[][] array, long offset, long length, CharSequence filename)
/* 4353:     */    throws IOException
/* 4354:     */  {
/* 4355:4355 */    storeDoubles(array, offset, length, new File(filename.toString()));
/* 4356:     */  }
/* 4357:     */  
/* 4360:     */  public static void storeDoubles(double[][] array, File file)
/* 4361:     */    throws IOException
/* 4362:     */  {
/* 4363:4363 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4364:4364 */    for (int i = 0; i < array.length; i++) {
/* 4365:4365 */      double[] t = array[i];
/* 4366:4366 */      int l = t.length;
/* 4367:4367 */      for (int d = 0; d < l; d++) dos.writeDouble(t[d]);
/* 4368:     */    }
/* 4369:4369 */    dos.close();
/* 4370:     */  }
/* 4371:     */  
/* 4374:     */  public static void storeDoubles(double[][] array, CharSequence filename)
/* 4375:     */    throws IOException
/* 4376:     */  {
/* 4377:4377 */    storeDoubles(array, new File(filename.toString()));
/* 4378:     */  }
/* 4379:     */  
/* 4382:     */  public static void storeDoubles(DoubleIterator i, DataOutput dataOutput)
/* 4383:     */    throws IOException
/* 4384:     */  {
/* 4385:4385 */    while (i.hasNext()) { dataOutput.writeDouble(i.nextDouble());
/* 4386:     */    }
/* 4387:     */  }
/* 4388:     */  
/* 4390:     */  public static void storeDoubles(DoubleIterator i, File file)
/* 4391:     */    throws IOException
/* 4392:     */  {
/* 4393:4393 */    DataOutputStream dos = new DataOutputStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 4394:4394 */    while (i.hasNext()) dos.writeDouble(i.nextDouble());
/* 4395:4395 */    dos.close();
/* 4396:     */  }
/* 4397:     */  
/* 4400:     */  public static void storeDoubles(DoubleIterator i, CharSequence filename)
/* 4401:     */    throws IOException
/* 4402:     */  {
/* 4403:4403 */    storeDoubles(i, new File(filename.toString()));
/* 4404:     */  }
/* 4405:     */  
/* 4406:     */  private static final class DoubleDataInputWrapper extends AbstractDoubleIterator {
/* 4407:     */    private final DataInput dataInput;
/* 4408:4408 */    private boolean toAdvance = true;
/* 4409:4409 */    private boolean endOfProcess = false;
/* 4410:     */    private double next;
/* 4411:     */    
/* 4412:4412 */    public DoubleDataInputWrapper(DataInput dataInput) { this.dataInput = dataInput; }
/* 4413:     */    
/* 4414:     */    public boolean hasNext() {
/* 4415:4415 */      if (!this.toAdvance) return !this.endOfProcess;
/* 4416:4416 */      this.toAdvance = false;
/* 4417:     */      try {
/* 4418:4418 */        this.next = this.dataInput.readDouble();
/* 4419:     */      }
/* 4420:     */      catch (EOFException eof) {
/* 4421:4421 */        this.endOfProcess = true;
/* 4422:     */      } catch (IOException rethrow) {
/* 4423:4423 */        throw new RuntimeException(rethrow); }
/* 4424:4424 */      return !this.endOfProcess;
/* 4425:     */    }
/* 4426:     */    
/* 4427:4427 */    public double nextDouble() { if (!hasNext()) throw new NoSuchElementException();
/* 4428:4428 */      this.toAdvance = true;
/* 4429:4429 */      return this.next;
/* 4430:     */    }
/* 4431:     */  }
/* 4432:     */  
/* 4435:     */  public static DoubleIterator asDoubleIterator(DataInput dataInput)
/* 4436:     */  {
/* 4437:4437 */    return new DoubleDataInputWrapper(dataInput);
/* 4438:     */  }
/* 4439:     */  
/* 4441:     */  public static DoubleIterator asDoubleIterator(File file)
/* 4442:     */    throws IOException
/* 4443:     */  {
/* 4444:4444 */    return new DoubleDataInputWrapper(new DataInputStream(new FastBufferedInputStream(new FileInputStream(file))));
/* 4445:     */  }
/* 4446:     */  
/* 4448:     */  public static DoubleIterator asDoubleIterator(CharSequence filename)
/* 4449:     */    throws IOException
/* 4450:     */  {
/* 4451:4451 */    return asDoubleIterator(new File(filename.toString()));
/* 4452:     */  }
/* 4453:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.BinIO
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */