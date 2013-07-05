/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ 
/*     */ public class HashedOverlappingPairCache extends OverlappingPairCache
/*     */ {
/*  39 */   private final ObjectPool<BroadphasePair> pairsPool = ObjectPool.get(BroadphasePair.class);
/*     */   private static final int NULL_PAIR = -1;
/*  43 */   private ObjectArrayList<BroadphasePair> overlappingPairArray = new ObjectArrayList();
/*     */   private OverlapFilterCallback overlapFilterCallback;
/*  45 */   private boolean blockedForChanges = false;
/*     */ 
/*  47 */   private IntArrayList hashTable = new IntArrayList();
/*  48 */   private IntArrayList next = new IntArrayList();
/*     */   protected OverlappingPairCallback ghostPairCallback;
/*     */ 
/*     */   public HashedOverlappingPairCache()
/*     */   {
/*  52 */     int initialAllocatedSize = 2;
/*     */ 
/*  54 */     growTables();
/*     */   }
/*     */ 
/*     */   public BroadphasePair addOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
/*     */   {
/*  62 */     BulletStats.gAddedPairs += 1;
/*     */ 
/*  64 */     if (!needsBroadphaseCollision(proxy0, proxy1)) {
/*  65 */       return null;
/*     */     }
/*     */ 
/*  68 */     return internalAddPair(proxy0, proxy1);
/*     */   }
/*     */ 
/*     */   public Object removeOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, Dispatcher dispatcher) {
/*  72 */     BulletStats.gRemovePairs += 1;
/*  73 */     if (proxy0.getUid() > proxy1.getUid()) {
/*  74 */       BroadphaseProxy tmp = proxy0;
/*  75 */       proxy0 = proxy1;
/*  76 */       proxy1 = tmp;
/*     */     }
/*  78 */     int proxyId1 = proxy0.getUid();
/*  79 */     int proxyId2 = proxy1.getUid();
/*     */ 
/*  84 */     int hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/*     */ 
/*  86 */     BroadphasePair pair = internalFindPair(proxy0, proxy1, hash);
/*  87 */     if (pair == null) {
/*  88 */       return null;
/*     */     }
/*     */ 
/*  91 */     cleanOverlappingPair(pair, dispatcher);
/*     */ 
/*  93 */     Object userData = pair.userInfo;
/*     */ 
/*  95 */     assert (pair.pProxy0.getUid() == proxyId1);
/*  96 */     assert (pair.pProxy1.getUid() == proxyId2);
/*     */ 
/* 100 */     int pairIndex = this.overlappingPairArray.indexOf(pair);
/* 101 */     assert (pairIndex != -1);
/*     */ 
/* 103 */     assert (pairIndex < this.overlappingPairArray.size());
/*     */ 
/* 106 */     int index = this.hashTable.get(hash);
/* 107 */     assert (index != -1);
/*     */ 
/* 109 */     int previous = -1;
/* 110 */     while (index != pairIndex) {
/* 111 */       previous = index;
/* 112 */       index = this.next.get(index);
/*     */     }
/*     */ 
/* 115 */     if (previous != -1) {
/* 116 */       assert (this.next.get(previous) == pairIndex);
/* 117 */       this.next.set(previous, this.next.get(pairIndex));
/*     */     }
/*     */     else {
/* 120 */       this.hashTable.set(hash, this.next.get(pairIndex));
/*     */     }
/*     */ 
/* 127 */     int lastPairIndex = this.overlappingPairArray.size() - 1;
/*     */ 
/* 129 */     if (this.ghostPairCallback != null) {
/* 130 */       this.ghostPairCallback.removeOverlappingPair(proxy0, proxy1, dispatcher);
/*     */     }
/*     */ 
/* 134 */     if (lastPairIndex == pairIndex) {
/* 135 */       this.overlappingPairArray.removeQuick(this.overlappingPairArray.size() - 1);
/* 136 */       return userData;
/*     */     }
/*     */ 
/* 140 */     BroadphasePair last = (BroadphasePair)this.overlappingPairArray.getQuick(lastPairIndex);
/*     */ 
/* 142 */     int lastHash = getHash(last.pProxy0.getUid(), last.pProxy1.getUid()) & this.overlappingPairArray.capacity() - 1;
/*     */ 
/* 144 */     index = this.hashTable.get(lastHash);
/* 145 */     assert (index != -1);
/*     */ 
/* 147 */     previous = -1;
/* 148 */     while (index != lastPairIndex) {
/* 149 */       previous = index;
/* 150 */       index = this.next.get(index);
/*     */     }
/*     */ 
/* 153 */     if (previous != -1) {
/* 154 */       assert (this.next.get(previous) == lastPairIndex);
/* 155 */       this.next.set(previous, this.next.get(lastPairIndex));
/*     */     }
/*     */     else {
/* 158 */       this.hashTable.set(lastHash, this.next.get(lastPairIndex));
/*     */     }
/*     */ 
/* 162 */     ((BroadphasePair)this.overlappingPairArray.getQuick(pairIndex)).set((BroadphasePair)this.overlappingPairArray.getQuick(lastPairIndex));
/*     */ 
/* 165 */     this.next.set(pairIndex, this.hashTable.get(lastHash));
/* 166 */     this.hashTable.set(lastHash, pairIndex);
/*     */ 
/* 168 */     this.overlappingPairArray.removeQuick(this.overlappingPairArray.size() - 1);
/*     */ 
/* 170 */     return userData;
/*     */   }
/*     */ 
/*     */   public boolean needsBroadphaseCollision(BroadphaseProxy proxy0, BroadphaseProxy proxy1) {
/* 174 */     if (this.overlapFilterCallback != null) {
/* 175 */       return this.overlapFilterCallback.needBroadphaseCollision(proxy0, proxy1);
/*     */     }
/*     */ 
/* 178 */     boolean collides = (proxy0.collisionFilterGroup & proxy1.collisionFilterMask) != 0;
/* 179 */     collides = (collides) && ((proxy1.collisionFilterGroup & proxy0.collisionFilterMask) != 0);
/*     */ 
/* 181 */     return collides;
/*     */   }
/*     */ 
/*     */   public void processAllOverlappingPairs(OverlapCallback callback, Dispatcher dispatcher)
/*     */   {
/* 187 */     for (int i = 0; i < this.overlappingPairArray.size(); )
/*     */     {
/* 189 */       BroadphasePair pair = (BroadphasePair)this.overlappingPairArray.getQuick(i);
/* 190 */       if (callback.processOverlap(pair)) {
/* 191 */         removeOverlappingPair(pair.pProxy0, pair.pProxy1, dispatcher);
/*     */ 
/* 193 */         BulletStats.gOverlappingPairs -= 1;
/*     */       }
/*     */       else {
/* 196 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeOverlappingPairsContainingProxy(BroadphaseProxy proxy, Dispatcher dispatcher) {
/* 202 */     processAllOverlappingPairs(new RemovePairCallback(proxy), dispatcher);
/*     */   }
/*     */ 
/*     */   public void cleanProxyFromPairs(BroadphaseProxy proxy, Dispatcher dispatcher)
/*     */   {
/* 207 */     processAllOverlappingPairs(new CleanPairCallback(proxy, this, dispatcher), dispatcher);
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<BroadphasePair> getOverlappingPairArray()
/*     */   {
/* 212 */     return this.overlappingPairArray;
/*     */   }
/*     */ 
/*     */   public void cleanOverlappingPair(BroadphasePair pair, Dispatcher dispatcher)
/*     */   {
/* 217 */     if (pair.algorithm != null)
/*     */     {
/* 219 */       dispatcher.freeCollisionAlgorithm(pair.algorithm);
/* 220 */       pair.algorithm = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public BroadphasePair findPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
/*     */   {
/* 226 */     BulletStats.gFindPairs += 1;
/* 227 */     if (proxy0.getUid() > proxy1.getUid()) {
/* 228 */       BroadphaseProxy tmp = proxy0;
/* 229 */       proxy0 = proxy1;
/* 230 */       proxy1 = proxy0;
/*     */     }
/* 232 */     int proxyId1 = proxy0.getUid();
/* 233 */     int proxyId2 = proxy1.getUid();
/*     */ 
/* 238 */     int hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/*     */ 
/* 240 */     if (hash >= this.hashTable.size())
/*     */     {
/* 242 */       return null;
/*     */     }
/*     */ 
/* 245 */     int index = this.hashTable.get(hash);
/* 246 */     while ((index != -1) && (!equalsPair((BroadphasePair)this.overlappingPairArray.getQuick(index), proxyId1, proxyId2)))
/*     */     {
/* 248 */       index = this.next.get(index);
/*     */     }
/*     */ 
/* 251 */     if (index == -1)
/*     */     {
/* 253 */       return null;
/*     */     }
/*     */ 
/* 256 */     assert (index < this.overlappingPairArray.size());
/*     */ 
/* 258 */     return (BroadphasePair)this.overlappingPairArray.getQuick(index);
/*     */   }
/*     */ 
/*     */   public int getCount() {
/* 262 */     return this.overlappingPairArray.size();
/*     */   }
/*     */ 
/*     */   public OverlapFilterCallback getOverlapFilterCallback()
/*     */   {
/* 267 */     return this.overlapFilterCallback;
/*     */   }
/*     */ 
/*     */   public void setOverlapFilterCallback(OverlapFilterCallback overlapFilterCallback)
/*     */   {
/* 272 */     this.overlapFilterCallback = overlapFilterCallback;
/*     */   }
/*     */ 
/*     */   public int getNumOverlappingPairs()
/*     */   {
/* 277 */     return this.overlappingPairArray.size();
/*     */   }
/*     */ 
/*     */   public boolean hasDeferredRemoval()
/*     */   {
/* 282 */     return false;
/*     */   }
/*     */ 
/*     */   private BroadphasePair internalAddPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1) {
/* 286 */     if (proxy0.getUid() > proxy1.getUid()) {
/* 287 */       BroadphaseProxy tmp = proxy0;
/* 288 */       proxy0 = proxy1;
/* 289 */       proxy1 = tmp;
/*     */     }
/* 291 */     int proxyId1 = proxy0.getUid();
/* 292 */     int proxyId2 = proxy1.getUid();
/*     */ 
/* 297 */     int hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/*     */ 
/* 299 */     BroadphasePair pair = internalFindPair(proxy0, proxy1, hash);
/* 300 */     if (pair != null) {
/* 301 */       return pair;
/*     */     }
/*     */ 
/* 312 */     int count = this.overlappingPairArray.size();
/* 313 */     int oldCapacity = this.overlappingPairArray.capacity();
/* 314 */     this.overlappingPairArray.add(null);
/*     */ 
/* 317 */     if (this.ghostPairCallback != null) {
/* 318 */       this.ghostPairCallback.addOverlappingPair(proxy0, proxy1);
/*     */     }
/*     */ 
/* 321 */     int newCapacity = this.overlappingPairArray.capacity();
/*     */ 
/* 323 */     if (oldCapacity < newCapacity) {
/* 324 */       growTables();
/*     */ 
/* 326 */       hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/*     */     }
/*     */ 
/* 329 */     pair = new BroadphasePair(proxy0, proxy1);
/*     */ 
/* 332 */     pair.algorithm = null;
/* 333 */     pair.userInfo = null;
/*     */ 
/* 335 */     this.overlappingPairArray.setQuick(this.overlappingPairArray.size() - 1, pair);
/*     */ 
/* 337 */     this.next.set(count, this.hashTable.get(hash));
/* 338 */     this.hashTable.set(hash, count);
/*     */ 
/* 340 */     return pair;
/*     */   }
/*     */ 
/*     */   private void growTables() {
/* 344 */     int newCapacity = this.overlappingPairArray.capacity();
/*     */ 
/* 346 */     if (this.hashTable.size() < newCapacity)
/*     */     {
/* 348 */       int curHashtableSize = this.hashTable.size();
/*     */ 
/* 350 */       MiscUtil.resize(this.hashTable, newCapacity, 0);
/* 351 */       MiscUtil.resize(this.next, newCapacity, 0);
/*     */ 
/* 353 */       for (int i = 0; i < newCapacity; i++) {
/* 354 */         this.hashTable.set(i, -1);
/*     */       }
/* 356 */       for (int i = 0; i < newCapacity; i++) {
/* 357 */         this.next.set(i, -1);
/*     */       }
/*     */ 
/* 360 */       for (int i = 0; i < curHashtableSize; i++)
/*     */       {
/* 362 */         BroadphasePair pair = (BroadphasePair)this.overlappingPairArray.getQuick(i);
/* 363 */         int proxyId1 = pair.pProxy0.getUid();
/* 364 */         int proxyId2 = pair.pProxy1.getUid();
/*     */ 
/* 367 */         int hashValue = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/* 368 */         this.next.set(i, this.hashTable.get(hashValue));
/* 369 */         this.hashTable.set(hashValue, i);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean equalsPair(BroadphasePair pair, int proxyId1, int proxyId2) {
/* 375 */     return (pair.pProxy0.getUid() == proxyId1) && (pair.pProxy1.getUid() == proxyId2);
/*     */   }
/*     */ 
/*     */   private int getHash(int proxyId1, int proxyId2) {
/* 379 */     int key = proxyId1 | proxyId2 << 16;
/*     */ 
/* 382 */     key += (key << 15 ^ 0xFFFFFFFF);
/* 383 */     key ^= key >>> 10;
/* 384 */     key += (key << 3);
/* 385 */     key ^= key >>> 6;
/* 386 */     key += (key << 11 ^ 0xFFFFFFFF);
/* 387 */     key ^= key >>> 16;
/* 388 */     return key;
/*     */   }
/*     */ 
/*     */   private BroadphasePair internalFindPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, int hash) {
/* 392 */     int proxyId1 = proxy0.getUid();
/* 393 */     int proxyId2 = proxy1.getUid();
/*     */ 
/* 399 */     int index = this.hashTable.get(hash);
/*     */ 
/* 401 */     while ((index != -1) && (!equalsPair((BroadphasePair)this.overlappingPairArray.getQuick(index), proxyId1, proxyId2))) {
/* 402 */       index = this.next.get(index);
/*     */     }
/*     */ 
/* 405 */     if (index == -1) {
/* 406 */       return null;
/*     */     }
/*     */ 
/* 409 */     assert (index < this.overlappingPairArray.size());
/*     */ 
/* 411 */     return (BroadphasePair)this.overlappingPairArray.getQuick(index);
/*     */   }
/*     */ 
/*     */   public void setInternalGhostPairCallback(OverlappingPairCallback ghostPairCallback) {
/* 415 */     this.ghostPairCallback = ghostPairCallback;
/*     */   }
/*     */ 
/*     */   private static class CleanPairCallback extends OverlapCallback
/*     */   {
/*     */     private BroadphaseProxy cleanProxy;
/*     */     private OverlappingPairCache pairCache;
/*     */     private Dispatcher dispatcher;
/*     */ 
/*     */     public CleanPairCallback(BroadphaseProxy cleanProxy, OverlappingPairCache pairCache, Dispatcher dispatcher)
/*     */     {
/* 439 */       this.cleanProxy = cleanProxy;
/* 440 */       this.pairCache = pairCache;
/* 441 */       this.dispatcher = dispatcher;
/*     */     }
/*     */ 
/*     */     public boolean processOverlap(BroadphasePair pair) {
/* 445 */       if ((pair.pProxy0 == this.cleanProxy) || (pair.pProxy1 == this.cleanProxy))
/*     */       {
/* 447 */         this.pairCache.cleanOverlappingPair(pair, this.dispatcher);
/*     */       }
/* 449 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class RemovePairCallback extends OverlapCallback
/*     */   {
/*     */     private BroadphaseProxy obsoleteProxy;
/*     */ 
/*     */     public RemovePairCallback(BroadphaseProxy obsoleteProxy)
/*     */     {
/* 424 */       this.obsoleteProxy = obsoleteProxy;
/*     */     }
/*     */ 
/*     */     public boolean processOverlap(BroadphasePair pair) {
/* 428 */       return (pair.pProxy0 == this.obsoleteProxy) || (pair.pProxy1 == this.obsoleteProxy);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.HashedOverlappingPairCache
 * JD-Core Version:    0.6.2
 */