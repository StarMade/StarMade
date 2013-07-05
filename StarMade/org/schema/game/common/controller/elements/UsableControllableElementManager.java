/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import cz;
/*     */ import jE;
/*     */ import jJ;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*     */ import q;
/*     */ 
/*     */ public abstract class UsableControllableElementManager extends UsableElementManager
/*     */ {
/*     */   protected short controllerId;
/*     */   private short controllingId;
/*     */   private ElementInformation controllerInfo;
/*     */   private ElementInformation controllingInfo;
/*     */ 
/*     */   public UsableControllableElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
/*     */   {
/*  28 */     super(paramSegmentController);
/*     */ 
/*  33 */     this.controllerId = paramShort1;
/*  34 */     this.controllingId = paramShort2;
/*     */ 
/*  36 */     this.controllerInfo = ElementKeyMap.getInfo(paramShort1);
/*  37 */     this.controllingInfo = ElementKeyMap.getInfo(paramShort2);
/*     */ 
/*  39 */     assert (this.controllerInfo.getControlling().contains(Short.valueOf(paramShort2))) : (this.controllerInfo.getControlling() + " : " + paramShort2);
/*  40 */     assert (this.controllingInfo.getControlledBy().contains(Short.valueOf(paramShort1))) : (this.controllingInfo.getName() + ": controlled by set " + this.controllingInfo.getControlledBy() + " does not contain " + ElementKeyMap.getInfo(paramShort1) + "(" + paramShort1 + ")");
/*     */   }
/*     */   public void addControllerBlockIfNecessary(q paramq1, q paramq2, short arg3) {
/*  43 */     if (??? == this.controllingId)
/*  44 */       synchronized (getCollectionManagers()) {
/*  45 */         for (Iterator localIterator = getCollectionManagers().iterator(); localIterator.hasNext(); )
/*     */         {
/*     */           ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
/*  46 */           if ((
/*  46 */             localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next())
/*  46 */             .equalsControllerPos(paramq1)) {
/*  47 */             localControlBlockElementCollectionManager.addModded(paramq2);
/*  48 */             notifyObservers(jJ.a);
/*  49 */             return;
/*     */           }
/*     */         }
/*  52 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   protected boolean receiveDistribution(jE paramjE)
/*     */   {
/*  58 */     for (Iterator localIterator = getCollectionManagers().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
/*  59 */       if (((
/*  59 */         localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next())
/*  59 */         .getControllerPos().equals(paramjE.a)) && 
/*  60 */         (localControlBlockElementCollectionManager.receiveDistribution(paramjE))) {
/*  61 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  67 */     return false;
/*     */   }
/*     */   protected boolean convertDeligateControls(lA paramlA, q paramq1, q paramq2) {
/*  70 */     paramq1.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/*  71 */     paramq2.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/*     */ 
/*  74 */     paramq1 = null;
/*     */     try
/*     */     {
/*  78 */       if (((
/*  78 */         paramq1 = getSegmentBuffer().a(paramq2, true, new le())) != null) && 
/*  78 */         (paramq1.a() == 1))
/*     */         try {
/*  80 */           paramq1 = checkShipConfig(paramlA);
/*  81 */           paramlA = paramlA.jdField_a_of_type_LE.d();
/*  82 */           if (!paramq1.a(paramlA)) {
/*  83 */             return false;
/*     */           }
/*  85 */           paramq2.b(paramq1.a(paramlA));
/*     */         }
/*     */         catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/*  88 */           return false;
/*     */         }
/*     */     }
/*     */     catch (CannotImmediateRequestOnClientException paramq1) {
/*  92 */       System.err.println("[CLIENT][WARNING] deferring request for deligated control. segment has been requested: " + paramq1.a());
/*  93 */       return false;
/*     */     }
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   public abstract ArrayList getCollectionManagers();
/*     */ 
/*     */   public void onControllerBlockAdd()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onControllerBlockRemove() {
/*     */   }
/*     */ 
/*     */   public void removeControllerIfNecessary(q paramq1, q paramq2, short arg3) {
/* 108 */     if (??? == this.controllingId)
/* 109 */       synchronized (getCollectionManagers()) {
/* 110 */         for (Iterator localIterator = getCollectionManagers().iterator(); localIterator.hasNext(); )
/*     */         {
/*     */           ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
/* 111 */           if ((
/* 111 */             localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next())
/* 111 */             .equalsControllerPos(paramq1)) {
/* 112 */             localControlBlockElementCollectionManager.remove(paramq2);
/* 113 */             notifyObservers(jJ.a);
/* 114 */             return;
/*     */           }
/*     */         }
/* 117 */         return;
/*     */       }
/*     */   }
/*     */ 
/* 121 */   public boolean hasMetaData() { return (getCollectionManagers().size() > 0) && (((ControlBlockElementCollectionManager)getCollectionManagers().get(0)).hasMetaData()); }
/*     */ 
/*     */   public Ad toTagStructure() {
/* 124 */     Ad[] arrayOfAd = new Ad[getCollectionManagers().size() + 1];
/* 125 */     for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 126 */       arrayOfAd[i] = ((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).toTagStructure();
/*     */     }
/* 128 */     arrayOfAd[getCollectionManagers().size()] = new Ad(Af.a, null, null);
/*     */ 
/* 130 */     return new Ad(Af.n, "wepContr", arrayOfAd);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableControllableElementManager
 * JD-Core Version:    0.6.2
 */