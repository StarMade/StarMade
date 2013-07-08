package com.bulletphysics.collision.broadphase;

import com.bulletphysics.BulletStats;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;

public class HashedOverlappingPairCache
  extends OverlappingPairCache
{
  private final ObjectPool<BroadphasePair> pairsPool = ObjectPool.get(BroadphasePair.class);
  private static final int NULL_PAIR = -1;
  private ObjectArrayList<BroadphasePair> overlappingPairArray = new ObjectArrayList();
  private OverlapFilterCallback overlapFilterCallback;
  private boolean blockedForChanges = false;
  private IntArrayList hashTable = new IntArrayList();
  private IntArrayList next = new IntArrayList();
  protected OverlappingPairCallback ghostPairCallback;
  
  public HashedOverlappingPairCache()
  {
    int initialAllocatedSize = 2;
    growTables();
  }
  
  public BroadphasePair addOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
  {
    BulletStats.gAddedPairs += 1;
    if (!needsBroadphaseCollision(proxy0, proxy1)) {
      return null;
    }
    return internalAddPair(proxy0, proxy1);
  }
  
  public Object removeOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, Dispatcher dispatcher)
  {
    BulletStats.gRemovePairs += 1;
    if (proxy0.getUid() > proxy1.getUid())
    {
      BroadphaseProxy tmp = proxy0;
      proxy0 = proxy1;
      proxy1 = tmp;
    }
    int tmp = proxy0.getUid();
    int proxyId2 = proxy1.getUid();
    int hash = getHash(tmp, proxyId2) & this.overlappingPairArray.capacity() - 1;
    BroadphasePair pair = internalFindPair(proxy0, proxy1, hash);
    if (pair == null) {
      return null;
    }
    cleanOverlappingPair(pair, dispatcher);
    Object userData = pair.userInfo;
    assert (pair.pProxy0.getUid() == tmp);
    assert (pair.pProxy1.getUid() == proxyId2);
    int pairIndex = this.overlappingPairArray.indexOf(pair);
    assert (pairIndex != -1);
    assert (pairIndex < this.overlappingPairArray.size());
    int index = this.hashTable.get(hash);
    assert (index != -1);
    int previous = -1;
    while (index != pairIndex)
    {
      previous = index;
      index = this.next.get(index);
    }
    if (previous != -1)
    {
      assert (this.next.get(previous) == pairIndex);
      this.next.set(previous, this.next.get(pairIndex));
    }
    else
    {
      this.hashTable.set(hash, this.next.get(pairIndex));
    }
    int lastPairIndex = this.overlappingPairArray.size() - 1;
    if (this.ghostPairCallback != null) {
      this.ghostPairCallback.removeOverlappingPair(proxy0, proxy1, dispatcher);
    }
    if (lastPairIndex == pairIndex)
    {
      this.overlappingPairArray.removeQuick(this.overlappingPairArray.size() - 1);
      return userData;
    }
    BroadphasePair last = (BroadphasePair)this.overlappingPairArray.getQuick(lastPairIndex);
    int lastHash = getHash(last.pProxy0.getUid(), last.pProxy1.getUid()) & this.overlappingPairArray.capacity() - 1;
    index = this.hashTable.get(lastHash);
    assert (index != -1);
    previous = -1;
    while (index != lastPairIndex)
    {
      previous = index;
      index = this.next.get(index);
    }
    if (previous != -1)
    {
      assert (this.next.get(previous) == lastPairIndex);
      this.next.set(previous, this.next.get(lastPairIndex));
    }
    else
    {
      this.hashTable.set(lastHash, this.next.get(lastPairIndex));
    }
    ((BroadphasePair)this.overlappingPairArray.getQuick(pairIndex)).set((BroadphasePair)this.overlappingPairArray.getQuick(lastPairIndex));
    this.next.set(pairIndex, this.hashTable.get(lastHash));
    this.hashTable.set(lastHash, pairIndex);
    this.overlappingPairArray.removeQuick(this.overlappingPairArray.size() - 1);
    return userData;
  }
  
  public boolean needsBroadphaseCollision(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
  {
    if (this.overlapFilterCallback != null) {
      return this.overlapFilterCallback.needBroadphaseCollision(proxy0, proxy1);
    }
    boolean collides = (proxy0.collisionFilterGroup & proxy1.collisionFilterMask) != 0;
    collides = (collides) && ((proxy1.collisionFilterGroup & proxy0.collisionFilterMask) != 0);
    return collides;
  }
  
  public void processAllOverlappingPairs(OverlapCallback callback, Dispatcher dispatcher)
  {
    int local_i = 0;
    while (local_i < this.overlappingPairArray.size())
    {
      BroadphasePair pair = (BroadphasePair)this.overlappingPairArray.getQuick(local_i);
      if (callback.processOverlap(pair))
      {
        removeOverlappingPair(pair.pProxy0, pair.pProxy1, dispatcher);
        BulletStats.gOverlappingPairs -= 1;
      }
      else
      {
        local_i++;
      }
    }
  }
  
  public void removeOverlappingPairsContainingProxy(BroadphaseProxy proxy, Dispatcher dispatcher)
  {
    processAllOverlappingPairs(new RemovePairCallback(proxy), dispatcher);
  }
  
  public void cleanProxyFromPairs(BroadphaseProxy proxy, Dispatcher dispatcher)
  {
    processAllOverlappingPairs(new CleanPairCallback(proxy, this, dispatcher), dispatcher);
  }
  
  public ObjectArrayList<BroadphasePair> getOverlappingPairArray()
  {
    return this.overlappingPairArray;
  }
  
  public void cleanOverlappingPair(BroadphasePair pair, Dispatcher dispatcher)
  {
    if (pair.algorithm != null)
    {
      dispatcher.freeCollisionAlgorithm(pair.algorithm);
      pair.algorithm = null;
    }
  }
  
  public BroadphasePair findPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
  {
    BulletStats.gFindPairs += 1;
    if (proxy0.getUid() > proxy1.getUid())
    {
      BroadphaseProxy tmp = proxy0;
      proxy0 = proxy1;
      proxy1 = proxy0;
    }
    int tmp = proxy0.getUid();
    int proxyId2 = proxy1.getUid();
    int hash = getHash(tmp, proxyId2) & this.overlappingPairArray.capacity() - 1;
    if (hash >= this.hashTable.size()) {
      return null;
    }
    for (int index = this.hashTable.get(hash); (index != -1) && (!equalsPair((BroadphasePair)this.overlappingPairArray.getQuick(index), tmp, proxyId2)); index = this.next.get(index)) {}
    if (index == -1) {
      return null;
    }
    assert (index < this.overlappingPairArray.size());
    return (BroadphasePair)this.overlappingPairArray.getQuick(index);
  }
  
  public int getCount()
  {
    return this.overlappingPairArray.size();
  }
  
  public OverlapFilterCallback getOverlapFilterCallback()
  {
    return this.overlapFilterCallback;
  }
  
  public void setOverlapFilterCallback(OverlapFilterCallback overlapFilterCallback)
  {
    this.overlapFilterCallback = overlapFilterCallback;
  }
  
  public int getNumOverlappingPairs()
  {
    return this.overlappingPairArray.size();
  }
  
  public boolean hasDeferredRemoval()
  {
    return false;
  }
  
  private BroadphasePair internalAddPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
  {
    if (proxy0.getUid() > proxy1.getUid())
    {
      BroadphaseProxy tmp = proxy0;
      proxy0 = proxy1;
      proxy1 = tmp;
    }
    int tmp = proxy0.getUid();
    int proxyId2 = proxy1.getUid();
    int hash = getHash(tmp, proxyId2) & this.overlappingPairArray.capacity() - 1;
    BroadphasePair pair = internalFindPair(proxy0, proxy1, hash);
    if (pair != null) {
      return pair;
    }
    int count = this.overlappingPairArray.size();
    int oldCapacity = this.overlappingPairArray.capacity();
    this.overlappingPairArray.add(null);
    if (this.ghostPairCallback != null) {
      this.ghostPairCallback.addOverlappingPair(proxy0, proxy1);
    }
    int newCapacity = this.overlappingPairArray.capacity();
    if (oldCapacity < newCapacity)
    {
      growTables();
      hash = getHash(tmp, proxyId2) & this.overlappingPairArray.capacity() - 1;
    }
    pair = new BroadphasePair(proxy0, proxy1);
    pair.algorithm = null;
    pair.userInfo = null;
    this.overlappingPairArray.setQuick(this.overlappingPairArray.size() - 1, pair);
    this.next.set(count, this.hashTable.get(hash));
    this.hashTable.set(hash, count);
    return pair;
  }
  
  private void growTables()
  {
    int newCapacity = this.overlappingPairArray.capacity();
    if (this.hashTable.size() < newCapacity)
    {
      int curHashtableSize = this.hashTable.size();
      MiscUtil.resize(this.hashTable, newCapacity, 0);
      MiscUtil.resize(this.next, newCapacity, 0);
      for (int local_i = 0; local_i < newCapacity; local_i++) {
        this.hashTable.set(local_i, -1);
      }
      for (int local_i = 0; local_i < newCapacity; local_i++) {
        this.next.set(local_i, -1);
      }
      for (int local_i = 0; local_i < curHashtableSize; local_i++)
      {
        BroadphasePair pair = (BroadphasePair)this.overlappingPairArray.getQuick(local_i);
        int proxyId1 = pair.pProxy0.getUid();
        int proxyId2 = pair.pProxy1.getUid();
        int hashValue = getHash(proxyId1, proxyId2) & this.overlappingPairArray.capacity() - 1;
        this.next.set(local_i, this.hashTable.get(hashValue));
        this.hashTable.set(hashValue, local_i);
      }
    }
  }
  
  private boolean equalsPair(BroadphasePair pair, int proxyId1, int proxyId2)
  {
    return (pair.pProxy0.getUid() == proxyId1) && (pair.pProxy1.getUid() == proxyId2);
  }
  
  private int getHash(int proxyId1, int proxyId2)
  {
    int key = proxyId1 | proxyId2 << 16;
    key += (key << 15 ^ 0xFFFFFFFF);
    key ^= key >>> 10;
    key += (key << 3);
    key ^= key >>> 6;
    key += (key << 11 ^ 0xFFFFFFFF);
    key ^= key >>> 16;
    return key;
  }
  
  private BroadphasePair internalFindPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, int hash)
  {
    int proxyId1 = proxy0.getUid();
    int proxyId2 = proxy1.getUid();
    for (int index = this.hashTable.get(hash); (index != -1) && (!equalsPair((BroadphasePair)this.overlappingPairArray.getQuick(index), proxyId1, proxyId2)); index = this.next.get(index)) {}
    if (index == -1) {
      return null;
    }
    assert (index < this.overlappingPairArray.size());
    return (BroadphasePair)this.overlappingPairArray.getQuick(index);
  }
  
  public void setInternalGhostPairCallback(OverlappingPairCallback ghostPairCallback)
  {
    this.ghostPairCallback = ghostPairCallback;
  }
  
  private static class CleanPairCallback
    extends OverlapCallback
  {
    private BroadphaseProxy cleanProxy;
    private OverlappingPairCache pairCache;
    private Dispatcher dispatcher;
    
    public CleanPairCallback(BroadphaseProxy cleanProxy, OverlappingPairCache pairCache, Dispatcher dispatcher)
    {
      this.cleanProxy = cleanProxy;
      this.pairCache = pairCache;
      this.dispatcher = dispatcher;
    }
    
    public boolean processOverlap(BroadphasePair pair)
    {
      if ((pair.pProxy0 == this.cleanProxy) || (pair.pProxy1 == this.cleanProxy)) {
        this.pairCache.cleanOverlappingPair(pair, this.dispatcher);
      }
      return false;
    }
  }
  
  private static class RemovePairCallback
    extends OverlapCallback
  {
    private BroadphaseProxy obsoleteProxy;
    
    public RemovePairCallback(BroadphaseProxy obsoleteProxy)
    {
      this.obsoleteProxy = obsoleteProxy;
    }
    
    public boolean processOverlap(BroadphasePair pair)
    {
      return (pair.pProxy0 == this.obsoleteProxy) || (pair.pProxy1 == this.obsoleteProxy);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.HashedOverlappingPairCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */