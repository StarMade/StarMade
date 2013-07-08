/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/* 54:   */public abstract class AbstractCharBidirectionalIterator
/* 55:   */  extends AbstractCharIterator
/* 56:   */  implements CharBidirectionalIterator
/* 57:   */{
/* 58:58 */  public char previousChar() { return previous().charValue(); }
/* 59:   */  
/* 60:60 */  public Character previous() { return Character.valueOf(previousChar()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousChar();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */