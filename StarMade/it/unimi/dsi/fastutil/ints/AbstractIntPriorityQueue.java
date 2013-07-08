/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractIntPriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Integer>
/* 49:   */  implements IntPriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Integer x) { enqueue(x.intValue()); }
/* 52:   */  
/* 53:53 */  public Integer dequeue() { return Integer.valueOf(dequeueInt()); }
/* 54:   */  
/* 55:55 */  public Integer first() { return Integer.valueOf(firstInt()); }
/* 56:   */  
/* 57:57 */  public Integer last() { return Integer.valueOf(lastInt()); }
/* 58:   */  
/* 59:59 */  public int lastInt() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */