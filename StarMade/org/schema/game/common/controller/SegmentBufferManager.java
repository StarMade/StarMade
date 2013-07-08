/*   1:    */package org.schema.game.common.controller;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   8:    */import jK;
/*   9:    */import jL;
/*  10:    */import jM;
/*  11:    */import jN;
/*  12:    */import jY;
/*  13:    */import java.io.PrintStream;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.HashSet;
/*  16:    */import java.util.Iterator;
/*  17:    */import java.util.Set;
/*  18:    */import java.util.concurrent.ThreadPoolExecutor;
/*  19:    */import javax.vecmath.Vector3f;
/*  20:    */import jz;
/*  21:    */import le;
/*  22:    */import org.schema.common.util.ByteUtil;
/*  23:    */import org.schema.game.common.data.element.ElementInformation;
/*  24:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  25:    */import org.schema.game.common.data.world.Segment;
/*  26:    */import org.schema.game.common.data.world.SegmentData;
/*  27:    */import org.schema.schine.network.StateInterface;
/*  28:    */import q;
/*  29:    */import vg;
/*  30:    */import xO;
/*  31:    */
/*  36:    */public class SegmentBufferManager
/*  37:    */  implements jL
/*  38:    */{
/*  39:    */  private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  40:    */  private xO jdField_a_of_type_XO;
/*  41:    */  private Long2ObjectRBTreeMap jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap;
/*  42:    */  private int jdField_a_of_type_Int;
/*  43:    */  private int jdField_b_of_type_Int;
/*  44:    */  private int c;
/*  45:    */  private long jdField_a_of_type_Long;
/*  46: 46 */  private final q jdField_a_of_type_Q = new q();
/*  47:    */  
/*  58:    */  private static long a(int paramInt1, int paramInt2, int paramInt3)
/*  59:    */  {
/*  60: 60 */    if ((!jdField_a_of_type_Boolean) && (paramInt1 >= 1048576)) throw new AssertionError();
/*  61: 61 */    if ((!jdField_a_of_type_Boolean) && (paramInt2 >= 1048576)) throw new AssertionError();
/*  62: 62 */    if ((!jdField_a_of_type_Boolean) && (paramInt3 >= 1048576)) { throw new AssertionError();
/*  63:    */    }
/*  64: 64 */    long l1 = paramInt3 << 42 & 0x0;
/*  65: 65 */    long l2 = paramInt2 << 21 & 0xFFE00000;
/*  66:    */    
/*  70: 70 */    return paramInt1 + l2 + l1;
/*  71:    */  }
/*  72:    */  
/*  97:    */  public static void main(String[] paramArrayOfString)
/*  98:    */  {
/*  99: 99 */    System.err.println("-8796093022208 MBytes needed if array 12");
/* 100:    */    
/* 102:102 */    paramArrayOfString = new HashSet();
/* 103:    */    
/* 104:104 */    for (int i = -1000; i < 100; 
/* 105:    */        
/* 106:106 */        i++) {
/* 107:107 */      for (int j = -100; j < 100; j++) {
/* 108:108 */        for (int k = -100; k < 100; k++)
/* 109:    */        {
/* 111:111 */          long l = a(k, j, i);
/* 112:    */          
/* 114:114 */          if ((!jdField_a_of_type_Boolean) && (paramArrayOfString.contains(Long.valueOf(l)))) { throw new AssertionError();
/* 115:    */          }
/* 116:116 */          paramArrayOfString.add(Long.valueOf(l));
/* 117:    */        }
/* 118:    */      }
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 122:    */  public SegmentBufferManager(SegmentController paramSegmentController) {
/* 123:123 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/* 124:124 */    this.jdField_a_of_type_XO = new xO();
/* 125:125 */    this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap = new Long2ObjectRBTreeMap();
/* 126:    */  }
/* 127:    */  
/* 129:    */  public final void a(Segment paramSegment)
/* 130:    */  {
/* 131:131 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 132:132 */      a(paramSegment.jdField_a_of_type_Q, true).a(paramSegment);
/* 133:133 */      if ((!jdField_a_of_type_Boolean) && (a(paramSegment.jdField_a_of_type_Q) == null)) throw new AssertionError();
/* 134:134 */      return;
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 196:    */  public final int a()
/* 197:    */  {
/* 198:198 */    Long2ObjectRBTreeMap localLong2ObjectRBTreeMap1 = new Long2ObjectRBTreeMap();
/* 199:199 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 200:200 */      localLong2ObjectRBTreeMap1.putAll(this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap);
/* 201:201 */      this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.clear();
/* 202:202 */      this.jdField_a_of_type_Int = 0;
/* 203:203 */      this.jdField_b_of_type_Int = 0;
/* 204:    */    }
/* 205:205 */    if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().isTerminating()) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().isTerminated())) {
/* 206:206 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().execute(new jN(this, localLong2ObjectRBTreeMap2));
/* 207:    */    }
/* 208:    */    
/* 229:229 */    return 0;
/* 230:    */  }
/* 231:    */  
/* 233:    */  public final boolean a(int paramInt1, int paramInt2, int paramInt3)
/* 234:    */  {
/* 235:    */    jL localjL;
/* 236:    */    
/* 237:237 */    if ((localjL = a(paramInt1, paramInt2, paramInt3, false)) != null) return localjL.a(paramInt1, paramInt2, paramInt3); return false;
/* 238:    */  }
/* 239:    */  
/* 241:    */  public final boolean a(q paramq)
/* 242:    */  {
/* 243:    */    jL localjL;
/* 244:244 */    if ((localjL = a(paramq, false)) != null) return localjL.a(paramq); return false;
/* 245:    */  }
/* 246:    */  
/* 255:    */  public final void c()
/* 256:    */  {
/* 257:257 */    this.jdField_a_of_type_Int = Math.max(0, this.jdField_a_of_type_Int - 1);
/* 258:    */  }
/* 259:    */  
/* 260:    */  public final void d()
/* 261:    */  {
/* 262:262 */    this.jdField_b_of_type_Int = Math.max(0, this.jdField_b_of_type_Int - 1);
/* 263:    */  }
/* 264:    */  
/* 266:    */  public final boolean b(q paramq)
/* 267:    */  {
/* 268:    */    jL localjL;
/* 269:269 */    if ((localjL = a(paramq, false)) == null) {
/* 270:270 */      return false;
/* 271:    */    }
/* 272:272 */    return localjL.b(paramq);
/* 273:    */  }
/* 274:    */  
/* 277:    */  public final Segment a(int paramInt1, int paramInt2, int paramInt3)
/* 278:    */  {
/* 279:    */    jL localjL;
/* 280:    */    
/* 281:281 */    if ((localjL = a(paramInt1, paramInt2, paramInt3, false)) != null) {
/* 282:282 */      return localjL.a(paramInt1, paramInt2, paramInt3);
/* 283:    */    }
/* 284:284 */    return null;
/* 285:    */  }
/* 286:    */  
/* 287:    */  public final Segment a(q paramq)
/* 288:    */  {
/* 289:289 */    return a(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.c);
/* 290:    */  }
/* 291:    */  
/* 292:    */  public final xO a() {
/* 293:293 */    return this.jdField_a_of_type_XO;
/* 294:    */  }
/* 295:    */  
/* 296:296 */  public final Long2ObjectRBTreeMap a() { return this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap; }
/* 297:    */  
/* 298:    */  private jL a(int paramInt1, int paramInt2, int paramInt3, boolean arg4)
/* 299:    */  {
/* 300:300 */    int i = ByteUtil.c(ByteUtil.b(paramInt1));int j = ByteUtil.c(ByteUtil.b(paramInt2));int k = ByteUtil.c(ByteUtil.b(paramInt3));long l = a(i, j, k);
/* 301:    */    
/* 302:302 */    Object localObject = (jL)this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(l);
/* 303:    */    
/* 304:304 */    if ((??? != 0) && (localObject == null)) {
/* 305:305 */      synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
/* 306:    */      {
/* 307:307 */        if (!this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.containsKey(l))
/* 308:    */        {
/* 312:312 */          paramInt1 = ByteUtil.c(ByteUtil.b(paramInt1)) << 3;
/* 313:313 */          paramInt2 = ByteUtil.c(ByteUtil.b(paramInt2)) << 3;
/* 314:314 */          paramInt3 = ByteUtil.c(ByteUtil.b(paramInt3)) << 3;
/* 315:    */          
/* 318:318 */          localObject = new q(paramInt1, paramInt2, paramInt3);
/* 319:319 */          (
/* 320:320 */            paramInt1 = new q(paramInt1, paramInt2, paramInt3)).a(8, 8, 8);
/* 321:321 */          localObject = new jK(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, (q)localObject, paramInt1, this);
/* 322:322 */          this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.put(l, localObject);
/* 323:323 */          if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(l) == null)) { throw new AssertionError();
/* 324:    */          }
/* 325:    */        }
/* 326:    */      }
/* 327:    */    }
/* 328:328 */    return localObject;
/* 329:    */  }
/* 330:    */  
/* 341:    */  private jL a(q paramq, boolean paramBoolean)
/* 342:    */  {
/* 343:343 */    return a(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.c, paramBoolean);
/* 344:    */  }
/* 345:    */  
/* 351:    */  public final long a()
/* 352:    */  {
/* 353:353 */    return this.jdField_a_of_type_Long;
/* 354:    */  }
/* 355:    */  
/* 356:356 */  private final q jdField_b_of_type_Q = new q();
/* 357:    */  
/* 358:    */  public final long a(q paramq1, q paramq2)
/* 359:    */  {
/* 360:360 */    this.jdField_a_of_type_Q.jdField_a_of_type_Int = ByteUtil.c(ByteUtil.b(paramq1.jdField_a_of_type_Int));
/* 361:361 */    this.jdField_a_of_type_Q.jdField_b_of_type_Int = ByteUtil.c(ByteUtil.b(paramq1.jdField_b_of_type_Int));
/* 362:362 */    this.jdField_a_of_type_Q.c = ByteUtil.c(ByteUtil.b(paramq1.c));
/* 363:    */    
/* 364:364 */    this.jdField_b_of_type_Q.jdField_a_of_type_Int = ByteUtil.c(ByteUtil.b(paramq2.jdField_a_of_type_Int));
/* 365:365 */    this.jdField_b_of_type_Q.jdField_b_of_type_Int = ByteUtil.c(ByteUtil.b(paramq2.jdField_b_of_type_Int));
/* 366:366 */    this.jdField_b_of_type_Q.c = ByteUtil.c(ByteUtil.b(paramq2.c));
/* 367:    */    
/* 368:368 */    this.jdField_b_of_type_Q.jdField_a_of_type_Int += 1;
/* 369:369 */    this.jdField_b_of_type_Q.jdField_b_of_type_Int += 1;
/* 370:370 */    this.jdField_b_of_type_Q.c += 1;
/* 371:    */    
/* 372:372 */    if (this.jdField_a_of_type_Q.jdField_a_of_type_Int == this.jdField_b_of_type_Q.jdField_a_of_type_Int) {
/* 373:373 */      this.jdField_b_of_type_Q.jdField_a_of_type_Int += 1;
/* 374:    */    }
/* 375:375 */    if (this.jdField_a_of_type_Q.jdField_b_of_type_Int == this.jdField_b_of_type_Q.jdField_b_of_type_Int) {
/* 376:376 */      this.jdField_b_of_type_Q.jdField_b_of_type_Int += 1;
/* 377:    */    }
/* 378:378 */    if (this.jdField_a_of_type_Q.c == this.jdField_b_of_type_Q.c) {
/* 379:379 */      this.jdField_b_of_type_Q.c += 1;
/* 380:    */    }
/* 381:    */    
/* 382:382 */    long l = 0L;
/* 383:383 */    for (paramq1 = this.jdField_a_of_type_Q.jdField_a_of_type_Int; paramq1 < this.jdField_b_of_type_Q.jdField_a_of_type_Int; 
/* 384:    */        
/* 402:402 */        paramq1 += 8) {
/* 403:403 */      for (paramq2 = this.jdField_a_of_type_Q.jdField_b_of_type_Int; paramq2 < this.jdField_b_of_type_Q.jdField_b_of_type_Int; paramq2 += 8) {
/* 404:404 */        for (int i = this.jdField_a_of_type_Q.c; i < this.jdField_b_of_type_Q.c; i += 8)
/* 405:    */        {
/* 406:    */          jL localjL;
/* 407:    */          
/* 408:408 */          if ((localjL = (jL)this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(a(paramq1, paramq2, i))) != null) {
/* 409:409 */            l = Math.max(l, localjL.a());
/* 410:    */          }
/* 411:    */        }
/* 412:    */      }
/* 413:    */    }
/* 414:    */    
/* 415:415 */    return l;
/* 416:    */  }
/* 417:    */  
/* 421:    */  public final le a(q paramq, boolean paramBoolean)
/* 422:    */  {
/* 423:    */    jL localjL;
/* 424:    */    
/* 427:427 */    if ((localjL = a(paramq, paramBoolean)) == null) {
/* 428:428 */      return null;
/* 429:    */    }
/* 430:430 */    return localjL.a(paramq, paramBoolean);
/* 431:    */  }
/* 432:    */  
/* 437:    */  public final le a(q paramq, boolean paramBoolean, le paramle)
/* 438:    */  {
/* 439:    */    jL localjL;
/* 440:    */    
/* 443:443 */    if ((localjL = a(paramq, paramBoolean)) == null) {
/* 444:444 */      return null;
/* 445:    */    }
/* 446:446 */    return localjL.a(paramq, paramBoolean, paramle);
/* 447:    */  }
/* 448:    */  
/* 451:    */  public final SegmentController a()
/* 452:    */  {
/* 453:453 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 454:    */  }
/* 455:    */  
/* 456:    */  public final int c()
/* 457:    */  {
/* 458:458 */    return this.jdField_b_of_type_Int;
/* 459:    */  }
/* 460:    */  
/* 465:    */  public final boolean a(q paramq, jM paramjM)
/* 466:    */  {
/* 467:    */    jL localjL;
/* 468:    */    
/* 472:472 */    if ((localjL = a(paramq, false)) != null) {
/* 473:473 */      return localjL.a(paramq, paramjM);
/* 474:    */    }
/* 475:475 */    return true;
/* 476:    */  }
/* 477:    */  
/* 482:    */  public final boolean a(int paramInt1, int paramInt2, int paramInt3, jM paramjM)
/* 483:    */  {
/* 484:    */    jL localjL;
/* 485:    */    
/* 489:489 */    if ((localjL = a(paramInt1, paramInt2, paramInt3, false)) != null) {
/* 490:490 */      return localjL.a(paramInt1, paramInt2, paramInt3, paramjM);
/* 491:    */    }
/* 492:492 */    return true;
/* 493:    */  }
/* 494:    */  
/* 495:    */  public final void e() {
/* 496:496 */    this.jdField_a_of_type_Int += 1;
/* 497:    */  }
/* 498:    */  
/* 499:    */  public final void a(int paramInt, Segment paramSegment) {
/* 500:    */    jL localjL;
/* 501:501 */    if ((localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null) {
/* 502:502 */      localjL.a(paramInt, paramSegment);
/* 503:    */    }
/* 504:    */  }
/* 505:    */  
/* 507:    */  public final jz a(q paramq)
/* 508:    */  {
/* 509:509 */    if (((paramq = a(paramq, false)) != null) && (paramq.a() != 0)) {
/* 510:    */      jz localjz;
/* 511:511 */      SegmentBufferManager localSegmentBufferManager = this;q localq1 = paramq;short s = localq1.a();q localq2 = null;(paramq = localjz = new jz()).jdField_a_of_type_Short = s; if ((paramq.jdField_a_of_type_Short == 0) || (!ElementKeyMap.getInfo(paramq.jdField_a_of_type_Short).getControlledBy().isEmpty())) { paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(localq1);localq1 = new q(); for (localq2 = new q(); !paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.isEmpty();) { le localle1; q localq3 = (localle1 = (le)paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.dequeue()).a(new q());paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localq3); if (localle1.a() == paramq.jdField_a_of_type_Short) { paramq.jdField_a_of_type_JavaUtilArrayList.add(localq3); for (int i = 0; i < 6; i++) { localle1.a(localq1);localq1.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]); le localle2; if (((localle2 = localSegmentBufferManager.a(localq1, false, new le())) == null) || (localle2.a() == 0) || (localle2.a() != paramq.jdField_a_of_type_Short)) { paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localle2.a(new q())); } else if (!paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(localle2.a(localq2))) { paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localle2.a(new q()));paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(localle2); } } } } }
/* 512:512 */      return localjz;
/* 513:    */    }
/* 514:514 */    return null;
/* 515:    */  }
/* 516:    */  
/* 517:    */  public final void f() {
/* 518:518 */    this.jdField_b_of_type_Int += 1;
/* 519:    */  }
/* 520:    */  
/* 521:    */  public final boolean b() {
/* 522:522 */    return this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.isEmpty();
/* 523:    */  }
/* 524:    */  
/* 526:    */  public final boolean a(jM paramjM, q paramq1, q paramq2)
/* 527:    */  {
/* 528:528 */    q localq = new q();
/* 529:    */    
/* 530:530 */    for (int i = paramq1.jdField_a_of_type_Int; i < paramq2.jdField_a_of_type_Int; 
/* 531:    */        
/* 545:545 */        i += 16) {
/* 546:546 */      for (int j = paramq1.jdField_b_of_type_Int; j < paramq2.jdField_b_of_type_Int; j += 16) {
/* 547:547 */        for (int k = paramq1.c; k < paramq2.c; k += 16) {
/* 548:548 */          localq.b(i, j, k);
/* 549:    */          boolean bool;
/* 550:550 */          if (!(bool = a(localq, paramjM))) {
/* 551:551 */            return bool;
/* 552:    */          }
/* 553:    */        }
/* 554:    */      }
/* 555:    */    }
/* 556:    */    
/* 557:557 */    return true;
/* 558:    */  }
/* 559:    */  
/* 560:    */  public final boolean a(jM paramjM, boolean paramBoolean) { boolean bool;
/* 561:561 */    if (paramBoolean) { Iterator localIterator;
/* 562:562 */      synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 563:563 */        for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); localIterator.hasNext();)
/* 564:    */        {
/* 565:565 */          if (!(bool = ((jL)localIterator.next()).a(paramjM, paramBoolean)))
/* 566:    */          {
/* 567:567 */            return bool;
/* 568:    */          }
/* 569:    */        }
/* 570:    */      }
/* 571:    */    }
/* 572:572 */    for (??? = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); ((Iterator)???).hasNext();)
/* 573:    */    {
/* 574:574 */      if (!(bool = ((jL)((Iterator)???).next()).a(paramjM, paramBoolean)))
/* 575:    */      {
/* 576:576 */        return bool;
/* 577:    */      }
/* 578:    */    }
/* 579:    */    
/* 581:581 */    return true;
/* 582:    */  }
/* 583:    */  
/* 585:    */  public final boolean b(jM paramjM, boolean paramBoolean)
/* 586:    */  {
/* 587:    */    boolean bool;
/* 588:588 */    if (paramBoolean) { Iterator localIterator;
/* 589:589 */      synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 590:590 */        for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); localIterator.hasNext();)
/* 591:    */        {
/* 592:592 */          if (!(bool = ((jL)localIterator.next()).b(paramjM, paramBoolean)))
/* 593:    */          {
/* 594:594 */            return bool;
/* 595:    */          }
/* 596:    */        }
/* 597:    */      }
/* 598:    */    }
/* 599:599 */    for (??? = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); ((Iterator)???).hasNext();)
/* 600:    */    {
/* 601:601 */      if (!(bool = ((jL)((Iterator)???).next()).b(paramjM, paramBoolean)))
/* 602:    */      {
/* 603:603 */        return bool;
/* 604:    */      }
/* 605:    */    }
/* 606:    */    
/* 608:608 */    return true;
/* 609:    */  }
/* 610:    */  
/* 618:    */  public final boolean b(jM paramjM, q paramq1, q paramq2)
/* 619:    */  {
/* 620:620 */    for (int i = paramq1.jdField_a_of_type_Int; i < paramq2.jdField_a_of_type_Int; 
/* 621:    */        
/* 635:635 */        i += 16) {
/* 636:636 */      for (int j = paramq1.jdField_b_of_type_Int; j < paramq2.jdField_b_of_type_Int; j += 16) {
/* 637:637 */        for (int k = paramq1.c; k < paramq2.c; k += 16) {
/* 638:    */          boolean bool;
/* 639:639 */          if (!(bool = a(i, j, k, paramjM))) {
/* 640:640 */            return bool;
/* 641:    */          }
/* 642:    */        }
/* 643:    */      }
/* 644:    */    }
/* 645:    */    
/* 648:648 */    return true;
/* 649:    */  }
/* 650:    */  
/* 654:    */  public final void a(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/* 655:    */  {
/* 656:    */    jL localjL;
/* 657:    */    
/* 660:660 */    if ((localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null) {
/* 661:661 */      localjL.a(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 662:    */    }
/* 663:    */  }
/* 664:    */  
/* 668:    */  public final void b(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/* 669:    */  {
/* 670:    */    jL localjL;
/* 671:    */    
/* 673:673 */    if ((localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null) {
/* 674:674 */      localjL.b(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 675:    */    }
/* 676:    */  }
/* 677:    */  
/* 723:    */  public final void a()
/* 724:    */  {
/* 725:725 */    Iterator localIterator = null; synchronized (this.jdField_a_of_type_XO) {
/* 726:726 */      this.jdField_a_of_type_XO.a();
/* 727:727 */      for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); localIterator.hasNext();) {
/* 728:728 */        ((jL)localIterator.next()).a();
/* 729:    */      }
/* 730:    */      
/* 731:    */      return;
/* 732:    */    }
/* 733:    */  }
/* 734:    */  
/* 736:    */  public final void a(SegmentData paramSegmentData)
/* 737:    */  {
/* 738:    */    jL localjL;
/* 739:    */    
/* 740:740 */    if (((localjL = a(paramSegmentData.getSegment().jdField_a_of_type_Q, false)) != null) && (
/* 741:741 */      (localjL.a().a.x == this.jdField_a_of_type_XO.a.x) || (localjL.a().a.y == this.jdField_a_of_type_XO.a.y) || (localjL.a().a.z == this.jdField_a_of_type_XO.a.z) || (localjL.a().b.x == this.jdField_a_of_type_XO.b.x) || (localjL.a().b.y == this.jdField_a_of_type_XO.b.y) || (localjL.a().b.z == this.jdField_a_of_type_XO.b.z)))
/* 742:    */    {
/* 750:750 */      localjL.a(paramSegmentData);
/* 751:    */    }
/* 752:    */  }
/* 753:    */  
/* 771:    */  public final int d()
/* 772:    */  {
/* 773:773 */    return this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.size();
/* 774:    */  }
/* 775:    */  
/* 782:    */  public final void a(xO paramxO)
/* 783:    */  {
/* 784:784 */    this.jdField_a_of_type_XO.a(paramxO.a, paramxO.b);
/* 785:785 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.aabbRecalcFlag();
/* 786:    */  }
/* 787:    */  
/* 790:    */  public final void b(Segment paramSegment)
/* 791:    */  {
/* 792:    */    jL localjL;
/* 793:    */    
/* 794:794 */    if ((localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null) {
/* 795:795 */      localjL.b(paramSegment);
/* 796:    */    }
/* 797:    */  }
/* 798:    */  
/* 801:    */  public final void g()
/* 802:    */  {
/* 803:803 */    this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 804:    */  }
/* 805:    */  
/* 806:    */  public final void b() {
/* 807:807 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a();
/* 808:    */    
/* 809:809 */    this.c = this.jdField_b_of_type_Int;
/* 810:    */    
/* 811:811 */    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 812:812 */      vg.jdField_a_of_type_Int += this.c;return;
/* 813:    */    }
/* 814:    */    
/* 815:815 */    ct.jdField_a_of_type_Int += this.c;
/* 816:    */  }
/* 817:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.SegmentBufferManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */