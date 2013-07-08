/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.lang.reflect.Method;
/*   4:    */import java.nio.Buffer;
/*   5:    */import java.nio.BufferOverflowException;
/*   6:    */import java.nio.BufferUnderflowException;
/*   7:    */import java.nio.ByteBuffer;
/*   8:    */import java.nio.ByteOrder;
/*   9:    */import java.nio.IntBuffer;
/*  10:    */import java.nio.LongBuffer;
/*  11:    */import java.nio.ReadOnlyBufferException;
/*  12:    */
/*  38:    */public class PointerBuffer
/*  39:    */  implements Comparable
/*  40:    */{
/*  41:    */  private static final boolean is64Bit;
/*  42:    */  protected final ByteBuffer pointers;
/*  43:    */  protected final Buffer view;
/*  44:    */  protected final IntBuffer view32;
/*  45:    */  protected final LongBuffer view64;
/*  46:    */  
/*  47:    */  static
/*  48:    */  {
/*  49: 49 */    boolean is64 = false;
/*  50:    */    try {
/*  51: 51 */      Method m = Class.forName("org.lwjgl.Sys").getDeclaredMethod("is64Bit", (Class[])null);
/*  52: 52 */      is64 = ((Boolean)m.invoke(null, (Object[])null)).booleanValue();
/*  53:    */    }
/*  54:    */    catch (Throwable t) {}finally
/*  55:    */    {
/*  56: 56 */      is64Bit = is64;
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  70:    */  public PointerBuffer(int capacity)
/*  71:    */  {
/*  72: 72 */    this(BufferUtils.createByteBuffer(capacity * getPointerSize()));
/*  73:    */  }
/*  74:    */  
/*  81:    */  public PointerBuffer(ByteBuffer source)
/*  82:    */  {
/*  83: 83 */    if (LWJGLUtil.CHECKS) {
/*  84: 84 */      checkSource(source);
/*  85:    */    }
/*  86: 86 */    this.pointers = source.slice().order(source.order());
/*  87:    */    
/*  88: 88 */    if (is64Bit) {
/*  89: 89 */      this.view32 = null;
/*  90: 90 */      this.view = (this.view64 = this.pointers.asLongBuffer());
/*  91:    */    } else {
/*  92: 92 */      this.view = (this.view32 = this.pointers.asIntBuffer());
/*  93: 93 */      this.view64 = null;
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/*  97:    */  private static void checkSource(ByteBuffer source) {
/*  98: 98 */    if (!source.isDirect()) {
/*  99: 99 */      throw new IllegalArgumentException("The source buffer is not direct.");
/* 100:    */    }
/* 101:101 */    int alignment = is64Bit ? 8 : 4;
/* 102:102 */    if (((MemoryUtil.getAddress0(source) + source.position()) % alignment != 0L) || (source.remaining() % alignment != 0)) {
/* 103:103 */      throw new IllegalArgumentException("The source buffer is not aligned to " + alignment + " bytes.");
/* 104:    */    }
/* 105:    */  }
/* 106:    */  
/* 110:    */  public ByteBuffer getBuffer()
/* 111:    */  {
/* 112:112 */    return this.pointers;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public static boolean is64Bit()
/* 116:    */  {
/* 117:117 */    return is64Bit;
/* 118:    */  }
/* 119:    */  
/* 124:    */  public static int getPointerSize()
/* 125:    */  {
/* 126:126 */    return is64Bit ? 8 : 4;
/* 127:    */  }
/* 128:    */  
/* 133:    */  public final int capacity()
/* 134:    */  {
/* 135:135 */    return this.view.capacity();
/* 136:    */  }
/* 137:    */  
/* 142:    */  public final int position()
/* 143:    */  {
/* 144:144 */    return this.view.position();
/* 145:    */  }
/* 146:    */  
/* 151:    */  public final int positionByte()
/* 152:    */  {
/* 153:153 */    return position() * getPointerSize();
/* 154:    */  }
/* 155:    */  
/* 166:    */  public final PointerBuffer position(int newPosition)
/* 167:    */  {
/* 168:168 */    this.view.position(newPosition);
/* 169:169 */    return this;
/* 170:    */  }
/* 171:    */  
/* 176:    */  public final int limit()
/* 177:    */  {
/* 178:178 */    return this.view.limit();
/* 179:    */  }
/* 180:    */  
/* 192:    */  public final PointerBuffer limit(int newLimit)
/* 193:    */  {
/* 194:194 */    this.view.limit(newLimit);
/* 195:195 */    return this;
/* 196:    */  }
/* 197:    */  
/* 202:    */  public final PointerBuffer mark()
/* 203:    */  {
/* 204:204 */    this.view.mark();
/* 205:205 */    return this;
/* 206:    */  }
/* 207:    */  
/* 217:    */  public final PointerBuffer reset()
/* 218:    */  {
/* 219:219 */    this.view.reset();
/* 220:220 */    return this;
/* 221:    */  }
/* 222:    */  
/* 239:    */  public final PointerBuffer clear()
/* 240:    */  {
/* 241:241 */    this.view.clear();
/* 242:242 */    return this;
/* 243:    */  }
/* 244:    */  
/* 265:    */  public final PointerBuffer flip()
/* 266:    */  {
/* 267:267 */    this.view.flip();
/* 268:268 */    return this;
/* 269:    */  }
/* 270:    */  
/* 285:    */  public final PointerBuffer rewind()
/* 286:    */  {
/* 287:287 */    this.view.rewind();
/* 288:288 */    return this;
/* 289:    */  }
/* 290:    */  
/* 296:    */  public final int remaining()
/* 297:    */  {
/* 298:298 */    return this.view.remaining();
/* 299:    */  }
/* 300:    */  
/* 306:    */  public final int remainingByte()
/* 307:    */  {
/* 308:308 */    return remaining() * getPointerSize();
/* 309:    */  }
/* 310:    */  
/* 317:    */  public final boolean hasRemaining()
/* 318:    */  {
/* 319:319 */    return this.view.hasRemaining();
/* 320:    */  }
/* 321:    */  
/* 333:    */  public static PointerBuffer allocateDirect(int capacity)
/* 334:    */  {
/* 335:335 */    return new PointerBuffer(capacity);
/* 336:    */  }
/* 337:    */  
/* 345:    */  protected PointerBuffer newInstance(ByteBuffer source)
/* 346:    */  {
/* 347:347 */    return new PointerBuffer(source);
/* 348:    */  }
/* 349:    */  
/* 366:    */  public PointerBuffer slice()
/* 367:    */  {
/* 368:368 */    int pointerSize = getPointerSize();
/* 369:    */    
/* 370:370 */    this.pointers.position(this.view.position() * pointerSize);
/* 371:371 */    this.pointers.limit(this.view.limit() * pointerSize);
/* 372:    */    
/* 373:    */    try
/* 374:    */    {
/* 375:375 */      return newInstance(this.pointers);
/* 376:    */    } finally {
/* 377:377 */      this.pointers.clear();
/* 378:    */    }
/* 379:    */  }
/* 380:    */  
/* 395:    */  public PointerBuffer duplicate()
/* 396:    */  {
/* 397:397 */    PointerBuffer buffer = newInstance(this.pointers);
/* 398:    */    
/* 399:399 */    buffer.position(this.view.position());
/* 400:400 */    buffer.limit(this.view.limit());
/* 401:    */    
/* 402:402 */    return buffer;
/* 403:    */  }
/* 404:    */  
/* 422:    */  public PointerBuffer asReadOnlyBuffer()
/* 423:    */  {
/* 424:424 */    PointerBuffer buffer = new PointerBufferR(this.pointers);
/* 425:    */    
/* 426:426 */    buffer.position(this.view.position());
/* 427:427 */    buffer.limit(this.view.limit());
/* 428:    */    
/* 429:429 */    return buffer;
/* 430:    */  }
/* 431:    */  
/* 432:    */  public boolean isReadOnly() {
/* 433:433 */    return false;
/* 434:    */  }
/* 435:    */  
/* 443:    */  public long get()
/* 444:    */  {
/* 445:445 */    if (is64Bit) {
/* 446:446 */      return this.view64.get();
/* 447:    */    }
/* 448:448 */    return this.view32.get() & 0xFFFFFFFF;
/* 449:    */  }
/* 450:    */  
/* 463:    */  public PointerBuffer put(long l)
/* 464:    */  {
/* 465:465 */    if (is64Bit) {
/* 466:466 */      this.view64.put(l);
/* 467:    */    } else
/* 468:468 */      this.view32.put((int)l);
/* 469:469 */    return this;
/* 470:    */  }
/* 471:    */  
/* 476:    */  public PointerBuffer put(PointerWrapper pointer)
/* 477:    */  {
/* 478:478 */    return put(pointer.getPointer());
/* 479:    */  }
/* 480:    */  
/* 486:    */  public static void put(ByteBuffer target, long l)
/* 487:    */  {
/* 488:488 */    if (is64Bit) {
/* 489:489 */      target.putLong(l);
/* 490:    */    } else {
/* 491:491 */      target.putInt((int)l);
/* 492:    */    }
/* 493:    */  }
/* 494:    */  
/* 504:    */  public long get(int index)
/* 505:    */  {
/* 506:506 */    if (is64Bit) {
/* 507:507 */      return this.view64.get(index);
/* 508:    */    }
/* 509:509 */    return this.view32.get(index) & 0xFFFFFFFF;
/* 510:    */  }
/* 511:    */  
/* 526:    */  public PointerBuffer put(int index, long l)
/* 527:    */  {
/* 528:528 */    if (is64Bit) {
/* 529:529 */      this.view64.put(index, l);
/* 530:    */    } else
/* 531:531 */      this.view32.put(index, (int)l);
/* 532:532 */    return this;
/* 533:    */  }
/* 534:    */  
/* 539:    */  public PointerBuffer put(int index, PointerWrapper pointer)
/* 540:    */  {
/* 541:541 */    return put(index, pointer.getPointer());
/* 542:    */  }
/* 543:    */  
/* 550:    */  public static void put(ByteBuffer target, int index, long l)
/* 551:    */  {
/* 552:552 */    if (is64Bit) {
/* 553:553 */      target.putLong(index, l);
/* 554:    */    } else {
/* 555:555 */      target.putInt(index, (int)l);
/* 556:    */    }
/* 557:    */  }
/* 558:    */  
/* 600:    */  public PointerBuffer get(long[] dst, int offset, int length)
/* 601:    */  {
/* 602:602 */    if (is64Bit) {
/* 603:603 */      this.view64.get(dst, offset, length);
/* 604:    */    } else {
/* 605:605 */      checkBounds(offset, length, dst.length);
/* 606:606 */      if (length > this.view32.remaining())
/* 607:607 */        throw new BufferUnderflowException();
/* 608:608 */      int end = offset + length;
/* 609:609 */      for (int i = offset; i < end; i++) {
/* 610:610 */        dst[i] = (this.view32.get() & 0xFFFFFFFF);
/* 611:    */      }
/* 612:    */    }
/* 613:613 */    return this;
/* 614:    */  }
/* 615:    */  
/* 630:    */  public PointerBuffer get(long[] dst)
/* 631:    */  {
/* 632:632 */    return get(dst, 0, dst.length);
/* 633:    */  }
/* 634:    */  
/* 669:    */  public PointerBuffer put(PointerBuffer src)
/* 670:    */  {
/* 671:671 */    if (is64Bit) {
/* 672:672 */      this.view64.put(src.view64);
/* 673:    */    } else
/* 674:674 */      this.view32.put(src.view32);
/* 675:675 */    return this;
/* 676:    */  }
/* 677:    */  
/* 717:    */  public PointerBuffer put(long[] src, int offset, int length)
/* 718:    */  {
/* 719:719 */    if (is64Bit) {
/* 720:720 */      this.view64.put(src, offset, length);
/* 721:    */    } else {
/* 722:722 */      checkBounds(offset, length, src.length);
/* 723:723 */      if (length > this.view32.remaining())
/* 724:724 */        throw new BufferOverflowException();
/* 725:725 */      int end = offset + length;
/* 726:726 */      for (int i = offset; i < end; i++) {
/* 727:727 */        this.view32.put((int)src[i]);
/* 728:    */      }
/* 729:    */    }
/* 730:730 */    return this;
/* 731:    */  }
/* 732:    */  
/* 748:    */  public final PointerBuffer put(long[] src)
/* 749:    */  {
/* 750:750 */    return put(src, 0, src.length);
/* 751:    */  }
/* 752:    */  
/* 774:    */  public PointerBuffer compact()
/* 775:    */  {
/* 776:776 */    if (is64Bit) {
/* 777:777 */      this.view64.compact();
/* 778:    */    } else
/* 779:779 */      this.view32.compact();
/* 780:780 */    return this;
/* 781:    */  }
/* 782:    */  
/* 794:    */  public ByteOrder order()
/* 795:    */  {
/* 796:796 */    if (is64Bit) {
/* 797:797 */      return this.view64.order();
/* 798:    */    }
/* 799:799 */    return this.view32.order();
/* 800:    */  }
/* 801:    */  
/* 806:    */  public String toString()
/* 807:    */  {
/* 808:808 */    StringBuilder sb = new StringBuilder(48);
/* 809:809 */    sb.append(getClass().getName());
/* 810:810 */    sb.append("[pos=");
/* 811:811 */    sb.append(position());
/* 812:812 */    sb.append(" lim=");
/* 813:813 */    sb.append(limit());
/* 814:814 */    sb.append(" cap=");
/* 815:815 */    sb.append(capacity());
/* 816:816 */    sb.append("]");
/* 817:817 */    return sb.toString();
/* 818:    */  }
/* 819:    */  
/* 832:    */  public int hashCode()
/* 833:    */  {
/* 834:834 */    int h = 1;
/* 835:835 */    int p = position();
/* 836:836 */    for (int i = limit() - 1; i >= p; i--)
/* 837:837 */      h = 31 * h + (int)get(i);
/* 838:838 */    return h;
/* 839:    */  }
/* 840:    */  
/* 865:    */  public boolean equals(Object ob)
/* 866:    */  {
/* 867:867 */    if (!(ob instanceof PointerBuffer))
/* 868:868 */      return false;
/* 869:869 */    PointerBuffer that = (PointerBuffer)ob;
/* 870:870 */    if (remaining() != that.remaining())
/* 871:871 */      return false;
/* 872:872 */    int p = position();
/* 873:873 */    int i = limit() - 1; for (int j = that.limit() - 1; i >= p; j--) {
/* 874:874 */      long v1 = get(i);
/* 875:875 */      long v2 = that.get(j);
/* 876:876 */      if (v1 != v2) {
/* 877:877 */        return false;
/* 878:    */      }
/* 879:873 */      i--;
/* 880:    */    }
/* 881:    */    
/* 886:880 */    return true;
/* 887:    */  }
/* 888:    */  
/* 900:    */  public int compareTo(Object o)
/* 901:    */  {
/* 902:896 */    PointerBuffer that = (PointerBuffer)o;
/* 903:897 */    int n = position() + Math.min(remaining(), that.remaining());
/* 904:898 */    int i = position(); for (int j = that.position(); i < n; j++) {
/* 905:899 */      long v1 = get(i);
/* 906:900 */      long v2 = that.get(j);
/* 907:901 */      if (v1 != v2)
/* 908:    */      {
/* 909:903 */        if (v1 < v2)
/* 910:904 */          return -1;
/* 911:905 */        return 1;
/* 912:    */      }
/* 913:898 */      i++;
/* 914:    */    }
/* 915:    */    
/* 922:907 */    return remaining() - that.remaining();
/* 923:    */  }
/* 924:    */  
/* 925:    */  private static void checkBounds(int off, int len, int size) {
/* 926:911 */    if ((off | len | off + len | size - (off + len)) < 0) {
/* 927:912 */      throw new IndexOutOfBoundsException();
/* 928:    */    }
/* 929:    */  }
/* 930:    */  
/* 933:    */  private static final class PointerBufferR
/* 934:    */    extends PointerBuffer
/* 935:    */  {
/* 936:    */    PointerBufferR(ByteBuffer source)
/* 937:    */    {
/* 938:923 */      super();
/* 939:    */    }
/* 940:    */    
/* 941:    */    public boolean isReadOnly() {
/* 942:927 */      return true;
/* 943:    */    }
/* 944:    */    
/* 945:    */    protected PointerBuffer newInstance(ByteBuffer source) {
/* 946:931 */      return new PointerBufferR(source);
/* 947:    */    }
/* 948:    */    
/* 949:    */    public PointerBuffer asReadOnlyBuffer() {
/* 950:935 */      return duplicate();
/* 951:    */    }
/* 952:    */    
/* 953:    */    public PointerBuffer put(long l) {
/* 954:939 */      throw new ReadOnlyBufferException();
/* 955:    */    }
/* 956:    */    
/* 957:    */    public PointerBuffer put(int index, long l) {
/* 958:943 */      throw new ReadOnlyBufferException();
/* 959:    */    }
/* 960:    */    
/* 961:    */    public PointerBuffer put(PointerBuffer src) {
/* 962:947 */      throw new ReadOnlyBufferException();
/* 963:    */    }
/* 964:    */    
/* 965:    */    public PointerBuffer put(long[] src, int offset, int length) {
/* 966:951 */      throw new ReadOnlyBufferException();
/* 967:    */    }
/* 968:    */    
/* 969:    */    public PointerBuffer compact() {
/* 970:955 */      throw new ReadOnlyBufferException();
/* 971:    */    }
/* 972:    */  }
/* 973:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.PointerBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */