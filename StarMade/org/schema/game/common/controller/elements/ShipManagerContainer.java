/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import ct;
/*     */ import dj;
/*     */ import eH;
/*     */ import eI;
/*     */ import eJ;
/*     */ import es;
/*     */ import ij;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import javax.vecmath.Vector3f;
/*     */ import kd;
/*     */ import kp;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import ml;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.cloaking.CloakingElementManager;
/*     */ import org.schema.game.common.controller.elements.dockingBeam.DockingBeamElementManager;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager;
/*     */ import org.schema.game.common.controller.elements.door.DoorCollectionManager;
/*     */ import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
/*     */ import org.schema.game.common.controller.elements.explosive.ExplosiveElementManager;
/*     */ import org.schema.game.common.controller.elements.harvest.SalvageElementManager;
/*     */ import org.schema.game.common.controller.elements.jamming.JammingCollectionManager;
/*     */ import org.schema.game.common.controller.elements.jamming.JammingElementManager;
/*     */ import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
/*     */ import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager;
/*     */ import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
/*     */ import org.schema.game.common.controller.elements.power.PowerCollectionManager;
/*     */ import org.schema.game.common.controller.elements.powerbeamdrain.PowerDrainElementManager;
/*     */ import org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyElementManager;
/*     */ import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
/*     */ import org.schema.game.common.controller.elements.repair.RepairElementManager;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*     */ import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*     */ import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkDoorInterface;
/*     */ import org.schema.game.network.objects.NetworkShip;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ import q;
/*     */ import s;
/*     */ import xm;
/*     */ import xq;
/*     */ 
/*     */ public class ShipManagerContainer extends ManagerContainer
/*     */   implements DoorContainerInterface, ManagerThrustInterface, ShieldContainerInterface, WeaponManagerInterface, DockingBlockManagerInterface
/*     */ {
/*     */   private DockingBeamElementManager dockingBeam;
/*     */   private final PowerAddOn powerAddOn;
/*     */   private ManagerModuleSingle shields;
/*     */   private ManagerModuleSingle thrust;
/*     */   private ManagerModuleSingle cloaking;
/*     */   private ManagerModuleSingle explosive;
/*     */   private ManagerModuleSingle jamming;
/*     */   private ManagerModuleSingle power;
/*     */   private ManagerModuleSingle powerCapacity;
/*     */   private ManagerModuleSingle door;
/*     */   private ManagerModuleCollection salvage;
/*     */   private ManagerModuleCollection powerDrain;
/*     */   private ManagerModuleCollection powerSupply;
/*     */   private ManagerModuleCollection turretDockingBlock;
/*     */   private ManagerModuleCollection fixedDockingBlock;
/*     */   private ManagerModuleCollection repair;
/*     */   private ManagerModuleCollection weapon;
/*     */   private ManagerModuleCollection dumbMissile;
/*     */   private ManagerModuleCollection heatMissile;
/*     */   private ManagerModuleCollection fafoMissile;
/* 136 */   private final ArrayList cockpits = new ArrayList();
/*     */ 
/*     */   public ManagerModuleSingle getDoor()
/*     */   {
/* 106 */     return this.door;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getTurretDockingBlock()
/*     */   {
/* 113 */     return this.turretDockingBlock;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getFixedDockingBlock()
/*     */   {
/* 120 */     return this.fixedDockingBlock;
/*     */   }
/*     */ 
/*     */   public ShipManagerContainer(kd paramkd)
/*     */   {
/* 142 */     super(paramkd);
/* 143 */     this.powerAddOn = new PowerAddOn(this, paramkd);
/*     */   }
/*     */ 
/*     */   public CloakingElementManager getCloakElementManager() {
/* 147 */     return (CloakingElementManager)this.cloaking.getElementManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getCloaking()
/*     */   {
/* 153 */     return this.cloaking;
/*     */   }
/*     */ 
/*     */   public ArrayList getCockpits()
/*     */   {
/* 159 */     return this.cockpits;
/*     */   }
/*     */ 
/*     */   public DockingBeamElementManager getDockingBeam()
/*     */   {
/* 169 */     return this.dockingBeam;
/*     */   }
/*     */ 
/*     */   public Collection getDockingBlock()
/*     */   {
/* 177 */     return this.dockingModules;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getDumbMissile()
/*     */   {
/* 185 */     return this.dumbMissile;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getExplosive()
/*     */   {
/* 191 */     return this.explosive;
/*     */   }
/*     */   public ExplosiveCollectionManager getExplosiveCollectionManager() {
/* 194 */     return (ExplosiveCollectionManager)this.explosive.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public ExplosiveElementManager getExplosiveElementManager() {
/* 198 */     return (ExplosiveElementManager)this.explosive.getElementManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getFafoMissile()
/*     */   {
/* 205 */     return this.fafoMissile;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getHeatMissile()
/*     */   {
/* 212 */     return this.heatMissile;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getJamming()
/*     */   {
/* 219 */     return this.jamming;
/*     */   }
/*     */ 
/*     */   public JammingElementManager getJammingElementManager() {
/* 223 */     return (JammingElementManager)this.jamming.getElementManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getPower()
/*     */   {
/* 230 */     return this.power;
/*     */   }
/*     */ 
/*     */   public PowerCollectionManager getPowerManager()
/*     */   {
/* 235 */     return (PowerCollectionManager)this.power.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getRepair()
/*     */   {
/* 242 */     return this.repair;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getSalvage()
/*     */   {
/* 250 */     return this.salvage;
/*     */   }
/*     */ 
/*     */   public ShieldCollectionManager getShieldManager() {
/* 254 */     return (ShieldCollectionManager)this.shields.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getShields()
/*     */   {
/* 261 */     return this.shields;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getThrust()
/*     */   {
/* 268 */     return this.thrust;
/*     */   }
/*     */ 
/*     */   public ThrusterElementManager getThrusterElementManager() {
/* 272 */     return (ThrusterElementManager)this.thrust.getElementManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getWeapon()
/*     */   {
/* 279 */     return this.weapon;
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/* 284 */     super.handle(paramlA);
/* 285 */     if (paramlA.a.b())
/* 286 */       this.dockingBeam.handle(paramlA);
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 292 */     this.dockingBeam = new DockingBeamElementManager(getSegmentController());
/*     */ 
/* 295 */     this.modules.add(this.shields = new ManagerModuleSingle(new VoidElementManager(new ShieldCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)3));
/*     */ 
/* 297 */     this.modules.add(this.thrust = new ManagerModuleSingle(new ThrusterElementManager(getSegmentController()), (short)1, (short)8));
/*     */ 
/* 299 */     this.modules.add(this.door = new ManagerModuleSingle(new VoidElementManager(new DoorCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)122));
/*     */ 
/* 301 */     this.modules.add(this.cloaking = new ManagerModuleSingle(new CloakingElementManager(getSegmentController()), (short)1, (short)22));
/*     */ 
/* 303 */     this.modules.add(this.explosive = new ManagerModuleSingle(new ExplosiveElementManager(getSegmentController()), (short)1, (short)14));
/*     */ 
/* 305 */     this.modules.add(this.jamming = new ManagerModuleSingle(new JammingElementManager(new JammingCollectionManager(getSegmentController()), getSegmentController()), (short)1, (short)15));
/*     */ 
/* 307 */     this.modules.add(this.power = new ManagerModuleSingle(new VoidElementManager(new PowerCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)2));
/*     */ 
/* 309 */     this.modules.add(this.powerCapacity = new ManagerModuleSingle(new VoidElementManager(new PowerCapacityCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)331));
/*     */ 
/* 311 */     this.modules.add(this.salvage = new ManagerModuleCollection(new SalvageElementManager(getSegmentController()), (short)4, (short)24));
/*     */ 
/* 313 */     this.modules.add(this.powerDrain = new ManagerModuleCollection(new PowerDrainElementManager(getSegmentController()), (short)332, (short)333));
/*     */ 
/* 315 */     this.modules.add(this.powerSupply = new ManagerModuleCollection(new PowerSupplyElementManager(getSegmentController()), (short)334, (short)335));
/*     */ 
/* 317 */     this.modules.add(this.turretDockingBlock = new ManagerModuleCollection(new TurretDockingBlockElementManager(getSegmentController()), (short)7, (short)88));
/*     */ 
/* 319 */     this.modules.add(this.fixedDockingBlock = new ManagerModuleCollection(new FixedDockingBlockElementManager(getSegmentController()), (short)289, (short)290));
/*     */ 
/* 321 */     this.modules.add(this.repair = new ManagerModuleCollection(new RepairElementManager(getSegmentController()), (short)39, (short)30));
/*     */ 
/* 323 */     this.modules.add(this.weapon = new ManagerModuleCollection(new WeaponElementManager(getSegmentController()), (short)6, (short)16));
/*     */ 
/* 325 */     this.modules.add(this.dumbMissile = new ManagerModuleCollection(new DumbMissileElementManager(getSegmentController()), (short)38, (short)32));
/*     */ 
/* 327 */     this.modules.add(this.fafoMissile = new ManagerModuleCollection(new FafoMissileElementManager(getSegmentController()), (short)54, (short)48));
/*     */ 
/* 329 */     this.modules.add(this.heatMissile = new ManagerModuleCollection(new HeatMissileElementManager(getSegmentController()), (short)46, (short)40));
/*     */   }
/*     */ 
/*     */   public boolean isCloaked() {
/* 333 */     return getCloakElementManager().isCloaked();
/*     */   }
/*     */ 
/*     */   public boolean isJamming() {
/* 337 */     return getJammingElementManager().isJamming();
/*     */   }
/*     */ 
/*     */   public DoorCollectionManager getDoorManager()
/*     */   {
/* 344 */     return (DoorCollectionManager)this.door.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public void onAction() {
/* 348 */     ((CloakingElementManager)this.cloaking.getElementManager()).stopCloak();
/*     */   }
/*     */ 
/*     */   public void onAddedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*     */   {
/* 356 */     super.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*     */ 
/* 358 */     switch (paramShort)
/*     */     {
/*     */     case 47:
/* 361 */       paramShort = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/*     */ 
/* 363 */       if (!getCockpits().contains(paramShort)) { getCockpits().add(paramShort);
/*     */         return;
/*     */       }
/*     */       break;
/*     */     case 121:
/* 369 */       ((kd)getSegmentController()).a().a(new le(paramSegment, paramByte1, paramByte2, paramByte3));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onHitNotice()
/*     */   {
/* 378 */     if (((kd)getSegmentController()).isOnServer()) {
/* 379 */       if (((isCloaked()) || (isJamming())) && 
/* 380 */         (((kd)getSegmentController()).a().onHitNotices.isEmpty())) {
/* 381 */         ((CloakingElementManager)this.cloaking.getElementManager()).onHit();
/* 382 */         ((JammingElementManager)this.jamming.getElementManager()).onHit();
/* 383 */         ((kd)getSegmentController()).a().onHitNotices.add(new RemoteBoolean(true, ((kd)getSegmentController()).a()));
/*     */       }
/*     */     }
/*     */     else {
/* 387 */       ((CloakingElementManager)this.cloaking.getElementManager()).onHit();
/* 388 */       ((JammingElementManager)this.jamming.getElementManager()).onHit();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*     */   {
/* 397 */     super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/* 398 */     switch (paramShort)
/*     */     {
/*     */     case 47:
/* 401 */       paramShort = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/* 402 */       getCockpits().remove(paramShort);
/* 403 */       return;
/*     */     case 121:
/* 406 */       ((kd)getSegmentController()).a().a(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void handleClientRemoteDoor(q paramq)
/*     */   {
/* 414 */     getDoorInterface().getDoorActivate().forceClientUpdates();
/* 415 */     (
/* 416 */       paramq = new s(paramq)).d = 
/* 416 */       -1;
/* 417 */     getDoorInterface().getDoorActivate().add(new RemoteVector4i(paramq, ((kd)getSegmentController()).a()));
/*     */   }
/*     */   private NetworkDoorInterface getDoorInterface() {
/* 420 */     return ((kd)getSegmentController()).a();
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 431 */     super.updateLocal(paramxq);
/* 432 */     this.dockingBeam.update(paramxq);
/* 433 */     this.powerAddOn.update(paramxq);
/*     */   }
/*     */ 
/*     */   public ml getInventoryNetworkObject()
/*     */   {
/* 438 */     return ((kd)getSegmentController()).a();
/*     */   }
/*     */ 
/*     */   public double handleShieldHit(Vector3f paramVector3f, SegmentController paramSegmentController, float paramFloat)
/*     */   {
/* 486 */     double d1 = getShieldManager().getShields();
/*     */ 
/* 488 */     onHit((int)paramFloat);
/*     */     double d2;
/* 490 */     if (getShieldManager().getShields() <= 0.0D)
/*     */     {
/* 493 */       if (paramFloat < d1)
/* 494 */         d2 = d1;
/*     */       else
/* 496 */         d2 = paramFloat;
/*     */     }
/*     */     else {
/* 499 */       d2 = 0.0D;
/*     */     }
/*     */ 
/* 504 */     if ((!((kd)getSegmentController()).isOnServer()) && (!xm.a()))
/*     */     {
/* 506 */       paramSegmentController = (ct)getState();
/*     */       Transform localTransform;
/* 507 */       (
/* 508 */         localTransform = new Transform())
/* 508 */         .setIdentity();
/* 509 */       localTransform.origin.set(paramVector3f);
/*     */ 
/* 512 */       ij.a.add(new eH(localTransform, String.valueOf((int)paramFloat), 1.0F, 0.0F, 0.0F));
/*     */ 
/* 514 */       paramSegmentController.a().a().a(paramVector3f, 4.0F);
/*     */ 
/* 517 */       if ((
/* 517 */         paramSegmentController = paramSegmentController.a().a().a(getSegmentController())) != null)
/*     */       {
/* 518 */         paramSegmentController.a(paramVector3f);
/*     */       }
/* 520 */       else System.err.println("[CLIENT] ERROR: shield drawer for " + getSegmentController() + " not initialized");
/*     */ 
/*     */     }
/*     */ 
/* 524 */     return d2;
/*     */   }
/*     */ 
/*     */   public PowerCapacityCollectionManager getPowerCapacityManager()
/*     */   {
/* 530 */     return (PowerCapacityCollectionManager)this.powerCapacity.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public PowerAddOn getPowerAddOn()
/*     */   {
/* 535 */     return this.powerAddOn;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getPowerDrain()
/*     */   {
/* 542 */     return this.powerDrain;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getPowerSupply()
/*     */   {
/* 549 */     return this.powerSupply;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ShipManagerContainer
 * JD-Core Version:    0.6.2
 */