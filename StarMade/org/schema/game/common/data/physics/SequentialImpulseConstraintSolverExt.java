/*    1:     */package org.schema.game.common.data.physics;
/*    2:     */
/*    3:     */import com.bulletphysics.BulletGlobals;
/*    4:     */import com.bulletphysics.BulletStats;
/*    5:     */import com.bulletphysics.collision.broadphase.Dispatcher;
/*    6:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*    7:     */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*    8:     */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*    9:     */import com.bulletphysics.dynamics.RigidBody;
/*   10:     */import com.bulletphysics.dynamics.constraintsolver.ConstraintPersistentData;
/*   11:     */import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*   12:     */import com.bulletphysics.dynamics.constraintsolver.ContactConstraint;
/*   13:     */import com.bulletphysics.dynamics.constraintsolver.ContactSolverFunc;
/*   14:     */import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*   15:     */import com.bulletphysics.dynamics.constraintsolver.JacobianEntry;
/*   16:     */import com.bulletphysics.dynamics.constraintsolver.SolverBody;
/*   17:     */import com.bulletphysics.dynamics.constraintsolver.SolverConstraint;
/*   18:     */import com.bulletphysics.dynamics.constraintsolver.SolverConstraintType;
/*   19:     */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   20:     */import com.bulletphysics.linearmath.IDebugDraw;
/*   21:     */import com.bulletphysics.linearmath.MiscUtil;
/*   22:     */import com.bulletphysics.linearmath.Transform;
/*   23:     */import com.bulletphysics.linearmath.TransformUtil;
/*   24:     */import com.bulletphysics.util.IntArrayList;
/*   25:     */import com.bulletphysics.util.ObjectArrayList;
/*   26:     */import com.bulletphysics.util.ObjectPool;
/*   27:     */import java.util.Arrays;
/*   28:     */import javax.vecmath.Matrix3f;
/*   29:     */import javax.vecmath.Vector3f;
/*   30:     */
/*   49:     */public class SequentialImpulseConstraintSolverExt
/*   50:     */  extends ConstraintSolver
/*   51:     */{
/*   52:     */  private static final int MAX_CONTACT_SOLVER_TYPES = 4;
/*   53:     */  private static final int SEQUENTIAL_IMPULSE_MAX_SOLVER_POINTS = 128;
/*   54:  54 */  private int[] gOrder = new int[256];
/*   55:     */  
/*   56:  56 */  private int totalCpd = 0;
/*   57:     */  
/*   61:  61 */  private final ObjectPool bodiesPool = ObjectPool.get(SolverBody.class);
/*   62:  62 */  private final ObjectPool constraintsPool = ObjectPool.get(SolverConstraint.class);
/*   63:  63 */  private final ObjectPool jacobiansPool = ObjectPool.get(JacobianEntry.class);
/*   64:     */  
/*   65:  65 */  private final ObjectArrayList tmpSolverBodyPool = new ObjectArrayList();
/*   66:  66 */  private final ObjectArrayList tmpSolverConstraintPool = new ObjectArrayList();
/*   67:  67 */  private final ObjectArrayList tmpSolverFrictionConstraintPool = new ObjectArrayList();
/*   68:  68 */  private final IntArrayList orderTmpConstraintPool = new IntArrayList();
/*   69:  69 */  private final IntArrayList orderFrictionConstraintPool = new IntArrayList();
/*   70:     */  
/*   71:  71 */  protected final ContactSolverFunc[][] contactDispatch = new ContactSolverFunc[4][4];
/*   72:  72 */  protected final ContactSolverFunc[][] frictionDispatch = new ContactSolverFunc[4][4];
/*   73:     */  
/*   75:  75 */  protected long btSeed2 = 0L;
/*   76:     */  
/*   77:     */  private final SequentialImpulseContraintSolverExtVariableSet v;
/*   78:     */  
/*   79:     */  public SequentialImpulseConstraintSolverExt()
/*   80:     */  {
/*   81:  81 */    this.v = new SequentialImpulseContraintSolverExtVariableSet();
/*   82:     */    
/*   83:  83 */    BulletGlobals.setContactDestroyedCallback(new SequentialImpulseConstraintSolverExt.1(this));
/*   84:     */    
/*   96:  96 */    for (int i = 0; i < 4; i++) {
/*   97:  97 */      for (int j = 0; j < 4; j++) {
/*   98:  98 */        this.contactDispatch[i][j] = ContactConstraint.resolveSingleCollision;
/*   99:  99 */        this.frictionDispatch[i][j] = ContactConstraint.resolveSingleFriction;
/*  100:     */      }
/*  101:     */    }
/*  102:     */  }
/*  103:     */  
/*  104:     */  public long rand2() {
/*  105: 105 */    this.btSeed2 = (1664525L * this.btSeed2 + 1013904223L);
/*  106: 106 */    return this.btSeed2;
/*  107:     */  }
/*  108:     */  
/*  110:     */  public int randInt2(int paramInt)
/*  111:     */  {
/*  112: 112 */    long l1 = paramInt;
/*  113: 113 */    long l2 = rand2();
/*  114:     */    
/*  117: 117 */    if (l1 <= 65536L) {
/*  118: 118 */      l2 ^= l2 >>> 16;
/*  119: 119 */      if (l1 <= 256L) {
/*  120: 120 */        l2 ^= l2 >>> 8;
/*  121: 121 */        if (l1 <= 16L) {
/*  122: 122 */          l2 ^= l2 >>> 4;
/*  123: 123 */          if (l1 <= 4L) {
/*  124: 124 */            l2 ^= l2 >>> 2;
/*  125: 125 */            if (l1 <= 2L) {
/*  126: 126 */              l2 ^= l2 >>> 1;
/*  127:     */            }
/*  128:     */          }
/*  129:     */        }
/*  130:     */      }
/*  131:     */    }
/*  132:     */    
/*  134: 134 */    return (int)Math.abs(l2 % l1);
/*  135:     */  }
/*  136:     */  
/*  137:     */  private void initSolverBody(SolverBody paramSolverBody, CollisionObject paramCollisionObject) { RigidBody localRigidBody;
/*  138: 138 */    if ((localRigidBody = RigidBody.upcast(paramCollisionObject)) != null) {
/*  139: 139 */      localRigidBody.getAngularVelocity(paramSolverBody.angularVelocity);
/*  140: 140 */      paramSolverBody.centerOfMassPosition.set(paramCollisionObject.getWorldTransform(this.v.centerOfMassTrans).origin);
/*  141: 141 */      paramSolverBody.friction = paramCollisionObject.getFriction();
/*  142: 142 */      paramSolverBody.invMass = localRigidBody.getInvMass();
/*  143: 143 */      localRigidBody.getLinearVelocity(paramSolverBody.linearVelocity);
/*  144: 144 */      paramSolverBody.originalBody = localRigidBody;
/*  145: 145 */      paramSolverBody.angularFactor = localRigidBody.getAngularFactor();
/*  146:     */    }
/*  147:     */    else {
/*  148: 148 */      paramSolverBody.angularVelocity.set(0.0F, 0.0F, 0.0F);
/*  149: 149 */      paramSolverBody.centerOfMassPosition.set(paramCollisionObject.getWorldTransform(this.v.centerOfMassTrans).origin);
/*  150: 150 */      paramSolverBody.friction = paramCollisionObject.getFriction();
/*  151: 151 */      paramSolverBody.invMass = 0.0F;
/*  152: 152 */      paramSolverBody.linearVelocity.set(0.0F, 0.0F, 0.0F);
/*  153: 153 */      paramSolverBody.originalBody = null;
/*  154: 154 */      paramSolverBody.angularFactor = 1.0F;
/*  155:     */    }
/*  156:     */    
/*  157: 157 */    paramSolverBody.pushVelocity.set(0.0F, 0.0F, 0.0F);
/*  158: 158 */    paramSolverBody.turnVelocity.set(0.0F, 0.0F, 0.0F);
/*  159:     */  }
/*  160:     */  
/*  161:     */  private float restitutionCurve(float paramFloat1, float paramFloat2)
/*  162:     */  {
/*  163: 163 */    return paramFloat2 * -paramFloat1;
/*  164:     */  }
/*  165:     */  
/*  170:     */  private void resolveSplitPenetrationImpulseCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo)
/*  171:     */  {
/*  172: 172 */    if (paramSolverConstraint.penetration < paramContactSolverInfo.splitImpulsePenetrationThreshold) {
/*  173: 173 */      BulletStats.gNumSplitImpulseRecoveries += 1;
/*  174:     */      
/*  183: 183 */      float f1 = paramSolverConstraint.contactNormal.dot(paramSolverBody1.pushVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.turnVelocity);
/*  184: 184 */      float f2 = paramSolverConstraint.contactNormal.dot(paramSolverBody2.pushVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.turnVelocity);
/*  185:     */      
/*  186: 186 */      f1 -= f2;
/*  187:     */      
/*  188: 188 */      paramContactSolverInfo = -paramSolverConstraint.penetration * paramContactSolverInfo.erp2 / paramContactSolverInfo.timeStep;
/*  189:     */      
/*  191: 191 */      f1 = paramSolverConstraint.restitution - f1;
/*  192:     */      
/*  193: 193 */      paramContactSolverInfo *= paramSolverConstraint.jacDiagABInv;
/*  194: 194 */      f1 *= paramSolverConstraint.jacDiagABInv;
/*  195: 195 */      paramContactSolverInfo += f1;
/*  196:     */      
/*  199: 199 */      paramContactSolverInfo = (f1 = paramSolverConstraint.appliedPushImpulse) + paramContactSolverInfo;
/*  200: 200 */      paramSolverConstraint.appliedPushImpulse = (0.0F > paramContactSolverInfo ? 0.0F : paramContactSolverInfo);
/*  201:     */      
/*  202: 202 */      paramContactSolverInfo = paramSolverConstraint.appliedPushImpulse - f1;
/*  203:     */      
/*  204:     */      Vector3f localVector3f;
/*  205:     */      
/*  206: 206 */      (localVector3f = this.v.tmp).scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
/*  207: 207 */      paramSolverBody1.internalApplyPushImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramContactSolverInfo);
/*  208:     */      
/*  209: 209 */      localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
/*  210: 210 */      paramSolverBody2.internalApplyPushImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramContactSolverInfo);
/*  211:     */    }
/*  212:     */  }
/*  213:     */  
/*  239:     */  private float resolveSingleCollisionCombinedCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo)
/*  240:     */  {
/*  241: 241 */    float f1 = paramSolverConstraint.contactNormal.dot(paramSolverBody1.linearVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.angularVelocity);
/*  242: 242 */    float f2 = paramSolverConstraint.contactNormal.dot(paramSolverBody2.linearVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.angularVelocity);
/*  243:     */    
/*  244: 244 */    f1 -= f2;
/*  245:     */    
/*  246: 246 */    f2 = 0.0F;
/*  247: 247 */    if ((!paramContactSolverInfo.splitImpulse) || (paramSolverConstraint.penetration > paramContactSolverInfo.splitImpulsePenetrationThreshold)) {
/*  248: 248 */      f2 = -paramSolverConstraint.penetration * paramContactSolverInfo.erp / paramContactSolverInfo.timeStep;
/*  249:     */    }
/*  250:     */    
/*  251: 251 */    paramContactSolverInfo = paramSolverConstraint.restitution - f1;
/*  252:     */    
/*  253: 253 */    f1 = f2 * paramSolverConstraint.jacDiagABInv;
/*  254: 254 */    paramContactSolverInfo *= paramSolverConstraint.jacDiagABInv;
/*  255: 255 */    paramContactSolverInfo = f1 + paramContactSolverInfo;
/*  256:     */    
/*  260: 260 */    paramContactSolverInfo = (f1 = paramSolverConstraint.appliedImpulse) + paramContactSolverInfo;
/*  261: 261 */    paramSolverConstraint.appliedImpulse = (0.0F > paramContactSolverInfo ? 0.0F : paramContactSolverInfo);
/*  262:     */    
/*  263: 263 */    paramContactSolverInfo = paramSolverConstraint.appliedImpulse - f1;
/*  264:     */    
/*  265:     */    Vector3f localVector3f;
/*  266:     */    
/*  267: 267 */    (localVector3f = this.v.tmp2).scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
/*  268: 268 */    paramSolverBody1.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramContactSolverInfo);
/*  269:     */    
/*  273: 273 */    localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
/*  274: 274 */    paramSolverBody2.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramContactSolverInfo);
/*  275:     */    
/*  280: 280 */    return paramContactSolverInfo;
/*  281:     */  }
/*  282:     */  
/*  287:     */  private float resolveSingleFrictionCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo, float paramFloat)
/*  288:     */  {
/*  289: 289 */    paramContactSolverInfo = paramSolverConstraint.friction;
/*  290:     */    
/*  291: 291 */    paramContactSolverInfo = paramFloat * paramContactSolverInfo;
/*  292:     */    
/*  293: 293 */    if (paramFloat > 0.0F)
/*  294:     */    {
/*  300: 300 */      paramFloat = paramSolverConstraint.contactNormal.dot(paramSolverBody1.linearVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.angularVelocity);
/*  301: 301 */      float f = paramSolverConstraint.contactNormal.dot(paramSolverBody2.linearVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.angularVelocity);
/*  302:     */      
/*  305: 305 */      paramFloat = -(paramFloat - f) * paramSolverConstraint.jacDiagABInv;
/*  306:     */      
/*  308: 308 */      f = paramSolverConstraint.appliedImpulse;
/*  309: 309 */      paramSolverConstraint.appliedImpulse = (f + paramFloat);
/*  310:     */      
/*  311: 311 */      if (paramContactSolverInfo < paramSolverConstraint.appliedImpulse) {
/*  312: 312 */        paramSolverConstraint.appliedImpulse = paramContactSolverInfo;
/*  314:     */      }
/*  315: 315 */      else if (paramSolverConstraint.appliedImpulse < -paramContactSolverInfo) {
/*  316: 316 */        paramSolverConstraint.appliedImpulse = (-paramContactSolverInfo);
/*  317:     */      }
/*  318:     */      
/*  319: 319 */      paramFloat = paramSolverConstraint.appliedImpulse - f;
/*  320:     */      
/*  328:     */      Vector3f localVector3f;
/*  329:     */      
/*  337: 337 */      (localVector3f = this.v.tmp3).scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
/*  338: 338 */      paramSolverBody1.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramFloat);
/*  339:     */      
/*  340: 340 */      localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
/*  341: 341 */      paramSolverBody2.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramFloat);
/*  342:     */    }
/*  343: 343 */    return 0.0F;
/*  344:     */  }
/*  345:     */  
/*  346:     */  protected void addFrictionConstraint(Vector3f paramVector3f1, int paramInt1, int paramInt2, int paramInt3, ManifoldPoint paramManifoldPoint, Vector3f paramVector3f2, Vector3f paramVector3f3, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, float paramFloat) {
/*  347: 347 */    paramCollisionObject1 = RigidBody.upcast(paramCollisionObject1);
/*  348: 348 */    paramCollisionObject2 = RigidBody.upcast(paramCollisionObject2);
/*  349:     */    
/*  350: 350 */    SolverConstraint localSolverConstraint = (SolverConstraint)this.constraintsPool.get();
/*  351: 351 */    this.tmpSolverFrictionConstraintPool.add(localSolverConstraint);
/*  352:     */    
/*  353: 353 */    localSolverConstraint.contactNormal.set(paramVector3f1);
/*  354:     */    
/*  355: 355 */    localSolverConstraint.solverBodyIdA = paramInt1;
/*  356: 356 */    localSolverConstraint.solverBodyIdB = paramInt2;
/*  357: 357 */    localSolverConstraint.constraintType = SolverConstraintType.SOLVER_FRICTION_1D;
/*  358: 358 */    localSolverConstraint.frictionIndex = paramInt3;
/*  359:     */    
/*  360: 360 */    localSolverConstraint.friction = paramManifoldPoint.combinedFriction;
/*  361: 361 */    localSolverConstraint.originalContactPoint = null;
/*  362:     */    
/*  363: 363 */    localSolverConstraint.appliedImpulse = 0.0F;
/*  364: 364 */    localSolverConstraint.appliedPushImpulse = 0.0F;
/*  365: 365 */    localSolverConstraint.penetration = 0.0F;
/*  366:     */    
/*  367: 367 */    paramInt1 = this.v.ftorqueAxis1;
/*  368: 368 */    paramInt2 = this.v.tmpMat;
/*  369:     */    
/*  371: 371 */    paramInt1.cross(paramVector3f2, localSolverConstraint.contactNormal);
/*  372: 372 */    localSolverConstraint.relpos1CrossNormal.set(paramInt1);
/*  373: 373 */    if (paramCollisionObject1 != null) {
/*  374: 374 */      localSolverConstraint.angularComponentA.set(paramInt1);
/*  375:     */      
/*  376: 376 */      paramCollisionObject1.getInvInertiaTensorWorld(paramInt2).transform(localSolverConstraint.angularComponentA);
/*  377:     */    }
/*  378:     */    else {
/*  379: 379 */      localSolverConstraint.angularComponentA.set(0.0F, 0.0F, 0.0F);
/*  380:     */    }
/*  381:     */    
/*  383: 383 */    paramInt1.cross(paramVector3f3, localSolverConstraint.contactNormal);
/*  384: 384 */    localSolverConstraint.relpos2CrossNormal.set(paramInt1);
/*  385: 385 */    if (paramCollisionObject2 != null) {
/*  386: 386 */      localSolverConstraint.angularComponentB.set(paramInt1);
/*  387:     */      
/*  388: 388 */      paramCollisionObject2.getInvInertiaTensorWorld(paramInt2).transform(localSolverConstraint.angularComponentB);
/*  389:     */    }
/*  390:     */    else {
/*  391: 391 */      localSolverConstraint.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*  392:     */    }
/*  393:     */    
/*  399: 399 */    paramInt1 = this.v.vec;
/*  400: 400 */    paramInt2 = 0.0F;
/*  401: 401 */    paramInt3 = 0.0F;
/*  402: 402 */    if (paramCollisionObject1 != null) {
/*  403: 403 */      paramInt1.cross(localSolverConstraint.angularComponentA, paramVector3f2);
/*  404: 404 */      paramInt2 = paramCollisionObject1.getInvMass() + paramVector3f1.dot(paramInt1);
/*  405:     */    }
/*  406: 406 */    if (paramCollisionObject2 != null) {
/*  407: 407 */      paramInt1.cross(localSolverConstraint.angularComponentB, paramVector3f3);
/*  408: 408 */      paramInt3 = paramCollisionObject2.getInvMass() + paramVector3f1.dot(paramInt1);
/*  409:     */    }
/*  410:     */    
/*  412: 412 */    paramVector3f1 = paramFloat / (paramInt2 + paramInt3);
/*  413: 413 */    localSolverConstraint.jacDiagABInv = paramVector3f1;
/*  414:     */  }
/*  415:     */  
/*  416:     */  public float solveGroupCacheFriendlySetup(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw) {
/*  417: 417 */    BulletStats.pushProfile("solveGroupCacheFriendlySetup");
/*  418:     */    
/*  420: 420 */    if (paramInt5 + paramInt3 == 0)
/*  421:     */    {
/*  422: 422 */      paramObjectArrayList1 = 0.0F;
/*  423:     */      
/*  800: 800 */      BulletStats.popProfile();return 0.0F;
/*  801:     */    }
/*  802:     */    try
/*  803:     */    {
/*  804: 424 */      paramObjectArrayList1 = null;
/*  805: 425 */      paramInt1 = null;paramIDebugDraw = null;
/*  806:     */      
/*  830: 450 */      Transform localTransform = this.v.tmpTrans;
/*  831:     */      
/*  874: 494 */      Vector3f localVector3f1 = this.v.rel_pos1A;
/*  875: 495 */      Vector3f localVector3f2 = this.v.rel_pos2A;
/*  876:     */      
/*  877: 497 */      Vector3f localVector3f3 = this.v.pos1A;
/*  878: 498 */      Vector3f localVector3f4 = this.v.pos2A;
/*  879: 499 */      Vector3f localVector3f5 = this.v.velA;
/*  880: 500 */      Vector3f localVector3f6 = this.v.torqueAxis0A;
/*  881: 501 */      Vector3f localVector3f7 = this.v.torqueAxis1A;
/*  882: 502 */      Vector3f localVector3f8 = this.v.vel1A;
/*  883: 503 */      Vector3f localVector3f9 = this.v.vel2A;
/*  884:     */      
/*  886: 506 */      Vector3f localVector3f10 = this.v.vecA;
/*  887:     */      
/*  888: 508 */      Matrix3f localMatrix3f = this.v.tmpMatA;
/*  889:     */      
/*  890: 510 */      for (int i = 0; i < paramInt3; i++)
/*  891:     */      {
/*  892: 512 */        paramInt1 = (CollisionObject)(paramObjectArrayList1 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i)).getBody0();
/*  893: 513 */        paramIDebugDraw = (CollisionObject)paramObjectArrayList1.getBody1();
/*  894:     */        
/*  895: 515 */        int m = -1;
/*  896: 516 */        int n = -1;
/*  897:     */        
/*  898: 518 */        if (paramObjectArrayList1.getNumContacts() != 0) {
/*  899:     */          SolverBody localSolverBody;
/*  900: 520 */          if (paramInt1.getIslandTag() >= 0) {
/*  901: 521 */            if (paramInt1.getCompanionId() >= 0)
/*  902:     */            {
/*  903: 523 */              m = paramInt1.getCompanionId();
/*  904:     */            }
/*  905:     */            else {
/*  906: 526 */              m = this.tmpSolverBodyPool.size();
/*  907: 527 */              localSolverBody = (SolverBody)this.bodiesPool.get();
/*  908: 528 */              this.tmpSolverBodyPool.add(localSolverBody);
/*  909: 529 */              initSolverBody(localSolverBody, paramInt1);
/*  910: 530 */              paramInt1.setCompanionId(m);
/*  911:     */            }
/*  912:     */          }
/*  913:     */          else
/*  914:     */          {
/*  915: 535 */            m = this.tmpSolverBodyPool.size();
/*  916: 536 */            localSolverBody = (SolverBody)this.bodiesPool.get();
/*  917: 537 */            this.tmpSolverBodyPool.add(localSolverBody);
/*  918: 538 */            initSolverBody(localSolverBody, paramInt1);
/*  919:     */          }
/*  920:     */          
/*  921: 541 */          if (paramIDebugDraw.getIslandTag() >= 0) {
/*  922: 542 */            if (paramIDebugDraw.getCompanionId() >= 0) {
/*  923: 543 */              n = paramIDebugDraw.getCompanionId();
/*  924:     */            }
/*  925:     */            else {
/*  926: 546 */              n = this.tmpSolverBodyPool.size();
/*  927: 547 */              localSolverBody = (SolverBody)this.bodiesPool.get();
/*  928: 548 */              this.tmpSolverBodyPool.add(localSolverBody);
/*  929: 549 */              initSolverBody(localSolverBody, paramIDebugDraw);
/*  930: 550 */              paramIDebugDraw.setCompanionId(n);
/*  931:     */            }
/*  932:     */          }
/*  933:     */          else
/*  934:     */          {
/*  935: 555 */            n = this.tmpSolverBodyPool.size();
/*  936: 556 */            localSolverBody = (SolverBody)this.bodiesPool.get();
/*  937: 557 */            this.tmpSolverBodyPool.add(localSolverBody);
/*  938: 558 */            initSolverBody(localSolverBody, paramIDebugDraw);
/*  939:     */          }
/*  940:     */        }
/*  941:     */        
/*  944: 564 */        for (int i1 = 0; i1 < paramObjectArrayList1.getNumContacts(); i1++)
/*  945:     */        {
/*  946:     */          ManifoldPoint localManifoldPoint;
/*  947:     */          
/*  948: 568 */          if ((localManifoldPoint = paramObjectArrayList1.getContactPoint(i1)).getDistance() <= 0.0F) {
/*  949: 569 */            localManifoldPoint.getPositionWorldOnA(localVector3f3);
/*  950: 570 */            localManifoldPoint.getPositionWorldOnB(localVector3f4);
/*  951:     */            
/*  952: 572 */            localVector3f1.sub(localVector3f3, paramInt1.getWorldTransform(localTransform).origin);
/*  953: 573 */            localVector3f2.sub(localVector3f4, paramIDebugDraw.getWorldTransform(localTransform).origin);
/*  954:     */            
/*  955: 575 */            int i2 = this.tmpSolverConstraintPool.size();
/*  956:     */            
/*  961: 581 */            SolverConstraint localSolverConstraint2 = (SolverConstraint)this.constraintsPool.get();
/*  962: 582 */            this.tmpSolverConstraintPool.add(localSolverConstraint2);
/*  963: 583 */            RigidBody localRigidBody1 = RigidBody.upcast(paramInt1);
/*  964: 584 */            RigidBody localRigidBody2 = RigidBody.upcast(paramIDebugDraw);
/*  965:     */            
/*  966: 586 */            localSolverConstraint2.solverBodyIdA = m;
/*  967: 587 */            localSolverConstraint2.solverBodyIdB = n;
/*  968: 588 */            localSolverConstraint2.constraintType = SolverConstraintType.SOLVER_CONTACT_1D;
/*  969:     */            
/*  970: 590 */            localSolverConstraint2.originalContactPoint = localManifoldPoint;
/*  971:     */            
/*  973: 593 */            localVector3f6.cross(localVector3f1, localManifoldPoint.normalWorldOnB);
/*  974:     */            
/*  975: 595 */            if (localRigidBody1 != null) {
/*  976: 596 */              localSolverConstraint2.angularComponentA.set(localVector3f6);
/*  977: 597 */              localRigidBody1.getInvInertiaTensorWorld(localMatrix3f).transform(localSolverConstraint2.angularComponentA);
/*  979:     */            }
/*  980:     */            else
/*  981:     */            {
/*  982: 602 */              localSolverConstraint2.angularComponentA.set(0.0F, 0.0F, 0.0F);
/*  983:     */            }
/*  984:     */            
/*  985: 605 */            localVector3f7.cross(localVector3f2, localManifoldPoint.normalWorldOnB);
/*  986:     */            
/*  987: 607 */            if (localRigidBody2 != null) {
/*  988: 608 */              localSolverConstraint2.angularComponentB.set(localVector3f7);
/*  989: 609 */              localRigidBody2.getInvInertiaTensorWorld(localMatrix3f).transform(localSolverConstraint2.angularComponentB);
/*  991:     */            }
/*  992:     */            else
/*  993:     */            {
/*  994: 614 */              localSolverConstraint2.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*  995:     */            }
/*  996:     */            
/* 1002: 622 */            float f2 = 0.0F;
/* 1003: 623 */            float f3 = 0.0F;
/* 1004: 624 */            if (localRigidBody1 != null) {
/* 1005: 625 */              localVector3f10.cross(localSolverConstraint2.angularComponentA, localVector3f1);
/* 1006:     */              
/* 1007: 627 */              f2 = localRigidBody1.getInvMass() + localManifoldPoint.normalWorldOnB.dot(localVector3f10);
/* 1008:     */            }
/* 1009:     */            
/* 1010: 630 */            if (localRigidBody2 != null) {
/* 1011: 631 */              localVector3f10.cross(localSolverConstraint2.angularComponentB, localVector3f2);
/* 1012: 632 */              f3 = localRigidBody2.getInvMass() + localManifoldPoint.normalWorldOnB.dot(localVector3f10);
/* 1013:     */            }
/* 1014:     */            
/* 1017: 637 */            float f1 = 1.0F / (f2 + f3);
/* 1018: 638 */            localSolverConstraint2.jacDiagABInv = f1;
/* 1019:     */            
/* 1021: 641 */            localSolverConstraint2.contactNormal.set(localManifoldPoint.normalWorldOnB);
/* 1022: 642 */            localSolverConstraint2.relpos1CrossNormal.cross(localVector3f1, localManifoldPoint.normalWorldOnB);
/* 1023: 643 */            localSolverConstraint2.relpos2CrossNormal.cross(localVector3f2, localManifoldPoint.normalWorldOnB);
/* 1024:     */            
/* 1025: 645 */            if (localRigidBody1 != null) {
/* 1026: 646 */              localRigidBody1.getVelocityInLocalPoint(localVector3f1, localVector3f8);
/* 1027:     */            }
/* 1028:     */            else {
/* 1029: 649 */              localVector3f8.set(0.0F, 0.0F, 0.0F);
/* 1030:     */            }
/* 1031:     */            
/* 1032: 652 */            if (localRigidBody2 != null) {
/* 1033: 653 */              localRigidBody2.getVelocityInLocalPoint(localVector3f2, localVector3f9);
/* 1034:     */            }
/* 1035:     */            else {
/* 1036: 656 */              localVector3f9.set(0.0F, 0.0F, 0.0F);
/* 1037:     */            }
/* 1038:     */            
/* 1039: 659 */            localVector3f5.sub(localVector3f8, localVector3f9);
/* 1040:     */            
/* 1041: 661 */            f1 = localManifoldPoint.normalWorldOnB.dot(localVector3f5);
/* 1042:     */            
/* 1043: 663 */            localSolverConstraint2.penetration = Math.min(localManifoldPoint.getDistance() + paramContactSolverInfo.linearSlop, 0.0F);
/* 1044:     */            
/* 1046: 666 */            localSolverConstraint2.friction = localManifoldPoint.combinedFriction;
/* 1047: 667 */            localSolverConstraint2.restitution = restitutionCurve(f1, localManifoldPoint.combinedRestitution);
/* 1048: 668 */            if (localSolverConstraint2.restitution <= 0.0F) {
/* 1049: 669 */              localSolverConstraint2.restitution = 0.0F;
/* 1050:     */            }
/* 1051:     */            
/* 1052: 672 */            f2 = -localSolverConstraint2.penetration / paramContactSolverInfo.timeStep;
/* 1053:     */            
/* 1054: 674 */            if (localSolverConstraint2.restitution > f2) {
/* 1055: 675 */              localSolverConstraint2.penetration = 0.0F;
/* 1056:     */            }
/* 1057:     */            
/* 1058: 678 */            Vector3f localVector3f11 = this.v.tmp4;
/* 1059:     */            
/* 1061: 681 */            if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/* 1062: 682 */              localSolverConstraint2.appliedImpulse = (localManifoldPoint.appliedImpulse * paramContactSolverInfo.warmstartingFactor);
/* 1063: 683 */              if (localRigidBody1 != null) {
/* 1064: 684 */                localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint2.contactNormal);
/* 1065: 685 */                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint2.angularComponentA, localSolverConstraint2.appliedImpulse);
/* 1066:     */              }
/* 1067: 687 */              if (localRigidBody2 != null) {
/* 1068: 688 */                localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint2.contactNormal);
/* 1069: 689 */                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint2.angularComponentB, -localSolverConstraint2.appliedImpulse);
/* 1070:     */              }
/* 1071:     */            }
/* 1072:     */            else {
/* 1073: 693 */              localSolverConstraint2.appliedImpulse = 0.0F;
/* 1074:     */            }
/* 1075:     */            
/* 1076: 696 */            localSolverConstraint2.appliedPushImpulse = 0.0F;
/* 1077:     */            
/* 1078: 698 */            localSolverConstraint2.frictionIndex = this.tmpSolverFrictionConstraintPool.size();
/* 1079: 699 */            if (!localManifoldPoint.lateralFrictionInitialized) {
/* 1080: 700 */              localManifoldPoint.lateralFrictionDir1.scale(f1, localManifoldPoint.normalWorldOnB);
/* 1081: 701 */              localManifoldPoint.lateralFrictionDir1.sub(localVector3f5, localManifoldPoint.lateralFrictionDir1);
/* 1082:     */              
/* 1084: 704 */              if ((f1 = localManifoldPoint.lateralFrictionDir1.lengthSquared()) > 1.192093E-007F)
/* 1085:     */              {
/* 1086: 706 */                localManifoldPoint.lateralFrictionDir1.scale(1.0F / (float)Math.sqrt(f1));
/* 1087: 707 */                addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/* 1088: 708 */                localManifoldPoint.lateralFrictionDir2.cross(localManifoldPoint.lateralFrictionDir1, localManifoldPoint.normalWorldOnB);
/* 1089: 709 */                localManifoldPoint.lateralFrictionDir2.normalize();
/* 1090: 710 */                addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/* 1092:     */              }
/* 1093:     */              else
/* 1094:     */              {
/* 1095: 715 */                TransformUtil.planeSpace1(localManifoldPoint.normalWorldOnB, localManifoldPoint.lateralFrictionDir1, localManifoldPoint.lateralFrictionDir2);
/* 1096: 716 */                addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/* 1097: 717 */                addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/* 1098:     */              }
/* 1099: 719 */              localManifoldPoint.lateralFrictionInitialized = true;
/* 1100:     */            }
/* 1101:     */            else
/* 1102:     */            {
/* 1103: 723 */              addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/* 1104: 724 */              addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/* 1105:     */            }
/* 1106:     */            
/* 1108: 728 */            SolverConstraint localSolverConstraint1 = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(localSolverConstraint2.frictionIndex);
/* 1109: 729 */            if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/* 1110: 730 */              localSolverConstraint1.appliedImpulse = (localManifoldPoint.appliedImpulseLateral1 * paramContactSolverInfo.warmstartingFactor);
/* 1111: 731 */              if (localRigidBody1 != null) {
/* 1112: 732 */                localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint1.contactNormal);
/* 1113: 733 */                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentA, localSolverConstraint1.appliedImpulse);
/* 1114:     */              }
/* 1115: 735 */              if (localRigidBody2 != null) {
/* 1116: 736 */                localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint1.contactNormal);
/* 1117: 737 */                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentB, -localSolverConstraint1.appliedImpulse);
/* 1118:     */              }
/* 1119:     */            }
/* 1120:     */            else {
/* 1121: 741 */              localSolverConstraint1.appliedImpulse = 0.0F;
/* 1122:     */            }
/* 1123:     */            
/* 1125: 745 */            localSolverConstraint1 = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(localSolverConstraint2.frictionIndex + 1);
/* 1126: 746 */            if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/* 1127: 747 */              localSolverConstraint1.appliedImpulse = (localManifoldPoint.appliedImpulseLateral2 * paramContactSolverInfo.warmstartingFactor);
/* 1128: 748 */              if (localRigidBody1 != null) {
/* 1129: 749 */                localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint1.contactNormal);
/* 1130: 750 */                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentA, localSolverConstraint1.appliedImpulse);
/* 1131:     */              }
/* 1132: 752 */              if (localRigidBody2 != null) {
/* 1133: 753 */                localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint1.contactNormal);
/* 1134: 754 */                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentB, -localSolverConstraint1.appliedImpulse);
/* 1135:     */              }
/* 1136:     */            }
/* 1137:     */            else {
/* 1138: 758 */              localSolverConstraint1.appliedImpulse = 0.0F;
/* 1139:     */            }
/* 1140:     */          }
/* 1141:     */        }
/* 1142:     */      }
/* 1143:     */      
/* 1153: 773 */      for (i = 0; i < paramInt5; i++)
/* 1154:     */      {
/* 1155: 775 */        ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + i)).buildJacobian();
/* 1156:     */      }
/* 1157:     */      
/* 1161: 781 */      i = this.tmpSolverConstraintPool.size();
/* 1162: 782 */      int j = this.tmpSolverFrictionConstraintPool.size();
/* 1163:     */      
/* 1165: 785 */      MiscUtil.resize(this.orderTmpConstraintPool, i, 0);
/* 1166: 786 */      MiscUtil.resize(this.orderFrictionConstraintPool, j, 0);
/* 1167:     */      
/* 1169: 789 */      for (int k = 0; k < i; k++) {
/* 1170: 790 */        this.orderTmpConstraintPool.set(k, k);
/* 1171:     */      }
/* 1172: 792 */      for (k = 0; k < j; k++) {
/* 1173: 793 */        this.orderFrictionConstraintPool.set(k, k);
/* 1174:     */      }
/* 1175:     */      
/* 1177: 797 */      return 0.0F; } finally { BulletStats.popProfile();
/* 1178:     */    }
/* 1179:     */  }
/* 1180:     */  
/* 1183:     */  public float solveGroupCacheFriendlyIterations(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
/* 1184:     */  {
/* 1185: 805 */    BulletStats.pushProfile("solveGroupCacheFriendlyIterations");
/* 1186:     */    try {
/* 1187: 807 */      paramObjectArrayList1 = this.tmpSolverConstraintPool.size();
/* 1188: 808 */      paramInt1 = this.tmpSolverFrictionConstraintPool.size();
/* 1189:     */      
/* 1193: 813 */      for (paramObjectArrayList2 = 0; paramObjectArrayList2 < paramContactSolverInfo.numIterations; paramObjectArrayList2++)
/* 1194:     */      {
/* 1196: 816 */        if (((paramContactSolverInfo.solverMode & 0x1) != 0) && 
/* 1197: 817 */          ((paramObjectArrayList2 & 0x7) == 0)) {
/* 1198: 818 */          for (paramInt2 = 0; paramInt2 < paramObjectArrayList1; paramInt2++) {
/* 1199: 819 */            paramInt3 = this.orderTmpConstraintPool.get(paramInt2);
/* 1200: 820 */            paramIDebugDraw = randInt2(paramInt2 + 1);
/* 1201: 821 */            this.orderTmpConstraintPool.set(paramInt2, this.orderTmpConstraintPool.get(paramIDebugDraw));
/* 1202: 822 */            this.orderTmpConstraintPool.set(paramIDebugDraw, paramInt3);
/* 1203:     */          }
/* 1204:     */          
/* 1205: 825 */          for (paramInt2 = 0; paramInt2 < paramInt1; paramInt2++) {
/* 1206: 826 */            paramInt3 = this.orderFrictionConstraintPool.get(paramInt2);
/* 1207: 827 */            paramIDebugDraw = randInt2(paramInt2 + 1);
/* 1208: 828 */            this.orderFrictionConstraintPool.set(paramInt2, this.orderFrictionConstraintPool.get(paramIDebugDraw));
/* 1209: 829 */            this.orderFrictionConstraintPool.set(paramIDebugDraw, paramInt3);
/* 1210:     */          }
/* 1211:     */        }
/* 1212:     */        
/* 1214: 834 */        for (paramInt2 = 0; paramInt2 < paramInt5; paramInt2++)
/* 1215:     */        {
/* 1218: 838 */          if (((paramInt3 = (TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + paramInt2)).getRigidBodyA().getIslandTag() >= 0) && (paramInt3.getRigidBodyA().getCompanionId() >= 0)) {
/* 1219: 839 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyA().getCompanionId())).writebackVelocity();
/* 1220:     */          }
/* 1221: 841 */          if ((paramInt3.getRigidBodyB().getIslandTag() >= 0) && (paramInt3.getRigidBodyB().getCompanionId() >= 0)) {
/* 1222: 842 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyB().getCompanionId())).writebackVelocity();
/* 1223:     */          }
/* 1224:     */          
/* 1225: 845 */          paramInt3.solveConstraint(paramContactSolverInfo.timeStep);
/* 1226:     */          
/* 1227: 847 */          if ((paramInt3.getRigidBodyA().getIslandTag() >= 0) && (paramInt3.getRigidBodyA().getCompanionId() >= 0)) {
/* 1228: 848 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyA().getCompanionId())).readVelocity();
/* 1229:     */          }
/* 1230: 850 */          if ((paramInt3.getRigidBodyB().getIslandTag() >= 0) && (paramInt3.getRigidBodyB().getCompanionId() >= 0)) {
/* 1231: 851 */            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyB().getCompanionId())).readVelocity();
/* 1232:     */          }
/* 1233:     */        }
/* 1234:     */        
/* 1236: 856 */        paramInt3 = this.tmpSolverConstraintPool.size();
/* 1237: 857 */        for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++) {
/* 1238: 858 */          paramIDebugDraw = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(paramInt2));
/* 1239:     */          
/* 1240: 860 */          resolveSingleCollisionCombinedCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo);
/* 1241:     */        }
/* 1242:     */        
/* 1246: 866 */        paramInt3 = this.tmpSolverFrictionConstraintPool.size();
/* 1247:     */        
/* 1248: 868 */        for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++) {
/* 1249: 869 */          paramIDebugDraw = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(this.orderFrictionConstraintPool.get(paramInt2));
/* 1250:     */          
/* 1251: 871 */          float f = ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramIDebugDraw.frictionIndex)).appliedImpulse + ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramIDebugDraw.frictionIndex)).appliedPushImpulse;
/* 1252:     */          
/* 1254: 874 */          resolveSingleFrictionCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo, f);
/* 1255:     */        }
/* 1256:     */      }
/* 1257:     */      
/* 1261: 881 */      if (paramContactSolverInfo.splitImpulse) {
/* 1262: 882 */        for (paramObjectArrayList2 = 0; paramObjectArrayList2 < paramContactSolverInfo.numIterations; paramObjectArrayList2++)
/* 1263:     */        {
/* 1264: 884 */          paramInt2 = this.tmpSolverConstraintPool.size();
/* 1265:     */          
/* 1266: 886 */          for (paramInt3 = 0; paramInt3 < paramInt2; paramInt3++) {
/* 1267: 887 */            paramIDebugDraw = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(paramInt3));
/* 1268:     */            
/* 1269: 889 */            resolveSplitPenetrationImpulseCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo);
/* 1270:     */          }
/* 1271:     */        }
/* 1272:     */      }
/* 1273:     */      
/* 1277: 897 */      return 0.0F; } finally { BulletStats.popProfile();
/* 1278:     */    }
/* 1279:     */  }
/* 1280:     */  
/* 1292:     */  public float solveGroupCacheFriendly(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
/* 1293:     */  {
/* 1294: 914 */    solveGroupCacheFriendlySetup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
/* 1295: 915 */    solveGroupCacheFriendlyIterations(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
/* 1296:     */    
/* 1297: 917 */    paramObjectArrayList1 = this.tmpSolverConstraintPool.size();
/* 1298: 918 */    for (paramInt1 = 0; paramInt1 < paramObjectArrayList1; paramInt1++)
/* 1299:     */    {
/* 1301: 921 */      paramInt2 = (ManifoldPoint)(paramObjectArrayList2 = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramInt1)).originalContactPoint;
/* 1302: 922 */      assert (paramInt2 != null);
/* 1303: 923 */      paramInt2.appliedImpulse = paramObjectArrayList2.appliedImpulse;
/* 1304: 924 */      paramInt2.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(paramObjectArrayList2.frictionIndex)).appliedImpulse;
/* 1305: 925 */      paramInt2.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(paramObjectArrayList2.frictionIndex + 1)).appliedImpulse;
/* 1306:     */    }
/* 1307:     */    
/* 1310: 930 */    if (paramContactSolverInfo.splitImpulse) {
/* 1311: 931 */      for (paramInt1 = 0; paramInt1 < this.tmpSolverBodyPool.size(); paramInt1++) {
/* 1312: 932 */        ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt1)).writebackVelocity(paramContactSolverInfo.timeStep);
/* 1313: 933 */        this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(paramInt1));
/* 1314:     */      }
/* 1315:     */      
/* 1316:     */    } else {
/* 1317: 937 */      for (paramInt1 = 0; paramInt1 < this.tmpSolverBodyPool.size(); paramInt1++) {
/* 1318: 938 */        ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt1)).writebackVelocity();
/* 1319: 939 */        this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(paramInt1));
/* 1320:     */      }
/* 1321:     */    }
/* 1322:     */    
/* 1334: 954 */    this.tmpSolverBodyPool.clear();
/* 1335:     */    
/* 1336: 956 */    for (paramInt1 = 0; paramInt1 < this.tmpSolverConstraintPool.size(); paramInt1++) {
/* 1337: 957 */      this.constraintsPool.release(this.tmpSolverConstraintPool.getQuick(paramInt1));
/* 1338:     */    }
/* 1339: 959 */    this.tmpSolverConstraintPool.clear();
/* 1340:     */    
/* 1341: 961 */    for (paramInt1 = 0; paramInt1 < this.tmpSolverFrictionConstraintPool.size(); paramInt1++) {
/* 1342: 962 */      this.constraintsPool.release(this.tmpSolverFrictionConstraintPool.getQuick(paramInt1));
/* 1343:     */    }
/* 1344: 964 */    this.tmpSolverFrictionConstraintPool.clear();
/* 1345:     */    
/* 1346: 966 */    return 0.0F;
/* 1347:     */  }
/* 1348:     */  
/* 1352:     */  public float solveGroup(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher)
/* 1353:     */  {
/* 1354: 974 */    BulletStats.pushProfile("solveGroup");
/* 1355:     */    try
/* 1356:     */    {
/* 1357: 977 */      if ((paramContactSolverInfo.solverMode & 0x8) != 0)
/* 1358:     */      {
/* 1360: 980 */        assert (paramObjectArrayList1 != null);
/* 1361: 981 */        assert (paramInt1 != 0);
/* 1362: 982 */        paramObjectArrayList1 = solveGroupCacheFriendly(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
/* 1363: 983 */        return paramObjectArrayList1;
/* 1364:     */      }
/* 1365:     */      
/* 1366: 986 */      paramObjectArrayList1 = new ContactSolverInfo(paramContactSolverInfo);
/* 1367:     */      
/* 1368: 988 */      paramInt1 = paramContactSolverInfo.numIterations;
/* 1369:     */      
/* 1370: 990 */      paramDispatcher = 0;
/* 1371:     */      int k;
/* 1372:     */      int m;
/* 1373: 993 */      for (int i = 0; i < paramInt3; i = (short)(i + 1)) {
/* 1374: 994 */        PersistentManifold localPersistentManifold1 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i);
/* 1375: 995 */        prepareConstraints(localPersistentManifold1, paramObjectArrayList1, paramIDebugDraw);
/* 1376:     */        
/* 1377: 997 */        for (k = 0; k < ((PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i)).getNumContacts(); k = (short)(k + 1)) {
/* 1378: 998 */          this.gOrder[(paramDispatcher << 1)] = i;
/* 1379: 999 */          this.gOrder[((paramDispatcher << 1) + 1)] = k;
/* 1380:     */          
/* 1382:1002 */          paramDispatcher += 2;
/* 1383:1003 */          if (paramDispatcher >= this.gOrder.length) {
/* 1384:1004 */            m = this.gOrder.length;
/* 1385:1005 */            this.gOrder = Arrays.copyOf(this.gOrder, m << 1);
/* 1386:     */          }
/* 1387:     */        }
/* 1388:     */      }
/* 1389:     */      
/* 1393:1013 */      for (i = 0; i < paramInt5; i++)
/* 1394:     */      {
/* 1395:1015 */        ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + i)).buildJacobian();
/* 1396:     */      }
/* 1397:     */      
/* 1402:1022 */      for (i = 0; i < paramInt1; i++)
/* 1403:     */      {
/* 1404:1024 */        if (((paramContactSolverInfo.solverMode & 0x1) != 0) && 
/* 1405:1025 */          ((i & 0x7) == 0)) {
/* 1406:1026 */          for (Dispatcher localDispatcher = 0; localDispatcher < paramDispatcher; localDispatcher += 2)
/* 1407:     */          {
/* 1408:1028 */            k = this.gOrder[localDispatcher];
/* 1409:1029 */            m = this.gOrder[(localDispatcher + 1)];
/* 1410:1030 */            paramInt3 = randInt2(localDispatcher / 2 + 1);
/* 1411:1031 */            this.gOrder[localDispatcher] = this.gOrder[paramInt3];
/* 1412:1032 */            this.gOrder[(localDispatcher + 1)] = this.gOrder[(paramInt3 + 1)];
/* 1413:     */            
/* 1414:1034 */            this.gOrder[paramInt3] = k;
/* 1415:1035 */            this.gOrder[(paramInt3 + 1)] = m;
/* 1416:     */          }
/* 1417:     */        }
/* 1418:     */        
/* 1420:1040 */        for (int j = 0; j < paramInt5; j++)
/* 1421:     */        {
/* 1422:1042 */          ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + j)).solveConstraint(paramObjectArrayList1.timeStep);
/* 1423:     */        }
/* 1424:     */        PersistentManifold localPersistentManifold2;
/* 1425:1045 */        for (j = 0; j < paramDispatcher; j += 2) {
/* 1426:1046 */          localPersistentManifold2 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + this.gOrder[j]);
/* 1427:1047 */          solve((RigidBody)localPersistentManifold2.getBody0(), (RigidBody)localPersistentManifold2.getBody1(), localPersistentManifold2.getContactPoint(this.gOrder[(j + 1)]), paramObjectArrayList1, i, paramIDebugDraw);
/* 1428:     */        }
/* 1429:     */        
/* 1431:1051 */        for (j = 0; j < paramDispatcher; j += 2) {
/* 1432:1052 */          localPersistentManifold2 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + this.gOrder[j]);
/* 1433:1053 */          solveFriction((RigidBody)localPersistentManifold2.getBody0(), (RigidBody)localPersistentManifold2.getBody1(), localPersistentManifold2.getContactPoint(this.gOrder[(j + 1)]), paramObjectArrayList1, i, paramIDebugDraw);
/* 1434:     */        }
/* 1435:     */      }
/* 1436:     */      
/* 1440:1060 */      return 0.0F; } finally { BulletStats.popProfile();
/* 1441:     */    }
/* 1442:     */  }
/* 1443:     */  
/* 1446:     */  protected void prepareConstraints(PersistentManifold paramPersistentManifold, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
/* 1447:     */  {
/* 1448:1068 */    paramIDebugDraw = (RigidBody)paramPersistentManifold.getBody0();
/* 1449:1069 */    RigidBody localRigidBody = (RigidBody)paramPersistentManifold.getBody1();
/* 1450:     */    
/* 1456:1076 */    int i = paramPersistentManifold.getNumContacts();
/* 1457:     */    
/* 1458:1078 */    BulletStats.gTotalContactPoints += i;
/* 1459:     */    
/* 1460:1080 */    Vector3f localVector3f1 = this.v.tmpVecB;
/* 1461:1081 */    Matrix3f localMatrix3f1 = this.v.tmpMat3B;
/* 1462:     */    
/* 1463:1083 */    Vector3f localVector3f2 = this.v.pos1B;
/* 1464:1084 */    Vector3f localVector3f3 = this.v.pos2B;
/* 1465:1085 */    Vector3f localVector3f4 = this.v.rel_pos1B;
/* 1466:1086 */    Vector3f localVector3f5 = this.v.rel_pos2B;
/* 1467:1087 */    Vector3f localVector3f6 = this.v.vel1B;
/* 1468:1088 */    Vector3f localVector3f7 = this.v.vel2B;
/* 1469:1089 */    Vector3f localVector3f8 = this.v.velB;
/* 1470:1090 */    Vector3f localVector3f9 = this.v.totalImpulseB;
/* 1471:1091 */    Vector3f localVector3f10 = this.v.torqueAxis0B;
/* 1472:1092 */    Vector3f localVector3f11 = this.v.torqueAxis1B;
/* 1473:1093 */    Vector3f localVector3f12 = this.v.ftorqueAxis0B;
/* 1474:1094 */    Vector3f localVector3f13 = this.v.ftorqueAxis1B;
/* 1475:     */    
/* 1476:1096 */    for (int j = 0; j < i; j++) {
/* 1477:     */      ManifoldPoint localManifoldPoint;
/* 1478:1098 */      if ((localManifoldPoint = paramPersistentManifold.getContactPoint(j)).getDistance() <= 0.0F) {
/* 1479:1099 */        localManifoldPoint.getPositionWorldOnA(localVector3f2);
/* 1480:1100 */        localManifoldPoint.getPositionWorldOnB(localVector3f3);
/* 1481:     */        
/* 1482:1102 */        localVector3f4.sub(localVector3f2, paramIDebugDraw.getCenterOfMassPosition(localVector3f1));
/* 1483:1103 */        localVector3f5.sub(localVector3f3, localRigidBody.getCenterOfMassPosition(localVector3f1));
/* 1484:     */        
/* 1485:     */        Matrix3f localMatrix3f2;
/* 1486:     */        
/* 1487:1107 */        (localMatrix3f2 = paramIDebugDraw.getCenterOfMassTransform(this.v.com0).basis).transpose();
/* 1488:     */        
/* 1489:     */        Object localObject;
/* 1490:1110 */        (localObject = localRigidBody.getCenterOfMassTransform(this.v.com1).basis).transpose();
/* 1491:     */        
/* 1492:     */        JacobianEntry localJacobianEntry;
/* 1493:1113 */        (localJacobianEntry = (JacobianEntry)this.jacobiansPool.get()).init(localMatrix3f2, (Matrix3f)localObject, localVector3f4, localVector3f5, localManifoldPoint.normalWorldOnB, paramIDebugDraw.getInvInertiaDiagLocal(this.v.in0), paramIDebugDraw.getInvMass(), localRigidBody.getInvInertiaDiagLocal(this.v.in1), localRigidBody.getInvMass());
/* 1494:     */        
/* 1498:1118 */        float f1 = localJacobianEntry.getDiagonal();
/* 1499:1119 */        this.jacobiansPool.release(localJacobianEntry);
/* 1500:     */        
/* 1502:1122 */        if ((localObject = (ConstraintPersistentData)localManifoldPoint.userPersistentData) != null)
/* 1503:     */        {
/* 1504:1124 */          localObject.persistentLifeTime += 1;
/* 1505:1125 */          if (((ConstraintPersistentData)localObject).persistentLifeTime != localManifoldPoint.getLifeTime())
/* 1506:     */          {
/* 1508:1128 */            ((ConstraintPersistentData)localObject).reset();
/* 1509:1129 */            ((ConstraintPersistentData)localObject).persistentLifeTime = localManifoldPoint.getLifeTime();
/* 1511:     */          }
/* 1512:     */          
/* 1515:     */        }
/* 1516:     */        else
/* 1517:     */        {
/* 1520:1140 */          localObject = new ConstraintPersistentData();
/* 1521:     */          
/* 1523:1143 */          this.totalCpd += 1;
/* 1524:     */          
/* 1525:1145 */          localManifoldPoint.userPersistentData = localObject;
/* 1526:1146 */          ((ConstraintPersistentData)localObject).persistentLifeTime = localManifoldPoint.getLifeTime();
/* 1527:     */        }
/* 1528:     */        
/* 1529:1149 */        assert (localObject != null);
/* 1530:     */        
/* 1531:1151 */        ((ConstraintPersistentData)localObject).jacDiagABInv = (1.0F / f1);
/* 1532:     */        
/* 1536:1156 */        ((ConstraintPersistentData)localObject).frictionSolverFunc = this.frictionDispatch[paramIDebugDraw.frictionSolverType][localRigidBody.frictionSolverType];
/* 1537:1157 */        ((ConstraintPersistentData)localObject).contactSolverFunc = this.contactDispatch[paramIDebugDraw.contactSolverType][localRigidBody.contactSolverType];
/* 1538:     */        
/* 1539:1159 */        paramIDebugDraw.getVelocityInLocalPoint(localVector3f4, localVector3f6);
/* 1540:1160 */        localRigidBody.getVelocityInLocalPoint(localVector3f5, localVector3f7);
/* 1541:1161 */        localVector3f8.sub(localVector3f6, localVector3f7);
/* 1542:     */        
/* 1544:1164 */        f1 = localManifoldPoint.normalWorldOnB.dot(localVector3f8);
/* 1545:     */        
/* 1546:1166 */        float f2 = localManifoldPoint.combinedRestitution;
/* 1547:     */        
/* 1548:1168 */        ((ConstraintPersistentData)localObject).penetration = localManifoldPoint.getDistance();
/* 1549:1169 */        ((ConstraintPersistentData)localObject).friction = localManifoldPoint.combinedFriction;
/* 1550:1170 */        ((ConstraintPersistentData)localObject).restitution = restitutionCurve(f1, f2);
/* 1551:1171 */        if (((ConstraintPersistentData)localObject).restitution <= 0.0F) {
/* 1552:1172 */          ((ConstraintPersistentData)localObject).restitution = 0.0F;
/* 1553:     */        }
/* 1554:     */        
/* 1558:1178 */        f1 = -((ConstraintPersistentData)localObject).penetration / paramContactSolverInfo.timeStep;
/* 1559:     */        
/* 1560:1180 */        if (((ConstraintPersistentData)localObject).restitution > f1) {
/* 1561:1181 */          ((ConstraintPersistentData)localObject).penetration = 0.0F;
/* 1562:     */        }
/* 1563:     */        
/* 1564:1184 */        f1 = paramContactSolverInfo.damping;
/* 1565:1185 */        if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/* 1566:1186 */          localObject.appliedImpulse *= f1;
/* 1567:     */        }
/* 1568:     */        else {
/* 1569:1189 */          ((ConstraintPersistentData)localObject).appliedImpulse = 0.0F;
/* 1570:     */        }
/* 1571:     */        
/* 1573:1193 */        ((ConstraintPersistentData)localObject).prevAppliedImpulse = ((ConstraintPersistentData)localObject).appliedImpulse;
/* 1574:     */        
/* 1576:1196 */        TransformUtil.planeSpace1(localManifoldPoint.normalWorldOnB, ((ConstraintPersistentData)localObject).frictionWorldTangential0, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1577:     */        
/* 1580:1200 */        ((ConstraintPersistentData)localObject).accumulatedTangentImpulse0 = 0.0F;
/* 1581:1201 */        ((ConstraintPersistentData)localObject).accumulatedTangentImpulse1 = 0.0F;
/* 1582:     */        
/* 1583:1203 */        f2 = paramIDebugDraw.computeImpulseDenominator(localVector3f2, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/* 1584:1204 */        float f3 = localRigidBody.computeImpulseDenominator(localVector3f3, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/* 1585:1205 */        f2 = f1 / (f2 + f3);
/* 1586:1206 */        ((ConstraintPersistentData)localObject).jacDiagABInvTangent0 = f2;
/* 1587:     */        
/* 1588:1208 */        f2 = paramIDebugDraw.computeImpulseDenominator(localVector3f2, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1589:1209 */        f3 = localRigidBody.computeImpulseDenominator(localVector3f3, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1590:1210 */        f2 = f1 / (f2 + f3);
/* 1591:1211 */        ((ConstraintPersistentData)localObject).jacDiagABInvTangent1 = f2;
/* 1592:     */        
/* 1599:1219 */        localVector3f9.scale(((ConstraintPersistentData)localObject).appliedImpulse, localManifoldPoint.normalWorldOnB);
/* 1600:     */        
/* 1603:1223 */        localVector3f10.cross(localVector3f4, localManifoldPoint.normalWorldOnB);
/* 1604:     */        
/* 1605:1225 */        ((ConstraintPersistentData)localObject).angularComponentA.set(localVector3f10);
/* 1606:1226 */        paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).angularComponentA);
/* 1607:     */        
/* 1608:1228 */        localVector3f11.cross(localVector3f5, localManifoldPoint.normalWorldOnB);
/* 1609:     */        
/* 1610:1230 */        ((ConstraintPersistentData)localObject).angularComponentB.set(localVector3f11);
/* 1611:1231 */        localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).angularComponentB);
/* 1612:     */        
/* 1614:1234 */        localVector3f12.cross(localVector3f4, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/* 1615:     */        
/* 1616:1236 */        ((ConstraintPersistentData)localObject).frictionAngularComponent0A.set(localVector3f12);
/* 1617:1237 */        paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent0A);
/* 1618:     */        
/* 1620:1240 */        localVector3f13.cross(localVector3f4, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1621:     */        
/* 1622:1242 */        ((ConstraintPersistentData)localObject).frictionAngularComponent1A.set(localVector3f13);
/* 1623:1243 */        paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent1A);
/* 1624:     */        
/* 1626:1246 */        localVector3f12.cross(localVector3f5, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/* 1627:     */        
/* 1628:1248 */        ((ConstraintPersistentData)localObject).frictionAngularComponent0B.set(localVector3f12);
/* 1629:1249 */        localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent0B);
/* 1630:     */        
/* 1632:1252 */        localVector3f13.cross(localVector3f5, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1633:     */        
/* 1634:1254 */        ((ConstraintPersistentData)localObject).frictionAngularComponent1B.set(localVector3f13);
/* 1635:1255 */        localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent1B);
/* 1636:     */        
/* 1641:1261 */        paramIDebugDraw.applyImpulse(localVector3f9, localVector3f4);
/* 1642:     */        
/* 1643:1263 */        localVector3f1.negate(localVector3f9);
/* 1644:1264 */        localRigidBody.applyImpulse(localVector3f1, localVector3f5);
/* 1645:     */      }
/* 1646:     */    }
/* 1647:     */  }
/* 1648:     */  
/* 1650:     */  public float solveCombinedContactFriction(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
/* 1651:     */  {
/* 1652:1272 */    paramInt = 0.0F;
/* 1653:     */    
/* 1655:1275 */    if (paramManifoldPoint.getDistance() <= 0.0F)
/* 1656:     */    {
/* 1658:1278 */      paramRigidBody1 = ContactConstraint.resolveSingleCollisionCombined(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
/* 1659:     */      
/* 1660:1280 */      if (0.0F < paramRigidBody1) {
/* 1661:1281 */        paramInt = paramRigidBody1;
/* 1662:     */      }
/* 1663:     */    }
/* 1664:     */    
/* 1666:1286 */    return paramInt;
/* 1667:     */  }
/* 1668:     */  
/* 1669:     */  protected float solve(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw) {
/* 1670:1290 */    paramInt = 0.0F;
/* 1671:     */    
/* 1673:1293 */    if (paramManifoldPoint.getDistance() <= 0.0F)
/* 1674:     */    {
/* 1676:1296 */      paramRigidBody1 = ((ConstraintPersistentData)paramManifoldPoint.userPersistentData).contactSolverFunc.resolveContact(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
/* 1677:     */      
/* 1678:1298 */      if (0.0F < paramRigidBody1) {
/* 1679:1299 */        paramInt = paramRigidBody1;
/* 1680:     */      }
/* 1681:     */    }
/* 1682:     */    
/* 1685:1305 */    return paramInt;
/* 1686:     */  }
/* 1687:     */  
/* 1688:     */  protected float solveFriction(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
/* 1689:     */  {
/* 1690:1310 */    if (paramManifoldPoint.getDistance() <= 0.0F)
/* 1691:     */    {
/* 1692:1312 */      ((ConstraintPersistentData)paramManifoldPoint.userPersistentData).frictionSolverFunc.resolveContact(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
/* 1693:     */    }
/* 1694:     */    
/* 1695:1315 */    return 0.0F;
/* 1696:     */  }
/* 1697:     */  
/* 1698:     */  public void reset()
/* 1699:     */  {
/* 1700:1320 */    this.btSeed2 = 0L;
/* 1701:     */  }
/* 1702:     */  
/* 1706:     */  public void setContactSolverFunc(ContactSolverFunc paramContactSolverFunc, int paramInt1, int paramInt2)
/* 1707:     */  {
/* 1708:1328 */    this.contactDispatch[paramInt1][paramInt2] = paramContactSolverFunc;
/* 1709:     */  }
/* 1710:     */  
/* 1714:     */  public void setFrictionSolverFunc(ContactSolverFunc paramContactSolverFunc, int paramInt1, int paramInt2)
/* 1715:     */  {
/* 1716:1336 */    this.frictionDispatch[paramInt1][paramInt2] = paramContactSolverFunc;
/* 1717:     */  }
/* 1718:     */  
/* 1719:     */  public void setRandSeed(long paramLong) {
/* 1720:1340 */    this.btSeed2 = paramLong;
/* 1721:     */  }
/* 1722:     */  
/* 1723:     */  public long getRandSeed() {
/* 1724:1344 */    return this.btSeed2;
/* 1725:     */  }
/* 1726:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseConstraintSolverExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */