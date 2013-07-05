/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
/*     */ import it.unimi.dsi.fastutil.IndirectPriorityQueue;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectArrayIndirectPriorityQueue<K> extends AbstractIndirectPriorityQueue<K>
/*     */   implements IndirectPriorityQueue<K>
/*     */ {
/*     */   protected K[] refArray;
/*  65 */   protected int[] array = IntArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected Comparator<? super K> c;
/*     */   protected int firstIndex;
/*     */   protected boolean firstIndexValid;
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, int capacity, Comparator<? super K> c)
/*     */   {
/*  81 */     if (capacity > 0) this.array = new int[capacity];
/*  82 */     this.refArray = refArray;
/*  83 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, int capacity)
/*     */   {
/*  91 */     this(refArray, capacity, null);
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, Comparator<? super K> c)
/*     */   {
/*  99 */     this(refArray, refArray.length, c);
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray)
/*     */   {
/* 105 */     this(refArray, refArray.length, null);
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, int[] a, int size, Comparator<? super K> c)
/*     */   {
/* 117 */     this(refArray, 0, c);
/* 118 */     this.array = a;
/* 119 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, int[] a, Comparator<? super K> c)
/*     */   {
/* 130 */     this(refArray, a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, int[] a, int size)
/*     */   {
/* 141 */     this(refArray, a, size, null);
/*     */   }
/*     */ 
/*     */   public ObjectArrayIndirectPriorityQueue(K[] refArray, int[] a)
/*     */   {
/* 153 */     this(refArray, a, a.length);
/*     */   }
/*     */ 
/*     */   private int findFirst()
/*     */   {
/* 161 */     if (this.firstIndexValid) return this.firstIndex;
/* 162 */     this.firstIndexValid = true;
/* 163 */     int i = this.size;
/* 164 */     i--; int firstIndex = i;
/* 165 */     Object first = this.refArray[this.array[firstIndex]];
/*     */ 
/* 167 */     for (this.c != null; i-- != 0; ) if (((Comparable)this.refArray[this.array[i]]).compareTo(first) < 0) first = this.refArray[this.array[(firstIndex = i)]];
/* 168 */     while (i-- != 0) if (this.c.compare(this.refArray[this.array[i]], first) < 0) first = this.refArray[this.array[(firstIndex = i)]];
/*     */ 
/* 170 */     return this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   private int findLast()
/*     */   {
/* 177 */     int i = this.size;
/* 178 */     i--; int lastIndex = i;
/* 179 */     Object last = this.refArray[this.array[lastIndex]];
/*     */ 
/* 181 */     for (this.c != null; i-- != 0; ) if (((Comparable)last).compareTo(this.refArray[this.array[i]]) < 0) last = this.refArray[this.array[(lastIndex = i)]];
/* 182 */     while (i-- != 0) if (this.c.compare(last, this.refArray[this.array[i]]) < 0) last = this.refArray[this.array[(lastIndex = i)]];
/*     */ 
/* 184 */     return lastIndex;
/*     */   }
/*     */ 
/*     */   protected final void ensureNonEmpty() {
/* 188 */     if (this.size == 0) throw new NoSuchElementException();
/*     */   }
/*     */ 
/*     */   protected void ensureElement(int index)
/*     */   {
/* 197 */     if (index < 0) throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
/* 198 */     if (index >= this.refArray.length) throw new IndexOutOfBoundsException("Index (" + index + ") is larger than or equal to reference array size (" + this.refArray.length + ")");
/*     */   }
/*     */ 
/*     */   public void enqueue(int x)
/*     */   {
/* 208 */     ensureElement(x);
/*     */ 
/* 210 */     if (this.size == this.array.length) this.array = IntArrays.grow(this.array, this.size + 1);
/* 211 */     if (this.firstIndexValid) {
/* 212 */       if (this.c == null) { if (((Comparable)this.refArray[x]).compareTo(this.refArray[this.array[this.firstIndex]]) < 0) this.firstIndex = this.size; 
/*     */       }
/* 213 */       else if (this.c.compare(this.refArray[x], this.refArray[this.array[this.firstIndex]]) < 0) this.firstIndex = this.size; 
/*     */     }
/*     */     else
/* 215 */       this.firstIndexValid = false;
/* 216 */     this.array[(this.size++)] = x;
/*     */   }
/*     */ 
/*     */   public int dequeue() {
/* 220 */     ensureNonEmpty();
/* 221 */     int firstIndex = findFirst();
/* 222 */     int result = this.array[firstIndex];
/* 223 */     if (--this.size != 0) System.arraycopy(this.array, firstIndex + 1, this.array, firstIndex, this.size - firstIndex);
/* 224 */     this.firstIndexValid = false;
/* 225 */     return result;
/*     */   }
/*     */ 
/*     */   public int first() {
/* 229 */     ensureNonEmpty();
/* 230 */     return this.array[findFirst()];
/*     */   }
/*     */ 
/*     */   public int last() {
/* 234 */     ensureNonEmpty();
/* 235 */     return this.array[findLast()];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 239 */     ensureNonEmpty();
/* 240 */     this.firstIndexValid = false;
/*     */   }
/*     */ 
/*     */   public void changed(int index)
/*     */   {
/* 250 */     ensureElement(index);
/* 251 */     if (index == this.firstIndex) this.firstIndexValid = false; 
/*     */   }
/*     */ 
/*     */   public void allChanged()
/*     */   {
/* 255 */     this.firstIndexValid = false;
/*     */   }
/*     */ 
/*     */   public boolean remove(int index) {
/* 259 */     ensureElement(index);
/* 260 */     int[] a = this.array;
/* 261 */     int i = this.size;
/* 262 */     while (i-- != 0) if (a[i] == index) break;
/* 263 */     if (i < 0) return false;
/* 264 */     this.firstIndexValid = false;
/* 265 */     if (--this.size != 0) System.arraycopy(a, i + 1, a, i, this.size - i);
/* 266 */     return true;
/*     */   }
/*     */ 
/*     */   public int front(int[] a) {
/* 270 */     Object top = this.refArray[this.array[findFirst()]];
/* 271 */     int i = this.size; int c = 0;
/* 272 */     while (i-- != 0) if (top.equals(this.refArray[this.array[i]])) a[(c++)] = this.array[i];
/* 273 */     return c;
/*     */   }
/*     */   public int size() {
/* 276 */     return this.size;
/*     */   }
/* 278 */   public void clear() { this.size = 0; this.firstIndexValid = false;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 284 */     this.array = IntArrays.trim(this.array, this.size);
/*     */   }
/*     */   public Comparator<? super K> comparator() {
/* 287 */     return this.c;
/*     */   }
/*     */   public String toString() {
/* 290 */     StringBuffer s = new StringBuffer();
/* 291 */     s.append("[");
/* 292 */     for (int i = 0; i < this.size; i++) {
/* 293 */       if (i != 0) s.append(", ");
/* 294 */       s.append(this.refArray[this.array[i]]);
/*     */     }
/* 296 */     s.append("]");
/* 297 */     return s.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayIndirectPriorityQueue
 * JD-Core Version:    0.6.2
 */