/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*   6:    */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*   7:    */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*   8:    */import com.bulletphysics.collision.narrowphase.ConvexCast;
/*   9:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*  10:    */import com.bulletphysics.collision.narrowphase.GjkConvexCast;
/*  11:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*  12:    */import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*  13:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*  14:    */import com.bulletphysics.collision.shapes.BoxShape;
/*  15:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  16:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  17:    */import com.bulletphysics.collision.shapes.SphereShape;
/*  18:    */import com.bulletphysics.linearmath.AabbUtil2;
/*  19:    */import com.bulletphysics.linearmath.MatrixUtil;
/*  20:    */import com.bulletphysics.linearmath.Transform;
/*  21:    */import com.bulletphysics.util.ObjectPool;
/*  22:    */import dO;
/*  23:    */import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*  24:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*  25:    */import jL;
/*  26:    */import java.io.PrintStream;
/*  27:    */import java.util.ArrayList;
/*  28:    */import java.util.Iterator;
/*  29:    */import java.util.List;
/*  30:    */import java.util.Map.Entry;
/*  31:    */import javax.vecmath.Matrix3f;
/*  32:    */import javax.vecmath.Vector3f;
/*  33:    */import ld;
/*  34:    */import o;
/*  35:    */import org.lwjgl.input.Keyboard;
/*  36:    */import org.schema.common.FastMath;
/*  37:    */import org.schema.common.util.ByteUtil;
/*  38:    */import org.schema.game.common.controller.SegmentController;
/*  39:    */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*  40:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*  41:    */import org.schema.game.common.controller.elements.shield.ShieldUnit;
/*  42:    */import org.schema.game.common.data.element.ElementCollection;
/*  43:    */import org.schema.game.common.data.element.ElementInformation;
/*  44:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  45:    */import org.schema.game.common.data.physics.octree.ArrayOctree;
/*  46:    */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*  47:    */import org.schema.game.common.data.world.Segment;
/*  48:    */import org.schema.game.common.data.world.SegmentData;
/*  49:    */import q;
/*  50:    */import s;
/*  51:    */import xO;
/*  52:    */
/* 128:    */public class SubsimplexCubesCovexCast
/* 129:    */  extends ConvexCast
/* 130:    */{
/* 131:131 */  private static ThreadLocal threadLocal = new SubsimplexCubesCovexCast.1();
/* 132:    */  
/* 135:    */  public static String mode;
/* 136:    */  
/* 138:138 */  ObjectPool pool = ObjectPool.get(o.class);
/* 139:139 */  ObjectPool pool4 = ObjectPool.get(s.class);
/* 140:    */  
/* 143:143 */  ObjectPool aabbpool = ObjectPool.get(AABBb.class);
/* 144:    */  
/* 145:    */  private CubeConvexCastVariableSet v;
/* 146:    */  CubeRayCastResult rayResult;
/* 147:    */  CollisionWorld.ConvexResultCallback resultCallback;
/* 148:    */  private SubsimplexCubesCovexCast.OuterSegmentHandler outerSegmentHandler;
/* 149:    */  private long time;
/* 150:    */  
/* 151:    */  public SubsimplexCubesCovexCast(ConvexShape paramConvexShape, CollisionShape paramCollisionShape, CollisionObject paramCollisionObject, SimplexSolverInterface paramSimplexSolverInterface, CollisionWorld.ConvexResultCallback paramConvexResultCallback, CubeRayCastResult paramCubeRayCastResult)
/* 152:    */  {
/* 153:153 */    this.v = ((CubeConvexCastVariableSet)threadLocal.get());
/* 154:154 */    this.v.shapeA = paramConvexShape;
/* 155:155 */    this.v.cubesB = ((CubeShape)paramCollisionShape);
/* 156:156 */    this.v.cubesObject = paramCollisionObject;
/* 157:157 */    this.resultCallback = paramConvexResultCallback;
/* 158:158 */    this.v.simplexSolver = paramSimplexSolverInterface;
/* 159:159 */    this.rayResult = paramCubeRayCastResult;
/* 160:160 */    this.outerSegmentHandler = new SubsimplexCubesCovexCast.OuterSegmentHandler(this, null);
/* 161:    */  }
/* 162:    */  
/* 170:    */  public boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
/* 171:    */  {
/* 172:172 */    this.v.sorted.clear();
/* 173:    */    
/* 175:175 */    this.time = System.currentTimeMillis();
/* 176:    */    
/* 177:177 */    this.v.shapeA.getAabb(paramTransform1, this.v.convexFromAABBMin, this.v.convexFromAABBMax);
/* 178:178 */    this.v.shapeA.getAabb(paramTransform2, this.v.convexToAABBMin, this.v.convexToAABBMax);
/* 179:    */    
/* 182:182 */    combineAabb(this.v.convexFromAABBMin, this.v.convexFromAABBMax, this.v.convexToAABBMin, this.v.convexToAABBMax, this.v.castedAABBMin, this.v.castedAABBMax);
/* 183:    */    
/* 184:184 */    this.v.castedAABBMin.sub(new Vector3f(0.2F, 0.2F, 0.2F));
/* 185:185 */    this.v.castedAABBMax.add(new Vector3f(0.2F, 0.2F, 0.2F));
/* 186:    */    
/* 202:202 */    SubsimplexCubesCovexCast.OuterSegmentHandler.access$302(this.outerSegmentHandler, paramTransform1);
/* 203:203 */    SubsimplexCubesCovexCast.OuterSegmentHandler.access$402(this.outerSegmentHandler, paramCastResult);
/* 204:204 */    SubsimplexCubesCovexCast.OuterSegmentHandler.access$502(this.outerSegmentHandler, paramTransform3);
/* 205:205 */    SubsimplexCubesCovexCast.OuterSegmentHandler.access$602(this.outerSegmentHandler, paramTransform2);
/* 206:206 */    SubsimplexCubesCovexCast.OuterSegmentHandler.access$702(this.outerSegmentHandler, false);
/* 207:    */    
/* 212:212 */    this.v.cubesB.getAabb(paramTransform3, this.v.outer.a, this.v.outer.b);
/* 213:213 */    this.v.inner.a.set(this.v.castedAABBMin);
/* 214:214 */    this.v.inner.b.set(this.v.castedAABBMax);
/* 215:    */    
/* 217:217 */    if ((this.v.inner.a(this.v.outer, this.v.outBB) == null) || (!this.v.outBB.b()))
/* 218:    */    {
/* 219:219 */      return false;
/* 220:    */    }
/* 221:221 */    this.v.inv.set(paramTransform3);
/* 222:222 */    this.v.inv.inverse();
/* 223:223 */    AabbUtil2.transformAabb(new Vector3f(this.v.outBB.a), new Vector3f(this.v.outBB.b), 0.5F, this.v.inv, this.v.outBB.a, this.v.outBB.b);
/* 224:    */    
/* 228:228 */    this.v.minIntA.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/* 229:229 */    this.v.minIntA.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/* 230:230 */    this.v.minIntA.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/* 231:    */    
/* 232:232 */    this.v.maxIntA.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/* 233:233 */    this.v.maxIntA.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/* 234:234 */    this.v.maxIntA.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/* 235:    */    
/* 236:    */    long l;
/* 237:    */    
/* 238:238 */    if ((l = (this.v.maxIntA.a - this.v.minIntA.a) * (this.v.maxIntA.b - this.v.minIntA.b) * (this.v.maxIntA.c - this.v.minIntA.c) / 16) > 10000L) {
/* 239:239 */      System.err.println("[SubSimplexConvexCubes][WARNING] more then 10000 segments to test: " + l + " -> intersection [" + this.v.minIntA + ", " + this.v.maxIntA + "]");
/* 240:    */    }
/* 241:    */    
/* 242:242 */    this.v.absolute1.set(paramTransform3.basis);
/* 243:243 */    MatrixUtil.absolute(this.v.absolute1);
/* 244:244 */    this.v.cubesB.getSegmentBuffer().b(this.outerSegmentHandler, this.v.minIntA, this.v.maxIntA);
/* 245:    */    
/* 246:246 */    if (SubsimplexCubesCovexCast.OuterSegmentHandler.access$700(this.outerSegmentHandler))
/* 247:    */    {
/* 248:248 */      return true;
/* 249:    */    }
/* 250:    */    
/* 251:251 */    return false;
/* 252:    */  }
/* 253:    */  
/* 260:    */  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 261:    */  {
/* 262:262 */    paramDispatcherInfo = 1.0F;
/* 263:    */    
/* 264:264 */    this.v.tmp.sub(paramCollisionObject1.getInterpolationWorldTransform(this.v.tmpTrans1).origin, paramCollisionObject1.getWorldTransform(this.v.tmpTrans2).origin);
/* 265:265 */    paramManifoldResult = this.v.tmp.lengthSquared();
/* 266:    */    
/* 267:267 */    this.v.tmp.sub(paramCollisionObject2.getInterpolationWorldTransform(this.v.tmpTrans1).origin, paramCollisionObject2.getWorldTransform(this.v.tmpTrans2).origin);
/* 268:268 */    float f = this.v.tmp.lengthSquared();
/* 269:    */    
/* 270:270 */    if ((paramManifoldResult < paramCollisionObject1.getCcdSquareMotionThreshold()) && (f < paramCollisionObject2.getCcdSquareMotionThreshold()))
/* 271:    */    {
/* 272:272 */      return 1.0F;
/* 273:    */    }
/* 274:    */    
/* 275:275 */    if (this.v.disableCcd) {
/* 276:276 */      return 1.0F;
/* 277:    */    }
/* 278:    */    
/* 289:289 */    paramManifoldResult = (ConvexShape)paramCollisionObject1.getCollisionShape();
/* 290:    */    
/* 291:291 */    SphereShape localSphereShape = new SphereShape(paramCollisionObject2.getCcdSweptSphereRadius());
/* 292:292 */    ConvexCast.CastResult localCastResult = new ConvexCast.CastResult();
/* 293:293 */    Object localObject = new VoronoiSimplexSolverExt();
/* 294:    */    
/* 298:298 */    if (new GjkConvexCast(paramManifoldResult, localSphereShape, (SimplexSolverInterface)localObject).calcTimeOfImpact(paramCollisionObject1.getWorldTransform(this.v.tmpTrans1), paramCollisionObject1.getInterpolationWorldTransform(this.v.tmpTrans2), paramCollisionObject2.getWorldTransform(this.v.tmpTrans3), paramCollisionObject2.getInterpolationWorldTransform(this.v.tmpTrans4), localCastResult))
/* 299:    */    {
/* 302:302 */      if (paramCollisionObject1.getHitFraction() > localCastResult.fraction) {
/* 303:303 */        paramCollisionObject1.setHitFraction(localCastResult.fraction);
/* 304:    */      }
/* 305:    */      
/* 306:306 */      if (paramCollisionObject2.getHitFraction() > localCastResult.fraction) {
/* 307:307 */        paramCollisionObject2.setHitFraction(localCastResult.fraction);
/* 308:    */      }
/* 309:    */      
/* 310:310 */      if (1.0F > localCastResult.fraction) {
/* 311:311 */        paramDispatcherInfo = localCastResult.fraction;
/* 312:    */      }
/* 313:    */    }
/* 314:    */    
/* 318:318 */    paramManifoldResult = (ConvexShape)paramCollisionObject2.getCollisionShape();
/* 319:    */    
/* 320:320 */    localSphereShape = new SphereShape(paramCollisionObject1.getCcdSweptSphereRadius());
/* 321:321 */    localCastResult = new ConvexCast.CastResult();
/* 322:322 */    localObject = new VoronoiSimplexSolver();
/* 323:    */    
/* 327:327 */    if (new GjkConvexCast(localSphereShape, paramManifoldResult, (SimplexSolverInterface)localObject).calcTimeOfImpact(paramCollisionObject1.getWorldTransform(this.v.tmpTrans1), paramCollisionObject1.getInterpolationWorldTransform(this.v.tmpTrans2), paramCollisionObject2.getWorldTransform(this.v.tmpTrans3), paramCollisionObject2.getInterpolationWorldTransform(this.v.tmpTrans4), localCastResult))
/* 328:    */    {
/* 331:331 */      if (paramCollisionObject1.getHitFraction() > localCastResult.fraction) {
/* 332:332 */        paramCollisionObject1.setHitFraction(localCastResult.fraction);
/* 333:    */      }
/* 334:    */      
/* 335:335 */      if (paramCollisionObject2.getHitFraction() > localCastResult.fraction) {
/* 336:336 */        paramCollisionObject2.setHitFraction(localCastResult.fraction);
/* 337:    */      }
/* 338:    */      
/* 339:339 */      if (paramDispatcherInfo > localCastResult.fraction) {
/* 340:340 */        paramDispatcherInfo = localCastResult.fraction;
/* 341:    */      }
/* 342:    */    }
/* 343:    */    
/* 346:346 */    return paramDispatcherInfo;
/* 347:    */  }
/* 348:    */  
/* 350:    */  private boolean checkExplicitCollision(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentController paramSegmentController, ConvexCast.CastResult paramCastResult)
/* 351:    */  {
/* 352:352 */    if (((paramSegmentController instanceof ld)) && ((((ld)paramSegmentController).a() instanceof ShieldContainerInterface)))
/* 353:    */    {
/* 354:354 */      for (paramSegmentController = ((ShieldContainerInterface)((ld)paramSegmentController).a()).getShieldManager().getCollection().iterator(); paramSegmentController.hasNext();) { ShieldUnit localShieldUnit;
/* 355:355 */        if (((ShieldUnit)(localShieldUnit = (ShieldUnit)paramSegmentController.next())).getShields() <= 0)
/* 356:    */        {
/* 357:357 */          return false;
/* 358:    */        }
/* 359:359 */        this.v.tmpTrans.set(paramTransform3);
/* 360:360 */        localShieldUnit.getOpenGLCenter(this.v.fromHelp);
/* 361:    */        
/* 362:362 */        this.v.tmpTrans.basis.transform(this.v.fromHelp);
/* 363:    */        
/* 364:364 */        this.v.tmpTrans.origin.add(this.v.fromHelp);
/* 365:    */        
/* 366:366 */        this.v.simplexSolver.reset();
/* 367:    */        
/* 368:368 */        this.v.sphereShape.setRadius(localShieldUnit.getRadius());
/* 369:    */        
/* 370:370 */        SubsimplexConvexCast localSubsimplexConvexCast = new SubsimplexConvexCast(this.v.shapeA, this.v.sphereShape, this.v.simplexSolver);
/* 371:371 */        this.v.sphereShape.getAabb(this.v.tmpTrans, this.v.outMin, this.v.outMax);
/* 372:    */        
/* 377:377 */        if (localSubsimplexConvexCast.calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans, this.v.tmpTrans, paramCastResult))
/* 378:    */        {
/* 381:381 */          if (this.rayResult != null)
/* 382:    */          {
/* 383:383 */            this.rayResult.setUserData(localShieldUnit);
/* 384:    */          }
/* 385:385 */          return true;
/* 386:    */        }
/* 387:    */      }
/* 388:    */    }
/* 389:389 */    return false;
/* 390:    */  }
/* 391:    */  
/* 392:392 */  private void combineAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, Vector3f paramVector3f6) { paramVector3f5.x = Math.min(paramVector3f1.x, paramVector3f3.x);
/* 393:393 */    paramVector3f5.y = Math.min(paramVector3f1.y, paramVector3f3.y);
/* 394:394 */    paramVector3f5.z = Math.min(paramVector3f1.z, paramVector3f3.z);
/* 395:    */    
/* 396:396 */    paramVector3f6.x = Math.max(paramVector3f2.x, paramVector3f4.x);
/* 397:397 */    paramVector3f6.y = Math.max(paramVector3f2.y, paramVector3f4.y);
/* 398:398 */    paramVector3f6.z = Math.max(paramVector3f2.z, paramVector3f4.z);
/* 399:    */  }
/* 400:    */  
/* 413:    */  private boolean doNarrorTest(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentData paramSegmentData, ConvexCast.CastResult paramCastResult, o paramo1, o paramo2)
/* 414:    */  {
/* 415:415 */    this.v.posCachePointer = 0;
/* 416:416 */    for (paramTransform2 = paramo1.a; paramTransform2 < paramo2.a; paramTransform2 = (byte)(paramTransform2 + 1)) {
/* 417:417 */      for (paramCastResult = paramo1.b; paramCastResult < paramo2.b; paramCastResult = (byte)(paramCastResult + 1)) {
/* 418:418 */        for (int i = paramo1.c; i < paramo2.c; i = (byte)(i + 1)) {
/* 419:419 */          this.v.elemA.b((byte)(paramTransform2 + 8), (byte)(paramCastResult + 8), (byte)(i + 8));
/* 420:    */          
/* 421:421 */          int j = SegmentData.getInfoIndex(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c);
/* 422:    */          
/* 423:    */          short s;
/* 424:    */          
/* 425:    */          ElementInformation localElementInformation;
/* 426:    */          
/* 427:427 */          if (((s = paramSegmentData.getType(j)) != 0) && ((localElementInformation = ElementKeyMap.getInfo(s)).isPhysical()) && ((s != 122) || (ElementKeyMap.getInfo(s).isPhysical(paramSegmentData.isActive(j)))))
/* 428:    */          {
/* 429:429 */            s = paramSegmentData.getOrientation(j);
/* 430:430 */            boolean bool = paramSegmentData.isActive(j);
/* 431:    */            
/* 441:441 */            this.v.elemPosA.set(paramTransform2, paramCastResult, i);
/* 442:442 */            this.v.elemPosA.x += paramSegmentData.getSegment().a.a;
/* 443:443 */            this.v.elemPosA.y += paramSegmentData.getSegment().a.b;
/* 444:444 */            this.v.elemPosA.z += paramSegmentData.getSegment().a.c;
/* 445:    */            
/* 447:447 */            this.v.nA.set(this.v.elemPosA);
/* 448:448 */            this.v.tmpTrans.set(paramTransform3);
/* 449:449 */            this.v.tmpTrans.basis.transform(this.v.nA);
/* 450:450 */            this.v.tmpTrans.origin.add(this.v.nA);
/* 451:    */            
/* 452:452 */            this.v.box0.setMargin(0.3F);
/* 453:453 */            this.v.box0.getAabb(this.v.tmpTrans, this.v.outMin, this.v.outMax);
/* 454:    */            
/* 455:455 */            this.v.normal.set(0.0F, 0.0F, 0.0F);
/* 456:    */            
/* 468:468 */            if (AabbUtil2.testAabbAgainstAabb2(this.v.outMin, this.v.outMax, this.v.castedAABBMin, this.v.castedAABBMax)) { Object localObject;
/* 469:469 */              if ((this.resultCallback instanceof ClosestConvexResultCallbackExt))
/* 470:    */              {
/* 471:471 */                if ((localObject = (ClosestConvexResultCallbackExt)this.resultCallback).checkHasHitOnly) {
/* 472:472 */                  ((ClosestConvexResultCallbackExt)localObject).userData = paramSegmentData.getSegmentController();
/* 473:473 */                  this.resultCallback.closestHitFraction = 0.5F;
/* 474:474 */                  return false;
/* 475:    */                }
/* 476:    */              }
/* 477:    */              
/* 483:483 */              this.v.distTest.set(paramTransform1.origin);
/* 484:484 */              this.v.distTest.sub(this.v.tmpTrans.origin);
/* 485:    */              
/* 486:486 */              (
/* 487:487 */                localObject = (s)this.pool4.get()).a(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c, s * 10 + localElementInformation.getBlockStyle() + (bool ? 1000 : 0));
/* 488:    */              
/* 489:489 */              float f = this.v.distTest.length();
/* 490:490 */              s = 0;
/* 491:491 */              while ((this.v.sorted.containsKey(f)) && (s < 100)) {
/* 492:492 */                f += 0.1F;
/* 493:493 */                s++;
/* 494:    */              }
/* 495:495 */              if (s >= 100) {
/* 496:496 */                System.err.println("[SUBSIMPLEX][WARNING] more than 100 tries in sorted");
/* 497:    */              }
/* 498:498 */              this.v.sorted.put(f, localObject);
/* 499:499 */              this.v.posCachePointer += 1;
/* 500:    */            }
/* 501:    */          }
/* 502:    */        }
/* 503:    */      }
/* 504:    */    }
/* 505:    */    
/* 515:515 */    return true;
/* 516:    */  }
/* 517:    */  
/* 518:    */  public boolean drawDebug() {
/* 519:519 */    return (this.v.cubesB.getSegmentBuffer().a().isOnServer()) && (mode.equals("UP")) && (Keyboard.isKeyDown(57)) && (!Keyboard.isKeyDown(29));
/* 520:    */  }
/* 521:    */  
/* 526:    */  public boolean isOnServer()
/* 527:    */  {
/* 528:528 */    return this.v.cubesB.getSegmentBuffer().a().isOnServer();
/* 529:    */  }
/* 530:    */  
/* 535:    */  private boolean performCastTest(Segment paramSegment, ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, ConvexCast.CastResult paramCastResult)
/* 536:    */  {
/* 537:537 */    paramSegment = paramSegment.a();
/* 538:    */    
/* 539:539 */    if (!this.v.intersectionCallBack.initialized) {
/* 540:540 */      this.v.intersectionCallBack.createHitCache(512);
/* 541:    */    }
/* 542:542 */    this.v.intersectionCallBack.reset();
/* 543:    */    
/* 545:545 */    this.v.intersectionCallBack = paramSegment.getOctree().findIntersecting(paramSegment.getOctree().getSet(), this.v.intersectionCallBack, paramSegment.getSegment(), paramTransform3, this.v.absolute1, 0.0F, this.v.castedAABBMin, this.v.castedAABBMax, 1.0F);
/* 546:    */    
/* 547:547 */    this.v.sortedAABB.clear();
/* 548:548 */    this.v.sorted.clear();
/* 549:    */    
/* 550:550 */    paramConvexShape = 0;
/* 551:    */    
/* 552:552 */    boolean bool1 = true;
/* 553:553 */    Object localObject2; Object localObject1; Iterator localIterator1; if (this.v.intersectionCallBack.hitCount > 0) {
/* 554:554 */      for (int i = 0; i < this.v.intersectionCallBack.hitCount; i++) {
/* 555:555 */        this.v.intersectionCallBack.getHit(i, this.v.minOut, this.v.maxOut, this.v.startOut, this.v.endOut);
/* 556:    */        
/* 557:557 */        this.v.dist.set(this.v.maxOut);
/* 558:558 */        this.v.dist.sub(this.v.minOut);
/* 559:559 */        float f1 = this.v.dist.length();
/* 560:560 */        this.v.dist.normalize();
/* 561:561 */        this.v.dist.scale(f1 / 2.0F);
/* 562:562 */        this.v.minOut.add(this.v.dist);
/* 563:    */        
/* 565:565 */        this.v.dist.sub(this.v.minOut, paramTransform1.origin);
/* 566:566 */        float f2 = this.v.dist.lengthSquared() * 1000.0F;
/* 567:567 */        int k = 0;
/* 568:568 */        while ((this.v.sortedAABB.containsKey(f2)) && (!Float.isNaN(f2)) && (!Float.isInfinite(f2)) && (k < 1000)) {
/* 569:569 */          f2 += 0.1F;
/* 570:570 */          k++;
/* 571:    */        }
/* 572:572 */        if (k > 100) {
/* 573:573 */          System.err.println("[CubesConvex][WARNING] extended more then 100 AABBs length: " + this.v.sortedAABB.size() + ": " + paramConvexShape);
/* 574:    */        }
/* 575:575 */        if ((!Float.isNaN(f2)) && (!Float.isInfinite(f2))) {
/* 576:576 */          if (paramConvexShape > 1000)
/* 577:    */          {
/* 579:579 */            System.err.println("[CubesConvex][WARNING] testing more then 1000 AABBs: " + this.v.sortedAABB.size() + ": " + paramConvexShape);
/* 580:    */          }
/* 581:581 */          localObject2 = (AABBb)this.aabbpool.get();
/* 582:582 */          localObject1 = (o)this.pool.get();
/* 583:583 */          o localo = (o)this.pool.get();
/* 584:584 */          ((o)localObject1).b(this.v.startOut);
/* 585:585 */          localo.b(this.v.endOut);
/* 586:586 */          ((AABBb)localObject2).min = ((o)localObject1);
/* 587:587 */          ((AABBb)localObject2).max = localo;
/* 588:588 */          this.v.sortedAABB.put(f2, localObject2);
/* 589:589 */          paramConvexShape++;
/* 590:    */        } }
/* 591:591 */      if (this.v.sortedAABB.size() > 1000) {
/* 592:592 */        System.err.println("[CubesConvex][WARNING] testing more then 1000 AABBs: " + this.v.sortedAABB.size());
/* 593:    */      }
/* 594:594 */      for (localIterator1 = this.v.sortedAABB.entrySet().iterator(); localIterator1.hasNext();) { localObject1 = (Map.Entry)localIterator1.next();
/* 595:595 */        if (!bool1) break;
/* 596:596 */        bool1 = doNarrorTest(paramTransform1, paramTransform2, paramTransform3, paramSegment, paramCastResult, ((AABBb)((Map.Entry)localObject1).getValue()).min, ((AABBb)((Map.Entry)localObject1).getValue()).max);
/* 597:    */      }
/* 598:    */    }
/* 599:    */    
/* 609:609 */    if (!bool1) {
/* 610:610 */      return true;
/* 611:    */    }
/* 612:612 */    if (this.v.sorted.size() > 1000) {
/* 613:613 */      System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "][CubeConvex] DOING " + this.v.sorted.size() + " box tests");
/* 614:    */    }
/* 615:615 */    boolean bool2 = false;
/* 616:    */    
/* 617:617 */    for (Map.Entry localEntry : this.v.sorted.entrySet())
/* 618:    */    {
/* 621:621 */      this.v.elemA.b((byte)((s)localEntry.getValue()).a, (byte)((s)localEntry.getValue()).b, (byte)((s)localEntry.getValue()).c);
/* 622:    */      
/* 624:624 */      (
/* 625:625 */        localObject2 = new ArrayList()).add(this.v.elemA);
/* 626:    */      
/* 639:639 */      for (int m = 0; m < ((ArrayList)localObject2).size(); 
/* 640:    */          
/* 674:674 */          m++)
/* 675:    */      {
/* 682:682 */        paramConvexShape = ((s)localEntry.getValue()).d % 10;
/* 683:683 */        bool1 = ((s)localEntry.getValue()).d >= 1000;
/* 684:684 */        int j = (((s)localEntry.getValue()).d - (bool1 ? 1000 : 0)) / 10;
/* 685:    */        
/* 687:687 */        if (m == 0)
/* 688:    */        {
/* 689:689 */          this.v.elemPosA.set(this.v.elemA.a - 8, this.v.elemA.b - 8, this.v.elemA.c - 8);
/* 692:    */        }
/* 693:    */        else
/* 694:    */        {
/* 696:696 */          (localObject3 = (o)((ArrayList)localObject2).get(m)).c(this.v.elemA);
/* 697:697 */          this.v.elemPosA.set(this.v.elemA.a - 8 + 0.5F * ((o)localObject3).a, this.v.elemA.b - 8 + 0.5F * ((o)localObject3).b, this.v.elemA.c - 8 + 0.5F * ((o)localObject3).c);
/* 698:    */        }
/* 699:    */        
/* 702:702 */        this.v.elemPosA.x += paramSegment.getSegment().a.a;
/* 703:703 */        this.v.elemPosA.y += paramSegment.getSegment().a.b;
/* 704:704 */        this.v.elemPosA.z += paramSegment.getSegment().a.c;
/* 705:    */        
/* 706:706 */        this.v.nA.set(this.v.elemPosA);
/* 707:707 */        this.v.tmpTrans.set(paramTransform3);
/* 708:708 */        this.v.tmpTrans.basis.transform(this.v.nA);
/* 709:709 */        this.v.tmpTrans.origin.add(this.v.nA);
/* 710:    */        
/* 712:712 */        this.v.simplexSolver.reset();
/* 713:713 */        this.v.box0.setMargin(0.005F);
/* 714:    */        
/* 724:724 */        Object localObject3 = this.v.box0;
/* 725:    */        
/* 728:728 */        if ((paramConvexShape > 0) && (paramConvexShape < 3))
/* 729:    */        {
/* 730:730 */          localObject3 = dO.a(paramConvexShape, (byte)j, bool1);
/* 731:    */        }
/* 732:    */        
/* 733:733 */        paramConvexShape = new ContinuousConvexCollision(this.v.shapeA, (ConvexShape)localObject3, this.v.simplexSolver, this.v.gjkEpaPenetrationDepthSolver);
/* 734:    */        
/* 735:    */        ConvexCast.CastResult localCastResult;
/* 736:736 */        (localCastResult = new ConvexCast.CastResult()).allowedPenetration = paramCastResult.allowedPenetration;
/* 737:737 */        localCastResult.fraction = 1.0F;
/* 738:    */        
/* 739:739 */        if (paramConvexShape.calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans, this.v.tmpTrans, localCastResult, this.v.gjkVar))
/* 740:    */        {
/* 742:742 */          if (this.resultCallback == null)
/* 743:    */          {
/* 744:744 */            if (this.rayResult != null) {
/* 745:745 */              this.rayResult.setSegment(paramSegment.getSegment());
/* 746:746 */              this.rayResult.cubePos.b(this.v.elemA);
/* 747:    */            }
/* 748:748 */            return true;
/* 749:    */          }
/* 750:    */          
/* 752:752 */          if ((localCastResult.normal.lengthSquared() > 1.0E-004F) && 
/* 753:753 */            (localCastResult.fraction <= this.resultCallback.closestHitFraction))
/* 754:    */          {
/* 769:769 */            paramCastResult.normal.normalize();
/* 770:770 */            paramConvexShape = new CollisionWorld.LocalConvexResult(this.v.cubesObject, null, localCastResult.normal, localCastResult.hitPoint, localCastResult.fraction);
/* 771:    */            
/* 772:772 */            if ((this.resultCallback instanceof KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback))
/* 773:    */            {
/* 774:774 */              ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)this.resultCallback).addSingleResult(paramConvexShape, true, paramSegment.getSegment(), this.v.elemA);
/* 775:    */            } else {
/* 776:776 */              this.resultCallback.addSingleResult(paramConvexShape, true);
/* 777:    */            }
/* 778:778 */            if ((this.resultCallback instanceof ClosestConvexResultCallbackExt)) {
/* 779:779 */              ((ClosestConvexResultCallbackExt)this.resultCallback).userData = paramSegment.getSegmentController();
/* 780:    */            }
/* 781:781 */            paramSegment.getSegment();Segment.d();
/* 782:782 */            bool2 = true;
/* 783:    */          }
/* 784:    */        }
/* 785:    */      }
/* 786:    */    }
/* 787:    */    
/* 793:793 */    return bool2;
/* 794:    */  }
/* 795:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexCubesCovexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */