/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/* 55:   */public abstract class AbstractCharListIterator
/* 56:   */  extends AbstractCharBidirectionalIterator
/* 57:   */  implements CharListIterator
/* 58:   */{
/* 59:59 */  public void set(Character ok) { set(ok.charValue()); }
/* 60:   */  
/* 61:61 */  public void add(Character ok) { add(ok.charValue()); }
/* 62:   */  
/* 63:63 */  public void set(char k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(char k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */