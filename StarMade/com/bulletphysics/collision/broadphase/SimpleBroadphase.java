/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */
/*  35:    */public class SimpleBroadphase
/*  36:    */  extends BroadphaseInterface
/*  37:    */{
/*  38: 38 */  private final ObjectArrayList<SimpleBroadphaseProxy> handles = new ObjectArrayList();
/*  39:    */  private int maxHandles;
/*  40:    */  private OverlappingPairCache pairCache;
/*  41:    */  private boolean ownsPairCache;
/*  42:    */  
/*  43:    */  public SimpleBroadphase() {
/*  44: 44 */    this(16384, null);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public SimpleBroadphase(int maxProxies) {
/*  48: 48 */    this(maxProxies, null);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public SimpleBroadphase(int maxProxies, OverlappingPairCache overlappingPairCache) {
/*  52: 52 */    this.pairCache = overlappingPairCache;
/*  53:    */    
/*  54: 54 */    if (overlappingPairCache == null) {
/*  55: 55 */      this.pairCache = new HashedOverlappingPairCache();
/*  56: 56 */      this.ownsPairCache = true;
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  60:    */  public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy) {
/*  61: 61 */    assert ((aabbMin.x <= aabbMax.x) && (aabbMin.y <= aabbMax.y) && (aabbMin.z <= aabbMax.z));
/*  62:    */    
/*  63: 63 */    SimpleBroadphaseProxy proxy = new SimpleBroadphaseProxy(aabbMin, aabbMax, shapeType, userPtr, collisionFilterGroup, collisionFilterMask, multiSapProxy);
/*  64: 64 */    proxy.uniqueId = this.handles.size();
/*  65: 65 */    this.handles.add(proxy);
/*  66: 66 */    return proxy;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void destroyProxy(BroadphaseProxy proxyOrg, Dispatcher dispatcher) {
/*  70: 70 */    this.handles.remove(proxyOrg);
/*  71:    */    
/*  72: 72 */    this.pairCache.removeOverlappingPairsContainingProxy(proxyOrg, dispatcher);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setAabb(BroadphaseProxy proxy, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher) {
/*  76: 76 */    SimpleBroadphaseProxy sbp = (SimpleBroadphaseProxy)proxy;
/*  77: 77 */    sbp.min.set(aabbMin);
/*  78: 78 */    sbp.max.set(aabbMax);
/*  79:    */  }
/*  80:    */  
/*  81:    */  private static boolean aabbOverlap(SimpleBroadphaseProxy proxy0, SimpleBroadphaseProxy proxy1) {
/*  82: 82 */    return (proxy0.min.x <= proxy1.max.x) && (proxy1.min.x <= proxy0.max.x) && (proxy0.min.y <= proxy1.max.y) && (proxy1.min.y <= proxy0.max.y) && (proxy0.min.z <= proxy1.max.z) && (proxy1.min.z <= proxy0.max.z);
/*  83:    */  }
/*  84:    */  
/*  86:    */  public void calculateOverlappingPairs(Dispatcher dispatcher)
/*  87:    */  {
/*  88: 88 */    for (int i = 0; i < this.handles.size(); i++) {
/*  89: 89 */      SimpleBroadphaseProxy proxy0 = (SimpleBroadphaseProxy)this.handles.getQuick(i);
/*  90: 90 */      for (int j = 0; j < this.handles.size(); j++) {
/*  91: 91 */        SimpleBroadphaseProxy proxy1 = (SimpleBroadphaseProxy)this.handles.getQuick(j);
/*  92: 92 */        if (proxy0 != proxy1)
/*  93:    */        {
/*  94: 94 */          if (aabbOverlap(proxy0, proxy1)) {
/*  95: 95 */            if (this.pairCache.findPair(proxy0, proxy1) == null) {
/*  96: 96 */              this.pairCache.addOverlappingPair(proxy0, proxy1);
/*  98:    */            }
/*  99:    */            
/* 101:    */          }
/* 102:102 */          else if ((!this.pairCache.hasDeferredRemoval()) && 
/* 103:103 */            (this.pairCache.findPair(proxy0, proxy1) != null)) {
/* 104:104 */            this.pairCache.removeOverlappingPair(proxy0, proxy1, dispatcher);
/* 105:    */          }
/* 106:    */        }
/* 107:    */      }
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:    */  public OverlappingPairCache getOverlappingPairCache()
/* 112:    */  {
/* 113:113 */    return this.pairCache;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax) {
/* 117:117 */    aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/* 118:118 */    aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public void printStats() {}
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.SimpleBroadphase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */