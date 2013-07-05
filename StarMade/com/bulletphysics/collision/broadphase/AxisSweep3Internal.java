/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class AxisSweep3Internal extends BroadphaseInterface
/*     */ {
/*     */   protected int bpHandleMask;
/*     */   protected int handleSentinel;
/*     */   protected final Vector3f worldAabbMin;
/*     */   protected final Vector3f worldAabbMax;
/*     */   protected final Vector3f quantize;
/*     */   protected int numHandles;
/*     */   protected int maxHandles;
/*     */   protected Handle[] pHandles;
/*     */   protected int firstFreeHandle;
/*     */   protected EdgeArray[] pEdges;
/*     */   protected OverlappingPairCache pairCache;
/*     */   protected OverlappingPairCallback userPairCallback;
/*     */   protected boolean ownsPairCache;
/*     */   protected int invalidPair;
/*     */   protected int mask;
/*     */ 
/*     */   AxisSweep3Internal(Vector3f arg1, Vector3f arg2, int arg3, int arg4, int arg5, OverlappingPairCache arg6)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected int allocHandle()
/*     */   {
/* 145 */     assert (this.firstFreeHandle != 0);
/*     */ 
/* 147 */     int handle = this.firstFreeHandle;
/* 148 */     this.firstFreeHandle = getHandle(handle).getNextFree();
/* 149 */     this.numHandles += 1;
/*     */ 
/* 151 */     return handle;
/*     */   }
/*     */ 
/*     */   protected void freeHandle(int handle) {
/* 155 */     assert ((handle > 0) && (handle < this.maxHandles));
/*     */ 
/* 157 */     getHandle(handle).setNextFree(this.firstFreeHandle);
/* 158 */     this.firstFreeHandle = handle;
/*     */ 
/* 160 */     this.numHandles -= 1;
/*     */   }
/*     */ 
/*     */   protected boolean testOverlap(int ignoreAxis, Handle pHandleA, Handle pHandleB)
/*     */   {
/* 166 */     for (int axis = 0; axis < 3; axis++) {
/* 167 */       if ((axis != ignoreAxis) && (
/* 168 */         (pHandleA.getMaxEdges(axis) < pHandleB.getMinEdges(axis)) || (pHandleB.getMaxEdges(axis) < pHandleA.getMinEdges(axis))))
/*     */       {
/* 170 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */   protected void quantize(int[] arg1, Vector3f arg2, int arg3)
/*     */   {
/* 195 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
/*     */ 
/* 197 */       VectorUtil.setMax(clampedPoint, this.worldAabbMin);
/* 198 */       VectorUtil.setMin(clampedPoint, this.worldAabbMax);
/*     */ 
/* 200 */       Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 201 */       v.sub(clampedPoint, this.worldAabbMin);
/* 202 */       VectorUtil.mul(v, v, this.quantize);
/*     */ 
/* 204 */       out[0] = (((int)v.x & this.bpHandleMask | isMax) & this.mask);
/* 205 */       out[1] = (((int)v.y & this.bpHandleMask | isMax) & this.mask);
/* 206 */       out[2] = (((int)v.z & this.bpHandleMask | isMax) & this.mask);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   protected void sortMinDown(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps) {
/* 211 */     EdgeArray edgeArray = this.pEdges[axis];
/* 212 */     int pEdge_idx = edge;
/* 213 */     int pPrev_idx = pEdge_idx - 1;
/*     */ 
/* 215 */     Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/*     */ 
/* 217 */     while (edgeArray.getPos(pEdge_idx) < edgeArray.getPos(pPrev_idx)) {
/* 218 */       Handle pHandlePrev = getHandle(edgeArray.getHandle(pPrev_idx));
/*     */ 
/* 220 */       if (edgeArray.isMax(pPrev_idx) != 0)
/*     */       {
/* 222 */         if ((updateOverlaps) && (testOverlap(axis, pHandleEdge, pHandlePrev))) {
/* 223 */           this.pairCache.addOverlappingPair(pHandleEdge, pHandlePrev);
/* 224 */           if (this.userPairCallback != null) {
/* 225 */             this.userPairCallback.addOverlappingPair(pHandleEdge, pHandlePrev);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 231 */         pHandlePrev.incMaxEdges(axis);
/*     */       }
/*     */       else {
/* 234 */         pHandlePrev.incMinEdges(axis);
/*     */       }
/* 236 */       pHandleEdge.decMinEdges(axis);
/*     */ 
/* 239 */       edgeArray.swap(pEdge_idx, pPrev_idx);
/*     */ 
/* 242 */       pEdge_idx--;
/* 243 */       pPrev_idx--;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void sortMinUp(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
/*     */   {
/* 253 */     EdgeArray edgeArray = this.pEdges[axis];
/* 254 */     int pEdge_idx = edge;
/* 255 */     int pNext_idx = pEdge_idx + 1;
/* 256 */     Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/*     */ 
/* 258 */     while ((edgeArray.getHandle(pNext_idx) != 0) && (edgeArray.getPos(pEdge_idx) >= edgeArray.getPos(pNext_idx))) {
/* 259 */       Handle pHandleNext = getHandle(edgeArray.getHandle(pNext_idx));
/*     */ 
/* 261 */       if (edgeArray.isMax(pNext_idx) != 0)
/*     */       {
/* 263 */         if (updateOverlaps) {
/* 264 */           Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
/* 265 */           Handle handle1 = getHandle(edgeArray.getHandle(pNext_idx));
/*     */ 
/* 267 */           this.pairCache.removeOverlappingPair(handle0, handle1, dispatcher);
/* 268 */           if (this.userPairCallback != null) {
/* 269 */             this.userPairCallback.removeOverlappingPair(handle0, handle1, dispatcher);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 274 */         pHandleNext.decMaxEdges(axis);
/*     */       }
/*     */       else {
/* 277 */         pHandleNext.decMinEdges(axis);
/*     */       }
/* 279 */       pHandleEdge.incMinEdges(axis);
/*     */ 
/* 282 */       edgeArray.swap(pEdge_idx, pNext_idx);
/*     */ 
/* 285 */       pEdge_idx++;
/* 286 */       pNext_idx++;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void sortMaxDown(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
/*     */   {
/* 292 */     EdgeArray edgeArray = this.pEdges[axis];
/* 293 */     int pEdge_idx = edge;
/* 294 */     int pPrev_idx = pEdge_idx - 1;
/* 295 */     Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/*     */ 
/* 297 */     while (edgeArray.getPos(pEdge_idx) < edgeArray.getPos(pPrev_idx)) {
/* 298 */       Handle pHandlePrev = getHandle(edgeArray.getHandle(pPrev_idx));
/*     */ 
/* 300 */       if (edgeArray.isMax(pPrev_idx) == 0)
/*     */       {
/* 302 */         if (updateOverlaps)
/*     */         {
/* 304 */           Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
/* 305 */           Handle handle1 = getHandle(edgeArray.getHandle(pPrev_idx));
/* 306 */           this.pairCache.removeOverlappingPair(handle0, handle1, dispatcher);
/* 307 */           if (this.userPairCallback != null) {
/* 308 */             this.userPairCallback.removeOverlappingPair(handle0, handle1, dispatcher);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 313 */         pHandlePrev.incMinEdges(axis);
/*     */       }
/*     */       else {
/* 316 */         pHandlePrev.incMaxEdges(axis);
/*     */       }
/* 318 */       pHandleEdge.decMaxEdges(axis);
/*     */ 
/* 321 */       edgeArray.swap(pEdge_idx, pPrev_idx);
/*     */ 
/* 324 */       pEdge_idx--;
/* 325 */       pPrev_idx--;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void sortMaxUp(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
/*     */   {
/* 335 */     EdgeArray edgeArray = this.pEdges[axis];
/* 336 */     int pEdge_idx = edge;
/* 337 */     int pNext_idx = pEdge_idx + 1;
/* 338 */     Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
/*     */ 
/* 340 */     while ((edgeArray.getHandle(pNext_idx) != 0) && (edgeArray.getPos(pEdge_idx) >= edgeArray.getPos(pNext_idx))) {
/* 341 */       Handle pHandleNext = getHandle(edgeArray.getHandle(pNext_idx));
/*     */ 
/* 343 */       if (edgeArray.isMax(pNext_idx) == 0)
/*     */       {
/* 345 */         if ((updateOverlaps) && (testOverlap(axis, pHandleEdge, pHandleNext))) {
/* 346 */           Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
/* 347 */           Handle handle1 = getHandle(edgeArray.getHandle(pNext_idx));
/* 348 */           this.pairCache.addOverlappingPair(handle0, handle1);
/* 349 */           if (this.userPairCallback != null) {
/* 350 */             this.userPairCallback.addOverlappingPair(handle0, handle1);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 355 */         pHandleNext.decMinEdges(axis);
/*     */       }
/*     */       else {
/* 358 */         pHandleNext.decMaxEdges(axis);
/*     */       }
/* 360 */       pHandleEdge.incMaxEdges(axis);
/*     */ 
/* 363 */       edgeArray.swap(pEdge_idx, pNext_idx);
/*     */ 
/* 366 */       pEdge_idx++;
/* 367 */       pNext_idx++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getNumHandles() {
/* 372 */     return this.numHandles;
/*     */   }
/*     */ 
/*     */   public void calculateOverlappingPairs(Dispatcher dispatcher) {
/* 376 */     if (this.pairCache.hasDeferredRemoval()) {
/* 377 */       ObjectArrayList overlappingPairArray = this.pairCache.getOverlappingPairArray();
/*     */ 
/* 380 */       MiscUtil.quickSort(overlappingPairArray, BroadphasePair.broadphasePairSortPredicate);
/*     */ 
/* 382 */       MiscUtil.resize(overlappingPairArray, overlappingPairArray.size() - this.invalidPair, BroadphasePair.class);
/* 383 */       this.invalidPair = 0;
/*     */ 
/* 387 */       BroadphasePair previousPair = new BroadphasePair();
/* 388 */       previousPair.pProxy0 = null;
/* 389 */       previousPair.pProxy1 = null;
/* 390 */       previousPair.algorithm = null;
/*     */ 
/* 392 */       for (int i = 0; i < overlappingPairArray.size(); i++) {
/* 393 */         BroadphasePair pair = (BroadphasePair)overlappingPairArray.getQuick(i);
/*     */ 
/* 395 */         boolean isDuplicate = pair.equals(previousPair);
/*     */ 
/* 397 */         previousPair.set(pair);
/*     */ 
/* 399 */         boolean needsRemoval = false;
/*     */ 
/* 401 */         if (!isDuplicate) {
/* 402 */           boolean hasOverlap = testAabbOverlap(pair.pProxy0, pair.pProxy1);
/*     */ 
/* 404 */           if (hasOverlap) {
/* 405 */             needsRemoval = false;
/*     */           }
/*     */           else {
/* 408 */             needsRemoval = true;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 413 */           needsRemoval = true;
/*     */ 
/* 415 */           assert (pair.algorithm == null);
/*     */         }
/*     */ 
/* 418 */         if (needsRemoval) {
/* 419 */           this.pairCache.cleanOverlappingPair(pair, dispatcher);
/*     */ 
/* 423 */           pair.pProxy0 = null;
/* 424 */           pair.pProxy1 = null;
/* 425 */           this.invalidPair += 1;
/* 426 */           BulletStats.gOverlappingPairs -= 1;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 436 */       MiscUtil.quickSort(overlappingPairArray, BroadphasePair.broadphasePairSortPredicate);
/*     */ 
/* 438 */       MiscUtil.resize(overlappingPairArray, overlappingPairArray.size() - this.invalidPair, BroadphasePair.class);
/* 439 */       this.invalidPair = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int addHandle(Vector3f aabbMin, Vector3f aabbMax, Object pOwner, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
/*     */   {
/* 448 */     int[] min = new int[3]; int[] max = new int[3];
/* 449 */     quantize(min, aabbMin, 0);
/* 450 */     quantize(max, aabbMax, 1);
/*     */ 
/* 453 */     int handle = allocHandle();
/*     */ 
/* 455 */     Handle pHandle = getHandle(handle);
/*     */ 
/* 457 */     pHandle.uniqueId = handle;
/*     */ 
/* 459 */     pHandle.clientObject = pOwner;
/* 460 */     pHandle.collisionFilterGroup = collisionFilterGroup;
/* 461 */     pHandle.collisionFilterMask = collisionFilterMask;
/* 462 */     pHandle.multiSapParentProxy = multiSapProxy;
/*     */ 
/* 465 */     int limit = this.numHandles * 2;
/*     */ 
/* 468 */     for (int axis = 0; axis < 3; axis++) {
/* 469 */       this.pHandles[0].setMaxEdges(axis, this.pHandles[0].getMaxEdges(axis) + 2);
/*     */ 
/* 471 */       this.pEdges[axis].set(limit + 1, limit - 1);
/*     */ 
/* 473 */       this.pEdges[axis].setPos(limit - 1, min[axis]);
/* 474 */       this.pEdges[axis].setHandle(limit - 1, handle);
/*     */ 
/* 476 */       this.pEdges[axis].setPos(limit, max[axis]);
/* 477 */       this.pEdges[axis].setHandle(limit, handle);
/*     */ 
/* 479 */       pHandle.setMinEdges(axis, limit - 1);
/* 480 */       pHandle.setMaxEdges(axis, limit);
/*     */     }
/*     */ 
/* 484 */     sortMinDown(0, pHandle.getMinEdges(0), dispatcher, false);
/* 485 */     sortMaxDown(0, pHandle.getMaxEdges(0), dispatcher, false);
/* 486 */     sortMinDown(1, pHandle.getMinEdges(1), dispatcher, false);
/* 487 */     sortMaxDown(1, pHandle.getMaxEdges(1), dispatcher, false);
/* 488 */     sortMinDown(2, pHandle.getMinEdges(2), dispatcher, true);
/* 489 */     sortMaxDown(2, pHandle.getMaxEdges(2), dispatcher, true);
/*     */ 
/* 491 */     return handle;
/*     */   }
/*     */ 
/*     */   public void removeHandle(int handle, Dispatcher dispatcher) {
/* 495 */     Handle pHandle = getHandle(handle);
/*     */ 
/* 500 */     if (!this.pairCache.hasDeferredRemoval()) {
/* 501 */       this.pairCache.removeOverlappingPairsContainingProxy(pHandle, dispatcher);
/*     */     }
/*     */ 
/* 505 */     int limit = this.numHandles * 2;
/*     */ 
/* 509 */     for (int axis = 0; axis < 3; axis++) {
/* 510 */       this.pHandles[0].setMaxEdges(axis, this.pHandles[0].getMaxEdges(axis) - 2);
/*     */     }
/*     */ 
/* 514 */     for (axis = 0; axis < 3; axis++) {
/* 515 */       EdgeArray pEdges = this.pEdges[axis];
/* 516 */       int max = pHandle.getMaxEdges(axis);
/* 517 */       pEdges.setPos(max, this.handleSentinel);
/*     */ 
/* 519 */       sortMaxUp(axis, max, dispatcher, false);
/*     */ 
/* 521 */       int i = pHandle.getMinEdges(axis);
/* 522 */       pEdges.setPos(i, this.handleSentinel);
/*     */ 
/* 524 */       sortMinUp(axis, i, dispatcher, false);
/*     */ 
/* 526 */       pEdges.setHandle(limit - 1, 0);
/* 527 */       pEdges.setPos(limit - 1, this.handleSentinel);
/*     */     }
/*     */ 
/* 535 */     freeHandle(handle);
/*     */   }
/*     */ 
/*     */   public void updateHandle(int handle, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher) {
/* 539 */     Handle pHandle = getHandle(handle);
/*     */ 
/* 542 */     int[] min = new int[3]; int[] max = new int[3];
/* 543 */     quantize(min, aabbMin, 0);
/* 544 */     quantize(max, aabbMax, 1);
/*     */ 
/* 547 */     for (int axis = 0; axis < 3; axis++) {
/* 548 */       int emin = pHandle.getMinEdges(axis);
/* 549 */       int emax = pHandle.getMaxEdges(axis);
/*     */ 
/* 551 */       int dmin = min[axis] - this.pEdges[axis].getPos(emin);
/* 552 */       int dmax = max[axis] - this.pEdges[axis].getPos(emax);
/*     */ 
/* 554 */       this.pEdges[axis].setPos(emin, min[axis]);
/* 555 */       this.pEdges[axis].setPos(emax, max[axis]);
/*     */ 
/* 558 */       if (dmin < 0) {
/* 559 */         sortMinDown(axis, emin, dispatcher, true);
/*     */       }
/* 561 */       if (dmax > 0) {
/* 562 */         sortMaxUp(axis, emax, dispatcher, true);
/*     */       }
/* 564 */       if (dmin > 0) {
/* 565 */         sortMinUp(axis, emin, dispatcher, true);
/*     */       }
/* 567 */       if (dmax < 0)
/* 568 */         sortMaxDown(axis, emax, dispatcher, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Handle getHandle(int index)
/*     */   {
/* 578 */     return this.pHandles[index];
/*     */   }
/*     */ 
/*     */   public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
/*     */   {
/* 585 */     int handleId = addHandle(aabbMin, aabbMax, userPtr, collisionFilterGroup, collisionFilterMask, dispatcher, multiSapProxy);
/*     */ 
/* 587 */     Handle handle = getHandle(handleId);
/*     */ 
/* 589 */     return handle;
/*     */   }
/*     */ 
/*     */   public void destroyProxy(BroadphaseProxy proxy, Dispatcher dispatcher) {
/* 593 */     Handle handle = (Handle)proxy;
/* 594 */     removeHandle(handle.uniqueId, dispatcher);
/*     */   }
/*     */ 
/*     */   public void setAabb(BroadphaseProxy proxy, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher) {
/* 598 */     Handle handle = (Handle)proxy;
/* 599 */     updateHandle(handle.uniqueId, aabbMin, aabbMax, dispatcher);
/*     */   }
/*     */ 
/*     */   public boolean testAabbOverlap(BroadphaseProxy proxy0, BroadphaseProxy proxy1) {
/* 603 */     Handle pHandleA = (Handle)proxy0;
/* 604 */     Handle pHandleB = (Handle)proxy1;
/*     */ 
/* 608 */     for (int axis = 0; axis < 3; axis++) {
/* 609 */       if ((pHandleA.getMaxEdges(axis) < pHandleB.getMinEdges(axis)) || (pHandleB.getMaxEdges(axis) < pHandleA.getMinEdges(axis)))
/*     */       {
/* 611 */         return false;
/*     */       }
/*     */     }
/* 614 */     return true;
/*     */   }
/*     */ 
/*     */   public OverlappingPairCache getOverlappingPairCache() {
/* 618 */     return this.pairCache;
/*     */   }
/*     */ 
/*     */   public void setOverlappingPairUserCallback(OverlappingPairCallback pairCallback) {
/* 622 */     this.userPairCallback = pairCallback;
/*     */   }
/*     */ 
/*     */   public OverlappingPairCallback getOverlappingPairUserCallback() {
/* 626 */     return this.userPairCallback;
/*     */   }
/*     */ 
/*     */   public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 632 */     aabbMin.set(this.worldAabbMin);
/* 633 */     aabbMax.set(this.worldAabbMax);
/*     */   }
/*     */ 
/*     */   public void printStats()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected abstract EdgeArray createEdgeArray(int paramInt);
/*     */ 
/*     */   protected abstract Handle createHandle();
/*     */ 
/*     */   protected abstract int getMask();
/*     */ 
/*     */   protected static abstract class Handle extends BroadphaseProxy
/*     */   {
/*     */     public abstract int getMinEdges(int paramInt);
/*     */ 
/*     */     public abstract void setMinEdges(int paramInt1, int paramInt2);
/*     */ 
/*     */     public abstract int getMaxEdges(int paramInt);
/*     */ 
/*     */     public abstract void setMaxEdges(int paramInt1, int paramInt2);
/*     */ 
/*     */     public void incMinEdges(int edgeIndex)
/*     */     {
/* 674 */       setMinEdges(edgeIndex, getMinEdges(edgeIndex) + 1);
/*     */     }
/*     */ 
/*     */     public void incMaxEdges(int edgeIndex) {
/* 678 */       setMaxEdges(edgeIndex, getMaxEdges(edgeIndex) + 1);
/*     */     }
/*     */ 
/*     */     public void decMinEdges(int edgeIndex) {
/* 682 */       setMinEdges(edgeIndex, getMinEdges(edgeIndex) - 1);
/*     */     }
/*     */ 
/*     */     public void decMaxEdges(int edgeIndex) {
/* 686 */       setMaxEdges(edgeIndex, getMaxEdges(edgeIndex) - 1);
/*     */     }
/*     */ 
/*     */     public void setNextFree(int next) {
/* 690 */       setMinEdges(0, next);
/*     */     }
/*     */ 
/*     */     public int getNextFree() {
/* 694 */       return getMinEdges(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static abstract class EdgeArray
/*     */   {
/*     */     public abstract void swap(int paramInt1, int paramInt2);
/*     */ 
/*     */     public abstract void set(int paramInt1, int paramInt2);
/*     */ 
/*     */     public abstract int getPos(int paramInt);
/*     */ 
/*     */     public abstract void setPos(int paramInt1, int paramInt2);
/*     */ 
/*     */     public abstract int getHandle(int paramInt);
/*     */ 
/*     */     public abstract void setHandle(int paramInt1, int paramInt2);
/*     */ 
/*     */     public int isMax(int offset)
/*     */     {
/* 662 */       return getPos(offset) & 0x1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3Internal
 * JD-Core Version:    0.6.2
 */