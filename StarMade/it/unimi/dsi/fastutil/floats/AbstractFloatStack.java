/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractFloatStack extends AbstractStack<Float>
/*    */   implements FloatStack
/*    */ {
/*    */   public void push(Float o)
/*    */   {
/* 56 */     push(o.floatValue());
/*    */   }
/*    */ 
/*    */   public Float pop() {
/* 60 */     return Float.valueOf(popFloat());
/*    */   }
/*    */ 
/*    */   public Float top() {
/* 64 */     return Float.valueOf(topFloat());
/*    */   }
/*    */ 
/*    */   public Float peek(int i) {
/* 68 */     return Float.valueOf(peekFloat(i));
/*    */   }
/*    */ 
/*    */   public void push(float k) {
/* 72 */     push(Float.valueOf(k));
/*    */   }
/*    */ 
/*    */   public float popFloat() {
/* 76 */     return pop().floatValue();
/*    */   }
/*    */ 
/*    */   public float topFloat() {
/* 80 */     return top().floatValue();
/*    */   }
/*    */ 
/*    */   public float peekFloat(int i) {
/* 84 */     return peek(i).floatValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatStack
 * JD-Core Version:    0.6.2
 */