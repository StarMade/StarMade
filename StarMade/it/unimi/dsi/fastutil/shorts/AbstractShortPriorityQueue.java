/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractShortPriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Short>
/* 49:   */  implements ShortPriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Short x) { enqueue(x.shortValue()); }
/* 52:   */  
/* 53:53 */  public Short dequeue() { return Short.valueOf(dequeueShort()); }
/* 54:   */  
/* 55:55 */  public Short first() { return Short.valueOf(firstShort()); }
/* 56:   */  
/* 57:57 */  public Short last() { return Short.valueOf(lastShort()); }
/* 58:   */  
/* 59:59 */  public short lastShort() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */