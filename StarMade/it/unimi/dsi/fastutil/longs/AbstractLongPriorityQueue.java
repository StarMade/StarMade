/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractLongPriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Long>
/* 49:   */  implements LongPriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Long x) { enqueue(x.longValue()); }
/* 52:   */  
/* 53:53 */  public Long dequeue() { return Long.valueOf(dequeueLong()); }
/* 54:   */  
/* 55:55 */  public Long first() { return Long.valueOf(firstLong()); }
/* 56:   */  
/* 57:57 */  public Long last() { return Long.valueOf(lastLong()); }
/* 58:   */  
/* 59:59 */  public long lastLong() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */