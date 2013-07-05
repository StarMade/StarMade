/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld;
/*     */ import com.bulletphysics.collision.dispatch.SimulationIslandManager;
/*     */ import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
/*     */ import com.bulletphysics.collision.dispatch.UnionFind;
/*     */ import com.bulletphysics.collision.dispatch.UnionFind.Element;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class SimulationIslandManagerExt extends SimulationIslandManager
/*     */ {
/*  43 */   private final UnionFind unionFind = new UnionFind();
/*     */ 
/*  45 */   private final ObjectArrayList islandmanifold = new ObjectArrayList();
/*  46 */   private final ObjectArrayList islandBodies = new ObjectArrayList();
/*     */ 
/* 396 */   private static final Comparator persistentManifoldComparator = new SimulationIslandManagerExt.1();
/*     */ 
/*     */   public void initUnionFind(int paramInt)
/*     */   {
/*  49 */     this.unionFind.reset(paramInt);
/*     */   }
/*     */ 
/*     */   public UnionFind getUnionFind() {
/*  53 */     return this.unionFind;
/*     */   }
/*     */ 
/*     */   public void findUnions(Dispatcher paramDispatcher, CollisionWorld paramCollisionWorld) {
/*  57 */     paramDispatcher = paramCollisionWorld.getPairCache().getOverlappingPairArray();
/*  58 */     for (paramCollisionWorld = 0; paramCollisionWorld < paramDispatcher.size(); paramCollisionWorld++)
/*     */     {
/*  61 */       CollisionObject localCollisionObject = (CollisionObject)(
/*  61 */         localObject = (BroadphasePair)paramDispatcher.getQuick(paramCollisionWorld)).pProxy0.clientObject;
/*     */ 
/*  62 */       Object localObject = (CollisionObject)((BroadphasePair)localObject).pProxy1.clientObject;
/*     */ 
/*  64 */       if ((localCollisionObject != null) && (localCollisionObject.mergesSimulationIslands()) && (localObject != null) && (((CollisionObject)localObject).mergesSimulationIslands()))
/*     */       {
/*  66 */         this.unionFind.unite(localCollisionObject.getIslandTag(), ((CollisionObject)localObject).getIslandTag());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateActivationState(CollisionWorld paramCollisionWorld, Dispatcher paramDispatcher) {
/*  72 */     initUnionFind(paramCollisionWorld.getCollisionObjectArray().size());
/*     */ 
/*  76 */     int i = 0;
/*     */ 
/*  78 */     for (int j = 0; j < paramCollisionWorld.getCollisionObjectArray().size(); j++)
/*     */     {
/*     */       CollisionObject localCollisionObject;
/*  79 */       (
/*  80 */         localCollisionObject = (CollisionObject)paramCollisionWorld.getCollisionObjectArray().getQuick(j))
/*  80 */         .setIslandTag(i);
/*  81 */       localCollisionObject.setCompanionId(-1);
/*  82 */       localCollisionObject.setHitFraction(1.0F);
/*  83 */       i++;
/*     */     }
/*     */ 
/*  88 */     findUnions(paramDispatcher, paramCollisionWorld);
/*     */   }
/*     */ 
/*     */   public void storeIslandActivationState(CollisionWorld paramCollisionWorld)
/*     */   {
/*  94 */     int i = 0;
/*     */ 
/*  96 */     for (int j = 0; j < paramCollisionWorld.getCollisionObjectArray().size(); j++)
/*     */     {
/*     */       CollisionObject localCollisionObject;
/*  98 */       if (!(
/*  98 */         localCollisionObject = (CollisionObject)paramCollisionWorld.getCollisionObjectArray().getQuick(j))
/*  98 */         .isStaticOrKinematicObject()) {
/*  99 */         localCollisionObject.setIslandTag(this.unionFind.find(i));
/* 100 */         localCollisionObject.setCompanionId(-1);
/*     */       }
/*     */       else {
/* 103 */         localCollisionObject.setIslandTag(-1);
/* 104 */         localCollisionObject.setCompanionId(-2);
/*     */       }
/* 106 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static int getIslandId(PersistentManifold paramPersistentManifold)
/*     */   {
/* 113 */     CollisionObject localCollisionObject = (CollisionObject)paramPersistentManifold.getBody0();
/* 114 */     paramPersistentManifold = (CollisionObject)paramPersistentManifold.getBody1();
/* 115 */     if (localCollisionObject.getIslandTag() >= 0) return localCollisionObject.getIslandTag();
/* 116 */     return paramPersistentManifold.getIslandTag();
/*     */   }
/*     */ 
/*     */   public void buildIslands(Dispatcher paramDispatcher, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 120 */     BulletStats.pushProfile("islandUnionFindAndQuickSort");
/*     */     try {
/* 122 */       this.islandmanifold.clear();
/*     */ 
/* 127 */       getUnionFind().sortIslands();
/* 128 */       int i = getUnionFind().getNumElements();
/*     */ 
/* 130 */       int j = 0;
/*     */       CollisionObject localCollisionObject3;
/* 134 */       for (int k = 0; k < i; k = j) {
/* 135 */         m = getUnionFind().getElement(k).id;
/* 136 */         for (j = k + 1; (j < i) && (getUnionFind().getElement(j).id == m); j++);
/* 141 */         n = 1;
/*     */         int i2;
/* 144 */         for (int i1 = k; i1 < j; i1++) {
/* 145 */           i2 = getUnionFind().getElement(i1).sz;
/*     */ 
/* 148 */           if ((
/* 148 */             localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2))
/* 148 */             .getIslandTag() != m) localCollisionObject3.getIslandTag();
/*     */ 
/* 152 */           assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
/* 153 */           if (localCollisionObject3.getIslandTag() == m) {
/* 154 */             if (localCollisionObject3.getActivationState() == 1) {
/* 155 */               n = 0;
/*     */             }
/* 157 */             if (localCollisionObject3.getActivationState() == 4) {
/* 158 */               n = 0;
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 164 */         if (n != 0)
/*     */         {
/* 166 */           for (i1 = k; i1 < j; i1++) {
/* 167 */             i2 = getUnionFind().getElement(i1).sz;
/*     */ 
/* 169 */             if ((
/* 169 */               localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2))
/* 169 */               .getIslandTag() != m) localCollisionObject3.getIslandTag();
/*     */ 
/* 173 */             assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
/*     */ 
/* 175 */             if (localCollisionObject3.getIslandTag() == m) {
/* 176 */               localCollisionObject3.setActivationState(2);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 183 */         for (i1 = k; i1 < j; i1++) {
/* 184 */           i2 = getUnionFind().getElement(i1).sz;
/*     */ 
/* 187 */           if ((
/* 187 */             localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2))
/* 187 */             .getIslandTag() != m) localCollisionObject3.getIslandTag();
/*     */ 
/* 191 */           assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
/*     */ 
/* 193 */           if ((localCollisionObject3.getIslandTag() == m) && 
/* 194 */             (localCollisionObject3.getActivationState() == 2)) {
/* 195 */             localCollisionObject3.setActivationState(3);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 204 */       int n = paramDispatcher.getNumManifolds();
/*     */ 
/* 210 */       for (int m = 0; m < n; m++)
/*     */       {
/*     */         PersistentManifold localPersistentManifold;
/* 213 */         CollisionObject localCollisionObject2 = (CollisionObject)(
/* 213 */           localPersistentManifold = paramDispatcher.getManifoldByIndexInternal(m))
/* 213 */           .getBody0();
/* 214 */         localCollisionObject3 = (CollisionObject)localPersistentManifold.getBody1();
/*     */ 
/* 216 */         i = 0;
/* 217 */         for (j = 0; j < paramObjectArrayList.size(); j++)
/*     */         {
/*     */           CollisionObject localCollisionObject1;
/* 219 */           if (((
/* 219 */             localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j)) == 
/* 219 */             localCollisionObject2) || (localCollisionObject1 == localCollisionObject3)) {
/* 220 */             i = 1;
/*     */           }
/*     */         }
/* 223 */         if (i == 0) {
/* 224 */           for (j = 0; j < paramObjectArrayList.size(); j++) {
/* 225 */             paramObjectArrayList.getQuick(j);
/*     */           }
/*     */         }
/*     */ 
/* 229 */         assert (i != 0) : ("MANIFOLD OBJECTS NOT FOUND " + localCollisionObject2 + "; " + localCollisionObject3);
/*     */ 
/* 233 */         if (((localCollisionObject2 != null) && (localCollisionObject2.getActivationState() != 2)) || ((localCollisionObject3 != null) && (localCollisionObject3.getActivationState() != 2)))
/*     */         {
/* 237 */           if ((localCollisionObject2.isKinematicObject()) && (localCollisionObject2.getActivationState() != 2)) {
/* 238 */             localCollisionObject3.activate();
/*     */           }
/* 240 */           if ((localCollisionObject3.isKinematicObject()) && (localCollisionObject3.getActivationState() != 2)) {
/* 241 */             localCollisionObject2.activate();
/*     */           }
/*     */ 
/* 245 */           if (paramDispatcher.needsResponse(localCollisionObject2, localCollisionObject3))
/*     */           {
/* 254 */             this.islandmanifold.add(localPersistentManifold);
/*     */           }
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally {
/* 261 */       BulletStats.popProfile(); } throw paramDispatcher;
/*     */   }
/*     */ 
/*     */   public void buildAndProcessIslands(Dispatcher paramDispatcher, ObjectArrayList paramObjectArrayList, SimulationIslandManager.IslandCallback paramIslandCallback)
/*     */   {
/* 266 */     buildIslands(paramDispatcher, paramObjectArrayList);
/*     */     int j;
/* 268 */     for (paramDispatcher = 0; paramDispatcher < this.islandmanifold.size(); paramDispatcher++)
/*     */     {
/*     */       PersistentManifold localPersistentManifold;
/* 270 */       if (((
/* 270 */         localPersistentManifold = (PersistentManifold)this.islandmanifold.getQuick(paramDispatcher))
/* 270 */         .getBody0().toString().contains("|CLI")) || (localPersistentManifold.getBody1().toString().contains("|CLI"))) {
/* 271 */         localDispatcher2 = 0;
/*     */         CollisionObject localCollisionObject1;
/* 272 */         for (j = 0; j < paramObjectArrayList.size(); j++)
/*     */         {
/* 274 */           if (((
/* 274 */             localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j)) == 
/* 274 */             localPersistentManifold.getBody0()) || (localCollisionObject1 == localPersistentManifold.getBody1())) {
/* 275 */             localDispatcher2 = 1;
/*     */           }
/*     */         }
/* 278 */         if (localDispatcher2 == 0) {
/* 279 */           for (j = 0; j < paramObjectArrayList.size(); j++) {
/* 280 */             localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j);
/* 281 */             System.err.println("#" + j + " COLLISION OBJECTS: " + localCollisionObject1);
/*     */           }
/*     */         }
/* 284 */         assert (localDispatcher2 != 0) : ("MANIFOLD OBJECTS NOT FOUND " + localPersistentManifold.getBody0() + "; " + localPersistentManifold.getBody1());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 290 */     Dispatcher localDispatcher2 = getUnionFind().getNumElements();
/*     */ 
/* 294 */     BulletStats.pushProfile("processIslands");
/*     */     try
/*     */     {
/* 305 */       j = this.islandmanifold.size();
/*     */ 
/* 312 */       MiscUtil.quickSort(this.islandmanifold, persistentManifoldComparator);
/*     */ 
/* 316 */       int k = 0;
/* 317 */       int m = 1;
/*     */       int i;
/* 324 */       for (Dispatcher localDispatcher1 = 0; localDispatcher1 < localDispatcher2; i = paramDispatcher) {
/* 325 */         int n = getUnionFind().getElement(localDispatcher1).id;
/* 326 */         int i1 = 0;
/*     */ 
/* 328 */         for (paramDispatcher = localDispatcher1; (paramDispatcher < localDispatcher2) && (getUnionFind().getElement(paramDispatcher).id == n); paramDispatcher++) {
/* 329 */           localDispatcher1 = getUnionFind().getElement(paramDispatcher).sz;
/* 330 */           CollisionObject localCollisionObject2 = (CollisionObject)paramObjectArrayList.getQuick(localDispatcher1);
/* 331 */           this.islandBodies.add(localCollisionObject2);
/* 332 */           if (!localCollisionObject2.isActive()) {
/* 333 */             i1 = 1;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 339 */         localDispatcher1 = 0;
/*     */ 
/* 341 */         int i2 = -1;
/*     */ 
/* 343 */         if (k < j)
/*     */         {
/* 345 */           if (getIslandId((PersistentManifold)this.islandmanifold.getQuick(k)) == 
/* 345 */             n)
/*     */           {
/* 348 */             i2 = k;
/*     */ 
/* 350 */             for (m = k + 1; (m < j) && (n == getIslandId((PersistentManifold)this.islandmanifold.getQuick(m))); m++);
/* 354 */             localDispatcher1 = m - k;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 359 */         if (i1 == 0)
/*     */         {
/* 373 */           paramIslandCallback.processIsland(this.islandBodies, this.islandBodies.size(), this.islandmanifold, i2, localDispatcher1, n);
/*     */         }
/*     */ 
/* 377 */         if (localDispatcher1 != 0) {
/* 378 */           k = m;
/*     */         }
/*     */ 
/* 381 */         this.islandBodies.clear();
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally {
/* 386 */       BulletStats.popProfile(); } throw paramDispatcher;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SimulationIslandManagerExt
 * JD-Core Version:    0.6.2
 */