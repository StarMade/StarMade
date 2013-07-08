/*  1:   */package it.unimi.dsi.fastutil;
/*  2:   */
/* 29:   */public abstract class AbstractStack<K>
/* 30:   */  implements Stack<K>
/* 31:   */{
/* 32:   */  public K top()
/* 33:   */  {
/* 34:34 */    return peek(0);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public K peek(int i) {
/* 38:38 */    throw new UnsupportedOperationException();
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */