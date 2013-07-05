/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.dynamics.DynamicsWorld;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ 
/*     */ public class CompoundCollisionAlgorithmExt extends CollisionAlgorithm
/*     */ {
/*     */   private CollisionObject compoundObject;
/*     */   private CollisionObject otherObject;
/*     */   public boolean swapped;
/*     */   private PersistentManifold manifoldPtr;
/*  30 */   private static ThreadLocal threadLocal = new CompoundCollisionAlgorithmExt.1();
/*     */ 
/*  84 */   private final ObjectArrayList childCollisionAlgorithms = new ObjectArrayList();
/*  85 */   private final ObjectArrayList childCollisionAlgorithmsCOM = new ObjectArrayList();
/*     */   private CompoundCollisionVariableSet v;
/*     */ 
/*     */   public String toString()
/*     */   {
/* 117 */     return "CompoundAlgo[" + this.compoundObject + "->" + this.otherObject + "]";
/*     */   }
/*     */ 
/*     */   public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo) {
/* 121 */     super.init(paramCollisionAlgorithmConstructionInfo);
/*     */ 
/* 128 */     this.v = ((CompoundCollisionVariableSet)threadLocal.get());
/* 129 */     this.v.instances += 1;
/*     */ 
/* 131 */     assert (this.compoundObject.getCollisionShape().isCompound());
/*     */ 
/* 133 */     assert ((!(this.compoundObject.getCollisionShape() instanceof CubeShape)) && (!(this.otherObject.getCollisionShape() instanceof CubeShape))) : (this.compoundObject + " --- " + this.otherObject);
/*     */     CompoundShape localCompoundShape;
/* 136 */     int i = (
/* 136 */       localCompoundShape = (CompoundShape)this.compoundObject.getCollisionShape())
/* 136 */       .getNumChildShapes();
/*     */ 
/* 138 */     for (int j = 0; j < i; 
/* 140 */       j++) {
/* 141 */       CollisionShape localCollisionShape1 = this.compoundObject.getCollisionShape();
/* 142 */       Object localObject = localCompoundShape.getChildShape(j);
/* 143 */       this.compoundObject.internalSetTemporaryCollisionShape((CollisionShape)localObject);
/*     */ 
/* 146 */       if (this.otherObject.getCollisionShape().isCompound()) {
/* 147 */         localObject = (CompoundShape)this.otherObject.getCollisionShape();
/* 148 */         for (int k = 0; k < ((CompoundShape)localObject).getNumChildShapes(); k++)
/*     */         {
/* 150 */           CollisionShape localCollisionShape2 = this.otherObject.getCollisionShape();
/* 151 */           CollisionShape localCollisionShape3 = ((CompoundShape)localObject).getChildShape(k);
/* 152 */           this.otherObject.internalSetTemporaryCollisionShape(localCollisionShape3);
/*     */ 
/* 154 */           this.childCollisionAlgorithmsCOM.add(paramCollisionAlgorithmConstructionInfo.dispatcher1.findAlgorithm(this.compoundObject, this.otherObject));
/*     */ 
/* 156 */           this.otherObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/*     */         }
/*     */       } else {
/* 159 */         this.childCollisionAlgorithms.add(paramCollisionAlgorithmConstructionInfo.dispatcher1.findAlgorithm(this.compoundObject, this.otherObject));
/*     */       }
/*     */ 
/* 164 */       this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 171 */     if (this.manifoldPtr != null)
/*     */     {
/* 173 */       this.dispatcher.releaseManifold(this.manifoldPtr);
/* 174 */       this.manifoldPtr = null;
/*     */     }
/* 176 */     else if (!$assertionsDisabled) { throw new AssertionError(); }
/*     */ 
/*     */ 
/* 179 */     int i = this.childCollisionAlgorithms.size();
/* 180 */     for (int j = 0; j < i; j++) {
/* 181 */       ((CollisionAlgorithm)this.childCollisionAlgorithms.get(j)).destroy();
/* 182 */       this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(j));
/*     */     }
/* 184 */     this.childCollisionAlgorithms.clear();
/*     */ 
/* 187 */     i = this.childCollisionAlgorithmsCOM.size();
/* 188 */     for (j = 0; j < i; j++)
/*     */     {
/* 190 */       this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithmsCOM.getQuick(j));
/*     */     }
/* 192 */     this.childCollisionAlgorithmsCOM.clear();
/*     */ 
/* 194 */     this.compoundObject = null;
/* 195 */     this.otherObject = null;
/* 196 */     this.childCollisionAlgorithms.clear();
/* 197 */     this.childCollisionAlgorithmsCOM.clear();
/* 198 */     this.v.instances -= 1;
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 209 */     if ((paramCollisionObject1 == this.otherObject) && (paramCollisionObject2 == this.compoundObject))
/*     */     {
/* 211 */       paramCollisionObject1 = this.compoundObject;
/* 212 */       paramCollisionObject2 = this.otherObject;
/*     */     }
/*     */ 
/* 217 */     if ((paramCollisionObject1 != this.compoundObject) || (paramCollisionObject2 != this.otherObject)) {
/* 218 */       System.err.println("COMPOUND ALGORITHM MULTIUSE ?!?!\n---> " + paramCollisionObject1 + ";         " + this.compoundObject + "\n---> " + paramCollisionObject2 + ";         " + this.otherObject);
/*     */     }
/*     */ 
/* 225 */     assert (paramCollisionObject1 == this.compoundObject) : (paramCollisionObject1 + "; " + this.compoundObject);
/* 226 */     assert (paramCollisionObject2 == this.otherObject) : (paramCollisionObject2 + "; " + this.otherObject);
/* 227 */     if (this.manifoldPtr == null) {
/* 228 */       this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/*     */     }
/* 230 */     if ((this.manifoldPtr.getBody0() != paramCollisionObject1) || (this.manifoldPtr.getBody1() != paramCollisionObject2))
/*     */     {
/* 232 */       this.dispatcher.releaseManifold(this.manifoldPtr);
/* 233 */       System.err.println("[COMPOUNDECOLLISION] Exception: wrong manifold: \n----> " + this.manifoldPtr.getBody0() + " != " + paramCollisionObject1 + " or \n----> " + this.manifoldPtr.getBody1() + " != " + paramCollisionObject2);
/*     */ 
/* 238 */       localObject1 = ((RigidBodyExt)this.compoundObject).getSegmentController().getPhysics().getDynamicsWorld().getCollisionObjectArray();
/* 239 */       for (int i = 0; i < ((ObjectArrayList)localObject1).size(); i++) {
/* 240 */         System.err.println("OBJECTS LISTED " + ((ObjectArrayList)localObject1).getQuick(i));
/*     */       }
/* 242 */       this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/*     */     }
/* 244 */     paramManifoldResult.setPersistentManifold(this.manifoldPtr);
/* 245 */     assert (this.compoundObject.getCollisionShape().isCompound());
/* 246 */     Object localObject1 = (CompoundShape)this.compoundObject.getCollisionShape();
/*     */ 
/* 256 */     paramCollisionObject1 = this.v.orgTrans;
/* 257 */     paramCollisionObject2 = this.v.chieldTrans;
/* 258 */     Transform localTransform1 = this.v.interpolationTrans;
/* 259 */     Transform localTransform2 = this.v.newChildWorldTrans;
/*     */ 
/* 261 */     int j = ((CompoundShape)localObject1).getNumChildShapes();
/*     */ 
/* 263 */     int m = 0;
/* 264 */     for (int k = 0; k < j; k++)
/*     */     {
/* 266 */       Object localObject2 = ((CompoundShape)localObject1).getChildShape(k);
/*     */ 
/* 269 */       this.compoundObject.getWorldTransform(paramCollisionObject1);
/* 270 */       this.compoundObject.getInterpolationWorldTransform(localTransform1);
/*     */ 
/* 272 */       ((CompoundShape)localObject1).getChildTransform(k, paramCollisionObject2);
/* 273 */       localTransform2.mul(paramCollisionObject1, paramCollisionObject2);
/* 274 */       this.compoundObject.setWorldTransform(localTransform2);
/* 275 */       this.compoundObject.setInterpolationWorldTransform(localTransform2);
/*     */ 
/* 278 */       CollisionShape localCollisionShape = this.compoundObject.getCollisionShape();
/* 279 */       this.compoundObject.internalSetTemporaryCollisionShape((CollisionShape)localObject2);
/*     */       Object localObject3;
/* 284 */       if (this.otherObject.getCollisionShape().isCompound()) {
/* 285 */         localObject2 = (CompoundShape)this.otherObject.getCollisionShape();
/* 286 */         for (int n = 0; n < ((CompoundShape)localObject2).getNumChildShapes(); n++) {
/* 287 */           localObject3 = (CollisionAlgorithm)this.childCollisionAlgorithmsCOM.getQuick(m);
/*     */ 
/* 289 */           Transform localTransform3 = this.v.orgTransO;
/* 290 */           Object localObject4 = this.v.chieldTransO;
/* 291 */           Transform localTransform4 = this.v.interpolationTransO;
/* 292 */           Object localObject5 = this.v.newChildWorldTransO;
/*     */ 
/* 294 */           Object localObject6 = ((CompoundShape)localObject2).getChildShape(n);
/*     */ 
/* 297 */           this.otherObject.getWorldTransform(localTransform3);
/* 298 */           this.otherObject.getInterpolationWorldTransform(localTransform4);
/*     */ 
/* 300 */           ((CompoundShape)localObject2).getChildTransform(n, (Transform)localObject4);
/* 301 */           ((Transform)localObject5).mul(localTransform3, (Transform)localObject4);
/* 302 */           this.otherObject.setWorldTransform((Transform)localObject5);
/* 303 */           this.otherObject.setInterpolationWorldTransform((Transform)localObject5);
/*     */ 
/* 306 */           localObject4 = this.otherObject.getCollisionShape();
/* 307 */           this.otherObject.internalSetTemporaryCollisionShape((CollisionShape)localObject6);
/*     */ 
/* 311 */           localObject5 = this.compoundObject;
/*     */ 
/* 331 */           localObject6 = this.otherObject;
/*     */ 
/* 340 */           ((CollisionAlgorithm)localObject3).processCollision((CollisionObject)localObject5, (CollisionObject)localObject6, paramDispatcherInfo, paramManifoldResult);
/*     */ 
/* 343 */           this.otherObject.internalSetTemporaryCollisionShape((CollisionShape)localObject4);
/* 344 */           this.otherObject.setWorldTransform(localTransform3);
/* 345 */           this.otherObject.setInterpolationWorldTransform(localTransform4);
/* 346 */           m++;
/*     */         }
/*     */       } else {
/* 349 */         localObject2 = (CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(k);
/* 350 */         if (((this.compoundObject.getCollisionShape() instanceof CubeShape)) && ((this.otherObject.getCollisionShape() instanceof CubeShape))) {
/* 351 */           if (!$assertionsDisabled) throw new AssertionError();
/* 352 */           System.err.println("CC COLLISSION sw " + this.swapped + "; " + this.compoundObject.getCollisionShape() + "; " + this.otherObject.getCollisionShape());
/*     */ 
/* 359 */           CollisionObject localCollisionObject = this.compoundObject;
/* 360 */           localObject3 = this.otherObject;
/* 361 */           this.dispatcher.findAlgorithm(localCollisionObject, (CollisionObject)localObject3)
/* 362 */             .processCollision(localCollisionObject, (CollisionObject)localObject3, paramDispatcherInfo, paramManifoldResult);
/*     */         }
/*     */         else
/*     */         {
/* 367 */           ((CollisionAlgorithm)localObject2).processCollision(this.compoundObject, this.otherObject, paramDispatcherInfo, paramManifoldResult);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 374 */       this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape);
/* 375 */       this.compoundObject.setWorldTransform(paramCollisionObject1);
/* 376 */       this.compoundObject.setInterpolationWorldTransform(localTransform1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 460 */     assert (this.compoundObject.getCollisionShape().isCompound());
/*     */ 
/* 462 */     paramCollisionObject1 = (CompoundShape)this.compoundObject.getCollisionShape();
/*     */ 
/* 471 */     paramCollisionObject2 = this.v.tmpTrans;
/* 472 */     Transform localTransform1 = this.v.orgTrans;
/* 473 */     Transform localTransform2 = this.v.chieldTrans;
/* 474 */     float f1 = 1.0F;
/*     */ 
/* 476 */     int i = this.childCollisionAlgorithms.size();
/*     */ 
/* 478 */     for (int j = 0; j < i; j++)
/*     */     {
/* 480 */       CollisionShape localCollisionShape1 = paramCollisionObject1.getChildShape(j);
/*     */ 
/* 483 */       this.compoundObject.getWorldTransform(localTransform1);
/*     */ 
/* 485 */       paramCollisionObject1.getChildTransform(j, localTransform2);
/*     */ 
/* 487 */       paramCollisionObject2.set(localTransform1);
/* 488 */       paramCollisionObject2.mul(localTransform2);
/* 489 */       this.compoundObject.setWorldTransform(paramCollisionObject2);
/*     */ 
/* 491 */       CollisionShape localCollisionShape2 = this.compoundObject.getCollisionShape();
/* 492 */       this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/*     */       float f2;
/* 494 */       if ((
/* 494 */         f2 = ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(j)).calculateTimeOfImpact(this.compoundObject, this.otherObject, paramDispatcherInfo, paramManifoldResult)) < 
/* 494 */         f1) {
/* 495 */         f1 = f2;
/*     */       }
/*     */ 
/* 498 */       this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/* 499 */       this.compoundObject.setWorldTransform(localTransform1);
/*     */     }
/* 501 */     return f1;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
/*     */   {
/* 506 */     for (int i = 0; i < this.childCollisionAlgorithms.size(); i++)
/* 507 */       ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).getAllContactManifolds(paramObjectArrayList);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt
 * JD-Core Version:    0.6.2
 */