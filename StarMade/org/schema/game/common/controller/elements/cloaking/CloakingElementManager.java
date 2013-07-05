/*     */ package org.schema.game.common.controller.elements.cloaking;
/*     */ 
/*     */ import cw;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerActivityInterface;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*     */ import org.schema.game.common.controller.elements.UpdatableCollectionManager;
/*     */ import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ import wm;
/*     */ import wp;
/*     */ import xq;
/*     */ 
/*     */ public class CloakingElementManager extends UsableControllableSingleElementManager
/*     */   implements ManagerActivityInterface, UpdatableCollectionManager
/*     */ {
/*     */   private CloakingCollectionManager cloakManager;
/*  29 */   private boolean cloaked = false;
/*     */   private long cloakStartTime;
/*  32 */   private q controlledFromOrig = new q();
/*     */ 
/*  34 */   private q controlledFrom = new q();
/*     */ 
/*  36 */   private float POWER_CONSUME_MULT = 2000.0F;
/*     */ 
/*     */   public CloakingElementManager(SegmentController paramSegmentController) {
/*  39 */     super(new CloakingCollectionManager(paramSegmentController), paramSegmentController);
/*  40 */     this.cloakManager = ((CloakingCollectionManager)getCollection());
/*     */   }
/*     */ 
/*     */   public float getActualCloak()
/*     */   {
/*  45 */     return this.cloakManager.getTotalCloak();
/*     */   }
/*     */ 
/*     */   public long getCloakStartTime()
/*     */   {
/*  51 */     return this.cloakStartTime;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  56 */     return new CloakingCollectionManager(getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  70 */     if (getSegmentController().isOnServer()) {
/*  71 */       if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  72 */         return;
/*     */       }
/*  74 */       if (System.currentTimeMillis() - getCloakStartTime() < 600L) {
/*  75 */         return;
/*     */       }
/*  77 */       if ((!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject)) || (this.cloakManager.getCollection().isEmpty()))
/*     */       {
/*  79 */         return;
/*     */       }
/*  81 */       if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  82 */         return;
/*     */       }
/*  84 */       getActualCloak();
/*     */       try {
/*  86 */         if (this.controlledFrom.equals(((CloakingUnit)this.cloakManager.getCollection().get(0)).getId().a(new q()))) {
/*  87 */           if (!isCloaked()) {
/*  88 */             System.err.println("CLOAKING STARTED");
/*  89 */             setCloakStartTime(System.currentTimeMillis());
/*  90 */             setCloaked(true); return;
/*     */           }
/*  92 */           stopCloak();
/*     */         }
/*     */ 
/*     */         return;
/*     */       }
/*     */       catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
/*     */       {
/*  99 */         localCannotImmediateRequestOnClientException.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isCloaked()
/*     */   {
/* 108 */     if (getSegmentController().isOnServer()) {
/* 109 */       return this.cloaked;
/*     */     }
/* 111 */     return ((kd)getSegmentController()).a();
/*     */   }
/*     */ 
/*     */   public void onControllerChange()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onHit()
/*     */   {
/* 120 */     stopCloak();
/*     */   }
/*     */ 
/*     */   public void setCloaked(boolean paramBoolean)
/*     */   {
/* 127 */     this.cloaked = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setCloakStartTime(long paramLong)
/*     */   {
/* 135 */     this.cloakStartTime = paramLong;
/*     */   }
/*     */ 
/*     */   public void stopCloak()
/*     */   {
/* 140 */     if (isCloaked()) {
/* 141 */       setCloaked(false);
/* 142 */       setCloakStartTime(System.currentTimeMillis());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq)
/*     */   {
/* 148 */     PowerAddOn localPowerAddOn = ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/* 149 */     paramxq = getSegmentController().getTotalElements() / 20.0F * paramxq.a() * this.POWER_CONSUME_MULT;
/*     */ 
/* 151 */     if ((isCloaked()) && (!localPowerAddOn.consumePowerInstantly(paramxq)) && 
/* 152 */       (getSegmentController().isOnServer())) {
/* 153 */       stopCloak();
/*     */     }
/*     */ 
/* 157 */     if ((isCloaked()) && (((cw)getSegmentController()).a().isEmpty()) && (!((wp)getSegmentController()).a().a()) && 
/* 158 */       (getSegmentController().isOnServer()))
/* 159 */       stopCloak();
/*     */   }
/*     */ 
/*     */   public boolean isActive()
/*     */   {
/* 166 */     return isCloaked();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingElementManager
 * JD-Core Version:    0.6.2
 */