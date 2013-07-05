/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ByteArrayPriorityQueue extends AbstractBytePriorityQueue
/*     */ {
/*  58 */   protected byte[] array = ByteArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected ByteComparator c;
/*     */   protected int firstIndex;
/*     */   protected boolean firstIndexValid;
/*     */ 
/*     */   public ByteArrayPriorityQueue(int capacity, ByteComparator c)
/*     */   {
/*  75 */     if (capacity > 0) this.array = new byte[capacity];
/*  76 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue(int capacity)
/*     */   {
/*  83 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue(ByteComparator c)
/*     */   {
/*  90 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue()
/*     */   {
/*  95 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue(byte[] a, int size, ByteComparator c)
/*     */   {
/* 106 */     this(c);
/* 107 */     this.array = a;
/* 108 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue(byte[] a, ByteComparator c)
/*     */   {
/* 118 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue(byte[] a, int size)
/*     */   {
/* 128 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public ByteArrayPriorityQueue(byte[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */ 
/*     */   private int findFirst()
/*     */   {
/* 145 */     if (this.firstIndexValid) return this.firstIndex;
/* 146 */     this.firstIndexValid = true;
/* 147 */     int i = this.size;
/* 148 */     i--; int firstIndex = i;
/* 149 */     byte first = this.array[firstIndex];
/*     */ 
/* 151 */     for (this.c != null; i-- != 0; ) if (this.array[i] < first) first = this.array[(firstIndex = i)];
/* 152 */     while (i-- != 0) if (this.c.compare(this.array[i], first) < 0) first = this.array[(firstIndex = i)];
/*     */ 
/* 154 */     return this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   private void ensureNonEmpty() {
/* 158 */     if (this.size == 0) throw new NoSuchElementException(); 
/*     */   }
/*     */ 
/*     */   public void enqueue(byte x)
/*     */   {
/* 162 */     if (this.size == this.array.length) this.array = ByteArrays.grow(this.array, this.size + 1);
/* 163 */     if (this.firstIndexValid) {
/* 164 */       if (this.c == null) { if (x < this.array[this.firstIndex]) this.firstIndex = this.size; 
/*     */       }
/* 165 */       else if (this.c.compare(x, this.array[this.firstIndex]) < 0) this.firstIndex = this.size; 
/*     */     }
/*     */     else
/* 167 */       this.firstIndexValid = false;
/* 168 */     this.array[(this.size++)] = x;
/*     */   }
/*     */ 
/*     */   public byte dequeueByte() {
/* 172 */     ensureNonEmpty();
/* 173 */     int first = findFirst();
/* 174 */     byte result = this.array[first];
/* 175 */     System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
/*     */ 
/* 179 */     this.firstIndexValid = false;
/* 180 */     return result;
/*     */   }
/*     */ 
/*     */   public byte firstByte() {
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
/*     */   public void clear()
/*     */   {
/* 199 */     this.size = 0;
/* 200 */     this.firstIndexValid = false;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 207 */     this.array = ByteArrays.trim(this.array, this.size);
/*     */   }
/*     */   public ByteComparator comparator() {
/* 210 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrayPriorityQueue
 * JD-Core Version:    0.6.2
 */