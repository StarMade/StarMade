/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArraySet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollections;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Int2ObjectArrayMap<V> extends AbstractInt2ObjectMap<V>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private transient int[] key;
/*     */   private transient Object[] value;
/*     */   private int size;
/*     */ 
/*     */   public Int2ObjectArrayMap(int[] key, Object[] value)
/*     */   {
/*  76 */     this.key = key;
/*  77 */     this.value = value;
/*  78 */     this.size = key.length;
/*  79 */     if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/*     */   }
/*     */ 
/*     */   public Int2ObjectArrayMap()
/*     */   {
/*  84 */     this.key = IntArrays.EMPTY_ARRAY;
/*  85 */     this.value = ObjectArrays.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   public Int2ObjectArrayMap(int capacity)
/*     */   {
/*  92 */     this.key = new int[capacity];
/*  93 */     this.value = new Object[capacity];
/*     */   }
/*     */ 
/*     */   public Int2ObjectArrayMap(Int2ObjectMap<V> m)
/*     */   {
/* 100 */     this(m.size());
/* 101 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Int2ObjectArrayMap(Map<? extends Integer, ? extends V> m)
/*     */   {
/* 108 */     this(m.size());
/* 109 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Int2ObjectArrayMap(int[] key, Object[] value, int size)
/*     */   {
/* 120 */     this.key = key;
/* 121 */     this.value = value;
/* 122 */     this.size = size;
/* 123 */     if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/* 124 */     if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
/*     */   }
/*     */ 
/*     */   public Int2ObjectMap.FastEntrySet<V> int2ObjectEntrySet()
/*     */   {
/* 169 */     return new EntrySet(null);
/*     */   }
/*     */ 
/*     */   private int findKey(int k) {
/* 173 */     int[] key = this.key;
/* 174 */     for (int i = this.size; i-- != 0; ) if (key[i] == k) return i;
/* 175 */     return -1;
/*     */   }
/*     */ 
/*     */   public V get(int k)
/*     */   {
/* 184 */     int[] key = this.key;
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
/* 201 */       this.value[i] = null;
/*     */     }
/*     */ 
/* 205 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(int k)
/*     */   {
/* 210 */     return findKey(k) != -1;
/*     */   }
/*     */ 
/*     */   public boolean containsValue(Object v)
/*     */   {
/* 216 */     for (int i = this.size; i-- != 0; return true) label5: if (this.value[i] == null ? v != null : !this.value[i].equals(v))
/*     */         break label5; return false;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 222 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public V put(int k, V v)
/*     */   {
/* 228 */     int oldKey = findKey(k);
/* 229 */     if (oldKey != -1) {
/* 230 */       Object oldValue = this.value[oldKey];
/* 231 */       this.value[oldKey] = v;
/* 232 */       return oldValue;
/*     */     }
/* 234 */     if (this.size == this.key.length) {
/* 235 */       int[] newKey = new int[this.size == 0 ? 2 : this.size * 2];
/* 236 */       Object[] newValue = new Object[this.size == 0 ? 2 : this.size * 2];
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
/*     */   public V remove(int k)
/*     */   {
/* 258 */     int oldPos = findKey(k);
/* 259 */     if (oldPos == -1) return this.defRetValue;
/* 260 */     Object oldValue = this.value[oldPos];
/* 261 */     int tail = this.size - oldPos - 1;
/* 262 */     for (int i = 0; i < tail; i++) {
/* 263 */       this.key[(oldPos + i)] = this.key[(oldPos + i + 1)];
/* 264 */       this.value[(oldPos + i)] = this.value[(oldPos + i + 1)];
/*     */     }
/* 266 */     this.size -= 1;
/*     */ 
/* 271 */     this.value[this.size] = null;
/*     */ 
/* 273 */     return oldValue;
/*     */   }
/*     */ 
/*     */   public IntSet keySet()
/*     */   {
/* 280 */     return new IntArraySet(this.key, this.size);
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 285 */     return ObjectCollections.unmodifiable(new ObjectArraySet(this.value, this.size));
/*     */   }
/*     */ 
/*     */   public Int2ObjectArrayMap<V> clone()
/*     */   {
/*     */     Int2ObjectArrayMap c;
/*     */     try
/*     */     {
/* 300 */       c = (Int2ObjectArrayMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 303 */       throw new InternalError();
/*     */     }
/* 305 */     c.key = ((int[])this.key.clone());
/* 306 */     c.value = ((Object[])this.value.clone());
/* 307 */     return c;
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 311 */     s.defaultWriteObject();
/* 312 */     for (int i = 0; i < this.size; i++) {
/* 313 */       s.writeInt(this.key[i]);
/* 314 */       s.writeObject(this.value[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
/*     */   {
/* 320 */     s.defaultReadObject();
/* 321 */     this.key = new int[this.size];
/* 322 */     this.value = new Object[this.size];
/* 323 */     for (int i = 0; i < this.size; i++) {
/* 324 */       this.key[i] = s.readInt();
/* 325 */       this.value[i] = s.readObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class EntrySet extends AbstractObjectSet<Int2ObjectMap.Entry<V>>
/*     */     implements Int2ObjectMap.FastEntrySet<V>
/*     */   {
/*     */     private EntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Int2ObjectMap.Entry<V>> iterator()
/*     */     {
/* 129 */       return new AbstractObjectIterator() {
/* 130 */         int next = 0;
/*     */ 
/* 132 */         public boolean hasNext() { return this.next < Int2ObjectArrayMap.this.size; }
/*     */ 
/*     */         public Int2ObjectMap.Entry<V> next()
/*     */         {
/* 136 */           if (!hasNext()) throw new NoSuchElementException();
/* 137 */           return new AbstractInt2ObjectMap.BasicEntry(Int2ObjectArrayMap.this.key[this.next], Int2ObjectArrayMap.this.value[(this.next++)]);
/*     */         } } ;
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Int2ObjectMap.Entry<V>> fastIterator() {
/* 142 */       return new AbstractObjectIterator() {
/* 143 */         int next = 0;
/* 144 */         final AbstractInt2ObjectMap.BasicEntry<V> entry = new AbstractInt2ObjectMap.BasicEntry(0, null);
/*     */ 
/* 146 */         public boolean hasNext() { return this.next < Int2ObjectArrayMap.this.size; }
/*     */ 
/*     */         public Int2ObjectMap.Entry<V> next()
/*     */         {
/* 150 */           if (!hasNext()) throw new NoSuchElementException();
/* 151 */           this.entry.key = Int2ObjectArrayMap.this.key[this.next];
/* 152 */           this.entry.value = Int2ObjectArrayMap.this.value[(this.next++)];
/* 153 */           return this.entry;
/*     */         } } ;
/*     */     }
/*     */ 
/*     */     public int size() {
/* 158 */       return Int2ObjectArrayMap.this.size;
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 162 */       if (!(o instanceof Map.Entry)) return false;
/* 163 */       Map.Entry e = (Map.Entry)o;
/* 164 */       int k = ((Integer)e.getKey()).intValue();
/* 165 */       return (Int2ObjectArrayMap.this.containsKey(k)) && (Int2ObjectArrayMap.this.get(k) == null ? e.getValue() == null : Int2ObjectArrayMap.this.get(k).equals(e.getValue()));
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap
 * JD-Core Version:    0.6.2
 */