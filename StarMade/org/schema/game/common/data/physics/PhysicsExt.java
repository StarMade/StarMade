/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.BulletGlobals;
/*     */ import com.bulletphysics.collision.broadphase.DbvtBroadphase;
/*     */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*     */ import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.GhostPairCallback;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*     */ import com.bulletphysics.dynamics.DynamicsWorld;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
/*     */ import com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import d;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jv;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import zI;
/*     */ import zL;
/*     */ import zM;
/*     */ import zR;
/*     */ import zS;
/*     */ 
/*     */ public class PhysicsExt extends zM
/*     */ {
/*     */   private AxisSweep3Ext axisSweep3Ext;
/*     */   private DbvtBroadphase dbvtBroadphase;
/*     */ 
/*     */   public PhysicsExt(zS paramzS)
/*     */   {
/*  44 */     super(paramzS);
/*     */   }
/*     */ 
/*     */   public RigidBody getBodyFromShape(CollisionShape paramCollisionShape, float paramFloat, Transform paramTransform)
/*     */   {
/*  64 */     int i = paramFloat != 0.0F ? 1 : 0;
/*     */ 
/*  66 */     assert ((!(paramCollisionShape instanceof CompoundShape)) || (((CompoundShape)paramCollisionShape).getNumChildShapes() != 0)) : "tried to add empty compound shape";
/*     */ 
/*  68 */     Vector3f localVector3f = new Vector3f();
/*  69 */     if (i != 0)
/*     */     {
/*  71 */       paramCollisionShape.calculateLocalInertia(paramFloat, localVector3f);
/*     */     }
/*     */ 
/*  74 */     paramCollisionShape.setMargin(0.13F);
/*     */ 
/*  77 */     paramTransform = new zI(paramTransform);
/*     */ 
/*  86 */     paramTransform = new RigidBodyConstructionInfo(paramFloat, paramTransform, paramCollisionShape, localVector3f);
/*     */ 
/*  95 */     assert (!(paramCollisionShape instanceof CubeShape));
/*     */ 
/*  97 */     if ((paramCollisionShape instanceof CubesCompoundShape))
/*  98 */       paramCollisionShape = new RigidBodyExt(((CubesCompoundShape)paramCollisionShape).getSegmentController(), paramTransform);
/*     */     else {
/* 100 */       paramCollisionShape = new RigidBody(paramTransform);
/*     */     }
/*     */ 
/* 110 */     if (paramFloat > 0.0F) {
/* 111 */       paramCollisionShape.setRestitution(0.1F);
/*     */     }
/*     */     else {
/* 114 */       paramCollisionShape.setRestitution(0.0F);
/*     */     }
/*     */ 
/* 123 */     paramCollisionShape.setFriction(0.7F);
/*     */ 
/* 131 */     if (paramFloat > 0.0F)
/* 132 */       paramCollisionShape.setDamping(getState().b(), getState().c());
/*     */     else {
/* 134 */       paramCollisionShape.setDamping(0.0F, 0.0F);
/*     */     }
/*     */ 
/* 137 */     paramCollisionShape.setMassProps(paramFloat, localVector3f);
/* 138 */     if (paramFloat > 0.0F) {
/* 139 */       paramCollisionShape.updateInertiaTensor();
/*     */     }
/*     */ 
/* 142 */     if (i == 0) {
/* 143 */       paramCollisionShape.setCollisionFlags(paramCollisionShape.getCollisionFlags() | 0x2);
/*     */     }
/*     */ 
/* 180 */     return paramCollisionShape;
/*     */   }
/*     */ 
/*     */   public void initPhysics()
/*     */   {
/* 213 */     setCollisionConfiguration(new CubeCollissionConfiguration());
/*     */ 
/* 217 */     setDispatcher(new CollisionDispatcherExt(getCollisionConfiguration()));
/*     */ 
/* 224 */     new Vector3f(-2000.0F, -2000.0F, -2000.0F);
/* 225 */     new Vector3f(2000.0F, 2000.0F, 2000.0F);
/*     */ 
/* 243 */     this.dbvtBroadphase = new DbvtBroadphaseExt(new HashedOverlappingPairCacheExt());
/* 244 */     setOverlappingPairCache(this.dbvtBroadphase);
/*     */ 
/* 251 */     SequentialImpulseConstraintSolverExt localSequentialImpulseConstraintSolverExt = new SequentialImpulseConstraintSolverExt();
/* 252 */     setSolver(localSequentialImpulseConstraintSolverExt);
/*     */ 
/* 257 */     setDynamicsWorld(new ModifiedDynamicsWorld(getDispatcher(), getOverlappingPairCache(), getSolver(), getCollisionConfiguration(), getState()));
/*     */ 
/* 266 */     GImpactCollisionAlgorithm.registerAlgorithm((CollisionDispatcher)this.dynamicsWorld.getDispatcher());
/*     */ 
/* 268 */     getDynamicsWorld().setGravity(new Vector3f(0.0F, -0.0F, 0.0F));
/*     */ 
/* 272 */     getDynamicsWorld().getPairCache().setInternalGhostPairCallback(new GhostPairCallback());
/*     */ 
/* 275 */     BulletGlobals.setContactBreakingThreshold(0.1F);
/* 276 */     getDynamicsWorld().getSolverInfo().linearSlop = 1.0E-006F;
/*     */   }
/*     */ 
/*     */   public void orientate(zL paramzL, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, float paramFloat)
/*     */   {
/* 284 */     if (((paramzL instanceof SegmentController)) && (((SegmentController)paramzL).getDockingController().b()))
/*     */     {
/* 286 */       paramFloat = ((CubeShape)paramzL.getPhysicsDataContainer().getShapeChild().childShape)
/* 286 */         .getSegmentBuffer().a();
/*     */ 
/* 289 */       paramzL = paramFloat.getDockingController().a().to.a().a();
/* 290 */       (
/* 291 */         localObject = (zR)threadLocal.get()).h
/* 291 */         .set(paramVector3f3);
/* 292 */       ((zR)localObject).h.normalize();
/* 293 */       ((zR)localObject).g.set(paramVector3f1);
/* 294 */       ((zR)localObject).g.normalize();
/* 295 */       ((zR)localObject).i.set(paramVector3f2);
/* 296 */       ((zR)localObject).i.normalize();
/*     */ 
/* 298 */       paramzL.getWorldTransformInverse().basis.transform(((zR)localObject).h);
/* 299 */       paramzL.getWorldTransformInverse().basis.transform(((zR)localObject).g);
/* 300 */       paramzL.getWorldTransformInverse().basis.transform(((zR)localObject).i);
/*     */ 
/* 303 */       paramzL = new Transform();
/* 304 */       GlUtil.a(((zR)localObject).g, paramzL);
/* 305 */       GlUtil.d(((zR)localObject).i, paramzL);
/* 306 */       GlUtil.c(((zR)localObject).h, paramzL);
/*     */ 
/* 308 */       paramVector3f3 = new Quat4f();
/* 309 */       d.a(paramzL.basis, paramVector3f3);
/* 310 */       paramFloat.getDockingController().a.set(paramVector3f3);
/*     */       return;
/*     */     }
/*     */     RigidBody localRigidBody;
/* 316 */     if (((
/* 316 */       localRigidBody = (RigidBody)paramzL.getPhysicsDataContainer().getObject()) == null) || 
/* 316 */       (localRigidBody.getCollisionFlags() == 1) || (paramzL.getMass() <= 0.0F)) {
/* 317 */       return;
/*     */     }
/* 319 */     zR localzR = (zR)threadLocal.get();
/*     */ 
/* 326 */     paramzL = paramzL.getPhysicsDataContainer().getCurrentPhysicsTransform();
/*     */ 
/* 329 */     GlUtil.c(localzR.a, paramzL);
/* 330 */     GlUtil.f(localzR.b, paramzL);
/* 331 */     GlUtil.e(localzR.j, paramzL);
/*     */ 
/* 335 */     localzR.h.set(paramVector3f3);
/* 336 */     localzR.h.normalize();
/* 337 */     localzR.g.set(paramVector3f1);
/* 338 */     localzR.g.normalize();
/* 339 */     localzR.i.set(paramVector3f2);
/* 340 */     localzR.i.normalize();
/*     */ 
/* 342 */     Object localObject = new Vector3f();
/* 343 */     paramzL = new Vector3f();
/* 344 */     paramVector3f3 = new Vector3f();
/* 345 */     ((Vector3f)localObject).sub(localzR.a, localzR.g);
/* 346 */     paramzL.sub(localzR.b, localzR.i);
/* 347 */     paramVector3f3.sub(localzR.j, localzR.h);
/*     */ 
/* 349 */     localzR.c.cross(localzR.a, localzR.g);
/* 350 */     localzR.c.normalize();
/* 351 */     localzR.d.cross(localzR.b, localzR.i);
/* 352 */     localzR.d.normalize();
/* 353 */     localzR.e.cross(localzR.j, localzR.h);
/* 354 */     localzR.e.normalize();
/*     */ 
/* 356 */     if ((((Vector3f)localObject).length() < 1.192093E-007F) && (paramzL.length() < 1.192093E-007F) && (paramVector3f3.length() < 1.192093E-007F)) {
/* 357 */       return;
/*     */     }
/*     */ 
/* 360 */     localzR.c.scale(((Vector3f)localObject).length());
/* 361 */     localzR.d.scale(paramzL.length());
/* 362 */     localzR.e.scale(paramVector3f3.length());
/*     */ 
/* 365 */     localzR.f.add(localzR.c, localzR.d);
/* 366 */     localzR.f.add(localzR.e);
/*     */ 
/* 369 */     if ((localzR.f.length() > 5.0E-005F) && (!localRigidBody.isActive()))
/*     */     {
/* 371 */       localRigidBody.activate();
/*     */     }
/* 373 */     if ((paramVector3f1 != null) && (paramVector3f1.length() > 0.0F) && (paramVector3f2 != null) && (paramVector3f2.length() > 0.0F))
/*     */     {
/* 378 */       paramzL = new Vector3f();
/* 379 */       localRigidBody.getAngularVelocity(paramzL);
/*     */ 
/* 383 */       localzR.f.scale(paramFloat);
/*     */ 
/* 386 */       if ((!Float.isNaN(localzR.f.x)) && (!Float.isNaN(localzR.f.y)) && (!Float.isNaN(localzR.f.z)))
/*     */       {
/* 388 */         localRigidBody.setAngularVelocity(localzR.f);
/*     */       }
/*     */ 
/* 393 */       localzR.k.set(localzR.f);
/*     */     }
/*     */   }
/*     */ 
/*     */   public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean1, SegmentController paramSegmentController1, SegmentController paramSegmentController2, boolean paramBoolean2, Segment paramSegment, boolean paramBoolean3)
/*     */   {
/* 405 */     (
/* 407 */       paramSegmentController1 = new CubeRayCastResult(paramVector3f1, paramVector3f2, paramSegmentController1, paramSegmentController2))
/* 407 */       .setRespectShields(paramBoolean2);
/* 408 */     paramSegmentController1.setLastHitSegment(paramSegment);
/* 409 */     paramSegmentController1.setIgnoereNotPhysical(paramBoolean3);
/* 410 */     assert (!paramSegmentController1.hasHit());
/* 411 */     ((ModifiedDynamicsWorld)this.dynamicsWorld).rayTest(paramVector3f1, paramVector3f2, paramSegmentController1);
/* 412 */     if (paramSegmentController1.collisionObject != null)
/*     */     {
/* 414 */       if (((
/* 414 */         paramVector3f1 = RigidBody.upcast(paramSegmentController1.collisionObject)) != null) && 
/* 415 */         (paramBoolean1) && (!paramVector3f1.isStaticObject()) && (!paramVector3f1.isKinematicObject())) {
/* 416 */         return null;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 421 */     return paramSegmentController1;
/*     */   }
/*     */ 
/*     */   public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, boolean paramBoolean)
/*     */   {
/* 426 */     if (SubsimplexRayCubesCovexCast.debug) {
/* 427 */       System.err.println("TESTING RAY CAST!!!");
/*     */     }
/* 429 */     ((ModifiedDynamicsWorld)this.dynamicsWorld).rayTest(paramVector3f1, paramVector3f2, paramCubeRayCastResult);
/* 430 */     if (paramCubeRayCastResult.collisionObject != null)
/*     */     {
/* 432 */       if (((
/* 432 */         paramVector3f1 = RigidBody.upcast(paramCubeRayCastResult.collisionObject)) != null) && 
/* 433 */         (paramBoolean) && (!paramVector3f1.isStaticObject()) && (!paramVector3f1.isKinematicObject()))
/*     */       {
/* 435 */         return null;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 440 */     return paramCubeRayCastResult;
/*     */   }
/*     */   public void cleanUp() {
/* 443 */     super.cleanUp();
/* 444 */     if (this.axisSweep3Ext != null) {
/* 445 */       this.axisSweep3Ext.cleanUp();
/*     */     }
/* 447 */     this.dynamicsWorld.setBroadphase(null);
/* 448 */     setOverlappingPairCache(null);
/* 449 */     setState(null);
/*     */   }
/*     */   public void softClean() {
/* 452 */     int i = getDynamicsWorld().getCollisionObjectArray().size();
/* 453 */     for (int j = 0; j < i; j++) {
/* 454 */       CollisionObject localCollisionObject = (CollisionObject)getDynamicsWorld().getCollisionObjectArray().get(j);
/* 455 */       System.err.println("WARNING: REMOVING EXCESS OBJECT FROM PHYSICS " + getState() + ": " + localCollisionObject);
/* 456 */       if ((localCollisionObject instanceof RigidBody))
/* 457 */         getDynamicsWorld().removeRigidBody((RigidBody)localCollisionObject);
/*     */       else {
/* 459 */         getDynamicsWorld().removeCollisionObject(localCollisionObject);
/*     */       }
/*     */     }
/* 462 */     if (this.dbvtBroadphase != null)
/*     */     {
/* 464 */       this.dbvtBroadphase = new DbvtBroadphaseExt(new HashedOverlappingPairCacheExt());
/*     */     }
/* 466 */     if (this.axisSweep3Ext != null)
/* 467 */       this.axisSweep3Ext.cleanUpReferences();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PhysicsExt
 * JD-Core Version:    0.6.2
 */