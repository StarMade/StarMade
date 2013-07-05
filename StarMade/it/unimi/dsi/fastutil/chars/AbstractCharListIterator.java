/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public abstract class AbstractCharListIterator extends AbstractCharBidirectionalIterator
/*    */   implements CharListIterator
/*    */ {
/*    */   public void set(Character ok)
/*    */   {
/* 59 */     set(ok.charValue());
/*    */   }
/* 61 */   public void add(Character ok) { add(ok.charValue()); } 
/*    */   public void set(char k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(char k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharListIterator
 * JD-Core Version:    0.6.2
 */