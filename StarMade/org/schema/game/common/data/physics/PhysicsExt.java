/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.BulletGlobals;
/*   4:    */import com.bulletphysics.collision.broadphase.DbvtBroadphase;
/*   5:    */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*   6:    */import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*   7:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   8:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   9:    */import com.bulletphysics.collision.dispatch.GhostPairCallback;
/*  10:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  11:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*  12:    */import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*  13:    */import com.bulletphysics.dynamics.DynamicsWorld;
/*  14:    */import com.bulletphysics.dynamics.RigidBody;
/*  15:    */import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
/*  16:    */import com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm;
/*  17:    */import com.bulletphysics.linearmath.Transform;
/*  18:    */import com.bulletphysics.util.ObjectArrayList;
/*  19:    */import d;
/*  20:    */import jL;
/*  21:    */import java.io.PrintStream;
/*  22:    */import javax.vecmath.Matrix3f;
/*  23:    */import javax.vecmath.Quat4f;
/*  24:    */import javax.vecmath.Vector3f;
/*  25:    */import jv;
/*  26:    */import le;
/*  27:    */import org.schema.game.common.controller.SegmentController;
/*  28:    */import org.schema.game.common.data.element.ElementDocking;
/*  29:    */import org.schema.game.common.data.world.Segment;
/*  30:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  31:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  32:    */import zM;
/*  33:    */import zP;
/*  34:    */import zQ;
/*  35:    */import zW;
/*  36:    */
/*  37:    */public class PhysicsExt extends zQ
/*  38:    */{
/*  39:    */  private AxisSweep3Ext axisSweep3Ext;
/*  40:    */  private DbvtBroadphase dbvtBroadphase;
/*  41:    */  
/*  42:    */  public PhysicsExt(zW paramzW)
/*  43:    */  {
/*  44: 44 */    super(paramzW);
/*  45:    */  }
/*  46:    */  
/*  62:    */  public RigidBody getBodyFromShape(CollisionShape paramCollisionShape, float paramFloat, Transform paramTransform)
/*  63:    */  {
/*  64: 64 */    int i = paramFloat != 0.0F ? 1 : 0;
/*  65:    */    
/*  66: 66 */    assert ((!(paramCollisionShape instanceof CompoundShape)) || (((CompoundShape)paramCollisionShape).getNumChildShapes() != 0)) : "tried to add empty compound shape";
/*  67:    */    
/*  68: 68 */    Vector3f localVector3f = new Vector3f();
/*  69: 69 */    if (i != 0)
/*  70:    */    {
/*  71: 71 */      paramCollisionShape.calculateLocalInertia(paramFloat, localVector3f);
/*  72:    */    }
/*  73:    */    
/*  74: 74 */    paramCollisionShape.setMargin(0.13F);
/*  75:    */    
/*  77: 77 */    paramTransform = new zM(paramTransform);
/*  78:    */    
/*  86: 86 */    paramTransform = new RigidBodyConstructionInfo(paramFloat, paramTransform, paramCollisionShape, localVector3f);
/*  87:    */    
/*  95: 95 */    assert (!(paramCollisionShape instanceof CubeShape));
/*  96:    */    
/*  97: 97 */    if ((paramCollisionShape instanceof CubesCompoundShape)) {
/*  98: 98 */      paramCollisionShape = new RigidBodyExt(((CubesCompoundShape)paramCollisionShape).getSegmentController(), paramTransform);
/*  99:    */    } else {
/* 100:100 */      paramCollisionShape = new RigidBody(paramTransform);
/* 101:    */    }
/* 102:    */    
/* 110:110 */    if (paramFloat > 0.0F) {
/* 111:111 */      paramCollisionShape.setRestitution(0.1F);
/* 112:    */    }
/* 113:    */    else {
/* 114:114 */      paramCollisionShape.setRestitution(0.0F);
/* 115:    */    }
/* 116:    */    
/* 123:123 */    paramCollisionShape.setFriction(0.7F);
/* 124:    */    
/* 131:131 */    if (paramFloat > 0.0F) {
/* 132:132 */      paramCollisionShape.setDamping(getState().b(), getState().c());
/* 133:    */    } else {
/* 134:134 */      paramCollisionShape.setDamping(0.0F, 0.0F);
/* 135:    */    }
/* 136:    */    
/* 137:137 */    paramCollisionShape.setMassProps(paramFloat, localVector3f);
/* 138:138 */    if (paramFloat > 0.0F) {
/* 139:139 */      paramCollisionShape.updateInertiaTensor();
/* 140:    */    }
/* 141:    */    
/* 142:142 */    if (i == 0) {
/* 143:143 */      paramCollisionShape.setCollisionFlags(paramCollisionShape.getCollisionFlags() | 0x2);
/* 144:    */    }
/* 145:    */    
/* 180:180 */    return paramCollisionShape;
/* 181:    */  }
/* 182:    */  
/* 211:    */  public void initPhysics()
/* 212:    */  {
/* 213:213 */    setCollisionConfiguration(new CubeCollissionConfiguration());
/* 214:    */    
/* 217:217 */    setDispatcher(new CollisionDispatcherExt(getCollisionConfiguration()));
/* 218:    */    
/* 224:224 */    new Vector3f(-2000.0F, -2000.0F, -2000.0F);
/* 225:225 */    new Vector3f(2000.0F, 2000.0F, 2000.0F);
/* 226:    */    
/* 243:243 */    this.dbvtBroadphase = new DbvtBroadphaseExt(new HashedOverlappingPairCacheExt());
/* 244:244 */    setOverlappingPairCache(this.dbvtBroadphase);
/* 245:    */    
/* 251:251 */    SequentialImpulseConstraintSolverExt localSequentialImpulseConstraintSolverExt = new SequentialImpulseConstraintSolverExt();
/* 252:252 */    setSolver(localSequentialImpulseConstraintSolverExt);
/* 253:    */    
/* 257:257 */    setDynamicsWorld(new ModifiedDynamicsWorld(getDispatcher(), getOverlappingPairCache(), getSolver(), getCollisionConfiguration(), getState()));
/* 258:    */    
/* 266:266 */    GImpactCollisionAlgorithm.registerAlgorithm((CollisionDispatcher)this.dynamicsWorld.getDispatcher());
/* 267:    */    
/* 268:268 */    getDynamicsWorld().setGravity(new Vector3f(0.0F, -0.0F, 0.0F));
/* 269:    */    
/* 272:272 */    getDynamicsWorld().getPairCache().setInternalGhostPairCallback(new GhostPairCallback());
/* 273:    */    
/* 275:275 */    BulletGlobals.setContactBreakingThreshold(0.1F);
/* 276:276 */    getDynamicsWorld().getSolverInfo().linearSlop = 1.0E-006F;
/* 277:    */  }
/* 278:    */  
/* 282:    */  public void orientate(zP paramzP, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, float paramFloat)
/* 283:    */  {
/* 284:284 */    if (((paramzP instanceof SegmentController)) && (((SegmentController)paramzP).getDockingController().b()))
/* 285:    */    {
/* 286:286 */      paramFloat = ((CubeShape)paramzP.getPhysicsDataContainer().getShapeChild().childShape).getSegmentBuffer().a();
/* 287:    */      
/* 289:289 */      paramzP = paramFloat.getDockingController().a().to.a().a();
/* 290:    */      
/* 291:291 */      (localObject = (zV)threadLocal.get()).h.set(paramVector3f3);
/* 292:292 */      ((zV)localObject).h.normalize();
/* 293:293 */      ((zV)localObject).g.set(paramVector3f1);
/* 294:294 */      ((zV)localObject).g.normalize();
/* 295:295 */      ((zV)localObject).i.set(paramVector3f2);
/* 296:296 */      ((zV)localObject).i.normalize();
/* 297:    */      
/* 298:298 */      paramzP.getWorldTransformInverse().basis.transform(((zV)localObject).h);
/* 299:299 */      paramzP.getWorldTransformInverse().basis.transform(((zV)localObject).g);
/* 300:300 */      paramzP.getWorldTransformInverse().basis.transform(((zV)localObject).i);
/* 301:    */      
/* 303:303 */      paramzP = new Transform();
/* 304:304 */      GlUtil.a(((zV)localObject).g, paramzP);
/* 305:305 */      GlUtil.d(((zV)localObject).i, paramzP);
/* 306:306 */      GlUtil.c(((zV)localObject).h, paramzP);
/* 307:    */      
/* 308:308 */      paramVector3f3 = new Quat4f();
/* 309:309 */      d.a(paramzP.basis, paramVector3f3);
/* 310:310 */      paramFloat.getDockingController().a.set(paramVector3f3); return;
/* 311:    */    }
/* 312:    */    
/* 314:    */    RigidBody localRigidBody;
/* 315:    */    
/* 316:316 */    if (((localRigidBody = (RigidBody)paramzP.getPhysicsDataContainer().getObject()) == null) || (localRigidBody.getCollisionFlags() == 1) || (paramzP.getMass() <= 0.0F)) {
/* 317:317 */      return;
/* 318:    */    }
/* 319:319 */    zV localzV = (zV)threadLocal.get();
/* 320:    */    
/* 326:326 */    paramzP = paramzP.getPhysicsDataContainer().getCurrentPhysicsTransform();
/* 327:    */    
/* 329:329 */    GlUtil.c(localzV.a, paramzP);
/* 330:330 */    GlUtil.f(localzV.b, paramzP);
/* 331:331 */    GlUtil.e(localzV.j, paramzP);
/* 332:    */    
/* 335:335 */    localzV.h.set(paramVector3f3);
/* 336:336 */    localzV.h.normalize();
/* 337:337 */    localzV.g.set(paramVector3f1);
/* 338:338 */    localzV.g.normalize();
/* 339:339 */    localzV.i.set(paramVector3f2);
/* 340:340 */    localzV.i.normalize();
/* 341:    */    
/* 342:342 */    Object localObject = new Vector3f();
/* 343:343 */    paramzP = new Vector3f();
/* 344:344 */    paramVector3f3 = new Vector3f();
/* 345:345 */    ((Vector3f)localObject).sub(localzV.a, localzV.g);
/* 346:346 */    paramzP.sub(localzV.b, localzV.i);
/* 347:347 */    paramVector3f3.sub(localzV.j, localzV.h);
/* 348:    */    
/* 349:349 */    localzV.c.cross(localzV.a, localzV.g);
/* 350:350 */    localzV.c.normalize();
/* 351:351 */    localzV.d.cross(localzV.b, localzV.i);
/* 352:352 */    localzV.d.normalize();
/* 353:353 */    localzV.e.cross(localzV.j, localzV.h);
/* 354:354 */    localzV.e.normalize();
/* 355:    */    
/* 356:356 */    if ((((Vector3f)localObject).length() < 1.192093E-007F) && (paramzP.length() < 1.192093E-007F) && (paramVector3f3.length() < 1.192093E-007F)) {
/* 357:357 */      return;
/* 358:    */    }
/* 359:    */    
/* 360:360 */    localzV.c.scale(((Vector3f)localObject).length());
/* 361:361 */    localzV.d.scale(paramzP.length());
/* 362:362 */    localzV.e.scale(paramVector3f3.length());
/* 363:    */    
/* 365:365 */    localzV.f.add(localzV.c, localzV.d);
/* 366:366 */    localzV.f.add(localzV.e);
/* 367:    */    
/* 369:369 */    if ((localzV.f.length() > 5.0E-005F) && (!localRigidBody.isActive()))
/* 370:    */    {
/* 371:371 */      localRigidBody.activate();
/* 372:    */    }
/* 373:373 */    if ((paramVector3f1 != null) && (paramVector3f1.length() > 0.0F) && (paramVector3f2 != null) && (paramVector3f2.length() > 0.0F))
/* 374:    */    {
/* 378:378 */      paramzP = new Vector3f();
/* 379:379 */      localRigidBody.getAngularVelocity(paramzP);
/* 380:    */      
/* 383:383 */      localzV.f.scale(paramFloat);
/* 384:    */      
/* 386:386 */      if ((!Float.isNaN(localzV.f.x)) && (!Float.isNaN(localzV.f.y)) && (!Float.isNaN(localzV.f.z)))
/* 387:    */      {
/* 388:388 */        localRigidBody.setAngularVelocity(localzV.f);
/* 389:    */      }
/* 390:    */      
/* 393:393 */      localzV.k.set(localzV.f);
/* 394:    */    }
/* 395:    */  }
/* 396:    */  
/* 405:    */  public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean1, SegmentController paramSegmentController1, SegmentController paramSegmentController2, boolean paramBoolean2, Segment paramSegment, boolean paramBoolean3)
/* 406:    */  {
/* 407:407 */    (paramSegmentController1 = new CubeRayCastResult(paramVector3f1, paramVector3f2, paramSegmentController1, paramSegmentController2)).setRespectShields(paramBoolean2);
/* 408:408 */    paramSegmentController1.setLastHitSegment(paramSegment);
/* 409:409 */    paramSegmentController1.setIgnoereNotPhysical(paramBoolean3);
/* 410:410 */    assert (!paramSegmentController1.hasHit());
/* 411:411 */    ((ModifiedDynamicsWorld)this.dynamicsWorld).rayTest(paramVector3f1, paramVector3f2, paramSegmentController1);
/* 412:412 */    if (paramSegmentController1.collisionObject != null)
/* 413:    */    {
/* 414:414 */      if (((paramVector3f1 = RigidBody.upcast(paramSegmentController1.collisionObject)) != null) && 
/* 415:415 */        (paramBoolean1) && (!paramVector3f1.isStaticObject()) && (!paramVector3f1.isKinematicObject())) {
/* 416:416 */        return null;
/* 417:    */      }
/* 418:    */    }
/* 419:    */    
/* 421:421 */    return paramSegmentController1;
/* 422:    */  }
/* 423:    */  
/* 424:    */  public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, boolean paramBoolean)
/* 425:    */  {
/* 426:426 */    if (SubsimplexRayCubesCovexCast.debug) {
/* 427:427 */      System.err.println("TESTING RAY CAST!!!");
/* 428:    */    }
/* 429:429 */    ((ModifiedDynamicsWorld)this.dynamicsWorld).rayTest(paramVector3f1, paramVector3f2, paramCubeRayCastResult);
/* 430:430 */    if (paramCubeRayCastResult.collisionObject != null)
/* 431:    */    {
/* 432:432 */      if (((paramVector3f1 = RigidBody.upcast(paramCubeRayCastResult.collisionObject)) != null) && 
/* 433:433 */        (paramBoolean) && (!paramVector3f1.isStaticObject()) && (!paramVector3f1.isKinematicObject()))
/* 434:    */      {
/* 435:435 */        return null;
/* 436:    */      }
/* 437:    */    }
/* 438:    */    
/* 440:440 */    return paramCubeRayCastResult;
/* 441:    */  }
/* 442:    */  
/* 443:443 */  public void cleanUp() { super.cleanUp();
/* 444:444 */    if (this.axisSweep3Ext != null) {
/* 445:445 */      this.axisSweep3Ext.cleanUp();
/* 446:    */    }
/* 447:447 */    this.dynamicsWorld.setBroadphase(null);
/* 448:448 */    setOverlappingPairCache(null);
/* 449:449 */    setState(null);
/* 450:    */  }
/* 451:    */  
/* 452:452 */  public void softClean() { int i = getDynamicsWorld().getCollisionObjectArray().size();
/* 453:453 */    for (int j = 0; j < i; j++) {
/* 454:454 */      CollisionObject localCollisionObject = (CollisionObject)getDynamicsWorld().getCollisionObjectArray().get(j);
/* 455:455 */      System.err.println("WARNING: REMOVING EXCESS OBJECT FROM PHYSICS " + getState() + ": " + localCollisionObject);
/* 456:456 */      if ((localCollisionObject instanceof RigidBody)) {
/* 457:457 */        getDynamicsWorld().removeRigidBody((RigidBody)localCollisionObject);
/* 458:    */      } else {
/* 459:459 */        getDynamicsWorld().removeCollisionObject(localCollisionObject);
/* 460:    */      }
/* 461:    */    }
/* 462:462 */    if (this.dbvtBroadphase != null)
/* 463:    */    {
/* 464:464 */      this.dbvtBroadphase = new DbvtBroadphaseExt(new HashedOverlappingPairCacheExt());
/* 465:    */    }
/* 466:466 */    if (this.axisSweep3Ext != null) {
/* 467:467 */      this.axisSweep3Ext.cleanUpReferences();
/* 468:    */    }
/* 469:    */  }
/* 470:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PhysicsExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */