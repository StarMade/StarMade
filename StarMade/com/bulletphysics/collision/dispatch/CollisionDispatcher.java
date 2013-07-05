/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.broadphase.OverlapCallback;
/*     */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collections;
/*     */ 
/*     */ public class CollisionDispatcher extends Dispatcher
/*     */ {
/*  47 */   protected final ObjectPool<PersistentManifold> manifoldsPool = ObjectPool.get(PersistentManifold.class);
/*     */ 
/*  49 */   private static final int MAX_BROADPHASE_COLLISION_TYPES = BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal();
/*  50 */   private int count = 0;
/*  51 */   private final ObjectArrayList<PersistentManifold> manifoldsPtr = new ObjectArrayList();
/*  52 */   private boolean useIslands = true;
/*  53 */   private boolean staticWarningReported = false;
/*     */   private ManifoldResult defaultManifoldResult;
/*     */   private NearCallback nearCallback;
/*  58 */   private final CollisionAlgorithmCreateFunc[][] doubleDispatch = new CollisionAlgorithmCreateFunc[MAX_BROADPHASE_COLLISION_TYPES][MAX_BROADPHASE_COLLISION_TYPES];
/*     */   private CollisionConfiguration collisionConfiguration;
/*  62 */   private CollisionAlgorithmConstructionInfo tmpCI = new CollisionAlgorithmConstructionInfo();
/*     */ 
/* 241 */   private CollisionPairCallback collisionPairCallback = new CollisionPairCallback(null);
/*     */ 
/*     */   public CollisionDispatcher(CollisionConfiguration collisionConfiguration)
/*     */   {
/*  65 */     this.collisionConfiguration = collisionConfiguration;
/*     */ 
/*  67 */     setNearCallback(new DefaultNearCallback());
/*     */ 
/*  72 */     for (int i = 0; i < MAX_BROADPHASE_COLLISION_TYPES; i++)
/*  73 */       for (int j = 0; j < MAX_BROADPHASE_COLLISION_TYPES; j++) {
/*  74 */         this.doubleDispatch[i][j] = collisionConfiguration.getCollisionAlgorithmCreateFunc(BroadphaseNativeType.forValue(i), BroadphaseNativeType.forValue(j));
/*     */ 
/*  78 */         assert (this.doubleDispatch[i][j] != null);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void registerCollisionCreateFunc(int proxyType0, int proxyType1, CollisionAlgorithmCreateFunc createFunc)
/*     */   {
/*  84 */     this.doubleDispatch[proxyType0][proxyType1] = createFunc;
/*     */   }
/*     */ 
/*     */   public NearCallback getNearCallback() {
/*  88 */     return this.nearCallback;
/*     */   }
/*     */ 
/*     */   public void setNearCallback(NearCallback nearCallback) {
/*  92 */     this.nearCallback = nearCallback;
/*     */   }
/*     */ 
/*     */   public CollisionConfiguration getCollisionConfiguration() {
/*  96 */     return this.collisionConfiguration;
/*     */   }
/*     */ 
/*     */   public void setCollisionConfiguration(CollisionConfiguration collisionConfiguration) {
/* 100 */     this.collisionConfiguration = collisionConfiguration;
/*     */   }
/*     */ 
/*     */   public CollisionAlgorithm findAlgorithm(CollisionObject body0, CollisionObject body1, PersistentManifold sharedManifold)
/*     */   {
/* 105 */     CollisionAlgorithmConstructionInfo ci = this.tmpCI;
/* 106 */     ci.dispatcher1 = this;
/* 107 */     ci.manifold = sharedManifold;
/* 108 */     CollisionAlgorithmCreateFunc createFunc = this.doubleDispatch[body0.getCollisionShape().getShapeType().ordinal()][body1.getCollisionShape().getShapeType().ordinal()];
/* 109 */     CollisionAlgorithm algo = createFunc.createCollisionAlgorithm(ci, body0, body1);
/* 110 */     algo.internalSetCreateFunc(createFunc);
/*     */ 
/* 112 */     return algo;
/*     */   }
/*     */ 
/*     */   public void freeCollisionAlgorithm(CollisionAlgorithm algo)
/*     */   {
/* 117 */     CollisionAlgorithmCreateFunc createFunc = algo.internalGetCreateFunc();
/* 118 */     algo.internalSetCreateFunc(null);
/* 119 */     createFunc.releaseCollisionAlgorithm(algo);
/* 120 */     algo.destroy();
/*     */   }
/*     */ 
/*     */   public PersistentManifold getNewManifold(Object b0, Object b1)
/*     */   {
/* 129 */     CollisionObject body0 = (CollisionObject)b0;
/* 130 */     CollisionObject body1 = (CollisionObject)b1;
/*     */ 
/* 148 */     PersistentManifold manifold = (PersistentManifold)this.manifoldsPool.get();
/* 149 */     manifold.init(body0, body1, 0);
/*     */ 
/* 151 */     manifold.index1a = this.manifoldsPtr.size();
/* 152 */     this.manifoldsPtr.add(manifold);
/*     */ 
/* 154 */     return manifold;
/*     */   }
/*     */ 
/*     */   public void releaseManifold(PersistentManifold manifold)
/*     */   {
/* 162 */     clearManifold(manifold);
/*     */ 
/* 165 */     int findIndex = manifold.index1a;
/* 166 */     assert (findIndex < this.manifoldsPtr.size());
/* 167 */     Collections.swap(this.manifoldsPtr, findIndex, this.manifoldsPtr.size() - 1);
/* 168 */     ((PersistentManifold)this.manifoldsPtr.getQuick(findIndex)).index1a = findIndex;
/* 169 */     this.manifoldsPtr.removeQuick(this.manifoldsPtr.size() - 1);
/*     */ 
/* 171 */     this.manifoldsPool.release(manifold);
/*     */   }
/*     */ 
/*     */   public void clearManifold(PersistentManifold manifold)
/*     */   {
/* 186 */     manifold.clearManifold();
/*     */   }
/*     */ 
/*     */   public boolean needsCollision(CollisionObject body0, CollisionObject body1)
/*     */   {
/* 191 */     assert (body0 != null);
/* 192 */     assert (body1 != null);
/*     */ 
/* 194 */     boolean needsCollision = true;
/*     */ 
/* 197 */     if (!this.staticWarningReported)
/*     */     {
/* 199 */       if (((body0.isStaticObject()) || (body0.isKinematicObject())) && ((body1.isStaticObject()) || (body1.isKinematicObject())))
/*     */       {
/* 201 */         this.staticWarningReported = true;
/* 202 */         System.err.println("warning CollisionDispatcher.needsCollision: static-static collision!");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 207 */     if ((!body0.isActive()) && (!body1.isActive())) {
/* 208 */       needsCollision = false;
/*     */     }
/* 210 */     else if (!body0.checkCollideWith(body1)) {
/* 211 */       needsCollision = false;
/*     */     }
/*     */ 
/* 214 */     return needsCollision;
/*     */   }
/*     */ 
/*     */   public boolean needsResponse(CollisionObject body0, CollisionObject body1)
/*     */   {
/* 220 */     boolean hasResponse = (body0.hasContactResponse()) && (body1.hasContactResponse());
/*     */ 
/* 222 */     hasResponse = (hasResponse) && ((!body0.isStaticOrKinematicObject()) || (!body1.isStaticOrKinematicObject()));
/* 223 */     return hasResponse;
/*     */   }
/*     */ 
/*     */   public void dispatchAllCollisionPairs(OverlappingPairCache pairCache, DispatcherInfo dispatchInfo, Dispatcher dispatcher)
/*     */   {
/* 246 */     this.collisionPairCallback.init(dispatchInfo, this);
/* 247 */     pairCache.processAllOverlappingPairs(this.collisionPairCallback, dispatcher);
/*     */   }
/*     */ 
/*     */   public int getNumManifolds()
/*     */   {
/* 253 */     return this.manifoldsPtr.size();
/*     */   }
/*     */ 
/*     */   public PersistentManifold getManifoldByIndexInternal(int index)
/*     */   {
/* 258 */     return (PersistentManifold)this.manifoldsPtr.getQuick(index);
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<PersistentManifold> getInternalManifoldPointer()
/*     */   {
/* 263 */     return this.manifoldsPtr;
/*     */   }
/*     */ 
/*     */   private static class CollisionPairCallback extends OverlapCallback
/*     */   {
/*     */     private DispatcherInfo dispatchInfo;
/*     */     private CollisionDispatcher dispatcher;
/*     */ 
/*     */     public void init(DispatcherInfo dispatchInfo, CollisionDispatcher dispatcher)
/*     */     {
/* 231 */       this.dispatchInfo = dispatchInfo;
/* 232 */       this.dispatcher = dispatcher;
/*     */     }
/*     */ 
/*     */     public boolean processOverlap(BroadphasePair pair) {
/* 236 */       this.dispatcher.getNearCallback().handleCollision(pair, this.dispatcher, this.dispatchInfo);
/* 237 */       return false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionDispatcher
 * JD-Core Version:    0.6.2
 */