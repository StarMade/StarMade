/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collection;
/*   5:    */
/*  53:    */public class ShortSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends ShortCollections.EmptyCollection
/*  57:    */    implements ShortSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(short ok) { throw new UnsupportedOperationException(); }
/*  62: 62 */    public Object clone() { return ShortSets.EMPTY_SET; }
/*  63: 63 */    private Object readResolve() { return ShortSets.EMPTY_SET; }
/*  64:    */  }
/*  65:    */  
/*  71: 71 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  72:    */  
/*  73:    */  public static class Singleton
/*  74:    */    extends AbstractShortSet
/*  75:    */    implements Serializable, Cloneable
/*  76:    */  {
/*  77:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  78:    */    protected final short element;
/*  79:    */    
/*  80: 80 */    protected Singleton(short element) { this.element = element; }
/*  81:    */    
/*  82: 82 */    public boolean add(short k) { throw new UnsupportedOperationException(); }
/*  83: 83 */    public boolean contains(short k) { return k == this.element; }
/*  84: 84 */    public boolean addAll(Collection<? extends Short> c) { throw new UnsupportedOperationException(); }
/*  85: 85 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  86: 86 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  87:    */    
/*  88:    */    public short[] toShortArray() {
/*  89: 89 */      short[] a = new short[1];
/*  90: 90 */      a[0] = this.element;
/*  91: 91 */      return a; }
/*  92:    */    
/*  93: 93 */    public boolean addAll(ShortCollection c) { throw new UnsupportedOperationException(); }
/*  94: 94 */    public boolean removeAll(ShortCollection c) { throw new UnsupportedOperationException(); }
/*  95: 95 */    public boolean retainAll(ShortCollection c) { throw new UnsupportedOperationException(); }
/*  96:    */    
/*  98: 98 */    public ShortListIterator iterator() { return ShortIterators.singleton(this.element); }
/*  99:    */    
/* 100:100 */    public int size() { return 1; }
/* 101:    */    
/* 102:102 */    public Object clone() { return this; }
/* 103:    */  }
/* 104:    */  
/* 112:    */  public static ShortSet singleton(short element)
/* 113:    */  {
/* 114:114 */    return new Singleton(element);
/* 115:    */  }
/* 116:    */  
/* 126:    */  public static ShortSet singleton(Short element)
/* 127:    */  {
/* 128:128 */    return new Singleton(element.shortValue());
/* 129:    */  }
/* 130:    */  
/* 132:    */  public static class SynchronizedSet
/* 133:    */    extends ShortCollections.SynchronizedCollection
/* 134:    */    implements ShortSet, Serializable
/* 135:    */  {
/* 136:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 137:    */    
/* 138:    */    protected SynchronizedSet(ShortSet s, Object sync)
/* 139:    */    {
/* 140:140 */      super(sync);
/* 141:    */    }
/* 142:    */    
/* 144:144 */    protected SynchronizedSet(ShortSet s) { super(); }
/* 145:    */    
/* 146:    */    public boolean remove(short k) {
/* 147:147 */      synchronized (this.sync) { return this.collection.remove(Short.valueOf(k)); } }
/* 148:148 */    public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); } }
/* 149:149 */    public int hashCode() { synchronized (this.sync) { return this.collection.hashCode();
/* 150:    */      }
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 157:    */  public static ShortSet synchronize(ShortSet s)
/* 158:    */  {
/* 159:159 */    return new SynchronizedSet(s);
/* 160:    */  }
/* 161:    */  
/* 167:    */  public static ShortSet synchronize(ShortSet s, Object sync)
/* 168:    */  {
/* 169:169 */    return new SynchronizedSet(s, sync);
/* 170:    */  }
/* 171:    */  
/* 172:    */  public static class UnmodifiableSet
/* 173:    */    extends ShortCollections.UnmodifiableCollection
/* 174:    */    implements ShortSet, Serializable
/* 175:    */  {
/* 176:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 177:    */    
/* 178:    */    protected UnmodifiableSet(ShortSet s)
/* 179:    */    {
/* 180:180 */      super();
/* 181:    */    }
/* 182:    */    
/* 183:183 */    public boolean remove(short k) { throw new UnsupportedOperationException(); }
/* 184:184 */    public boolean equals(Object o) { return this.collection.equals(o); }
/* 185:185 */    public int hashCode() { return this.collection.hashCode(); }
/* 186:    */  }
/* 187:    */  
/* 193:    */  public static ShortSet unmodifiable(ShortSet s)
/* 194:    */  {
/* 195:195 */    return new UnmodifiableSet(s);
/* 196:    */  }
/* 197:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */