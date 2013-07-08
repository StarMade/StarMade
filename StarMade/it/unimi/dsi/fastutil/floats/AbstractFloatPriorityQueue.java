/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractFloatPriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Float>
/* 49:   */  implements FloatPriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Float x) { enqueue(x.floatValue()); }
/* 52:   */  
/* 53:53 */  public Float dequeue() { return Float.valueOf(dequeueFloat()); }
/* 54:   */  
/* 55:55 */  public Float first() { return Float.valueOf(firstFloat()); }
/* 56:   */  
/* 57:57 */  public Float last() { return Float.valueOf(lastFloat()); }
/* 58:   */  
/* 59:59 */  public float lastFloat() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */