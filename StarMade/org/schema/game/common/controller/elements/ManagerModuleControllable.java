/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import xq;
/*    */ 
/*    */ public class ManagerModuleControllable extends ManagerModule
/*    */ {
/*    */   private final short controllerID;
/*    */ 
/*    */   public ManagerModuleControllable(UsableElementManager paramUsableElementManager, short paramShort1, short paramShort2)
/*    */   {
/* 15 */     super(paramUsableElementManager, paramShort1);
/* 16 */     this.controllerID = paramShort2;
/*    */   }
/*    */ 
/*    */   public short getControllerID()
/*    */   {
/* 22 */     return this.controllerID;
/*    */   }
/*    */ 
/*    */   public void update(xq paramxq, long paramLong)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleControllable
 * JD-Core Version:    0.6.2
 */