/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ObjectArraySet<K> extends AbstractObjectSet<K>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private transient Object[] a;
/*     */   private int size;
/*     */ 
/*     */   public ObjectArraySet(Object[] a)
/*     */   {
/*  64 */     this.a = a;
/*  65 */     this.size = a.length;
/*     */   }
/*     */ 
/*     */   public ObjectArraySet()
/*     */   {
/*  70 */     this.a = ObjectArrays.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   public ObjectArraySet(int capacity)
/*     */   {
/*  77 */     this.a = new Object[capacity];
/*     */   }
/*     */ 
/*     */   public ObjectArraySet(ObjectCollection<K> c)
/*     */   {
/*  83 */     this(c.size());
/*  84 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectArraySet(Collection<? extends K> c)
/*     */   {
/*  90 */     this(c.size());
/*  91 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectArraySet(Object[] a, int size)
/*     */   {
/* 101 */     this.a = a;
/* 102 */     this.size = size;
/* 103 */     if (size > a.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + a.length + ")"); 
/*     */   }
/*     */ 
/* 106 */   private int findKey(Object o) { for (int i = this.size; i-- != 0; return i) label5: if (this.a[i] == null ? o != null : !this.a[i].equals(o))
/*     */         break label5; return -1;
/*     */   }
/*     */ 
/*     */   public ObjectIterator<K> iterator()
/*     */   {
/* 112 */     return ObjectIterators.wrap((Object[])this.a, 0, this.size);
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k) {
/* 116 */     return findKey(k) != -1;
/*     */   }
/*     */   public int size() {
/* 119 */     return this.size;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object k)
/*     */   {
/* 124 */     int pos = findKey(k);
/* 125 */     if (pos == -1) return false;
/* 126 */     int tail = this.size - pos - 1;
/* 127 */     for (int i = 0; i < tail; i++) this.a[(pos + i)] = this.a[(pos + i + 1)];
/* 128 */     this.size -= 1;
/* 129 */     this.a[this.size] = null;
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean add(K k) {
/* 134 */     int pos = findKey(k);
/* 135 */     if (pos != -1) return false;
/* 136 */     if (this.size == this.a.length) {
/* 137 */       Object[] b = new Object[this.size == 0 ? 2 : this.size * 2];
/* 138 */       for (int i = this.size; i-- != 0; b[i] = this.a[i]);
/* 139 */       this.a = b;
/*     */     }
/* 141 */     this.a[(this.size++)] = k;
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 146 */     for (int i = this.size; i-- != 0; this.a[i] = null);
/* 147 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 151 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public ObjectArraySet<K> clone()
/*     */   {
/*     */     ObjectArraySet c;
/*     */     try
/*     */     {
/* 166 */       c = (ObjectArraySet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 169 */       throw new InternalError();
/*     */     }
/* 171 */     c.a = ((Object[])this.a.clone());
/* 172 */     return c;
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 176 */     s.defaultWriteObject();
/* 177 */     for (int i = 0; i < this.size; i++) s.writeObject(this.a[i]);
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 183 */     s.defaultReadObject();
/* 184 */     this.a = new Object[this.size];
/* 185 */     for (int i = 0; i < this.size; i++) this.a[i] = s.readObject();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArraySet
 * JD-Core Version:    0.6.2
 */