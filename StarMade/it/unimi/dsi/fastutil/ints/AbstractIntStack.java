/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractIntStack extends AbstractStack<Integer>
/*    */   implements IntStack
/*    */ {
/*    */   public void push(Integer o)
/*    */   {
/* 56 */     push(o.intValue());
/*    */   }
/*    */ 
/*    */   public Integer pop() {
/* 60 */     return Integer.valueOf(popInt());
/*    */   }
/*    */ 
/*    */   public Integer top() {
/* 64 */     return Integer.valueOf(topInt());
/*    */   }
/*    */ 
/*    */   public Integer peek(int i) {
/* 68 */     return Integer.valueOf(peekInt(i));
/*    */   }
/*    */ 
/*    */   public void push(int k) {
/* 72 */     push(Integer.valueOf(k));
/*    */   }
/*    */ 
/*    */   public int popInt() {
/* 76 */     return pop().intValue();
/*    */   }
/*    */ 
/*    */   public int topInt() {
/* 80 */     return top().intValue();
/*    */   }
/*    */ 
/*    */   public int peekInt(int i) {
/* 84 */     return peek(i).intValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntStack
 * JD-Core Version:    0.6.2
 */