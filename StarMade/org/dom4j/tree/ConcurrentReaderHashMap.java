/*    1:     */package org.dom4j.tree;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.ObjectInputStream;
/*    5:     */import java.io.ObjectOutputStream;
/*    6:     */import java.io.Serializable;
/*    7:     */import java.util.AbstractCollection;
/*    8:     */import java.util.AbstractMap;
/*    9:     */import java.util.AbstractSet;
/*   10:     */import java.util.Collection;
/*   11:     */import java.util.Enumeration;
/*   12:     */import java.util.Iterator;
/*   13:     */import java.util.Map;
/*   14:     */import java.util.Map.Entry;
/*   15:     */import java.util.NoSuchElementException;
/*   16:     */import java.util.Set;
/*   17:     */
/*  183:     */class ConcurrentReaderHashMap
/*  184:     */  extends AbstractMap
/*  185:     */  implements Map, Cloneable, Serializable
/*  186:     */{
/*  187: 187 */  protected final BarrierLock barrierLock = new BarrierLock();
/*  188:     */  
/*  192:     */  protected transient Object lastWrite;
/*  193:     */  
/*  198:     */  protected final void recordModification(Object x)
/*  199:     */  {
/*  200: 200 */    synchronized (this.barrierLock) {
/*  201: 201 */      this.lastWrite = x;
/*  202:     */    }
/*  203:     */  }
/*  204:     */  
/*  208:     */  protected final Entry[] getTableForReading()
/*  209:     */  {
/*  210: 210 */    synchronized (this.barrierLock) {
/*  211: 211 */      return this.table;
/*  212:     */    }
/*  213:     */  }
/*  214:     */  
/*  219: 219 */  public static int DEFAULT_INITIAL_CAPACITY = 32;
/*  220:     */  
/*  226:     */  private static final int MINIMUM_CAPACITY = 4;
/*  227:     */  
/*  232:     */  private static final int MAXIMUM_CAPACITY = 1073741824;
/*  233:     */  
/*  238:     */  public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*  239:     */  
/*  244:     */  protected transient Entry[] table;
/*  245:     */  
/*  250:     */  protected transient int count;
/*  251:     */  
/*  256:     */  protected int threshold;
/*  257:     */  
/*  262:     */  protected float loadFactor;
/*  263:     */  
/*  269:     */  private int p2capacity(int initialCapacity)
/*  270:     */  {
/*  271: 271 */    int cap = initialCapacity;
/*  272:     */    
/*  273:     */    int result;
/*  274:     */    int result;
/*  275: 275 */    if ((cap > 1073741824) || (cap < 0)) {
/*  276: 276 */      result = 1073741824;
/*  277:     */    } else {
/*  278: 278 */      result = 4;
/*  279: 279 */      while (result < cap)
/*  280: 280 */        result <<= 1;
/*  281:     */    }
/*  282: 282 */    return result;
/*  283:     */  }
/*  284:     */  
/*  289:     */  private static int hash(Object x)
/*  290:     */  {
/*  291: 291 */    int h = x.hashCode();
/*  292:     */    
/*  295: 295 */    return (h << 7) - h + (h >>> 9) + (h >>> 17);
/*  296:     */  }
/*  297:     */  
/*  300:     */  protected boolean eq(Object x, Object y)
/*  301:     */  {
/*  302: 302 */    return (x == y) || (x.equals(y));
/*  303:     */  }
/*  304:     */  
/*  318:     */  public ConcurrentReaderHashMap(int initialCapacity, float loadFactor)
/*  319:     */  {
/*  320: 320 */    if (loadFactor <= 0.0F) {
/*  321: 321 */      throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
/*  322:     */    }
/*  323: 323 */    this.loadFactor = loadFactor;
/*  324:     */    
/*  325: 325 */    int cap = p2capacity(initialCapacity);
/*  326:     */    
/*  327: 327 */    this.table = new Entry[cap];
/*  328: 328 */    this.threshold = ((int)(cap * loadFactor));
/*  329:     */  }
/*  330:     */  
/*  340:     */  public ConcurrentReaderHashMap(int initialCapacity)
/*  341:     */  {
/*  342: 342 */    this(initialCapacity, 0.75F);
/*  343:     */  }
/*  344:     */  
/*  349:     */  public ConcurrentReaderHashMap()
/*  350:     */  {
/*  351: 351 */    this(DEFAULT_INITIAL_CAPACITY, 0.75F);
/*  352:     */  }
/*  353:     */  
/*  359:     */  public ConcurrentReaderHashMap(Map t)
/*  360:     */  {
/*  361: 361 */    this(Math.max((int)(t.size() / 0.75F) + 1, 16), 0.75F);
/*  362:     */    
/*  363: 363 */    putAll(t);
/*  364:     */  }
/*  365:     */  
/*  371:     */  public synchronized int size()
/*  372:     */  {
/*  373: 373 */    return this.count;
/*  374:     */  }
/*  375:     */  
/*  381:     */  public synchronized boolean isEmpty()
/*  382:     */  {
/*  383: 383 */    return this.count == 0;
/*  384:     */  }
/*  385:     */  
/*  400:     */  public Object get(Object key)
/*  401:     */  {
/*  402: 402 */    int hash = hash(key);
/*  403:     */    
/*  412: 412 */    Entry[] tab = this.table;
/*  413: 413 */    int index = hash & tab.length - 1;
/*  414: 414 */    Entry first = tab[index];
/*  415: 415 */    Entry e = first;
/*  416:     */    for (;;)
/*  417:     */    {
/*  418: 418 */      if (e == null)
/*  419:     */      {
/*  423: 423 */        Entry[] reread = getTableForReading();
/*  424: 424 */        if ((tab == reread) && (first == tab[index])) {
/*  425: 425 */          return null;
/*  426:     */        }
/*  427:     */        
/*  428: 428 */        tab = reread;
/*  429: 429 */        e = first = tab[(index = hash & tab.length - 1)];
/*  433:     */      }
/*  434: 434 */      else if ((e.hash == hash) && (eq(key, e.key))) {
/*  435: 435 */        Object value = e.value;
/*  436: 436 */        if (value != null) {
/*  437: 437 */          return value;
/*  438:     */        }
/*  439:     */        
/*  445: 445 */        synchronized (this) {
/*  446: 446 */          tab = this.table;
/*  447:     */        }
/*  448: 448 */        e = first = tab[(index = hash & tab.length - 1)];
/*  449:     */      }
/*  450:     */      else {
/*  451: 451 */        e = e.next;
/*  452:     */      }
/*  453:     */    }
/*  454:     */  }
/*  455:     */  
/*  467:     */  public boolean containsKey(Object key)
/*  468:     */  {
/*  469: 469 */    return get(key) != null;
/*  470:     */  }
/*  471:     */  
/*  491:     */  public Object put(Object key, Object value)
/*  492:     */  {
/*  493: 493 */    if (value == null) {
/*  494: 494 */      throw new NullPointerException();
/*  495:     */    }
/*  496: 496 */    int hash = hash(key);
/*  497: 497 */    Entry[] tab = this.table;
/*  498: 498 */    int index = hash & tab.length - 1;
/*  499: 499 */    Entry first = tab[index];
/*  500:     */    
/*  502: 502 */    for (Entry e = first; (e != null) && (
/*  503: 503 */          (e.hash != hash) || (!eq(key, e.key))); e = e.next) {}
/*  504:     */    
/*  507: 506 */    synchronized (this) {
/*  508: 507 */      if (tab == this.table) {
/*  509: 508 */        if (e == null)
/*  510:     */        {
/*  511: 510 */          if (first == tab[index])
/*  512:     */          {
/*  513: 512 */            Entry newEntry = new Entry(hash, key, value, first);
/*  514: 513 */            tab[index] = newEntry;
/*  515: 514 */            if (++this.count >= this.threshold) {
/*  516: 515 */              rehash();
/*  517:     */            } else
/*  518: 517 */              recordModification(newEntry);
/*  519: 518 */            return null;
/*  520:     */          }
/*  521:     */        } else {
/*  522: 521 */          Object oldValue = e.value;
/*  523: 522 */          if ((first == tab[index]) && (oldValue != null)) {
/*  524: 523 */            e.value = value;
/*  525: 524 */            return oldValue;
/*  526:     */          }
/*  527:     */        }
/*  528:     */      }
/*  529:     */      
/*  531: 530 */      return sput(key, value, hash);
/*  532:     */    }
/*  533:     */  }
/*  534:     */  
/*  539:     */  protected Object sput(Object key, Object value, int hash)
/*  540:     */  {
/*  541: 540 */    Entry[] tab = this.table;
/*  542: 541 */    int index = hash & tab.length - 1;
/*  543: 542 */    Entry first = tab[index];
/*  544: 543 */    Entry e = first;
/*  545:     */    for (;;)
/*  546:     */    {
/*  547: 546 */      if (e == null) {
/*  548: 547 */        Entry newEntry = new Entry(hash, key, value, first);
/*  549: 548 */        tab[index] = newEntry;
/*  550: 549 */        if (++this.count >= this.threshold) {
/*  551: 550 */          rehash();
/*  552:     */        } else
/*  553: 552 */          recordModification(newEntry);
/*  554: 553 */        return null; }
/*  555: 554 */      if ((e.hash == hash) && (eq(key, e.key))) {
/*  556: 555 */        Object oldValue = e.value;
/*  557: 556 */        e.value = value;
/*  558: 557 */        return oldValue;
/*  559:     */      }
/*  560: 559 */      e = e.next;
/*  561:     */    }
/*  562:     */  }
/*  563:     */  
/*  568:     */  protected void rehash()
/*  569:     */  {
/*  570: 569 */    Entry[] oldTable = this.table;
/*  571: 570 */    int oldCapacity = oldTable.length;
/*  572: 571 */    if (oldCapacity >= 1073741824) {
/*  573: 572 */      this.threshold = 2147483647;
/*  574: 573 */      return;
/*  575:     */    }
/*  576:     */    
/*  577: 576 */    int newCapacity = oldCapacity << 1;
/*  578: 577 */    int mask = newCapacity - 1;
/*  579: 578 */    this.threshold = ((int)(newCapacity * this.loadFactor));
/*  580:     */    
/*  581: 580 */    Entry[] newTable = new Entry[newCapacity];
/*  582:     */    
/*  594: 593 */    for (int i = 0; i < oldCapacity; i++)
/*  595:     */    {
/*  597: 596 */      Entry e = oldTable[i];
/*  598:     */      
/*  599: 598 */      if (e != null) {
/*  600: 599 */        int idx = e.hash & mask;
/*  601: 600 */        Entry next = e.next;
/*  602:     */        
/*  604: 603 */        if (next == null) {
/*  605: 604 */          newTable[idx] = e;
/*  606:     */        }
/*  607:     */        else
/*  608:     */        {
/*  609: 608 */          Entry lastRun = e;
/*  610: 609 */          int lastIdx = idx;
/*  611: 610 */          for (Entry last = next; last != null; last = last.next) {
/*  612: 611 */            int k = last.hash & mask;
/*  613: 612 */            if (k != lastIdx) {
/*  614: 613 */              lastIdx = k;
/*  615: 614 */              lastRun = last;
/*  616:     */            }
/*  617:     */          }
/*  618: 617 */          newTable[lastIdx] = lastRun;
/*  619:     */          
/*  621: 620 */          for (Entry p = e; p != lastRun; p = p.next) {
/*  622: 621 */            int k = p.hash & mask;
/*  623: 622 */            newTable[k] = new Entry(p.hash, p.key, p.value, newTable[k]);
/*  624:     */          }
/*  625:     */        }
/*  626:     */      }
/*  627:     */    }
/*  628:     */    
/*  630: 629 */    this.table = newTable;
/*  631: 630 */    recordModification(newTable);
/*  632:     */  }
/*  633:     */  
/*  653:     */  public Object remove(Object key)
/*  654:     */  {
/*  655: 654 */    int hash = hash(key);
/*  656: 655 */    Entry[] tab = this.table;
/*  657: 656 */    int index = hash & tab.length - 1;
/*  658: 657 */    Entry first = tab[index];
/*  659: 658 */    Entry e = first;
/*  660:     */    
/*  661: 660 */    for (e = first; (e != null) && (
/*  662: 661 */          (e.hash != hash) || (!eq(key, e.key))); e = e.next) {}
/*  663:     */    
/*  666: 664 */    synchronized (this) {
/*  667: 665 */      if (tab == this.table) {
/*  668: 666 */        if (e == null) {
/*  669: 667 */          if (first == tab[index])
/*  670: 668 */            return null;
/*  671:     */        } else {
/*  672: 670 */          Object oldValue = e.value;
/*  673: 671 */          if ((first == tab[index]) && (oldValue != null)) {
/*  674: 672 */            e.value = null;
/*  675: 673 */            this.count -= 1;
/*  676:     */            
/*  677: 675 */            Entry head = e.next;
/*  678: 676 */            for (Entry p = first; p != e; p = p.next) {
/*  679: 677 */              head = new Entry(p.hash, p.key, p.value, head);
/*  680:     */            }
/*  681: 679 */            tab[index] = head;
/*  682: 680 */            recordModification(head);
/*  683: 681 */            return oldValue;
/*  684:     */          }
/*  685:     */        }
/*  686:     */      }
/*  687:     */      
/*  689: 687 */      return sremove(key, hash);
/*  690:     */    }
/*  691:     */  }
/*  692:     */  
/*  697:     */  protected Object sremove(Object key, int hash)
/*  698:     */  {
/*  699: 697 */    Entry[] tab = this.table;
/*  700: 698 */    int index = hash & tab.length - 1;
/*  701: 699 */    Entry first = tab[index];
/*  702:     */    
/*  703: 701 */    for (Entry e = first; e != null; e = e.next) {
/*  704: 702 */      if ((e.hash == hash) && (eq(key, e.key))) {
/*  705: 703 */        Object oldValue = e.value;
/*  706: 704 */        e.value = null;
/*  707: 705 */        this.count -= 1;
/*  708: 706 */        Entry head = e.next;
/*  709: 707 */        for (Entry p = first; p != e; p = p.next) {
/*  710: 708 */          head = new Entry(p.hash, p.key, p.value, head);
/*  711:     */        }
/*  712: 710 */        tab[index] = head;
/*  713: 711 */        recordModification(head);
/*  714: 712 */        return oldValue;
/*  715:     */      }
/*  716:     */    }
/*  717: 715 */    return null;
/*  718:     */  }
/*  719:     */  
/*  732:     */  public boolean containsValue(Object value)
/*  733:     */  {
/*  734: 732 */    if (value == null) {
/*  735: 733 */      throw new NullPointerException();
/*  736:     */    }
/*  737: 735 */    Entry[] tab = getTableForReading();
/*  738:     */    
/*  739: 737 */    for (int i = 0; i < tab.length; i++) {
/*  740: 738 */      for (Entry e = tab[i]; e != null; e = e.next) {
/*  741: 739 */        if (value.equals(e.value))
/*  742: 740 */          return true;
/*  743:     */      }
/*  744:     */    }
/*  745: 743 */    return false;
/*  746:     */  }
/*  747:     */  
/*  756:     */  protected static class BarrierLock
/*  757:     */    implements Serializable
/*  758:     */  {}
/*  759:     */  
/*  767:     */  public boolean contains(Object value)
/*  768:     */  {
/*  769: 767 */    return containsValue(value);
/*  770:     */  }
/*  771:     */  
/*  781:     */  public synchronized void putAll(Map t)
/*  782:     */  {
/*  783: 781 */    int n = t.size();
/*  784: 782 */    if (n == 0) {
/*  785: 783 */      return;
/*  786:     */    }
/*  787:     */    
/*  790: 788 */    while (n >= this.threshold) {
/*  791: 789 */      rehash();
/*  792:     */    }
/*  793: 791 */    for (Iterator it = t.entrySet().iterator(); it.hasNext();) {
/*  794: 792 */      Map.Entry entry = (Map.Entry)it.next();
/*  795: 793 */      Object key = entry.getKey();
/*  796: 794 */      Object value = entry.getValue();
/*  797: 795 */      put(key, value);
/*  798:     */    }
/*  799:     */  }
/*  800:     */  
/*  803:     */  public synchronized void clear()
/*  804:     */  {
/*  805: 803 */    Entry[] tab = this.table;
/*  806: 804 */    for (int i = 0; i < tab.length; i++)
/*  807:     */    {
/*  810: 808 */      for (Entry e = tab[i]; e != null; e = e.next) {
/*  811: 809 */        e.value = null;
/*  812:     */      }
/*  813: 811 */      tab[i] = null;
/*  814:     */    }
/*  815: 813 */    this.count = 0;
/*  816: 814 */    recordModification(tab);
/*  817:     */  }
/*  818:     */  
/*  824:     */  public synchronized Object clone()
/*  825:     */  {
/*  826:     */    try
/*  827:     */    {
/*  828: 826 */      ConcurrentReaderHashMap t = (ConcurrentReaderHashMap)super.clone();
/*  829:     */      
/*  830: 828 */      t.keySet = null;
/*  831: 829 */      t.entrySet = null;
/*  832: 830 */      t.values = null;
/*  833:     */      
/*  834: 832 */      Entry[] tab = this.table;
/*  835: 833 */      t.table = new Entry[tab.length];
/*  836: 834 */      Entry[] ttab = t.table;
/*  837:     */      
/*  838: 836 */      for (int i = 0; i < tab.length; i++) {
/*  839: 837 */        Entry first = null;
/*  840: 838 */        for (Entry e = tab[i]; e != null; e = e.next)
/*  841: 839 */          first = new Entry(e.hash, e.key, e.value, first);
/*  842: 840 */        ttab[i] = first;
/*  843:     */      }
/*  844:     */      
/*  845: 843 */      return t;
/*  846:     */    }
/*  847:     */    catch (CloneNotSupportedException e) {
/*  848: 846 */      throw new InternalError();
/*  849:     */    }
/*  850:     */  }
/*  851:     */  
/*  854: 852 */  protected transient Set keySet = null;
/*  855:     */  
/*  856: 854 */  protected transient Set entrySet = null;
/*  857:     */  
/*  858: 856 */  protected transient Collection values = null;
/*  859:     */  
/*  871:     */  public Set keySet()
/*  872:     */  {
/*  873: 871 */    Set ks = this.keySet;
/*  874: 872 */    return this.keySet = new KeySet(null);
/*  875:     */  }
/*  876:     */  
/*  877: 875 */  private class KeySet extends AbstractSet { KeySet(ConcurrentReaderHashMap.1 x1) { this(); }
/*  878:     */    
/*  879: 877 */    public Iterator iterator() { return new ConcurrentReaderHashMap.KeyIterator(ConcurrentReaderHashMap.this); }
/*  880:     */    
/*  881:     */    public int size()
/*  882:     */    {
/*  883: 881 */      return ConcurrentReaderHashMap.this.size();
/*  884:     */    }
/*  885:     */    
/*  886:     */    public boolean contains(Object o) {
/*  887: 885 */      return ConcurrentReaderHashMap.this.containsKey(o);
/*  888:     */    }
/*  889:     */    
/*  890:     */    public boolean remove(Object o) {
/*  891: 889 */      return ConcurrentReaderHashMap.this.remove(o) != null;
/*  892:     */    }
/*  893:     */    
/*  894:     */    public void clear() {
/*  895: 893 */      ConcurrentReaderHashMap.this.clear();
/*  896:     */    }
/*  897:     */    
/*  903:     */    private KeySet() {}
/*  904:     */  }
/*  905:     */  
/*  911:     */  public Collection values()
/*  912:     */  {
/*  913: 911 */    Collection vs = this.values;
/*  914: 912 */    return this.values = new Values(null);
/*  915:     */  }
/*  916:     */  
/*  917: 915 */  private class Values extends AbstractCollection { Values(ConcurrentReaderHashMap.1 x1) { this(); }
/*  918:     */    
/*  919: 917 */    public Iterator iterator() { return new ConcurrentReaderHashMap.ValueIterator(ConcurrentReaderHashMap.this); }
/*  920:     */    
/*  921:     */    public int size()
/*  922:     */    {
/*  923: 921 */      return ConcurrentReaderHashMap.this.size();
/*  924:     */    }
/*  925:     */    
/*  926:     */    public boolean contains(Object o) {
/*  927: 925 */      return ConcurrentReaderHashMap.this.containsValue(o);
/*  928:     */    }
/*  929:     */    
/*  930:     */    public void clear() {
/*  931: 929 */      ConcurrentReaderHashMap.this.clear();
/*  932:     */    }
/*  933:     */    
/*  940:     */    private Values() {}
/*  941:     */  }
/*  942:     */  
/*  948:     */  public Set entrySet()
/*  949:     */  {
/*  950: 948 */    Set es = this.entrySet;
/*  951: 949 */    return this.entrySet = new EntrySet(null);
/*  952:     */  }
/*  953:     */  
/*  954: 952 */  private class EntrySet extends AbstractSet { EntrySet(ConcurrentReaderHashMap.1 x1) { this(); }
/*  955:     */    
/*  956: 954 */    public Iterator iterator() { return new ConcurrentReaderHashMap.HashIterator(ConcurrentReaderHashMap.this); }
/*  957:     */    
/*  958:     */    public boolean contains(Object o)
/*  959:     */    {
/*  960: 958 */      if (!(o instanceof Map.Entry))
/*  961: 959 */        return false;
/*  962: 960 */      Map.Entry entry = (Map.Entry)o;
/*  963: 961 */      Object v = ConcurrentReaderHashMap.this.get(entry.getKey());
/*  964: 962 */      return (v != null) && (v.equals(entry.getValue()));
/*  965:     */    }
/*  966:     */    
/*  967:     */    public boolean remove(Object o) {
/*  968: 966 */      if (!(o instanceof Map.Entry))
/*  969: 967 */        return false;
/*  970: 968 */      return ConcurrentReaderHashMap.this.findAndRemoveEntry((Map.Entry)o);
/*  971:     */    }
/*  972:     */    
/*  973:     */    public int size()
/*  974:     */    {
/*  975: 973 */      return ConcurrentReaderHashMap.this.size();
/*  976:     */    }
/*  977:     */    
/*  978:     */    public void clear() {
/*  979: 977 */      ConcurrentReaderHashMap.this.clear();
/*  980:     */    }
/*  981:     */    
/*  982:     */    private EntrySet() {}
/*  983:     */  }
/*  984:     */  
/*  985:     */  protected synchronized boolean findAndRemoveEntry(Map.Entry entry)
/*  986:     */  {
/*  987: 985 */    Object key = entry.getKey();
/*  988: 986 */    Object v = get(key);
/*  989: 987 */    if ((v != null) && (v.equals(entry.getValue()))) {
/*  990: 988 */      remove(key);
/*  991: 989 */      return true;
/*  992:     */    }
/*  993: 991 */    return false;
/*  994:     */  }
/*  995:     */  
/* 1004:     */  public Enumeration keys()
/* 1005:     */  {
/* 1006:1004 */    return new KeyIterator();
/* 1007:     */  }
/* 1008:     */  
/* 1019:     */  public Enumeration elements()
/* 1020:     */  {
/* 1021:1019 */    return new ValueIterator();
/* 1022:     */  }
/* 1023:     */  
/* 1027:     */  protected static class Entry
/* 1028:     */    implements Map.Entry
/* 1029:     */  {
/* 1030:     */    protected final int hash;
/* 1031:     */    
/* 1033:     */    protected final Object key;
/* 1034:     */    
/* 1036:     */    protected final Entry next;
/* 1037:     */    
/* 1039:     */    protected volatile Object value;
/* 1040:     */    
/* 1043:     */    Entry(int hash, Object key, Object value, Entry next)
/* 1044:     */    {
/* 1045:1043 */      this.hash = hash;
/* 1046:1044 */      this.key = key;
/* 1047:1045 */      this.next = next;
/* 1048:1046 */      this.value = value;
/* 1049:     */    }
/* 1050:     */    
/* 1052:     */    public Object getKey()
/* 1053:     */    {
/* 1054:1052 */      return this.key;
/* 1055:     */    }
/* 1056:     */    
/* 1068:     */    public Object getValue()
/* 1069:     */    {
/* 1070:1068 */      return this.value;
/* 1071:     */    }
/* 1072:     */    
/* 1094:     */    public Object setValue(Object value)
/* 1095:     */    {
/* 1096:1094 */      if (value == null)
/* 1097:1095 */        throw new NullPointerException();
/* 1098:1096 */      Object oldValue = this.value;
/* 1099:1097 */      this.value = value;
/* 1100:1098 */      return oldValue;
/* 1101:     */    }
/* 1102:     */    
/* 1103:     */    public boolean equals(Object o) {
/* 1104:1102 */      if (!(o instanceof Map.Entry))
/* 1105:1103 */        return false;
/* 1106:1104 */      Map.Entry e = (Map.Entry)o;
/* 1107:1105 */      return (this.key.equals(e.getKey())) && (this.value.equals(e.getValue()));
/* 1108:     */    }
/* 1109:     */    
/* 1110:     */    public int hashCode() {
/* 1111:1109 */      return this.key.hashCode() ^ this.value.hashCode();
/* 1112:     */    }
/* 1113:     */    
/* 1114:     */    public String toString() {
/* 1115:1113 */      return this.key + "=" + this.value;
/* 1116:     */    }
/* 1117:     */  }
/* 1118:     */  
/* 1120:     */  protected class HashIterator
/* 1121:     */    implements Iterator, Enumeration
/* 1122:     */  {
/* 1123:     */    protected final ConcurrentReaderHashMap.Entry[] tab;
/* 1124:     */    protected int index;
/* 1125:1123 */    protected ConcurrentReaderHashMap.Entry entry = null;
/* 1126:     */    
/* 1127:     */    protected Object currentKey;
/* 1128:     */    
/* 1129:     */    protected Object currentValue;
/* 1130:     */    
/* 1131:1129 */    protected ConcurrentReaderHashMap.Entry lastReturned = null;
/* 1132:     */    
/* 1133:     */    protected HashIterator() {
/* 1134:1132 */      this.tab = ConcurrentReaderHashMap.this.getTableForReading();
/* 1135:1133 */      this.index = (this.tab.length - 1);
/* 1136:     */    }
/* 1137:     */    
/* 1138:     */    public boolean hasMoreElements() {
/* 1139:1137 */      return hasNext();
/* 1140:     */    }
/* 1141:     */    
/* 1142:     */    public Object nextElement() {
/* 1143:1141 */      return next();
/* 1144:     */    }
/* 1145:     */    
/* 1152:     */    public boolean hasNext()
/* 1153:     */    {
/* 1154:     */      do
/* 1155:     */      {
/* 1156:1154 */        if (this.entry != null) {
/* 1157:1155 */          Object v = this.entry.value;
/* 1158:1156 */          if (v != null) {
/* 1159:1157 */            this.currentKey = this.entry.key;
/* 1160:1158 */            this.currentValue = v;
/* 1161:1159 */            return true;
/* 1162:     */          }
/* 1163:1161 */          this.entry = this.entry.next;
/* 1164:     */        }
/* 1165:     */        
/* 1166:1164 */        while ((this.entry == null) && (this.index >= 0)) {
/* 1167:1165 */          this.entry = this.tab[(this.index--)];
/* 1168:     */        }
/* 1169:1167 */      } while (this.entry != null);
/* 1170:1168 */      this.currentKey = (this.currentValue = null);
/* 1171:1169 */      return false;
/* 1172:     */    }
/* 1173:     */    
/* 1175:     */    protected Object returnValueOfNext()
/* 1176:     */    {
/* 1177:1175 */      return this.entry;
/* 1178:     */    }
/* 1179:     */    
/* 1180:     */    public Object next() {
/* 1181:1179 */      if ((this.currentKey == null) && (!hasNext())) {
/* 1182:1180 */        throw new NoSuchElementException();
/* 1183:     */      }
/* 1184:1182 */      Object result = returnValueOfNext();
/* 1185:1183 */      this.lastReturned = this.entry;
/* 1186:1184 */      this.currentKey = (this.currentValue = null);
/* 1187:1185 */      this.entry = this.entry.next;
/* 1188:1186 */      return result;
/* 1189:     */    }
/* 1190:     */    
/* 1191:     */    public void remove() {
/* 1192:1190 */      if (this.lastReturned == null)
/* 1193:1191 */        throw new IllegalStateException();
/* 1194:1192 */      ConcurrentReaderHashMap.this.remove(this.lastReturned.key);
/* 1195:1193 */      this.lastReturned = null;
/* 1196:     */    }
/* 1197:     */  }
/* 1198:     */  
/* 1199:     */  protected class KeyIterator extends ConcurrentReaderHashMap.HashIterator {
/* 1200:1198 */    protected KeyIterator() { super(); }
/* 1201:     */    
/* 1202:1200 */    protected Object returnValueOfNext() { return this.currentKey; }
/* 1203:     */  }
/* 1204:     */  
/* 1205:     */  protected class ValueIterator extends ConcurrentReaderHashMap.HashIterator {
/* 1206:1204 */    protected ValueIterator() { super(); }
/* 1207:     */    
/* 1208:1206 */    protected Object returnValueOfNext() { return this.currentValue; }
/* 1209:     */  }
/* 1210:     */  
/* 1225:     */  private synchronized void writeObject(ObjectOutputStream s)
/* 1226:     */    throws IOException
/* 1227:     */  {
/* 1228:1226 */    s.defaultWriteObject();
/* 1229:     */    
/* 1231:1229 */    s.writeInt(this.table.length);
/* 1232:     */    
/* 1234:1232 */    s.writeInt(this.count);
/* 1235:     */    
/* 1237:1235 */    for (int index = this.table.length - 1; index >= 0; index--) {
/* 1238:1236 */      Entry entry = this.table[index];
/* 1239:     */      
/* 1240:1238 */      while (entry != null) {
/* 1241:1239 */        s.writeObject(entry.key);
/* 1242:1240 */        s.writeObject(entry.value);
/* 1243:1241 */        entry = entry.next;
/* 1244:     */      }
/* 1245:     */    }
/* 1246:     */  }
/* 1247:     */  
/* 1252:     */  private synchronized void readObject(ObjectInputStream s)
/* 1253:     */    throws IOException, ClassNotFoundException
/* 1254:     */  {
/* 1255:1253 */    s.defaultReadObject();
/* 1256:     */    
/* 1258:1256 */    int numBuckets = s.readInt();
/* 1259:1257 */    this.table = new Entry[numBuckets];
/* 1260:     */    
/* 1262:1260 */    int size = s.readInt();
/* 1263:     */    
/* 1265:1263 */    for (int i = 0; i < size; i++) {
/* 1266:1264 */      Object key = s.readObject();
/* 1267:1265 */      Object value = s.readObject();
/* 1268:1266 */      put(key, value);
/* 1269:     */    }
/* 1270:     */  }
/* 1271:     */  
/* 1275:     */  public synchronized int capacity()
/* 1276:     */  {
/* 1277:1275 */    return this.table.length;
/* 1278:     */  }
/* 1279:     */  
/* 1282:     */  public float loadFactor()
/* 1283:     */  {
/* 1284:1282 */    return this.loadFactor;
/* 1285:     */  }
/* 1286:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ConcurrentReaderHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */