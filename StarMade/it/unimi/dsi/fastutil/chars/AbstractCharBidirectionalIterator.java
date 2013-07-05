/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public abstract class AbstractCharBidirectionalIterator extends AbstractCharIterator
/*    */   implements CharBidirectionalIterator
/*    */ {
/*    */   public char previousChar()
/*    */   {
/* 58 */     return previous().charValue();
/*    */   }
/* 60 */   public Character previous() { return Character.valueOf(previousChar()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousChar();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharBidirectionalIterator
 * JD-Core Version:    0.6.2
 */