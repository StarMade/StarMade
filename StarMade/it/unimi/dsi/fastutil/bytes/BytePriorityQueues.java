/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 14:   */public class BytePriorityQueues
/* 15:   */{
/* 16:   */  public static class SynchronizedPriorityQueue
/* 17:   */    implements BytePriorityQueue
/* 18:   */  {
/* 19:   */    public static final long serialVersionUID = -7046029254386353129L;
/* 20:   */    
/* 31:   */    protected final BytePriorityQueue q;
/* 32:   */    
/* 43:   */    protected final Object sync;
/* 44:   */    
/* 55:   */    protected SynchronizedPriorityQueue(BytePriorityQueue q, Object sync)
/* 56:   */    {
/* 57:57 */      this.q = q;
/* 58:58 */      this.sync = sync;
/* 59:   */    }
/* 60:   */    
/* 61:61 */    protected SynchronizedPriorityQueue(BytePriorityQueue q) { this.q = q;
/* 62:62 */      this.sync = this; }
/* 63:   */    
/* 64:64 */    public void enqueue(byte x) { synchronized (this.sync) { this.q.enqueue(x); } }
/* 65:65 */    public byte dequeueByte() { synchronized (this.sync) { return this.q.dequeueByte(); } }
/* 66:66 */    public byte firstByte() { synchronized (this.sync) { return this.q.firstByte(); } }
/* 67:67 */    public byte lastByte() { synchronized (this.sync) { return this.q.lastByte(); } }
/* 68:68 */    public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); } }
/* 69:69 */    public int size() { synchronized (this.sync) { return this.q.size(); } }
/* 70:70 */    public void clear() { synchronized (this.sync) { this.q.clear(); } }
/* 71:71 */    public void changed() { synchronized (this.sync) { this.q.changed(); } }
/* 72:72 */    public ByteComparator comparator() { synchronized (this.sync) { return this.q.comparator(); } }
/* 73:73 */    public void enqueue(Byte x) { synchronized (this.sync) { this.q.enqueue(x); } }
/* 74:74 */    public Byte dequeue() { synchronized (this.sync) { return (Byte)this.q.dequeue(); } }
/* 75:75 */    public Byte first() { synchronized (this.sync) { return (Byte)this.q.first(); } }
/* 76:76 */    public Byte last() { synchronized (this.sync) { return (Byte)this.q.last();
/* 77:   */      }
/* 78:   */    }
/* 79:   */  }
/* 80:   */  
/* 81:   */  public static BytePriorityQueue synchronize(BytePriorityQueue q)
/* 82:   */  {
/* 83:83 */    return new SynchronizedPriorityQueue(q);
/* 84:   */  }
/* 85:   */  
/* 88:   */  public static BytePriorityQueue synchronize(BytePriorityQueue q, Object sync)
/* 89:   */  {
/* 90:90 */    return new SynchronizedPriorityQueue(q, sync);
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.BytePriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */