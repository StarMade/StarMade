/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractDoublePriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Double>
/* 49:   */  implements DoublePriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Double x) { enqueue(x.doubleValue()); }
/* 52:   */  
/* 53:53 */  public Double dequeue() { return Double.valueOf(dequeueDouble()); }
/* 54:   */  
/* 55:55 */  public Double first() { return Double.valueOf(firstDouble()); }
/* 56:   */  
/* 57:57 */  public Double last() { return Double.valueOf(lastDouble()); }
/* 58:   */  
/* 59:59 */  public double lastDouble() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoublePriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */