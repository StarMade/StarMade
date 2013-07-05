/*     */ package org.schema.game.common.controller.elements.missile.heatseeking;
/*     */ 
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
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.controller.elements.missile.MissileController;
/*     */ import org.schema.game.common.controller.elements.missile.MissileElementManager;
/*     */ import org.schema.game.common.controller.elements.missile.MissileUnit;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkEntity;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ import x;
/*     */ import xe;
/*     */ 
/*     */ public class HeatMissileElementManager extends MissileElementManager
/*     */ {
/*     */   private Segment cachedLastWeaponFireHitSegment;
/*  37 */   private Vector3f shootingDirTemp = new Vector3f();
/*  38 */   private q controlledFromOrig = new q();
/*  39 */   private q controlledFrom = new q();
/*     */   private ArrayList missileManagers;
/*     */ 
/*     */   public HeatMissileElementManager(SegmentController paramSegmentController)
/*     */   {
/*  43 */     super((short)46, (short)40, paramSegmentController);
/*  44 */     this.missileManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers()
/*     */   {
/*  49 */     return this.missileManagers;
/*     */   }
/*     */   public MissileController getMissileController() {
/*  52 */     return ((GameServerController)getSegmentController().getState().getController()).a();
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  59 */     return new HeatMissileCollectionManager(paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  64 */     if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  65 */       return;
/*     */     }
/*  67 */     if (getCollectionManagers().isEmpty())
/*     */     {
/*  69 */       return;
/*     */     }
/*  71 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  72 */       return;
/*     */     }
/*     */ 
/*  75 */     for (int i = 0; i < getCollectionManagers().size(); 
/*  77 */       i++)
/*     */     {
/*     */       HeatMissileCollectionManager localHeatMissileCollectionManager;
/*  80 */       int j = 0; if ((
/*  79 */         localHeatMissileCollectionManager = (HeatMissileCollectionManager)getCollectionManagers().get(i))
/*  79 */         .equalsControllerPos(this.controlledFrom))
/*     */       {
/*  84 */         if ((this.controlledFromOrig.equals(this.controlledFrom) | 
/*  82 */           getControlElementMap().isControlling(this.controlledFromOrig, localHeatMissileCollectionManager.getControllerPos(), this.controllerId)))
/*     */         {
/*  86 */           for (j = 0; j < localHeatMissileCollectionManager.getCollection().size(); j++)
/*     */           {
/*     */             MissileUnit localMissileUnit;
/*  88 */             if ((
/*  88 */               localMissileUnit = (MissileUnit)localHeatMissileCollectionManager.getCollection().get(j))
/*  88 */               .canShoot()) {
/*  89 */               Object localObject1 = localMissileUnit.getOutput();
/*     */ 
/*  91 */               localObject1 = new Vector3f(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*     */ 
/*  97 */               getWorldTransform().transform((Vector3f)localObject1);
/*     */ 
/*  99 */               (
/* 100 */                 localObject2 = new q(this.controlledFromOrig))
/* 100 */                 .c(kd.a);
/* 101 */               Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((q)localObject2, new Vector3f());
/* 102 */               Vector3f localVector3f = new Vector3f(paramlA.a.a());
/* 103 */               PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 104 */               localVector3f.scale(localMissileUnit.getDistance());
/* 105 */               localVector3f.add((Tuple3f)localObject2);
/*     */ 
/* 110 */               if ((
/* 110 */                 localObject2 = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false))
/* 110 */                 .hasHit()) {
/* 111 */                 if ((localObject2 instanceof CubeRayCastResult)) {
/* 112 */                   this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
/*     */                 }
/*     */ 
/* 115 */                 this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
/*     */               } else {
/* 117 */                 this.cachedLastWeaponFireHitSegment = null;
/* 118 */                 this.shootingDirTemp.set(paramlA.a.a());
/*     */               }
/*     */ 
/* 124 */               this.shootingDirTemp.normalize();
/* 125 */               this.shootingDirTemp.scale(localMissileUnit.getSpeed());
/* 126 */               localMissileUnit.updateLastShoot();
/*     */ 
/* 128 */               if (getSegmentController().isOnServer()) {
/* 129 */                 System.err.println("[SERVER] spawing heat missile " + localObject1);
/* 130 */                 (
/* 131 */                   localObject2 = new Transform())
/* 131 */                   .setIdentity();
/* 132 */                 ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 133 */                 getMissileController().addHeatMissile(getSegmentController(), (Transform)localObject2, new Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
/*     */               }
/*     */ 
/* 136 */               (
/* 137 */                 localObject2 = new Transform())
/* 137 */                 .setIdentity();
/* 138 */               ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/*     */ 
/* 140 */               if (!getSegmentController().isOnServer()) {
/* 141 */                 xe.a("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
/* 142 */                 notifyObservers(localMissileUnit, "s");
/*     */               }
/* 144 */               getManagerContainer().onAction();
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 149 */           if ((localHeatMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 150 */             ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 155 */     if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/*     */     {
/* 159 */       ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void notifyShooting(MissileUnit paramMissileUnit) {
/* 164 */     notifyObservers(paramMissileUnit, "s");
/*     */   }
/*     */ 
/*     */   public void updateFromNT(NetworkObject paramNetworkObject)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void updateToFullNT(NetworkObject paramNetworkObject) {
/* 172 */     if (getSegmentController().isOnServer())
/*     */     {
/* 183 */       for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++)
/* 184 */         ((HeatMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/*     */   {
/* 193 */     return receiveDistribution(paramjE);
/*     */   }
/*     */ 
/*     */   public void onActivate(le paramle, boolean paramBoolean)
/*     */   {
/* 199 */     q localq = paramle.a(new q());
/*     */     Iterator localIterator;
/* 200 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/* 201 */       for (localIterator = ((HeatMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         MissileUnit localMissileUnit;
/* 202 */         if ((
/* 202 */           localMissileUnit = (MissileUnit)localIterator.next())
/* 202 */           .contains(localq)) {
/* 203 */           localMissileUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 205 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager
 * JD-Core Version:    0.6.2
 */