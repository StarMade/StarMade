/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collection;
/*   5:    */
/*  52:    */public class ReferenceSets
/*  53:    */{
/*  54:    */  public static class EmptySet<K>
/*  55:    */    extends ReferenceCollections.EmptyCollection<K>
/*  56:    */    implements ReferenceSet<K>, Serializable, Cloneable
/*  57:    */  {
/*  58:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  59:    */    
/*  60: 60 */    public boolean remove(Object ok) { throw new UnsupportedOperationException(); }
/*  61: 61 */    public Object clone() { return ReferenceSets.EMPTY_SET; }
/*  62: 62 */    private Object readResolve() { return ReferenceSets.EMPTY_SET; }
/*  63:    */  }
/*  64:    */  
/*  70: 70 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  71:    */  
/*  72:    */  public static class Singleton<K>
/*  73:    */    extends AbstractReferenceSet<K>
/*  74:    */    implements Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    protected final K element;
/*  78:    */    
/*  79: 79 */    protected Singleton(K element) { this.element = element; }
/*  80:    */    
/*  81: 81 */    public boolean add(K k) { throw new UnsupportedOperationException(); }
/*  82: 82 */    public boolean contains(Object k) { return k == this.element; }
/*  83: 83 */    public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/*  84: 84 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  85: 85 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  86:    */    
/*  87: 87 */    public ObjectListIterator<K> iterator() { return ObjectIterators.singleton(this.element); }
/*  88: 88 */    public int size() { return 1; }
/*  89: 89 */    public Object clone() { return this; }
/*  90:    */  }
/*  91:    */  
/*  95:    */  public static <K> ReferenceSet<K> singleton(K element)
/*  96:    */  {
/*  97: 97 */    return new Singleton(element);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public static class SynchronizedSet<K> extends ReferenceCollections.SynchronizedCollection<K> implements ReferenceSet<K>, Serializable {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:103 */    protected SynchronizedSet(ReferenceSet<K> s, Object sync) { super(sync); }
/* 104:    */    
/* 106:106 */    protected SynchronizedSet(ReferenceSet<K> s) { super(); }
/* 107:    */    
/* 108:108 */    public boolean remove(Object k) { synchronized (this.sync) { return this.collection.remove(k); } }
/* 109:109 */    public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); } }
/* 110:110 */    public int hashCode() { synchronized (this.sync) { return this.collection.hashCode();
/* 111:    */      }
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 116:    */  public static <K> ReferenceSet<K> synchronize(ReferenceSet<K> s)
/* 117:    */  {
/* 118:118 */    return new SynchronizedSet(s);
/* 119:    */  }
/* 120:    */  
/* 126:126 */  public static <K> ReferenceSet<K> synchronize(ReferenceSet<K> s, Object sync) { return new SynchronizedSet(s, sync); }
/* 127:    */  
/* 128:    */  public static class UnmodifiableSet<K> extends ReferenceCollections.UnmodifiableCollection<K> implements ReferenceSet<K>, Serializable {
/* 129:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 130:    */    
/* 131:131 */    protected UnmodifiableSet(ReferenceSet<K> s) { super(); }
/* 132:    */    
/* 133:133 */    public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/* 134:134 */    public boolean equals(Object o) { return this.collection.equals(o); }
/* 135:135 */    public int hashCode() { return this.collection.hashCode(); }
/* 136:    */  }
/* 137:    */  
/* 141:    */  public static <K> ReferenceSet<K> unmodifiable(ReferenceSet<K> s)
/* 142:    */  {
/* 143:143 */    return new UnmodifiableSet(s);
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */