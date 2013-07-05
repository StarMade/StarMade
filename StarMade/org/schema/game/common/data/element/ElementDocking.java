/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import le;
/*    */ 
/*    */ public class ElementDocking
/*    */ {
/*    */   public final le from;
/*    */   public final le to;
/*    */ 
/*    */   public ElementDocking(le paramle1, le paramle2)
/*    */   {
/* 10 */     this.from = paramle1;
/* 11 */     this.to = paramle2;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object paramObject) {
/* 15 */     return equalsDock((ElementDocking)paramObject);
/*    */   }
/*    */   public boolean equalsDock(ElementDocking paramElementDocking) {
/* 18 */     return (this.from.equals(paramElementDocking.from)) && (this.to.equals(paramElementDocking.to));
/*    */   }
/*    */ 
/*    */   public int hashCode() {
/* 22 */     return this.from.hashCode() + this.to.hashCode();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementDocking
 * JD-Core Version:    0.6.2
 */