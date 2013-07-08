/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */import com.jcraft.jogg.Packet;
/*   5:    */
/*  31:    */public class Info
/*  32:    */{
/*  33:    */  private static final int OV_EBADPACKET = -136;
/*  34:    */  private static final int OV_ENOTAUDIO = -135;
/*  35: 35 */  private static byte[] _vorbis = "vorbis".getBytes();
/*  36:    */  
/*  38:    */  private static final int VI_TIMEB = 1;
/*  39:    */  
/*  41:    */  private static final int VI_FLOORB = 2;
/*  42:    */  
/*  44:    */  private static final int VI_RESB = 3;
/*  45:    */  
/*  47:    */  private static final int VI_MAPB = 1;
/*  48:    */  
/*  50:    */  private static final int VI_WINDOWB = 1;
/*  51:    */  
/*  53:    */  public int version;
/*  54:    */  
/*  56:    */  public int channels;
/*  57:    */  
/*  59:    */  public int rate;
/*  60:    */  
/*  62:    */  int bitrate_upper;
/*  63:    */  
/*  65:    */  int bitrate_nominal;
/*  66:    */  
/*  67:    */  int bitrate_lower;
/*  68:    */  
/*  69: 69 */  int[] blocksizes = new int[2];
/*  70:    */  
/*  71:    */  int modes;
/*  72:    */  
/*  73:    */  int maps;
/*  74:    */  
/*  75:    */  int times;
/*  76:    */  
/*  77:    */  int floors;
/*  78:    */  
/*  79:    */  int residues;
/*  80:    */  
/*  81:    */  int books;
/*  82:    */  
/*  83:    */  int psys;
/*  84: 84 */  InfoMode[] mode_param = null;
/*  85:    */  
/*  86: 86 */  int[] map_type = null;
/*  87: 87 */  Object[] map_param = null;
/*  88:    */  
/*  89: 89 */  int[] time_type = null;
/*  90: 90 */  Object[] time_param = null;
/*  91:    */  
/*  92: 92 */  int[] floor_type = null;
/*  93: 93 */  Object[] floor_param = null;
/*  94:    */  
/*  95: 95 */  int[] residue_type = null;
/*  96: 96 */  Object[] residue_param = null;
/*  97:    */  
/*  98: 98 */  StaticCodeBook[] book_param = null;
/*  99:    */  
/* 100:100 */  PsyInfo[] psy_param = new PsyInfo[64];
/* 101:    */  
/* 102:    */  int envelopesa;
/* 103:    */  
/* 104:    */  float preecho_thresh;
/* 105:    */  float preecho_clamp;
/* 106:    */  
/* 107:    */  public void init()
/* 108:    */  {
/* 109:109 */    this.rate = 0;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void clear() {
/* 113:113 */    for (int i = 0; i < this.modes; i++) {
/* 114:114 */      this.mode_param[i] = null;
/* 115:    */    }
/* 116:116 */    this.mode_param = null;
/* 117:    */    
/* 118:118 */    for (int i = 0; i < this.maps; i++) {
/* 119:119 */      FuncMapping.mapping_P[this.map_type[i]].free_info(this.map_param[i]);
/* 120:    */    }
/* 121:121 */    this.map_param = null;
/* 122:    */    
/* 123:123 */    for (int i = 0; i < this.times; i++) {
/* 124:124 */      FuncTime.time_P[this.time_type[i]].free_info(this.time_param[i]);
/* 125:    */    }
/* 126:126 */    this.time_param = null;
/* 127:    */    
/* 128:128 */    for (int i = 0; i < this.floors; i++) {
/* 129:129 */      FuncFloor.floor_P[this.floor_type[i]].free_info(this.floor_param[i]);
/* 130:    */    }
/* 131:131 */    this.floor_param = null;
/* 132:    */    
/* 133:133 */    for (int i = 0; i < this.residues; i++) {
/* 134:134 */      FuncResidue.residue_P[this.residue_type[i]].free_info(this.residue_param[i]);
/* 135:    */    }
/* 136:136 */    this.residue_param = null;
/* 137:    */    
/* 142:142 */    for (int i = 0; i < this.books; i++)
/* 143:    */    {
/* 144:144 */      if (this.book_param[i] != null) {
/* 145:145 */        this.book_param[i].clear();
/* 146:146 */        this.book_param[i] = null;
/* 147:    */      }
/* 148:    */    }
/* 149:    */    
/* 150:150 */    this.book_param = null;
/* 151:    */    
/* 152:152 */    for (int i = 0; i < this.psys; i++) {
/* 153:153 */      this.psy_param[i].free();
/* 154:    */    }
/* 155:    */  }
/* 156:    */  
/* 158:    */  int unpack_info(Buffer opb)
/* 159:    */  {
/* 160:160 */    this.version = opb.read(32);
/* 161:161 */    if (this.version != 0) {
/* 162:162 */      return -1;
/* 163:    */    }
/* 164:164 */    this.channels = opb.read(8);
/* 165:165 */    this.rate = opb.read(32);
/* 166:    */    
/* 167:167 */    this.bitrate_upper = opb.read(32);
/* 168:168 */    this.bitrate_nominal = opb.read(32);
/* 169:169 */    this.bitrate_lower = opb.read(32);
/* 170:    */    
/* 171:171 */    this.blocksizes[0] = (1 << opb.read(4));
/* 172:172 */    this.blocksizes[1] = (1 << opb.read(4));
/* 173:    */    
/* 174:174 */    if ((this.rate < 1) || (this.channels < 1) || (this.blocksizes[0] < 8) || (this.blocksizes[1] < this.blocksizes[0]) || (opb.read(1) != 1))
/* 175:    */    {
/* 176:176 */      clear();
/* 177:177 */      return -1;
/* 178:    */    }
/* 179:179 */    return 0;
/* 180:    */  }
/* 181:    */  
/* 184:    */  int unpack_books(Buffer opb)
/* 185:    */  {
/* 186:186 */    this.books = (opb.read(8) + 1);
/* 187:    */    
/* 188:188 */    if ((this.book_param == null) || (this.book_param.length != this.books))
/* 189:189 */      this.book_param = new StaticCodeBook[this.books];
/* 190:190 */    for (int i = 0; i < this.books; i++) {
/* 191:191 */      this.book_param[i] = new StaticCodeBook();
/* 192:192 */      if (this.book_param[i].unpack(opb) != 0) {
/* 193:193 */        clear();
/* 194:194 */        return -1;
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 199:199 */    this.times = (opb.read(6) + 1);
/* 200:200 */    if ((this.time_type == null) || (this.time_type.length != this.times))
/* 201:201 */      this.time_type = new int[this.times];
/* 202:202 */    if ((this.time_param == null) || (this.time_param.length != this.times))
/* 203:203 */      this.time_param = new Object[this.times];
/* 204:204 */    for (int i = 0; i < this.times; i++) {
/* 205:205 */      this.time_type[i] = opb.read(16);
/* 206:206 */      if ((this.time_type[i] < 0) || (this.time_type[i] >= 1)) {
/* 207:207 */        clear();
/* 208:208 */        return -1;
/* 209:    */      }
/* 210:210 */      this.time_param[i] = FuncTime.time_P[this.time_type[i]].unpack(this, opb);
/* 211:211 */      if (this.time_param[i] == null) {
/* 212:212 */        clear();
/* 213:213 */        return -1;
/* 214:    */      }
/* 215:    */    }
/* 216:    */    
/* 218:218 */    this.floors = (opb.read(6) + 1);
/* 219:219 */    if ((this.floor_type == null) || (this.floor_type.length != this.floors))
/* 220:220 */      this.floor_type = new int[this.floors];
/* 221:221 */    if ((this.floor_param == null) || (this.floor_param.length != this.floors)) {
/* 222:222 */      this.floor_param = new Object[this.floors];
/* 223:    */    }
/* 224:224 */    for (int i = 0; i < this.floors; i++) {
/* 225:225 */      this.floor_type[i] = opb.read(16);
/* 226:226 */      if ((this.floor_type[i] < 0) || (this.floor_type[i] >= 2)) {
/* 227:227 */        clear();
/* 228:228 */        return -1;
/* 229:    */      }
/* 230:    */      
/* 231:231 */      this.floor_param[i] = FuncFloor.floor_P[this.floor_type[i]].unpack(this, opb);
/* 232:232 */      if (this.floor_param[i] == null) {
/* 233:233 */        clear();
/* 234:234 */        return -1;
/* 235:    */      }
/* 236:    */    }
/* 237:    */    
/* 239:239 */    this.residues = (opb.read(6) + 1);
/* 240:    */    
/* 241:241 */    if ((this.residue_type == null) || (this.residue_type.length != this.residues)) {
/* 242:242 */      this.residue_type = new int[this.residues];
/* 243:    */    }
/* 244:244 */    if ((this.residue_param == null) || (this.residue_param.length != this.residues)) {
/* 245:245 */      this.residue_param = new Object[this.residues];
/* 246:    */    }
/* 247:247 */    for (int i = 0; i < this.residues; i++) {
/* 248:248 */      this.residue_type[i] = opb.read(16);
/* 249:249 */      if ((this.residue_type[i] < 0) || (this.residue_type[i] >= 3)) {
/* 250:250 */        clear();
/* 251:251 */        return -1;
/* 252:    */      }
/* 253:253 */      this.residue_param[i] = FuncResidue.residue_P[this.residue_type[i]].unpack(this, opb);
/* 254:254 */      if (this.residue_param[i] == null) {
/* 255:255 */        clear();
/* 256:256 */        return -1;
/* 257:    */      }
/* 258:    */    }
/* 259:    */    
/* 261:261 */    this.maps = (opb.read(6) + 1);
/* 262:262 */    if ((this.map_type == null) || (this.map_type.length != this.maps))
/* 263:263 */      this.map_type = new int[this.maps];
/* 264:264 */    if ((this.map_param == null) || (this.map_param.length != this.maps))
/* 265:265 */      this.map_param = new Object[this.maps];
/* 266:266 */    for (int i = 0; i < this.maps; i++) {
/* 267:267 */      this.map_type[i] = opb.read(16);
/* 268:268 */      if ((this.map_type[i] < 0) || (this.map_type[i] >= 1)) {
/* 269:269 */        clear();
/* 270:270 */        return -1;
/* 271:    */      }
/* 272:272 */      this.map_param[i] = FuncMapping.mapping_P[this.map_type[i]].unpack(this, opb);
/* 273:273 */      if (this.map_param[i] == null) {
/* 274:274 */        clear();
/* 275:275 */        return -1;
/* 276:    */      }
/* 277:    */    }
/* 278:    */    
/* 280:280 */    this.modes = (opb.read(6) + 1);
/* 281:281 */    if ((this.mode_param == null) || (this.mode_param.length != this.modes))
/* 282:282 */      this.mode_param = new InfoMode[this.modes];
/* 283:283 */    for (int i = 0; i < this.modes; i++) {
/* 284:284 */      this.mode_param[i] = new InfoMode();
/* 285:285 */      this.mode_param[i].blockflag = opb.read(1);
/* 286:286 */      this.mode_param[i].windowtype = opb.read(16);
/* 287:287 */      this.mode_param[i].transformtype = opb.read(16);
/* 288:288 */      this.mode_param[i].mapping = opb.read(8);
/* 289:    */      
/* 290:290 */      if ((this.mode_param[i].windowtype >= 1) || (this.mode_param[i].transformtype >= 1) || (this.mode_param[i].mapping >= this.maps))
/* 291:    */      {
/* 293:293 */        clear();
/* 294:294 */        return -1;
/* 295:    */      }
/* 296:    */    }
/* 297:    */    
/* 298:298 */    if (opb.read(1) != 1) {
/* 299:299 */      clear();
/* 300:300 */      return -1;
/* 301:    */    }
/* 302:    */    
/* 303:303 */    return 0;
/* 304:    */  }
/* 305:    */  
/* 310:    */  public int synthesis_headerin(Comment vc, Packet op)
/* 311:    */  {
/* 312:312 */    Buffer opb = new Buffer();
/* 313:    */    
/* 314:314 */    if (op != null) {
/* 315:315 */      opb.readinit(op.packet_base, op.packet, op.bytes);
/* 316:    */      
/* 320:320 */      byte[] buffer = new byte[6];
/* 321:321 */      int packtype = opb.read(8);
/* 322:322 */      opb.read(buffer, 6);
/* 323:323 */      if ((buffer[0] != 118) || (buffer[1] != 111) || (buffer[2] != 114) || (buffer[3] != 98) || (buffer[4] != 105) || (buffer[5] != 115))
/* 324:    */      {
/* 326:326 */        return -1;
/* 327:    */      }
/* 328:328 */      switch (packtype) {
/* 329:    */      case 1: 
/* 330:330 */        if (op.b_o_s == 0)
/* 331:    */        {
/* 332:332 */          return -1;
/* 333:    */        }
/* 334:334 */        if (this.rate != 0)
/* 335:    */        {
/* 336:336 */          return -1;
/* 337:    */        }
/* 338:338 */        return unpack_info(opb);
/* 339:    */      case 3: 
/* 340:340 */        if (this.rate == 0)
/* 341:    */        {
/* 342:342 */          return -1;
/* 343:    */        }
/* 344:344 */        return vc.unpack(opb);
/* 345:    */      case 5: 
/* 346:346 */        if ((this.rate == 0) || (vc.vendor == null))
/* 347:    */        {
/* 348:348 */          return -1;
/* 349:    */        }
/* 350:350 */        return unpack_books(opb);
/* 351:    */      }
/* 352:    */      
/* 353:    */    }
/* 354:    */    
/* 358:358 */    return -1;
/* 359:    */  }
/* 360:    */  
/* 362:    */  int pack_info(Buffer opb)
/* 363:    */  {
/* 364:364 */    opb.write(1, 8);
/* 365:365 */    opb.write(_vorbis);
/* 366:    */    
/* 368:368 */    opb.write(0, 32);
/* 369:369 */    opb.write(this.channels, 8);
/* 370:370 */    opb.write(this.rate, 32);
/* 371:    */    
/* 372:372 */    opb.write(this.bitrate_upper, 32);
/* 373:373 */    opb.write(this.bitrate_nominal, 32);
/* 374:374 */    opb.write(this.bitrate_lower, 32);
/* 375:    */    
/* 376:376 */    opb.write(Util.ilog2(this.blocksizes[0]), 4);
/* 377:377 */    opb.write(Util.ilog2(this.blocksizes[1]), 4);
/* 378:378 */    opb.write(1, 1);
/* 379:379 */    return 0;
/* 380:    */  }
/* 381:    */  
/* 382:    */  int pack_books(Buffer opb) {
/* 383:383 */    opb.write(5, 8);
/* 384:384 */    opb.write(_vorbis);
/* 385:    */    
/* 387:387 */    opb.write(this.books - 1, 8);
/* 388:388 */    for (int i = 0; i < this.books; i++) {
/* 389:389 */      if (this.book_param[i].pack(opb) != 0)
/* 390:    */      {
/* 391:391 */        return -1;
/* 392:    */      }
/* 393:    */    }
/* 394:    */    
/* 396:396 */    opb.write(this.times - 1, 6);
/* 397:397 */    for (int i = 0; i < this.times; i++) {
/* 398:398 */      opb.write(this.time_type[i], 16);
/* 399:399 */      FuncTime.time_P[this.time_type[i]].pack(this.time_param[i], opb);
/* 400:    */    }
/* 401:    */    
/* 403:403 */    opb.write(this.floors - 1, 6);
/* 404:404 */    for (int i = 0; i < this.floors; i++) {
/* 405:405 */      opb.write(this.floor_type[i], 16);
/* 406:406 */      FuncFloor.floor_P[this.floor_type[i]].pack(this.floor_param[i], opb);
/* 407:    */    }
/* 408:    */    
/* 410:410 */    opb.write(this.residues - 1, 6);
/* 411:411 */    for (int i = 0; i < this.residues; i++) {
/* 412:412 */      opb.write(this.residue_type[i], 16);
/* 413:413 */      FuncResidue.residue_P[this.residue_type[i]].pack(this.residue_param[i], opb);
/* 414:    */    }
/* 415:    */    
/* 417:417 */    opb.write(this.maps - 1, 6);
/* 418:418 */    for (int i = 0; i < this.maps; i++) {
/* 419:419 */      opb.write(this.map_type[i], 16);
/* 420:420 */      FuncMapping.mapping_P[this.map_type[i]].pack(this, this.map_param[i], opb);
/* 421:    */    }
/* 422:    */    
/* 424:424 */    opb.write(this.modes - 1, 6);
/* 425:425 */    for (int i = 0; i < this.modes; i++) {
/* 426:426 */      opb.write(this.mode_param[i].blockflag, 1);
/* 427:427 */      opb.write(this.mode_param[i].windowtype, 16);
/* 428:428 */      opb.write(this.mode_param[i].transformtype, 16);
/* 429:429 */      opb.write(this.mode_param[i].mapping, 8);
/* 430:    */    }
/* 431:431 */    opb.write(1, 1);
/* 432:432 */    return 0;
/* 433:    */  }
/* 434:    */  
/* 435:    */  public int blocksize(Packet op)
/* 436:    */  {
/* 437:437 */    Buffer opb = new Buffer();
/* 438:    */    
/* 441:441 */    opb.readinit(op.packet_base, op.packet, op.bytes);
/* 442:    */    
/* 444:444 */    if (opb.read(1) != 0)
/* 445:    */    {
/* 446:446 */      return -135;
/* 447:    */    }
/* 448:    */    
/* 449:449 */    int modebits = 0;
/* 450:450 */    int v = this.modes;
/* 451:451 */    while (v > 1) {
/* 452:452 */      modebits++;
/* 453:453 */      v >>>= 1;
/* 454:    */    }
/* 455:    */    
/* 457:457 */    int mode = opb.read(modebits);
/* 458:    */    
/* 459:459 */    if (mode == -1)
/* 460:460 */      return -136;
/* 461:461 */    return this.blocksizes[this.mode_param[mode].blockflag];
/* 462:    */  }
/* 463:    */  
/* 464:    */  public String toString() {
/* 465:465 */    return "version:" + new Integer(this.version) + ", channels:" + new Integer(this.channels) + ", rate:" + new Integer(this.rate) + ", bitrate:" + new Integer(this.bitrate_upper) + "," + new Integer(this.bitrate_nominal) + "," + new Integer(this.bitrate_lower);
/* 466:    */  }
/* 467:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Info
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */