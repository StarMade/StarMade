/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class PriorityQSort extends PriorityQ
/*     */ {
/*     */   PriorityQHeap heap;
/*     */   Object[] keys;
/*     */   int[] order;
/*     */   int size;
/*     */   int max;
/*     */   boolean initialized;
/*     */   PriorityQ.Leq leq;
/*     */ 
/*     */   PriorityQSort(PriorityQ.Leq leq)
/*     */   {
/*  97 */     this.heap = new PriorityQHeap(leq);
/*     */ 
/*  99 */     this.keys = new Object[32];
/*     */ 
/* 101 */     this.size = 0;
/* 102 */     this.max = 32;
/* 103 */     this.initialized = false;
/* 104 */     this.leq = leq;
/*     */   }
/*     */ 
/*     */   void pqDeletePriorityQ()
/*     */   {
/* 109 */     if (this.heap != null) this.heap.pqDeletePriorityQ();
/* 110 */     this.order = null;
/* 111 */     this.keys = null;
/*     */   }
/*     */ 
/*     */   private static boolean LT(PriorityQ.Leq leq, Object x, Object y) {
/* 115 */     return !PriorityQHeap.LEQ(leq, y, x);
/*     */   }
/*     */ 
/*     */   private static boolean GT(PriorityQ.Leq leq, Object x, Object y) {
/* 119 */     return !PriorityQHeap.LEQ(leq, x, y);
/*     */   }
/*     */ 
/*     */   private static void Swap(int[] array, int a, int b)
/*     */   {
/* 124 */     int tmp = array[a];
/* 125 */     array[a] = array[b];
/* 126 */     array[b] = tmp;
/*     */   }
/*     */ 
/*     */   boolean pqInit()
/*     */   {
/* 140 */     Stack[] stack = new Stack[50];
/* 141 */     for (int k = 0; k < stack.length; k++) {
/* 142 */       stack[k] = new Stack(null);
/*     */     }
/* 144 */     int top = 0;
/*     */ 
/* 146 */     int seed = 2016473283;
/*     */ 
/* 151 */     this.order = new int[this.size + 1];
/*     */ 
/* 156 */     int p = 0;
/* 157 */     int r = this.size - 1;
/* 158 */     int piv = 0; for (int i = p; i <= r; i++)
/*     */     {
/* 160 */       this.order[i] = piv;
/*     */ 
/* 158 */       piv++;
/*     */     }
/*     */ 
/* 166 */     stack[top].p = p;
/* 167 */     stack[top].r = r;
/* 168 */     top++;
/*     */     while (true) { top--; if (top < 0) break;
/* 170 */       p = stack[top].p;
/* 171 */       r = stack[top].r;
/* 172 */       while (r > p + 10) {
/* 173 */         seed = Math.abs(seed * 1539415821 + 1);
/* 174 */         i = p + seed % (r - p + 1);
/* 175 */         piv = this.order[i];
/* 176 */         this.order[i] = this.order[p];
/* 177 */         this.order[p] = piv;
/* 178 */         i = p - 1;
/* 179 */         int j = r + 1;
/*     */         do {
/*     */           do
/* 182 */             i++;
/* 183 */           while (GT(this.leq, this.keys[this.order[i]], this.keys[piv]));
/*     */           do
/* 185 */             j--;
/* 186 */           while (LT(this.leq, this.keys[this.order[j]], this.keys[piv]));
/* 187 */           Swap(this.order, i, j);
/* 188 */         }while (i < j);
/* 189 */         Swap(this.order, i, j);
/* 190 */         if (i - p < r - j) {
/* 191 */           stack[top].p = (j + 1);
/* 192 */           stack[top].r = r;
/* 193 */           top++;
/* 194 */           r = i - 1;
/*     */         } else {
/* 196 */           stack[top].p = p;
/* 197 */           stack[top].r = (i - 1);
/* 198 */           top++;
/* 199 */           p = j + 1;
/*     */         }
/*     */       }
/*     */ 
/* 203 */       for (i = p + 1; i <= r; i++) {
/* 204 */         piv = this.order[i];
/* 205 */         for (int j = i; (j > p) && (LT(this.leq, this.keys[this.order[(j - 1)]], this.keys[piv])); j--) {
/* 206 */           this.order[j] = this.order[(j - 1)];
/*     */         }
/* 208 */         this.order[j] = piv;
/*     */       }
/*     */     }
/* 211 */     this.max = this.size;
/* 212 */     this.initialized = true;
/* 213 */     this.heap.pqInit();
/*     */ 
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   int pqInsert(Object keyNew)
/*     */   {
/* 231 */     if (this.initialized) {
/* 232 */       return this.heap.pqInsert(keyNew);
/*     */     }
/* 234 */     int curr = this.size;
/* 235 */     if (++this.size >= this.max) {
/* 236 */       Object[] saveKey = this.keys;
/*     */ 
/* 239 */       this.max <<= 1;
/*     */ 
/* 241 */       Object[] pqKeys = new Object[this.max];
/* 242 */       System.arraycopy(this.keys, 0, pqKeys, 0, this.keys.length);
/* 243 */       this.keys = pqKeys;
/* 244 */       if (this.keys == null) {
/* 245 */         this.keys = saveKey;
/* 246 */         return 2147483647;
/*     */       }
/*     */     }
/* 249 */     assert (curr != 2147483647);
/* 250 */     this.keys[curr] = keyNew;
/*     */ 
/* 253 */     return -(curr + 1);
/*     */   }
/*     */ 
/*     */   Object pqExtractMin()
/*     */   {
/* 260 */     if (this.size == 0) {
/* 261 */       return this.heap.pqExtractMin();
/*     */     }
/* 263 */     Object sortMin = this.keys[this.order[(this.size - 1)]];
/* 264 */     if (!this.heap.pqIsEmpty()) {
/* 265 */       Object heapMin = this.heap.pqMinimum();
/* 266 */       if (LEQ(this.leq, heapMin, sortMin)) {
/* 267 */         return this.heap.pqExtractMin();
/*     */       }
/*     */     }
/*     */     do
/* 271 */       this.size -= 1;
/* 272 */     while ((this.size > 0) && (this.keys[this.order[(this.size - 1)]] == null));
/* 273 */     return sortMin;
/*     */   }
/*     */ 
/*     */   Object pqMinimum()
/*     */   {
/* 280 */     if (this.size == 0) {
/* 281 */       return this.heap.pqMinimum();
/*     */     }
/* 283 */     Object sortMin = this.keys[this.order[(this.size - 1)]];
/* 284 */     if (!this.heap.pqIsEmpty()) {
/* 285 */       Object heapMin = this.heap.pqMinimum();
/* 286 */       if (PriorityQHeap.LEQ(this.leq, heapMin, sortMin)) {
/* 287 */         return heapMin;
/*     */       }
/*     */     }
/* 290 */     return sortMin;
/*     */   }
/*     */ 
/*     */   boolean pqIsEmpty()
/*     */   {
/* 295 */     return (this.size == 0) && (this.heap.pqIsEmpty());
/*     */   }
/*     */ 
/*     */   void pqDelete(int curr)
/*     */   {
/* 300 */     if (curr >= 0) {
/* 301 */       this.heap.pqDelete(curr);
/* 302 */       return;
/*     */     }
/* 304 */     curr = -(curr + 1);
/* 305 */     assert ((curr < this.max) && (this.keys[curr] != null));
/*     */ 
/* 307 */     this.keys[curr] = null;
/* 308 */     while ((this.size > 0) && (this.keys[this.order[(this.size - 1)]] == null))
/* 309 */       this.size -= 1;
/*     */   }
/*     */ 
/*     */   private static class Stack
/*     */   {
/*     */     int p;
/*     */     int r;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQSort
 * JD-Core Version:    0.6.2
 */