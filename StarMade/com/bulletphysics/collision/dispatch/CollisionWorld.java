/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.BulletGlobals;
/*   5:    */import com.bulletphysics.BulletStats;
/*   6:    */import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*   7:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   8:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   9:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*  10:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*  11:    */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*  12:    */import com.bulletphysics.collision.narrowphase.ConvexCast;
/*  13:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*  14:    */import com.bulletphysics.collision.narrowphase.GjkConvexCast;
/*  15:    */import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*  16:    */import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*  17:    */import com.bulletphysics.collision.narrowphase.TriangleConvexcastCallback;
/*  18:    */import com.bulletphysics.collision.narrowphase.TriangleRaycastCallback;
/*  19:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*  20:    */import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
/*  21:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  22:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*  23:    */import com.bulletphysics.collision.shapes.ConcaveShape;
/*  24:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  25:    */import com.bulletphysics.collision.shapes.SphereShape;
/*  26:    */import com.bulletphysics.collision.shapes.TriangleMeshShape;
/*  27:    */import com.bulletphysics.linearmath.AabbUtil2;
/*  28:    */import com.bulletphysics.linearmath.IDebugDraw;
/*  29:    */import com.bulletphysics.linearmath.Transform;
/*  30:    */import com.bulletphysics.linearmath.TransformUtil;
/*  31:    */import com.bulletphysics.linearmath.VectorUtil;
/*  32:    */import com.bulletphysics.util.ObjectArrayList;
/*  33:    */import java.io.PrintStream;
/*  34:    */import javax.vecmath.Matrix3f;
/*  35:    */import javax.vecmath.Vector3f;
/*  36:    */
/*  68:    */public class CollisionWorld
/*  69:    */{
/*  70: 70 */  protected ObjectArrayList<CollisionObject> collisionObjects = new ObjectArrayList();
/*  71:    */  protected Dispatcher dispatcher1;
/*  72: 72 */  protected DispatcherInfo dispatchInfo = new DispatcherInfo();
/*  73:    */  
/*  74:    */  protected BroadphaseInterface broadphasePairCache;
/*  75:    */  
/*  76:    */  protected IDebugDraw debugDrawer;
/*  77:    */  
/*  79:    */  public CollisionWorld(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration)
/*  80:    */  {
/*  81: 81 */    this.dispatcher1 = dispatcher;
/*  82: 82 */    this.broadphasePairCache = broadphasePairCache;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void destroy()
/*  86:    */  {
/*  87: 87 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  88: 88 */      CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*  89:    */      
/*  90: 90 */      BroadphaseProxy bp = collisionObject.getBroadphaseHandle();
/*  91: 91 */      if (bp != null)
/*  92:    */      {
/*  95: 95 */        getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(bp, this.dispatcher1);
/*  96: 96 */        getBroadphase().destroyProxy(bp, this.dispatcher1);
/*  97:    */      }
/*  98:    */    }
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void addCollisionObject(CollisionObject collisionObject) {
/* 102:102 */    addCollisionObject(collisionObject, (short)1, (short)-1);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void addCollisionObject(CollisionObject arg1, short arg2, short arg3)
/* 106:    */  {
/* 107:107 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();assert (!this.collisionObjects.contains(collisionObject));
/* 108:    */      
/* 109:109 */      this.collisionObjects.add(collisionObject);
/* 110:    */      
/* 113:113 */      Transform trans = collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 114:    */      
/* 115:115 */      Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
/* 116:116 */      Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/* 117:117 */      collisionObject.getCollisionShape().getAabb(trans, minAabb, maxAabb);
/* 118:    */      
/* 119:119 */      BroadphaseNativeType type = collisionObject.getCollisionShape().getShapeType();
/* 120:120 */      collisionObject.setBroadphaseHandle(getBroadphase().createProxy(minAabb, maxAabb, type, collisionObject, collisionFilterGroup, collisionFilterMask, this.dispatcher1, null));
/* 123:    */    }
/* 124:    */    finally
/* 125:    */    {
/* 128:128 */      .Stack tmp132_130 = localStack;tmp132_130.pop$com$bulletphysics$linearmath$Transform();tmp132_130.pop$javax$vecmath$Vector3f();
/* 129:    */    } }
/* 130:    */  
/* 131:131 */  public void performDiscreteCollisionDetection() { BulletStats.pushProfile("performDiscreteCollisionDetection");
/* 132:    */    
/* 133:    */    try
/* 134:    */    {
/* 135:135 */      updateAabbs();
/* 136:    */      
/* 137:137 */      BulletStats.pushProfile("calculateOverlappingPairs");
/* 138:    */      try {
/* 139:139 */        this.broadphasePairCache.calculateOverlappingPairs(this.dispatcher1);
/* 140:    */      }
/* 141:    */      finally {}
/* 142:    */      
/* 145:145 */      Dispatcher dispatcher = getDispatcher();
/* 146:    */      
/* 147:147 */      BulletStats.pushProfile("dispatchAllCollisionPairs");
/* 148:    */      try {
/* 149:149 */        if (dispatcher != null) {
/* 150:150 */          dispatcher.dispatchAllCollisionPairs(this.broadphasePairCache.getOverlappingPairCache(), this.dispatchInfo, this.dispatcher1);
/* 151:    */        }
/* 152:    */        
/* 154:    */      }
/* 155:    */      finally {}
/* 156:    */    }
/* 157:    */    finally
/* 158:    */    {
/* 159:159 */      BulletStats.popProfile();
/* 160:    */    }
/* 161:    */  }
/* 162:    */  
/* 165:    */  public void removeCollisionObject(CollisionObject collisionObject)
/* 166:    */  {
/* 167:167 */    BroadphaseProxy bp = collisionObject.getBroadphaseHandle();
/* 168:168 */    if (bp != null)
/* 169:    */    {
/* 172:172 */      getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(bp, this.dispatcher1);
/* 173:173 */      getBroadphase().destroyProxy(bp, this.dispatcher1);
/* 174:174 */      collisionObject.setBroadphaseHandle(null);
/* 175:    */    }
/* 176:    */    
/* 179:179 */    this.collisionObjects.remove(collisionObject);
/* 180:    */  }
/* 181:    */  
/* 182:    */  public void setBroadphase(BroadphaseInterface pairCache) {
/* 183:183 */    this.broadphasePairCache = pairCache;
/* 184:    */  }
/* 185:    */  
/* 186:    */  public BroadphaseInterface getBroadphase() {
/* 187:187 */    return this.broadphasePairCache;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public OverlappingPairCache getPairCache() {
/* 191:191 */    return this.broadphasePairCache.getOverlappingPairCache();
/* 192:    */  }
/* 193:    */  
/* 194:    */  public Dispatcher getDispatcher() {
/* 195:195 */    return this.dispatcher1;
/* 196:    */  }
/* 197:    */  
/* 198:    */  public DispatcherInfo getDispatchInfo() {
/* 199:199 */    return this.dispatchInfo;
/* 200:    */  }
/* 201:    */  
/* 202:202 */  private static boolean updateAabbs_reportMe = true;
/* 203:    */  
/* 204:    */  public void updateSingleAabb(CollisionObject arg1)
/* 205:    */  {
/* 206:206 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/* 207:207 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 208:208 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 209:    */      
/* 210:210 */      colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
/* 211:    */      
/* 212:212 */      Vector3f contactThreshold = localStack.get$javax$vecmath$Vector3f();
/* 213:213 */      contactThreshold.set(BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold());
/* 214:214 */      minAabb.sub(contactThreshold);
/* 215:215 */      maxAabb.add(contactThreshold);
/* 216:    */      
/* 217:217 */      BroadphaseInterface bp = this.broadphasePairCache;
/* 218:    */      
/* 220:220 */      tmp.sub(maxAabb, minAabb);
/* 221:221 */      if ((colObj.isStaticObject()) || (tmp.lengthSquared() < 1.0E+012F)) {
/* 222:222 */        bp.setAabb(colObj.getBroadphaseHandle(), minAabb, maxAabb, this.dispatcher1);
/* 224:    */      }
/* 225:    */      else
/* 226:    */      {
/* 227:227 */        colObj.setActivationState(5);
/* 228:    */        
/* 229:229 */        if ((updateAabbs_reportMe) && (this.debugDrawer != null)) {
/* 230:230 */          updateAabbs_reportMe = false;
/* 231:231 */          this.debugDrawer.reportErrorWarning("Overflow in AABB, object removed from simulation");
/* 232:232 */          this.debugDrawer.reportErrorWarning("If you can reproduce this, please email bugs@continuousphysics.com\n");
/* 233:233 */          this.debugDrawer.reportErrorWarning("Please include above information, your Platform, version of OS.\n");
/* 234:234 */          this.debugDrawer.reportErrorWarning("Thanks.\n");
/* 235:    */        }
/* 236:    */      }
/* 237:237 */    } finally { .Stack tmp212_210 = localStack;tmp212_210.pop$com$bulletphysics$linearmath$Transform();tmp212_210.pop$javax$vecmath$Vector3f();
/* 238:    */    } }
/* 239:    */  
/* 240:240 */  public void updateAabbs() { BulletStats.pushProfile("updateAabbs");
/* 241:    */    try {
/* 242:242 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 243:243 */        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 244:    */        
/* 246:246 */        if (colObj.isActive()) {
/* 247:247 */          updateSingleAabb(colObj);
/* 248:    */        }
/* 249:    */      }
/* 250:    */    }
/* 251:    */    finally {
/* 252:252 */      BulletStats.popProfile();
/* 253:    */    }
/* 254:    */  }
/* 255:    */  
/* 256:    */  public IDebugDraw getDebugDrawer() {
/* 257:257 */    return this.debugDrawer;
/* 258:    */  }
/* 259:    */  
/* 260:    */  public void setDebugDrawer(IDebugDraw debugDrawer) {
/* 261:261 */    this.debugDrawer = debugDrawer;
/* 262:    */  }
/* 263:    */  
/* 264:    */  public int getNumCollisionObjects() {
/* 265:265 */    return this.collisionObjects.size();
/* 266:    */  }
/* 267:    */  
/* 272:    */  public static void rayTestSingle(Transform arg0, Transform arg1, CollisionObject arg2, CollisionShape arg3, Transform arg4, RayResultCallback arg5)
/* 273:    */  {
/* 274:274 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();SphereShape pointShape = new SphereShape(0.0F);
/* 275:275 */      pointShape.setMargin(0.0F);
/* 276:276 */      ConvexShape castShape = pointShape;
/* 277:    */      
/* 278:278 */      if (collisionShape.isConvex()) {
/* 279:279 */        ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 280:280 */        castResult.fraction = resultCallback.closestHitFraction;
/* 281:    */        
/* 282:282 */        ConvexShape convexShape = (ConvexShape)collisionShape;
/* 283:283 */        VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 284:    */        
/* 287:287 */        SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(castShape, convexShape, simplexSolver);
/* 288:    */        
/* 293:293 */        if (convexCaster.calcTimeOfImpact(rayFromTrans, rayToTrans, colObjWorldTransform, colObjWorldTransform, castResult))
/* 294:    */        {
/* 295:295 */          if ((castResult.normal.lengthSquared() > 1.0E-004F) && 
/* 296:296 */            (castResult.fraction < resultCallback.closestHitFraction))
/* 297:    */          {
/* 299:299 */            rayFromTrans.basis.transform(castResult.normal);
/* 300:    */            
/* 302:302 */            castResult.normal.normalize();
/* 303:303 */            LocalRayResult localRayResult = new LocalRayResult(collisionObject, null, castResult.normal, castResult.fraction);
/* 304:    */            
/* 309:309 */            boolean normalInWorldSpace = true;
/* 310:310 */            resultCallback.addSingleResult(localRayResult, normalInWorldSpace);
/* 311:    */          }
/* 312:    */          
/* 313:    */        }
/* 314:    */        
/* 315:    */      }
/* 316:316 */      else if (collisionShape.isConcave()) {
/* 317:317 */        if (collisionShape.getShapeType() == BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE)
/* 318:    */        {
/* 319:319 */          BvhTriangleMeshShape triangleMesh = (BvhTriangleMeshShape)collisionShape;
/* 320:320 */          Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 321:321 */          worldTocollisionObject.inverse(colObjWorldTransform);
/* 322:322 */          Vector3f rayFromLocal = localStack.get$javax$vecmath$Vector3f(rayFromTrans.origin);
/* 323:323 */          worldTocollisionObject.transform(rayFromLocal);
/* 324:324 */          Vector3f rayToLocal = localStack.get$javax$vecmath$Vector3f(rayToTrans.origin);
/* 325:325 */          worldTocollisionObject.transform(rayToLocal);
/* 326:    */          
/* 327:327 */          BridgeTriangleRaycastCallback rcb = new BridgeTriangleRaycastCallback(rayFromLocal, rayToLocal, resultCallback, collisionObject, triangleMesh);
/* 328:328 */          rcb.hitFraction = resultCallback.closestHitFraction;
/* 329:329 */          triangleMesh.performRaycast(rcb, rayFromLocal, rayToLocal);
/* 330:    */        }
/* 331:    */        else {
/* 332:332 */          ConcaveShape triangleMesh = (ConcaveShape)collisionShape;
/* 333:    */          
/* 334:334 */          Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 335:335 */          worldTocollisionObject.inverse(colObjWorldTransform);
/* 336:    */          
/* 337:337 */          Vector3f rayFromLocal = localStack.get$javax$vecmath$Vector3f(rayFromTrans.origin);
/* 338:338 */          worldTocollisionObject.transform(rayFromLocal);
/* 339:339 */          Vector3f rayToLocal = localStack.get$javax$vecmath$Vector3f(rayToTrans.origin);
/* 340:340 */          worldTocollisionObject.transform(rayToLocal);
/* 341:    */          
/* 342:342 */          BridgeTriangleRaycastCallback rcb = new BridgeTriangleRaycastCallback(rayFromLocal, rayToLocal, resultCallback, collisionObject, triangleMesh);
/* 343:343 */          rcb.hitFraction = resultCallback.closestHitFraction;
/* 344:    */          
/* 345:345 */          Vector3f rayAabbMinLocal = localStack.get$javax$vecmath$Vector3f(rayFromLocal);
/* 346:346 */          VectorUtil.setMin(rayAabbMinLocal, rayToLocal);
/* 347:347 */          Vector3f rayAabbMaxLocal = localStack.get$javax$vecmath$Vector3f(rayFromLocal);
/* 348:348 */          VectorUtil.setMax(rayAabbMaxLocal, rayToLocal);
/* 349:    */          
/* 350:350 */          triangleMesh.processAllTriangles(rcb, rayAabbMinLocal, rayAabbMaxLocal);
/* 351:    */        }
/* 352:    */        
/* 354:    */      }
/* 355:355 */      else if (collisionShape.isCompound()) {
/* 356:356 */        CompoundShape compoundShape = (CompoundShape)collisionShape;
/* 357:357 */        int i = 0;
/* 358:358 */        Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 359:359 */        for (i = 0; i < compoundShape.getNumChildShapes(); i++) {
/* 360:360 */          compoundShape.getChildTransform(i, childTrans);
/* 361:361 */          CollisionShape childCollisionShape = compoundShape.getChildShape(i);
/* 362:362 */          Transform childWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform(colObjWorldTransform);
/* 363:363 */          childWorldTrans.mul(childTrans);
/* 364:    */          
/* 365:365 */          CollisionShape saveCollisionShape = collisionObject.getCollisionShape();
/* 366:366 */          collisionObject.internalSetTemporaryCollisionShape(childCollisionShape);
/* 367:367 */          rayTestSingle(rayFromTrans, rayToTrans, collisionObject, childCollisionShape, childWorldTrans, resultCallback);
/* 368:    */          
/* 373:373 */          collisionObject.internalSetTemporaryCollisionShape(saveCollisionShape);
/* 374:    */        }
/* 375:    */      }
/* 376:    */    }
/* 377:    */    finally {
/* 378:378 */      .Stack tmp563_561 = localStack;tmp563_561.pop$com$bulletphysics$linearmath$Transform();tmp563_561.pop$javax$vecmath$Vector3f();
/* 379:    */    }
/* 380:    */  }
/* 381:    */  
/* 382:    */  private static class BridgeTriangleConvexcastCallback extends TriangleConvexcastCallback { public CollisionWorld.ConvexResultCallback resultCallback;
/* 383:    */    public CollisionObject collisionObject;
/* 384:    */    public TriangleMeshShape triangleMesh;
/* 385:    */    public boolean normalInWorldSpace;
/* 386:    */    
/* 387:387 */    public BridgeTriangleConvexcastCallback(ConvexShape castShape, Transform from, Transform to, CollisionWorld.ConvexResultCallback resultCallback, CollisionObject collisionObject, TriangleMeshShape triangleMesh, Transform triangleToWorld) { super(from, to, triangleToWorld, triangleMesh.getMargin());
/* 388:388 */      this.resultCallback = resultCallback;
/* 389:389 */      this.collisionObject = collisionObject;
/* 390:390 */      this.triangleMesh = triangleMesh;
/* 391:    */    }
/* 392:    */    
/* 393:    */    public float reportHit(Vector3f hitNormalLocal, Vector3f hitPointLocal, float hitFraction, int partId, int triangleIndex)
/* 394:    */    {
/* 395:395 */      CollisionWorld.LocalShapeInfo shapeInfo = new CollisionWorld.LocalShapeInfo();
/* 396:396 */      shapeInfo.shapePart = partId;
/* 397:397 */      shapeInfo.triangleIndex = triangleIndex;
/* 398:398 */      if (hitFraction <= this.resultCallback.closestHitFraction) {
/* 399:399 */        CollisionWorld.LocalConvexResult convexResult = new CollisionWorld.LocalConvexResult(this.collisionObject, shapeInfo, hitNormalLocal, hitPointLocal, hitFraction);
/* 400:400 */        return this.resultCallback.addSingleResult(convexResult, this.normalInWorldSpace);
/* 401:    */      }
/* 402:402 */      return hitFraction;
/* 403:    */    }
/* 404:    */  }
/* 405:    */  
/* 408:    */  public static void objectQuerySingle(ConvexShape arg0, Transform arg1, Transform arg2, CollisionObject arg3, CollisionShape arg4, Transform arg5, ConvexResultCallback arg6, float arg7)
/* 409:    */  {
/* 410:410 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Matrix3f(); if (collisionShape.isConvex()) {
/* 411:411 */        ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 412:412 */        castResult.allowedPenetration = allowedPenetration;
/* 413:413 */        castResult.fraction = 1.0F;
/* 414:    */        
/* 415:415 */        ConvexShape convexShape = (ConvexShape)collisionShape;
/* 416:416 */        VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 417:417 */        GjkEpaPenetrationDepthSolver gjkEpaPenetrationSolver = new GjkEpaPenetrationDepthSolver();
/* 418:    */        
/* 421:421 */        GjkConvexCast convexCaster2 = new GjkConvexCast(castShape, convexShape, simplexSolver);
/* 422:    */        
/* 424:424 */        ConvexCast castPtr = convexCaster2;
/* 425:    */        
/* 426:426 */        if (castPtr.calcTimeOfImpact(convexFromTrans, convexToTrans, colObjWorldTransform, colObjWorldTransform, castResult))
/* 427:    */        {
/* 428:428 */          if ((castResult.normal.lengthSquared() > 1.0E-004F) && 
/* 429:429 */            (castResult.fraction < resultCallback.closestHitFraction)) {
/* 430:430 */            castResult.normal.normalize();
/* 431:431 */            LocalConvexResult localConvexResult = new LocalConvexResult(collisionObject, null, castResult.normal, castResult.hitPoint, castResult.fraction);
/* 432:    */            
/* 433:433 */            boolean normalInWorldSpace = true;
/* 434:434 */            resultCallback.addSingleResult(localConvexResult, normalInWorldSpace);
/* 435:    */          }
/* 436:    */          
/* 437:    */        }
/* 438:    */        
/* 439:    */      }
/* 440:440 */      else if (collisionShape.isConcave()) {
/* 441:441 */        if (collisionShape.getShapeType() == BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE) {
/* 442:442 */          BvhTriangleMeshShape triangleMesh = (BvhTriangleMeshShape)collisionShape;
/* 443:443 */          Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 444:444 */          worldTocollisionObject.inverse(colObjWorldTransform);
/* 445:    */          
/* 446:446 */          Vector3f convexFromLocal = localStack.get$javax$vecmath$Vector3f();
/* 447:447 */          convexFromLocal.set(convexFromTrans.origin);
/* 448:448 */          worldTocollisionObject.transform(convexFromLocal);
/* 449:    */          
/* 450:450 */          Vector3f convexToLocal = localStack.get$javax$vecmath$Vector3f();
/* 451:451 */          convexToLocal.set(convexToTrans.origin);
/* 452:452 */          worldTocollisionObject.transform(convexToLocal);
/* 453:    */          
/* 455:455 */          Transform rotationXform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 456:456 */          Matrix3f tmpMat = localStack.get$javax$vecmath$Matrix3f();
/* 457:457 */          tmpMat.mul(worldTocollisionObject.basis, convexToTrans.basis);
/* 458:458 */          rotationXform.set(tmpMat);
/* 459:    */          
/* 460:460 */          BridgeTriangleConvexcastCallback tccb = new BridgeTriangleConvexcastCallback(castShape, convexFromTrans, convexToTrans, resultCallback, collisionObject, triangleMesh, colObjWorldTransform);
/* 461:461 */          tccb.hitFraction = resultCallback.closestHitFraction;
/* 462:462 */          tccb.normalInWorldSpace = true;
/* 463:    */          
/* 464:464 */          Vector3f boxMinLocal = localStack.get$javax$vecmath$Vector3f();
/* 465:465 */          Vector3f boxMaxLocal = localStack.get$javax$vecmath$Vector3f();
/* 466:466 */          castShape.getAabb(rotationXform, boxMinLocal, boxMaxLocal);
/* 467:467 */          triangleMesh.performConvexcast(tccb, convexFromLocal, convexToLocal, boxMinLocal, boxMaxLocal);
/* 468:    */        }
/* 469:    */        else {
/* 470:470 */          BvhTriangleMeshShape triangleMesh = (BvhTriangleMeshShape)collisionShape;
/* 471:471 */          Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 472:472 */          worldTocollisionObject.inverse(colObjWorldTransform);
/* 473:    */          
/* 474:474 */          Vector3f convexFromLocal = localStack.get$javax$vecmath$Vector3f();
/* 475:475 */          convexFromLocal.set(convexFromTrans.origin);
/* 476:476 */          worldTocollisionObject.transform(convexFromLocal);
/* 477:    */          
/* 478:478 */          Vector3f convexToLocal = localStack.get$javax$vecmath$Vector3f();
/* 479:479 */          convexToLocal.set(convexToTrans.origin);
/* 480:480 */          worldTocollisionObject.transform(convexToLocal);
/* 481:    */          
/* 483:483 */          Transform rotationXform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 484:484 */          Matrix3f tmpMat = localStack.get$javax$vecmath$Matrix3f();
/* 485:485 */          tmpMat.mul(worldTocollisionObject.basis, convexToTrans.basis);
/* 486:486 */          rotationXform.set(tmpMat);
/* 487:    */          
/* 488:488 */          BridgeTriangleConvexcastCallback tccb = new BridgeTriangleConvexcastCallback(castShape, convexFromTrans, convexToTrans, resultCallback, collisionObject, triangleMesh, colObjWorldTransform);
/* 489:489 */          tccb.hitFraction = resultCallback.closestHitFraction;
/* 490:490 */          tccb.normalInWorldSpace = false;
/* 491:491 */          Vector3f boxMinLocal = localStack.get$javax$vecmath$Vector3f();
/* 492:492 */          Vector3f boxMaxLocal = localStack.get$javax$vecmath$Vector3f();
/* 493:493 */          castShape.getAabb(rotationXform, boxMinLocal, boxMaxLocal);
/* 494:    */          
/* 495:495 */          Vector3f rayAabbMinLocal = localStack.get$javax$vecmath$Vector3f(convexFromLocal);
/* 496:496 */          VectorUtil.setMin(rayAabbMinLocal, convexToLocal);
/* 497:497 */          Vector3f rayAabbMaxLocal = localStack.get$javax$vecmath$Vector3f(convexFromLocal);
/* 498:498 */          VectorUtil.setMax(rayAabbMaxLocal, convexToLocal);
/* 499:499 */          rayAabbMinLocal.add(boxMinLocal);
/* 500:500 */          rayAabbMaxLocal.add(boxMaxLocal);
/* 501:501 */          triangleMesh.processAllTriangles(tccb, rayAabbMinLocal, rayAabbMaxLocal);
/* 502:    */        }
/* 503:    */        
/* 505:    */      }
/* 506:506 */      else if (collisionShape.isCompound()) {
/* 507:507 */        CompoundShape compoundShape = (CompoundShape)collisionShape;
/* 508:508 */        for (int i = 0; i < compoundShape.getNumChildShapes(); i++) {
/* 509:509 */          Transform childTrans = compoundShape.getChildTransform(i, localStack.get$com$bulletphysics$linearmath$Transform());
/* 510:510 */          CollisionShape childCollisionShape = compoundShape.getChildShape(i);
/* 511:511 */          Transform childWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 512:512 */          childWorldTrans.mul(colObjWorldTransform, childTrans);
/* 513:    */          
/* 514:514 */          CollisionShape saveCollisionShape = collisionObject.getCollisionShape();
/* 515:515 */          collisionObject.internalSetTemporaryCollisionShape(childCollisionShape);
/* 516:516 */          objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, childCollisionShape, childWorldTrans, resultCallback, allowedPenetration);
/* 517:    */          
/* 522:522 */          collisionObject.internalSetTemporaryCollisionShape(saveCollisionShape);
/* 523:    */        }
/* 524:    */      }
/* 525:    */    }
/* 526:    */    finally {
/* 527:527 */      .Stack tmp729_727 = localStack;tmp729_727.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp733_729 = tmp729_727;tmp733_729.pop$javax$vecmath$Vector3f();tmp733_729.pop$javax$vecmath$Matrix3f();
/* 528:    */    }
/* 529:    */  }
/* 530:    */  
/* 532:    */  public void rayTest(Vector3f arg1, Vector3f arg2, RayResultCallback arg3)
/* 533:    */  {
/* 534:534 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform rayFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();Transform rayToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 535:535 */      rayFromTrans.setIdentity();
/* 536:536 */      rayFromTrans.origin.set(rayFromWorld);
/* 537:537 */      rayToTrans.setIdentity();
/* 538:    */      
/* 539:539 */      rayToTrans.origin.set(rayToWorld);
/* 540:    */      
/* 542:542 */      Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 543:543 */      float[] hitLambda = new float[1];
/* 544:    */      
/* 545:545 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 546:    */      
/* 547:547 */      for (int i = 0; i < this.collisionObjects.size(); i++)
/* 548:    */      {
/* 549:549 */        if (resultCallback.closestHitFraction == 0.0F) {
/* 550:    */          break;
/* 551:    */        }
/* 552:    */        
/* 553:553 */        CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/* 554:    */        
/* 555:555 */        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
/* 556:    */        {
/* 557:557 */          collisionObject.getCollisionShape().getAabb(collisionObject.getWorldTransform(tmpTrans), collisionObjectAabbMin, collisionObjectAabbMax);
/* 558:    */          
/* 559:559 */          hitLambda[0] = resultCallback.closestHitFraction;
/* 560:560 */          Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/* 561:561 */          if (AabbUtil2.rayAabb(rayFromWorld, rayToWorld, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal)) {
/* 562:562 */            rayTestSingle(rayFromTrans, rayToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback);
/* 563:    */          }
/* 564:    */          
/* 565:    */        }
/* 566:    */        
/* 567:    */      }
/* 568:    */    }
/* 569:    */    finally
/* 570:    */    {
/* 571:571 */      .Stack tmp225_223 = localStack;tmp225_223.pop$com$bulletphysics$linearmath$Transform();tmp225_223.pop$javax$vecmath$Vector3f();
/* 572:    */    }
/* 573:    */  }
/* 574:    */  
/* 576:    */  public void convexSweepTest(ConvexShape arg1, Transform arg2, Transform arg3, ConvexResultCallback arg4)
/* 577:    */  {
/* 578:578 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Quat4f();Transform convexFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 579:579 */      Transform convexToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 580:    */      
/* 581:581 */      convexFromTrans.set(convexFromWorld);
/* 582:582 */      convexToTrans.set(convexToWorld);
/* 583:    */      
/* 584:584 */      Vector3f castShapeAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 585:585 */      Vector3f castShapeAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 586:    */      
/* 589:589 */      Vector3f linVel = localStack.get$javax$vecmath$Vector3f();
/* 590:590 */      Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
/* 591:591 */      TransformUtil.calculateVelocity(convexFromTrans, convexToTrans, 1.0F, linVel, angVel);
/* 592:592 */      Transform R = localStack.get$com$bulletphysics$linearmath$Transform();
/* 593:593 */      R.setIdentity();
/* 594:594 */      R.setRotation(convexFromTrans.getRotation(localStack.get$javax$vecmath$Quat4f()));
/* 595:595 */      castShape.calculateTemporalAabb(R, linVel, angVel, 1.0F, castShapeAabbMin, castShapeAabbMax);
/* 596:    */      
/* 598:598 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 599:599 */      Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 600:600 */      Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 601:601 */      float[] hitLambda = new float[1];
/* 602:    */      
/* 605:605 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 606:606 */        CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/* 607:    */        
/* 609:609 */        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
/* 610:    */        {
/* 611:611 */          collisionObject.getWorldTransform(tmpTrans);
/* 612:612 */          collisionObject.getCollisionShape().getAabb(tmpTrans, collisionObjectAabbMin, collisionObjectAabbMax);
/* 613:613 */          AabbUtil2.aabbExpand(collisionObjectAabbMin, collisionObjectAabbMax, castShapeAabbMin, castShapeAabbMax);
/* 614:614 */          hitLambda[0] = 1.0F;
/* 615:615 */          Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/* 616:616 */          if (AabbUtil2.rayAabb(convexFromWorld.origin, convexToWorld.origin, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal)) {
/* 617:617 */            objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, collisionObject.getCollisionShape(), tmpTrans, resultCallback, getDispatchInfo().allowedCcdPenetration);
/* 618:    */          }
/* 619:    */          
/* 620:    */        }
/* 621:    */        
/* 622:    */      }
/* 623:    */    }
/* 624:    */    finally
/* 625:    */    {
/* 626:626 */      .Stack tmp309_307 = localStack;tmp309_307.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp313_309 = tmp309_307;tmp313_309.pop$javax$vecmath$Vector3f();tmp313_309.pop$javax$vecmath$Quat4f();
/* 627:    */    } }
/* 628:    */  
/* 629:629 */  public ObjectArrayList<CollisionObject> getCollisionObjectArray() { return this.collisionObjects; }
/* 630:    */  
/* 633:    */  public static class LocalShapeInfo
/* 634:    */  {
/* 635:    */    public int shapePart;
/* 636:    */    
/* 638:    */    public int triangleIndex;
/* 639:    */  }
/* 640:    */  
/* 642:    */  public static class LocalRayResult
/* 643:    */  {
/* 644:    */    public CollisionObject collisionObject;
/* 645:    */    
/* 646:    */    public CollisionWorld.LocalShapeInfo localShapeInfo;
/* 647:    */    
/* 648:648 */    public final Vector3f hitNormalLocal = new Vector3f();
/* 649:    */    public float hitFraction;
/* 650:    */    
/* 651:    */    public LocalRayResult(CollisionObject collisionObject, CollisionWorld.LocalShapeInfo localShapeInfo, Vector3f hitNormalLocal, float hitFraction) {
/* 652:652 */      this.collisionObject = collisionObject;
/* 653:653 */      this.localShapeInfo = localShapeInfo;
/* 654:654 */      this.hitNormalLocal.set(hitNormalLocal);
/* 655:655 */      this.hitFraction = hitFraction;
/* 656:    */    }
/* 657:    */  }
/* 658:    */  
/* 661:    */  public static abstract class RayResultCallback
/* 662:    */  {
/* 663:663 */    public float closestHitFraction = 1.0F;
/* 664:    */    public CollisionObject collisionObject;
/* 665:665 */    public short collisionFilterGroup = 1;
/* 666:666 */    public short collisionFilterMask = -1;
/* 667:    */    
/* 668:    */    public boolean hasHit() {
/* 669:669 */      return this.collisionObject != null;
/* 670:    */    }
/* 671:    */    
/* 672:    */    public boolean needsCollision(BroadphaseProxy proxy0) {
/* 673:673 */      boolean collides = (proxy0.collisionFilterGroup & this.collisionFilterMask & 0xFFFF) != 0;
/* 674:674 */      collides = (collides) && ((this.collisionFilterGroup & proxy0.collisionFilterMask & 0xFFFF) != 0);
/* 675:675 */      return collides;
/* 676:    */    }
/* 677:    */    
/* 678:    */    public abstract float addSingleResult(CollisionWorld.LocalRayResult paramLocalRayResult, boolean paramBoolean);
/* 679:    */  }
/* 680:    */  
/* 681:    */  public static class ClosestRayResultCallback extends CollisionWorld.RayResultCallback {
/* 682:682 */    public final Vector3f rayFromWorld = new Vector3f();
/* 683:683 */    public final Vector3f rayToWorld = new Vector3f();
/* 684:    */    
/* 685:685 */    public final Vector3f hitNormalWorld = new Vector3f();
/* 686:686 */    public final Vector3f hitPointWorld = new Vector3f();
/* 687:    */    
/* 688:    */    public ClosestRayResultCallback(Vector3f rayFromWorld, Vector3f rayToWorld) {
/* 689:689 */      this.rayFromWorld.set(rayFromWorld);
/* 690:690 */      this.rayToWorld.set(rayToWorld);
/* 691:    */    }
/* 692:    */    
/* 694:    */    public float addSingleResult(CollisionWorld.LocalRayResult arg1, boolean arg2)
/* 695:    */    {
/* 696:696 */      .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();assert (rayResult.hitFraction <= this.closestHitFraction);
/* 697:    */        
/* 698:698 */        this.closestHitFraction = rayResult.hitFraction;
/* 699:699 */        this.collisionObject = rayResult.collisionObject;
/* 700:700 */        if (normalInWorldSpace) {
/* 701:701 */          this.hitNormalWorld.set(rayResult.hitNormalLocal);
/* 702:    */        }
/* 703:    */        else
/* 704:    */        {
/* 705:705 */          this.hitNormalWorld.set(rayResult.hitNormalLocal);
/* 706:706 */          this.collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(this.hitNormalWorld);
/* 707:    */        }
/* 708:    */        
/* 709:709 */        VectorUtil.setInterpolate3(this.hitPointWorld, this.rayFromWorld, this.rayToWorld, rayResult.hitFraction);
/* 710:710 */        return rayResult.hitFraction; } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 711:    */      }
/* 712:    */    }
/* 713:    */  }
/* 714:    */  
/* 715:    */  public static class LocalConvexResult { public CollisionObject hitCollisionObject;
/* 716:    */    public CollisionWorld.LocalShapeInfo localShapeInfo;
/* 717:717 */    public final Vector3f hitNormalLocal = new Vector3f();
/* 718:718 */    public final Vector3f hitPointLocal = new Vector3f();
/* 719:    */    public float hitFraction;
/* 720:    */    
/* 721:    */    public LocalConvexResult(CollisionObject hitCollisionObject, CollisionWorld.LocalShapeInfo localShapeInfo, Vector3f hitNormalLocal, Vector3f hitPointLocal, float hitFraction) {
/* 722:722 */      this.hitCollisionObject = hitCollisionObject;
/* 723:723 */      this.localShapeInfo = localShapeInfo;
/* 724:724 */      this.hitNormalLocal.set(hitNormalLocal);
/* 725:725 */      this.hitPointLocal.set(hitPointLocal);
/* 726:726 */      this.hitFraction = hitFraction;
/* 727:    */    }
/* 728:    */  }
/* 729:    */  
/* 730:    */  public static abstract class ConvexResultCallback {
/* 731:731 */    public float closestHitFraction = 1.0F;
/* 732:732 */    public short collisionFilterGroup = 1;
/* 733:733 */    public short collisionFilterMask = -1;
/* 734:    */    
/* 735:    */    public boolean hasHit() {
/* 736:736 */      return this.closestHitFraction < 1.0F;
/* 737:    */    }
/* 738:    */    
/* 739:    */    public boolean needsCollision(BroadphaseProxy proxy0) {
/* 740:740 */      boolean collides = (proxy0.collisionFilterGroup & this.collisionFilterMask & 0xFFFF) != 0;
/* 741:741 */      collides = (collides) && ((this.collisionFilterGroup & proxy0.collisionFilterMask & 0xFFFF) != 0);
/* 742:742 */      return collides;
/* 743:    */    }
/* 744:    */    
/* 745:    */    public abstract float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean);
/* 746:    */  }
/* 747:    */  
/* 748:    */  public static class ClosestConvexResultCallback extends CollisionWorld.ConvexResultCallback {
/* 749:749 */    public final Vector3f convexFromWorld = new Vector3f();
/* 750:750 */    public final Vector3f convexToWorld = new Vector3f();
/* 751:751 */    public final Vector3f hitNormalWorld = new Vector3f();
/* 752:752 */    public final Vector3f hitPointWorld = new Vector3f();
/* 753:    */    public CollisionObject hitCollisionObject;
/* 754:    */    
/* 755:    */    public ClosestConvexResultCallback(Vector3f convexFromWorld, Vector3f convexToWorld) {
/* 756:756 */      this.convexFromWorld.set(convexFromWorld);
/* 757:757 */      this.convexToWorld.set(convexToWorld);
/* 758:758 */      this.hitCollisionObject = null;
/* 759:    */    }
/* 760:    */    
/* 762:    */    public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
/* 763:    */    {
/* 764:764 */      .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();assert (convexResult.hitFraction <= this.closestHitFraction);
/* 765:    */        
/* 766:766 */        this.closestHitFraction = convexResult.hitFraction;
/* 767:767 */        this.hitCollisionObject = convexResult.hitCollisionObject;
/* 768:768 */        if (normalInWorldSpace) {
/* 769:769 */          this.hitNormalWorld.set(convexResult.hitNormalLocal);
/* 770:770 */          if (this.hitNormalWorld.length() > 2.0F) {
/* 771:771 */            System.out.println("CollisionWorld.addSingleResult world " + this.hitNormalWorld);
/* 772:    */          }
/* 773:    */        }
/* 774:    */        else
/* 775:    */        {
/* 776:776 */          this.hitNormalWorld.set(convexResult.hitNormalLocal);
/* 777:777 */          this.hitCollisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(this.hitNormalWorld);
/* 778:778 */          if (this.hitNormalWorld.length() > 2.0F) {
/* 779:779 */            System.out.println("CollisionWorld.addSingleResult world " + this.hitNormalWorld);
/* 780:    */          }
/* 781:    */        }
/* 782:    */        
/* 783:783 */        this.hitPointWorld.set(convexResult.hitPointLocal);
/* 784:784 */        return convexResult.hitFraction; } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 785:    */      }
/* 786:    */    }
/* 787:    */  }
/* 788:    */  
/* 789:    */  private static class BridgeTriangleRaycastCallback extends TriangleRaycastCallback {
/* 790:    */    public CollisionWorld.RayResultCallback resultCallback;
/* 791:    */    public CollisionObject collisionObject;
/* 792:    */    public ConcaveShape triangleMesh;
/* 793:    */    
/* 794:794 */    public BridgeTriangleRaycastCallback(Vector3f from, Vector3f to, CollisionWorld.RayResultCallback resultCallback, CollisionObject collisionObject, ConcaveShape triangleMesh) { super(to);
/* 795:795 */      this.resultCallback = resultCallback;
/* 796:796 */      this.collisionObject = collisionObject;
/* 797:797 */      this.triangleMesh = triangleMesh;
/* 798:    */    }
/* 799:    */    
/* 800:    */    public float reportHit(Vector3f hitNormalLocal, float hitFraction, int partId, int triangleIndex) {
/* 801:801 */      CollisionWorld.LocalShapeInfo shapeInfo = new CollisionWorld.LocalShapeInfo();
/* 802:802 */      shapeInfo.shapePart = partId;
/* 803:803 */      shapeInfo.triangleIndex = triangleIndex;
/* 804:    */      
/* 805:805 */      CollisionWorld.LocalRayResult rayResult = new CollisionWorld.LocalRayResult(this.collisionObject, shapeInfo, hitNormalLocal, hitFraction);
/* 806:    */      
/* 807:807 */      boolean normalInWorldSpace = false;
/* 808:808 */      return this.resultCallback.addSingleResult(rayResult, normalInWorldSpace);
/* 809:    */    }
/* 810:    */  }
/* 811:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */