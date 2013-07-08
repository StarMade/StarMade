/*   1:    */package com.bulletphysics.dynamics;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.BulletGlobals;
/*   5:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   6:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   7:    */import com.bulletphysics.collision.dispatch.CollisionObjectType;
/*   8:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   9:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*  10:    */import com.bulletphysics.linearmath.MatrixUtil;
/*  11:    */import com.bulletphysics.linearmath.MiscUtil;
/*  12:    */import com.bulletphysics.linearmath.MotionState;
/*  13:    */import com.bulletphysics.linearmath.Transform;
/*  14:    */import com.bulletphysics.linearmath.TransformUtil;
/*  15:    */import com.bulletphysics.util.ObjectArrayList;
/*  16:    */import javax.vecmath.Matrix3f;
/*  17:    */import javax.vecmath.Quat4f;
/*  18:    */import javax.vecmath.Vector3f;
/*  19:    */
/*  70:    */public class RigidBody
/*  71:    */  extends CollisionObject
/*  72:    */{
/*  73:    */  private static final float MAX_ANGVEL = 1.570796F;
/*  74: 74 */  private final Matrix3f invInertiaTensorWorld = new Matrix3f();
/*  75: 75 */  private final Vector3f linearVelocity = new Vector3f();
/*  76: 76 */  private final Vector3f angularVelocity = new Vector3f();
/*  77:    */  
/*  78:    */  private float inverseMass;
/*  79:    */  private float angularFactor;
/*  80: 80 */  private final Vector3f gravity = new Vector3f();
/*  81: 81 */  private final Vector3f invInertiaLocal = new Vector3f();
/*  82: 82 */  private final Vector3f totalForce = new Vector3f();
/*  83: 83 */  private final Vector3f totalTorque = new Vector3f();
/*  84:    */  
/*  85:    */  private float linearDamping;
/*  86:    */  
/*  87:    */  private float angularDamping;
/*  88:    */  
/*  89:    */  private boolean additionalDamping;
/*  90:    */  
/*  91:    */  private float additionalDampingFactor;
/*  92:    */  
/*  93:    */  private float additionalLinearDampingThresholdSqr;
/*  94:    */  
/*  95:    */  private float additionalAngularDampingThresholdSqr;
/*  96:    */  
/*  97:    */  private float additionalAngularDampingFactor;
/*  98:    */  private float linearSleepingThreshold;
/*  99:    */  private float angularSleepingThreshold;
/* 100:    */  private MotionState optionalMotionState;
/* 101:101 */  private final ObjectArrayList<TypedConstraint> constraintRefs = new ObjectArrayList();
/* 102:    */  
/* 103:    */  public int contactSolverType;
/* 104:    */  
/* 105:    */  public int frictionSolverType;
/* 106:    */  
/* 107:107 */  private static int uniqueId = 0;
/* 108:    */  public int debugBodyId;
/* 109:    */  
/* 110:    */  public RigidBody(RigidBodyConstructionInfo constructionInfo) {
/* 111:111 */    setupRigidBody(constructionInfo);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public RigidBody(float mass, MotionState motionState, CollisionShape collisionShape) {
/* 115:115 */    this(mass, motionState, collisionShape, new Vector3f(0.0F, 0.0F, 0.0F));
/* 116:    */  }
/* 117:    */  
/* 118:    */  public RigidBody(float mass, MotionState motionState, CollisionShape collisionShape, Vector3f localInertia) {
/* 119:119 */    RigidBodyConstructionInfo cinfo = new RigidBodyConstructionInfo(mass, motionState, collisionShape, localInertia);
/* 120:120 */    setupRigidBody(cinfo);
/* 121:    */  }
/* 122:    */  
/* 123:    */  private void setupRigidBody(RigidBodyConstructionInfo constructionInfo) {
/* 124:124 */    this.internalType = CollisionObjectType.RIGID_BODY;
/* 125:    */    
/* 126:126 */    this.linearVelocity.set(0.0F, 0.0F, 0.0F);
/* 127:127 */    this.angularVelocity.set(0.0F, 0.0F, 0.0F);
/* 128:128 */    this.angularFactor = 1.0F;
/* 129:129 */    this.gravity.set(0.0F, 0.0F, 0.0F);
/* 130:130 */    this.totalForce.set(0.0F, 0.0F, 0.0F);
/* 131:131 */    this.totalTorque.set(0.0F, 0.0F, 0.0F);
/* 132:132 */    this.linearDamping = 0.0F;
/* 133:133 */    this.angularDamping = 0.5F;
/* 134:134 */    this.linearSleepingThreshold = constructionInfo.linearSleepingThreshold;
/* 135:135 */    this.angularSleepingThreshold = constructionInfo.angularSleepingThreshold;
/* 136:136 */    this.optionalMotionState = constructionInfo.motionState;
/* 137:137 */    this.contactSolverType = 0;
/* 138:138 */    this.frictionSolverType = 0;
/* 139:139 */    this.additionalDamping = constructionInfo.additionalDamping;
/* 140:140 */    this.additionalDampingFactor = constructionInfo.additionalDampingFactor;
/* 141:141 */    this.additionalLinearDampingThresholdSqr = constructionInfo.additionalLinearDampingThresholdSqr;
/* 142:142 */    this.additionalAngularDampingThresholdSqr = constructionInfo.additionalAngularDampingThresholdSqr;
/* 143:143 */    this.additionalAngularDampingFactor = constructionInfo.additionalAngularDampingFactor;
/* 144:    */    
/* 145:145 */    if (this.optionalMotionState != null)
/* 146:    */    {
/* 147:147 */      this.optionalMotionState.getWorldTransform(this.worldTransform);
/* 148:    */    }
/* 149:    */    else {
/* 150:150 */      this.worldTransform.set(constructionInfo.startWorldTransform);
/* 151:    */    }
/* 152:    */    
/* 153:153 */    this.interpolationWorldTransform.set(this.worldTransform);
/* 154:154 */    this.interpolationLinearVelocity.set(0.0F, 0.0F, 0.0F);
/* 155:155 */    this.interpolationAngularVelocity.set(0.0F, 0.0F, 0.0F);
/* 156:    */    
/* 158:158 */    this.friction = constructionInfo.friction;
/* 159:159 */    this.restitution = constructionInfo.restitution;
/* 160:    */    
/* 161:161 */    setCollisionShape(constructionInfo.collisionShape);
/* 162:162 */    this.debugBodyId = (uniqueId++);
/* 163:    */    
/* 164:164 */    setMassProps(constructionInfo.mass, constructionInfo.localInertia);
/* 165:165 */    setDamping(constructionInfo.linearDamping, constructionInfo.angularDamping);
/* 166:166 */    updateInertiaTensor();
/* 167:    */  }
/* 168:    */  
/* 170:    */  public void destroy()
/* 171:    */  {
/* 172:172 */    assert (this.constraintRefs.size() == 0);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void proceedToTransform(Transform newTrans) {
/* 176:176 */    setCenterOfMassTransform(newTrans);
/* 177:    */  }
/* 178:    */  
/* 182:    */  public static RigidBody upcast(CollisionObject colObj)
/* 183:    */  {
/* 184:184 */    if (colObj.getInternalType() == CollisionObjectType.RIGID_BODY) {
/* 185:185 */      return (RigidBody)colObj;
/* 186:    */    }
/* 187:187 */    return null;
/* 188:    */  }
/* 189:    */  
/* 192:    */  public void predictIntegratedTransform(float timeStep, Transform predictedTransform)
/* 193:    */  {
/* 194:194 */    TransformUtil.integrateTransform(this.worldTransform, this.linearVelocity, this.angularVelocity, timeStep, predictedTransform);
/* 195:    */  }
/* 196:    */  
/* 197:    */  public void saveKinematicState(float timeStep)
/* 198:    */  {
/* 199:199 */    if (timeStep != 0.0F)
/* 200:    */    {
/* 201:201 */      if (getMotionState() != null) {
/* 202:202 */        getMotionState().getWorldTransform(this.worldTransform);
/* 203:    */      }
/* 204:    */      
/* 206:206 */      TransformUtil.calculateVelocity(this.interpolationWorldTransform, this.worldTransform, timeStep, this.linearVelocity, this.angularVelocity);
/* 207:207 */      this.interpolationLinearVelocity.set(this.linearVelocity);
/* 208:208 */      this.interpolationAngularVelocity.set(this.angularVelocity);
/* 209:209 */      this.interpolationWorldTransform.set(this.worldTransform);
/* 210:    */    }
/* 211:    */  }
/* 212:    */  
/* 213:    */  public void applyGravity()
/* 214:    */  {
/* 215:215 */    if (isStaticOrKinematicObject()) {
/* 216:216 */      return;
/* 217:    */    }
/* 218:218 */    applyCentralForce(this.gravity);
/* 219:    */  }
/* 220:    */  
/* 221:    */  public void setGravity(Vector3f acceleration) {
/* 222:222 */    if (this.inverseMass != 0.0F) {
/* 223:223 */      this.gravity.scale(1.0F / this.inverseMass, acceleration);
/* 224:    */    }
/* 225:    */  }
/* 226:    */  
/* 227:    */  public Vector3f getGravity(Vector3f out) {
/* 228:228 */    out.set(this.gravity);
/* 229:229 */    return out;
/* 230:    */  }
/* 231:    */  
/* 232:    */  public void setDamping(float lin_damping, float ang_damping) {
/* 233:233 */    this.linearDamping = MiscUtil.GEN_clamped(lin_damping, 0.0F, 1.0F);
/* 234:234 */    this.angularDamping = MiscUtil.GEN_clamped(ang_damping, 0.0F, 1.0F);
/* 235:    */  }
/* 236:    */  
/* 237:    */  public float getLinearDamping() {
/* 238:238 */    return this.linearDamping;
/* 239:    */  }
/* 240:    */  
/* 241:    */  public float getAngularDamping() {
/* 242:242 */    return this.angularDamping;
/* 243:    */  }
/* 244:    */  
/* 245:    */  public float getLinearSleepingThreshold() {
/* 246:246 */    return this.linearSleepingThreshold;
/* 247:    */  }
/* 248:    */  
/* 249:    */  public float getAngularSleepingThreshold() {
/* 250:250 */    return this.angularSleepingThreshold;
/* 251:    */  }
/* 252:    */  
/* 263:    */  public void applyDamping(float arg1)
/* 264:    */  {
/* 265:265 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.linearVelocity.scale((float)Math.pow(1.0F - this.linearDamping, timeStep));
/* 266:266 */      this.angularVelocity.scale((float)Math.pow(1.0F - this.angularDamping, timeStep));
/* 267:    */      
/* 269:269 */      if (this.additionalDamping)
/* 270:    */      {
/* 272:272 */        if ((this.angularVelocity.lengthSquared() < this.additionalAngularDampingThresholdSqr) && (this.linearVelocity.lengthSquared() < this.additionalLinearDampingThresholdSqr))
/* 273:    */        {
/* 274:274 */          this.angularVelocity.scale(this.additionalDampingFactor);
/* 275:275 */          this.linearVelocity.scale(this.additionalDampingFactor);
/* 276:    */        }
/* 277:    */        
/* 278:278 */        float speed = this.linearVelocity.length();
/* 279:279 */        if (speed < this.linearDamping) {
/* 280:280 */          float dampVel = 0.005F;
/* 281:281 */          if (speed > dampVel) {
/* 282:282 */            Vector3f dir = localStack.get$javax$vecmath$Vector3f(this.linearVelocity);
/* 283:283 */            dir.normalize();
/* 284:284 */            dir.scale(dampVel);
/* 285:285 */            this.linearVelocity.sub(dir);
/* 286:    */          }
/* 287:    */          else {
/* 288:288 */            this.linearVelocity.set(0.0F, 0.0F, 0.0F);
/* 289:    */          }
/* 290:    */        }
/* 291:    */        
/* 292:292 */        float angSpeed = this.angularVelocity.length();
/* 293:293 */        if (angSpeed < this.angularDamping) {
/* 294:294 */          float angDampVel = 0.005F;
/* 295:295 */          if (angSpeed > angDampVel) {
/* 296:296 */            Vector3f dir = localStack.get$javax$vecmath$Vector3f(this.angularVelocity);
/* 297:297 */            dir.normalize();
/* 298:298 */            dir.scale(angDampVel);
/* 299:299 */            this.angularVelocity.sub(dir);
/* 300:    */          }
/* 301:    */          else {
/* 302:302 */            this.angularVelocity.set(0.0F, 0.0F, 0.0F);
/* 303:    */          }
/* 304:    */        }
/* 305:    */      }
/* 306:306 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 307:    */    } }
/* 308:    */  
/* 309:309 */  public void setMassProps(float mass, Vector3f inertia) { if (mass == 0.0F) {
/* 310:310 */      this.collisionFlags |= 1;
/* 311:311 */      this.inverseMass = 0.0F;
/* 312:    */    }
/* 313:    */    else {
/* 314:314 */      this.collisionFlags &= -2;
/* 315:315 */      this.inverseMass = (1.0F / mass);
/* 316:    */    }
/* 317:    */    
/* 318:318 */    this.invInertiaLocal.set(inertia.x != 0.0F ? 1.0F / inertia.x : 0.0F, inertia.y != 0.0F ? 1.0F / inertia.y : 0.0F, inertia.z != 0.0F ? 1.0F / inertia.z : 0.0F);
/* 319:    */  }
/* 320:    */  
/* 322:    */  public float getInvMass()
/* 323:    */  {
/* 324:324 */    return this.inverseMass;
/* 325:    */  }
/* 326:    */  
/* 327:    */  public Matrix3f getInvInertiaTensorWorld(Matrix3f out) {
/* 328:328 */    out.set(this.invInertiaTensorWorld);
/* 329:329 */    return out;
/* 330:    */  }
/* 331:    */  
/* 332:    */  public void integrateVelocities(float arg1) {
/* 333:333 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (isStaticOrKinematicObject()) {
/* 334:334 */        return;
/* 335:    */      }
/* 336:    */      
/* 337:337 */      this.linearVelocity.scaleAdd(this.inverseMass * step, this.totalForce, this.linearVelocity);
/* 338:338 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f(this.totalTorque);
/* 339:339 */      this.invInertiaTensorWorld.transform(tmp);
/* 340:340 */      this.angularVelocity.scaleAdd(step, tmp, this.angularVelocity);
/* 341:    */      
/* 343:343 */      float angvel = this.angularVelocity.length();
/* 344:344 */      if (angvel * step > 1.570796F)
/* 345:345 */        this.angularVelocity.scale(1.570796F / step / angvel);
/* 346:    */    } finally {
/* 347:347 */      localStack.pop$javax$vecmath$Vector3f();
/* 348:    */    } }
/* 349:    */  
/* 350:350 */  public void setCenterOfMassTransform(Transform xform) { if (isStaticOrKinematicObject()) {
/* 351:351 */      this.interpolationWorldTransform.set(this.worldTransform);
/* 352:    */    }
/* 353:    */    else {
/* 354:354 */      this.interpolationWorldTransform.set(xform);
/* 355:    */    }
/* 356:356 */    getLinearVelocity(this.interpolationLinearVelocity);
/* 357:357 */    getAngularVelocity(this.interpolationAngularVelocity);
/* 358:358 */    this.worldTransform.set(xform);
/* 359:359 */    updateInertiaTensor();
/* 360:    */  }
/* 361:    */  
/* 362:    */  public void applyCentralForce(Vector3f force) {
/* 363:363 */    this.totalForce.add(force);
/* 364:    */  }
/* 365:    */  
/* 366:    */  public Vector3f getInvInertiaDiagLocal(Vector3f out) {
/* 367:367 */    out.set(this.invInertiaLocal);
/* 368:368 */    return out;
/* 369:    */  }
/* 370:    */  
/* 371:    */  public void setInvInertiaDiagLocal(Vector3f diagInvInertia) {
/* 372:372 */    this.invInertiaLocal.set(diagInvInertia);
/* 373:    */  }
/* 374:    */  
/* 375:    */  public void setSleepingThresholds(float linear, float angular) {
/* 376:376 */    this.linearSleepingThreshold = linear;
/* 377:377 */    this.angularSleepingThreshold = angular;
/* 378:    */  }
/* 379:    */  
/* 380:    */  public void applyTorque(Vector3f torque) {
/* 381:381 */    this.totalTorque.add(torque);
/* 382:    */  }
/* 383:    */  
/* 384:    */  public void applyForce(Vector3f arg1, Vector3f arg2) {
/* 385:385 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();applyCentralForce(force);
/* 386:    */      
/* 387:387 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 388:388 */      tmp.cross(rel_pos, force);
/* 389:389 */      tmp.scale(this.angularFactor);
/* 390:390 */      applyTorque(tmp);
/* 391:391 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 392:    */    } }
/* 393:    */  
/* 394:394 */  public void applyCentralImpulse(Vector3f impulse) { this.linearVelocity.scaleAdd(this.inverseMass, impulse, this.linearVelocity); }
/* 395:    */  
/* 397:    */  public void applyTorqueImpulse(Vector3f arg1)
/* 398:    */  {
/* 399:399 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f(torque);
/* 400:400 */      this.invInertiaTensorWorld.transform(tmp);
/* 401:401 */      this.angularVelocity.add(tmp);
/* 402:402 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 403:    */    }
/* 404:    */  }
/* 405:    */  
/* 406:406 */  public void applyImpulse(Vector3f arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (this.inverseMass != 0.0F) {
/* 407:407 */        applyCentralImpulse(impulse);
/* 408:408 */        if (this.angularFactor != 0.0F) {
/* 409:409 */          Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 410:410 */          tmp.cross(rel_pos, impulse);
/* 411:411 */          tmp.scale(this.angularFactor);
/* 412:412 */          applyTorqueImpulse(tmp);
/* 413:    */        }
/* 414:    */      }
/* 415:415 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 416:    */    }
/* 417:    */  }
/* 418:    */  
/* 419:    */  public void internalApplyImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
/* 420:    */  {
/* 421:421 */    if (this.inverseMass != 0.0F) {
/* 422:422 */      this.linearVelocity.scaleAdd(impulseMagnitude, linearComponent, this.linearVelocity);
/* 423:423 */      if (this.angularFactor != 0.0F) {
/* 424:424 */        this.angularVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.angularVelocity);
/* 425:    */      }
/* 426:    */    }
/* 427:    */  }
/* 428:    */  
/* 429:    */  public void clearForces() {
/* 430:430 */    this.totalForce.set(0.0F, 0.0F, 0.0F);
/* 431:431 */    this.totalTorque.set(0.0F, 0.0F, 0.0F);
/* 432:    */  }
/* 433:    */  
/* 434:    */  public void updateInertiaTensor() {
/* 435:435 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Matrix3f();Matrix3f mat1 = localStack.get$javax$vecmath$Matrix3f();
/* 436:436 */      MatrixUtil.scale(mat1, this.worldTransform.basis, this.invInertiaLocal);
/* 437:    */      
/* 438:438 */      Matrix3f mat2 = localStack.get$javax$vecmath$Matrix3f(this.worldTransform.basis);
/* 439:439 */      mat2.transpose();
/* 440:    */      
/* 441:441 */      this.invInertiaTensorWorld.mul(mat1, mat2);
/* 442:442 */    } finally { localStack.pop$javax$vecmath$Matrix3f();
/* 443:    */    } }
/* 444:    */  
/* 445:445 */  public Vector3f getCenterOfMassPosition(Vector3f out) { out.set(this.worldTransform.origin);
/* 446:446 */    return out;
/* 447:    */  }
/* 448:    */  
/* 449:    */  public Quat4f getOrientation(Quat4f out) {
/* 450:450 */    MatrixUtil.getRotation(this.worldTransform.basis, out);
/* 451:451 */    return out;
/* 452:    */  }
/* 453:    */  
/* 454:    */  public Transform getCenterOfMassTransform(Transform out) {
/* 455:455 */    out.set(this.worldTransform);
/* 456:456 */    return out;
/* 457:    */  }
/* 458:    */  
/* 459:    */  public Vector3f getLinearVelocity(Vector3f out) {
/* 460:460 */    out.set(this.linearVelocity);
/* 461:461 */    return out;
/* 462:    */  }
/* 463:    */  
/* 464:    */  public Vector3f getAngularVelocity(Vector3f out) {
/* 465:465 */    out.set(this.angularVelocity);
/* 466:466 */    return out;
/* 467:    */  }
/* 468:    */  
/* 469:    */  public void setLinearVelocity(Vector3f lin_vel) {
/* 470:470 */    assert (this.collisionFlags != 1);
/* 471:471 */    this.linearVelocity.set(lin_vel);
/* 472:    */  }
/* 473:    */  
/* 474:    */  public void setAngularVelocity(Vector3f ang_vel) {
/* 475:475 */    assert (this.collisionFlags != 1);
/* 476:476 */    this.angularVelocity.set(ang_vel);
/* 477:    */  }
/* 478:    */  
/* 479:    */  public Vector3f getVelocityInLocalPoint(Vector3f rel_pos, Vector3f out)
/* 480:    */  {
/* 481:481 */    Vector3f vec = out;
/* 482:482 */    vec.cross(this.angularVelocity, rel_pos);
/* 483:483 */    vec.add(this.linearVelocity);
/* 484:484 */    return out;
/* 485:    */  }
/* 486:    */  
/* 489:    */  public void translate(Vector3f v)
/* 490:    */  {
/* 491:491 */    this.worldTransform.origin.add(v);
/* 492:    */  }
/* 493:    */  
/* 494:    */  public void getAabb(Vector3f aabbMin, Vector3f aabbMax) {
/* 495:495 */    getCollisionShape().getAabb(this.worldTransform, aabbMin, aabbMax);
/* 496:    */  }
/* 497:    */  
/* 498:    */  public float computeImpulseDenominator(Vector3f arg1, Vector3f arg2) {
/* 499:499 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f r0 = localStack.get$javax$vecmath$Vector3f();
/* 500:500 */      r0.sub(pos, getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
/* 501:    */      
/* 502:502 */      Vector3f c0 = localStack.get$javax$vecmath$Vector3f();
/* 503:503 */      c0.cross(r0, normal);
/* 504:    */      
/* 505:505 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 506:506 */      MatrixUtil.transposeTransform(tmp, c0, getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()));
/* 507:    */      
/* 508:508 */      Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/* 509:509 */      vec.cross(tmp, r0);
/* 510:    */      
/* 511:511 */      return this.inverseMass + normal.dot(vec); } finally { .Stack tmp109_107 = localStack;tmp109_107.pop$javax$vecmath$Vector3f();tmp109_107.pop$javax$vecmath$Matrix3f();
/* 512:    */    }
/* 513:    */  }
/* 514:    */  
/* 515:515 */  public float computeAngularImpulseDenominator(Vector3f arg1) { .Stack localStack = .Stack.get(); try { .Stack tmp5_4 = localStack;tmp5_4.push$javax$vecmath$Vector3f();tmp5_4.push$javax$vecmath$Matrix3f();Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/* 516:516 */      MatrixUtil.transposeTransform(vec, axis, getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()));
/* 517:517 */      return axis.dot(vec); } finally { .Stack tmp45_44 = localStack;tmp45_44.pop$javax$vecmath$Vector3f();tmp45_44.pop$javax$vecmath$Matrix3f();
/* 518:    */    }
/* 519:    */  }
/* 520:    */  
/* 521:521 */  public void updateDeactivation(float arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if ((getActivationState() == 2) || (getActivationState() == 4)) {
/* 522:522 */        return;
/* 523:    */      }
/* 524:    */      
/* 525:525 */      if ((getLinearVelocity(localStack.get$javax$vecmath$Vector3f()).lengthSquared() < this.linearSleepingThreshold * this.linearSleepingThreshold) && (getAngularVelocity(localStack.get$javax$vecmath$Vector3f()).lengthSquared() < this.angularSleepingThreshold * this.angularSleepingThreshold))
/* 526:    */      {
/* 527:527 */        this.deactivationTime += timeStep;
/* 528:    */      }
/* 529:    */      else {
/* 530:530 */        this.deactivationTime = 0.0F;
/* 531:531 */        setActivationState(0);
/* 532:    */      }
/* 533:533 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 534:    */    } }
/* 535:    */  
/* 536:536 */  public boolean wantsSleeping() { if (getActivationState() == 4) {
/* 537:537 */      return false;
/* 538:    */    }
/* 539:    */    
/* 541:541 */    if ((BulletGlobals.isDeactivationDisabled()) || (BulletGlobals.getDeactivationTime() == 0.0F)) {
/* 542:542 */      return false;
/* 543:    */    }
/* 544:    */    
/* 545:545 */    if ((getActivationState() == 2) || (getActivationState() == 3)) {
/* 546:546 */      return true;
/* 547:    */    }
/* 548:    */    
/* 549:549 */    if (this.deactivationTime > BulletGlobals.getDeactivationTime()) {
/* 550:550 */      return true;
/* 551:    */    }
/* 552:552 */    return false;
/* 553:    */  }
/* 554:    */  
/* 555:    */  public BroadphaseProxy getBroadphaseProxy() {
/* 556:556 */    return this.broadphaseHandle;
/* 557:    */  }
/* 558:    */  
/* 559:    */  public void setNewBroadphaseProxy(BroadphaseProxy broadphaseProxy) {
/* 560:560 */    this.broadphaseHandle = broadphaseProxy;
/* 561:    */  }
/* 562:    */  
/* 563:    */  public MotionState getMotionState() {
/* 564:564 */    return this.optionalMotionState;
/* 565:    */  }
/* 566:    */  
/* 567:    */  public void setMotionState(MotionState motionState) {
/* 568:568 */    this.optionalMotionState = motionState;
/* 569:569 */    if (this.optionalMotionState != null) {
/* 570:570 */      motionState.getWorldTransform(this.worldTransform);
/* 571:    */    }
/* 572:    */  }
/* 573:    */  
/* 574:    */  public void setAngularFactor(float angFac) {
/* 575:575 */    this.angularFactor = angFac;
/* 576:    */  }
/* 577:    */  
/* 578:    */  public float getAngularFactor() {
/* 579:579 */    return this.angularFactor;
/* 580:    */  }
/* 581:    */  
/* 584:    */  public boolean isInWorld()
/* 585:    */  {
/* 586:586 */    return getBroadphaseProxy() != null;
/* 587:    */  }
/* 588:    */  
/* 590:    */  public boolean checkCollideWithOverride(CollisionObject co)
/* 591:    */  {
/* 592:592 */    RigidBody otherRb = upcast(co);
/* 593:593 */    if (otherRb == null) {
/* 594:594 */      return true;
/* 595:    */    }
/* 596:    */    
/* 597:597 */    for (int i = 0; i < this.constraintRefs.size(); i++) {
/* 598:598 */      TypedConstraint c = (TypedConstraint)this.constraintRefs.getQuick(i);
/* 599:599 */      if ((c.getRigidBodyA() == otherRb) || (c.getRigidBodyB() == otherRb)) {
/* 600:600 */        return false;
/* 601:    */      }
/* 602:    */    }
/* 603:    */    
/* 604:604 */    return true;
/* 605:    */  }
/* 606:    */  
/* 607:    */  public void addConstraintRef(TypedConstraint c) {
/* 608:608 */    int index = this.constraintRefs.indexOf(c);
/* 609:609 */    if (index == -1) {
/* 610:610 */      this.constraintRefs.add(c);
/* 611:    */    }
/* 612:    */    
/* 613:613 */    this.checkCollideWith = true;
/* 614:    */  }
/* 615:    */  
/* 616:    */  public void removeConstraintRef(TypedConstraint c) {
/* 617:617 */    this.constraintRefs.remove(c);
/* 618:618 */    this.checkCollideWith = (this.constraintRefs.size() > 0);
/* 619:    */  }
/* 620:    */  
/* 621:    */  public TypedConstraint getConstraintRef(int index) {
/* 622:622 */    return (TypedConstraint)this.constraintRefs.getQuick(index);
/* 623:    */  }
/* 624:    */  
/* 625:    */  public int getNumConstraintRefs() {
/* 626:626 */    return this.constraintRefs.size();
/* 627:    */  }
/* 628:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.RigidBody
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */