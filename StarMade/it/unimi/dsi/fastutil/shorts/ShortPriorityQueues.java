/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 14:   */public class ShortPriorityQueues
/* 15:   */{
/* 16:   */  public static class SynchronizedPriorityQueue
/* 17:   */    implements ShortPriorityQueue
/* 18:   */  {
/* 19:   */    public static final long serialVersionUID = -7046029254386353129L;
/* 20:   */    
/* 31:   */    protected final ShortPriorityQueue q;
/* 32:   */    
/* 43:   */    protected final Object sync;
/* 44:   */    
/* 55:   */    protected SynchronizedPriorityQueue(ShortPriorityQueue q, Object sync)
/* 56:   */    {
/* 57:57 */      this.q = q;
/* 58:58 */      this.sync = sync;
/* 59:   */    }
/* 60:   */    
/* 61:61 */    protected SynchronizedPriorityQueue(ShortPriorityQueue q) { this.q = q;
/* 62:62 */      this.sync = this; }
/* 63:   */    
/* 64:64 */    public void enqueue(short x) { synchronized (this.sync) { this.q.enqueue(x); } }
/* 65:65 */    public short dequeueShort() { synchronized (this.sync) { return this.q.dequeueShort(); } }
/* 66:66 */    public short firstShort() { synchronized (this.sync) { return this.q.firstShort(); } }
/* 67:67 */    public short lastShort() { synchronized (this.sync) { return this.q.lastShort(); } }
/* 68:68 */    public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); } }
/* 69:69 */    public int size() { synchronized (this.sync) { return this.q.size(); } }
/* 70:70 */    public void clear() { synchronized (this.sync) { this.q.clear(); } }
/* 71:71 */    public void changed() { synchronized (this.sync) { this.q.changed(); } }
/* 72:72 */    public ShortComparator comparator() { synchronized (this.sync) { return this.q.comparator(); } }
/* 73:73 */    public void enqueue(Short x) { synchronized (this.sync) { this.q.enqueue(x); } }
/* 74:74 */    public Short dequeue() { synchronized (this.sync) { return (Short)this.q.dequeue(); } }
/* 75:75 */    public Short first() { synchronized (this.sync) { return (Short)this.q.first(); } }
/* 76:76 */    public Short last() { synchronized (this.sync) { return (Short)this.q.last();
/* 77:   */      }
/* 78:   */    }
/* 79:   */  }
/* 80:   */  
/* 81:   */  public static ShortPriorityQueue synchronize(ShortPriorityQueue q)
/* 82:   */  {
/* 83:83 */    return new SynchronizedPriorityQueue(q);
/* 84:   */  }
/* 85:   */  
/* 88:   */  public static ShortPriorityQueue synchronize(ShortPriorityQueue q, Object sync)
/* 89:   */  {
/* 90:90 */    return new SynchronizedPriorityQueue(q, sync);
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */