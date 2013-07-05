/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.util.linAlg.TransformTools;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ 
/*     */ public abstract class PairCachingGhostObjectExt extends PairCachingGhostObject
/*     */ {
/*     */   private PhysicsDataContainer pCon;
/*     */   private SegmentController attached;
/*  27 */   Vector3f castShapeAabbMin = new Vector3f();
/*     */ 
/*  29 */   Vector3f castShapeAabbMax = new Vector3f();
/*  30 */   Transform convexFromTrans = new Transform();
/*     */ 
/*  33 */   Transform convexToTrans = new Transform();
/*  34 */   Vector3f linVel = new Vector3f();
/*     */ 
/*  37 */   Vector3f angVel = new Vector3f();
/*  38 */   Transform tmpTrans = new Transform();
/*  39 */   Vector3f collisionObjectAabbMin = new Vector3f();
/*  40 */   Vector3f collisionObjectAabbMax = new Vector3f();
/*  41 */   Vector3f hitNormal = new Vector3f();
/*  42 */   Transform R = new Transform();
/*  43 */   Quat4f quat = new Quat4f();
/*     */ 
/*  49 */   private Matrix3f tmp = new Matrix3f();
/*  50 */   private final Vector3f axis = new Vector3f();
/*  51 */   private final Matrix3f dmat = new Matrix3f();
/*  52 */   private final Quat4f dorn = new Quat4f();
/*     */ 
/*     */   public PairCachingGhostObjectExt(PhysicsDataContainer paramPhysicsDataContainer)
/*     */   {
/*  45 */     this.pCon = paramPhysicsDataContainer;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  59 */     return "PCGhostObjExt(" + getUserPointer() + ")@" + hashCode();
/*     */   }
/*     */ 
/*     */   public void setWorldTransform(Transform paramTransform)
/*     */   {
/*  65 */     this.worldTransform.set(paramTransform);
/*     */   }
/*     */ 
/*     */   public void convexSweepTest(ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, CollisionWorld.ConvexResultCallback paramConvexResultCallback, float paramFloat)
/*     */   {
/*  71 */     this.convexFromTrans.set(paramTransform1);
/*  72 */     this.convexToTrans.set(paramTransform2);
/*     */ 
/*  74 */     this.quat.x = 0.0F;
/*  75 */     this.quat.y = 0.0F;
/*  76 */     this.quat.x = 0.0F;
/*  77 */     this.quat.w = 0.0F;
/*     */ 
/*  80 */     assert (paramTransform1.getMatrix(new Matrix4f()).determinant() != 0.0F) : paramTransform1.getMatrix(new Matrix4f());
/*  81 */     assert (paramTransform2.getMatrix(new Matrix4f()).determinant() != 0.0F) : paramTransform2.getMatrix(new Matrix4f());
/*  82 */     TransformTools.a(this.convexFromTrans, this.convexToTrans, 1.0F, this.linVel, this.angVel, this.axis, this.tmp, this.dmat, this.dorn);
/*     */ 
/*  84 */     this.R.setIdentity();
/*  85 */     this.R.setRotation(this.convexFromTrans.getRotation(this.quat));
/*  86 */     paramConvexShape.calculateTemporalAabb(this.R, this.linVel, this.angVel, 1.0F, this.castShapeAabbMin, this.castShapeAabbMax);
/*     */ 
/*  93 */     long l = System.currentTimeMillis();
/*  94 */     for (int i = 0; i < this.overlappingObjects.size(); i++)
/*     */     {
/*     */       CollisionObject localCollisionObject;
/*  97 */       if (((
/*  97 */         localCollisionObject = (CollisionObject)this.overlappingObjects.getQuick(i)) != 
/*  97 */         this.pCon.getObject()) && 
/*  98 */         (paramConvexResultCallback.needsCollision(localCollisionObject.getBroadphaseHandle())))
/*     */       {
/* 107 */         localCollisionObject.getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/* 108 */         AabbUtil2.aabbExpand(this.collisionObjectAabbMin, this.collisionObjectAabbMax, this.castShapeAabbMin, this.castShapeAabbMax);
/* 109 */         float[] arrayOfFloat = { 1.0F };
/* 110 */         this.hitNormal.set(0.0F, 0.0F, 0.0F);
/* 111 */         if (AabbUtil2.rayAabb(paramTransform1.origin, paramTransform2.origin, this.collisionObjectAabbMin, this.collisionObjectAabbMax, arrayOfFloat, this.hitNormal))
/*     */         {
/* 114 */           ModifiedDynamicsWorld.objectQuerySingle(paramConvexShape, this.convexFromTrans, this.convexToTrans, localCollisionObject, localCollisionObject.getCollisionShape(), localCollisionObject.getWorldTransform(this.tmpTrans), paramConvexResultCallback, paramFloat);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 128 */     if ((
/* 128 */       i = (int)(System.currentTimeMillis() - l)) > 
/* 128 */       15)
/* 129 */       System.err.println("[GHOST-OBJECT] SWEEP TEST TIME: " + i);
/*     */   }
/*     */ 
/*     */   public SegmentController getAttached()
/*     */   {
/* 137 */     return this.attached;
/*     */   }
/*     */ 
/*     */   public void setAttached(SegmentController paramSegmentController)
/*     */   {
/* 144 */     this.attached = paramSegmentController;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectExt
 * JD-Core Version:    0.6.2
 */