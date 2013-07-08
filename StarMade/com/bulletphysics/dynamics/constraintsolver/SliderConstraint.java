/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Matrix3f;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */
/*  48:    */public class SliderConstraint
/*  49:    */  extends TypedConstraint
/*  50:    */{
/*  51:    */  public static final float SLIDER_CONSTRAINT_DEF_SOFTNESS = 1.0F;
/*  52:    */  public static final float SLIDER_CONSTRAINT_DEF_DAMPING = 1.0F;
/*  53:    */  public static final float SLIDER_CONSTRAINT_DEF_RESTITUTION = 0.7F;
/*  54: 54 */  protected final Transform frameInA = new Transform();
/*  55: 55 */  protected final Transform frameInB = new Transform();
/*  56:    */  
/*  57:    */  protected boolean useLinearReferenceFrameA;
/*  58:    */  
/*  59:    */  protected float lowerLinLimit;
/*  60:    */  
/*  61:    */  protected float upperLinLimit;
/*  62:    */  
/*  63:    */  protected float lowerAngLimit;
/*  64:    */  
/*  65:    */  protected float upperAngLimit;
/*  66:    */  
/*  67:    */  protected float softnessDirLin;
/*  68:    */  
/*  69:    */  protected float restitutionDirLin;
/*  70:    */  
/*  71:    */  protected float dampingDirLin;
/*  72:    */  
/*  73:    */  protected float softnessDirAng;
/*  74:    */  
/*  75:    */  protected float restitutionDirAng;
/*  76:    */  
/*  77:    */  protected float dampingDirAng;
/*  78:    */  
/*  79:    */  protected float softnessLimLin;
/*  80:    */  protected float restitutionLimLin;
/*  81:    */  protected float dampingLimLin;
/*  82:    */  protected float softnessLimAng;
/*  83:    */  protected float restitutionLimAng;
/*  84:    */  protected float dampingLimAng;
/*  85:    */  protected float softnessOrthoLin;
/*  86:    */  protected float restitutionOrthoLin;
/*  87:    */  protected float dampingOrthoLin;
/*  88:    */  protected float softnessOrthoAng;
/*  89:    */  protected float restitutionOrthoAng;
/*  90:    */  protected float dampingOrthoAng;
/*  91:    */  protected boolean solveLinLim;
/*  92:    */  protected boolean solveAngLim;
/*  93: 93 */  protected JacobianEntry[] jacLin = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  94: 94 */  protected float[] jacLinDiagABInv = new float[3];
/*  95:    */  
/*  96: 96 */  protected JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  97:    */  
/*  98:    */  protected float timeStep;
/*  99: 99 */  protected final Transform calculatedTransformA = new Transform();
/* 100:100 */  protected final Transform calculatedTransformB = new Transform();
/* 101:    */  
/* 102:102 */  protected final Vector3f sliderAxis = new Vector3f();
/* 103:103 */  protected final Vector3f realPivotAInW = new Vector3f();
/* 104:104 */  protected final Vector3f realPivotBInW = new Vector3f();
/* 105:105 */  protected final Vector3f projPivotInW = new Vector3f();
/* 106:106 */  protected final Vector3f delta = new Vector3f();
/* 107:107 */  protected final Vector3f depth = new Vector3f();
/* 108:108 */  protected final Vector3f relPosA = new Vector3f();
/* 109:109 */  protected final Vector3f relPosB = new Vector3f();
/* 110:    */  
/* 111:    */  protected float linPos;
/* 112:    */  
/* 113:    */  protected float angDepth;
/* 114:    */  
/* 115:    */  protected float kAngle;
/* 116:    */  protected boolean poweredLinMotor;
/* 117:    */  protected float targetLinMotorVelocity;
/* 118:    */  protected float maxLinMotorForce;
/* 119:    */  protected float accumulatedLinMotorImpulse;
/* 120:    */  protected boolean poweredAngMotor;
/* 121:    */  protected float targetAngMotorVelocity;
/* 122:    */  protected float maxAngMotorForce;
/* 123:    */  protected float accumulatedAngMotorImpulse;
/* 124:    */  
/* 125:    */  public SliderConstraint()
/* 126:    */  {
/* 127:127 */    super(TypedConstraintType.SLIDER_CONSTRAINT_TYPE);
/* 128:128 */    this.useLinearReferenceFrameA = true;
/* 129:129 */    initParams();
/* 130:    */  }
/* 131:    */  
/* 132:    */  public SliderConstraint(RigidBody rbA, RigidBody rbB, Transform frameInA, Transform frameInB, boolean useLinearReferenceFrameA) {
/* 133:133 */    super(TypedConstraintType.SLIDER_CONSTRAINT_TYPE, rbA, rbB);
/* 134:134 */    this.frameInA.set(frameInA);
/* 135:135 */    this.frameInB.set(frameInB);
/* 136:136 */    this.useLinearReferenceFrameA = useLinearReferenceFrameA;
/* 137:137 */    initParams();
/* 138:    */  }
/* 139:    */  
/* 140:    */  protected void initParams() {
/* 141:141 */    this.lowerLinLimit = 1.0F;
/* 142:142 */    this.upperLinLimit = -1.0F;
/* 143:143 */    this.lowerAngLimit = 0.0F;
/* 144:144 */    this.upperAngLimit = 0.0F;
/* 145:145 */    this.softnessDirLin = 1.0F;
/* 146:146 */    this.restitutionDirLin = 0.7F;
/* 147:147 */    this.dampingDirLin = 0.0F;
/* 148:148 */    this.softnessDirAng = 1.0F;
/* 149:149 */    this.restitutionDirAng = 0.7F;
/* 150:150 */    this.dampingDirAng = 0.0F;
/* 151:151 */    this.softnessOrthoLin = 1.0F;
/* 152:152 */    this.restitutionOrthoLin = 0.7F;
/* 153:153 */    this.dampingOrthoLin = 1.0F;
/* 154:154 */    this.softnessOrthoAng = 1.0F;
/* 155:155 */    this.restitutionOrthoAng = 0.7F;
/* 156:156 */    this.dampingOrthoAng = 1.0F;
/* 157:157 */    this.softnessLimLin = 1.0F;
/* 158:158 */    this.restitutionLimLin = 0.7F;
/* 159:159 */    this.dampingLimLin = 1.0F;
/* 160:160 */    this.softnessLimAng = 1.0F;
/* 161:161 */    this.restitutionLimAng = 0.7F;
/* 162:162 */    this.dampingLimAng = 1.0F;
/* 163:    */    
/* 164:164 */    this.poweredLinMotor = false;
/* 165:165 */    this.targetLinMotorVelocity = 0.0F;
/* 166:166 */    this.maxLinMotorForce = 0.0F;
/* 167:167 */    this.accumulatedLinMotorImpulse = 0.0F;
/* 168:    */    
/* 169:169 */    this.poweredAngMotor = false;
/* 170:170 */    this.targetAngMotorVelocity = 0.0F;
/* 171:171 */    this.maxAngMotorForce = 0.0F;
/* 172:172 */    this.accumulatedAngMotorImpulse = 0.0F;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void buildJacobian()
/* 176:    */  {
/* 177:177 */    if (this.useLinearReferenceFrameA) {
/* 178:178 */      buildJacobianInt(this.rbA, this.rbB, this.frameInA, this.frameInB);
/* 179:    */    }
/* 180:    */    else {
/* 181:181 */      buildJacobianInt(this.rbB, this.rbA, this.frameInB, this.frameInA);
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 185:    */  public void solveConstraint(float timeStep)
/* 186:    */  {
/* 187:187 */    this.timeStep = timeStep;
/* 188:188 */    if (this.useLinearReferenceFrameA) {
/* 189:189 */      solveConstraintInt(this.rbA, this.rbB);
/* 190:    */    }
/* 191:    */    else {
/* 192:192 */      solveConstraintInt(this.rbB, this.rbA);
/* 193:    */    }
/* 194:    */  }
/* 195:    */  
/* 196:    */  public Transform getCalculatedTransformA(Transform out) {
/* 197:197 */    out.set(this.calculatedTransformA);
/* 198:198 */    return out;
/* 199:    */  }
/* 200:    */  
/* 201:    */  public Transform getCalculatedTransformB(Transform out) {
/* 202:202 */    out.set(this.calculatedTransformB);
/* 203:203 */    return out;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public Transform getFrameOffsetA(Transform out) {
/* 207:207 */    out.set(this.frameInA);
/* 208:208 */    return out;
/* 209:    */  }
/* 210:    */  
/* 211:    */  public Transform getFrameOffsetB(Transform out) {
/* 212:212 */    out.set(this.frameInB);
/* 213:213 */    return out;
/* 214:    */  }
/* 215:    */  
/* 216:    */  public float getLowerLinLimit() {
/* 217:217 */    return this.lowerLinLimit;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public void setLowerLinLimit(float lowerLimit) {
/* 221:221 */    this.lowerLinLimit = lowerLimit;
/* 222:    */  }
/* 223:    */  
/* 224:    */  public float getUpperLinLimit() {
/* 225:225 */    return this.upperLinLimit;
/* 226:    */  }
/* 227:    */  
/* 228:    */  public void setUpperLinLimit(float upperLimit) {
/* 229:229 */    this.upperLinLimit = upperLimit;
/* 230:    */  }
/* 231:    */  
/* 232:    */  public float getLowerAngLimit() {
/* 233:233 */    return this.lowerAngLimit;
/* 234:    */  }
/* 235:    */  
/* 236:    */  public void setLowerAngLimit(float lowerLimit) {
/* 237:237 */    this.lowerAngLimit = lowerLimit;
/* 238:    */  }
/* 239:    */  
/* 240:    */  public float getUpperAngLimit() {
/* 241:241 */    return this.upperAngLimit;
/* 242:    */  }
/* 243:    */  
/* 244:    */  public void setUpperAngLimit(float upperLimit) {
/* 245:245 */    this.upperAngLimit = upperLimit;
/* 246:    */  }
/* 247:    */  
/* 248:    */  public boolean getUseLinearReferenceFrameA() {
/* 249:249 */    return this.useLinearReferenceFrameA;
/* 250:    */  }
/* 251:    */  
/* 252:    */  public float getSoftnessDirLin() {
/* 253:253 */    return this.softnessDirLin;
/* 254:    */  }
/* 255:    */  
/* 256:    */  public float getRestitutionDirLin() {
/* 257:257 */    return this.restitutionDirLin;
/* 258:    */  }
/* 259:    */  
/* 260:    */  public float getDampingDirLin() {
/* 261:261 */    return this.dampingDirLin;
/* 262:    */  }
/* 263:    */  
/* 264:    */  public float getSoftnessDirAng() {
/* 265:265 */    return this.softnessDirAng;
/* 266:    */  }
/* 267:    */  
/* 268:    */  public float getRestitutionDirAng() {
/* 269:269 */    return this.restitutionDirAng;
/* 270:    */  }
/* 271:    */  
/* 272:    */  public float getDampingDirAng() {
/* 273:273 */    return this.dampingDirAng;
/* 274:    */  }
/* 275:    */  
/* 276:    */  public float getSoftnessLimLin() {
/* 277:277 */    return this.softnessLimLin;
/* 278:    */  }
/* 279:    */  
/* 280:    */  public float getRestitutionLimLin() {
/* 281:281 */    return this.restitutionLimLin;
/* 282:    */  }
/* 283:    */  
/* 284:    */  public float getDampingLimLin() {
/* 285:285 */    return this.dampingLimLin;
/* 286:    */  }
/* 287:    */  
/* 288:    */  public float getSoftnessLimAng() {
/* 289:289 */    return this.softnessLimAng;
/* 290:    */  }
/* 291:    */  
/* 292:    */  public float getRestitutionLimAng() {
/* 293:293 */    return this.restitutionLimAng;
/* 294:    */  }
/* 295:    */  
/* 296:    */  public float getDampingLimAng() {
/* 297:297 */    return this.dampingLimAng;
/* 298:    */  }
/* 299:    */  
/* 300:    */  public float getSoftnessOrthoLin() {
/* 301:301 */    return this.softnessOrthoLin;
/* 302:    */  }
/* 303:    */  
/* 304:    */  public float getRestitutionOrthoLin() {
/* 305:305 */    return this.restitutionOrthoLin;
/* 306:    */  }
/* 307:    */  
/* 308:    */  public float getDampingOrthoLin() {
/* 309:309 */    return this.dampingOrthoLin;
/* 310:    */  }
/* 311:    */  
/* 312:    */  public float getSoftnessOrthoAng() {
/* 313:313 */    return this.softnessOrthoAng;
/* 314:    */  }
/* 315:    */  
/* 316:    */  public float getRestitutionOrthoAng() {
/* 317:317 */    return this.restitutionOrthoAng;
/* 318:    */  }
/* 319:    */  
/* 320:    */  public float getDampingOrthoAng() {
/* 321:321 */    return this.dampingOrthoAng;
/* 322:    */  }
/* 323:    */  
/* 324:    */  public void setSoftnessDirLin(float softnessDirLin) {
/* 325:325 */    this.softnessDirLin = softnessDirLin;
/* 326:    */  }
/* 327:    */  
/* 328:    */  public void setRestitutionDirLin(float restitutionDirLin) {
/* 329:329 */    this.restitutionDirLin = restitutionDirLin;
/* 330:    */  }
/* 331:    */  
/* 332:    */  public void setDampingDirLin(float dampingDirLin) {
/* 333:333 */    this.dampingDirLin = dampingDirLin;
/* 334:    */  }
/* 335:    */  
/* 336:    */  public void setSoftnessDirAng(float softnessDirAng) {
/* 337:337 */    this.softnessDirAng = softnessDirAng;
/* 338:    */  }
/* 339:    */  
/* 340:    */  public void setRestitutionDirAng(float restitutionDirAng) {
/* 341:341 */    this.restitutionDirAng = restitutionDirAng;
/* 342:    */  }
/* 343:    */  
/* 344:    */  public void setDampingDirAng(float dampingDirAng) {
/* 345:345 */    this.dampingDirAng = dampingDirAng;
/* 346:    */  }
/* 347:    */  
/* 348:    */  public void setSoftnessLimLin(float softnessLimLin) {
/* 349:349 */    this.softnessLimLin = softnessLimLin;
/* 350:    */  }
/* 351:    */  
/* 352:    */  public void setRestitutionLimLin(float restitutionLimLin) {
/* 353:353 */    this.restitutionLimLin = restitutionLimLin;
/* 354:    */  }
/* 355:    */  
/* 356:    */  public void setDampingLimLin(float dampingLimLin) {
/* 357:357 */    this.dampingLimLin = dampingLimLin;
/* 358:    */  }
/* 359:    */  
/* 360:    */  public void setSoftnessLimAng(float softnessLimAng) {
/* 361:361 */    this.softnessLimAng = softnessLimAng;
/* 362:    */  }
/* 363:    */  
/* 364:    */  public void setRestitutionLimAng(float restitutionLimAng) {
/* 365:365 */    this.restitutionLimAng = restitutionLimAng;
/* 366:    */  }
/* 367:    */  
/* 368:    */  public void setDampingLimAng(float dampingLimAng) {
/* 369:369 */    this.dampingLimAng = dampingLimAng;
/* 370:    */  }
/* 371:    */  
/* 372:    */  public void setSoftnessOrthoLin(float softnessOrthoLin) {
/* 373:373 */    this.softnessOrthoLin = softnessOrthoLin;
/* 374:    */  }
/* 375:    */  
/* 376:    */  public void setRestitutionOrthoLin(float restitutionOrthoLin) {
/* 377:377 */    this.restitutionOrthoLin = restitutionOrthoLin;
/* 378:    */  }
/* 379:    */  
/* 380:    */  public void setDampingOrthoLin(float dampingOrthoLin) {
/* 381:381 */    this.dampingOrthoLin = dampingOrthoLin;
/* 382:    */  }
/* 383:    */  
/* 384:    */  public void setSoftnessOrthoAng(float softnessOrthoAng) {
/* 385:385 */    this.softnessOrthoAng = softnessOrthoAng;
/* 386:    */  }
/* 387:    */  
/* 388:    */  public void setRestitutionOrthoAng(float restitutionOrthoAng) {
/* 389:389 */    this.restitutionOrthoAng = restitutionOrthoAng;
/* 390:    */  }
/* 391:    */  
/* 392:    */  public void setDampingOrthoAng(float dampingOrthoAng) {
/* 393:393 */    this.dampingOrthoAng = dampingOrthoAng;
/* 394:    */  }
/* 395:    */  
/* 396:    */  public void setPoweredLinMotor(boolean onOff) {
/* 397:397 */    this.poweredLinMotor = onOff;
/* 398:    */  }
/* 399:    */  
/* 400:    */  public boolean getPoweredLinMotor() {
/* 401:401 */    return this.poweredLinMotor;
/* 402:    */  }
/* 403:    */  
/* 404:    */  public void setTargetLinMotorVelocity(float targetLinMotorVelocity) {
/* 405:405 */    this.targetLinMotorVelocity = targetLinMotorVelocity;
/* 406:    */  }
/* 407:    */  
/* 408:    */  public float getTargetLinMotorVelocity() {
/* 409:409 */    return this.targetLinMotorVelocity;
/* 410:    */  }
/* 411:    */  
/* 412:    */  public void setMaxLinMotorForce(float maxLinMotorForce) {
/* 413:413 */    this.maxLinMotorForce = maxLinMotorForce;
/* 414:    */  }
/* 415:    */  
/* 416:    */  public float getMaxLinMotorForce() {
/* 417:417 */    return this.maxLinMotorForce;
/* 418:    */  }
/* 419:    */  
/* 420:    */  public void setPoweredAngMotor(boolean onOff) {
/* 421:421 */    this.poweredAngMotor = onOff;
/* 422:    */  }
/* 423:    */  
/* 424:    */  public boolean getPoweredAngMotor() {
/* 425:425 */    return this.poweredAngMotor;
/* 426:    */  }
/* 427:    */  
/* 428:    */  public void setTargetAngMotorVelocity(float targetAngMotorVelocity) {
/* 429:429 */    this.targetAngMotorVelocity = targetAngMotorVelocity;
/* 430:    */  }
/* 431:    */  
/* 432:    */  public float getTargetAngMotorVelocity() {
/* 433:433 */    return this.targetAngMotorVelocity;
/* 434:    */  }
/* 435:    */  
/* 436:    */  public void setMaxAngMotorForce(float maxAngMotorForce) {
/* 437:437 */    this.maxAngMotorForce = maxAngMotorForce;
/* 438:    */  }
/* 439:    */  
/* 440:    */  public float getMaxAngMotorForce() {
/* 441:441 */    return this.maxAngMotorForce;
/* 442:    */  }
/* 443:    */  
/* 444:    */  public float getLinearPos() {
/* 445:445 */    return this.linPos;
/* 446:    */  }
/* 447:    */  
/* 449:    */  public boolean getSolveLinLimit()
/* 450:    */  {
/* 451:451 */    return this.solveLinLim;
/* 452:    */  }
/* 453:    */  
/* 454:    */  public float getLinDepth() {
/* 455:455 */    return this.depth.x;
/* 456:    */  }
/* 457:    */  
/* 458:    */  public boolean getSolveAngLimit() {
/* 459:459 */    return this.solveAngLim;
/* 460:    */  }
/* 461:    */  
/* 462:    */  public float getAngDepth() {
/* 463:463 */    return this.angDepth;
/* 464:    */  }
/* 465:    */  
/* 467:    */  public void buildJacobianInt(RigidBody arg1, RigidBody arg2, Transform arg3, Transform arg4)
/* 468:    */  {
/* 469:469 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 470:470 */      Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 471:471 */      Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 472:472 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 473:473 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 474:    */      
/* 476:476 */      this.calculatedTransformA.mul(rbA.getCenterOfMassTransform(tmpTrans), frameInA);
/* 477:477 */      this.calculatedTransformB.mul(rbB.getCenterOfMassTransform(tmpTrans), frameInB);
/* 478:478 */      this.realPivotAInW.set(this.calculatedTransformA.origin);
/* 479:479 */      this.realPivotBInW.set(this.calculatedTransformB.origin);
/* 480:480 */      this.calculatedTransformA.basis.getColumn(0, tmp);
/* 481:481 */      this.sliderAxis.set(tmp);
/* 482:482 */      this.delta.sub(this.realPivotBInW, this.realPivotAInW);
/* 483:483 */      this.projPivotInW.scaleAdd(this.sliderAxis.dot(this.delta), this.sliderAxis, this.realPivotAInW);
/* 484:484 */      this.relPosA.sub(this.projPivotInW, rbA.getCenterOfMassPosition(tmp));
/* 485:485 */      this.relPosB.sub(this.realPivotBInW, rbB.getCenterOfMassPosition(tmp));
/* 486:486 */      Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
/* 487:    */      
/* 489:489 */      for (int i = 0; i < 3; i++) {
/* 490:490 */        this.calculatedTransformA.basis.getColumn(i, normalWorld);
/* 491:    */        
/* 492:492 */        Matrix3f mat1 = rbA.getCenterOfMassTransform(tmpTrans1).basis;
/* 493:493 */        mat1.transpose();
/* 494:    */        
/* 495:495 */        Matrix3f mat2 = rbB.getCenterOfMassTransform(tmpTrans2).basis;
/* 496:496 */        mat2.transpose();
/* 497:    */        
/* 498:498 */        this.jacLin[i].init(mat1, mat2, this.relPosA, this.relPosB, normalWorld, rbA.getInvInertiaDiagLocal(tmp), rbA.getInvMass(), rbB.getInvInertiaDiagLocal(tmp2), rbB.getInvMass());
/* 499:    */        
/* 508:508 */        this.jacLinDiagABInv[i] = (1.0F / this.jacLin[i].getDiagonal());
/* 509:509 */        VectorUtil.setCoord(this.depth, i, this.delta.dot(normalWorld));
/* 510:    */      }
/* 511:511 */      testLinLimits();
/* 512:    */      
/* 514:514 */      for (int i = 0; i < 3; i++) {
/* 515:515 */        this.calculatedTransformA.basis.getColumn(i, normalWorld);
/* 516:    */        
/* 517:517 */        Matrix3f mat1 = rbA.getCenterOfMassTransform(tmpTrans1).basis;
/* 518:518 */        mat1.transpose();
/* 519:    */        
/* 520:520 */        Matrix3f mat2 = rbB.getCenterOfMassTransform(tmpTrans2).basis;
/* 521:521 */        mat2.transpose();
/* 522:    */        
/* 523:523 */        this.jacAng[i].init(normalWorld, mat1, mat2, rbA.getInvInertiaDiagLocal(tmp), rbB.getInvInertiaDiagLocal(tmp2));
/* 524:    */      }
/* 525:    */      
/* 530:530 */      testAngLimits();
/* 531:    */      
/* 532:532 */      Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 533:533 */      this.calculatedTransformA.basis.getColumn(0, axisA);
/* 534:534 */      this.kAngle = (1.0F / (rbA.computeAngularImpulseDenominator(axisA) + rbB.computeAngularImpulseDenominator(axisA)));
/* 535:    */      
/* 536:536 */      this.accumulatedLinMotorImpulse = 0.0F;
/* 537:537 */      this.accumulatedAngMotorImpulse = 0.0F;
/* 538:538 */    } finally { .Stack tmp510_508 = localStack;tmp510_508.pop$com$bulletphysics$linearmath$Transform();tmp510_508.pop$javax$vecmath$Vector3f();
/* 539:    */    } }
/* 540:    */  
/* 541:541 */  public void solveConstraintInt(RigidBody arg1, RigidBody arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 542:    */      
/* 544:544 */      Vector3f velA = rbA.getVelocityInLocalPoint(this.relPosA, localStack.get$javax$vecmath$Vector3f());
/* 545:545 */      Vector3f velB = rbB.getVelocityInLocalPoint(this.relPosB, localStack.get$javax$vecmath$Vector3f());
/* 546:546 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 547:547 */      vel.sub(velA, velB);
/* 548:    */      
/* 549:549 */      Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 550:    */      
/* 551:551 */      for (int i = 0; i < 3; i++) {
/* 552:552 */        Vector3f normal = this.jacLin[i].linearJointAxis;
/* 553:553 */        float rel_vel = normal.dot(vel);
/* 554:    */        
/* 555:555 */        float depth = VectorUtil.getCoord(this.depth, i);
/* 556:    */        
/* 557:557 */        float softness = this.solveLinLim ? this.softnessLimLin : i != 0 ? this.softnessOrthoLin : this.softnessDirLin;
/* 558:558 */        float restitution = this.solveLinLim ? this.restitutionLimLin : i != 0 ? this.restitutionOrthoLin : this.restitutionDirLin;
/* 559:559 */        float damping = this.solveLinLim ? this.dampingLimLin : i != 0 ? this.dampingOrthoLin : this.dampingDirLin;
/* 560:    */        
/* 561:561 */        float normalImpulse = softness * (restitution * depth / this.timeStep - damping * rel_vel) * this.jacLinDiagABInv[i];
/* 562:562 */        impulse_vector.scale(normalImpulse, normal);
/* 563:563 */        rbA.applyImpulse(impulse_vector, this.relPosA);
/* 564:564 */        tmp.negate(impulse_vector);
/* 565:565 */        rbB.applyImpulse(tmp, this.relPosB);
/* 566:    */        
/* 567:567 */        if ((this.poweredLinMotor) && (i == 0))
/* 568:    */        {
/* 569:569 */          if (this.accumulatedLinMotorImpulse < this.maxLinMotorForce) {
/* 570:570 */            float desiredMotorVel = this.targetLinMotorVelocity;
/* 571:571 */            float motor_relvel = desiredMotorVel + rel_vel;
/* 572:572 */            normalImpulse = -motor_relvel * this.jacLinDiagABInv[i];
/* 573:    */            
/* 574:574 */            float new_acc = this.accumulatedLinMotorImpulse + Math.abs(normalImpulse);
/* 575:575 */            if (new_acc > this.maxLinMotorForce) {
/* 576:576 */              new_acc = this.maxLinMotorForce;
/* 577:    */            }
/* 578:578 */            float del = new_acc - this.accumulatedLinMotorImpulse;
/* 579:579 */            if (normalImpulse < 0.0F) {
/* 580:580 */              normalImpulse = -del;
/* 581:    */            }
/* 582:    */            else {
/* 583:583 */              normalImpulse = del;
/* 584:    */            }
/* 585:585 */            this.accumulatedLinMotorImpulse = new_acc;
/* 586:    */            
/* 587:587 */            impulse_vector.scale(normalImpulse, normal);
/* 588:588 */            rbA.applyImpulse(impulse_vector, this.relPosA);
/* 589:589 */            tmp.negate(impulse_vector);
/* 590:590 */            rbB.applyImpulse(tmp, this.relPosB);
/* 591:    */          }
/* 592:    */        }
/* 593:    */      }
/* 594:    */      
/* 597:597 */      Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 598:598 */      this.calculatedTransformA.basis.getColumn(0, axisA);
/* 599:599 */      Vector3f axisB = localStack.get$javax$vecmath$Vector3f();
/* 600:600 */      this.calculatedTransformB.basis.getColumn(0, axisB);
/* 601:    */      
/* 602:602 */      Vector3f angVelA = rbA.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 603:603 */      Vector3f angVelB = rbB.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 604:    */      
/* 605:605 */      Vector3f angVelAroundAxisA = localStack.get$javax$vecmath$Vector3f();
/* 606:606 */      angVelAroundAxisA.scale(axisA.dot(angVelA), axisA);
/* 607:607 */      Vector3f angVelAroundAxisB = localStack.get$javax$vecmath$Vector3f();
/* 608:608 */      angVelAroundAxisB.scale(axisB.dot(angVelB), axisB);
/* 609:    */      
/* 610:610 */      Vector3f angAorthog = localStack.get$javax$vecmath$Vector3f();
/* 611:611 */      angAorthog.sub(angVelA, angVelAroundAxisA);
/* 612:612 */      Vector3f angBorthog = localStack.get$javax$vecmath$Vector3f();
/* 613:613 */      angBorthog.sub(angVelB, angVelAroundAxisB);
/* 614:614 */      Vector3f velrelOrthog = localStack.get$javax$vecmath$Vector3f();
/* 615:615 */      velrelOrthog.sub(angAorthog, angBorthog);
/* 616:    */      
/* 618:618 */      float len = velrelOrthog.length();
/* 619:619 */      if (len > 1.0E-005F) {
/* 620:620 */        Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 621:621 */        normal.normalize(velrelOrthog);
/* 622:622 */        float denom = rbA.computeAngularImpulseDenominator(normal) + rbB.computeAngularImpulseDenominator(normal);
/* 623:623 */        velrelOrthog.scale(1.0F / denom * this.dampingOrthoAng * this.softnessOrthoAng);
/* 624:    */      }
/* 625:    */      
/* 627:627 */      Vector3f angularError = localStack.get$javax$vecmath$Vector3f();
/* 628:628 */      angularError.cross(axisA, axisB);
/* 629:629 */      angularError.scale(1.0F / this.timeStep);
/* 630:630 */      float len2 = angularError.length();
/* 631:631 */      if (len2 > 1.0E-005F) {
/* 632:632 */        Vector3f normal2 = localStack.get$javax$vecmath$Vector3f();
/* 633:633 */        normal2.normalize(angularError);
/* 634:634 */        float denom2 = rbA.computeAngularImpulseDenominator(normal2) + rbB.computeAngularImpulseDenominator(normal2);
/* 635:635 */        angularError.scale(1.0F / denom2 * this.restitutionOrthoAng * this.softnessOrthoAng);
/* 636:    */      }
/* 637:    */      
/* 639:639 */      tmp.negate(velrelOrthog);
/* 640:640 */      tmp.add(angularError);
/* 641:641 */      rbA.applyTorqueImpulse(tmp);
/* 642:642 */      tmp.sub(velrelOrthog, angularError);
/* 643:643 */      rbB.applyTorqueImpulse(tmp);
/* 644:    */      
/* 645:    */      float impulseMag;
/* 646:    */      
/* 647:647 */      if (this.solveAngLim) {
/* 648:648 */        tmp.sub(angVelB, angVelA);
/* 649:649 */        float impulseMag = tmp.dot(axisA) * this.dampingLimAng + this.angDepth * this.restitutionLimAng / this.timeStep;
/* 650:650 */        impulseMag *= this.kAngle * this.softnessLimAng;
/* 651:    */      }
/* 652:    */      else {
/* 653:653 */        tmp.sub(angVelB, angVelA);
/* 654:654 */        impulseMag = tmp.dot(axisA) * this.dampingDirAng + this.angDepth * this.restitutionDirAng / this.timeStep;
/* 655:655 */        impulseMag *= this.kAngle * this.softnessDirAng;
/* 656:    */      }
/* 657:657 */      Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 658:658 */      impulse.scale(impulseMag, axisA);
/* 659:659 */      rbA.applyTorqueImpulse(impulse);
/* 660:660 */      tmp.negate(impulse);
/* 661:661 */      rbB.applyTorqueImpulse(tmp);
/* 662:    */      
/* 664:664 */      if ((this.poweredAngMotor) && 
/* 665:665 */        (this.accumulatedAngMotorImpulse < this.maxAngMotorForce)) {
/* 666:666 */        Vector3f velrel = localStack.get$javax$vecmath$Vector3f();
/* 667:667 */        velrel.sub(angVelAroundAxisA, angVelAroundAxisB);
/* 668:668 */        float projRelVel = velrel.dot(axisA);
/* 669:    */        
/* 670:670 */        float desiredMotorVel = this.targetAngMotorVelocity;
/* 671:671 */        float motor_relvel = desiredMotorVel - projRelVel;
/* 672:    */        
/* 673:673 */        float angImpulse = this.kAngle * motor_relvel;
/* 674:    */        
/* 675:675 */        float new_acc = this.accumulatedAngMotorImpulse + Math.abs(angImpulse);
/* 676:676 */        if (new_acc > this.maxAngMotorForce) {
/* 677:677 */          new_acc = this.maxAngMotorForce;
/* 678:    */        }
/* 679:679 */        float del = new_acc - this.accumulatedAngMotorImpulse;
/* 680:680 */        if (angImpulse < 0.0F) {
/* 681:681 */          angImpulse = -del;
/* 682:    */        } else {
/* 683:683 */          angImpulse = del;
/* 684:    */        }
/* 685:685 */        this.accumulatedAngMotorImpulse = new_acc;
/* 686:    */        
/* 688:688 */        Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
/* 689:689 */        motorImp.scale(angImpulse, axisA);
/* 690:690 */        rbA.applyTorqueImpulse(motorImp);
/* 691:691 */        tmp.negate(motorImp);
/* 692:692 */        rbB.applyTorqueImpulse(tmp);
/* 693:    */      }
/* 694:    */    } finally {
/* 695:695 */      localStack.pop$javax$vecmath$Vector3f();
/* 696:    */    }
/* 697:    */  }
/* 698:    */  
/* 699:    */  public void calculateTransforms() {
/* 700:700 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 701:    */      
/* 702:702 */      if (this.useLinearReferenceFrameA) {
/* 703:703 */        this.calculatedTransformA.mul(this.rbA.getCenterOfMassTransform(tmpTrans), this.frameInA);
/* 704:704 */        this.calculatedTransformB.mul(this.rbB.getCenterOfMassTransform(tmpTrans), this.frameInB);
/* 705:    */      }
/* 706:    */      else {
/* 707:707 */        this.calculatedTransformA.mul(this.rbB.getCenterOfMassTransform(tmpTrans), this.frameInB);
/* 708:708 */        this.calculatedTransformB.mul(this.rbA.getCenterOfMassTransform(tmpTrans), this.frameInA);
/* 709:    */      }
/* 710:710 */      this.realPivotAInW.set(this.calculatedTransformA.origin);
/* 711:711 */      this.realPivotBInW.set(this.calculatedTransformB.origin);
/* 712:712 */      this.calculatedTransformA.basis.getColumn(0, this.sliderAxis);
/* 713:713 */      this.delta.sub(this.realPivotBInW, this.realPivotAInW);
/* 714:714 */      this.projPivotInW.scaleAdd(this.sliderAxis.dot(this.delta), this.sliderAxis, this.realPivotAInW);
/* 715:715 */      Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
/* 716:    */      
/* 717:717 */      for (int i = 0; i < 3; i++) {
/* 718:718 */        this.calculatedTransformA.basis.getColumn(i, normalWorld);
/* 719:719 */        VectorUtil.setCoord(this.depth, i, this.delta.dot(normalWorld));
/* 720:    */      }
/* 721:721 */    } finally { .Stack tmp249_247 = localStack;tmp249_247.pop$com$bulletphysics$linearmath$Transform();tmp249_247.pop$javax$vecmath$Vector3f();
/* 722:    */    } }
/* 723:    */  
/* 724:724 */  public void testLinLimits() { this.solveLinLim = false;
/* 725:725 */    this.linPos = this.depth.x;
/* 726:726 */    if (this.lowerLinLimit <= this.upperLinLimit) {
/* 727:727 */      if (this.depth.x > this.upperLinLimit) {
/* 728:728 */        this.depth.x -= this.upperLinLimit;
/* 729:729 */        this.solveLinLim = true;
/* 730:    */      }
/* 731:731 */      else if (this.depth.x < this.lowerLinLimit) {
/* 732:732 */        this.depth.x -= this.lowerLinLimit;
/* 733:733 */        this.solveLinLim = true;
/* 734:    */      }
/* 735:    */      else {
/* 736:736 */        this.depth.x = 0.0F;
/* 737:    */      }
/* 738:    */    }
/* 739:    */    else {
/* 740:740 */      this.depth.x = 0.0F;
/* 741:    */    }
/* 742:    */  }
/* 743:    */  
/* 744:    */  public void testAngLimits() {
/* 745:745 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.angDepth = 0.0F;
/* 746:746 */      this.solveAngLim = false;
/* 747:747 */      if (this.lowerAngLimit <= this.upperAngLimit) {
/* 748:748 */        Vector3f axisA0 = localStack.get$javax$vecmath$Vector3f();
/* 749:749 */        this.calculatedTransformA.basis.getColumn(1, axisA0);
/* 750:750 */        Vector3f axisA1 = localStack.get$javax$vecmath$Vector3f();
/* 751:751 */        this.calculatedTransformA.basis.getColumn(2, axisA1);
/* 752:752 */        Vector3f axisB0 = localStack.get$javax$vecmath$Vector3f();
/* 753:753 */        this.calculatedTransformB.basis.getColumn(1, axisB0);
/* 754:    */        
/* 755:755 */        float rot = (float)Math.atan2(axisB0.dot(axisA1), axisB0.dot(axisA0));
/* 756:756 */        if (rot < this.lowerAngLimit) {
/* 757:757 */          this.angDepth = (rot - this.lowerAngLimit);
/* 758:758 */          this.solveAngLim = true;
/* 759:    */        }
/* 760:760 */        else if (rot > this.upperAngLimit) {
/* 761:761 */          this.angDepth = (rot - this.upperAngLimit);
/* 762:762 */          this.solveAngLim = true;
/* 763:    */        }
/* 764:    */      }
/* 765:765 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 766:    */    }
/* 767:    */  }
/* 768:    */  
/* 769:    */  public Vector3f getAncorInA(Vector3f arg1) {
/* 770:770 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 771:    */      
/* 772:772 */      Vector3f ancorInA = out;
/* 773:773 */      ancorInA.scaleAdd((this.lowerLinLimit + this.upperLinLimit) * 0.5F, this.sliderAxis, this.realPivotAInW);
/* 774:774 */      this.rbA.getCenterOfMassTransform(tmpTrans);
/* 775:775 */      tmpTrans.inverse();
/* 776:776 */      tmpTrans.transform(ancorInA);
/* 777:777 */      return ancorInA; } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 778:    */    }
/* 779:    */  }
/* 780:    */  
/* 781:781 */  public Vector3f getAncorInB(Vector3f out) { Vector3f ancorInB = out;
/* 782:782 */    ancorInB.set(this.frameInB.origin);
/* 783:783 */    return ancorInB;
/* 784:    */  }
/* 785:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SliderConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */