/*    1:     */package com.bulletphysics.dynamics.constraintsolver;
/*    2:     */
/*    3:     */import com.bulletphysics..Stack;
/*    4:     */import com.bulletphysics.BulletGlobals;
/*    5:     */import com.bulletphysics.BulletStats;
/*    6:     */import com.bulletphysics.ContactDestroyedCallback;
/*    7:     */import com.bulletphysics.collision.broadphase.Dispatcher;
/*    8:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*    9:     */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*   10:     */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   11:     */import com.bulletphysics.dynamics.RigidBody;
/*   12:     */import com.bulletphysics.linearmath.IDebugDraw;
/*   13:     */import com.bulletphysics.linearmath.Transform;
/*   14:     */import com.bulletphysics.linearmath.TransformUtil;
/*   15:     */import com.bulletphysics.util.IntArrayList;
/*   16:     */import com.bulletphysics.util.ObjectArrayList;
/*   17:     */import com.bulletphysics.util.ObjectPool;
/*   18:     */import javax.vecmath.Matrix3f;
/*   19:     */import javax.vecmath.Vector3f;
/*   20:     */
/*   56:     */public class SequentialImpulseConstraintSolver
/*   57:     */  extends ConstraintSolver
/*   58:     */{
/*   59:  59 */  private static final int MAX_CONTACT_SOLVER_TYPES = ContactConstraintEnum.MAX_CONTACT_SOLVER_TYPES.ordinal();
/*   60:     */  
/*   61:     */  private static final int SEQUENTIAL_IMPULSE_MAX_SOLVER_POINTS = 16384;
/*   62:  62 */  private OrderIndex[] gOrder = new OrderIndex[16384];
/*   63:     */  
/*   64:  64 */  private int totalCpd = 0;
/*   65:     */  private final ObjectPool<SolverBody> bodiesPool;
/*   66:     */  
/*   67:  67 */  public SequentialImpulseConstraintSolver() { for (int i = 0; i < this.gOrder.length; i++) {
/*   68:  68 */      this.gOrder[i] = new OrderIndex(null);
/*   69:     */    }
/*   70:     */    
/*   74:  74 */    this.bodiesPool = ObjectPool.get(SolverBody.class);
/*   75:  75 */    this.constraintsPool = ObjectPool.get(SolverConstraint.class);
/*   76:  76 */    this.jacobiansPool = ObjectPool.get(JacobianEntry.class);
/*   77:     */    
/*   78:  78 */    this.tmpSolverBodyPool = new ObjectArrayList();
/*   79:  79 */    this.tmpSolverConstraintPool = new ObjectArrayList();
/*   80:  80 */    this.tmpSolverFrictionConstraintPool = new ObjectArrayList();
/*   81:  81 */    this.orderTmpConstraintPool = new IntArrayList();
/*   82:  82 */    this.orderFrictionConstraintPool = new IntArrayList();
/*   83:     */    
/*   84:  84 */    this.contactDispatch = new ContactSolverFunc[MAX_CONTACT_SOLVER_TYPES][MAX_CONTACT_SOLVER_TYPES];
/*   85:  85 */    this.frictionDispatch = new ContactSolverFunc[MAX_CONTACT_SOLVER_TYPES][MAX_CONTACT_SOLVER_TYPES];
/*   86:     */    
/*   88:  88 */    this.btSeed2 = 0L;
/*   89:     */    
/*   91:  91 */    BulletGlobals.setContactDestroyedCallback(new ContactDestroyedCallback() {
/*   92:     */      public boolean contactDestroyed(Object userPersistentData) {
/*   93:  93 */        assert (userPersistentData != null);
/*   94:  94 */        ConstraintPersistentData cpd = (ConstraintPersistentData)userPersistentData;
/*   95:     */        
/*   96:  96 */        SequentialImpulseConstraintSolver.access$110(SequentialImpulseConstraintSolver.this);
/*   97:     */        
/*   98:  98 */        return true;
/*   99:     */      }
/*  100:     */    });
/*  101:     */    
/*  104: 104 */    for (int i = 0; i < MAX_CONTACT_SOLVER_TYPES; i++) {
/*  105: 105 */      for (int j = 0; j < MAX_CONTACT_SOLVER_TYPES; j++) {
/*  106: 106 */        this.contactDispatch[i][j] = ContactConstraint.resolveSingleCollision;
/*  107: 107 */        this.frictionDispatch[i][j] = ContactConstraint.resolveSingleFriction;
/*  108:     */      }
/*  109:     */    }
/*  110:     */  }
/*  111:     */  
/*  112:     */  public long rand2() {
/*  113: 113 */    this.btSeed2 = (1664525L * this.btSeed2 + 1013904223L & 0xFFFFFFFF);
/*  114: 114 */    return this.btSeed2;
/*  115:     */  }
/*  116:     */  
/*  117:     */  private final ObjectPool<SolverConstraint> constraintsPool;
/*  118:     */  public int randInt2(int n)
/*  119:     */  {
/*  120: 120 */    long un = n;
/*  121: 121 */    long r = rand2();
/*  122:     */    
/*  125: 125 */    if (un <= 65536L) {
/*  126: 126 */      r ^= r >>> 16;
/*  127: 127 */      if (un <= 256L) {
/*  128: 128 */        r ^= r >>> 8;
/*  129: 129 */        if (un <= 16L) {
/*  130: 130 */          r ^= r >>> 4;
/*  131: 131 */          if (un <= 4L) {
/*  132: 132 */            r ^= r >>> 2;
/*  133: 133 */            if (un <= 2L) {
/*  134: 134 */              r ^= r >>> 1;
/*  135:     */            }
/*  136:     */          }
/*  137:     */        }
/*  138:     */      }
/*  139:     */    }
/*  140:     */    
/*  142: 142 */    return (int)Math.abs(r % un);
/*  143:     */  }
/*  144:     */  
/*  145:     */  private void initSolverBody(SolverBody arg1, CollisionObject arg2) {
/*  146: 146 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();RigidBody rb = RigidBody.upcast(collisionObject);
/*  147: 147 */      if (rb != null) {
/*  148: 148 */        rb.getAngularVelocity(solverBody.angularVelocity);
/*  149: 149 */        solverBody.centerOfMassPosition.set(collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/*  150: 150 */        solverBody.friction = collisionObject.getFriction();
/*  151: 151 */        solverBody.invMass = rb.getInvMass();
/*  152: 152 */        rb.getLinearVelocity(solverBody.linearVelocity);
/*  153: 153 */        solverBody.originalBody = rb;
/*  154: 154 */        solverBody.angularFactor = rb.getAngularFactor();
/*  155:     */      }
/*  156:     */      else {
/*  157: 157 */        solverBody.angularVelocity.set(0.0F, 0.0F, 0.0F);
/*  158: 158 */        solverBody.centerOfMassPosition.set(collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/*  159: 159 */        solverBody.friction = collisionObject.getFriction();
/*  160: 160 */        solverBody.invMass = 0.0F;
/*  161: 161 */        solverBody.linearVelocity.set(0.0F, 0.0F, 0.0F);
/*  162: 162 */        solverBody.originalBody = null;
/*  163: 163 */        solverBody.angularFactor = 1.0F;
/*  164:     */      }
/*  165:     */      
/*  166: 166 */      solverBody.pushVelocity.set(0.0F, 0.0F, 0.0F);
/*  167: 167 */      solverBody.turnVelocity.set(0.0F, 0.0F, 0.0F);
/*  168: 168 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/*  169:     */    } }
/*  170:     */  
/*  171: 171 */  private float restitutionCurve(float rel_vel, float restitution) { float rest = restitution * -rel_vel;
/*  172: 172 */    return rest;
/*  173:     */  }
/*  174:     */  
/*  176:     */  private final ObjectPool<JacobianEntry> jacobiansPool;
/*  177:     */  private final ObjectArrayList<SolverBody> tmpSolverBodyPool;
/*  178:     */  private final ObjectArrayList<SolverConstraint> tmpSolverConstraintPool;
/*  179:     */  private void resolveSplitPenetrationImpulseCacheFriendly(SolverBody arg1, SolverBody arg2, SolverConstraint arg3, ContactSolverInfo arg4)
/*  180:     */  {
/*  181: 181 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (contactConstraint.penetration < solverInfo.splitImpulsePenetrationThreshold) {
/*  182: 182 */        BulletStats.gNumSplitImpulseRecoveries += 1;
/*  183:     */        
/*  192: 192 */        float vel1Dotn = contactConstraint.contactNormal.dot(body1.pushVelocity) + contactConstraint.relpos1CrossNormal.dot(body1.turnVelocity);
/*  193: 193 */        float vel2Dotn = contactConstraint.contactNormal.dot(body2.pushVelocity) + contactConstraint.relpos2CrossNormal.dot(body2.turnVelocity);
/*  194:     */        
/*  195: 195 */        float rel_vel = vel1Dotn - vel2Dotn;
/*  196:     */        
/*  197: 197 */        float positionalError = -contactConstraint.penetration * solverInfo.erp2 / solverInfo.timeStep;
/*  198:     */        
/*  200: 200 */        float velocityError = contactConstraint.restitution - rel_vel;
/*  201:     */        
/*  202: 202 */        float penetrationImpulse = positionalError * contactConstraint.jacDiagABInv;
/*  203: 203 */        float velocityImpulse = velocityError * contactConstraint.jacDiagABInv;
/*  204: 204 */        float normalImpulse = penetrationImpulse + velocityImpulse;
/*  205:     */        
/*  207: 207 */        float oldNormalImpulse = contactConstraint.appliedPushImpulse;
/*  208: 208 */        float sum = oldNormalImpulse + normalImpulse;
/*  209: 209 */        contactConstraint.appliedPushImpulse = (0.0F > sum ? 0.0F : sum);
/*  210:     */        
/*  211: 211 */        normalImpulse = contactConstraint.appliedPushImpulse - oldNormalImpulse;
/*  212:     */        
/*  213: 213 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  214:     */        
/*  215: 215 */        tmp.scale(body1.invMass, contactConstraint.contactNormal);
/*  216: 216 */        body1.internalApplyPushImpulse(tmp, contactConstraint.angularComponentA, normalImpulse);
/*  217:     */        
/*  218: 218 */        tmp.scale(body2.invMass, contactConstraint.contactNormal);
/*  219: 219 */        body2.internalApplyPushImpulse(tmp, contactConstraint.angularComponentB, -normalImpulse);
/*  220:     */      }
/*  221: 221 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  222:     */    }
/*  223:     */  }
/*  224:     */  
/*  227:     */  private final ObjectArrayList<SolverConstraint> tmpSolverFrictionConstraintPool;
/*  228:     */  
/*  230:     */  private final IntArrayList orderTmpConstraintPool;
/*  231:     */  
/*  233:     */  private final IntArrayList orderFrictionConstraintPool;
/*  234:     */  
/*  235:     */  protected final ContactSolverFunc[][] contactDispatch;
/*  236:     */  
/*  237:     */  protected final ContactSolverFunc[][] frictionDispatch;
/*  238:     */  
/*  239:     */  protected long btSeed2;
/*  240:     */  
/*  241:     */  private float resolveSingleCollisionCombinedCacheFriendly(SolverBody arg1, SolverBody arg2, SolverConstraint arg3, ContactSolverInfo arg4)
/*  242:     */  {
/*  243: 243 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();float vel1Dotn = contactConstraint.contactNormal.dot(body1.linearVelocity) + contactConstraint.relpos1CrossNormal.dot(body1.angularVelocity);
/*  244: 244 */      float vel2Dotn = contactConstraint.contactNormal.dot(body2.linearVelocity) + contactConstraint.relpos2CrossNormal.dot(body2.angularVelocity);
/*  245:     */      
/*  246: 246 */      float rel_vel = vel1Dotn - vel2Dotn;
/*  247:     */      
/*  248: 248 */      float positionalError = 0.0F;
/*  249: 249 */      if ((!solverInfo.splitImpulse) || (contactConstraint.penetration > solverInfo.splitImpulsePenetrationThreshold)) {
/*  250: 250 */        positionalError = -contactConstraint.penetration * solverInfo.erp / solverInfo.timeStep;
/*  251:     */      }
/*  252:     */      
/*  253: 253 */      float velocityError = contactConstraint.restitution - rel_vel;
/*  254:     */      
/*  255: 255 */      float penetrationImpulse = positionalError * contactConstraint.jacDiagABInv;
/*  256: 256 */      float velocityImpulse = velocityError * contactConstraint.jacDiagABInv;
/*  257: 257 */      float normalImpulse = penetrationImpulse + velocityImpulse;
/*  258:     */      
/*  261: 261 */      float oldNormalImpulse = contactConstraint.appliedImpulse;
/*  262: 262 */      float sum = oldNormalImpulse + normalImpulse;
/*  263: 263 */      contactConstraint.appliedImpulse = (0.0F > sum ? 0.0F : sum);
/*  264:     */      
/*  265: 265 */      normalImpulse = contactConstraint.appliedImpulse - oldNormalImpulse;
/*  266:     */      
/*  267: 267 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  268:     */      
/*  269: 269 */      tmp.scale(body1.invMass, contactConstraint.contactNormal);
/*  270: 270 */      body1.internalApplyImpulse(tmp, contactConstraint.angularComponentA, normalImpulse);
/*  271:     */      
/*  272: 272 */      tmp.scale(body2.invMass, contactConstraint.contactNormal);
/*  273: 273 */      body2.internalApplyImpulse(tmp, contactConstraint.angularComponentB, -normalImpulse);
/*  274:     */      
/*  276: 276 */      return normalImpulse; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  277:     */    }
/*  278:     */  }
/*  279:     */  
/*  283:     */  private float resolveSingleFrictionCacheFriendly(SolverBody arg1, SolverBody arg2, SolverConstraint arg3, ContactSolverInfo arg4, float arg5)
/*  284:     */  {
/*  285: 285 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();float combinedFriction = contactConstraint.friction;
/*  286:     */      
/*  287: 287 */      float limit = appliedNormalImpulse * combinedFriction;
/*  288:     */      
/*  289: 289 */      if (appliedNormalImpulse > 0.0F)
/*  290:     */      {
/*  296: 296 */        float vel1Dotn = contactConstraint.contactNormal.dot(body1.linearVelocity) + contactConstraint.relpos1CrossNormal.dot(body1.angularVelocity);
/*  297: 297 */        float vel2Dotn = contactConstraint.contactNormal.dot(body2.linearVelocity) + contactConstraint.relpos2CrossNormal.dot(body2.angularVelocity);
/*  298: 298 */        float rel_vel = vel1Dotn - vel2Dotn;
/*  299:     */        
/*  301: 301 */        float j1 = -rel_vel * contactConstraint.jacDiagABInv;
/*  302:     */        
/*  304: 304 */        float oldTangentImpulse = contactConstraint.appliedImpulse;
/*  305: 305 */        contactConstraint.appliedImpulse = (oldTangentImpulse + j1);
/*  306:     */        
/*  307: 307 */        if (limit < contactConstraint.appliedImpulse) {
/*  308: 308 */          contactConstraint.appliedImpulse = limit;
/*  310:     */        }
/*  311: 311 */        else if (contactConstraint.appliedImpulse < -limit) {
/*  312: 312 */          contactConstraint.appliedImpulse = (-limit);
/*  313:     */        }
/*  314:     */        
/*  315: 315 */        j1 = contactConstraint.appliedImpulse - oldTangentImpulse;
/*  316:     */        
/*  331: 331 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  332:     */        
/*  333: 333 */        tmp.scale(body1.invMass, contactConstraint.contactNormal);
/*  334: 334 */        body1.internalApplyImpulse(tmp, contactConstraint.angularComponentA, j1);
/*  335:     */        
/*  336: 336 */        tmp.scale(body2.invMass, contactConstraint.contactNormal);
/*  337: 337 */        body2.internalApplyImpulse(tmp, contactConstraint.angularComponentB, -j1);
/*  338:     */      }
/*  339: 339 */      return 0.0F; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  340:     */    }
/*  341:     */  }
/*  342:     */  
/*  343:     */  protected void addFrictionConstraint(Vector3f arg1, int arg2, int arg3, int arg4, ManifoldPoint arg5, Vector3f arg6, Vector3f arg7, CollisionObject arg8, CollisionObject arg9, float arg10) {
/*  344: 344 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();RigidBody body0 = RigidBody.upcast(colObj0);
/*  345: 345 */      RigidBody body1 = RigidBody.upcast(colObj1);
/*  346:     */      
/*  347: 347 */      SolverConstraint solverConstraint = (SolverConstraint)this.constraintsPool.get();
/*  348: 348 */      this.tmpSolverFrictionConstraintPool.add(solverConstraint);
/*  349:     */      
/*  350: 350 */      solverConstraint.contactNormal.set(normalAxis);
/*  351:     */      
/*  352: 352 */      solverConstraint.solverBodyIdA = solverBodyIdA;
/*  353: 353 */      solverConstraint.solverBodyIdB = solverBodyIdB;
/*  354: 354 */      solverConstraint.constraintType = SolverConstraintType.SOLVER_FRICTION_1D;
/*  355: 355 */      solverConstraint.frictionIndex = frictionIndex;
/*  356:     */      
/*  357: 357 */      solverConstraint.friction = cp.combinedFriction;
/*  358: 358 */      solverConstraint.originalContactPoint = null;
/*  359:     */      
/*  360: 360 */      solverConstraint.appliedImpulse = 0.0F;
/*  361: 361 */      solverConstraint.appliedPushImpulse = 0.0F;
/*  362: 362 */      solverConstraint.penetration = 0.0F;
/*  363:     */      
/*  364: 364 */      Vector3f ftorqueAxis1 = localStack.get$javax$vecmath$Vector3f();
/*  365: 365 */      Matrix3f tmpMat = localStack.get$javax$vecmath$Matrix3f();
/*  366:     */      
/*  368: 368 */      ftorqueAxis1.cross(rel_pos1, solverConstraint.contactNormal);
/*  369: 369 */      solverConstraint.relpos1CrossNormal.set(ftorqueAxis1);
/*  370: 370 */      if (body0 != null) {
/*  371: 371 */        solverConstraint.angularComponentA.set(ftorqueAxis1);
/*  372: 372 */        body0.getInvInertiaTensorWorld(tmpMat).transform(solverConstraint.angularComponentA);
/*  373:     */      }
/*  374:     */      else {
/*  375: 375 */        solverConstraint.angularComponentA.set(0.0F, 0.0F, 0.0F);
/*  376:     */      }
/*  377:     */      
/*  379: 379 */      ftorqueAxis1.cross(rel_pos2, solverConstraint.contactNormal);
/*  380: 380 */      solverConstraint.relpos2CrossNormal.set(ftorqueAxis1);
/*  381: 381 */      if (body1 != null) {
/*  382: 382 */        solverConstraint.angularComponentB.set(ftorqueAxis1);
/*  383: 383 */        body1.getInvInertiaTensorWorld(tmpMat).transform(solverConstraint.angularComponentB);
/*  384:     */      }
/*  385:     */      else {
/*  386: 386 */        solverConstraint.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*  387:     */      }
/*  388:     */      
/*  394: 394 */      Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/*  395: 395 */      float denom0 = 0.0F;
/*  396: 396 */      float denom1 = 0.0F;
/*  397: 397 */      if (body0 != null) {
/*  398: 398 */        vec.cross(solverConstraint.angularComponentA, rel_pos1);
/*  399: 399 */        denom0 = body0.getInvMass() + normalAxis.dot(vec);
/*  400:     */      }
/*  401: 401 */      if (body1 != null) {
/*  402: 402 */        vec.cross(solverConstraint.angularComponentB, rel_pos2);
/*  403: 403 */        denom1 = body1.getInvMass() + normalAxis.dot(vec);
/*  404:     */      }
/*  405:     */      
/*  407: 407 */      float denom = relaxation / (denom0 + denom1);
/*  408: 408 */      solverConstraint.jacDiagABInv = denom;
/*  409: 409 */    } finally { .Stack tmp370_368 = localStack;tmp370_368.pop$javax$vecmath$Vector3f();tmp370_368.pop$javax$vecmath$Matrix3f();
/*  410:     */    }
/*  411:     */  }
/*  412:     */  
/*  413:     */  /* Error */
/*  414:     */  public float solveGroupCacheFriendlySetup(ObjectArrayList<CollisionObject> arg1, int arg2, ObjectArrayList<PersistentManifold> arg3, int arg4, int arg5, ObjectArrayList<TypedConstraint> arg6, int arg7, int arg8, ContactSolverInfo arg9, IDebugDraw arg10)
/*  415:     */  {
/*  416:     */    // Byte code:
/*  417:     */    //   0: invokestatic 154	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/*  418:     */    //   3: astore 43
/*  419:     */    //   5: aload 43
/*  420:     */    //   7: dup
/*  421:     */    //   8: invokevirtual 157	com/bulletphysics/$Stack:push$com$bulletphysics$linearmath$Transform	()V
/*  422:     */    //   11: dup
/*  423:     */    //   12: invokevirtual 254	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/*  424:     */    //   15: invokevirtual 358	com/bulletphysics/$Stack:push$javax$vecmath$Matrix3f	()V
/*  425:     */    //   18: ldc_w 431
/*  426:     */    //   21: invokestatic 435	com/bulletphysics/BulletStats:pushProfile	(Ljava/lang/String;)V
/*  427:     */    //   24: iload 8
/*  428:     */    //   26: iload 5
/*  429:     */    //   28: iadd
/*  430:     */    //   29: ifne +25 -> 54
/*  431:     */    //   32: fconst_0
/*  432:     */    //   33: fstore 11
/*  433:     */    //   35: invokestatic 438	com/bulletphysics/BulletStats:popProfile	()V
/*  434:     */    //   38: fload 11
/*  435:     */    //   40: aload 43
/*  436:     */    //   42: dup
/*  437:     */    //   43: invokevirtual 237	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*  438:     */    //   46: dup
/*  439:     */    //   47: invokevirtual 315	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*  440:     */    //   50: invokevirtual 411	com/bulletphysics/$Stack:pop$javax$vecmath$Matrix3f	()V
/*  441:     */    //   53: freturn
/*  442:     */    //   54: aconst_null
/*  443:     */    //   55: astore 11
/*  444:     */    //   57: aconst_null
/*  445:     */    //   58: astore 12
/*  446:     */    //   60: aconst_null
/*  447:     */    //   61: astore 13
/*  448:     */    //   63: aload 43
/*  449:     */    //   65: invokevirtual 178	com/bulletphysics/$Stack:get$com$bulletphysics$linearmath$Transform	()Lcom/bulletphysics/linearmath/Transform;
/*  450:     */    //   68: astore 14
/*  451:     */    //   70: aload 43
/*  452:     */    //   72: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  453:     */    //   75: astore 16
/*  454:     */    //   77: aload 43
/*  455:     */    //   79: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  456:     */    //   82: astore 17
/*  457:     */    //   84: aload 43
/*  458:     */    //   86: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  459:     */    //   89: astore 18
/*  460:     */    //   91: aload 43
/*  461:     */    //   93: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  462:     */    //   96: astore 19
/*  463:     */    //   98: aload 43
/*  464:     */    //   100: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  465:     */    //   103: astore 20
/*  466:     */    //   105: aload 43
/*  467:     */    //   107: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  468:     */    //   110: astore 21
/*  469:     */    //   112: aload 43
/*  470:     */    //   114: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  471:     */    //   117: astore 22
/*  472:     */    //   119: aload 43
/*  473:     */    //   121: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  474:     */    //   124: astore 23
/*  475:     */    //   126: aload 43
/*  476:     */    //   128: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  477:     */    //   131: astore 24
/*  478:     */    //   133: aload 43
/*  479:     */    //   135: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  480:     */    //   138: astore 25
/*  481:     */    //   140: aload 43
/*  482:     */    //   142: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  483:     */    //   145: astore 26
/*  484:     */    //   147: aload 43
/*  485:     */    //   149: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  486:     */    //   152: astore 27
/*  487:     */    //   154: aload 43
/*  488:     */    //   156: invokevirtual 395	com/bulletphysics/$Stack:get$javax$vecmath$Matrix3f	()Ljavax/vecmath/Matrix3f;
/*  489:     */    //   159: astore 28
/*  490:     */    //   161: iconst_0
/*  491:     */    //   162: istore 15
/*  492:     */    //   164: iload 15
/*  493:     */    //   166: iload 5
/*  494:     */    //   168: if_icmpge +1625 -> 1793
/*  495:     */    //   171: aload_3
/*  496:     */    //   172: iload 4
/*  497:     */    //   174: iload 15
/*  498:     */    //   176: iadd
/*  499:     */    //   177: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*  500:     */    //   180: checkcast 444	com/bulletphysics/collision/narrowphase/PersistentManifold
/*  501:     */    //   183: astore 11
/*  502:     */    //   185: aload 11
/*  503:     */    //   187: invokevirtual 447	com/bulletphysics/collision/narrowphase/PersistentManifold:getBody0	()Ljava/lang/Object;
/*  504:     */    //   190: checkcast 180	com/bulletphysics/collision/dispatch/CollisionObject
/*  505:     */    //   193: astore 12
/*  506:     */    //   195: aload 11
/*  507:     */    //   197: invokevirtual 450	com/bulletphysics/collision/narrowphase/PersistentManifold:getBody1	()Ljava/lang/Object;
/*  508:     */    //   200: checkcast 180	com/bulletphysics/collision/dispatch/CollisionObject
/*  509:     */    //   203: astore 13
/*  510:     */    //   205: iconst_m1
/*  511:     */    //   206: istore 29
/*  512:     */    //   208: iconst_m1
/*  513:     */    //   209: istore 30
/*  514:     */    //   211: aload 11
/*  515:     */    //   213: invokevirtual 454	com/bulletphysics/collision/narrowphase/PersistentManifold:getNumContacts	()I
/*  516:     */    //   216: ifeq +231 -> 447
/*  517:     */    //   219: aload 12
/*  518:     */    //   221: invokevirtual 457	com/bulletphysics/collision/dispatch/CollisionObject:getIslandTag	()I
/*  519:     */    //   224: iflt +70 -> 294
/*  520:     */    //   227: aload 12
/*  521:     */    //   229: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*  522:     */    //   232: iflt +13 -> 245
/*  523:     */    //   235: aload 12
/*  524:     */    //   237: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*  525:     */    //   240: istore 29
/*  526:     */    //   242: goto +91 -> 333
/*  527:     */    //   245: aload_0
/*  528:     */    //   246: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  529:     */    //   249: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*  530:     */    //   252: istore 29
/*  531:     */    //   254: aload_0
/*  532:     */    //   255: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*  533:     */    //   258: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*  534:     */    //   261: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*  535:     */    //   264: astore 31
/*  536:     */    //   266: aload_0
/*  537:     */    //   267: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  538:     */    //   270: aload 31
/*  539:     */    //   272: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*  540:     */    //   275: pop
/*  541:     */    //   276: aload_0
/*  542:     */    //   277: aload 31
/*  543:     */    //   279: aload 12
/*  544:     */    //   281: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*  545:     */    //   284: aload 12
/*  546:     */    //   286: iload 29
/*  547:     */    //   288: invokevirtual 469	com/bulletphysics/collision/dispatch/CollisionObject:setCompanionId	(I)V
/*  548:     */    //   291: goto +42 -> 333
/*  549:     */    //   294: aload_0
/*  550:     */    //   295: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  551:     */    //   298: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*  552:     */    //   301: istore 29
/*  553:     */    //   303: aload_0
/*  554:     */    //   304: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*  555:     */    //   307: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*  556:     */    //   310: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*  557:     */    //   313: astore 31
/*  558:     */    //   315: aload_0
/*  559:     */    //   316: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  560:     */    //   319: aload 31
/*  561:     */    //   321: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*  562:     */    //   324: pop
/*  563:     */    //   325: aload_0
/*  564:     */    //   326: aload 31
/*  565:     */    //   328: aload 12
/*  566:     */    //   330: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*  567:     */    //   333: aload 13
/*  568:     */    //   335: invokevirtual 457	com/bulletphysics/collision/dispatch/CollisionObject:getIslandTag	()I
/*  569:     */    //   338: iflt +70 -> 408
/*  570:     */    //   341: aload 13
/*  571:     */    //   343: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*  572:     */    //   346: iflt +13 -> 359
/*  573:     */    //   349: aload 13
/*  574:     */    //   351: invokevirtual 460	com/bulletphysics/collision/dispatch/CollisionObject:getCompanionId	()I
/*  575:     */    //   354: istore 30
/*  576:     */    //   356: goto +91 -> 447
/*  577:     */    //   359: aload_0
/*  578:     */    //   360: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  579:     */    //   363: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*  580:     */    //   366: istore 30
/*  581:     */    //   368: aload_0
/*  582:     */    //   369: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*  583:     */    //   372: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*  584:     */    //   375: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*  585:     */    //   378: astore 31
/*  586:     */    //   380: aload_0
/*  587:     */    //   381: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  588:     */    //   384: aload 31
/*  589:     */    //   386: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*  590:     */    //   389: pop
/*  591:     */    //   390: aload_0
/*  592:     */    //   391: aload 31
/*  593:     */    //   393: aload 13
/*  594:     */    //   395: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*  595:     */    //   398: aload 13
/*  596:     */    //   400: iload 30
/*  597:     */    //   402: invokevirtual 469	com/bulletphysics/collision/dispatch/CollisionObject:setCompanionId	(I)V
/*  598:     */    //   405: goto +42 -> 447
/*  599:     */    //   408: aload_0
/*  600:     */    //   409: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  601:     */    //   412: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*  602:     */    //   415: istore 30
/*  603:     */    //   417: aload_0
/*  604:     */    //   418: getfield 61	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:bodiesPool	Lcom/bulletphysics/util/ObjectPool;
/*  605:     */    //   421: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*  606:     */    //   424: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*  607:     */    //   427: astore 31
/*  608:     */    //   429: aload_0
/*  609:     */    //   430: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  610:     */    //   433: aload 31
/*  611:     */    //   435: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*  612:     */    //   438: pop
/*  613:     */    //   439: aload_0
/*  614:     */    //   440: aload 31
/*  615:     */    //   442: aload 13
/*  616:     */    //   444: invokespecial 465	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:initSolverBody	(Lcom/bulletphysics/dynamics/constraintsolver/SolverBody;Lcom/bulletphysics/collision/dispatch/CollisionObject;)V
/*  617:     */    //   447: iconst_0
/*  618:     */    //   448: istore 32
/*  619:     */    //   450: iload 32
/*  620:     */    //   452: aload 11
/*  621:     */    //   454: invokevirtual 454	com/bulletphysics/collision/narrowphase/PersistentManifold:getNumContacts	()I
/*  622:     */    //   457: if_icmpge +1330 -> 1787
/*  623:     */    //   460: aload 11
/*  624:     */    //   462: iload 32
/*  625:     */    //   464: invokevirtual 473	com/bulletphysics/collision/narrowphase/PersistentManifold:getContactPoint	(I)Lcom/bulletphysics/collision/narrowphase/ManifoldPoint;
/*  626:     */    //   467: astore 33
/*  627:     */    //   469: aload 33
/*  628:     */    //   471: invokevirtual 476	com/bulletphysics/collision/narrowphase/ManifoldPoint:getDistance	()F
/*  629:     */    //   474: fconst_0
/*  630:     */    //   475: fcmpg
/*  631:     */    //   476: ifgt +1305 -> 1781
/*  632:     */    //   479: aload 33
/*  633:     */    //   481: aload 18
/*  634:     */    //   483: invokevirtual 479	com/bulletphysics/collision/narrowphase/ManifoldPoint:getPositionWorldOnA	(Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*  635:     */    //   486: pop
/*  636:     */    //   487: aload 33
/*  637:     */    //   489: aload 19
/*  638:     */    //   491: invokevirtual 482	com/bulletphysics/collision/narrowphase/ManifoldPoint:getPositionWorldOnB	(Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*  639:     */    //   494: pop
/*  640:     */    //   495: aload 16
/*  641:     */    //   497: aload 18
/*  642:     */    //   499: aload 12
/*  643:     */    //   501: aload 14
/*  644:     */    //   503: invokevirtual 184	com/bulletphysics/collision/dispatch/CollisionObject:getWorldTransform	(Lcom/bulletphysics/linearmath/Transform;)Lcom/bulletphysics/linearmath/Transform;
/*  645:     */    //   506: getfield 189	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*  646:     */    //   509: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*  647:     */    //   512: aload 17
/*  648:     */    //   514: aload 19
/*  649:     */    //   516: aload 13
/*  650:     */    //   518: aload 14
/*  651:     */    //   520: invokevirtual 184	com/bulletphysics/collision/dispatch/CollisionObject:getWorldTransform	(Lcom/bulletphysics/linearmath/Transform;)Lcom/bulletphysics/linearmath/Transform;
/*  652:     */    //   523: getfield 189	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*  653:     */    //   526: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*  654:     */    //   529: fconst_1
/*  655:     */    //   530: fstore 31
/*  656:     */    //   532: aload_0
/*  657:     */    //   533: getfield 76	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  658:     */    //   536: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*  659:     */    //   539: istore 35
/*  660:     */    //   541: aload_0
/*  661:     */    //   542: getfield 65	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:constraintsPool	Lcom/bulletphysics/util/ObjectPool;
/*  662:     */    //   545: invokevirtual 361	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*  663:     */    //   548: checkcast 63	com/bulletphysics/dynamics/constraintsolver/SolverConstraint
/*  664:     */    //   551: astore 36
/*  665:     */    //   553: aload_0
/*  666:     */    //   554: getfield 76	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  667:     */    //   557: aload 36
/*  668:     */    //   559: invokevirtual 365	com/bulletphysics/util/ObjectArrayList:add	(Ljava/lang/Object;)Z
/*  669:     */    //   562: pop
/*  670:     */    //   563: aload 12
/*  671:     */    //   565: invokestatic 163	com/bulletphysics/dynamics/RigidBody:upcast	(Lcom/bulletphysics/collision/dispatch/CollisionObject;)Lcom/bulletphysics/dynamics/RigidBody;
/*  672:     */    //   568: astore 37
/*  673:     */    //   570: aload 13
/*  674:     */    //   572: invokestatic 163	com/bulletphysics/dynamics/RigidBody:upcast	(Lcom/bulletphysics/collision/dispatch/CollisionObject;)Lcom/bulletphysics/dynamics/RigidBody;
/*  675:     */    //   575: astore 38
/*  676:     */    //   577: aload 36
/*  677:     */    //   579: iload 29
/*  678:     */    //   581: putfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/*  679:     */    //   584: aload 36
/*  680:     */    //   586: iload 30
/*  681:     */    //   588: putfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/*  682:     */    //   591: aload 36
/*  683:     */    //   593: getstatic 489	com/bulletphysics/dynamics/constraintsolver/SolverConstraintType:SOLVER_CONTACT_1D	Lcom/bulletphysics/dynamics/constraintsolver/SolverConstraintType;
/*  684:     */    //   596: putfield 380	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:constraintType	Lcom/bulletphysics/dynamics/constraintsolver/SolverConstraintType;
/*  685:     */    //   599: aload 36
/*  686:     */    //   601: aload 33
/*  687:     */    //   603: putfield 391	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:originalContactPoint	Ljava/lang/Object;
/*  688:     */    //   606: aload 21
/*  689:     */    //   608: aload 16
/*  690:     */    //   610: aload 33
/*  691:     */    //   612: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  692:     */    //   615: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  693:     */    //   618: aload 37
/*  694:     */    //   620: ifnull +31 -> 651
/*  695:     */    //   623: aload 36
/*  696:     */    //   625: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*  697:     */    //   628: aload 21
/*  698:     */    //   630: invokevirtual 195	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*  699:     */    //   633: aload 37
/*  700:     */    //   635: aload 28
/*  701:     */    //   637: invokevirtual 403	com/bulletphysics/dynamics/RigidBody:getInvInertiaTensorWorld	(Ljavax/vecmath/Matrix3f;)Ljavax/vecmath/Matrix3f;
/*  702:     */    //   640: aload 36
/*  703:     */    //   642: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*  704:     */    //   645: invokevirtual 408	javax/vecmath/Matrix3f:transform	(Ljavax/vecmath/Tuple3f;)V
/*  705:     */    //   648: goto +14 -> 662
/*  706:     */    //   651: aload 36
/*  707:     */    //   653: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*  708:     */    //   656: fconst_0
/*  709:     */    //   657: fconst_0
/*  710:     */    //   658: fconst_0
/*  711:     */    //   659: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*  712:     */    //   662: aload 22
/*  713:     */    //   664: aload 17
/*  714:     */    //   666: aload 33
/*  715:     */    //   668: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  716:     */    //   671: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  717:     */    //   674: aload 38
/*  718:     */    //   676: ifnull +31 -> 707
/*  719:     */    //   679: aload 36
/*  720:     */    //   681: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*  721:     */    //   684: aload 22
/*  722:     */    //   686: invokevirtual 195	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*  723:     */    //   689: aload 38
/*  724:     */    //   691: aload 28
/*  725:     */    //   693: invokevirtual 403	com/bulletphysics/dynamics/RigidBody:getInvInertiaTensorWorld	(Ljavax/vecmath/Matrix3f;)Ljavax/vecmath/Matrix3f;
/*  726:     */    //   696: aload 36
/*  727:     */    //   698: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*  728:     */    //   701: invokevirtual 408	javax/vecmath/Matrix3f:transform	(Ljavax/vecmath/Tuple3f;)V
/*  729:     */    //   704: goto +14 -> 718
/*  730:     */    //   707: aload 36
/*  731:     */    //   709: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*  732:     */    //   712: fconst_0
/*  733:     */    //   713: fconst_0
/*  734:     */    //   714: fconst_0
/*  735:     */    //   715: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*  736:     */    //   718: fconst_0
/*  737:     */    //   719: fstore 39
/*  738:     */    //   721: fconst_0
/*  739:     */    //   722: fstore 40
/*  740:     */    //   724: aload 37
/*  741:     */    //   726: ifnull +33 -> 759
/*  742:     */    //   729: aload 27
/*  743:     */    //   731: aload 36
/*  744:     */    //   733: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*  745:     */    //   736: aload 16
/*  746:     */    //   738: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  747:     */    //   741: aload 37
/*  748:     */    //   743: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*  749:     */    //   746: aload 33
/*  750:     */    //   748: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  751:     */    //   751: aload 27
/*  752:     */    //   753: invokevirtual 274	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*  753:     */    //   756: fadd
/*  754:     */    //   757: fstore 39
/*  755:     */    //   759: aload 38
/*  756:     */    //   761: ifnull +33 -> 794
/*  757:     */    //   764: aload 27
/*  758:     */    //   766: aload 36
/*  759:     */    //   768: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*  760:     */    //   771: aload 17
/*  761:     */    //   773: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  762:     */    //   776: aload 38
/*  763:     */    //   778: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*  764:     */    //   781: aload 33
/*  765:     */    //   783: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  766:     */    //   786: aload 27
/*  767:     */    //   788: invokevirtual 274	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*  768:     */    //   791: fadd
/*  769:     */    //   792: fstore 40
/*  770:     */    //   794: fload 31
/*  771:     */    //   796: fload 39
/*  772:     */    //   798: fload 40
/*  773:     */    //   800: fadd
/*  774:     */    //   801: fdiv
/*  775:     */    //   802: fstore 41
/*  776:     */    //   804: aload 36
/*  777:     */    //   806: fload 41
/*  778:     */    //   808: putfield 291	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:jacDiagABInv	F
/*  779:     */    //   811: aload 36
/*  780:     */    //   813: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*  781:     */    //   816: aload 33
/*  782:     */    //   818: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  783:     */    //   821: invokevirtual 195	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*  784:     */    //   824: aload 36
/*  785:     */    //   826: getfield 277	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:relpos1CrossNormal	Ljavax/vecmath/Vector3f;
/*  786:     */    //   829: aload 16
/*  787:     */    //   831: aload 33
/*  788:     */    //   833: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  789:     */    //   836: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  790:     */    //   839: aload 36
/*  791:     */    //   841: getfield 280	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:relpos2CrossNormal	Ljavax/vecmath/Vector3f;
/*  792:     */    //   844: aload 17
/*  793:     */    //   846: aload 33
/*  794:     */    //   848: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  795:     */    //   851: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  796:     */    //   854: aload 37
/*  797:     */    //   856: ifnull +16 -> 872
/*  798:     */    //   859: aload 37
/*  799:     */    //   861: aload 16
/*  800:     */    //   863: aload 23
/*  801:     */    //   865: invokevirtual 496	com/bulletphysics/dynamics/RigidBody:getVelocityInLocalPoint	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*  802:     */    //   868: pop
/*  803:     */    //   869: goto +11 -> 880
/*  804:     */    //   872: aload 23
/*  805:     */    //   874: fconst_0
/*  806:     */    //   875: fconst_0
/*  807:     */    //   876: fconst_0
/*  808:     */    //   877: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*  809:     */    //   880: aload 38
/*  810:     */    //   882: ifnull +16 -> 898
/*  811:     */    //   885: aload 38
/*  812:     */    //   887: aload 17
/*  813:     */    //   889: aload 24
/*  814:     */    //   891: invokevirtual 496	com/bulletphysics/dynamics/RigidBody:getVelocityInLocalPoint	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*  815:     */    //   894: pop
/*  816:     */    //   895: goto +11 -> 906
/*  817:     */    //   898: aload 24
/*  818:     */    //   900: fconst_0
/*  819:     */    //   901: fconst_0
/*  820:     */    //   902: fconst_0
/*  821:     */    //   903: invokevirtual 228	javax/vecmath/Vector3f:set	(FFF)V
/*  822:     */    //   906: aload 20
/*  823:     */    //   908: aload 23
/*  824:     */    //   910: aload 24
/*  825:     */    //   912: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*  826:     */    //   915: aload 33
/*  827:     */    //   917: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  828:     */    //   920: aload 20
/*  829:     */    //   922: invokevirtual 274	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*  830:     */    //   925: fstore 34
/*  831:     */    //   927: aload 36
/*  832:     */    //   929: aload 33
/*  833:     */    //   931: invokevirtual 476	com/bulletphysics/collision/narrowphase/ManifoldPoint:getDistance	()F
/*  834:     */    //   934: aload 9
/*  835:     */    //   936: getfield 499	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:linearSlop	F
/*  836:     */    //   939: fadd
/*  837:     */    //   940: fconst_0
/*  838:     */    //   941: invokestatic 502	java/lang/Math:min	(FF)F
/*  839:     */    //   944: putfield 257	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:penetration	F
/*  840:     */    //   947: aload 36
/*  841:     */    //   949: aload 33
/*  842:     */    //   951: getfield 387	com/bulletphysics/collision/narrowphase/ManifoldPoint:combinedFriction	F
/*  843:     */    //   954: putfield 348	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:friction	F
/*  844:     */    //   957: aload 36
/*  845:     */    //   959: aload_0
/*  846:     */    //   960: fload 34
/*  847:     */    //   962: aload 33
/*  848:     */    //   964: getfield 505	com/bulletphysics/collision/narrowphase/ManifoldPoint:combinedRestitution	F
/*  849:     */    //   967: invokespecial 507	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:restitutionCurve	(FF)F
/*  850:     */    //   970: putfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*  851:     */    //   973: aload 36
/*  852:     */    //   975: getfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*  853:     */    //   978: fconst_0
/*  854:     */    //   979: fcmpg
/*  855:     */    //   980: ifgt +9 -> 989
/*  856:     */    //   983: aload 36
/*  857:     */    //   985: fconst_0
/*  858:     */    //   986: putfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*  859:     */    //   989: aload 36
/*  860:     */    //   991: getfield 257	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:penetration	F
/*  861:     */    //   994: fneg
/*  862:     */    //   995: aload 9
/*  863:     */    //   997: getfield 286	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:timeStep	F
/*  864:     */    //   1000: fdiv
/*  865:     */    //   1001: fstore 39
/*  866:     */    //   1003: aload 36
/*  867:     */    //   1005: getfield 288	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:restitution	F
/*  868:     */    //   1008: fload 39
/*  869:     */    //   1010: fcmpl
/*  870:     */    //   1011: ifle +9 -> 1020
/*  871:     */    //   1014: aload 36
/*  872:     */    //   1016: fconst_0
/*  873:     */    //   1017: putfield 257	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:penetration	F
/*  874:     */    //   1020: aload 43
/*  875:     */    //   1022: invokevirtual 298	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*  876:     */    //   1025: astore 40
/*  877:     */    //   1027: aload 9
/*  878:     */    //   1029: getfield 510	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:solverMode	I
/*  879:     */    //   1032: iconst_4
/*  880:     */    //   1033: iand
/*  881:     */    //   1034: ifeq +123 -> 1157
/*  882:     */    //   1037: aload 36
/*  883:     */    //   1039: aload 33
/*  884:     */    //   1041: getfield 511	com/bulletphysics/collision/narrowphase/ManifoldPoint:appliedImpulse	F
/*  885:     */    //   1044: aload 9
/*  886:     */    //   1046: getfield 514	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:warmstartingFactor	F
/*  887:     */    //   1049: fmul
/*  888:     */    //   1050: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*  889:     */    //   1053: aload 37
/*  890:     */    //   1055: ifnull +48 -> 1103
/*  891:     */    //   1058: aload 40
/*  892:     */    //   1060: aload 37
/*  893:     */    //   1062: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*  894:     */    //   1065: aload 36
/*  895:     */    //   1067: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*  896:     */    //   1070: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*  897:     */    //   1073: aload_0
/*  898:     */    //   1074: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  899:     */    //   1077: aload 36
/*  900:     */    //   1079: getfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/*  901:     */    //   1082: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*  902:     */    //   1085: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*  903:     */    //   1088: aload 40
/*  904:     */    //   1090: aload 36
/*  905:     */    //   1092: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/*  906:     */    //   1095: aload 36
/*  907:     */    //   1097: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*  908:     */    //   1100: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*  909:     */    //   1103: aload 38
/*  910:     */    //   1105: ifnull +58 -> 1163
/*  911:     */    //   1108: aload 40
/*  912:     */    //   1110: aload 38
/*  913:     */    //   1112: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/*  914:     */    //   1115: aload 36
/*  915:     */    //   1117: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/*  916:     */    //   1120: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*  917:     */    //   1123: aload_0
/*  918:     */    //   1124: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  919:     */    //   1127: aload 36
/*  920:     */    //   1129: getfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/*  921:     */    //   1132: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/*  922:     */    //   1135: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/*  923:     */    //   1138: aload 40
/*  924:     */    //   1140: aload 36
/*  925:     */    //   1142: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/*  926:     */    //   1145: aload 36
/*  927:     */    //   1147: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*  928:     */    //   1150: fneg
/*  929:     */    //   1151: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*  930:     */    //   1154: goto +9 -> 1163
/*  931:     */    //   1157: aload 36
/*  932:     */    //   1159: fconst_0
/*  933:     */    //   1160: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/*  934:     */    //   1163: aload 36
/*  935:     */    //   1165: fconst_0
/*  936:     */    //   1166: putfield 294	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedPushImpulse	F
/*  937:     */    //   1169: aload 36
/*  938:     */    //   1171: aload_0
/*  939:     */    //   1172: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/*  940:     */    //   1175: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/*  941:     */    //   1178: putfield 383	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:frictionIndex	I
/*  942:     */    //   1181: aload 33
/*  943:     */    //   1183: getfield 517	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionInitialized	Z
/*  944:     */    //   1186: ifne +233 -> 1419
/*  945:     */    //   1189: aload 33
/*  946:     */    //   1191: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  947:     */    //   1194: fload 34
/*  948:     */    //   1196: aload 33
/*  949:     */    //   1198: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  950:     */    //   1201: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*  951:     */    //   1204: aload 33
/*  952:     */    //   1206: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  953:     */    //   1209: aload 20
/*  954:     */    //   1211: aload 33
/*  955:     */    //   1213: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  956:     */    //   1216: invokevirtual 486	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*  957:     */    //   1219: aload 33
/*  958:     */    //   1221: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  959:     */    //   1224: invokevirtual 523	javax/vecmath/Vector3f:lengthSquared	()F
/*  960:     */    //   1227: fstore 41
/*  961:     */    //   1229: fload 41
/*  962:     */    //   1231: ldc_w 524
/*  963:     */    //   1234: fcmpl
/*  964:     */    //   1235: ifle +103 -> 1338
/*  965:     */    //   1238: aload 33
/*  966:     */    //   1240: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  967:     */    //   1243: fconst_1
/*  968:     */    //   1244: fload 41
/*  969:     */    //   1246: f2d
/*  970:     */    //   1247: invokestatic 528	java/lang/Math:sqrt	(D)D
/*  971:     */    //   1250: d2f
/*  972:     */    //   1251: fdiv
/*  973:     */    //   1252: invokevirtual 531	javax/vecmath/Vector3f:scale	(F)V
/*  974:     */    //   1255: aload_0
/*  975:     */    //   1256: aload 33
/*  976:     */    //   1258: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  977:     */    //   1261: iload 29
/*  978:     */    //   1263: iload 30
/*  979:     */    //   1265: iload 35
/*  980:     */    //   1267: aload 33
/*  981:     */    //   1269: aload 16
/*  982:     */    //   1271: aload 17
/*  983:     */    //   1273: aload 12
/*  984:     */    //   1275: aload 13
/*  985:     */    //   1277: fload 31
/*  986:     */    //   1279: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/*  987:     */    //   1282: aload 33
/*  988:     */    //   1284: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*  989:     */    //   1287: aload 33
/*  990:     */    //   1289: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/*  991:     */    //   1292: aload 33
/*  992:     */    //   1294: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/*  993:     */    //   1297: invokevirtual 399	javax/vecmath/Vector3f:cross	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*  994:     */    //   1300: aload 33
/*  995:     */    //   1302: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/*  996:     */    //   1305: invokevirtual 539	javax/vecmath/Vector3f:normalize	()V
/*  997:     */    //   1308: aload_0
/*  998:     */    //   1309: aload 33
/*  999:     */    //   1311: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/* 1000:     */    //   1314: iload 29
/* 1001:     */    //   1316: iload 30
/* 1002:     */    //   1318: iload 35
/* 1003:     */    //   1320: aload 33
/* 1004:     */    //   1322: aload 16
/* 1005:     */    //   1324: aload 17
/* 1006:     */    //   1326: aload 12
/* 1007:     */    //   1328: aload 13
/* 1008:     */    //   1330: fload 31
/* 1009:     */    //   1332: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/* 1010:     */    //   1335: goto +75 -> 1410
/* 1011:     */    //   1338: aload 33
/* 1012:     */    //   1340: getfield 492	com/bulletphysics/collision/narrowphase/ManifoldPoint:normalWorldOnB	Ljavax/vecmath/Vector3f;
/* 1013:     */    //   1343: aload 33
/* 1014:     */    //   1345: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/* 1015:     */    //   1348: aload 33
/* 1016:     */    //   1350: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/* 1017:     */    //   1353: invokestatic 545	com/bulletphysics/linearmath/TransformUtil:planeSpace1	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/* 1018:     */    //   1356: aload_0
/* 1019:     */    //   1357: aload 33
/* 1020:     */    //   1359: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/* 1021:     */    //   1362: iload 29
/* 1022:     */    //   1364: iload 30
/* 1023:     */    //   1366: iload 35
/* 1024:     */    //   1368: aload 33
/* 1025:     */    //   1370: aload 16
/* 1026:     */    //   1372: aload 17
/* 1027:     */    //   1374: aload 12
/* 1028:     */    //   1376: aload 13
/* 1029:     */    //   1378: fload 31
/* 1030:     */    //   1380: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/* 1031:     */    //   1383: aload_0
/* 1032:     */    //   1384: aload 33
/* 1033:     */    //   1386: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/* 1034:     */    //   1389: iload 29
/* 1035:     */    //   1391: iload 30
/* 1036:     */    //   1393: iload 35
/* 1037:     */    //   1395: aload 33
/* 1038:     */    //   1397: aload 16
/* 1039:     */    //   1399: aload 17
/* 1040:     */    //   1401: aload 12
/* 1041:     */    //   1403: aload 13
/* 1042:     */    //   1405: fload 31
/* 1043:     */    //   1407: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/* 1044:     */    //   1410: aload 33
/* 1045:     */    //   1412: iconst_1
/* 1046:     */    //   1413: putfield 517	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionInitialized	Z
/* 1047:     */    //   1416: goto +57 -> 1473
/* 1048:     */    //   1419: aload_0
/* 1049:     */    //   1420: aload 33
/* 1050:     */    //   1422: getfield 520	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir1	Ljavax/vecmath/Vector3f;
/* 1051:     */    //   1425: iload 29
/* 1052:     */    //   1427: iload 30
/* 1053:     */    //   1429: iload 35
/* 1054:     */    //   1431: aload 33
/* 1055:     */    //   1433: aload 16
/* 1056:     */    //   1435: aload 17
/* 1057:     */    //   1437: aload 12
/* 1058:     */    //   1439: aload 13
/* 1059:     */    //   1441: fload 31
/* 1060:     */    //   1443: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/* 1061:     */    //   1446: aload_0
/* 1062:     */    //   1447: aload 33
/* 1063:     */    //   1449: getfield 536	com/bulletphysics/collision/narrowphase/ManifoldPoint:lateralFrictionDir2	Ljavax/vecmath/Vector3f;
/* 1064:     */    //   1452: iload 29
/* 1065:     */    //   1454: iload 30
/* 1066:     */    //   1456: iload 35
/* 1067:     */    //   1458: aload 33
/* 1068:     */    //   1460: aload 16
/* 1069:     */    //   1462: aload 17
/* 1070:     */    //   1464: aload 12
/* 1071:     */    //   1466: aload 13
/* 1072:     */    //   1468: fload 31
/* 1073:     */    //   1470: invokevirtual 533	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:addFrictionConstraint	(Ljavax/vecmath/Vector3f;IIILcom/bulletphysics/collision/narrowphase/ManifoldPoint;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/dispatch/CollisionObject;Lcom/bulletphysics/collision/dispatch/CollisionObject;F)V
/* 1074:     */    //   1473: aload_0
/* 1075:     */    //   1474: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1076:     */    //   1477: aload 36
/* 1077:     */    //   1479: getfield 383	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:frictionIndex	I
/* 1078:     */    //   1482: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1079:     */    //   1485: checkcast 63	com/bulletphysics/dynamics/constraintsolver/SolverConstraint
/* 1080:     */    //   1488: astore 41
/* 1081:     */    //   1490: aload 9
/* 1082:     */    //   1492: getfield 510	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:solverMode	I
/* 1083:     */    //   1495: iconst_4
/* 1084:     */    //   1496: iand
/* 1085:     */    //   1497: ifeq +123 -> 1620
/* 1086:     */    //   1500: aload 41
/* 1087:     */    //   1502: aload 33
/* 1088:     */    //   1504: getfield 548	com/bulletphysics/collision/narrowphase/ManifoldPoint:appliedImpulseLateral1	F
/* 1089:     */    //   1507: aload 9
/* 1090:     */    //   1509: getfield 514	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:warmstartingFactor	F
/* 1091:     */    //   1512: fmul
/* 1092:     */    //   1513: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1093:     */    //   1516: aload 37
/* 1094:     */    //   1518: ifnull +48 -> 1566
/* 1095:     */    //   1521: aload 40
/* 1096:     */    //   1523: aload 37
/* 1097:     */    //   1525: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/* 1098:     */    //   1528: aload 41
/* 1099:     */    //   1530: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/* 1100:     */    //   1533: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1101:     */    //   1536: aload_0
/* 1102:     */    //   1537: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1103:     */    //   1540: aload 36
/* 1104:     */    //   1542: getfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/* 1105:     */    //   1545: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1106:     */    //   1548: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/* 1107:     */    //   1551: aload 40
/* 1108:     */    //   1553: aload 41
/* 1109:     */    //   1555: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/* 1110:     */    //   1558: aload 41
/* 1111:     */    //   1560: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1112:     */    //   1563: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/* 1113:     */    //   1566: aload 38
/* 1114:     */    //   1568: ifnull +58 -> 1626
/* 1115:     */    //   1571: aload 40
/* 1116:     */    //   1573: aload 38
/* 1117:     */    //   1575: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/* 1118:     */    //   1578: aload 41
/* 1119:     */    //   1580: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/* 1120:     */    //   1583: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1121:     */    //   1586: aload_0
/* 1122:     */    //   1587: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1123:     */    //   1590: aload 36
/* 1124:     */    //   1592: getfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/* 1125:     */    //   1595: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1126:     */    //   1598: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/* 1127:     */    //   1601: aload 40
/* 1128:     */    //   1603: aload 41
/* 1129:     */    //   1605: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/* 1130:     */    //   1608: aload 41
/* 1131:     */    //   1610: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1132:     */    //   1613: fneg
/* 1133:     */    //   1614: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/* 1134:     */    //   1617: goto +9 -> 1626
/* 1135:     */    //   1620: aload 41
/* 1136:     */    //   1622: fconst_0
/* 1137:     */    //   1623: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1138:     */    //   1626: aload_0
/* 1139:     */    //   1627: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1140:     */    //   1630: aload 36
/* 1141:     */    //   1632: getfield 383	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:frictionIndex	I
/* 1142:     */    //   1635: iconst_1
/* 1143:     */    //   1636: iadd
/* 1144:     */    //   1637: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1145:     */    //   1640: checkcast 63	com/bulletphysics/dynamics/constraintsolver/SolverConstraint
/* 1146:     */    //   1643: astore 41
/* 1147:     */    //   1645: aload 9
/* 1148:     */    //   1647: getfield 510	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:solverMode	I
/* 1149:     */    //   1650: iconst_4
/* 1150:     */    //   1651: iand
/* 1151:     */    //   1652: ifeq +123 -> 1775
/* 1152:     */    //   1655: aload 41
/* 1153:     */    //   1657: aload 33
/* 1154:     */    //   1659: getfield 551	com/bulletphysics/collision/narrowphase/ManifoldPoint:appliedImpulseLateral2	F
/* 1155:     */    //   1662: aload 9
/* 1156:     */    //   1664: getfield 514	com/bulletphysics/dynamics/constraintsolver/ContactSolverInfo:warmstartingFactor	F
/* 1157:     */    //   1667: fmul
/* 1158:     */    //   1668: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1159:     */    //   1671: aload 37
/* 1160:     */    //   1673: ifnull +48 -> 1721
/* 1161:     */    //   1676: aload 40
/* 1162:     */    //   1678: aload 37
/* 1163:     */    //   1680: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/* 1164:     */    //   1683: aload 41
/* 1165:     */    //   1685: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/* 1166:     */    //   1688: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1167:     */    //   1691: aload_0
/* 1168:     */    //   1692: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1169:     */    //   1695: aload 36
/* 1170:     */    //   1697: getfield 368	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdA	I
/* 1171:     */    //   1700: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1172:     */    //   1703: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/* 1173:     */    //   1706: aload 40
/* 1174:     */    //   1708: aload 41
/* 1175:     */    //   1710: getfield 305	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentA	Ljavax/vecmath/Vector3f;
/* 1176:     */    //   1713: aload 41
/* 1177:     */    //   1715: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1178:     */    //   1718: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/* 1179:     */    //   1721: aload 38
/* 1180:     */    //   1723: ifnull +58 -> 1781
/* 1181:     */    //   1726: aload 40
/* 1182:     */    //   1728: aload 38
/* 1183:     */    //   1730: invokevirtual 206	com/bulletphysics/dynamics/RigidBody:getInvMass	()F
/* 1184:     */    //   1733: aload 41
/* 1185:     */    //   1735: getfield 270	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:contactNormal	Ljavax/vecmath/Vector3f;
/* 1186:     */    //   1738: invokevirtual 302	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1187:     */    //   1741: aload_0
/* 1188:     */    //   1742: getfield 74	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverBodyPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1189:     */    //   1745: aload 36
/* 1190:     */    //   1747: getfield 371	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:solverBodyIdB	I
/* 1191:     */    //   1750: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1192:     */    //   1753: checkcast 53	com/bulletphysics/dynamics/constraintsolver/SolverBody
/* 1193:     */    //   1756: aload 40
/* 1194:     */    //   1758: aload 41
/* 1195:     */    //   1760: getfield 312	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:angularComponentB	Ljavax/vecmath/Vector3f;
/* 1196:     */    //   1763: aload 41
/* 1197:     */    //   1765: getfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1198:     */    //   1768: fneg
/* 1199:     */    //   1769: invokevirtual 345	com/bulletphysics/dynamics/constraintsolver/SolverBody:internalApplyImpulse	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/* 1200:     */    //   1772: goto +9 -> 1781
/* 1201:     */    //   1775: aload 41
/* 1202:     */    //   1777: fconst_0
/* 1203:     */    //   1778: putfield 342	com/bulletphysics/dynamics/constraintsolver/SolverConstraint:appliedImpulse	F
/* 1204:     */    //   1781: iinc 32 1
/* 1205:     */    //   1784: goto -1334 -> 450
/* 1206:     */    //   1787: iinc 15 1
/* 1207:     */    //   1790: goto -1626 -> 164
/* 1208:     */    //   1793: iconst_0
/* 1209:     */    //   1794: istore 15
/* 1210:     */    //   1796: iload 15
/* 1211:     */    //   1798: iload 8
/* 1212:     */    //   1800: if_icmpge +29 -> 1829
/* 1213:     */    //   1803: aload 6
/* 1214:     */    //   1805: iload 7
/* 1215:     */    //   1807: iload 15
/* 1216:     */    //   1809: iadd
/* 1217:     */    //   1810: invokevirtual 442	com/bulletphysics/util/ObjectArrayList:getQuick	(I)Ljava/lang/Object;
/* 1218:     */    //   1813: checkcast 553	com/bulletphysics/dynamics/constraintsolver/TypedConstraint
/* 1219:     */    //   1816: astore 16
/* 1220:     */    //   1818: aload 16
/* 1221:     */    //   1820: invokevirtual 556	com/bulletphysics/dynamics/constraintsolver/TypedConstraint:buildJacobian	()V
/* 1222:     */    //   1823: iinc 15 1
/* 1223:     */    //   1826: goto -30 -> 1796
/* 1224:     */    //   1829: aload_0
/* 1225:     */    //   1830: getfield 76	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1226:     */    //   1833: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/* 1227:     */    //   1836: istore 15
/* 1228:     */    //   1838: aload_0
/* 1229:     */    //   1839: getfield 78	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:tmpSolverFrictionConstraintPool	Lcom/bulletphysics/util/ObjectArrayList;
/* 1230:     */    //   1842: invokevirtual 463	com/bulletphysics/util/ObjectArrayList:size	()I
/* 1231:     */    //   1845: istore 16
/* 1232:     */    //   1847: aload_0
/* 1233:     */    //   1848: getfield 83	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderTmpConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/* 1234:     */    //   1851: iload 15
/* 1235:     */    //   1853: iconst_0
/* 1236:     */    //   1854: invokestatic 562	com/bulletphysics/linearmath/MiscUtil:resize	(Lcom/bulletphysics/util/IntArrayList;II)V
/* 1237:     */    //   1857: aload_0
/* 1238:     */    //   1858: getfield 85	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderFrictionConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/* 1239:     */    //   1861: iload 16
/* 1240:     */    //   1863: iconst_0
/* 1241:     */    //   1864: invokestatic 562	com/bulletphysics/linearmath/MiscUtil:resize	(Lcom/bulletphysics/util/IntArrayList;II)V
/* 1242:     */    //   1867: iconst_0
/* 1243:     */    //   1868: istore 17
/* 1244:     */    //   1870: iload 17
/* 1245:     */    //   1872: iload 15
/* 1246:     */    //   1874: if_icmpge +20 -> 1894
/* 1247:     */    //   1877: aload_0
/* 1248:     */    //   1878: getfield 83	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderTmpConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/* 1249:     */    //   1881: iload 17
/* 1250:     */    //   1883: iload 17
/* 1251:     */    //   1885: invokevirtual 565	com/bulletphysics/util/IntArrayList:set	(II)V
/* 1252:     */    //   1888: iinc 17 1
/* 1253:     */    //   1891: goto -21 -> 1870
/* 1254:     */    //   1894: iconst_0
/* 1255:     */    //   1895: istore 17
/* 1256:     */    //   1897: iload 17
/* 1257:     */    //   1899: iload 16
/* 1258:     */    //   1901: if_icmpge +20 -> 1921
/* 1259:     */    //   1904: aload_0
/* 1260:     */    //   1905: getfield 85	com/bulletphysics/dynamics/constraintsolver/SequentialImpulseConstraintSolver:orderFrictionConstraintPool	Lcom/bulletphysics/util/IntArrayList;
/* 1261:     */    //   1908: iload 17
/* 1262:     */    //   1910: iload 17
/* 1263:     */    //   1912: invokevirtual 565	com/bulletphysics/util/IntArrayList:set	(II)V
/* 1264:     */    //   1915: iinc 17 1
/* 1265:     */    //   1918: goto -21 -> 1897
/* 1266:     */    //   1921: fconst_0
/* 1267:     */    //   1922: fstore 17
/* 1268:     */    //   1924: invokestatic 438	com/bulletphysics/BulletStats:popProfile	()V
/* 1269:     */    //   1927: fload 17
/* 1270:     */    //   1929: aload 43
/* 1271:     */    //   1931: dup
/* 1272:     */    //   1932: invokevirtual 237	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 1273:     */    //   1935: dup
/* 1274:     */    //   1936: invokevirtual 315	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 1275:     */    //   1939: invokevirtual 411	com/bulletphysics/$Stack:pop$javax$vecmath$Matrix3f	()V
/* 1276:     */    //   1942: freturn
/* 1277:     */    //   1943: astore 42
/* 1278:     */    //   1945: invokestatic 438	com/bulletphysics/BulletStats:popProfile	()V
/* 1279:     */    //   1948: aload 42
/* 1280:     */    //   1950: athrow
/* 1281:     */    //   1951: aload 43
/* 1282:     */    //   1953: dup
/* 1283:     */    //   1954: invokevirtual 237	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 1284:     */    //   1957: dup
/* 1285:     */    //   1958: invokevirtual 315	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 1286:     */    //   1961: invokevirtual 411	com/bulletphysics/$Stack:pop$javax$vecmath$Matrix3f	()V
/* 1287:     */    //   1964: athrow
/* 1288:     */    // Line number table:
/* 1289:     */    //   Java source line #412	-> byte code offset #18
/* 1290:     */    //   Java source line #415	-> byte code offset #24
/* 1291:     */    //   Java source line #417	-> byte code offset #32
/* 1292:     */    //   Java source line #785	-> byte code offset #35
/* 1293:     */    //   Java source line #419	-> byte code offset #54
/* 1294:     */    //   Java source line #420	-> byte code offset #57
/* 1295:     */    //   Java source line #445	-> byte code offset #63
/* 1296:     */    //   Java source line #489	-> byte code offset #70
/* 1297:     */    //   Java source line #490	-> byte code offset #77
/* 1298:     */    //   Java source line #492	-> byte code offset #84
/* 1299:     */    //   Java source line #493	-> byte code offset #91
/* 1300:     */    //   Java source line #494	-> byte code offset #98
/* 1301:     */    //   Java source line #495	-> byte code offset #105
/* 1302:     */    //   Java source line #496	-> byte code offset #112
/* 1303:     */    //   Java source line #497	-> byte code offset #119
/* 1304:     */    //   Java source line #498	-> byte code offset #126
/* 1305:     */    //   Java source line #499	-> byte code offset #133
/* 1306:     */    //   Java source line #500	-> byte code offset #140
/* 1307:     */    //   Java source line #501	-> byte code offset #147
/* 1308:     */    //   Java source line #503	-> byte code offset #154
/* 1309:     */    //   Java source line #505	-> byte code offset #161
/* 1310:     */    //   Java source line #506	-> byte code offset #171
/* 1311:     */    //   Java source line #507	-> byte code offset #185
/* 1312:     */    //   Java source line #508	-> byte code offset #195
/* 1313:     */    //   Java source line #510	-> byte code offset #205
/* 1314:     */    //   Java source line #511	-> byte code offset #208
/* 1315:     */    //   Java source line #513	-> byte code offset #211
/* 1316:     */    //   Java source line #514	-> byte code offset #219
/* 1317:     */    //   Java source line #515	-> byte code offset #227
/* 1318:     */    //   Java source line #517	-> byte code offset #235
/* 1319:     */    //   Java source line #520	-> byte code offset #245
/* 1320:     */    //   Java source line #521	-> byte code offset #254
/* 1321:     */    //   Java source line #522	-> byte code offset #266
/* 1322:     */    //   Java source line #523	-> byte code offset #276
/* 1323:     */    //   Java source line #524	-> byte code offset #284
/* 1324:     */    //   Java source line #525	-> byte code offset #291
/* 1325:     */    //   Java source line #529	-> byte code offset #294
/* 1326:     */    //   Java source line #530	-> byte code offset #303
/* 1327:     */    //   Java source line #531	-> byte code offset #315
/* 1328:     */    //   Java source line #532	-> byte code offset #325
/* 1329:     */    //   Java source line #535	-> byte code offset #333
/* 1330:     */    //   Java source line #536	-> byte code offset #341
/* 1331:     */    //   Java source line #537	-> byte code offset #349
/* 1332:     */    //   Java source line #540	-> byte code offset #359
/* 1333:     */    //   Java source line #541	-> byte code offset #368
/* 1334:     */    //   Java source line #542	-> byte code offset #380
/* 1335:     */    //   Java source line #543	-> byte code offset #390
/* 1336:     */    //   Java source line #544	-> byte code offset #398
/* 1337:     */    //   Java source line #545	-> byte code offset #405
/* 1338:     */    //   Java source line #549	-> byte code offset #408
/* 1339:     */    //   Java source line #550	-> byte code offset #417
/* 1340:     */    //   Java source line #551	-> byte code offset #429
/* 1341:     */    //   Java source line #552	-> byte code offset #439
/* 1342:     */    //   Java source line #558	-> byte code offset #447
/* 1343:     */    //   Java source line #560	-> byte code offset #460
/* 1344:     */    //   Java source line #562	-> byte code offset #469
/* 1345:     */    //   Java source line #563	-> byte code offset #479
/* 1346:     */    //   Java source line #564	-> byte code offset #487
/* 1347:     */    //   Java source line #566	-> byte code offset #495
/* 1348:     */    //   Java source line #567	-> byte code offset #512
/* 1349:     */    //   Java source line #569	-> byte code offset #529
/* 1350:     */    //   Java source line #572	-> byte code offset #532
/* 1351:     */    //   Java source line #575	-> byte code offset #541
/* 1352:     */    //   Java source line #576	-> byte code offset #553
/* 1353:     */    //   Java source line #577	-> byte code offset #563
/* 1354:     */    //   Java source line #578	-> byte code offset #570
/* 1355:     */    //   Java source line #580	-> byte code offset #577
/* 1356:     */    //   Java source line #581	-> byte code offset #584
/* 1357:     */    //   Java source line #582	-> byte code offset #591
/* 1358:     */    //   Java source line #584	-> byte code offset #599
/* 1359:     */    //   Java source line #586	-> byte code offset #606
/* 1360:     */    //   Java source line #588	-> byte code offset #618
/* 1361:     */    //   Java source line #589	-> byte code offset #623
/* 1362:     */    //   Java source line #590	-> byte code offset #633
/* 1363:     */    //   Java source line #593	-> byte code offset #651
/* 1364:     */    //   Java source line #596	-> byte code offset #662
/* 1365:     */    //   Java source line #598	-> byte code offset #674
/* 1366:     */    //   Java source line #599	-> byte code offset #679
/* 1367:     */    //   Java source line #600	-> byte code offset #689
/* 1368:     */    //   Java source line #603	-> byte code offset #707
/* 1369:     */    //   Java source line #611	-> byte code offset #718
/* 1370:     */    //   Java source line #612	-> byte code offset #721
/* 1371:     */    //   Java source line #613	-> byte code offset #724
/* 1372:     */    //   Java source line #614	-> byte code offset #729
/* 1373:     */    //   Java source line #615	-> byte code offset #741
/* 1374:     */    //   Java source line #617	-> byte code offset #759
/* 1375:     */    //   Java source line #618	-> byte code offset #764
/* 1376:     */    //   Java source line #619	-> byte code offset #776
/* 1377:     */    //   Java source line #623	-> byte code offset #794
/* 1378:     */    //   Java source line #624	-> byte code offset #804
/* 1379:     */    //   Java source line #627	-> byte code offset #811
/* 1380:     */    //   Java source line #628	-> byte code offset #824
/* 1381:     */    //   Java source line #629	-> byte code offset #839
/* 1382:     */    //   Java source line #631	-> byte code offset #854
/* 1383:     */    //   Java source line #632	-> byte code offset #859
/* 1384:     */    //   Java source line #635	-> byte code offset #872
/* 1385:     */    //   Java source line #638	-> byte code offset #880
/* 1386:     */    //   Java source line #639	-> byte code offset #885
/* 1387:     */    //   Java source line #642	-> byte code offset #898
/* 1388:     */    //   Java source line #645	-> byte code offset #906
/* 1389:     */    //   Java source line #647	-> byte code offset #915
/* 1390:     */    //   Java source line #649	-> byte code offset #927
/* 1391:     */    //   Java source line #652	-> byte code offset #947
/* 1392:     */    //   Java source line #653	-> byte code offset #957
/* 1393:     */    //   Java source line #654	-> byte code offset #973
/* 1394:     */    //   Java source line #655	-> byte code offset #983
/* 1395:     */    //   Java source line #658	-> byte code offset #989
/* 1396:     */    //   Java source line #660	-> byte code offset #1003
/* 1397:     */    //   Java source line #661	-> byte code offset #1014
/* 1398:     */    //   Java source line #664	-> byte code offset #1020
/* 1399:     */    //   Java source line #667	-> byte code offset #1027
/* 1400:     */    //   Java source line #668	-> byte code offset #1037
/* 1401:     */    //   Java source line #669	-> byte code offset #1053
/* 1402:     */    //   Java source line #670	-> byte code offset #1058
/* 1403:     */    //   Java source line #671	-> byte code offset #1073
/* 1404:     */    //   Java source line #673	-> byte code offset #1103
/* 1405:     */    //   Java source line #674	-> byte code offset #1108
/* 1406:     */    //   Java source line #675	-> byte code offset #1123
/* 1407:     */    //   Java source line #679	-> byte code offset #1157
/* 1408:     */    //   Java source line #682	-> byte code offset #1163
/* 1409:     */    //   Java source line #684	-> byte code offset #1169
/* 1410:     */    //   Java source line #685	-> byte code offset #1181
/* 1411:     */    //   Java source line #686	-> byte code offset #1189
/* 1412:     */    //   Java source line #687	-> byte code offset #1204
/* 1413:     */    //   Java source line #689	-> byte code offset #1219
/* 1414:     */    //   Java source line #690	-> byte code offset #1229
/* 1415:     */    //   Java source line #692	-> byte code offset #1238
/* 1416:     */    //   Java source line #693	-> byte code offset #1255
/* 1417:     */    //   Java source line #694	-> byte code offset #1282
/* 1418:     */    //   Java source line #695	-> byte code offset #1300
/* 1419:     */    //   Java source line #696	-> byte code offset #1308
/* 1420:     */    //   Java source line #701	-> byte code offset #1338
/* 1421:     */    //   Java source line #702	-> byte code offset #1356
/* 1422:     */    //   Java source line #703	-> byte code offset #1383
/* 1423:     */    //   Java source line #705	-> byte code offset #1410
/* 1424:     */    //   Java source line #707	-> byte code offset #1416
/* 1425:     */    //   Java source line #709	-> byte code offset #1419
/* 1426:     */    //   Java source line #710	-> byte code offset #1446
/* 1427:     */    //   Java source line #714	-> byte code offset #1473
/* 1428:     */    //   Java source line #715	-> byte code offset #1490
/* 1429:     */    //   Java source line #716	-> byte code offset #1500
/* 1430:     */    //   Java source line #717	-> byte code offset #1516
/* 1431:     */    //   Java source line #718	-> byte code offset #1521
/* 1432:     */    //   Java source line #719	-> byte code offset #1536
/* 1433:     */    //   Java source line #721	-> byte code offset #1566
/* 1434:     */    //   Java source line #722	-> byte code offset #1571
/* 1435:     */    //   Java source line #723	-> byte code offset #1586
/* 1436:     */    //   Java source line #727	-> byte code offset #1620
/* 1437:     */    //   Java source line #731	-> byte code offset #1626
/* 1438:     */    //   Java source line #732	-> byte code offset #1645
/* 1439:     */    //   Java source line #733	-> byte code offset #1655
/* 1440:     */    //   Java source line #734	-> byte code offset #1671
/* 1441:     */    //   Java source line #735	-> byte code offset #1676
/* 1442:     */    //   Java source line #736	-> byte code offset #1691
/* 1443:     */    //   Java source line #738	-> byte code offset #1721
/* 1444:     */    //   Java source line #739	-> byte code offset #1726
/* 1445:     */    //   Java source line #740	-> byte code offset #1741
/* 1446:     */    //   Java source line #744	-> byte code offset #1775
/* 1447:     */    //   Java source line #558	-> byte code offset #1781
/* 1448:     */    //   Java source line #505	-> byte code offset #1787
/* 1449:     */    //   Java source line #758	-> byte code offset #1793
/* 1450:     */    //   Java source line #759	-> byte code offset #1803
/* 1451:     */    //   Java source line #760	-> byte code offset #1818
/* 1452:     */    //   Java source line #758	-> byte code offset #1823
/* 1453:     */    //   Java source line #766	-> byte code offset #1829
/* 1454:     */    //   Java source line #767	-> byte code offset #1838
/* 1455:     */    //   Java source line #770	-> byte code offset #1847
/* 1456:     */    //   Java source line #771	-> byte code offset #1857
/* 1457:     */    //   Java source line #774	-> byte code offset #1867
/* 1458:     */    //   Java source line #775	-> byte code offset #1877
/* 1459:     */    //   Java source line #774	-> byte code offset #1888
/* 1460:     */    //   Java source line #777	-> byte code offset #1894
/* 1461:     */    //   Java source line #778	-> byte code offset #1904
/* 1462:     */    //   Java source line #777	-> byte code offset #1915
/* 1463:     */    //   Java source line #782	-> byte code offset #1921
/* 1464:     */    //   Java source line #785	-> byte code offset #1924
/* 1465:     */    // Local variable table:
/* 1466:     */    //   start	length	slot	name	signature
/* 1467:     */    //   18	1933	0	this	SequentialImpulseConstraintSolver
/* 1468:     */    //   18	1933	1	bodies	ObjectArrayList<CollisionObject>
/* 1469:     */    //   18	1933	2	numBodies	int
/* 1470:     */    //   18	1933	3	manifoldPtr	ObjectArrayList<PersistentManifold>
/* 1471:     */    //   18	1933	4	manifold_offset	int
/* 1472:     */    //   18	1933	5	numManifolds	int
/* 1473:     */    //   18	1933	6	constraints	ObjectArrayList<TypedConstraint>
/* 1474:     */    //   18	1933	7	constraints_offset	int
/* 1475:     */    //   18	1933	8	numConstraints	int
/* 1476:     */    //   18	1933	9	infoGlobal	ContactSolverInfo
/* 1477:     */    //   18	1933	10	debugDrawer	IDebugDraw
/* 1478:     */    //   33	6	11	f1	float
/* 1479:     */    //   55	406	11	manifold	PersistentManifold
/* 1480:     */    //   58	1407	12	colObj0	CollisionObject
/* 1481:     */    //   61	1406	13	colObj1	CollisionObject
/* 1482:     */    //   68	451	14	tmpTrans	Transform
/* 1483:     */    //   162	1626	15	i	int
/* 1484:     */    //   1794	30	15	j	int
/* 1485:     */    //   1836	37	15	numConstraintPool	int
/* 1486:     */    //   75	1386	16	rel_pos1	Vector3f
/* 1487:     */    //   1816	3	16	constraint	TypedConstraint
/* 1488:     */    //   1845	55	16	numFrictionPool	int
/* 1489:     */    //   82	1381	17	rel_pos2	Vector3f
/* 1490:     */    //   1868	60	17	i	int
/* 1491:     */    //   89	409	18	pos1	Vector3f
/* 1492:     */    //   96	419	19	pos2	Vector3f
/* 1493:     */    //   103	1107	20	vel	Vector3f
/* 1494:     */    //   110	519	21	torqueAxis0	Vector3f
/* 1495:     */    //   117	568	22	torqueAxis1	Vector3f
/* 1496:     */    //   124	785	23	vel1	Vector3f
/* 1497:     */    //   131	780	24	vel2	Vector3f
/* 1498:     */    //   138	3	25	frictionDir1	Vector3f
/* 1499:     */    //   145	3	26	frictionDir2	Vector3f
/* 1500:     */    //   152	635	27	vec	Vector3f
/* 1501:     */    //   159	533	28	tmpMat	Matrix3f
/* 1502:     */    //   206	1247	29	solverBodyIdA	int
/* 1503:     */    //   209	1246	30	solverBodyIdB	int
/* 1504:     */    //   264	14	31	solverBody	SolverBody
/* 1505:     */    //   313	14	31	solverBody	SolverBody
/* 1506:     */    //   378	14	31	solverBody	SolverBody
/* 1507:     */    //   427	14	31	solverBody	SolverBody
/* 1508:     */    //   530	939	31	relaxation	float
/* 1509:     */    //   448	1334	32	j	int
/* 1510:     */    //   467	1191	33	cp	ManifoldPoint
/* 1511:     */    //   925	270	34	rel_vel	float
/* 1512:     */    //   539	918	35	frictionIndex	int
/* 1513:     */    //   551	1195	36	solverConstraint	SolverConstraint
/* 1514:     */    //   568	1111	37	rb0	RigidBody
/* 1515:     */    //   575	1154	38	rb1	RigidBody
/* 1516:     */    //   719	78	39	denom0	float
/* 1517:     */    //   1001	8	39	penVel	float
/* 1518:     */    //   722	77	40	denom1	float
/* 1519:     */    //   1025	732	40	tmp	Vector3f
/* 1520:     */    //   802	5	41	denom	float
/* 1521:     */    //   1227	18	41	lat_rel_vel	float
/* 1522:     */    //   1488	133	41	frictionConstraint1	SolverConstraint
/* 1523:     */    //   1643	133	41	frictionConstraint2	SolverConstraint
/* 1524:     */    //   1943	6	42	localObject	Object
/* 1525:     */    //   3	1949	43	localStack	.Stack
/* 1526:     */    // Exception table:
/* 1527:     */    //   from	to	target	type
/* 1528:     */    //   24	35	1943	finally
/* 1529:     */    //   54	1924	1943	finally
/* 1530:     */    //   1943	1945	1943	finally
/* 1531:     */    //   5	1951	1951	finally
/* 1532:     */  }
/* 1533:     */  
/* 1534:     */  public float solveGroupCacheFriendlyIterations(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifoldPtr, int manifold_offset, int numManifolds, ObjectArrayList<TypedConstraint> constraints, int constraints_offset, int numConstraints, ContactSolverInfo infoGlobal, IDebugDraw debugDrawer)
/* 1535:     */  {
/* 1536: 790 */    BulletStats.pushProfile("solveGroupCacheFriendlyIterations");
/* 1537:     */    try {
/* 1538: 792 */      int numConstraintPool = this.tmpSolverConstraintPool.size();
/* 1539: 793 */      int numFrictionPool = this.tmpSolverFrictionConstraintPool.size();
/* 1540:     */      
/* 1544: 798 */      for (int iteration = 0; iteration < infoGlobal.numIterations; iteration++)
/* 1545:     */      {
/* 1547: 801 */        if (((infoGlobal.solverMode & 0x1) != 0) && 
/* 1548: 802 */          ((iteration & 0x7) == 0)) {
/* 1549: 803 */          for (int j = 0; j < numConstraintPool; j++) {
/* 1550: 804 */            int tmp = this.orderTmpConstraintPool.get(j);
/* 1551: 805 */            int swapi = randInt2(j + 1);
/* 1552: 806 */            this.orderTmpConstraintPool.set(j, this.orderTmpConstraintPool.get(swapi));
/* 1553: 807 */            this.orderTmpConstraintPool.set(swapi, tmp);
/* 1554:     */          }
/* 1555:     */          
/* 1556: 810 */          for (j = 0; j < numFrictionPool; j++) {
/* 1557: 811 */            int tmp = this.orderFrictionConstraintPool.get(j);
/* 1558: 812 */            int swapi = randInt2(j + 1);
/* 1559: 813 */            this.orderFrictionConstraintPool.set(j, this.orderFrictionConstraintPool.get(swapi));
/* 1560: 814 */            this.orderFrictionConstraintPool.set(swapi, tmp);
/* 1561:     */          }
/* 1562:     */        }
/* 1563:     */        
/* 1565: 819 */        for (int j = 0; j < numConstraints; j++) {
/* 1566: 820 */          TypedConstraint constraint = (TypedConstraint)constraints.getQuick(constraints_offset + j);
/* 1567:     */          
/* 1569: 823 */          if ((constraint.getRigidBodyA().getIslandTag() >= 0) && (constraint.getRigidBodyA().getCompanionId() >= 0)) {
/* 1570: 824 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyA().getCompanionId())).writebackVelocity();
/* 1571:     */          }
/* 1572: 826 */          if ((constraint.getRigidBodyB().getIslandTag() >= 0) && (constraint.getRigidBodyB().getCompanionId() >= 0)) {
/* 1573: 827 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyB().getCompanionId())).writebackVelocity();
/* 1574:     */          }
/* 1575:     */          
/* 1576: 830 */          constraint.solveConstraint(infoGlobal.timeStep);
/* 1577:     */          
/* 1578: 832 */          if ((constraint.getRigidBodyA().getIslandTag() >= 0) && (constraint.getRigidBodyA().getCompanionId() >= 0)) {
/* 1579: 833 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyA().getCompanionId())).readVelocity();
/* 1580:     */          }
/* 1581: 835 */          if ((constraint.getRigidBodyB().getIslandTag() >= 0) && (constraint.getRigidBodyB().getCompanionId() >= 0)) {
/* 1582: 836 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(constraint.getRigidBodyB().getCompanionId())).readVelocity();
/* 1583:     */          }
/* 1584:     */        }
/* 1585:     */        
/* 1587: 841 */        int numPoolConstraints = this.tmpSolverConstraintPool.size();
/* 1588: 842 */        for (j = 0; j < numPoolConstraints; j++) {
/* 1589: 843 */          SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(j));
/* 1590: 844 */          resolveSingleCollisionCombinedCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdB), solveManifold, infoGlobal);
/* 1591:     */        }
/* 1592:     */        
/* 1596: 850 */        int numFrictionPoolConstraints = this.tmpSolverFrictionConstraintPool.size();
/* 1597:     */        
/* 1598: 852 */        for (j = 0; j < numFrictionPoolConstraints; j++) {
/* 1599: 853 */          SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(this.orderFrictionConstraintPool.get(j));
/* 1600:     */          
/* 1601: 855 */          float totalImpulse = ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(solveManifold.frictionIndex)).appliedImpulse + ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(solveManifold.frictionIndex)).appliedPushImpulse;
/* 1602:     */          
/* 1604: 858 */          resolveSingleFrictionCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdB), solveManifold, infoGlobal, totalImpulse);
/* 1605:     */        }
/* 1606:     */      }
/* 1607:     */      
/* 1609:     */      int numPoolConstraints;
/* 1610:     */      
/* 1611: 865 */      if (infoGlobal.splitImpulse) {
/* 1612: 866 */        for (iteration = 0; iteration < infoGlobal.numIterations; iteration++)
/* 1613:     */        {
/* 1614: 868 */          numPoolConstraints = this.tmpSolverConstraintPool.size();
/* 1615:     */          
/* 1616: 870 */          for (int j = 0; j < numPoolConstraints; j++) {
/* 1617: 871 */            SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(j));
/* 1618:     */            
/* 1619: 873 */            resolveSplitPenetrationImpulseCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(solveManifold.solverBodyIdB), solveManifold, infoGlobal);
/* 1620:     */          }
/* 1621:     */        }
/* 1622:     */      }
/* 1623:     */      
/* 1627: 881 */      return 0.0F;
/* 1628:     */    }
/* 1629:     */    finally {
/* 1630: 884 */      BulletStats.popProfile();
/* 1631:     */    }
/* 1632:     */  }
/* 1633:     */  
/* 1634:     */  public float solveGroupCacheFriendly(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifoldPtr, int manifold_offset, int numManifolds, ObjectArrayList<TypedConstraint> constraints, int constraints_offset, int numConstraints, ContactSolverInfo infoGlobal, IDebugDraw debugDrawer) {
/* 1635: 889 */    solveGroupCacheFriendlySetup(bodies, numBodies, manifoldPtr, manifold_offset, numManifolds, constraints, constraints_offset, numConstraints, infoGlobal, debugDrawer);
/* 1636: 890 */    solveGroupCacheFriendlyIterations(bodies, numBodies, manifoldPtr, manifold_offset, numManifolds, constraints, constraints_offset, numConstraints, infoGlobal, debugDrawer);
/* 1637:     */    
/* 1638: 892 */    int numPoolConstraints = this.tmpSolverConstraintPool.size();
/* 1639: 893 */    for (int j = 0; j < numPoolConstraints; j++)
/* 1640:     */    {
/* 1641: 895 */      SolverConstraint solveManifold = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(j);
/* 1642: 896 */      ManifoldPoint pt = (ManifoldPoint)solveManifold.originalContactPoint;
/* 1643: 897 */      assert (pt != null);
/* 1644: 898 */      pt.appliedImpulse = solveManifold.appliedImpulse;
/* 1645: 899 */      pt.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(solveManifold.frictionIndex)).appliedImpulse;
/* 1646: 900 */      pt.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(solveManifold.frictionIndex + 1)).appliedImpulse;
/* 1647:     */    }
/* 1648:     */    
/* 1651: 905 */    if (infoGlobal.splitImpulse) {
/* 1652: 906 */      for (int i = 0; i < this.tmpSolverBodyPool.size(); i++) {
/* 1653: 907 */        ((SolverBody)this.tmpSolverBodyPool.getQuick(i)).writebackVelocity(infoGlobal.timeStep);
/* 1654: 908 */        this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(i));
/* 1655:     */      }
/* 1656:     */      
/* 1657:     */    } else {
/* 1658: 912 */      for (int i = 0; i < this.tmpSolverBodyPool.size(); i++) {
/* 1659: 913 */        ((SolverBody)this.tmpSolverBodyPool.getQuick(i)).writebackVelocity();
/* 1660: 914 */        this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(i));
/* 1661:     */      }
/* 1662:     */    }
/* 1663:     */    
/* 1675: 929 */    this.tmpSolverBodyPool.clear();
/* 1676:     */    
/* 1677: 931 */    for (int i = 0; i < this.tmpSolverConstraintPool.size(); i++) {
/* 1678: 932 */      this.constraintsPool.release(this.tmpSolverConstraintPool.getQuick(i));
/* 1679:     */    }
/* 1680: 934 */    this.tmpSolverConstraintPool.clear();
/* 1681:     */    
/* 1682: 936 */    for (int i = 0; i < this.tmpSolverFrictionConstraintPool.size(); i++) {
/* 1683: 937 */      this.constraintsPool.release(this.tmpSolverFrictionConstraintPool.getQuick(i));
/* 1684:     */    }
/* 1685: 939 */    this.tmpSolverFrictionConstraintPool.clear();
/* 1686:     */    
/* 1687: 941 */    return 0.0F;
/* 1688:     */  }
/* 1689:     */  
/* 1693:     */  public float solveGroup(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifoldPtr, int manifold_offset, int numManifolds, ObjectArrayList<TypedConstraint> constraints, int constraints_offset, int numConstraints, ContactSolverInfo infoGlobal, IDebugDraw debugDrawer, Dispatcher dispatcher)
/* 1694:     */  {
/* 1695: 949 */    BulletStats.pushProfile("solveGroup");
/* 1696:     */    try
/* 1697:     */    {
/* 1698: 952 */      if ((infoGlobal.solverMode & 0x8) != 0)
/* 1699:     */      {
/* 1701: 955 */        assert (bodies != null);
/* 1702: 956 */        assert (numBodies != 0);
/* 1703: 957 */        float value = solveGroupCacheFriendly(bodies, numBodies, manifoldPtr, manifold_offset, numManifolds, constraints, constraints_offset, numConstraints, infoGlobal, debugDrawer);
/* 1704: 958 */        return value;
/* 1705:     */      }
/* 1706:     */      
/* 1707: 961 */      ContactSolverInfo info = new ContactSolverInfo(infoGlobal);
/* 1708:     */      
/* 1709: 963 */      int numiter = infoGlobal.numIterations;
/* 1710:     */      
/* 1711: 965 */      int totalPoints = 0;
/* 1712:     */      
/* 1714: 968 */      for (short j = 0; j < numManifolds; j = (short)(j + 1)) {
/* 1715: 969 */        PersistentManifold manifold = (PersistentManifold)manifoldPtr.getQuick(manifold_offset + j);
/* 1716: 970 */        prepareConstraints(manifold, info, debugDrawer);
/* 1717:     */        
/* 1718: 972 */        for (short p = 0; p < ((PersistentManifold)manifoldPtr.getQuick(manifold_offset + j)).getNumContacts(); p = (short)(p + 1)) {
/* 1719: 973 */          this.gOrder[totalPoints].manifoldIndex = j;
/* 1720: 974 */          this.gOrder[totalPoints].pointIndex = p;
/* 1721: 975 */          totalPoints++;
/* 1722:     */        }
/* 1723:     */      }
/* 1724:     */      
/* 1728: 982 */      for (int j = 0; j < numConstraints; j++) {
/* 1729: 983 */        TypedConstraint constraint = (TypedConstraint)constraints.getQuick(constraints_offset + j);
/* 1730: 984 */        constraint.buildJacobian();
/* 1731:     */      }
/* 1732:     */      
/* 1734:     */      int j;
/* 1735:     */      
/* 1737: 991 */      for (int iteration = 0; iteration < numiter; iteration++)
/* 1738:     */      {
/* 1739: 993 */        if (((infoGlobal.solverMode & 0x1) != 0) && 
/* 1740: 994 */          ((iteration & 0x7) == 0)) {
/* 1741: 995 */          for (int j = 0; j < totalPoints; j++)
/* 1742:     */          {
/* 1743: 997 */            OrderIndex tmp = this.gOrder[j];
/* 1744: 998 */            int swapi = randInt2(j + 1);
/* 1745: 999 */            this.gOrder[j] = this.gOrder[swapi];
/* 1746:1000 */            this.gOrder[swapi] = tmp;
/* 1747:     */          }
/* 1748:     */        }
/* 1749:     */        
/* 1751:1005 */        for (j = 0; j < numConstraints; j++) {
/* 1752:1006 */          TypedConstraint constraint = (TypedConstraint)constraints.getQuick(constraints_offset + j);
/* 1753:1007 */          constraint.solveConstraint(info.timeStep);
/* 1754:     */        }
/* 1755:     */        
/* 1756:1010 */        for (j = 0; j < totalPoints; j++) {
/* 1757:1011 */          PersistentManifold manifold = (PersistentManifold)manifoldPtr.getQuick(manifold_offset + this.gOrder[j].manifoldIndex);
/* 1758:1012 */          solve((RigidBody)manifold.getBody0(), (RigidBody)manifold.getBody1(), manifold.getContactPoint(this.gOrder[j].pointIndex), info, iteration, debugDrawer);
/* 1759:     */        }
/* 1760:     */        
/* 1762:1016 */        for (j = 0; j < totalPoints; j++) {
/* 1763:1017 */          PersistentManifold manifold = (PersistentManifold)manifoldPtr.getQuick(manifold_offset + this.gOrder[j].manifoldIndex);
/* 1764:1018 */          solveFriction((RigidBody)manifold.getBody0(), (RigidBody)manifold.getBody1(), manifold.getContactPoint(this.gOrder[j].pointIndex), info, iteration, debugDrawer);
/* 1765:     */        }
/* 1766:     */      }
/* 1767:     */      
/* 1771:1025 */      return 0.0F;
/* 1772:     */    }
/* 1773:     */    finally {
/* 1774:1028 */      BulletStats.popProfile();
/* 1775:     */    }
/* 1776:     */  }
/* 1777:     */  
/* 1778:     */  protected void prepareConstraints(PersistentManifold arg1, ContactSolverInfo arg2, IDebugDraw arg3) {
/* 1779:1033 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Matrix3f();RigidBody body0 = (RigidBody)manifoldPtr.getBody0();
/* 1780:1034 */      RigidBody body1 = (RigidBody)manifoldPtr.getBody1();
/* 1781:     */      
/* 1787:1041 */      int numpoints = manifoldPtr.getNumContacts();
/* 1788:     */      
/* 1789:1043 */      BulletStats.gTotalContactPoints += numpoints;
/* 1790:     */      
/* 1791:1045 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 1792:1046 */      Matrix3f tmpMat3 = localStack.get$javax$vecmath$Matrix3f();
/* 1793:     */      
/* 1794:1048 */      Vector3f pos1 = localStack.get$javax$vecmath$Vector3f();
/* 1795:1049 */      Vector3f pos2 = localStack.get$javax$vecmath$Vector3f();
/* 1796:1050 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 1797:1051 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 1798:1052 */      Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
/* 1799:1053 */      Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
/* 1800:1054 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 1801:1055 */      Vector3f totalImpulse = localStack.get$javax$vecmath$Vector3f();
/* 1802:1056 */      Vector3f torqueAxis0 = localStack.get$javax$vecmath$Vector3f();
/* 1803:1057 */      Vector3f torqueAxis1 = localStack.get$javax$vecmath$Vector3f();
/* 1804:1058 */      Vector3f ftorqueAxis0 = localStack.get$javax$vecmath$Vector3f();
/* 1805:1059 */      Vector3f ftorqueAxis1 = localStack.get$javax$vecmath$Vector3f();
/* 1806:     */      
/* 1807:1061 */      for (int i = 0; i < numpoints; i++) {
/* 1808:1062 */        ManifoldPoint cp = manifoldPtr.getContactPoint(i);
/* 1809:1063 */        if (cp.getDistance() <= 0.0F) {
/* 1810:1064 */          cp.getPositionWorldOnA(pos1);
/* 1811:1065 */          cp.getPositionWorldOnB(pos2);
/* 1812:     */          
/* 1813:1067 */          rel_pos1.sub(pos1, body0.getCenterOfMassPosition(tmpVec));
/* 1814:1068 */          rel_pos2.sub(pos2, body1.getCenterOfMassPosition(tmpVec));
/* 1815:     */          
/* 1817:1071 */          Matrix3f mat1 = body0.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 1818:1072 */          mat1.transpose();
/* 1819:     */          
/* 1820:1074 */          Matrix3f mat2 = body1.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 1821:1075 */          mat2.transpose();
/* 1822:     */          
/* 1823:1077 */          JacobianEntry jac = (JacobianEntry)this.jacobiansPool.get();
/* 1824:1078 */          jac.init(mat1, mat2, rel_pos1, rel_pos2, cp.normalWorldOnB, body0.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body0.getInvMass(), body1.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body1.getInvMass());
/* 1825:     */          
/* 1829:1083 */          float jacDiagAB = jac.getDiagonal();
/* 1830:1084 */          this.jacobiansPool.release(jac);
/* 1831:     */          
/* 1832:1086 */          ConstraintPersistentData cpd = (ConstraintPersistentData)cp.userPersistentData;
/* 1833:1087 */          if (cpd != null)
/* 1834:     */          {
/* 1835:1089 */            cpd.persistentLifeTime += 1;
/* 1836:1090 */            if (cpd.persistentLifeTime != cp.getLifeTime())
/* 1837:     */            {
/* 1839:1093 */              cpd.reset();
/* 1840:1094 */              cpd.persistentLifeTime = cp.getLifeTime();
/* 1842:     */            }
/* 1843:     */            
/* 1846:     */          }
/* 1847:     */          else
/* 1848:     */          {
/* 1851:1105 */            cpd = new ConstraintPersistentData();
/* 1852:     */            
/* 1854:1108 */            this.totalCpd += 1;
/* 1855:     */            
/* 1856:1110 */            cp.userPersistentData = cpd;
/* 1857:1111 */            cpd.persistentLifeTime = cp.getLifeTime();
/* 1858:     */          }
/* 1859:     */          
/* 1860:1114 */          assert (cpd != null);
/* 1861:     */          
/* 1862:1116 */          cpd.jacDiagABInv = (1.0F / jacDiagAB);
/* 1863:     */          
/* 1867:1121 */          cpd.frictionSolverFunc = this.frictionDispatch[body0.frictionSolverType][body1.frictionSolverType];
/* 1868:1122 */          cpd.contactSolverFunc = this.contactDispatch[body0.contactSolverType][body1.contactSolverType];
/* 1869:     */          
/* 1870:1124 */          body0.getVelocityInLocalPoint(rel_pos1, vel1);
/* 1871:1125 */          body1.getVelocityInLocalPoint(rel_pos2, vel2);
/* 1872:1126 */          vel.sub(vel1, vel2);
/* 1873:     */          
/* 1875:1129 */          float rel_vel = cp.normalWorldOnB.dot(vel);
/* 1876:     */          
/* 1877:1131 */          float combinedRestitution = cp.combinedRestitution;
/* 1878:     */          
/* 1879:1133 */          cpd.penetration = cp.getDistance();
/* 1880:1134 */          cpd.friction = cp.combinedFriction;
/* 1881:1135 */          cpd.restitution = restitutionCurve(rel_vel, combinedRestitution);
/* 1882:1136 */          if (cpd.restitution <= 0.0F) {
/* 1883:1137 */            cpd.restitution = 0.0F;
/* 1884:     */          }
/* 1885:     */          
/* 1889:1143 */          float penVel = -cpd.penetration / info.timeStep;
/* 1890:     */          
/* 1891:1145 */          if (cpd.restitution > penVel) {
/* 1892:1146 */            cpd.penetration = 0.0F;
/* 1893:     */          }
/* 1894:     */          
/* 1895:1149 */          float relaxation = info.damping;
/* 1896:1150 */          if ((info.solverMode & 0x4) != 0) {
/* 1897:1151 */            cpd.appliedImpulse *= relaxation;
/* 1898:     */          }
/* 1899:     */          else {
/* 1900:1154 */            cpd.appliedImpulse = 0.0F;
/* 1901:     */          }
/* 1902:     */          
/* 1904:1158 */          cpd.prevAppliedImpulse = cpd.appliedImpulse;
/* 1905:     */          
/* 1907:1161 */          TransformUtil.planeSpace1(cp.normalWorldOnB, cpd.frictionWorldTangential0, cpd.frictionWorldTangential1);
/* 1908:     */          
/* 1911:1165 */          cpd.accumulatedTangentImpulse0 = 0.0F;
/* 1912:1166 */          cpd.accumulatedTangentImpulse1 = 0.0F;
/* 1913:     */          
/* 1914:1168 */          float denom0 = body0.computeImpulseDenominator(pos1, cpd.frictionWorldTangential0);
/* 1915:1169 */          float denom1 = body1.computeImpulseDenominator(pos2, cpd.frictionWorldTangential0);
/* 1916:1170 */          float denom = relaxation / (denom0 + denom1);
/* 1917:1171 */          cpd.jacDiagABInvTangent0 = denom;
/* 1918:     */          
/* 1919:1173 */          denom0 = body0.computeImpulseDenominator(pos1, cpd.frictionWorldTangential1);
/* 1920:1174 */          denom1 = body1.computeImpulseDenominator(pos2, cpd.frictionWorldTangential1);
/* 1921:1175 */          denom = relaxation / (denom0 + denom1);
/* 1922:1176 */          cpd.jacDiagABInvTangent1 = denom;
/* 1923:     */          
/* 1930:1184 */          totalImpulse.scale(cpd.appliedImpulse, cp.normalWorldOnB);
/* 1931:     */          
/* 1934:1188 */          torqueAxis0.cross(rel_pos1, cp.normalWorldOnB);
/* 1935:     */          
/* 1936:1190 */          cpd.angularComponentA.set(torqueAxis0);
/* 1937:1191 */          body0.getInvInertiaTensorWorld(tmpMat3).transform(cpd.angularComponentA);
/* 1938:     */          
/* 1939:1193 */          torqueAxis1.cross(rel_pos2, cp.normalWorldOnB);
/* 1940:     */          
/* 1941:1195 */          cpd.angularComponentB.set(torqueAxis1);
/* 1942:1196 */          body1.getInvInertiaTensorWorld(tmpMat3).transform(cpd.angularComponentB);
/* 1943:     */          
/* 1945:1199 */          ftorqueAxis0.cross(rel_pos1, cpd.frictionWorldTangential0);
/* 1946:     */          
/* 1947:1201 */          cpd.frictionAngularComponent0A.set(ftorqueAxis0);
/* 1948:1202 */          body0.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent0A);
/* 1949:     */          
/* 1951:1205 */          ftorqueAxis1.cross(rel_pos1, cpd.frictionWorldTangential1);
/* 1952:     */          
/* 1953:1207 */          cpd.frictionAngularComponent1A.set(ftorqueAxis1);
/* 1954:1208 */          body0.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent1A);
/* 1955:     */          
/* 1957:1211 */          ftorqueAxis0.cross(rel_pos2, cpd.frictionWorldTangential0);
/* 1958:     */          
/* 1959:1213 */          cpd.frictionAngularComponent0B.set(ftorqueAxis0);
/* 1960:1214 */          body1.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent0B);
/* 1961:     */          
/* 1963:1217 */          ftorqueAxis1.cross(rel_pos2, cpd.frictionWorldTangential1);
/* 1964:     */          
/* 1965:1219 */          cpd.frictionAngularComponent1B.set(ftorqueAxis1);
/* 1966:1220 */          body1.getInvInertiaTensorWorld(tmpMat3).transform(cpd.frictionAngularComponent1B);
/* 1967:     */          
/* 1972:1226 */          body0.applyImpulse(totalImpulse, rel_pos1);
/* 1973:     */          
/* 1974:1228 */          tmpVec.negate(totalImpulse);
/* 1975:1229 */          body1.applyImpulse(tmpVec, rel_pos2);
/* 1976:     */        }
/* 1977:     */      }
/* 1978:     */    }
/* 1979:     */    finally {
/* 1980:1234 */      .Stack tmp1077_1075 = localStack;tmp1077_1075.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp1081_1077 = tmp1077_1075;tmp1081_1077.pop$javax$vecmath$Vector3f();tmp1081_1077.pop$javax$vecmath$Matrix3f();
/* 1981:     */    } }
/* 1982:     */  
/* 1983:1237 */  public float solveCombinedContactFriction(RigidBody body0, RigidBody body1, ManifoldPoint cp, ContactSolverInfo info, int iter, IDebugDraw debugDrawer) { float maxImpulse = 0.0F;
/* 1984:     */    
/* 1986:1240 */    if (cp.getDistance() <= 0.0F)
/* 1987:     */    {
/* 1989:1243 */      float impulse = ContactConstraint.resolveSingleCollisionCombined(body0, body1, cp, info);
/* 1990:     */      
/* 1991:1245 */      if (maxImpulse < impulse) {
/* 1992:1246 */        maxImpulse = impulse;
/* 1993:     */      }
/* 1994:     */    }
/* 1995:     */    
/* 1997:1251 */    return maxImpulse;
/* 1998:     */  }
/* 1999:     */  
/* 2000:     */  protected float solve(RigidBody body0, RigidBody body1, ManifoldPoint cp, ContactSolverInfo info, int iter, IDebugDraw debugDrawer) {
/* 2001:1255 */    float maxImpulse = 0.0F;
/* 2002:     */    
/* 2004:1258 */    if (cp.getDistance() <= 0.0F)
/* 2005:     */    {
/* 2006:1260 */      ConstraintPersistentData cpd = (ConstraintPersistentData)cp.userPersistentData;
/* 2007:1261 */      float impulse = cpd.contactSolverFunc.resolveContact(body0, body1, cp, info);
/* 2008:     */      
/* 2009:1263 */      if (maxImpulse < impulse) {
/* 2010:1264 */        maxImpulse = impulse;
/* 2011:     */      }
/* 2012:     */    }
/* 2013:     */    
/* 2016:1270 */    return maxImpulse;
/* 2017:     */  }
/* 2018:     */  
/* 2019:     */  protected float solveFriction(RigidBody body0, RigidBody body1, ManifoldPoint cp, ContactSolverInfo info, int iter, IDebugDraw debugDrawer)
/* 2020:     */  {
/* 2021:1275 */    if (cp.getDistance() <= 0.0F) {
/* 2022:1276 */      ConstraintPersistentData cpd = (ConstraintPersistentData)cp.userPersistentData;
/* 2023:1277 */      cpd.frictionSolverFunc.resolveContact(body0, body1, cp, info);
/* 2024:     */    }
/* 2025:     */    
/* 2026:1280 */    return 0.0F;
/* 2027:     */  }
/* 2028:     */  
/* 2029:     */  public void reset()
/* 2030:     */  {
/* 2031:1285 */    this.btSeed2 = 0L;
/* 2032:     */  }
/* 2033:     */  
/* 2037:     */  public void setContactSolverFunc(ContactSolverFunc func, int type0, int type1)
/* 2038:     */  {
/* 2039:1293 */    this.contactDispatch[type0][type1] = func;
/* 2040:     */  }
/* 2041:     */  
/* 2045:     */  public void setFrictionSolverFunc(ContactSolverFunc func, int type0, int type1)
/* 2046:     */  {
/* 2047:1301 */    this.frictionDispatch[type0][type1] = func;
/* 2048:     */  }
/* 2049:     */  
/* 2050:     */  public void setRandSeed(long seed) {
/* 2051:1305 */    this.btSeed2 = seed;
/* 2052:     */  }
/* 2053:     */  
/* 2054:     */  public long getRandSeed() {
/* 2055:1309 */    return this.btSeed2;
/* 2056:     */  }
/* 2057:     */  
/* 2058:     */  private static class OrderIndex
/* 2059:     */  {
/* 2060:     */    public int manifoldIndex;
/* 2061:     */    public int pointIndex;
/* 2062:     */  }
/* 2063:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */