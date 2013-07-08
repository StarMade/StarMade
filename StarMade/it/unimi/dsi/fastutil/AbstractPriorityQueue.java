/*  1:   */package it.unimi.dsi.fastutil;
/*  2:   */
/* 26:   */public abstract class AbstractPriorityQueue<K>
/* 27:   */  implements PriorityQueue<K>
/* 28:   */{
/* 29:29 */  public void changed() { throw new UnsupportedOperationException(); }
/* 30:   */  
/* 31:31 */  public K last() { throw new UnsupportedOperationException(); }
/* 32:   */  
/* 33:33 */  public boolean isEmpty() { return size() == 0; }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */