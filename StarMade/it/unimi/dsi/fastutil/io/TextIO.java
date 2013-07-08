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
/*   12:     */import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*   13:     */import it.unimi.dsi.fastutil.doubles.DoubleArrays;
/*   14:     */import it.unimi.dsi.fastutil.doubles.DoubleBigArrays;
/*   15:     */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   16:     */import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*   17:     */import it.unimi.dsi.fastutil.floats.FloatArrays;
/*   18:     */import it.unimi.dsi.fastutil.floats.FloatBigArrays;
/*   19:     */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   20:     */import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*   21:     */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   22:     */import it.unimi.dsi.fastutil.ints.IntBigArrays;
/*   23:     */import it.unimi.dsi.fastutil.ints.IntIterator;
/*   24:     */import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*   25:     */import it.unimi.dsi.fastutil.longs.LongArrays;
/*   26:     */import it.unimi.dsi.fastutil.longs.LongBigArrays;
/*   27:     */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   28:     */import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*   29:     */import it.unimi.dsi.fastutil.shorts.ShortArrays;
/*   30:     */import it.unimi.dsi.fastutil.shorts.ShortBigArrays;
/*   31:     */import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*   32:     */import java.io.BufferedReader;
/*   33:     */import java.io.EOFException;
/*   34:     */import java.io.File;
/*   35:     */import java.io.FileOutputStream;
/*   36:     */import java.io.FileReader;
/*   37:     */import java.io.IOException;
/*   38:     */import java.io.PrintStream;
/*   39:     */import java.util.NoSuchElementException;
/*   40:     */
/*  129:     */public class TextIO
/*  130:     */{
/*  131:     */  public static final int BUFFER_SIZE = 8192;
/*  132:     */  
/*  133:     */  public static int loadBooleans(BufferedReader reader, boolean[] array, int offset, int length)
/*  134:     */    throws IOException
/*  135:     */  {
/*  136: 136 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/*  137: 137 */    int i = 0;
/*  138:     */    try
/*  139:     */    {
/*  140: 140 */      for (i = 0; i < length; i++) { String s;
/*  141: 141 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Boolean.parseBoolean(s);
/*  142:     */      }
/*  143:     */    }
/*  144:     */    catch (EOFException itsOk) {}
/*  145: 145 */    return i;
/*  146:     */  }
/*  147:     */  
/*  151:     */  public static int loadBooleans(BufferedReader reader, boolean[] array)
/*  152:     */    throws IOException
/*  153:     */  {
/*  154: 154 */    return loadBooleans(reader, array, 0, array.length);
/*  155:     */  }
/*  156:     */  
/*  162:     */  public static int loadBooleans(File file, boolean[] array, int offset, int length)
/*  163:     */    throws IOException
/*  164:     */  {
/*  165: 165 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/*  166: 166 */    int result = loadBooleans(reader, array, offset, length);
/*  167: 167 */    reader.close();
/*  168: 168 */    return result;
/*  169:     */  }
/*  170:     */  
/*  176:     */  public static int loadBooleans(CharSequence filename, boolean[] array, int offset, int length)
/*  177:     */    throws IOException
/*  178:     */  {
/*  179: 179 */    return loadBooleans(new File(filename.toString()), array, offset, length);
/*  180:     */  }
/*  181:     */  
/*  185:     */  public static int loadBooleans(File file, boolean[] array)
/*  186:     */    throws IOException
/*  187:     */  {
/*  188: 188 */    return loadBooleans(file, array, 0, array.length);
/*  189:     */  }
/*  190:     */  
/*  194:     */  public static int loadBooleans(CharSequence filename, boolean[] array)
/*  195:     */    throws IOException
/*  196:     */  {
/*  197: 197 */    return loadBooleans(filename, array, 0, array.length);
/*  198:     */  }
/*  199:     */  
/*  205:     */  public static void storeBooleans(boolean[] array, int offset, int length, PrintStream stream)
/*  206:     */  {
/*  207: 207 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/*  208: 208 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/*  209:     */    }
/*  210:     */  }
/*  211:     */  
/*  214:     */  public static void storeBooleans(boolean[] array, PrintStream stream)
/*  215:     */  {
/*  216: 216 */    storeBooleans(array, 0, array.length, stream);
/*  217:     */  }
/*  218:     */  
/*  223:     */  public static void storeBooleans(boolean[] array, int offset, int length, File file)
/*  224:     */    throws IOException
/*  225:     */  {
/*  226: 226 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  227: 227 */    storeBooleans(array, offset, length, stream);
/*  228: 228 */    stream.close();
/*  229:     */  }
/*  230:     */  
/*  235:     */  public static void storeBooleans(boolean[] array, int offset, int length, CharSequence filename)
/*  236:     */    throws IOException
/*  237:     */  {
/*  238: 238 */    storeBooleans(array, offset, length, new File(filename.toString()));
/*  239:     */  }
/*  240:     */  
/*  243:     */  public static void storeBooleans(boolean[] array, File file)
/*  244:     */    throws IOException
/*  245:     */  {
/*  246: 246 */    storeBooleans(array, 0, array.length, file);
/*  247:     */  }
/*  248:     */  
/*  251:     */  public static void storeBooleans(boolean[] array, CharSequence filename)
/*  252:     */    throws IOException
/*  253:     */  {
/*  254: 254 */    storeBooleans(array, 0, array.length, filename);
/*  255:     */  }
/*  256:     */  
/*  260:     */  public static void storeBooleans(BooleanIterator i, PrintStream stream)
/*  261:     */  {
/*  262: 262 */    while (i.hasNext()) { stream.println(i.nextBoolean());
/*  263:     */    }
/*  264:     */  }
/*  265:     */  
/*  267:     */  public static void storeBooleans(BooleanIterator i, File file)
/*  268:     */    throws IOException
/*  269:     */  {
/*  270: 270 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  271: 271 */    storeBooleans(i, stream);
/*  272: 272 */    stream.close();
/*  273:     */  }
/*  274:     */  
/*  277:     */  public static void storeBooleans(BooleanIterator i, CharSequence filename)
/*  278:     */    throws IOException
/*  279:     */  {
/*  280: 280 */    storeBooleans(i, new File(filename.toString()));
/*  281:     */  }
/*  282:     */  
/*  288:     */  public static long loadBooleans(BufferedReader reader, boolean[][] array, long offset, long length)
/*  289:     */    throws IOException
/*  290:     */  {
/*  291: 291 */    BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  292: 292 */    long c = 0L;
/*  293:     */    try
/*  294:     */    {
/*  295: 295 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  296: 296 */        boolean[] t = array[i];
/*  297: 297 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  298: 298 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/*  299: 299 */          if ((s = reader.readLine()) != null) t[d] = Boolean.parseBoolean(s); else
/*  300: 300 */            return c;
/*  301: 301 */          c += 1L;
/*  302:     */        }
/*  303:     */      }
/*  304:     */    }
/*  305:     */    catch (EOFException itsOk) {}
/*  306: 306 */    return c;
/*  307:     */  }
/*  308:     */  
/*  312:     */  public static long loadBooleans(BufferedReader reader, boolean[][] array)
/*  313:     */    throws IOException
/*  314:     */  {
/*  315: 315 */    return loadBooleans(reader, array, 0L, BooleanBigArrays.length(array));
/*  316:     */  }
/*  317:     */  
/*  323:     */  public static long loadBooleans(File file, boolean[][] array, long offset, long length)
/*  324:     */    throws IOException
/*  325:     */  {
/*  326: 326 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/*  327: 327 */    long result = loadBooleans(reader, array, offset, length);
/*  328: 328 */    reader.close();
/*  329: 329 */    return result;
/*  330:     */  }
/*  331:     */  
/*  337:     */  public static long loadBooleans(CharSequence filename, boolean[][] array, long offset, long length)
/*  338:     */    throws IOException
/*  339:     */  {
/*  340: 340 */    return loadBooleans(new File(filename.toString()), array, offset, length);
/*  341:     */  }
/*  342:     */  
/*  346:     */  public static long loadBooleans(File file, boolean[][] array)
/*  347:     */    throws IOException
/*  348:     */  {
/*  349: 349 */    return loadBooleans(file, array, 0L, BooleanBigArrays.length(array));
/*  350:     */  }
/*  351:     */  
/*  355:     */  public static long loadBooleans(CharSequence filename, boolean[][] array)
/*  356:     */    throws IOException
/*  357:     */  {
/*  358: 358 */    return loadBooleans(filename, array, 0L, BooleanBigArrays.length(array));
/*  359:     */  }
/*  360:     */  
/*  366:     */  public static void storeBooleans(boolean[][] array, long offset, long length, PrintStream stream)
/*  367:     */  {
/*  368: 368 */    BooleanBigArrays.ensureOffsetLength(array, offset, length);
/*  369: 369 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  370: 370 */      boolean[] t = array[i];
/*  371: 371 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  372: 372 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/*  373:     */      }
/*  374:     */    }
/*  375:     */  }
/*  376:     */  
/*  379:     */  public static void storeBooleans(boolean[][] array, PrintStream stream)
/*  380:     */  {
/*  381: 381 */    storeBooleans(array, 0L, BooleanBigArrays.length(array), stream);
/*  382:     */  }
/*  383:     */  
/*  388:     */  public static void storeBooleans(boolean[][] array, long offset, long length, File file)
/*  389:     */    throws IOException
/*  390:     */  {
/*  391: 391 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  392: 392 */    storeBooleans(array, offset, length, stream);
/*  393: 393 */    stream.close();
/*  394:     */  }
/*  395:     */  
/*  400:     */  public static void storeBooleans(boolean[][] array, long offset, long length, CharSequence filename)
/*  401:     */    throws IOException
/*  402:     */  {
/*  403: 403 */    storeBooleans(array, offset, length, new File(filename.toString()));
/*  404:     */  }
/*  405:     */  
/*  408:     */  public static void storeBooleans(boolean[][] array, File file)
/*  409:     */    throws IOException
/*  410:     */  {
/*  411: 411 */    storeBooleans(array, 0L, BooleanBigArrays.length(array), file);
/*  412:     */  }
/*  413:     */  
/*  416:     */  public static void storeBooleans(boolean[][] array, CharSequence filename)
/*  417:     */    throws IOException
/*  418:     */  {
/*  419: 419 */    storeBooleans(array, 0L, BooleanBigArrays.length(array), filename);
/*  420:     */  }
/*  421:     */  
/*  422:     */  private static final class BooleanReaderWrapper extends AbstractBooleanIterator {
/*  423:     */    private final BufferedReader reader;
/*  424: 424 */    private boolean toAdvance = true;
/*  425:     */    private String s;
/*  426:     */    private boolean next;
/*  427:     */    
/*  428: 428 */    public BooleanReaderWrapper(BufferedReader reader) { this.reader = reader; }
/*  429:     */    
/*  430:     */    public boolean hasNext() {
/*  431: 431 */      if (!this.toAdvance) return this.s != null;
/*  432: 432 */      this.toAdvance = false;
/*  433:     */      try {
/*  434: 434 */        this.s = this.reader.readLine();
/*  435:     */      }
/*  436:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/*  437: 437 */        throw new RuntimeException(rethrow); }
/*  438: 438 */      if (this.s == null) return false;
/*  439: 439 */      this.next = Boolean.parseBoolean(this.s);
/*  440: 440 */      return true;
/*  441:     */    }
/*  442:     */    
/*  443: 443 */    public boolean nextBoolean() { if (!hasNext()) throw new NoSuchElementException();
/*  444: 444 */      this.toAdvance = true;
/*  445: 445 */      return this.next;
/*  446:     */    }
/*  447:     */  }
/*  448:     */  
/*  451:     */  public static BooleanIterator asBooleanIterator(BufferedReader reader)
/*  452:     */  {
/*  453: 453 */    return new BooleanReaderWrapper(reader);
/*  454:     */  }
/*  455:     */  
/*  457:     */  public static BooleanIterator asBooleanIterator(File file)
/*  458:     */    throws IOException
/*  459:     */  {
/*  460: 460 */    return new BooleanReaderWrapper(new BufferedReader(new FileReader(file)));
/*  461:     */  }
/*  462:     */  
/*  464:     */  public static BooleanIterator asBooleanIterator(CharSequence filename)
/*  465:     */    throws IOException
/*  466:     */  {
/*  467: 467 */    return asBooleanIterator(new File(filename.toString()));
/*  468:     */  }
/*  469:     */  
/*  512:     */  public static int loadBytes(BufferedReader reader, byte[] array, int offset, int length)
/*  513:     */    throws IOException
/*  514:     */  {
/*  515: 515 */    ByteArrays.ensureOffsetLength(array, offset, length);
/*  516: 516 */    int i = 0;
/*  517:     */    try
/*  518:     */    {
/*  519: 519 */      for (i = 0; i < length; i++) { String s;
/*  520: 520 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Byte.parseByte(s);
/*  521:     */      }
/*  522:     */    }
/*  523:     */    catch (EOFException itsOk) {}
/*  524: 524 */    return i;
/*  525:     */  }
/*  526:     */  
/*  530:     */  public static int loadBytes(BufferedReader reader, byte[] array)
/*  531:     */    throws IOException
/*  532:     */  {
/*  533: 533 */    return loadBytes(reader, array, 0, array.length);
/*  534:     */  }
/*  535:     */  
/*  541:     */  public static int loadBytes(File file, byte[] array, int offset, int length)
/*  542:     */    throws IOException
/*  543:     */  {
/*  544: 544 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/*  545: 545 */    int result = loadBytes(reader, array, offset, length);
/*  546: 546 */    reader.close();
/*  547: 547 */    return result;
/*  548:     */  }
/*  549:     */  
/*  555:     */  public static int loadBytes(CharSequence filename, byte[] array, int offset, int length)
/*  556:     */    throws IOException
/*  557:     */  {
/*  558: 558 */    return loadBytes(new File(filename.toString()), array, offset, length);
/*  559:     */  }
/*  560:     */  
/*  564:     */  public static int loadBytes(File file, byte[] array)
/*  565:     */    throws IOException
/*  566:     */  {
/*  567: 567 */    return loadBytes(file, array, 0, array.length);
/*  568:     */  }
/*  569:     */  
/*  573:     */  public static int loadBytes(CharSequence filename, byte[] array)
/*  574:     */    throws IOException
/*  575:     */  {
/*  576: 576 */    return loadBytes(filename, array, 0, array.length);
/*  577:     */  }
/*  578:     */  
/*  584:     */  public static void storeBytes(byte[] array, int offset, int length, PrintStream stream)
/*  585:     */  {
/*  586: 586 */    ByteArrays.ensureOffsetLength(array, offset, length);
/*  587: 587 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/*  588:     */    }
/*  589:     */  }
/*  590:     */  
/*  593:     */  public static void storeBytes(byte[] array, PrintStream stream)
/*  594:     */  {
/*  595: 595 */    storeBytes(array, 0, array.length, stream);
/*  596:     */  }
/*  597:     */  
/*  602:     */  public static void storeBytes(byte[] array, int offset, int length, File file)
/*  603:     */    throws IOException
/*  604:     */  {
/*  605: 605 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  606: 606 */    storeBytes(array, offset, length, stream);
/*  607: 607 */    stream.close();
/*  608:     */  }
/*  609:     */  
/*  614:     */  public static void storeBytes(byte[] array, int offset, int length, CharSequence filename)
/*  615:     */    throws IOException
/*  616:     */  {
/*  617: 617 */    storeBytes(array, offset, length, new File(filename.toString()));
/*  618:     */  }
/*  619:     */  
/*  622:     */  public static void storeBytes(byte[] array, File file)
/*  623:     */    throws IOException
/*  624:     */  {
/*  625: 625 */    storeBytes(array, 0, array.length, file);
/*  626:     */  }
/*  627:     */  
/*  630:     */  public static void storeBytes(byte[] array, CharSequence filename)
/*  631:     */    throws IOException
/*  632:     */  {
/*  633: 633 */    storeBytes(array, 0, array.length, filename);
/*  634:     */  }
/*  635:     */  
/*  639:     */  public static void storeBytes(ByteIterator i, PrintStream stream)
/*  640:     */  {
/*  641: 641 */    while (i.hasNext()) { stream.println(i.nextByte());
/*  642:     */    }
/*  643:     */  }
/*  644:     */  
/*  646:     */  public static void storeBytes(ByteIterator i, File file)
/*  647:     */    throws IOException
/*  648:     */  {
/*  649: 649 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  650: 650 */    storeBytes(i, stream);
/*  651: 651 */    stream.close();
/*  652:     */  }
/*  653:     */  
/*  656:     */  public static void storeBytes(ByteIterator i, CharSequence filename)
/*  657:     */    throws IOException
/*  658:     */  {
/*  659: 659 */    storeBytes(i, new File(filename.toString()));
/*  660:     */  }
/*  661:     */  
/*  667:     */  public static long loadBytes(BufferedReader reader, byte[][] array, long offset, long length)
/*  668:     */    throws IOException
/*  669:     */  {
/*  670: 670 */    ByteBigArrays.ensureOffsetLength(array, offset, length);
/*  671: 671 */    long c = 0L;
/*  672:     */    try
/*  673:     */    {
/*  674: 674 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  675: 675 */        byte[] t = array[i];
/*  676: 676 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  677: 677 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/*  678: 678 */          if ((s = reader.readLine()) != null) t[d] = Byte.parseByte(s); else
/*  679: 679 */            return c;
/*  680: 680 */          c += 1L;
/*  681:     */        }
/*  682:     */      }
/*  683:     */    }
/*  684:     */    catch (EOFException itsOk) {}
/*  685: 685 */    return c;
/*  686:     */  }
/*  687:     */  
/*  691:     */  public static long loadBytes(BufferedReader reader, byte[][] array)
/*  692:     */    throws IOException
/*  693:     */  {
/*  694: 694 */    return loadBytes(reader, array, 0L, ByteBigArrays.length(array));
/*  695:     */  }
/*  696:     */  
/*  702:     */  public static long loadBytes(File file, byte[][] array, long offset, long length)
/*  703:     */    throws IOException
/*  704:     */  {
/*  705: 705 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/*  706: 706 */    long result = loadBytes(reader, array, offset, length);
/*  707: 707 */    reader.close();
/*  708: 708 */    return result;
/*  709:     */  }
/*  710:     */  
/*  716:     */  public static long loadBytes(CharSequence filename, byte[][] array, long offset, long length)
/*  717:     */    throws IOException
/*  718:     */  {
/*  719: 719 */    return loadBytes(new File(filename.toString()), array, offset, length);
/*  720:     */  }
/*  721:     */  
/*  725:     */  public static long loadBytes(File file, byte[][] array)
/*  726:     */    throws IOException
/*  727:     */  {
/*  728: 728 */    return loadBytes(file, array, 0L, ByteBigArrays.length(array));
/*  729:     */  }
/*  730:     */  
/*  734:     */  public static long loadBytes(CharSequence filename, byte[][] array)
/*  735:     */    throws IOException
/*  736:     */  {
/*  737: 737 */    return loadBytes(filename, array, 0L, ByteBigArrays.length(array));
/*  738:     */  }
/*  739:     */  
/*  745:     */  public static void storeBytes(byte[][] array, long offset, long length, PrintStream stream)
/*  746:     */  {
/*  747: 747 */    ByteBigArrays.ensureOffsetLength(array, offset, length);
/*  748: 748 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/*  749: 749 */      byte[] t = array[i];
/*  750: 750 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/*  751: 751 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/*  752:     */      }
/*  753:     */    }
/*  754:     */  }
/*  755:     */  
/*  758:     */  public static void storeBytes(byte[][] array, PrintStream stream)
/*  759:     */  {
/*  760: 760 */    storeBytes(array, 0L, ByteBigArrays.length(array), stream);
/*  761:     */  }
/*  762:     */  
/*  767:     */  public static void storeBytes(byte[][] array, long offset, long length, File file)
/*  768:     */    throws IOException
/*  769:     */  {
/*  770: 770 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  771: 771 */    storeBytes(array, offset, length, stream);
/*  772: 772 */    stream.close();
/*  773:     */  }
/*  774:     */  
/*  779:     */  public static void storeBytes(byte[][] array, long offset, long length, CharSequence filename)
/*  780:     */    throws IOException
/*  781:     */  {
/*  782: 782 */    storeBytes(array, offset, length, new File(filename.toString()));
/*  783:     */  }
/*  784:     */  
/*  787:     */  public static void storeBytes(byte[][] array, File file)
/*  788:     */    throws IOException
/*  789:     */  {
/*  790: 790 */    storeBytes(array, 0L, ByteBigArrays.length(array), file);
/*  791:     */  }
/*  792:     */  
/*  795:     */  public static void storeBytes(byte[][] array, CharSequence filename)
/*  796:     */    throws IOException
/*  797:     */  {
/*  798: 798 */    storeBytes(array, 0L, ByteBigArrays.length(array), filename);
/*  799:     */  }
/*  800:     */  
/*  801:     */  private static final class ByteReaderWrapper extends AbstractByteIterator {
/*  802:     */    private final BufferedReader reader;
/*  803: 803 */    private boolean toAdvance = true;
/*  804:     */    private String s;
/*  805:     */    private byte next;
/*  806:     */    
/*  807: 807 */    public ByteReaderWrapper(BufferedReader reader) { this.reader = reader; }
/*  808:     */    
/*  809:     */    public boolean hasNext() {
/*  810: 810 */      if (!this.toAdvance) return this.s != null;
/*  811: 811 */      this.toAdvance = false;
/*  812:     */      try {
/*  813: 813 */        this.s = this.reader.readLine();
/*  814:     */      }
/*  815:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/*  816: 816 */        throw new RuntimeException(rethrow); }
/*  817: 817 */      if (this.s == null) return false;
/*  818: 818 */      this.next = Byte.parseByte(this.s);
/*  819: 819 */      return true;
/*  820:     */    }
/*  821:     */    
/*  822: 822 */    public byte nextByte() { if (!hasNext()) throw new NoSuchElementException();
/*  823: 823 */      this.toAdvance = true;
/*  824: 824 */      return this.next;
/*  825:     */    }
/*  826:     */  }
/*  827:     */  
/*  830:     */  public static ByteIterator asByteIterator(BufferedReader reader)
/*  831:     */  {
/*  832: 832 */    return new ByteReaderWrapper(reader);
/*  833:     */  }
/*  834:     */  
/*  836:     */  public static ByteIterator asByteIterator(File file)
/*  837:     */    throws IOException
/*  838:     */  {
/*  839: 839 */    return new ByteReaderWrapper(new BufferedReader(new FileReader(file)));
/*  840:     */  }
/*  841:     */  
/*  843:     */  public static ByteIterator asByteIterator(CharSequence filename)
/*  844:     */    throws IOException
/*  845:     */  {
/*  846: 846 */    return asByteIterator(new File(filename.toString()));
/*  847:     */  }
/*  848:     */  
/*  891:     */  public static int loadShorts(BufferedReader reader, short[] array, int offset, int length)
/*  892:     */    throws IOException
/*  893:     */  {
/*  894: 894 */    ShortArrays.ensureOffsetLength(array, offset, length);
/*  895: 895 */    int i = 0;
/*  896:     */    try
/*  897:     */    {
/*  898: 898 */      for (i = 0; i < length; i++) { String s;
/*  899: 899 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Short.parseShort(s);
/*  900:     */      }
/*  901:     */    }
/*  902:     */    catch (EOFException itsOk) {}
/*  903: 903 */    return i;
/*  904:     */  }
/*  905:     */  
/*  909:     */  public static int loadShorts(BufferedReader reader, short[] array)
/*  910:     */    throws IOException
/*  911:     */  {
/*  912: 912 */    return loadShorts(reader, array, 0, array.length);
/*  913:     */  }
/*  914:     */  
/*  920:     */  public static int loadShorts(File file, short[] array, int offset, int length)
/*  921:     */    throws IOException
/*  922:     */  {
/*  923: 923 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/*  924: 924 */    int result = loadShorts(reader, array, offset, length);
/*  925: 925 */    reader.close();
/*  926: 926 */    return result;
/*  927:     */  }
/*  928:     */  
/*  934:     */  public static int loadShorts(CharSequence filename, short[] array, int offset, int length)
/*  935:     */    throws IOException
/*  936:     */  {
/*  937: 937 */    return loadShorts(new File(filename.toString()), array, offset, length);
/*  938:     */  }
/*  939:     */  
/*  943:     */  public static int loadShorts(File file, short[] array)
/*  944:     */    throws IOException
/*  945:     */  {
/*  946: 946 */    return loadShorts(file, array, 0, array.length);
/*  947:     */  }
/*  948:     */  
/*  952:     */  public static int loadShorts(CharSequence filename, short[] array)
/*  953:     */    throws IOException
/*  954:     */  {
/*  955: 955 */    return loadShorts(filename, array, 0, array.length);
/*  956:     */  }
/*  957:     */  
/*  963:     */  public static void storeShorts(short[] array, int offset, int length, PrintStream stream)
/*  964:     */  {
/*  965: 965 */    ShortArrays.ensureOffsetLength(array, offset, length);
/*  966: 966 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/*  967:     */    }
/*  968:     */  }
/*  969:     */  
/*  972:     */  public static void storeShorts(short[] array, PrintStream stream)
/*  973:     */  {
/*  974: 974 */    storeShorts(array, 0, array.length, stream);
/*  975:     */  }
/*  976:     */  
/*  981:     */  public static void storeShorts(short[] array, int offset, int length, File file)
/*  982:     */    throws IOException
/*  983:     */  {
/*  984: 984 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/*  985: 985 */    storeShorts(array, offset, length, stream);
/*  986: 986 */    stream.close();
/*  987:     */  }
/*  988:     */  
/*  993:     */  public static void storeShorts(short[] array, int offset, int length, CharSequence filename)
/*  994:     */    throws IOException
/*  995:     */  {
/*  996: 996 */    storeShorts(array, offset, length, new File(filename.toString()));
/*  997:     */  }
/*  998:     */  
/* 1001:     */  public static void storeShorts(short[] array, File file)
/* 1002:     */    throws IOException
/* 1003:     */  {
/* 1004:1004 */    storeShorts(array, 0, array.length, file);
/* 1005:     */  }
/* 1006:     */  
/* 1009:     */  public static void storeShorts(short[] array, CharSequence filename)
/* 1010:     */    throws IOException
/* 1011:     */  {
/* 1012:1012 */    storeShorts(array, 0, array.length, filename);
/* 1013:     */  }
/* 1014:     */  
/* 1018:     */  public static void storeShorts(ShortIterator i, PrintStream stream)
/* 1019:     */  {
/* 1020:1020 */    while (i.hasNext()) { stream.println(i.nextShort());
/* 1021:     */    }
/* 1022:     */  }
/* 1023:     */  
/* 1025:     */  public static void storeShorts(ShortIterator i, File file)
/* 1026:     */    throws IOException
/* 1027:     */  {
/* 1028:1028 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1029:1029 */    storeShorts(i, stream);
/* 1030:1030 */    stream.close();
/* 1031:     */  }
/* 1032:     */  
/* 1035:     */  public static void storeShorts(ShortIterator i, CharSequence filename)
/* 1036:     */    throws IOException
/* 1037:     */  {
/* 1038:1038 */    storeShorts(i, new File(filename.toString()));
/* 1039:     */  }
/* 1040:     */  
/* 1046:     */  public static long loadShorts(BufferedReader reader, short[][] array, long offset, long length)
/* 1047:     */    throws IOException
/* 1048:     */  {
/* 1049:1049 */    ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1050:1050 */    long c = 0L;
/* 1051:     */    try
/* 1052:     */    {
/* 1053:1053 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1054:1054 */        short[] t = array[i];
/* 1055:1055 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1056:1056 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/* 1057:1057 */          if ((s = reader.readLine()) != null) t[d] = Short.parseShort(s); else
/* 1058:1058 */            return c;
/* 1059:1059 */          c += 1L;
/* 1060:     */        }
/* 1061:     */      }
/* 1062:     */    }
/* 1063:     */    catch (EOFException itsOk) {}
/* 1064:1064 */    return c;
/* 1065:     */  }
/* 1066:     */  
/* 1070:     */  public static long loadShorts(BufferedReader reader, short[][] array)
/* 1071:     */    throws IOException
/* 1072:     */  {
/* 1073:1073 */    return loadShorts(reader, array, 0L, ShortBigArrays.length(array));
/* 1074:     */  }
/* 1075:     */  
/* 1081:     */  public static long loadShorts(File file, short[][] array, long offset, long length)
/* 1082:     */    throws IOException
/* 1083:     */  {
/* 1084:1084 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1085:1085 */    long result = loadShorts(reader, array, offset, length);
/* 1086:1086 */    reader.close();
/* 1087:1087 */    return result;
/* 1088:     */  }
/* 1089:     */  
/* 1095:     */  public static long loadShorts(CharSequence filename, short[][] array, long offset, long length)
/* 1096:     */    throws IOException
/* 1097:     */  {
/* 1098:1098 */    return loadShorts(new File(filename.toString()), array, offset, length);
/* 1099:     */  }
/* 1100:     */  
/* 1104:     */  public static long loadShorts(File file, short[][] array)
/* 1105:     */    throws IOException
/* 1106:     */  {
/* 1107:1107 */    return loadShorts(file, array, 0L, ShortBigArrays.length(array));
/* 1108:     */  }
/* 1109:     */  
/* 1113:     */  public static long loadShorts(CharSequence filename, short[][] array)
/* 1114:     */    throws IOException
/* 1115:     */  {
/* 1116:1116 */    return loadShorts(filename, array, 0L, ShortBigArrays.length(array));
/* 1117:     */  }
/* 1118:     */  
/* 1124:     */  public static void storeShorts(short[][] array, long offset, long length, PrintStream stream)
/* 1125:     */  {
/* 1126:1126 */    ShortBigArrays.ensureOffsetLength(array, offset, length);
/* 1127:1127 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1128:1128 */      short[] t = array[i];
/* 1129:1129 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1130:1130 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/* 1131:     */      }
/* 1132:     */    }
/* 1133:     */  }
/* 1134:     */  
/* 1137:     */  public static void storeShorts(short[][] array, PrintStream stream)
/* 1138:     */  {
/* 1139:1139 */    storeShorts(array, 0L, ShortBigArrays.length(array), stream);
/* 1140:     */  }
/* 1141:     */  
/* 1146:     */  public static void storeShorts(short[][] array, long offset, long length, File file)
/* 1147:     */    throws IOException
/* 1148:     */  {
/* 1149:1149 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1150:1150 */    storeShorts(array, offset, length, stream);
/* 1151:1151 */    stream.close();
/* 1152:     */  }
/* 1153:     */  
/* 1158:     */  public static void storeShorts(short[][] array, long offset, long length, CharSequence filename)
/* 1159:     */    throws IOException
/* 1160:     */  {
/* 1161:1161 */    storeShorts(array, offset, length, new File(filename.toString()));
/* 1162:     */  }
/* 1163:     */  
/* 1166:     */  public static void storeShorts(short[][] array, File file)
/* 1167:     */    throws IOException
/* 1168:     */  {
/* 1169:1169 */    storeShorts(array, 0L, ShortBigArrays.length(array), file);
/* 1170:     */  }
/* 1171:     */  
/* 1174:     */  public static void storeShorts(short[][] array, CharSequence filename)
/* 1175:     */    throws IOException
/* 1176:     */  {
/* 1177:1177 */    storeShorts(array, 0L, ShortBigArrays.length(array), filename);
/* 1178:     */  }
/* 1179:     */  
/* 1180:     */  private static final class ShortReaderWrapper extends AbstractShortIterator {
/* 1181:     */    private final BufferedReader reader;
/* 1182:1182 */    private boolean toAdvance = true;
/* 1183:     */    private String s;
/* 1184:     */    private short next;
/* 1185:     */    
/* 1186:1186 */    public ShortReaderWrapper(BufferedReader reader) { this.reader = reader; }
/* 1187:     */    
/* 1188:     */    public boolean hasNext() {
/* 1189:1189 */      if (!this.toAdvance) return this.s != null;
/* 1190:1190 */      this.toAdvance = false;
/* 1191:     */      try {
/* 1192:1192 */        this.s = this.reader.readLine();
/* 1193:     */      }
/* 1194:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/* 1195:1195 */        throw new RuntimeException(rethrow); }
/* 1196:1196 */      if (this.s == null) return false;
/* 1197:1197 */      this.next = Short.parseShort(this.s);
/* 1198:1198 */      return true;
/* 1199:     */    }
/* 1200:     */    
/* 1201:1201 */    public short nextShort() { if (!hasNext()) throw new NoSuchElementException();
/* 1202:1202 */      this.toAdvance = true;
/* 1203:1203 */      return this.next;
/* 1204:     */    }
/* 1205:     */  }
/* 1206:     */  
/* 1209:     */  public static ShortIterator asShortIterator(BufferedReader reader)
/* 1210:     */  {
/* 1211:1211 */    return new ShortReaderWrapper(reader);
/* 1212:     */  }
/* 1213:     */  
/* 1215:     */  public static ShortIterator asShortIterator(File file)
/* 1216:     */    throws IOException
/* 1217:     */  {
/* 1218:1218 */    return new ShortReaderWrapper(new BufferedReader(new FileReader(file)));
/* 1219:     */  }
/* 1220:     */  
/* 1222:     */  public static ShortIterator asShortIterator(CharSequence filename)
/* 1223:     */    throws IOException
/* 1224:     */  {
/* 1225:1225 */    return asShortIterator(new File(filename.toString()));
/* 1226:     */  }
/* 1227:     */  
/* 1270:     */  public static int loadInts(BufferedReader reader, int[] array, int offset, int length)
/* 1271:     */    throws IOException
/* 1272:     */  {
/* 1273:1273 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 1274:1274 */    int i = 0;
/* 1275:     */    try
/* 1276:     */    {
/* 1277:1277 */      for (i = 0; i < length; i++) { String s;
/* 1278:1278 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Integer.parseInt(s);
/* 1279:     */      }
/* 1280:     */    }
/* 1281:     */    catch (EOFException itsOk) {}
/* 1282:1282 */    return i;
/* 1283:     */  }
/* 1284:     */  
/* 1288:     */  public static int loadInts(BufferedReader reader, int[] array)
/* 1289:     */    throws IOException
/* 1290:     */  {
/* 1291:1291 */    return loadInts(reader, array, 0, array.length);
/* 1292:     */  }
/* 1293:     */  
/* 1299:     */  public static int loadInts(File file, int[] array, int offset, int length)
/* 1300:     */    throws IOException
/* 1301:     */  {
/* 1302:1302 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1303:1303 */    int result = loadInts(reader, array, offset, length);
/* 1304:1304 */    reader.close();
/* 1305:1305 */    return result;
/* 1306:     */  }
/* 1307:     */  
/* 1313:     */  public static int loadInts(CharSequence filename, int[] array, int offset, int length)
/* 1314:     */    throws IOException
/* 1315:     */  {
/* 1316:1316 */    return loadInts(new File(filename.toString()), array, offset, length);
/* 1317:     */  }
/* 1318:     */  
/* 1322:     */  public static int loadInts(File file, int[] array)
/* 1323:     */    throws IOException
/* 1324:     */  {
/* 1325:1325 */    return loadInts(file, array, 0, array.length);
/* 1326:     */  }
/* 1327:     */  
/* 1331:     */  public static int loadInts(CharSequence filename, int[] array)
/* 1332:     */    throws IOException
/* 1333:     */  {
/* 1334:1334 */    return loadInts(filename, array, 0, array.length);
/* 1335:     */  }
/* 1336:     */  
/* 1342:     */  public static void storeInts(int[] array, int offset, int length, PrintStream stream)
/* 1343:     */  {
/* 1344:1344 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 1345:1345 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/* 1346:     */    }
/* 1347:     */  }
/* 1348:     */  
/* 1351:     */  public static void storeInts(int[] array, PrintStream stream)
/* 1352:     */  {
/* 1353:1353 */    storeInts(array, 0, array.length, stream);
/* 1354:     */  }
/* 1355:     */  
/* 1360:     */  public static void storeInts(int[] array, int offset, int length, File file)
/* 1361:     */    throws IOException
/* 1362:     */  {
/* 1363:1363 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1364:1364 */    storeInts(array, offset, length, stream);
/* 1365:1365 */    stream.close();
/* 1366:     */  }
/* 1367:     */  
/* 1372:     */  public static void storeInts(int[] array, int offset, int length, CharSequence filename)
/* 1373:     */    throws IOException
/* 1374:     */  {
/* 1375:1375 */    storeInts(array, offset, length, new File(filename.toString()));
/* 1376:     */  }
/* 1377:     */  
/* 1380:     */  public static void storeInts(int[] array, File file)
/* 1381:     */    throws IOException
/* 1382:     */  {
/* 1383:1383 */    storeInts(array, 0, array.length, file);
/* 1384:     */  }
/* 1385:     */  
/* 1388:     */  public static void storeInts(int[] array, CharSequence filename)
/* 1389:     */    throws IOException
/* 1390:     */  {
/* 1391:1391 */    storeInts(array, 0, array.length, filename);
/* 1392:     */  }
/* 1393:     */  
/* 1397:     */  public static void storeInts(IntIterator i, PrintStream stream)
/* 1398:     */  {
/* 1399:1399 */    while (i.hasNext()) { stream.println(i.nextInt());
/* 1400:     */    }
/* 1401:     */  }
/* 1402:     */  
/* 1404:     */  public static void storeInts(IntIterator i, File file)
/* 1405:     */    throws IOException
/* 1406:     */  {
/* 1407:1407 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1408:1408 */    storeInts(i, stream);
/* 1409:1409 */    stream.close();
/* 1410:     */  }
/* 1411:     */  
/* 1414:     */  public static void storeInts(IntIterator i, CharSequence filename)
/* 1415:     */    throws IOException
/* 1416:     */  {
/* 1417:1417 */    storeInts(i, new File(filename.toString()));
/* 1418:     */  }
/* 1419:     */  
/* 1425:     */  public static long loadInts(BufferedReader reader, int[][] array, long offset, long length)
/* 1426:     */    throws IOException
/* 1427:     */  {
/* 1428:1428 */    IntBigArrays.ensureOffsetLength(array, offset, length);
/* 1429:1429 */    long c = 0L;
/* 1430:     */    try
/* 1431:     */    {
/* 1432:1432 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1433:1433 */        int[] t = array[i];
/* 1434:1434 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1435:1435 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/* 1436:1436 */          if ((s = reader.readLine()) != null) t[d] = Integer.parseInt(s); else
/* 1437:1437 */            return c;
/* 1438:1438 */          c += 1L;
/* 1439:     */        }
/* 1440:     */      }
/* 1441:     */    }
/* 1442:     */    catch (EOFException itsOk) {}
/* 1443:1443 */    return c;
/* 1444:     */  }
/* 1445:     */  
/* 1449:     */  public static long loadInts(BufferedReader reader, int[][] array)
/* 1450:     */    throws IOException
/* 1451:     */  {
/* 1452:1452 */    return loadInts(reader, array, 0L, IntBigArrays.length(array));
/* 1453:     */  }
/* 1454:     */  
/* 1460:     */  public static long loadInts(File file, int[][] array, long offset, long length)
/* 1461:     */    throws IOException
/* 1462:     */  {
/* 1463:1463 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1464:1464 */    long result = loadInts(reader, array, offset, length);
/* 1465:1465 */    reader.close();
/* 1466:1466 */    return result;
/* 1467:     */  }
/* 1468:     */  
/* 1474:     */  public static long loadInts(CharSequence filename, int[][] array, long offset, long length)
/* 1475:     */    throws IOException
/* 1476:     */  {
/* 1477:1477 */    return loadInts(new File(filename.toString()), array, offset, length);
/* 1478:     */  }
/* 1479:     */  
/* 1483:     */  public static long loadInts(File file, int[][] array)
/* 1484:     */    throws IOException
/* 1485:     */  {
/* 1486:1486 */    return loadInts(file, array, 0L, IntBigArrays.length(array));
/* 1487:     */  }
/* 1488:     */  
/* 1492:     */  public static long loadInts(CharSequence filename, int[][] array)
/* 1493:     */    throws IOException
/* 1494:     */  {
/* 1495:1495 */    return loadInts(filename, array, 0L, IntBigArrays.length(array));
/* 1496:     */  }
/* 1497:     */  
/* 1503:     */  public static void storeInts(int[][] array, long offset, long length, PrintStream stream)
/* 1504:     */  {
/* 1505:1505 */    IntBigArrays.ensureOffsetLength(array, offset, length);
/* 1506:1506 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1507:1507 */      int[] t = array[i];
/* 1508:1508 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1509:1509 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/* 1510:     */      }
/* 1511:     */    }
/* 1512:     */  }
/* 1513:     */  
/* 1516:     */  public static void storeInts(int[][] array, PrintStream stream)
/* 1517:     */  {
/* 1518:1518 */    storeInts(array, 0L, IntBigArrays.length(array), stream);
/* 1519:     */  }
/* 1520:     */  
/* 1525:     */  public static void storeInts(int[][] array, long offset, long length, File file)
/* 1526:     */    throws IOException
/* 1527:     */  {
/* 1528:1528 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1529:1529 */    storeInts(array, offset, length, stream);
/* 1530:1530 */    stream.close();
/* 1531:     */  }
/* 1532:     */  
/* 1537:     */  public static void storeInts(int[][] array, long offset, long length, CharSequence filename)
/* 1538:     */    throws IOException
/* 1539:     */  {
/* 1540:1540 */    storeInts(array, offset, length, new File(filename.toString()));
/* 1541:     */  }
/* 1542:     */  
/* 1545:     */  public static void storeInts(int[][] array, File file)
/* 1546:     */    throws IOException
/* 1547:     */  {
/* 1548:1548 */    storeInts(array, 0L, IntBigArrays.length(array), file);
/* 1549:     */  }
/* 1550:     */  
/* 1553:     */  public static void storeInts(int[][] array, CharSequence filename)
/* 1554:     */    throws IOException
/* 1555:     */  {
/* 1556:1556 */    storeInts(array, 0L, IntBigArrays.length(array), filename);
/* 1557:     */  }
/* 1558:     */  
/* 1559:     */  private static final class IntReaderWrapper extends AbstractIntIterator {
/* 1560:     */    private final BufferedReader reader;
/* 1561:1561 */    private boolean toAdvance = true;
/* 1562:     */    private String s;
/* 1563:     */    private int next;
/* 1564:     */    
/* 1565:1565 */    public IntReaderWrapper(BufferedReader reader) { this.reader = reader; }
/* 1566:     */    
/* 1567:     */    public boolean hasNext() {
/* 1568:1568 */      if (!this.toAdvance) return this.s != null;
/* 1569:1569 */      this.toAdvance = false;
/* 1570:     */      try {
/* 1571:1571 */        this.s = this.reader.readLine();
/* 1572:     */      }
/* 1573:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/* 1574:1574 */        throw new RuntimeException(rethrow); }
/* 1575:1575 */      if (this.s == null) return false;
/* 1576:1576 */      this.next = Integer.parseInt(this.s);
/* 1577:1577 */      return true;
/* 1578:     */    }
/* 1579:     */    
/* 1580:1580 */    public int nextInt() { if (!hasNext()) throw new NoSuchElementException();
/* 1581:1581 */      this.toAdvance = true;
/* 1582:1582 */      return this.next;
/* 1583:     */    }
/* 1584:     */  }
/* 1585:     */  
/* 1588:     */  public static IntIterator asIntIterator(BufferedReader reader)
/* 1589:     */  {
/* 1590:1590 */    return new IntReaderWrapper(reader);
/* 1591:     */  }
/* 1592:     */  
/* 1594:     */  public static IntIterator asIntIterator(File file)
/* 1595:     */    throws IOException
/* 1596:     */  {
/* 1597:1597 */    return new IntReaderWrapper(new BufferedReader(new FileReader(file)));
/* 1598:     */  }
/* 1599:     */  
/* 1601:     */  public static IntIterator asIntIterator(CharSequence filename)
/* 1602:     */    throws IOException
/* 1603:     */  {
/* 1604:1604 */    return asIntIterator(new File(filename.toString()));
/* 1605:     */  }
/* 1606:     */  
/* 1649:     */  public static int loadLongs(BufferedReader reader, long[] array, int offset, int length)
/* 1650:     */    throws IOException
/* 1651:     */  {
/* 1652:1652 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 1653:1653 */    int i = 0;
/* 1654:     */    try
/* 1655:     */    {
/* 1656:1656 */      for (i = 0; i < length; i++) { String s;
/* 1657:1657 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Long.parseLong(s);
/* 1658:     */      }
/* 1659:     */    }
/* 1660:     */    catch (EOFException itsOk) {}
/* 1661:1661 */    return i;
/* 1662:     */  }
/* 1663:     */  
/* 1667:     */  public static int loadLongs(BufferedReader reader, long[] array)
/* 1668:     */    throws IOException
/* 1669:     */  {
/* 1670:1670 */    return loadLongs(reader, array, 0, array.length);
/* 1671:     */  }
/* 1672:     */  
/* 1678:     */  public static int loadLongs(File file, long[] array, int offset, int length)
/* 1679:     */    throws IOException
/* 1680:     */  {
/* 1681:1681 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1682:1682 */    int result = loadLongs(reader, array, offset, length);
/* 1683:1683 */    reader.close();
/* 1684:1684 */    return result;
/* 1685:     */  }
/* 1686:     */  
/* 1692:     */  public static int loadLongs(CharSequence filename, long[] array, int offset, int length)
/* 1693:     */    throws IOException
/* 1694:     */  {
/* 1695:1695 */    return loadLongs(new File(filename.toString()), array, offset, length);
/* 1696:     */  }
/* 1697:     */  
/* 1701:     */  public static int loadLongs(File file, long[] array)
/* 1702:     */    throws IOException
/* 1703:     */  {
/* 1704:1704 */    return loadLongs(file, array, 0, array.length);
/* 1705:     */  }
/* 1706:     */  
/* 1710:     */  public static int loadLongs(CharSequence filename, long[] array)
/* 1711:     */    throws IOException
/* 1712:     */  {
/* 1713:1713 */    return loadLongs(filename, array, 0, array.length);
/* 1714:     */  }
/* 1715:     */  
/* 1721:     */  public static void storeLongs(long[] array, int offset, int length, PrintStream stream)
/* 1722:     */  {
/* 1723:1723 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 1724:1724 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/* 1725:     */    }
/* 1726:     */  }
/* 1727:     */  
/* 1730:     */  public static void storeLongs(long[] array, PrintStream stream)
/* 1731:     */  {
/* 1732:1732 */    storeLongs(array, 0, array.length, stream);
/* 1733:     */  }
/* 1734:     */  
/* 1739:     */  public static void storeLongs(long[] array, int offset, int length, File file)
/* 1740:     */    throws IOException
/* 1741:     */  {
/* 1742:1742 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1743:1743 */    storeLongs(array, offset, length, stream);
/* 1744:1744 */    stream.close();
/* 1745:     */  }
/* 1746:     */  
/* 1751:     */  public static void storeLongs(long[] array, int offset, int length, CharSequence filename)
/* 1752:     */    throws IOException
/* 1753:     */  {
/* 1754:1754 */    storeLongs(array, offset, length, new File(filename.toString()));
/* 1755:     */  }
/* 1756:     */  
/* 1759:     */  public static void storeLongs(long[] array, File file)
/* 1760:     */    throws IOException
/* 1761:     */  {
/* 1762:1762 */    storeLongs(array, 0, array.length, file);
/* 1763:     */  }
/* 1764:     */  
/* 1767:     */  public static void storeLongs(long[] array, CharSequence filename)
/* 1768:     */    throws IOException
/* 1769:     */  {
/* 1770:1770 */    storeLongs(array, 0, array.length, filename);
/* 1771:     */  }
/* 1772:     */  
/* 1776:     */  public static void storeLongs(LongIterator i, PrintStream stream)
/* 1777:     */  {
/* 1778:1778 */    while (i.hasNext()) { stream.println(i.nextLong());
/* 1779:     */    }
/* 1780:     */  }
/* 1781:     */  
/* 1783:     */  public static void storeLongs(LongIterator i, File file)
/* 1784:     */    throws IOException
/* 1785:     */  {
/* 1786:1786 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1787:1787 */    storeLongs(i, stream);
/* 1788:1788 */    stream.close();
/* 1789:     */  }
/* 1790:     */  
/* 1793:     */  public static void storeLongs(LongIterator i, CharSequence filename)
/* 1794:     */    throws IOException
/* 1795:     */  {
/* 1796:1796 */    storeLongs(i, new File(filename.toString()));
/* 1797:     */  }
/* 1798:     */  
/* 1804:     */  public static long loadLongs(BufferedReader reader, long[][] array, long offset, long length)
/* 1805:     */    throws IOException
/* 1806:     */  {
/* 1807:1807 */    LongBigArrays.ensureOffsetLength(array, offset, length);
/* 1808:1808 */    long c = 0L;
/* 1809:     */    try
/* 1810:     */    {
/* 1811:1811 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1812:1812 */        long[] t = array[i];
/* 1813:1813 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1814:1814 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/* 1815:1815 */          if ((s = reader.readLine()) != null) t[d] = Long.parseLong(s); else
/* 1816:1816 */            return c;
/* 1817:1817 */          c += 1L;
/* 1818:     */        }
/* 1819:     */      }
/* 1820:     */    }
/* 1821:     */    catch (EOFException itsOk) {}
/* 1822:1822 */    return c;
/* 1823:     */  }
/* 1824:     */  
/* 1828:     */  public static long loadLongs(BufferedReader reader, long[][] array)
/* 1829:     */    throws IOException
/* 1830:     */  {
/* 1831:1831 */    return loadLongs(reader, array, 0L, LongBigArrays.length(array));
/* 1832:     */  }
/* 1833:     */  
/* 1839:     */  public static long loadLongs(File file, long[][] array, long offset, long length)
/* 1840:     */    throws IOException
/* 1841:     */  {
/* 1842:1842 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 1843:1843 */    long result = loadLongs(reader, array, offset, length);
/* 1844:1844 */    reader.close();
/* 1845:1845 */    return result;
/* 1846:     */  }
/* 1847:     */  
/* 1853:     */  public static long loadLongs(CharSequence filename, long[][] array, long offset, long length)
/* 1854:     */    throws IOException
/* 1855:     */  {
/* 1856:1856 */    return loadLongs(new File(filename.toString()), array, offset, length);
/* 1857:     */  }
/* 1858:     */  
/* 1862:     */  public static long loadLongs(File file, long[][] array)
/* 1863:     */    throws IOException
/* 1864:     */  {
/* 1865:1865 */    return loadLongs(file, array, 0L, LongBigArrays.length(array));
/* 1866:     */  }
/* 1867:     */  
/* 1871:     */  public static long loadLongs(CharSequence filename, long[][] array)
/* 1872:     */    throws IOException
/* 1873:     */  {
/* 1874:1874 */    return loadLongs(filename, array, 0L, LongBigArrays.length(array));
/* 1875:     */  }
/* 1876:     */  
/* 1882:     */  public static void storeLongs(long[][] array, long offset, long length, PrintStream stream)
/* 1883:     */  {
/* 1884:1884 */    LongBigArrays.ensureOffsetLength(array, offset, length);
/* 1885:1885 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 1886:1886 */      long[] t = array[i];
/* 1887:1887 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 1888:1888 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/* 1889:     */      }
/* 1890:     */    }
/* 1891:     */  }
/* 1892:     */  
/* 1895:     */  public static void storeLongs(long[][] array, PrintStream stream)
/* 1896:     */  {
/* 1897:1897 */    storeLongs(array, 0L, LongBigArrays.length(array), stream);
/* 1898:     */  }
/* 1899:     */  
/* 1904:     */  public static void storeLongs(long[][] array, long offset, long length, File file)
/* 1905:     */    throws IOException
/* 1906:     */  {
/* 1907:1907 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 1908:1908 */    storeLongs(array, offset, length, stream);
/* 1909:1909 */    stream.close();
/* 1910:     */  }
/* 1911:     */  
/* 1916:     */  public static void storeLongs(long[][] array, long offset, long length, CharSequence filename)
/* 1917:     */    throws IOException
/* 1918:     */  {
/* 1919:1919 */    storeLongs(array, offset, length, new File(filename.toString()));
/* 1920:     */  }
/* 1921:     */  
/* 1924:     */  public static void storeLongs(long[][] array, File file)
/* 1925:     */    throws IOException
/* 1926:     */  {
/* 1927:1927 */    storeLongs(array, 0L, LongBigArrays.length(array), file);
/* 1928:     */  }
/* 1929:     */  
/* 1932:     */  public static void storeLongs(long[][] array, CharSequence filename)
/* 1933:     */    throws IOException
/* 1934:     */  {
/* 1935:1935 */    storeLongs(array, 0L, LongBigArrays.length(array), filename);
/* 1936:     */  }
/* 1937:     */  
/* 1938:     */  private static final class LongReaderWrapper extends AbstractLongIterator {
/* 1939:     */    private final BufferedReader reader;
/* 1940:1940 */    private boolean toAdvance = true;
/* 1941:     */    private String s;
/* 1942:     */    private long next;
/* 1943:     */    
/* 1944:1944 */    public LongReaderWrapper(BufferedReader reader) { this.reader = reader; }
/* 1945:     */    
/* 1946:     */    public boolean hasNext() {
/* 1947:1947 */      if (!this.toAdvance) return this.s != null;
/* 1948:1948 */      this.toAdvance = false;
/* 1949:     */      try {
/* 1950:1950 */        this.s = this.reader.readLine();
/* 1951:     */      }
/* 1952:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/* 1953:1953 */        throw new RuntimeException(rethrow); }
/* 1954:1954 */      if (this.s == null) return false;
/* 1955:1955 */      this.next = Long.parseLong(this.s);
/* 1956:1956 */      return true;
/* 1957:     */    }
/* 1958:     */    
/* 1959:1959 */    public long nextLong() { if (!hasNext()) throw new NoSuchElementException();
/* 1960:1960 */      this.toAdvance = true;
/* 1961:1961 */      return this.next;
/* 1962:     */    }
/* 1963:     */  }
/* 1964:     */  
/* 1967:     */  public static LongIterator asLongIterator(BufferedReader reader)
/* 1968:     */  {
/* 1969:1969 */    return new LongReaderWrapper(reader);
/* 1970:     */  }
/* 1971:     */  
/* 1973:     */  public static LongIterator asLongIterator(File file)
/* 1974:     */    throws IOException
/* 1975:     */  {
/* 1976:1976 */    return new LongReaderWrapper(new BufferedReader(new FileReader(file)));
/* 1977:     */  }
/* 1978:     */  
/* 1980:     */  public static LongIterator asLongIterator(CharSequence filename)
/* 1981:     */    throws IOException
/* 1982:     */  {
/* 1983:1983 */    return asLongIterator(new File(filename.toString()));
/* 1984:     */  }
/* 1985:     */  
/* 2028:     */  public static int loadFloats(BufferedReader reader, float[] array, int offset, int length)
/* 2029:     */    throws IOException
/* 2030:     */  {
/* 2031:2031 */    FloatArrays.ensureOffsetLength(array, offset, length);
/* 2032:2032 */    int i = 0;
/* 2033:     */    try
/* 2034:     */    {
/* 2035:2035 */      for (i = 0; i < length; i++) { String s;
/* 2036:2036 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Float.parseFloat(s);
/* 2037:     */      }
/* 2038:     */    }
/* 2039:     */    catch (EOFException itsOk) {}
/* 2040:2040 */    return i;
/* 2041:     */  }
/* 2042:     */  
/* 2046:     */  public static int loadFloats(BufferedReader reader, float[] array)
/* 2047:     */    throws IOException
/* 2048:     */  {
/* 2049:2049 */    return loadFloats(reader, array, 0, array.length);
/* 2050:     */  }
/* 2051:     */  
/* 2057:     */  public static int loadFloats(File file, float[] array, int offset, int length)
/* 2058:     */    throws IOException
/* 2059:     */  {
/* 2060:2060 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2061:2061 */    int result = loadFloats(reader, array, offset, length);
/* 2062:2062 */    reader.close();
/* 2063:2063 */    return result;
/* 2064:     */  }
/* 2065:     */  
/* 2071:     */  public static int loadFloats(CharSequence filename, float[] array, int offset, int length)
/* 2072:     */    throws IOException
/* 2073:     */  {
/* 2074:2074 */    return loadFloats(new File(filename.toString()), array, offset, length);
/* 2075:     */  }
/* 2076:     */  
/* 2080:     */  public static int loadFloats(File file, float[] array)
/* 2081:     */    throws IOException
/* 2082:     */  {
/* 2083:2083 */    return loadFloats(file, array, 0, array.length);
/* 2084:     */  }
/* 2085:     */  
/* 2089:     */  public static int loadFloats(CharSequence filename, float[] array)
/* 2090:     */    throws IOException
/* 2091:     */  {
/* 2092:2092 */    return loadFloats(filename, array, 0, array.length);
/* 2093:     */  }
/* 2094:     */  
/* 2100:     */  public static void storeFloats(float[] array, int offset, int length, PrintStream stream)
/* 2101:     */  {
/* 2102:2102 */    FloatArrays.ensureOffsetLength(array, offset, length);
/* 2103:2103 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/* 2104:     */    }
/* 2105:     */  }
/* 2106:     */  
/* 2109:     */  public static void storeFloats(float[] array, PrintStream stream)
/* 2110:     */  {
/* 2111:2111 */    storeFloats(array, 0, array.length, stream);
/* 2112:     */  }
/* 2113:     */  
/* 2118:     */  public static void storeFloats(float[] array, int offset, int length, File file)
/* 2119:     */    throws IOException
/* 2120:     */  {
/* 2121:2121 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2122:2122 */    storeFloats(array, offset, length, stream);
/* 2123:2123 */    stream.close();
/* 2124:     */  }
/* 2125:     */  
/* 2130:     */  public static void storeFloats(float[] array, int offset, int length, CharSequence filename)
/* 2131:     */    throws IOException
/* 2132:     */  {
/* 2133:2133 */    storeFloats(array, offset, length, new File(filename.toString()));
/* 2134:     */  }
/* 2135:     */  
/* 2138:     */  public static void storeFloats(float[] array, File file)
/* 2139:     */    throws IOException
/* 2140:     */  {
/* 2141:2141 */    storeFloats(array, 0, array.length, file);
/* 2142:     */  }
/* 2143:     */  
/* 2146:     */  public static void storeFloats(float[] array, CharSequence filename)
/* 2147:     */    throws IOException
/* 2148:     */  {
/* 2149:2149 */    storeFloats(array, 0, array.length, filename);
/* 2150:     */  }
/* 2151:     */  
/* 2155:     */  public static void storeFloats(FloatIterator i, PrintStream stream)
/* 2156:     */  {
/* 2157:2157 */    while (i.hasNext()) { stream.println(i.nextFloat());
/* 2158:     */    }
/* 2159:     */  }
/* 2160:     */  
/* 2162:     */  public static void storeFloats(FloatIterator i, File file)
/* 2163:     */    throws IOException
/* 2164:     */  {
/* 2165:2165 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2166:2166 */    storeFloats(i, stream);
/* 2167:2167 */    stream.close();
/* 2168:     */  }
/* 2169:     */  
/* 2172:     */  public static void storeFloats(FloatIterator i, CharSequence filename)
/* 2173:     */    throws IOException
/* 2174:     */  {
/* 2175:2175 */    storeFloats(i, new File(filename.toString()));
/* 2176:     */  }
/* 2177:     */  
/* 2183:     */  public static long loadFloats(BufferedReader reader, float[][] array, long offset, long length)
/* 2184:     */    throws IOException
/* 2185:     */  {
/* 2186:2186 */    FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 2187:2187 */    long c = 0L;
/* 2188:     */    try
/* 2189:     */    {
/* 2190:2190 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2191:2191 */        float[] t = array[i];
/* 2192:2192 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2193:2193 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/* 2194:2194 */          if ((s = reader.readLine()) != null) t[d] = Float.parseFloat(s); else
/* 2195:2195 */            return c;
/* 2196:2196 */          c += 1L;
/* 2197:     */        }
/* 2198:     */      }
/* 2199:     */    }
/* 2200:     */    catch (EOFException itsOk) {}
/* 2201:2201 */    return c;
/* 2202:     */  }
/* 2203:     */  
/* 2207:     */  public static long loadFloats(BufferedReader reader, float[][] array)
/* 2208:     */    throws IOException
/* 2209:     */  {
/* 2210:2210 */    return loadFloats(reader, array, 0L, FloatBigArrays.length(array));
/* 2211:     */  }
/* 2212:     */  
/* 2218:     */  public static long loadFloats(File file, float[][] array, long offset, long length)
/* 2219:     */    throws IOException
/* 2220:     */  {
/* 2221:2221 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2222:2222 */    long result = loadFloats(reader, array, offset, length);
/* 2223:2223 */    reader.close();
/* 2224:2224 */    return result;
/* 2225:     */  }
/* 2226:     */  
/* 2232:     */  public static long loadFloats(CharSequence filename, float[][] array, long offset, long length)
/* 2233:     */    throws IOException
/* 2234:     */  {
/* 2235:2235 */    return loadFloats(new File(filename.toString()), array, offset, length);
/* 2236:     */  }
/* 2237:     */  
/* 2241:     */  public static long loadFloats(File file, float[][] array)
/* 2242:     */    throws IOException
/* 2243:     */  {
/* 2244:2244 */    return loadFloats(file, array, 0L, FloatBigArrays.length(array));
/* 2245:     */  }
/* 2246:     */  
/* 2250:     */  public static long loadFloats(CharSequence filename, float[][] array)
/* 2251:     */    throws IOException
/* 2252:     */  {
/* 2253:2253 */    return loadFloats(filename, array, 0L, FloatBigArrays.length(array));
/* 2254:     */  }
/* 2255:     */  
/* 2261:     */  public static void storeFloats(float[][] array, long offset, long length, PrintStream stream)
/* 2262:     */  {
/* 2263:2263 */    FloatBigArrays.ensureOffsetLength(array, offset, length);
/* 2264:2264 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2265:2265 */      float[] t = array[i];
/* 2266:2266 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2267:2267 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/* 2268:     */      }
/* 2269:     */    }
/* 2270:     */  }
/* 2271:     */  
/* 2274:     */  public static void storeFloats(float[][] array, PrintStream stream)
/* 2275:     */  {
/* 2276:2276 */    storeFloats(array, 0L, FloatBigArrays.length(array), stream);
/* 2277:     */  }
/* 2278:     */  
/* 2283:     */  public static void storeFloats(float[][] array, long offset, long length, File file)
/* 2284:     */    throws IOException
/* 2285:     */  {
/* 2286:2286 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2287:2287 */    storeFloats(array, offset, length, stream);
/* 2288:2288 */    stream.close();
/* 2289:     */  }
/* 2290:     */  
/* 2295:     */  public static void storeFloats(float[][] array, long offset, long length, CharSequence filename)
/* 2296:     */    throws IOException
/* 2297:     */  {
/* 2298:2298 */    storeFloats(array, offset, length, new File(filename.toString()));
/* 2299:     */  }
/* 2300:     */  
/* 2303:     */  public static void storeFloats(float[][] array, File file)
/* 2304:     */    throws IOException
/* 2305:     */  {
/* 2306:2306 */    storeFloats(array, 0L, FloatBigArrays.length(array), file);
/* 2307:     */  }
/* 2308:     */  
/* 2311:     */  public static void storeFloats(float[][] array, CharSequence filename)
/* 2312:     */    throws IOException
/* 2313:     */  {
/* 2314:2314 */    storeFloats(array, 0L, FloatBigArrays.length(array), filename);
/* 2315:     */  }
/* 2316:     */  
/* 2317:     */  private static final class FloatReaderWrapper extends AbstractFloatIterator {
/* 2318:     */    private final BufferedReader reader;
/* 2319:2319 */    private boolean toAdvance = true;
/* 2320:     */    private String s;
/* 2321:     */    private float next;
/* 2322:     */    
/* 2323:2323 */    public FloatReaderWrapper(BufferedReader reader) { this.reader = reader; }
/* 2324:     */    
/* 2325:     */    public boolean hasNext() {
/* 2326:2326 */      if (!this.toAdvance) return this.s != null;
/* 2327:2327 */      this.toAdvance = false;
/* 2328:     */      try {
/* 2329:2329 */        this.s = this.reader.readLine();
/* 2330:     */      }
/* 2331:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/* 2332:2332 */        throw new RuntimeException(rethrow); }
/* 2333:2333 */      if (this.s == null) return false;
/* 2334:2334 */      this.next = Float.parseFloat(this.s);
/* 2335:2335 */      return true;
/* 2336:     */    }
/* 2337:     */    
/* 2338:2338 */    public float nextFloat() { if (!hasNext()) throw new NoSuchElementException();
/* 2339:2339 */      this.toAdvance = true;
/* 2340:2340 */      return this.next;
/* 2341:     */    }
/* 2342:     */  }
/* 2343:     */  
/* 2346:     */  public static FloatIterator asFloatIterator(BufferedReader reader)
/* 2347:     */  {
/* 2348:2348 */    return new FloatReaderWrapper(reader);
/* 2349:     */  }
/* 2350:     */  
/* 2352:     */  public static FloatIterator asFloatIterator(File file)
/* 2353:     */    throws IOException
/* 2354:     */  {
/* 2355:2355 */    return new FloatReaderWrapper(new BufferedReader(new FileReader(file)));
/* 2356:     */  }
/* 2357:     */  
/* 2359:     */  public static FloatIterator asFloatIterator(CharSequence filename)
/* 2360:     */    throws IOException
/* 2361:     */  {
/* 2362:2362 */    return asFloatIterator(new File(filename.toString()));
/* 2363:     */  }
/* 2364:     */  
/* 2407:     */  public static int loadDoubles(BufferedReader reader, double[] array, int offset, int length)
/* 2408:     */    throws IOException
/* 2409:     */  {
/* 2410:2410 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 2411:2411 */    int i = 0;
/* 2412:     */    try
/* 2413:     */    {
/* 2414:2414 */      for (i = 0; i < length; i++) { String s;
/* 2415:2415 */        if ((s = reader.readLine()) == null) break; array[(i + offset)] = Double.parseDouble(s);
/* 2416:     */      }
/* 2417:     */    }
/* 2418:     */    catch (EOFException itsOk) {}
/* 2419:2419 */    return i;
/* 2420:     */  }
/* 2421:     */  
/* 2425:     */  public static int loadDoubles(BufferedReader reader, double[] array)
/* 2426:     */    throws IOException
/* 2427:     */  {
/* 2428:2428 */    return loadDoubles(reader, array, 0, array.length);
/* 2429:     */  }
/* 2430:     */  
/* 2436:     */  public static int loadDoubles(File file, double[] array, int offset, int length)
/* 2437:     */    throws IOException
/* 2438:     */  {
/* 2439:2439 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2440:2440 */    int result = loadDoubles(reader, array, offset, length);
/* 2441:2441 */    reader.close();
/* 2442:2442 */    return result;
/* 2443:     */  }
/* 2444:     */  
/* 2450:     */  public static int loadDoubles(CharSequence filename, double[] array, int offset, int length)
/* 2451:     */    throws IOException
/* 2452:     */  {
/* 2453:2453 */    return loadDoubles(new File(filename.toString()), array, offset, length);
/* 2454:     */  }
/* 2455:     */  
/* 2459:     */  public static int loadDoubles(File file, double[] array)
/* 2460:     */    throws IOException
/* 2461:     */  {
/* 2462:2462 */    return loadDoubles(file, array, 0, array.length);
/* 2463:     */  }
/* 2464:     */  
/* 2468:     */  public static int loadDoubles(CharSequence filename, double[] array)
/* 2469:     */    throws IOException
/* 2470:     */  {
/* 2471:2471 */    return loadDoubles(filename, array, 0, array.length);
/* 2472:     */  }
/* 2473:     */  
/* 2479:     */  public static void storeDoubles(double[] array, int offset, int length, PrintStream stream)
/* 2480:     */  {
/* 2481:2481 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 2482:2482 */    for (int i = 0; i < length; i++) { stream.println(array[(offset + i)]);
/* 2483:     */    }
/* 2484:     */  }
/* 2485:     */  
/* 2488:     */  public static void storeDoubles(double[] array, PrintStream stream)
/* 2489:     */  {
/* 2490:2490 */    storeDoubles(array, 0, array.length, stream);
/* 2491:     */  }
/* 2492:     */  
/* 2497:     */  public static void storeDoubles(double[] array, int offset, int length, File file)
/* 2498:     */    throws IOException
/* 2499:     */  {
/* 2500:2500 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2501:2501 */    storeDoubles(array, offset, length, stream);
/* 2502:2502 */    stream.close();
/* 2503:     */  }
/* 2504:     */  
/* 2509:     */  public static void storeDoubles(double[] array, int offset, int length, CharSequence filename)
/* 2510:     */    throws IOException
/* 2511:     */  {
/* 2512:2512 */    storeDoubles(array, offset, length, new File(filename.toString()));
/* 2513:     */  }
/* 2514:     */  
/* 2517:     */  public static void storeDoubles(double[] array, File file)
/* 2518:     */    throws IOException
/* 2519:     */  {
/* 2520:2520 */    storeDoubles(array, 0, array.length, file);
/* 2521:     */  }
/* 2522:     */  
/* 2525:     */  public static void storeDoubles(double[] array, CharSequence filename)
/* 2526:     */    throws IOException
/* 2527:     */  {
/* 2528:2528 */    storeDoubles(array, 0, array.length, filename);
/* 2529:     */  }
/* 2530:     */  
/* 2534:     */  public static void storeDoubles(DoubleIterator i, PrintStream stream)
/* 2535:     */  {
/* 2536:2536 */    while (i.hasNext()) { stream.println(i.nextDouble());
/* 2537:     */    }
/* 2538:     */  }
/* 2539:     */  
/* 2541:     */  public static void storeDoubles(DoubleIterator i, File file)
/* 2542:     */    throws IOException
/* 2543:     */  {
/* 2544:2544 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2545:2545 */    storeDoubles(i, stream);
/* 2546:2546 */    stream.close();
/* 2547:     */  }
/* 2548:     */  
/* 2551:     */  public static void storeDoubles(DoubleIterator i, CharSequence filename)
/* 2552:     */    throws IOException
/* 2553:     */  {
/* 2554:2554 */    storeDoubles(i, new File(filename.toString()));
/* 2555:     */  }
/* 2556:     */  
/* 2562:     */  public static long loadDoubles(BufferedReader reader, double[][] array, long offset, long length)
/* 2563:     */    throws IOException
/* 2564:     */  {
/* 2565:2565 */    DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 2566:2566 */    long c = 0L;
/* 2567:     */    try
/* 2568:     */    {
/* 2569:2569 */      for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2570:2570 */        double[] t = array[i];
/* 2571:2571 */        int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2572:2572 */        for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { String s;
/* 2573:2573 */          if ((s = reader.readLine()) != null) t[d] = Double.parseDouble(s); else
/* 2574:2574 */            return c;
/* 2575:2575 */          c += 1L;
/* 2576:     */        }
/* 2577:     */      }
/* 2578:     */    }
/* 2579:     */    catch (EOFException itsOk) {}
/* 2580:2580 */    return c;
/* 2581:     */  }
/* 2582:     */  
/* 2586:     */  public static long loadDoubles(BufferedReader reader, double[][] array)
/* 2587:     */    throws IOException
/* 2588:     */  {
/* 2589:2589 */    return loadDoubles(reader, array, 0L, DoubleBigArrays.length(array));
/* 2590:     */  }
/* 2591:     */  
/* 2597:     */  public static long loadDoubles(File file, double[][] array, long offset, long length)
/* 2598:     */    throws IOException
/* 2599:     */  {
/* 2600:2600 */    BufferedReader reader = new BufferedReader(new FileReader(file));
/* 2601:2601 */    long result = loadDoubles(reader, array, offset, length);
/* 2602:2602 */    reader.close();
/* 2603:2603 */    return result;
/* 2604:     */  }
/* 2605:     */  
/* 2611:     */  public static long loadDoubles(CharSequence filename, double[][] array, long offset, long length)
/* 2612:     */    throws IOException
/* 2613:     */  {
/* 2614:2614 */    return loadDoubles(new File(filename.toString()), array, offset, length);
/* 2615:     */  }
/* 2616:     */  
/* 2620:     */  public static long loadDoubles(File file, double[][] array)
/* 2621:     */    throws IOException
/* 2622:     */  {
/* 2623:2623 */    return loadDoubles(file, array, 0L, DoubleBigArrays.length(array));
/* 2624:     */  }
/* 2625:     */  
/* 2629:     */  public static long loadDoubles(CharSequence filename, double[][] array)
/* 2630:     */    throws IOException
/* 2631:     */  {
/* 2632:2632 */    return loadDoubles(filename, array, 0L, DoubleBigArrays.length(array));
/* 2633:     */  }
/* 2634:     */  
/* 2640:     */  public static void storeDoubles(double[][] array, long offset, long length, PrintStream stream)
/* 2641:     */  {
/* 2642:2642 */    DoubleBigArrays.ensureOffsetLength(array, offset, length);
/* 2643:2643 */    for (int i = BigArrays.segment(offset); i < BigArrays.segment(offset + length + 134217727L); i++) {
/* 2644:2644 */      double[] t = array[i];
/* 2645:2645 */      int l = (int)Math.min(t.length, offset + length - BigArrays.start(i));
/* 2646:2646 */      for (int d = (int)Math.max(0L, offset - BigArrays.start(i)); d < l; d++) { stream.println(t[d]);
/* 2647:     */      }
/* 2648:     */    }
/* 2649:     */  }
/* 2650:     */  
/* 2653:     */  public static void storeDoubles(double[][] array, PrintStream stream)
/* 2654:     */  {
/* 2655:2655 */    storeDoubles(array, 0L, DoubleBigArrays.length(array), stream);
/* 2656:     */  }
/* 2657:     */  
/* 2662:     */  public static void storeDoubles(double[][] array, long offset, long length, File file)
/* 2663:     */    throws IOException
/* 2664:     */  {
/* 2665:2665 */    PrintStream stream = new PrintStream(new FastBufferedOutputStream(new FileOutputStream(file)));
/* 2666:2666 */    storeDoubles(array, offset, length, stream);
/* 2667:2667 */    stream.close();
/* 2668:     */  }
/* 2669:     */  
/* 2674:     */  public static void storeDoubles(double[][] array, long offset, long length, CharSequence filename)
/* 2675:     */    throws IOException
/* 2676:     */  {
/* 2677:2677 */    storeDoubles(array, offset, length, new File(filename.toString()));
/* 2678:     */  }
/* 2679:     */  
/* 2682:     */  public static void storeDoubles(double[][] array, File file)
/* 2683:     */    throws IOException
/* 2684:     */  {
/* 2685:2685 */    storeDoubles(array, 0L, DoubleBigArrays.length(array), file);
/* 2686:     */  }
/* 2687:     */  
/* 2690:     */  public static void storeDoubles(double[][] array, CharSequence filename)
/* 2691:     */    throws IOException
/* 2692:     */  {
/* 2693:2693 */    storeDoubles(array, 0L, DoubleBigArrays.length(array), filename);
/* 2694:     */  }
/* 2695:     */  
/* 2696:     */  private static final class DoubleReaderWrapper extends AbstractDoubleIterator {
/* 2697:     */    private final BufferedReader reader;
/* 2698:2698 */    private boolean toAdvance = true;
/* 2699:     */    private String s;
/* 2700:     */    private double next;
/* 2701:     */    
/* 2702:2702 */    public DoubleReaderWrapper(BufferedReader reader) { this.reader = reader; }
/* 2703:     */    
/* 2704:     */    public boolean hasNext() {
/* 2705:2705 */      if (!this.toAdvance) return this.s != null;
/* 2706:2706 */      this.toAdvance = false;
/* 2707:     */      try {
/* 2708:2708 */        this.s = this.reader.readLine();
/* 2709:     */      }
/* 2710:     */      catch (EOFException itsOk) {}catch (IOException rethrow) {
/* 2711:2711 */        throw new RuntimeException(rethrow); }
/* 2712:2712 */      if (this.s == null) return false;
/* 2713:2713 */      this.next = Double.parseDouble(this.s);
/* 2714:2714 */      return true;
/* 2715:     */    }
/* 2716:     */    
/* 2717:2717 */    public double nextDouble() { if (!hasNext()) throw new NoSuchElementException();
/* 2718:2718 */      this.toAdvance = true;
/* 2719:2719 */      return this.next;
/* 2720:     */    }
/* 2721:     */  }
/* 2722:     */  
/* 2725:     */  public static DoubleIterator asDoubleIterator(BufferedReader reader)
/* 2726:     */  {
/* 2727:2727 */    return new DoubleReaderWrapper(reader);
/* 2728:     */  }
/* 2729:     */  
/* 2731:     */  public static DoubleIterator asDoubleIterator(File file)
/* 2732:     */    throws IOException
/* 2733:     */  {
/* 2734:2734 */    return new DoubleReaderWrapper(new BufferedReader(new FileReader(file)));
/* 2735:     */  }
/* 2736:     */  
/* 2738:     */  public static DoubleIterator asDoubleIterator(CharSequence filename)
/* 2739:     */    throws IOException
/* 2740:     */  {
/* 2741:2741 */    return asDoubleIterator(new File(filename.toString()));
/* 2742:     */  }
/* 2743:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.TextIO
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */