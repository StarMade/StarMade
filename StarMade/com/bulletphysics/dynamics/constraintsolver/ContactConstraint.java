/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ContactConstraint
/*     */ {
/*  42 */   public static final ContactSolverFunc resolveSingleCollision = new ContactSolverFunc() {
/*     */     public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info) {
/*  44 */       return ContactConstraint.resolveSingleCollision(body1, body2, contactPoint, info);
/*     */     }
/*  42 */   };
/*     */ 
/*  48 */   public static final ContactSolverFunc resolveSingleFriction = new ContactSolverFunc() {
/*     */     public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info) {
/*  50 */       return ContactConstraint.resolveSingleFriction(body1, body2, contactPoint, info);
/*     */     }
/*  48 */   };
/*     */ 
/*  54 */   public static final ContactSolverFunc resolveSingleCollisionCombined = new ContactSolverFunc() {
/*     */     public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info) {
/*  56 */       return ContactConstraint.resolveSingleCollisionCombined(body1, body2, contactPoint, info);
/*     */     }
/*  54 */   };
/*     */ 
/*     */   public static void resolveSingleBilateral(RigidBody arg0, Vector3f arg1, RigidBody arg2, Vector3f arg3, float arg4, Vector3f arg5, float[] arg6, float arg7)
/*     */   {
/*  66 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); float normalLenSqr = normal.lengthSquared();
/*  67 */       assert (Math.abs(normalLenSqr) < 1.1F);
/*  68 */       if (normalLenSqr > 1.1F) {
/*  69 */         impulse[0] = 0.0F;
/*  70 */         return;
/*     */       }
/*     */ 
/*  73 */       ObjectPool jacobiansPool = ObjectPool.get(JacobianEntry.class);
/*  74 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  76 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/*  77 */       rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmp));
/*     */ 
/*  79 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/*  80 */       rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmp));
/*     */ 
/*  84 */       Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
/*  85 */       body1.getVelocityInLocalPoint(rel_pos1, vel1);
/*     */ 
/*  87 */       Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
/*  88 */       body2.getVelocityInLocalPoint(rel_pos2, vel2);
/*     */ 
/*  90 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/*  91 */       vel.sub(vel1, vel2);
/*     */ 
/*  93 */       Matrix3f mat1 = body1.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/*  94 */       mat1.transpose();
/*     */ 
/*  96 */       Matrix3f mat2 = body2.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/*  97 */       mat2.transpose();
/*     */ 
/*  99 */       JacobianEntry jac = (JacobianEntry)jacobiansPool.get();
/* 100 */       jac.init(mat1, mat2, rel_pos1, rel_pos2, normal, body1.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body1.getInvMass(), body2.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body2.getInvMass());
/*     */ 
/* 105 */       float jacDiagAB = jac.getDiagonal();
/* 106 */       float jacDiagABInv = 1.0F / jacDiagAB;
/*     */ 
/* 108 */       Vector3f tmp1 = body1.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 109 */       mat1.transform(tmp1);
/*     */ 
/* 111 */       Vector3f tmp2 = body2.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 112 */       mat2.transform(tmp2);
/*     */ 
/* 114 */       float rel_vel = jac.getRelativeVelocity(body1.getLinearVelocity(localStack.get$javax$vecmath$Vector3f()), tmp1, body2.getLinearVelocity(localStack.get$javax$vecmath$Vector3f()), tmp2);
/*     */ 
/* 120 */       jacobiansPool.release(jac);
/*     */ 
/* 123 */       float a = jacDiagABInv;
/*     */ 
/* 126 */       rel_vel = normal.dot(vel);
/*     */ 
/* 129 */       float contactDamping = 0.2F;
/*     */ 
/* 135 */       float velocityImpulse = -contactDamping * rel_vel * jacDiagABInv;
/* 136 */       impulse[0] = velocityImpulse;
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 138 */       .Stack tmp389_387 = localStack; tmp389_387.pop$com$bulletphysics$linearmath$Transform(); tmp389_387.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static float resolveSingleCollision(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
/*     */   {
/* 149 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 151 */       Vector3f pos1_ = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
/* 152 */       Vector3f pos2_ = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
/* 153 */       Vector3f normal = contactPoint.normalWorldOnB;
/*     */ 
/* 156 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 157 */       rel_pos1.sub(pos1_, body1.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 159 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 160 */       rel_pos2.sub(pos2_, body2.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 162 */       Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 163 */       Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 164 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 165 */       vel.sub(vel1, vel2);
/*     */ 
/* 168 */       float rel_vel = normal.dot(vel);
/*     */ 
/* 170 */       float Kfps = 1.0F / solverInfo.timeStep;
/*     */ 
/* 173 */       float Kerp = solverInfo.erp;
/* 174 */       float Kcor = Kerp * Kfps;
/*     */ 
/* 176 */       ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
/* 177 */       assert (cpd != null);
/* 178 */       float distance = cpd.penetration;
/* 179 */       float positionalError = Kcor * -distance;
/* 180 */       float velocityError = cpd.restitution - rel_vel;
/*     */ 
/* 182 */       float penetrationImpulse = positionalError * cpd.jacDiagABInv;
/*     */ 
/* 184 */       float velocityImpulse = velocityError * cpd.jacDiagABInv;
/*     */ 
/* 186 */       float normalImpulse = penetrationImpulse + velocityImpulse;
/*     */ 
/* 189 */       float oldNormalImpulse = cpd.appliedImpulse;
/* 190 */       float sum = oldNormalImpulse + normalImpulse;
/* 191 */       cpd.appliedImpulse = (0.0F > sum ? 0.0F : sum);
/*     */ 
/* 193 */       normalImpulse = cpd.appliedImpulse - oldNormalImpulse;
/*     */ 
/* 196 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 197 */       if (body1.getInvMass() != 0.0F) {
/* 198 */         tmp.scale(body1.getInvMass(), contactPoint.normalWorldOnB);
/* 199 */         body1.internalApplyImpulse(tmp, cpd.angularComponentA, normalImpulse);
/*     */       }
/* 201 */       if (body2.getInvMass() != 0.0F) {
/* 202 */         tmp.scale(body2.getInvMass(), contactPoint.normalWorldOnB);
/* 203 */         body2.internalApplyImpulse(tmp, cpd.angularComponentB, -normalImpulse);
/*     */       }
/*     */ 
/* 210 */       return normalImpulse; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static float resolveSingleFriction(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
/*     */   {
/* 219 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 221 */       Vector3f pos1 = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
/* 222 */       Vector3f pos2 = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 224 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 225 */       rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 227 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 228 */       rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 230 */       ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
/* 231 */       assert (cpd != null);
/*     */ 
/* 233 */       float combinedFriction = cpd.friction;
/*     */ 
/* 235 */       float limit = cpd.appliedImpulse * combinedFriction;
/*     */ 
/* 237 */       if (cpd.appliedImpulse > 0.0F)
/*     */       {
/* 242 */         Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
/* 243 */         body1.getVelocityInLocalPoint(rel_pos1, vel1);
/*     */ 
/* 245 */         Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
/* 246 */         body2.getVelocityInLocalPoint(rel_pos2, vel2);
/*     */ 
/* 248 */         Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 249 */         vel.sub(vel1, vel2);
/*     */ 
/* 254 */         float vrel = cpd.frictionWorldTangential0.dot(vel);
/*     */ 
/* 257 */         float j1 = -vrel * cpd.jacDiagABInvTangent0;
/* 258 */         float oldTangentImpulse = cpd.accumulatedTangentImpulse0;
/* 259 */         cpd.accumulatedTangentImpulse0 = (oldTangentImpulse + j1);
/*     */ 
/* 261 */         cpd.accumulatedTangentImpulse0 = Math.min(cpd.accumulatedTangentImpulse0, limit);
/* 262 */         cpd.accumulatedTangentImpulse0 = Math.max(cpd.accumulatedTangentImpulse0, -limit);
/* 263 */         j1 = cpd.accumulatedTangentImpulse0 - oldTangentImpulse;
/*     */ 
/* 268 */         float vrel = cpd.frictionWorldTangential1.dot(vel);
/*     */ 
/* 271 */         float j2 = -vrel * cpd.jacDiagABInvTangent1;
/* 272 */         float oldTangentImpulse = cpd.accumulatedTangentImpulse1;
/* 273 */         cpd.accumulatedTangentImpulse1 = (oldTangentImpulse + j2);
/*     */ 
/* 275 */         cpd.accumulatedTangentImpulse1 = Math.min(cpd.accumulatedTangentImpulse1, limit);
/* 276 */         cpd.accumulatedTangentImpulse1 = Math.max(cpd.accumulatedTangentImpulse1, -limit);
/* 277 */         j2 = cpd.accumulatedTangentImpulse1 - oldTangentImpulse;
/*     */ 
/* 281 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 283 */         if (body1.getInvMass() != 0.0F) {
/* 284 */           tmp.scale(body1.getInvMass(), cpd.frictionWorldTangential0);
/* 285 */           body1.internalApplyImpulse(tmp, cpd.frictionAngularComponent0A, j1);
/*     */ 
/* 287 */           tmp.scale(body1.getInvMass(), cpd.frictionWorldTangential1);
/* 288 */           body1.internalApplyImpulse(tmp, cpd.frictionAngularComponent1A, j2);
/*     */         }
/* 290 */         if (body2.getInvMass() != 0.0F) {
/* 291 */           tmp.scale(body2.getInvMass(), cpd.frictionWorldTangential0);
/* 292 */           body2.internalApplyImpulse(tmp, cpd.frictionAngularComponent0B, -j1);
/*     */ 
/* 294 */           tmp.scale(body2.getInvMass(), cpd.frictionWorldTangential1);
/* 295 */           body2.internalApplyImpulse(tmp, cpd.frictionAngularComponent1B, -j2);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 302 */       return cpd.appliedImpulse; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static float resolveSingleCollisionCombined(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
/*     */   {
/* 315 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 317 */       Vector3f pos1 = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
/* 318 */       Vector3f pos2 = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
/* 319 */       Vector3f normal = contactPoint.normalWorldOnB;
/*     */ 
/* 321 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 322 */       rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 324 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 325 */       rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 327 */       Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 328 */       Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 329 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 330 */       vel.sub(vel1, vel2);
/*     */ 
/* 333 */       float rel_vel = normal.dot(vel);
/*     */ 
/* 335 */       float Kfps = 1.0F / solverInfo.timeStep;
/*     */ 
/* 338 */       float Kerp = solverInfo.erp;
/* 339 */       float Kcor = Kerp * Kfps;
/*     */ 
/* 341 */       ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
/* 342 */       assert (cpd != null);
/* 343 */       float distance = cpd.penetration;
/* 344 */       float positionalError = Kcor * -distance;
/* 345 */       float velocityError = cpd.restitution - rel_vel;
/*     */ 
/* 347 */       float penetrationImpulse = positionalError * cpd.jacDiagABInv;
/*     */ 
/* 349 */       float velocityImpulse = velocityError * cpd.jacDiagABInv;
/*     */ 
/* 351 */       float normalImpulse = penetrationImpulse + velocityImpulse;
/*     */ 
/* 354 */       float oldNormalImpulse = cpd.appliedImpulse;
/* 355 */       float sum = oldNormalImpulse + normalImpulse;
/* 356 */       cpd.appliedImpulse = (0.0F > sum ? 0.0F : sum);
/*     */ 
/* 358 */       normalImpulse = cpd.appliedImpulse - oldNormalImpulse;
/*     */ 
/* 362 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 363 */       if (body1.getInvMass() != 0.0F) {
/* 364 */         tmp.scale(body1.getInvMass(), contactPoint.normalWorldOnB);
/* 365 */         body1.internalApplyImpulse(tmp, cpd.angularComponentA, normalImpulse);
/*     */       }
/* 367 */       if (body2.getInvMass() != 0.0F) {
/* 368 */         tmp.scale(body2.getInvMass(), contactPoint.normalWorldOnB);
/* 369 */         body2.internalApplyImpulse(tmp, cpd.angularComponentB, -normalImpulse);
/*     */       }
/*     */ 
/* 378 */       body1.getVelocityInLocalPoint(rel_pos1, vel1);
/* 379 */       body2.getVelocityInLocalPoint(rel_pos2, vel2);
/* 380 */       vel.sub(vel1, vel2);
/*     */ 
/* 382 */       rel_vel = normal.dot(vel);
/*     */ 
/* 384 */       tmp.scale(rel_vel, normal);
/* 385 */       Vector3f lat_vel = localStack.get$javax$vecmath$Vector3f();
/* 386 */       lat_vel.sub(vel, tmp);
/* 387 */       float lat_rel_vel = lat_vel.length();
/*     */ 
/* 389 */       float combinedFriction = cpd.friction;
/*     */ 
/* 391 */       if ((cpd.appliedImpulse > 0.0F) && 
/* 392 */         (lat_rel_vel > 1.192093E-007F)) {
/* 393 */         lat_vel.scale(1.0F / lat_rel_vel);
/*     */ 
/* 395 */         Vector3f temp1 = localStack.get$javax$vecmath$Vector3f();
/* 396 */         temp1.cross(rel_pos1, lat_vel);
/* 397 */         body1.getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()).transform(temp1);
/*     */ 
/* 399 */         Vector3f temp2 = localStack.get$javax$vecmath$Vector3f();
/* 400 */         temp2.cross(rel_pos2, lat_vel);
/* 401 */         body2.getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()).transform(temp2);
/*     */ 
/* 403 */         Vector3f java_tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 404 */         java_tmp1.cross(temp1, rel_pos1);
/*     */ 
/* 406 */         Vector3f java_tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 407 */         java_tmp2.cross(temp2, rel_pos2);
/*     */ 
/* 409 */         tmp.add(java_tmp1, java_tmp2);
/*     */ 
/* 411 */         float friction_impulse = lat_rel_vel / (body1.getInvMass() + body2.getInvMass() + lat_vel.dot(tmp));
/*     */ 
/* 413 */         float normal_impulse = cpd.appliedImpulse * combinedFriction;
/*     */ 
/* 415 */         friction_impulse = Math.min(friction_impulse, normal_impulse);
/* 416 */         friction_impulse = Math.max(friction_impulse, -normal_impulse);
/*     */ 
/* 418 */         tmp.scale(-friction_impulse, lat_vel);
/* 419 */         body1.applyImpulse(tmp, rel_pos1);
/*     */ 
/* 421 */         tmp.scale(friction_impulse, lat_vel);
/* 422 */         body2.applyImpulse(tmp, rel_pos2);
/*     */       }
/*     */ 
/* 427 */       return normalImpulse;
/*     */     }
/*     */     finally
/*     */     {
/* 427 */       .Stack tmp665_663 = localStack; tmp665_663.pop$javax$vecmath$Vector3f(); tmp665_663.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static float resolveSingleFrictionEmpty(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo solverInfo)
/*     */   {
/* 435 */     return 0.0F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactConstraint
 * JD-Core Version:    0.6.2
 */