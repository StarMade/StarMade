/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.ScalarUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.linearmath.TransformUtil;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Quat4f;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */
/*  44:    */public class HingeConstraint
/*  45:    */  extends TypedConstraint
/*  46:    */{
/*  47: 47 */  private JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  48: 48 */  private JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  49:    */  
/*  50: 50 */  private final Transform rbAFrame = new Transform();
/*  51: 51 */  private final Transform rbBFrame = new Transform();
/*  52:    */  
/*  53:    */  private float motorTargetVelocity;
/*  54:    */  
/*  55:    */  private float maxMotorImpulse;
/*  56:    */  
/*  57:    */  private float limitSoftness;
/*  58:    */  
/*  59:    */  private float biasFactor;
/*  60:    */  
/*  61:    */  private float relaxationFactor;
/*  62:    */  
/*  63:    */  private float lowerLimit;
/*  64:    */  private float upperLimit;
/*  65:    */  private float kHinge;
/*  66:    */  private float limitSign;
/*  67:    */  private float correction;
/*  68:    */  private float accLimitImpulse;
/*  69:    */  private boolean angularOnly;
/*  70:    */  private boolean enableAngularMotor;
/*  71:    */  private boolean solveLimit;
/*  72:    */  
/*  73:    */  public HingeConstraint()
/*  74:    */  {
/*  75: 75 */    super(TypedConstraintType.HINGE_CONSTRAINT_TYPE);
/*  76: 76 */    this.enableAngularMotor = false;
/*  77:    */  }
/*  78:    */  
/* 112:    */  public HingeConstraint(RigidBody arg1, RigidBody arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, Vector3f arg6) {}
/* 113:    */  
/* 146:    */  public HingeConstraint(RigidBody arg1, Vector3f arg2, Vector3f arg3) {}
/* 147:    */  
/* 180:    */  public HingeConstraint(RigidBody rbA, RigidBody rbB, Transform rbAFrame, Transform rbBFrame)
/* 181:    */  {
/* 182:182 */    super(TypedConstraintType.HINGE_CONSTRAINT_TYPE, rbA, rbB);
/* 183:183 */    this.rbAFrame.set(rbAFrame);
/* 184:184 */    this.rbBFrame.set(rbBFrame);
/* 185:185 */    this.angularOnly = false;
/* 186:186 */    this.enableAngularMotor = false;
/* 187:    */    
/* 189:189 */    this.rbBFrame.basis.m02 *= -1.0F;
/* 190:190 */    this.rbBFrame.basis.m12 *= -1.0F;
/* 191:191 */    this.rbBFrame.basis.m22 *= -1.0F;
/* 192:    */    
/* 194:194 */    this.lowerLimit = 1.0E+030F;
/* 195:195 */    this.upperLimit = -1.0E+030F;
/* 196:196 */    this.biasFactor = 0.3F;
/* 197:197 */    this.relaxationFactor = 1.0F;
/* 198:198 */    this.limitSoftness = 0.9F;
/* 199:199 */    this.solveLimit = false;
/* 200:    */  }
/* 201:    */  
/* 214:    */  public HingeConstraint(RigidBody arg1, Transform arg2) {}
/* 215:    */  
/* 228:    */  public void buildJacobian()
/* 229:    */  {
/* 230:230 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Matrix3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 231:231 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 232:232 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 233:233 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 234:234 */      Matrix3f mat1 = localStack.get$javax$vecmath$Matrix3f();
/* 235:235 */      Matrix3f mat2 = localStack.get$javax$vecmath$Matrix3f();
/* 236:    */      
/* 237:237 */      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 238:238 */      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 239:    */      
/* 240:240 */      this.appliedImpulse = 0.0F;
/* 241:    */      
/* 242:242 */      if (!this.angularOnly) {
/* 243:243 */        Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 244:244 */        centerOfMassA.transform(pivotAInW);
/* 245:    */        
/* 246:246 */        Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 247:247 */        centerOfMassB.transform(pivotBInW);
/* 248:    */        
/* 249:249 */        Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
/* 250:250 */        relPos.sub(pivotBInW, pivotAInW);
/* 251:    */        
/* 252:252 */        Vector3f[] normal = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 253:253 */        if (relPos.lengthSquared() > 1.192093E-007F) {
/* 254:254 */          normal[0].set(relPos);
/* 255:255 */          normal[0].normalize();
/* 256:    */        }
/* 257:    */        else {
/* 258:258 */          normal[0].set(1.0F, 0.0F, 0.0F);
/* 259:    */        }
/* 260:    */        
/* 261:261 */        TransformUtil.planeSpace1(normal[0], normal[1], normal[2]);
/* 262:    */        
/* 263:263 */        for (int i = 0; i < 3; i++) {
/* 264:264 */          mat1.transpose(centerOfMassA.basis);
/* 265:265 */          mat2.transpose(centerOfMassB.basis);
/* 266:    */          
/* 267:267 */          tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 268:268 */          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 269:    */          
/* 270:270 */          this.jac[i].init(mat1, mat2, tmp1, tmp2, normal[i], this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/* 271:    */        }
/* 272:    */      }
/* 273:    */      
/* 287:287 */      Vector3f jointAxis0local = localStack.get$javax$vecmath$Vector3f();
/* 288:288 */      Vector3f jointAxis1local = localStack.get$javax$vecmath$Vector3f();
/* 289:    */      
/* 290:290 */      this.rbAFrame.basis.getColumn(2, tmp);
/* 291:291 */      TransformUtil.planeSpace1(tmp, jointAxis0local, jointAxis1local);
/* 292:    */      
/* 296:296 */      Vector3f jointAxis0 = localStack.get$javax$vecmath$Vector3f(jointAxis0local);
/* 297:297 */      centerOfMassA.basis.transform(jointAxis0);
/* 298:    */      
/* 299:299 */      Vector3f jointAxis1 = localStack.get$javax$vecmath$Vector3f(jointAxis1local);
/* 300:300 */      centerOfMassA.basis.transform(jointAxis1);
/* 301:    */      
/* 302:302 */      Vector3f hingeAxisWorld = localStack.get$javax$vecmath$Vector3f();
/* 303:303 */      this.rbAFrame.basis.getColumn(2, hingeAxisWorld);
/* 304:304 */      centerOfMassA.basis.transform(hingeAxisWorld);
/* 305:    */      
/* 306:306 */      mat1.transpose(centerOfMassA.basis);
/* 307:307 */      mat2.transpose(centerOfMassB.basis);
/* 308:308 */      this.jacAng[0].init(jointAxis0, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/* 309:    */      
/* 315:315 */      this.jacAng[1].init(jointAxis1, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/* 316:    */      
/* 322:322 */      this.jacAng[2].init(hingeAxisWorld, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/* 323:    */      
/* 329:329 */      float hingeAngle = getHingeAngle();
/* 330:    */      
/* 332:332 */      this.correction = 0.0F;
/* 333:333 */      this.limitSign = 0.0F;
/* 334:334 */      this.solveLimit = false;
/* 335:335 */      this.accLimitImpulse = 0.0F;
/* 336:    */      
/* 337:337 */      if (this.lowerLimit < this.upperLimit) {
/* 338:338 */        if (hingeAngle <= this.lowerLimit * this.limitSoftness) {
/* 339:339 */          this.correction = (this.lowerLimit - hingeAngle);
/* 340:340 */          this.limitSign = 1.0F;
/* 341:341 */          this.solveLimit = true;
/* 342:    */        }
/* 343:343 */        else if (hingeAngle >= this.upperLimit * this.limitSoftness) {
/* 344:344 */          this.correction = (this.upperLimit - hingeAngle);
/* 345:345 */          this.limitSign = -1.0F;
/* 346:346 */          this.solveLimit = true;
/* 347:    */        }
/* 348:    */      }
/* 349:    */      
/* 351:351 */      Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 352:352 */      this.rbAFrame.basis.getColumn(2, axisA);
/* 353:353 */      centerOfMassA.basis.transform(axisA);
/* 354:    */      
/* 355:355 */      this.kHinge = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(axisA) + getRigidBodyB().computeAngularImpulseDenominator(axisA)));
/* 356:    */    } finally {
/* 357:357 */      .Stack tmp792_790 = localStack;tmp792_790.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp796_792 = tmp792_790;tmp796_792.pop$javax$vecmath$Vector3f();tmp796_792.pop$javax$vecmath$Matrix3f();
/* 358:    */    }
/* 359:    */  }
/* 360:    */  
/* 361:361 */  public void solveConstraint(float arg1) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 362:362 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 363:363 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 364:    */      
/* 365:365 */      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 366:366 */      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 367:    */      
/* 368:368 */      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 369:369 */      centerOfMassA.transform(pivotAInW);
/* 370:    */      
/* 371:371 */      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 372:372 */      centerOfMassB.transform(pivotBInW);
/* 373:    */      
/* 374:374 */      float tau = 0.3F;
/* 375:    */      
/* 377:377 */      if (!this.angularOnly) {
/* 378:378 */        Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 379:379 */        rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 380:    */        
/* 381:381 */        Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 382:382 */        rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 383:    */        
/* 384:384 */        Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 385:385 */        Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 386:386 */        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 387:387 */        vel.sub(vel1, vel2);
/* 388:    */        
/* 389:389 */        for (int i = 0; i < 3; i++) {
/* 390:390 */          Vector3f normal = this.jac[i].linearJointAxis;
/* 391:391 */          float jacDiagABInv = 1.0F / this.jac[i].getDiagonal();
/* 392:    */          
/* 394:394 */          float rel_vel = normal.dot(vel);
/* 395:    */          
/* 396:396 */          tmp.sub(pivotAInW, pivotBInW);
/* 397:397 */          float depth = -tmp.dot(normal);
/* 398:398 */          float impulse = depth * tau / timeStep * jacDiagABInv - rel_vel * jacDiagABInv;
/* 399:399 */          this.appliedImpulse += impulse;
/* 400:400 */          Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 401:401 */          impulse_vector.scale(impulse, normal);
/* 402:    */          
/* 403:403 */          tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 404:404 */          this.rbA.applyImpulse(impulse_vector, tmp);
/* 405:    */          
/* 406:406 */          tmp.negate(impulse_vector);
/* 407:407 */          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 408:408 */          this.rbB.applyImpulse(tmp, tmp2);
/* 409:    */        }
/* 410:    */      }
/* 411:    */      
/* 417:417 */      Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 418:418 */      this.rbAFrame.basis.getColumn(2, axisA);
/* 419:419 */      centerOfMassA.basis.transform(axisA);
/* 420:    */      
/* 421:421 */      Vector3f axisB = localStack.get$javax$vecmath$Vector3f();
/* 422:422 */      this.rbBFrame.basis.getColumn(2, axisB);
/* 423:423 */      centerOfMassB.basis.transform(axisB);
/* 424:    */      
/* 425:425 */      Vector3f angVelA = getRigidBodyA().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 426:426 */      Vector3f angVelB = getRigidBodyB().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 427:    */      
/* 428:428 */      Vector3f angVelAroundHingeAxisA = localStack.get$javax$vecmath$Vector3f();
/* 429:429 */      angVelAroundHingeAxisA.scale(axisA.dot(angVelA), axisA);
/* 430:    */      
/* 431:431 */      Vector3f angVelAroundHingeAxisB = localStack.get$javax$vecmath$Vector3f();
/* 432:432 */      angVelAroundHingeAxisB.scale(axisB.dot(angVelB), axisB);
/* 433:    */      
/* 434:434 */      Vector3f angAorthog = localStack.get$javax$vecmath$Vector3f();
/* 435:435 */      angAorthog.sub(angVelA, angVelAroundHingeAxisA);
/* 436:    */      
/* 437:437 */      Vector3f angBorthog = localStack.get$javax$vecmath$Vector3f();
/* 438:438 */      angBorthog.sub(angVelB, angVelAroundHingeAxisB);
/* 439:    */      
/* 440:440 */      Vector3f velrelOrthog = localStack.get$javax$vecmath$Vector3f();
/* 441:441 */      velrelOrthog.sub(angAorthog, angBorthog);
/* 442:    */      
/* 445:445 */      float relaxation = 1.0F;
/* 446:446 */      float len = velrelOrthog.length();
/* 447:447 */      if (len > 1.0E-005F) {
/* 448:448 */        Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 449:449 */        normal.normalize(velrelOrthog);
/* 450:    */        
/* 451:451 */        float denom = getRigidBodyA().computeAngularImpulseDenominator(normal) + getRigidBodyB().computeAngularImpulseDenominator(normal);
/* 452:    */        
/* 455:455 */        velrelOrthog.scale(1.0F / denom * this.relaxationFactor);
/* 456:    */      }
/* 457:    */      
/* 461:461 */      Vector3f angularError = localStack.get$javax$vecmath$Vector3f();
/* 462:462 */      angularError.cross(axisA, axisB);
/* 463:463 */      angularError.negate();
/* 464:464 */      angularError.scale(1.0F / timeStep);
/* 465:465 */      float len2 = angularError.length();
/* 466:466 */      if (len2 > 1.0E-005F) {
/* 467:467 */        Vector3f normal2 = localStack.get$javax$vecmath$Vector3f();
/* 468:468 */        normal2.normalize(angularError);
/* 469:    */        
/* 470:470 */        float denom2 = getRigidBodyA().computeAngularImpulseDenominator(normal2) + getRigidBodyB().computeAngularImpulseDenominator(normal2);
/* 471:    */        
/* 472:472 */        angularError.scale(1.0F / denom2 * relaxation);
/* 473:    */      }
/* 474:    */      
/* 475:475 */      tmp.negate(velrelOrthog);
/* 476:476 */      tmp.add(angularError);
/* 477:477 */      this.rbA.applyTorqueImpulse(tmp);
/* 478:    */      
/* 479:479 */      tmp.sub(velrelOrthog, angularError);
/* 480:480 */      this.rbB.applyTorqueImpulse(tmp);
/* 481:    */      
/* 483:483 */      if (this.solveLimit) {
/* 484:484 */        tmp.sub(angVelB, angVelA);
/* 485:485 */        float amplitude = (tmp.dot(axisA) * this.relaxationFactor + this.correction * (1.0F / timeStep) * this.biasFactor) * this.limitSign;
/* 486:    */        
/* 487:487 */        float impulseMag = amplitude * this.kHinge;
/* 488:    */        
/* 490:490 */        float temp = this.accLimitImpulse;
/* 491:491 */        this.accLimitImpulse = Math.max(this.accLimitImpulse + impulseMag, 0.0F);
/* 492:492 */        impulseMag = this.accLimitImpulse - temp;
/* 493:    */        
/* 494:494 */        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 495:495 */        impulse.scale(impulseMag * this.limitSign, axisA);
/* 496:    */        
/* 497:497 */        this.rbA.applyTorqueImpulse(impulse);
/* 498:    */        
/* 499:499 */        tmp.negate(impulse);
/* 500:500 */        this.rbB.applyTorqueImpulse(tmp);
/* 501:    */      }
/* 502:    */      
/* 505:505 */      if (this.enableAngularMotor)
/* 506:    */      {
/* 507:507 */        Vector3f angularLimit = localStack.get$javax$vecmath$Vector3f();
/* 508:508 */        angularLimit.set(0.0F, 0.0F, 0.0F);
/* 509:    */        
/* 510:510 */        Vector3f velrel = localStack.get$javax$vecmath$Vector3f();
/* 511:511 */        velrel.sub(angVelAroundHingeAxisA, angVelAroundHingeAxisB);
/* 512:512 */        float projRelVel = velrel.dot(axisA);
/* 513:    */        
/* 514:514 */        float desiredMotorVel = this.motorTargetVelocity;
/* 515:515 */        float motor_relvel = desiredMotorVel - projRelVel;
/* 516:    */        
/* 517:517 */        float unclippedMotorImpulse = this.kHinge * motor_relvel;
/* 518:    */        
/* 519:519 */        float clippedMotorImpulse = unclippedMotorImpulse > this.maxMotorImpulse ? this.maxMotorImpulse : unclippedMotorImpulse;
/* 520:520 */        clippedMotorImpulse = clippedMotorImpulse < -this.maxMotorImpulse ? -this.maxMotorImpulse : clippedMotorImpulse;
/* 521:521 */        Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
/* 522:522 */        motorImp.scale(clippedMotorImpulse, axisA);
/* 523:    */        
/* 524:524 */        tmp.add(motorImp, angularLimit);
/* 525:525 */        this.rbA.applyTorqueImpulse(tmp);
/* 526:    */        
/* 527:527 */        tmp.negate(motorImp);
/* 528:528 */        tmp.sub(angularLimit);
/* 529:529 */        this.rbB.applyTorqueImpulse(tmp);
/* 530:    */      }
/* 531:    */    } finally {
/* 532:532 */      .Stack tmp1058_1056 = localStack;tmp1058_1056.pop$com$bulletphysics$linearmath$Transform();tmp1058_1056.pop$javax$vecmath$Vector3f();
/* 533:    */    }
/* 534:    */  }
/* 535:    */  
/* 536:    */  public void updateRHS(float timeStep) {}
/* 537:    */  
/* 538:538 */  public float getHingeAngle() { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 539:539 */      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 540:    */      
/* 541:541 */      Vector3f refAxis0 = localStack.get$javax$vecmath$Vector3f();
/* 542:542 */      this.rbAFrame.basis.getColumn(0, refAxis0);
/* 543:543 */      centerOfMassA.basis.transform(refAxis0);
/* 544:    */      
/* 545:545 */      Vector3f refAxis1 = localStack.get$javax$vecmath$Vector3f();
/* 546:546 */      this.rbAFrame.basis.getColumn(1, refAxis1);
/* 547:547 */      centerOfMassA.basis.transform(refAxis1);
/* 548:    */      
/* 549:549 */      Vector3f swingAxis = localStack.get$javax$vecmath$Vector3f();
/* 550:550 */      this.rbBFrame.basis.getColumn(1, swingAxis);
/* 551:551 */      centerOfMassB.basis.transform(swingAxis);
/* 552:    */      
/* 553:553 */      return ScalarUtil.atan2Fast(swingAxis.dot(refAxis0), swingAxis.dot(refAxis1)); } finally { .Stack tmp152_150 = localStack;tmp152_150.pop$com$bulletphysics$linearmath$Transform();tmp152_150.pop$javax$vecmath$Vector3f();
/* 554:    */    }
/* 555:    */  }
/* 556:    */  
/* 557:557 */  public void setAngularOnly(boolean angularOnly) { this.angularOnly = angularOnly; }
/* 558:    */  
/* 559:    */  public void enableAngularMotor(boolean enableMotor, float targetVelocity, float maxMotorImpulse)
/* 560:    */  {
/* 561:561 */    this.enableAngularMotor = enableMotor;
/* 562:562 */    this.motorTargetVelocity = targetVelocity;
/* 563:563 */    this.maxMotorImpulse = maxMotorImpulse;
/* 564:    */  }
/* 565:    */  
/* 566:    */  public void setLimit(float low, float high) {
/* 567:567 */    setLimit(low, high, 0.9F, 0.3F, 1.0F);
/* 568:    */  }
/* 569:    */  
/* 570:    */  public void setLimit(float low, float high, float _softness, float _biasFactor, float _relaxationFactor) {
/* 571:571 */    this.lowerLimit = low;
/* 572:572 */    this.upperLimit = high;
/* 573:    */    
/* 574:574 */    this.limitSoftness = _softness;
/* 575:575 */    this.biasFactor = _biasFactor;
/* 576:576 */    this.relaxationFactor = _relaxationFactor;
/* 577:    */  }
/* 578:    */  
/* 579:    */  public float getLowerLimit() {
/* 580:580 */    return this.lowerLimit;
/* 581:    */  }
/* 582:    */  
/* 583:    */  public float getUpperLimit() {
/* 584:584 */    return this.upperLimit;
/* 585:    */  }
/* 586:    */  
/* 587:    */  public Transform getAFrame(Transform out) {
/* 588:588 */    out.set(this.rbAFrame);
/* 589:589 */    return out;
/* 590:    */  }
/* 591:    */  
/* 592:    */  public Transform getBFrame(Transform out) {
/* 593:593 */    out.set(this.rbBFrame);
/* 594:594 */    return out;
/* 595:    */  }
/* 596:    */  
/* 597:    */  public boolean getSolveLimit() {
/* 598:598 */    return this.solveLimit;
/* 599:    */  }
/* 600:    */  
/* 601:    */  public float getLimitSign() {
/* 602:602 */    return this.limitSign;
/* 603:    */  }
/* 604:    */  
/* 605:    */  public boolean getAngularOnly() {
/* 606:606 */    return this.angularOnly;
/* 607:    */  }
/* 608:    */  
/* 609:    */  public boolean getEnableAngularMotor() {
/* 610:610 */    return this.enableAngularMotor;
/* 611:    */  }
/* 612:    */  
/* 613:    */  public float getMotorTargetVelosity() {
/* 614:614 */    return this.motorTargetVelocity;
/* 615:    */  }
/* 616:    */  
/* 617:    */  public float getMaxMotorImpulse() {
/* 618:618 */    return this.maxMotorImpulse;
/* 619:    */  }
/* 620:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.HingeConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */