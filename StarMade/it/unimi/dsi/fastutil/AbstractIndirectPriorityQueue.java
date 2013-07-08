/*  1:   */package it.unimi.dsi.fastutil;
/*  2:   */
/* 24:   */public abstract class AbstractIndirectPriorityQueue<K>
/* 25:   */  implements IndirectPriorityQueue<K>
/* 26:   */{
/* 27:27 */  public int last() { throw new UnsupportedOperationException(); }
/* 28:   */  
/* 29:29 */  public void changed() { changed(first()); }
/* 30:   */  
/* 31:31 */  public void changed(int index) { throw new UnsupportedOperationException(); }
/* 32:   */  
/* 33:33 */  public void allChanged() { throw new UnsupportedOperationException(); }
/* 34:   */  
/* 35:35 */  public boolean remove(int index) { throw new UnsupportedOperationException(); }
/* 36:   */  
/* 37:37 */  public boolean contains(int index) { throw new UnsupportedOperationException(); }
/* 38:   */  
/* 39:39 */  public boolean isEmpty() { return size() == 0; }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */