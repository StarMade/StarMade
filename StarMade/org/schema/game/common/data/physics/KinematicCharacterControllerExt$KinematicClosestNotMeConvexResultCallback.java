/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ class KinematicCharacterControllerExt$KinematicClosestNotMeConvexResultCallback extends CollisionWorld.ClosestConvexResultCallback
/*     */ {
/*     */   protected CollisionObject me;
/*     */   protected final Vector3f up;
/*     */   protected float minSlopeDot;
/*  52 */   Transform t = new Transform();
/*     */   private Segment segment;
/*     */   private o cubePos;
/*     */ 
/*     */   public KinematicCharacterControllerExt$KinematicClosestNotMeConvexResultCallback(CollisionObject paramCollisionObject, Vector3f paramVector3f, float paramFloat)
/*     */   {
/*  57 */     super(new Vector3f(), new Vector3f());
/*  58 */     this.me = paramCollisionObject;
/*  59 */     this.up = paramVector3f;
/*  60 */     this.minSlopeDot = paramFloat;
/*     */   }
/*     */ 
/*     */   public float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean)
/*     */   {
/*  66 */     if (paramLocalConvexResult.hitCollisionObject == this.me)
/*  67 */       return 1.0F;
/*     */     Vector3f localVector3f;
/*  71 */     if (paramBoolean) {
/*  72 */       localVector3f = paramLocalConvexResult.hitNormalLocal;
/*     */     }
/*     */     else {
/*  75 */       localVector3f = new Vector3f();
/*  76 */       this.hitCollisionObject.getWorldTransform(this.t).basis.transform(paramLocalConvexResult.hitNormalLocal, localVector3f);
/*     */     }
/*     */ 
/*  81 */     if (this.up.dot(localVector3f) < 
/*  81 */       this.minSlopeDot) {
/*  82 */       return 1.0F;
/*     */     }
/*     */ 
/*  85 */     return super.addSingleResult(paramLocalConvexResult, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean, Segment paramSegment, o paramo)
/*     */   {
/* 101 */     addSingleResult(paramLocalConvexResult, paramBoolean);
/*     */ 
/* 103 */     this.segment = paramSegment;
/* 104 */     this.cubePos = new o(paramo);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback
 * JD-Core Version:    0.6.2
 */