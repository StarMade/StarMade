/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractShortStack extends AbstractStack<Short>
/*    */   implements ShortStack
/*    */ {
/*    */   public void push(Short o)
/*    */   {
/* 56 */     push(o.shortValue());
/*    */   }
/*    */ 
/*    */   public Short pop() {
/* 60 */     return Short.valueOf(popShort());
/*    */   }
/*    */ 
/*    */   public Short top() {
/* 64 */     return Short.valueOf(topShort());
/*    */   }
/*    */ 
/*    */   public Short peek(int i) {
/* 68 */     return Short.valueOf(peekShort(i));
/*    */   }
/*    */ 
/*    */   public void push(short k) {
/* 72 */     push(Short.valueOf(k));
/*    */   }
/*    */ 
/*    */   public short popShort() {
/* 76 */     return pop().shortValue();
/*    */   }
/*    */ 
/*    */   public short topShort() {
/* 80 */     return top().shortValue();
/*    */   }
/*    */ 
/*    */   public short peekShort(int i) {
/* 84 */     return peek(i).shortValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortStack
 * JD-Core Version:    0.6.2
 */