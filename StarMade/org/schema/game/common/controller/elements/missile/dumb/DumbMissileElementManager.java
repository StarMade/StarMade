/*     */ package org.schema.game.common.controller.elements.missile.dumb;
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
/*     */ public class DumbMissileElementManager extends MissileElementManager
/*     */ {
/*     */   private Segment cachedLastWeaponFireHitSegment;
/*  41 */   private Vector3f shootingDirTemp = new Vector3f();
/*  42 */   private q controlledFromOrig = new q();
/*  43 */   private q controlledFrom = new q();
/*     */   private ArrayList missileManagers;
/*     */ 
/*     */   public DumbMissileElementManager(SegmentController paramSegmentController)
/*     */   {
/*  47 */     super((short)38, (short)32, paramSegmentController);
/*  48 */     this.missileManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers()
/*     */   {
/*  53 */     return this.missileManagers;
/*     */   }
/*     */   public MissileController getMissileController() {
/*  56 */     return ((GameServerController)getSegmentController().getState().getController()).a();
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  63 */     return new DumbMissileCollectionManager(paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  68 */     if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  69 */       return;
/*     */     }
/*  71 */     if (getCollectionManagers().isEmpty())
/*     */     {
/*  73 */       return;
/*     */     }
/*  75 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  76 */       return;
/*     */     }
/*     */ 
/*  79 */     for (int i = 0; i < getCollectionManagers().size(); 
/*  81 */       i++)
/*     */     {
/*     */       DumbMissileCollectionManager localDumbMissileCollectionManager;
/*  84 */       int j = 0; if ((
/*  83 */         localDumbMissileCollectionManager = (DumbMissileCollectionManager)getCollectionManagers().get(i))
/*  83 */         .equalsControllerPos(this.controlledFrom))
/*     */       {
/*  88 */         if ((this.controlledFromOrig.equals(this.controlledFrom) | 
/*  86 */           getControlElementMap().isControlling(this.controlledFromOrig, localDumbMissileCollectionManager.getControllerPos(), this.controllerId)))
/*     */         {
/*  90 */           for (j = 0; j < localDumbMissileCollectionManager.getCollection().size(); j++)
/*     */           {
/*     */             MissileUnit localMissileUnit;
/*  92 */             if ((
/*  92 */               localMissileUnit = (MissileUnit)localDumbMissileCollectionManager.getCollection().get(j))
/*  92 */               .canShoot()) {
/*  93 */               Object localObject1 = localMissileUnit.getOutput();
/*     */ 
/*  95 */               localObject1 = new Vector3f(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*     */ 
/* 101 */               getWorldTransform().transform((Vector3f)localObject1);
/*     */ 
/* 103 */               (
/* 104 */                 localObject2 = new q(this.controlledFromOrig))
/* 104 */                 .c(kd.a);
/* 105 */               Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((q)localObject2, new Vector3f());
/* 106 */               Vector3f localVector3f = new Vector3f(paramlA.a.a());
/* 107 */               PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 108 */               localVector3f.scale(localMissileUnit.getDistance());
/* 109 */               localVector3f.add((Tuple3f)localObject2);
/*     */ 
/* 114 */               if ((
/* 114 */                 localObject2 = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false))
/* 114 */                 .hasHit()) {
/* 115 */                 if ((localObject2 instanceof CubeRayCastResult)) {
/* 116 */                   this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
/*     */                 }
/*     */ 
/* 119 */                 this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
/*     */               } else {
/* 121 */                 this.cachedLastWeaponFireHitSegment = null;
/* 122 */                 this.shootingDirTemp.set(paramlA.a.a());
/*     */               }
/*     */ 
/* 128 */               this.shootingDirTemp.normalize();
/* 129 */               this.shootingDirTemp.scale(localMissileUnit.getSpeed());
/* 130 */               localMissileUnit.updateLastShoot();
/*     */ 
/* 132 */               if (getSegmentController().isOnServer()) {
/* 133 */                 (
/* 134 */                   localObject2 = new Transform())
/* 134 */                   .setIdentity();
/* 135 */                 ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/*     */ 
/* 137 */                 getMissileController().addDumbMissile(getSegmentController(), (Transform)localObject2, new Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
/*     */               }
/*     */ 
/* 140 */               (
/* 141 */                 localObject2 = new Transform())
/* 141 */                 .setIdentity();
/* 142 */               ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/*     */ 
/* 144 */               if (!getSegmentController().isOnServer()) {
/* 145 */                 xe.a("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
/* 146 */                 notifyShooting(localMissileUnit);
/*     */               }
/* 148 */               getManagerContainer().onAction();
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 153 */           if ((localDumbMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 154 */             ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 159 */     if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/*     */     {
/* 163 */       ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void notifyShooting(MissileUnit paramMissileUnit) {
/* 168 */     notifyObservers(paramMissileUnit, "s");
/*     */   }
/*     */ 
/*     */   public void updateFromNT(NetworkObject paramNetworkObject)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void updateToFullNT(NetworkObject paramNetworkObject)
/*     */   {
/* 179 */     if (getSegmentController().isOnServer())
/*     */     {
/* 190 */       for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++)
/* 191 */         ((DumbMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/*     */   {
/* 200 */     return receiveDistribution(paramjE);
/*     */   }
/*     */ 
/*     */   public void onActivate(le paramle, boolean paramBoolean)
/*     */   {
/* 205 */     q localq = paramle.a(new q());
/*     */     Iterator localIterator;
/* 206 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/* 207 */       for (localIterator = ((DumbMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         MissileUnit localMissileUnit;
/* 208 */         if ((
/* 208 */           localMissileUnit = (MissileUnit)localIterator.next())
/* 208 */           .contains(localq)) {
/* 209 */           localMissileUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 211 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager
 * JD-Core Version:    0.6.2
 */