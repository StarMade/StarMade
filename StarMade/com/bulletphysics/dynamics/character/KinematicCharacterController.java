/*   1:    */package com.bulletphysics.dynamics.character;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*   5:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   6:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   7:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   8:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   9:    */import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*  10:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  11:    */import com.bulletphysics.collision.dispatch.CollisionWorld;
/*  12:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*  13:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*  14:    */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*  15:    */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
/*  16:    */import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
/*  17:    */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*  18:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  19:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  20:    */import com.bulletphysics.dynamics.ActionInterface;
/*  21:    */import com.bulletphysics.linearmath.IDebugDraw;
/*  22:    */import com.bulletphysics.linearmath.Transform;
/*  23:    */import com.bulletphysics.util.ObjectArrayList;
/*  24:    */import java.io.PrintStream;
/*  25:    */import javax.vecmath.Matrix3f;
/*  26:    */import javax.vecmath.Vector3f;
/*  27:    */
/*  52:    */public class KinematicCharacterController
/*  53:    */  extends ActionInterface
/*  54:    */{
/*  55: 55 */  private static Vector3f[] upAxisDirection = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*  56:    */  
/*  58:    */  protected float halfHeight;
/*  59:    */  
/*  61:    */  protected PairCachingGhostObject ghostObject;
/*  62:    */  
/*  64:    */  protected ConvexShape convexShape;
/*  65:    */  
/*  66:    */  protected float verticalVelocity;
/*  67:    */  
/*  68:    */  protected float verticalOffset;
/*  69:    */  
/*  70:    */  protected float fallSpeed;
/*  71:    */  
/*  72:    */  protected float jumpSpeed;
/*  73:    */  
/*  74:    */  protected float maxJumpHeight;
/*  75:    */  
/*  76:    */  protected float maxSlopeRadians;
/*  77:    */  
/*  78:    */  protected float maxSlopeCosine;
/*  79:    */  
/*  80:    */  protected float gravity;
/*  81:    */  
/*  82:    */  protected float turnAngle;
/*  83:    */  
/*  84:    */  protected float stepHeight;
/*  85:    */  
/*  86:    */  protected float addedMargin;
/*  87:    */  
/*  88: 88 */  protected Vector3f walkDirection = new Vector3f();
/*  89: 89 */  protected Vector3f normalizedDirection = new Vector3f();
/*  90:    */  
/*  92: 92 */  protected Vector3f currentPosition = new Vector3f();
/*  93:    */  protected float currentStepOffset;
/*  94: 94 */  protected Vector3f targetPosition = new Vector3f();
/*  95:    */  
/*  97: 97 */  ObjectArrayList<PersistentManifold> manifoldArray = new ObjectArrayList();
/*  98:    */  
/*  99:    */  protected boolean touchingContact;
/* 100:100 */  protected Vector3f touchingNormal = new Vector3f();
/* 101:    */  
/* 102:    */  protected boolean wasOnGround;
/* 103:    */  
/* 104:    */  protected boolean useGhostObjectSweepTest;
/* 105:    */  protected boolean useWalkDirection;
/* 106:    */  protected float velocityTimeInterval;
/* 107:    */  protected int upAxis;
/* 108:    */  protected CollisionObject me;
/* 109:    */  
/* 110:    */  public KinematicCharacterController(PairCachingGhostObject ghostObject, ConvexShape convexShape, float stepHeight)
/* 111:    */  {
/* 112:112 */    this(ghostObject, convexShape, stepHeight, 1);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public KinematicCharacterController(PairCachingGhostObject ghostObject, ConvexShape convexShape, float stepHeight, int upAxis) {
/* 116:116 */    this.upAxis = upAxis;
/* 117:117 */    this.addedMargin = 0.02F;
/* 118:118 */    this.walkDirection.set(0.0F, 0.0F, 0.0F);
/* 119:119 */    this.useGhostObjectSweepTest = true;
/* 120:120 */    this.ghostObject = ghostObject;
/* 121:121 */    this.stepHeight = stepHeight;
/* 122:122 */    this.turnAngle = 0.0F;
/* 123:123 */    this.convexShape = convexShape;
/* 124:124 */    this.useWalkDirection = true;
/* 125:125 */    this.velocityTimeInterval = 0.0F;
/* 126:126 */    this.verticalVelocity = 0.0F;
/* 127:127 */    this.verticalOffset = 0.0F;
/* 128:128 */    this.gravity = 9.8F;
/* 129:129 */    this.fallSpeed = 55.0F;
/* 130:130 */    this.jumpSpeed = 10.0F;
/* 131:131 */    this.wasOnGround = false;
/* 132:132 */    setMaxSlope(0.8726647F);
/* 133:    */  }
/* 134:    */  
/* 135:    */  private PairCachingGhostObject getGhostObject() {
/* 136:136 */    return this.ghostObject;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void updateAction(CollisionWorld collisionWorld, float deltaTime)
/* 140:    */  {
/* 141:141 */    preStep(collisionWorld);
/* 142:142 */    playerStep(collisionWorld, deltaTime);
/* 143:    */  }
/* 144:    */  
/* 146:    */  public void debugDraw(IDebugDraw debugDrawer) {}
/* 147:    */  
/* 148:    */  public void setUpAxis(int axis)
/* 149:    */  {
/* 150:150 */    if (axis < 0) {
/* 151:151 */      axis = 0;
/* 152:    */    }
/* 153:153 */    if (axis > 2) {
/* 154:154 */      axis = 2;
/* 155:    */    }
/* 156:156 */    this.upAxis = axis;
/* 157:    */  }
/* 158:    */  
/* 165:    */  public void setWalkDirection(Vector3f arg1)
/* 166:    */  {
/* 167:167 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.useWalkDirection = true;
/* 168:168 */      this.walkDirection.set(walkDirection);
/* 169:169 */      this.normalizedDirection.set(getNormalizedVector(walkDirection, localStack.get$javax$vecmath$Vector3f()));
/* 170:170 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 171:    */    }
/* 172:    */  }
/* 173:    */  
/* 177:    */  public void setVelocityForTimeInterval(Vector3f arg1, float arg2)
/* 178:    */  {
/* 179:179 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.useWalkDirection = false;
/* 180:180 */      this.walkDirection.set(velocity);
/* 181:181 */      this.normalizedDirection.set(getNormalizedVector(this.walkDirection, localStack.get$javax$vecmath$Vector3f()));
/* 182:182 */      this.velocityTimeInterval = timeInterval;
/* 183:183 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void reset() {}
/* 188:    */  
/* 189:189 */  public void warp(Vector3f arg1) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform xform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 190:190 */      xform.setIdentity();
/* 191:191 */      xform.origin.set(origin);
/* 192:192 */      this.ghostObject.setWorldTransform(xform);
/* 193:193 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 194:    */    } }
/* 195:    */  
/* 196:196 */  public void preStep(CollisionWorld arg1) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();int numPenetrationLoops = 0;
/* 197:197 */      this.touchingContact = false;
/* 198:198 */      while (recoverFromPenetration(collisionWorld)) {
/* 199:199 */        numPenetrationLoops++;
/* 200:200 */        this.touchingContact = true;
/* 201:201 */        if (numPenetrationLoops > 4)
/* 202:    */        {
/* 203:203 */          break;
/* 204:    */        }
/* 205:    */      }
/* 206:    */      
/* 207:207 */      this.currentPosition.set(this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/* 208:208 */      this.targetPosition.set(this.currentPosition);
/* 209:    */    } finally {
/* 210:210 */      localStack.pop$com$bulletphysics$linearmath$Transform();
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 215:    */  public void playerStep(CollisionWorld arg1, float arg2)
/* 216:    */  {
/* 217:217 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f(); if ((!this.useWalkDirection) && (this.velocityTimeInterval <= 0.0F))
/* 218:    */      {
/* 219:219 */        return;
/* 220:    */      }
/* 221:    */      
/* 222:222 */      this.wasOnGround = onGround();
/* 223:    */      
/* 225:225 */      this.verticalVelocity -= this.gravity * dt;
/* 226:226 */      if ((this.verticalVelocity > 0.0D) && (this.verticalVelocity > this.jumpSpeed))
/* 227:    */      {
/* 228:228 */        this.verticalVelocity = this.jumpSpeed;
/* 229:    */      }
/* 230:230 */      if ((this.verticalVelocity < 0.0D) && (Math.abs(this.verticalVelocity) > Math.abs(this.fallSpeed)))
/* 231:    */      {
/* 232:232 */        this.verticalVelocity = (-Math.abs(this.fallSpeed));
/* 233:    */      }
/* 234:234 */      this.verticalOffset = (this.verticalVelocity * dt);
/* 235:    */      
/* 236:236 */      Transform xform = this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 237:    */      
/* 241:241 */      stepUp(collisionWorld);
/* 242:242 */      if (this.useWalkDirection)
/* 243:    */      {
/* 244:244 */        stepForwardAndStrafe(collisionWorld, this.walkDirection);
/* 245:    */      }
/* 246:    */      else {
/* 247:247 */        System.out.println("playerStep 4");
/* 248:    */        
/* 251:251 */        float dtMoving = dt < this.velocityTimeInterval ? dt : this.velocityTimeInterval;
/* 252:252 */        this.velocityTimeInterval -= dt;
/* 253:    */        
/* 255:255 */        Vector3f move = localStack.get$javax$vecmath$Vector3f();
/* 256:256 */        move.scale(dtMoving, this.walkDirection);
/* 257:    */        
/* 261:261 */        stepForwardAndStrafe(collisionWorld, move);
/* 262:    */      }
/* 263:263 */      stepDown(collisionWorld, dt);
/* 264:    */      
/* 267:267 */      xform.origin.set(this.currentPosition);
/* 268:268 */      this.ghostObject.setWorldTransform(xform);
/* 269:269 */    } finally { .Stack tmp279_277 = localStack;tmp279_277.pop$com$bulletphysics$linearmath$Transform();tmp279_277.pop$javax$vecmath$Vector3f();
/* 270:    */    } }
/* 271:    */  
/* 272:272 */  public void setFallSpeed(float fallSpeed) { this.fallSpeed = fallSpeed; }
/* 273:    */  
/* 274:    */  public void setJumpSpeed(float jumpSpeed)
/* 275:    */  {
/* 276:276 */    this.jumpSpeed = jumpSpeed;
/* 277:    */  }
/* 278:    */  
/* 279:    */  public void setMaxJumpHeight(float maxJumpHeight) {
/* 280:280 */    this.maxJumpHeight = maxJumpHeight;
/* 281:    */  }
/* 282:    */  
/* 283:    */  public boolean canJump() {
/* 284:284 */    return onGround();
/* 285:    */  }
/* 286:    */  
/* 287:    */  public void jump() {
/* 288:288 */    if (!canJump()) { return;
/* 289:    */    }
/* 290:290 */    this.verticalVelocity = this.jumpSpeed;
/* 291:    */  }
/* 292:    */  
/* 302:    */  public void setGravity(float gravity)
/* 303:    */  {
/* 304:304 */    this.gravity = gravity;
/* 305:    */  }
/* 306:    */  
/* 307:    */  public float getGravity() {
/* 308:308 */    return this.gravity;
/* 309:    */  }
/* 310:    */  
/* 311:    */  public void setMaxSlope(float slopeRadians) {
/* 312:312 */    this.maxSlopeRadians = slopeRadians;
/* 313:313 */    this.maxSlopeCosine = ((float)Math.cos(slopeRadians));
/* 314:    */  }
/* 315:    */  
/* 316:    */  public float getMaxSlope() {
/* 317:317 */    return this.maxSlopeRadians;
/* 318:    */  }
/* 319:    */  
/* 320:    */  public boolean onGround() {
/* 321:321 */    return (this.verticalVelocity == 0.0F) && (this.verticalOffset == 0.0F);
/* 322:    */  }
/* 323:    */  
/* 324:    */  private static Vector3f getNormalizedVector(Vector3f v, Vector3f out)
/* 325:    */  {
/* 326:326 */    out.set(v);
/* 327:327 */    out.normalize();
/* 328:328 */    if (out.length() < 1.192093E-007F) {
/* 329:329 */      out.set(0.0F, 0.0F, 0.0F);
/* 330:    */    }
/* 331:331 */    return out;
/* 332:    */  }
/* 333:    */  
/* 340:    */  protected Vector3f computeReflectionDirection(Vector3f direction, Vector3f normal, Vector3f out)
/* 341:    */  {
/* 342:342 */    out.set(normal);
/* 343:343 */    out.scale(-2.0F * direction.dot(normal));
/* 344:344 */    out.add(direction);
/* 345:345 */    return out;
/* 346:    */  }
/* 347:    */  
/* 352:    */  protected Vector3f parallelComponent(Vector3f direction, Vector3f normal, Vector3f out)
/* 353:    */  {
/* 354:354 */    out.set(normal);
/* 355:355 */    out.scale(direction.dot(normal));
/* 356:356 */    return out;
/* 357:    */  }
/* 358:    */  
/* 362:    */  protected Vector3f perpindicularComponent(Vector3f direction, Vector3f normal, Vector3f out)
/* 363:    */  {
/* 364:364 */    Vector3f perpendicular = parallelComponent(direction, normal, out);
/* 365:365 */    perpendicular.scale(-1.0F);
/* 366:366 */    perpendicular.add(direction);
/* 367:367 */    return perpendicular;
/* 368:    */  }
/* 369:    */  
/* 370:    */  protected boolean recoverFromPenetration(CollisionWorld arg1) {
/* 371:371 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();boolean penetration = false;
/* 372:    */      
/* 373:373 */      collisionWorld.getDispatcher().dispatchAllCollisionPairs(this.ghostObject.getOverlappingPairCache(), collisionWorld.getDispatchInfo(), collisionWorld.getDispatcher());
/* 374:    */      
/* 376:376 */      this.currentPosition.set(this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/* 377:    */      
/* 378:378 */      float maxPen = 0.0F;
/* 379:379 */      for (int i = 0; i < this.ghostObject.getOverlappingPairCache().getNumOverlappingPairs(); i++) {
/* 380:380 */        this.manifoldArray.clear();
/* 381:    */        
/* 382:382 */        BroadphasePair collisionPair = (BroadphasePair)this.ghostObject.getOverlappingPairCache().getOverlappingPairArray().getQuick(i);
/* 383:    */        
/* 384:384 */        if (collisionPair.algorithm != null) {
/* 385:385 */          collisionPair.algorithm.getAllContactManifolds(this.manifoldArray);
/* 386:    */        }
/* 387:    */        
/* 388:388 */        for (int j = 0; j < this.manifoldArray.size(); j++) {
/* 389:389 */          PersistentManifold manifold = (PersistentManifold)this.manifoldArray.getQuick(j);
/* 390:390 */          float directionSign = manifold.getBody0() == this.ghostObject ? -1.0F : 1.0F;
/* 391:391 */          for (int p = 0; p < manifold.getNumContacts(); p++) {
/* 392:392 */            ManifoldPoint pt = manifold.getContactPoint(p);
/* 393:    */            
/* 394:394 */            float dist = pt.getDistance();
/* 395:395 */            if (dist < 0.0F) {
/* 396:396 */              if (dist < maxPen) {
/* 397:397 */                maxPen = dist;
/* 398:398 */                this.touchingNormal.set(pt.normalWorldOnB);
/* 399:399 */                this.touchingNormal.scale(directionSign);
/* 400:    */              }
/* 401:    */              
/* 402:402 */              this.currentPosition.scaleAdd(directionSign * dist * 0.2F, pt.normalWorldOnB, this.currentPosition);
/* 403:    */              
/* 404:404 */              penetration = true;
/* 405:    */            }
/* 406:    */          }
/* 407:    */        }
/* 408:    */      }
/* 409:    */      
/* 415:415 */      Transform newTrans = this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 416:416 */      newTrans.origin.set(this.currentPosition);
/* 417:417 */      this.ghostObject.setWorldTransform(newTrans);
/* 418:    */      
/* 422:422 */      return penetration; } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 423:    */    }
/* 424:    */  }
/* 425:    */  
/* 426:    */  protected void stepUp(CollisionWorld arg1) {
/* 427:427 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
/* 428:428 */      Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
/* 429:429 */      this.targetPosition.scaleAdd(this.stepHeight + (this.verticalOffset > 0.0D ? this.verticalOffset : 0.0F), upAxisDirection[this.upAxis], this.currentPosition);
/* 430:    */      
/* 431:431 */      start.setIdentity();
/* 432:432 */      end.setIdentity();
/* 433:    */      
/* 435:435 */      start.origin.scaleAdd(this.convexShape.getMargin() + this.addedMargin, upAxisDirection[this.upAxis], this.currentPosition);
/* 436:436 */      end.origin.set(this.targetPosition);
/* 437:    */      
/* 439:439 */      Vector3f up = localStack.get$javax$vecmath$Vector3f();
/* 440:440 */      up.scale(-1.0F, upAxisDirection[this.upAxis]);
/* 441:441 */      KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, up, 0.0F);
/* 442:442 */      callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
/* 443:443 */      callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
/* 444:    */      
/* 445:445 */      if (this.useGhostObjectSweepTest) {
/* 446:446 */        this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, world.getDispatchInfo().allowedCcdPenetration);
/* 447:    */      }
/* 448:    */      else {
/* 449:449 */        world.convexSweepTest(this.convexShape, start, end, callback);
/* 450:    */      }
/* 451:    */      
/* 452:452 */      if (callback.hasHit())
/* 453:    */      {
/* 454:454 */        this.currentStepOffset = (this.stepHeight * callback.closestHitFraction);
/* 455:455 */        this.currentPosition.interpolate(this.currentPosition, this.targetPosition, callback.closestHitFraction);
/* 456:456 */        this.verticalVelocity = 0.0F;
/* 457:457 */        this.verticalOffset = 0.0F;
/* 458:    */      }
/* 459:    */      else {
/* 460:460 */        this.currentStepOffset = this.stepHeight;
/* 461:461 */        this.currentPosition.set(this.targetPosition);
/* 462:    */      }
/* 463:463 */    } finally { .Stack tmp317_315 = localStack;tmp317_315.pop$com$bulletphysics$linearmath$Transform();tmp317_315.pop$javax$vecmath$Vector3f();
/* 464:    */    } }
/* 465:    */  
/* 466:466 */  protected void updateTargetPositionBasedOnCollision(Vector3f hitNormal) { updateTargetPositionBasedOnCollision(hitNormal, 0.0F, 1.0F); }
/* 467:    */  
/* 468:    */  protected void updateTargetPositionBasedOnCollision(Vector3f arg1, float arg2, float arg3)
/* 469:    */  {
/* 470:470 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f movementDirection = localStack.get$javax$vecmath$Vector3f();
/* 471:471 */      movementDirection.sub(this.targetPosition, this.currentPosition);
/* 472:472 */      float movementLength = movementDirection.length();
/* 473:473 */      if (movementLength > 1.192093E-007F) {
/* 474:474 */        movementDirection.normalize();
/* 475:    */        
/* 476:476 */        Vector3f reflectDir = computeReflectionDirection(movementDirection, hitNormal, localStack.get$javax$vecmath$Vector3f());
/* 477:477 */        reflectDir.normalize();
/* 478:    */        
/* 479:479 */        Vector3f parallelDir = parallelComponent(reflectDir, hitNormal, localStack.get$javax$vecmath$Vector3f());
/* 480:480 */        Vector3f perpindicularDir = perpindicularComponent(reflectDir, hitNormal, localStack.get$javax$vecmath$Vector3f());
/* 481:    */        
/* 482:482 */        this.targetPosition.set(this.currentPosition);
/* 483:    */        
/* 491:491 */        if (normalMag != 0.0F) {
/* 492:492 */          Vector3f perpComponent = localStack.get$javax$vecmath$Vector3f();
/* 493:493 */          perpComponent.scale(normalMag * movementLength, perpindicularDir);
/* 494:    */          
/* 495:495 */          this.targetPosition.add(perpComponent);
/* 496:    */        }
/* 497:    */      }
/* 498:    */    }
/* 499:    */    finally
/* 500:    */    {
/* 501:501 */      localStack.pop$javax$vecmath$Vector3f();
/* 502:    */    }
/* 503:    */  }
/* 504:    */  
/* 505:    */  protected void stepForwardAndStrafe(CollisionWorld arg1, Vector3f arg2)
/* 506:    */  {
/* 507:507 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
/* 508:508 */      Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
/* 509:509 */      this.targetPosition.add(this.currentPosition, walkMove);
/* 510:510 */      start.setIdentity();
/* 511:511 */      end.setIdentity();
/* 512:    */      
/* 513:513 */      float fraction = 1.0F;
/* 514:514 */      Vector3f distance2Vec = localStack.get$javax$vecmath$Vector3f();
/* 515:515 */      distance2Vec.sub(this.currentPosition, this.targetPosition);
/* 516:516 */      float distance2 = distance2Vec.lengthSquared();
/* 517:    */      
/* 525:525 */      int maxIter = 10;
/* 526:    */      
/* 527:527 */      while ((fraction > 0.01F) && (maxIter-- > 0)) {
/* 528:528 */        start.origin.set(this.currentPosition);
/* 529:529 */        end.origin.set(this.targetPosition);
/* 530:    */        
/* 531:531 */        KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, upAxisDirection[this.upAxis], -1.0F);
/* 532:532 */        callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
/* 533:533 */        callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
/* 534:    */        
/* 535:535 */        float margin = this.convexShape.getMargin();
/* 536:536 */        this.convexShape.setMargin(margin + this.addedMargin);
/* 537:    */        
/* 538:538 */        if (this.useGhostObjectSweepTest) {
/* 539:539 */          this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, collisionWorld.getDispatchInfo().allowedCcdPenetration);
/* 540:    */        }
/* 541:    */        else {
/* 542:542 */          collisionWorld.convexSweepTest(this.convexShape, start, end, callback);
/* 543:    */        }
/* 544:    */        
/* 545:545 */        this.convexShape.setMargin(margin);
/* 546:    */        
/* 547:547 */        fraction -= callback.closestHitFraction;
/* 548:    */        
/* 549:549 */        if (callback.hasHit())
/* 550:    */        {
/* 551:551 */          Vector3f hitDistanceVec = localStack.get$javax$vecmath$Vector3f();
/* 552:552 */          hitDistanceVec.sub(callback.hitPointWorld, this.currentPosition);
/* 553:    */          
/* 561:561 */          updateTargetPositionBasedOnCollision(callback.hitNormalWorld);
/* 562:    */          
/* 563:563 */          Vector3f currentDir = localStack.get$javax$vecmath$Vector3f();
/* 564:564 */          currentDir.sub(this.targetPosition, this.currentPosition);
/* 565:565 */          distance2 = currentDir.lengthSquared();
/* 566:566 */          if (distance2 <= 1.192093E-007F) break;
/* 567:567 */          currentDir.normalize();
/* 568:    */          
/* 569:569 */          if (currentDir.dot(this.normalizedDirection) <= 0.0F)
/* 570:    */          {
/* 571:    */            break;
/* 573:    */          }
/* 574:    */          
/* 576:    */        }
/* 577:    */        else
/* 578:    */        {
/* 580:580 */          this.currentPosition.set(this.targetPosition);
/* 581:    */        }
/* 582:    */      }
/* 583:    */    }
/* 584:    */    finally
/* 585:    */    {
/* 586:586 */      .Stack tmp389_387 = localStack;tmp389_387.pop$com$bulletphysics$linearmath$Transform();tmp389_387.pop$javax$vecmath$Vector3f();
/* 587:    */    } }
/* 588:    */  
/* 589:589 */  protected void stepDown(CollisionWorld arg1, float arg2) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
/* 590:590 */      Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
/* 591:    */      
/* 593:593 */      float additionalDownStep = this.wasOnGround ? this.stepHeight : 0.0F;
/* 594:594 */      Vector3f step_drop = localStack.get$javax$vecmath$Vector3f();
/* 595:595 */      step_drop.scale(this.currentStepOffset + additionalDownStep, upAxisDirection[this.upAxis]);
/* 596:596 */      float downVelocity = ((additionalDownStep == 0.0F) && (this.verticalVelocity < 0.0F) ? -this.verticalVelocity : 0.0F) * dt;
/* 597:597 */      Vector3f gravity_drop = localStack.get$javax$vecmath$Vector3f();
/* 598:598 */      gravity_drop.scale(downVelocity, upAxisDirection[this.upAxis]);
/* 599:599 */      this.targetPosition.sub(step_drop);
/* 600:600 */      this.targetPosition.sub(gravity_drop);
/* 601:    */      
/* 602:602 */      start.setIdentity();
/* 603:603 */      end.setIdentity();
/* 604:    */      
/* 605:605 */      start.origin.set(this.currentPosition);
/* 606:606 */      end.origin.set(this.targetPosition);
/* 607:    */      
/* 608:608 */      KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, upAxisDirection[this.upAxis], this.maxSlopeCosine);
/* 609:609 */      callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
/* 610:610 */      callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
/* 611:    */      
/* 612:612 */      if (this.useGhostObjectSweepTest) {
/* 613:613 */        this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, collisionWorld.getDispatchInfo().allowedCcdPenetration);
/* 614:    */      }
/* 615:    */      else {
/* 616:616 */        collisionWorld.convexSweepTest(this.convexShape, start, end, callback);
/* 617:    */      }
/* 618:    */      
/* 619:619 */      if (callback.hasHit())
/* 620:    */      {
/* 621:621 */        this.currentPosition.interpolate(this.currentPosition, this.targetPosition, callback.closestHitFraction);
/* 622:622 */        this.verticalVelocity = 0.0F;
/* 623:623 */        this.verticalOffset = 0.0F;
/* 624:    */      }
/* 625:    */      else
/* 626:    */      {
/* 627:627 */        this.currentPosition.set(this.targetPosition);
/* 628:    */      }
/* 629:629 */    } finally { .Stack tmp337_335 = localStack;tmp337_335.pop$com$bulletphysics$linearmath$Transform();tmp337_335.pop$javax$vecmath$Vector3f();
/* 630:    */    }
/* 631:    */  }
/* 632:    */  
/* 633:    */  private static class KinematicClosestNotMeRayResultCallback extends CollisionWorld.ClosestRayResultCallback {
/* 634:    */    protected CollisionObject me;
/* 635:    */    
/* 636:    */    public KinematicClosestNotMeRayResultCallback(CollisionObject me) {
/* 637:637 */      super(new Vector3f());
/* 638:638 */      this.me = me;
/* 639:    */    }
/* 640:    */    
/* 641:    */    public float addSingleResult(CollisionWorld.LocalRayResult rayResult, boolean normalInWorldSpace)
/* 642:    */    {
/* 643:643 */      if (rayResult.collisionObject == this.me) {
/* 644:644 */        return 1.0F;
/* 645:    */      }
/* 646:    */      
/* 647:647 */      return super.addSingleResult(rayResult, normalInWorldSpace);
/* 648:    */    }
/* 649:    */  }
/* 650:    */  
/* 651:    */  private static class KinematicClosestNotMeConvexResultCallback extends CollisionWorld.ClosestConvexResultCallback
/* 652:    */  {
/* 653:    */    protected CollisionObject me;
/* 654:    */    protected final Vector3f up;
/* 655:    */    protected float minSlopeDot;
/* 656:    */    
/* 657:    */    public KinematicClosestNotMeConvexResultCallback(CollisionObject me, Vector3f up, float minSlopeDot)
/* 658:    */    {
/* 659:659 */      super(new Vector3f());
/* 660:660 */      this.me = me;
/* 661:661 */      this.up = up;
/* 662:662 */      this.minSlopeDot = minSlopeDot;
/* 663:    */    }
/* 664:    */    
/* 665:    */    public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
/* 666:    */    {
/* 667:667 */      .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f(); if (convexResult.hitCollisionObject == this.me) {
/* 668:668 */          return 1.0F;
/* 669:    */        }
/* 670:    */        Vector3f hitNormalWorld;
/* 671:    */        Vector3f hitNormalWorld;
/* 672:672 */        if (normalInWorldSpace) {
/* 673:673 */          hitNormalWorld = convexResult.hitNormalLocal;
/* 674:    */        }
/* 675:    */        else {
/* 676:676 */          hitNormalWorld = localStack.get$javax$vecmath$Vector3f();
/* 677:677 */          this.hitCollisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(convexResult.hitNormalLocal, hitNormalWorld);
/* 678:    */        }
/* 679:    */        
/* 680:680 */        float dotUp = this.up.dot(hitNormalWorld);
/* 681:681 */        if (dotUp < this.minSlopeDot) {
/* 682:682 */          return 1.0F;
/* 683:    */        }
/* 684:    */        
/* 685:685 */        return super.addSingleResult(convexResult, normalInWorldSpace); } finally { .Stack tmp126_124 = localStack;tmp126_124.pop$com$bulletphysics$linearmath$Transform();tmp126_124.pop$javax$vecmath$Vector3f();
/* 686:    */      }
/* 687:    */    }
/* 688:    */  }
/* 689:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.character.KinematicCharacterController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */