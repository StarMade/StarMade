/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import com.bulletphysics.BulletStats;
/*   4:    */import com.bulletphysics.linearmath.MiscUtil;
/*   5:    */import com.bulletphysics.util.IntArrayList;
/*   6:    */import com.bulletphysics.util.ObjectArrayList;
/*   7:    */import com.bulletphysics.util.ObjectPool;
/*   8:    */
/*  36:    */public class HashedOverlappingPairCache
/*  37:    */  extends OverlappingPairCache
/*  38:    */{
/*  39: 39 */  private final ObjectPool<BroadphasePair> pairsPool = ObjectPool.get(BroadphasePair.class);
/*  40:    */  
/*  41:    */  private static final int NULL_PAIR = -1;
/*  42:    */  
/*  43: 43 */  private ObjectArrayList<BroadphasePair> overlappingPairArray = new ObjectArrayList();
/*  44:    */  private OverlapFilterCallback overlapFilterCallback;
/*  45: 45 */  private boolean blockedForChanges = false;
/*  46:    */  
/*  47: 47 */  private IntArrayList hashTable = new IntArrayList();
/*  48: 48 */  private IntArrayList next = new IntArrayList();
/*  49:    */  protected OverlappingPairCallback ghostPairCallback;
/*  50:    */  
/*  51:    */  public HashedOverlappingPairCache() {
/*  52: 52 */    int initialAllocatedSize = 2;
/*  53:    */    
/*  54: 54 */    growTables();
/*  55:    */  }
/*  56:    */  
/*  60:    */  public BroadphasePair addOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
/*  61:    */  {
/*  62: 62 */    BulletStats.gAddedPairs += 1;
/*  63:    */    
/*  64: 64 */    if (!needsBroadphaseCollision(proxy0, proxy1)) {
/*  65: 65 */      return null;
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    return internalAddPair(proxy0, proxy1);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public Object removeOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, Dispatcher dispatcher) {
/*  72: 72 */    BulletStats.gRemovePairs += 1;
/*  73: 73 */    if (proxy0.getUid() > proxy1.getUid()) {
/*  74: 74 */      BroadphaseProxy tmp = proxy0;
/*  75: 75 */      proxy0 = proxy1;
/*  76: 76 */      proxy1 = tmp;
/*  77:    */    }
/*  78: 78 */    int proxyId1 = proxy0.getUid();
/*  79: 79 */    int proxyId2 = proxy1.getUid();
/*  80:    */    
/*  84: 84 */    int hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/*  85:    */    
/*  86: 86 */    BroadphasePair pair = internalFindPair(proxy0, proxy1, hash);
/*  87: 87 */    if (pair == null) {
/*  88: 88 */      return null;
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    cleanOverlappingPair(pair, dispatcher);
/*  92:    */    
/*  93: 93 */    Object userData = pair.userInfo;
/*  94:    */    
/*  95: 95 */    assert (pair.pProxy0.getUid() == proxyId1);
/*  96: 96 */    assert (pair.pProxy1.getUid() == proxyId2);
/*  97:    */    
/* 100:100 */    int pairIndex = this.overlappingPairArray.indexOf(pair);
/* 101:101 */    assert (pairIndex != -1);
/* 102:    */    
/* 103:103 */    assert (pairIndex < this.overlappingPairArray.size());
/* 104:    */    
/* 106:106 */    int index = this.hashTable.get(hash);
/* 107:107 */    assert (index != -1);
/* 108:    */    
/* 109:109 */    int previous = -1;
/* 110:110 */    while (index != pairIndex) {
/* 111:111 */      previous = index;
/* 112:112 */      index = this.next.get(index);
/* 113:    */    }
/* 114:    */    
/* 115:115 */    if (previous != -1) {
/* 116:116 */      assert (this.next.get(previous) == pairIndex);
/* 117:117 */      this.next.set(previous, this.next.get(pairIndex));
/* 118:    */    }
/* 119:    */    else {
/* 120:120 */      this.hashTable.set(hash, this.next.get(pairIndex));
/* 121:    */    }
/* 122:    */    
/* 127:127 */    int lastPairIndex = this.overlappingPairArray.size() - 1;
/* 128:    */    
/* 129:129 */    if (this.ghostPairCallback != null) {
/* 130:130 */      this.ghostPairCallback.removeOverlappingPair(proxy0, proxy1, dispatcher);
/* 131:    */    }
/* 132:    */    
/* 134:134 */    if (lastPairIndex == pairIndex) {
/* 135:135 */      this.overlappingPairArray.removeQuick(this.overlappingPairArray.size() - 1);
/* 136:136 */      return userData;
/* 137:    */    }
/* 138:    */    
/* 140:140 */    BroadphasePair last = (BroadphasePair)this.overlappingPairArray.getQuick(lastPairIndex);
/* 141:    */    
/* 142:142 */    int lastHash = getHash(last.pProxy0.getUid(), last.pProxy1.getUid()) & this.overlappingPairArray.capacity() - 1;
/* 143:    */    
/* 144:144 */    index = this.hashTable.get(lastHash);
/* 145:145 */    assert (index != -1);
/* 146:    */    
/* 147:147 */    previous = -1;
/* 148:148 */    while (index != lastPairIndex) {
/* 149:149 */      previous = index;
/* 150:150 */      index = this.next.get(index);
/* 151:    */    }
/* 152:    */    
/* 153:153 */    if (previous != -1) {
/* 154:154 */      assert (this.next.get(previous) == lastPairIndex);
/* 155:155 */      this.next.set(previous, this.next.get(lastPairIndex));
/* 156:    */    }
/* 157:    */    else {
/* 158:158 */      this.hashTable.set(lastHash, this.next.get(lastPairIndex));
/* 159:    */    }
/* 160:    */    
/* 162:162 */    ((BroadphasePair)this.overlappingPairArray.getQuick(pairIndex)).set((BroadphasePair)this.overlappingPairArray.getQuick(lastPairIndex));
/* 163:    */    
/* 165:165 */    this.next.set(pairIndex, this.hashTable.get(lastHash));
/* 166:166 */    this.hashTable.set(lastHash, pairIndex);
/* 167:    */    
/* 168:168 */    this.overlappingPairArray.removeQuick(this.overlappingPairArray.size() - 1);
/* 169:    */    
/* 170:170 */    return userData;
/* 171:    */  }
/* 172:    */  
/* 173:    */  public boolean needsBroadphaseCollision(BroadphaseProxy proxy0, BroadphaseProxy proxy1) {
/* 174:174 */    if (this.overlapFilterCallback != null) {
/* 175:175 */      return this.overlapFilterCallback.needBroadphaseCollision(proxy0, proxy1);
/* 176:    */    }
/* 177:    */    
/* 178:178 */    boolean collides = (proxy0.collisionFilterGroup & proxy1.collisionFilterMask) != 0;
/* 179:179 */    collides = (collides) && ((proxy1.collisionFilterGroup & proxy0.collisionFilterMask) != 0);
/* 180:    */    
/* 181:181 */    return collides;
/* 182:    */  }
/* 183:    */  
/* 185:    */  public void processAllOverlappingPairs(OverlapCallback callback, Dispatcher dispatcher)
/* 186:    */  {
/* 187:187 */    for (int i = 0; i < this.overlappingPairArray.size();)
/* 188:    */    {
/* 189:189 */      BroadphasePair pair = (BroadphasePair)this.overlappingPairArray.getQuick(i);
/* 190:190 */      if (callback.processOverlap(pair)) {
/* 191:191 */        removeOverlappingPair(pair.pProxy0, pair.pProxy1, dispatcher);
/* 192:    */        
/* 193:193 */        BulletStats.gOverlappingPairs -= 1;
/* 194:    */      }
/* 195:    */      else {
/* 196:196 */        i++;
/* 197:    */      }
/* 198:    */    }
/* 199:    */  }
/* 200:    */  
/* 201:    */  public void removeOverlappingPairsContainingProxy(BroadphaseProxy proxy, Dispatcher dispatcher) {
/* 202:202 */    processAllOverlappingPairs(new RemovePairCallback(proxy), dispatcher);
/* 203:    */  }
/* 204:    */  
/* 205:    */  public void cleanProxyFromPairs(BroadphaseProxy proxy, Dispatcher dispatcher)
/* 206:    */  {
/* 207:207 */    processAllOverlappingPairs(new CleanPairCallback(proxy, this, dispatcher), dispatcher);
/* 208:    */  }
/* 209:    */  
/* 210:    */  public ObjectArrayList<BroadphasePair> getOverlappingPairArray()
/* 211:    */  {
/* 212:212 */    return this.overlappingPairArray;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public void cleanOverlappingPair(BroadphasePair pair, Dispatcher dispatcher)
/* 216:    */  {
/* 217:217 */    if (pair.algorithm != null)
/* 218:    */    {
/* 219:219 */      dispatcher.freeCollisionAlgorithm(pair.algorithm);
/* 220:220 */      pair.algorithm = null;
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 224:    */  public BroadphasePair findPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
/* 225:    */  {
/* 226:226 */    BulletStats.gFindPairs += 1;
/* 227:227 */    if (proxy0.getUid() > proxy1.getUid()) {
/* 228:228 */      BroadphaseProxy tmp = proxy0;
/* 229:229 */      proxy0 = proxy1;
/* 230:230 */      proxy1 = proxy0;
/* 231:    */    }
/* 232:232 */    int proxyId1 = proxy0.getUid();
/* 233:233 */    int proxyId2 = proxy1.getUid();
/* 234:    */    
/* 238:238 */    int hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/* 239:    */    
/* 240:240 */    if (hash >= this.hashTable.size())
/* 241:    */    {
/* 242:242 */      return null;
/* 243:    */    }
/* 244:    */    
/* 245:245 */    int index = this.hashTable.get(hash);
/* 246:246 */    while ((index != -1) && (!equalsPair((BroadphasePair)this.overlappingPairArray.getQuick(index), proxyId1, proxyId2)))
/* 247:    */    {
/* 248:248 */      index = this.next.get(index);
/* 249:    */    }
/* 250:    */    
/* 251:251 */    if (index == -1)
/* 252:    */    {
/* 253:253 */      return null;
/* 254:    */    }
/* 255:    */    
/* 256:256 */    assert (index < this.overlappingPairArray.size());
/* 257:    */    
/* 258:258 */    return (BroadphasePair)this.overlappingPairArray.getQuick(index);
/* 259:    */  }
/* 260:    */  
/* 261:    */  public int getCount() {
/* 262:262 */    return this.overlappingPairArray.size();
/* 263:    */  }
/* 264:    */  
/* 265:    */  public OverlapFilterCallback getOverlapFilterCallback()
/* 266:    */  {
/* 267:267 */    return this.overlapFilterCallback;
/* 268:    */  }
/* 269:    */  
/* 270:    */  public void setOverlapFilterCallback(OverlapFilterCallback overlapFilterCallback)
/* 271:    */  {
/* 272:272 */    this.overlapFilterCallback = overlapFilterCallback;
/* 273:    */  }
/* 274:    */  
/* 275:    */  public int getNumOverlappingPairs()
/* 276:    */  {
/* 277:277 */    return this.overlappingPairArray.size();
/* 278:    */  }
/* 279:    */  
/* 280:    */  public boolean hasDeferredRemoval()
/* 281:    */  {
/* 282:282 */    return false;
/* 283:    */  }
/* 284:    */  
/* 285:    */  private BroadphasePair internalAddPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1) {
/* 286:286 */    if (proxy0.getUid() > proxy1.getUid()) {
/* 287:287 */      BroadphaseProxy tmp = proxy0;
/* 288:288 */      proxy0 = proxy1;
/* 289:289 */      proxy1 = tmp;
/* 290:    */    }
/* 291:291 */    int proxyId1 = proxy0.getUid();
/* 292:292 */    int proxyId2 = proxy1.getUid();
/* 293:    */    
/* 297:297 */    int hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/* 298:    */    
/* 299:299 */    BroadphasePair pair = internalFindPair(proxy0, proxy1, hash);
/* 300:300 */    if (pair != null) {
/* 301:301 */      return pair;
/* 302:    */    }
/* 303:    */    
/* 312:312 */    int count = this.overlappingPairArray.size();
/* 313:313 */    int oldCapacity = this.overlappingPairArray.capacity();
/* 314:314 */    this.overlappingPairArray.add(null);
/* 315:    */    
/* 317:317 */    if (this.ghostPairCallback != null) {
/* 318:318 */      this.ghostPairCallback.addOverlappingPair(proxy0, proxy1);
/* 319:    */    }
/* 320:    */    
/* 321:321 */    int newCapacity = this.overlappingPairArray.capacity();
/* 322:    */    
/* 323:323 */    if (oldCapacity < newCapacity) {
/* 324:324 */      growTables();
/* 325:    */      
/* 326:326 */      hash = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/* 327:    */    }
/* 328:    */    
/* 329:329 */    pair = new BroadphasePair(proxy0, proxy1);
/* 330:    */    
/* 332:332 */    pair.algorithm = null;
/* 333:333 */    pair.userInfo = null;
/* 334:    */    
/* 335:335 */    this.overlappingPairArray.setQuick(this.overlappingPairArray.size() - 1, pair);
/* 336:    */    
/* 337:337 */    this.next.set(count, this.hashTable.get(hash));
/* 338:338 */    this.hashTable.set(hash, count);
/* 339:    */    
/* 340:340 */    return pair;
/* 341:    */  }
/* 342:    */  
/* 343:    */  private void growTables() {
/* 344:344 */    int newCapacity = this.overlappingPairArray.capacity();
/* 345:    */    
/* 346:346 */    if (this.hashTable.size() < newCapacity)
/* 347:    */    {
/* 348:348 */      int curHashtableSize = this.hashTable.size();
/* 349:    */      
/* 350:350 */      MiscUtil.resize(this.hashTable, newCapacity, 0);
/* 351:351 */      MiscUtil.resize(this.next, newCapacity, 0);
/* 352:    */      
/* 353:353 */      for (int i = 0; i < newCapacity; i++) {
/* 354:354 */        this.hashTable.set(i, -1);
/* 355:    */      }
/* 356:356 */      for (int i = 0; i < newCapacity; i++) {
/* 357:357 */        this.next.set(i, -1);
/* 358:    */      }
/* 359:    */      
/* 360:360 */      for (int i = 0; i < curHashtableSize; i++)
/* 361:    */      {
/* 362:362 */        BroadphasePair pair = (BroadphasePair)this.overlappingPairArray.getQuick(i);
/* 363:363 */        int proxyId1 = pair.pProxy0.getUid();
/* 364:364 */        int proxyId2 = pair.pProxy1.getUid();
/* 365:    */        
/* 367:367 */        int hashValue = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
/* 368:368 */        this.next.set(i, this.hashTable.get(hashValue));
/* 369:369 */        this.hashTable.set(hashValue, i);
/* 370:    */      }
/* 371:    */    }
/* 372:    */  }
/* 373:    */  
/* 374:    */  private boolean equalsPair(BroadphasePair pair, int proxyId1, int proxyId2) {
/* 375:375 */    return (pair.pProxy0.getUid() == proxyId1) && (pair.pProxy1.getUid() == proxyId2);
/* 376:    */  }
/* 377:    */  
/* 378:    */  private int getHash(int proxyId1, int proxyId2) {
/* 379:379 */    int key = proxyId1 | proxyId2 << 16;
/* 380:    */    
/* 382:382 */    key += (key << 15 ^ 0xFFFFFFFF);
/* 383:383 */    key ^= key >>> 10;
/* 384:384 */    key += (key << 3);
/* 385:385 */    key ^= key >>> 6;
/* 386:386 */    key += (key << 11 ^ 0xFFFFFFFF);
/* 387:387 */    key ^= key >>> 16;
/* 388:388 */    return key;
/* 389:    */  }
/* 390:    */  
/* 391:    */  private BroadphasePair internalFindPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, int hash) {
/* 392:392 */    int proxyId1 = proxy0.getUid();
/* 393:393 */    int proxyId2 = proxy1.getUid();
/* 394:    */    
/* 399:399 */    int index = this.hashTable.get(hash);
/* 400:    */    
/* 401:401 */    while ((index != -1) && (!equalsPair((BroadphasePair)this.overlappingPairArray.getQuick(index), proxyId1, proxyId2))) {
/* 402:402 */      index = this.next.get(index);
/* 403:    */    }
/* 404:    */    
/* 405:405 */    if (index == -1) {
/* 406:406 */      return null;
/* 407:    */    }
/* 408:    */    
/* 409:409 */    assert (index < this.overlappingPairArray.size());
/* 410:    */    
/* 411:411 */    return (BroadphasePair)this.overlappingPairArray.getQuick(index);
/* 412:    */  }
/* 413:    */  
/* 414:    */  public void setInternalGhostPairCallback(OverlappingPairCallback ghostPairCallback) {
/* 415:415 */    this.ghostPairCallback = ghostPairCallback;
/* 416:    */  }
/* 417:    */  
/* 418:    */  private static class RemovePairCallback extends OverlapCallback
/* 419:    */  {
/* 420:    */    private BroadphaseProxy obsoleteProxy;
/* 421:    */    
/* 422:    */    public RemovePairCallback(BroadphaseProxy obsoleteProxy)
/* 423:    */    {
/* 424:424 */      this.obsoleteProxy = obsoleteProxy;
/* 425:    */    }
/* 426:    */    
/* 427:    */    public boolean processOverlap(BroadphasePair pair) {
/* 428:428 */      return (pair.pProxy0 == this.obsoleteProxy) || (pair.pProxy1 == this.obsoleteProxy);
/* 429:    */    }
/* 430:    */  }
/* 431:    */  
/* 432:    */  private static class CleanPairCallback extends OverlapCallback
/* 433:    */  {
/* 434:    */    private BroadphaseProxy cleanProxy;
/* 435:    */    private OverlappingPairCache pairCache;
/* 436:    */    private Dispatcher dispatcher;
/* 437:    */    
/* 438:    */    public CleanPairCallback(BroadphaseProxy cleanProxy, OverlappingPairCache pairCache, Dispatcher dispatcher) {
/* 439:439 */      this.cleanProxy = cleanProxy;
/* 440:440 */      this.pairCache = pairCache;
/* 441:441 */      this.dispatcher = dispatcher;
/* 442:    */    }
/* 443:    */    
/* 444:    */    public boolean processOverlap(BroadphasePair pair) {
/* 445:445 */      if ((pair.pProxy0 == this.cleanProxy) || (pair.pProxy1 == this.cleanProxy))
/* 446:    */      {
/* 447:447 */        this.pairCache.cleanOverlappingPair(pair, this.dispatcher);
/* 448:    */      }
/* 449:449 */      return false;
/* 450:    */    }
/* 451:    */  }
/* 452:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.HashedOverlappingPairCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */