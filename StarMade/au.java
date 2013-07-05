/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.CannotBeControlledException;
/*     */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.controller.SegmentBufferManager;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.controller.elements.ManagerModule;
/*     */ import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*     */ import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class au extends af
/*     */ {
/*     */   private boolean jdField_d_of_type_Boolean;
/*  83 */   private final az jdField_a_of_type_Az = new az();
/*     */   private dr jdField_a_of_type_Dr;
/*     */   private al jdField_a_of_type_Al;
/*     */   private le jdField_a_of_type_Le;
/*     */   private CollisionWorld.ClosestRayResultCallback jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
/*     */   private q jdField_a_of_type_Q;
/*     */   private boolean e;
/*     */   private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*     */   private int jdField_a_of_type_Int;
/*     */   private Segment jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment;
/*     */   private boolean f;
/*     */   private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private long jdField_b_of_type_Long;
/*     */   private ju jdField_a_of_type_Ju;
/*     */   private int jdField_b_of_type_Int;
/*     */   private int c;
/*     */   private int jdField_d_of_type_Int;
/*     */   private ElementCollectionManager jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager;
/*     */   private ArrayList jdField_b_of_type_JavaUtilArrayList;
/*     */ 
/*     */   public au(ct paramct, al paramal)
/*     */   {
/* 134 */     super(paramct);
/*     */ 
/*  94 */     new Matrix4f();
/*  95 */     new Matrix4f();
/*     */ 
/* 102 */     this.jdField_a_of_type_Q = new q();
/*     */ 
/* 108 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */ 
/* 189 */     this.jdField_b_of_type_Int = 0;
/* 190 */     this.c = 0;
/* 191 */     this.jdField_d_of_type_Int = 0;
/*     */ 
/* 135 */     this.jdField_a_of_type_Al = paramal;
/*     */   }
/*     */ 
/*     */   public final SegmentController a()
/*     */   {
/* 183 */     return this.jdField_a_of_type_Al.a();
/*     */   }
/*     */   public final le a() {
/* 186 */     return this.jdField_a_of_type_Le;
/*     */   }
/*     */ 
/*     */   private void a(int paramInt, boolean paramBoolean)
/*     */   {
/*     */     ld localld;
/*     */     Object localObject;
/*     */     while (true)
/*     */     {
/* 198 */       localld = (ld)a();
/* 199 */       if (paramInt == -2147483648) {
/* 200 */         a().a().a().a(null, null);
/* 201 */         return;
/*     */       }
/* 203 */       if (paramBoolean) {
/* 204 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/* 205 */         this.jdField_b_of_type_Int = 0;
/*     */       }
/*     */ 
/* 208 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager != null) break;
/* 209 */       this.c = FastMath.b(this.c + paramInt, localld.a().getModules().size());
/*     */ 
/* 211 */       if ((this.jdField_b_of_type_JavaUtilArrayList != null) && (this.jdField_d_of_type_Int < this.jdField_b_of_type_JavaUtilArrayList.size()) && (!paramBoolean)) {
/* 212 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((ElementCollectionManager)this.jdField_b_of_type_JavaUtilArrayList.get(this.jdField_d_of_type_Int));
/* 213 */         this.jdField_d_of_type_Int += 1;
/*     */       } else {
/* 215 */         this.jdField_d_of_type_Int = 0;
/* 216 */         this.jdField_b_of_type_JavaUtilArrayList = null;
/*     */ 
/* 220 */         if (((
/* 220 */           localObject = (ManagerModule)localld.a().getModules().get(this.c))
/* 220 */           .getElementManager() instanceof UsableControllableElementManager)) {
/* 221 */           localObject = (UsableControllableElementManager)((ManagerModule)localObject).getElementManager();
/* 222 */           this.jdField_b_of_type_JavaUtilArrayList = ((UsableControllableElementManager)localObject).getCollectionManagers();
/* 223 */           if (((UsableControllableElementManager)localObject).getCollectionManagers().size() > 0) {
/* 224 */             this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((ElementCollectionManager)((UsableControllableElementManager)localObject).getCollectionManagers().get(0)); break label260;
/*     */           }
/* 226 */           this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/* 227 */           continue;
/* 228 */         }if ((((ManagerModule)localObject).getElementManager() instanceof UsableControllableSingleElementManager))
/*     */         {
/* 231 */           localObject = (UsableControllableSingleElementManager)((ManagerModule)localObject).getElementManager();
/* 232 */           this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((UsableControllableSingleElementManager)localObject).getCollection();
/*     */         }
/*     */       }
/*     */ 
/* 236 */       label260: if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager == null) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().size() != 0)) break;
/* 237 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/*     */     }
/* 239 */     if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager != null) && (this.jdField_b_of_type_Int < this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().size()) && (this.jdField_b_of_type_Int >= 0))
/*     */     {
/* 245 */       localObject = (ElementCollection)this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().get(this.jdField_b_of_type_Int);
/* 246 */       if (Keyboard.isKeyDown(71))
/* 247 */         a().a().a().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.rawCollection, (mF)localld);
/*     */       else {
/* 249 */         a().a().a().a(((ElementCollection)localObject).getNeighboringCollection(), (mF)localld);
/*     */       }
/* 251 */       this.jdField_b_of_type_Int = FastMath.b(this.jdField_b_of_type_Int + paramInt, localld.a().getModules().size());
/*     */ 
/* 253 */       return;
/* 254 */     }this.jdField_b_of_type_Int = 0;
/* 255 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/* 260 */     if (Keyboard.getEventKeyState()) {
/* 261 */       q localq = null; Keyboard.getEventKey(); if (a().a().a.a.a.a())
/*     */       {
/* 263 */         return;
/*     */       }
/* 265 */       if (Keyboard.getEventKey() == 73) {
/* 266 */         a(1, false);
/*     */       }
/* 268 */       if (Keyboard.getEventKey() == 72) {
/* 269 */         a(-1, false);
/*     */       }
/* 271 */       if (Keyboard.getEventKey() == 77) {
/* 272 */         a(1, true);
/*     */       }
/* 274 */       if (Keyboard.getEventKey() == 76) {
/* 275 */         a(-1, true);
/*     */       }
/* 277 */       if (Keyboard.getEventKey() == 80) {
/* 278 */         a(-2147483648, true);
/*     */       }
/*     */ 
/* 281 */       if (Keyboard.getEventKey() == cv.B.a()) {
/* 282 */         this.jdField_d_of_type_Boolean = (!this.jdField_d_of_type_Boolean);
/*     */       }
/*     */       else
/*     */       {
/*     */         au localau;
/* 283 */         if (Keyboard.getEventKey() == cv.z.a()) {
/* 284 */           localau = this; if ((this.e) && (localau.jdField_a_of_type_Q != null)) if ((localau.jdField_a_of_type_Le != null) && (localau.jdField_a_of_type_Le.a(new q()).equals(localau.jdField_a_of_type_Q))) localau.jdField_a_of_type_Le = null; else try { localau.jdField_a_of_type_Le = localau.jdField_a_of_type_Al.a().getSegmentBuffer().a(localau.jdField_a_of_type_Q, true); } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException1) { localCannotImmediateRequestOnClientException1.printStackTrace(); }  
/*     */         }
/* 285 */         else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.R.a())) {
/* 286 */           f(true);
/* 287 */         } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.S.a())) {
/* 288 */           f(false);
/* 289 */         } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.T.a())) {
/* 290 */           localau = this; if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) try { localau.jdField_a_of_type_Le = localau.jdField_a_of_type_Al.a().getSegmentBuffer().a(localau.jdField_a_of_type_Al.a(), true); localau.jdField_a_of_type_Int = Math.max(0, localau.jdField_a_of_type_JavaUtilArrayList.indexOf(localau.jdField_a_of_type_Al.a())); } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException2) { localCannotImmediateRequestOnClientException2.printStackTrace(); }  
/*     */         }
/* 291 */         else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.t.a())) {
/* 292 */           this.jdField_a_of_type_Dr.a(new q(8, 8, 0));
/* 293 */         } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.A.a())) {
/* 294 */           localau = this; System.err.println("[CLIENT][SEGBUILDCONTROLLER] NORMAL CONNECTION");
/*     */           try { localau.jdField_a_of_type_Al.a().setCurrentBlockController(localau.jdField_a_of_type_Le, localau.jdField_a_of_type_Q); } catch (CannotBeControlledException localCannotBeControlledException1) { localau.a(localCannotBeControlledException1); }
/* 295 */         } else if ((Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.A.a())) {
/* 296 */           localau = this; System.err.println("[CLIENT][SEGBUILDCONTROLLER] BULK CONNECTION");
/*     */           try { Object localObject = ((SegmentBufferManager)localau.jdField_a_of_type_Al.a().getSegmentBuffer()).a(localau.jdField_a_of_type_Q); new q(); if ((localau.jdField_a_of_type_Le != null) && (localObject != null) && (((jz)localObject).jdField_a_of_type_JavaUtilArrayList.size() > 0)) { System.err.println("[CLIENT][SEGBUILDCONTROLLER] BULK CONNECTING " + ((jz)localObject).jdField_a_of_type_JavaUtilArrayList.size() + " elements of type " + ((jz)localObject).jdField_a_of_type_Short); boolean bool = localau.jdField_a_of_type_Al.a().getControlElementMap().isControlling(localau.jdField_a_of_type_Le.a(new q()), localau.jdField_a_of_type_Q, ((jz)localObject).jdField_a_of_type_Short);
/*     */               int i;
/* 296 */               for (localObject = ((jz)localObject).jdField_a_of_type_JavaUtilArrayList.iterator(); ((Iterator)localObject).hasNext(); localau.jdField_a_of_type_Al.a().setCurrentBlockController(localau.jdField_a_of_type_Le, localq, i)) { localq = (q)((Iterator)localObject).next(); i = bool ? 2 : 1; }  }  } catch (CannotBeControlledException localCannotBeControlledException2) {
/* 296 */             localau.a(localCannotBeControlledException2);
/*     */           }
/*     */         }
/*     */       }
/* 298 */       if (cv.U.a()) {
/* 299 */         a().a().a.a.a
/* 301 */           .b();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 307 */     this.jdField_a_of_type_Dr.handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/* 312 */     if (System.currentTimeMillis() - a().a().a.a.a.jdField_a_of_type_Long < 300L)
/*     */     {
/* 314 */       return;
/*     */     }
/*     */ 
/* 318 */     if ((this.jdField_a_of_type_Al.a() != null) && (xe.a() != null) && (!this.jdField_a_of_type_Boolean))
/*     */     {
/* 320 */       int i = xu.a.b() ? 1 : 0;
/* 321 */       int j = xu.a.b() ? 0 : 1;
/*     */       Vector3f localVector3f1;
/*     */       Vector3f localVector3f2;
/* 323 */       if (paramxp.jdField_a_of_type_Int == i) {
/* 324 */         localVector3f1 = new Vector3f(xe.a().a());
/* 325 */         localVector3f2 = new Vector3f(xe.a().c());
/*     */ 
/* 327 */         if (Keyboard.isKeyDown(cv.U.a())) {
/* 328 */           (
/* 329 */             localVector3f2 = new Vector3f(a().a().b()))
/* 329 */             .sub(localVector3f1);
/*     */         }
/* 331 */         int k = 0;
/* 332 */         if (paramxp.jdField_a_of_type_Boolean) {
/* 333 */           this.jdField_a_of_type_Ju = null;
/* 334 */           this.jdField_b_of_type_Long = System.currentTimeMillis();
/*     */         } else {
/* 336 */           k = (this.jdField_b_of_type_Long > 0L) && (System.currentTimeMillis() - this.jdField_b_of_type_Long > 500L) ? 1 : 0;
/* 337 */           this.jdField_b_of_type_Long = 0L;
/*     */         }
/*     */ 
/* 340 */         if ((!paramxp.jdField_a_of_type_Boolean) && (k == 0))
/*     */         {
/* 343 */           a().a().a.a.a.a(this.jdField_a_of_type_Al.a(), localVector3f1, localVector3f2, new av(this), new ju(), this.jdField_a_of_type_Az, 160.0F);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 403 */       if ((paramxp.jdField_a_of_type_Int == j) && (!paramxp.jdField_a_of_type_Boolean)) {
/* 404 */         localVector3f1 = new Vector3f(xe.a().a());
/* 405 */         localVector3f2 = new Vector3f(xe.a().c());
/*     */ 
/* 407 */         if (Keyboard.isKeyDown(cv.U.a())) {
/* 408 */           (
/* 409 */             localVector3f2 = new Vector3f(a().a().b()))
/* 409 */             .sub(localVector3f1);
/*     */         }
/*     */ 
/* 412 */         a().a().a.a.a.a(this.jdField_a_of_type_Al.a(), localVector3f1, localVector3f2, 160.0F, this.jdField_a_of_type_Az);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 435 */     if (paramBoolean)
/*     */     {
/* 438 */       if (this.jdField_a_of_type_Al.a() != this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController) {
/* 439 */         this.jdField_a_of_type_Le = null;
/*     */ 
/* 441 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = this.jdField_a_of_type_Al.a();
/*     */       }
/*     */ 
/* 447 */       this.jdField_a_of_type_Al.a().getControlElementMap().setObs(this);
/*     */ 
/* 450 */       this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 451 */       this.f = true;
/*     */ 
/* 457 */       if ((this.jdField_a_of_type_Dr == null) || (((xb)this.jdField_a_of_type_Dr.a()).a() != this.jdField_a_of_type_Al.a())) {
/* 458 */         if (this.jdField_a_of_type_Al.a() != null) {
/* 459 */           Transform localTransform = new Transform(this.jdField_a_of_type_Al.a().getWorldTransform());
/* 460 */           GlUtil.d(new Vector3f(0.0F, 1.0F, 0.0F), localTransform);
/* 461 */           GlUtil.c(new Vector3f(1.0F, 0.0F, 0.0F), localTransform);
/* 462 */           GlUtil.a(new Vector3f(0.0F, 0.0F, 1.0F), localTransform);
/*     */ 
/* 471 */           a(); this.jdField_a_of_type_Dr = new dr(xe.a(), this.jdField_a_of_type_Al);
/*     */ 
/* 474 */           this.jdField_a_of_type_Dr.a(new q(8, 8, 0));
/*     */ 
/* 476 */           this.jdField_a_of_type_Dr.a(0.0F);
/*     */         }
/* 480 */         else if (this.jdField_a_of_type_Dr != null) {
/* 481 */           this.jdField_a_of_type_Dr.a(xe.a());
/*     */         }
/* 483 */       } else if (this.jdField_a_of_type_Dr != null) {
/* 484 */         this.jdField_a_of_type_Dr.a(xe.a());
/*     */       }
/* 486 */       if (this.jdField_a_of_type_Al.a() != null)
/*     */       {
/* 488 */         a().a(); dj.a(this.jdField_a_of_type_Al.a());
/*     */       }
/* 490 */       a().a().c("FlightMode");
/* 491 */       a().a().a("BuildMode", "Build Mode", "(press " + cv.r.b() + " to switch to FLIGHT MODE; press " + cv.v.b() + " to exit structure)");
/* 492 */       if ((!g) && (this.jdField_a_of_type_Al.a() == null)) throw new AssertionError();
/*     */ 
/* 495 */       xe.a(this.jdField_a_of_type_Dr);
/*     */     }
/* 497 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   private void f(boolean paramBoolean)
/*     */   {
/* 532 */     if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 533 */       return;
/*     */     }
/* 535 */     this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + (paramBoolean ? 1 : -1), this.jdField_a_of_type_JavaUtilArrayList.size() - 1);
/* 536 */     System.err.println("SWITCH " + paramBoolean + " " + this.jdField_a_of_type_Int);
/*     */     try {
/* 539 */       this.jdField_a_of_type_Le = this.jdField_a_of_type_Al.a().getSegmentBuffer().a((q)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_Int), true);
/*     */       return;
/*     */     } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {
/* 542 */       localCannotImmediateRequestOnClientException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 571 */     if (this.jdField_a_of_type_Le != null) {
/* 572 */       this.jdField_a_of_type_Le.a();
/* 573 */       if (this.jdField_a_of_type_Le.a() == 0) {
/* 574 */         this.jdField_a_of_type_Le = null;
/*     */       }
/*     */     }
/* 577 */     if (this.f)
/*     */     {
/* 579 */       this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 580 */       for (paramxq = this.jdField_a_of_type_Al.a().getControlElementMap().getControllingMap().keySet().iterator(); paramxq.hasNext(); ) { long l = ((Long)paramxq.next()).longValue();
/* 581 */         this.jdField_a_of_type_JavaUtilArrayList.add(ElementCollection.getPosFromIndex(l, new q()));
/*     */       }
/*     */ 
/* 584 */       this.f = false;
/*     */     }
/*     */     Vector3f localVector3f1;
/* 586 */     if ((this.jdField_b_of_type_Long > 0L) && (Keyboard.isKeyDown(cv.U.a())) && (System.currentTimeMillis() - this.jdField_b_of_type_Long > 500L)) {
/* 587 */       paramxq = new Vector3f(xe.a().a());
/* 588 */       (
/* 589 */         localVector3f1 = new Vector3f(a().a().b()))
/* 589 */         .sub(paramxq);
/*     */ 
/* 595 */       a().a().a.a.a.a(this.jdField_a_of_type_Al.a(), paramxq, localVector3f1, new aw(this), this.jdField_a_of_type_Ju, this.jdField_a_of_type_Az, 160.0F);
/*     */     }
/*     */ 
/* 647 */     wV.jdField_a_of_type_Boolean = !Keyboard.isKeyDown(cv.U.a());
/*     */ 
/* 653 */     if (this.jdField_a_of_type_Al.a() != null) {
/* 654 */       paramxq = this; Object localObject = new Vector3f(xe.a().a()); localVector3f1 = new Vector3f(xe.a().c()); Vector3f localVector3f2 = new Vector3f((Vector3f)localObject); localVector3f1.scale(160.0F); localVector3f2.add(localVector3f1); if (Keyboard.isKeyDown(cv.U.a())) localVector3f2 = new Vector3f(paramxq.a().a().b()); paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = ((PhysicsExt)paramxq.a().a()).testRayCollisionPoint((Vector3f)localObject, localVector3f2, false, null, paramxq.jdField_a_of_type_Al.a(), false, paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment, true); if ((paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult))) { if (((localObject = (CubeRayCastResult)paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null) && (((CubeRayCastResult)localObject).cubePos != null)) { ((CubeRayCastResult)localObject).getSegment().a(((CubeRayCastResult)localObject).cubePos, paramxq.jdField_a_of_type_Q); ((CubeRayCastResult)localObject).getSegment().a().getType(((CubeRayCastResult)localObject).cubePos); paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject).getSegment(); paramxq.e = true; return; } paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null; paramxq.e = false; return; } paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null; paramxq.e = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 701 */     this.f = true;
/*     */   }
/*     */ 
/*     */   public final az a()
/*     */   {
/* 709 */     return this.jdField_a_of_type_Az;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     au
 * JD-Core Version:    0.6.2
 */