/*     */ package org.schema.game.common.controller.elements.lift;
/*     */ 
/*     */ import com.bulletphysics.collision.shapes.BoxShape;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.MotionState;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import ct;
/*     */ import dj;
/*     */ import ex;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import q;
/*     */ import xq;
/*     */ import zM;
/*     */ 
/*     */ public class LiftUnit extends ElementCollection
/*     */ {
/*  20 */   private q significator = new q();
/*  21 */   private float height = -1.0F;
/*     */   private RigidBody body;
/*     */   private Transform t;
/*     */   private float maxHeight;
/*  25 */   private float timeSpendUp = 0.0F;
/*     */ 
/*     */   public void activate()
/*     */   {
/*  33 */     PhysicsExt localPhysicsExt = getController().getPhysics();
/*     */ 
/*  35 */     Object localObject2 = new BoxShape(new Vector3f(Math.max(2, getMax().a - getMin().a), 0.2F, Math.max(2, getMax().c - getMin().c)));
/*     */ 
/*  37 */     Vector3f localVector3f = new Vector3f(getSignificator().a - 8, getMin().b - 8 - 1.0F, getSignificator().c - 8);
/*     */ 
/*  39 */     getController().getWorldTransform().transform(localVector3f);
/*     */ 
/*  41 */     this.t = new Transform();
/*  42 */     this.t.setIdentity();
/*  43 */     this.t.origin.set(localVector3f);
/*  44 */     this.timeSpendUp = 0.0F;
/*     */ 
/*  46 */     if (getBody() != null) {
/*  47 */       localPhysicsExt.removeObject(getBody());
/*     */     }
/*  49 */     setBody(localPhysicsExt.getBodyFromShape((CollisionShape)localObject2, 0.0F, this.t));
/*  50 */     this.height = 0.0F;
/*  51 */     this.maxHeight = (getMax().b - getMin().b + 1.5F);
/*     */ 
/*  53 */     localPhysicsExt.addObject(getBody());
/*     */ 
/*  55 */     if (!getController().isOnServer()) {
/*  56 */       int i = 0; localObject2 = this; Object localObject1 = null; ((ct)getController().getState()).a().a().a.add(localObject2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cleanUp()
/*     */   {
/*  64 */     deactivate();
/*  65 */     super.cleanUp();
/*     */   }
/*     */ 
/*     */   public void deactivate()
/*     */   {
/*  71 */     if (isActive())
/*     */     {
/*  73 */       PhysicsExt localPhysicsExt = getController().getPhysics();
/*  74 */       if (getBody() != null) {
/*  75 */         localPhysicsExt.removeObject(getBody());
/*     */       }
/*     */     }
/*  78 */     this.height = -1.0F;
/*  79 */     this.timeSpendUp = 0.0F;
/*  80 */     setChanged();
/*  81 */     notifyObservers(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public RigidBody getBody()
/*     */   {
/*  91 */     return this.body;
/*     */   }
/*     */ 
/*     */   public q getSignificator() {
/*  95 */     return this.significator;
/*     */   }
/*     */ 
/*     */   public boolean isActive()
/*     */   {
/* 104 */     return this.height >= 0.0F;
/*     */   }
/*     */ 
/*     */   public void refreshLiftCapabilities()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setBody(RigidBody paramRigidBody)
/*     */   {
/* 116 */     this.body = paramRigidBody;
/*     */   }
/*     */ 
/*     */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 125 */     this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 126 */     this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 127 */     this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq) {
/* 131 */     if (isActive()) {
/* 132 */       paramxq = paramxq.a() * Math.max(1.0F, (getMax().b - getMin().b) / 16.0F);
/* 133 */       if (this.height < this.maxHeight) {
/* 134 */         this.height += paramxq;
/* 135 */         this.t.origin.y += paramxq;
/* 136 */         getBody().setActivationState(1);
/*     */ 
/* 138 */         getBody().getMotionState().setWorldTransform(this.t);
/* 139 */         getBody().setWorldTransform(this.t); return;
/*     */       }
/* 141 */       this.timeSpendUp += paramxq;
/*     */ 
/* 143 */       if (this.timeSpendUp > 5.0F)
/* 144 */         deactivate();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.lift.LiftUnit
 * JD-Core Version:    0.6.2
 */