/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public class ManagerModuleSingle extends ManagerModuleControllable
/*    */ {
/*    */   private final UsableControllableSingleElementManager elementManager;
/*    */ 
/*    */   public ManagerModuleSingle(UsableControllableSingleElementManager paramUsableControllableSingleElementManager, short paramShort1, short paramShort2)
/*    */   {
/* 20 */     super(paramUsableControllableSingleElementManager, paramShort2, paramShort1);
/* 21 */     this.elementManager = paramUsableControllableSingleElementManager;
/*    */   }
/*    */ 
/*    */   public void addControlledBlock(q paramq1, q paramq2, short paramShort)
/*    */   {
/* 33 */     getCollectionManager().addModded(paramq2);
/* 34 */     this.elementManager.onControllerChange();
/*    */   }
/*    */ 
/*    */   public void addElement(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*    */   {
/* 39 */     getCollectionManager().addModded(ElementCollection.getIndex(paramByte1, paramByte2, paramByte3, paramSegment));
/*    */   }
/*    */ 
/*    */   public void clear()
/*    */   {
/* 48 */     getCollectionManager().clear();
/*    */   }
/*    */ 
/*    */   public ElementCollectionManager getCollectionManager()
/*    */   {
/* 57 */     return getElementManager().getCollection();
/*    */   }
/*    */ 
/*    */   public UsableControllableSingleElementManager getElementManager()
/*    */   {
/* 63 */     return this.elementManager;
/*    */   }
/*    */ 
/*    */   public void removeControllerBlock(q paramq1, q paramq2, short paramShort)
/*    */   {
/* 73 */     getCollectionManager().remove(paramq2);
/* 74 */     this.elementManager.onControllerChange();
/*    */   }
/*    */ 
/*    */   public void removeElement(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*    */   {
/* 80 */     getCollectionManager().remove(ElementCollection.getIndex(paramByte1, paramByte2, paramByte3, paramSegment));
/*    */   }
/*    */ 
/*    */   public void update(xq paramxq, long paramLong)
/*    */   {
/* 89 */     ElementCollectionManager localElementCollectionManager = this.elementManager.getCollection();
/* 90 */     assert (localElementCollectionManager != null);
/* 91 */     localElementCollectionManager.updateStructure(paramLong);
/* 92 */     if (localElementCollectionManager.needsUpdate())
/* 93 */       localElementCollectionManager.update(paramxq);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleSingle
 * JD-Core Version:    0.6.2
 */