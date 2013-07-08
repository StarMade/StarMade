/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */
/*  45:    */class StaticCodeBook
/*  46:    */{
/*  47:    */  int dim;
/*  48:    */  int entries;
/*  49:    */  int[] lengthlist;
/*  50:    */  int maptype;
/*  51:    */  int q_min;
/*  52:    */  int q_delta;
/*  53:    */  int q_quant;
/*  54:    */  int q_sequencep;
/*  55:    */  int[] quantlist;
/*  56:    */  static final int VQ_FEXP = 10;
/*  57:    */  static final int VQ_FMAN = 21;
/*  58:    */  static final int VQ_FEXP_BIAS = 768;
/*  59:    */  
/*  60:    */  int pack(Buffer opb)
/*  61:    */  {
/*  62: 62 */    boolean ordered = false;
/*  63:    */    
/*  64: 64 */    opb.write(5653314, 24);
/*  65: 65 */    opb.write(this.dim, 16);
/*  66: 66 */    opb.write(this.entries, 24);
/*  67:    */    
/*  71: 71 */    for (int i = 1; i < this.entries; i++) {
/*  72: 72 */      if (this.lengthlist[i] < this.lengthlist[(i - 1)])
/*  73:    */        break;
/*  74:    */    }
/*  75: 75 */    if (i == this.entries) {
/*  76: 76 */      ordered = true;
/*  77:    */    }
/*  78: 78 */    if (ordered)
/*  79:    */    {
/*  83: 83 */      int count = 0;
/*  84: 84 */      opb.write(1, 1);
/*  85: 85 */      opb.write(this.lengthlist[0] - 1, 5);
/*  86:    */      
/*  87: 87 */      for (i = 1; i < this.entries; i++) {
/*  88: 88 */        int _this = this.lengthlist[i];
/*  89: 89 */        int _last = this.lengthlist[(i - 1)];
/*  90: 90 */        if (_this > _last) {
/*  91: 91 */          for (int j = _last; j < _this; j++) {
/*  92: 92 */            opb.write(i - count, Util.ilog(this.entries - count));
/*  93: 93 */            count = i;
/*  94:    */          }
/*  95:    */        }
/*  96:    */      }
/*  97: 97 */      opb.write(i - count, Util.ilog(this.entries - count));
/*  99:    */    }
/* 100:    */    else
/* 101:    */    {
/* 102:102 */      opb.write(0, 1);
/* 103:    */      
/* 107:107 */      for (i = 0; i < this.entries; i++) {
/* 108:108 */        if (this.lengthlist[i] == 0) {
/* 109:    */          break;
/* 110:    */        }
/* 111:    */      }
/* 112:112 */      if (i == this.entries) {
/* 113:113 */        opb.write(0, 1);
/* 114:114 */        for (i = 0; i < this.entries; i++) {
/* 115:115 */          opb.write(this.lengthlist[i] - 1, 5);
/* 116:    */        }
/* 117:    */      }
/* 118:    */      
/* 119:119 */      opb.write(1, 1);
/* 120:120 */      for (i = 0; i < this.entries; i++) {
/* 121:121 */        if (this.lengthlist[i] == 0) {
/* 122:122 */          opb.write(0, 1);
/* 123:    */        }
/* 124:    */        else {
/* 125:125 */          opb.write(1, 1);
/* 126:126 */          opb.write(this.lengthlist[i] - 1, 5);
/* 127:    */        }
/* 128:    */      }
/* 129:    */    }
/* 130:    */    
/* 134:134 */    opb.write(this.maptype, 4);
/* 135:135 */    switch (this.maptype)
/* 136:    */    {
/* 137:    */    case 0: 
/* 138:138 */      break;
/* 139:    */    
/* 141:    */    case 1: 
/* 142:    */    case 2: 
/* 143:143 */      if (this.quantlist == null)
/* 144:    */      {
/* 145:145 */        return -1;
/* 146:    */      }
/* 147:    */      
/* 149:149 */      opb.write(this.q_min, 32);
/* 150:150 */      opb.write(this.q_delta, 32);
/* 151:151 */      opb.write(this.q_quant - 1, 4);
/* 152:152 */      opb.write(this.q_sequencep, 1);
/* 153:    */      
/* 155:155 */      int quantvals = 0;
/* 156:156 */      switch (this.maptype)
/* 157:    */      {
/* 159:    */      case 1: 
/* 160:160 */        quantvals = maptype1_quantvals();
/* 161:161 */        break;
/* 162:    */      
/* 163:    */      case 2: 
/* 164:164 */        quantvals = this.entries * this.dim;
/* 165:    */      }
/* 166:    */      
/* 167:    */      
/* 169:169 */      for (i = 0; i < quantvals; i++) {
/* 170:170 */        opb.write(Math.abs(this.quantlist[i]), this.q_quant);
/* 171:    */      }
/* 172:    */      
/* 173:173 */      break;
/* 174:    */    
/* 175:    */    default: 
/* 176:176 */      return -1;
/* 177:    */    }
/* 178:178 */    return 0;
/* 179:    */  }
/* 180:    */  
/* 186:    */  int unpack(Buffer opb)
/* 187:    */  {
/* 188:188 */    if (opb.read(24) != 5653314)
/* 189:    */    {
/* 190:190 */      clear();
/* 191:191 */      return -1;
/* 192:    */    }
/* 193:    */    
/* 195:195 */    this.dim = opb.read(16);
/* 196:196 */    this.entries = opb.read(24);
/* 197:197 */    if (this.entries == -1)
/* 198:    */    {
/* 199:199 */      clear();
/* 200:200 */      return -1;
/* 201:    */    }
/* 202:    */    
/* 203:    */    int i;
/* 204:204 */    switch (opb.read(1))
/* 205:    */    {
/* 206:    */    case 0: 
/* 207:207 */      this.lengthlist = new int[this.entries];
/* 208:    */      
/* 210:210 */      if (opb.read(1) != 0)
/* 211:    */      {
/* 213:213 */        for (int i = 0; i < this.entries; i++) {
/* 214:214 */          if (opb.read(1) != 0) {
/* 215:215 */            int num = opb.read(5);
/* 216:216 */            if (num == -1)
/* 217:    */            {
/* 218:218 */              clear();
/* 219:219 */              return -1;
/* 220:    */            }
/* 221:221 */            this.lengthlist[i] = (num + 1);
/* 222:    */          }
/* 223:    */          else {
/* 224:224 */            this.lengthlist[i] = 0;
/* 225:    */          }
/* 226:    */        }
/* 227:    */      }
/* 228:    */      
/* 230:230 */      for (i = 0; i < this.entries;) {
/* 231:231 */        int num = opb.read(5);
/* 232:232 */        if (num == -1)
/* 233:    */        {
/* 234:234 */          clear();
/* 235:235 */          return -1;
/* 236:    */        }
/* 237:237 */        this.lengthlist[i] = (num + 1);i++;continue;
/* 238:    */        
/* 244:244 */        int length = opb.read(5) + 1;
/* 245:245 */        this.lengthlist = new int[this.entries];
/* 246:    */        
/* 247:247 */        for (i = 0; i < this.entries;) {
/* 248:248 */          int num = opb.read(Util.ilog(this.entries - i));
/* 249:249 */          if (num == -1)
/* 250:    */          {
/* 251:251 */            clear();
/* 252:252 */            return -1;
/* 253:    */          }
/* 254:254 */          for (int j = 0; j < num; i++) {
/* 255:255 */            this.lengthlist[i] = length;j++;
/* 256:    */          }
/* 257:    */          
/* 258:257 */          length++;
/* 259:    */        }
/* 260:    */        
/* 261:260 */        break;
/* 262:    */        
/* 264:263 */        return -1;
/* 265:    */      }
/* 266:    */    }
/* 267:    */    
/* 268:267 */    switch (this.maptype = opb.read(4))
/* 269:    */    {
/* 270:    */    case 0: 
/* 271:270 */      break;
/* 272:    */    
/* 274:    */    case 1: 
/* 275:    */    case 2: 
/* 276:275 */      this.q_min = opb.read(32);
/* 277:276 */      this.q_delta = opb.read(32);
/* 278:277 */      this.q_quant = (opb.read(4) + 1);
/* 279:278 */      this.q_sequencep = opb.read(1);
/* 280:    */      
/* 282:281 */      int quantvals = 0;
/* 283:282 */      switch (this.maptype) {
/* 284:    */      case 1: 
/* 285:284 */        quantvals = maptype1_quantvals();
/* 286:285 */        break;
/* 287:    */      case 2: 
/* 288:287 */        quantvals = this.entries * this.dim;
/* 289:    */      }
/* 290:    */      
/* 291:    */      
/* 293:292 */      this.quantlist = new int[quantvals];
/* 294:293 */      for (i = 0; i < quantvals; i++) {
/* 295:294 */        this.quantlist[i] = opb.read(this.q_quant);
/* 296:    */      }
/* 297:296 */      if (this.quantlist[(quantvals - 1)] == -1)
/* 298:    */      {
/* 299:298 */        clear();
/* 300:299 */        return -1;
/* 301:    */      }
/* 302:    */      
/* 303:302 */      break;
/* 304:    */    
/* 305:    */    default: 
/* 306:305 */      clear();
/* 307:306 */      return -1;
/* 308:    */    }
/* 309:    */    
/* 310:309 */    return 0;
/* 311:    */  }
/* 312:    */  
/* 319:    */  private int maptype1_quantvals()
/* 320:    */  {
/* 321:320 */    int vals = (int)Math.floor(Math.pow(this.entries, 1.0D / this.dim));
/* 322:    */    
/* 327:    */    for (;;)
/* 328:    */    {
/* 329:328 */      int acc = 1;
/* 330:329 */      int acc1 = 1;
/* 331:330 */      for (int i = 0; i < this.dim; i++) {
/* 332:331 */        acc *= vals;
/* 333:332 */        acc1 *= (vals + 1);
/* 334:    */      }
/* 335:334 */      if ((acc <= this.entries) && (acc1 > this.entries)) {
/* 336:335 */        return vals;
/* 337:    */      }
/* 338:    */      
/* 339:338 */      if (acc > this.entries) {
/* 340:339 */        vals--;
/* 341:    */      }
/* 342:    */      else {
/* 343:342 */        vals++;
/* 344:    */      }
/* 345:    */    }
/* 346:    */  }
/* 347:    */  
/* 352:    */  void clear() {}
/* 353:    */  
/* 357:    */  float[] unquantize()
/* 358:    */  {
/* 359:358 */    if ((this.maptype == 1) || (this.maptype == 2))
/* 360:    */    {
/* 361:360 */      float mindel = float32_unpack(this.q_min);
/* 362:361 */      float delta = float32_unpack(this.q_delta);
/* 363:362 */      float[] r = new float[this.entries * this.dim];
/* 364:    */      
/* 367:366 */      switch (this.maptype)
/* 368:    */      {
/* 374:    */      case 1: 
/* 375:374 */        int quantvals = maptype1_quantvals();
/* 376:375 */        for (int j = 0; j < this.entries; j++) {
/* 377:376 */          float last = 0.0F;
/* 378:377 */          int indexdiv = 1;
/* 379:378 */          for (int k = 0; k < this.dim; k++) {
/* 380:379 */            int index = j / indexdiv % quantvals;
/* 381:380 */            float val = this.quantlist[index];
/* 382:381 */            val = Math.abs(val) * delta + mindel + last;
/* 383:382 */            if (this.q_sequencep != 0)
/* 384:383 */              last = val;
/* 385:384 */            r[(j * this.dim + k)] = val;
/* 386:385 */            indexdiv *= quantvals;
/* 387:    */          }
/* 388:    */        }
/* 389:388 */        break;
/* 390:    */      case 2: 
/* 391:390 */        for (int j = 0; j < this.entries; j++) {
/* 392:391 */          float last = 0.0F;
/* 393:392 */          for (int k = 0; k < this.dim; k++) {
/* 394:393 */            float val = this.quantlist[(j * this.dim + k)];
/* 395:    */            
/* 396:395 */            val = Math.abs(val) * delta + mindel + last;
/* 397:396 */            if (this.q_sequencep != 0)
/* 398:397 */              last = val;
/* 399:398 */            r[(j * this.dim + k)] = val;
/* 400:    */          }
/* 401:    */        }
/* 402:    */      }
/* 403:    */      
/* 404:    */      
/* 405:404 */      return r;
/* 406:    */    }
/* 407:406 */    return null;
/* 408:    */  }
/* 409:    */  
/* 418:    */  static long float32_pack(float val)
/* 419:    */  {
/* 420:419 */    int sign = 0;
/* 421:    */    
/* 423:422 */    if (val < 0.0F) {
/* 424:423 */      sign = -2147483648;
/* 425:424 */      val = -val;
/* 426:    */    }
/* 427:426 */    int exp = (int)Math.floor(Math.log(val) / Math.log(2.0D));
/* 428:427 */    int mant = (int)Math.rint(Math.pow(val, 20 - exp));
/* 429:428 */    exp = exp + 768 << 21;
/* 430:429 */    return sign | exp | mant;
/* 431:    */  }
/* 432:    */  
/* 433:    */  static float float32_unpack(int val) {
/* 434:433 */    float mant = val & 0x1FFFFF;
/* 435:434 */    float exp = (val & 0x7FE00000) >>> 21;
/* 436:435 */    if ((val & 0x80000000) != 0)
/* 437:436 */      mant = -mant;
/* 438:437 */    return ldexp(mant, (int)exp - 20 - 768);
/* 439:    */  }
/* 440:    */  
/* 441:    */  static float ldexp(float foo, int e) {
/* 442:441 */    return (float)(foo * Math.pow(2.0D, e));
/* 443:    */  }
/* 444:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.StaticCodeBook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */