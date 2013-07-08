/*    1:     */package org.schema.game.common.data.physics;
/*    2:     */
/*    3:     */import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*    4:     */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    5:     */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    6:     */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    7:     */import com.bulletphysics.collision.broadphase.Dispatcher;
/*    8:     */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    9:     */import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*   10:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   11:     */import com.bulletphysics.collision.dispatch.CollisionWorld;
/*   12:     */import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
/*   13:     */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*   14:     */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   15:     */import com.bulletphysics.collision.shapes.CapsuleShape;
/*   16:     */import com.bulletphysics.collision.shapes.CollisionShape;
/*   17:     */import com.bulletphysics.collision.shapes.ConvexShape;
/*   18:     */import com.bulletphysics.dynamics.character.KinematicCharacterController;
/*   19:     */import com.bulletphysics.linearmath.Transform;
/*   20:     */import com.bulletphysics.util.ObjectArrayList;
/*   21:     */import ct;
/*   22:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   23:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   24:     */import jR;
/*   25:     */import java.io.PrintStream;
/*   26:     */import java.util.Iterator;
/*   27:     */import javax.vecmath.Matrix3f;
/*   28:     */import javax.vecmath.Matrix4f;
/*   29:     */import javax.vecmath.Vector3f;
/*   30:     */import lD;
/*   31:     */import le;
/*   32:     */import org.schema.game.common.controller.SegmentController;
/*   33:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   34:     */import org.schema.schine.network.NetworkStateContainer;
/*   35:     */import org.schema.schine.network.StateInterface;
/*   36:     */import org.schema.schine.network.objects.Sendable;
/*   37:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   38:     */import org.schema.schine.network.server.ServerStateInterface;
/*   39:     */import xq;
/*   40:     */
/*  125:     */public class KinematicCharacterControllerExt
/*  126:     */  extends KinematicCharacterController
/*  127:     */{
/*  128: 128 */  private float extendedCharacterHeight = 1.7F;
/*  129:     */  
/*  130: 130 */  public static Vector3f[] upAxisDirectionDefault = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*  131:     */  
/*  136: 136 */  public Vector3f[] upAxisDirection = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*  137:     */  
/*  139:     */  private lD obj;
/*  140:     */  
/*  142: 142 */  Vector3f move = new Vector3f();
/*  143: 143 */  Transform xform = new Transform();
/*  144: 144 */  Transform start = new Transform();
/*  145:     */  
/*  146: 146 */  Transform end = new Transform();
/*  147: 147 */  Vector3f distance2Vec = new Vector3f();
/*  148: 148 */  Vector3f hitDistanceVec = new Vector3f();
/*  149: 149 */  Vector3f currentDir = new Vector3f();
/*  150: 150 */  Transform tmp = new Transform();
/*  151: 151 */  Vector3f before = new Vector3f();
/*  152:     */  
/*  153:     */  Vector3f after;
/*  154:     */  
/*  155:     */  int penetrationCounter;
/*  156:     */  
/*  157:     */  private CapsuleShape capsule;
/*  158:     */  
/*  159:     */  private CapsuleShape strafeCapsule;
/*  160: 160 */  ObjectArrayList manifoldArray = new ObjectArrayList();
/*  161: 161 */  Transform ttmp = new Transform();
/*  162:     */  
/*  163: 163 */  Vector3f normal = new Vector3f();
/*  164:     */  private float localJumpSpeed;
/*  165:     */  private long jumpingStarted;
/*  166: 166 */  private Vector3f velocity = new Vector3f();
/*  167:     */  
/*  168:     */  public KinematicCharacterControllerExt(lD paramlD, PairCachingGhostObjectExt paramPairCachingGhostObjectExt, ConvexShape paramConvexShape, float paramFloat) {
/*  169: 169 */    super(paramPairCachingGhostObjectExt, paramConvexShape, paramFloat);
/*  170: 170 */    this.obj = paramlD;
/*  171:     */  }
/*  172:     */  
/*  173:     */  public void setLocalJumpSpeed(float paramFloat)
/*  174:     */  {
/*  175: 175 */    this.localJumpSpeed = paramFloat;
/*  176:     */  }
/*  177:     */  
/*  180:     */  public void breakJump(xq paramxq)
/*  181:     */  {
/*  182: 182 */    if ((this.verticalVelocity > 0.0F) && (this.jumpingStarted != -1L))
/*  183:     */    {
/*  184: 184 */      if (System.currentTimeMillis() - this.jumpingStarted > 80L)
/*  185:     */      {
/*  186: 186 */        this.verticalVelocity = Math.max(0.0F, this.verticalVelocity - paramxq.a() * 50.0F);
/*  187:     */      }
/*  188:     */      
/*  189: 189 */      return; }
/*  190: 190 */    this.jumpingStarted = -1L;
/*  191:     */  }
/*  192:     */  
/*  194:     */  public void jump()
/*  195:     */  {
/*  196: 196 */    if (!canJump())
/*  197:     */    {
/*  198: 198 */      return;
/*  199:     */    }
/*  200:     */    
/*  201: 201 */    this.jumpingStarted = System.currentTimeMillis();
/*  202: 202 */    this.verticalVelocity = this.jumpSpeed;
/*  203:     */  }
/*  204:     */  
/*  216:     */  public void loadAttached(CollisionWorld paramCollisionWorld)
/*  217:     */  {
/*  218: 218 */    if ((paramCollisionWorld = (PairCachingGhostObjectAlignable)this.ghostObject).getAttached() != null)
/*  219:     */    {
/*  220: 220 */      Object localObject = paramCollisionWorld.getAttached().getWorldTransform();
/*  221:     */      
/*  222: 222 */      if (paramCollisionWorld.getAttached().getPhysicsDataContainer().getObject() != null) {
/*  223: 223 */        paramCollisionWorld.getAttached().getPhysicsDataContainer().getObject().activate(true);
/*  224:     */      }
/*  225:     */      
/*  233: 233 */      GlUtil.e(this.upAxisDirection[0], (Transform)localObject);
/*  234: 234 */      GlUtil.f(this.upAxisDirection[1], (Transform)localObject);
/*  235: 235 */      GlUtil.c(this.upAxisDirection[2], (Transform)localObject);
/*  236:     */      
/*  240: 240 */      localObject = ((Transform)localObject).getMatrix(new Matrix4f());
/*  241: 241 */      Matrix4f localMatrix4f = paramCollisionWorld.localWorldTransform.getMatrix(new Matrix4f());
/*  242:     */      
/*  244: 244 */      (
/*  245: 245 */        localObject = new Matrix4f((Matrix4f)localObject)).mul(localMatrix4f);
/*  246:     */      
/*  247: 247 */      paramCollisionWorld.setWorldTransform(new Transform((Matrix4f)localObject));
/*  248:     */    }
/*  249:     */  }
/*  250:     */  
/*  256:     */  public void updateAction(CollisionWorld paramCollisionWorld, float paramFloat)
/*  257:     */  {
/*  258: 258 */    preStep(paramCollisionWorld);
/*  259:     */    
/*  260: 260 */    playerStep(paramCollisionWorld, paramFloat);
/*  261:     */    
/*  262: 262 */    this.obj.getPhysicsDataContainer().updatePhysical();
/*  263: 263 */    if ((!this.obj.isOnServer()) && (((ct)this.obj.getState()).a() == this.obj))
/*  264: 264 */      this.obj.a = true;
/*  265:     */  }
/*  266:     */  
/*  267:     */  private void adaptLocalAttached() {
/*  268:     */    PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable;
/*  269: 269 */    if ((localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)this.ghostObject).getAttached() != null)
/*  270:     */    {
/*  271: 271 */      Matrix4f localMatrix4f1 = localPairCachingGhostObjectAlignable.getAttached().getWorldTransform().getMatrix(new Matrix4f());
/*  272: 272 */      new Matrix4f(localMatrix4f1);
/*  273: 273 */      localPairCachingGhostObjectAlignable.localWorldTransform.getMatrix(new Matrix4f());
/*  274:     */      
/*  275: 275 */      (
/*  276: 276 */        localMatrix4f1 = new Matrix4f(localMatrix4f1)).invert();
/*  277:     */      
/*  278: 278 */      Matrix4f localMatrix4f2 = this.xform.getMatrix(new Matrix4f());
/*  279:     */      
/*  280: 280 */      localMatrix4f1.mul(localMatrix4f2);
/*  281:     */      
/*  282: 282 */      localPairCachingGhostObjectAlignable.localWorldTransform.set(localMatrix4f1);
/*  283:     */    }
/*  284:     */  }
/*  285:     */  
/*  290:     */  public void preStep(CollisionWorld paramCollisionWorld)
/*  291:     */  {
/*  292: 292 */    loadAttached(paramCollisionWorld);
/*  293:     */    
/*  294: 294 */    int i = 0;
/*  295:     */    
/*  296: 296 */    Vector3f localVector3f = this.ghostObject.getWorldTransform(this.ttmp).origin;
/*  297: 297 */    this.currentPosition.set(localVector3f);
/*  298:     */    
/*  299: 299 */    this.touchingContact = false;
/*  300: 300 */    do { if (!recoverFromPenetration(paramCollisionWorld)) break;
/*  301: 301 */      i++;
/*  302: 302 */      this.touchingContact = true;
/*  303: 303 */    } while (i <= 4);
/*  304:     */    
/*  305: 305 */    this.targetPosition.set(this.currentPosition);
/*  306:     */  }
/*  307:     */  
/*  323:     */  public void playerStep(CollisionWorld paramCollisionWorld, float paramFloat)
/*  324:     */  {
/*  325: 325 */    if ((!this.useWalkDirection) && (this.velocityTimeInterval <= 0.0F))
/*  326:     */    {
/*  330: 330 */      return;
/*  331:     */    }
/*  332:     */    
/*  333: 333 */    PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)this.ghostObject;
/*  334:     */    
/*  336: 336 */    this.wasOnGround = onGround();
/*  337:     */    
/*  339: 339 */    this.verticalVelocity -= this.gravity * paramFloat;
/*  340:     */    
/*  342: 342 */    if ((this.verticalVelocity > 0.0D) && (this.verticalVelocity > this.jumpSpeed)) {
/*  343: 343 */      this.verticalVelocity = this.jumpSpeed;
/*  344:     */    }
/*  345: 345 */    if ((this.verticalVelocity < 0.0D) && (Math.abs(this.verticalVelocity) > Math.abs(this.fallSpeed)))
/*  346:     */    {
/*  347: 347 */      this.verticalVelocity = (-Math.abs(this.fallSpeed));
/*  348:     */    }
/*  349: 349 */    this.verticalOffset = (this.verticalVelocity * paramFloat);
/*  350:     */    
/*  354: 354 */    localPairCachingGhostObjectAlignable.getWorldTransform(this.xform);
/*  355:     */    
/*  356: 356 */    this.velocity.set(this.xform.origin);
/*  357:     */    
/*  365: 365 */    stepUp(paramCollisionWorld);
/*  366: 366 */    if (this.useWalkDirection)
/*  367:     */    {
/*  368: 368 */      stepForwardAndStrafe(paramCollisionWorld, this.walkDirection);
/*  370:     */    }
/*  371:     */    else
/*  372:     */    {
/*  374: 374 */      float f = paramFloat < this.velocityTimeInterval ? paramFloat : this.velocityTimeInterval;
/*  375:     */      
/*  376: 376 */      this.velocityTimeInterval -= paramFloat;
/*  377:     */      
/*  380: 380 */      this.move.scale(f, this.walkDirection);
/*  381:     */      
/*  385: 385 */      stepForwardAndStrafe(paramCollisionWorld, this.move);
/*  386:     */    }
/*  387:     */    
/*  392: 392 */    if (this.gravity > 0.0F) {
/*  393: 393 */      stepDown(paramCollisionWorld, paramFloat);
/*  394:     */    }
/*  395:     */    
/*  398: 398 */    this.xform.origin.set(this.currentPosition);
/*  399:     */    
/*  400: 400 */    GlUtil.c(this.upAxisDirection[0], this.xform);
/*  401: 401 */    GlUtil.d(this.upAxisDirection[1], this.xform);
/*  402: 402 */    GlUtil.a(this.upAxisDirection[2], this.xform);
/*  403:     */    
/*  424: 424 */    localPairCachingGhostObjectAlignable.setWorldTransform(this.xform);
/*  425: 425 */    adaptLocalAttached();
/*  426:     */    
/*  428: 428 */    this.velocity.sub(this.xform.origin);
/*  429: 429 */    this.velocity.negate();
/*  430: 430 */    this.walkDirection.set(0.0F, 0.0F, 0.0F);
/*  431: 431 */    this.velocityTimeInterval = 0.0F;
/*  432:     */  }
/*  433:     */  
/*  434:     */  protected boolean recoverFromPenetration(CollisionWorld paramCollisionWorld) {
/*  435: 435 */    boolean bool = false;
/*  436:     */    
/*  444: 444 */    Vector3f localVector3f = new Vector3f();
/*  445: 445 */    Object localObject = new Vector3f();
/*  446: 446 */    this.convexShape.getAabb(this.ghostObject.getWorldTransform(new Transform()), localVector3f, (Vector3f)localObject);
/*  447: 447 */    paramCollisionWorld.getBroadphase().setAabb(this.ghostObject.getBroadphaseHandle(), localVector3f, (Vector3f)localObject, paramCollisionWorld.getDispatcher());
/*  448:     */    
/*  453: 453 */    paramCollisionWorld.getDispatcher().dispatchAllCollisionPairs(this.ghostObject.getOverlappingPairCache(), paramCollisionWorld.getDispatchInfo(), paramCollisionWorld.getDispatcher());
/*  454:     */    
/*  456: 456 */    this.currentPosition.set(this.ghostObject.getWorldTransform(this.ttmp).origin);
/*  457:     */    
/*  458: 458 */    paramCollisionWorld = 0.0F;
/*  459: 459 */    for (int i = 0; i < this.ghostObject.getOverlappingPairCache().getNumOverlappingPairs(); i++) {
/*  460: 460 */      this.manifoldArray.clear();
/*  461:     */      
/*  464: 464 */      if ((localObject = (BroadphasePair)this.ghostObject.getOverlappingPairCache().getOverlappingPairArray().getQuick(i)).algorithm != null) {
/*  465: 465 */        ((BroadphasePair)localObject).algorithm.getAllContactManifolds(this.manifoldArray);
/*  466:     */      }
/*  467:     */      
/*  468: 468 */      for (int j = 0; j < this.manifoldArray.size(); j++) {
/*  469:     */        PersistentManifold localPersistentManifold;
/*  470: 470 */        float f1 = (localPersistentManifold = (PersistentManifold)this.manifoldArray.getQuick(j)).getBody0() == this.ghostObject ? -1.0F : 1.0F;
/*  471: 471 */        for (int k = 0; k < localPersistentManifold.getNumContacts(); k++)
/*  472:     */        {
/*  473:     */          ManifoldPoint localManifoldPoint;
/*  474:     */          float f2;
/*  475: 475 */          if ((f2 = (localManifoldPoint = localPersistentManifold.getContactPoint(k)).getDistance()) < 0.1F)
/*  476:     */          {
/*  483: 483 */            if (f2 < paramCollisionWorld)
/*  484:     */            {
/*  485: 485 */              paramCollisionWorld = f2;
/*  486: 486 */              this.touchingNormal.set(localManifoldPoint.normalWorldOnB);
/*  487: 487 */              this.touchingNormal.scale(f1);
/*  488:     */            }
/*  489:     */            
/*  497: 497 */            this.currentPosition.scaleAdd(f1 * f2 * 0.2F, localManifoldPoint.normalWorldOnB, this.currentPosition);
/*  498:     */            
/*  503: 503 */            bool = true;
/*  504:     */          }
/*  505:     */          else {
/*  506: 506 */            this.obj.getState();
/*  507:     */          }
/*  508:     */        }
/*  509:     */      }
/*  510:     */    }
/*  511:     */    
/*  514:     */    Transform localTransform;
/*  515:     */    
/*  518: 518 */    (localTransform = this.ghostObject.getWorldTransform(this.ttmp)).origin.set(this.currentPosition);
/*  519: 519 */    this.ghostObject.setWorldTransform(localTransform);
/*  520:     */    
/*  524: 524 */    return bool;
/*  525:     */  }
/*  526:     */  
/*  538:     */  public void setVelocityForTimeIntervalStacked(Vector3f paramVector3f, float paramFloat)
/*  539:     */  {
/*  540: 540 */    this.useWalkDirection = false;
/*  541: 541 */    this.walkDirection.add(paramVector3f);
/*  542: 542 */    this.normal.set(this.walkDirection);
/*  543: 543 */    this.normal.normalize();
/*  544: 544 */    this.normalizedDirection.set(this.normal);
/*  545: 545 */    this.velocityTimeInterval += paramFloat;
/*  546:     */  }
/*  547:     */  
/*  566:     */  public void setWalkDirectionStacked(Vector3f paramVector3f)
/*  567:     */  {
/*  568: 568 */    this.useWalkDirection = true;
/*  569: 569 */    this.walkDirection.add(paramVector3f);
/*  570: 570 */    this.normal.set(this.walkDirection);
/*  571: 571 */    this.normal.normalize();
/*  572: 572 */    this.normalizedDirection.set(this.normal);
/*  573:     */  }
/*  574:     */  
/*  576:     */  protected void stepDown(CollisionWorld paramCollisionWorld, float paramFloat)
/*  577:     */  {
/*  578: 578 */    if (this.obj.isOnServer()) {
/*  579: 579 */      SubsimplexCubesCovexCast.mode = "DWN";
/*  580:     */    }
/*  581: 581 */    Transform localTransform1 = new Transform();
/*  582: 582 */    Transform localTransform2 = new Transform();
/*  583:     */    
/*  585: 585 */    float f1 = this.wasOnGround ? this.stepHeight : 0.0F;
/*  586:     */    Vector3f localVector3f2;
/*  587: 587 */    (localVector3f2 = new Vector3f()).scale(this.currentStepOffset + f1, this.upAxisDirection[this.upAxis]);
/*  588:     */    
/*  589: 589 */    paramFloat = ((f1 == 0.0F) && (this.verticalVelocity < 0.0F) ? -this.verticalVelocity : 0.0F) * paramFloat;
/*  590:     */    
/*  591:     */    Vector3f localVector3f1;
/*  592:     */    
/*  593: 593 */    (localVector3f1 = new Vector3f()).scale(paramFloat, this.upAxisDirection[this.upAxis]);
/*  594:     */    
/*  595: 595 */    this.targetPosition.sub(localVector3f2);
/*  596: 596 */    this.targetPosition.sub(localVector3f1);
/*  597:     */    
/*  598: 598 */    localTransform1.setIdentity();
/*  599: 599 */    localTransform2.setIdentity();
/*  600:     */    
/*  610: 610 */    localTransform1.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  611: 611 */    localTransform2.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  612:     */    
/*  613: 613 */    localTransform1.origin.set(this.currentPosition);
/*  614: 614 */    localTransform2.origin.set(this.targetPosition);
/*  615:     */    
/*  618: 618 */    paramFloat = this.ghostObject.getCollisionShape().getMargin();
/*  619:     */    
/*  621: 621 */    if (this.capsule == null) {
/*  622: 622 */      float f2 = this.obj.b();
/*  623: 623 */      float f3 = this.extendedCharacterHeight;
/*  624: 624 */      this.capsule = new CapsuleShape(f2, f3);
/*  625:     */      
/*  626: 626 */      this.capsule.setMargin(0.1F);
/*  627:     */    }
/*  628:     */    
/*  629:     */    KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
/*  630: 630 */    (localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], this.maxSlopeCosine)).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*  631: 631 */    localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*  632:     */    
/*  633:     */    Vector3f localVector3f3;
/*  634: 634 */    (localVector3f3 = new Vector3f()).sub(this.targetPosition, this.currentPosition);
/*  635: 635 */    if (localVector3f3.length() == 0.0F)
/*  636:     */    {
/*  639: 639 */      return;
/*  640:     */    }
/*  641:     */    
/*  642: 642 */    new Vector3f(this.currentPosition);
/*  643: 643 */    (
/*  644: 644 */      localVector3f3 = new Vector3f(localVector3f3)).normalize();
/*  645: 645 */    localVector3f3.scale(0.999F);
/*  646: 646 */    localVector3f3.add(this.currentPosition);
/*  647:     */    
/*  664: 664 */    if (this.useGhostObjectSweepTest) {
/*  665: 665 */      this.ghostObject.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
/*  666:     */    }
/*  667:     */    else {
/*  668: 668 */      paramCollisionWorld.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback);
/*  669:     */    }
/*  670:     */    
/*  671: 671 */    if (localKinematicClosestNotMeConvexResultCallback.hasHit())
/*  672:     */    {
/*  681: 681 */      this.currentPosition.interpolate(this.currentPosition, this.targetPosition, localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
/*  682: 682 */      this.verticalVelocity = 0.0F;
/*  683: 683 */      this.verticalOffset = 0.0F;
/*  684:     */      
/*  689: 689 */      if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  690: 690 */        this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));
/*  691:     */      } else {
/*  692: 692 */        System.err.println("Exception: Kinmatik: NO SEGMENT TO HANDLE AFTER COLLISION (LIFT?)");
/*  693:     */      }
/*  694:     */      
/*  696:     */    }
/*  697:     */    else
/*  698:     */    {
/*  699: 699 */      if (this.targetPosition.epsilonEquals(this.currentPosition, 1.0E-007F)) {
/*  700: 700 */        this.verticalVelocity = 0.0F;
/*  701: 701 */        this.verticalOffset = 0.0F;
/*  702:     */      }
/*  703:     */      
/*  705: 705 */      this.currentPosition.set(this.targetPosition);
/*  706:     */    }
/*  707:     */    
/*  709: 709 */    this.ghostObject.getCollisionShape().setMargin(paramFloat);
/*  710:     */  }
/*  711:     */  
/*  714:     */  protected void stepForwardAndStrafe(CollisionWorld paramCollisionWorld, Vector3f paramVector3f)
/*  715:     */  {
/*  716: 716 */    if (this.obj.isOnServer())
/*  717:     */    {
/*  719: 719 */      SubsimplexCubesCovexCast.mode = "FW";
/*  720:     */    }
/*  721:     */    
/*  724: 724 */    this.targetPosition.add(this.currentPosition, paramVector3f);
/*  725: 725 */    this.start.setIdentity();
/*  726: 726 */    this.end.setIdentity();
/*  727:     */    
/*  734: 734 */    this.start.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  735: 735 */    this.end.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  736:     */    
/*  737: 737 */    paramVector3f = 1.0F;
/*  738:     */    
/*  739: 739 */    this.distance2Vec.sub(this.targetPosition, this.currentPosition);
/*  740:     */    
/*  745: 745 */    float f = 0.0F; if (this.distance2Vec.lengthSquared() == 0.0F) {
/*  746: 746 */      return;
/*  747:     */    }
/*  748:     */    
/*  769: 769 */    if (this.strafeCapsule == null) {
/*  770: 770 */      f = this.obj.a();
/*  771:     */      
/*  772: 772 */      this.strafeCapsule = new CapsuleShape(0.3F, f);
/*  773:     */      
/*  774: 774 */      this.strafeCapsule.setMargin(0.1F);
/*  775:     */    }
/*  776:     */    
/*  778: 778 */    int i = 10;
/*  779:     */    
/*  780: 780 */    this.before.set(this.currentPosition);
/*  781:     */    
/*  783: 783 */    while ((paramVector3f > 0.01F) && (i-- > 0))
/*  784:     */    {
/*  787: 787 */      this.start.origin.set(this.currentPosition);
/*  788: 788 */      this.end.origin.set(this.targetPosition);
/*  789:     */      
/*  792:     */      KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
/*  793:     */      
/*  795: 795 */      (localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], -1.0F)).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*  796:     */      
/*  797: 797 */      localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*  798:     */      
/*  800: 800 */      f = this.convexShape.getMargin();
/*  801: 801 */      this.convexShape.setMargin(f + this.addedMargin);
/*  802:     */      
/*  804: 804 */      if (this.useGhostObjectSweepTest)
/*  805:     */      {
/*  806: 806 */        this.ghostObject.convexSweepTest(this.strafeCapsule, this.start, this.end, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
/*  807:     */      }
/*  808:     */      else {
/*  809: 809 */        paramCollisionWorld.convexSweepTest(this.convexShape, this.start, this.end, localKinematicClosestNotMeConvexResultCallback);
/*  810:     */      }
/*  811:     */      
/*  813: 813 */      this.convexShape.setMargin(f);
/*  814:     */      
/*  815: 815 */      paramVector3f -= localKinematicClosestNotMeConvexResultCallback.closestHitFraction;
/*  816: 816 */      if (localKinematicClosestNotMeConvexResultCallback.hasHit()) {
/*  817: 817 */        this.hitDistanceVec.sub(localKinematicClosestNotMeConvexResultCallback.hitPointWorld, this.currentPosition);
/*  818:     */        
/*  822: 822 */        this.hitDistanceVec.length();
/*  823:     */        
/*  830: 830 */        updateTargetPositionBasedOnCollision(localKinematicClosestNotMeConvexResultCallback.hitNormalWorld);
/*  831:     */        
/*  833: 833 */        this.currentDir.sub(this.targetPosition, this.currentPosition);
/*  834:     */        
/*  835: 835 */        if (this.currentDir.lengthSquared() > 1.192093E-007F) {
/*  836: 836 */          this.currentDir.normalize();
/*  837:     */          
/*  839: 839 */          if (this.currentDir.dot(this.normalizedDirection) <= 0.0F) {
/*  840: 840 */            if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  841: 841 */              this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));break;
/*  842:     */            }
/*  843: 843 */            System.err.println("[KINEMATIC] collision: No Segment To handle");
/*  844:     */            
/*  845: 845 */            break;
/*  846:     */          }
/*  847:     */        } else {
/*  848: 848 */          if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  849: 849 */            this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));break;
/*  850:     */          }
/*  851: 851 */          System.err.println("[KINEMATIC] collision: No Segment To handle");
/*  852:     */          
/*  854: 854 */          break;
/*  855:     */        }
/*  856:     */      }
/*  857:     */      else
/*  858:     */      {
/*  859: 859 */        this.currentPosition.set(this.targetPosition);
/*  860:     */      }
/*  861:     */    }
/*  862:     */    
/*  865: 865 */    this.obj.getState();
/*  866:     */    
/*  872: 872 */    if (this.after == null) {
/*  873: 873 */      this.after = new Vector3f();
/*  874:     */    }
/*  875: 875 */    this.after.set(this.currentPosition);
/*  876:     */  }
/*  877:     */  
/*  879:     */  protected void stepUp(CollisionWorld paramCollisionWorld)
/*  880:     */  {
/*  881: 881 */    if (this.obj.isOnServer()) {
/*  882: 882 */      SubsimplexCubesCovexCast.mode = "UP";
/*  883:     */    }
/*  884:     */    
/*  885: 885 */    Transform localTransform1 = new Transform();
/*  886: 886 */    Transform localTransform2 = new Transform();
/*  887: 887 */    this.targetPosition.scaleAdd(this.stepHeight + (this.verticalOffset > 0.0D ? this.verticalOffset : 0.0F), this.upAxisDirection[this.upAxis], this.currentPosition);
/*  888:     */    
/*  889: 889 */    if (this.capsule == null) {
/*  890: 890 */      float f1 = this.obj.b();
/*  891: 891 */      float f2 = this.extendedCharacterHeight;
/*  892: 892 */      this.capsule = new CapsuleShape(f1, f2);
/*  893:     */      
/*  894: 894 */      this.capsule.setMargin(0.1F);
/*  895:     */    }
/*  896:     */    
/*  897: 897 */    localTransform1.setIdentity();
/*  898: 898 */    localTransform2.setIdentity();
/*  899: 899 */    assert (this.ghostObject.getWorldTransform(new Transform()).getMatrix(new Matrix4f()).determinant() != 0.0F) : this.ghostObject.getWorldTransform(new Transform()).getMatrix(new Matrix4f());
/*  900: 900 */    localTransform1.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  901: 901 */    localTransform2.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  902:     */    
/*  904: 904 */    localTransform1.origin.set(this.currentPosition);
/*  905: 905 */    localTransform2.origin.set(this.targetPosition);
/*  906:     */    
/*  908:     */    Vector3f localVector3f;
/*  909:     */    
/*  910: 910 */    (localVector3f = new Vector3f()).scale(-1.0F, this.upAxisDirection[this.upAxis]);
/*  911:     */    KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
/*  912: 912 */    (localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, localVector3f, 0.1F)).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*  913: 913 */    localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*  914:     */    
/*  915: 915 */    if (this.useGhostObjectSweepTest) {
/*  916: 916 */      this.ghostObject.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
/*  917:     */    }
/*  918:     */    else {
/*  919: 919 */      paramCollisionWorld.convexSweepTest(this.convexShape, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback);
/*  920:     */    }
/*  921:     */    
/*  922: 922 */    if (localKinematicClosestNotMeConvexResultCallback.hasHit())
/*  923:     */    {
/*  926: 926 */      this.currentStepOffset = (this.stepHeight * localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
/*  927: 927 */      this.currentPosition.interpolate(this.currentPosition, this.targetPosition, localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
/*  928: 928 */      this.verticalVelocity = -1.0E-008F;
/*  929: 929 */      this.verticalOffset = 0.0F;
/*  930: 930 */      this.jumpingStarted = -1L;
/*  931:     */      
/*  933: 933 */      if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  934: 934 */        this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));return;
/*  935:     */      }
/*  936: 936 */      System.err.println("[KINEMATIC] collision: No Segment To handle");return;
/*  937:     */    }
/*  938:     */    
/*  940: 940 */    this.currentStepOffset = this.stepHeight;
/*  941: 941 */    this.currentPosition.set(this.targetPosition);
/*  942:     */  }
/*  943:     */  
/*  946:     */  public void stopJump()
/*  947:     */  {
/*  948: 948 */    this.verticalVelocity = 0.0F;
/*  949:     */  }
/*  950:     */  
/*  952:     */  public void warpOutOfCollision(StateInterface paramStateInterface, CollisionWorld paramCollisionWorld, Transform paramTransform)
/*  953:     */  {
/*  954: 954 */    warp(paramTransform.origin);
/*  955:     */    
/*  961: 961 */    this.start.setIdentity();
/*  962: 962 */    this.end.setIdentity();
/*  963:     */    
/*  964: 964 */    this.distance2Vec.sub(paramTransform.origin, this.targetPosition);
/*  965: 965 */    new Vector3f();
/*  966: 966 */    paramCollisionWorld.getBroadphase().calculateOverlappingPairs(paramCollisionWorld.getDispatcher());
/*  967:     */    
/*  968: 968 */    paramCollisionWorld = 4;
/*  969:     */    
/*  970:     */    Vector3f localVector3f;
/*  971:     */    
/*  972: 972 */    GlUtil.c(localVector3f = new Vector3f(), paramTransform);
/*  973:     */    
/*  974: 974 */    int j = 0;
/*  975:     */    
/*  981: 981 */    this.targetPosition.set(paramTransform.origin);
/*  982:     */    
/*  985: 985 */    boolean bool1 = false;
/*  986:     */    
/*  990: 990 */    warp(this.targetPosition);
/*  991:     */    Object localObject2;
/*  992: 992 */    Object localObject3; Object localObject4; Object localObject5; synchronized (paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  993:     */      ObjectCollection localObjectCollection;
/*  994: 994 */      if ((paramStateInterface instanceof ServerStateInterface)) {
/*  995: 995 */        localObjectCollection = paramStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values();
/*  996:     */      } else {
/*  997: 997 */        localObjectCollection = ((ct)paramStateInterface).a().values();
/*  998:     */      }
/*  999: 999 */      for (localObject2 = localObjectCollection.iterator(); ((Iterator)localObject2).hasNext();) {
/* 1000:1000 */        if (((localObject3 = (Sendable)((Iterator)localObject2).next()) instanceof SegmentController))
/* 1001:     */        {
/* 1002:1002 */          if ((localObject4 = (SegmentController)localObject3).getSectorId() == this.obj.getSectorId())
/* 1003:     */          {
/* 1004:1004 */            (localObject5 = new Transform()).setIdentity();
/* 1005:1005 */            ((Transform)localObject5).origin.set(this.targetPosition);
/* 1006:     */            
/* 1008:1008 */            if ((bool1 = ((SegmentController)localObject4).getCollisionChecker().a((SegmentController)localObject4, (Transform)localObject5))) {
/* 1009:     */              break;
/* 1010:     */            }
/* 1011:     */          }
/* 1012:     */        }
/* 1013:     */      }
/* 1014:     */    }
/* 1015:     */    
/* 1017:1017 */    if (localObject1 == 0) {
/* 1018:1018 */      System.err.println("[WARPING OT OF COLLISION][CHECK_SINGLE] NOHIT -> can spawn here!! " + this.targetPosition);
/* 1019:     */    }
/* 1020:     */    
/* 1024:1024 */    while ((localObject1 != 0) && (j < 10000)) {
/* 1025:1025 */      this.start.origin.set(this.targetPosition);
/* 1026:     */      
/* 1027:1027 */      switch (paramCollisionWorld) {
/* 1028:1028 */      case 4:  GlUtil.c(localVector3f, paramTransform);break;
/* 1029:1029 */      case 5:  GlUtil.a(localVector3f, paramTransform);break;
/* 1030:1030 */      case 2:  GlUtil.f(localVector3f, paramTransform);break;
/* 1031:1031 */      case 3:  GlUtil.b(localVector3f, paramTransform);break;
/* 1032:1032 */      case 1:  GlUtil.d(localVector3f, paramTransform);break;
/* 1033:1033 */      case 0:  GlUtil.e(localVector3f, paramTransform);
/* 1034:     */      }
/* 1035:1035 */      paramCollisionWorld = (paramCollisionWorld + 1) % 6;
/* 1036:     */      
/* 1037:1037 */      localVector3f.scale(1.0F + j / 6 * 0.2F);
/* 1038:     */      
/* 1039:1039 */      this.targetPosition.add(paramTransform.origin, localVector3f);
/* 1040:1040 */      this.end.origin.set(this.targetPosition);
/* 1041:     */      
/* 1045:1045 */      ??? = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], -1.0F);
/* 1046:     */      
/* 1048:1048 */      if (this.ghostObject == null) {
/* 1049:1049 */        System.err.println("Exception KinematikCharacter: warp out of collision ghost object " + this.ghostObject + " is null " + this.obj);
/* 1050:1050 */        return;
/* 1051:     */      }
/* 1052:1052 */      if (this.ghostObject.getBroadphaseHandle() == null)
/* 1053:     */      {
/* 1054:1054 */        System.err.println("Exception KinematikCharacter: warp out of collision broadphase handle of ghost object " + this.ghostObject + " is null " + this.obj);
/* 1055:1055 */        return;
/* 1056:     */      }
/* 1057:1057 */      ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)???).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/* 1058:     */      
/* 1059:1059 */      ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)???).collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/* 1060:     */      
/* 1063:1063 */      int i = 0;
/* 1064:     */      
/* 1071:1071 */      warp(this.targetPosition);
/* 1072:1072 */      boolean bool2; synchronized (paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
/* 1073:     */      {
/* 1074:1074 */        if ((paramStateInterface instanceof ServerStateInterface)) {
/* 1075:1075 */          localObject2 = paramStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values();
/* 1076:     */        } else {
/* 1077:1077 */          localObject2 = ((ct)paramStateInterface).a().values();
/* 1078:     */        }
/* 1079:1079 */        for (localObject3 = ((ObjectCollection)localObject2).iterator(); ((Iterator)localObject3).hasNext();) {
/* 1080:1080 */          if (((localObject4 = (Sendable)((Iterator)localObject3).next()) instanceof SegmentController))
/* 1081:     */          {
/* 1082:1082 */            if ((localObject5 = (SegmentController)localObject4).getSectorId() == this.obj.getSectorId())
/* 1083:     */            {
/* 1084:     */              Transform localTransform;
/* 1085:1085 */              (localTransform = new Transform()).setIdentity();
/* 1086:1086 */              localTransform.origin.set(this.targetPosition);
/* 1087:     */              
/* 1089:1089 */              if ((bool2 = ((SegmentController)localObject5).getCollisionChecker().a((SegmentController)localObject5, localTransform))) {
/* 1090:     */                break;
/* 1091:     */              }
/* 1092:     */            }
/* 1093:     */          }
/* 1094:     */        }
/* 1095:     */      }
/* 1096:1096 */      if (!bool2) {
/* 1097:1097 */        System.err.println("[WARPING OT OF COLLISION][CHECK WARP] NOHIT -> can spawn here!! " + this.targetPosition);
/* 1098:     */      }
/* 1099:     */      
/* 1103:1103 */      j++;
/* 1104:     */    }
/* 1105:1105 */    if (j >= 10000) {
/* 1106:     */      try {
/* 1107:1107 */        throw new RuntimeException("Exceeded warping out of collision!!!!! " + paramTransform.origin + "; " + paramTransform.basis);
/* 1108:1108 */      } catch (Exception localException) { localException;
/* 1109:     */      }
/* 1110:     */    }
/* 1111:     */  }
/* 1112:     */  
/* 1113:     */  public Vector3f getLinearVelocity(Vector3f paramVector3f)
/* 1114:     */  {
/* 1115:1115 */    paramVector3f.set(this.velocity);
/* 1116:1116 */    paramVector3f.scale(1000.0F);
/* 1117:     */    
/* 1118:1118 */    return paramVector3f;
/* 1119:     */  }
/* 1120:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */