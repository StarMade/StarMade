/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*   3:    */import org.lwjgl.util.glu.GLUtessellator;
/*   4:    */import org.lwjgl.util.glu.GLUtessellatorCallback;
/*   5:    */import org.lwjgl.util.glu.GLUtessellatorCallbackAdapter;
/*   6:    */
/*  97:    */public class GLUtessellatorImpl
/*  98:    */  implements GLUtessellator
/*  99:    */{
/* 100:    */  public static final int TESS_MAX_CACHE = 100;
/* 101:    */  private int state;
/* 102:    */  private GLUhalfEdge lastEdge;
/* 103:    */  GLUmesh mesh;
/* 104:104 */  double[] normal = new double[3];
/* 105:105 */  double[] sUnit = new double[3];
/* 106:106 */  double[] tUnit = new double[3];
/* 107:    */  
/* 109:    */  private double relTolerance;
/* 110:    */  
/* 111:    */  int windingRule;
/* 112:    */  
/* 113:    */  boolean fatalError;
/* 114:    */  
/* 115:    */  Dict dict;
/* 116:    */  
/* 117:    */  PriorityQ pq;
/* 118:    */  
/* 119:    */  GLUvertex event;
/* 120:    */  
/* 121:    */  boolean flagBoundary;
/* 122:    */  
/* 123:    */  boolean boundaryOnly;
/* 124:    */  
/* 125:    */  GLUface lonelyTriList;
/* 126:    */  
/* 127:    */  private boolean flushCacheOnNextVertex;
/* 128:    */  
/* 129:    */  int cacheCount;
/* 130:    */  
/* 131:131 */  CachedVertex[] cache = new CachedVertex[100];
/* 132:    */  
/* 133:    */  private Object polygonData;
/* 134:    */  
/* 135:    */  private GLUtessellatorCallback callBegin;
/* 136:    */  
/* 137:    */  private GLUtessellatorCallback callEdgeFlag;
/* 138:    */  
/* 139:    */  private GLUtessellatorCallback callVertex;
/* 140:    */  
/* 141:    */  private GLUtessellatorCallback callEnd;
/* 142:    */  
/* 143:    */  private GLUtessellatorCallback callError;
/* 144:    */  
/* 145:    */  private GLUtessellatorCallback callCombine;
/* 146:    */  
/* 147:    */  private GLUtessellatorCallback callBeginData;
/* 148:    */  private GLUtessellatorCallback callEdgeFlagData;
/* 149:    */  private GLUtessellatorCallback callVertexData;
/* 150:    */  private GLUtessellatorCallback callEndData;
/* 151:    */  private GLUtessellatorCallback callErrorData;
/* 152:    */  private GLUtessellatorCallback callCombineData;
/* 153:    */  private static final double GLU_TESS_DEFAULT_TOLERANCE = 0.0D;
/* 154:154 */  private static GLUtessellatorCallback NULL_CB = new GLUtessellatorCallbackAdapter();
/* 155:    */  
/* 158:    */  public GLUtessellatorImpl()
/* 159:    */  {
/* 160:160 */    this.state = 0;
/* 161:    */    
/* 162:162 */    this.normal[0] = 0.0D;
/* 163:163 */    this.normal[1] = 0.0D;
/* 164:164 */    this.normal[2] = 0.0D;
/* 165:    */    
/* 166:166 */    this.relTolerance = 0.0D;
/* 167:167 */    this.windingRule = 100130;
/* 168:168 */    this.flagBoundary = false;
/* 169:169 */    this.boundaryOnly = false;
/* 170:    */    
/* 171:171 */    this.callBegin = NULL_CB;
/* 172:172 */    this.callEdgeFlag = NULL_CB;
/* 173:173 */    this.callVertex = NULL_CB;
/* 174:174 */    this.callEnd = NULL_CB;
/* 175:175 */    this.callError = NULL_CB;
/* 176:176 */    this.callCombine = NULL_CB;
/* 177:    */    
/* 179:179 */    this.callBeginData = NULL_CB;
/* 180:180 */    this.callEdgeFlagData = NULL_CB;
/* 181:181 */    this.callVertexData = NULL_CB;
/* 182:182 */    this.callEndData = NULL_CB;
/* 183:183 */    this.callErrorData = NULL_CB;
/* 184:184 */    this.callCombineData = NULL_CB;
/* 185:    */    
/* 186:186 */    this.polygonData = null;
/* 187:    */    
/* 188:188 */    for (int i = 0; i < this.cache.length; i++) {
/* 189:189 */      this.cache[i] = new CachedVertex();
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 193:    */  public static GLUtessellator gluNewTess()
/* 194:    */  {
/* 195:195 */    return new GLUtessellatorImpl();
/* 196:    */  }
/* 197:    */  
/* 200:    */  private void makeDormant()
/* 201:    */  {
/* 202:202 */    if (this.mesh != null) {
/* 203:203 */      Mesh.__gl_meshDeleteMesh(this.mesh);
/* 204:    */    }
/* 205:205 */    this.state = 0;
/* 206:206 */    this.lastEdge = null;
/* 207:207 */    this.mesh = null;
/* 208:    */  }
/* 209:    */  
/* 210:    */  private void requireState(int newState) {
/* 211:211 */    if (this.state != newState) gotoState(newState);
/* 212:    */  }
/* 213:    */  
/* 214:    */  private void gotoState(int newState) {
/* 215:215 */    while (this.state != newState)
/* 216:    */    {
/* 219:219 */      if (this.state < newState) {
/* 220:220 */        if (this.state == 0) {
/* 221:221 */          callErrorOrErrorData(100151);
/* 222:222 */          gluTessBeginPolygon(null);
/* 223:223 */        } else if (this.state == 1) {
/* 224:224 */          callErrorOrErrorData(100152);
/* 225:225 */          gluTessBeginContour();
/* 226:    */        }
/* 227:    */      }
/* 228:228 */      else if (this.state == 2) {
/* 229:229 */        callErrorOrErrorData(100154);
/* 230:230 */        gluTessEndContour();
/* 231:231 */      } else if (this.state == 1) {
/* 232:232 */        callErrorOrErrorData(100153);
/* 233:    */        
/* 234:234 */        makeDormant();
/* 235:    */      }
/* 236:    */    }
/* 237:    */  }
/* 238:    */  
/* 239:    */  public void gluDeleteTess()
/* 240:    */  {
/* 241:241 */    requireState(0);
/* 242:    */  }
/* 243:    */  
/* 244:    */  public void gluTessProperty(int which, double value) {
/* 245:245 */    switch (which) {
/* 246:    */    case 100142: 
/* 247:247 */      if ((value >= 0.0D) && (value <= 1.0D)) {
/* 248:248 */        this.relTolerance = value; return;
/* 249:    */      }
/* 250:    */      break;
/* 251:    */    case 100140: 
/* 252:252 */      int windingRule = (int)value;
/* 253:253 */      if (windingRule == value)
/* 254:    */      {
/* 255:255 */        switch (windingRule) {
/* 256:    */        case 100130: 
/* 257:    */        case 100131: 
/* 258:    */        case 100132: 
/* 259:    */        case 100133: 
/* 260:    */        case 100134: 
/* 261:261 */          this.windingRule = windingRule; return;
/* 262:    */        }
/* 263:    */        
/* 264:    */      }
/* 265:    */      
/* 266:    */      break;
/* 267:    */    case 100141: 
/* 268:268 */      this.boundaryOnly = (value != 0.0D);
/* 269:269 */      return;
/* 270:    */    
/* 271:    */    default: 
/* 272:272 */      callErrorOrErrorData(100900);
/* 273:273 */      return;
/* 274:    */    }
/* 275:275 */    callErrorOrErrorData(100901);
/* 276:    */  }
/* 277:    */  
/* 278:    */  public void gluGetTessProperty(int which, double[] value, int value_offset)
/* 279:    */  {
/* 280:280 */    switch (which)
/* 281:    */    {
/* 282:    */    case 100142: 
/* 283:283 */      assert ((0.0D <= this.relTolerance) && (this.relTolerance <= 1.0D));
/* 284:284 */      value[value_offset] = this.relTolerance;
/* 285:285 */      break;
/* 286:    */    case 100140: 
/* 287:287 */      assert ((this.windingRule == 100130) || (this.windingRule == 100131) || (this.windingRule == 100132) || (this.windingRule == 100133) || (this.windingRule == 100134));
/* 288:    */      
/* 292:292 */      value[value_offset] = this.windingRule;
/* 293:293 */      break;
/* 294:    */    case 100141: 
/* 295:295 */      assert ((this.boundaryOnly == true) || (!this.boundaryOnly));
/* 296:296 */      value[value_offset] = (this.boundaryOnly ? 1.0D : 0.0D);
/* 297:297 */      break;
/* 298:    */    default: 
/* 299:299 */      value[value_offset] = 0.0D;
/* 300:300 */      callErrorOrErrorData(100900);
/* 301:    */    }
/* 302:    */  }
/* 303:    */  
/* 304:    */  public void gluTessNormal(double x, double y, double z)
/* 305:    */  {
/* 306:306 */    this.normal[0] = x;
/* 307:307 */    this.normal[1] = y;
/* 308:308 */    this.normal[2] = z;
/* 309:    */  }
/* 310:    */  
/* 311:    */  public void gluTessCallback(int which, GLUtessellatorCallback aCallback) {
/* 312:312 */    switch (which) {
/* 313:    */    case 100100: 
/* 314:314 */      this.callBegin = (aCallback == null ? NULL_CB : aCallback);
/* 315:315 */      return;
/* 316:    */    case 100106: 
/* 317:317 */      this.callBeginData = (aCallback == null ? NULL_CB : aCallback);
/* 318:318 */      return;
/* 319:    */    case 100104: 
/* 320:320 */      this.callEdgeFlag = (aCallback == null ? NULL_CB : aCallback);
/* 321:    */      
/* 324:324 */      this.flagBoundary = (aCallback != null);
/* 325:325 */      return;
/* 326:    */    case 100110: 
/* 327:327 */      this.callEdgeFlagData = (this.callBegin = aCallback == null ? NULL_CB : aCallback);
/* 328:    */      
/* 331:331 */      this.flagBoundary = (aCallback != null);
/* 332:332 */      return;
/* 333:    */    case 100101: 
/* 334:334 */      this.callVertex = (aCallback == null ? NULL_CB : aCallback);
/* 335:335 */      return;
/* 336:    */    case 100107: 
/* 337:337 */      this.callVertexData = (aCallback == null ? NULL_CB : aCallback);
/* 338:338 */      return;
/* 339:    */    case 100102: 
/* 340:340 */      this.callEnd = (aCallback == null ? NULL_CB : aCallback);
/* 341:341 */      return;
/* 342:    */    case 100108: 
/* 343:343 */      this.callEndData = (aCallback == null ? NULL_CB : aCallback);
/* 344:344 */      return;
/* 345:    */    case 100103: 
/* 346:346 */      this.callError = (aCallback == null ? NULL_CB : aCallback);
/* 347:347 */      return;
/* 348:    */    case 100109: 
/* 349:349 */      this.callErrorData = (aCallback == null ? NULL_CB : aCallback);
/* 350:350 */      return;
/* 351:    */    case 100105: 
/* 352:352 */      this.callCombine = (aCallback == null ? NULL_CB : aCallback);
/* 353:353 */      return;
/* 354:    */    case 100111: 
/* 355:355 */      this.callCombineData = (aCallback == null ? NULL_CB : aCallback);
/* 356:356 */      return;
/* 357:    */    }
/* 358:    */    
/* 359:    */    
/* 361:361 */    callErrorOrErrorData(100900);
/* 362:    */  }
/* 363:    */  
/* 367:    */  private boolean addVertex(double[] coords, Object vertexData)
/* 368:    */  {
/* 369:369 */    GLUhalfEdge e = this.lastEdge;
/* 370:370 */    if (e == null)
/* 371:    */    {
/* 373:373 */      e = Mesh.__gl_meshMakeEdge(this.mesh);
/* 374:374 */      if (e == null) return false;
/* 375:375 */      if (!Mesh.__gl_meshSplice(e, e.Sym)) { return false;
/* 376:    */      }
/* 377:    */    }
/* 378:    */    else
/* 379:    */    {
/* 380:380 */      if (Mesh.__gl_meshSplitEdge(e) == null) return false;
/* 381:381 */      e = e.Lnext;
/* 382:    */    }
/* 383:    */    
/* 385:385 */    e.Org.data = vertexData;
/* 386:386 */    e.Org.coords[0] = coords[0];
/* 387:387 */    e.Org.coords[1] = coords[1];
/* 388:388 */    e.Org.coords[2] = coords[2];
/* 389:    */    
/* 395:395 */    e.winding = 1;
/* 396:396 */    e.Sym.winding = -1;
/* 397:    */    
/* 398:398 */    this.lastEdge = e;
/* 399:    */    
/* 400:400 */    return true;
/* 401:    */  }
/* 402:    */  
/* 403:    */  private void cacheVertex(double[] coords, Object vertexData) {
/* 404:404 */    if (this.cache[this.cacheCount] == null) {
/* 405:405 */      this.cache[this.cacheCount] = new CachedVertex();
/* 406:    */    }
/* 407:    */    
/* 408:408 */    CachedVertex v = this.cache[this.cacheCount];
/* 409:    */    
/* 410:410 */    v.data = vertexData;
/* 411:411 */    v.coords[0] = coords[0];
/* 412:412 */    v.coords[1] = coords[1];
/* 413:413 */    v.coords[2] = coords[2];
/* 414:414 */    this.cacheCount += 1;
/* 415:    */  }
/* 416:    */  
/* 417:    */  private boolean flushCache()
/* 418:    */  {
/* 419:419 */    CachedVertex[] v = this.cache;
/* 420:    */    
/* 421:421 */    this.mesh = Mesh.__gl_meshNewMesh();
/* 422:422 */    if (this.mesh == null) { return false;
/* 423:    */    }
/* 424:424 */    for (int i = 0; i < this.cacheCount; i++) {
/* 425:425 */      CachedVertex vertex = v[i];
/* 426:426 */      if (!addVertex(vertex.coords, vertex.data)) return false;
/* 427:    */    }
/* 428:428 */    this.cacheCount = 0;
/* 429:429 */    this.flushCacheOnNextVertex = false;
/* 430:    */    
/* 431:431 */    return true;
/* 432:    */  }
/* 433:    */  
/* 434:    */  public void gluTessVertex(double[] coords, int coords_offset, Object vertexData)
/* 435:    */  {
/* 436:436 */    boolean tooLarge = false;
/* 437:    */    
/* 438:438 */    double[] clamped = new double[3];
/* 439:    */    
/* 440:440 */    requireState(2);
/* 441:    */    
/* 442:442 */    if (this.flushCacheOnNextVertex) {
/* 443:443 */      if (!flushCache()) {
/* 444:444 */        callErrorOrErrorData(100902);
/* 445:445 */        return;
/* 446:    */      }
/* 447:447 */      this.lastEdge = null;
/* 448:    */    }
/* 449:449 */    for (int i = 0; i < 3; i++) {
/* 450:450 */      double x = coords[(i + coords_offset)];
/* 451:451 */      if (x < -1.0E+150D) {
/* 452:452 */        x = -1.0E+150D;
/* 453:453 */        tooLarge = true;
/* 454:    */      }
/* 455:455 */      if (x > 1.0E+150D) {
/* 456:456 */        x = 1.0E+150D;
/* 457:457 */        tooLarge = true;
/* 458:    */      }
/* 459:459 */      clamped[i] = x;
/* 460:    */    }
/* 461:461 */    if (tooLarge) {
/* 462:462 */      callErrorOrErrorData(100155);
/* 463:    */    }
/* 464:    */    
/* 465:465 */    if (this.mesh == null) {
/* 466:466 */      if (this.cacheCount < 100) {
/* 467:467 */        cacheVertex(clamped, vertexData);
/* 468:468 */        return;
/* 469:    */      }
/* 470:470 */      if (!flushCache()) {
/* 471:471 */        callErrorOrErrorData(100902);
/* 472:472 */        return;
/* 473:    */      }
/* 474:    */    }
/* 475:    */    
/* 476:476 */    if (!addVertex(clamped, vertexData)) {
/* 477:477 */      callErrorOrErrorData(100902);
/* 478:    */    }
/* 479:    */  }
/* 480:    */  
/* 481:    */  public void gluTessBeginPolygon(Object data)
/* 482:    */  {
/* 483:483 */    requireState(0);
/* 484:    */    
/* 485:485 */    this.state = 1;
/* 486:486 */    this.cacheCount = 0;
/* 487:487 */    this.flushCacheOnNextVertex = false;
/* 488:488 */    this.mesh = null;
/* 489:    */    
/* 490:490 */    this.polygonData = data;
/* 491:    */  }
/* 492:    */  
/* 493:    */  public void gluTessBeginContour()
/* 494:    */  {
/* 495:495 */    requireState(1);
/* 496:    */    
/* 497:497 */    this.state = 2;
/* 498:498 */    this.lastEdge = null;
/* 499:499 */    if (this.cacheCount > 0)
/* 500:    */    {
/* 504:504 */      this.flushCacheOnNextVertex = true;
/* 505:    */    }
/* 506:    */  }
/* 507:    */  
/* 508:    */  public void gluTessEndContour()
/* 509:    */  {
/* 510:510 */    requireState(2);
/* 511:511 */    this.state = 1;
/* 512:    */  }
/* 513:    */  
/* 514:    */  public void gluTessEndPolygon()
/* 515:    */  {
/* 516:    */    try
/* 517:    */    {
/* 518:518 */      requireState(1);
/* 519:519 */      this.state = 0;
/* 520:    */      
/* 521:521 */      if (this.mesh == null) {
/* 522:522 */        if (!this.flagBoundary)
/* 523:    */        {
/* 529:529 */          if (Render.__gl_renderCache(this)) {
/* 530:530 */            this.polygonData = null;
/* 531:531 */            return;
/* 532:    */          }
/* 533:    */        }
/* 534:534 */        if (!flushCache()) { throw new RuntimeException();
/* 535:    */        }
/* 536:    */      }
/* 537:    */      
/* 540:540 */      Normal.__gl_projectPolygon(this);
/* 541:    */      
/* 548:548 */      if (!Sweep.__gl_computeInterior(this)) {
/* 549:549 */        throw new RuntimeException();
/* 550:    */      }
/* 551:    */      
/* 552:552 */      GLUmesh mesh = this.mesh;
/* 553:553 */      if (!this.fatalError) {
/* 554:554 */        boolean rc = true;
/* 555:    */        
/* 560:560 */        if (this.boundaryOnly) {
/* 561:561 */          rc = TessMono.__gl_meshSetWindingNumber(mesh, 1, true);
/* 562:    */        } else {
/* 563:563 */          rc = TessMono.__gl_meshTessellateInterior(mesh);
/* 564:    */        }
/* 565:565 */        if (!rc) { throw new RuntimeException();
/* 566:    */        }
/* 567:567 */        Mesh.__gl_meshCheckMesh(mesh);
/* 568:    */        
/* 569:569 */        if ((this.callBegin != NULL_CB) || (this.callEnd != NULL_CB) || (this.callVertex != NULL_CB) || (this.callEdgeFlag != NULL_CB) || (this.callBeginData != NULL_CB) || (this.callEndData != NULL_CB) || (this.callVertexData != NULL_CB) || (this.callEdgeFlagData != NULL_CB))
/* 570:    */        {
/* 575:575 */          if (this.boundaryOnly) {
/* 576:576 */            Render.__gl_renderBoundary(this, mesh);
/* 577:    */          } else {
/* 578:578 */            Render.__gl_renderMesh(this, mesh);
/* 579:    */          }
/* 580:    */        }
/* 581:    */      }
/* 582:    */      
/* 596:596 */      Mesh.__gl_meshDeleteMesh(mesh);
/* 597:597 */      this.polygonData = null;
/* 598:598 */      mesh = null;
/* 599:    */    } catch (Exception e) {
/* 600:600 */      e.printStackTrace();
/* 601:601 */      callErrorOrErrorData(100902);
/* 602:    */    }
/* 603:    */  }
/* 604:    */  
/* 608:    */  public void gluBeginPolygon()
/* 609:    */  {
/* 610:610 */    gluTessBeginPolygon(null);
/* 611:611 */    gluTessBeginContour();
/* 612:    */  }
/* 613:    */  
/* 615:    */  public void gluNextContour(int type)
/* 616:    */  {
/* 617:617 */    gluTessEndContour();
/* 618:618 */    gluTessBeginContour();
/* 619:    */  }
/* 620:    */  
/* 621:    */  public void gluEndPolygon()
/* 622:    */  {
/* 623:623 */    gluTessEndContour();
/* 624:624 */    gluTessEndPolygon();
/* 625:    */  }
/* 626:    */  
/* 627:    */  void callBeginOrBeginData(int a) {
/* 628:628 */    if (this.callBeginData != NULL_CB) {
/* 629:629 */      this.callBeginData.beginData(a, this.polygonData);
/* 630:    */    } else
/* 631:631 */      this.callBegin.begin(a);
/* 632:    */  }
/* 633:    */  
/* 634:    */  void callVertexOrVertexData(Object a) {
/* 635:635 */    if (this.callVertexData != NULL_CB) {
/* 636:636 */      this.callVertexData.vertexData(a, this.polygonData);
/* 637:    */    } else
/* 638:638 */      this.callVertex.vertex(a);
/* 639:    */  }
/* 640:    */  
/* 641:    */  void callEdgeFlagOrEdgeFlagData(boolean a) {
/* 642:642 */    if (this.callEdgeFlagData != NULL_CB) {
/* 643:643 */      this.callEdgeFlagData.edgeFlagData(a, this.polygonData);
/* 644:    */    } else
/* 645:645 */      this.callEdgeFlag.edgeFlag(a);
/* 646:    */  }
/* 647:    */  
/* 648:    */  void callEndOrEndData() {
/* 649:649 */    if (this.callEndData != NULL_CB) {
/* 650:650 */      this.callEndData.endData(this.polygonData);
/* 651:    */    } else
/* 652:652 */      this.callEnd.end();
/* 653:    */  }
/* 654:    */  
/* 655:    */  void callCombineOrCombineData(double[] coords, Object[] vertexData, float[] weights, Object[] outData) {
/* 656:656 */    if (this.callCombineData != NULL_CB) {
/* 657:657 */      this.callCombineData.combineData(coords, vertexData, weights, outData, this.polygonData);
/* 658:    */    } else
/* 659:659 */      this.callCombine.combine(coords, vertexData, weights, outData);
/* 660:    */  }
/* 661:    */  
/* 662:    */  void callErrorOrErrorData(int a) {
/* 663:663 */    if (this.callErrorData != NULL_CB) {
/* 664:664 */      this.callErrorData.errorData(a, this.polygonData);
/* 665:    */    } else {
/* 666:666 */      this.callError.error(a);
/* 667:    */    }
/* 668:    */  }
/* 669:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUtessellatorImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */