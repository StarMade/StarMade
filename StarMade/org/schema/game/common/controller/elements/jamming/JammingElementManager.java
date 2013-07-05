/*     */ package org.schema.game.common.controller.elements.jamming;
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
/*     */ public class JammingElementManager extends UsableControllableSingleElementManager
/*     */   implements ManagerActivityInterface, UpdatableCollectionManager
/*     */ {
/*     */   private JammingCollectionManager jamManager;
/*  32 */   private boolean jamming = false;
/*     */   private long jamStartTime;
/*  35 */   private q controlledFromOrig = new q();
/*     */ 
/*  37 */   private q controlledFrom = new q();
/*     */ 
/*  40 */   private float POWER_CONSUME_MULT = 1000.0F;
/*     */ 
/*     */   public JammingElementManager(JammingCollectionManager paramJammingCollectionManager, SegmentController paramSegmentController)
/*     */   {
/*  44 */     super(paramJammingCollectionManager, paramSegmentController);
/*  45 */     this.jamManager = paramJammingCollectionManager;
/*     */   }
/*     */ 
/*     */   public float getActualJam()
/*     */   {
/*  50 */     return this.jamManager.getTotalJam();
/*     */   }
/*     */ 
/*     */   public long getJamStartTime()
/*     */   {
/*  57 */     return this.jamStartTime;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  62 */     return new JammingCollectionManager(getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  77 */     if (getSegmentController().isOnServer()) {
/*  78 */       if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  79 */         return;
/*     */       }
/*  81 */       if (System.currentTimeMillis() - this.jamStartTime < 600L) {
/*  82 */         return;
/*     */       }
/*  84 */       if ((!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject)) || (this.jamManager.getCollection().isEmpty()))
/*     */       {
/*  86 */         return;
/*     */       }
/*  88 */       if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  89 */         return;
/*     */       }
/*  91 */       getActualJam();
/*     */       try
/*     */       {
/*  94 */         if (this.controlledFrom.equals(((JammingUnit)this.jamManager.getCollection().get(0)).getId().a(new q()))) {
/*  95 */           if (!isJamming()) {
/*  96 */             System.err.println("[JAMMING] NOW JAMMING");
/*  97 */             setJamStartTime(System.currentTimeMillis());
/*  98 */             setJamming(true); return;
/*     */           }
/* 100 */           stopJamming();
/*     */         }
/*     */         return;
/*     */       }
/*     */       catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {
/* 105 */         localCannotImmediateRequestOnClientException.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isJamming()
/*     */   {
/* 114 */     if (getSegmentController().isOnServer()) {
/* 115 */       return this.jamming;
/*     */     }
/* 117 */     return ((kd)getSegmentController()).c();
/*     */   }
/*     */ 
/*     */   public void onControllerChange()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onHit()
/*     */   {
/* 127 */     stopJamming();
/*     */   }
/*     */ 
/*     */   public void setJamming(boolean paramBoolean)
/*     */   {
/* 134 */     this.jamming = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setJamStartTime(long paramLong)
/*     */   {
/* 142 */     this.jamStartTime = paramLong;
/*     */   }
/*     */ 
/*     */   public void stopJamming()
/*     */   {
/* 149 */     if (isJamming()) {
/* 150 */       System.err.println("Stopped jamming -> reloading");
/* 151 */       setJamming(false);
/* 152 */       setJamStartTime(System.currentTimeMillis());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq)
/*     */   {
/* 158 */     PowerAddOn localPowerAddOn = ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/* 159 */     paramxq = getSegmentController().getTotalElements() / 20.0F * paramxq.a() * this.POWER_CONSUME_MULT;
/*     */ 
/* 161 */     if ((isJamming()) && (!localPowerAddOn.consumePowerInstantly(paramxq)) && 
/* 162 */       (getSegmentController().isOnServer())) {
/* 163 */       stopJamming();
/*     */     }
/*     */ 
/* 167 */     if ((isJamming()) && (((cw)getSegmentController()).a().isEmpty()) && (!((wp)getSegmentController()).a().a()) && 
/* 168 */       (getSegmentController().isOnServer()))
/* 169 */       stopJamming();
/*     */   }
/*     */ 
/*     */   public boolean isActive()
/*     */   {
/* 176 */     return isJamming();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingElementManager
 * JD-Core Version:    0.6.2
 */