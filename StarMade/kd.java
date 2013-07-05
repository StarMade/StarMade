/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*      */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import javax.vecmath.Tuple3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import javax.vecmath.Vector4f;
/*      */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.controller.elements.ParticleHandler;
/*      */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*      */ import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
/*      */ import org.schema.game.common.controller.elements.explosive.ExplosiveElementManager;
/*      */ import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*      */ import org.schema.game.common.data.element.BeamHandler;
/*      */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*      */ import org.schema.game.common.data.element.ElementDocking;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.element.factory.FactoryElement;
/*      */ import org.schema.game.common.data.element.factory.ManufacturingElement;
/*      */ import org.schema.game.common.data.element.ship.ShipElement;
/*      */ import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*      */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.network.objects.NetworkSegmentController;
/*      */ import org.schema.game.network.objects.NetworkShip;
/*      */ import org.schema.game.network.objects.remote.RemoteSegmentPiece;
/*      */ import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*      */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.objects.NetworkObject;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*      */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*      */ import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*      */ import org.schema.schine.network.objects.remote.RemoteString;
/*      */ 
/*      */ public class kd extends EditableSendableSegmentController
/*      */   implements Aj, cw, jI, kh, ld, ParticleHandler, wp, zL
/*      */ {
/*      */   public static final q a;
/*      */   private final ArrayList jdField_a_of_type_JavaUtilArrayList;
/*   81 */   private String jdField_a_of_type_JavaLangString = "";
/*      */ 
/*   83 */   private Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*      */   private final kp jdField_a_of_type_Kp;
/*      */   private final ShipManagerContainer jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer;
/*   89 */   private long jdField_a_of_type_Long = -1L;
/*   90 */   private long jdField_b_of_type_Long = -1L;
/*      */ 
/*   95 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*   96 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*      */ 
/*  101 */   private q jdField_b_of_type_Q = new q();
/*  102 */   private le jdField_a_of_type_Le = new le();
/*      */ 
/*  105 */   private o jdField_a_of_type_O = new o();
/*      */   private int jdField_a_of_type_Int;
/*      */   private long c;
/*      */ 
/*      */   public kd(StateInterface paramStateInterface)
/*      */   {
/*  129 */     super(paramStateInterface);
/*      */ 
/*  131 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  132 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer = new ShipManagerContainer(this);
/*  133 */     this.jdField_a_of_type_Kp = new kt(paramStateInterface, this);
/*      */   }
/*      */ 
/*      */   public boolean allowedType(short paramShort)
/*      */   {
/*  146 */     if ((!SpaceStationElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0);
/*  147 */     if ((!FactoryElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0);
/*  149 */     if ((!ManufacturingElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) == 0);
/*      */     int i;
/*  151 */     if (((
/*  151 */       i = ShipElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) == 0) && 
/*  151 */       (!isOnServer())) {
/*  152 */       ((x)getState().getController()).b("Cannot place\n" + ElementKeyMap.getInfo(paramShort).getName() + "\non a ship");
/*      */     }
/*      */ 
/*  157 */     return (super.allowedType(paramShort)) && (i != 0);
/*      */   }
/*      */ 
/*      */   public void cleanUpOnEntityDelete()
/*      */   {
/*  165 */     super.cleanUpOnEntityDelete();
/*  166 */     Iterator localIterator = null; if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty())
/*  167 */       for (localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); ) ((lE)localIterator.next())
/*  168 */           .a().b();
/*      */   }
/*      */ 
/*      */   public boolean isClientOwnObject()
/*      */   {
/*  178 */     return (!isOnServer()) && (this.jdField_a_of_type_JavaUtilArrayList.contains(((ct)getState()).a()));
/*      */   }
/*      */ 
/*      */   public final kp a()
/*      */   {
/*  189 */     return this.jdField_a_of_type_Kp;
/*      */   }
/*      */ 
/*      */   public final ArrayList a()
/*      */   {
/*  203 */     return this.jdField_a_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final long a()
/*      */   {
/*  210 */     return this.jdField_b_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final long b()
/*      */   {
/*  220 */     return this.jdField_a_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final ShipManagerContainer a() {
/*  224 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer;
/*      */   }
/*      */ 
/*      */   public final float a()
/*      */   {
/*  230 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getThrusterElementManager().getMaxVelocity();
/*      */   }
/*      */ 
/*      */   public int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, ag paramag, int paramInt1, boolean paramBoolean, ju paramju, q paramq, int paramInt2, float paramFloat, az paramaz)
/*      */   {
/*  235 */     if ((paramShort == 121) && (this.jdField_a_of_type_Kp.a() != null)) {
/*  236 */       if (!isOnServer()) {
/*  237 */         ((ct)getState()).a().b("ERROR\nOnly one AI block is permitted\nper ship");
/*      */       }
/*      */ 
/*  242 */       return 0;
/*      */     }
/*      */ 
/*  245 */     if (paramShort == 1) {
/*  246 */       if (!isOnServer()) {
/*  247 */         ((ct)getState()).a().b("ERROR\nCore Elements cannot be placed\nthey are used to spawn new ships");
/*      */       }
/*      */ 
/*  252 */       return 0;
/*      */     }
/*      */ 
/*  255 */     return super.getNearestIntersection(paramShort, paramVector3f1, paramVector3f2, paramag, paramInt1, paramBoolean, paramju, paramq, paramInt2, paramFloat, paramaz);
/*      */   }
/*      */ 
/*      */   public final NetworkShip a()
/*      */   {
/*  271 */     return (NetworkShip)super.getNetworkObject();
/*      */   }
/*      */ 
/*      */   public L getParticleController()
/*      */   {
/*  276 */     if (!isOnServer()) {
/*  277 */       return ((ct)getState()).getParticleController();
/*      */     }
/*  279 */     return ((vg)getState()).a().getSector(getSectorId()).a();
/*      */   }
/*      */ 
/*      */   public final SegmentController a()
/*      */   {
/*  285 */     return this;
/*      */   }
/*      */ 
/*      */   public final Set a()
/*      */   {
/*  295 */     return this.jdField_a_of_type_JavaUtilSet;
/*      */   }
/*      */ 
/*      */   public final Vector3f a()
/*      */   {
/*  305 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getThrusterElementManager().getVelocity();
/*      */   }
/*      */ 
/*      */   public final void a(xq paramxq, lA paramlA)
/*      */   {
/*  313 */     paramlA.jdField_a_of_type_LE.a().addAll(this.jdField_a_of_type_JavaUtilSet);
/*      */ 
/*  315 */     if ((paramlA.jdField_a_of_type_JavaLangObject instanceof q))
/*      */     {
/*  318 */       if (getPhysicsDataContainer().isInitialized())
/*      */       {
/*  320 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.handle(paramlA);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*      */   {
/*  330 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onHitNotice();
/*      */ 
/*  332 */     super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*      */ 
/*  334 */     for (paramClosestRayResultCallback = 0; paramClosestRayResultCallback < this.jdField_a_of_type_JavaUtilArrayList.size(); paramClosestRayResultCallback++)
/*  335 */       ((lE)this.jdField_a_of_type_JavaUtilArrayList.get(paramClosestRayResultCallback)).a(this);
/*      */   }
/*      */ 
/*      */   protected void onCoreHitAlreadyDestroyed(float paramFloat)
/*      */   {
/*  348 */     super.onCoreHitAlreadyDestroyed(paramFloat);
/*  349 */     if ((isOnServer()) && ((this instanceof kd)) && (this.jdField_b_of_type_Long > 0L))
/*  350 */       this.jdField_b_of_type_Long = (()Math.max(60000.0F, (float)this.jdField_b_of_type_Long - paramFloat * 10.0F));
/*      */   }
/*      */ 
/*      */   public final int a(BeamHandler.BeamState paramBeamState, jq paramjq, CubeRayCastResult paramCubeRayCastResult, Collection paramCollection)
/*      */   {
/*  358 */     le localle = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
/*      */ 
/*  366 */     if (((
/*  366 */       paramBeamState = paramjq.getHandler().beamHit(paramBeamState, localle)) > 0) && 
/*  366 */       (isOnServer())) {
/*  367 */       paramjq = localle.a();
/*      */ 
/*  369 */       if (localle.a().a(localle.a(this.jdField_a_of_type_O), false))
/*      */       {
/*  370 */         paramCollection.add(paramCubeRayCastResult.getSegment());
/*  371 */         ((mw)localle.a()).a(System.currentTimeMillis());
/*  372 */         localle.a();
/*  373 */         if ((!jdField_a_of_type_Boolean) && (localle.a() != 0)) throw new AssertionError();
/*  374 */         localle.a(getState().getId());
/*      */ 
/*  376 */         ((NetworkSegmentController)localle.a().a().getNetworkObject()).modificationBuffer.add(new RemoteSegmentPiece(localle, this, (NetworkShip)super.getNetworkObject()));
/*      */ 
/*  379 */         if (this.jdField_a_of_type_JavaUtilArrayList.size() > 0) {
/*  380 */           ((lE)this.jdField_a_of_type_JavaUtilArrayList.get(0))
/*  382 */             .a().a(paramjq);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  389 */     return paramBeamState;
/*      */   }
/*      */ 
/*      */   public final boolean a()
/*      */   {
/*  399 */     return ((Boolean)((NetworkShip)super.getNetworkObject()).cloaked.get()).booleanValue();
/*      */   }
/*      */ 
/*      */   public final boolean b()
/*      */   {
/*  404 */     return this.jdField_a_of_type_Long > 0L;
/*      */   }
/*      */   public final boolean c() {
/*  407 */     return ((Boolean)((NetworkShip)super.getNetworkObject()).jamming.get()).booleanValue();
/*      */   }
/*      */ 
/*      */   public final boolean a(String[] paramArrayOfString, q paramq)
/*      */   {
/*  415 */     if (jdField_a_of_type_Q.equals(paramq)) {
/*  416 */       paramArrayOfString[0] = "Can't salvage core! Please Pick up manually.";
/*  417 */       return false;
/*      */     }
/*  419 */     if ((isHomeBase()) || ((getDockingController().b()) && (getDockingController().a().to.a().a().isHomeBaseFor(getFactionId()))))
/*      */     {
/*  421 */       paramArrayOfString[0] = "Cannot salvage: home base protected";
/*  422 */       return false;
/*      */     }
/*  424 */     if (this.jdField_a_of_type_Kp.a()) {
/*  425 */       paramArrayOfString[0] = "Can only salvage defeated AI ships!";
/*  426 */       return false;
/*      */     }
/*  428 */     if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*  429 */       return true;
/*      */     }
/*  431 */     paramArrayOfString[0] = "Can only salvage empty ships!";
/*  432 */     return false;
/*      */   }
/*      */ 
/*      */   public void newNetworkObject()
/*      */   {
/*  439 */     setNetworkObject(new NetworkShip(getState(), this));
/*      */   }
/*      */ 
/*      */   public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
/*      */   {
/*  447 */     switch (paramShort)
/*      */     {
/*      */     case 1:
/*  450 */       isOnServer();
/*      */     }
/*      */ 
/*  457 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*  458 */     super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
/*      */   }
/*      */ 
/*      */   public final void a(lE paramlE, Sendable paramSendable, q paramq)
/*      */   {
/*  466 */     a();
/*      */ 
/*  469 */     if (isOnServer())
/*      */     {
/*  475 */       if (paramlE.c() != getSectorId()) {
/*  476 */         System.err.println("[SERVER][ONATTACHPLAYER] entering! " + this + " in a different sector");
/*      */ 
/*  479 */         if (isOnServer())
/*  480 */           paramSendable = ((vg)getState()).a().getSector(getSectorId()).jdField_a_of_type_Q;
/*      */         else {
/*  482 */           paramSendable = ((mv)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId())).a();
/*      */         }
/*      */ 
/*  485 */         paramlE.a(new q(paramSendable));
/*  486 */         paramlE.c(getSectorId());
/*      */ 
/*  490 */         if ((
/*  490 */           paramq = paramlE.a()) != null)
/*      */         {
/*  491 */           System.err.println("[SERVER][ONATTACHPLAYER] entering! Moving along playercharacter " + this + " in a different sector");
/*  492 */           paramq.setSectorId(getSectorId());
/*      */         } else {
/*  494 */           System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + paramlE);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  504 */     if (!isOnServer())
/*      */     {
/*  510 */       if ((
/*  510 */         paramSendable = (ct)getState())
/*  510 */         .a() == paramlE)
/*      */       {
/*  512 */         xe.a().a(this, "0022_ambience loop - interior cockpit (loop)", 1.0F, 1.0F);
/*      */ 
/*  514 */         if (!paramSendable.a().a(this)) {
/*  515 */           paramq = new cz(paramSendable.a(), getUniqueIdentifier());
/*  516 */           paramSendable.a().a().add(paramq);
/*  517 */           paramq.a(9, new q(8, 8, 8), true);
/*      */         }
/*  519 */         paramSendable.a().a().a()
/*  521 */           .a().a().c(true);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable)
/*      */   {
/*  536 */     super.onCollision(paramManifoldPoint, paramSendable);
/*      */ 
/*  538 */     if ((isOnServer()) && ((paramSendable instanceof EditableSendableSegmentController)) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getExplosiveCollectionManager().getCollection().isEmpty())) {
/*  539 */       paramManifoldPoint.getPositionWorldOnB(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  540 */       getWorldTransformInverse().transform(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  541 */       this.jdField_a_of_type_JavaxVecmathVector3f.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/*      */       try
/*      */       {
/*  544 */         for (int i = -1; i < 2; i++) {
/*  545 */           for (int j = -1; j < 2; j++) {
/*  546 */             for (int k = -1; k < 2; k++) {
/*  547 */               this.jdField_b_of_type_Q.b(Math.round(this.jdField_a_of_type_JavaxVecmathVector3f.x) + 8, Math.round(this.jdField_a_of_type_JavaxVecmathVector3f.y) + 8, Math.round(this.jdField_a_of_type_JavaxVecmathVector3f.z) + 8);
/*  548 */               this.jdField_b_of_type_Q.jdField_a_of_type_Int += i;
/*  549 */               this.jdField_b_of_type_Q.b += j;
/*  550 */               this.jdField_b_of_type_Q.c += k;
/*      */               le localle;
/*  552 */               if (((
/*  552 */                 localle = getSegmentBuffer().a(this.jdField_b_of_type_Q, false, this.jdField_a_of_type_Le)) != null) && 
/*  552 */                 (localle.a() == 14)) {
/*  553 */                 this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getExplosiveElementManager().addExplosion(this.jdField_b_of_type_Q, paramManifoldPoint.getPositionWorldOnB(new Vector3f()), (EditableSendableSegmentController)paramSendable, localle);
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */         return;
/*      */       } catch (IOException localIOException) { localIOException.printStackTrace();
/*      */         return;
/*      */       } catch (InterruptedException localInterruptedException) { localInterruptedException.printStackTrace(); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void onCoreDestroyed(lb paramlb)
/*      */   {
/*  580 */     for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/*  581 */       ((lE)this.jdField_a_of_type_JavaUtilArrayList.get(i)).b(0.0F, paramlb);
/*      */     }
/*  583 */     this.jdField_a_of_type_Kp.a(paramlb);
/*      */ 
/*  585 */     if ((isOnServer()) && 
/*  586 */       (this.jdField_a_of_type_Long < 0L)) {
/*  587 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  588 */       this.jdField_b_of_type_Long = Math.min(900000, getTotalElements() * 1000);
/*      */ 
/*  590 */       System.err.println("[SERVER] MAIN CORE STARTED DESTRUCTION: in " + this.jdField_b_of_type_Long / 1000L + " - started " + this.jdField_a_of_type_Long);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void onDamageServer(int paramInt, lb paramlb)
/*      */   {
/*  602 */     this.jdField_a_of_type_Kp.b(paramlb);
/*      */   }
/*      */ 
/*      */   public final void a(lE paramlE, boolean paramBoolean)
/*      */   {
/*  608 */     if (!isOnServer())
/*      */     {
/*  611 */       if (((
/*  611 */         paramBoolean = (ct)getState())
/*  611 */         .a() == paramlE) && (((ct)getState()).a() == paramlE)) {
/*  612 */         paramBoolean.a().a().a()
/*  614 */           .a().a().c(false);
/*      */ 
/*  616 */         xe.a().a(this, "0022_spaceship user - small engine thruster loop", 1.0F, 1.0F);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  621 */     a();
/*      */   }
/*      */ 
/*      */   public void onProximity(SegmentController paramSegmentController)
/*      */   {
/*  629 */     this.jdField_a_of_type_Kp.a(paramSegmentController);
/*      */   }
/*      */ 
/*      */   public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*      */   {
/*  639 */     switch (paramShort)
/*      */     {
/*      */     case 1:
/*  642 */       if (!isOnServer()) {
/*  643 */         xe.a().a(this);
/*      */       }
/*      */ 
/*      */       break;
/*      */     }
/*      */ 
/*  649 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*  650 */     super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*      */   }
/*      */ 
/*      */   private void a()
/*      */   {
/*  655 */     if (getRealName().equals("undef"))
/*      */       return;
/*      */     StringBuffer localStringBuffer;
/*  658 */     (
/*  659 */       localStringBuffer = new StringBuffer())
/*  659 */       .append(getRealName());
/*      */ 
/*  661 */     if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*  662 */       localStringBuffer.append(" <");
/*  663 */       for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/*      */         try {
/*  665 */           localStringBuffer.append(((lE)this.jdField_a_of_type_JavaUtilArrayList.get(i)).getName());
/*  666 */           if (i < this.jdField_a_of_type_JavaUtilArrayList.size() - 1)
/*  667 */             localStringBuffer.append(", ");
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/*  671 */           localException.printStackTrace();
/*      */         }
/*      */       }
/*      */ 
/*  673 */       localStringBuffer.append(">");
/*      */     }
/*      */ 
/*  677 */     if (getFactionId() != 0) {
/*  678 */       localStringBuffer.append("[");
/*      */       lP locallP;
/*  680 */       if ((
/*  680 */         locallP = ((vf)getState()).a().a(getFactionId())) != null)
/*      */       {
/*  681 */         localStringBuffer.append(locallP.a());
/*      */       } else {
/*  683 */         localStringBuffer.append("factionUnknown");
/*  684 */         localStringBuffer.append(getFactionId());
/*      */       }
/*  686 */       localStringBuffer.append("]");
/*      */     }
/*  688 */     this.jdField_a_of_type_JavaLangString = localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public void startCreatorThread()
/*      */   {
/*  704 */     if (getCreatorThread() == null)
/*  705 */       setCreatorThread(new kI(this));
/*      */   }
/*      */ 
/*      */   public String toNiceString()
/*      */   {
/*  714 */     if (((String)((NetworkShip)super.getNetworkObject()).debugState.get()).length() > 0) {
/*  715 */       return this.jdField_a_of_type_JavaLangString + (String)((NetworkShip)super.getNetworkObject()).debugState.get() + "\n[CLIENTAI " + (this.jdField_a_of_type_Kp.a() ? "ACTIVE" : "INACTIVE") + "] " + this.jdField_a_of_type_Kp.a();
/*      */     }
/*      */ 
/*  718 */     getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId());
/*  719 */     if (!isOnServer()) {
/*  720 */       return this.jdField_a_of_type_JavaLangString;
/*      */     }
/*  722 */     return this.jdField_a_of_type_JavaLangString;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  730 */     return "Ship[" + getRealName() + "](" + getId() + ")";
/*      */   }
/*      */ 
/*      */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*      */   {
/*  738 */     super.updateFromNetworkObject(paramNetworkObject);
/*      */ 
/*  740 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateFromNetworkObject(paramNetworkObject);
/*  741 */     this.jdField_a_of_type_Kp.d();
/*  742 */     if (!isOnServer())
/*      */     {
/*  745 */       this.jdField_a_of_type_Long = ((NetworkShip)paramNetworkObject).coreDestructionStarted.get().longValue();
/*  746 */       this.jdField_b_of_type_Long = ((NetworkShip)paramNetworkObject).coreDestructionDuration.get().longValue();
/*      */ 
/*  750 */       if (!((NetworkShip)paramNetworkObject).onHitNotices.getReceiveBuffer().isEmpty())
/*  751 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onHitNotice();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void updateLocal(xq paramxq)
/*      */   {
/*  760 */     if (!isOnServer()) {
/*  761 */       if ((!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) && (((lE)this.jdField_a_of_type_JavaUtilArrayList.get(0)).a())) {
/*  762 */         getRemoteTransformable().jdField_a_of_type_Boolean = false;
/*  763 */         getRemoteTransformable().a(true);
/*      */       } else {
/*  765 */         getRemoteTransformable().jdField_a_of_type_Boolean = true;
/*  766 */         getRemoteTransformable().a(false);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  771 */     super.updateLocal(paramxq);
/*      */ 
/*  779 */     if ((!getDockingController().b()) && (this.flagUpdateMass))
/*  780 */       if (isOnServer())
/*      */       {
/*  783 */         if (updateMass())
/*      */         {
/*  784 */           this.flagUpdateMass = false;
/*      */         }
/*      */       } else {
/*  787 */         getPhysicsDataContainer().updateMass(getMass(), true);
/*  788 */         this.flagUpdateMass = false;
/*      */       }
/*      */     label209: Object localObject;
/*  796 */     if (this.jdField_a_of_type_Long > 0L) {
/*  797 */       long l1 = System.currentTimeMillis() - this.jdField_a_of_type_Long;
/*  798 */       long l3 = this.jdField_b_of_type_Long - l1;
/*      */ 
/*  800 */       if ((l1 <= 1000L) || (this.jdField_a_of_type_JavaUtilArrayList.size() <= 0)) {
/*  801 */         if (l1 <= this.jdField_b_of_type_Long)
/*      */         {
/*      */           break label209;
/*      */         }
/*  805 */         if (isOnServer()) {
/*  806 */           destroy();
/*      */         }
/*      */       }
/*      */ 
/*  810 */       this.jdField_a_of_type_Long = -1L;
/*  811 */       this.jdField_b_of_type_Long = -1L;
/*      */ 
/*  813 */       if (!isOnServer())
/*      */       {
/*  815 */         float f1 = 3.0E-005F;
/*      */ 
/*  817 */         if (l3 < 4000L)
/*  818 */           f1 = 0.1F;
/*  819 */         else if (l3 < 30000L)
/*  820 */           f1 = 0.02F;
/*  821 */         else if (l3 < 120000L)
/*  822 */           f1 = 0.003F;
/*  823 */         else if (l3 < 240000L) {
/*  824 */           f1 = 0.0005F;
/*      */         }
/*      */ 
/*  828 */         if (Math.random() < f1)
/*      */         {
/*  830 */           ct localct = (ct)getState();
/*      */ 
/*  832 */           Vector3f localVector3f = new Vector3f();
/*  833 */           getSegmentBuffer().a().a(localVector3f);
/*      */ 
/*  835 */           float f2 = localVector3f.length() / 2.0F;
/*      */ 
/*  837 */           localObject = new Vector3f((float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D));
/*  838 */           while (((Vector3f)localObject).lengthSquared() == 0.0F) {
/*  839 */             ((Vector3f)localObject).set((float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D));
/*      */           }
/*  841 */           ((Vector3f)localObject).normalize();
/*  842 */           ((Vector3f)localObject).scale((float)(f2 * (1.0D + Math.random())));
/*  843 */           localVector3f.set(getWorldTransform().origin);
/*  844 */           localVector3f.add((Tuple3f)localObject);
/*      */ 
/*  847 */           localct.a().a().a(localVector3f, (float)(2.0D + Math.random() * 40.0D));
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  854 */     if (isOnServer()) {
/*      */       try
/*      */       {
/*  857 */         if ((getTotalElements() <= 0) && (System.currentTimeMillis() - this.c > 2000L) && (System.currentTimeMillis() - getTimeCreated() > 5000L) && (isEmptyOnServer()))
/*      */         {
/*  863 */           if (((
/*  863 */             localObject = getSegmentBuffer().a(new q(jdField_a_of_type_Q), true)) != null) && 
/*  863 */             (((le)localObject).a() != 1)) {
/*  864 */             System.err.println("[SERVER][SHIP] Empty SHIP: deleting " + this);
/*  865 */             markedForPermanentDelete(true);
/*  866 */             setMarkedForDeleteVolatile(true);
/*      */           }
/*  868 */           this.c = System.currentTimeMillis();
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  874 */         localIOException.printStackTrace();
/*      */       }
/*      */       catch (InterruptedException localInterruptedException)
/*      */       {
/*  874 */         localInterruptedException.printStackTrace();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  877 */     long l2 = System.currentTimeMillis();
/*      */     try
/*      */     {
/*  880 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateLocal(paramxq);
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*  884 */       System.err.println("Exception catched: " + localException + " printing startrace and then rethrowing");
/*  885 */       localException.printStackTrace();
/*  886 */       throw new ErrorDialogException(localException.getClass().getSimpleName() + ": " + localException.getMessage());
/*      */     }
/*      */     long l4;
/*  889 */     if ((
/*  889 */       l4 = System.currentTimeMillis() - l2) > 
/*  889 */       20L) {
/*  890 */       System.err.println("[SHIP] " + getState() + " " + this + " manager udpate took " + l4);
/*      */     }
/*      */ 
/*  894 */     l2 = System.currentTimeMillis();
/*  895 */     if ((this.jdField_a_of_type_JavaLangString.length() == 0) || (this.jdField_a_of_type_Int != getFactionId())) {
/*  896 */       a();
/*  897 */       this.jdField_a_of_type_Int = getFactionId();
/*      */     }
/*      */     long l5;
/*  900 */     if ((
/*  900 */       l5 = System.currentTimeMillis() - l2) > 
/*  900 */       5L) {
/*  901 */       System.err.println("[SHIP] " + getState() + " " + this + " name udpate took " + l5);
/*      */     }
/*      */ 
/*  905 */     l2 = System.currentTimeMillis();
/*  906 */     this.jdField_a_of_type_Kp.a(paramxq);
/*      */     long l6;
/*  908 */     if ((
/*  908 */       l6 = System.currentTimeMillis() - l2) > 
/*  908 */       15L)
/*  909 */       System.err.println("[SHIP] " + getState() + " " + this + " AI udpate took " + l6);
/*      */   }
/*      */ 
/*      */   public boolean updateMass()
/*      */   {
/*  918 */     if (isOnServer()) {
/*  919 */       float f = Math.max(0.1F, getTotalElements() * 0.1F);
/*  920 */       setMass(f);
/*      */ 
/*  926 */       return getPhysicsDataContainer().updateMass(f, false);
/*      */     }
/*      */ 
/*  928 */     return true;
/*      */   }
/*      */ 
/*      */   public void updateToFullNetworkObject()
/*      */   {
/*  939 */     super.updateToFullNetworkObject();
/*  940 */     if (isOnServer()) {
/*  941 */       super.getNetworkObject(); this.jdField_a_of_type_Kp.c();
/*      */ 
/*  943 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateToFullNetworkObject((NetworkShip)super.getNetworkObject());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void updateToNetworkObject()
/*      */   {
/*  951 */     super.updateToNetworkObject();
/*  952 */     if (isOnServer()) {
/*  953 */       ((NetworkShip)super.getNetworkObject()).jamming.set(Boolean.valueOf(this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.isJamming()));
/*  954 */       ((NetworkShip)super.getNetworkObject()).cloaked.set(Boolean.valueOf(this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.isCloaked()));
/*  955 */       ((NetworkShip)super.getNetworkObject()).coreDestructionStarted.set(this.jdField_a_of_type_Long);
/*  956 */       ((NetworkShip)super.getNetworkObject()).coreDestructionDuration.set(this.jdField_b_of_type_Long);
/*  957 */       this.jdField_a_of_type_Kp.a((NetworkShip)super.getNetworkObject());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void getRelationColor(lZ paramlZ, Vector4f paramVector4f, float paramFloat)
/*      */   {
/*  969 */     switch (ke.a[paramlZ.ordinal()]) {
/*      */     case 1:
/*  971 */       paramVector4f.x = (paramFloat + 1.0F);
/*  972 */       paramVector4f.y = paramFloat;
/*  973 */       paramVector4f.z = paramFloat;
/*  974 */       return;
/*      */     case 2:
/*  977 */       paramVector4f.x = paramFloat;
/*  978 */       paramVector4f.y = (paramFloat + 1.0F);
/*  979 */       paramVector4f.z = paramFloat;
/*  980 */       return;
/*      */     case 3:
/*  983 */       if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*  984 */         paramVector4f.x = (paramFloat + 0.5F);
/*  985 */         paramVector4f.y = (paramFloat + 0.7F);
/*  986 */         paramVector4f.z = (paramFloat + 0.9F); return;
/*      */       }
/*  988 */       paramVector4f.x = (paramFloat + 0.3F);
/*  989 */       paramVector4f.y = (paramFloat + 0.5F);
/*  990 */       paramVector4f.z = (paramFloat + 0.7F);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected String getSegmentControllerTypeString()
/*      */   {
/* 1000 */     return "Ship";
/*      */   }
/*      */ 
/*      */   public final String a()
/*      */   {
/* 1007 */     return "0022_ambience loop - interior cockpit (loop)";
/*      */   }
/*      */ 
/*      */   public final String b()
/*      */   {
/* 1014 */     return "0022_spaceship user - small engine thruster loop";
/*      */   }
/*      */ 
/*      */   public final float b()
/*      */   {
/* 1035 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   73 */     jdField_a_of_type_Q = new q(8, 8, 8);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kd
 * JD-Core Version:    0.6.2
 */