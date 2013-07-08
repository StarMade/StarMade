/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collection;
/*   5:    */
/*  53:    */public class IntSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends IntCollections.EmptyCollection
/*  57:    */    implements IntSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(int ok) { throw new UnsupportedOperationException(); }
/*  62: 62 */    public Object clone() { return IntSets.EMPTY_SET; }
/*  63: 63 */    private Object readResolve() { return IntSets.EMPTY_SET; }
/*  64:    */  }
/*  65:    */  
/*  71: 71 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  72:    */  
/*  73:    */  public static class Singleton
/*  74:    */    extends AbstractIntSet
/*  75:    */    implements Serializable, Cloneable
/*  76:    */  {
/*  77:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  78:    */    protected final int element;
/*  79:    */    
/*  80: 80 */    protected Singleton(int element) { this.element = element; }
/*  81:    */    
/*  82: 82 */    public boolean add(int k) { throw new UnsupportedOperationException(); }
/*  83: 83 */    public boolean contains(int k) { return k == this.element; }
/*  84: 84 */    public boolean addAll(Collection<? extends Integer> c) { throw new UnsupportedOperationException(); }
/*  85: 85 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  86: 86 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  87:    */    
/*  88:    */    public int[] toIntArray() {
/*  89: 89 */      int[] a = new int[1];
/*  90: 90 */      a[0] = this.element;
/*  91: 91 */      return a; }
/*  92:    */    
/*  93: 93 */    public boolean addAll(IntCollection c) { throw new UnsupportedOperationException(); }
/*  94: 94 */    public boolean removeAll(IntCollection c) { throw new UnsupportedOperationException(); }
/*  95: 95 */    public boolean retainAll(IntCollection c) { throw new UnsupportedOperationException(); }
/*  96:    */    
/*  98: 98 */    public IntListIterator iterator() { return IntIterators.singleton(this.element); }
/*  99:    */    
/* 100:100 */    public int size() { return 1; }
/* 101:    */    
/* 102:102 */    public Object clone() { return this; }
/* 103:    */  }
/* 104:    */  
/* 112:    */  public static IntSet singleton(int element)
/* 113:    */  {
/* 114:114 */    return new Singleton(element);
/* 115:    */  }
/* 116:    */  
/* 126:    */  public static IntSet singleton(Integer element)
/* 127:    */  {
/* 128:128 */    return new Singleton(element.intValue());
/* 129:    */  }
/* 130:    */  
/* 132:    */  public static class SynchronizedSet
/* 133:    */    extends IntCollections.SynchronizedCollection
/* 134:    */    implements IntSet, Serializable
/* 135:    */  {
/* 136:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 137:    */    
/* 138:    */    protected SynchronizedSet(IntSet s, Object sync)
/* 139:    */    {
/* 140:140 */      super(sync);
/* 141:    */    }
/* 142:    */    
/* 144:144 */    protected SynchronizedSet(IntSet s) { super(); }
/* 145:    */    
/* 146:    */    public boolean remove(int k) {
/* 147:147 */      synchronized (this.sync) { return this.collection.remove(Integer.valueOf(k)); } }
/* 148:148 */    public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); } }
/* 149:149 */    public int hashCode() { synchronized (this.sync) { return this.collection.hashCode();
/* 150:    */      }
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 157:    */  public static IntSet synchronize(IntSet s)
/* 158:    */  {
/* 159:159 */    return new SynchronizedSet(s);
/* 160:    */  }
/* 161:    */  
/* 167:    */  public static IntSet synchronize(IntSet s, Object sync)
/* 168:    */  {
/* 169:169 */    return new SynchronizedSet(s, sync);
/* 170:    */  }
/* 171:    */  
/* 172:    */  public static class UnmodifiableSet
/* 173:    */    extends IntCollections.UnmodifiableCollection
/* 174:    */    implements IntSet, Serializable
/* 175:    */  {
/* 176:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 177:    */    
/* 178:    */    protected UnmodifiableSet(IntSet s)
/* 179:    */    {
/* 180:180 */      super();
/* 181:    */    }
/* 182:    */    
/* 183:183 */    public boolean remove(int k) { throw new UnsupportedOperationException(); }
/* 184:184 */    public boolean equals(Object o) { return this.collection.equals(o); }
/* 185:185 */    public int hashCode() { return this.collection.hashCode(); }
/* 186:    */  }
/* 187:    */  
/* 193:    */  public static IntSet unmodifiable(IntSet s)
/* 194:    */  {
/* 195:195 */    return new UnmodifiableSet(s);
/* 196:    */  }
/* 197:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */