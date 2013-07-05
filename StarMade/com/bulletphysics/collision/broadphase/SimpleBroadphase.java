/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class SimpleBroadphase extends BroadphaseInterface
/*     */ {
/*  38 */   private final ObjectArrayList<SimpleBroadphaseProxy> handles = new ObjectArrayList();
/*     */   private int maxHandles;
/*     */   private OverlappingPairCache pairCache;
/*     */   private boolean ownsPairCache;
/*     */ 
/*     */   public SimpleBroadphase()
/*     */   {
/*  44 */     this(16384, null);
/*     */   }
/*     */ 
/*     */   public SimpleBroadphase(int maxProxies) {
/*  48 */     this(maxProxies, null);
/*     */   }
/*     */ 
/*     */   public SimpleBroadphase(int maxProxies, OverlappingPairCache overlappingPairCache) {
/*  52 */     this.pairCache = overlappingPairCache;
/*     */ 
/*  54 */     if (overlappingPairCache == null) {
/*  55 */       this.pairCache = new HashedOverlappingPairCache();
/*  56 */       this.ownsPairCache = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy) {
/*  61 */     assert ((aabbMin.x <= aabbMax.x) && (aabbMin.y <= aabbMax.y) && (aabbMin.z <= aabbMax.z));
/*     */ 
/*  63 */     SimpleBroadphaseProxy proxy = new SimpleBroadphaseProxy(aabbMin, aabbMax, shapeType, userPtr, collisionFilterGroup, collisionFilterMask, multiSapProxy);
/*  64 */     proxy.uniqueId = this.handles.size();
/*  65 */     this.handles.add(proxy);
/*  66 */     return proxy;
/*     */   }
/*     */ 
/*     */   public void destroyProxy(BroadphaseProxy proxyOrg, Dispatcher dispatcher) {
/*  70 */     this.handles.remove(proxyOrg);
/*     */ 
/*  72 */     this.pairCache.removeOverlappingPairsContainingProxy(proxyOrg, dispatcher);
/*     */   }
/*     */ 
/*     */   public void setAabb(BroadphaseProxy proxy, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher) {
/*  76 */     SimpleBroadphaseProxy sbp = (SimpleBroadphaseProxy)proxy;
/*  77 */     sbp.min.set(aabbMin);
/*  78 */     sbp.max.set(aabbMax);
/*     */   }
/*     */ 
/*     */   private static boolean aabbOverlap(SimpleBroadphaseProxy proxy0, SimpleBroadphaseProxy proxy1) {
/*  82 */     return (proxy0.min.x <= proxy1.max.x) && (proxy1.min.x <= proxy0.max.x) && (proxy0.min.y <= proxy1.max.y) && (proxy1.min.y <= proxy0.max.y) && (proxy0.min.z <= proxy1.max.z) && (proxy1.min.z <= proxy0.max.z);
/*     */   }
/*     */ 
/*     */   public void calculateOverlappingPairs(Dispatcher dispatcher)
/*     */   {
/*  88 */     for (int i = 0; i < this.handles.size(); i++) {
/*  89 */       SimpleBroadphaseProxy proxy0 = (SimpleBroadphaseProxy)this.handles.getQuick(i);
/*  90 */       for (int j = 0; j < this.handles.size(); j++) {
/*  91 */         SimpleBroadphaseProxy proxy1 = (SimpleBroadphaseProxy)this.handles.getQuick(j);
/*  92 */         if (proxy0 != proxy1)
/*     */         {
/*  94 */           if (aabbOverlap(proxy0, proxy1)) {
/*  95 */             if (this.pairCache.findPair(proxy0, proxy1) == null) {
/*  96 */               this.pairCache.addOverlappingPair(proxy0, proxy1);
/*     */             }
/*     */ 
/*     */           }
/* 102 */           else if ((!this.pairCache.hasDeferredRemoval()) && 
/* 103 */             (this.pairCache.findPair(proxy0, proxy1) != null))
/* 104 */             this.pairCache.removeOverlappingPair(proxy0, proxy1, dispatcher);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public OverlappingPairCache getOverlappingPairCache()
/*     */   {
/* 113 */     return this.pairCache;
/*     */   }
/*     */ 
/*     */   public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax) {
/* 117 */     aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/* 118 */     aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*     */   }
/*     */ 
/*     */   public void printStats()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.SimpleBroadphase
 * JD-Core Version:    0.6.2
 */