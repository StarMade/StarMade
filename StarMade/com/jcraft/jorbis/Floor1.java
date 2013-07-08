/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */
/*  28:    */class Floor1
/*  29:    */  extends FuncFloor
/*  30:    */{
/*  31:    */  static final int floor1_rangedb = 140;
/*  32:    */  static final int VIF_POSIT = 63;
/*  33:    */  
/*  34:    */  void pack(Object i, Buffer opb)
/*  35:    */  {
/*  36: 36 */    InfoFloor1 info = (InfoFloor1)i;
/*  37:    */    
/*  38: 38 */    int count = 0;
/*  39:    */    
/*  40: 40 */    int maxposit = info.postlist[1];
/*  41: 41 */    int maxclass = -1;
/*  42:    */    
/*  44: 44 */    opb.write(info.partitions, 5);
/*  45: 45 */    for (int j = 0; j < info.partitions; j++) {
/*  46: 46 */      opb.write(info.partitionclass[j], 4);
/*  47: 47 */      if (maxclass < info.partitionclass[j]) {
/*  48: 48 */        maxclass = info.partitionclass[j];
/*  49:    */      }
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    for (int j = 0; j < maxclass + 1; j++) {
/*  53: 53 */      opb.write(info.class_dim[j] - 1, 3);
/*  54: 54 */      opb.write(info.class_subs[j], 2);
/*  55: 55 */      if (info.class_subs[j] != 0) {
/*  56: 56 */        opb.write(info.class_book[j], 8);
/*  57:    */      }
/*  58: 58 */      for (int k = 0; k < 1 << info.class_subs[j]; k++) {
/*  59: 59 */        opb.write(info.class_subbook[j][k] + 1, 8);
/*  60:    */      }
/*  61:    */    }
/*  62:    */    
/*  64: 64 */    opb.write(info.mult - 1, 2);
/*  65: 65 */    opb.write(Util.ilog2(maxposit), 4);
/*  66: 66 */    int rangebits = Util.ilog2(maxposit);
/*  67:    */    
/*  68: 68 */    int j = 0; for (int k = 0; j < info.partitions; j++) {
/*  69: 69 */      count += info.class_dim[info.partitionclass[j]];
/*  70: 70 */      for (; k < count; k++) {
/*  71: 71 */        opb.write(info.postlist[(k + 2)], rangebits);
/*  72:    */      }
/*  73:    */    }
/*  74:    */  }
/*  75:    */  
/*  76:    */  Object unpack(Info vi, Buffer opb) {
/*  77: 77 */    int count = 0;int maxclass = -1;
/*  78: 78 */    InfoFloor1 info = new InfoFloor1();
/*  79:    */    
/*  81: 81 */    info.partitions = opb.read(5);
/*  82: 82 */    for (int j = 0; j < info.partitions; j++) {
/*  83: 83 */      info.partitionclass[j] = opb.read(4);
/*  84: 84 */      if (maxclass < info.partitionclass[j]) {
/*  85: 85 */        maxclass = info.partitionclass[j];
/*  86:    */      }
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    for (int j = 0; j < maxclass + 1; j++) {
/*  90: 90 */      info.class_dim[j] = (opb.read(3) + 1);
/*  91: 91 */      info.class_subs[j] = opb.read(2);
/*  92: 92 */      if (info.class_subs[j] < 0) {
/*  93: 93 */        info.free();
/*  94: 94 */        return null;
/*  95:    */      }
/*  96: 96 */      if (info.class_subs[j] != 0) {
/*  97: 97 */        info.class_book[j] = opb.read(8);
/*  98:    */      }
/*  99: 99 */      if ((info.class_book[j] < 0) || (info.class_book[j] >= vi.books)) {
/* 100:100 */        info.free();
/* 101:101 */        return null;
/* 102:    */      }
/* 103:103 */      for (int k = 0; k < 1 << info.class_subs[j]; k++) {
/* 104:104 */        info.class_subbook[j][k] = (opb.read(8) - 1);
/* 105:105 */        if ((info.class_subbook[j][k] < -1) || (info.class_subbook[j][k] >= vi.books)) {
/* 106:106 */          info.free();
/* 107:107 */          return null;
/* 108:    */        }
/* 109:    */      }
/* 110:    */    }
/* 111:    */    
/* 113:113 */    info.mult = (opb.read(2) + 1);
/* 114:114 */    int rangebits = opb.read(4);
/* 115:    */    
/* 116:116 */    int j = 0; for (int k = 0; j < info.partitions; j++) {
/* 117:117 */      count += info.class_dim[info.partitionclass[j]];
/* 118:118 */      for (; k < count; k++) {
/* 119:119 */        int t = info.postlist[(k + 2)] = opb.read(rangebits);
/* 120:120 */        if ((t < 0) || (t >= 1 << rangebits)) {
/* 121:121 */          info.free();
/* 122:122 */          return null;
/* 123:    */        }
/* 124:    */      }
/* 125:    */    }
/* 126:126 */    info.postlist[0] = 0;
/* 127:127 */    info.postlist[1] = (1 << rangebits);
/* 128:    */    
/* 129:129 */    return info;
/* 130:    */  }
/* 131:    */  
/* 132:    */  Object look(DspState vd, InfoMode mi, Object i) {
/* 133:133 */    int _n = 0;
/* 134:    */    
/* 135:135 */    int[] sortpointer = new int[65];
/* 136:    */    
/* 139:139 */    InfoFloor1 info = (InfoFloor1)i;
/* 140:140 */    LookFloor1 look = new LookFloor1();
/* 141:141 */    look.vi = info;
/* 142:142 */    look.n = info.postlist[1];
/* 143:    */    
/* 151:151 */    for (int j = 0; j < info.partitions; j++) {
/* 152:152 */      _n += info.class_dim[info.partitionclass[j]];
/* 153:    */    }
/* 154:154 */    _n += 2;
/* 155:155 */    look.posts = _n;
/* 156:    */    
/* 158:158 */    for (int j = 0; j < _n; j++) {
/* 159:159 */      sortpointer[j] = j;
/* 160:    */    }
/* 161:    */    
/* 164:164 */    for (int j = 0; j < _n - 1; j++) {
/* 165:165 */      for (int k = j; k < _n; k++) {
/* 166:166 */        if (info.postlist[sortpointer[j]] > info.postlist[sortpointer[k]]) {
/* 167:167 */          int foo = sortpointer[k];
/* 168:168 */          sortpointer[k] = sortpointer[j];
/* 169:169 */          sortpointer[j] = foo;
/* 170:    */        }
/* 171:    */      }
/* 172:    */    }
/* 173:    */    
/* 175:175 */    for (int j = 0; j < _n; j++) {
/* 176:176 */      look.forward_index[j] = sortpointer[j];
/* 177:    */    }
/* 178:    */    
/* 179:179 */    for (int j = 0; j < _n; j++) {
/* 180:180 */      look.reverse_index[look.forward_index[j]] = j;
/* 181:    */    }
/* 182:    */    
/* 183:183 */    for (int j = 0; j < _n; j++) {
/* 184:184 */      look.sorted_index[j] = info.postlist[look.forward_index[j]];
/* 185:    */    }
/* 186:    */    
/* 188:188 */    switch (info.mult) {
/* 189:    */    case 1: 
/* 190:190 */      look.quant_q = 256;
/* 191:191 */      break;
/* 192:    */    case 2: 
/* 193:193 */      look.quant_q = 128;
/* 194:194 */      break;
/* 195:    */    case 3: 
/* 196:196 */      look.quant_q = 86;
/* 197:197 */      break;
/* 198:    */    case 4: 
/* 199:199 */      look.quant_q = 64;
/* 200:200 */      break;
/* 201:    */    default: 
/* 202:202 */      look.quant_q = -1;
/* 203:    */    }
/* 204:    */    
/* 205:    */    
/* 207:207 */    for (int j = 0; j < _n - 2; j++) {
/* 208:208 */      int lo = 0;
/* 209:209 */      int hi = 1;
/* 210:210 */      int lx = 0;
/* 211:211 */      int hx = look.n;
/* 212:212 */      int currentx = info.postlist[(j + 2)];
/* 213:213 */      for (int k = 0; k < j + 2; k++) {
/* 214:214 */        int x = info.postlist[k];
/* 215:215 */        if ((x > lx) && (x < currentx)) {
/* 216:216 */          lo = k;
/* 217:217 */          lx = x;
/* 218:    */        }
/* 219:219 */        if ((x < hx) && (x > currentx)) {
/* 220:220 */          hi = k;
/* 221:221 */          hx = x;
/* 222:    */        }
/* 223:    */      }
/* 224:224 */      look.loneighbor[j] = lo;
/* 225:225 */      look.hineighbor[j] = hi;
/* 226:    */    }
/* 227:    */    
/* 228:228 */    return look;
/* 229:    */  }
/* 230:    */  
/* 232:    */  void free_info(Object i) {}
/* 233:    */  
/* 235:    */  void free_look(Object i) {}
/* 236:    */  
/* 237:    */  void free_state(Object vs) {}
/* 238:    */  
/* 239:    */  int forward(Block vb, Object i, float[] in, float[] out, Object vs)
/* 240:    */  {
/* 241:241 */    return 0;
/* 242:    */  }
/* 243:    */  
/* 244:    */  Object inverse1(Block vb, Object ii, Object memo) {
/* 245:245 */    LookFloor1 look = (LookFloor1)ii;
/* 246:246 */    InfoFloor1 info = look.vi;
/* 247:247 */    CodeBook[] books = vb.vd.fullbooks;
/* 248:    */    
/* 250:250 */    if (vb.opb.read(1) == 1) {
/* 251:251 */      int[] fit_value = null;
/* 252:252 */      if ((memo instanceof int[])) {
/* 253:253 */        fit_value = (int[])memo;
/* 254:    */      }
/* 255:255 */      if ((fit_value == null) || (fit_value.length < look.posts)) {
/* 256:256 */        fit_value = new int[look.posts];
/* 257:    */      }
/* 258:    */      else {
/* 259:259 */        for (int i = 0; i < fit_value.length; i++) {
/* 260:260 */          fit_value[i] = 0;
/* 261:    */        }
/* 262:    */      }
/* 263:263 */      fit_value[0] = vb.opb.read(Util.ilog(look.quant_q - 1));
/* 264:264 */      fit_value[1] = vb.opb.read(Util.ilog(look.quant_q - 1));
/* 265:    */      
/* 267:267 */      int i = 0; for (int j = 2; i < info.partitions; i++) {
/* 268:268 */        int clss = info.partitionclass[i];
/* 269:269 */        int cdim = info.class_dim[clss];
/* 270:270 */        int csubbits = info.class_subs[clss];
/* 271:271 */        int csub = 1 << csubbits;
/* 272:272 */        int cval = 0;
/* 273:    */        
/* 275:275 */        if (csubbits != 0) {
/* 276:276 */          cval = books[info.class_book[clss]].decode(vb.opb);
/* 277:    */          
/* 278:278 */          if (cval == -1) {
/* 279:279 */            return null;
/* 280:    */          }
/* 281:    */        }
/* 282:    */        
/* 283:283 */        for (int k = 0; k < cdim; k++) {
/* 284:284 */          int book = info.class_subbook[clss][(cval & csub - 1)];
/* 285:285 */          cval >>>= csubbits;
/* 286:286 */          if (book >= 0) {
/* 287:287 */            if ((fit_value[(j + k)] = books[book].decode(vb.opb)) == -1) {
/* 288:288 */              return null;
/* 289:    */            }
/* 290:    */          }
/* 291:    */          else {
/* 292:292 */            fit_value[(j + k)] = 0;
/* 293:    */          }
/* 294:    */        }
/* 295:295 */        j += cdim;
/* 296:    */      }
/* 297:    */      
/* 299:299 */      for (int i = 2; i < look.posts; i++) {
/* 300:300 */        int predicted = render_point(info.postlist[look.loneighbor[(i - 2)]], info.postlist[look.hineighbor[(i - 2)]], fit_value[look.loneighbor[(i - 2)]], fit_value[look.hineighbor[(i - 2)]], info.postlist[i]);
/* 301:    */        
/* 304:304 */        int hiroom = look.quant_q - predicted;
/* 305:305 */        int loroom = predicted;
/* 306:306 */        int room = (hiroom < loroom ? hiroom : loroom) << 1;
/* 307:307 */        int val = fit_value[i];
/* 308:    */        
/* 309:309 */        if (val != 0) {
/* 310:310 */          if (val >= room) {
/* 311:311 */            if (hiroom > loroom) {
/* 312:312 */              val -= loroom;
/* 313:    */            }
/* 314:    */            else {
/* 315:315 */              val = -1 - (val - hiroom);
/* 316:    */            }
/* 317:    */            
/* 318:    */          }
/* 319:319 */          else if ((val & 0x1) != 0) {
/* 320:320 */            val = -(val + 1 >>> 1);
/* 321:    */          }
/* 322:    */          else {
/* 323:323 */            val >>= 1;
/* 324:    */          }
/* 325:    */          
/* 327:327 */          fit_value[i] = (val + predicted);
/* 328:328 */          fit_value[look.loneighbor[(i - 2)]] &= 32767;
/* 329:329 */          fit_value[look.hineighbor[(i - 2)]] &= 32767;
/* 330:    */        }
/* 331:    */        else {
/* 332:332 */          fit_value[i] = (predicted | 0x8000);
/* 333:    */        }
/* 334:    */      }
/* 335:335 */      return fit_value;
/* 336:    */    }
/* 337:    */    
/* 338:338 */    return null;
/* 339:    */  }
/* 340:    */  
/* 341:    */  private static int render_point(int x0, int x1, int y0, int y1, int x) {
/* 342:342 */    y0 &= 32767;
/* 343:343 */    y1 &= 32767;
/* 344:    */    
/* 346:346 */    int dy = y1 - y0;
/* 347:347 */    int adx = x1 - x0;
/* 348:348 */    int ady = Math.abs(dy);
/* 349:349 */    int err = ady * (x - x0);
/* 350:    */    
/* 351:351 */    int off = err / adx;
/* 352:352 */    if (dy < 0)
/* 353:353 */      return y0 - off;
/* 354:354 */    return y0 + off;
/* 355:    */  }
/* 356:    */  
/* 357:    */  int inverse2(Block vb, Object i, Object memo, float[] out)
/* 358:    */  {
/* 359:359 */    LookFloor1 look = (LookFloor1)i;
/* 360:360 */    InfoFloor1 info = look.vi;
/* 361:361 */    int n = vb.vd.vi.blocksizes[vb.mode] / 2;
/* 362:    */    
/* 363:363 */    if (memo != null)
/* 364:    */    {
/* 365:365 */      int[] fit_value = (int[])memo;
/* 366:366 */      int hx = 0;
/* 367:367 */      int lx = 0;
/* 368:368 */      int ly = fit_value[0] * info.mult;
/* 369:369 */      for (int j = 1; j < look.posts; j++) {
/* 370:370 */        int current = look.forward_index[j];
/* 371:371 */        int hy = fit_value[current] & 0x7FFF;
/* 372:372 */        if (hy == fit_value[current]) {
/* 373:373 */          hy *= info.mult;
/* 374:374 */          hx = info.postlist[current];
/* 375:    */          
/* 376:376 */          render_line(lx, hx, ly, hy, out);
/* 377:    */          
/* 378:378 */          lx = hx;
/* 379:379 */          ly = hy;
/* 380:    */        }
/* 381:    */      }
/* 382:382 */      for (int j = hx; j < n; j++) {
/* 383:383 */        out[j] *= out[(j - 1)];
/* 384:    */      }
/* 385:385 */      return 1;
/* 386:    */    }
/* 387:387 */    for (int j = 0; j < n; j++) {
/* 388:388 */      out[j] = 0.0F;
/* 389:    */    }
/* 390:390 */    return 0;
/* 391:    */  }
/* 392:    */  
/* 393:393 */  private static float[] FLOOR_fromdB_LOOKUP = { 1.064986E-007F, 1.134195E-007F, 1.207902E-007F, 1.286398E-007F, 1.369995E-007F, 1.459025E-007F, 1.553841E-007F, 1.654818E-007F, 1.762357E-007F, 1.876886E-007F, 1.998856E-007F, 2.128753E-007F, 2.267091E-007F, 2.41442E-007F, 2.571322E-007F, 2.738421E-007F, 2.916379E-007F, 3.105902E-007F, 3.307741E-007F, 3.522697E-007F, 3.751621E-007F, 3.995423E-007F, 4.255068E-007F, 4.531586E-007F, 4.826075E-007F, 5.1397E-007F, 5.473706E-007F, 5.829419E-007F, 6.208247E-007F, 6.611694E-007F, 7.041359E-007F, 7.498946E-007F, 7.98627E-007F, 8.505263E-007F, 9.057983E-007F, 9.646622E-007F, 1.027351E-006F, 1.094114E-006F, 1.165216E-006F, 1.240938E-006F, 1.321582E-006F, 1.407465E-006F, 1.498931E-006F, 1.596339E-006F, 1.700079E-006F, 1.810559E-006F, 1.92822E-006F, 2.053526E-006F, 2.186976E-006F, 2.329098E-006F, 2.480456E-006F, 2.64165E-006F, 2.813319E-006F, 2.996144E-006F, 3.190851E-006F, 3.39821E-006F, 3.619045E-006F, 3.854231E-006F, 4.104701E-006F, 4.371447E-006F, 4.655528E-006F, 4.958071E-006F, 5.280274E-006F, 5.623416E-006F, 5.988857E-006F, 6.378047E-006F, 6.792528E-006F, 7.233945E-006F, 7.704048E-006F, 8.2047E-006F, 8.737888E-006F, 9.305725E-006F, 9.910464E-006F, 1.05545E-005F, 1.124039E-005F, 1.197086E-005F, 1.274879E-005F, 1.357728E-005F, 1.445961E-005F, 1.539927E-005F, 1.640001E-005F, 1.746577E-005F, 1.860079E-005F, 1.980958E-005F, 2.109691E-005F, 2.246791E-005F, 2.3928E-005F, 2.548298E-005F, 2.713901E-005F, 2.890265E-005F, 3.078091E-005F, 3.278123E-005F, 3.491153E-005F, 3.718028E-005F, 3.959647E-005F, 4.216967E-005F, 4.491009E-005F, 4.78286E-005F, 5.093678E-005F, 5.424693E-005F, 5.77722E-005F, 6.152657E-005F, 6.552491E-005F, 6.978308E-005F, 7.431798E-005F, 7.914758E-005F, 8.429104E-005F, 8.976875E-005F, 9.560242E-005F, 0.0001018152F, 0.0001084317F, 0.0001154782F, 0.0001229827F, 0.0001309748F, 0.0001394863F, 0.0001485509F, 0.0001582045F, 0.0001684856F, 0.0001794347F, 0.0001910954F, 0.0002035138F, 0.000216739F, 0.0002308242F, 0.0002458245F, 0.0002617996F, 0.0002788128F, 0.0002969316F, 0.0003162279F, 0.0003367782F, 0.0003586639F, 0.0003819719F, 0.0004067946F, 0.0004332304F, 0.000461384F, 0.0004913675F, 0.0005232993F, 0.0005573062F, 0.0005935231F, 0.0006320936F, 0.0006731706F, 0.000716917F, 0.0007635063F, 0.0008131233F, 0.0008659646F, 0.0009222399F, 0.0009821722F, 0.001045999F, 0.001113974F, 0.001186367F, 0.001263463F, 0.00134557F, 0.001433013F, 0.001526138F, 0.001625315F, 0.001730937F, 0.001843424F, 0.00196322F, 0.002090801F, 0.002226673F, 0.002371374F, 0.00252548F, 0.002689599F, 0.002864385F, 0.003050529F, 0.003248769F, 0.003459893F, 0.003684736F, 0.003924191F, 0.004179207F, 0.004450795F, 0.004740033F, 0.005048067F, 0.005376119F, 0.00572549F, 0.006097564F, 0.006493818F, 0.006915823F, 0.007365251F, 0.007843887F, 0.008353627F, 0.008896492F, 0.00947464F, 0.01009035F, 0.0107461F, 0.01144442F, 0.01218814F, 0.0129802F, 0.01382373F, 0.01472207F, 0.01567879F, 0.01669769F, 0.0177828F, 0.01893842F, 0.02016915F, 0.02147985F, 0.02287574F, 0.02436233F, 0.02594553F, 0.02763162F, 0.02942728F, 0.03133963F, 0.0333763F, 0.03554523F, 0.03785516F, 0.040315F, 0.04293511F, 0.04572528F, 0.04869676F, 0.05186135F, 0.0552316F, 0.05882085F, 0.06264336F, 0.06671428F, 0.0710498F, 0.07566696F, 0.08058423F, 0.08582105F, 0.0913982F, 0.09733775F, 0.103663F, 0.1103999F, 0.1175743F, 0.125215F, 0.1333522F, 0.1420181F, 0.1512473F, 0.1610762F, 0.1715438F, 0.1826917F, 0.194564F, 0.2072079F, 0.2206734F, 0.235014F, 0.2502866F, 0.2665516F, 0.2838736F, 0.3023213F, 0.3219679F, 0.3428911F, 0.3651741F, 0.388905F, 0.4141785F, 0.4410941F, 0.469759F, 0.5002865F, 0.5327979F, 0.5674221F, 0.6042964F, 0.643567F, 0.6853896F, 0.72993F, 0.777365F, 0.8278826F, 0.8816831F, 0.93898F, 1.0F };
/* 394:    */  
/* 454:    */  private static void render_line(int x0, int x1, int y0, int y1, float[] d)
/* 455:    */  {
/* 456:456 */    int dy = y1 - y0;
/* 457:457 */    int adx = x1 - x0;
/* 458:458 */    int ady = Math.abs(dy);
/* 459:459 */    int base = dy / adx;
/* 460:460 */    int sy = dy < 0 ? base - 1 : base + 1;
/* 461:461 */    int x = x0;
/* 462:462 */    int y = y0;
/* 463:463 */    int err = 0;
/* 464:    */    
/* 465:465 */    ady -= Math.abs(base * adx);
/* 466:    */    
/* 467:467 */    d[x] *= FLOOR_fromdB_LOOKUP[y];
/* 468:468 */    for (;;) { x++; if (x >= x1) break;
/* 469:469 */      err += ady;
/* 470:470 */      if (err >= adx) {
/* 471:471 */        err -= adx;
/* 472:472 */        y += sy;
/* 473:    */      }
/* 474:    */      else {
/* 475:475 */        y += base;
/* 476:    */      }
/* 477:477 */      d[x] *= FLOOR_fromdB_LOOKUP[y];
/* 478:    */    }
/* 479:    */  }
/* 480:    */  
/* 481:    */  class InfoFloor1
/* 482:    */  {
/* 483:    */    static final int VIF_POSIT = 63;
/* 484:    */    static final int VIF_CLASS = 16;
/* 485:    */    static final int VIF_PARTS = 31;
/* 486:    */    int partitions;
/* 487:487 */    int[] partitionclass = new int[31];
/* 488:    */    
/* 489:489 */    int[] class_dim = new int[16];
/* 490:490 */    int[] class_subs = new int[16];
/* 491:491 */    int[] class_book = new int[16];
/* 492:492 */    int[][] class_subbook = new int[16][];
/* 493:    */    
/* 494:    */    int mult;
/* 495:495 */    int[] postlist = new int[65];
/* 496:    */    
/* 497:    */    float maxover;
/* 498:    */    
/* 499:    */    float maxunder;
/* 500:    */    
/* 501:    */    float maxerr;
/* 502:    */    int twofitminsize;
/* 503:    */    int twofitminused;
/* 504:    */    int twofitweight;
/* 505:    */    float twofitatten;
/* 506:    */    int unusedminsize;
/* 507:    */    int unusedmin_n;
/* 508:    */    int n;
/* 509:    */    
/* 510:    */    InfoFloor1()
/* 511:    */    {
/* 512:512 */      for (int i = 0; i < this.class_subbook.length; i++) {
/* 513:513 */        this.class_subbook[i] = new int[8];
/* 514:    */      }
/* 515:    */    }
/* 516:    */    
/* 517:    */    void free() {
/* 518:518 */      this.partitionclass = null;
/* 519:519 */      this.class_dim = null;
/* 520:520 */      this.class_subs = null;
/* 521:521 */      this.class_book = null;
/* 522:522 */      this.class_subbook = ((int[][])null);
/* 523:523 */      this.postlist = null;
/* 524:    */    }
/* 525:    */    
/* 526:    */    Object copy_info() {
/* 527:527 */      InfoFloor1 info = this;
/* 528:528 */      InfoFloor1 ret = new InfoFloor1(Floor1.this);
/* 529:    */      
/* 530:530 */      ret.partitions = info.partitions;
/* 531:531 */      System.arraycopy(info.partitionclass, 0, ret.partitionclass, 0, 31);
/* 532:    */      
/* 533:533 */      System.arraycopy(info.class_dim, 0, ret.class_dim, 0, 16);
/* 534:534 */      System.arraycopy(info.class_subs, 0, ret.class_subs, 0, 16);
/* 535:535 */      System.arraycopy(info.class_book, 0, ret.class_book, 0, 16);
/* 536:    */      
/* 537:537 */      for (int j = 0; j < 16; j++) {
/* 538:538 */        System.arraycopy(info.class_subbook[j], 0, ret.class_subbook[j], 0, 8);
/* 539:    */      }
/* 540:    */      
/* 541:541 */      ret.mult = info.mult;
/* 542:542 */      System.arraycopy(info.postlist, 0, ret.postlist, 0, 65);
/* 543:    */      
/* 544:544 */      ret.maxover = info.maxover;
/* 545:545 */      ret.maxunder = info.maxunder;
/* 546:546 */      ret.maxerr = info.maxerr;
/* 547:    */      
/* 548:548 */      ret.twofitminsize = info.twofitminsize;
/* 549:549 */      ret.twofitminused = info.twofitminused;
/* 550:550 */      ret.twofitweight = info.twofitweight;
/* 551:551 */      ret.twofitatten = info.twofitatten;
/* 552:552 */      ret.unusedminsize = info.unusedminsize;
/* 553:553 */      ret.unusedmin_n = info.unusedmin_n;
/* 554:    */      
/* 555:555 */      ret.n = info.n;
/* 556:    */      
/* 557:557 */      return ret;
/* 558:    */    }
/* 559:    */  }
/* 560:    */  
/* 562:    */  class LookFloor1
/* 563:    */  {
/* 564:    */    static final int VIF_POSIT = 63;
/* 565:565 */    int[] sorted_index = new int[65];
/* 566:566 */    int[] forward_index = new int[65];
/* 567:567 */    int[] reverse_index = new int[65];
/* 568:568 */    int[] hineighbor = new int[63];
/* 569:569 */    int[] loneighbor = new int[63];
/* 570:    */    int posts;
/* 571:    */    int n;
/* 572:    */    int quant_q;
/* 573:    */    Floor1.InfoFloor1 vi;
/* 574:    */    int phrasebits;
/* 575:    */    int postbits;
/* 576:    */    int frames;
/* 577:    */    
/* 578:    */    LookFloor1() {}
/* 579:    */    
/* 580:    */    void free() {
/* 581:581 */      this.sorted_index = null;
/* 582:582 */      this.forward_index = null;
/* 583:583 */      this.reverse_index = null;
/* 584:584 */      this.hineighbor = null;
/* 585:585 */      this.loneighbor = null;
/* 586:    */    }
/* 587:    */  }
/* 588:    */  
/* 589:    */  class Lsfit_acc
/* 590:    */  {
/* 591:    */    long x0;
/* 592:    */    long x1;
/* 593:    */    long xa;
/* 594:    */    long ya;
/* 595:    */    long x2a;
/* 596:    */    long y2a;
/* 597:    */    long xya;
/* 598:    */    long n;
/* 599:    */    long an;
/* 600:    */    long un;
/* 601:    */    long edgey0;
/* 602:    */    long edgey1;
/* 603:    */    
/* 604:    */    Lsfit_acc() {}
/* 605:    */  }
/* 606:    */  
/* 607:    */  class EchstateFloor1
/* 608:    */  {
/* 609:    */    int[] codewords;
/* 610:    */    float[] curve;
/* 611:    */    long frameno;
/* 612:    */    long codes;
/* 613:    */    
/* 614:    */    EchstateFloor1() {}
/* 615:    */  }
/* 616:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Floor1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */