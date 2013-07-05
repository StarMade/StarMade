/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ final class FastIntMap<V>
/*     */   implements Iterable<Entry<V>>
/*     */ {
/*     */   private Entry[] table;
/*     */   private int size;
/*     */   private int mask;
/*     */   private int capacity;
/*     */   private int threshold;
/*     */ 
/*     */   FastIntMap()
/*     */   {
/*  32 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   FastIntMap(int initialCapacity)
/*     */   {
/*  37 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */ 
/*     */   FastIntMap(int initialCapacity, float loadFactor) {
/*  41 */     if (initialCapacity > 1073741824) throw new IllegalArgumentException("initialCapacity is too large.");
/*  42 */     if (initialCapacity < 0) throw new IllegalArgumentException("initialCapacity must be greater than zero.");
/*  43 */     if (loadFactor <= 0.0F) throw new IllegalArgumentException("initialCapacity must be greater than zero.");
/*  44 */     this.capacity = 1;
/*  45 */     while (this.capacity < initialCapacity)
/*  46 */       this.capacity <<= 1;
/*  47 */     this.threshold = ((int)(this.capacity * loadFactor));
/*  48 */     this.table = new Entry[this.capacity];
/*  49 */     this.mask = (this.capacity - 1);
/*     */   }
/*     */ 
/*     */   private int index(int key) {
/*  53 */     return index(key, this.mask);
/*     */   }
/*     */ 
/*     */   private static int index(int key, int mask) {
/*  57 */     return key & mask;
/*     */   }
/*     */ 
/*     */   public V put(int key, V value) {
/*  61 */     Entry[] table = this.table;
/*  62 */     int index = index(key);
/*     */ 
/*  65 */     for (Entry e = table[index]; e != null; e = e.next) {
/*  66 */       if (e.key == key) {
/*  67 */         Object oldValue = e.value;
/*  68 */         e.value = value;
/*  69 */         return oldValue;
/*     */       }
/*     */     }
/*  72 */     table[index] = new Entry(key, value, table[index]);
/*     */ 
/*  74 */     if (this.size++ >= this.threshold) {
/*  75 */       rehash(table);
/*     */     }
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   private void rehash(Entry<V>[] table) {
/*  81 */     int newCapacity = 2 * this.capacity;
/*  82 */     int newMask = newCapacity - 1;
/*     */ 
/*  84 */     Entry[] newTable = new Entry[newCapacity];
/*     */ 
/*  86 */     for (int i = 0; i < table.length; i++) {
/*  87 */       Entry e = table[i];
/*  88 */       if (e != null) {
/*     */         do {
/*  90 */           Entry next = e.next;
/*  91 */           int index = index(e.key, newMask);
/*  92 */           e.next = newTable[index];
/*  93 */           newTable[index] = e;
/*  94 */           e = next;
/*  95 */         }while (e != null);
/*     */       }
/*     */     }
/*  98 */     this.table = newTable;
/*  99 */     this.capacity = newCapacity;
/* 100 */     this.mask = newMask;
/* 101 */     this.threshold *= 2;
/*     */   }
/*     */ 
/*     */   public V get(int key) {
/* 105 */     int index = index(key);
/* 106 */     for (Entry e = this.table[index]; e != null; e = e.next)
/* 107 */       if (e.key == key) return e.value;
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean containsValue(Object value) {
/* 112 */     Entry[] table = this.table;
/* 113 */     for (int i = table.length - 1; i >= 0; i--)
/* 114 */       for (Entry e = table[i]; e != null; e = e.next)
/* 115 */         if (e.value.equals(value)) return true;
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(int key) {
/* 120 */     int index = index(key);
/* 121 */     for (Entry e = this.table[index]; e != null; e = e.next)
/* 122 */       if (e.key == key) return true;
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   public V remove(int key) {
/* 127 */     int index = index(key);
/*     */ 
/* 129 */     Entry prev = this.table[index];
/* 130 */     Entry e = prev;
/* 131 */     while (e != null) {
/* 132 */       Entry next = e.next;
/* 133 */       if (e.key == key) {
/* 134 */         this.size -= 1;
/* 135 */         if (prev == e)
/* 136 */           this.table[index] = next;
/*     */         else
/* 138 */           prev.next = next;
/* 139 */         return e.value;
/*     */       }
/* 141 */       prev = e;
/* 142 */       e = next;
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   public int size() {
/* 148 */     return this.size;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 152 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 156 */     Entry[] table = this.table;
/* 157 */     for (int index = table.length - 1; index >= 0; index--)
/* 158 */       table[index] = null;
/* 159 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public FastIntMap<V>.EntryIterator iterator() {
/* 163 */     return new EntryIterator();
/*     */   }
/*     */ 
/*     */   static final class Entry<T>
/*     */   {
/*     */     final int key;
/*     */     T value;
/*     */     Entry<T> next;
/*     */ 
/*     */     Entry(int key, T value, Entry<T> next)
/*     */     {
/* 223 */       this.key = key;
/* 224 */       this.value = value;
/* 225 */       this.next = next;
/*     */     }
/*     */ 
/*     */     public int getKey() {
/* 229 */       return this.key;
/*     */     }
/*     */ 
/*     */     public T getValue() {
/* 233 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */   public class EntryIterator
/*     */     implements Iterator<FastIntMap.Entry<V>>
/*     */   {
/*     */     private int nextIndex;
/*     */     private FastIntMap.Entry<V> current;
/*     */ 
/*     */     EntryIterator()
/*     */     {
/* 172 */       reset();
/*     */     }
/*     */ 
/*     */     public void reset() {
/* 176 */       this.current = null;
/*     */ 
/* 178 */       FastIntMap.Entry[] table = FastIntMap.this.table;
/*     */ 
/* 180 */       for (int i = table.length - 1; (i >= 0) && 
/* 181 */         (table[i] == null); i--);
/* 182 */       this.nextIndex = i;
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 186 */       if (this.nextIndex >= 0) return true;
/* 187 */       FastIntMap.Entry e = this.current;
/* 188 */       return (e != null) && (e.next != null);
/*     */     }
/*     */ 
/*     */     public FastIntMap.Entry<V> next()
/*     */     {
/* 193 */       FastIntMap.Entry e = this.current;
/* 194 */       if (e != null) {
/* 195 */         e = e.next;
/* 196 */         if (e != null) {
/* 197 */           this.current = e;
/* 198 */           return e;
/*     */         }
/*     */       }
/*     */ 
/* 202 */       FastIntMap.Entry[] table = FastIntMap.this.table;
/* 203 */       int i = this.nextIndex;
/* 204 */       e = this.current = table[i];
/*     */       while (true) { i--; if (i >= 0)
/* 206 */           if (table[i] != null) break; 
/*     */       }
/* 207 */       this.nextIndex = i;
/* 208 */       return e;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 212 */       FastIntMap.this.remove(this.current.key);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.FastIntMap
 * JD-Core Version:    0.6.2
 */