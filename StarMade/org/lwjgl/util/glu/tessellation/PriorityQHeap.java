/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class PriorityQHeap extends PriorityQ
/*     */ {
/*     */   PriorityQ.PQnode[] nodes;
/*     */   PriorityQ.PQhandleElem[] handles;
/*     */   int size;
/*     */   int max;
/*     */   int freeList;
/*     */   boolean initialized;
/*     */   PriorityQ.Leq leq;
/*     */ 
/*     */   PriorityQHeap(PriorityQ.Leq leq)
/*     */   {
/*  99 */     this.size = 0;
/* 100 */     this.max = 32;
/* 101 */     this.nodes = new PriorityQ.PQnode[33];
/* 102 */     for (int i = 0; i < this.nodes.length; i++) {
/* 103 */       this.nodes[i] = new PriorityQ.PQnode();
/*     */     }
/* 105 */     this.handles = new PriorityQ.PQhandleElem[33];
/* 106 */     for (int i = 0; i < this.handles.length; i++) {
/* 107 */       this.handles[i] = new PriorityQ.PQhandleElem();
/*     */     }
/* 109 */     this.initialized = false;
/* 110 */     this.freeList = 0;
/* 111 */     this.leq = leq;
/*     */ 
/* 113 */     this.nodes[1].handle = 1;
/* 114 */     this.handles[1].key = null;
/*     */   }
/*     */ 
/*     */   void pqDeletePriorityQ()
/*     */   {
/* 119 */     this.handles = null;
/* 120 */     this.nodes = null;
/*     */   }
/*     */ 
/*     */   void FloatDown(int curr) {
/* 124 */     PriorityQ.PQnode[] n = this.nodes;
/* 125 */     PriorityQ.PQhandleElem[] h = this.handles;
/*     */ 
/* 129 */     int hCurr = n[curr].handle;
/*     */     while (true) {
/* 131 */       int child = curr << 1;
/* 132 */       if ((child < this.size) && (LEQ(this.leq, h[n[(child + 1)].handle].key, h[n[child].handle].key)))
/*     */       {
/* 134 */         child++;
/*     */       }
/*     */ 
/* 137 */       assert (child <= this.max);
/*     */ 
/* 139 */       int hChild = n[child].handle;
/* 140 */       if ((child > this.size) || (LEQ(this.leq, h[hCurr].key, h[hChild].key))) {
/* 141 */         n[curr].handle = hCurr;
/* 142 */         h[hCurr].node = curr;
/* 143 */         break;
/*     */       }
/* 145 */       n[curr].handle = hChild;
/* 146 */       h[hChild].node = curr;
/* 147 */       curr = child;
/*     */     }
/*     */   }
/*     */ 
/*     */   void FloatUp(int curr)
/*     */   {
/* 153 */     PriorityQ.PQnode[] n = this.nodes;
/* 154 */     PriorityQ.PQhandleElem[] h = this.handles;
/*     */ 
/* 158 */     int hCurr = n[curr].handle;
/*     */     while (true) {
/* 160 */       int parent = curr >> 1;
/* 161 */       int hParent = n[parent].handle;
/* 162 */       if ((parent == 0) || (LEQ(this.leq, h[hParent].key, h[hCurr].key))) {
/* 163 */         n[curr].handle = hCurr;
/* 164 */         h[hCurr].node = curr;
/* 165 */         break;
/*     */       }
/* 167 */       n[curr].handle = hParent;
/* 168 */       h[hParent].node = curr;
/* 169 */       curr = parent;
/*     */     }
/*     */   }
/*     */ 
/*     */   boolean pqInit()
/*     */   {
/* 179 */     for (int i = this.size; i >= 1; i--) {
/* 180 */       FloatDown(i);
/*     */     }
/* 182 */     this.initialized = true;
/*     */ 
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   int pqInsert(Object keyNew)
/*     */   {
/* 193 */     int curr = ++this.size;
/* 194 */     if (curr * 2 > this.max) {
/* 195 */       PriorityQ.PQnode[] saveNodes = this.nodes;
/* 196 */       PriorityQ.PQhandleElem[] saveHandles = this.handles;
/*     */ 
/* 199 */       this.max <<= 1;
/*     */ 
/* 201 */       PriorityQ.PQnode[] pqNodes = new PriorityQ.PQnode[this.max + 1];
/* 202 */       System.arraycopy(this.nodes, 0, pqNodes, 0, this.nodes.length);
/* 203 */       for (int i = this.nodes.length; i < pqNodes.length; i++) {
/* 204 */         pqNodes[i] = new PriorityQ.PQnode();
/*     */       }
/* 206 */       this.nodes = pqNodes;
/* 207 */       if (this.nodes == null) {
/* 208 */         this.nodes = saveNodes;
/* 209 */         return 2147483647;
/*     */       }
/*     */ 
/* 213 */       PriorityQ.PQhandleElem[] pqHandles = new PriorityQ.PQhandleElem[this.max + 1];
/* 214 */       System.arraycopy(this.handles, 0, pqHandles, 0, this.handles.length);
/* 215 */       for (int i = this.handles.length; i < pqHandles.length; i++) {
/* 216 */         pqHandles[i] = new PriorityQ.PQhandleElem();
/*     */       }
/* 218 */       this.handles = pqHandles;
/* 219 */       if (this.handles == null) {
/* 220 */         this.handles = saveHandles;
/* 221 */         return 2147483647;
/*     */       }
/*     */     }
/*     */     int free;
/*     */     int free;
/* 225 */     if (this.freeList == 0) {
/* 226 */       free = curr;
/*     */     } else {
/* 228 */       free = this.freeList;
/* 229 */       this.freeList = this.handles[free].node;
/*     */     }
/*     */ 
/* 232 */     this.nodes[curr].handle = free;
/* 233 */     this.handles[free].node = curr;
/* 234 */     this.handles[free].key = keyNew;
/*     */ 
/* 236 */     if (this.initialized) {
/* 237 */       FloatUp(curr);
/*     */     }
/* 239 */     assert (free != 2147483647);
/* 240 */     return free;
/*     */   }
/*     */ 
/*     */   Object pqExtractMin()
/*     */   {
/* 245 */     PriorityQ.PQnode[] n = this.nodes;
/* 246 */     PriorityQ.PQhandleElem[] h = this.handles;
/* 247 */     int hMin = n[1].handle;
/* 248 */     Object min = h[hMin].key;
/*     */ 
/* 250 */     if (this.size > 0) {
/* 251 */       n[1].handle = n[this.size].handle;
/* 252 */       h[n[1].handle].node = 1;
/*     */ 
/* 254 */       h[hMin].key = null;
/* 255 */       h[hMin].node = this.freeList;
/* 256 */       this.freeList = hMin;
/*     */ 
/* 258 */       if (--this.size > 0) {
/* 259 */         FloatDown(1);
/*     */       }
/*     */     }
/* 262 */     return min;
/*     */   }
/*     */ 
/*     */   void pqDelete(int hCurr)
/*     */   {
/* 267 */     PriorityQ.PQnode[] n = this.nodes;
/* 268 */     PriorityQ.PQhandleElem[] h = this.handles;
/*     */ 
/* 271 */     assert ((hCurr >= 1) && (hCurr <= this.max) && (h[hCurr].key != null));
/*     */ 
/* 273 */     int curr = h[hCurr].node;
/* 274 */     n[curr].handle = n[this.size].handle;
/* 275 */     h[n[curr].handle].node = curr;
/*     */ 
/* 277 */     if (curr <= --this.size) {
/* 278 */       if ((curr <= 1) || (LEQ(this.leq, h[n[(curr >> 1)].handle].key, h[n[curr].handle].key)))
/* 279 */         FloatDown(curr);
/*     */       else {
/* 281 */         FloatUp(curr);
/*     */       }
/*     */     }
/* 284 */     h[hCurr].key = null;
/* 285 */     h[hCurr].node = this.freeList;
/* 286 */     this.freeList = hCurr;
/*     */   }
/*     */ 
/*     */   Object pqMinimum() {
/* 290 */     return this.handles[this.nodes[1].handle].key;
/*     */   }
/*     */ 
/*     */   boolean pqIsEmpty() {
/* 294 */     return this.size == 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQHeap
 * JD-Core Version:    0.6.2
 */