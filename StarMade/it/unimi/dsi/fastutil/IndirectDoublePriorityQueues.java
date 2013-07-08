/*   1:    */package it.unimi.dsi.fastutil;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  37:    */public class IndirectDoublePriorityQueues
/*  38:    */{
/*  39:    */  public static class EmptyIndirectDoublePriorityQueue
/*  40:    */    extends IndirectPriorityQueues.EmptyIndirectPriorityQueue
/*  41:    */  {
/*  42: 42 */    public int secondaryFirst() { throw new NoSuchElementException(); }
/*  43: 43 */    public int secondaryLast() { throw new NoSuchElementException(); }
/*  44: 44 */    public Comparator<?> secondaryComparator() { return null; }
/*  45:    */  }
/*  46:    */  
/*  51: 51 */  public static final EmptyIndirectDoublePriorityQueue EMPTY_QUEUE = new EmptyIndirectDoublePriorityQueue();
/*  52:    */  
/*  54:    */  public static class SynchronizedIndirectDoublePriorityQueue<K>
/*  55:    */    implements IndirectDoublePriorityQueue<K>
/*  56:    */  {
/*  57:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  58:    */    
/*  59:    */    protected final IndirectDoublePriorityQueue<K> q;
/*  60:    */    protected final Object sync;
/*  61:    */    
/*  62:    */    protected SynchronizedIndirectDoublePriorityQueue(IndirectDoublePriorityQueue<K> q, Object sync)
/*  63:    */    {
/*  64: 64 */      this.q = q;
/*  65: 65 */      this.sync = sync;
/*  66:    */    }
/*  67:    */    
/*  68:    */    protected SynchronizedIndirectDoublePriorityQueue(IndirectDoublePriorityQueue<K> q) {
/*  69: 69 */      this.q = q;
/*  70: 70 */      this.sync = this;
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    public void enqueue(int index) { synchronized (this.sync) { this.q.enqueue(index); } }
/*  74: 74 */    public int dequeue() { synchronized (this.sync) { return this.q.dequeue(); } }
/*  75: 75 */    public int first() { synchronized (this.sync) { return this.q.first(); } }
/*  76: 76 */    public int last() { synchronized (this.sync) { return this.q.last(); } }
/*  77: 77 */    public boolean contains(int index) { synchronized (this.sync) { return this.q.contains(index); } }
/*  78: 78 */    public int secondaryFirst() { synchronized (this.sync) { return this.q.secondaryFirst(); } }
/*  79: 79 */    public int secondaryLast() { synchronized (this.sync) { return this.q.secondaryLast(); } }
/*  80: 80 */    public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); } }
/*  81: 81 */    public int size() { synchronized (this.sync) { return this.q.size(); } }
/*  82: 82 */    public void clear() { synchronized (this.sync) { this.q.clear(); } }
/*  83: 83 */    public void changed() { synchronized (this.sync) { this.q.changed(); } }
/*  84: 84 */    public void allChanged() { synchronized (this.sync) { this.q.allChanged(); } }
/*  85: 85 */    public void changed(int i) { synchronized (this.sync) { this.q.changed(i); } }
/*  86: 86 */    public boolean remove(int i) { synchronized (this.sync) { return this.q.remove(i); } }
/*  87: 87 */    public Comparator<? super K> comparator() { synchronized (this.sync) { return this.q.comparator(); } }
/*  88: 88 */    public Comparator<? super K> secondaryComparator() { synchronized (this.sync) { return this.q.secondaryComparator(); } }
/*  89: 89 */    public int secondaryFront(int[] a) { return this.q.secondaryFront(a); }
/*  90: 90 */    public int front(int[] a) { return this.q.front(a); }
/*  91:    */  }
/*  92:    */  
/*  97:    */  public static <K> IndirectDoublePriorityQueue<K> synchronize(IndirectDoublePriorityQueue<K> q)
/*  98:    */  {
/*  99: 99 */    return new SynchronizedIndirectDoublePriorityQueue(q);
/* 100:    */  }
/* 101:    */  
/* 106:    */  public static <K> IndirectDoublePriorityQueue<K> synchronize(IndirectDoublePriorityQueue<K> q, Object sync)
/* 107:    */  {
/* 108:108 */    return new SynchronizedIndirectDoublePriorityQueue(q, sync);
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectDoublePriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */