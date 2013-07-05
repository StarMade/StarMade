/*     */ package org.schema.game.common.controller.elements.powerbeamdrain;
/*     */ 
/*     */ import ct;
/*     */ import dj;
/*     */ import do;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ 
/*     */ public class PowerDrainElementManager extends UsableControllableElementManager
/*     */   implements BlockActivationListenerInterface
/*     */ {
/*  21 */   private Vector3f shootingDirTemp = new Vector3f();
/*  22 */   private q controlledFromOrig = new q();
/*  23 */   private q controlledFrom = new q();
/*     */   private ArrayList powerDrainBeamCollectionManagers;
/*     */ 
/*     */   public PowerDrainElementManager(SegmentController paramSegmentController)
/*     */   {
/*  28 */     super((short)332, (short)333, paramSegmentController);
/*  29 */     this.powerDrainBeamCollectionManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers() {
/*  33 */     return this.powerDrainBeamCollectionManagers;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  39 */     return new PowerDrainBeamCollectionManager(paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  44 */     if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  45 */       return;
/*     */     }
/*  47 */     this.shootingDirTemp.set(paramlA.a.a());
/*  48 */     this.shootingDirTemp.normalize();
/*  49 */     this.shootingDirTemp.scale(100.0F);
/*     */ 
/*  53 */     if (this.powerDrainBeamCollectionManagers.isEmpty())
/*     */     {
/*  55 */       return;
/*     */     }
/*     */ 
/*  58 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  59 */       return;
/*     */     }
/*     */ 
/*  62 */     int i = this.powerDrainBeamCollectionManagers.size();
/*     */     PowerDrainBeamCollectionManager localPowerDrainBeamCollectionManager;
/*     */     int k;
/*  63 */     for (int j = 0; j < i; j++)
/*     */     {
/*  66 */       k = 0; if ((
/*  65 */         localPowerDrainBeamCollectionManager = (PowerDrainBeamCollectionManager)this.powerDrainBeamCollectionManagers.get(j))
/*  65 */         .equalsControllerPos(this.controlledFrom))
/*     */       {
/*  69 */         if ((this.controlledFromOrig.equals(this.controlledFrom) | 
/*  68 */           getControlElementMap().isControlling(this.controlledFromOrig, localPowerDrainBeamCollectionManager.getControllerPos(), this.controllerId)))
/*     */         {
/*  71 */           for (k = 0; k < localPowerDrainBeamCollectionManager.getCollection().size(); ) {
/*  72 */             PowerDrainUnit localPowerDrainUnit = (PowerDrainUnit)localPowerDrainBeamCollectionManager.getCollection().get(k);
/*  73 */             Vector3f localVector3f = new Vector3f();
/*     */ 
/*  75 */             Object localObject = localPowerDrainUnit.getOutput();
/*     */ 
/*  77 */             localObject = new q(((q)localObject).a - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/*     */ 
/*  82 */             getSegmentController().getAbsoluteElementWorldPosition((q)localObject, localVector3f);
/*     */ 
/*  84 */             (
/*  85 */               localObject = new Vector3f())
/*  85 */               .set(localVector3f);
/*     */ 
/*  87 */             ((Vector3f)localObject).add(this.shootingDirTemp);
/*  88 */             localPowerDrainBeamCollectionManager.getHandler().addBeam(localPowerDrainUnit.getSignificator(), localVector3f, (Vector3f)localObject, paramlA.a, localPowerDrainUnit.getDrainSpeedFactor());
/*  89 */             getManagerContainer().onAction();
/*  90 */             k++;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onControllerBlockAdd()
/*     */   {
/* 102 */     if (!getSegmentController().isOnServer())
/* 103 */       ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(true), null);
/*     */   }
/*     */ 
/*     */   public void onControllerBlockRemove()
/*     */   {
/* 112 */     if (!getSegmentController().isOnServer())
/* 113 */       ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(false), null);
/*     */   }
/*     */ 
/*     */   public void onActivate(le paramle, boolean paramBoolean)
/*     */   {
/* 118 */     q localq = paramle.a(new q());
/*     */     Iterator localIterator;
/* 119 */     for (int i = 0; i < this.powerDrainBeamCollectionManagers.size(); i++)
/* 120 */       for (localIterator = ((PowerDrainBeamCollectionManager)this.powerDrainBeamCollectionManagers.get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         PowerDrainUnit localPowerDrainUnit;
/* 121 */         if ((
/* 121 */           localPowerDrainUnit = (PowerDrainUnit)localIterator.next())
/* 121 */           .contains(localq)) {
/* 122 */           localPowerDrainUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 124 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamdrain.PowerDrainElementManager
 * JD-Core Version:    0.6.2
 */