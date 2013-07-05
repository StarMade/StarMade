/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import q;
/*    */ 
/*    */ public class ControlledElementContainer
/*    */ {
/*    */   public long from;
/*    */   public q to;
/*    */   public short controlledType;
/* 14 */   public boolean add = false;
/*    */   public boolean send;
/*    */ 
/*    */   public ControlledElementContainer(long paramLong, q paramq, short paramShort, boolean paramBoolean1, boolean paramBoolean2)
/*    */   {
/* 19 */     this.from = paramLong;
/* 20 */     this.to = paramq;
/* 21 */     this.controlledType = paramShort;
/* 22 */     this.add = paramBoolean1;
/* 23 */     this.send = paramBoolean2;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 30 */     return (((ControlledElementContainer)paramObject).add == this.add) && (((ControlledElementContainer)paramObject).from == this.from) && (((ControlledElementContainer)paramObject).to.equals(this.to)) && (((ControlledElementContainer)paramObject).controlledType == this.controlledType);
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 38 */     return (int)(this.from + this.to.hashCode());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlledElementContainer
 * JD-Core Version:    0.6.2
 */