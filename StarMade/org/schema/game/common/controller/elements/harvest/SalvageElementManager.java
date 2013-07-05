/*     */ package org.schema.game.common.controller.elements.harvest;
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
/*     */ public class SalvageElementManager extends UsableControllableElementManager
/*     */   implements BlockActivationListenerInterface
/*     */ {
/*  21 */   private Vector3f shootingDirTemp = new Vector3f();
/*  22 */   private q controlledFromOrig = new q();
/*  23 */   private q controlledFrom = new q();
/*     */   private ArrayList salvageBeamCollectionManagers;
/*     */ 
/*     */   public SalvageElementManager(SegmentController paramSegmentController)
/*     */   {
/*  28 */     super((short)4, (short)24, paramSegmentController);
/*  29 */     this.salvageBeamCollectionManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers() {
/*  33 */     return this.salvageBeamCollectionManagers;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  39 */     return new SalvageBeamCollectionManager(paramle, getSegmentController());
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
/*  53 */     if (this.salvageBeamCollectionManagers.isEmpty())
/*     */     {
/*  55 */       return;
/*     */     }
/*     */ 
/*  58 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  59 */       return;
/*     */     }
/*     */ 
/*  62 */     int i = this.salvageBeamCollectionManagers.size();
/*     */     SalvageBeamCollectionManager localSalvageBeamCollectionManager;
/*     */     int k;
/*  63 */     for (int j = 0; j < i; j++)
/*     */     {
/*  66 */       k = 0; if ((
/*  65 */         localSalvageBeamCollectionManager = (SalvageBeamCollectionManager)this.salvageBeamCollectionManagers.get(j))
/*  65 */         .equalsControllerPos(this.controlledFrom))
/*     */       {
/*  69 */         if ((this.controlledFromOrig.equals(this.controlledFrom) | 
/*  68 */           getControlElementMap().isControlling(this.controlledFromOrig, localSalvageBeamCollectionManager.getControllerPos(), this.controllerId)))
/*     */         {
/*  71 */           for (k = 0; k < localSalvageBeamCollectionManager.getCollection().size(); ) {
/*  72 */             SalvageUnit localSalvageUnit = (SalvageUnit)localSalvageBeamCollectionManager.getCollection().get(k);
/*  73 */             Vector3f localVector3f = new Vector3f();
/*     */ 
/*  75 */             Object localObject = localSalvageUnit.getOutput();
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
/*  88 */             localSalvageBeamCollectionManager.getHandler().addBeam(localSalvageUnit.getSignificator(), localVector3f, (Vector3f)localObject, paramlA.a, localSalvageUnit.getSalvageSpeedFactor());
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
/* 119 */     for (int i = 0; i < this.salvageBeamCollectionManagers.size(); i++)
/* 120 */       for (localIterator = ((SalvageBeamCollectionManager)this.salvageBeamCollectionManagers.get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         SalvageUnit localSalvageUnit;
/* 121 */         if ((
/* 121 */           localSalvageUnit = (SalvageUnit)localIterator.next())
/* 121 */           .contains(localq)) {
/* 122 */           localSalvageUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 124 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageElementManager
 * JD-Core Version:    0.6.2
 */