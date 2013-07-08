/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/* 55:   */public abstract class AbstractIntListIterator
/* 56:   */  extends AbstractIntBidirectionalIterator
/* 57:   */  implements IntListIterator
/* 58:   */{
/* 59:59 */  public void set(Integer ok) { set(ok.intValue()); }
/* 60:   */  
/* 61:61 */  public void add(Integer ok) { add(ok.intValue()); }
/* 62:   */  
/* 63:63 */  public void set(int k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(int k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */