/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public abstract class ManagerModule
/*    */ {
/*    */   private final UsableElementManager elementManager;
/*    */   private final short elementID;
/*    */   private ManagerModule next;
/*    */ 
/*    */   public ManagerModule(UsableElementManager paramUsableElementManager, short paramShort)
/*    */   {
/* 19 */     this.elementManager = paramUsableElementManager;
/* 20 */     this.elementID = paramShort;
/*    */   }
/*    */ 
/*    */   public void addControlledBlock(q paramq1, q paramq2, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clear()
/*    */   {
/*    */   }
/*    */ 
/*    */   public short getElementID()
/*    */   {
/* 37 */     return this.elementID;
/*    */   }
/*    */ 
/*    */   public UsableElementManager getElementManager()
/*    */   {
/* 42 */     return this.elementManager;
/*    */   }
/*    */ 
/*    */   public void removeControllerBlock(q paramq1, q paramq2, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 53 */     return "(" + getElementManager().getClass().getSimpleName() + ": " + ElementKeyMap.getInfo(this.elementID).getName() + ")";
/*    */   }
/*    */ 
/*    */   public abstract void update(xq paramxq, long paramLong);
/*    */ 
/*    */   public ManagerModule getNext()
/*    */   {
/* 63 */     return this.next;
/*    */   }
/*    */ 
/*    */   public void setNext(ManagerModule paramManagerModule)
/*    */   {
/* 71 */     this.next = paramManagerModule;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModule
 * JD-Core Version:    0.6.2
 */