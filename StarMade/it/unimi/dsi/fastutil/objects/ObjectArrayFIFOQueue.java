/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectArrayFIFOQueue<K> extends AbstractPriorityQueue<K>
/*     */ {
/*     */   public static final int INITIAL_CAPACITY = 16;
/*  59 */   protected K[] array = (Object[])ObjectArrays.EMPTY_ARRAY;
/*     */   protected int length;
/*     */   protected int start;
/*     */   protected int end;
/*     */ 
/*     */   public ObjectArrayFIFOQueue(int capacity)
/*     */   {
/*  74 */     if (capacity > 0) this.array = ((Object[])new Object[capacity]);
/*  75 */     this.length = this.array.length;
/*     */   }
/*     */ 
/*     */   public ObjectArrayFIFOQueue()
/*     */   {
/*  80 */     this(16);
/*     */   }
/*     */ 
/*     */   public Comparator<? super K> comparator()
/*     */   {
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */   public K dequeue() {
/*  91 */     if (this.start == this.end) throw new NoSuchElementException();
/*  92 */     Object t = this.array[this.start];
/*  93 */     this.array[this.start] = null;
/*  94 */     if (++this.start == this.length) this.start = 0;
/*  95 */     return t;
/*     */   }
/*     */ 
/*     */   public K dequeueLast()
/*     */   {
/* 103 */     if (this.start == this.end) throw new NoSuchElementException();
/* 104 */     if (this.end == 0) this.end = this.length;
/* 105 */     Object t = this.array[(--this.end)];
/* 106 */     this.array[this.end] = null;
/* 107 */     return t;
/*     */   }
/*     */   private final void expand() {
/* 110 */     Object[] newArray = ObjectArrays.grow(this.array, this.length + 1, 0);
/* 111 */     System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
/* 112 */     System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
/* 113 */     this.start = 0;
/* 114 */     this.end = this.length;
/* 115 */     this.length = (this.array = newArray).length;
/*     */   }
/*     */ 
/*     */   public void enqueue(K x)
/*     */   {
/* 120 */     this.array[(this.end++)] = x;
/* 121 */     if (this.end == this.length) this.end = 0;
/* 122 */     if (this.end == this.start) expand();
/*     */   }
/*     */ 
/*     */   public void enqueueFirst(K x)
/*     */   {
/* 128 */     if (this.start == 0) this.start = this.length;
/* 129 */     this.array[(--this.start)] = x;
/* 130 */     if (this.end == this.start) expand();
/*     */   }
/*     */ 
/*     */   public K first()
/*     */   {
/* 137 */     if (this.start == this.end) throw new NoSuchElementException();
/* 138 */     return this.array[this.start];
/*     */   }
/*     */ 
/*     */   public K last()
/*     */   {
/* 146 */     if (this.start == this.end) throw new NoSuchElementException();
/* 147 */     return this.array[(this.end - 1)];
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 153 */     if (this.start <= this.end) { ObjectArrays.fill(this.array, this.start, this.end, null);
/*     */     } else {
/* 155 */       ObjectArrays.fill(this.array, this.start, this.length, null);
/* 156 */       ObjectArrays.fill(this.array, 0, this.end, null);
/*     */     }
/*     */ 
/* 159 */     this.start = (this.end = 0);
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 165 */     int size = size();
/* 166 */     Object[] newArray = (Object[])new Object[size + 1];
/*     */ 
/* 172 */     if (this.start <= this.end) { System.arraycopy(this.array, this.start, newArray, 0, this.end - this.start);
/*     */     } else {
/* 174 */       System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
/* 175 */       System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
/*     */     }
/* 177 */     this.start = 0;
/* 178 */     this.length = ((this.end = size) + 1);
/* 179 */     this.array = newArray;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 184 */     int apparentLength = this.end - this.start;
/* 185 */     return apparentLength >= 0 ? apparentLength : this.length + apparentLength;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue
 * JD-Core Version:    0.6.2
 */