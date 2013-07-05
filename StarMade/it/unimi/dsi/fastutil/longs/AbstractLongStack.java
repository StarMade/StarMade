/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractLongStack extends AbstractStack<Long>
/*    */   implements LongStack
/*    */ {
/*    */   public void push(Long o)
/*    */   {
/* 56 */     push(o.longValue());
/*    */   }
/*    */ 
/*    */   public Long pop() {
/* 60 */     return Long.valueOf(popLong());
/*    */   }
/*    */ 
/*    */   public Long top() {
/* 64 */     return Long.valueOf(topLong());
/*    */   }
/*    */ 
/*    */   public Long peek(int i) {
/* 68 */     return Long.valueOf(peekLong(i));
/*    */   }
/*    */ 
/*    */   public void push(long k) {
/* 72 */     push(Long.valueOf(k));
/*    */   }
/*    */ 
/*    */   public long popLong() {
/* 76 */     return pop().longValue();
/*    */   }
/*    */ 
/*    */   public long topLong() {
/* 80 */     return top().longValue();
/*    */   }
/*    */ 
/*    */   public long peekLong(int i) {
/* 84 */     return peek(i).longValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongStack
 * JD-Core Version:    0.6.2
 */