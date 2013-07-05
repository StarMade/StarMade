/*    */ package org.schema.game.common.controller.elements.door;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ import q;
/*    */ 
/*    */ public class DoorCollectionManager extends ElementCollectionManager
/*    */   implements BlockActivationListenerInterface
/*    */ {
/*    */   public DoorCollectionManager(SegmentController paramSegmentController)
/*    */   {
/* 15 */     super((short)122, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 23 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 30 */     return DoorUnit.class;
/*    */   }
/*    */ 
/*    */   public void onActivate(le paramle, boolean paramBoolean)
/*    */   {
/*    */     q localq;
/*    */     Iterator localIterator;
/* 42 */     if (getSegmentController().isOnServer()) {
/* 43 */       localq = paramle.a(new q());
/* 44 */       for (localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*    */       {
/*    */         DoorUnit localDoorUnit;
/* 45 */         if ((
/* 45 */           localDoorUnit = (DoorUnit)localIterator.next())
/* 45 */           .contains(localq)) {
/* 46 */           localDoorUnit.activate(paramle, paramBoolean);
/* 47 */           return;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 68 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.door.DoorCollectionManager
 * JD-Core Version:    0.6.2
 */