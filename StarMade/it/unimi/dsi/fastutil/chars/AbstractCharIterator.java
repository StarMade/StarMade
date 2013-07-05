/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public abstract class AbstractCharIterator
/*    */   implements CharIterator
/*    */ {
/*    */   public char nextChar()
/*    */   {
/* 60 */     return next().charValue();
/*    */   }
/* 62 */   public Character next() { return Character.valueOf(nextChar()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextChar();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharIterator
 * JD-Core Version:    0.6.2
 */