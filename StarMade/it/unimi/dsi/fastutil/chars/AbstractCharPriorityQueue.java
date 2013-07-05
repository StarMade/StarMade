/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractCharPriorityQueue extends AbstractPriorityQueue<Character>
/*    */   implements CharPriorityQueue
/*    */ {
/*    */   public void enqueue(Character x)
/*    */   {
/* 51 */     enqueue(x.charValue());
/*    */   }
/* 53 */   public Character dequeue() { return Character.valueOf(dequeueChar()); } 
/*    */   public Character first() {
/* 55 */     return Character.valueOf(firstChar());
/*    */   }
/* 57 */   public Character last() { return Character.valueOf(lastChar()); } 
/*    */   public char lastChar() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharPriorityQueue
 * JD-Core Version:    0.6.2
 */