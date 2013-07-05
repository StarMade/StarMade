/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class TypedConstraint
/*     */ {
/*     */   private static RigidBody s_fixed;
/*  48 */   private int userConstraintType = -1;
/*  49 */   private int userConstraintId = -1;
/*     */   private TypedConstraintType constraintType;
/*     */   protected RigidBody rbA;
/*     */   protected RigidBody rbB;
/*  55 */   protected float appliedImpulse = 0.0F;
/*     */ 
/*     */   private static synchronized RigidBody getFixed()
/*     */   {
/*  42 */     if (s_fixed == null) {
/*  43 */       s_fixed = new RigidBody(0.0F, null, null);
/*     */     }
/*  45 */     return s_fixed;
/*     */   }
/*     */ 
/*     */   public TypedConstraint(TypedConstraintType type)
/*     */   {
/*  58 */     this(type, getFixed(), getFixed());
/*     */   }
/*     */ 
/*     */   public TypedConstraint(TypedConstraintType type, RigidBody rbA) {
/*  62 */     this(type, rbA, getFixed());
/*     */   }
/*     */ 
/*     */   public TypedConstraint(TypedConstraintType type, RigidBody rbA, RigidBody rbB) {
/*  66 */     this.constraintType = type;
/*  67 */     this.rbA = rbA;
/*  68 */     this.rbB = rbB;
/*  69 */     getFixed().setMassProps(0.0F, new Vector3f(0.0F, 0.0F, 0.0F));
/*     */   }
/*     */ 
/*     */   public abstract void buildJacobian();
/*     */ 
/*     */   public abstract void solveConstraint(float paramFloat);
/*     */ 
/*     */   public RigidBody getRigidBodyA() {
/*  77 */     return this.rbA;
/*     */   }
/*     */ 
/*     */   public RigidBody getRigidBodyB() {
/*  81 */     return this.rbB;
/*     */   }
/*     */ 
/*     */   public int getUserConstraintType() {
/*  85 */     return this.userConstraintType;
/*     */   }
/*     */ 
/*     */   public void setUserConstraintType(int userConstraintType) {
/*  89 */     this.userConstraintType = userConstraintType;
/*     */   }
/*     */ 
/*     */   public int getUserConstraintId() {
/*  93 */     return this.userConstraintId;
/*     */   }
/*     */ 
/*     */   public int getUid() {
/*  97 */     return this.userConstraintId;
/*     */   }
/*     */ 
/*     */   public void setUserConstraintId(int userConstraintId) {
/* 101 */     this.userConstraintId = userConstraintId;
/*     */   }
/*     */ 
/*     */   public float getAppliedImpulse() {
/* 105 */     return this.appliedImpulse;
/*     */   }
/*     */ 
/*     */   public TypedConstraintType getConstraintType() {
/* 109 */     return this.constraintType;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.TypedConstraint
 * JD-Core Version:    0.6.2
 */