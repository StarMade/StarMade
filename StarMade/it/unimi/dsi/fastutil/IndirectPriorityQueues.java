/*   1:    */package it.unimi.dsi.fastutil;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  38:    */public class IndirectPriorityQueues
/*  39:    */{
/*  40:    */  public static class EmptyIndirectPriorityQueue
/*  41:    */    extends AbstractIndirectPriorityQueue
/*  42:    */  {
/*  43: 43 */    public void enqueue(int i) { throw new UnsupportedOperationException(); }
/*  44: 44 */    public int dequeue() { throw new NoSuchElementException(); }
/*  45: 45 */    public boolean isEmpty() { return true; }
/*  46: 46 */    public int size() { return 0; }
/*  47: 47 */    public boolean contains(int index) { return false; }
/*  48:    */    public void clear() {}
/*  49: 49 */    public int first() { throw new NoSuchElementException(); }
/*  50: 50 */    public int last() { throw new NoSuchElementException(); }
/*  51: 51 */    public void changed() { throw new NoSuchElementException(); }
/*  52:    */    public void allChanged() {}
/*  53: 53 */    public Comparator<?> comparator() { return null; }
/*  54: 54 */    public void changed(int i) { throw new IllegalArgumentException("Index " + i + " is not in the queue"); }
/*  55: 55 */    public boolean remove(int i) { return false; }
/*  56: 56 */    public int front(int[] a) { return 0; }
/*  57:    */  }
/*  58:    */  
/*  63: 63 */  public static final EmptyIndirectPriorityQueue EMPTY_QUEUE = new EmptyIndirectPriorityQueue();
/*  64:    */  
/*  66:    */  public static class SynchronizedIndirectPriorityQueue<K>
/*  67:    */    implements IndirectPriorityQueue<K>
/*  68:    */  {
/*  69:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  70:    */    
/*  71:    */    protected final IndirectPriorityQueue<K> q;
/*  72:    */    protected final Object sync;
/*  73:    */    
/*  74:    */    protected SynchronizedIndirectPriorityQueue(IndirectPriorityQueue<K> q, Object sync)
/*  75:    */    {
/*  76: 76 */      this.q = q;
/*  77: 77 */      this.sync = sync;
/*  78:    */    }
/*  79:    */    
/*  80:    */    protected SynchronizedIndirectPriorityQueue(IndirectPriorityQueue<K> q) {
/*  81: 81 */      this.q = q;
/*  82: 82 */      this.sync = this;
/*  83:    */    }
/*  84:    */    
/*  85: 85 */    public void enqueue(int x) { synchronized (this.sync) { this.q.enqueue(x); } }
/*  86: 86 */    public int dequeue() { synchronized (this.sync) { return this.q.dequeue(); } }
/*  87: 87 */    public boolean contains(int index) { synchronized (this.sync) { return this.q.contains(index); } }
/*  88: 88 */    public int first() { synchronized (this.sync) { return this.q.first(); } }
/*  89: 89 */    public int last() { synchronized (this.sync) { return this.q.last(); } }
/*  90: 90 */    public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); } }
/*  91: 91 */    public int size() { synchronized (this.sync) { return this.q.size(); } }
/*  92: 92 */    public void clear() { synchronized (this.sync) { this.q.clear(); } }
/*  93: 93 */    public void changed() { synchronized (this.sync) { this.q.changed(); } }
/*  94: 94 */    public void allChanged() { synchronized (this.sync) { this.q.allChanged(); } }
/*  95: 95 */    public void changed(int i) { synchronized (this.sync) { this.q.changed(i); } }
/*  96: 96 */    public boolean remove(int i) { synchronized (this.sync) { return this.q.remove(i); } }
/*  97: 97 */    public Comparator<? super K> comparator() { synchronized (this.sync) { return this.q.comparator(); } }
/*  98: 98 */    public int front(int[] a) { return this.q.front(a); }
/*  99:    */  }
/* 100:    */  
/* 105:    */  public static <K> IndirectPriorityQueue<K> synchronize(IndirectPriorityQueue<K> q)
/* 106:    */  {
/* 107:107 */    return new SynchronizedIndirectPriorityQueue(q);
/* 108:    */  }
/* 109:    */  
/* 114:    */  public static <K> IndirectPriorityQueue<K> synchronize(IndirectPriorityQueue<K> q, Object sync)
/* 115:    */  {
/* 116:116 */    return new SynchronizedIndirectPriorityQueue(q, sync);
/* 117:    */  }
/* 118:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */