/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class dv extends Camera
/*     */   implements wx
/*     */ {
/*     */   private ct jdField_a_of_type_Ct;
/*     */   private lD jdField_a_of_type_LD;
/*     */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private CollisionWorld.ClosestRayResultCallback jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
/*     */   private Transform b;
/*     */ 
/*     */   public dv(ct paramct, lD paramlD)
/*     */   {
/*  83 */     super(new dC(paramlD));
/*     */ 
/*  74 */     a(paramlD);
/*     */ 
/*  85 */     this.jdField_a_of_type_Ct = paramct;
/*  86 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  87 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*     */ 
/*  89 */     this.jdField_a_of_type_WZ = new wX(this);
/*     */   }
/*     */ 
/*     */   public final lD a()
/*     */   {
/* 108 */     return this.jdField_a_of_type_LD;
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/* 143 */     ((dz)a()).handleKeyEvent();
/*     */   }
/*     */ 
/*     */   protected final int a(int paramInt)
/*     */   {
/* 156 */     return Math.max(0, Math.min(paramInt, 2500));
/*     */   }
/*     */ 
/*     */   public final void a(lD paramlD)
/*     */   {
/* 167 */     this.jdField_a_of_type_LD = paramlD;
/* 168 */     ((xb)a()).a(paramlD);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 180 */     Vector3f localVector3f = null; if ((this.jdField_a_of_type_LD.getGravity().b()) || (this.jdField_a_of_type_LD.getGravity().a())) {
/* 181 */       if (this.jdField_a_of_type_LD.getGravity().a()) {
/* 182 */         ((wX)this.jdField_a_of_type_WZ).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_LD.getGravity().a.getWorldTransform());
/* 183 */         this.b = null;
/*     */       }
/*     */       else
/*     */       {
/* 187 */         if (this.b == null) {
/* 188 */           this.b = new Transform(this.jdField_a_of_type_LD.getGravity().a.getWorldTransform());
/*     */         }
/* 190 */         ((wX)this.jdField_a_of_type_WZ).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.b);
/*     */       }
/*     */     } else {
/* 193 */       this.b = null;
/* 194 */       ((wX)this.jdField_a_of_type_WZ).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */     }
/*     */ 
/* 197 */     super.a(paramxq);
/*     */ 
/* 200 */     Object localObject = this.jdField_a_of_type_LD; paramxq = this; localObject = new Vector3f(((lD)localObject).a().origin); localVector3f = new Vector3f(paramxq.c(new Vector3f()));
/*     */     CubeRayCastResult localCubeRayCastResult;
/* 200 */     (localCubeRayCastResult = new CubeRayCastResult((Vector3f)localObject, localVector3f, Boolean.valueOf(false), null)).setRespectShields(false); localCubeRayCastResult.onlyCubeMeshes = true; this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = (this.jdField_a_of_type_Float > 0.0F ? ((PhysicsExt)paramxq.jdField_a_of_type_Ct.a()).testRayCollisionPoint((Vector3f)localObject, localVector3f, localCubeRayCastResult, false) : null);
/*     */ 
/* 203 */     if ((this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit())) {
/* 204 */       (
/* 205 */         paramxq = new Vector3f())
/* 205 */         .sub(c(new Vector3f()), this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hitPointWorld);
/* 206 */       paramxq.scale(1.01F);
/* 207 */       getWorldTransform().origin.sub(paramxq);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dv
 * JD-Core Version:    0.6.2
 */