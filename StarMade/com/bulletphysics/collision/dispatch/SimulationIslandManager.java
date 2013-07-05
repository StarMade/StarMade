/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class SimulationIslandManager
/*     */ {
/*     */   private final UnionFind unionFind;
/*     */   private final ObjectArrayList<PersistentManifold> islandmanifold;
/*     */   private final ObjectArrayList<CollisionObject> islandBodies;
/* 335 */   private static final Comparator<PersistentManifold> persistentManifoldComparator = new Comparator() {
/*     */     public int compare(PersistentManifold lhs, PersistentManifold rhs) {
/* 337 */       return SimulationIslandManager.getIslandId(lhs) < SimulationIslandManager.getIslandId(rhs) ? -1 : 1;
/*     */     }
/* 335 */   };
/*     */ 
/*     */   public SimulationIslandManager()
/*     */   {
/*  41 */     this.unionFind = new UnionFind();
/*     */ 
/*  43 */     this.islandmanifold = new ObjectArrayList();
/*  44 */     this.islandBodies = new ObjectArrayList();
/*     */   }
/*     */   public void initUnionFind(int n) {
/*  47 */     this.unionFind.reset(n);
/*     */   }
/*     */ 
/*     */   public UnionFind getUnionFind() {
/*  51 */     return this.unionFind;
/*     */   }
/*     */ 
/*     */   public void findUnions(Dispatcher dispatcher, CollisionWorld colWorld) {
/*  55 */     ObjectArrayList pairPtr = colWorld.getPairCache().getOverlappingPairArray();
/*  56 */     for (int i = 0; i < pairPtr.size(); i++) {
/*  57 */       BroadphasePair collisionPair = (BroadphasePair)pairPtr.getQuick(i);
/*     */ 
/*  59 */       CollisionObject colObj0 = (CollisionObject)collisionPair.pProxy0.clientObject;
/*  60 */       CollisionObject colObj1 = (CollisionObject)collisionPair.pProxy1.clientObject;
/*     */ 
/*  62 */       if ((colObj0 != null) && (colObj0.mergesSimulationIslands()) && (colObj1 != null) && (colObj1.mergesSimulationIslands()))
/*     */       {
/*  64 */         this.unionFind.unite(colObj0.getIslandTag(), colObj1.getIslandTag());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateActivationState(CollisionWorld colWorld, Dispatcher dispatcher) {
/*  70 */     initUnionFind(colWorld.getCollisionObjectArray().size());
/*     */ 
/*  74 */     int index = 0;
/*     */ 
/*  76 */     for (int i = 0; i < colWorld.getCollisionObjectArray().size(); i++) {
/*  77 */       CollisionObject collisionObject = (CollisionObject)colWorld.getCollisionObjectArray().getQuick(i);
/*  78 */       collisionObject.setIslandTag(index);
/*  79 */       collisionObject.setCompanionId(-1);
/*  80 */       collisionObject.setHitFraction(1.0F);
/*  81 */       index++;
/*     */     }
/*     */ 
/*  86 */     findUnions(dispatcher, colWorld);
/*     */   }
/*     */ 
/*     */   public void storeIslandActivationState(CollisionWorld colWorld)
/*     */   {
/*  92 */     int index = 0;
/*     */ 
/*  94 */     for (int i = 0; i < colWorld.getCollisionObjectArray().size(); i++) {
/*  95 */       CollisionObject collisionObject = (CollisionObject)colWorld.getCollisionObjectArray().getQuick(i);
/*  96 */       if (!collisionObject.isStaticOrKinematicObject()) {
/*  97 */         collisionObject.setIslandTag(this.unionFind.find(index));
/*  98 */         collisionObject.setCompanionId(-1);
/*     */       }
/*     */       else {
/* 101 */         collisionObject.setIslandTag(-1);
/* 102 */         collisionObject.setCompanionId(-2);
/*     */       }
/* 104 */       index++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static int getIslandId(PersistentManifold lhs)
/*     */   {
/* 111 */     CollisionObject rcolObj0 = (CollisionObject)lhs.getBody0();
/* 112 */     CollisionObject rcolObj1 = (CollisionObject)lhs.getBody1();
/* 113 */     int islandId = rcolObj0.getIslandTag() >= 0 ? rcolObj0.getIslandTag() : rcolObj1.getIslandTag();
/* 114 */     return islandId;
/*     */   }
/*     */ 
/*     */   public void buildIslands(Dispatcher dispatcher, ObjectArrayList<CollisionObject> collisionObjects) {
/* 118 */     BulletStats.pushProfile("islandUnionFindAndQuickSort");
/*     */     try {
/* 120 */       this.islandmanifold.clear();
/*     */ 
/* 125 */       getUnionFind().sortIslands();
/* 126 */       int numElem = getUnionFind().getNumElements();
/*     */ 
/* 128 */       int endIslandIndex = 1;
/*     */ 
/* 132 */       for (int startIslandIndex = 0; startIslandIndex < numElem; startIslandIndex = endIslandIndex) {
/* 133 */         int islandId = getUnionFind().getElement(startIslandIndex).id;
/* 134 */         for (endIslandIndex = startIslandIndex + 1; (endIslandIndex < numElem) && (getUnionFind().getElement(endIslandIndex).id == islandId); endIslandIndex++);
/* 139 */         boolean allSleeping = true;
/*     */ 
/* 142 */         for (int idx = startIslandIndex; idx < endIslandIndex; idx++) {
/* 143 */           int i = getUnionFind().getElement(idx).sz;
/*     */ 
/* 145 */           CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 146 */           if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || (
/* 150 */             (!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) throw new AssertionError();
/* 151 */           if (colObj0.getIslandTag() == islandId) {
/* 152 */             if (colObj0.getActivationState() == 1) {
/* 153 */               allSleeping = false;
/*     */             }
/* 155 */             if (colObj0.getActivationState() == 4) {
/* 156 */               allSleeping = false;
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 162 */         if (allSleeping)
/*     */         {
/* 164 */           for (idx = startIslandIndex; idx < endIslandIndex; idx++) {
/* 165 */             int i = getUnionFind().getElement(idx).sz;
/* 166 */             CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 167 */             if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || (
/* 171 */               (!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) throw new AssertionError();
/*     */ 
/* 173 */             if (colObj0.getIslandTag() == islandId) {
/* 174 */               colObj0.setActivationState(2);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 181 */         for (idx = startIslandIndex; idx < endIslandIndex; idx++) {
/* 182 */           int i = getUnionFind().getElement(idx).sz;
/*     */ 
/* 184 */           CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 185 */           if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || (
/* 189 */             (!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) throw new AssertionError();
/*     */ 
/* 191 */           if ((colObj0.getIslandTag() == islandId) && 
/* 192 */             (colObj0.getActivationState() == 2)) {
/* 193 */             colObj0.setActivationState(3);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 202 */       int maxNumManifolds = dispatcher.getNumManifolds();
/*     */ 
/* 208 */       for (int i = 0; i < maxNumManifolds; i++) {
/* 209 */         PersistentManifold manifold = dispatcher.getManifoldByIndexInternal(i);
/*     */ 
/* 211 */         CollisionObject colObj0 = (CollisionObject)manifold.getBody0();
/* 212 */         CollisionObject colObj1 = (CollisionObject)manifold.getBody1();
/*     */ 
/* 215 */         if (((colObj0 != null) && (colObj0.getActivationState() != 2)) || ((colObj1 != null) && (colObj1.getActivationState() != 2)))
/*     */         {
/* 219 */           if ((colObj0.isKinematicObject()) && (colObj0.getActivationState() != 2)) {
/* 220 */             colObj1.activate();
/*     */           }
/* 222 */           if ((colObj1.isKinematicObject()) && (colObj1.getActivationState() != 2)) {
/* 223 */             colObj0.activate();
/*     */           }
/*     */ 
/* 227 */           if (dispatcher.needsResponse(colObj0, colObj1)) {
/* 228 */             this.islandmanifold.add(manifold);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 235 */       BulletStats.popProfile();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void buildAndProcessIslands(Dispatcher dispatcher, ObjectArrayList<CollisionObject> collisionObjects, IslandCallback callback) {
/* 240 */     buildIslands(dispatcher, collisionObjects);
/*     */ 
/* 242 */     int endIslandIndex = 1;
/*     */ 
/* 244 */     int numElem = getUnionFind().getNumElements();
/*     */ 
/* 246 */     BulletStats.pushProfile("processIslands");
/*     */     try
/*     */     {
/* 257 */       int numManifolds = this.islandmanifold.size();
/*     */ 
/* 264 */       MiscUtil.quickSort(this.islandmanifold, persistentManifoldComparator);
/*     */ 
/* 268 */       int startManifoldIndex = 0;
/* 269 */       int endManifoldIndex = 1;
/*     */ 
/* 276 */       for (int startIslandIndex = 0; startIslandIndex < numElem; startIslandIndex = endIslandIndex) {
/* 277 */         int islandId = getUnionFind().getElement(startIslandIndex).id;
/* 278 */         boolean islandSleeping = false;
/*     */ 
/* 280 */         for (endIslandIndex = startIslandIndex; (endIslandIndex < numElem) && (getUnionFind().getElement(endIslandIndex).id == islandId); endIslandIndex++) {
/* 281 */           int i = getUnionFind().getElement(endIslandIndex).sz;
/* 282 */           CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 283 */           this.islandBodies.add(colObj0);
/* 284 */           if (!colObj0.isActive()) {
/* 285 */             islandSleeping = true;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 291 */         int numIslandManifolds = 0;
/*     */ 
/* 293 */         int startManifold_idx = -1;
/*     */ 
/* 295 */         if (startManifoldIndex < numManifolds) {
/* 296 */           int curIslandId = getIslandId((PersistentManifold)this.islandmanifold.getQuick(startManifoldIndex));
/* 297 */           if (curIslandId == islandId)
/*     */           {
/* 300 */             startManifold_idx = startManifoldIndex;
/*     */ 
/* 302 */             for (endManifoldIndex = startManifoldIndex + 1; (endManifoldIndex < numManifolds) && (islandId == getIslandId((PersistentManifold)this.islandmanifold.getQuick(endManifoldIndex))); endManifoldIndex++);
/* 306 */             numIslandManifolds = endManifoldIndex - startManifoldIndex;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 311 */         if (!islandSleeping) {
/* 312 */           callback.processIsland(this.islandBodies, this.islandBodies.size(), this.islandmanifold, startManifold_idx, numIslandManifolds, islandId);
/*     */         }
/*     */ 
/* 316 */         if (numIslandManifolds != 0) {
/* 317 */           startManifoldIndex = endManifoldIndex;
/*     */         }
/*     */ 
/* 320 */         this.islandBodies.clear();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 325 */       BulletStats.popProfile();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class IslandCallback
/*     */   {
/*     */     public abstract void processIsland(ObjectArrayList<CollisionObject> paramObjectArrayList, int paramInt1, ObjectArrayList<PersistentManifold> paramObjectArrayList1, int paramInt2, int paramInt3, int paramInt4);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.SimulationIslandManager
 * JD-Core Version:    0.6.2
 */