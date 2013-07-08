/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 55:   */public abstract class AbstractLongListIterator
/* 56:   */  extends AbstractLongBidirectionalIterator
/* 57:   */  implements LongListIterator
/* 58:   */{
/* 59:59 */  public void set(Long ok) { set(ok.longValue()); }
/* 60:   */  
/* 61:61 */  public void add(Long ok) { add(ok.longValue()); }
/* 62:   */  
/* 63:63 */  public void set(long k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(long k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */