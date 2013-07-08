/*   1:    */package com.jcraft.jogg;
/*   2:    */
/*   6:    */public class StreamState
/*   7:    */{
/*   8:    */  byte[] body_data;
/*   9:    */  
/*  12:    */  int body_storage;
/*  13:    */  
/*  16:    */  int body_fill;
/*  17:    */  
/*  20:    */  private int body_returned;
/*  21:    */  
/*  24:    */  int[] lacing_vals;
/*  25:    */  
/*  28:    */  long[] granule_vals;
/*  29:    */  
/*  32:    */  int lacing_storage;
/*  33:    */  
/*  35:    */  int lacing_fill;
/*  36:    */  
/*  38:    */  int lacing_packet;
/*  39:    */  
/*  41:    */  int lacing_returned;
/*  42:    */  
/*  44: 44 */  byte[] header = new byte[282];
/*  45:    */  
/*  46:    */  int header_fill;
/*  47:    */  
/*  48:    */  public int e_o_s;
/*  49:    */  
/*  50:    */  int b_o_s;
/*  51:    */  
/*  52:    */  int serialno;
/*  53:    */  
/*  54:    */  int pageno;
/*  55:    */  
/*  56:    */  long packetno;
/*  57:    */  long granulepos;
/*  58:    */  
/*  59:    */  public StreamState()
/*  60:    */  {
/*  61: 61 */    init();
/*  62:    */  }
/*  63:    */  
/*  64:    */  StreamState(int serialno) {
/*  65: 65 */    this();
/*  66: 66 */    init(serialno);
/*  67:    */  }
/*  68:    */  
/*  69:    */  void init() {
/*  70: 70 */    this.body_storage = 16384;
/*  71: 71 */    this.body_data = new byte[this.body_storage];
/*  72: 72 */    this.lacing_storage = 1024;
/*  73: 73 */    this.lacing_vals = new int[this.lacing_storage];
/*  74: 74 */    this.granule_vals = new long[this.lacing_storage];
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void init(int serialno) {
/*  78: 78 */    if (this.body_data == null) {
/*  79: 79 */      init();
/*  80:    */    }
/*  81:    */    else {
/*  82: 82 */      for (int i = 0; i < this.body_data.length; i++)
/*  83: 83 */        this.body_data[i] = 0;
/*  84: 84 */      for (int i = 0; i < this.lacing_vals.length; i++)
/*  85: 85 */        this.lacing_vals[i] = 0;
/*  86: 86 */      for (int i = 0; i < this.granule_vals.length; i++)
/*  87: 87 */        this.granule_vals[i] = 0L;
/*  88:    */    }
/*  89: 89 */    this.serialno = serialno;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public void clear() {
/*  93: 93 */    this.body_data = null;
/*  94: 94 */    this.lacing_vals = null;
/*  95: 95 */    this.granule_vals = null;
/*  96:    */  }
/*  97:    */  
/*  98:    */  void destroy() {
/*  99: 99 */    clear();
/* 100:    */  }
/* 101:    */  
/* 102:    */  void body_expand(int needed) {
/* 103:103 */    if (this.body_storage <= this.body_fill + needed) {
/* 104:104 */      this.body_storage += needed + 1024;
/* 105:105 */      byte[] foo = new byte[this.body_storage];
/* 106:106 */      System.arraycopy(this.body_data, 0, foo, 0, this.body_data.length);
/* 107:107 */      this.body_data = foo;
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:    */  void lacing_expand(int needed) {
/* 112:112 */    if (this.lacing_storage <= this.lacing_fill + needed) {
/* 113:113 */      this.lacing_storage += needed + 32;
/* 114:114 */      int[] foo = new int[this.lacing_storage];
/* 115:115 */      System.arraycopy(this.lacing_vals, 0, foo, 0, this.lacing_vals.length);
/* 116:116 */      this.lacing_vals = foo;
/* 117:    */      
/* 118:118 */      long[] bar = new long[this.lacing_storage];
/* 119:119 */      System.arraycopy(this.granule_vals, 0, bar, 0, this.granule_vals.length);
/* 120:120 */      this.granule_vals = bar;
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 124:    */  public int packetin(Packet op)
/* 125:    */  {
/* 126:126 */    int lacing_val = op.bytes / 255 + 1;
/* 127:    */    
/* 128:128 */    if (this.body_returned != 0)
/* 129:    */    {
/* 133:133 */      this.body_fill -= this.body_returned;
/* 134:134 */      if (this.body_fill != 0) {
/* 135:135 */        System.arraycopy(this.body_data, this.body_returned, this.body_data, 0, this.body_fill);
/* 136:    */      }
/* 137:137 */      this.body_returned = 0;
/* 138:    */    }
/* 139:    */    
/* 141:141 */    body_expand(op.bytes);
/* 142:142 */    lacing_expand(lacing_val);
/* 143:    */    
/* 149:149 */    System.arraycopy(op.packet_base, op.packet, this.body_data, this.body_fill, op.bytes);
/* 150:150 */    this.body_fill += op.bytes;
/* 151:    */    
/* 154:154 */    for (int j = 0; j < lacing_val - 1; j++) {
/* 155:155 */      this.lacing_vals[(this.lacing_fill + j)] = 255;
/* 156:156 */      this.granule_vals[(this.lacing_fill + j)] = this.granulepos;
/* 157:    */    }
/* 158:158 */    this.lacing_vals[(this.lacing_fill + j)] = (op.bytes % 255);
/* 159:159 */    this.granulepos = (this.granule_vals[(this.lacing_fill + j)] = op.granulepos);
/* 160:    */    
/* 162:162 */    this.lacing_vals[this.lacing_fill] |= 256;
/* 163:    */    
/* 164:164 */    this.lacing_fill += lacing_val;
/* 165:    */    
/* 167:167 */    this.packetno += 1L;
/* 168:    */    
/* 169:169 */    if (op.e_o_s != 0)
/* 170:170 */      this.e_o_s = 1;
/* 171:171 */    return 0;
/* 172:    */  }
/* 173:    */  
/* 178:    */  public int packetout(Packet op)
/* 179:    */  {
/* 180:180 */    int ptr = this.lacing_returned;
/* 181:    */    
/* 182:182 */    if (this.lacing_packet <= ptr) {
/* 183:183 */      return 0;
/* 184:    */    }
/* 185:    */    
/* 186:186 */    if ((this.lacing_vals[ptr] & 0x400) != 0)
/* 187:    */    {
/* 188:188 */      this.lacing_returned += 1;
/* 189:    */      
/* 192:192 */      this.packetno += 1L;
/* 193:193 */      return -1;
/* 194:    */    }
/* 195:    */    
/* 198:198 */    int size = this.lacing_vals[ptr] & 0xFF;
/* 199:199 */    int bytes = 0;
/* 200:    */    
/* 201:201 */    op.packet_base = this.body_data;
/* 202:202 */    op.packet = this.body_returned;
/* 203:203 */    op.e_o_s = (this.lacing_vals[ptr] & 0x200);
/* 204:204 */    op.b_o_s = (this.lacing_vals[ptr] & 0x100);
/* 205:205 */    bytes += size;
/* 206:    */    
/* 207:207 */    while (size == 255) {
/* 208:208 */      int val = this.lacing_vals[(++ptr)];
/* 209:209 */      size = val & 0xFF;
/* 210:210 */      if ((val & 0x200) != 0)
/* 211:211 */        op.e_o_s = 512;
/* 212:212 */      bytes += size;
/* 213:    */    }
/* 214:    */    
/* 215:215 */    op.packetno = this.packetno;
/* 216:216 */    op.granulepos = this.granule_vals[ptr];
/* 217:217 */    op.bytes = bytes;
/* 218:    */    
/* 219:219 */    this.body_returned += bytes;
/* 220:    */    
/* 221:221 */    this.lacing_returned = (ptr + 1);
/* 222:    */    
/* 223:223 */    this.packetno += 1L;
/* 224:224 */    return 1;
/* 225:    */  }
/* 226:    */  
/* 229:    */  public int pagein(Page og)
/* 230:    */  {
/* 231:231 */    byte[] header_base = og.header_base;
/* 232:232 */    int header = og.header;
/* 233:233 */    byte[] body_base = og.body_base;
/* 234:234 */    int body = og.body;
/* 235:235 */    int bodysize = og.body_len;
/* 236:236 */    int segptr = 0;
/* 237:    */    
/* 238:238 */    int version = og.version();
/* 239:239 */    int continued = og.continued();
/* 240:240 */    int bos = og.bos();
/* 241:241 */    int eos = og.eos();
/* 242:242 */    long granulepos = og.granulepos();
/* 243:243 */    int _serialno = og.serialno();
/* 244:244 */    int _pageno = og.pageno();
/* 245:245 */    int segments = header_base[(header + 26)] & 0xFF;
/* 246:    */    
/* 249:249 */    int lr = this.lacing_returned;
/* 250:250 */    int br = this.body_returned;
/* 251:    */    
/* 253:253 */    if (br != 0) {
/* 254:254 */      this.body_fill -= br;
/* 255:255 */      if (this.body_fill != 0) {
/* 256:256 */        System.arraycopy(this.body_data, br, this.body_data, 0, this.body_fill);
/* 257:    */      }
/* 258:258 */      this.body_returned = 0;
/* 259:    */    }
/* 260:    */    
/* 261:261 */    if (lr != 0)
/* 262:    */    {
/* 263:263 */      if (this.lacing_fill - lr != 0) {
/* 264:264 */        System.arraycopy(this.lacing_vals, lr, this.lacing_vals, 0, this.lacing_fill - lr);
/* 265:265 */        System.arraycopy(this.granule_vals, lr, this.granule_vals, 0, this.lacing_fill - lr);
/* 266:    */      }
/* 267:267 */      this.lacing_fill -= lr;
/* 268:268 */      this.lacing_packet -= lr;
/* 269:269 */      this.lacing_returned = 0;
/* 270:    */    }
/* 271:    */    
/* 274:274 */    if (_serialno != this.serialno)
/* 275:275 */      return -1;
/* 276:276 */    if (version > 0) {
/* 277:277 */      return -1;
/* 278:    */    }
/* 279:279 */    lacing_expand(segments + 1);
/* 280:    */    
/* 282:282 */    if (_pageno != this.pageno)
/* 283:    */    {
/* 286:286 */      for (int i = this.lacing_packet; i < this.lacing_fill; i++) {
/* 287:287 */        this.body_fill -= (this.lacing_vals[i] & 0xFF);
/* 288:    */      }
/* 289:    */      
/* 290:290 */      this.lacing_fill = this.lacing_packet;
/* 291:    */      
/* 293:293 */      if (this.pageno != -1) {
/* 294:294 */        this.lacing_vals[(this.lacing_fill++)] = 1024;
/* 295:295 */        this.lacing_packet += 1;
/* 296:    */      }
/* 297:    */      
/* 300:300 */      if (continued != 0) {
/* 301:301 */        bos = 0;
/* 302:302 */        for (; segptr < segments; segptr++) {
/* 303:303 */          int val = header_base[(header + 27 + segptr)] & 0xFF;
/* 304:304 */          body += val;
/* 305:305 */          bodysize -= val;
/* 306:306 */          if (val < 255) {
/* 307:307 */            segptr++;
/* 308:308 */            break;
/* 309:    */          }
/* 310:    */        }
/* 311:    */      }
/* 312:    */    }
/* 313:    */    
/* 314:314 */    if (bodysize != 0) {
/* 315:315 */      body_expand(bodysize);
/* 316:316 */      System.arraycopy(body_base, body, this.body_data, this.body_fill, bodysize);
/* 317:317 */      this.body_fill += bodysize;
/* 318:    */    }
/* 319:    */    
/* 321:321 */    int saved = -1;
/* 322:322 */    while (segptr < segments) {
/* 323:323 */      int val = header_base[(header + 27 + segptr)] & 0xFF;
/* 324:324 */      this.lacing_vals[this.lacing_fill] = val;
/* 325:325 */      this.granule_vals[this.lacing_fill] = -1L;
/* 326:    */      
/* 327:327 */      if (bos != 0) {
/* 328:328 */        this.lacing_vals[this.lacing_fill] |= 256;
/* 329:329 */        bos = 0;
/* 330:    */      }
/* 331:    */      
/* 332:332 */      if (val < 255) {
/* 333:333 */        saved = this.lacing_fill;
/* 334:    */      }
/* 335:335 */      this.lacing_fill += 1;
/* 336:336 */      segptr++;
/* 337:    */      
/* 338:338 */      if (val < 255) {
/* 339:339 */        this.lacing_packet = this.lacing_fill;
/* 340:    */      }
/* 341:    */    }
/* 342:    */    
/* 343:343 */    if (saved != -1) {
/* 344:344 */      this.granule_vals[saved] = granulepos;
/* 345:    */    }
/* 346:    */    
/* 348:348 */    if (eos != 0) {
/* 349:349 */      this.e_o_s = 1;
/* 350:350 */      if (this.lacing_fill > 0) {
/* 351:351 */        this.lacing_vals[(this.lacing_fill - 1)] |= 512;
/* 352:    */      }
/* 353:    */    }
/* 354:354 */    this.pageno = (_pageno + 1);
/* 355:355 */    return 0;
/* 356:    */  }
/* 357:    */  
/* 373:    */  public int flush(Page og)
/* 374:    */  {
/* 375:375 */    int vals = 0;
/* 376:376 */    int maxvals = this.lacing_fill > 255 ? 255 : this.lacing_fill;
/* 377:377 */    int bytes = 0;
/* 378:378 */    int acc = 0;
/* 379:379 */    long granule_pos = this.granule_vals[0];
/* 380:    */    
/* 381:381 */    if (maxvals == 0) {
/* 382:382 */      return 0;
/* 383:    */    }
/* 384:    */    
/* 389:389 */    if (this.b_o_s == 0) {
/* 390:390 */      granule_pos = 0L;
/* 391:391 */      for (vals = 0; vals < maxvals; vals++) {
/* 392:392 */        if ((this.lacing_vals[vals] & 0xFF) < 255) {
/* 393:393 */          vals++;
/* 394:394 */          break;
/* 395:    */        }
/* 396:    */      }
/* 397:    */    }
/* 398:    */    
/* 399:399 */    for (vals = 0; vals < maxvals; vals++) {
/* 400:400 */      if (acc > 4096)
/* 401:    */        break;
/* 402:402 */      acc += (this.lacing_vals[vals] & 0xFF);
/* 403:403 */      granule_pos = this.granule_vals[vals];
/* 404:    */    }
/* 405:    */    
/* 408:408 */    System.arraycopy("OggS".getBytes(), 0, this.header, 0, 4);
/* 409:    */    
/* 411:411 */    this.header[4] = 0;
/* 412:    */    
/* 414:414 */    this.header[5] = 0;
/* 415:415 */    if ((this.lacing_vals[0] & 0x100) == 0) {
/* 416:416 */      int tmp186_185 = 5; byte[] tmp186_182 = this.header;tmp186_182[tmp186_185] = ((byte)(tmp186_182[tmp186_185] | 0x1));
/* 417:    */    }
/* 418:418 */    if (this.b_o_s == 0) {
/* 419:419 */      int tmp204_203 = 5; byte[] tmp204_200 = this.header;tmp204_200[tmp204_203] = ((byte)(tmp204_200[tmp204_203] | 0x2));
/* 420:    */    }
/* 421:421 */    if ((this.e_o_s != 0) && (this.lacing_fill == vals)) {
/* 422:422 */      int tmp230_229 = 5; byte[] tmp230_226 = this.header;tmp230_226[tmp230_229] = ((byte)(tmp230_226[tmp230_229] | 0x4)); }
/* 423:423 */    this.b_o_s = 1;
/* 424:    */    
/* 426:426 */    for (int i = 6; i < 14; i++) {
/* 427:427 */      this.header[i] = ((byte)(int)granule_pos);
/* 428:428 */      granule_pos >>>= 8;
/* 429:    */    }
/* 430:    */    
/* 433:433 */    int _serialno = this.serialno;
/* 434:434 */    for (i = 14; i < 18; i++) {
/* 435:435 */      this.header[i] = ((byte)_serialno);
/* 436:436 */      _serialno >>>= 8;
/* 437:    */    }
/* 438:    */    
/* 442:442 */    if (this.pageno == -1) {
/* 443:443 */      this.pageno = 0;
/* 444:    */    }
/* 445:    */    
/* 449:449 */    int _pageno = this.pageno++;
/* 450:450 */    for (i = 18; i < 22; i++) {
/* 451:451 */      this.header[i] = ((byte)_pageno);
/* 452:452 */      _pageno >>>= 8;
/* 453:    */    }
/* 454:    */    
/* 457:457 */    this.header[22] = 0;
/* 458:458 */    this.header[23] = 0;
/* 459:459 */    this.header[24] = 0;
/* 460:460 */    this.header[25] = 0;
/* 461:    */    
/* 463:463 */    this.header[26] = ((byte)vals);
/* 464:464 */    for (i = 0; i < vals; i++) {
/* 465:465 */      this.header[(i + 27)] = ((byte)this.lacing_vals[i]);
/* 466:466 */      bytes += (this.header[(i + 27)] & 0xFF);
/* 467:    */    }
/* 468:    */    
/* 470:470 */    og.header_base = this.header;
/* 471:471 */    og.header = 0;
/* 472:472 */    og.header_len = (this.header_fill = vals + 27);
/* 473:473 */    og.body_base = this.body_data;
/* 474:474 */    og.body = this.body_returned;
/* 475:475 */    og.body_len = bytes;
/* 476:    */    
/* 479:479 */    this.lacing_fill -= vals;
/* 480:480 */    System.arraycopy(this.lacing_vals, vals, this.lacing_vals, 0, this.lacing_fill * 4);
/* 481:481 */    System.arraycopy(this.granule_vals, vals, this.granule_vals, 0, this.lacing_fill * 8);
/* 482:482 */    this.body_returned += bytes;
/* 483:    */    
/* 486:486 */    og.checksum();
/* 487:    */    
/* 489:489 */    return 1;
/* 490:    */  }
/* 491:    */  
/* 494:    */  public int pageout(Page og)
/* 495:    */  {
/* 496:496 */    if (((this.e_o_s != 0) && (this.lacing_fill != 0)) || (this.body_fill - this.body_returned > 4096) || (this.lacing_fill >= 255) || ((this.lacing_fill != 0) && (this.b_o_s == 0)))
/* 497:    */    {
/* 500:500 */      return flush(og);
/* 501:    */    }
/* 502:502 */    return 0;
/* 503:    */  }
/* 504:    */  
/* 505:    */  public int eof() {
/* 506:506 */    return this.e_o_s;
/* 507:    */  }
/* 508:    */  
/* 509:    */  public int reset() {
/* 510:510 */    this.body_fill = 0;
/* 511:511 */    this.body_returned = 0;
/* 512:    */    
/* 513:513 */    this.lacing_fill = 0;
/* 514:514 */    this.lacing_packet = 0;
/* 515:515 */    this.lacing_returned = 0;
/* 516:    */    
/* 517:517 */    this.header_fill = 0;
/* 518:    */    
/* 519:519 */    this.e_o_s = 0;
/* 520:520 */    this.b_o_s = 0;
/* 521:521 */    this.pageno = -1;
/* 522:522 */    this.packetno = 0L;
/* 523:523 */    this.granulepos = 0L;
/* 524:524 */    return 0;
/* 525:    */  }
/* 526:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.StreamState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */