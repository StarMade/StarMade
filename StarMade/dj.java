/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*      */ import com.bulletphysics.dynamics.DynamicsWorld;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import java.io.File;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ThreadPoolExecutor;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Tuple3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GL15;
/*      */ import org.schema.game.client.view.SegmentDrawer;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.data.element.ControlElementMap;
/*      */ import org.schema.game.common.data.element.ElementClassNotFoundException;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*      */ import org.schema.game.common.data.physics.PhysicsExt;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.schine.graphicsengine.camera.Camera;
/*      */ import org.schema.schine.graphicsengine.core.GLException;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ import org.schema.schine.graphicsengine.forms.Mesh;
/*      */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ 
/*      */ public final class dj extends xM
/*      */ {
/*      */   public static boolean a;
/*      */   private ct jdField_a_of_type_Ct;
/*      */   private iN jdField_a_of_type_IN;
/*      */   private iU jdField_a_of_type_IU;
/*      */   private es jdField_a_of_type_Es;
/*      */   private eL jdField_a_of_type_EL;
/*      */   private ez jdField_a_of_type_Ez;
/*      */   private final ex jdField_a_of_type_Ex;
/*      */   private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*      */   private final eG jdField_a_of_type_EG;
/*   88 */   private zF jdField_a_of_type_ZF = new zF(9.0F);
/*      */   private iJ jdField_a_of_type_IJ;
/*      */   private dq jdField_a_of_type_Dq;
/*      */   private iV jdField_a_of_type_IV;
/*      */   private ep jdField_a_of_type_Ep;
/*      */   private final fe jdField_a_of_type_Fe;
/*      */   private final cL jdField_a_of_type_CL;
/*      */   private final eN jdField_a_of_type_EN;
/*      */   private boolean f;
/*      */   private SegmentDrawer jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer;
/*      */   private dD jdField_a_of_type_DD;
/*      */   public static SegmentController a;
/*      */   public static long a;
/*      */   private final do jdField_a_of_type_Do;
/*      */   private final eJ jdField_a_of_type_EJ;
/*      */   private final eu jdField_a_of_type_Eu;
/*  116 */   private final dF jdField_a_of_type_DF = new dF();
/*      */ 
/*  123 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*      */   public static boolean b;
/*  127 */   public static boolean c = false;
/*  128 */   public static boolean d = false;
/*      */   private iW jdField_a_of_type_IW;
/*      */   private ew jdField_a_of_type_Ew;
/*      */   private Integer[] jdField_a_of_type_ArrayOfJavaLangInteger;
/*  226 */   private Integer[] jdField_b_of_type_ArrayOfJavaLangInteger = new Integer[0];
/*  227 */   private int jdField_a_of_type_Int = -2;
/*      */   private float jdField_a_of_type_Float;
/*  301 */   private q jdField_a_of_type_Q = new q();
/*  302 */   private q jdField_b_of_type_Q = new q();
/*      */ 
/*  376 */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*      */   private boolean g;
/*      */   private boolean h;
/*      */   private boolean i;
/*      */ 
/*      */   public dj(ct paramct)
/*      */   {
/*  166 */     this.jdField_a_of_type_Ct = paramct;
/*      */ 
/*  168 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  169 */     this.jdField_a_of_type_Do = new do();
/*  170 */     this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer = new SegmentDrawer(paramct);
/*  171 */     this.jdField_a_of_type_EJ = new eJ();
/*  172 */     this.jdField_a_of_type_Eu = new eu();
/*      */ 
/*  174 */     this.jdField_a_of_type_CL = new cL(paramct);
/*  175 */     this.jdField_a_of_type_EN = new eN(paramct);
/*  176 */     this.jdField_a_of_type_Ex = new ex();
/*  177 */     this.jdField_a_of_type_Fe = new fe(paramct.a());
/*  178 */     this.jdField_a_of_type_IJ = new iJ();
/*  179 */     this.jdField_a_of_type_Dq = new dq();
/*  180 */     this.jdField_a_of_type_IV = new iV();
/*  181 */     this.jdField_a_of_type_IU = new iU();
/*      */ 
/*  183 */     this.jdField_a_of_type_DD = new dD(paramct);
/*  184 */     this.jdField_a_of_type_EG = new eG();
/*      */   }
/*      */ 
/*      */   public final void a()
/*      */   {
/*      */   }
/*      */ 
/*      */   public final void d()
/*      */   {
/*  195 */     this.jdField_a_of_type_EG.d();
/*  196 */     this.jdField_a_of_type_EJ.d();
/*      */ 
/*  198 */     this.jdField_a_of_type_Do.e();
/*  199 */     this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d();
/*  200 */     for (dD localdD = this.jdField_a_of_type_DD; localdD.jdField_a_of_type_JavaUtilArrayList.size() > 0; ((dE)localdD.jdField_a_of_type_JavaUtilArrayList.remove(0)).e());
/*      */   }
/*      */ 
/*      */   private void s()
/*      */   {
/*      */     StringBuffer localStringBuffer;
/*  208 */     (
/*  209 */       localStringBuffer = new StringBuffer())
/*  209 */       .append("Physics Data (!!warning slow): ");
/*      */ 
/*  211 */     for (CollisionObject localCollisionObject : this.jdField_a_of_type_Ct.a().getDynamicsWorld().getCollisionObjectArray())
/*      */     {
/*  212 */       localStringBuffer.append(localCollisionObject.getCollisionShape().getClass().getSimpleName() + ": ");
/*  213 */       for (Iterator localIterator2 = this.jdField_a_of_type_Ct.a().values().iterator(); localIterator2.hasNext(); )
/*      */       {
/*      */         mF localmF;
/*  214 */         if ((((
/*  214 */           localmF = (mF)localIterator2.next()) instanceof zL)) && 
/*  215 */           (((zL)localmF).getPhysicsDataContainer().getObject() == localCollisionObject)) {
/*  216 */           localStringBuffer.append(localmF);
/*      */         }
/*      */       }
/*      */ 
/*  220 */       localStringBuffer.append("; \n");
/*      */     }
/*      */ 
/*  223 */     xd.jdField_a_of_type_JavaUtilArrayList.add(localStringBuffer.toString());
/*      */   }
/*      */ 
/*      */   private void a(long paramLong, q paramq1, q paramq2, Transform paramTransform)
/*      */   {
/*  265 */     paramLong = (float)((System.currentTimeMillis() - paramLong) % 1200000L) / 
/*  265 */       1200000.0F;
/*  266 */     paramq1.a(16);
/*  267 */     paramq1.a(8, 8, 8);
/*      */ 
/*  269 */     paramq1.c(this.jdField_a_of_type_Ct.a().a());
/*      */ 
/*  273 */     this.jdField_a_of_type_Float = paramLong;
/*      */ 
/*  279 */     paramLong = new Vector3f(paramq2.jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), paramq2.b * Universe.getSectorSizeWithMargin(), paramq2.c * Universe.getSectorSizeWithMargin());
/*      */ 
/*  284 */     new Vector3f(paramq1.jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), paramq1.b * Universe.getSectorSizeWithMargin(), paramq1.c * Universe.getSectorSizeWithMargin());
/*      */ 
/*  288 */     paramTransform.setIdentity();
/*  289 */     if (mI.a(this.jdField_a_of_type_Ct.a().a())) {
/*  290 */       paramTransform.basis.rotX(6.283186F * this.jdField_a_of_type_Float);
/*      */     }
/*      */ 
/*  297 */     paramTransform.transform(paramLong);
/*  298 */     xd.jdField_a_of_type_XT.c(paramLong);
/*      */   }
/*      */ 
/*      */   private void a(long paramLong)
/*      */   {
/*  306 */     mq localmq = this.jdField_a_of_type_Ct.a().a();
/*  307 */     int j = -1;
/*  308 */     q localq = new q();
/*      */     Object localObject3;
/*  310 */     for (int k = 0; k < 27; k++)
/*      */     {
/*  312 */       int m = localmq.a(k);
/*      */ 
/*  314 */       localObject3 = mD.values()[m];
/*      */ 
/*  316 */       localmq.a(k, this.jdField_a_of_type_Q);
/*      */ 
/*  318 */       this.jdField_b_of_type_Q.b((this.jdField_a_of_type_Q.jdField_a_of_type_Int << 4) + 8, (this.jdField_a_of_type_Q.b << 4) + 8, (this.jdField_a_of_type_Q.c << 4) + 8);
/*      */ 
/*  323 */       (
/*  324 */         localObject2 = new q(this.jdField_b_of_type_Q))
/*  324 */         .c(this.jdField_a_of_type_Ct.a().a());
/*  325 */       if ((j < 0) || (((q)localObject2).a() < localq.a())) {
/*  326 */         j = k;
/*  327 */         localq.b((q)localObject2);
/*      */       }
/*  329 */       this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  330 */       if (localObject3 == mD.e)
/*      */       {
/*  332 */         a(paramLong, this.jdField_a_of_type_Q, (q)localObject2, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*      */       }
/*  334 */       else if (localObject3 == mD.f)
/*      */       {
/*  336 */         a(paramLong, this.jdField_a_of_type_Q, (q)localObject2, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*      */ 
/*  338 */         this.jdField_a_of_type_Dq.b();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  344 */     mJ.a(this.jdField_a_of_type_Ct.a().a(), this.jdField_a_of_type_Q);
/*      */ 
/*  346 */     if (!localmq.a().equals(this.jdField_a_of_type_Q)) {
/*  347 */       System.err.println("[CLIENT] WARNING: System not yet right: " + this.jdField_a_of_type_Q + " / " + localmq.a());
/*  348 */       return;
/*      */     }
/*      */ 
/*  351 */     k = localmq.a(localmq.a(this.jdField_a_of_type_Q));
/*      */ 
/*  353 */     Object localObject2 = mD.values()[k];
/*      */ 
/*  355 */     this.jdField_b_of_type_Q.b((this.jdField_a_of_type_Q.jdField_a_of_type_Int << 4) + 8, (this.jdField_a_of_type_Q.b << 4) + 8, (this.jdField_a_of_type_Q.c << 4) + 8);
/*      */ 
/*  360 */     (
/*  361 */       localObject3 = new q(this.jdField_b_of_type_Q))
/*  361 */       .c(this.jdField_a_of_type_Ct.a().a());
/*      */ 
/*  363 */     a(paramLong, this.jdField_a_of_type_Q, (q)localObject3, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*  364 */     GL11.glDepthRange(0.0D, 1.0D);
/*  365 */     Object localObject1 = localObject3; this.jdField_a_of_type_IV.jdField_a_of_type_Q.b(localObject1);
/*      */ 
/*  367 */     if ((!xu.U.b()) && (localObject2 == mD.e))
/*      */     {
/*  370 */       this.jdField_a_of_type_IV.b();
/*      */     }
/*      */ 
/*  374 */     xd.jdField_a_of_type_JavaUtilArrayList.add("#####SECTORSYSTEM " + this.jdField_a_of_type_Q + " CENTER: " + this.jdField_b_of_type_Q + ": " + localObject2);
/*      */   }
/*      */ 
/*      */   public final void b()
/*      */   {
/*  403 */     if (xu.m.b()) {
/*  404 */       this.jdField_a_of_type_Ct.a().b(0);
/*      */     }
/*      */ 
/*  407 */     mC localmC = null;
/*      */ 
/*  409 */     int j = 0;
/*      */     Object localObject2;
/*  411 */     if (((localObject2 = this.jdField_a_of_type_IV).jdField_a_of_type_Q.jdField_a_of_type_Int == 0) && (((iV)localObject2).jdField_a_of_type_Q.b == 0) && (((iV)localObject2).jdField_a_of_type_Q.c == 0)) { GL11.glDisable(2929); GL11.glDisable(2884); GlUtil.d(); GlUtil.a(xe.a().a()); GlUtil.b(3.0F, 3.0F, 3.0F); GL11.glEnable(3042); GL11.glBlendFunc(770, 771); d.h.c(); GlUtil.a(d.h, "time", ((iV)localObject2).jdField_a_of_type_Float * 10.0F); ((iV)localObject2).jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b(); zj.e(); GL11.glDisable(3042); GlUtil.c(); GL11.glEnable(2929); GL11.glEnable(2884); GL11.glClear(256);
/*      */     }
/*      */     Object localObject3;
/*  414 */     if (this.jdField_a_of_type_Ct.a().a().a() != this.jdField_a_of_type_Int) {
/*  415 */       synchronized (this.jdField_a_of_type_Ct.a().a()) {
/*  416 */         this.jdField_a_of_type_Int = this.jdField_a_of_type_Ct.a().a().a();
/*  417 */         localObject2 = this; localObject3 = new dk((dj)localObject2); ((dj)localObject2).jdField_a_of_type_Ct.getThreadPool().execute((Runnable)localObject3);
/*      */       }
/*      */     }
/*  420 */     if (this.jdField_a_of_type_ArrayOfJavaLangInteger != null) {
/*  421 */       this.jdField_b_of_type_ArrayOfJavaLangInteger = this.jdField_a_of_type_ArrayOfJavaLangInteger;
/*  422 */       this.jdField_a_of_type_ArrayOfJavaLangInteger = null;
/*      */     }
/*      */ 
/*  425 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  426 */     ??? = new q();
/*      */ 
/*  430 */     long l = this.jdField_a_of_type_Ct.a().calculateStartTime();
/*      */ 
/*  432 */     for (int k = 0; k < this.jdField_b_of_type_ArrayOfJavaLangInteger.length; k++)
/*      */     {
/*  435 */       int m = this.jdField_b_of_type_ArrayOfJavaLangInteger[k].intValue();
/*      */ 
/*  439 */       this.jdField_a_of_type_Ct.a().a().a(m, (q)???);
/*  440 */       localObject3 = mD.values()[this.jdField_a_of_type_Ct.a().a().b(m)];
/*  441 */       mC.values(); this.jdField_a_of_type_Ct.a().a().a(m);
/*      */ 
/*  443 */       if (localObject3 == mD.c)
/*      */       {
/*  445 */         if (this.jdField_a_of_type_Ct.a().a().equals(???))
/*      */         {
/*  449 */           localmC = mC.values()[this.jdField_a_of_type_Ct.a().a().a(m)];
/*      */ 
/*  451 */           this.jdField_a_of_type_IN.a = localmC;
/*  452 */           if (this.jdField_a_of_type_IN.a())
/*  453 */             j = 1;
/*      */           else {
/*  455 */             this.jdField_a_of_type_IN.b();
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  460 */           (
/*  461 */             localObject3 = new q((q)???))
/*  461 */             .c(this.jdField_a_of_type_Ct.a().a());
/*      */ 
/*  463 */           this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.setIdentity();
/*  464 */           q localq = mJ.a((q)???, new q());
/*  465 */           if (mI.a((q)???))
/*      */           {
/*  467 */             float f1 = (float)((System.currentTimeMillis() - l) % 1200000L) / 
/*  467 */               1200000.0F;
/*  468 */             if (mI.a(this.jdField_a_of_type_Ct.a().a()))
/*  469 */               this.jdField_a_of_type_IJ.jdField_a_of_type_Float = f1;
/*      */             else {
/*  471 */               this.jdField_a_of_type_IJ.jdField_a_of_type_Float = 0.0F;
/*      */             }
/*      */ 
/*  474 */             localq.a(16);
/*  475 */             localq.a(8, 8, 8);
/*  476 */             localq.c(this.jdField_a_of_type_Ct.a().a());
/*  477 */             this.jdField_a_of_type_IJ.jdField_a_of_type_Q.b(localq);
/*      */           }
/*      */           else {
/*  480 */             this.jdField_a_of_type_IJ.jdField_a_of_type_Float = 0.0F;
/*      */           }
/*  482 */           this.jdField_a_of_type_IJ.a((q)localObject3);
/*  483 */           this.jdField_a_of_type_IJ.a(mC.values()[this.jdField_a_of_type_Ct.a().a().a(m)]);
/*  484 */           this.jdField_a_of_type_IJ.b();
/*      */         }
/*      */       }
/*      */     }
/*  488 */     GL11.glDepthRange(0.0D, 1.0D);
/*      */ 
/*  497 */     this.jdField_a_of_type_DD.b();
/*      */ 
/*  499 */     this.jdField_a_of_type_Ep.b();
/*      */ 
/*  501 */     this.jdField_a_of_type_Ex.b();
/*      */ 
/*  503 */     for (k = 0; k < this.jdField_a_of_type_JavaUtilArrayList.size(); k++) {
/*  504 */       ((cX)this.jdField_a_of_type_JavaUtilArrayList.get(k)).b();
/*      */     }
/*  506 */     this.jdField_a_of_type_Do.f();
/*      */ 
/*  508 */     h.a("SEGMENTS");
/*      */ 
/*  511 */     this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.b();
/*  512 */     h.b("SEGMENTS");
/*      */ 
/*  514 */     this.jdField_a_of_type_DF.b();
/*      */ 
/*  516 */     if (Keyboard.isKeyDown(cv.U.a())) if (((((localObject3 = this.jdField_a_of_type_Ct.a().a.a.a).a().a().a.c) || (((ar)localObject3).a().a().c)) && (jdField_a_of_type_Boolean) ? 1 : 0) != 0) {
/*  517 */         this.jdField_b_of_type_JavaxVecmathVector3f = xd.a(this.jdField_b_of_type_JavaxVecmathVector3f);
/*      */       }
/*      */ 
/*      */ 
/*  521 */     this.jdField_a_of_type_Ew.b();
/*      */ 
/*  523 */     this.jdField_a_of_type_CL.b();
/*      */     try
/*      */     {
/*  526 */       if ((jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (System.currentTimeMillis() - jdField_a_of_type_Long < 8000L) && (this.jdField_a_of_type_Ct.a() != null) && (this.jdField_a_of_type_Ct.a().a.a.a.a().a().a.c))
/*      */       {
/*  529 */         System.currentTimeMillis();
/*  530 */         GlUtil.d();
/*  531 */         Mesh localMesh = (Mesh)xe.a().a("Arrow").a.get(0);
/*  532 */         localObject3 = new Transform(jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*      */ 
/*  534 */         ( = new Vector3f(0.0F, 0.0F, 1.0F))
/*  535 */           .scale(this.jdField_a_of_type_ZF.a());
/*      */ 
/*  537 */         ((Transform)localObject3).basis.transform((Tuple3f)???);
/*  538 */         ((Transform)localObject3).origin.add((Tuple3f)???);
/*  539 */         GlUtil.b((Transform)localObject3);
/*      */ 
/*  542 */         GlUtil.b(0.3F, 0.3F, 0.3F);
/*  543 */         GL11.glEnable(3042);
/*  544 */         GL11.glBlendFunc(770, 771);
/*  545 */         GL11.glEnable(2903);
/*  546 */         GlUtil.a(1.0F, 1.0F, 1.0F, this.jdField_a_of_type_ZF.a());
/*      */ 
/*  549 */         localMesh.b();
/*  550 */         GlUtil.c();
/*  551 */         GL11.glDisable(2903);
/*  552 */         GL11.glDisable(3042);
/*  553 */         GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       } else {
/*  555 */         jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = null;
/*  556 */         jdField_a_of_type_Long = 0L;
/*      */       }
/*      */     }
/*      */     catch (NullPointerException localNullPointerException) {
/*  560 */       localNullPointerException.printStackTrace();
/*      */     }
/*      */ 
/*  562 */     if (j != 0) {
/*  563 */       this.jdField_a_of_type_IN.b();
/*      */     }
/*      */ 
/*  568 */     this.jdField_a_of_type_EG.b();
/*      */ 
/*  573 */     this.jdField_a_of_type_EL.jdField_a_of_type_Boolean = this.jdField_a_of_type_IN.a();
/*  574 */     this.jdField_a_of_type_EL.b();
/*  575 */     this.jdField_a_of_type_Eu.a();
/*      */ 
/*  578 */     this.jdField_a_of_type_EJ.b();
/*      */ 
/*  584 */     this.jdField_a_of_type_Es.b();
/*      */ 
/*  586 */     this.jdField_a_of_type_Ez.b();
/*      */ 
/*  588 */     if ((localmC != null) && (j == 0)) {
/*  589 */       this.jdField_a_of_type_IJ.a(new q(0, 0, 0));
/*  590 */       this.jdField_a_of_type_IJ.a(localmC);
/*  591 */       this.jdField_a_of_type_IJ.b();
/*      */     }
/*      */ 
/*  606 */     a(l);
/*      */ 
/*  609 */     this.jdField_a_of_type_EN.b();
/*      */ 
/*  676 */     if (xu.G.b()) {
/*  677 */       s();
/*      */     }
/*      */ 
/*  682 */     SegmentDrawer.jdField_a_of_type_Boolean = false;
/*      */   }
/*      */ 
/*      */   public final void e()
/*      */   {
/*  697 */     if (d) {
/*  698 */       t();
/*  699 */       d = false;
/*      */     }
/*      */ 
/*  702 */     h.a("GUI");
/*  703 */     this.jdField_a_of_type_Fe.b();
/*  704 */     h.b("GUI");
/*      */ 
/*  706 */     if (c) {
/*  707 */       t();
/*  708 */       c = false;
/*      */     }
/*      */ 
/*  711 */     eV.e();
/*      */     iW localiW;
/*  713 */     if (xu.o.b()) {
/*  714 */       if (this.jdField_a_of_type_IW == null)
/*  715 */         this.jdField_a_of_type_IW = new iW();
/*      */       iW tmp77_74 = this.jdField_a_of_type_IW; tmp77_74.b = (localiW = tmp77_74).d; localiW.c = localiW.e; localiW.a();
/*      */     }
/*  719 */     if (jdField_b_of_type_Boolean) {
/*  720 */       localiW = new iW();
/*  721 */       if (this.jdField_a_of_type_IW == null) {
/*  722 */         this.jdField_a_of_type_IW = new iW();
/*      */       }
/*  724 */       localiW.jdField_a_of_type_Int = this.jdField_a_of_type_IW.jdField_a_of_type_Int;
/*      */       try
/*      */       {
/*      */         xi localxi;
/*  726 */         (localxi = new xi(1024, 1024)).e(); localxi.d(); GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F); GL11.glClear(16640); localiW.b = 32; localiW.c = 32; GL11.glViewport(0, 0, 1024, 1024); localiW.jdField_a_of_type_Boolean = true; localiW.a(); localiW.jdField_a_of_type_Boolean = false; System.err.println("SHEET: " + localiW.jdField_a_of_type_Int + " WRITING SCREEN TO DISK: ./data/image-resource/build-icons-" + i.b(localiW.jdField_a_of_type_Int) + "-16x16-gui-"); GlUtil.a("./data/image-resource/build-icons-" + i.b(localiW.jdField_a_of_type_Int) + "-16x16-gui-", "png", 1024, 1024); GL11.glViewport(0, 0, xm.b(), xm.a()); localxi.b(); localxi.a();
/*      */       }
/*      */       catch (GLException localGLException) {
/*  729 */         localGLException.printStackTrace();
/*      */       }
/*      */ 
/*  730 */       jdField_b_of_type_Boolean = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void t()
/*      */   {
/*  738 */     File[] arrayOfFile1 = new File("./")
/*  738 */       .listFiles();
/*  739 */     int j = 0;
/*  740 */     int k = 1;
/*  741 */     label120: while (k != 0) {
/*  742 */       k = 0;
/*  743 */       File[] arrayOfFile2 = arrayOfFile1; int m = arrayOfFile1.length; for (int n = 0; ; n++) { if (n >= m) break label120;
/*  744 */         if (arrayOfFile2[n]
/*  744 */           .getName().startsWith("starmade-screenshot-" + i.a(j) + ".png"))
/*      */         {
/*  746 */           System.err.println("Screen Already Exists: ./starmade-screenshot-" + i.a(j) + ".png");
/*  747 */           j++;
/*  748 */           k = 1;
/*  749 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  754 */     GlUtil.a("./starmade-screenshot-" + i.a(j), "png", xm.b(), xm.a());
/*      */   }
/*      */ 
/*      */   public final void f() {
/*  758 */     Keyboard.enableRepeatEvents(true);
/*  759 */     if ((xu.o.b()) && 
/*  760 */       (this.jdField_a_of_type_IW != null)) {
/*  761 */       iW localiW = this.jdField_a_of_type_IW; if (Keyboard.getEventKeyState())
/*      */       {
/*      */         int j;
/*  761 */         switch (Keyboard.getEventKey()) { case 17:
/*  761 */           localiW.e += 8; break;
/*      */         case 30:
/*  761 */           localiW.d -= 8; break;
/*      */         case 31:
/*  761 */           localiW.e -= 8; break;
/*      */         case 32:
/*  761 */           localiW.d += 8; break;
/*      */         case 205:
/*  761 */           j = ElementKeyMap.highestType / 256 + 1; localiW.jdField_a_of_type_Int = ((localiW.jdField_a_of_type_Int + 1) % j); break;
/*      */         case 203:
/*  761 */           j = ElementKeyMap.highestType / 256 + 1; if (localiW.jdField_a_of_type_Int - 1 < 0) localiW.jdField_a_of_type_Int = (j - 1); else localiW.jdField_a_of_type_Int -= 1; break; }
/*      */       }
/*      */     }
/*  764 */     Keyboard.enableRepeatEvents(false);
/*      */   }
/*      */   public final void g() {
/*  767 */     this.jdField_a_of_type_Ct.a(); cW.b().b();
/*  768 */     Vector3f localVector3f = xd.jdField_a_of_type_XT.a();
/*  769 */     GlUtil.d();
/*      */ 
/*  771 */     GlUtil.c(localVector3f.x, localVector3f.y, localVector3f.z);
/*      */ 
/*  773 */     float f2 = 4.0F;
/*      */     float f1;
/*  777 */     if ((
/*  777 */       f1 = (float)Math.pow(localVector3f.length(), 1.299999952316284D)) < 
/*  777 */       30000.0F) {
/*  778 */       f2 = f1 / 30000.0F;
/*  779 */       f2 = 4.0F * f2;
/*      */     }
/*      */ 
/*  782 */     xe.a().a("Sphere").b(f2, f2, f2);
/*  783 */     xe.a().a("Sphere").b();
/*  784 */     GlUtil.c();
/*      */   }
/*      */ 
/*      */   public static void a(SegmentController paramSegmentController)
/*      */   {
/*  789 */     jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*  790 */     jdField_a_of_type_Long = System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   public final Vector3f b()
/*      */   {
/*  799 */     return this.jdField_b_of_type_JavaxVecmathVector3f;
/*      */   }
/*      */ 
/*      */   public final do a()
/*      */   {
/*  805 */     return this.jdField_a_of_type_Do;
/*      */   }
/*      */ 
/*      */   public final cL a()
/*      */   {
/*  811 */     return this.jdField_a_of_type_CL;
/*      */   }
/*      */   public final es a() {
/*  814 */     return this.jdField_a_of_type_Es;
/*      */   }
/*      */   public final fe a() {
/*  817 */     return this.jdField_a_of_type_Fe;
/*      */   }
/*      */ 
/*      */   public final ex a()
/*      */   {
/*  823 */     return this.jdField_a_of_type_Ex;
/*      */   }
/*      */ 
/*      */   public final SegmentDrawer a()
/*      */   {
/*  829 */     return this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer;
/*      */   }
/*      */ 
/*      */   public final eJ a()
/*      */   {
/*  835 */     return this.jdField_a_of_type_EJ;
/*      */   }
/*      */   public final iU a() {
/*  838 */     return this.jdField_a_of_type_IU;
/*      */   }
/*      */ 
/*      */   public final ez a()
/*      */   {
/*  844 */     return this.jdField_a_of_type_Ez;
/*      */   }
/*      */ 
/*      */   public final void h() {
/*  848 */     h.a("CONTEXT");
/*  849 */     this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.e();
/*  850 */     h.b("CONTEXT");
/*      */   }
/*      */ 
/*      */   public final void c()
/*      */   {
/*  856 */     this.jdField_a_of_type_IN = new iN();
/*  857 */     this.jdField_a_of_type_IN.c();
/*      */ 
/*  859 */     this.jdField_a_of_type_EG.c();
/*      */ 
/*  862 */     this.jdField_a_of_type_DD.c();
/*      */ 
/*  864 */     this.jdField_a_of_type_Ex.c();
/*      */ 
/*  866 */     this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.c();
/*      */ 
/*  870 */     this.jdField_a_of_type_IJ.c();
/*  871 */     this.jdField_a_of_type_Dq.c();
/*  872 */     this.jdField_a_of_type_IV.c();
/*  873 */     this.jdField_a_of_type_CL.c();
/*  874 */     this.jdField_a_of_type_EN.c();
/*  875 */     this.jdField_a_of_type_IU.c();
/*  876 */     this.jdField_a_of_type_Es = new es();
/*  877 */     this.jdField_a_of_type_Es.c();
/*  878 */     this.jdField_a_of_type_Fe.c();
/*      */ 
/*  880 */     this.jdField_a_of_type_EL = new eL();
/*  881 */     this.jdField_a_of_type_EL.c();
/*      */ 
/*  883 */     this.jdField_a_of_type_Ez = new ez(this.jdField_a_of_type_Ct);
/*  884 */     this.jdField_a_of_type_Ez.c();
/*      */ 
/*  886 */     this.jdField_a_of_type_Ew = new ew(this.jdField_a_of_type_Ct);
/*  887 */     this.jdField_a_of_type_Ew.c();
/*      */ 
/*  889 */     this.jdField_a_of_type_Ep = new ep(this.jdField_a_of_type_Ct);
/*  890 */     this.jdField_a_of_type_Ep.c();
/*      */ 
/*  892 */     cX localcX = new cX(this.jdField_a_of_type_Ct.getParticleController());
/*  893 */     this.jdField_a_of_type_Ct.a().jdField_a_of_type_JavaUtilArrayList.add(localcX);
/*  894 */     localcX.c();
/*      */ 
/*  943 */     this.f = true;
/*      */   }
/*      */ 
/*      */   public final void i()
/*      */   {
/*  954 */     long l1 = System.currentTimeMillis();
/*      */ 
/*  959 */     this.jdField_a_of_type_Fe.d();
/*      */     long l2;
/*  961 */     if ((
/*  961 */       l2 = System.currentTimeMillis() - l1) > 
/*  961 */       10L)
/*  962 */       System.err.println("[WORLDDRAWER] WARNING: SECTOR CHANGE UPDATE FOR DRAWER TOOK " + l2);
/*      */   }
/*      */ 
/*      */   public final void j()
/*      */   {
/*  970 */     iV localiV;
/*  969 */     if ((this.jdField_a_of_type_IV != null) && 
/*  970 */       ((localiV = this.jdField_a_of_type_IV).c > 5)) { localiV.b = GL15.glGetQueryObjectui(localiV.jdField_a_of_type_Int, 34918); localiV.c = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void a(xq paramxq)
/*      */   {
/*  996 */     if (!this.f) {
/*  997 */       return;
/*      */     }
/*      */ 
/* 1000 */     h.a("update");
/*      */ 
/* 1002 */     long l1 = System.currentTimeMillis();
/*      */     long l2;
/* 1004 */     if ((
/* 1004 */       l2 = System.currentTimeMillis() - l1) > 
/* 1004 */       10L) {
/* 1005 */       System.err.println("[DRAWER][WARNING] synUPDATE took " + l2 + " ms");
/*      */     }
/* 1007 */     l1 = System.currentTimeMillis();
/* 1008 */     if (this.h) {
/* 1009 */       this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.f();
/* 1010 */       this.h = false;
/*      */     }
/*      */     long l3;
/* 1015 */     if ((
/* 1015 */       l3 = System.currentTimeMillis() - l1) > 
/* 1015 */       10L) {
/* 1016 */       System.err.println("[DRAWER][WARNING] seg controller set update took " + l3 + " ms");
/*      */     }
/* 1018 */     l1 = System.currentTimeMillis();
/* 1019 */     if (this.g) {
/* 1020 */       u();
/* 1021 */       this.g = false;
/*      */     }
/*      */     long l4;
/* 1024 */     if ((
/* 1024 */       l4 = System.currentTimeMillis() - l1) > 
/* 1024 */       10L) {
/* 1025 */       System.err.println("[DRAWER][WARNING] segManControllerUpdate took " + l4 + " ms");
/*      */     }
/* 1027 */     if (this.i) {
/* 1028 */       this.jdField_a_of_type_DD.d();
/* 1029 */       this.i = false;
/*      */     }
/* 1031 */     this.jdField_a_of_type_ZF.a(paramxq);
/* 1032 */     Object localObject4 = null; Object localObject2 = paramxq; if ((localObject1 = this.jdField_a_of_type_IU).a != null) ((iU)localObject1).a.jdField_a_of_type_ZF.a((xq)localObject2);
/*      */ 
/* 1034 */     this.jdField_a_of_type_DD.a(paramxq);
/*      */ 
/* 1036 */     this.jdField_a_of_type_IJ.a(paramxq);
/* 1037 */     localObject2 = paramxq; (localObject1 = this.jdField_a_of_type_Dq).jdField_a_of_type_Float += ((xq)localObject2).a() * 0.07F; if (((dq)localObject1).jdField_a_of_type_Float > 1.0F)
/*      */     {
/*      */       Object tmp293_292 = localObject1; tmp293_292.jdField_a_of_type_Float = ((float)(tmp293_292.jdField_a_of_type_Float - Math.floor(((dq)localObject1).jdField_a_of_type_Float)));
/* 1038 */     }localObject2 = paramxq; this.jdField_a_of_type_IV.jdField_a_of_type_Float += ((xq)localObject2).a() * 0.07F;
/*      */ 
/* 1040 */     this.jdField_a_of_type_EN.a(paramxq);
/*      */ 
/* 1044 */     this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.a(paramxq);
/*      */ 
/* 1047 */     localObject2 = paramxq; this.jdField_a_of_type_Es.a.a((xq)localObject2);
/*      */ 
/* 1050 */     localObject2 = paramxq; if (!(localObject1 = this.jdField_a_of_type_EL).jdField_a_of_type_Boolean) { ((eL)localObject1).jdField_a_of_type_JavaxVecmathVector3f.set(xe.a().a()); ((eL)localObject1).jdField_a_of_type_JavaxVecmathVector3f.sub(((eL)localObject1).jdField_b_of_type_JavaxVecmathVector3f); float f1 = ((eL)localObject1).jdField_a_of_type_JavaxVecmathVector3f.length(); localObject1.jdField_b_of_type_Float += f1; if (((eL)localObject1).jdField_b_of_type_Float > 0.05F) { ((eL)localObject1).jdField_a_of_type_Za.a(Math.min(((eL)localObject1).jdField_b_of_type_Float, ((eL)localObject1).jdField_a_of_type_Float), ((eL)localObject1).jdField_a_of_type_Float); ((eL)localObject1).jdField_b_of_type_Float = 0.0F; }  } ((eL)localObject1).jdField_a_of_type_Za.a((xq)localObject2); ((eL)localObject1).jdField_b_of_type_JavaxVecmathVector3f.set(xe.a().a());
/*      */ 
/* 1053 */     this.jdField_a_of_type_EJ.a(paramxq);
/* 1054 */     eu.b();
/*      */ 
/* 1056 */     this.jdField_a_of_type_EG.a(paramxq);
/*      */ 
/* 1059 */     this.jdField_a_of_type_Ez.a(paramxq);
/*      */ 
/* 1062 */     this.jdField_a_of_type_Fe.a(paramxq);
/*      */ 
/* 1065 */     localObject2 = paramxq; Object localObject1 = this.jdField_a_of_type_CL; ij.jdField_a_of_type_JavaUtilArrayList.remove(((cL)localObject1).jdField_a_of_type_Eq); if (((((cL)localObject1).a().a().c) || (((cL)localObject1).a().a.c) || (((cL)localObject1).a().jdField_b_of_type_Boolean)) && (((cL)localObject1).jdField_a_of_type_Ct.a() != null)) { if (((cL)localObject1).d) { ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment = null; ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null; ((cL)localObject1).jdField_a_of_type_Le = null; ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = null; ((cL)localObject1).d = false; } ((cL)localObject1).jdField_a_of_type_ZE.a((xq)localObject2); ((cL)localObject1).jdField_b_of_type_ZE.a((xq)localObject2);
/*      */       try { localObject3 = ((cL)localObject1).a(); localObject2 = new Vector3f(xe.a().a()); if ((localObject3 == null) && (((cL)localObject1).jdField_a_of_type_Ct.a() != null)) ((Vector3f)localObject2).set(((cL)localObject1).jdField_a_of_type_Ct.a().a().origin); localObject4 = new Vector3f((Vector3f)localObject2);
/*      */         Vector3f localVector3f;
/* 1065 */         if (!Float.isNaN((localVector3f = new Vector3f(xe.a().c())).x)) { if (Keyboard.isKeyDown(cv.U.a())) { localObject4 = new Vector3f(((cL)localObject1).jdField_a_of_type_Ct.a().jdField_b_of_type_JavaxVecmathVector3f); localVector3f.sub((Tuple3f)localObject4, (Tuple3f)localObject2); localVector3f.normalize(); } localVector3f.scale(localObject3 != null ? 160.0F : 6.0F); ((Vector3f)localObject4).add(localVector3f); ((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = ((PhysicsExt)((cL)localObject1).jdField_a_of_type_Ct.a()).testRayCollisionPoint((Vector3f)localObject2, (Vector3f)localObject4, false, null, localObject3 != null ? ((au)localObject3).a() : null, false, ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment, true); if ((((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult)) && ((localObject2 = (CubeRayCastResult)((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null)) { ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject2).getSegment(); if (((((CubeRayCastResult)localObject2).getSegment() != null) && (((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment != null) && (!((CubeRayCastResult)localObject2).getSegment().equals(((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment))) || (!((CubeRayCastResult)localObject2).cubePos.equals(((cL)localObject1).jdField_a_of_type_O)) || (((cL)localObject1).jdField_a_of_type_Le == null)) { ((cL)localObject1).jdField_a_of_type_O.b(((CubeRayCastResult)localObject2).cubePos); ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject2).getSegment(); ((cL)localObject1).jdField_a_of_type_Le = new le(((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment, ((cL)localObject1).jdField_a_of_type_O); ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(((cL)localObject1).jdField_a_of_type_Le.a()); }  } else { ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null; ((cL)localObject1).jdField_a_of_type_Le = null; ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = null; } if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null) if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.isEnterable()) { ((cL)localObject1).jdField_a_of_type_Eq.a = ("Enter " + ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " [" + cv.v.b() + "]"); ((cL)localObject1).jdField_a_of_type_Le.a(((cL)localObject1).jdField_a_of_type_Eq.a); ij.jdField_a_of_type_JavaUtilArrayList.add(((cL)localObject1).jdField_a_of_type_Eq); } else if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getFactory() != null) { localObject2 = "[" + cv.z.b() + "]: Enter Connection Mode";
/*      */               String str1;
/* 1065 */               if ((localObject4 = ((cL)localObject1).a().jdField_a_of_type_Le) != null) if (!((le)localObject4).equals(((cL)localObject1).jdField_a_of_type_Le)) { boolean bool = ((le)localObject4).a().a().getControlElementMap().isControlling(((le)localObject4).a(((cL)localObject1).jdField_a_of_type_Q), ((cL)localObject1).jdField_a_of_type_Le.a(new q(((cL)localObject1).jdField_b_of_type_Q)), ((cL)localObject1).jdField_a_of_type_Le.a()); str1 = "[" + cv.A.b() + "]: " + (!bool ? "Connect" : "Disconnect") + " this Block to " + ElementKeyMap.getInfo(((le)localObject4).a()).getName() + "\n[" + cv.z.b() + "]: Switch Connection Mode"; } else { str1 = "[" + cv.z.b() + "]: Exit Connection Mode"; } ((cL)localObject1).jdField_a_of_type_Eq.a = ("[" + cv.w.b() + "]: Open Block Inventory\n" + str1); ((cL)localObject1).jdField_a_of_type_Le.a(((cL)localObject1).jdField_a_of_type_Eq.a); ij.jdField_a_of_type_JavaUtilArrayList.add(((cL)localObject1).jdField_a_of_type_Eq); } else if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.canActivate()) { if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getId() == 16) ((cL)localObject1).jdField_a_of_type_Eq.a = ("Make Output [" + cv.w.b() + "] " + (((cL)localObject1).jdField_a_of_type_Le.a() ? "ON" : "OFF")); else ((cL)localObject1).jdField_a_of_type_Eq.a = ("Activate " + ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " [" + cv.w.b() + "]"); ((cL)localObject1).jdField_a_of_type_Le.a(((cL)localObject1).jdField_a_of_type_Eq.a); ij.jdField_a_of_type_JavaUtilArrayList.add(((cL)localObject1).jdField_a_of_type_Eq); } if (localObject3 != null) { if (((au)localObject3).a() != null) { ((au)localObject3).a().a(); if ((((cL)localObject1).jdField_b_of_type_Le != ((au)localObject3).a()) && (((au)localObject3).a().a() != 0)) { ((cL)localObject1).jdField_b_of_type_Le = ((au)localObject3).a(); ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(((cL)localObject1).jdField_b_of_type_Le.a()); } else if (((au)localObject3).a().a() != 0); } else { ((cL)localObject1).jdField_b_of_type_Le = null; ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation = null; } ((cL)localObject1).jdField_a_of_type_Boolean = false; ((cL)localObject1).jdField_b_of_type_Boolean = false; ((cL)localObject1).c = false;
/*      */             short s;
/* 1065 */             if ((((cL)localObject1).jdField_b_of_type_Le != null) && ((s = ((cL)localObject1).jdField_b_of_type_Le.a()) != 0) && (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null)) { if (((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation != null) try { if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getControlledBy().contains(Short.valueOf(s))) ((cL)localObject1).jdField_a_of_type_Boolean = true;  } catch (ElementClassNotFoundException localElementClassNotFoundException) { localElementClassNotFoundException.printStackTrace(); } if (!((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getControlling().isEmpty()) ((cL)localObject1).jdField_b_of_type_Boolean = true;  } if ((((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation != null) && (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation == ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation)) { ((cL)localObject1).c = true; ((cL)localObject1).jdField_b_of_type_Boolean = false;
/*      */             }
/* 1065 */             String str2;
/* 1065 */             if ((localObject1 = localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation != null) { str2 = "SELECTED:\n" + ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + (((cL)localObject1).c ? "\ndeselect with " + cv.z.b() : ""); ((cL)localObject1).jdField_a_of_type_Ct.a().b(str2); } else { ((cL)localObject1).jdField_a_of_type_Ct.a().c(); } if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null) { str2 = ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + (((cL)localObject1).jdField_a_of_type_Boolean ? "\n\n(dis)connect to " + ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + "\nwith " + cv.A.b() : "") + (((cL)localObject1).jdField_b_of_type_Boolean ? "\nselect with " + cv.A.b() : ""); ((cL)localObject1).jdField_a_of_type_Ct.a().a(str2); } else { ((cL)localObject1).jdField_a_of_type_Ct.a().b(); }
/*      */ 
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */         Object localObject3;
/* 1065 */         (localObject3 = localException).printStackTrace(); System.err.println("[BUILDMODEDRAWER] " + localObject3.getClass().getSimpleName() + ": " + ((Exception)localObject3).getMessage());
/*      */       }
/*      */     }
/* 1068 */     this.jdField_a_of_type_Do.a(paramxq);
/*      */ 
/* 1070 */     h.b("update");
/*      */ 
/* 1072 */     if (h.a("update") > 15L)
/* 1073 */       System.err.println("[DRAWER][WARNING] update took " + h.a("update") + " ms");
/*      */   }
/*      */ 
/*      */   private void u() {
/* 1077 */     long l1 = System.currentTimeMillis();
/* 1078 */     this.jdField_a_of_type_EG.d();
/* 1079 */     this.jdField_a_of_type_EJ.d();
/*      */     long l2;
/* 1083 */     if ((
/* 1083 */       l2 = System.currentTimeMillis() - l1) > 
/* 1083 */       5L) {
/* 1084 */       System.err.println("[WORLDDRAWER] WARNING: CLEAR TOOK " + l2);
/*      */     }
/* 1086 */     this.jdField_a_of_type_Do.a(this.jdField_a_of_type_Ct.a());
/* 1087 */     l1 = System.currentTimeMillis();
/* 1088 */     this.jdField_a_of_type_Eu.a(this.jdField_a_of_type_Ct.a());
/* 1089 */     for (mF localmF : this.jdField_a_of_type_Ct.a().values()) {
/* 1090 */       long l4 = System.currentTimeMillis();
/*      */       long l6;
/* 1092 */       if ((localmF instanceof kd)) {
/* 1093 */         l6 = System.currentTimeMillis();
/*      */ 
/* 1095 */         long l8 = System.currentTimeMillis();
/* 1096 */         Object localObject = new er((kd)localmF); this.jdField_a_of_type_EG.jdField_a_of_type_JavaUtilArrayList.add(localObject);
/*      */         long l7;
/* 1098 */         if ((
/* 1098 */           l7 = System.currentTimeMillis() - l8) > 
/* 1098 */           3L) {
/* 1099 */           System.err.println("[BEAMDRAWER] WARNING SHIP PLUM ADD of " + localmF + " took " + l7 + " ms");
/*      */         }
/*      */ 
/* 1102 */         l8 = System.currentTimeMillis();
/* 1103 */         localObject = new eB((kd)localmF); this.jdField_a_of_type_EG.b.add(localObject);
/*      */ 
/* 1105 */         if ((
/* 1105 */           l7 = System.currentTimeMillis() - l8) > 
/* 1105 */           3L) {
/* 1106 */           System.err.println("[BEAMDRAWER] WARNING SHIP MUZZLE ADD of " + localmF + " took " + l7 + " ms");
/*      */         }
/* 1108 */         l8 = System.currentTimeMillis();
/* 1109 */         this.jdField_a_of_type_EJ.a((kd)localmF);
/*      */ 
/* 1111 */         if ((
/* 1111 */           l7 = System.currentTimeMillis() - l8) > 
/* 1111 */           3L)
/* 1112 */           System.err.println("[BEAMDRAWER] WARNING SHIP SHIELD ADD of " + localmF + " took " + l7 + " ms");
/*      */         long l9;
/* 1116 */         if ((
/* 1116 */           l9 = System.currentTimeMillis() - l6) > 
/* 1116 */           5L) {
/* 1117 */           System.err.println("[BEAMDRAWER] WARNING SHIP NORMAL ADD of " + localmF + " took " + l9 + " ms");
/*      */         }
/*      */       }
/* 1120 */       if (((localmF instanceof ki)) || ((localmF instanceof mM))) {
/* 1121 */         this.jdField_a_of_type_EJ.a((ld)localmF);
/* 1122 */         this.jdField_a_of_type_Eu.a((ld)localmF);
/*      */       }
/*      */ 
/* 1128 */       if ((
/* 1128 */         l6 = System.currentTimeMillis() - l4) > 
/* 1128 */         5L)
/* 1129 */         System.err.println("[WORLDDRAWER] WARNING: DRAWER UPDATE OF " + localmF + " took " + l6);
/*      */     }
/*      */     long l3;
/* 1133 */     if ((
/* 1133 */       l3 = System.currentTimeMillis() - l1) > 
/* 1133 */       5L) {
/* 1134 */       System.err.println("[WORLDDRAWER] WARNING: ADD TOOK " + l3);
/*      */     }
/*      */ 
/* 1138 */     l1 = System.currentTimeMillis();
/*      */     try {
/* 1140 */       this.jdField_a_of_type_Ep.d();
/*      */     }
/*      */     catch (ErrorDialogException localErrorDialogException2)
/*      */     {
/*      */       ErrorDialogException localErrorDialogException1;
/* 1141 */       (
/* 1142 */         localErrorDialogException1 = 
/* 1144 */         localErrorDialogException2).printStackTrace();
/* 1143 */       xm.a(localErrorDialogException1);
/*      */     }
/*      */     long l5;
/* 1146 */     if ((
/* 1146 */       l5 = System.currentTimeMillis() - l1) > 
/* 1146 */       5L)
/* 1147 */       System.err.println("[WORLDDRAWER] WARNING: CONNECTION UPDATE TOOK " + l5);
/*      */   }
/*      */ 
/*      */   public final ep a()
/*      */   {
/* 1154 */     return this.jdField_a_of_type_Ep;
/*      */   }
/*      */ 
/*      */   public final dF a()
/*      */   {
/* 1166 */     return this.jdField_a_of_type_DF;
/*      */   }
/*      */ 
/*      */   public final void k()
/*      */   {
/* 1172 */     this.h = true;
/*      */   }
/*      */ 
/*      */   public final eG a()
/*      */   {
/* 1178 */     return this.jdField_a_of_type_EG;
/*      */   }
/*      */ 
/*      */   public final eN a()
/*      */   {
/* 1184 */     return this.jdField_a_of_type_EN;
/*      */   }
/*      */ 
/*      */   public final eu a()
/*      */   {
/* 1190 */     return this.jdField_a_of_type_Eu;
/*      */   }
/*      */ 
/*      */   public final void l()
/*      */   {
/* 1202 */     this.i = true;
/*      */   }
/*      */ 
/*      */   public final void m()
/*      */   {
/* 1215 */     this.g = true;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  126 */     jdField_b_of_type_Boolean = false;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dj
 * JD-Core Version:    0.6.2
 */