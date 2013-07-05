/*     */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.dynamics.DynamicsWorld;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.Element;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.physics.ClosestConvexResultCallbackExt;
/*     */ import org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.common.data.world.SectorNotFoundException;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkClientChannel;
/*     */ import org.schema.game.network.objects.remote.RemoteMissileUpdate;
/*     */ import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import org.schema.schine.network.ControllerInterface;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import org.schema.schine.network.server.ServerState;
/*     */ 
/*     */ public abstract class ln
/*     */   implements lb, zL
/*     */ {
/*     */   private float jdField_b_of_type_Float;
/*     */   protected final ArrayList a;
/*     */   protected final ArrayList b;
/*     */   private float jdField_c_of_type_Float;
/*     */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/*     */   lb jdField_a_of_type_Lb;
/*     */   private ArrayList jdField_c_of_type_JavaUtilArrayList;
/*     */   private float jdField_d_of_type_Float;
/*     */   float jdField_a_of_type_Float;
/*     */   Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*     */   private SphereShape jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape;
/*     */   private PairCachingGhostObjectUncollidable jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable;
/*     */   private PairCachingGhostObjectUncollidable jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable;
/*     */   boolean jdField_a_of_type_Boolean;
/*     */   private int jdField_b_of_type_Int;
/*     */   private SphereShape jdField_b_of_type_ComBulletphysicsCollisionShapesSphereShape;
/*     */   final Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   short jdField_a_of_type_Short;
/*     */   int jdField_a_of_type_Int;
/*     */   final boolean jdField_b_of_type_Boolean;
/*     */   private PhysicsDataContainer jdField_a_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
/*     */   private Transform jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private final Transform jdField_d_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private Transform e;
/*     */   private Transform f;
/*     */   private Transform g;
/*     */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*     */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*     */   private int jdField_c_of_type_Int;
/*     */   private Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*     */   private final ArrayList jdField_d_of_type_JavaUtilArrayList;
/*     */   private int jdField_d_of_type_Int;
/*     */   private long jdField_a_of_type_Long;
/*     */   private long jdField_b_of_type_Long;
/*     */ 
/*     */   public ln(StateInterface paramStateInterface)
/*     */   {
/*  65 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  66 */     this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  67 */     new ArrayList();
/*     */ 
/*  69 */     this.jdField_c_of_type_Float = 1.0F;
/*  70 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/*  72 */     this.jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/*     */ 
/*  74 */     this.jdField_a_of_type_Float = 100.0F;
/*  75 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  82 */     this.jdField_a_of_type_Boolean = true;
/*  83 */     this.jdField_b_of_type_Int = 1;
/*     */ 
/*  89 */     this.jdField_a_of_type_Short = -1231;
/*     */ 
/*  97 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/* 316 */     this.jdField_d_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 317 */     this.e = new Transform();
/* 318 */     this.f = new Transform();
/* 319 */     this.g = new Transform();
/* 320 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 321 */     this.jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/* 322 */     this.jdField_c_of_type_Int = -1;
/* 323 */     this.jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/* 734 */     this.jdField_d_of_type_JavaUtilArrayList = new ArrayList();
/*     */ 
/*  99 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/* 100 */     this.jdField_b_of_type_Boolean = (paramStateInterface instanceof ServerState);
/* 101 */     this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer = new PhysicsDataContainer();
/* 102 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 103 */     this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 104 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 133 */     return this.jdField_c_of_type_Float;
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 139 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final Vector3f a()
/*     */   {
/* 146 */     return this.jdField_a_of_type_JavaxVecmathVector3f;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 153 */     return Math.min(5.0F, this.jdField_a_of_type_Float);
/*     */   }
/*     */ 
/*     */   public final Transform a()
/*     */   {
/* 180 */     return this.jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/*     */   }
/*     */ 
/*     */   public float getMass()
/*     */   {
/* 189 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public PhysicsDataContainer getPhysicsDataContainer()
/*     */   {
/* 197 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/* 205 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   }
/*     */ 
/*     */   public void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final lb a()
/*     */   {
/* 231 */     return this.jdField_a_of_type_Lb;
/*     */   }
/*     */ 
/*     */   public final float c()
/*     */   {
/* 240 */     return this.jdField_b_of_type_Float;
/*     */   }
/*     */ 
/*     */   public void initPhysics()
/*     */   {
/* 254 */     this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable = new PairCachingGhostObjectUncollidable(getPhysicsDataContainer());
/*     */ 
/* 256 */     this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/* 259 */     this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable = new PairCachingGhostObjectUncollidable(getPhysicsDataContainer());
/*     */ 
/* 261 */     this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/* 264 */     this.jdField_b_of_type_ComBulletphysicsCollisionShapesSphereShape = new SphereShape(this.jdField_c_of_type_Float);
/* 265 */     this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape = new SphereShape(0.5F);
/*     */ 
/* 268 */     this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape.setMargin(0.1F);
/* 269 */     this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionShape(this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape);
/* 270 */     this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionShape(this.jdField_b_of_type_ComBulletphysicsCollisionShapesSphereShape);
/*     */ 
/* 275 */     this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setUserPointer(null);
/*     */ 
/* 278 */     getPhysicsDataContainer().setObject(this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable);
/* 279 */     getPhysicsDataContainer().setShape(this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape);
/*     */ 
/* 281 */     getPhysicsDataContainer().updatePhysical();
/*     */ 
/* 283 */     this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.getCollisionFlags() | 0x4);
/*     */ 
/* 287 */     if (this.jdField_b_of_type_Boolean)
/*     */     {
/* 290 */       f();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/* 295 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   private void f()
/*     */   {
/* 304 */     if (this.jdField_b_of_type_Boolean) {
/* 305 */       this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 306 */       this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 307 */       a().addObject(getPhysicsDataContainer().getObject(), (short)-9, (short)8);
/*     */ 
/* 309 */       a().addObject(this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable, (short)-9, (short)16);
/* 310 */       this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 311 */       this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, q paramq)
/*     */   {
/* 326 */     this.jdField_c_of_type_Int = -1;
/*     */ 
/* 329 */     Object localObject2 = null; if (this.jdField_a_of_type_Int == paramInt) {
/* 330 */       this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); return;
/*     */     }
/* 332 */     int i = 0;
/* 333 */     float f1 = 0.0F;
/*     */     Object localObject1;
/* 334 */     if (this.jdField_c_of_type_Int != paramInt) {
/* 335 */       i = 1;
/* 336 */       localObject1 = new q();
/*     */       q localq;
/* 338 */       if (this.jdField_b_of_type_Boolean)
/*     */       {
/* 340 */         if ((
/* 340 */           localObject2 = ((vg)getState()).a().getSector(this.jdField_a_of_type_Int)) != null)
/*     */         {
/* 341 */           localq = ((mx)localObject2).a;
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 348 */         if ((
/* 348 */           localObject2 = (mv)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_a_of_type_Int)) == null)
/*     */         {
/* 351 */           System.err.println("Exception: Sector Not Found: " + this.jdField_a_of_type_Int + " for " + this + "; from sector: " + paramInt);
/* 352 */           this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 353 */           this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.origin.set(10000.0F, 10000.0F, 1000.0F);
/* 354 */           return;
/*     */         }
/* 356 */         localq = ((mv)localObject2).a();
/*     */       }
/*     */ 
/* 359 */       localObject2 = mJ.a(paramq, new q());
/* 360 */       if (mI.a(paramq))
/*     */       {
/* 362 */         f1 = (float)((System.currentTimeMillis() - this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime()) % 1200000L) / 
/* 362 */           1200000.0F;
/* 363 */         if (!mI.a(paramq)) {
/* 364 */           f1 = 0.0F;
/*     */         }
/*     */ 
/* 369 */         ((q)localObject2).a(16);
/* 370 */         ((q)localObject2).a(8, 8, 8);
/* 371 */         ((q)localObject2).c(paramq);
/* 372 */         ((q)localObject1).b((q)localObject2);
/*     */       } else {
/* 374 */         f1 = 0.0F;
/*     */       }
/*     */ 
/* 378 */       (
/* 379 */         localObject2 = new q())
/* 379 */         .a(localq, paramq);
/*     */ 
/* 383 */       this.jdField_b_of_type_JavaxVecmathVector3f.set(((q)localObject2).jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject2).jdField_b_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject2).jdField_c_of_type_Int * Universe.getSectorSizeWithMargin());
/*     */ 
/* 388 */       this.jdField_c_of_type_JavaxVecmathVector3f.set(((q)localObject1).jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject1).jdField_b_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject1).jdField_c_of_type_Int * Universe.getSectorSizeWithMargin());
/*     */ 
/* 393 */       this.f.setIdentity();
/*     */ 
/* 395 */       if (((q)localObject1).a() > 0.0F)
/*     */       {
/* 398 */         this.f.origin.add(this.jdField_c_of_type_JavaxVecmathVector3f);
/* 399 */         this.f.basis.rotX(6.283186F * f1);
/*     */ 
/* 402 */         this.jdField_d_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f);
/* 403 */         this.f.origin.add(this.jdField_d_of_type_JavaxVecmathVector3f);
/*     */ 
/* 406 */         this.f.basis.transform(this.f.origin);
/*     */       }
/*     */       else
/*     */       {
/* 411 */         this.f.basis.setIdentity();
/*     */ 
/* 413 */         this.f.origin.set(this.jdField_b_of_type_JavaxVecmathVector3f);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 420 */     if ((this.jdField_c_of_type_Int != paramInt) || (!this.e.equals(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform))) {
/* 421 */       this.g.set(this.f);
/*     */ 
/* 423 */       (
/* 424 */         localObject1 = new Transform())
/* 424 */         .setIdentity();
/* 425 */       float f2 = -6.283186F * f1;
/* 426 */       ((Transform)localObject1).basis.rotX(f2);
/* 427 */       d.a((Transform)localObject1, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/* 432 */       d.a(this.g, (Transform)localObject1);
/*     */ 
/* 438 */       this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 439 */       this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.g.origin);
/*     */ 
/* 444 */       this.e.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */     }
/*     */ 
/* 451 */     if (i != 0)
/* 452 */       this.jdField_c_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 459 */     if (this.jdField_a_of_type_Int == ((ct)getState()).a())
/*     */     {
/*     */       Object localObject1;
/* 461 */       if (((
/* 461 */         localObject1 = (ct)getState())
/* 461 */         .a() != null) && (((ct)localObject1).a().a() != null)) {
/* 462 */         System.err.println("[CLIENT] Adding Trail for missile " + this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 463 */         Transform localTransform = this.jdField_d_of_type_ComBulletphysicsLinearmathTransform; synchronized ((localObject1 = ((ct)localObject1).a().a()).jdField_a_of_type_JavaUtilArrayList) { ((ez)localObject1).jdField_a_of_type_JavaUtilArrayList.add(new eA(localTransform, true)); return; }
/*     */       }
/* 465 */       System.err.println("[CLIENT] Cannot add Trail for missile (drawer not initialized)");
/*     */ 
/* 467 */       return;
/* 468 */     }System.err.println("[CLIENT] NOT ADDING TRAIL FOR " + this + "; not in sector");
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*     */     Object localObject1;
/* 474 */     if (((
/* 474 */       localObject1 = (ct)getState())
/* 474 */       .a() != null) && (((ct)localObject1).a().a() != null)) {
/* 475 */       Transform localTransform = this.jdField_d_of_type_ComBulletphysicsLinearmathTransform; synchronized ((localObject1 = ((ct)localObject1).a().a()).jdField_a_of_type_JavaUtilArrayList) { ((ez)localObject1).jdField_a_of_type_JavaUtilArrayList.add(new eA(localTransform, false)); }
/* 476 */       System.err.println("[CLIENT] Removing Trail for missile");
/*     */     }
/*     */   }
/*     */ 
/*     */   private zM a() {
/* 481 */     ln localln = this; long l1 = System.currentTimeMillis(); mx localmx = ((vg)localln.getState()).a().getSector(localln.jdField_a_of_type_Int);
/*     */     long l2;
/* 481 */     if ((l2 = System.currentTimeMillis() - l1) > 5L) System.err.println("[SERVER][STO] WARNING: Loading sector " + localln.jdField_a_of_type_Int + " for " + localln + " took " + l2 + " ms"); if (localmx == null) throw new SectorNotFoundException(localln.jdField_a_of_type_Int); return (PhysicsExt)(this.jdField_b_of_type_Boolean ? localmx : (ct)localln.getState()).a();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 488 */     if (this.jdField_b_of_type_Boolean) {
/* 489 */       a().removeObject(getPhysicsDataContainer().getObject());
/* 490 */       a().removeObject(this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(ClosestConvexResultCallbackExt paramClosestConvexResultCallbackExt)
/*     */   {
/* 514 */     ObjectArrayList localObjectArrayList = this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.getOverlappingPairCache().getOverlappingPairArray();
/*     */ 
/* 517 */     Sendable localSendable = null;
/* 518 */     if (paramClosestConvexResultCallbackExt.userData == this.jdField_a_of_type_Lb)
/*     */       return;
/*     */     int i;
/*     */     Object localObject1;
/*     */     Object localObject2;
/* 520 */     if (paramClosestConvexResultCallbackExt.userData != null) {
/* 521 */       i = 0;
/* 522 */       if ((this.jdField_a_of_type_Lb instanceof SegmentController))
/*     */       {
/* 524 */         if (((
/* 524 */           localObject1 = (SegmentController)this.jdField_a_of_type_Lb)
/* 524 */           .getDockingController().a() != null) && (((SegmentController)localObject1).getDockingController().a().to.a().a() == (SegmentController)paramClosestConvexResultCallbackExt.userData)) {
/* 525 */           i = 1;
/*     */         }
/* 527 */         if (i == 0) {
/* 528 */           for (localObject2 = ((SegmentController)localObject1).getDockingController().a().iterator(); ((Iterator)localObject2).hasNext(); ) {
/* 529 */             if (((ElementDocking)((Iterator)localObject2).next()).from
/* 529 */               .a().a() == (SegmentController)paramClosestConvexResultCallbackExt.userData) {
/* 530 */               i = 1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 535 */       if (i == 0)
/* 536 */         localSendable = (Sendable)paramClosestConvexResultCallbackExt.userData;
/*     */     }
/*     */     else {
/* 539 */       System.err.println("Exception: warning: callback userdata is null (probably not hit with a segmentcontroller)");
/*     */     }
/* 541 */     if (localSendable != null)
/* 542 */       for (i = 0; i < localObjectArrayList.size(); i++)
/*     */       {
/* 550 */         if (((
/* 550 */           localObject2 = (
/* 545 */           localObject1 = (BroadphasePair)localObjectArrayList.get(i)).pProxy0.clientObject != 
/* 545 */           this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable ? ((BroadphasePair)localObject1).pProxy0 : ((BroadphasePair)localObject1).pProxy1).clientObject != null) && 
/* 550 */           ((((BroadphaseProxy)localObject2).clientObject instanceof CollisionObject)) && (this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.checkCollideWith((CollisionObject)((BroadphaseProxy)localObject2).clientObject))) {
/* 551 */           paramClosestConvexResultCallbackExt = (CollisionObject)((BroadphaseProxy)localObject2).clientObject;
/*     */ 
/* 553 */           System.err.println(this + " HIT BOADPHASE " + ((BroadphasePair)localObject1).pProxy0.clientObject + " and " + ((BroadphasePair)localObject1).pProxy1.clientObject);
/*     */ 
/* 557 */           if ((paramClosestConvexResultCallbackExt.getUserPointer() != null) && ((paramClosestConvexResultCallbackExt.getUserPointer() instanceof Integer))) {
/* 558 */             paramClosestConvexResultCallbackExt = ((Integer)paramClosestConvexResultCallbackExt.getUserPointer()).intValue();
/*     */ 
/* 560 */             paramClosestConvexResultCallbackExt = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramClosestConvexResultCallbackExt);
/* 561 */             localObject1 = this.jdField_a_of_type_Lb;
/* 562 */             if ((paramClosestConvexResultCallbackExt == localObject1) && 
/* 563 */               (this.jdField_d_of_type_Float < 5.0F)) {
/* 564 */               System.err.println("[MISSILE]: " + this + " Cannot hit owner: " + this.jdField_d_of_type_Float + "/5");
/*     */             }
/* 568 */             else if (paramClosestConvexResultCallbackExt != null) {
/* 569 */               System.err.println("[MISSILE]: " + this + " REGISTERED HIT: " + paramClosestConvexResultCallbackExt);
/* 570 */               this.jdField_c_of_type_JavaUtilArrayList.add(paramClosestConvexResultCallbackExt);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     Iterator localIterator;
/* 576 */     if (localSendable != null)
/*     */     {
/* 579 */       for (localIterator = this.jdField_c_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); ) {
/* 580 */         if (((
/* 580 */           localObject1 = (Sendable)localIterator.next()) instanceof C))
/*     */         {
/* 581 */           ((C)localObject1).handleHitMissile(this, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 582 */           this.jdField_a_of_type_Boolean = false;
/*     */         }
/*     */       }
/*     */     }
/* 586 */     this.jdField_c_of_type_JavaUtilArrayList.clear();
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 604 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void a(float paramFloat)
/*     */   {
/* 611 */     this.jdField_c_of_type_Float = paramFloat;
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt)
/*     */   {
/* 618 */     this.jdField_b_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final void a(Vector3f paramVector3f)
/*     */   {
/* 625 */     this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/*     */   }
/*     */ 
/*     */   public final void b(float paramFloat)
/*     */   {
/* 632 */     this.jdField_a_of_type_Float = paramFloat;
/*     */   }
/*     */ 
/*     */   public final void a(lb paramlb)
/*     */   {
/* 648 */     this.jdField_a_of_type_Lb = paramlb;
/*     */   }
/*     */ 
/*     */   public final void c(float paramFloat)
/*     */   {
/* 655 */     this.jdField_b_of_type_Float = paramFloat;
/*     */   }
/*     */ 
/*     */   protected final void a(Transform paramTransform) {
/* 659 */     if (this.jdField_b_of_type_Boolean)
/* 660 */       this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(paramTransform);
/* 661 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(paramTransform);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 724 */     return "Missile(" + this.jdField_a_of_type_Short + " s[" + this.jdField_a_of_type_Int + "] " + this.jdField_a_of_type_Lb + ")";
/*     */   }
/*     */ 
/*     */   public abstract void b(xq paramxq);
/*     */ 
/*     */   public void a(xq paramxq)
/*     */   {
/*     */     Object localObject1;
/* 748 */     if (this.jdField_a_of_type_Boolean) {
/*     */       try {
/*     */         try {
/* 751 */           localObject1 = this; if (this.jdField_a_of_type_Lb == null) System.err.println("[MISSILE] Exception: OWNER IS NULL"); Object localObject2;
/*     */           try { localObject2 = ((mF)((ln)localObject1).jdField_a_of_type_Lb).getPhysicsState(); } catch (SectorNotFoundException localSectorNotFoundException) { localObject2 = null; localSectorNotFoundException.printStackTrace(); System.err.println("[MISSILE] Exception CATCHED: sectors are not kept alive for missiles. terminate taht missile"); ((ln)localObject1).jdField_a_of_type_Boolean = false; break label335; } if (localObject2 == null) { System.err.println("[MISSILE] Exception: MISSILE NOT ABLE TO RETRIEVE PHYSICS FOR SECTOR " + ((ln)localObject1).jdField_a_of_type_Int); if (((ln)localObject1).jdField_b_of_type_Boolean) ((ln)localObject1).jdField_a_of_type_Boolean = false;  } else { localObject2 = (PhysicsExt)((zS)localObject2).a(); ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity(); ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform); Object localObject3 = new Vector3f(((ln)localObject1).jdField_a_of_type_JavaxVecmathVector3f); ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject3); ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject3); if (((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.determinant() != 0.0F) { if ((!jdField_c_of_type_Boolean) && (localObject2 == null)) throw new AssertionError(); if ((!jdField_c_of_type_Boolean) && (((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform == null)) throw new AssertionError(); if ((!jdField_c_of_type_Boolean) && (((ln)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable == null)) throw new AssertionError(); (localObject3 = new ClosestConvexResultCallbackExt(((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin)).checkHasHitOnly = true; ((ln)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.convexSweepTest(((ln)localObject1).jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape, ((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform, ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform, (CollisionWorld.ConvexResultCallback)localObject3, ((PhysicsExt)localObject2).getDynamicsWorld().getDispatchInfo().allowedCcdPenetration); if (((ClosestConvexResultCallbackExt)localObject3).hasHit()) try { ((ln)localObject1).a((ClosestConvexResultCallbackExt)localObject3); } catch (Exception localException) { localException.printStackTrace(); }   } else {
/* 751 */               throw new IllegalStateException("[MISSILE] WORLD TRANSFORM IS STRANGE OR PHYSICS NOT INITIALIZED");
/*     */             } }
/* 753 */           label335: this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); } catch (IOException localIOException) {
/* 754 */           (
/* 755 */             localObject1 = 
/* 760 */             localIOException).printStackTrace();
/* 756 */           throw new ErrorDialogException(((IOException)localObject1).getMessage()); } catch (InterruptedException localInterruptedException) {
/* 757 */           (
/* 758 */             localObject1 = localInterruptedException)
/* 758 */             .printStackTrace();
/* 759 */           throw new ErrorDialogException(((InterruptedException)localObject1).getMessage()); }  } catch (IllegalStateException localIllegalStateException) { localIllegalStateException
/* 763 */           .printStackTrace();
/*     */ 
/* 765 */         b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 770 */     float f1 = 0.0F; this.jdField_d_of_type_Float += paramxq.a();
/*     */ 
/* 774 */     if ((this.jdField_a_of_type_Boolean) && (this.jdField_d_of_type_Float > this.jdField_a_of_type_Float)) {
/* 775 */       this.jdField_a_of_type_Boolean = false;
/* 776 */       System.err.println("[SERVER] MISSILE DIED FROM LIFETIME");
/*     */     }
/*     */ 
/* 779 */     if (!this.jdField_a_of_type_Boolean) {
/* 780 */       System.err.println("[SERVER] Deleting missile " + this);
/* 781 */       this.jdField_b_of_type_JavaUtilArrayList.add(new lq(this.jdField_a_of_type_Short)); return;
/*     */     }
/*     */ 
/* 784 */     if (this.jdField_a_of_type_Int != this.jdField_d_of_type_Int) {
/* 785 */       (
/* 786 */         localObject1 = new lt(this.jdField_a_of_type_Short)).jdField_a_of_type_JavaxVecmathVector3f
/* 786 */         .set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 787 */       ((lt)localObject1).jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 788 */       ((lt)localObject1).jdField_a_of_type_Int = this.jdField_a_of_type_Int;
/* 789 */       this.jdField_a_of_type_JavaUtilArrayList.add(localObject1);
/* 790 */       this.jdField_d_of_type_Int = this.jdField_a_of_type_Int;
/*     */ 
/* 793 */       return;
/* 794 */     }if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 500L) {
/* 795 */       (
/* 796 */         localObject1 = new ls(this.jdField_a_of_type_Short)).jdField_a_of_type_JavaxVecmathVector3f
/* 796 */         .set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 797 */       this.jdField_a_of_type_JavaUtilArrayList.add(localObject1);
/* 798 */       this.jdField_a_of_type_Long = (System.currentTimeMillis() + Universe.getRandom().nextInt(30));
/*     */     }
/* 800 */     if (((this instanceof lp)) && 
/* 801 */       (System.currentTimeMillis() - this.jdField_b_of_type_Long > 500L)) {
/* 802 */       (
/* 803 */         localObject1 = new lr(this.jdField_a_of_type_Short)).jdField_a_of_type_JavaxVecmathVector3f
/* 803 */         .set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 804 */       this.jdField_a_of_type_JavaUtilArrayList.add(localObject1);
/* 805 */       this.jdField_b_of_type_Long = (System.currentTimeMillis() + Universe.getRandom().nextInt(30));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b(Vector3f paramVector3f)
/*     */   {
/* 818 */     if ((!jdField_c_of_type_Boolean) && (!(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof vg))) throw new AssertionError();
/* 819 */     Vector3f localVector3f1 = new Vector3f(paramVector3f);
/*     */     mx localmx1;
/* 821 */     if ((
/* 821 */       localmx1 = ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().getSector(this.jdField_a_of_type_Int)) != null)
/*     */     {
/* 822 */       q localq1 = localmx1.a;
/* 823 */       int i = -1;
/* 824 */       Vector3f localVector3f2 = new Vector3f(localVector3f1);
/* 825 */       q localq2 = new q();
/* 826 */       boolean bool = mI.a(localmx1.a);
/* 827 */       for (int j = 0; j < Element.DIRECTIONSi.length; j++)
/*     */       {
/*     */         Object localObject;
/* 828 */         (
/* 829 */           localObject = new q(Element.DIRECTIONSi[j]))
/* 829 */           .a(localq1);
/*     */         Transform localTransform;
/* 830 */         (
/* 831 */           localTransform = new Transform())
/* 831 */           .setIdentity();
/*     */ 
/* 833 */         if (bool) {
/* 834 */           Universe.calcSecPos(localq1, (q)localObject, this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime(), System.currentTimeMillis(), localTransform);
/*     */         } else {
/* 836 */           localTransform.origin.set(Element.DIRECTIONSi[j].jdField_a_of_type_Int, Element.DIRECTIONSi[j].jdField_b_of_type_Int, Element.DIRECTIONSi[j].jdField_c_of_type_Int);
/* 837 */           localTransform.origin.scale(Universe.getSectorSizeWithMargin());
/*     */         }
/*     */ 
/* 840 */         (
/* 841 */           localObject = new Vector3f())
/* 841 */           .sub(localVector3f1, localTransform.origin);
/*     */ 
/* 845 */         if (((Vector3f)localObject).lengthSquared() < localVector3f2.lengthSquared()) {
/* 846 */           localVector3f2.set((Tuple3f)localObject);
/* 847 */           i = j;
/*     */         }
/*     */       }
/*     */ 
/* 851 */       if (i >= 0) {
/* 852 */         localq2.b(localq1);
/* 853 */         localq2.a(Element.DIRECTIONSi[i]);
/*     */       }
/*     */       else {
/* 856 */         return;
/*     */       }
/*     */       try
/*     */       {
/* 860 */         if (((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().isSectorLoaded(localq2)) {
/* 861 */           mx localmx2 = ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().getSector(localq2);
/* 862 */           L.a(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface, localmx1, localmx2, localVector3f1, paramVector3f);
/* 863 */           c();
/* 864 */           this.jdField_a_of_type_Int = localmx2.a();
/* 865 */           f();
/*     */ 
/* 868 */           return;
/*     */         }
/*     */ 
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 876 */         localException.printStackTrace();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 879 */       System.err.println("[SERVER][PROJECTILE] Stopping projectile: out of loaded sector range");
/*     */     }
/*     */ 
/* 882 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final ArrayList a()
/*     */   {
/* 887 */     return this.jdField_d_of_type_JavaUtilArrayList;
/*     */   }
/*     */   public final void a(t paramt) {
/* 890 */     for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/* 891 */       lw locallw = (lw)this.jdField_a_of_type_JavaUtilArrayList.get(i);
/* 892 */       paramt.a().missileUpdateBuffer.add(new RemoteMissileUpdate(locallw, this.jdField_b_of_type_Boolean));
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(t paramt)
/*     */   {
/* 898 */     for (int i = 0; i < this.jdField_b_of_type_JavaUtilArrayList.size(); i++) {
/* 899 */       lw locallw = (lw)this.jdField_b_of_type_JavaUtilArrayList.get(i);
/* 900 */       paramt.a().missileUpdateBuffer.add(new RemoteMissileUpdate(locallw, this.jdField_b_of_type_Boolean));
/*     */ 
/* 902 */       System.err.println("[SERVER] sent missile update BB " + locallw);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final short a()
/*     */   {
/* 910 */     return this.jdField_a_of_type_Short;
/*     */   }
/*     */ 
/*     */   public final void a(short paramShort)
/*     */   {
/* 917 */     this.jdField_a_of_type_Short = paramShort;
/*     */   }
/*     */ 
/*     */   public final int b()
/*     */   {
/* 924 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void b(int paramInt)
/*     */   {
/* 931 */     this.jdField_a_of_type_Int = paramInt;
/* 932 */     this.jdField_d_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final Transform b()
/*     */   {
/* 941 */     return this.jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   }
/*     */ 
/*     */   public void a(lu paramlu) {
/* 945 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 946 */     this.jdField_a_of_type_Short = paramlu.jdField_a_of_type_Short;
/* 947 */     this.jdField_a_of_type_Int = paramlu.jdField_b_of_type_Int;
/* 948 */     if ((!jdField_c_of_type_Boolean) && (paramlu.jdField_a_of_type_Byte != a())) throw new AssertionError(paramlu.jdField_a_of_type_Byte + " --- " + a());
/* 949 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramlu.jdField_b_of_type_JavaxVecmathVector3f);
/* 950 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.set(paramlu.jdField_a_of_type_JavaxVecmathVector3f);
/*     */   }
/*     */ 
/*     */   public abstract byte a();
/*     */ 
/*     */   public final boolean b()
/*     */   {
/* 957 */     return !this.jdField_a_of_type_JavaUtilArrayList.isEmpty();
/*     */   }
/*     */ 
/*     */   public final boolean c() {
/* 961 */     return !this.jdField_b_of_type_JavaUtilArrayList.isEmpty();
/*     */   }
/*     */ 
/*     */   public final void e() {
/* 965 */     this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 966 */     this.jdField_b_of_type_JavaUtilArrayList.clear();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ln
 * JD-Core Version:    0.6.2
 */