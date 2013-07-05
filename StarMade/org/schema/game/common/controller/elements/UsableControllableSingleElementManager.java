/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import cz;
/*    */ import jL;
/*    */ import java.io.PrintStream;
/*    */ import lA;
/*    */ import lE;
/*    */ import le;
/*    */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*    */ import q;
/*    */ 
/*    */ public abstract class UsableControllableSingleElementManager extends UsableElementManager
/*    */ {
/*    */   private final ElementCollectionManager collection;
/*    */ 
/*    */   public UsableControllableSingleElementManager(ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/*    */   {
/* 22 */     super(paramSegmentController);
/* 23 */     this.collection = paramElementCollectionManager;
/*    */   }
/*    */ 
/*    */   protected boolean convertDeligateControls(lA paramlA, q paramq1, q paramq2) {
/* 27 */     paramq1.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/* 28 */     paramq2.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/*    */ 
/* 31 */     paramq1 = null;
/*    */     try
/*    */     {
/* 35 */       if (((
/* 35 */         paramq1 = getSegmentBuffer().a(paramq2, true, new le())) != null) && 
/* 35 */         (paramq1.a() == 1))
/*    */         try {
/* 37 */           paramq1 = checkShipConfig(paramlA);
/* 38 */           paramlA = paramlA.jdField_a_of_type_LE.d();
/* 39 */           if (!paramq1.a(paramlA)) {
/* 40 */             return false;
/*    */           }
/* 42 */           paramq2.b(paramq1.a(paramlA));
/*    */         }
/*    */         catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/* 45 */           return false;
/*    */         }
/*    */     }
/*    */     catch (CannotImmediateRequestOnClientException paramq1) {
/* 49 */       System.err.println("[CLIENT][WARNING] deferring request for deligated control. segment has been requested: " + paramq1.a());
/* 50 */       return false;
/*    */     }
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   public final ElementCollectionManager getCollection()
/*    */   {
/* 59 */     return this.collection;
/*    */   }
/*    */ 
/*    */   public abstract void onControllerChange();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableControllableSingleElementManager
 * JD-Core Version:    0.6.2
 */