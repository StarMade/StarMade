/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ public class FactoryResource
/*    */ {
/*    */   public final int count;
/*    */   public final short type;
/*    */ 
/*    */   public FactoryResource(int paramInt, short paramShort)
/*    */   {
/*  8 */     this.count = paramInt;
/*  9 */     this.type = paramShort;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 13 */     return "(" + ElementKeyMap.getInfo(this.type) + " x" + this.count + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.FactoryResource
 * JD-Core Version:    0.6.2
 */