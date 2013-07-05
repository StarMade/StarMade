/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class Point2PointConstraint extends TypedConstraint
/*     */ {
/*  41 */   private final JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*     */ 
/*  43 */   private final Vector3f pivotInA = new Vector3f();
/*  44 */   private final Vector3f pivotInB = new Vector3f();
/*     */ 
/*  46 */   public ConstraintSetting setting = new ConstraintSetting();
/*     */ 
/*     */   public Point2PointConstraint() {
/*  49 */     super(TypedConstraintType.POINT2POINT_CONSTRAINT_TYPE);
/*     */   }
/*     */ 
/*     */   public Point2PointConstraint(RigidBody rbA, RigidBody rbB, Vector3f pivotInA, Vector3f pivotInB) {
/*  53 */     super(TypedConstraintType.POINT2POINT_CONSTRAINT_TYPE, rbA, rbB);
/*  54 */     this.pivotInA.set(pivotInA);
/*  55 */     this.pivotInB.set(pivotInB);
/*     */   }
/*     */ 
/*     */   public Point2PointConstraint(RigidBody arg1, Vector3f arg2)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/*  67 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Matrix3f(); this.appliedImpulse = 0.0F;
/*     */ 
/*  69 */       Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  70 */       normal.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  72 */       Matrix3f tmpMat1 = localStack.get$javax$vecmath$Matrix3f();
/*  73 */       Matrix3f tmpMat2 = localStack.get$javax$vecmath$Matrix3f();
/*  74 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  75 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  76 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  78 */       Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*  79 */       Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/*  81 */       for (int i = 0; i < 3; i++) {
/*  82 */         VectorUtil.setCoord(normal, i, 1.0F);
/*     */ 
/*  84 */         tmpMat1.transpose(centerOfMassA.basis);
/*  85 */         tmpMat2.transpose(centerOfMassB.basis);
/*     */ 
/*  87 */         tmp1.set(this.pivotInA);
/*  88 */         centerOfMassA.transform(tmp1);
/*  89 */         tmp1.sub(this.rbA.getCenterOfMassPosition(tmpVec));
/*     */ 
/*  91 */         tmp2.set(this.pivotInB);
/*  92 */         centerOfMassB.transform(tmp2);
/*  93 */         tmp2.sub(this.rbB.getCenterOfMassPosition(tmpVec));
/*     */ 
/*  95 */         this.jac[i].init(tmpMat1, tmpMat2, tmp1, tmp2, normal, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/*     */ 
/* 105 */         VectorUtil.setCoord(normal, i, 0.0F);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 107 */       .Stack tmp275_273 = localStack; tmp275_273.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp279_275 = tmp275_273; tmp279_275.pop$javax$vecmath$Vector3f(); tmp279_275.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float arg1) {
/* 111 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 112 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 113 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 115 */       Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 116 */       Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 118 */       Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.pivotInA);
/* 119 */       centerOfMassA.transform(pivotAInW);
/*     */ 
/* 121 */       Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.pivotInB);
/* 122 */       centerOfMassB.transform(pivotBInW);
/*     */ 
/* 124 */       Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 125 */       normal.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 130 */       for (int i = 0; i < 3; i++) {
/* 131 */         VectorUtil.setCoord(normal, i, 1.0F);
/* 132 */         float jacDiagABInv = 1.0F / this.jac[i].getDiagonal();
/*     */ 
/* 134 */         Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 135 */         rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 136 */         Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 137 */         rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 140 */         Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 141 */         Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 142 */         Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 143 */         vel.sub(vel1, vel2);
/*     */ 
/* 146 */         float rel_vel = normal.dot(vel);
/*     */ 
/* 155 */         tmp.sub(pivotAInW, pivotBInW);
/* 156 */         float depth = -tmp.dot(normal);
/*     */ 
/* 158 */         float impulse = depth * this.setting.tau / timeStep * jacDiagABInv - this.setting.damping * rel_vel * jacDiagABInv;
/*     */ 
/* 160 */         float impulseClamp = this.setting.impulseClamp;
/* 161 */         if (impulseClamp > 0.0F) {
/* 162 */           if (impulse < -impulseClamp) {
/* 163 */             impulse = -impulseClamp;
/*     */           }
/* 165 */           if (impulse > impulseClamp) {
/* 166 */             impulse = impulseClamp;
/*     */           }
/*     */         }
/*     */ 
/* 170 */         this.appliedImpulse += impulse;
/* 171 */         Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 172 */         impulse_vector.scale(impulse, normal);
/* 173 */         tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 174 */         this.rbA.applyImpulse(impulse_vector, tmp);
/* 175 */         tmp.negate(impulse_vector);
/* 176 */         tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 177 */         this.rbB.applyImpulse(tmp, tmp2);
/*     */ 
/* 179 */         VectorUtil.setCoord(normal, i, 0.0F);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 181 */       .Stack tmp446_444 = localStack; tmp446_444.pop$com$bulletphysics$linearmath$Transform(); tmp446_444.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void updateRHS(float timeStep) {
/*     */   }
/*     */ 
/*     */   public void setPivotA(Vector3f pivotA) {
/* 187 */     this.pivotInA.set(pivotA);
/*     */   }
/*     */ 
/*     */   public void setPivotB(Vector3f pivotB) {
/* 191 */     this.pivotInB.set(pivotB);
/*     */   }
/*     */ 
/*     */   public Vector3f getPivotInA(Vector3f out) {
/* 195 */     out.set(this.pivotInA);
/* 196 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getPivotInB(Vector3f out) {
/* 200 */     out.set(this.pivotInB);
/* 201 */     return out;
/*     */   }
/*     */ 
/*     */   public static class ConstraintSetting
/*     */   {
/* 207 */     public float tau = 0.3F;
/* 208 */     public float damping = 1.0F;
/* 209 */     public float impulseClamp = 0.0F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.Point2PointConstraint
 * JD-Core Version:    0.6.2
 */