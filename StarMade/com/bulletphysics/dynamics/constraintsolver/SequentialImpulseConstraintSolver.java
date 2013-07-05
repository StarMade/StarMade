/*      */ package com.bulletphysics.dynamics.constraintsolver;
/*      */ 
/*      */ import com.bulletphysics..Stack;
/*      */ import com.bulletphysics.BulletGlobals;
/*      */ import com.bulletphysics.BulletStats;
/*      */ import com.bulletphysics.ContactDestroyedCallback;
/*      */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*      */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*      */ import com.bulletphysics.dynamics.RigidBody;
/*      */ import com.bulletphysics.linearmath.IDebugDraw;
/*      */ import com.bulletphysics.linearmath.MiscUtil;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.linearmath.TransformUtil;
/*      */ import com.bulletphysics.util.IntArrayList;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import com.bulletphysics.util.ObjectPool;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ public class SequentialImpulseConstraintSolver extends ConstraintSolver
/*      */ {
/*   59 */   private static final int MAX_CONTACT_SOLVER_TYPES = ContactConstraintEnum.MAX_CONTACT_SOLVER_TYPES.ordinal();
/*      */   private static final int SEQUENTIAL_IMPULSE_MAX_SOLVER_POINTS = 16384;
/*   62 */   private OrderIndex[] gOrder = new OrderIndex[16384];
/*      */ 
/*   64 */   private int totalCpd = 0;
/*      */   private final ObjectPool<SolverBody> bodiesPool;
/*      */   private final ObjectPool<SolverConstraint> constraintsPool;
/*      */   private final ObjectPool<JacobianEntry> jacobiansPool;
/*      */   private final ObjectArrayList<SolverBody> tmpSolverBodyPool;
/*      */   private final ObjectArrayList<SolverConstraint> tmpSolverConstraintPool;
/*      */   private final ObjectArrayList<SolverConstraint> tmpSolverFrictionConstraintPool;
/*      */   private final IntArrayList orderTmpConstraintPool;
/*      */   private final IntArrayList orderFrictionConstraintPool;
/*      */   protected final ContactSolverFunc[][] contactDispatch;
/*      */   protected final ContactSolverFunc[][] frictionDispatch;
/*      */   protected long btSeed2;
/*      */ 
/*      */   public SequentialImpulseConstraintSolver()
/*      */   {
/*   67 */     for (int i = 0; i < this.gOrder.length; i++) {
/*   68 */       this.gOrder[i] = new OrderIndex(null);
/*      */     }
/*      */ 
/*   74 */     this.bodiesPool = ObjectPool.get(SolverBody.class);
/*   75 */     this.constraintsPool = ObjectPool.get(SolverConstraint.class);
/*   76 */     this.jacobiansPool = ObjectPool.get(JacobianEntry.class);
/*      */ 
/*   78 */     this.tmpSolverBodyPool = new ObjectArrayList();
/*   79 */     this.tmpSolverConstraintPool = new ObjectArrayList();
/*   80 */     this.tmpSolverFrictionConstraintPool = new ObjectArrayList();
/*   81 */     this.orderTmpConstraintPool = new IntArrayList();
/*   82 */     this.orderFrictionConstraintPool = new IntArrayList();
/*      */ 
/*   84 */     this.contactDispatch = new ContactSolverFunc[MAX_CONTACT_SOLVER_TYPES][MAX_CONTACT_SOLVER_TYPES];
/*   85 */     this.frictionDispatch = new ContactSolverFunc[MAX_CONTACT_SOLVER_TYPES][MAX_CONTACT_SOLVER_TYPES];
/*      */ 
/*   88 */     this.btSeed2 = 0L;
/*      */ 
/*   91 */     BulletGlobals.setContactDestroyedCallback(new ContactDestroyedCallback() {
/*      */       public boolean contactDestroyed(Object userPersistentData) {
/*   93 */         assert (userPersistentData != null);
/*   94 */         ConstraintPersistentData cpd = (ConstraintPersistentData)userPersistentData;
/*      */ 
/*   96 */         SequentialImpulseConstraintSolver.access$110(SequentialImpulseConstraintSolver.this);
/*      */ 
/*   98 */         return true;
/*      */       }
/*      */     });
/*  104 */     for (int i = 0; i < MAX_CONTACT_SOLVER_TYPES; i++)
/*  105 */       for (int j = 0; j < MAX_CONTACT_SOLVER_TYPES; j++) {
/*  106 */         this.contactDispatch[i][j] = ContactConstraint.resolveSingleCollision;
/*  107 */         this.frictionDispatch[i][j] = ContactConstraint.resolveSingleFriction;
/*      */       }
/*      */   }
/*      */ 
/*      */   public long rand2()
/*      */   {
/*  113 */     this.btSeed2 = (1664525L * this.btSeed2 + 1013904223L & 0xFFFFFFFF);
/*  114 */     return this.btSeed2;
/*      */   }
/*      */ 
/*      */   public int randInt2(int n)
/*      */   {
/*  120 */     long un = n;
/*  121 */     long r = rand2();
/*      */ 
/*  125 */     if (un <= 65536L) {
/*  126 */       r ^= r >>> 16;
/*  127 */       if (un <= 256L) {
/*  128 */         r ^= r >>> 8;
/*  129 */         if (un <= 16L) {
/*  130 */           r ^= r >>> 4;
/*  131 */           if (un <= 4L) {
/*  132 */             r ^= r >>> 2;
/*  133 */             if (un <= 2L) {
/*  134 */               r ^= r >>> 1;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  142 */     return (int)Math.abs(r % un);
/*      */   }
/*      */ 
/*      */   private void initSolverBody(SolverBody arg1, CollisionObject arg2) {
/*  146 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$com$bulletphysics$linearmath$Transform(); RigidBody rb = RigidBody.upcast(collisionObject);
/*  147 */       if (rb != null) {
/*  148 */         rb.getAngularVelocity(solverBody.angularVelocity);
/*  149 */         solverBody.centerOfMassPosition.set(collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/*  150 */         solverBody.friction = collisionObject.getFriction();
/*  151 */         solverBody.invMass = rb.getInvMass();
/*  152 */         rb.getLinearVelocity(solverBody.linearVelocity);
/*  153 */         solverBody.originalBody = rb;
/*  154 */         solverBody.angularFactor = rb.getAngularFactor();
/*      */       }
/*      */       else {
/*  157 */         solverBody.angularVelocity.set(0.0F, 0.0F, 0.0F);
/*  158 */         solverBody.centerOfMassPosition.set(collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/*  159 */         solverBody.friction = collisionObject.getFriction();
/*  160 */         solverBody.invMass = 0.0F;
/*  161 */         solverBody.linearVelocity.set(0.0F, 0.0F, 0.0F);
/*  162 */         solverBody.originalBody = null;
/*  163 */         solverBody.angularFactor = 1.0F; } 
/*      */ solverBody.pushVelocity.set(0.0F, 0.0F, 0.0F);
/*  167 */       solverBody.turnVelocity.set(0.0F, 0.0F, 0.0F);
/*      */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*      */   }
/*      */   private float restitutionCurve(float rel_vel, float restitution) {
/*  171 */     float rest = restitution * -rel_vel;
/*  172 */     return rest;
/*      */   }
/*      */ 
/*      */   private void resolveSplitPenetrationImpulseCacheFriendly(SolverBody arg1, SolverBody arg2, SolverConstraint arg3, ContactSolverInfo arg4)
/*      */   {
/*  181 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); if (contactConstraint.penetration < solverInfo.splitImpulsePenetrationThreshold) {
/*  182 */         BulletStats.gNumSplitImpulseRecoveries += 1;
/*      */ 
/*  192 */         float vel1Dotn = contactConstraint.contactNormal.dot(body1.pushVelocity) + contactConstraint.relpos1CrossNormal.dot(body1.turnVelocity);
/*  193 */         float vel2Dotn = contactConstraint.contactNormal.dot(body2.pushVelocity) + contactConstraint.relpos2CrossNormal.dot(body2.turnVelocity);
/*      */ 
/*  195 */         float rel_vel = vel1Dotn - vel2Dotn;
/*      */ 
/*  197 */         float positionalError = -contactConstraint.penetration * solverInfo.erp2 / solverInfo.timeStep;
/*      */ 
/*  200 */         float velocityError = contactConstraint.restitution - rel_vel;
/*      */ 
/*  202 */         float penetrationImpulse = positionalError * contactConstraint.jacDiagABInv;
/*  203 */         float velocityImpulse = velocityError * contactConstraint.jacDiagABInv;
/*  204 */         float normalImpulse = penetrationImpulse + velocityImpulse;
/*      */ 
/*  207 */         float oldNormalImpulse = contactConstraint.appliedPushImpulse;
/*  208 */         float sum = oldNormalImpulse + normalImpulse;
/*  209 */         contactConstraint.appliedPushImpulse = (0.0F > sum ? 0.0F : sum);
/*      */ 
/*  211 */         normalImpulse = contactConstraint.appliedPushImpulse - oldNormalImpulse;
/*      */ 
/*  213 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  215 */         tmp.scale(body1.invMass, contactConstraint.contactNormal);
/*  216 */         body1.internalApplyPushImpulse(tmp, contactConstraint.angularComponentA, normalImpulse);
/*      */ 
/*  218 */         tmp.scale(body2.invMass, contactConstraint.contactNormal);
/*  219 */         body2.internalApplyPushImpulse(tmp, contactConstraint.angularComponentB, -normalImpulse);
/*      */       }return; } finally {
/*  221 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   private float resolveSingleCollisionCombinedCacheFriendly(SolverBody arg1, SolverBody arg2, SolverConstraint arg3, ContactSolverInfo arg4)
/*      */   {
/*  243 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); float vel1Dotn = contactConstraint.contactNormal.dot(body1.linearVelocity) + contactConstraint.relpos1CrossNormal.dot(body1.angularVelocity);
/*  244 */       float vel2Dotn = contactConstraint.contactNormal.dot(body2.linearVelocity) + contactConstraint.relpos2CrossNormal.dot(body2.angularVelocity);
/*      */ 
/*  246 */       float rel_vel = vel1Dotn - vel2Dotn;
/*      */ 
/*  248 */       float positionalError = 0.0F;
/*  249 */       if ((!solverInfo.splitImpulse) || (contactConstraint.penetration > solverInfo.splitImpulsePenetrationThreshold)) {
/*  250 */         positionalError = -contactConstraint.penetration * solverInfo.erp / solverInfo.timeStep;
/*      */       }
/*      */ 
/*  253 */       float velocityError = contactConstraint.restitution - rel_vel;
/*      */ 
/*  255 */       float penetrationImpulse = positionalError * contactConstraint.jacDiagABInv;
/*  256 */       float velocityImpulse = velocityError * contactConstraint.jacDiagABInv;
/*  257 */       float normalImpulse = penetrationImpulse + velocityImpulse;
/*      */ 
/*  261 */       float oldNormalImpulse = contactConstraint.appliedImpulse;
/*  262 */       float sum = oldNormalImpulse + normalImpulse;
/*  263 */       contactConstraint.appliedImpulse = (0.0F > sum ? 0.0F : sum);
/*      */ 
/*  265 */       normalImpulse = contactConstraint.appliedImpulse - oldNormalImpulse;
/*      */ 
/*  267 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  269 */       tmp.scale(body1.invMass, contactConstraint.contactNormal);
/*  270 */       body1.internalApplyImpulse(tmp, contactConstraint.angularComponentA, normalImpulse);
/*      */ 
/*  272 */       tmp.scale(body2.invMass, contactConstraint.contactNormal);
/*  273 */       body2.internalApplyImpulse(tmp, contactConstraint.angularComponentB, -normalImpulse);
/*      */ 
/*  276 */       return normalImpulse; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   private float resolveSingleFrictionCacheFriendly(SolverBody arg1, SolverBody arg2, SolverConstraint arg3, ContactSolverInfo arg4, float arg5)
/*      */   {
/*  285 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); float combinedFriction = contactConstraint.friction;
/*      */ 
/*  287 */       float limit = appliedNormalImpulse * combinedFriction;
/*      */ 
/*  289 */       if (appliedNormalImpulse > 0.0F)
/*      */       {
/*  296 */         float vel1Dotn = contactConstraint.contactNormal.dot(body1.linearVelocity) + contactConstraint.relpos1CrossNormal.dot(body1.angularVelocity);
/*  297 */         float vel2Dotn = contactConstraint.contactNormal.dot(body2.linearVelocity) + contactConstraint.relpos2CrossNormal.dot(body2.angularVelocity);
/*  298 */         float rel_vel = vel1Dotn - vel2Dotn;
/*      */ 
/*  301 */         float j1 = -rel_vel * contactConstraint.jacDiagABInv;
/*      */ 
/*  304 */         float oldTangentImpulse = contactConstraint.appliedImpulse;
/*  305 */         contactConstraint.appliedImpulse = (oldTangentImpulse + j1);
/*      */ 
/*  307 */         if (limit < contactConstraint.appliedImpulse) {
/*  308 */           contactConstraint.appliedImpulse = limit;
/*      */         }
/*  311 */         else if (contactConstraint.appliedImpulse < -limit) {
/*  312 */           contactConstraint.appliedImpulse = (-limit);
/*      */         }
/*      */ 
/*  315 */         j1 = contactConstraint.appliedImpulse - oldTangentImpulse;
/*      */ 
/*  331 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  333 */         tmp.scale(body1.invMass, contactConstraint.contactNormal);
/*  334 */         body1.internalApplyImpulse(tmp, contactConstraint.angularComponentA, j1);
/*      */ 
/*  336 */         tmp.scale(body2.invMass, contactConstraint.contactNormal);
/*  337 */         body2.internalApplyImpulse(tmp, contactConstraint.angularComponentB, -j1);
/*      */       }
/*  339 */       return 0.0F; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   protected void addFrictionConstraint(Vector3f arg1, int arg2, int arg3, int arg4, ManifoldPoint arg5, Vector3f arg6, Vector3f arg7, CollisionObject arg8, CollisionObject arg9, float arg10)
/*      */   {
/*  344 */     .Stack localStack = .Stack.get();
/*      */     try
/*      */     {
/*      */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); RigidBody body0 = RigidBody.upcast(colObj0);
/*  345 */       RigidBody body1 = RigidBody.upcast(colObj1);
/*      */ 
/*  347 */       SolverConstraint solverConstraint = (SolverConstraint)this.constraintsPool.get();
/*  348 */       this.tmpSolverFrictionConstraintPool.add(solverConstraint);
/*      */ 
/*  350 */       solverConstraint.contactNormal.set(normalAxis);
/*      */ 
/*  352 */       solverConstraint.solverBodyIdA = solverBodyIdA;
/*  353 */       solverConstraint.solverBodyIdB = solverBodyIdB;
/*  354 */       solverConstraint.constraintType = SolverConstraintType.SOLVER_FRICTION_1D;
/*  355 */       solverConstraint.frictionIndex = frictionIndex;
/*      */ 
/*  357 */       solverConstraint.friction = cp.combinedFriction;
/*  358 */       solverConstraint.originalContactPoint = null;
/*      */ 
/*  360 */       solverConstraint.appliedImpulse = 0.0F;
/*  361 */       solverConstraint.appliedPushImpulse = 0.0F;
/*  362 */       solverConstraint.penetration = 0.0F;
/*      */ 
/*  364 */       Vector3f ftorqueAxis1 = localStack.get$javax$vecmath$Vector3f();
/*  365 */       Matrix3f tmpMat = localStack.get$javax$vecmath$Matrix3f();
/*      */ 
/*  368 */       ftorqueAxis1.cross(rel_pos1, solverConstraint.contactNormal);
/*  369 */       solverConstraint.relpos1CrossNormal.set(ftorqueAxis1);
/*  370 */       if (body0 != null) {
/*  371 */         solverConstraint.angularComponentA.set(ftorqueAxis1);
/*  372 */         body0.getInvInertiaTensorWorld(tmpMat).transform(solverConstraint.angularComponentA);
/*      */       }
/*      */       else {
/*  375 */         solverConstraint.angularComponentA.set(0.0F, 0.0F, 0.0F);
/*      */       }
/*      */ 
/*  379 */       ftorqueAxis1.cross(rel_pos2, solverConstraint.contactNormal);
/*  380 */       solverConstraint.relpos2CrossNormal.set(ftorqueAxis1);
/*  381 */       if (body1 != null) {
/*  382 */         solverConstraint.angularComponentB.set(ftorqueAxis1);
/*  383 */         body1.getInvInertiaTensorWorld(tmpMat).transform(solverConstraint.angularComponentB);
/*      */       }
/*      */       else {
/*  386 */         solverConstraint.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*      */       }
/*      */ 
/*  394 */       Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/*  395 */       float denom0 = 0.0F;
/*  396 */       float denom1 = 0.0F;
/*  397 */       if (body0 != null) {
/*  398 */         vec.cross(solverConstraint.angularComponentA, rel_pos1);
/*  399 */         denom0 = body0.getInvMass() + normalAxis.dot(vec);
/*      */       }
/*  401 */       if (body1 != null) {
/*  402 */         vec.cross(solverConstraint.angularComponentB, rel_pos2);
/*  403 */         denom1 = body1.getInvMass() + normalAxis.dot(vec);
/*      */       }
/*      */ 
/*  407 */       float denom = relaxation / (denom0 + denom1);
/*  408 */       solverConstraint.jacDiagABInv = denom;
/*      */       return;
/*      */     }
/*      */     finally
/*      */     {
/*  409 */       .Stack tmp370_368 = localStack; tmp370_368.pop$javax$vecmath$Vector3f(); tmp370_368.pop$javax$vecmath$Matrix3f(); } throw finally; } 
/*      */   public float solveGroupCacheFriendlySetup(ObjectArrayList<CollisionObject> arg1, int arg2, ObjectArrayList<PersistentManifold> arg3, int arg4, int arg5, ObjectArrayList<TypedConstraint> arg6, int arg7, int arg8, ContactSolverInfo arg9, IDebugDraw arg10) { // Byte code:
/*      */     //   0: invokestatic 154	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/*      */     //   3: astore 43
/*      */     //   5: aload 43
/*      */     //   7: dup
/*      */     //   8: invokevirtual 157	com/bulletphysics/$Stack:push$com$bulletphysics$linearmath$Transform	()V
/*      */     //   11: dup
/*      */     //   12: invokevirtual 254	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/*      */     //   15: invokevirtual 358	com/bulletphysics/$Stack:push$javax$vecmath$Matrix3f	()V
/*      */     //   18: ldc_w 431
/*      */     //   21: invokestatic 435	com/bulletphysics/BulletStats:pushProfile	(Ljava/lang/String;)V
/*      */     //   24: iload 8
/*      */     //   26: iload 5
/*      */     //   28: iadd
/*      */     //   29: ifne +25 -> 54
/*      */     //   32: fconst_0
/*      */     //   33: fstore 11
/*      */     //   35: invokestatic 438	com/bulletphysics/BulletStats:popProfile	()V
/*      */     //   38: fload 11
/*      */     //   40: aload 43
/*      */     //   42: dup
/*      */     //   43: invokevirtual 237	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*      */     //   46: dup
/*      */     //   47: invokevirtual 315	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*      */     //   50: invokevirtual 411	com/bulletphysics/$Stack:pop$javax$vecmath$Matrix3f	()V
/*      */     //   53: freturn
/*      */     //   54: aconst_null
/*      */     //   55: astore 11
/*      */     //   57: aconst_null
/*      */     //   58: astore 12
/*      */     //   60: aconst_null
/*      */     //   61: astore 13
/*      */     //   63: aload 43
/*      */     //   65: invokevirtual 178	com/bulletphysics/$Stack:get$com$bulletphysics$linearmath$Transform	()Lcom/bulletphysics/linearmath/Transform;
/*      */     //   68: astore 14
/*      */     //   70: aload 43
/*      */     //   72: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   75: astore 16
/*      */     //   77: aload 43
/*      */     //   79: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   82: astore 17
/*      */     //   84: aload 43
/*      */     //   86: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   89: astore 18
/*      */     //   91: aload 43
/*      */     //   93: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   96: astore 19
/*      */     //   98: aload 43
/*      */     //   100: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   103: astore 20
/*      */     //   105: aload 43
/*      */     //   107: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   110: astore 21
/*      */     //   112: aload 43
/*      */     //   114: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   117: astore 22
/*      */     //   119: aload 43
/*      */     //   121: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   124: astore 23
/*      */     //   126: aload 43
/*      */     //   128: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   131: astore 24
/*      */     //   133: aload 43
/*      */     //   135: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   138: astore 25
/*      */     //   140: aload 43
/*      */     //   142: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   145: astore 26
/*      */     //   147: aload 43
/*      */     //   149: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   152: astore 27
/*      */     //   154: aload 43
/*      */     //   156: invokevirtual 395	com/bulletphysics/$Stack:get$javax$vecmath$Matrix3f	()Ljavax/vecmath/Matrix3f;
/*      */     //   159: astore 28
/*      */     //   161: iconst_0
/*      */     //   162: istore 15
/*      */     //   164: iload 15
/*      */     //   166: iload 5
/*      */     //   168: if_icmpge +1625 -> 1793
/*      */     //   171: aload_3
/*      */     //   172: iload 4
/*      */     //   174: iload 15
/*      */     //   176: iadd
/*      */     //   177: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   180: checkcast 444	com/bulletphysics/collision/narrowphase/PersistentManifold
/*      */     //   183: astore 11
/*      */     //   185: aload 11
/*      */     //   187: invokevirtual 447	com/bulletphysics/collision/narrowphase/PersistentManifold:getBody0	()Ljava/lang/Object;
/*      */     //   190: checkcast 180	com/bulletphysics/collision/dispatch/CollisionObject
/*      */     //   193: astore 12
/*      */     //   195: aload 11
/*      */     //   197: invokevirtual 450	com/bulletphysics/collision/narrowphase/PersistentManifold:getBody1	()Ljava/lang/Object;
/*      */     //   200: checkcast 180	com/bulletphysics/collision/dispatch/CollisionObject
/*      */     //   203: astore 13
/*      */     //   205: iconst_m1
/*      */     //   206: istore 29
/*      */     //   208: iconst_m1
/*      */     //   209: istore 30
/*      */     //   211: aload 11
/*      */     //   213: invokevirtual 454	com/bulletphysics/collision/narrowphase/PersistentManifold:getNumContacts	()I
/*      */     //   216: ifeq +231 -> 447
/*      */     //   219: aload 12
/*      */     //   221: invokevirtual 457	com/bulletphysics/collision/dispatch/CollisionObject:getIslandTag	()I
/*      */     //   224: iflt +70 -> 294
/*      */     //   227: aload 12
/*      */     //   229: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*      */     //   232: iflt +13 -> 245
/*      */     //   235: aload 12
/*      */     //   237: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*      */     //   240: istore 29
/*      */     //   242: goto +91 -> 333
/*      */     //   245: aload_0
/*      */     //   246: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   249: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   252: istore 29
/*      */     //   254: aload_0
/*      */     //   255: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*      */     //   258: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*      */     //   261: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   264: astore 31
/*      */     //   266: aload_0
/*      */     //   267: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   270: aload 31
/*      */     //   272: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   275: pop
/*      */     //   276: aload_0
/*      */     //   277: aload 31
/*      */     //   279: aload 12
/*      */     //   281: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*      */     //   284: aload 12
/*      */     //   286: iload 29
/*      */     //   288: invokevirtual 469	com/bulletphysics/collision/dispatch/CollisionObject:setCompanionId	(I)V
/*      */     //   291: goto +42 -> 333
/*      */     //   294: aload_0
/*      */     //   295: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   298: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   301: istore 29
/*      */     //   303: aload_0
/*      */     //   304: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*      */     //   307: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*      */     //   310: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   313: astore 31
/*      */     //   315: aload_0
/*      */     //   316: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   319: aload 31
/*      */     //   321: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   324: pop
/*      */     //   325: aload_0
/*      */     //   326: aload 31
/*      */     //   328: aload 12
/*      */     //   330: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*      */     //   333: aload 13
/*      */     //   335: invokevirtual 457	com/bulletphysics/collision/dispatch/CollisionObject:getIslandTag	()I
/*      */     //   338: iflt +70 -> 408
/*      */     //   341: aload 13
/*      */     //   343: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*      */     //   346: iflt +13 -> 359
/*      */     //   349: aload 13
/*      */     //   351: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*      */     //   354: istore 30
/*      */     //   356: goto +91 -> 447
/*      */     //   359: aload_0
/*      */     //   360: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   363: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   366: istore 30
/*      */     //   368: aload_0
/*      */     //   369: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*      */     //   372: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*      */     //   375: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   378: astore 31
/*      */     //   380: aload_0
/*      */     //   381: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   384: aload 31
/*      */     //   386: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   389: pop
/*      */     //   390: aload_0
/*      */     //   391: aload 31
/*      */     //   393: aload 13
/*      */     //   395: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*      */     //   398: aload 13
/*      */     //   400: iload 30
/*      */     //   402: invokevirtual 469	com/bulletphysics/collision/dispatch/CollisionObject:setCompanionId	(I)V
/*      */     //   405: goto +42 -> 447
/*      */     //   408: aload_0
/*      */     //   409: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   412: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   415: istore 30
/*      */     //   417: aload_0
/*      */     //   418: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*      */     //   421: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*      */     //   424: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   427: astore 31
/*      */     //   429: aload_0
/*      */     //   430: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   433: aload 31
/*      */     //   435: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   438: pop
/*      */     //   439: aload_0
/*      */     //   440: aload 31
/*      */     //   442: aload 13
/*      */     //   444: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*      */     //   447: iconst_0
/*      */     //   448: istore 32
/*      */     //   450: iload 32
/*      */     //   452: aload 11
/*      */     //   454: invokevirtual 454	com/bulletphysics/collision/narrowphase/PersistentManifold:getNumContacts	()I
/*      */     //   457: if_icmpge +1330 -> 1787
/*      */     //   460: aload 11
/*      */     //   462: iload 32
/*      */     //   464: invokevirtual 473	com/bulletphysics/collision/narrowphase/PersistentManifold:getContactPoint	(I)Lcom/bulletphysics/collision/narrowphase/ManifoldPoint;
/*      */     //   467: astore 33
/*      */     //   469: aload 33
/*      */     //   471: invokevirtual 476	com/bulletphysics/collision/narrowphase/ManifoldPoint:getDistance	()F
/*      */     //   474: fconst_0
/*      */     //   475: fcmpg
/*      */     //   476: ifgt +1305 -> 1781
/*      */     //   479: aload 33
/*      */     //   481: aload 18
/*      */     //   483: invokevirtual 479	com/bulletphysics/collision/narrowphase/ManifoldPoint:getPositionWorldOnA	(Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*      */     //   486: pop
/*      */     //   487: aload 33
/*      */     //   489: aload 19
/*      */     //   491: invokevirtual 482	com/bulletphysics/collision/narrowphase/ManifoldPoint:getPositionWorldOnB	(Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*      */     //   494: pop
/*      */     //   495: aload 16
/*      */     //   497: aload 18
/*      */     //   499: aload 12
/*      */     //   501: aload 14
/*      */     //   503: invokevirtual 184	com/bulletphysics/collision/dispatch/CollisionObject:getWorldTransform	(Lcom/bulletphysics/linearmath/Transform;)Lcom/bulletphysics/linearmath/Transform;
/*      */     //   506: getfield 189	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*      */     //   509: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*      */     //   512: aload 17
/*      */     //   514: aload 19
/*      */     //   516: aload 13
/*      */     //   518: aload 14
/*      */     //   520: invokevirtual 184	com/bulletphysics/collision/dispatch/CollisionObject:getWorldTransform	(Lcom/bulletphysics/linearmath/Transform;)Lcom/bulletphysics/linearmath/Transform;
/*      */     //   523: getfield 189	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*      */     //   526: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*      */     //   529: fconst_1
/*      */     //   530: fstore 31
/*      */     //   532: aload_0
/*      */     //   533: getfield 76	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   536: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   539: istore 35
/*      */     //   541: aload_0
/*      */     //   542: getfield 65	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:constraintsPool	Lcom/bulletphysics/util/ObjectPool;
/*      */     //   545: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*      */     //   548: checkcast 63	com/bulletphysics/dynamics/constraintsolver/SolverConstraint
/*      */     //   551: astore 36
/*      */     //   553: aload_0
/*      */     //   554: getfield 76	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   557: aload 36
/*      */     //   559: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   562: pop
/*      */     //   563: aload 12
/*      */     //   565: invokestatic 163	com/bulletphysics/dynamics/RigidBody:upcast	(Lcom/bulletphysics/collision/dispatch/CollisionObject;)Lcom/bulletphysics/dynamics/RigidBody;
/*      */     //   568: astore 37
/*      */     //   570: aload 13
/*      */     //   572: invokestatic 163	com/bulletphysics/dynamics/RigidBody:upcast	(Lcom/bulletphysics/collision/dispatch/CollisionObject;)Lcom/bulletphysics/dynamics/RigidBody;
/*      */     //   575: astore 38
/*      */     //   577: aload 36
/*      */     //   579: iload 29
/*      */     //   581: putfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/*      */     //   584: aload 36
/*      */     //   586: iload 30
/*      */     //   588: putfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/*      */     //   591: aload 36
/*      */     //   593: getstatic 489	com/bulletphysics/dynamics/constraintsolver/SolverConstraintType:SOLVER_CONTACT_1D	Lcom/bulletphysics/dynamics/constraintsolver/SolverConstraintType;
/*      */     //   596: putfield 380	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:constraintType	Lcom/bulletphysics/dynamics/constraintsolver/SolverConstraintType;
/*      */     //   599: aload 36
/*      */     //   601: aload 33
/*      */     //   603: putfield 391	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:originalContactPoint	Ljava/lang/Object;
/*      */     //   606: aload 21
/*      */     //   608: aload 16
/*      */     //   610: aload 33
/*      */     //   612: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   615: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   618: aload 37
/*      */     //   620: ifnull +31 -> 651
/*      */     //   623: aload 36
/*      */     //   625: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   628: aload 21
/*      */     //   630: invokevirtual 195	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*      */     //   633: aload 37
/*      */     //   635: aload 28
/*      */     //   637: invokevirtual 403	com/bulletphysics/dynamics/RigidBody:getInvInertiaTensorWorld	(Ljavax/vecmath/Matrix3f;)Ljavax/vecmath/Matrix3f;
/*      */     //   640: aload 36
/*      */     //   642: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   645: invokevirtual 408	javax/vecmath/Matrix3f:transform	(Ljavax/vecmath/Tuple3f;)V
/*      */     //   648: goto +14 -> 662
/*      */     //   651: aload 36
/*      */     //   653: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   656: fconst_0
/*      */     //   657: fconst_0
/*      */     //   658: fconst_0
/*      */     //   659: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*      */     //   662: aload 22
/*      */     //   664: aload 17
/*      */     //   666: aload 33
/*      */     //   668: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   671: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   674: aload 38
/*      */     //   676: ifnull +31 -> 707
/*      */     //   679: aload 36
/*      */     //   681: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   684: aload 22
/*      */     //   686: invokevirtual 195	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*      */     //   689: aload 38
/*      */     //   691: aload 28
/*      */     //   693: invokevirtual 403	com/bulletphysics/dynamics/RigidBody:getInvInertiaTensorWorld	(Ljavax/vecmath/Matrix3f;)Ljavax/vecmath/Matrix3f;
/*      */     //   696: aload 36
/*      */     //   698: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   701: invokevirtual 408	javax/vecmath/Matrix3f:transform	(Ljavax/vecmath/Tuple3f;)V
/*      */     //   704: goto +14 -> 718
/*      */     //   707: aload 36
/*      */     //   709: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   712: fconst_0
/*      */     //   713: fconst_0
/*      */     //   714: fconst_0
/*      */     //   715: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*      */     //   718: fconst_0
/*      */     //   719: fstore 39
/*      */     //   721: fconst_0
/*      */     //   722: fstore 40
/*      */     //   724: aload 37
/*      */     //   726: ifnull +33 -> 759
/*      */     //   729: aload 27
/*      */     //   731: aload 36
/*      */     //   733: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   736: aload 16
/*      */     //   738: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   741: aload 37
/*      */     //   743: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   746: aload 33
/*      */     //   748: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   751: aload 27
/*      */     //   753: invokevirtual 274	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*      */     //   756: fadd
/*      */     //   757: fstore 39
/*      */     //   759: aload 38
/*      */     //   761: ifnull +33 -> 794
/*      */     //   764: aload 27
/*      */     //   766: aload 36
/*      */     //   768: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   771: aload 17
/*      */     //   773: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   776: aload 38
/*      */     //   778: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   781: aload 33
/*      */     //   783: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   786: aload 27
/*      */     //   788: invokevirtual 274	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*      */     //   791: fadd
/*      */     //   792: fstore 40
/*      */     //   794: fload 31
/*      */     //   796: fload 39
/*      */     //   798: fload 40
/*      */     //   800: fadd
/*      */     //   801: fdiv
/*      */     //   802: fstore 41
/*      */     //   804: aload 36
/*      */     //   806: fload 41
/*      */     //   808: putfield 291	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:jacDiagABInv	F
/*      */     //   811: aload 36
/*      */     //   813: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   816: aload 33
/*      */     //   818: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   821: invokevirtual 195	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*      */     //   824: aload 36
/*      */     //   826: getfield 277	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:relpos1CrossNormal	Ljavax/vecmath/Vector3f;
/*      */     //   829: aload 16
/*      */     //   831: aload 33
/*      */     //   833: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   836: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   839: aload 36
/*      */     //   841: getfield 280	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:relpos2CrossNormal	Ljavax/vecmath/Vector3f;
/*      */     //   844: aload 17
/*      */     //   846: aload 33
/*      */     //   848: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   851: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   854: aload 37
/*      */     //   856: ifnull +16 -> 872
/*      */     //   859: aload 37
/*      */     //   861: aload 16
/*      */     //   863: aload 23
/*      */     //   865: invokevirtual 496	com/bulletphysics/dynamics/RigidBody:getVelocityInLocalPoint	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*      */     //   868: pop
/*      */     //   869: goto +11 -> 880
/*      */     //   872: aload 23
/*      */     //   874: fconst_0
/*      */     //   875: fconst_0
/*      */     //   876: fconst_0
/*      */     //   877: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*      */     //   880: aload 38
/*      */     //   882: ifnull +16 -> 898
/*      */     //   885: aload 38
/*      */     //   887: aload 17
/*      */     //   889: aload 24
/*      */     //   891: invokevirtual 496	com/bulletphysics/dynamics/RigidBody:getVelocityInLocalPoint	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*      */     //   894: pop
/*      */     //   895: goto +11 -> 906
/*      */     //   898: aload 24
/*      */     //   900: fconst_0
/*      */     //   901: fconst_0
/*      */     //   902: fconst_0
/*      */     //   903: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*      */     //   906: aload 20
/*      */     //   908: aload 23
/*      */     //   910: aload 24
/*      */     //   912: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*      */     //   915: aload 33
/*      */     //   917: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   920: aload 20
/*      */     //   922: invokevirtual 274	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*      */     //   925: fstore 34
/*      */     //   927: aload 36
/*      */     //   929: aload 33
/*      */     //   931: invokevirtual 476	com/bulletphysics/collision/narrowphase/ManifoldPoint:getDistance	()F
/*      */     //   934: aload 9
/*      */     //   936: getfield 499	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:linearSlop	F
/*      */     //   939: fadd
/*      */     //   940: fconst_0
/*      */     //   941: invokestatic 502	java/lang/Math:min	(FF)F
/*      */     //   944: putfield 257	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:penetration	F
/*      */     //   947: aload 36
/*      */     //   949: aload 33
/*      */     //   951: getfield 387	com/bulletphysics/collision/narrowphase/ManifoldPoint:combinedFriction	F
/*      */     //   954: putfield 348	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:friction	F
/*      */     //   957: aload 36
/*      */     //   959: aload_0
/*      */     //   960: fload 34
/*      */     //   962: aload 33
/*      */     //   964: getfield 505	com/bulletphysics/collision/narrowphase/ManifoldPoint:combinedRestitution	F
/*      */     //   967: invokespecial 507	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:restitutionCurve	(FF)F
/*      */     //   970: putfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*      */     //   973: aload 36
/*      */     //   975: getfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*      */     //   978: fconst_0
/*      */     //   979: fcmpg
/*      */     //   980: ifgt +9 -> 989
/*      */     //   983: aload 36
/*      */     //   985: fconst_0
/*      */     //   986: putfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*      */     //   989: aload 36
/*      */     //   991: getfield 257	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:penetration	F
/*      */     //   994: fneg
/*      */     //   995: aload 9
/*      */     //   997: getfield 286	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:timeStep	F
/*      */     //   1000: fdiv
/*      */     //   1001: fstore 39
/*      */     //   1003: aload 36
/*      */     //   1005: getfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*      */     //   1008: fload 39
/*      */     //   1010: fcmpl
/*      */     //   1011: ifle +9 -> 1020
/*      */     //   1014: aload 36
/*      */     //   1016: fconst_0
/*      */     //   1017: putfield 257	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:penetration	F
/*      */     //   1020: aload 43
/*      */     //   1022: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*      */     //   1025: astore 40
/*      */     //   1027: aload 9
/*      */     //   1029: getfield 510	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:solverMode	I
/*      */     //   1032: iconst_4
/*      */     //   1033: iand
/*      */     //   1034: ifeq +123 -> 1157
/*      */     //   1037: aload 36
/*      */     //   1039: aload 33
/*      */     //   1041: getfield 511	com/bulletphysics/collision/narrowphase/ManifoldPoint:appliedImpulse	F
/*      */     //   1044: aload 9
/*      */     //   1046: getfield 514	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:warmstartingFactor	F
/*      */     //   1049: fmul
/*      */     //   1050: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1053: aload 37
/*      */     //   1055: ifnull +48 -> 1103
/*      */     //   1058: aload 40
/*      */     //   1060: aload 37
/*      */     //   1062: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   1065: aload 36
/*      */     //   1067: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   1070: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1073: aload_0
/*      */     //   1074: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1077: aload 36
/*      */     //   1079: getfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/*      */     //   1082: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1085: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   1088: aload 40
/*      */     //   1090: aload 36
/*      */     //   1092: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   1095: aload 36
/*      */     //   1097: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1100: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*      */     //   1103: aload 38
/*      */     //   1105: ifnull +58 -> 1163
/*      */     //   1108: aload 40
/*      */     //   1110: aload 38
/*      */     //   1112: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   1115: aload 36
/*      */     //   1117: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   1120: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1123: aload_0
/*      */     //   1124: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1127: aload 36
/*      */     //   1129: getfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/*      */     //   1132: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1135: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   1138: aload 40
/*      */     //   1140: aload 36
/*      */     //   1142: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   1145: aload 36
/*      */     //   1147: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1150: fneg
/*      */     //   1151: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*      */     //   1154: goto +9 -> 1163
/*      */     //   1157: aload 36
/*      */     //   1159: fconst_0
/*      */     //   1160: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1163: aload 36
/*      */     //   1165: fconst_0
/*      */     //   1166: putfield 294	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedPushImpulse	F
/*      */     //   1169: aload 36
/*      */     //   1171: aload_0
/*      */     //   1172: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1175: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   1178: putfield 383	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:frictionIndex	I
/*      */     //   1181: aload 33
/*      */     //   1183: getfield 517	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionInitialized	Z
/*      */     //   1186: ifne +233 -> 1419
/*      */     //   1189: aload 33
/*      */     //   1191: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1194: fload 34
/*      */     //   1196: aload 33
/*      */     //   1198: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   1201: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1204: aload 33
/*      */     //   1206: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1209: aload 20
/*      */     //   1211: aload 33
/*      */     //   1213: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1216: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*      */     //   1219: aload 33
/*      */     //   1221: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1224: invokevirtual 523	javax/vecmath/Vector3f:lengthSquared	()F
/*      */     //   1227: fstore 41
/*      */     //   1229: fload 41
/*      */     //   1231: ldc_w 524
/*      */     //   1234: fcmpl
/*      */     //   1235: ifle +103 -> 1338
/*      */     //   1238: aload 33
/*      */     //   1240: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1243: fconst_1
/*      */     //   1244: fload 41
/*      */     //   1246: f2d
/*      */     //   1247: invokestatic 528	java/lang/Math:sqrt	(D)D
/*      */     //   1250: d2f
/*      */     //   1251: fdiv
/*      */     //   1252: invokevirtual 531	javax/vecmath/Vector3f:scale	(F)V
/*      */     //   1255: aload_0
/*      */     //   1256: aload 33
/*      */     //   1258: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1261: iload 29
/*      */     //   1263: iload 30
/*      */     //   1265: iload 35
/*      */     //   1267: aload 33
/*      */     //   1269: aload 16
/*      */     //   1271: aload 17
/*      */     //   1273: aload 12
/*      */     //   1275: aload 13
/*      */     //   1277: fload 31
/*      */     //   1279: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*      */     //   1282: aload 33
/*      */     //   1284: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*      */     //   1287: aload 33
/*      */     //   1289: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1292: aload 33
/*      */     //   1294: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   1297: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   1300: aload 33
/*      */     //   1302: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*      */     //   1305: invokevirtual 539	javax/vecmath/Vector3f:normalize	()V
/*      */     //   1308: aload_0
/*      */     //   1309: aload 33
/*      */     //   1311: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*      */     //   1314: iload 29
/*      */     //   1316: iload 30
/*      */     //   1318: iload 35
/*      */     //   1320: aload 33
/*      */     //   1322: aload 16
/*      */     //   1324: aload 17
/*      */     //   1326: aload 12
/*      */     //   1328: aload 13
/*      */     //   1330: fload 31
/*      */     //   1332: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*      */     //   1335: goto +75 -> 1410
/*      */     //   1338: aload 33
/*      */     //   1340: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*      */     //   1343: aload 33
/*      */     //   1345: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1348: aload 33
/*      */     //   1350: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*      */     //   1353: invokestatic 545	com/bulletphysics/linearmath/TransformUtil:planeSpace1	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*      */     //   1356: aload_0
/*      */     //   1357: aload 33
/*      */     //   1359: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1362: iload 29
/*      */     //   1364: iload 30
/*      */     //   1366: iload 35
/*      */     //   1368: aload 33
/*      */     //   1370: aload 16
/*      */     //   1372: aload 17
/*      */     //   1374: aload 12
/*      */     //   1376: aload 13
/*      */     //   1378: fload 31
/*      */     //   1380: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*      */     //   1383: aload_0
/*      */     //   1384: aload 33
/*      */     //   1386: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*      */     //   1389: iload 29
/*      */     //   1391: iload 30
/*      */     //   1393: iload 35
/*      */     //   1395: aload 33
/*      */     //   1397: aload 16
/*      */     //   1399: aload 17
/*      */     //   1401: aload 12
/*      */     //   1403: aload 13
/*      */     //   1405: fload 31
/*      */     //   1407: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*      */     //   1410: aload 33
/*      */     //   1412: iconst_1
/*      */     //   1413: putfield 517	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionInitialized	Z
/*      */     //   1416: goto +57 -> 1473
/*      */     //   1419: aload_0
/*      */     //   1420: aload 33
/*      */     //   1422: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*      */     //   1425: iload 29
/*      */     //   1427: iload 30
/*      */     //   1429: iload 35
/*      */     //   1431: aload 33
/*      */     //   1433: aload 16
/*      */     //   1435: aload 17
/*      */     //   1437: aload 12
/*      */     //   1439: aload 13
/*      */     //   1441: fload 31
/*      */     //   1443: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*      */     //   1446: aload_0
/*      */     //   1447: aload 33
/*      */     //   1449: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*      */     //   1452: iload 29
/*      */     //   1454: iload 30
/*      */     //   1456: iload 35
/*      */     //   1458: aload 33
/*      */     //   1460: aload 16
/*      */     //   1462: aload 17
/*      */     //   1464: aload 12
/*      */     //   1466: aload 13
/*      */     //   1468: fload 31
/*      */     //   1470: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*      */     //   1473: aload_0
/*      */     //   1474: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1477: aload 36
/*      */     //   1479: getfield 383	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:frictionIndex	I
/*      */     //   1482: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1485: checkcast 63	com/bulletphysics/dynamics/constraintsolver/SolverConstraint
/*      */     //   1488: astore 41
/*      */     //   1490: aload 9
/*      */     //   1492: getfield 510	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:solverMode	I
/*      */     //   1495: iconst_4
/*      */     //   1496: iand
/*      */     //   1497: ifeq +123 -> 1620
/*      */     //   1500: aload 41
/*      */     //   1502: aload 33
/*      */     //   1504: getfield 548	com/bulletphysics/collision/narrowphase/ManifoldPoint:appliedImpulseLateral1	F
/*      */     //   1507: aload 9
/*      */     //   1509: getfield 514	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:warmstartingFactor	F
/*      */     //   1512: fmul
/*      */     //   1513: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1516: aload 37
/*      */     //   1518: ifnull +48 -> 1566
/*      */     //   1521: aload 40
/*      */     //   1523: aload 37
/*      */     //   1525: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   1528: aload 41
/*      */     //   1530: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   1533: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1536: aload_0
/*      */     //   1537: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1540: aload 36
/*      */     //   1542: getfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/*      */     //   1545: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1548: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   1551: aload 40
/*      */     //   1553: aload 41
/*      */     //   1555: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   1558: aload 41
/*      */     //   1560: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1563: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*      */     //   1566: aload 38
/*      */     //   1568: ifnull +58 -> 1626
/*      */     //   1571: aload 40
/*      */     //   1573: aload 38
/*      */     //   1575: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   1578: aload 41
/*      */     //   1580: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   1583: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1586: aload_0
/*      */     //   1587: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1590: aload 36
/*      */     //   1592: getfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/*      */     //   1595: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1598: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   1601: aload 40
/*      */     //   1603: aload 41
/*      */     //   1605: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   1608: aload 41
/*      */     //   1610: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1613: fneg
/*      */     //   1614: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*      */     //   1617: goto +9 -> 1626
/*      */     //   1620: aload 41
/*      */     //   1622: fconst_0
/*      */     //   1623: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1626: aload_0
/*      */     //   1627: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1630: aload 36
/*      */     //   1632: getfield 383	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:frictionIndex	I
/*      */     //   1635: iconst_1
/*      */     //   1636: iadd
/*      */     //   1637: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1640: checkcast 63	com/bulletphysics/dynamics/constraintsolver/SolverConstraint
/*      */     //   1643: astore 41
/*      */     //   1645: aload 9
/*      */     //   1647: getfield 510	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:solverMode	I
/*      */     //   1650: iconst_4
/*      */     //   1651: iand
/*      */     //   1652: ifeq +123 -> 1775
/*      */     //   1655: aload 41
/*      */     //   1657: aload 33
/*      */     //   1659: getfield 551	com/bulletphysics/collision/narrowphase/ManifoldPoint:appliedImpulseLateral2	F
/*      */     //   1662: aload 9
/*      */     //   1664: getfield 514	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:warmstartingFactor	F
/*      */     //   1667: fmul
/*      */     //   1668: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1671: aload 37
/*      */     //   1673: ifnull +48 -> 1721
/*      */     //   1676: aload 40
/*      */     //   1678: aload 37
/*      */     //   1680: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   1683: aload 41
/*      */     //   1685: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   1688: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1691: aload_0
/*      */     //   1692: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1695: aload 36
/*      */     //   1697: getfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/*      */     //   1700: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1703: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   1706: aload 40
/*      */     //   1708: aload 41
/*      */     //   1710: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*      */     //   1713: aload 41
/*      */     //   1715: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1718: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*      */     //   1721: aload 38
/*      */     //   1723: ifnull +58 -> 1781
/*      */     //   1726: aload 40
/*      */     //   1728: aload 38
/*      */     //   1730: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*      */     //   1733: aload 41
/*      */     //   1735: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*      */     //   1738: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*      */     //   1741: aload_0
/*      */     //   1742: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1745: aload 36
/*      */     //   1747: getfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/*      */     //   1750: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1753: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*      */     //   1756: aload 40
/*      */     //   1758: aload 41
/*      */     //   1760: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*      */     //   1763: aload 41
/*      */     //   1765: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1768: fneg
/*      */     //   1769: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*      */     //   1772: goto +9 -> 1781
/*      */     //   1775: aload 41
/*      */     //   1777: fconst_0
/*      */     //   1778: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*      */     //   1781: iinc 32 1
/*      */     //   1784: goto -1334 -> 450
/*      */     //   1787: iinc 15 1
/*      */     //   1790: goto -1626 -> 164
/*      */     //   1793: iconst_0
/*      */     //   1794: istore 15
/*      */     //   1796: iload 15
/*      */     //   1798: iload 8
/*      */     //   1800: if_icmpge +29 -> 1829
/*      */     //   1803: aload 6
/*      */     //   1805: iload 7
/*      */     //   1807: iload 15
/*      */     //   1809: iadd
/*      */     //   1810: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*      */     //   1813: checkcast 553	com/bulletphysics/dynamics/constraintsolver/TypedConstraint
/*      */     //   1816: astore 16
/*      */     //   1818: aload 16
/*      */     //   1820: invokevirtual 556	com/bulletphysics/dynamics/constraintsolver/TypedConstraint:buildJacobian	()V
/*      */     //   1823: iinc 15 1
/*      */     //   1826: goto -30 -> 1796
/*      */     //   1829: aload_0
/*      */     //   1830: getfield 76	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1833: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   1836: istore 15
/*      */     //   1838: aload_0
/*      */     //   1839: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*      */     //   1842: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*      */     //   1845: istore 16
/*      */     //   1847: aload_0
/*      */     //   1848: getfield 83	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderTmpConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/*      */     //   1851: iload 15
/*      */     //   1853: iconst_0
/*      */     //   1854: invokestatic 562	com/bulletphysics/linearmath/MiscUtil:resize	(Lcom/bulletphysics/util/IntArrayList;II)V
/*      */     //   1857: aload_0
/*      */     //   1858: getfield 85	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderFrictionConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/*      */     //   1861: iload 16
/*      */     //   1863: iconst_0
/*      */     //   1864: invokestatic 562	com/bulletphysics/linearmath/MiscUtil:resize	(Lcom/bulletphysics/util/IntArrayList;II)V
/*      */     //   1867: iconst_0
/*      */     //   1868: istore 17
/*      */     //   1870: iload 17
/*      */     //   1872: iload 15
/*      */     //   1874: if_icmpge +20 -> 1894
/*      */     //   1877: aload_0
/*      */     //   1878: getfield 83	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderTmpConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/*      */     //   1881: iload 17
/*      */     //   1883: iload 17
/*      */     //   1885: invokevirtual 565	com/bulletphysics/util/IntArrayList:set	(II)V
/*      */     //   1888: iinc 17 1
/*      */     //   1891: goto -21 -> 1870
/*      */     //   1894: iconst_0
/*      */     //   1895: istore 17
/*      */     //   1897: iload 17
/*      */     //   1899: iload 16
/*      */     //   1901: if_icmpge +20 -> 1921
/*      */     //   1904: aload_0
/*      */     //   1905: getfield 85	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderFrictionConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/*      */     //   1908: iload 17
/*      */     //   1910: iload 17
/*      */     //   1912: invokevirtual 565	com/bulletphysics/util/IntArrayList:set	(II)V
/*      */     //   1915: iinc 17 1
/*      */     //   1918: goto -21 -> 1897
/*      */     //   1921: fconst_0
/*      */     //   1922: fstore 17
/*      */     //   1924: invokestatic 438	com/bulletphysics/BulletStats:popProfile	()V
/*      */     //   1927: fload 17
/*      */     //   1929: aload 43
/*      */     //   1931: dup
/*      */     //   1932: invokevirtual 237	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*      */     //   1935: dup
/*      */     //   1936: invokevirtual 315	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*      */     //   1939: invokevirtual 411	com/bulletphysics/$Stack:pop$javax$vecmath$Matrix3f	()V
/*      */     //   1942: freturn
/*      */     //   1943: astore 42
/*      */     //   1945: invokestatic 438	com/bulletphysics/BulletStats:popProfile	()V
/*      */     //   1948: aload 42
/*      */     //   1950: athrow
/*      */     //   1951: aload 43
/*      */     //   1953: dup
/*      */     //   1954: invokevirtual 237	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*      */     //   1957: dup
/*      */     //   1958: invokevirtual 315	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*      */     //   1961: invokevirtual 411	com/bulletphysics/$Stack:pop$javax$vecmath$Matrix3f	()V
/*      */     //   1964: athrow
/*      */     //
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   24	35	1943	finally
/*      */     //   54	1924	1943	finally
/*      */     //   1943	1945	1943	finally
/*      */     //   5	1951	1951	finally } 
/*  790 */   public float solveGroupCacheFriendlyIterations(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifoldPtr, int manifold_offset, int numManifolds, ObjectArrayList<TypedConstraint> constraints, int constraints_offset, int numConstraints, ContactSolverInfo infoGlobal, IDebugDraw debugDrawer) { BulletStats.pushProfile("solveGroupCacheFriendlyIterations");
/*      */     try {
/*  792 */       int numConstraintPool = this.tmpSolverConstraintPool.size();
/*  793 */       int numFrictionPool = this.tmpSolverFrictionConstraintPool.size();
/*      */ 
/*  798 */       for (int iteration = 0; iteration < infoGlobal.numIterations; iteration++)
/*      */       {
/*  801 */         if (((infoGlobal.solverMode & 0x1) != 0) && 
/*  802 */           ((iteration & 0x7) == 0)) {
/*  803 */           for (int j = 0; j < numConstraintPool; j++) {
/*  804 */             int tmp = this.orderTmpConstraintPool.get(j);
/*  805 */             int swapi = randInt2(j + 1);
/*  806 */             this.orderTmpConstraintPool.set(j, this.orderTmpConstraintPool.get(swapi));
/*  807 */             this.orderTmpConstraintPool.set(swapi, tmp);
/*      */           }
/*      */ 
/*  810 */           for (j = 0; j < numFrictionPool; j++) {
/*  811 */             int tmp = this.orderFrictionConstraintPool.get(j);
/*  812 */             int swapi = randInt2(j + 1);
/*  813 */             this.orderFrictionConstraintPool.set(j, this.orderFrictionConstraintPool.get(swapi));
/*  814 */             this.orderFrictionConstraintPool.set(swapi, tmp);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  819 */         for (int j = 0; j < numConstraints; j++) {
/*  820 */           TypedConstraint constraint = (TypedConstraint)constraints.getQuick(constraints_offset + j);
/*      */ 
/*  823 */           if ((constraint.getRigidBodyA().getIslandTag() >= 0) && (constraint.getRigidBodyA().getCompanionId() >= 0)) {
/*  824 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyA().getCompanionId())).writebackVelocity();
/*      */           }
/*  826 */           if ((constraint.getRigidBodyB().getIslandTag() >= 0) && (constraint.getRigidBodyB().getCompanionId() >= 0)) {
/*  827 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyB().getCompanionId())).writebackVelocity();
/*      */           }
/*      */ 
/*  830 */           constraint.solveConstraint(infoGlobal.timeStep);
/*      */ 
/*  832 */           if ((constraint.getRigidBodyA().getIslandTag() >= 0) && (constraint.getRigidBodyA().getCompanionId() >= 0)) {
/*  833 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyA().getCompanionId())).readVelocity();
/*      */           }
/*  835 */           if ((constraint.getRigidBodyB().getIslandTag() >= 0) && (constraint.getRigidBodyB().getCompanionId() >= 0)) {
/*  836 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyB().getCompanionId())).readVelocity();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  841 */         int numPoolConstraints = this.tmpSolverConstraintPool.size();
/*  842 */         for (j = 0; j < numPoolConstraints; j++) {
/*  843 */           SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(j));
/*  844 */           resolveSingleCollisionCombinedCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdB), solveManifold, infoGlobal);
/*      */         }
/*      */ 
/*  850 */         int numFrictionPoolConstraints = this.tmpSolverFrictionConstraintPool.size();
/*      */ 
/*  852 */         for (j = 0; j < numFrictionPoolConstraints; j++) {
/*  853 */           SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(this.orderFrictionConstraintPool.get(j));
/*      */ 
/*  855 */           float totalImpulse = ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(solveManifold.frictionIndex)).appliedImpulse + ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(solveManifold.frictionIndex)).appliedPushImpulse;
/*      */ 
/*  858 */           resolveSingleFrictionCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdB), solveManifold, infoGlobal, totalImpulse);
/*      */         }
/*      */       }
/*      */       int numPoolConstraints;
/*  865 */       if (infoGlobal.splitImpulse) {
/*  866 */         for (iteration = 0; iteration < infoGlobal.numIterations; iteration++)
/*      */         {
/*  868 */           numPoolConstraints = this.tmpSolverConstraintPool.size();
/*      */ 
/*  870 */           for (int j = 0; j < numPoolConstraints; j++) {
/*  871 */             SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(j));
/*      */ 
/*  873 */             resolveSplitPenetrationImpulseCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdB), solveManifold, infoGlobal);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  881 */       return 0.0F;
/*      */     }
/*      */     finally {
/*  884 */       BulletStats.popProfile();
/*      */     } }
/*      */ 
/*      */   public float solveGroupCacheFriendly(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifoldPtr, int manifold_offset, int numManifolds, ObjectArrayList<TypedConstraint> constraints, int constraints_offset, int numConstraints, ContactSolverInfo infoGlobal, IDebugDraw debugDrawer)
/*      */   {
/*  889 */     solveGroupCacheFriendlySetup(bodies, numBodies, manifoldPtr, manifold_offset, numManifolds, constraints, constraints_offset, numConstraints, infoGlobal, debugDrawer);
/*  890 */     solveGroupCacheFriendlyIterations(bodies, numBodies, manifoldPtr, manifold_offset, numManifolds, constraints, constraints_offset, numConstraints, infoGlobal, debugDrawer);
/*      */ 
/*  892 */     int numPoolConstraints = this.tmpSolverConstraintPool.size();
/*  893 */     for (int j = 0; j < numPoolConstraints; j++)
/*      */     {
/*  895 */       SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(j);
/*  896 */       ManifoldPoint pt = (ManifoldPoint)solveManifold.originalContactPoint;
/*  897 */       assert (pt != null);
/*  898 */       pt.appliedImpulse = solveManifold.appliedImpulse;
/*  899 */       pt.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(solveManifold.frictionIndex)).appliedImpulse;
/*  900 */       pt.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(solveManifold.frictionIndex + 1)).appliedImpulse;
/*      */     }
/*      */ 
/*  905 */     if (infoGlobal.splitImpulse) {
/*  906 */       for (int i = 0; i < this.tmpSolverBodyPool.size(); i++) {
/*  907 */         ((SolverBody)this.tmpSolverBodyPool.getQuick(i)).writebackVelocity(infoGlobal.timeStep);
/*  908 */         this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(i));
/*      */       }
/*      */     }
/*      */     else {
/*  912 */       for (int i = 0; i < this.tmpSolverBodyPool.size(); i++) {
/*  913 */         ((SolverBody)this.tmpSolverBodyPool.getQuick(i)).writebackVelocity();
/*  914 */         this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(i));
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  929 */     this.tmpSolverBodyPool.clear();
/*      */ 
/*  931 */     for (int i = 0; i < this.tmpSolverConstraintPool.size(); i++) {
/*  932 */       this.constraintsPool.release(this.tmpSolverConstraintPool.getQuick(i));
/*      */     }
/*  934 */     this.tmpSolverConstraintPool.clear();
/*      */ 
/*  936 */     for (int i = 0; i < this.tmpSolverFrictionConstraintPool.size(); i++) {
/*  937 */       this.constraintsPool.release(this.tmpSolverFrictionConstraintPool.getQuick(i));
/*      */     }
/*  939 */     this.tmpSolverFrictionConstraintPool.clear();
/*      */ 
/*  941 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public float solveGroup(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifoldPtr, int manifold_offset, int numManifolds, ObjectArrayList<TypedConstraint> constraints, int constraints_offset, int numConstraints, ContactSolverInfo infoGlobal, IDebugDraw debugDrawer, Dispatcher dispatcher)
/*      */   {
/*  949 */     BulletStats.pushProfile("solveGroup");
/*      */     try
/*      */     {
/*  952 */       if ((infoGlobal.solverMode & 0x8) != 0)
/*      */       {
/*  955 */         assert (bodies != null);
/*  956 */         assert (numBodies != 0);
/*  957 */         float value = solveGroupCacheFriendly(bodies, numBodies, manifoldPtr, manifold_offset, numManifolds, constraints, constraints_offset, numConstraints, infoGlobal, debugDrawer);
/*  958 */         return value;
/*      */       }
/*      */ 
/*  961 */       ContactSolverInfo info = new ContactSolverInfo(infoGlobal);
/*      */ 
/*  963 */       int numiter = infoGlobal.numIterations;
/*      */ 
/*  965 */       int totalPoints = 0;
/*      */ 
/*  968 */       for (short j = 0; j < numManifolds; j = (short)(j + 1)) {
/*  969 */         PersistentManifold manifold = (PersistentManifold)manifoldPtr.getQuick(manifold_offset + j);
/*  970 */         prepareConstraints(manifold, info, debugDrawer);
/*      */ 
/*  972 */         for (short p = 0; p < ((PersistentManifold)manifoldPtr.getQuick(manifold_offset + j)).getNumContacts(); p = (short)(p + 1)) {
/*  973 */           this.gOrder[totalPoints].manifoldIndex = j;
/*  974 */           this.gOrder[totalPoints].pointIndex = p;
/*  975 */           totalPoints++;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  982 */       for (int j = 0; j < numConstraints; j++) {
/*  983 */         TypedConstraint constraint = (TypedConstraint)constraints.getQuick(constraints_offset + j);
/*  984 */         constraint.buildJacobian();
/*      */       }
/*      */       int j;
/*  991 */       for (int iteration = 0; iteration < numiter; iteration++)
/*      */       {
/*  993 */         if (((infoGlobal.solverMode & 0x1) != 0) && 
/*  994 */           ((iteration & 0x7) == 0)) {
/*  995 */           for (int j = 0; j < totalPoints; j++)
/*      */           {
/*  997 */             OrderIndex tmp = this.gOrder[j];
/*  998 */             int swapi = randInt2(j + 1);
/*  999 */             this.gOrder[j] = this.gOrder[swapi];
/* 1000 */             this.gOrder[swapi] = tmp;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1005 */         for (j = 0; j < numConstraints; j++) {
/* 1006 */           TypedConstraint constraint = (TypedConstraint)constraints.getQuick(constraints_offset + j);
/* 1007 */           constraint.solveConstraint(info.timeStep);
/*      */         }
/*      */ 
/* 1010 */         for (j = 0; j < totalPoints; j++) {
/* 1011 */           PersistentManifold manifold = (PersistentManifold)manifoldPtr.getQuick(manifold_offset + this.gOrder[j].manifoldIndex);
/* 1012 */           solve((RigidBody)manifold.getBody0(), (RigidBody)manifold.getBody1(), manifold.getContactPoint(this.gOrder[j].pointIndex), info, iteration, debugDrawer);
/*      */         }
/*      */ 
/* 1016 */         for (j = 0; j < totalPoints; j++) {
/* 1017 */           PersistentManifold manifold = (PersistentManifold)manifoldPtr.getQuick(manifold_offset + this.gOrder[j].manifoldIndex);
/* 1018 */           solveFriction((RigidBody)manifold.getBody0(), (RigidBody)manifold.getBody1(), manifold.getContactPoint(this.gOrder[j].pointIndex), info, iteration, debugDrawer);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1025 */       return 0.0F;
/*      */     }
/*      */     finally {
/* 1028 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void prepareConstraints(PersistentManifold arg1, ContactSolverInfo arg2, IDebugDraw arg3) {
/* 1033 */     .Stack localStack = .Stack.get();
/*      */     try
/*      */     {
/*      */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*      */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Matrix3f(); RigidBody body0 = (RigidBody)manifoldPtr.getBody0();
/* 1034 */       RigidBody body1 = (RigidBody)manifoldPtr.getBody1();
/*      */ 
/* 1041 */       int numpoints = manifoldPtr.getNumContacts();
/*      */ 
/* 1043 */       BulletStats.gTotalContactPoints += numpoints;
/*      */ 
/* 1045 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 1046 */       Matrix3f tmpMat3 = localStack.get$javax$vecmath$Matrix3f();
/*      */ 
/* 1048 */       Vector3f pos1 = localStack.get$javax$vecmath$Vector3f();
/* 1049 */       Vector3f pos2 = localStack.get$javax$vecmath$Vector3f();
/* 1050 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 1051 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 1052 */       Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
/* 1053 */       Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
/* 1054 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 1055 */       Vector3f totalImpulse = localStack.get$javax$vecmath$Vector3f();
/* 1056 */       Vector3f torqueAxis0 = localStack.get$javax$vecmath$Vector3f();
/* 1057 */       Vector3f torqueAxis1 = localStack.get$javax$vecmath$Vector3f();
/* 1058 */       Vector3f ftorqueAxis0 = localStack.get$javax$vecmath$Vector3f();
/* 1059 */       Vector3f ftorqueAxis1 = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/* 1061 */       for (int i = 0; i < numpoints; i++) {
/* 1062 */         ManifoldPoint cp = manifoldPtr.getContactPoint(i);
/* 1063 */         if (cp.getDistance() <= 0.0F) {
/* 1064 */           cp.getPositionWorldOnA(pos1);
/* 1065 */           cp.getPositionWorldOnB(pos2);
/*      */ 
/* 1067 */           rel_pos1.sub(pos1, body0.getCenterOfMassPosition(tmpVec));
/* 1068 */           rel_pos2.sub(pos2, body1.getCenterOfMassPosition(tmpVec));
/*      */ 
/* 1071 */           Matrix3f mat1 = body0.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 1072 */           mat1.transpose();
/*      */ 
/* 1074 */           Matrix3f mat2 = body1.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 1075 */           mat2.transpose();
/*      */ 
/* 1077 */           JacobianEntry jac = (JacobianEntry)this.jacobiansPool.get();
/* 1078 */           jac.init(mat1, mat2, rel_pos1, rel_pos2, cp.normalWorldOnB, body0.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body0.getInvMass(), body1.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body1.getInvMass());
/*      */ 
/* 1083 */           float jacDiagAB = jac.getDiagonal();
/* 1084 */           this.jacobiansPool.release(jac);
/*      */ 
/* 1086 */           ConstraintPersistentData cpd = (ConstraintPersistentData)cp.userPersistentData;
/* 1087 */           if (cpd != null)
/*      */           {
/* 1089 */             cpd.persistentLifeTime += 1;
/* 1090 */             if (cpd.persistentLifeTime != cp.getLifeTime())
/*      */             {
/* 1093 */               cpd.reset();
/* 1094 */               cpd.persistentLifeTime = cp.getLifeTime();
/*      */             }
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 1105 */             cpd = new ConstraintPersistentData();
/*      */ 
/* 1108 */             this.totalCpd += 1;
/*      */ 
/* 1110 */             cp.userPersistentData = cpd;
/* 1111 */             cpd.persistentLifeTime = cp.getLifeTime();
/*      */           }
/*      */ 
/* 1114 */           assert (cpd != null);
/*      */ 
/* 1116 */           cpd.jacDiagABInv = (1.0F / jacDiagAB);
/*      */ 
/* 1121 */           cpd.frictionSolverFunc = this.frictionDispatch[body0.frictionSolverType][body1.frictionSolverType];
/* 1122 */           cpd.contactSolverFunc = this.contactDispatch[body0.contactSolverType][body1.contactSolverType];
/*      */ 
/* 1124 */           body0.getVelocityInLocalPoint(rel_pos1, vel1);
/* 1125 */           body1.getVelocityInLocalPoint(rel_pos2, vel2);
/* 1126 */           vel.sub(vel1, vel2);
/*      */ 
/* 1129 */           float rel_vel = cp.normalWorldOnB.dot(vel);
/*      */ 
/* 1131 */           float combinedRestitution = cp.combinedRestitution;
/*      */ 
/* 1133 */           cpd.penetration = cp.getDistance();
/* 1134 */           cpd.friction = cp.combinedFriction;
/* 1135 */           cpd.restitution = restitutionCurve(rel_vel, combinedRestitution);
/* 1136 */           if (cpd.restitution <= 0.0F) {
/* 1137 */             cpd.restitution = 0.0F;
/*      */           }
/*      */ 
/* 1143 */           float penVel = -cpd.penetration / info.timeStep;
/*      */ 
/* 1145 */           if (cpd.restitution > penVel) {
/* 1146 */             cpd.penetration = 0.0F;
/*      */           }
/*      */ 
/* 1149 */           float relaxation = info.damping;
/* 1150 */           if ((info.solverMode & 0x4) != 0) {
/* 1151 */             cpd.appliedImpulse *= relaxation;
/*      */           }
/*      */           else {
/* 1154 */             cpd.appliedImpulse = 0.0F;
/*      */           }
/*      */ 
/* 1158 */           cpd.prevAppliedImpulse = cpd.appliedImpulse;
/*      */ 
/* 1161 */           TransformUtil.planeSpace1(cp.normalWorldOnB, cpd.frictionWorldTangential0, cpd.frictionWorldTangential1);
/*      */ 
/* 1165 */           cpd.accumulatedTangentImpulse0 = 0.0F;
/* 1166 */           cpd.accumulatedTangentImpulse1 = 0.0F;
/*      */ 
/* 1168 */           float denom0 = body0.computeImpulseDenominator(pos1, cpd.frictionWorldTangential0);
/* 1169 */           float denom1 = body1.computeImpulseDenominator(pos2, cpd.frictionWorldTangential0);
/* 1170 */           float denom = relaxation / (denom0 + denom1);
/* 1171 */           cpd.jacDiagABInvTangent0 = denom;
/*      */ 
/* 1173 */           denom0 = body0.computeImpulseDenominator(pos1, cpd.frictionWorldTangential1);
/* 1174 */           denom1 = body1.computeImpulseDenominator(pos2, cpd.frictionWorldTangential1);
/* 1175 */           denom = relaxation / (denom0 + denom1);
/* 1176 */           cpd.jacDiagABInvTangent1 = denom;
/*      */ 
/* 1184 */           totalImpulse.scale(cpd.appliedImpulse, cp.normalWorldOnB);
/*      */ 
/* 1188 */           torqueAxis0.cross(rel_pos1, cp.normalWorldOnB);
/*      */ 
/* 1190 */           cpd.angularComponentA.set(torqueAxis0);
/* 1191 */           body0.getInvInertiaTensorWorld(tmpMat3).transform(cpd.angularComponentA);
/*      */ 
/* 1193 */           torqueAxis1.cross(rel_pos2, cp.normalWorldOnB);
/*      */ 
/* 1195 */           cpd.angularComponentB.set(torqueAxis1);
/* 1196 */           body1.getInvInertiaTensorWorld(tmpMat3).transform(cpd.angularComponentB);
/*      */ 
/* 1199 */           ftorqueAxis0.cross(rel_pos1, cpd.frictionWorldTangential0);
/*      */ 
/* 1201 */           cpd.frictionAngularComponent0A.set(ftorqueAxis0);
/* 1202 */           body0.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent0A);
/*      */ 
/* 1205 */           ftorqueAxis1.cross(rel_pos1, cpd.frictionWorldTangential1);
/*      */ 
/* 1207 */           cpd.frictionAngularComponent1A.set(ftorqueAxis1);
/* 1208 */           body0.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent1A);
/*      */ 
/* 1211 */           ftorqueAxis0.cross(rel_pos2, cpd.frictionWorldTangential0);
/*      */ 
/* 1213 */           cpd.frictionAngularComponent0B.set(ftorqueAxis0);
/* 1214 */           body1.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent0B);
/*      */ 
/* 1217 */           ftorqueAxis1.cross(rel_pos2, cpd.frictionWorldTangential1);
/*      */ 
/* 1219 */           cpd.frictionAngularComponent1B.set(ftorqueAxis1);
/* 1220 */           body1.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent1B);
/*      */ 
/* 1226 */           body0.applyImpulse(totalImpulse, rel_pos1);
/*      */ 
/* 1228 */           tmpVec.negate(totalImpulse);
/* 1229 */           body1.applyImpulse(tmpVec, rel_pos2);
/*      */         }
/*      */       }
/*      */       return;
/*      */     }
/*      */     finally
/*      */     {
/* 1234 */       .Stack tmp1077_1075 = localStack; tmp1077_1075.pop$com$bulletphysics$linearmath$Transform();
/*      */       .Stack tmp1081_1077 = tmp1077_1075; tmp1081_1077.pop$javax$vecmath$Vector3f(); tmp1081_1077.pop$javax$vecmath$Matrix3f(); } throw finally;
/*      */   }
/*      */   public float solveCombinedContactFriction(RigidBody body0, RigidBody body1, ManifoldPoint cp, ContactSolverInfo info, int iter, IDebugDraw debugDrawer) {
/* 1237 */     float maxImpulse = 0.0F;
/*      */ 
/* 1240 */     if (cp.getDistance() <= 0.0F)
/*      */     {
/* 1243 */       float impulse = ContactConstraint.resolveSingleCollisionCombined(body0, body1, cp, info);
/*      */ 
/* 1245 */       if (maxImpulse < impulse) {
/* 1246 */         maxImpulse = impulse;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1251 */     return maxImpulse;
/*      */   }
/*      */ 
/*      */   protected float solve(RigidBody body0, RigidBody body1, ManifoldPoint cp, ContactSolverInfo info, int iter, IDebugDraw debugDrawer) {
/* 1255 */     float maxImpulse = 0.0F;
/*      */ 
/* 1258 */     if (cp.getDistance() <= 0.0F)
/*      */     {
/* 1260 */       ConstraintPersistentData cpd = (ConstraintPersistentData)cp.userPersistentData;
/* 1261 */       float impulse = cpd.contactSolverFunc.resolveContact(body0, body1, cp, info);
/*      */ 
/* 1263 */       if (maxImpulse < impulse) {
/* 1264 */         maxImpulse = impulse;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1270 */     return maxImpulse;
/*      */   }
/*      */ 
/*      */   protected float solveFriction(RigidBody body0, RigidBody body1, ManifoldPoint cp, ContactSolverInfo info, int iter, IDebugDraw debugDrawer)
/*      */   {
/* 1275 */     if (cp.getDistance() <= 0.0F) {
/* 1276 */       ConstraintPersistentData cpd = (ConstraintPersistentData)cp.userPersistentData;
/* 1277 */       cpd.frictionSolverFunc.resolveContact(body0, body1, cp, info);
/*      */     }
/*      */ 
/* 1280 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public void reset()
/*      */   {
/* 1285 */     this.btSeed2 = 0L;
/*      */   }
/*      */ 
/*      */   public void setContactSolverFunc(ContactSolverFunc func, int type0, int type1)
/*      */   {
/* 1293 */     this.contactDispatch[type0][type1] = func;
/*      */   }
/*      */ 
/*      */   public void setFrictionSolverFunc(ContactSolverFunc func, int type0, int type1)
/*      */   {
/* 1301 */     this.frictionDispatch[type0][type1] = func;
/*      */   }
/*      */ 
/*      */   public void setRandSeed(long seed) {
/* 1305 */     this.btSeed2 = seed;
/*      */   }
/*      */ 
/*      */   public long getRandSeed() {
/* 1309 */     return this.btSeed2;
/*      */   }
/*      */ 
/*      */   private static class OrderIndex
/*      */   {
/*      */     public int manifoldIndex;
/*      */     public int pointIndex;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver
 * JD-Core Version:    0.6.2
 */