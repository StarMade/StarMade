/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectArrayPriorityQueue<K> extends AbstractPriorityQueue<K>
/*     */ {
/*  59 */   protected K[] array = (Object[])ObjectArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected Comparator<? super K> c;
/*     */   protected int firstIndex;
/*     */   protected boolean firstIndexValid;
/*     */ 
/*     */   public ObjectArrayPriorityQueue(int capacity, Comparator<? super K> c)
/*     */   {
/*  76 */     if (capacity > 0) this.array = ((Object[])new Object[capacity]);
/*  77 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue(int capacity)
/*     */   {
/*  84 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue(Comparator<? super K> c)
/*     */   {
/*  91 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue()
/*     */   {
/*  96 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue(K[] a, int size, Comparator<? super K> c)
/*     */   {
/* 107 */     this(c);
/* 108 */     this.array = a;
/* 109 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue(K[] a, Comparator<? super K> c)
/*     */   {
/* 119 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue(K[] a, int size)
/*     */   {
/* 129 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public ObjectArrayPriorityQueue(K[] a)
/*     */   {
/* 138 */     this(a, a.length);
/*     */   }
/*     */ 
/*     */   private int findFirst()
/*     */   {
/* 145 */     if (this.firstIndexValid) return this.firstIndex;
/* 146 */     this.firstIndexValid = true;
/* 147 */     int i = this.size;
/* 148 */     i--; int firstIndex = i;
/* 149 */     Object first = this.array[firstIndex];
/*     */ 
/* 151 */     for (this.c != null; i-- != 0; ) if (((Comparable)this.array[i]).compareTo(first) < 0) first = this.array[(firstIndex = i)];
/* 152 */     while (i-- != 0) if (this.c.compare(this.array[i], first) < 0) first = this.array[(firstIndex = i)];
/*     */ 
/* 154 */     return this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   private void ensureNonEmpty() {
/* 158 */     if (this.size == 0) throw new NoSuchElementException(); 
/*     */   }
/*     */ 
/*     */   public void enqueue(K x)
/*     */   {
/* 162 */     if (this.size == this.array.length) this.array = ObjectArrays.grow(this.array, this.size + 1);
/* 163 */     if (this.firstIndexValid) {
/* 164 */       if (this.c == null) { if (((Comparable)x).compareTo(this.array[this.firstIndex]) < 0) this.firstIndex = this.size; 
/*     */       }
/* 165 */       else if (this.c.compare(x, this.array[this.firstIndex]) < 0) this.firstIndex = this.size; 
/*     */     }
/*     */     else
/* 167 */       this.firstIndexValid = false;
/* 168 */     this.array[(this.size++)] = x;
/*     */   }
/*     */ 
/*     */   public K dequeue() {
/* 172 */     ensureNonEmpty();
/* 173 */     int first = findFirst();
/* 174 */     Object result = this.array[first];
/* 175 */     System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
/*     */ 
/* 177 */     this.array[this.size] = null;
/*     */ 
/* 179 */     this.firstIndexValid = false;
/* 180 */     return result;
/*     */   }
/*     */ 
/*     */   public K first() {
/* 184 */     ensureNonEmpty();
/* 185 */     return this.array[findFirst()];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 189 */     ensureNonEmpty();
/* 190 */     this.firstIndexValid = false;
/*     */   }
/*     */   public int size() {
/* 193 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 197 */     ObjectArrays.fill(this.array, 0, this.size, null);
/*     */ 
/* 199 */     this.size = 0;
/* 200 */     this.firstIndexValid = false;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 207 */     this.array = ObjectArrays.trim(this.array, this.size);
/*     */   }
/*     */   public Comparator<? super K> comparator() {
/* 210 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue
 * JD-Core Version:    0.6.2
 */