/*     */ package org.schema.game.common.controller.elements.weapon;
/*     */ 
/*     */ import L;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import ct;
/*     */ import jE;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jv;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.controller.elements.ManagerReloadInterface;
/*     */ import org.schema.game.common.controller.elements.NTDistributionReceiverInterface;
/*     */ import org.schema.game.common.controller.elements.NTReceiveInterface;
/*     */ import org.schema.game.common.controller.elements.NTSenderInterface;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*     */ import org.schema.game.common.controller.elements.UsableDistributionControllableElementManager;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.NetworkEntity;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ import q;
/*     */ import x;
/*     */ import xe;
/*     */ import yE;
/*     */ 
/*     */ public class WeaponElementManager extends UsableDistributionControllableElementManager
/*     */   implements BlockActivationListenerInterface, ManagerReloadInterface, NTDistributionReceiverInterface, NTReceiveInterface, NTSenderInterface
/*     */ {
/*  43 */   private Vector3f shootingDirTemp = new Vector3f();
/*  44 */   private q controlledFromOrig = new q();
/*  45 */   private q controlledFrom = new q();
/*     */   private final ArrayList weaponManagers;
/*  75 */   public static boolean debug = false;
/*     */ 
/*     */   public WeaponElementManager(SegmentController paramSegmentController)
/*     */   {
/*  49 */     super((short)6, (short)16, paramSegmentController);
/*  50 */     this.weaponManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   private boolean consumePower(float paramFloat) {
/*  54 */     if (getPowerManager().consumePowerInstantly(paramFloat)) {
/*  55 */       return true;
/*     */     }
/*  57 */     if (getSegmentController().getDockingController().a() != null)
/*     */     {
/*     */       SegmentController localSegmentController;
/*  59 */       if ((((
/*  59 */         localSegmentController = getSegmentController().getDockingController().a().to.a().a()) instanceof ld)) && 
/*  59 */         ((((ld)localSegmentController).a() instanceof PowerManagerInterface)))
/*     */       {
/*  61 */         return ((PowerManagerInterface)((ld)localSegmentController).a())
/*  61 */           .getPowerAddOn().consumePowerInstantly(paramFloat);
/*     */       }
/*     */     }
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers() {
/*  68 */     return this.weaponManagers;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  73 */     return new WeaponCollectionManager(paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  81 */     if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  82 */       if (debug) {
/*  83 */         System.err.println("NOT ACTIVE");
/*     */       }
/*  85 */       return;
/*     */     }
/*  87 */     if (getCollectionManagers().isEmpty()) {
/*  88 */       if (debug) {
/*  89 */         System.err.println("NO WEAPONS");
/*     */       }
/*     */ 
/*  92 */       return;
/*     */     }
/*  94 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  95 */       if (debug) {
/*  96 */         System.err.println("NO SLOT");
/*     */       }
/*  98 */       return;
/*     */     }
/* 100 */     if ((getPowerManager().getPower() <= 0.5D) && (clientIsOwnShip()))
/*     */     {
/* 102 */       ((ct)getState()).a().b("WARNING!\n \nNo Power Supply \nfor weapon systems");
/*     */     }
/* 104 */     if (debug) {
/* 105 */       System.err.println("FIREING CONTROLLERS: " + getState() + ", " + this.weaponManagers.size() + " FROM: " + this.controlledFrom);
/*     */     }
/* 107 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/*     */     {
/*     */       WeaponCollectionManager localWeaponCollectionManager;
/* 109 */       int j = (
/* 109 */         localWeaponCollectionManager = (WeaponCollectionManager)getCollectionManagers().get(i))
/* 109 */         .getControllerElement().a(this.controlledFrom);
/* 110 */       if (debug) {
/* 111 */         System.err.println("SELECTED:: " + j + " " + getState());
/*     */       }
/* 113 */       if (j != 0)
/*     */       {
/* 115 */         j = this.controlledFromOrig.equals(this.controlledFrom) | 
/* 115 */           getControlElementMap().isControlling(this.controlledFromOrig, localWeaponCollectionManager.getControllerPos(), this.controllerId);
/* 116 */         if (debug) {
/* 117 */           System.err.println("Controlling " + j + " " + getState());
/*     */         }
/*     */ 
/* 120 */         if (j != 0)
/*     */         {
/* 122 */           if (this.controlledFromOrig.equals(kd.a)) {
/* 123 */             this.controlledFromOrig.b(paramlA.a.a().cockpit.getVector());
/*     */           }
/* 125 */           if (debug) {
/* 126 */             System.err.println("Controlling " + j + " " + getState() + ": " + localWeaponCollectionManager.getCollection().size());
/*     */           }
/* 128 */           for (j = 0; j < localWeaponCollectionManager.getCollection().size(); j++) {
/* 129 */             WeaponUnit localWeaponUnit = (WeaponUnit)localWeaponCollectionManager.getCollection().get(j);
/* 130 */             if (debug) {
/* 131 */               System.err.println("CAN SHOOT: " + localWeaponUnit.canShoot() + "; " + getPowerManager().getPower());
/*     */             }
/* 133 */             if ((localWeaponUnit.canShoot()) && (consumePower(localWeaponUnit.getPowerConsumed()))) {
/* 134 */               if (debug) {
/* 135 */                 System.err.println("2CAN SHOOT: " + localWeaponUnit.canShoot() + "; " + getPowerManager().getPower() + ": " + getState() + ": " + getSegmentController().getSectorId() + ";; " + getSegmentController().getPhysics());
/*     */               }
/*     */ 
/* 138 */               Object localObject = localWeaponUnit.getOutput();
/*     */ 
/* 140 */               Vector3f localVector3f1 = new Vector3f(((q)localObject).a - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/*     */ 
/* 144 */               if (debug) {
/* 145 */                 System.err.println("WEAPON OUTPUT ON " + getState() + " -> " + localObject);
/*     */               }
/* 147 */               if (getSegmentController().isOnServer())
/* 148 */                 getSegmentController().getWorldTransform().transform(localVector3f1);
/*     */               else {
/* 150 */                 getSegmentController().getWorldTransformClient().transform(localVector3f1);
/*     */               }
/*     */ 
/* 155 */               (
/* 156 */                 localObject = new q(this.controlledFromOrig))
/* 156 */                 .c(kd.a);
/*     */ 
/* 159 */               localObject = getSegmentController().getAbsoluteElementWorldPosition((q)localObject, new Vector3f());
/* 160 */               Vector3f localVector3f2 = new Vector3f(paramlA.a.a());
/* 161 */               PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 162 */               localVector3f2.scale(localWeaponUnit.getDistance());
/* 163 */               localVector3f2.add((Tuple3f)localObject);
/*     */ 
/* 168 */               if ((
/* 168 */                 localObject = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject, localVector3f2, false, getSegmentController(), null, true, null, false))
/* 168 */                 .hasHit())
/* 169 */                 this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject).hitPointWorld, localVector3f1);
/*     */               else {
/* 171 */                 this.shootingDirTemp.sub(localVector3f2, localVector3f1);
/*     */               }
/*     */ 
/* 175 */               this.shootingDirTemp.normalize();
/* 176 */               this.shootingDirTemp.scale(0.01F * localWeaponUnit.getSpeed());
/* 177 */               localWeaponUnit.updateLastShoot();
/*     */ 
/* 179 */               getParticleController().a(getSegmentController(), localVector3f1, this.shootingDirTemp, localWeaponUnit.getDamage(), localWeaponUnit.getDistance());
/*     */ 
/* 181 */               (
/* 182 */                 localObject = new Transform())
/* 182 */                 .setIdentity();
/* 183 */               ((Transform)localObject).origin.set(localVector3f1);
/*     */ 
/* 186 */               getManagerContainer().onAction();
/*     */ 
/* 189 */               if (!getSegmentController().isOnServer()) {
/* 190 */                 xe.a("0022_spaceship user - laser gun single fire small", (Transform)localObject, 0.99F);
/*     */               }
/* 192 */               notifyShooting(localWeaponUnit);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 197 */           if ((localWeaponCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 198 */             ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 203 */     if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/*     */     {
/* 207 */       ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void notifyShooting(WeaponUnit paramWeaponUnit)
/*     */   {
/* 213 */     notifyObservers(paramWeaponUnit, "s");
/*     */   }
/*     */ 
/*     */   public void updateFromNT(NetworkObject paramNetworkObject)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void updateToFullNT(NetworkObject paramNetworkObject)
/*     */   {
/* 225 */     if (getSegmentController().isOnServer())
/*     */     {
/* 236 */       for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++)
/* 237 */         ((WeaponCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/*     */   {
/* 246 */     return receiveDistribution(paramjE);
/*     */   }
/*     */ 
/*     */   public void onActivate(le paramle, boolean paramBoolean)
/*     */   {
/* 252 */     q localq = paramle.a(new q());
/*     */     Iterator localIterator;
/* 253 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/* 254 */       for (localIterator = ((WeaponCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         WeaponUnit localWeaponUnit;
/* 255 */         if ((
/* 255 */           localWeaponUnit = (WeaponUnit)localIterator.next())
/* 255 */           .contains(localq)) {
/* 256 */           localWeaponUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 258 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void drawReloads(yE paramyE, q paramq)
/*     */   {
/*     */     float f;
/* 267 */     for (int i = 0; i < getCollectionManagers().size(); f++)
/* 268 */       if (((WeaponCollectionManager)getCollectionManagers().get(i)).getControllerPos().equals(paramq))
/*     */       {
/* 270 */         for (WeaponUnit localWeaponUnit : ((WeaponCollectionManager)getCollectionManagers().get(i)).getCollection())
/*     */         {
/* 276 */           int j = (int)(System.currentTimeMillis() - 
/* 276 */             localWeaponUnit.getLastShoot());
/* 277 */           f = Math.min(2.0F, j / localWeaponUnit.getRelaodTime());
/*     */ 
/* 279 */           j = 9;
/* 280 */           if (f <= 1.0F) {
/* 281 */             j = (int)FastMath.a(9.0F + f * 8.0F, 9.0F, 17.0F);
/*     */           }
/*     */ 
/* 284 */           paramyE.a_(j);
/* 285 */           paramyE.b();
/*     */         }
/* 287 */         return;
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponElementManager
 * JD-Core Version:    0.6.2
 */