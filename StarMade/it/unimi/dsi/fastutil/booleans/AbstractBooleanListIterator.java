/*  1:   */package it.unimi.dsi.fastutil.booleans;
/*  2:   */
/* 55:   */public abstract class AbstractBooleanListIterator
/* 56:   */  extends AbstractBooleanBidirectionalIterator
/* 57:   */  implements BooleanListIterator
/* 58:   */{
/* 59:59 */  public void set(Boolean ok) { set(ok.booleanValue()); }
/* 60:   */  
/* 61:61 */  public void add(Boolean ok) { add(ok.booleanValue()); }
/* 62:   */  
/* 63:63 */  public void set(boolean k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(boolean k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */