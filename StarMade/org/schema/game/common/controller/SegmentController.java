/*      */ package org.schema.game.common.controller;
/*      */ 
/*      */ import Ad;
/*      */ import Af;
/*      */ import C;
/*      */ import Q;
/*      */ import ag;
/*      */ import ah;
/*      */ import az;
/*      */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*      */ import com.bulletphysics.collision.shapes.BoxShape;
/*      */ import com.bulletphysics.collision.shapes.CompoundShape;
/*      */ import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*      */ import com.bulletphysics.collision.shapes.ConvexShape;
/*      */ import com.bulletphysics.dynamics.DynamicsWorld;
/*      */ import com.bulletphysics.dynamics.RigidBody;
/*      */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*      */ import com.bulletphysics.linearmath.AabbUtil2;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import ct;
/*      */ import dw;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import jF;
/*      */ import jL;
/*      */ import jP;
/*      */ import jQ;
/*      */ import jR;
/*      */ import jY;
/*      */ import java.io.File;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import ju;
/*      */ import jv;
/*      */ import jx;
/*      */ import kG;
/*      */ import km;
/*      */ import ld;
/*      */ import le;
/*      */ import mF;
/*      */ import mx;
/*      */ import o;
/*      */ import org.schema.common.FastMath;
/*      */ import org.schema.common.util.ByteUtil;
/*      */ import org.schema.game.common.controller.database.DatabaseIndex;
/*      */ import org.schema.game.common.controller.elements.ManagerContainer;
/*      */ import org.schema.game.common.data.element.ControlElementMap;
/*      */ import org.schema.game.common.data.element.ElementClassNotFoundException;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.physics.CubeShape;
/*      */ import org.schema.game.common.data.physics.CubesCompoundShape;
/*      */ import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
/*      */ import org.schema.game.common.data.physics.PhysicsExt;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.common.data.world.SegmentData;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.server.controller.GameServerController;
/*      */ import org.schema.schine.graphicsengine.camera.Camera;
/*      */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ import org.schema.schine.network.server.ServerStateInterface;
/*      */ import q;
/*      */ import sB;
/*      */ import tR;
/*      */ import vg;
/*      */ import vo;
/*      */ import x;
/*      */ import xO;
/*      */ import xR;
/*      */ import xe;
/*      */ import xq;
/*      */ import xu;
/*      */ import yk;
/*      */ import zL;
/*      */ import zM;
/*      */ import zS;
/*      */ 
/*      */ public abstract class SegmentController extends mF
/*      */   implements C, sB
/*      */ {
/*      */   private final jv dockingController;
/*      */   private jY segmentProvider;
/*      */   private int creatorId;
/*      */   private kG creatorThread;
/*  115 */   private int id = -1234;
/*  116 */   private final q maxPos = new q();
/*  117 */   private final q minPos = new q();
/*  118 */   private String realName = "undef";
/*  119 */   private final Transform worldTransformInverse = new Transform();
/*  120 */   private final jx elementClassCountMap = new jx();
/*  121 */   private int totalElements = 0;
/*      */   private final jR collisionChecker;
/*  123 */   private final q testPos = new q();
/*      */   private final jL segmentBuffer;
/*  125 */   private final Vector3f camPosLocal = new Vector3f((1.0F / 1.0F), (1.0F / 1.0F), (1.0F / 1.0F));
/*  126 */   private final Vector3f camForwLocal = new Vector3f();
/*      */ 
/*  128 */   private final Vector3f camLeftLocal = new Vector3f();
/*      */ 
/*  130 */   private final Vector3f camUpLocal = new Vector3f();
/*      */   private final ControlElementMap controlElementMap;
/*      */   private String uniqueIdentifier;
/*  136 */   protected final o tmpLocalPos = new o();
/*  137 */   protected final q posTmp = new q();
/*      */   private long timeCreated;
/*  141 */   private final Vector3f oMin = new Vector3f();
/*  142 */   private final Vector3f oMax = new Vector3f();
/*      */   private boolean aabbRecalcFlag;
/*      */   protected boolean flagUpdateMass;
/*      */   private boolean flagCheckDocking;
/*      */   public boolean flagPhysicsAABBUpdate;
/*      */   private boolean flagSegmentBufferAABBUpdate;
/*  148 */   private String spawner = "";
/*  149 */   private String lastModifier = "";
/*      */   private long seed;
/*  151 */   private final ArrayList needsActiveUpdateClient = new ArrayList();
/*      */   private long delayDockingCheck;
/*      */   private int lastSector;
/*  154 */   private final Transform clientTransformInverse = new Transform();
/*  155 */   private final Vector3f camLocalTmp = new Vector3f();
/*      */ 
/*      */   public static void setContraintFrameOrientation(byte paramByte, Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
/*      */   {
/*   75 */     switch (paramByte) {
/*      */     case 5:
/*   77 */       paramTransform.basis.setRow(0, -paramVector3f1.x, -paramVector3f2.x, -paramVector3f3.x);
/*   78 */       paramTransform.basis.setRow(1, -paramVector3f1.y, -paramVector3f2.y, -paramVector3f3.y);
/*   79 */       paramTransform.basis.setRow(2, -paramVector3f1.z, -paramVector3f2.z, -paramVector3f3.z);
/*   80 */       return;
/*      */     case 4:
/*      */     default:
/*   83 */       paramTransform.basis.setRow(0, paramVector3f1.x, paramVector3f2.x, paramVector3f3.x);
/*   84 */       paramTransform.basis.setRow(1, paramVector3f1.y, paramVector3f2.y, paramVector3f3.y);
/*   85 */       paramTransform.basis.setRow(2, paramVector3f1.z, paramVector3f2.z, paramVector3f3.z);
/*   86 */       return;
/*      */     case 0:
/*   88 */       paramTransform.basis.setRow(0, -paramVector3f3.x, -paramVector3f2.x, -paramVector3f1.x);
/*   89 */       paramTransform.basis.setRow(1, -paramVector3f3.y, -paramVector3f2.y, -paramVector3f1.y);
/*   90 */       paramTransform.basis.setRow(2, -paramVector3f3.z, -paramVector3f2.z, -paramVector3f1.z);
/*   91 */       return;
/*      */     case 1:
/*   93 */       paramTransform.basis.setRow(0, paramVector3f3.x, -paramVector3f2.x, paramVector3f1.x);
/*   94 */       paramTransform.basis.setRow(1, paramVector3f3.y, -paramVector3f2.y, paramVector3f1.y);
/*   95 */       paramTransform.basis.setRow(2, paramVector3f3.z, -paramVector3f2.z, paramVector3f1.z);
/*   96 */       return;
/*      */     case 2:
/*   98 */       paramTransform.basis.setRow(0, paramVector3f3.x, paramVector3f1.x, paramVector3f2.x);
/*   99 */       paramTransform.basis.setRow(1, paramVector3f3.y, paramVector3f1.y, paramVector3f2.y);
/*  100 */       paramTransform.basis.setRow(2, paramVector3f3.z, paramVector3f1.z, paramVector3f2.z);
/*  101 */       return;
/*      */     case 3:
/*  103 */     }paramTransform.basis.setRow(0, -paramVector3f3.x, paramVector3f1.x, -paramVector3f2.x);
/*  104 */     paramTransform.basis.setRow(1, -paramVector3f3.y, paramVector3f1.y, -paramVector3f2.y);
/*  105 */     paramTransform.basis.setRow(2, -paramVector3f3.z, paramVector3f1.z, -paramVector3f2.z);
/*      */   }
/*      */ 
/*      */   public SegmentController(StateInterface paramStateInterface)
/*      */   {
/*  159 */     super(paramStateInterface);
/*  160 */     this.collisionChecker = new jR(this);
/*  161 */     if ((paramStateInterface instanceof ServerStateInterface))
/*  162 */       this.segmentProvider = new tR(this);
/*      */     else {
/*  164 */       this.segmentProvider = new Q(this);
/*      */     }
/*      */ 
/*  167 */     this.segmentBuffer = new SegmentBufferManager(this);
/*      */ 
/*  170 */     this.controlElementMap = new ControlElementMap();
/*      */ 
/*  178 */     setTimeCreated(System.currentTimeMillis());
/*  179 */     this.dockingController = new jv(this);
/*      */   }
/*      */ 
/*      */   public void aabbRecalcFlag()
/*      */   {
/*  184 */     this.aabbRecalcFlag = true;
/*      */   }
/*      */ 
/*      */   public void cleanUpOnEntityDelete()
/*      */   {
/*  189 */     super.cleanUpOnEntityDelete();
/*      */     try
/*      */     {
/*  192 */       getSegmentProvider().f();
/*      */     }
/*      */     catch (IOException localIOException) {
/*  195 */       localIOException.printStackTrace();
/*      */     }
/*      */ 
/*  196 */     getCreatorThread().a = false;
/*      */   }
/*      */ 
/*      */   public void createConstraint(zL paramzL1, zL paramzL2, Object paramObject)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void decTotalElements()
/*      */   {
/*  419 */     this.totalElements -= 1;
/*  420 */     flagupdateMass();
/*  421 */     flagUpdateDocking();
/*      */   }
/*      */   public boolean existsNeighborSegment(q paramq, int paramInt) {
/*  424 */     getNeighborSegmentPos(paramq, paramInt, this.testPos);
/*  425 */     return this.segmentBuffer.a(this.testPos);
/*      */   }
/*      */   public void flagUpdateDocking() {
/*  428 */     this.flagCheckDocking = true;
/*      */   }
/*      */ 
/*      */   public void flagupdateMass()
/*      */   {
/*  435 */     if (vg.jdField_f_of_type_Int == getId()) {
/*      */       try {
/*  437 */         throw new IllegalArgumentException("MASS SET DEBUG"); } catch (Exception localException) { localException
/*  438 */           .printStackTrace();
/*      */       }
/*      */     }
/*      */ 
/*  442 */     this.flagUpdateMass = true;
/*      */   }
/*      */ 
/*      */   public void setSpawner(String paramString)
/*      */   {
/*  453 */     this.spawner = paramString;
/*      */   }
/*      */   public void setLastModified(String paramString) {
/*  456 */     this.lastModifier = paramString;
/*      */   }
/*      */ 
/*      */   public void fromTagStructure(Ad paramAd)
/*      */   {
/*  467 */     assert (paramAd.a().equals("sc"));
/*      */ 
/*  469 */     paramAd = (Ad[])paramAd.a();
/*      */ 
/*  471 */     setUniqueIdentifier((String)paramAd[0].a());
/*      */ 
/*  474 */     getMinPos().b((q)paramAd[1].a());
/*  475 */     getMaxPos().b((q)paramAd[2].a());
/*      */ 
/*  478 */     getDockingController().a(paramAd[3]);
/*      */ 
/*  480 */     getControlElementMap().fromTagStructure(paramAd[4]);
/*      */ 
/*  482 */     setRealName((String)paramAd[5].a());
/*      */ 
/*  484 */     super.fromTagStructure(paramAd[6]);
/*      */ 
/*  486 */     if ((this instanceof ld)) {
/*  487 */       ((ld)this).a().fromTagStructure(paramAd[7]);
/*      */     }
/*      */ 
/*  490 */     setCreatorId(((Integer)paramAd[8].a()).intValue());
/*  491 */     if ((paramAd.length > 9) && (paramAd[9].a() == Af.i)) {
/*  492 */       this.spawner = ((String)paramAd[9].a());
/*      */     }
/*      */ 
/*  495 */     if ((paramAd.length > 10) && (paramAd[10].a() == Af.i)) {
/*  496 */       this.lastModifier = ((String)paramAd[10].a());
/*      */     }
/*  498 */     if ((paramAd.length > 11) && (paramAd[11].a() == Af.e))
/*  499 */       this.seed = ((Long)paramAd[11].a()).longValue();
/*      */     else {
/*  501 */       this.seed = Universe.getRandom().nextLong();
/*      */     }
/*  503 */     if ((paramAd.length > 12) && (paramAd[12].a() == Af.b)) {
/*  504 */       if ((this instanceof km)) {
/*  505 */         ((km)this).a(((Byte)paramAd[12].a()).byteValue() == 1);
/*      */       }
/*      */ 
/*      */     }
/*  510 */     else if ((this instanceof km))
/*  511 */       ((km)this).a(true);
/*      */   }
/*      */ 
/*      */   public Ad toTagStructure()
/*      */   {
/*  521 */     Ad localAd1 = new Ad(Af.i, "uniqueId", getUniqueIdentifier());
/*  522 */     Ad localAd2 = new Ad(Af.d, "creatoreId", Integer.valueOf(getCreatorId()));
/*      */ 
/*  524 */     Ad localAd3 = new Ad(Af.k, "maxPos", getMinPos());
/*  525 */     Ad localAd4 = new Ad(Af.k, "minPos", getMaxPos());
/*  526 */     Ad localAd5 = new Ad(Af.i, "realname", getRealName());
/*      */ 
/*  528 */     Ad localAd6 = this.dockingController.a();
/*      */     Ad localAd7;
/*  531 */     if ((this instanceof ld))
/*      */     {
/*  533 */       localAd7 = ((ld)this).a().toTagStructure();
/*      */     }
/*  535 */     else localAd7 = new Ad(Af.b, "dummy", Byte.valueOf((byte)0));
/*      */ 
/*  538 */     Ad localAd8 = getControlElementMap().toTagStructure();
/*      */ 
/*  540 */     Ad localAd9 = new Ad(Af.i, null, this.spawner != null ? this.spawner : "");
/*  541 */     Ad localAd10 = new Ad(Af.i, null, this.lastModifier != null ? this.lastModifier : "");
/*      */ 
/*  543 */     Ad localAd11 = new Ad(Af.e, null, Long.valueOf(getSeed()));
/*      */     Ad localAd12;
/*  547 */     if ((this instanceof km))
/*  548 */       localAd12 = new Ad(Af.b, null, Byte.valueOf((byte)(((km)this).a() ? 1 : 0)));
/*      */     else {
/*  550 */       localAd12 = new Ad(Af.b, null, Byte.valueOf((byte)1));
/*      */     }
/*      */ 
/*  554 */     return new Ad(Af.n, "sc", new Ad[] { localAd1, localAd3, localAd4, localAd6, localAd8, localAd5, super.toTagStructure(), localAd7, localAd2, localAd9, localAd10, localAd11, localAd12, new Ad(Af.a, null, null) });
/*      */   }
/*      */ 
/*      */   public Vector3f getAbsoluteElementWorldPosition(q paramq, Vector3f paramVector3f)
/*      */   {
/*  563 */     paramVector3f.set(paramq.a, paramq.b, paramq.c);
/*  564 */     getWorldTransform().basis.transform(paramVector3f);
/*  565 */     paramVector3f.add(getWorldTransform().origin);
/*  566 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public void getAbsoluteSegmentWorldPositionClient(Segment paramSegment, Vector3f paramVector3f)
/*      */   {
/*  571 */     paramVector3f.set(paramSegment.a.a, paramSegment.a.b, paramSegment.a.c);
/*  572 */     (
/*  573 */       paramSegment = getWorldTransformClient()).basis
/*  573 */       .transform(paramVector3f);
/*  574 */     paramVector3f.add(paramSegment.origin);
/*      */   }
/*      */   public xO getBoundingBox() {
/*  577 */     return getSegmentBuffer().a();
/*      */   }
/*      */   public Vector3f getCamForwLocal() {
/*  580 */     return this.camForwLocal;
/*      */   }
/*      */   public Vector3f getCamLeftLocal() {
/*  583 */     return this.camLeftLocal;
/*      */   }
/*      */   public Vector3f getCamUpLocal() {
/*  586 */     return this.camUpLocal;
/*      */   }
/*      */ 
/*      */   public jR getCollisionChecker()
/*      */   {
/*  593 */     return this.collisionChecker;
/*      */   }
/*      */ 
/*      */   public ControlElementMap getControlElementMap()
/*      */   {
/*  601 */     return this.controlElementMap;
/*      */   }
/*      */   public int getCreatorId() {
/*  604 */     return this.creatorId;
/*      */   }
/*      */   public void setCreatorId(int paramInt) {
/*  607 */     this.creatorId = paramInt;
/*      */   }
/*      */ 
/*      */   public kG getCreatorThread()
/*      */   {
/*  614 */     return this.creatorThread;
/*      */   }
/*      */ 
/*      */   public jv getDockingController()
/*      */   {
/*  621 */     return this.dockingController;
/*      */   }
/*      */ 
/*      */   public jx getElementClassCountMap()
/*      */   {
/*  628 */     return this.elementClassCountMap;
/*      */   }
/*      */ 
/*      */   public final int getId()
/*      */   {
/*  653 */     return this.id;
/*      */   }
/*      */ 
/*      */   public Vector3f getLocalCamPos() {
/*  657 */     return this.camPosLocal;
/*      */   }
/*      */ 
/*      */   public float getMass()
/*      */   {
/*  663 */     return super.getMass();
/*      */   }
/*      */ 
/*      */   private float getMaxPhysicsSubsteps(xq paramxq)
/*      */   {
/*      */     Object localObject;
/*      */     Transform localTransform;
/*      */     float f1;
/*      */     Iterator localIterator;
/*  669 */     if ((vo.m.a()) && 
/*  670 */       (getMass() > 0.0F) && ((getPhysicsDataContainer().getObject() instanceof RigidBody)))
/*      */     {
/*      */       Vector3f localVector3f;
/*      */       float f2;
/*  674 */       if ((
/*  674 */         f2 = (
/*  673 */         localVector3f = ((RigidBody)getPhysicsDataContainer().getObject())
/*  672 */         .getLinearVelocity(new Vector3f()))
/*  673 */         .length()) > 
/*  674 */         47.0F) {
/*  675 */         System.err.println("Tuned on Tunneling prevention for " + this + " at speed " + f2 + " on " + getState());
/*  676 */         (
/*  677 */           localObject = new Vector3f())
/*  677 */           .sub(getSegmentBuffer().a().b, getSegmentBuffer().a().a);
/*  678 */         ((Vector3f)localObject).scale(0.5F);
/*      */ 
/*  680 */         localVector3f.scale(paramxq.a());
/*  681 */         paramxq = new Transform(getWorldTransform());
/*  682 */         localTransform = new Transform(getWorldTransform());
/*  683 */         paramxq.basis.transform(localVector3f);
/*  684 */         localTransform.origin.add(localVector3f);
/*      */ 
/*  688 */         f1 = getPhysics().getDynamicsWorld().getDispatchInfo().allowedCcdPenetration;
/*  689 */         localObject = new BoxShape((Vector3f)localObject);
/*  690 */         for (localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*      */         {
/*      */           Sendable localSendable;
/*  691 */           if (((
/*  691 */             localSendable = (Sendable)localIterator.next()) != 
/*  691 */             this) && ((localSendable instanceof SegmentController)) && (((SegmentController)localSendable).getSectorId() != getSectorId())) {
/*  692 */             CollisionWorld.ClosestConvexResultCallback localClosestConvexResultCallback = new CollisionWorld.ClosestConvexResultCallback(paramxq.origin, localTransform.origin);
/*  693 */             SegmentController localSegmentController = (SegmentController)localSendable;
/*      */ 
/*  695 */             ModifiedDynamicsWorld.objectQuerySingle((ConvexShape)localObject, paramxq, localTransform, localSegmentController.getPhysicsDataContainer().getObject(), localSegmentController.getPhysicsDataContainer().getObject().getCollisionShape(), localSegmentController.getWorldTransform(), localClosestConvexResultCallback, f1);
/*      */ 
/*  702 */             if (localClosestConvexResultCallback.hasHit())
/*      */             {
/*  704 */               (
/*  706 */                 paramxq = new Vector3f(getWorldTransform().origin))
/*  706 */                 .sub(localClosestConvexResultCallback.hitPointWorld);
/*  707 */               System.err.println("[TunnelPrevention] HIT AT " + localClosestConvexResultCallback.hitPointWorld + " " + localSendable + " on " + getState() + " DIST: " + paramxq.length());
/*  708 */               return paramxq.length();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  715 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public q getMaxPos()
/*      */   {
/*  720 */     return this.maxPos;
/*      */   }
/*      */ 
/*      */   public q getMinPos()
/*      */   {
/*  747 */     return this.minPos;
/*      */   }
/*      */ 
/*      */   public abstract void getNearestIntersectingElementPosition(Vector3f paramVector3f1, Vector3f paramVector3f2, q paramq, float paramFloat, ah paramah, az paramaz);
/*      */ 
/*      */   public abstract int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, ag paramag, int paramInt1, boolean paramBoolean, ju paramju, q paramq, int paramInt2, float paramFloat, az paramaz);
/*      */ 
/*      */   public le[] getNeighborElements(q paramq, short paramShort, le[] paramArrayOfle)
/*      */   {
/*  761 */     assert (paramArrayOfle.length == 6);
/*      */ 
/*  763 */     for (int i = 0; i < 6; i++) {
/*  764 */       this.posTmp.b(paramq);
/*  765 */       this.posTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/*      */       le localle;
/*  770 */       if (((
/*  770 */         localle = getSegmentBuffer().a(this.posTmp, true)) != null) && 
/*  770 */         ((paramShort == 32767) || (paramShort == localle.a())))
/*  771 */         paramArrayOfle[i] = localle;
/*      */       else {
/*  773 */         paramArrayOfle[i] = null;
/*      */       }
/*      */     }
/*  776 */     return paramArrayOfle;
/*      */   }
/*      */ 
/*      */   public le[] getNeighborElements(q paramq, short paramShort, le[] paramArrayOfle, Set paramSet)
/*      */   {
/*  783 */     assert (paramArrayOfle.length == 6);
/*  784 */     for (int i = 0; i < 6; i++) {
/*  785 */       this.posTmp.b(paramq);
/*  786 */       this.posTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/*  787 */       int j = 0;
/*  788 */       if (paramSet.contains(this.posTmp))
/*      */       {
/*      */         le localle;
/*  791 */         if (((
/*  791 */           localle = getSegmentBuffer().a(this.posTmp, true, paramArrayOfle[i])) != null) && 
/*  791 */           ((paramShort == 32767) || (paramShort == localle.a()))) {
/*  792 */           assert (localle.a() != null);
/*  793 */           j = 1;
/*      */         }
/*      */       }
/*  796 */       paramArrayOfle[i].a(j);
/*      */     }
/*  798 */     for (i = 0; i < 6; i++) {
/*  799 */       if ((paramArrayOfle[i].b() == 1) && 
/*  800 */         (!$assertionsDisabled) && (paramArrayOfle[i].a() == null)) throw new AssertionError();
/*      */     }
/*      */ 
/*  803 */     return paramArrayOfle;
/*      */   }
/*      */ 
/*      */   public Segment getNeighboringSegment(o paramo, Segment paramSegment, q paramq)
/*      */   {
/*  816 */     assert (paramSegment != null) : (this + ", " + getState() + " has null seg");
/*      */ 
/*  818 */     if (SegmentData.valid(paramo.a, paramo.b, paramo.c))
/*      */     {
/*  821 */       return paramSegment;
/*      */     }
/*      */ 
/*  824 */     paramq.b(paramSegment.a);
/*  825 */     paramSegment = ByteUtil.b(paramo.a);
/*  826 */     int i = ByteUtil.b(paramo.b);
/*  827 */     int j = ByteUtil.b(paramo.c);
/*      */ 
/*  829 */     paramq.a(paramSegment << 4, i << 4, j << 4);
/*      */ 
/*  831 */     paramo.a = ((byte)ByteUtil.d(paramo.a));
/*  832 */     paramo.b = ((byte)ByteUtil.d(paramo.b));
/*  833 */     paramo.c = ((byte)ByteUtil.d(paramo.c));
/*      */ 
/*  836 */     return this.segmentBuffer.a(paramq);
/*      */   }
/*      */ 
/*      */   public Segment getNeighboringSegmentFast(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
/*      */   {
/*  849 */     assert (paramSegment != null) : (this + ", " + getState() + " has null seg");
/*      */ 
/*  851 */     if (SegmentData.valid(paramByte1, paramByte2, paramByte3))
/*      */     {
/*  854 */       return paramSegment;
/*      */     }
/*      */ 
/*  860 */     paramByte1 = paramSegment.a.a + (ByteUtil.b(paramByte1) << 4);
/*  861 */     paramByte2 = paramSegment.a.b + (ByteUtil.b(paramByte2) << 4);
/*  862 */     paramSegment = paramSegment.a.c + (ByteUtil.b(paramByte3) << 4);
/*      */ 
/*  865 */     return this.segmentBuffer.a(paramByte1, paramByte2, paramSegment);
/*      */   }
/*      */ 
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/*  870 */     System.err.println(ByteUtil.b(16));
/*      */   }
/*      */ 
/*      */   public q getNeighboringSegmentPosUnsave(o paramo, Segment paramSegment, q paramq1, q paramq2) {
/*  874 */     int i = paramo.a >> 4;
/*  875 */     int j = paramo.b >> 4;
/*  876 */     int k = paramo.c >> 4;
/*      */ 
/*  878 */     paramq2.a = (paramSegment.b.a + i);
/*  879 */     paramq2.b = (paramSegment.b.b + j);
/*  880 */     paramq2.c = (paramSegment.b.c + k);
/*      */ 
/*  882 */     paramq1.a = (paramSegment.a.a + (i << 4));
/*  883 */     paramq1.b = (paramSegment.a.b + (j << 4));
/*  884 */     paramq1.c = (paramSegment.a.c + (k << 4));
/*      */ 
/*  886 */     paramo.a = ((byte)(paramo.a & 0xF));
/*  887 */     paramo.b = ((byte)(paramo.b & 0xF));
/*  888 */     paramo.c = ((byte)(paramo.c & 0xF));
/*      */ 
/*  890 */     return paramq1;
/*      */   }
/*      */ 
/*      */   private static void modVec(o paramo) {
/*  894 */     paramo.a = ((byte)ByteUtil.d(paramo.a));
/*  895 */     paramo.b = ((byte)ByteUtil.d(paramo.b));
/*  896 */     paramo.c = ((byte)ByteUtil.d(paramo.c));
/*      */   }
/*      */   public q getNeighborSegmentPos(q paramq1, int paramInt, q paramq2) {
/*  899 */     paramq2.b(paramq1);
/*  900 */     switch (paramInt) { case 1:
/*  901 */       paramq2.a -= 16; break;
/*      */     case 0:
/*  902 */       paramq2.a += 16; break;
/*      */     case 3:
/*  903 */       paramq2.b -= 16; break;
/*      */     case 2:
/*  904 */       paramq2.b += 16; break;
/*      */     case 5:
/*  905 */       paramq2.c -= 16; break;
/*      */     case 4:
/*  906 */       paramq2.c += 16; break;
/*      */     default:
/*  907 */       if (!$assertionsDisabled) throw new AssertionError(); break;
/*      */     }
/*  909 */     return paramq2;
/*      */   }
/*      */ 
/*      */   public ArrayList getRadius(Vector3f paramVector3f, float paramFloat)
/*      */   {
/*  924 */     paramVector3f = new Vector3f(paramVector3f);
/*  925 */     getWorldTransformInverse().transform(paramVector3f);
/*  926 */     paramVector3f.x += 8.0F;
/*  927 */     paramVector3f.y += 8.0F;
/*  928 */     paramVector3f.z += 8.0F;
/*  929 */     return getRadiusRelative(paramVector3f, paramFloat);
/*      */   }
/*      */ 
/*      */   public ArrayList getRadiusRelative(Vector3f paramVector3f, float paramFloat)
/*      */   {
/*  942 */     ArrayList localArrayList = new ArrayList();
/*  943 */     new Transform()
/*  944 */       .setIdentity();
/*  945 */     Vector3f localVector3f = new Vector3f(paramVector3f);
/*  946 */     paramVector3f = new Vector3f(paramVector3f);
/*  947 */     localVector3f.x += paramFloat;
/*  948 */     localVector3f.y += paramFloat;
/*  949 */     localVector3f.z += paramFloat;
/*      */ 
/*  951 */     paramVector3f.x -= paramFloat;
/*  952 */     paramVector3f.y -= paramFloat;
/*  953 */     paramVector3f.z -= paramFloat;
/*      */ 
/*  955 */     paramVector3f = new q(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/*  956 */     paramFloat = new q(FastMath.c(localVector3f.x), FastMath.c(localVector3f.y), FastMath.c(localVector3f.z));
/*      */ 
/*  958 */     paramVector3f.b(ByteUtil.b(paramVector3f.a), ByteUtil.b(paramVector3f.b), ByteUtil.b(paramVector3f.c));
/*  959 */     paramFloat.b(ByteUtil.b(paramFloat.a), ByteUtil.b(paramFloat.b), ByteUtil.b(paramFloat.c));
/*  960 */     paramFloat.a(1, 1, 1);
/*  961 */     paramVector3f.a(16);
/*  962 */     paramFloat.a(16);
/*      */ 
/*  964 */     getSegmentBuffer().a(new jP(localArrayList), paramVector3f, paramFloat);
/*      */ 
/*  977 */     return localArrayList;
/*      */   }
/*      */ 
/*      */   public String getRealName()
/*      */   {
/*  983 */     return this.realName;
/*      */   }
/*      */ 
/*      */   public long getSeed()
/*      */   {
/*  991 */     return this.seed;
/*      */   }
/*      */ 
/*      */   public void setSeed(long paramLong) {
/*  995 */     this.seed = paramLong;
/*      */   }
/*      */ 
/*      */   public jL getSegmentBuffer() {
/*  999 */     return this.segmentBuffer;
/*      */   }
/*      */ 
/*      */   public Segment getSegmentFromCache(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1006 */     return this.segmentBuffer.a(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */ 
/*      */   public jY getSegmentProvider()
/*      */   {
/* 1012 */     return this.segmentProvider;
/*      */   }
/*      */ 
/*      */   public int getTotalElements()
/*      */   {
/* 1019 */     return this.totalElements;
/*      */   }
/*      */ 
/*      */   public void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4)
/*      */   {
/* 1030 */     paramVector3f3.set(getSegmentBuffer().a().a);
/* 1031 */     paramVector3f4.set(getSegmentBuffer().a().b);
/* 1032 */     if (getSegmentBuffer().c() == 0) {
/* 1033 */       paramVector3f3.set(0.0F, 0.0F, 0.0F);
/* 1034 */       paramVector3f4.set(0.0F, 0.0F, 0.0F); return;
/*      */     }
/* 1036 */     if ((paramVector3f3.x > paramVector3f4.x) || (paramVector3f3.y > paramVector3f4.y) || (paramVector3f3.z > paramVector3f4.z)) {
/* 1037 */       paramVector3f3.set(0.0F, 0.0F, 0.0F);
/* 1038 */       paramVector3f4.set(0.0F, 0.0F, 0.0F);
/*      */     }
/* 1040 */     AabbUtil2.transformAabb(paramVector3f3, paramVector3f4, paramFloat, getWorldTransform(), paramVector3f1, paramVector3f2);
/*      */   }
/*      */ 
/*      */   public String getUniqueIdentifier()
/*      */   {
/* 1045 */     return this.uniqueIdentifier;
/*      */   }
/*      */ 
/*      */   public Transform getWorldTransformInverse()
/*      */   {
/* 1053 */     return this.worldTransformInverse;
/*      */   }
/*      */ 
/*      */   public boolean hasNeighborElements(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3, q paramq)
/*      */   {
/*      */     byte b1;
/*      */     byte b2;
/*      */     byte b3;
/* 1078 */     if (SegmentData.allNeighborsInside(paramByte1, paramByte2, paramByte3)) {
/* 1079 */       for (paramq = 0; paramq < 6; paramq++) {
/* 1080 */         b1 = (byte)ByteUtil.d(paramByte1 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].a);
/* 1081 */         b2 = (byte)ByteUtil.d(paramByte2 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].b);
/* 1082 */         b3 = (byte)ByteUtil.d(paramByte3 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].c);
/* 1083 */         if (paramSegment.a().containsUnsave(b1, b2, b3))
/* 1084 */           return true;
/*      */       }
/*      */     }
/*      */     else {
/* 1088 */       for (paramq = 0; paramq < 6; paramq++) {
/* 1089 */         b1 = (byte)(paramByte1 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].a);
/* 1090 */         b2 = (byte)(paramByte2 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].b);
/* 1091 */         b3 = (byte)(paramByte3 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].c);
/*      */         Segment localSegment;
/* 1093 */         if (((
/* 1093 */           localSegment = getNeighboringSegmentFast(paramSegment, b1, b2, b3)) != null) && 
/* 1093 */           (!localSegment.g()))
/*      */         {
/* 1095 */           b1 = (byte)ByteUtil.d(b1);
/* 1096 */           b2 = (byte)ByteUtil.d(b2);
/* 1097 */           b3 = (byte)ByteUtil.d(b3);
/* 1098 */           if (localSegment.a().containsUnsave(b1, b2, b3)) {
/* 1099 */             return true;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1108 */     return false;
/*      */   }
/*      */ 
/*      */   public void incTotalElements() {
/* 1112 */     this.totalElements += 1;
/* 1113 */     flagupdateMass();
/* 1114 */     flagUpdateDocking();
/*      */   }
/*      */ 
/*      */   public void initPhysics()
/*      */   {
/* 1119 */     if (getPhysicsDataContainer().getObject() == null) {
/* 1120 */       Object localObject = getRemoteTransformable().a();
/*      */ 
/* 1123 */       CubeShape localCubeShape = new CubeShape(getSegmentBuffer());
/*      */ 
/* 1125 */       CubesCompoundShape localCubesCompoundShape = new CubesCompoundShape(this);
/*      */       Transform localTransform;
/* 1126 */       (
/* 1127 */         localTransform = new Transform())
/* 1127 */         .setIdentity();
/* 1128 */       localCubesCompoundShape.addChildShape(localTransform, localCubeShape);
/* 1129 */       getPhysicsDataContainer().setShapeChield((CompoundShapeChild)localCubesCompoundShape.getChildList().get(localCubesCompoundShape.getChildList().size() - 1));
/*      */ 
/* 1131 */       localCubesCompoundShape.recalculateLocalAabb();
/*      */ 
/* 1133 */       getPhysicsDataContainer().setShape(localCubesCompoundShape);
/* 1134 */       getPhysicsDataContainer().initialTransform = new Transform();
/* 1135 */       getPhysicsDataContainer().initialTransform.set((Transform)localObject);
/*      */ 
/* 1137 */       (
/* 1139 */         localObject = getPhysics().getBodyFromShape(localCubesCompoundShape, getMass(), getPhysicsDataContainer().initialTransform))
/* 1139 */         .setUserPointer(Integer.valueOf(getId()));
/*      */ 
/* 1141 */       getPhysicsDataContainer().setObject((CollisionObject)localObject);
/*      */     }
/*      */     else {
/* 1144 */       System.err.println("[SegmentController][WARNING] not adding initial physics object. it already exists");
/*      */     }
/* 1146 */     setFlagPhysicsInit(true);
/*      */   }
/*      */ 
/*      */   public boolean isClientOwnObject()
/*      */   {
/* 1153 */     return (!isOnServer()) && (((ct)getState()).a() == this);
/*      */   }
/*      */ 
/*      */   public boolean isInbound(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1164 */     if ((paramInt1 <= this.maxPos.a) && (paramInt2 <= this.maxPos.b) && (paramInt3 <= this.maxPos.c) && (paramInt1 >= this.minPos.a) && (paramInt2 >= this.minPos.b) && (paramInt3 >= this.minPos.c)) return true;
/*      */ 
/* 1168 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean isInboundCoord(int paramInt, q paramq)
/*      */   {
/* 1175 */     if ((paramq.a(paramInt) <= this.maxPos.a(paramInt)) && (paramq.a(paramInt) >= this.minPos.a(paramInt))) return true;
/*      */ 
/* 1177 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean isInbound(q paramq)
/*      */   {
/* 1184 */     return isInbound(paramq.a, paramq.b, paramq.c);
/*      */   }
/*      */ 
/*      */   public boolean isInboundAbs(q paramq)
/*      */   {
/* 1197 */     return isInbound(paramq.a, paramq.b, paramq.c);
/*      */   }
/*      */   public boolean isInboundAbs(int paramInt1, int paramInt2, int paramInt3) {
/* 1200 */     boolean bool = true;
/*      */ 
/* 1202 */     paramInt1 = ByteUtil.a(paramInt1);
/* 1203 */     paramInt2 = ByteUtil.a(paramInt2);
/* 1204 */     paramInt3 = ByteUtil.a(paramInt3);
/* 1205 */     if (getMaxPos() != null) {
/* 1206 */       bool = (paramInt1 <= getMaxPos().a) && (paramInt2 <= getMaxPos().b) && (paramInt3 <= getMaxPos().c);
/*      */     }
/*      */ 
/* 1211 */     if ((getMinPos() != null) && (bool)) {
/* 1212 */       bool = (paramInt1 >= getMinPos().a) && (paramInt2 >= getMinPos().b) && (paramInt3 >= getMinPos().c);
/*      */     }
/*      */ 
/* 1216 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean isInboundSegmentPos(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1221 */     boolean bool = true;
/* 1222 */     if (getMaxPos() != null) {
/* 1223 */       bool = (paramInt1 <= getMaxPos().a << 4) && (paramInt2 <= getMaxPos().b << 4) && (paramInt3 <= getMaxPos().c << 4);
/*      */     }
/*      */ 
/* 1227 */     if (getMinPos() != null) {
/* 1228 */       bool = (bool) && (paramInt1 >= getMinPos().a << 4) && (paramInt2 >= getMinPos().b << 4) && (paramInt3 >= getMinPos().c << 4);
/*      */     }
/*      */ 
/* 1233 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean isInboundSegmentPos(q paramq)
/*      */   {
/* 1239 */     return isInboundSegmentPos(paramq.a, paramq.b, paramq.c);
/*      */   }
/*      */ 
/*      */   public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean) {
/* 1243 */     if (paramBoolean) {
/* 1244 */       this.segmentBuffer.a(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*      */     }
/*      */ 
/* 1247 */     getElementClassCountMap().b(paramShort);
/* 1248 */     incTotalElements();
/*      */   }
/*      */ 
/*      */   public void onProximity(SegmentController paramSegmentController)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*      */   {
/* 1263 */     this.segmentBuffer.b(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*      */ 
/* 1266 */     getElementClassCountMap().a(paramShort);
/* 1267 */     decTotalElements();
/*      */ 
/* 1270 */     if (!paramBoolean)
/*      */     {
/* 1272 */       paramInt = new q();
/* 1273 */       paramSegment.a(paramByte1, paramByte2, paramByte3, paramInt);
/*      */ 
/* 1275 */       getControlElementMap().onRemoveElement(paramInt, paramShort);
/*      */ 
/* 1277 */       if ((isOnServer()) && (paramShort == 291)) {
/* 1278 */         System.err.println("[SREVER] FACTION BLOCK REMOVED FROM " + this + "; resetting faction !!!!!!!!!!!!!!");
/* 1279 */         setFactionId(0);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removeAllConstraints(zL paramzL)
/*      */   {
/* 1296 */     RigidBody localRigidBody = (RigidBody)paramzL.getPhysicsDataContainer().getObject();
/*      */ 
/* 1298 */     PhysicsExt localPhysicsExt = getPhysics();
/* 1299 */     for (int i = 0; i < localPhysicsExt.getDynamicsWorld().getNumConstraints(); i++)
/*      */     {
/*      */       TypedConstraint localTypedConstraint;
/* 1301 */       if (((
/* 1301 */         localTypedConstraint = localPhysicsExt.getDynamicsWorld().getConstraint(i))
/* 1301 */         .getRigidBodyA() == localRigidBody) || (localTypedConstraint.getRigidBodyB() == localRigidBody)) {
/* 1302 */         localPhysicsExt.getDynamicsWorld().removeConstraint(localTypedConstraint);
/* 1303 */         System.err.println("[SEGMENT-CONTROLLER][PHYSICS] constraint removed on " + this + " " + getState());
/*      */ 
/* 1306 */         getPhysics().removeTimedExceptions(this, paramzL);
/* 1307 */         getPhysics().addTimedPhysicsException(localTypedConstraint.getRigidBodyA(), localTypedConstraint.getRigidBodyB(), 4000);
/* 1308 */         getPhysics().addTimedPhysicsException(getPhysicsDataContainer().getObject(), paramzL.getPhysicsDataContainer().getObject(), 4000);
/*      */ 
/* 1310 */         localRigidBody.setDamping(0.1F, 0.1F);
/*      */ 
/* 1312 */         removeAllConstraints(paramzL);
/* 1313 */         return;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void resetTotalElements()
/*      */   {
/* 1322 */     this.totalElements = 0;
/* 1323 */     flagupdateMass();
/* 1324 */     flagUpdateDocking();
/*      */   }
/*      */ 
/*      */   public void setCreatorThread(kG paramkG)
/*      */   {
/* 1333 */     this.creatorThread = paramkG;
/*      */   }
/*      */   public void setCurrentBlockController(le paramle, q paramq) {
/* 1336 */     setCurrentBlockController(paramle, paramq, 0);
/*      */   }
/*      */   public void setCurrentBlockController(le paramle, q paramq, int paramInt) {
/* 1339 */     if (paramq != null)
/*      */       try
/*      */       {
/*      */         le localle;
/* 1344 */         if (((
/* 1344 */           localle = getSegmentBuffer().a(paramq, true)) != null) && 
/* 1344 */           (paramle != null)) {
/* 1345 */           paramle.a();
/* 1346 */           short s2 = paramle.a().a().getType(paramle.a(this.tmpLocalPos));
/* 1347 */           short s1 = localle.a().a().getType(localle.a(this.tmpLocalPos));
/*      */ 
/* 1350 */           ElementInformation localElementInformation1 = ElementKeyMap.getInfo(s2);
/*      */           ElementInformation localElementInformation2;
/* 1352 */           if ((
/* 1352 */             localElementInformation2 = ElementKeyMap.getInfo(s1)) == null)
/*      */           {
/* 1353 */             System.err.println("WARNING. cannot connect block controller to null");
/* 1354 */             return;
/*      */           }
/* 1356 */           if (((this instanceof ld)) && 
/* 1357 */             (!((ld)this).a().canBeControlled(s1))) {
/* 1358 */             System.err.println("This cant be controlled by " + this + ": " + s1);
/* 1359 */             throw new CannotBeControlledException(localElementInformation1, localElementInformation2);
/*      */           }
/*      */           ControlElementMap localControlElementMap;
/* 1363 */           if (localElementInformation2.getControlledBy().contains(Short.valueOf(s2))) {
/* 1364 */             localControlElementMap = paramle.a().a().getControlElementMap();
/* 1365 */             if (paramInt == 0) {
/* 1366 */               localControlElementMap.switchControllerForElement(paramle.a(new q()), paramq, s1); return;
/* 1367 */             }if (paramInt == 1)
/*      */             {
/* 1369 */               if (!localControlElementMap.isControlling(paramle.a(new q()), paramq, s1)) {
/* 1370 */                 localControlElementMap.switchControllerForElement(paramle.a(new q()), paramq, s1);
/*      */               }
/*      */ 
/*      */             }
/* 1375 */             else if (localControlElementMap.isControlling(paramle.a(new q()), paramq, s1)) {
/* 1376 */               localControlElementMap.switchControllerForElement(paramle.a(new q()), paramq, s1);
/*      */             }
/*      */ 
/* 1380 */             return;
/* 1381 */           }System.err.println("OBJECT " + s1 + " CANNOT BE CONTROLLED BY " + localControlElementMap);
/* 1382 */           if ((!isOnServer()) && 
/* 1383 */             (localElementInformation2.getControlledBy().size() > 0))
/* 1384 */             ((ct)getState()).a().b(ElementKeyMap.getInfo(s1).getName() + " cannot be\nconnected to " + ElementKeyMap.getInfo(localControlElementMap).getName() + ".\nReason: Incompatible blocks");
/*      */         }
/*      */         return;
/*      */       }
/*      */       catch (ElementClassNotFoundException localElementClassNotFoundException)
/*      */       {
/* 1390 */         System.err.println("WARING TRIED TO CONNECT TO AIR");
/* 1391 */         return;
/*      */       }
/* 1393 */     System.err.println("ADDING CONTROL WARNING: CONTROLLED IS NULL " + paramle + ": " + paramq);
/*      */   }
/*      */ 
/*      */   public void setId(int paramInt)
/*      */   {
/* 1404 */     this.id = paramInt;
/*      */   }
/*      */ 
/*      */   public void setRealName(String paramString)
/*      */   {
/* 1412 */     this.realName = paramString;
/*      */   }
/*      */ 
/*      */   public void setSegmentProvider(jY paramjY)
/*      */   {
/* 1418 */     this.segmentProvider = paramjY;
/*      */   }
/*      */ 
/*      */   public void setUniqueIdentifier(String paramString)
/*      */   {
/* 1424 */     this.uniqueIdentifier = paramString;
/*      */   }
/*      */ 
/*      */   public abstract void startCreatorThread();
/*      */ 
/*      */   public void updateLocal(xq paramxq)
/*      */   {
/* 1449 */     super.updateLocal(paramxq);
/*      */ 
/* 1454 */     long l = System.currentTimeMillis();
/*      */     Object localObject1;
/* 1460 */     if (xu.F.b()) {
/* 1461 */       xO localxO = getSegmentBuffer().a();
/*      */ 
/* 1463 */       if (isOnServer()) {
/* 1464 */         if (((vg)getState()).a().getSector(getSectorId()).a.a(2, 2, 2)) {
/* 1465 */           localObject1 = new xR(localxO.a, localxO.b, getWorldTransform(), 0.0F, 0.0F);
/* 1466 */           yk.a.add(localObject1);
/*      */         }
/*      */       } else {
/* 1469 */         localObject1 = new xR(localxO.a, localxO.b, getWorldTransformClient(), 1.0F, 1.0F);
/* 1470 */         yk.a.add(localObject1);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1476 */     if (l - getLastSlowdown() < 5000L) {
/* 1477 */       if ((getSlowdownStart() > 0L) && (l - getSlowdownStart() > 10000L))
/*      */       {
/* 1479 */         if (isOnServer()) {
/* 1480 */           ((vg)getState()).a().broadcastMessage("Structure\n" + this + "\nis slowing down the server\nWARPING OUT OF COLLISION!", 3);
/*      */         }
/* 1482 */         warpOutOfCollision();
/* 1483 */         getPhysicsDataContainer().updatePhysical();
/*      */       } } else resetSlowdownStart();
/*      */ 
/* 1490 */     getDockingController().a(paramxq);
/* 1491 */     assert (getUniqueIdentifier() != null);
/*      */ 
/* 1493 */     getWorldTransformInverse().set(getWorldTransform());
/*      */ 
/* 1495 */     getWorldTransformInverse().inverse();
/*      */ 
/* 1497 */     if ((this.flagCheckDocking) && (getPhysicsDataContainer().isInitialized()) && (System.currentTimeMillis() - this.delayDockingCheck > 1000L)) {
/*      */       try
/*      */       {
/* 1500 */         getDockingController().a();
/* 1501 */         this.flagCheckDocking = false;
/* 1502 */         this.delayDockingCheck = System.currentTimeMillis();
/*      */       } catch (CollectionNotLoadedException localCollectionNotLoadedException) {
/* 1504 */         System.err.println("[SEGMENT-CONTROLLER] Cannot validate docking yet, because enhancers aren't fully loaded -> DELAY by 1 sec");
/* 1505 */         this.delayDockingCheck = System.currentTimeMillis();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1511 */     if ((!isOnServer()) && (!getNeedsActiveUpdateClient().isEmpty())) {
/* 1512 */       synchronized (getNeedsActiveUpdateClient()) {
/* 1513 */         while (!getNeedsActiveUpdateClient().isEmpty()) {
/* 1514 */           localObject1 = (le)getNeedsActiveUpdateClient().remove(0);
/* 1515 */           ((ld)this).a().handleBlockActivate((le)localObject1, ((le)localObject1).a());
/*      */         }
/*      */       }
/*      */     }
/* 1519 */     if ((this.aabbRecalcFlag) && (getPhysicsDataContainer().isInitialized())) {
/* 1520 */       flagupdateMass();
/* 1521 */       ((CompoundShape)getPhysicsDataContainer().getShape()).recalculateLocalAabb();
/* 1522 */       this.aabbRecalcFlag = false;
/*      */     }
/*      */ 
/* 1525 */     if ((isFlagSegmentBufferAABBUpdate()) && (getPhysicsDataContainer().isInitialized())) {
/* 1526 */       if (getPhysicsDataContainer().getObject() != null) {
/* 1527 */         getPhysicsDataContainer().getObject().activate(true);
/*      */       }
/* 1529 */       aabbRecalcFlag();
/* 1530 */       setFlagSegmentBufferAABBUpdate(false);
/*      */     }
/* 1532 */     if ((!isOnServer()) && (xe.a() != null))
/*      */     {
/* 1543 */       this.camLocalTmp.set(xe.a().a());
/*      */ 
/* 1546 */       getClientTransformInverse().set(getWorldTransformClient());
/*      */ 
/* 1548 */       getClientTransformInverse().inverse();
/*      */ 
/* 1552 */       getClientTransformInverse().transform(this.camLocalTmp);
/* 1553 */       this.camPosLocal.set(this.camLocalTmp);
/*      */ 
/* 1555 */       if (((xe.a() instanceof dw)) && (((dw)xe.a()).a() == this))
/*      */       {
/* 1557 */         getCamForwLocal().set(xe.a().i());
/* 1558 */         getCamLeftLocal().set(xe.a().h());
/* 1559 */         getCamLeftLocal().negate();
/* 1560 */         getCamUpLocal().set(xe.a().g());
/*      */ 
/* 1562 */         getWorldTransformInverse().basis.transform(getCamForwLocal());
/* 1563 */         getWorldTransformInverse().basis.transform(getCamLeftLocal());
/* 1564 */         getWorldTransformInverse().basis.transform(getCamUpLocal());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1577 */       this.segmentProvider.d(); } catch (InterruptedException localInterruptedException) {
/* 1578 */       ( = 
/* 1583 */         localInterruptedException).printStackTrace();
/* 1580 */       throw new ErrorDialogException(((InterruptedException)???).getMessage()); } catch (IOException localIOException) { localIOException
/* 1581 */         .printStackTrace();
/*      */     }
/*      */ 
/* 1587 */     getPhysicsState().d();
/* 1588 */     getSegmentBuffer();
/*      */ 
/* 1590 */     this.lastSector = getSectorId();
/*      */   }
/*      */ 
/*      */   private void warpOutOfCollision() {
/* 1594 */     if ((isOnServer()) && (getMass() > 0.0F)) {
/* 1595 */       Vector3f localVector3f1 = new Vector3f();
/* 1596 */       Vector3f localVector3f2 = new Vector3f();
/*      */ 
/* 1599 */       Transform localTransform = new Transform(getWorldTransform());
/*      */ 
/* 1601 */       int i = 0;
/*      */       do {
/* 1603 */         getTransformedAABB(localVector3f1, localVector3f2, 1.0F, new Vector3f(), new Vector3f());
/*      */ 
/* 1605 */         i = 0;
/* 1606 */         for (localObject1 = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); ((Iterator)localObject1).hasNext(); )
/*      */         {
/*      */           Object localObject2;
/* 1607 */           if (((
/* 1607 */             localObject2 = (Sendable)((Iterator)localObject1).next()) instanceof mF))
/*      */           {
/* 1610 */             if (((
/* 1610 */               localObject2 = (mF)localObject2) != 
/* 1610 */               this) && (((mF)localObject2).getSectorId() == getSectorId()) && (!((mF)localObject2).isHidden()))
/*      */             {
/* 1612 */               Vector3f localVector3f3 = new Vector3f();
/* 1613 */               Vector3f localVector3f4 = new Vector3f();
/* 1614 */               ((mF)localObject2).getTransformedAABB(localVector3f3, localVector3f4, 1.0F, new Vector3f(), new Vector3f());
/* 1615 */               if (AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4)) {
/* 1616 */                 i = 1;
/* 1617 */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1622 */         if (i != 0)
/* 1623 */           getWorldTransform().origin.y += 20.0F;
/*      */       }
/* 1625 */       while (i != 0);
/* 1626 */       System.err.println("[SEREVR][SEGMENTCONTROLLER] WARNING: COLLISION RECOVER: " + this + " warped from " + localTransform.origin + " to " + getWorldTransform().origin);
/* 1627 */       Object localObject1 = new Transform(getWorldTransform());
/* 1628 */       getWorldTransform().set(localTransform);
/* 1629 */       warpTransformable((Transform)localObject1, true);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean updateMass()
/*      */   {
/* 1636 */     return true;
/*      */   }
/*      */ 
/*      */   public abstract void writeAllBufferedSegmentsToDatabase();
/*      */ 
/*      */   public long getTimeCreated()
/*      */   {
/* 1643 */     return this.timeCreated;
/*      */   }
/*      */ 
/*      */   public void setTimeCreated(long paramLong)
/*      */   {
/* 1649 */     this.timeCreated = paramLong;
/*      */   }
/*      */ 
/*      */   public boolean isFlagSegmentBufferAABBUpdate()
/*      */   {
/* 1655 */     return this.flagSegmentBufferAABBUpdate;
/*      */   }
/*      */ 
/*      */   public void setFlagSegmentBufferAABBUpdate(boolean paramBoolean)
/*      */   {
/* 1661 */     this.flagSegmentBufferAABBUpdate = paramBoolean;
/*      */   }
/*      */ 
/*      */   public String getLastModifier()
/*      */   {
/* 1667 */     return this.lastModifier;
/*      */   }
/*      */ 
/*      */   public void setLastModifier(String paramString)
/*      */   {
/* 1673 */     this.lastModifier = paramString;
/*      */   }
/*      */ 
/*      */   public String getSpawner()
/*      */   {
/* 1679 */     return this.spawner;
/*      */   }
/*      */ 
/*      */   public ArrayList getNeedsActiveUpdateClient()
/*      */   {
/* 1685 */     return this.needsActiveUpdateClient;
/*      */   }
/*      */ 
/*      */   public Transform getClientTransformInverse()
/*      */   {
/* 1691 */     return this.clientTransformInverse;
/*      */   }
/*      */ 
/*      */   public void destroyPersistent()
/*      */   {
/* 1698 */     assert (isOnServer());
/* 1699 */     vg localvg = (vg)getState();
/* 1700 */     new StringBuilder().append(vg.a).append(getUniqueIdentifier()).toString();
/*      */     try {
/* 1702 */       Object localObject1 = new File(vg.a + getUniqueIdentifier() + ".ent");
/* 1703 */       System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY: " + ((File)localObject1).getName());
/* 1704 */       ((File)localObject1).delete();
/* 1705 */       localObject1 = new jQ(this);
/*      */ 
/* 1713 */       for (Object localObject2 : new File(vg.jdField_f_of_type_JavaLangString)
/* 1712 */         .listFiles((FilenameFilter)localObject1))
/*      */       {
/* 1714 */         System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY DATA: " + localObject2.getName());
/* 1715 */         localObject2.delete();
/* 1717 */       }localvg.a().b(this);
/*      */       return;
/*      */     } catch (SQLException localSQLException) {
/* 1720 */       localSQLException.printStackTrace();
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.SegmentController
 * JD-Core Version:    0.6.2
 */