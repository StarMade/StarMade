/*    */ package org.schema.game.common.controller;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ 
/*    */ public class CannotBeControlledException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 4188138482695970846L;
/*    */   public final ElementInformation a;
/*    */   public final ElementInformation b;
/*    */ 
/*    */   public CannotBeControlledException(ElementInformation paramElementInformation1, ElementInformation paramElementInformation2)
/*    */   {
/* 15 */     this.b = paramElementInformation1;
/* 16 */     this.a = paramElementInformation2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.CannotBeControlledException
 * JD-Core Version:    0.6.2
 */