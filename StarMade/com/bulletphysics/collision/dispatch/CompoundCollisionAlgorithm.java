/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ 
/*     */ public class CompoundCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/*  45 */   private final ObjectArrayList<CollisionAlgorithm> childCollisionAlgorithms = new ObjectArrayList();
/*     */   private boolean isSwapped;
/*     */ 
/*     */   public void init(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1, boolean isSwapped)
/*     */   {
/*  49 */     super.init(ci);
/*     */ 
/*  51 */     this.isSwapped = isSwapped;
/*     */ 
/*  53 */     CollisionObject colObj = isSwapped ? body1 : body0;
/*  54 */     CollisionObject otherObj = isSwapped ? body0 : body1;
/*  55 */     assert (colObj.getCollisionShape().isCompound());
/*     */ 
/*  57 */     CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
/*  58 */     int numChildren = compoundShape.getNumChildShapes();
/*     */ 
/*  62 */     for (int i = 0; i < numChildren; i++) {
/*  63 */       CollisionShape tmpShape = colObj.getCollisionShape();
/*  64 */       CollisionShape childShape = compoundShape.getChildShape(i);
/*  65 */       colObj.internalSetTemporaryCollisionShape(childShape);
/*  66 */       this.childCollisionAlgorithms.add(ci.dispatcher1.findAlgorithm(colObj, otherObj));
/*  67 */       colObj.internalSetTemporaryCollisionShape(tmpShape);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  73 */     int numChildren = this.childCollisionAlgorithms.size();
/*  74 */     for (int i = 0; i < numChildren; i++)
/*     */     {
/*  76 */       this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i));
/*     */     }
/*  78 */     this.childCollisionAlgorithms.clear();
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*     */   {
/*  83 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); CollisionObject colObj = this.isSwapped ? body1 : body0;
/*  84 */       CollisionObject otherObj = this.isSwapped ? body0 : body1;
/*     */ 
/*  86 */       assert (colObj.getCollisionShape().isCompound());
/*  87 */       CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
/*     */ 
/*  96 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  97 */       Transform orgTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  98 */       Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  99 */       Transform orgInterpolationTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 100 */       Transform newChildWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 102 */       int numChildren = this.childCollisionAlgorithms.size();
/*     */ 
/* 104 */       for (int i = 0; i < numChildren; i++)
/*     */       {
/* 106 */         CollisionShape childShape = compoundShape.getChildShape(i);
/*     */ 
/* 109 */         colObj.getWorldTransform(orgTrans);
/* 110 */         colObj.getInterpolationWorldTransform(orgInterpolationTrans);
/*     */ 
/* 112 */         compoundShape.getChildTransform(i, childTrans);
/* 113 */         newChildWorldTrans.mul(orgTrans, childTrans);
/* 114 */         colObj.setWorldTransform(newChildWorldTrans);
/* 115 */         colObj.setInterpolationWorldTransform(newChildWorldTrans);
/*     */ 
/* 118 */         CollisionShape tmpShape = colObj.getCollisionShape();
/* 119 */         colObj.internalSetTemporaryCollisionShape(childShape);
/* 120 */         ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).processCollision(colObj, otherObj, dispatchInfo, resultOut);
/*     */ 
/* 122 */         colObj.internalSetTemporaryCollisionShape(tmpShape);
/* 123 */         colObj.setWorldTransform(orgTrans);
/* 124 */         colObj.setInterpolationWorldTransform(orgInterpolationTrans);
/*     */       }return; } finally {
/* 126 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4) {
/* 130 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); CollisionObject colObj = this.isSwapped ? body1 : body0;
/* 131 */       CollisionObject otherObj = this.isSwapped ? body0 : body1;
/*     */ 
/* 133 */       assert (colObj.getCollisionShape().isCompound());
/*     */ 
/* 135 */       CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
/*     */ 
/* 144 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 145 */       Transform orgTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 146 */       Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 147 */       float hitFraction = 1.0F;
/*     */ 
/* 149 */       int numChildren = this.childCollisionAlgorithms.size();
/*     */ 
/* 151 */       for (int i = 0; i < numChildren; i++)
/*     */       {
/* 153 */         CollisionShape childShape = compoundShape.getChildShape(i);
/*     */ 
/* 156 */         colObj.getWorldTransform(orgTrans);
/*     */ 
/* 158 */         compoundShape.getChildTransform(i, childTrans);
/*     */ 
/* 160 */         tmpTrans.set(orgTrans);
/* 161 */         tmpTrans.mul(childTrans);
/* 162 */         colObj.setWorldTransform(tmpTrans);
/*     */ 
/* 164 */         CollisionShape tmpShape = colObj.getCollisionShape();
/* 165 */         colObj.internalSetTemporaryCollisionShape(childShape);
/* 166 */         float frac = ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).calculateTimeOfImpact(colObj, otherObj, dispatchInfo, resultOut);
/* 167 */         if (frac < hitFraction) {
/* 168 */           hitFraction = frac;
/*     */         }
/*     */ 
/* 171 */         colObj.internalSetTemporaryCollisionShape(tmpShape);
/* 172 */         colObj.setWorldTransform(orgTrans);
/*     */       }
/* 174 */       return hitFraction; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*     */   {
/* 179 */     for (int i = 0; i < this.childCollisionAlgorithms.size(); i++)
/* 180 */       ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).getAllContactManifolds(manifoldArray);
/*     */   }
/*     */ 
/*     */   public static class SwappedCreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 203 */     private final ObjectPool<CompoundCollisionAlgorithm> pool = ObjectPool.get(CompoundCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 207 */       CompoundCollisionAlgorithm algo = (CompoundCollisionAlgorithm)this.pool.get();
/* 208 */       algo.init(ci, body0, body1, true);
/* 209 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 214 */       this.pool.release((CompoundCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 187 */     private final ObjectPool<CompoundCollisionAlgorithm> pool = ObjectPool.get(CompoundCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 191 */       CompoundCollisionAlgorithm algo = (CompoundCollisionAlgorithm)this.pool.get();
/* 192 */       algo.init(ci, body0, body1, false);
/* 193 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 198 */       this.pool.release((CompoundCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CompoundCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */