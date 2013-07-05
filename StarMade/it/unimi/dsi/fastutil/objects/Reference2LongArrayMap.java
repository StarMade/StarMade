/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.LongArraySet;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrays;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollections;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Reference2LongArrayMap<K> extends AbstractReference2LongMap<K>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private transient Object[] key;
/*     */   private transient long[] value;
/*     */   private int size;
/*     */ 
/*     */   public Reference2LongArrayMap(Object[] key, long[] value)
/*     */   {
/*  76 */     this.key = key;
/*  77 */     this.value = value;
/*  78 */     this.size = key.length;
/*  79 */     if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/*     */   }
/*     */ 
/*     */   public Reference2LongArrayMap()
/*     */   {
/*  84 */     this.key = ObjectArrays.EMPTY_ARRAY;
/*  85 */     this.value = LongArrays.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   public Reference2LongArrayMap(int capacity)
/*     */   {
/*  92 */     this.key = new Object[capacity];
/*  93 */     this.value = new long[capacity];
/*     */   }
/*     */ 
/*     */   public Reference2LongArrayMap(Reference2LongMap<K> m)
/*     */   {
/* 100 */     this(m.size());
/* 101 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Reference2LongArrayMap(Map<? extends K, ? extends Long> m)
/*     */   {
/* 108 */     this(m.size());
/* 109 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Reference2LongArrayMap(Object[] key, long[] value, int size)
/*     */   {
/* 120 */     this.key = key;
/* 121 */     this.value = value;
/* 122 */     this.size = size;
/* 123 */     if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/* 124 */     if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
/*     */   }
/*     */ 
/*     */   public Reference2LongMap.FastEntrySet<K> reference2LongEntrySet()
/*     */   {
/* 169 */     return new EntrySet(null);
/*     */   }
/*     */ 
/*     */   private int findKey(Object k) {
/* 173 */     Object[] key = this.key;
/* 174 */     for (int i = this.size; i-- != 0; ) if (key[i] == k) return i;
/* 175 */     return -1;
/*     */   }
/*     */ 
/*     */   public long getLong(Object k)
/*     */   {
/* 184 */     Object[] key = this.key;
/* 185 */     for (int i = this.size; i-- != 0; ) if (key[i] == k) return this.value[i];
/* 186 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public int size() {
/* 190 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 196 */     for (int i = this.size; i-- != 0; )
/*     */     {
/* 198 */       this.key[i] = null;
/*     */     }
/*     */ 
/* 205 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k)
/*     */   {
/* 210 */     return findKey(k) != -1;
/*     */   }
/*     */ 
/*     */   public boolean containsValue(long v)
/*     */   {
/* 216 */     for (int i = this.size; i-- != 0; ) if (this.value[i] == v) return true;
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 222 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public long put(K k, long v)
/*     */   {
/* 228 */     int oldKey = findKey(k);
/* 229 */     if (oldKey != -1) {
/* 230 */       long oldValue = this.value[oldKey];
/* 231 */       this.value[oldKey] = v;
/* 232 */       return oldValue;
/*     */     }
/* 234 */     if (this.size == this.key.length) {
/* 235 */       Object[] newKey = new Object[this.size == 0 ? 2 : this.size * 2];
/* 236 */       long[] newValue = new long[this.size == 0 ? 2 : this.size * 2];
/* 237 */       for (int i = this.size; i-- != 0; ) {
/* 238 */         newKey[i] = this.key[i];
/* 239 */         newValue[i] = this.value[i];
/*     */       }
/* 241 */       this.key = newKey;
/* 242 */       this.value = newValue;
/*     */     }
/* 244 */     this.key[this.size] = k;
/* 245 */     this.value[this.size] = v;
/* 246 */     this.size += 1;
/* 247 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public long removeLong(Object k)
/*     */   {
/* 258 */     int oldPos = findKey(k);
/* 259 */     if (oldPos == -1) return this.defRetValue;
/* 260 */     long oldValue = this.value[oldPos];
/* 261 */     int tail = this.size - oldPos - 1;
/* 262 */     for (int i = 0; i < tail; i++) {
/* 263 */       this.key[(oldPos + i)] = this.key[(oldPos + i + 1)];
/* 264 */       this.value[(oldPos + i)] = this.value[(oldPos + i + 1)];
/*     */     }
/* 266 */     this.size -= 1;
/*     */ 
/* 268 */     this.key[this.size] = null;
/*     */ 
/* 273 */     return oldValue;
/*     */   }
/*     */ 
/*     */   public ReferenceSet<K> keySet()
/*     */   {
/* 280 */     return new ReferenceArraySet(this.key, this.size);
/*     */   }
/*     */ 
/*     */   public LongCollection values()
/*     */   {
/* 285 */     return LongCollections.unmodifiable(new LongArraySet(this.value, this.size));
/*     */   }
/*     */ 
/*     */   public Reference2LongArrayMap<K> clone()
/*     */   {
/*     */     Reference2LongArrayMap c;
/*     */     try
/*     */     {
/* 300 */       c = (Reference2LongArrayMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 303 */       throw new InternalError();
/*     */     }
/* 305 */     c.key = ((Object[])this.key.clone());
/* 306 */     c.value = ((long[])this.value.clone());
/* 307 */     return c;
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 311 */     s.defaultWriteObject();
/* 312 */     for (int i = 0; i < this.size; i++) {
/* 313 */       s.writeObject(this.key[i]);
/* 314 */       s.writeLong(this.value[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
/*     */   {
/* 320 */     s.defaultReadObject();
/* 321 */     this.key = new Object[this.size];
/* 322 */     this.value = new long[this.size];
/* 323 */     for (int i = 0; i < this.size; i++) {
/* 324 */       this.key[i] = s.readObject();
/* 325 */       this.value[i] = s.readLong();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class EntrySet extends AbstractObjectSet<Reference2LongMap.Entry<K>>
/*     */     implements Reference2LongMap.FastEntrySet<K>
/*     */   {
/*     */     private EntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Reference2LongMap.Entry<K>> iterator()
/*     */     {
/* 129 */       return new AbstractObjectIterator() {
/* 130 */         int next = 0;
/*     */ 
/* 132 */         public boolean hasNext() { return this.next < Reference2LongArrayMap.this.size; }
/*     */ 
/*     */         public Reference2LongMap.Entry<K> next()
/*     */         {
/* 136 */           if (!hasNext()) throw new NoSuchElementException();
/* 137 */           return new AbstractReference2LongMap.BasicEntry(Reference2LongArrayMap.this.key[this.next], Reference2LongArrayMap.this.value[(this.next++)]);
/*     */         } } ;
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Reference2LongMap.Entry<K>> fastIterator() {
/* 142 */       return new AbstractObjectIterator() {
/* 143 */         int next = 0;
/* 144 */         final AbstractReference2LongMap.BasicEntry<K> entry = new AbstractReference2LongMap.BasicEntry(null, 0L);
/*     */ 
/* 146 */         public boolean hasNext() { return this.next < Reference2LongArrayMap.this.size; }
/*     */ 
/*     */         public Reference2LongMap.Entry<K> next()
/*     */         {
/* 150 */           if (!hasNext()) throw new NoSuchElementException();
/* 151 */           this.entry.key = Reference2LongArrayMap.this.key[this.next];
/* 152 */           this.entry.value = Reference2LongArrayMap.this.value[(this.next++)];
/* 153 */           return this.entry;
/*     */         } } ;
/*     */     }
/*     */ 
/*     */     public int size() {
/* 158 */       return Reference2LongArrayMap.this.size;
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 162 */       if (!(o instanceof Map.Entry)) return false;
/* 163 */       Map.Entry e = (Map.Entry)o;
/* 164 */       Object k = e.getKey();
/* 165 */       return (Reference2LongArrayMap.this.containsKey(k)) && (Reference2LongArrayMap.this.get(k).longValue() == ((Long)e.getValue()).longValue());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2LongArrayMap
 * JD-Core Version:    0.6.2
 */