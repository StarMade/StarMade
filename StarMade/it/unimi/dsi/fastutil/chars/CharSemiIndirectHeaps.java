/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*     */ 
/*     */ public class CharSemiIndirectHeaps
/*     */ {
/*     */   public static int downHeap(char[] refArray, int[] heap, int size, int i, CharComparator c)
/*     */   {
/*  66 */     if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  67 */     int e = heap[i];
/*  68 */     char E = refArray[e];
/*     */ 
/*  70 */     if (c == null)
/*     */     {
/*     */       int child;
/*  71 */       while ((child = 2 * i + 1) < size) {
/*  72 */         if ((child + 1 < size) && (refArray[heap[(child + 1)]] < refArray[heap[child]])) child++;
/*  73 */         if (E <= refArray[heap[child]]) break;
/*  74 */         heap[i] = heap[child];
/*  75 */         i = child;
/*     */       }
/*     */     }
/*     */     int child;
/*  78 */     while ((child = 2 * i + 1) < size) {
/*  79 */       if ((child + 1 < size) && (c.compare(refArray[heap[(child + 1)]], refArray[heap[child]]) < 0)) child++;
/*  80 */       if (c.compare(E, refArray[heap[child]]) <= 0) break;
/*  81 */       heap[i] = heap[child];
/*  82 */       i = child;
/*     */     }
/*  84 */     heap[i] = e;
/*  85 */     return i;
/*     */   }
/*     */ 
/*     */   public static int upHeap(char[] refArray, int[] heap, int size, int i, CharComparator c)
/*     */   {
/*  98 */     if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  99 */     int e = heap[i];
/*     */ 
/* 101 */     char E = refArray[e];
/* 102 */     if (c == null)
/*     */     {
/*     */       int parent;
/* 103 */       while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 104 */         (refArray[heap[parent]] > E)) {
/* 105 */         heap[i] = heap[parent];
/* 106 */         i = parent;
/*     */       }
/*     */     }
/*     */     int parent;
/* 109 */     while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 110 */       (c.compare(refArray[heap[parent]], E) > 0)) {
/* 111 */       heap[i] = heap[parent];
/* 112 */       i = parent;
/*     */     }
/* 114 */     heap[i] = e;
/* 115 */     return i;
/*     */   }
/*     */ 
/*     */   public static void makeHeap(char[] refArray, int offset, int length, int[] heap, CharComparator c)
/*     */   {
/* 126 */     CharArrays.ensureOffsetLength(refArray, offset, length);
/* 127 */     if (heap.length < length) throw new IllegalArgumentException("The heap length (" + heap.length + ") is smaller than the number of elements (" + length + ")");
/* 128 */     int i = length;
/* 129 */     while (i-- != 0) heap[i] = (offset + i);
/* 130 */     i = length / 2;
/* 131 */     while (i-- != 0) downHeap(refArray, heap, length, i, c);
/*     */   }
/*     */ 
/*     */   public static int[] makeHeap(char[] refArray, int offset, int length, CharComparator c)
/*     */   {
/* 144 */     int[] heap = length <= 0 ? IntArrays.EMPTY_ARRAY : new int[length];
/* 145 */     makeHeap(refArray, offset, length, heap, c);
/* 146 */     return heap;
/*     */   }
/*     */ 
/*     */   public static void makeHeap(char[] refArray, int[] heap, int size, CharComparator c)
/*     */   {
/* 160 */     int i = size / 2;
/* 161 */     while (i-- != 0) downHeap(refArray, heap, size, i, c);
/*     */   }
/*     */ 
/*     */   public static int front(char[] refArray, int[] heap, int size, int[] a)
/*     */   {
/* 182 */     char top = refArray[heap[0]];
/* 183 */     int j = 0;
/* 184 */     int l = 0;
/* 185 */     int r = 1;
/* 186 */     int f = 0;
/* 187 */     for (int i = 0; i < r; i++) {
/* 188 */       if (i == f) {
/* 189 */         if (l >= r) break;
/* 190 */         f = (f << 1) + 1;
/* 191 */         i = l;
/* 192 */         l = -1;
/*     */       }
/* 194 */       if (top == refArray[heap[i]]) {
/* 195 */         a[(j++)] = heap[i];
/* 196 */         if (l == -1) l = i * 2 + 1;
/* 197 */         r = Math.min(size, i * 2 + 3);
/*     */       }
/*     */     }
/*     */ 
/* 201 */     return j;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSemiIndirectHeaps
 * JD-Core Version:    0.6.2
 */