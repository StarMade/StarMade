/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   5:    */public class NeatTriangulator
/*   6:    */  implements Triangulator
/*   7:    */{
/*   8:    */  static final float EPSILON = 1.0E-006F;
/*   9:    */  
/*  11:    */  private float[] pointsX;
/*  12:    */  
/*  14:    */  private float[] pointsY;
/*  15:    */  
/*  17:    */  private int numPoints;
/*  18:    */  
/*  20:    */  private Edge[] edges;
/*  21:    */  
/*  23:    */  private int[] V;
/*  24:    */  
/*  25:    */  private int numEdges;
/*  26:    */  
/*  27:    */  private Triangle[] triangles;
/*  28:    */  
/*  29:    */  private int numTriangles;
/*  30:    */  
/*  31: 31 */  private float offset = 1.0E-006F;
/*  32:    */  
/*  36:    */  public NeatTriangulator()
/*  37:    */  {
/*  38: 38 */    this.pointsX = new float[100];
/*  39: 39 */    this.pointsY = new float[100];
/*  40: 40 */    this.numPoints = 0;
/*  41: 41 */    this.edges = new Edge[100];
/*  42: 42 */    this.numEdges = 0;
/*  43: 43 */    this.triangles = new Triangle[100];
/*  44: 44 */    this.numTriangles = 0;
/*  45:    */  }
/*  46:    */  
/*  50:    */  public void clear()
/*  51:    */  {
/*  52: 52 */    this.numPoints = 0;
/*  53: 53 */    this.numEdges = 0;
/*  54: 54 */    this.numTriangles = 0;
/*  55:    */  }
/*  56:    */  
/*  59:    */  private int findEdge(int i, int j)
/*  60:    */  {
/*  61:    */    int l;
/*  62:    */    
/*  64:    */    int k;
/*  65:    */    
/*  66:    */    int l;
/*  67:    */    
/*  68: 68 */    if (i < j)
/*  69:    */    {
/*  70: 70 */      int k = i;
/*  71: 71 */      l = j;
/*  72:    */    }
/*  73:    */    else {
/*  74: 74 */      k = j;
/*  75: 75 */      l = i;
/*  76:    */    }
/*  77: 77 */    for (int i1 = 0; i1 < this.numEdges; i1++) {
/*  78: 78 */      if ((this.edges[i1].v0 == k) && (this.edges[i1].v1 == l))
/*  79: 79 */        return i1;
/*  80:    */    }
/*  81: 81 */    return -1;
/*  82:    */  }
/*  83:    */  
/*  91:    */  private void addEdge(int i, int j, int k)
/*  92:    */  {
/*  93: 93 */    int l1 = findEdge(i, j);
/*  94:    */    Edge edge;
/*  95:    */    Edge edge;
/*  96:    */    int j1;
/*  97: 97 */    int k1; if (l1 < 0)
/*  98:    */    {
/*  99: 99 */      if (this.numEdges == this.edges.length)
/* 100:    */      {
/* 101:101 */        Edge[] aedge = new Edge[this.edges.length * 2];
/* 102:102 */        System.arraycopy(this.edges, 0, aedge, 0, this.numEdges);
/* 103:103 */        this.edges = aedge;
/* 104:    */      }
/* 105:105 */      int j1 = -1;
/* 106:106 */      int k1 = -1;
/* 107:107 */      l1 = this.numEdges++;
/* 108:108 */      edge = this.edges[l1] =  = new Edge();
/* 109:    */    }
/* 110:    */    else {
/* 111:111 */      edge = this.edges[l1];
/* 112:112 */      j1 = edge.t0;
/* 113:113 */      k1 = edge.t1;
/* 114:    */    }
/* 115:    */    int l;
/* 116:    */    int i1;
/* 117:117 */    if (i < j)
/* 118:    */    {
/* 119:119 */      int l = i;
/* 120:120 */      int i1 = j;
/* 121:121 */      j1 = k;
/* 122:    */    }
/* 123:    */    else {
/* 124:124 */      l = j;
/* 125:125 */      i1 = i;
/* 126:126 */      k1 = k;
/* 127:    */    }
/* 128:128 */    edge.v0 = l;
/* 129:129 */    edge.v1 = i1;
/* 130:130 */    edge.t0 = j1;
/* 131:131 */    edge.t1 = k1;
/* 132:132 */    edge.suspect = true;
/* 133:    */  }
/* 134:    */  
/* 138:    */  private void deleteEdge(int i, int j)
/* 139:    */    throws NeatTriangulator.InternalException
/* 140:    */  {
/* 141:    */    int k;
/* 142:    */    
/* 145:145 */    if (0 > (k = findEdge(i, j)))
/* 146:    */    {
/* 147:147 */      throw new InternalException("Attempt to delete unknown edge");
/* 148:    */    }
/* 149:    */    
/* 151:151 */    this.edges[k] = this.edges[(--this.numEdges)];
/* 152:    */  }
/* 153:    */  
/* 159:    */  void markSuspect(int i, int j, boolean flag)
/* 160:    */    throws NeatTriangulator.InternalException
/* 161:    */  {
/* 162:    */    int k;
/* 163:    */    
/* 167:167 */    if (0 > (k = findEdge(i, j)))
/* 168:    */    {
/* 169:169 */      throw new InternalException("Attempt to mark unknown edge");
/* 170:    */    }
/* 171:    */    
/* 172:172 */    this.edges[k].suspect = flag;
/* 173:    */  }
/* 174:    */  
/* 182:    */  private Edge chooseSuspect()
/* 183:    */  {
/* 184:184 */    for (int i = 0; i < this.numEdges; i++)
/* 185:    */    {
/* 186:186 */      Edge edge = this.edges[i];
/* 187:187 */      if (edge.suspect)
/* 188:    */      {
/* 189:189 */        edge.suspect = false;
/* 190:190 */        if ((edge.t0 >= 0) && (edge.t1 >= 0)) {
/* 191:191 */          return edge;
/* 192:    */        }
/* 193:    */      }
/* 194:    */    }
/* 195:195 */    return null;
/* 196:    */  }
/* 197:    */  
/* 209:    */  private static float rho(float f, float f1, float f2, float f3, float f4, float f5)
/* 210:    */  {
/* 211:211 */    float f6 = f4 - f2;
/* 212:212 */    float f7 = f5 - f3;
/* 213:213 */    float f8 = f - f4;
/* 214:214 */    float f9 = f1 - f5;
/* 215:215 */    float f18 = f6 * f9 - f7 * f8;
/* 216:216 */    if (f18 > 0.0F)
/* 217:    */    {
/* 218:218 */      if (f18 < 1.0E-006F)
/* 219:219 */        f18 = 1.0E-006F;
/* 220:220 */      float f12 = f6 * f6;
/* 221:221 */      float f13 = f7 * f7;
/* 222:222 */      float f14 = f8 * f8;
/* 223:223 */      float f15 = f9 * f9;
/* 224:224 */      float f10 = f2 - f;
/* 225:225 */      float f11 = f3 - f1;
/* 226:226 */      float f16 = f10 * f10;
/* 227:227 */      float f17 = f11 * f11;
/* 228:228 */      return (f12 + f13) * (f14 + f15) * (f16 + f17) / (f18 * f18);
/* 229:    */    }
/* 230:    */    
/* 231:231 */    return -1.0F;
/* 232:    */  }
/* 233:    */  
/* 249:    */  private static boolean insideTriangle(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7)
/* 250:    */  {
/* 251:251 */    float f8 = f4 - f2;
/* 252:252 */    float f9 = f5 - f3;
/* 253:253 */    float f10 = f - f4;
/* 254:254 */    float f11 = f1 - f5;
/* 255:255 */    float f12 = f2 - f;
/* 256:256 */    float f13 = f3 - f1;
/* 257:257 */    float f14 = f6 - f;
/* 258:258 */    float f15 = f7 - f1;
/* 259:259 */    float f16 = f6 - f2;
/* 260:260 */    float f17 = f7 - f3;
/* 261:261 */    float f18 = f6 - f4;
/* 262:262 */    float f19 = f7 - f5;
/* 263:263 */    float f22 = f8 * f17 - f9 * f16;
/* 264:264 */    float f20 = f12 * f15 - f13 * f14;
/* 265:265 */    float f21 = f10 * f19 - f11 * f18;
/* 266:266 */    return (f22 >= 0.0D) && (f21 >= 0.0D) && (f20 >= 0.0D);
/* 267:    */  }
/* 268:    */  
/* 279:    */  private boolean snip(int i, int j, int k, int l)
/* 280:    */  {
/* 281:281 */    float f = this.pointsX[this.V[i]];
/* 282:282 */    float f1 = this.pointsY[this.V[i]];
/* 283:283 */    float f2 = this.pointsX[this.V[j]];
/* 284:284 */    float f3 = this.pointsY[this.V[j]];
/* 285:285 */    float f4 = this.pointsX[this.V[k]];
/* 286:286 */    float f5 = this.pointsY[this.V[k]];
/* 287:287 */    if (1.0E-006F > (f2 - f) * (f5 - f1) - (f3 - f1) * (f4 - f))
/* 288:288 */      return false;
/* 289:289 */    for (int i1 = 0; i1 < l; i1++) {
/* 290:290 */      if ((i1 != i) && (i1 != j) && (i1 != k))
/* 291:    */      {
/* 292:292 */        float f6 = this.pointsX[this.V[i1]];
/* 293:293 */        float f7 = this.pointsY[this.V[i1]];
/* 294:294 */        if (insideTriangle(f, f1, f2, f3, f4, f5, f6, f7))
/* 295:295 */          return false;
/* 296:    */      }
/* 297:    */    }
/* 298:298 */    return true;
/* 299:    */  }
/* 300:    */  
/* 306:    */  private float area()
/* 307:    */  {
/* 308:308 */    float f = 0.0F;
/* 309:309 */    int i = this.numPoints - 1;
/* 310:310 */    for (int j = 0; j < this.numPoints;)
/* 311:    */    {
/* 312:312 */      f += this.pointsX[i] * this.pointsY[j] - this.pointsY[i] * this.pointsX[j];
/* 313:313 */      i = j++;
/* 314:    */    }
/* 315:    */    
/* 316:316 */    return f * 0.5F;
/* 317:    */  }
/* 318:    */  
/* 323:    */  public void basicTriangulation()
/* 324:    */    throws NeatTriangulator.InternalException
/* 325:    */  {
/* 326:326 */    int i = this.numPoints;
/* 327:327 */    if (i < 3)
/* 328:328 */      return;
/* 329:329 */    this.numEdges = 0;
/* 330:330 */    this.numTriangles = 0;
/* 331:331 */    this.V = new int[i];
/* 332:    */    
/* 333:333 */    if (0.0D < area())
/* 334:    */    {
/* 335:335 */      for (int k = 0; k < i; k++) {
/* 336:336 */        this.V[k] = k;
/* 337:    */      }
/* 338:    */      
/* 339:    */    } else {
/* 340:340 */      for (int l = 0; l < i; l++) {
/* 341:341 */        this.V[l] = (this.numPoints - 1 - l);
/* 342:    */      }
/* 343:    */    }
/* 344:344 */    int k1 = 2 * i;
/* 345:345 */    int i1 = i - 1;
/* 346:346 */    while (i > 2)
/* 347:    */    {
/* 348:348 */      if (0 >= k1--) {
/* 349:349 */        throw new InternalException("Bad polygon");
/* 350:    */      }
/* 351:    */      
/* 352:352 */      int j = i1;
/* 353:353 */      if (i <= j)
/* 354:354 */        j = 0;
/* 355:355 */      i1 = j + 1;
/* 356:356 */      if (i <= i1)
/* 357:357 */        i1 = 0;
/* 358:358 */      int j1 = i1 + 1;
/* 359:359 */      if (i <= j1)
/* 360:360 */        j1 = 0;
/* 361:361 */      if (snip(j, i1, j1, i))
/* 362:    */      {
/* 363:363 */        int l1 = this.V[j];
/* 364:364 */        int i2 = this.V[i1];
/* 365:365 */        int j2 = this.V[j1];
/* 366:366 */        if (this.numTriangles == this.triangles.length)
/* 367:    */        {
/* 368:368 */          Triangle[] atriangle = new Triangle[this.triangles.length * 2];
/* 369:369 */          System.arraycopy(this.triangles, 0, atriangle, 0, this.numTriangles);
/* 370:370 */          this.triangles = atriangle;
/* 371:    */        }
/* 372:372 */        this.triangles[this.numTriangles] = new Triangle(l1, i2, j2);
/* 373:373 */        addEdge(l1, i2, this.numTriangles);
/* 374:374 */        addEdge(i2, j2, this.numTriangles);
/* 375:375 */        addEdge(j2, l1, this.numTriangles);
/* 376:376 */        this.numTriangles += 1;
/* 377:377 */        int k2 = i1;
/* 378:378 */        for (int l2 = i1 + 1; l2 < i; l2++)
/* 379:    */        {
/* 380:380 */          this.V[k2] = this.V[l2];
/* 381:381 */          k2++;
/* 382:    */        }
/* 383:    */        
/* 384:384 */        i--;
/* 385:385 */        k1 = 2 * i;
/* 386:    */      }
/* 387:    */    }
/* 388:388 */    this.V = null;
/* 389:    */  }
/* 390:    */  
/* 394:    */  private void optimize()
/* 395:    */    throws NeatTriangulator.InternalException
/* 396:    */  {
/* 397:    */    Edge edge;
/* 398:    */    
/* 401:401 */    while ((edge = chooseSuspect()) != null)
/* 402:    */    {
/* 404:404 */      int i1 = edge.v0;
/* 405:405 */      int k1 = edge.v1;
/* 406:406 */      int i = edge.t0;
/* 407:407 */      int j = edge.t1;
/* 408:408 */      int j1 = -1;
/* 409:409 */      int l1 = -1;
/* 410:410 */      for (int k = 0; k < 3; k++)
/* 411:    */      {
/* 412:412 */        int i2 = this.triangles[i].v[k];
/* 413:413 */        if ((i1 != i2) && (k1 != i2))
/* 414:    */        {
/* 416:416 */          l1 = i2;
/* 417:417 */          break;
/* 418:    */        }
/* 419:    */      }
/* 420:420 */      for (int l = 0; l < 3; l++)
/* 421:    */      {
/* 422:422 */        int j2 = this.triangles[j].v[l];
/* 423:423 */        if ((i1 != j2) && (k1 != j2))
/* 424:    */        {
/* 426:426 */          j1 = j2;
/* 427:427 */          break;
/* 428:    */        }
/* 429:    */      }
/* 430:430 */      if ((-1 == j1) || (-1 == l1)) {
/* 431:431 */        throw new InternalException("can't find quad");
/* 432:    */      }
/* 433:    */      
/* 434:434 */      float f = this.pointsX[i1];
/* 435:435 */      float f1 = this.pointsY[i1];
/* 436:436 */      float f2 = this.pointsX[j1];
/* 437:437 */      float f3 = this.pointsY[j1];
/* 438:438 */      float f4 = this.pointsX[k1];
/* 439:439 */      float f5 = this.pointsY[k1];
/* 440:440 */      float f6 = this.pointsX[l1];
/* 441:441 */      float f7 = this.pointsY[l1];
/* 442:442 */      float f8 = rho(f, f1, f2, f3, f4, f5);
/* 443:443 */      float f9 = rho(f, f1, f4, f5, f6, f7);
/* 444:444 */      float f10 = rho(f2, f3, f4, f5, f6, f7);
/* 445:445 */      float f11 = rho(f2, f3, f6, f7, f, f1);
/* 446:446 */      if ((0.0F > f8) || (0.0F > f9)) {
/* 447:447 */        throw new InternalException("original triangles backwards");
/* 448:    */      }
/* 449:449 */      if ((0.0F <= f10) && (0.0F <= f11))
/* 450:    */      {
/* 451:451 */        if (f8 > f9) {
/* 452:452 */          f8 = f9;
/* 453:    */        }
/* 454:454 */        if (f10 > f11) {
/* 455:455 */          f10 = f11;
/* 456:    */        }
/* 457:457 */        if (f8 > f10) {
/* 458:458 */          deleteEdge(i1, k1);
/* 459:459 */          this.triangles[i].v[0] = j1;
/* 460:460 */          this.triangles[i].v[1] = k1;
/* 461:461 */          this.triangles[i].v[2] = l1;
/* 462:462 */          this.triangles[j].v[0] = j1;
/* 463:463 */          this.triangles[j].v[1] = l1;
/* 464:464 */          this.triangles[j].v[2] = i1;
/* 465:465 */          addEdge(j1, k1, i);
/* 466:466 */          addEdge(k1, l1, i);
/* 467:467 */          addEdge(l1, j1, i);
/* 468:468 */          addEdge(l1, i1, j);
/* 469:469 */          addEdge(i1, j1, j);
/* 470:470 */          addEdge(j1, l1, j);
/* 471:471 */          markSuspect(j1, l1, false);
/* 472:    */        }
/* 473:    */      }
/* 474:    */    }
/* 475:    */  }
/* 476:    */  
/* 480:    */  public boolean triangulate()
/* 481:    */  {
/* 482:    */    try
/* 483:    */    {
/* 484:484 */      basicTriangulation();
/* 485:    */      
/* 486:486 */      return true;
/* 487:    */    }
/* 488:    */    catch (InternalException e)
/* 489:    */    {
/* 490:490 */      this.numEdges = 0;
/* 491:    */    }
/* 492:492 */    return false;
/* 493:    */  }
/* 494:    */  
/* 498:    */  public void addPolyPoint(float x, float y)
/* 499:    */  {
/* 500:500 */    for (int i = 0; i < this.numPoints; i++) {
/* 501:501 */      if ((this.pointsX[i] == x) && (this.pointsY[i] == y))
/* 502:    */      {
/* 503:503 */        y += this.offset;
/* 504:504 */        this.offset += 1.0E-006F;
/* 505:    */      }
/* 506:    */    }
/* 507:    */    
/* 508:508 */    if (this.numPoints == this.pointsX.length)
/* 509:    */    {
/* 510:510 */      float[] af = new float[this.numPoints * 2];
/* 511:511 */      System.arraycopy(this.pointsX, 0, af, 0, this.numPoints);
/* 512:512 */      this.pointsX = af;
/* 513:513 */      af = new float[this.numPoints * 2];
/* 514:514 */      System.arraycopy(this.pointsY, 0, af, 0, this.numPoints);
/* 515:515 */      this.pointsY = af;
/* 516:    */    }
/* 517:    */    
/* 518:518 */    this.pointsX[this.numPoints] = x;
/* 519:519 */    this.pointsY[this.numPoints] = y;
/* 520:520 */    this.numPoints += 1;
/* 521:    */  }
/* 522:    */  
/* 530:    */  class Triangle
/* 531:    */  {
/* 532:    */    int[] v;
/* 533:    */    
/* 540:    */    Triangle(int i, int j, int k)
/* 541:    */    {
/* 542:542 */      this.v = new int[3];
/* 543:543 */      this.v[0] = i;
/* 544:544 */      this.v[1] = j;
/* 545:545 */      this.v[2] = k;
/* 546:    */    }
/* 547:    */  }
/* 548:    */  
/* 552:    */  class Edge
/* 553:    */  {
/* 554:    */    int v0;
/* 555:    */    
/* 557:    */    int v1;
/* 558:    */    
/* 560:    */    int t0;
/* 561:    */    
/* 563:    */    int t1;
/* 564:    */    
/* 566:    */    boolean suspect;
/* 567:    */    
/* 570:    */    Edge()
/* 571:    */    {
/* 572:572 */      this.v0 = -1;
/* 573:573 */      this.v1 = -1;
/* 574:574 */      this.t0 = -1;
/* 575:575 */      this.t1 = -1;
/* 576:    */    }
/* 577:    */  }
/* 578:    */  
/* 586:    */  class InternalException
/* 587:    */    extends Exception
/* 588:    */  {
/* 589:    */    public InternalException(String msg)
/* 590:    */    {
/* 591:591 */      super();
/* 592:    */    }
/* 593:    */  }
/* 594:    */  
/* 597:    */  public int getTriangleCount()
/* 598:    */  {
/* 599:599 */    return this.numTriangles;
/* 600:    */  }
/* 601:    */  
/* 604:    */  public float[] getTrianglePoint(int tri, int i)
/* 605:    */  {
/* 606:606 */    float xp = this.pointsX[this.triangles[tri].v[i]];
/* 607:607 */    float yp = this.pointsY[this.triangles[tri].v[i]];
/* 608:    */    
/* 609:609 */    return new float[] { xp, yp };
/* 610:    */  }
/* 611:    */  
/* 612:    */  public void startHole() {}
/* 613:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.NeatTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */