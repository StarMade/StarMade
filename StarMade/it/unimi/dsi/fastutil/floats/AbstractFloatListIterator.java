/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/* 55:   */public abstract class AbstractFloatListIterator
/* 56:   */  extends AbstractFloatBidirectionalIterator
/* 57:   */  implements FloatListIterator
/* 58:   */{
/* 59:59 */  public void set(Float ok) { set(ok.floatValue()); }
/* 60:   */  
/* 61:61 */  public void add(Float ok) { add(ok.floatValue()); }
/* 62:   */  
/* 63:63 */  public void set(float k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(float k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */