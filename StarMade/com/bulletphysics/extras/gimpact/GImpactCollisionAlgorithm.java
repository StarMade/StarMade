/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   6:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   7:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   8:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   9:    */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*  10:    */import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*  11:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  12:    */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*  13:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  14:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  15:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*  16:    */import com.bulletphysics.collision.shapes.ConcaveShape;
/*  17:    */import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*  18:    */import com.bulletphysics.linearmath.Transform;
/*  19:    */import com.bulletphysics.linearmath.VectorUtil;
/*  20:    */import com.bulletphysics.util.IntArrayList;
/*  21:    */import com.bulletphysics.util.ObjectArrayList;
/*  22:    */import com.bulletphysics.util.ObjectPool;
/*  23:    */import javax.vecmath.Vector3f;
/*  24:    */import javax.vecmath.Vector4f;
/*  25:    */
/*  62:    */public class GImpactCollisionAlgorithm
/*  63:    */  extends CollisionAlgorithm
/*  64:    */{
/*  65:    */  protected CollisionAlgorithm convex_algorithm;
/*  66:    */  protected PersistentManifold manifoldPtr;
/*  67:    */  protected ManifoldResult resultOut;
/*  68:    */  protected DispatcherInfo dispatchInfo;
/*  69:    */  protected int triface0;
/*  70:    */  protected int part0;
/*  71:    */  protected int triface1;
/*  72:    */  protected int part1;
/*  73:    */  private PairSet tmpPairset;
/*  74:    */  
/*  75: 75 */  public GImpactCollisionAlgorithm() { this.tmpPairset = new PairSet(); }
/*  76:    */  
/*  77:    */  public void init(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1) {
/*  78: 78 */    super.init(ci);
/*  79: 79 */    this.manifoldPtr = null;
/*  80: 80 */    this.convex_algorithm = null;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void destroy()
/*  84:    */  {
/*  85: 85 */    clearCache();
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*  89:    */  {
/*  90: 90 */    clearCache();
/*  91:    */    
/*  92: 92 */    this.resultOut = resultOut;
/*  93: 93 */    this.dispatchInfo = dispatchInfo;
/*  94:    */    
/*  97: 97 */    if (body0.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
/*  98:    */    {
/*  99: 99 */      GImpactShapeInterface gimpactshape0 = (GImpactShapeInterface)body0.getCollisionShape();
/* 100:    */      
/* 101:101 */      if (body1.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
/* 102:    */      {
/* 103:103 */        GImpactShapeInterface gimpactshape1 = (GImpactShapeInterface)body1.getCollisionShape();
/* 104:    */        
/* 105:105 */        gimpact_vs_gimpact(body0, body1, gimpactshape0, gimpactshape1);
/* 106:    */      }
/* 107:    */      else
/* 108:    */      {
/* 109:109 */        gimpact_vs_shape(body0, body1, gimpactshape0, body1.getCollisionShape(), false);
/* 110:    */      }
/* 111:    */      
/* 112:    */    }
/* 113:113 */    else if (body1.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
/* 114:    */    {
/* 115:115 */      GImpactShapeInterface gimpactshape1 = (GImpactShapeInterface)body1.getCollisionShape();
/* 116:    */      
/* 117:117 */      gimpact_vs_shape(body1, body0, gimpactshape1, body0.getCollisionShape(), true);
/* 118:    */    }
/* 119:    */  }
/* 120:    */  
/* 121:    */  public void gimpact_vs_gimpact(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, GImpactShapeInterface arg4) {
/* 122:122 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform(); if (shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE) {
/* 123:123 */        GImpactMeshShape meshshape0 = (GImpactMeshShape)shape0;
/* 124:124 */        this.part0 = meshshape0.getMeshPartCount();
/* 125:    */        
/* 126:126 */        while (this.part0-- != 0) {
/* 127:127 */          gimpact_vs_gimpact(body0, body1, meshshape0.getMeshPart(this.part0), shape1);
/* 128:    */        }
/* 129:    */        
/* 130:130 */        return;
/* 131:    */      }
/* 132:    */      
/* 133:133 */      if (shape1.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE) {
/* 134:134 */        GImpactMeshShape meshshape1 = (GImpactMeshShape)shape1;
/* 135:135 */        this.part1 = meshshape1.getMeshPartCount();
/* 136:    */        
/* 137:137 */        while (this.part1-- != 0) {
/* 138:138 */          gimpact_vs_gimpact(body0, body1, shape0, meshshape1.getMeshPart(this.part1));
/* 139:    */        }
/* 140:    */        
/* 141:141 */        return;
/* 142:    */      }
/* 143:    */      
/* 144:144 */      Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 145:145 */      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 146:    */      
/* 147:147 */      PairSet pairset = this.tmpPairset;
/* 148:148 */      pairset.clear();
/* 149:    */      
/* 150:150 */      gimpact_vs_gimpact_find_pairs(orgtrans0, orgtrans1, shape0, shape1, pairset);
/* 151:    */      
/* 152:152 */      if (pairset.size() == 0) {
/* 153:153 */        return;
/* 154:    */      }
/* 155:155 */      if ((shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART) && (shape1.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART))
/* 156:    */      {
/* 158:158 */        GImpactMeshShapePart shapepart0 = (GImpactMeshShapePart)shape0;
/* 159:159 */        GImpactMeshShapePart shapepart1 = (GImpactMeshShapePart)shape1;
/* 160:    */        
/* 165:165 */        collide_sat_triangles(body0, body1, shapepart0, shapepart1, pairset, pairset.size());
/* 166:    */        
/* 168:168 */        return;
/* 169:    */      }
/* 170:    */      
/* 173:173 */      shape0.lockChildShapes();
/* 174:174 */      shape1.lockChildShapes();
/* 175:    */      
/* 176:176 */      GIM_ShapeRetriever retriever0 = new GIM_ShapeRetriever(shape0);
/* 177:177 */      GIM_ShapeRetriever retriever1 = new GIM_ShapeRetriever(shape1);
/* 178:    */      
/* 179:179 */      boolean child_has_transform0 = shape0.childrenHasTransform();
/* 180:180 */      boolean child_has_transform1 = shape1.childrenHasTransform();
/* 181:    */      
/* 182:182 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 183:    */      
/* 184:184 */      int i = pairset.size();
/* 185:185 */      while (i-- != 0) {
/* 186:186 */        Pair pair = pairset.get(i);
/* 187:187 */        this.triface0 = pair.index1;
/* 188:188 */        this.triface1 = pair.index2;
/* 189:189 */        CollisionShape colshape0 = retriever0.getChildShape(this.triface0);
/* 190:190 */        CollisionShape colshape1 = retriever1.getChildShape(this.triface1);
/* 191:    */        
/* 192:192 */        if (child_has_transform0) {
/* 193:193 */          tmpTrans.mul(orgtrans0, shape0.getChildTransform(this.triface0));
/* 194:194 */          body0.setWorldTransform(tmpTrans);
/* 195:    */        }
/* 196:    */        
/* 197:197 */        if (child_has_transform1) {
/* 198:198 */          tmpTrans.mul(orgtrans1, shape1.getChildTransform(this.triface1));
/* 199:199 */          body1.setWorldTransform(tmpTrans);
/* 200:    */        }
/* 201:    */        
/* 203:203 */        convex_vs_convex_collision(body0, body1, colshape0, colshape1);
/* 204:    */        
/* 205:205 */        if (child_has_transform0) {
/* 206:206 */          body0.setWorldTransform(orgtrans0);
/* 207:    */        }
/* 208:    */        
/* 209:209 */        if (child_has_transform1) {
/* 210:210 */          body1.setWorldTransform(orgtrans1);
/* 211:    */        }
/* 212:    */      }
/* 213:    */      
/* 215:215 */      shape0.unlockChildShapes();
/* 216:216 */      shape1.unlockChildShapes();
/* 217:217 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 218:    */    } }
/* 219:    */  
/* 220:220 */  public void gimpact_vs_shape(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, CollisionShape arg4, boolean arg5) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform(); if (shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE) {
/* 221:221 */        GImpactMeshShape meshshape0 = (GImpactMeshShape)shape0;
/* 222:222 */        this.part0 = meshshape0.getMeshPartCount();
/* 223:    */        
/* 224:224 */        while (this.part0-- != 0) {
/* 225:225 */          gimpact_vs_shape(body0, body1, meshshape0.getMeshPart(this.part0), shape1, swapped);
/* 226:    */        }
/* 227:    */        
/* 231:231 */        return;
/* 232:    */      }
/* 233:    */      
/* 235:235 */      if ((shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART) && (shape1.getShapeType() == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/* 236:    */      {
/* 237:237 */        GImpactMeshShapePart shapepart = (GImpactMeshShapePart)shape0;
/* 238:238 */        StaticPlaneShape planeshape = (StaticPlaneShape)shape1;
/* 239:239 */        gimpacttrimeshpart_vs_plane_collision(body0, body1, shapepart, planeshape, swapped);
/* 240:240 */        return;
/* 241:    */      }
/* 242:    */      
/* 244:244 */      if (shape1.isCompound()) {
/* 245:245 */        CompoundShape compoundshape = (CompoundShape)shape1;
/* 246:246 */        gimpact_vs_compoundshape(body0, body1, shape0, compoundshape, swapped);
/* 247:247 */        return;
/* 248:    */      }
/* 249:249 */      if (shape1.isConcave()) {
/* 250:250 */        ConcaveShape concaveshape = (ConcaveShape)shape1;
/* 251:251 */        gimpact_vs_concave(body0, body1, shape0, concaveshape, swapped);
/* 252:252 */        return;
/* 253:    */      }
/* 254:    */      
/* 255:255 */      Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 256:256 */      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 257:    */      
/* 258:258 */      IntArrayList collided_results = new IntArrayList();
/* 259:    */      
/* 260:260 */      gimpact_vs_shape_find_pairs(orgtrans0, orgtrans1, shape0, shape1, collided_results);
/* 261:    */      
/* 262:262 */      if (collided_results.size() == 0) {
/* 263:263 */        return;
/* 264:    */      }
/* 265:265 */      shape0.lockChildShapes();
/* 266:    */      
/* 267:267 */      GIM_ShapeRetriever retriever0 = new GIM_ShapeRetriever(shape0);
/* 268:    */      
/* 269:269 */      boolean child_has_transform0 = shape0.childrenHasTransform();
/* 270:    */      
/* 271:271 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 272:    */      
/* 273:273 */      int i = collided_results.size();
/* 274:    */      
/* 275:275 */      while (i-- != 0) {
/* 276:276 */        int child_index = collided_results.get(i);
/* 277:277 */        if (swapped) {
/* 278:278 */          this.triface1 = child_index;
/* 279:    */        }
/* 280:    */        else {
/* 281:281 */          this.triface0 = child_index;
/* 282:    */        }
/* 283:283 */        CollisionShape colshape0 = retriever0.getChildShape(child_index);
/* 284:    */        
/* 285:285 */        if (child_has_transform0) {
/* 286:286 */          tmpTrans.mul(orgtrans0, shape0.getChildTransform(child_index));
/* 287:287 */          body0.setWorldTransform(tmpTrans);
/* 288:    */        }
/* 289:    */        
/* 291:291 */        if (swapped) {
/* 292:292 */          shape_vs_shape_collision(body1, body0, shape1, colshape0);
/* 293:    */        }
/* 294:    */        else {
/* 295:295 */          shape_vs_shape_collision(body0, body1, colshape0, shape1);
/* 296:    */        }
/* 297:    */        
/* 299:299 */        if (child_has_transform0) {
/* 300:300 */          body0.setWorldTransform(orgtrans0);
/* 301:    */        }
/* 302:    */      }
/* 303:    */      
/* 305:305 */      shape0.unlockChildShapes();
/* 306:306 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 307:    */    } }
/* 308:    */  
/* 309:309 */  public void gimpact_vs_compoundshape(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, CompoundShape arg4, boolean arg5) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 310:310 */      Transform childtrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 311:311 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 312:    */      
/* 313:313 */      int i = shape1.getNumChildShapes();
/* 314:314 */      while (i-- != 0) {
/* 315:315 */        CollisionShape colshape1 = shape1.getChildShape(i);
/* 316:316 */        childtrans1.mul(orgtrans1, shape1.getChildTransform(i, tmpTrans));
/* 317:    */        
/* 318:318 */        body1.setWorldTransform(childtrans1);
/* 319:    */        
/* 321:321 */        gimpact_vs_shape(body0, body1, shape0, colshape1, swapped);
/* 322:    */        
/* 325:325 */        body1.setWorldTransform(orgtrans1);
/* 326:    */      }
/* 327:327 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 328:    */    }
/* 329:    */  }
/* 330:    */  
/* 331:331 */  public void gimpact_vs_concave(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, ConcaveShape arg4, boolean arg5) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();GImpactTriangleCallback tricallback = new GImpactTriangleCallback();
/* 332:332 */      tricallback.algorithm = this;
/* 333:333 */      tricallback.body0 = body0;
/* 334:334 */      tricallback.body1 = body1;
/* 335:335 */      tricallback.gimpactshape0 = shape0;
/* 336:336 */      tricallback.swapped = swapped;
/* 337:337 */      tricallback.margin = shape1.getMargin();
/* 338:    */      
/* 340:340 */      Transform gimpactInConcaveSpace = localStack.get$com$bulletphysics$linearmath$Transform();
/* 341:    */      
/* 342:342 */      body1.getWorldTransform(gimpactInConcaveSpace);
/* 343:343 */      gimpactInConcaveSpace.inverse();
/* 344:344 */      gimpactInConcaveSpace.mul(body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()));
/* 345:    */      
/* 346:346 */      Vector3f minAABB = localStack.get$javax$vecmath$Vector3f();Vector3f maxAABB = localStack.get$javax$vecmath$Vector3f();
/* 347:347 */      shape0.getAabb(gimpactInConcaveSpace, minAABB, maxAABB);
/* 348:    */      
/* 349:349 */      shape1.processAllTriangles(tricallback, minAABB, maxAABB);
/* 350:350 */    } finally { .Stack tmp144_142 = localStack;tmp144_142.pop$com$bulletphysics$linearmath$Transform();tmp144_142.pop$javax$vecmath$Vector3f();
/* 351:    */    }
/* 352:    */  }
/* 353:    */  
/* 354:    */  protected PersistentManifold newContactManifold(CollisionObject body0, CollisionObject body1)
/* 355:    */  {
/* 356:356 */    this.manifoldPtr = this.dispatcher.getNewManifold(body0, body1);
/* 357:357 */    return this.manifoldPtr;
/* 358:    */  }
/* 359:    */  
/* 360:    */  protected void destroyConvexAlgorithm() {
/* 361:361 */    if (this.convex_algorithm != null)
/* 362:    */    {
/* 363:363 */      this.dispatcher.freeCollisionAlgorithm(this.convex_algorithm);
/* 364:364 */      this.convex_algorithm = null;
/* 365:    */    }
/* 366:    */  }
/* 367:    */  
/* 368:    */  protected void destroyContactManifolds() {
/* 369:369 */    if (this.manifoldPtr == null) return;
/* 370:370 */    this.dispatcher.releaseManifold(this.manifoldPtr);
/* 371:371 */    this.manifoldPtr = null;
/* 372:    */  }
/* 373:    */  
/* 374:    */  protected void clearCache() {
/* 375:375 */    destroyContactManifolds();
/* 376:376 */    destroyConvexAlgorithm();
/* 377:    */    
/* 378:378 */    this.triface0 = -1;
/* 379:379 */    this.part0 = -1;
/* 380:380 */    this.triface1 = -1;
/* 381:381 */    this.part1 = -1;
/* 382:    */  }
/* 383:    */  
/* 384:    */  protected PersistentManifold getLastManifold() {
/* 385:385 */    return this.manifoldPtr;
/* 386:    */  }
/* 387:    */  
/* 390:    */  protected void checkManifold(CollisionObject body0, CollisionObject body1)
/* 391:    */  {
/* 392:392 */    if (getLastManifold() == null) {
/* 393:393 */      newContactManifold(body0, body1);
/* 394:    */    }
/* 395:    */    
/* 396:396 */    this.resultOut.setPersistentManifold(getLastManifold());
/* 397:    */  }
/* 398:    */  
/* 401:    */  protected CollisionAlgorithm newAlgorithm(CollisionObject body0, CollisionObject body1)
/* 402:    */  {
/* 403:403 */    checkManifold(body0, body1);
/* 404:    */    
/* 405:405 */    CollisionAlgorithm convex_algorithm = this.dispatcher.findAlgorithm(body0, body1, getLastManifold());
/* 406:406 */    return convex_algorithm;
/* 407:    */  }
/* 408:    */  
/* 411:    */  protected void checkConvexAlgorithm(CollisionObject body0, CollisionObject body1)
/* 412:    */  {
/* 413:413 */    if (this.convex_algorithm != null) return;
/* 414:414 */    this.convex_algorithm = newAlgorithm(body0, body1);
/* 415:    */  }
/* 416:    */  
/* 417:    */  protected void addContactPoint(CollisionObject body0, CollisionObject body1, Vector3f point, Vector3f normal, float distance) {
/* 418:418 */    this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
/* 419:419 */    checkManifold(body0, body1);
/* 420:420 */    this.resultOut.addContactPoint(normal, point, distance);
/* 421:    */  }
/* 422:    */  
/* 427:    */  void collide_sat_triangles(CollisionObject arg1, CollisionObject arg2, GImpactMeshShapePart arg3, GImpactMeshShapePart arg4, PairSet arg5, int arg6)
/* 428:    */  {
/* 429:429 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle(); .Stack tmp15_11 = tmp11_7;tmp15_11.push$javax$vecmath$Vector3f();tmp15_11.push$com$bulletphysics$extras$gimpact$TriangleContact();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 430:    */      
/* 431:431 */      Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 432:432 */      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 433:    */      
/* 434:434 */      PrimitiveTriangle ptri0 = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 435:435 */      PrimitiveTriangle ptri1 = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 436:436 */      TriangleContact contact_data = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
/* 437:    */      
/* 438:438 */      shape0.lockChildShapes();
/* 439:439 */      shape1.lockChildShapes();
/* 440:    */      
/* 441:441 */      int pair_pointer = 0;
/* 442:    */      
/* 443:443 */      while (pair_count-- != 0)
/* 444:    */      {
/* 447:447 */        Pair pair = pairs.get(pair_pointer++);
/* 448:448 */        this.triface0 = pair.index1;
/* 449:449 */        this.triface1 = pair.index2;
/* 450:    */        
/* 451:451 */        shape0.getPrimitiveTriangle(this.triface0, ptri0);
/* 452:452 */        shape1.getPrimitiveTriangle(this.triface1, ptri1);
/* 453:    */        
/* 458:458 */        ptri0.applyTransform(orgtrans0);
/* 459:459 */        ptri1.applyTransform(orgtrans1);
/* 460:    */        
/* 462:462 */        ptri0.buildTriPlane();
/* 463:463 */        ptri1.buildTriPlane();
/* 464:    */        
/* 466:466 */        if ((ptri0.overlap_test_conservative(ptri1)) && 
/* 467:467 */          (ptri0.find_triangle_collision_clip_method(ptri1, contact_data)))
/* 468:    */        {
/* 469:469 */          int j = contact_data.point_count;
/* 470:470 */          while (j-- != 0) {
/* 471:471 */            tmp.x = contact_data.separating_normal.x;
/* 472:472 */            tmp.y = contact_data.separating_normal.y;
/* 473:473 */            tmp.z = contact_data.separating_normal.z;
/* 474:    */            
/* 475:475 */            addContactPoint(body0, body1, contact_data.points[j], tmp, -contact_data.penetration_depth);
/* 476:    */          }
/* 477:    */        }
/* 478:    */      }
/* 479:    */      
/* 488:488 */      shape0.unlockChildShapes();
/* 489:489 */      shape1.unlockChildShapes();
/* 490:490 */    } finally { .Stack tmp300_298 = localStack;tmp300_298.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp304_300 = tmp300_298;tmp304_300.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle(); .Stack tmp308_304 = tmp304_300;tmp308_304.pop$javax$vecmath$Vector3f();tmp308_304.pop$com$bulletphysics$extras$gimpact$TriangleContact();
/* 491:    */    } }
/* 492:    */  
/* 493:493 */  protected void shape_vs_shape_collision(CollisionObject body0, CollisionObject body1, CollisionShape shape0, CollisionShape shape1) { CollisionShape tmpShape0 = body0.getCollisionShape();
/* 494:494 */    CollisionShape tmpShape1 = body1.getCollisionShape();
/* 495:    */    
/* 496:496 */    body0.internalSetTemporaryCollisionShape(shape0);
/* 497:497 */    body1.internalSetTemporaryCollisionShape(shape1);
/* 498:    */    
/* 500:500 */    CollisionAlgorithm algor = newAlgorithm(body0, body1);
/* 501:    */    
/* 503:503 */    this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
/* 504:    */    
/* 505:505 */    algor.processCollision(body0, body1, this.dispatchInfo, this.resultOut);
/* 506:    */    
/* 508:508 */    this.dispatcher.freeCollisionAlgorithm(algor);
/* 509:    */    
/* 511:511 */    body0.internalSetTemporaryCollisionShape(tmpShape0);
/* 512:512 */    body1.internalSetTemporaryCollisionShape(tmpShape1);
/* 513:    */  }
/* 514:    */  
/* 515:    */  protected void convex_vs_convex_collision(CollisionObject body0, CollisionObject body1, CollisionShape shape0, CollisionShape shape1) {
/* 516:516 */    CollisionShape tmpShape0 = body0.getCollisionShape();
/* 517:517 */    CollisionShape tmpShape1 = body1.getCollisionShape();
/* 518:    */    
/* 519:519 */    body0.internalSetTemporaryCollisionShape(shape0);
/* 520:520 */    body1.internalSetTemporaryCollisionShape(shape1);
/* 521:    */    
/* 522:522 */    this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
/* 523:    */    
/* 524:524 */    checkConvexAlgorithm(body0, body1);
/* 525:525 */    this.convex_algorithm.processCollision(body0, body1, this.dispatchInfo, this.resultOut);
/* 526:    */    
/* 527:527 */    body0.internalSetTemporaryCollisionShape(tmpShape0);
/* 528:528 */    body1.internalSetTemporaryCollisionShape(tmpShape1);
/* 529:    */  }
/* 530:    */  
/* 531:    */  void gimpact_vs_gimpact_find_pairs(Transform arg1, Transform arg2, GImpactShapeInterface arg3, GImpactShapeInterface arg4, PairSet arg5) {
/* 532:532 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); if ((shape0.hasBoxSet()) && (shape1.hasBoxSet())) {
/* 533:533 */        GImpactBvh.find_collision(shape0.getBoxSet(), trans0, shape1.getBoxSet(), trans1, pairset);
/* 534:    */      }
/* 535:    */      else {
/* 536:536 */        BoxCollision.AABB boxshape0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 537:537 */        BoxCollision.AABB boxshape1 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 538:538 */        int i = shape0.getNumChildShapes();
/* 539:    */        
/* 540:540 */        while (i-- != 0) {
/* 541:541 */          shape0.getChildAabb(i, trans0, boxshape0.min, boxshape0.max);
/* 542:    */          
/* 543:543 */          int j = shape1.getNumChildShapes();
/* 544:544 */          while (j-- != 0) {
/* 545:545 */            shape1.getChildAabb(i, trans1, boxshape1.min, boxshape1.max);
/* 546:    */            
/* 547:547 */            if (boxshape1.has_collision(boxshape0))
/* 548:548 */              pairset.push_pair(i, j);
/* 549:    */          }
/* 550:    */        }
/* 551:    */      }
/* 552:    */    } finally {
/* 553:553 */      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 554:    */    } }
/* 555:    */  
/* 556:556 */  protected void gimpact_vs_shape_find_pairs(Transform arg1, Transform arg2, GImpactShapeInterface arg3, CollisionShape arg4, IntArrayList arg5) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB boxshape = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 557:    */      
/* 558:558 */      if (shape0.hasBoxSet()) {
/* 559:559 */        Transform trans1to0 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 560:560 */        trans1to0.inverse(trans0);
/* 561:561 */        trans1to0.mul(trans1);
/* 562:    */        
/* 563:563 */        shape1.getAabb(trans1to0, boxshape.min, boxshape.max);
/* 564:    */        
/* 565:565 */        shape0.getBoxSet().boxQuery(boxshape, collided_primitives);
/* 566:    */      }
/* 567:    */      else {
/* 568:568 */        shape1.getAabb(trans1, boxshape.min, boxshape.max);
/* 569:    */        
/* 570:570 */        BoxCollision.AABB boxshape0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 571:571 */        int i = shape0.getNumChildShapes();
/* 572:    */        
/* 573:573 */        while (i-- != 0) {
/* 574:574 */          shape0.getChildAabb(i, trans0, boxshape0.min, boxshape0.max);
/* 575:    */          
/* 576:576 */          if (boxshape.has_collision(boxshape0))
/* 577:577 */            collided_primitives.add(i);
/* 578:    */        }
/* 579:    */      }
/* 580:    */    } finally {
/* 581:581 */      .Stack tmp165_163 = localStack;tmp165_163.pop$com$bulletphysics$linearmath$Transform();tmp165_163.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 582:    */    } }
/* 583:    */  
/* 584:584 */  protected void gimpacttrimeshpart_vs_plane_collision(CollisionObject arg1, CollisionObject arg2, GImpactMeshShapePart arg3, StaticPlaneShape arg4, boolean arg5) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); .Stack tmp15_11 = tmp11_7;tmp15_11.push$javax$vecmath$Vector3f();tmp15_11.push$javax$vecmath$Vector4f();Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 585:585 */      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 586:    */      
/* 587:587 */      StaticPlaneShape planeshape = shape1;
/* 588:588 */      Vector4f plane = localStack.get$javax$vecmath$Vector4f();
/* 589:589 */      PlaneShape.get_plane_equation_transformed(planeshape, orgtrans1, plane);
/* 590:    */      
/* 593:593 */      BoxCollision.AABB tribox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 594:594 */      shape0.getAabb(orgtrans0, tribox.min, tribox.max);
/* 595:595 */      tribox.increment_margin(planeshape.getMargin());
/* 596:    */      
/* 597:597 */      if (tribox.plane_classify(plane) != PlaneIntersectionType.COLLIDE_PLANE) {
/* 598:598 */        return;
/* 599:    */      }
/* 600:600 */      shape0.lockChildShapes();
/* 601:    */      
/* 602:602 */      float margin = shape0.getMargin() + planeshape.getMargin();
/* 603:    */      
/* 604:604 */      Vector3f vertex = localStack.get$javax$vecmath$Vector3f();
/* 605:    */      
/* 606:606 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 607:    */      
/* 608:608 */      int vi = shape0.getVertexCount();
/* 609:609 */      while (vi-- != 0) {
/* 610:610 */        shape0.getVertex(vi, vertex);
/* 611:611 */        orgtrans0.transform(vertex);
/* 612:    */        
/* 613:613 */        float distance = VectorUtil.dot3(vertex, plane) - plane.w - margin;
/* 614:    */        
/* 615:615 */        if (distance < 0.0F)
/* 616:    */        {
/* 617:617 */          if (swapped) {
/* 618:618 */            tmp.set(-plane.x, -plane.y, -plane.z);
/* 619:619 */            addContactPoint(body1, body0, vertex, tmp, distance);
/* 620:    */          }
/* 621:    */          else {
/* 622:622 */            tmp.set(plane.x, plane.y, plane.z);
/* 623:623 */            addContactPoint(body0, body1, vertex, tmp, distance);
/* 624:    */          }
/* 625:    */        }
/* 626:    */      }
/* 627:    */      
/* 628:628 */      shape0.unlockChildShapes();
/* 629:629 */    } finally { .Stack tmp314_312 = localStack;tmp314_312.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp318_314 = tmp314_312;tmp318_314.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); .Stack tmp322_318 = tmp318_314;tmp322_318.pop$javax$vecmath$Vector3f();tmp322_318.pop$javax$vecmath$Vector4f();
/* 630:    */    }
/* 631:    */  }
/* 632:    */  
/* 633:633 */  public void setFace0(int value) { this.triface0 = value; }
/* 634:    */  
/* 635:    */  public int getFace0()
/* 636:    */  {
/* 637:637 */    return this.triface0;
/* 638:    */  }
/* 639:    */  
/* 640:    */  public void setFace1(int value) {
/* 641:641 */    this.triface1 = value;
/* 642:    */  }
/* 643:    */  
/* 644:    */  public int getFace1() {
/* 645:645 */    return this.triface1;
/* 646:    */  }
/* 647:    */  
/* 648:    */  public void setPart0(int value) {
/* 649:649 */    this.part0 = value;
/* 650:    */  }
/* 651:    */  
/* 652:    */  public int getPart0() {
/* 653:653 */    return this.part0;
/* 654:    */  }
/* 655:    */  
/* 656:    */  public void setPart1(int value) {
/* 657:657 */    this.part1 = value;
/* 658:    */  }
/* 659:    */  
/* 660:    */  public int getPart1() {
/* 661:661 */    return this.part1;
/* 662:    */  }
/* 663:    */  
/* 664:    */  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/* 665:    */  {
/* 666:666 */    return 1.0F;
/* 667:    */  }
/* 668:    */  
/* 669:    */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/* 670:    */  {
/* 671:671 */    if (this.manifoldPtr != null) {
/* 672:672 */      manifoldArray.add(this.manifoldPtr);
/* 673:    */    }
/* 674:    */  }
/* 675:    */  
/* 680:    */  public static void registerAlgorithm(CollisionDispatcher dispatcher)
/* 681:    */  {
/* 682:682 */    CreateFunc createFunc = new CreateFunc();
/* 683:    */    
/* 684:684 */    for (int i = 0; i < BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal(); i++) {
/* 685:685 */      dispatcher.registerCollisionCreateFunc(BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE.ordinal(), i, createFunc);
/* 686:    */    }
/* 687:    */    
/* 688:688 */    for (int i = 0; i < BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal(); i++) {
/* 689:689 */      dispatcher.registerCollisionCreateFunc(i, BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE.ordinal(), createFunc);
/* 690:    */    }
/* 691:    */  }
/* 692:    */  
/* 693:    */  public static class CreateFunc extends CollisionAlgorithmCreateFunc {
/* 694:694 */    private final ObjectPool<GImpactCollisionAlgorithm> pool = ObjectPool.get(GImpactCollisionAlgorithm.class);
/* 695:    */    
/* 696:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 697:    */    {
/* 698:698 */      GImpactCollisionAlgorithm algo = (GImpactCollisionAlgorithm)this.pool.get();
/* 699:699 */      algo.init(ci, body0, body1);
/* 700:700 */      return algo;
/* 701:    */    }
/* 702:    */    
/* 703:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 704:    */    {
/* 705:705 */      this.pool.release((GImpactCollisionAlgorithm)algo);
/* 706:    */    }
/* 707:    */  }
/* 708:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */