/*    */ package org.schema.game.common.controller.elements.missile;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*    */ import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*    */ import org.schema.game.common.controller.elements.ManagerReloadInterface;
/*    */ import org.schema.game.common.controller.elements.NTDistributionReceiverInterface;
/*    */ import org.schema.game.common.controller.elements.NTReceiveInterface;
/*    */ import org.schema.game.common.controller.elements.NTSenderInterface;
/*    */ import org.schema.game.common.controller.elements.UsableDistributionControllableElementManager;
/*    */ import q;
/*    */ import yE;
/*    */ 
/*    */ public abstract class MissileElementManager extends UsableDistributionControllableElementManager
/*    */   implements BlockActivationListenerInterface, ManagerReloadInterface, NTDistributionReceiverInterface, NTReceiveInterface, NTSenderInterface
/*    */ {
/*    */   public MissileElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
/*    */   {
/* 20 */     super(paramShort1, paramShort2, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public void drawReloads(yE paramyE, q paramq)
/*    */   {
/*    */     float f;
/* 24 */     for (int i = 0; i < getCollectionManagers().size(); f++)
/* 25 */       if (((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).getControllerPos().equals(paramq))
/*    */       {
/* 27 */         for (MissileUnit localMissileUnit : ((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).getCollection())
/*    */         {
/* 33 */           int j = (int)(System.currentTimeMillis() - 
/* 33 */             localMissileUnit.getLastShoot());
/* 34 */           f = Math.min(2.0F, j / localMissileUnit.getRelaodTime());
/*    */ 
/* 36 */           j = 9;
/* 37 */           if (f <= 1.0F) {
/* 38 */             j = (int)FastMath.a(9.0F + f * 8.0F, 9.0F, 17.0F);
/*    */           }
/*    */ 
/* 41 */           paramyE.a_(j);
/* 42 */           paramyE.b();
/*    */         }
/* 44 */         return;
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileElementManager
 * JD-Core Version:    0.6.2
 */