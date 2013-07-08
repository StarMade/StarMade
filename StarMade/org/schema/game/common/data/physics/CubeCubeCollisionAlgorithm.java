/*    1:     */package org.schema.game.common.data.physics;
/*    2:     */
/*    3:     */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    4:     */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*    5:     */import com.bulletphysics.collision.broadphase.Dispatcher;
/*    6:     */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    7:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*    8:     */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*    9:     */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*   10:     */import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*   11:     */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   12:     */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*   13:     */import com.bulletphysics.collision.shapes.BoxShape;
/*   14:     */import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*   15:     */import com.bulletphysics.collision.shapes.ConvexShape;
/*   16:     */import com.bulletphysics.linearmath.AabbUtil2;
/*   17:     */import com.bulletphysics.linearmath.MatrixUtil;
/*   18:     */import com.bulletphysics.linearmath.Transform;
/*   19:     */import com.bulletphysics.util.ObjectArrayList;
/*   20:     */import com.bulletphysics.util.ObjectPool;
/*   21:     */import dO;
/*   22:     */import jL;
/*   23:     */import java.io.IOException;
/*   24:     */import java.io.PrintStream;
/*   25:     */import java.util.List;
/*   26:     */import javax.vecmath.Matrix3f;
/*   27:     */import javax.vecmath.Vector3f;
/*   28:     */import le;
/*   29:     */import o;
/*   30:     */import org.schema.common.FastMath;
/*   31:     */import org.schema.common.util.ByteUtil;
/*   32:     */import org.schema.game.common.controller.SegmentController;
/*   33:     */import org.schema.game.common.data.element.ElementInformation;
/*   34:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   35:     */import org.schema.game.common.data.physics.octree.ArrayOctree;
/*   36:     */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*   37:     */import org.schema.game.common.data.world.Segment;
/*   38:     */import org.schema.game.common.data.world.SegmentData;
/*   39:     */import org.schema.schine.network.StateInterface;
/*   40:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   41:     */import q;
/*   42:     */import vo;
/*   43:     */import xO;
/*   44:     */import zQ;
/*   45:     */import zY;
/*   46:     */
/*   75:     */public class CubeCubeCollisionAlgorithm
/*   76:     */  extends CollisionAlgorithm
/*   77:     */{
/*   78:  78 */  private int handles = 0;
/*   79:     */  
/*  204: 204 */  protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*  205:     */  
/*  206:     */  private GjkPairDetectorExt gjkPairDetector;
/*  207: 207 */  private BoxBoxDetector boxboxPairDetector = new BoxBoxDetector();
/*  208:     */  
/*  209: 209 */  private int slow_threashold = ((Integer)vo.r.a()).intValue();
/*  210:     */  
/*  211:     */  public boolean lowLevelOfDetail;
/*  212:     */  
/*  213:     */  private boolean ownManifold;
/*  214:     */  
/*  215:     */  private PersistentManifold manifoldPtr;
/*  216:     */  
/*  217:     */  private CubeCubeCollisionAlgorithm.OuterSegmentHandler outerSegmentHandler;
/*  218:     */  
/*  219:     */  private CubeCubeCollisionAlgorithm.InnerSegmentHandler innerSegmentHandler;
/*  220: 220 */  static float margin = 0.05F;
/*  221:     */  
/*  222: 222 */  private static ThreadLocal threadLocal = new CubeCubeCollisionAlgorithm.1();
/*  223:     */  
/*  225:     */  public CubeCubeCollisionVariableSet v;
/*  226:     */  
/*  228:     */  private int aabbBlockOverlapping;
/*  229:     */  
/*  231:     */  private int narrowTime;
/*  232:     */  
/*  233:     */  private long octATreeTime;
/*  234:     */  
/*  235:     */  private int leaftCallsA;
/*  236:     */  
/*  237:     */  private long leafRetrieve;
/*  238:     */  
/*  239:     */  private long leafAABBTest;
/*  240:     */  
/*  241:     */  private long currentTime;
/*  242:     */  
/*  243:     */  public boolean localSwap;
/*  244:     */  
/*  245:     */  private boolean stuck;
/*  246:     */  
/*  247:     */  private float currentMaxDepth;
/*  248:     */  
/*  249:     */  private long stuckStarted;
/*  250:     */  
/*  252:     */  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*  253:     */  {
/*  254: 254 */    this.handles = 0;
/*  255: 255 */    this.stuck = false;
/*  256: 256 */    if ((!(paramCollisionObject1.getCollisionShape() instanceof CubeShape)) || (!(paramCollisionObject2.getCollisionShape() instanceof CubeShape)))
/*  257:     */    {
/*  258: 258 */      return;
/*  259:     */    }
/*  260: 260 */    if ((paramCollisionObject1.isStaticObject()) && (paramCollisionObject2.isStaticObject())) {
/*  261: 261 */      return;
/*  262:     */    }
/*  263:     */    
/*  264: 264 */    if (this.localSwap) {
/*  265: 265 */      System.err.println("LOCAL SWAP IN CUBE CUBE");
/*  266: 266 */      localObject = paramCollisionObject1;
/*  267: 267 */      paramCollisionObject1 = paramCollisionObject2;
/*  268: 268 */      paramCollisionObject2 = (CollisionObject)localObject;
/*  269:     */    }
/*  270:     */    
/*  271: 271 */    this.currentTime = System.currentTimeMillis();
/*  272: 272 */    this.narrowTime = 0;
/*  273: 273 */    this.octATreeTime = 0L;
/*  274: 274 */    this.aabbBlockOverlapping = 0;
/*  275: 275 */    this.leaftCallsA = 0;
/*  276: 276 */    this.leafRetrieve = 0L;
/*  277: 277 */    this.leafAABBTest = 0L;
/*  278: 278 */    Object localObject = (CubeShape)paramCollisionObject1.getCollisionShape();
/*  279: 279 */    CubeShape localCubeShape = (CubeShape)paramCollisionObject2.getCollisionShape();
/*  280:     */    
/*  281: 281 */    if (localObject == localCubeShape) {
/*  282: 282 */      System.err.println("EUALCOL " + ((CubeShape)localObject).getSegmentBuffer().a());
/*  283: 283 */      return;
/*  284:     */    }
/*  285:     */    
/*  288: 288 */    assert (((CubeShape)localObject).getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape == localObject) : (((CubeShape)localObject).getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape + " -- " + localObject);
/*  289:     */    
/*  291: 291 */    assert (localCubeShape.getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape == localCubeShape) : (localCubeShape.getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape + " -- " + localCubeShape);
/*  292:     */    
/*  297:     */    PhysicsExt localPhysicsExt;
/*  298:     */    
/*  303: 303 */    if (!(localPhysicsExt = ((CubeShape)localObject).getSegmentBuffer().a().getPhysics()).getPhysicsExceptions().isEmpty()) {
/*  304: 304 */      for (i = 0; i < localPhysicsExt.getPhysicsExceptions().size(); i++) {
/*  305:     */        zY localzY;
/*  306: 306 */        if ((((localzY = (zY)localPhysicsExt.getPhysicsExceptions().get(i)).a == paramCollisionObject1) && (localzY.b == paramCollisionObject2)) || ((localzY.a == paramCollisionObject2) && (localzY.b == paramCollisionObject1))) {
/*  307: 307 */          return;
/*  308:     */        }
/*  309:     */      }
/*  310:     */    }
/*  311:     */    
/*  314: 314 */    this.v.oSet = ArrayOctree.getSet(((CubeShape)localObject).getSegmentBuffer().a().isOnServer());
/*  315:     */    
/*  316: 316 */    this.v.tmpTrans1 = paramCollisionObject1.getWorldTransform(this.v.tmpTrans1);
/*  317: 317 */    this.v.tmpTrans2 = paramCollisionObject2.getWorldTransform(this.v.tmpTrans2);
/*  318:     */    
/*  321: 321 */    this.v.absolute1.set(this.v.tmpTrans1.basis);
/*  322: 322 */    MatrixUtil.absolute(this.v.absolute1);
/*  323: 323 */    this.v.absolute2.set(this.v.tmpTrans2.basis);
/*  324: 324 */    MatrixUtil.absolute(this.v.absolute2);
/*  325:     */    
/*  328: 328 */    ((CubeShape)localObject).getSegmentBuffer().a().onProximity(localCubeShape.getSegmentBuffer().a());
/*  329:     */    
/*  334: 334 */    localCubeShape.getSegmentBuffer().a().onProximity(((CubeShape)localObject).getSegmentBuffer().a());
/*  335:     */    
/*  339: 339 */    CubeCubeCollisionAlgorithm.OuterSegmentHandler.access$402(this.outerSegmentHandler, (CubeShape)localObject);
/*  340:     */    
/*  343: 343 */    CubeCubeCollisionAlgorithm.OuterSegmentHandler.access$502(this.outerSegmentHandler, localCubeShape);
/*  344:     */    
/*  345: 345 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$602(this.innerSegmentHandler, (CubeShape)localObject);
/*  346: 346 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$702(this.innerSegmentHandler, localCubeShape);
/*  347: 347 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$802(this.innerSegmentHandler, paramDispatcherInfo);
/*  348: 348 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$902(this.innerSegmentHandler, paramManifoldResult);
/*  349: 349 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1002(this.innerSegmentHandler, 0);
/*  350: 350 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1102(this.innerSegmentHandler, 0L);
/*  351:     */    
/*  362: 362 */    ((CubeShape)localObject).getAabb(this.v.tmpTrans1, this.v.outer.a, this.v.outer.b);
/*  363: 363 */    localCubeShape.getAabb(this.v.tmpTrans2, this.v.inner.a, this.v.inner.b);
/*  364:     */    
/*  370: 370 */    if (this.v.inner.a(this.v.outer, this.v.outBB) == null)
/*  371:     */    {
/*  372: 372 */      return;
/*  373:     */    }
/*  374:     */    
/*  377: 377 */    this.v.outBBCopy.a(this.v.outBB);
/*  378:     */    
/*  379: 379 */    paramCollisionObject1.getWorldTransform(this.v.wtInv0);
/*  380: 380 */    this.v.wtInv0.inverse();
/*  381: 381 */    AabbUtil2.transformAabb(this.v.outBB.a, this.v.outBB.b, 0.5F, this.v.wtInv0, this.v.outBB.a, this.v.outBB.b);
/*  382:     */    
/*  388: 388 */    this.v.minIntA.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/*  389: 389 */    this.v.minIntA.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/*  390: 390 */    this.v.minIntA.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/*  391:     */    
/*  392: 392 */    this.v.maxIntA.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/*  393: 393 */    this.v.maxIntA.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/*  394: 394 */    this.v.maxIntA.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/*  395:     */    
/*  397: 397 */    this.v.outBB.a(this.v.outBBCopy);
/*  398:     */    
/*  399: 399 */    paramCollisionObject2.getWorldTransform(this.v.wtInv1);
/*  400: 400 */    this.v.wtInv1.inverse();
/*  401: 401 */    AabbUtil2.transformAabb(this.v.outBB.a, this.v.outBB.b, 0.5F, this.v.wtInv1, this.v.outBB.a, this.v.outBB.b);
/*  402:     */    
/*  406: 406 */    this.v.minIntB.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/*  407: 407 */    this.v.minIntB.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/*  408: 408 */    this.v.minIntB.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/*  409:     */    
/*  410: 410 */    this.v.maxIntB.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/*  411: 411 */    this.v.maxIntB.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/*  412: 412 */    this.v.maxIntB.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/*  413:     */    
/*  416: 416 */    paramDispatcherInfo = Math.abs(ByteUtil.a(this.v.maxIntA.a) - ByteUtil.a(this.v.minIntA.a)) * Math.abs(ByteUtil.a(this.v.maxIntA.b) - ByteUtil.a(this.v.minIntA.b)) * Math.abs(ByteUtil.a(this.v.maxIntA.c) - ByteUtil.a(this.v.minIntA.c));
/*  417:     */    
/*  420: 420 */    DispatcherInfo localDispatcherInfo = Math.abs(ByteUtil.a(this.v.maxIntB.a) - ByteUtil.a(this.v.minIntB.a)) * Math.abs(ByteUtil.a(this.v.maxIntB.b) - ByteUtil.a(this.v.minIntB.b)) * Math.abs(ByteUtil.a(this.v.maxIntB.c) - ByteUtil.a(this.v.minIntB.c));
/*  421:     */    
/*  423: 423 */    long l2 = System.currentTimeMillis();
/*  424:     */    try {
/*  425: 425 */      ((CubeShape)localObject).getSegmentBuffer().b(this.outerSegmentHandler, this.v.minIntA, this.v.maxIntA);
/*  426: 426 */    } catch (Exception localException) { 
/*  427:     */      
/*  428: 428 */        localException;
/*  429:     */    }
/*  430:     */    
/*  434: 432 */    int i = CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1000(this.innerSegmentHandler);
/*  435: 433 */    long l1 = CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1100(this.innerSegmentHandler);
/*  436:     */    
/*  442: 440 */    int j = (int)(System.currentTimeMillis() - l2);
/*  443:     */    
/*  444: 442 */    if (this.stuck) {
/*  445: 443 */      StateInterface localStateInterface = ((CubeShape)localObject).getSegmentBuffer().a().getState();
/*  446: 444 */      System.err.println("[CC] " + localStateInterface + " getSObjects detected stuck: " + ((CubeShape)localObject).getSegmentBuffer().a() + " <-> " + localCubeShape.getSegmentBuffer().a());
/*  447: 445 */      if (paramCollisionObject1.isStaticOrKinematicObject()) {
/*  448: 446 */        System.err.println("[CC] " + localStateInterface + " Objects detected stuck --- warping out: " + localCubeShape.getSegmentBuffer().a());
/*  449: 447 */        localCubeShape.getSegmentBuffer().a().setImmediateStuck(true);
/*  450:     */      } else {
/*  451: 449 */        System.err.println("[CC] " + localStateInterface + " Objects detected stuck --- warping out: " + ((CubeShape)localObject).getSegmentBuffer().a());
/*  452: 450 */        ((CubeShape)localObject).getSegmentBuffer().a().setImmediateStuck(true);
/*  453:     */      }
/*  454:     */    }
/*  455:     */    
/*  456: 454 */    if (j > 15) {
/*  457: 455 */      if (this.aabbBlockOverlapping > ((CubeShape)localObject).getSegmentBuffer().a().getTotalElements() << 1) {
/*  458: 456 */        if (!paramCollisionObject1.isStaticObject()) {
/*  459: 457 */          ((CubeShape)localObject).getSegmentBuffer().a().flagPhysicsSlowdown();
/*  460:     */          break label1904;
/*  461:     */        }
/*  462:     */      } else {
/*  463: 461 */        if (this.aabbBlockOverlapping <= localCubeShape.getSegmentBuffer().a().getTotalElements() << 1) break label1904;
/*  464: 462 */        if (paramCollisionObject2.isStaticObject()) {
/*  465: 463 */          ((CubeShape)localObject).getSegmentBuffer().a().flagPhysicsSlowdown();
/*  466:     */          break label1904; } }
/*  467: 465 */      localCubeShape.getSegmentBuffer().a().flagPhysicsSlowdown();
/*  468:     */      
/*  470:     */      label1904:
/*  471:     */      
/*  472: 470 */      System.err.println("[CUBECUBE] CUBECUBE COL: " + j + " ms; NarTests: " + i + "; overlappingSingleBlockAABBs: " + this.aabbBlockOverlapping + "; handle: " + this.handles + " of MAX " + paramDispatcherInfo * localDispatcherInfo + "; DistiTime: " + l1 / 1000000L + ";NarrTime: " + this.narrowTime / 1000000 + "; Oct: " + this.octATreeTime / 1000000L + " call(" + this.leaftCallsA + ") retr(" + this.leafRetrieve / 1000000L + ") aabb(" + this.leafAABBTest / 1000000L + "); on " + ((CubeShape)localObject).getSegmentBuffer().a() + ": COUNT " + ((CubeShape)localObject).getSegmentBuffer().c() + "; TOTEl " + ((CubeShape)localObject).getSegmentBuffer().a().getTotalElements() + "; " + localCubeShape.getSegmentBuffer().a() + ": COUNT " + localCubeShape.getSegmentBuffer().c() + "; TOTEl " + localCubeShape.getSegmentBuffer().a().getTotalElements() + " on " + localCubeShape.getSegmentBuffer().a().getState() + ";OverlappingBOX A " + this.v.minIntA + "-" + this.v.maxIntA + "; B " + this.v.minIntB + "-" + this.v.maxIntB);
/*  473:     */      
/*  480: 478 */      System.err.println("[CC]INFO: " + paramCollisionObject1 + ": " + paramCollisionObject1.getWorldTransform(new Transform()).origin + "; " + paramCollisionObject2 + ": " + paramCollisionObject2.getWorldTransform(new Transform()).origin + " UPDATE#: " + ((CubeShape)localObject).getSegmentBuffer().a().getState().getUpdateNumber());
/*  481:     */    }
/*  482:     */    
/*  507: 505 */    if (this.ownManifold) {
/*  508: 506 */      paramManifoldResult.refreshContactPoints();
/*  509:     */    }
/*  510:     */  }
/*  511:     */  
/*  515:     */  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*  516:     */  {
/*  517: 515 */    return 1.0F;
/*  518:     */  }
/*  519:     */  
/*  520:     */  public void destroy()
/*  521:     */  {
/*  522: 520 */    if (this.ownManifold) {
/*  523: 521 */      if (this.manifoldPtr != null) {
/*  524: 522 */        this.dispatcher.releaseManifold(this.manifoldPtr);
/*  525:     */      }
/*  526: 524 */      this.manifoldPtr = null;
/*  527:     */    }
/*  528:     */  }
/*  529:     */  
/*  532:     */  private int doExtCubeCubeCollision(BoxShape paramBoxShape1, BoxShape paramBoxShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, DispatcherInfo paramDispatcherInfo)
/*  533:     */  {
/*  534:     */    DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
/*  535:     */    
/*  537: 535 */    (localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
/*  538:     */    
/*  540: 538 */    localClosestPointInput.maximumDistanceSquared = (paramBoxShape1.getMargin() + paramBoxShape2.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
/*  541:     */    
/*  542: 540 */    localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
/*  543:     */    
/*  544: 542 */    localClosestPointInput.transformA.set(paramTransform1);
/*  545: 543 */    localClosestPointInput.transformB.set(paramTransform2);
/*  546: 544 */    this.boxboxPairDetector.GetClosestPoints(paramBoxShape1, paramBoxShape2, localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw, false);
/*  547:     */    
/*  548: 546 */    this.pointInputsPool.release(localClosestPointInput);
/*  549:     */    
/*  550: 548 */    this.currentMaxDepth = this.boxboxPairDetector.maxDepth;
/*  551:     */    
/*  552: 550 */    return this.boxboxPairDetector.contacts;
/*  553:     */  }
/*  554:     */  
/*  556:     */  private int doNonBlockCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, DispatcherInfo paramDispatcherInfo)
/*  557:     */  {
/*  558:     */    DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
/*  559: 557 */    (localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
/*  560:     */    
/*  561: 559 */    paramManifoldResult.setPersistentManifold(this.manifoldPtr);
/*  562:     */    
/*  564: 562 */    this.gjkPairDetector.setMinkowskiA(paramConvexShape1);
/*  565: 563 */    this.gjkPairDetector.setMinkowskiB(paramConvexShape2);
/*  566:     */    
/*  567: 565 */    localClosestPointInput.maximumDistanceSquared = (paramConvexShape1.getMargin() + paramConvexShape2.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
/*  568:     */    
/*  569: 567 */    localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
/*  570:     */    
/*  574: 572 */    localClosestPointInput.transformA.set(paramTransform1);
/*  575: 573 */    localClosestPointInput.transformB.set(paramTransform2);
/*  576: 574 */    this.gjkPairDetector.getClosestPoints(localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw);
/*  577:     */    
/*  579: 577 */    this.pointInputsPool.release(localClosestPointInput);
/*  580:     */    
/*  586: 584 */    this.currentMaxDepth = this.gjkPairDetector.maxDepth;
/*  587: 585 */    return this.gjkPairDetector.contacts;
/*  588:     */  }
/*  589:     */  
/*  630:     */  private void doNarrowTest(SegmentData paramSegmentData1, SegmentData paramSegmentData2, o paramo1, o paramo2, o paramo3, o paramo4, int paramInt1, int paramInt2, xO paramxO, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*  631:     */  {
/*  632: 630 */    paramo2 = org.schema.game.common.data.physics.octree.ArrayOctreeTraverse.tMap[paramInt1];
/*  633: 631 */    paramo4 = org.schema.game.common.data.physics.octree.ArrayOctreeTraverse.tMap[paramInt2];
/*  634: 632 */    for (paramInt1 = 0; paramInt1 < paramo2.length; paramInt1++)
/*  635:     */    {
/*  636: 634 */      paramInt2 = (byte)(paramo1.a + paramo2[paramInt1][0]);
/*  637: 635 */      int i = (byte)(paramo1.b + paramo2[paramInt1][1]);
/*  638: 636 */      int k = (byte)(paramo1.c + paramo2[paramInt1][2]);
/*  639:     */      
/*  642: 640 */      int m = SegmentData.getInfoIndex((byte)(paramInt2 + 8), (byte)(i + 8), (byte)(k + 8));
/*  643:     */      
/*  647: 645 */      int n = 0;
/*  648: 646 */      paramManifoldResult.getPersistentManifold().getNumContacts();
/*  649:     */      
/*  650:     */      ElementInformation localElementInformation1;
/*  651: 649 */      if ((paramSegmentData1.containsFast(m)) && ((localElementInformation1 = ElementKeyMap.getInfo(paramSegmentData1.getType(m))).isPhysical(paramSegmentData1.isActive(m)))) {
/*  652: 650 */        byte b1 = paramSegmentData1.getOrientation(m);
/*  653: 651 */        boolean bool1 = paramSegmentData1.isActive(m);
/*  654:     */        
/*  655: 653 */        this.v.elemPosA.set(paramInt2 + paramSegmentData1.getSegment().a.a, i + paramSegmentData1.getSegment().a.b, k + paramSegmentData1.getSegment().a.c);
/*  656:     */        
/*  660: 658 */        this.v.elemPosAAbs.set(this.v.elemPosA);
/*  661: 659 */        this.v.tmpTrans1.transform(this.v.elemPosAAbs);
/*  662: 660 */        this.v.min.set(this.v.elemPosA.x - 0.5F, this.v.elemPosA.y - 0.5F, this.v.elemPosA.z - 0.5F);
/*  663:     */        
/*  666: 664 */        this.v.max.set(this.v.elemPosA.x + 0.5F, this.v.elemPosA.y + 0.5F, this.v.elemPosA.z + 0.5F);
/*  667:     */        
/*  672: 670 */        CubeShape.transformAabb(this.v.min, this.v.max, margin, this.v.tmpTrans1, this.v.minOut, this.v.maxOut, this.v.aabbVarSet, this.v.absolute1);
/*  673:     */        
/*  678: 676 */        if (AabbUtil2.testAabbAgainstAabb2(paramxO.a, paramxO.b, this.v.minOut, this.v.maxOut))
/*  679:     */        {
/*  681: 679 */          for (paramInt2 = 0; paramInt2 < paramo4.length; 
/*  682:     */              
/*  684: 682 */              paramInt2++)
/*  685:     */          {
/*  686: 684 */            i = (byte)(paramo3.a + paramo4[paramInt2][0]);
/*  687: 685 */            k = (byte)(paramo3.b + paramo4[paramInt2][1]);
/*  688: 686 */            int i1 = (byte)(paramo3.c + paramo4[paramInt2][2]);
/*  689:     */            
/*  690: 688 */            int i2 = SegmentData.getInfoIndex((byte)(i + 8), (byte)(k + 8), (byte)(i1 + 8));
/*  691:     */            
/*  694:     */            ElementInformation localElementInformation2;
/*  695:     */            
/*  697: 695 */            if ((paramSegmentData2.containsFast(i2)) && ((localElementInformation2 = ElementKeyMap.getInfo(paramSegmentData2.getType(i2))).isPhysical(paramSegmentData2.isActive(i2)))) {
/*  698: 696 */              byte b2 = paramSegmentData2.getOrientation(i2);
/*  699: 697 */              boolean bool2 = paramSegmentData2.isActive(i2);
/*  700:     */              
/*  701: 699 */              this.v.elemPosB.set(i + paramSegmentData2.getSegment().a.a, k + paramSegmentData2.getSegment().a.b, i1 + paramSegmentData2.getSegment().a.c);
/*  702:     */              
/*  706: 704 */              this.v.elemPosBAbs.set(this.v.elemPosB);
/*  707: 705 */              this.v.tmpTrans2.transform(this.v.elemPosBAbs);
/*  708:     */              
/*  709: 707 */              this.v.elemPosBAbs.sub(this.v.elemPosAAbs);
/*  710:     */              
/*  711: 709 */              if ((this.v.elemPosBAbs.length() < 1.5F ? 1 : 0) != 0)
/*  712:     */              {
/*  713: 711 */                this.aabbBlockOverlapping += 1;
/*  714: 712 */                this.v.tmpTrans3.set(this.v.tmpTrans1);
/*  715: 713 */                this.v.tmpTrans4.set(this.v.tmpTrans2);
/*  716:     */                
/*  717: 715 */                this.v.nA.set(this.v.elemPosA);
/*  718: 716 */                this.v.nB.set(this.v.elemPosB);
/*  719: 717 */                this.v.tmpTrans3.basis.transform(this.v.nA);
/*  720: 718 */                this.v.tmpTrans4.basis.transform(this.v.nB);
/*  721:     */                
/*  722: 720 */                this.v.tmpTrans3.origin.add(this.v.nA);
/*  723: 721 */                this.v.tmpTrans4.origin.add(this.v.nB);
/*  724: 722 */                paramSegmentData1.getSegment();Segment.d();
/*  725: 723 */                paramSegmentData2.getSegment();Segment.d();
/*  726: 724 */                this.v.box0.setMargin(margin);
/*  727: 725 */                this.v.box1.setMargin(margin);
/*  728:     */                
/*  731: 729 */                Object localObject1 = this.v.box0;
/*  732: 730 */                Object localObject2 = this.v.box1;
/*  733: 731 */                i1 = 0;
/*  734: 732 */                if ((localElementInformation1.getBlockStyle() > 0) && (localElementInformation1.getBlockStyle() < 3))
/*  735:     */                {
/*  736: 734 */                  localObject1 = dO.a(localElementInformation1.getBlockStyle(), b1, bool1);
/*  737: 735 */                  i1 = 1;
/*  738:     */                }
/*  739: 737 */                if ((localElementInformation2.getBlockStyle() > 0) && (localElementInformation2.getBlockStyle() < 3))
/*  740:     */                {
/*  741: 739 */                  localObject2 = dO.a(localElementInformation2.getBlockStyle(), b2, bool2);
/*  742: 740 */                  i1 = 1;
/*  743:     */                }
/*  744: 742 */                this.currentMaxDepth = 0.0F;
/*  745: 743 */                int j; if (i1 != 0)
/*  746:     */                {
/*  747: 745 */                  j = doNonBlockCollision((ConvexShape)localObject1, (ConvexShape)localObject2, this.v.tmpTrans3, this.v.tmpTrans4, paramManifoldResult, paramDispatcherInfo);
/*  750:     */                }
/*  751:     */                else
/*  752:     */                {
/*  754: 752 */                  j = doExtCubeCubeCollision(this.v.box0, this.v.box1, this.v.tmpTrans3, this.v.tmpTrans4, paramManifoldResult, paramDispatcherInfo);
/*  755:     */                }
/*  756:     */                
/*  759: 757 */                if (j > 0) {
/*  760: 758 */                  paramManifoldResult.getPersistentManifold().getNumContacts();
/*  761: 759 */                  n++;
/*  762:     */                }
/*  763: 761 */                if ((!this.stuck) && (this.currentMaxDepth > 0.8F)) {
/*  764: 762 */                  System.err.println("CHECKING STUCK " + paramSegmentData1.getSegment().a() + " -> " + paramSegmentData2.getSegment().a() + ": " + n + "; CC " + paramManifoldResult.getPersistentManifold().getNumContacts() + "; " + this.currentMaxDepth);
/*  765:     */                  
/*  766: 764 */                  checkStuck(paramSegmentData1, paramSegmentData2, this.v.tmpTrans1, this.v.wtInv1, this.v.elemPosA);
/*  767:     */                  
/*  768: 766 */                  System.err.println("CHECKING STUCK " + paramSegmentData2.getSegment().a() + " -> " + paramSegmentData1.getSegment().a() + ": " + n + "; CC " + paramManifoldResult.getPersistentManifold().getNumContacts() + "; " + this.currentMaxDepth);
/*  769:     */                  
/*  770: 768 */                  checkStuck(paramSegmentData2, paramSegmentData1, this.v.tmpTrans2, this.v.wtInv0, this.v.elemPosB);
/*  771:     */                }
/*  772:     */              }
/*  773:     */            }
/*  774:     */          }
/*  775:     */        }
/*  776:     */      }
/*  777:     */    }
/*  778:     */  }
/*  779:     */  
/*  880:     */  private void checkStuck(SegmentData paramSegmentData1, SegmentData paramSegmentData2, Transform paramTransform1, Transform paramTransform2, Vector3f paramVector3f)
/*  881:     */  {
/*  882: 880 */    this.v.elemPosTest.set(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/*  883: 881 */    paramTransform1.transform(this.v.elemPosTest);
/*  884: 882 */    paramTransform2.transform(this.v.elemPosTest);
/*  885: 883 */    this.v.elemPosCheck.b((int)this.v.elemPosTest.x + 8, (int)this.v.elemPosTest.y + 8, (int)this.v.elemPosTest.z + 8);
/*  886:     */    
/*  887: 885 */    paramTransform1 = 0;
/*  888:     */    try {
/*  889: 887 */      for (paramTransform2 = 0; paramTransform2 < 6; paramTransform2++) {
/*  890: 888 */        this.v.elemPosCheckD.b(this.v.elemPosCheck, org.schema.game.common.data.element.Element.DIRECTIONSi[paramTransform2]);
/*  891:     */        
/*  893: 891 */        if (((paramVector3f = paramSegmentData2.getSegmentController().getSegmentBuffer().a(this.v.elemPosCheckD, false, this.v.pieceTmp)) != null) && (paramVector3f.a() != 0)) {
/*  894: 892 */          paramTransform1++;
/*  895:     */        }
/*  896:     */      }
/*  897:     */      
/*  899: 897 */      if (((paramTransform2 = paramSegmentData2.getSegmentController().getSegmentBuffer().a(this.v.elemPosCheck, false, this.v.pieceTmp)) != null) && (paramTransform2.a() != 0)) {
/*  900: 898 */        paramTransform1++;
/*  901:     */      }
/*  902:     */      
/*  903: 901 */      if (paramTransform1 >= 7) {
/*  904: 902 */        System.err.println("[CC]POSSIBLE STUCK: " + paramSegmentData1.getSegmentController() + " <-> " + paramSegmentData2.getSegmentController() + ": from " + this.v.elemPosTest + " to " + this.v.elemPosCheck + "; existing surround: " + paramTransform1);
/*  905: 903 */        if ((this.stuckStarted == 0L) || (System.currentTimeMillis() - this.stuckStarted > 10000L)) {
/*  906: 904 */          this.stuckStarted = System.currentTimeMillis();return;
/*  907:     */        }
/*  908: 906 */        if (System.currentTimeMillis() - this.stuckStarted > 5000L) {
/*  909: 907 */          this.stuck = true;
/*  910:     */        }
/*  911:     */      }
/*  912:     */      else
/*  913:     */      {
/*  914: 912 */        System.err.println("[CC]POSSIBLE NOT STUCK: " + paramSegmentData1.getSegmentController() + " <-> " + paramSegmentData2.getSegmentController() + ": from " + this.v.elemPosTest + " to " + this.v.elemPosCheck + "; existing surround: " + paramTransform1);
/*  915:     */      }
/*  916:     */      return; } catch (IOException localIOException) { 
/*  917:     */      
/*  920: 918 */        localIOException.printStackTrace(); return;
/*  921:     */    } catch (InterruptedException localInterruptedException) {
/*  922: 916 */      
/*  923:     */      
/*  924: 918 */        localInterruptedException;
/*  925:     */    }
/*  926:     */  }
/*  927:     */  
/*  931:     */  public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
/*  932:     */  {
/*  933: 925 */    if ((this.manifoldPtr != null) && (this.ownManifold)) {
/*  934: 926 */      paramObjectArrayList.add(this.manifoldPtr);
/*  935:     */    }
/*  936:     */  }
/*  937:     */  
/*  940:     */  public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo)
/*  941:     */  {
/*  942: 934 */    super.init(paramCollisionAlgorithmConstructionInfo);
/*  943:     */  }
/*  944:     */  
/*  949:     */  public void init(PersistentManifold paramPersistentManifold, CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, SimplexSolverInterface paramSimplexSolverInterface, GjkEpaPenetrationDepthSolver paramGjkEpaPenetrationDepthSolver)
/*  950:     */  {
/*  951: 943 */    super.init(paramCollisionAlgorithmConstructionInfo);
/*  952: 944 */    this.v = ((CubeCubeCollisionVariableSet)threadLocal.get());
/*  953: 945 */    this.gjkPairDetector = new GjkPairDetectorExt(this.v.gjkVar);
/*  954: 946 */    this.outerSegmentHandler = new CubeCubeCollisionAlgorithm.OuterSegmentHandler(this, null);
/*  955: 947 */    this.innerSegmentHandler = new CubeCubeCollisionAlgorithm.InnerSegmentHandler(this, null);
/*  956: 948 */    this.manifoldPtr = paramPersistentManifold;
/*  957: 949 */    this.gjkPairDetector.init(null, null, paramSimplexSolverInterface, paramGjkEpaPenetrationDepthSolver);
/*  958: 950 */    if (this.manifoldPtr == null) {
/*  959: 951 */      this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/*  960: 952 */      this.ownManifold = true;
/*  961:     */    }
/*  962:     */  }
/*  963:     */  
/*  968:     */  private void processDistinctCollision(CubeShape paramCubeShape1, CubeShape paramCubeShape2, SegmentData paramSegmentData1, SegmentData paramSegmentData2, Transform paramTransform1, Transform paramTransform2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*  969:     */  {
/*  970: 962 */    if (!this.v.intersectionCallBackAwithB.initialized) {
/*  971: 963 */      this.v.intersectionCallBackAwithB.createHitCache(512);
/*  972: 964 */      this.v.intersectionCallBackBwithA.createHitCache(512);
/*  973:     */    }
/*  974:     */    
/*  983: 975 */    this.v.intersectionCallBackAwithB.reset();
/*  984: 976 */    this.v.intersectionCallBackBwithA.reset();
/*  985:     */    
/*  987: 979 */    long l1 = System.nanoTime();
/*  988:     */    
/*  991: 983 */    this.v.intersectionCallBackAwithB = paramSegmentData1.getOctree().findIntersecting(this.v.oSet, this.v.intersectionCallBackAwithB, paramSegmentData1.getSegment(), paramTransform1, this.v.absolute1, margin, this.v.bbSectorIntersection.a, this.v.bbSectorIntersection.b, 1.0F);
/*  992:     */    
/*  995: 987 */    this.leafAABBTest += this.v.intersectionCallBackAwithB.aabbTest;
/*  996: 988 */    this.leafRetrieve += this.v.intersectionCallBackAwithB.aabbRetrieve;
/*  997: 989 */    this.leaftCallsA += this.v.intersectionCallBackAwithB.leafCalcs;
/*  998:     */    
/* 1000: 992 */    if (this.v.intersectionCallBackAwithB.hitCount == 0) {
/* 1001: 993 */      return;
/* 1002:     */    }
/* 1003: 995 */    this.v.intersectionCallBackBwithA.reset();
/* 1004:     */    
/* 1005: 997 */    this.v.oSet.debug = paramSegmentData2.getSegmentController().isOnServer();
/* 1006: 998 */    this.v.intersectionCallBackBwithA = paramSegmentData2.getOctree().findIntersecting(this.v.oSet, this.v.intersectionCallBackBwithA, paramSegmentData2.getSegment(), paramTransform2, this.v.absolute2, margin, this.v.bbSectorIntersection.a, this.v.bbSectorIntersection.b, 1.0F);
/* 1007:     */    
/* 1009:1001 */    this.v.oSet.debug = false;
/* 1010:1002 */    if (this.v.intersectionCallBackBwithA.hitCount == 0) {
/* 1011:1003 */      return;
/* 1012:     */    }
/* 1013:     */    
/* 1014:1006 */    for (paramCubeShape1 = 0; paramCubeShape1 < this.v.intersectionCallBackAwithB.hitCount; 
/* 1015:1007 */        paramCubeShape1++) {
/* 1016:1008 */      paramCubeShape2 = this.v.intersectionCallBackAwithB.getHit(paramCubeShape1, this.v.bbOuterOct.a, this.v.bbOuterOct.b, this.v.startA, this.v.endA);
/* 1017:     */      
/* 1022:1014 */      for (paramTransform1 = 0; paramTransform1 < this.v.intersectionCallBackBwithA.hitCount; paramTransform1++)
/* 1023:     */      {
/* 1024:1016 */        paramTransform2 = this.v.intersectionCallBackBwithA.getHit(paramTransform1, this.v.bbInnerOct.a, this.v.bbInnerOct.b, this.v.startB, this.v.endB);
/* 1025:     */        
/* 1026:1018 */        if (AabbUtil2.testAabbAgainstAabb2(this.v.bbInnerOct.a, this.v.bbInnerOct.b, this.v.bbOuterOct.a, this.v.bbOuterOct.b)) {
/* 1027:1019 */          this.v.bbInnerOct.a(this.v.bbOuterOct, this.v.bbOctIntersection);
/* 1028:     */          
/* 1029:1021 */          long l2 = System.nanoTime();
/* 1030:     */          
/* 1031:1023 */          doNarrowTest(paramSegmentData1, paramSegmentData2, this.v.startA, this.v.endA, this.v.startB, this.v.endB, paramCubeShape2, paramTransform2, this.v.bbOctIntersection, paramDispatcherInfo, paramManifoldResult);
/* 1032:     */          
/* 1033:1025 */          this.narrowTime = ((int)(this.narrowTime + (System.nanoTime() - l2)));
/* 1034:     */        }
/* 1035:     */      }
/* 1036:     */    }
/* 1037:     */    
/* 1039:1031 */    this.octATreeTime += System.nanoTime() - l1;
/* 1040:     */  }
/* 1041:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */