/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class SolverBody
/*     */ {
/*  42 */   public final Vector3f angularVelocity = new Vector3f();
/*     */   public float angularFactor;
/*     */   public float invMass;
/*     */   public float friction;
/*     */   public RigidBody originalBody;
/*  47 */   public final Vector3f linearVelocity = new Vector3f();
/*  48 */   public final Vector3f centerOfMassPosition = new Vector3f();
/*     */ 
/*  50 */   public final Vector3f pushVelocity = new Vector3f();
/*  51 */   public final Vector3f turnVelocity = new Vector3f();
/*     */ 
/*     */   public void getVelocityInLocalPoint(Vector3f arg1, Vector3f arg2) {
/*  54 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  55 */       tmp.cross(this.angularVelocity, rel_pos);
/*  56 */       velocity.add(this.linearVelocity, tmp);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void internalApplyImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
/*     */   {
/*  63 */     if (this.invMass != 0.0F) {
/*  64 */       this.linearVelocity.scaleAdd(impulseMagnitude, linearComponent, this.linearVelocity);
/*  65 */       this.angularVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.angularVelocity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void internalApplyPushImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude) {
/*  70 */     if (this.invMass != 0.0F) {
/*  71 */       this.pushVelocity.scaleAdd(impulseMagnitude, linearComponent, this.pushVelocity);
/*  72 */       this.turnVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.turnVelocity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writebackVelocity() {
/*  77 */     if (this.invMass != 0.0F) {
/*  78 */       this.originalBody.setLinearVelocity(this.linearVelocity);
/*  79 */       this.originalBody.setAngularVelocity(this.angularVelocity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writebackVelocity(float arg1)
/*     */   {
/*  85 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); if (this.invMass != 0.0F) {
/*  86 */         this.originalBody.setLinearVelocity(this.linearVelocity);
/*  87 */         this.originalBody.setAngularVelocity(this.angularVelocity);
/*     */ 
/*  90 */         Transform newTransform = localStack.get$com$bulletphysics$linearmath$Transform();
/*  91 */         Transform curTrans = this.originalBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*  92 */         TransformUtil.integrateTransform(curTrans, this.pushVelocity, this.turnVelocity, timeStep, newTransform);
/*  93 */         this.originalBody.setWorldTransform(newTransform);
/*     */       }
/*     */       return;
/*     */     } finally {
/*  97 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   public void readVelocity() {
/* 100 */     if (this.invMass != 0.0F) {
/* 101 */       this.originalBody.getLinearVelocity(this.linearVelocity);
/* 102 */       this.originalBody.getAngularVelocity(this.angularVelocity);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SolverBody
 * JD-Core Version:    0.6.2
 */