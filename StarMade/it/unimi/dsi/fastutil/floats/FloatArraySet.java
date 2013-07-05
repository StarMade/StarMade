/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class FloatArraySet extends AbstractFloatSet
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private transient float[] a;
/*     */   private int size;
/*     */ 
/*     */   public FloatArraySet(float[] a)
/*     */   {
/*  65 */     this.a = a;
/*  66 */     this.size = a.length;
/*     */   }
/*     */ 
/*     */   public FloatArraySet()
/*     */   {
/*  71 */     this.a = FloatArrays.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   public FloatArraySet(int capacity)
/*     */   {
/*  78 */     this.a = new float[capacity];
/*     */   }
/*     */ 
/*     */   public FloatArraySet(FloatCollection c)
/*     */   {
/*  84 */     this(c.size());
/*  85 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public FloatArraySet(Collection<? extends Float> c)
/*     */   {
/*  91 */     this(c.size());
/*  92 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public FloatArraySet(float[] a, int size)
/*     */   {
/* 102 */     this.a = a;
/* 103 */     this.size = size;
/* 104 */     if (size > a.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + a.length + ")"); 
/*     */   }
/*     */ 
/* 107 */   private int findKey(float o) { for (int i = this.size; i-- != 0; ) if (this.a[i] == o) return i;
/* 108 */     return -1;
/*     */   }
/*     */ 
/*     */   public FloatIterator iterator()
/*     */   {
/* 113 */     return FloatIterators.wrap(this.a, 0, this.size);
/*     */   }
/*     */ 
/*     */   public boolean contains(float k) {
/* 117 */     return findKey(k) != -1;
/*     */   }
/*     */   public int size() {
/* 120 */     return this.size;
/*     */   }
/*     */ 
/*     */   public boolean remove(float k)
/*     */   {
/* 125 */     int pos = findKey(k);
/* 126 */     if (pos == -1) return false;
/* 127 */     int tail = this.size - pos - 1;
/* 128 */     for (int i = 0; i < tail; i++) this.a[(pos + i)] = this.a[(pos + i + 1)];
/* 129 */     this.size -= 1;
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean add(float k) {
/* 134 */     int pos = findKey(k);
/* 135 */     if (pos != -1) return false;
/* 136 */     if (this.size == this.a.length) {
/* 137 */       float[] b = new float[this.size == 0 ? 2 : this.size * 2];
/* 138 */       for (int i = this.size; i-- != 0; b[i] = this.a[i]);
/* 139 */       this.a = b;
/*     */     }
/* 141 */     this.a[(this.size++)] = k;
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 146 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 151 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public FloatArraySet clone()
/*     */   {
/*     */     FloatArraySet c;
/*     */     try
/*     */     {
/* 166 */       c = (FloatArraySet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 169 */       throw new InternalError();
/*     */     }
/* 171 */     c.a = ((float[])this.a.clone());
/* 172 */     return c;
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 176 */     s.defaultWriteObject();
/* 177 */     for (int i = 0; i < this.size; i++) s.writeFloat(this.a[i]);
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 183 */     s.defaultReadObject();
/* 184 */     this.a = new float[this.size];
/* 185 */     for (int i = 0; i < this.size; i++) this.a[i] = s.readFloat();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArraySet
 * JD-Core Version:    0.6.2
 */