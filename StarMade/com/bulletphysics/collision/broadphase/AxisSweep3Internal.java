/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.BulletStats;
/*   5:    */import com.bulletphysics.linearmath.MiscUtil;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import com.bulletphysics.util.ObjectArrayList;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */
/* 122:    */public abstract class AxisSweep3Internal
/* 123:    */  extends BroadphaseInterface
/* 124:    */{
/* 125:    */  protected int bpHandleMask;
/* 126:    */  protected int handleSentinel;
/* 127:    */  protected final Vector3f worldAabbMin;
/* 128:    */  protected final Vector3f worldAabbMax;
/* 129:    */  protected final Vector3f quantize;
/* 130:    */  protected int numHandles;
/* 131:    */  protected int maxHandles;
/* 132:    */  protected Handle[] pHandles;
/* 133:    */  protected int firstFreeHandle;
/* 134:    */  protected EdgeArray[] pEdges;
/* 135:    */  protected OverlappingPairCache pairCache;
/* 136:    */  protected OverlappingPairCallback userPairCallback;
/* 137:    */  protected boolean ownsPairCache;
/* 138:    */  protected int invalidPair;
/* 139:    */  protected int mask;
/* 140:    */  
/* 141:    */  AxisSweep3Internal(Vector3f arg1, Vector3f arg2, int arg3, int arg4, int arg5, OverlappingPairCache arg6) {}
/* 142:    */  
/* 143:    */  protected int allocHandle()
/* 144:    */  {
/* 145:145 */    assert (this.firstFreeHandle != 0);
/* 146:    */    
/* 147:147 */    int handle = this.firstFreeHandle;
/* 148:148 */    this.firstFreeHandle = getHandle(handle).getNextFree();
/* 149:149 */    this.numHandles += 1;
/* 150:    */    
/* 151:151 */    return handle;
/* 152:    */  }
/* 153:    */  
/* 154:    */  protected void freeHandle(int handle) {
/* 155:155 */    assert ((handle > 0) && (handle < this.maxHandles));
/* 156:    */    
/* 157:157 */    getHandle(handle).setNextFree(this.firstFreeHandle);
/* 158:158 */    this.firstFreeHandle = handle;
/* 159:    */    
/* 160:160 */    this.numHandles -= 1;
/* 161:    */  }
/* 162:    */  
/* 164:    */  protected boolean testOverlap(int ignoreAxis, Handle pHandleA, Handle pHandleB)
/* 165:    */  {
/* 166:166 */    for (int axis = 0; axis < 3; axis++) {
/* 167:167 */      if ((axis != ignoreAxis) && (
/* 168:168 */        (pHandleA.getMaxEdges(axis) < pHandleB.getMinEdges(axis)) || (pHandleB.getMaxEdges(axis) < pHandleA.getMinEdges(axis))))
/* 169:    */      {
/* 170:170 */        return false;
/* 171:    */      }
/* 172:    */    }
/* 173:    */    
/* 187:187 */    return true;
/* 188:    */  }
/* 189:    */  
/* 193:    */  protected void quantize(int[] arg1, Vector3f arg2, int arg3)
/* 194:    */  {
/* 195:195 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
/* 196:    */      
/* 197:197 */      VectorUtil.setMax(clampedPoint, this.worldAabbMin);
/* 198:198 */      VectorUtil.setMin(clampedPoint, this.worldAabbMax);
/* 199:    */      
/* 200:200 */      Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 201:201 */      v.sub(clampedPoint, this.worldAabbMin);
/* 202:202 */      VectorUtil.mul(v, v, this.quantize);
/* 203:    */      
/* 204:204 */      out[0] = (((int)v.x & this.bpHandleMask | isMax) & this.mask);
/* 205:205 */      out[1] = (((int)v.y & this.bpHandleMask | isMax) & this.mask);
/* 206:206 */      out[2] = (((int)v.z & this.bpHandleMask | isMax) & this.mask);
/* 207:207 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 211:211 */  protected void sortMinDown(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps) { EdgeArray edgeArray = this.pEdges[axis];
/* 212:212 */    int pEdge_idx = edge;
/* 213:213 */    int pPrev_idx = pEdge_idx - 1;
/* 214:    */    
/* 215:215 */    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/* 216:    */    
/* 217:217 */    while (edgeArray.getPos(pEdge_idx) < edgeArray.getPos(pPrev_idx)) {
/* 218:218 */      Handle pHandlePrev = getHandle(edgeArray.getHandle(pPrev_idx));
/* 219:    */      
/* 220:220 */      if (edgeArray.isMax(pPrev_idx) != 0)
/* 221:    */      {
/* 222:222 */        if ((updateOverlaps) && (testOverlap(axis, pHandleEdge, pHandlePrev))) {
/* 223:223 */          this.pairCache.addOverlappingPair(pHandleEdge, pHandlePrev);
/* 224:224 */          if (this.userPairCallback != null) {
/* 225:225 */            this.userPairCallback.addOverlappingPair(pHandleEdge, pHandlePrev);
/* 226:    */          }
/* 227:    */        }
/* 228:    */        
/* 231:231 */        pHandlePrev.incMaxEdges(axis);
/* 232:    */      }
/* 233:    */      else {
/* 234:234 */        pHandlePrev.incMinEdges(axis);
/* 235:    */      }
/* 236:236 */      pHandleEdge.decMinEdges(axis);
/* 237:    */      
/* 239:239 */      edgeArray.swap(pEdge_idx, pPrev_idx);
/* 240:    */      
/* 242:242 */      pEdge_idx--;
/* 243:243 */      pPrev_idx--;
/* 244:    */    }
/* 245:    */  }
/* 246:    */  
/* 251:    */  protected void sortMinUp(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
/* 252:    */  {
/* 253:253 */    EdgeArray edgeArray = this.pEdges[axis];
/* 254:254 */    int pEdge_idx = edge;
/* 255:255 */    int pNext_idx = pEdge_idx + 1;
/* 256:256 */    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/* 257:    */    
/* 258:258 */    while ((edgeArray.getHandle(pNext_idx) != 0) && (edgeArray.getPos(pEdge_idx) >= edgeArray.getPos(pNext_idx))) {
/* 259:259 */      Handle pHandleNext = getHandle(edgeArray.getHandle(pNext_idx));
/* 260:    */      
/* 261:261 */      if (edgeArray.isMax(pNext_idx) != 0)
/* 262:    */      {
/* 263:263 */        if (updateOverlaps) {
/* 264:264 */          Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
/* 265:265 */          Handle handle1 = getHandle(edgeArray.getHandle(pNext_idx));
/* 266:    */          
/* 267:267 */          this.pairCache.removeOverlappingPair(handle0, handle1, dispatcher);
/* 268:268 */          if (this.userPairCallback != null) {
/* 269:269 */            this.userPairCallback.removeOverlappingPair(handle0, handle1, dispatcher);
/* 270:    */          }
/* 271:    */        }
/* 272:    */        
/* 274:274 */        pHandleNext.decMaxEdges(axis);
/* 275:    */      }
/* 276:    */      else {
/* 277:277 */        pHandleNext.decMinEdges(axis);
/* 278:    */      }
/* 279:279 */      pHandleEdge.incMinEdges(axis);
/* 280:    */      
/* 282:282 */      edgeArray.swap(pEdge_idx, pNext_idx);
/* 283:    */      
/* 285:285 */      pEdge_idx++;
/* 286:286 */      pNext_idx++;
/* 287:    */    }
/* 288:    */  }
/* 289:    */  
/* 290:    */  protected void sortMaxDown(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
/* 291:    */  {
/* 292:292 */    EdgeArray edgeArray = this.pEdges[axis];
/* 293:293 */    int pEdge_idx = edge;
/* 294:294 */    int pPrev_idx = pEdge_idx - 1;
/* 295:295 */    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/* 296:    */    
/* 297:297 */    while (edgeArray.getPos(pEdge_idx) < edgeArray.getPos(pPrev_idx)) {
/* 298:298 */      Handle pHandlePrev = getHandle(edgeArray.getHandle(pPrev_idx));
/* 299:    */      
/* 300:300 */      if (edgeArray.isMax(pPrev_idx) == 0)
/* 301:    */      {
/* 302:302 */        if (updateOverlaps)
/* 303:    */        {
/* 304:304 */          Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
/* 305:305 */          Handle handle1 = getHandle(edgeArray.getHandle(pPrev_idx));
/* 306:306 */          this.pairCache.removeOverlappingPair(handle0, handle1, dispatcher);
/* 307:307 */          if (this.userPairCallback != null) {
/* 308:308 */            this.userPairCallback.removeOverlappingPair(handle0, handle1, dispatcher);
/* 309:    */          }
/* 310:    */        }
/* 311:    */        
/* 313:313 */        pHandlePrev.incMinEdges(axis);
/* 314:    */      }
/* 315:    */      else {
/* 316:316 */        pHandlePrev.incMaxEdges(axis);
/* 317:    */      }
/* 318:318 */      pHandleEdge.decMaxEdges(axis);
/* 319:    */      
/* 321:321 */      edgeArray.swap(pEdge_idx, pPrev_idx);
/* 322:    */      
/* 324:324 */      pEdge_idx--;
/* 325:325 */      pPrev_idx--;
/* 326:    */    }
/* 327:    */  }
/* 328:    */  
/* 333:    */  protected void sortMaxUp(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
/* 334:    */  {
/* 335:335 */    EdgeArray edgeArray = this.pEdges[axis];
/* 336:336 */    int pEdge_idx = edge;
/* 337:337 */    int pNext_idx = pEdge_idx + 1;
/* 338:338 */    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/* 339:    */    
/* 340:340 */    while ((edgeArray.getHandle(pNext_idx) != 0) && (edgeArray.getPos(pEdge_idx) >= edgeArray.getPos(pNext_idx))) {
/* 341:341 */      Handle pHandleNext = getHandle(edgeArray.getHandle(pNext_idx));
/* 342:    */      
/* 343:343 */      if (edgeArray.isMax(pNext_idx) == 0)
/* 344:    */      {
/* 345:345 */        if ((updateOverlaps) && (testOverlap(axis, pHandleEdge, pHandleNext))) {
/* 346:346 */          Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
/* 347:347 */          Handle handle1 = getHandle(edgeArray.getHandle(pNext_idx));
/* 348:348 */          this.pairCache.addOverlappingPair(handle0, handle1);
/* 349:349 */          if (this.userPairCallback != null) {
/* 350:350 */            this.userPairCallback.addOverlappingPair(handle0, handle1);
/* 351:    */          }
/* 352:    */        }
/* 353:    */        
/* 355:355 */        pHandleNext.decMinEdges(axis);
/* 356:    */      }
/* 357:    */      else {
/* 358:358 */        pHandleNext.decMaxEdges(axis);
/* 359:    */      }
/* 360:360 */      pHandleEdge.incMaxEdges(axis);
/* 361:    */      
/* 363:363 */      edgeArray.swap(pEdge_idx, pNext_idx);
/* 364:    */      
/* 366:366 */      pEdge_idx++;
/* 367:367 */      pNext_idx++;
/* 368:    */    }
/* 369:    */  }
/* 370:    */  
/* 371:    */  public int getNumHandles() {
/* 372:372 */    return this.numHandles;
/* 373:    */  }
/* 374:    */  
/* 375:    */  public void calculateOverlappingPairs(Dispatcher dispatcher) {
/* 376:376 */    if (this.pairCache.hasDeferredRemoval()) {
/* 377:377 */      ObjectArrayList<BroadphasePair> overlappingPairArray = this.pairCache.getOverlappingPairArray();
/* 378:    */      
/* 380:380 */      MiscUtil.quickSort(overlappingPairArray, BroadphasePair.broadphasePairSortPredicate);
/* 381:    */      
/* 382:382 */      MiscUtil.resize(overlappingPairArray, overlappingPairArray.size() - this.invalidPair, BroadphasePair.class);
/* 383:383 */      this.invalidPair = 0;
/* 384:    */      
/* 387:387 */      BroadphasePair previousPair = new BroadphasePair();
/* 388:388 */      previousPair.pProxy0 = null;
/* 389:389 */      previousPair.pProxy1 = null;
/* 390:390 */      previousPair.algorithm = null;
/* 391:    */      
/* 392:392 */      for (int i = 0; i < overlappingPairArray.size(); i++) {
/* 393:393 */        BroadphasePair pair = (BroadphasePair)overlappingPairArray.getQuick(i);
/* 394:    */        
/* 395:395 */        boolean isDuplicate = pair.equals(previousPair);
/* 396:    */        
/* 397:397 */        previousPair.set(pair);
/* 398:    */        
/* 399:399 */        boolean needsRemoval = false;
/* 400:    */        
/* 401:401 */        if (!isDuplicate) {
/* 402:402 */          boolean hasOverlap = testAabbOverlap(pair.pProxy0, pair.pProxy1);
/* 403:    */          
/* 404:404 */          if (hasOverlap) {
/* 405:405 */            needsRemoval = false;
/* 406:    */          }
/* 407:    */          else {
/* 408:408 */            needsRemoval = true;
/* 409:    */          }
/* 410:    */        }
/* 411:    */        else
/* 412:    */        {
/* 413:413 */          needsRemoval = true;
/* 414:    */          
/* 415:415 */          assert (pair.algorithm == null);
/* 416:    */        }
/* 417:    */        
/* 418:418 */        if (needsRemoval) {
/* 419:419 */          this.pairCache.cleanOverlappingPair(pair, dispatcher);
/* 420:    */          
/* 423:423 */          pair.pProxy0 = null;
/* 424:424 */          pair.pProxy1 = null;
/* 425:425 */          this.invalidPair += 1;
/* 426:426 */          BulletStats.gOverlappingPairs -= 1;
/* 427:    */        }
/* 428:    */      }
/* 429:    */      
/* 436:436 */      MiscUtil.quickSort(overlappingPairArray, BroadphasePair.broadphasePairSortPredicate);
/* 437:    */      
/* 438:438 */      MiscUtil.resize(overlappingPairArray, overlappingPairArray.size() - this.invalidPair, BroadphasePair.class);
/* 439:439 */      this.invalidPair = 0;
/* 440:    */    }
/* 441:    */  }
/* 442:    */  
/* 446:    */  public int addHandle(Vector3f aabbMin, Vector3f aabbMax, Object pOwner, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
/* 447:    */  {
/* 448:448 */    int[] min = new int[3];int[] max = new int[3];
/* 449:449 */    quantize(min, aabbMin, 0);
/* 450:450 */    quantize(max, aabbMax, 1);
/* 451:    */    
/* 453:453 */    int handle = allocHandle();
/* 454:    */    
/* 455:455 */    Handle pHandle = getHandle(handle);
/* 456:    */    
/* 457:457 */    pHandle.uniqueId = handle;
/* 458:    */    
/* 459:459 */    pHandle.clientObject = pOwner;
/* 460:460 */    pHandle.collisionFilterGroup = collisionFilterGroup;
/* 461:461 */    pHandle.collisionFilterMask = collisionFilterMask;
/* 462:462 */    pHandle.multiSapParentProxy = multiSapProxy;
/* 463:    */    
/* 465:465 */    int limit = this.numHandles * 2;
/* 466:    */    
/* 468:468 */    for (int axis = 0; axis < 3; axis++) {
/* 469:469 */      this.pHandles[0].setMaxEdges(axis, this.pHandles[0].getMaxEdges(axis) + 2);
/* 470:    */      
/* 471:471 */      this.pEdges[axis].set(limit + 1, limit - 1);
/* 472:    */      
/* 473:473 */      this.pEdges[axis].setPos(limit - 1, min[axis]);
/* 474:474 */      this.pEdges[axis].setHandle(limit - 1, handle);
/* 475:    */      
/* 476:476 */      this.pEdges[axis].setPos(limit, max[axis]);
/* 477:477 */      this.pEdges[axis].setHandle(limit, handle);
/* 478:    */      
/* 479:479 */      pHandle.setMinEdges(axis, limit - 1);
/* 480:480 */      pHandle.setMaxEdges(axis, limit);
/* 481:    */    }
/* 482:    */    
/* 484:484 */    sortMinDown(0, pHandle.getMinEdges(0), dispatcher, false);
/* 485:485 */    sortMaxDown(0, pHandle.getMaxEdges(0), dispatcher, false);
/* 486:486 */    sortMinDown(1, pHandle.getMinEdges(1), dispatcher, false);
/* 487:487 */    sortMaxDown(1, pHandle.getMaxEdges(1), dispatcher, false);
/* 488:488 */    sortMinDown(2, pHandle.getMinEdges(2), dispatcher, true);
/* 489:489 */    sortMaxDown(2, pHandle.getMaxEdges(2), dispatcher, true);
/* 490:    */    
/* 491:491 */    return handle;
/* 492:    */  }
/* 493:    */  
/* 494:    */  public void removeHandle(int handle, Dispatcher dispatcher) {
/* 495:495 */    Handle pHandle = getHandle(handle);
/* 496:    */    
/* 500:500 */    if (!this.pairCache.hasDeferredRemoval()) {
/* 501:501 */      this.pairCache.removeOverlappingPairsContainingProxy(pHandle, dispatcher);
/* 502:    */    }
/* 503:    */    
/* 505:505 */    int limit = this.numHandles * 2;
/* 506:    */    
/* 509:509 */    for (int axis = 0; axis < 3; axis++) {
/* 510:510 */      this.pHandles[0].setMaxEdges(axis, this.pHandles[0].getMaxEdges(axis) - 2);
/* 511:    */    }
/* 512:    */    
/* 514:514 */    for (axis = 0; axis < 3; axis++) {
/* 515:515 */      EdgeArray pEdges = this.pEdges[axis];
/* 516:516 */      int max = pHandle.getMaxEdges(axis);
/* 517:517 */      pEdges.setPos(max, this.handleSentinel);
/* 518:    */      
/* 519:519 */      sortMaxUp(axis, max, dispatcher, false);
/* 520:    */      
/* 521:521 */      int i = pHandle.getMinEdges(axis);
/* 522:522 */      pEdges.setPos(i, this.handleSentinel);
/* 523:    */      
/* 524:524 */      sortMinUp(axis, i, dispatcher, false);
/* 525:    */      
/* 526:526 */      pEdges.setHandle(limit - 1, 0);
/* 527:527 */      pEdges.setPos(limit - 1, this.handleSentinel);
/* 528:    */    }
/* 529:    */    
/* 535:535 */    freeHandle(handle);
/* 536:    */  }
/* 537:    */  
/* 538:    */  public void updateHandle(int handle, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher) {
/* 539:539 */    Handle pHandle = getHandle(handle);
/* 540:    */    
/* 542:542 */    int[] min = new int[3];int[] max = new int[3];
/* 543:543 */    quantize(min, aabbMin, 0);
/* 544:544 */    quantize(max, aabbMax, 1);
/* 545:    */    
/* 547:547 */    for (int axis = 0; axis < 3; axis++) {
/* 548:548 */      int emin = pHandle.getMinEdges(axis);
/* 549:549 */      int emax = pHandle.getMaxEdges(axis);
/* 550:    */      
/* 551:551 */      int dmin = min[axis] - this.pEdges[axis].getPos(emin);
/* 552:552 */      int dmax = max[axis] - this.pEdges[axis].getPos(emax);
/* 553:    */      
/* 554:554 */      this.pEdges[axis].setPos(emin, min[axis]);
/* 555:555 */      this.pEdges[axis].setPos(emax, max[axis]);
/* 556:    */      
/* 558:558 */      if (dmin < 0) {
/* 559:559 */        sortMinDown(axis, emin, dispatcher, true);
/* 560:    */      }
/* 561:561 */      if (dmax > 0) {
/* 562:562 */        sortMaxUp(axis, emax, dispatcher, true);
/* 563:    */      }
/* 564:564 */      if (dmin > 0) {
/* 565:565 */        sortMinUp(axis, emin, dispatcher, true);
/* 566:    */      }
/* 567:567 */      if (dmax < 0) {
/* 568:568 */        sortMaxDown(axis, emax, dispatcher, true);
/* 569:    */      }
/* 570:    */    }
/* 571:    */  }
/* 572:    */  
/* 576:    */  public Handle getHandle(int index)
/* 577:    */  {
/* 578:578 */    return this.pHandles[index];
/* 579:    */  }
/* 580:    */  
/* 583:    */  public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
/* 584:    */  {
/* 585:585 */    int handleId = addHandle(aabbMin, aabbMax, userPtr, collisionFilterGroup, collisionFilterMask, dispatcher, multiSapProxy);
/* 586:    */    
/* 587:587 */    Handle handle = getHandle(handleId);
/* 588:    */    
/* 589:589 */    return handle;
/* 590:    */  }
/* 591:    */  
/* 592:    */  public void destroyProxy(BroadphaseProxy proxy, Dispatcher dispatcher) {
/* 593:593 */    Handle handle = (Handle)proxy;
/* 594:594 */    removeHandle(handle.uniqueId, dispatcher);
/* 595:    */  }
/* 596:    */  
/* 597:    */  public void setAabb(BroadphaseProxy proxy, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher) {
/* 598:598 */    Handle handle = (Handle)proxy;
/* 599:599 */    updateHandle(handle.uniqueId, aabbMin, aabbMax, dispatcher);
/* 600:    */  }
/* 601:    */  
/* 602:    */  public boolean testAabbOverlap(BroadphaseProxy proxy0, BroadphaseProxy proxy1) {
/* 603:603 */    Handle pHandleA = (Handle)proxy0;
/* 604:604 */    Handle pHandleB = (Handle)proxy1;
/* 605:    */    
/* 608:608 */    for (int axis = 0; axis < 3; axis++) {
/* 609:609 */      if ((pHandleA.getMaxEdges(axis) < pHandleB.getMinEdges(axis)) || (pHandleB.getMaxEdges(axis) < pHandleA.getMinEdges(axis)))
/* 610:    */      {
/* 611:611 */        return false;
/* 612:    */      }
/* 613:    */    }
/* 614:614 */    return true;
/* 615:    */  }
/* 616:    */  
/* 617:    */  public OverlappingPairCache getOverlappingPairCache() {
/* 618:618 */    return this.pairCache;
/* 619:    */  }
/* 620:    */  
/* 621:    */  public void setOverlappingPairUserCallback(OverlappingPairCallback pairCallback) {
/* 622:622 */    this.userPairCallback = pairCallback;
/* 623:    */  }
/* 624:    */  
/* 625:    */  public OverlappingPairCallback getOverlappingPairUserCallback() {
/* 626:626 */    return this.userPairCallback;
/* 627:    */  }
/* 628:    */  
/* 630:    */  public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax)
/* 631:    */  {
/* 632:632 */    aabbMin.set(this.worldAabbMin);
/* 633:633 */    aabbMax.set(this.worldAabbMax);
/* 634:    */  }
/* 635:    */  
/* 638:    */  public void printStats() {}
/* 639:    */  
/* 642:    */  protected abstract EdgeArray createEdgeArray(int paramInt);
/* 643:    */  
/* 644:    */  protected abstract Handle createHandle();
/* 645:    */  
/* 646:    */  protected abstract int getMask();
/* 647:    */  
/* 648:    */  protected static abstract class EdgeArray
/* 649:    */  {
/* 650:    */    public abstract void swap(int paramInt1, int paramInt2);
/* 651:    */    
/* 652:    */    public abstract void set(int paramInt1, int paramInt2);
/* 653:    */    
/* 654:    */    public abstract int getPos(int paramInt);
/* 655:    */    
/* 656:    */    public abstract void setPos(int paramInt1, int paramInt2);
/* 657:    */    
/* 658:    */    public abstract int getHandle(int paramInt);
/* 659:    */    
/* 660:    */    public abstract void setHandle(int paramInt1, int paramInt2);
/* 661:    */    
/* 662:662 */    public int isMax(int offset) { return getPos(offset) & 0x1; }
/* 663:    */  }
/* 664:    */  
/* 665:    */  protected static abstract class Handle extends BroadphaseProxy {
/* 666:    */    public abstract int getMinEdges(int paramInt);
/* 667:    */    
/* 668:    */    public abstract void setMinEdges(int paramInt1, int paramInt2);
/* 669:    */    
/* 670:    */    public abstract int getMaxEdges(int paramInt);
/* 671:    */    
/* 672:    */    public abstract void setMaxEdges(int paramInt1, int paramInt2);
/* 673:    */    
/* 674:674 */    public void incMinEdges(int edgeIndex) { setMinEdges(edgeIndex, getMinEdges(edgeIndex) + 1); }
/* 675:    */    
/* 676:    */    public void incMaxEdges(int edgeIndex)
/* 677:    */    {
/* 678:678 */      setMaxEdges(edgeIndex, getMaxEdges(edgeIndex) + 1);
/* 679:    */    }
/* 680:    */    
/* 681:    */    public void decMinEdges(int edgeIndex) {
/* 682:682 */      setMinEdges(edgeIndex, getMinEdges(edgeIndex) - 1);
/* 683:    */    }
/* 684:    */    
/* 685:    */    public void decMaxEdges(int edgeIndex) {
/* 686:686 */      setMaxEdges(edgeIndex, getMaxEdges(edgeIndex) - 1);
/* 687:    */    }
/* 688:    */    
/* 689:    */    public void setNextFree(int next) {
/* 690:690 */      setMinEdges(0, next);
/* 691:    */    }
/* 692:    */    
/* 693:    */    public int getNextFree() {
/* 694:694 */      return getMinEdges(0);
/* 695:    */    }
/* 696:    */  }
/* 697:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3Internal
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */