/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  14:    */class PriorityQHeap
/*  15:    */  extends PriorityQ
/*  16:    */{
/*  17:    */  PriorityQ.PQnode[] nodes;
/*  18:    */  
/*  29:    */  PriorityQ.PQhandleElem[] handles;
/*  30:    */  
/*  41:    */  int size;
/*  42:    */  
/*  52:    */  int max;
/*  53:    */  
/*  63:    */  int freeList;
/*  64:    */  
/*  74:    */  boolean initialized;
/*  75:    */  
/*  85:    */  PriorityQ.Leq leq;
/*  86:    */  
/*  97:    */  PriorityQHeap(PriorityQ.Leq leq)
/*  98:    */  {
/*  99: 99 */    this.size = 0;
/* 100:100 */    this.max = 32;
/* 101:101 */    this.nodes = new PriorityQ.PQnode[33];
/* 102:102 */    for (int i = 0; i < this.nodes.length; i++) {
/* 103:103 */      this.nodes[i] = new PriorityQ.PQnode();
/* 104:    */    }
/* 105:105 */    this.handles = new PriorityQ.PQhandleElem[33];
/* 106:106 */    for (int i = 0; i < this.handles.length; i++) {
/* 107:107 */      this.handles[i] = new PriorityQ.PQhandleElem();
/* 108:    */    }
/* 109:109 */    this.initialized = false;
/* 110:110 */    this.freeList = 0;
/* 111:111 */    this.leq = leq;
/* 112:    */    
/* 113:113 */    this.nodes[1].handle = 1;
/* 114:114 */    this.handles[1].key = null;
/* 115:    */  }
/* 116:    */  
/* 117:    */  void pqDeletePriorityQ()
/* 118:    */  {
/* 119:119 */    this.handles = null;
/* 120:120 */    this.nodes = null;
/* 121:    */  }
/* 122:    */  
/* 123:    */  void FloatDown(int curr) {
/* 124:124 */    PriorityQ.PQnode[] n = this.nodes;
/* 125:125 */    PriorityQ.PQhandleElem[] h = this.handles;
/* 126:    */    
/* 129:129 */    int hCurr = n[curr].handle;
/* 130:    */    for (;;) {
/* 131:131 */      int child = curr << 1;
/* 132:132 */      if ((child < this.size) && (LEQ(this.leq, h[n[(child + 1)].handle].key, h[n[child].handle].key)))
/* 133:    */      {
/* 134:134 */        child++;
/* 135:    */      }
/* 136:    */      
/* 137:137 */      assert (child <= this.max);
/* 138:    */      
/* 139:139 */      int hChild = n[child].handle;
/* 140:140 */      if ((child > this.size) || (LEQ(this.leq, h[hCurr].key, h[hChild].key))) {
/* 141:141 */        n[curr].handle = hCurr;
/* 142:142 */        h[hCurr].node = curr;
/* 143:143 */        break;
/* 144:    */      }
/* 145:145 */      n[curr].handle = hChild;
/* 146:146 */      h[hChild].node = curr;
/* 147:147 */      curr = child;
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 151:    */  void FloatUp(int curr)
/* 152:    */  {
/* 153:153 */    PriorityQ.PQnode[] n = this.nodes;
/* 154:154 */    PriorityQ.PQhandleElem[] h = this.handles;
/* 155:    */    
/* 158:158 */    int hCurr = n[curr].handle;
/* 159:    */    for (;;) {
/* 160:160 */      int parent = curr >> 1;
/* 161:161 */      int hParent = n[parent].handle;
/* 162:162 */      if ((parent == 0) || (LEQ(this.leq, h[hParent].key, h[hCurr].key))) {
/* 163:163 */        n[curr].handle = hCurr;
/* 164:164 */        h[hCurr].node = curr;
/* 165:165 */        break;
/* 166:    */      }
/* 167:167 */      n[curr].handle = hParent;
/* 168:168 */      h[hParent].node = curr;
/* 169:169 */      curr = parent;
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 177:    */  boolean pqInit()
/* 178:    */  {
/* 179:179 */    for (int i = this.size; i >= 1; i--) {
/* 180:180 */      FloatDown(i);
/* 181:    */    }
/* 182:182 */    this.initialized = true;
/* 183:    */    
/* 184:184 */    return true;
/* 185:    */  }
/* 186:    */  
/* 191:    */  int pqInsert(Object keyNew)
/* 192:    */  {
/* 193:193 */    int curr = ++this.size;
/* 194:194 */    if (curr * 2 > this.max) {
/* 195:195 */      PriorityQ.PQnode[] saveNodes = this.nodes;
/* 196:196 */      PriorityQ.PQhandleElem[] saveHandles = this.handles;
/* 197:    */      
/* 199:199 */      this.max <<= 1;
/* 200:    */      
/* 201:201 */      PriorityQ.PQnode[] pqNodes = new PriorityQ.PQnode[this.max + 1];
/* 202:202 */      System.arraycopy(this.nodes, 0, pqNodes, 0, this.nodes.length);
/* 203:203 */      for (int i = this.nodes.length; i < pqNodes.length; i++) {
/* 204:204 */        pqNodes[i] = new PriorityQ.PQnode();
/* 205:    */      }
/* 206:206 */      this.nodes = pqNodes;
/* 207:207 */      if (this.nodes == null) {
/* 208:208 */        this.nodes = saveNodes;
/* 209:209 */        return 2147483647;
/* 210:    */      }
/* 211:    */      
/* 213:213 */      PriorityQ.PQhandleElem[] pqHandles = new PriorityQ.PQhandleElem[this.max + 1];
/* 214:214 */      System.arraycopy(this.handles, 0, pqHandles, 0, this.handles.length);
/* 215:215 */      for (int i = this.handles.length; i < pqHandles.length; i++) {
/* 216:216 */        pqHandles[i] = new PriorityQ.PQhandleElem();
/* 217:    */      }
/* 218:218 */      this.handles = pqHandles;
/* 219:219 */      if (this.handles == null) {
/* 220:220 */        this.handles = saveHandles;
/* 221:221 */        return 2147483647;
/* 222:    */      } }
/* 223:    */    int free;
/* 224:    */    int free;
/* 225:225 */    if (this.freeList == 0) {
/* 226:226 */      free = curr;
/* 227:    */    } else {
/* 228:228 */      free = this.freeList;
/* 229:229 */      this.freeList = this.handles[free].node;
/* 230:    */    }
/* 231:    */    
/* 232:232 */    this.nodes[curr].handle = free;
/* 233:233 */    this.handles[free].node = curr;
/* 234:234 */    this.handles[free].key = keyNew;
/* 235:    */    
/* 236:236 */    if (this.initialized) {
/* 237:237 */      FloatUp(curr);
/* 238:    */    }
/* 239:239 */    assert (free != 2147483647);
/* 240:240 */    return free;
/* 241:    */  }
/* 242:    */  
/* 243:    */  Object pqExtractMin()
/* 244:    */  {
/* 245:245 */    PriorityQ.PQnode[] n = this.nodes;
/* 246:246 */    PriorityQ.PQhandleElem[] h = this.handles;
/* 247:247 */    int hMin = n[1].handle;
/* 248:248 */    Object min = h[hMin].key;
/* 249:    */    
/* 250:250 */    if (this.size > 0) {
/* 251:251 */      n[1].handle = n[this.size].handle;
/* 252:252 */      h[n[1].handle].node = 1;
/* 253:    */      
/* 254:254 */      h[hMin].key = null;
/* 255:255 */      h[hMin].node = this.freeList;
/* 256:256 */      this.freeList = hMin;
/* 257:    */      
/* 258:258 */      if (--this.size > 0) {
/* 259:259 */        FloatDown(1);
/* 260:    */      }
/* 261:    */    }
/* 262:262 */    return min;
/* 263:    */  }
/* 264:    */  
/* 265:    */  void pqDelete(int hCurr)
/* 266:    */  {
/* 267:267 */    PriorityQ.PQnode[] n = this.nodes;
/* 268:268 */    PriorityQ.PQhandleElem[] h = this.handles;
/* 269:    */    
/* 271:271 */    assert ((hCurr >= 1) && (hCurr <= this.max) && (h[hCurr].key != null));
/* 272:    */    
/* 273:273 */    int curr = h[hCurr].node;
/* 274:274 */    n[curr].handle = n[this.size].handle;
/* 275:275 */    h[n[curr].handle].node = curr;
/* 276:    */    
/* 277:277 */    if (curr <= --this.size) {
/* 278:278 */      if ((curr <= 1) || (LEQ(this.leq, h[n[(curr >> 1)].handle].key, h[n[curr].handle].key))) {
/* 279:279 */        FloatDown(curr);
/* 280:    */      } else {
/* 281:281 */        FloatUp(curr);
/* 282:    */      }
/* 283:    */    }
/* 284:284 */    h[hCurr].key = null;
/* 285:285 */    h[hCurr].node = this.freeList;
/* 286:286 */    this.freeList = hCurr;
/* 287:    */  }
/* 288:    */  
/* 289:    */  Object pqMinimum() {
/* 290:290 */    return this.handles[this.nodes[1].handle].key;
/* 291:    */  }
/* 292:    */  
/* 293:    */  boolean pqIsEmpty() {
/* 294:294 */    return this.size == 0;
/* 295:    */  }
/* 296:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQHeap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */