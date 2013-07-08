/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   5:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   6:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   7:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   8:    */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*   9:    */import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*  10:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*  11:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  12:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*  13:    */import com.bulletphysics.collision.shapes.BoxShape;
/*  14:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  15:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  16:    */import com.bulletphysics.linearmath.AabbUtil2;
/*  17:    */import com.bulletphysics.linearmath.MatrixUtil;
/*  18:    */import com.bulletphysics.linearmath.Transform;
/*  19:    */import com.bulletphysics.util.ObjectArrayList;
/*  20:    */import com.bulletphysics.util.ObjectPool;
/*  21:    */import dO;
/*  22:    */import jL;
/*  23:    */import java.io.PrintStream;
/*  24:    */import java.util.ArrayList;
/*  25:    */import javax.vecmath.Matrix3f;
/*  26:    */import javax.vecmath.Vector3f;
/*  27:    */import o;
/*  28:    */import org.schema.common.FastMath;
/*  29:    */import org.schema.common.util.ByteUtil;
/*  30:    */import org.schema.game.common.controller.SegmentController;
/*  31:    */import org.schema.game.common.data.element.ElementInformation;
/*  32:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  33:    */import org.schema.game.common.data.physics.octree.ArrayOctree;
/*  34:    */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*  35:    */import org.schema.game.common.data.world.Segment;
/*  36:    */import org.schema.game.common.data.world.SegmentData;
/*  37:    */import q;
/*  38:    */import xO;
/*  39:    */
/* 129:    */public class CubeConvexCollisionAlgorithm
/* 130:    */  extends CollisionAlgorithm
/* 131:    */{
/* 132:132 */  protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/* 133:    */  
/* 134:    */  GjkPairDetectorExt gjkPairDetector;
/* 135:    */  
/* 136:136 */  private static ThreadLocal threadLocal = new CubeConvexCollisionAlgorithm.1();
/* 137:    */  
/* 139:    */  public boolean lowLevelOfDetail;
/* 140:    */  
/* 142:    */  private boolean ownManifold;
/* 143:    */  
/* 145:    */  private static final float margin = 0.05F;
/* 146:    */  
/* 148:    */  private boolean onServer;
/* 149:    */  
/* 151:    */  private PersistentManifold manifoldPtr;
/* 152:    */  
/* 154:    */  private CubeConvexVariableSet v;
/* 155:    */  
/* 157:    */  private CubeConvexCollisionAlgorithm.OuterSegmentHandler outerSegmentHandler;
/* 158:    */  
/* 160:    */  private long currentTime;
/* 161:    */  
/* 163:    */  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 164:    */  {
/* 165:165 */    System.err.println("CALCULATING CONVEX CUBE TOI");
/* 166:166 */    return 1.0F;
/* 167:    */  }
/* 168:    */  
/* 176:    */  public void destroy() {}
/* 177:    */  
/* 184:    */  private void doNarrowTest(SegmentData paramSegmentData, CollisionObject paramCollisionObject, o paramo1, o paramo2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 185:    */  {
/* 186:186 */    paramCollisionObject.getCollisionShape().getAabb(this.v.convexShapeTransform, this.v.otherminOut, this.v.othermaxOut);
/* 187:    */    
/* 188:188 */    for (paramCollisionObject = paramo1.a; paramCollisionObject < paramo2.a; paramCollisionObject = (byte)(paramCollisionObject + 1)) {
/* 189:189 */      for (paramDispatcherInfo = paramo1.b; paramDispatcherInfo < paramo2.b; paramDispatcherInfo = (byte)(paramDispatcherInfo + 1)) {
/* 190:190 */        for (paramManifoldResult = paramo1.c; paramManifoldResult < paramo2.c; paramManifoldResult = (byte)(paramManifoldResult + 1)) {
/* 191:191 */          int i = SegmentData.getInfoIndex((byte)(paramCollisionObject + 8), (byte)(paramDispatcherInfo + 8), (byte)(paramManifoldResult + 8));
/* 192:    */          
/* 194:    */          ElementInformation localElementInformation;
/* 195:    */          
/* 196:196 */          if ((paramSegmentData.contains(i)) && ((localElementInformation = ElementKeyMap.getInfo(paramSegmentData.getType(i))).isPhysical(paramSegmentData.isActive(i))))
/* 197:    */          {
/* 198:198 */            int j = paramSegmentData.getOrientation(i);
/* 199:    */            
/* 200:200 */            this.v.elemPosA.set(paramCollisionObject, paramDispatcherInfo, paramManifoldResult);
/* 201:201 */            this.v.elemPosA.x += paramSegmentData.getSegment().a.a;
/* 202:202 */            this.v.elemPosA.y += paramSegmentData.getSegment().a.b;
/* 203:203 */            this.v.elemPosA.z += paramSegmentData.getSegment().a.c;
/* 204:204 */            this.v.min.set(this.v.elemPosA);
/* 205:205 */            this.v.max.set(this.v.elemPosA);
/* 206:    */            
/* 207:207 */            this.v.min.x -= 0.5F;
/* 208:208 */            this.v.min.y -= 0.5F;
/* 209:209 */            this.v.min.z -= 0.5F;
/* 210:210 */            this.v.max.x += 0.5F;
/* 211:211 */            this.v.max.y += 0.5F;
/* 212:212 */            this.v.max.z += 0.5F;
/* 213:    */            
/* 217:217 */            AabbUtil2.transformAabb(this.v.min, this.v.max, 0.25F, this.v.cubeMeshTransform, this.v.minOut, this.v.maxOut);
/* 218:    */            
/* 221:221 */            this.v.box0.setMargin(0.0F);
/* 222:    */            
/* 228:228 */            if (AabbUtil2.testAabbAgainstAabb2(this.v.minOut, this.v.maxOut, this.v.otherminOut, this.v.othermaxOut))
/* 229:    */            {
/* 230:230 */              paramSegmentData.getSegment();Segment.d();
/* 231:231 */              this.v.boxTransformation.set(this.v.cubeMeshTransform);
/* 232:    */              
/* 233:233 */              this.v.nA.set(this.v.elemPosA);
/* 234:234 */              this.v.boxTransformation.basis.transform(this.v.nA);
/* 235:235 */              this.v.boxTransformation.origin.add(this.v.nA);
/* 236:    */              
/* 237:237 */              this.v.positionCache.add(new Transform(this.v.boxTransformation));
/* 238:238 */              this.v.blockInfoCache.add(new q(localElementInformation.getBlockStyle(), j, paramSegmentData.isActive(i) ? 1 : 0));
/* 239:    */            }
/* 240:    */          }
/* 241:    */        }
/* 242:    */      }
/* 243:    */    }
/* 244:    */  }
/* 245:    */  
/* 253:    */  private void doRegularCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, PersistentManifold paramPersistentManifold, DispatcherInfo paramDispatcherInfo)
/* 254:    */  {
/* 255:    */    DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
/* 256:    */    
/* 263:263 */    (localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
/* 264:    */    
/* 267:267 */    this.gjkPairDetector.setMinkowskiA(paramConvexShape1);
/* 268:268 */    this.gjkPairDetector.setMinkowskiB(paramConvexShape2);
/* 269:    */    
/* 270:270 */    localClosestPointInput.maximumDistanceSquared = (paramConvexShape1.getMargin() + paramPersistentManifold.getContactBreakingThreshold());
/* 271:    */    
/* 272:272 */    localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
/* 273:    */    
/* 277:277 */    localClosestPointInput.transformA.set(paramTransform1);
/* 278:278 */    localClosestPointInput.transformB.set(paramTransform2);
/* 279:    */    
/* 280:280 */    this.gjkPairDetector.getClosestPoints(localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw);
/* 281:    */    
/* 291:291 */    this.pointInputsPool.release(localClosestPointInput);
/* 292:    */    
/* 293:293 */    paramManifoldResult.refreshContactPoints();
/* 294:    */  }
/* 295:    */  
/* 301:    */  public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
/* 302:    */  {
/* 303:303 */    if ((this.manifoldPtr != null) && (this.ownManifold)) {
/* 304:304 */      paramObjectArrayList.add(this.manifoldPtr);
/* 305:    */    }
/* 306:    */  }
/* 307:    */  
/* 310:    */  public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo)
/* 311:    */  {
/* 312:312 */    super.init(paramCollisionAlgorithmConstructionInfo);
/* 313:    */  }
/* 314:    */  
/* 319:    */  public void init(PersistentManifold paramPersistentManifold, CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver, boolean paramBoolean)
/* 320:    */  {
/* 321:321 */    super.init(paramCollisionAlgorithmConstructionInfo);
/* 322:322 */    this.manifoldPtr = paramPersistentManifold;
/* 323:323 */    this.v = ((CubeConvexVariableSet)threadLocal.get());
/* 324:    */    
/* 325:325 */    this.gjkPairDetector = new GjkPairDetectorExt(this.v.gjkVars);
/* 326:    */    
/* 327:327 */    this.gjkPairDetector.init(null, null, paramSimplexSolverInterface, paramConvexPenetrationDepthSolver);
/* 328:328 */    if (this.manifoldPtr == null) {
/* 329:329 */      if (!paramBoolean) {
/* 330:330 */        this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/* 331:    */      } else {
/* 332:332 */        this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject2, paramCollisionObject1);
/* 333:    */      }
/* 334:334 */      this.ownManifold = true;
/* 335:    */    }
/* 336:336 */    this.outerSegmentHandler = new CubeConvexCollisionAlgorithm.OuterSegmentHandler(this, null);
/* 337:    */  }
/* 338:    */  
/* 341:    */  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 342:    */  {
/* 343:343 */    if (this.manifoldPtr == null)
/* 344:    */    {
/* 353:353 */      this.ownManifold = true;
/* 354:    */    }
/* 355:    */    
/* 356:356 */    this.manifoldPtr = paramManifoldResult.getPersistentManifold();
/* 357:357 */    this.ownManifold = true;
/* 358:    */    
/* 361:361 */    long l = System.currentTimeMillis();
/* 362:362 */    this.currentTime = l;
/* 363:    */    
/* 383:383 */    if (((paramCollisionObject2 = paramCollisionObject2) instanceof PairCachingGhostObjectUncollidable)) {
/* 384:384 */      return;
/* 385:    */    }
/* 386:    */    
/* 387:387 */    this.v.positionCache.clear();
/* 388:388 */    this.v.blockInfoCache.clear();
/* 389:389 */    paramManifoldResult.setPersistentManifold(this.manifoldPtr);
/* 390:390 */    Object localObject1 = (CubeShape)paramCollisionObject1.getCollisionShape();
/* 391:391 */    ConvexShape localConvexShape = (ConvexShape)paramCollisionObject2.getCollisionShape();
/* 392:    */    
/* 393:393 */    this.onServer = ((CubeShape)localObject1).getSegmentBuffer().a().isOnServer();
/* 394:    */    
/* 398:398 */    this.v.oSet = ArrayOctree.getSet(((CubeShape)localObject1).getSegmentBuffer().a().isOnServer());
/* 399:    */    
/* 400:400 */    this.v.cubeMeshTransform = paramCollisionObject1.getWorldTransform(this.v.cubeMeshTransform);
/* 401:401 */    this.v.convexShapeTransform = paramCollisionObject2.getWorldTransform(this.v.convexShapeTransform);
/* 402:    */    
/* 403:403 */    this.v.absolute.set(this.v.cubeMeshTransform.basis);
/* 404:404 */    MatrixUtil.absolute(this.v.absolute);
/* 405:405 */    ((CubeShape)localObject1).setMargin(0.05F);
/* 406:406 */    ((CubeShape)localObject1).getAabb(this.v.cubeMeshTransform, this.v.outMin, this.v.outMax);
/* 407:407 */    localConvexShape.getAabb(this.v.convexShapeTransform, this.v.shapeMin, this.v.shapeMax);
/* 408:    */    
/* 409:409 */    if (!AabbUtil2.testAabbAgainstAabb2(this.v.outMin, this.v.outMax, this.v.shapeMin, this.v.shapeMax)) {
/* 410:410 */      return;
/* 411:    */    }
/* 412:    */    
/* 414:414 */    ((CubeShape)localObject1).setMargin(0.05F);
/* 415:    */    
/* 416:416 */    this.outerSegmentHandler.col1 = paramCollisionObject2;
/* 417:417 */    this.outerSegmentHandler.cubeShape0 = ((CubeShape)localObject1);
/* 418:418 */    this.outerSegmentHandler.dispatchInfo = paramDispatcherInfo;
/* 419:419 */    this.outerSegmentHandler.resultOut = paramManifoldResult;
/* 420:    */    
/* 421:421 */    this.v.outer.a.set(this.v.outMin);
/* 422:422 */    this.v.outer.b.set(this.v.outMax);
/* 423:423 */    this.v.inner.a.set(this.v.shapeMin);
/* 424:424 */    this.v.inner.b.set(this.v.shapeMax);
/* 425:    */    
/* 427:427 */    if ((this.v.inner.a(this.v.outer, this.v.outBB) == null) || (!this.v.outBB.b()))
/* 428:    */    {
/* 429:429 */      return;
/* 430:    */    }
/* 431:    */    
/* 433:433 */    paramCollisionObject1.getWorldTransform(this.v.inv);
/* 434:434 */    this.v.inv.inverse();
/* 435:435 */    AabbUtil2.transformAabb(new Vector3f(this.v.outBB.a), new Vector3f(this.v.outBB.b), 0.5F, this.v.inv, this.v.outBB.a, this.v.outBB.b);
/* 436:    */    
/* 441:441 */    this.v.minIntA.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/* 442:442 */    this.v.minIntA.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/* 443:443 */    this.v.minIntA.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/* 444:    */    
/* 445:445 */    this.v.maxIntA.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/* 446:446 */    this.v.maxIntA.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/* 447:447 */    this.v.maxIntA.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/* 448:    */    
/* 449:449 */    ((CubeShape)localObject1).getSegmentBuffer().b(this.outerSegmentHandler, this.v.minIntA, this.v.maxIntA);
/* 450:    */    
/* 458:458 */    paramCollisionObject1 = this.v.positionCache.size();
/* 459:    */    
/* 462:462 */    for (paramCollisionObject2 = 0; paramCollisionObject2 < paramCollisionObject1; paramCollisionObject2++) {
/* 463:463 */      localObject1 = (Transform)this.v.positionCache.get(paramCollisionObject2);
/* 464:    */      
/* 465:    */      q localq;
/* 466:466 */      int i = (localq = (q)this.v.blockInfoCache.get(paramCollisionObject2)).a;
/* 467:467 */      int j = localq.b;
/* 468:468 */      boolean bool = localq.c == 1;
/* 469:    */      
/* 470:470 */      Object localObject2 = this.v.box0;
/* 471:471 */      if ((i > 0) && (i < 3)) {
/* 472:472 */        localObject2 = dO.a(i, (byte)j, bool);
/* 473:    */      }
/* 474:    */      try
/* 475:    */      {
/* 476:476 */        doRegularCollision((ConvexShape)localObject2, localConvexShape, (Transform)localObject1, this.v.convexShapeTransform, paramManifoldResult, this.manifoldPtr, paramDispatcherInfo);
/* 477:    */      }
/* 478:    */      catch (Exception localException) {
/* 479:479 */        
/* 480:    */        
/* 484:484 */          localException.printStackTrace();System.err.println(this.v.box0 + ", " + localConvexShape + ", " + localObject1 + ", " + this.v.convexShapeTransform + ", " + paramManifoldResult + ", " + paramDispatcherInfo);
/* 485:    */      } }
/* 486:486 */    if (this.ownManifold) {
/* 487:487 */      paramManifoldResult.refreshContactPoints();return;
/* 488:    */    }
/* 489:489 */    if (!$assertionsDisabled) { throw new AssertionError();
/* 490:    */    }
/* 491:    */  }
/* 492:    */  
/* 520:    */  public void processDistinctCollision(CubeShape paramCubeShape, CollisionObject paramCollisionObject, SegmentData paramSegmentData, Transform paramTransform1, Transform paramTransform2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 521:    */  {
/* 522:522 */    if (!this.v.intersectionCallBackAwithB.initialized) {
/* 523:523 */      this.v.intersectionCallBackAwithB.createHitCache(512);
/* 524:    */    }
/* 525:    */    
/* 529:529 */    paramCollisionObject.getCollisionShape().getAabb(paramTransform2, this.v.othermin, this.v.othermax);
/* 530:    */    
/* 531:531 */    this.v.intersectionCallBackAwithB.reset();
/* 532:    */    
/* 535:535 */    this.v.intersectionCallBackAwithB = paramSegmentData.getOctree().findIntersecting(this.v.oSet, this.v.intersectionCallBackAwithB, paramSegmentData.getSegment(), paramTransform1, this.v.absolute, 0.05F, this.v.othermin, this.v.othermax, 1.0F);
/* 536:    */    
/* 542:542 */    if (this.v.intersectionCallBackAwithB.hitCount == 0) {
/* 543:543 */      paramManifoldResult.refreshContactPoints();
/* 544:544 */      return;
/* 545:    */    }
/* 546:    */    
/* 547:547 */    for (paramCubeShape = 0; paramCubeShape < this.v.intersectionCallBackAwithB.hitCount; 
/* 548:548 */        paramCubeShape++)
/* 549:    */    {
/* 550:550 */      this.v.intersectionCallBackAwithB.getHit(paramCubeShape, this.v.hitMin, this.v.hitMax, this.v.startA, this.v.endA);
/* 551:    */      
/* 552:552 */      assert ((this.v.startA.a < this.v.endA.a) && (this.v.startA.b < this.v.endA.b) && (this.v.startA.c < this.v.endA.c));
/* 553:    */      
/* 554:554 */      doNarrowTest(paramSegmentData, paramCollisionObject, this.v.startA, this.v.endA, paramDispatcherInfo, paramManifoldResult);
/* 555:    */    }
/* 556:    */  }
/* 557:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */