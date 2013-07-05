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
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import javax.vecmath.Vector3f;
/*     */ import mh;
/*     */ import ml;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager;
/*     */ import org.schema.game.common.controller.elements.door.DoorCollectionManager;
/*     */ import org.schema.game.common.controller.elements.lift.LiftCollectionManager;
/*     */ import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
/*     */ import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager;
/*     */ import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
/*     */ import org.schema.game.common.controller.elements.power.PowerCollectionManager;
/*     */ import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
/*     */ import org.schema.game.common.controller.elements.repair.RepairElementManager;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*     */ import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
/*     */ import org.schema.game.network.objects.NetworkDoorInterface;
/*     */ import org.schema.game.network.objects.NetworkLiftInterface;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ import q;
/*     */ import s;
/*     */ import xq;
/*     */ 
/*     */ public class StationaryManagerContainer extends ManagerContainer
/*     */   implements mh, DoorContainerInterface, FactoryAddOnInterface, LiftContainerInterface, ShieldContainerInterface, WeaponManagerInterface, DockingBlockManagerInterface
/*     */ {
/*     */   private ManagerModuleSingle shields;
/*     */   private ManagerModuleSingle power;
/*     */   private ManagerModuleSingle lift;
/*     */   private ManagerModuleSingle door;
/*     */   private ManagerModuleCollection repair;
/*     */   private ManagerModuleSingle powerCapacity;
/*     */   private ManagerModuleCollection weapon;
/*     */   private ManagerModuleCollection dumbMissile;
/*     */   private ManagerModuleCollection heatMissile;
/*     */   private ManagerModuleCollection fafoMissile;
/*     */   private ManagerModuleCollection turretDockingBlock;
/*     */   private ManagerModuleCollection fixedDockingBlock;
/*     */   private ManagerContainerFactoryAddOn factory;
/*  79 */   private final ArrayList initialPointDists = new ArrayList();
/*     */   private final PowerAddOn powerAddOn;
/*     */ 
/*     */   public StationaryManagerContainer(SegmentController paramSegmentController)
/*     */   {
/*  85 */     super(paramSegmentController);
/*  86 */     this.powerAddOn = new PowerAddOn(this, paramSegmentController);
/*     */   }
/*     */ 
/*     */   public Collection getDockingBlock()
/*     */   {
/*  93 */     return this.dockingModules;
/*     */   }
/*     */ 
/*     */   private NetworkDoorInterface getDoorInterface()
/*     */   {
/*  98 */     return (NetworkDoorInterface)getSegmentController().getNetworkObject();
/*     */   }
/*     */ 
/*     */   public DoorCollectionManager getDoorManager()
/*     */   {
/* 106 */     return (DoorCollectionManager)this.door.getCollectionManager();
/*     */   }
/*     */ 
/*     */   private NetworkLiftInterface getLiftInterface() {
/* 110 */     return (NetworkLiftInterface)getSegmentController().getNetworkObject();
/*     */   }
/*     */ 
/*     */   public LiftCollectionManager getLiftManager()
/*     */   {
/* 117 */     return (LiftCollectionManager)this.lift.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public PowerCollectionManager getPowerManager() {
/* 121 */     return (PowerCollectionManager)this.power.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getRepair() {
/* 125 */     return this.repair;
/*     */   }
/*     */ 
/*     */   public ShieldCollectionManager getShieldManager()
/*     */   {
/* 132 */     return (ShieldCollectionManager)this.shields.getCollectionManager();
/*     */   }
/*     */ 
/*     */   public void handleClientRemoteDoor(q paramq) {
/* 136 */     getDoorInterface().getDoorActivate().forceClientUpdates();
/* 137 */     (
/* 138 */       paramq = new s(paramq)).d = 
/* 138 */       -1;
/* 139 */     getDoorInterface().getDoorActivate().add(new RemoteVector4i(paramq, getSegmentController().getNetworkObject()));
/*     */   }
/*     */ 
/*     */   public void handleClientRemoteLift(q paramq) {
/* 143 */     getLiftInterface().getLiftActivate().forceClientUpdates();
/* 144 */     (
/* 145 */       paramq = new s(paramq)).d = 
/* 145 */       1;
/* 146 */     getLiftInterface().getLiftActivate().add(new RemoteVector4i(paramq, getSegmentController().getNetworkObject()));
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 152 */     getModules().add(this.lift = new ManagerModuleSingle(new VoidElementManager(new LiftCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)113));
/*     */ 
/* 154 */     getModules().add(this.door = new ManagerModuleSingle(new VoidElementManager(new DoorCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)122));
/*     */ 
/* 156 */     getModules().add(this.shields = new ManagerModuleSingle(new VoidElementManager(new ShieldCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)3));
/*     */ 
/* 158 */     getModules().add(this.power = new ManagerModuleSingle(new VoidElementManager(new PowerCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)2));
/*     */ 
/* 160 */     getModules().add(this.powerCapacity = new ManagerModuleSingle(new VoidElementManager(new PowerCapacityCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)331));
/*     */ 
/* 162 */     getModules().add(this.turretDockingBlock = new ManagerModuleCollection(new TurretDockingBlockElementManager(getSegmentController()), (short)7, (short)88));
/*     */ 
/* 164 */     getModules().add(this.fixedDockingBlock = new ManagerModuleCollection(new FixedDockingBlockElementManager(getSegmentController()), (short)289, (short)290));
/*     */ 
/* 166 */     getModules().add(this.repair = new ManagerModuleCollection(new RepairElementManager(getSegmentController()), (short)39, (short)30));
/*     */ 
/* 168 */     getModules().add(this.weapon = new ManagerModuleCollection(new WeaponElementManager(getSegmentController()), (short)6, (short)16));
/*     */ 
/* 170 */     getModules().add(this.dumbMissile = new ManagerModuleCollection(new DumbMissileElementManager(getSegmentController()), (short)38, (short)32));
/*     */ 
/* 172 */     getModules().add(this.fafoMissile = new ManagerModuleCollection(new FafoMissileElementManager(getSegmentController()), (short)54, (short)48));
/*     */ 
/* 174 */     getModules().add(this.heatMissile = new ManagerModuleCollection(new HeatMissileElementManager(getSegmentController()), (short)46, (short)40));
/*     */ 
/* 176 */     this.factory = new ManagerContainerFactoryAddOn();
/*     */ 
/* 178 */     this.factory.initialize(getModules(), getSegmentController());
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 188 */     super.updateLocal(paramxq);
/* 189 */     this.factory.update(paramxq, getSegmentController().isOnServer());
/* 190 */     this.powerAddOn.update(paramxq);
/*     */   }
/*     */ 
/*     */   public void onAction()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ml getInventoryNetworkObject()
/*     */   {
/* 199 */     return (ml)getSegmentController().getNetworkObject();
/*     */   }
/*     */ 
/*     */   public ManagerContainerFactoryAddOn getFactory()
/*     */   {
/* 205 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public double handleShieldHit(Vector3f paramVector3f, SegmentController paramSegmentController, float paramFloat)
/*     */   {
/* 210 */     double d1 = getShieldManager().getShields();
/*     */ 
/* 212 */     onHit((int)paramFloat);
/*     */     double d2;
/* 214 */     if (getShieldManager().getShields() <= 0.0D)
/*     */     {
/* 217 */       if (paramFloat < d1)
/* 218 */         d2 = d1;
/*     */       else
/* 220 */         d2 = paramFloat;
/*     */     }
/*     */     else {
/* 223 */       d2 = 0.0D;
/*     */     }
/*     */ 
/* 228 */     if (!getSegmentController().isOnServer())
/*     */     {
/* 230 */       paramSegmentController = (ct)getState();
/*     */       Transform localTransform;
/* 231 */       (
/* 232 */         localTransform = new Transform())
/* 232 */         .setIdentity();
/* 233 */       localTransform.origin.set(paramVector3f);
/*     */ 
/* 236 */       ij.a.add(new eH(localTransform, String.valueOf((int)paramFloat), 1.0F, 0.0F, 0.0F));
/*     */ 
/* 238 */       paramSegmentController.a().a().a(paramVector3f, 4.0F);
/*     */ 
/* 241 */       if ((
/* 241 */         paramSegmentController = paramSegmentController.a().a().a(getSegmentController())) != null)
/*     */       {
/* 242 */         paramSegmentController.a(paramVector3f);
/*     */       }
/*     */     }
/*     */ 
/* 246 */     return d2;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getShields()
/*     */   {
/* 252 */     return this.shields;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getPower()
/*     */   {
/* 258 */     return this.power;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getLift()
/*     */   {
/* 264 */     return this.lift;
/*     */   }
/*     */ 
/*     */   public ManagerModuleSingle getDoor()
/*     */   {
/* 270 */     return this.door;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getWeapon()
/*     */   {
/* 276 */     return this.weapon;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getDumbMissile()
/*     */   {
/* 282 */     return this.dumbMissile;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getHeatMissile()
/*     */   {
/* 288 */     return this.heatMissile;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getFafoMissile()
/*     */   {
/* 294 */     return this.fafoMissile;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getTurretDockingBlock()
/*     */   {
/* 300 */     return this.turretDockingBlock;
/*     */   }
/*     */ 
/*     */   public ManagerModuleCollection getFixedDockingBlock()
/*     */   {
/* 306 */     return this.fixedDockingBlock;
/*     */   }
/*     */ 
/*     */   public ArrayList getInitialPointDists()
/*     */   {
/* 312 */     return this.initialPointDists;
/*     */   }
/*     */ 
/*     */   public PowerAddOn getPowerAddOn()
/*     */   {
/* 319 */     return this.powerAddOn;
/*     */   }
/*     */ 
/*     */   public PowerCapacityCollectionManager getPowerCapacityManager() {
/* 323 */     return (PowerCapacityCollectionManager)this.powerCapacity.getCollectionManager();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.StationaryManagerContainer
 * JD-Core Version:    0.6.2
 */