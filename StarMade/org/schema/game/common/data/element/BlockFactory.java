/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ public class BlockFactory
/*    */ {
/*    */   public short enhancer;
/*    */   public FactoryResource[][] input;
/*    */   public FactoryResource[][] output;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 11 */     if (this.input != null) return "Block Factory Products: " + this.input.length; return "INPUT";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BlockFactory
 * JD-Core Version:    0.6.2
 */