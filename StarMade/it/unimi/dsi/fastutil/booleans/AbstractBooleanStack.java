/*    */ package it.unimi.dsi.fastutil.booleans;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractBooleanStack extends AbstractStack<Boolean>
/*    */   implements BooleanStack
/*    */ {
/*    */   public void push(Boolean o)
/*    */   {
/* 56 */     push(o.booleanValue());
/*    */   }
/*    */ 
/*    */   public Boolean pop() {
/* 60 */     return Boolean.valueOf(popBoolean());
/*    */   }
/*    */ 
/*    */   public Boolean top() {
/* 64 */     return Boolean.valueOf(topBoolean());
/*    */   }
/*    */ 
/*    */   public Boolean peek(int i) {
/* 68 */     return Boolean.valueOf(peekBoolean(i));
/*    */   }
/*    */ 
/*    */   public void push(boolean k) {
/* 72 */     push(Boolean.valueOf(k));
/*    */   }
/*    */ 
/*    */   public boolean popBoolean() {
/* 76 */     return pop().booleanValue();
/*    */   }
/*    */ 
/*    */   public boolean topBoolean() {
/* 80 */     return top().booleanValue();
/*    */   }
/*    */ 
/*    */   public boolean peekBoolean(int i) {
/* 84 */     return peek(i).booleanValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanStack
 * JD-Core Version:    0.6.2
 */