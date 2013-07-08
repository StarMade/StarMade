/*   1:    */package it.unimi.dsi.fastutil;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  40:    */public class PriorityQueues
/*  41:    */{
/*  42:    */  public static class EmptyPriorityQueue
/*  43:    */    extends AbstractPriorityQueue
/*  44:    */  {
/*  45: 45 */    public void enqueue(Object o) { throw new UnsupportedOperationException(); }
/*  46: 46 */    public Object dequeue() { throw new NoSuchElementException(); }
/*  47: 47 */    public boolean isEmpty() { return true; }
/*  48: 48 */    public int size() { return 0; }
/*  49:    */    public void clear() {}
/*  50: 50 */    public Object first() { throw new NoSuchElementException(); }
/*  51: 51 */    public Object last() { throw new NoSuchElementException(); }
/*  52: 52 */    public void changed() { throw new NoSuchElementException(); }
/*  53: 53 */    public Comparator<?> comparator() { return null; }
/*  54:    */  }
/*  55:    */  
/*  60: 60 */  public static final EmptyPriorityQueue EMPTY_QUEUE = new EmptyPriorityQueue();
/*  61:    */  
/*  63:    */  public static class SynchronizedPriorityQueue<K>
/*  64:    */    implements PriorityQueue<K>
/*  65:    */  {
/*  66:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  67:    */    
/*  68:    */    protected final PriorityQueue<K> q;
/*  69:    */    protected final Object sync;
/*  70:    */    
/*  71:    */    protected SynchronizedPriorityQueue(PriorityQueue<K> q, Object sync)
/*  72:    */    {
/*  73: 73 */      this.q = q;
/*  74: 74 */      this.sync = sync;
/*  75:    */    }
/*  76:    */    
/*  77:    */    protected SynchronizedPriorityQueue(PriorityQueue<K> q) {
/*  78: 78 */      this.q = q;
/*  79: 79 */      this.sync = this;
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    public void enqueue(K x) { synchronized (this.sync) { this.q.enqueue(x); } }
/*  83: 83 */    public K dequeue() { synchronized (this.sync) { return this.q.dequeue(); } }
/*  84: 84 */    public K first() { synchronized (this.sync) { return this.q.first(); } }
/*  85: 85 */    public K last() { synchronized (this.sync) { return this.q.last(); } }
/*  86: 86 */    public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); } }
/*  87: 87 */    public int size() { synchronized (this.sync) { return this.q.size(); } }
/*  88: 88 */    public void clear() { synchronized (this.sync) { this.q.clear(); } }
/*  89: 89 */    public void changed() { synchronized (this.sync) { this.q.changed(); } }
/*  90: 90 */    public Comparator<? super K> comparator() { synchronized (this.sync) { return this.q.comparator();
/*  91:    */      }
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  97:    */  public static <K> PriorityQueue<K> synchronize(PriorityQueue<K> q)
/*  98:    */  {
/*  99: 99 */    return new SynchronizedPriorityQueue(q);
/* 100:    */  }
/* 101:    */  
/* 106:    */  public static <K> PriorityQueue<K> synchronize(PriorityQueue<K> q, Object sync)
/* 107:    */  {
/* 108:108 */    return new SynchronizedPriorityQueue(q, sync);
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.PriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */