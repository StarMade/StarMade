/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.linearmath.VectorUtil;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  88:    */public class Generic6DofConstraint
/*  89:    */  extends TypedConstraint
/*  90:    */{
/*  91: 91 */  protected final Transform frameInA = new Transform();
/*  92: 92 */  protected final Transform frameInB = new Transform();
/*  93:    */  
/*  94: 94 */  protected final JacobianEntry[] jacLinear = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  95: 95 */  protected final JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  96:    */  
/*  97: 97 */  protected final TranslationalLimitMotor linearLimits = new TranslationalLimitMotor();
/*  98:    */  
/*  99: 99 */  protected final RotationalLimitMotor[] angularLimits = { new RotationalLimitMotor(), new RotationalLimitMotor(), new RotationalLimitMotor() };
/* 100:    */  
/* 101:    */  protected float timeStep;
/* 102:102 */  protected final Transform calculatedTransformA = new Transform();
/* 103:103 */  protected final Transform calculatedTransformB = new Transform();
/* 104:104 */  protected final Vector3f calculatedAxisAngleDiff = new Vector3f();
/* 105:105 */  protected final Vector3f[] calculatedAxis = { new Vector3f(), new Vector3f(), new Vector3f() };
/* 106:    */  
/* 107:107 */  protected final Vector3f anchorPos = new Vector3f();
/* 108:    */  protected boolean useLinearReferenceFrameA;
/* 109:    */  
/* 110:    */  public Generic6DofConstraint()
/* 111:    */  {
/* 112:112 */    super(TypedConstraintType.D6_CONSTRAINT_TYPE);
/* 113:113 */    this.useLinearReferenceFrameA = true;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public Generic6DofConstraint(RigidBody rbA, RigidBody rbB, Transform frameInA, Transform frameInB, boolean useLinearReferenceFrameA) {
/* 117:117 */    super(TypedConstraintType.D6_CONSTRAINT_TYPE, rbA, rbB);
/* 118:118 */    this.frameInA.set(frameInA);
/* 119:119 */    this.frameInB.set(frameInB);
/* 120:120 */    this.useLinearReferenceFrameA = useLinearReferenceFrameA;
/* 121:    */  }
/* 122:    */  
/* 123:    */  private static float getMatrixElem(Matrix3f mat, int index) {
/* 124:124 */    int i = index % 3;
/* 125:125 */    int j = index / 3;
/* 126:126 */    return mat.getElement(i, j);
/* 127:    */  }
/* 128:    */  
/* 136:    */  private static boolean matrixToEulerXYZ(Matrix3f mat, Vector3f xyz)
/* 137:    */  {
/* 138:138 */    if (getMatrixElem(mat, 2) < 1.0F) {
/* 139:139 */      if (getMatrixElem(mat, 2) > -1.0F) {
/* 140:140 */        xyz.x = ((float)Math.atan2(-getMatrixElem(mat, 5), getMatrixElem(mat, 8)));
/* 141:141 */        xyz.y = ((float)Math.asin(getMatrixElem(mat, 2)));
/* 142:142 */        xyz.z = ((float)Math.atan2(-getMatrixElem(mat, 1), getMatrixElem(mat, 0)));
/* 143:143 */        return true;
/* 144:    */      }
/* 145:    */      
/* 147:147 */      xyz.x = (-(float)Math.atan2(getMatrixElem(mat, 3), getMatrixElem(mat, 4)));
/* 148:148 */      xyz.y = -1.570796F;
/* 149:149 */      xyz.z = 0.0F;
/* 150:150 */      return false;
/* 151:    */    }
/* 152:    */    
/* 155:155 */    xyz.x = ((float)Math.atan2(getMatrixElem(mat, 3), getMatrixElem(mat, 4)));
/* 156:156 */    xyz.y = 1.570796F;
/* 157:157 */    xyz.z = 0.0F;
/* 158:    */    
/* 160:160 */    return false;
/* 161:    */  }
/* 162:    */  
/* 165:    */  protected void calculateAngleInfo()
/* 166:    */  {
/* 167:167 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Matrix3f mat = localStack.get$javax$vecmath$Matrix3f();
/* 168:    */      
/* 169:169 */      Matrix3f relative_frame = localStack.get$javax$vecmath$Matrix3f();
/* 170:170 */      mat.set(this.calculatedTransformA.basis);
/* 171:171 */      MatrixUtil.invert(mat);
/* 172:172 */      relative_frame.mul(mat, this.calculatedTransformB.basis);
/* 173:    */      
/* 174:174 */      matrixToEulerXYZ(relative_frame, this.calculatedAxisAngleDiff);
/* 175:    */      
/* 191:191 */      Vector3f axis0 = localStack.get$javax$vecmath$Vector3f();
/* 192:192 */      this.calculatedTransformB.basis.getColumn(0, axis0);
/* 193:    */      
/* 194:194 */      Vector3f axis2 = localStack.get$javax$vecmath$Vector3f();
/* 195:195 */      this.calculatedTransformA.basis.getColumn(2, axis2);
/* 196:    */      
/* 197:197 */      this.calculatedAxis[1].cross(axis2, axis0);
/* 198:198 */      this.calculatedAxis[0].cross(this.calculatedAxis[1], axis2);
/* 199:199 */      this.calculatedAxis[2].cross(axis0, this.calculatedAxis[1]);
/* 204:    */    }
/* 205:    */    finally
/* 206:    */    {
/* 211:211 */      .Stack tmp157_155 = localStack;tmp157_155.pop$javax$vecmath$Vector3f();tmp157_155.pop$javax$vecmath$Matrix3f();
/* 212:    */    }
/* 213:    */  }
/* 214:    */  
/* 218:    */  public void calculateTransforms()
/* 219:    */  {
/* 220:220 */    this.rbA.getCenterOfMassTransform(this.calculatedTransformA);
/* 221:221 */    this.calculatedTransformA.mul(this.frameInA);
/* 222:    */    
/* 223:223 */    this.rbB.getCenterOfMassTransform(this.calculatedTransformB);
/* 224:224 */    this.calculatedTransformB.mul(this.frameInB);
/* 225:    */    
/* 226:226 */    calculateAngleInfo();
/* 227:    */  }
/* 228:    */  
/* 229:    */  protected void buildLinearJacobian(int arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4) {
/* 230:230 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 231:231 */      mat1.transpose();
/* 232:    */      
/* 233:233 */      Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 234:234 */      mat2.transpose();
/* 235:    */      
/* 236:236 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 237:    */      
/* 238:238 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 239:239 */      tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 240:    */      
/* 241:241 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 242:242 */      tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 243:    */      
/* 244:244 */      this.jacLinear[jacLinear_index].init(mat1, mat2, tmp1, tmp2, normalWorld, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/* 248:    */    }
/* 249:    */    finally
/* 250:    */    {
/* 254:254 */      .Stack tmp178_176 = localStack;tmp178_176.pop$com$bulletphysics$linearmath$Transform();tmp178_176.pop$javax$vecmath$Vector3f();
/* 255:    */    } }
/* 256:    */  
/* 257:257 */  protected void buildAngularJacobian(int arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 258:258 */      mat1.transpose();
/* 259:    */      
/* 260:260 */      Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 261:261 */      mat2.transpose();
/* 262:    */      
/* 263:263 */      this.jacAng[jacAngular_index].init(jointAxisW, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/* 265:    */    }
/* 266:    */    finally
/* 267:    */    {
/* 268:268 */      .Stack tmp105_103 = localStack;tmp105_103.pop$com$bulletphysics$linearmath$Transform();tmp105_103.pop$javax$vecmath$Vector3f();
/* 269:    */    }
/* 270:    */  }
/* 271:    */  
/* 274:    */  public boolean testAngularLimitMotor(int axis_index)
/* 275:    */  {
/* 276:276 */    float angle = VectorUtil.getCoord(this.calculatedAxisAngleDiff, axis_index);
/* 277:    */    
/* 279:279 */    this.angularLimits[axis_index].testLimitValue(angle);
/* 280:280 */    return this.angularLimits[axis_index].needApplyTorques();
/* 281:    */  }
/* 282:    */  
/* 284:    */  public void buildJacobian()
/* 285:    */  {
/* 286:286 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.linearLimits.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
/* 287:287 */      for (int i = 0; i < 3; i++) {
/* 288:288 */        this.angularLimits[i].accumulatedImpulse = 0.0F;
/* 289:    */      }
/* 290:    */      
/* 292:292 */      calculateTransforms();
/* 293:    */      
/* 294:294 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 295:    */      
/* 298:298 */      calcAnchorPos();
/* 299:299 */      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.anchorPos);
/* 300:300 */      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.anchorPos);
/* 301:    */      
/* 306:306 */      Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
/* 307:    */      
/* 308:308 */      for (int i = 0; i < 3; i++) {
/* 309:309 */        if (this.linearLimits.isLimited(i)) {
/* 310:310 */          if (this.useLinearReferenceFrameA) {
/* 311:311 */            this.calculatedTransformA.basis.getColumn(i, normalWorld);
/* 312:    */          }
/* 313:    */          else {
/* 314:314 */            this.calculatedTransformB.basis.getColumn(i, normalWorld);
/* 315:    */          }
/* 316:    */          
/* 317:317 */          buildLinearJacobian(i, normalWorld, pivotAInW, pivotBInW);
/* 318:    */        }
/* 319:    */      }
/* 320:    */      
/* 325:325 */      for (int i = 0; i < 3; i++)
/* 326:    */      {
/* 327:327 */        if (testAngularLimitMotor(i)) {
/* 328:328 */          getAxis(i, normalWorld);
/* 329:    */          
/* 330:330 */          buildAngularJacobian(i, normalWorld);
/* 331:    */        } }
/* 332:    */    } finally {
/* 333:333 */      localStack.pop$javax$vecmath$Vector3f();
/* 334:    */    }
/* 335:    */  }
/* 336:    */  
/* 337:337 */  public void solveConstraint(float arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.timeStep = timeStep;
/* 338:    */      
/* 345:345 */      Vector3f pointInA = localStack.get$javax$vecmath$Vector3f(this.calculatedTransformA.origin);
/* 346:346 */      Vector3f pointInB = localStack.get$javax$vecmath$Vector3f(this.calculatedTransformB.origin);
/* 347:    */      
/* 349:349 */      Vector3f linear_axis = localStack.get$javax$vecmath$Vector3f();
/* 350:350 */      for (int i = 0; i < 3; i++) {
/* 351:351 */        if (this.linearLimits.isLimited(i)) {
/* 352:352 */          float jacDiagABInv = 1.0F / this.jacLinear[i].getDiagonal();
/* 353:    */          
/* 354:354 */          if (this.useLinearReferenceFrameA) {
/* 355:355 */            this.calculatedTransformA.basis.getColumn(i, linear_axis);
/* 356:    */          }
/* 357:    */          else {
/* 358:358 */            this.calculatedTransformB.basis.getColumn(i, linear_axis);
/* 359:    */          }
/* 360:    */          
/* 361:361 */          this.linearLimits.solveLinearAxis(this.timeStep, jacDiagABInv, this.rbA, pointInA, this.rbB, pointInB, i, linear_axis, this.anchorPos);
/* 362:    */        }
/* 363:    */      }
/* 364:    */      
/* 372:372 */      Vector3f angular_axis = localStack.get$javax$vecmath$Vector3f();
/* 373:    */      
/* 374:374 */      for (i = 0; i < 3; i++)
/* 375:375 */        if (this.angularLimits[i].needApplyTorques())
/* 376:    */        {
/* 377:377 */          getAxis(i, angular_axis);
/* 378:    */          
/* 379:379 */          float angularJacDiagABInv = 1.0F / this.jacAng[i].getDiagonal();
/* 380:    */          
/* 381:381 */          this.angularLimits[i].solveAngularLimits(this.timeStep, angular_axis, angularJacDiagABInv, this.rbA, this.rbB);
/* 382:    */        }
/* 383:    */    } finally {
/* 384:384 */      localStack.pop$javax$vecmath$Vector3f();
/* 385:    */    }
/* 386:    */  }
/* 387:    */  
/* 390:    */  public void updateRHS(float timeStep) {}
/* 391:    */  
/* 393:    */  public Vector3f getAxis(int axis_index, Vector3f out)
/* 394:    */  {
/* 395:395 */    out.set(this.calculatedAxis[axis_index]);
/* 396:396 */    return out;
/* 397:    */  }
/* 398:    */  
/* 402:    */  public float getAngle(int axis_index)
/* 403:    */  {
/* 404:404 */    return VectorUtil.getCoord(this.calculatedAxisAngleDiff, axis_index);
/* 405:    */  }
/* 406:    */  
/* 410:    */  public Transform getCalculatedTransformA(Transform out)
/* 411:    */  {
/* 412:412 */    out.set(this.calculatedTransformA);
/* 413:413 */    return out;
/* 414:    */  }
/* 415:    */  
/* 419:    */  public Transform getCalculatedTransformB(Transform out)
/* 420:    */  {
/* 421:421 */    out.set(this.calculatedTransformB);
/* 422:422 */    return out;
/* 423:    */  }
/* 424:    */  
/* 425:    */  public Transform getFrameOffsetA(Transform out) {
/* 426:426 */    out.set(this.frameInA);
/* 427:427 */    return out;
/* 428:    */  }
/* 429:    */  
/* 430:    */  public Transform getFrameOffsetB(Transform out) {
/* 431:431 */    out.set(this.frameInB);
/* 432:432 */    return out;
/* 433:    */  }
/* 434:    */  
/* 435:    */  public void setLinearLowerLimit(Vector3f linearLower) {
/* 436:436 */    this.linearLimits.lowerLimit.set(linearLower);
/* 437:    */  }
/* 438:    */  
/* 439:    */  public void setLinearUpperLimit(Vector3f linearUpper) {
/* 440:440 */    this.linearLimits.upperLimit.set(linearUpper);
/* 441:    */  }
/* 442:    */  
/* 443:    */  public void setAngularLowerLimit(Vector3f angularLower) {
/* 444:444 */    this.angularLimits[0].loLimit = angularLower.x;
/* 445:445 */    this.angularLimits[1].loLimit = angularLower.y;
/* 446:446 */    this.angularLimits[2].loLimit = angularLower.z;
/* 447:    */  }
/* 448:    */  
/* 449:    */  public void setAngularUpperLimit(Vector3f angularUpper) {
/* 450:450 */    this.angularLimits[0].hiLimit = angularUpper.x;
/* 451:451 */    this.angularLimits[1].hiLimit = angularUpper.y;
/* 452:452 */    this.angularLimits[2].hiLimit = angularUpper.z;
/* 453:    */  }
/* 454:    */  
/* 457:    */  public RotationalLimitMotor getRotationalLimitMotor(int index)
/* 458:    */  {
/* 459:459 */    return this.angularLimits[index];
/* 460:    */  }
/* 461:    */  
/* 464:    */  public TranslationalLimitMotor getTranslationalLimitMotor()
/* 465:    */  {
/* 466:466 */    return this.linearLimits;
/* 467:    */  }
/* 468:    */  
/* 471:    */  public void setLimit(int axis, float lo, float hi)
/* 472:    */  {
/* 473:473 */    if (axis < 3) {
/* 474:474 */      VectorUtil.setCoord(this.linearLimits.lowerLimit, axis, lo);
/* 475:475 */      VectorUtil.setCoord(this.linearLimits.upperLimit, axis, hi);
/* 476:    */    }
/* 477:    */    else {
/* 478:478 */      this.angularLimits[(axis - 3)].loLimit = lo;
/* 479:479 */      this.angularLimits[(axis - 3)].hiLimit = hi;
/* 480:    */    }
/* 481:    */  }
/* 482:    */  
/* 489:    */  public boolean isLimited(int limitIndex)
/* 490:    */  {
/* 491:491 */    if (limitIndex < 3) {
/* 492:492 */      return this.linearLimits.isLimited(limitIndex);
/* 493:    */    }
/* 494:    */    
/* 495:495 */    return this.angularLimits[(limitIndex - 3)].isLimited();
/* 496:    */  }
/* 497:    */  
/* 498:    */  public void calcAnchorPos()
/* 499:    */  {
/* 500:500 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();float imA = this.rbA.getInvMass();
/* 501:501 */      float imB = this.rbB.getInvMass();
/* 502:    */      float weight;
/* 503:503 */      float weight; if (imB == 0.0F) {
/* 504:504 */        weight = 1.0F;
/* 505:    */      }
/* 506:    */      else {
/* 507:507 */        weight = imA / (imA + imB);
/* 508:    */      }
/* 509:509 */      Vector3f pA = this.calculatedTransformA.origin;
/* 510:510 */      Vector3f pB = this.calculatedTransformB.origin;
/* 511:    */      
/* 512:512 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 513:513 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 514:    */      
/* 515:515 */      tmp1.scale(weight, pA);
/* 516:516 */      tmp2.scale(1.0F - weight, pB);
/* 517:517 */      this.anchorPos.add(tmp1, tmp2);
/* 518:518 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 519:    */    }
/* 520:    */  }
/* 521:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.Generic6DofConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */