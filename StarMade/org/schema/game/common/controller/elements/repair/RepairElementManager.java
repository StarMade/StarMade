/*     */ package org.schema.game.common.controller.elements.repair;
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
/*     */ public class RepairElementManager extends UsableControllableElementManager
/*     */   implements BlockActivationListenerInterface
/*     */ {
/*  22 */   private Vector3f shootingDirTemp = new Vector3f();
/*  23 */   private q controlledFromOrig = new q();
/*  24 */   private q controlledFrom = new q();
/*     */   private ArrayList repairBeamManagers;
/*     */ 
/*     */   public RepairElementManager(SegmentController paramSegmentController)
/*     */   {
/*  29 */     super((short)39, (short)30, paramSegmentController);
/*  30 */     this.repairBeamManagers = new ArrayList();
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers()
/*     */   {
/*  35 */     return this.repairBeamManagers;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  41 */     return new RepairBeamCollectionManager(paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  47 */     if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  48 */       return;
/*     */     }
/*  50 */     this.shootingDirTemp.set(paramlA.a.a());
/*  51 */     this.shootingDirTemp.scale(100.0F);
/*     */ 
/*  55 */     if (getCollectionManagers().isEmpty())
/*     */     {
/*  57 */       return;
/*     */     }
/*     */ 
/*  60 */     if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  61 */       return;
/*     */     }
/*     */ 
/*  65 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/*     */     {
/*     */       RepairBeamCollectionManager localRepairBeamCollectionManager;
/*  68 */       int j = 0; if ((
/*  67 */         localRepairBeamCollectionManager = (RepairBeamCollectionManager)getCollectionManagers().get(i))
/*  67 */         .equalsControllerPos(this.controlledFrom))
/*     */       {
/*  71 */         if ((this.controlledFromOrig.equals(this.controlledFrom) | 
/*  70 */           getControlElementMap().isControlling(this.controlledFromOrig, localRepairBeamCollectionManager.getControllerPos(), this.controllerId)))
/*     */         {
/*  72 */           for (j = 0; j < localRepairBeamCollectionManager.getCollection().size(); j++) {
/*  73 */             RepairUnit localRepairUnit = (RepairUnit)localRepairBeamCollectionManager.getCollection().get(j);
/*  74 */             Vector3f localVector3f = new Vector3f();
/*     */ 
/*  76 */             Object localObject = localRepairUnit.getOutput();
/*     */ 
/*  78 */             localObject = new q(((q)localObject).a - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/*     */ 
/*  83 */             getSegmentController().getAbsoluteElementWorldPosition((q)localObject, localVector3f);
/*     */ 
/*  85 */             (
/*  86 */               localObject = new Vector3f())
/*  86 */               .set(localVector3f);
/*     */ 
/*  88 */             ((Vector3f)localObject).add(this.shootingDirTemp);
/*     */ 
/*  90 */             localRepairBeamCollectionManager.getHandler().addBeam(localRepairUnit.getSignificator(), localVector3f, (Vector3f)localObject, paramlA.a, localRepairUnit.getRepairSpeedFactor());
/*  91 */             getManagerContainer().onAction();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onControllerBlockAdd()
/*     */   {
/* 105 */     if (!getSegmentController().isOnServer())
/* 106 */       ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(true), null);
/*     */   }
/*     */ 
/*     */   public void onControllerBlockRemove()
/*     */   {
/* 115 */     if (!getSegmentController().isOnServer())
/* 116 */       ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(false), null);
/*     */   }
/*     */ 
/*     */   public void onActivate(le paramle, boolean paramBoolean)
/*     */   {
/* 121 */     q localq = paramle.a(new q());
/*     */     Iterator localIterator;
/* 122 */     for (int i = 0; i < getCollectionManagers().size(); i++)
/* 123 */       for (localIterator = ((RepairBeamCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         RepairUnit localRepairUnit;
/* 124 */         if ((
/* 124 */           localRepairUnit = (RepairUnit)localIterator.next())
/* 124 */           .contains(localq)) {
/* 125 */           localRepairUnit.setMainPiece(paramle, paramBoolean);
/*     */ 
/* 127 */           return;
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairElementManager
 * JD-Core Version:    0.6.2
 */