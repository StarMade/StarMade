/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
/*     */ import com.bulletphysics.linearmath.MotionState;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.util.linAlg.TransformTools;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import q;
/*     */ 
/*     */ public class RigidBodyExt extends RigidBody
/*     */   implements RelativeBody
/*     */ {
/*     */   private final SegmentController segmentController;
/*     */   public String virtualString;
/*     */   public q virtualSec;
/*  78 */   private final Vector3f angularVeloTmp = new Vector3f();
/*  79 */   private final Vector3f linearVeloTmp = new Vector3f();
/*  80 */   private final Vector3f axis = new Vector3f();
/*  81 */   private final Matrix3f tmp = new Matrix3f();
/*  82 */   private final Matrix3f dmat = new Matrix3f();
/*  83 */   private final Quat4f dorn = new Quat4f();
/*     */ 
/*     */   public RigidBodyExt(SegmentController paramSegmentController, float paramFloat, MotionState paramMotionState, CollisionShape paramCollisionShape)
/*     */   {
/*  26 */     super(paramFloat, paramMotionState, paramCollisionShape);
/*  27 */     this.segmentController = paramSegmentController;
/*  28 */     this.interpolationWorldTransform.setIdentity();
/*     */   }
/*     */ 
/*     */   public RigidBodyExt(SegmentController paramSegmentController, float paramFloat, MotionState paramMotionState, CollisionShape paramCollisionShape, Vector3f paramVector3f)
/*     */   {
/*  34 */     super(paramFloat, paramMotionState, paramCollisionShape, paramVector3f);
/*  35 */     this.segmentController = paramSegmentController;
/*  36 */     this.interpolationWorldTransform.setIdentity();
/*     */   }
/*     */ 
/*     */   public RigidBodyExt(SegmentController paramSegmentController, RigidBodyConstructionInfo paramRigidBodyConstructionInfo)
/*     */   {
/*  41 */     super(paramRigidBodyConstructionInfo);
/*  42 */     this.segmentController = paramSegmentController;
/*  43 */     this.interpolationWorldTransform.setIdentity();
/*     */   }
/*     */ 
/*     */   public void saveKinematicState(float paramFloat)
/*     */   {
/*  91 */     if (paramFloat != 0.0F)
/*     */     {
/*  93 */       if (getMotionState() != null) {
/*  94 */         getMotionState().getWorldTransform(this.worldTransform);
/*     */       }
/*  96 */       getLinearVelocity(this.linearVeloTmp);
/*  97 */       getAngularVelocity(this.angularVeloTmp);
/*     */ 
/* 100 */       if ((this.linearVeloTmp.lengthSquared() > 0.0F) || (this.angularVeloTmp.lengthSquared() > 0.0F))
/*     */       {
/* 102 */         TransformTools.a(this.interpolationWorldTransform, this.worldTransform, paramFloat, this.linearVeloTmp, this.angularVeloTmp, this.axis, this.tmp, this.dmat, this.dorn);
/*     */ 
/* 107 */         setLinearVelocity(this.linearVeloTmp);
/* 108 */         setAngularVelocity(this.angularVeloTmp);
/*     */       }
/*     */ 
/* 111 */       this.interpolationLinearVelocity.set(this.linearVeloTmp);
/* 112 */       this.interpolationAngularVelocity.set(this.angularVeloTmp);
/* 113 */       this.interpolationWorldTransform.set(this.worldTransform);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Vector3f getInvInertiaDiagLocal(Vector3f paramVector3f)
/*     */   {
/* 132 */     return super.getInvInertiaDiagLocal(paramVector3f);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 144 */     return "RigBEx" + (this.virtualString != null ? this.virtualString : "Orig") + "@" + hashCode() + "(" + getCollisionShape() + ")";
/*     */   }
/*     */ 
/*     */   public void setLinearVelocity(Vector3f paramVector3f)
/*     */   {
/* 153 */     if (this.virtualSec == null)
/* 154 */       super.setLinearVelocity(paramVector3f);
/*     */   }
/*     */ 
/*     */   public void setAngularVelocity(Vector3f paramVector3f)
/*     */   {
/* 172 */     if (this.virtualSec == null)
/* 173 */       super.setAngularVelocity(paramVector3f);
/*     */   }
/*     */ 
/*     */   public SegmentController getSegmentController()
/*     */   {
/* 190 */     return this.segmentController;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RigidBodyExt
 * JD-Core Version:    0.6.2
 */