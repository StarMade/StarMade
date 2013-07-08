/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  14:    */class PriorityQSort
/*  15:    */  extends PriorityQ
/*  16:    */{
/*  17:    */  PriorityQHeap heap;
/*  18:    */  
/*  28:    */  Object[] keys;
/*  29:    */  
/*  39:    */  int[] order;
/*  40:    */  
/*  50:    */  int size;
/*  51:    */  
/*  61:    */  int max;
/*  62:    */  
/*  72:    */  boolean initialized;
/*  73:    */  
/*  83:    */  PriorityQ.Leq leq;
/*  84:    */  
/*  95:    */  PriorityQSort(PriorityQ.Leq leq)
/*  96:    */  {
/*  97: 97 */    this.heap = new PriorityQHeap(leq);
/*  98:    */    
/*  99: 99 */    this.keys = new Object[32];
/* 100:    */    
/* 101:101 */    this.size = 0;
/* 102:102 */    this.max = 32;
/* 103:103 */    this.initialized = false;
/* 104:104 */    this.leq = leq;
/* 105:    */  }
/* 106:    */  
/* 107:    */  void pqDeletePriorityQ()
/* 108:    */  {
/* 109:109 */    if (this.heap != null) this.heap.pqDeletePriorityQ();
/* 110:110 */    this.order = null;
/* 111:111 */    this.keys = null;
/* 112:    */  }
/* 113:    */  
/* 114:    */  private static boolean LT(PriorityQ.Leq leq, Object x, Object y) {
/* 115:115 */    return !PriorityQHeap.LEQ(leq, y, x);
/* 116:    */  }
/* 117:    */  
/* 118:    */  private static boolean GT(PriorityQ.Leq leq, Object x, Object y) {
/* 119:119 */    return !PriorityQHeap.LEQ(leq, x, y);
/* 120:    */  }
/* 121:    */  
/* 122:    */  private static void Swap(int[] array, int a, int b)
/* 123:    */  {
/* 124:124 */    int tmp = array[a];
/* 125:125 */    array[a] = array[b];
/* 126:126 */    array[b] = tmp;
/* 127:    */  }
/* 128:    */  
/* 138:    */  boolean pqInit()
/* 139:    */  {
/* 140:140 */    Stack[] stack = new Stack[50];
/* 141:141 */    for (int k = 0; k < stack.length; k++) {
/* 142:142 */      stack[k] = new Stack(null);
/* 143:    */    }
/* 144:144 */    int top = 0;
/* 145:    */    
/* 146:146 */    int seed = 2016473283;
/* 147:    */    
/* 151:151 */    this.order = new int[this.size + 1];
/* 152:    */    
/* 156:156 */    int p = 0;
/* 157:157 */    int r = this.size - 1;
/* 158:158 */    int piv = 0; for (int i = p; i <= r; i++)
/* 159:    */    {
/* 160:160 */      this.order[i] = piv;piv++;
/* 161:    */    }
/* 162:    */    
/* 168:166 */    stack[top].p = p;
/* 169:167 */    stack[top].r = r;
/* 170:168 */    top++;
/* 171:169 */    for (;;) { top--; if (top < 0) break;
/* 172:170 */      p = stack[top].p;
/* 173:171 */      r = stack[top].r;
/* 174:172 */      while (r > p + 10) {
/* 175:173 */        seed = Math.abs(seed * 1539415821 + 1);
/* 176:174 */        i = p + seed % (r - p + 1);
/* 177:175 */        piv = this.order[i];
/* 178:176 */        this.order[i] = this.order[p];
/* 179:177 */        this.order[p] = piv;
/* 180:178 */        i = p - 1;
/* 181:179 */        int j = r + 1;
/* 182:    */        do {
/* 183:    */          do {
/* 184:182 */            i++;
/* 185:183 */          } while (GT(this.leq, this.keys[this.order[i]], this.keys[piv]));
/* 186:    */          do {
/* 187:185 */            j--;
/* 188:186 */          } while (LT(this.leq, this.keys[this.order[j]], this.keys[piv]));
/* 189:187 */          Swap(this.order, i, j);
/* 190:188 */        } while (i < j);
/* 191:189 */        Swap(this.order, i, j);
/* 192:190 */        if (i - p < r - j) {
/* 193:191 */          stack[top].p = (j + 1);
/* 194:192 */          stack[top].r = r;
/* 195:193 */          top++;
/* 196:194 */          r = i - 1;
/* 197:    */        } else {
/* 198:196 */          stack[top].p = p;
/* 199:197 */          stack[top].r = (i - 1);
/* 200:198 */          top++;
/* 201:199 */          p = j + 1;
/* 202:    */        }
/* 203:    */      }
/* 204:    */      
/* 205:203 */      for (i = p + 1; i <= r; i++) {
/* 206:204 */        piv = this.order[i];
/* 207:205 */        for (int j = i; (j > p) && (LT(this.leq, this.keys[this.order[(j - 1)]], this.keys[piv])); j--) {
/* 208:206 */          this.order[j] = this.order[(j - 1)];
/* 209:    */        }
/* 210:208 */        this.order[j] = piv;
/* 211:    */      }
/* 212:    */    }
/* 213:211 */    this.max = this.size;
/* 214:212 */    this.initialized = true;
/* 215:213 */    this.heap.pqInit();
/* 216:    */    
/* 225:223 */    return true;
/* 226:    */  }
/* 227:    */  
/* 231:    */  int pqInsert(Object keyNew)
/* 232:    */  {
/* 233:231 */    if (this.initialized) {
/* 234:232 */      return this.heap.pqInsert(keyNew);
/* 235:    */    }
/* 236:234 */    int curr = this.size;
/* 237:235 */    if (++this.size >= this.max) {
/* 238:236 */      Object[] saveKey = this.keys;
/* 239:    */      
/* 241:239 */      this.max <<= 1;
/* 242:    */      
/* 243:241 */      Object[] pqKeys = new Object[this.max];
/* 244:242 */      System.arraycopy(this.keys, 0, pqKeys, 0, this.keys.length);
/* 245:243 */      this.keys = pqKeys;
/* 246:244 */      if (this.keys == null) {
/* 247:245 */        this.keys = saveKey;
/* 248:246 */        return 2147483647;
/* 249:    */      }
/* 250:    */    }
/* 251:249 */    assert (curr != 2147483647);
/* 252:250 */    this.keys[curr] = keyNew;
/* 253:    */    
/* 255:253 */    return -(curr + 1);
/* 256:    */  }
/* 257:    */  
/* 260:    */  Object pqExtractMin()
/* 261:    */  {
/* 262:260 */    if (this.size == 0) {
/* 263:261 */      return this.heap.pqExtractMin();
/* 264:    */    }
/* 265:263 */    Object sortMin = this.keys[this.order[(this.size - 1)]];
/* 266:264 */    if (!this.heap.pqIsEmpty()) {
/* 267:265 */      Object heapMin = this.heap.pqMinimum();
/* 268:266 */      if (LEQ(this.leq, heapMin, sortMin)) {
/* 269:267 */        return this.heap.pqExtractMin();
/* 270:    */      }
/* 271:    */    }
/* 272:    */    do {
/* 273:271 */      this.size -= 1;
/* 274:272 */    } while ((this.size > 0) && (this.keys[this.order[(this.size - 1)]] == null));
/* 275:273 */    return sortMin;
/* 276:    */  }
/* 277:    */  
/* 280:    */  Object pqMinimum()
/* 281:    */  {
/* 282:280 */    if (this.size == 0) {
/* 283:281 */      return this.heap.pqMinimum();
/* 284:    */    }
/* 285:283 */    Object sortMin = this.keys[this.order[(this.size - 1)]];
/* 286:284 */    if (!this.heap.pqIsEmpty()) {
/* 287:285 */      Object heapMin = this.heap.pqMinimum();
/* 288:286 */      if (PriorityQHeap.LEQ(this.leq, heapMin, sortMin)) {
/* 289:287 */        return heapMin;
/* 290:    */      }
/* 291:    */    }
/* 292:290 */    return sortMin;
/* 293:    */  }
/* 294:    */  
/* 295:    */  boolean pqIsEmpty()
/* 296:    */  {
/* 297:295 */    return (this.size == 0) && (this.heap.pqIsEmpty());
/* 298:    */  }
/* 299:    */  
/* 300:    */  void pqDelete(int curr)
/* 301:    */  {
/* 302:300 */    if (curr >= 0) {
/* 303:301 */      this.heap.pqDelete(curr);
/* 304:302 */      return;
/* 305:    */    }
/* 306:304 */    curr = -(curr + 1);
/* 307:305 */    assert ((curr < this.max) && (this.keys[curr] != null));
/* 308:    */    
/* 309:307 */    this.keys[curr] = null;
/* 310:308 */    while ((this.size > 0) && (this.keys[this.order[(this.size - 1)]] == null)) {
/* 311:309 */      this.size -= 1;
/* 312:    */    }
/* 313:    */  }
/* 314:    */  
/* 315:    */  private static class Stack
/* 316:    */  {
/* 317:    */    int p;
/* 318:    */    int r;
/* 319:    */  }
/* 320:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQSort
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */