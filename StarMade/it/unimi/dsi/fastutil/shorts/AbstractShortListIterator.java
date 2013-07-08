/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 55:   */public abstract class AbstractShortListIterator
/* 56:   */  extends AbstractShortBidirectionalIterator
/* 57:   */  implements ShortListIterator
/* 58:   */{
/* 59:59 */  public void set(Short ok) { set(ok.shortValue()); }
/* 60:   */  
/* 61:61 */  public void add(Short ok) { add(ok.shortValue()); }
/* 62:   */  
/* 63:63 */  public void set(short k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(short k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */