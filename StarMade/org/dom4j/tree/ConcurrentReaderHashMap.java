/*      */ package org.dom4j.tree;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ 
/*      */ class ConcurrentReaderHashMap extends AbstractMap
/*      */   implements Map, Cloneable, Serializable
/*      */ {
/*  187 */   protected final BarrierLock barrierLock = new BarrierLock();
/*      */   protected transient Object lastWrite;
/*  219 */   public static int DEFAULT_INITIAL_CAPACITY = 32;
/*      */   private static final int MINIMUM_CAPACITY = 4;
/*      */   private static final int MAXIMUM_CAPACITY = 1073741824;
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   protected transient Entry[] table;
/*      */   protected transient int count;
/*      */   protected int threshold;
/*      */   protected float loadFactor;
/*  852 */   protected transient Set keySet = null;
/*      */ 
/*  854 */   protected transient Set entrySet = null;
/*      */ 
/*  856 */   protected transient Collection values = null;
/*      */ 
/*      */   protected final void recordModification(Object x)
/*      */   {
/*  200 */     synchronized (this.barrierLock) {
/*  201 */       this.lastWrite = x;
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final Entry[] getTableForReading()
/*      */   {
/*  210 */     synchronized (this.barrierLock) {
/*  211 */       return this.table;
/*      */     }
/*      */   }
/*      */ 
/*      */   private int p2capacity(int initialCapacity)
/*      */   {
/*  271 */     int cap = initialCapacity;
/*      */     int result;
/*      */     int result;
/*  275 */     if ((cap > 1073741824) || (cap < 0)) {
/*  276 */       result = 1073741824;
/*      */     } else {
/*  278 */       result = 4;
/*  279 */       while (result < cap)
/*  280 */         result <<= 1;
/*      */     }
/*  282 */     return result;
/*      */   }
/*      */ 
/*      */   private static int hash(Object x)
/*      */   {
/*  291 */     int h = x.hashCode();
/*      */ 
/*  295 */     return (h << 7) - h + (h >>> 9) + (h >>> 17);
/*      */   }
/*      */ 
/*      */   protected boolean eq(Object x, Object y)
/*      */   {
/*  302 */     return (x == y) || (x.equals(y));
/*      */   }
/*      */ 
/*      */   public ConcurrentReaderHashMap(int initialCapacity, float loadFactor)
/*      */   {
/*  320 */     if (loadFactor <= 0.0F) {
/*  321 */       throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
/*      */     }
/*  323 */     this.loadFactor = loadFactor;
/*      */ 
/*  325 */     int cap = p2capacity(initialCapacity);
/*      */ 
/*  327 */     this.table = new Entry[cap];
/*  328 */     this.threshold = ((int)(cap * loadFactor));
/*      */   }
/*      */ 
/*      */   public ConcurrentReaderHashMap(int initialCapacity)
/*      */   {
/*  342 */     this(initialCapacity, 0.75F);
/*      */   }
/*      */ 
/*      */   public ConcurrentReaderHashMap()
/*      */   {
/*  351 */     this(DEFAULT_INITIAL_CAPACITY, 0.75F);
/*      */   }
/*      */ 
/*      */   public ConcurrentReaderHashMap(Map t)
/*      */   {
/*  361 */     this(Math.max((int)(t.size() / 0.75F) + 1, 16), 0.75F);
/*      */ 
/*  363 */     putAll(t);
/*      */   }
/*      */ 
/*      */   public synchronized int size()
/*      */   {
/*  373 */     return this.count;
/*      */   }
/*      */ 
/*      */   public synchronized boolean isEmpty()
/*      */   {
/*  383 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public Object get(Object key)
/*      */   {
/*  402 */     int hash = hash(key);
/*      */ 
/*  412 */     Entry[] tab = this.table;
/*  413 */     int index = hash & tab.length - 1;
/*  414 */     Entry first = tab[index];
/*  415 */     Entry e = first;
/*      */     while (true)
/*      */     {
/*  418 */       if (e == null)
/*      */       {
/*  423 */         Entry[] reread = getTableForReading();
/*  424 */         if ((tab == reread) && (first == tab[index])) {
/*  425 */           return null;
/*      */         }
/*      */ 
/*  428 */         tab = reread;
/*  429 */         e = first = tab[(index = hash & tab.length - 1)];
/*      */       }
/*  434 */       else if ((e.hash == hash) && (eq(key, e.key))) {
/*  435 */         Object value = e.value;
/*  436 */         if (value != null) {
/*  437 */           return value;
/*      */         }
/*      */ 
/*  445 */         synchronized (this) {
/*  446 */           tab = this.table;
/*      */         }
/*  448 */         e = first = tab[(index = hash & tab.length - 1)];
/*      */       }
/*      */       else {
/*  451 */         e = e.next;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object key)
/*      */   {
/*  469 */     return get(key) != null;
/*      */   }
/*      */ 
/*      */   public Object put(Object key, Object value)
/*      */   {
/*  493 */     if (value == null) {
/*  494 */       throw new NullPointerException();
/*      */     }
/*  496 */     int hash = hash(key);
/*  497 */     Entry[] tab = this.table;
/*  498 */     int index = hash & tab.length - 1;
/*  499 */     Entry first = tab[index];
/*      */ 
/*  502 */     for (Entry e = first; (e != null) && (
/*  503 */       (e.hash != hash) || (!eq(key, e.key))); e = e.next);
/*  506 */     synchronized (this) {
/*  507 */       if (tab == this.table) {
/*  508 */         if (e == null)
/*      */         {
/*  510 */           if (first == tab[index])
/*      */           {
/*  512 */             Entry newEntry = new Entry(hash, key, value, first);
/*  513 */             tab[index] = newEntry;
/*  514 */             if (++this.count >= this.threshold)
/*  515 */               rehash();
/*      */             else
/*  517 */               recordModification(newEntry);
/*  518 */             return null;
/*      */           }
/*      */         } else {
/*  521 */           Object oldValue = e.value;
/*  522 */           if ((first == tab[index]) && (oldValue != null)) {
/*  523 */             e.value = value;
/*  524 */             return oldValue;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  530 */       return sput(key, value, hash);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected Object sput(Object key, Object value, int hash)
/*      */   {
/*  540 */     Entry[] tab = this.table;
/*  541 */     int index = hash & tab.length - 1;
/*  542 */     Entry first = tab[index];
/*  543 */     Entry e = first;
/*      */     while (true)
/*      */     {
/*  546 */       if (e == null) {
/*  547 */         Entry newEntry = new Entry(hash, key, value, first);
/*  548 */         tab[index] = newEntry;
/*  549 */         if (++this.count >= this.threshold)
/*  550 */           rehash();
/*      */         else
/*  552 */           recordModification(newEntry);
/*  553 */         return null;
/*  554 */       }if ((e.hash == hash) && (eq(key, e.key))) {
/*  555 */         Object oldValue = e.value;
/*  556 */         e.value = value;
/*  557 */         return oldValue;
/*      */       }
/*  559 */       e = e.next;
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void rehash()
/*      */   {
/*  569 */     Entry[] oldTable = this.table;
/*  570 */     int oldCapacity = oldTable.length;
/*  571 */     if (oldCapacity >= 1073741824) {
/*  572 */       this.threshold = 2147483647;
/*  573 */       return;
/*      */     }
/*      */ 
/*  576 */     int newCapacity = oldCapacity << 1;
/*  577 */     int mask = newCapacity - 1;
/*  578 */     this.threshold = ((int)(newCapacity * this.loadFactor));
/*      */ 
/*  580 */     Entry[] newTable = new Entry[newCapacity];
/*      */ 
/*  593 */     for (int i = 0; i < oldCapacity; i++)
/*      */     {
/*  596 */       Entry e = oldTable[i];
/*      */ 
/*  598 */       if (e != null) {
/*  599 */         int idx = e.hash & mask;
/*  600 */         Entry next = e.next;
/*      */ 
/*  603 */         if (next == null) {
/*  604 */           newTable[idx] = e;
/*      */         }
/*      */         else
/*      */         {
/*  608 */           Entry lastRun = e;
/*  609 */           int lastIdx = idx;
/*  610 */           for (Entry last = next; last != null; last = last.next) {
/*  611 */             int k = last.hash & mask;
/*  612 */             if (k != lastIdx) {
/*  613 */               lastIdx = k;
/*  614 */               lastRun = last;
/*      */             }
/*      */           }
/*  617 */           newTable[lastIdx] = lastRun;
/*      */ 
/*  620 */           for (Entry p = e; p != lastRun; p = p.next) {
/*  621 */             int k = p.hash & mask;
/*  622 */             newTable[k] = new Entry(p.hash, p.key, p.value, newTable[k]);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  629 */     this.table = newTable;
/*  630 */     recordModification(newTable);
/*      */   }
/*      */ 
/*      */   public Object remove(Object key)
/*      */   {
/*  654 */     int hash = hash(key);
/*  655 */     Entry[] tab = this.table;
/*  656 */     int index = hash & tab.length - 1;
/*  657 */     Entry first = tab[index];
/*  658 */     Entry e = first;
/*      */ 
/*  660 */     for (e = first; (e != null) && (
/*  661 */       (e.hash != hash) || (!eq(key, e.key))); e = e.next);
/*  664 */     synchronized (this) {
/*  665 */       if (tab == this.table) {
/*  666 */         if (e == null) {
/*  667 */           if (first == tab[index])
/*  668 */             return null;
/*      */         } else {
/*  670 */           Object oldValue = e.value;
/*  671 */           if ((first == tab[index]) && (oldValue != null)) {
/*  672 */             e.value = null;
/*  673 */             this.count -= 1;
/*      */ 
/*  675 */             Entry head = e.next;
/*  676 */             for (Entry p = first; p != e; p = p.next) {
/*  677 */               head = new Entry(p.hash, p.key, p.value, head);
/*      */             }
/*  679 */             tab[index] = head;
/*  680 */             recordModification(head);
/*  681 */             return oldValue;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  687 */       return sremove(key, hash);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected Object sremove(Object key, int hash)
/*      */   {
/*  697 */     Entry[] tab = this.table;
/*  698 */     int index = hash & tab.length - 1;
/*  699 */     Entry first = tab[index];
/*      */ 
/*  701 */     for (Entry e = first; e != null; e = e.next) {
/*  702 */       if ((e.hash == hash) && (eq(key, e.key))) {
/*  703 */         Object oldValue = e.value;
/*  704 */         e.value = null;
/*  705 */         this.count -= 1;
/*  706 */         Entry head = e.next;
/*  707 */         for (Entry p = first; p != e; p = p.next) {
/*  708 */           head = new Entry(p.hash, p.key, p.value, head);
/*      */         }
/*  710 */         tab[index] = head;
/*  711 */         recordModification(head);
/*  712 */         return oldValue;
/*      */       }
/*      */     }
/*  715 */     return null;
/*      */   }
/*      */ 
/*      */   public boolean containsValue(Object value)
/*      */   {
/*  732 */     if (value == null) {
/*  733 */       throw new NullPointerException();
/*      */     }
/*  735 */     Entry[] tab = getTableForReading();
/*      */ 
/*  737 */     for (int i = 0; i < tab.length; i++) {
/*  738 */       for (Entry e = tab[i]; e != null; e = e.next) {
/*  739 */         if (value.equals(e.value))
/*  740 */           return true;
/*      */       }
/*      */     }
/*  743 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean contains(Object value)
/*      */   {
/*  767 */     return containsValue(value);
/*      */   }
/*      */ 
/*      */   public synchronized void putAll(Map t)
/*      */   {
/*  781 */     int n = t.size();
/*  782 */     if (n == 0) {
/*  783 */       return;
/*      */     }
/*      */ 
/*  788 */     while (n >= this.threshold) {
/*  789 */       rehash();
/*      */     }
/*  791 */     for (Iterator it = t.entrySet().iterator(); it.hasNext(); ) {
/*  792 */       Map.Entry entry = (Map.Entry)it.next();
/*  793 */       Object key = entry.getKey();
/*  794 */       Object value = entry.getValue();
/*  795 */       put(key, value);
/*      */     }
/*      */   }
/*      */ 
/*      */   public synchronized void clear()
/*      */   {
/*  803 */     Entry[] tab = this.table;
/*  804 */     for (int i = 0; i < tab.length; i++)
/*      */     {
/*  808 */       for (Entry e = tab[i]; e != null; e = e.next) {
/*  809 */         e.value = null;
/*      */       }
/*  811 */       tab[i] = null;
/*      */     }
/*  813 */     this.count = 0;
/*  814 */     recordModification(tab);
/*      */   }
/*      */ 
/*      */   public synchronized Object clone()
/*      */   {
/*      */     try
/*      */     {
/*  826 */       ConcurrentReaderHashMap t = (ConcurrentReaderHashMap)super.clone();
/*      */ 
/*  828 */       t.keySet = null;
/*  829 */       t.entrySet = null;
/*  830 */       t.values = null;
/*      */ 
/*  832 */       Entry[] tab = this.table;
/*  833 */       t.table = new Entry[tab.length];
/*  834 */       Entry[] ttab = t.table;
/*      */ 
/*  836 */       for (int i = 0; i < tab.length; i++) {
/*  837 */         Entry first = null;
/*  838 */         for (Entry e = tab[i]; e != null; e = e.next)
/*  839 */           first = new Entry(e.hash, e.key, e.value, first);
/*  840 */         ttab[i] = first;
/*      */       }
/*      */ 
/*  843 */       return t;
/*      */     } catch (CloneNotSupportedException e) {
/*      */     }
/*  846 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   public Set keySet()
/*      */   {
/*  871 */     Set ks = this.keySet;
/*  872 */     return this.keySet = new KeySet(null);
/*      */   }
/*      */ 
/*      */   public Collection values()
/*      */   {
/*  911 */     Collection vs = this.values;
/*  912 */     return this.values = new Values(null);
/*      */   }
/*      */ 
/*      */   public Set entrySet()
/*      */   {
/*  948 */     Set es = this.entrySet;
/*  949 */     return this.entrySet = new EntrySet(null);
/*      */   }
/*      */ 
/*      */   protected synchronized boolean findAndRemoveEntry(Map.Entry entry)
/*      */   {
/*  985 */     Object key = entry.getKey();
/*  986 */     Object v = get(key);
/*  987 */     if ((v != null) && (v.equals(entry.getValue()))) {
/*  988 */       remove(key);
/*  989 */       return true;
/*      */     }
/*  991 */     return false;
/*      */   }
/*      */ 
/*      */   public Enumeration keys()
/*      */   {
/* 1004 */     return new KeyIterator();
/*      */   }
/*      */ 
/*      */   public Enumeration elements()
/*      */   {
/* 1019 */     return new ValueIterator();
/*      */   }
/*      */ 
/*      */   private synchronized void writeObject(ObjectOutputStream s)
/*      */     throws IOException
/*      */   {
/* 1226 */     s.defaultWriteObject();
/*      */ 
/* 1229 */     s.writeInt(this.table.length);
/*      */ 
/* 1232 */     s.writeInt(this.count);
/*      */ 
/* 1235 */     for (int index = this.table.length - 1; index >= 0; index--) {
/* 1236 */       Entry entry = this.table[index];
/*      */ 
/* 1238 */       while (entry != null) {
/* 1239 */         s.writeObject(entry.key);
/* 1240 */         s.writeObject(entry.value);
/* 1241 */         entry = entry.next;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private synchronized void readObject(ObjectInputStream s)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1253 */     s.defaultReadObject();
/*      */ 
/* 1256 */     int numBuckets = s.readInt();
/* 1257 */     this.table = new Entry[numBuckets];
/*      */ 
/* 1260 */     int size = s.readInt();
/*      */ 
/* 1263 */     for (int i = 0; i < size; i++) {
/* 1264 */       Object key = s.readObject();
/* 1265 */       Object value = s.readObject();
/* 1266 */       put(key, value);
/*      */     }
/*      */   }
/*      */ 
/*      */   public synchronized int capacity()
/*      */   {
/* 1275 */     return this.table.length;
/*      */   }
/*      */ 
/*      */   public float loadFactor()
/*      */   {
/* 1282 */     return this.loadFactor;
/*      */   }
/*      */ 
/*      */   protected class ValueIterator extends ConcurrentReaderHashMap.HashIterator
/*      */   {
/*      */     protected ValueIterator()
/*      */     {
/* 1204 */       super();
/*      */     }
/* 1206 */     protected Object returnValueOfNext() { return this.currentValue; }
/*      */ 
/*      */   }
/*      */ 
/*      */   protected class KeyIterator extends ConcurrentReaderHashMap.HashIterator
/*      */   {
/*      */     protected KeyIterator()
/*      */     {
/* 1198 */       super();
/*      */     }
/* 1200 */     protected Object returnValueOfNext() { return this.currentKey; }
/*      */ 
/*      */   }
/*      */ 
/*      */   protected class HashIterator
/*      */     implements Iterator, Enumeration
/*      */   {
/*      */     protected final ConcurrentReaderHashMap.Entry[] tab;
/*      */     protected int index;
/* 1123 */     protected ConcurrentReaderHashMap.Entry entry = null;
/*      */     protected Object currentKey;
/*      */     protected Object currentValue;
/* 1129 */     protected ConcurrentReaderHashMap.Entry lastReturned = null;
/*      */ 
/*      */     protected HashIterator() {
/* 1132 */       this.tab = ConcurrentReaderHashMap.this.getTableForReading();
/* 1133 */       this.index = (this.tab.length - 1);
/*      */     }
/*      */ 
/*      */     public boolean hasMoreElements() {
/* 1137 */       return hasNext();
/*      */     }
/*      */ 
/*      */     public Object nextElement() {
/* 1141 */       return next();
/*      */     }
/*      */ 
/*      */     public boolean hasNext()
/*      */     {
/*      */       do
/*      */       {
/* 1154 */         if (this.entry != null) {
/* 1155 */           Object v = this.entry.value;
/* 1156 */           if (v != null) {
/* 1157 */             this.currentKey = this.entry.key;
/* 1158 */             this.currentValue = v;
/* 1159 */             return true;
/*      */           }
/* 1161 */           this.entry = this.entry.next;
/*      */         }
/*      */ 
/* 1164 */         while ((this.entry == null) && (this.index >= 0))
/* 1165 */           this.entry = this.tab[(this.index--)];
/*      */       }
/* 1167 */       while (this.entry != null);
/* 1168 */       this.currentKey = (this.currentValue = null);
/* 1169 */       return false;
/*      */     }
/*      */ 
/*      */     protected Object returnValueOfNext()
/*      */     {
/* 1175 */       return this.entry;
/*      */     }
/*      */ 
/*      */     public Object next() {
/* 1179 */       if ((this.currentKey == null) && (!hasNext())) {
/* 1180 */         throw new NoSuchElementException();
/*      */       }
/* 1182 */       Object result = returnValueOfNext();
/* 1183 */       this.lastReturned = this.entry;
/* 1184 */       this.currentKey = (this.currentValue = null);
/* 1185 */       this.entry = this.entry.next;
/* 1186 */       return result;
/*      */     }
/*      */ 
/*      */     public void remove() {
/* 1190 */       if (this.lastReturned == null)
/* 1191 */         throw new IllegalStateException();
/* 1192 */       ConcurrentReaderHashMap.this.remove(this.lastReturned.key);
/* 1193 */       this.lastReturned = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   protected static class Entry
/*      */     implements Map.Entry
/*      */   {
/*      */     protected final int hash;
/*      */     protected final Object key;
/*      */     protected final Entry next;
/*      */     protected volatile Object value;
/*      */ 
/*      */     Entry(int hash, Object key, Object value, Entry next)
/*      */     {
/* 1043 */       this.hash = hash;
/* 1044 */       this.key = key;
/* 1045 */       this.next = next;
/* 1046 */       this.value = value;
/*      */     }
/*      */ 
/*      */     public Object getKey()
/*      */     {
/* 1052 */       return this.key;
/*      */     }
/*      */ 
/*      */     public Object getValue()
/*      */     {
/* 1068 */       return this.value;
/*      */     }
/*      */ 
/*      */     public Object setValue(Object value)
/*      */     {
/* 1094 */       if (value == null)
/* 1095 */         throw new NullPointerException();
/* 1096 */       Object oldValue = this.value;
/* 1097 */       this.value = value;
/* 1098 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/* 1102 */       if (!(o instanceof Map.Entry))
/* 1103 */         return false;
/* 1104 */       Map.Entry e = (Map.Entry)o;
/* 1105 */       return (this.key.equals(e.getKey())) && (this.value.equals(e.getValue()));
/*      */     }
/*      */ 
/*      */     public int hashCode() {
/* 1109 */       return this.key.hashCode() ^ this.value.hashCode();
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1113 */       return this.key + "=" + this.value;
/*      */     }
/*      */   }
/*      */ 
/*      */   private class EntrySet extends AbstractSet
/*      */   {
/*      */     private EntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public Iterator iterator()
/*      */     {
/*  954 */       return new ConcurrentReaderHashMap.HashIterator(ConcurrentReaderHashMap.this);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  958 */       if (!(o instanceof Map.Entry))
/*  959 */         return false;
/*  960 */       Map.Entry entry = (Map.Entry)o;
/*  961 */       Object v = ConcurrentReaderHashMap.this.get(entry.getKey());
/*  962 */       return (v != null) && (v.equals(entry.getValue()));
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  966 */       if (!(o instanceof Map.Entry))
/*  967 */         return false;
/*  968 */       return ConcurrentReaderHashMap.this.findAndRemoveEntry((Map.Entry)o);
/*      */     }
/*      */ 
/*      */     public int size()
/*      */     {
/*  973 */       return ConcurrentReaderHashMap.this.size();
/*      */     }
/*      */ 
/*      */     public void clear() {
/*  977 */       ConcurrentReaderHashMap.this.clear();
/*      */     }
/*      */ 
/*      */     EntrySet(ConcurrentReaderHashMap.1 x1)
/*      */     {
/*  952 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   private class Values extends AbstractCollection
/*      */   {
/*      */     private Values()
/*      */     {
/*      */     }
/*      */ 
/*      */     public Iterator iterator()
/*      */     {
/*  917 */       return new ConcurrentReaderHashMap.ValueIterator(ConcurrentReaderHashMap.this);
/*      */     }
/*      */ 
/*      */     public int size() {
/*  921 */       return ConcurrentReaderHashMap.this.size();
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  925 */       return ConcurrentReaderHashMap.this.containsValue(o);
/*      */     }
/*      */ 
/*      */     public void clear() {
/*  929 */       ConcurrentReaderHashMap.this.clear();
/*      */     }
/*      */ 
/*      */     Values(ConcurrentReaderHashMap.1 x1)
/*      */     {
/*  915 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractSet
/*      */   {
/*      */     private KeySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public Iterator iterator()
/*      */     {
/*  877 */       return new ConcurrentReaderHashMap.KeyIterator(ConcurrentReaderHashMap.this);
/*      */     }
/*      */ 
/*      */     public int size() {
/*  881 */       return ConcurrentReaderHashMap.this.size();
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  885 */       return ConcurrentReaderHashMap.this.containsKey(o);
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  889 */       return ConcurrentReaderHashMap.this.remove(o) != null;
/*      */     }
/*      */ 
/*      */     public void clear() {
/*  893 */       ConcurrentReaderHashMap.this.clear();
/*      */     }
/*      */ 
/*      */     KeySet(ConcurrentReaderHashMap.1 x1)
/*      */     {
/*  875 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected static class BarrierLock
/*      */     implements Serializable
/*      */   {
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ConcurrentReaderHashMap
 * JD-Core Version:    0.6.2
 */