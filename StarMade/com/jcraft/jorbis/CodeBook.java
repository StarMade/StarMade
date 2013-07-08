/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */
/*  30:    */class CodeBook
/*  31:    */{
/*  32:    */  int dim;
/*  33:    */  int entries;
/*  34:    */  StaticCodeBook c;
/*  35:    */  float[] valuelist;
/*  36:    */  int[] codelist;
/*  37:    */  DecodeAux decode_tree;
/*  38:    */  private int[] t;
/*  39:    */  
/*  40:    */  int encode(int a, Buffer b)
/*  41:    */  {
/*  42: 42 */    b.write(this.codelist[a], this.c.lengthlist[a]);
/*  43: 43 */    return this.c.lengthlist[a];
/*  44:    */  }
/*  45:    */  
/*  60:    */  int errorv(float[] a)
/*  61:    */  {
/*  62: 62 */    int best = best(a, 1);
/*  63: 63 */    for (int k = 0; k < this.dim; k++) {
/*  64: 64 */      a[k] = this.valuelist[(best * this.dim + k)];
/*  65:    */    }
/*  66: 66 */    return best;
/*  67:    */  }
/*  68:    */  
/*  69:    */  int encodev(int best, float[] a, Buffer b)
/*  70:    */  {
/*  71: 71 */    for (int k = 0; k < this.dim; k++) {
/*  72: 72 */      a[k] = this.valuelist[(best * this.dim + k)];
/*  73:    */    }
/*  74: 74 */    return encode(best, b);
/*  75:    */  }
/*  76:    */  
/*  78:    */  int encodevs(float[] a, Buffer b, int step, int addmul)
/*  79:    */  {
/*  80: 80 */    int best = besterror(a, step, addmul);
/*  81: 81 */    return encode(best, b);
/*  82:    */  }
/*  83:    */  
/*  84:    */  CodeBook()
/*  85:    */  {
/*  86: 34 */    this.c = new StaticCodeBook();
/*  87:    */    
/* 136: 84 */    this.t = new int[15];
/* 137:    */  }
/* 138:    */  
/* 139: 87 */  synchronized int decodevs_add(float[] a, int offset, Buffer b, int n) { int step = n / this.dim;
/* 140:    */    
/* 143: 91 */    if (this.t.length < step) {
/* 144: 92 */      this.t = new int[step];
/* 145:    */    }
/* 146:    */    
/* 147: 95 */    for (int i = 0; i < step; i++) {
/* 148: 96 */      int entry = decode(b);
/* 149: 97 */      if (entry == -1)
/* 150: 98 */        return -1;
/* 151: 99 */      this.t[i] = (entry * this.dim);
/* 152:    */    }
/* 153:101 */    i = 0; for (int o = 0; i < this.dim; o += step) {
/* 154:102 */      for (int j = 0; j < step; j++) {
/* 155:103 */        a[(offset + o + j)] += this.valuelist[(this.t[j] + i)];
/* 156:    */      }
/* 157:101 */      i++;
/* 158:    */    }
/* 159:    */    
/* 163:107 */    return 0;
/* 164:    */  }
/* 165:    */  
/* 167:    */  int decodev_add(float[] a, int offset, Buffer b, int n)
/* 168:    */  {
/* 169:    */    int i;
/* 170:114 */    if (this.dim > 8) {
/* 171:115 */      for (i = 0; i < n;) {
/* 172:116 */        int entry = decode(b);
/* 173:117 */        if (entry == -1)
/* 174:118 */          return -1;
/* 175:119 */        t = entry * this.dim;
/* 176:120 */        for (j = 0; j < this.dim;)
/* 177:121 */          a[(offset + i++)] += this.valuelist[(t + j++)];
/* 178:    */      }
/* 179:    */    }
/* 180:    */    int t;
/* 181:    */    int j;
/* 182:126 */    for (int i = 0; i < n;) {
/* 183:127 */      int entry = decode(b);
/* 184:128 */      if (entry == -1)
/* 185:129 */        return -1;
/* 186:130 */      int t = entry * this.dim;
/* 187:131 */      int j = 0;
/* 188:132 */      switch (this.dim) {
/* 189:    */      case 8: 
/* 190:134 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 191:    */      case 7: 
/* 192:136 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 193:    */      case 6: 
/* 194:138 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 195:    */      case 5: 
/* 196:140 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 197:    */      case 4: 
/* 198:142 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 199:    */      case 3: 
/* 200:144 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 201:    */      case 2: 
/* 202:146 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 203:    */      case 1: 
/* 204:148 */        a[(offset + i++)] += this.valuelist[(t + j++)];
/* 205:    */      }
/* 206:    */      
/* 207:    */    }
/* 208:    */    
/* 210:154 */    return 0;
/* 211:    */  }
/* 212:    */  
/* 215:    */  int decodev_set(float[] a, int offset, Buffer b, int n)
/* 216:    */  {
/* 217:161 */    for (int i = 0; i < n;) {
/* 218:162 */      int entry = decode(b);
/* 219:163 */      if (entry == -1)
/* 220:164 */        return -1;
/* 221:165 */      t = entry * this.dim;
/* 222:166 */      for (j = 0; j < this.dim;)
/* 223:167 */        a[(offset + i++)] = this.valuelist[(t + j++)]; }
/* 224:    */    int t;
/* 225:    */    int j;
/* 226:170 */    return 0;
/* 227:    */  }
/* 228:    */  
/* 229:    */  int decodevv_add(float[][] a, int offset, int ch, Buffer b, int n)
/* 230:    */  {
/* 231:175 */    int chptr = 0;
/* 232:    */    
/* 233:177 */    for (int i = offset / ch; i < (offset + n) / ch;) {
/* 234:178 */      int entry = decode(b);
/* 235:179 */      if (entry == -1) {
/* 236:180 */        return -1;
/* 237:    */      }
/* 238:182 */      int t = entry * this.dim;
/* 239:183 */      for (int j = 0; j < this.dim; j++) {
/* 240:184 */        a[(chptr++)][i] += this.valuelist[(t + j)];
/* 241:185 */        if (chptr == ch) {
/* 242:186 */          chptr = 0;
/* 243:187 */          i++;
/* 244:    */        }
/* 245:    */      }
/* 246:    */    }
/* 247:191 */    return 0;
/* 248:    */  }
/* 249:    */  
/* 264:    */  int decode(Buffer b)
/* 265:    */  {
/* 266:210 */    int ptr = 0;
/* 267:211 */    DecodeAux t = this.decode_tree;
/* 268:212 */    int lok = b.look(t.tabn);
/* 269:    */    
/* 270:214 */    if (lok >= 0) {
/* 271:215 */      ptr = t.tab[lok];
/* 272:216 */      b.adv(t.tabl[lok]);
/* 273:217 */      if (ptr <= 0) {
/* 274:218 */        return -ptr;
/* 275:    */      }
/* 276:    */    }
/* 277:    */    do {
/* 278:222 */      switch (b.read1()) {
/* 279:    */      case 0: 
/* 280:224 */        ptr = t.ptr0[ptr];
/* 281:225 */        break;
/* 282:    */      case 1: 
/* 283:227 */        ptr = t.ptr1[ptr];
/* 284:228 */        break;
/* 285:    */      case -1: 
/* 286:    */      default: 
/* 287:231 */        return -1;
/* 288:    */      }
/* 289:    */      
/* 290:234 */    } while (ptr > 0);
/* 291:235 */    return -ptr;
/* 292:    */  }
/* 293:    */  
/* 294:    */  int decodevs(float[] a, int index, Buffer b, int step, int addmul)
/* 295:    */  {
/* 296:240 */    int entry = decode(b);
/* 297:241 */    if (entry == -1)
/* 298:242 */      return -1;
/* 299:243 */    switch (addmul) {
/* 300:    */    case -1: 
/* 301:245 */      int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 302:246 */        a[(index + o)] = this.valuelist[(entry * this.dim + i)];i++;
/* 303:    */      }
/* 304:247 */      break;
/* 305:    */    case 0: 
/* 306:249 */      int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 307:250 */        a[(index + o)] += this.valuelist[(entry * this.dim + i)];i++;
/* 308:    */      }
/* 309:251 */      break;
/* 310:    */    case 1: 
/* 311:253 */      int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 312:254 */        a[(index + o)] *= this.valuelist[(entry * this.dim + i)];i++;
/* 313:    */      }
/* 314:255 */      break;
/* 315:    */    }
/* 316:    */    
/* 317:    */    
/* 318:259 */    return entry;
/* 319:    */  }
/* 320:    */  
/* 322:    */  int best(float[] a, int step)
/* 323:    */  {
/* 324:265 */    int besti = -1;
/* 325:266 */    float best = 0.0F;
/* 326:267 */    int e = 0;
/* 327:268 */    for (int i = 0; i < this.entries; i++) {
/* 328:269 */      if (this.c.lengthlist[i] > 0) {
/* 329:270 */        float _this = dist(this.dim, this.valuelist, e, a, step);
/* 330:271 */        if ((besti == -1) || (_this < best)) {
/* 331:272 */          best = _this;
/* 332:273 */          besti = i;
/* 333:    */        }
/* 334:    */      }
/* 335:276 */      e += this.dim;
/* 336:    */    }
/* 337:278 */    return besti;
/* 338:    */  }
/* 339:    */  
/* 341:    */  int besterror(float[] a, int step, int addmul)
/* 342:    */  {
/* 343:284 */    int best = best(a, step);
/* 344:285 */    switch (addmul) {
/* 345:    */    case 0: 
/* 346:287 */      int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 347:288 */        a[o] -= this.valuelist[(best * this.dim + i)];i++;
/* 348:    */      }
/* 349:289 */      break;
/* 350:    */    case 1: 
/* 351:291 */      int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 352:292 */        float val = this.valuelist[(best * this.dim + i)];
/* 353:293 */        if (val == 0.0F) {
/* 354:294 */          a[o] = 0.0F;
/* 355:    */        }
/* 356:    */        else {
/* 357:297 */          a[o] /= val;
/* 358:    */        }
/* 359:291 */        i++;
/* 360:    */      }
/* 361:    */    }
/* 362:    */    
/* 363:    */    
/* 370:302 */    return best;
/* 371:    */  }
/* 372:    */  
/* 375:    */  private static float dist(int el, float[] ref, int index, float[] b, int step)
/* 376:    */  {
/* 377:309 */    float acc = 0.0F;
/* 378:310 */    for (int i = 0; i < el; i++) {
/* 379:311 */      float val = ref[(index + i)] - b[(i * step)];
/* 380:312 */      acc += val * val;
/* 381:    */    }
/* 382:314 */    return acc;
/* 383:    */  }
/* 384:    */  
/* 385:    */  int init_decode(StaticCodeBook s) {
/* 386:318 */    this.c = s;
/* 387:319 */    this.entries = s.entries;
/* 388:320 */    this.dim = s.dim;
/* 389:321 */    this.valuelist = s.unquantize();
/* 390:    */    
/* 391:323 */    this.decode_tree = make_decode_tree();
/* 392:324 */    if (this.decode_tree == null) {
/* 393:325 */      clear();
/* 394:326 */      return -1;
/* 395:    */    }
/* 396:328 */    return 0;
/* 397:    */  }
/* 398:    */  
/* 401:    */  static int[] make_words(int[] l, int n)
/* 402:    */  {
/* 403:335 */    int[] marker = new int[33];
/* 404:336 */    int[] r = new int[n];
/* 405:    */    
/* 406:338 */    for (int i = 0; i < n; i++) {
/* 407:339 */      int length = l[i];
/* 408:340 */      if (length > 0) {
/* 409:341 */        int entry = marker[length];
/* 410:    */        
/* 417:349 */        if ((length < 32) && (entry >>> length != 0))
/* 418:    */        {
/* 420:352 */          return null;
/* 421:    */        }
/* 422:354 */        r[i] = entry;
/* 423:    */        
/* 427:359 */        for (int j = length; j > 0; j--) {
/* 428:360 */          if ((marker[j] & 0x1) != 0)
/* 429:    */          {
/* 430:362 */            if (j == 1) {
/* 431:363 */              marker[1] += 1;break;
/* 432:    */            }
/* 433:365 */            marker[j] = (marker[(j - 1)] << 1);
/* 434:366 */            break;
/* 435:    */          }
/* 436:    */          
/* 437:369 */          marker[j] += 1;
/* 438:    */        }
/* 439:    */        
/* 444:376 */        for (int j = length + 1; j < 33; j++) {
/* 445:377 */          if (marker[j] >>> 1 != entry) break;
/* 446:378 */          entry = marker[j];
/* 447:379 */          marker[j] = (marker[(j - 1)] << 1);
/* 448:    */        }
/* 449:    */      }
/* 450:    */    }
/* 451:    */    
/* 458:390 */    for (int i = 0; i < n; i++) {
/* 459:391 */      int temp = 0;
/* 460:392 */      for (int j = 0; j < l[i]; j++) {
/* 461:393 */        temp <<= 1;
/* 462:394 */        temp |= r[i] >>> j & 0x1;
/* 463:    */      }
/* 464:396 */      r[i] = temp;
/* 465:    */    }
/* 466:    */    
/* 467:399 */    return r;
/* 468:    */  }
/* 469:    */  
/* 470:    */  DecodeAux make_decode_tree()
/* 471:    */  {
/* 472:404 */    int top = 0;
/* 473:405 */    DecodeAux t = new DecodeAux();
/* 474:406 */    int[] ptr0 = t.ptr0 = new int[this.entries * 2];
/* 475:407 */    int[] ptr1 = t.ptr1 = new int[this.entries * 2];
/* 476:408 */    int[] codelist = make_words(this.c.lengthlist, this.c.entries);
/* 477:    */    
/* 478:410 */    if (codelist == null)
/* 479:411 */      return null;
/* 480:412 */    t.aux = (this.entries * 2);
/* 481:    */    
/* 482:414 */    for (int i = 0; i < this.entries; i++) {
/* 483:415 */      if (this.c.lengthlist[i] > 0) {
/* 484:416 */        int ptr = 0;
/* 485:    */        
/* 486:418 */        for (int j = 0; j < this.c.lengthlist[i] - 1; j++) {
/* 487:419 */          int bit = codelist[i] >>> j & 0x1;
/* 488:420 */          if (bit == 0) {
/* 489:421 */            if (ptr0[ptr] == 0) {
/* 490:422 */              ptr0[ptr] = (++top);
/* 491:    */            }
/* 492:424 */            ptr = ptr0[ptr];
/* 493:    */          }
/* 494:    */          else {
/* 495:427 */            if (ptr1[ptr] == 0) {
/* 496:428 */              ptr1[ptr] = (++top);
/* 497:    */            }
/* 498:430 */            ptr = ptr1[ptr];
/* 499:    */          }
/* 500:    */        }
/* 501:    */        
/* 502:434 */        if ((codelist[i] >>> j & 0x1) == 0) {
/* 503:435 */          ptr0[ptr] = (-i);
/* 504:    */        }
/* 505:    */        else {
/* 506:438 */          ptr1[ptr] = (-i);
/* 507:    */        }
/* 508:    */      }
/* 509:    */    }
/* 510:    */    
/* 512:444 */    t.tabn = (Util.ilog(this.entries) - 4);
/* 513:    */    
/* 514:446 */    if (t.tabn < 5)
/* 515:447 */      t.tabn = 5;
/* 516:448 */    int n = 1 << t.tabn;
/* 517:449 */    t.tab = new int[n];
/* 518:450 */    t.tabl = new int[n];
/* 519:451 */    for (int i = 0; i < n; i++) {
/* 520:452 */      int p = 0;
/* 521:453 */      int j = 0;
/* 522:454 */      for (j = 0; (j < t.tabn) && ((p > 0) || (j == 0)); j++) {
/* 523:455 */        if ((i & 1 << j) != 0) {
/* 524:456 */          p = ptr1[p];
/* 525:    */        }
/* 526:    */        else {
/* 527:459 */          p = ptr0[p];
/* 528:    */        }
/* 529:    */      }
/* 530:462 */      t.tab[i] = p;
/* 531:463 */      t.tabl[i] = j;
/* 532:    */    }
/* 533:    */    
/* 534:466 */    return t;
/* 535:    */  }
/* 536:    */  
/* 537:    */  void clear() {}
/* 538:    */  
/* 539:    */  class DecodeAux
/* 540:    */  {
/* 541:    */    int[] tab;
/* 542:    */    int[] tabl;
/* 543:    */    int tabn;
/* 544:    */    int[] ptr0;
/* 545:    */    int[] ptr1;
/* 546:    */    int aux;
/* 547:    */    
/* 548:    */    DecodeAux() {}
/* 549:    */  }
/* 550:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.CodeBook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */