/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import L;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import ct;
/*     */ import cw;
/*     */ import cz;
/*     */ import jL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import kW;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import q;
/*     */ 
/*     */ public abstract class UsableElementManager extends kW
/*     */ {
/*     */   private final SegmentController segmentController;
/*     */ 
/*     */   public UsableElementManager(SegmentController paramSegmentController)
/*     */   {
/*  34 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */ 
/*     */   public boolean canHandle(lA paramlA)
/*     */   {
/*  46 */     return paramlA.jdField_a_of_type_LE.b();
/*     */   }
/*     */ 
/*     */   public cz checkShipConfig(lA paramlA) {
/*  50 */     if (!paramlA.jdField_a_of_type_LE.a(getSegmentController())) {
/*  51 */       throw new ShipConfigurationNotFoundException("does not exist for that ship");
/*     */     }
/*  53 */     reassignControllerKeysIfNecessary(paramlA.jdField_a_of_type_LE, (q)paramlA.jdField_a_of_type_JavaLangObject);
/*     */ 
/*  57 */     return paramlA.jdField_a_of_type_LE.a(getSegmentController());
/*     */   }
/*     */ 
/*     */   protected boolean clientIsOwnShip()
/*     */   {
/*  61 */     return ((cw)this.segmentController).isClientOwnObject();
/*     */   }
/*     */   protected ArrayList getAttachedPlayers() {
/*  64 */     return ((kd)this.segmentController).a();
/*     */   }
/*     */   public ControlElementMap getControlElementMap() {
/*  67 */     return this.segmentController.getControlElementMap();
/*     */   }
/*     */ 
/*     */   public ManagerContainer getManagerContainer()
/*     */   {
/*  73 */     return ((ld)this.segmentController).a();
/*     */   }
/*     */ 
/*     */   public abstract ElementCollectionManager getNewCollectionManager(le paramle);
/*     */ 
/*     */   protected L getParticleController() {
/*  79 */     return ((ParticleHandler)this.segmentController).getParticleController();
/*     */   }
/*     */ 
/*     */   protected PhysicsDataContainer getPhysicsDataContainer() {
/*  83 */     return this.segmentController.getPhysicsDataContainer();
/*     */   }
/*     */   protected PowerAddOn getPowerManager() {
/*  86 */     return ((PowerManagerInterface)getManagerContainer()).getPowerAddOn();
/*     */   }
/*     */   protected jL getSegmentBuffer() {
/*  89 */     return this.segmentController.getSegmentBuffer();
/*     */   }
/*     */ 
/*     */   public SegmentController getSegmentController()
/*     */   {
/*  96 */     return this.segmentController;
/*     */   }
/*     */ 
/*     */   protected StateInterface getState() {
/* 100 */     return this.segmentController.getState();
/*     */   }
/*     */ 
/*     */   protected Transform getWorldTransform() {
/* 104 */     return this.segmentController.getWorldTransform();
/*     */   }
/*     */ 
/*     */   public abstract void handle(lA paramlA);
/*     */ 
/*     */   private void reassignControllerKeysIfNecessary(lE paramlE, q paramq)
/*     */   {
/* 112 */     paramlE = paramlE.a(getSegmentController());
/* 113 */     if (((getState() instanceof ct)) && (
/* 114 */       (paramlE.a.size() == 0) || ((paramlE.a.size() == 1) && (paramlE.a(9)) && (paramlE.a(9).a(8, 8, 8)))))
/*     */     {
/* 118 */       paramq = getControlElementMap().getControlledElements((short)32767, paramq);
/* 119 */       paramlE.a((ct)getState(), paramq);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableElementManager
 * JD-Core Version:    0.6.2
 */