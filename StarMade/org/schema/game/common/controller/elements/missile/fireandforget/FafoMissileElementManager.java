/*     */ package org.schema.game.common.controller.elements.missile.fireandforget;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import ct;
/*     */ import jE;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import mF;
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
/*     */ public class FafoMissileElementManager extends MissileElementManager
/*     */ {
/*     */   private Segment cachedLastWeaponFireHitSegment;
/*  38 */   private Vector3f shootingDirTemp = new Vector3f();
/*  39 */   private q controlledFromOrig = new q();
/*  40 */   private q controlledFrom = new q();
/*     */   private ArrayList missileManagers;
/*     */ 
/*     */   public FafoMissileElementManager(SegmentController paramSegmentController)
/*     */   {
/*  44 */     super((short)54, (short)48, paramSegmentController);
/*  45 */     this.missileManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers()
/*     */   {
/*  50 */     return this.missileManagers;
/*     */   }
/*     */   private MissileController getMissileController() {
/*  53 */     return ((GameServerController)getSegmentController().getState().getController()).a();
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  60 */     return new FafoMissileCollectionManager(paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  65 */     if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  66 */       return;
/*     */     }
/*  68 */     mF localmF = paramlA.a.a();
/*     */ 
/*  70 */     if (getCollectionManagers().isEmpty())
/*     */     {
/*  72 */       return;
/*     */     }
/*  74 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  75 */       return;
/*     */     }
/*     */ 
/*  78 */     for (int i = 0; i < getCollectionManagers().size(); 
/*  80 */       i++)
/*     */     {
/*     */       FafoMissileCollectionManager localFafoMissileCollectionManager;
/*  83 */       int j = 0; if ((
/*  82 */         localFafoMissileCollectionManager = (FafoMissileCollectionManager)getCollectionManagers().get(i))
/*  82 */         .equalsControllerPos(this.controlledFrom))
/*     */       {
/*  87 */         if ((this.controlledFromOrig.equals(this.controlledFrom) | 
/*  85 */           getControlElementMap().isControlling(this.controlledFromOrig, localFafoMissileCollectionManager.getControllerPos(), this.controllerId)))
/*     */         {
/*  89 */           for (j = 0; j < localFafoMissileCollectionManager.getCollection().size(); j++)
/*     */           {
/*     */             MissileUnit localMissileUnit;
/*  91 */             if ((
/*  91 */               localMissileUnit = (MissileUnit)localFafoMissileCollectionManager.getCollection().get(j))
/*  91 */               .canShoot()) {
/*  92 */               Object localObject1 = localMissileUnit.getOutput();
/*     */ 
/*  94 */               localObject1 = new Vector3f(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*     */ 
/* 100 */               getWorldTransform().transform((Vector3f)localObject1);
/*     */ 
/* 102 */               (
/* 103 */                 localObject2 = new q(this.controlledFromOrig))
/* 103 */                 .c(kd.a);
/* 104 */               Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((q)localObject2, new Vector3f());
/* 105 */               Vector3f localVector3f = new Vector3f(paramlA.a.a());
/* 106 */               PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 107 */               localVector3f.scale(localMissileUnit.getDistance());
/* 108 */               localVector3f.add((Tuple3f)localObject2);
/*     */ 
/* 113 */               if ((
/* 113 */                 localObject2 = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false))
/* 113 */                 .hasHit()) {
/* 114 */                 if ((localObject2 instanceof CubeRayCastResult)) {
/* 115 */                   this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
/*     */                 }
/*     */ 
/* 118 */                 this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
/*     */               } else {
/* 120 */                 this.cachedLastWeaponFireHitSegment = null;
/* 121 */                 this.shootingDirTemp.set(paramlA.a.a());
/*     */               }
/*     */ 
/* 127 */               this.shootingDirTemp.normalize();
/* 128 */               localMissileUnit.updateLastShoot();
/*     */ 
/* 130 */               if (getSegmentController().isOnServer()) {
/* 131 */                 (
/* 132 */                   localObject2 = new Transform())
/* 132 */                   .setIdentity();
/* 133 */                 ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 134 */                 getMissileController().addFafoMissile(getSegmentController(), (Transform)localObject2, new Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance(), localmF);
/*     */               }
/*     */ 
/* 137 */               (
/* 138 */                 localObject2 = new Transform())
/* 138 */                 .setIdentity();
/* 139 */               ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/*     */ 
/* 141 */               if (!getSegmentController().isOnServer()) {
/* 142 */                 xe.a("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
/* 143 */                 notifyObservers(localMissileUnit, "s");
/*     */               }
/* 145 */               getManagerContainer().onAction();
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 150 */           if ((localFafoMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 151 */             ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 156 */     if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/*     */     {
/* 160 */       ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateFromNT(NetworkObject paramNetworkObject)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void updateToFullNT(NetworkObject paramNetworkObject)
/*     */   {
/* 170 */     if (getSegmentController().isOnServer())
/*     */     {
/* 181 */       for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++)
/* 182 */         ((FafoMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/*     */   {
/* 191 */     return receiveDistribution(paramjE);
/*     */   }
/*     */ 
/*     */   public void onActivate(le paramle, boolean paramBoolean)
/*     */   {
/* 196 */     q localq = paramle.a(new q());
/*     */     Iterator localIterator;
/* 197 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/* 198 */       for (localIterator = ((FafoMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         MissileUnit localMissileUnit;
/* 199 */         if ((
/* 199 */           localMissileUnit = (MissileUnit)localIterator.next())
/* 199 */           .contains(localq)) {
/* 200 */           localMissileUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 202 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager
 * JD-Core Version:    0.6.2
 */