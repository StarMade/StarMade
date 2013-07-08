/*  1:   */package com.bulletphysics.collision.dispatch;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*  4:   */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*  5:   */import com.bulletphysics.collision.broadphase.Dispatcher;
/*  6:   */import com.bulletphysics.collision.broadphase.OverlappingPairCallback;
/*  7:   */
/* 36:   */public class GhostPairCallback
/* 37:   */  extends OverlappingPairCallback
/* 38:   */{
/* 39:   */  public BroadphasePair addOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
/* 40:   */  {
/* 41:41 */    CollisionObject colObj0 = (CollisionObject)proxy0.clientObject;
/* 42:42 */    CollisionObject colObj1 = (CollisionObject)proxy1.clientObject;
/* 43:43 */    GhostObject ghost0 = GhostObject.upcast(colObj0);
/* 44:44 */    GhostObject ghost1 = GhostObject.upcast(colObj1);
/* 45:   */    
/* 46:46 */    if (ghost0 != null) {
/* 47:47 */      ghost0.addOverlappingObjectInternal(proxy1, proxy0);
/* 48:   */    }
/* 49:49 */    if (ghost1 != null) {
/* 50:50 */      ghost1.addOverlappingObjectInternal(proxy0, proxy1);
/* 51:   */    }
/* 52:52 */    return null;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public Object removeOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, Dispatcher dispatcher) {
/* 56:56 */    CollisionObject colObj0 = (CollisionObject)proxy0.clientObject;
/* 57:57 */    CollisionObject colObj1 = (CollisionObject)proxy1.clientObject;
/* 58:58 */    GhostObject ghost0 = GhostObject.upcast(colObj0);
/* 59:59 */    GhostObject ghost1 = GhostObject.upcast(colObj1);
/* 60:   */    
/* 61:61 */    if (ghost0 != null) {
/* 62:62 */      ghost0.removeOverlappingObjectInternal(proxy1, dispatcher, proxy0);
/* 63:   */    }
/* 64:64 */    if (ghost1 != null) {
/* 65:65 */      ghost1.removeOverlappingObjectInternal(proxy0, dispatcher, proxy1);
/* 66:   */    }
/* 67:67 */    return null;
/* 68:   */  }
/* 69:   */  
/* 70:   */  public void removeOverlappingPairsContainingProxy(BroadphaseProxy proxy0, Dispatcher dispatcher) {
/* 71:71 */    if (!$assertionsDisabled) throw new AssertionError();
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.GhostPairCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */