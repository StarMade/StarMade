/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*      */ import com.bulletphysics.collision.shapes.CollisionShape;
/*      */ import com.bulletphysics.dynamics.DynamicsWorld;
/*      */ import com.bulletphysics.linearmath.AabbUtil2;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import java.util.concurrent.RejectedExecutionException;
/*      */ import java.util.concurrent.ThreadPoolExecutor;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import javax.vecmath.Vector4f;
/*      */ import org.schema.common.util.ByteUtil;
/*      */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.controller.database.DatabaseIndex;
/*      */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*      */ import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
/*      */ import org.schema.game.common.data.physics.PhysicsExt;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.server.controller.EntityAlreadyExistsException;
/*      */ import org.schema.game.server.controller.EntityNotFountException;
/*      */ import org.schema.game.server.controller.GameServerController;
/*      */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.SynchronizationContainerController;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ import org.schema.schine.network.server.ServerEntityWriterThread;
/*      */ import org.schema.schine.network.server.ServerMessage;
/*      */ 
/*      */ public class mx
/*      */   implements Ag, zS
/*      */ {
/*      */   private static kF[] jdField_a_of_type_ArrayOfKF;
/*      */   public q a;
/*      */   public vg a;
/*      */   private float jdField_a_of_type_Float;
/*  169 */   private boolean jdField_b_of_type_Boolean = false;
/*      */   private boolean jdField_c_of_type_Boolean;
/*      */   public static final q b;
/*      */   public Set a;
/*      */   private zM jdField_a_of_type_ZM;
/*  177 */   private static int jdField_a_of_type_Int = 1;
/*      */   private mv jdField_a_of_type_Mv;
/*  181 */   private int jdField_b_of_type_Int = 0;
/*      */ 
/*  186 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  187 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  188 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  189 */   private Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*  190 */   private Vector3f jdField_e_of_type_JavaxVecmathVector3f = new Vector3f(-jdField_a_of_type_Int << 4, -jdField_a_of_type_Int << 4, -jdField_a_of_type_Int << 4);
/*      */ 
/*  194 */   private Vector3f jdField_f_of_type_JavaxVecmathVector3f = new Vector3f(jdField_a_of_type_Int << 4, jdField_a_of_type_Int << 4, jdField_a_of_type_Int << 4);
/*      */ 
/*  198 */   private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*      */   private int jdField_c_of_type_Int;
/*      */   private long jdField_a_of_type_Long;
/*      */   private boolean jdField_d_of_type_Boolean;
/*  204 */   private static ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*      */ 
/*  280 */   private Object jdField_a_of_type_JavaLangObject = new Object();
/*      */   private L jdField_a_of_type_L;
/*      */   private boolean jdField_e_of_type_Boolean;
/*  470 */   private final Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*      */   private long jdField_b_of_type_Long;
/*      */   private boolean jdField_f_of_type_Boolean;
/*      */   private mI jdField_a_of_type_MI;
/* 1148 */   private float jdField_b_of_type_Float = 0.0F;
/* 1149 */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 1150 */   private Vector3f jdField_g_of_type_JavaxVecmathVector3f = new Vector3f();
/* 1151 */   private Vector3f jdField_h_of_type_JavaxVecmathVector3f = new Vector3f();
/* 1152 */   private Vector3f i = new Vector3f();
/* 1153 */   private Vector3f j = new Vector3f();
/*      */ 
/* 1155 */   private ArrayList jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/*      */   private boolean jdField_g_of_type_Boolean;
/* 1157 */   private long jdField_c_of_type_Long = -1L;
/*      */   public boolean a;
/*      */   private long jdField_d_of_type_Long;
/*      */ 
/*      */   public mx(vg paramvg)
/*      */   {
/*  175 */     this.jdField_a_of_type_JavaUtilSet = new HashSet();
/*      */ 
/*  285 */     this.jdField_a_of_type_Vg = paramvg;
/*  286 */     this.jdField_c_of_type_Int = paramvg.getNextFreeObjectId();
/*  287 */     this.jdField_a_of_type_ZM = paramvg.a().getPhysicsInstance(this);
/*  288 */     this.jdField_a_of_type_ZM.getDynamicsWorld().setInternalTickCallback(new mB(this, (byte)0), null);
/*  289 */     this.jdField_b_of_type_Long = System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   private void a(vg paramvg, q paramq1, q paramq2)
/*      */   {
/*      */     q localq1;
/*  294 */     a(localq1 = new q());
/*      */     jy localjy;
/*  296 */     (
/*  297 */       localjy = new jy(paramvg))
/*  297 */       .setSeed(Universe.getRandom().nextInt());
/*      */ 
/*  299 */     int k = Universe.getRandom().nextInt(5) - 2;
/*      */ 
/*  301 */     q localq2 = mJ.a(localq1, new q());
/*      */ 
/*  305 */     if (paramvg.a().getStellarSystemFromSecPos(localq2).a == 
/*  305 */       mD.e) {
/*  306 */       int i1 = ByteUtil.d((localq2 = this.jdField_a_of_type_Q).jdField_a_of_type_Int) - 8; int i2 = ByteUtil.d(localq2.jdField_b_of_type_Int) - 8; int m = ByteUtil.d(localq2.jdField_c_of_type_Int) - 8; Vector3f localVector3f2 = new Vector3f(8.0F, 8.0F, 8.0F); Vector3f localVector3f1 = new Vector3f(i1, i2, m); float f1 = Math.min(1.0F, localVector3f1.length() / localVector3f2.length()); f1 = 1.0F - f1;
/*      */ 
/*  308 */       for (int i3 = 0; i3 < kF.values().length; )
/*      */       {
/*  310 */         if (jdField_a_of_type_ArrayOfKF[i3].jdField_a_of_type_Float > f1) break;
/*  311 */         i3++;
/*      */       }
/*      */ 
/*  314 */       i3 = Math.min(kF.values().length - 1, Math.max(0, i3 + k));
/*  315 */       localjy.setCreatorId(jdField_a_of_type_ArrayOfKF[i3].ordinal());
/*      */     }
/*      */     else {
/*  318 */       localjy.setCreatorId(Universe.getRandom().nextInt(kF.values().length));
/*      */     }
/*      */ 
/*  324 */     localjy.setUniqueIdentifier("ENTITY_FLOATINGROCK_" + System.currentTimeMillis());
/*  325 */     localjy.getMinPos().b(paramq1);
/*  326 */     localjy.getMaxPos().b(paramq2);
/*  327 */     localjy.setId(paramvg.getNextFreeObjectId());
/*  328 */     localjy.setSectorId(this.jdField_c_of_type_Int);
/*      */ 
/*  330 */     localjy.initialize();
/*  331 */     int n = 0;
/*  332 */     long l1 = System.currentTimeMillis();
/*  333 */     while ((a(localjy, localq1) != null) && (n < 1000)) {
/*  334 */       a(localq1);
/*  335 */       n++;
/*      */     }
/*      */     long l2;
/*  338 */     if ((
/*  338 */       l2 = System.currentTimeMillis() - l1) > 
/*  338 */       10L) {
/*  339 */       System.err.println("[SECTOR] Placing ROCK took " + l2 + "ms");
/*      */     }
/*  341 */     if (n < 1000) {
/*  342 */       this.jdField_a_of_type_JavaUtilArrayList.add(localjy);
/*  343 */       paramvg.a().a().addImmediateSynchronizedObject(localjy); return;
/*      */     }
/*      */     try {
/*  346 */       throw new RuntimeException("Could not place rock " + localjy.getMinPos() + "; " + localjy.getMaxPos()); } catch (RuntimeException localRuntimeException) { localRuntimeException
/*  347 */         .printStackTrace();
/*      */ 
/*  349 */       System.out.println("[ERRORLOG][SECTOR] PRINTING AABB of all objects");
/*  350 */       for (paramvg = this.jdField_a_of_type_JavaUtilArrayList.iterator(); paramvg.hasNext(); )
/*  351 */         if (((
/*  351 */           paramq1 = (SegmentController)paramvg.next()) instanceof zL))
/*      */         {
/*  352 */           if ((!(paramq1 instanceof mF)) || (((mF)paramq1).getSectorId() == localjy.getSectorId())) {
/*  353 */             (
/*  357 */               paramq2 = (zL)paramq1)
/*  357 */               .getPhysicsDataContainer().getShape().getAabb(paramq2.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*  358 */             System.out.println("[ERRORLOG][SECTOR] " + paramq1 + ": [" + this.jdField_c_of_type_JavaxVecmathVector3f + " " + this.jdField_d_of_type_JavaxVecmathVector3f + "]");
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   private Sendable a(SegmentController paramSegmentController, q paramq)
/*      */   {
/*  367 */     long l1 = System.currentTimeMillis();
/*      */     SegmentController localSegmentController;
/*      */     Iterator localIterator;
/*  368 */     if ((paramSegmentController instanceof SegmentController)) {
/*  369 */       (
/*  370 */         localSegmentController = paramSegmentController)
/*  370 */         .getInitialTransform().setIdentity();
/*  371 */       localSegmentController.getInitialTransform().origin.set(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.jdField_c_of_type_Int);
/*      */ 
/*  373 */       this.jdField_e_of_type_JavaxVecmathVector3f.set(localSegmentController.getMinPos().jdField_a_of_type_Int << 4, localSegmentController.getMinPos().jdField_b_of_type_Int << 4, localSegmentController.getMinPos().jdField_c_of_type_Int << 4);
/*      */ 
/*  377 */       this.jdField_f_of_type_JavaxVecmathVector3f.set(localSegmentController.getMaxPos().jdField_a_of_type_Int << 4, localSegmentController.getMaxPos().jdField_b_of_type_Int << 4, localSegmentController.getMaxPos().jdField_c_of_type_Int << 4);
/*      */ 
/*  383 */       AabbUtil2.transformAabb(this.jdField_e_of_type_JavaxVecmathVector3f, this.jdField_f_of_type_JavaxVecmathVector3f, 100.0F, localSegmentController.getInitialTransform(), this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f);
/*      */ 
/*  386 */       for (localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); )
/*  387 */         if (((
/*  387 */           paramSegmentController = (SegmentController)localIterator.next()) instanceof zL))
/*      */         {
/*  388 */           if ((!(paramSegmentController instanceof mF)) || (((mF)paramSegmentController).getSectorId() == localSegmentController.getSectorId()))
/*      */           {
/*      */             zL localzL;
/*  389 */             (
/*  393 */               localzL = (zL)paramSegmentController)
/*  393 */               .getPhysicsDataContainer().getShape().getAabb(localzL.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*      */ 
/*  395 */             if (AabbUtil2.testAabbAgainstAabb2(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f))
/*      */             {
/*      */               long l3;
/*  397 */               if ((
/*  397 */                 l3 = System.currentTimeMillis() - l1) > 
/*  397 */                 10L) {
/*  398 */                 System.err.println("[Sector] [Sector] collision test at " + paramq + " is true: trying another pos " + l3 + "ms");
/*      */               }
/*  400 */               return paramSegmentController;
/*      */             }
/*      */           }
/*      */         }
/*      */     }
/*      */     long l2;
/*  406 */     if ((
/*  406 */       l2 = System.currentTimeMillis() - l1) > 
/*  406 */       10L) {
/*  407 */       System.err.println("[Sector] No Collission: " + l2 + "ms");
/*      */     }
/*  409 */     return null;
/*      */   }
/*      */ 
/*      */   public final Sendable a(mF parammF, Vector3f paramVector3f)
/*      */   {
/*  414 */     long l1 = System.currentTimeMillis();
/*      */     Object localObject1;
/*  415 */     (
/*  416 */       localObject1 = new Transform()).basis
/*  416 */       .set(parammF.getWorldTransform().basis);
/*  417 */     ((Transform)localObject1).origin.set(paramVector3f);
/*      */ 
/*  419 */     parammF.getRemoteTransformable().getPhysicsDataContainer().getShape().getAabb((Transform)localObject1, this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f);
/*      */     Iterator localIterator;
/*  424 */     synchronized (this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  425 */       for (localIterator = this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*  426 */         if (((
/*  426 */           localObject1 = (Sendable)localIterator.next()) instanceof zL))
/*      */         {
/*  427 */           if ((!(localObject1 instanceof mF)) || (((mF)localObject1).getSectorId() == parammF.getSectorId()))
/*      */           {
/*      */             zL localzL;
/*  430 */             (
/*  434 */               localzL = (zL)localObject1)
/*  434 */               .getPhysicsDataContainer().getShape().getAabb(localzL.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*      */ 
/*  441 */             if (AabbUtil2.testAabbAgainstAabb2(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f))
/*      */             {
/*      */               long l3;
/*  445 */               if ((
/*  445 */                 l3 = System.currentTimeMillis() - l1) > 
/*  445 */                 10L) {
/*  446 */                 System.err.println("[Sector] collision test at " + paramVector3f + " is true: trying another pos " + this.jdField_a_of_type_JavaxVecmathVector3f + ", " + this.jdField_b_of_type_JavaxVecmathVector3f + " ---> " + this.jdField_c_of_type_JavaxVecmathVector3f + ", " + this.jdField_d_of_type_JavaxVecmathVector3f + ": " + l3 + "ms");
/*      */               }
/*      */ 
/*  449 */               return localObject1;
/*      */             }
/*      */           }
/*      */         }
/*      */     }
/*      */     long l2;
/*  455 */     if ((
/*  455 */       l2 = System.currentTimeMillis() - l1) > 
/*  455 */       10L) {
/*  456 */       System.err.println("[Sector] No Collission: " + l2 + "ms");
/*      */     }
/*  458 */     return null;
/*      */   }
/*      */   public final void a() {
/*  461 */     synchronized (this.jdField_a_of_type_JavaLangObject)
/*      */     {
/*  463 */       ((ModifiedDynamicsWorld)this.jdField_a_of_type_ZM.getDynamicsWorld()).clean();
/*  464 */       this.jdField_a_of_type_Vg.a().addToFreePhysics(this.jdField_a_of_type_ZM, this);
/*  465 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void fromTagStructure(Ad paramAd)
/*      */   {
/*  476 */     paramAd = (Ad[])paramAd.a();
/*  477 */     this.jdField_a_of_type_Q = ((q)paramAd[0].a());
/*  478 */     if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_Q == null)) throw new AssertionError();
/*      */     Ad[] arrayOfAd;
/*  480 */     int k = (arrayOfAd = (Ad[])paramAd[1].a()).length;
/*      */     Object localObject;
/*  480 */     for (int m = 0; (m < k) && 
/*  481 */       ((
/*  481 */       localObject = arrayOfAd[m])
/*  481 */       .a() != Af.a); m++)
/*      */     {
/*  482 */       localObject = (String)((Ad)localObject).a();
/*      */ 
/*  486 */       this.jdField_a_of_type_JavaUtilSet.add(localObject);
/*      */     }
/*  488 */     if ((paramAd.length > 2) && (paramAd[2].a() == Af.d)) {
/*  489 */       this.jdField_b_of_type_Int = ((Integer)paramAd[2].a()).intValue();
/*      */     }
/*  491 */     if ((paramAd.length > 3) && (paramAd[3].a() == Af.n)) {
/*  492 */       arrayOfAd = (Ad[])paramAd[3].a();
/*      */ 
/*  494 */       for (k = 0; k < arrayOfAd.length - 1; k++)
/*      */       {
/*      */         me localme;
/*  495 */         (
/*  496 */           localme = new me())
/*  496 */           .fromTagStructure(arrayOfAd[k]);
/*  497 */         localme.b(vg.e++);
/*  498 */         if (localme.a() != 0) {
/*  499 */           this.jdField_a_of_type_JavaUtilMap.put(Integer.valueOf(localme.b()), localme);
/*      */         }
/*      */       }
/*      */     }
/*  503 */     System.err.println("Read From Disk: " + this);
/*      */   }
/*      */ 
/*      */   public Ad toTagStructure()
/*      */   {
/*      */     ArrayList localArrayList;
/*  509 */     Ad[] arrayOfAd1 = new Ad[(
/*  509 */       localArrayList = a())
/*  509 */       .size() + 1];
/*  510 */     int k = 0;
/*  511 */     for (int m = 0; m < localArrayList.size(); m++)
/*      */     {
/*  514 */       arrayOfAd1[k] = new Ad(Af.i, null, ((mF)localArrayList.get(m)).getUniqueIdentifier());
/*  515 */       k++;
/*      */     }
/*      */ 
/*  519 */     k = 0;
/*  520 */     Ad[] arrayOfAd2 = new Ad[this.jdField_a_of_type_Mv.a().size() + 1];
/*  521 */     for (me localme : this.jdField_a_of_type_Mv.a().values()) {
/*  522 */       arrayOfAd2[k] = localme.toTagStructure();
/*  523 */       k++;
/*      */     }
/*  525 */     arrayOfAd2[(arrayOfAd2.length - 1)] = new Ad(Af.a, null, null);
/*  526 */     arrayOfAd1[localArrayList.size()] = new Ad(Af.a, null, null);
/*  527 */     return new Ad(Af.n, "SECTOR", new Ad[] { new Ad(Af.k, "POS", this.jdField_a_of_type_Q), new Ad(Af.n, "idents", arrayOfAd1), new Ad(Af.d, "PROT", Integer.valueOf(this.jdField_b_of_type_Int)), new Ad(Af.n, "items", arrayOfAd2), new Ad(Af.a, null, null) });
/*      */   }
/*      */ 
/*      */   public final int a()
/*      */   {
/*  539 */     return this.jdField_c_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final long a()
/*      */   {
/*  546 */     return this.jdField_a_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final L a() {
/*  550 */     return this.jdField_a_of_type_L;
/*      */   }
/*      */ 
/*      */   public final zM a()
/*      */   {
/*  566 */     return this.jdField_a_of_type_ZM;
/*      */   }
/*      */ 
/*      */   public String getUniqueIdentifier() {
/*  570 */     return "SECTOR_" + this.jdField_a_of_type_Q.jdField_a_of_type_Int + "." + this.jdField_a_of_type_Q.jdField_b_of_type_Int + "." + this.jdField_a_of_type_Q.jdField_c_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final void d()
/*      */   {
/*  576 */     this.jdField_a_of_type_Float = Math.max(this.jdField_a_of_type_Float, 0.0F);
/*      */   }
/*      */ 
/*      */   public final boolean a()
/*      */   {
/*  588 */     return this.jdField_b_of_type_Boolean;
/*      */   }
/*      */ 
/*      */   public boolean isVolatile() {
/*  592 */     return false;
/*      */   }
/*      */ 
/*      */   public final void a(vg paramvg)
/*      */   {
/*  597 */     for (String str : this.jdField_a_of_type_JavaUtilSet)
/*  598 */       a(paramvg, str);
/*      */   }
/*      */ 
/*      */   public final void a(vg paramvg, String paramString)
/*      */   {
/*  604 */     synchronized (paramvg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  605 */       int k = 0;
/*  606 */       for (Iterator localIterator = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*      */       {
/*      */         Sendable localSendable;
/*  607 */         if ((((
/*  607 */           localSendable = (Sendable)localIterator.next()) instanceof zS)) && 
/*  607 */           (((zS)localSendable).getUniqueIdentifier().equals(paramString))) {
/*  608 */           System.err.println("[SECTOR] entity " + paramString + " is still active -> not loaded from disk again");
/*  609 */           k = 1;
/*  610 */           break;
/*      */         }
/*      */       }
/*  613 */       if (k != 0)
/*      */         return;
/*      */     }
/*      */     Object localObject1;
/*  618 */     if (paramString.startsWith("ENTITY_FLOATINGROCK_"))
/*      */     {
/*  620 */       ??? = paramString.substring(20);
/*      */ 
/*  624 */       if ((
/*  624 */         localObject1 = paramvg.a().a((String)???, 0))
/*  624 */         .size() > 0) {
/*  625 */         int m = 0;
/*  626 */         for (int n = 0; n < ((List)localObject1).size(); n++) {
/*  627 */           if ((((kw)((List)localObject1).get(n)).jdField_a_of_type_Int == 3) && (!((kw)((List)localObject1).get(n)).jdField_a_of_type_Boolean)) {
/*  628 */             Universe.loadEntity(paramvg, (kw)((List)localObject1).get(n), this);
/*  629 */             m = 1;
/*  630 */             break;
/*      */           }
/*      */         }
/*      */ 
/*  634 */         if (m != 0)
/*      */         {
/*  636 */           return;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  641 */     ??? = new File(vg.jdField_a_of_type_JavaLangString + paramString + ".ent");
/*      */     try
/*      */     {
/*  645 */       if ((
/*  645 */         localObject1 = ((File)???).getName().split("_")).length <= 0)
/*      */       {
/*  646 */         return;
/*  648 */       }a(localObject1[1], (File)???);
/*      */       return;
/*      */     } catch (Exception localException) {
/*  651 */       System.err.println("[SERVER][ERROR] Exception Loading Sector " + ((File)???).getName() + ";");
/*      */ 
/*  653 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private Sendable a(String paramString, File paramFile) {
/*  658 */     return Universe.loadEntity(this.jdField_a_of_type_Vg, paramString, paramFile, this);
/*      */   }
/*      */   public final void b() {
/*  661 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*      */   }
/*      */   public final void c() {
/*  664 */     this.jdField_a_of_type_Long = (System.currentTimeMillis() - ((Integer)vo.f.a()).intValue() * 1000 + 1000L);
/*      */   }
/*      */ 
/*      */   public final mD a()
/*      */   {
/*  670 */     return this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q)
/*  670 */       .a(this.jdField_a_of_type_Q);
/*      */   }
/*      */ 
/*      */   public final mC a() {
/*  674 */     return this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q)
/*  674 */       .a(this.jdField_a_of_type_Q);
/*      */   }
/*      */ 
/*      */   public final kk a() {
/*  678 */     return this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q)
/*  678 */       .a(this.jdField_a_of_type_Q);
/*      */   }
/*      */ 
/*      */   public final void b(vg paramvg)
/*      */   {
/*  683 */     Object localObject = paramvg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q);
/*      */ 
/*  685 */     switch (mA.a[localObject.a(this.jdField_a_of_type_Q).ordinal()]) { case 1:
/*  686 */       localObject = paramvg; paramvg = this; int k = Universe.getRandom().nextInt(5) + 2; if (Math.random() < 0.08D)
/*      */       {
/*  686 */         kf localkf;
/*  686 */         (localkf = new kf((StateInterface)localObject)).setSeed(Universe.getRandom().nextInt()); localkf.setUniqueIdentifier("ENTITY_SHOP_" + System.currentTimeMillis()); localkf.getMinPos().b(new q(0, -6, 0)); localkf.getMaxPos().b(new q(0, 6, 0)); localkf.setId(((vg)localObject).getNextFreeObjectId()); localkf.setSectorId(paramvg.jdField_c_of_type_Int); localkf.initialize(); localkf.a(); localkf.getInitialTransform().setIdentity(); localkf.getInitialTransform().origin.set(500.0F, Universe.getRandom().nextInt(250), 500.0F); ((vg)localObject).a().a().addImmediateSynchronizedObject(localkf); paramvg.jdField_a_of_type_JavaUtilArrayList.add(localkf); } for (int m = 0; m < k; m++) paramvg.a((vg)localObject, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(4), -jdField_a_of_type_Int - Universe.getRandom().nextInt(4), -jdField_a_of_type_Int - Universe.getRandom().nextInt(4)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(4), jdField_a_of_type_Int + Universe.getRandom().nextInt(4), jdField_a_of_type_Int + Universe.getRandom().nextInt(4))); return;
/*      */     case 2:
/*  687 */       kk localkk = ((mI)localObject).a(this.jdField_a_of_type_Q); localObject = paramvg; paramvg = this; switch (mA.b[localkk.ordinal()]) { case 1:
/*  687 */         return;
/*      */       case 2:
/*  687 */         paramvg.e((vg)localObject); return;
/*      */       case 3:
/*  687 */         paramvg.f((vg)localObject); } return;
/*      */     case 3:
/*  688 */       a(paramvg, ((mI)localObject).a(this.jdField_a_of_type_Q)); return;
/*      */     case 4:
/*  689 */       d(paramvg); return;
/*      */     case 5:
/*  690 */       return;
/*      */     case 6:
/*  691 */       return;
/*      */     case 7:
/*  692 */       return; }
/*  693 */     if (!jdField_h_of_type_Boolean) throw new AssertionError("unknown sector type " + ((mI)localObject).a(this.jdField_a_of_type_Q));
/*      */   }
/*      */ 
/*      */   private void d(vg paramvg)
/*      */   {
/*      */     kf localkf;
/*  726 */     (
/*  727 */       localkf = new kf(paramvg))
/*  727 */       .setSeed(Universe.getRandom().nextInt());
/*      */ 
/*  729 */     localkf.setUniqueIdentifier("ENTITY_SHOP_" + System.currentTimeMillis());
/*  730 */     localkf.getMinPos().b(new q(0, -10, 0));
/*  731 */     localkf.getMaxPos().b(new q(0, 10, 0));
/*  732 */     localkf.setId(paramvg.getNextFreeObjectId());
/*  733 */     localkf.setSectorId(this.jdField_c_of_type_Int);
/*  734 */     localkf.initialize();
/*  735 */     localkf.a();
/*  736 */     localkf.getInitialTransform().setIdentity();
/*  737 */     localkf.getInitialTransform().origin.set(0.0F, 0.0F, 0.0F);
/*  738 */     paramvg.a().a().addImmediateSynchronizedObject(localkf);
/*  739 */     this.jdField_a_of_type_JavaUtilArrayList.add(localkf);
/*      */ 
/*  742 */     int k = 1 + Universe.getRandom().nextInt(3);
/*      */ 
/*  744 */     for (int m = 0; m < k; m++)
/*  745 */       a(paramvg, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3)));
/*      */   }
/*      */ 
/*      */   private void a(vg paramvg, mC parammC)
/*      */   {
/*  753 */     q localq = new q();
/*  754 */     System.err.println("populating sector " + localq);
/*      */     jA localjA;
/*  757 */     (
/*  762 */       localjA = new jA(paramvg))
/*  762 */       .setCreatorId(parammC.ordinal());
/*  763 */     localjA.setSeed(Universe.getRandom().nextInt());
/*      */ 
/*  765 */     localjA.setUniqueIdentifier("ENTITY_PLANET_" + this.jdField_a_of_type_Q.jdField_a_of_type_Int + "_" + this.jdField_a_of_type_Q.jdField_b_of_type_Int + "_" + this.jdField_a_of_type_Q.jdField_c_of_type_Int + "_" + System.currentTimeMillis());
/*  766 */     localjA.getMinPos().b(new q(-16, 0, -16));
/*  767 */     localjA.getMaxPos().b(new q(16, 4, 16));
/*  768 */     localjA.setId(paramvg.getNextFreeObjectId());
/*  769 */     localjA.setSectorId(this.jdField_c_of_type_Int);
/*  770 */     localjA.initialize();
/*      */ 
/*  772 */     while (a(localjA, localq) != null) {
/*  773 */       localq.jdField_b_of_type_Int += 16;
/*      */     }
/*  775 */     paramvg.a().a().addImmediateSynchronizedObject(localjA);
/*  776 */     this.jdField_a_of_type_JavaUtilArrayList.add(localjA);
/*      */   }
/*      */ 
/*      */   private void e(vg paramvg)
/*      */   {
/*      */     ki localki;
/*  872 */     (
/*  873 */       localki = new ki(paramvg))
/*  873 */       .setSeed(Universe.getRandom().nextLong());
/*  874 */     localki.setUniqueIdentifier("ENTITY_SPACESTATION_" + System.currentTimeMillis());
/*      */ 
/*  876 */     localki.setRealName("Station " + this.jdField_c_of_type_Int);
/*      */ 
/*  878 */     localki.getMinPos().b(new q(-3, -3, -3));
/*      */ 
/*  880 */     localki.getMaxPos().b(new q(3, 3, 3));
/*      */ 
/*  882 */     localki.setCreatorId(kk.a.ordinal());
/*      */ 
/*  884 */     localki.setId(paramvg.getNextFreeObjectId());
/*      */ 
/*  886 */     localki.setSectorId(this.jdField_c_of_type_Int);
/*  887 */     localki.initialize();
/*  888 */     localki.getInitialTransform().setIdentity();
/*  889 */     paramvg.a().a().addImmediateSynchronizedObject(localki);
/*  890 */     this.jdField_a_of_type_JavaUtilArrayList.add(localki);
/*      */ 
/*  894 */     int k = Universe.getRandom().nextInt(4) + 2;
/*      */ 
/*  896 */     for (int m = 0; m < k; m++)
/*  897 */       a(paramvg, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3)));
/*      */   }
/*      */ 
/*      */   private void f(vg paramvg)
/*      */   {
/*      */     ki localki;
/*  905 */     (
/*  906 */       localki = new ki(paramvg))
/*  906 */       .setSeed(Universe.getRandom().nextLong());
/*  907 */     localki.setUniqueIdentifier("ENTITY_SPACESTATION_P" + System.currentTimeMillis());
/*      */ 
/*  909 */     localki.setRealName("Station " + this.jdField_c_of_type_Int);
/*      */ 
/*  911 */     localki.getMinPos().b(new q(-3, -3, -3));
/*      */ 
/*  913 */     localki.getMaxPos().b(new q(3, 3, 3));
/*      */ 
/*  915 */     localki.setCreatorId(kk.c.ordinal());
/*      */ 
/*  917 */     localki.setFactionId(-1);
/*      */ 
/*  919 */     localki.setId(paramvg.getNextFreeObjectId());
/*  920 */     localki.setSectorId(this.jdField_c_of_type_Int);
/*  921 */     localki.initialize();
/*  922 */     localki.getInitialTransform().setIdentity();
/*  923 */     paramvg.a().a().addImmediateSynchronizedObject(localki);
/*  924 */     this.jdField_a_of_type_JavaUtilArrayList.add(localki);
/*      */ 
/*  928 */     int k = Universe.getRandom().nextInt(4) + 2;
/*      */ 
/*  930 */     for (int m = 0; m < k; m++)
/*  931 */       a(paramvg, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3)));
/*      */   }
/*      */ 
/*      */   public final void a(boolean paramBoolean)
/*      */   {
/*  948 */     if ((!this.jdField_b_of_type_Boolean) && (paramBoolean)) {
/*  949 */       this.jdField_c_of_type_Boolean = false;
/*  950 */       this.jdField_d_of_type_Boolean = true;
/*  951 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  952 */       mx localmx = this; if (jdField_b_of_type_JavaUtilArrayList.isEmpty()) { localmx.jdField_a_of_type_L = new L(localmx.jdField_a_of_type_Vg, localmx.jdField_c_of_type_Int); }
/*      */       else
/*      */       {
/*      */         L localL;
/*  952 */         (localL = (L)jdField_b_of_type_JavaUtilArrayList.remove(0)).a(localmx.jdField_c_of_type_Int); localmx.jdField_a_of_type_L = localL;
/*  953 */       }synchronized (this.jdField_a_of_type_JavaLangObject) {
/*  954 */         this.jdField_b_of_type_Boolean = paramBoolean;
/*  955 */         return;
/*      */       }
/*      */     }
/*  957 */     if ((this.jdField_b_of_type_Boolean) && (!paramBoolean)) {
/*  958 */       ??? = this; if ((!jdField_h_of_type_Boolean) && (((mx)???).jdField_a_of_type_L == null)) throw new AssertionError(); jdField_b_of_type_JavaUtilArrayList.add(((mx)???).jdField_a_of_type_L); ((mx)???).jdField_a_of_type_L = null; if ((!jdField_h_of_type_Boolean) && (jdField_b_of_type_JavaUtilArrayList.get(jdField_b_of_type_JavaUtilArrayList.size() - 1) == null)) throw new AssertionError();
/*  959 */       this.jdField_b_of_type_Long = System.currentTimeMillis();
/*      */     }
/*  961 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final void a(int paramInt)
/*      */   {
/*  974 */     this.jdField_c_of_type_Int = paramInt;
/*      */   }
/*      */ 
/*      */   private static void a(q paramq) {
/*  978 */     paramq.b(0, 0, 0);
/*  979 */     while (paramq.a() < 25.0F)
/*      */     {
/*  981 */       int k = Universe.getRandom().nextInt(500) - 250;
/*  982 */       int m = Universe.getRandom().nextInt(250) - 125;
/*  983 */       int n = Universe.getRandom().nextInt(500) - 250;
/*  984 */       paramq.b(k, m, n);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1009 */     return "Sector[" + this.jdField_c_of_type_Int + "]" + this.jdField_a_of_type_Q;
/*      */   }
/*      */ 
/*      */   public final String c() {
/* 1013 */     return "Sector[" + this.jdField_c_of_type_Int + "]" + this.jdField_a_of_type_Q + "; @" + this.jdField_a_of_type_ZM;
/*      */   }
/*      */   public final void a(xq paramxq) {
/* 1016 */     if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_Boolean)) throw new AssertionError();
/* 1017 */     vg.g += 1;
/* 1018 */     if (this.jdField_g_of_type_Boolean) {
/* 1019 */       i();
/* 1020 */       this.jdField_g_of_type_Boolean = false;
/*      */     }
/*      */ 
/* 1023 */     if (this.jdField_b_of_type_Boolean) {
/* 1024 */       vg.h += 1;
/* 1025 */       if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_L == null)) throw new AssertionError();
/*      */ 
/* 1027 */       this.jdField_a_of_type_L.a(paramxq);
/*      */ 
/* 1029 */       if (this.jdField_e_of_type_Boolean) {
/* 1030 */         mx localmx = this; mD localmD = this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(localmx.jdField_a_of_type_Q).a(localmx.jdField_a_of_type_Q); if ((localmx.jdField_a_of_type_Q.equals(jdField_b_of_type_Q)) && (vo.a.a())) localmx.a(2, true); if (((localmx.jdField_b_of_type_Int & 0x1) == 1 ? 1 : 0) == 0) { if (vo.d.a()); } else { System.err.println("[SECTOR] NEW SECTOR IS PROTECTED FROM SPAWNING ANY ENEMIES"); break label251; } if ((localmD == mD.b) && (Universe.getRandom().nextInt(100) == 0)) try { localmx.jdField_a_of_type_Vg.a().a(Universe.getRandom().nextInt(8) + 3, 1, 3, tH.a, localmx.jdField_c_of_type_Int); } catch (EntityNotFountException localEntityNotFountException) { localEntityNotFountException.printStackTrace(); } catch (ErrorDialogException localErrorDialogException) { localErrorDialogException.printStackTrace(); } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) { localEntityAlreadyExistsException.printStackTrace(); }
/* 1031 */         label251: this.jdField_e_of_type_Boolean = false;
/*      */       }
/* 1033 */       this.jdField_a_of_type_ZM.update(paramxq, this.jdField_a_of_type_Float);
/* 1034 */       this.jdField_c_of_type_Boolean = false;
/* 1035 */       this.jdField_a_of_type_Float = 0.0F;
/*      */ 
/* 1037 */       if ((this.jdField_f_of_type_Boolean) && (this.jdField_a_of_type_MI.a == mD.e)) {
/* 1038 */         b(paramxq);
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1045 */       if (this.jdField_d_of_type_Boolean) {
/* 1046 */         long l1 = System.currentTimeMillis();
/* 1047 */         a(2, this.jdField_a_of_type_Vg.a());
/*      */         long l2;
/* 1049 */         if ((
/* 1049 */           l2 = System.currentTimeMillis() - l1) > 
/* 1049 */           4L) {
/* 1050 */           System.err.println("[SERVER][SECTOR] WRITING SECTOR ID " + this.jdField_c_of_type_Int + " -> " + this.jdField_a_of_type_Q + " TOOK " + l2 + "ms");
/*      */         }
/*      */       }
/* 1053 */       this.jdField_d_of_type_Boolean = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void i()
/*      */   {
/*      */     try
/*      */     {
/* 1064 */       localIterator = null; for (kw localkw : this.jdField_a_of_type_Vg.a().a(this.jdField_a_of_type_Q, 0))
/*      */       {
/*      */         try
/*      */         {
/* 1067 */           if (!this.jdField_a_of_type_Vg.c().containsKey(localkw.jdField_a_of_type_JavaLangString.trim())) {
/* 1068 */             String str = localkw.jdField_a_of_type_JavaLangString.split("_", 3)[1];
/* 1069 */             System.err.println("[REPAIR] FOUND SECTOR ENTITY: " + localkw.jdField_a_of_type_JavaLangString + " [" + str + "]");
/* 1070 */             a(str, new File(localkw.jdField_a_of_type_JavaLangString + ".ent"));
/*      */           }
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 1078 */           localIOException.printStackTrace();
/*      */         }
/*      */         catch (EntityNotFountException localEntityNotFountException)
/*      */         {
/* 1078 */           localEntityNotFountException.printStackTrace();
/*      */         }
/*      */         catch (ErrorDialogException localErrorDialogException)
/*      */         {
/* 1078 */           localErrorDialogException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException) {
/* 1081 */       Iterator localIterator = null;
/*      */ 
/* 1083 */       localSQLException.printStackTrace();
/*      */     }
/*      */ 
/* 1086 */     new File(vg.jdField_a_of_type_JavaLangString).listFiles();
/*      */   }
/*      */ 
/*      */   private void b(xq arg1)
/*      */   {
/* 1161 */     this.jdField_b_of_type_Float += ???.a();
/* 1162 */     ??? = mI.b(this.jdField_a_of_type_Q) ? 0.005F : 0.5F;
/*      */ 
/* 1165 */     float f1 = mI.b(this.jdField_a_of_type_Q) ? 666.0F : 80.0F;
/*      */ 
/* 1170 */     if (this.jdField_b_of_type_Float > ???)
/*      */     {
/* 1174 */       Universe.calcSunPosInnerStarSystem(this.jdField_a_of_type_Q, this.jdField_a_of_type_MI, this.jdField_a_of_type_Vg.a().calculateStartTime(), this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*      */ 
/* 1176 */       this.jdField_c_of_type_JavaUtilArrayList.clear();
/*      */       Iterator localIterator;
/* 1177 */       synchronized (this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 1178 */         for (localIterator = this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); localIterator.hasNext(); )
/*      */         {
/*      */           Sendable localSendable;
/* 1179 */           if ((((
/* 1179 */             localSendable = (Sendable)localIterator.next()) instanceof kd)) && 
/* 1179 */             (((SegmentController)localSendable).getSectorId() == this.jdField_c_of_type_Int)) {
/* 1180 */             this.jdField_c_of_type_JavaUtilArrayList.add((EditableSendableSegmentController)localSendable);
/*      */           }
/*      */         }
/*      */       }
/* 1184 */       if (this.jdField_c_of_type_JavaUtilArrayList.isEmpty()) {
/* 1185 */         return;
/*      */       }
/* 1187 */       ??? = (EditableSendableSegmentController)this.jdField_c_of_type_JavaUtilArrayList.get(Universe.getRandom().nextInt(this.jdField_c_of_type_JavaUtilArrayList.size()));
/*      */ 
/* 1191 */       this.jdField_h_of_type_JavaxVecmathVector3f.set(???.getWorldTransform().origin);
/*      */ 
/* 1193 */       float f2 = ???.getSegmentBuffer().a().a() / 
/* 1193 */         3.0F;
/*      */ 
/* 1196 */       this.jdField_g_of_type_JavaxVecmathVector3f.sub(this.jdField_h_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 1197 */       float f3 = this.jdField_g_of_type_JavaxVecmathVector3f.length();
/* 1198 */       this.jdField_g_of_type_JavaxVecmathVector3f.normalize();
/* 1199 */       this.i.set(0.0F, 1.0F, 0.0F);
/*      */ 
/* 1201 */       this.j.cross(this.jdField_g_of_type_JavaxVecmathVector3f, this.i);
/* 1202 */       this.j.normalize();
/* 1203 */       this.i.cross(this.j, this.jdField_g_of_type_JavaxVecmathVector3f);
/* 1204 */       this.i.normalize();
/*      */ 
/* 1206 */       this.jdField_g_of_type_JavaxVecmathVector3f.scale(f3 + f2);
/*      */ 
/* 1208 */       this.jdField_h_of_type_JavaxVecmathVector3f.add(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_g_of_type_JavaxVecmathVector3f);
/*      */ 
/* 1216 */       this.i.scale(f2 * ((Universe.getRandom().nextFloat() - 0.5F) * 2.0F));
/* 1217 */       this.j.scale(f2 * ((Universe.getRandom().nextFloat() - 0.5F) * 2.0F));
/* 1218 */       this.jdField_h_of_type_JavaxVecmathVector3f.add(this.i);
/* 1219 */       this.jdField_h_of_type_JavaxVecmathVector3f.add(this.j);
/*      */       Object localObject2;
/* 1221 */       if (xu.F.b()) {
/* 1222 */         localObject2 = new ym(new Vector3f(this.jdField_h_of_type_JavaxVecmathVector3f), new Vector4f(1.0F, 1.0F, 0.0F, 1.0F));
/* 1223 */         yk.b.add(localObject2);
/*      */ 
/* 1225 */         localObject2 = new xQ(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_h_of_type_JavaxVecmathVector3f);
/* 1226 */         yk.c.add(localObject2);
/*      */       }
/*      */ 
/* 1231 */       if (((
/* 1231 */         localObject2 = ((PhysicsExt)this.jdField_a_of_type_ZM).testRayCollisionPoint(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_h_of_type_JavaxVecmathVector3f, false, null, null, true, null, false)) != null) && 
/* 1231 */         (((CollisionWorld.ClosestRayResultCallback)localObject2).hasHit()) && ((localObject2 instanceof CubeRayCastResult)) && 
/* 1232 */         (((CubeRayCastResult)localObject2).getSegment() != null) && 
/* 1233 */         (((CubeRayCastResult)localObject2).getSegment().a() == ???)) {
/* 1234 */         ???.handleHit((CollisionWorld.ClosestRayResultCallback)localObject2, null, f1);
/* 1235 */         if (((??? instanceof cw)) && (System.currentTimeMillis() - this.jdField_d_of_type_Long > 3000L))
/*      */         {
/* 1237 */           for (localObject2 = ((cw)???).a().iterator(); ((Iterator)localObject2).hasNext(); ) ( = (lE)((Iterator)localObject2).next())
/* 1238 */               .a(new ServerMessage("WARNING!\nthe heat of the sun is\ndamaging the hulls!\nHide behind natural objects!\nINTESITY: " + f1, 3, ???.getId()));
/*      */ 
/* 1241 */           this.jdField_d_of_type_Long = System.currentTimeMillis();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1249 */       this.jdField_b_of_type_Float = 0.0F;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final ArrayList a()
/*      */   {
/*      */     ArrayList localArrayList;
/*      */     while (true)
/*      */     {
/* 1298 */       localArrayList = new ArrayList();
/*      */       try
/*      */       {
/* 1302 */         for (localIterator = this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*      */         {
/*      */           Object localObject;
/* 1303 */           if ((((
/* 1303 */             localObject = (Sendable)localIterator.next()) instanceof mF)) && 
/* 1303 */             (((mF)localObject).getSectorId() == this.jdField_c_of_type_Int))
/*      */           {
/* 1305 */             (
/* 1306 */               localObject = (mF)localObject)
/* 1306 */               .getWorldTransform();
/* 1307 */             localArrayList.add(localObject);
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (ConcurrentModificationException localConcurrentModificationException)
/*      */       {
/*      */         Iterator localIterator;
/* 1314 */         localConcurrentModificationException.printStackTrace();
/*      */ 
/* 1312 */         System.err.println("CATCHED EXCEPTION!!!!!!!!!!!!!!!!!! (sector entity calc)");
/*      */       }
/*      */     }
/* 1315 */     return localArrayList;
/*      */   }
/*      */ 
/*      */   public final void a(int paramInt, Universe paramUniverse)
/*      */   {
/* 1354 */     ArrayList localArrayList = a();
/*      */ 
/* 1364 */     paramInt = new mz(this, localArrayList, paramInt, paramUniverse);
/*      */     try
/*      */     {
/* 1402 */       this.jdField_a_of_type_Vg.getThreadQueue().enqueue(paramInt);
/*      */       return;
/*      */     }
/*      */     catch (RejectedExecutionException localRejectedExecutionException)
/*      */     {
/* 1406 */       localRejectedExecutionException.printStackTrace();
/*      */ 
/* 1405 */       System.err.println(this.jdField_a_of_type_Vg.getThreadPool().getActiveCount() + "/" + this.jdField_a_of_type_Vg.getThreadPool().getMaximumPoolSize());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final int b()
/*      */   {
/* 1412 */     return this.jdField_b_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final void b(int paramInt)
/*      */   {
/* 1418 */     this.jdField_b_of_type_Int = paramInt;
/*      */   }
/*      */   public final void e() {
/* 1421 */     this.jdField_e_of_type_Boolean = true;
/*      */   }
/*      */   public final void b(boolean paramBoolean) {
/* 1424 */     a(2, paramBoolean);
/*      */   }
/*      */   public final void c(boolean paramBoolean) {
/* 1427 */     a(1, paramBoolean);
/*      */   }
/*      */   private void a(int paramInt, boolean paramBoolean) {
/* 1430 */     if (paramBoolean) {
/* 1431 */       if ((this.jdField_b_of_type_Int & paramInt) != paramInt) {
/* 1432 */         this.jdField_b_of_type_Int += paramInt;
/*      */       }
/*      */     }
/* 1435 */     else if ((this.jdField_b_of_type_Int & paramInt) == paramInt)
/* 1436 */       this.jdField_b_of_type_Int -= paramInt;
/*      */   }
/*      */ 
/*      */   public final boolean b()
/*      */   {
/* 1441 */     return (this.jdField_b_of_type_Int & 0x2) == 2;
/*      */   }
/*      */ 
/*      */   public final void f()
/*      */   {
/* 1447 */     if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_Mv != null)) throw new AssertionError();
/* 1448 */     this.jdField_a_of_type_Mv = new mv(this.jdField_a_of_type_Vg);
/* 1449 */     this.jdField_a_of_type_Mv.a(this);
/* 1450 */     this.jdField_a_of_type_Mv.a(this.jdField_a_of_type_JavaUtilMap);
/* 1451 */     this.jdField_a_of_type_Vg.a().a().addNewSynchronizedObjectQueued(this.jdField_a_of_type_Mv);
/*      */     q localq;
/* 1453 */     int m = ByteUtil.d((localq = this.jdField_a_of_type_Q).jdField_a_of_type_Int) - 8; int n = ByteUtil.d(localq.jdField_b_of_type_Int) - 8; int k = ByteUtil.d(localq.jdField_c_of_type_Int) - 8; this.jdField_f_of_type_Boolean = (p.a(m, n, k) < 1.42F);
/* 1454 */     mI localmI = this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q);
/* 1455 */     this.jdField_a_of_type_MI = localmI;
/* 1456 */     localmI.a(this.jdField_a_of_type_Q);
/*      */   }
/*      */ 
/*      */   public final mv a()
/*      */   {
/* 1463 */     return this.jdField_a_of_type_Mv;
/*      */   }
/*      */ 
/*      */   public final Map a()
/*      */   {
/* 1472 */     return this.jdField_a_of_type_Mv.a();
/*      */   }
/*      */ 
/*      */   public final boolean c()
/*      */   {
/* 1478 */     return this.jdField_c_of_type_Boolean;
/*      */   }
/*      */ 
/*      */   public final boolean a(long paramLong)
/*      */   {
/*      */     int k;
/* 1482 */     return ((
/* 1482 */       k = ((Integer)vo.g.a()).intValue()) >= 0) && 
/* 1482 */       (paramLong > this.jdField_b_of_type_Long + k * 1000);
/*      */   }
/*      */   public static boolean a(q paramq1, q paramq2) {
/* 1485 */     (
/* 1486 */       paramq2 = new q(paramq2))
/* 1486 */       .c(paramq1);
/* 1487 */     return paramq2.a() < 1.42F;
/*      */   }
/*      */ 
/*      */   public final void g()
/*      */   {
/* 1493 */     this.jdField_a_of_type_ZM = null;
/*      */   }
/*      */ 
/*      */   public final String b() {
/* 1497 */     return "[PHYSICS][SERVER] WARNING: PHYSICS SYNC IN DANGER. SECTOR: " + this.jdField_a_of_type_Q + " [" + this.jdField_c_of_type_Int + "]";
/*      */   }
/*      */ 
/*      */   public final void h() {
/* 1501 */     this.jdField_g_of_type_Boolean = true;
/*      */   }
/*      */ 
/*      */   public final long b()
/*      */   {
/* 1508 */     return this.jdField_c_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final void a(long paramLong)
/*      */   {
/* 1514 */     this.jdField_c_of_type_Long = paramLong;
/*      */   }
/*      */ 
/*      */   public static byte[] a(Map paramMap) {
/* 1518 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramMap.size() * 18);
/* 1519 */     DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
/* 1520 */     for (Map.Entry localEntry : paramMap.entrySet()) {
/* 1521 */       localDataOutputStream.writeShort(((me)localEntry.getValue()).a());
/* 1522 */       localDataOutputStream.writeInt(((me)localEntry.getValue()).a());
/* 1523 */       localDataOutputStream.writeFloat(((me)localEntry.getValue()).a().x);
/* 1524 */       localDataOutputStream.writeFloat(((me)localEntry.getValue()).a().y);
/* 1525 */       localDataOutputStream.writeFloat(((me)localEntry.getValue()).a().z);
/*      */     }
/* 1527 */     localByteArrayOutputStream.flush();
/* 1528 */     return localByteArrayOutputStream.toByteArray();
/*      */   }
/*      */ 
/*      */   public final void a(byte[] paramArrayOfByte)
/*      */   {
/* 1554 */     DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfByte));
/* 1555 */     paramArrayOfByte = paramArrayOfByte.length / 18;
/* 1556 */     System.err.println("[SERVER][SECTOR] READING " + paramArrayOfByte + " ITEMS");
/* 1557 */     for (byte[] arrayOfByte = 0; arrayOfByte < paramArrayOfByte; arrayOfByte++)
/*      */     {
/*      */       me localme;
/* 1558 */       (
/* 1559 */         localme = new me())
/* 1559 */         .b(vg.b());
/* 1560 */       localme.a(localDataInputStream.readShort());
/* 1561 */       localme.a(localDataInputStream.readInt());
/* 1562 */       localme.a(new Vector3f(localDataInputStream.readFloat(), localDataInputStream.readFloat(), localDataInputStream.readFloat()));
/*      */ 
/* 1564 */       if (localme.a() != 0) {
/* 1565 */         System.err.println("[SERVER][SECTOR] LOADED ITEM " + localme.a() + ": " + localme.a() + " at " + localme.a() + " with ID: " + localme.b());
/* 1566 */         this.jdField_a_of_type_JavaUtilMap.put(Integer.valueOf(localme.b()), localme);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/* 1571 */   public final void c(vg paramvg) { this.jdField_a_of_type_JavaUtilSet = paramvg.a().a(this.jdField_a_of_type_Q); }
/*      */ 
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/* 1578 */     return this.jdField_c_of_type_Int == ((mx)paramObject).jdField_c_of_type_Int;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1585 */     return this.jdField_c_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final float b() {
/* 1589 */     if (this.jdField_a_of_type_Vg.a() != null) {
/* 1590 */       return this.jdField_a_of_type_Vg.a().b();
/*      */     }
/* 1592 */     throw new NullPointerException();
/*      */   }
/*      */ 
/*      */   public final float c()
/*      */   {
/* 1598 */     if (this.jdField_a_of_type_Vg.a() != null) {
/* 1599 */       return this.jdField_a_of_type_Vg.a().c();
/*      */     }
/* 1601 */     throw new NullPointerException();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   92 */     jdField_a_of_type_ArrayOfKF = new kF[kF.values().length];
/*   93 */     for (int k = 0; k < jdField_a_of_type_ArrayOfKF.length; k++) {
/*   94 */       jdField_a_of_type_ArrayOfKF[k] = kF.values()[k];
/*      */     }
/*   96 */     Arrays.sort(jdField_a_of_type_ArrayOfKF, new my());
/*      */ 
/*  103 */     for (k = 0; k < jdField_a_of_type_ArrayOfKF.length; k++);
/*  173 */     jdField_b_of_type_Q = new q(2, 2, 2);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mx
 * JD-Core Version:    0.6.2
 */