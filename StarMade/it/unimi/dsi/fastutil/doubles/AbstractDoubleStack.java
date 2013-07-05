/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractDoubleStack extends AbstractStack<Double>
/*    */   implements DoubleStack
/*    */ {
/*    */   public void push(Double o)
/*    */   {
/* 56 */     push(o.doubleValue());
/*    */   }
/*    */ 
/*    */   public Double pop() {
/* 60 */     return Double.valueOf(popDouble());
/*    */   }
/*    */ 
/*    */   public Double top() {
/* 64 */     return Double.valueOf(topDouble());
/*    */   }
/*    */ 
/*    */   public Double peek(int i) {
/* 68 */     return Double.valueOf(peekDouble(i));
/*    */   }
/*    */ 
/*    */   public void push(double k) {
/* 72 */     push(Double.valueOf(k));
/*    */   }
/*    */ 
/*    */   public double popDouble() {
/* 76 */     return pop().doubleValue();
/*    */   }
/*    */ 
/*    */   public double topDouble() {
/* 80 */     return top().doubleValue();
/*    */   }
/*    */ 
/*    */   public double peekDouble(int i) {
/* 84 */     return peek(i).doubleValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleStack
 * JD-Core Version:    0.6.2
 */