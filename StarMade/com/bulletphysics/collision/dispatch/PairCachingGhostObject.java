/*  1:   */package com.bulletphysics.collision.dispatch;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*  4:   */import com.bulletphysics.collision.broadphase.Dispatcher;
/*  5:   */import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*  6:   */import com.bulletphysics.util.ObjectArrayList;
/*  7:   */
/* 33:   */public class PairCachingGhostObject
/* 34:   */  extends GhostObject
/* 35:   */{
/* 36:36 */  HashedOverlappingPairCache hashPairCache = new HashedOverlappingPairCache();
/* 37:   */  
/* 41:   */  public void addOverlappingObjectInternal(BroadphaseProxy otherProxy, BroadphaseProxy thisProxy)
/* 42:   */  {
/* 43:43 */    BroadphaseProxy actualThisProxy = thisProxy != null ? thisProxy : getBroadphaseHandle();
/* 44:44 */    assert (actualThisProxy != null);
/* 45:   */    
/* 46:46 */    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/* 47:47 */    assert (otherObject != null);
/* 48:   */    
/* 50:50 */    int index = this.overlappingObjects.indexOf(otherObject);
/* 51:51 */    if (index == -1) {
/* 52:52 */      this.overlappingObjects.add(otherObject);
/* 53:53 */      this.hashPairCache.addOverlappingPair(actualThisProxy, otherProxy);
/* 54:   */    }
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void removeOverlappingObjectInternal(BroadphaseProxy otherProxy, Dispatcher dispatcher, BroadphaseProxy thisProxy1)
/* 58:   */  {
/* 59:59 */    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/* 60:60 */    BroadphaseProxy actualThisProxy = thisProxy1 != null ? thisProxy1 : getBroadphaseHandle();
/* 61:61 */    assert (actualThisProxy != null);
/* 62:   */    
/* 63:63 */    assert (otherObject != null);
/* 64:64 */    int index = this.overlappingObjects.indexOf(otherObject);
/* 65:65 */    if (index != -1) {
/* 66:66 */      this.overlappingObjects.setQuick(index, this.overlappingObjects.getQuick(this.overlappingObjects.size() - 1));
/* 67:67 */      this.overlappingObjects.removeQuick(this.overlappingObjects.size() - 1);
/* 68:68 */      this.hashPairCache.removeOverlappingPair(actualThisProxy, otherProxy, dispatcher);
/* 69:   */    }
/* 70:   */  }
/* 71:   */  
/* 72:   */  public HashedOverlappingPairCache getOverlappingPairCache() {
/* 73:73 */    return this.hashPairCache;
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.PairCachingGhostObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */