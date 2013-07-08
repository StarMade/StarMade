/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
/*   5:    */import com.bulletphysics.collision.narrowphase.ConvexCast;
/*   6:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   7:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*   8:    */import com.bulletphysics.collision.shapes.BoxShape;
/*   9:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  10:    */import com.bulletphysics.linearmath.AabbUtil2;
/*  11:    */import com.bulletphysics.linearmath.MatrixUtil;
/*  12:    */import com.bulletphysics.linearmath.Transform;
/*  13:    */import com.bulletphysics.util.ObjectPool;
/*  14:    */import dO;
/*  15:    */import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*  16:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*  17:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*  18:    */import jL;
/*  19:    */import java.io.PrintStream;
/*  20:    */import java.util.Iterator;
/*  21:    */import java.util.List;
/*  22:    */import java.util.Map.Entry;
/*  23:    */import javax.vecmath.Matrix3f;
/*  24:    */import javax.vecmath.Tuple3f;
/*  25:    */import javax.vecmath.Vector3f;
/*  26:    */import ld;
/*  27:    */import o;
/*  28:    */import org.schema.game.common.controller.SegmentController;
/*  29:    */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*  30:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*  31:    */import org.schema.game.common.controller.elements.shield.ShieldUnit;
/*  32:    */import org.schema.game.common.data.element.ElementCollection;
/*  33:    */import org.schema.game.common.data.element.ElementInformation;
/*  34:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  35:    */import org.schema.game.common.data.physics.octree.ArrayOctree;
/*  36:    */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*  37:    */import org.schema.game.common.data.world.Segment;
/*  38:    */import org.schema.game.common.data.world.SegmentData;
/*  39:    */import org.schema.schine.network.server.ServerStateInterface;
/*  40:    */import q;
/*  41:    */import s;
/*  42:    */import xO;
/*  43:    */
/*  69:    */public class SubsimplexRayCubesCovexCast
/*  70:    */  extends ConvexCast
/*  71:    */{
/*  72: 72 */  private static ThreadLocal threadLocal = new SubsimplexRayCubesCovexCast.1();
/*  73:    */  
/*  76:    */  public static boolean debug;
/*  77:    */  
/*  79: 79 */  private float extraMargin = 0.0F;
/*  80:    */  private CubeRayCastResult rayResult;
/*  81:    */  private CubeRayVariableSet v;
/*  82: 82 */  ObjectPool pool = ObjectPool.get(o.class);
/*  83: 83 */  ObjectPool pool4 = ObjectPool.get(s.class);
/*  84: 84 */  ObjectPool aabbpool = ObjectPool.get(AABBb.class);
/*  85:    */  SubsimplexRayCubesCovexCast.OuterSegmentIterator outerSegmentIterator;
/*  86: 86 */  boolean hit = false;
/*  87: 87 */  int hitboxes = 0;
/*  88: 88 */  int casts = 0;
/*  89:    */  
/*  90:    */  private long currentTime;
/*  91:    */  
/*  92:    */  public SubsimplexRayCubesCovexCast(ConvexShape paramConvexShape, CollisionObject paramCollisionObject, SimplexSolverInterface paramSimplexSolverInterface, CubeRayCastResult paramCubeRayCastResult)
/*  93:    */  {
/*  94: 94 */    this.v = ((CubeRayVariableSet)threadLocal.get());
/*  95:    */    
/*  96: 96 */    this.outerSegmentIterator = new SubsimplexRayCubesCovexCast.OuterSegmentIterator(this, null);
/*  97: 97 */    this.v.shapeA = paramConvexShape;
/*  98: 98 */    this.v.cubesB = ((CubeShape)paramCollisionObject.getCollisionShape());
/*  99: 99 */    this.v.cubesCollisionObject = paramCollisionObject;
/* 100:100 */    this.v.simplexSolver = paramSimplexSolverInterface;
/* 101:101 */    this.rayResult = paramCubeRayCastResult;
/* 102:    */    
/* 103:103 */    this.v.box0.setMargin(0.15F);
/* 104:104 */    this.v.lastMinDist = -1.0F;
/* 105:    */  }
/* 106:    */  
/* 124:    */  public boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
/* 125:    */  {
/* 126:126 */    if (debug) {
/* 127:127 */      System.err.println("TESTING RAY COLLISION:  " + paramTransform1.origin + " -> " + paramTransform2.origin + " on " + this.v.cubesB.getSegmentBuffer().a());
/* 128:    */    }
/* 129:    */    
/* 130:130 */    this.currentTime = System.currentTimeMillis();
/* 131:131 */    this.v.lastHitpointWorld.set((0.0F / 0.0F), 0.0F, 0.0F);
/* 132:    */    
/* 134:134 */    if ((this.rayResult.filter != null) && (!this.rayResult.filter.equals(this.v.cubesB.getSegmentBuffer().a()))) {
/* 135:135 */      return false;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    this.v.lastMinDist = -1.0F;
/* 139:139 */    this.hit = false;
/* 140:140 */    if (this.v.oSet == null) {
/* 141:141 */      this.v.oSet = ArrayOctree.getSet(this.v.cubesB.getSegmentBuffer().a().getState() instanceof ServerStateInterface);
/* 142:    */    } else {
/* 143:143 */      assert (this.v.oSet == ArrayOctree.getSet(this.v.cubesB.getSegmentBuffer().a().isOnServer()));
/* 144:    */    }
/* 145:145 */    this.v.absolute.set(paramTransform3.basis);
/* 146:146 */    MatrixUtil.absolute(this.v.absolute);
/* 147:    */    
/* 149:149 */    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$302(this.outerSegmentIterator, paramTransform1);
/* 150:150 */    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$402(this.outerSegmentIterator, false);
/* 151:151 */    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$502(this.outerSegmentIterator, paramCastResult);
/* 152:152 */    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$602(this.outerSegmentIterator, paramTransform3);
/* 153:153 */    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$702(this.outerSegmentIterator, paramTransform2);
/* 154:154 */    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$802(this.outerSegmentIterator, paramTransform4);
/* 155:    */    
/* 157:157 */    this.v.solve.initialize(paramTransform1.origin, paramTransform2.origin, this.v.cubesB.getSegmentBuffer().a(), paramTransform3);
/* 158:158 */    if (debug) {
/* 159:159 */      System.err.println("TRAVERSING ON: " + this.outerSegmentIterator);
/* 160:    */    }
/* 161:    */    
/* 165:165 */    this.v.solve.traverseSegmentsOnRay(this.outerSegmentIterator);
/* 166:    */    
/* 168:168 */    if (SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$400(this.outerSegmentIterator))
/* 169:    */    {
/* 176:176 */      return true;
/* 177:    */    }
/* 178:    */    
/* 191:191 */    return this.hit;
/* 192:    */  }
/* 193:    */  
/* 197:    */  private boolean checkExplicitCollision(CollisionObject paramCollisionObject, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentController paramSegmentController, ConvexCast.CastResult paramCastResult)
/* 198:    */  {
/* 199:199 */    if (((paramSegmentController instanceof ld)) && ((((ld)paramSegmentController).a() instanceof ShieldContainerInterface)))
/* 200:    */    {
/* 201:201 */      for (paramSegmentController = ((ShieldContainerInterface)((ld)paramSegmentController).a()).getShieldManager().getCollection().iterator(); paramSegmentController.hasNext();) { ShieldUnit localShieldUnit;
/* 202:202 */        if (((ShieldUnit)(localShieldUnit = (ShieldUnit)paramSegmentController.next())).getShields() <= 0) {
/* 203:203 */          System.err.println("Shields decativated");
/* 204:    */          
/* 205:205 */          return false;
/* 206:    */        }
/* 207:207 */        this.v.tmpTrans3.set(paramTransform3);
/* 208:208 */        localShieldUnit.getOpenGLCenter(this.v.fromHelp);
/* 209:    */        
/* 210:210 */        this.v.tmpTrans3.basis.transform(this.v.fromHelp);
/* 211:    */        
/* 212:212 */        this.v.tmpTrans3.origin.add(this.v.fromHelp);
/* 213:    */        
/* 214:214 */        this.v.simplexSolver.reset();
/* 215:    */        
/* 216:216 */        this.v.sphereShape.setRadius(localShieldUnit.getRadius());
/* 217:    */        
/* 218:218 */        ContinuousConvexCollision localContinuousConvexCollision = new ContinuousConvexCollision(this.v.shapeA, this.v.sphereShape, this.v.simplexSolver, this.v.gjkEpaPenetrationDepthSolver);
/* 219:    */        
/* 220:220 */        this.v.sphereShape.getAabb(this.v.tmpTrans3, this.v.outMin, this.v.outMax);
/* 221:    */        
/* 224:224 */        if ((localContinuousConvexCollision.calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans3, this.v.tmpTrans3, paramCastResult, this.v.gjkVar)) && 
/* 225:225 */          (paramCastResult.fraction < this.rayResult.closestHitFraction))
/* 226:    */        {
/* 229:229 */          paramTransform1.basis.transform(paramCastResult.normal);
/* 230:    */          
/* 232:232 */          paramCastResult.normal.normalize();
/* 233:233 */          paramCollisionObject = new CollisionWorld.LocalRayResult(paramCollisionObject, null, paramCastResult.normal, paramCastResult.fraction);
/* 234:    */          
/* 238:238 */          this.rayResult.setUserData(localShieldUnit);
/* 239:239 */          this.rayResult.addSingleResult(paramCollisionObject, true);
/* 240:    */          
/* 241:241 */          System.err.println("[RAY] Explicid COLLISION");
/* 242:242 */          return true;
/* 243:    */        }
/* 244:    */      }
/* 245:    */    }
/* 246:    */    
/* 247:247 */    return false;
/* 248:    */  }
/* 249:    */  
/* 250:250 */  private void checkSegment(Segment paramSegment, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult) { if ((paramSegment.a() == null) || (paramSegment.g()))
/* 251:    */    {
/* 252:252 */      return;
/* 253:    */    }
/* 254:    */    
/* 255:255 */    paramSegment.a(this.v.distToHit);
/* 256:256 */    this.v.distToHit.sub(paramTransform1.origin);
/* 257:    */    
/* 258:258 */    this.v.cubesB.setMargin(this.v.cubesB.getMargin() + this.extraMargin);
/* 259:    */    
/* 260:260 */    this.v.cubesB.getSegmentAabb(paramSegment, paramTransform3, this.v.outMin, this.v.outMax, this.v.localMinOut, this.v.localMaxOut, this.v.aabbVarSet);
/* 261:261 */    this.v.hitLambda[0] = 1.0F;
/* 262:262 */    this.v.normal.set(0.0F, 0.0F, 0.0F);
/* 263:    */    
/* 264:264 */    this.v.cubesB.setMargin(this.v.cubesB.getMargin() - this.extraMargin);
/* 265:    */    
/* 269:269 */    paramTransform4 = (AabbUtil2.rayAabb(paramTransform1.origin, paramTransform2.origin, this.v.outMin, this.v.outMax, this.v.hitLambda, this.v.normal)) || (xO.a(paramTransform1.origin, this.v.outMin, this.v.outMax)) || (xO.a(paramTransform2.origin, this.v.outMin, this.v.outMax)) ? 1 : 0;
/* 270:    */    
/* 274:274 */    if (debug) {
/* 275:275 */      System.err.println("HIT?: " + paramTransform4);
/* 276:    */    }
/* 277:277 */    if (paramTransform4 != 0)
/* 278:    */    {
/* 282:282 */      new Transform().setIdentity();
/* 283:    */      
/* 290:290 */      this.hitboxes += 1;
/* 291:    */      
/* 305:305 */      paramSegment = performCastTest(this.v.cubesCollisionObject, paramSegment, paramTransform1, paramTransform2, paramTransform3, paramCastResult);
/* 306:306 */      for (paramTransform1 = this.v.sorted.values().iterator(); paramTransform1.hasNext();) { paramTransform2 = (s)paramTransform1.next();
/* 307:307 */        this.pool4.release(paramTransform2);
/* 308:    */      }
/* 309:309 */      this.v.sorted.clear();
/* 310:    */      
/* 320:320 */      if (paramSegment != 0) {
/* 321:321 */        this.casts += 1;
/* 322:    */      }
/* 323:323 */      this.hit |= paramSegment;
/* 324:324 */      if (paramSegment != 0) {
/* 325:325 */        if (this.rayResult.hasHit()) {
/* 326:326 */          return;
/* 327:    */        }
/* 328:    */        
/* 330:330 */        this.v.lastHitpointWorld.set(this.rayResult.hitPointWorld);
/* 331:331 */        if ((this.v.lastMinDist < 0.0F) || (this.v.distToHit.length() < this.v.lastMinDist)) {
/* 332:332 */          this.v.lastMinDist = this.v.distToHit.length();
/* 333:    */        }
/* 334:    */      }
/* 335:    */    }
/* 336:    */  }
/* 337:    */  
/* 363:    */  private void doNarrorTest(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentData paramSegmentData, ConvexCast.CastResult paramCastResult, o paramo1, o paramo2)
/* 364:    */  {
/* 365:365 */    this.v.posCachePointer = 0;
/* 366:366 */    for (paramTransform2 = paramo1.a; paramTransform2 < paramo2.a; paramTransform2 = (byte)(paramTransform2 + 1)) {
/* 367:367 */      for (paramCastResult = paramo1.b; paramCastResult < paramo2.b; paramCastResult = (byte)(paramCastResult + 1)) {
/* 368:368 */        for (int i = paramo1.c; i < paramo2.c; i = (byte)(i + 1)) {
/* 369:369 */          this.v.elemA.b((byte)(paramTransform2 + 8), (byte)(paramCastResult + 8), (byte)(i + 8));
/* 370:    */          
/* 373:373 */          int j = SegmentData.getInfoIndex(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c);
/* 374:    */          
/* 382:382 */          if ((paramSegmentData.containsFast(j)) && ((this.rayResult == null) || (this.rayResult.isIgnoereNotPhysical()) || (ElementKeyMap.getInfo(paramSegmentData.getType(j)).isPhysical(paramSegmentData.isActive(j))))) {
/* 383:383 */            ElementInformation localElementInformation = ElementKeyMap.getInfo(paramSegmentData.getType(j));
/* 384:384 */            int k = paramSegmentData.getOrientation(j);
/* 385:385 */            boolean bool = paramSegmentData.isActive(j);
/* 386:    */            
/* 389:389 */            this.v.elemPosA.set(paramTransform2, paramCastResult, i);
/* 390:390 */            this.v.elemPosA.x += paramSegmentData.getSegment().a.a;
/* 391:391 */            this.v.elemPosA.y += paramSegmentData.getSegment().a.b;
/* 392:392 */            this.v.elemPosA.z += paramSegmentData.getSegment().a.c;
/* 393:    */            
/* 394:394 */            this.v.nA.set(this.v.elemPosA);
/* 395:395 */            this.v.tmpTrans3.set(paramTransform3);
/* 396:396 */            this.v.tmpTrans3.basis.transform(this.v.nA);
/* 397:397 */            this.v.tmpTrans3.origin.add(this.v.nA);
/* 398:398 */            this.v.box0.setMargin(0.2F + this.extraMargin);
/* 399:399 */            this.v.normal.set(0.0F, 0.0F, 0.0F);
/* 400:    */            
/* 401:401 */            this.v.distTest.set(paramTransform1.origin);
/* 402:402 */            this.v.distTest.sub(this.v.tmpTrans3.origin);
/* 403:    */            
/* 407:    */            s locals;
/* 408:    */            
/* 412:412 */            (locals = (s)this.pool4.get()).a(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c, k * 10 + localElementInformation.getBlockStyle() + (bool ? 1000 : 0));
/* 413:    */            
/* 414:414 */            float f = this.v.distTest.length();
/* 415:415 */            while (this.v.sorted.containsKey(f)) {
/* 416:416 */              f += 0.1F;
/* 417:    */            }
/* 418:418 */            this.v.sorted.put(f, locals);
/* 419:419 */            this.v.posCachePointer += 1;
/* 420:    */          }
/* 421:    */        }
/* 422:    */      }
/* 423:    */    }
/* 424:    */  }
/* 425:    */  
/* 431:    */  private boolean performCastTest(CollisionObject paramCollisionObject, Segment paramSegment, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, ConvexCast.CastResult paramCastResult)
/* 432:    */  {
/* 433:433 */    paramSegment = paramSegment.a();
/* 434:    */    
/* 435:435 */    if (!this.v.intersectionCallBack.initialized) {
/* 436:436 */      this.v.intersectionCallBack.createHitCache(512);
/* 437:    */    }
/* 438:438 */    this.v.intersectionCallBack.reset();
/* 439:439 */    this.v.intersectionCallBack = paramSegment.getOctree().findIntersectingRay(this.v.oSet, this.v.intersectionCallBack, paramTransform3, this.v.absolute, 0.15F + this.extraMargin, paramSegment.getSegment(), paramTransform1.origin, paramTransform2.origin, 1.0F);
/* 440:    */    
/* 444:444 */    this.v.sortedAABB.clear();
/* 445:445 */    this.v.sorted.clear();
/* 446:446 */    if (debug)
/* 447:447 */      System.err.println("CAST TEST: (INSIDE)" + this.v.intersectionCallBack.hitCount);
/* 448:    */    Object localObject2;
/* 449:449 */    Object localObject3; if (this.v.intersectionCallBack.hitCount > 0)
/* 450:    */    {
/* 451:451 */      for (int i = 0; i < this.v.intersectionCallBack.hitCount; i++) {
/* 452:452 */        this.v.intersectionCallBack.getHit(i, this.v.minOut, this.v.maxOut, this.v.startOut, this.v.endOut);
/* 453:    */        
/* 454:454 */        (
/* 455:455 */          localObject1 = new Vector3f(this.v.maxOut)).sub(this.v.minOut);
/* 456:456 */        ((Vector3f)localObject1).scale(0.5F + this.extraMargin);
/* 457:    */        
/* 459:459 */        (
/* 460:460 */          localObject2 = new Vector3f(this.v.minOut)).add((Tuple3f)localObject1);
/* 461:461 */        ((Vector3f)localObject2).sub(paramTransform1.origin);
/* 462:462 */        float f = ((Vector3f)localObject2).length();
/* 463:463 */        while (this.v.sortedAABB.containsKey(f)) {
/* 464:464 */          f += 0.1F;
/* 465:    */        }
/* 466:    */        
/* 467:467 */        AABBb localAABBb = (AABBb)this.aabbpool.get();
/* 468:468 */        localObject2 = (o)this.pool.get();
/* 469:469 */        localObject3 = (o)this.pool.get();
/* 470:470 */        ((o)localObject2).b(this.v.startOut);
/* 471:471 */        ((o)localObject3).b(this.v.endOut);
/* 472:472 */        localAABBb.min = ((o)localObject2);
/* 473:473 */        localAABBb.max = ((o)localObject3);
/* 474:474 */        this.v.sortedAABB.put(f, localAABBb);
/* 475:    */      }
/* 476:    */      
/* 478:478 */      for (Iterator localIterator = this.v.sortedAABB.entrySet().iterator(); localIterator.hasNext();) { localObject1 = (Map.Entry)localIterator.next();
/* 479:479 */        doNarrorTest(paramTransform1, paramTransform2, paramTransform3, paramSegment, paramCastResult, ((AABBb)((Map.Entry)localObject1).getValue()).min, ((AABBb)((Map.Entry)localObject1).getValue()).max);
/* 480:480 */        this.pool.release(((AABBb)((Map.Entry)localObject1).getValue()).min);
/* 481:481 */        this.pool.release(((AABBb)((Map.Entry)localObject1).getValue()).max);
/* 482:482 */        this.aabbpool.release(((Map.Entry)localObject1).getValue());
/* 483:    */      }
/* 484:484 */      this.v.sortedAABB.clear();
/* 485:    */    }
/* 486:    */    
/* 490:490 */    for (Object localObject1 = this.v.sorted.entrySet().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/* 491:    */      
/* 493:493 */      this.v.elemA.b((byte)((s)((Map.Entry)localObject2).getValue()).a, (byte)((s)((Map.Entry)localObject2).getValue()).b, (byte)((s)((Map.Entry)localObject2).getValue()).c);
/* 494:494 */      this.v.elemPosA.set(this.v.elemA.a - 8, this.v.elemA.b - 8, this.v.elemA.c - 8);
/* 495:    */      
/* 499:499 */      int k = ((s)((Map.Entry)localObject2).getValue()).d % 10;
/* 500:500 */      boolean bool = ((s)((Map.Entry)localObject2).getValue()).d >= 1000;
/* 501:501 */      int j = (((s)((Map.Entry)localObject2).getValue()).d - (bool ? 1000 : 0)) / 10;
/* 502:    */      
/* 503:503 */      this.v.elemPosA.x += paramSegment.getSegment().a.a;
/* 504:504 */      this.v.elemPosA.y += paramSegment.getSegment().a.b;
/* 505:505 */      this.v.elemPosA.z += paramSegment.getSegment().a.c;
/* 506:    */      
/* 512:512 */      this.v.nA.set(this.v.elemPosA);
/* 513:513 */      this.v.tmpTrans3.set(paramTransform3);
/* 514:514 */      this.v.tmpTrans3.basis.transform(this.v.nA);
/* 515:515 */      this.v.tmpTrans3.origin.add(this.v.nA);
/* 516:    */      
/* 518:518 */      this.v.simplexSolver.reset();
/* 519:519 */      this.v.box0.setMargin(0.01F + this.extraMargin);
/* 520:    */      
/* 521:521 */      localObject3 = this.v.box0;
/* 522:522 */      if ((k > 0) && (k < 3))
/* 523:    */      {
/* 524:524 */        localObject3 = dO.a(k, (byte)j, bool);
/* 525:    */      }
/* 526:    */      
/* 535:535 */      if (new ContinuousConvexCollision(this.v.shapeA, (ConvexShape)localObject3, this.v.simplexSolver, this.v.gjkEpaPenetrationDepthSolver).calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans3, this.v.tmpTrans3, paramCastResult, this.v.gjkVar))
/* 536:    */      {
/* 537:537 */        if (paramCastResult.normal.lengthSquared() > 1.0E-006F)
/* 538:    */        {
/* 540:540 */          if (paramCastResult.fraction < this.rayResult.closestHitFraction) {
/* 541:541 */            assert (paramSegment.getSegment() != null) : ("SEGMENT NULL OF DATA: " + paramSegment + " ");
/* 542:    */            
/* 543:543 */            this.rayResult.setSegment(paramSegment.getSegment());
/* 544:544 */            this.rayResult.cubePos.b(this.v.elemA);
/* 545:    */            
/* 547:547 */            paramTransform1.basis.transform(paramCastResult.normal);
/* 548:    */            
/* 550:550 */            paramCastResult.normal.normalize();
/* 551:551 */            paramCollisionObject = new CollisionWorld.LocalRayResult(paramCollisionObject, null, paramCastResult.normal, paramCastResult.fraction);
/* 552:    */            
/* 557:557 */            this.rayResult.addSingleResult(paramCollisionObject, true);
/* 558:    */            
/* 559:559 */            paramSegment.getSegment();Segment.d();
/* 560:    */            
/* 561:561 */            assert ((!this.rayResult.hasHit()) || (this.rayResult.getSegment() != null));
/* 562:562 */            return true;
/* 563:    */          }
/* 564:    */        }
/* 565:    */      }
/* 566:    */    }
/* 567:    */    
/* 580:580 */    return false;
/* 581:    */  }
/* 582:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexRayCubesCovexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */