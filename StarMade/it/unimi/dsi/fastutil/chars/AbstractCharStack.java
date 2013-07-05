/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractCharStack extends AbstractStack<Character>
/*    */   implements CharStack
/*    */ {
/*    */   public void push(Character o)
/*    */   {
/* 56 */     push(o.charValue());
/*    */   }
/*    */ 
/*    */   public Character pop() {
/* 60 */     return Character.valueOf(popChar());
/*    */   }
/*    */ 
/*    */   public Character top() {
/* 64 */     return Character.valueOf(topChar());
/*    */   }
/*    */ 
/*    */   public Character peek(int i) {
/* 68 */     return Character.valueOf(peekChar(i));
/*    */   }
/*    */ 
/*    */   public void push(char k) {
/* 72 */     push(Character.valueOf(k));
/*    */   }
/*    */ 
/*    */   public char popChar() {
/* 76 */     return pop().charValue();
/*    */   }
/*    */ 
/*    */   public char topChar() {
/* 80 */     return top().charValue();
/*    */   }
/*    */ 
/*    */   public char peekChar(int i) {
/* 84 */     return peek(i).charValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharStack
 * JD-Core Version:    0.6.2
 */